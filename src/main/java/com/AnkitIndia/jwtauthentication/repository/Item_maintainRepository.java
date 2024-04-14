package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_maintain;

public interface Item_maintainRepository extends JpaRepository<Item_maintain, Long>{
	
	@Query( "select i from Item_maintain i where i.modified_type = 'INSERTED' and i.item_id =:itemid")
	List<Item_maintain> getItemMaintains(@Param("itemid") String itemid);
	
	@Query( "select i from Item_maintain i where i.modified_type = 'INSERTED' and i.business_unit =:business_unit and i.item_type =:item_type")
	List<Item_maintain> getItemMaintains(@Param("business_unit") String business_unit,@Param("item_type") String item_type);
	
	@Query( "select i from Item_maintain i where i.modified_type = 'INSERTED' and i.business_unit =:business_unit")
	List<Item_maintain> getItemMaintainThruBunit(@Param("business_unit") String business_unit);
	
	@Modifying
	@Query("DELETE FROM Item_maintain WHERE business_unit =:bunit and item_id =:itemid and packing_id =:packingid")
	void deleteItemMaintain(@Param("bunit") String bunit,@Param("itemid") String itemid,@Param("packingid") String packingid);
	
}
