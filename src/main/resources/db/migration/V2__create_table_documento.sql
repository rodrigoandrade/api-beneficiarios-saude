CREATE TABLE documentos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(20) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    data_inclusao TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP,
    id_beneficiario BIGINT NOT NULL,
    FOREIGN KEY (id_beneficiario) REFERENCES beneficiarios(id)
);
