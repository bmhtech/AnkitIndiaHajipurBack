package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_groupwise_hsnsumm;

public interface Item_groupwise_hsnsummRepository extends JpaRepository<Item_groupwise_hsnsumm, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_groupwise_hsnsumm i SET i.modified_type =:mstatus WHERE i.invoice_id =:invoice_id")
    int updateItem_groupwise_hsnsumm(@Param("invoice_id") String invoice_id,@Param("mstatus") String mstatus);
	
	@Query("select i from Item_groupwise_hsnsumm i WHERE i.invoice_id =:invoice_id and i.modified_type ='INSERTED'")
    List<Item_groupwise_hsnsumm> getItem_groupwise_hsnsumm (@Param("invoice_id") String invoice_id);
}
