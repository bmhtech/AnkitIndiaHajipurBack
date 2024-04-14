package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Uniqueid;

public interface UniqueidRepository extends JpaRepository<Uniqueid, Long> {
	
	@Query("select MAX(id) from Uniqueid")
	String getSequenceId();
	
}
