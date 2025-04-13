package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;

import java.util.List;
import java.util.Map;

public interface Delivery_challanRepository extends JpaRepository<Delivery_challan, Long>{
	
	@Query("select COUNT(id) from Delivery_challan")
	String countId();
	
	@Query("select COUNT(id) from Delivery_challan where fin_year =:fin_year and inv_type =:inv_type")
	String countDlvChln(@Param("fin_year") String fin_year,@Param("inv_type") String inv_type);
	
	@Query(value = "select COUNT(d.id) from delivery_challan d left join cust_bussiness_partner c on d.party=c.cp_id and c.modified_type='INSERTED' where d.fin_year =:fin_year and c.group_type='CG00019'", nativeQuery=true)
	String countDlvChln4Defence(@Param("fin_year") String fin_year);
	
	@Query( "select s from Delivery_challan s where s.modified_type ='INSERTED' and s.delivery_cid =:delivery_cid ")
	Delivery_challan getDeliveryChallanDtls(@Param("delivery_cid") String delivery_cid);

	@Query( value="select * from delivery_challan where modified_type ='INSERTED' and delivery_cid =:delivery_cid ",nativeQuery=true)
	Map<String, Object> getDeliveryChallanDtlsnew(@Param("delivery_cid") String delivery_cid);
	
	@Query( value="select d.challan_no,d.challan_date,d.delivery_cid,d.inv_type,CASE WHEN d.referance_id LIKE'%WLA%' THEN (SELECT order_no FROM sales_order WHERE order_id=(SELECT referance_id FROM wm_loading_advice WHERE advice_id=d.referance_id) AND modified_type='INSERTED') WHEN d.referance_id LIKE'%SO%' THEN (SELECT order_no FROM sales_order WHERE order_id=d.referance_id AND modified_type='INSERTED') END AS salesorderno,CASE WHEN d.referance_id LIKE'%WLA%' THEN (SELECT order_date FROM sales_order WHERE order_id=(SELECT referance_id FROM wm_loading_advice WHERE advice_id=d.referance_id) AND modified_type='INSERTED') WHEN d.referance_id LIKE'%SO%' THEN (SELECT order_date FROM sales_order WHERE order_id=d.referance_id AND modified_type='INSERTED') END AS salesorderdate from delivery_challan d where d.modified_type ='INSERTED' and d.delivery_cid =:delivery_cid ",nativeQuery=true)
	Map<String, Object> getDeliveryChallanDtlsFast(@Param("delivery_cid") String delivery_cid);
	
	//@Query( "select d from Delivery_challan d where d.modified_type ='INSERTED' and d.invoicestatus ='No' and d.inv_type =:invtype and d.party =:party and d.company_id =:comp and d.challan_date <=:invdate")
	//@Query( "select d from Delivery_challan d where d.modified_type ='INSERTED' and d.invoicestatus ='Yes' and d.inv_type =:invtype and d.sales_returnstatus ='NO' and d.party =:party and d.company_id =:comp and d.challan_date <=:invdate")
	@Query( "select d from Delivery_challan d where d.modified_type ='INSERTED' and d.invoicestatus ='No' and d.inv_type =:invtype and d.sales_returnstatus ='NO' and d.party =:party and d.company_id =:comp and d.challan_date <=:invdate")
	List<Delivery_challan> deliveryChallanList(@Param("invtype") String invtype,@Param("party") String party,@Param("invdate") String invdate,@Param("comp") String comp);
	
	//@Query( value="select * from delivery_challan d where d.modified_type ='INSERTED' and d.invoicestatus ='No' and d.inv_type =:invtype and d.sales_returnstatus ='NO' and d.party =:party and d.company_id =:comp and d.challan_date <=:invdate",nativeQuery=true)
	@Query( value="select * from channeldeliverycustomer d where d.modified_type ='INSERTED' and d.invoicestatus ='No' and d.inv_type =:invtype and d.sales_returnstatus ='NO' and d.party LIKE %:party% and d.company_id =:comp and d.challan_date <=:invdate",nativeQuery=true)
	List<Map<String,Object>>  deliveryChallanListnew(@Param("invtype") String invtype,@Param("party") String party,@Param("invdate") String invdate,@Param("comp") String comp);
	
