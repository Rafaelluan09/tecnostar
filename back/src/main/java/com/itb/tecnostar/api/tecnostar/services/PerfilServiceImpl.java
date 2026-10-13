package com.itb.tecnostar.api.tecnostar.services;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.PerfilRequest;
import com.itb.tecnostar.api.tecnostar.exceptions.BadRequest;
import com.itb.tecnostar.api.tecnostar.exceptions.NotFound;
import com.itb.tecnostar.api.tecnostar.model.Perfil;
import com.itb.tecnostar.api.tecnostar.model.RegistroPeso;
import com.itb.tecnostar.api.tecnostar.model.Usuario;
import com.itb.tecnostar.api.tecnostar.repository.PerfilRepository;
import com.itb.tecnostar.api.tecnostar.repository.RegistroPesoRepository;
import com.itb.tecnostar.api.tecnostar.util.Calculos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;
    private final RegistroPesoRepository pesoRepository;
    private final UsuarioService usuarioService;

    public PerfilServiceImpl(PerfilRepository perfilRepository, RegistroPesoRepository pesoRepository,
                             UsuarioService usuarioService) {
        this.perfilRepository = perfilRepository;
        this.pesoRepository = pesoRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Perfil findByUsuarioId(Long usuarioId) {
        usuarioService.findById(usuarioId); // garante 404 se o usuário não existir
        return perfilRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new NotFound("O usuário " + usuarioId + " ainda não preencheu seus dados e objetivos."));
    }

    @Override
    public boolean existsByUsuarioId(Long usuarioId) {
        return perfilRepository.findByUsuarioId(usuarioId).isPresent();
    }

    // Cria o perfil na primeira vez e atualiza nas próximas (tela "Seus dados e objetivos")
    @Override
    @Transactional
    public Perfil salvar(Long usuarioId, PerfilRequest r) {
        Usuario usuario = usuarioService.findById(usuarioId);
        validar(r);

        Perfil perfil = perfilRepository.findByUsuarioId(usuarioId).orElseGet(Perfil::new);
        perfil.setUsuario(usuario);
        perfil.setPeso(r.peso());
        perfil.setAltura(r.altura());
        perfil.setIdade(r.idade());
        perfil.setGenero(r.genero());
        perfil.setNivelAtividade(r.nivelAtividade());
        perfil.setObjetivo(r.objetivo());
        perfil.setPreferencia(r.preferencia());
        Perfil salvo = perfilRepository.save(perfil);

        // Primeiro peso do histórico (igual ao front: se ainda não há registros, cria o inicial)
        if (!pesoRepository.existsByUsuarioId(usuarioId)) {
            RegistroPeso inicial = new RegistroPeso();
            inicial.setUsuario(usuario);
            inicial.setDataRegistro(LocalDate.now(ZoneId.of("America/Sao_Paulo")));
            inicial.setPeso(r.peso());
            pesoRepository.save(inicial);
        }
        return salvo;
    }

    private void validar(PerfilRequest r) {
        if (r.peso() == null || r.altura() == null || r.idade() == null
                || !(r.peso() > 20 && r.peso() < 400 && r.altura() > 100 && r.altura() < 250
                && r.idade() > 10 && r.idade() < 110)) {
            throw new BadRequest("Confira peso, altura e idade.");
        }
        if (!"m".equals(r.genero()) && !"f".equals(r.genero())) {
            throw new BadRequest("Gênero inválido. Use 'f' ou 'm'.");
        }
        if (r.nivelAtividade() == null || !Calculos.ATIVIDADES.contains(r.nivelAtividade())) {
            throw new BadRequest("Nível de atividade inválido. Use 1.2, 1.375, 1.55 ou 1.725.");
        }
        if (r.objetivo() == null || !Calculos.OBJETIVOS.contains(r.objetivo())) {
            throw new BadRequest("Objetivo inválido. Use: emagrecer, massa, manter ou saude.");
        }
        if (r.preferencia() == null || !Calculos.PREFERENCIAS.contains(r.preferencia())) {
            throw new BadRequest("Preferência alimentar inválida. Use: comum ou vegetariano.");
        }
    }
}
