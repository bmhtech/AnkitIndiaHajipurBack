package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_broker;

public interface Pur_good_receipt_brokerRepository extends JpaRepository<Pur_good_receipt_broker, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt_broker w SET w.modified_type ='UPDATED' WHERE w.grn_id = :grn_id")
    int updatePur_good_receipt_broker(@Param("grn_id") String grn_id);
	
	@Query( "select p from Pur_good_receipt_broker p where p.grn_id =:grnid and p.modified_type ='INSERTED'")
	List<Pur_good_receipt_broker> getPurGoodRcptBrokerList(@Param("grnid") String grnid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_good_receipt_broker w SET w.modified_type ='DELETED' WHERE w.grn_id = :grn_id")
    int pur_good_receipt_brokerUpdate(@Param("grn_id") String grn_id);
	
}
