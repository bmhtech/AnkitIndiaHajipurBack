package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Outstandingledger;

public interface OutstandingledgerRepository extends JpaRepository<Outstandingledger, Long>{

	@Query( "select l from Outstandingledger l where l.modified_type ='INSERTED' and l.referenceno =:referenceno ")
	Outstandingledger getOutstandingledgerDtls(@Param("referenceno") String referenceno);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Outstandingledger l SET l.adjustmentamount =l.adjustmentamount+:payableamt,l.duesamount = l.duesamount-:payableamt  WHERE l.referenceno =:invoice_id and l.ledgerid =:ledgerid" )
    int updateOutstandingledger(@Param("ledgerid") String ledgerid,@Param("invoice_id") String invoice_id,@Param("payableamt") double payableamt);
	
}
