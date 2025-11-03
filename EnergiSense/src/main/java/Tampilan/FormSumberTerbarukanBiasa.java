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
public class FormSumberTerbarukanBiasa extends JFrame {

    private Biasa user;
    private JTable table;
    private DefaultTableModel model;

    /**
     * Creates new form FormSumberTerbarukanBiasa
     */
    public FormSumberTerbarukanBiasa(Biasa user) {
        this.user = user;
        initUI();
        loadData();
    }
    
    private void initUI() {
        setTitle("EnergiSense - Sumber Terbarukan (Pengguna)");
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

        JLabel lblTitle = new JLabel("Dashboard - Sumber Terbarukan Saya", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setBounds(0,110,1280,30);
        panelUtama.add(lblTitle);

        JButton btnCreate = new JButton("Tambah Data");
        JButton btnRefresh = new JButton("Refresh Data");

        stylePrimaryButton(btnCreate);
        stylePrimaryButton(btnRefresh);

        btnCreate.setBounds(450,160,180,40);
        btnRefresh.setBounds(650,160,180,40);

        panelUtama.add(btnCreate);
        panelUtama.add(btnRefresh);

        String[] kolom = {"ID", "Jenis Sumber", "Kapasitas (kW)", "Kontribusi (kWh)", "Tanggal Catat"};
        model = new DefaultTableModel(kolom,0);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(24);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(60,220,1160,430);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(120,170,220),2,true));
        panelUtama.add(scroll);

        btnCreate.addActionListener(e -> tambahData());
        btnRefresh.addActionListener(e -> loadData());

        setVisible(true);
    }

    private void loadData() {
        try (Connection conn = Koneksi.getConnection()) {
            String sql = "SELECT id_sumber, jenis_sumber, kapasitas_kw, kontribusi_kwh, tanggal_catat " +
                         "FROM sumber_terbarukan WHERE id_pengguna = ? ORDER BY id_sumber DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id_sumber"),
                        rs.getString("jenis_sumber"),
                        rs.getBigDecimal("kapasitas_kw"),
                        rs.getBigDecimal("kontribusi_kwh"),
                        rs.getDate("tanggal_catat")
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

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Jenis Sumber:")); panel.add(jenis);
        panel.add(new JLabel("Kapasitas (kW):")); panel.add(kapasitas);
        panel.add(new JLabel("Kontribusi (kWh):")); panel.add(kontribusi);
        panel.add(new JLabel("Tanggal Catat (YYYY-MM-DD):")); panel.add(tanggal);

        int res = JOptionPane.showConfirmDialog(this, panel, "Tambah Sumber Terbarukan", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try (Connection conn = Koneksi.getConnection()) {
                String sql = "INSERT INTO sumber_terbarukan (id_pengguna, jenis_sumber, kapasitas_kw, kontribusi_kwh, tanggal_catat, admin_id_pengguna) VALUES (?,?,?,?,?,NULL)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, user.getId());
                ps.setString(2, jenis.getText().trim());
                ps.setBigDecimal(3, new java.math.BigDecimal(kapasitas.getText().trim()));
                ps.setBigDecimal(4, new java.math.BigDecimal(kontribusi.getText().trim()));
                ps.setDate(5, java.sql.Date.valueOf(tanggal.getText().trim()));
                ps.executeUpdate();
                loadData();
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambah data: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
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
