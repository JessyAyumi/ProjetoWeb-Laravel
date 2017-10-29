create table usuario (
id serial primary key,
nome varchar(20),
email varchar(60),
senha varchar(10)
);

create table conteudo (
id serial not null,
id_usuario int,
titulo varchar(30),
descricao varchar(60),
midia varchar(500),
foreign key (id_usuario) references usuario (id)
);