package com.itb.tecnostar.api.tecnostar.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

// Mesmas fórmulas do front (src/utils/calculos.js), agora calculadas no servidor.
public final class Calculos {

    private Calculos() {
    }

    public static final Set<String> OBJETIVOS = Set.of("emagrecer", "massa", "manter", "saude");
    public static final Set<String> PREFERENCIAS = Set.of("comum", "vegetariano");
    public static final Set<Double> ATIVIDADES = Set.of(1.2, 1.375, 1.55, 1.725);

    public record Refeicao(String nome, double parte, String texto) {
    }

    public record Dieta(int tmb, int kcal, int prot, int gord, int carb, int agua) {
    }

    public static final Map<String, List<Refeicao>> REFEICOES = Map.of(
            "comum", List.of(
                    new Refeicao("Café da manhã", 0.25, "2 ovos mexidos, 1 fatia de pão integral, 1 fruta e café sem açúcar"),
                    new Refeicao("Lanche da manhã", 0.10, "Iogurte natural com aveia e 1 colher de chia"),
                    new Refeicao("Almoço", 0.30, "Arroz e feijão, filé de frango grelhado ou peixe, salada colorida e legumes"),
                    new Refeicao("Lanche da tarde", 0.10, "1 fruta com um punhado de castanhas"),
                    new Refeicao("Jantar", 0.25, "Omelete de legumes ou carne magra, batata-doce e salada verde")),
            "vegetariano", List.of(
                    new Refeicao("Café da manhã", 0.25, "Panqueca de aveia e banana, pasta de amendoim e frutas"),
                    new Refeicao("Lanche da manhã", 0.10, "Iogurte natural (ou vegetal) com granola"),
                    new Refeicao("Almoço", 0.30, "Arroz integral, feijão, grão-de-bico ou lentilha, tofu grelhado e legumes"),
                    new Refeicao("Lanche da tarde", 0.10, "Hummus com palitos de cenoura e pepino"),
                    new Refeicao("Jantar", 0.25, "Risoto de cogumelos ou quinoa com legumes assados e salada")));

    // peso (kg), altura (cm), idade (anos), genero ("m"/"f"), ativ (fator de atividade), objetivo
    public static Dieta calcularDieta(double peso, double altura, int idade, String genero, double ativ, String objetivo) {
        double tmb = 10 * peso + 6.25 * altura - 5 * idade + ("m".equals(genero) ? 5 : -161);
        double gasto = tmb * ativ;
        double ajustado = "emagrecer".equals(objetivo) ? gasto - 500 : "massa".equals(objetivo) ? gasto + 400 : gasto;
        int kcal = (int) Math.round(ajustado);
        double fatorProt = "emagrecer".equals(objetivo) ? 2 : "massa".equals(objetivo) ? 1.9 : 1.5;
        int prot = (int) Math.round(peso * fatorProt);
        int gord = (int) Math.round((kcal * 0.25) / 9);
        int carb = (int) Math.round((kcal - prot * 4.0 - gord * 9.0) / 4);
        return new Dieta((int) Math.round(tmb), kcal, prot, gord, carb, (int) Math.round(peso * 35));
    }

    public static double calcularImc(double peso, double alturaCm) {
        double m = alturaCm / 100;
        return peso / (m * m);
    }

    public static String categoriaImc(double v) {
        if (v < 18.5) return "Abaixo do peso";
        if (v < 25) return "Peso normal";
        if (v < 30) return "Sobrepeso";
        if (v < 35) return "Obesidade grau I";
        if (v < 40) return "Obesidade grau II";
        return "Obesidade grau III";
    }
}
