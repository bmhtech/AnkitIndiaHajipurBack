package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.AnkitIndia.jwtauthentication.model.Purpose_master;

public interface Purpose_masterRepository extends JpaRepository<Purpose_master, Long>{
	
	@Query("SELECT MAX(SUBSTRING(purpose_id , 3 , length(purpose_id))) FROM Purpose_master where purpose_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select p from Purpose_master p where p.modified_type != 'DELETED'")
	List<Purpose_master> getPurposeList();
	
	
}
