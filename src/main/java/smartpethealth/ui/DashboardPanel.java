package smartpethealth.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel{

    public DashboardPanel(MainFrame frame){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("dashboard - Smart Pet Health", SwingConstants.CENTER);
        title.setFont(new Font("Segeo UI", Font.BOLD,22));

        add(title, BorderLayout.CENTER);

        JButton petBtn = new JButton("Lihat Daftar Hewan");
        petBtn.addActionListener(e -> frame.showPage("petList"));

        JButton addBtn = new JButton("Tambah Pet");
        addBtn.addActionListener(e -> frame.showPage("addPet"));

        JPanel bottom = new JPanel();
        bottom.add(petBtn);
        bottom.add(addBtn);
        add(bottom, BorderLayout.SOUTH);

    }

}