	@Query( value="select * from channel_grn_delivery_customer d where d.modified_type ='INSERTED' and d.invoicestatus ='No' and d.inv_type =:invtype and d.sales_returnstatus ='NO' and d.party LIKE %:party% and d.company_id =:comp and d.challan_date <=:invdate",nativeQuery=true)
	List<Map<String,Object>>  deliveryChallanThroughGrnList(@Param("invtype") String invtype,@Param("party") String party,@Param("invdate") String invdate,@Param("comp") String comp);
	
	
	@Query( value="select * from channeldeliverycustomerjobwork d where d.modified_type ='INSERTED' and d.invoicestatus ='No' and d.inv_type =:invtype and d.sales_returnstatus ='NO' and d.party LIKE %:party% and d.company_id =:comp and d.challan_date <=:invdate",nativeQuery=true)
	List<Map<String,Object>>  deliveryChallanListnewjobwork(@Param("invtype") String invtype,@Param("party") String party,@Param("invdate") String invdate,@Param("comp") String comp);
	
	
	
	@Query( "select d from Delivery_challan d where d.modified_type ='INSERTED' and d.invoicestatus ='Yes' and d.inv_type =:invtype and d.sales_returnstatus ='NO' and d.party =:party and d.company_id =:comp and d.challan_date <=:invdate")
	List<Delivery_challan> deliveryChallanListreturn(@Param("invtype") String invtype,@Param("party") String party,@Param("invdate") String invdate,@Param("comp") String comp);
	
	
	@Query( value="select delivery_cid from salereturnapproval where inv_type=:invtype and party=:party and challan_date<=:invdate and difference!=0",nativeQuery=true)
	List<String> getdeliverchallanreturnapproval(@Param("invtype") String invtype,@Param("party") String party,@Param("invdate") String invdate);
	
			
	//@Query( "select d from Delivery_challan d where d.modified_type ='INSERTED' and d.invoicestatus ='No' and d.party =:party and d.company_id =:comp and d.challan_date <=:invdate")
	//@Query( "select d from Delivery_challan d where d.modified_type ='INSERTED' and d.invoicestatus ='Yes' and d.party =:party and d.sales_returnstatus ='NO' and d.company_id =:comp and d.challan_date <=:invdate")
	@Query( "select d from Delivery_challan d where d.modified_type ='INSERTED' and d.invoicestatus ='No' and d.party =:party and d.sales_returnstatus ='NO' and d.company_id =:comp and d.challan_date <=:invdate")
	List<Delivery_challan> getDelvChallansApp(@Param("party") String party,@Param("invdate") String invdate,@Param("comp") String comp);
	
	@Query(value= "SELECT delivery_cid from salereturnapproval where party=:party and challan_date<=:invdate and difference!=0", nativeQuery=true)
	List<String> getDelvChallansReturnApp(@Param("party") String party,@Param("invdate") String invdate);
	
	@Query(value="SELECT challan_no,delivery_cid,challan_date,party,partyname from delivery_challan where delivery_cid=:delvid and modified_type = 'INSERTED'", nativeQuery=true)
	Map<String,Object> getDelvChallanReturnList(@Param("delvid") String delvid);
	
	@Query( "select d from Delivery_challan d where d.modified_type ='INSERTED' and d.invoicestatus ='Yes' and d.party =:party and d.sales_returnstatus ='NO' and d.company_id =:comp and d.challan_date <=:invdate")
	List<Delivery_challan> getDelvChallansAppreturn(@Param("party") String party,@Param("invdate") String invdate,@Param("comp") String comp);
	
	@Query(value="select * from delivery_challan d where d.modified_type ='INSERTED' and d.invoicestatus ='Yes' and d.party =:party and d.sales_returnstatus ='NO' and d.company_id =:comp and d.challan_date <=:invdate", nativeQuery=true)
	List<Map<String,Object>> getDelvChallansAppreturnnew(@Param("party") String party,@Param("invdate") String invdate,@Param("comp") String comp);
	
	@Query( "select s from Delivery_challan s where s.modified_type ='INSERTED' ORDER BY s.delivery_cid DESC ")
	List<Delivery_challan> findDeliveryChallans();
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Delivery_challan d SET d.invoicestatus =:istatus,d.challan_status=:challan_status,d.sales_invoice_id=:sales_invoice_id WHERE d.delivery_cid = :delivery_cid" )
    int updateDelvChallanStatus(@Param("delivery_cid") String delivery_cid,@Param("istatus") String istatus,@Param("challan_status") String challan_status,@Param("sales_invoice_id") String sales_invoice_id);
	
	@Procedure(name = "Delivery_challan.callSoldItemStockDelivery")
	int callSoldItemStockDelivery(@Param("advitemqty") String advitemqty,@Param("advpackqty") String advpackqty,@Param("advitemcode") String advitemcode,
			@Param("advpackcode") String advpackcode,@Param("bunit") String bunit,@Param("finyear") String finyear);
	
	@Procedure(name = "Delivery_challan.callSoldItemStockDeliveryReverse")
	int callSoldItemStockDeliveryReverse(@Param("radvitemqty") String radvitemqty,@Param("radvpackqty") String radvpackqty,@Param("radvitemcode") String radvitemcode,
			@Param("radvpackcode") String radvpackcode,@Param("bunit") String bunit,@Param("finyear") String finyear);
	
