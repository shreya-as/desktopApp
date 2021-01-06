/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sales.controller;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import org.sales.model.Table;
import org.sales.util.DBConnection;

/**
 *
 * @author fox
 */
public class SalesDAO {
             public void saveData(List<Table> list){
        Connection con = null;
        try{
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            for(int i = 0; i < list.size(); i++){
                String sql = "insert into sales (pid, sid, qty, dos) values (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, list.get(i).getPid());
                pst.setInt(2, list.get(i).getSid());
                pst.setInt(3, list.get(i).getQty());
                pst.setDate(4, new java.sql.Date(list.get(i).getDate().getTime()));
                pst.execute();
                ProductDAO pdc = new ProductDAO();
                int qty = pdc.getQty(list.get(i).getPid(), con);
                int newQty = qty - list.get(i).getQty();
                pdc.updateQty(list.get(i).getPid(),newQty, con);
                con.commit();
            }
        }catch(Exception ex){
            System.out.println(ex);
            try{
                con.rollback();
            }catch(Exception e){
                System.out.println(e);
            }
        }
        finally{
            try{
                con.close();
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
    }
}
           