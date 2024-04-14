package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Employee_master;
import com.AnkitIndia.jwtauthentication.model.HubMaster;

public interface HubMasterRepository extends JpaRepository<HubMaster, Long>{
	
	@Query("select COUNT(id) from HubMaster")
	String countId();
	
	@Query("select g from HubMaster g where g.modified_type='INSERTED'")
	List<HubMaster> findcheckList();
	
	@Query("select g from HubMaster g where g.modified_type='INSERTED' and g.company_id =:company_id")
	List<HubMaster> getHubMaster(@Param("company_id") String company_id);
	
	@Query( "select e from HubMaster e where e.modified_type = 'INSERTED' and e.hubid =:hubid")
	HubMaster getHubNameById(@Param("hubid") String hubid);
}
