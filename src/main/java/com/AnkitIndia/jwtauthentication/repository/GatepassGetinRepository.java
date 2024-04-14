package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.GatepassGetin;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin_details;

public interface GatepassGetinRepository extends JpaRepository<GatepassGetin, Long>{

	@Query("select COUNT(id) from GatepassGetin")
	String countId();
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE GatepassGetin_details p SET p.modified_type = 'UPDATED' WHERE p.gp_gi_id =:gp_gi_id and p.modified_type = 'INSERTED'" )
	int updategetInDetails(@Param("gp_gi_id") String gp_gi_id);
	
	
	
	@Query("select g from GatepassGetin g where g.modified_type != 'DELETED'")
	List<GatepassGetin> getGatepassgetin_List();
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Vehicle_weighment_load_unload p SET p.gp_gi_id=:gp_gi_id,p.gatepass_status='IN' WHERE p.vehicle_id = :vehicleid and p.weighment_status=0 and p.modified_type != 'DELETED'" )
	int updateVehicleLoadunload(@Param("vehicleid") String vehicleid,@Param("gp_gi_id") String gp_gi_id);
	
	
	@Query("select g from GatepassGetin_details g where g.modified_type = 'INSERTED' and g.gp_gi_id =:gp_gi_id")
	List<GatepassGetin_details> getGatepassgetinretrivedetails(@Param("gp_gi_id") String gp_gi_id);
	
	@Query("select g from GatepassGetin g where g.modified_type = 'INSERTED' and g.gp_gi_id =:gp_gi_id")
	List<GatepassGetin> getGatepassdetails(@Param("gp_gi_id") String gp_gi_id);
	
}
