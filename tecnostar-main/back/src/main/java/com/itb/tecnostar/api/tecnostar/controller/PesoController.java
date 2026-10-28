package com.itb.tecnostar.api.tecnostar.controller;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.PesoRequest;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DataArray;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DataObject;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.PesoResponse;
import com.itb.tecnostar.api.tecnostar.model.RegistroPeso;
import com.itb.tecnostar.api.tecnostar.services.PesoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// Tela "Progresso de peso"
@RestController
@RequestMapping("/api/v1/usuarios/{usuarioId}/pesos")
public class PesoController {

    private final PesoService pesoService;

    public PesoController(PesoService pesoService) {
        this.pesoService = pesoService;
    }

    @GetMapping
    public ResponseEntity<DataArray<PesoResponse>> listar(@PathVariable("usuarioId") Long usuarioId) {
        List<PesoResponse> lista = pesoService.listar(usuarioId).stream().map(PesoResponse::from).toList();
        return ResponseEntity.ok().body(new DataArray<>(200, lista));
    }

    @PostMapping
    public ResponseEntity<DataObject> registrar(@PathVariable("usuarioId") Long usuarioId, @RequestBody PesoRequest request) {
        RegistroPeso novo = pesoService.registrar(usuarioId, request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(new DataObject(201, PesoResponse.from(novo)));
    }
}
