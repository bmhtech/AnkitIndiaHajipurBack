package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.AnkitIndia.jwtauthentication.model.Charge_Master;
import com.AnkitIndia.jwtauthentication.model.Dieselusedimport;
import com.AnkitIndia.jwtauthentication.model.GatepassChecklist;
import com.AnkitIndia.jwtauthentication.model.Powercutreport;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note;
import com.AnkitIndia.jwtauthentication.responseDTO.Powercutreport_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;



public interface PowercutreportService {
	

	public Page<Powercutreport_Pagination_DTO> getpowercutlist(int page,int size);

	//public List<Powercutreport_Pagination_DTO> searchpowercut(String fromdate, String todate,String finyear);
	
	public List<Powercutreport> getpowercutDatalist(String currDate,String finyear);
	
	public List<Powercutreport> searchpowercut(String fromdate, String todate,String finyear);
	
	public Powercutreport save(Powercutreport powercutreport);
	
	public Powercutreport findOne(long id);
	
	public Powercutreport update(Powercutreport powercutreport,long id);
	
	public Powercutreport deletePowerCutReport(Powercutreport powercutreport,long id);
	
}
