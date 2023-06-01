   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.project;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class InputDataServis extends javax.swing.JFrame {
Koneksi connect = new Koneksi();
public  void idClient_Auto(){
       try {
           Connection Koneksi= DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
           java.sql.Statement stat = Koneksi.createStatement();
           ResultSet rs = stat.executeQuery("SELECT * FROM tb_client WHERE id_client LIKE 'CLI%' order by id_client desc");
           if (rs.next()) {
               String kode=rs.getString("id_client").substring(3);
               String AN = ""+ (Integer.parseInt(kode)+ 1);
               String Nol = "";
               if (AN.length()==1) 
                   {Nol="00";}
               else if(AN.length()==2)
               {Nol="0" ;}
               else if(AN.length()==3)
               {Nol="";}   
               cmb_idKlient.addItem("CLI" +Nol+AN);
               id.setText("CLI" +Nol+AN);
           } else {
               cmb_idKlient.addItem("CLI001");
               id.setText("CLI001");
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }}
 public void tampilCombobox(){
     try {
         String sql= "select * from tb_client";
         java.sql.Connection conn =(Connection)CONFIG.configDB();
         java.sql.PreparedStatement pst=conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery();
         while (rs.next()) {             
             cmb_idKlient.addItem(rs.getString("id_client"));
         }
         rs.last();
         int jumlahdata =rs.getRow();
         rs.first();
     } catch (Exception e) {
     }
 
 }
     public void idServis_auto(){
       try {
           Connection Koneksi= DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
           java.sql.Statement stat = Koneksi.createStatement();
           ResultSet rs = stat.executeQuery("SELECT * FROM tb_service WHERE id_service LIKE 'SR%' order by id_service desc");
           if (rs.next()) {
               String kode=rs.getString("id_service").substring(2);
               String AN = ""+ (Integer.parseInt(kode)+ 1);
               String Nol = "";
               if (AN.length()==1) 
                   {Nol="000";}
               else if(AN.length()==2)
               {Nol="00" ;}
               else if(AN.length()==3)
               {Nol="0";}   
               txt_idservis.setText("SR" +Nol+AN);
           } else {
               txt_idservis.setText("SR0001");
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }}
       int mousepX;
       int mousepY;
    public InputDataServis() {
        initComponents();
         Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;
        this.setLocation(x, y);
   setShape(new RoundRectangle2D.Double(0,0, getWidth(),getHeight(),27,27)); 
        idServis_auto();
        tampilCombobox();
        id.setVisible(false);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_idservis = new javax.swing.JTextField();
        txt_namabarang = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        cmb_idKlient = new javax.swing.JComboBox<>();
        txt_kerusakan = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        nohp = new javax.swing.JTextField();
        alamat = new javax.swing.JTextField();
        opt_clientbaru = new javax.swing.JCheckBox();
        Selesai = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_idservis.setBackground(new java.awt.Color(236, 239, 254));
        txt_idservis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_idservis.setBorder(null);
        txt_idservis.setEnabled(false);
        getContentPane().add(txt_idservis, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 70, 204, 21));

        txt_namabarang.setBackground(new java.awt.Color(236, 239, 254));
        txt_namabarang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_namabarang.setBorder(null);
        getContentPane().add(txt_namabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 108, 204, 21));

        id.setBackground(new java.awt.Color(236, 239, 254));
        id.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        id.setBorder(null);
        getContentPane().add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 182, 204, 22));

        cmb_idKlient.setBackground(new java.awt.Color(236, 239, 254));
        cmb_idKlient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmb_idKlient.setBorder(null);
        cmb_idKlient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_idKlientActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_idKlient, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 182, 204, 21));

        txt_kerusakan.setBackground(new java.awt.Color(236, 239, 254));
        txt_kerusakan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_kerusakan.setBorder(null);
        getContentPane().add(txt_kerusakan, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 145, 204, 21));

        nama.setBackground(new java.awt.Color(236, 239, 254));
        nama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nama.setBorder(null);
        nama.setEnabled(false);
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 242, 204, 22));

        nohp.setBackground(new java.awt.Color(236, 239, 254));
        nohp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nohp.setBorder(null);
        nohp.setEnabled(false);
        nohp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nohpActionPerformed(evt);
            }
        });
        getContentPane().add(nohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 280, 204, 22));

        alamat.setBackground(new java.awt.Color(236, 239, 254));
        alamat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        alamat.setBorder(null);
        alamat.setEnabled(false);
        getContentPane().add(alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 318, 204, 60));

        opt_clientbaru.setBackground(new java.awt.Color(255, 255, 255));
        opt_clientbaru.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        opt_clientbaru.setText("Input Client baru?");
        opt_clientbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt_clientbaruActionPerformed(evt);
            }
        });
        getContentPane().add(opt_clientbaru, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 120, 20));

        Selesai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Selesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelesaiMouseClicked(evt);
            }
        });
        getContentPane().add(Selesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 430, 70, 30));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 70, 20));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Edit Data Service.png"))); // NOI18N
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SelesaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelesaiMouseClicked
                String id_service = txt_idservis.getText();
                String nama_barang = txt_namabarang.getText();
                String id_client = cmb_idKlient.getSelectedItem().toString();
                String keluhan = txt_kerusakan.getText();
                String id_clientbaru = id.getText();
                String namac = nama.getText();
                String nohpc = nohp.getText();
                String alamatc = alamat.getText();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject", "root", "");
            if(opt_clientbaru.isSelected()){
                java.sql.Statement st = conn.createStatement();
                String query = "INSERT INTO tb_client VALUES('" +id_clientbaru + "','" +namac+ "','"+alamatc + "','" +nohpc+"')";
                st.executeUpdate(query);
                String query1 = "INSERT INTO tb_service VALUES('" +id_service + "','" +nama_barang+ "','"+id_clientbaru + "','" +keluhan+"',now(),null,'Antre')";
                st.executeUpdate(query1);
                JOptionPane.showMessageDialog(null, "Data Berhasil DiSimpan");
                idServis_auto();
                tampilCombobox();
                Dashboard.loadDataservis();
                dispose();
            }else{
                try{
                java.sql.Statement st = conn.createStatement();
                String query = "INSERT INTO tb_service VALUES('" +id_service + "','" +nama_barang+ "','"+id_client + "','" +keluhan+"',now(),null,'Antre')";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Data Berhasil DiSimpan");
                idServis_auto();
                tampilCombobox();
                Dashboard.loadDataservis();
                dispose();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"Masukkan data dengan benar" );
            }
            }
        } catch (SQLException ex) {
            System.out.print("TERJADI KESALAHAN" + ex);
        }
    }//GEN-LAST:event_SelesaiMouseClicked

    private void cmb_idKlientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_idKlientActionPerformed
        String id_client = cmb_idKlient.getSelectedItem().toString();
        try {
           Connection Koneksi= DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
           java.sql.Statement stat = Koneksi.createStatement();
           ResultSet rs = stat.executeQuery("SELECT * FROM tb_client WHERE id_client = '"+id_client+"'");
           if (rs.next()) {
               String namac=rs.getString("nama_client");
               String alamatc=rs.getString("alamat");
               String nohpc=rs.getString("no_hp");
               nama.setText(namac);
               nohp.setText(nohpc);
               alamat.setText(alamatc);
           } 
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
    }//GEN-LAST:event_cmb_idKlientActionPerformed

    private void nohpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nohpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nohpActionPerformed

    private void opt_clientbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt_clientbaruActionPerformed
        if(opt_clientbaru.isSelected()) {
        nama.setEnabled(true);
        nohp.setEnabled(true);
        alamat.setEnabled(true);
        nama.setText("");
        nohp.setText("");
        alamat.setText("");
        idClient_Auto();
        id.setVisible(true);
        }else {
            nama.setText("");
            nohp.setText("");
            alamat.setText("");
            id.setText("");
            id.setVisible(false);
            nama.setEnabled(false);
            alamat.setEnabled(false);
            nohp.setEnabled(false);}
    }//GEN-LAST:event_opt_clientbaruActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputDataServis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputDataServis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputDataServis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputDataServis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputDataServis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Selesai;
    private javax.swing.JTextField alamat;
    private javax.swing.JComboBox<String> cmb_idKlient;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nohp;
    private javax.swing.JCheckBox opt_clientbaru;
    private javax.swing.JTextField txt_idservis;
    private javax.swing.JTextField txt_kerusakan;
    private javax.swing.JTextField txt_namabarang;
    // End of variables declaration//GEN-END:variables
}
