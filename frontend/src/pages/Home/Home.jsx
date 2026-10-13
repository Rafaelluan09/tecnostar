import { Link } from "react-router-dom";
import MenuUsuario from "../MenuUsuario/MenuUsuario";
import api from "../../services/api";
import styles from "./Home.module.css";

const ATALHOS = [
  ["🍽️", "Dieta personalizada", "Plano baseado no seu corpo e objetivo.", "/tecnostar/usuario/dieta"],
  ["⚖️", "Calculadora de IMC", "Descubra sua faixa de peso ideal.", "/tecnostar/imc"],
  ["💧", "Controle de água", "Meta diária e registro de copos.", "/tecnostar/usuario/agua"],
  ["📈", "Progresso", "Acompanhe a evolução do seu peso.", "/tecnostar/usuario/progresso"],
];

function Home() {
  const logado = api.usuarioLogado();
  return (
    <div className="container">
      <MenuUsuario />
      <section className={styles.hero}>
        <h1>Sua dieta, do seu jeito.</h1>
        <p className="text-secondary">Informe seus dados e objetivos e receba um plano alimentar personalizado, com calorias, macronutrientes e refeições do dia.</p>
        <Link className="btn btn-primary me-2" to={logado ? "/tecnostar/usuario/dieta" : "/tecnostar/login"}>
          {logado ? "Ver minha dieta" : "Fazer login"}
        </Link>
        {!logado && <Link className="btn btn-outline-primary" to="/tecnostar/cadastro">Criar conta</Link>}
      </section>
      <div className="row g-3">
        {ATALHOS.map(([icone, titulo, texto, rota]) => (
          <div className="col-12 col-sm-6 col-lg-3" key={titulo}>
            <Link to={rota} className={`card h-100 p-3 text-decoration-none ${styles.atalho}`}>
              <div className={styles.icone}>{icone}</div>
              <h5 className="text-body">{titulo}</h5>
              <p className="text-secondary mb-0">{texto}</p>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Home;
