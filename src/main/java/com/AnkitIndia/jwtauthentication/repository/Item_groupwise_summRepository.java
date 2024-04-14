package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_groupwise_summ;

public interface Item_groupwise_summRepository extends JpaRepository<Item_groupwise_summ, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_groupwise_summ i SET i.modified_type =:mstatus WHERE i.invoice_id =:invoice_id")
    int updateItem_groupwise_summ(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);
	
}
