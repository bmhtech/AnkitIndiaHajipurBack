package com.AnkitIndia.jwtauthentication.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Item_Dtls_for_jobwork_price;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Party_Dtls;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesOrderDeliveryChallanCheckDTO;

public interface Sales_OrderRepository extends JpaRepository<Sales_Order, Long>{
	
	@Query("select COUNT(id) from Sales_Order")
	String countId();
	
	@Query("select COUNT(id) from Sales_Order where order_date =:orderdate and order_type =:type ")
	String countSalesOrder(@Param("orderdate") String orderdate,@Param("type") String type);
	
	@Query("select COUNT(id) from Sales_Order where order_date =:orderdate and inv_type='INV00005' ")
	String countSalesOrderwarehouse(@Param("orderdate") String orderdate);
	
	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.q_status='Finalise' ")
	List<Sales_Order> salesOrderList();
	
	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' ORDER BY s.order_id DESC ")
	List<Sales_Order> findSalesOrders();
	
	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.reason='RSM00006'  ORDER BY s.order_id DESC ")
	List<Sales_Order> findSalesOrdersliew();
	
	//@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.customer =:party and s.company_id =:comp and s.order_date <=:invdate ")
	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.customer =:party and s.sales_returnstatus ='NO' and s.company_id =:comp and s.order_date <=:invdate ")
	List<Sales_Order> getSalesOrders(@Param("party") String party,@Param("invdate") String invdate,@Param("comp") String comp);
	
	//DATE_FORMAT(STR_TO_DATE(s.order_date,'%d-%m-%Y'),'%Y-%m-%d')
	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.customer =:party and s.business_unit =:bunit and s.order_date <=:invdate ")
	List<Sales_Order> findSalesOrders(@Param("party") String party,@Param("invdate") String invdate,@Param("bunit") String bunit);
	
	//@Query(value="SELECT soi.order_no  AS order_no, soi.order_type AS order_type, soi.order_date AS order_date, soi.order_id AS order_id, soi.trans_borne_by_chgs AS trans_borne_by_chgs, cbp.cp_name AS customer_name, soi.we_uom AS we_uom, soi.remarks AS remarks, X.rest_wt,soi.cust_channel_list FROM (sales_order soi LEFT JOIN cust_bussiness_partner cbp ON (cbp.cp_id= soi.customer ) LEFT JOIN custom_uom_master cum ON (cum.customuom_id= soi.we_uom ) RIGHT JOIN (SELECT SUM(wl.rest_wt) AS rest_wt,wl.order_id FROM loading_sales_order_w_tolerance wl GROUP BY wl.order_id) X ON (soi.order_id = X.order_id)) WHERE ((soi.modified_type = 'INSERTED') AND (soi.cust_channel_list LIKE %:party%) AND (soi.tolerancecheckpoint ='Yes') AND (soi.inv_type!='INV00003') AND (soi.order_date<=:invdate) AND (X.rest_wt>0) AND (soi.terminate=0) )", nativeQuery = true)
	@Query(value="SELECT soi.order_no  AS order_no, soi.order_type AS order_type, soi.order_date AS order_date, soi.order_id AS order_id, soi.trans_borne_by_chgs AS trans_borne_by_chgs,cbp.cp_name AS customer_name, soi.we_uom AS we_uom, soi.remarks AS remarks, X.rest_wt,soi.cust_channel_list,con.payment_mode FROM (sales_order soi LEFT JOIN cust_bussiness_partner cbp ON (cbp.cp_id= soi.customer ) LEFT JOIN custom_uom_master cum ON (cum.customuom_id= soi.we_uom ) LEFT JOIN  sales_order_terms_con con  ON (con.order_id= soi.order_id  AND con.modified_type='INSERTED') RIGHT JOIN (SELECT SUM(wl.rest_wt) AS rest_wt,wl.order_id FROM loading_sales_order_w_tolerance wl GROUP BY wl.order_id) X ON (soi.order_id = X.order_id)) WHERE ((soi.modified_type = 'INSERTED') AND (soi.cust_channel_list LIKE %:party%) AND (soi.tolerancecheckpoint ='Yes') AND (soi.inv_type!='INV00003') AND (soi.order_date<=:invdate) AND (X.rest_wt>0) AND (soi.terminate=0) ) ORDER BY soi.order_date", nativeQuery = true)
	List<Map<String,Object>> findSalesOrdersModified(@Param("party") String party,@Param("invdate") String invdate);
	
	
	@Query(value="SELECT soi.order_no  AS order_no, soi.order_type AS order_type, soi.order_date AS order_date, soi.order_id AS order_id, soi.trans_borne_by_chgs AS trans_borne_by_chgs,cbp.cp_name AS customer_name, soi.we_uom AS we_uom, soi.remarks AS remarks, X.rest_wt,soi.cust_channel_list,con.payment_mode FROM (sales_order soi LEFT JOIN cust_bussiness_partner cbp ON (cbp.cp_id= soi.customer ) LEFT JOIN custom_uom_master cum ON (cum.customuom_id= soi.we_uom ) LEFT JOIN  sales_order_terms_con con  ON (con.order_id= soi.order_id  AND con.modified_type='INSERTED') RIGHT JOIN (SELECT SUM(wl.rest_wt) AS rest_wt,wl.order_id FROM loading_sales_order_w_tolerance wl GROUP BY wl.order_id) X ON (soi.order_id = X.order_id)) WHERE ((soi.modified_type = 'INSERTED') AND (soi.cust_channel_list LIKE %:party%) AND (soi.tolerancecheckpoint ='Yes') AND (soi.inv_type!='INV00003') AND (soi.order_date<=:invdate) AND (X.rest_wt>0) AND (soi.terminate=0) and (soi.loading_status=0) ) ORDER BY soi.order_date", nativeQuery = true)
	List<Map<String,Object>> findSalesOrdersRefraction(@Param("party") String party,@Param("invdate") String invdate);
	
	
	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.order_id =:orderid ")
	Sales_Order getSalesOrderDetails(@Param("orderid") String orderid);
	
	
	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.order_no =:order_no ")
	Sales_Order getSalesOrderDetailssearch(@Param("order_no") String order_no);
	
	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.order_id =:orderid and s.reason='RSM00001' and s.business_unit =:bunit and s.order_date <=:invdate ")
	Sales_Order getSalesOrderDetailsloading(@Param("orderid") String orderid,@Param("bunit") String bunit,@Param("invdate") String invdate);
	
	
	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.order_id =:orderid and s.business_unit =:bunit")
	Optional<Sales_Order> getSalesOrderDetails(@Param("orderid") String orderid,@Param("bunit") String bunit);
	
	@Query("select s from Sales_Order s where s.id =:id")
	Sales_Order getSalesOrderDetails(@Param("id") Long id);
	
