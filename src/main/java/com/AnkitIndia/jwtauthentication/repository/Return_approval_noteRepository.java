package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Return_approval_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Return_approval_trans_info;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note;

public interface Return_approval_noteRepository extends  JpaRepository<Return_approval_note, Long>{

	@Query("SELECT MAX(SUBSTRING(salesreturnid , 4 , length(salesreturnid))) FROM Return_approval_note where salesreturnid like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select c from Return_approval_note c where c.modified_type = 'INSERTED' AND c.salesreturnid = :salesreturnid")
	Return_approval_note getReturnApprovalDtls(@Param("salesreturnid") String salesreturnid);
	
	@Query( "select c from Return_approval_note c where c.modified_type = 'INSERTED' AND c.company_id = :company AND c.fin_year =:finyear ORDER BY c.salesreturnid DESC")
	List<Return_approval_note> getReturnApprovalNote(@Param("company") String company,@Param("finyear") String finyear);
	
	@Query(value="select id,salesreturnno,salesreturntype,salesreturndate,businessunitname,partyname,returncriteria,returnbasedon,salesreturnid,unload_status from return_approval_note where modified_type = 'INSERTED' AND company_id = :company AND salesreturndate =:currentdate ORDER BY salesreturnid DESC", nativeQuery=true)
	List<Map<String, Object>> getReturnApprovalNoteList(@Param("company") String company,@Param("currentdate") String currentdate);
	
	/*@Query(value="SELECT r.id AS id,r.salesreturnno AS salesreturnno,r.salesreturntype AS salesreturntype,r.salesreturndate AS salesreturndate,r.businessunitname AS businessunitname,r.partyname AS partyname,r.returncriteria AS returncriteria,r.returnbasedon AS returnbasedon,r.salesreturnid AS salesreturnid,r.unload_status AS unload_status FROM return_approval_note r WHERE r.modified_type = 'INSERTED' AND r.salesreturndate >=:fromdate AND r.salesreturndate <=:todate AND r.party=:party1 ORDER BY salesreturnid DESC", nativeQuery=true)
	List<Map<String, Object>> searchReturnApprovalNote(@Param("fromdate") String fromdate,@Param("todate") String todate, @Param("party1") String party1);*/
	
	@Query(value="SELECT r.id AS id,r.salesreturnno AS salesreturnno,r.salesreturntype AS salesreturntype,r.salesreturndate AS salesreturndate,r.businessunitname AS businessunitname,r.partyname AS partyname,r.returncriteria AS returncriteria,r.returnbasedon AS returnbasedon,r.salesreturnid AS salesreturnid,r.unload_status AS unload_status FROM return_approval_note r WHERE r.modified_type = 'INSERTED' AND r.salesreturndate >=:fromdate AND r.salesreturndate <=:todate ORDER BY salesreturnid DESC", nativeQuery=true)
	List<Map<String, Object>> searchReturnApprovalNote(@Param("fromdate") String fromdate,@Param("todate") String todate);

	@Query( "select c from Return_approval_note c where c.modified_type = 'INSERTED' AND c.unload_status !='Done' AND c.businessunit =:bunit AND c.party =:party AND c.salesreturndate <=:uldate AND c.salesreturntype='Goods Return Due To Rejection' AND c.company_id = :company AND c.fin_year =:finyear ORDER BY c.salesreturnid DESC")
	List<Return_approval_note> getReturnAppNoteThruUnAdv(@Param("bunit") String bunit,@Param("uldate") String uldate,@Param("company") String company,@Param("finyear") String finyear,@Param("party") String party);
	
	@Query(value="select * from return_approval_note where  modified_type = 'INSERTED' and unload_status !='Done' and jobwork=1 and businessunit=:bunit and salesreturndate<=:advice_date and party=:party",nativeQuery=true)
	List<Map<String,Object>> getReturnAppNoteThruUnAdvjobwork(@Param("advice_date") String advice_date,@Param("bunit") String bunit,@Param("party") String party);
	
