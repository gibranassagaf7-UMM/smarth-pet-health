package smartpethealth.ui;

import smartpethealth.service.DataService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private DataService dataService;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame(){

        dataService = new DataService();

        setTitle("Smart Pet Health");
        setSize(450,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI(){
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new DashboardPanel(this), "dashboard");
        mainPanel.add(new PetListPanel(this), "petList");

        add(mainPanel);
        cardLayout.show(mainPanel, "dashboard");
    }

    public void showPage(String pageName){
        cardLayout.show(mainPanel,pageName);
    }

    public DataService getDataService() {
        return dataService;
    }

}
