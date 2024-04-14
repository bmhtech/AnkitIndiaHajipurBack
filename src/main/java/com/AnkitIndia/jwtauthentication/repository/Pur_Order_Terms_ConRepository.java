package com.AnkitIndia.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order_Terms_Con;

public interface Pur_Order_Terms_ConRepository extends JpaRepository<Pur_Order_Terms_Con, Long>{

	@Query( "select p from Pur_Order_Terms_Con p where p.pur_orderid = :code and p.modified_type = 'INSERTED'" )
	Pur_Order_Terms_Con purOrdTransConRetriveList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Terms_Con w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_Terms_Conupdate(@Param("pur_orderid") String pur_orderid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Terms_Con w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_Terms_ConUpdate(@Param("pur_orderid") String pur_orderid);
}
