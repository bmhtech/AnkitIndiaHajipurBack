package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_delv_to;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerGroupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_accontDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_address_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_bill_addrDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_bill_addr_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_brokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_delv_toDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_statutoryDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Cust_bussiness_partnerService {
	
	public SequenceIdDTO getCustSequenceId(String prefix,String company,String wtype);
	
	public Cust_bussiness_partner save(Cust_bussiness_partner cust_bussiness_partner);
	
	public Cust_bussiness_partner update(Cust_bussiness_partner iMaster,Long id);
	
	public Cust_bussiness_partner deleteCustomerMaster (Cust_bussiness_partner cust_bp,Long id);
	
	public List<Cust_bussiness_partner> findAll();
	
	public List<Map<String,Object>> getCustomerBussinessPartnerFastApi(String company);
	
	public List<Cust_bussiness_partner> findCustomers(String searchtext);
	
	public Cust_bussiness_partner findOne(long id);
	
	public List<Cust_bussiness_partnerDTOC> customerNameCodeList(String company);
	
	public List<Cust_bussiness_partnerDTOC> customerList(String company);
	
	//newcustomerList
	public List<Cust_bussiness_partnerDTOC> newcustomerList(String company);
	
	public List<Map<String,Object>> newfastcustomerList(String company);
	
	
	public List<Cust_bussiness_partnerDTOC> customerNameCodeList();
	
	public List<Cust_bussiness_partnerDTOC> getCustomers();
	
	public List<Cust_bussiness_partnerDTOC> getCustomerThruBU(String bunit);
	
	public StatusDTO checkcustomersaleclosed(String customer_id);
	
	public Cust_bussiness_partnerDTO getCustomerName(String code);
	
	public List<Cust_bussiness_partnerDTO> getCustomerByChannel(String channelid);
	
	public List<Map<String,Object>> getCustomerByChannelFastApi(String channelid);
	
	public List<Cust_bussiness_partnerGroupDTO> getCustGroupByChannel(String channelid);
	
	public List<Cust_bussiness_partnerDTO> getCustomerByGroup(String group);
	
	public Cust_bussiness_partner_addressDTO custAddRetriveList(String code);
	
	public List<Cust_bussiness_partner_address_dtlsDTO> custAddDtlsRetriveList(String code,String company);
	
	public List<Cust_bussiness_partner_address_dtlsDTO> custAddDtlsRetriveListnew(String code);
	
	public Cust_bussiness_partner_address_dtlsDTO custContactByName(String code,String name,String company);
	
	public Cust_bussiness_partner_bill_addrDTO custBillAddRetriveList(String code);
	
	public List<Cust_bussiness_partner_bill_addr_dtlsDTO> custBillAddDtlsRetriveList(String code);
	
	public List<Cust_bussiness_partner_delv_toDTO> getCustDelvFromList(String code);
	
	public Cust_bussiness_partner_accontDTO custAccountRetriveList(String code,String company);
	
	public Cust_bussiness_partner_accontDTO custAccountRetriveList(String code);
	
	public Cust_bussiness_partner_statutoryDTO custStatutoryRetriveList(String code);
	
	public List<Cust_bussiness_partner_brokerDTO> custBrokerRetriveList(String code);
	
	public List<Cust_bussiness_partner_docDTO> custDocRetriveList(String code);
	
	public List<Cust_bussiness_partner_brokerDTO> custBrokerByCode(String bcode);
	
	public Cust_bussiness_partner_brokerDTO getCustomerBrokerDtls(String cp_id,String bcode);
	
	public Cust_bussiness_partner_addressDTO getCustomerAddress(String custid);
	
	public Cust_bussiness_partner_address_dtlsDTO getCustContDetails(String cbp_id,String cp);
	
	public Cust_bussiness_partner_delv_toDTO getCustDelvFromAdd(String cbp_id,String b_unit_name);
	
	public String updateCustTransporters(String custid,String transid);
	
	public Cust_bussiness_partner_delv_to updateCustomerTransporters(String custid,String transid);
	
	public List<Cust_bussiness_partnerDTO> getCustomerThruBUGrp(String bunit,String custgrp);
	
	public MessageStatusDTO chkCustNameStatus(String cname);
	
	public StatusDTO chkCustCodeStatus(String code);
	
	public StatusDTO checkCustomerMasterUsage(String code);
	
	public Cust_bussiness_partner accountpostingCustomerMaster(long id) ;
	
	public Cust_bussiness_partner accountpostingUndoCustomerMaster(long id) ;
	
	public Cust_bussiness_partner_address getCustomerDetails(String custid);
	
	public List<Cust_bussiness_partner> searchCustomerMasterData(String cust_name,String cust_group,String cust_type,String finyear,String company_name);
	
	public List<Map<String,Object>> customerNameCodeListnew(String company);
	
	public List<Map<String,Object>> getCustomerThruBUnewlist(String bunit);
	
	public List<Map<String,Object>> customerNameActiveBlockAllList(String company);

	public List<Map<String,Object>> getChannelPartyList(String group_type,String channeltype);

	public Map<String, Object> getCustBPStat(String bp_id);
	
	public Map<String,Object> getCustomerAddressDetails(String cpid);
	
	public List<Map<String, Object>> getCustomerMasterReport();
	
	public List<Map<String, Object>> custShipAddDtlsRetriveList(String cp_id);
	
}
