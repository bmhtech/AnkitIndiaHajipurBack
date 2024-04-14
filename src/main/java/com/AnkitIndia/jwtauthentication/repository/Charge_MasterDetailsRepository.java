package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Charge_Masterdtls;

public interface Charge_MasterDetailsRepository extends JpaRepository<Charge_Masterdtls, Long>{
	
  @Modifying(clearAutomatically = true)
  @Query("UPDATE Charge_Masterdtls w SET w.modified_type =:mstatus WHERE w.charge_id = :charge_id and w.modified_type ='INSERTED'")
  int updateCharge_Masterdtls(@Param("charge_id") String charge_id,@Param("mstatus") String mstatus);

}
