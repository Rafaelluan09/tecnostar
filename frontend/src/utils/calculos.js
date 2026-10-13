export const OBJETIVOS = { emagrecer: "Emagrecer", massa: "Ganhar massa muscular", manter: "Manter o peso", saude: "Alimentação saudável" };
export const ATIVIDADES = { 1.2: "Sedentário", 1.375: "Leve (1–2x/semana)", 1.55: "Moderado (3–5x/semana)", 1.725: "Intenso (6–7x/semana)" };

export function calcularDieta(p) {
  const tmb = 10 * p.peso + 6.25 * p.altura - 5 * p.idade + (p.genero === "m" ? 5 : -161);
  const gasto = tmb * p.ativ;
  const kcal = Math.round(p.objetivo === "emagrecer" ? gasto - 500 : p.objetivo === "massa" ? gasto + 400 : gasto);
  const prot = Math.round(p.peso * (p.objetivo === "emagrecer" ? 2 : p.objetivo === "massa" ? 1.9 : 1.5));
  const gord = Math.round((kcal * 0.25) / 9);
  const carb = Math.round((kcal - prot * 4 - gord * 9) / 4);
  return { tmb: Math.round(tmb), kcal, prot, gord, carb, agua: Math.round(p.peso * 35) };
}

export const calcularImc = (peso, altura) => peso / (altura / 100) ** 2;

export const categoriaImc = (v) =>
  v < 18.5 ? "Abaixo do peso" : v < 25 ? "Peso normal" : v < 30 ? "Sobrepeso" :
  v < 35 ? "Obesidade grau I" : v < 40 ? "Obesidade grau II" : "Obesidade grau III";

export const REFEICOES = {
  comum: [
    ["Café da manhã", 0.25, "2 ovos mexidos, 1 fatia de pão integral, 1 fruta e café sem açúcar"],
    ["Lanche da manhã", 0.1, "Iogurte natural com aveia e 1 colher de chia"],
    ["Almoço", 0.3, "Arroz e feijão, filé de frango grelhado ou peixe, salada colorida e legumes"],
    ["Lanche da tarde", 0.1, "1 fruta com um punhado de castanhas"],
    ["Jantar", 0.25, "Omelete de legumes ou carne magra, batata-doce e salada verde"],
  ],
  vegetariano: [
    ["Café da manhã", 0.25, "Panqueca de aveia e banana, pasta de amendoim e frutas"],
    ["Lanche da manhã", 0.1, "Iogurte natural (ou vegetal) com granola"],
    ["Almoço", 0.3, "Arroz integral, feijão, grão-de-bico ou lentilha, tofu grelhado e legumes"],
    ["Lanche da tarde", 0.1, "Hummus com palitos de cenoura e pepino"],
    ["Jantar", 0.25, "Risoto de cogumelos ou quinoa com legumes assados e salada"],
  ],
};
