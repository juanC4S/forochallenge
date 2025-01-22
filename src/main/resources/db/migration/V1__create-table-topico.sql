
CREATE TABLE topico (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        mensaje TEXT NOT NULL,
                        fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(50) NOT NULL,
                        autor_id BIGINT NOT NULL,
                        curso_id BIGINT NOT NULL
);

-- Crear la tabla `respuesta`
CREATE TABLE respuesta (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           mensaje TEXT NOT NULL,
                           fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           topico_id BIGINT NOT NULL,
                           autor_id BIGINT NOT NULL,
    -- Clave foránea para relacionar con `topico`
                           CONSTRAINT fk_respuesta_topico FOREIGN KEY (topico_id) REFERENCES topico (id) ON DELETE CASCADE
);

