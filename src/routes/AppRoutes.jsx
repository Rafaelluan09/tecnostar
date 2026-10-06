import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "../pages/Home/Home";
import Login from "../pages/Login/Login";
import CriarConta from "../pages/CriarConta/CriarConta";
import DadosObjetivos from "../pages/DadosObjetivos/DadosObjetivos";
import MinhaDieta from "../pages/MinhaDieta/MinhaDieta";
import CalcularImc from "../pages/CalcularImc/CalcularImc";
import ControleAgua from "../pages/ControleAgua/ControleAgua";
import Progresso from "../pages/Progresso/Progresso";
import Perfil from "../pages/Perfil/Perfil";
import RotaPrivada from "../components/RotaPrivada";

function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/tecnostar/login" element={<Login />} />
        <Route path="/tecnostar/cadastro" element={<CriarConta />} />
        <Route path="/tecnostar/imc" element={<CalcularImc />} />
        <Route path="/tecnostar/usuario/dados" element={<RotaPrivada><DadosObjetivos /></RotaPrivada>} />
        <Route path="/tecnostar/usuario/dieta" element={<RotaPrivada><MinhaDieta /></RotaPrivada>} />
        <Route path="/tecnostar/usuario/agua" element={<RotaPrivada><ControleAgua /></RotaPrivada>} />
        <Route path="/tecnostar/usuario/progresso" element={<RotaPrivada><Progresso /></RotaPrivada>} />
        <Route path="/tecnostar/usuario/perfil" element={<RotaPrivada><Perfil /></RotaPrivada>} />
      </Routes>
    </BrowserRouter>
  );
}

export default AppRoutes;
