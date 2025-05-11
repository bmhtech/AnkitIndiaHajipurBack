package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Status_table;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.responseDTO.DailygatewheatinwardreportDTO;

public interface Wm_unload_adviceRepository extends JpaRepository<Wm_unload_advice, Long> {
	
	@Query("SELECT MAX(SUBSTRING(unadviceid , 4 , length(unadviceid))) FROM Wm_unload_advice where unadviceid like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select COUNT(id) from Wm_unload_advice where ula_date =:orderdate and business_unit =:bunit")
	String countUAOrder(@Param("orderdate") String orderdate,@Param("bunit") String bunit);
	
	
	@Query("select COUNT(id) from Wm_unload_advice where ula_date =:orderdate and business_unit =:bunit AND item_subtype='ITMT00008'")
	String countUAOrderNEW(@Param("orderdate") String orderdate,@Param("bunit") String bunit);
	
	
	
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' ORDER BY w.unadviceid DESC")
	List<Wm_unload_advice> findAllUnloadAdvices();
	
	@Query("select w from Wm_unload_advice w where w.vehicle_id =:vcode and w.modified_type = 'INSERTED' ")
	Wm_unload_advice getUnloadAdvice(@Param("vcode") String vcode);
	
	@Query("select w from Wm_unload_advice w where w.unadviceid =:unadviceid and w.modified_type = 'INSERTED'")
	Wm_unload_advice getUnloadId(@Param("unadviceid") String unadviceid);
	
	@Query(value="select w.item_subtype from wm_unload_advice w where w.unadviceid =:unadviceid and w.modified_type = 'INSERTED'",nativeQuery=true)
	String getUnloadIdfast(@Param("unadviceid") String unadviceid);
	
	
	
	@Query("select w from Wm_unload_advice w where w.unadviceid =:unadviceid and w.modified_type = 'INSERTED'")
	Wm_unload_advice getUnloadDetails(@Param("unadviceid") String unadviceid);
	
	//@Query(value="select * from wm_unload_advice w where w.unadviceid =:unadviceid and w.modified_type = 'INSERTED'",nativeQuery=true)
	@Query(value="SELECT w.*,s.bp_code FROM wm_unload_advice w LEFT JOIN supp_bussiness_partner s ON w.busi_partner=s.bp_id WHERE w.unadviceid =:unadviceid AND w.modified_type = 'INSERTED'",nativeQuery=true)
	Map<String,Object> getUnloadDetailsFastApi(@Param("unadviceid") String unadviceid);
	
	@Query(value="select * from Wm_unload_advice  where unadviceid =:unadviceid and modified_type = 'INSERTED'",nativeQuery=true)
	Wm_unload_advice getUnloadDetailsnew(@Param("unadviceid") String weighmentdata);
	
	@Query("select w from Wm_unload_advice w where w.vehicle_id =:vehicleid and w.modified_type = 'INSERTED' AND w.ref_type='Stock Transfer' AND w.weighment_status ='2'")
	Wm_unload_advice getUnloadAdvIdThruVehi(@Param("vehicleid") String vehicleid);
	
	@Query("select w from Wm_unload_advice w where w.busi_partner = :bpartner and w.modified_type = 'INSERTED' ")
	List<Wm_unload_advice> getUnloadCodeList(@Param("bpartner") String bpartner);
	
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.unload_status !='1' ")
	List<Wm_unload_advice> getUnloadCodeList();
	
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.advice_type='Purchase Order' AND w.we_req='1' AND w.weighment_status !='2' ")
	List<Wm_unload_advice> getUnloadVehiThruPurchase();
	
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.advice_type='Sales Return' AND w.we_req='1' AND w.weighment_status !='2' ")
	List<Wm_unload_advice> getUnloadVehiThruSR();
	
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.advice_type='Stock Transfer' AND w.weighment_status !='2' ")
	List<Wm_unload_advice> getUnloadVehiThruStkTransfer();
	
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.ref_type='Open Advice' AND w.weighment_status !='2' ")
	List<Wm_unload_advice> getUnloadAdvRefOpenAdv();
	
	@Query(value = "select p.rest_wt from pur_order_view p where p.pur_orderid =:pur_orderid", nativeQuery=true)
	 double getpurchaserestwt(@Param("pur_orderid") String pur_orderid);
	
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.ref_type='Open Advice' AND w.weighment_status ='2' ")
	List<Wm_unload_advice> getUnloadAdvRefOpenAdvWt2();
	
