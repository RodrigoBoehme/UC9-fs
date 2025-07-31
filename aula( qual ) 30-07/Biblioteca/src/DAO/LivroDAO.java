package DAO;
import Conexao.ConexaoPostgresDB;
import Model.Livro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;//Importa preparedStatement
import java.sql.ResultSet;//importa ResultSet para consutas
import java.util.ArrayList;
import java.util.List;

import static Conexao.ConexaoPostgresDB.conectar;
import static Conexao.ConexaoPostgresDB.fecharConexao;

public class LivroDAO {
    public static void setLivro(Livro livro){

        String sql = "INSERT INTO Livro (nome_livro, autor, genero,isbn) VALUES (?,?,?,?)";

        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, livro.getNome());
                stmt.setString(2, livro.getAutor());
                stmt.setString(3, livro.getGenero());
                stmt.setString(4,livro.getISBN());
                int linhasAfetadas = stmt.executeUpdate();//executa o INSERT
                if (linhasAfetadas > 0) {
                    System.out.println("Livro: " + livro.getNome() + " inserido no BD com sucesso!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro inesperado ao inserir Livro no postgress " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    fecharConexao(conexao);
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos apos insercao: " + e.getMessage());
            }
        }
    }
    public static Livro buscarLivroPorId(int id){
        String sql="Select id_livro as id, nome_livro as nome, autor, genero, isbn from livro where id_livro=?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("autor"),
                            rs.getString("genero"),
                            rs.getString("isbn")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Livro por ID: " + e.getMessage());
        }
        return null;

    }

    public static List<Livro> getLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT id_livro,nome_livro,autor,genero,isbn from livro ORDER BY id_livro";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livro dinossauro = new Livro(
                        rs.getInt("id"),
                        rs.getString("nome_livro"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        rs.getString("isbn")
                );
                livros.add(dinossauro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar dinossauros: " + e.getMessage());
        }
        return livros;
    }
    public static void deletarLivro(int IdLivro){
        String sql="DELETE FROM livro where id_livro=?";
        Connection conexao=null;
        PreparedStatement stmt=null;

        try{
            conexao=conectar();
            if(conexao!=null){
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1,IdLivro);

                int linhasAfetadas=stmt.executeUpdate();
                if(linhasAfetadas>0){
                    System.out.println("Livro com ID "+IdLivro+" removido com sucesso!");
                }else{
                    System.out.println("Nenhum Livro encotrado com ID "+IdLivro+" para remoção.");
                }
            }
        } catch(SQLException e){
            System.err.println("Erro ao remover livro no PostgreSQL: "+e.getMessage());
        }finally {
            try{
                if(stmt!=null) stmt.close();
                if(conexao!=null)fecharConexao(conexao);
            }catch(SQLException e){
                System.err.println("Erro ao fechar recursos após atualização: "+e.getMessage());
            }
        }
   }
   public static void editarLivro(int idLivro, String novoNome,String novoAutor,String novoGenero,String novoISBN){


       String sql="UPDATE livro SET nome=?, autor=?,genero=?,isbn=? WHERE id_livro=?";
       Connection conexao=null;
       PreparedStatement stmt=null;
       try{conexao=conectar();
           if(conexao!=null){
               stmt=conexao.prepareStatement(sql);
               stmt.setString(1,novoNome);
               stmt.setString(2,novoAutor);
               stmt.setString(3,novoGenero);
               stmt.setString(4,novoISBN);
               stmt.setInt(5,idLivro);
               int linhasAfetadas=stmt.executeUpdate();
               if(linhasAfetadas>0){
                   System.out.println("Livro com ID "+idLivro+" atualizado com sucesso!");
               }else{
                   System.out.println("Nenhum aluno encontrado com ID "+idLivro+" para atualização.");
               }
           }
       }
       catch(SQLException e){
           System.err.println("Erro ao atualizar Livro no PostgreSQL: "+e.getMessage());
       }finally{
           try{
               if(stmt!=null) stmt.close();
               if(conexao!=null)fecharConexao(conexao);
           }catch(SQLException e){
               System.err.println("Erro ao fechar recursos após atualização: "+e.getMessage());
           }
       }

   }
}


