import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import MenuUsuario from "../MenuUsuario/MenuUsuario";
import api from "../../services/api";
import styles from "./CriarConta.module.css";

function CriarConta() {
  const [form, setForm] = useState({ nome: "", email: "", tel: "", nasc: "", senha: "", senha2: "" });
  const [erro, setErro] = useState("");
  const navigate = useNavigate();
  const campo = (nome) => ({ value: form[nome], onChange: (e) => setForm({ ...form, [nome]: e.target.value }) });

  function cadastrar(e) {
    e.preventDefault();
    if (form.senha.length < 6) return setErro("A senha precisa ter ao menos 6 caracteres.");
    if (form.senha !== form.senha2) return setErro("As senhas não coincidem.");
    const { senha2, ...dados } = form;
    const falha = api.cadastrar({ ...dados, email: form.email.trim().toLowerCase() });
    if (falha) return setErro(falha);
    navigate("/tecnostar/usuario/dados");
  }

  return (
    <div className="container">
      <MenuUsuario />
      <form className={`card p-4 ${styles.caixa}`} onSubmit={cadastrar}>
        <h2 className="text-center mb-3">Criar conta</h2>
        <div className="mb-3"><label className="form-label">Nome completo</label><input className="form-control" required {...campo("nome")} /></div>
        <div className="mb-3"><label className="form-label">E-mail</label><input type="email" className="form-control" required {...campo("email")} /></div>
        <div className="row">
          <div className="col-sm-6 mb-3"><label className="form-label">Telefone</label><input type="tel" className="form-control" {...campo("tel")} /></div>
          <div className="col-sm-6 mb-3"><label className="form-label">Nascimento</label><input type="date" className="form-control" {...campo("nasc")} /></div>
        </div>
        <div className="mb-3"><label className="form-label">Senha (mín. 6 caracteres)</label><input type="password" className="form-control" required {...campo("senha")} /></div>
        <div className="mb-3"><label className="form-label">Confirmar senha</label><input type="password" className="form-control" required {...campo("senha2")} /></div>
        {erro && <div className="text-danger mb-2">{erro}</div>}
        <button type="submit" className="btn btn-primary w-100">Cadastrar</button>
        <p className="text-center text-secondary mt-3 mb-0">Já tem conta? <Link to="/tecnostar/login">Entrar</Link></p>
      </form>
    </div>
  );
}

export default CriarConta;
