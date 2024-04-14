package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Store_issue_note;


public interface Store_issue_noteRepository extends JpaRepository<Store_issue_note, Long>{

	@Query("select COUNT(id) from Store_issue_note")
	String countId();
	
	@Query(value="SELECT * FROM store_issue_note g WHERE g.`modified_type`='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> getstoreIssuelist();

	@Query(value="SELECT * FROM po_store_item_tracking WHERE adv_item_code=:item AND classified_item_name=:clasitem AND warehouse_code=:warehouse", nativeQuery=true)
	Map<String, Object> getPoStoreQty(@Param("item") String item,@Param("clasitem") String clasitem,@Param("warehouse") String warehouse);
	
	@Query(value="SELECT DISTINCT(s.adv_item_code) as item_id,s.adv_item_name as item_name FROM po_store_item_tracking s", nativeQuery=true)
	List<Map<String, Object>> getItemThruGrn();
	
	@Query(value="SELECT s.classified_item_name FROM po_store_item_tracking s WHERE s.adv_item_code=:item_id", nativeQuery=true)
	List<Map<String, Object>> retriveClassifiedItemThruGrn(@Param("item_id") String item_id);
	
	@Query(value="SELECT * FROM po_store_item_tracking ", nativeQuery=true)
	List<Map<String, Object>> getWarehouseFromPoStoreItem();
	
	//@Query(value="select * from jw_grn_partytag_details where modified_type='INSERTED' AND jw_grn_tag=:jw_grn_tag order by slno", nativeQuery=true)
	//List<Map<String, Object>> getJwGrnPartytagDetails(@Param("jw_grn_tag") String jw_grn_tag);
	
	//@Modifying(clearAutomatically = true)
   // @Query("UPDATE Jw_grn_partytag_details w SET w.modified_type =:mstatus WHERE w.jw_grn_tag = :jw_grn_tag AND w.modified_type ='INSERTED'")
    //int updateJwGrnPartytagDetails(@Param("jw_grn_tag") String jw_grn_tag,@Param("mstatus") String mstatus);
}
