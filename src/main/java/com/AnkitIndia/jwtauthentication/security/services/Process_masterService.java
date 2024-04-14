package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Process_master_doc;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_ListDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_shiftDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_shift_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.TimeCalculationDTO;


public interface Process_masterService {
	
	public SequenceIdDTO getPMSequenceId(String prefix,String company);
	
	public TimeCalculationDTO getShiftTime(String sstime,int shifthrs);
	
	//public Process_master save(Process_master pMaster);
	public Process_master save(Process_master pMaster,MultipartFile files[]) throws IOException;

	public List<Process_master> findAllProcess();
	

	public List<Process_master_ListDTO> findAllProcessList();

	
	public Process_master findOne(long id);
	
	public Process_master_doc findOneprocessdoc(long id);
	
	public void deleteProcessDocument(Process_master_doc process_master_doc);
	
	
	public List<Process_master_docDTO> getProcessDocs(String pid);
	
	public List<Process_master_docDTO> getdocumentListwithfile(String doc_pdf);
	
	public List<Process_master_shiftDTO> getProcessShift(String pid);
	
	public List<Process_master_shift_infoDTO> getProcessShiftThruDate(String pid,String sdate);
	
	public List<Process_masterDTO> getProcess();
	
	public List<Process_masterDTO> getProcessThruBUShopFloor(String bunit,String sfloor,String company);
	
	public List<Process_masterDTO> getProcessThruBUShopFloorRegular(String bunit,String sfloor,String company);
	
	public List<Process_masterDTO> getProcessThruBUShopFloorSpl(String bunit,String sfloor,String company);
	
	public Process_masterDTO getProcessThruBUSFProDesc(String bunit,String sfloor,String pdesc,String company);
	
	//public Process_master update(Process_master pMaster,long id);
	public Process_master update(Process_master pMaster,MultipartFile files[]);
	
	
	 public Process_master deleteProcessMaster(Process_master pMaster,long id);
}
