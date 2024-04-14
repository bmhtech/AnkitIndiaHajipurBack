package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Ledgermaster;
import com.AnkitIndia.jwtauthentication.model.Store_inv_charge_master;

public interface Store_inv_charge_masterRepository extends JpaRepository<Store_inv_charge_master, Long> {
	
	@Query("select COUNT(id) from Store_inv_charge_master")
	String countId();
	
	@Query(value="select * from store_inv_charge_master c where c.modified_type = 'INSERTED'", nativeQuery=true)
	 List<Map<String, Object>> getStoreChargesList();
	
	@Query(value="select * from store_inv_charge_master_dtls c where c.modified_type = 'INSERTED' AND c.store_charge_id=:code", nativeQuery=true)
	 List<Map<String, Object>> getStoreChargeMasterDtlsList(@Param("code") String code);
	
	@Query( "select l from Store_inv_charge_master l where l.store_charge_id =:code")
	Store_inv_charge_master getStoreChargeMaster(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Store_inv_charge_master_dtls w SET w.modified_type =:mstatus WHERE w.store_charge_id = :store_charge_id AND w.modified_type ='INSERTED'")
    int updateStoreInvChgsDetails(@Param("store_charge_id") String store_charge_id,@Param("mstatus") String mstatus);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order pl where pl.modified_type = 'INSERTED' and pl.store_charges=:code")
	Boolean checkStoreChargeMasterUsage(@Param("code") String code);
	
}
