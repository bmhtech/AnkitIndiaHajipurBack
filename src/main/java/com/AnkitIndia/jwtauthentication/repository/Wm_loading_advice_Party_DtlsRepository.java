package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_Party_Dtls;

public interface Wm_loading_advice_Party_DtlsRepository extends JpaRepository<Wm_loading_advice_Party_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice_Party_Dtls w SET w.modified_type =:mstatus WHERE w.advice_id = :advice_id")
    int wm_loading_advice_Party_DtlsUpdate(@Param("advice_id") String advice_id,@Param("mstatus") String mstatus);

	@Query( "select s from Wm_loading_advice_Party_Dtls s where s.modified_type = 'INSERTED' and s.advice_id =:advice_id ORDER BY s.sl_no")
	List<Wm_loading_advice_Party_Dtls> getLoadingAdvPartyDtls(@Param("advice_id") String advice_id);
}
