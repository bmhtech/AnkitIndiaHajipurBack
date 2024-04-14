package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Granulation;
import com.AnkitIndia.jwtauthentication.model.Wheatacceptance;

public interface WheatacceptanceRepository extends JpaRepository<Wheatacceptance, Long>{

	@Query("select COUNT(id) from Wheatacceptance")
	String countId();
	
	@Query("select d from Wheatacceptance d where d.date=:currDate and d.fin_year=:finyear and d.modified_type='INSERTED' ORDER BY id")
	List<Wheatacceptance> getWheatAcceptancelist(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
	
	@Query(value = "{call getSearchreport(:#{#tablename},:#{#order_date},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Wheatacceptance> searchWheatAcceptance(@Param("tablename") String tablename
			,@Param("order_date") String order_date, 
			@Param("fromdate") String fromdate,
			@Param("todate") String todate,
			@Param("finyear") String finyear);
	
	@Query("select d from Wheatacceptance d where d.acceptanceid=:acceptanceid and d.modified_type='INSERTED'")
	List<Wheatacceptance> getWheatAcceptancePrintList(@Param("acceptanceid") String acceptanceid);
	
}
