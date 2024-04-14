package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Acc_pay_term_master_details;

public interface Acc_pay_term_master_detailsRepository extends JpaRepository<Acc_pay_term_master_details, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Acc_pay_term_master_details w SET w.modified_type =:mstatus WHERE w.payterm_id = :payterm_id")
    int updateAcc_pay_term_master_details(@Param("payterm_id") String payterm_id,@Param("mstatus") String mstatus);

}
