package smartpethealth.ui;

import smartpethealth.model.Pet;

import javax.swing.*;
import java.awt.*;

public class EditPetPanel extends JPanel {

    private int petId;

    private JTextField namaField;
    private JTextField jenisField;
    private JTextField umurField;
    private JTextField pemilikField;

    public EditPetPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Edit Hewan", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 12, 10, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Nama
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Nama"), gbc);

        gbc.gridx = 1;
        namaField = new JTextField();
        namaField.setPreferredSize(new Dimension(250, 30));
        form.add(namaField, gbc);

        // Jenis
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Jenis"), gbc);

        gbc.gridx = 1;
        jenisField = new JTextField();
        jenisField.setPreferredSize(new Dimension(250, 30));
        form.add(jenisField, gbc);

        // Umur
        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Umur"), gbc);

        gbc.gridx = 1;
        umurField = new JTextField();
        umurField.setPreferredSize(new Dimension(250, 30));
        form.add(umurField, gbc);

        // Pemilik
        gbc.gridx = 0; gbc.gridy = 3;
        form.add(new JLabel("Pemilik"), gbc);

        gbc.gridx = 1;
        pemilikField = new JTextField();
        pemilikField.setPreferredSize(new Dimension(250, 30));
        form.add(pemilikField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 99;
        gbc.weighty = 1;
        form.add(Box.createVerticalGlue(), gbc);

        add(form, BorderLayout.CENTER);

        JButton saveBtn = new JButton("Update");
        saveBtn.addActionListener(e -> {
            frame.getDataService().updatePet(
                    petId,
                    namaField.getText(),
                    jenisField.getText(),
                    umurField.getText(),
                    pemilikField.getText()
            );
            frame.showPage("petList");
        });

        add(saveBtn, BorderLayout.SOUTH);
    }

    public void setPet(Pet pet) {
        this.petId = pet.getId();
        namaField.setText(pet.getNama());
        jenisField.setText(pet.getJenis());
        umurField.setText(pet.getUmur());
        pemilikField.setText(pet.getPemilik());
    }
}
