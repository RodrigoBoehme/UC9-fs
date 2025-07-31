package Controller;

import Model.Aluno;
import DAO.AlunoDAO;

import static java.lang.Integer.valueOf;

public class AlunoController {
    private AlunoDAO alunoDao;

    AlunoController(){this.alunoDao=new AlunoDAO();}

    public void cadastrarAluno(String Nome, String idade, String Telefone)throws Exception{
        if(Nome==null||Nome.trim().isEmpty()){
            throw new Exception("O nome do aluno é obrigatorio.");
        }
        if(idade==null||idade.trim().isEmpty()){
            throw new Exception("Idade é obrigatorio.");
        }
        if(Telefone==null||Telefone.trim().isEmpty()){
            throw new Exception("Telefone é Obrigatorio.");
        }
        int idadenumeric;
        try {
            idadenumeric = valueOf(idade);
        }catch(Exception e){
            throw new Exception("Numero Invalido");
        }

        Aluno aluno=new Aluno(Nome,idadenumeric,Telefone);
        AlunoDAO.setAluno(aluno);
    }

}
