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

    public static void getDino(){
        String sql="Select id_dino,nome,especie,idd_estm_anos,idade_do_dino,dieta,status from Dissionauro Order by id_Dino";
        Connection conexao=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;

        try {
            conexao=conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                System.out.println("\n ----Dinos CADASTRADOS NO BD ----");
                boolean encontrouAluno = false;
                while (rs.next()) {
                    encontrouAluno = true;
                    int id = rs.getInt("id_dino");
                    String nome = rs.getString("nome");
                    String especie=rs.getString("especie");
                    Number idade= rs.getInt("idade_do_dino");
                    Number idadeestimada=rs.getInt("idd_estm_anos");
                    String dieta=rs.getString("dieta");
                    String status=rs.getString("status");
                    System.out.println("Id: " + id + ", Nome: " + nome + ", Especie: "+especie+", Idade: " + idade +", Idade estimada: "+idadeestimada+ ", Dieta: " + dieta+", Status: "+status);
                }
                if (!encontrouAluno) {
                    System.out.println("Nenhum Dino encontrado.");
                }
                System.out.println("-------------------------------------\n");
            }
        }catch(SQLException e){
            System.err.println("Erro ao mostrar dinossauros: "+e.getMessage());
        }
    }

    public static void dinusDeletus(int id_Dino){
        String sql="Delete from Dissionauro Where id_Dino=?";
        Connection conexao=null;
        PreparedStatement stmt=null;

        try{
            conexao=conectar();
            if(conexao!=null){
                stmt=conexao.prepareStatement(sql);
                stmt.setInt(1,id_Dino);

                int linhasAfetadas=stmt.executeUpdate();
                if(linhasAfetadas>0){
                    System.out.println("Dino com id: "+id_Dino+" Deletado com sucesso!");
                }else{
                    System.out.println("Nenhum Dinossauro com id: "+id_Dino);
                }
            }
        }catch(SQLException e){
            System.err.println("Erro ao deletar dinossauro: "+e.getMessage());
        }finally {
            try{
                if(stmt!=null) stmt.close();
                if(conexao!=null)fecharConexao(conexao);
            }catch(SQLException e){
                System.err.println("Erro ao fechar recursos após atualização: "+e.getMessage());
            }
        }

    }
    public static void updateDino(int id_dino,String nome,int idade,String status){
        String sql="UPDATE Dissionauro SET nome=?, idade_do_dino=?,status=? WHERE id_dino=?";
        Connection conexao=null;
        PreparedStatement stmt=null;
        try{conexao=conectar();
            if(conexao!=null){
                stmt=conexao.prepareStatement(sql);
                stmt.setString(1,nome);
                stmt.setInt(2,idade);
                stmt.setString(3,status);
                stmt.setInt(4,id_dino);
                int linhasAfetadas=stmt.executeUpdate();
                if(linhasAfetadas>0){
                    System.out.println("Dino com ID "+id_dino+" atualizado com sucesso!");
                }else{
                    System.out.println("Nenhum aluno encontrado com ID "+id_dino+" para atualização.");
                }
            }
        }
        catch(SQLException e){
            System.err.println("Erro ao atualizar Dino no PostgreSQL: "+e.getMessage());
        }finally{
            try{
                if(stmt!=null) stmt.close();
                if(conexao!=null)fecharConexao(conexao);
            }catch(SQLException e){
                System.err.println("Erro ao fechar recursos após atualização: "+e.getMessage());
            }
        }

    }


    public static void main(String[] args){
       // Connection testeConexao = dinoDB.conectar();
       // if(testeConexao != null){dinoDB.fecharConexao(testeConexao);}
       setDino(1,"Tre","Tyranosaurus rex",15,2,"Carne","Solto");
       setDino(2,"Traaa","Tyranosaurus rex",15,2,"Carne","Solto");
       getDino();
       updateDino(1,"Josueph",10,"Morto");
       getDino();
       dinusDeletus(1);
       dinusDeletus(2);
    }
}



