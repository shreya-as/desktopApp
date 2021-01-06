/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sales.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.sales.model.Login;
import org.sales.util.DBConnection;

/**
 *
 * @author fox
 */
public class LoginDAO {
    public int verifyUser(Login ob){
    
   

    
 int count = 0;
        try{
            Connection con = DBConnection.getConnection();
            String sql = "select * from login where username = ? and password = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, ob.getUsername());
            st.setString(2, ob.getPassword());
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                count = rs.getInt("id");
                con.close();
                return count; 
            }
            //con.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
}

