package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry_Docs;

public interface Sales_Enquiry_DocsRepository extends JpaRepository<Sales_Enquiry_Docs, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Enquiry_Docs w SET w.modified_type ='UPDATED' WHERE w.enquiry_id = :enquiry_id")
    int sales_Enquiry_Docsupdate(@Param("enquiry_id") String enquiry_id);
	
	@Query( "select s from Sales_Enquiry_Docs s where s.modified_type = 'INSERTED' and s.enquiry_id =:enquiry_id ORDER BY s.id")
	List<Sales_Enquiry_Docs> getSalesEnqDoc(@Param("enquiry_id") String enquiry_id);

}
