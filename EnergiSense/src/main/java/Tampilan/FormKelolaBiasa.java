/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Tampilan;

import db.Koneksi;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import model.Admin;

/**
 *
 * @author Administrator
 */
public class FormKelolaBiasa extends javax.swing.JFrame {
    
    private JTable table;
    private DefaultTableModel model;
    private Admin admin;
    private JLabel lblTotal;
    
    
    public FormKelolaBiasa(Admin admin) {
        this.admin = admin;
        initUI();
        loadData();
    }
    
    private void initUI() {
        setTitle("EnergiSense - Kelola Pengguna Biasa");
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelUtama = new JPanel(null);
        panelUtama.setBackground(new Color(200, 230, 255));
        setContentPane(panelUtama);

        JLabel lblWelcome = new JLabel("Selamat datang " + admin.getNama());
        lblWelcome.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblWelcome.setBounds(30, 25, 400, 30);
        panelUtama.add(lblWelcome);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(30, 60, 120, 30);
        styleSecondaryButton(btnLogout);
        btnLogout.addActionListener(e -> {
            new FormLoginPengguna().setVisible(true);
            dispose();
        });
        panelUtama.add(btnLogout);

        ImageIcon icon = new ImageIcon("src/energisense_logo.png");
        Image img = icon.getImage();
        int targetWidth = 120;
        int targetHeight = (int) ((double) img.getHeight(null) / img.getWidth(null) * targetWidth);
        Image scaled = img.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(scaled));
        lblLogo.setBounds(1120, 20, targetWidth, targetHeight);
        panelUtama.add(lblLogo);

        JLabel lblTitle = new JLabel("Dashboard Kelola Pengguna Biasa", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setBounds(0, 110, 1280, 30);
        panelUtama.add(lblTitle);

        JButton btnCreate = new JButton("Buat/Create");
        JButton btnRefresh = new JButton("Refresh");
        JButton btnEdit = new JButton("Edit/Update");
        JButton btnDelete = new JButton("Hapus/Delete");
        JButton btnBack = new JButton("Kembali");

        JButton[] buttons = {btnCreate, btnRefresh, btnEdit, btnDelete, btnBack};
        int x = 200;
        for (JButton btn : buttons) {
            btn.setBounds(x, 160, 150, 40);
            stylePrimaryButton(btn);
            panelUtama.add(btn);
            x += 180;
        }

        String[] kolom = {"ID", "Nama", "Email", "Tipe", "Tanggal Daftar", "Status Aktif"};
        model = new DefaultTableModel(kolom, 0);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(60, 220, 1160, 400);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(120, 170, 220), 2, true));
        panelUtama.add(scrollPane);

        lblTotal = new JLabel("Total Data: 0", SwingConstants.RIGHT);
        lblTotal.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblTotal.setBounds(950, 640, 250, 25);
        panelUtama.add(lblTotal);

        btnCreate.addActionListener(e -> tambahData());
        btnRefresh.addActionListener(e -> loadData());
        btnEdit.addActionListener(e -> editData());
        btnDelete.addActionListener(e -> hapusData());
        btnBack.addActionListener(e -> {
            new FormAdmin(admin).setVisible(true);
            dispose();
        });

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
        try (Connection c = Koneksi.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(
                     "SELECT p.id_pengguna, p.nama_pengguna, p.email, p.tipe_pengguna, b.tanggal_daftar, b.status_aktif " +
                             "FROM pengguna p JOIN biasa b ON p.id_pengguna = b.id_pengguna")) {
            model.setRowCount(0);
            int count = 0;
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id_pengguna"),
                        rs.getString("nama_pengguna"),
                        rs.getString("email"),
                        rs.getString("tipe_pengguna"),
                        rs.getDate("tanggal_daftar"),
                        rs.getBoolean("status_aktif") ? "Aktif" : "Nonaktif"
                };
                model.addRow(row);
                count++;
            }
            lblTotal.setText("Total Data: " + count);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void tambahData() {
        JTextField nama = new JTextField();
        JTextField email = new JTextField();
        JTextField pass = new JTextField();
        JComboBox<String> tipe = new JComboBox<>(new String[]{"rumah", "kantor", "industri"});

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nama:"));
        panel.add(nama);
        panel.add(new JLabel("Email:"));
        panel.add(email);
        panel.add(new JLabel("Kata Sandi:"));
        panel.add(pass);
        panel.add(new JLabel("Tipe Pengguna:"));
        panel.add(tipe);

        int result = JOptionPane.showConfirmDialog(this, panel, "Tambah Pengguna", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try (Connection c = Koneksi.getConnection()) {
                String sql = "INSERT INTO pengguna (nama_pengguna, email, kata_sandi, tipe_pengguna) VALUES (?,?,?,?)";
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, nama.getText());
                ps.setString(2, email.getText());
                ps.setString(3, pass.getText());
                ps.setString(4, tipe.getSelectedItem().toString());
                ps.executeUpdate();

                ResultSet gen = ps.getGeneratedKeys();
                if (gen.next()) {
                    int id = gen.getInt(1);
                    PreparedStatement ps2 = c.prepareStatement(
                            "INSERT INTO biasa (id_pengguna, tanggal_daftar, status_aktif) VALUES (?,?,?)");
                    ps2.setInt(1, id);
                    ps2.setDate(2, new java.sql.Date(System.currentTimeMillis()));
                    ps2.setBoolean(3, true);
                    ps2.executeUpdate();
                }
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
        String nama = (String) model.getValueAt(row, 1);
        String email = (String) model.getValueAt(row, 2);
        String tipe = (String) model.getValueAt(row, 3);

        String oldPass = "";
        try (Connection c = Koneksi.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT kata_sandi FROM pengguna WHERE id_pengguna=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) oldPass = rs.getString("kata_sandi");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data kata sandi: " + e.getMessage());
        }

        JTextField txtNama = new JTextField(nama);
        JTextField txtEmail = new JTextField(email);
        JPasswordField txtPass = new JPasswordField(oldPass);
        JComboBox<String> cmbTipe = new JComboBox<>(new String[]{"rumah", "kantor", "industri"});
        cmbTipe.setSelectedItem(tipe);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nama:"));
        panel.add(txtNama);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Kata Sandi:"));
        panel.add(txtPass);
        panel.add(new JLabel("Tipe Pengguna:"));
        panel.add(cmbTipe);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Pengguna", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try (Connection c = Koneksi.getConnection()) {
                PreparedStatement ps = c.prepareStatement(
                        "UPDATE pengguna SET nama_pengguna=?, email=?, kata_sandi=?, tipe_pengguna=? WHERE id_pengguna=?");
                ps.setString(1, txtNama.getText());
                ps.setString(2, txtEmail.getText());
                ps.setString(3, new String(txtPass.getPassword()));
                ps.setString(4, cmbTipe.getSelectedItem().toString());
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
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus pengguna ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection c = Koneksi.getConnection()) {
                c.prepareStatement("DELETE FROM biasa WHERE id_pengguna=" + id).executeUpdate();
                c.prepareStatement("DELETE FROM pengguna WHERE id_pengguna=" + id).executeUpdate();
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
