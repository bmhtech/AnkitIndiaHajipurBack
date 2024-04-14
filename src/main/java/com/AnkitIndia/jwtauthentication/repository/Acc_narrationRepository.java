package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Acc_narration_master;


public interface Acc_narrationRepository extends JpaRepository<Acc_narration_master, Long> {
	@Query("select COUNT(id) from Acc_narration_master")
	String countId();
}