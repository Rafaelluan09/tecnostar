-- Script do banco TecnoStar (SQL Server).
-- Use este script se preferir ddl-auto: none. Com ddl-auto: update (perfil dev) o Hibernate cria as tabelas sozinho.

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'tecnostar_db')
BEGIN
    USE master;
    ALTER DATABASE tecnostar_db SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE tecnostar_db;
END
GO

CREATE DATABASE tecnostar_db;
GO

USE tecnostar_db;
GO

CREATE TABLE usuarios (
    id              BIGINT        NOT NULL IDENTITY(1,1) PRIMARY KEY,
    nome            VARCHAR(100)  NOT NULL,
    email           VARCHAR(100)  NOT NULL,
    telefone        VARCHAR(20)   NULL,
    data_nascimento DATE          NULL,
    senha           VARCHAR(255)  NOT NULL,   -- hash BCrypt
    cod_status      BIT           NOT NULL DEFAULT 1,
    CONSTRAINT uk_usuarios_email UNIQUE (email)
);
GO

CREATE TABLE perfis (
    id              BIGINT        NOT NULL IDENTITY(1,1) PRIMARY KEY,
    peso            FLOAT         NOT NULL,   -- kg
    altura          FLOAT         NOT NULL,   -- cm
    idade           INT           NOT NULL,
    genero          VARCHAR(1)    NOT NULL,   -- 'f' ou 'm'
    nivel_atividade FLOAT         NOT NULL,   -- 1.2 | 1.375 | 1.55 | 1.725
    objetivo        VARCHAR(20)   NOT NULL,   -- emagrecer | massa | manter | saude
    preferencia     VARCHAR(20)   NOT NULL,   -- comum | vegetariano
    usuario_id      BIGINT        NOT NULL,
    CONSTRAINT uk_perfis_usuario UNIQUE (usuario_id),
    CONSTRAINT fk_perfis_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
GO

CREATE TABLE registros_peso (
    id            BIGINT  NOT NULL IDENTITY(1,1) PRIMARY KEY,
    data_registro DATE    NOT NULL,
    peso          FLOAT   NOT NULL,
    usuario_id    BIGINT  NOT NULL,
    CONSTRAINT fk_registros_peso_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
GO

CREATE TABLE registros_agua (
    id            BIGINT  NOT NULL IDENTITY(1,1) PRIMARY KEY,
    data_registro DATE    NOT NULL,
    copos         INT     NOT NULL,
    usuario_id    BIGINT  NOT NULL,
    CONSTRAINT uk_registros_agua_usuario_data UNIQUE (usuario_id, data_registro),
    CONSTRAINT fk_registros_agua_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
GO
