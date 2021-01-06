/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sales.util;
import java.sql.*;

/**
 *
 * @author fox
 */
public class DBConnection {
    //create a fn to connect db
    public static Connection getConnection(){  //we can call directly without creating obj
      final String username="root";//final xa vni constant vo aru user bata acces so nagaros
      final String password="";
      final String url="jdbc:mysql://localhost:3306/salesmgmt";
      try{
         Class.forName("com.mysql.jdbc.Driver");

Class.forName("com.mysql.jdbc.Driver");
          Connection con;
          con = DriverManager.getConnection(url,username,password);
          return con;
          
      }
      catch(ClassNotFoundException | SQLException ex)
      {
          System.out.println(ex);
          return null;
      }
    }
    
}
//table ma avako data euta euta grera tanera list ma rkhx