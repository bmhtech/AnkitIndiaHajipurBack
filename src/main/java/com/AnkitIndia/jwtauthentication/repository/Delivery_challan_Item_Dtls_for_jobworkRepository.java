package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Item_Dtls_for_jobwork;

public interface Delivery_challan_Item_Dtls_for_jobworkRepository extends JpaRepository<Delivery_challan_Item_Dtls_for_jobwork, Long>{

	
	@Query(value= "select * from delivery_challan_item_dtls_for_jobwork  where modified_type ='INSERTED' and delivery_cid =:delivery_cid ",nativeQuery=true)
	List<Map<String,Object>> getDlvChallanItemjobworkRetriveList(@Param("delivery_cid") String delivery_cid);
	
	@Query(value = "SELECT IFNULL(0, 1) FROM delivery_challan_Item_Dtls_for_jobwork pl WHERE pl.modified_type = 'INSERTED' AND  pl.delivery_cid=:delivery_cid AND pl.job_item_name LIKE '%BULK SUPPLY%' UNION SELECT 1 LIMIT 1",nativeQuery=true)
	int searchJobItem(@Param("delivery_cid") String delivery_cid);
	
}
