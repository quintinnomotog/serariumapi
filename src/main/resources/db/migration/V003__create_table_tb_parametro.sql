create table if not exists tb_parametro (
	code int(11) not null auto_increment comment 'Identificador único da tabela',
	chave varchar(50) not null comment 'Guarda a Chave do Parâmetro',
	valor varchar(100) not null comment 'Guarda a Valor do Parâmetro',
	constraint pk_parametro_code primary key (code),
	constraint un_parametro unique (chave, valor)
);