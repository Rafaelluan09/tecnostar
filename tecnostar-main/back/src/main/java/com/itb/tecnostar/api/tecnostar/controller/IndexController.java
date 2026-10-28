package com.itb.tecnostar.api.tecnostar.controller;

import com.itb.tecnostar.api.tecnostar.controller.dtoresponse.DataObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/index")
public class IndexController {

    @GetMapping
    public ResponseEntity<DataObject> home() {
        return ResponseEntity.ok().body(new DataObject(200, "TecnoStar API - Dietas Personalizadas"));
    }
}
