/*package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Invoice;

public interface Stk_Transfer_InvoiceRepository extends JpaRepository<Stk_Transfer_Invoice, Long>{
	
	@Query("select COUNT(id) from Stk_Transfer_Invoice")
	String countId();

}
*/