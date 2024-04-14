package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Gatepassregister;


public interface GatepassregisterRepository extends JpaRepository<Gatepassregister, Long>{

	@Query("select COUNT(id) from Gatepassregister")
	String countId();
	
	@Query("select d from Gatepassregister d where d.gp_date=:currDate and d.fin_year=:finyear and d.modified_type='INSERTED' ORDER BY id")
	List<Gatepassregister> getGatepassRegisterList(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
	
	@Query(value = "{call getSearchreport(:#{#tablename},:#{#order_date},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Gatepassregister> searchgatePassRegister(@Param("tablename") String tablename
			,@Param("order_date") String order_date, 
			@Param("fromdate") String fromdate,
			@Param("todate") String todate,
			@Param("finyear") String finyear);
	
}
