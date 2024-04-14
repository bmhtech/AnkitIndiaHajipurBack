package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Acc_acceptance_norms_master;
import com.AnkitIndia.jwtauthentication.model.Acc_acceptance_norms_master_dts;

public interface Acc_acceptance_normsRepository extends JpaRepository<Acc_acceptance_norms_master, Long>{
	@Query("select COUNT(id) from Acc_acceptance_norms_master")
	String countId();
	
	@Query( "select a from Acc_acceptance_norms_master a where a.anm_active = :sts and a.modified_type != 'DELETED' ")
	List<Acc_acceptance_norms_master> getAccNormsNameCode(@Param("sts") Boolean sts);
	
	@Query( "select a from Acc_acceptance_norms_master_dts a where a.anmd_code = :a_id")
	List<Acc_acceptance_norms_master> accNormsRetriveList(@Param("a_id") String a_id);
	
}
