package com.AnkitIndia.jwtauthentication.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_Business_Partner_details;

public interface Pur_good_receipt_Business_Partner_detailsRepository extends JpaRepository<Pur_good_receipt_Business_Partner_details, Long>{

	@Query( "select p from Pur_good_receipt_Business_Partner_details p where p.grn_id = :grnid and p.modified_type ='INSERTED'" )
	Pur_good_receipt_Business_Partner_details getPurGoodRcptBPDtls(@Param("grnid") String grnid);
	
	@Query(value= "SELECT p.* FROM pur_good_receipt_business_partner_details p WHERE p.grn_id =:grnid AND p.modified_type ='INSERTED'",nativeQuery = true)
	Map<String, Object> grnBPDtlsRetriveListFast(@Param("grnid") String grnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt_Business_Partner_details w SET w.modified_type ='UPDATED' WHERE w.grn_id = :grn_id")
    int updatePur_good_receipt_Business_Partner_details(@Param("grn_id") String grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt_Business_Partner_details w SET w.modified_type ='DELETED' WHERE w.grn_id = :grn_id")
    int pur_good_receipt_Business_Partner_detailsUpdate(@Param("grn_id") String grn_id);
}
