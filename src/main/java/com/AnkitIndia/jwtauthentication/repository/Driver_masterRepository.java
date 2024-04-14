package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.jwtauthentication.model.Driver_master;
import com.AnkitIndia.jwtauthentication.model.Item_group_master;

public interface Driver_masterRepository extends JpaRepository<Driver_master, Long>{
	
	@Query("select COUNT(id) from Driver_master")
	String countId();
	
	@Query("select d from Driver_master d where d.modified_type !='DELETED' ")
	List<Driver_master> getDriverList();
	
	@Query(value="SELECT *,(SELECT v.vehicle_no FROM `vehicle_master` v WHERE v.vehicle_id=d.veh_no AND v.modified_type !='DELETED') AS vehicle_no FROM driver_master d WHERE d.modified_type !='DELETED' ", nativeQuery=true)
	List<Map<String,Object>> getDriverListnew();
	
	@Query("select d from Driver_master d where d.modified_type !='DELETED' AND d.veh_no =:vid ")
	List<Driver_master> getDriverByVehicle(@Param("vid") String vid);
	
	@Query("select d from Driver_master d where d.modified_type !='DELETED' AND d.driver_id =:did ")
	Driver_master getDriverDetails(@Param("did") String did);
	
	@Query("select d from Driver_master d where d.modified_type != 'DELETED' and d.doc_no =:doc ")
	List<Driver_master> chkDriverStatus(@Param("doc") String doc);

}
