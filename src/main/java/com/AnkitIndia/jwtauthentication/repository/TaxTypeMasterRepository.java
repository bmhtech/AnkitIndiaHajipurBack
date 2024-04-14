package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Gst_input_output_ledger_dtls;
import com.AnkitIndia.jwtauthentication.model.Tax_type_master;

public interface TaxTypeMasterRepository extends JpaRepository<Tax_type_master, Long>{
	
	@Query("SELECT MAX(SUBSTRING(taxtype_code , 4 , length(taxtype_code))) FROM Tax_type_master where taxtype_code like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
//	@Query( "select t from Tax_type_master t where t.taxtype_active = :sts and t.modified_type != 'DELETED' ")
	@Query( "select t from Tax_type_master t where  t.modified_type != 'DELETED' ")
	List<Tax_type_master> getTaxTypeNameCode(@Param("sts") Boolean sts);
	
	@Query("select taxtype_code,taxtype_name from Tax_type_master")
	List<Tax_type_master> getTaxTypeNCList();
	
	@Query( "select t from Gst_input_output_ledger_dtls t where  t.modified_type = 'INSERTED' and t.taxtype_code=:taxtypecode ")
	Gst_input_output_ledger_dtls getgstdetailsoftaxtype(@Param("taxtypecode") String taxtypecode);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Gst_input_output_ledger_dtls w SET w.modified_type ='UPDATED' WHERE w.taxtype_code = :taxcode")
    int gst_input_output_ledger_dtlsupdate(@Param("taxcode") String taxcode);
	
	@Query( "select t from Gst_input_output_ledger_dtls t where  t.modified_type = 'INSERTED' and t.taxtype_code=:taxtypecode ")
	List<Gst_input_output_ledger_dtls> retriveTaxTypeGst(@Param("taxtypecode") String taxtypecode);
	
	@Query( "select t from Tax_type_master t where  t.modified_type != 'DELETED' and t.taxtype_code=:code ")
	Tax_type_master gettaxtypename(@Param("code") String code);
}
