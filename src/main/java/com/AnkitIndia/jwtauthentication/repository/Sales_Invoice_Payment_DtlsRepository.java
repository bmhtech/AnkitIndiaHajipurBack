package com.AnkitIndia.jwtauthentication.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Payment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Shipment_Dtls;

public interface Sales_Invoice_Payment_DtlsRepository extends JpaRepository<Sales_Invoice_Payment_Dtls, Long>{
	
	@Query( "select s from Sales_Invoice_Payment_Dtls s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id ")
	Sales_Invoice_Payment_Dtls getSalesPayDtls(@Param("invoice_id") String invoice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice_Payment_Dtls w SET w.modified_type =:mstatus WHERE w.invoice_id = :invoice_id")
    int sales_Invoice_Payment_DtlsUpdate(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);
	
	@Query(value="SELECT * FROM sales_invoice_payment_dtls WHERE modified_type='INSERTED' And invoice_id=:invoice_id ",nativeQuery=true)
	Map<String,Object> getSalesInvPayDtls(@Param("invoice_id") String invoice_id);
}
