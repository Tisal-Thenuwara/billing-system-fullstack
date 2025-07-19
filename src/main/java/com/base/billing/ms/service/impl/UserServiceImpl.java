package com.base.billing.ms.service.impl;

import com.base.billing.ms.model.dao.User;
import com.base.billing.ms.repository.UserRepository;
import com.base.billing.ms.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void updateUserStatusAndRole(String username, String status, String role) {
        userRepository.updateStatusAndRoleByUsername(status, role, username);
    }

    @Override
    @Transactional
    public void updateUserPassword(String username, String password) {
        userRepository.updatePasswordByUsername(password, username);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }
}
