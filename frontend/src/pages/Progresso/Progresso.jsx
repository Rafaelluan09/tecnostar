import { useState } from "react";
import MenuUsuario from "../MenuUsuario/MenuUsuario";
import api from "../../services/api";
import styles from "./Progresso.module.css";

function Progresso() {
  const [pesos, setPesos] = useState(api.usuarioLogado().pesos);
  const [novo, setNovo] = useState("");

  function registrar(e) {
    e.preventDefault();
    const v = +novo;
    if (!(v > 20 && v < 400)) return;
    api.registrarPeso(v);
    setPesos(api.usuarioLogado().pesos);
    setNovo("");
  }

  const lista = [...pesos].reverse();
  return (
    <div className="container">
      <MenuUsuario />
      <div className={`card p-4 ${styles.caixa}`}>
        <h2>📈 Progresso de peso</h2>
        <form className="d-flex gap-2" onSubmit={registrar}>
          <input type="number" step="0.1" className="form-control" placeholder="Peso atual (kg)" value={novo} onChange={(e) => setNovo(e.target.value)} />
          <button type="submit" className="btn btn-primary">Registrar</button>
        </form>
        <div className="mt-3">
          {lista.length === 0 && <p className="text-secondary">Nenhum registro ainda.</p>}
          {lista.map((x, i) => {
            const ant = lista[i + 1];
            const dif = ant ? x.v - ant.v : 0;
            return (
              <div className={styles.linha} key={i}>
                <span>{x.d}</span>
                <span><b>{x.v} kg</b> {ant && <span className="text-secondary">({dif > 0 ? "+" : ""}{dif.toFixed(1)})</span>}</span>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default Progresso;
