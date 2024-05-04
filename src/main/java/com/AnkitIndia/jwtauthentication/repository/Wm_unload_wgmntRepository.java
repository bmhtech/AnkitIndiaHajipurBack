package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Vehicle_other_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Weighment_doc;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt_dtls;

public interface Wm_unload_wgmntRepository extends JpaRepository<Wm_unload_wgmnt, Long> {
	
	@Query("SELECT MAX(SUBSTRING(wgment_id , 5 , length(wgment_id))) FROM Wm_unload_wgmnt where wgment_id like %:code% ")
	Optional<String> countId(@Param("code") String code);

	@Query("select COUNT(id) from Wm_unload_wgmnt where wgment_date =:orderdate and weight2 =:weight ")
	String countWeighmentOrder(@Param("orderdate") String orderdate,@Param("weight") String weight);
	
	
	@Query(value="select COUNT(id) from wm_unload_wgmnt where wgment_date =:orderdate and weight2 =:weight and wgmentno like %:code%",nativeQuery=true)
	String countWeighmentOrdernew(@Param("orderdate") String orderdate,@Param("weight") String weight,@Param("code") String code);
	
	@Query("select w from Wm_unload_wgmnt w where w.vehicle_id = :vcode and w.modified_type != 'DELETED' ")
	Wm_unload_wgmnt getUnloadWeightment(@Param("vcode") String vcode);
	
	@Query("select w from Wm_unload_wgmnt w where w.wgment_id = :wgment_id and w.modified_type != 'DELETED' ")
	Wm_unload_wgmnt getUnloadWeightmentWt(@Param("wgment_id") String wgment_id);
	
	@Query("select w from Wm_unload_wgmnt w where w.wgment_id = :wgment_id and w.modified_type != 'DELETED' ")
	Wm_unload_wgmnt getUnloadWeightmentWtnew(@Param("wgment_id") String wgment_id);
	
	@Query( "select w from Wm_unload_wgmnt_dtls w where w.wgment_id =:code ORDER BY sl_no")
	List<Wm_unload_wgmnt> unloadWMDtlsRetriveList(@Param("code") String code);
	
	@Query( "select w from Wm_unload_wgmnt w where w.we_status !=:status and w.modified_type != 'DELETED'")
	List<Wm_unload_wgmnt> getWeighmentVehicle(@Param("status") boolean status);
	
	@Query( "select w from Wm_unload_wgmnt w where w.modified_type != 'DELETED' and w.we_status =:status ORDER BY w.wgment_id DESC ")
	List<Wm_unload_wgmnt> findAllWeighments(@Param("status") boolean status);
	
	
	@Query("select w.supplier from Wm_unload_wgmnt_dtls w where w.wgment_id = :wgment_id and w.modified_type != 'DELETED' ")
	String getSupplierCode(@Param("wgment_id") String wgment_id);
	
	@Query("select w.supplier from Wm_unload_wgmnt_dtls w where w.wgment_id = :wgment_id and w.modified_type = 'INSERTED' ")
	String getSupplierCodeNew(@Param("wgment_id") String wgment_id);
	
	@Query("select w.customer from Wm_unload_wgmnt_dtls w where w.wgment_id = :wgment_id and w.modified_type != 'DELETED' ")
	List <String> getCustomererCode(@Param("wgment_id") String wgment_id);
	
	@Query("select w.customer from Wm_unload_wgmnt_dtls w where w.wgment_id = :wgment_id and w.modified_type = 'INSERTED' ")
	List <String> getCustomererCodeNew(@Param("wgment_id") String wgment_id);
	
	@Query(value = "{call own_weighment_calculation(:unadviceid)}", nativeQuery = true)
	Wm_unload_wgmnt getUnloadWeightmentWtmultipopup(@Param("unadviceid") String unload_id);
	
	
	@Query( "select w from Vehicle_weighment_load_unload w where w.modified_type ='INSERTED' and w.weighment_status=0 AND w.gatepass_status='NA' ")
	List<Vehicle_weighment_load_unload> findVehicleList();
	
	

	//@Query( "select w from Vehicle_weighment_load_unload w where w.modified_type ='INSERTED' and w.weighment_status !=2 and  and w.we_req=1 ")
	@Query( "select w from Vehicle_weighment_load_unload w where w.modified_type ='INSERTED' and w.weighment_status !=2 and w.gatepass_status='IN' and w.we_req=1 ")
	List<Vehicle_weighment_load_unload> findVehicleListnew();
	
