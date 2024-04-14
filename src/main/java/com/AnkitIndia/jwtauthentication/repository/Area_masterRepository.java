package com.AnkitIndia.jwtauthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Area_master;

public interface Area_masterRepository extends JpaRepository<Area_master, Long>{

	@Query("SELECT MAX(SUBSTRING(area_id , 3 , length(area_id))) FROM Area_master where area_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("SELECT DISTINCT i.city_code FROM Area_master i where i.city_code =:city_code and i.modified_type !='DELETED'")
    Optional<String> getAreaDtlsThruCity(@Param("city_code") String city_code);
	
}
