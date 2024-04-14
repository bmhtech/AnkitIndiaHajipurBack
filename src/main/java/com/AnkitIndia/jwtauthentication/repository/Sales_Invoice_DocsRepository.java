package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Docs;

public interface Sales_Invoice_DocsRepository extends JpaRepository<Sales_Invoice_Docs, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice_Docs w SET w.modified_type =:mstatus WHERE w.invoice_id = :invoice_id")
    int sales_Invoice_Docsupdate(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);
	
	@Query( "select s from Sales_Invoice_Docs s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id ")
	List<Sales_Invoice_Docs> getSalesInvDocs(@Param("invoice_id") String invoice_id);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice_Docs p SET p.modified_type ='DELETED' WHERE p.id =:id and p.modified_type = 'INSERTED'")
    int updatepdfdelete(@Param("id") long id);
	
	@Query(value= "SELECT d.doc_pdf as doc_pdf,d.doc_file_name as doc_file_name from Sales_Invoice_Docs d where d.invoice_no=:refno and d.doctype='Transport' and d.modified_type = 'INSERTED'", nativeQuery=true)
	Map<String,Object> getTransportimage1(@Param("refno") String refno);

}
