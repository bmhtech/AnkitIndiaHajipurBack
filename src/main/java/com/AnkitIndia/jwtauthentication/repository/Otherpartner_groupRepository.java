package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Otherpartner_group;
import com.AnkitIndia.jwtauthentication.model.Transporter_group;


public interface Otherpartner_groupRepository extends JpaRepository<Otherpartner_group, Long> {

	@Query("select COUNT(id) from Otherpartner_group")
	String countId();
	
	//@Query( "select c.company_name from Company_master c where c.company_code = :ccode" )
	//String getCompCodeToCompName(String ccode);
	
	//@Query( "select c.company_code from Company_master c where c.company_name = :cname" )
	//String getCompNameToCompCode(String cname);
	
	@Query("select o from Otherpartner_group o where o.op_active = :sts and o.modified_type != 'DELETED'")
	List<Otherpartner_group> getOtherpartnerGroupList(@Param("sts") Boolean sts);
}