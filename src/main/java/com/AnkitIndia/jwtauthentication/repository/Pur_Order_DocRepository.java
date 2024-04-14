package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order_Doc;

public interface Pur_Order_DocRepository extends JpaRepository<Pur_Order_Doc, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Doc w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_Docupdate(@Param("pur_orderid") String pur_orderid);
	
	@Query("select p from Pur_Order_Doc p where p.pur_orderid = :code and p.modified_type = 'INSERTED' ORDER BY p.id")
	List<Pur_Order_Doc> purOrdDocRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Doc w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_DocUpdate(@Param("pur_orderid") String pur_orderid);

}
