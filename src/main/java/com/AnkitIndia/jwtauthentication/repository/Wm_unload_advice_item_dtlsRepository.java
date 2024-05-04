package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_item_dtls;

public interface Wm_unload_advice_item_dtlsRepository extends JpaRepository<Wm_unload_advice_item_dtls, Long>{
	
	@Query("select w from Wm_unload_advice_item_dtls w where w.unadviceid = :adviceid and w.modified_type = 'INSERTED' ")
	List<Wm_unload_advice_item_dtls> getUnloadAdvItemList(@Param("adviceid") String adviceid);
	
	@Query("select w from Wm_unload_advice_item_dtls w where w.unadviceid = :adviceid and w.item_code =:code and w.modified_type = 'INSERTED' ")
	Wm_unload_advice_item_dtls getUnloadAdvPOItemDtls(@Param("adviceid") String adviceid,@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_item_dtls w SET w.modified_type ='UPDATED' WHERE w.unadviceid = :unadviceid")
    int wm_unload_advice_item_dtlsUpdate(@Param("unadviceid") String unadviceid);
	
	@Query("select w from Wm_unload_advice_item_dtls w where w.unadviceid = :code and w.modified_type = 'INSERTED' ")
	List<Wm_unload_advice_item_dtls> getUnloadItemList(@Param("code") String code);
	
	@Query(value= "SELECT s.unadviceid AS unadviceid,s.unadviceno AS unadviceno,s.sl_no AS sl_no,s.item_code AS item_code,s.item_name AS item_name,s.packing AS packing,s.packing_name AS packing_name,s.packing_item_code AS packing_item_code,s.packing_size AS packing_size,s.packing_weight AS packing_weight,s.packing_type AS packing_type,s.quantity AS quantity,s.uom AS uom,s.s_qty AS s_qty,s.s_uom AS s_uom,s.mat_wt AS mat_wt,s.qc_norms AS qc_norms,s.wearhouse AS wearhouse,s.rack  AS rack,CASE WHEN (SELECT warehouse_name FROM warehouse_master WHERE warehouse_id=s.wearhouse) IS NULL THEN '' ELSE (SELECT warehouse_name FROM warehouse_master WHERE warehouse_id=s.wearhouse) END  AS wearhouse_name,CASE WHEN (SELECT bin_description FROM bin_master WHERE bin_code=s.rack) IS NULL THEN '' ELSE (SELECT bin_description FROM bin_master WHERE bin_code=s.rack) END  AS rack_name,s.base_uom AS base_uom,s.base_qty AS base_qty,s.pur_dyn_id AS pur_dyn_id,s.con_factor AS con_factor,s.pur_orderid AS pur_orderid,s.classified_item_name AS classified_item_name,s.price_based_on AS price_based_on FROM wm_unload_advice_item_dtls s WHERE s.unadviceid='WUL00324' AND s.modified_type = 'INSERTED'", nativeQuery=true)
	List<Map<String,Object>> getUnloadItemFastList(@Param("code") String code); 
	
	@Query("select w from Wm_unload_advice_item_dtls w where w.unadviceid = :code and w.modified_type = 'INSERTED'")
	List<Wm_unload_advice_item_dtls> wmUnAdviceItemRetriveList(@Param("code") String code);
	
//	@Modifying(clearAutomatically = true)
//    @Query("UPDATE Vehicle_weighment_load_unload w SET w.modified_type ='UPDATED' WHERE w.reference_id = :reference_id and  w.modified_type ='INSERTED' ")
//    int Vehicle_weighment_load_unloadUpdate(@Param("reference_id") String reference_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Wm_unload_advice_item_dtls w SET w.modified_type ='DELETED' WHERE w.unadviceid = :code and  w.modified_type ='INSERTED' ")
    int wm_unload_advice_item_dtlsupdate(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    //@Query("DELETE from Vehicle_weighment_load_unload w WHERE w.reference_id = :reference_id and  w.modified_type ='INSERTED' ")
	@Query("UPDATE from Vehicle_weighment_load_unload w set  w.modified_type ='DELETED'  WHERE w.reference_id = :reference_id and  w.modified_type ='INSERTED' ")
    int vehicle_weighment_load_unloadupdate(@Param("reference_id") String reference_id);
	
	 @Query("select w from Vehicle_weighment_load_unload w WHERE w.reference_id = :reference_id and  w.modified_type ='INSERTED' ")
	Vehicle_weighment_load_unload vehicle_weighment_load_unloadupdateFETCH(@Param("reference_id") String reference_id);

	
	@Query(value = "{call multiple_advice_grn_item(:unadvice_id)}", nativeQuery = true)
	List<Wm_unload_advice_item_dtls> getmultiplegrnunloading(@Param("unadvice_id") String unadvice_id);
	

	@Query("select SUM(w.mat_wt) from Wm_unload_advice_item_dtls w where w.unadviceid = :code and w.modified_type = 'INSERTED'")
	double gettotalmat_weight(@Param("code") String code);
	
	
	@Query("select SUM(w.s_qty) from Wm_unload_advice_item_dtls w where w.unadviceid = :code and w.modified_type = 'INSERTED' and w.item_name LIKE '%WHEAT%'")
	double gettotalpackingqty(@Param("code") String code);
	
	@Query("select w from Wm_unload_advice_item_dtls w where w.unadviceno = :adviceid and w.modified_type = 'INSERTED'  and w.item_name LIKE '%WHEAT%'")
	List<Wm_unload_advice_item_dtls> getUnloadAdvItemListreport(@Param("adviceid") String adviceid);
	
}
