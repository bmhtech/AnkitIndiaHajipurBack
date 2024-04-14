package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AnkitIndia.jwtauthentication.model.Item_master_stat_info;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Item_master_stat_infoRepository extends JpaRepository<Item_master_stat_info, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_master_stat_info w SET w.modified_type =:mstatus WHERE w.item_id = :item_id")
    int item_master_stat_infoupdate(@Param("item_id") String item_id,@Param("mstatus") String mstatus);

	@Query( "select i from Item_master_stat_info i where i.modified_type = 'INSERTED' and i.item_id =:item_id ")
	List<Item_master_stat_info> retriveItemMasterStatInfo(@Param("item_id") String item_id);
}
