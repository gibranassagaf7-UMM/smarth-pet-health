package smartpethealth.service.ui;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    private static final Color PASTEL_PURPLE = Color.decode("#D39CFA");

    public SettingsPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(PASTEL_PURPLE);

        JLabel title = new JLabel("Pengaturan", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(new Color(255, 255, 255, 200));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel backupLabel = new JLabel("Backup Data:");
        backupLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        form.add(backupLabel, gbc);

        gbc.gridx = 1;
        JButton backupBtn = new JButton("Export Backup");
        backupBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backupBtn.setBackground(new Color(102, 51, 153));
        backupBtn.setForeground(Color.WHITE);
        backupBtn.setFocusPainted(false);
        backupBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                frame.getDataService().exportData(chooser.getSelectedFile().getAbsolutePath() + ".json");
                JOptionPane.showMessageDialog(this, "Backup berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        form.add(backupBtn, gbc);

        add(form, BorderLayout.CENTER);

        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backBtn.setBackground(new Color(102, 51, 153));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        backBtn.addActionListener(e -> frame.showPage("dashboard"));

        JPanel bottom = new JPanel();
        bottom.setBackground(PASTEL_PURPLE);
        bottom.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }
}