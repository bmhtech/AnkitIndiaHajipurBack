package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_accont;

public interface Trans_bussiness_partner_accontRepository extends JpaRepository<Trans_bussiness_partner_accont, Long>{

	
	@Query("SELECT b FROM Trans_bussiness_partner_accont b where b.bp_Id= :bp_Id and b.modified_type = 'INSERTED'")
	Trans_bussiness_partner_accont getTransAccount(@Param("bp_Id") String bp_Id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Trans_bussiness_partner_accont w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int trans_bussiness_partner_accontupdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
}
