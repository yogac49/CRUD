package Perpustakaan.model;


import Perpustakaan.model.Buku;
import config.CheckConnection;
import config.ConnectionHelper;
//import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import Perpustakaan.ui.ShowBookJframe;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

public class BukuManager {
    
        public static List<Buku> showAllBook() {
            List<Buku> bukuList = new ArrayList<>();
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from buku");
            while (rs.next()) {                
                Buku buku = new Buku(); 
                buku.setId_buku(Integer.parseInt(rs.getString("id_buku")));
                buku.setJudul_buku(rs.getString("judul_buku"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPengarang(rs.getString("pengarang"));
                buku.setTahun_terbit(Integer.parseInt(rs.getString("tahun_terbit")));
                
                bukuList.add(buku);
            }  
        } catch (SQLException e) {
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE,null,e);
        }
    return bukuList;
    }   
          public static Buku showbook(int id) throws SQLException{
       Connection conn = ConnectionHelper.getConnection();
       Buku buku = new Buku();
       
       PreparedStatement patm = conn.prepareStatement("Select * from buku where id_buku =?");
               patm.setInt(1, id);
               ResultSet rs = patm.executeQuery();
               
               while (rs.next()) {           
           buku.setId_buku(Integer.parseInt(rs.getString("id_buku")));
           buku.setJudul_buku(rs.getString("judul_buku"));
           buku.setPenerbit(rs.getString("penerbit"));
           buku.setPengarang(rs.getString("pengarang"));
           buku.setTahun_terbit(Integer.valueOf(rs.getString("tahun_terbit")));
       }
               return buku;
   }
             public static void updateBook(Buku buku) throws SQLException{
                 try{
        Connection conn = ConnectionHelper.getConnection();
        PreparedStatement ptam = conn.prepareStatement("UPDATE  buku SET judul_buku =?,pengarang = ?, penerbit=?, tahun_terbit = ? WHERE id_buku=?");
        ptam.setString(1, buku.getJudul_buku());
        ptam.setString(2, buku.getPengarang());
        ptam.setString(3, buku.getPenerbit());  
        ptam.setInt(4, buku.getTahun_terbit());
        ptam.setInt(5, buku.getId_buku());
       int option = ptam.executeUpdate();
         if (option>0) { 
                         JOptionPane.showMessageDialog(null,"data berhasil diubah");
                         ShowBookJframe.loadbook();
                     }else{
                         JOptionPane.showMessageDialog(null,"data data gagal diubah");
                     }
                 }catch(SQLException e){
                     System.out.println(e);          
    }
             }
             public static boolean insertBook(Buku buku) throws SQLException{
               PreparedStatement insertBook = null;
                 try {
                     Connection conn = ConnectionHelper.getConnection();
                     insertBook = conn.prepareStatement("INSERT INTO buku(judul_buku,pengarang , penerbit, tahun_terbit)" +
                            " values (?,?,?,?)");
                    
                     insertBook.setString(1, buku.getJudul_buku());
                     insertBook.setString(2, buku.getPengarang());
                     insertBook.setString(3, buku.getPenerbit());
                     insertBook.setInt(4, buku.getTahun_terbit());
                     int i = insertBook.executeUpdate();
                     if (i>0) { 
                         JOptionPane.showMessageDialog(null,"data tersimpan");
                         ShowBookJframe.loadbook();
                         return true;
                     }else{
                         JOptionPane.showMessageDialog(null,"data tidak tersimpan");
                         return false ;
                     }
                     
                 } catch (SQLException e) {
                     e.printStackTrace();
                     
                 }finally{
                     insertBook.close();
                 }
               return true;
             }
             
             public static void delbook(Buku buku) throws SQLException{
                 Connection conn = ConnectionHelper.getConnection();
                 PreparedStatement pstm = conn.prepareStatement("DELETE FROM buku WHERE id_buku=?");
                 
                 int Rowsdel = pstm.executeUpdate();
                 if (Rowsdel > 0) {
                     System.out.println("data berhasil di hapus");
                 }
             }
}
      
     



