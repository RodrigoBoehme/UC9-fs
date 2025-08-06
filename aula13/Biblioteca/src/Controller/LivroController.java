package Controller;

import Model.Livro;
import DAO.LivroDAO;

import java.util.List;

public class LivroController {
    private LivroDAO livroDao;

    public LivroController() {this.livroDao = new LivroDAO();}

    public List<Livro> TodosOsLivros(){
        return LivroDAO.getLivros();
    }

    public void EditLivro(int idLivro,String nome,String autor,String genero,String isbn)throws Exception {
        if (nome == null || nome.trim().isEmpty() || autor == null || autor.trim().isEmpty() || genero == null || genero.trim().isEmpty() || isbn == null || isbn.trim().isEmpty()) {
            throw new Exception("Todos os Campos de Livros são obrigatorios e devem ser Validos.");
        }
        Livro book = new Livro(idLivro, nome, autor, genero, isbn);
        LivroDAO.editarLivro(book);
    }


    public void setLivro(String nomeLivro,String Autor,String Genero,String isbn)throws Exception{
        if (nomeLivro == null || nomeLivro.trim().isEmpty()) {
            throw new Exception("O nome do Livro é obrigatório.");
        }
        if (Autor == null || Autor.trim().isEmpty()) {
            throw new Exception("O Autor do Livro é obrigatório.");
        }
        if (Genero == null || Genero.trim().isEmpty()) {
            throw new Exception("O Genero do Livro é obrigatório");
        }
        if (isbn== null || isbn.trim().isEmpty()) {
            throw new Exception("isbn é um dado obrigatório");
        }
        Livro book=new Livro(nomeLivro,Autor,Genero,isbn);
        livroDao.setLivro(book);
    }
    public Livro buscarLivroPorId(int id){return LivroDAO.buscarLivroPorId(id);}
}
