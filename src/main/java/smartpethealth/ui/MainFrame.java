package smartpethealth.ui;

import smartpethealth.model.HealthRecord;
import smartpethealth.model.Pet;
import smartpethealth.service.DataService;

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

        petListPanel = new PetListPanel(this);
        addPetPanel = new AddPetPanel(this);
        editPetPanel = new EditPetPanel(this);
        healthRecordListPanel = new HealthRecordListPanel(this);
        addHealthRecordPanel = new AddHealthRecordPanel(this);
        editHealthRecordPanel = new EditHealthRecordPanel(this);

        mainPanel.add(new DashboardPanel(this), "dashboard");
        mainPanel.add(petListPanel, "petList");
        mainPanel.add(addPetPanel, "addPet");
        mainPanel.add(editPetPanel, "editPet");
        mainPanel.add(healthRecordListPanel, "healthRecordList");
        mainPanel.add(addHealthRecordPanel, "addHealthRecord");
        mainPanel.add(editHealthRecordPanel, "editHealthRecord");

        add(mainPanel);
        cardLayout.show(mainPanel, "dashboard");
    }

    public void showPage(String pageName){
        if (pageName.equals("petList")){
            petListPanel.refresh(this);
        }
        cardLayout.show(mainPanel,pageName);
    }

    public void showEditPet(int id){
        Pet p = dataService.getPetByID(id);
        if (p != null){
            editPetPanel.setPet(p);
            showPage("editPet");
        }
    }

    public DataService getDataService() {
        return dataService;
    }

    public void showHealthRecords(int petId){
        healthRecordListPanel.setPet(petId, this);
        showPage("healthRecordList");
    }

    public void showAddHealthRecord(int petId){
        addHealthRecordPanel.setPet(petId);
        showPage("addHealthRecord");
    }

    public void showEditHealthRecord(int recordId, int petId){
        HealthRecord r = dataService.getRecordById(recordId);
        if (r != null){
            editHealthRecordPanel.setRecord(r);
            showPage("editHealthRecord");
        }
    }
}
