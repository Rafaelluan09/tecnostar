package com.itb.tecnostar.api.tecnostar.controller;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.PerfilRequest;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DataObject;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.PerfilResponse;
import com.itb.tecnostar.api.tecnostar.services.PerfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Tela "Seus dados e objetivos"
@RestController
@RequestMapping("/api/v1/usuarios/{usuarioId}/perfil")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public ResponseEntity<DataObject> find(@PathVariable("usuarioId") Long usuarioId) {
        return ResponseEntity.ok().body(new DataObject(200, PerfilResponse.from(perfilService.findByUsuarioId(usuarioId))));
    }

    @PutMapping
    public ResponseEntity<DataObject> salvar(@PathVariable("usuarioId") Long usuarioId, @RequestBody PerfilRequest request) {
        return ResponseEntity.ok().body(new DataObject(200, PerfilResponse.from(perfilService.salvar(usuarioId, request))));
    }
}
