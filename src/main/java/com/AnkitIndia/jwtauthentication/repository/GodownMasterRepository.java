package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.GodownMaster;
import com.AnkitIndia.jwtauthentication.model.HubMaster;


public interface GodownMasterRepository  extends JpaRepository<GodownMaster, Long>{
	
	@Query("select COUNT(id) from GodownMaster")
	String countId();
	
	@Query("select g from GodownMaster g where g.modified_type='INSERTED'")
	List<GodownMaster> findcheckList();
	
	@Query("select g from GodownMaster g where g.modified_type='INSERTED' and g.company_id =:company_id")
	List<GodownMaster> getGodownMasterList(@Param("company_id") String company_id);
	
	@Query( "select e from GodownMaster e where e.modified_type = 'INSERTED' and e.godownid =:godownid")
	GodownMaster getGodownNameById(@Param("godownid") String godownid);
}
