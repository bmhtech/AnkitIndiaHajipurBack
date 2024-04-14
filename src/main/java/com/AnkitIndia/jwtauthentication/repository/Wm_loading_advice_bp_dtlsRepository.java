package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_bp_dtls;

public interface Wm_loading_advice_bp_dtlsRepository extends JpaRepository<Wm_loading_advice_bp_dtls, Long>{

	@Query( "select w from Wm_loading_advice_bp_dtls w where w.advice_id = :code and  w.modified_type = 'INSERTED'" )
	Wm_loading_advice_bp_dtls loadingBPDtlsRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice_bp_dtls w SET w.modified_type =:mstatus WHERE w.advice_id = :advice_id")
    int wm_loading_advice_bp_dtlsupdate(@Param("advice_id") String advice_id,@Param("mstatus") String mstatus);
}
