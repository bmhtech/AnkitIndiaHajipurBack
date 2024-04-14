package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_groupwise_taxsumm;

public interface Item_groupwise_taxsummRepository extends JpaRepository<Item_groupwise_taxsumm, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_groupwise_taxsumm i SET i.modified_type =:mstatus WHERE i.invoice_id =:invoice_id")
    int updateItem_groupwise_taxsumm(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);
	
	 @Query( value="SELECT id,company_id,deleted_by,deleted_on,fin_year,inserted_by,inserted_on,modified_type,updated_by,updated_on,username,SUM(cgst) as cgst,cgstledgerid,SUM(igst) as igst,igstledgerid,invoice_id,percentage,SUM(sgst) AS sgst,sgstledgerid,SUM(tax_amt) AS tax_amt,tax_code,tax_rate,SUM(taxable_amt) AS  taxable_amt FROM item_groupwise_taxsumm i WHERE i.invoice_id =:invoice_id AND i.modified_type ='INSERTED' GROUP BY invoice_id",nativeQuery=true)
	 List<Map<String,Object>> getupdateItem_groupwise_taxsumm(@Param("invoice_id") String invoice_id);
	 
	 @Query("select i from Item_groupwise_taxsumm i WHERE i.invoice_id =:invoice_id and i.modified_type ='INSERTED' and i.taxable_amt!=0")
	 Item_groupwise_taxsumm getItem_groupwise_taxsummbyinvoiceid(@Param("invoice_id") String invoice_id);
	 
}
