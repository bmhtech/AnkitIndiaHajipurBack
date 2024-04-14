package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Acc_taxcode_details_master;

public interface Acc_taxcode_detailsRepository extends JpaRepository<Acc_taxcode_details_master, Long> {
	@Query("select COUNT(id) from Acc_taxcode_details_master")
	String countId();
	
	@Query( "select a from Acc_taxcode_details_master a where a.modified_type != 'DELETED' ")
	List<Acc_taxcode_details_master> getAccTaxcodeNc();
}