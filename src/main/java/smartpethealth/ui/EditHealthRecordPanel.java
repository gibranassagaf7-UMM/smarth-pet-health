package smartpethealth.ui;

import smartpethealth.model.HealthRecord;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class EditHealthRecordPanel extends JPanel {

    private int recordId;
    private int petId;

    private JTextField tanggalField;
    private JTextField beratField;
    private JTextField kondisiField;
    private JTextArea catatanArea;

    public EditHealthRecordPanel(MainFrame frame){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Edit Health Record", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,12,10,12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Tanggal"), gbc);

        gbc.gridx = 1;
        tanggalField = new JTextField();
        form.add(tanggalField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Berat"), gbc);

        gbc.gridx = 1;
        beratField = new JTextField();
        form.add(beratField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Kondisi"), gbc);

        gbc.gridx = 1;
        kondisiField = new JTextField();
        form.add(kondisiField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        form.add(new JLabel("Catatan"), gbc);

        gbc.gridx = 1;
        catatanArea = new JTextArea(4,20);
        form.add(new JScrollPane(catatanArea), gbc);

        add(form, BorderLayout.CENTER);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> {
            try {
                frame.getDataService().updateHealthRecord(
                        recordId,
                        LocalDate.parse(tanggalField.getText()),
                        Double.parseDouble(beratField.getText()),
                        kondisiField.getText(),
                        catatanArea.getText()
                );
                frame.showHealthRecords(petId);

            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        add(updateBtn, BorderLayout.SOUTH);
    }

    public void setRecord(HealthRecord r){
        this.recordId = r.getId();
        this.petId = r.getPetId();

        tanggalField.setText(r.getTanggal().toString());
        beratField.setText(String.valueOf(r.getBerat()));
        kondisiField.setText(r.getKondisi());
        catatanArea.setText(r.getCatatan());
    }
}
