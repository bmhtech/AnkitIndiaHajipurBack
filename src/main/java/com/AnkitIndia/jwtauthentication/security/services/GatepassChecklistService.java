package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;

import com.AnkitIndia.jwtauthentication.model.GatepassChecklist;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface GatepassChecklistService {

	public GatepassChecklist save(GatepassChecklist gatepassChecklist);
	
	public List<GatepassChecklist> getGatepasschecklistin();
	
	public List<GatepassChecklist> getGatepasschecklistout();
	

	public List<GatepassChecklist> findAll();
    
    public GatepassChecklist findOne(long id);
    
    public GatepassChecklist update(GatepassChecklist gatepassChecklist,long id);
    
    public StatusDTO checkGatePassCheckListUsage(String code);
    
    public GatepassChecklist deleteGatePassCheckList(GatepassChecklist gatepassChecklist,long id);
}
