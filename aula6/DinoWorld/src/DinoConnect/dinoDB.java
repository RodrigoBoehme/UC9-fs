package DinoConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;//Importa preparedStatement
import java.sql.ResultSet;//importa ResultSet para consutas

public class dinoDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/JurassicWorld";
    private static final String USUARIO="postgres";
    private static final String SENHA="root";


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
    public static void setDino(int id_Dino,String nome_Dino, String specie_Dino,int idadeEstimaDino,int idaDino,String dietaDino,String statusDino){

        String sql="INSERT INTO Dissionauro (id_dino, nome,especie, idd_estm_anos,idade_do_dino,dieta,status) VALUES (?,?,?,?,?,?,?)";

        Connection conexao=null;
        PreparedStatement stmt=null;

        try{
            conexao= conectar();
            if(conexao!=null){
                stmt=conexao.prepareStatement(sql);
                stmt.setInt(1,id_Dino);
                stmt.setString(2,nome_Dino);
                stmt.setString(3,specie_Dino);
                stmt.setInt(4,idadeEstimaDino);
                stmt.setInt(5,idaDino);
                stmt.setString(6,dietaDino);
                stmt.setString(7,statusDino);
                int linhasAfetadas=stmt.executeUpdate();//executa o INSERT
                if(linhasAfetadas>0){
                    System.out.println("Dino: "+nome_Dino+" inserido no BD com sucesso!");
                }
            }
        }catch(SQLException e){
            System.err.println("Erro inesperado ao inserir Dinossauro no postgress "+e.getMessage());
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




    public static void main(String[] args){
        Connection testeConexao = dinoDB.conectar();
        if(testeConexao != null){dinoDB.fecharConexao(testeConexao);}
    }
}



