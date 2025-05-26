create table if not exists tb_diretorio (
    code int(11) not null auto_increment comment 'Código identificador do diretório',
    code_diretorio_pai int(11) null comment 'Código do diretório pai (se for NULL, é raiz)',
    nome varchar(255) not null comment 'Nome do diretório',
    rotulo varchar(100) not null comment 'Rótulo de identificação',
    tamanho varchar(10) null comment 'Tamanho estimado do diretório',
    created_at datetime default current_timestamp not null comment 'Data de criação do diretório',
    updated_at datetime default current_timestamp null comment 'Data de última atualização',
    deleted_at datetime null comment 'Data de exclusão lógica (lixeira)',
    constraint pk_diretorio_code primary key (code),
    constraint un_diretorio unique (nome, rotulo)
) comment = 'Representa a hierarquia de diretórios no sistema de arquivos.';