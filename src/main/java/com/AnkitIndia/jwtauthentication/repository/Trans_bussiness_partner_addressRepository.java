package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_address;

public interface Trans_bussiness_partner_addressRepository extends JpaRepository<Trans_bussiness_partner_address, Long>{
	
	@Query( "select b from Trans_bussiness_partner_address b where b.bp_Id = :bp_id and b.modified_type = 'INSERTED'")
	Trans_bussiness_partner_address getTransBPAddr(@Param("bp_id") String bp_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Trans_bussiness_partner_address w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int trans_bussiness_partner_addressupdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
}
