package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization_details;

public interface GatepassGateoutAuthorizationRepository extends JpaRepository<GatepassGateoutAuthorization, Long>{
	
	@Query("select COUNT(id) from GatepassGateoutAuthorization")
	String countId();
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE GatepassGateoutAuthorization_details p SET p.modified_type = 'UPDATED' WHERE  p.modified_type = 'INSERTED'  and p.gp_go_auth_id =:gp_go_auth_id" )
	int updatedetails(@Param("gp_go_auth_id") String gp_go_auth_id);
	
	@Query("select a from GatepassGateoutAuthorization a where a.modified_type='INSERTED' and a.vechileid =:vehicle_id and a.gate_outstatus ='NO'")
	GatepassGateoutAuthorization getgatepassauthstaticdetails(@Param("vehicle_id") String vehicle_id);
	
	
	@Query("select a from GatepassGateoutAuthorization_details a where a.modified_type='INSERTED' and a.gp_go_auth_id =:authid")
	List<GatepassGateoutAuthorization_details> getgatepassauthdetails(@Param("authid") String authid);
	 //authid

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Vehicle_weighment_load_unload p SET p.gp_go_auth_id=:gp_go_auth_id,p.gatepass_status='AUTH' WHERE p.vehicle_id = :vehicleid and p.weighment_status=2 and p.modified_type != 'DELETED' and p.gatepass_status='IN'" )
	int updateVehicleLoadunload(@Param("vehicleid") String vehicleid,@Param("gp_go_auth_id") String gp_go_auth_id);
	
	
	@Query("select a from GatepassGateoutAuthorization a where a.modified_type='INSERTED'")
	List<GatepassGateoutAuthorization> getGatepassgetouta_List();
	
}
