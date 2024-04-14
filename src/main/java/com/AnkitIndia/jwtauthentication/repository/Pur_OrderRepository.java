package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Item_Details;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;

public interface Pur_OrderRepository extends JpaRepository<Pur_Order, Long> {
	
	@Query("select COUNT(id) from Pur_Order")
	String countId();
	
	@Query("select COUNT(id) from Pur_Order where ord_date =:orderdate and ser_item_type =:orderfor and ser_item_subtype =:potype and pur_ord_type =:posubtype")
	String countPOrder(@Param("orderdate") String orderdate,@Param("orderfor") boolean orderfor,@Param("potype") String potype,@Param("posubtype") String posubtype);
	
	@Query("select p from Pur_Order p where p.modified_type = 'INSERTED' ORDER BY p.pur_orderid DESC ")
	List<Pur_Order> findAllPurOrders();
	
	@Query("select p from Pur_Order p where p.modified_type = 'INSERTED' and p.pur_orderid =:pur_order_id ")
	List<Pur_Order> findPurOrders(@Param("pur_order_id") String pur_order_id);
	
	
	@Query("select w from Wm_unload_advice w where w.unadviceid =:unadviceid and w.modified_type = 'INSERTED'")
	Wm_unload_advice getUnloadDetailsnew(@Param("unadviceid") String weighmentdata);

	@Query("select w from Wm_unload_wgmnt w where w.wgment_id = :wgment_id and w.modified_type = 'INSERTED' ")
	Wm_unload_wgmnt getUnloadWeightmentWtnew(@Param("wgment_id") String wgment_id);
	
	//@Query(value="SELECT IF(:pricebasedon='Packing',w.net_weight,CONVERT(w.tarebags,DECIMAL(9,2))) as bags FROM wm_unload_wgmnt w WHERE w.advice = :unadviceid AND w.weight2='weight2' AND w.modified_type = 'INSERTED' ",nativeQuery=true)
	//double getUnloadWeightmentWtnewrectify(@Param("unadviceid") String unadviceid,@Param("pricebasedon") String pricebasedon);
	
	@Query(value="SELECT w.tarebags FROM wm_unload_wgmnt w WHERE w.advice = :unadviceid AND w.weight2='weight2' AND w.modified_type = 'INSERTED' ",nativeQuery=true)
	String getUnloadWeightmentWtnewrectifyraw(@Param("unadviceid") String unadviceid);
	
	
	//@Query(value="SELECT IF(:pricebasedon='Packing', CONVERT(w.tarebags,DECIMAL(9,2)),w.net_weight) as bags FROM wm_unload_wgmnt w WHERE w.advice = :unadviceid AND w.weight2='weight2' AND w.modified_type = 'INSERTED' ",nativeQuery=true)
	//double getpssd_item_qtyrectifystore(@Param("unadviceid") String unadviceid,@Param("pricebasedon") String pricebasedon);
	
	
	@Query(value="SELECT w.net_weight FROM wm_unload_wgmnt w WHERE w.advice = :unadviceid AND w.weight2='weight2' AND w.modified_type = 'INSERTED' ",nativeQuery=true)
	double getpssd_item_qtyrectifyraw(@Param("unadviceid") String unadviceid);
	
	@Query(value="SELECT  (SELECT net_weight FROM wm_unload_wgmnt WHERE wgment_id = w.weighment_id AND modified_type = 'INSERTED') AS net_weight FROM wm_unload_advice w WHERE w.unadviceid =:wgment_id AND w.modified_type = 'INSERTED'",nativeQuery=true)
	double getnetweight(@Param("wgment_id") String wgment_id);
	
	@Query("select p from Pur_Order p where p.modified_type = 'INSERTED' and p.pur_orderid =:purchaseid  ")
	List <Pur_Order> findPurOrdersnew(@Param("purchaseid") String purchaseid);
	
	
	@Query("select p from Pur_Order p where p.modified_type = 'INSERTED' and p.pur_orderid =:pur_order_no  ")
	List<Pur_Order> getPurOrdDDCList(@Param("pur_order_no") String pur_order_no);
	
	@Query("select p from Pur_Order p where p.modified_type = 'INSERTED'")
	List<Pur_Order> getPurOrdAllList();

	//@Query( "select c.company_name from Company_master c where c.company_code = :ccode" )
	//String getCompCodeToCompName(String ccode);
	
	//@Query( "select c.company_code from Company_master c where c.company_name = :cname" )
	//String getCompNameToCompCode(String cname);
	
	@Query("select p from Pur_Order p where p.supplier_name = :suppid and p.modified_type = 'INSERTED'")
	List<Pur_Order> getPurOrdThruSuppList(@Param("suppid") String suppid);
	
//	@Query("select p from Pur_Order p where p.supplier_name = :suppid and p.advice_req='Yes' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit")
//  @Query("select p from Pur_Order p where p.supplier_name = :suppid and p.advice_req='Yes' and p.po_status ='Open' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit")
//	@Query("select p from Pur_Order p where p.supplier_name = :suppid and p.advice_req='Yes' and p.po_status ='Open' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit and p.tagadvice_status = 'No'")
	//@Query("select p from Pur_Order p where p.supplier_name = :suppid and p.advice_req='Yes' and p.po_status ='Open' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit and p.tagadvice_status = 'No' and p.unload_status=0")
	//@Query("select p from Pur_Order p where p.sup_channel_list like %:suppid% and p.advice_req='Yes' and p.po_status ='Open' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit and p.tagadvice_status = 'No' and p.unload_status=0")
	@Query(value= "SELECT p.* FROM pur_order p,pur_order_view q,pur_order_termination t WHERE p.pur_orderid = q.pur_orderid AND p.pur_orderid = t.pur_orderid AND q.rest_wt !=0 AND p.sup_channel_list like %:suppid% and p.advice_req='Yes' and p.po_status ='Open' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit and p.tagadvice_status = 'No' and p.unload_status=0 AND t.term_pur_ord='0'", nativeQuery=true)
	List<Pur_Order> getPurOrdAdvThruSupp(@Param("suppid") String suppid,@Param("businessunit") String businessunit);
	
	
	//@Query("select p from Pur_Order p where p.advice_req='No' and p.po_status ='Open' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit and p.ser_item_subtype =:pur_type")
	//@Query("select p from Pur_Order p where p.advice_req='No' and p.po_status ='Open' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit and p.ser_item_subtype =:pur_type and p.grn_status =0")
	@Query("select p from Pur_Order p where p.weightment_req='0' and p.po_status ='Open' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit and p.ser_item_subtype =:pur_type and p.grn_status =0 and p.advice_req='No' ")
	List<Pur_Order> getGrnThroughPurOrd(@Param("businessunit") String businessunit,@Param("pur_type") String pur_type);
	
