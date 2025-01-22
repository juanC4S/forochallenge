CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Llave primaria
    nombre VARCHAR(255) NOT NULL,        -- Nombre del curso
    categoria VARCHAR(255) NOT NULL     -- Enum representado como cadena
);
ALTER TABLE topico
ADD CONSTRAINT fk_topico_curso
FOREIGN KEY (curso_id) REFERENCES cursos (id)
ON DELETE CASCADE;

