package com.itb.tecnostar.api.tecnostar.services;

import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DietaResponse;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DietaResponse.RefeicaoResponse;
import com.itb.tecnostar.api.tecnostar.model.Perfil;
import com.itb.tecnostar.api.tecnostar.util.Calculos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietaServiceImpl implements DietaService {

    private final PerfilService perfilService;

    public DietaServiceImpl(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @Override
    public DietaResponse gerar(Long usuarioId) {
        Perfil p = perfilService.findByUsuarioId(usuarioId);
        Calculos.Dieta d = Calculos.calcularDieta(p.getPeso(), p.getAltura(), p.getIdade(),
                p.getGenero(), p.getNivelAtividade(), p.getObjetivo());

        List<RefeicaoResponse> refeicoes = Calculos.REFEICOES.get(p.getPreferencia()).stream()
                .map(r -> new RefeicaoResponse(r.nome(), (int) Math.round(d.kcal() * r.parte()), r.texto()))
                .toList();

        return new DietaResponse(p.getObjetivo(), d.tmb(), d.kcal(), d.prot(), d.carb(), d.gord(), d.agua(), refeicoes);
    }
}
