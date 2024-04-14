package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stock_transfer_doc;

public interface Stock_transfer_docRepository extends JpaRepository<Stock_transfer_doc, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stock_transfer_doc s SET s.modified_type ='UPDATED' WHERE s.order_id = :order_id")
    int updateStock_transfer_doc(@Param("order_id") String order_id);
	
	@Query("select p from Stock_transfer_doc p where p.modified_type = 'INSERTED' and p.order_id = :order_id ")
	List<Stock_transfer_doc> getStockTransDoc(@Param("order_id") String order_id);

}
