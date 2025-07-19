package com.base.billing.ms.repository;

import com.base.billing.ms.model.dao.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	Customer findByAccountNumber(String accountNumber);

	void deleteByAccountNumber(String accountNumber);


	@Transactional
	@Modifying
	@Query("update Customer c set c.unitsConsumed = ?1 where c.accountNumber = ?2")
	int updateUnitsConsumedByAccountNumber(int unitsConsumed, String accountNumber);

	@Transactional
	@Modifying
	@Query("""
			update Customer c set c.firstName = ?1, c.lastName = ?2, c.phone = ?3, c.email = ?4
			where c.accountNumber = ?5""")
	int updateFirstNameAndLastNameAndPhoneAndEmailByAccountNumber(String firstName, String lastName, String phone, String email, String accountNumber);

	@Transactional
	@Modifying
	@Query("""
			update Customer c set c.addressLine1 = ?1, c.addressLine2 = ?2, c.addressLine3 = ?3, c.city = ?4, c.postalCode = ?5
			where c.accountNumber = ?6""")
	int updateAddressLine1AndAddressLine2AndAddressLine3AndCityAndPostalCodeByAccountNumber(String addressLine1, String addressLine2, String addressLine3, String city, String postalCode, String accountNumber);
}