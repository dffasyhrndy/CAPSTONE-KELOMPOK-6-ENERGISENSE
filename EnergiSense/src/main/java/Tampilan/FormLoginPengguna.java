/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Tampilan;

import db.Koneksi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Admin;
import model.Biasa;
import model.Pengguna;

/**
 *
 * @author Administrator
 */
public class FormLoginPengguna extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnMasuk, btnDaftar, btnKeluar;
    
    /**
     * Creates new form FormLoginPengguna
     */
    public FormLoginPengguna() {
        setTitle("EnergiSense - Login Pengguna");
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelUtama = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = new ImageIcon("src/energisense_logo.png");
                Image img = bgIcon.getImage();
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.18f));
                int logoW = getWidth() / 4;
                int logoH = getHeight() / 4;
                g2d.drawImage(img, 40, 30, logoW, logoH, this);
                g2d.drawImage(img, getWidth() - logoW - 40, getHeight() - logoH - 40, logoW, logoH, this);
                g2d.dispose();
            }
        };
        panelUtama.setBackground(new Color(210, 235, 255));
        setContentPane(panelUtama);

        JPanel card = new JPanel(null);
        card.setBounds(420, 160, 440, 360);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(120, 170, 220), 2, true));
        panelUtama.add(card);

        JLabel lblTitle = new JLabel("Masuk ke EnergiSense", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setBounds(50, 20, 340, 30);
        card.add(lblTitle);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(60, 70, 80, 25);
        card.add(lblEmail);
        txtEmail = buatTextField();
        txtEmail.setBounds(140, 70, 240, 28);
        card.add(txtEmail);

        JLabel lblPass = new JLabel("Kata Sandi:");
        lblPass.setBounds(60, 110, 80, 25);
        card.add(lblPass);
        txtPassword = buatPasswordField();
        txtPassword.setBounds(140, 110, 240, 28);
        card.add(txtPassword);

        btnMasuk = buatButton("Masuk", new Color(90, 160, 250), new Color(70, 140, 230));
        btnMasuk.setBounds(140, 160, 240, 40);
        card.add(btnMasuk);

        btnDaftar = buatButton("Belum punya akun?", Color.WHITE, new Color(200, 220, 255));
        btnDaftar.setForeground(new Color(70, 120, 200));
        btnDaftar.setBounds(140, 210, 240, 30);
        card.add(btnDaftar);

        btnKeluar = buatButton("⏻  Keluar", new Color(220, 70, 70), new Color(190, 50, 50));
        btnKeluar.setBounds(140, 255, 240, 35);
        card.add(btnKeluar);

        btnDaftar.addActionListener(e -> {
            new FormDaftarPengguna().setVisible(true);
            dispose();
        });

        btnKeluar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Yakin ingin keluar dari aplikasi?",
                    "Konfirmasi Keluar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });

        btnMasuk.addActionListener(e -> {
            String email = txtEmail.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Isi email dan kata sandi terlebih dahulu.");
                return;
            }
            try {
                Pengguna p = authenticateUser(email, password);
                if (p == null) {
                    JOptionPane.showMessageDialog(this, "Email atau kata sandi salah.");
                    return;
                }
                if (p instanceof Admin) new FormAdmin((Admin) p).setVisible(true);
                else new FormBiasa((Biasa) p).setVisible(true);
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Terjadi error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }

    private JButton buatButton(String text, Color baseColor, Color hoverColor) {
        JButton btn = new JButton(text);
        btn.setBackground(baseColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                new Thread(() -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(15);
                        } catch (InterruptedException ignored) {}
                        SwingUtilities.invokeLater(() -> btn.setBackground(hoverColor));
                    }
                }).start();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(baseColor);
            }
        });
        return btn;
    }

    private JTextField buatTextField() {
        JTextField field = new JTextField();
        field.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230)));
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(new Color(90, 160, 250), 2));
                field.setBackground(new Color(245, 250, 255));
            }
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230)));
                field.setBackground(Color.WHITE);
            }
        });
        return field;
    }

    private JPasswordField buatPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230)));
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(new Color(90, 160, 250), 2));
                field.setBackground(new Color(245, 250, 255));
            }
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230)));
                field.setBackground(Color.WHITE);
            }
        });
        return field;
    }

    private Pengguna authenticateUser(String email, String password) throws Exception {
        String sql = "SELECT * FROM pengguna WHERE email = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String dbPass = rs.getString("kata_sandi");
                    if (!password.equals(dbPass)) return null;

                    int id = rs.getInt("id_pengguna");
                    String nama = rs.getString("nama_pengguna");
                    String tipe = rs.getString("tipe_pengguna");

                    String cekAdmin = "SELECT * FROM admin WHERE id_pengguna = ?";
                    PreparedStatement psAdmin = conn.prepareStatement(cekAdmin);
                    psAdmin.setInt(1, id);
                    ResultSet rsAdmin = psAdmin.executeQuery();

                    if (rsAdmin.next()) {
                        int hak = rsAdmin.getInt("hak_akses");
                        String jabatan = rsAdmin.getString("jabatan");
                        return new Admin(id, nama, email, dbPass, tipe, hak, jabatan);
                    } else {
                        String cekBiasa = "SELECT * FROM biasa WHERE id_pengguna = ?";
                        PreparedStatement psBiasa = conn.prepareStatement(cekBiasa);
                        psBiasa.setInt(1, id);
                        ResultSet rsBiasa = psBiasa.executeQuery();

                        java.sql.Date tgl = null;
                        boolean aktif = true;
                        if (rsBiasa.next()) {
                            tgl = rsBiasa.getDate("tanggal_daftar");
                            aktif = rsBiasa.getBoolean("status_aktif");
                        }
                        return new Biasa(id, nama, email, dbPass, tipe, tgl, aktif);
                    }
                }
            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
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
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(FormLoginPengguna::new);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
