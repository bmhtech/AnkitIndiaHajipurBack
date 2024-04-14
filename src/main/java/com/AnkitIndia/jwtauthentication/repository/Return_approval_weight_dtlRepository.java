package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Return_approval_weight_dtl;
import com.AnkitIndia.jwtauthentication.model.Wm_charges_master;

public interface Return_approval_weight_dtlRepository extends JpaRepository<Return_approval_weight_dtl, Long>{
	
	@Query( "select c from Return_approval_weight_dtl c where c.modified_type = 'INSERTED' AND c.salesreturnid = :salesreturnid")
	Return_approval_weight_dtl getReturnApprovalWD(@Param("salesreturnid") String salesreturnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_weight_dtl w SET w.modified_type =:mstatus WHERE w.salesreturnid = :salesreturnid")
    int updateReturn_approval_weight_dtl(@Param("salesreturnid") String salesreturnid,@Param("mstatus") String mstatus);
}
