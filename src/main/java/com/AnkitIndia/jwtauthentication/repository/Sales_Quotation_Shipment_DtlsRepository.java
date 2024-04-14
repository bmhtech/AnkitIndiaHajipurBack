package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Shipment_Dtls;

public interface Sales_Quotation_Shipment_DtlsRepository extends JpaRepository<Sales_Quotation_Shipment_Dtls, Long>{
	
	@Query( "select s from Sales_Quotation_Shipment_Dtls s where s.modified_type ='INSERTED' and s.quotation_id = :quot_id" )
	Sales_Quotation_Shipment_Dtls getSalesQuotShipDtls(@Param("quot_id") String quot_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Quotation_Shipment_Dtls w SET w.modified_type ='UPDATED' WHERE w.quotation_id = :quotation_id AND w.modified_type='INSERTED'")
    int sales_Quotation_Shipment_Dtlsupdate(@Param("quotation_id") String quotation_id);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Quotation_Shipment_Dtls w SET w.modified_type ='DELETED' WHERE w.quotation_id = :quotation_id AND w.modified_type='INSERTED'")
    int sales_Quotation_Shipment_DtlsDelete(@Param("quotation_id") String quotation_id);
	
}
