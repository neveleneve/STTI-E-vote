package form;

import conf.dbconnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.ColorUIResource;

public class voter extends JFrame {

    Thread T;
    boolean kanan = true;
    boolean kiri = false;
    boolean berjalan = true;
    int x, y;
    Date tgl = new Date();
    public String namapemilih = null;
    public String nimpemilih = null;
    Dimension d = new Dimension(604, 420);

    public voter(String paranama, String paranim) {
        initComponents();
        UIManager.put("OptionPane.background", new ColorUIResource(44, 62, 80));
        UIManager.put("Panel.background", new ColorUIResource(44, 62, 80));
        jButton1.requestFocus();
        namapemilih = paranama;
        nimpemilih = paranim;
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        namaPaslon1();
        namaPaslon2();
    }
    String ip = getIPServer.IPaddress;
    public Connection conn = new dbconnection().connect(ip);
    int xa = 1000;
    int ya = 30;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gd = (Graphics2D) g;
        gd.setFont(new Font("Tahoma", Font.BOLD, 14));
        gd.setColor(new Color(0, 0, 0));
        gd.drawString("Selamat Datang, " + namapemilih, xa, ya);
        try {
            Thread.sleep(25);
            xa -= 2;
        } catch (InterruptedException e) {

        }
        if (xa < -257) {
            xa = 1000;
        }
        repaint();
    }

    public void namaPaslon1() {
        try {
            java.sql.PreparedStatement stm = conn.prepareStatement("select nama,gambar from hasilvote where Id = 1");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String nama = rs.getString("nama");
                byte[] img = rs.getBytes("gambar");
                ImageIcon myimg = new ImageIcon(img);
                Image img1 = myimg.getImage();
                Image img2 = img1.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon i = new ImageIcon(img2);
                jLabel2.setIcon(i);
                jRadioButton1.setText(nama);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePaslon1Choosed() {
        try {
            java.sql.PreparedStatement stm = conn.prepareStatement("UPDATE hasilvote SET jumlahsuara = jumlahsuara + 1 WHERE Id = 1");
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void namaPaslon2() {
        try {
            java.sql.PreparedStatement stm = conn.prepareStatement("select nama,gambar from hasilvote where Id = 2");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String nama = rs.getString("nama");
                byte[] img = rs.getBytes("gambar");
                ImageIcon myimg = new ImageIcon(img);
                Image img1 = myimg.getImage();
                Image img2 = img1.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon i = new ImageIcon(img2);
                jLabel3.setIcon(i);
                jRadioButton2.setText(nama);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePaslon2Choosed() {
        try {
            java.sql.PreparedStatement stm = conn.prepareStatement("UPDATE hasilvote SET jumlahsuara = jumlahsuara + 1 WHERE Id = 2");
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateVoterTimeChooser() {
        try {
            java.sql.PreparedStatement stm = conn.prepareStatement("UPDATE login SET confirm = 's', tanggal ='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tgl) + "' WHERE nim = '" + nimpemilih + "'");
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pemilihan Raya STT Indonesia");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 132));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 270, 300));
        jLabel2.getAccessibleContext().setAccessibleName("jLabel2");

        jLabel3.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 132));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 270, 300));
        jLabel3.getAccessibleContext().setAccessibleName("jLabel3");

        jRadioButton2.setBackground(new java.awt.Color(0, 0, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setBorder(null);
        jRadioButton2.setFocusable(false);
        jRadioButton2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton2.setOpaque(false);
        jRadioButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 430, 310, 50));

        jRadioButton1.setBackground(new java.awt.Color(0, 0, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setActionCommand("   \n\nJokowi - Ma'ruf Amin");
        jRadioButton1.setBorder(null);
        jRadioButton1.setFocusable(false);
        jRadioButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton1.setOpaque(false);
        jRadioButton1.setPreferredSize(new java.awt.Dimension(120, 23));
        jRadioButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, 310, 50));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(247, 202, 24));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("SEKOLAH TINGGI TEKNOLOGI INDONESIA TANJUNGPINANG"); // NOI18N
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(247, 202, 24)));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 740, 50));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(247, 202, 24));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("KOMISI PEMILIHAN UMUM MAHASISWA"); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 980, 60));

        jButton1.setBackground(new java.awt.Color(247, 202, 24));
        jButton1.setText("PILIH");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 970, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1000, 560));

        jPanel2.setBackground(new java.awt.Color(247, 202, 24));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        if (evt.getClickCount() == 2) {
            System.out.println("double clicked");
            jRadioButton1.setSelected(true);
            jButton1.doClick();
        } else if (evt.getClickCount() == 1) {
            System.out.println("single clicked");
            jRadioButton1.setSelected(true);
        }
    }//GEN-LAST:event_jLabel2MouseClicked
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        jRadioButton2.setSelected(true);
        if (evt.getClickCount() == 2) {
            System.out.println("double clicked");
            jRadioButton2.setSelected(true);
            jButton1.doClick();
        } else if (evt.getClickCount() == 1) {
            System.out.println("single clicked");
            jRadioButton2.setSelected(true);
        }
    }//GEN-LAST:event_jLabel3MouseClicked
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!jRadioButton1.isSelected() && !jRadioButton2.isSelected()) {
            String t = "<html><font color=#f7ca18>Silahkan Pilih Kandidat Terlebih Dahulu</font>";
            JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String nourut = "";
            if (jRadioButton1.isSelected()) {
                nourut = "1";
            } else if (jRadioButton2.isSelected()) {
                nourut = "2";
            }
            String t = "<html><font color=#f7ca18>Apakah Anda Yakin Memilih Pasangan Nomor Urut " + nourut + " ?</font>";
            String[] options = {"YA", "TIDAK"};
            int xyz = JOptionPane.showOptionDialog(null,
                    t, "Yakin Memilih?", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (xyz) {
                case 0:
                    if (jRadioButton1.isSelected()) {
                        updatePaslon1Choosed();
                        updateVoterTimeChooser();
                    } else if (jRadioButton2.isSelected()) {
                        updatePaslon2Choosed();
                        updateVoterTimeChooser();
                    }
                    String xx = "<html><font color=#f7ca18>Terima Kasih. Anda Berhasil Memilih!\"</font>";
                    JOptionPane.showMessageDialog(null, xx, "Info", JOptionPane.INFORMATION_MESSAGE);
                    new login().setVisible(true);
                    this.dispose();
                    break;
                case 1:
                    buttonGroup1.clearSelection();
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed
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
            java.util.logging.Logger.getLogger(voter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(voter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(voter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(voter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new voter("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}
