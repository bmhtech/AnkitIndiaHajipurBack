package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Bill_Item_Details;

public interface Pur_Bill_Item_DetailsRepository extends JpaRepository<Pur_Bill_Item_Details, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Bill_Item_Details w SET w.modified_type ='UPDATED' WHERE w.pur_bill_id = :pur_bill_id")
    int updatePur_Bill_Item_Details(@Param("pur_bill_id") String pur_bill_id);
	
	@Query( "select p from Pur_Bill_Item_Details p where p.pur_bill_id = :pbid and p.modified_type ='INSERTED'" )
	List<Pur_Bill_Item_Details> purBillItemRetriveList(@Param("pbid") String pbid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Bill_Item_Details w SET w.modified_type ='DELETED' WHERE w.pur_bill_id = :pur_bill_id")
    int pur_Bill_Item_DetailsUpdate(@Param("pur_bill_id") String pur_bill_id);

}
