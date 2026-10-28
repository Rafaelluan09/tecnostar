package com.itb.tecnostar.api.tecnostar.services;

import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DietaResponse;

public interface DietaService {

    DietaResponse gerar(Long usuarioId);
}
