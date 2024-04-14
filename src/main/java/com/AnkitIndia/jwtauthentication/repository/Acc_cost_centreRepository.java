package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Acc_cost_centre_master;


public interface Acc_cost_centreRepository extends JpaRepository<Acc_cost_centre_master, Long> {
	@Query("select COUNT(id) from Acc_cost_centre_master")
	String countId();
	
	@Query("select c from Acc_cost_centre_master c where c.costcentre_active = :sts and c.modified_type != 'DELETED'")
	List<Acc_cost_centre_master> getAccCostCentreN(@Param("sts") Boolean sts);
}