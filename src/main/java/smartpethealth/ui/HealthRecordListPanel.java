package smartpethealth.ui;

import smartpethealth.model.HealthRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HealthRecordListPanel extends JPanel {

    private int petId;
    private DefaultTableModel tableModel;
    private JTable table;

    public HealthRecordListPanel(MainFrame frame){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Riwayat Kesehatan", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Tanggal", "Berat", "Kondisi", "Catatan"
        }, 0);

        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton addBtn = new JButton("Tambah");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");
        JButton backBtn = new JButton("Back");

        addBtn.addActionListener(e ->
                frame.showAddHealthRecord(petId)
        );

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1){
                JOptionPane.showMessageDialog(this, "Pilih data dulu");
                return;
            }
            int recordId = (int) tableModel.getValueAt(row, 0);
            frame.showEditHealthRecord(recordId, petId);
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;

            int recordId = (int) tableModel.getValueAt(row, 0);
            frame.getDataService().deleteHealthRecord(recordId);
            frame.showHealthRecords(petId);
        });

        backBtn.addActionListener(e -> frame.showPage("petList"));

        JPanel bottom = new JPanel();
        bottom.add(addBtn);
        bottom.add(editBtn);
        bottom.add(deleteBtn);
        bottom.add(backBtn);

        add(bottom, BorderLayout.SOUTH);
    }

    public void setPet(int petId, MainFrame frame){
        this.petId = petId;
        refresh(frame);
    }

    public void refresh(MainFrame frame){
        tableModel.setRowCount(0);
        List<HealthRecord> list = frame.getDataService().getRecordsByPet(petId);

        for (HealthRecord r : list){
            tableModel.addRow(new Object[]{
                    r.getId(),
                    r.getTanggal(),
                    r.getBerat(),
                    r.getKondisi(),
                    r.getCatatan()
            });
        }
    }
}
