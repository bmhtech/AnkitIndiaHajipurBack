package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_BP_Dtls;

public interface Sales_Invoice_BP_DtlsRepository extends JpaRepository<Sales_Invoice_BP_Dtls, Long>{

	@Query( "select s from Sales_Invoice_BP_Dtls s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id ")
	Sales_Invoice_BP_Dtls getSales_Invoice_BP_Dtls(@Param("invoice_id") String invoice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice_BP_Dtls w SET w.modified_type =:mstatus WHERE w.invoice_id = :invoice_id")
    int sales_Invoice_BP_Dtlsupdate(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);

}
