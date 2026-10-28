package com.itb.tecnostar.api.tecnostar.controller.dtoresponse;

import com.itb.tecnostar.api.tecnostar.model.Usuario;

import java.time.LocalDate;

// Nunca devolvemos a senha (nem o hash) para o front
public record UsuarioResponse(Long id, String nome, String email, String telefone,
                              LocalDate dataNascimento, boolean codStatus) {

    public static UsuarioResponse from(Usuario u) {
        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), u.getTelefone(),
                u.getDataNascimento(), u.isCodStatus());
    }
}
