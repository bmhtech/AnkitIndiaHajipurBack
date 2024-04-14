package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Wheatstackcardreport;

public interface WheatstackcardreportRepository extends JpaRepository<Wheatstackcardreport, Long>{

	
	@Query("select COUNT(id) from Wheatstackcardreport")
	String countId();
	
	@Query("select  w.id,w.wheatstackid,w.business_unitname,w.godownname,w.stackno,w.closed from Wheatstackcardreport w where w.modified_type = 'INSERTED' and w.fin_year =:finyear ORDER BY w.id ")
	List<Object[]>getwheatstack(@Param("finyear") String finyear);
	
	
}
