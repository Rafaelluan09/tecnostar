package com.itb.tecnostar.api.tecnostar;

import com.itb.tecnostar.api.tecnostar.util.Calculos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Teste sem banco de dados: confere se as fórmulas do servidor batem com as do front (calculos.js)
class CalculosTest {

    @Test
    void dietaEmagrecer() {
        // mulher, 70 kg, 165 cm, 25 anos, atividade leve, emagrecer
        Calculos.Dieta d = Calculos.calcularDieta(70, 165, 25, "f", 1.375, "emagrecer");
        assertEquals(1445, d.tmb());
        assertEquals(1487, d.kcal());
        assertEquals(140, d.prot());
        assertEquals(41, d.gord());
        assertEquals(140, d.carb());
        assertEquals(2450, d.agua());
    }

    @Test
    void imcECategoria() {
        double imc = Calculos.calcularImc(70, 165);
        assertEquals(25.7, Math.round(imc * 10) / 10.0);
        assertEquals("Sobrepeso", Calculos.categoriaImc(imc));
        assertEquals("Peso normal", Calculos.categoriaImc(22));
    }
}
