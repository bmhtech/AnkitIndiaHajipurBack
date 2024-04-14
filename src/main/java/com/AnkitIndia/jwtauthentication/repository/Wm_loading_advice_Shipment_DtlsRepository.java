package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_Shipment_Dtls;

public interface Wm_loading_advice_Shipment_DtlsRepository extends JpaRepository<Wm_loading_advice_Shipment_Dtls, Long> {
	
	@Query( "select w from Wm_loading_advice_Shipment_Dtls w where w.modified_type ='INSERTED' and w.advice_id =:advice_id ")
	Wm_loading_advice_Shipment_Dtls getLoadingAdvShipDtls(@Param("advice_id") String advice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice_Shipment_Dtls w SET w.modified_type =:mstatus WHERE w.advice_id = :advice_id")
    int wm_loading_advice_Shipment_DtlsUpdate(@Param("advice_id") String advice_id,@Param("mstatus") String mstatus);

}
