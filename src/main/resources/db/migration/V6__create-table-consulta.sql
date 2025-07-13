CREATE TABLE IF NOT EXISTS consulta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    medico_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    data DATETIME NOT NULL,

    CONSTRAINT fk_consulta_medico_id
      FOREIGN KEY (medico_id) REFERENCES medico(id),

    CONSTRAINT fk_consulta_paciente_id
      FOREIGN KEY (paciente_id) REFERENCES paciente(id)
);