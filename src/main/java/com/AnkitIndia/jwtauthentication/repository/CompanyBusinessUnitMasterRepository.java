package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Company_business_unit_master;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_vehicle_dtls;

public interface CompanyBusinessUnitMasterRepository extends JpaRepository<Company_business_unit_master, Long>{
	
	@Query("SELECT MAX(SUBSTRING(businessunit_id , 4 , length(businessunit_id))) FROM Company_business_unit_master where businessunit_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select b from Company_business_unit_master b where b.modified_type = 'INSERTED' and b.company_id =:company")
	List<Company_business_unit_master> getcompanyBUMNCList(@Param("company") String company);
	//Delete
	@Query("select b from Company_business_unit_master b where b.modified_type = 'INSERTED'")
	List<Company_business_unit_master> getcompanyBUMNCList();
	
	@Query( "select b from Company_business_unit_master b where b.businessunit_code = :code and b.modified_type = 'INSERTED'")
	Company_business_unit_master buNameByCode(@Param("code") String code);
	
	@Query( "select b from Company_business_unit_master b where b.businessunit_id = :Id and b.modified_type = 'INSERTED'")
	Company_business_unit_master CompanyBUAddress(@Param("Id") String Id);

	@Query( "select c from Company_business_unit_details c where c.businessunit_id = :b_id and c.modified_type = 'INSERTED'")
	List<Company_business_unit_master> compBURetriveList(@Param("b_id") String b_id);
	
	@Query("select b from Company_business_unit_master b where b.modified_type = 'INSERTED' and b.businessunit_id !=:bunitid")
	List<Company_business_unit_master> getCompBusinessUnitDiff(@Param("bunitid") String bunitid);
	
	@Query("select max(substring(businessunit_code , 8, length(businessunit_code))) from Company_business_unit_master where businessunit_code like %:code% and company_id =:company ")
	String getCompanyBusiSequenceId(@Param("code") String code,@Param("company") String company);
	
	@Procedure(name = "Company_business_unit_master.saveGrpSubParent")
	int saveGrpSubParent(@Param("bunitid") String bunitid,@Param("grpsdata") String grpsdata);
	
	@Query("SELECT DISTINCT c.businessunit_id FROM Company_business_unit_master c where c.company_id =:company_id and c.modified_type = 'INSERTED'")
    Optional<String> getCBUDtlsThruCompany(@Param("company_id") String company_id);
	
	
	
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order pl where pl.modified_type != 'DELETED' and pl.businessunit=:code")
	Boolean checkBussinessUnitPurchaseOrderUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Sales_Order pl where pl.modified_type != 'DELETED' and pl.business_unit=:code")
	Boolean checkBussinessUnitSalesOrderUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Wm_unload_advice_item_dtls pl where pl.modified_type != 'DELETED' and pl.wearhouse=:code")
	Boolean checkWareHouseUnloadAdviceUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Wm_loading_advice_itm_dtls pl where pl.modified_type != 'DELETED' and pl.warehouse=:code")
	Boolean checkWareHouseLoadAdviceUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Bin_master pl where pl.modified_type != 'DELETED' and pl.warehouse_code=:code")
	Boolean checkBinMasterUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Item_master pl where pl.modified_type != 'DELETED' and pl.mstock_unit=:code")
	Boolean checkItemMasterUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Trans_bussiness_partner_vehicle_dtls pl where pl.modified_type != 'DELETED' and pl.vehicle_name=:code")
	Boolean checkTransMasterUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Wm_unload_advice pl where pl.modified_type != 'DELETED' and pl.vehicle_id=:code")
	Boolean checkUnloadAdviceUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Wm_loading_advice pl where pl.modified_type != 'DELETED' and pl.vehicle_id=:code")
	Boolean checkLoadAdviceUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order_Item_Details pl where pl.modified_type != 'DELETED' and pl.to_be_used=:code")
	Boolean checkPurOrderItemDetailsUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Trans_bussiness_partner_vehicle_dtls pl where pl.modified_type != 'DELETED' and pl.vehicle_type=:code")
	Boolean checTransVehicleDtlsUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM VehicleMaster pl where pl.modified_type != 'DELETED' and pl.vehtype_code=:code")
	Boolean checkVehicleMasterUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Supp_bussiness_partner_address pl where pl.modified_type != 'DELETED' and pl.dist_code=:code")
	Boolean checkSuppMasterUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Cust_bussiness_partner_address pl where pl.modified_type != 'DELETED' and pl.dist_code=:code")
	Boolean checkCustMasterUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Broker_master_add pl where pl.modified_type != 'DELETED' and pl.dist_code=:code")
	Boolean checkBrokerMasterUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Trans_bussiness_partner_address pl where pl.modified_type != 'DELETED' and pl.dist_code=:code")
	Boolean checkTransporetrMasterUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Pur_Order_Item_Details pl where pl.modified_type != 'DELETED' and pl.purpose=:code")
	Boolean checkPurOrderitemDtlsMasterUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Delivery_challan pl where pl.modified_type != 'DELETED' and pl.inv_type=:code")
	Boolean checkDeliveryChallanUsage(@Param("code") String code);
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Charge_Master pl where pl.modified_type != 'DELETED' and pl.screen_id=:code")
	Boolean checkChargematrixMasterUsage(@Param("code") String code);
	
	@Query( "select b from Company_business_unit_master b where b.businessunit_id = :businessunit_id and b.modified_type = 'INSERTED'")
	Company_business_unit_master businessunitbyid(@Param("businessunit_id") String businessunit_id);
		
	@Query( "select b from Company_business_unit_master b where b.businessunit_name = :bunit and b.modified_type = 'INSERTED'")
	Company_business_unit_master getCBUdetails(@Param("bunit") String bunit);
	
	@Query( "select c.businessunit_id,UPPER(c.businessunit_name) from Company_business_unit_master c where c.modified_type = 'INSERTED' and  c.company_id =:company_id ")
	List<Object[]> getcomapanybuunit(@Param("company_id") String company_id);
	
	@Query(value= "select c.businessunit_id as businessunit_id,UPPER(c.businessunit_name) as businessunit_name from company_business_unit_master c where c.modified_type = 'INSERTED' and  c.company_id =:company_id ", nativeQuery=true)
	List<Map<String,Object>> getcompanyBUMNCListFastApi(@Param("company_id") String company_id);
	
	@Query(value= "select c.state_name,c.dist_name,c.mailing_address,c.pin_code,c.fssai_no,c.work_phoneno,c.website_name,c.gstin_no,c.pan_no,c.city_name from company_business_unit_master c where c.modified_type = 'INSERTED' and  c.company_id =:company and c.businessunit_id=:bunit ", nativeQuery=true)
	Map<String,Object> getCompanyBussinessUnitDetails(@Param("company") String company,@Param("bunit") String bunit);
	
}
