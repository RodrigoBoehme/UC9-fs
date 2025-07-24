create table Aluno(
 id_aluno serial primary key not null,
 nome varchar(48) not null,
 idade int,
 telefone varchar(20)
)
create table Livro(
 id_livro serial primary key not null,
 nome_livro varchar(48) not null,
 autor varchar(48),
 genero varchar(48),
 isbn varchar(48)
)

create table Emprestimo(
 id_emprestimo serial primary key not null,
 id_livro int references Aluno(id_aluno),
 id_aluno int references Livro(id_livro),
 datahora DATE
)