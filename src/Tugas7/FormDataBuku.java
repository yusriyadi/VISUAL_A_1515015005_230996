/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tugas7;
import Tugas6.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author usery
 */
public class FormDataBuku extends javax.swing.JFrame {

        private DefaultTableModel model; //mendeklarasikan tabel dengan nama model
        private Connection con =koneksi.getConnection(); // membuat koneksi dari method getconnection
        private Statement stt;
        private ResultSet rss;
        private int baris;
        private boolean cekbuku=true;
    /**
     * Creates new form FormDataBuku
     */
    public FormDataBuku() {
        initComponents();
    }
     private void InitTable(){
       model =new DefaultTableModel();  //mendefinisikan isi record tabel model
       model.addColumn("ID");
       model.addColumn("JUDUL");
       model.addColumn("Penulis");
       model.addColumn("HARGA");
       
       jTable1.setModel(model); //memasukan data model ke jtable1
       }
private void TampilData()
{
    try{
        String sql ="SELECT * FROM buku";
                stt =con.createStatement(); //membuat statment dari koneksi
                rss =stt.executeQuery(sql); //mengesekusi queri dan dengan mengecek koneksinya
                
                while(rss.next()) //fetching isi dari rss (hasil dari esekusi sql) kedalam model tabel 
                {
                Object[] o = new Object[4];    
                o[0]=rss.getString("id");
                o[1]=rss.getString("judul");
                o[2]=rss.getString("penulis");
                o[3]=rss.getString("harga");
                model.addRow(o); //memasukan isi array ke model
                }      
        }catch(SQLException e){
                System.out.println(e.getMessage());
            }
}
private void TambahData(String judul,String penulis ,String harga){         //method tambah data
    try{
        String sql="insert into buku values (NULL,'"+judul+"','"+penulis+"',"+harga+")"; //penulisan query
    stt=con.createStatement();
    stt.executeUpdate(sql);  //esekusi query
    //model.addRow(new Object[]{judul,penulis,harga});    
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    }
    
}
private void HapusData(String id, int baris) //methon hapus data
    {
        try{
        String sqldelete="delete from buku where id='"+id+"'"; //penulisan query penghapusan data berdasar id
        stt= con.createStatement();
        stt.executeUpdate(sqldelete); //esekusi query delete
        model.removeRow(baris);     //mengilangkan baris pada model dengan parameter baris
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
}
private void UbahData(String judul,String penulis, String harga, String id){
        try {
            
            String sql = "UPDATE buku SET "
                         + "judul='"+judul+"',"
                         + "penulis='"+penulis+"',"
                         + "harga='"+harga+"'"
                         + "WHERE id='"+id+"'"; //query update data berdasar id
            stt = con.createStatement();
            stt.executeUpdate(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

private void cari(){
        try {
            String sql = "SELECT * FROM buku where judul='"+caritxt.getText()+"'|| penulis='"+caritxt.getText()+"'|| harga='"+caritxt.getText()+"'"; //pencarian data berdasar judul||penulis||harga
            stt = con.createStatement(); //koneksi
            rss = stt.executeQuery(sql);    //esekusi query
            
            while(rss.next()){
                Object[] o = new Object[4];
                o[0] = rss.getInt("id");
                o[1] = rss.getString("judul");
                o[2] = rss.getString("penulis");
                o[3] = rss.getString("harga");
                model.addRow(o); //mengisi ke model
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
private void validasi (String judul, String penulis, String harga){ //membuatfungsi validasi
        try {
            String sql = "SELECT * FROM buku"; //mengeluarkan isi tabele buku 
            stt = con.createStatement(); 
            rss = stt.executeQuery(sql); //esekusi query
            while(rss.next()){ //mengeluarkan seluruh data
                Object[] o = new Object[2];
                o[0] = rss.getString("judul").toLowerCase(); //masukan ke array dan rubah ke huruf kecil
                o[1] = rss.getString("penulis").toLowerCase(); //idem
                
                if( o[0].equals(judul.toLowerCase()) && o[1].equals(penulis.toLowerCase())){ //cocokan data buku dengan parameter in validasi
                    JOptionPane.showMessageDialog(null,"Data telah ada");
                    cekbuku=false; //set cek buku=false
                    break;
                }
            }
            if(cekbuku==true){
                TambahData(judul, penulis, harga); //jika buku tidak sama(cekbuku=true) tambahkan data 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        judul1 = new javax.swing.JTextField();
        harga1 = new javax.swing.JTextField();
        penulis1 = new javax.swing.JComboBox<>();
        simpan = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        caritxt = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        tampil = new javax.swing.JButton();
        ulang = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel4.setFont(new java.awt.Font("Geometr415 Blk BT", 0, 18)); // NOI18N
        jLabel4.setText("FORM DATA BUKU");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(145, 145, 145))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setText("judul");

        jLabel2.setText("penulis");

        jLabel3.setText("Harga");

        penulis1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yusriyadi", "Tere Liye", "W.S. Rendra", "Felix Siauw", "Asma nadia", "dewi lestari" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(harga1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(judul1)
                            .addComponent(penulis1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(judul1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(penulis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(harga1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        ubah.setText("ubah");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        hapus.setText("hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        keluar.setText("keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        caritxt.setToolTipText("cari berdasar nama/penulis/harga");

        cari.setText("cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        tampil.setText("tampilkan seluruh data");
        tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tampilActionPerformed(evt);
            }
        });

        ulang.setText("ulangi");
        ulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ulangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ulang, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(caritxt, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cari)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tampil)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpan)
                    .addComponent(ubah)
                    .addComponent(hapus)
                    .addComponent(keluar)
                    .addComponent(ulang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(caritxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari)
                    .addComponent(tampil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        caritxt.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        InitTable();
        TampilData();
    }//GEN-LAST:event_formComponentShown

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
          if(judul1.getText().equals("") && harga1.getText().equals("")) //kondisi untuk mengecek inputan text field kosong atau tidak
     {
           JOptionPane.showMessageDialog(null, "Data Belum Lengkap","Warning !!!!",JOptionPane.INFORMATION_MESSAGE);
           judul1.requestFocus(); //program akan request ke judul1
     } else{
        String judul = judul1.getText(); 
        String penulis = penulis1.getSelectedItem().toString();
        String harga = harga1.getText();
        
        validasi(judul, penulis, harga); ///jalankan method validasi
        
        InitTable(); //init jTable
        TampilData(); //tampilkan data
     }
    }//GEN-LAST:event_simpanActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_keluarActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        String id = jTable1.getValueAt(baris, 0 ).toString(); //hapus isi kolom berdasar baris yg dipilih pada jTable1
        HapusData(id, baris); //jalankan method hapusdata
    }//GEN-LAST:event_hapusActionPerformed

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        try {
        int baris = jTable1.getSelectedRow();
        
        jTable1.setValueAt(judul1.getText(),baris,1);  // men set isi record pada tabel 
        jTable1.setValueAt(penulis1.getSelectedItem(),baris,2); //idem
        jTable1.setValueAt(harga1.getText(),baris,3);   //idem
        
        String judul=jTable1.getValueAt(baris,1).toString(); //menset nilai ke dalam variabel yg akan dimasuka nsebagai parameter untuk method ubah data
        String penulis=jTable1.getValueAt(baris,2).toString(); //idem
        String harga=jTable1.getValueAt(baris,3).toString();    //idem
        String id=jTable1.getValueAt(baris,0).toString();   //idem
        
        
        judul1.setText(judul);   //men set isi jTextfield berdasar record yg terpilih dari baris pada tabel
        penulis1.setSelectedItem(penulis); //idem
        harga1.setText(harga);  //idem
        
        UbahData(judul,penulis,harga,id);  //pemanggilan method ubah data  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Data tidak dipilih","Warning !!!!",JOptionPane.INFORMATION_MESSAGE);
        }
 
    }//GEN-LAST:event_ubahActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        model.getDataVector().removeAllElements(); //menghilangkan isi tabel ketika button cari di tekan
        model.fireTableDataChanged();   //idem
        cari(); //pemangggilan meton cari
        tampil.setVisible(true); //menampilkan button tampil yg berfungsi menampilkan isi tabel.
    }//GEN-LAST:event_cariActionPerformed

    private void tampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tampilActionPerformed
       InitTable(); //menampilka tabel1 dari nilai yg didapat dari model
       TampilData(); //pemanggilan method tampildata
        tampil.setVisible(false); //menghilangkan button tampil
        caritxt.setText("");    //reseet textfield cari
    }//GEN-LAST:event_tampilActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil.setVisible(false); //menghilangkan button tampil
        caritxt.setText("");    //clear text textfield cari
    }//GEN-LAST:event_formWindowOpened

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         int baris = jTable1.getSelectedRow();  //action untuk memilih baris pada table
        
        String judul=jTable1.getValueAt(baris,1).toString();
        String penulis=jTable1.getValueAt(baris,2).toString();
        String harga=jTable1.getValueAt(baris,3).toString();
        String id=jTable1.getValueAt(baris,0).toString();
        
        
        judul1.setText(judul);
        penulis1.setSelectedItem(penulis);
        harga1.setText(harga);
    }//GEN-LAST:event_jTable1MouseClicked

    private void ulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ulangActionPerformed
         judul1.setText(""); //reset textfield
        harga1.setText(""); //idem
    }//GEN-LAST:event_ulangActionPerformed

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
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDataBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cari;
    private javax.swing.JTextField caritxt;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField harga1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField judul1;
    private javax.swing.JButton keluar;
    private javax.swing.JComboBox<String> penulis1;
    private javax.swing.JButton simpan;
    private javax.swing.JButton tampil;
    private javax.swing.JButton ubah;
    private javax.swing.JButton ulang;
    // End of variables declaration//GEN-END:variables
}
