package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

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
