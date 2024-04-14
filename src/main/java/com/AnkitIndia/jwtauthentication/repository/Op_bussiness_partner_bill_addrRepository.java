package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner_bill_addr;

public interface Op_bussiness_partner_bill_addrRepository extends JpaRepository<Op_bussiness_partner_bill_addr, Long>{
	
	@Query( "select o from Op_bussiness_partner_bill_addr o where o.bp_Id = :code" )
	Op_bussiness_partner_bill_addr oBPBillAddressRetriveList(@Param("code") String code);
	

}
