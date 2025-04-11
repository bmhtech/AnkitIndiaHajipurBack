package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Process_master_doc;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls_for_jobwork_price;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Summary_dyn;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;

public interface Sales_InvoiceRepository extends JpaRepository<Sales_Invoice, Long>{
	
	/*@Query("select COUNT(id) from Sales_Invoice")
	String countId();*/
	@Query("SELECT MAX(SUBSTRING(invoice_id , 3, length(invoice_id))) from Sales_Invoice where invoice_id like %:icode% ")
	String countId(@Param("icode") String icode);
	
	@Query( "select s from Sales_Invoice s where s.id =:id ")
	Sales_Invoice findSalesInvoiceDtls(@Param("id") Long id);
	
	@Query( "select s from Sales_Invoice s where s.modified_type = 'INSERTED' and s.company_id =:company ORDER BY s.invoice_id DESC")
	List<Sales_Invoice> findSalesInvoices(@Param("company") String company);
	
	//@Query( "select s from Sales_Invoice s where s.modified_type = 'INSERTED' and s.party =:party and s.company_id =:comp and s.invoice_date <=:invdate")
	@Query( "select s from Sales_Invoice s where s.modified_type = 'INSERTED' and s.party =:party and s.company_id =:comp and s.return_approval_status='NO' and s.sales_returnstatus='NO' and s.invoice_date <=:invdate")
	List<Sales_Invoice> getSalesInvoices(@Param("party") String party,@Param("invdate") String invdate,@Param("comp") String comp);
	
	@Query( "select s from Sales_Invoice s where s.modified_type = 'INSERTED' and s.party =:party and s.company_id =:comp and s.return_approval_status!='YES' and s.sales_returnstatus='NO' and s.invoice_date <=:invdate")
	List<Sales_Invoice> getSalesInvoicesnew(@Param("party") String party,@Param("invdate") String invdate,@Param("comp") String comp);
	
	@Query(value= "SELECT sales_invoice_id from salereturnapproval where party=:party and invoice_date<=:invdate and difference!=0", nativeQuery=true)
	List<String> getSalesInvoiceReturn(@Param("party") String party,@Param("invdate") String invdate);
	
	@Query(value="SELECT invoice_id,invoice_no,invoice_date,party,partyname from sales_invoice where invoice_id=:invid and modified_type = 'INSERTED'", nativeQuery=true)
	Map<String,Object> getSalesInvoiceReturnList(@Param("invid") String delvid);
	
	
	@Query( "select s from Sales_Invoice s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id ORDER BY s.invoice_id DESC")
	Sales_Invoice getSalesInvDetails(@Param("invoice_id") String invoice_id);
	
	@Query("select COUNT(id) from Sales_Invoice where fin_year =:fin_year and invoice_type =:inv_type")
	String countSalesInvoice(@Param("fin_year") String fin_year,@Param("inv_type") String inv_type);
	
	
	@Query( "select s from Sales_Invoice s where s.modified_type = 'INSERTED' and s.reference_id =:reference_id ")
	Sales_Invoice getSalesInvDetailsthroughdelivery(@Param("reference_id") String reference_id);

	
	
	/*
	
	
	
	*/
	
	@Procedure(name = "Sales_Invoice.saveReceiptAccount")
	int saveReceiptAccount(@Param("branch") String branch,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,
			@Param("referenceno") String referenceno,@Param("referencedate") String referencedate,@Param("ledgerid") String ledgerid,
			@Param("amount") double amount,@Param("drawnon") String drawnon,@Param("modified_type") String modified_type,@Param("inserted_on") LocalDateTime inserted_on,
			@Param("inserted_by") String inserted_by,@Param("fin_year") String fin_year,@Param("username") String username,@Param("narration") String narration,
			@Param("narration_dtls") String narration_dtls,@Param("uniquevoucher") String uniquevoucher,@Param("subgrps") String subgrps,@Param("groupcode") String groupcode);
	
	@Procedure(name = "Sales_Invoice.callRcptAccInsertPlus")
	int callRcptAccInsertPlus(@Param("branch") String branch,@Param("entrydate") String entrydate,@Param("voucherno") String voucherno,
			@Param("ledgerid") String ledgerid,@Param("amount") double amount,@Param("narration") String narration,
			@Param("inserted_on") LocalDateTime inserted_on,@Param("inserted_by") String inserted_by,@Param("fin_year") String fin_year,
			@Param("subgrps") String subgrps,@Param("groupcode") String groupcode,@Param("gstcal") String gstcal,
			@Param("percentage") double percentage);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice s SET s.payment_status =:pstatus WHERE s.invoice_id =:invoice_id" )
    int updateInvPaymentStatus(@Param("invoice_id") String invoice_id,@Param("pstatus") boolean pstatus);
	
	
	
