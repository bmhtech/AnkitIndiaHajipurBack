package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_order_store_chgs;

public interface Pur_order_store_chgsRepository extends JpaRepository<Pur_order_store_chgs, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_order_store_chgs w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_storeUpdate(@Param("pur_orderid") String pur_orderid);
	
	@Query(value="select * from pur_order_store_chgs p where p.pur_orderid =:pur_order_no and p.modified_type = 'INSERTED' ORDER BY p.id", nativeQuery=true)
	List<Map<String,Object>> getPurOrdStoreDynList(@Param("pur_order_no") String pur_order_no);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_order_store_chgs w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_storeDelete(@Param("pur_orderid") String pur_orderid);
	
}
