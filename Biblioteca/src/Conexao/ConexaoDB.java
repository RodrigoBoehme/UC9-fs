package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL="jdbc:mysql://localhost:3306/biblioteca_db";
    private static final String USUARIO="root";
    private static final String SENHA="root";


    public static Connection conectar() {
        Connection conexao=null;//Inicializa a conexao como nulaa
        try{
            conexao=DriverManager.getConnection(URL,USUARIO,SENHA);
            System.err.println("Conexao com o db Completa");
        }catch(SQLException e){
            System.err.println("Erro ao conectar com o bd: "+e.getMessage());
        }
        return conexao;// retorna a conexao, pode ser nula em caso de erro
    }
    public static void fecharConexao(Connection conexao){
        if(conexao!=null){
            //Verifica se a conexao nao e nula antes de tentar fechar:
            try{
                conexao.close();
                System.err.println("Conexao com o db encerrado ");
            }catch(SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args){
        Connection testeConexao = ConexaoDB.conectar();
        if(testeConexao != null){
            ConexaoDB.fecharConexao(testeConexao);

        }
    }
}
