package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry_BPartner_Details;

public interface Pur_Enquiry_BPartner_DetailsRepository extends JpaRepository<Pur_Enquiry_BPartner_Details, Long> {

	@Query( "SELECT d FROM Pur_Enquiry_BPartner_Details d WHERE d.modified_type = 'INSERTED' AND d.bp_code =:sid ")
	List<Pur_Enquiry_BPartner_Details> getPurEnquiryBPList(@Param("sid") String sid);
	

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Enquiry_BPartner_Details w SET w.modified_type ='UPDATED' WHERE w.enquiry_id = :enquiry_id")
    int pur_Enquiry_BPartner_Detailsupdate(@Param("enquiry_id") String enquiry_id);

	@Query( "SELECT d FROM Pur_Enquiry_BPartner_Details d WHERE d.modified_type = 'INSERTED' AND d.enquiry_id =:enq_id ")
	List<Pur_Enquiry_BPartner_Details> getPurEnquiryBPDetails(@Param("enq_id") String enq_id);
	
}
