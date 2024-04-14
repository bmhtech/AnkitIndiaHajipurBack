package com.AnkitIndia.jwtauthentication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master_inv_data2;

public interface Item_master_inv_data2Repository extends JpaRepository<Item_master_inv_data2, Long>{

	@Query( "select a from Item_master_inv_data2 a where a.item_id = :item_id and a.company_id=:company_id and a.modified_type = 'INSERTED'")
	Item_master_inv_data2 retriveItemMasterInvData2(@Param("item_id") String item_id,@Param("company_id") String company_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_master_inv_data2 w SET w.modified_type =:mstatus WHERE w.item_id = :item_id")
    int getItem_master_inv_data2Update(@Param("item_id") String item_id,@Param("mstatus") String mstatus);

}
