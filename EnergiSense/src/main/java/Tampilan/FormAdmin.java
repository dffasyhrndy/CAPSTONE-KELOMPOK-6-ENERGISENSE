/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Tampilan;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import model.Admin;
  
/**
 *
 * @author Administrator
 */
public class FormAdmin extends JFrame {
    
    private Admin admin;

    public FormAdmin(Admin admin) {
        this.admin = admin;
        initUI();
    }
    
    private void initUI() {
        setTitle("EnergiSense - Admin");
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel main = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bg = new ImageIcon("src/energisense_logo.png");
                Image img = bg.getImage();
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.18f));
                int w = getWidth() / 4, h = getHeight() / 4;
                g2.drawImage(img, 30, 30, w, h, this);
                g2.drawImage(img, getWidth() - w - 30, getHeight() - h - 30, w, h, this);
                g2.dispose();
            }
        };
        main.setBackground(new Color(210, 235, 255));
        setContentPane(main);

        JPanel card = new JPanel(null);
        card.setBounds(220, 120, 840, 520);
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(120, 170, 220), 2, true),
                new EmptyBorder(15, 15, 15, 15)
        ));
        card.setOpaque(true);
        card.setVisible(false);
        main.add(card);

        Timer timer = new Timer(3, null);
        final int[] y = {720};
        timer.addActionListener(e -> {
            y[0] -= 20;
            if (y[0] <= 120) {
                y[0] = 120;
                timer.stop();
                card.setVisible(true);
            }
            card.setLocation(220, y[0]);
        });
        timer.start();

        JLabel lbl = new JLabel(
                "Hai " + (admin.getNama() == null ? "Admin" : admin.getNama()) + ", Mau kemana nih?",
                SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 22));
        lbl.setBounds(50, 30, 740, 40);
        card.add(lbl);

        JButton b1 = buatTombol("Kelola Daftar Pengguna Biasa", 120);
        JButton b2 = buatTombol("Kelola Daftar Perangkat", 190);
        JButton b3 = buatTombol("Kelola Daftar Sumber Terbarukan", 260);
        JButton b4 = buatTombol("Kelola Daftar Laporan", 330);
        JButton b5 = buatTombol("LogOut / Kembali", 410);

        card.add(b1);
        card.add(b2);
        card.add(b3);
        card.add(b4);
        card.add(b5);

        b1.addActionListener(e -> {
            new FormKelolaBiasa(admin).setVisible(true);
            dispose();
        });
        b2.addActionListener(e -> {
            new FormKelolaPerangkat(admin).setVisible(true);
            dispose();
        });
        b3.addActionListener(e -> {
            new FormKelolaSumberTerbarukan(admin).setVisible(true);
            dispose();
        });
        b4.addActionListener(e -> {
            new FormKelolaLaporanEfisiensi(admin).setVisible(true);
            dispose();
        });
        b5.addActionListener(e -> {
            new FormLoginPengguna().setVisible(true);
            dispose();
        });

        setVisible(true);
    }

    private JButton buatTombol(String teks, int y) {
        JButton btn = new JButton(teks);
        btn.setBounds(200, y, 440, 45);
        Color base = new Color(90, 160, 250);
        Color hover = new Color(70, 140, 230);
        btn.setBackground(base);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorder(new LineBorder(new Color(120, 170, 220), 1, true));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(hover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(base);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(50, 120, 200));
                btn.setBounds(202, y + 2, 436, 41);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn.setBackground(hover);
                btn.setBounds(200, y, 440, 45);
            }
        });
        return btn;
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