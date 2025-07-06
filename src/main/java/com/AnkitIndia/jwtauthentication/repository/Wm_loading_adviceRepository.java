package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_itm_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;


public interface Wm_loading_adviceRepository extends JpaRepository<Wm_loading_advice, Long> {

	@Query("select COUNT(id) from Wm_loading_advice")
	String countId();
	
	@Query("select COUNT(id) from Wm_loading_advice where advice_date =:orderdate and advice_type =:type ")
	String countLoadingAdv(@Param("orderdate") String orderdate,@Param("type") String type);

	@Query(value="select COUNT(id) from wm_loading_advice where advice_date =:orderdate and advice_no like %:type%", nativeQuery = true)
	String countLoadingAdvwarehouse(@Param("orderdate") String orderdate,@Param("type") String type);
	
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' ")
	List<Wm_loading_advice> getLoadingAdviceList();
	
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' ORDER BY w.advice_id DESC ")
	List<Wm_loading_advice> findLoadingAdvices();
	
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' and w.advice_type='Stock Transfer' ")
	List<Wm_loading_advice> getLoadingAdviceStkTrans();
	
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' and w.advice_type='Stock Transfer' and w.stk_trans_chln_status='No'")
	List<Wm_loading_advice> getLoadingAdviceStkTransNew();
	
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' and w.loading_status ='1' and w.weighment_status='2' AND w.advice_date <=:cdate AND w.advice_type='Sale' ")
	List<Wm_loading_advice> getLoadingAdvicesThruWeigh(@Param("cdate") String cdate);
	
	//@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' and w.loading_status ='1' and w.weighment_status='2' AND w.advice_date <=:cdate AND w.advice_type='Sale' AND w.bus_partner =:party AND w.delvchallan_status =:dstatus")
	//List<Wm_loading_advice> getLoadingAdvicesThruWeigh(@Param("cdate") String cdate,@Param("party") String party,@Param("dstatus") boolean dstatus);
	
	//@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' and w.loading_status ='1' and w.weighment_status='2' AND w.advice_date <=:cdate AND w.advice_type='Sale' AND w.bus_partner =:party AND w.delvchallan_status =:dstatus")
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' and w.loading_status ='1' and w.weighment_status='2' AND w.advice_date <=:cdate AND w.advice_type='Sale' AND w.bus_partner =:party AND w.delvchallan_status =:dstatus and w.ref_doc_type ='Sales Order' and w.terminate=0")
	List<Wm_loading_advice> getLoadingAdvicesThruWeigh(@Param("cdate") String cdate,@Param("party") String party,@Param("dstatus") boolean dstatus);
	
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' and w.loading_status ='1' and w.weighment_status='2' AND w.advice_date <=:prdate AND w.supplier =:supplier AND w.advice_type='Purchase Return' AND w.company_id=:company AND w.fin_year=:finyear ")
	List<Wm_loading_advice> getLoadingAdvPurRtn(@Param("prdate") String prdate,@Param("supplier") String supplier,@Param("company") String company,@Param("finyear") String finyear);
	
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' AND advice_type='Stock Transfer' AND w.delivery_business_unit =:dbunit AND w.stk_trans_chln_status ='Yes' AND w.billing_req='Yes' AND w.stk_trans_inv_status='Yes' AND w.unload_status='Not Done' AND w.advice_date <=:cdate ")
	List<Wm_loading_advice> getLoadingAdvThruStkWithBill(@Param("dbunit") String dbunit,@Param("cdate") String cdate);
	
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' AND advice_type='Stock Transfer' AND w.delivery_business_unit =:dbunit AND w.stk_trans_chln_status ='Yes' AND w.billing_req='No' AND w.stk_trans_inv_status='No' AND w.unload_status='Not Done' AND w.advice_date <=:cdate ")
	List<Wm_loading_advice> getLoadingAdvThruStkWithoutBill(@Param("dbunit") String dbunit,@Param("cdate") String cdate);
	
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' and w.delivery_business_unit =:bunit and w.advice_type='Stock Transfer' ")
	List<Wm_loading_advice> getLoadingAdviceThruBUnit(@Param("bunit") String bunit);
	
