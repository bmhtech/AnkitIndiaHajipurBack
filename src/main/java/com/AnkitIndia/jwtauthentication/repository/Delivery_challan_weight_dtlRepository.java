package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Shipment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_weight_dtl;

public interface Delivery_challan_weight_dtlRepository extends JpaRepository<Delivery_challan_weight_dtl, Long>{
	
	@Query( "select s from Delivery_challan_weight_dtl s where s.modified_type = 'INSERTED' and s.delivery_cid =:delivery_cid ")
	Delivery_challan_weight_dtl getDlvChallanWeightDtls(@Param("delivery_cid") String delivery_cid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_weight_dtl w SET w.modified_type =:mstatus WHERE w.delivery_cid = :delivery_cid")
    int deliveryChallanWeightDtlsupdate(@Param("delivery_cid") String delivery_cid,@Param("mstatus") String mstatus);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_weight_dtl w SET w.modified_type ='DELETED' WHERE w.delivery_cid = :delivery_cid")
    int delivery_challan_weight_dtlUpdate(@Param("delivery_cid") String delivery_cid);
	
	@Query(value="select * from delivery_challan_weight_dtl s where s.modified_type = 'INSERTED' and s.delivery_cid =:delivery_cid ", nativeQuery = true)
	Map<String, Object> getDlvChallanWeightDtlsMulti(@Param("delivery_cid") String delivery_cid);
}
