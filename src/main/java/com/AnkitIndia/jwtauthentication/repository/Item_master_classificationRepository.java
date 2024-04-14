package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master_classification;


public interface Item_master_classificationRepository extends JpaRepository<Item_master_classification, Long> {
	
	@Query(value="select i.* from item_master_classification i where i.modified_type = 'INSERTED' and i.item_id=:itemid AND i.company_id=:company ORDER BY i.sl_no", nativeQuery=true)
	List<Map<String,Object>> retriveClassifiedItem(@Param("itemid") String itemid,@Param("company") String company);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_master_classification w SET w.modified_type =:mstatus WHERE w.item_id = :item_id AND w.modified_type='INSERTED'")
    int itemMasterClassificationUpdate(@Param("item_id") String item_id,@Param("mstatus") String mstatus);
}
