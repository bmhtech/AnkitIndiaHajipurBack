package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Bingroup;
import com.AnkitIndia.jwtauthentication.model.Company_business_unit_master;

public interface BingroupRepository extends JpaRepository<Bingroup, Long>{

	@Query("select COUNT(id) from Bingroup")
	String countId();
	
	@Query("select d from Bingroup d where d.modified_type='INSERTED' ORDER BY id")
	List<Bingroup> getBinGrouplist();
	
	@Query( "select b from Bingroup b where b.bingroupid = :bingroupid and b.modified_type = 'INSERTED'")
	Bingroup binGroupName(@Param("bingroupid") String bingroupid);
}
