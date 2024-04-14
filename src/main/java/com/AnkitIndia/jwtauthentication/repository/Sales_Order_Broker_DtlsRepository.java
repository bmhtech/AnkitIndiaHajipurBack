package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Order_Broker_Dtls;

public interface Sales_Order_Broker_DtlsRepository extends JpaRepository<Sales_Order_Broker_Dtls, Long>{

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Broker_Dtls w SET w.modified_type =:mstatus WHERE w.order_id = :order_id")
    int sales_Order_Broker_Dtlsupdate(@Param("order_id") String order_id,@Param("mstatus") String mstatus);
	
	@Query("select s from Sales_Order_Broker_Dtls s where s.modified_type = 'INSERTED' and s.order_id =:order_id ORDER BY s.slno")
	List<Sales_Order_Broker_Dtls> getSalesOrdBrokerDtls(@Param("order_id") String order_id);
}
