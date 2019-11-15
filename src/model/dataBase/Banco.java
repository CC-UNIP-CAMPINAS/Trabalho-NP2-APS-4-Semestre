package model.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {
    public final static String usuario = "postgres";
    public final static String senha = "root";
    public final static String url = "jdbc:postgresql:biblioteca";
    public static Connection conn = null;

    
    //Abre a conexão com banco de dados
    public static Connection getConnection(){
        if(conn == null){
            try{
                conn = DriverManager.getConnection(url, usuario, senha);
            }
            catch(SQLException e){
            	e.printStackTrace();
            }
        }
        return conn;
    }
    
    //fecha a conexão com banco de dados
    public static void closeConnection(){
        if(conn != null){
            try{
                conn.close();
                conn = null;
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    //fecha os dois tipos de Statments
    public static void closeStatement(Statement st){
        if(st != null){
            try{
                st.close();
                //st = null;
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    //fecha o resultSet
    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
                rs = null;
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
