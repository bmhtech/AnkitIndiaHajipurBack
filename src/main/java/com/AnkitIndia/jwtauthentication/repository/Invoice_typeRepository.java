package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Invoice_type;

public interface Invoice_typeRepository extends JpaRepository<Invoice_type, Long>{

	@Query("SELECT MAX(SUBSTRING(invtype_id , 4 , length(invtype_id))) FROM Invoice_type where invtype_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select i from Invoice_type i where i.modified_type != 'DELETED'")
	List<Invoice_type> getSalesInvTypes();
	
	@Query("select i from Invoice_type i where i.invtype_id =:inv_type and i.modified_type != 'DELETED'")
	Invoice_type getSalesInvTypesDtls(@Param("inv_type") String inv_type);
	
	@Query("select max(substring(invtype_code , 8, length(invtype_code))) from Invoice_type where invtype_code like %:code% and company_id =:company ")
	String getInvtypeSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Query(value="select * from invoice_type where modified_type = 'INSERTED'",nativeQuery=true)
	 List<Map<String,Object>> getSalesInvTypesFast();
	
}
