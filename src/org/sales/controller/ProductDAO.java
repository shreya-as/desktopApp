/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sales.controller;

import org.sales.model.Product;
import java.sql.*;
import org.sales.util.DBConnection;
import java.util.*;

/**
 *
 * @author fox
 */
public class ProductDAO {
    public int insertData(Product ob)
//uta bata pass greko obj lai hold grna
            //dher thauma use hune code utility pkcg use garxau
    {
        int count=0;
        //connection ko kaam grne
        try{
            Connection con=DBConnection.getConnection();//to create a connctioin it is easy to call directly
            String sql="insert into product(name,price,qty,remarks)values(?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            //settting values 
            st.setString(1,ob.getName());
            st.setDouble(2,ob.getPrice());
            st.setInt(3,ob.getQty());
            st.setString(4,ob.getRemarks());
          count= st.executeUpdate();//to count kati choti gareko
            con.close();
        }
        catch(Exception ex){
             System.out.println(ex);

        }
        return count;//value return grna paaudaena so int grne void ko sato
    }
    //table ma vako data euta euta grera tanera list ma rkhx

    public List<Product> fetchData(){//void ko thau ma ysko type lekheko
        List<Product> productList=new ArrayList();//import util pckg for this
        
        try{
            Connection con=DBConnection.getConnection();
            String sql="select * from product";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);//rs ma tanneu db hamile rs lai return grdaenau yo rs ma aako value lai model cls ma set garxau
            Product ob=new Product();//setter ra gertr ma store garxau n return garna paauxau
            while(rs.next()){
            ob.setId(rs.getInt("id"));
            ob.setName(rs.getString("name"));
            ob.setPrice(rs.getDouble("price"));
            ob.setQty(rs.getInt("qty"));
            ob.setRemarks(rs.getString("remarks"));
            productList.add(ob);
                       }
             con.close();

        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return productList;
//so that other can use
    }
     public int updateData(Product ob)//uta bata pass vako obj lai hold garna lai
    {
        int count=0;
        try{
            Connection con=DBConnection.getConnection();
            
            String sql="update product set name=?,price=?,qty=?,remarks=?where id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,ob.getName());//ob bata aako xa
            st.setDouble(2,ob.getPrice());
            st.setInt(3,ob.getQty());
            st.setString(4,ob.getRemarks());
            st.setInt(5,ob.getId());
           count= st.executeUpdate();//auta row affect vayo vane insert vayo vane bujinxa count ko value 0 vanda badi aayo vane insert vayo
           con.close();
            
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        //return count;//error aauxa bcoz return type void xa  so mathi void ko sato int rakhne 
        return count;
        
    }

    public int deleteData(Product ob) {
        int count=0;
        try{
            Connection con=DBConnection.getConnection();
            String sql="delete from product where id=?";
            
            
            PreparedStatement st=con.prepareStatement(sql);
            
            st.setInt(1,ob.getId());
           count= st.executeUpdate();//auta row affect vayo vane insert vayo vane bujinxa count ko value 0 vanda badi aayo vane insert vayo
           con.close();
            
            
    }
         catch(Exception ex)
        {
            System.out.println(ex);
        }
        //return count;//error aauxa bcoz return type void xa  so mathi void ko sato int rakhne 
        return count;
        
    }

       public Product fetchData(int id) {
        Product ob=new Product();
        try{
            Connection con=DBConnection.getConnection();
            String sql="select * from product where id=? and qty>0";
             PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,id);
           ResultSet rs= st.executeQuery();//auta row affect vayo vane insert vayo vane bujinxa count ko value 0 vanda badi aayo vane insert vayo
          if(rs.next())
          {
              ob.setId(rs.getInt("id"));
                           
              ob.setName(rs.getString("name"));
              ob.setPrice(rs.getDouble("price"));

          }
           
           con.close();
            
            
    }
         catch(Exception ex)
        {
            System.out.println(ex);
        }
        //return count;//error aauxa bcoz return type void xa  so mathi void ko sato int rakhne 
        return ob;
        
    } 
     public int getQty(int id,Connection con){
         int qty=0;
         try{
             String sql="select qty from product where id=?";
             PreparedStatement pst=con.prepareStatement(sql);
             pst.setInt(1, id);
             ResultSet rs=pst.executeQuery();
             while(rs.next()){
                 qty=rs.getInt("qty");
                 
             }
             }catch(Exception ex){
                  System.out.println(ex);
                 
             }
         return qty;
     }   
     public void updateQty(int id,int newQty,Connection con){
         try{
             String sql="update product set qty=? where id=?";
             PreparedStatement pst=con.prepareStatement(sql);
             pst.setInt(1,newQty);
             pst.setInt(2,id);
             pst.execute();
             
         }catch(Exception ex){
              System.out.println(ex);
         }
     }
    
}
