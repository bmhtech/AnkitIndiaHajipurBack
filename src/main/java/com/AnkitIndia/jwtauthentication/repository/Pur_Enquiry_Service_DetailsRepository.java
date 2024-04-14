package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry_Service_Details;

public interface Pur_Enquiry_Service_DetailsRepository extends JpaRepository<Pur_Enquiry_Service_Details, Long>{
	
		//@Query("select p from Pur_Enquiry e JOIN e.pur_Enquiry_Service_Details p WHERE p.enquiry_id = :Id and p.quotation_status !='1' and p.modified_type != 'DELETED' ")
		//@Query(value ="SELECT * FROM Pur_Enquiry e JOIN Pur_Enquiry_Service_Details p ON e.enquiry_id=p.enquiry_id WHERE p.enquiry_id =:Id AND p.quotation_status !='1' AND p.modified_type != 'DELETED' ",nativeQuery = true)
		@Query("SELECT p FROM Pur_Enquiry_Service_Details p WHERE p.modified_type = 'INSERTED' AND p.enquiry_id =:Id AND p.quotation_status !='1' ORDER BY p.sl_no")
		List<Pur_Enquiry_Service_Details> getPurEnquiryCNQUPList(@Param("Id") String Id);
		
		//@Query("select p from Pur_Enquiry_Service_Details p where p.quotation_status !='1' and p.modified_type != 'DELETED' ")
		//@Query(value ="SELECT * FROM Pur_Enquiry e JOIN Pur_Enquiry_Service_Details p ON e.enquiry_id=p.enquiry_id WHERE p.quotation_status !='1' AND p.modified_type != 'DELETED' ",nativeQuery = true)
		@Query("SELECT p FROM Pur_Enquiry_Service_Details p WHERE p.quotation_status !='1' AND p.modified_type = 'INSERTED' ORDER BY p.sl_no")
		List<Pur_Enquiry_Service_Details> getPurEnqItemDtlsList();
		

		@Modifying(clearAutomatically = true)
	    @Query("UPDATE Pur_Enquiry_Service_Details w SET w.modified_type ='UPDATED' WHERE w.enquiry_id = :enquiry_id")
	    int pur_Enquiry_Service_Detailsupdate(@Param("enquiry_id") String enquiry_id);

		@Query("SELECT p FROM Pur_Enquiry_Service_Details p WHERE p.modified_type ='INSERTED' AND p.enquiry_id =:Id ORDER BY p.sl_no ")
		List<Pur_Enquiry_Service_Details> getPurEnquiryItemDtlsList(@Param("Id") String Id);


}
