package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order_broker;

public interface Pur_Order_brokerRepository extends JpaRepository<Pur_Order_broker, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_broker w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_brokerupdate(@Param("pur_orderid") String pur_orderid);
	
	@Query("select p from Pur_Order_broker p where p.pur_orderid =:pur_order_no and p.modified_type = 'INSERTED' ORDER BY p.sl_no")
	List<Pur_Order_broker> getPurOrdBrokerList(@Param("pur_order_no") String pur_order_no);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_broker w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_brokerUpdate(@Param("pur_orderid") String pur_orderid);

}
