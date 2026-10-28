package com.itb.tecnostar.api.tecnostar.services;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.AguaRequest;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.AguaResponse;

import java.util.List;

public interface AguaService {

    AguaResponse hoje(Long usuarioId);
    AguaResponse salvarCopos(Long usuarioId, AguaRequest request);
    List<AguaResponse> historico(Long usuarioId);
}
