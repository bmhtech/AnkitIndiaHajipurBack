package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner_broker;

public interface Op_bussiness_partner_brokerRepository extends JpaRepository<Op_bussiness_partner,Long> {
	
	@Query( "select o from Op_bussiness_partner_broker o where o.bp_Id = :code")
	List<Op_bussiness_partner_broker> oBPBrokerRetriveList(@Param("code") String code);

}
