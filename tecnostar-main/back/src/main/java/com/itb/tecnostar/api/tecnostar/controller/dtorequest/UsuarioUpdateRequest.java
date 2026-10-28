package com.itb.tecnostar.api.tecnostar.controller.dtorequest;

import java.time.LocalDate;

// DTO de entrada (o que o front envia no corpo da requisição)
public record UsuarioUpdateRequest(String nome, String telefone, LocalDate dataNascimento) {
}
