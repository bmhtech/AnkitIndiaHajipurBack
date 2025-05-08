package com.AnkitIndia.jwtauthentication.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Sales_Order_Shipment_Dtls;

public interface Sales_Order_Shipment_DtlsRepository extends JpaRepository<Sales_Order_Shipment_Dtls, Long> {
	
	@Query( "select s from Sales_Order_Shipment_Dtls s where s.modified_type = 'INSERTED' and s.order_id =:order_id ")
	Sales_Order_Shipment_Dtls getSalesOrdShipDtls(@Param("order_id") String order_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Sales_Order_Shipment_Dtls w SET w.modified_type =:mstatus WHERE w.order_id = :order_id")
    int sales_Order_Shipment_Dtlsupdate(@Param("order_id") String order_id,@Param("mstatus") String mstatus);

	@Query(value="select s.* from sales_order_shipment_dtls s where s.modified_type = 'INSERTED' and s.order_id =:order_id ",nativeQuery = true)
	Map<String,Object> getSalesOrdShipDtlsFast(@Param("order_id") String order_id);
}
