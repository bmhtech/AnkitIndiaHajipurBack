package com.AnkitIndia.jwtauthentication.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_trans_info;


public interface Wm_unload_advice_trans_infoRepository extends JpaRepository<Wm_unload_advice_trans_info, Long>{

	@Query( "select w from Wm_unload_advice_trans_info w where w.unadviceid = :code and w.modified_type ='INSERTED'" )
	Wm_unload_advice_trans_info wmUnAdviceTransInfoRetriveList(@Param("code") String code);
	
	@Query(value="select * from wm_unload_advice_trans_info w where w.modified_type = 'INSERTED' and w.unadviceid=:code",nativeQuery=true)
	 Map<String,Object> wmUnAdviceTransInfoRetriveFastList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_trans_info w SET w.modified_type ='UPDATED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_trans_infoUpdate(@Param("unadviceid") String unadviceid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_trans_info w SET w.modified_type ='DELETED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_trans_infoupdate(@Param("unadviceid") String unadviceid);
}
