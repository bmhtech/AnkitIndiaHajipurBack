package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.VehicleTypeMaster;

public interface VehicleTypeMasterRepository extends JpaRepository<VehicleTypeMaster, Long>{
	
	@Query("SELECT MAX(SUBSTRING(vehtype_id , 4 , length(vehtype_id))) FROM VehicleTypeMaster where vehtype_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query("select max(substring(vehtype_code , 3, length(vehtype_code))) from VehicleTypeMaster where vehtype_code like %:code% ")
	String getVtypeSequenceId(@Param("code") String code);
	
	@Query( "select v from VehicleTypeMaster v where v.vehtype_active = :sts and  v.modified_type != 'DELETED' ")
	List<VehicleTypeMaster> getVehicleTNameCode(@Param("sts") Boolean sts);
	
	@Query( "select v from VehicleTypeMaster v where v.vehtype_code = :code ")
	VehicleTypeMaster getVehicleTypeNByCode(@Param("code") String code);
	
	@Query("select vehtype_code,vehtype_name from VehicleTypeMaster")
	List<VehicleTypeMaster> getVehicleTypeNCList();

}
