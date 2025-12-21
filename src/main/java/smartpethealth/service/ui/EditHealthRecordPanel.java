package smartpethealth.service.ui;

import smartpethealth.service.model.HealthRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditHealthRecordPanel extends JPanel {

    private int recordId;
    private int petId;

    private JTextField tanggalField;
    private JTextField beratField;
    private JTextField kondisiField;
    private JTextArea catatanArea;
    private JButton updateBtn;

    private static final Color PASTEL_PURPLE = Color.decode("#D39CFA");

    public EditHealthRecordPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(PASTEL_PURPLE);

        JLabel title = new JLabel("Edit Riwayat Kesehatan", SwingConstants.CENTER);
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
        tanggalField.setToolTipText("Edit tanggal dalam format DD MM YYYY");
        form.add(tanggalField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel beratLabel = new JLabel("Berat (kg)");
        beratLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        form.add(beratLabel, gbc);

        gbc.gridx = 1;
        beratField = new JTextField();
        beratField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        beratField.setPreferredSize(new Dimension(200, 30));
        beratField.setToolTipText("Edit berat dalam kg");
        form.add(beratField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel kondisiLabel = new JLabel("Kondisi");
        kondisiLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        form.add(kondisiLabel, gbc);

        gbc.gridx = 1;
        kondisiField = new JTextField();
        kondisiField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        kondisiField.setPreferredSize(new Dimension(200, 30));
        kondisiField.setToolTipText("Edit kondisi kesehatan");
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
        catatanArea.setToolTipText("Edit catatan tambahan");
        JScrollPane scrollPane = new JScrollPane(catatanArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        gbc.weighty = 0.0;
        form.add(scrollPane, gbc);

        add(form, BorderLayout.CENTER);

        updateBtn = new JButton("Update");
        updateBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        updateBtn.setBackground(new Color(102, 51, 153));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFocusPainted(false);
        updateBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ActionListener updateAction = e -> {
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

                frame.getDataService().updateHealthRecord(
                        recordId,
                        tanggal,
                        Double.parseDouble(beratText),
                        kondisiText,
                        catatanText
                );

                JOptionPane.showMessageDialog(this, "Riwayat kesehatan berhasil diupdate!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                frame.showHealthRecords(petId);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        updateBtn.addActionListener(updateAction);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(PASTEL_PURPLE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(updateBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        editActionListener();

        catatanArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
                    e.consume();
                    updateAction.actionPerformed(null);
                }
            }
        });
    }

    private void editActionListener() {
        tanggalField.addActionListener(e -> beratField.requestFocus());
        beratField.addActionListener(e -> kondisiField.requestFocus());
        kondisiField.addActionListener(e -> catatanArea.requestFocus());
    }

    public void setRecord(HealthRecord r) {
        this.recordId = r.getId();
        this.petId = r.getPetId();

        tanggalField.setText(r.getTanggal().format(DateTimeFormatter.ofPattern("dd MM yyyy")));
        beratField.setText(String.valueOf(r.getBerat()));
        kondisiField.setText(r.getKondisi());
        catatanArea.setText(r.getCatatan());
    }
}