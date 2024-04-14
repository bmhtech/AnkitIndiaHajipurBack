package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Bom_master;

public interface Bom_masterRepository extends JpaRepository<Bom_master, Long>{
	
	@Query("select max(substring(production_code , 12, length(production_code))) from Bom_master where production_code like %:code% and business_unit=:bunit and shop_floor=:sfloor and company_id =:company ")
	String getBMSequenceId(@Param("code") String code,@Param("bunit") String bunit,@Param("sfloor") String sfloor,@Param("company") String company);
	
	@Query("select COUNT(id) from Bom_master")
	String countId();
	
	@Query("select p from Bom_master p where p.modified_type ='INSERTED' ORDER BY p.production_id DESC")
	List<Bom_master> findAllBom();
	
	@Query("select p from Bom_master p where p.modified_type ='INSERTED' and p.business_unit =:bunit and p.shop_floor =:sfloor and p.prod_process like %:process% and p.company_id =:company")
	List<Bom_master> getBomThruProcess(@Param("bunit") String bunit,@Param("sfloor") String sfloor,@Param("process") String process,@Param("company") String company);
	
	@Query("select p from Bom_master p where p.modified_type ='INSERTED' and p.production_id =:bomid and p.company_id =:company")
	Bom_master getBomDetails(@Param("bomid") String bomid,@Param("company") String company);
	
	@Query("select p from Bom_master p where p.modified_type ='INSERTED' and p.production_id =:bomid ")
	Bom_master getBomDetails(@Param("bomid") String bomid);
	

	
	@Query("select CASE  WHEN count(p)> 0 THEN true ELSE false END from Bom_master p where p.modified_type ='INSERTED' and p.prod_process like %:process% ")
	boolean checkprocessexist(@Param("process") String process);

	
}
