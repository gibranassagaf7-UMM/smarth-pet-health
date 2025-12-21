package smartpethealth.service.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardPanel extends JPanel {

    public static final String[] TIPS = new String[]{
            "Tips: Berikan vaksin rutin untuk kesehatan pet.",
            "Tips: Jaga kebersihan kandang pet.",
            "Tips: Berikan makanan seimbang dan air bersih.",
            "Tips: Lakukan olahraga harian dengan pet."
    };
    private MainFrame frame;
    private JLabel totalPetsLabel;
    private JLabel tipsLabel;

    private static final Color PASTEL_PURPLE = Color.decode("#D39CFA");
    private static final Color DARK_PURPLE = new Color(102, 51, 153);

    public DashboardPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(PASTEL_PURPLE);

        JLabel title = new JLabel("Smart Pet Health Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(PASTEL_PURPLE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        totalPetsLabel = new JLabel("Total Hewan Peliharaan: Memuat...", SwingConstants.CENTER);
        totalPetsLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        totalPetsLabel.setForeground(Color.WHITE);
        totalPetsLabel.setOpaque(true);
        totalPetsLabel.setBackground(new Color(255, 255, 255, 180));
        totalPetsLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        totalPetsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(totalPetsLabel);
        centerPanel.add(Box.createVerticalStrut(20));

        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setBackground(PASTEL_PURPLE);
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        buttonPanel.setBackground(PASTEL_PURPLE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton petBtn = new JButton("Daftar Hewan");
        JButton reportsBtn = new JButton("Laporan");
        JButton settingsBtn = new JButton("Pengaturan");
        JButton searchBtn = new JButton("Pencarian");

        JButton[] buttons = {petBtn, reportsBtn, settingsBtn, searchBtn};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
            btn.setBackground(DARK_PURPLE);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createRaisedBevelBorder(),
                    BorderFactory.createEmptyBorder(15, 20, 15, 20)
            ));
            btn.setPreferredSize(new Dimension(180, 60));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        petBtn.addActionListener(e -> frame.showPage("petList"));
        reportsBtn.addActionListener(e -> frame.showPage("reports"));
        settingsBtn.addActionListener(e -> frame.showPage("settings"));
        searchBtn.addActionListener(e -> frame.showPage("search"));

        buttonPanel.add(petBtn);
        buttonPanel.add(reportsBtn);
        buttonPanel.add(settingsBtn);
        buttonPanel.add(searchBtn);

        southPanel.add(buttonPanel);
        southPanel.add(Box.createVerticalStrut(15));

        tipsLabel = new JLabel("Tips: Jaga kesehatan pet Anda dengan check-up rutin!", SwingConstants.CENTER);
        tipsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        tipsLabel.setForeground(Color.WHITE);
        tipsLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        tipsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        southPanel.add(tipsLabel);

        add(southPanel, BorderLayout.SOUTH);

        refresh();
    }

    public void refresh() {
        List<?> pets = frame.getDataService().getAllPets();
        totalPetsLabel.setText("Total Hewan Peliharaan: " + pets.size());

        tipsLabel.setText(TIPS[(int) (Math.random() * TIPS.length)]);
    }
}