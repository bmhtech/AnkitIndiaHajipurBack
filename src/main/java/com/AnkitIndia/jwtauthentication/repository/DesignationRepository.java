package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.AnkitIndia.jwtauthentication.model.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Long> {

	@Query("SELECT MAX(SUBSTRING(desig_id , 4 , length(desig_id))) FROM Designation where desig_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select d from Designation d where d.modified_type != 'DELETED' and d.desig_id =:code ")
	Designation getDesigNameToCode(@Param("code") String code);
	
	@Query("select d from Designation d")
	List<Designation> getDesignation();
	
	@Query(value="select * from designation where modified_type = 'INSERTED'", nativeQuery=true)
	List<Map<String,Object>> designationListNew();
	
}
