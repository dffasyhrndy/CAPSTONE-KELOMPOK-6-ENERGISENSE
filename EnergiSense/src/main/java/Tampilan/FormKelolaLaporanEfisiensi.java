/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Tampilan;

import db.Koneksi;
import model.Admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author Administrator
 */
public class FormKelolaLaporanEfisiensi extends JFrame {
    
    private JTable table;
    private DefaultTableModel model;
    private Admin admin;

    /**
     * Creates new form FormKelolaLaporanEfisiensi
     */
    public FormKelolaLaporanEfisiensi(Admin admin) {
        this.admin = admin;
        initUI();
        loadData();
    }
    
    private void initUI() {
        setTitle("EnergiSense - Kelola Laporan Efisiensi");
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelUtama = new JPanel(null);
        panelUtama.setBackground(new Color(200, 230, 255));
        setContentPane(panelUtama);

        JLabel lblWelcome = new JLabel("Admin: " + admin.getNama());
        lblWelcome.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblWelcome.setBounds(30, 25, 400, 30);
        panelUtama.add(lblWelcome);

        JButton btnBack = new JButton("Kembali");
        styleSecondaryButton(btnBack);
        btnBack.setBounds(30, 60, 120, 30);
        btnBack.addActionListener(e -> {
            new FormAdmin(admin).setVisible(true);
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

        JLabel lblTitle = new JLabel("Dashboard Kelola Laporan Efisiensi", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setBounds(0, 110, 1280, 30);
        panelUtama.add(lblTitle);

        JButton btnCreate = new JButton("Buat/Create");
        JButton btnRefresh = new JButton("Refresh");
        JButton btnEdit = new JButton("Edit/Update");
        JButton btnDelete = new JButton("Hapus/Delete");

        JButton[] buttons = {btnCreate, btnRefresh, btnEdit, btnDelete};
        int x = 300;
        for (JButton btn : buttons) {
            btn.setBounds(x, 160, 150, 40);
            stylePrimaryButton(btn);
            panelUtama.add(btn);
            x += 180;
        }

        String[] kolom = {"ID", "Nama Pengguna", "Email", "Periode", "Total Energi (kWh)",
                "Skor Efisiensi", "Saran Hemat"};
        model = new DefaultTableModel(kolom, 0);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(24);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(60, 220, 1160, 430);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(120, 170, 220), 2, true));
        panelUtama.add(scrollPane);

        btnCreate.addActionListener(e -> tambahData());
        btnRefresh.addActionListener(e -> loadData());
        btnEdit.addActionListener(e -> editData());
        btnDelete.addActionListener(e -> hapusData());

        setVisible(true);
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(90, 160, 250));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(70, 140, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(90, 160, 250));
            }
        });
    }

    private void styleSecondaryButton(JButton btn) {
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(70, 120, 200));
        btn.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(120, 170, 220), 1, true));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void loadData() {
        try (Connection conn = Koneksi.getConnection()) {
            String sql = """
                SELECT l.id_laporan, p.nama_pengguna, p.email, l.periode, l.total_energi, 
                       l.skor_efisiensi, l.saran_hemat
                FROM laporan_efisiensi l
                JOIN pengguna p ON l.id_pengguna = p.id_pengguna
                ORDER BY l.id_laporan ASC
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id_laporan"),
                        rs.getString("nama_pengguna"),
                        rs.getString("email"),
                        rs.getString("periode"),
                        rs.getBigDecimal("total_energi"),
                        rs.getBigDecimal("skor_efisiensi"),
                        rs.getString("saran_hemat")
                };
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data laporan: " + e.getMessage());
        }
    }

    private void tambahData() {
        JTextField txtIdPengguna = new JTextField();
        JTextField txtPeriode = new JTextField();
        JTextField txtTotalEnergi = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ID Pengguna (jangan isi ID laporan):"));
        panel.add(txtIdPengguna);
        panel.add(new JLabel("Periode (misal 2025-10):"));
        panel.add(txtPeriode);
        panel.add(new JLabel("Total Energi (kWh):"));
        panel.add(txtTotalEnergi);

        int result = JOptionPane.showConfirmDialog(this, panel, "Tambah Laporan", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                double totalEnergi = Double.parseDouble(txtTotalEnergi.getText());

                double skorEfisiensi = 100 - (totalEnergi / 2);
                if (skorEfisiensi < 0) skorEfisiensi = 0;
                if (skorEfisiensi > 100) skorEfisiensi = 100;

                String saran;
                if (skorEfisiensi >= 85)
                    saran = "Penggunaan energi sudah efisien, pertahankan kebiasaan hemat energi.";
                else if (skorEfisiensi >= 70)
                    saran = "Coba kurangi penggunaan alat elektronik saat tidak dibutuhkan.";
                else if (skorEfisiensi >= 50)
                    saran = "Gunakan peralatan hemat energi dan matikan perangkat idle.";
                else
                    saran = "Tingkatkan efisiensi energi dengan audit penggunaan dan ganti alat boros listrik.";

                try (Connection conn = Koneksi.getConnection()) {
                    String sql = """
                        INSERT INTO laporan_efisiensi 
                        (id_pengguna, periode, total_energi, skor_efisiensi, saran_hemat, admin_id_pengguna)
                        VALUES (?,?,?,?,?,?)
                    """;
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(txtIdPengguna.getText()));
                    ps.setString(2, txtPeriode.getText());
                    ps.setBigDecimal(3, new java.math.BigDecimal(totalEnergi));
                    ps.setBigDecimal(4, new java.math.BigDecimal(skorEfisiensi));
                    ps.setString(5, saran);
                    ps.setInt(6, admin.getId());
                    ps.executeUpdate();
                    loadData();
                    JOptionPane.showMessageDialog(this, "Laporan berhasil ditambahkan!\nSkor: " +
                            skorEfisiensi + "\nSaran: " + saran);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal menambah laporan: " + e.getMessage());
            }
        }
    }

    private void editData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih laporan yang ingin diubah!");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        String periode = (String) model.getValueAt(row, 3);
        String total = String.valueOf(model.getValueAt(row, 4));

        JTextField txtPeriode = new JTextField(periode);
        JTextField txtTotalEnergi = new JTextField(total);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Periode:"));
        panel.add(txtPeriode);
        panel.add(new JLabel("Total Energi (kWh):"));
        panel.add(txtTotalEnergi);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Laporan", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                double totalEnergi = Double.parseDouble(txtTotalEnergi.getText());
                double skorEfisiensi = 100 - (totalEnergi / 2);
                if (skorEfisiensi < 0) skorEfisiensi = 0;
                if (skorEfisiensi > 100) skorEfisiensi = 100;

                String saran;
                if (skorEfisiensi >= 85)
                    saran = "Penggunaan energi sudah efisien, pertahankan kebiasaan hemat energi.";
                else if (skorEfisiensi >= 70)
                    saran = "Coba kurangi penggunaan alat elektronik saat tidak dibutuhkan.";
                else if (skorEfisiensi >= 50)
                    saran = "Gunakan peralatan hemat energi dan matikan perangkat idle.";
                else
                    saran = "Tingkatkan efisiensi energi dengan audit penggunaan dan ganti alat boros listrik.";

                try (Connection conn = Koneksi.getConnection()) {
                    String sql = """
                        UPDATE laporan_efisiensi 
                        SET periode=?, total_energi=?, skor_efisiensi=?, saran_hemat=? 
                        WHERE id_laporan=?
                    """;
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, txtPeriode.getText());
                    ps.setBigDecimal(2, new java.math.BigDecimal(totalEnergi));
                    ps.setBigDecimal(3, new java.math.BigDecimal(skorEfisiensi));
                    ps.setString(4, saran);
                    ps.setInt(5, id);
                    ps.executeUpdate();
                    loadData();
                    JOptionPane.showMessageDialog(this, "Laporan berhasil diperbarui!\nSkor: " +
                            skorEfisiensi + "\nSaran: " + saran);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal mengubah laporan: " + e.getMessage());
            }
        }
    }

    private void hapusData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih laporan yang ingin dihapus!");
            return;
        }
        int id = (int) model.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus laporan ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = Koneksi.getConnection()) {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM laporan_efisiensi WHERE id_laporan=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                loadData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus laporan: " + e.getMessage());
            }
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
