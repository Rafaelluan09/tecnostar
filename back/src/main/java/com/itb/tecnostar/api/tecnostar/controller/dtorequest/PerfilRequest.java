package com.itb.tecnostar.api.tecnostar.controller.dtorequest;

// DTO de entrada (o que o front envia no corpo da requisição)
public record PerfilRequest(Double peso, Double altura, Integer idade, String genero, Double nivelAtividade, String objetivo, String preferencia) {
}
