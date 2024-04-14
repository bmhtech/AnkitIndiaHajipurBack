package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_app_chgs;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;

public interface Wm_unload_advice_app_chgsRepository extends JpaRepository<Wm_unload_advice_app_chgs, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_app_chgs w SET w.modified_type ='UPDATED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_app_chgsUpdate(@Param("unadviceid") String unadviceid);
	
	@Query("select w from Wm_unload_advice_app_chgs w where w.unadviceid = :code and w.modified_type = 'INSERTED'")
	List<Wm_unload_advice_app_chgs> wmUnAdviceAppChgsRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_app_chgs w SET w.modified_type ='DELETED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_app_chgsupdate(@Param("unadviceid") String unadviceid);
	
	
}
