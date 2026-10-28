package com.itb.tecnostar.api.tecnostar.controller.dtoresponse;

import com.itb.tecnostar.api.tecnostar.model.Perfil;

public record PerfilResponse(Long id, Long usuarioId, Double peso, Double altura, Integer idade, String genero,
                             Double nivelAtividade, String objetivo, String preferencia) {

    public static PerfilResponse from(Perfil p) {
        return new PerfilResponse(p.getId(), p.getUsuario().getId(), p.getPeso(), p.getAltura(), p.getIdade(),
                p.getGenero(), p.getNivelAtividade(), p.getObjetivo(), p.getPreferencia());
    }
}
