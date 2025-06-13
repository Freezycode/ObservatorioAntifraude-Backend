-- Tabela de usuários
CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    anonimo BOOLEAN NOT NULL,
    nome VARCHAR(150),
    CPF CHAR(14) UNIQUE NOT NULL,
    email VARCHAR(150),
    telefone VARCHAR(50),
    CEP CHAR(9),
    cidade VARCHAR(100),
    estado VARCHAR(100),
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela de cadastro de novo relato
CREATE TABLE cadastro_relato (
    id_cadastro_relato SERIAL PRIMARY KEY,
    id_usuario INTEGER REFERENCES usuario(id_usuario),
    tipo_relato TEXT NOT NULL,
    data_ocorrida DATE NOT NULL,
    horario TIME NOT NULL,
    sistema_relacionado TEXT NOT NULL,
    consentimento_responsabilidade BOOLEAN NOT NULL,
    aceite_termos BOOLEAN NOT NULL,
    codigo_relato VARCHAR(10) UNIQUE DEFAULT substring(md5(random()::text), 1, 8),
    foi_vitima BOOLEAN NOT NULL,
    status_relato VARCHAR(30),
    outras_pessoas_vitimas BOOLEAN NOT NULL,
    quantidade_outras_pessoas INTEGER, 
    tomou_ciencia VARCHAR(50) NOT NULL DEFAULT 'não informado',
    descricao_ocorrido TEXT NOT NULL,
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT un_rlt_unicos UNIQUE (id_usuario, tipo_relato, data_ocorrida, horario)
);

-- Tabela de status do relato
CREATE TABLE status_relato (
    id_status_relato SERIAL PRIMARY KEY,
    id_cadastro_relato INTEGER NOT NULL REFERENCES cadastro_relato(id_cadastro_relato) ON DELETE CASCADE,
    status VARCHAR(50) NOT NULL,
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de log de alterações
CREATE TABLE log_alteracoes (
    id SERIAL PRIMARY KEY,
    tabela_afetada VARCHAR(150) NOT NULL,
    registro_id INTEGER NOT NULL,
    acao VARCHAR(10) NOT NULL,
    dados_anteriores TEXT,
    dados_novos TEXT,
    usuario_id INTEGER,
    data_acao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_log_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario)
);

-- Tabela de evidências
CREATE TABLE evidencia_relato (
    id_evidencia SERIAL PRIMARY KEY,
    id_relato INTEGER NOT NULL REFERENCES cadastro_relato(id_cadastro_relato) ON DELETE CASCADE,
    tipo_arquivo VARCHAR(50),
    caminho_arquivo TEXT,
    data_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
