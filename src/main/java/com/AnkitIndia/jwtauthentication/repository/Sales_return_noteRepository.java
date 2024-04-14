package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_item_dtls;

public interface Sales_return_noteRepository extends JpaRepository<Sales_return_note, Long>{
	
	@Query("SELECT MAX(SUBSTRING(salesreturnnoteid , 4 , length(salesreturnnoteid))) FROM Sales_return_note where salesreturnnoteid like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select COUNT(id) from Sales_return_note where fin_year =:fin_year and inv_type =:inv_type")
	String countSalesReturn(@Param("fin_year") String fin_year,@Param("inv_type") String inv_type);
	
	@Query( "select c from Sales_return_note c where c.modified_type = 'INSERTED' AND c.salesreturnnoteid = :salesreturnnoteid")
	Sales_return_note getSalesReturnNoteDtls(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	//@Query( "select c from Sales_return_note c where c.modified_type = 'INSERTED' AND c.businessunit =:bunit AND c.company_id =:company AND c.fin_year =:finyear AND c.salesreturnnotedate <=:invdate AND c.party =:party ORDER BY c.salesreturnnoteid DESC")
	//@Query( "select c from Sales_return_note c where c.modified_type = 'INSERTED' AND c.businessunit =:bunit AND c.company_id =:company AND c.fin_year =:finyear AND c.salesreturnnotedate <=:invdate AND c.party =:party AND c.credit_note_status ='NO'  ORDER BY c.salesreturnnoteid DESC")
	@Query( "select c from Sales_return_note c where c.modified_type = 'INSERTED' AND c.businessunit =:bunit AND c.company_id =:company AND c.fin_year =:finyear AND c.salesreturnnotedate <=:invdate AND c.party =:party AND c.credit_note_status ='NO' AND c.inv_type=:invoicetype  ORDER BY c.salesreturnnoteid DESC")
	List<Sales_return_note> getSalesRtnNote(@Param("bunit") String bunit,@Param("company") String company,@Param("finyear") String finyear,@Param("invdate") String invdate,@Param("party") String party,@Param("invoicetype") String invoicetype);
	
	
	@Query(value="select * from sales_return_note where modified_type = 'INSERTED' AND businessunit =:bunit  and salesreturnnotedate <=:date and party =:party_id  and credit_note_status ='NO'",nativeQuery=true)
	List<Map<String,Object>> getSalesRtnNoteJw (@Param("date") String date,@Param("bunit") String bunit,@Param("party_id") String party_id);
	
	
	@Query(value="select * from sales_return_note_item_dtls_for_jobwork where modified_type = 'INSERTED' and salesreturnnoteid =:salereturn",nativeQuery=true)
	List<Map<String,Object>> getSalesRtnNoteJwdetails (@Param("salereturn") String salereturn);
	
	
	@Query(value="SELECT * FROM return_approval_item_dtls_for_jobwork_price  WHERE modified_type='INSERTED' AND  salesreturnid=:salereturn",nativeQuery=true)
	List<Map<String,Object>> getsalereturnjobworkprice (@Param("salereturn") String salereturn);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note w SET w.credit_note_id =:creditNoteId,w.credit_note_status ='YES' WHERE w.salesreturnnoteid = :ref_id")
    int updatedUniqueSalesNoteToCreditNote(@Param("ref_id") String ref_id,@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note w SET w.credit_note_id =0,w.credit_note_status ='NO' WHERE w.credit_note_id = :creditNoteId")
    int updatedUniqueSalesNoteToCreditNoteDeleted(@Param("creditNoteId") String creditNoteId);
	
	@Query( "select c from Sales_return_note_item_dtls c where c.modified_type = 'INSERTED' AND c.salesreturnnoteid = :salesreturnnoteid")
	List<Sales_return_note_item_dtls> getSalesItemDetails(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Query("select s from Sales_return_note s where s.id =:id")
	Sales_return_note getSalesReturnNoteDetails(@Param("id") Long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note_broker_dtls r SET r.modified_type ='DELETED' WHERE r.salesreturnnoteid = :salesreturnnoteid")
    int sales_return_note_broker_dtlsupdate(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note_docs r SET r.modified_type ='DELETED' WHERE r.salesreturnnoteid = :salesreturnnoteid")
    int sales_return_note_docsupdate(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note_item_dtls r SET r.modified_type ='DELETED' WHERE r.salesreturnnoteid = :salesreturnnoteid")
    int sales_return_note_item_dtlsupdate(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note_party_dtls r SET r.modified_type ='DELETED' WHERE r.salesreturnnoteid = :salesreturnnoteid")
    int sales_return_note_party_dtlsupdate(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note_shipment_dtls r SET r.modified_type ='DELETED' WHERE r.salesreturnnoteid = :salesreturnnoteid")
    int sales_return_note_shipment_dtlsupdate(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note_trans_info r SET r.modified_type ='DELETED' WHERE r.salesreturnnoteid = :salesreturnnoteid")
    int sales_return_note_trans_infoupdate(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_return_note_weight_dtl r SET r.modified_type ='DELETED' WHERE r.salesreturnnoteid = :salesreturnnoteid")
    int sales_return_note_weight_dtlupdate(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Query(value = "{call checkSalesReturnNoteUsage(:salesreturnnoteid)}", nativeQuery = true)
	String salesReturnNoteUsage(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Query( "select c from Sales_return_note_item_dtls c where c.modified_type = 'INSERTED' AND c.salesreturnnoteid = :salesreturnnoteid")
	List<Sales_return_note_item_dtls> getsalesreturnitemdetals(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Query( "select c from Sales_return_note c where c.modified_type = 'INSERTED' AND c.salesreturnnoteid =:salesreturnnoteid ")
	Sales_return_note getSalesRtnNoteUpdate(@Param("salesreturnnoteid") String salesreturnnoteid);
	
	@Query(value="SELECT * FROM sales_return_note r WHERE r.modified_type = 'INSERTED' AND r.salesreturnnotedate >=:fromdate AND r.salesreturnnotedate <=:todate AND r.party=:party1 ORDER BY salesreturnnoteid DESC", nativeQuery=true)
	List<Map<String, Object>> searchSalesReturnNote(@Param("fromdate") String fromdate,@Param("todate") String todate, @Param("party1") String party1);

	@Query(value="select * from sales_return_note where modified_type = 'INSERTED' AND company_id = :company AND salesreturnnotedate =:currentdate ORDER BY salesreturnnoteid DESC", nativeQuery=true)
	List<Map<String, Object>> getSalesReturnNote(@Param("company") String company,@Param("currentdate") String currentdate);

	
}
