package DAO;

import Conexao.ConexaoPostgresDB;
import Model.Emprestimo;
import Model.Livro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;//Importa preparedStatement
import java.sql.ResultSet;//importa ResultSet para consutas
import java.util.List;

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

    public static List<Emprestimo> ListarEmprestimos(){

    }
    public static Emprestimo getEmprestimoPorId(int id) {
        String sql = "Select id_emprestimo as id, id_aluno as aluno, id_livro as livro, datahora as data from emprestimo where id_emprestimo=?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Emprestimo(
                            rs.getInt("id"),
                            rs.getInt("livro"),
                            rs.getInt("aluno"),
                            rs.getDate("data")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Emprestimo por ID: " + e.getMessage());
        }
        return null;
    }
}

