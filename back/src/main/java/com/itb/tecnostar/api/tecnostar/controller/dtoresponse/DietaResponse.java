package com.itb.tecnostar.api.tecnostar.controller.dtoresponse;

import java.util.List;

public record DietaResponse(String objetivo, int metabolismoBasal, int kcal, int proteinas, int carboidratos,
                            int gorduras, int aguaMl, List<RefeicaoResponse> refeicoes) {

    public record RefeicaoResponse(String nome, int kcal, String descricao) {
    }
}
