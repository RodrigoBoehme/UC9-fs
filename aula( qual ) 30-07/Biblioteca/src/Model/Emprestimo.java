package Model;

import java.util.Date;

public class Emprestimo {
    int id;
    int id_livro;
    int id_aluno;
    Date dataCadastro;
    public Emprestimo(int idAluno,int idLivro){
        this.id_livro=idLivro;
        this.id_aluno=idAluno;
        dataCadastro=new Date();
    }
    public Emprestimo(int id, int id_livro, int id_aluno, Date dataCadastro) {
        this.id = id;
        this.id_livro = id_livro;
        this.id_aluno = id_aluno;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public int getId_livro() {
        return id_livro;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }
}
