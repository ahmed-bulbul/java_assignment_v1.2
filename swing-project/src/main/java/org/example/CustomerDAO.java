package org.example;

import java.sql.*;
import java.util.*;

public class CustomerDAO {
    public List<Customer> getAllCustomers() throws Exception {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Customer(
                        rs.getInt("customerId"),
                        rs.getString("shortName"),
                        rs.getString("fullName"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getString("address3"),
                        rs.getString("city"),
                        rs.getString("postalCode")
                ));
            }
        }
        return list;
    }

    public void insert(Customer c) throws Exception {
        String sql = "INSERT INTO Customer (shortName, fullName, address1, address2, address3, city, postalCode) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getShortName());
            ps.setString(2, c.getFullName());
            ps.setString(3, c.getAddress1());
            ps.setString(4, c.getAddress2());
            ps.setString(5, c.getAddress3());
            ps.setString(6, c.getCity());
            ps.setString(7, c.getPostalCode());
            ps.executeUpdate();
        }
    }

    public void update(Customer c) throws Exception {
        String sql = "UPDATE Customer SET shortName=?, fullName=?, address1=?, address2=?, address3=?, city=?, postalCode=? WHERE customerId=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getShortName());
            ps.setString(2, c.getFullName());
            ps.setString(3, c.getAddress1());
            ps.setString(4, c.getAddress2());
            ps.setString(5, c.getAddress3());
            ps.setString(6, c.getCity());
            ps.setString(7, c.getPostalCode());
            ps.setInt(8, c.getCustomerId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Customer WHERE customerId=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
