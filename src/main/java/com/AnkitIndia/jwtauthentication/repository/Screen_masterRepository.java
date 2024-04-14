package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Screen_master;



public interface Screen_masterRepository extends JpaRepository<Screen_master, Long> {
	
	@Query("SELECT MAX(SUBSTRING(screen_id , 4 , length(screen_id))) FROM Screen_master where screen_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select s from Screen_master s where s.modified_type != 'DELETED' ")
	List<Screen_master> getScreenMNCList();
	
	@Query( "select s from Screen_master s where s.modified_type != 'DELETED' and s.screen_id =:screen_id ")
	Screen_master getScreenDetails(@Param("screen_id") String screen_id);

}
