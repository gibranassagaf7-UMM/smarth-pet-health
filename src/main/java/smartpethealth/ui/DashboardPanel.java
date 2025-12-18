package smartpethealth.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel{

    public DashboardPanel(MainFrame frame){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("dashboard - Smart Pet Health", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD,22));

        add(title, BorderLayout.CENTER);

        JButton petBtn = new JButton("Lihat Daftar Hewan");
        petBtn.addActionListener(e -> frame.showPage("petList"));

        add(petBtn, BorderLayout.SOUTH);
    }

}
