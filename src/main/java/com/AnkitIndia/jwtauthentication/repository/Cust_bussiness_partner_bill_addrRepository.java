package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_bill_addr;

public interface Cust_bussiness_partner_bill_addrRepository extends JpaRepository<Cust_bussiness_partner_bill_addr, Long>{


	@Query( "select c from Cust_bussiness_partner_bill_addr c where c.cp_Id = :code and c.modified_type = 'INSERTED'" )
	Cust_bussiness_partner_bill_addr custBillAddRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Cust_bussiness_partner_bill_addr w SET w.modified_type =:mstatus WHERE w.cp_Id = :cp_Id")
    int cust_bussiness_partner_bill_addrupdate(@Param("cp_Id") String cp_Id,@Param("mstatus") String mstatus);
}
