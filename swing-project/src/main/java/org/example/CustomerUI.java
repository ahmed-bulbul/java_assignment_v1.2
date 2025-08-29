package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private CustomerDAO dao = new CustomerDAO();

    public CustomerUI() {
        setTitle("Customer Management");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel(new String[]{"ID", "Short Name", "Full Name", "City", "Postal Code"}, 0);
        table = new JTable(model);

        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Modify");
        JButton btnDelete = new JButton("Delete");
        JButton btnRefresh = new JButton("Refresh");

        JPanel panel = new JPanel();
        panel.add(btnAdd);
        panel.add(btnEdit);
        panel.add(btnDelete);
        panel.add(btnRefresh);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        btnRefresh.addActionListener(e -> loadCustomers());

        btnAdd.addActionListener(e -> {
            CustomerForm form = new CustomerForm(this, null);
            form.setVisible(true);
            loadCustomers();
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) model.getValueAt(row, 0);
                String shortName = (String) model.getValueAt(row, 1);
                String fullName = (String) model.getValueAt(row, 2);
                String city = (String) model.getValueAt(row, 3);
                String postalCode = (String) model.getValueAt(row, 4);

                Customer c = new Customer(id, shortName, fullName, "", "", "", city, postalCode);
                CustomerForm form = new CustomerForm(this, c);
                form.setVisible(true);
                loadCustomers();
            }
        });

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) model.getValueAt(row, 0);
                try {
                    dao.delete(id);
                    loadCustomers();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting: " + ex.getMessage());
                }
            }
        });

        loadCustomers();
    }

    public void loadCustomers() {
        try {
            model.setRowCount(0);
            List<Customer> list = dao.getAllCustomers();
            for (Customer c : list) {
                model.addRow(new Object[]{c.getCustomerId(), c.getShortName(), c.getFullName(), c.getCity(), c.getPostalCode()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading customers: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerUI().setVisible(true));
    }
}
