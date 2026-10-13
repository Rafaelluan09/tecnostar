import { useState } from "react";
import { useNavigate } from "react-router-dom";
import MenuUsuario from "../MenuUsuario/MenuUsuario";
import api from "../../services/api";
import { OBJETIVOS, ATIVIDADES } from "../../utils/calculos";
import styles from "./DadosObjetivos.module.css";

function DadosObjetivos() {
  const salvo = api.usuarioLogado().perfil;
  const [f, setF] = useState({ peso: "", altura: "", idade: "", genero: "f", ativ: 1.375, objetivo: "emagrecer", pref: "comum", ...salvo });
  const [erro, setErro] = useState("");
  const navigate = useNavigate();
  const campo = (n) => ({ value: f[n], onChange: (e) => setF({ ...f, [n]: e.target.value }) });

  function gerar(e) {
    e.preventDefault();
    const p = { ...f, peso: +f.peso, altura: +f.altura, idade: +f.idade, ativ: +f.ativ };
    if (!(p.peso > 20 && p.peso < 400 && p.altura > 100 && p.altura < 250 && p.idade > 10 && p.idade < 110))
      return setErro("Confira peso, altura e idade.");
    api.salvarPerfil(p);
    navigate("/tecnostar/usuario/dieta");
  }

  return (
    <div className="container">
      <MenuUsuario />
      <form className={`card p-4 ${styles.caixa}`} onSubmit={gerar}>
        <h2>Seus dados e objetivos</h2>
        <p className="text-secondary">Usaremos essas informações para montar sua dieta.</p>
        <div className="row">
          <div className="col-6 mb-3"><label className="form-label">Peso (kg)</label><input type="number" step="0.1" className="form-control" required {...campo("peso")} /></div>
          <div className="col-6 mb-3"><label className="form-label">Altura (cm)</label><input type="number" className="form-control" required {...campo("altura")} /></div>
          <div className="col-6 mb-3"><label className="form-label">Idade</label><input type="number" className="form-control" required {...campo("idade")} /></div>
          <div className="col-6 mb-3"><label className="form-label">Gênero</label>
            <select className="form-select" {...campo("genero")}><option value="f">Feminino</option><option value="m">Masculino</option></select></div>
        </div>
        <div className="mb-3"><label className="form-label">Nível de atividade física</label>
          <select className="form-select" {...campo("ativ")}>{Object.entries(ATIVIDADES).map(([k, v]) => <option key={k} value={k}>{v}</option>)}</select></div>
        <div className="mb-3"><label className="form-label">Objetivo</label>
          <select className="form-select" {...campo("objetivo")}>{Object.entries(OBJETIVOS).map(([k, v]) => <option key={k} value={k}>{v}</option>)}</select></div>
        <div className="mb-3"><label className="form-label">Preferência alimentar</label>
          <select className="form-select" {...campo("pref")}><option value="comum">Come de tudo</option><option value="vegetariano">Vegetariana</option></select></div>
        {erro && <div className="text-danger mb-2">{erro}</div>}
        <button type="submit" className="btn btn-primary w-100">Gerar minha dieta</button>
      </form>
    </div>
  );
}

export default DadosObjetivos;
