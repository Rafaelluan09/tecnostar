package com.itb.tecnostar.api.tecnostar.controller;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.LoginRequest;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DataObject;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.LoginResponse;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.UsuarioResponse;
import com.itb.tecnostar.api.tecnostar.model.Usuario;
import com.itb.tecnostar.api.tecnostar.services.PerfilService;
import com.itb.tecnostar.api.tecnostar.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final PerfilService perfilService;

    public AuthController(UsuarioService usuarioService, PerfilService perfilService) {
        this.usuarioService = usuarioService;
        this.perfilService = perfilService;
    }

    @PostMapping("/login")
    public ResponseEntity<DataObject> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.autenticar(request.email(), request.senha());
        LoginResponse resposta = new LoginResponse(UsuarioResponse.from(usuario),
                perfilService.existsByUsuarioId(usuario.getId()));
        return ResponseEntity.ok().body(new DataObject(200, resposta));
    }
}
