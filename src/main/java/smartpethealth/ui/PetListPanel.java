package smartpethealth.ui;

import javax.swing.*;
import java.awt.*;

public class PetListPanel extends JPanel{

    public PetListPanel(MainFrame frame){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Daftar Hewan", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        add(title, BorderLayout.NORTH);

        JTable table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> frame.showPage("dashboard"));

        add(backBtn, BorderLayout.SOUTH);

    }

}
