package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;

public interface Pur_good_receiptRepository extends JpaRepository<Pur_good_receipt, Long>{
	
	@Query("select COUNT(id) from Pur_good_receipt")
	String countId();
	
	@Query( "select p from Pur_good_receipt p where p.modified_type ='INSERTED' ORDER BY p.grn_id DESC ")
	List<Pur_good_receipt> findGoodReceipts();
	
	@Query("select COUNT(id) from Pur_good_receipt where grn_date =:orderdate and b_unit =:bunit and item_type =:itype and purchase_type =:ptype and purchase_subtype =:psubtype")
	String countGRNOrder(@Param("bunit") String bunit,@Param("itype") boolean itype,@Param("ptype") String ptype,@Param("psubtype") String psubtype,@Param("orderdate") String orderdate);

	@Query( "select p from Pur_good_receipt p where p.grn_id =:grnid and p.modified_type ='INSERTED'")
	Pur_good_receipt getPurGoodRcptDtls(@Param("grnid") String grnid);
	
	@Query( "select p from Pur_good_receipt p where p.modified_type ='INSERTED'")
	List<Pur_good_receipt> getPurGoodRcptList();
	
	//@Query( "select p from Pur_good_receipt p where p.modified_type ='INSERTED' and p.supplier_name =:suppid and p.item_type =:itype and p.purchase_type =:ptype and p.purchase_subtype =:psubtype and p.bill_status = 0")
	//@Query(value= "SELECT * FROM pur_good_receipt p WHERE p.modified_type ='INSERTED' AND p.grn_id =(SELECT grn_id FROM  channelgrnsupplier WHERE supplier like %:suppid% ) AND p.item_type =:itype AND p.purchase_type =:ptype AND p.purchase_subtype =:psubtype AND p.bill_status = 0",nativeQuery=true)
	@Query(value= "SELECT * FROM channelgrnsupplier p WHERE p.modified_type ='INSERTED' AND p.supplier like %:suppid% AND p.item_type =:itype AND p.purchase_type =:ptype AND p.purchase_subtype =:psubtype AND p.bill_status = 0",nativeQuery=true)
	List<Pur_good_receipt> getPurGoodRcptRawMaterial(@Param("suppid") String suppid,@Param("itype") boolean itype,@Param("ptype") String ptype,@Param("psubtype") String psubtype);
	
	@Query( "select p from Pur_good_receipt p where p.modified_type ='INSERTED' and p.supplier_name =:suppid and p.item_type =:itype and p.purchase_type =:ptype and p.purchase_subtype =:psubtype and p.bill_status = 0")
	List<Pur_good_receipt> getPurGoodRcptOtherPurchase(@Param("suppid") String suppid,@Param("itype") boolean itype,@Param("ptype") String ptype,@Param("psubtype") String psubtype);
	
	@Query("select p from Pur_good_receipt p where p.b_unit =:bunit and p.supplier_name =:supplier and p.grn_date =:tdate and p.company_id =:company and p.fin_year =:finyear and p.modified_type = 'INSERTED'")
	List<Pur_good_receipt> getPurGRptRtnApp(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("tdate") String tdate,@Param("company") String company,@Param("finyear") String finyear);
	
	/*@Procedure(name = "Pur_good_receipt.callItemStockMaintain")
	int callItemStockMaintain(@Param("advitemqty") String advitemqty,@Param("advpackqty") String advpackqty,@Param("advitemcode") String advitemcode,
			@Param("advpackcode") String advpackcode,@Param("bunit") String bunit,@Param("finyear") String finyear);*/
	
