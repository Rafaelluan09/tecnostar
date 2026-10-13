package com.itb.tecnostar.api.tecnostar.services;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.CadastroRequest;
import com.itb.tecnostar.api.tecnostar.controller.dtorequest.UsuarioUpdateRequest;
import com.itb.tecnostar.api.tecnostar.model.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario findById(Long id);
    List<Usuario> findAll();
    Usuario save(CadastroRequest request);
    Usuario update(Long id, UsuarioUpdateRequest request);
    boolean delete(Long id);
    Usuario deleteLogic(Long id);
    Usuario autenticar(String email, String senha);
}
