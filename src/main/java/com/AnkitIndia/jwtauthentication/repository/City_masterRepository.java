package com.AnkitIndia.jwtauthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AnkitIndia.jwtauthentication.model.City_master;

public interface City_masterRepository extends JpaRepository<City_master, Long> {
	
	@Query("SELECT MAX(SUBSTRING(city_code , 3 , length(city_code))) FROM City_master where city_code like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("SELECT DISTINCT i.dist_code FROM City_master i where i.dist_code =:dist_code and i.modified_type !='DELETED'")
    Optional<String> getCityDtlsThruDist(@Param("dist_code") String dist_code);
		
	@Query("select c from City_master c where c.city_code = :citycode")
	City_master getCityDtls(@Param("citycode") String citycode);
	
	
}