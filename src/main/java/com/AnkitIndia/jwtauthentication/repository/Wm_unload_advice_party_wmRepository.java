package com.AnkitIndia.jwtauthentication.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_party_wm;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;

public interface Wm_unload_advice_party_wmRepository extends JpaRepository<Wm_unload_advice_party_wm, Long>{

	@Query( "select w from Wm_unload_advice_party_wm w where w.unadviceid = :code and w.modified_type ='INSERTED'" )
	Wm_unload_advice_party_wm wmUnAdvicePartyWmRetriveList(@Param("code") String code);
	
	@Query(value="select * from wm_unload_advice_party_wm w where w.modified_type = 'INSERTED' and w.unadviceid=:code",nativeQuery=true)
	 Map<String,Object> wmUnAdvicePartyWmRetriveFastList(@Param("code") String code);
	
	@Query(value = "{call party_weighment_calculation(:unadviceid)}", nativeQuery = true)
	Wm_unload_advice_party_wm wmUnAdvicePartyWmRetriveListmultipopup(@Param("unadviceid") String unload_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_party_wm w SET w.modified_type ='UPDATED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_party_wmUpdate(@Param("unadviceid") String unadviceid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_party_wm w SET w.modified_type ='DELETED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_party_wmupdate(@Param("unadviceid") String unadviceid);
}
