package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Purchase_item_groupwise_summ;



public interface Purchase_item_groupwise_summRepository  extends JpaRepository<Purchase_item_groupwise_summ, Long> {
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Purchase_item_groupwise_summ i SET i.modified_type ='UPDATED' WHERE i.pur_bill_id =:pur_bill_id")
    int updateItem_groupwise_summ(@Param("pur_bill_id") String pur_bill_id);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Purchase_item_groupwise_summ i SET i.modified_type ='DELETED' WHERE i.pur_bill_id =:pur_bill_id")
    int deleteItem_groupwise_summ(@Param("pur_bill_id") String pur_bill_id);
}