	@Query( "select s from Sales_Order_Summary_dyn s where s.modified_type = 'INSERTED' and s.order_id =:order_id ")
	List<Sales_Order_Summary_dyn> getappcharges(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice s SET s.salereturn_id =:pstatus , s.sales_returnstatus='YES',s.return_approval_status='YES' WHERE s.invoice_id =:invoice_id and s.modified_type = 'INSERTED' " )
    int updatesales_invoice_salesreturn(@Param("invoice_id") String invoice_id,@Param("pstatus") String pstatus);
	
	@Modifying(clearAutomatically = true)
   // @Query("UPDATE Sales_Invoice s SET s.return_approval_status='YES' WHERE s.reference_id =:refid and s.modified_type = 'INSERTED'" )
	 @Query("UPDATE Sales_Invoice s SET s.return_approval_status='YES',s.salereturn_id =:approve_id , s.sales_returnstatus='YES' WHERE s.reference_id =:refid and s.modified_type = 'INSERTED'" )
    //int updateInvoiceStaus(@Param("refid") String refid);
	int updateInvoiceStaus(@Param("refid") String refid,@Param("approve_id") String approve_id);
	
	@Modifying(clearAutomatically = true)	  
    @Query("UPDATE Sales_Invoice s SET s.return_approval_status='YES',s.salereturn_id =:approve_id , s.sales_returnstatus='YES' WHERE s.invoice_id =:invoice_id and s.modified_type = 'INSERTED'" )
    int updateInvoiceStausonmultiple(@Param("invoice_id") String invoice_id,@Param("approve_id") String approve_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice s SET s.return_approval_status='NO' WHERE s.reference_id =:refid and s.modified_type = 'INSERTED'" )
    int updateInvoiceStausReverse(@Param("refid") String refid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice s SET s.return_approval_status='NO',s.sales_returnstatus='NO',s.salereturn_id='NA' WHERE s.salereturn_id =:salereturn_id and s.modified_type = 'INSERTED'" )
    int updateInvoiceStausReverseNew(@Param("salereturn_id") String salereturn_id);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 1 THEN true ELSE false END FROM Sales_Invoice pl where pl.modified_type != 'DELETED' and pl.reference_id=:reference_id")
	Boolean checkmultipledileverychallanininvoice(@Param("reference_id") String reference_id);
	
	@Query( "select s from Sales_Invoice s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id")
	Sales_Invoice getSingleSalesInvoice(@Param("invoice_id") String invoice_id);
	
	@Query( "select s from Item_groupwise_taxsumm s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id")
	Item_groupwise_taxsumm getgstdetails(@Param("invoice_id") String invoice_id);
	
	@Query( "select s from Item_groupwise_taxsumm s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id and s.taxable_amt!=0")
	Item_groupwise_taxsumm getgstdetailsnew(@Param("invoice_id") String invoice_id);
	
	
	@Query( "select s from Item_groupwise_taxsumm s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id")
	List<Item_groupwise_taxsumm> getgstdetails_dynamic(@Param("invoice_id") String invoice_id);
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Sales_Invoice p SET p.export=:export , p.response=:response WHERE p.id = :id" )
	int exportuomtally(@Param("id") long id,@Param("response") String response,@Param("export") int export);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Sales_Invoice p SET p.export=0 , p.response='NA',undo_by=:user,undo_on=:curtime WHERE p.id = :id" )
	int exportuomtallyReverse(@Param("id") long id,@Param("user") String user,@Param("curtime") LocalDateTime curtime);
	
	@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Sales_Invoice> getsearchdata(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("customername") String customername,@Param("finyear") String finyear);

	@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	//@Query(value = "{call getSearchsaleinvoiceData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Map<String,Object>> getsearchdataFast(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("customername") String customername,@Param("finyear") String finyear);

	@Query("select s from Sales_Invoice s where s.modified_type = 'INSERTED' and s.invoice_date=:currDate ")
	List<Sales_Invoice> getSalesInvoiceDataList(@Param("currDate") String currDate);
	
	@Query(value="SELECT s.*,CASE WHEN (s.invoice_type='INV00002' AND s.grand_total>=50000 AND LENGTH(waybill)>0 AND s.e_invoice_no='' AND s.create_ewaybill_wo_invoice=0) \r\n"
			+ "OR (s.invoice_type='INV00004' AND s.grand_total>=50000 AND LENGTH(waybill)>0 AND s.e_invoice_no='' AND s.create_ewaybill_wo_invoice=0 AND IF( (SELECT COUNT(DISTINCT item_name)  FROM sales_invoice_item_dtls WHERE invoice_id=s.invoice_id AND item_name NOT LIKE '%BRAN%')>0,'1','0')>0)\r\n"
			+ "OR (s.invoice_type='INV00001' AND s.grand_total>=100000 AND s.create_ewaybill_wo_invoice=0 AND s.state='BIHAR' AND IF( (SELECT COUNT(DISTINCT item_name)  FROM sales_invoice_item_dtls WHERE invoice_id=s.invoice_id AND item_name NOT LIKE '%BRAN%')>0,'1','0')>0)\r\n"
			+ "OR (s.invoice_type='INV00001' AND s.create_ewaybill_wo_invoice=0 AND s.state!='BIHAR' AND IF( (SELECT COUNT(DISTINCT item_name)  FROM sales_invoice_item_dtls WHERE invoice_id=s.invoice_id AND item_name NOT LIKE '%BRAN%')>0,'1','0')>0) THEN 1 ELSE 0 END AS avail_ewaybill_woeinv\r\n"
			+ "FROM sales_invoice s WHERE s.modified_type='INSERTED' AND s.invoice_date=:currdate AND s.fin_year=:finyear", nativeQuery=true)
	List<Map<String,Object>> getSalesInvoiceDataListFast(@Param("currdate") String currdate,@Param("finyear") String finyear);
	
	
	//@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount FROM sales_invoice_item_dtls s1,sales_invoice s2 WHERE s1.invoice_id = s2.invoice_id AND s2.party=:customer AND S2.invoice_date>=:fromdate AND S2.invoice_date<=:todate AND S2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name,s1.price ORDER BY s1.item_name", nativeQuery=true)
	@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount FROM sales_invoice_item_dtls s1,sales_invoice s2 WHERE s1.invoice_id = s2.invoice_id AND s2.party IN (:customer) AND s2.invoice_date>=:fromdate AND s2.invoice_date<=:todate AND s2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name ORDER BY s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesInvoiceSummaryPartywise(@Param("customer") List<String> customer,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit);
	
	//@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount FROM sales_invoice_item_dtls s1,sales_invoice s2 WHERE s1.invoice_id = s2.invoice_id AND s1.item_code IN (:item) AND S2.invoice_date>=:fromdate AND S2.invoice_date<=:todate AND S2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name,s1.price ORDER BY s1.item_name", nativeQuery=true)
	@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount FROM sales_invoice_item_dtls s1,sales_invoice s2 WHERE s1.invoice_id = s2.invoice_id AND s1.item_code IN (:item) AND s2.invoice_date>=:fromdate AND s2.invoice_date<=:todate AND s2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name ORDER BY s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesInvoiceSummaryItemwise(@Param("item") List<String> item,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit);
	
	//@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount FROM sales_invoice_item_dtls s1,sales_invoice_broker_dtls s2,sales_invoice s3 WHERE s1.invoice_id = s2.invoice_id AND s2.invoice_id= s3.invoice_id AND s2.brokercode=:broker AND S3.invoice_date>=:fromdate AND S3.invoice_date<=:todate AND S3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name,s1.price ORDER BY s1.item_name", nativeQuery=true)
	@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount FROM sales_invoice_item_dtls s1,sales_invoice_broker_dtls s2,sales_invoice s3 WHERE s1.invoice_id = s2.invoice_id AND s2.invoice_id= s3.invoice_id AND s2.brokercode IN (:broker) AND s3.invoice_date>=:fromdate AND s3.invoice_date<=:todate AND s3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name ORDER BY s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesInvoiceSummaryBrokerwise(@Param("broker") List<String> broker,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit);
	
	//@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount,s2.partyname AS partyname,s2.invoice_date AS invoice_date,s3.broker_name as broker_name FROM sales_invoice_item_dtls s1,sales_invoice s2,sales_invoice_broker_dtls s3 WHERE s1.invoice_id = s2.invoice_id AND s1.invoice_id = s3.invoice_id AND s2.invoice_id = s3.invoice_id AND s2.invoice_date>=:fromdate AND S2.invoice_date<=:todate AND S2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' AND s2.party=:customer GROUP BY s2.party,s1.packing_name,s1.item_name,s1.price", nativeQuery=true)
	@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount,s2.partyname AS partyname,s2.invoice_date AS invoice_date,s3.broker_name as broker_name FROM sales_invoice_item_dtls s1,sales_invoice s2,sales_invoice_broker_dtls s3 WHERE s1.invoice_id = s2.invoice_id AND s1.invoice_id = s3.invoice_id AND s2.invoice_id = s3.invoice_id AND s2.invoice_date>=:fromdate AND s2.invoice_date<=:todate AND s2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' AND s2.party IN (:customer) GROUP BY s2.party,s1.packing_name,s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesInvoiceMiscPartyList(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit,@Param("customer") List<String> customer);
	
	//@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount,s2.broker_name AS broker_name,s3.invoice_date AS invoice_date,s3.partyname AS partyname FROM sales_invoice_item_dtls s1,sales_invoice_broker_dtls s2,sales_invoice s3 WHERE s1.invoice_id = s2.invoice_id AND s1.invoice_id = s3.invoice_id AND s2.invoice_id = s3.invoice_id AND S3.invoice_date>=:fromdate AND S3.invoice_date<=:todate AND S3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' AND s2.brokercode=:broker GROUP BY s2.brokercode,s1.packing_name,s1.item_name,s1.price", nativeQuery=true)
	@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount,s2.broker_name AS broker_name,s3.invoice_date AS invoice_date,s3.partyname AS partyname FROM sales_invoice_item_dtls s1,sales_invoice_broker_dtls s2,sales_invoice s3 WHERE s1.invoice_id = s2.invoice_id AND s1.invoice_id = s3.invoice_id AND s2.invoice_id = s3.invoice_id AND s3.invoice_date>=:fromdate AND s3.invoice_date<=:todate AND s3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' AND s2.brokercode IN (:broker) GROUP BY s2.brokercode,s1.packing_name,s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesInvoiceMiscBrokerList(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit,@Param("broker") List<String> broker);
	
	//@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount,s2.partyname AS partyname,S2.invoice_date AS invoice_date,s3.broker_name AS broker_name FROM sales_invoice_item_dtls s1,sales_invoice s2,sales_invoice_broker_dtls s3 WHERE s1.invoice_id = s2.invoice_id AND s1.invoice_id = s3.invoice_id AND s2.invoice_id = s3.invoice_id AND S2.invoice_date>=:fromdate AND S2.invoice_date<=:todate AND S2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' GROUP BY s2.party,s3.brokercode,s1.packing_name,s1.item_name,s1.price", nativeQuery=true)
	@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount,s2.partyname AS partyname,s2.invoice_date AS invoice_date,s3.broker_name AS broker_name FROM sales_invoice_item_dtls s1,sales_invoice s2,sales_invoice_broker_dtls s3 WHERE s1.invoice_id = s2.invoice_id AND s1.invoice_id = s3.invoice_id AND s2.invoice_id = s3.invoice_id AND s2.invoice_date>=:fromdate AND s2.invoice_date<=:todate AND s2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' GROUP BY s2.party,s3.brokercode,s1.packing_name,s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesInvoiceMiscAllList(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit);
	
	@Query(value= "SELECT DISTINCT i.item_code AS item_code,i.item_name AS item_name FROM sales_invoice s,sales_invoice_item_dtls i WHERE s.modified_type='INSERTED' AND i.modified_type='INSERTED' AND s.modified_type=i.modified_type AND s.invoice_id =i.invoice_id AND s.company_id=:company AND s.invoice_date>=:fromdate AND s.invoice_date<=:todate and s.business_unit=:business_unit AND i.item_code IS NOT NULL", nativeQuery=true)
	List<Map<String, Object>> salesInvoiceFinishedItemlist(@Param("company") String company,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("business_unit") String business_unit);
	
	@Query(value= "SELECT DISTINCT i.brokercode AS brokercode,i.broker_name AS broker_name FROM sales_invoice s,sales_invoice_broker_dtls i WHERE s.modified_type='INSERTED' AND i.modified_type='INSERTED' AND s.modified_type=i.modified_type AND s.invoice_id =i.invoice_id AND s.company_id=:company AND s.invoice_date>=:fromdate AND s.invoice_date<=:todate and s.business_unit=:business_unit AND i.brokercode IS NOT NULL", nativeQuery=true)
	List<Map<String, Object>> salesInvoiceBrokerlist(@Param("company") String company,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("business_unit") String business_unit);
	
	@Query(value= "SELECT DISTINCT i.party AS party,i.partyname AS partyname FROM sales_invoice i WHERE i.modified_type='INSERTED' AND i.company_id=:company AND i.invoice_date>=:fromdate AND i.invoice_date<=:todate and i.business_unit=:business_unit", nativeQuery=true)
	List<Map<String, Object>> salesInvoicePartylist(@Param("company") String company,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("business_unit") String business_unit);
	
	
	@Query(value= "SELECT s.id AS id,s.invoiceno AS invoiceno,LENGTH(s.e_invoice_no) AS einvoicelength,s.party AS party,s1.gst_no AS gst_no FROM sales_invoice s, cust_bussiness_partner_statutory s1 WHERE invoice_type_name='GST INVOICE' AND s.modified_type='INSERTED' AND s.party=s1.cp_id AND s1.modified_type='INSERTED' AND s1.gst_no!='' ORDER BY s.id DESC LIMIT 1",nativeQuery=true)
	List<Map<String, Object>> geteinvoicestatus_saleinv();
	
	//@Query(value= "SELECT s.id AS id,s.invoiceno AS invoiceno,LENGTH(s.e_invoice_no) AS einvoicelength,s.party AS party,s1.gst_no AS gst_no FROM sales_invoice s, cust_bussiness_partner_statutory s1 WHERE invoice_type_name='GST INVOICE' AND s.modified_type='INSERTED' AND s.party=s1.cp_id AND s1.modified_type='INSERTED' AND s1.gst_no!='' ORDER BY s.id DESC LIMIT 1,1",nativeQuery=true)
	
	//@Query(value= "SELECT s.id AS id,s.invoiceno AS invoiceno,LENGTH(s.e_invoice_no) AS einvoicelength,s.party AS party,s1.gst_no AS gst_no FROM sales_invoice s, cust_bussiness_partner_statutory s1 WHERE invoice_type_name='GST INVOICE' AND s.modified_type='INSERTED' AND s.party=s1.cp_id AND s1.modified_type='INSERTED' AND s1.gst_no!='' AND s.id <(SELECT id FROM sales_invoice WHERE invoice_no=:invoiceno) ORDER BY s.id DESC LIMIT 0,1",nativeQuery=true)
	@Query(value="SELECT s.id AS id,s.invoiceno AS invoiceno,LENGTH(s.e_invoice_no) AS einvoicelength,s.party AS party,s1.gst_no AS gst_no, IFNULL(i.irn_no, 'NA') AS irn_no FROM sales_invoice s LEFT JOIN cust_bussiness_partner_statutory s1 ON s.party=s1.cp_id LEFT JOIN sale_invoice_einvoice_gen i ON s.`invoice_id`=i.invoice_id WHERE s.`modified_type`='INSERTED' AND s1.`modified_type`='INSERTED' AND s.invoice_type_name='GST INVOICE' AND s1.gst_no!='' AND s.id <(SELECT id FROM sales_invoice WHERE invoice_no=:invoiceno) ORDER BY s.id DESC LIMIT 0,1", nativeQuery=true)
	List<Map<String, Object>> geteinvoicestatus_saleinvupdate(@Param("invoiceno") String invoiceno);
	
	@Query(value="SELECT * FROM sales_invoice WHERE modified_type='INSERTED' And reference_id=:challanid ",nativeQuery=true)
	List<Map<String,Object>> getInvoiceReportThroughChallan(@Param("challanid") String challanid);
	
	@Query(value="SELECT * FROM sales_invoice_docs WHERE modified_type='INSERTED' And doc_pdf=:doc_pdf ",nativeQuery=true)
	List<Map<String,Object>> getdocumentListwithfileSalesInvoice(@Param("doc_pdf") String doc_pdf);
	

	//@Query(value="SELECT s.invoiceno,s.invoice_date,d.`challan_no`,d.`challan_date`,b.`broker_name`,t.`vehicleno`,s.`partyname`,sd.`paytodtls`,c.`district`,c.`state`,st.`gst_no`,CASE WHEN ad.`mobile` = 0 THEN '' ELSE ad.`mobile` END AS mobile,s.roundoff_amt FROM sales_invoice s,delivery_challan d ,sales_invoice_broker_dtls b,sales_invoice_trans_dtls t,`sales_invoice_shipment_dtls` sd,`cust_bussiness_partner_address` c,`cust_bussiness_partner_statutory` st,`cust_bussiness_partner_address_dtls` ad WHERE s.id=:id AND d.`delivery_cid`=s.`reference_id` AND s.`modified_type`=d.`modified_type` AND s.`modified_type`=b.`modified_type` AND s.`modified_type`=t.`modified_type` AND s.`modified_type`=sd.`modified_type` AND s.`modified_type`=c.`modified_type` AND s.`modified_type`=st.`modified_type` AND s.`modified_type`=ad.`modified_type` AND s.`invoice_id`=b.`invoice_id` AND t.`invoice_id`=b.`invoice_id` AND sd.`invoice_id`=b.`invoice_id` AND s.`party`=c.`cp_id` AND  s.`party`=st.`cp_id` AND  s.`party`=ad.`cp_id`",nativeQuery=true)
	@Query(value="SELECT s.tax_total,s.invoiceno,s.invoice_date,s.cust_refdocno,s.refchallanno,s.refchallandate,s.cust_ref_doc_date,s.remarks,s.e_invoice_no,CASE WHEN s.waybill IS NULL THEN '' ELSE s.waybill END AS waybill,s.invoice_type,d.`challan_no`,d.`challan_date`,b.`broker_name`,t.`vehicleno`,s.`partyname`,sd.`paytodtls`,c.add1,c.add2,c.add3,c.`district`,c.`state`,c.pincode,st.`gst_no`,CASE WHEN ad.`mobile` = 0 THEN '' ELSE ad.`mobile` END AS mobile,s.roundoff_amt,s.salesorderno,s.asn_no,s.create_ewaybill_wo_invoice FROM sales_invoice s,delivery_challan d ,sales_invoice_broker_dtls b,sales_invoice_trans_dtls t,`sales_invoice_shipment_dtls` sd,`cust_bussiness_partner_address` c,`cust_bussiness_partner_statutory` st,`cust_bussiness_partner_address_dtls` ad WHERE s.id=:id AND d.`delivery_cid`=s.`reference_id` AND s.`modified_type`=d.`modified_type` AND s.`modified_type`=b.`modified_type` AND s.`modified_type`=t.`modified_type` AND s.`modified_type`=sd.`modified_type` AND s.`modified_type`=c.`modified_type` AND s.`modified_type`=st.`modified_type` AND s.`modified_type`=ad.`modified_type` AND s.`invoice_id`=b.`invoice_id` AND t.`invoice_id`=b.`invoice_id` AND sd.`invoice_id`=b.`invoice_id` AND s.`party`=c.`cp_id` AND  s.`party`=st.`cp_id` AND  s.`party`=ad.`cp_id`",nativeQuery=true)
	Map<String,Object> getJobWorkInvPrint(@Param("id") Long id);

	@Query(value="SELECT * FROM sales_invoice_item_dtls_for_jobwork WHERE modified_type='INSERTED' And invoice_id=:invoice_id ORDER BY sl_no ASC",nativeQuery=true)
	List<Map<String,Object>> getInvoiceJobItemDtls(@Param("invoice_id") String invoice_id);
	
	@Query("SELECT s FROM Sales_Invoice_Item_Dtls_for_jobwork s WHERE s.modified_type='INSERTED' And s.invoice_id=:invoice_id")
	Sales_Invoice_Item_Dtls_for_jobwork getInvoiceJobItemDtlstally(@Param("invoice_id") String invoice_id);
	
	@Query(value="SELECT * FROM sales_invoice_item_dtls_for_jobwork_price WHERE modified_type='INSERTED' And invoice_id=:invoice_id ",nativeQuery=true)
	List<Map<String,Object>> getInvoiceTServiceItem(@Param("invoice_id") String invoice_id);
	
	@Query(value="SELECT * FROM sale_invoice_einvoice_gen WHERE modified_type='INSERTED' And invoice_id=:invoice_id ",nativeQuery=true)
	Map<String,Object> geteinvoicedetails(@Param("invoice_id") String invoice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice_Item_Dtls_for_jobwork w SET w.modified_type =:mstatus WHERE w.invoice_id =:invoice_id and w.modified_type='INSERTED'")
    int invoiceJobItemupdate(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice_Item_Dtls_for_jobwork_price w SET w.modified_type =:mstatus WHERE w.invoice_id =:invoice_id and w.modified_type='INSERTED'")
    int invoiceServiceItemupdate(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);
	
	@Query(value= "SELECT * FROM sales_invoice_item_dtls_for_jobwork_price WHERE invoice_id=(SELECT sales_invoice_id FROM salereturnapprovaljobwork WHERE delivery_cid=:challanid and modified_type='INSERTED')", nativeQuery=true)
	List<Map<String, Object>> getDelvChallanJobworkPrice(@Param("challanid") String challanid);
	
	@Query(value= "SELECT i.* FROM sales_invoice_item_dtls_for_jobwork_price i,sales_invoice j WHERE i.invoice_id=j.invoice_id and i.modified_type='INSERTED' and j.modified_type='INSERTED' AND j.reference_id IN (:challanid)", nativeQuery=true)
	List<Map<String, Object>> getDelvChallanMultiJobworkPrice(@Param("challanid") List<String> challanid);
	
	//@Query(value= "SELECT s.invoice_date,s.invoice_no,s.partyname,s.cust_ref_doc_date,s.cust_refdocno,t.vehicleno,j.job_item_name,j.job_packing_name,j.pack_qty,j.item_qty,j.item_uom,jp.job_price AS rate,jp.item_service_name,(SELECT ac_ledger_name FROM item_service_master WHERE service_id= jp.item_service AND modified_type='INSERTED') AS sal_ledger,jp.sac_code,jp.tax_value,(SELECT tax_name FROM tax_code_details WHERE tax_id=jp.taxcode AND modified_type='INSERTED') AS taxname,jp.cgst_tax,jp.cgst_amt,jp.sgst_tax,jp.sgst_amt,jp.igst_tax,jp.igst_amt,jp.tot_amount,b.broker_name,s.salesorderdate,s.salesorderno,d.own_gross,d.own_tare,d.own_net FROM sales_invoice s,sales_invoice_trans_dtls t,sales_invoice_item_dtls_for_jobwork j,sales_invoice_item_dtls_for_jobwork_price jp,delivery_challan_weight_dtl d,sales_invoice_broker_dtls b WHERE t.invoice_id=s.invoice_id AND j.invoice_id=s.invoice_id AND jp.invoice_id=s.invoice_id AND b.invoice_id=s.invoice_id AND d.delivery_cid =s.reference_id AND s.modified_type='INSERTED' AND t.modified_type=s.modified_type AND j.modified_type=s.modified_type AND jp.modified_type=s.modified_type AND d.modified_type=s.modified_type AND b.modified_type=s.modified_type AND s.invoice_date>=:fromdate AND s.invoice_date<=:todate", nativeQuery=true)
	//@Query(value= "SELECT s.invoice_date,s.invoice_no,s.partyname,s.cust_ref_doc_date,s.cust_refdocno,t.vehicleno,j.job_item_name,j.job_packing_name,j.pack_qty,j.item_qty,j.item_uom,jp.job_price AS rate,jp.item_service_name,(SELECT ac_ledger_name FROM item_service_master WHERE service_id= jp.item_service AND modified_type='INSERTED') AS sal_ledger,jp.sac_code,jp.tax_value,(SELECT tax_name FROM tax_code_details WHERE tax_id=jp.taxcode AND modified_type='INSERTED') AS taxname,jp.cgst_tax,jp.cgst_amt,jp.sgst_tax,jp.sgst_amt,jp.igst_tax,jp.igst_amt,jp.tot_amount,b.broker_name,s.salesorderdate,s.salesorderno,d.own_gross,d.own_tare,d.own_net,tax.gstno FROM sales_invoice s,sales_invoice_trans_dtls t,sales_invoice_item_dtls_for_jobwork j,sales_invoice_item_dtls_for_jobwork_price jp,delivery_challan_weight_dtl d,sales_invoice_broker_dtls b,sales_invoice_tax_info tax WHERE t.invoice_id=s.invoice_id AND j.invoice_id=s.invoice_id AND jp.invoice_id=s.invoice_id AND b.invoice_id=s.invoice_id AND d.delivery_cid =s.reference_id AND tax.invoice_id=s.invoice_id AND s.modified_type='INSERTED' AND t.modified_type=s.modified_type AND j.modified_type=s.modified_type AND jp.modified_type=s.modified_type AND d.modified_type=s.modified_type AND b.modified_type=s.modified_type AND tax.modified_type=s.modified_type AND s.invoice_date>=:fromdate AND s.invoice_date<=:todate", nativeQuery=true)
	//@Query(value= "SELECT s.invoice_date,s.invoice_no,s.partyname,s.cust_ref_doc_date,s.cust_refdocno,s.e_invoice_no,s.remarks,s.waybill,t.vehicleno,j.job_item_name,j.job_packing_name,j.pack_qty,j.item_qty,j.item_uom,jp.job_price AS rate,jp.item_service_name,(SELECT ac_ledger_name FROM item_service_master WHERE service_id = jp.item_service AND modified_type = 'INSERTED') AS sal_ledger,jp.sac_code,jp.tax_value,(SELECT tax_name FROM tax_code_details WHERE tax_id = jp.taxcode AND modified_type = 'INSERTED') AS taxname,jp.cgst_tax,jp.cgst_amt,jp.sgst_tax,jp.sgst_amt, jp.igst_tax,jp.igst_amt,jp.tot_amount,b.broker_name,s.salesorderdate,s.salesorderno,d.own_gross,d.own_tare,d.own_net,tax.gstno FROM sales_invoice s LEFT JOIN sales_invoice_trans_dtls t ON t.invoice_id = s.invoice_id AND t.modified_type = s.modified_type LEFT JOIN sales_invoice_item_dtls_for_jobwork j ON j.invoice_id = s.invoice_id AND j.modified_type = s.modified_type LEFT JOIN sales_invoice_item_dtls_for_jobwork_price jp ON jp.invoice_id = s.invoice_id AND jp.modified_type = s.modified_type LEFT JOIN delivery_challan_weight_dtl d ON d.delivery_cid = s.reference_id AND d.modified_type = s.modified_type LEFT JOIN sales_invoice_broker_dtls b ON b.invoice_id = s.invoice_id AND b.modified_type = s.modified_type LEFT JOIN sales_invoice_tax_info tax ON tax.invoice_id = s.invoice_id AND tax.modified_type = s.modified_type WHERE s.modified_type = 'INSERTED' AND s.invoice_type='INV00003' AND s.invoice_date>=:fromdate AND s.invoice_date<=:todate", nativeQuery=true)
	@Query(value= "SELECT s.invoice_date,s.invoice_no,s.partyname,s.cust_ref_doc_date,s.cust_refdocno,loading.pur_cust_refdocno,s.e_invoice_no,s.remarks,CASE WHEN s.asn_no='' OR s.asn_no IS NULL OR s.asn_no='NA' THEN s.remarks ELSE s.asn_no END AS asn,s.waybill,t.vehicleno,j.job_item_name,j.job_packing_name,j.pack_qty,j.item_qty,j.item_uom,jp.job_price AS rate,jp.item_service_name,(SELECT ac_ledger_name FROM item_service_master WHERE service_id = jp.item_service AND modified_type = 'INSERTED') AS sal_ledger,jp.sac_code,jp.tax_value,(SELECT tax_name FROM tax_code_details WHERE tax_id = jp.taxcode AND modified_type = 'INSERTED') AS taxname,jp.cgst_tax,jp.cgst_amt,jp.sgst_tax,jp.sgst_amt, jp.igst_tax,jp.igst_amt,jp.tot_amount,b.broker_name,s.salesorderdate,s.salesorderno,d.own_gross,d.own_tare,d.own_net,tax.gstno FROM sales_invoice s LEFT JOIN sales_invoice_trans_dtls t ON t.invoice_id = s.invoice_id AND t.modified_type = s.modified_type LEFT JOIN sales_invoice_item_dtls_for_jobwork j ON j.invoice_id = s.invoice_id AND j.modified_type = s.modified_type LEFT JOIN sales_invoice_item_dtls_for_jobwork_price jp ON jp.invoice_id = s.invoice_id AND jp.modified_type = s.modified_type LEFT JOIN delivery_challan_weight_dtl d ON d.delivery_cid = s.reference_id AND d.modified_type = s.modified_type LEFT JOIN sales_invoice_broker_dtls b ON b.invoice_id = s.invoice_id AND b.modified_type = s.modified_type LEFT JOIN sales_invoice_tax_info tax ON tax.invoice_id = s.invoice_id AND tax.modified_type = s.modified_type LEFT JOIN wm_loading_advice loading ON loading.adviceno = s.adviceno AND loading.modified_type = s.modified_type WHERE s.modified_type = 'INSERTED' AND s.invoice_type='INV00003' AND s.invoice_date>=:fromdate AND s.invoice_date<=:todate", nativeQuery=true)
	List<Map<String, Object>> getJobWorkSalesReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice SET e_invoice_no=:einvno,create_einvoice=1 WHERE id =:id and modified_type='INSERTED'")
    int updateEinvoice(@Param("einvno") String einvno,@Param("id") long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice SET cencel_einvoice=1 WHERE id =:id and modified_type='INSERTED'")
    int updateEinvoiceCancel(@Param("id") long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice SET create_ewaybill=1,waybill=:ewaybill WHERE id =:id and modified_type='INSERTED'")
    int updateEwaybill(@Param("id") long id,@Param("ewaybill") String ewaybill);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice SET cencel_ewaybill=1 WHERE id =:id and modified_type='INSERTED'")
    int updateWaybillCancel(@Param("id") long id);
	
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice SET create_ewaybill_wo_invoice=1,waybill=:ewaybill WHERE id =:id and modified_type='INSERTED'")
    int updateEwaybillWOInv(@Param("id") long id,@Param("ewaybill") String ewaybill);
    
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Invoice SET cancel_ewaybill_wo_invoice=1 WHERE id =:id and modified_type='INSERTED'")
    int updateWaybillCancelWOInv(@Param("id") long id);
    
    /*@Query(value="SELECT\r\n" + 
    		"  `soi`.`invoice_date` AS `invoice_date`,\r\n" + 
    		"  `soi`.`invoice_id`   AS `invoice_id`,\r\n" + 
    		"  `soi`.`invoice_no`   AS `invoice_no`,\r\n" + 
    		"  `soi`.`state`        AS `state`,\r\n" + 
    		"  `soi`.`party`        AS `party`,\r\n" + 
    		"  `soi`.`partyname`    AS `partyname`,\r\n" + 
    		"  `z`.`add1`    AS `address`,\r\n" + 
    		"  `x`.`item_name`         AS `item_name`,\r\n" + 
    		"  `x`.`packing_name`      AS `packing_name`,\r\n" + 
    		"  `x`.`squantity`         AS `squantity`,\r\n" + 
    		"  `x`.`quantity`          AS `quantity`,\r\n" + 
    		"  `x`.`price`             AS `price`,\r\n" + 
    		"  `x`.`amount`            AS `amount`,\r\n" + 
    		"  `x`.`cgstamt`           AS `cgstamt`,\r\n" + 
    		"  `x`.`igstamt`           AS `igstamt`,\r\n" + 
    		"  `x`.`sgstamt`           AS `sgstamt`,\r\n" + 
    		"  `x`.`tax_amt`           AS `tax_amt`,\r\n" + 
    		"  `x`.`total_amt`         AS `total_amt`,\r\n" + 
    		"  `soi`.`payable_amt`     AS `payable_amt`,\r\n" + 
    		"  `y`.`vehicleno`         AS `vehicleno`\r\n" + 
    		"FROM (`sales_invoice` `soi`\r\n" + 
    		"   LEFT JOIN (SELECT\r\n" + 
    		"                `si`.`invoice_id`        AS `invoice_id`,\r\n" + 
    		"                `si`.`item_name`         AS `item_name`,\r\n" + 
    		"                `si`.`packing_name`      AS `packing_name`,\r\n" + 
    		"                CONCAT(`si`.`squantity`,\" \",`si`.`suom`)         AS `squantity`,\r\n" + 
    		"                CONCAT(`si`.`quantity`,\" \",`si`.`uom`)          AS `quantity`,\r\n" + 
    		"                `si`.`price`             AS `price`,\r\n" + 
    		"                `si`.`amount`            AS `amount`,\r\n" + 
    		"                `si`.`cgstamt`           AS `cgstamt`,\r\n" + 
    		"                `si`.`igstamt`           AS `igstamt`,\r\n" + 
    		"                `si`.`sgstamt`           AS `sgstamt`,\r\n" + 
    		"                `si`.`tax_amt`           AS `tax_amt`,\r\n" + 
    		"                `si`.`total_amt`           AS `total_amt`\r\n" + 
    		"              FROM (`sales_invoice_item_dtls` `si`)\r\n" + 
    		"              WHERE (`si`.`modified_type` = 'INSERTED')\r\n" + 
    		"              GROUP BY `si`.`invoice_id`,`si`.`item_name`,`si`.`packing_name`) `x`\r\n" + 
    		"     ON (((`soi`.`invoice_id` = `x`.`invoice_id`)))\r\n" + 
    		"      LEFT JOIN (SELECT\r\n" + 
    		"                `v`.`invoice_id`           AS `invoice_id`,\r\n" + 
    		"                `v`.`vehicleno`           AS `vehicleno`\r\n" + 
    		"              FROM (`sales_invoice_trans_dtls` `v`)\r\n" + 
    		"              WHERE (`v`.`modified_type` = 'INSERTED')\r\n" + 
    		"              GROUP BY `v`.`invoice_id`) `y`\r\n" + 
    		"     ON (((`soi`.`invoice_id` = `y`.`invoice_id`)))\r\n" + 
    		"      LEFT JOIN (SELECT\r\n" + 
    		"                `c`.`cp_id`           AS `cp_id`,\r\n" + 
    		"                `c`.`add1`           AS `add1`\r\n" + 
    		"              FROM (`cust_bussiness_partner_address` `c`)\r\n" + 
    		"              WHERE (`c`.`modified_type` = 'INSERTED')\r\n" + 
    		"              GROUP BY `c`.`cp_id`) `z`\r\n" + 
    		"     ON (((`soi`.`party` = `z`.`cp_id`)))\r\n" + 
    		"     \r\n" + 
    		"     )\r\n" + 
    		"  WHERE (`soi`.`modified_type` = 'INSERTED'  AND `soi`.`invoice_date`>=:fromdate AND `soi`.`invoice_date`<=:todate AND `soi`.`jobwork` =0)",nativeQuery=true)*/
    @Query(value="SELECT\\r\\n\" + \r\n"
    		+ "    		\"  `soi`.`invoice_date` AS `invoice_date`,\\r\\n\" + \r\n"
    		+ "    		\"  `soi`.`invoice_id`   AS `invoice_id`,\\r\\n\" + \r\n"
    		+ "    		\"  `soi`.`invoice_no`   AS `invoice_no`,\\r\\n\" + \r\n"
    		+ "    		\"  `soi`.`state`        AS `state`,\\r\\n\" + \r\n"
    		+ "    		\"  `soi`.`party`        AS `party`,\\r\\n\" + \r\n"
    		+ "    		\"  `soi`.`partyname`    AS `partyname`,\\r\\n\" + \r\n"
    		+ "    		\"  `z`.`add1`    AS `address`,\\r\\n\" + \r\n"
    		+ "    		\"   CASE WHEN (SELECT pincode FROM `cust_bussiness_partner_address` WHERE cp_id=`soi`.`party` AND modified_type='INSERTED') IS NULL THEN 'NA' ELSE (SELECT pincode FROM `cust_bussiness_partner_address` WHERE cp_id=`soi`.`party` AND modified_type='INSERTED') END AS pincode,\\r\\n\" +\r\n"
    		+ "    		\"  `x`.`item_name`         AS `item_name`,\\r\\n\" + \r\n"
    		+ "    		\"  `x`.`packing_name`      AS `packing_name`,\\r\\n\" + \r\n"
    		+ "    		\"  `x`.`squantity`         AS `squantity`,\\r\\n\" + \r\n"
    		+ "    		\"  `x`.`quantity`          AS `quantity`,\\r\\n\" + \r\n"
    		+ "    		\"  `x`.`price`             AS `price`,\\r\\n\" + \r\n"
    		+ "    		\"  `x`.`amount`            AS `amount`,\\r\\n\" + \r\n"
    		+ "    		\"  `x`.`cgstamt`           AS `cgstamt`,\\r\\n\" + \r\n"
    		+ "    		\"  `x`.`igstamt`           AS `igstamt`,\\r\\n\" + \r\n"
    		+ "    		\"  `x`.`sgstamt`           AS `sgstamt`,\\r\\n\" + \r\n"
    		+ "    		\"  `x`.`tax_amt`           AS `tax_amt`,\\r\\n\" + \r\n"
    		+ "    		\"  `x`.`total_amt`         AS `total_amt`,\\r\\n\" + \r\n"
    		+ "    		\"  `soi`.`payable_amt`     AS `payable_amt`,\\r\\n\" + \r\n"
    		+ "    		\"  `y`.`vehicleno`         AS `vehicleno`,\\r\\n\" + \r\n"
    		+ "    		\"  `soi`.`waybill`         AS `waybill`\\r\\n\" + \r\n"
    		+ "    		\"FROM (`sales_invoice` `soi`\\r\\n\" + \r\n"
    		+ "    		\"   LEFT JOIN (SELECT\\r\\n\" + \r\n"
    		+ "    		\"                `si`.`invoice_id`        AS `invoice_id`,\\r\\n\" + \r\n"
    		+ "    		\"                `si`.`item_name`         AS `item_name`,\\r\\n\" + \r\n"
    		+ "    		\"                `si`.`packing_name`      AS `packing_name`,\\r\\n\" + \r\n"
    		+ "    		\"                CONCAT(`si`.`squantity`,\\\" \\\",`si`.`suom`)         AS `squantity`,\\r\\n\" + \r\n"
    		+ "    		\"                CONCAT(`si`.`quantity`,\\\" \\\",`si`.`uom`)          AS `quantity`,\\r\\n\" + \r\n"
    		+ "    		\"                `si`.`price`             AS `price`,\\r\\n\" + \r\n"
    		+ "    		\"                `si`.`amount`            AS `amount`,\\r\\n\" + \r\n"
    		+ "    		\"                `si`.`cgstamt`           AS `cgstamt`,\\r\\n\" + \r\n"
    		+ "    		\"                `si`.`igstamt`           AS `igstamt`,\\r\\n\" + \r\n"
    		+ "    		\"                `si`.`sgstamt`           AS `sgstamt`,\\r\\n\" + \r\n"
    		+ "    		\"                `si`.`tax_amt`           AS `tax_amt`,\\r\\n\" + \r\n"
    		+ "    		\"                `si`.`total_amt`           AS `total_amt`\\r\\n\" + \r\n"
    		+ "    		\"              FROM (`sales_invoice_item_dtls` `si`)\\r\\n\" + \r\n"
    		+ "    		\"              WHERE (`si`.`modified_type` = 'INSERTED')\\r\\n\" + \r\n"
    		+ "    		\"              GROUP BY `si`.`invoice_id`,`si`.`item_name`,`si`.`packing_name`) `x`\\r\\n\" + \r\n"
    		+ "    		\"     ON (((`soi`.`invoice_id` = `x`.`invoice_id`)))\\r\\n\" + \r\n"
    		+ "    		\"      LEFT JOIN (SELECT\\r\\n\" + \r\n"
    		+ "    		\"                `v`.`invoice_id`           AS `invoice_id`,\\r\\n\" + \r\n"
    		+ "    		\"                `v`.`vehicleno`           AS `vehicleno`\\r\\n\" + \r\n"
    		+ "    		\"              FROM (`sales_invoice_trans_dtls` `v`)\\r\\n\" + \r\n"
    		+ "    		\"              WHERE (`v`.`modified_type` = 'INSERTED')\\r\\n\" + \r\n"
    		+ "    		\"              GROUP BY `v`.`invoice_id`) `y`\\r\\n\" + \r\n"
    		+ "    		\"     ON (((`soi`.`invoice_id` = `y`.`invoice_id`)))\\r\\n\" + \r\n"
    		+ "    		\"      LEFT JOIN (SELECT\\r\\n\" + \r\n"
    		+ "    		\"                `c`.`cp_id`           AS `cp_id`,\\r\\n\" + \r\n"
    		+ "    		\"                `c`.`add1`           AS `add1`\\r\\n\" + \r\n"
    		+ "    		\"              FROM (`cust_bussiness_partner_address` `c`)\\r\\n\" + \r\n"
    		+ "    		\"              WHERE (`c`.`modified_type` = 'INSERTED')\\r\\n\" + \r\n"
    		+ "    		\"              GROUP BY `c`.`cp_id`) `z`\\r\\n\" + \r\n"
    		+ "    		\"     ON (((`soi`.`party` = `z`.`cp_id`)))\\r\\n\" + \r\n"
    		+ "    		\"     \\r\\n\" + \r\n"
    		+ "    		\"     )\\r\\n\" + \r\n"
    		+ "    		\"  WHERE (`soi`.`modified_type` = 'INSERTED'  AND `soi`.`invoice_date`>=:fromdate AND `soi`.`invoice_date`<=:todate AND `soi`.`jobwork` =0)",nativeQuery=true)
    List<Map<String,Object>> getSalesInvoicetransitReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
    
    @Modifying(clearAutomatically = true)
	@Query("UPDATE Sales_Invoice p SET p.asn_no=:asnno WHERE p.id = :id AND p.invoice_no=:invoiceno" )
	int updateAsnNo(@Param("id") long id,@Param("invoiceno") String invoiceno,@Param("asnno") String asnno);
	
    
    @Query(value="select s.payable_amt from sales_invoice s where s.modified_type = 'INSERTED' and s.invoice_id =:invoice_id ORDER BY s.invoice_id DESC",nativeQuery=true)
	double getSalesInvDetailsNew(@Param("invoice_id") String invoice_id);
    
}