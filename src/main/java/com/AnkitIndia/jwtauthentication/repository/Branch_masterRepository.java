package com.AnkitIndia.jwtauthentication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Branch_master;

public interface Branch_masterRepository extends JpaRepository<Branch_master,Long>{
	
	@Query( "select COUNT(id) from Branch_master")
	String countBranchId();
	
	@Query("select max(substring(branchcode , 3, length(branchcode))) from Branch_master where branchcode like %:prefix% ")
	String countBranchId(@Param("prefix") String prefix);
	
	@Query( "select b from Branch_master b where b.modified_type !='DELETED'")
	List<Branch_master> getBranchDetails();

	@Query( "select b from Branch_master b where b.modified_type !='DELETED' and b.branchcode =:branch ")
	Branch_master getBranchDetails(@Param("branch") String branch);
}
