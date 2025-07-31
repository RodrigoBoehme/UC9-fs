package DAO;

import Model.Emprestimo;
import Model.Livro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;//Importa preparedStatement
import java.sql.ResultSet;//importa ResultSet para consutas

import static Conexao.ConexaoPostgresDB.conectar;
import static Conexao.ConexaoPostgresDB.fecharConexao;


public class EmprestimoDAO {
    public static void setEmprestimo(Emprestimo emp) {

        String sql = "INSERT INTO Emprestimo (id_livro, id_aluno,datahora) VALUES (?,?,NOW())";

        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, emp.getId_livro());
                stmt.setInt(2, emp.getId_aluno());
                int linhasAfetadas = stmt.executeUpdate();//executa o INSERT
                if (linhasAfetadas > 0) {
                    System.out.println("Emprestimo coms o Aluno de id: " + emp.getId_aluno()+", e livro de id"+emp.getId_livro() + " registrado no BD com sucesso!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro inesperado ao inserir registros de Emprestimo no postgress " + e.getMessage());
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
    public static void getEmprestimo(){

        String sql="select id_emprestimo, nome_livro, al.nome as Nome_Aluno, datahora from emprestimo em join aluno al on al.id_aluno=em.id_aluno join livro li on li.id_livro=em.id_livro";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;//Objeto para aramazenar os resultados da consulta

        try {
            conexao = conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                System.out.println("\n ---LIVROS CADASTRADOS NO BD ---");
                boolean encontrouAluno = false;
                while (rs.next()) {
                    encontrouAluno = true;
                    int id = rs.getInt("id_emprestimo");
                    String nome = rs.getString("nome_livro");
                    String aluno = rs.getString("nome_aluno");
                    String dataehora = rs.getString("datahora");
                    System.out.println("Id: " + id + ", Livro: " + nome + ", Aluno: " + aluno + ", Horario: " + dataehora);
                }
                if (!encontrouAluno) {
                    System.out.println("Nenhum Emprestimo encontrado.");
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
}

