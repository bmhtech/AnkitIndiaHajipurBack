package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Dailyproduction;
import com.AnkitIndia.jwtauthentication.model.Item_master;

public interface DailyproductionRepository extends JpaRepository<Dailyproduction, Long>{
	
	@Query("select COUNT(id) from Dailyproduction")
	String countId();
	
	@Query("select d from Dailyproduction d where d.date=:currDate and d.fin_year=:finyear and d.modified_type='INSERTED' ORDER BY id DESC")
	List<Dailyproduction> getDailyproductionList(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
	
	@Query(value = "{call getSearchreport(:#{#tablename},:#{#order_date},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Dailyproduction> searchDailyProduction(@Param("tablename") String tablename
			,@Param("order_date") String order_date, 
			@Param("fromdate") String fromdate,
			@Param("todate") String todate,
			@Param("finyear") String finyear);


	
	@Query(value ="select * from dailyproduction d where d.date<:date  and d.modified_type='INSERTED' ORDER BY id DESC LIMIT 1",nativeQuery=true)
	Dailyproduction getdetailsbydate(@Param("date") String date);
	
	
}
