package smartpethealth.service.ui;

import smartpethealth.service.model.HealthRecord;
import smartpethealth.service.model.Pet;
import smartpethealth.service.service.DataService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private DataService dataService;
    private PetListPanel petListPanel;
    private AddPetPanel addPetPanel;
    private EditPetPanel editPetPanel;
    private HealthRecordListPanel healthRecordListPanel;
    private AddHealthRecordPanel addHealthRecordPanel;
    private EditHealthRecordPanel editHealthRecordPanel;
    private DashboardPanel dashboardPanel;
    private ReportsPanel reportsPanel;
    private SettingsPanel settingsPanel;
    private SearchPanel searchPanel;
    private HelpPanel helpPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {

        dataService = new DataService();

        setTitle("Smart Pet Health");
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        initializePanels();

        add(mainPanel);
        cardLayout.show(mainPanel, "dashboard");
    }

    private void initializePanels() {
        try {
            dashboardPanel = new DashboardPanel(this);
            petListPanel = new PetListPanel(this);
            addPetPanel = new AddPetPanel(this);
            editPetPanel = new EditPetPanel(this);
            healthRecordListPanel = new HealthRecordListPanel(this);
            addHealthRecordPanel = new AddHealthRecordPanel(this);
            editHealthRecordPanel = new EditHealthRecordPanel(this);
            reportsPanel = new ReportsPanel(this);
            settingsPanel = new SettingsPanel(this);
            searchPanel = new SearchPanel(this);
            helpPanel = new HelpPanel(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inisialisasi panel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        mainPanel.add(dashboardPanel, "dashboard");
        mainPanel.add(petListPanel, "petList");
        mainPanel.add(addPetPanel, "addPet");
        mainPanel.add(editPetPanel, "editPet");
        mainPanel.add(healthRecordListPanel, "healthRecordList");
        mainPanel.add(addHealthRecordPanel, "addHealthRecord");
        mainPanel.add(editHealthRecordPanel, "editHealthRecord");
        mainPanel.add(reportsPanel, "reports");
        mainPanel.add(settingsPanel, "settings");
        mainPanel.add(searchPanel, "search");
        mainPanel.add(helpPanel, "help");
    }

    public void showPage(String pageName) {
        if (pageName.equals("petList")) {
            petListPanel.refresh(this);
        } else if (pageName.equals("dashboard")) {
            dashboardPanel.refresh();
        }
        cardLayout.show(mainPanel, pageName);
    }

    public void showEditPet(int id) {
        Pet p = dataService.getPetByID(id);
        if (p != null) {
            editPetPanel.setPet(p);
            showPage("editPet");
        } else {
            JOptionPane.showMessageDialog(this, "Pet tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DataService getDataService() {
        return dataService;
    }

    public void showHealthRecords(int petId) {
        healthRecordListPanel.setPet(petId, this);
        showPage("healthRecordList");
    }

    public void showAddHealthRecord(int petId) {
        addHealthRecordPanel.setPet(petId);
        showPage("addHealthRecord");
    }

    public void showEditHealthRecord(int recordId, int petId) {
        HealthRecord r = dataService.getRecordById(recordId);
        if (r != null) {
            editHealthRecordPanel.setRecord(r);
            showPage("editHealthRecord");
        } else {
            JOptionPane.showMessageDialog(this, "Record tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}