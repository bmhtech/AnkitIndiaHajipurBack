package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_tds;
import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_address_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_statutoryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_delv_fromDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Trans_bussiness_partner_accontDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Trans_bussiness_partner_brokerDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Trans_bussiness_partner_vehicle_dtlsDTO;

public interface Trans_bussiness_partnerService {
	
	public SequenceIdDTO getTransSequenceId(String prefix,String company);
	
	//public Trans_bussiness_partner save(Trans_bussiness_partner trans_bussiness_partner);
	public Trans_bussiness_partner save(Trans_bussiness_partner trans_bussiness_partner,MultipartFile files[]) throws IOException;
	
	//public Trans_bussiness_partner update(Trans_bussiness_partner iMaster,Long id);
	public Trans_bussiness_partner update(Trans_bussiness_partner tMaster,MultipartFile files[]);
	
	public Trans_bussiness_partner deleteTransBussinessPartner (Trans_bussiness_partner tbpartner,Long id);
	
	public List<Trans_bussiness_partner> findAll();
	
	public List<Map<String,Object>> getTransporterBussinessPartnerFast();
	
	public List<Trans_bussiness_partner> findTransporters(String searchtext);
	
	public List<Trans_bussiness_partnerDTO> getTransporterMNCList();
	
	public List<Map<String, Object>> getTransporterMNCListFast();
	
	public List<Map<String, Object>> getTransporterListFastbp_Id();
	
	public List<Trans_bussiness_partnerDTO> getTransporterThruCustomer(String custid);
	
	public List<Trans_bussiness_partnerDTO> getTransporterThruSupplier(String suppid);
	
	//public String getSuppliertransport(String suppid);
	public List<Supp_bussiness_partner_delv_fromDTO> getSuppliertransport(String suppid) ;
	
	public List<VehicleMaster> getsalevehiclelist(String transportid);
	
	public List<Trans_bussiness_partnerDTO> getTransporterThruGroup(String tgid);
	
	public Trans_bussiness_partnerDTO getBPNameByCode(String bpCode);
	
	public List<Trans_bussiness_partner_brokerDTO> getTransporterBrokerList(String code);
	
	public List<Trans_bussiness_partner_brokerDTO> getTransporterBrokers(String vcode);
	
	public List<Trans_bussiness_partner_vehicle_dtlsDTO> getTransListByVlno(String vno);
	
	public Trans_bussiness_partner_accontDTO getTransAccount(String bp_Id);
	
	public Trans_bussiness_partner findOne(long id);
	
	public Trans_bussiness_partner_addressDTO getTransBPAddr(String bp_id);
	
	public Trans_bussiness_partner_statutoryDTO getTransBPStatu(String bp_id);
	
	public List<Trans_bussiness_partner_address_dtlsDTO> getTransBPAddrDtls(String bp_id);
	
	public Trans_bussiness_partner_tds getTranstds(String bp_id);
	
	public List<Trans_bussiness_partner_docDTO> getTransBPDocs(String bp_id);
	
	public List<Trans_bussiness_partner_vehicle_dtlsDTO> getTransBPVD(String bp_id);
	
	public MessageStatusDTO chkTransNameStatus(String tname);
	
	public StatusDTO chkTransBpCodeStatus(String code);
	
	public List<Trans_bussiness_partner> transporterownlist(String translist);
	
	public StatusDTO checkTransporterMasterUsage(String code);
	
	public Trans_bussiness_partner accountPostingTransporter(Long id);
	
}
