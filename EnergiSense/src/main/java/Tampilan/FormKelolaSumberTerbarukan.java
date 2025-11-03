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
public class FormKelolaSumberTerbarukan extends JFrame {
    
    private JTable table;
    private DefaultTableModel model;
    private Admin admin;

    /**
     * Creates new form FormKelolaSumberTerbarukan
     */
    public FormKelolaSumberTerbarukan(Admin admin) {
        this.admin = admin;
        initUI();
        loadData();
    }
    
    private void initUI() {
        setTitle("EnergiSense - Kelola Sumber Energi Terbarukan");
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

        JLabel lblTitle = new JLabel("Dashboard Kelola Sumber Energi Terbarukan", SwingConstants.CENTER);
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

        String[] kolom = {
                "ID Sumber", "Jenis Sumber", "Kapasitas (kW)", "Kontribusi (kWh)",
                "Tanggal Catat", "Pemilik", "Admin Pengawas"
        };
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
                SELECT s.id_sumber, s.jenis_sumber, s.kapasitas_kw, s.kontribusi_kwh, s.tanggal_catat,
                       p.nama_pengguna AS pemilik,
                       (SELECT nama_pengguna FROM pengguna a WHERE a.id_pengguna = s.admin_id_pengguna) AS admin_pengawas
                FROM sumber_terbarukan s
                JOIN pengguna p ON s.id_pengguna = p.id_pengguna
                ORDER BY s.id_sumber ASC
            """;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id_sumber"),
                        rs.getString("jenis_sumber"),
                        rs.getBigDecimal("kapasitas_kw"),
                        rs.getBigDecimal("kontribusi_kwh"),
                        rs.getDate("tanggal_catat"),
                        rs.getString("pemilik"),
                        rs.getString("admin_pengawas")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void tambahData() {
        JTextField jenis = new JTextField();
        JTextField kapasitas = new JTextField();
        JTextField kontribusi = new JTextField();
        JTextField tanggal = new JTextField("YYYY-MM-DD");
        JTextField idPemilik = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Jenis Sumber:"));
        panel.add(jenis);
        panel.add(new JLabel("Kapasitas (kW):"));
        panel.add(kapasitas);
        panel.add(new JLabel("Kontribusi (kWh):"));
        panel.add(kontribusi);
        panel.add(new JLabel("Tanggal Catat (YYYY-MM-DD):"));
        panel.add(tanggal);
        panel.add(new JLabel("ID Pemilik:"));
        panel.add(idPemilik);

        int result = JOptionPane.showConfirmDialog(this, panel, "Tambah Data Sumber", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try (Connection conn = Koneksi.getConnection()) {
                String sql = "INSERT INTO sumber_terbarukan (id_pengguna, jenis_sumber, kapasitas_kw, kontribusi_kwh, tanggal_catat, admin_id_pengguna) VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(idPemilik.getText()));
                ps.setString(2, jenis.getText());
                ps.setBigDecimal(3, new java.math.BigDecimal(kapasitas.getText()));
                ps.setBigDecimal(4, new java.math.BigDecimal(kontribusi.getText()));
                ps.setDate(5, java.sql.Date.valueOf(tanggal.getText()));
                ps.setInt(6, admin.getId());
                ps.executeUpdate();
                loadData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menambah data: " + e.getMessage());
            }
        }
    }

    private void editData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diubah!");
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        String jenis = (String) model.getValueAt(row, 1);
        String kapasitas = model.getValueAt(row, 2).toString();
        String kontribusi = model.getValueAt(row, 3).toString();
        String tanggal = model.getValueAt(row, 4).toString();

        JTextField txtJenis = new JTextField(jenis);
        JTextField txtKapasitas = new JTextField(kapasitas);
        JTextField txtKontribusi = new JTextField(kontribusi);
        JTextField txtTanggal = new JTextField(tanggal);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Jenis Sumber:"));
        panel.add(txtJenis);
        panel.add(new JLabel("Kapasitas (kW):"));
        panel.add(txtKapasitas);
        panel.add(new JLabel("Kontribusi (kWh):"));
        panel.add(txtKontribusi);
        panel.add(new JLabel("Tanggal Catat (YYYY-MM-DD):"));
        panel.add(txtTanggal);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Data Sumber", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try (Connection conn = Koneksi.getConnection()) {
                String sql = "UPDATE sumber_terbarukan SET jenis_sumber=?, kapasitas_kw=?, kontribusi_kwh=?, tanggal_catat=? WHERE id_sumber=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, txtJenis.getText());
                ps.setBigDecimal(2, new java.math.BigDecimal(txtKapasitas.getText()));
                ps.setBigDecimal(3, new java.math.BigDecimal(txtKontribusi.getText()));
                ps.setDate(4, java.sql.Date.valueOf(txtTanggal.getText()));
                ps.setInt(5, id);
                ps.executeUpdate();
                loadData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal mengubah data: " + e.getMessage());
            }
        }
    }

    private void hapusData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
            return;
        }
        int id = (int) model.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = Koneksi.getConnection()) {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM sumber_terbarukan WHERE id_sumber=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                loadData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage());
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
