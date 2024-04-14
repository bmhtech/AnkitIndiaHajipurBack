package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_terms_con;


public interface Wm_unload_advice_terms_conRepository extends JpaRepository<Wm_unload_advice_terms_con, Long>{

	@Query( "select w from Wm_unload_advice_terms_con w where w.unadviceid = :code and w.modified_type ='INSERTED'" )
	Wm_unload_advice_terms_con wmUnAdviceTransConRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_terms_con w SET w.modified_type ='UPDATED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_terms_conUpdate(@Param("unadviceid") String unadviceid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_terms_con w SET w.modified_type ='DELETED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_terms_conupdate(@Param("unadviceid") String unadviceid);
}
