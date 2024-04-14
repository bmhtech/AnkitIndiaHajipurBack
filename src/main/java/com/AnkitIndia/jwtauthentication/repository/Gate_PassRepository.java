package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Gate_Pass;

public interface Gate_PassRepository extends JpaRepository<Gate_Pass, Long >{
	
	@Query("select COUNT(id) from Gate_Pass")
	String countId();

}
