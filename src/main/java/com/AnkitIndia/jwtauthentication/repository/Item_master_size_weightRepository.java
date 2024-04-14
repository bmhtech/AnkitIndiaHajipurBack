package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master_size_weight;

public interface Item_master_size_weightRepository extends JpaRepository<Item_master_size_weight, Long> {
	
	@Query(value="select i.* from item_master_size_weight i where i.modified_type = 'INSERTED' and i.item_id=:itemid AND i.company_id=:company ORDER BY i.sl_no", nativeQuery=true)
	List<Map<String,Object>> retriveItemSizeWeight(@Param("itemid") String itemid,@Param("company") String company);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_master_size_weight w SET w.modified_type =:mstatus WHERE w.item_id = :item_id AND w.modified_type='INSERTED'")
    int itemSizeWeightUpdate(@Param("item_id") String item_id,@Param("mstatus") String mstatus);
	
	@Query(value="SELECT i.* FROM item_master_size_weight i WHERE i.modified_type = 'INSERTED' AND i.item_id=:item_id AND i.item_code=:packingid", nativeQuery=true)
	List<Map<String,Object>> getPackingMasterCode(@Param("item_id") String item_id,@Param("packingid") String packingid);
	
	@Query(value="SELECT i.* FROM item_master_size_weight i WHERE i.modified_type = 'INSERTED' AND i.master_code=:packingMasterCode AND i.item_code=:packingid", nativeQuery=true)
	Map<String,Object> retrivePackingDtls(@Param("packingMasterCode") String packingMasterCode,
										  @Param("packingid") String packingid);
}
