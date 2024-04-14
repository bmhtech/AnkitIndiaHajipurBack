package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Item_master_alternative_dtls;

public interface Item_master_alternative_dtlsRepository extends JpaRepository<Item_master_alternative_dtls, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Item_master_alternative_dtls w SET w.modified_type =:mstatus WHERE w.item_id = :item_id")
    int getItem_master_alternative_dtlsUpdate(@Param("item_id") String item_id,@Param("mstatus") String mstatus);
	
	@Query( "select i from Item_master_alternative_dtls i where i.modified_type = 'INSERTED' and i.company_id=:company_id and i.item_id =:item_id ")
	List<Item_master_alternative_dtls> retriveItemMasterAltDtls(@Param("item_id") String item_id,@Param("company_id") String company_id);
	
	@Query(value="SELECT i.addless,i.packing_cost FROM item_master_alternative_dtls i WHERE i.modified_type = 'INSERTED' and i.item_id=:itemid and i.item_id_old = :alternateitemid ", nativeQuery=true)
	Map<String,Object> getItemAlternateprice(@Param("itemid") String itemid,@Param("alternateitemid") String alternateitemid);
}
