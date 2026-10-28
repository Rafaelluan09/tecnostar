package com.itb.tecnostar.api.tecnostar.services;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.AguaRequest;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.AguaResponse;
import com.itb.tecnostar.api.tecnostar.exceptions.BadRequest;
import com.itb.tecnostar.api.tecnostar.model.RegistroAgua;
import com.itb.tecnostar.api.tecnostar.model.Usuario;
import com.itb.tecnostar.api.tecnostar.repository.PerfilRepository;
import com.itb.tecnostar.api.tecnostar.repository.RegistroAguaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class AguaServiceImpl implements AguaService {

    private static final ZoneId ZONA_BRASIL = ZoneId.of("America/Sao_Paulo");
    private static final int ML_POR_COPO = 250;
    private static final int META_PADRAO_ML = 2000;   // usada enquanto o usuário não tem perfil
    private static final int MAX_COPOS = 40;

    private final RegistroAguaRepository aguaRepository;
    private final PerfilRepository perfilRepository;
    private final UsuarioService usuarioService;

    public AguaServiceImpl(RegistroAguaRepository aguaRepository, PerfilRepository perfilRepository,
                           UsuarioService usuarioService) {
        this.aguaRepository = aguaRepository;
        this.perfilRepository = perfilRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public AguaResponse hoje(Long usuarioId) {
        usuarioService.findById(usuarioId);
        LocalDate hoje = LocalDate.now(ZONA_BRASIL);
        int copos = aguaRepository.findByUsuarioIdAndDataRegistro(usuarioId, hoje)
                .map(RegistroAgua::getCopos).orElse(0);
        return resposta(usuarioId, hoje, copos);
    }

    @Override
    @Transactional
    public AguaResponse salvarCopos(Long usuarioId, AguaRequest request) {
        Usuario usuario = usuarioService.findById(usuarioId);
        Integer copos = request.copos();
        if (copos == null || copos < 0 || copos > MAX_COPOS) {
            throw new BadRequest("Informe a quantidade de copos (de 0 a " + MAX_COPOS + ").");
        }
        LocalDate hoje = LocalDate.now(ZONA_BRASIL);
        RegistroAgua registro = aguaRepository.findByUsuarioIdAndDataRegistro(usuarioId, hoje)
                .orElseGet(() -> {
                    RegistroAgua novo = new RegistroAgua();
                    novo.setUsuario(usuario);
                    novo.setDataRegistro(hoje);
                    return novo;
                });
        registro.setCopos(copos);
        aguaRepository.save(registro);
        return resposta(usuarioId, hoje, copos);
    }

    @Override
    public List<AguaResponse> historico(Long usuarioId) {
        usuarioService.findById(usuarioId);
        return aguaRepository.findByUsuarioIdOrderByDataRegistroDesc(usuarioId).stream()
                .map(r -> resposta(usuarioId, r.getDataRegistro(), r.getCopos()))
                .toList();
    }

    // Meta = 35 ml por kg de peso (mesma regra do front); sem perfil usa 2 L
    private AguaResponse resposta(Long usuarioId, LocalDate data, int copos) {
        int meta = perfilRepository.findByUsuarioId(usuarioId)
                .map(p -> (int) Math.round(p.getPeso() * 35))
                .orElse(META_PADRAO_ML);
        int totalCopos = (int) Math.ceil(meta / (double) ML_POR_COPO);
        return new AguaResponse(data, copos, meta, totalCopos);
    }
}
