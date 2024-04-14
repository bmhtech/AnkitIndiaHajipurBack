package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Party_Dtls;

public interface Delivery_challan_Party_DtlsRepository extends JpaRepository<Delivery_challan_Party_Dtls, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_Party_Dtls w SET w.modified_type =:mstatus WHERE w.delivery_cid = :delivery_cid")
    int deliveryChallanPartyDtlsupdate(@Param("delivery_cid") String delivery_cid,@Param("mstatus") String mstatus);

	@Query( "select s from Delivery_challan_Party_Dtls s where s.modified_type = 'INSERTED' and s.delivery_cid =:delivery_cid ORDER BY s.sl_no")
	List<Delivery_challan_Party_Dtls> getDlvCPtyDtls(@Param("delivery_cid") String delivery_cid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_Party_Dtls w SET w.modified_type ='DELETED' WHERE w.delivery_cid = :delivery_cid")
    int delivery_challan_Party_DtlsUpdate(@Param("delivery_cid") String delivery_cid);
}
