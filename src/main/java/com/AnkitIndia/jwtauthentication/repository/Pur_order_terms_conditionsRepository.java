package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Pur_order_terms_conditions;

public interface Pur_order_terms_conditionsRepository extends JpaRepository<Pur_order_terms_conditions, Long> {

	@Query(value= "SELECT * FROM pur_order_terms_conditions WHERE pur_orderid='NA'", nativeQuery=true)
	List<Map<String, Object>> getTermsConditionsDtlsList();
	
	@Query(value= "SELECT * FROM pur_order_terms_conditions p WHERE p.pur_orderid =:code AND p.modified_type ='INSERTED' ORDER BY p.slno",nativeQuery = true)
	List<Map<String, Object>> getPurOrdTermsCondDynList(@Param("code") String code);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_order_terms_conditions w SET w.modified_type ='UPDATED' WHERE w.pur_orderid = :pur_orderid")
    int pur_order_terms_conditionsupdate(@Param("pur_orderid") String pur_orderid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Pur_order_terms_conditions w SET w.modified_type ='DELETED' WHERE w.pur_orderid = :pur_orderid")
    int purOrderTermsCondDynUpdate(@Param("pur_orderid") String pur_orderid);
	
	@Query(value= "SELECT * FROM pur_order_terms_conditions WHERE pur_orderid=:pur_orderid AND modified_type='INSERTED'", nativeQuery=true)
	List<Map<String, Object>> purOrdTramsCondition(@Param("pur_orderid") String pur_orderid);
}
