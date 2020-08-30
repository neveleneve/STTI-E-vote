/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import conf.dbconnection;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author NEVE
 */
public final class admin extends JFrame {

    /**
     * Creates new form admin
     */
    private DefaultTableModel tbllogin_mdl;

    public admin(String para) {
        initComponents();
        TampilDataPemilih();
        TampilDataPaslon();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        lbPass.setVisible(false);
        UIManager.put("OptionPane.background", new ColorUIResource(44, 62, 80));
        UIManager.put("Panel.background", new ColorUIResource(44, 62, 80));
        UIManager.put("TabbedPane.background", Color.PINK);
    }
    String ip = getIPServer.IPaddress;
    public Connection conn = new dbconnection().connect(ip);

    public void ambilGamber(String id) {
//        lbFotoPasanganx.setIcon(null);
        try {
            java.sql.PreparedStatement stm = conn.prepareStatement("select gambar from hasilvote where Id = '" + id + "'");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                byte[] img = rs.getBytes("gambar");
                ImageIcon myimg = new ImageIcon(img);
                Image img1 = myimg.getImage();
                Image img2 = img1.getScaledInstance(lbFotoPasanganx.getWidth(), lbFotoPasanganx.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon i = new ImageIcon(img2);
                lbFotoPasanganx.setBorder(null);
                lbFotoPasanganx.setIcon(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void TampilDataPemilih() {
        tbllogin_mdl = new DefaultTableModel();
        tbllogin_mdl.addColumn("NIM");
        tbllogin_mdl.addColumn("Nama");
        tbllogin_mdl.addColumn("Password");
        tbllogin_mdl.addColumn("Status");
        tbPemilih.setModel(tbllogin_mdl);
        tbPemilih.getColumnModel().getColumn(0).setPreferredWidth(70);
        tbPemilih.getColumnModel().getColumn(1).setPreferredWidth(120);
        tbPemilih.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbPemilih.getColumnModel().getColumn(3).setPreferredWidth(20);
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select * from login where nim <> 'admin'");
            while (res.next()) {
                tbllogin_mdl.addRow(new Object[]{
                    res.getString("nim"),
                    res.getString("nama"),
                    res.getString("password"),
                    res.getString("confirm")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void TampilDataPaslon() {
        tbllogin_mdl = new DefaultTableModel();
        tbllogin_mdl.addColumn("Nama Urut Pasangan Calon");
        tbllogin_mdl.addColumn("Nama Pasangan Calon");
        tbllogin_mdl.addColumn("Jumlah Suara");
        tbPaslon.setModel(tbllogin_mdl);
        tbPaslon.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbPaslon.getColumnModel().getColumn(1).setPreferredWidth(190);
        tbPaslon.getColumnModel().getColumn(2).setPreferredWidth(50);
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select * from hasilvote");
            while (res.next()) {
                tbllogin_mdl.addRow(new Object[]{
                    res.getString("id"),
                    res.getString("nama"),
                    res.getString("jumlahsuara")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void AddDataCalon() throws FileNotFoundException {
        try {
            File image = new File(txtGbCalon.getText());
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select max(id) as xxx from hasilvote");
            int id = 0;
            while (res.next()) {
                id = res.getInt("xxx");
            }
            System.out.println(String.valueOf(id));
            FileInputStream inputstream = new FileInputStream(image);
            PreparedStatement stm = conn.prepareStatement("insert into hasilvote values (?,?,?, ?)");
            try {
                stm.setString(1, txtNoCalon.getText());
                stm.setString(2, txtNamaKa.getText() + " - " + txtNamaWaka.getText());
                stm.setInt(3, 0);
                stm.setBinaryStream(4, (InputStream) inputstream, (int) image.length());
                stm.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error Add Data Calon");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println("Error Selecting max ID Calon");
            System.out.println(e);
        }
    }

    public void AddDataPemilih() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select max(right(password, 3)) as xxx from login where nim <> 'admin'");
            int pass;
            while (res.next()) {
                pass = res.getInt("xxx");
                String no = String.valueOf(pass + 1);
                int noLong = no.length();
                for (int a = 0; a < 3 - noLong; a++) {
                    no = "0" + no;
                }
                lbPass.setText(no);
                System.out.println(no);
            }
            PreparedStatement stm = conn.prepareStatement("insert into login (nim, nama, password, confirm) values (?,?,?,?)");
            try {
                stm.setString(1, txtNIMTambah.getText());
                stm.setString(2, txtNamaTambah.getText());
                stm.setString(3, "A" + lbPass.getText());
                stm.setString(4, "b");
                stm.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error Add Data Pemilih");
                System.out.println(e);
            }
            TampilDataPemilih();
        } catch (SQLException e) {
            System.out.println("Error Selecting max ID");
            System.out.println(e);
        }
    }

    public void DeleteAllData() {
        try {
            PreparedStatement stm = conn.prepareStatement("truncate login");
            stm.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void cetakHasilAkhir() {
        try {
            File file = new File("src/report/laporansuara.jrxml");
            JasperDesign jasperdesign = JRXmlLoader.load(file);
            String sql = "SELECT *, (select sum(jumlahsuara) from hasilvote) as total FROM hasilvote";
            JRDesignQuery newquery = new JRDesignQuery();
            newquery.setText(sql);
            jasperdesign.setQuery(newquery);
            JasperReport jasperreport = JasperCompileManager.compileReport(jasperdesign);
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, null, conn);
            JasperViewer.viewReport(jasperprint, false);
        } catch (JRException e) {
            System.out.println(e);
        }
    }

    public void cetakPemilihTidakMemilih() {
        try {
            File file = new File("src/report/LaporanPemilih.jrxml");
            JasperDesign jasperdesign = JRXmlLoader.load(file);
            String sql = "select * from login where nim <> 'admin' and confirm = 'b'";
            JRDesignQuery newquery = new JRDesignQuery();
            newquery.setText(sql);
            jasperdesign.setQuery(newquery);
            JasperReport jasperreport = JasperCompileManager.compileReport(jasperdesign);
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, null, conn);
            JasperViewer.viewReport(jasperprint, false);
        } catch (JRException e) {
            System.out.println(e);
        }
    }

    public void cetakPemilihMemilih() {
        try {
            File file = new File("src/report/LaporanPemilih.jrxml");
            JasperDesign jasperdesign = JRXmlLoader.load(file);
            String sql = "select * from login where nim <> 'admin' and confirm = 's'";
            JRDesignQuery newquery = new JRDesignQuery();
            newquery.setText(sql);
            jasperdesign.setQuery(newquery);
            JasperReport jasperreport = JasperCompileManager.compileReport(jasperdesign);
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, null, conn);
            JasperViewer.viewReport(jasperprint, false);
        } catch (JRException e) {
            System.out.println(e);
        }
    }

    public void UpdateData(String nama, String id) {
        try {
            PreparedStatement stm = conn.prepareStatement("update hasilvote set nama= '" + nama + "' where id = '" + id + "'");
            stm.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void DeleteDataPaslon(String id) {
        try {
            PreparedStatement stm = conn.prepareStatement("delete from hasilvote where id = '" + id + "'");
            stm.executeUpdate();
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        insertButton = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPemilih = new javax.swing.JTable();
        btnPersentase = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtNIMTambah = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNamaTambah = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbPaslon = new javax.swing.JTable();
        lbFotoPasanganx = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNoUrut = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNamaPaslon = new javax.swing.JTextField();
        btnUpdatePaslon = new javax.swing.JButton();
        btnHapusPaslon = new javax.swing.JButton();
        btnHapusPaslon1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNamaWaka = new javax.swing.JTextField();
        txtNamaKa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGbCalon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNoCalon = new javax.swing.JTextField();
        lbFotoPasangan = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JButton();
        btnInputPaslon = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        cbLaporan = new javax.swing.JComboBox<>();
        btnInputPaslon1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Halaman Administrator");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(44, 62, 80));
        jTabbedPane1.setForeground(new java.awt.Color(247, 202, 24));
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 30));

        insertButton.setBackground(new java.awt.Color(123, 239, 178));
        insertButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        insertButton.setText("Insert Data");
        insertButton.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        insertButton.setBorderPainted(false);
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });
        jPanel1.add(insertButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 125, 30));

        jButton6.setBackground(new java.awt.Color(150, 40, 27));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Delete All Data");
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton6.setBorderPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 125, 30));

        jButton1.setBackground(new java.awt.Color(123, 239, 178));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Cari Nim");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton1.setBorderPainted(false);
        jButton1.setPreferredSize(new java.awt.Dimension(71, 20));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 80, 30));

        tbPemilih.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbPemilih.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbPemilih);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 675, 420));

        btnPersentase.setBackground(new java.awt.Color(0, 181, 204));
        btnPersentase.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPersentase.setText("Lihat Persentase");
        btnPersentase.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        btnPersentase.setBorderPainted(false);
        btnPersentase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersentaseActionPerformed(evt);
            }
        });
        jPanel1.add(btnPersentase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 165, 30));

        btnLogOut.setBackground(new java.awt.Color(150, 40, 27));
        btnLogOut.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLogOut.setForeground(new java.awt.Color(255, 255, 255));
        btnLogOut.setText("Log Out");
        btnLogOut.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        btnLogOut.setBorderPainted(false);
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 490, 165, 30));

        btnRefresh.setBackground(new java.awt.Color(123, 239, 178));
        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRefresh.setText("Refresh Tabel");
        btnRefresh.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        btnRefresh.setBorderPainted(false);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 165, 30));

        jTabbedPane1.addTab("  Data Pemilih  ", jPanel1);

        jPanel5.setBackground(new java.awt.Color(44, 62, 80));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNIMTambah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtNIMTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 180, 30));

        lbPass.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbPass.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lbPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 60, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 60, 30));

        txtNamaTambah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtNamaTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 220, 30));

        jButton9.setBackground(new java.awt.Color(123, 239, 178));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton9.setText("Input");
        jButton9.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(123, 239, 178)));
        jButton9.setBorderPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 220, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("NIM");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 60, 30));

        jTabbedPane1.addTab("Tambah Data Pemilih", jPanel5);

        jPanel4.setBackground(new java.awt.Color(44, 62, 80));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbPaslon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbPaslon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPaslon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPaslonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbPaslon);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 675, 240));

        lbFotoPasanganx.setForeground(new java.awt.Color(255, 255, 255));
        lbFotoPasanganx.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFotoPasanganx.setText("FOTO PASANGAN CALON");
        lbFotoPasanganx.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));
        jPanel4.add(lbFotoPasanganx, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 30, 160, 160));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("No. Urut Pasangan Calon");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 150, 30));

        txtNoUrut.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNoUrut.setEnabled(false);
        jPanel4.add(txtNoUrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 240, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nama Pasangan Calon");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 150, 30));

        txtNamaPaslon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel4.add(txtNamaPaslon, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 240, 30));

        btnUpdatePaslon.setBackground(new java.awt.Color(123, 239, 178));
        btnUpdatePaslon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnUpdatePaslon.setText("Perbarui Data Paslon");
        btnUpdatePaslon.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        btnUpdatePaslon.setBorderPainted(false);
        btnUpdatePaslon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePaslonActionPerformed(evt);
            }
        });
        jPanel4.add(btnUpdatePaslon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 150, 30));

        btnHapusPaslon.setBackground(new java.awt.Color(150, 40, 27));
        btnHapusPaslon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHapusPaslon.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusPaslon.setText("Hapus Data Paslon");
        btnHapusPaslon.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        btnHapusPaslon.setBorderPainted(false);
        btnHapusPaslon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusPaslonActionPerformed(evt);
            }
        });
        jPanel4.add(btnHapusPaslon, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 150, 30));

        btnHapusPaslon1.setBackground(new java.awt.Color(0, 181, 204));
        btnHapusPaslon1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHapusPaslon1.setText("Refresh");
        btnHapusPaslon1.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        btnHapusPaslon1.setBorderPainted(false);
        btnHapusPaslon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusPaslon1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnHapusPaslon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 90, 30));

        jTabbedPane1.addTab("Data Pasangan Calon", jPanel4);

        jPanel3.setBackground(new java.awt.Color(44, 62, 80));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 202, 24));
        jLabel1.setText("Nama Calon Wakil Ketua");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 180, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(247, 202, 24));
        jLabel2.setText("Nama Calon Ketua");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 140, 20));

        txtNamaWaka.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtNamaWaka, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 180, 30));

        txtNamaKa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtNamaKa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 180, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 202, 24));
        jLabel3.setText("Gambar Calon");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 140, 20));

        txtGbCalon.setEditable(false);
        txtGbCalon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtGbCalon.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtGbCalon, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 180, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(247, 202, 24));
        jLabel4.setText("Nomor Calon");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 140, 20));

        txtNoCalon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtNoCalon, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 180, 30));

        lbFotoPasangan.setForeground(new java.awt.Color(255, 255, 255));
        lbFotoPasangan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFotoPasangan.setText("FOTO PASANGAN CALON");
        lbFotoPasangan.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));
        jPanel3.add(lbFotoPasangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 270, 288));

        btnBrowse.setBackground(new java.awt.Color(0, 181, 204));
        btnBrowse.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBrowse.setText("Browse");
        btnBrowse.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(123, 239, 178)));
        btnBrowse.setBorderPainted(false);
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });
        jPanel3.add(btnBrowse, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 80, 30));

        btnInputPaslon.setBackground(new java.awt.Color(123, 239, 178));
        btnInputPaslon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnInputPaslon.setText("Input");
        btnInputPaslon.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(123, 239, 178)));
        btnInputPaslon.setBorderPainted(false);
        btnInputPaslon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputPaslonActionPerformed(evt);
            }
        });
        jPanel3.add(btnInputPaslon, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 270, 30));

        jTabbedPane1.addTab("  Input Data Calon  ", jPanel3);

        jPanel6.setBackground(new java.awt.Color(44, 62, 80));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbLaporan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH LAPORAN...", "Laporan Hasil Suara Akhir", "Laporan Mahasiswa Pemilih Memilih", "Laporan Mahasiswa Pemilih Tidak Memilih" }));
        jPanel6.add(cbLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 440, 30));

        btnInputPaslon1.setBackground(new java.awt.Color(123, 239, 178));
        btnInputPaslon1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnInputPaslon1.setText("Cetak");
        btnInputPaslon1.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(123, 239, 178)));
        btnInputPaslon1.setBorderPainted(false);
        btnInputPaslon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputPaslon1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnInputPaslon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 220, 30));

        jTabbedPane1.addTab("Report", jPanel6);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 700, 560));

        jPanel2.setBackground(new java.awt.Color(247, 202, 24));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(44, 62, 80));
        jLabel5.setText("ADMIN DASHBOARD");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 220, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 60));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tbllogin_mdl = new DefaultTableModel();
        tbllogin_mdl.addColumn("NIM");
        tbllogin_mdl.addColumn("Nama");
        tbllogin_mdl.addColumn("Password");
        tbllogin_mdl.addColumn("Status");
        tbPemilih.setModel(tbllogin_mdl);
        tbPemilih.getColumnModel().getColumn(0).setPreferredWidth(70);
        tbPemilih.getColumnModel().getColumn(1).setPreferredWidth(120);
        tbPemilih.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbPemilih.getColumnModel().getColumn(3).setPreferredWidth(20);
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select * from login where nim like  '" + jTextField1.getText() + "%'");
            while (res.next()) {
                tbllogin_mdl.addRow(new Object[]{
                    res.getString("nim"),
                    res.getString("nama"),
                    res.getString("password"),
                    res.getString("confirm")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "A", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPersentaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersentaseActionPerformed
        new Persentase().setVisible(true);
    }//GEN-LAST:event_btnPersentaseActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        if (new Persentase().isEnabled()) {
            new Persentase().dispose();
            new login().setVisible(true);
            this.dispose();
        } else {
            new Persentase().setVisible(false);
            new login().dispose();
            this.dispose();
        }
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        TampilDataPemilih();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.dispose();
        new adminlogin().setVisible(true);
        adminlogin.jButton1.setText("Konfirmasi");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
        new loadData().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_insertButtonActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        FileNameExtensionFilter fileext = new FileNameExtensionFilter("File .jpg / .png", "jpg", "png");
        JFileChooser jfc = new JFileChooser("D:");
        jfc.addChoosableFileFilter(fileext);
        int r = jfc.showOpenDialog(jPanel1);
        if (r == JFileChooser.APPROVE_OPTION) {
            txtGbCalon.setText(jfc.getSelectedFile().getAbsolutePath());
            ImageIcon icon = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
            Image img1 = icon.getImage();
            Image img2 = img1.getScaledInstance(lbFotoPasangan.getWidth(), lbFotoPasangan.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon img = new ImageIcon(img2);
            lbFotoPasangan.setIcon(img);
            lbFotoPasangan.setText("");
            lbFotoPasangan.setBorder(null);
        } else {
            txtGbCalon.setText("");
            lbFotoPasangan.setIcon(null);
            lbFotoPasangan.setText("FOTO PASANGAN CALON");
            lbFotoPasangan.setBorder(new MatteBorder(3, 3, 3, 3, Color.white));
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void btnInputPaslonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputPaslonActionPerformed
        if ("".equals(txtNamaKa.getText()) || "".equals(txtNamaWaka.getText()) || "".equals(txtNoCalon.getText()) || "".equals(txtGbCalon.getText())) {
            JOptionPane.showMessageDialog(this, "Data Calon Tidak Lengkap! Mohon Periksa Kembali", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                AddDataCalon();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Data Calon Berhasil Diinput", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            TampilDataPaslon();
            txtNamaKa.setText("");
            txtNamaWaka.setText("");
            txtNoCalon.setText("");
            txtGbCalon.setText("");
            lbFotoPasangan.setIcon(null);
            lbFotoPasangan.setText("FOTO PASANGAN CALON");
            lbFotoPasangan.setBorder(new MatteBorder(3, 3, 3, 3, Color.white));
        }
    }//GEN-LAST:event_btnInputPaslonActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if ("".equals(txtNIMTambah.getText()) || "".equals(txtNamaTambah.getText())) {
            JOptionPane.showMessageDialog(this, "Data Peserta Tidak Lengkap! Mohon Periksa Kembali", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            AddDataPemilih();
            JOptionPane.showMessageDialog(this, "Data Pemilih Berhasil Diinput", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            txtNIMTambah.setText("");
            txtNamaTambah.setText("");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void btnUpdatePaslonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePaslonActionPerformed
        UpdateData(txtNamaPaslon.getText(), txtNoUrut.getText());
        TampilDataPaslon();
        txtNoUrut.setText("");
        txtNamaPaslon.setText("");
        lbFotoPasanganx.setIcon(null);
        lbFotoPasanganx.setText("FOTO PASANGAN CALON");
        lbFotoPasanganx.setBorder(new MatteBorder(3, 3, 3, 3, Color.white));
    }//GEN-LAST:event_btnUpdatePaslonActionPerformed

    private void btnHapusPaslonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPaslonActionPerformed
        DeleteDataPaslon(txtNoUrut.getText());
        TampilDataPaslon();
        txtNoUrut.setText("");
        txtNamaPaslon.setText("");
        lbFotoPasanganx.setIcon(null);
        lbFotoPasanganx.setText("FOTO PASANGAN CALON");
        lbFotoPasanganx.setBorder(new MatteBorder(3, 3, 3, 3, Color.white));
    }//GEN-LAST:event_btnHapusPaslonActionPerformed

    private void tbPaslonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPaslonMouseClicked
        int selrow = tbPaslon.getSelectedRow();
        txtNoUrut.setText(tbPaslon.getValueAt(selrow, 0).toString());
        txtNamaPaslon.setText(tbPaslon.getValueAt(selrow, 1).toString());
        ambilGamber(txtNoUrut.getText());
    }//GEN-LAST:event_tbPaslonMouseClicked

    private void btnHapusPaslon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPaslon1ActionPerformed
        txtNoUrut.setText("");
        txtNamaPaslon.setText("");
        lbFotoPasanganx.setIcon(null);
        lbFotoPasanganx.setText("FOTO PASANGAN CALON");
        lbFotoPasanganx.setBorder(new MatteBorder(3, 3, 3, 3, Color.white));
    }//GEN-LAST:event_btnHapusPaslon1ActionPerformed

    private void btnInputPaslon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputPaslon1ActionPerformed
        switch (cbLaporan.getSelectedIndex()) {
            case 0:
                break;
            case 1:
                cetakHasilAkhir();
                break;
            case 2:
                cetakPemilihMemilih();
                break;
            case 3:
                cetakPemilihTidakMemilih();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnInputPaslon1ActionPerformed

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
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnHapusPaslon;
    private javax.swing.JButton btnHapusPaslon1;
    private javax.swing.JButton btnInputPaslon;
    private javax.swing.JButton btnInputPaslon1;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnPersentase;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdatePaslon;
    private javax.swing.JComboBox<String> cbLaporan;
    private javax.swing.JButton insertButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbFotoPasangan;
    private javax.swing.JLabel lbFotoPasanganx;
    private javax.swing.JLabel lbPass;
    private javax.swing.JTable tbPaslon;
    private javax.swing.JTable tbPemilih;
    private javax.swing.JTextField txtGbCalon;
    private javax.swing.JTextField txtNIMTambah;
    private javax.swing.JTextField txtNamaKa;
    private javax.swing.JTextField txtNamaPaslon;
    private javax.swing.JTextField txtNamaTambah;
    private javax.swing.JTextField txtNamaWaka;
    private javax.swing.JTextField txtNoCalon;
    private javax.swing.JTextField txtNoUrut;
    // End of variables declaration//GEN-END:variables
}
