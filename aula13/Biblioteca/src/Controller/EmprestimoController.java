package Controller;

import DAO.EmprestimoDAO;
import Model.Emprestimo;

public class EmprestimoController {
    EmprestimoDAO empreDao;
    public EmprestimoController(){this.empreDao=new EmprestimoDAO();}

    public void newEmprestimo(int idAluno,int IdLivro)throws Exception{
        if(idAluno<=0||IdLivro<=0){
            throw new Exception("Id's inseridas devem ser ID's valida");
        }

        Emprestimo emp=new Emprestimo(idAluno,IdLivro);
        EmprestimoDAO.setEmprestimo(emp);
    }

    public Emprestimo GetEmprestimoPorId(int id){
        return EmprestimoDAO.getEmprestimoPorId(id);
    }
}
