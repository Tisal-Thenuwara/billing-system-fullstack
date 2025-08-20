package com.pahanaedu.dao;

import com.pahanaedu.model.Customer;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class CustomerDAO {
    private static final Logger logger = Logger.getLogger(CustomerDAO.class.getName());

    public boolean add(Customer c) {
        logger.info("Adding customer: " + c.toString());

        String sql = "INSERT INTO customers(name,address1, address2, phone, email, units_consumed) VALUES(?,?,?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getAddress1());
            ps.setString(3, c.getAddress2());
            ps.setString(4, c.getPhone());
            ps.setString(5, c.getEmail());
	        if (!Objects.isNull(c.getUnitsConsumed())) ps.setInt(6, c.getUnitsConsumed());
	        else ps.setInt(6, 0);
	        return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer get(int accountNo) {
        String sql = "SELECT account_no,name,address1, address2, phone, email, units_consumed FROM customers WHERE account_no=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("account_no"),
                        rs.getString("name"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getInt("units_consumed")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Customer c) {
        String sql = "UPDATE customers SET name=?, address1=?, address2=?, phone=?, email=?, units_consumed=? WHERE account_no=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getAddress1());
            ps.setString(3, c.getAddress2());
            ps.setString(4, c.getPhone());
            ps.setString(5, c.getEmail());
            ps.setInt(6, c.getUnitsConsumed());
            ps.setInt(7, c.getAccountNo());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Customer> listAll() {
        String sql = "SELECT account_no,name,address1,address2,phone,email,units_consumed FROM customers ORDER BY account_no";
        List<Customer> out = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out.add(new Customer(
                        rs.getInt("account_no"),
                        rs.getString("name"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getInt("units_consumed")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public boolean delete(int accountNo) {
        String sql = "DELETE FROM customers WHERE account_no = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountNo); // Set the accountNo in the query
            return ps.executeUpdate() == 1; // Return true if one row is deleted
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an exception occurs
    }
}