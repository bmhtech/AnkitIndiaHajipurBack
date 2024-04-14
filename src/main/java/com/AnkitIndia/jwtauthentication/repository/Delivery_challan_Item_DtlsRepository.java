package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Item_Dtls;

public interface Delivery_challan_Item_DtlsRepository extends JpaRepository<Delivery_challan_Item_Dtls, Long>{
	
	@Query( "select s from Delivery_challan_Item_Dtls s where s.modified_type = 'INSERTED' and s.delivery_cid =:delivery_cid ORDER BY s.slno")
	List<Delivery_challan_Item_Dtls> getDelivery_challan_Item_Dtls(@Param("delivery_cid") String delivery_cid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_Item_Dtls w SET w.modified_type =:mstatus WHERE w.delivery_cid = :delivery_cid")
    int deliveryChallanItemDtlsupdate(@Param("delivery_cid") String delivery_cid,@Param("mstatus") String mstatus);
	
	@Query( "select s from Delivery_challan_Item_Dtls s where s.modified_type = 'INSERTED' and s.delivery_cid =:delivery_cid ORDER BY s.slno")
	List<Delivery_challan_Item_Dtls> getDlvCItmDtls(@Param("delivery_cid") String delivery_cid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_Item_Dtls w SET w.modified_type ='DELETED' WHERE w.delivery_cid = :delivery_cid")
    int delivery_challan_Item_DtlsUpdate(@Param("delivery_cid") String delivery_cid);
	
	@Query(value = "SELECT IFNULL(0, 1) FROM delivery_challan_Item_Dtls pl WHERE pl.modified_type = 'INSERTED' AND  pl.delivery_cid=:delivery_cid AND pl.item_name LIKE '%BULK SUPPLY%' UNION SELECT 1 LIMIT 1",nativeQuery=true)
	int searchBulkItem(@Param("delivery_cid") String delivery_cid);
	
}
