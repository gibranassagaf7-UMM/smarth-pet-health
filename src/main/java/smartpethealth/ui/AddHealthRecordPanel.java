package smartpethealth.ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class AddHealthRecordPanel extends JPanel {

    private int petId;

    private JTextField tanggalField;
    private JTextField beratField;
    private JTextField kondisiField;
    private JTextArea catatanArea;

    public AddHealthRecordPanel(MainFrame frame){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Tambah Health Record", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,12,10,12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tanggal
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Tanggal (YYYY-MM-DD)"), gbc);

        gbc.gridx = 1;
        tanggalField = new JTextField();
        form.add(tanggalField, gbc);

        // Berat
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Berat"), gbc);

        gbc.gridx = 1;
        beratField = new JTextField();
        form.add(beratField, gbc);

        // Kondisi
        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Kondisi"), gbc);

        gbc.gridx = 1;
        kondisiField = new JTextField();
        form.add(kondisiField, gbc);

        // Catatan
        gbc.gridx = 0; gbc.gridy = 3;
        form.add(new JLabel("Catatan"), gbc);

        gbc.gridx = 1;
        catatanArea = new JTextArea(4,20);
        form.add(new JScrollPane(catatanArea), gbc);

        add(form, BorderLayout.CENTER);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            try {
                frame.getDataService().addHealthRecord(
                        petId,
                        LocalDate.parse(tanggalField.getText()),
                        Double.parseDouble(beratField.getText()),
                        kondisiField.getText(),
                        catatanArea.getText()
                );

                // 🔑 AUTO REFRESH
                frame.showHealthRecords(petId);

            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        add(saveBtn, BorderLayout.SOUTH);
    }

    public void setPet(int petId){
        this.petId = petId;

        tanggalField.setText("");
        beratField.setText("");
        kondisiField.setText("");
        catatanArea.setText("");
    }
}
