package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls_for_jobwork_price;

public interface Sales_Invoice_Item_Dtls_for_jobwork_priceRepository extends JpaRepository<Sales_Invoice_Item_Dtls_for_jobwork_price, Long>{
	
	
	 @Query( value="select id,company_id,deleted_by,deleted_on,fin_year,inserted_by,inserted_on,modified_type,updated_by,updated_on,username,cgst_amt,cgst_tax,invoice_id,invoice_no,item_service,item_service_name as item_name,job_price,sac_code,sgst_amt,sgst_tax,tax_value as amount,tot_amount from sales_invoice_item_dtls_for_jobwork_price s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id",nativeQuery=true)
	 List<Map<String,Object>> retriveinvoicejobworkprice(@Param("invoice_id") String invoice_id);
	 
	 
	 @Query( "select s from Sales_Invoice_Item_Dtls_for_jobwork_price s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id ")
		List<Sales_Invoice_Item_Dtls_for_jobwork_price> getSalesInvItmjobpriceDtls(@Param("invoice_id") String invoice_id);
		
	@Query( "select s from Sales_Invoice_Item_Dtls_for_jobwork_price s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id")
	Sales_Invoice_Item_Dtls_for_jobwork_price getSalesInvItmjobpriceDtlstally(@Param("invoice_id") String invoice_id);

}
