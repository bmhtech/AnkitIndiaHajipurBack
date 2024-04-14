package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Visitor_master;

public interface Visitor_masterRepository extends JpaRepository<Visitor_master, Long>{

	
	@Query("select COUNT(id) from Visitor_master")
	String countId();
	
	@Query("select g from Visitor_master g where g.modified_type='INSERTED'")
	List<Visitor_master> findcheckList();
	
	//GatepassChecklist for demo,
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Driver_master pl where pl.modified_type != 'DELETED' and pl.catagory=:visitor_id")
	Boolean checkVisitors(@Param("visitor_id") String visitor_id);
}
