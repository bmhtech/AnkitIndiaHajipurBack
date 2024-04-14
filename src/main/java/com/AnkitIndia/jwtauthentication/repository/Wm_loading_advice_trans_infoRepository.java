package com.AnkitIndia.jwtauthentication.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_Shipment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_trans_info;

public interface Wm_loading_advice_trans_infoRepository extends JpaRepository<Wm_loading_advice_trans_info, Long>{

	@Query( "select w from Wm_loading_advice_trans_info w where w.advice_id = :code and w.company_id=:company and w.modified_type = 'INSERTED'" )
	Wm_loading_advice_trans_info loadingTransInfoRetriveList(@Param("code") String code,@Param("company") String company);
	
	//copy first for company purpose
	@Query( "select w from Wm_loading_advice_trans_info w where w.advice_id = :code and w.modified_type = 'INSERTED'" )
	Wm_loading_advice_trans_info loadingTransInfoRetriveList(@Param("code") String code);
	
	@Query( "select w from Wm_loading_advice_trans_info w where w.modified_type ='INSERTED' and w.advice_id =:advice_id ")
	Wm_loading_advice_trans_info getLoadingAdvTransinfo(@Param("advice_id") String advice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice_trans_info w SET w.modified_type =:mstatus WHERE w.advice_id = :advice_id")
    int wm_loading_advice_trans_infoupdate(@Param("advice_id") String advice_id,@Param("mstatus") String mstatus);
	
	@Query(value= "SELECT * from wm_loading_advice_trans_info  where modified_type = 'INSERTED' and advice_id=:refid", nativeQuery=true)
	Map<String,Object> getloadingTransDetails(@Param("refid") String refid);
	
}
