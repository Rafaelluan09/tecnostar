import { Navigate } from "react-router-dom";
import api from "../services/api";

function RotaPrivada({ children }) {
  return api.usuarioLogado() ? children : <Navigate to="/tecnostar/login" replace />;
}

export default RotaPrivada;