	@Query(value = "{call checkDeliveryChallanUsage(:delvid)}", nativeQuery = true)
	String checkDeliveryChallanUsage(@Param("delvid") String delvid);
	
	//updatedeliverychallan_salesreturn
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan d SET d.salereturn_id =:salereturn_id , d.sales_returnstatus='YES' WHERE d.delivery_cid = :delivery_cid" )
    int updatedeliverychallan_salesreturn(@Param("delivery_cid") String delivery_cid,@Param("salereturn_id") String salereturn_id);
	
	@Query( "select s from Delivery_challan s where s.modified_type ='INSERTED' and s.referance_id = :referance_id")
	Delivery_challan getloadingdeliverychallan(@Param("referance_id") String referance_id );
	
	@Query( "select s from Delivery_challan_Item_Dtls s where s.modified_type ='INSERTED' and s.delivery_cid=:delivery_cid")
	List<Delivery_challan_Item_Dtls> getdeliverychallanitemlist(@Param("delivery_cid") String delivery_cid );
	
	@Query(value="select * from delivery_challan_item_dtls_for_jobwork s where s.modified_type ='INSERTED' and s.delivery_cid IN (:delivery_cid)",nativeQuery=true)
	List<Map<String,Object>> getdeliverchallanjobitemlist(@Param("delivery_cid") List<String> delivery_cid);
	
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Delivery_challan pl where pl.modified_type != 'DELETED' and pl.delivery_cid=:delivery_cid and pl.sales_returnstatus ='NO'")
	Boolean checkDelivery_challanRETURN(@Param("delivery_cid") String delivery_cid);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Delivery_challan pl where pl.modified_type != 'DELETED' and pl.referance_id=:ref_id and pl.sales_returnstatus ='NO'")
	Boolean checkmultipledelvChallan(@Param("ref_id") String ref_id);
	
	@Query( "select d from Delivery_challan d where d.modified_type ='INSERTED' and d.delivery_cid=:delivery_cid ")
	Delivery_challan deliveryChallanListUpdate(@Param("delivery_cid") String delivery_cid);
	
	@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Delivery_challan> getsearchdata(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("customername") String customername,@Param("finyear") String finyear);
	
	@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Map<String,Object>> getsearchdataFast(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("customername") String customername,@Param("finyear") String finyear);
	
	
	@Query("select s from Delivery_challan s where s.modified_type = 'INSERTED' and s.challan_date=:currDate ")
	List<Delivery_challan> getDeliveryChallanDataList(@Param("currDate") String currDate);
	
	@Query("select s from Delivery_challan s where s.modified_type = 'INSERTED' and s.challan_date=:currDate and s.party =:party")
	List<Delivery_challan> getDeliveryChallanDataListnew(@Param("currDate") String currDate,@Param("party") String party);
	
	@Query("select s from Delivery_challan s where s.modified_type = 'INSERTED' and s.challan_date=:currDate and id!=:id and s.party =:party")//party
	List<Delivery_challan> getDeliveryChallanDataListupdate(@Param("currDate") String currDate,@Param("id") long id,@Param("party") String party);
	
