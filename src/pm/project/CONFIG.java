/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class CONFIG {

 
public static Connection mysqlconfig;
public static Connection configDB()throws SQLException{
    try
    {
       String url="jdbc:mysql://localhost:3306/pmproject";
       String user= "root";
       String pass="";
       DriverManager.registerDriver(new com.mysql.jdbc.Driver());
       mysqlconfig=DriverManager.getConnection(url, user, pass);
       
    } catch (Exception e)
    {System.out.println("koneksi gagal "+e.getMessage());
    
    }
    return mysqlconfig;
}
    public static boolean isConnected(){
        try
        {
            CONFIG.configDB();
            System.out.println("Data base terkoneksi");
            return true;
        } catch (Exception e)
        {Logger.getLogger(CONFIG.class.getName()).log(Level.SEVERE, null,e);
            System.out.println("KONEKSI GAGAL");
            return false;
        }
        }

    public static void main(String[] args) {
        System.out.println("cek");
        isConnected();
    }
  
      
}

