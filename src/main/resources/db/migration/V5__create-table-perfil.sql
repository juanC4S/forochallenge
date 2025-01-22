
CREATE TABLE perfil (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,      -- Llave primaria
    nombre VARCHAR(255) NOT NULL,              -- Nombre del perfil
    usuario_id BIGINT NOT NULL,                -- Clave foránea hacia usuarios
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);


