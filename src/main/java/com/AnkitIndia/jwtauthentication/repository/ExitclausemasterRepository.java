package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Exitclausemaster;

public interface ExitclausemasterRepository extends JpaRepository<Exitclausemaster, Long> {
	
	@Query("Select count(id) from Exitclausemaster")
	String countexitclause();
	
	@Query(value="select id, business_unitname, exitclausename, exitclauseno from exitclausemaster where modified_type='INSERTED' and fin_year=:finyear", nativeQuery=true)
	List<Map<String, Object>> getExitClauseMaster(@Param("finyear") String finyear);

}
