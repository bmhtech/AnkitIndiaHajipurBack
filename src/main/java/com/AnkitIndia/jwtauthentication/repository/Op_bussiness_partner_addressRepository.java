package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner_address;

public interface Op_bussiness_partner_addressRepository extends JpaRepository<Op_bussiness_partner_address, Long>{
	
	@Query( "select o from Op_bussiness_partner_address o where o.bp_Id = :code" )
	Op_bussiness_partner_address oBPAddressRetriveList(@Param("code") String code);

}
