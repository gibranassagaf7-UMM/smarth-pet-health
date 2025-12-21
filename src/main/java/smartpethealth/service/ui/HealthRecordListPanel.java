package smartpethealth.service.ui;

import smartpethealth.service.model.HealthRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HealthRecordListPanel extends JPanel {

    private int petId;
    private DefaultTableModel tableModel;
    private JTable table;

    private static final Color PASTEL_PURPLE = Color.decode("#D39CFA");

    public HealthRecordListPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(PASTEL_PURPLE);

        JLabel title = new JLabel("Riwayat Kesehatan", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Tanggal", "Berat (kg)", "Kondisi", "Catatan"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);
        table.setAutoCreateRowSorter(true);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245));
                } else {
                    c.setBackground(new Color(184, 207, 229));
                }
                setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        });

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(102, 51, 153));
        table.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255, 200));
        add(scrollPane, BorderLayout.CENTER);

        JButton addBtn = new JButton("Tambah Record");
        JButton editBtn = new JButton("Edit Record");
        JButton deleteBtn = new JButton("Hapus Record");
        JButton backBtn = new JButton("Kembali");

        JButton[] buttons = {addBtn, editBtn, deleteBtn, backBtn};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setBackground(new Color(102, 51, 153));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        addBtn.addActionListener(e -> frame.showAddHealthRecord(petId));

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih record dulu untuk edit.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int recordId = (int) tableModel.getValueAt(table.convertRowIndexToModel(row), 0);
            frame.showEditHealthRecord(recordId, petId);
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih record dulu untuk hapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus record ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm != JOptionPane.YES_OPTION) return;

            int recordId = (int) tableModel.getValueAt(table.convertRowIndexToModel(row), 0);
            frame.getDataService().deleteHealthRecord(recordId);
            refresh(frame);
            JOptionPane.showMessageDialog(this, "Record berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        });

        backBtn.addActionListener(e -> frame.showPage("petList"));

        JPanel bottom = new JPanel();
        bottom.setBackground(PASTEL_PURPLE);
        bottom.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        bottom.add(addBtn);
        bottom.add(editBtn);
        bottom.add(deleteBtn);
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }

    public void setPet(int petId, MainFrame frame) {
        this.petId = petId;
        refresh(frame);
    }

    public void refresh(MainFrame frame) {
        tableModel.setRowCount(0);
        List<HealthRecord> list = frame.getDataService().getRecordsByPet(petId);

        for (HealthRecord r : list) {
            tableModel.addRow(new Object[]{
                    r.getId(),
                    r.getTanggal().toString(),
                    r.getBerat(),
                    r.getKondisi(),
                    r.getCatatan()
            });
        }
    }
}