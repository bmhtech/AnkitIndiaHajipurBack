package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Tax_Info;

public interface Sales_Invoice_Tax_InfoRepository extends JpaRepository<Sales_Invoice_Tax_Info, Long>{
	
	@Query( "select s from Sales_Invoice_Tax_Info s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id ")
	Sales_Invoice_Tax_Info getSales_Invoice_Tax_Info(@Param("invoice_id") String invoice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice_Tax_Info w SET w.modified_type =:mstatus WHERE w.invoice_id = :invoice_id")
    int sales_Invoice_Tax_Infoupdate(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);

}
