package smartpethealth.service.ui;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {

    private static final Color PASTEL_PURPLE = Color.decode("#D39CFA");

    public HelpPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(PASTEL_PURPLE);

        JLabel title = new JLabel("Panduan Penggunaan", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JTextArea helpText = new JTextArea();
        helpText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        helpText.setEditable(false);
        helpText.setText("Panduan:\n\n" +
                "1. Dashboard: Lihat statistik dan navigasi.\n" +
                "2. Daftar Hewan: Tambah, edit, hapus pet.\n" +
                "3. Riwayat Kesehatan: Lihat, tambah, edit, hapus record kesehatan.\n" +
                "4. Laporan: Generate laporan kesehatan.\n" +
                "5. Pengaturan: Backup data, ubah tema.\n" +
                "6. Notifikasi: Cek reminder kesehatan.\n" +
                "7. Pencarian: Cari pet atau kondisi.\n\n" +
                "Tips: Isi semua field wajib, catatan opsional. Gunakan format tanggal DD MM YYYY.");
        helpText.setBackground(new Color(255, 255, 255, 200));
        helpText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(helpText);
        add(scrollPane, BorderLayout.CENTER);

        JButton backBtn = new JButton("Kembali");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backBtn.setBackground(new Color(102, 51, 153));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> frame.showPage("dashboard"));

        JPanel bottom = new JPanel();
        bottom.setBackground(PASTEL_PURPLE);
        bottom.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }
}