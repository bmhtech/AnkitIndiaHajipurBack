package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Dieselusedimport;
import com.AnkitIndia.jwtauthentication.model.Powercutreport;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;

public interface PowercutreportRepository extends JpaRepository<Powercutreport, Long>{
	
	@Query("select COUNT(id) from Powercutreport")
	String countId();
	
	@Query("select d from Powercutreport d where d.powercutdate=:currDate and d.fin_year=:finyear and d.modified_type='INSERTED' ORDER BY id")
	List<Powercutreport> getPowerlist(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
	@Query(value = "{call getSearchreport(:#{#tablename},:#{#order_date},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Powercutreport> getsearchdatapower(@Param("tablename") String tablename
			,@Param("order_date") String order_date, 
			@Param("fromdate") String fromdate,
			@Param("todate") String todate,
			@Param("finyear") String finyear);

}
