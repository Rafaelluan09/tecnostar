package com.itb.tecnostar.api.tecnostar.services;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.PesoRequest;
import com.itb.tecnostar.api.tecnostar.exceptions.BadRequest;
import com.itb.tecnostar.api.tecnostar.model.RegistroPeso;
import com.itb.tecnostar.api.tecnostar.model.Usuario;
import com.itb.tecnostar.api.tecnostar.repository.PerfilRepository;
import com.itb.tecnostar.api.tecnostar.repository.RegistroPesoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class PesoServiceImpl implements PesoService {

    private final RegistroPesoRepository pesoRepository;
    private final PerfilRepository perfilRepository;
    private final UsuarioService usuarioService;

    public PesoServiceImpl(RegistroPesoRepository pesoRepository, PerfilRepository perfilRepository,
                           UsuarioService usuarioService) {
        this.pesoRepository = pesoRepository;
        this.perfilRepository = perfilRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<RegistroPeso> listar(Long usuarioId) {
        usuarioService.findById(usuarioId);
        return pesoRepository.findByUsuarioIdOrderByDataRegistroAscIdAsc(usuarioId);
    }

    @Override
    @Transactional
    public RegistroPeso registrar(Long usuarioId, PesoRequest request) {
        Usuario usuario = usuarioService.findById(usuarioId);
        Double peso = request.peso();
        if (peso == null || !(peso > 20 && peso < 400)) {
            throw new BadRequest("Informe um peso válido (entre 20 e 400 kg).");
        }
        RegistroPeso registro = new RegistroPeso();
        registro.setUsuario(usuario);
        registro.setDataRegistro(LocalDate.now(ZoneId.of("America/Sao_Paulo")));
        registro.setPeso(peso);
        RegistroPeso salvo = pesoRepository.save(registro);

        // Mantém o peso atual do perfil sincronizado (a dieta usa esse valor)
        perfilRepository.findByUsuarioId(usuarioId).ifPresent(perfil -> {
            perfil.setPeso(peso);
            perfilRepository.save(perfil);
        });
        return salvo;
    }
}
