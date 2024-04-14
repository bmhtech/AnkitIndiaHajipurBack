package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_group_master_sales_acc;

public interface Item_group_master_sales_accRepository extends JpaRepository<Item_group_master_sales_acc, Long>{
	
	@Query( "select i from Item_group_master_sales_acc i where i.item_group_id =:item_group_id and i.modified_type ='INSERTED'")
	Item_group_master_sales_acc getItem_group_master_sales_acc(@Param("item_group_id") String item_group_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_group_master_sales_acc i SET i.modified_type =:status WHERE i.item_group_id =:item_group_id")
    int updateItem_group_master_sales_acc(@Param("item_group_id") String item_group_id,@Param("status") String status);

}