	//@Query("select p from Pur_Order p where p.weightment_req='0' and p.po_status ='Open' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit and p.ser_item_subtype =:pur_type  and p.advice_req='No' ")
	@Query(value= "SELECT p.* FROM pur_order p WHERE p.weightment_req='0' AND p.po_status ='Open' AND p.modified_type = 'INSERTED' AND p.businessunit = 'CBU00001' AND p.ser_item_subtype ='ITMT00004'  AND p.advice_req='No' AND checkstoregrn(p.pur_orderid)>0", nativeQuery=true)
	List<Pur_Order> getGrnThroughPurOrdstore(@Param("businessunit") String businessunit,@Param("pur_type") String pur_type);
	
	@Query(value= "SELECT p.* FROM pur_order p WHERE p.weightment_req='0' AND p.po_status ='Open' AND p.modified_type = 'INSERTED' AND p.businessunit = 'CBU00001' AND p.ser_item_subtype ='ITMT00002'  AND p.advice_req='No' AND checkpackinggrn(p.pur_orderid)>0", nativeQuery=true)
	List<Pur_Order> getGrnThroughPurOrdpacking(@Param("businessunit") String businessunit,@Param("pur_type") String pur_type);
	
	
	@Query("select p from Pur_Order p where p.businessunit =:bunit and p.supplier_name =:supplier and p.ord_date =:tdate and p.company_id =:company and p.fin_year =:finyear and p.modified_type = 'INSERTED'")
	List<Pur_Order> getPurOrdRtnApp(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("tdate") String tdate,@Param("company") String company,@Param("finyear") String finyear);
	
	@Query("select p from Pur_Order p where p.modified_type = 'INSERTED' and p.pur_orderid = :orderid ")
	Pur_Order getPurOrdDetails(@Param("orderid") String orderid);
	
