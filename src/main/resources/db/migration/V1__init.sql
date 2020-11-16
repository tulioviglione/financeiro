-- financeiroapi.usuario definition
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_alt` datetime NOT NULL,
  `dat_ins` datetime NOT NULL,
  `email` varchar(150) NOT NULL,
  `login` varchar(100) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `perfil` varchar(5) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `situacao` varchar(9) NOT NULL,
  `sobrenome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_EMAIL` (`email`),
  UNIQUE KEY `UK_LOGIN` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- financeiroapi.caixa definition
CREATE TABLE `caixa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_alt` datetime NOT NULL,
  `dat_ins` datetime NOT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `nome` varchar(50) NOT NULL,
  `situacao` varchar(7) NOT NULL,
  `tipo_caixa` varchar(13) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CAIXA_USUARIO` (`id_usuario`),
  CONSTRAINT `FK_CAIXA_USUARIO` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;