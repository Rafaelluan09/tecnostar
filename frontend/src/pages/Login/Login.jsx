import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import MenuUsuario from "../MenuUsuario/MenuUsuario";
import api from "../../services/api";
import styles from "./Login.module.css";

function Login() {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState("");
  const navigate = useNavigate();

  function entrar(e) {
    e.preventDefault();
    const usuario = api.entrar(email.trim().toLowerCase(), senha);
    if (!usuario) return setErro("E-mail ou senha incorretos.");
    navigate(usuario.perfil ? "/tecnostar/usuario/dieta" : "/tecnostar/usuario/dados");
  }

  return (
    <div className="container">
      <MenuUsuario />
      <form className={`card p-4 ${styles.caixa}`} onSubmit={entrar}>
        <h2 className="text-center mb-3">Entrar</h2>
        <div className="mb-3">
          <label className="form-label">E-mail</label>
          <input type="email" className="form-control" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Senha</label>
          <input type="password" className="form-control" value={senha} onChange={(e) => setSenha(e.target.value)} required />
        </div>
        {erro && <div className="text-danger mb-2">{erro}</div>}
        <button type="submit" className="btn btn-primary w-100">Entrar</button>
        <p className="text-center text-secondary mt-3 mb-2">Não tem conta?</p>
        <Link to="/tecnostar/cadastro" className="btn btn-outline-primary w-100">Criar conta</Link>
      </form>
    </div>
  );
}

export default Login;