	//@Query( "select c from Return_approval_note c where c.modified_type = 'INSERTED' AND c.salesreturntype='Acceptance of Lower Rate' AND c.businessunit =:bunit AND c.party =:party AND c.company_id = :company AND c.fin_year =:finyear AND c.salesreturndate <=:invdate ORDER BY c.salesreturnid DESC")
	//@Query( "select c from Return_approval_note c where c.modified_type = 'INSERTED' AND c.salesreturntype='Acceptance of Lower Rate' AND c.businessunit =:bunit AND c.party =:party AND c.company_id = :company AND c.fin_year =:finyear AND c.salesreturndate <=:invdate AND c.credit_note_status = 'NO' ORDER BY c.salesreturnid DESC")
	@Query( "select c from Return_approval_note c where c.modified_type = 'INSERTED' AND c.salesreturntype='Acceptance of Lower Rate' AND c.businessunit =:bunit AND c.party =:party AND c.company_id = :company AND c.fin_year =:finyear AND c.salesreturndate <=:invdate AND c.credit_note_status = 'NO' AND c.inv_type=:invoicetype ORDER BY c.salesreturnid DESC")
	List<Return_approval_note> getRtnAppNoteLowRate(@Param("bunit") String bunit,@Param("party") String party,@Param("invdate") String invdate,@Param("company") String company,@Param("finyear") String finyear,@Param("invoicetype") String invoicetype);
	
	@Query( "select c from Return_approval_note c where c.modified_type = 'INSERTED' AND c.inv_type =:invtype AND c.businessunit =:bunit AND c.party =:party AND c.salesreturndate <=:srdate AND c.company_id = :company AND c.fin_year =:finyear AND c.reapp_note_status='1' AND c.weighment_status='2' AND c.srn_status !='Done' ")
	List<Return_approval_note> getReturnAppNoteThruWe(@Param("invtype") String invtype,@Param("bunit") String bunit,@Param("party") String party,@Param("srdate") String srdate,@Param("company") String company,@Param("finyear") String finyear);
	
	@Query( value="select * from return_approval_note c where c.modified_type = 'INSERTED'  AND c.businessunit =:bunit AND c.party =:party AND c.salesreturndate <=:date  AND c.reapp_note_status='1' AND c.weighment_status='2' AND c.srn_status !='Done' and c.jobwork=1 ",nativeQuery=true)
	List<Map<String,Object>> getReturnAppNoteThruWejobwork(@Param("date") String date,@Param("bunit") String bunit,@Param("party") String party);
	
	@Query("select COUNT(id) from Return_approval_note where fin_year =:fin_year and salesreturntype =:return_type")
	String countReturnNote(@Param("fin_year") String fin_year,@Param("return_type") String return_type);
	
