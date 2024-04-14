package com.AnkitIndia.jwtauthentication.repository;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.AnkitIndia.jwtauthentication.model.Pur_bill_store_chgs;

public interface Pur_bill_store_chgsRepository extends JpaRepository<Pur_bill_store_chgs, Long>{

	@Query( "select p from Pur_bill_store_chgs p where p.pur_bill_id = :pbid and p.modified_type ='INSERTED'" )
	List<Pur_bill_store_chgs> purBillstorechargesList(@Param("pbid") String pbid);
	
	
	@Query( value="select * from pur_bill_store_chgs p where p.pur_bill_id = :pbid and p.modified_type ='INSERTED'",nativeQuery = true )
	List<Map<String,Object>> purBillStoreChargesRetriveList (@Param("pbid") String pbid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_bill_store_chgs w SET w.modified_type ='DELETED' WHERE w.pur_bill_id = :pur_bill_id")
    int pur_Bill_store_chgsUpdate(@Param("pur_bill_id") String pur_bill_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_bill_store_chgs w SET w.modified_type ='UPDATED' WHERE w.pur_bill_id = :pur_bill_id")
    int updatePur_Bill_store(@Param("pur_bill_id") String pur_bill_id);
}
