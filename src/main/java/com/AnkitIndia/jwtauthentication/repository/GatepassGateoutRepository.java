package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.GatepassGateout;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization;
import com.AnkitIndia.jwtauthentication.model.GatepassGateout_details;

public interface GatepassGateoutRepository extends JpaRepository<GatepassGateout, Long>{
	
	@Query("select COUNT(id) from GatepassGateout")
	String countId();
	
	@Query("select g from GatepassGateoutAuthorization g where g.modified_type!='DELETED' and g.gate_outstatus='NO'")
	List<GatepassGateoutAuthorization>getVehicleListgatepassout();
	//getVehicleListgatepassout
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE GatepassGateoutAuthorization p SET p.gate_outstatus='YES' WHERE p.gp_go_auth_id = :refid and p.modified_type ='INSERTED'" )
	int updateGatepassAuthset(@Param("refid") String refid);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Vehicle_weighment_load_unload p SET p.gp_go_id=:gp_go_id,p.gatepass_status='OUT' WHERE p.vehicle_id = :vehicleid and p.weighment_status=2 and p.modified_type != 'DELETED' and p.gatepass_status='AUTH'" )
	int updateVehicleLoadunload(@Param("vehicleid") String vehicleid,@Param("gp_go_id") String gp_go_id);
	
	@Query("select g from GatepassGateout g where g.modified_type='INSERTED'")
	List<GatepassGateout> findGetoutList();
	
	@Query( "select g from GatepassGateout_details g where g.gp_go_id = :gp_go_id and g.modified_type ='INSERTED'" )
	List<GatepassGateout_details> retriveGatepassGateOutDetails(@Param("gp_go_id") String gp_go_id);
	
	@Query( "select g from GatepassGateout g where g.gp_go_id = :gp_go_id and g.modified_type ='INSERTED'" )
	List<GatepassGateout> getgateoutdetails(@Param("gp_go_id") String gp_go_id);
}
