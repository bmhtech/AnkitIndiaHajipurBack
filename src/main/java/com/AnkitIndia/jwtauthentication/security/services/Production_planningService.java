package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Production_planning;
import com.AnkitIndia.jwtauthentication.model.Production_planning_periodic_date_details;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planningDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_periodic_date_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shiftDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shift_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shift_timeDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_shop_floor_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_special_date_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_special_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Production_planning_up;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface Production_planningService {

	public SequenceIdDTO getPPSequenceId(String prefix,String bunit,String company);
	
	public Production_planning save(Production_planning planning) throws JsonParseException, JsonMappingException, IOException;

	public List<Production_planningDTO> findAllProdPlanning();
	
	public Production_planning findOne(long id);
	
	public List<Production_planningDTO> getProdPlanning();
	
	public Production_planning update(Production_planning planning,long id) throws JsonParseException, JsonMappingException, IOException;
	
	public List<Production_planning_shop_floor_dtlsDTO> getProdPlanFloorDtls(String prodid);
	
	public List<Production_planning_special_dtlsDTO> getProdPlanSpecialDtls(String prodid);
	
	public List<Production_planning_periodic_date_detailsDTO> getProdPlanPerDateDtls(String prodid,String proddateid);
	
	public List<Production_planning_periodic_date_details> getProdPlanPerDateDtlsShiftNo(String prodid,String proddateid);
	
	public List<Production_planning_special_date_detailsDTO> getProdPlanSplDateDtls(String prodid,String proddateid);
	
	public List<Production_planning_shop_floor_dtlsDTO> getProcessThruProPlan(String pdate,String bunit,String sfloor,String company);
	
	public Production_planning_shop_floor_dtlsDTO getProcessThruProdPlan(String pdate,String bunit,String sfloor,String process,String company);
	
	public List<Production_planning_special_dtlsDTO> getProdPlanSplDtls(String bunit,String sfloor,String company);
	
	public Map<String,Object> getProdPlanSplProcessDtls(String bunit,String sfloor,String process,String company);
	
	public List<Production_planning_shift_detailsDTO> getProdPlanShiftDtls(String businessunit,String shopfl,String tdate);
	
	public Production_planning_shift_detailsDTO getProdPlanShiftDetails(String shiftid);
	
	public List<Production_planning_shiftDTO> getProdPlanShifts(String process,String shopfl,String startdate,String enddate);
	
	public List<Production_planning_shiftDTO> getProdPlanShiftsFrom(String process,String shopfl,String startdate);
	
	public StatusDTO getProdPlanShiftStatus(String process,String shopfl,String sdate);
	
	public List<Production_planning_shift_timeDTO> getProdTranShiftTime(String bunit,String sfloor,String process,String shiftid);
	
	public List<Production_planning_shift_timeDTO> getProdTranShiftTimeShiftNo(String bunit,String sfloor,String process,String shiftid);
	
	public Production_planning_up checkdaterangeupdate( String date);
	
	public Production_planning deleteProductionPlanning(Production_planning pPlanning,long id);
	
	 public List<Map<String, Object>> getProdInputReport(String business_unit,String shop_floor,String fromdate,String todate);
	 
	 public List<Map<String, Object>> getProdOutputReport(String business_unit,String shop_floor,String fromdate,String todate);
}
