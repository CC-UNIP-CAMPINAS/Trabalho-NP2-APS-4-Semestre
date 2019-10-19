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

    public static Connection getConnection(){
        if(conn == null){
            try{
                conn = DriverManager.getConnection(url, usuario, senha);
                System.out.println("Ta Mec");
            }
            catch(SQLException e){
            	e.printStackTrace();
            }
        }
        return conn;
    }

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
