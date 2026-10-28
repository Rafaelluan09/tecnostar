package com.itb.tecnostar.api.tecnostar.services;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.PerfilRequest;
import com.itb.tecnostar.api.tecnostar.model.Perfil;

public interface PerfilService {

    Perfil findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioId(Long usuarioId);
    Perfil salvar(Long usuarioId, PerfilRequest request);
}
