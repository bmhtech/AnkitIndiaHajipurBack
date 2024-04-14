package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Dailyproduction;
import com.AnkitIndia.jwtauthentication.model.Dailyproduction_Dtls;
import com.AnkitIndia.jwtauthentication.model.Dailyproduction_Dtls_One;
import com.AnkitIndia.jwtauthentication.model.Item_master_pack_mat_tag;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;


public interface DailyproductionService {
	
	public List<Dailyproduction> getDailyproductionList(String currDate,String finyear);
	
	public Dailyproduction save(Dailyproduction dailyproduction);
	
	public Dailyproduction update(Dailyproduction dailyproduction,long id);
	
	public Dailyproduction delete(Dailyproduction dailyproduction,long id);
	
	public List<Dailyproduction> searchDailyProduction(String fromdate, String todate,String finyear);
	
	public Dailyproduction_Dtls productionreportitembydata(String itemId, String date);
	
	public Dailyproduction findOne(long id);
	
	public List<Dailyproduction_Dtls> retriveProductionDetails(String dailyproductionid);
	
	public List<Dailyproduction_Dtls_One> retriveProductionDetails1(String dailyproductionid);
	
	public List<Item_masterDTO> getItemCodeByTypeNew(String type);
	
	public Item_master_pack_mat_tag getItemCapacity(String item_code,String finyear);
}
