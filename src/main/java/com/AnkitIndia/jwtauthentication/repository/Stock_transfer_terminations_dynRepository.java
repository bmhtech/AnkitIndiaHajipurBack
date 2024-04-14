package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stock_transfer_terminations_dyn;

public interface Stock_transfer_terminations_dynRepository extends JpaRepository<Stock_transfer_terminations_dyn, Long>{
	
	@Query( "select a from Stock_transfer_terminations_dyn a where a.order_id =:order_id and a.modified_type ='INSERTED' ")
	List<Stock_transfer_terminations_dyn> getStockTransTermDtls(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_transfer_terminations_dyn s SET s.modified_type ='UPDATED' WHERE s.order_id = :order_id")
    int updateStock_transfer_terminations_dyn(@Param("order_id") String order_id);

}
