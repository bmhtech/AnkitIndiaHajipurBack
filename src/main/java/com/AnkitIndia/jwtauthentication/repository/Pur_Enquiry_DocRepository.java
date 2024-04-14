package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry_Doc;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry_Service_Details;

public interface Pur_Enquiry_DocRepository extends JpaRepository<Pur_Enquiry_Doc, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Enquiry_Doc w SET w.modified_type ='UPDATED' WHERE w.enquiry_id = :enquiry_id")
    int pur_Enquiry_Docupdate(@Param("enquiry_id") String enquiry_id);
	
	
	@Query("SELECT p FROM Pur_Enquiry_Doc p WHERE p.modified_type ='INSERTED' AND p.enquiry_id =:Id  ")
	List<Pur_Enquiry_Doc> getPurEnquiryDocList(@Param("Id") String Id);

}
