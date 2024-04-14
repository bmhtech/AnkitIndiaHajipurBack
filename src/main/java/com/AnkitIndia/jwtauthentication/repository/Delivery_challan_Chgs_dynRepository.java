package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Chgs_dyn;

public interface Delivery_challan_Chgs_dynRepository extends JpaRepository<Delivery_challan_Chgs_dyn, Long>{
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_Chgs_dyn w SET w.modified_type =:mstatus WHERE w.delivery_cid = :delivery_cid")
    int delivery_challan_Chgs_dynupdate(@Param("delivery_cid") String delivery_cid,@Param("mstatus") String mstatus);

	@Query(value="select * from delivery_challan_chgs_dyn where modified_type='INSERTED' and delivery_cid=:delivery_cid", nativeQuery = true)
    List<Map<String,Object>> getDelivery_challan_Chgs_dyn(@Param("delivery_cid") String delivery_cid);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Delivery_challan_Chgs_dyn t SET t.distance_in_km =:distance WHERE t.delivery_cid = :delv_id")
	int updateTransporterDetailsthruPopup(@Param("delv_id") String delv_id,@Param("distance") double distance);

}
