package dao;

import model.Customer;


import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;

    public class CustomerDAO {
        public void addCustomer(Customer customer) {
            try {
                Connection con = DBConnection.getConnection();
                String sql = "INSERT INTO customer (name, contact) VALUES (?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, customer.getName());
                pst.setString(2, customer.getContact());

                pst.executeUpdate();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