	@Query( "select w from Vehicle_weighment_load_unload w where w.modified_type ='INSERTED' and w.weighment_status =2 and w.gatepass_status='IN'")
	List<Vehicle_weighment_load_unload> getVehicleListWeighmenOutAuth();
	
	@Query("select w from Wm_unload_wgmnt_dtls w where w.advice = :advice_id and w.modified_type != 'DELETED' ")
	List<Wm_unload_wgmnt_dtls> getWeimentid(@Param("advice_id") String advice_id);
	
	@Query( "select w from Weighment_doc w where w.wgment_id =:code and w.modified_type ='INSERTED'")
	List<Weighment_doc> getWm_doc(@Param("code") String code);
	
	
	
	@Query(value = "{call getSearchsaleorderData(:#{#tablename},:#{#party_name},:#{#order_no},:#{#order_date},:#{#customername},:#{#orderno},:#{#fromdate},:#{#todate},:#{#finyear})}", nativeQuery = true)
	List<Wm_unload_wgmnt> getsearchdata(@Param("tablename") String tablename,@Param("party_name") String party_name,
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
	
	
	@Query(value = "{call getsearchweighmentdata(:#{#tablename},:#{#order_date},:#{#fromdate},:#{#todate})}", nativeQuery = true)
	List<Wm_unload_wgmnt> getsearchWeighmentData(@Param("tablename") String tablename,@Param("order_date") String order_date,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	
	@Query(value = "{call getsearchweighmentdatasale(:#{#tablename},:#{#order_date},:#{#fromdate},:#{#todate})}", nativeQuery = true)
	List<Wm_unload_wgmnt> getsearchweighmentdatasale(@Param("tablename") String tablename,@Param("order_date") String order_date,@Param("fromdate") String fromdate,@Param("todate") String todate);
	
	
	@Query( "select w from Wm_unload_wgmnt w where w.modified_type = 'INSERTED' and w.weight2='weight2' and w.advice=:advice_id")
	Wm_unload_wgmnt getreportwgnmet(@Param("advice_id") String advice_id);
	
	@Query(value="select id,wgment_no,vehicle_no,wgment_date,noitemname,nopartyname,gross_weight,tare_weight,net_weight,wgment_rs,we_status,wgment_id from wm_unload_wgmnt where modified_type = 'INSERTED' and fin_year=:finyear and wgment_date=:currDate and weighment_type='Other' ",nativeQuery=true)
	 List<Map<String,Object>> getOtherWeighmentDataList(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
	@Query(value="select DISTINCT nopartyname from wm_unload_wgmnt where modified_type = 'INSERTED' and weighment_type='Other' ",nativeQuery=true)
	 List<Map<String,Object>> getNopartyList();
	
	@Query(value="select w.wgment_date,w.noitemname,w.nopartyname,w.ref_doc_no,w.ref_doc_date,w.gross_weight,w.gw_unit,w.gw_date,w.gw_time,w.gw_remarks,w.tare_weight,w.tw_unit,w.tw_date,w.tw_time,w.tw_remarks,w.tarebags,w.net_weight,w.nw_unit,w.wgment_charge,w.wgment_rs,w.we_status,w.wgment_id from wm_unload_wgmnt w,vehicle_other_weighment_load_unload v where w.wgment_id=v.weighment_id and v.weighment_status=1 and w.modified_type = 'INSERTED' and w.weighment_type='Other' ",nativeQuery=true)
	 Map<String,Object> getFirstData();
	
	@Query(value="select w.wgment_date,w.noitemname,w.nopartyname,w.ref_doc_no,w.ref_doc_date,w.gross_weight,w.gw_unit,w.gw_date,w.gw_time,w.gw_remarks,w.tare_weight,w.tw_unit,w.tw_date,w.tw_time,w.tw_remarks,w.tarebags,w.net_weight,w.nw_unit,w.wgment_charge,w.wgment_rs,w.we_status,w.wgment_id,w.nopartyid,w.noitemid,w.firstbags from wm_unload_wgmnt w,vehicle_other_weighment_load_unload v where w.wgment_id=v.weighment_id and w.vehicle_id=:vehicleid and v.weighment_status=1 and w.modified_type = 'INSERTED' and w.weighment_type='Other' ",nativeQuery=true)
	 Map<String,Object> getOtherWgFirstData(@Param("vehicleid") String vehicleid);
	
	@Query( "select w from Wm_unload_wgmnt w where w.modified_type = 'INSERTED' and w.fin_year=:finyear and w.wgment_date=:currDate and w.weighment_type!='Other'")
	List<Wm_unload_wgmnt> getWeighmentData(@Param("currDate") String currDate,@Param("finyear") String finyear);
	
	//@Query(value="SELECT * FROM wm_unload_wgmnt WHERE modified_type = 'INSERTED' AND wgment_date=:currDate AND (wgment_for='Purchase Order' OR wgment_for='Sale') ",nativeQuery=true)
	@Query(value="SELECT w.*,(CASE WHEN (w.partyname LIKE 'CBP%') THEN (SELECT c.cp_name FROM cust_bussiness_partner c WHERE w.partyname=c.cp_id) ELSE (SELECT s.bp_name FROM supp_bussiness_partner s WHERE w.partyname=s.bp_id) END) AS party FROM wm_unload_wgmnt w WHERE w.modified_type = 'INSERTED' AND w.wgment_date=:currDate AND (w.wgment_for='Purchase Order' OR w.wgment_for='Sale' OR w.wgment_for='Sales Return')",nativeQuery=true)
	List<Map<String,Object>> getWeighmentDataFastList(@Param("currDate") String currDate);
	
	@Query(value="SELECT * FROM wm_unload_wgmnt w WHERE w.wgment_date >= :fdate AND w.wgment_date <= :todate AND weighment_type='Other'", nativeQuery=true)
	List<Map<String, Object>> getOtherKataReport(@Param("fdate") String fdate,@Param("todate") String todate);
	
	@Query(value="SELECT * FROM wm_unload_wgmnt w WHERE w.wgment_date >= :fdate AND w.wgment_date <= :todate AND w.weighment_type='Other' AND w.weight2='weight2' AND w.nopartyid=:party", nativeQuery=true)
	List<Map<String, Object>> getOtherKataWithPartyReport(@Param("fdate") String fdate,@Param("todate") String todate,@Param("party") String party);
	
	@Query(value="SELECT * FROM wheat_inward_view_sale_jobwork w WHERE w.weighmentdate>=:loadfromdate AND w.weighmentdate<=:load2date", nativeQuery=true)
	List<Map<String, Object>> wheat_inward_view_sale_jobwork(@Param("loadfromdate") String loadfromdate,@Param("load2date") String load2date);
	
	@Query(value="SELECT * FROM wheat_inward_view_sale_jobwork w WHERE w.weighmentdate>=:loadfromdate AND w.weighmentdate<=:load2date AND w.party=:party", nativeQuery=true)
	List<Map<String, Object>> wheat_inward_view_sale_jobworkWithParty(@Param("loadfromdate") String loadfromdate,@Param("load2date") String load2date,@Param("party") String party);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_wgmnt w SET w.terminate ='1', w.terminated_by=:username,w.ter_oth_wgmnt_no=:ter_oth_wgmnt_no,w.terminate_remarks=:terminate_remarks WHERE w.wgment_id = :wgment_id")
    int terminatekatamain(@Param("wgment_id") String wgment_id,@Param("username") String username,@Param("ter_oth_wgmnt_no") String ter_oth_wgmnt_no,@Param("terminate_remarks") String terminate_remarks);
	
	//@Query(value="SELECT w.wgment_id, w.wgment_no,w.net_weight FROM wm_unload_wgmnt w WHERE w.modified_type='INSERTED' AND w.weighment_type='Other'",nativeQuery=true)
	@Query(value="SELECT w.wgment_id, w.wgment_no,w.net_weight FROM wm_unload_wgmnt w WHERE w.modified_type='INSERTED'",nativeQuery=true)
	List<Map<String,Object>> getOtherWgnmtList();
	
	@Query("SELECT COUNT(DISTINCT advice) FROM Wm_unload_wgmnt where modified_type = 'INSERTED'")
	String countVechicle();
	
	@Query(value="SELECT wgment_no_alt FROM wm_unload_wgmnt WHERE modified_type = 'INSERTED' AND advice=:advice_id", nativeQuery = true)
	String getAltWgtNo(@Param("advice_id") String advice_id);
	
	@Query(value = "{call own_weighment_calculation_multiple_item_grn(:wgment_id)}", nativeQuery = true)
	List<Map<String,Object>> getUnloadWeightmentWtmultipopupmultipleItem(@Param("wgment_id") String wgment_id);
	
}
