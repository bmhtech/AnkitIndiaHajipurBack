package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Pur_L1_Selection;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;

public interface Pur_L1_SelectionRepository extends JpaRepository<Pur_L1_Selection, Long> {

	@Query("select COUNT(id) from Pur_L1_Selection")
	String countId();
	
	//@Query( "select c.company_name from Company_master c where c.company_code = :ccode" )
	//String getCompCodeToCompName(String ccode);
	
	//@Query( "select c.company_code from Company_master c where c.company_name = :cname" )
	//String getCompNameToCompCode(String cname);
	
	@Query( "select p from Pur_L1_Selection_Dtls p where p.lsel_id =:code ")
	List<Pur_L1_Selection> l1DtlsRetriveList(@Param("code") String code);
}