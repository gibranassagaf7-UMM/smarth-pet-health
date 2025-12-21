package smartpethealth.ui;

import smartpethealth.model.Pet;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PetListPanel extends JPanel{

    private DefaultTableModel tableModel;
    private JTable table;

    public PetListPanel(MainFrame frame){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Daftar Hewan", SwingConstants.CENTER);
        title.setFont(new Font("Segeo UI", Font.BOLD, 22));

        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Nama", "Jenis", "Umur", "Pemilik",
        },0);

        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JButton backBtn = new JButton("Back");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        backBtn.addActionListener(e -> frame.showPage("dashboard"));

        editBtn.addActionListener(e-> {
            int row = table.getSelectedRow();
            if (row == -1){
                JOptionPane.showMessageDialog(this, "Pilih data dulu");
                return;
            }
            int id = (int) tableModel.getValueAt(row, 0);
            frame.showEditPet(id);
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1){
                JOptionPane.showMessageDialog(this, "Pilih data dulu");
                return;
            }
            int id = (int) tableModel.getValueAt(row, 0);

            int confirm = JOptionPane.showConfirmDialog(this, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

            if(confirm == JOptionPane.YES_OPTION){
                frame.getDataService().deletePet(id);
                refresh(frame);
            }
        });

        JButton recordBtn = new JButton("Health Record");

        recordBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;
            int id = (int) tableModel.getValueAt(row, 0);
            frame.showHealthRecords(id);
        });


        JPanel action = new JPanel();
        action.add(editBtn);
        action.add(deleteBtn);
        action.add(backBtn);
        action.add(recordBtn);
        add(action, BorderLayout.SOUTH);

        loadData(frame);
    }



    public void loadData(MainFrame frame){
        tableModel.setRowCount(0);

        List<Pet> pets = frame.getDataService().getAllPets();
        for(Pet p : pets){
            tableModel.addRow(new Object[]{
                    p.getId(),
                    p.getNama(),
                    p.getJenis(),
                    p.getUmur(),
                    p.getPemilik()
            });
        }
    }

    public void refresh(MainFrame frame){
        loadData(frame);
    }



}
