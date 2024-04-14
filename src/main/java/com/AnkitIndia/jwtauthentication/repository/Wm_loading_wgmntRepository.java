package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_wgmnt;


public interface Wm_loading_wgmntRepository extends JpaRepository<Wm_loading_wgmnt, Long> {

	@Query("select COUNT(id) from Wm_loading_wgmnt")
	String countId();
	
	@Query( "select w from Wm_loading_wgmnt_dtls w where w.wgment_id =:code ")
	List<Wm_loading_wgmnt> wmLoadingDtlsRetriveList(@Param("code") String code);
	
	
}