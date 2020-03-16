/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Djemaiel
 */
public class ConnexionBD {
       private static String url= "jdbc:mysql://localhost:3306/agora";
    private static String usr="root";
    private static String pwd="";
    private static ConnexionBD cbd;
    private static Connection cnx;
    
     public static Connection getCnx() {
        return cnx;
    }
    private ConnexionBD() {
          try {
              // TODO code application logic here

               cnx = DriverManager.getConnection(url, usr, pwd);
                   System.out.println("connexion etabli!");
          } catch (SQLException ex) {
              System.out.println("erreur"+ ex.getMessage());
          }
    }
public static ConnexionBD getinstance(){
    if (cbd==null)
            cbd =new ConnexionBD();
        return cbd;
}
   
}
