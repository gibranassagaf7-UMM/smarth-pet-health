package smartpethealth.service.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddHealthRecordPanel extends JPanel {

    private int petId;

    private JTextField tanggalField;
    private JTextField beratField;
    private JTextField kondisiField;
    private JTextArea catatanArea;
    private JButton saveBtn;

    private static final Color PASTEL_PURPLE = Color.decode("#D39CFA");

    public AddHealthRecordPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(PASTEL_PURPLE);

        JLabel title = new JLabel("Tambah Riwayat Kesehatan", SwingConstants.CENTER);
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

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel tanggalLabel = new JLabel("Tanggal (DD MM YYYY)");
        tanggalLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        form.add(tanggalLabel, gbc);

        gbc.gridx = 1;
        tanggalField = new JTextField();
        tanggalField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tanggalField.setPreferredSize(new Dimension(200, 30));
        tanggalField.setToolTipText("Masukkan tanggal dalam format DD MM YYYY, contoh: 15 08 2023");
        form.add(tanggalField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel beratLabel = new JLabel("Berat (kg)");
        beratLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        form.add(beratLabel, gbc);

        gbc.gridx = 1;
        beratField = new JTextField();
        beratField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        beratField.setPreferredSize(new Dimension(200, 30));
        beratField.setToolTipText("Masukkan berat dalam kg, contoh: 5.5");
        form.add(beratField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel kondisiLabel = new JLabel("Kondisi");
        kondisiLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        form.add(kondisiLabel, gbc);

        gbc.gridx = 1;
        kondisiField = new JTextField();
        kondisiField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        kondisiField.setPreferredSize(new Dimension(200, 30));
        kondisiField.setToolTipText("Masukkan kondisi kesehatan, contoh: Sehat");
        form.add(kondisiField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel catatanLabel = new JLabel("Catatan");
        catatanLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        form.add(catatanLabel, gbc);

        gbc.gridx = 1;
        catatanArea = new JTextArea(4, 20);
        catatanArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        catatanArea.setLineWrap(true);
        catatanArea.setWrapStyleWord(true);
        catatanArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(102, 51, 153), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        catatanArea.setToolTipText("Masukkan catatan tambahan tentang kesehatan pet");
        JScrollPane scrollPane = new JScrollPane(catatanArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        gbc.weighty = 0.0;
        form.add(scrollPane, gbc);

        add(form, BorderLayout.CENTER);

        saveBtn = new JButton("Simpan");
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        saveBtn.setBackground(new Color(102, 51, 153));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        saveBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ActionListener saveAction = e -> {
            String tanggalText = tanggalField.getText().trim();
            String beratText = beratField.getText().trim();
            String kondisiText = kondisiField.getText().trim();
            String catatanText = catatanArea.getText().trim();

            if (tanggalText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tanggal wajib diisi.", "Input Error", JOptionPane.ERROR_MESSAGE);
                tanggalField.requestFocus();
                return;
            }
            if (beratText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Berat wajib diisi.", "Input Error", JOptionPane.ERROR_MESSAGE);
                beratField.requestFocus();
                return;
            }
            try {
                Double.parseDouble(beratText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Berat harus berupa angka.", "Input Error", JOptionPane.ERROR_MESSAGE);
                beratField.requestFocus();
                return;
            }
            if (kondisiText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Kondisi wajib diisi.", "Input Error", JOptionPane.ERROR_MESSAGE);
                kondisiField.requestFocus();
                return;
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
                LocalDate tanggal = LocalDate.parse(tanggalText, formatter);

                frame.getDataService().addHealthRecord(
                        petId,
                        tanggal,
                        Double.parseDouble(beratText),
                        kondisiText,
                        catatanText
                );

                JOptionPane.showMessageDialog(this, "Riwayat kesehatan berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                frame.showHealthRecords(petId);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        saveBtn.addActionListener(saveAction);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(PASTEL_PURPLE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(saveBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        addActionListener();

        catatanArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
                    e.consume();
                    saveAction.actionPerformed(null);
                }
            }
        });
    }

    private void addActionListener() {
        tanggalField.addActionListener(e -> beratField.requestFocus());
        beratField.addActionListener(e -> kondisiField.requestFocus());
        kondisiField.addActionListener(e -> catatanArea.requestFocus());
    }

    public void setPet(int petId) {
        this.petId = petId;

        tanggalField.setText("");
        beratField.setText("");
        kondisiField.setText("");
        catatanArea.setText("");
    }
}