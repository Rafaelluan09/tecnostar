package com.itb.tecnostar.api.tecnostar.controller;

import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DataObject;
import com.itb.tecnostar.api.tecnostar.services.DietaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Tela "Minha dieta": calorias, macros e refeições calculados a partir do perfil
@RestController
@RequestMapping("/api/v1/usuarios/{usuarioId}/dieta")
public class DietaController {

    private final DietaService dietaService;

    public DietaController(DietaService dietaService) {
        this.dietaService = dietaService;
    }

    @GetMapping
    public ResponseEntity<DataObject> gerar(@PathVariable("usuarioId") Long usuarioId) {
        return ResponseEntity.ok().body(new DataObject(200, dietaService.gerar(usuarioId)));
    }
}
