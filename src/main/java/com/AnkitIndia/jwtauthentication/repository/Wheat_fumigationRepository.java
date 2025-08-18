package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wheat_fumigation;

public interface Wheat_fumigationRepository extends JpaRepository<Wheat_fumigation, Long>{
	
	@Query("select COUNT(id) from Wheat_fumigation")
	String countId();
	
	@Query(value= "select * from wheat_fumigation where modified_type='INSERTED' AND fin_year=:fin_year", nativeQuery=true)
	List<Map<String,Object>> getWheatFumigationList(@Param("fin_year") String fin_year);
	
}
