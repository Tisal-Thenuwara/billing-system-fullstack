package com.base.billing.ms.service;

import com.base.billing.ms.model.dao.User;

public interface UserService {
    User getUserByUsernameAndPassword(String username, String password);

    User getUserByUsername(String username);

    void updateUserStatusAndRole(String username, String status, String role);

    void updateUserPassword(String username, String password);

    void deleteUser(String username);
}
