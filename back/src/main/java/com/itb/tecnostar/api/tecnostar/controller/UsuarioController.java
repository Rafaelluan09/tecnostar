package com.itb.tecnostar.api.tecnostar.controller;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.CadastroRequest;
import com.itb.tecnostar.api.tecnostar.controller.dtorequest.UsuarioUpdateRequest;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DataArray;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DataObject;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.ObjectDeleteResponse;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.UsuarioResponse;
import com.itb.tecnostar.api.tecnostar.model.Usuario;
import com.itb.tecnostar.api.tecnostar.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// Cadastro ("Criar conta") e manutenção de usuários
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<DataArray<UsuarioResponse>> findAll() {
        List<UsuarioResponse> lista = usuarioService.findAll().stream().map(UsuarioResponse::from).toList();
        return ResponseEntity.ok().body(new DataArray<>(200, lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataObject> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(new DataObject(200, UsuarioResponse.from(usuarioService.findById(id))));
    }

    @PostMapping
    public ResponseEntity<DataObject> save(@RequestBody CadastroRequest request) {
        Usuario novo = usuarioService.save(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataObject(201, UsuarioResponse.from(novo)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataObject> update(@PathVariable("id") Long id, @RequestBody UsuarioUpdateRequest request) {
        return ResponseEntity.ok().body(new DataObject(200, UsuarioResponse.from(usuarioService.update(id, request))));
    }

    @PutMapping("/delete-logic/{id}")
    public ResponseEntity<ObjectDeleteResponse> deleteLogic(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.deleteLogic(id);
        return ResponseEntity.ok().body(new ObjectDeleteResponse(200, "Usuário desativado com sucesso", usuario.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectDeleteResponse> delete(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return ResponseEntity.ok().body(new ObjectDeleteResponse(200, "Usuário excluído com sucesso", id));
    }
}
