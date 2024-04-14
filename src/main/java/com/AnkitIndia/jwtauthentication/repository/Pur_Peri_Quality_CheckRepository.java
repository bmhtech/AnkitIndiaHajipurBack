package com.AnkitIndia.jwtauthentication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Pur_Peri_Quality_Check;


public interface Pur_Peri_Quality_CheckRepository extends JpaRepository<Pur_Peri_Quality_Check, Long>{
	
	@Query("select COUNT(id) from Pur_Peri_Quality_Check")
	String countId();
	
	
}
