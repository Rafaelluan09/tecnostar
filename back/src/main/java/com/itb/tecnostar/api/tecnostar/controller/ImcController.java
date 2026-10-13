package com.itb.tecnostar.api.tecnostar.controller;

import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DataObject;
import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.ImcResponse;
import com.itb.tecnostar.api.tecnostar.exceptions.BadRequest;
import com.itb.tecnostar.api.tecnostar.util.Calculos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Tela pública "Calcular IMC":  GET /api/v1/imc?peso=70&altura=170   (altura em cm)
@RestController
@RequestMapping("/api/v1/imc")
public class ImcController {

    @GetMapping
    public ResponseEntity<DataObject> calcular(@RequestParam("peso") double peso, @RequestParam("altura") double altura) {
        if (!(peso > 0 && altura > 0)) {
            throw new BadRequest("Peso (kg) e altura (cm) precisam ser maiores que zero.");
        }
        double imc = Calculos.calcularImc(peso, altura);
        return ResponseEntity.ok().body(new DataObject(200,
                new ImcResponse(Math.round(imc * 10) / 10.0, Calculos.categoriaImc(imc))));
    }
}
