package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Country_master;

public interface Country_masterRepository extends JpaRepository<Country_master, Long> {
	
	@Query("SELECT MAX(SUBSTRING(country_code , 4 , length(country_code))) FROM Country_master where country_code like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select c from Country_master c where c.country_name =:CName")
	Country_master getCountry(@Param("CName") String CName);
	
	@Query( "select c from Country_master c")
	List<Country_master> country();
	
}