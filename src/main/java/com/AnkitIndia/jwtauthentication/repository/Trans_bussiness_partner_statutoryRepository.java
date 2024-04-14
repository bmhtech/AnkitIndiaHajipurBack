package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_statutory;

public interface Trans_bussiness_partner_statutoryRepository extends JpaRepository<Trans_bussiness_partner_statutory, Long>{
	
	@Query( "select b from Trans_bussiness_partner_statutory b where b.bp_Id = :bp_id and b.modified_type = 'INSERTED'")
	Trans_bussiness_partner_statutory getTransBPStatu(@Param("bp_id") String bp_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Trans_bussiness_partner_statutory w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int trans_bussiness_partner_statutoryupdate(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);


}
