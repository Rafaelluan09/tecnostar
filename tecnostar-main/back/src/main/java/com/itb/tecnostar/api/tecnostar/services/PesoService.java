package com.itb.tecnostar.api.tecnostar.services;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.PesoRequest;
import com.itb.tecnostar.api.tecnostar.model.RegistroPeso;

import java.util.List;

public interface PesoService {

    List<RegistroPeso> listar(Long usuarioId);
    RegistroPeso registrar(Long usuarioId, PesoRequest request);
}
