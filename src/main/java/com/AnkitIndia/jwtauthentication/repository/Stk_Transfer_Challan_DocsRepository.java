package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Docs;

public interface Stk_Transfer_Challan_DocsRepository extends JpaRepository<Stk_Transfer_Challan_Docs, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan_Docs s SET s.modified_type ='UPDATED' WHERE s.stk_challan_id = :stk_challan_id")
    int updateStk_Transfer_Challan_Docs(@Param("stk_challan_id") String stk_challan_id);
	
	@Query("select s from Stk_Transfer_Challan_Docs s where s.modified_type = 'INSERTED' and s.stk_challan_id = :stk_challan_id ")
	List<Stk_Transfer_Challan_Docs> getStkTransChallanDocs(@Param("stk_challan_id") String stk_challan_id);
	
}
