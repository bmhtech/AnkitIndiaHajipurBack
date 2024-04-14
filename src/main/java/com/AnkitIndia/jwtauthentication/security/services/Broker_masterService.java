package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Broker_master;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.responseDTO.Broker_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_adviceDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_accountDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_addDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_add_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_docDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_othDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_ptyDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_statutoryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_transporterDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_vdrDTO;

public interface Broker_masterService {
	
	public SequenceIdDTO getBrokerSequenceId(String prefix,String company,String wtype);
	
	//public Broker_master save(Broker_master broker_master);
	
	public Broker_master save(Broker_master broker_master,MultipartFile files[]) throws IOException;
	
	public List<Broker_master> findAll();
	
	public List<Map<String,Object>> getBrokersFastApi(String company);
	
	public List<Broker_master> findBrokers(String searchtext);
	
	public Broker_master update(Broker_master iMaster,Long id);
	
	public Broker_master deleteBrokerMaster (Broker_master bMaster,Long id);
	
	public List<Broker_masterDTO> brokerMNCList();
	
	public List<Broker_masterDTO> getbrokerMsNameList();
	
	public Broker_masterDTO brokerNameByCode(String code);
	
	public Broker_masterDTO getBrokerMaster(Long id);
	
	public List<Broker_master_addDTO> getBrokerMasterAddr(String code);
	
	public List<Broker_master_add_dtlsDTO> getBrokerMasterAddrDtls(String code);
	
	public List<Broker_master_ptyDTO> getBrokerMasterPty(String code);
	
	public List<Broker_master_transporterDTO> getBrokerMasterTransporter(String code);
	
	public List<Broker_master_vdrDTO> getBrokerMasterVdr(String code);
	
	public List<Map<String, Object>> getBrokerMasterVdrnew(String code);
	
	public Broker_master_statutoryDTO brokerStatutoryRetriveList(String code);
	
	public Broker_master_accountDTO brokerAccountRetriveList(String code);
	
	public List<Broker_master_othDTO> brokerOthPartnerRetriveList(String code);
	
	public List<Broker_master_docDTO> brokerDocRetriveList(String code);
	
	public MessageStatusDTO chkBrokerNameStatus(String bname);
	
	public StatusDTO chkBrokerMasterCodeStatus(String code);
	
	public List<Broker_master_vdrDTO> bro_supp_updation(String brokercode);
	
	public StatusDTO checkBrokerMasterUsage(String code);
	
	public Broker_master accountpostingBrokerMaster(long id);
	
	public Broker_master accountpostingUndoBrokerMaster(long id);
	
	public List<Map<String, Object>> brokerNameListFast();
	
	
}
