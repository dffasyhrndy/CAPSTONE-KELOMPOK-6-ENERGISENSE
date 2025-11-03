/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Tampilan;

import db.Koneksi;
import model.Biasa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author Administrator
 */
public class FormLaporanEfisiensiBiasa extends JFrame {

    private Biasa user;
    private JTable table;
    private DefaultTableModel model;

    /**
     * Creates new form FormLaporanEfisiensiBiasa
     */
    public FormLaporanEfisiensiBiasa(Biasa user) {
        this.user = user;
        initUI();
        loadData();
    }
    
    private void initUI() {
        setTitle("EnergiSense - Laporan Efisiensi (Pengguna)");
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelUtama = new JPanel(null);
        panelUtama.setBackground(new Color(200, 230, 255));
        setContentPane(panelUtama);

        JLabel lblWelcome = new JLabel("Pengguna: " + user.getNama());
        lblWelcome.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblWelcome.setBounds(30, 25, 400, 30);
        panelUtama.add(lblWelcome);

        JButton btnBack = new JButton("Kembali");
        styleSecondaryButton(btnBack);
        btnBack.setBounds(30, 60, 120, 30);
        btnBack.addActionListener(e -> {
            new FormBiasa(user).setVisible(true);
            dispose();
        });
        panelUtama.add(btnBack);

        ImageIcon icon = new ImageIcon("src/energisense_logo.png");
        Image img = icon.getImage();
        int targetWidth = 120;
        int targetHeight = (int) ((double) img.getHeight(null) / img.getWidth(null) * targetWidth);
        Image scaled = img.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(scaled));
        lblLogo.setBounds(1120, 20, targetWidth, targetHeight);
        panelUtama.add(lblLogo);

        JLabel lblTitle = new JLabel("Dashboard - Laporan Efisiensi Saya", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setBounds(0, 110, 1280, 30);
        panelUtama.add(lblTitle);

        JButton btnCreate = new JButton("Buat Laporan");
        JButton btnRefresh = new JButton("Refresh");

        stylePrimaryButton(btnCreate);
        stylePrimaryButton(btnRefresh);
        btnCreate.setBounds(420, 160, 200, 40);
        btnRefresh.setBounds(640, 160, 200, 40);

        panelUtama.add(btnCreate);
        panelUtama.add(btnRefresh);

        String[] kolom = {"ID", "Periode", "Total Energi (kWh)", "Skor Efisiensi", "Saran Hemat"};
        model = new DefaultTableModel(kolom, 0);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(24);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(60, 220, 1160, 430);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(120, 170, 220), 2, true));
        panelUtama.add(scroll);

        btnCreate.addActionListener(e -> tambahLaporan());
        btnRefresh.addActionListener(e -> loadData());

        setVisible(true);
    }

    private void loadData() {
        try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT id_laporan, periode, total_energi, skor_efisiensi, saran_hemat " +
                         "FROM laporan_efisiensi WHERE id_pengguna = ? ORDER BY id_laporan DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id_laporan"),
                        rs.getString("periode"),
                        rs.getBigDecimal("total_energi"),
                        rs.getBigDecimal("skor_efisiensi"),
                        rs.getString("saran_hemat")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void tambahLaporan() {
        JTextField txtPeriode = new JTextField();
        JTextField txtTotal = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Periode (mis. 2025-10):"));
        panel.add(txtPeriode);
        panel.add(new JLabel("Total Energi (kWh):"));
        panel.add(txtTotal);

        int res = JOptionPane.showConfirmDialog(this, panel, "Buat Laporan Efisiensi", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                double totalEnergi = Double.parseDouble(txtTotal.getText().trim());

                double skor = hitungSkorEfisiensi(totalEnergi);
                String saran = generateSaran(skor);

                try (Connection conn = Koneksi.getConnection()) {
                    String sql = "INSERT INTO laporan_efisiensi (id_pengguna, periode, total_energi, skor_efisiensi, saran_hemat, admin_id_pengguna) VALUES (?,?,?,?,?,NULL)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, user.getId());
                    ps.setString(2, txtPeriode.getText().trim());
                    ps.setBigDecimal(3, new java.math.BigDecimal(totalEnergi));
                    ps.setBigDecimal(4, new java.math.BigDecimal(skor));
                    ps.setString(5, saran);
                    ps.executeUpdate();
                    loadData();
                    JOptionPane.showMessageDialog(this, "Laporan tersimpan.\nSkor: " + skor + "\nSaran: " + saran);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Total energi harus angka.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan laporan: " + ex.getMessage());
            }
        }
    }

    private double hitungSkorEfisiensi(double totalEnergi) {
        double skor = 100 - (totalEnergi / 2.0);
        if (skor < 0) skor = 0;
        if (skor > 100) skor = 100;
        return Math.round(skor * 100.0) / 100.0;
    }

    private String generateSaran(double skor) {
        if (skor >= 85) return "Penggunaan energi sudah efisien, pertahankan kebiasaan hemat energi.";
        if (skor >= 70) return "Coba kurangi penggunaan alat elektronik saat tidak dibutuhkan.";
        if (skor >= 50) return "Gunakan peralatan hemat energi dan matikan perangkat idle.";
        return "Tingkatkan efisiensi energi dengan audit penggunaan dan ganti alat boros listrik.";
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(90, 160, 250));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void styleSecondaryButton(JButton btn) {
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(70,120,200));
        btn.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(120,170,220),1,true));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
