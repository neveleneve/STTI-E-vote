/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import form.getIPServer;
//import javax.swing.*;

public class dbconnection {

    public Connection koneksi;

    public Connection connect(String ip) {
        try {
            //ubah ip address sesuai server
            String url = "jdbc:mysql://" + ip + "/votingapp";
            koneksi = DriverManager.getConnection(url, "root", "");
            System.out.println("Berhasil Koneksi Ke Database");
        } catch (SQLException e) {
            System.out.println("Tidak Berhasil Koneksi Ke Database");
        }
        return koneksi;
    }

    public static void main(String[] args) {
        String ip = getIPServer.IPaddress;
        java.sql.Connection conn = new dbconnection().connect(ip);
    }
}
