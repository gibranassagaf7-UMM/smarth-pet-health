package smartpethealth.ui;

import javax.swing.*;
import java.awt.*;

public class AddPetPanel extends JPanel {

    public AddPetPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Tambah Hewan", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 12, 10, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        //nama
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0; gbc.weighty = 0;
        form.add(new JLabel("Nama"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        JTextField namaField = new JTextField();
        namaField.setPreferredSize(new Dimension(250, 30));
        form.add(namaField, gbc);

        //jenis
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0; gbc.weighty = 0;
        form.add(new JLabel("Jenis"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        JTextField jenisField = new JTextField();
        jenisField.setPreferredSize(new Dimension(250, 30));
        form.add(jenisField, gbc);

        //umur
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.weightx = 0; gbc.weighty = 0;
        form.add(new JLabel("Umur"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        JTextField umurField = new JTextField();
        umurField.setPreferredSize(new Dimension(250, 30));
        form.add(umurField, gbc);

        //pemilik
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.weightx = 0; gbc.weighty = 0;
        form.add(new JLabel("Pemilik"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        JTextField pemilikField = new JTextField();
        pemilikField.setPreferredSize(new Dimension(250,30));
        form.add(pemilikField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 99;
        gbc.weighty = 1;
        form.add(Box.createVerticalGlue(), gbc);


        add(form, BorderLayout.CENTER);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            frame.getDataService().addPet(
                    namaField.getText(),
                    jenisField.getText(),
                    umurField.getText(),
                    pemilikField.getText()
            );
            frame.showPage("petList");
        });
        add(saveBtn, BorderLayout.SOUTH);
    }
}
