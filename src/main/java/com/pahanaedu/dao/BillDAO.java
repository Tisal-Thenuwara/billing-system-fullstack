package com.pahanaedu.dao;

import com.pahanaedu.model.Bill;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

	public boolean createBill(Bill bill) {
		String sql = "INSERT INTO bills(account_no, item_id, units, total, price_per_unit) VALUES(?,?,?,?,?)";
		try (Connection con = DBUtil.getConnection();
		     PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, bill.getAccountNo());
			ps.setInt(2, bill.getItemId());
			ps.setInt(3, bill.getUnits());
			ps.setBigDecimal(4, bill.getTotal());
			ps.setBigDecimal(5, bill.getPricePerUnit());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected == 1) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					bill.setBillId(rs.getInt(1)); // Set the generated bill ID
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteBill(int billId) {
		String sql = "DELETE FROM bills WHERE bill_id=?";
		try (Connection con = DBUtil.getConnection();
		     PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, billId);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Bill viewBillById(int billId) {
		String sql = "SELECT bill_id, account_no, item_id, units, total, price_per_unit FROM bills WHERE bill_id=?";
		try (Connection con = DBUtil.getConnection();
		     PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, billId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Bill(
						rs.getInt("bill_id"),
						rs.getInt("account_no"),
						rs.getInt("item_id"),
						rs.getInt("units"),
						rs.getBigDecimal("total"),
						rs.getBigDecimal("price_per_unit")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Bill> viewAllBills() {
		String sql = "SELECT bill_id, account_no, item_id, units, total, price_per_unit FROM bills";
		List<Bill> bills = new ArrayList<>();
		try (Connection con = DBUtil.getConnection();
		     PreparedStatement ps = con.prepareStatement(sql);
		     ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				bills.add(new Bill(
						rs.getInt("bill_id"),
						rs.getInt("account_no"),
						rs.getInt("item_id"),
						rs.getInt("units"),
						rs.getBigDecimal("total"),
						rs.getBigDecimal("price_per_unit")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bills;
	}
}
