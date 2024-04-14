package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Tax_code_details;
import com.AnkitIndia.jwtauthentication.model.Tax_code_master;

public interface Tax_code_masterRepository extends JpaRepository<Tax_code_master, Long> {
	
	@Query("SELECT MAX(SUBSTRING(tax_id , 3 , length(tax_id))) FROM Tax_code_master where tax_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select t from Tax_code_master t where t.modified_type = 'INSERTED' ")
	List<Tax_code_master> getTaxCodes();
	
	@Query( "select t from Tax_code_master t where t.modified_type = 'INSERTED' and t.company_id =:company ")
	List<Tax_code_master> getTaxNameCode(@Param("company") String company);
	//Delete
	@Query( "select t from Tax_code_master t where t.modified_type = 'INSERTED' ")
	List<Tax_code_master> getTaxNameCode();
	
	@Query( "select t.tax_code from Tax_code_master t where t.modified_type = 'INSERTED' ")
	List<Tax_code_master> getTaxCodeList();
	
	@Query( "select t from Tax_code_master t where t.tax_code = :taxcode")
	Tax_code_master getTaxNameByTaxCode(@Param("taxcode") String taxcode);
	
	@Query( "select t from Tax_code_details t where t.modified_type = 'INSERTED'")
	List<Tax_code_master> getTaxNameRate();
	
	@Query( "select t from Tax_code_details t where t.tax_id = :taxcode and t.modified_type = 'INSERTED'")
	Tax_code_details getTaxNameByTaxCodesaleorder(@Param("taxcode") String taxcode);
	
	
	@Query( "select t from Tax_code_details t where t.tax_code = :tax_code and t.modified_type = 'INSERTED' ORDER BY t.srno")
	//List<Tax_code_master> taxCodeDtlsRetriveList(@Param("tax_code") String tax_code);
	List<Tax_code_details> taxCodeDtlsRetriveList(@Param("tax_code") String tax_code);
	
	
	@Query("select max(substring(tax_code , 8, length(tax_code))) from Tax_code_master where tax_code like %:code% and company_id =:company ")
	String getTaxCodeSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Tax_code_details p SET p.modified_type='DELETED' WHERE p.tax_code = :tax_code" )
    int updatedynamicdetails(@Param("tax_code") String tax_code);
}
