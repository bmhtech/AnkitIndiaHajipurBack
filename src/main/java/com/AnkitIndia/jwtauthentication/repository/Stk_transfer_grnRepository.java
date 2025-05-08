package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_resource_cost;

public interface Stk_transfer_grnRepository extends JpaRepository<Stk_transfer_grn, Long>{
	
	@Query("select COUNT(id) from Stk_transfer_grn")
	String countId();
	
	@Query("select COUNT(id) from Stk_transfer_grn where stk_grn_date=:tdate and b_unit =:bunit")
	String getSTGRNSequenceId(@Param("tdate") String tdate,@Param("bunit") String bunit);
	
	@Query("select s from Stk_transfer_grn s where s.modified_type = 'INSERTED' and s.company_id =:company and s.fin_year =:finyear ORDER BY s.stk_grn_id DESC ")
	List<Stk_transfer_grn> findAllStkTransGRN(@Param("company") String company,@Param("finyear") String finyear);
	
	@Query("select s from Stk_transfer_grn s where s.modified_type = 'INSERTED' and s.b_unit =:bunit and s.stk_grn_date =:tdate and s.company_id =:company and s.fin_year =:finyear ORDER BY s.stk_grn_id DESC ")
	List<Stk_transfer_grn> getStkTranGrns(@Param("bunit") String bunit,@Param("tdate") String tdate,@Param("company") String company,@Param("finyear") String finyear);
	
	//@Query("select s from Stk_transfer_grn s where s.modified_type = 'INSERTED' and s.b_unit =:bunit and s.stk_grn_date =:tdate and s.company_id =:company and s.fin_year =:finyear and  s.stk_pur_inv_status=0")
	@Query("select s from Stk_transfer_grn s where s.modified_type = 'INSERTED' and s.rec_b_unit =:bunit and s.stk_grn_date =:tdate and s.company_id =:company and s.fin_year =:finyear and  s.stk_pur_inv_status=0")
	List<Stk_transfer_grn> getStkTranGrnsNew(@Param("bunit") String bunit,@Param("tdate") String tdate,@Param("company") String company,@Param("finyear") String finyear);
	
	
	
	@Query("select s from Stk_transfer_grn s where s.modified_type = 'INSERTED' and s.stk_grn_id =:stkgrnid ")
	Stk_transfer_grn getStkTranGrnDtls(@Param("stkgrnid") String stkgrnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Vehicle_weighment_load_unload v SET v.stock_grn_status =1 WHERE v.reference_id =:ref_id and v.vehicle_id =:veh_id and v.ref_name_type ='Stock Transfer' and v.stock_grn_status =0")
    int updateStockGRNStatus(@Param("ref_id") String ref_id,@Param("veh_id") String veh_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Vehicle_weighment_load_unload v SET v.stock_grn_status =1 WHERE v.reference_id =:ref_id and v.vehicle_id =:veh_id and v.ref_name_type ='Goods Stock Transfer' and v.stock_grn_status =0")
    int updateStockGRNGoodsStatus(@Param("ref_id") String ref_id,@Param("veh_id") String veh_id);
	
	
	@Query("select s from Stk_transfer_grn s where s.modified_type = 'INSERTED' and s.reference_id =:stkgrnid ")
	Stk_transfer_grn getGrn(@Param("stkgrnid") String stkgrnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_grn_item_details v SET v.modified_type ='UPDATED' WHERE v.stk_grn_id =:stk_grn_id and v.modified_type = 'INSERTED'")
    int updateStkTransferGrnItemDetails(@Param("stk_grn_id") String stk_grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_grn_trans_info v SET v.modified_type ='UPDATED' WHERE v.stk_grn_id =:stk_grn_id and v.modified_type = 'INSERTED'")
    int updateStkTransferGrnTransportDetails(@Param("stk_grn_id") String stk_grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_grn v SET v.modified_type ='UPDATED' WHERE v.stk_grn_id =:stk_grn_id and v.modified_type = 'INSERTED'")
    int updateStkTransferGrn(@Param("stk_grn_id") String stk_grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_grn_bu_dtls v SET v.modified_type ='UPDATED' WHERE v.stk_grn_id =:stk_grn_id and v.modified_type = 'INSERTED'")
    int stkTransferGrnBuDtlsUpdate(@Param("stk_grn_id") String stk_grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_grn_other_info v SET v.modified_type ='UPDATED' WHERE v.stk_grn_id =:stk_grn_id and v.modified_type = 'INSERTED'")
    int stkTransferGrnOtherInfoUpdate(@Param("stk_grn_id") String stk_grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_grn_resource_cost v SET v.modified_type ='UPDATED' WHERE v.stk_grn_id =:stk_grn_id and v.modified_type = 'INSERTED'")
    int stkTransferGrnResourceCostUpdate(@Param("stk_grn_id") String stk_grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_grn_docs v SET v.modified_type ='UPDATED' WHERE v.stk_grn_id =:stk_grn_id and v.modified_type = 'INSERTED'")
    int stkTransferGrnDocsUpdate(@Param("stk_grn_id") String stk_grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Vehicle_weighment_load_unload v SET v.stock_grn_status =0 WHERE v.reference_id =:ref_id and v.vehicle_id =:veh_id and v.ref_name_type ='Stock Transfer' and v.stock_grn_status =1")
    int vehicleLoadUnloadUpdate(@Param("ref_id") String ref_id,@Param("veh_id") String veh_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Vehicle_weighment_load_unload v SET v.stock_grn_status =0 WHERE v.reference_id =:ref_id and v.vehicle_id =:veh_id and v.ref_name_type ='Goods Stock Transfer' and v.stock_grn_status =1")
    int stockGRNGoodsStatusUpdate(@Param("ref_id") String ref_id,@Param("veh_id") String veh_id);
	
	
	@Query(value = "SELECT CASE  WHEN count(pl)> 0 THEN true ELSE false END FROM Vehicle_weighment_load_unload pl where pl.vehicle_id=:vehicle_id and (pl.ref_name_type ='Goods Stock Transfer' OR pl.ref_name_type ='Stock Transfer') and pl.stock_grn_status=0")
	Boolean checkStockGRNUsage(@Param("vehicle_id") String vehicle_id);
	

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_grn v SET v.stk_pur_inv_status =1 WHERE v.stk_grn_id =:ref_id and v.modified_type = 'INSERTED' ")
    int updatePurGrnStatus(@Param("ref_id") String ref_id);
	
	@Query("select s from Stk_Transfer_Challan s where s.modified_type = 'INSERTED' and s.stk_challan_id = :stk_challan_id ")
	Stk_Transfer_Challan getStkOrderDetails(@Param("stk_challan_id") String stk_challan_id);
	
	@Query(value="select s.rest_bag,s.rest_wt from stock_grn_stock_transfer_rest_wt s where s.order_id =:orderid AND s.item_code = :item AND packing=:packing ",nativeQuery = true)
	Map<String,Object> getStkTransferGrnRestQty(@Param("orderid") String orderid,@Param("item") String item,@Param("packing") String packing);
	
	@Query(value="select s.* from stk_transfer_grn s where s.modified_type = 'INSERTED' and s.company_id =:company and s.fin_year =:finyear ORDER BY s.stk_grn_id DESC ",nativeQuery = true)
	List<Map<String,Object>> getStkTranGrnsFast(@Param("company") String company,@Param("finyear") String finyear);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_grn v SET v.sale_inv_status =:stat WHERE v.stk_grn_id =:ref_id and v.modified_type = 'INSERTED' ")
    int updateInvoiceStatus(@Param("ref_id") String ref_id,@Param("stat") String stat);
	
}
