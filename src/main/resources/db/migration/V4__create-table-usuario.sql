CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(191) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);



