import { useState } from "react";
import MenuUsuario from "../MenuUsuario/MenuUsuario";
import api from "../../services/api";
import { calcularDieta } from "../../utils/calculos";
import styles from "./ControleAgua.module.css";

function ControleAgua() {
  const perfil = api.usuarioLogado().perfil;
  const meta = perfil ? calcularDieta(perfil).agua : 2000;
  const total = Math.ceil(meta / 250);
  const [copos, setCopos] = useState(api.copos());

  function marcar(n) {
    const novo = copos === n ? n - 1 : n;
    setCopos(novo);
    api.salvarCopos(novo);
  }

  return (
    <div className="container">
      <MenuUsuario />
      <div className={`card p-4 ${styles.caixa}`}>
        <h2>💧 Água de hoje</h2>
        <p>Meta: <b>{(meta / 1000).toFixed(1)} L</b> ({total} copos de 250 ml). Você bebeu <b>{copos}</b>.</p>
        <div className={styles.copos}>
          {Array.from({ length: total }, (_, i) => (
            <button key={i} className={`${styles.copo} ${i < copos ? styles.cheio : ""}`} onClick={() => marcar(i + 1)}>💧</button>
          ))}
        </div>
        <p className="text-secondary mb-0">{copos >= total ? "🎉 Meta batida, parabéns!" : "Toque nos copos conforme for bebendo."}</p>
      </div>
    </div>
  );
}

export default ControleAgua;
