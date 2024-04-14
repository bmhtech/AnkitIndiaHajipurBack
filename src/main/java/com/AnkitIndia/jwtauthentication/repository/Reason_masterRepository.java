package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Reason_master;

public interface Reason_masterRepository extends JpaRepository<Reason_master,Long>{
	
	
	@Query("SELECT MAX(SUBSTRING(reason_id , 4 , length(reason_id))) FROM Reason_master where reason_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select r from Reason_master r where r.modified_type != 'DELETED' ")
	List<Reason_master> getReasonMNCList();
	
	@Query( "select r from Reason_master r where r.modified_type != 'DELETED' and r.screen_name =:screen")
	List<Reason_master> getReasonIndent(@Param("screen") String screen);

	@Query( "select r from Reason_master r where r.modified_type != 'DELETED' and r.screen_name =:screen ")
	List<Reason_master> getPurTermReasons(@Param("screen") String screen);
}
