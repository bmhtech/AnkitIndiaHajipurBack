package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Credit_item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls_for_jobwork_price;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_item_dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_item_dtls_for_jobwork_price;

public interface Sales_credit_noteRepository extends JpaRepository<Sales_credit_note, Long>{
	
	@Query("SELECT MAX(SUBSTRING(creditnoteid , 4, length(creditnoteid))) from Sales_credit_note where creditnoteid like %:ncode% ")
	Optional<String> countId(@Param("ncode") String ncode);
	
	@Query("select COUNT(id) from Sales_credit_note where fin_year =:fin_year and invoice_type =:cntype and creditnotetype=:cnotetype")
	String countSalesCredit(@Param("fin_year") String fin_year,@Param("cntype") String cntype,@Param("cnotetype") String cnotetype);
	
	@Query("select COUNT(id) from Sales_credit_note where fin_year =:fin_year and invoice_type =:cntype and creditnotetype=:cnotetype")
	String countSalesCreditacceptance(@Param("fin_year") String fin_year,@Param("cntype") String cntype,@Param("cnotetype") String cnotetype);
	
	@Query("select s from Sales_credit_note s where s.id =:id")
	Sales_credit_note getCreditNoteDetails(@Param("id") Long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_item_dtls r SET r.modified_type ='DELETED' WHERE r.creditnoteid = :creditNoteId")
    int sales_credit_note_item_dtlsupdate(@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_bp_dtls r SET r.modified_type ='DELETED' WHERE r.creditnoteid = :creditNoteId")
    int sales_credit_note_bp_dtlsupdate(@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_broker_dtls r SET r.modified_type ='DELETED' WHERE r.creditnoteid = :creditNoteId")
    int sales_credit_note_broker_dtlsupdate(@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_docs r SET r.modified_type ='DELETED' WHERE r.creditnoteid = :creditNoteId")
    int sales_credit_note_docsupdate(@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_payment_dtls r SET r.modified_type ='DELETED' WHERE r.creditnoteid = :creditNoteId")
    int sales_credit_note_payment_dtlsupdate(@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_shipment_dtls r SET r.modified_type ='DELETED' WHERE r.creditnoteid = :creditNoteId")
    int sales_credit_note_shipment_dtlsupdate(@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_tax_info r SET r.modified_type ='DELETED' WHERE r.creditnoteid = :creditNoteId")
    int sales_credit_note_tax_infoupdate(@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_trans_dtls r SET r.modified_type ='DELETED' WHERE r.creditnoteid = :creditNoteId")
    int sales_credit_note_trans_dtlsupdate(@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Credit_item_groupwise_summ r SET r.modified_type ='DELETED' WHERE r.creditnoteid = :creditNoteId")
    int credit_item_groupwise_summupdate(@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Credit_item_groupwise_taxsumm r SET r.modified_type ='DELETED' WHERE r.creditnoteid = :creditNoteId")
    int credit_item_groupwise_taxsummupdate(@Param("creditNoteId") String creditNoteId);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Credit_item_groupwise_hsnsumm r SET r.modified_type ='DELETED' WHERE r.creditnoteid = :creditNoteId")
    int credit_item_groupwise_hsnsummupdate(@Param("creditNoteId") String creditNoteId);
	
	@Query("select s from Sales_credit_note_item_dtls s where s.creditnoteid =:creditnoteid and s.modified_type ='INSERTED'")
	List<Sales_credit_note_item_dtls> getcreditnote_itemdetails(@Param("creditnoteid") String creditnoteid);
	
	@Query("select s from Credit_item_groupwise_taxsumm s where s.creditnoteid =:creditnoteid and s.modified_type ='INSERTED'")
	Credit_item_groupwise_taxsumm getgstdetails(@Param("creditnoteid") String creditnoteid);
	
	@Query("select s from Sales_credit_note s where s.creditnoteid =:creditnoteid and s.modified_type ='INSERTED'")
	Sales_credit_note getCreditNoteDetailsthcerdit(@Param("creditnoteid") String creditnoteid);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Sales_credit_note p SET p.export=:export , p.response=:response WHERE p.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Sales_credit_note p SET p.export=0 , p.response='NA',undo_by=:user,undo_on=:curtime WHERE p.id = :id" )
	int exportuomtallyReverse(@Param("id") long id,@Param("user") String user,@Param("curtime") LocalDateTime curtime);
	
	
	
	@Query(value= "SELECT s.id AS id,s.creditnoteno AS invoiceno,LENGTH(s.e_invoice_no) AS einvoicelength,s.party AS party,s1.gst_no AS gst_no FROM sales_credit_note s, cust_bussiness_partner_statutory s1 WHERE invoice_typename='GST INVOICE' AND s.modified_type='INSERTED' AND s.party=s1.cp_id AND s1.modified_type='INSERTED' AND s1.gst_no!='' ORDER BY s.id DESC LIMIT 1",nativeQuery=true)
	List<Map<String, Object>> geteinvoicestatus_saleinv();
	
	//@Query(value= "SELECT s.id AS id,s.creditnoteno AS invoiceno,LENGTH(s.e_invoice_no) AS einvoicelength,s.party AS party,s1.gst_no AS gst_no FROM sales_credit_note s, cust_bussiness_partner_statutory s1 WHERE invoice_typename='GST INVOICE' AND s.modified_type='INSERTED' AND s.party=s1.cp_id AND s1.modified_type='INSERTED' AND s1.gst_no!='' ORDER BY s.id DESC LIMIT 2",nativeQuery=true)
	
	@Query(value= "SELECT s.id AS id,s.creditnoteno AS invoiceno,LENGTH(s.e_invoice_no) AS einvoicelength,s.party AS party,s1.gst_no AS gst_no FROM sales_credit_note s, cust_bussiness_partner_statutory s1 WHERE invoice_typename='GST INVOICE' AND s.modified_type='INSERTED' AND s.party=s1.cp_id AND s1.modified_type='INSERTED' AND s1.gst_no!=''   AND s.id <(SELECT id FROM sales_credit_note WHERE creditnoteno=:invoiceno) ORDER BY s.id DESC LIMIT 0,1",nativeQuery=true)
	List<Map<String, Object>> geteinvoicestatus_saleinvupdate(@Param("invoiceno") String invoiceno);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_item_dtls_for_jobwork w SET w.modified_type =:mstatus WHERE w.creditnoteid =:creditnoteid and w.modified_type='INSERTED'")
    int creditNoteJobItemupdate(@Param("creditnoteid") String creditnoteid,@Param("mstatus") String mstatus);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note_item_dtls_for_jobwork_price w SET w.modified_type =:mstatus WHERE w.creditnoteid =:creditnoteid and w.modified_type='INSERTED'")
    int creditNoteServiceItemupdate(@Param("creditnoteid") String creditnoteid,@Param("mstatus") String mstatus);
	
	@Query(value= "SELECT * from sales_credit_note where company_id =:compid and modified_type ='INSERTED'",nativeQuery=true)
	List<Map<String, Object>> getSalesCreditNoteFast(@Param("compid") String compid);
	
	@Query(value= "SELECT * from sales_credit_note_item_dtls_for_jobwork where modified_type = 'INSERTED' and creditnoteid =:creditnoteid",nativeQuery=true)
	List<Map<String, Object>> getSalesCreditNoteJobwork(@Param("creditnoteid") String creditnoteid);
	
	@Query(value= "SELECT id,company_id,deleted_by,deleted_on,fin_year,inserted_by,inserted_on,modified_type,updated_by,updated_on,username,cgst_amt,cgst_tax,creditnoteid,creditnoteno,item_service,item_service_name as item_name,item_service_acc_name,job_price,sac_code,sgst_amt,sgst_tax,tax_value as amount,tot_amount,taxcode,igst_tax,igst_amt from sales_credit_note_item_dtls_for_jobwork_price where modified_type = 'INSERTED' and creditnoteid =:creditnoteid",nativeQuery=true)
	List<Map<String, Object>> getSalesCreditNoteJobworkPrice(@Param("creditnoteid") String creditnoteid);
	
	@Query("SELECT s FROM Sales_credit_note_item_dtls_for_jobwork s WHERE s.modified_type='INSERTED' And s.creditnoteid=:creditnoteid")
	Sales_credit_note_item_dtls_for_jobwork getInvoiceJobItemDtlstally(@Param("creditnoteid") String creditnoteid);
	
	@Query( "select s from Sales_credit_note_item_dtls_for_jobwork_price s where s.modified_type = 'INSERTED' and s.creditnoteid =:creditnoteid")
	Sales_credit_note_item_dtls_for_jobwork_price getSalesInvItmjobpriceDtlstally(@Param("creditnoteid") String creditnoteid);
	
	@Query(value= "SELECT c.creditnotetype,c.creditnoteno,c.creditnotedate,c.invoice_typename,c.partyname,c.challan,c.salesorderno,c.salesorderdate,c.refchallanno,c.refchallandate,cj.job_item_name AS item_name,(SELECT item_group_name FROM item_master WHERE item_id= cj.job_item AND modified_type='INSERTED') AS item_groupname,cj.job_packing_name AS packing_name,cj.job_hsn AS hsn_code,cj.pack_qty AS squantity,cj.pack_uom AS suom,cj.item_qty AS quantity,cj.item_uom AS uom,cj.item_qty AS mat_wt,cp.job_price AS price,cp.tax_value AS amount,cp.taxcode_name AS tax_code,cp.igst_tax AS tax_rate,(cp.tot_amount-cp.tax_value) AS tax_amt,cp.tot_amount AS total_amt,ctx.gstno,(SELECT NAME FROM broker_master WHERE modified_type = 'INSERTED' AND broker_id =cb.brokercode) AS brokercode,(SELECT vehicle_no FROM vehicle_master WHERE modified_type = 'INSERTED' AND vehicle_id =ctr.vehicleno ) AS vehicleno,c.e_invoice_no,ts.cgst as cgst,ts.sgst as sgst,ts.igst as igst,cj.price_based_on as price_based_on,0 AS discount_amt,(SELECT state FROM cust_bussiness_partner_address WHERE cp_id=c.party AND modified_type='INSERTED') AS state,c.roundoff_amt as roundoff_amt  FROM sales_credit_note c,sales_credit_note_item_dtls_for_jobwork cj,sales_credit_note_item_dtls_for_jobwork_price cp,sales_credit_note_broker_dtls cb,sales_credit_note_trans_dtls ctr,sales_credit_note_tax_info ctx,credit_item_groupwise_taxsumm ts WHERE c.creditnoteid=cj.creditnoteid AND c.creditnoteid=cp.creditnoteid AND c.creditnoteid=cb.creditnoteid AND c.creditnoteid=ctr.creditnoteid AND c.creditnoteid=ts.creditnoteid AND c.creditnoteid=ctx.creditnoteid AND c.modified_type='INSERTED' AND cj.modified_type='INSERTED' AND cp.modified_type='INSERTED' AND cb.modified_type='INSERTED' AND ctr.modified_type='INSERTED' AND ctx.modified_type='INSERTED' AND ts.modified_type='INSERTED' AND c.creditnotedate>=:fromdate AND c.creditnotedate<=:todate AND c.invoice_type=:invoicetype",nativeQuery=true)
	List<Map<String, Object>> getSalesCreditNoteJobWorkReport(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("invoicetype") String invoicetype);
	
	@Query(value= "SELECT c.creditnotetype,c.creditnoteno,c.creditnotedate,c.invoice_typename,c.partyname,c.challan,c.salesorderno,c.salesorderdate,c.refchallanno,c.refchallandate,ci.item_name,ci.item_groupname,ci.packing_name,ci.hsn_code,ci.squantity,ci.suom,ci.quantity,ci.uom,ci.mat_wt,ci.price,ci.price_based_on,ci.amount,ci.discount_amt,(SELECT tax_name FROM tax_code_details WHERE modified_type = 'INSERTED' AND tax_id =ci.tax_code) AS tax_code,ci.tax_rate,ci.tax_amt,ci.total_amt,ctx.gstno,(SELECT NAME FROM broker_master WHERE modified_type = 'INSERTED' AND broker_id =cb.brokercode) AS brokercode,(SELECT vehicle_no FROM vehicle_master WHERE modified_type = 'INSERTED' AND vehicle_id =ctr.vehicleno ) AS vehicleno,c.e_invoice_no,ci.cgstamt as cgst,ci.sgstamt as sgst,ci.igstamt as igst,(SELECT state FROM cust_bussiness_partner_address WHERE cp_id=c.party AND modified_type='INSERTED') AS state,c.roundoff_amt as roundoff_amt FROM sales_credit_note c,sales_credit_note_item_dtls ci,sales_credit_note_broker_dtls cb,sales_credit_note_trans_dtls ctr,sales_credit_note_tax_info ctx,credit_item_groupwise_taxsumm ts WHERE c.creditnoteid=ci.creditnoteid AND c.creditnoteid=cb.creditnoteid AND c.creditnoteid=ctr.creditnoteid AND c.creditnoteid=ctx.creditnoteid and c.creditnoteid=ts.creditnoteid AND c.modified_type='INSERTED' AND ci.modified_type='INSERTED' AND cb.modified_type='INSERTED' AND ctr.modified_type='INSERTED' AND ctx.modified_type='INSERTED' AND ts.modified_type='INSERTED' AND c.creditnotedate>=:fromdate AND c.creditnotedate<=:todate AND c.invoice_type=:invoicetype",nativeQuery=true)
	List<Map<String, Object>> getSalesCreditNoteReport(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("invoicetype") String invoicetype);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note SET e_invoice_no=:einvno,create_einvoice=1 WHERE id =:id and modified_type='INSERTED'")
    int updateEinvoice(@Param("einvno") String einvno,@Param("id") long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note SET cencel_einvoice=1 WHERE id =:id and modified_type='INSERTED'")
    int updateEinvoiceCancel(@Param("id") long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note SET create_ewaybill=1,waybill=:ewaybill WHERE id =:id and modified_type='INSERTED'")
    int updateEwaybill(@Param("id") long id,@Param("ewaybill") String ewaybill);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note SET cencel_ewaybill=1 WHERE id =:id and modified_type='INSERTED'")
    int updateWaybillCancel(@Param("id") long id);
	
	 @Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_credit_note SET create_ewaybill_wo_invoice=1,waybill=:ewaybill WHERE id =:id and modified_type='INSERTED'")
    int updateEwaybillWOInv(@Param("id") long id,@Param("ewaybill") String ewaybill);
    
}
