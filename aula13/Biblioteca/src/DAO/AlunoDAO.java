package DAO;
import Conexao.ConexaoPostgresDB;
import Model.Aluno;
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

public class AlunoDAO {

    public static void setAluno(Aluno aluno) {
        String sql = "INSERT INTO Aluno (nome, idade, telefone) VALUES (?,?,?)";

        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, aluno.getNome());
                stmt.setInt(2, aluno.getIdade());
                stmt.setString(3, aluno.getTelefone());
                int linhasAfetadas = stmt.executeUpdate();//executa o INSERT
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno: " + aluno.getNome() + " inserido no BD com sucesso!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro inesperado ao inserir aluno no postgress " + e.getMessage());
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

    public static List<Aluno> getAlunos() {
        String sql = "SELECT id_aluno,nome,idade,telefone from aluno ORDER BY id_aluno";
        List<Aluno> livros = new ArrayList<>();
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Aluno dinossauro = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("telefone")
                );
                livros.add(dinossauro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Alunos: " + e.getMessage());
        }
        return livros;
    }
    public static void alumnusDeletus(int idAluno){
        String sql="DELETE FROM aluno where id_aluno=?";
        Connection conexao=null;
        PreparedStatement stmt=null;

        try{
            conexao=conectar();
            if(conexao!=null){
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1,idAluno);

                int linhasAfetadas=stmt.executeUpdate();
                if(linhasAfetadas>0){
                    System.out.println("Aluno com ID "+idAluno+" removido com sucesso!");
                }else{
                    System.out.println("Nenhum aluno encotrado com ID "+idAluno+" para remoção.");
                }
            }
        } catch(SQLException e){
            System.err.println("Erro ao remover aluno no PostgreSQL: "+e.getMessage());
        }finally {
            try{
                if(stmt!=null) stmt.close();
                if(conexao!=null)fecharConexao(conexao);
            }catch(SQLException e){
                System.err.println("Erro ao fechar recursos após atualização: "+e.getMessage());
            }
        }
    }
    public static void atualizarAluno(Aluno aluno){
        String sql="UPDATE aluno SET nome=?, idade=?,telefone=? WHERE id_aluno=?";
        Connection conexao=null;
        PreparedStatement stmt=null;
        try{conexao=conectar();
            if(conexao!=null){
                stmt=conexao.prepareStatement(sql);
                stmt.setString(1,aluno.getNome());
                stmt.setInt(2,aluno.getIdade());
                stmt.setString(3,aluno.getTelefone());
                stmt.setInt(4,aluno.getId_aluno());
                int linhasAfetadas=stmt.executeUpdate();
                if(linhasAfetadas>0){
                    System.out.println("Aluno com ID "+aluno.getId_aluno()+" atualizado com sucesso!");
                }else{
                    System.out.println("Nenhum aluno encontrado com ID "+aluno.getId_aluno()+" para atualização.");
                }
            }
        }

        catch(SQLException e){
            System.err.println("Erro ao atualizar aluno no PostgreSQL: "+e.getMessage());
        }finally{
            try{
                if(stmt!=null) stmt.close();
                if(conexao!=null)fecharConexao(conexao);
            }catch(SQLException e){
                System.err.println("Erro ao fechar recursos após atualização: "+e.getMessage());
            }
        }
    }
    public Aluno buscarAlunoPorId(int idAluno){
        String sql="select id_aluno,nome,idade,telefone from aluno where id_aluno=?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("telefone")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Aluno por ID: " + e.getMessage());
        }
        return null;

    }


}