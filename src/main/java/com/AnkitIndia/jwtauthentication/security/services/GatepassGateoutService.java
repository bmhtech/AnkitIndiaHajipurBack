package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;


import com.AnkitIndia.jwtauthentication.model.GatepassGateout;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization_details;
import com.AnkitIndia.jwtauthentication.model.GatepassGateout_details;


public interface GatepassGateoutService {
	
	public List<GatepassGateoutAuthorization> getVehicleListgatepassout();
	
	public GatepassGateoutAuthorization getVehicleListgatepassauth(String vehicle_id);
	
    public List<GatepassGateoutAuthorization_details> getgatepassauthdetails(String authid);
    
    public GatepassGateout save(GatepassGateout gatepassGateout);
    
    
    public List<GatepassGateout> findAll();
    
    public GatepassGateout findOne(long id);
    
    public List<GatepassGateout_details> retriveGatepassGateOutDetails(String gp_go_id);
    
   // public GatepassGateout update(GatepassGateout gatepassGateout,long id);

}
