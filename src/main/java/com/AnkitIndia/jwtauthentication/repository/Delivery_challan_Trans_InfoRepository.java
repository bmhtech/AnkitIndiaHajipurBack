package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Trans_Info;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_weight_dtl;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Trans_InfoDTO;

public interface Delivery_challan_Trans_InfoRepository extends JpaRepository<Delivery_challan_Trans_Info, Long>{
	
	@Query( "select s from Delivery_challan_Trans_Info s where s.modified_type = 'INSERTED' and s.delivery_cid =:delivery_cid ")
	Delivery_challan_Trans_Info getDlvChallanTransInfo(@Param("delivery_cid") String delivery_cid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_Trans_Info w SET w.modified_type =:mstatus WHERE w.delivery_cid = :delivery_cid")
    int deliveryChallanTrInfoupdate(@Param("delivery_cid") String delivery_cid,@Param("mstatus") String mstatus);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_Trans_Info w SET w.modified_type ='DELETED' WHERE w.delivery_cid = :delivery_cid")
    int delivery_challan_Trans_InfoUpdate(@Param("delivery_cid") String delivery_cid);
	
	@Query(value="SELECT * FROM delivery_challan_Trans_Info WHERE modified_type = 'INSERTED' AND delivery_cid=:delvid ",nativeQuery=true)
	Map<String,Object> getSalesOrdIfMultiTransInfo(@Param("delvid") String delvid);
	
}
