
package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_broker_dtls;

public interface Wm_unload_advice_broker_dtlsRepository extends JpaRepository<Wm_unload_advice_broker_dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_broker_dtls w SET w.modified_type ='UPDATED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_broker_dtlsUpdate(@Param("unadviceid") String unadviceid);
	
	@Query("select w from Wm_unload_advice_broker_dtls w where w.unadviceid = :code and w.modified_type = 'INSERTED'")
	List<Wm_unload_advice_broker_dtls> wmUnAdviceBrokerRetriveList(@Param("code") String code);

	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Wm_unload_advice_broker_dtls p SET p.modified_type='UPDATED' WHERE p.unadviceid = :unadviceid and  p.modified_type ='INSERTED'" )
	int updatezerobroker(@Param("unadviceid") String unadviceid);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Wm_unload_advice_broker_dtls p SET p.modified_type='DELETED' WHERE p.unadviceid = :unadviceid and  p.modified_type ='INSERTED'" )
	int wm_unload_advice_broker_dtlsupdate(@Param("unadviceid") String unadviceid);
}
