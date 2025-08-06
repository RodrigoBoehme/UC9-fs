import Controller.AlunoController;
import Controller.EmprestimoController;
import Controller.LivroController;
import DAO.AlunoDAO;
import DAO.EmprestimoDAO;
import DAO.LivroDAO;
import Model.Aluno;
import Model.Livro;
import View.CadastrarAlunoForm;
import View.CadastrarLivroForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame {

    private JDesktopPane desktopPane;
    private LivroController livroCtr;
    private AlunoController alunoCtr;
    private EmprestimoController emprCtr;

    public Main() {
        super("Sistema de Gerenciamento da Bibilioteca");
        this.alunoCtr=new AlunoController();
        this.livroCtr=new LivroController();
        this.emprCtr=new EmprestimoController();

        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        createMenuBar();


    }

    private void createMenuBar(){
        JMenuBar menuBar=new JMenuBar();

        JMenu menuLivro=new JMenu("Livro");
        JMenuItem itemCadastLivro=new JMenuItem("Cadastrar Livro");
        JMenuItem ListarLivros=new JMenuItem("Listar Livros");
        itemCadastLivro.addActionListener(e->openLivrosForm(null));
        menuLivro.add(itemCadastLivro);

        JMenu menuAluno=new JMenu("Aluno");
        JMenuItem itemCastAlun=new JMenuItem("Cadastrar Aluno");
        JMenuItem ListarAlunos=new JMenuItem("Listar Alunos");
        itemCastAlun.addActionListener(e->openAlunoForm(null));
        menuAluno.add(itemCastAlun);



        menuBar.add(menuLivro);
        menuBar.add(menuAluno);
        JMenu menuSair = new JMenu("Sair");
        JMenuItem itemSair = new JMenuItem("Sair do Sistema");
        itemSair.addActionListener(e -> System.exit(0));

        menuSair.add(itemSair);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);

    }
    private void openLivrosForm(Integer idLivro){
        CadastrarLivroForm livroForm=new CadastrarLivroForm(livroCtr,idLivro);
        desktopPane.add(livroForm);
        livroForm.setVisible(true);
        livroForm.toFront();
    }
    private void openAlunoForm(Integer idAluno){
        CadastrarAlunoForm aluform=new CadastrarAlunoForm(alunoCtr,idAluno);
        desktopPane.add(aluform);
        aluform.setVisible(true);
        aluform.toFront();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}