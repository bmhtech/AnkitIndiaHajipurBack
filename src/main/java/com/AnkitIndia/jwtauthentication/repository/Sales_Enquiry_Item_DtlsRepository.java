package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry_Item_Dtls;

public interface Sales_Enquiry_Item_DtlsRepository extends JpaRepository<Sales_Enquiry_Item_Dtls, Long>{
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Enquiry_Item_Dtls w SET w.modified_type ='UPDATED' WHERE w.enquiry_id = :enquiry_id")
    int sales_Enquiry_Item_Dtlsupdate(@Param("enquiry_id") String enquiry_id);
	
	@Query( "select s from Sales_Enquiry_Item_Dtls s where s.modified_type = 'INSERTED' and s.enquiry_id =:enquiry_id ORDER BY s.slno")
	List<Sales_Enquiry_Item_Dtls> getSalesEnqItemDtls(@Param("enquiry_id") String enquiry_id);

}
