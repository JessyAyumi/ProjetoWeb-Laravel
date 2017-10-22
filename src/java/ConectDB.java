


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConectDB {
    
    public Connection conn;
    
    public ConectDB() {
        conn            = null;
        String url      = "jdbc:postgresql://localhost/"; // localizacao do servidor
        String dbName   = "ServletWeb";                    // nome do banco de dados
        String dbName   = "laravel";                    // nome do banco de dados
        String driver   = "org.postgresql.Driver";   // nome do driver de conexao
        String userName = "postgres";                    // nome do usuario do banco
//        String password = "";                    // respectiva senha
        String password = "admin";                    // respectiva senha
        String password = "root";                    // respectiva senha
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
      return conn;
    }
    
    //Fecha a conecção com bco de dados
    public void closeConnection() {
        try                { 
        conn.close(); 
        }
        catch(Exception e) { 
        e.printStackTrace(); 
        }
    } 
    
}
