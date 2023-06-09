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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class EditDataReturn extends javax.swing.JFrame {

    /**
     * Creates new form EditDataKaryawan
     */
    public EditDataReturn() {
        initComponents();
          Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
          int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
   setShape(new RoundRectangle2D.Double(0,0, getWidth(),getHeight(),27,27)); 
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            int row = Dashboard.TableReturn.getSelectedRow();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tb_return WHERE id_return ='"+Dashboard.TableReturn.getValueAt(row, 0)+"'");
            while(rs.next()){
                txt_idreturn.setText(rs.getString("id_return"));
                txt_idpenjualan.setText(rs.getString("id_penjualan"));
                cmb_status.setSelectedItem(rs.getString("status"));
                txt_nama.setText(rs.getString("nama_barang"));
                txt_keluhan.setText(rs.getString("keluhan"));
                txt_cost.setText(rs.getString("cost"));
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jPanel1 = new javax.swing.JPanel();
        txt_idreturn = new javax.swing.JTextField();
        txt_idpenjualan = new javax.swing.JTextField();
        cmb_status = new javax.swing.JComboBox<>();
        txt_nama = new javax.swing.JTextField();
        txt_keluhan = new javax.swing.JTextField();
        txt_cost = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        popupMenu1.setLabel("popupMenu1");
        popupMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupMenu1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_idreturn.setBackground(new java.awt.Color(236, 239, 254));
        txt_idreturn.setBorder(null);
        txt_idreturn.setEnabled(false);
        jPanel1.add(txt_idreturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 70, 200, 20));

        txt_idpenjualan.setBackground(new java.awt.Color(236, 239, 254));
        txt_idpenjualan.setBorder(null);
        jPanel1.add(txt_idpenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 108, 200, 20));

        cmb_status.setBackground(new java.awt.Color(236, 239, 254));
        cmb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Antre", "Proses", "Selesai", "Tutup" }));
        cmb_status.setBorder(null);
        cmb_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_statusActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 221, 200, 21));

        txt_nama.setBackground(new java.awt.Color(236, 239, 254));
        txt_nama.setBorder(null);
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        jPanel1.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 146, 200, 20));

        txt_keluhan.setBackground(new java.awt.Color(236, 239, 254));
        txt_keluhan.setBorder(null);
        jPanel1.add(txt_keluhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 184, 200, 20));

        txt_cost.setBackground(new java.awt.Color(236, 239, 254));
        txt_cost.setBorder(null);
        jPanel1.add(txt_cost, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 261, 200, 20));

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 80, 30));

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 70, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Edit Return.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_statusActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void popupMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_popupMenu1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            String id = txt_idreturn.getText();
            String nama=txt_nama.getText();
            String status=cmb_status.getSelectedItem().toString();
            int row = Dashboard.TableReturn.getSelectedRow();
            String tanggalmasuk = Dashboard.TableReturn.getValueAt(row, 4).toString();
            try{
                Long.parseLong(txt_cost.getText());
                //String nohp =txt_nama.getText();
                String keluhan=txt_keluhan.getText();
                //String email=txt_email.getText();
                String cost =txt_cost.getText();
                //String password =txt_password.getText();

                PreparedStatement ps = con.prepareStatement("UPDATE tb_return SET nama_barang ='"+nama+"', keluhan = '"+keluhan+"', tgl_masuk = '"+tanggalmasuk+"', tgl_keluar=null, status = '"+status+"' , cost = '"+cost+"'WHERE id_return = '"+id+"'");
                ps.execute();
                JOptionPane.showMessageDialog(null,"Data return berhasil diedit");
                Dashboard.loadDatareturn();
                dispose();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this, "Masukkan Tarif");
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(EditDataReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditDataReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditDataReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditDataReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditDataReturn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTextField txt_cost;
    private javax.swing.JTextField txt_idpenjualan;
    private javax.swing.JTextField txt_idreturn;
    private javax.swing.JTextField txt_keluhan;
    private javax.swing.JTextField txt_nama;
    // End of variables declaration//GEN-END:variables
}
