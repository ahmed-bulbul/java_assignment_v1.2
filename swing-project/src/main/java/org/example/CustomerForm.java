package org.example;

import javax.swing.*;
import java.awt.*;

class CustomerForm extends JDialog {
    private JTextField txtShort, txtFull, txtCity, txtPostal;
    private JTextField txtAddress1, txtAddress2, txtAddress3;
    private JButton btnSave, btnCancel;
    private Customer customer;
    private CustomerDAO dao = new CustomerDAO();

    public CustomerForm(JFrame parent, Customer customer) {
        super(parent, "Customer Form", true);
        this.customer = customer;

        setLayout(new GridLayout(8, 2, 5, 5));
        setSize(400, 300);

        add(new JLabel("Short Name:"));
        txtShort = new JTextField();
        add(txtShort);

        add(new JLabel("Full Name:"));
        txtFull = new JTextField();
        add(txtFull);

        add(new JLabel("Address 1:"));
        txtAddress1 = new JTextField();
        add(txtAddress1);

        add(new JLabel("Address 2:"));
        txtAddress2 = new JTextField();
        add(txtAddress2);

        add(new JLabel("Address 3:"));
        txtAddress3 = new JTextField();
        add(txtAddress3);

        add(new JLabel("City:"));
        txtCity = new JTextField();
        add(txtCity);

        add(new JLabel("Postal Code:"));
        txtPostal = new JTextField();
        add(txtPostal);

        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        add(btnSave);
        add(btnCancel);

        if (customer != null) {
            txtShort.setText(customer.getShortName());
            txtFull.setText(customer.getFullName());
            txtCity.setText(customer.getCity());
            txtPostal.setText(customer.getPostalCode());
        }

        btnSave.addActionListener(e -> saveCustomer());
        btnCancel.addActionListener(e -> dispose());
    }

    private void saveCustomer() {
        String postalCode = txtPostal.getText().trim();
        if (!postalCode.matches("\\d{4,6}")) {
            JOptionPane.showMessageDialog(this, "Invalid Postal Code");
            return;
        }
        try {
            if (customer == null) {
                dao.insert(new Customer(0, txtShort.getText(), txtFull.getText(),
                        txtAddress1.getText(), txtAddress2.getText(), txtAddress3.getText(),
                        txtCity.getText(), postalCode));
            } else {
                dao.update(new Customer(customer.getCustomerId(), txtShort.getText(), txtFull.getText(),
                        txtAddress1.getText(), txtAddress2.getText(), txtAddress3.getText(),
                        txtCity.getText(), postalCode));
            }
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving: " + ex.getMessage());
        }
    }
}
