package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_bp_dtls;


public interface Wm_unload_advice_bp_dtlsRepository extends JpaRepository<Wm_unload_advice_bp_dtls, Long>{

	@Query( "select w from Wm_unload_advice_bp_dtls w where w.unadviceid = :code and w.modified_type ='INSERTED'" )
	Wm_unload_advice_bp_dtls wmUnAdviceBpDtlsRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_bp_dtls w SET w.modified_type ='UPDATED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_bp_dtlsUpdate(@Param("unadviceid") String unadviceid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_bp_dtls w SET w.modified_type ='DELETED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_bp_dtlsupdate(@Param("unadviceid") String unadviceid);
}
