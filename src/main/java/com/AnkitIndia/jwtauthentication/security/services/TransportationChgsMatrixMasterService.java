package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.TransportationChgsMatrixMaster;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.TransportationChgsMatrixMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Transportation_chgs_matrix_detailsDTO;




public interface TransportationChgsMatrixMasterService {
	
	public SequenceIdDTO getTCMId(String prefix,String company);
	
	public TransportationChgsMatrixMaster save(TransportationChgsMatrixMaster transportation);
	
	public List<TransportationChgsMatrixMaster> findAll();
	
	public List<TransportationChgsMatrixMaster> getTransportationCMNCList();
	
	public TransportationChgsMatrixMaster findOne(long id);
	
	public TransportationChgsMatrixMaster update(TransportationChgsMatrixMaster transportation,long id);
	
	
	public List<TransportationChgsMatrixMasterDTO> getTransChgNameCode();
	
	public List<Map<String,Object>> getTransChargeCode(String trans_id,String transfrom,String transto,String type);
	
	public List<Transportation_chgs_matrix_detailsDTO> transChrgsMatRetriveList(String t_id);
	
	public List<TransportationChgsMatrixMaster> findTransportationChgsMatrixMaster(String searchtext);
	
	public TransportationChgsMatrixMaster deleteTCMM(TransportationChgsMatrixMaster transportationChgsMatrixMaster,long id);
}
