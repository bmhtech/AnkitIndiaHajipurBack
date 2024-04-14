package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.AnkitIndia.jwtauthentication.model.Item_rate_dtls;
import com.AnkitIndia.jwtauthentication.model.Ratechart;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface RatechartService 
{
	public SequenceIdDTO getRSequenceId(String fin_year);
	
	public Ratechart save(Ratechart ratechart);
	
	public List<Map<String, Object>> getRateChartList(String fin_year);
	
	public List<Map<String, Object>> RateChartList();
	
	public Ratechart findOne(long id);
	
	public List<Map<String, Object>> rateRetriveList(String r_code);
	
	public Ratechart update(Ratechart ratechart,long id);
	
	public Ratechart delete(Ratechart ratechart,long id);
	
	public StatusDTO getRateChartDateVerify(String date);
	
	public List<Item_rate_dtls> getRateChartItemthdt(String date);
	
	public List<Map<String,Object>> getRateChartItemList(String curr_date, String b_unit, String inv_type);
	
	public Map<String,Object> getRateItemQty(String order_date, String itemid, String packingid, long id,String pricebasedon);
	
}
