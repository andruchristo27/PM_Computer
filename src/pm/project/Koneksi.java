/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class Koneksi {
   
   public static void main(String[] args) {
        // TODO code application logic here

        Koneksi tesDatabase = new Koneksi();
        tesDatabase.koneksi();
    }
    String status = "Tidak Terhubung";
    Connection con = null;

    public void koneksi() {
        try {
            String connectionURL = "jdbc:mysql://localhost/pmproject";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionURL, username, password);

            status = "Terhubung";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Terhubung");
            e.printStackTrace();

        }
    }


   
    
}
 

