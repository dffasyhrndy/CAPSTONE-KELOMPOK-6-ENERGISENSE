/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Tampilan;

import db.Koneksi;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class FormDaftarPengguna extends JFrame {

    private JTextField txtNama, txtEmail;
    private JPasswordField txtKataSandi;
    private JComboBox<String> cmbTipe;
    private JButton btnNext, btnLogin;
    private JLabel lblTogglePassword;
    private boolean showPassword = false;
    
    public FormDaftarPengguna() {
        setTitle("EnergiSense - Daftar Pengguna");
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

        JPanel cardPanel = new JPanel(null);
        cardPanel.setBounds(420, 120, 440, 480);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 5, 5, new Color(190, 210, 240)),
                BorderFactory.createLineBorder(new Color(120, 170, 220), 1, true)
        ));
        panelUtama.add(cardPanel);

        JLabel lblTitle = new JLabel("Form Daftar Pengguna", SwingConstants.CENTER);
        lblTitle.setBounds(50, 40, 340, 35);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        cardPanel.add(lblTitle);

        JLabel lblNama = new JLabel("Nama:");
        lblNama.setBounds(60, 110, 100, 25);
        cardPanel.add(lblNama);
        txtNama = buatTextField(160, 110, 220);
        cardPanel.add(txtNama);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(60, 150, 100, 25);
        cardPanel.add(lblEmail);
        txtEmail = buatTextField(160, 150, 220);
        cardPanel.add(txtEmail);

        JLabel lblSandi = new JLabel("Kata Sandi:");
        lblSandi.setBounds(60, 190, 100, 25);
        cardPanel.add(lblSandi);
        txtKataSandi = buatPasswordField(160, 190, 190);
        cardPanel.add(txtKataSandi);

        lblTogglePassword = new JLabel("\uD83D\uDC41");
        lblTogglePassword.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        lblTogglePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTogglePassword.setBounds(355, 190, 30, 28);
        cardPanel.add(lblTogglePassword);

        lblTogglePassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                togglePasswordVisibility();
            }
        });

        JLabel lblTipe = new JLabel("Tipe Pengguna:");
        lblTipe.setBounds(60, 230, 120, 25);
        cardPanel.add(lblTipe);
        cmbTipe = new JComboBox<>(new String[]{"-- Pilih --", "rumah", "kantor", "industri"});
        cmbTipe.setBounds(190, 230, 190, 28);
        cmbTipe.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cmbTipe.setBorder(BorderFactory.createLineBorder(new Color(160, 200, 240)));
        cardPanel.add(cmbTipe);

        btnNext = buatButton("Daftar Sekarang", new Color(90, 160, 250), new Color(70, 140, 230));
        btnNext.setBounds(110, 310, 220, 40);
        cardPanel.add(btnNext);

        btnLogin = buatButton("Sudah punya akun?", Color.WHITE, new Color(220, 240, 255));
        btnLogin.setForeground(new Color(70, 120, 200));
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(120, 170, 220), 1, true));
        btnLogin.setBounds(110, 370, 220, 35);
        cardPanel.add(btnLogin);

        btnLogin.addActionListener(e -> {
            new FormLoginPengguna().setVisible(true);
            dispose();
        });

        btnNext.addActionListener(e -> simpanKeDatabase());

        setVisible(true);
    }

    private JTextField buatTextField(int x, int y, int width) {
        JTextField field = new JTextField();
        field.setBounds(x, y, width, 28);
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230)));
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(new Color(90, 160, 250), 2));
            }
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230)));
            }
        });
        return field;
    }

    private JPasswordField buatPasswordField(int x, int y, int width) {
        JPasswordField field = new JPasswordField();
        field.setBounds(x, y, width, 28);
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230)));
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(new Color(90, 160, 250), 2));
            }
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 230)));
            }
        });
        return field;
    }

    private JButton buatButton(String text, Color baseColor, Color hoverColor) {
        JButton btn = new JButton(text);
        btn.setBackground(baseColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hoverColor); }
            public void mouseExited(MouseEvent e) { btn.setBackground(baseColor); }
        });
        return btn;
    }

    private void togglePasswordVisibility() {
        if (showPassword) {
            txtKataSandi.setEchoChar('•');
            lblTogglePassword.setText("\uD83D\uDC41");
        } else {
            txtKataSandi.setEchoChar((char) 0);
            lblTogglePassword.setText("🚫");
        }
        showPassword = !showPassword;
    }

    private void simpanKeDatabase() {
        String nama = txtNama.getText().trim();
        String email = txtEmail.getText().trim();
        String sandi = new String(txtKataSandi.getPassword()).trim();
        String tipe = cmbTipe.getSelectedItem().toString();

        if (nama.isEmpty() || email.isEmpty() || sandi.isEmpty() || tipe.equals("-- Pilih --")) {
            JOptionPane.showMessageDialog(this, "Semua field wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$")) {
            JOptionPane.showMessageDialog(this, "Format email tidak valid! Harus mengandung '@' dan diakhiri dengan .com", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = Koneksi.getConnection()) {
            String cekEmail = "SELECT COUNT(*) FROM pengguna WHERE email = ?";
            PreparedStatement psCek = conn.prepareStatement(cekEmail);
            psCek.setString(1, email);
            ResultSet rs = psCek.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Email sudah terdaftar! Gunakan email lain.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sqlPengguna = "INSERT INTO pengguna (nama_pengguna, email, kata_sandi, tipe_pengguna) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlPengguna, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nama);
            ps.setString(2, email);
            ps.setString(3, sandi);
            ps.setString(4, tipe);
            ps.executeUpdate();

            ResultSet rsKey = ps.getGeneratedKeys();
            int idBaru = 0;
            if (rsKey.next()) idBaru = rsKey.getInt(1);

            String sqlBiasa = "INSERT INTO biasa (id_pengguna, tanggal_daftar, status_aktif) VALUES (?, NOW(), 1)";
            PreparedStatement ps2 = conn.prepareStatement(sqlBiasa);
            ps2.setInt(1, idBaru);
            ps2.executeUpdate();

            JOptionPane.showMessageDialog(this, "Pendaftaran berhasil! Silakan login.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            new FormLoginPengguna().setVisible(true);
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage());
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

        setBounds(0, 0, 416, 309);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
