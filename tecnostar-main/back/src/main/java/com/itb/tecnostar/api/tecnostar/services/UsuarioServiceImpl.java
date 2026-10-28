package com.itb.tecnostar.api.tecnostar.services;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.CadastroRequest;
import com.itb.tecnostar.api.tecnostar.controller.dtorequest.UsuarioUpdateRequest;
import com.itb.tecnostar.api.tecnostar.exceptions.BadRequest;
import com.itb.tecnostar.api.tecnostar.exceptions.NotFound;
import com.itb.tecnostar.api.tecnostar.exceptions.Unauthorized;
import com.itb.tecnostar.api.tecnostar.model.Usuario;
import com.itb.tecnostar.api.tecnostar.repository.PerfilRepository;
import com.itb.tecnostar.api.tecnostar.repository.RegistroAguaRepository;
import com.itb.tecnostar.api.tecnostar.repository.RegistroPesoRepository;
import com.itb.tecnostar.api.tecnostar.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final RegistroPesoRepository pesoRepository;
    private final RegistroAguaRepository aguaRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository,
                              RegistroPesoRepository pesoRepository, RegistroAguaRepository aguaRepository,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.pesoRepository = pesoRepository;
        this.aguaRepository = aguaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFound("Usuário não encontrado com o id " + id));
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario save(CadastroRequest request) {
        String nome = limpar(request.nome());
        String email = limpar(request.email()).toLowerCase();
        String telefone = limpar(request.telefone());
        validarNomeEmailTelefone(nome, email, telefone);
        if (request.senha() == null || request.senha().length() < 6) {
            throw new BadRequest("A senha precisa ter ao menos 6 caracteres.");
        }
        if (usuarioRepository.existsByEmail(email)) {
            throw new BadRequest("Este e-mail já está cadastrado.");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone.isEmpty() ? null : telefone);
        usuario.setDataNascimento(request.dataNascimento());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setCodStatus(true);
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario update(Long id, UsuarioUpdateRequest request) {
        Usuario usuario = findById(id);
        String nome = limpar(request.nome());
        String telefone = limpar(request.telefone());
        if (nome.isEmpty()) throw new BadRequest("Informe o nome completo.");
        if (nome.length() > 100) throw new BadRequest("O nome pode ter no máximo 100 caracteres.");
        if (telefone.length() > 20) throw new BadRequest("O telefone pode ter no máximo 20 caracteres.");
        usuario.setNome(nome);
        usuario.setTelefone(telefone.isEmpty() ? null : telefone);
        usuario.setDataNascimento(request.dataNascimento());
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFound("Usuário não encontrado com o id " + id);
        }
        // Apaga primeiro quem depende do usuário (chave estrangeira)
        aguaRepository.deleteByUsuarioId(id);
        pesoRepository.deleteByUsuarioId(id);
        perfilRepository.deleteByUsuarioId(id);
        usuarioRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public Usuario deleteLogic(Long id) {
        Usuario usuario = findById(id);
        usuario.setCodStatus(false);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(limpar(email).toLowerCase())
                .orElseThrow(() -> new Unauthorized("E-mail ou senha incorretos."));
        if (!usuario.isCodStatus() || senha == null || !passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new Unauthorized("E-mail ou senha incorretos.");
        }
        return usuario;
    }

    private void validarNomeEmailTelefone(String nome, String email, String telefone) {
        if (nome.isEmpty()) throw new BadRequest("Informe o nome completo.");
        if (nome.length() > 100) throw new BadRequest("O nome pode ter no máximo 100 caracteres.");
        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) throw new BadRequest("Informe um e-mail válido.");
        if (email.length() > 100) throw new BadRequest("O e-mail pode ter no máximo 100 caracteres.");
        if (telefone.length() > 20) throw new BadRequest("O telefone pode ter no máximo 20 caracteres.");
    }

    private String limpar(String valor) {
        return valor == null ? "" : valor.trim();
    }
}
