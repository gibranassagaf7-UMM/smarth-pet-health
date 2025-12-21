package smartpethealth.service.ui;

import smartpethealth.service.model.Pet;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PetListPanel extends JPanel {

    private DefaultTableModel tableModel;
    private JTable table;

    private static final Color PASTEL_PURPLE = Color.decode("#D39CFA");

    public PetListPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(PASTEL_PURPLE);

        JLabel title = new JLabel("Daftar Hewan Peliharaan", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{
                "ID", "Nama", "Jenis", "Umur", "Pemilik"
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
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255, 200));
        add(scrollPane, BorderLayout.CENTER);

        JButton addBtn = new JButton("Tambah Hewan");
        JButton editBtn = new JButton("Edit Hewan");
        JButton deleteBtn = new JButton("Hapus Hewan");
        JButton recordBtn = new JButton("Lihat Riwayat");
        JButton backBtn = new JButton("Kembali");

        JButton[] buttons = {addBtn, editBtn, deleteBtn, recordBtn, backBtn};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setBackground(new Color(102, 51, 153));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        addBtn.addActionListener(e -> frame.showPage("addPet"));

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih hewan dulu untuk edit.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = (int) tableModel.getValueAt(table.convertRowIndexToModel(row), 0);
            frame.showEditPet(id);
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih hewan dulu untuk hapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = (int) tableModel.getValueAt(table.convertRowIndexToModel(row), 0);

            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus hewan ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm != JOptionPane.YES_OPTION) return;

            frame.getDataService().deletePet(id);
            refresh(frame);
            JOptionPane.showMessageDialog(this, "Hewan berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        });

        recordBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih hewan dulu untuk lihat riwayat.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = (int) tableModel.getValueAt(table.convertRowIndexToModel(row), 0);
            frame.showHealthRecords(id);
        });

        backBtn.addActionListener(e -> frame.showPage("dashboard"));

        JPanel action = new JPanel();
        action.setBackground(PASTEL_PURPLE);
        action.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        action.add(addBtn);
        action.add(editBtn);
        action.add(deleteBtn);
        action.add(recordBtn);
        action.add(backBtn);
        add(action, BorderLayout.SOUTH);

        loadData(frame);
    }

    public void loadData(MainFrame frame) {
        tableModel.setRowCount(0);

        List<Pet> pets = frame.getDataService().getAllPets();
        for (Pet p : pets) {
            tableModel.addRow(new Object[]{
                    p.getId(),
                    p.getNama(),
                    p.getJenis(),
                    p.getUmur(),
                    p.getPemilik()
            });
        }
    }

    public void refresh(MainFrame frame) {
        loadData(frame);
    }
}