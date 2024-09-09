CREATE TABLE IF NOT EXISTS `tb_clientes`(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `cpf` VARCHAR(11) NOT NULL UNIQUE,
    `data_nascimento` DATE NOT NULL,
    `usuario_id` BIGINT NOT NULL,
    FOREIGN KEY (`usuario_id`) REFERENCES `tb_usuarios`(`id`)
);

CREATE TABLE IF NOT EXISTS `tb_fornecedores`(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `cnpj` VARCHAR(14) NOT NULL UNIQUE,
    `razao_social` VARCHAR(255) NOT NULL UNIQUE,
    `usuario_id` BIGINT NOT NULL,
    FOREIGN KEY (`usuario_id`) REFERENCES `tb_usuarios`(`id`)
);