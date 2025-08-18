package com.pahanaedu.dao;

import com.pahanaedu.model.Item;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public boolean add(Item it) {
        String sql = "INSERT INTO items(item_name, price_per_unit) VALUES(?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, it.getItemName());
            ps.setBigDecimal(2, it.getPricePerUnit());
            int ok = ps.executeUpdate();
            if (ok == 1) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) it.setItemId(keys.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Item it) {
        String sql = "UPDATE items SET item_name=?, price_per_unit=? WHERE item_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, it.getItemName());
            ps.setBigDecimal(2, it.getPricePerUnit());
            ps.setInt(3, it.getItemId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM items WHERE item_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Item get(int id) {
        String sql = "SELECT item_id,item_name,price_per_unit FROM items WHERE item_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Item(rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getBigDecimal("price_per_unit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> listAll() {
        String sql = "SELECT item_id,item_name,price_per_unit FROM items ORDER BY item_id";
        List<Item> out = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out.add(new Item(rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getBigDecimal("price_per_unit")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }
}
