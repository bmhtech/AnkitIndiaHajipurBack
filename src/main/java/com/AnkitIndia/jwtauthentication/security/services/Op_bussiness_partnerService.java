package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner;
import com.AnkitIndia.jwtauthentication.responseDTO.Company_licence_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_accontDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_bill_addrDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_brokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_delv_fromDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_statutoryDTO;


public interface Op_bussiness_partnerService {
	public Op_bussiness_partner save(Op_bussiness_partner op_bussiness_partner);
	
	public List<Op_bussiness_partner> findAll();
	
	public Op_bussiness_partner findOne(long id);
	
	public List<Op_bussiness_partnerDTO> getOtherPartnerMNCList();
	
	public Op_bussiness_partner_addressDTO oBPAddressRetriveList(String code);
	
	public Op_bussiness_partner_bill_addrDTO oBPBillAddressRetriveList(String code);
	
	public List<Op_bussiness_partner_delv_fromDTO> oBPDelvFromRetriveList(String cp_id);
	
	public Op_bussiness_partner_accontDTO oBPAccountRetriveList(String code);
	
	public Op_bussiness_partner_statutoryDTO oBPStatutoryRetriveList(String code);
	
	public List<Op_bussiness_partner_brokerDTO> oBPBrokerRetriveList(String cp_id);
}