	@Query(value="select * from wm_unload_advice w where w.modified_type = 'INSERTED' and w.ref_type='Open Advice' and w.weighment_status ='2' ", nativeQuery=true)
	List<Map<String, Object>> getUnloadAdvRefOpenAdvWt2New();
	
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.ref_type='Open Advice' AND w.qc_required =:status AND w.qc_status !='Yes' AND w.grn_status=0")
	List<Wm_unload_advice> getUnloadAdvRefOpenAdvQc(@Param("status") boolean status);
	
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.ref_type='Purchase Order' AND w.weighment_status !='2'  ")
	List<Wm_unload_advice> getUnloadAdvRefPO();
	
	//@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.ref_type='Purchase Order' AND w.weighment_status =2 AND w.business_unit =:bunit AND w.busi_partner =:supplier and w.item_type =:itype and w.item_subtype =:ptype and w.purchase_subtype =:psubtype and w.ula_date <=:orderdate")
	//@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' ")
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.ref_type='Purchase Order' AND w.weighment_status =2 AND w.business_unit =:bunit AND w.busi_partner =:supplier and w.item_type =:itype and w.item_subtype =:ptype and w.purchase_subtype =:psubtype and w.grn_status =0 and w.ula_date <=:orderdate")
	List<Wm_unload_advice> getUnloadAdvRefPOwt2(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("itype") String itype,@Param("ptype") String ptype,@Param("psubtype") String psubtype,@Param("orderdate") String orderdate);
	
	@Query(value="select u.unadviceid as unadviceid,u.unload_status as unload_status,u.unadviceno as unadviceno,u.weighment_status as weighment_status,(select wgment_no from wm_unload_wgmnt where wgment_id =u.weighment_id and modified_type = 'INSERTED') as weighment_id,u.item_subtypename as item_subtypename,u.supp_name as supp_name from wm_unload_advice u where u.modified_type ='INSERTED' AND u.ref_type='Purchase Order' AND u.weighment_status =2 AND u.business_unit =:bunit AND u.busi_partner =:supplier and u.item_type =:itype and u.item_subtype =:ptype and u.purchase_subtype =:psubtype and u.grn_status =0 and u.terminate =0 and u.ula_date <=:orderdate", nativeQuery=true)
	List<Map<String, Object>> getUnloadAdvRefPOwt2Fast(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("itype") String itype,@Param("ptype") String ptype,@Param("psubtype") String psubtype,@Param("orderdate") String orderdate);
	
	@Query(value="select u.unadviceid as unadviceid,u.unload_status as unload_status,u.unadviceno as unadviceno,u.weighment_status as weighment_status,(select wgment_no from wm_unload_wgmnt where wgment_id =u.weighment_id and modified_type = 'INSERTED') as weighment_id,u.item_subtypename as item_subtypename,u.supp_name as supp_name from wm_unload_advice u where u.modified_type ='INSERTED' AND u.ref_type='Purchase Order' AND u.weighment_status =2 AND u.business_unit =:bunit AND u.busi_partner =:supplier and u.item_type =:itype and u.item_subtype =:ptype and u.purchase_subtype =:psubtype and u.grn_status =0 and u.terminate =0 and u.ula_date <=:orderdate AND u.qc_status='Yes'", nativeQuery=true)
	List<Map<String, Object>> getUnloadAdvRefPOwt2FastRawQC(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("itype") String itype,@Param("ptype") String ptype,@Param("psubtype") String psubtype,@Param("orderdate") String orderdate);

	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.ref_type='Purchase Order' AND w.weighment_status =0 AND w.we_req=0 AND w.business_unit =:bunit AND w.busi_partner =:supplier and w.item_type =:itype and w.item_subtype =:ptype and w.purchase_subtype =:psubtype and w.grn_status =0 and w.ula_date <=:orderdate")
	List<Wm_unload_advice> getunloadstore(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("itype") String itype,@Param("ptype") String ptype,@Param("psubtype") String psubtype,@Param("orderdate") String orderdate);
	
	
	//@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.ref_type='Purchase Order' AND w.weighment_status =2 AND w.business_unit =:bunit AND w.busi_partner =:supplier and w.item_type =:itype and w.item_subtype =:ptype and w.purchase_subtype =:psubtype and w.ula_date <=:orderdate")
	//	@Query(value = "select * from Wm_unload_advice where modified_type = 'INSERTED' AND weighment_status =2 ", nativeQuery=true)
	//	List<Wm_unload_advice> getUnloadAdvRefPOwt2Argnew(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("itype") String itype,@Param("ptype") String ptype,@Param("psubtype") String psubtype,@Param("orderdate") String orderdate);
		
