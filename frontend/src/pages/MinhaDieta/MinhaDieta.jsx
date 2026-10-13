import { Link, Navigate } from "react-router-dom";
import MenuUsuario from "../MenuUsuario/MenuUsuario";
import api from "../../services/api";
import { calcularDieta, OBJETIVOS, REFEICOES } from "../../utils/calculos";
import styles from "./MinhaDieta.module.css";

function MinhaDieta() {
  const usuario = api.usuarioLogado();
  const p = usuario.perfil;
  if (!p) return <Navigate to="/tecnostar/usuario/dados" replace />;
  const c = calcularDieta(p);
  const macros = [[c.kcal, "kcal/dia"], [`${c.prot}g`, "proteínas"], [`${c.carb}g`, "carboidratos"], [`${c.gord}g`, "gorduras"]];

  return (
    <div className="container">
      <MenuUsuario />
      <h2>Dieta de {usuario.nome.split(" ")[0]} · {OBJETIVOS[p.objetivo]}</h2>
      <div className="row g-2 my-2">
        {macros.map(([valor, rotulo]) => (
          <div className="col-6 col-md-3" key={rotulo}>
            <div className={styles.macro}><b>{valor}</b>{rotulo}</div>
          </div>
        ))}
      </div>
      <div className="card px-3">
        {REFEICOES[p.pref || "comum"].map(([nome, parte, texto]) => (
          <div className={styles.refeicao} key={nome}>
            <div><b className="d-block">{nome}</b><span className="text-secondary">{texto}</span></div>
            <span className={styles.pilula}>{Math.round(c.kcal * parte)} kcal</span>
          </div>
        ))}
      </div>
      <p className="text-secondary mt-3">
        Metabolismo basal: {c.tmb} kcal. Meta de água: {(c.agua / 1000).toFixed(1)} L/dia. Estimativa educativa; para acompanhamento clínico, consulte um nutricionista.
      </p>
      <Link to="/tecnostar/usuario/dados" className="btn btn-outline-primary">Atualizar dados</Link>
    </div>
  );
}

export default MinhaDieta;
