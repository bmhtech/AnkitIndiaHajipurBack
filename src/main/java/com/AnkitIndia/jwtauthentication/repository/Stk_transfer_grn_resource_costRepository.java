package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_resource_cost;

public interface Stk_transfer_grn_resource_costRepository extends JpaRepository<Stk_transfer_grn_resource_cost, Long>{
	
	@Query( "select a from Stk_transfer_grn_resource_cost a where a.stk_grn_id =:stk_grn_id and a.modified_type ='INSERTED' ")
	List<Stk_transfer_grn_resource_cost> getStk_transfer_grn_resource_cost(@Param("stk_grn_id") String stk_grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_grn_resource_cost s SET s.modified_type ='UPDATED' WHERE s.stk_grn_id = :stk_grn_id")
    int updateStk_transfer_grn_resource_cost(@Param("stk_grn_id") String stk_grn_id);

}
