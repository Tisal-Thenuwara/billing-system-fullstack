package com.base.billing.ms.repository;

import com.base.billing.ms.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
	
	void deleteByUsername(String username);

	@Transactional
	@Modifying
	@Query("update User u set u.status = ?1, u.role = ?2 where u.username = ?3")
	int updateStatusAndRoleByUsername(String status, String role, String username);

	@Transactional
	@Modifying
	@Query("update User u set u.password = ?1 where u.username = ?2")
	int updatePasswordByUsername(String password, String username);

}