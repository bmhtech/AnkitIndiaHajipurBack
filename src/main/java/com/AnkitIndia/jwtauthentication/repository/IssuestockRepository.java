package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Issuestock;
import com.AnkitIndia.jwtauthentication.model.Requisition;

public interface IssuestockRepository extends JpaRepository<Issuestock, Long>{
	
	
	@Query("Select count(id) from Issuestock")
	String countissued();
	
	@Query("select r from Issuestock r where modified_type ='INSERTED' and r.fin_year=:finyear ORDER BY r.id DESC")
	List<Issuestock> getIssueStocklist(@Param("finyear") String finyear);
}
