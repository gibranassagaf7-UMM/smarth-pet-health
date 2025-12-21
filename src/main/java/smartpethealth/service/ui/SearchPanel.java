package smartpethealth.service.ui;

import smartpethealth.service.model.Pet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SearchPanel extends JPanel {

    private static final Color PASTEL_PURPLE = Color.decode("#D39CFA");
    private List<Pet> searchResults = new ArrayList<>();  // Untuk menyimpan hasil pencarian

    public SearchPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(PASTEL_PURPLE);

        JLabel title = new JLabel("Pencarian", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(new Color(255, 255, 255, 200));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchPanel.setOpaque(true);

        JTextField searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchField.setToolTipText("Cari berdasarkan nama pet atau jenis");
        searchField.setOpaque(true);
        searchPanel.add(new JLabel("Cari:"));
        searchPanel.add(searchField);

        JButton searchBtn = new JButton("Cari");
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        searchBtn.setBackground(new Color(102, 51, 153));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);
        searchBtn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBtn.setOpaque(true);
        searchPanel.add(searchBtn);

        add(searchPanel, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> resultList = new JList<>(listModel);
        resultList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultList.setBackground(new Color(255, 255, 255, 200));
        resultList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resultList.setOpaque(true);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(resultList);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setOpaque(true);
        add(scrollPane, BorderLayout.CENTER);

        resultList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    int index = resultList.getSelectedIndex();
                    if (index >= 0 && index < searchResults.size()) {
                        Pet selectedPet = searchResults.get(index);
                        frame.showHealthRecords(selectedPet.getId());
                    }
                }
            }
        });

        searchBtn.addActionListener(e -> {
            String query = searchField.getText().trim().toLowerCase();
            listModel.clear();
            searchResults.clear();

            List<Pet> pets = frame.getDataService().getAllPets();
            int no = 1;  // Nomor urut untuk hasil pencarian
            for (Pet p : pets) {
                if (p.getNama().toLowerCase().contains(query) || p.getJenis().toLowerCase().contains(query)) {
                    searchResults.add(p);  // Simpan Pet ke list
                    listModel.addElement(no + ": " + p.getNama() + " (" + p.getJenis() + ")");
                    no++;
                }
            }
            if (searchResults.isEmpty()) {
                listModel.addElement("Tidak ada hasil ditemukan.");
            }

            SwingUtilities.invokeLater(() -> {
                this.revalidate();
                this.repaint();
                resultList.revalidate();
                resultList.repaint();
                scrollPane.revalidate();
                scrollPane.repaint();
            });
            resultList.requestFocusInWindow();
        });

        JButton backBtn = new JButton("Kembali");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backBtn.setBackground(new Color(102, 51, 153));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> frame.showPage("dashboard"));

        JPanel bottom = new JPanel();
        bottom.setBackground(PASTEL_PURPLE);
        bottom.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);
    }
}