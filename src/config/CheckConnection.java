/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YOGA SAPUTRA
 */
public class CheckConnection {
    private static boolean isConnected(){
        try {
            ConnectionHelper.getConnection();
            System.out.println("Database Connected");
            return true;
        } catch (SQLException e) {
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE,null,e);
            System.out.println("Database not Connected");
            return false;
        }
    }
        public static void showbook(){
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from buku");
            while (rs.next()) {                
                System.out.println("ID buku :"+rs.getString("id_buku")+" Judul buku :"+rs.getString("judul_buku")
                +" Pengarang :"+rs.getString("Pengarang")+" Tahun terbit :"+rs.getString("tahun_terbit"));
            }
        } catch (SQLException e) {
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE,null,e);
        }
    } 
    public static void main(String[] args) {
        isConnected();
        showbook();
    }
}
