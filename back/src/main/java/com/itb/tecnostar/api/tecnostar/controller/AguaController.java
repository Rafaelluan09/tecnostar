package com.itb.tecnostar.api.tecnostar.controller;

import com.itb.tecnostar.api.tecnostar.controller.dtorequest.AguaRequest;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.AguaResponse;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DataArray;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DataObject;
import com.itb.tecnostar.api.tecnostar.services.AguaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Tela "Controle de água"
@RestController
@RequestMapping("/api/v1/usuarios/{usuarioId}/agua")
public class AguaController {

    private final AguaService aguaService;

    public AguaController(AguaService aguaService) {
        this.aguaService = aguaService;
    }

    @GetMapping("/hoje")
    public ResponseEntity<DataObject> hoje(@PathVariable("usuarioId") Long usuarioId) {
        return ResponseEntity.ok().body(new DataObject(200, aguaService.hoje(usuarioId)));
    }

    @PutMapping("/hoje")
    public ResponseEntity<DataObject> salvarCopos(@PathVariable("usuarioId") Long usuarioId, @RequestBody AguaRequest request) {
        return ResponseEntity.ok().body(new DataObject(200, aguaService.salvarCopos(usuarioId, request)));
    }

    @GetMapping
    public ResponseEntity<DataArray<AguaResponse>> historico(@PathVariable("usuarioId") Long usuarioId) {
        return ResponseEntity.ok().body(new DataArray<>(200, aguaService.historico(usuarioId)));
    }
}