	@Query( "select w from Wm_loading_advice w where w.advice_id =:code ")
	Wm_loading_advice loadingAdviceVehicleList(@Param("code") String code);
	
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' and w.vehicle_id =:vehicle_id and w.advice_type =:adv_type and w.unload_status='Not Done'")
	List<Wm_loading_advice> loadingAdviceList(@Param("vehicle_id") String vehicle_id,@Param("adv_type") String adv_type);
	
	@Query("select w from Wm_loading_advice w where w.modified_type ='INSERTED' AND w.advice_type='Sale' AND w.weighment_status !='2' ")
	List<Wm_loading_advice> getLoadingVehiThruSales();
	
	@Query("select w from Wm_loading_advice w where w.modified_type ='INSERTED' AND w.advice_type='Sale' AND w.weighment_status !='2' ")
	Set<Wm_loading_advice> getLoadingVehicleThruSales();
	
	@Query("select w from Wm_loading_advice w where w.modified_type ='INSERTED' AND w.advice_type='Stock Transfer' AND w.weighment_status !='2' ")
	List<Wm_loading_advice> getLoadingVehiThruStkTransfer();
	
	@Query("select w from Wm_loading_advice w where w.modified_type ='INSERTED' AND w.advice_type='Purchase Return' AND w.weighment_status !='2' ")
	List<Wm_loading_advice> getLoadingVehiThruPR();
	
	@Query("select w from Wm_loading_advice w where w.vehicle_id = :vcode and w.modified_type ='INSERTED' and advice_type =:weighment and w.weighment_status !='2' ")
	List<Wm_loading_advice> getLoadngAdviceThruVehicle(@Param("vcode") String vcode,@Param("weighment") String weighment);
	
	@Query(value="select * from wm_loading_advice w where w.vehicle_id = :vcode and w.modified_type ='INSERTED' and advice_type =:weighment and w.weighment_status !='2' ",nativeQuery=true)
	List<Map<String, Object>> getLoadngAdviceThruVehiclefast(@Param("vcode") String vcode,@Param("weighment") String weighment);
	
	@Query( "select w from Wm_loading_advice w where w.advice_id =:advice_id and w.modified_type ='INSERTED'")
	Wm_loading_advice getLoadingDetails(@Param("advice_id") String advice_id);
	
	@Query( "select w from Wm_loading_advice w where w.advice_id =:advice_id and w.referance_id =:referance_id and w.b_unit =:bunit and w.modified_type ='INSERTED' ")
	Wm_loading_advice getLoadingDtls(@Param("advice_id") String advice_id,@Param("referance_id") String referance_id,@Param("bunit") String bunit);
	
	@Query( "select w from Wm_loading_advice w where w.id =:id")
	Wm_loading_advice getLoadingDetails(@Param("id") Long id);
	
