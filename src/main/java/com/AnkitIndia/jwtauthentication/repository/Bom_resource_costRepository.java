package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Bom_resource_cost;

public interface Bom_resource_costRepository extends JpaRepository<Bom_resource_cost, Long>{
	
	@Query( "select b from Bom_resource_cost b where b.production_id =:production_id and b.modified_type ='INSERTED'")
	List<Bom_resource_cost> getBom_resource_cost(@Param("production_id") String production_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Bom_resource_cost b SET b.modified_type ='UPDATED' WHERE b.production_id = :production_id")
    int updateBom_resource_cost(@Param("production_id") String production_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Bom_resource_cost w SET w.modified_type ='DELETED' WHERE w.production_id = :production_id")
    int bomResourceCostUpdate(@Param("production_id") String production_id);
}
