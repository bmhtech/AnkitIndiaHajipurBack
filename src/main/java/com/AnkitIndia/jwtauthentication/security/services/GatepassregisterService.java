package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Gatepassregister;

public interface GatepassregisterService {

	public List<Gatepassregister> getGatepassRegisterList(String currDate,String finyear);

	public Gatepassregister save(Gatepassregister gatepassregister);
	
	public Gatepassregister update(Gatepassregister gatepassregister,long id);
	
	public Gatepassregister delete(Gatepassregister gatepassregister,long id);
	
	public List<Gatepassregister> searchgatePassRegister(String fromdate, String todate,String finyear);
	
	public Gatepassregister findOne(long id);
	
}
