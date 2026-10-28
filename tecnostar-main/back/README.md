# TecnoStar – Back-End (API REST)

Spring Boot 4 · Java 21 · Spring Data JPA · SQL Server · Maven.
Segue a mesma arquitetura do projeto `pizzaria-crud` do professor:
`model` (entities) → `repository` → `services` (interface + Impl) → `controller` (+ `dtorequest` / `dtoresponse`),
mais `exceptions` (erros padronizados) e `config` (CORS).

## Como rodar
1. No SQL Server Management Studio: `CREATE DATABASE tecnostar_db;`
   (ou execute `src/main/resources/ddl_tecnostardb.sql`, que também cria as tabelas).
2. Em `src/main/resources/application-dev.yml`, ajuste usuário/senha do SQL Server
   (ou defina a variável de ambiente `DB_PASSWORD`).
3. Rode `TecnostarApplication` (IntelliJ) ou `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`).
4. Teste em `http://localhost:8080/api/v1/index` e use `docs/requests/tecnostar.http` para testar os demais endpoints.

O CORS já libera o front em `http://localhost:5173` (Vite) e `http://localhost:8686` (mobile).

## Endpoints (`/api/v1`)
| Tela do front | Método e rota |
|---|---|
| Criar conta | `POST /usuarios` |
| Entrar | `POST /auth/login` |
| Usuários | `GET /usuarios`, `GET /usuarios/{id}`, `PUT /usuarios/{id}`, `PUT /usuarios/delete-logic/{id}`, `DELETE /usuarios/{id}` |
| Dados e objetivos | `GET` e `PUT /usuarios/{id}/perfil` |
| Minha dieta | `GET /usuarios/{id}/dieta` |
| Progresso de peso | `GET` e `POST /usuarios/{id}/pesos` |
| Controle de água | `GET` e `PUT /usuarios/{id}/agua/hoje`, `GET /usuarios/{id}/agua` |
| Calcular IMC | `GET /imc?peso=70&altura=165` |

Respostas no padrão do professor: `{ "status": 200, "data": ... }`; erros: `{ timestamp, messages, title, status }`.

## Tabelas
`usuarios` · `perfis` (1:1 com usuário) · `registros_peso` (N:1) · `registros_agua` (N:1, um por usuário/dia).
