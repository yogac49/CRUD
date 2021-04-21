/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author YOGA SAPUTRA
 */
public class ConnectionHelper{
private static final String DB_name = "buku";
private static final String USER = "root";
private static final String PASSWORD = "";
private static final String URL = "jdbc:mysql://localhost:3306/"+DB_name;
 
public static Connection getConnection () throws SQLException{
    DriverManager.registerDriver(new Driver());
    Connection connection = DriverManager.getConnection(URL , USER ,PASSWORD);
    return connection;
    }
    
}
