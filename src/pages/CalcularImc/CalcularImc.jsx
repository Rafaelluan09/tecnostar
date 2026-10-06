import { useState } from "react";
import MenuUsuario from "../MenuUsuario/MenuUsuario";
import api from "../../services/api";
import { calcularImc, categoriaImc } from "../../utils/calculos";
import styles from "./CalcularImc.module.css";

function CalcularImc() {
  const perfil = api.usuarioLogado()?.perfil;
  const [peso, setPeso] = useState(perfil?.peso ?? "");
  const [altura, setAltura] = useState(perfil?.altura ?? "");
  const p = +peso, a = +altura;
  const valido = p > 0 && a > 0;
  const imc = valido ? calcularImc(p, a) : 0;
  const pos = Math.min(Math.max((imc - 15) / 25, 0), 1) * 100;
  const faixa = (v) => (v * (a / 100) ** 2).toFixed(1);

  return (
    <div className="container">
      <MenuUsuario />
      <div className={`card p-4 ${styles.caixa}`}>
        <h2>Calculadora de IMC</h2>
        <div className="row">
          <div className="col-6"><label className="form-label">Peso (kg)</label><input type="number" step="0.1" className="form-control" value={peso} onChange={(e) => setPeso(e.target.value)} /></div>
          <div className="col-6"><label className="form-label">Altura (cm)</label><input type="number" className="form-control" value={altura} onChange={(e) => setAltura(e.target.value)} /></div>
        </div>
        {valido && (
          <div className="text-center mt-3">
            <div className={styles.valor}>{imc.toFixed(1)}</div>
            <b>{categoriaImc(imc)}</b>
            <div className={styles.barra}><i style={{ left: `calc(${pos}% - 2px)` }}></i></div>
            <p className="text-secondary">Faixa de peso saudável para sua altura: {faixa(18.5)} a {faixa(24.9)} kg.</p>
          </div>
        )}
      </div>
    </div>
  );
}

export default CalcularImc;
