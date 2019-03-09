create table Usuario(
	usercode integer primary key unique,
	login varchar(40) not null,
	senha varchar(40) not null,
	nome varchar(70) not null,
	email varchar(40) not null,
	cpf varchar(11) not null unique,
	codred varchar(15)
);
create table Pessoa(
	codinsc serial primary key unique,
	usercode integer not null,
	estado varchar(25) not null,
	numero varchar(6) not null,
	serie varchar(10),
	endereco varchar(40) not null,
	turno varchar(10),
	bairro varchar(25) not null,
	turma varchar(5),
	cidade varchar(30) not null,
	categoria varchar(20) not null,
	email varchar(40) not null,
	contato varchar(15) not null,
	nome varchar(50) not null,
	cep varchar(10),
	ativos integer not null,
	total_emprestimos integer not null,
	foreign key(usercode) references Usuario(usercode)
);
create table Material(
	nchamada serial primary key unique,
	usercode integer not null,
	corestante varchar(10) not null,
	codprateleira integer not null,
	data date not null,
	titulo text not null,
	exemplar integer,
	volume integer,
	local text not null,
	anopublicacao integer not null,
	formadeaquisicao varchar(15) not null,
	observacao text,
	status varchar(10) not null,
	foreign key(usercode) references Usuario(usercode)
);

create table Livro(
	nchamada integer primary key,
	autor varchar(50) not null,
	editora varchar(40) not null,
	cdu integer,
	cdd integer,
	foreign key(nchamada) references Material(nchamada)
); 

create table Multimidia(
	nchamada integer primary key,
	produtor varchar(50) not null,
	estudio varchar(40) not null,
	foreign key(nchamada) references Material(nchamada)
);

create table Emprestimo(
	codemp serial primary key unique,
	codinsc integer not null,
	usercode integer not null,
	dataemp Date not null,
	datadev date not null,
	status varchar(20) not null,
	foreign key(usercode) references Usuario(usercode),
	foreign key(codinsc) references Pessoa(codinsc)
);


create table Indicar(
	nchamada integer,
	codemp integer,
	primary key(nchamada,codemp),
	foreign key(nchamada) references Material(nchamada),
	foreign key(codemp) references Emprestimo(codemp)
);