	/*
	
	
	
*/	
	@Query( "select c from Return_approval_item_dtls c where c.modified_type = 'INSERTED' AND c.salesreturnid = :salesreturnid")
	List<Return_approval_item_dtls> getReutnitem(@Param("salesreturnid") String salesreturnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_note r SET r.reapp_note_status ='1',r.weighment_status='1',r.weighment_id =:wid WHERE r.salesreturnid = :salesreturnid")
    int updateStatus(@Param("salesreturnid") String salesreturnid,@Param("wid") String wid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_note r SET r.reapp_note_status ='1',r.weighment_status='2',r.weighment_id =:wid WHERE r.salesreturnid = :salesreturnid")
    int updateAppStatus(@Param("salesreturnid") String salesreturnid,@Param("wid") String wid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_note r SET r.srn_status ='Done' WHERE r.salesreturnid = :salesreturnid")
    int updateRtnAppStatus(@Param("salesreturnid") String salesreturnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_note r SET r.unload_status ='Done' WHERE r.salesreturnid = :salesreturnid")
    int updateUnloadStatus(@Param("salesreturnid") String salesreturnid);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_note r SET r.unload_status ='Not Done' WHERE r.salesreturnid = :salesreturnid")
    int updateUnloadStatusrevert(@Param("salesreturnid") String salesreturnid);
	
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_note r SET r.credit_note_id =:creditNoteId,r.credit_note_status ='YES' WHERE r.salesreturnid = :ref_id")
    int updatedUniqueApproveNoteToCreditNote(@Param("ref_id") String ref_id,@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_note r SET r.credit_note_id =0,r.credit_note_status ='NO' WHERE r.credit_note_id = :creditNoteId")
    int updatedUniqueApproveNoteToCreditNoteDelete(@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_note r SET r.srn_status ='Not Done' WHERE r.salesreturnid = :salesreturnid")
    int updateRtnAppStatusReverse(@Param("salesreturnid") String salesreturnid);
	
	@Query(value = "{call checkSalesReturnApprovalNoteUsage(:salesreturnid)}", nativeQuery = true)
	String salesReturnApprovalNoteUsage(@Param("salesreturnid") String salesreturnid);
	
	@Query("select s from Return_approval_note s where s.id =:id")
	Return_approval_note getReturnNoteDetails(@Param("id") Long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_broker_dtls r SET r.modified_type ='DELETED' WHERE r.salesreturnid = :salesreturnid")
    int return_approval_broker_dtlsupdate(@Param("salesreturnid") String salesreturnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_docs r SET r.modified_type ='DELETED' WHERE r.salesreturnid = :salesreturnid")
    int return_approval_docsupdate(@Param("salesreturnid") String salesreturnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_item_dtls r SET r.modified_type ='DELETED' WHERE r.salesreturnid = :salesreturnid")
    int return_approval_item_dtlsupdate(@Param("salesreturnid") String salesreturnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_party_dtls r SET r.modified_type ='DELETED' WHERE r.salesreturnid = :salesreturnid")
    int return_approval_party_dtlsupdate(@Param("salesreturnid") String salesreturnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_shipment_dtls r SET r.modified_type ='DELETED' WHERE r.salesreturnid = :salesreturnid")
    int return_approval_shipment_dtlsupdate(@Param("salesreturnid") String salesreturnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_trans_info r SET r.modified_type ='DELETED' WHERE r.salesreturnid = :salesreturnid")
    int return_approval_trans_infoupdate(@Param("salesreturnid") String salesreturnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Return_approval_weight_dtl r SET r.modified_type ='DELETED' WHERE r.salesreturnid = :salesreturnid")
    int return_approval_weight_dtlupdate(@Param("salesreturnid") String salesreturnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan d SET d.salereturn_id ='NA' , d.sales_returnstatus='NO' WHERE d.delivery_cid = :delivery_cid" )
    int updatedeliverychallanSalesreturnReverse(@Param("delivery_cid") String delivery_cid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan d SET d.salereturn_id ='NA' , d.sales_returnstatus='NO' WHERE d.salereturn_id = :salereturn_id and d.modified_type = 'INSERTED'" )
    int updatedeliverychallanSalesreturnReverseNew(@Param("salereturn_id") String salereturn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order s SET s.salereturn_id ='NA', s.sales_returnstatus='NO' WHERE s.order_id =:referenceid")
    int updatesalesorderSalesreturnReverse(@Param("referenceid") String referenceid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order s SET s.salereturn_id ='NA', s.sales_returnstatus='NO' WHERE s.salereturn_id =:salereturn_id")
    int updatesalesorderSalesreturnReverseNew(@Param("salereturn_id") String salereturn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice s SET s.salereturn_id ='NA' , s.sales_returnstatus='NO',s.return_approval_status ='NO' WHERE s.invoice_id =:invoice_id" )
    int updatesalesinvoiceSalesreturnReverse(@Param("invoice_id") String invoice_id);
	
	@Query( "select c from Return_approval_note c where c.modified_type = 'INSERTED' AND c.salesreturnid = :salesreturnid")
	Return_approval_note getRtnAppNoteLowRateUpdate(@Param("salesreturnid") String salesreturnid);
	
	@Query( "select c from Return_approval_note c where c.modified_type = 'INSERTED' AND c.salesreturnid = :salesreturnid")
	List<Return_approval_note> getReturnApprovalDtlslist(@Param("salesreturnid") String salesreturnid);
	
	@Query( "select c from Return_approval_trans_info c where c.modified_type = 'INSERTED' AND c.salesreturnid = :salesreturnid")
	Return_approval_trans_info getTallyvehicleno(@Param("salesreturnid") String salesreturnid);


}
