package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Acc_acceptance_norms_master_dts;

public interface Acc_acceptance_norms_master_dtsRepository extends JpaRepository<Acc_acceptance_norms_master_dts, Long> {

	@Query( "select a from Acc_acceptance_norms_master_dts a where a.anmd_code = :code ")
	List<Acc_acceptance_norms_master_dts> getAccNormsDetails(@Param("code") String code);
	
}
