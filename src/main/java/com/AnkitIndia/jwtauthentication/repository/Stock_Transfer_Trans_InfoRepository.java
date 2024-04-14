package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stock_Transfer_Trans_Info;

public interface Stock_Transfer_Trans_InfoRepository extends JpaRepository<Stock_Transfer_Trans_Info, Long >{
	
	@Query( "select a from Stock_Transfer_Trans_Info a where a.order_id = :order_id and a.modified_type ='INSERTED'")
	Stock_Transfer_Trans_Info getStkTransTranInfo(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_Transfer_Trans_Info s SET s.modified_type ='UPDATED' WHERE s.order_id = :order_id")
    int updateStock_Transfer_Trans_Info(@Param("order_id") String order_id);
}
