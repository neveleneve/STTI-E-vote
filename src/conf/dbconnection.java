/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import javax.swing.*;

public class dbconnection {
    public Connection koneksi;
    public Connection connect(){
    //koneksi ke database
    try{
        //ubah ip address sesuai server
        String url = "jdbc:mysql://192.168.100.87/votingapp";
        koneksi= DriverManager.getConnection(url,"rootvoting","");
        System.out.println("Berhasil Koneksi Ke Database");
    }catch(SQLException e){
        System.out.println("Tidak Berhasil Koneksi Ke Database");
    }
    return koneksi;
    }
    public static void main(String [] args){
        java.sql.Connection conn = new dbconnection().connect();
    }
}