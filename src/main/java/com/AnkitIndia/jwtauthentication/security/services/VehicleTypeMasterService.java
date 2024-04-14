package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.VehicleTypeMaster;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.VehicleTypeMasterDTO;

public interface VehicleTypeMasterService {
	
	public SequenceIdDTO getVtypeSequenceId(String prefix);
	
	public VehicleTypeMaster save(VehicleTypeMaster vehicletype);
	
	public List<VehicleTypeMaster> findAll();
	
	public List<VehicleTypeMaster> getVehicleTypeNCList();
	
	public VehicleTypeMaster findOne(long id);
	
	public VehicleTypeMaster update(VehicleTypeMaster vehicletype,long id);
	
	public List<VehicleTypeMasterDTO> getVehicleTNameCode();
	
	public VehicleTypeMasterDTO getVehicleTypeNByCode(String code);
	
	public VehicleTypeMaster deleteVehicleType(VehicleTypeMaster vehicletype,long id);
	
	public List<VehicleTypeMaster> findVehicleType(String searchtext);
	

}