	@Query( "select w from Wm_loading_advice w where w.modified_type ='INSERTED' and w.vehicle_id =:vehicleid and loading_status='1' and w.weighment_status='2' and w.advice_type =:adv_type and w.unload_status='Not Done'")
	Wm_loading_advice getLoadingAdvThruVehicle(@Param("vehicleid") String vehicleid,@Param("adv_type") String adv_type);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice w SET w.loading_status ='1',w.weighment_status='1',w.weighment_id =:wid WHERE w.advice_id = :advice_id")
    int updateStatus(@Param("advice_id") String advice_id,@Param("wid") String wid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice w SET w.loading_status ='1',w.weighment_status='2',w.weighment_id =:wid WHERE w.advice_id = :advice_id")
    int updateLoadingStatus(@Param("advice_id") String advice_id,@Param("wid") String wid);
	
	@Query( "select w from Wm_loading_advice w where w.referance_id =:referance_id and w.modified_type ='INSERTED'")
	List<Wm_loading_advice> loadingAdviceDetails(@Param("referance_id") String referance_id);
	
	@Query(value= "SELECT delivery_cid from salereturnapproval where sale_order_id=:salesorderid and difference!=0", nativeQuery=true)
	List<String> loadingAdviceReturnDetails(@Param("salesorderid") String salesorderid);
	
	@Query( "select w from Wm_loading_advice w where w.referance_id =:referance_id and w.modified_type ='INSERTED'  AND w.advice_id !=:advice_id")
	List<Wm_loading_advice> loadingAdviceDetails(@Param("advice_id") String advice_id,@Param("referance_id") String referance_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice w SET w.stk_trans_chln_status ='Yes' WHERE w.advice_id =:advice_id")
    int updateStockChlnStatus(@Param("advice_id") String advice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice w SET w.stk_trans_chln_status ='No' WHERE w.advice_id =:advice_id and w.stk_trans_chln_status ='Yes' ")
    int revertStockChlnStatus(@Param("advice_id") String advice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice w SET w.stk_trans_inv_status ='Yes' WHERE w.advice_id =:advice_id")
    int updateStockInvStatus(@Param("advice_id") String advice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice l SET l.unload_status ='Done' WHERE l.advice_type='Stock Transfer' AND l.vehicle_id = :vehicle_id")
    int updateLoadingStatus(@Param("vehicle_id") String vehicle_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice l SET l.unload_status ='Not Done' WHERE l.advice_type='Stock Transfer' AND l.vehicle_id = :vehicle_id")
    int updateLoadingStatusrevert(@Param("vehicle_id") String vehicle_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice l SET l.delvchallan_status =:mstatus WHERE l.advice_id =:referenceid")
    int updateDelvStatus(@Param("referenceid") String referenceid,@Param("mstatus") boolean mstatus);
	
	
	@Query( "select w from Vehicle_weighment_load_unload w where w.reference_id =:reference_id ")
	Vehicle_weighment_load_unload getvehiclegstat(@Param("reference_id") String reference_id);
	
	
	@Modifying(clearAutomatically = true)
	@Query("DELETE from Vehicle_weighment_load_unload w WHERE w.reference_id = :reference_id and  w.modified_type ='INSERTED' ")
    int loadingVehicleUpdate(@Param("reference_id") String reference_id);
	
	 @Query(value = "{call wm_loading_status_check(:vcode,:weighment)}", nativeQuery=true)
		List<Wm_loading_advice> wm_loading_status_check(@Param("vcode") String vcode,@Param("weighment") String weighment);
			
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Vehicle_weighment_load_unload w SET w.load_unload_status ='1',w.weighment_status='1',w.weighment_id =:wid WHERE w.reference_id = :advice_id")
    int updateLoadUnloadStatus(@Param("advice_id") String advice_id,@Param("wid") String wid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Vehicle_weighment_load_unload w SET w.load_unload_status ='1',w.weighment_status='2',w.weighment_id =:wid WHERE w.reference_id = :advice_id")
    int updateAfterLoadUnloadStatus(@Param("advice_id") String advice_id,@Param("wid") String wid);
	
	
	@Query("select s from Wm_loading_advice s where s.modified_type = 'INSERTED' and s.advice_id =:adviceid ")
	List<Wm_loading_advice> getLoadAdvThruWeighmentUpdate(@Param("adviceid") String adviceid);
	
	@Query("select s from Wm_loading_advice_itm_dtls s where s.modified_type = 'INSERTED' and s.advice_id =:adviceid ")
	List<Wm_loading_advice_itm_dtls> loadingItemRetriveListUpdate(@Param("adviceid") String adviceid);
	
	@Query("select s from Wm_loading_advice s where s.modified_type = 'INSERTED' and s.advice_id =:adviceid ")
	Wm_loading_advice getvehicleno(@Param("adviceid") String adviceid);
	
	@Query("select s from Wm_loading_advice s where s.modified_type = 'INSERTED' and s.advice_id = :stk_challan_id")
	Wm_loading_advice getLoadingStk(@Param("stk_challan_id") String stk_challan_id);
	
	@Query("select s from Wm_loading_advice s where s.modified_type = 'INSERTED' and s.advice_id = :stk_challan_id and s.advice_type='Stock Transfer'")
	Wm_loading_advice getLoadingStkbyId(@Param("stk_challan_id") String stk_challan_id);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Wm_loading_advice pl where pl.modified_type != 'DELETED' and pl.vehicle_id=:veh_id and pl.weighment_status=0 and pl.multipleloading !=1")
	Boolean checkVehicleNo(@Param("veh_id") String veh_id);
	
	@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Wm_loading_advice> getsearchdata(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("customername") String customername,@Param("finyear") String finyear);
	
	@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Map<String,Object>> getsearchdataFast(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("customername") String customername,@Param("finyear") String finyear);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice w SET w.multipleloading =0 WHERE w.advice_id =:advice_id and w.multipleloading =1")
    int updatemultipleadviceinsinglekata(@Param("advice_id") String advice_id);
	

	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice l SET l.delvchallan_status =0 WHERE l.advice_id =:referenceid and l.delvchallan_status =1")
    int revertgrnstatus(@Param("referenceid") String referenceid);
	
	@Query("select s from Wm_loading_advice s where s.modified_type = 'INSERTED' and s.advice_date=:currDate ")
	List<Wm_loading_advice> getLoadingAdviceDataList(@Param("currDate") String currDate);
	
	
	
	@Query(value="select referance_id from wm_loading_advice s where s.modified_type = 'INSERTED' and s.advice_date=:currDate ",nativeQuery=true)
	List<String> getloadingdetailsthroughorder(@Param("currDate") String currDate);
	
	
	//@Query(value="SELECT m.id as id,m.advice_id as advice_id,m.advice_date as advice_date,m.advice_no as advice_no,m.advice_type as advice_type,m.customer_name as customer_name,m.vehicle_no as vehicle_no,(SELECT loading_name FROM loading_point  WHERE loading_id =m.load_point) AS load_point,CASE  WHEN  m.referance_id LIKE 'SO%' THEN (SELECT order_no FROM `sales_order`  WHERE modified_type = 'INSERTED' AND order_id =m.referance_id) WHEN m.referance_id LIKE 'ST%' THEN  (SELECT order_no FROM stock_transfer  WHERE modified_type = 'INSERTED' AND order_id = m.referance_id) WHEN m.referance_id LIKE 'PRA%' THEN (SELECT purreturnno FROM pur_return_approval_note  WHERE modified_type = 'INSERTED' AND purreturnid = m.referance_id ) ELSE  0 END AS order_no, CASE  WHEN  m.referance_id LIKE 'SO%' THEN (SELECT order_date FROM `sales_order`  WHERE modified_type = 'INSERTED' AND order_id =m.referance_id)  WHEN m.referance_id LIKE 'ST%' THEN (SELECT order_date FROM stock_transfer  WHERE modified_type = 'INSERTED' AND order_id = m.referance_id) WHEN m.referance_id LIKE 'PRA%' THEN (SELECT purreturndate FROM pur_return_approval_note  WHERE modified_type = 'INSERTED' AND purreturnid = m.referance_id ) ELSE  0 END AS order_date,m.weighment_status as weighment_status FROM wm_loading_advice m WHERE  m.advice_date =:fromdate  AND m.modified_type = 'INSERTED' AND m.fin_year =:finyear",nativeQuery=true)
	@Query(value="SELECT m.id as id,m.advice_id as advice_id,m.advice_date as advice_date,m.advice_no as advice_no,m.advice_type as advice_type,m.delvchallan_status as delvchallan_status,m.looseitem as looseitem,m.customer_name as customer_name,m.vehicle_no as vehicle_no,(SELECT loading_name FROM loading_point  WHERE loading_id =m.load_point) AS load_point,CASE  WHEN  m.referance_id LIKE 'SO%' THEN (SELECT order_no FROM `sales_order`  WHERE modified_type = 'INSERTED' AND order_id =m.referance_id) WHEN m.referance_id LIKE 'ST%' THEN  (SELECT order_no FROM stock_transfer  WHERE modified_type = 'INSERTED' AND order_id = m.referance_id) WHEN m.referance_id LIKE 'PRA%' THEN (SELECT purreturnno FROM pur_return_approval_note  WHERE modified_type = 'INSERTED' AND purreturnid = m.referance_id ) ELSE  0 END AS order_no, CASE  WHEN  m.referance_id LIKE 'SO%' THEN (SELECT order_date FROM `sales_order`  WHERE modified_type = 'INSERTED' AND order_id =m.referance_id)  WHEN m.referance_id LIKE 'ST%' THEN (SELECT order_date FROM stock_transfer  WHERE modified_type = 'INSERTED' AND order_id = m.referance_id) WHEN m.referance_id LIKE 'PRA%' THEN (SELECT purreturndate FROM pur_return_approval_note  WHERE modified_type = 'INSERTED' AND purreturnid = m.referance_id ) ELSE  0 END AS order_date,m.weighment_status as weighment_status, m.terminate AS terminate,m.weight_bridge_location FROM wm_loading_advice m WHERE  m.advice_date =:fromdate  AND m.modified_type = 'INSERTED' AND m.fin_year =:finyear",nativeQuery=true)
	 List<Map<String,Object>> getsearchdatanewfast(@Param("fromdate") String fromdate,@Param("finyear") String finyear);
	
	//@Query(value ="select advice_date , advice_no, customer_name, vehicle_no from wm_loading_advice where delvchallan_status=0 AND weighment_status=2 AND modified_type='INSERTED' and advice_date>=:fromdate and advice_date<=:todate",nativeQuery=true) // commented on 2025-06-02 as termanation not included
	@Query(value ="SELECT advice_date , advice_no, customer_name, vehicle_no, IF(weighment_status=2,'2nd Wgt',IF(weighment_status=1,'1st Wgt','No Wgt')) AS wgt_status FROM wm_loading_advice WHERE delvchallan_status=0 AND terminate=0 AND modified_type='INSERTED' AND advice_date>=:fromdate AND advice_date<=:todate",nativeQuery=true)
	List<Map<String, Object>> getLoadingAdviceReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value ="select count(s.id) from wm_loading_advice_itm_dtls s where s.modified_type = 'INSERTED' and s.order_id = :orderid",nativeQuery=true)
	int countlength(@Param("orderid") String orderid);
	
	@Query(value ="select delvchallan_status from wm_loading_advice s where s.modified_type = 'INSERTED' and s.referance_id = :orderid",nativeQuery=true)
	Boolean getchallan(@Param("orderid") String orderid);
	
	@Query("select s from Wm_loading_advice_itm_dtls s where s.modified_type = 'INSERTED' and s.order_id =:order_id ")
	List<Wm_loading_advice_itm_dtls> getloadingadviceviasaleorder(@Param("order_id") String order_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice_itm_dtls w SET w.modified_type ='UPDATED',w.username=:username,w.updated_on=:updated_on,w.updated_by=:username WHERE w.order_id =:order_id AND w.modified_type = 'INSERTED' ")
    int updateloadingbysaleorder(@Param("order_id") String order_id,@Param("username") String username,@Param("updated_on") LocalDateTime updated_on);
	
	@Query(value=" select case when count(*) = 0 then false else true end from wm_loading_advice w where w.modified_type = 'INSERTED' and w.advice_id =:adviceid and w.advice_no like %:type%  ", nativeQuery = true)
	int  getloadIdfast(@Param("adviceid") String adviceid, @Param("type") String type );
	
	@Query(value="select * from wm_loading_advice where modified_type='INSERTED' AND ref_doc_type='Sales Order' AND referance_id IN(:soid)",nativeQuery=true)
	List<Map<String, Object>> getLoadingAdviceReportThrouhgSO(@Param("soid") List<String> soid);
	
	@Query(value="SELECT SUM(l.quantity) as totalitemwt,w.tw_unit as weightunit FROM wm_loading_advice_itm_dtls l,wm_unload_wgmnt w WHERE l.modified_type = 'INSERTED' and w.modified_type = 'INSERTED' and l.advice_id=w.advice and l.advice_id=:loadadviceid ", nativeQuery=true)
	Map<String,Object> getLoadItemTotalWt(@Param("loadadviceid") String loadadviceid);
	
	//@Query(value="SELECT CASE WHEN jobwork = '0' THEN (SELECT SUM(w.quantity) FROM wm_loading_advice_itm_dtls w WHERE w.advice_id =:loadadviceid AND w.modified_type = 'INSERTED') ELSE (SELECT SUM(j.item_qty) FROM wm_loading_advice_item_dtls_for_jobwork j WHERE j.advice_id =:loadadviceid AND j.modified_type = 'INSERTED') END AS totalitemwt,(SELECT k.tw_unit FROM wm_unload_wgmnt k WHERE k.modified_type = 'INSERTED' AND k.advice=:loadadviceid) AS tw_unit FROM wm_loading_advice WHERE advice_id =:loadadviceid AND modified_type = 'INSERTED'", nativeQuery=true)
	//Map<String,Object> getLoadItemTotalWtjobwork(@Param("loadadviceid") String loadadviceid);
	@Query(value="SELECT CASE WHEN jobwork = '0' THEN (SELECT SUM(w.quantity) FROM wm_loading_advice_itm_dtls w WHERE w.advice_id IN (:loadadviceid) AND w.modified_type = 'INSERTED') ELSE (SELECT SUM(j.item_qty) FROM wm_loading_advice_item_dtls_for_jobwork j WHERE j.advice_id IN (:loadadviceid) AND j.modified_type = 'INSERTED') END AS totalitemwt,(SELECT k.tw_unit FROM wm_unload_wgmnt k WHERE k.modified_type = 'INSERTED' AND k.advice IN (:loadadviceid) LIMIT 1) AS tw_unit FROM wm_loading_advice WHERE advice_id IN (:loadadviceid) AND modified_type = 'INSERTED' LIMIT 1", nativeQuery=true)
	Map<String,Object> getLoadItemTotalWtjobwork(@Param("loadadviceid") List<String> loadadviceid);
	
	@Query(value="select * from wm_loading_advice_item_dtls_for_jobwork where modified_type='INSERTED' AND advice_id=:adviceid",nativeQuery=true)
	List<Map<String, Object>> loadadvicejobworkRetriveList(@Param("adviceid") String adviceid);
	
	
	@Query( "select s from Wm_loading_advice_Item_Dtls_for_jobwork s where s.modified_type = 'INSERTED' and s.advice_id =:adviceid ORDER BY s.slno")
	List<Wm_loading_advice_Item_Dtls_for_jobwork> loadingItemjobworkRetriveList(@Param("adviceid") String adviceid);
	
	@Query(value="SELECT CASE WHEN COUNT(id)>0 THEN (SELECT CASE WHEN (SUM(d.total_amt)+:total_amt)>200000 THEN 'Yes' ELSE 'No' END FROM wm_loading_advice w,wm_loading_advice_itm_dtls d WHERE w.modified_type='INSERTED' AND w.payment_mode='Cash' AND w.customer=:party AND w.advice_date=:advicedate AND w.advice_id=d.advice_id AND w.modified_type=d.modified_type) ELSE 'No' END AS res FROM wm_loading_advice WHERE modified_type='INSERTED' AND payment_mode='Cash' AND customer=:party AND advice_date=:advicedate",nativeQuery=true)
	String checkAdviceinCash(@Param("advicedate") String advicedate,@Param("party") String party,@Param("total_amt") double total_amt);
	
	@Query(value="SELECT SUM(d.total_amt) AS oldadviceamt FROM wm_loading_advice w,wm_loading_advice_itm_dtls d WHERE w.modified_type='INSERTED' AND w.advice_id=d.advice_id AND w.modified_type=d.modified_type AND w.advice_id=:advice_id",nativeQuery=true)
	double getOldAdviceAmt(@Param("advice_id") String advice_id);
	
	@Query(value="SELECT CASE WHEN COUNT(id)>0 THEN (SELECT CASE WHEN (SUM(d.total_amt)+:total_amt)>200000 THEN 'Yes' ELSE 'No' END FROM wm_loading_advice w,wm_loading_advice_itm_dtls d WHERE w.modified_type='INSERTED' AND w.payment_mode='Cash' AND w.customer=:party AND w.advice_date=:advicedate AND w.advice_id=d.advice_id AND w.modified_type=d.modified_type) ELSE 'No' END AS res FROM wm_loading_advice WHERE modified_type='INSERTED' AND payment_mode='Cash' AND customer=:party AND advice_date=:advicedate",nativeQuery=true)
	String checkAdviceinCashUpdate(@Param("advicedate") String advicedate,@Param("party") String party,@Param("total_amt") double total_amt);
	
	@Query(value="SELECT CASE WHEN COUNT(id)>0 THEN (SELECT CASE WHEN SUM(d.total_amt)>200000 THEN 'Yes' ELSE 'No' END FROM wm_loading_advice w,wm_loading_advice_itm_dtls d WHERE w.modified_type='INSERTED' AND w.payment_mode='Cash' AND w.customer=:party AND w.advice_date=:advicedate AND w.advice_id=d.advice_id AND w.modified_type=d.modified_type) ELSE 'No' END AS res FROM wm_loading_advice WHERE modified_type='INSERTED' AND payment_mode='Cash' AND customer=:party AND advice_date=:advicedate",nativeQuery=true)
	//@Query(value="SELECT CASE WHEN COUNT(id)>0 THEN 'Yes' ELSE 'No' END AS res FROM wm_loading_advice WHERE modified_type='INSERTED' AND payment_mode='Cash' AND customer=:party AND advice_date=:advicedate",nativeQuery=true)
	String custPayment(@Param("advicedate") String advicedate,@Param("party") String party);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice_Item_Dtls_for_jobwork w SET w.modified_type =:mstatus WHERE w.advice_id =:adviceid and w.modified_type='INSERTED'")
    int wm_advJobItemupdate(@Param("adviceid") String adviceid,@Param("mstatus") String mstatus);
	
	@Query( "select s from Wm_loading_advice_Item_Dtls_for_jobwork s where s.modified_type = 'INSERTED' and s.advice_id =:adviceid")
	Wm_loading_advice_Item_Dtls_for_jobwork jwDtls(@Param("adviceid") String adviceid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_loading_advice w SET w.terminate ='1', w.terminated_by=:username WHERE w.weighment_id = :wgment_id")
    int terminateloading(@Param("wgment_id") String wgment_id,@Param("username") String username);
	
	@Query(value="select * from wm_loading_advice where modified_type='INSERTED' AND advice_id=:adviceid",nativeQuery=true)
	Map<String, Object> loadAdviceDetails(@Param("adviceid") String adviceid);
	
	@Query(value="SELECT l.* FROM wm_loading_advice l,wm_unload_wgmnt w WHERE l.advice_id=w.advice AND l.modified_type='INSERTED' AND w.modified_type='INSERTED' AND wgment_id=:wid",nativeQuery=true)
	Map<String, Object> getLoadingDtlsByWeighmentId(@Param("wid") String wid);
	
}