package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Weight_Dtl;

public interface Stk_Transfer_Challan_Weight_DtlRepository extends JpaRepository<Stk_Transfer_Challan_Weight_Dtl, Long>{
	
	@Query( "select s from Stk_Transfer_Challan_Weight_Dtl s where s.modified_type = 'INSERTED' and s.stk_challan_id =:stk_challan_id ")
	Stk_Transfer_Challan_Weight_Dtl getStkTransChallanWtDtls(@Param("stk_challan_id") String stk_challan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan_Weight_Dtl s SET s.modified_type ='UPDATED' WHERE s.stk_challan_id = :stk_challan_id")
    int updateStk_Transfer_Challan_Weight_Dtl(@Param("stk_challan_id") String stk_challan_id);
}
