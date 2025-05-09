package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_item_details;

public interface Stk_transfer_grn_item_detailsRepository extends JpaRepository<Stk_transfer_grn_item_details, Long>{
	
	@Query( "select a from Stk_transfer_grn_item_details a where a.stk_grn_id =:stk_grn_id and a.modified_type ='INSERTED' ")
	List<Stk_transfer_grn_item_details> getStk_transfer_grn_item_details(@Param("stk_grn_id") String stk_grn_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_transfer_grn_item_details s SET s.modified_type ='UPDATED' WHERE s.stk_grn_id = :stk_grn_id")
    int updateStk_transfer_grn_item_details(@Param("stk_grn_id") String stk_grn_id);
	
	@Query( value="SELECT a.adv_item_code,(SELECT i.item_group FROM item_master i WHERE i.item_id=a.adv_item_code AND modified_type='INSERTED') AS item_group,a.adv_packing,a.rcv_item_uom,a.rcv_item_qty,a.rcv_mat_wt,a.rcv_pack_qty,a.rcv_pack_uom,a.price_based_on,a.tax_rate,a.tax_code,(SELECT i.hsn_code FROM item_master i WHERE i.item_id=a.adv_item_code AND modified_type='INSERTED') AS hsn_code,a.discount,a.discount_based_on,a.discount_amt FROM stk_transfer_grn_item_details a WHERE a.stk_grn_id =:stk_grn_id AND a.modified_type ='INSERTED' AND a.adv_item_code='IM00027' AND a.adv_packing='IM00025'",nativeQuery = true)
	Map<String,Object> getSalesInvFromStkTransGrn(@Param("stk_grn_id") String stk_grn_id);
}
