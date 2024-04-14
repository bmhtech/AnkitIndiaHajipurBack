package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stock_Transfer_Summary;

public interface Stock_Transfer_SummaryRepository extends JpaRepository<Stock_Transfer_Summary, Long>{
	
	@Query( "select a from Stock_Transfer_Summary a where a.order_id =:order_id and a.modified_type ='INSERTED' ")
	Stock_Transfer_Summary getStkTransSumm(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_Transfer_Summary s SET s.modified_type ='UPDATED' WHERE s.order_id = :order_id")
    int updateStock_Transfer_Summary(@Param("order_id") String order_id);

}
