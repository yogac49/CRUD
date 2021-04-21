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
      
     



