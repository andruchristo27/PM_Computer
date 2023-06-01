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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class EditDatabarangLaptop extends javax.swing.JFrame {

    /**
     * Creates new form EditDatabarangLaptop
     */
    public EditDatabarangLaptop() {
        initComponents();
          Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
          int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
   setShape(new RoundRectangle2D.Double(0,0, getWidth(),getHeight(),27,27)); 
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tb_barang INNER JOIN tb_detail_barang ON tb_barang.id_barang = tb_detail_barang.id_detail_barang WHERE tb_barang.id_barang='" + Dashboard.selectedbarangID + "'");
                        while (rs.next()) {
                txt_kodebarang.setText(rs.getString("id_barang"));
                txt_jenis.setText(rs.getString("nama_jenis"));
                txt_namabarang.setText(rs.getString("nama_barang"));
                txt_processor.setText(rs.getString("processor"));
                txt_GPU.setText(rs.getString("GPU"));
                txt_RAM.setText(String.valueOf(rs.getInt("RAM")));
                txt_warna.setText(rs.getString("Warna"));
                txt_jumlah.setText(String.valueOf(rs.getInt("kuantitas")));
                txt_hargabeli.setText(String.valueOf(rs.getInt("harga_beli")));
                txt_hargajual.setText(String.valueOf(rs.getInt("harga_jual")));
            }

        } catch (Exception ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
       
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_kodebarang = new javax.swing.JTextField();
        txt_jenis = new javax.swing.JTextField();
        txt_namabarang = new javax.swing.JTextField();
        txt_processor = new javax.swing.JTextField();
        txt_GPU = new javax.swing.JTextField();
        txt_RAM = new javax.swing.JTextField();
        txt_warna = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        txt_hargajual = new javax.swing.JTextField();
        txt_hargabeli = new javax.swing.JTextField();
        Selesai = new javax.swing.JLabel();
        Kembalilaptop = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_kodebarang.setBackground(new java.awt.Color(236, 239, 254));
        txt_kodebarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_kodebarang.setBorder(null);
        txt_kodebarang.setEnabled(false);
        getContentPane().add(txt_kodebarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 68, 228, 31));

        txt_jenis.setEditable(false);
        txt_jenis.setBackground(new java.awt.Color(236, 239, 254));
        txt_jenis.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_jenis.setText("   Laptop");
        txt_jenis.setBorder(null);
        txt_jenis.setEnabled(false);
        txt_jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jenisActionPerformed(evt);
            }
        });
        getContentPane().add(txt_jenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 114, 228, 31));

        txt_namabarang.setBackground(new java.awt.Color(236, 239, 254));
        txt_namabarang.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_namabarang.setBorder(null);
        txt_namabarang.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txt_namabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 161, 212, 31));

        txt_processor.setBackground(new java.awt.Color(236, 239, 254));
        txt_processor.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_processor.setBorder(null);
        getContentPane().add(txt_processor, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 208, 212, 31));

        txt_GPU.setBackground(new java.awt.Color(236, 239, 254));
        txt_GPU.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_GPU.setBorder(null);
        getContentPane().add(txt_GPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 257, 212, 31));

        txt_RAM.setBackground(new java.awt.Color(236, 239, 254));
        txt_RAM.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_RAM.setBorder(null);
        getContentPane().add(txt_RAM, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 309, 212, 31));

        txt_warna.setBackground(new java.awt.Color(236, 239, 254));
        txt_warna.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_warna.setBorder(null);
        getContentPane().add(txt_warna, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 354, 212, 31));

        txt_jumlah.setBackground(new java.awt.Color(236, 239, 254));
        txt_jumlah.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_jumlah.setBorder(null);
        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });
        getContentPane().add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 404, 212, 31));

        txt_hargajual.setBackground(new java.awt.Color(236, 239, 254));
        txt_hargajual.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_hargajual.setBorder(null);
        getContentPane().add(txt_hargajual, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 453, 212, 31));

        txt_hargabeli.setBackground(new java.awt.Color(236, 239, 254));
        txt_hargabeli.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_hargabeli.setBorder(null);
        txt_hargabeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargabeliActionPerformed(evt);
            }
        });
        getContentPane().add(txt_hargabeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 498, 212, 31));

        Selesai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Selesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelesaiMouseClicked(evt);
            }
        });
        getContentPane().add(Selesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 540, 64, 23));

        Kembalilaptop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Kembalilaptop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembalilaptopMouseClicked(evt);
            }
        });
        getContentPane().add(Kembalilaptop, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 542, 65, 20));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/input data barng laptop (2).png"))); // NOI18N
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SelesaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelesaiMouseClicked
        // TODO add your handling code here:
       try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            String kode = txt_kodebarang.getText();
            String nama=txt_namabarang.getText();
            String processor =txt_processor.getText();
            String GPU =txt_GPU.getText();
            String ram =txt_RAM.getText();
            String warna =txt_warna.getText();
            //String jumlah =txt_jumlah.getText();
            String beli =txt_hargabeli.getText();
            String jual =txt_hargajual.getText();
            
            try{
                long jumlah = Long.parseLong(txt_jumlah.getText());
                
                //java.sql.Statement  = conn.createStatement();
                PreparedStatement ps = con.prepareStatement("UPDATE tb_barang SET id_barang ='"+kode+"', nama_jenis = 'Laptop', nama_barang = '"+nama+"', kuantitas ='"+jumlah+"', harga_beli = '"+beli+"' , harga_jual = '"+jual+"'WHERE id_barang = '"+kode+"'");
                ps.execute();
                PreparedStatement ps1 = con.prepareStatement("UPDATE tb_detail_barang SET id_detail_barang ='"+kode+"', processor = '"+processor+"', GPU = '"+GPU+"', RAM = '"+ram+"', Warna = '"+warna+"' WHERE id_detail_barang = '"+kode+"'");
                ps1.execute();
                JOptionPane.showMessageDialog(null,"Data barang berhasil diedit");
                Dashboard.loadDataLaptop();
                dispose();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this, "Masukkan data dengan benar");
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        

    }//GEN-LAST:event_SelesaiMouseClicked

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void txt_jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jenisActionPerformed

    private void txt_hargabeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargabeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargabeliActionPerformed

    private void KembalilaptopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembalilaptopMouseClicked
        // TODO add your handling code here:
     dispose();
    }//GEN-LAST:event_KembalilaptopMouseClicked

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
            java.util.logging.Logger.getLogger(EditDatabarangLaptop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditDatabarangLaptop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditDatabarangLaptop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditDatabarangLaptop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditDatabarangLaptop().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Kembalilaptop;
    private javax.swing.JLabel Selesai;
    private javax.swing.JTextField txt_GPU;
    private javax.swing.JTextField txt_RAM;
    private javax.swing.JTextField txt_hargabeli;
    private javax.swing.JTextField txt_hargajual;
    private javax.swing.JTextField txt_jenis;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kodebarang;
    private javax.swing.JTextField txt_namabarang;
    private javax.swing.JTextField txt_processor;
    private javax.swing.JTextField txt_warna;
    // End of variables declaration//GEN-END:variables
}
