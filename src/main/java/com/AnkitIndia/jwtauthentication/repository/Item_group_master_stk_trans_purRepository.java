package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_group_master_stk_trans_pur;

public interface Item_group_master_stk_trans_purRepository extends JpaRepository<Item_group_master_stk_trans_pur, Long>{

	@Query( "select i from Item_group_master_stk_trans_pur i where i.item_group_id =:item_group_id and i.modified_type ='INSERTED'")
	Item_group_master_stk_trans_pur getItem_group_master_stk_trans_pur(@Param("item_group_id") String item_group_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_group_master_stk_trans_pur i SET i.modified_type =:status WHERE i.item_group_id = :item_group_id")
    int updateItem_group_master_stk_trans_pur(@Param("item_group_id") String item_group_id,@Param("status") String status);
	
}
