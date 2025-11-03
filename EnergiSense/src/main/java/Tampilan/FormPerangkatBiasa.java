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
import java.sql.*;

/**
 *
 * @author Administrator
 */
public class FormPerangkatBiasa extends JFrame {

    private Biasa user;
    private JTable table;
    private DefaultTableModel model;

    /**
     * Creates new form FormPerangkatBiasa
     */
    public FormPerangkatBiasa(Biasa user) {
        this.user = user;
        initUI();
        loadData();
    }
    
    private void initUI() {
        setTitle("EnergiSense - Perangkat Saya");
        setSize(1280,720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelUtama = new JPanel(null);
        panelUtama.setBackground(new Color(200,230,255));
        setContentPane(panelUtama);

        JLabel lblWelcome = new JLabel("Pengguna: " + user.getNama());
        lblWelcome.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblWelcome.setBounds(30,25,400,30);
        panelUtama.add(lblWelcome);

        JButton btnBack = new JButton("Kembali");
        styleSecondaryButton(btnBack);
        btnBack.setBounds(30,60,120,30);
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
        lblLogo.setBounds(1120,20,targetWidth,targetHeight);
        panelUtama.add(lblLogo);

        JLabel lblTitle = new JLabel("Dashboard - Perangkat Saya", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setBounds(0,110,1280,30);
        panelUtama.add(lblTitle);

        JButton btnCreate = new JButton("Tambah");
        JButton btnRefresh = new JButton("Refresh");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Hapus");

        stylePrimaryButton(btnCreate); stylePrimaryButton(btnRefresh);
        stylePrimaryButton(btnEdit); stylePrimaryButton(btnDelete);

        btnCreate.setBounds(300,160,150,40);
        btnRefresh.setBounds(480,160,150,40);
        btnEdit.setBounds(660,160,150,40);
        btnDelete.setBounds(840,160,150,40);

        panelUtama.add(btnCreate); panelUtama.add(btnRefresh);
        panelUtama.add(btnEdit); panelUtama.add(btnDelete);

        String[] kolom = {"ID", "Nama Perangkat", "Daya (W)", "Status"};
        model = new DefaultTableModel(kolom, 0);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(24);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(60,220,1160,430);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(120,170,220),2,true));
        panelUtama.add(scroll);

        btnCreate.addActionListener(e -> tambahPerangkat());
        btnRefresh.addActionListener(e -> loadData());
        btnEdit.addActionListener(e -> editPerangkat());
        btnDelete.addActionListener(e -> hapusPerangkat());

        setVisible(true);
    }

    private void loadData() {
        try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT id_perangkat, nama_perangkat, daya_watt, status_perangkat FROM perangkat WHERE id_pengguna = ? ORDER BY id_perangkat DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id_perangkat"),
                        rs.getString("nama_perangkat"),
                        rs.getBigDecimal("daya_watt"),
                        rs.getString("status_perangkat")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data perangkat: " + e.getMessage());
        }
    }

    private void tambahPerangkat() {
        JTextField nama = new JTextField();
        JTextField daya = new JTextField();
        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"ON","OFF"});

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Nama Perangkat:")); panel.add(nama);
        panel.add(new JLabel("Daya (W):")); panel.add(daya);
        panel.add(new JLabel("Status:")); panel.add(cmbStatus);

        int res = JOptionPane.showConfirmDialog(this, panel, "Tambah Perangkat", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try (Connection conn = Koneksi.getConnection()) {
                String sql = "INSERT INTO perangkat (id_pengguna, nama_perangkat, daya_watt, status_perangkat, admin_id_pengguna) VALUES (?,?,?,?,NULL)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, user.getId());
                ps.setString(2, nama.getText().trim());
                ps.setBigDecimal(3, new java.math.BigDecimal(daya.getText().trim()));
                ps.setString(4, cmbStatus.getSelectedItem().toString());
                ps.executeUpdate();
                loadData();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambah perangkat: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
            }
        }
    }

    private void editPerangkat() {
        int row = table.getSelectedRow();
        if (row == -1) { JOptionPane.showMessageDialog(this, "Pilih perangkat untuk diedit!"); return; }
        int id = (int) model.getValueAt(row, 0);
        String nama = (String) model.getValueAt(row,1);
        String daya = model.getValueAt(row,2).toString();
        String status = (String) model.getValueAt(row,3);

        JTextField txtNama = new JTextField(nama);
        JTextField txtDaya = new JTextField(daya);
        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"ON","OFF"});
        cmbStatus.setSelectedItem(status);

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Nama Perangkat:")); panel.add(txtNama);
        panel.add(new JLabel("Daya (W):")); panel.add(txtDaya);
        panel.add(new JLabel("Status:")); panel.add(cmbStatus);

        int res = JOptionPane.showConfirmDialog(this, panel, "Edit Perangkat", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try (Connection conn = Koneksi.getConnection()) {
                String sql = "UPDATE perangkat SET nama_perangkat=?, daya_watt=?, status_perangkat=? WHERE id_perangkat=? AND id_pengguna=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, txtNama.getText().trim());
                ps.setBigDecimal(2, new java.math.BigDecimal(txtDaya.getText().trim()));
                ps.setString(3, cmbStatus.getSelectedItem().toString());
                ps.setInt(4, id);
                ps.setInt(5, user.getId());
                int updated = ps.executeUpdate();
                if (updated == 0) JOptionPane.showMessageDialog(this, "Tidak punya akses mengubah perangkat ini.");
                loadData();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal mengubah perangkat: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
            }
        }
    }

    private void hapusPerangkat() {
        int row = table.getSelectedRow();
        if (row == -1) { JOptionPane.showMessageDialog(this, "Pilih perangkat untuk dihapus!"); return; }
        int id = (int) model.getValueAt(row, 0);
        int conf = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus perangkat ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
            try (Connection conn = Koneksi.getConnection()) {
                String sql = "DELETE FROM perangkat WHERE id_perangkat=? AND id_pengguna=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setInt(2, user.getId());
                int del = ps.executeUpdate();
                if (del == 0) JOptionPane.showMessageDialog(this, "Tidak punya akses menghapus perangkat ini.");
                loadData();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus: " + ex.getMessage());
            }
        }
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(90,160,250)); btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14)); btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder()); btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    private void styleSecondaryButton(JButton btn) {
        btn.setBackground(Color.WHITE); btn.setForeground(new Color(70,120,200));
        btn.setFont(new Font("SansSerif", Font.PLAIN, 13)); btn.setFocusPainted(false);
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
