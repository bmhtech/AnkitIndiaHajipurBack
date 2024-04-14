package com.AnkitIndia.jwtauthentication.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Shipment_Dtls;

public interface Delivery_challan_Shipment_DtlsRepository extends JpaRepository<Delivery_challan_Shipment_Dtls, Long>{
	
	@Query( "select s from Delivery_challan_Shipment_Dtls s where s.modified_type = 'INSERTED' and s.delivery_cid =:delivery_cid ")
	Delivery_challan_Shipment_Dtls rdlvChallanShipmentDtls(@Param("delivery_cid") String delivery_cid);
	
	@Query(value="SELECT * from delivery_challan_shipment_dtls where delivery_cid=:delvid and modified_type = 'INSERTED'", nativeQuery=true)
	Map<String,Object> getDlvChallanShipmentDtlsFast(@Param("delvid") String delvid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_Shipment_Dtls w SET w.modified_type =:mstatus WHERE w.delivery_cid = :delivery_cid")
    int deliveryChallanShipDtlsupdate(@Param("delivery_cid") String delivery_cid,@Param("mstatus") String mstatus);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_Shipment_Dtls w SET w.modified_type ='DELETED' WHERE w.delivery_cid = :delivery_cid")
    int delivery_challan_Shipment_DtlsUpdate(@Param("delivery_cid") String delivery_cid);
	
}
