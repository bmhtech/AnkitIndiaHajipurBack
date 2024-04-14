package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_bill_addr;

public interface Supp_bussiness_partner_bill_addrRepository extends JpaRepository<Supp_bussiness_partner_bill_addr, Long> {
	
	@Query( "select a from Supp_bussiness_partner_bill_addr a where a.bp_Id = :bp_id and a.modified_type ='INSERTED'")
	Supp_bussiness_partner_bill_addr getSuppBPBillAddr(@Param("bp_id") String bp_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Supp_bussiness_partner_bill_addr w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int supp_bussiness_partner_bill_addrUpdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);

}
