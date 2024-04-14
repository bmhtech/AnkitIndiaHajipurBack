package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Zone_master;

public interface Zone_masterRepository extends JpaRepository<Zone_master, Long>{

	@Query("SELECT MAX(SUBSTRING(zone_id , 3 , length(zone_id))) FROM Zone_master where zone_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select a from Zone_master a")
	List<Zone_master> zoneList();
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order pl where pl.modified_type != 'DELETED' and pl.app_chgs_id=:code")
	Boolean checkZoneMasterUsage(@Param("code") String code);
	
}
