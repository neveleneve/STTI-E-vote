package form;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import conf.dbconnection;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import form.getIPServer;
import javax.swing.plaf.ColorUIResource;

public class login extends JFrame{
    Thread T;
    boolean kanan = true;
    boolean kiri = false ;
    boolean berjalan = true;
    int x, y;
        
    public login() {        
        initComponents();
        jl1pic();
        btnhidpass();                  
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false); 
        jPasswordField1.setEchoChar('*');
        jTextField1.requestFocus();
        jLabel4.setText(" ");
        jLabel5.setText(" "); 
        jLabel6.setText(" ");
        UIManager.put("OptionPane.background", new ColorUIResource(44, 62, 80));
        UIManager.put("Panel.background", new ColorUIResource(44, 62, 80));
    }    
    String ip = getIPServer.IPaddress;
    public Connection conn = new dbconnection().connect(ip);
    int xa =620;
    int ya = 80;
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D gd =(Graphics2D)g;
        gd.setFont(new Font("Tahoma", Font.BOLD, 14));
        gd.setColor(new Color(247,202,24));
        gd.drawString("Komisi Pemilihan Umum Mahasiswa Sekolah Tinggi Teknologi Indonesia Tanjungpinang", xa, ya);
            try {
                Thread.sleep(25);
                xa-=2;
            } catch (InterruptedException e) {

            }
            if(xa<-598){
                xa=620;
            }
            repaint();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pemilihan Raya STT Indonesia");
        setForeground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(247, 202, 24));
        jLabel2.setText("Username");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ini peringatan untuk input non angka");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 200, 20));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 180, 180));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ini untuk memberi tahu pass salah dan sudah memilih");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 200, 20));

        jPasswordField1.setBackground(new java.awt.Color(44, 62, 80));
        jPasswordField1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(125, 118, 58));
        jPasswordField1.setText("Password...");
        jPasswordField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(247, 202, 24)));
        jPasswordField1.setCaretColor(new java.awt.Color(247, 202, 24));
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusLost(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });
        jPanel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 154, 20));

        jButton3.setBackground(new java.awt.Color(247, 202, 24));
        jButton3.setText("Admin Login");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(247, 202, 24)));
        jButton3.setBorderPainted(false);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 100, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 202, 24));
        jLabel3.setText("Password");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 181, 204));
        jButton2.setText("Login");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 181, 204)));
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 90, 30));

        jTextField1.setBackground(new java.awt.Color(44, 62, 80));
        jTextField1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(125, 118, 58));
        jTextField1.setText("NIM...");
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(247, 202, 24)));
        jTextField1.setCaretColor(new java.awt.Color(247, 202, 24));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 200, 20));

        jButton1.setBackground(new java.awt.Color(150, 40, 27));
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(150, 40, 27)));
        jButton1.setBorderPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 40, 20));

        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 200, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 620, 310));

        jPanel2.setBackground(new java.awt.Color(247, 202, 24));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setBackground(new java.awt.Color(123, 239, 178));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton4.setText("Connection Test");
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(123, 239, 178)));
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 110, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnshwpass(){
        ImageIcon myimg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("showpass.png")));
        Image img1 = myimg.getImage();
        Image img2 = img1.getScaledInstance(jButton1.getWidth() - 15, jButton1.getHeight() - 6, Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        jButton1.setIcon(i);
    }
    
    private void btnhidpass(){
        ImageIcon myimg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("hidepass.png")));
        Image img1 = myimg.getImage();
        Image img2 = img1.getScaledInstance(jButton1.getWidth() - 15, jButton1.getHeight() - 6, Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        jButton1.setIcon(i);
    }    
    
    private void jl1pic(){       
        ImageIcon myimg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logokpu remake.png")));
        Image img1 = myimg.getImage();
        Image img2 = img1.getScaledInstance(jLabel1.getWidth()-5, jLabel1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        jLabel1.setIcon(i);
    }   
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        char[] a = {'P','a','s','s','w','o','r','d','.','.','.'};
        char[] reset = {'r','e','s','t','a','r','t'};
        char[] close = {'e','x','i','t'};
        if("NIM...".equals(jTextField1.getText()) && Arrays.equals(jPasswordField1.getPassword(), close)){
            this.dispose();
        }else if("NIM...".equals(jTextField1.getText()) && Arrays.equals(jPasswordField1.getPassword(), reset)){
            this.dispose();
            new getIPServer().setVisible(true);
        }else if("NIM...".equals(jTextField1.getText())){
            String t = "<html><font color=#f7ca18>Silahkan Lengkapi Data Login Anda</font>";
            JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }else if(Arrays.equals(a, jPasswordField1.getPassword())){
            String t = "<html><font color=#f7ca18>Silahkan Lengkapi Data Login Anda</font>";
            JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }else {
            try {
                PreparedStatement nimstate = conn.prepareStatement("select * from login where nim = ?");
                nimstate.setString(1, jTextField1.getText());
                ResultSet nimrs = nimstate.executeQuery();
                if(nimrs.next()){
                    String nama = nimrs.getString("nama");
                    String nim = nimrs.getString("nim");
                    PreparedStatement passtate = conn.prepareStatement("select * from login where nim = ? and password = ?");
                    passtate.setString(1, jTextField1.getText());
                    passtate.setString(2, jPasswordField1.getText());
                    ResultSet pasrs = passtate.executeQuery();
                    if(pasrs.next()){
                        PreparedStatement conftate = conn.prepareStatement("select * from login where nim = ? and password = ?");
                        conftate.setString(1, jTextField1.getText());
                        conftate.setString(2, String.valueOf(jPasswordField1.getPassword()));
                        ResultSet confrs = conftate.executeQuery();
                        if(confrs.next()){
                            if ("s".equals(confrs.getString("confirm"))){
                                String t = "<html><font color=#f7ca18>Anda Sudah Memilih!</font>";
                                JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                                jTextField1.setText("NIM...");
                                jPasswordField1.setText("Password...");
                                jPasswordField1.setEchoChar('*');
                                btnhidpass();
                                jTextField1.requestFocus();
                            }else{
                                jLabel4.setText(" ");
                                jLabel5.setText(" ");
                                String t = "<html><font color=#f7ca18>Anda Telah Login! Silahkan Memilih!</font>";
                                JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                                this.dispose();
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                new voter(nama, nim).setVisible(true);
                            }
                        }
                    }else{
                        jLabel5.setText("Password Anda Salah! Coba Lagi.");
                        jLabel5.setForeground(new Color(247,202,24));
                        jPasswordField1.setText("");
                    }                        
                }else{
                    String t = "<html><font color=#f7ca18>NIM Anda Tidak Terdaftar. Silahkan Hubungi Panitia!</font>";
                    JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }    
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }           
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jPasswordField1.getEchoChar() == (char)0){
            jPasswordField1.setEchoChar('*');
            btnhidpass();
            jButton1.setBackground(new Color(150, 40, 27));
        }else if(jPasswordField1.getEchoChar() == ('*')){
            jPasswordField1.setEchoChar((char)0);
            btnshwpass();
            jButton1.setBackground(new Color(0, 230, 64));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
            //ubah ip address sesuai server
            String url = "jdbc:mysql://"+ip+"/votingapp";
            Connection koneksi= DriverManager.getConnection(url,"root","");
            String t = "<html><font color=#f7ca18>Anda Sudah Terhubung</font>";
            Thread.sleep(1000);
            JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            jTextField1.requestFocus();
        }catch(InterruptedException | SQLException e){
            try {
                String t = "<html><font color=#f7ca18>Anda Tidak Terhubung</font>";
                Thread.sleep(1000);
                JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e);
            } catch (InterruptedException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            jButton2.doClick();
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        char vchar = evt.getKeyChar();
        if(Character.isDigit(vchar) || vchar == KeyEvent.VK_BACK_SPACE || vchar == KeyEvent.VK_DELETE){
            jLabel4.setText(" ");
        }else{            
            evt.consume();
            System.out.println("bukan angka, backspace, atau delete");
            jLabel4.setText("Silahkan Masukkan NIM Anda!");
            jLabel4.setForeground(Color.red);
            jLabel4.setVisible(true); 
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyTyped
        char vchar = evt.getKeyChar();
        if(Character.isAlphabetic(vchar) || vchar == KeyEvent.VK_BACK_SPACE || vchar == KeyEvent.VK_DELETE || Character.isDigit(vchar) || vchar == KeyEvent.VK_TAB){
            jLabel5.setText(" ");
        }
    }//GEN-LAST:event_jPasswordField1KeyTyped

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        if(jTextField1.getText().equals("NIM...")){
            jTextField1.setFont(new Font("Tahoma", Font.BOLD, 11));
            jTextField1.setForeground(new Color(247,202,24));
            jTextField1.setText("");
            
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if(jTextField1.getText().equals("")){
            jTextField1.setFont(new Font("Tahoma", Font.ITALIC + Font.BOLD, 11));
            jTextField1.setForeground(new Color(125,118,58));
            jTextField1.setText("NIM...");
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
        String pass = "Password...";
        String password = new String(jPasswordField1.getPassword());
        if(password.equals(pass)){
            jPasswordField1.setFont(new Font("Tahoma", Font.BOLD, 11));
            jPasswordField1.setForeground(new Color(247,202,24));
            jPasswordField1.setText("");            
        }
    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusLost
        String pass = "";
        String password = new String(jPasswordField1.getPassword());
        if(password.equals(pass)){
            jPasswordField1.setFont(new Font("Tahoma", Font.ITALIC + Font.BOLD, 11));
            jPasswordField1.setForeground(new Color(125,118,58));
            jPasswordField1.setText("Password...");
        }
    }//GEN-LAST:event_jPasswordField1FocusLost

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        if (evt.getClickCount() == 5) {
            System.out.println("5 times clicked");
            String t = "<html><font color=#f7ca18>Anda Berhasil Melakukan Langkah Administrator. Silahkan Masukkan Password Administrator!</font>";
            JOptionPane.showMessageDialog(null, t, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            // Last Edited 27/03/2019 
            //adminlogin.jButton1.setText("Login");
            new adminlogin().setVisible(true);
        }
    }//GEN-LAST:event_jButton3MouseClicked

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
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}