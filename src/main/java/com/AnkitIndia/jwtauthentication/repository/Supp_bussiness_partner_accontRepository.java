package com.AnkitIndia.jwtauthentication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_accont;

public interface Supp_bussiness_partner_accontRepository extends JpaRepository<Supp_bussiness_partner_accont, Long> {

	@Query("SELECT b FROM Supp_bussiness_partner_accont b where b.bp_Id =:bp_id and b.modified_type ='INSERTED'")
	Supp_bussiness_partner_accont getSuppBPAcc(@Param("bp_id") String bp_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Supp_bussiness_partner_accont w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int supp_bussiness_partner_accontUpdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);	
}
