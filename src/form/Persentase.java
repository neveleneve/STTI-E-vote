package form;

import conf.dbconnection;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NEVE
 */
public final class Persentase extends javax.swing.JFrame {

    /**
     * Creates new form Persentase
     */
    public Persentase() {
        initComponents();
        namacalon1();
        namacalon2();
        jumlahsuara();
        progressbar1();
        progressbar2();
    }
    String ip = getIPServer.IPaddress;
    public Connection conn = new dbconnection().connect(ip);
    public String namacalon1, namacalon2;
    public static Double totalsuara, jumsuara1, jumsuara2;
    public DecimalFormat df = new DecimalFormat("#0.00");

    public void namacalon1() {
        try {
            PreparedStatement idstate = conn.prepareStatement("select * from hasilvote where id = 1");
            ResultSet idrs = idstate.executeQuery();
            if (idrs.next()) {
                namacalon1 = idrs.getString("nama");
                jLabel1.setText(namacalon1);
                byte[] img = idrs.getBytes("gambar");
                ImageIcon myimg = new ImageIcon(img);
                Image img1 = myimg.getImage();
                Image img2 = img1.getScaledInstance(lbFotoPaslon1.getWidth(), lbFotoPaslon1.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon i = new ImageIcon(img2);
                lbFotoPaslon1.setText("");
                lbFotoPaslon1.setIcon(i);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void progressbar1() {
        jProgressBar1.setValue(0);
        try {
            PreparedStatement idstate = conn.prepareStatement("select jumlahsuara from hasilvote where id = 1");
            ResultSet idrs = idstate.executeQuery();
            if (idrs.next()) {
                jumsuara1 = idrs.getDouble("jumlahsuara");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        double persen1 = (jumsuara1 / totalsuara) * 100;
        jProgressBar1.setValue(jumsuara1.intValue());
        jProgressBar1.setMaximum(totalsuara.intValue());
        String persenan1 = Double.toString(persen1);
        if ("NaN".equals(persenan1)) {
            persen1 = 0.0;
        } else {

        }
        jProgressBar1.setString(String.format("%,.2f", persen1) + " %");
        System.out.println("Persen Paslon 1 : " + persen1);
    }

    public void namacalon2() {
        try {
            PreparedStatement idstate = conn.prepareStatement("select * from hasilvote where id = 2");
            ResultSet idrs = idstate.executeQuery();
            if (idrs.next()) {
                namacalon2 = idrs.getString("nama");
                jLabel2.setText(namacalon2);
                byte[] img = idrs.getBytes("gambar");
                ImageIcon myimg = new ImageIcon(img);
                Image img1 = myimg.getImage();
                Image img2 = img1.getScaledInstance(lbFotoPaslon2.getWidth(), lbFotoPaslon2.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon i = new ImageIcon(img2);
                lbFotoPaslon2.setText("");
                lbFotoPaslon2.setIcon(i);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void progressbar2() {
        jProgressBar2.setValue(0);
        try {
            PreparedStatement idstate = conn.prepareStatement("select jumlahsuara from hasilvote where id = 2");
            ResultSet idrs = idstate.executeQuery();
            if (idrs.next()) {
                jumsuara2 = idrs.getDouble("jumlahsuara");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        double persen2 = (jumsuara2 / totalsuara) * 100;
        jProgressBar2.setValue(jumsuara2.intValue());
        jProgressBar2.setMaximum(totalsuara.intValue());
        String persenan2 = Double.toString(persen2);
        if ("NaN".equals(persenan2)) {
            persen2 = 0.0;
        } else {

        }
        jProgressBar2.setString(String.format("%,.2f", persen2) + " %");
        System.out.println("Persen Paslon 2 : " + persen2);
    }

    public void jumlahsuara() {
        totalsuara = 0.0;
        try {
            PreparedStatement idstate = conn.prepareStatement("select sum(jumlahsuara) as a from hasilvote");
            ResultSet idrs = idstate.executeQuery();
            if (idrs.next()) {
                totalsuara = idrs.getDouble("a");
                jLabel3.setText("Total Suara Masuk : " + String.format("%,.0f", totalsuara));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        lbFotoPaslon2 = new javax.swing.JLabel();
        lbFotoPaslon1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total Suara Masuk :");
        jLabel3.setFocusable(false);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 1366, 130));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(247, 202, 24));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pasangan No 2");
        jLabel2.setFocusable(false);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, 370, 130));

        jProgressBar2.setBackground(new java.awt.Color(255, 255, 0));
        jProgressBar2.setForeground(new java.awt.Color(255, 255, 0));
        jProgressBar2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jProgressBar2.setFocusable(false);
        jProgressBar2.setStringPainted(true);
        jPanel1.add(jProgressBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, 370, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 202, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pasangan No 1");
        jLabel1.setFocusable(false);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 360, 130));

        jProgressBar1.setBackground(new java.awt.Color(255, 0, 0));
        jProgressBar1.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jProgressBar1.setFocusable(false);
        jProgressBar1.setStringPainted(true);
        jPanel1.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 220, 360, 50));

        lbFotoPaslon2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbFotoPaslon2.setForeground(new java.awt.Color(255, 255, 255));
        lbFotoPaslon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFotoPaslon2.setText("FOTO");
        jPanel1.add(lbFotoPaslon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 180, 200));

        lbFotoPaslon1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbFotoPaslon1.setForeground(new java.awt.Color(255, 255, 255));
        lbFotoPaslon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFotoPaslon1.setText("FOTO");
        jPanel1.add(lbFotoPaslon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 180, 200));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1366, 730));

        jPanel2.setBackground(new java.awt.Color(247, 202, 24));
        jPanel2.setFocusable(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Persentase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Persentase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Persentase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Persentase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Persentase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static transient javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static transient javax.swing.JProgressBar jProgressBar1;
    public static transient javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JLabel lbFotoPaslon1;
    private javax.swing.JLabel lbFotoPaslon2;
    // End of variables declaration//GEN-END:variables
}
