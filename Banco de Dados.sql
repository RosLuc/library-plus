create table Usuario(
	UserCode integer primary key unique,
	login varchar(40) not null,
	senha varchar(40) not null,
	nome varchar(70) not null,
	email varchar(40) not null,
	cpf varchar(11) not null unique
);
create table Pessoa(
	CodInsc serial primary key unique,
	UserCode integer not null,
	Estado varchar(25) not null,
	Numero varchar(6) not null,
	Serie varchar(10),
	Endereco varchar(40) not null,
	Turno varchar(10),
	Bairro varchar(25) not null,
	Turma varchar(5),
	Cidade varchar(30) not null,
	Categoria varchar(20) not null,
	Email varchar(40) not null,
	Contato varchar(15) not null,
	Nome varchar(50) not null,
	Cep varchar(10),
	foreign key(UserCode) references Usuario(UserCode)
);
create table Prateleira(
	codestante integer unique ,
	codprateleira integer,
	corredor integer not null,
	genero varchar(20) not null,
	primary key (codestante, codprateleira)
);
create table Material(
	nchamada text primary key unique,
	usercode integer not null,
	codestante integer not null,
	codprateleira integer not null,
	nsequencia integer not null,
	data date not null,
	titulo text not null,
	exemplar integer,
	volume integer,
	local text not null,
	anopublicacao integer not null,
	formadeaquisicao varchar(15) not null,
	observacao text,
	status varchar(10) not null,
	foreign key(usercode) references Usuario(UserCode),
	foreign key(codestante, codprateleira) references Prateleira (codestante, codprateleira)
);

create table Livro(
	nchamada text primary key,
	autor varchar(50) not null,
	editora varchar(40) not null,
	foreign key(nchamada) references Material(nchamada)
); 

create table Multimidia(
	nchamada text primary key,
	produtor varchar(50) not null,
	estudio varchar(40) not null,
	foreign key(nchamada) references Material(nchamada)
);