package Model;

public class Aluno {
    private int id_aluno;
    private String nome;
    private int idade;
    private String telefone;

    public Aluno(String nome,int idade,String telefone){

        this.nome=nome;
        this.idade=idade;
        this.telefone=telefone;

    }

    public Aluno(int id_aluno, String nome, int idade, String telefone) {
        this.id_aluno = id_aluno;
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
    }

    public int getIdade() {return idade;}
    public String getNome() {return nome;}
    public String getTelefone() {return telefone;}
    public int getId_aluno() {return id_aluno;}
}
