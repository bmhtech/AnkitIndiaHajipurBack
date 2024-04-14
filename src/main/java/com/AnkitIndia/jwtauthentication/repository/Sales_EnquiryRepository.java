package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry;

public interface Sales_EnquiryRepository extends JpaRepository<Sales_Enquiry , Long> {
	
	@Query("select COUNT(id) from Sales_Enquiry")
	String countId();

	@Query( "select s from Sales_Enquiry s where s.modified_type = 'INSERTED'")
	List<Sales_Enquiry> salesEnquiryList();
	
	@Query( "select s from Sales_Enquiry s where s.modified_type = 'INSERTED' and s.enq_status='Open' AND enq_date <=:qdate ")
	List<Sales_Enquiry> getSalesEnquiriesOpen(@Param("qdate") String qdate);
	
	@Query( "select s from Sales_Enquiry s where s.modified_type = 'INSERTED' ORDER BY s.enquiry_id DESC ")
	List<Sales_Enquiry> findSalesEnquirys();
	
	@Query( "select s from Sales_Enquiry s where s.modified_type = 'INSERTED' and s.enquiry_id =:enquiry_id ")
	Sales_Enquiry salesEnquiryByEnqId(@Param("enquiry_id") String enquiry_id);
	
	@Query( "select s from Sales_Enquiry s where s.modified_type = 'INSERTED' and s.enquiry_id =:enquiry_id  ")
	Sales_Enquiry salesEnqPersonList(@Param("enquiry_id") String enquiry_id);
	
	@Query("select COUNT(id) from Sales_Enquiry where enq_date =:orderdate and enq_type =:type ")
	String countEnquiryOrder(@Param("orderdate") String orderdate,@Param("type") String type);
	
}
