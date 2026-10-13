package com.itb.tecnostar.api.tecnostar.controller.dtoresponse;

// temPerfil: o front usa para decidir se vai para "dieta" (true) ou "dados e objetivos" (false)
public record LoginResponse(UsuarioResponse usuario, boolean temPerfil) {
}
