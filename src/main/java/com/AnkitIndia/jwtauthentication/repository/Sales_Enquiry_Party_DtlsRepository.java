package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry_Party_Dtls;

public interface Sales_Enquiry_Party_DtlsRepository extends JpaRepository<Sales_Enquiry_Party_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Enquiry_Party_Dtls w SET w.modified_type ='UPDATED' WHERE w.enquiry_id = :enquiry_id")
    int sales_Enquiry_Party_Dtlsupdate(@Param("enquiry_id") String enquiry_id);
	
	@Query( "select s from Sales_Enquiry_Party_Dtls s where s.modified_type = 'INSERTED' and s.enquiry_id =:enquiry_id ORDER BY s.sl_no")
	List<Sales_Enquiry_Party_Dtls> getSalesEnqPartyDtls(@Param("enquiry_id") String enquiry_id);


}
