import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "./index.css";
import AppRoutes from "./routes/AppRoutes";

// Tema claro/escuro automático (Bootstrap 5.3)
const escuro = window.matchMedia("(prefers-color-scheme: dark)").matches;
document.documentElement.setAttribute("data-bs-theme", escuro ? "dark" : "light");

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <AppRoutes />
  </StrictMode>
);
