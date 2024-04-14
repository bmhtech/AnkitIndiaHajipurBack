package com.AnkitIndia.jwtauthentication.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Tax_code_details;

public interface Tax_code_detailsRepository extends JpaRepository<Tax_code_details, Long>{
	
	@Query("select max(substring(tax_id , 3, length(tax_id))) from Tax_code_details where tax_id like %:prefix%  AND modified_type ='INSERTED' ")
	String getMaxTaxCodes(@Param("prefix") String prefix);
	
	@Query("select t from Tax_code_details t where t.tax_id =:taxid and t.modified_type ='INSERTED'")
	Tax_code_details getTaxCodeDetails(@Param("taxid") String taxid);
	
	
	@Query("select t from Tax_code_details t where t.tax_name =:tax_name and t.modified_type ='INSERTED'")
	Tax_code_details getTaxCodeDetailstax_name(@Param("tax_name") String tax_name);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Tax_code_details w SET w.modified_type =:mstatus WHERE w.tax_code =:tax_code")
    int updateTax_code_details(@Param("tax_code") String tax_code,@Param("mstatus") String mstatus);
	
	@Query(value= "SELECT cgst_act_val,sgst_act_val,tax_id,tax_name,tax_rate,igst_act_val,tax_code from tax_code_details d where d.tax_id=:taxcode and d.modified_type = 'INSERTED'", nativeQuery=true)
	Map<String,Object> getServiceItemTax(@Param("taxcode") String taxcode);

}
