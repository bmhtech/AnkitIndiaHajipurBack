package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Party_bill_payment;

public interface Party_bill_paymentRepository extends JpaRepository<Party_bill_payment, Long>{
	
	@Query("select max(substring(pbill_id ,4, length(pbill_id))) from Party_bill_payment where pbill_id like %:code% ")
	String getBillPaySequenceId(@Param("code") String code);

	@Query("select max(substring(pbill_no , 14, length(pbill_no))) from Party_bill_payment where pbill_no like %:code%")
	String getBillPaySequenceNo(@Param("code") String code);
	
}