	//@Query(value="SELECT CASE WHEN SUM(su.net_amount) IS NULL THEN '0.00' ELSE SUM(su.net_amount) END AS amount FROM sales_order s,sales_order_summary su WHERE s.customer=:party AND s.modified_type='INSERTED' AND su.modified_type=s.modified_type AND su.order_id= s.order_id and s.fin_year=:finyear",nativeQuery=true)
	@Query(value="SELECT CASE WHEN SUM(s.payable_amt) IS NULL THEN '0.00' ELSE SUM(s.payable_amt) END AS amount FROM sales_invoice s WHERE s.party=:party AND s.modified_type='INSERTED' AND s.fin_year=:finyear",nativeQuery=true)
	double getSalesOrderDetailsaprty(@Param("party") String party,@Param("finyear") String finyear);
	
	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.order_id =:order_id ")
	List<Sales_Order> salesOrderPartyList(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order s SET s.loading_status =:mstatus  WHERE s.order_id =:referenceid")
    int updateSalesOrderDetails(@Param("referenceid") String referenceid,@Param("mstatus") boolean mstatus);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order s SET s.salereturn_id =:salereturn_id,s.sales_returnstatus='YES' WHERE s.order_id =:referenceid")
    int updatesalesorder_salesreturn(@Param("referenceid") String referenceid,@Param("salereturn_id") String salereturn_id);
	
	@Query(value = "select s.rest_wt from loading_sales_order s where s.order_id = :orderid and s.item_code = :itemid ", nativeQuery=true)
	Double getLoadingRestWeight(@Param("orderid") String orderid,@Param("itemid") String itemid);
	
	
	@Query(value = "select s.rest_wt from loading_sales_order s where s.order_id = :orderid and s.item_code = :itemid and s.packing = :packing ", nativeQuery=true)
	Double getLoadingRestWeightNew(@Param("orderid") String orderid,@Param("itemid") String itemid,@Param("packing") String packing);
	
	
	
	@Query(value = "select s.rest_wt from loading_sales_order_w_tolerance s where s.order_id = :orderid and s.item_code = :itemid ", nativeQuery=true)
	Double toleranceloading(@Param("orderid") String orderid,@Param("itemid") String itemid);
	
	//SELECT s.rest_item_qty FROM loading_sales_order_jobworks_w_tolerance s WHERE s.order_id = 'SO08744' AND s.job_item = 'IM00722'
	
	@Query(value = "SELECT s.rest_item_qty FROM loading_sales_order_jobworks_w_tolerance s WHERE s.order_id =:orderid  AND s.job_item =:itemid", nativeQuery=true)
	Double toleranceloadingjobwork(@Param("orderid") String orderid,@Param("itemid") String itemid);
	
	
	@Query(value="SELECT CASE WHEN rest_wt>=:item_qty THEN 'Yes' ELSE 'No' END FROM item_allocation_potaggingwith_loading WHERE document_no=:pur_cust_refdocno ", nativeQuery=true)
	String allocationStatus(@Param ("pur_cust_refdocno") String pur_cust_refdocno,@Param("item_qty") Double item_qty);
	
	@Query(value="SELECT CASE WHEN allocation.rest_wt>=x.mat_wt  THEN 'Yes' ELSE 'No' END FROM (SELECT  `wlm`.`advice_id` AS `advice_id`,`wlm`.`pur_cust_refdocno` AS pur_cust_refdocno,SUM(`wl`.`mat_wt`) AS `mat_wt` FROM (`wm_loading_advice_itm_dtls` `wl` LEFT JOIN `wm_loading_advice` `wlm` ON ((`wlm`.`advice_id` = `wl`.`advice_id`))) WHERE ((`wl`.`modified_type` = 'INSERTED') AND (`wlm`.`modified_type` = 'INSERTED') AND (`wlm`.`advice_id`=:advice_id))) `x` LEFT JOIN (SELECT rest_wt,document_no FROM item_allocation_potaggingwith_loading ) allocation ON (allocation.document_no=x.pur_cust_refdocno)", nativeQuery=true)
	String allocationStatusgrn(@Param("advice_id") String advice_id);
	
	@Query(value = "select s.rest_wt from loading_sales_order_w_tolerance s where s.order_id = :orderid and s.item_code = :itemid and s.packing = :packing ", nativeQuery=true)
	Double getLoadingRestWeightNewtolerance(@Param("orderid") String orderid,@Param("itemid") String itemid,@Param("packing") String packing);
	
	
	@Query("select s from Sales_Order_Party_Dtls s where s.modified_type = 'INSERTED' and s.p_code =:party ")
	List<Sales_Order_Party_Dtls> getpartysalesorder(@Param("party") String party);

	
	@Query(value="select SUM(rest_wt)  from loading_sales_order  where order_id =:order_id",nativeQuery=true)
	double getrestwet_forloadingpopup(@Param("order_id") String order_id);
	
	
	@Query(value="select SUM(rest_wt)  from loading_sales_order_w_tolerance  where order_id =:order_id",nativeQuery=true)
	double getrestwet_forloadingpopuptolerance(@Param("order_id") String order_id);
	
	//@Query("SELECT p FROM Sales_Order p WHERE p.cust_channel_list like %:customer% AND p.modified_type = 'INSERTED' ")
	@Query("SELECT p FROM Sales_Order p WHERE p.cust_channel_list like %:customer% AND p.modified_type = 'INSERTED' and p.tolerancecheckpoint ='Yes' and p.inv_type!='INV00003' ")
	List<Sales_Order> getsaleorderbypartychannel(@Param("customer") String customer);
	
	
	//@Query(value = "{call getSearchpurorderData(:#{#orderno},:#{#fromdate},:#{#todate},:#{#customername})}", nativeQuery = true)
	@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Sales_Order> getsearchdata(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("customername") String customername,@Param("finyear") String finyear);

	
	//
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Sales_Order pl where pl.modified_type = 'INSERTED' and pl.order_id=:order_id  and pl.inv_type =:type")
	Boolean checkgstregular(@Param("order_id") String order_id,@Param("type") String type);
	
//	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.order_date=:currDate ")
//	List<Sales_Order> getSaleOrderList(@Param("currDate") String currDate);
	
	
	@Query(value = "SELECT s.order_id AS order_id,\r\n" + 
			"(SELECT SUM(quantity) FROM `sales_order_item_dtls` WHERE modified_type='INSERTED' AND s.order_id=order_id) AS salesqty,\r\n" + 
			"SUM(CASE\r\n" + 
			"WHEN \r\n" + 
			"(SELECT COUNT(id)>0 FROM `delivery_challan` WHERE modified_type='INSERTED' AND referance_id=w.advice_id)\r\n" + 
			"THEN \r\n" + 
			"(SELECT SUM(d.quantity) FROM `delivery_challan_item_dtls` d,delivery_challan c  WHERE d.modified_type='INSERTED' AND d.delivery_cid =c.delivery_cid AND  c.referance_id=w.advice_id AND d.modified_type= c.modified_type)\r\n" + 
			"ELSE \r\n" + 
			"0.000\r\n" + 
			"END)\r\n" + 
			"AS challanqty\r\n" + 
			"FROM `sales_order` s, `wm_loading_advice` w\r\n" + 
			"WHERE s.order_id=w.`referance_id`\r\n" + 
			"AND  s.modified_type='INSERTED'\r\n" + 
			"AND  s.modified_type= w.modified_type\r\n" + 
			"AND s.order_id=:order_id", nativeQuery = true)
	//@Query(value = "SELECT s.order_id AS order_id,(SELECT SUM(quantity) FROM `sales_order_item_dtls` WHERE modified_type='INSERTED' AND s.order_id=:order_id) AS salesqty, checkdeliverychallan(:order_id) AS challanqty FROM sales_order s WHERE s.order_id=:order_id AND s.modified_type='INSERTED'", nativeQuery = true)
	List<Object[]>  deliveryChallanCheck(@Param("order_id") String order_id);
	//SELECT s.order_id AS order_id,(SELECT SUM(quantity) FROM `sales_order_item_dtls` WHERE modified_type='INSERTED' AND s.order_id='SO03982') AS salesqty,SUM(CASE WHEN (SELECT COUNT(id)>0 FROM `delivery_challan` WHERE modified_type='INSERTED' AND referance_id=w.advice_id) THEN (SELECT SUM(d.quantity) FROM `delivery_challan_item_dtls` d,delivery_challan c  WHERE d.modified_type='INSERTED' AND d.delivery_cid =c.delivery_cid AND  c.referance_id=w.advice_id AND d.modified_type= c.modified_type) ELSE 0.000 END) AS challanqty FROM `sales_order` s, `wm_loading_advice` w WHERE s.order_id=w.`referance_id` AND  s.modified_type='INSERTED'AND s.modified_type= w.modified_type AND s.order_id='SO03982'
	
	@Query(value= "SELECT s.id AS id,s.order_id AS order_id,s.order_date AS order_date,s.order_no AS order_no,s.order_type AS order_type,(CASE WHEN s.delivery_date IS NULL THEN 'NA' ELSE s.delivery_date END) AS delivery_date,(SELECT reason FROM reason_master WHERE modified_type ='INSERTED' AND reason_id =s.reason) AS q_status,(SELECT cp_name FROM cust_bussiness_partner WHERE modified_type ='INSERTED' AND cp_id =s.customer) AS customer_name,s.ref_type AS ref_type,(SELECT invtype_name FROM invoice_type WHERE modified_type ='INSERTED' AND invtype_id =s.inv_type) AS inv_type,(CASE WHEN s.inv_type = 'INV00003' THEN s.total_job_amt ELSE (SELECT (SUM(si.amount) + SUM(si.tax_amt) - SUM(si.discount_amt)) AS netamt FROM sales_order_item_dtls si WHERE si.modified_type = 'INSERTED' AND si.order_id = s.order_id) END) AS net_amount,s.tolerancecheckpoint AS tolerancecheckpoint,s.loading_status as loading_status,s.terminate as terminate FROM sales_order s WHERE s.modified_type='INSERTED' AND s.order_date=:currdate AND s.fin_year=:finyear", nativeQuery=true)
	List<Map<String, Object>> getSaleOrderFastList(@Param("currdate") String currdate, @Param("finyear") String finyear);
	
	@Query(value= "SELECT s.id AS id,s.order_id AS order_id,s.order_date AS order_date,s.order_no AS order_no,s.order_type AS order_type,(CASE WHEN s.delivery_date IS NULL THEN 'NA' ELSE s.delivery_date END) AS delivery_date,(SELECT reason FROM reason_master WHERE modified_type ='INSERTED' AND reason_id =s.reason) AS q_status,(SELECT cp_name FROM cust_bussiness_partner WHERE modified_type ='INSERTED' AND cp_id =s.customer) AS customer_name,s.ref_type AS ref_type,(SELECT invtype_name FROM invoice_type WHERE modified_type ='INSERTED' AND invtype_id =s.inv_type) AS inv_type,(CASE WHEN s.inv_type = 'INV00003' THEN s.total_job_amt ELSE (SELECT (SUM(si.amount) + SUM(si.tax_amt) - SUM(si.discount_amt)) AS netamt FROM sales_order_item_dtls si WHERE si.modified_type = 'INSERTED' AND si.order_id = s.order_id) END) AS net_amount,s.tolerancecheckpoint AS tolerancecheckpoint,s.loading_status as loading_status,s.terminate as terminate FROM sales_order s WHERE s.modified_type='INSERTED' AND :#{#sql}", nativeQuery=true)
	List<Map<String, Object>> getSaleOrderFastListsearch(@Param("sql") String sql);
	
	
	@Query(value ="select count(s.id) from sales_order_item_dtls s where s.modified_type = 'INSERTED' and s.order_id = :orderid",nativeQuery=true)
	int countlengthSalesItem(@Param("orderid") String orderid);
	
	//@Procedure(name = "Wm_loading_advice_itm_dtls.updateSalesorderwithLoadingAdvice")
	@Query(value = "{call updateSalesorderwithLoadingAdvice(:#{#orderid},:#{#localdate})}", nativeQuery = true)
	int updateSalesorderwithLoadingAdvice(@Param("orderid") String orderid,@Param("localdate") LocalDateTime localdate);


	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order s SET s.loading_status =0 WHERE s.order_id =:referenceid")
    int reverseSalesOrderExchange(@Param("referenceid") String referenceid);

	//@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount FROM sales_order_item_dtls s1,sales_order s2 WHERE s1.order_id = s2.order_id AND s2.customer=:customer AND S2.order_date>=:fromdate AND S2.order_date<=:todate AND S2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name,s1.price ORDER BY s1.item_name", nativeQuery=true)
	@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount FROM sales_order_item_dtls s1,sales_order s2 WHERE s1.order_id = s2.order_id AND s2.customer IN (:customer) AND s2.order_date>=:fromdate AND s2.order_date<=:todate AND s2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name ORDER BY s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesOrderSummaryPartywise(@Param("customer") List<String> customer,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit);
	
	//@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount FROM sales_order_item_dtls s1,sales_order s2 WHERE s1.order_id = s2.order_id AND s1.item_code IN (:item) AND S2.order_date>=:fromdate AND S2.order_date<=:todate AND S2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name,s1.price ORDER BY s1.item_name", nativeQuery=true)
	@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount FROM sales_order_item_dtls s1,sales_order s2 WHERE s1.order_id = s2.order_id AND s1.item_code IN (:item) AND s2.order_date>=:fromdate AND s2.order_date<=:todate AND s2.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name ORDER BY s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesOrderSummaryItemwise(@Param("item") List<String> item,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit);
	
	//@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount FROM sales_order_item_dtls s1,sales_order_broker_dtls s2,sales_order s3 WHERE s1.order_id = s2.order_id AND s2.order_id= s3.order_id AND s2.broker_code=:broker AND S3.order_date>=:fromdate AND S3.order_date<=:todate AND S3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name,s1.price ORDER BY s1.item_name", nativeQuery=true)
	@Query(value="select s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount FROM sales_order_item_dtls s1,sales_order_broker_dtls s2,sales_order s3 WHERE s1.order_id = s2.order_id AND s2.order_id= s3.order_id AND s2.broker_code IN (:broker) AND s3.order_date>=:fromdate AND s3.order_date<=:todate AND s3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' GROUP BY s1.item_name,s1.packing_name ORDER BY s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesOrderSummaryBrokerwise(@Param("broker") List<String> broker,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit);
	
	//@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount,s2.p_name AS p_name,S3.order_date AS order_date,s4.broker_name as broker_name FROM sales_order_item_dtls s1,sales_order_party_dtls s2,sales_order s3,sales_order_broker_dtls s4 WHERE s1.order_id = s2.order_id AND s1.order_id = s3.order_id AND s2.order_id = s3.order_id AND s1.order_id = s4.order_id AND s2.order_id = s4.order_id AND s3.order_id = s4.order_id AND S3.order_date>=:fromdate AND S3.order_date<=:todate AND S3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' AND s4.modified_type='INSERTED' AND s2.p_code=:customer GROUP BY s2.p_code,s1.packing_name,s1.item_name,s1.price", nativeQuery=true)
	@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount,s2.p_name AS p_name,s3.order_date AS order_date,s3.id as id,s3.order_id as order_id,s4.broker_name as broker_name FROM sales_order_item_dtls s1,sales_order_party_dtls s2,sales_order s3,sales_order_broker_dtls s4 WHERE s1.order_id = s2.order_id AND s1.order_id = s3.order_id AND s2.order_id = s3.order_id AND s1.order_id = s4.order_id AND s2.order_id = s4.order_id AND s3.order_id = s4.order_id AND s3.order_date>=:fromdate AND s3.order_date<=:todate AND s3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' AND s4.modified_type='INSERTED' AND s2.p_code IN (:customer) GROUP BY s2.p_code,s1.packing_name,s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesOrderMiscPartyList(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit,@Param("customer") List<String> customer);
	
	//@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount,s2.broker_name AS broker_name,S3.order_date AS order_date,s4.p_name FROM sales_order_item_dtls s1,sales_order_broker_dtls s2,sales_order s3,sales_order_party_dtls s4 WHERE s1.order_id = s2.order_id AND s1.order_id = s3.order_id AND s2.order_id = s3.order_id AND s1.order_id = s4.order_id AND s2.order_id = s4.order_id AND s3.order_id = s4.order_id AND S3.order_date>=:fromdate AND S3.order_date<=:todate AND S3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' AND s4.modified_type='INSERTED' AND s2.broker_code=:broker GROUP BY s2.broker_code,s1.packing_name,s1.item_name,s1.price", nativeQuery=true)
	@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount,s2.broker_name AS broker_name,s3.order_date AS order_date,s3.id as id,s3.order_id as order_id,s4.p_name FROM sales_order_item_dtls s1,sales_order_broker_dtls s2,sales_order s3,sales_order_party_dtls s4 WHERE s1.order_id = s2.order_id AND s1.order_id = s3.order_id AND s2.order_id = s3.order_id AND s1.order_id = s4.order_id AND s2.order_id = s4.order_id AND s3.order_id = s4.order_id AND s3.order_date>=:fromdate AND s3.order_date<=:todate AND s3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' AND s4.modified_type='INSERTED' AND s2.broker_code IN (:broker) GROUP BY s2.broker_code,s1.packing_name,s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesOrderMiscBrokerList(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit,@Param("broker") List<String> broker);
	
	//@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,s1.price AS price,SUM(s1.amount) AS amount,s2.p_name AS p_name,S3.order_date AS order_date,s4.broker_name AS broker_name FROM sales_order_item_dtls s1,sales_order_party_dtls s2,sales_order s3,sales_order_broker_dtls s4 WHERE s1.order_id = s2.order_id AND s1.order_id = s3.order_id AND s2.order_id = s3.order_id AND s1.order_id = s4.order_id AND s2.order_id = s4.order_id AND s3.order_id = s4.order_id AND S3.order_date>=:fromdate AND S3.order_date<=:todate AND S3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' AND s4.modified_type='INSERTED' GROUP BY s2.p_code,s4.broker_code,s1.packing_name,s1.item_name,s1.price", nativeQuery=true)
	@Query(value="SELECT s1.item_name AS item_name,s1.packing_name AS packing_name,SUM(s1.squantity) AS squantity,SUM(s1.quantity) AS quantity,SUM(s1.amount)/SUM(s1.squantity) AS bagsprice,SUM(s1.amount)/SUM(s1.quantity) AS qtlsprice,SUM(s1.amount) AS amount,s2.p_name AS p_name,s3.order_date AS order_date,s3.id as id,s3.order_id as order_id,s4.broker_name AS broker_name FROM sales_order_item_dtls s1,sales_order_party_dtls s2,sales_order s3,sales_order_broker_dtls s4 WHERE s1.order_id = s2.order_id AND s1.order_id = s3.order_id AND s2.order_id = s3.order_id AND s1.order_id = s4.order_id AND s2.order_id = s4.order_id AND s3.order_id = s4.order_id AND s3.order_date>=:fromdate AND s3.order_date<=:todate AND s3.business_unit=:bunit AND s1.modified_type='INSERTED' AND s2.modified_type='INSERTED' AND s3.modified_type='INSERTED' AND s4.modified_type='INSERTED' GROUP BY s2.p_code,s4.broker_code,s1.packing_name,s1.item_name", nativeQuery=true)
	List<Map<String,Object>> getSalesOrderMiscAllList(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("bunit") String bunit);
	
	@Query(value= "SELECT DISTINCT i.item_code AS item_code,i.item_name AS item_name FROM sales_order s,sales_order_item_dtls i WHERE s.modified_type='INSERTED' AND i.modified_type='INSERTED' AND s.modified_type=i.modified_type AND s.order_id =i.order_id AND s.company_id=:company AND s.order_date>=:fromdate AND s.order_date<=:todate and s.business_unit=:business_unit AND i.item_code IS NOT NULL", nativeQuery=true)
	List<Map<String, Object>> salesOrderFinishedItemlist(@Param("company") String company,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("business_unit") String business_unit);
	
	@Query(value= "SELECT DISTINCT i.broker_code AS broker_code,i.broker_name AS broker_name FROM sales_order s,sales_order_broker_dtls i WHERE s.modified_type='INSERTED' AND i.modified_type='INSERTED' AND s.modified_type=i.modified_type AND s.order_id =i.order_id AND s.company_id=:company AND s.order_date>=:fromdate AND s.order_date<=:todate and s.business_unit=:business_unit AND i.broker_code IS NOT NULL", nativeQuery=true)
	List<Map<String, Object>> salesOrderBrokerlist(@Param("company") String company,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("business_unit") String business_unit);
	
	@Query(value= "SELECT DISTINCT i.p_code AS p_code,i.p_name AS p_name FROM sales_order s,sales_order_party_dtls i WHERE s.modified_type='INSERTED' AND i.modified_type='INSERTED' AND s.modified_type=i.modified_type AND s.order_id =i.order_id AND s.company_id=:company AND s.order_date>=:fromdate AND s.order_date<=:todate and s.business_unit=:business_unit AND i.p_code IS NOT NULL", nativeQuery=true)
	List<Map<String, Object>> salesOrderPartylist(@Param("company") String company,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("business_unit") String business_unit);
	
	
	@Query("select s from Sales_Order s where s.modified_type = 'INSERTED' and s.cust_channel =:channel_custid")
	List<Sales_Order> getsalesorderthroughchannel(@Param("channel_custid") String channel_custid);
	
	@Query(value= "SELECT sale_order_id from salereturnapproval where party=:party and order_date<=:invdate and difference!=0", nativeQuery=true)
	List<String> getSalesOrderReturn(@Param("party") String party,@Param("invdate") String invdate);
	
	@Query(value= "SELECT s.order_id as order_id,s.order_no as order_no,s.order_date as order_date,s.customer as customer,CASE WHEN (SELECT cp_name FROM cust_bussiness_partner WHERE cp_id=s.customer) IS NULL THEN '' ELSE (SELECT cp_name FROM cust_bussiness_partner WHERE cp_id=s.customer) END  AS customer_name from sales_order s where s.order_id=:orderid and s.modified_type = 'INSERTED'", nativeQuery=true)
	Map<String,Object> getSalesOrderReturnList(@Param("orderid") String orderid);
	
	@Query(value= "SELECT d.trans_code AS trans_code FROM delivery_challan_trans_info d WHERE d.modified_type='INSERTED' AND d.delivery_cid=:delivery_cid", nativeQuery=true)
	String getTransCode(@Param("delivery_cid") String delivery_cid);
	
	@Query(value= "SELECT t.* FROM wm_loading_advice l,sales_order_trans_chgs_dyn t WHERE l.modified_type='INSERTED' AND t.modified_type=l.modified_type AND l.advice_id=:advice_id AND t.order_id=l.referance_id AND t.transporter_name=:trans_code", nativeQuery=true)
	Map<String,Object> getSalesOrderData(@Param("advice_id") String advice_id, @Param("trans_code") String trans_code);
	
	
	
	@Query(value= "SELECT t.* FROM delivery_challan_chgs_dyn t WHERE t.modified_type='INSERTED' AND t.delivery_cid=:delivery_cid", nativeQuery=true)
	Map<String,Object> getdeliverychallanData(@Param("delivery_cid") String delivery_cid);
	
	//@Query(value= "SELECT s.order_date,s.order_no, (SELECT cp_name FROM cust_bussiness_partner WHERE modified_type ='INSERTED' AND cp_id =s.customer) AS partyname, s.ref_type,b.broker_name,s.order_type,sd.item_name,sd.mat_wt,sd.squantity AS bag,sd.price,sd.price_based_on, (CASE WHEN (SELECT COUNT(id)>0 FROM wm_loading_advice WHERE modified_type='INSERTED' AND referance_id=s.order_id  AND weighment_status='2') THEN (SELECT SUM(d.mat_wt) FROM wm_loading_advice_itm_dtls d,wm_loading_advice w WHERE w.modified_type='INSERTED' AND d.modified_type =w.modified_type AND w.weighment_status='2' AND d.item_code = sd.item_code  AND d.packing =sd.packing AND d.advice_id = w.advice_id  AND s.order_id = w.referance_id) ELSE 0 END ) AS dispatch, (CASE WHEN (SELECT COUNT(id)>0 FROM wm_loading_advice WHERE modified_type='INSERTED' AND referance_id=s.order_id  AND weighment_status='2') THEN (SELECT SUM(d.s_quantity) FROM wm_loading_advice_itm_dtls d,wm_loading_advice w WHERE w.modified_type='INSERTED' AND d.modified_type =w.modified_type AND w.weighment_status='2' AND d.item_code = sd.item_code  AND d.packing =sd.packing AND d.advice_id = w.advice_id  AND s.order_id = w.referance_id) ELSE 0 END ) AS dispatchbag, ((sd.mat_wt)-((CASE WHEN (SELECT COUNT(id)>0 FROM wm_loading_advice WHERE modified_type='INSERTED' AND referance_id=s.order_id  AND weighment_status='2') THEN (SELECT SUM(d.mat_wt) FROM wm_loading_advice_itm_dtls d,wm_loading_advice w WHERE w.modified_type='INSERTED' AND d.modified_type =w.modified_type AND w.weighment_status='2' AND d.item_code = sd.item_code  AND d.packing =sd.packing AND   d.advice_id = w.advice_id  AND s.order_id = w.referance_id) ELSE 0 END))) AS pending, ((sd.squantity)-((CASE WHEN (SELECT COUNT(id)>0 FROM wm_loading_advice WHERE modified_type='INSERTED' AND referance_id=s.order_id  AND weighment_status='2') THEN (SELECT SUM(d.s_quantity) FROM wm_loading_advice_itm_dtls d,wm_loading_advice w WHERE w.modified_type='INSERTED' AND d.modified_type =w.modified_type AND w.weighment_status='2' AND d.item_code = sd.item_code  AND d.packing =sd.packing AND   d.advice_id = w.advice_id  AND s.order_id = w.referance_id) ELSE 0 END))) AS pendingbags FROM sales_order s,sales_order_item_dtls sd,sales_order_broker_dtls b WHERE s.order_id = sd.order_id AND s.order_id = b.order_id AND s.modified_type='INSERTED' AND s.modified_type= sd.modified_type AND s.modified_type=  b.modified_type AND s.order_date>=:fromdate AND s.order_date<=:todate", nativeQuery=true)

	//@Query(value= "SELECT s.order_date,s.order_no, (SELECT cp_name FROM cust_bussiness_partner WHERE modified_type ='INSERTED' AND cp_id =s.customer) AS partyname, s.ref_type,b.broker_name,s.order_type,sd.item_name,sd.mat_wt,sd.squantity AS bag,sd.price,sd.price_based_on, (CASE WHEN (SELECT COUNT(id)>0 FROM wm_loading_advice WHERE modified_type='INSERTED' AND referance_id=s.order_id  AND weighment_status='2') THEN (SELECT SUM(d.mat_wt) FROM wm_loading_advice_itm_dtls d,wm_loading_advice w WHERE w.modified_type='INSERTED' AND d.modified_type =w.modified_type AND w.weighment_status='2' AND d.item_code = sd.item_code  AND d.packing =sd.packing AND d.advice_id = w.advice_id  AND s.order_id = w.referance_id) ELSE 0 END ) AS dispatch, (CASE WHEN (SELECT COUNT(id)>0 FROM wm_loading_advice WHERE modified_type='INSERTED' AND referance_id=s.order_id  AND weighment_status='2') THEN (SELECT SUM(d.alt_s_quantity) FROM wm_loading_advice_itm_dtls d,wm_loading_advice w WHERE w.modified_type='INSERTED' AND d.modified_type =w.modified_type AND w.weighment_status='2' AND d.item_code = sd.item_code  AND d.packing =sd.packing AND d.advice_id = w.advice_id  AND s.order_id = w.referance_id) ELSE 0 END ) AS dispatchbag, ((sd.mat_wt)-((CASE WHEN (SELECT COUNT(id)>0 FROM wm_loading_advice WHERE modified_type='INSERTED' AND referance_id=s.order_id  AND weighment_status='2') THEN (SELECT SUM(d.mat_wt) FROM wm_loading_advice_itm_dtls d,wm_loading_advice w WHERE w.modified_type='INSERTED' AND d.modified_type =w.modified_type AND w.weighment_status='2' AND d.item_code = sd.item_code  AND d.packing =sd.packing AND   d.advice_id = w.advice_id  AND s.order_id = w.referance_id) ELSE 0 END))) AS pending, ((sd.squantity)-((CASE WHEN (SELECT COUNT(id)>0 FROM wm_loading_advice WHERE modified_type='INSERTED' AND referance_id=s.order_id  AND weighment_status='2') THEN (SELECT SUM(d.alt_s_quantity) FROM wm_loading_advice_itm_dtls d,wm_loading_advice w WHERE w.modified_type='INSERTED' AND d.modified_type =w.modified_type AND w.weighment_status='2' AND d.item_code = sd.item_code  AND d.packing =sd.packing AND   d.advice_id = w.advice_id  AND s.order_id = w.referance_id) ELSE 0 END))) AS pendingbags FROM sales_order s,sales_order_item_dtls sd,sales_order_broker_dtls b WHERE s.order_id = sd.order_id AND s.order_id = b.order_id AND s.modified_type='INSERTED' AND s.modified_type= sd.modified_type AND s.modified_type=  b.modified_type AND s.order_date>=:fromdate AND s.order_date<=:todate", nativeQuery=true)
	//@Query(value= "SELECT x1.order_id,x1.order_date,x1.order_no,x1.terminate,x1.ref_type,x1.partyname,x1.broker_name,x1.order_type,x1.item_name,x1.mat_wt,x1.bag,x1.price,x1.price_based_on,IFNULL(y1.mat_wt,0) AS dispatched_quantity,IFNULL(y1.alt_s_quantity,0) AS dispatched_bag,(x1.mat_wt-IFNULL(y1.mat_wt,0))AS pending_quantity,(x1.bag-IFNULL(y1.alt_s_quantity,0))AS pending_bag FROM (SELECT s1.order_id,s.order_date,s.order_no,s.terminate,s.ref_type,c.cp_name AS partyname,b.broker_name,s.order_type,s1.item_name,s1.mat_wt,s1.squantity AS bag,s1.price,s1.price_based_on FROM sales_order_item_dtls s1 LEFT JOIN sales_order s ON s1.order_id= s.order_id LEFT JOIN cust_bussiness_partner c ON c.cp_id =s.customer LEFT JOIN sales_order_broker_dtls b ON s.order_id = b.order_id WHERE s1.modified_type='INSERTED' AND s.modified_type='INSERTED' AND c.modified_type='INSERTED' AND b.modified_type='INSERTED')x1 LEFT JOIN(SELECT wmd.advice_id,wmd.advice_no,wmd.item_name,wmd.order_id,wmd.mat_wt,wmd.alt_s_quantity FROM wm_loading_advice_itm_dtls wmd LEFT JOIN wm_loading_advice wm ON wm.advice_id=wmd.advice_id WHERE wmd.modified_type='INSERTED' AND  wm.modified_type='INSERTED' AND wm.weighment_status=2 GROUP BY wmd.order_id,wmd.item_name)y1 ON x1.order_id=y1.order_id AND x1.item_name=y1.item_name WHERE x1.order_date>=:fromdate AND x1.order_date<=:todate AND x1.terminate=0", nativeQuery = true)
	@Query(value= "SELECT x1.order_id,x1.order_date,x1.order_no,x1.terminate,x1.ref_type,x1.partyname,x1.broker_name,x1.order_type,x1.item_name,x1.mat_wt,x1.bag,x1.price,x1.price_based_on,\r\n"
			+ "IFNULL(y1.mat_wt,0) AS dispatched_quantity,IFNULL(y1.alt_s_quantity,0) AS dispatched_bag,(x1.mat_wt-IFNULL(y1.mat_wt,0))AS pending_quantity,\r\n"
			+ "(x1.bag-IFNULL(y1.alt_s_quantity,0))AS pending_bag FROM (SELECT s1.order_id,s.order_date,s.order_no,s.terminate,s.ref_type,c.cp_name AS partyname,\r\n"
			+ "b.broker_name,s.order_type,s1.item_name,s1.mat_wt,s1.squantity AS bag,s1.price,s1.price_based_on \r\n"
			+ "FROM sales_order_item_dtls s1 \r\n"
			+ "LEFT JOIN sales_order s ON s1.order_id= s.order_id \r\n"
			+ "LEFT JOIN cust_bussiness_partner c ON c.cp_id =s.customer \r\n"
			+ "LEFT JOIN sales_order_broker_dtls b ON s.order_id = b.order_id \r\n"
			+ "WHERE s1.modified_type='INSERTED' AND s.modified_type='INSERTED' AND c.modified_type='INSERTED' AND b.modified_type='INSERTED')x1 \r\n"
			+ "LEFT JOIN(SELECT wmd.advice_id,wmd.advice_no,wmd.item_name,wmd.order_id,SUM(wmd.mat_wt) AS mat_wt,SUM(wmd.alt_s_quantity) AS alt_s_quantity FROM wm_loading_advice_itm_dtls wmd \r\n"
			+ "LEFT JOIN wm_loading_advice wm ON wm.advice_id=wmd.advice_id WHERE wmd.modified_type='INSERTED' AND  wm.modified_type='INSERTED' AND wm.weighment_status=2 AND wm.terminate='0'\r\n"
			+ "GROUP BY wmd.order_id,wmd.item_name)y1 ON x1.order_id=y1.order_id AND x1.item_name=y1.item_name \r\n"
			+ "WHERE x1.order_date>=:fromdate AND x1.order_date<=:todate AND x1.terminate=0", nativeQuery = true)  // Only Pending Qty
		//	+ "WHERE x1.order_date>=:fromdate AND x1.order_date<=:todate AND x1.terminate=0 AND(x1.bag-IFNULL(y1.alt_s_quantity,0))>0", nativeQuery = true)  // Only Pending Qty
	List<Map<String, Object>> getSalesOrderReport(@Param("fromdate") String fromdate, @Param("todate") String todate);
	
	@Query(value= "SELECT x1.order_id,x1.order_date,x1.order_no,x1.terminate,x1.ref_type,x1.partyname,x1.broker_name,x1.order_type,x1.item_name,x1.mat_wt,x1.bag,x1.price,x1.price_based_on,\r\n"
			+ "IFNULL(y1.mat_wt,0) AS dispatched_quantity,IFNULL(y1.alt_s_quantity,0) AS dispatched_bag,(x1.mat_wt-IFNULL(y1.mat_wt,0))AS pending_quantity,\r\n"
			+ "(x1.bag-IFNULL(y1.alt_s_quantity,0))AS pending_bag FROM (SELECT s1.order_id,s.order_date,s.order_no,s.terminate,s.ref_type,c.cp_name AS partyname,\r\n"
			+ "b.broker_name,s.order_type,s1.item_name,s1.mat_wt,s1.squantity AS bag,s1.price,s1.price_based_on \r\n"
			+ "FROM sales_order_item_dtls s1 \r\n"
			+ "LEFT JOIN sales_order s ON s1.order_id= s.order_id \r\n"
			+ "LEFT JOIN cust_bussiness_partner c ON c.cp_id =s.customer \r\n"
			+ "LEFT JOIN sales_order_broker_dtls b ON s.order_id = b.order_id \r\n"
			+ "WHERE s1.modified_type='INSERTED' AND s.modified_type='INSERTED' AND c.modified_type='INSERTED' AND b.modified_type='INSERTED')x1 \r\n"
			+ "LEFT JOIN(SELECT wmd.advice_id,wmd.advice_no,wmd.item_name,wmd.order_id,SUM(wmd.mat_wt) AS mat_wt,SUM(wmd.alt_s_quantity) AS alt_s_quantity FROM wm_loading_advice_itm_dtls wmd \r\n"
			+ "LEFT JOIN wm_loading_advice wm ON wm.advice_id=wmd.advice_id WHERE wmd.modified_type='INSERTED' AND  wm.modified_type='INSERTED' AND wm.weighment_status=2 AND wm.terminate='0'\r\n"
			+ "GROUP BY wmd.order_id,wmd.item_name)y1 ON x1.order_id=y1.order_id AND x1.item_name=y1.item_name \r\n"
			+ "WHERE x1.order_no=:orderno AND x1.terminate=0", nativeQuery = true)  // Only Pending Qty
	List<Map<String, Object>> getSalesOrderReportOrderWise(@Param("orderno") String  orderno);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order p SET p.cust_channel_list=:custid WHERE p.cust_channel = :channel_custid" )
    int changeChannelCustomerList(@Param("custid") String custid,@Param("channel_custid") String channel_custid);
	
	@Query(value= "SELECT t.* FROM sales_order_item_dtls_for_jobwork t WHERE t.modified_type='INSERTED' AND t.order_id=:order_id", nativeQuery=true)
	List<Map<String,Object>> getSalesOrdJobItemDtls(@Param("order_id") String order_id);
	
	@Query(value= "SELECT t.* FROM sales_order_item_dtls_for_jobwork_price t WHERE t.modified_type='INSERTED' AND t.order_id=:order_id", nativeQuery=true)
	List<Map<String,Object>> getSalesOrdTServiceItem(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Item_Dtls_for_jobwork w SET w.modified_type =:mstatus WHERE w.order_id = :order_id and w.modified_type='INSERTED'")
    int sales_OrderJobItemupdate(@Param("order_id") String order_id,@Param("mstatus") String mstatus);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Item_Dtls_for_jobwork_price w SET w.modified_type =:mstatus WHERE w.order_id = :order_id and w.modified_type='INSERTED'")
    int sales_OrderServiceItemupdate(@Param("order_id") String order_id,@Param("mstatus") String mstatus);
	
	@Query(value= "SELECT s.order_id as order_id,s.order_date as order_date,s.order_no as order_no,s.order_type as order_type,(SELECT cp_name FROM cust_bussiness_partner WHERE modified_type ='INSERTED' AND cp_id =:party) AS customer_name,s.we_uom as we_uom,j.job_price as rate from sales_order s,sales_order_item_dtls_for_jobwork_price j WHERE j.order_id=s.order_id and j.modified_type='INSERTED' and s.modified_type='INSERTED' and s.order_date<=:advdate and s.business_unit=:bunit and s.customer=:party and s.inv_type='INV00003' and s.terminate=0", nativeQuery=true)
	List<Map<String, Object>> findJobSalesOrders(@Param("bunit") String bunit, @Param("party") String party,@Param("advdate") String advdate);
	
	
	@Query(value= "SELECT * FROM `sales_order_item_dtls_for_jobwork_price` WHERE order_id=(SELECT order_id FROM channeldeliverycustomerjobwork WHERE delivery_cid=:challanid) and modified_type ='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> getSalesOrdTServiceItemdc(@Param("challanid") String challanid);
	
	@Query(value= "SELECT i.party,i.partyname,i.sale_order_id as order_id,i.order_date,i.order_no,i.challan_no,i.delivery_cid,i.challan_date FROM `salereturnapprovaljobwork` i,sales_order j WHERE i.sale_order_id=j.order_id AND i.party=:customer AND i.order_date<=:returndate AND j.`modified_type`='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> getSalesOrderReturnData(@Param("customer") String customer,@Param("returndate") String returndate);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order s SET s.terminate=1 , s.updated_by=:username WHERE  s.id =:id")
    int SalesOrderTerminate(@Param("id") long id,@Param("username") String username);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order s SET s.terminate=1 , s.updated_by=:username WHERE  s.referance_id =:referance_id")
    int salesOrderTerminationviaQuotation(@Param("referance_id") String referance_id,@Param("username") String username);
	
	//@Query(value= "SELECT d.inv_type_name AS inv_type_name,CASE WHEN (SELECT referance_id FROM sales_order WHERE modified_type='INSERTED' AND order_id=(SELECT referance_id FROM wm_loading_advice WHERE modified_type='INSERTED' AND advice_id=d.referance_id)) LIKE 'SO%' THEN 'Sales Order' ELSE 'Sales Quotation' END AS reftype,d.challan_date AS challan_date,d.challan_no AS challan_no,d.adviceno AS adviceno,d.partyname AS partyname,d.price_term AS price_term,d.date2 AS date2,d.grand_total AS grand_total,i.item_name AS item_name,i.packing_name AS packing_name,i.squantity AS squantity,i.suom AS suom,i.quantity AS quantity,i.uom AS uom,i.mat_wt AS mat_wt,i.price AS price,i.price_based_on AS price_based_on,i.amount AS amount,i.discount_rate AS discount_rate,i.discount_type AS discount_type,i.discount_amt AS discount_amt,i.tax_name AS tax_name,i.tax_rate AS tax_rate,i.tax_amt AS tax_amt,i.total_amt AS total_amt,w.own_uom_name AS own_uom_name,w.own_gross AS own_gross,w.own_tare AS own_tare,w.own_net AS own_net,t.vehicle_no AS vehicle_no FROM delivery_challan d,delivery_challan_weight_dtl w,delivery_challan_trans_info t,delivery_challan_item_dtls i WHERE d.`delivery_cid`=i.`delivery_cid` AND d.`delivery_cid`=w.`delivery_cid` AND d.`delivery_cid`=t.`delivery_cid` AND d.`modified_type`='INSERTED' AND d.challan_date>=:fromdate AND d.`challan_date`<=:todate", nativeQuery=true)
	//@Query(value="SELECT challan.inv_type_name,challan.challan_no,challan.adviceno,challan.partyname,challan.price_term,IFNULL(challan.date2,''),challan.grand_total,challan.delivery_cid,challan.challan_date,challan.item_name,challan.packing_name,challan.squantity,challan.suom,challan.quantity,challan.uom,challan.mat_wt,challan.price,challan.price_based_on,challan.amount,challan.discount_rate,challan.discount_type,challan.discount_amt,challan.tax_name,challan.tax_rate,challan.tax_amt,challan.total_amt,challan.own_uom_name,challan.own_gross,challan.own_tare,challan.own_net,challan.vehicle_no,advicedata.ref_type FROM ((SELECT c.inv_type_name,c.challan_no,c.adviceno,c.partyname,c.price_term,c.date2,c.grand_total,c.delivery_cid,c.challan_date,restdetails.item_name,restdetails.packing_name,restdetails.squantity,restdetails.suom,restdetails.quantity,restdetails.uom,restdetails.mat_wt,restdetails.price,restdetails.price_based_on,restdetails.amount,restdetails.discount_rate,restdetails.discount_type,restdetails.discount_amt,restdetails.tax_name,restdetails.tax_rate,restdetails.tax_amt,restdetails.total_amt,restdetails.own_uom_name,restdetails.own_gross,restdetails.own_tare,restdetails.own_net,restdetails.vehicle_no FROM (`delivery_challan` c LEFT JOIN  (SELECT d.item_name,d.packing_name,d.squantity,d.suom,d.quantity,d.uom,d.mat_wt,d.price,d.price_based_on,d.amount,d.discount_rate,d.discount_type,d.discount_amt,d.tax_name,d.tax_rate,d.tax_amt,d.total_amt,d.delivery_cid,details.own_uom_name,details.own_gross,details.own_tare,details.own_net,details.vehicle_no FROM (`delivery_challan_item_dtls` d LEFT JOIN (SELECT w.own_uom_name,w.own_gross,w.own_tare,w.own_net,w.delivery_cid,w.modified_type,t.vehicle_no FROM (`delivery_challan_weight_dtl` w LEFT JOIN (SELECT vehicle_no,delivery_cid,modified_type FROM `delivery_challan_trans_info`)t ON ((w.delivery_cid = t.delivery_cid) AND  (w.modified_type = t.modified_type))) WHERE w.`modified_type` = 'INSERTED') details ON ((d.delivery_cid = details.delivery_cid) AND (d.modified_type = details.modified_type))) WHERE d.`modified_type` = 'INSERTED') restdetails ON (c.delivery_cid = restdetails.delivery_cid)) WHERE c.`modified_type` = 'INSERTED') challan  JOIN (SELECT s.ref_type,s.order_id,s.modified_type,a.adviceno FROM (sales_order s LEFT JOIN wm_loading_advice a ON ((`a`.`referance_id` = `s`.`order_id`) AND (a.`modified_type` = s.`modified_type`))) WHERE `s`.`modified_type` = 'INSERTED') advicedata ON(advicedata.adviceno=challan.adviceno)) WHERE challan.challan_date>=:fromdate AND challan.`challan_date`<=:todate",nativeQuery=true)
	@Query(value= "SELECT d.inv_type_name AS inv_type_name,CASE WHEN so.referance_id LIKE 'SO%' THEN 'Sales Order' ELSE 'Sales Quotation' END AS reftype,d.challan_date AS challan_date,d.challan_no AS challan_no,d.adviceno AS adviceno,d.partyname AS partyname,d.price_term AS price_term,d.date2 AS date2,d.grand_total AS grand_total,i.item_name AS item_name,i.packing_name AS packing_name,i.squantity AS squantity,i.suom AS suom,i.quantity AS quantity,i.uom AS uom,i.mat_wt AS mat_wt,i.price AS price,i.price_based_on AS price_based_on,i.amount AS amount,i.discount_rate AS discount_rate,i.discount_type AS discount_type,i.discount_amt AS discount_amt,i.tax_name AS tax_name,i.tax_rate AS tax_rate,i.tax_amt AS tax_amt,i.total_amt AS total_amt,w.own_uom_name AS own_uom_name,w.own_gross AS own_gross,w.own_tare AS own_tare,w.own_net AS own_net,t.vehicle_no AS vehicle_no,wg.wgment_no\r\n"
			+ "FROM delivery_challan d\r\n"
			+ "LEFT JOIN delivery_challan_weight_dtl w ON d.delivery_cid=w.delivery_cid\r\n"
			+ "LEFT JOIN delivery_challan_trans_info t ON d.delivery_cid=t.delivery_cid\r\n"
			+ "LEFT JOIN delivery_challan_item_dtls i ON d.delivery_cid=i.delivery_cid\r\n"
			+ "LEFT JOIN wm_loading_advice wl ON wl.advice_id=d.referance_id\r\n"
			+ "LEFT JOIN sales_order so ON so.order_id=wl.referance_id\r\n"
			+ "LEFT JOIN wm_unload_wgmnt wg ON wg.wgment_id=wl.weighment_id\r\n"
			+ "WHERE d.modified_type='INSERTED' AND w.modified_type='INSERTED' AND t.modified_type='INSERTED' AND i.modified_type='INSERTED' AND so.modified_type='INSERTED' AND wl.modified_type='INSERTED' AND d.modified_type=w.modified_type AND d.modified_type=t.modified_type AND d.modified_type=i.modified_type AND d.modified_type=so.modified_type AND d.modified_type=wl.modified_type AND d.challan_date>=:fromdate AND d.challan_date<=:todate AND wg.weight2 ='weight2' ", nativeQuery=true)
	List<Map<String, Object>> getTrialdata(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value= "SELECT w.wgment_date,w.wgment_for,d.advice_no,w.totalweight,w.wgment_no,w.gross_weight,w.gw_date,w.gw_time,w.gw_remarks,w.tare_weight,w.tw_date,w.tw_time,w.tw_remarks,w.net_weight,w.vehicle_no,w.tarebags,w.wgment_charge,w.wgment_rs,w.digital_weight,w.digital_weight1,w.remarks,\r\n"
			+ "CASE WHEN ug.description IS NULL THEN 'NA' ELSE ug.description END AS gw_unit,CASE WHEN ut.description IS NULL THEN 'NA' ELSE ut.description END AS tw_unit,\r\n"
			+ "CASE WHEN un.description IS NULL THEN 'NA' ELSE un.description END AS nw_unit,CASE WHEN c.cp_name IS NULL THEN 'NA' ELSE c.cp_name END AS customername,CASE WHEN s.bp_name IS NULL THEN 'NA' ELSE s.bp_name END AS suppliername\r\n"
			+ "FROM wm_unload_wgmnt w\r\n"
			+ "LEFT JOIN wm_unload_wgmnt_dtls d ON w.wgment_id = d.wgment_id\r\n"
			+ "LEFT JOIN cust_bussiness_partner c ON c.cp_id=d.customer\r\n"
			+ "LEFT JOIN supp_bussiness_partner s ON s.bp_id=d.supplier\r\n"
			+ "LEFT JOIN custom_uom_master ug ON ug.customuom_id = w.gw_unit \r\n"
			+ "LEFT JOIN custom_uom_master ut ON ut.customuom_id = w.tw_unit \r\n"
			+ "LEFT JOIN custom_uom_master un ON un.customuom_id = w.nw_unit \r\n"
			+ "WHERE w.wgment_date>=:fromdate AND w.wgment_date<=:todate AND w.weight2 ='weight2' AND w.modified_type = 'INSERTED' AND d.modified_type = 'INSERTED' AND s.modified_type = 'INSERTED' AND c.modified_type = 'INSERTED' GROUP BY w.wgment_id,d.wgment_id", nativeQuery=true)
	List<Map<String, Object>> getWeighmentReportForAnujSir(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value= "SELECT * FROM loading_sales_order_jobworks_w_tolerance l WHERE l.`order_id`=:order_id AND l.`job_item`=:item_id AND l.`job_packing`=:item_code", nativeQuery=true)
	List<Map<String, Object>> getSOjwRestQty(@Param("order_id") String order_id,@Param("item_id") String item_id,@Param("item_code") String item_code);
	
	@Query(value= "SELECT s.order_id,s.order_no FROM sales_order s WHERE s.modified_type='INSERTED' AND s.inv_type='INV00003' AND s.fin_year=:fin_year AND s.terminate=0", nativeQuery=true)
	List<Map<String, Object>> getSalesOrderJWList(@Param("fin_year") String fin_year);
	
	@Query(value= "SELECT s.order_id,s.order_no FROM sales_order s WHERE s.modified_type='INSERTED' AND s.inv_type!='INV00003' AND s.fin_year=:fin_year AND s.terminate=0", nativeQuery=true)
	List<Map<String, Object>> getSalesOrderList(@Param("fin_year") String fin_year);
}