	@Query(value="SELECT d.challan_date,d.challan_no,d.partyname,SUM(e.squantity) AS packing,SUM(e.quantity) AS item,f.own_gross,f.own_tare,f.own_net,CASE WHEN g.trans_borne_by IS NULL THEN '' ELSE g.trans_borne_by END AS modeoftrans,CASE WHEN g.mode_of_trans IS NULL THEN '' ELSE g.mode_of_trans END AS mode_of_trans,CASE WHEN g.transporter_name IS NULL THEN '' ELSE g.transporter_name END AS transname,g.vehicle_no,g.freight_amt,g.adv_paid,(f.own_net*100) AS kg FROM delivery_challan d,delivery_challan_item_dtls e,delivery_challan_weight_dtl f,delivery_challan_trans_info g WHERE d.modified_type='INSERTED' AND d.delivery_cid=e.delivery_cid AND d.modified_type=e.modified_type AND d.delivery_cid=f.delivery_cid AND d.modified_type=f.modified_type AND d.delivery_cid=g.delivery_cid AND d.modified_type=g.modified_type and d.challan_date>=:fromdate and d.challan_date<=:todate GROUP BY d.delivery_cid" ,nativeQuery=true)
	List<Object[]> getChallanPerTransportReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value="SELECT d.challan_date,d.challan_no,d.partyname,SUM(e.squantity) AS packing,SUM(e.quantity) AS item,f.own_gross,f.own_tare,f.own_net,CASE WHEN g.trans_borne_by IS NULL THEN '' ELSE g.trans_borne_by END AS modeoftrans,CASE WHEN g.mode_of_trans IS NULL THEN '' ELSE g.mode_of_trans END AS mode_of_trans,CASE WHEN g.transporter_name IS NULL THEN '' ELSE g.transporter_name END AS transname,g.vehicle_no,g.freight_amt,g.adv_paid,(f.own_net*100) AS kg FROM delivery_challan d,delivery_challan_item_dtls e,delivery_challan_weight_dtl f,delivery_challan_trans_info g WHERE d.modified_type='INSERTED' AND d.delivery_cid=e.delivery_cid AND d.modified_type=e.modified_type AND d.delivery_cid=f.delivery_cid AND d.modified_type=f.modified_type AND d.delivery_cid=g.delivery_cid AND d.modified_type=g.modified_type and d.challan_date>=:fromdate and d.challan_date<=:todate and g.trans_borne_by=:transborneby GROUP BY d.delivery_cid" ,nativeQuery=true)
	List<Object[]> getChallanPerTransportModeReport(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("transborneby") String transborneby);
	
	@Query(value ="select COUNT(id) from delivery_challan where challan_date=:currDate and  party=:party  ",nativeQuery=true)
	int countIdbydate(@Param("currDate") String currDate,@Param("party") String party);
	
	@Query(value ="select COUNT(id) from delivery_challan where challan_date=:currDate and id !=:id and party=:party",nativeQuery=true)
	int countIdbydateUPDATE(@Param("currDate") String currDate,@Param("id") long id,@Param("party") String party);
	
	@Query(value ="SELECT CASE  WHEN SUM(w.total_amt) IS NOT NULL THEN SUM(w.total_amt) ELSE '0' END AS totalamount FROM `sales_order_terms_con` s ,`wm_loading_advice_itm_dtls` w WHERE  w.modified_type='INSERTED' AND w.advice_id=:ref_id AND w.modified_type=s.modified_type AND s.`order_id` =w.`order_id` AND s.`payment_mode`='Cash' ",nativeQuery=true)
	double gettotalamount(@Param("ref_id") String ref_id);
	
	
	//SELECT COUNT(w.id) FROM `sales_order_terms_con` s ,`wm_loading_advice_itm_dtls` w WHERE  w.modified_type='INSERTED' AND w.advice_id='WLA03913' AND w.modified_type=s.modified_type AND s.`order_id` =w.order_id AND s.`payment_mode`='Cash'
	@Query(value ="SELECT COUNT(w.id) FROM `sales_order_terms_con` s ,`wm_loading_advice_itm_dtls` w WHERE  w.modified_type='INSERTED' AND w.advice_id=:advice_id AND w.modified_type=s.modified_type AND s.`order_id` =w.order_id AND s.`payment_mode`='Cash'",nativeQuery=true)
	int countIdbycheckpaymentmode(@Param("advice_id") String advice_id);
	
	@Query(value= "select d.id as id,d.challan_date as challan_date,d.challanno as challanno,d.partyname as partyname,d.inv_type_name as inv_type_name,d.ref_type as ref_type,d.price_term as price_term,d.delivery_cid as delivery_cid,d.referance_id from delivery_challan d where d.modified_type = 'INSERTED' and d.challan_date =:currDate and d.fin_year =:finyear ", nativeQuery=true)
	//@Query(value= "SELECT d.id AS id,d.challan_date AS challan_date,d.challanno AS challanno,d.partyname AS partyname,d.inv_type_name AS inv_type_name,d.ref_type AS ref_type,d.price_term AS price_term,d.delivery_cid AS delivery_cid,t.trans_borne_by AS trans_borne_by FROM delivery_challan d,delivery_challan_trans_info t WHERE d.modified_type = 'INSERTED' AND t.modified_type='INSERTED' AND d.modified_type=t.modified_type AND d.delivery_cid=t.delivery_cid AND d.challan_date =:currDate AND d.fin_year =:finyear", nativeQuery=true)
	List<Map<String,Object>> getDeliveryChallanFastList(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
	
	@Query(value="SELECT d.challanno AS challanno,d.challan_date AS challan_date,CASE WHEN d.sales_invoice_id = 'NA' THEN 'NA' ELSE (SELECT invoice_no FROM sales_invoice WHERE modified_type='INSERTED' AND invoice_id=d.sales_invoice_id ) END AS invoiceno,CASE WHEN d.sales_invoice_id = 'NA' THEN 'NA' ELSE (SELECT invoice_date FROM sales_invoice WHERE modified_type='INSERTED' AND invoice_id=d.sales_invoice_id ) END AS invoice_date,d.challan_status AS challan_status,d.inv_type_name AS inv_type_name FROM  delivery_challan d WHERE d.modified_type='INSERTED' AND d.challan_date>=:fromdate AND d.challan_date<=:todate ",nativeQuery=true)
	List<Map<String,Object>> getchallanReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	//@Query(value="SELECT d.challan_date,d.challan_no,d.partyname,(f.own_gross*100) AS own_gross,(f.own_tare*100) AS own_tare,(f.own_net*100) AS own_net,CASE WHEN g.transporter_name IS NULL THEN 'NA' WHEN g.transporter_name = '0' THEN 'NA' ELSE g.transporter_name END AS transname,g.vehicle_no,g.adv_paid,f.own_slip_no,CASE WHEN (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=d.business_unit) IS NULL THEN '' ELSE (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=d.business_unit) END AS bunitname,(SUM(e.quantity)*100) as quantity,SUM(e.squantity) as squantity,CASE WHEN (SELECT id FROM sales_transport WHERE challanno=d.challanno and modified_type='INSERTED') IS NULL THEN '0' ELSE (SELECT id FROM sales_transport WHERE challanno=d.challanno and modified_type='INSERTED') END AS id,d.referance_id AS referance_id, d.delivery_cid AS delivery_cid,(SELECT SUM(di.total_amt)/SUM(di.quantity) FROM delivery_challan_item_dtls di WHERE di.modified_type='INSERTED' AND di.delivery_cid=d.delivery_cid) AS avg_price FROM delivery_challan d,delivery_challan_item_dtls e,delivery_challan_weight_dtl f,delivery_challan_trans_info g WHERE d.modified_type='INSERTED' AND d.delivery_cid=e.delivery_cid AND d.modified_type=e.modified_type AND d.delivery_cid=f.delivery_cid AND d.modified_type=f.modified_type AND d.delivery_cid=g.delivery_cid AND d.modified_type=g.modified_type and d.challan_date>=:fromdate and d.challan_date<=:todate and d.business_unit=:business_unit and d.inv_type=:inv_type and g.trans_borne_by=:trans_type GROUP BY d.delivery_cid" ,nativeQuery=true)
	@Query(value="SELECT d.challan_date,d.challan_no,d.partyname,(f.own_gross*100) AS own_gross,(f.own_tare*100) AS own_tare,(f.own_net*100) AS own_net,CASE WHEN g.transporter_name IS NULL THEN 'NA' WHEN g.transporter_name = '0' THEN 'NA' ELSE g.transporter_name END AS transname,g.vehicle_no,g.adv_paid,f.own_slip_no,CASE WHEN (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=d.business_unit) IS NULL THEN '' ELSE (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=d.business_unit) END AS bunitname,(SUM(e.quantity)*100) as quantity,SUM(e.squantity) as squantity,CASE WHEN (SELECT id FROM sales_transport WHERE challanno=d.challanno and modified_type='INSERTED') IS NULL THEN '0' ELSE (SELECT id FROM sales_transport WHERE challanno=d.challanno and modified_type='INSERTED') END AS id,d.referance_id AS referance_id, d.delivery_cid AS delivery_cid,(SELECT SUM(di.total_amt)/SUM(di.quantity) FROM delivery_challan_item_dtls di WHERE di.modified_type='INSERTED' AND di.delivery_cid=d.delivery_cid) AS avg_price,SUM(e.quantity) AS itemqty,g.transport_rate as transport_rate,g.transportchargesadd as transportchargesadd FROM delivery_challan d,delivery_challan_item_dtls e,delivery_challan_weight_dtl f,delivery_challan_trans_info g WHERE d.modified_type='INSERTED' AND d.delivery_cid=e.delivery_cid AND d.modified_type=e.modified_type AND d.delivery_cid=f.delivery_cid AND d.modified_type=f.modified_type AND d.delivery_cid=g.delivery_cid AND d.modified_type=g.modified_type and d.challan_date>=:fromdate and d.challan_date<=:todate and d.business_unit=:business_unit and d.inv_type=:inv_type and g.trans_borne_by=:trans_type GROUP BY d.delivery_cid" ,nativeQuery=true)

	List<Map<String, Object>> getChallanTransportModeReport(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("inv_type") String inv_type,@Param("trans_type") String trans_type);
	
	@Query(value="SELECT d.challan_date,d.challan_no,d.partyname,(f.own_gross*100) AS own_gross,(f.own_tare*100) AS own_tare,(f.own_net*100) AS own_net,CASE WHEN g.transporter_name IS NULL THEN 'NA' WHEN g.transporter_name = '0' THEN 'NA' ELSE g.transporter_name END AS transname,g.vehicle_no,g.adv_paid,f.own_slip_no,CASE WHEN (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=d.business_unit) IS NULL THEN '' ELSE (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=d.business_unit) END AS bunitname,(SUM(e.quantity)*100) as quantity,SUM(e.squantity) as squantity,CASE WHEN (SELECT id FROM sales_transport WHERE challanno=d.challanno and modified_type='INSERTED') IS NULL THEN '0' ELSE (SELECT id FROM sales_transport WHERE challanno=d.challanno and modified_type='INSERTED') END AS id,d.referance_id AS referance_id, d.delivery_cid AS delivery_cid,(SELECT SUM(di.total_amt)/SUM(di.quantity) FROM delivery_challan_item_dtls di WHERE di.modified_type='INSERTED' AND di.delivery_cid=d.delivery_cid) AS avg_price,SUM(e.quantity) AS itemqty,g.transport_rate as transport_rate,g.transportchargesadd as transportchargesadd FROM delivery_challan d,delivery_challan_item_dtls e,delivery_challan_weight_dtl f,delivery_challan_trans_info g WHERE d.modified_type='INSERTED' AND d.delivery_cid=e.delivery_cid AND d.modified_type=e.modified_type AND d.delivery_cid=f.delivery_cid AND d.modified_type=f.modified_type AND d.delivery_cid=g.delivery_cid AND d.modified_type=g.modified_type and d.challan_date>=:fromdate and d.challan_date<=:todate and d.business_unit=:business_unit and d.inv_type=:inv_type and g.trans_borne_by=:trans_type AND g.trans_code=:transporter_code GROUP BY d.delivery_cid" ,nativeQuery=true)
	List<Map<String, Object>> getChallanTransportModeReportwithTrans(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("inv_type") String inv_type,@Param("trans_type") String trans_type,@Param("transporter_code") String transporter_code);

	
	@Query(value="SELECT d.challan_date,d.challan_no,d.partyname,(f.own_gross*100) AS own_gross,(f.own_tare*100) AS own_tare,(f.own_net*100) AS own_net,CASE WHEN g.transporter_name IS NULL THEN 'NA' WHEN g.transporter_name = '0' THEN 'NA' ELSE g.transporter_name END AS transname,g.vehicle_no,g.adv_paid,f.own_slip_no,CASE WHEN (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=d.business_unit) IS NULL THEN '' ELSE (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=d.business_unit) END AS bunitname,(SUM(e.item_qty)*100) AS quantity,SUM(e.pack_qty) AS squantity,CASE WHEN (SELECT id FROM sales_transport WHERE challanno=d.challanno AND modified_type='INSERTED') IS NULL THEN '0' ELSE (SELECT id FROM sales_transport WHERE challanno=d.challanno AND modified_type='INSERTED') END AS id,d.referance_id AS referance_id, d.delivery_cid AS delivery_cid,(SELECT (SELECT SUM(tax_value) FROM sales_order_item_dtls_for_jobwork_price WHERE modified_type='INSERTED' AND order_id=(SELECT referance_id FROM wm_loading_advice WHERE modified_type='INSERTED' AND advice_id=d.referance_id) )/SUM(di.item_qty) FROM delivery_challan_item_dtls_for_jobwork di WHERE di.modified_type='INSERTED' AND di.delivery_cid=d.delivery_cid) AS avg_price,SUM(e.item_qty) AS itemqty,g.transport_rate AS transport_rate,g.transportchargesadd AS transportchargesadd FROM delivery_challan d,delivery_challan_item_dtls_for_jobwork e,delivery_challan_weight_dtl f,delivery_challan_trans_info g WHERE d.modified_type='INSERTED'  AND d.modified_type=e.modified_type AND d.delivery_cid=e.delivery_cid AND d.delivery_cid=f.delivery_cid AND d.modified_type=f.modified_type AND d.delivery_cid=g.delivery_cid AND d.modified_type=g.modified_type AND d.challan_date>=:fromdate AND d.challan_date<=:todate AND d.business_unit=:business_unit AND d.inv_type=:inv_type AND g.trans_borne_by=:trans_type GROUP BY d.delivery_cid" ,nativeQuery=true)
	List<Map<String, Object>> getChallanTransportModeReportJW(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("inv_type") String inv_type,@Param("trans_type") String trans_type);

	@Query(value="SELECT d.challan_date,d.challan_no,d.partyname,(f.own_gross*100) AS own_gross,(f.own_tare*100) AS own_tare,(f.own_net*100) AS own_net,CASE WHEN g.transporter_name IS NULL THEN 'NA' WHEN g.transporter_name = '0' THEN 'NA' ELSE g.transporter_name END AS transname,g.vehicle_no,g.adv_paid,f.own_slip_no,CASE WHEN (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=d.business_unit) IS NULL THEN '' ELSE (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=d.business_unit) END AS bunitname,(SUM(e.item_qty)*100) AS quantity,SUM(e.pack_qty) AS squantity,CASE WHEN (SELECT id FROM sales_transport WHERE challanno=d.challanno AND modified_type='INSERTED') IS NULL THEN '0' ELSE (SELECT id FROM sales_transport WHERE challanno=d.challanno AND modified_type='INSERTED') END AS id,d.referance_id AS referance_id, d.delivery_cid AS delivery_cid,(SELECT (SELECT SUM(tax_value) FROM sales_order_item_dtls_for_jobwork_price WHERE modified_type='INSERTED' AND order_id=(SELECT referance_id FROM wm_loading_advice WHERE modified_type='INSERTED' AND advice_id=d.referance_id) )/SUM(di.item_qty) FROM delivery_challan_item_dtls_for_jobwork di WHERE di.modified_type='INSERTED' AND di.delivery_cid=d.delivery_cid) AS avg_price,SUM(e.item_qty) AS itemqty,g.transport_rate AS transport_rate,g.transportchargesadd AS transportchargesadd FROM delivery_challan d,delivery_challan_item_dtls_for_jobwork e,delivery_challan_weight_dtl f,delivery_challan_trans_info g WHERE d.modified_type='INSERTED'  AND d.modified_type=e.modified_type AND d.delivery_cid=e.delivery_cid AND d.delivery_cid=f.delivery_cid AND d.modified_type=f.modified_type AND d.delivery_cid=g.delivery_cid AND d.modified_type=g.modified_type AND d.challan_date>=:fromdate AND d.challan_date<=:todate AND d.business_unit=:business_unit AND d.inv_type=:inv_type AND g.trans_borne_by=:trans_type AND g.trans_code=:transporter_code GROUP BY d.delivery_cid" ,nativeQuery=true)
	List<Map<String, Object>> getChallanTransportModeReportJWwithTrans(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("inv_type") String inv_type,@Param("trans_type") String trans_type,@Param("transporter_code") String transporter_code);

	
	@Query(value= "SELECT d.delivery_cid as delivery_cid,d.challan_no as challan_no,d.challan_date as challan_date from delivery_challan d where d.delivery_cid=:deliveryid and d.modified_type = 'INSERTED'", nativeQuery=true)
	Map<String,Object> getloadingdeliverychallanReturn(@Param("deliveryid") String deliveryid);
	
	@Query(value="SELECT * FROM delivery_challan WHERE modified_type = 'INSERTED' AND referance_id=:loadingid ",nativeQuery=true)
	List<Map<String,Object>> getDeliveryChallanReportThrouhgLA(@Param("loadingid") String loadingid);
	
	@Query(value="SELECT * FROM delivery_challan_item_dtls_for_jobwork WHERE modified_type = 'INSERTED' AND delivery_cid=:delvid ",nativeQuery=true)
	List<Map<String,Object>> deliverychallanjobworkRetriveList(@Param("delvid") String delvid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_Item_Dtls_for_jobwork w SET w.modified_type =:mstatus WHERE w.delivery_cid =:delivery_cid and w.modified_type='INSERTED'")
    int delvJobItemupdate(@Param("delivery_cid") String delivery_cid,@Param("mstatus") String mstatus);
	
	@Query(value= "SELECT i.party,i.partyname,i.delivery_cid,i.challan_date,i.challan_no FROM `salereturnapprovaljobwork` i,delivery_challan j WHERE i.delivery_cid=j.delivery_cid AND i.party=:customer AND i.challan_date<=:returndate AND j.`modified_type`='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> getDelvChallanReturnData(@Param("customer") String customer,@Param("returndate") String returndate);

	@Query(value= "SELECT \r\n"
			+ "    d.challan_date,\r\n"
			+ "    c.state,\r\n"
			+ "    d.challan_no,\r\n"
			+ "    d.partyname,\r\n"
			+ "    c.district,\r\n"
			+ "    bn.name AS broker_name,\r\n"
			+ "    i.item_name,\r\n"
			+ "    i.squantity,\r\n"
			+ "    i.suom,\r\n"
			+ "    i.quantity,\r\n"
			+ "    i.uom,\r\n"
			+ "    i.price,\r\n"
			+ "    t.freight_amt,\r\n"
			+ "    t.transport_rate,\r\n"
			+ "    ROUND(((i.squantity * i.price) / i.quantity), 2) AS price_qtl,\r\n"
			+ "    CASE \r\n"
			+ "        WHEN t.transport_rate = 'PER TRUCK' \r\n"
			+ "            THEN ROUND(t.freight_amt / total_quantity.sum_quantity, 2)\r\n"
			+ "        ELSE t.freight_amt \r\n"
			+ "    END AS freight_qtl\r\n"
			+ "FROM \r\n"
			+ "    delivery_challan d\r\n"
			+ "LEFT JOIN \r\n"
			+ "    delivery_challan_item_dtls i ON d.delivery_cid = i.delivery_cid \r\n"
			+ "LEFT JOIN \r\n"
			+ "    delivery_challan_trans_info t ON d.delivery_cid = t.delivery_cid\r\n"
			+ "LEFT JOIN \r\n"
			+ "    delivery_challan_broker_dtls b ON d.delivery_cid = b.delivery_cid\r\n"
			+ "LEFT JOIN \r\n"
			+ "    cust_bussiness_partner_address c ON d.party = c.cp_id\r\n"
			+ "LEFT JOIN \r\n"
			+ "    broker_master bn ON b.broker_code = bn.broker_id\r\n"
			+ "LEFT JOIN \r\n"
			+ "    (SELECT \r\n"
			+ "         s.delivery_cid, \r\n"
			+ "         SUM(s.quantity) AS sum_quantity\r\n"
			+ "     FROM \r\n"
			+ "         delivery_challan_item_dtls s\r\n"
			+ "     WHERE \r\n"
			+ "         s.modified_type = 'INSERTED'\r\n"
			+ "     GROUP BY \r\n"
			+ "         s.delivery_cid) AS total_quantity ON d.delivery_cid = total_quantity.delivery_cid\r\n"
			+ "WHERE \r\n"
			+ "    d.modified_type = 'INSERTED'\r\n"
			+ "    AND i.modified_type = 'INSERTED'\r\n"
			+ "    AND t.modified_type = 'INSERTED'\r\n"
			+ "    AND c.modified_type = 'INSERTED'\r\n"
			+ "    AND b.modified_type = 'INSERTED'\r\n"
			+ "    AND bn.modified_type = 'INSERTED'\r\n"
			+ "    AND t.trans_borne_by = 'FOR'\r\n"
			+ "    AND d.challan_date BETWEEN :fromdate AND :todate \r\n"
			+ "    AND d.inv_type = :invoicetype \r\n"
			+ "",nativeQuery=true)
	List<Map<String, Object>> getSalesFreightReport(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("invoicetype") String invoicetype);
	
	@Query(value= "SELECT d.slno,d.item_code,d.item_name,d.hsn_code,d.packing,d.packing_name,r.rest_pack_wt AS squantity,d.uom,r.rest_item_wt AS quantity,d.suom,mat_wt,d.price,d.price_based_on,d.amount,d.discount_type,d.discount_rate,d.discount_amt,d.tax_code,d.tax_rate,d.cgstamt,d.sgstamt,d.igstamt,d.tax_amt,d.total_amt,d.acc_norms FROM delivery_challan_item_dtls d, return_approval_dchallan_rest_wt r WHERE d.`delivery_cid` =r.delv_challan AND d.item_code =r.item_code AND r.delv_packing =d.packing AND d.`delivery_cid`=:delivery_cid AND d.`modified_type`='INSERTED' ",nativeQuery=true)
	List<Map<String, Object>> getRestwt(@Param("delivery_cid") String delivery_cid);
	
	@Query(value="SELECT delivery_cid,challan_no,challan_date,referance_id,partyname FROM delivery_challan WHERE `modified_type`='INSERTED' AND invoicestatus='No' AND challan_date>=:fromdate AND challan_date<=:todate", nativeQuery=true)
	List<Map<String,Object>> searchpendingDelvChallan(@Param("fromdate") String fromdate, @Param("todate") String todate);
	
	//@Query(value="SELECT ref_type FROM delivery_challan WHERE invoicestatus='No' AND inv_type=:inv_type AND party=:party AND challan_date<=:challan_date AND modified_type='INSERTED'", nativeQuery=true)
	//List<Map<String,Object>> challanTypeList(@Param("inv_type") String inv_type, @Param("party") String party, @Param("challan_date") String challan_date);
	
	@Query("select s from Delivery_challan s where s.modified_type = 'INSERTED' AND invoicestatus='No' AND inv_type=:inv_type AND party=:party AND challan_date<=:challan_date")
	List<Delivery_challan> challanTypeList(@Param("inv_type") String inv_type, @Param("party") String party, @Param("challan_date") String challan_date);
	
	@Query(value="SELECT * FROM delivery_challan_docs WHERE modified_type='INSERTED' And doc_pdf=:doc_pdf ",nativeQuery=true)
	List<Map<String,Object>> getdocumentListwithfileDelvChallan(@Param("doc_pdf") String doc_pdf);
	
	@Query(value="SELECT ref_type,gatepass FROM delivery_challan WHERE modified_type='INSERTED' And sales_invoice_id=:challan ",nativeQuery=true)
	Map<String,Object> getGatepassByChallan(@Param("challan") String challan);
	
}
