package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_broker_dtls;

public interface Wm_loading_advice_broker_dtlsRepository extends JpaRepository<Wm_loading_advice_broker_dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice_broker_dtls w SET w.modified_type =:mstatus WHERE w.advice_id = :advice_id")
    int wm_loading_advice_broker_dtlsUpdate(@Param("advice_id") String advice_id,@Param("mstatus") String mstatus);
	
	@Query( "select s from Wm_loading_advice_broker_dtls s where s.modified_type = 'INSERTED' and s.advice_id =:order_id ORDER BY s.slno")
	List<Wm_loading_advice_broker_dtls> getLoadingAdvBrokerDtls(@Param("order_id") String order_id);
}
