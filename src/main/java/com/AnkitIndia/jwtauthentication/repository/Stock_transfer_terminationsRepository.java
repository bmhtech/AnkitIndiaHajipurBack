package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stock_transfer_terminations;

public interface Stock_transfer_terminationsRepository extends JpaRepository<Stock_transfer_terminations, Long>{
	
	@Query( "select a from Stock_transfer_terminations a where a.order_id =:order_id and a.modified_type ='INSERTED' ")
	Stock_transfer_terminations getStkTransTerms(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_transfer_terminations s SET s.modified_type ='UPDATED' WHERE s.order_id = :order_id")
    int updateStock_transfer_terminations(@Param("order_id") String order_id);

}
