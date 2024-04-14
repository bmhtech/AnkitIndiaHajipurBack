package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Shipment_Dtls;

public interface Sales_Invoice_Shipment_DtlsRepository extends JpaRepository<Sales_Invoice_Shipment_Dtls, Long>{
	
	@Query( "select s from Sales_Invoice_Shipment_Dtls s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id ")
	Sales_Invoice_Shipment_Dtls getSalesShipDtls(@Param("invoice_id") String invoice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice_Shipment_Dtls w SET w.modified_type =:mstatus WHERE w.invoice_id = :invoice_id")
    int sales_Invoice_Shipment_DtlsUpdate(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);

}
