create table usuario (
id serial primary key,
nome char(20),
email char(20),
senha char(10)
);

create table conteudo (
id serial not null,
id_usuario int,
titulo char(20),
descricao char(60),
midia char(60),
foreign key (id_usuario) references usuario (id)
);