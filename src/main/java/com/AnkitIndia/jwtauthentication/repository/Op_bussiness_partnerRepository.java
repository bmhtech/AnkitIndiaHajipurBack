package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner;


public interface Op_bussiness_partnerRepository extends JpaRepository<Op_bussiness_partner, Long> {

	@Query("select COUNT(id) from Op_bussiness_partner")
	String countId();
	
	//@Query( "select c.company_name from Company_master c where c.company_code = :ccode" )
	//String getCompCodeToCompName(String ccode);
	
	//@Query( "select c.company_code from Company_master c where c.company_name = :cname" )
	//String getCompNameToCompCode(String cname);
	
	@Query( "select o from Op_bussiness_partner o where o.modified_type != 'DELETED' and o.bp_active =:status ")
	List<Op_bussiness_partner> otherPartnerMsNameList(@Param("status") boolean status);
	
	/*
	
	
	

	*/

}