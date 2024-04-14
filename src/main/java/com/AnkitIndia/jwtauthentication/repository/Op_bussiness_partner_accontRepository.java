package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner_accont;

public interface Op_bussiness_partner_accontRepository extends JpaRepository<Op_bussiness_partner_accont, Long>{

	@Query( "select o from Op_bussiness_partner_accont o where o.bp_Id = :code" )
	Op_bussiness_partner_accont oBPAccountRetriveList(@Param("code") String code);
	
}
