CREATE DATABASE IF NOT EXISTS javaeseguranca;

USE javaeseguranca;

-- Tabela paciente
CREATE TABLE IF NOT EXISTS paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL
);

-- Tabela medico
CREATE TABLE IF NOT EXISTS medico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    especialidade VARCHAR(255) NOT NULL
);

-- Tabela exame (Potassio)
CREATE TABLE IF NOT EXISTS exame_potassio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    potassio VARCHAR(255) NOT NULL,
    tipoDado VARCHAR(255) NOT NULL
);

-- Tabela valorespadroes
CREATE TABLE IF NOT EXISTS valorespadroes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    valorLimite VARCHAR(255) NOT NULL,
    unidade VARCHAR(50) NOT NULL,
    valorReferencia VARCHAR(255) NOT NULL
);

-- Tabela senha
CREATE TABLE IF NOT EXISTS senha (
    id INT AUTO_INCREMENT PRIMARY KEY,
    chaveSecreta VARCHAR(255) NOT NULL
);

-- Tabela usuario
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    senhaHash VARCHAR(128) NOT NULL
);
