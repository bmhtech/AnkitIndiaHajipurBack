package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order_app_chgs;

public interface Pur_Order_app_chgsRepository extends JpaRepository<Pur_Order_app_chgs, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_app_chgs w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_app_chgsupdate(@Param("pur_orderid") String pur_orderid);
	
	@Query("select p from Pur_Order_app_chgs p where p.pur_orderid = :orderid and p.modified_type = 'INSERTED' ORDER BY p.id")
	List<Pur_Order_app_chgs> getPurOrdAppChgs(@Param("orderid") String orderid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_app_chgs w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_app_chgsUpdate(@Param("pur_orderid") String pur_orderid);
}
