package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Acc_pay_mode_master;

public interface Acc_pay_modeRepository extends JpaRepository<Acc_pay_mode_master, Long> {
	@Query("select COUNT(id) from Acc_pay_mode_master")
	String countId();
	
	@Query( "select a from Acc_pay_mode_master a where a.paymode_active = :sts and a.modified_type != 'DELETED' ")
	List<Acc_pay_mode_master> getAccPaymodeNc(@Param("sts") Boolean sts);
}