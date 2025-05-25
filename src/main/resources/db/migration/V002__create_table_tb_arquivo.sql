create table if not exists tb_arquivo (
    code int(11) not null auto_increment comment 'Código identificador do arquivo',
    id_diretorio int(11) not null comment 'Código identificador do diretório',
    nome varchar(255) not null comment 'Nome do arquivo',
    rotulo varchar(100) not null comment 'Rótulo do arquivo',
    tamanho varchar(10) null comment 'Tamanho estimado do arquivo',
    extensao char(6) null comment 'Extensão do arquivo',
    endereco_fisico varchar(255) not null comment 'Endereço físico do arquivo',
    created_at datetime default current_timestamp comment 'Data de criação do arquivo',
    updated_at datetime default current_timestamp comment 'Data de última atualização',
    deleted_at datetime null comment 'Data de exclusão lógica (lixeira)',
    constraint pk_arquivo_code primary key (code),
    constraint fk_arquivo_diretorio foreign key (id_diretorio) references tb_diretorio(code),
    constraint un_arquivo unique (nome, rotulo)
) comment = 'tabela que representa os arquivos armazenados no sistema.';