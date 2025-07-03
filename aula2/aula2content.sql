select *from emprestimos;
insert into alunos(id_alunos,nome_aluno,idade_aluno,telefone)
values(1,"Nome Do Aluno",22,"telefone");

insert into livros(id_livro,nome_livro,autor,genero,isbn)
values(1,"Nome do libro","Nome do autor","Genero do livro","isb");

insert into emprestimos(alunos_id_alunos,livros_id_livro,datahora,id_emprestimo)
values(1,1,NOW(),1);