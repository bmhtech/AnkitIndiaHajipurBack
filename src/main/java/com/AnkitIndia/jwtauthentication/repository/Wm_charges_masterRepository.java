package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wm_charges_master;

public interface Wm_charges_masterRepository extends JpaRepository<Wm_charges_master, Long>{
	
	@Query("SELECT MAX(SUBSTRING(wm_charge_id , 4 , length(wm_charge_id))) FROM Wm_charges_master where wm_charge_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select c from Wm_charges_master c where c.modified_type != 'DELETED' ")
	List<Wm_charges_master> getWeighmentCharges();
	
	@Query( "select c from Wm_charges_master c where c.wm_charge_id = :wm_charge_id")
	Wm_charges_master getWeighmentChargeMasters(@Param("wm_charge_id") String wm_charge_id);
	
	@Query( "select c from Wm_charges_master c where c.vehicle_type = :vehicletype")
	Wm_charges_master getWeighmentChargeThruVtype(@Param("vehicletype") String vehicletype);
	
	
	
	@Query("select max(substring(wm_charge_code , 8, length(wm_charge_code))) from Wm_charges_master where wm_charge_code like %:code% and company_id =:company ")
	String getWnChargesSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order pl where pl.modified_type != 'DELETED' and pl.app_chgs_id=:code")
	Boolean checkWmChgsMasterUsage(@Param("code") String code);
	
}
