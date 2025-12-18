package smartpethealth.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel{

    public DashboardPanel(MainFrame frame){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("dashboard - Smart Pet Health", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD,24));

        add(title, BorderLayout.CENTER);
    }
}