		@Query(value = "{call GetUnloadStatus(:#{#bunit},:#{#supplier},:#{#itype},:#{#ptype},:#{#psubtype},:#{#orderdate})}", nativeQuery = true)
		List<Wm_unload_advice> getUnloadAdvRefPOwt2Argnew(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("itype") String itype,@Param("ptype") String ptype,@Param("psubtype") String psubtype,@Param("orderdate") String orderdate);
		
		 
		
		
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.ref_type='Purchase Order' AND w.qc_required =:status AND w.qc_status !='Yes' AND w.grn_status=0 ")
	List<Wm_unload_advice> getUnloadAdvRefPOQc(@Param("status") boolean status);
	
	//@Query(value="select id,unadviceid,unadviceno,ula_date from wm_unload_advice where ref_type='Purchase Order' and modified_type='INSERTED' AND qc_required =:status AND weighment_status !='2'",nativeQuery=true)
	@Query(value="select id,unadviceid,unadviceno,ula_date,vehicle_no,vehicle_id from wm_unload_advice where ref_type='Purchase Order' and modified_type='INSERTED' AND qc_required =:status AND qc_status !='Yes' AND grn_status=0",nativeQuery=true)
	List<Map<String,Object>> getUnloadAdvRefPOQcNew(@Param("status") boolean status);

	//@Query("select w from Wm_unload_advice w where w.vehicle_id = :vcode and w.modified_type = 'INSERTED' and advice_type =:weighment and w.weighment_status !='2' ")
	//List<Wm_unload_advice> getUnloadAdviceThruVehicle(@Param("vcode") String vcode,@Param("weighment") String weighment);
	
	  @Query(value = "{call weighment_status_check(:vcode,:weighment)}", nativeQuery=true)
	List<Wm_unload_advice> getUnloadAdviceThruVehicle(@Param("vcode") String vcode,@Param("weighment") String weighment);
	
	@Query(value = "{call weighment_status_check(:vcode,:weighment)}", nativeQuery=true)
	public List<Map<String, Object>> getUnloadAdviceThruVehiclefast(@Param("vcode") String vcode,@Param("weighment") String weighment);
	
