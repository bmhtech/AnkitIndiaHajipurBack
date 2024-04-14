package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Broker_templt;

public interface Broker_templtRepository extends JpaRepository<Broker_templt, Long>{
	@Query("select COUNT(id) from Broker_templt")
	String countId();
	
	/*@Query("select b from Broker_templt b where i.bp_grp_active = :sts and i.modified_type != 'DELETED'")
	List<Supplier_group> getSupplierGroupList(Boolean sts);*/
}
