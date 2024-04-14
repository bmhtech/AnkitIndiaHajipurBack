package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order_Termination;

public interface Pur_Order_TerminationRepository extends JpaRepository<Pur_Order_Termination, Long>{

	@Query( "select p from Pur_Order_Termination p where p.pur_orderid = :code and p.modified_type = 'INSERTED'" )
	Pur_Order_Termination purOrdTerminateRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Termination w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_Terminationupdate(@Param("pur_orderid") String pur_orderid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Termination w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_TerminationUpdate(@Param("pur_orderid") String pur_orderid);
}
