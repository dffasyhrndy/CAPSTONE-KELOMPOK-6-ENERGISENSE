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
public class FormKelolaPerangkat extends JFrame {
    
    private JTable table;
    private DefaultTableModel model;
    private Admin admin;

    /**
     * Creates new form FormKelolaPerangkat
     */
    public FormKelolaPerangkat(Admin admin) {
        this.admin = admin;
        initUI();
        loadData();
    }
    
    private void initUI() {
        setTitle("EnergiSense - Kelola Daftar Perangkat (View Only)");
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

        JLabel lblTitle = new JLabel("Dashboard - Daftar Perangkat Pengguna", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setBounds(0, 110, 1280, 30);
        panelUtama.add(lblTitle);

        JButton btnRefresh = new JButton("Refresh Data");
        stylePrimaryButton(btnRefresh);
        btnRefresh.setBounds(560, 160, 150, 40);
        btnRefresh.addActionListener(e -> loadData());
        panelUtama.add(btnRefresh);

        String[] kolom = {"ID Perangkat", "Nama Perangkat", "Daya (W)", "Status", "Pemilik", "Email Pemilik", "Admin Pengawas"};
        model = new DefaultTableModel(kolom, 0);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(24);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(60, 220, 1160, 430);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(120, 170, 220), 2, true));
        panelUtama.add(scrollPane);

        setVisible(true);
    }

    private void loadData() {
        try (Connection conn = Koneksi.getConnection()) {
            String sql = """
                SELECT 
                    pr.id_perangkat, 
                    pr.nama_perangkat, 
                    pr.daya_watt AS daya, 
                    pr.status_perangkat AS status, 
                    p.nama_pengguna, 
                    p.email, 
                    (SELECT nama_pengguna FROM pengguna a WHERE a.id_pengguna = pr.admin_id_pengguna) AS admin_pengawas
                FROM perangkat pr
                JOIN pengguna p ON pr.id_pengguna = p.id_pengguna
                ORDER BY pr.id_perangkat ASC
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id_perangkat"),
                        rs.getString("nama_perangkat"),
                        rs.getString("daya") + " W",
                        rs.getString("status"),
                        rs.getString("nama_pengguna"),
                        rs.getString("email"),
                        rs.getString("admin_pengawas")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data perangkat: " + e.getMessage());
        }
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
