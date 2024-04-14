package com.AnkitIndia.jwtauthentication.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master_inv_data1;

public interface Item_master_inv_data1Repository extends JpaRepository< Item_master_inv_data1, Long>{
	
	@Query( "select a from Item_master_inv_data1 a where a.item_id = :item_id and a.company_id=:company and a.modified_type = 'INSERTED' ")
	Item_master_inv_data1 retriveItemMasterInvData1(@Param("item_id") String item_id,@Param("company") String comapay);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_master_inv_data1 w SET w.modified_type =:mstatus WHERE w.item_id = :item_id ")
    int getItem_master_inv_data1Update(@Param("item_id") String item_id,@Param("mstatus") String mstatus);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_master_inv_data1 w SET w.modified_type =:status,w.deleted_on =:ldt,w.deleted_by =:user WHERE w.item_id = :item_id ")
    int setItem_master_inv_data1Update(@Param("item_id") String item_id,@Param("status") String status,@Param("user") String user,@Param("ldt") LocalDateTime ldt);

}
