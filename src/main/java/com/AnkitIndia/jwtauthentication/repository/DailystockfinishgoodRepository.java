package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;


import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Binreport;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood;


public interface DailystockfinishgoodRepository extends JpaRepository<Dailystockfinishgood, Long>{

	@Query("select COUNT(id) from Dailystockfinishgood")
	String countId();
	
	@Query("select d from Dailystockfinishgood d where d.date=:currDate and d.fin_year=:finyear and d.modified_type='INSERTED' ORDER BY id")
	List<Dailystockfinishgood> getdailylist(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
	
	@Query(value = "{call getSearchreport(:#{#tablename},:#{#order_date},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Dailystockfinishgood> getsearchdatapower(@Param("tablename") String tablename
			,@Param("order_date") String order_date, 
			@Param("fromdate") String fromdate,
			@Param("todate") String todate,
			@Param("finyear") String finyear);
	
	
	
	@Query(value ="select * from dailystockfinishgood d where d.date<:currDate and d.fin_year=:finyear and d.modified_type='INSERTED' ORDER BY id DESC LIMIT 1",nativeQuery=true)
	Dailystockfinishgood getdetails(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
	
	@Query("select  COUNT(id) from Dailystockfinishgood d where d.date=:currDate and d.fin_year=:finyear and d.modified_type='INSERTED'")
	int countdate(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
	
	@Query(value = "{call DailySaleReport(:#{#fromdate},:#{#todate})}", nativeQuery = true)
	List<Tuple> getdailyloading(@Param("fromdate") String fromdate,@Param("todate") String todate);

	@Query(value = "{call DailySaleReportJobwork(:#{#fromdate},:#{#todate})}", nativeQuery = true)
	List<Tuple> getdailyloadingJobwork(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Dailystockfinishgood pl where pl.modified_type = 'INSERTED' ")
	Boolean checkDailyStockFG();
	
	@Query(value ="select * from dailystockfinishgood d where  d.business_unit=:bunit and date<:date and d.fin_year=:finyear and d.modified_type='INSERTED' ORDER BY date DESC LIMIT 1",nativeQuery=true)
	Dailystockfinishgood getlastrowdata(@Param("bunit") String bunit,@Param("date") String date,@Param("finyear") String finyear);
	
}

