package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order_BPDetails;

public interface Pur_Order_BPDetailsRepository extends JpaRepository<Pur_Order_BPDetails, Long>{

	@Query( "select p from Pur_Order_BPDetails p where p.pur_orderid = :code and p.modified_type = 'INSERTED'" )
	Pur_Order_BPDetails purOrdBPDRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_BPDetails w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_BPDetailsupdate(@Param("pur_orderid") String pur_orderid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_BPDetails w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_BPDetailsUpdate(@Param("pur_orderid") String pur_orderid);
}
