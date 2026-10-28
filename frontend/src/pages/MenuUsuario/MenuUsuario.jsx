import { Link, useNavigate } from "react-router-dom";
import api from "../../services/api";
import styles from "./MenuUsuario.module.css";

function MenuUsuario() {
  const navigate = useNavigate();
  const logado = api.usuarioLogado();

  function sair() {
    api.sair();
    navigate("/");
  }

  return (
    <nav className="navbar navbar-expand-lg bg-body-tertiary p-2 rounded shadow-sm w-100 mb-4">
      <Link className={`navbar-brand ${styles.marca}`} to="/">🥗 TecnoStar</Link>
      <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
        data-bs-target="#menuTecnoStar" aria-controls="menuTecnoStar" aria-expanded="false" aria-label="Alternar navegação">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="menuTecnoStar">
        <ul className="navbar-nav me-auto">
          {logado && (
            <>
              <li className="nav-item"><Link className={`nav-link ${styles.link}`} to="/tecnostar/usuario/dieta">Minha dieta</Link></li>
              <li className="nav-item"><Link className={`nav-link ${styles.link}`} to="/tecnostar/usuario/perfil">Perfil</Link></li>
            </>
          )}
          <li className="nav-item dropdown">
            <a className={`nav-link dropdown-toggle ${styles.link}`} href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Ferramentas</a>
            <ul className="dropdown-menu">
              <li><Link className="dropdown-item" to="/tecnostar/imc">Calculadora de IMC</Link></li>
              {logado && (
                <>
                  <li><Link className="dropdown-item" to="/tecnostar/usuario/agua">Controle de água</Link></li>
                  <li><Link className="dropdown-item" to="/tecnostar/usuario/progresso">Progresso de peso</Link></li>
                  <li><hr className="dropdown-divider" /></li>
                  <li><Link className="dropdown-item" to="/tecnostar/usuario/dados">Atualizar dados</Link></li>
                </>
              )}
            </ul>
          </li>
        </ul>
        {logado ? (
          <button type="button" className="btn btn-outline-primary" onClick={sair}>Sair</button>
        ) : (
          <Link to="/tecnostar/login" className="btn btn-primary">Entrar</Link>
        )}
      </div>
    </nav>
  );
}

export default MenuUsuario;
