package projetopoo;


import java.sql.*;
/**
 *
 * @author Dayana
 */
public class ConexaoBD {
    
    static String driverJDBC = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/locadora?useTimezone=true&serverTimezone=UTC";
    static String user = "root";
    static String senha = "";
    
    public static Connection createConnectionToMySQL() throws Exception{ 
        //Conexao com o BD
        
         Class.forName(driverJDBC);
         Connection connection = DriverManager.getConnection(url, user, senha);
         return connection;     
    }
    
    
}
