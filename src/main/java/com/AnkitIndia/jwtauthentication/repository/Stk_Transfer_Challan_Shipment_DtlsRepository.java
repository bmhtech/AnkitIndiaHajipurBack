package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Shipment_Dtls;

public interface Stk_Transfer_Challan_Shipment_DtlsRepository extends JpaRepository<Stk_Transfer_Challan_Shipment_Dtls, Long>{
	
	@Query( "select s from Stk_Transfer_Challan_Shipment_Dtls s where s.stk_challan_id =:stk_challan_id and s.modified_type ='INSERTED'")
	Stk_Transfer_Challan_Shipment_Dtls getStkTransChallanShipDtls(@Param("stk_challan_id") String stk_challan_id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Stk_Transfer_Challan_Shipment_Dtls s SET s.modified_type ='UPDATED' WHERE s.stk_challan_id = :stk_challan_id")
    int updateStk_Transfer_Challan_Shipment_Dtls(@Param("stk_challan_id") String stk_challan_id);

}
