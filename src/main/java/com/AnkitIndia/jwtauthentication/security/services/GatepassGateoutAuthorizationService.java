package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization_details;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner;


public interface GatepassGateoutAuthorizationService {

	public GatepassGateoutAuthorization save(GatepassGateoutAuthorization gatepassGateoutAuthorization);
	
	public GatepassGateoutAuthorization update(GatepassGateoutAuthorization gatepassGateoutAuthorization,Long id);
	
	
	public List<GatepassGateoutAuthorization> getGatepassgetouta_List();
	
	public GatepassGateoutAuthorization findOne(long id);
	
	public List<GatepassGateoutAuthorization_details> getGatepassgetoutaretrivedetails(String gp_go_auth_id);
	
}
