CREATE TABLE beneficiarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    data_nascimento DATE NOT NULL,
    data_inclusao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP
);
