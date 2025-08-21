package com.pahanaedu.dao;

import com.pahanaedu.model.User;
import com.pahanaedu.util.DBUtil;

import java.sql.*;

public class UserDAO {

    public User findByUsername(String username) {
        String sql = "SELECT id, username, password, role FROM users WHERE username=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
