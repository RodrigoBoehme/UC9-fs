package Controller;

import DAO.LivroDAO;
import Model.Aluno;
import DAO.AlunoDAO;
import Model.Livro;

import static java.lang.Integer.valueOf;

public class AlunoController {
    private AlunoDAO alunoDao;

    public AlunoController(){this.alunoDao=new AlunoDAO();}

    public void cadastrarAluno(String Nome, int idade, String Telefone)throws Exception{
        if(Nome==null||Nome.trim().isEmpty()){
            throw new Exception("O nome do aluno é obrigatorio.");
        }

        if(idade<=0||idade>120){
            throw new Exception("A Idade não é valida.");
        }
        if(Telefone==null||Telefone.trim().isEmpty()){
            throw new Exception("Telefone é Obrigatorio.");
        }
        //if(email==null||email.trim().isEmpty()||!email.contains("@")){
        //    throw new Exception("Email não é valido");
        //}

        Aluno aluno=new Aluno(Nome,idade,Telefone);
        AlunoDAO.setAluno(aluno);
    }
    public void EditAluno(int idAluno,String nome,int idade,String telefone)throws Exception{
        if (nome == null || nome.trim().isEmpty() || telefone==null|| telefone.trim().isEmpty()) {
            throw new Exception("Todos os Campos de Livros são obrigatorios e devem ser Validos.");
        }
        Aluno book = new Aluno(idAluno, nome,idade, telefone);
        AlunoDAO.atualizarAluno(book);
    }

    public Aluno buscarAlunoPorId(int id){return this.alunoDao.buscarAlunoPorId(id);}

}
