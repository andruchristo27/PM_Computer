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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class EditDataClient extends javax.swing.JFrame {

    /** Creates new form editclient */
    public EditDataClient() {
        initComponents();
          Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
          int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
   setShape(new RoundRectangle2D.Double(0,0, getWidth(),getHeight(),27,27)); 
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tb_client WHERE id_client ='"+Dashboard.selectedClientID+"'");
            while(rs.next()){
                txt_idclient.setText(rs.getString("id_client"));
                txt_namaclient.setText(rs.getString("nama_client"));
                txt_alamat.setText(rs.getString("alamat"));
                txt_nohp.setText(rs.getString("no_hp"));
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
     /*Statement pst;
    ResultSet rs;
    Update update = new Update();

    public void fillEdit(String id) {
        Koneksi konek = new Koneksi();
        konek.koneksi();

        try {
            String sql = "select * from tb_client where id_client like '%" + id + "%'";
            pst = konek.con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                txt_idclient.setText(rs.getString("id_client"));
                txt_namaclient.setText(rs.getString("nama_client"));
                txt_alamat.setText(rs.getString("alamat"));
                txt_nohp.setText(rs.getString("no_hp"));
               
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        Koneksi connect = new Koneksi();
    public void editloadDataClient(String id_client, String nama_client, String alamat, String nohp) {

        try {

            connect.koneksi();
            Statement statement = connect.con.createStatement();

            String sql_nama_client = "update tb_client set nama_client='" + nama_client + "'where id_client='" + id_client + "'";
            String sql_alamat = "update tb_client set alamat='" +alamat  + "'where id_client='" + id_client + "'";
            String sql_nohp = "update tb_client set no_hp='" + nohp + "'where id_client='" + id_client + "'";
            
            statement.executeUpdate(sql_nama_client);
            statement.executeUpdate(sql_alamat);
            statement.executeUpdate(sql_nohp);
            statement.close();

            JOptionPane.showMessageDialog(null, "Berhasil Diubah");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }*/

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_idclient = new javax.swing.JTextField();
        txt_namaclient = new javax.swing.JTextField();
        txt_nohp = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        KembaliInputKlien = new javax.swing.JLabel();
        selesai = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setMaximumSize(new java.awt.Dimension(571, 402));
        jPanel1.setMinimumSize(new java.awt.Dimension(571, 402));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_idclient.setBackground(new java.awt.Color(236, 239, 254));
        txt_idclient.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_idclient.setText("  ");
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
        txt_alamat.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_alamat.setBorder(null);
        txt_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatActionPerformed(evt);
            }
        });
        jPanel1.add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 197, 215, 74));

        KembaliInputKlien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 34 (2).png"))); // NOI18N
        KembaliInputKlien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        KembaliInputKlien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliInputKlienMouseClicked(evt);
            }
        });
        jPanel1.add(KembaliInputKlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        selesai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selesaiMouseClicked(evt);
            }
        });
        jPanel1.add(selesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 70, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Input data klient.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void selesaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selesaiMouseClicked
      try{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
        try{
            long nohpcvt=Long.parseLong(txt_nohp.getText());
            String nohp=txt_nohp.getText();
            String idclient = txt_idclient.getText();
            String nama = txt_namaclient.getText();
            String alamat = txt_alamat.getText();
            PreparedStatement ps = con.prepareStatement("UPDATE tb_client SET nama_client ='"+nama+"', alamat = '"+alamat+"', no_hp='"+nohp+"' WHERE id_client = '"+idclient+"'");
            ps.execute();
            JOptionPane.showMessageDialog(null,"Data client berhasil diedit");
            Dashboard.loadDataClient();
            dispose();
            } 
            catch(Exception e){
                JOptionPane.showMessageDialog(this, "Masukkan data dengan benar");
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selesaiMouseClicked

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
            java.util.logging.Logger.getLogger(EditDataClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditDataClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditDataClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditDataClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditDataClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel KembaliInputKlien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel selesai;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_idclient;
    private javax.swing.JTextField txt_namaclient;
    private javax.swing.JTextField txt_nohp;
    // End of variables declaration//GEN-END:variables

}
