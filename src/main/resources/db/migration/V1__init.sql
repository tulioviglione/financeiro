CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat_alt` datetime NOT NULL,
  `dat_ins` datetime NOT NULL,
  `email` varchar(150) NOT NULL,
  `login` varchar(100) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `senha` varchar(255) NOT NULL,
  `situacao` varchar(9) NOT NULL,
  `sobrenome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_EMAIL` (`email`),
  UNIQUE KEY `UK_LOGIN` (`login`)
)ENGINE=MyISAM DEFAULT CHARSET=latin1;
