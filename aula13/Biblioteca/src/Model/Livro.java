package Model;

public class Livro {
    private int id;
    private String nome;
    private String autor;
    private String genero;
    private String ISBN;

    public Livro(int id ,String nome, String autor, String genero, String ISBN) {
        this.autor = autor;
        this.nome = nome;
        this.genero = genero;
        this.ISBN = ISBN;
        this.id=id;
    }
    public Livro(String nome, String autor, String genero, String ISBN) {
        this.autor = autor;
        this.nome = nome;
        this.genero = genero;
        this.ISBN = ISBN;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getId() {
        return id;
    }
}
