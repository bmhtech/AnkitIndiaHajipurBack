package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order_Termination_dyn;

public interface Pur_Order_Termination_dynRepository extends JpaRepository<Pur_Order_Termination_dyn, Long>{
	
	@Query("select p from Pur_Order_Termination_dyn p where p.pur_orderid = :porderid and p.modified_type = 'INSERTED' ")
	List<Pur_Order_Termination_dyn> getPurOrdTermList(@Param("porderid") String porderid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Termination_dyn w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_Termination_dynupdate(@Param("pur_orderid") String pur_orderid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_Order_Termination_dyn w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int pur_Order_Termination_dynUpdate(@Param("pur_orderid") String pur_orderid);

}
