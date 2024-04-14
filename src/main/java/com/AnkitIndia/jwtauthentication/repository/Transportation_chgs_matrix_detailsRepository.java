package com.AnkitIndia.jwtauthentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.TransportationChgsMatrixMaster;
import com.AnkitIndia.jwtauthentication.model.Transportation_chgs_matrix_details;

public interface Transportation_chgs_matrix_detailsRepository extends JpaRepository<Transportation_chgs_matrix_details, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Transportation_chgs_matrix_details t SET t.modified_type =:mstatus WHERE t.tcm_id = :tcm_id AND t.modified_type='INSERTED'")
    int Transportation_chgs_matrix_detailsUpdate(@Param("tcm_id") String tcm_id,@Param("mstatus") String mstatus);
	
	@Query( "select t from Transportation_chgs_matrix_details t where t.tcm_id =:tcmid")
	Optional<Transportation_chgs_matrix_details> findByTcmId(@Param("tcmid") String tcmid);
}
