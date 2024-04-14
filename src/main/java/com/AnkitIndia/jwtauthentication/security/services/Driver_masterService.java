package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Driver_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Driver_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Driver_masterService {
	
	//public Driver_master save(MultipartFile[] files,Driver_master dMaster);
	
	public Driver_master save(Driver_master dMaster);
	
	public Driver_master savepopup(MultipartFile files[],Driver_master dMaster) throws IOException;
	
	public List<Driver_master> findAll();
	
	public List<Driver_masterDTO> getDriverList();
	
	public List<Map<String,Object>> getDriverListnew();
	
	public List<Driver_masterDTO> getDriverByVehicle(String vid);
	
	public Driver_masterDTO getDriverDetails(String did);
	
	public List<Driver_masterDTO> chkDriverStatus(String doc);
	
	public Driver_master findOne(long id);
	
    public Driver_master update(MultipartFile files[],Driver_master dMaster) throws IOException;
	

	public Driver_master deleteDriver(Driver_master dMaster,long id);

}