	@Query("select p from Pur_Order p where p.modified_type = 'INSERTED' and p.pur_order_no = :pur_order_no ")
	Pur_Order getPurOrdDetailsbyno(@Param("pur_order_no") String pur_order_no);
	
	
	//@Query("select p from Pur_Order p where p.supplier_name = :suppid and p.tagadvice_status ='Yes' and p.po_status ='Open' and p.advice_req ='Yes' and p.modified_type = 'INSERTED'")
	@Query("select p from Pur_Order p where p.sup_channel_list like %:suppid%  and p.tagadvice_status ='Yes' and p.po_status ='Open' and p.advice_req ='Yes' and p.modified_type = 'INSERTED'")
	List<Pur_Order> getPurOrdThruSuppAdvReq(@Param("suppid") String suppid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order p SET p.tagadvice_status ='Yes' WHERE p.pur_orderid = :po_number" )
    int updateTagAdvStatus(@Param("po_number") String po_number);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order p SET p.sup_channel_list=:custid WHERE p.sup_channel = :channel_custid" )
    int changeChannelSupplierList(@Param("custid") String custid,@Param("channel_custid") String channel_custid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order p SET p.tagadvice_status ='Yes',p.po_status='Open' WHERE p.pur_orderid = :po_number" )
    int updateTagAdvStatusrevert(@Param("po_number") String po_number);
	
	
	@Modifying(clearAutomatically = true)
  //  @Query("UPDATE Pur_Order p SET p.tagadvice_status ='Yes',p.weightment_req=:weightment_req WHERE p.pur_orderid = :po_number" )
	  @Query("UPDATE Pur_Order p SET p.weightment_req=:weightment_req WHERE p.pur_orderid = :po_number" )
    int updateTagAdvStatusraw(@Param("po_number") String po_number,@Param("weightment_req") boolean weightment_req);
	
	      @Modifying(clearAutomatically = true)
	  //  @Query("UPDATE Pur_Order p SET p.tagadvice_status ='Yes',p.weightment_req=:weightment_req WHERE p.pur_orderid = :po_number" )
		  @Query("UPDATE Pur_Order p SET p.weightment_req=:weightment_req WHERE p.pur_orderid = :po_number" )
	    int updateTagAdvStatusrawnew(@Param("po_number") String po_number,@Param("weightment_req") boolean weightment_req);
		
	
	
	@Query("select p from Pur_Order p where p.pur_orderid = :pono and p.modified_type = 'INSERTED'")
	List<Pur_Order> getsubtype(@Param("pono") String pono);

	
//	@Query("select p from Pur_Order p where p.pur_orderid = :pono and p.modified_type = 'INSERTED'")
	//List<Pur_Order> getPurOrdreceipt_criteria(@Param("pono") String pono);
	
	
	//@Modifying(clearAutomatically = true)
   // @Formula("UPDATE Pur_Order p SET p.total_qty_copy-=:total_qty_copy WHERE p.pur_orderid = :pur_orderid" )
   // int updateTotalQuantity(@Param("total_qty_copy") Double total_qty_copy,@Param("pur_orderid") String pur_orderid);
	
	
	@Query(value = "select p.rest_wt from pur_order_view p where p.pur_orderid = :pur_orderid ", nativeQuery=true)
	String pur_ord_view(@Param("pur_orderid") String pur_orderid);
	
	
	@Query(value = "select p.rest_wt from pur_order_item_view p where p.pur_orderid = :pur_orderid and p.item_code = :item_code ", nativeQuery=true)
	String pur_order_item_view(@Param("pur_orderid") String pur_orderid,@Param("item_code") String item_code);
	
	@Query(value = "select p.rest_wt from pur_order_store_item_view p where p.pur_orderid = :pur_orderid and p.item_code = :item_code and p.packing_item=:packing_item and p.classified_item_name=:classified_item_name", nativeQuery=true)
	String pur_order_store_item_view(@Param("pur_orderid") String pur_orderid,@Param("item_code") String item_code,@Param("packing_item") String packing_item,@Param("classified_item_name") String classified_item_name);
	
	@Query(value = "SELECT p.rest_wt FROM pur_order_packing_item_view p WHERE p.pur_orderid = :pur_orderid AND p.item_code = :item_code AND p.packing_item=:packing_item AND p.packing_item_code=:packing_item_code AND p.packing_size=:packing_size AND p.packing_type=:packing_type AND p.packing_weight=:packing_weight ", nativeQuery=true)
	String pur_order_packing_item_view(@Param("pur_orderid") String pur_orderid,@Param("item_code") String item_code,@Param("packing_item") String packing_item,
			@Param("packing_item_code") String packing_item_code,@Param("packing_size") String packing_size,
			@Param("packing_type") String packing_type,@Param("packing_weight") String packing_weight);
	
	
	@Query(value = "SELECT CASE WHEN p.price_based_on = 'Without Packing'  THEN p.rest_itemqty ELSE p.rest_wt END  AS rest_wt FROM pur_order_per_item_view p WHERE p.pur_orderid = :pur_orderid AND p.item_code = :item_code ", nativeQuery=true)
	String pur_order_per_item_view(@Param("pur_orderid") String pur_orderid,@Param("item_code") String item_code);
	
	//@Query(value = "select p.rest_wt from pur_order_item_view p where p.pur_orderid = :pur_orderid  ", nativeQuery=true)
	@Query(value = "select p.rest_wt from pur_order_item_qty_view p where p.pur_orderid = :pur_orderid  ", nativeQuery=true)
	String pur_order_item_view_update(@Param("pur_orderid") String pur_orderid);
	
	@Query(value = "select p.rest_wt from pur_order_item_qty_view p where p.pur_orderid = :pur_orderid  and item_code =:item_code and packing_item=:packing ", nativeQuery=true)
	String pur_order_item_view_update_new(@Param("pur_orderid") String pur_orderid,@Param("item_code") String item_code,@Param("packing") String packing);
	
	
	@Query(value = "select p.item_code from pur_order_item_view p where p.pur_orderid = :pur_orderid  ", nativeQuery=true)
	String pur_order_item_view_itemcode(@Param("pur_orderid") String pur_orderid);
	
	
	@Query(value = "SELECT CASE WHEN COUNT(p.pur_orderid)>0 THEN TRUE ELSE FALSE END  FROM pur_order_item_view p WHERE p.pur_orderid = :pur_orderid AND item_code=:item_code  ", nativeQuery=true)
	int pur_order_item_view_itemcodesinglecehck(@Param("pur_orderid") String pur_orderid,@Param("item_code") String item_code);
	
	
	@Query(value = "SELECT CASE WHEN COUNT(p.pur_orderid)>0 THEN TRUE ELSE FALSE END  FROM pur_order_item_qty_view p WHERE p.pur_orderid = :pur_orderid AND item_code=:item_code AND packing_item=:packing ", nativeQuery=true)
	int pur_order_item_view_itemcodecehck(@Param("pur_orderid") String pur_orderid,@Param("item_code") String item_code,@Param("packing") String packing);
	
	@Query(value = "select p.unload_item_wt from pur_order_item_view p where p.pur_orderid = :pur_orderid and p.item_code = :item_code ", nativeQuery=true)
	double getPurOrderItemWeightView(@Param("pur_orderid") String pur_orderid,@Param("item_code") String item_code);
	
	@Query("select p from Pur_Order_Item_Details p where p.pur_orderid = :pur_orderid and p.item_code = :item_code and  p.modified_type = 'INSERTED'")
	Pur_Order_Item_Details getPurOrderItemWeightViewList(@Param("pur_orderid") String pur_orderid,@Param("item_code") String item_code);
	
	
	@Query("select p from Pur_Order_Item_Details p where p.pur_orderid = :pur_orderid and p.item_code = :item_code and p.packing_item=:packing_item and  p.modified_type = 'INSERTED'")
	Pur_Order_Item_Details getPurOrderItemWeightViewListnew(@Param("pur_orderid") String pur_orderid,@Param("item_code") String item_code,@Param("packing_item") String packing_item);
	
	@Query("select count(e)>0  from  Wm_unload_advice e where e.pur_orderid= :pur_orderid and e.modified_type = 'INSERTED'")
	Boolean checkifexistunloadadvice(@Param("pur_orderid") String pur_orderid);
	
	@Query(value = "{call checkPurchaseOrderUsage(:code)}", nativeQuery = true)
	String checkPurchaseOrderUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order pl where pl.modified_type != 'DELETED' and pl.businessunit=:code")
	Boolean checkBussinessUnitPurchaseOrderUsage(@Param("code") String code);
	
	@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Pur_Order> getsearchdata(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,
			@Param("todate") String todate,@Param("customername") String customername,
			@Param("finyear") String finyear);
	
	@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Map<String,Object>> getsearchdataFast(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,
			@Param("todate") String todate,@Param("customername") String customername,
			@Param("finyear") String finyear);

	@Query(value="SELECT p.id AS id,p.pur_orderid AS pur_orderid,p.pur_order_no AS pur_order_no,p.ord_date AS ord_date,p.supplier AS supplier,p.businessunit_name AS businessunit_name,p.ser_item_subtype_name AS ser_item_subtype_name,p.pur_ord_type AS pur_ord_type,p.advice_req AS advice_req,p.po_status AS po_status FROM pur_order p, pur_order_termination t WHERE p.pur_orderid=t.pur_orderid AND p.modified_type=t.modified_type AND p.modified_type='INSERTED' AND t.term_pur_ord='0' AND p.po_status='Open' AND p.orddate=:cur_date",nativeQuery=true)
	List<Map<String,Object>> purchaseorderlist(@Param("cur_date") String cur_date);
	
	
	@Query(value="SELECT * from item_allocation_potaggingwith_loading where rest_wt>0",nativeQuery=true)
	List<Map<String,Object>> getdocumentno();
	
	
	@Query(value= "select * from pur_Order o where o.modified_type = 'INSERTED' and o.pur_return_status ='No' and o.supplier_name =:supplier and o.businessunit =:bunit and o.company_id =:compid and o.fin_year =:finyear and o.ord_date <=:date",nativeQuery=true)
	List<Map<String, Object>> getReturnPurOrderData(@Param("date") String date,@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("finyear") String finyear,@Param("compid") String compid);
	
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Pur_Order p SET p.pur_return_status='Yes' ,p.purreturnid=:purreturnid WHERE p.pur_orderid = :pur_orderid" )
	int updatepurreturn(@Param("pur_orderid") String pur_orderid,@Param("purreturnid") String purreturnid);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Pur_Order p SET p.pur_return_status ='No' , p.purreturnid='NA' WHERE p.purreturnid = :purreturnid" )
	int updateTimePurOrderPurReturn(@Param("purreturnid") String purreturnid);
	
	@Query(value= "select pur_order_no,ord_date,supplier,businessunit_name,ser_item_subtype_name,pur_ord_type,advice_req,po_status,id,pur_orderid from pur_order where modified_type ='INSERTED' and fin_year =:finyear and ord_date =:currDate", nativeQuery=true)
	List<Map<String, Object>> getPurchaseOrderListFastApi(@Param("currDate") String currDate, @Param("finyear") String finyear);
	//SELECT p.pur_order_no AS pur_order_no, p.ord_date AS ord_date, p.supplier AS supplier,p.businessunit_name AS businessunit_name,p.ser_item_subtype_name AS ser_item_subtype_name,p.pur_ord_type AS pur_ord_type,p.advice_req AS advice_req,p.po_status AS po_status,p.id AS id,p.pur_orderid AS pur_orderid FROM pur_order p, pur_order_termination t WHERE p.modified_type ='INSERTED' AND p.modified_type=t.modified_type AND p.ord_date =:currDate AND t.term_pur_ord='0' AND p.pur_orderid=t.pur_orderid
	
	@Query(value= "SELECT p.pur_order_no AS pur_order_no, p.ord_date AS ord_date, p.supplier AS supplier,p.businessunit_name AS businessunit_name,p.ser_item_subtype_name AS ser_item_subtype_name,p.pur_ord_type AS pur_ord_type,p.advice_req AS advice_req,p.po_status AS po_status,p.id AS id,p.pur_orderid AS pur_orderid,p.unload_status as unload_status FROM pur_order p, pur_order_termination t WHERE p.modified_type ='INSERTED' AND p.modified_type=t.modified_type AND p.ord_date =:currDate AND t.term_pur_ord='0' AND p.pur_orderid=t.pur_orderid", nativeQuery=true)
	List<Map<String, Object>> getPurchaseOrderListFastApiNew(@Param("currDate") String currDate);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) END  AS allias_brokername,CASE WHEN  (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>=:fromdate AND p.ord_date<=:todate AND p.businessunit=:business_unit AND b.ven_code_name IN (:ven_code_name) AND p.ser_item_subtype_name='RAW MATERIALS' AND p.po_status ='Open' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportbrokerOpen(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("ven_code_name") List<String> ven_code_name);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) END  AS allias_brokername,CASE WHEN  (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>=:fromdate AND p.ord_date<=:todate AND p.businessunit=:business_unit AND b.ven_code_name IN (:ven_code_name) AND p.ser_item_subtype_name='RAW MATERIALS' AND p.po_status ='Closed' AND c.term_pur_ord ='0' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportbrokerClose(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("ven_code_name") List<String> ven_code_name);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) END  AS allias_brokername,CASE WHEN  (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>=:fromdate AND p.ord_date<=:todate AND p.businessunit=:business_unit AND b.ven_code_name IN (:ven_code_name) AND p.ser_item_subtype_name='RAW MATERIALS' AND p.po_status ='Closed' AND c.term_pur_ord ='1' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportbrokerTerminate(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("ven_code_name") List<String> ven_code_name);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) END  AS allias_brokername,CASE WHEN  (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>=:fromdate AND p.ord_date<=:todate AND p.businessunit=:business_unit AND b.ven_code_name IN (:ven_code_name) AND p.ser_item_subtype_name='RAW MATERIALS' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportbroker(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("ven_code_name") List<String> ven_code_name);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) END  AS allias_brokername,CASE WHEN  (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>=:fromdate AND p.ord_date<=:todate AND p.businessunit=:business_unit AND p.supplier_name IN (:supplier_name) AND p.ser_item_subtype_name='RAW MATERIALS' AND p.po_status ='Open' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportpartyOpen(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("supplier_name") List<String> supplier_name);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) END  AS allias_brokername,CASE WHEN  (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>=:fromdate AND p.ord_date<=:todate AND p.businessunit=:business_unit AND p.supplier_name IN (:supplier_name) AND p.ser_item_subtype_name='RAW MATERIALS' AND p.po_status ='Closed' AND c.term_pur_ord ='0' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportpartyClose(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("supplier_name") List<String> supplier_name);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) END  AS allias_brokername,CASE WHEN  (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>=:fromdate AND p.ord_date<=:todate AND p.businessunit=:business_unit AND p.supplier_name IN (:supplier_name) AND p.ser_item_subtype_name='RAW MATERIALS' AND p.po_status ='Closed' AND c.term_pur_ord ='1' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportpartyTerminate(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("supplier_name") List<String> supplier_name);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM  `broker_master` WHERE broker_id=b.ven_code_name) END  AS allias_brokername,CASE WHEN  (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id= p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>=:fromdate AND p.ord_date<=:todate AND p.businessunit=:business_unit AND p.supplier_name IN (:supplier_name) AND p.ser_item_subtype_name='RAW MATERIALS' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportparty(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("supplier_name") List<String> supplier_name);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM broker_master WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM broker_master WHERE broker_id=b.ven_code_name) END AS allias_brokername,CASE WHEN (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id=p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id=p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>:fromdate AND p.ord_date<:todate AND p.businessunit=:business_unit AND p.ser_item_subtype_name='RAW MATERIALS' AND p.po_status ='Open' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportallOpen(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM broker_master WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM broker_master WHERE broker_id=b.ven_code_name) END AS allias_brokername,CASE WHEN (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id=p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id=p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>:fromdate AND p.ord_date<:todate AND p.businessunit=:business_unit AND p.ser_item_subtype_name='RAW MATERIALS' AND p.po_status ='Closed' AND c.term_pur_ord ='0' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportallClose(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM broker_master WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM broker_master WHERE broker_id=b.ven_code_name) END AS allias_brokername,CASE WHEN (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id=p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id=p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>:fromdate AND p.ord_date<:todate AND p.businessunit=:business_unit AND p.ser_item_subtype_name='RAW MATERIALS' AND p.po_status ='Closed' AND c.term_pur_ord ='1' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportallTerminate(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value= "SELECT SUBSTRING(p.pur_order_no,21,24) AS pur_order_no,p.pur_orderid AS order_id,p.ord_date AS orderdate,CASE WHEN (SELECT  alt_name FROM broker_master WHERE broker_id=b.ven_code_name) IS NULL THEN '' ELSE (SELECT  alt_name FROM broker_master WHERE broker_id=b.ven_code_name) END AS allias_brokername,CASE WHEN (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id=p.supplier_name) IS NULL THEN '' ELSE (SELECT alt_name FROM supp_bussiness_partner WHERE bp_id=p.supplier_name) END AS allias_partyname,CASE WHEN IF(@lastid1 = p.pur_order_no,'', @lastid1 \\:= p.pur_order_no) ='' THEN 0 ELSE p.no_of_advice END truck,CASE WHEN p.po_status = 'Open' THEN 'O' WHEN p.po_status = 'Closed' THEN (CASE WHEN c.term_pur_ord = '1' THEN 'T' ELSE 'C' END) ELSE p.po_status END AS po_status,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.item_code) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.item_code) END AS item_alliasname,CASE WHEN (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) IS NULL THEN '' ELSE (SELECT alt_name FROM item_master WHERE item_id=d.packing_item) END AS packing_alliasname,d.stock_qty AS itemqty,d.packing_qty AS packingqty,d.packing_uom AS packing_uom,d.stock_uom AS stock_uom,d.price AS rate,CASE WHEN d.price_based_on ='With Packing' THEN 'WP' WHEN d.price_based_on = 'Without Packing' THEN 'WOP' ELSE d.price_based_on END AS ordertype,CASE WHEN t.trans_borne_by = 'PARTY ACCOUNT' THEN 'PA' WHEN t.trans_borne_by ='OWN ACCOUNT' THEN 'OA' WHEN t.trans_borne_by IS NULL THEN 'NG' ELSE t.trans_borne_by END AS delivery FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_trans_info t, pur_order_termination c WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.modified_type=c.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND d.pur_orderid = t.pur_orderid AND d.pur_orderid = c.pur_orderid AND p.ord_date>:fromdate AND p.ord_date<:todate AND p.businessunit=:business_unit AND p.ser_item_subtype_name='RAW MATERIALS' GROUP BY p.pur_orderid ORDER BY p.ord_date",nativeQuery=true)
	List<Map<String, Object>> getPurchaseordermiscreportall(@Param("business_unit") String business_unit,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	
	@Query(value="SELECT * FROM pur_order_item_details WHERE modified_type='INSERTED' AND pur_orderid=(SELECT pur_orderid FROM pur_order WHERE supplier_name=:supplier_name AND modified_type='INSERTED' ORDER BY ord_date DESC LIMIT 1)", nativeQuery=true)
	List<Map<String, Object>> getLastPOThruSupItemDtls(@Param("supplier_name") String supplier_name);
	
	@Query(value="SELECT * FROM pur_order WHERE modified_type='INSERTED' AND supplier_name=:supplier_name ORDER BY ord_date DESC LIMIT 1", nativeQuery=true)
	List<Map<String, Object>> getLastPOThruSupPurDtls(@Param("supplier_name") String supplier_name);
	
	//@Query(value="SELECT p.ord_date AS orderdate, p.pur_order_no AS pur_order_no, b.ven_name AS brokername, CASE WHEN p.channel_req='No' THEN p.supplier ELSE  (SELECT CONCAT(channel_desc,' #') FROM channel_customer_master WHERE channel_custid =p.sup_channel ) END AS partyname, d.price AS rate, p.no_of_advice AS truck, (p.no_of_advice - CASE WHEN (SELECT COUNT(id) FROM wm_unload_advice WHERE modified_type ='INSERTED' AND pur_orderid = p.pur_orderid)> 0 THEN (SELECT COUNT(w.id) FROM wm_unload_advice w WHERE w.pur_orderid = p.pur_orderid AND w.weighment_status=2 AND w.modified_type='INSERTED') ELSE '0' END) AS pending FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_termination t WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND p.pur_orderid =t.pur_orderid AND p.ord_date>=:fromdate AND p.ord_date<=:todate AND p.businessunit='CBU00001' AND p.no_of_advice >0 AND t.term_pur_ord='0' GROUP BY p.pur_order_no",nativeQuery=true)
	@Query(value="SELECT p.ord_date AS orderdate, p.pur_order_no AS pur_order_no, b.ven_name AS brokername,p.po_status, CASE WHEN p.channel_req='No' THEN p.supplier ELSE (SELECT CONCAT(channel_desc,' #') FROM channel_customer_master WHERE channel_custid =p.sup_channel ) END AS partyname, d.price AS rate, p.no_of_advice AS truck,(p.no_of_advice - CASE WHEN (SELECT COUNT(id) FROM wm_unload_advice WHERE modified_type ='INSERTED' AND pur_orderid = p.pur_orderid)> 0 THEN (SELECT COUNT(w.id) FROM wm_unload_advice w WHERE w.pur_orderid = p.pur_orderid AND w.weighment_status=2 AND w.modified_type='INSERTED') ELSE '0' END) AS pending FROM pur_order p,pur_order_broker b,pur_order_item_details d,pur_order_termination t WHERE p.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.modified_type=d.modified_type AND p.modified_type=t.modified_type AND p.pur_orderid =b.pur_orderid AND d.pur_orderid = p.pur_orderid AND p.pur_orderid =t.pur_orderid AND p.ord_date>=:fromdate AND p.ord_date<=:todate AND p.businessunit='CBU00001' AND p.no_of_advice >0 AND t.term_pur_ord='0' AND (p.no_of_advice - CASE WHEN (SELECT COUNT(id) FROM wm_unload_advice WHERE modified_type ='INSERTED' AND pur_orderid = p.pur_orderid)> 0 THEN (SELECT COUNT(w.id) FROM wm_unload_advice w WHERE w.pur_orderid = p.pur_orderid AND w.weighment_status=2 AND w.modified_type='INSERTED') ELSE '0' END)>0 GROUP BY p.pur_order_no",nativeQuery=true) // Only Pending Truck
	List<Map<String,Object>> getPendingSoudaReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	//@Query(value="SELECT y.orddate,y.pur_orderid,y.pur_order_no,y.supplier,y.Broker,y.price,y.total_qty,IFNULL(z.adv_mat_wt,0) AS adv_mat_wt,(y.total_qty-IFNULL(z.adv_mat_wt,0)) AS pending FROM ( SELECT p.orddate,p.`pur_orderid`, p.pur_order_no,p.supplier,IFNULL(q.ven_name,'NA') AS Broker,p1.price,p1.`total_qty` FROM pur_order p LEFT JOIN (SELECT pur_orderid,SUM(mat_weight) AS total_qty,price FROM `pur_order_item_details` WHERE modified_type='INSERTED' GROUP BY pur_orderid)p1 ON p.`pur_orderid`=p1.pur_orderid LEFT JOIN pur_order_broker q ON p.`pur_orderid`=q.`pur_orderid` WHERE p.`modified_type`='INSERTED' AND q.modified_type='INSERTED' AND p.ser_item_subtype_name='RAW MATERIALS' AND p.`ord_date`>=:fromdate AND p.ord_date<=:todate AND p.no_of_advice=0) y LEFT JOIN ( SELECT x.referance_id,x.grn_id,SUM(x.adv_mat_wt) AS adv_mat_wt,x.PO FROM (SELECT a.referance_id,a.grn_id,b.adv_mat_wt,c.pur_orderid AS PO FROM `pur_good_receipt` a LEFT JOIN (SELECT grn_id,SUM(pssd_mat_wt) AS adv_mat_wt FROM `pur_good_receipt_item_details` WHERE modified_type='INSERTED' GROUP BY grn_id ) b ON a.grn_id=b.grn_id LEFT JOIN wm_unload_advice c ON a.referance_id=c.unadviceid WHERE a.modified_type='INSERTED' AND a.purchase_typename='RAW MATERIALS' AND c.modified_type='INSERTED')x GROUP BY PO) z ON y.pur_orderid=z.PO",nativeQuery=true)
	//@Query(value="SELECT y1.orddate,y1.pur_orderid,y1.pur_order_no,y1.supplier,y1.Broker,y1.po_status,y1.price,y1.total_qty,IFNULL(z1.adv_mat_wt,0) AS adv_mat_wt,(y1.total_qty-IFNULL(z1.adv_mat_wt,0)) AS pending FROM ( SELECT p.orddate,p.`pur_orderid`, p.pur_order_no,p.supplier,IFNULL(q.ven_name,'NA') AS Broker,p1.price,p1.`total_qty`,p.po_status FROM pur_order p LEFT JOIN (SELECT pur_orderid,SUM(mat_weight) AS total_qty,price FROM `pur_order_item_details` WHERE modified_type='INSERTED' GROUP BY pur_orderid)p1 ON p.`pur_orderid`=p1.pur_orderid LEFT JOIN pur_order_broker q ON p.`pur_orderid`=q.`pur_orderid` WHERE p.`modified_type`='INSERTED' AND q.modified_type='INSERTED' AND p.ser_item_subtype_name='RAW MATERIALS' AND p.`ord_date`>=:fromdate AND p.ord_date<=:todate AND p.no_of_advice=0) y1 LEFT JOIN ( SELECT x1.referance_id,x1.grn_id,SUM(x1.adv_mat_wt) AS adv_mat_wt,x1.PO FROM (SELECT a.referance_id,a.grn_id,b.adv_mat_wt,c.pur_orderid AS PO FROM `pur_good_receipt` a LEFT JOIN (SELECT grn_id,SUM(pssd_mat_wt) AS adv_mat_wt FROM `pur_good_receipt_item_details` WHERE modified_type='INSERTED' GROUP BY grn_id ) b ON a.grn_id=b.grn_id LEFT JOIN wm_unload_advice c ON a.referance_id=c.unadviceid WHERE a.modified_type='INSERTED' AND a.purchase_typename='RAW MATERIALS' AND c.modified_type='INSERTED')x1 GROUP BY PO) z1 ON y1.pur_orderid=z1.PO WHERE (y1.total_qty-IFNULL(z1.adv_mat_wt,0))>0",nativeQuery=true) //Only Pending Qty
	@Query(value="SELECT y1.orddate,y1.pur_orderid,y1.pur_order_no,y1.supplier,y1.Broker,y1.po_status,y1.price,y1.total_qty,IFNULL(z1.adv_mat_wt,0) AS adv_mat_wt,(y1.total_qty-IFNULL(z1.adv_mat_wt,0)) AS pending FROM ( SELECT p.orddate,p.`pur_orderid`, p.pur_order_no,p.supplier,IFNULL(q.ven_name,'NA') AS Broker,p1.price,p1.`total_qty`,p.po_status, t.term_pur_ord FROM pur_order p LEFT JOIN (SELECT pur_orderid,SUM(mat_weight) AS total_qty,price FROM `pur_order_item_details` WHERE modified_type='INSERTED' GROUP BY pur_orderid)p1 ON p.`pur_orderid`=p1.pur_orderid LEFT JOIN pur_order_termination t ON p.`pur_orderid`=t.pur_orderid LEFT JOIN pur_order_broker q ON p.`pur_orderid`=q.`pur_orderid` WHERE p.`modified_type`='INSERTED' AND q.modified_type='INSERTED' AND t.modified_type='INSERTED' AND p.ser_item_subtype_name='RAW MATERIALS' AND p.`ord_date`>=:fromdate AND p.ord_date<=:todate AND p.no_of_advice=0) y1 LEFT JOIN ( SELECT x1.referance_id,x1.grn_id,SUM(x1.adv_mat_wt) AS adv_mat_wt,x1.PO FROM (SELECT a.referance_id,a.grn_id,b.adv_mat_wt,c.pur_orderid AS PO FROM `pur_good_receipt` a LEFT JOIN (SELECT grn_id,SUM(pssd_mat_wt) AS adv_mat_wt FROM `pur_good_receipt_item_details` WHERE modified_type='INSERTED' GROUP BY grn_id ) b ON a.grn_id=b.grn_id LEFT JOIN wm_unload_advice c ON a.referance_id=c.unadviceid WHERE a.modified_type='INSERTED' AND a.purchase_typename='RAW MATERIALS' AND c.modified_type='INSERTED')x1 GROUP BY PO) z1 ON y1.pur_orderid=z1.PO WHERE (y1.total_qty-IFNULL(z1.adv_mat_wt,0))>0 AND y1.term_pur_ord='0'",nativeQuery=true) //Only Pending Qty
	List<Map<String,Object>> getPendingSoudaReportPerQty(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value= "SELECT DISTINCT p.supplier_name AS supplier_name,p.supplier AS supplier FROM pur_order p WHERE p.modified_type='INSERTED' AND p.company_id=:company AND p.ord_date>=:fromdate AND p.ord_date<=:todate and p.businessunit=:business_unit", nativeQuery=true)
	List<Map<String, Object>> purchaseOrderSupplierNamesList(@Param("company") String company,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("business_unit") String business_unit);
	
	@Query(value= "SELECT DISTINCT b.ven_code_name AS ven_code_name,b.ven_name AS ven_name FROM pur_order p,pur_order_broker b WHERE p.modified_type='INSERTED' AND b.modified_type='INSERTED' AND p.modified_type=b.modified_type AND p.pur_orderid =b.pur_orderid AND p.company_id=:company AND p.ord_date>=:fromdate AND p.ord_date<=:todate and p.businessunit=:business_unit AND b.ven_name IS NOT NULL", nativeQuery=true)
	List<Map<String, Object>> purchaseOrderBrokerNamesList(@Param("company") String company,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("business_unit") String business_unit);
	
	@Query(value= "SELECT p.pur_bill_no, w.pur_order_no, w.advicenumber, w.partyname, w.vehicleno, w.grossweight, w.netweight, w.digital_weight, w.digital_weight1, w.weighmentdate, d.adv_item_name, d.adv_packing_item_name, d.passed_packing_qty, d.passed_item_qty, d.unit_rate, d.price_based_on, d.gross_amt, p.discount AS discount, p.qc_deduction AS qc_deduction, Dala_Charges_Driver,UP_Front_Brokerage,WB_Truck_Charges_Driver,Deduction_From_Supplier,FREIGHT_ADV,MOISTURE,DALA_CHARGES,UP_BROKERAGE,QUALITY_CLAIM,WB_CHARGES,OFFICE_EXP,HDPE_BAG,TDS_194Q,FREIGHT_ADVANCE , p.add_tax AS add_tax, p.add1 AS addplus, p.add2 AS addminus, p.roundoff_amt AS roundoff_amt, p.payable_party AS payable_party FROM pur_bill p , wheat_inward_view w , purbill_weigment b,pur_bill_item_details d WHERE p.modified_type='INSERTED' AND p.adviceno=w.advicenumber AND p.pur_bill_no = b.pur_bill_no AND p.pur_bill_id=d.pur_bill_id AND p.modified_type=d.modified_type AND p.bill_date>=:fromdate AND p.bill_date<=:todate", nativeQuery=true)
	List<Map<String, Object>> PurOrderReport(@Param("fromdate") String fromdate,@Param("todate") String todate);

	@Query(value= "SELECT t.* FROM wm_unload_advice l,pur_order_trans_chgs_dyn t WHERE l.modified_type='INSERTED' AND t.modified_type=l.modified_type AND l.unadviceid=:advice_id AND t.pur_orderid=l.referance_id AND t.transporter_name=:trans_code", nativeQuery=true)
	Map<String,Object> getPurOrderTransChgsData(@Param("advice_id") String advice_id, @Param("trans_code") String trans_code);
	
	@Query(value= "SELECT * FROM pur_order WHERE modified_type='INSERTED' AND pur_orderid =:orderid", nativeQuery=true)
	List<Map<String, Object>> getPurOrderDetailsThroughOrderId(@Param("orderid") String orderid);
	
	@Query(value= "SELECT p.* FROM pur_order p WHERE p.sup_channel_list like %:supplier% and p.advice_req='Yes' and p.po_status ='Open' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit and p.tagadvice_status = 'No' and p.unload_status=0 AND ser_item_subtype='ITMT00004'", nativeQuery=true)
	//List<Map<String, Object>> getPurOrdListOnlyStorePurchase(@Param("supplier") String supplier,@Param("businessunit") String businessunit);
	List<Pur_Order> getPurOrdListOnlyStorePurchase(@Param("supplier") String supplier,@Param("businessunit") String businessunit);
	
	//@Query(value= "SELECT p.* FROM pur_order p WHERE p.sup_channel_list like %:supplier% and p.advice_req='Yes' and p.po_status ='Open' and p.modified_type = 'INSERTED' and p.businessunit = :businessunit and p.tagadvice_status = 'No' and p.unload_status=0 AND ser_item_subtype='ITMT00002'", nativeQuery=true)
	@Query(value= "SELECT p.* FROM pur_order p WHERE NOT EXISTS(SELECT `wl`.`referance_id` FROM  wm_unload_advice wl WHERE `wl`.`modified_type` = 'INSERTED' AND `wl`.`referance_id` = p.`pur_orderid`) AND  `p`.`modified_type` = 'INSERTED' AND p.sup_channel_list LIKE %:supplier% AND  p.advice_req='Yes' AND p.businessunit = :businessunit AND p.tagadvice_status = 'No' AND p.ser_item_subtype='ITMT00002' ", nativeQuery=true)

	List<Pur_Order> getPurOrdListOnlyPacking(@Param("supplier") String supplier,@Param("businessunit") String businessunit);
	
	//@Query(value="SELECT p.ord_date,p.pur_order_no,p.pur_orderid,p.supplier,SUM(i.mat_weight) AS tot_wt FROM pur_order p,`pur_order_item_details` i WHERE p.modified_type = 'INSERTED' AND p.ser_item_subtype ='ITMT00009' AND i.modified_type = p.modified_type AND i.pur_orderid=p.pur_orderid GROUP BY p.`pur_orderid`",nativeQuery=true)
	@Query(value="SELECT p.ord_date,p.pur_order_no,p.pur_orderid,p.supplier,SUM(i.mat_weight) AS tot_wt FROM pur_order p,`pur_order_item_details` i WHERE p.modified_type = 'INSERTED'  AND i.modified_type = p.modified_type AND i.pur_orderid=p.pur_orderid GROUP BY p.`pur_orderid`",nativeQuery=true)
	List<Map<String,Object>>  getJwPo();
	
	
	@Query(value=" SELECT IF(ISNULL(p.store_charges),'NA',p.store_charges) AS store_charges,p.pur_orderid AS pur_orderid FROM pur_order p,wm_unload_advice w,`pur_good_receipt` g  WHERE p.modified_type='INSERTED' AND w.unadviceid = g.referance_id AND w.referance_id=p.pur_orderid AND g.grn_id=:grnid AND g.modified_type='INSERTED' AND w.modified_type='INSERTED' ", nativeQuery=true)
	Map<String,Object> getStoreChargesPo(@Param("grnid") String grnid);
	
	@Query(value=" SELECT IF(ISNULL(p.store_charges),'NA',p.store_charges) AS store_charges,p.pur_orderid AS pur_orderid FROM pur_order p,`pur_good_receipt` g WHERE p.modified_type='INSERTED' AND  g.referance_id=p.pur_orderid AND g.grn_id=:grnid AND g.modified_type='INSERTED' ", nativeQuery=true)
	Map<String,Object> getStoreChargesdirectPo(@Param("grnid") String grnid);
	
	@Query(value=" p.pur_order_no AS pur_order_no FROM pur_order p,wm_unload_advice w,`pur_good_receipt` g  WHERE p.modified_type='INSERTED' AND w.unadviceid = g.referance_id AND w.referance_id=p.pur_orderid AND g.grn_id=:grnid AND g.modified_type='INSERTED' AND w.modified_type='INSERTED' ", nativeQuery=true)
	String getStoreChargesPoid(@Param("grnid") String grnid);
	
	@Query(value=" SELECT p.pur_order_no AS pur_order_no FROM pur_order p,`pur_good_receipt` g WHERE p.modified_type='INSERTED' AND  g.referance_id=p.pur_orderid AND g.grn_id=:grnid AND g.modified_type='INSERTED' ", nativeQuery=true)
	String getStoreChargesdirectPoid(@Param("grnid") String grnid);
	

}
