create table if not exists tb_diretorio (
    code int(11) not null auto_increment comment 'Código identificador do diretório',
    code_diretorio_pai int(11) null comment 'Código do diretório pai (se for NULL, é raiz)',
    code_public varchar(255) not null comment 'Identificador público do diretório em formato UUID',
    nome varchar(255) not null comment 'Nome do diretório',
    endereco_fisico varchar(255) not null comment 'Endereço físico do diretório',
    tamanho varchar(10) null comment 'Tamanho estimado do diretório',
    created_at datetime default current_timestamp not null comment 'Data de criação do diretório',
    updated_at datetime default current_timestamp null comment 'Data de última atualização',
    deleted_at datetime null comment 'Data de exclusão lógica (lixeira)',
    constraint pk_diretorio_code primary key (code),
    constraint un_diretorio unique (nome, code_diretorio_pai)
) comment = 'Representa a hierarquia de diretórios no sistema de arquivos.';