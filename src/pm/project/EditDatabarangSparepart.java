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
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class EditDatabarangSparepart extends javax.swing.JFrame {

    /**
     * Creates new form EditDatabarangSparepart
     */
    public EditDatabarangSparepart() {
        initComponents();
          Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
          int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
   setShape(new RoundRectangle2D.Double(0,0, getWidth(),getHeight(),27,27)); 
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("select * from tb_barang where id_barang = '" + Dashboard.selectedbarangID + "'");
            
            while (rs.next()) {
                txt_kodebrng.setText(rs.getString("id_barang"));
                //jComboBox1.setSelectedItem((rs.getString("nama_jenis")));
                txt_namabrng.setText(rs.getString("nama_barang"));
                txt_jumlah.setText(String.valueOf(rs.getInt("kuantitas")));
                txt_hargabeli.setText(String.valueOf(rs.getInt("harga_beli")));
                txt_hargajual.setText(String.valueOf(rs.getInt("harga_jual")));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
            }
 

    public void fillEdit(String id) {
        Koneksi konek = new Koneksi();
        konek.koneksi();

    }
    
     /**public void edit( 
        String id_barang,
        String nama_jenis,
        String nama_barang,
        int kuantitas,
        int harga_beli, 
        int harga_jual) {

        try {

            connect.koneksi();
            Statement statement = connect.con.createStatement();

            String sql_nama_jenis= "update tb_barang set nama_jenis='" + nama_jenis + "'where id_barang='" + id_barang + "'";
            String sql_nama_barang = "update tb_barang set nama_barang='" + nama_barang + "'where id_barang='" + id_barang + "'";
            String sql_kuantitas = "update tb_barang set kuantitas='" + kuantitas + "'where id_barang='" + id_barang + "'";
            String sql_harga_beli = "update tb_barang set harga_beli='" + harga_beli + "'where id_barang='" + id_barang + "'";
            String sql_harga_jual = "update tb_barang set harga_jual='" + harga_jual + "'where id_barang='" + id_barang + "'";

            statement.executeUpdate(sql_nama_jenis);
            statement.executeUpdate(sql_nama_barang);
            statement.executeUpdate(sql_kuantitas);
            statement.executeUpdate(sql_harga_beli);
            statement.executeUpdate(sql_harga_jual);
            statement.close();

            JOptionPane.showMessageDialog(null, "Berhasil Diubah");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
        Koneksi connect = new Koneksi();
    public void editloadDataSparepart(String id_barang, String nama_jenis, String nama_barang, String kuantitas, String harga_beli, String harga_jual) {

        try {

            connect.koneksi();
            Statement statement = connect.con.createStatement();

            String sql_nama_jenis = "update tb_barang set nama_jenis='" + nama_jenis + "'where id_barang='" + id_barang + "'";
            String sql_nama_barang = "update tb_barang set nama_barang='" +nama_barang  + "'where id_barang='" + id_barang + "'";
            String sql_kuantitas = "update tb_barang set kuantitas='" + kuantitas + "'where id_barang='" + id_barang + "'";
            String sql_harga_beli = "update tb_barang set harga_beli='" + harga_beli + "'where id_barang='" + id_barang + "'";
            String sql_harga_jual = "update tb_barang set harga_jual='" + harga_jual + "'where id_barang='" + id_barang + "'";

            statement.executeUpdate(sql_nama_jenis);
            statement.executeUpdate(sql_nama_barang);
            statement.executeUpdate(sql_kuantitas);
            statement.executeUpdate(sql_harga_beli);
            statement.executeUpdate(sql_harga_jual);
            statement.close();

            JOptionPane.showMessageDialog(null, "Berhasil Diubah");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
**/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_jenis = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        txt_hargajual = new javax.swing.JTextField();
        txt_hargabeli = new javax.swing.JTextField();
        txt_kodebrng = new javax.swing.JTextField();
        txt_namabrng = new javax.swing.JTextField();
        Selesai = new javax.swing.JLabel();
        KembaliSparePart = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_jenis.setEditable(false);
        txt_jenis.setBackground(new java.awt.Color(236, 239, 254));
        txt_jenis.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_jenis.setText("     Spare part");
        txt_jenis.setBorder(null);
        txt_jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jenisActionPerformed(evt);
            }
        });
        getContentPane().add(txt_jenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 107, 238, 29));

        txt_jumlah.setBackground(new java.awt.Color(236, 239, 254));
        txt_jumlah.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_jumlah.setBorder(null);
        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });
        getContentPane().add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 194, 212, 29));

        txt_hargajual.setBackground(new java.awt.Color(236, 239, 254));
        txt_hargajual.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_hargajual.setBorder(null);
        getContentPane().add(txt_hargajual, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 240, 212, 29));

        txt_hargabeli.setBackground(new java.awt.Color(236, 239, 254));
        txt_hargabeli.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_hargabeli.setBorder(null);
        getContentPane().add(txt_hargabeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 283, 212, 29));

        txt_kodebrng.setEditable(false);
        txt_kodebrng.setBackground(new java.awt.Color(236, 239, 254));
        txt_kodebrng.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_kodebrng.setText("  ");
        txt_kodebrng.setBorder(null);
        txt_kodebrng.setEnabled(false);
        getContentPane().add(txt_kodebrng, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 64, 238, 29));

        txt_namabrng.setBackground(new java.awt.Color(236, 239, 254));
        txt_namabrng.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_namabrng.setBorder(null);
        getContentPane().add(txt_namabrng, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 151, 212, 28));

        Selesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelesaiMouseClicked(evt);
            }
        });
        getContentPane().add(Selesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, 50, 22));

        KembaliSparePart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 34 (2).png"))); // NOI18N
        KembaliSparePart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliSparePartMouseClicked(evt);
            }
        });
        getContentPane().add(KembaliSparePart, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        Background.setBackground(new java.awt.Color(236, 239, 254));
        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/input data barng.png"))); // NOI18N
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SelesaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelesaiMouseClicked
        // TODO add your handling code here:
        
         
      

     
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject", "root", "");
            String kode = txt_kodebrng.getText();
            //String jenis = (String)jComboBox1.getSelectedItem();
            String nama =txt_namabrng.getText();
            //String jumlah = txt_jumlah.getText();
            
            try{
                long jumlah = Long.parseLong(txt_jumlah.getText());
                long jual = Long.parseLong(txt_hargajual.getText());
                long beli = Long.parseLong(txt_hargabeli.getText());
                java.sql.Statement st = conn.createStatement();
                String query = "UPDATE tb_barang SET id_barang ='"+kode+"', nama_jenis = 'Spare Part', nama_barang = '"+nama+"', kuantitas ='"+jumlah+"', harga_beli = '"+beli+"' , harga_jual = '"+jual+"'WHERE id_barang = '"+kode+"'";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Data Berhasil DiSimpan");
                Dashboard.loadDataSparepart();
                dispose();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(this, "Masukkan data dengan benar");
            }

        } catch (SQLException ex) {
            System.out.print("TERJADI KESALAHAN" + ex);
        }
        

    }//GEN-LAST:event_SelesaiMouseClicked

    private void txt_jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jenisActionPerformed

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void KembaliSparePartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliSparePartMouseClicked
        // TODO add your handling code here:
    dispose();
    }//GEN-LAST:event_KembaliSparePartMouseClicked

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
            java.util.logging.Logger.getLogger(EditDatabarangSparepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditDatabarangSparepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditDatabarangSparepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditDatabarangSparepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditDatabarangSparepart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel KembaliSparePart;
    private javax.swing.JLabel Selesai;
    public javax.swing.JTextField txt_hargabeli;
    public javax.swing.JTextField txt_hargajual;
    public javax.swing.JTextField txt_jenis;
    public javax.swing.JTextField txt_jumlah;
    public javax.swing.JTextField txt_kodebrng;
    public javax.swing.JTextField txt_namabrng;
    // End of variables declaration//GEN-END:variables
}
