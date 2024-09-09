CREATE TABLE IF NOT EXISTS `tb_usuarios` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `nome` VARCHAR(150) NOT NULL,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `senha` VARCHAR(255) NOT NULL,
    `telefone` VARCHAR(255),
    `celular` VARCHAR(255) NOT NULL,
    `administrador` BIT(1) NOT NULL,
    `colaborador` BIT(1) NOT NULL,
    `usuario_externo` BIT(1) NOT NULL
);