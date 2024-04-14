package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Servicemaster;
import com.AnkitIndia.jwtauthentication.model.Termasservice;

public interface TermasserviceRepository extends JpaRepository<Termasservice, Long> 
{
	@Query("Select count(id) from Termasservice")
	String counttermasservice();
	
	@Query(value="select id, business_unitname, termasservicename, termasserviceno from termasservice where modified_type='INSERTED' and fin_year=:finyear", nativeQuery=true)
	List<Map<String, Object>> getTermasService(@Param("finyear") String finyear);
	
	@Query(value="select termasservicename, termasserviceno from termasservice where modified_type='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> getTermAsServiceList();
	

}
