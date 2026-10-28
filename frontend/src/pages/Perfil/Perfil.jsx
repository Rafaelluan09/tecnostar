import { Link } from "react-router-dom";
import MenuUsuario from "../MenuUsuario/MenuUsuario";
import api from "../../services/api";
import styles from "./Perfil.module.css";

function Perfil() {
  const u = api.usuarioLogado();
  return (
    <div className="container">
      <MenuUsuario />
      <div className={`card p-4 ${styles.caixa}`}>
        <h2>Meu perfil</h2>
        <p><b>{u.nome}</b><br /><span className="text-secondary">{u.email}</span></p>
        <Link to="/tecnostar/usuario/dados" className="btn btn-primary">Editar dados e objetivos</Link>
      </div>
    </div>
  );
}

export default Perfil;
