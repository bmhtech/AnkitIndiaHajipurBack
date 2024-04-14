package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.State_master;

public interface State_masterRepository extends JpaRepository<State_master, Long> {
	
	@Query("SELECT MAX(SUBSTRING(state_code , 3 , length(state_code))) FROM State_master where state_code like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select s from State_master s where s.state_code =:stcode and s.modified_type='INSERTED'")
	State_master getState(@Param("stcode") String stcode);
	
	@Query("select s from State_master s where s.modified_type='INSERTED'")
	List<State_master> getStates();
	
}