package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order_Trans_Info;

public interface Pur_Order_Trans_InfoRepository extends JpaRepository<Pur_Order_Trans_Info, Long>{
	
	@Query("SELECT sa FROM Pur_Order_Trans_Info sa where sa.pur_orderid= :pur_order_no and sa.modified_type = 'INSERTED'")
	Pur_Order_Trans_Info getPurOrdTrans(@Param("pur_order_no") String pur_order_no);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Trans_Info w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_Trans_Infoupdate(@Param("pur_orderid") String pur_orderid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Trans_Info w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_Trans_InfoUpdate(@Param("pur_orderid") String pur_orderid);

}
