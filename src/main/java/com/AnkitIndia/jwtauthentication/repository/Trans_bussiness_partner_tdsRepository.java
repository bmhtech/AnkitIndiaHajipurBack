package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_tds;

public interface Trans_bussiness_partner_tdsRepository  extends JpaRepository<Trans_bussiness_partner_tds, Long>{

	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Trans_bussiness_partner_tds w SET w.modified_type =:mstatus WHERE w.bp_Id = :bp_id")
    int updatetds(@Param("bp_id") String bp_id,@Param("mstatus") String mstatus);
	
	@Query("select t from  Trans_bussiness_partner_tds t where t.modified_type='INSERTED' AND t.bp_Id = :bp_id")
	Trans_bussiness_partner_tds getTranstds(@Param("bp_id") String bp_id);
}
