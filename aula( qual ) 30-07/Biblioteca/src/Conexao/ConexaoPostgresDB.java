package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;//Importa preparedStatement
import java.sql.ResultSet;//importa ResultSet para consutas

public class ConexaoPostgresDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/Biblioteca_MVC";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "root";


    public static Connection conectar() {
        Connection conexao = null;//Inicializa a conexao como nulaa
        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.err.println("Conexao com o db Completa");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o bd: " + e.getMessage());
        }
        return conexao;// retorna a conexao, pode ser nula em caso de erro
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            //Verifica se a conexao nao e nula antes de tentar fechar:
            try {
                conexao.close();
                System.err.println("Conexao com o db encerrado ");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    public static void setAluno(int id,String nome,int idade,String telefone){
        String sql="INSERT INTO alunos (id_alunos, nome_aluno, idade_aluno, telefone) VALUES (?,?,?,?)";

        Connection conexao=null;
        PreparedStatement stmt=null;

        try{
            conexao= conectar();
            if(conexao!=null){
                stmt=conexao.prepareStatement(sql);
                stmt.setInt(1,id);
                stmt.setString(2,nome);
                stmt.setInt(3,idade);
                stmt.setString(4,telefone);
                int linhasAfetadas=stmt.executeUpdate();//executa o INSERT
                if(linhasAfetadas>0){
                    System.out.println("Aluno: "+nome+" inserido no BD com sucesso!");
                }
            }
        }catch(SQLException e){
            System.err.println("Erro inesperado ao inserir aluno no postgress "+e.getMessage());
        }finally {
            try{
                if(stmt!=null){
                    stmt.close();
                }
                if(conexao !=null){
                    fecharConexao(conexao);
                }
            }catch(SQLException e){
                System.err.println("Erro ao fechar recursos apos insercao: "+e.getMessage());
            }
        }
    }

    public static void getAluno(){
        String sql= "SELECT id_alunos,nome_aluno,idade_aluno,telefone from alunos ORDER BY id_alunos";
        Connection conexao=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;//Objeto para aramazenar os resultados da consulta

        try{
            conexao=conectar();
            if(conexao!=null){
                stmt=conexao.prepareStatement(sql);
                rs=stmt.executeQuery();
                System.out.println("\n ---ALUNOS CADASTRADOS NO BD ---");
                boolean encontrouAluno=false;
                while(rs.next()){
                    encontrouAluno=true;
                    int id=rs.getInt("id_alunos");
                    String nome = rs.getString("nome_aluno");
                    Number idade=rs.getInt("idade_aluno");
                    String telefone=rs.getString("telefone");
                    System.out.println("Id: "+id+", Nome: "+nome+", Idade: "+idade+", Telefone: "+telefone);
                }
                if(!encontrouAluno){
                    System.out.println("Nenhum aluno encontrado.");
                }
                System.out.println("-------------------------------------\n");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }finally {
            try{
                if(rs!=null) {rs.close();}
                if(stmt!=null){stmt.close();}
                if(conexao!=null){fecharConexao(conexao);}
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos apos consulta: "+e.getMessage());
            }
        }




    }
    public static void atualizarAluno(int idAluno,String novonome,int novaIdade,String novoTelefone){
        String sql="UPDATE alunos SET nome_Aluno=?, idade_Aluno=?,telefone=? WHERE id_alunos=?";
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
    public static void alumnusDeletus(int idAluno){
        String sql="DELETE FROM alunos where id_alunos=?";
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

    public static void main(String[] args){
//        Connection testeConexao = ConexaoPostgresDB.conectar();
//        if(testeConexao != null){ConexaoPostgresDB.fecharConexao(testeConexao);}}
//
//        alumnusDeletus(3);
//        setAluno(3,"Joseph",14,"31 999999999");
//        agetAluno();
    }
}




