import DAO.AlunoDAO;
import DAO.EmprestimoDAO;
import DAO.LivroDAO;
import Model.Aluno;
import Model.Livro;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Aluno newAlumnus=new Aluno("Joshua",22,"212121");
        //AlunoDAO.setAluno(newAlumnus);
        //AlunoDAO.getAluno();
        //AlunoDAO.atualizarAluno(5,"Raphael",23,"31 9 9999-9909");
        //Livro newLibro=new Livro("N lembro","O guia do molicherio","Neutro","12349213");
        //LivroDAO.setLivro(newLibro);
        EmprestimoDAO.getEmprestimo();


    }
}