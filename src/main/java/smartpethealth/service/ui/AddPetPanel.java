package smartpethealth.service.ui;

import javax.swing.*;
import java.awt.*;

public class AddPetPanel extends JPanel {

    private JTextField namaField;
    private JTextField jenisField;
    private JTextField umurField;
    private JTextField pemilikField;
    private JButton saveBtn;
    private JButton cancelBtn;

    private static final Color PASTEL_PURPLE = Color.decode("#D39CFA");

    public AddPetPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(PASTEL_PURPLE);

        JLabel title = new JLabel("Tambah Hewan Peliharaan", SwingConstants.CENTER);
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
        JLabel namaLabel = new JLabel("Nama");
        namaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        form.add(namaLabel, gbc);

        gbc.gridx = 1;
        namaField = new JTextField();
        namaField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        namaField.setPreferredSize(new Dimension(250, 30));
        form.add(namaField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel jenisLabel = new JLabel("Jenis");
        jenisLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        form.add(jenisLabel, gbc);

        gbc.gridx = 1;
        jenisField = new JTextField();
        jenisField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jenisField.setPreferredSize(new Dimension(250, 30));
        form.add(jenisField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel umurLabel = new JLabel("Umur");
        umurLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        form.add(umurLabel, gbc);

        gbc.gridx = 1;
        umurField = new JTextField();
        umurField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        umurField.setPreferredSize(new Dimension(250, 30));
        form.add(umurField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel pemilikLabel = new JLabel("Pemilik");
        pemilikLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        form.add(pemilikLabel, gbc);

        gbc.gridx = 1;
        pemilikField = new JTextField();
        pemilikField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pemilikField.setPreferredSize(new Dimension(250, 30));
        form.add(pemilikField, gbc);

        add(form, BorderLayout.CENTER);

        saveBtn = new JButton("Simpan");
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        saveBtn.setBackground(new Color(102, 51, 153));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        saveBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancelBtn = new JButton("Batal");
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        cancelBtn.setBackground(new Color(102, 51, 153));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        saveBtn.addActionListener(e -> {
            String nama = namaField.getText().trim();
            String jenis = jenisField.getText().trim();
            String umur = umurField.getText().trim();
            String pemilik = pemilikField.getText().trim();

            if (isValidInput(nama, jenis, umur, pemilik)) return;

            try {
                frame.getDataService().addPet(nama, jenis, umur, pemilik);
                JOptionPane.showMessageDialog(this, "Hewan peliharaan berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                frame.showPage("petList");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> {
            clearFields();
            frame.showPage("petList");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(PASTEL_PURPLE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        namaField.addActionListener(e -> jenisField.requestFocus());
        jenisField.addActionListener(e -> umurField.requestFocus());
        umurField.addActionListener(e -> pemilikField.requestFocus());
        pemilikField.addActionListener(e -> saveBtn.doClick());
    }

    private boolean isValidInput(String nama, String jenis, String umur, String pemilik) {
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama wajib diisi.", "Input Error", JOptionPane.ERROR_MESSAGE);
            namaField.requestFocus();
            return true;
        }
        if (jenis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jenis wajib diisi.", "Input Error", JOptionPane.ERROR_MESSAGE);
            jenisField.requestFocus();
            return true;
        }
        if (umur.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Umur wajib diisi.", "Input Error", JOptionPane.ERROR_MESSAGE);
            umurField.requestFocus();
            return true;
        }
        if (pemilik.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pemilik wajib diisi.", "Input Error", JOptionPane.ERROR_MESSAGE);
            pemilikField.requestFocus();
            return true;
        }
        return false;
    }

    private void clearFields() {
        namaField.setText("");
        jenisField.setText("");
        umurField.setText("");
        pemilikField.setText("");
    }
}