/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.primefaces.demo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Daniel
 */
public class ConnectionFactory {
     public static Connection getConnection() throws Exception {    
        String username = "root";
        String password = "root";
         Class.forName("com.mysql.jdbc.Driver");
        return (Connection)DriverManager.getConnection("jdbc:mysql://localhost/bookdb", username, password);
    }
}
