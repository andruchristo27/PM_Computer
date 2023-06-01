package pm.project;

import com.raven.chart.ModelChart;
import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.collections.map.HashedMap;

public class Dashboard extends javax.swing.JFrame {
    static DefaultTableModel model;
    static DefaultTableModel modelpb;
    static DefaultTableModel modelpbs;
    static DefaultTableModel modelcb;//clientbeli
    static DefaultTableModel modeltrs;
    static DefaultTableModel modeltrsj;
    int mousepX;
    int mousepY;
    static int total_beli;
    static int total_belis;
    static String selectedKaryawanID;
    static String selectedClientID;
    static String selectedJasaID;
    static String selectedbarangID;
    static String selectedjenisbrgID;
    static String selectedServisID;
    static String selectedReturnID;
    static ArrayList<String> idlist = new ArrayList<String>();
    static ArrayList<String> idlist1 = new ArrayList<String>();
      //Menampilkan data dari database tb_barang dan tb_detail_barang ke tabel Laptop
    public static void loadDataLaptop(){
        String [] judul = {"Id","Jenis","Nama","Processor","GPU","RAM","Warna","Jumlah","Harga jual", "Harga beli"};
        model = new DefaultTableModel(judul,0);
        jTableLaptop.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery(" SELECT tb_barang.id_barang,tb_barang.nama_jenis,tb_barang.nama_barang,"
                    + "tb_detail_barang.processor,tb_detail_barang.GPU,tb_detail_barang.RAM,tb_detail_barang.Warna, tb_barang.kuantitas,"
                    + "tb_barang.harga_jual,tb_barang.harga_beli\n" +
            "FROM tb_barang INNER JOIN tb_detail_barang ON tb_barang.id_barang=tb_detail_barang.id_detail_barang;");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)
                ,rs.getString(8),rs.getString(9),rs.getString(10)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }}
       //Menampilkan data dari database tb_barang ke tabel SparePart
    public static void loadDataSparepart(){
       String [] judul = {"Id ","Jenis","Nama","Jumlah","Harga jual", "Harga beli"};
        model = new DefaultTableModel(judul,0);
        jTableSparePart.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * from tb_barang where id_barang LIKE '%BRS%'");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       //Menampilkan data dari database tb_karyawan ke tabel karyawan
    public static void loadDataKaryawan() {
        String [] judul = {"Id","Nama","Jenis Kelamin","Tanggal Lahir","No HP","Alamat","Email"};
        model = new DefaultTableModel(judul,0);
        tabelKaryawan.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tb_karyawan");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(7),rs.getString(4),rs.getString(5),rs.getString(6)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       //Menampilkan data dari database tb_karyawan ke tabel klien
    public static void loadDataClient() {
        String [] judul = {"Id","Nama Klien","Alamat","No. HP",};
        model = new DefaultTableModel(judul,0);
        tabelklien.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * from tb_client");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       //Menampilkan data dari database tb_karyawan ke tabel servis
    public static void loadDataservis() {
        String [] judul = {"Id","Nama klien","Nama barang","Keluhan","Tanggal mulai","Tanggal selesai","Status"};
        model = new DefaultTableModel(judul,0);
        TableServis.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT tb_service.id_service,tb_client.nama_client,tb_service.nama_barang,tb_service.keluhan,tb_service.tgl_mulai,tb_service.tgl_selesai,\n" +
"tb_service.status FROM tb_service INNER JOIN tb_client ON tb_service.id_client=tb_client.id_client where tb_service.status != 'Tutup' ;");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       //Menampilkan data dari database tb_penjualan,tb_detail_penjualan,dan tb_barang ke tabel Laporan Transaksi Penjualan
    public static void loadLaporanTransaksiPenjualan() {
        String [] judul = {"Id ","Nama","Jumlah","Harga","Subtotal","Garansi","Total","Bayar","Kembalian"};
        model = new DefaultTableModel(judul,0);
        tabelLaporanpenjualan.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT tb_penjualan.id_penjualan,tb_barang.nama_jenis,tb_detail_penjualan.jumlah,\n" +
"                     tb_barang.harga_jual,tb_detail_penjualan.subtotal,tb_penjualan.garansi,tb_penjualan.tgl_penjualan,tb_penjualan.total_harga, tb_penjualan.Bayar, \n" +
"                    tb_penjualan.Kembalian FROM tb_penjualan JOIN tb_detail_penjualan ON tb_penjualan.id_penjualan=tb_detail_penjualan.id_penjualan \n" +
"                    JOIN tb_barang ON tb_detail_penjualan.id_barang=tb_barang.id_barang;");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       //Menampilkan data dari database tb_servis,tb_trs_servis,dan tb_client ke tabel Laporan Transaksi servis
    public static void loadLaporanTransaksiServis() {
        String [] judul = {"Id Servis","Id Klien","Nama Barang","Keluhan","Status","Tanggal Mulai","Tanggal Selesai","Harga","Bayar","Kembalian"};
        model = new DefaultTableModel(judul,0);
        tabelLaporanTRpembelian.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT tb_service.`id_service`,tb_client.`nama_client`,tb_service.`nama_barang`,"
                    + "tb_service.`keluhan`,tb_service.status, tb_service.`tgl_mulai`,tb_service.`tgl_selesai`,tb_trs_service.harga_total,tb_trs_service.bayar,tb_trs_service.kembalian FROM tb_service "
                    + "JOIN tb_trs_service ON tb_service.id_service=tb_trs_service.id_service JOIN tb_client ON tb_service.id_service=tb_client.id_client;");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       //Menampilkan data dari database tb_pembelian,tb_detail_pembelian,tb_barang,dan tb_client ke tabel Laporan Transaksi Pembelian
    public static void loadLaporanTransaksiPembelian() {
        String [] judul = {"Id","Nama Jenis","Nama Klien","Jumlah","Harga","Subtotal","Total"};
        model = new DefaultTableModel(judul,0);
        tabelLaporanServis.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT tb_pembelian.`id_pembelian`,tb_barang.nama_jenis,tb_client.nama_client,tb_detail_pembelian.jumlah,\n" +
            "tb_barang.harga_beli,tb_detail_pembelian.subtotal,tb_pembelian.total  \n" +
            "FROM tb_pembelian JOIN tb_detail_pembelian ON tb_pembelian.id_pembelian=tb_detail_pembelian.id_pembelian \n" +
            "JOIN tb_barang ON tb_detail_pembelian.id_barang=tb_barang.id_barang JOIN tb_client ON tb_pembelian.`id_client`=tb_client.id_client;");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getString(7)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       //Menampilkan data dari database tb_return dengan kondisi selain tutup ke tabel return
    public static void loadDatareturn() {
        String [] judul = {"ID Return","ID Transaksi","Nama Barang","Keluhan","Tgl Masuk","Status","Cost"};
        model = new DefaultTableModel(judul,0);
        TableReturn.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * From tb_return where status != 'Tutup';");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(7),rs.getString(8)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       //Menampilkan data dari database tb_barang dan tb_detail_barang dengan jenis laptop ke tabel Laporan barang laptop 
    public static void loadLaporanDataLaptop(){
        String [] judul = {"ID","Nama jenis","Nama barang","Processor","GPU","RAM","Warna","Jumlah","Harga jual", "Harga beli"};
        model = new DefaultTableModel(judul,0);
        tb_laporanlaptop.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery(" SELECT tb_barang.id_barang,tb_barang.nama_jenis,tb_barang.nama_barang,"
                    + "tb_detail_barang.processor,tb_detail_barang.GPU,tb_detail_barang.RAM,tb_detail_barang.Warna, tb_barang.kuantitas,"
                    + "tb_barang.harga_jual,tb_barang.harga_beli\n" +
            "FROM tb_barang INNER JOIN tb_detail_barang ON tb_barang.id_barang=tb_detail_barang.id_detail_barang where kuantitas > 0;");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)
                ,rs.getString(8),rs.getString(9),rs.getString(10)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }}
      //Menampilkan data dari database tb_barang dan tb_detail_barang dengan jenis sparepart ke tabel Laporan barang spare part 
    public static void loadLaporanDataSparepart(){
       String [] judul = {"ID","Nama jenis","Nama barang","Jumlah","Harga jual", "Harga beli"};
        model = new DefaultTableModel(judul,0);
        tb_laporansparepart.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * from tb_barang where id_barang LIKE '%BRS%' and kuantitas > 0");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     //Menampilkan data dari database tb_penjualan dengan menjumlahkan semua data dari tb_penjualan ke jlabel jumlah penjualan  
    public static void jumlahpenjualan(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(id_penjualan)AS Total FROM tb_penjualan;");
            rs.next();
            String jumlah=rs.getString("Total");    
            JumlahPenjualan.setText(jumlah);
        } catch (Exception e) {
        }}
     //Menampilkan data dari database tb_pembelian dengan menjumlahkan semua data dari tb_pembelian ke jlabel jumlah pembelian                
    public static void jumlahpembelian(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(id_pembelian)AS Total FROM tb_pembelian;");
            rs.next();
            String jumlah=rs.getString("Total");    
            JumlahPembelian.setText(jumlah);
        } catch (Exception e) {
        }}
     //Menampilkan data dari database tb_trs_servis dengan menjumlahkan semua data dari tb_trs_servis ke jlabel jumlah servis              
    public static void jumlahservis(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(id_transaksi_service)AS Total FROM tb_trs_service;");
            rs.next();
            String jumlah=rs.getString("Total");    
            JumlahServis.setText(jumlah);
        } catch (Exception e) {
        }}
    //method untuk header tabel pembelian laptop           
    public static void tampilkanpembelian() {
        String[] judul = {"Kode Transaksi", "Kode", "Nama Barang", "Processor", "GPU", "RAM", "Warna", "Jumlah", "Harga Beli", "Harga Jual"};
        modelpb = new DefaultTableModel(judul, 0);
        tb_pembelianlaptop.setModel(modelpb);
        ttbeli.setModel(modelpb);
    }
    //method untuk header tabel pembelian laptop
    public static void tampilkanpembeliansparepart() {
        String[] judul = {"Kode Transaksi", "Kode Barang", "Nama Barang", "Jumlah", "Harga"};
        modelpbs = new DefaultTableModel(judul, 0);
        tb_pembeliansparepart.setModel(modelpbs);
    }
    //method untuk header tabel pilih client beli laptop
    public static void pilihclientbeli() {
        String[] judul = {"Id", "Nama", "nohp", "Alamat"};
        modelcb = new DefaultTableModel(judul, 0);
        tb_pilihclientbelilaptop.setModel(modelcb);
    }
    //method untuk header tabel pilih client beli sparepart
    public static void pilihclientbelisparepart() {
        String[] judul = {"Id", "Nama", "nohp", "Alamat"};
        modelcb = new DefaultTableModel(judul, 0);
        tb_pilihclientbelisparepart.setModel(modelcb);
    }
    //membuat method autonumber id untuk  transaksi penjualan 
    public static void idTransaksi_auto(){
       try {
           Connection Koneksi= DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
           java.sql.Statement stat = Koneksi.createStatement();
           ResultSet rs = stat.executeQuery("SELECT * FROM tb_penjualan WHERE id_penjualan LIKE 'TR%' order by id_penjualan desc");
           if (rs.next()) {
               String kode=rs.getString("id_penjualan").substring(2);
               String AN = ""+ (Integer.parseInt(kode)+ 1);
               String Nol = "";
               if (AN.length()==1) 
                   {Nol="000";}
               else if(AN.length()==2)
               {Nol="00" ;}
               else if(AN.length()==3)
               {Nol="0";}   
               txt_idpenjualan.setText("TR" +Nol+AN);
           } else {
               txt_idpenjualan.setText("TR0001");
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }}
    //membuat method autonumber id untuk cetak transaksi penjualan
    public static void idTransaksi_autoCETAK(){
       try {
           Connection Koneksi= DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
           java.sql.Statement stat = Koneksi.createStatement();
           ResultSet rs = stat.executeQuery("SELECT * FROM tb_penjualan WHERE id_penjualan LIKE 'TR%' order by id_penjualan desc");
           if (rs.next()) {
               String kode=rs.getString("id_penjualan").substring(2);
               String AN = ""+ (Integer.parseInt(kode)+ 1);
               String Nol = "";
               if (AN.length()==1) 
                   {Nol="000";}
               else if(AN.length()==2)
               {Nol="00" ;}
               else if(AN.length()==3)
               {Nol="0";}   
               txtCetak.setText("TR" +Nol+AN);
           } else {
               txtCetak.setText("TR0001");
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }}
    //method untuk memasukkan data barang yang telah dipilih ke tabel trs penjualan
    public static void loadData(){
        DefaultTableModel model = (DefaultTableModel) tabelTransaksi.getModel();
        model.addRow(new Object[]{
             txt_idpenjualan.getText(),
            txt_id_barang.getText(),
            txt_namabarang.getText(),
            txt_jumlah.getText(),
            txt_harga.getText(),
            txt_total.getText()
        });
    }
    //method untuk set tabel transaksi penjualan menjadi kosong setelah checkout  
    public void kosong(){
        DefaultTableModel model = (DefaultTableModel) tabelTransaksi.getModel();
        
        while (model.getRowCount()>0) {
            model.removeRow(0);
        }
    }
    //method untuk set Textfield transaksi penjualan menjadi kosong setelah checkout  
    public void utama(){
        txt_idpenjualan.setText("");
        txt_id_barang.setText("");
        txt_namabarang.setText("");
        txt_harga.setText("");
        txt_garansi.setText("");
        
    }
    //method untuk subtotal transaksi penjualan
    public static void totalBiaya(){
        int jumlahBaris = tabelTransaksi.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(tabelTransaksi.getValueAt(i, 3).toString());
            hargaBarang = Integer.parseInt(tabelTransaksi.getValueAt(i, 4).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        txt_total.setText(String.valueOf(totalBiaya));
    }
    //method untuk set Textfield transaksi penjualan menjadi kosong setelah checkout  
    public static void clear(){
          
        txt_total.setText("0");
        txt_bayar.setText("");
        txt_kembalian.setText("");
    }
    //method untuk set Textfield total transaksi penjualan menjadi kosong setelah checkout  
    public static void clear2(){
        txt_id_barang.setText("");
        txt_namabarang.setText("");
        txt_harga.setText("");
        txt_jumlah.setText("");
    }
    //method untuk set total transaksi penjualan
    public static void tambahTransaksi(){
        int jumlah, harga, total;
        jumlah = Integer.valueOf(txt_garansi.getText());
        harga = Integer.valueOf(txt_harga.getText());
        total = jumlah * harga;
        txt_total.setText(String.valueOf(total));
        loadData();
        totalBiaya();
        clear();
        txt_id_barang.requestFocus();
    }
    //method untuk set total transaksi pembelian laptop
    public static void _settotalbeli() {
        int row = tb_pembelianlaptop.getRowCount();
        int totalbeli = 0;
        for (int i = 0; i < row; i++) {
            int harga_barang = Integer.parseInt(tb_pembelianlaptop.getValueAt(i, 8).toString());
            int jumlah = Integer.parseInt(tb_pembelianlaptop.getValueAt(i, 7).toString());
            totalbeli = totalbeli + (harga_barang * jumlah);
        }
        total_beli = totalbeli;
        txt_totalbeli.setText(String.valueOf(total_beli));
        
    }
    //method untuk set total transaksi pembelian spare part
    public static void _settotalbelis() {
        int row = tb_pembeliansparepart.getRowCount();
        int totalbeli = 0;
        for (int i = 0; i < row; i++) {
            int harga_barang = Integer.parseInt(tb_pembeliansparepart.getValueAt(i, 4).toString());
            int jumlah = Integer.parseInt(tb_pembeliansparepart.getValueAt(i, 3).toString());
            totalbeli = totalbeli + (harga_barang * jumlah);
        }
        total_belis = totalbeli;
        txt_totalbeli1.setText(String.valueOf(total_belis));
        
    }
    //method untuk mereset total transaksi ke 0 setelah checkout
    public void reset(){
        this.total_beli = 0;
        this.total_belis = 0;
        txt_totalbeli.setText("");
        txt_totalbeli1.setText("");
        modelpb.setRowCount(0);
        modelpbs.setRowCount(0);
        modelcb.setRowCount(0);
    }
    //method cari tambah placeholder
    public void addplaceholderStyle(JTextField textField){
   Font font = textField.getFont();
   font =font.deriveFont(Font.ITALIC);
   textField.setFont(font);
   textField.setForeground(Color.GRAY);
   }
    //method cari hapus placeholder
    public void removeplaceholderStyle(JTextField textField){
      Font font = textField.getFont();
      font =font.deriveFont(Font.PLAIN|font.PLAIN);
      textField.setFont(font);
      textField.setForeground(Color.BLACK);};
    //method untuk menampilkan chart grafik
    public void LoadDataChart() {
        chart.addLegend("Pemasukan", new Color(245, 189, 135));
        chart.addLegend("Pengeluaran", new Color(135, 189, 245));
         for (int i = 1; i <=12; i++) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-");
            LocalDateTime tanggal = LocalDateTime.now();
            String a = (dtf.format(tanggal));
            int j = 0;
            int q = 0;
             try {
                String sql =  "SELECT SUM(total)AS Total_transaksibeli FROM `tb_pembelian`"
                        + " WHERE tgl_pembelian BETWEEN '"+a+""+i+"-01' and '"+a+""+i+"-31' ;";
                java.sql.Connection conn = (Connection) CONFIG.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                rs.next();
                q = rs.getInt("Total_transaksibeli");
             } catch (SQLException e) {
                 e.printStackTrace();
             }
            try {
                String sql = "SELECT SUM(total_harga)AS Total_transaksijual FROM `tb_penjualan`"
                        + " WHERE tgl_penjualan BETWEEN '"+a+""+i+"-01' and '"+a+""+i+"-31' ;";
                java.sql.Connection conn = (Connection) CONFIG.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
                rs.next();
                j = rs.getInt("Total_transaksijual");
              

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
           chart.addData(new ModelChart(""+i+"", new double[]{q,j}));
        }
        chart.start();
      }
    public static void loadDatatransaksiservice(){
         String [] judul = {"Id Service","Nama klien","Nama barang","Keluhan"};
        modeltrs = new DefaultTableModel(judul,0);
        tabeltransaksiservice.setModel(modeltrs);
    }
    public static void loadDatatransaksiservicejasa(){
         String [] judul = {"Nama Jasa","Harga"};
        modeltrsj = new DefaultTableModel(judul,0);
        tabeltransaksiservicejasa.setModel(modeltrsj);
    }
    public static void loadDatajasa() {
        String [] judul = {"Nama Jasa","Harga"};
        model = new DefaultTableModel(judul,0);
        TabelJasa.setModel(model);
    }
    public static void pilihclient() {

        String[] judul = {"Id", "Nama", "nohp", "Alamat"};
        modelcb = new DefaultTableModel(judul, 0);
        tb_pilihclientbelisparepart.setModel(modelcb);
    }
    public static void idTukarTambah_auto(){
       try {
           Connection Koneksi= DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
           java.sql.Statement stat = Koneksi.createStatement();
           ResultSet rs = stat.executeQuery("SELECT * FROM tb_tukar_tambah WHERE id_tukartambah LIKE 'TRT%' order by id_tukartambah desc");
           if (rs.next()) {
               String kode=rs.getString("id_tukartambah").substring(3);
               String AN = ""+ (Integer.parseInt(kode)+ 1);
               String Nol = "";
               if (AN.length()==1) 
                   {Nol="00";}
               else if(AN.length()==2)
               {Nol="0" ;}
               else if(AN.length()==3)
               {Nol="";}   
               txt_idtt.setText("TRT" +Nol+AN);
           } else {
               txt_idtt.setText("TRT001");
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }}
    public static void idTransaksiService_auto(){
       try {
           Connection Koneksi= DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
           java.sql.Statement stat = Koneksi.createStatement();
           ResultSet rs = stat.executeQuery("SELECT * FROM tb_trs_service WHERE id_transaksi_service LIKE 'TRS%' order by id_transaksi_service desc");
           if (rs.next()) {
               String kode=rs.getString("id_transaksi_service").substring(3);
               String AN = ""+ (Integer.parseInt(kode)+ 1);
               String Nol = "";
               if (AN.length()==1) 
                   {Nol="00";}
               else if(AN.length()==2)
               {Nol="0" ;}
               else if(AN.length()==3)
               {Nol="";}   
               txt_idtrservice.setText("TRS" +Nol+AN);
           } else {
               txt_idtrservice.setText("TRS001");
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }}
    public static void totalBiayaService(){
        int jumlahBaris = tabeltransaksiservicejasa.getRowCount();
        int totalBiaya = 0;
        int  hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            //jumlahBarang = Integer.parseInt(tabeltransaksiservice.getValueAt(i, 3).toString());
            hargaBarang = Integer.parseInt(tabeltransaksiservicejasa.getValueAt(i, 1).toString());
            totalBiaya = totalBiaya + hargaBarang;
        }
        txt_total2.setText(String.valueOf(totalBiaya));
    }  
    public static void totalBiayaTT(){
        
        int totalBiaya = 0;
        int penjualan, pembelian;
        
            penjualan = Integer.parseInt(txt_total.getText());
            pembelian = Integer.parseInt(txt_totalbeli.getText());
            if(pembelian != 0){
            totalBiaya = penjualan-pembelian;
        
        txt_totaltt.setText(String.valueOf(totalBiaya));}
    }
    public Dashboard() {
      
        initComponents();
        //Kode untuk membuat tampilam Jframe berada di tengah
         Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;
        this.setLocation(x, y);
        //Kode membuat tampilan Jframe dengan sudut melengkung
        setShape(new RoundRectangle2D.Double(0,0, getWidth(),getHeight(),27,27)); 
        Sparepart1.setVisible(false);        
        Sparepart2.setVisible(false);        
        Sparepart4.setVisible(false);        
        //memanggil Method tampilan tabel SparePart
        loadDataSparepart();
        //memanggil Method tampilan tabel Laptop
        loadDataLaptop();
        //memanggil Method tampilan tabel Karyawan
        loadDataKaryawan();
        //memanggil Method tampilan tabel Klien
        loadDataClient();
        //memanggil Method tampilan tabel Jasa
        loadDatajasa();
        //memanggil Method tampilan tabel Servis
        loadDataservis();
        //memanggil Method tampilan Laporan Transaksi Penjualan      
        loadLaporanTransaksiPenjualan();
        loadLaporanTransaksiPembelian();
        //memanggil Method tampilan Laporan Transaksi Servis      
        loadLaporanTransaksiServis();
        loadLaporanDataLaptop();
        loadLaporanDataSparepart();
        loadDatatransaksiservice();
        loadDatatransaksiservicejasa();
        tampilkanpembelian();
        tampilkanpembeliansparepart();
        pilihclient();  
        idTukarTambah_auto();
        idTransaksiService_auto();
        loadDatareturn();
        LoadDataChart();
        
        
         tabelklien.fixTable(jScrollPanel8);
         tabelKaryawan.fixTable(jScrollPanel4);
         tabelLaporanpenjualan.fixTable(ScrollpanelLaporanpenjualan);
         tabelLaporanTRpembelian.fixTable(ScrollpanelLaporanpembelian);
         tabelLaporanServis.fixTable(ScrollpanelLaporanservis);
         tabelTransaksi.fixTable(jScrollPanel6);
         jTableSparePart.fixTable(jScrollPanel3);
         jTableLaptop.fixTable(jScrollPanel2);
         TableServis.fixTable(jScrollPane1servis);
        
         model = new DefaultTableModel();
        tabelTransaksi.setModel(model);
        ttjual.setModel(model);
        model.addColumn("Kode Transaksi");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Harga");
        model.addColumn("Total");
      
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        txt_tanggalTransaksi.setText(s.format(date));
        txt_tanggalTransaksiservice.setText(s.format(date));
        idTransaksi_auto();
        idTransaksi_autoCETAK();
        txt_total.setText("0");
        txt_bayar.setText("");
        txt_kembalian.setText("0");
        chart.start();
        
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        side = new javax.swing.JPanel();
        Home = new javax.swing.JLabel();
        tabelmaster = new javax.swing.JLabel();
        transaksi = new javax.swing.JLabel();
        servis = new javax.swing.JLabel();
        laporan = new javax.swing.JLabel();
        retur = new javax.swing.JLabel();
        gMenu = new javax.swing.JLabel();
        gdDatamaster = new javax.swing.JLabel();
        gTransaksi = new javax.swing.JLabel();
        gServis = new javax.swing.JLabel();
        gLaporan = new javax.swing.JLabel();
        gRetur = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        backgroundSide = new javax.swing.JLabel();
        Header = new javax.swing.JPanel();
        Time = new javax.swing.JLabel();
        main = new javax.swing.JPanel();
        home = new javax.swing.JPanel();
        chart = new com.raven.chart.Chart();
        jLabel11 = new javax.swing.JLabel();
        DataMaster = new javax.swing.JPanel();
        DataKlien = new javax.swing.JLabel();
        DataKaryawan = new javax.swing.JLabel();
        DataBarang = new javax.swing.JLabel();
        backgroundMaster = new javax.swing.JLabel();
        Transaksi = new javax.swing.JPanel();
        ButtonTrPenjualan = new javax.swing.JLabel();
        ButtonTrServis = new javax.swing.JLabel();
        ButtonnTrPembelian = new javax.swing.JLabel();
        ButtonnTrPembelian1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Servis = new javax.swing.JPanel();
        Service = new javax.swing.JLabel();
        HapusLaptop1 = new javax.swing.JLabel();
        TambahLaptop1 = new javax.swing.JLabel();
        EditLaptop1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_cariServis = new javax.swing.JTextField();
        Cari = new javax.swing.JLabel();
        jScrollPanel5 = new javax.swing.JScrollPane();
        TabelJasa = new javaswingdev.swing.table.Table();
        jScrollPane1servis = new javax.swing.JScrollPane();
        TableServis = new javaswingdev.swing.table.Table();
        TambahJasa = new javax.swing.JLabel();
        hapusjasa = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Laporan = new javax.swing.JPanel();
        ButtonReportTransaksi = new javax.swing.JLabel();
        ButtonReportBarang = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Retur = new javax.swing.JPanel();
        HapusReturn = new javax.swing.JLabel();
        TambahReturn = new javax.swing.JLabel();
        EditReturn = new javax.swing.JLabel();
        SelesaiReturn = new javax.swing.JLabel();
        txt_cariReturn = new javax.swing.JTextField();
        Cari6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableReturn = new javaswingdev.swing.table.Table();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanelDatabarang = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Laptop4 = new javax.swing.JLabel();
        Laptop5 = new javax.swing.JLabel();
        Sparepart4 = new javax.swing.JLabel();
        Sparepart5 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanelPilihan = new javax.swing.JPanel();
        jPanelLaptop = new javax.swing.JPanel();
        HapusLaptop = new javax.swing.JLabel();
        TambahLaptop = new javax.swing.JLabel();
        EditLaptop = new javax.swing.JLabel();
        txt_cariLaptop = new javax.swing.JTextField();
        Cari4 = new javax.swing.JLabel();
        jScrollPanel2 = new javax.swing.JScrollPane();
        jPanelSparePart = new javax.swing.JPanel();
        HapusSparePart = new javax.swing.JLabel();
        TambahSparePart = new javax.swing.JLabel();
        EditSparePart = new javax.swing.JLabel();
        txt_cariSparePart = new javax.swing.JTextField();
        Cari5 = new javax.swing.JLabel();
        jScrollPanel3 = new javax.swing.JScrollPane();
        jPanelDatakaryawan = new javax.swing.JPanel();
        TambahDatakaryawan = new javax.swing.JLabel();
        EditDatakaryawan = new javax.swing.JLabel();
        HapusDataKaryawan = new javax.swing.JLabel();
        txt_cariKaryawan = new javax.swing.JTextField();
        Cari1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanelDataclient = new javax.swing.JPanel();
        TambahDataKlien = new javax.swing.JLabel();
        EditDataKlien = new javax.swing.JLabel();
        HapusDataKlien = new javax.swing.JLabel();
        txt_cariKlien = new javax.swing.JTextField();
        Cari2 = new javax.swing.JLabel();
        jScrollPanel8 = new javax.swing.JScrollPane();
        jLabel24 = new javax.swing.JLabel();
        jPanelTransaksiPenjualan = new javax.swing.JPanel();
        cetak = new javax.swing.JLabel();
        PilihBarang = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        PilihKlien = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        txt_bayar = new javax.swing.JTextField();
        Bayar = new javax.swing.JLabel();
        txt_kembalian = new javax.swing.JTextField();
        Kembalian = new javax.swing.JLabel();
        txt_tanggalTransaksi = new javax.swing.JTextField();
        txt_idpenjualan = new javax.swing.JTextField();
        jScrollPanel6 = new javax.swing.JScrollPane();
        txt_harga = new javax.swing.JTextField();
        txt_id_barang = new javax.swing.JTextField();
        txt_namabarang = new javax.swing.JTextField();
        txt_id_clien = new javax.swing.JTextField();
        txt_garansi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Garansi = new javax.swing.JLabel();
        Simpan = new javax.swing.JLabel();
        Hapus = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanelTransaksiPembelian = new javax.swing.JPanel();
        pilihanbeli = new javax.swing.JPanel();
        Laptop1 = new javax.swing.JLabel();
        Laptop0 = new javax.swing.JLabel();
        Sparepart1 = new javax.swing.JLabel();
        Sparepart0 = new javax.swing.JLabel();
        Pilihclient1 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanelPilihan1 = new javax.swing.JPanel();
        belilaptop = new javax.swing.JPanel();
        Tambahbeli = new javax.swing.JLabel();
        Editbeli = new javax.swing.JLabel();
        Hapusbeli = new javax.swing.JLabel();
        Selesaibeli = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tb_pilihclientbelilaptop = new javax.swing.JTable();
        txt_totalbeli = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        belisparepart = new javax.swing.JPanel();
        Tambahbeli1 = new javax.swing.JLabel();
        Editbeli1 = new javax.swing.JLabel();
        Hapusbeli1 = new javax.swing.JLabel();
        Selesaibeli1 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tb_pilihclientbelisparepart = new javax.swing.JTable();
        txt_totalbeli1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanelTransaksiservice = new javax.swing.JPanel();
        cetak2 = new javax.swing.JLabel();
        PilihBarang2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        PilihKlien2 = new javax.swing.JLabel();
        Total2 = new javax.swing.JLabel();
        Jumlah2 = new javax.swing.JLabel();
        txt_bayar2 = new javax.swing.JTextField();
        Bayar2 = new javax.swing.JLabel();
        txt_kembalian2 = new javax.swing.JTextField();
        Kembalian2 = new javax.swing.JLabel();
        txt_tanggalTransaksiservice = new javax.swing.JTextField();
        tabeltransaksiservicejasa = new javaswingdev.swing.table.Table();
        tabeltransaksiservice = new javaswingdev.swing.table.Table();
        txt_idtrservice = new javax.swing.JTextField();
        Simpan2 = new javax.swing.JLabel();
        Hapus2 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanelTransaksiTukarTambah = new javax.swing.JPanel();
        Tambahjual = new javax.swing.JLabel();
        Hapusjual = new javax.swing.JLabel();
        Tambahbeli4 = new javax.swing.JLabel();
        Editbeli4 = new javax.swing.JLabel();
        Hapusbeli4 = new javax.swing.JLabel();
        Pilihclient4 = new javax.swing.JLabel();
        Selesaitt = new javax.swing.JLabel();
        txt_totaltt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPanel9 = new javax.swing.JScrollPane();
        jScrollPanel11 = new javax.swing.JScrollPane();
        txt_idtt = new javax.swing.JTextField();
        kembaliantt = new javax.swing.JTextField();
        bayartt = new javax.swing.JTextField();
        garansitt = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LaporanTransaksi = new javax.swing.JPanel();
        MainTransaksi = new javax.swing.JPanel();
        panelTabelTRpenjualan = new javax.swing.JPanel();
        ScrollpanelLaporanpenjualan = new javax.swing.JScrollPane();
        cetak3 = new javax.swing.JLabel();
        panelTabelTRPembelian = new javax.swing.JPanel();
        ScrollpanelLaporanpembelian = new javax.swing.JScrollPane();
        cetak4 = new javax.swing.JLabel();
        panelTabelTRServis = new javax.swing.JPanel();
        cetak1 = new javax.swing.JLabel();
        ScrollpanelLaporanservis = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        MoreinfoPenjualan = new javax.swing.JLabel();
        GambarPenjualan = new javax.swing.JLabel();
        MoreinfoPembelian = new javax.swing.JLabel();
        GambarPembelian = new javax.swing.JLabel();
        MoreinfoServis = new javax.swing.JLabel();
        GambarServis = new javax.swing.JLabel();
        LaporanBarang = new javax.swing.JPanel();
        pilihanbeli2 = new javax.swing.JPanel();
        Laptop2 = new javax.swing.JLabel();
        Laptop3 = new javax.swing.JLabel();
        Sparepart2 = new javax.swing.JLabel();
        Sparepart3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanelPilihan3 = new javax.swing.JPanel();
        baranglaptop3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_laporanlaptop = new javaswingdev.swing.table.Table();
        cetaklaporanlaptop = new javax.swing.JLabel();
        barangsparepart3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_laporansparepart = new javaswingdev.swing.table.Table();
        cetaklaporanSparepart = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeMouseClicked(evt);
            }
        });
        side.add(Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 144, 180, 40));

        tabelmaster.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelmaster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelmasterMouseClicked(evt);
            }
        });
        side.add(tabelmaster, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 180, 40));

        transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transaksiMouseClicked(evt);
            }
        });
        side.add(transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 273, 180, 40));

        servis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        servis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                servisMouseClicked(evt);
            }
        });
        side.add(servis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 180, 40));

        laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanMouseClicked(evt);
            }
        });
        side.add(laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 404, 180, 40));

        retur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        retur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returMouseClicked(evt);
            }
        });
        side.add(retur, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 468, 180, 40));

        gMenu.setBackground(new java.awt.Color(255, 255, 255));
        gMenu.setOpaque(true);
        side.add(gMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 5, 30));

        gdDatamaster.setBackground(new java.awt.Color(6, 18, 77));
        gdDatamaster.setOpaque(true);
        side.add(gdDatamaster, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 215, 5, 30));

        gTransaksi.setBackground(new java.awt.Color(6, 18, 77));
        gTransaksi.setOpaque(true);
        side.add(gTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 279, 5, 30));

        gServis.setBackground(new java.awt.Color(6, 18, 77));
        gServis.setOpaque(true);
        side.add(gServis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 344, 5, 30));

        gLaporan.setBackground(new java.awt.Color(6, 18, 77));
        gLaporan.setOpaque(true);
        side.add(gLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 5, 30));

        gRetur.setBackground(new java.awt.Color(6, 18, 77));
        gRetur.setOpaque(true);
        side.add(gRetur, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 473, 5, 30));

        back.setBackground(new java.awt.Color(255, 255, 255));
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        side.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 580, 100, 34));

        backgroundSide.setBackground(new java.awt.Color(255, 255, 255));
        backgroundSide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/GG.png"))); // NOI18N
        backgroundSide.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        side.add(backgroundSide, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 646));
        side.add(txt_level, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 100, 30));

        getContentPane().add(side, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Header.setBackground(new java.awt.Color(255, 255, 255));

        Time.setEnabled(false);

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addContainerGap(580, Short.MAX_VALUE)
                .addComponent(Time, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Time, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 0, 820, 60));

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setMinimumSize(new java.awt.Dimension(820, 591));
        main.setPreferredSize(new java.awt.Dimension(820, 591));
        main.setLayout(new java.awt.CardLayout());

        home.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/HomeGrafik.png"))); // NOI18N

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addGroup(homeLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        main.add(home, "card2");

        DataMaster.setBackground(new java.awt.Color(255, 255, 255));
        DataMaster.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DataKlien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DataKlien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataKlienMouseClicked(evt);
            }
        });
        DataMaster.add(DataKlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 170, 190));

        DataKaryawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DataKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataKaryawanMouseClicked(evt);
            }
        });
        DataMaster.add(DataKaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 180, 190));

        DataBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataBarangMouseClicked(evt);
            }
        });
        DataMaster.add(DataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 170, 190));

        backgroundMaster.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/New Table Master.png"))); // NOI18N
        DataMaster.add(backgroundMaster, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 590));

        main.add(DataMaster, "card3");

        Transaksi.setBackground(new java.awt.Color(255, 255, 255));
        Transaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ButtonTrPenjualan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonTrPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonTrPenjualanMouseClicked(evt);
            }
        });
        Transaksi.add(ButtonTrPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 630, 70));

        ButtonTrServis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonTrServis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonTrServisMouseClicked(evt);
            }
        });
        Transaksi.add(ButtonTrServis, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 630, 70));

        ButtonnTrPembelian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonnTrPembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonnTrPembelianMouseClicked(evt);
            }
        });
        Transaksi.add(ButtonnTrPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 630, 70));

        ButtonnTrPembelian1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonnTrPembelian1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonnTrPembelian1MouseClicked(evt);
            }
        });
        Transaksi.add(ButtonnTrPembelian1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 630, 70));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/New Table Transaksi.png"))); // NOI18N
        Transaksi.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 590));

        main.add(Transaksi, "card4");

        Servis.setBackground(new java.awt.Color(255, 255, 255));
        Servis.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Service.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Ikon Kotak (1).png"))); // NOI18N
        Servis.add(Service, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        HapusLaptop1.setBackground(new java.awt.Color(255, 255, 255));
        HapusLaptop1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 43.png"))); // NOI18N
        HapusLaptop1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HapusLaptop1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HapusLaptop1MouseClicked(evt);
            }
        });
        Servis.add(HapusLaptop1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, -1, -1));

        TambahLaptop1.setBackground(new java.awt.Color(255, 255, 255));
        TambahLaptop1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41.png"))); // NOI18N
        TambahLaptop1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TambahLaptop1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TambahLaptop1MouseClicked(evt);
            }
        });
        Servis.add(TambahLaptop1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, -1, -1));

        EditLaptop1.setBackground(new java.awt.Color(255, 255, 255));
        EditLaptop1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 42.png"))); // NOI18N
        EditLaptop1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EditLaptop1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditLaptop1MouseClicked(evt);
            }
        });
        Servis.add(EditLaptop1, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 530, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 38.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        Servis.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 530, -1, -1));

        txt_cariServis.setBackground(new java.awt.Color(240, 240, 240));
        txt_cariServis.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_cariServis.setText("Cari Berdasarkan Nama Client");
        txt_cariServis.setBorder(null);
        txt_cariServis.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cariServisFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cariServisFocusLost(evt);
            }
        });
        txt_cariServis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariServisActionPerformed(evt);
            }
        });
        txt_cariServis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariServisKeyReleased(evt);
            }
        });
        Servis.add(txt_cariServis, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 244, 25));

        Cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41 (2).png"))); // NOI18N
        Servis.add(Cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        TabelJasa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelJasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelJasaMouseClicked(evt);
            }
        });
        jScrollPanel5.setViewportView(TabelJasa);

        Servis.add(jScrollPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 260, 360));

        TableServis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableServis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableServisMouseClicked(evt);
            }
        });
        jScrollPane1servis.setViewportView(TableServis);

        Servis.add(jScrollPane1servis, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 510, 440));

        TambahJasa.setBackground(new java.awt.Color(255, 255, 255));
        TambahJasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 75.png"))); // NOI18N
        TambahJasa.setText("Tambah Jasa");
        TambahJasa.setToolTipText("");
        TambahJasa.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        TambahJasa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TambahJasa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TambahJasa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        TambahJasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TambahJasaMouseClicked(evt);
            }
        });
        Servis.add(TambahJasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 440, 90, 70));

        hapusjasa.setBackground(new java.awt.Color(255, 255, 255));
        hapusjasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 76.png"))); // NOI18N
        hapusjasa.setText("Hapus Jasa");
        hapusjasa.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        hapusjasa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapusjasa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        hapusjasa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        hapusjasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusjasaMouseClicked(evt);
            }
        });
        Servis.add(hapusjasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 440, 70, 70));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 78 (1).png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        Servis.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 130, 40));

        main.add(Servis, "card5");

        Laporan.setBackground(new java.awt.Color(255, 255, 255));
        Laporan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ButtonReportTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonReportTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonReportTransaksiMouseClicked(evt);
            }
        });
        Laporan.add(ButtonReportTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 180, 190));

        ButtonReportBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonReportBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonReportBarangMouseClicked(evt);
            }
        });
        Laporan.add(ButtonReportBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 170, 190));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/New Report.png"))); // NOI18N
        Laporan.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 590));

        main.add(Laporan, "card6");

        Retur.setBackground(new java.awt.Color(255, 255, 255));
        Retur.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HapusReturn.setBackground(new java.awt.Color(255, 255, 255));
        HapusReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 43.png"))); // NOI18N
        HapusReturn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HapusReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HapusReturnMouseClicked(evt);
            }
        });
        Retur.add(HapusReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 530, -1, -1));

        TambahReturn.setBackground(new java.awt.Color(255, 255, 255));
        TambahReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41.png"))); // NOI18N
        TambahReturn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TambahReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TambahReturnMouseClicked(evt);
            }
        });
        Retur.add(TambahReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, -1, -1));

        EditReturn.setBackground(new java.awt.Color(255, 255, 255));
        EditReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 42.png"))); // NOI18N
        EditReturn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EditReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditReturnMouseClicked(evt);
            }
        });
        Retur.add(EditReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, -1, -1));

        SelesaiReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 38.png"))); // NOI18N
        SelesaiReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelesaiReturnMouseClicked(evt);
            }
        });
        Retur.add(SelesaiReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 530, -1, -1));

        txt_cariReturn.setBackground(new java.awt.Color(240, 240, 240));
        txt_cariReturn.setText("Cari Berdasarkan Nama Barang");
        txt_cariReturn.setBorder(null);
        txt_cariReturn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cariReturnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cariReturnFocusLost(evt);
            }
        });
        txt_cariReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariReturnActionPerformed(evt);
            }
        });
        txt_cariReturn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariReturnKeyReleased(evt);
            }
        });
        Retur.add(txt_cariReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 244, 25));

        Cari6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41 (2).png"))); // NOI18N
        Retur.add(Cari6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        TableReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableReturnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableReturn);

        Retur.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 750, 440));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 78 (1).png"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        Retur.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 130, 40));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/returnn.png"))); // NOI18N
        Retur.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 210, 70));

        main.add(Retur, "card7");

        jPanelDatabarang.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDatabarang.setMaximumSize(new java.awt.Dimension(3270, 3276));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Laptop4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 73 (1).png"))); // NOI18N
        jPanel1.add(Laptop4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 90, 30));

        Laptop5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 73.png"))); // NOI18N
        Laptop5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Laptop5MouseClicked(evt);
            }
        });
        jPanel1.add(Laptop5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 90, 30));

        Sparepart4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 74 (1).png"))); // NOI18N
        jPanel1.add(Sparepart4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 90, 30));

        Sparepart5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 74.png"))); // NOI18N
        Sparepart5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sparepart5MouseClicked(evt);
            }
        });
        jPanel1.add(Sparepart5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 90, 30));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/DataBarang (1).png"))); // NOI18N
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 210, 50));

        jPanelPilihan.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPilihan.setLayout(new java.awt.CardLayout());

        jPanelLaptop.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLaptop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HapusLaptop.setBackground(new java.awt.Color(255, 255, 255));
        HapusLaptop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 43.png"))); // NOI18N
        HapusLaptop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HapusLaptop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HapusLaptopMouseClicked(evt);
            }
        });
        jPanelLaptop.add(HapusLaptop, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, -1, -1));

        TambahLaptop.setBackground(new java.awt.Color(255, 255, 255));
        TambahLaptop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41.png"))); // NOI18N
        TambahLaptop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TambahLaptop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TambahLaptopMouseClicked(evt);
            }
        });
        jPanelLaptop.add(TambahLaptop, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        EditLaptop.setBackground(new java.awt.Color(255, 255, 255));
        EditLaptop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 42.png"))); // NOI18N
        EditLaptop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EditLaptop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditLaptopMouseClicked(evt);
            }
        });
        jPanelLaptop.add(EditLaptop, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, -1, -1));

        txt_cariLaptop.setBackground(new java.awt.Color(240, 240, 240));
        txt_cariLaptop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_cariLaptop.setText("Cari Berdasarkan Nama");
        txt_cariLaptop.setBorder(null);
        txt_cariLaptop.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cariLaptopKaryawanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cariLaptopKaryawanFocusLost(evt);
            }
        });
        txt_cariLaptop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariLaptopKaryawanActionPerformed(evt);
            }
        });
        txt_cariLaptop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariLaptopKaryawanKeyReleased(evt);
            }
        });
        jPanelLaptop.add(txt_cariLaptop, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 244, 25));

        Cari4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41 (2).png"))); // NOI18N
        jPanelLaptop.add(Cari4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        jTableLaptop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableLaptop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLaptopMouseClicked(evt);
            }
        });
        jScrollPanel2.setViewportView(jTableLaptop);

        jPanelLaptop.add(jScrollPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 780, 410));

        jPanelPilihan.add(jPanelLaptop, "card3");

        jPanelSparePart.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSparePart.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HapusSparePart.setBackground(new java.awt.Color(255, 255, 255));
        HapusSparePart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 43.png"))); // NOI18N
        HapusSparePart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HapusSparePart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HapusSparePartMouseClicked(evt);
            }
        });
        jPanelSparePart.add(HapusSparePart, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, -1, -1));

        TambahSparePart.setBackground(new java.awt.Color(255, 255, 255));
        TambahSparePart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41.png"))); // NOI18N
        TambahSparePart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TambahSparePart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TambahSparePartMouseClicked(evt);
            }
        });
        jPanelSparePart.add(TambahSparePart, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        EditSparePart.setBackground(new java.awt.Color(255, 255, 255));
        EditSparePart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 42.png"))); // NOI18N
        EditSparePart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EditSparePart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditSparePartMouseClicked(evt);
            }
        });
        jPanelSparePart.add(EditSparePart, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, -1, -1));

        txt_cariSparePart.setBackground(new java.awt.Color(240, 240, 240));
        txt_cariSparePart.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_cariSparePart.setText("Cari Berdasarkan Nama");
        txt_cariSparePart.setBorder(null);
        txt_cariSparePart.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cariSparePartKaryawanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cariSparePartKaryawanFocusLost(evt);
            }
        });
        txt_cariSparePart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariSparePartKaryawanActionPerformed(evt);
            }
        });
        txt_cariSparePart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariSparePartKaryawanKeyReleased(evt);
            }
        });
        jPanelSparePart.add(txt_cariSparePart, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 244, 25));

        Cari5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41 (2).png"))); // NOI18N
        jPanelSparePart.add(Cari5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        jTableSparePart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableSparePart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSparePartMouseClicked(evt);
            }
        });
        jScrollPanel3.setViewportView(jTableSparePart);

        jPanelSparePart.add(jScrollPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 780, 410));

        jPanelPilihan.add(jPanelSparePart, "card4");

        jPanel1.add(jPanelPilihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 54, -1, 530));

        javax.swing.GroupLayout jPanelDatabarangLayout = new javax.swing.GroupLayout(jPanelDatabarang);
        jPanelDatabarang.setLayout(jPanelDatabarangLayout);
        jPanelDatabarangLayout.setHorizontalGroup(
            jPanelDatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatabarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDatabarangLayout.setVerticalGroup(
            jPanelDatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatabarangLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        main.add(jPanelDatabarang, "card8");

        jPanelDatakaryawan.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDatakaryawan.setPreferredSize(new java.awt.Dimension(820, 571));
        jPanelDatakaryawan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TambahDatakaryawan.setBackground(new java.awt.Color(255, 255, 255));
        TambahDatakaryawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41.png"))); // NOI18N
        TambahDatakaryawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TambahDatakaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TambahDatakaryawanMouseClicked(evt);
            }
        });
        jPanelDatakaryawan.add(TambahDatakaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, 49));

        EditDatakaryawan.setBackground(new java.awt.Color(255, 255, 255));
        EditDatakaryawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 42.png"))); // NOI18N
        EditDatakaryawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EditDatakaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditDatakaryawanMouseClicked(evt);
            }
        });
        jPanelDatakaryawan.add(EditDatakaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 520, -1, 49));

        HapusDataKaryawan.setBackground(new java.awt.Color(255, 255, 255));
        HapusDataKaryawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 43.png"))); // NOI18N
        HapusDataKaryawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HapusDataKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HapusDataKaryawanMouseClicked(evt);
            }
        });
        jPanelDatakaryawan.add(HapusDataKaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 520, -1, 49));

        txt_cariKaryawan.setBackground(new java.awt.Color(240, 240, 240));
        txt_cariKaryawan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_cariKaryawan.setText("Cari Berdasarkan Nama");
        txt_cariKaryawan.setBorder(null);
        txt_cariKaryawan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cariKaryawanKaryawanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cariKaryawanKaryawanFocusLost(evt);
            }
        });
        txt_cariKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariKaryawanKaryawanActionPerformed(evt);
            }
        });
        txt_cariKaryawan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKaryawanKaryawanKeyReleased(evt);
            }
        });
        jPanelDatakaryawan.add(txt_cariKaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 15, 244, 25));

        Cari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41 (2).png"))); // NOI18N
        jPanelDatakaryawan.add(Cari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 13, -1, -1));

        tabelKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKaryawanMouseClicked(evt);
            }
        });
        jScrollPanel4.setViewportView(tabelKaryawan);

        jPanelDatakaryawan.add(jScrollPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 790, 450));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/DataKaryawan.png"))); // NOI18N
        jPanelDatakaryawan.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 220, 50));

        main.add(jPanelDatakaryawan, "card6");

        jPanelDataclient.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDataclient.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TambahDataKlien.setBackground(new java.awt.Color(255, 255, 255));
        TambahDataKlien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41.png"))); // NOI18N
        TambahDataKlien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TambahDataKlien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TambahDataKlienMouseClicked(evt);
            }
        });
        jPanelDataclient.add(TambahDataKlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 531, -1, 49));

        EditDataKlien.setBackground(new java.awt.Color(255, 255, 255));
        EditDataKlien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 42.png"))); // NOI18N
        EditDataKlien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EditDataKlien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditDataKlienMouseClicked(evt);
            }
        });
        jPanelDataclient.add(EditDataKlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 531, -1, 49));

        HapusDataKlien.setBackground(new java.awt.Color(255, 255, 255));
        HapusDataKlien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 43.png"))); // NOI18N
        HapusDataKlien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HapusDataKlien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HapusDataKlienMouseClicked(evt);
            }
        });
        jPanelDataclient.add(HapusDataKlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 531, -1, 49));

        txt_cariKlien.setBackground(new java.awt.Color(240, 240, 240));
        txt_cariKlien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_cariKlien.setText("Cari Berdasarkan Nama");
        txt_cariKlien.setBorder(null);
        txt_cariKlien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cariKlienFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cariKlienFocusLost(evt);
            }
        });
        txt_cariKlien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariKlienActionPerformed(evt);
            }
        });
        txt_cariKlien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKlienKeyReleased(evt);
            }
        });
        jPanelDataclient.add(txt_cariKlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 15, 244, 25));

        Cari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41 (2).png"))); // NOI18N
        jPanelDataclient.add(Cari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 13, -1, -1));

        tabelklien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelklien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelklienMouseClicked(evt);
            }
        });
        jScrollPanel8.setViewportView(tabelklien);

        jPanelDataclient.add(jScrollPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 70, 800, 430));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/DataKlien (1).png"))); // NOI18N
        jPanelDataclient.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 220, 50));

        main.add(jPanelDataclient, "card9");

        jPanelTransaksiPenjualan.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTransaksiPenjualan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cetak.setBackground(new java.awt.Color(255, 255, 255));
        cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Cetak struk.png"))); // NOI18N
        cetak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetakMouseClicked(evt);
            }
        });
        jPanelTransaksiPenjualan.add(cetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 530, -1, -1));

        PilihBarang.setBackground(new java.awt.Color(255, 255, 255));
        PilihBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Pilih barang.png"))); // NOI18N
        PilihBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PilihBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PilihBarangMouseClicked(evt);
            }
        });
        jPanelTransaksiPenjualan.add(PilihBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Rp");
        jPanelTransaksiPenjualan.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 475, 20, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Rp");
        jPanelTransaksiPenjualan.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 423, 20, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Rp");
        jPanelTransaksiPenjualan.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 373, 20, 30));

        PilihKlien.setBackground(new java.awt.Color(255, 255, 255));
        PilihKlien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Pilih klien.png"))); // NOI18N
        PilihKlien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PilihKlien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PilihKlienMouseClicked(evt);
            }
        });
        jPanelTransaksiPenjualan.add(PilihKlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, -1, -1));

        txt_total.setBackground(new java.awt.Color(236, 239, 254));
        txt_total.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_total.setBorder(null);
        txt_total.setEnabled(false);
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        jPanelTransaksiPenjualan.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 372, 220, 35));

        Total.setBackground(new java.awt.Color(255, 255, 255));
        Total.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Total.png"))); // NOI18N
        Total.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TotalMouseClicked(evt);
            }
        });
        jPanelTransaksiPenjualan.add(Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        txt_bayar.setBackground(new java.awt.Color(236, 239, 254));
        txt_bayar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_bayar.setBorder(null);
        txt_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayarActionPerformed(evt);
            }
        });
        jPanelTransaksiPenjualan.add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 422, 160, 35));

        Bayar.setBackground(new java.awt.Color(255, 255, 255));
        Bayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Bayar.png"))); // NOI18N
        Bayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BayarMouseClicked(evt);
            }
        });
        jPanelTransaksiPenjualan.add(Bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        txt_kembalian.setBackground(new java.awt.Color(236, 239, 254));
        txt_kembalian.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_kembalian.setBorder(null);
        txt_kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kembalianActionPerformed(evt);
            }
        });
        jPanelTransaksiPenjualan.add(txt_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 472, 220, 35));

        Kembalian.setBackground(new java.awt.Color(255, 255, 255));
        Kembalian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Kembalian.png"))); // NOI18N
        Kembalian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Kembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembalianMouseClicked(evt);
            }
        });
        jPanelTransaksiPenjualan.add(Kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        txt_tanggalTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tanggalTransaksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tanggalTransaksi.setBorder(null);
        txt_tanggalTransaksi.setEnabled(false);
        txt_tanggalTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tanggalTransaksiActionPerformed(evt);
            }
        });
        jPanelTransaksiPenjualan.add(txt_tanggalTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 130, 30));

        txt_idpenjualan.setBackground(new java.awt.Color(236, 239, 254));
        txt_idpenjualan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_idpenjualan.setBorder(null);
        txt_idpenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idpenjualanActionPerformed(evt);
            }
        });
        jPanelTransaksiPenjualan.add(txt_idpenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 85, 80, 20));

        txt_idkaryawan.setBackground(new java.awt.Color(204, 204, 204));
        txt_idkaryawan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idkaryawan.setBorder(null);
        txt_idkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idkaryawanActionPerformed(evt);
            }
        });
        jPanelTransaksiPenjualan.add(txt_idkaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 540, -1, 10));

        tabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPanel6.setViewportView(tabelTransaksi);

        jPanelTransaksiPenjualan.add(jScrollPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 780, 240));

        txt_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargaActionPerformed(evt);
            }
        });
        jPanelTransaksiPenjualan.add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 100, -1));

        txt_id_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_barangActionPerformed(evt);
            }
        });
        jPanelTransaksiPenjualan.add(txt_id_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 100, -1));
        jPanelTransaksiPenjualan.add(txt_namabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 100, -1));

        txt_id_clien.setBorder(null);
        txt_id_clien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_clienActionPerformed(evt);
            }
        });
        jPanelTransaksiPenjualan.add(txt_id_clien, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 100, 20));

        txt_garansi.setBackground(new java.awt.Color(236, 239, 254));
        txt_garansi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_garansi.setBorder(null);
        txt_garansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_garansiActionPerformed(evt);
            }
        });
        jPanelTransaksiPenjualan.add(txt_garansi, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 422, 70, 35));

        txt_jumlah.setEditable(false);
        txt_jumlah.setBackground(new java.awt.Color(255, 255, 255));
        txt_jumlah.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_jumlah.setBorder(null);
        txt_jumlah.setEnabled(false);
        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });
        jPanelTransaksiPenjualan.add(txt_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 225, 70, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Bulan");
        jPanelTransaksiPenjualan.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 423, 50, 30));

        Garansi.setBackground(new java.awt.Color(255, 255, 255));
        Garansi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Garansi.png"))); // NOI18N
        Garansi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Garansi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GaransiMouseClicked(evt);
            }
        });
        jPanelTransaksiPenjualan.add(Garansi, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 420, -1, -1));

        Simpan.setBackground(new java.awt.Color(255, 255, 255));
        Simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Simpan transaksi.png"))); // NOI18N
        Simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SimpanMouseClicked(evt);
            }
        });
        jPanelTransaksiPenjualan.add(Simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, -1, -1));

        Hapus.setBackground(new java.awt.Color(255, 255, 255));
        Hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Hapus Transaksi.png"))); // NOI18N
        Hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HapusMouseClicked(evt);
            }
        });
        jPanelTransaksiPenjualan.add(Hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, -1, -1));
        jPanelTransaksiPenjualan.add(txtCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 120, -1));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 71 (1).png"))); // NOI18N
        jPanelTransaksiPenjualan.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Transaksi Penjualan.png"))); // NOI18N
        jPanelTransaksiPenjualan.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, 320, 60));

        main.add(jPanelTransaksiPenjualan, "card7");

        jPanelTransaksiPembelian.setBackground(new java.awt.Color(255, 255, 255));

        pilihanbeli.setBackground(new java.awt.Color(255, 255, 255));
        pilihanbeli.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Laptop1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 73 (1).png"))); // NOI18N
        pilihanbeli.add(Laptop1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 90, 30));

        Laptop0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 73.png"))); // NOI18N
        Laptop0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Laptop0MouseClicked(evt);
            }
        });
        pilihanbeli.add(Laptop0, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 90, 30));

        Sparepart1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 74 (1).png"))); // NOI18N
        pilihanbeli.add(Sparepart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 90, 30));

        Sparepart0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 74.png"))); // NOI18N
        Sparepart0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sparepart0MouseClicked(evt);
            }
        });
        pilihanbeli.add(Sparepart0, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 90, 30));

        Pilihclient1.setBackground(new java.awt.Color(255, 255, 255));
        Pilihclient1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Pilih klien.png"))); // NOI18N
        Pilihclient1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Pilihclient1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pilihclient1MouseClicked(evt);
            }
        });
        pilihanbeli.add(Pilihclient1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, -1, -1));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Transaksi Pembelian.png"))); // NOI18N
        pilihanbeli.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 60));

        jPanelPilihan1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPilihan1.setLayout(new java.awt.CardLayout());

        belilaptop.setBackground(new java.awt.Color(255, 255, 255));
        belilaptop.setPreferredSize(new java.awt.Dimension(870, 490));
        belilaptop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tambahbeli.setBackground(new java.awt.Color(255, 255, 255));
        Tambahbeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41.png"))); // NOI18N
        Tambahbeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tambahbeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TambahbeliMouseClicked(evt);
            }
        });
        belilaptop.add(Tambahbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, 50));

        Editbeli.setBackground(new java.awt.Color(255, 255, 255));
        Editbeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 42.png"))); // NOI18N
        Editbeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Editbeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditbeliMouseClicked(evt);
            }
        });
        belilaptop.add(Editbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, -1, 49));

        Hapusbeli.setBackground(new java.awt.Color(255, 255, 255));
        Hapusbeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 43.png"))); // NOI18N
        Hapusbeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Hapusbeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HapusbeliMouseClicked(evt);
            }
        });
        belilaptop.add(Hapusbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, -1, 49));

        Selesaibeli.setBackground(new java.awt.Color(255, 255, 255));
        Selesaibeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 38.png"))); // NOI18N
        Selesaibeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Selesaibeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelesaibeliMouseClicked(evt);
            }
        });
        belilaptop.add(Selesaibeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 440, 140, 50));

        tb_pembelianlaptop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tb_pembelianlaptop);

        belilaptop.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 800, 370));

        tb_pilihclientbelilaptop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(tb_pilihclientbelilaptop);

        belilaptop.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 790, 280));

        txt_totalbeli.setBackground(new java.awt.Color(236, 239, 254));
        txt_totalbeli.setText("0");
        txt_totalbeli.setBorder(null);
        txt_totalbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalbeliActionPerformed(evt);
            }
        });
        belilaptop.add(txt_totalbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 393, 200, 33));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 80.png"))); // NOI18N
        belilaptop.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 390, -1, -1));

        jPanelPilihan1.add(belilaptop, "card4");

        belisparepart.setBackground(new java.awt.Color(255, 255, 255));
        belisparepart.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tambahbeli1.setBackground(new java.awt.Color(255, 255, 255));
        Tambahbeli1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 41.png"))); // NOI18N
        Tambahbeli1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tambahbeli1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tambahbeli1MouseClicked(evt);
            }
        });
        belisparepart.add(Tambahbeli1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 140, 50));

        Editbeli1.setBackground(new java.awt.Color(255, 255, 255));
        Editbeli1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 42.png"))); // NOI18N
        Editbeli1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Editbeli1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Editbeli1MouseClicked(evt);
            }
        });
        belisparepart.add(Editbeli1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 440, 140, 50));

        Hapusbeli1.setBackground(new java.awt.Color(255, 255, 255));
        Hapusbeli1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 43.png"))); // NOI18N
        Hapusbeli1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Hapusbeli1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Hapusbeli1MouseClicked(evt);
            }
        });
        belisparepart.add(Hapusbeli1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, 140, 50));

        Selesaibeli1.setBackground(new java.awt.Color(255, 255, 255));
        Selesaibeli1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 38.png"))); // NOI18N
        Selesaibeli1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Selesaibeli1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Selesaibeli1MouseClicked(evt);
            }
        });
        belisparepart.add(Selesaibeli1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 440, -1, 50));

        tb_pembeliansparepart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tb_pembeliansparepart);

        belisparepart.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 800, 370));

        tb_pilihclientbelisparepart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(tb_pilihclientbelisparepart);

        belisparepart.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 790, 330));

        txt_totalbeli1.setBackground(new java.awt.Color(236, 239, 254));
        txt_totalbeli1.setText("0");
        txt_totalbeli1.setBorder(null);
        txt_totalbeli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalbeli1ActionPerformed(evt);
            }
        });
        belisparepart.add(txt_totalbeli1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 393, 230, 33));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 80.png"))); // NOI18N
        belisparepart.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 390, -1, -1));

        jPanelPilihan1.add(belisparepart, "card5");

        pilihanbeli.add(jPanelPilihan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 74, 870, 490));

        javax.swing.GroupLayout jPanelTransaksiPembelianLayout = new javax.swing.GroupLayout(jPanelTransaksiPembelian);
        jPanelTransaksiPembelian.setLayout(jPanelTransaksiPembelianLayout);
        jPanelTransaksiPembelianLayout.setHorizontalGroup(
            jPanelTransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pilihanbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelTransaksiPembelianLayout.setVerticalGroup(
            jPanelTransaksiPembelianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pilihanbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        main.add(jPanelTransaksiPembelian, "card7");

        jPanelTransaksiservice.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTransaksiservice.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cetak2.setBackground(new java.awt.Color(255, 255, 255));
        cetak2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Cetak struk.png"))); // NOI18N
        cetak2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cetak2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetak2MouseClicked(evt);
            }
        });
        jPanelTransaksiservice.add(cetak2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 530, -1, -1));

        PilihBarang2.setBackground(new java.awt.Color(255, 255, 255));
        PilihBarang2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Pilih barang.png"))); // NOI18N
        PilihBarang2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PilihBarang2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PilihBarang2MouseClicked(evt);
            }
        });
        jPanelTransaksiservice.add(PilihBarang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Rp");
        jPanelTransaksiservice.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 475, 20, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Rp");
        jPanelTransaksiservice.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 423, 20, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Rp");
        jPanelTransaksiservice.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 373, 20, 30));

        PilihKlien2.setBackground(new java.awt.Color(255, 255, 255));
        PilihKlien2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PilihKlien2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PilihKlien2MouseClicked(evt);
            }
        });
        jPanelTransaksiservice.add(PilihKlien2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, -1));

        txt_total2.setBackground(new java.awt.Color(236, 239, 254));
        txt_total2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_total2.setBorder(null);
        txt_total2.setEnabled(false);
        txt_total2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_total2ActionPerformed(evt);
            }
        });
        jPanelTransaksiservice.add(txt_total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 372, 160, 35));

        Total2.setBackground(new java.awt.Color(255, 255, 255));
        Total2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Total.png"))); // NOI18N
        Total2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Total2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Total2MouseClicked(evt);
            }
        });
        jPanelTransaksiservice.add(Total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        Jumlah2.setBackground(new java.awt.Color(255, 255, 255));
        Jumlah2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Jumlah2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jumlah2MouseClicked(evt);
            }
        });
        jPanelTransaksiservice.add(Jumlah2, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 370, -1, -1));

        txt_bayar2.setBackground(new java.awt.Color(236, 239, 254));
        txt_bayar2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_bayar2.setBorder(null);
        txt_bayar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayar2ActionPerformed(evt);
            }
        });
        txt_bayar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_bayar2KeyTyped(evt);
            }
        });
        jPanelTransaksiservice.add(txt_bayar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 422, 160, 35));

        Bayar2.setBackground(new java.awt.Color(255, 255, 255));
        Bayar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Bayar.png"))); // NOI18N
        Bayar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bayar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bayar2MouseClicked(evt);
            }
        });
        jPanelTransaksiservice.add(Bayar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        txt_kembalian2.setBackground(new java.awt.Color(236, 239, 254));
        txt_kembalian2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_kembalian2.setBorder(null);
        txt_kembalian2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kembalian2ActionPerformed(evt);
            }
        });
        jPanelTransaksiservice.add(txt_kembalian2, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 472, 220, 35));

        Kembalian2.setBackground(new java.awt.Color(255, 255, 255));
        Kembalian2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Kembalian.png"))); // NOI18N
        Kembalian2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Kembalian2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Kembalian2MouseClicked(evt);
            }
        });
        jPanelTransaksiservice.add(Kembalian2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 790, -1));

        txt_tanggalTransaksiservice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_tanggalTransaksiservice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tanggalTransaksiservice.setBorder(null);
        txt_tanggalTransaksiservice.setEnabled(false);
        txt_tanggalTransaksiservice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tanggalTransaksiserviceActionPerformed(evt);
            }
        });
        jPanelTransaksiservice.add(txt_tanggalTransaksiservice, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 130, 30));

        tabeltransaksiservicejasa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tabeltransaksiservicejasa);

        jPanelTransaksiservice.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 210, 250));

        tabeltransaksiservice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tabeltransaksiservice);

        jPanelTransaksiservice.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 110, 560, 250));

        txt_idtrservice.setBackground(new java.awt.Color(236, 239, 254));
        txt_idtrservice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_idtrservice.setText(":");
        txt_idtrservice.setBorder(null);
        jPanelTransaksiservice.add(txt_idtrservice, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 75, 80, 20));

        Simpan2.setBackground(new java.awt.Color(255, 255, 255));
        Simpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Simpan transaksi.png"))); // NOI18N
        Simpan2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Simpan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan2MouseClicked(evt);
            }
        });
        jPanelTransaksiservice.add(Simpan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, -1, -1));

        Hapus2.setBackground(new java.awt.Color(255, 255, 255));
        Hapus2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Hapus Transaksi.png"))); // NOI18N
        Hapus2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Hapus2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Hapus2MouseClicked(evt);
            }
        });
        jPanelTransaksiservice.add(Hapus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Transaksi Service.png"))); // NOI18N
        jPanelTransaksiservice.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 60));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 71 (1).png"))); // NOI18N
        jPanelTransaksiservice.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        main.add(jPanelTransaksiservice, "card7");

        jPanelTransaksiTukarTambah.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTransaksiTukarTambah.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tambahjual.setBackground(new java.awt.Color(255, 255, 255));
        Tambahjual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 75.png"))); // NOI18N
        Tambahjual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tambahjual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TambahjualMouseClicked(evt);
            }
        });
        jPanelTransaksiTukarTambah.add(Tambahjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, 50, 50));

        Hapusjual.setBackground(new java.awt.Color(255, 255, 255));
        Hapusjual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 76.png"))); // NOI18N
        Hapusjual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Hapusjual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HapusjualMouseClicked(evt);
            }
        });
        jPanelTransaksiTukarTambah.add(Hapusjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 470, 50, 49));

        Tambahbeli4.setBackground(new java.awt.Color(255, 255, 255));
        Tambahbeli4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 75.png"))); // NOI18N
        Tambahbeli4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tambahbeli4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tambahbeli4MouseClicked(evt);
            }
        });
        jPanelTransaksiTukarTambah.add(Tambahbeli4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 50, 50));

        Editbeli4.setBackground(new java.awt.Color(255, 255, 255));
        Editbeli4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 77.png"))); // NOI18N
        Editbeli4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Editbeli4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Editbeli4MouseClicked(evt);
            }
        });
        jPanelTransaksiTukarTambah.add(Editbeli4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 260, 50, 49));

        Hapusbeli4.setBackground(new java.awt.Color(255, 255, 255));
        Hapusbeli4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 76.png"))); // NOI18N
        Hapusbeli4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Hapusbeli4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Hapusbeli4MouseClicked(evt);
            }
        });
        jPanelTransaksiTukarTambah.add(Hapusbeli4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 50, 49));

        Pilihclient4.setBackground(new java.awt.Color(255, 255, 255));
        Pilihclient4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Pilih klien.png"))); // NOI18N
        Pilihclient4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Pilihclient4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pilihclient4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Pilihclient4MouseEntered(evt);
            }
        });
        jPanelTransaksiTukarTambah.add(Pilihclient4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, -1, -1));

        Selesaitt.setBackground(new java.awt.Color(255, 255, 255));
        Selesaitt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 38.png"))); // NOI18N
        Selesaitt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Selesaitt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelesaittMouseClicked(evt);
            }
        });
        jPanelTransaksiTukarTambah.add(Selesaitt, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 530, -1, 50));

        txt_totaltt.setEditable(false);
        txt_totaltt.setBackground(new java.awt.Color(236, 239, 254));
        txt_totaltt.setText("0");
        txt_totaltt.setBorder(null);
        txt_totaltt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalttActionPerformed(evt);
            }
        });
        jPanelTransaksiTukarTambah.add(txt_totaltt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 483, 210, 33));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tabel Pembelian");
        jPanelTransaksiTukarTambah.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 120, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tabel Penjualan");
        jPanelTransaksiTukarTambah.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 130, 20));

        ttbeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPanel9.setViewportView(ttbeli);

        jPanelTransaksiTukarTambah.add(jScrollPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 780, 120));

        ttjual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPanel11.setViewportView(ttjual);

        jPanelTransaksiTukarTambah.add(jScrollPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 780, 120));

        txt_idtt.setEditable(false);
        txt_idtt.setBackground(new java.awt.Color(236, 239, 254));
        txt_idtt.setText(":");
        txt_idtt.setBorder(null);
        jPanelTransaksiTukarTambah.add(txt_idtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 76, 80, 20));

        kembaliantt.setEditable(false);
        kembaliantt.setBackground(new java.awt.Color(236, 239, 254));
        kembaliantt.setBorder(null);
        kembaliantt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalianttActionPerformed(evt);
            }
        });
        jPanelTransaksiTukarTambah.add(kembaliantt, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 533, 170, 33));

        bayartt.setBackground(new java.awt.Color(236, 239, 254));
        bayartt.setBorder(null);
        bayartt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarttActionPerformed(evt);
            }
        });
        jPanelTransaksiTukarTambah.add(bayartt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 533, 170, 33));

        garansitt.setBackground(new java.awt.Color(236, 239, 254));
        garansitt.setBorder(null);
        jPanelTransaksiTukarTambah.add(garansitt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 483, 180, 33));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 71 (1).png"))); // NOI18N
        jPanelTransaksiTukarTambah.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 80.png"))); // NOI18N
        jPanelTransaksiTukarTambah.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 480, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 81.png"))); // NOI18N
        jPanelTransaksiTukarTambah.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 79.png"))); // NOI18N
        jPanelTransaksiTukarTambah.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 530, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 67.png"))); // NOI18N
        jPanelTransaksiTukarTambah.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Transaksi TT (1).png"))); // NOI18N
        jPanelTransaksiTukarTambah.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 4, 320, 60));

        main.add(jPanelTransaksiTukarTambah, "card7");

        LaporanTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        LaporanTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LaporanTransaksiMouseClicked(evt);
            }
        });
        LaporanTransaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MainTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        MainTransaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTabelTRpenjualan.setBackground(new java.awt.Color(255, 255, 255));
        panelTabelTRpenjualan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelLaporanpenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        ScrollpanelLaporanpenjualan.setViewportView(tabelLaporanpenjualan);

        panelTabelTRpenjualan.add(ScrollpanelLaporanpenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 790, 330));

        cetak3.setBackground(new java.awt.Color(255, 255, 255));
        cetak3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Cetak struk.png"))); // NOI18N
        cetak3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cetak3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetak3MouseClicked(evt);
            }
        });
        panelTabelTRpenjualan.add(cetak3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 350, -1, 40));

        MainTransaksi.add(panelTabelTRpenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 430));

        panelTabelTRPembelian.setBackground(new java.awt.Color(255, 255, 255));
        panelTabelTRPembelian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelLaporanTRpembelian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelLaporanTRpembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelLaporanTRpembelianMouseClicked(evt);
            }
        });
        ScrollpanelLaporanpembelian.setViewportView(tabelLaporanTRpembelian);

        panelTabelTRPembelian.add(ScrollpanelLaporanpembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 790, 330));

        cetak4.setBackground(new java.awt.Color(255, 255, 255));
        cetak4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Cetak struk.png"))); // NOI18N
        cetak4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cetak4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetak4MouseClicked(evt);
            }
        });
        panelTabelTRPembelian.add(cetak4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 350, -1, 40));

        MainTransaksi.add(panelTabelTRPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 430));

        panelTabelTRServis.setBackground(new java.awt.Color(255, 255, 255));
        panelTabelTRServis.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cetak1.setBackground(new java.awt.Color(255, 255, 255));
        cetak1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Cetak struk.png"))); // NOI18N
        cetak1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cetak1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetak1MouseClicked(evt);
            }
        });
        panelTabelTRServis.add(cetak1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 350, -1, 40));

        tabelLaporanServis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        ScrollpanelLaporanservis.setViewportView(tabelLaporanServis);

        panelTabelTRServis.add(ScrollpanelLaporanservis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 790, 330));

        MainTransaksi.add(panelTabelTRServis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 430));

        LaporanTransaksi.add(MainTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 790, 400));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 82.png"))); // NOI18N
        LaporanTransaksi.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 300, -1));

        JumlahPenjualan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        JumlahPenjualan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LaporanTransaksi.add(JumlahPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 80, 40));

        MoreinfoPenjualan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MoreinfoPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MoreinfoPenjualanMouseClicked(evt);
            }
        });
        LaporanTransaksi.add(MoreinfoPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 120, 26));

        GambarPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 53 (2).png"))); // NOI18N
        LaporanTransaksi.add(GambarPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 200, 110));

        JumlahPembelian.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        JumlahPembelian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LaporanTransaksi.add(JumlahPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 80, 40));

        MoreinfoPembelian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MoreinfoPembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MoreinfoPembelianMouseClicked(evt);
            }
        });
        LaporanTransaksi.add(MoreinfoPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 120, 26));

        GambarPembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 56.png"))); // NOI18N
        LaporanTransaksi.add(GambarPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 200, 110));

        MoreinfoServis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MoreinfoServis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MoreinfoServisMouseClicked(evt);
            }
        });
        LaporanTransaksi.add(MoreinfoServis, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 120, 26));

        JumlahServis.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        JumlahServis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LaporanTransaksi.add(JumlahServis, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 80, 40));

        GambarServis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 57.png"))); // NOI18N
        LaporanTransaksi.add(GambarServis, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 200, 110));

        main.add(LaporanTransaksi, "card3");

        LaporanBarang.setBackground(new java.awt.Color(255, 255, 255));
        LaporanBarang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pilihanbeli2.setBackground(new java.awt.Color(255, 255, 255));
        pilihanbeli2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Laptop2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 73 (1).png"))); // NOI18N
        pilihanbeli2.add(Laptop2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 90, 30));

        Laptop3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 73.png"))); // NOI18N
        Laptop3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Laptop3MouseClicked(evt);
            }
        });
        pilihanbeli2.add(Laptop3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 90, 30));

        Sparepart2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 74 (1).png"))); // NOI18N
        pilihanbeli2.add(Sparepart2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 90, 30));

        Sparepart3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 74.png"))); // NOI18N
        Sparepart3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sparepart3MouseClicked(evt);
            }
        });
        pilihanbeli2.add(Sparepart3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 90, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Group 82 (1).png"))); // NOI18N
        pilihanbeli2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 60));

        jPanelPilihan3.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPilihan3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        baranglaptop3.setBackground(new java.awt.Color(255, 255, 255));
        baranglaptop3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_laporanlaptop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tb_laporanlaptop);

        baranglaptop3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 770, 420));

        cetaklaporanlaptop.setBackground(new java.awt.Color(255, 255, 255));
        cetaklaporanlaptop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Cetak struk.png"))); // NOI18N
        cetaklaporanlaptop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cetaklaporanlaptop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetaklaporanlaptopMouseClicked(evt);
            }
        });
        baranglaptop3.add(cetaklaporanlaptop, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 450, -1, 40));

        jPanelPilihan3.add(baranglaptop3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, -1));

        barangsparepart3.setBackground(new java.awt.Color(255, 255, 255));
        barangsparepart3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_laporansparepart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tb_laporansparepart);

        barangsparepart3.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 780, 420));

        cetaklaporanSparepart.setBackground(new java.awt.Color(255, 255, 255));
        cetaklaporanSparepart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pm/project/PROPERTI/Cetak struk.png"))); // NOI18N
        cetaklaporanSparepart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cetaklaporanSparepart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetaklaporanSparepartMouseClicked(evt);
            }
        });
        barangsparepart3.add(cetaklaporanSparepart, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 450, -1, 40));

        jPanelPilihan3.add(barangsparepart3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, -1));

        pilihanbeli2.add(jPanelPilihan3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 76, 790, 490));

        LaporanBarang.add(pilihanbeli2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        main.add(LaporanBarang, "card15");

        getContentPane().add(main, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 55, -1, -1));

        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel7MouseDragged(evt);
            }
        });
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelmasterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelmasterMouseClicked
        String level = txt_level.getText().toString();
        String level0 = "admin";
        System.out.println(level);
        System.out.println(level0);
        
        if(level == null ? level0 == null : level.equals(level0)){
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(DataMaster);
        main.repaint();
        main.revalidate();
            gMenu.setBackground(new Color(6, 18, 77));
        gdDatamaster.setBackground(new Color(255, 255, 255));
        gTransaksi.setBackground(new Color(6, 18, 77));
        gServis.setBackground(new Color(6, 18, 77));
        gLaporan.setBackground(new Color(6, 18, 77));
        gRetur.setBackground(new Color(6, 18, 77));
        
        }       
    }//GEN-LAST:event_tabelmasterMouseClicked

    private void transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksiMouseClicked
        //Status
        gMenu.setBackground(new Color(6, 18, 77));
        gdDatamaster.setBackground(new Color(6, 18, 77));
        gTransaksi.setBackground(new Color(255, 255, 255));
        gServis.setBackground(new Color(6, 18, 77));
        gLaporan.setBackground(new Color(6, 18, 77));
        gRetur.setBackground(new Color(6, 18, 77));
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(Transaksi);
        main.repaint();
        main.revalidate();
    }//GEN-LAST:event_transaksiMouseClicked

    private void servisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_servisMouseClicked
        //Status
        gMenu.setBackground(new Color(6, 18, 77));
        gdDatamaster.setBackground(new Color(6, 18, 77));
        gTransaksi.setBackground(new Color(6, 18, 77));
        gServis.setBackground(new Color(255, 255, 255));
        gLaporan.setBackground(new Color(6, 18, 77));
        gRetur.setBackground(new Color(6, 18, 77));
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(Servis);
        main.repaint();
        main.revalidate();
        System.out.println(txt_level.getText());
    }//GEN-LAST:event_servisMouseClicked

    private void returMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returMouseClicked
        //Status
        gMenu.setBackground(new Color(6, 18, 77));
        gdDatamaster.setBackground(new Color(6, 18, 77));
        gTransaksi.setBackground(new Color(6, 18, 77));
        gServis.setBackground(new Color(6, 18, 77));
        gLaporan.setBackground(new Color(6, 18, 77));
        gRetur.setBackground(new Color(255,255,255));
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(Retur);
        main.repaint();
        main.revalidate();
    }//GEN-LAST:event_returMouseClicked

    private void HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseClicked
        //Status
        chart.start(); 
        gMenu.setBackground(new Color(255, 255, 255));
        gdDatamaster.setBackground(new Color(6, 18, 77));
        gTransaksi.setBackground(new Color(6, 18, 77));
        gServis.setBackground(new Color(6, 18, 77));
        gLaporan.setBackground(new Color(6, 18, 77));
        gRetur.setBackground(new Color(6, 18, 77));
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(home);
        main.repaint();
        main.revalidate();
        LoadDataChart();
    }//GEN-LAST:event_HomeMouseClicked

    private void laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanMouseClicked
        //Status
        gMenu.setBackground(new Color(6, 18, 77));
        gdDatamaster.setBackground(new Color(6, 18, 77));
        gTransaksi.setBackground(new Color(6, 18, 77));
        gServis.setBackground(new Color(6, 18, 77));
        gLaporan.setBackground(new Color(255, 255, 255));
        gRetur.setBackground(new Color(6, 18, 77));
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(Laporan);
        main.repaint();
        main.revalidate();
    }//GEN-LAST:event_laporanMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        //Kembali ke Login
       this.setVisible(false);
       new LOGIN().setVisible(true);
    }//GEN-LAST:event_backMouseClicked

    private void TambahDatakaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TambahDatakaryawanMouseClicked
        // Menampilkan form input data Karyawan
        InputDataKaryawan b = new InputDataKaryawan();
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_TambahDatakaryawanMouseClicked

    private void EditDatakaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditDatakaryawanMouseClicked
        // Menampilkan form edit data karyawan
        if(selectedKaryawanID != null){
            new EditDataKaryawan().setVisible(true);
        } else JOptionPane.showMessageDialog(null,"Pilih karyawan terlebih dahulu");
    }//GEN-LAST:event_EditDatakaryawanMouseClicked

    private void HapusDataKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HapusDataKaryawanMouseClicked
        // Menghapus data karyawan
        if(selectedKaryawanID!=null){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_karyawan WHERE id_karyawan = '"+this.selectedKaryawanID+"'");
            ps.execute();
            JOptionPane.showMessageDialog(null,"Data karyawan berhasil dihapus");
            this.loadDataKaryawan();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Tidak bisa dihapus karena terdapat data karyawan di transaksi");
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }}else JOptionPane.showMessageDialog(null,"Pilih data terlebih dahulu");
    }//GEN-LAST:event_HapusDataKaryawanMouseClicked

    private void txt_cariKaryawanKaryawanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariKaryawanKaryawanFocusGained
     if (txt_cariKaryawan.getText().equals("Cari Berdasarkan Nama")) {
     txt_cariKaryawan.setText(null);
     txt_cariKaryawan.requestFocus();
     removeplaceholderStyle(txt_cariKaryawan);
        }

    }//GEN-LAST:event_txt_cariKaryawanKaryawanFocusGained

    private void txt_cariKaryawanKaryawanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariKaryawanKaryawanFocusLost
   if (txt_cariKaryawan.getText().length()==0) {
            addplaceholderStyle(txt_cariKaryawan);
            txt_cariKaryawan.setText("Cari Berdasarkan Nama");
        }
        
    }//GEN-LAST:event_txt_cariKaryawanKaryawanFocusLost

    private void txt_cariKaryawanKaryawanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKaryawanKaryawanKeyReleased
        String [] judul = {"ID","Nama Karyawan","Jenis Kelamin","Tanggal Lahir","No HP","Alamat","Email"};
        model = new DefaultTableModel(judul,0);
        tabelKaryawan.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tb_karyawan WHERE nama_karyawan LIKE '%"+txt_cariKaryawan.getText()+"%'");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(7),rs.getString(5),rs.getString(4),rs.getString(6)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_cariKaryawanKaryawanKeyReleased

    private void txt_cariKaryawanKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariKaryawanKaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariKaryawanKaryawanActionPerformed

    private void HapusDataKlienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HapusDataKlienMouseClicked
       //Menghapus data klien
       if(selectedClientID != null){ 
       try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_client WHERE id_client = '"+this.selectedClientID+"'");
            ps.execute();
            JOptionPane.showMessageDialog(null,"data berhasil dihapus");
            this.loadDataClient();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Tidak bisa dihapus karena terdapat data client di transaksi");
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }}else JOptionPane.showMessageDialog(null,"Pilih klien terlebih dahulu");
    }//GEN-LAST:event_HapusDataKlienMouseClicked

    private void EditDataKlienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditDataKlienMouseClicked
        //Mengedit data klien
        if(selectedClientID != null){
            new EditDataClient().setVisible(true);
        } else JOptionPane.showMessageDialog(null,"Pilih klien terlebih dahulu");
    }//GEN-LAST:event_EditDataKlienMouseClicked

    private void TambahDataKlienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TambahDataKlienMouseClicked
        //Menampilkan form input data klien
        InputDataClient a = new InputDataClient();
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_TambahDataKlienMouseClicked

    private void txt_cariKlienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKlienKeyReleased
        String [] judul = {"ID","Nama Client","Alamat","No HP"};
        model = new DefaultTableModel(judul,0);
        tabelklien.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tb_client WHERE nama_client LIKE '%"+txt_cariKlien.getText()+"%'");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_cariKlienKeyReleased

    private void txt_cariKlienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariKlienActionPerformed
 
    }//GEN-LAST:event_txt_cariKlienActionPerformed

    private void txt_cariKlienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariKlienFocusLost
       if (txt_cariKlien.getText().length()==0) {
            addplaceholderStyle(txt_cariKlien);
            txt_cariKlien.setText("Cari Berdasarkan Nama");
        }
        
    }//GEN-LAST:event_txt_cariKlienFocusLost

    private void txt_cariKlienFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariKlienFocusGained
           if (txt_cariKlien.getText().equals("Cari Berdasarkan Nama")) {
            txt_cariKlien.setText(null);
            txt_cariKlien.requestFocus();
            removeplaceholderStyle(txt_cariKlien);
        }
    }//GEN-LAST:event_txt_cariKlienFocusGained

    private void HapusSparePartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HapusSparePartMouseClicked
        // Hapus data Sparepart
        String sparepart = "Spare Part";
        if(sparepart==null?selectedjenisbrgID == null:sparepart.equals(selectedjenisbrgID)){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_barang WHERE id_barang = '"+this.selectedbarangID+"'");
            ps.execute();
            JOptionPane.showMessageDialog(null,"data berhasil dihapus");
            this.loadDataLaptop();
            this.loadDataSparepart();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Tidak bisa dihapus karena terdapat data supplier di transaksi");
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }}else {JOptionPane.showMessageDialog(null,"Pilih barang terlebih dahulu");}
    }//GEN-LAST:event_HapusSparePartMouseClicked

    private void TambahSparePartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TambahSparePartMouseClicked
        // Menampilkan form input data barang SparePart
           InputdatabarangSparepart c = new InputdatabarangSparepart();
        c.setVisible(true);
        c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_TambahSparePartMouseClicked

    private void EditSparePartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditSparePartMouseClicked
        // Menampilkan form edit data barang SparePart
        String sparepart = "Spare Part";
        if(sparepart==null?selectedjenisbrgID == null:sparepart.equals(selectedjenisbrgID)){
            new EditDatabarangSparepart().setVisible(true);}
        else {JOptionPane.showMessageDialog(null,"Pilih barang terlebih dahulu");}
    }//GEN-LAST:event_EditSparePartMouseClicked

    private void HapusLaptopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HapusLaptopMouseClicked
        //Menghapus data barang laptop
        String laptop = "Laptop";        
        if(laptop == null ? selectedjenisbrgID == null : laptop.equals(selectedjenisbrgID) ){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            PreparedStatement ps=con.prepareStatement("DELETE FROM tb_barang WHERE id_barang = '"+this.selectedbarangID+"'");
            ps.execute();
            JOptionPane.showMessageDialog(null,"data berhasil dihapus");
            this.loadDataLaptop();
            this.loadDataSparepart();
            selectedjenisbrgID = null;
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Tidak bisa dihapus karena terdapat data supplier di transaksi");
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }}else {JOptionPane.showMessageDialog(null,"Pilih barang terlebih dahulu");}
    }//GEN-LAST:event_HapusLaptopMouseClicked

    private void TambahLaptopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TambahLaptopMouseClicked
        // Menampilkan form input data barang laptop
        InputDatabaranglaptop b = new InputDatabaranglaptop();
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_TambahLaptopMouseClicked

    private void EditLaptopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditLaptopMouseClicked
        // Menampilkan form edit data barang laptop
         String laptop = "Laptop";        
        if(laptop == null ? selectedjenisbrgID == null : laptop.equals(selectedjenisbrgID) ){
            new EditDatabarangLaptop().setVisible(true);
            selectedjenisbrgID = null;
        }else {JOptionPane.showMessageDialog(null,"Pilih barang terlebih dahulu");}
    }//GEN-LAST:event_EditLaptopMouseClicked

    private void jLabel7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseDragged
        // TODO add your handling code here:
        int koordinatX=evt.getXOnScreen();
        int koordinatY=evt.getYOnScreen();
        this.setLocation(koordinatX-mousepX,koordinatY-mousepY);
    }//GEN-LAST:event_jLabel7MouseDragged

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        // TODO add your handling code here:
        mousepX = evt.getX();
        mousepY = evt.getY();
    }//GEN-LAST:event_jLabel7MousePressed

    private void HapusLaptop1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HapusLaptop1MouseClicked
        if(selectedServisID!=null){
            try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_service WHERE id_service = '"+this.selectedServisID+"'");
            ps.execute();
            JOptionPane.showMessageDialog(null,"Data service berhasil dihapus");
            this.loadDataservis();
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }}else JOptionPane.showMessageDialog(null,"Pilih data terlebih dahulu");
    }//GEN-LAST:event_HapusLaptop1MouseClicked

    private void TambahLaptop1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TambahLaptop1MouseClicked
  InputDataServis a = new InputDataServis();
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    }//GEN-LAST:event_TambahLaptop1MouseClicked

    private void EditLaptop1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditLaptop1MouseClicked
        if(selectedServisID!=null){
            EditDataServis a = new EditDataServis();
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }else JOptionPane.showMessageDialog(null,"Pilih data terlebih dahulu");
        
    }//GEN-LAST:event_EditLaptop1MouseClicked
                                          

    private void txt_cariServisFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariServisFocusGained
        // TODO add your handling code here:
                 if (txt_cariServis.getText().equals("Cari Berdasarkan Nama Client")) {
            txt_cariServis.setText(null);
            txt_cariServis.requestFocus();
            removeplaceholderStyle(txt_cariServis);
        }
    }//GEN-LAST:event_txt_cariServisFocusGained

    private void txt_cariServisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariServisFocusLost
        // TODO add your handling code here:
        if (txt_cariServis.getText().length()==0) {
            addplaceholderStyle(txt_cariServis);
            txt_cariServis.setText("Cari Berdasarkan Nama Client");
        }
    }//GEN-LAST:event_txt_cariServisFocusLost

    private void txt_cariServisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariServisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariServisActionPerformed

    private void txt_cariServisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariServisKeyReleased
        // TODO add your handling code here:
  String [] judul = {"ID","Nama klien","Nama barang","Keluhan","Tanggal mulai","Tanggal selesai","Status"};
  model = new DefaultTableModel(judul,0);
        TableServis.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT tb_service.id_service,tb_client.nama_client,tb_service.nama_barang,tb_service.keluhan,tb_service.tgl_mulai,tb_service.tgl_selesai,\n" +
