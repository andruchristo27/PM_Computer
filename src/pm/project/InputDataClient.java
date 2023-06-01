/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.project;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;
import java.awt.HeadlessException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class InputDataClient extends javax.swing.JFrame {
     public void kodeclient_auto(){
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
               txt_idclient.setText("CLI" +Nol+AN);
           } else {
               txt_idclient.setText("CLI001");
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }}
     public InputDataClient() {
        initComponents();
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
          int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
   setShape(new RoundRectangle2D.Double(0,0, getWidth(),getHeight(),27,27));
   kodeclient_auto();
    }
    /**
     * Creates new form tambahclient
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txt_idclient = new javax.swing.JTextField();
        txt_namaclient = new javax.swing.JTextField();
        txt_nohp = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        selesai = new javax.swing.JLabel();
        KembaliInputKlien = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setMaximumSize(new java.awt.Dimension(571, 402));
        jPanel1.setMinimumSize(new java.awt.Dimension(571, 402));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_idclient.setBackground(new java.awt.Color(236, 239, 254));
        txt_idclient.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_idclient.setBorder(null);
        txt_idclient.setEnabled(false);
        txt_idclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idclientActionPerformed(evt);
            }
        });
        jPanel1.add(txt_idclient, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 63, 238, 29));

        txt_namaclient.setBackground(new java.awt.Color(236, 239, 254));
        txt_namaclient.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_namaclient.setBorder(null);
        txt_namaclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaclientActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namaclient, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 105, 215, 30));

        txt_nohp.setBackground(new java.awt.Color(236, 239, 254));
        txt_nohp.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_nohp.setBorder(null);
        txt_nohp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nohpActionPerformed(evt);
            }
        });
        jPanel1.add(txt_nohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 149, 215, 30));

        txt_alamat.setBackground(new java.awt.Color(236, 239, 254));
        txt_alamat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_alamat.setBorder(null);
        txt_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatActionPerformed(evt);
            }
        });
        jPanel1.add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 197, 215, 74));

        selesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selesaiMouseClicked(evt);
            }
        });
        jPanel1.add(selesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 329, 70, 21));

        KembaliInputKlien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 34 (2).png"))); // NOI18N
        KembaliInputKlien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliInputKlienMouseClicked(evt);
            }
        });
        jPanel1.add(KembaliInputKlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Input data klient.png"))); // NOI18N
        jPanel1.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selesaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selesaiMouseClicked
        // TODO add your handling code here:
       Koneksi connect = new Koneksi();
       connect.koneksi();
try {
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
   try {
        Long.parseLong (txt_nohp.getText());
        String kodeclient = txt_idclient.getText();
        String namaclient = txt_namaclient.getText();
        String alamat = txt_alamat.getText();
        
        java.sql.Statement st = conn.createStatement();
                String query = "INSERT INTO tb_client VALUES('"+kodeclient+"', '" + namaclient + "','"+ alamat + "','"+txt_nohp.getText()+"')";
        st.executeUpdate(query);
        JOptionPane.showMessageDialog(null,"Data Client berhasil ditambahkan");
        kodeclient_auto();
        Dashboard.loadDataClient();
        dispose();
    }  catch(Exception e){
            JOptionPane.showMessageDialog(null,"Masukkan data dengan benar" );
            
    }
}     catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan");
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
}
    
    }//GEN-LAST:event_selesaiMouseClicked

    private void txt_idclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idclientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idclientActionPerformed

    private void txt_namaclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaclientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaclientActionPerformed

    private void txt_nohpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nohpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nohpActionPerformed

    private void txt_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alamatActionPerformed

    private void KembaliInputKlienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliInputKlienMouseClicked
        // TODO add your handling code here:                
        dispose();
    }//GEN-LAST:event_KembaliInputKlienMouseClicked

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
            java.util.logging.Logger.getLogger(InputDataClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputDataClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputDataClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputDataClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputDataClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel KembaliInputKlien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel selesai;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_idclient;
    private javax.swing.JTextField txt_namaclient;
    private javax.swing.JTextField txt_nohp;
    // End of variables declaration//GEN-END:variables
}