	@Query(value = "{call checkGRNUsage(:grnid)}", nativeQuery = true)
	String checkGRNUsage(@Param("grnid") String grnid);
	
	
	@Query(value = "select sum(rest_wt) from pur_order_store_item_view where pur_orderid=:purid and advice_req='No'", nativeQuery = true)
	String getrestqty(@Param("purid") String purid);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt_doc w SET w.modified_type ='DELETED' WHERE w.grn_id = :grn_id")
    int pur_good_receipt_docsUpdate(@Param("grn_id") String grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order w SET w.grn_status =1 WHERE w.pur_orderid = :referance_id and  w.modified_type ='INSERTED'")
    int getrevert(@Param("referance_id") String referance_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order w SET w.grn_status =0 WHERE w.pur_orderid = :referance_id and w.grn_status=1 and  w.modified_type ='INSERTED'")
    int getundorevert(@Param("referance_id") String referance_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt w SET w.bill_status =1 WHERE w.grn_id = :referance_id and   w.modified_type ='INSERTED'")
	  int getrevertbill(@Param("referance_id") String referance_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt w SET w.bill_status =0 WHERE w.grn_id = :referance_id and   w.modified_type ='INSERTED'")
	  int getrevertbilldelete(@Param("referance_id") String referance_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice w SET w.unload_status ='0' WHERE w.unadviceid = :ref_id")
    int unload_adviceUpdate(@Param("ref_id") String ref_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice w SET w.grn_status ='0' WHERE w.unadviceid = :ref_id")
    int unload_adviceUpdategrn(@Param("ref_id") String ref_id);
	
	
	@Query(value = "{call getSearchGRNData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#purchase_type},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#pur_type1},:#{#finyear})}", nativeQuery = true)
	List<Pur_good_receipt> getsearchdata(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("purchase_type") String purchase_type,
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,
			@Param("todate") String todate,@Param("customername") String customername,
			@Param("pur_type1") String pur_type1,
			@Param("finyear") String finyear);
	
	@Query(value = "{call getSearchGRNData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#purchase_type},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#pur_type1},:#{#finyear})}", nativeQuery = true)
	List<Map<String,Object>> getsearchdataFast(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("purchase_type") String purchase_type,
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,
			@Param("todate") String todate,@Param("customername") String customername,
			@Param("pur_type1") String pur_type1,
			@Param("finyear") String finyear);
	
	@Query(value= "select * from pur_good_receipt g where g.modified_type = 'INSERTED' and g.pur_return_status ='No' and g.supplier_name =:supplier and g.b_unit =:bunit and g.company_id =:compid and g.fin_year =:finyear and g.grn_date <=:date",nativeQuery=true)
	List<Map<String, Object>> getReturnGRNData(@Param("date") String date,@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("finyear") String finyear,@Param("compid") String compid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt p SET p.pur_return_status ='Yes' , p.purreturnid=:purreturnid WHERE p.grn_id = :referance_id")
    int updateGRNPurReturn(@Param("referance_id") String referance_id,@Param("purreturnid") String purreturnid);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Pur_good_receipt p SET p.pur_return_status ='No' , p.purreturnid='NA' WHERE p.purreturnid = :purreturnid" )
	int updateTimeGRNPurReturn(@Param("purreturnid") String purreturnid);
	
	
	@Query(value= "select g.grn_id,g.grn_no,g.grn_date from pur_good_receipt g where g.modified_type = 'INSERTED' and g.referance_id=:referance_id",nativeQuery=true)
	List<Map<String, Object>> getlistbyunload(@Param("referance_id") String referance_id);
	
	@Query(value= "select id,grn_id,grn_no,grn_date,b_unitname,supplier,purchase_typename,referance_type,vehicle_no,sales_process,sales_order,challan_status from pur_good_receipt where modified_type ='INSERTED' and fin_year =:finyear and grn_date =:currDate", nativeQuery=true)
	List<Map<String, Object>> grnListData(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
	//SELECT m.`grn_no` AS grnno,m.`grndate` AS grndate,m.purchase_subtype AS purchase_subtype,m.referance_type AS referance_type, m.receipt_criteria AS receipt_criteria,m.supplier AS supplier ,m.vehicle_no AS vehicleno,p.`supp_ref_docno`,p.`supp_ref_doc_date`,p.`purbillno`,p.`billdate` AS billdate ,p.`remarks`,d.adv_item_name,d.adv_packing_name,d.adv_item_qty AS adviceitemqty,d.`adv_pack_qty` AS adv_pack_qty,d.`adv_mat_wt`,d.rcv_item_qty AS rcv_item_qty,d.`rcv_pack_qty` AS rcv_pack_qty,d.`rcv_mat_wt`,d.`pssd_item_qty` AS pssd_item_qty,d.`pssd_pack_qty` AS pssd_pack_qty,d.`pssd_mat_wt`,d.unit_rate AS unit_rate FROM `pur_good_receipt_item_details` d,`pur_good_receipt` m ,`pur_bill` p WHERE d.`modified_type`='INSERTED'  AND m.`modified_type` = d.`modified_type` AND  d.`grn_id`=m.`grn_id` AND p.`modified_type`= d.`modified_type` AND p.`referance_id`=m.`grn_id` AND m.grndate>=:fromdate AND m.grndate<=:todate
	@Query(value= "SELECT m.`grn_no` AS grnno,m.`grndate` AS grndate,m.purchase_subtype AS purchase_subtype,m.referance_type AS referance_type, m.receipt_criteria AS receipt_criteria,m.supplier AS supplier ,m.vehicle_no AS vehicleno,p.`supp_ref_docno`,p.`supp_ref_doc_date`,p.`purbillno`,p.`billdate` AS billdate ,p.`remarks`,d.adv_item_name,d.adv_packing_name,d.adv_item_qty AS adviceitemqty,d.`adv_pack_qty` AS adv_pack_qty,d.`adv_mat_wt`,d.rcv_item_qty AS rcv_item_qty,d.`rcv_pack_qty` AS rcv_pack_qty,d.`rcv_mat_wt`,d.`pssd_item_qty` AS pssd_item_qty,d.`pssd_pack_qty` AS pssd_pack_qty,d.`pssd_mat_wt`,d.unit_rate AS unit_rate FROM `pur_good_receipt_item_details` d,`pur_good_receipt` m ,`pur_bill` p WHERE d.`modified_type`='INSERTED'  AND m.`modified_type` = d.`modified_type` AND  d.`grn_id`=m.`grn_id` AND p.`modified_type`= d.`modified_type` AND p.`referance_id`=m.`grn_id` AND m.grndate>=:fromdate AND m.grndate<=:todate",nativeQuery=true)
	List<Map<String, Object>> getrgnpurbillreport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value="SELECT g.grn_date AS challan_date,g.grn_no AS challan_no,g.supplier AS partyname,(f.own_gross_wt*100) AS own_gross,(f.own_tare_wt*100) AS own_tare,(f.own_net_wt*100) AS own_net,CASE WHEN t.transporter_code IS NULL THEN 'NA' WHEN t.transporter_code = '0' THEN 'NA' ELSE (SELECT bp_name FROM trans_bussiness_partner WHERE bp_id=t.transporter_code) END AS transname,g.vehicle_no AS vehicle_no,f.own_weigh_slip_no AS own_slip_no,CASE WHEN (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=g.b_unit) IS NULL THEN '' ELSE (SELECT businessunit_name FROM company_business_unit_master WHERE businessunit_id=g.b_unit) END AS bunitname,(SUM(e.pssd_item_qty)*100) AS quantity,SUM(e.pssd_pack_qty) AS squantity,CASE WHEN (SELECT id FROM sales_transport WHERE challanno=g.grnno AND modified_type='INSERTED') IS NULL THEN '0' ELSE (SELECT id FROM sales_transport WHERE challanno=g.grnno AND modified_type='INSERTED') END AS id,g.referance_id AS referance_id, g.grn_id AS grn_id,(SELECT SUM(di.unit_rate)/COUNT(di.id) FROM pur_good_receipt_item_details di WHERE di.modified_type='INSERTED' AND di.grn_id=g.grn_id) AS avg_price,CASE WHEN w.wgment_no IS NULL THEN 'NA' WHEN w.wgment_no = '0' THEN 'NA' ELSE w.wgment_no END AS ref_doc_no,(w.gross_weight*100) AS gross,(w.tare_weight*100) AS tare FROM pur_good_receipt g,pur_good_receipt_item_details e,pur_goods_receipt_other_information f,pur_good_reciept_trans_info t,wm_unload_wgmnt w WHERE g.modified_type='INSERTED' AND g.grn_id=e.grn_id AND g.modified_type=e.modified_type AND g.grn_id=f.grn_id AND g.modified_type=f.modified_type AND g.grn_id=t.grn_id AND g.modified_type=t.modified_type AND g.referance_id=w.advice AND w.we_status=1 AND w.modified_type='INSERTED' AND g.grn_date>=:fromdate AND g.grn_date<=:todate AND g.b_unit=:business_unit AND t.trans_borne_by=:trans_type AND g.purchase_type=:pur_inv_type GROUP BY g.grn_id" ,nativeQuery=true)
	List<Map<String, Object>> getPurchaseTransportReport(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("pur_inv_type") String pur_inv_type,@Param("trans_type") String trans_type);
	
	@Query(value= "SELECT d.transporter_code AS transporter_code FROM pur_good_reciept_trans_info d WHERE d.modified_type='INSERTED' AND d.grn_id=:grn_id", nativeQuery=true)
	String getTransCode(@Param("grn_id") String grn_id);
	
	@Query(value="SELECT * FROM pur_good_receipt WHERE referance_type='UNLOAD ADVICE' AND modified_type='INSERTED' AND referance_id LIKE %:unloadid% ", nativeQuery=true)
	List<Map<String,Object>> getGRNThroughUnloadId(@Param("unloadid") String unloadid);
	
	@Query(value="SELECT * FROM pur_good_receipt WHERE modified_type='INSERTED' AND grn_id =:grn_id ", nativeQuery=true)
	List<Map<String,Object>> getGrnDetailsThroughGrnId(@Param("grn_id") String grn_id);
	
	@Query(value=" SELECT SUM(i.gross_amt) FROM pur_good_receipt p,pur_good_receipt_item_details i WHERE p.supplier_name=:supplier_name AND i.grn_id=p.grn_id AND p.modified_type='INSERTED' AND p.modified_type=i.modified_type AND p.fin_year =:finyear",nativeQuery=true)
	double getpurchasechecktotaltranslimit(@Param("supplier_name") String supplier_name,@Param("finyear") String finyear);
	
	@Query(value=" SELECT SUM(i.gross_amt) FROM pur_good_receipt p,pur_good_receipt_item_details i WHERE p.supplier_name=:supplier_name AND i.grn_id=p.grn_id AND p.modified_type='INSERTED' AND p.modified_type=i.modified_type AND p.fin_year =:finyear",nativeQuery=true)
	double purchasechecktotaltranslimitupdate(@Param("supplier_name") String supplier_name,@Param("finyear") String finyear);
	
	@Query(value="select SUM(gross_amt) FROM pur_good_receipt_item_details where grn_id=:grnid and modified_type='INSERTED'",nativeQuery=true)
	double grndetailsamount(@Param("grnid") String grnid);
	
	@Query(value="SELECT * FROM item_allocation_potaggingwith_loading WHERE ord_date>=:fromdate AND ord_date<=:todate", nativeQuery=true)
	List<Map<String,Object>> getJobWorkAllocationReport(@Param("fromdate") String fromdate, @Param("todate") String todate);
	
	@Query(value="SELECT grn_id,grn_no,grn_date,purchase_typename,referance_id,supplier,vehicle_no FROM pur_good_receipt WHERE `modified_type`='INSERTED' AND bill_status=0 AND grn_date>=:fromdate AND grn_date<=:todate", nativeQuery=true)
	List<Map<String,Object>> searchpendingGRNReport(@Param("fromdate") String fromdate, @Param("todate") String todate);
	
	@Query(value= "SELECT p.supplier_name,p.vehicle_id,SUM(i.pssd_pack_qty) AS total_grn_pkt,SUM(i.pssd_item_qty) AS total_grn_item FROM pur_good_receipt p LEFT JOIN pur_good_receipt_item_details i ON i.grn_id=p.grn_id AND i.modified_type=p.modified_type WHERE p.grn_id =:grnid AND p.modified_type ='INSERTED'",nativeQuery = true)
	Map<String, Object> getGrnDetailsById(@Param("grnid") String grnid);
	
	@Query(value= "SELECT DISTINCT p.* FROM pur_good_receipt p,pur_good_receipt_item_details i WHERE p.grn_id=i.grn_id AND i.modified_type = 'INSERTED' and p.modified_type ='INSERTED' AND i.modified_type = p.modified_type AND p.stack_maintain='No' AND (i.warehouse_name ='MULTIPLE' OR i.rack='MULTIPLE')",nativeQuery = true)
	List<Map<String, Object>> getGrnList();
	
	@Query(value= "SELECT DISTINCT p.* FROM pur_good_receipt p,pur_good_receipt_item_details i WHERE p.grn_id=i.grn_id AND i.modified_type = 'INSERTED' and p.modified_type ='INSERTED' AND i.modified_type = p.modified_type AND (i.warehouse_name ='MULTIPLE' OR i.rack='MULTIPLE')",nativeQuery = true)
	List<Map<String, Object>> getGrnAllList();
	
	@Query(value= "SELECT adv_item_code AS item_id,adv_item_name AS item_name FROM pur_good_receipt_item_details WHERE modified_type='INSERTED' AND grn_id=:grnid",nativeQuery = true)
	List<Map<String, Object>> getItemListByGrnId(@Param("grnid") String grnid);
	
	@Query(value= "SELECT adv_packing AS item_code,adv_packing_name AS item_name FROM pur_good_receipt_item_details WHERE modified_type='INSERTED' AND adv_item_code=:item AND grn_id=:grnid",nativeQuery = true)
	List<Map<String, Object>> getPackingItemByGrn(@Param("item") String item,@Param("grnid") String grnid);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Pur_good_receipt p SET p.stack_maintain =:stat WHERE p.grn_id = :grn_id AND p.modified_type='INSERTED'" )
    int updateStackMaintain(@Param("grn_id") String grn_id,@Param("stat") String stat);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt l SET l.challan_status =:dstatus WHERE l.grn_id =:referenceid")
    int updateGrnStatus(@Param("referenceid") String referenceid,@Param("dstatus") String dstatus);

	@Query(value= "SELECT p.* FROM pur_good_receipt p WHERE p.grn_id =:grnid AND p.modified_type ='INSERTED'",nativeQuery = true)
	Map<String, Object> getGrnDetails(@Param("grnid") String grnid);
	
	@Query(value= "SELECT w.id AS id,w.wgment_id AS weighment_id FROM wm_unload_wgmnt w,pur_good_receipt p,wm_unload_advice a WHERE a.`unadviceid`=p.`referance_id`\r\n"
			+ "AND a.`weighment_id`=w.`wgment_id` AND p.`modified_type`=a.`modified_type` AND a.`modified_type`=w.`modified_type` AND p.modified_type ='INSERTED'\r\n"
			+ "AND a.modified_type ='INSERTED' AND w.modified_type ='INSERTED' AND p.`grn_id`=:grnid AND p.`company_id`=:company",nativeQuery = true)
	Map<String, Object> getGrndetailsforWeighment(@Param("grnid") String grnid,@Param("company") String company);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Bill pl where pl.modified_type = 'INSERTED' and pl.referance_id=:grnid")
	Boolean checkPurBillGrnUsage(@Param("grnid") String grnid);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Delivery_challan pl where pl.modified_type = 'INSERTED' and pl.referance_id=:grnid")
	Boolean checkDelChallanGrnUsage(@Param("grnid") String grnid);
	
	@Query(value= "SELECT m.tw_date AS unload_date,w.vehicle_no AS vehicle,i.item_name AS item,\r\n"
			+ "CASE WHEN i.packing_name LIKE \"%GUNNY BAG%\" THEN REPLACE(i.packing_name,i.packing_name,\"GUNNY\")\r\n"
			+ "WHEN i.packing_name LIKE \"%HDPE%\" THEN REPLACE(i.packing_name,i.packing_name,\"HDPE\")\r\n"
			+ "ELSE i.packing_name END AS sh_pack,i.mat_wt AS ul_mat_weight,q.qc_date AS qc_date,\r\n"
			+ "q.MOIS,q.HLW,q.INFES,q.ODOUR,q.STONE,q.POTIA,q.KB,\r\n"
			+ "g.grn_no,g.grn_date,gi.pssd_mat_wt AS grn_pssd_mat_weight,\r\n"
			+ "IF(mq.M_MOIS!=\"\",mq.mas_qc_date,\"\") AS mas_qc_date,mq.M_MOIS,mq.M_HLW,mq.M_INFES,mq.M_ODOUR,mq.M_STONE,mq.M_POTIA,mq.M_KB,mq.M_2_5MM,mq.M_OFG,\r\n"
			+ "mq.M_IDK,mq.M_SVINML,mq.M_BR_SH,mq.M_WETGLUTEN,mq.M_TOTALIMPURITES,mq.M_6MM,\r\n"
			+ "g.sales_process,IFNULL(d.challan_no,\"NA\") AS challan_no,IFNULL(d.challan_date,\"NA\") AS challan_date,IFNULL(d.partyname,\"NA\") AS partyname,\r\n"
			+ "IF(gi.warehouse_name=\"MULTIPLE\",'MULTIPLE',wh.warehouse_name) AS warehouse_name,IF(gi.rack=\"0\",\"\",gi.rack) AS stack\r\n"
			+ "FROM peripheral_qc q\r\n"
			+ "LEFT JOIN master_qc mq ON q.quality_check_id=mq.quality_check_id\r\n"
			+ "LEFT JOIN wm_unload_advice w ON q.unloadid=w.unadviceid  AND w.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN wm_unload_advice_item_dtls i ON q.unloadid=i.unadviceid  AND i.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN wm_unload_wgmnt m ON q.unloadid=m.advice AND m.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN pur_good_receipt g ON g.referance_id=w.unadviceid AND g.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN pur_good_receipt_item_details gi ON gi.grn_id=g.grn_id AND gi.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN delivery_challan d ON g.grn_id=d.referance_id AND d.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN `warehouse_master` wh ON wh.warehouse_code=gi.warehouse_name AND wh.modified_type='INSERTED' \r\n"
			+ "WHERE m.tw_date BETWEEN :fromdate AND :todate\r\n"
			+ "ORDER BY m.tw_date",nativeQuery = true)
	List<Map<String, Object>> getAllWhQCReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value= "SELECT m.tw_date AS unload_date,w.vehicle_no AS vehicle,i.item_name AS item,\r\n"
			+ "CASE WHEN i.packing_name LIKE \"%GUNNY BAG%\" THEN REPLACE(i.packing_name,i.packing_name,\"GUNNY\")\r\n"
			+ "WHEN i.packing_name LIKE \"%HDPE%\" THEN REPLACE(i.packing_name,i.packing_name,\"HDPE\")\r\n"
			+ "ELSE i.packing_name END AS sh_pack,i.mat_wt AS ul_mat_weight,q.qc_date AS qc_date,\r\n"
			+ "q.MOIS,q.HLW,q.INFES,q.ODOUR,q.STONE,q.POTIA,q.KB,\r\n"
			+ "g.grn_no,g.grn_date,gi.pssd_mat_wt AS grn_pssd_mat_weight,\r\n"
			+ "IF(mq.M_MOIS!=\"\",mq.mas_qc_date,\"\") AS mas_qc_date,mq.M_MOIS,mq.M_HLW,mq.M_INFES,mq.M_ODOUR,mq.M_STONE,mq.M_POTIA,mq.M_KB,mq.M_2_5MM,mq.M_OFG,\r\n"
			+ "mq.M_IDK,mq.M_SVINML,mq.M_BR_SH,mq.M_WETGLUTEN,mq.M_TOTALIMPURITES,mq.M_6MM,\r\n"
			+ "g.sales_process,IFNULL(d.challan_no,\"NA\") AS challan_no,IFNULL(d.challan_date,\"NA\") AS challan_date,IFNULL(d.partyname,\"NA\") AS partyname,\r\n"
			+ "IF(gi.warehouse_name=\"MULTIPLE\",'MULTIPLE',wh.warehouse_name) AS warehouse_name,IF(gi.rack=\"0\",\"\",gi.rack) AS stack\r\n"
			+ "FROM peripheral_qc q\r\n"
			+ "LEFT JOIN master_qc mq ON q.quality_check_id=mq.quality_check_id\r\n"
			+ "LEFT JOIN wm_unload_advice w ON q.unloadid=w.unadviceid  AND w.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN wm_unload_advice_item_dtls i ON q.unloadid=i.unadviceid  AND i.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN wm_unload_wgmnt m ON q.unloadid=m.advice AND m.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN pur_good_receipt g ON g.referance_id=w.unadviceid AND g.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN pur_good_receipt_item_details gi ON gi.grn_id=g.grn_id AND gi.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN delivery_challan d ON g.grn_id=d.referance_id AND d.modified_type='INSERTED' \r\n"
			+ "LEFT JOIN `warehouse_master` wh ON wh.warehouse_code=gi.warehouse_name AND wh.modified_type='INSERTED' \r\n"
			+ "WHERE m.tw_date BETWEEN :fromdate AND :todate\r\n"
			+ "AND g.sales_process=:process\r\n"
			+ "ORDER BY m.tw_date",nativeQuery = true)
	List<Map<String, Object>> getWhQCReport(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("process") String process);

	@Query(value="SELECT CASE WHEN d.rest_wt>=:netwt THEN \"Yes\" ELSE \"No\" END FROM delivery_challan_sales_order_w_tolerance d WHERE d.order_id =:orderid",nativeQuery=true)
	String getSoRestQtyCheckWithGrn(@Param("orderid") String orderid,@Param("netwt") Double netwt);
	
}
