package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stock_transfer_resource_cost;

public interface Stock_transfer_resource_costRepository extends JpaRepository<Stock_transfer_resource_cost, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_transfer_resource_cost s SET s.modified_type ='UPDATED' WHERE s.order_id = :order_id")
    int updateStock_transfer_resource_cost(@Param("order_id") String order_id);
	
	@Query("select p from Stock_transfer_resource_cost p where p.modified_type = 'INSERTED' and p.order_id = :order_id ")
	List<Stock_transfer_resource_cost> getStockTransReCost(@Param("order_id") String order_id);
	

}
