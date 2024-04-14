package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Purchase_item_groupwise_taxsumm;


public interface Purchase_item_groupwise_taxsummRepository extends JpaRepository<Purchase_item_groupwise_taxsumm, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Purchase_item_groupwise_taxsumm i SET i.modified_type ='UPDATED' WHERE i.pur_bill_id =:pur_bill_id")
    int updateItem_groupwise_taxsumm(@Param("pur_bill_id") String pur_bill_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Purchase_item_groupwise_taxsumm i SET i.modified_type ='DELETED' WHERE i.pur_bill_id =:pur_bill_id")
    int deleteItem_groupwise_taxsumm(@Param("pur_bill_id") String pur_bill_id);
	
	
	 @Query("select p from Purchase_item_groupwise_taxsumm p where p.modified_type ='INSERTED' and p.pur_bill_id =:pur_bill_id and p.taxable_amt !=0 ")
	 List<Purchase_item_groupwise_taxsumm> getdetails(@Param("pur_bill_id") String pur_bill_id);
	 
	 
	 

}
