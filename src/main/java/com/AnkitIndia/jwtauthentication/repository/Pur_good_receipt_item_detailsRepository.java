package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_item_details;

public interface Pur_good_receipt_item_detailsRepository extends JpaRepository<Pur_good_receipt_item_details, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt_item_details w SET w.modified_type ='UPDATED' WHERE w.grn_id = :grn_id")
    int updatePur_good_receipt_item_details(@Param("grn_id") String grn_id);
	
	@Query( "select p from Pur_good_receipt_item_details p where p.grn_id =:grnid and p.modified_type ='INSERTED' order by p.slno")
	List<Pur_good_receipt_item_details> getPurGoodRcptItemDtlsList(@Param("grnid") String grnid);
	
	
	//@Query(value= "select * from pur_good_receipt_item_details p where p.grn_id =:grnid and p.modified_type ='INSERTED' order by p.slno",nativeQuery = true)
	@Query(value= "SELECT p.*,(SELECT t.tax_id FROM tax_code_details t WHERE t.tax_name =p.tax_code AND t.modified_type ='INSERTED') AS tax_id FROM pur_good_receipt_item_details p WHERE p.grn_id =:grnid AND p.modified_type ='INSERTED' ORDER BY p.slno",nativeQuery = true)
	List<Map<String, Object>> getPurGoodRcptItemDtlsListfastapi(@Param("grnid") String grnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt_item_details w SET w.modified_type ='DELETED' WHERE w.grn_id = :grn_id")
    int pur_good_receipt_item_detailsUpdate(@Param("grn_id") String grn_id);
	
	@Query( "select p from Pur_good_receipt_item_details p where p.grn_id =:grnid AND p.adv_item_code=:adv_item_code and p.modified_type ='INSERTED'")
	Pur_good_receipt_item_details getPurGoodRcptItemDtlsListTAX (@Param("grnid") String grnid ,@Param("adv_item_code") String adv_item_code);
	
	
	@Query( "select p from Pur_good_receipt_item_details p where p.grn_id =:grnid AND p.adv_item_code=:adv_item_code and p.adv_packing=:adv_packing and p.modified_type ='INSERTED'")
	Pur_good_receipt_item_details getPurGoodRcptItemDtlsListTAXnew (@Param("grnid") String grnid ,@Param("adv_item_code") String adv_item_code,@Param("adv_packing") String adv_packing);
	
	@Query( "select p from Pur_good_receipt_item_details p where p.grn_id =:grnid AND p.adv_item_code=:adv_item_code and p.adv_packing=:adv_packing and p.classified_item_name=:classified and p.modified_type ='INSERTED'")
	Pur_good_receipt_item_details getPurGoodRcptItemDtlsListTAXnewForStore (@Param("grnid") String grnid ,@Param("adv_item_code") String adv_item_code,@Param("adv_packing") String adv_packing,@Param("classified") String classified);
	
	@Query( value="select * from pur_good_receipt_item_details p where p.grn_id =:grn_id and p.modified_type ='INSERTED'",nativeQuery=true)
	List<Map<String, Object>> getgrnitemlist(@Param("grn_id") String grn_id);
}
