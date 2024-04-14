package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AnkitIndia.jwtauthentication.model.Seives_master;

public interface Seives_masterRepository extends JpaRepository<Seives_master, Long>{
	
	@Query("select COUNT(id) from Seives_master")
	String countId();
	
	@Query("select d from Seives_master d where d.modified_type='INSERTED' ORDER BY id")
	List<Seives_master> getSeivesMasterlist();
	

}
