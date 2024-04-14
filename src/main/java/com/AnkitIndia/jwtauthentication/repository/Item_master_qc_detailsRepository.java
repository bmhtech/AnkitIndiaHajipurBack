package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master_qc_details;

public interface Item_master_qc_detailsRepository extends JpaRepository<Item_master_qc_details, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_master_qc_details w SET w.modified_type =:mstatus WHERE w.item_id = :item_id ")
    int getItmItem_master_qc_detailsUpdate(@Param("item_id") String item_id,@Param("mstatus") String mstatus);

	@Query( "select i from Item_master_qc_details i where i.item_id = :code and i.company_id=:company and i.modified_type = 'INSERTED'")
	List<Item_master_qc_details> getItemQCDetails(@Param("code") String code,@Param("company") String company);
}
