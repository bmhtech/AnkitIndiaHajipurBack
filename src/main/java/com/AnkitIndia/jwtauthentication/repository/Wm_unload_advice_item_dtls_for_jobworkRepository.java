package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_item_dtls_for_jobwork;

public interface Wm_unload_advice_item_dtls_for_jobworkRepository extends JpaRepository<Wm_unload_advice_item_dtls_for_jobwork, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_item_dtls_for_jobwork w SET w.modified_type =:mstatus WHERE w.unadviceid = :unadviceid")
    int updateWm_unload_advice_item_dtls_for_jobwork(@Param("unadviceid") String unadviceid,@Param("mstatus") String mstatus);

	@Query(value="select * from wm_unload_advice_item_dtls_for_jobwork s where s.modified_type = 'INSERTED' and s.unadviceid =:unadviceid", nativeQuery=true)
	List<Map<String,Object>> retriveUnloadAdviceJobwork(@Param("unadviceid") String unadviceid);
	
	@Query("select SUM(w.item_qty) from Wm_unload_advice_item_dtls_for_jobwork w where w.unadviceid = :code and w.modified_type = 'INSERTED'")
	double getTotalJobItem(@Param("code") String code);
}
