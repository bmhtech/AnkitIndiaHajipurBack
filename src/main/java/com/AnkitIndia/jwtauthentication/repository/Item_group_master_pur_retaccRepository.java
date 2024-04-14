package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_group_master_pur_retacc;

public interface Item_group_master_pur_retaccRepository extends JpaRepository<Item_group_master_pur_retacc, Long>{

	@Query( "select i from Item_group_master_pur_retacc i where i.item_group_id =:item_group_id and i.modified_type ='INSERTED'")
	Item_group_master_pur_retacc getItem_group_master_pur_retacc(@Param("item_group_id") String item_group_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_group_master_pur_retacc i SET i.modified_type =:status WHERE i.item_group_id = :item_group_id")
    int updateItem_group_master_pur_retacc(@Param("item_group_id") String item_group_id,@Param("status") String status);
	
}
