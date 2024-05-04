package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master_pack_mat_tag;

public interface Item_master_pack_mat_tagRepository extends JpaRepository<Item_master_pack_mat_tag, Long> {
	
	@Query( "select i from Item_master_pack_mat_tag i where i.item_id = :code and i.item_code= :code1 and i.company_id=:company and i.modified_type = 'INSERTED'")
	Item_master_pack_mat_tag itempackUom(@Param("code") String code,@Param("code1") String code1,@Param("company") String company);
	
	@Query( "select i from Item_master_pack_mat_tag i where i.item_id = :code and i.modified_type = 'INSERTED'")
	Item_master_pack_mat_tag getItemCapacity(@Param("code") String code);
	
	@Query( "select i from Item_master_pack_mat_tag i where i.item_id = :item and i.item_code = :packing and i.modified_type = 'INSERTED'")
	Item_master_pack_mat_tag getItemCapacityNew(@Param("item") String item,@Param("packing") String packing);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_master_pack_mat_tag w SET w.modified_type =:mstatus WHERE w.item_id = :item_id")
    int item_master_pack_mat_tagUpdate(@Param("item_id") String item_id,@Param("mstatus") String mstatus);
	
	@Query( "select i from Item_master_pack_mat_tag i where i.modified_type = 'INSERTED' and i.item_id =:code ORDER BY i.sl_no")
	List<Item_master_pack_mat_tag> getItemMasterPackMat(@Param("code") String code);
	
	@Query( value="select * from item_master_pack_mat_tag i where i.modified_type = 'INSERTED' and i.item_id =:code ORDER BY i.sl_no",nativeQuery = true)
	List<Map<String,Object>> getItemMasterPackMatNew(@Param("code") String code);
	
	@Query( value="select * from item_master_pack_mat_tag i where i.item_id = :code and i.item_code= :code1 and i.company_id=:company and i.modified_type = 'INSERTED'",nativeQuery = true)
	Map<String,Object> getItemPackUomNew(@Param("code") String code,@Param("code1") String code1,@Param("company") String company);
}
