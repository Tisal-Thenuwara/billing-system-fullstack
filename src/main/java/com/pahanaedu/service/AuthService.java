package com.pahanaedu.service;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();

    public boolean validate(String username, String password) {
        User u = userDAO.findByUsername(username);
        if (u == null) return false;
        // NOTE: For assignment simplicity, plain-text compare. Replace with hash+salt in real life.
        return u.getPassword().equals(password);
    }
}
