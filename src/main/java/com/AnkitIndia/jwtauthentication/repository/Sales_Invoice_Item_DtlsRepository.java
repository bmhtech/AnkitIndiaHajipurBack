package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls;

public interface Sales_Invoice_Item_DtlsRepository extends JpaRepository<Sales_Invoice_Item_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice_Item_Dtls w SET w.modified_type =:mstatus WHERE w.invoice_id = :invoice_id")
    int sales_Invoice_Item_Dtlsupdate(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);
	
	@Query( "select s from Sales_Invoice_Item_Dtls s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id ORDER BY s.slno")
	List<Sales_Invoice_Item_Dtls> getSalesInvItmDtls(@Param("invoice_id") String invoice_id);
	
	//@Query( value="SELECT s.*,i.alt_name FROM sales_invoice_item_dtls s LEFT JOIN item_master i ON s.item_code=i.item_id WHERE s.modified_type = 'INSERTED' AND i.modified_type='INSERTED' AND s.invoice_id =:invoice_id ORDER BY s.slno", nativeQuery = true)
	@Query( value="SELECT s.*,CASE WHEN a.party='CBP00006' THEN '10019910' ELSE s.hsn_code END AS hsnm_code,i.alt_name FROM sales_invoice_item_dtls s LEFT JOIN item_master i ON s.item_code=i.item_id\r\n" //hsn code hard core for itc
			+ "LEFT JOIN sales_invoice a ON s.`invoice_id`=a.`invoice_id` WHERE s.modified_type = 'INSERTED' AND i.modified_type='INSERTED' AND a.modified_type='INSERTED' AND s.invoice_id =:invoice_id ORDER BY s.slno", nativeQuery = true)
	List<Map<String,Object>> getSalesInvItmDtlswtAltName(@Param("invoice_id") String invoice_id);
	
	@Query( "select s from Sales_Invoice_Item_Dtls s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id  and s.item_code =:item_code and s.packing=:packing ")
	Sales_Invoice_Item_Dtls getSalesInvItmDtlsitem(@Param("invoice_id") String invoice_id,@Param("item_code") String item_code,@Param("packing") String packing);
}