"tb_service.status FROM tb_service INNER JOIN tb_client ON tb_service.id_client=tb_client.id_client WHERE \n" +
"tb_client.nama_client LIKE '%"+txt_cariServis.getText()+"%'");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_cariServisKeyReleased

    private void txt_cariLaptopKaryawanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariLaptopKaryawanFocusGained
        // TODO add your handling code here:
       if (txt_cariLaptop.getText().equals("Cari Berdasarkan Nama")) {
            txt_cariLaptop.setText(null);
            txt_cariLaptop.requestFocus();
            removeplaceholderStyle(txt_cariLaptop);
        }
    }//GEN-LAST:event_txt_cariLaptopKaryawanFocusGained

    private void txt_cariLaptopKaryawanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariLaptopKaryawanFocusLost
        // TODO add your handling code here:
      if (txt_cariLaptop.getText().length()==0) {
            addplaceholderStyle(txt_cariLaptop);
            txt_cariLaptop.setText("Cari Berdasarkan Nama");
        }
    }//GEN-LAST:event_txt_cariLaptopKaryawanFocusLost

    private void txt_cariLaptopKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariLaptopKaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariLaptopKaryawanActionPerformed

    private void txt_cariLaptopKaryawanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariLaptopKaryawanKeyReleased
        // TODO add your handling code here:
        String [] judul = {"ID","Nama jenis","Nama barang","Processor","GPU","RAM","Warna","Jumlah","Harga jual", "Harga beli"};
  model = new DefaultTableModel(judul,0);
        jTableLaptop.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
           ResultSet rs = con.createStatement().executeQuery("SELECT tb_barang.id_barang,tb_barang.nama_jenis,tb_barang.nama_barang, tb_detail_barang.processor,tb_detail_barang.GPU,tb_detail_barang.RAM,tb_detail_barang.Warna, tb_barang.kuantitas ,tb_barang.harga_jual,"
                   + "tb_barang.harga_beli FROM tb_barang INNER JOIN tb_detail_barang ON tb_barang.id_barang=tb_detail_barang.id_detail_barang WHERE tb_barang.nama_barang LIKE '%"+txt_cariLaptop.getText()+"%'");
            while(rs.next()){
            String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)
                ,rs.getString(8),rs.getString(9),rs.getString(10)};
            model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_cariLaptopKaryawanKeyReleased

    private void txt_cariSparePartKaryawanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariSparePartKaryawanFocusGained
        // TODO add your handling code here:
         if (txt_cariSparePart.getText().equals("Cari Berdasarkan Nama")) {
            txt_cariSparePart.setText(null);
            txt_cariSparePart.requestFocus();
            removeplaceholderStyle(txt_cariSparePart);
        }
    }//GEN-LAST:event_txt_cariSparePartKaryawanFocusGained

    private void txt_cariSparePartKaryawanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariSparePartKaryawanFocusLost
   if (txt_cariSparePart.getText().length()==0) {
            addplaceholderStyle(txt_cariSparePart);
            txt_cariSparePart.setText("Cari Berdasarkan Nama");
        }
    }//GEN-LAST:event_txt_cariSparePartKaryawanFocusLost

    private void txt_cariSparePartKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariSparePartKaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariSparePartKaryawanActionPerformed

    private void txt_cariSparePartKaryawanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariSparePartKaryawanKeyReleased
        // TODO add your handling code here:
       String [] judul = {"ID","Nama jenis","Nama barang","Jumlah","Harga jual", "Harga beli"};
  model = new DefaultTableModel(judul,0);
        jTableSparePart.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * from tb_barang where id_barang LIKE '%BRS%' AND nama_barang LIKE '%"+txt_cariSparePart.getText()+"%'");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_cariSparePartKaryawanKeyReleased

    private void cetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakMouseClicked
        // TODO add your handling code here:
           try {
            java.sql.Connection conn = (Connection)KoneksiTR.getConnection();
            InputStream report = getClass().getResourceAsStream("Nota.jasper");
             HashedMap hash =new HashedMap();
            hash.put("KODE",txtCetak.getText());

            JasperPrint jp = JasperFillManager.fillReport(report,hash , conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        idTransaksi_autoCETAK();
        
        
        
    }//GEN-LAST:event_cetakMouseClicked

    private void PilihBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PilihBarangMouseClicked
        // TODO add your handling code here:
        ListdataBarang a = new ListdataBarang();
        a.setVisible(true);
    }//GEN-LAST:event_PilihBarangMouseClicked

    private void PilihKlienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PilihKlienMouseClicked
        // TODO add your handling code here:
          ListDataKlien a = new ListDataKlien();
        a.setVisible(true);
        
    }//GEN-LAST:event_PilihKlienMouseClicked

    private void TotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TotalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalMouseClicked

    private void KembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembalianMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_KembalianMouseClicked

    private void BayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BayarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BayarMouseClicked

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void txt_kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kembalianActionPerformed

    private void txt_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayarActionPerformed
        // TODO add your handling code here:
           int total, bayar, kembalian;

        total = Integer.valueOf(txt_total.getText());
        bayar = Integer.valueOf(txt_bayar.getText());

        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            txt_kembalian.setText(String.valueOf(kembalian));
        }
        
    }//GEN-LAST:event_txt_bayarActionPerformed

    private void txt_idkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idkaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idkaryawanActionPerformed

    private void txt_id_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_barangActionPerformed

    private void txt_id_clienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_clienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_clienActionPerformed

    private void txt_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargaActionPerformed

    private void txt_garansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_garansiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_garansiActionPerformed

    private void SimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SimpanMouseClicked
  
    DefaultTableModel model = (DefaultTableModel) tabelTransaksi.getModel();
    String query;
    PreparedStatement ps;           
    String id_penjualan = txt_idpenjualan.getText();
    String id_klien = txt_id_clien.getText();
    String id_karyawan = txt_idkaryawan.getText();
    if(tb_pilihclientbelisparepart.getRowCount()>0){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            java.sql.Statement st = con.createStatement();
            query = "INSERT INTO tb_client VALUES ('"+tb_pilihclientbelisparepart.getValueAt(0,0)+"','"+tb_pilihclientbelisparepart.getValueAt(0,1)+"','"+tb_pilihclientbelisparepart.getValueAt(0, 3)+"','"+tb_pilihclientbelisparepart.getValueAt(0, 2)+"')";
            ps = con.prepareStatement(query);
            ps.execute();} 
        catch (Exception e) {
            System.out.println("simpan penjualan error");}
        
        try {
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root",""); 
           java.sql.Statement st = con.createStatement();
           query = "INSERT INTO tb_penjualan VALUES('"+id_penjualan +"','"+tb_pilihclientbelisparepart.getValueAt(0,0)+"',now(),'"+id_karyawan+"','"+txt_bayar.getText()+"','"+txt_kembalian.getText()+"','"+txt_total.getText()+"','"+txt_garansi.getText()+"')";
           st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("simpan penjualan error");
        }}
    else{
        try {
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root",""); 
           java.sql.Statement st = con.createStatement();
           query = "INSERT INTO tb_penjualan VALUES('"+id_penjualan +"','"+id_klien+"',now(),'"+id_karyawan+"','"+txt_bayar.getText()+"','"+txt_kembalian.getText()+"','"+txt_total.getText()+"','"+txt_garansi.getText()+"')";
           st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("simpan penjualan error");
        }
    }
    
    
        try {
            Connection c = KoneksiTR.getConnection();
            int baris = tabelTransaksi.getRowCount();
            for (int i = 0; i < baris; i++) {
                String sql = "INSERT INTO tb_detail_penjualan VALUES('"+ tabelTransaksi.getValueAt(i, 0) +"','"+ tabelTransaksi.getValueAt(i, 1) +"','"+tabelTransaksi.getValueAt(i, 3)+"','"+tabelTransaksi.getValueAt(i, 5)+"')";
                PreparedStatement p = c.prepareStatement(sql);
                p.executeUpdate();
                p.close();
            }
        } catch (Exception e) {
            System.out.println("simpan penjualanrinci error");
        }
        clear();
        utama();
        kosong();
        idTransaksi_auto();
        kosong();

    }//GEN-LAST:event_SimpanMouseClicked

    private void HapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HapusMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tabelTransaksi.getModel();
        int row = tabelTransaksi.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        txt_bayar.setText("");
        txt_kembalian.setText("0");
     
    }//GEN-LAST:event_HapusMouseClicked

    private void txt_tanggalTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tanggalTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tanggalTransaksiActionPerformed

    private void GaransiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GaransiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_GaransiMouseClicked
    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void TambahbeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TambahbeliMouseClicked
        InputLaptopBeli b = new InputLaptopBeli();
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_TambahbeliMouseClicked

    private void EditbeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditbeliMouseClicked
        EditLaptopBeli b = new EditLaptopBeli();
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_EditbeliMouseClicked

    private void HapusbeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HapusbeliMouseClicked
        int row = tb_pembelianlaptop.getSelectedRow();//0
        idlist.remove(row);
        modelpb.removeRow(row);
        _settotalbeli();
        //modelpj.
    }//GEN-LAST:event_HapusbeliMouseClicked

    private void SelesaibeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelesaibeliMouseClicked
        int rows = tb_pembelianlaptop.getRowCount();
        //       String id_karyawan = Login.id_karyawan;
        String query;
        PreparedStatement ps;
        if(tb_pilihclientbelisparepart.getRowCount()>0){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            java.sql.Statement st = con.createStatement();
            query = "INSERT INTO tb_client VALUES ('"+tb_pilihclientbelisparepart.getValueAt(0,0)+"','"+tb_pilihclientbelisparepart.getValueAt(0,1)+"','"+tb_pilihclientbelisparepart.getValueAt(0, 3)+"','"+tb_pilihclientbelisparepart.getValueAt(0, 2)+"')";
            ps = con.prepareStatement(query);
            ps.execute();
            query = "INSERT INTO tb_pembelian VALUES ('"+tb_pembelianlaptop.getValueAt(0, 0).toString()+"',now(),'"+tb_pilihclientbelisparepart.getValueAt(0,0)+"','"+txt_totalbeli.getText()+"')";
            st.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        }else{
            try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            java.sql.Statement st = con.createStatement();
            query = "INSERT INTO tb_pembelian VALUES ('"+tb_pembelianlaptop.getValueAt(0, 0).toString()+"',now(),'"+txt_id_clien.getText()+"','"+txt_totalbeli.getText()+"')";
            st.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
                for (int i = 0; i < rows; i++) {
                    java.sql.Statement st = con.createStatement();
                    String query2 = "INSERT INTO tb_barang VALUES ('"+tb_pembelianlaptop.getValueAt(i, 1)+"','Laptop','"+tb_pembelianlaptop.getValueAt(i,2)+"','"+tb_pembelianlaptop.getValueAt(i,7)+"',null,'"+tb_pembelianlaptop.getValueAt(i,8)+"')";
                    st.executeUpdate(query2);
                    String query1 = "INSERT INTO tb_detail_pembelian VALUES ('"+tb_pembelianlaptop.getValueAt(i, 0).toString()+"','"+tb_pembelianlaptop.getValueAt(i, 1).toString()+"','"+tb_pembelianlaptop.getValueAt(i, 7).toString()+"','"+tb_pembelianlaptop.getValueAt(i,8)+"')";
                    st.executeUpdate(query1);
                    String query3 = "INSERT INTO tb_detail_barang VALUES ('"+tb_pembelianlaptop.getValueAt(i, 1)+"','"+tb_pembelianlaptop.getValueAt(i,3)+"','"+tb_pembelianlaptop.getValueAt(i,4)+"','"+tb_pembelianlaptop.getValueAt(i,5)+"','"+tb_pembelianlaptop.getValueAt(i,6)+"')";
                    st.executeUpdate(query3);
            }
            idlist.clear();
            JOptionPane.showMessageDialog(null, "Transaksi Pembelian Berhasil di Input");
            reset();

        } catch (Exception e) {
            //con.rollback();
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_SelesaibeliMouseClicked

    private void txt_totalbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalbeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalbeliActionPerformed

    private void Tambahbeli1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tambahbeli1MouseClicked
        // TODO add your handling code here:
        InputSparepartBeli b = new InputSparepartBeli();
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_Tambahbeli1MouseClicked

    private void Editbeli1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Editbeli1MouseClicked
        // TODO add your handling code here:
        EditSparepartBeli b = new EditSparepartBeli();
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_Editbeli1MouseClicked

    private void Hapusbeli1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Hapusbeli1MouseClicked
        // TODO add your handling code here:
        int row = tb_pembeliansparepart.getSelectedRow();//0
        idlist.remove(row);

        //InputLaptopBeli.idlist1.remove(row);
        modelpbs.removeRow(row);
        _settotalbelis();
    }//GEN-LAST:event_Hapusbeli1MouseClicked

    private void Pilihclient1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pilihclient1MouseClicked
        System.out.println(tb_pilihclientbelisparepart.getRowCount());
        if(tb_pilihclientbelisparepart.getRowCount()<1){
            ListDataKlien c = new ListDataKlien();
            c.setVisible(true);
            c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }else{
            int respon = JOptionPane.showConfirmDialog(this, "Anda sudah memasukkan client \nApakan ingin edit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respon==JOptionPane.YES_OPTION){
                ListDataKlien a = new ListDataKlien();
                a.setVisible(true);
                a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        }
    }//GEN-LAST:event_Pilihclient1MouseClicked

    private void Selesaibeli1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Selesaibeli1MouseClicked
        int rows = tb_pembeliansparepart.getRowCount();
        //       String id_karyawan = Login.id_karyawan;
        String query;
        PreparedStatement ps;
        if(tb_pilihclientbelisparepart.getRowCount()>0){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            java.sql.Statement st = con.createStatement();
            query = "INSERT INTO tb_client VALUES ('"+tb_pilihclientbelisparepart.getValueAt(0,0)+"','"+tb_pilihclientbelisparepart.getValueAt(0,1)+"','"+tb_pilihclientbelisparepart.getValueAt(0, 3)+"','"+tb_pilihclientbelisparepart.getValueAt(0, 2)+"')";
            ps = con.prepareStatement(query);
            ps.execute();
            query = "INSERT INTO tb_pembelian VALUES ('"+tb_pembeliansparepart.getValueAt(0, 0).toString()+"',now(),'"+tb_pilihclientbelisparepart.getValueAt(0,0)+"','"+txt_totalbeli1.getText()+"')";
            st.executeUpdate(query);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Anda belum memasukkan client");
        }}
        else{
            try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            java.sql.Statement st = con.createStatement();
            query = "INSERT INTO tb_pembelian VALUES ('"+tb_pembeliansparepart.getValueAt(0, 0).toString()+"',now(),'"+txt_id_clien.getText()+"','"+txt_totalbeli1.getText()+"')";
            st.executeUpdate(query);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Anda belum memilih client");
        }
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
          
                for (int i = 0; i < rows; i++) {
                    java.sql.Statement st = con.createStatement();
                    String query2 = "INSERT INTO tb_barang VALUES ('"+tb_pembeliansparepart.getValueAt(i, 1)+"','Spare Part','"+tb_pembeliansparepart.getValueAt(i,2)+"','"+tb_pembeliansparepart.getValueAt(i,3)+"',null,'"+tb_pembeliansparepart.getValueAt(i,4)+"')";
                    st.executeUpdate(query2);
                    String query1 = "INSERT INTO tb_detail_pembelian VALUES ('"+tb_pembeliansparepart.getValueAt(0, 0).toString()+"','"+tb_pembeliansparepart.getValueAt(i, 1).toString()+"','"+tb_pembeliansparepart.getValueAt(i, 3).toString()+"','"+tb_pembeliansparepart.getValueAt(i, 4).toString()+"')";
                    st.executeUpdate(query1);
                
            }
            //con.commit();
            idlist.clear();
            JOptionPane.showMessageDialog(null, "Transaksi Pembelian Berhasil di Input");
            reset();

        } catch (Exception e) {
            //con.rollback();
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_Selesaibeli1MouseClicked

    private void txt_totalbeli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalbeli1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalbeli1ActionPerformed

    private void tabelklienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelklienMouseClicked
             // TODO add your handling code here:
        int row =tabelklien.getSelectedRow();
        String klien =tabelklien.getValueAt(row,0).toString();
        this.selectedClientID=klien;
    }//GEN-LAST:event_tabelklienMouseClicked

    private void tabelKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKaryawanMouseClicked
        // Memilih data yang ada di tabel karyawan
        int row = tabelKaryawan.getSelectedRow();
        String karyawan = tabelKaryawan.getValueAt(row, 0).toString();
        this.selectedKaryawanID = karyawan;
    }//GEN-LAST:event_tabelKaryawanMouseClicked

    private void jTableLaptopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLaptopMouseClicked
        // TODO add your handling code here:
          // Memilih data yang ada di tabel Laptop
        int row = jTableLaptop.getSelectedRow();
        String client = jTableLaptop.getValueAt(row, 0).toString();
        this.selectedbarangID = client;
        String jenis = jTableLaptop.getValueAt(row, 1).toString();
        this.selectedjenisbrgID = jenis;
        System.out.println(selectedbarangID);
        System.out.println(selectedjenisbrgID);
    }//GEN-LAST:event_jTableLaptopMouseClicked

    private void jTableSparePartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSparePartMouseClicked
       // Memilih data yang ada di tabel Spare part
      int row = jTableSparePart.getSelectedRow();
        String client = jTableSparePart.getValueAt(row, 0).toString();
        this.selectedbarangID = client;
        String jenis = jTableSparePart.getValueAt(row, 1).toString();
        this.selectedjenisbrgID = jenis;
        System.out.println(selectedbarangID);
        System.out.println(selectedjenisbrgID);
    }//GEN-LAST:event_jTableSparePartMouseClicked

    private void HapusReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HapusReturnMouseClicked
        
        if(selectedReturnID != null){
            try{
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
                PreparedStatement ps = con.prepareStatement("DELETE FROM tb_return WHERE id_return = '"+selectedReturnID+"'");
                ps.execute();
                JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
                this.loadDatareturn();
            }
            catch (SQLException ex){
                JOptionPane.showMessageDialog(null,"Tidak bisa dihapus karena terdapat data karyawan di transaksi");
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }} else JOptionPane.showMessageDialog(null,"Pilih data terlebih dahulu");
    }//GEN-LAST:event_HapusReturnMouseClicked

    private void TambahReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TambahReturnMouseClicked
        // TODO add your handling code here:
        PilihBarangReturn b = new PilihBarangReturn();
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_TambahReturnMouseClicked

    private void EditReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditReturnMouseClicked
        if(selectedReturnID != null){
            new EditDataReturn().setVisible(true);
        }else JOptionPane.showMessageDialog(null, "Pilih Data Terlebih Dahulu");
    }//GEN-LAST:event_EditReturnMouseClicked

    private void SelesaiReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelesaiReturnMouseClicked
        // TODO add your handling code here:
        int row = TableReturn.getSelectedRow();
        if(selectedReturnID != null){
            try{
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
                PreparedStatement ps = con.prepareStatement("UPDATE tb_return SET tgl_keluar=now(), status = 'Tutup', cost='"+TableReturn.getValueAt(row, 6)+"' WHERE id_return = '"+selectedReturnID+"'");
                ps.execute();
                JOptionPane.showMessageDialog(null,"Data return berhasil diedit");
                loadDatareturn();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Masukkan Harga Terlebih Dahulu");
            }
        }else JOptionPane.showMessageDialog(null, "Pilih Data Terlebih Dahulu");

    }//GEN-LAST:event_SelesaiReturnMouseClicked

    private void txt_cariReturnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariReturnFocusGained
        // TODO add your handling code here:
        if(txt_cariReturn.getText().equals("Cari Berdasarkan Nama Barang")) {
            txt_cariReturn.setText("");
            txt_cariReturn.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txt_cariReturnFocusGained

    private void txt_cariReturnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cariReturnFocusLost
        // TODO add your handling code here:
        if(txt_cariReturn.getText().equals("")) {
            txt_cariReturn.setText("Cari Berdasarkan Nama Barang");
            txt_cariReturn.setForeground(new Color(153,153, 153));
        } else {
            txt_cariReturn.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txt_cariReturnFocusLost

    private void txt_cariReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariReturnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cariReturnActionPerformed

    private void txt_cariReturnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariReturnKeyReleased
        String [] judul = {"ID Return","ID Transaksi","Nama Barang","Keluhan","Tgl Masuk","Status","Cost"};
        model = new DefaultTableModel(judul,0);
        TableReturn.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * From tb_return where nama_barang LIKE '%"+txt_cariReturn.getText()+"%' and status != 'Tutup' ;");
            while(rs.next()){
                String data [] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(7),rs.getString(8)};
                model.addRow(data);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_cariReturnKeyReleased

    private void cetak2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetak2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cetak2MouseClicked

    private void PilihBarang2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PilihBarang2MouseClicked
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(Servis);
        main.repaint();
        main.revalidate();
    }//GEN-LAST:event_PilihBarang2MouseClicked

    private void PilihKlien2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PilihKlien2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PilihKlien2MouseClicked

    private void txt_total2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_total2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total2ActionPerformed

    private void Total2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Total2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Total2MouseClicked

    private void Jumlah2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jumlah2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Jumlah2MouseClicked

    private void txt_bayar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayar2ActionPerformed
        int total, bayar, kembalian;

        total = Integer.valueOf(txt_total2.getText());
        bayar = Integer.valueOf(txt_bayar2.getText());

        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            txt_kembalian2.setText(String.valueOf(kembalian));
        }
    }//GEN-LAST:event_txt_bayar2ActionPerformed

    private void Bayar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bayar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Bayar2MouseClicked

    private void txt_kembalian2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kembalian2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kembalian2ActionPerformed

    private void Kembalian2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kembalian2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Kembalian2MouseClicked

    private void txt_tanggalTransaksiserviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tanggalTransaksiserviceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tanggalTransaksiserviceActionPerformed

    private void Simpan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan2MouseClicked
        String query;
        PreparedStatement ps;

        try{

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            query = "INSERT INTO tb_trs_service VALUES ('"+txt_idtrservice.getText()+"','"+tabeltransaksiservice.getValueAt(0,0)+"',now(),'"+txt_total2.getText()+"','"+txt_bayar2.getText()+"','"+txt_kembalian2.getText()+"')";
            ps = con.prepareStatement(query);
            ps.execute();
            query = ("UPDATE tb_service SET status='Tutup', tgl_selesai=now() WHERE id_service = '"+tabeltransaksiservice.getValueAt(0,0)+"'");
            ps = con.prepareStatement(query);    
            ps.execute();
            JOptionPane.showMessageDialog(null, "Transaksi berhasil");
            clear();
            idTransaksiService_auto();
            loadDatatransaksiservice();
            loadDatatransaksiservicejasa();
            loadDataservis();;
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Anda belum memasukkan client");
        }
    }//GEN-LAST:event_Simpan2MouseClicked

    private void Hapus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Hapus2MouseClicked
        modeltrs.removeRow(0);
        totalBiayaService();
    }//GEN-LAST:event_Hapus2MouseClicked

    private void TambahjualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TambahjualMouseClicked
        ListdataBarang a = new ListdataBarang();
        a.setVisible(true);
    }//GEN-LAST:event_TambahjualMouseClicked

    private void HapusjualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HapusjualMouseClicked
        DefaultTableModel model = (DefaultTableModel) tabelTransaksi.getModel();
        int row = tabelTransaksi.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        txt_bayar.setText("");
        txt_kembalian.setText("0");
    }//GEN-LAST:event_HapusjualMouseClicked

    private void Tambahbeli4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tambahbeli4MouseClicked
        InputLaptopBeli b = new InputLaptopBeli();
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_Tambahbeli4MouseClicked

    private void Editbeli4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Editbeli4MouseClicked
        EditLaptopBeli b = new EditLaptopBeli();
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_Editbeli4MouseClicked

    private void Hapusbeli4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Hapusbeli4MouseClicked
        int row = tb_pembelianlaptop.getSelectedRow();//0
        idlist.remove(row);
        modelpb.removeRow(row);
        _settotalbeli();
    }//GEN-LAST:event_Hapusbeli4MouseClicked

    private void Pilihclient4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pilihclient4MouseClicked
        ListDataKlien a = new ListDataKlien();
        a.setVisible(true);
    }//GEN-LAST:event_Pilihclient4MouseClicked

    private void Pilihclient4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pilihclient4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Pilihclient4MouseEntered

    private void SelesaittMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelesaittMouseClicked
        String query;
    PreparedStatement ps;           
    String id_penjualan = txt_idpenjualan.getText();
    String id_klien = txt_id_clien.getText();
    String id_karyawan = txt_idkaryawan.getText();
    int rows = tb_pembelianlaptop.getRowCount();
    int baris1 = tabelTransaksi.getRowCount();
    

        
    
    
    if(tb_pilihclientbelisparepart.getRowCount()==1){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            java.sql.Statement st = con.createStatement();
            query = "INSERT INTO tb_client VALUES ('"+tb_pilihclientbelisparepart.getValueAt(0,0)+"','"+tb_pilihclientbelisparepart.getValueAt(0,1)+"','"+tb_pilihclientbelisparepart.getValueAt(0, 3)+"','"+tb_pilihclientbelisparepart.getValueAt(0, 2)+"')";
            ps = con.prepareStatement(query);
            ps.execute();
            try {
                query = "INSERT INTO tb_penjualan VALUES('"+id_penjualan +"','"+tb_pilihclientbelisparepart.getValueAt(0,0)+"',now(),'"+id_karyawan+"',null,null,'"+txt_total.getText()+"','"+garansitt.getText()+"')";
                st.executeUpdate(query);
                query = "INSERT INTO tb_pembelian VALUES ('"+tb_pembelianlaptop.getValueAt(0, 0).toString()+"',now(),'"+tb_pilihclientbelisparepart.getValueAt(0,0)+"','"+txt_totalbeli.getText()+"')";
                st.executeUpdate(query);
                try {
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
                    for (int i = 0; i < rows; i++) {
                        //java.sql.Statement st = con.createStatement();
                        String query2 = "INSERT INTO tb_barang VALUES ('"+tb_pembelianlaptop.getValueAt(i, 1)+"','Laptop','"+tb_pembelianlaptop.getValueAt(i,2)+"','"+tb_pembelianlaptop.getValueAt(i,7)+"',null,'"+tb_pembelianlaptop.getValueAt(i,8)+"')";
                        st.executeUpdate(query2);
                        String query1 = "INSERT INTO tb_detail_pembelian VALUES ('"+tb_pembelianlaptop.getValueAt(i, 0).toString()+"','"+tb_pembelianlaptop.getValueAt(i, 1).toString()+"','"+tb_pembelianlaptop.getValueAt(i, 7).toString()+"','"+tb_pembelianlaptop.getValueAt(i,8)+"')";
                        st.executeUpdate(query1);
                        String query3 = "INSERT INTO tb_detail_barang VALUES ('"+tb_pembelianlaptop.getValueAt(i, 1)+"','"+tb_pembelianlaptop.getValueAt(i,3)+"','"+tb_pembelianlaptop.getValueAt(i,4)+"','"+tb_pembelianlaptop.getValueAt(i,5)+"','"+tb_pembelianlaptop.getValueAt(i,6)+"')";
                        st.executeUpdate(query3);
                    try {
                        Connection c = KoneksiTR.getConnection();
                        //int baris1 = tabelTransaksi.getRowCount();
                        for (int j = 0; j < baris1; j++) {
                            String sql = "INSERT INTO tb_detail_penjualan VALUES('"+ tabelTransaksi.getValueAt(j, 0) +"','"+ tabelTransaksi.getValueAt(j, 1) +"','"+tabelTransaksi.getValueAt(j, 3)+"','"+tabelTransaksi.getValueAt(j, 5)+"')";
                            PreparedStatement p = c.prepareStatement(sql);
                            p.executeUpdate();
                            p.close();
                        }
                        try {
                            query = "INSERT INTO tb_tukar_tambah VALUES('"+txt_idtt.getText()+"','"+tb_pembelianlaptop.getValueAt(0, 0).toString()+"','"+id_penjualan +"','"+txt_totaltt.getText()+"','"+bayartt.getText()+"','"+kembaliantt.getText()+"')";
                            st.executeUpdate(query);
                            JOptionPane.showMessageDialog(null, "Transaksi Pembelian Berhasil di Input");
                            idlist.clear();
                            reset();
                            clear();
                            utama();
                            kosong();
                            idTransaksi_auto();
                            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Masukkan uang pembayaran");
        }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Masukkan Garansi");
        }
        }   catch (Exception e) {
            JOptionPane.showMessageDialog(null, "02 Anda belum Memilih Client");
        }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "01 Anda belum Memilih Client");}
        
        }
    else{
        try {
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root",""); 
           java.sql.Statement st = con.createStatement();
           query = "INSERT INTO tb_penjualan VALUES('"+id_penjualan +"','"+id_klien+"',now(),'"+id_karyawan+"',null,null,null,'"+garansitt.getText()+"')";
           st.executeUpdate(query);
           query = "INSERT INTO tb_pembelian VALUES ('"+tb_pembelianlaptop.getValueAt(0, 0).toString()+"',now(),'"+id_klien+"','"+txt_totalbeli.getText()+"')";
           st.executeUpdate(query);
           try {
                for (int i = 0; i < rows; i++) {
                    String query2 = "INSERT INTO tb_barang VALUES ('"+tb_pembelianlaptop.getValueAt(i, 1)+"','Laptop','"+tb_pembelianlaptop.getValueAt(i,2)+"','"+tb_pembelianlaptop.getValueAt(i,7)+"',null,'"+tb_pembelianlaptop.getValueAt(i,8)+"')";
                    st.executeUpdate(query2);
                    String query1 = "INSERT INTO tb_detail_pembelian VALUES ('"+tb_pembelianlaptop.getValueAt(i, 0).toString()+"','"+tb_pembelianlaptop.getValueAt(i, 1).toString()+"','"+tb_pembelianlaptop.getValueAt(i, 7).toString()+"','"+tb_pembelianlaptop.getValueAt(i,8)+"')";
                    st.executeUpdate(query1);
                    String query3 = "INSERT INTO tb_detail_barang VALUES ('"+tb_pembelianlaptop.getValueAt(i, 1)+"','"+tb_pembelianlaptop.getValueAt(i,3)+"','"+tb_pembelianlaptop.getValueAt(i,4)+"','"+tb_pembelianlaptop.getValueAt(i,5)+"','"+tb_pembelianlaptop.getValueAt(i,6)+"')";
                    st.executeUpdate(query3);
                try {
                    Connection c = KoneksiTR.getConnection();
                    //int baris1 = tabelTransaksi.getRowCount();
                    for (int j = 0; j < baris1; j++) {
                        String sql = "INSERT INTO tb_detail_penjualan VALUES('"+ tabelTransaksi.getValueAt(j, 0) +"','"+ tabelTransaksi.getValueAt(j, 1) +"','"+tabelTransaksi.getValueAt(j, 3)+"','"+tabelTransaksi.getValueAt(j, 5)+"')";
                        PreparedStatement p = c.prepareStatement(sql);
                        p.executeUpdate();
                        p.close();
                        }
                    try {
                        query = "INSERT INTO tb_tukar_tambah VALUES('"+txt_idtt.getText()+"','"+tb_pembelianlaptop.getValueAt(0, 0).toString()+"','"+id_penjualan +"','"+txt_totaltt.getText()+"','"+bayartt.getText()+"','"+kembaliantt.getText()+"')";
                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Transaksi Pembelian Berhasil di Input");
                        idlist.clear();
                        reset();
                        clear();
                        utama();
                        kosong();
                        idTransaksi_auto();
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Masukkan uang pembayaran");
        }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Masukkan Garansi");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "03 Anda belum Memilih Client");
        }
    }
    }//GEN-LAST:event_SelesaittMouseClicked

    private void txt_totalttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalttActionPerformed

    private void bayarttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarttActionPerformed
        int total, bayar, kembalian;

        total = Integer.valueOf(txt_totaltt.getText());
        bayar = Integer.valueOf(bayartt.getText());

        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            kembaliantt.setText(String.valueOf(kembalian));
        }
    }//GEN-LAST:event_bayarttActionPerformed

    private void txt_bayar2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayar2KeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){
    getToolkit().beep();
    evt.consume();
}
    }//GEN-LAST:event_txt_bayar2KeyTyped

    private void TabelJasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelJasaMouseClicked
        
    }//GEN-LAST:event_TabelJasaMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        int row = TableServis.getSelectedRow();
        int baris = TabelJasa.getRowCount();
        int rowjumlah = tabeltransaksiservice.getRowCount();
        String id = TableServis.getValueAt(row, 0).toString();
        String namac = TableServis.getValueAt(row, 1).toString();
        String namab = TableServis.getValueAt(row, 2).toString();
        String keluhan = TableServis.getValueAt(row, 3).toString();
        
        if (rowjumlah<1){
        try{
            String data []= {id,namac,namab,keluhan};
                modeltrs.addRow(data);
                totalBiayaService();
                txt_bayar2.setText("");
                txt_kembalian2.setText("");
            for (int i = 0; i < baris; i++) {
                String data2 [] = {TabelJasa.getValueAt(i, 0).toString(),TabelJasa.getValueAt(i, 1).toString()};
                modeltrsj.addRow(data2);
            }
        totalBiayaService();
            main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(jPanelTransaksiservice);
        main.repaint();
        main.revalidate();    
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Masukkan data dengan benar" );}
        
        }else {
            
            int respon = JOptionPane.showConfirmDialog(this, "Anda sudah memasukkan data \nApakan ingin mengganti dengan yang ini?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respon==JOptionPane.YES_OPTION){
               try{
                loadDatatransaksiservice();
                loadDatatransaksiservicejasa();   
                String data []= {id,namac,namab,keluhan};
                modeltrs.addRow(data);
                totalBiayaService();
                txt_bayar2.setText("");
                txt_kembalian2.setText("");
                for (int i = 0; i < baris; i++) {
                    String data2 [] = {TabelJasa.getValueAt(i, 0).toString(),TabelJasa.getValueAt(i, 1).toString()};
                    modeltrsj.addRow(data2);
            }
        totalBiayaService();
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(jPanelTransaksiservice);
        main.repaint();
        main.revalidate();    
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Masukkan data dengan benar" );} 
            }
        if(respon==JOptionPane.NO_OPTION){
            main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(jPanelTransaksiservice);
        main.repaint();
        main.revalidate();
        }
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void TableServisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableServisMouseClicked
        int row =TableServis.getSelectedRow();
        String servis =TableServis.getValueAt(row,0).toString();
        this.selectedServisID=servis;
        
        String [] judul = {"Nama Jasa","Harga"};
        model = new DefaultTableModel(judul,0);
        TabelJasa.setModel(model);
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            ResultSet rs = con.createStatement().executeQuery("SELECT * from tb_jasa where id_service ='"+servis+"'");
            while(rs.next()){
                String data [] = {rs.getString(2),rs.getString(3)};
                model.addRow(data);
                _settotalbeli();
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TableServisMouseClicked

    private void TambahJasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TambahJasaMouseClicked
        InputDataJasa a = new InputDataJasa();
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_TambahJasaMouseClicked

    private void hapusjasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusjasaMouseClicked
        int row = TabelJasa.getSelectedRow();
        int rows = TableServis.getSelectedRow();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pmproject","root","");
            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_jasa WHERE nama_jasa = '"+TabelJasa.getValueAt(row, 0)+"' AND id_service = '"+TableServis.getValueAt(rows, 0)+"'");
            ps.execute();
            JOptionPane.showMessageDialog(null,"Jasa berhasil dihapus");
            this.loadDataservis();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Tidak bisa dihapus");
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_hapusjasaMouseClicked

    private void TableReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableReturnMouseClicked
        int row =TableReturn.getSelectedRow();
        String retur =TableReturn.getValueAt(row,0).toString();
        this.selectedReturnID=retur;
    }//GEN-LAST:event_TableReturnMouseClicked

    private void cetak3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetak3MouseClicked
        // TODO add your handling code here:
        Cetak_LaporanTr_Penjualan a=new Cetak_LaporanTr_Penjualan();
        a.setVisible(true);

    }//GEN-LAST:event_cetak3MouseClicked

    private void tabelLaporanTRpembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelLaporanTRpembelianMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelLaporanTRpembelianMouseClicked

    private void cetak4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetak4MouseClicked
        Cetak_LaporanTr_Pembelian a=new Cetak_LaporanTr_Pembelian();
    }//GEN-LAST:event_cetak4MouseClicked

    private void cetak1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetak1MouseClicked
        Cetak_LaporanTr_Servis a=new Cetak_LaporanTr_Servis();
        a.setVisible(true);

    }//GEN-LAST:event_cetak1MouseClicked

    private void MoreinfoPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MoreinfoPenjualanMouseClicked
        //Remove panel
        MainTransaksi.removeAll();
        MainTransaksi.repaint();
        MainTransaksi.revalidate();
        //add panel
        MainTransaksi.add(panelTabelTRpenjualan);
        MainTransaksi.repaint();
        MainTransaksi.revalidate();
    }//GEN-LAST:event_MoreinfoPenjualanMouseClicked

    private void MoreinfoPembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MoreinfoPembelianMouseClicked
        //Remove panel
        MainTransaksi.removeAll();
        MainTransaksi.repaint();
        MainTransaksi.revalidate();
        //add panel
        MainTransaksi.add(panelTabelTRPembelian);
        MainTransaksi.repaint();
        MainTransaksi.revalidate();
    }//GEN-LAST:event_MoreinfoPembelianMouseClicked

    private void MoreinfoServisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MoreinfoServisMouseClicked
        //Remove panel
        MainTransaksi.removeAll();
        MainTransaksi.repaint();
        MainTransaksi.revalidate();
        //add panel
        MainTransaksi.add(panelTabelTRServis);
        MainTransaksi.repaint();
        MainTransaksi.revalidate();
    }//GEN-LAST:event_MoreinfoServisMouseClicked

    private void LaporanTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaporanTransaksiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LaporanTransaksiMouseClicked

    private void Sparepart0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sparepart0MouseClicked
        
        try {
        Laptop1.setVisible(false);
        Sparepart1.setVisible(true);
        Laptop0.setVisible(true);
         //remove panel
        jPanelPilihan1.removeAll();
        jPanelPilihan1.repaint();
        jPanelPilihan1.revalidate();
        //add panel
        jPanelPilihan1.add(belisparepart);
        jPanelPilihan1.repaint();
        jPanelPilihan1.revalidate();
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_Sparepart0MouseClicked

    private void Laptop0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laptop0MouseClicked
        
        try {
        Sparepart1.setVisible(false);
        Laptop1.setVisible(true);
        Sparepart0.setVisible(true);
        //remove panel
        jPanelPilihan1.removeAll();
        jPanelPilihan1.repaint();
        jPanelPilihan1.revalidate();
        //add panel
        jPanelPilihan1.add(belilaptop);
        jPanelPilihan1.repaint();
        jPanelPilihan1.revalidate();
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_Laptop0MouseClicked

    private void DataKlienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataKlienMouseClicked
        //Menampilkan panel data klien
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();

        //add panel
        main.add(jPanelDataclient);
        main.repaint();
        main.revalidate();
        loadDataClient();
    }//GEN-LAST:event_DataKlienMouseClicked

    private void DataKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataKaryawanMouseClicked
        //Menampilkan Panel data karyawan
        // Remove panell
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(jPanelDatakaryawan);
        main.repaint();
        main.revalidate();
        loadDataKaryawan();
    }//GEN-LAST:event_DataKaryawanMouseClicked

    private void DataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataBarangMouseClicked

        //Menampilkan panel Data Barang
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(jPanelDatabarang);
        main.repaint();
        main.revalidate();
        //memanggil Method tampilan tabel Laptop
        loadDataSparepart();
        //memanggil Method tampilan tabel Laptop
        loadDataLaptop();
    }//GEN-LAST:event_DataBarangMouseClicked

    private void ButtonReportTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonReportTransaksiMouseClicked
        jumlahpenjualan();
        jumlahpembelian();
        jumlahservis();
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(LaporanTransaksi);
        main.repaint();
    }//GEN-LAST:event_ButtonReportTransaksiMouseClicked

    private void ButtonReportBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonReportBarangMouseClicked
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(LaporanBarang);
        main.repaint();
        main.revalidate();
    }//GEN-LAST:event_ButtonReportBarangMouseClicked

    private void ButtonTrPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonTrPenjualanMouseClicked
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(jPanelTransaksiPenjualan);
        main.repaint();
        main.revalidate();
    }//GEN-LAST:event_ButtonTrPenjualanMouseClicked

    private void ButtonTrServisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonTrServisMouseClicked
        // TODO add your handling code here:
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(jPanelTransaksiservice);
        main.repaint();
        main.revalidate();
    }//GEN-LAST:event_ButtonTrServisMouseClicked

    private void ButtonnTrPembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonnTrPembelianMouseClicked
        // TODO add your handling code here:
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(jPanelTransaksiPembelian);
        main.repaint();
        main.revalidate();
    }//GEN-LAST:event_ButtonnTrPembelianMouseClicked

    private void ButtonnTrPembelian1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonnTrPembelian1MouseClicked
        // TODO add your handling code here:
        //Remove panel
        main.removeAll();
        main.repaint();
        main.revalidate();
        //add panel
        main.add(jPanelTransaksiTukarTambah);
        main.repaint();
        main.revalidate();
    }//GEN-LAST:event_ButtonnTrPembelian1MouseClicked

    private void txt_idpenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idpenjualanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idpenjualanActionPerformed

    private void kembalianttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalianttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembalianttActionPerformed

    private void cetaklaporanlaptopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetaklaporanlaptopMouseClicked
        try {
            java.sql.Connection conn = (Connection)KoneksiTR.getConnection();
            InputStream report = getClass().getResourceAsStream("Report barang laptop.jasper");
            HashedMap hash =new HashedMap();
            JasperPrint jp = JasperFillManager.fillReport(report,hash , conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_cetaklaporanlaptopMouseClicked

    private void cetaklaporanSparepartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetaklaporanSparepartMouseClicked
        try {
            java.sql.Connection conn = (Connection)KoneksiTR.getConnection();
            InputStream report = getClass().getResourceAsStream("Report barang sparepart.jasper");
            HashedMap hash =new HashedMap();
            JasperPrint jp = JasperFillManager.fillReport(report,hash , conn);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_cetaklaporanSparepartMouseClicked

    private void Laptop3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laptop3MouseClicked
        try {
        Sparepart2.setVisible(false);
        Laptop2.setVisible(true);
        Sparepart3.setVisible(true);
        //remove panel
        jPanelPilihan3.removeAll();
        jPanelPilihan3.repaint();
        jPanelPilihan3.revalidate();
        //add panel
        jPanelPilihan3.add(baranglaptop3);
        jPanelPilihan3.repaint();
        jPanelPilihan3.revalidate();
        } catch (Exception e){
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_Laptop3MouseClicked

    private void Sparepart3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sparepart3MouseClicked
        try {
        Laptop2.setVisible(false);
        Sparepart2.setVisible(true);
        Laptop3.setVisible(true);
        //remove panel
        jPanelPilihan3.removeAll();
        jPanelPilihan3.repaint();
        jPanelPilihan3.revalidate();
        //add panel
        jPanelPilihan3.add(barangsparepart3);
        jPanelPilihan3.repaint();
        jPanelPilihan3.revalidate();
        } catch (Exception e){
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_Sparepart3MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        RiwayatReturn b = new RiwayatReturn();
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        RiwayatService b = new RiwayatService();
        b.setVisible(true);
        b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void Laptop5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laptop5MouseClicked
        try {
        Sparepart4.setVisible(false);
        Laptop4.setVisible(true);
        Sparepart5.setVisible(true);
        //remove panel
        jPanelPilihan.removeAll();
        jPanelPilihan.repaint();
        jPanelPilihan.revalidate();
        //add panel
        jPanelPilihan.add(jPanelLaptop);
        jPanelPilihan.repaint();
        jPanelPilihan.revalidate();
        loadDataLaptop();
        } catch (Exception e){
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_Laptop5MouseClicked

    private void Sparepart5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sparepart5MouseClicked
        try {
        Laptop4.setVisible(false);
        Sparepart4.setVisible(true);
        Laptop5.setVisible(true);
        //remove panel
        jPanelPilihan.removeAll();
        jPanelPilihan.repaint();
        jPanelPilihan.revalidate();
        //add panel
        jPanelPilihan.add(jPanelSparePart);
        jPanelPilihan.repaint();
        jPanelPilihan.revalidate();
        loadDataSparepart();
        } catch (Exception e){
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_Sparepart5MouseClicked


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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bayar;
    private javax.swing.JLabel Bayar2;
    private javax.swing.JLabel ButtonReportBarang;
    private javax.swing.JLabel ButtonReportTransaksi;
    private javax.swing.JLabel ButtonTrPenjualan;
    private javax.swing.JLabel ButtonTrServis;
    private javax.swing.JLabel ButtonnTrPembelian;
    private javax.swing.JLabel ButtonnTrPembelian1;
    private javax.swing.JLabel Cari;
    private javax.swing.JLabel Cari1;
    private javax.swing.JLabel Cari2;
    private javax.swing.JLabel Cari4;
    private javax.swing.JLabel Cari5;
    private javax.swing.JLabel Cari6;
    private javax.swing.JLabel DataBarang;
    private javax.swing.JLabel DataKaryawan;
    private javax.swing.JLabel DataKlien;
    private javax.swing.JPanel DataMaster;
    private javax.swing.JLabel EditDataKlien;
    private javax.swing.JLabel EditDatakaryawan;
    private javax.swing.JLabel EditLaptop;
    private javax.swing.JLabel EditLaptop1;
    private javax.swing.JLabel EditReturn;
    private javax.swing.JLabel EditSparePart;
    private javax.swing.JLabel Editbeli;
    private javax.swing.JLabel Editbeli1;
    private javax.swing.JLabel Editbeli4;
    private javax.swing.JLabel GambarPembelian;
    private javax.swing.JLabel GambarPenjualan;
    private javax.swing.JLabel GambarServis;
    private javax.swing.JLabel Garansi;
    private javax.swing.JLabel Hapus;
    private javax.swing.JLabel Hapus2;
    private javax.swing.JLabel HapusDataKaryawan;
    private javax.swing.JLabel HapusDataKlien;
    private javax.swing.JLabel HapusLaptop;
    private javax.swing.JLabel HapusLaptop1;
    private javax.swing.JLabel HapusReturn;
    private javax.swing.JLabel HapusSparePart;
    private javax.swing.JLabel Hapusbeli;
    private javax.swing.JLabel Hapusbeli1;
    private javax.swing.JLabel Hapusbeli4;
    private javax.swing.JLabel Hapusjual;
    private javax.swing.JPanel Header;
    private javax.swing.JLabel Home;
    private javax.swing.JLabel Jumlah2;
    public static final javax.swing.JLabel JumlahPembelian = new javax.swing.JLabel();
    public static final javax.swing.JLabel JumlahPenjualan = new javax.swing.JLabel();
    public static final javax.swing.JLabel JumlahServis = new javax.swing.JLabel();
    private javax.swing.JLabel Kembalian;
    private javax.swing.JLabel Kembalian2;
    private javax.swing.JPanel Laporan;
    private javax.swing.JPanel LaporanBarang;
    private javax.swing.JPanel LaporanTransaksi;
    private javax.swing.JLabel Laptop0;
    private javax.swing.JLabel Laptop1;
    private javax.swing.JLabel Laptop2;
    private javax.swing.JLabel Laptop3;
    private javax.swing.JLabel Laptop4;
    private javax.swing.JLabel Laptop5;
    private javax.swing.JPanel MainTransaksi;
    private javax.swing.JLabel MoreinfoPembelian;
    private javax.swing.JLabel MoreinfoPenjualan;
    private javax.swing.JLabel MoreinfoServis;
    private javax.swing.JLabel PilihBarang;
    private javax.swing.JLabel PilihBarang2;
    private javax.swing.JLabel PilihKlien;
    private javax.swing.JLabel PilihKlien2;
    private javax.swing.JLabel Pilihclient1;
    private javax.swing.JLabel Pilihclient4;
    private javax.swing.JPanel Retur;
    private javax.swing.JScrollPane ScrollpanelLaporanpembelian;
    private javax.swing.JScrollPane ScrollpanelLaporanpenjualan;
    private javax.swing.JScrollPane ScrollpanelLaporanservis;
    private javax.swing.JLabel SelesaiReturn;
    private javax.swing.JLabel Selesaibeli;
    private javax.swing.JLabel Selesaibeli1;
    private javax.swing.JLabel Selesaitt;
    private javax.swing.JLabel Service;
    private javax.swing.JPanel Servis;
    private javax.swing.JLabel Simpan;
    private javax.swing.JLabel Simpan2;
    private javax.swing.JLabel Sparepart0;
    private javax.swing.JLabel Sparepart1;
    private javax.swing.JLabel Sparepart2;
    private javax.swing.JLabel Sparepart3;
    private javax.swing.JLabel Sparepart4;
    private javax.swing.JLabel Sparepart5;
    public static javaswingdev.swing.table.Table TabelJasa;
    public static javaswingdev.swing.table.Table TableReturn;
    public static javaswingdev.swing.table.Table TableServis;
    private javax.swing.JLabel TambahDataKlien;
    private javax.swing.JLabel TambahDatakaryawan;
    private javax.swing.JLabel TambahJasa;
    private javax.swing.JLabel TambahLaptop;
    private javax.swing.JLabel TambahLaptop1;
    private javax.swing.JLabel TambahReturn;
    private javax.swing.JLabel TambahSparePart;
    private javax.swing.JLabel Tambahbeli;
    private javax.swing.JLabel Tambahbeli1;
    private javax.swing.JLabel Tambahbeli4;
    private javax.swing.JLabel Tambahjual;
    private javax.swing.JLabel Time;
    private javax.swing.JLabel Total;
    private javax.swing.JLabel Total2;
    private javax.swing.JPanel Transaksi;
    private javax.swing.JLabel back;
    private javax.swing.JLabel backgroundMaster;
    private javax.swing.JLabel backgroundSide;
    private javax.swing.JPanel baranglaptop3;
    private javax.swing.JPanel barangsparepart3;
    private javax.swing.JTextField bayartt;
    private javax.swing.JPanel belilaptop;
    private javax.swing.JPanel belisparepart;
    private javax.swing.JLabel cetak;
    private javax.swing.JLabel cetak1;
    private javax.swing.JLabel cetak2;
    private javax.swing.JLabel cetak3;
    private javax.swing.JLabel cetak4;
    private javax.swing.JLabel cetaklaporanSparepart;
    private javax.swing.JLabel cetaklaporanlaptop;
    private com.raven.chart.Chart chart;
    private javax.swing.JLabel gLaporan;
    private javax.swing.JLabel gMenu;
    private javax.swing.JLabel gRetur;
    private javax.swing.JLabel gServis;
    private javax.swing.JLabel gTransaksi;
    private javax.swing.JTextField garansitt;
    private javax.swing.JLabel gdDatamaster;
    private javax.swing.JLabel hapusjasa;
    private javax.swing.JPanel home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelDatabarang;
    private javax.swing.JPanel jPanelDataclient;
    private javax.swing.JPanel jPanelDatakaryawan;
    private javax.swing.JPanel jPanelLaptop;
    private javax.swing.JPanel jPanelPilihan;
    private javax.swing.JPanel jPanelPilihan1;
    private javax.swing.JPanel jPanelPilihan3;
    private javax.swing.JPanel jPanelSparePart;
    private javax.swing.JPanel jPanelTransaksiPembelian;
    private javax.swing.JPanel jPanelTransaksiPenjualan;
    private javax.swing.JPanel jPanelTransaksiTukarTambah;
    private javax.swing.JPanel jPanelTransaksiservice;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane1servis;
    private javax.swing.JScrollPane jScrollPane2;
    public static final javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
    public static final javax.swing.JScrollPane jScrollPane4 = new javax.swing.JScrollPane();
    public static final javax.swing.JScrollPane jScrollPane5 = new javax.swing.JScrollPane();
    public static final javax.swing.JScrollPane jScrollPane6 = new javax.swing.JScrollPane();
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JScrollPane jScrollPanel11;
    private javax.swing.JScrollPane jScrollPanel2;
    private javax.swing.JScrollPane jScrollPanel3;
    public static final javax.swing.JScrollPane jScrollPanel4 = new javax.swing.JScrollPane();
    private javax.swing.JScrollPane jScrollPanel5;
    private javax.swing.JScrollPane jScrollPanel6;
    private javax.swing.JScrollPane jScrollPanel8;
    private javax.swing.JScrollPane jScrollPanel9;
    public static final javaswingdev.swing.table.Table jTableLaptop = new javaswingdev.swing.table.Table();
    public static final javaswingdev.swing.table.Table jTableSparePart = new javaswingdev.swing.table.Table();
    private javax.swing.JTextField kembaliantt;
    private javax.swing.JLabel laporan;
    private javax.swing.JPanel main;
    private javax.swing.JPanel panelTabelTRPembelian;
    private javax.swing.JPanel panelTabelTRServis;
    private javax.swing.JPanel panelTabelTRpenjualan;
    private javax.swing.JPanel pilihanbeli;
    private javax.swing.JPanel pilihanbeli2;
    private javax.swing.JLabel retur;
    private javax.swing.JLabel servis;
    private javax.swing.JPanel side;
    private static final javaswingdev.swing.table.Table tabelKaryawan = new javaswingdev.swing.table.Table();
    public static final javaswingdev.swing.table.Table tabelLaporanServis = new javaswingdev.swing.table.Table();
    public static final javaswingdev.swing.table.Table tabelLaporanTRpembelian = new javaswingdev.swing.table.Table();
    public static final javaswingdev.swing.table.Table tabelLaporanpenjualan = new javaswingdev.swing.table.Table();
    public static final javaswingdev.swing.table.Table tabelTransaksi = new javaswingdev.swing.table.Table();
    public static final javaswingdev.swing.table.Table tabelklien = new javaswingdev.swing.table.Table();
    private javax.swing.JLabel tabelmaster;
    public static javaswingdev.swing.table.Table tabeltransaksiservice;
    public static javaswingdev.swing.table.Table tabeltransaksiservicejasa;
    private static javaswingdev.swing.table.Table tb_laporanlaptop;
    private static javaswingdev.swing.table.Table tb_laporansparepart;
    public static final javaswingdev.swing.table.Table tb_pembelianlaptop = new javaswingdev.swing.table.Table();
    public static final javaswingdev.swing.table.Table tb_pembeliansparepart = new javaswingdev.swing.table.Table();
    public static javax.swing.JTable tb_pilihclientbelilaptop;
    public static javax.swing.JTable tb_pilihclientbelisparepart;
    private javax.swing.JLabel transaksi;
    public static final javaswingdev.swing.table.Table ttbeli = new javaswingdev.swing.table.Table();
    public static final javaswingdev.swing.table.Table ttjual = new javaswingdev.swing.table.Table();
    public static final javax.swing.JTextField txtCetak = new javax.swing.JTextField();
    public static javax.swing.JTextField txt_bayar;
    public static javax.swing.JTextField txt_bayar2;
    private javax.swing.JTextField txt_cariKaryawan;
    private javax.swing.JTextField txt_cariKlien;
    private javax.swing.JTextField txt_cariLaptop;
    private javax.swing.JTextField txt_cariReturn;
    private javax.swing.JTextField txt_cariServis;
    private javax.swing.JTextField txt_cariSparePart;
    public static javax.swing.JTextField txt_garansi;
    public static javax.swing.JTextField txt_harga;
    public static javax.swing.JTextField txt_id_barang;
    public static javax.swing.JTextField txt_id_clien;
    public static final javax.swing.JTextField txt_idkaryawan = new javax.swing.JTextField();
    public static javax.swing.JTextField txt_idpenjualan;
    public static javax.swing.JTextField txt_idtrservice;
    private static javax.swing.JTextField txt_idtt;
    public static final javax.swing.JTextField txt_jumlah = new javax.swing.JTextField();
    private static javax.swing.JTextField txt_kembalian;
    public static javax.swing.JTextField txt_kembalian2;
    public static final javax.swing.JTextField txt_level = new javax.swing.JTextField();
    public static javax.swing.JTextField txt_namabarang;
    private javax.swing.JTextField txt_tanggalTransaksi;
    private javax.swing.JTextField txt_tanggalTransaksiservice;
    public static final javax.swing.JTextField txt_total = new javax.swing.JTextField();
    public static final javax.swing.JTextField txt_total2 = new javax.swing.JTextField();
    public static javax.swing.JTextField txt_totalbeli;
    public static javax.swing.JTextField txt_totalbeli1;
    public static javax.swing.JTextField txt_totaltt;
    // End of variables declaration//GEN-END:variables
}
