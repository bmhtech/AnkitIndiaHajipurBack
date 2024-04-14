package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Misc_master;

public interface MiscMasterRepository extends JpaRepository<Misc_master, Long>{
	
	@Query("SELECT MAX(SUBSTRING(mastertype_code , 4 , length(mastertype_code))) FROM Misc_master where mastertype_code like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select m from Misc_master m where m.mastertype_active = :sts and m.modified_type != 'DELETED' ")
	List<Misc_master> getMiscNameCode(@Param("sts") Boolean sts);
	
	@Query("select mastertype_code,mastertype_name from Misc_master")
	List<Misc_master> getMiscNCList();
	
	@Query( "select m from Misc_master m where m.mastertype_active = :sts and m.modified_type != 'DELETED' and m.mastertype_name ='Supplier Webridge'")
	List<Misc_master> getMiscList(@Param("sts") boolean sts);

}
