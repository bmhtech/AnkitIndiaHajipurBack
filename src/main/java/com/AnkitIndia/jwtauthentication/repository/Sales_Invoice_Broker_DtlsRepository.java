package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Broker_Dtls;

public interface Sales_Invoice_Broker_DtlsRepository extends JpaRepository<Sales_Invoice_Broker_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice_Broker_Dtls w SET w.modified_type =:mstatus WHERE w.invoice_id = :invoice_id")
    int sales_Invoice_Broker_Dtlsupdate(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);
	
	@Query( "select s from Sales_Invoice_Broker_Dtls s where s.modified_type ='INSERTED' and s.invoice_id =:invoice_id ")
	List<Sales_Invoice_Broker_Dtls> getSalesInvBrkDtls(@Param("invoice_id") String invoice_id);

	@Query( "select s from Sales_Invoice_Broker_Dtls s where s.modified_type ='INSERTED' and s.invoice_id =:invoice_id ")
	Sales_Invoice_Broker_Dtls getSalesInvBrkDtlsposting(@Param("invoice_id") String invoice_id);
}