	@Query("select w from Wm_unload_advice w where w.modified_type = 'INSERTED' AND w.business_unit =:bunit AND w.ref_type='Stock Transfer' AND w.weighment_status ='2' and w.ula_date <=:orderdate")
	List<Wm_unload_advice> getUnloadAdvVehiThruBU(@Param("bunit") String bunit,@Param("orderdate") String orderdate);
	
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice w SET w.unload_status ='1',w.weighment_status='1',w.weighment_id =:wid WHERE w.unadviceid = :unadviceid")
    int updateStatus(@Param("unadviceid") String unadviceid,@Param("wid") String wid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice w SET w.unload_status ='1',w.weighment_status='2',w.weighment_id =:wid WHERE w.unadviceid = :unadviceid")
    int updateUnloadStatus(@Param("unadviceid") String unadviceid,@Param("wid") String wid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice w SET w.ref_type ='Purchase Order',w.advice_type='Purchase Order',w.pur_orderid=:po_number,w.adv_po_tag_no =:advpotagno,w.adv_po_tag_date=:advpotagdate,w.purchase_subtype=:purchasesubtype WHERE w.unadviceid = :advice_no" )
    int updateTagAdvicePO(@Param("advice_no") String advice_no,@Param("po_number") String po_number,@Param("advpotagno") String advpotagno,@Param("advpotagdate") String advpotagdate,@Param("purchasesubtype") String purchasesubtype);
	
	@Query(value = "{call weight_check_details(:#{#pur_orderid}, :#{#item_code}, :#{#mat_wt})}", nativeQuery = true)
	Status_table checkweightunloadadvice(@Param("pur_orderid") String pur_orderid,@Param("item_code") String item_code,@Param("mat_wt") Double mat_wt);
	

	
	//@Procedure(value = "call unload_advice_updation")
	//int getUnload_advice_updation( String pur_orderid);
	
	@Query(value = "{call unload_advice_updation(:pur_orderid)}", nativeQuery = true)
	List<Status_table> getUnload_advice_updation(@Param("pur_orderid") String pur_orderid);
	
	@Query("select w from Wm_unload_advice w where w.unadviceid =:unadviceid and w.weighment_status =2 and w.modified_type = 'INSERTED'")
	Wm_unload_advice getUnloadadvanceList(@Param("unadviceid") String unadviceid);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice w SET w.ref_type ='Open Advice',w.pur_orderid='0',w.adv_po_tag_no ='No',w.adv_po_tag_date='No',w.purchase_subtype='0' WHERE w.unadviceid = :unadviceid" )
    int updateTagAdvicePODeleteTime(@Param("unadviceid") String unadviceid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_item_dtls w SET w.pur_orderid='' WHERE w.unadviceid = :unadviceid" )
    int updateUnAdvItemDetails(@Param("unadviceid") String unadviceid);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Vehicle_weighment_load_unload w SET w.load_unload_status ='1',w.weighment_status='1',w.weighment_id =:wid WHERE w.reference_id = :unadviceid")
    int updateLoadUnloadStatus(@Param("unadviceid") String unadviceid,@Param("wid") String wid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Vehicle_weighment_load_unload w SET w.load_unload_status ='1',w.weighment_status='2',w.weighment_id =:wid WHERE w.reference_id = :unadviceid")
    int updateAfterLoadUnloadStatus(@Param("unadviceid") String unadviceid,@Param("wid") String wid);
	
	@Query("select w from Wm_unload_advice w where w.advice_type ='Sales Return' and w.referance_id=:referance_id and w.weighment_status =2 and w.modified_type = 'INSERTED'")
	Wm_unload_advice getTallyvehicleno(@Param("referance_id") String referance_id);
	
	@Query("select w from Wm_unload_advice w where w.unadviceid=:unadviceid and w.modified_type = 'INSERTED'")
	Wm_unload_advice getUnloadAdviceData(@Param("unadviceid") String unadviceid);
	

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice w SET w.grn_status =1 WHERE w.unadviceid = :referance_id and  w.modified_type ='INSERTED'")
    int getrevert(@Param("referance_id") String referance_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order w SET w.grn_status =1 WHERE w.pur_orderid = :referance_id and w.grn_status=1 and  w.modified_type ='INSERTED'")
    int getundorevert(@Param("referance_id") String referance_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order w SET w.unload_status =1 WHERE w.pur_orderid = :referance_id and  w.modified_type ='INSERTED' and w.madvice_sin_grn=0")
    int getpurOrderUnloadUpdate(@Param("referance_id") String referance_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order w SET w.unload_status =0 WHERE w.pur_orderid = :referance_id and w.unload_status=1 and  w.modified_type ='INSERTED' and w.madvice_sin_grn=0")
    int getpurOrderUnloadRevert(@Param("referance_id") String referance_id);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Wm_unload_advice pl where pl.modified_type != 'DELETED' and pl.vehicle_id=:veh_id and pl.weighment_status=0 and pl.item_subtypename!='STORE PURCHASE' ")
	Boolean checkVehicleNo(@Param("veh_id") String veh_id);
	
	@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Wm_unload_advice> getsearchdata(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,
			@Param("todate") String todate,@Param("customername") String customername,
			@Param("finyear") String finyear);
	
	//@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	@Query(value = "{call getSearchPurchaseData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Map<String, Object>> getSearchUnloadAdvice(@Param("tablename") String tablename,@Param("party_name") String party_name,
			@Param("order_no") String order_no,@Param("order_date") String order_date, 
			@Param("orderno") String orderno,@Param("fromdate") String fromdate,
			@Param("todate") String todate,@Param("customername") String customername,
			@Param("finyear") String finyear);
	
	
	@Query(value = "SELECT w.unadviceno AS unadviceno,w.ula_date AS ula_date,CASE WHEN w.ref_type = 'Stock Transfer' THEN 'None' WHEN w.advice_type ='Purchase Order' THEN (SELECT b.bp_name FROM supp_bussiness_partner b WHERE b.bp_Id =w.busi_partner AND b.modified_type = 'INSERTED') WHEN w.advice_type ='Sales Return' THEN (SELECT s.cp_name FROM cust_bussiness_partner s WHERE s.modified_type = 'INSERTED' AND s.cp_Id =w.customer) ELSE '' END AS customer, CASE WHEN w.advice_type ='Purchase Order' THEN (SELECT p.pur_order_no FROM pur_order p WHERE p.pur_orderid =w.referance_id AND p.modified_type = 'INSERTED') ELSE 'NA' END AS po_no, w.business_unitname AS business_unitname,w.item_subtypename AS item_subtypename,w.vehicle_no AS vehicle_no, w.ref_type AS ref_type,w.grn_status AS grn_status,w.unadviceid AS unadviceid,w.id AS id,w.weighment_status AS weighment_status,w.weight_bridge_location FROM wm_unload_advice  w WHERE w.ula_date =:date AND w.modified_type = 'INSERTED'  ",nativeQuery=true)
	List<Map<String, Object>> getunloadlistfast(@Param("date") String date);
	
	
	@Query(value = "{call dailygatewheatinwardreport(:#{#date})}", nativeQuery = true)
	List<Object[]> getdailygatewheatinwardreport(@Param("date") String date);
	
	
	@Query("select s from Wm_unload_advice s where s.modified_type = 'INSERTED' and s.ula_date>=:date and s.ula_date<=:todate  ")
	List<Wm_unload_advice> getdetailswithdate(@Param("date") String date,@Param("todate") String todate);
	
	
	@Query("select s from Wm_unload_advice s where s.modified_type = 'INSERTED' and s.referance_id=:salereturnid ")
	Wm_unload_advice getunloadfromreturnid(@Param("salereturnid") String salereturnid);
	
	@Query("select s from Vehicle_weighment_load_unload s where s.modified_type = 'INSERTED' and s.reference_id=:referance_id and s.load_unload_status ='1' and s.weighment_status='2' ")
	Vehicle_weighment_load_unload getvechiledetails(@Param("referance_id") String referance_id);
	
	//@Query(value = "select w.advicenumber,w.partyname,w.vehicleno,w.pur_order_no,w.description,w.slipnumber,w.tw_date,w.tarebags,w.timein,w.timeout,w.tareweight,w.grossweight,w.netweight,w.drivername,w.drivernumber from wheat_inward_view w where w.uladate >= :date and w.uladate <= :todate ", nativeQuery=true)
	@Query(value = "select w.advicenumber,w.partyname,w.vehicleno,w.supp_ref_docno,w.description,w.slipnumber,w.tw_date,w.tarebags,w.timein,w.timeout,w.tareweight,w.grossweight,w.netweight,w.drivername,w.drivernumber,w.Item_name from wheat_inward_view w where w.uladate >= :date and w.uladate <= :todate ", nativeQuery=true)
	List<Object[]> wheat_inward_view(@Param("date") String date,@Param("todate") String todate);
	
	//@Query(value = "select w.advicenumber,w.partyname,w.vehicleno,w.supp_ref_docno,w.description,w.slipnumber,w.tw_date,w.tarebags,w.timein,w.timeout,w.tareweight,w.grossweight,w.netweight,w.drivername,w.drivernumber,w.Item_name,w.weighmentdate,w.digital_weight,w.digital_weight1,w.pur_order_no from wheat_inward_view w where w.uladate >= :date and w.uladate <= :todate ", nativeQuery=true)
	@Query(value="SELECT w.advicenumber AS advicenumber,w.partyname AS partyname,w.vehicleno AS vehicleno,w.supp_ref_docno AS supp_ref_docno,w.description AS description,w.slipnumber AS slipnumber,w.gw_date AS gw_date,w.tw_date AS tw_date,w.tarebags AS tarebags,w.timein AS timein,w.timeout AS timeout,w.tareweight AS tareweight,w.grossweight AS grossweight,w.netweight AS netweight,w.item_name AS item_name,w.weighmentdate AS weighmentdate,w.digital_weight AS digital_weight,w.digital_weight1 AS digital_weight1,w.pur_order_no AS pur_order_no,w.gw_remarks AS gw_remarks,w.tw_remarks AS tw_remarks,w.remarks AS remarks FROM wheat_inward_view w WHERE w.weighmentdate >= :fdate AND w.weighmentdate <= :todate", nativeQuery=true)
	List<Map<String, Object>> wheat_inward_view_new(@Param("fdate") String fdate,@Param("todate") String todate);
	
	@Query(value="SELECT w.advicenumber AS advicenumber,w.partyname AS partyname,w.vehicleno AS vehicleno,w.supp_ref_docno AS supp_ref_docno,w.description AS description,w.slipnumber AS slipnumber,w.gw_date AS gw_date,w.tw_date AS tw_date,w.tarebags AS tarebags,w.timein AS timein,w.timeout AS timeout,w.tareweight AS tareweight,w.grossweight AS grossweight,w.netweight AS netweight,w.drivername AS drivername,w.drivernumber AS drivernumber,w.item_name AS item_name,w.weighmentdate AS weighmentdate,w.digital_weight AS digital_weight,w.digital_weight1 AS digital_weight1,w.pur_order_no AS pur_order_no,w.gw_remarks AS gw_remarks,w.tw_remarks AS tw_remarks,w.remarks AS remarks FROM wheat_inward_view_sale w WHERE w.weighmentdate >= :fdate AND w.weighmentdate <= :todate", nativeQuery=true)
	List<Map<String, Object>> wheat_inward_view_new_sale(@Param("fdate") String fdate,@Param("todate") String todate);

	@Query(value="SELECT w.advicenumber AS advicenumber,w.partyname AS partyname,w.vehicleno AS vehicleno,w.supp_ref_docno AS supp_ref_docno,w.description AS description,w.slipnumber AS slipnumber,w.gw_date AS gw_date,w.tw_date AS tw_date,w.tarebags AS tarebags,w.timein AS timein,w.timeout AS timeout,w.tareweight AS tareweight,w.grossweight AS grossweight,w.netweight AS netweight,w.item_name AS item_name,w.weighmentdate AS weighmentdate,w.digital_weight AS digital_weight,w.digital_weight1 AS digital_weight1,w.pur_order_no AS pur_order_no,w.gw_remarks AS gw_remarks,w.tw_remarks AS tw_remarks,w.remarks AS remarks FROM wheat_inward_view w WHERE w.weighmentdate >= :fdate AND w.weighmentdate <= :todate AND w.party=:supplier", nativeQuery=true)
	List<Map<String, Object>> wheat_inward_view_new_withparty(@Param("fdate") String fdate,@Param("todate") String todate,@Param("supplier") String supplier);
	
	@Query(value="SELECT w.advicenumber AS advicenumber,w.partyname AS partyname,w.vehicleno AS vehicleno,w.supp_ref_docno AS supp_ref_docno,w.description AS description,w.slipnumber AS slipnumber,w.gw_date AS gw_date,w.tw_date AS tw_date,w.tarebags AS tarebags,w.timein AS timein,w.timeout AS timeout,w.tareweight AS tareweight,w.grossweight AS grossweight,w.netweight AS netweight,w.drivername AS drivername,w.drivernumber AS drivernumber,w.item_name AS item_name,w.weighmentdate AS weighmentdate,w.digital_weight AS digital_weight,w.digital_weight1 AS digital_weight1,w.pur_order_no AS pur_order_no,w.gw_remarks AS gw_remarks,w.tw_remarks AS tw_remarks,w.remarks AS remarks FROM wheat_inward_view_sale w WHERE w.weighmentdate >= :fdate AND w.weighmentdate <= :todate AND w.party=:customer", nativeQuery=true)
	List<Map<String, Object>> wheat_inward_view_new_sale_withparty(@Param("fdate") String fdate,@Param("todate") String todate,@Param("customer") String customer);

	
	@Query(value = "select referance_id from wm_unload_advice w where w.unadviceid =:unadviceid and w.modified_type = 'INSERTED'", nativeQuery=true)
	String getUnloadPurId(@Param("unadviceid") String unadviceid);
	
	@Query(value = "select pur_return_status from wm_unload_advice w where w.unadviceid NOT IN (:unadviceid) and w.referance_id=:referance_id   and w.modified_type = 'INSERTED'", nativeQuery=true)
	List<String> checkpurorderuniquerespcttounload(@Param("unadviceid") String unadviceid, @Param("referance_id") String referance_id);
	
	@Query(value = "select pur_return_status from wm_unload_advice w where w.unadviceid NOT IN (:unadviceid) and w.referance_id=:referance_id and w.pur_return_status='Yes'  and w.modified_type = 'INSERTED'", nativeQuery=true)
	List<String> checkpurorderuniquerespcttounloadmultiple(@Param("unadviceid") String unadviceid, @Param("referance_id") String referance_id);
	
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice w SET w.pur_return_status='Yes',w.purreturnid=:purreturnid  WHERE w.unadviceid = :unadviceid and w.modified_type = 'INSERTED'" ) 
    int updatereturnstatus(@Param("unadviceid") String unadviceid,@Param("purreturnid") String purreturnid);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Wm_unload_advice p SET p.pur_return_status ='No' , p.purreturnid='NA' WHERE p.purreturnid = :purreturnid" )
	int updateTimeUnloadAdvicePurReturn(@Param("purreturnid") String purreturnid);
	
	@Query(value = "select unadviceid from wm_unload_advice w where  w.modified_type = 'INSERTED' and w.referance_id=:pur_orderid", nativeQuery=true)
	List<String> getunlaodidbyporder(@Param("pur_orderid") String pur_orderid);
	
	@Query(value="SELECT u.ula_date AS ula_date,u.unadviceno AS unadviceno,u.ref_type AS ref_type,u.supp_name AS supp_name,u.item_type AS item_type,u.item_subtypename AS item_subtypename,u.supp_ref_doc AS supp_ref_doc,u.supp_ref_docno AS supp_ref_docno,u.supp_ref_doc_date AS supp_ref_doc_date,CASE WHEN u.remarks IS NULL THEN '' ELSE u.remarks END AS remarks,u.vehicle_no AS vehicle_no,c.description AS uom,i.item_name AS item_name,i.packing_name AS packing_name,i.quantity AS quantity,i.s_qty AS s_qty,i.mat_wt AS mat_wt,p.gross_wt AS gross_wt,c.description AS uom1,p.tare_wt AS tare_wt,c.description AS uom2,p.net_wt AS net_wt,c.description AS uom3,p.slip_no AS slip_no,p.pw_date AS pw_date FROM wm_unload_advice u,wm_unload_advice_item_dtls i,wm_unload_advice_party_wm p,custom_uom_master c WHERE u.modified_type='INSERTED' AND u.uom=c.`customuom_id` AND i.modified_type='INSERTED' AND p.modified_type='INSERTED' AND u.unadviceid = i.unadviceid AND u.unadviceid=p.unadviceid AND i.unadviceid=p.unadviceid AND u.ula_date>=:fromdate AND u.ula_date<=:todate",nativeQuery=true)
	List<Map<String, Object>> getUnloadAdvReportwithoutSupplier(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value="SELECT u.ula_date AS ula_date,u.unadviceno AS unadviceno,u.ref_type AS ref_type,u.supp_name AS supp_name,u.item_type AS item_type,u.item_subtypename AS item_subtypename,u.supp_ref_doc AS supp_ref_doc,u.supp_ref_docno AS supp_ref_docno,u.supp_ref_doc_date AS supp_ref_doc_date,CASE WHEN u.remarks IS NULL THEN '' ELSE u.remarks END AS remarks,u.vehicle_no AS vehicle_no,c.description AS uom,i.item_name AS item_name,i.packing_name AS packing_name,i.quantity AS quantity,i.s_qty AS s_qty,i.mat_wt AS mat_wt,p.gross_wt AS gross_wt,c.description AS uom1,p.tare_wt AS tare_wt,c.description AS uom2,p.net_wt AS net_wt,c.description AS uom3,p.slip_no AS slip_no,p.pw_date AS pw_date FROM wm_unload_advice u,wm_unload_advice_item_dtls i,wm_unload_advice_party_wm p,custom_uom_master c WHERE u.modified_type='INSERTED' AND u.uom=c.`customuom_id` AND i.modified_type='INSERTED' AND p.modified_type='INSERTED' AND u.unadviceid = i.unadviceid AND u.unadviceid=p.unadviceid AND i.unadviceid=p.unadviceid AND u.ula_date>=:fromdate AND u.ula_date<=:todate AND u.busi_partner=:suppliername",nativeQuery=true)
	List<Map<String, Object>> getUnloadAdvReportwithSupplier(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("suppliername") String suppliername);
	
	
	@Query(value="SELECT * FROM wm_unload_advice WHERE ref_type='Purchase Order' AND modified_type='INSERTED' AND referance_id IN (:pur_orderid) ", nativeQuery=true)
	List<Map<String,Object>> getUnloadListThroughPurOrderId(@Param("pur_orderid") List<String> pur_orderid);
	
	@Query(value="SELECT * FROM wm_unload_advice WHERE modified_type='INSERTED' AND unadviceid =:unadviceid ", nativeQuery=true)
	List<Map<String,Object>> getUnloadDetailsThroughUnloadId(@Param("unadviceid") String unadviceid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice r SET r.qc_status ='Yes' WHERE r.unadviceid = :unadviceid AND r.modified_type='INSERTED'")
    int updateUnloadQcStatus(@Param("unadviceid") String unadviceid);
	
	@Query(value="SELECT unadviceid,unadviceno,ref_type,uladate,vehicle_no,referance_id,(SELECT cp_name FROM cust_bussiness_partner WHERE modified_type ='INSERTED' AND cp_id =:party) AS customer_name FROM wm_unload_advice WHERE modified_type='INSERTED' and uladate<=:unadvicedate and business_unit=:bunit and customer=:party and item_subtype='ITMT00009' ", nativeQuery=true)
	List<Map<String,Object>> getSalesReturnNoteJobwork(@Param("unadvicedate") String unadvicedate,@Param("bunit") String bunit,@Param("party") String party);
	
	@Query(value="select * from wm_unload_advice_item_dtls_for_jobwork where modified_type='INSERTED' AND unadviceid=:unadviceid",nativeQuery=true)
	List<Map<String, Object>> unloadadvicejobworkRetriveList(@Param("unadviceid") String unadviceid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice w SET w.terminate ='1', w.terminated_by=:username WHERE w.weighment_id = :wgment_id")
    int terminateunloading(@Param("wgment_id") String wgment_id,@Param("username") String username);
	
	@Query(value = "SELECT CASE WHEN w.grn_status=1 THEN 'YES' ELSE 'NO' END FROM wm_unload_advice w WHERE w.modified_type = 'INSERTED' AND w.unadviceid=:advice_id", nativeQuery=true)
	String checkQC(@Param("advice_id") String advice_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice r SET r.qc_status ='No' WHERE r.unadviceid = :unadviceid AND r.modified_type='INSERTED'")
    int updateUnloadAdviceQCStatus(@Param("unadviceid") String unadviceid);
	
	@Query(value = "{call GetMultiUnloadStatusSingleGRN(:#{#bunit},:#{#supplier},:#{#itype},:#{#ptype},:#{#psubtype},:#{#orderdate})}", nativeQuery = true)
	List<Map<String, Object>> getUnloadAdvRefPOwt2ArgnewMultiItemGRN(@Param("bunit") String bunit,@Param("supplier") String supplier,@Param("itype") String itype,@Param("ptype") String ptype,@Param("psubtype") String psubtype,@Param("orderdate") String orderdate);	 
	
	@Query(value="SELECT x1.pur_orderid,x1.pur_order_no,x1.supplier,u.unadviceno,u.supp_name,u.ula_date,u.weighment_status,u.grn_status \r\n"
			+ "FROM (SELECT p.pur_orderid,p.pur_order_no,p.supplier,p1.term_pur_ord,p.ord_date FROM pur_order_termination p1 LEFT JOIN pur_order p ON p1.pur_orderid= p.pur_orderid WHERE p1.modified_type='INSERTED' AND p.modified_type='INSERTED') x1\r\n"
			+ "LEFT JOIN wm_unload_advice u ON x1.pur_orderid=u.`referance_id` WHERE u.modified_type='INSERTED' AND u.terminate='0' AND x1.term_pur_ord='0' AND u.grn_status='0' AND x1.ord_date>=:fromdate AND x1.ord_date<=:todate", nativeQuery=true)
	List<Map<String,Object>> searchpendingUnAdviceReport(@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	@Query(value = "SELECT w.weighment_id FROM wm_unload_advice w,pur_good_receipt p WHERE w.`unadviceid`=p.`referance_id` AND p.`modified_type`='INSERTED' AND w.`modified_type`='INSERTED' AND p.`grn_id`=:grnid", nativeQuery=true)
	String getWeimentId(@Param("grnid") String grnid);
	
	@Query(value = "SELECT w.weighment_id,w.vehicle_id,w.vehicle_no FROM wm_unload_advice w,pur_good_receipt p WHERE w.`unadviceid`=p.`referance_id` AND p.`modified_type`='INSERTED' AND w.`modified_type`='INSERTED' AND p.`grn_id`=:grnid", nativeQuery=true)
	Map<String,Object> getGrnWeighment(@Param("grnid") String grnid);
	
	@Query(value="SELECT w.`unadviceid`,w.`ula_date`,w.`unadviceno`,w.`referance_id`,w.`vehicle_id`,wi.`item_code`,wi.`item_name`,wi.`packing`,wi.`packing_name`,wi.`s_qty`,wi.`quantity`,wi.itc_item_qty \r\n"
			+ "FROM `wm_unload_advice_item_dtls` wi LEFT JOIN `wm_unload_advice` w ON wi.`unadviceid`=w.`unadviceid` AND w.`modified_type`='INSERTED' WHERE wi.`modified_type`='INSERTED' AND w.id=:id AND wi.`unadviceid`=:unadviceid", nativeQuery=true)
	List<Map<String,Object>> getUnloadItemDtls(@Param("id") Long id, @Param("unadviceid") String unadviceid);
}
