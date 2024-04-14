package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_BusinessUnit_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Shipment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Trans_Info;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Weight_Dtl;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_driver_dtls;

public interface Stk_Transfer_ChallanRepository extends JpaRepository<Stk_Transfer_Challan, Long>{
	
	@Query("select COUNT(id) from Stk_Transfer_Challan")
	String countId();
	
	@Query("select COUNT(id) from Stk_Transfer_Challan where stk_challan_date=:cdate and business_unit=:bunit")
	String getSTCSequenceId(@Param("cdate") String cdate,@Param("bunit") String bunit);
	
	@Query("select s from Stk_Transfer_Challan s where s.modified_type = 'INSERTED' ORDER BY s.stk_challan_id DESC ")
	List<Stk_Transfer_Challan> findAllStkChallans();
	
	@Query("select s from Stk_Transfer_Challan s where s.modified_type = 'INSERTED' and s.business_unit = :bunit")
	List<Stk_Transfer_Challan> getStkTransChallanThruBUnit(@Param("bunit") String bunit);
	
	@Query("select s from Stk_Transfer_Challan s where s.modified_type = 'INSERTED' and s.business_unit = :bunit and s.stk_challan_date <=:invdate and s.company_id=:comp and s.fin_year=:finyear and s.billing_req='Yes' and s.saleinvoice_status='NO' ")
	List<Stk_Transfer_Challan> getStkTransChallans(@Param("bunit") String bunit,@Param("invdate") String invdate,@Param("comp") String comp,@Param("finyear") String finyear);
	
	@Query("select s from Stk_Transfer_Challan s where s.modified_type = 'INSERTED' and s.business_unit = :bunit and s.stk_challan_date <=:invdate and s.company_id=:comp and s.fin_year=:finyear and s.ref_type='Loading Advice' ")
	List<Stk_Transfer_Challan> getstocktransferchallaninunloading(@Param("bunit") String bunit,@Param("invdate") String invdate,@Param("comp") String comp,@Param("finyear") String finyear);
	
	@Query("select s from Stk_Transfer_Challan s where s.modified_type = 'INSERTED' and s.delivery_business_unit = :bunit and s.stk_challan_date <=:invdate and s.company_id=:comp and s.fin_year=:finyear and s.unload_status=0 and (s.ref_type='Loading Advice' OR s.ref_type='Goods Stock Transfer') ")
	List<Stk_Transfer_Challan> getstocktransferchallaninunloadingNew(@Param("bunit") String bunit,@Param("invdate") String invdate,@Param("comp") String comp,@Param("finyear") String finyear);
	
	
	@Query("select s from Stk_Transfer_Challan s where s.modified_type = 'INSERTED' and s.stk_challan_id =:stk_challan_id ")
	Stk_Transfer_Challan getStkTransChallanDtls(@Param("stk_challan_id") String stk_challan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan_Item_Dtls w SET w.modified_type ='DELETED' WHERE w.stk_challan_id = :stk_challan_id and w.modified_type = 'INSERTED'")
    int stkTransferChallanItemDtlsUpdate(@Param("stk_challan_id") String stk_challan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan_BusinessUnit_Dtls w SET w.modified_type ='DELETED' WHERE w.stk_challan_id = :stk_challan_id and w.modified_type = 'INSERTED'")
    int stkTransferChallanBusinessUnitDtlsUpdate(@Param("stk_challan_id") String stk_challan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan_Shipment_Dtls w SET w.modified_type ='DELETED' WHERE w.stk_challan_id = :stk_challan_id and w.modified_type = 'INSERTED'")
    int stkTransferChallanShipmentDtlsUpdate(@Param("stk_challan_id") String stk_challan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan_Trans_Info w SET w.modified_type ='DELETED' WHERE w.stk_challan_id = :stk_challan_id and w.modified_type = 'INSERTED'")
    int stkTransferChallanTransInfoUpdate(@Param("stk_challan_id") String stk_challan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan_Weight_Dtl w SET w.modified_type ='DELETED' WHERE w.stk_challan_id = :stk_challan_id and w.modified_type = 'INSERTED'")
    int stkTransferChallanWeightDtlUpdate(@Param("stk_challan_id") String stk_challan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan_Docs w SET w.modified_type ='DELETED' WHERE w.stk_challan_id = :stk_challan_id and w.modified_type = 'INSERTED'")
    int stkTransferChallanDocsUpdate(@Param("stk_challan_id") String stk_challan_id);
	
	@Query("select s from Stk_Transfer_Challan s where s.modified_type = 'INSERTED' and s.stk_challan_id = :stk_challan_id ")
	Stk_Transfer_Challan getStkOrderDetails(@Param("stk_challan_id") String stk_challan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan w SET w.sales_inv_id=:inv_id,w.saleinvoice_status='YES',w.challan_status='Single' WHERE w.stk_challan_id = :reference_id and w.modified_type = 'INSERTED'")
    int updateStkTransferChallan(@Param("reference_id") String reference_id,@Param("inv_id") String inv_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan w SET w.sales_inv_id=:inv_id,w.saleinvoice_status='YES',w.challan_status='Multiple' WHERE w.stk_challan_id = :reference_id and w.modified_type = 'INSERTED'")
    int updateStkTransferChallanmultiple(@Param("reference_id") String reference_id,@Param("inv_id") String inv_id);
	
	@Query("select s from Stk_Transfer_Challan_Trans_Info s where s.modified_type = 'INSERTED' and s.stk_challan_id = :stk_challan_id ")
	Stk_Transfer_Challan_Trans_Info getStkChallanVehicleNo(@Param("stk_challan_id") String stk_challan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan w SET w.unload_status=1 WHERE w.stk_challan_id = :reference_id and w.modified_type = 'INSERTED'")
    int updateUnloadChallanStatus(@Param("reference_id") String reference_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan w SET w.unload_status=0 WHERE w.stk_challan_id = :reference_id and w.modified_type = 'INSERTED' and w.unload_status=1")
    int updateUnloadChallanRevertStatus(@Param("reference_id") String reference_id);
	
	@Query("select s from Stk_Transfer_Challan_Item_Dtls s where s.modified_type = 'INSERTED' and s.stk_challan_id = :stk_challan_id and s.item_code=:itemid ")
	Stk_Transfer_Challan_Item_Dtls getitemDetails(@Param("stk_challan_id") String stk_challan_id,@Param("itemid") String itemid);
	
	@Query("select s from Stk_Transfer_Challan_Trans_Info s where s.modified_type = 'INSERTED' and s.stk_challan_id = :stk_challan_id")
	Stk_Transfer_Challan_Trans_Info getStockVehicleAndDriver(@Param("stk_challan_id") String stk_challan_id);
	
	@Query("select s from Wm_loading_advice_driver_dtls s where s.modified_type = 'INSERTED' and s.advice_id = :advice_id")
	Wm_loading_advice_driver_dtls getStockDriverName(@Param("advice_id") String advice_id);
}
