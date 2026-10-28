// Serviço de dados. Como não há back-end, usa o localStorage do navegador.
// Para ligar a uma API real, troque o corpo destas funções por chamadas axios.
const ler = (k, padrao) => {
  try { return JSON.parse(localStorage.getItem(k)) ?? padrao; } catch { return padrao; }
};
const gravar = (k, v) => {
  try { localStorage.setItem(k, JSON.stringify(v)); } catch { /* ignora */ }
};
const hoje = () => new Date().toLocaleDateString("pt-BR");

const api = {
  usuarioLogado() {
    const email = ler("ts_cur", null);
    return email ? { email, ...ler("ts_users", {})[email] } : null;
  },
  atualizar(dados) {
    const u = this.usuarioLogado();
    const todos = ler("ts_users", {});
    todos[u.email] = { ...todos[u.email], ...dados };
    gravar("ts_users", todos);
  },
  cadastrar({ email, ...resto }) {
    const todos = ler("ts_users", {});
    if (todos[email]) return "Este e-mail já está cadastrado.";
    todos[email] = { ...resto, senha: btoa(resto.senha), perfil: null, agua: {}, pesos: [] };
    gravar("ts_users", todos);
    gravar("ts_cur", email);
    return null;
  },
  entrar(email, senha) {
    const u = ler("ts_users", {})[email];
    if (!u || u.senha !== btoa(senha)) return null;
    gravar("ts_cur", email);
    return u;
  },
  sair() { gravar("ts_cur", null); },
  salvarPerfil(perfil) {
    const u = this.usuarioLogado();
    const pesos = u.pesos.length ? u.pesos : [{ d: hoje(), v: perfil.peso }];
    this.atualizar({ perfil, pesos });
  },
  registrarPeso(v) {
    const u = this.usuarioLogado();
    this.atualizar({ pesos: [...u.pesos, { d: hoje(), v }], perfil: u.perfil && { ...u.perfil, peso: v } });
  },
  copos() { return (this.usuarioLogado().agua || {})[hoje()] || 0; },
  salvarCopos(n) { this.atualizar({ agua: { ...this.usuarioLogado().agua, [hoje()]: n } }); },
};

export default api;
