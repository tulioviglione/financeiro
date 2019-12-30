CREATE TABLE FINANCEIROAPI.dbo.usuario (
	id bigint IDENTITY(1,1) NOT NULL,
	dat_alt datetime2(7) NOT NULL,
	dat_ins datetime2(7) NOT NULL,
	email varchar(150) COLLATE Latin1_General_CI_AS NOT NULL,
	login varchar(100) COLLATE Latin1_General_CI_AS NOT NULL,
	nome varchar(50) COLLATE Latin1_General_CI_AS NULL,
	perfil varchar(5) COLLATE Latin1_General_CI_AS NOT NULL,
	senha varchar(255) COLLATE Latin1_General_CI_AS NOT NULL,
	situacao varchar(9) COLLATE Latin1_General_CI_AS NOT NULL,
	sobrenome varchar(50) COLLATE Latin1_General_CI_AS NULL,
	CONSTRAINT PK_USUARIO PRIMARY KEY (id)
) GO
CREATE UNIQUE INDEX UK_EMAIL ON FINANCEIROAPI.dbo.usuario (email) GO
CREATE UNIQUE INDEX UK_LOGIN ON FINANCEIROAPI.dbo.usuario (login) GO

CREATE TABLE FINANCEIROAPI.dbo.caixa (
	id bigint IDENTITY(1,1) NOT NULL,
	dat_alt datetime2(7) NOT NULL,
	dat_ins datetime2(7) NOT NULL,
	descricao varchar(200) COLLATE Latin1_General_CI_AS NULL,
	nome varchar(50) COLLATE Latin1_General_CI_AS NOT NULL,
	situacao varchar(7) COLLATE Latin1_General_CI_AS NOT NULL,
	tipo_caixa varchar(13) COLLATE Latin1_General_CI_AS NOT NULL,
	id_usuario bigint NOT NULL,
	CONSTRAINT PK_CAIXA PRIMARY KEY (id),
	CONSTRAINT FK_CAIXA_USUARIO FOREIGN KEY (id_usuario) REFERENCES FINANCEIROAPI.dbo.usuario(id)
);