package com.itb.tecnostar.api.tecnostar.controller.dtoresponse;

import com.itb.tecnostar.api.tecnostar.model.RegistroPeso;

import java.time.LocalDate;

public record PesoResponse(Long id, LocalDate data, Double peso) {

    public static PesoResponse from(RegistroPeso r) {
        return new PesoResponse(r.getId(), r.getDataRegistro(), r.getPeso());
    }
}
