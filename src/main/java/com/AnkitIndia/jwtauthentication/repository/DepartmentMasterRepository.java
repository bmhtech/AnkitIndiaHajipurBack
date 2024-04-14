package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Department_master;

public interface DepartmentMasterRepository extends JpaRepository<Department_master, Long>{

	@Query("SELECT MAX(SUBSTRING(department_code , 3 , length(department_code))) FROM Department_master where department_code like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select d from Department_master d where d.department_active = :sts and d.modified_type != 'DELETED' ")
	List<Department_master> getDeptNameCode(@Param("sts") Boolean sts);
	
	@Query("select i from Department_master i")
	List<Department_master> getDepartmentNCList();
	
	@Query( "select d from Department_master d where d.department_code = :deptid and d.modified_type != 'DELETED' ")
	Department_master getDeptName(@Param("deptid") String deptid);
}
