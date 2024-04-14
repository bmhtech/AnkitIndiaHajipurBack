package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Qc_rules_setup_dtls;

public interface Qc_rules_setup_dtlsRepository extends JpaRepository<Qc_rules_setup_dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Qc_rules_setup_dtls w SET w.modified_type =:mstatus WHERE w.qc_id = :qc_id")
    int updateQc_rules_setup_dtls(@Param("qc_id") String qc_id,@Param("mstatus") String mstatus);

}
