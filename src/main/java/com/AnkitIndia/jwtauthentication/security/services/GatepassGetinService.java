package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.GatepassGetin;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin_details;
import com.AnkitIndia.jwtauthentication.model.Process_master;


public interface GatepassGetinService {

	public GatepassGetin save(GatepassGetin gatepassGetin,MultipartFile files) throws IOException;
	
	public GatepassGetin update(GatepassGetin gatepassGetin,MultipartFile files) throws IOException;
	
	public List<GatepassGetin> getGatepassgetin_List();
	
	public GatepassGetin findOne(long id);
	
	public List<GatepassGetin_details> getGatepassgetinretrivedetails(String gp_gi_id);
	
}
