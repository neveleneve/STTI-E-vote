package form;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import conf.dbconnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import java.sql.Connection;

public class adminlogin extends javax.swing.JFrame {

    public adminlogin() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        UIManager.put("OptionPane.background", new ColorUIResource(44, 62, 80));
        UIManager.put("Panel.background", new ColorUIResource(44, 62, 80));
    }

    String ip = getIPServer.IPaddress;
    public Connection conn = new dbconnection().connect(ip);
    int attempt = 0;
    String SQL;

    public void AdminLogin() {
        try {
            java.sql.PreparedStatement adminstate = conn.prepareStatement("select * from login where nim = 'admin' and password = ?");
            adminstate.setString(1, String.valueOf(jPasswordField1.getPassword()));
            ResultSet adminrs = adminstate.executeQuery();
            if (adminrs.next()) {
                String nama = adminrs.getString("nama");
                this.dispose();
                new admin(nama).setVisible(true);
                //new persentase().setVisible(true);
            } else if (!adminrs.next() && attempt < 2) {
                String t = "<html><font color=#f7ca18>Password Salah!</font>";
                JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                attempt = attempt + 1;
                jPasswordField1.setText("");
            } else if (!adminrs.next() && attempt == 2) {
                String t = "<html><font color=#f7ca18>Sudah 3 Kali Memasukkan Password! Kembali Ke Halaman Login</font>";
                JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                attempt = 0;
                new login().setVisible(true);
                this.dispose();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteAllDataLogin() {
        try {
            java.sql.PreparedStatement stm = conn.prepareStatement("delete from login where nama not in('admin')");
            stm.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void DeleteDataConfirm() {
        try {
            java.sql.PreparedStatement adminstate = conn.prepareStatement("select * from login where nim = 'admin' and password = ?");
            adminstate.setString(1, String.valueOf(jPasswordField1.getPassword()));
            ResultSet adminrs = adminstate.executeQuery();
            if (adminrs.next() && attempt < 2) {
                String a = "<html><font color=#f7ca18></font>";
                String[] options = {"YA", "TIDAK"};
                int xyz = JOptionPane.showOptionDialog(null,
                        a, "Yakin Akan Menghapus Data Login?", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                switch (xyz) {
                    case 0:
                        String t = "<html><font color=#f7ca18>Data Berhasil Dihapus!</font>";
                        JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        deleteAllDataLogin();
                        this.dispose();
                        new admin("admin").setVisible(true);
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            } else if (!adminrs.next() && attempt < 2) {
                String t = "<html><font color=#f7ca18>Password Salah!</font>";
                JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                attempt = attempt + 1;
                jPasswordField1.setText("");
            } else if (!adminrs.next() && attempt == 2) {
                String t = "<html><font color=#f7ca18>Sudah 3 Kali Memasukkan Password! Kembali Ke Halaman Admin</font>";
                JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                attempt = 0;
                new admin("admin").setVisible(true);
                this.dispose();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Admin");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 202, 24));
        jLabel1.setText("Password");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, 20));

        jPasswordField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        jPanel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 170, 30));

        jButton2.setBackground(new java.awt.Color(247, 202, 24));
        jButton2.setText("Kembali");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 80, 30));

        jButton1.setBackground(new java.awt.Color(0, 181, 204));
        jButton1.setText("Login");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 80, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 120));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
            //new login().setVisible(true);
            //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);            
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton1.doClick();
            //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jButton1.getText().equals("Login")) {
            AdminLogin();
        } else if (jButton1.getText().equals("Konfirmasi")) {
            DeleteDataConfirm();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jButton1.getText().equals("Konfirmasi")) {
            new admin("admin").setVisible(true);
            this.dispose();
        } else if (jButton1.getText().equals("Login")) {
            new login().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(adminlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminlogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static transient javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}