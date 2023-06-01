/*
 * To change this license header, choose License Headers in Project Properti
 * and open the template in the editor.
 */
 
package pm.project;

import com.mysql.jdbc.Driver;
//import com.mysql.jdbc.Statement;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class EditSparepartBeli extends javax.swing.JFrame {

   
    public EditSparepartBeli() {
        initComponents();
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
   setShape(new RoundRectangle2D.Double(0,0, getWidth(),getHeight(),27,27));  
        try{
       int row = Dashboard.tb_pembeliansparepart.getSelectedRow();
       txt_kodebrng.setText(Dashboard.tb_pembeliansparepart.getValueAt(row, 1).toString());
       txt_namabrng.setText(Dashboard.tb_pembeliansparepart.getValueAt(row,2).toString());
       txt_jumlah.setText(Dashboard.tb_pembeliansparepart.getValueAt(row, 3).toString());
       txt_hargabeli.setText(Dashboard.tb_pembeliansparepart.getValueAt(row, 4).toString());
       //txt_hargajual.setText(Dashboard.tb_pembeliansparepart.getValueAt(row, 8).toString());
   }catch(Exception e)
   {}
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_kodebrng = new javax.swing.JTextField();
        txt_nama_jenis = new javax.swing.JTextField();
        txt_namabrng = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        txt_hargabeli = new javax.swing.JTextField();
        Selesai = new javax.swing.JLabel();
        Kembali = new javax.swing.JLabel();
        txt_kodetrs = new javax.swing.JTextField();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_kodebrng.setEditable(false);
        txt_kodebrng.setBackground(new java.awt.Color(236, 239, 254));
        txt_kodebrng.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_kodebrng.setBorder(null);
        txt_kodebrng.setEnabled(false);
        getContentPane().add(txt_kodebrng, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 70, 200, 20));

        txt_nama_jenis.setBackground(new java.awt.Color(236, 239, 254));
        txt_nama_jenis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nama_jenis.setText("    Spare Part");
        txt_nama_jenis.setBorder(null);
        txt_nama_jenis.setEnabled(false);
        txt_nama_jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nama_jenisActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nama_jenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 107, 200, 20));

        txt_namabrng.setBackground(new java.awt.Color(236, 239, 254));
        txt_namabrng.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_namabrng.setBorder(null);
        getContentPane().add(txt_namabrng, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 146, 200, 20));

        txt_jumlah.setBackground(new java.awt.Color(236, 239, 254));
        txt_jumlah.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_jumlah.setBorder(null);
        getContentPane().add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 185, 200, 20));

        txt_hargabeli.setBackground(new java.awt.Color(236, 239, 254));
        txt_hargabeli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_hargabeli.setBorder(null);
        getContentPane().add(txt_hargabeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 221, 200, 20));

        Selesai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Selesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelesaiMouseClicked(evt);
            }
        });
        getContentPane().add(Selesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 80, 22));

        Kembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 34 (2).png"))); // NOI18N
        Kembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Kembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliMouseClicked(evt);
            }
        });
        getContentPane().add(Kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 291, 70, -1));

        txt_kodetrs.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_kodetrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodetrsActionPerformed(evt);
            }
        });
        getContentPane().add(txt_kodetrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 50, 10));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/belisparepart.png"))); // NOI18N
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SelesaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelesaiMouseClicked
        try {
            int row = Dashboard.tb_pembeliansparepart.getSelectedRow();
            Dashboard.tb_pembeliansparepart.setValueAt(txt_kodebrng.getText(), row, 1);
            Dashboard.tb_pembeliansparepart.setValueAt(txt_namabrng.getText(), row, 2);
            Dashboard.tb_pembeliansparepart.setValueAt(txt_jumlah.getText(), row, 3);
            Dashboard.tb_pembeliansparepart.setValueAt(txt_hargabeli.getText(), row, 4);
            
            Dashboard._settotalbelis();
            dispose();
        } catch (Exception ex) {
                System.out.print("TERJADI KESALAHAN" + ex);
            }
    }//GEN-LAST:event_SelesaiMouseClicked

    private void txt_nama_jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nama_jenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_jenisActionPerformed

    private void KembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliMouseClicked
        // TODO add your handling code here:
     dispose();
    }//GEN-LAST:event_KembaliMouseClicked

    private void txt_kodetrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodetrsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kodetrsActionPerformed
    
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
            java.util.logging.Logger.getLogger(EditSparepartBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditSparepartBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditSparepartBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditSparepartBeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new EditSparepartBeli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Kembali;
    private javax.swing.JLabel Selesai;
    public javax.swing.JTextField txt_hargabeli;
    public javax.swing.JTextField txt_jumlah;
    public javax.swing.JTextField txt_kodebrng;
    private javax.swing.JTextField txt_kodetrs;
    public javax.swing.JTextField txt_nama_jenis;
    public javax.swing.JTextField txt_namabrng;
    // End of variables declaration//GEN-END:variables
}