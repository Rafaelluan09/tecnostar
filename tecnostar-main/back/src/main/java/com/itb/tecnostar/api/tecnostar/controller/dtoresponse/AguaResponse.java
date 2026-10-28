package com.itb.tecnostar.api.tecnostar.controller.dtoresponse;

import java.time.LocalDate;

// metaMl = meta diária em ml | totalCopos = quantidade de copos de 250 ml para bater a meta
public record AguaResponse(LocalDate data, int copos, int metaMl, int totalCopos) {
}
