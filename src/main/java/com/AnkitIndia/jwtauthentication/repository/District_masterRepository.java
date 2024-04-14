package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.District_master;


public interface District_masterRepository extends JpaRepository<District_master, Long> {
	
	@Query("SELECT MAX(SUBSTRING(dist_code , 4 , length(dist_code))) FROM District_master where dist_code like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select d from District_master d where d.dist_code =:distcode and d.modified_type !='DELETED'")
	District_master getDistrictDtls(@Param("distcode") String distcode);
	
	@Query( "select d from District_master d where d.modified_type !='DELETED'")
	List<District_master> district();
    
}