package smartpethealth.service.ui;

import smartpethealth.service.model.HealthRecord;
import smartpethealth.service.model.Pet;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReportsPanel extends JPanel {

    private static final Color PASTEL_PURPLE = Color.decode("#D39CFA");

    public ReportsPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(PASTEL_PURPLE);

        JLabel title = new JLabel("Laporan Kesehatan", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JTextArea reportArea = new JTextArea();
        reportArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        reportArea.setEditable(false);
        reportArea.setBackground(new Color(255, 255, 255, 200));
        reportArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        reportArea.setOpaque(true);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setOpaque(true);
        add(scrollPane, BorderLayout.CENTER);

        JButton generateBtn = new JButton("Generate Laporan");
        generateBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        generateBtn.setBackground(new Color(102, 51, 153));
        generateBtn.setForeground(Color.WHITE);
        generateBtn.setFocusPainted(false);
        generateBtn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        generateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generateBtn.setOpaque(true);

        generateBtn.addActionListener(e -> {
            StringBuilder report = new StringBuilder();
            report.append("Laporan Kesehatan Hewan Peliharaan\n\n");

            List<Pet> pets = frame.getDataService().getAllPets();
            for (Pet pet : pets) {
                report.append("Pet: ").append(pet.getNama()).append(" (").append(pet.getJenis()).append(")\n");
                List<HealthRecord> records = frame.getDataService().getRecordsByPet(pet.getId());
                if (records.isEmpty()) {
                    report.append("  Tidak ada riwayat kesehatan.\n");
                } else {
                    for (HealthRecord r : records) {
                        report.append("  - ").append(r.getTanggal()).append(": ").append(r.getKondisi()).append(", Berat: ").append(r.getBerat()).append("kg\n");
                    }
                }
                report.append("\n");
            }
            reportArea.setText(report.toString());

            SwingUtilities.invokeLater(() -> {
                this.revalidate();
                this.repaint();
                reportArea.revalidate();
                reportArea.repaint();
                scrollPane.revalidate();
                scrollPane.repaint();
            });

            reportArea.requestFocusInWindow();
        });

        JButton backBtn = new JButton("Kembali");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backBtn.setBackground(new Color(102, 51, 153));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> frame.showPage("dashboard"));

        JPanel bottom = new JPanel();
        bottom.setBackground(PASTEL_PURPLE);
        bottom.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        bottom.add(generateBtn);
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }
}