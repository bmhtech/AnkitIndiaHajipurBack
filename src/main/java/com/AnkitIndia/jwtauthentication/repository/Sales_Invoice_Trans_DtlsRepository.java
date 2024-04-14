package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_BP_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Trans_Dtls;

public interface Sales_Invoice_Trans_DtlsRepository extends JpaRepository<Sales_Invoice_Trans_Dtls, Long>{
	
	@Query( "select s from Sales_Invoice_Trans_Dtls s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id ")
	List<Sales_Invoice_Trans_Dtls> getSalesTransDtls(@Param("invoice_id") String invoice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice_Trans_Dtls w SET w.modified_type =:mstatus WHERE w.invoice_id = :invoice_id")
    int sales_Invoice_Trans_DtlsUpdate(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);
	
}
