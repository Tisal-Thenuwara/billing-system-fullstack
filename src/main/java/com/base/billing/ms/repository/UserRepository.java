package com.base.billing.ms.repository;

import com.base.billing.ms.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}