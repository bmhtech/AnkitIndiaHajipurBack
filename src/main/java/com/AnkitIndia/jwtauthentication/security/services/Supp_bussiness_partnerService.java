package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partnerDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partner_accontDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partner_bill_addrDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partner_bill_addr_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_or_Customer_bussinessDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_vdrDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_addr_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_brokerDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_delv_fromDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_docDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_statutoryDTO;

public interface Supp_bussiness_partnerService {
	
	public SequenceIdDTO getSuppSequenceId(String prefix,String company,String wtype);
	
	//public Supp_bussiness_partner save(Supp_bussiness_partner supp_bussiness_partner);
	
	public Supp_bussiness_partner save(Supp_bussiness_partner supp_bussiness_partner,MultipartFile files[]) throws IOException;
	
	public Supp_bussiness_partner update(Supp_bussiness_partner iMaster,long id);
	
	public Supp_bussiness_partner deleteSupp_bp(Supp_bussiness_partner supp_bussiness_partner,long id);
	
	public List<Supp_bussiness_partner> findAll();
	
	public List<Map<String,Object>> getSupplierBPartnersFastApi(String company);
	
	public List<Supp_bussiness_partner> findSuppliers(String searchtext);
	
	public Supp_bussiness_partner findOne(long id);
	
	public List<Supp_bussiness_partner_brokerDTO> getSupplierBrokerList(Long bp_id);
	
	public Supp_bussiness_partner_brokerDTO getSupplierBrokerDtls(String sbp_id,String brokercode);
	
	public List<Supp_bussiness_partner_brokerDTO> getSuppBPBroker(String id);
	
	public List<Supp_bussiness_partner_brokerDTO> getSupplierBrokersByCode(String Code);
	
	public List<Supp_bussiness_partner_brokerDTO> getSupplierBrokerDtls(String Code);
	
	public List<Supp_bussiness_partner_brokerDTO> getnewSupplierBrokersDtls(String code,String supp);
	
	public List<Supp_bussiness_partner_statutoryDTO> getSupplierStatutaries(String Code);
	
	public List<Supp_bussiness_partnerDTO> getSupplierMasterNCList(String company);
	
	public List<Map<String, Object>> getSupplierMasterNCListNew(String company);
	
	public List<Map<String, Object>> getSupplierMasterListWithTotalAmt(String company, String fin_year);
	
	public List<Supp_bussiness_partnerDTOC> newsupplierNamesList(String company);
	
	
	public List<Supp_or_Customer_bussinessDTO> supplierorcustomerCodeList(String company);
	
	public  List<Map<String, Object>> supplierorcustomerCodeListNew(String company);
	//Delete
	public List<Supp_bussiness_partnerDTO> getSupplierMasterNCList();
	
	public List<Supp_bussiness_partnerDTO> supplierListByGroup(String group);
	
	public Supp_bussiness_partnerDTO getSupplierName(String Code);
	
	public String getSupplierAddress(String code);
	
	public List<Supp_bussiness_partner_addr_dtlsDTO> getSupplierContact();
	
	public List<Supp_bussiness_partner_addr_dtlsDTO> getSuppContById(String bp_id);
	
	public List<Supp_bussiness_partner_delv_fromDTO> getSuppBUnitName();
	
	public List<Supp_bussiness_partner_delv_fromDTO> getSuppContactNameList(String bp_id);
	
	public Supp_bussiness_partner_delv_fromDTO getSuppBUnitAddr(String bp_id);
	
	public Supp_bussiness_partner_delv_fromDTO getSupplierDelvFromAdd(String sbp_id,String CN);
	
	public Supp_bussiness_partner_addr_dtlsDTO getSupplierContDetails(String sbp_id,String cp);
	
	public Supp_bussiness_partner_addressDTO getSupplierAddr(String bp_id);
	
	public Supp_bussiness_partner_addr_dtlsDTO getSuppphoneByIdName(String bp_id,String CP);
	
	public Supp_bussiness_partner_addressDTO getSuppBPAddr(String bp_id);
	
	public List<Supp_bussiness_partner_bill_addr_dtlsDTO> getSuppBPBillAddrDtls(String bp_id);
	
	public Supp_bussiness_partner_accontDTO getSuppBPAcc(String bp_id);
	
	public Supp_bussiness_partner_statutoryDTO getSuppBPStatuDtls(String bp_id);
	
	public List<Supp_bussiness_partner_docDTO> getSuppBPDoc(String bp_id);
	
	public Supp_bussiness_partner_bill_addrDTO getSuppBPBillAddr(String bp_id);
	
	public Supp_bussiness_partner_statutoryDTO getSupplierStatDtls(String bp_id);
	
	public Supp_bussiness_partner_delv_fromDTO getSuppDelvAddress(String bp_id,String bu_name);
	
	public List<Supp_bussiness_partnerDTO> getSupplierThruBU(String bunit);
	
	public List<Map<String,Object>> getSupplierThruBUNew(String bunit);
	
	public MessageStatusDTO chkSuppNameStatus(String sname);
	
	
	public List<Supp_bussiness_partnerDTO> getSupplierByChannel(String channelid);
			
	public StatusDTO chkSuppBpCodeStatus(String code);
	
	public List<Supp_bussiness_partner_brokerDTO> supp_bro_updation(String suppliercode);
	
	public StatusDTO checkSupplierMasterUsage(String code);
	
	public Supp_bussiness_partner accountpostingSupplierBPartner(long id) ;
	
	public Supp_bussiness_partner accountpostingUndoSupplierBPartner(long id) ;
	
	public List<Supp_bussiness_partner> searchSupplierMasterData(String supp_name,String supp_group,String supp_type,String finyear,String company_name);
	
	public Map<String, Object> getSuppBPStat(String bp_id);
	
	public Map<String, Object> getSupplierAddrFast(String bp_id);
	
	public StatusDTO checkValidGstNo(String gst);
	
	public StatusDTO checkValidPANNo(String pan);
	
	public List<Map<String, Object>> getSupplierMasterReport();
	
}
