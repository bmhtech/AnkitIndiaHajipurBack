package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.model.Vehicle_master_doc_details;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.VehicleMasterDTO;



public interface VehicleMasterService {
	
	public VehicleMaster save(VehicleMaster vehicle,MultipartFile files[]) throws IOException;
	
	public VehicleMaster popupsave(VehicleMaster vehicle);
	
	public List<VehicleMaster> findAll();

	public List<VehicleMaster> getVehicleNCList();
	
	public List<Vehicle_master_doc_details> getVehicleDocDtls(String vid);
	
	public VehicleMaster findOne(long id);
	
	public VehicleMaster update(VehicleMaster vehicle,MultipartFile files[]) throws IOException;
	
	public VehicleMaster deleteVehicleMaster(VehicleMaster vehicle,long id);
	
	public List<VehicleMasterDTO> getVehicleNameCode();
	
	public List<VehicleMasterDTO> getVehicleThruWeighment();
	
	public List<Map<String,Object>> getVehicleThruWeighmentfast();
	
	public VehicleMasterDTO getVehicleDetails(String vid);
	
	public List<VehicleMasterDTO> getVehicleThruTransporter(String tid);
	
	public List<VehicleMasterDTO> getVehicleThruTransWOWt1(String tid);
	
	public List<VehicleMasterDTO> getVehicleThruTransWOWt2(String tid);
	
	public List<VehicleMasterDTO> getVehicleNumberByVtype(String vtype);
	
	public List<VehicleMasterDTO> getVehicleTypeName(String vcode);
	
	public SequenceIdDTO getSequenceId(String prefix);
	
	public SequenceIdDTO getVehicleSequenceId(String prefix,String company);
	
	public List<VehicleMaster> findVehicle(String company,String searchtext);
	
	//public StatusDTO checkVehicleMasterUsage(String code);
	
	public MessageStatusDTO chkVehicleNoStatus(String sno);
	
	public List<Map<String,Object>> allVechileList();
	
}
