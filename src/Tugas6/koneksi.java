package Tugas6;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author usery
 */
public class koneksi {
    private static Connection con;
    public static Connection getConnection(){
    
    try{
        con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/praktikum_visual","root","");
        JOptionPane.showMessageDialog(null,"koneksi berhasil");
    }
    catch(SQLException e)
    {
        JOptionPane.showMessageDialog(null,"koneksi gagal" +e.getMessage());  
    }
    return con;
    }
}
