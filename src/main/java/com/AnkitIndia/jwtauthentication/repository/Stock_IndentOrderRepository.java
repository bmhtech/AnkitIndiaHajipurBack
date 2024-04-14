package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Stock_Indent_Order;

public interface Stock_IndentOrderRepository extends JpaRepository<Stock_Indent_Order, Long>{
	
	@Query("select COUNT(id) from Stock_Indent_Order")
	String countId();
	
	@Query("select COUNT(id) from Stock_Indent_Order where indent_date=:idate")
	String getSTISequenceId(@Param("idate") String idate);
	
	@Query( "select s from Stock_Indent_Order s where s.modified_type = 'INSERTED' ORDER BY s.indent_id DESC ")
	List<Stock_Indent_Order> findStkIndOrds();
	
	@Query("select p from Stock_Indent_Item_Details p where p.modified_type = 'INSERTED' and p.indent_id = :indent_id ")
	List<Stock_Indent_Order> getStkIndentOrderDetailsList(@Param("indent_id") String indent_id);
	
	@Query("select p from Stock_Indent_docs p where p.modified_type = 'INSERTED' and p.indent_id = :indent_id ")
	List<Stock_Indent_Order> getStkIndentDocsList(@Param("indent_id") String indent_id);
	
	@Query( "select c from Stock_Indent_Order c where c.modified_type = 'INSERTED' ")
	List<Stock_Indent_Order> getStkIndOrder();
	
	@Query("select p from Stock_Indent_Terminations_dyn p where p.modified_type = 'INSERTED' and p.indent_id = :indent_id ")
	List<Stock_Indent_Order> getStkIndentTermDyn(@Param("indent_id") String indent_id);
	
	@Query("select p from Stock_Indent_Order p where p.modified_type = 'INSERTED' and p.indent_id = :indent_id ")
	Stock_Indent_Order getStkIndODR(@Param("indent_id") String indent_id);
}