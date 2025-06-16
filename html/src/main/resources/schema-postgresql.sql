CREATE DATABASE equipamentos;

\c equipamentos;

CREATE TABLE indisponibilidades (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    motivo TEXT,
    responsavel VARCHAR(100),
    setor VARCHAR(100),
    observacoes TEXT,
    data_indisponibilidade DATE
);
