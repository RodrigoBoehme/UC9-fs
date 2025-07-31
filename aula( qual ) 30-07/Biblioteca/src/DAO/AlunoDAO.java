package DAO;
import Model.Aluno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;//Importa preparedStatement
import java.sql.ResultSet;//importa ResultSet para consutas

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

    public static void getAluno() {
        String sql = "SELECT id_aluno,nome,idade,telefone from aluno ORDER BY id_aluno";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;//Objeto para aramazenar os resultados da consulta

        try {
            conexao = conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                System.out.println("\n ---ALUNOS CADASTRADOS NO BD ---");
                boolean encontrouAluno = false;
                while (rs.next()) {
                    encontrouAluno = true;
                    int id = rs.getInt("id_aluno");
                    String nome = rs.getString("nome");
                    Number idade = rs.getInt("idade");
                    String telefone = rs.getString("telefone");
                    System.out.println("Id: " + id + ", Nome: " + nome + ", Idade: " + idade + ", Telefone: " + telefone);
                }
                if (!encontrouAluno) {
                    System.out.println("Nenhum aluno encontrado.");
                }
                System.out.println("-------------------------------------\n");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    fecharConexao(conexao);
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos apos consulta: " + e.getMessage());
            }
        }

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
    public static void atualizarAluno(int idAluno,String novonome,int novaIdade,String novoTelefone){
        String sql="UPDATE aluno SET nome=?, idade=?,telefone=? WHERE id_aluno=?";
        Connection conexao=null;
        PreparedStatement stmt=null;
        try{conexao=conectar();
            if(conexao!=null){
                stmt=conexao.prepareStatement(sql);
                stmt.setString(1,novonome);
                stmt.setInt(2,novaIdade);
                stmt.setString(3,novoTelefone);
                stmt.setInt(4,idAluno);
                int linhasAfetadas=stmt.executeUpdate();
                if(linhasAfetadas>0){
                    System.out.println("Aluno com ID "+idAluno+" atualizado com sucesso!");
                }else{
                    System.out.println("Nenhum aluno encontrado com ID "+idAluno+" para atualização.");
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


}