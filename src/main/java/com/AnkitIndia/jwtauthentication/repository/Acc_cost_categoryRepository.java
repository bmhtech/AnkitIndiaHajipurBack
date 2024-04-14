package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Acc_cost_category_master;


public interface Acc_cost_categoryRepository extends JpaRepository<Acc_cost_category_master, Long> {
	@Query("select COUNT(id) from Acc_cost_category_master")
	String countId();
	
	@Query("select c from Acc_cost_category_master c where c.costcat_active = :sts and c.modified_type != 'DELETED'")
	List<Acc_cost_category_master> getAccCostCategoriCNList(@Param("sts") Boolean sts);
	
	@Query("select c from Acc_cost_category_master c where c.costcat_code = :code")
	Acc_cost_category_master getAccCostCatNListbyC(@Param("code") String  code);
	
}