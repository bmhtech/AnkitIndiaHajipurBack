package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Exporttotally.Tallyrequest_CustomerMaster;
import com.AnkitIndia.Exporttotally.Tallyrequest_Openingbalanceofmaster;
import com.AnkitIndia.Exporttotally.Tallyrequest_Suppliermaster;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Broker_master_add_dtls;
import com.AnkitIndia.jwtauthentication.model.Broker_master_vdr;
import com.AnkitIndia.jwtauthentication.model.Channel_customer_master;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_accont;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address_dtls;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_bill_addr;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_bill_addr_dtls;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_broker;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_delv_to;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_doc;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_shipping_addr_dtls;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_statutory;
import com.AnkitIndia.jwtauthentication.model.Employee_master;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_accont;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_addr_dtls;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_statutory;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Channel_customer_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.City_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_accontRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_addressRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_address_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_bill_addrRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_bill_addr_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_brokerRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_delv_toRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_docRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_shipping_addr_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_statutoryRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_groupRepository;
import com.AnkitIndia.jwtauthentication.repository.District_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Post_office_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
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
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_add_dtlsDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;

@Service
public class Cust_bussiness_partnerService_Imp implements Cust_bussiness_partnerService{
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	Cust_groupRepository cust_groupRepository;
	
	@Autowired
	Cust_bussiness_partner_address_dtlsRepository custAddrDtlsRepository;
	
	@Autowired
	Channel_customer_masterRepository channel_customer_masterRepository;
	
	@Autowired
	Cust_bussiness_partner_addressRepository cust_bussiness_partner_addressRepository;
	
	@Autowired
	Cust_bussiness_partner_bill_addrRepository cust_bussiness_partner_bill_addrRepository;
	
	@Autowired
	Cust_bussiness_partner_accontRepository cust_bussiness_partner_accontRepository;
	 
	@Autowired
	Cust_bussiness_partner_statutoryRepository cust_bussiness_partner_statutoryRepository;
	
	@Autowired
	Cust_bussiness_partner_address_dtlsRepository cust_bussiness_partner_address_dtlsRepository;
	
	@Autowired
	Cust_bussiness_partner_delv_toRepository cust_bussiness_partner_delv_toRepository;
	
	@Autowired
	Broker_masterRepository broker_masterRepository;

	@Autowired
	Cust_bussiness_partner_brokerRepository cust_bussiness_partner_brokerRepository;
	
	@Autowired
	Cust_bussiness_partner_bill_addr_dtlsRepository cust_bussiness_partner_bill_addr_dtlsRepository;
	
	@Autowired
	Cust_bussiness_partner_docRepository cust_bussiness_partner_docRepository;
	
	@Autowired
	Cust_bussiness_partner_shipping_addr_dtlsRepository cust_bussiness_partner_shipping_addr_dtlsRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	City_masterRepository city_masterRepository;
	
	@Autowired
	District_masterRepository district_masterRepository;
	
	@Autowired
	State_masterRepository state_masterRepository;
	
	@Autowired
	Post_office_masterRepository post_office_masterRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	public SequenceIdDTO getCustSequenceId(String prefix,String company,String wtype) {
			Long slno=0L;String fprefix="",type="";
			String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
			
			if(wtype.compareTo("Customer For Material")==0 || wtype.compareTo("CUSTOMER FOR MATERIAL")==0) {type="CM";}
			else if(wtype.compareTo("Customer For Services")==0 || wtype.compareTo("CUSTOMER FOR SERVICES")==0) {type="CS";}
			else if(wtype.compareTo("Customer For Both")==0) {type="CB";}
			else {type="";}
			fprefix=prefix+"/"+code+"/"+type+"/";
			String gen_sno=cust_bussiness_partnerRepository.getCustSequenceId(fprefix,company,wtype);
			
			if(gen_sno != null ) {
				slno=Long.parseLong(gen_sno);
			}
			
			String gen_slno=UniqueIDTransaction.uniqueId5(fprefix,slno);
			
			Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
			
			SequenceIdDTO genCode = new ModelMapper().map(gen_slno,listType);
			
			genCode.setSequenceid(gen_slno);
			
			return genCode;
		}
	
	@Transactional
	public Cust_bussiness_partner save(Cust_bussiness_partner cPartner) 
	{
			LocalDateTime ldt = LocalDateTime.now();
			
			long slno=0;String prefix="CBP";
			if(cust_bussiness_partnerRepository.countId(prefix).isPresent()) {
				slno=Long.parseLong(cust_bussiness_partnerRepository.countId(prefix).get());
			}
			String gen_sno=UniqueID.uniqueid(prefix,slno);
			
			if(Utility.isNullOrEmpty(cPartner.getGroup_type())) {
				cPartner.setGroup_name(cust_groupRepository.getCustParentGroup(cPartner.getGroup_type()).getGrp_name());
			}else {cPartner.setGroup_name("None");}
			
			/*Start checking transaction no for unique */
			long nslno=0;String tprefix=cPartner.getCp_code(),type="";
			if(cPartner.getCp_type().compareTo("Customer For Material")==0) {type="CM";}
			else if(cPartner.getCp_type().compareTo("Customer For Services")==0) {type="CS";}
			else if(cPartner.getCp_type().compareTo("Customer For Both")==0) {type="CB";}
			else {type="";}
			String gen_snonew=cust_bussiness_partnerRepository.getCustSequenceId(tprefix.substring(0,10),cPartner.getCompany_id(),cPartner.getCp_type());
			if(gen_snonew != null ) {
				nslno=Long.parseLong(gen_snonew);
			}
			String gen_tslno=UniqueIDTransaction.uniqueId5(tprefix.substring(0,10),nslno);
			cPartner.setCp_code(gen_tslno);
			/*End checking transaction no for unique */
			
			cPartner.setCp_Id(gen_sno);
			cPartner.setModified_type("INSERTED");
			cPartner.setInserted_by(userRepository.getUserDetails(cPartner.getUsername()).getName());
			cPartner.setInserted_on(ldt);
			cPartner.setUpdated_by("NA");
			cPartner.setUpdated_on(ldt);
			cPartner.setDeleted_by("NA");
			cPartner.setDeleted_on(ldt);
			
			Set<Cust_bussiness_partner_accont> cBPA=new HashSet<Cust_bussiness_partner_accont>();
			cBPA.add(cPartner.getCust_bussiness_partner_accont());
			for(Cust_bussiness_partner_accont bb1:cBPA) 
			{
				bb1.setCp_Id(gen_sno);
				bb1.setCompany_id(cPartner.getCompany_id());
				bb1.setFin_year(cPartner.getFin_year());
				bb1.setModified_type("INSERTED");
				bb1.setInserted_by(cPartner.getInserted_by());
				bb1.setInserted_on(cPartner.getInserted_on());
				bb1.setUpdated_by("NA");
				bb1.setUpdated_on(ldt);
				bb1.setDeleted_by("NA");
				bb1.setDeleted_on(ldt);
			}
			if(!cBPA.isEmpty())	{
				cPartner.setCust_bussiness_partner_accont(cBPA.iterator().next());
			}
			
			Set<Cust_bussiness_partner_address> cBPA1=new HashSet<Cust_bussiness_partner_address>();
			cBPA1.add(cPartner.getCust_bussiness_partner_address());
			for(Cust_bussiness_partner_address cAddress:cBPA1) 
			{
				if(Utility.isNullOrEmpty(cAddress.getState_code())) {
					cAddress.setState(state_masterRepository.getState(cAddress.getState_code()).getState_name());
				}else {cAddress.setState("None");}
				
				if(Utility.isNullOrEmpty(cAddress.getDist_code())) {
					cAddress.setDistrict(district_masterRepository.getDistrictDtls(cAddress.getDist_code()).getDist_name());
				}else {cAddress.setDistrict("None");}
			
				
				if(Utility.isNullOrEmpty(cAddress.getPostid())) {
				
				}else {cAddress.setPost_office("None");}
				
				cAddress.setCity(cAddress.getCity_code());
				cAddress.setCp_Id(gen_sno);
				cAddress.setCompany_id(cPartner.getCompany_id());
				cAddress.setFin_year(cPartner.getFin_year());
				cAddress.setModified_type("INSERTED");
				cAddress.setInserted_by(cPartner.getInserted_by());
				cAddress.setInserted_on(cPartner.getInserted_on());
				cAddress.setUpdated_by("NA");
				cAddress.setUpdated_on(ldt);
				cAddress.setDeleted_by("NA");
				cAddress.setDeleted_on(ldt);
			}
			if(!cBPA1.isEmpty()) {
				cPartner.setCust_bussiness_partner_address(cBPA1.iterator().next());
			}
			
			Set<Cust_bussiness_partner_bill_addr> cBPA2=new HashSet<Cust_bussiness_partner_bill_addr>();
			cBPA2.add(cPartner.getCust_bussiness_partner_bill_addr());
			for(Cust_bussiness_partner_bill_addr bb3:cBPA2) 
			{
				if(Utility.isNullOrEmpty(bb3.getState_code())) {
					bb3.setState(state_masterRepository.getState(bb3.getState_code()).getState_name());
				}else {bb3.setState("None");}
				
				if(Utility.isNullOrEmpty(bb3.getDist_code())) {
					bb3.setDistrict(district_masterRepository.getDistrictDtls(bb3.getDist_code()).getDist_name());
				}else {bb3.setDistrict("None");}
				
				
				
				if(Utility.isNullOrEmpty(bb3.getPostid())) {
				
				}else {bb3.setPost_office("None");}
				
				bb3.setCity(bb3.getCity_code());
				bb3.setCp_Id(gen_sno);
				bb3.setCompany_id(cPartner.getCompany_id());
				bb3.setFin_year(cPartner.getFin_year());
				bb3.setModified_type("INSERTED");
				bb3.setInserted_by(cPartner.getInserted_by());
				bb3.setInserted_on(cPartner.getInserted_on());
				bb3.setUpdated_by("NA");
				bb3.setUpdated_on(ldt);
				bb3.setDeleted_by("NA");
				bb3.setDeleted_on(ldt);
			}
			if(!cBPA2.isEmpty()) {
				cPartner.setCust_bussiness_partner_bill_addr(cBPA2.iterator().next());
			}
			
			Set<Cust_bussiness_partner_statutory> cBPA3=new HashSet<Cust_bussiness_partner_statutory>();
			cBPA3.add(cPartner.getCust_bussiness_partner_statutory());
			for(Cust_bussiness_partner_statutory bb4:cBPA3) 
			{
				bb4.setCp_Id(gen_sno);
				bb4.setCompany_id(cPartner.getCompany_id());
				bb4.setFin_year(cPartner.getFin_year());
				bb4.setModified_type("INSERTED");
				bb4.setInserted_by(cPartner.getInserted_by());
				bb4.setInserted_on(cPartner.getInserted_on());
				bb4.setUpdated_by("NA");
				bb4.setUpdated_on(ldt);
				bb4.setDeleted_by("NA");
				bb4.setDeleted_on(ldt);
			}
			if(!cBPA3.isEmpty()) {
				cPartner.setCust_bussiness_partner_statutory(cBPA3.iterator().next());
			}
			
			Set<Cust_bussiness_partner_broker> cBPA4=new HashSet<Cust_bussiness_partner_broker>();
			cBPA4.addAll(cPartner.getCust_bussiness_partner_broker());
			for(Cust_bussiness_partner_broker bb5:cBPA4) 
			{
				bb5.setCp_Id(gen_sno);
				if(Utility.isNullOrEmpty(bb5.getVen_code_name())) {
					bb5.setVen_name(broker_masterRepository.brokerNameByCode(bb5.getVen_code_name()).getName());
				}else {bb5.setVen_name("None");}
				bb5.setCompany_id(cPartner.getCompany_id());
				bb5.setFin_year(cPartner.getFin_year());
				bb5.setModified_type("INSERTED");
				bb5.setInserted_by(cPartner.getInserted_by());
				bb5.setInserted_on(cPartner.getInserted_on());
				bb5.setUpdated_by("NA");
				bb5.setUpdated_on(ldt);
				bb5.setDeleted_by("NA");
				bb5.setDeleted_on(ldt);
				
				
				Broker_master_vdr brokercust=new Broker_master_vdr();
				
				brokercust.setVdr_code_name(bb5.getCp_Id());
				brokercust.setBased_on(bb5.getBased_on());
				brokercust.setBasis(bb5.getBasis());
				brokercust.setBrokerage_acc(bb5.getBrokerage_acc());
				brokercust.setCompany_id(bb5.getCompany_id());
				brokercust.setDeleted_by("NA");
				brokercust.setDeleted_on(ldt);
				//brokercust.setEff_date(bb5.getEff_date());
				brokercust.setFin_year(cPartner.getFin_year());
				brokercust.setInserted_by(cPartner.getInserted_by());
				brokercust.setInserted_on(cPartner.getInserted_on());
				brokercust.setModified_type("INSERTED");
				brokercust.setRate(bb5.getRate());
				brokercust.setRemarks(bb5.getRemarks());
				//brokercust.setSl_no(bb5.getSrl_no());
				brokercust.setTds_acc(bb5.getTds_acc());
				brokercust.setTds_rate(bb5.getTds_rate());
				brokercust.setUpdated_by(cPartner.getUpdated_by());
				brokercust.setUpdated_on(cPartner.getUpdated_on());
				
				brokercust.setBroker_Id(bb5.getVen_code_name());
				brokercust.setVdr_name(cPartner.getCp_name());
				
			    bb5.setBroker_master_vdr(brokercust);
				
				
				
			}
			cPartner.setCust_bussiness_partner_broker(cBPA4);
			
			Set<Cust_bussiness_partner_delv_to> cBPA5=new HashSet<Cust_bussiness_partner_delv_to>();
			cBPA5.addAll(cPartner.getCust_bussiness_partner_delv_to());
			for(Cust_bussiness_partner_delv_to bb6:cBPA5) 
			{
				if(Utility.isStringNullOrEmpty(bb6.getTransport_own())) {
					bb6.setTransport_own("No");bb6.setTransporters("");
				}
				bb6.setCp_Id(gen_sno);
				bb6.setCompany_id(cPartner.getCompany_id());
				bb6.setFin_year(cPartner.getFin_year());
				bb6.setModified_type("INSERTED");
				bb6.setInserted_by(cPartner.getInserted_by());
				bb6.setInserted_on(cPartner.getInserted_on());
				bb6.setUpdated_by("NA");
				bb6.setUpdated_on(ldt);
				bb6.setDeleted_by("NA");
				bb6.setDeleted_on(ldt);
			}
			cPartner.setCust_bussiness_partner_delv_to(cBPA5);
			
			Set<Cust_bussiness_partner_address_dtls> cBPA6=new HashSet<Cust_bussiness_partner_address_dtls>();
			cBPA6.addAll(cPartner.getCust_bussiness_partner_addr_dtls());
			for(Cust_bussiness_partner_address_dtls bb7:cBPA6) 
			{
				bb7.setCp_Id(gen_sno);
				bb7.setCompany_id(cPartner.getCompany_id());
				bb7.setFin_year(cPartner.getFin_year());
				bb7.setModified_type("INSERTED");
				bb7.setInserted_by(cPartner.getInserted_by());
				bb7.setInserted_on(cPartner.getInserted_on());
				bb7.setUpdated_by("NA");
				bb7.setUpdated_on(ldt);
				bb7.setDeleted_by("NA");
				bb7.setDeleted_on(ldt);
			}
			cPartner.setCust_bussiness_partner_addr_dtls(cBPA6);
			
			Set<Cust_bussiness_partner_bill_addr_dtls> cBPA7=new HashSet<Cust_bussiness_partner_bill_addr_dtls>();
			cBPA7.addAll(cPartner.getCust_bussiness_partner_bill_addr_dtls());
			for(Cust_bussiness_partner_bill_addr_dtls bb8:cBPA7) 
			{
				bb8.setCp_Id(gen_sno);
				bb8.setCompany_id(cPartner.getCompany_id());
				bb8.setFin_year(cPartner.getFin_year());
				bb8.setModified_type("INSERTED");
				bb8.setInserted_by(cPartner.getInserted_by());
				bb8.setInserted_on(cPartner.getInserted_on());
				bb8.setUpdated_by("NA");
				bb8.setUpdated_on(ldt);
				bb8.setDeleted_by("NA");
				bb8.setDeleted_on(ldt);
			}
			cPartner.setCust_bussiness_partner_bill_addr_dtls(cBPA7);
			
			Set<Cust_bussiness_partner_shipping_addr_dtls> cSA=new HashSet<Cust_bussiness_partner_shipping_addr_dtls>();
			cSA.addAll(cPartner.getCust_bussiness_partner_shipping_addr_dtls());
			for(Cust_bussiness_partner_shipping_addr_dtls sa:cSA) 
			{
				sa.setCp_Id(gen_sno);
				//System.out.println("DIST SAVE :: "+sa.getDist_code());
				if(Utility.isNullOrEmpty(sa.getShipping_name())) 
				{
					sa.setParty_name(cust_bussiness_partnerRepository.getCustomerName(sa.getShipping_name()).getCp_name());
				}
				else 
				{
					sa.setParty_name("None");
				}
				sa.setCompany_id(cPartner.getCompany_id());
				sa.setFin_year(cPartner.getFin_year());
				sa.setModified_type("INSERTED");
				sa.setInserted_by(cPartner.getInserted_by());
				sa.setInserted_on(cPartner.getInserted_on());
				sa.setUpdated_by("NA");
				sa.setUpdated_on(ldt);
				sa.setDeleted_by("NA");
				sa.setDeleted_on(ldt);
			}
			cPartner.setCust_bussiness_partner_shipping_addr_dtls(cSA);
			
			Set<Cust_bussiness_partner_doc> cBPA8=new HashSet<Cust_bussiness_partner_doc>();
			cBPA8.addAll(cPartner.getCust_bussiness_partner_docs());
			for(Cust_bussiness_partner_doc bb9:cBPA8) 
			{
				bb9.setCp_Id(gen_sno);
				bb9.setCompany_id(cPartner.getCompany_id());
				bb9.setFin_year(cPartner.getFin_year());
				bb9.setModified_type("INSERTED");
				bb9.setInserted_by(cPartner.getInserted_by());
				bb9.setInserted_on(cPartner.getInserted_on());
				bb9.setUpdated_by("NA");
				bb9.setUpdated_on(ldt);
				bb9.setDeleted_by("NA");
				bb9.setDeleted_on(ldt);
			}
			cPartner.setCust_bussiness_partner_docs(cBPA8);					

			return cust_bussiness_partnerRepository.save(cPartner);
		}
	
	@Transactional
	public String updateCustTransporters(String custid,String transid)
	{
		String result="Transporters Updated Successfully !!!";
		String[] arrOfStr1=null;
		String[] arrOfStr2=transid.split(",");
		String transporters="";
		Cust_bussiness_partner_delv_to delv_to=cust_bussiness_partner_delv_toRepository.getCustTransporters(custid);
		LinkedHashSet<String> all=new LinkedHashSet<String>();
		
		if(delv_to.getTransport_own().compareTo("Yes")==0) {
			arrOfStr1=delv_to.getTransporters().split(",");
			for(int i=0;i<arrOfStr1.length;i++)
			{
				all.add(arrOfStr1[i]);
			}
		}
		for(int j=0;j<arrOfStr2.length;j++)
		{
			all.add(arrOfStr2[j]);
		}
		ArrayList<String> trans=new ArrayList<String>(all);
		
		for(int j=0;j<trans.size();j++)
		{
			transporters +=trans.get(j) + ",";
		}
		int x=cust_bussiness_partner_delv_toRepository.updateCustTransporters(custid,transporters.substring(0,(transporters.length()-1)));
		
		return result;
	}
	
	@Transactional
	public Cust_bussiness_partner_delv_to updateCustomerTransporters(String custid,String transid)
	{
		String result="Transporters Updated Successfully !!!";
		String[] arrOfStr1=null;
		String[] arrOfStr2=transid.split(",");
		String transporters="";
		System.out.println("custid:: "+custid);
		Cust_bussiness_partner_delv_to delv_to=cust_bussiness_partner_delv_toRepository.getCustTransporters(custid);
		LinkedHashSet<String> all=new LinkedHashSet<String>();
		
		if(delv_to.getTransport_own().compareTo("Yes")==0) {
			arrOfStr1=delv_to.getTransporters().split(",");
			for(int i=0;i<arrOfStr1.length;i++)
			{
				all.add(arrOfStr1[i]);
			}
		}
		for(int j=0;j<arrOfStr2.length;j++)
		{
			all.add(arrOfStr2[j]);
		}
		ArrayList<String> trans=new ArrayList<String>(all);
		
		for(int j=0;j<trans.size();j++)
		{
			transporters +=trans.get(j) + ",";
		}
		int x=cust_bussiness_partner_delv_toRepository.updateCustTransporters(custid,transporters.substring(0,(transporters.length()-1)));
		
		return delv_to;
	}

	public List<Cust_bussiness_partner> findAll(){
		List<Cust_bussiness_partner> custList=new ArrayList<Cust_bussiness_partner>();
		custList.addAll(cust_bussiness_partnerRepository.findAll());
		
		List<Cust_bussiness_partner> allItem = custList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Cust_bussiness_partner::getCp_Id).reversed())
				  .collect(Collectors.toList());
		
		return allItem;
	}
	
	public List<Map<String,Object>> getCustomerBussinessPartnerFastApi(String company)
	{
		List<Map<String,Object>> customerlist=new ArrayList<Map<String,Object>>();
		
		customerlist.addAll(cust_bussiness_partnerRepository.findallnewcustomerfast(company));
		
		return customerlist; 
	}
	
	public List<Cust_bussiness_partner> findCustomers(String searchtext){
		List<Cust_bussiness_partner> custList=new ArrayList<Cust_bussiness_partner>();
		custList.addAll(cust_bussiness_partnerRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Cust_bussiness_partner> allData = custList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Cust_bussiness_partner::getCp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Cust_bussiness_partner> allData = custList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") &&
							  (c.getCp_code()+c.getCp_name()+c.getCp_type()+c.getAlt_name()+c.getGroup_type()+c.getGroup_name()+c.getSub_group_type()+c.getTrans_currency()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Cust_bussiness_partner::getCp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	public Cust_bussiness_partner findOne(long id)
	{
		Optional<Cust_bussiness_partner> op=this.cust_bussiness_partnerRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Cust_bussiness_partner update(Cust_bussiness_partner iMaster,Long id)
	{
		Optional<Cust_bussiness_partner> op = cust_bussiness_partnerRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(iMaster.getGroup_type())) {
			iMaster.setGroup_name(cust_groupRepository.getCustParentGroup(iMaster.getGroup_type()).getGrp_name());
		}else {iMaster.setGroup_name("None");}
		
		iMaster.setCp_Id(op.get().getCp_Id());
		iMaster.setModified_type("INSERTED");
		iMaster.setInserted_by(op.get().getInserted_by());
		iMaster.setInserted_on(op.get().getInserted_on());
		iMaster.setUpdated_by(userRepository.getUserDetails(iMaster.getUsername()).getName());
		iMaster.setUpdated_on(ldt);
		iMaster.setDeleted_by("NA");
		iMaster.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			iMaster.setId(id);
		}
		
		cust_bussiness_partner_accontRepository.updateCust_bussiness_partner_accont(iMaster.getCp_Id(),"UPDATED");
		
		Set<Cust_bussiness_partner_accont> cBPA=new HashSet<Cust_bussiness_partner_accont>();
		cBPA.add(iMaster.getCust_bussiness_partner_accont());
		for(Cust_bussiness_partner_accont bb1:cBPA) 
		{
			bb1.setCp_Id(iMaster.getCp_Id());
			bb1.setCompany_id(iMaster.getCompany_id());
			bb1.setFin_year(iMaster.getFin_year());
			bb1.setModified_type("INSERTED");
			bb1.setInserted_by(iMaster.getInserted_by());
			bb1.setInserted_on(iMaster.getInserted_on());
			bb1.setUpdated_by("NA");
			bb1.setUpdated_on(ldt);
			bb1.setDeleted_by("NA");
			bb1.setDeleted_on(ldt);
		}
		if(!cBPA.isEmpty())	{
			iMaster.setCust_bussiness_partner_accont(cBPA.iterator().next());
		}
		
		cust_bussiness_partner_addressRepository.cust_bussiness_partner_addressupdate(iMaster.getCp_Id(),"UPDATED");
		
		Set<Cust_bussiness_partner_address> cBPA1=new HashSet<Cust_bussiness_partner_address>();
		cBPA1.add(iMaster.getCust_bussiness_partner_address());
		for(Cust_bussiness_partner_address cAddress:cBPA1) 
		{
			if(Utility.isNullOrEmpty(cAddress.getState_code())) {
				cAddress.setState(state_masterRepository.getState(cAddress.getState_code()).getState_name());
			}else {cAddress.setState("None");}
			
			if(Utility.isNullOrEmpty(cAddress.getDist_code())) {
				cAddress.setDistrict(district_masterRepository.getDistrictDtls(cAddress.getDist_code()).getDist_name());
			}else {cAddress.setDistrict("None");}

			if(Utility.isNullOrEmpty(cAddress.getCity_code())) {
				cAddress.setCity(cAddress.getCity_code());
			}else {cAddress.setCity("None");}
			
			
			
			if(Utility.isNullOrEmpty(cAddress.getPostid())) {
				cAddress.setPost_office(cAddress.getPostid());
			}else {cAddress.setPost_office("None");}
			
			cAddress.setCp_Id(iMaster.getCp_Id());
			cAddress.setCompany_id(iMaster.getCompany_id());
			cAddress.setFin_year(iMaster.getFin_year());
			cAddress.setModified_type("INSERTED");
			cAddress.setInserted_by(iMaster.getInserted_by());
			cAddress.setInserted_on(iMaster.getInserted_on());
			cAddress.setUpdated_by(iMaster.getUpdated_by());
			cAddress.setUpdated_on(iMaster.getUpdated_on());
			cAddress.setDeleted_by("NA");
			cAddress.setDeleted_on(ldt);
		}
		if(!cBPA1.isEmpty()) {
			iMaster.setCust_bussiness_partner_address(cBPA1.iterator().next());
		}
		
		cust_bussiness_partner_bill_addrRepository.cust_bussiness_partner_bill_addrupdate(iMaster.getCp_Id(),"UPDATED");
		
		Set<Cust_bussiness_partner_bill_addr> cBPA2=new HashSet<Cust_bussiness_partner_bill_addr>();
		cBPA2.add(iMaster.getCust_bussiness_partner_bill_addr());
		for(Cust_bussiness_partner_bill_addr bb3:cBPA2) 
		{
			if(Utility.isNullOrEmpty(bb3.getState_code())) {
				bb3.setState(state_masterRepository.getState(bb3.getState_code()).getState_name());
			}else {bb3.setState("None");}
			
			if(Utility.isNullOrEmpty(bb3.getDist_code())) {
				bb3.setDistrict(district_masterRepository.getDistrictDtls(bb3.getDist_code()).getDist_name());
			}else {bb3.setDistrict("None");}
			
			if(Utility.isNullOrEmpty(bb3.getCity_code())) {
				bb3.setCity(bb3.getCity_code());
			}else {bb3.setCity("None");}
			
			
			
			
			if(Utility.isNullOrEmpty(bb3.getPostid())) {
				bb3.setPost_office(bb3.getPostid());
			}else {bb3.setPost_office("None");}
			
			bb3.setCp_Id(iMaster.getCp_Id());
			bb3.setCompany_id(iMaster.getCompany_id());
			bb3.setFin_year(iMaster.getFin_year());
			bb3.setModified_type("INSERTED");
			bb3.setInserted_by(iMaster.getInserted_by());
			bb3.setInserted_on(iMaster.getInserted_on());
			bb3.setUpdated_by(iMaster.getUpdated_by());
			bb3.setUpdated_on(iMaster.getUpdated_on());
			bb3.setDeleted_by("NA");
			bb3.setDeleted_on(ldt);
		}
		if(!cBPA2.isEmpty()) {
			iMaster.setCust_bussiness_partner_bill_addr(cBPA2.iterator().next());
		}
		
		cust_bussiness_partner_statutoryRepository.cust_bussiness_partner_statutoryupdate(iMaster.getCp_Id(),"UPDATED");
		
		Set<Cust_bussiness_partner_statutory> cBPA3=new HashSet<Cust_bussiness_partner_statutory>();
		cBPA3.add(iMaster.getCust_bussiness_partner_statutory());
		for(Cust_bussiness_partner_statutory bb4:cBPA3) 
		{
			bb4.setCp_Id(iMaster.getCp_Id());
			bb4.setCompany_id(iMaster.getCompany_id());
			bb4.setFin_year(iMaster.getFin_year());
			bb4.setModified_type("INSERTED");
			bb4.setInserted_by(iMaster.getInserted_by());
			bb4.setInserted_on(iMaster.getInserted_on());
			bb4.setUpdated_by(iMaster.getUpdated_by());
			bb4.setUpdated_on(iMaster.getUpdated_on());
			bb4.setDeleted_by("NA");
			bb4.setDeleted_on(ldt);
		}
		if(!cBPA3.isEmpty()) {
			iMaster.setCust_bussiness_partner_statutory(cBPA3.iterator().next());
		}
		
		cust_bussiness_partner_brokerRepository.cust_bussiness_partner_brokerupdate(iMaster.getCp_Id(),"UPDATED");
		
		Set<Cust_bussiness_partner_broker> cBPA4=new HashSet<Cust_bussiness_partner_broker>();
		cBPA4.addAll(iMaster.getCust_bussiness_partner_broker());
		for(Cust_bussiness_partner_broker bb5:cBPA4) 
		{
			bb5.setCp_Id(iMaster.getCp_Id());
			if(Utility.isNullOrEmpty(bb5.getVen_code_name())) {
				bb5.setVen_name(broker_masterRepository.brokerNameByCode(bb5.getVen_code_name()).getName());
			}else {bb5.setVen_name("0");}
			bb5.setCompany_id(iMaster.getCompany_id());
			bb5.setFin_year(iMaster.getFin_year());
			bb5.setModified_type("INSERTED");
			bb5.setInserted_by(iMaster.getInserted_by());
			bb5.setInserted_on(iMaster.getInserted_on());
			bb5.setUpdated_by(iMaster.getUpdated_by());
			bb5.setUpdated_on(iMaster.getUpdated_on());
			bb5.setDeleted_by("NA");
			bb5.setDeleted_on(ldt);
		}
		iMaster.setCust_bussiness_partner_broker(cBPA4);
		
		cust_bussiness_partner_delv_toRepository.cust_bussiness_partner_delv_toupdate(iMaster.getCp_Id(),"UPDATED");
		
		Set<Cust_bussiness_partner_delv_to> cBPA5=new HashSet<Cust_bussiness_partner_delv_to>();
		cBPA5.addAll(iMaster.getCust_bussiness_partner_delv_to());
		for(Cust_bussiness_partner_delv_to bb6:cBPA5) 
		{
			if(Utility.isStringNullOrEmpty(bb6.getTransport_own())) {
				bb6.setTransport_own("No");bb6.setTransporters("");
			}
			bb6.setCp_Id(iMaster.getCp_Id());
			bb6.setCompany_id(iMaster.getCompany_id());
			bb6.setFin_year(iMaster.getFin_year());
			bb6.setModified_type("INSERTED");
			bb6.setInserted_by(iMaster.getInserted_by());
			bb6.setInserted_on(iMaster.getInserted_on());
			bb6.setUpdated_by(iMaster.getUpdated_by());
			bb6.setUpdated_on(iMaster.getUpdated_on());
			bb6.setDeleted_by("NA");
			bb6.setDeleted_on(ldt);
		}
		iMaster.setCust_bussiness_partner_delv_to(cBPA5);
		
		cust_bussiness_partner_address_dtlsRepository.cust_bussiness_partner_address_dtlsupdate(iMaster.getCp_Id(),"UPDATED");
		
		Set<Cust_bussiness_partner_address_dtls> cBPA6=new HashSet<Cust_bussiness_partner_address_dtls>();
		cBPA6.addAll(iMaster.getCust_bussiness_partner_addr_dtls());
		for(Cust_bussiness_partner_address_dtls bb7:cBPA6) 
		{
			bb7.setCp_Id(iMaster.getCp_Id());
			bb7.setCompany_id(iMaster.getCompany_id());
			bb7.setFin_year(iMaster.getFin_year());
			bb7.setModified_type("INSERTED");
			bb7.setInserted_by(iMaster.getInserted_by());
			bb7.setInserted_on(iMaster.getInserted_on());
			bb7.setUpdated_by(iMaster.getUpdated_by());
			bb7.setUpdated_on(iMaster.getUpdated_on());
			bb7.setDeleted_by("NA");
			bb7.setDeleted_on(ldt);
		}
		iMaster.setCust_bussiness_partner_addr_dtls(cBPA6);
		
		cust_bussiness_partner_bill_addr_dtlsRepository.cust_bussiness_partner_bill_addr_dtlsupdate(iMaster.getCp_Id(),"UPDATED");
		
		Set<Cust_bussiness_partner_bill_addr_dtls> cBPA7=new HashSet<Cust_bussiness_partner_bill_addr_dtls>();
		cBPA7.addAll(iMaster.getCust_bussiness_partner_bill_addr_dtls());
		for(Cust_bussiness_partner_bill_addr_dtls bb8:cBPA7) 
		{
			bb8.setCp_Id(iMaster.getCp_Id());
			bb8.setCompany_id(iMaster.getCompany_id());
			bb8.setFin_year(iMaster.getFin_year());
			bb8.setModified_type("INSERTED");
			bb8.setInserted_by(iMaster.getInserted_by());
			bb8.setInserted_on(iMaster.getInserted_on());
			bb8.setUpdated_by(iMaster.getUpdated_by());
			bb8.setUpdated_on(iMaster.getUpdated_on());
			bb8.setDeleted_by("NA");
			bb8.setDeleted_on(ldt);
		}
			
		iMaster.setCust_bussiness_partner_bill_addr_dtls(cBPA7);
		
		cust_bussiness_partner_shipping_addr_dtlsRepository.cust_bussiness_partner_shipping_addr_dtlsupdate(iMaster.getCp_Id(),"UPDATED");
		
		Set<Cust_bussiness_partner_shipping_addr_dtls> cSA=new HashSet<Cust_bussiness_partner_shipping_addr_dtls>();
		cSA.addAll(iMaster.getCust_bussiness_partner_shipping_addr_dtls());
		for(Cust_bussiness_partner_shipping_addr_dtls sa:cSA) 
		{
			sa.setCp_Id(iMaster.getCp_Id());
			System.out.println("Dist : "+ sa.getDist_code());
			if(Utility.isNullOrEmpty(sa.getShipping_name())) 
			{
				sa.setParty_name(cust_bussiness_partnerRepository.getCustomerName(sa.getShipping_name()).getCp_name());
			}
			else 
			{
				sa.setParty_name("None");
			}
			sa.setCompany_id(iMaster.getCompany_id());
			sa.setFin_year(iMaster.getFin_year());
			sa.setModified_type("INSERTED");
			sa.setInserted_by(iMaster.getInserted_by());
			sa.setInserted_on(iMaster.getInserted_on());
			sa.setUpdated_by("NA");
			sa.setUpdated_on(ldt);
			sa.setDeleted_by("NA");
			sa.setDeleted_on(ldt);
		}
		iMaster.setCust_bussiness_partner_shipping_addr_dtls(cSA);
		
		cust_bussiness_partner_docRepository.cust_bussiness_partner_docupdate(iMaster.getCp_Id(),"UPDATED");
		
		Set<Cust_bussiness_partner_doc> cBPA8=new HashSet<Cust_bussiness_partner_doc>();
		cBPA8.addAll(iMaster.getCust_bussiness_partner_docs());
		for(Cust_bussiness_partner_doc bb9:cBPA8) 
		{
			bb9.setCp_Id(iMaster.getCp_Id());
			bb9.setCompany_id(iMaster.getCompany_id());
			bb9.setFin_year(iMaster.getFin_year());
			bb9.setModified_type("INSERTED");
			bb9.setInserted_by(iMaster.getInserted_by());
			bb9.setInserted_on(iMaster.getInserted_on());
			bb9.setUpdated_by(iMaster.getUpdated_by());
			bb9.setUpdated_on(iMaster.getUpdated_on());
			bb9.setDeleted_by("NA");
			bb9.setDeleted_on(ldt);
		}
		iMaster.setCust_bussiness_partner_docs(cBPA8);
		
		return cust_bussiness_partnerRepository.save(iMaster);	
	}
	
	@Transactional
	public Cust_bussiness_partner deleteCustomerMaster (Cust_bussiness_partner cust_bp,Long id)
	{
		Optional<Cust_bussiness_partner> op = cust_bussiness_partnerRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Cust_bussiness_partner cust_bussiness_partner = op.get();
		
		cust_bussiness_partner.setCp_Id(op.get().getCp_Id());
		cust_bussiness_partner.setInserted_by(op.get().getInserted_by());
		cust_bussiness_partner.setInserted_on(op.get().getInserted_on());
		cust_bussiness_partner.setUpdated_by("NA");
		cust_bussiness_partner.setUpdated_on(ldt);
		cust_bussiness_partner.setDeleted_by(userRepository.getUserDetails(cust_bp.getUsername()).getName());
		cust_bussiness_partner.setDeleted_on(ldt);
		
		cust_bussiness_partner.setModified_type("DELETED");
		
		cust_bussiness_partner_docRepository.cust_bussiness_partner_docupdate(op.get().getCp_Id(),"DELETED");
		cust_bussiness_partner_bill_addr_dtlsRepository.cust_bussiness_partner_bill_addr_dtlsupdate(op.get().getCp_Id(),"DELETED");
		cust_bussiness_partner_delv_toRepository.cust_bussiness_partner_delv_toupdate(op.get().getCp_Id(),"DELETED");
		cust_bussiness_partner_brokerRepository.cust_bussiness_partner_brokerupdate(op.get().getCp_Id(),"DELETED");
		cust_bussiness_partner_statutoryRepository.cust_bussiness_partner_statutoryupdate(op.get().getCp_Id(),"DELETED");
		cust_bussiness_partner_bill_addrRepository.cust_bussiness_partner_bill_addrupdate(op.get().getCp_Id(),"DELETED");
		cust_bussiness_partner_addressRepository.cust_bussiness_partner_addressupdate(op.get().getCp_Id(),"DELETED");
		cust_bussiness_partner_accontRepository.updateCust_bussiness_partner_accont(op.get().getCp_Id(),"DELETED");
		cust_bussiness_partner_shipping_addr_dtlsRepository.cust_bussiness_partner_shipping_addr_dtlsupdate(op.get().getCp_Id(),"DELETED");
		
		if(op.isPresent()) {
			cust_bussiness_partner.setId(id);
		}
		return cust_bussiness_partnerRepository.save(cust_bussiness_partner);
	}	
	
	public List<Cust_bussiness_partnerDTOC> customerNameCodeList(String company){
		List<Cust_bussiness_partner> custList=cust_bussiness_partnerRepository.findAll();
		custList.forEach((e->{
			e.setCp_name(e.getCp_name().toUpperCase());
		}));
		List<Cust_bussiness_partner> allItem = custList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED") && c.isCp_active() == true && c.getCompany_id().equals(company))
				  .sorted(Comparator.comparing(Cust_bussiness_partner::getCp_name))
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Cust_bussiness_partnerDTOC>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<Cust_bussiness_partnerDTOC> custNameCodeList = new ModelMapper().map(allItem,listType);
		
		return custNameCodeList;
	}
	
	public List<Cust_bussiness_partnerDTOC> customerList(String company)
	{
		List<Cust_bussiness_partner> custList=new ArrayList<Cust_bussiness_partner>();
		custList.addAll(cust_bussiness_partnerRepository.findallcustomer(company));
		//System.out.println(custList.get(0));
		
		List<Cust_bussiness_partnerDTOC> custNameCodeList=new ArrayList<Cust_bussiness_partnerDTOC>();
		for(int i=0;i<custList.size();i++) 
		{
			Cust_bussiness_partnerDTOC newcust = new Cust_bussiness_partnerDTOC();
			newcust.setCp_Id(custList.get(i).getCp_Id());
			newcust.setCp_name(custList.get(i).getCp_name());
			newcust.setCp_code(custList.get(i).getCp_code());
			
			custNameCodeList.add(newcust);
		}
		
		return custNameCodeList;
	}
	//newcustomerList
	
	public List<Cust_bussiness_partnerDTOC> newcustomerList(String company)
	{
		List<Object[]> cust_list=new ArrayList<Object[]>();
	
		cust_list.addAll(cust_bussiness_partnerRepository.findallnewcustomer(company));
	
		
		 List<Cust_bussiness_partnerDTOC> list = new ArrayList<Cust_bussiness_partnerDTOC>();        
	    for(final Object o : cust_list)
	    {
	        Object[] obj = (Object[]) o;
	        list.add(new Cust_bussiness_partnerDTOC(obj[0].toString(), obj[1].toString(),obj[2].toString()));
	    }
		  
		return list;
	}
	
	public List<Map<String,Object>> newfastcustomerList(String company)
	{
		
		 List<Map<String,Object>> cust_list=cust_bussiness_partnerRepository.findallnewcustomerfast(company);
				 
		return cust_list;
	}
	
	public List<Cust_bussiness_partnerDTOC> customerNameCodeList(){
		List<Cust_bussiness_partner> custList=cust_bussiness_partnerRepository.findAll();
		custList.forEach((e->{
			e.setCp_name(e.getCp_name().toUpperCase());
		}));
		List<Cust_bussiness_partner> allItem = custList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED") && c.isCp_active() == true)
				  .sorted(Comparator.comparing(Cust_bussiness_partner::getCp_name))
				  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Cust_bussiness_partnerDTOC>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<Cust_bussiness_partnerDTOC> custNameCodeList = new ModelMapper().map(allItem,listType);
		
		return custNameCodeList;
	}
	
	public List<Cust_bussiness_partnerDTOC> getCustomers(){
		List<Cust_bussiness_partner> custList=new ArrayList<Cust_bussiness_partner>();
		custList.addAll(cust_bussiness_partnerRepository.findAll());
		
		List<Cust_bussiness_partner> allItem = custList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED") && c.isCp_active() == true)
				  .sorted(Comparator.comparing(Cust_bussiness_partner::getCp_name))
				  .collect(Collectors.toList());
		
		Type listType = new TypeToken<List<Cust_bussiness_partnerDTOC>>() {}.getType();
		List<Cust_bussiness_partnerDTOC> customerList = new ModelMapper().map(allItem,listType);
		
		return customerList;
	}
	
	public List<Cust_bussiness_partnerDTOC> getCustomerThruBU(String bunit){
 		List<Cust_bussiness_partner> custList=new ArrayList<Cust_bussiness_partner>();
		custList.addAll(cust_bussiness_partnerRepository.findAll());
		
		custList.forEach((e)->{
			e.setCp_name(e.getCp_name().toUpperCase());
		});
 		
		List<Cust_bussiness_partner> cPartners = custList
				  .parallelStream()
				  .filter(c -> c.getBusiness_unit().contains(bunit) && c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Cust_bussiness_partner::getCp_name))
				  .collect(Collectors.toList());
			
		Type listType=new TypeToken<List<Cust_bussiness_partnerDTOC>>() {}.getType();
		List<Cust_bussiness_partnerDTOC> customerList=new ModelMapper().map(cPartners,listType);
		
		return customerList;
	}
	
	public StatusDTO checkcustomersaleclosed(String customer_id) 
	{
		StatusDTO res = new StatusDTO();
		if(cust_bussiness_partnerRepository.getCustomer(customer_id).isSaleclosed()) 
		{
			res.setStatus("Yes");
		}
		else 
		{
			res.setStatus("No");
		}
		
		
		return res;
	}

	public Cust_bussiness_partnerDTO getCustomerName(String code){
		Cust_bussiness_partner modelList=cust_bussiness_partnerRepository.getCustomerName(code);
		
		// Create Conversion Type
		Type listType = new TypeToken<Cust_bussiness_partnerDTO>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		Cust_bussiness_partnerDTO customer = new ModelMapper().map(modelList,listType);
		
		return customer;
	}

	public List<Cust_bussiness_partnerDTO> getCustomerByChannel(String channelid){
	
		Channel_customer_master custChannelId=channel_customer_masterRepository.getChannelCustId(channelid);
		String[] arrOfStr=custChannelId.getCustid().split(",");
		List<Cust_bussiness_partner> modelList=new ArrayList<Cust_bussiness_partner>();
		
		for(int i=0;i<arrOfStr.length;i++)
		{
			modelList.addAll(cust_bussiness_partnerRepository.getCustomerDetails(arrOfStr[i]));
		}
		
		Type listType = new TypeToken<List<Cust_bussiness_partnerDTO>>() {}.getType();
		List<Cust_bussiness_partnerDTO> custchannel = new ModelMapper().map(modelList,listType);
		
		return custchannel;
	}
	
	public List<Map<String,Object>> getCustomerByChannelFastApi(String channelid){
		
		Channel_customer_master custChannelId=channel_customer_masterRepository.getChannelCustId(channelid);
		String[] arrOfStr=custChannelId.getCustid().split(",");
		List<Map<String,Object>> modelList=new ArrayList<Map<String,Object>>();
		
		for(int i=0;i<arrOfStr.length;i++)
		{
			modelList.addAll(cust_bussiness_partnerRepository.getCustomerDetailsFast(arrOfStr[i]));
		}
		
		Type listType = new TypeToken<List<Map<String,Object>>>() {}.getType();
		List<Map<String,Object>> custchannel = new ModelMapper().map(modelList,listType);
		
		return custchannel;
	}

	public List<Cust_bussiness_partnerGroupDTO> getCustGroupByChannel(String channelid){
	
		Channel_customer_master custChannelId=channel_customer_masterRepository.getChannelCustId(channelid);
		String[] arrOfStr=custChannelId.getCustid().split(",");
		
		List<Cust_bussiness_partner> modelList=new ArrayList<Cust_bussiness_partner>();
		for(int i=0;i<arrOfStr.length;i++)
		{
			modelList.add(cust_bussiness_partnerRepository.getCustomer(arrOfStr[i]));
		}
		
		Type listType = new TypeToken<List<Cust_bussiness_partnerGroupDTO>>() {}.getType();
		List<Cust_bussiness_partnerGroupDTO> custchannel = new ModelMapper().map(modelList,listType);
		
		List<Cust_bussiness_partnerGroupDTO> listWithoutDuplicates = new ArrayList<>(new HashSet<>(custchannel));
		return listWithoutDuplicates;
	}	

	public List<Cust_bussiness_partnerDTO> getCustomerByGroup(String group){
		
		List<Cust_bussiness_partner> modelList=cust_bussiness_partnerRepository.getCustomerByGroup(group);
		
		Type listType = new TypeToken<List<Cust_bussiness_partnerDTO>>() {}.getType();
		List<Cust_bussiness_partnerDTO> custbygrp = new ModelMapper().map(modelList,listType);
		
		return custbygrp;
	}

	public Cust_bussiness_partner_addressDTO custAddRetriveList(String code)
	 {
		 Cust_bussiness_partner_address modelList=cust_bussiness_partner_addressRepository.custAddRetriveList(code);
		 
		 Type listType = new TypeToken<Cust_bussiness_partner_addressDTO>() {}.getType();
		 Cust_bussiness_partner_addressDTO custAdd= new ModelMapper().map(modelList,listType);
			
		return custAdd;
	 }
 
 	public List<Cust_bussiness_partner_address_dtlsDTO> custAddDtlsRetriveList(String code,String company)
	{
		List<Cust_bussiness_partner_address_dtls> modelList=cust_bussiness_partner_address_dtlsRepository.custAddDtlsRetriveList(code);
		
		modelList.forEach((e->{
			e.setContact_person(e.getContact_person().toUpperCase());
		}));
		
		System.out.println(company+ " service impcustAddDtlsRetriveList:: "+code);
		List<Cust_bussiness_partner_address_dtls> allData = modelList
				  .stream()
				  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
				  .sorted(Comparator.comparing(Cust_bussiness_partner_address_dtls::getContact_person))
				  .collect(Collectors.toList());
			
		Type listType=new TypeToken<List<Cust_bussiness_partner_address_dtlsDTO>>() {}.getType();
		
		List<Cust_bussiness_partner_address_dtlsDTO> custAddDtls=new ModelMapper().map(allData,listType);
		
		return custAddDtls;
	}
 	
 	public List<Cust_bussiness_partner_address_dtlsDTO> custAddDtlsRetriveListnew(String code)
	{
		List<Cust_bussiness_partner_address_dtls> modelList=cust_bussiness_partner_address_dtlsRepository.custAddDtlsRetriveList(code);
		
		Type listType=new TypeToken<List<Cust_bussiness_partner_address_dtlsDTO>>() {}.getType();
		
		List<Cust_bussiness_partner_address_dtlsDTO> custAddDtls=new ModelMapper().map(modelList,listType);
		
		return custAddDtls;
	}
 
 	public Cust_bussiness_partner_address_dtlsDTO custContactByName(String code,String name,String company)
	{
	 	Cust_bussiness_partner_address_dtls modelList=custAddrDtlsRepository.custContactByName(code,name,company);
		
		Type listType=new TypeToken<Cust_bussiness_partner_address_dtlsDTO>() {}.getType();
		
		Cust_bussiness_partner_address_dtlsDTO custContact=new ModelMapper().map(modelList,listType);
		
		return custContact;
	}
 
	public Cust_bussiness_partner_bill_addrDTO custBillAddRetriveList(String code)
	 {
		 String addr2="",addr3="",state="",city="",dist="",pin="";
		 Cust_bussiness_partner_bill_addr modelList=cust_bussiness_partner_bill_addrRepository.custBillAddRetriveList(code);
		 
		 Type listType = new TypeToken<Cust_bussiness_partner_bill_addrDTO>() {}.getType();
		 Cust_bussiness_partner_bill_addrDTO cBillAdd= new ModelMapper().map(modelList,listType);

		// if(Utility.isStringNullOrEmpty(cBillAdd.getAdd2())) {addr2="";}else {addr2=","+cBillAdd.getAdd2();}
		// if(Utility.isStringNullOrEmpty(cBillAdd.getAdd3())) {addr3="";}else {addr3=","+cBillAdd.getAdd3();}
		// if(Utility.isStringNullOrEmpty(cBillAdd.getState())) {state="";}else {state=","+cBillAdd.getState();}
		// if(Utility.isStringNullOrEmpty(cBillAdd.getDistrict())) {dist="";}else {dist=","+cBillAdd.getDistrict();}
		// if(Utility.isStringNullOrEmpty(cBillAdd.getCity())) {city="";}else {city=","+cBillAdd.getCity();}
		// if(cBillAdd.getPincode()!=0 ) {pin=","+cBillAdd.getPincode();}
	//	 if(Utility.isStringNullOrEmpty(String.valueOf(cBillAdd.getPincode()))) {pin="";}else {pin=","+cBillAdd.getPincode();}
		 
			String address="";

			if(Utility.isNullOrEmpty(cBillAdd.getAdd1())) 
			{
				if(cBillAdd.getAdd1().compareToIgnoreCase("")==0) 
				{
					
				}
				else 
				{

					address+=cBillAdd.getAdd1();

				}
			}
			if(Utility.isNullOrEmpty(cBillAdd.getAdd2())) 
			{
				if(cBillAdd.getAdd2().compareToIgnoreCase("")==0) 
				{
					
				}
				else 
				{
					address+=cBillAdd.getAdd3();
				}
			}
			if(Utility.isNullOrEmpty(cBillAdd.getAdd3())) 
			{
				if(cBillAdd.getAdd3().compareToIgnoreCase("")==0) 
				{
					
				}
				else 
				{
					address+=cBillAdd.getAdd3();
				}

			}
		 cBillAdd.setAddress(address);

		 
		 return cBillAdd;
	 }
 
 	public List<Cust_bussiness_partner_bill_addr_dtlsDTO> custBillAddDtlsRetriveList(String code)
	{
		List<Cust_bussiness_partner_bill_addr_dtls> modelList=cust_bussiness_partner_bill_addr_dtlsRepository.custBillAddDtlsRetriveList(code);
		
		Type listType=new TypeToken<List<Cust_bussiness_partner_bill_addr_dtlsDTO>>() {}.getType();
		
		List<Cust_bussiness_partner_bill_addr_dtlsDTO> custBillAddDtls=new ModelMapper().map(modelList,listType);
		
		return custBillAddDtls;
	}
 
 	public List<Cust_bussiness_partner_delv_toDTO> getCustDelvFromList(String code)
	{
		List<Cust_bussiness_partner_delv_to> modelList=cust_bussiness_partner_delv_toRepository.getCustDelvFromList(code);
		
			
		Type listType=new TypeToken<List<Cust_bussiness_partner_delv_toDTO>>() {}.getType();
		
		List<Cust_bussiness_partner_delv_toDTO> custDelv=new ModelMapper().map(modelList,listType);
		
		return custDelv;
	}
 
	public Cust_bussiness_partner_accontDTO custAccountRetriveList(String code,String company)
	 {
		 Cust_bussiness_partner_accont modelList=cust_bussiness_partner_accontRepository.custAccountRetriveList(code,company);
		 
		 Type listType = new TypeToken<Cust_bussiness_partner_accontDTO>() {}.getType();

		 Cust_bussiness_partner_accontDTO custAcc= new ModelMapper().map(modelList,listType);
			
		return custAcc;
	 }
	
	public Cust_bussiness_partner_accontDTO custAccountRetriveList(String code)
	 {
		 Cust_bussiness_partner_accont modelList=cust_bussiness_partner_accontRepository.custAccountRetriveList(code);
		 
		 Type listType = new TypeToken<Cust_bussiness_partner_accontDTO>() {}.getType();

		 Cust_bussiness_partner_accontDTO custAcc= new ModelMapper().map(modelList,listType);
			
		return custAcc;
	 }
 
	public Cust_bussiness_partner_statutoryDTO custStatutoryRetriveList(String code)
	 {
		 Cust_bussiness_partner_statutory modelList=cust_bussiness_partner_statutoryRepository.custStatutoryRetriveList(code);
		 Type listType = new TypeToken<Cust_bussiness_partner_statutoryDTO>() {}.getType();

		 Cust_bussiness_partner_statutoryDTO custStatutory= new ModelMapper().map(modelList,listType);
			
		return custStatutory;
	 }
 
 	public List<Cust_bussiness_partner_docDTO> custDocRetriveList(String code)
	{
		List<Cust_bussiness_partner_doc> modelList=cust_bussiness_partner_docRepository.custDocRetriveList(code);
		
		Type listType=new TypeToken<List<Cust_bussiness_partner_docDTO>>() {}.getType();
		
		List<Cust_bussiness_partner_docDTO> custDoc=new ModelMapper().map(modelList,listType);
		
		return custDoc;
	}
 
 	public List<Cust_bussiness_partner_brokerDTO> custBrokerRetriveList(String code)
	{
		List<Cust_bussiness_partner_broker> modelList=cust_bussiness_partner_brokerRepository.custBrokerRetriveList(code);
			
		Type listType=new TypeToken<List<Cust_bussiness_partner_brokerDTO>>() {}.getType();
		List<Cust_bussiness_partner_brokerDTO> custBroker=new ModelMapper().map(modelList,listType);
		
		return custBroker;
	}
 	
 	public List<Cust_bussiness_partner_brokerDTO> custBrokerByCode(String bcode){
		
		List<Cust_bussiness_partner_broker> modelList=cust_bussiness_partner_brokerRepository.custBrokerByCode(bcode);
			
		Type listType=new TypeToken<List<Cust_bussiness_partner_brokerDTO>>() {}.getType();
		List<Cust_bussiness_partner_brokerDTO> brokerList=new ModelMapper().map(modelList,listType);
		
		return brokerList;
	}
 	
 	public Cust_bussiness_partner_brokerDTO getCustomerBrokerDtls(String cp_id,String bcode){
		
		Cust_bussiness_partner_broker modelList=cust_bussiness_partner_brokerRepository.getCustomerBrokerDtls(cp_id, bcode);
			
		Type listType=new TypeToken<Cust_bussiness_partner_brokerDTO>() {}.getType();
		
		if(modelList == null) {
		Cust_bussiness_partner_brokerDTO def=new Cust_bussiness_partner_brokerDTO("0","0",0L,"0","0","0","0","0","0",0.0,"0",0.0,"0","0");
		
		Cust_bussiness_partner_brokerDTO brokerDtls = new ModelMapper().map(def,listType);
			return brokerDtls;
		}
		else {
			Cust_bussiness_partner_brokerDTO brokerDtls = new ModelMapper().map(modelList,listType);
			return brokerDtls;
		}
	}
 
 	public Cust_bussiness_partner_addressDTO getCustomerAddress(String custid){

	 	Cust_bussiness_partner_address modelList=cust_bussiness_partner_addressRepository.getCustomerAddress(custid);

		Type listType = new TypeToken<Cust_bussiness_partner_addressDTO>() {}.getType();

		Cust_bussiness_partner_addressDTO custAddress= new ModelMapper().map(modelList,listType);
		
		String address="";
		if(Utility.isNullOrEmpty(modelList.getAdd1())) 
		{
			if(modelList.getAdd1().compareToIgnoreCase("")==0) 
			{
				
			}
			else 
			{
				address+=modelList.getAdd1();
			}
		}
		if(Utility.isNullOrEmpty(modelList.getAdd2())) 
		{
			if(modelList.getAdd2().compareToIgnoreCase("")==0) 
			{
				
			}
			else 
			{
				address+=modelList.getAdd3();
			}
		}
		if(Utility.isNullOrEmpty(modelList.getAdd3())) 
		{
			if(modelList.getAdd3().compareToIgnoreCase("")==0) 
			{
				
			}
			else 
			{
				address+=modelList.getAdd3();
			}
		}
		custAddress.setAddress(address);
		
		//custAddress.setAddress(modelList.getAdd1()+","+modelList.getAdd2()+","+modelList.getAdd3());
		
		return custAddress;
 	}
 
 	public Cust_bussiness_partner_address_dtlsDTO getCustContDetails(String cbp_id,String cp){

	 	Cust_bussiness_partner_address_dtls modelList=cust_bussiness_partner_address_dtlsRepository.getCustContDetails(cbp_id,cp);

		Type listType = new TypeToken<Cust_bussiness_partner_address_dtlsDTO>() {}.getType();

		Cust_bussiness_partner_address_dtlsDTO custDtls= new ModelMapper().map(modelList,listType);
		
		return custDtls;
	}
 	
 	public Cust_bussiness_partner_delv_toDTO getCustDelvFromAdd(String cbp_id,String b_unit_name){
 		String addr="",city="",fax="",email="",mob="";
 		Cust_bussiness_partner_delv_to cDelv_to=new Cust_bussiness_partner_delv_to();
 		if(cbp_id.compareTo("0")!=0 && b_unit_name.compareTo("0") !=0 && b_unit_name.compareTo("Choose an Option")!=0) {
 			System.err.println("Id: "+cbp_id+" unit: "+b_unit_name);
 			cDelv_to=cust_bussiness_partner_delv_toRepository.getCustDelvFromAdd(cbp_id,b_unit_name);
 		}
 		Type listType = new TypeToken<Cust_bussiness_partner_delv_toDTO>() {}.getType();
		Cust_bussiness_partner_delv_toDTO delvDetails= new ModelMapper().map(cDelv_to,listType);
		
		if(Utility.isStringNullOrEmpty(delvDetails.getAddress())) {addr="";}else {addr=","+delvDetails.getAddress();}
		if(Utility.isStringNullOrEmpty(delvDetails.getCity())) {city="";}else {city=","+delvDetails.getCity();}
		if(Utility.isStringNullOrEmpty(delvDetails.getFax())) {fax="";}else {fax=","+delvDetails.getFax();}
		if(Utility.isStringNullOrEmpty(delvDetails.getEmail())) {email="";}else {email=","+delvDetails.getEmail();}
		//if(Utility.isStringNullOrEmpty(delvDetails.getMobile())) {mob="";}else {mob=","+delvDetails.getMobile();}
		if(delvDetails.getMobile()!=0) {mob=","+delvDetails.getMobile();}
		
		delvDetails.setShip_to(delvDetails.getB_unit_name()+addr+city+fax+email+mob);
		
		return delvDetails;
	}
 	
 	public List<Cust_bussiness_partnerDTO> getCustomerThruBUGrp(String bunit,String custgrp){
		List<Cust_bussiness_partner> custList=new ArrayList<Cust_bussiness_partner>();
		custList.addAll(cust_bussiness_partnerRepository.findAll());

		List<Cust_bussiness_partner> cPartners = custList
				  .parallelStream()
				  .filter(c -> c.getBusiness_unit().contains(bunit) && c.getGroup_type().equals(custgrp) && c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Cust_bussiness_partner::getCp_name))
				  .collect(Collectors.toList());
		
		Type listType=new TypeToken<List<Cust_bussiness_partnerDTO>>() {}.getType();
		List<Cust_bussiness_partnerDTO> customerList=new ModelMapper().map(cPartners,listType);
		
		return customerList;
	}
 	
 	public MessageStatusDTO chkCustNameStatus(String cname){
		String result=cust_bussiness_partnerRepository.chkCustNameStatus(cname);
		
		if(result == null) {result="NOTEXIST";}
		else {result="EXIST";}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}
 	
 	public StatusDTO chkCustCodeStatus(String code){
		String result="";
		
		if(cust_bussiness_partnerRepository.chkCustCodeStatus(code).isPresent()) {
			result="EXIST";
		}else {
			result="NOTEXIST";
		}
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
	}
 	
 	public StatusDTO checkCustomerMasterUsage(String code)
 	{
 		String result=cust_bussiness_partnerRepository.checkCustomerMasterUsage(code);
		
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
 	}
 	
 	@Transactional
	public Cust_bussiness_partner accountpostingCustomerMaster(long id) 
	{
		Optional<Cust_bussiness_partner> op=this.cust_bussiness_partnerRepository.findById(id);
		Cust_bussiness_partner_address cust_address=cust_bussiness_partner_addressRepository.custAddRetriveList(op.get().getCp_Id());
		Cust_bussiness_partner_statutory cust_statutary=cust_bussiness_partner_statutoryRepository.custStatutoryRetriveList(op.get().getCp_Id());
		Cust_bussiness_partner_accont cust_accounts=cust_bussiness_partner_accontRepository.custAccountRetriveList(op.get().getCp_Id());
		
		List<Cust_bussiness_partner_address_dtls> contactdetails=cust_bussiness_partner_address_dtlsRepository.custAddDtlsRetriveList(op.get().getCp_Id());
		System.out.println("over here ");
		
		String output,finalresult,registered;
	
		
		Tallyrequest_CustomerMaster tally=new Tallyrequest_CustomerMaster();
		try  
		{
			String address="",gstno="";
			if(Utility.isNullOrEmpty(cust_address.getAdd1())) 
			{
				address = cust_address.getAdd1();
			}
			if(Utility.isNullOrEmpty(cust_address.getAdd2())) 
			{
				address = cust_address.getAdd2();
			}
			if(Utility.isNullOrEmpty(cust_address.getAdd3())) 
			{
				address = cust_address.getAdd3();
			}
			
			System.out.println("over here 12 ");
			
	//address.substring(0, address.length()-1)
			String panno="";
			if(Utility.isNullOrEmpty(cust_statutary.getPan_no()))
			{
				if(cust_statutary.getPan_no().compareToIgnoreCase("null")==0 || cust_statutary.getPan_no().compareToIgnoreCase("")==0 ||cust_statutary.getPan_no().compareToIgnoreCase("XXXXXXXXXX")==0) 
				{
					panno="";
				}
				else
				{
					panno=cust_statutary.getPan_no();
				}
			}
			else
			{
				panno="";
			}
			String registerstatus="";
	
		if(Utility.isNullOrEmpty(cust_statutary.getGst_no())) 
		{
			String gstnno=cust_statutary.getGst_no();
			
			if(gstnno.compareToIgnoreCase("null")==0 || gstnno.compareToIgnoreCase("")==0) 
			{
				//registerstatus="UnRegistered";
				registerstatus="Unregistered/Consumer";
			}
			else {
				gstno = cust_statutary.getGst_no();
				//registerstatus="Registered";
				registerstatus=cust_statutary.getCustomer_type();
			}
			
		}
		else 
		{
			//registerstatus="UnRegistered";
			registerstatus="Unregistered/Consumer";
		}
		String altname;
		if(Utility.isNullOrEmpty(op.get().getAlt_name())) 
		{
			altname=op.get().getAlt_name();
		}
		else 
		{
			altname="";
		}
		if(address.compareToIgnoreCase("")!=0) 
		{
			//address=address.substring(0, address.length()-1);
		}
		if(address.contains("&"))
		{
			address=address.replaceAll("&","&amp;");
		}
		
		
		if(address.compareToIgnoreCase("")!=0) 
		{
			address=address+"  DIST: "+ cust_address.getDistrict();
		}
		else 
		{
			address=" DIST: "+cust_address.getDistrict();
		}
		
		String pinno;
		if(Utility.isNullOrEmpty(String.valueOf(cust_address.getPincode()))) 
		{
			pinno=String.valueOf(cust_address.getPincode());
			System.out.println("here 1 ");
			if(pinno.compareToIgnoreCase("null")==0 || pinno.compareToIgnoreCase("")==0)
			{
				pinno="";
			}
			System.out.println("here 1 ");
		}
		else 
		{
			pinno="";
		}
		String statename=toTitleCase(cust_address.getState());
		System.out.println("State :: "+statename);
		String bankname=cust_accounts.getBankname();
		//System.out.println("over here 12 ");
		//System.out.println(op.get().getCp_name()+"  // "+ altname + " // " + address);
		//System.out.println(address+"  // "+cust_address.getState()+" /// "+gstno);
		//System.out.println(String.valueOf(cust_address.getPincode()) + " /  " + pinno +"  // "+cust_statutary.getPan_no());
		//System.out.println(registerstatus+"  // "+op.get().getCp_type()+"  // "+String.valueOf(contactdetails.get(0).getMobile()));
		//System.out.println(cust_accounts.getIfsc()+"  // "+cust_accounts.getAcc_no());
		
		//System.out.println(op.get().getBp_name()+"  // "+ altname+"  // "+address.substring(0, address.length()-1)+"  // "+sup_address.getState()+"  // "+String.valueOf(sup_address.getPincode())+"  // "+sup_statutary.getPan_no()+"  // "+registerstatus+"  // "+op.get().getBp_type()+"  // "+String.valueOf(contactdetails.get(0).getMobile())+"  // "+sup_accounts.getIfsc()+"  // "+sup_accounts.getAcc_no());
	//	 System.out.println("first :: ");
			//Properties prop = readPropertiesFile("src/main/resources/credentials.properties");
		       
	       
	      //  String Url = "http://192.168.10.100:9000/"; 
	      //  System.out.println("secound :: "+Url);
	       // output=tally.SendToTally(op.get().getCp_name(), altname,address,cust_address.getState(),pinno,cust_statutary.getPan_no(),registerstatus,op.get().getCp_type(),String.valueOf(contactdetails.get(0).getMobile()),gstno,cust_accounts.getIfsc(),cust_accounts.getAcc_no());
		
		String customer_name=op.get().getCp_name();
		if(customer_name.contains("&"))
		{
			customer_name=customer_name.replaceAll("&","&amp;");
		}
		
		String printtoname=op.get().getPrint_to_name();
		
		if(printtoname.contains("&"))
		{
			printtoname=printtoname.replaceAll("&","&amp;");
		}
		
		String type=op.get().getGroup_name();
		if(type.contains("&"))
		{
			type=type.replaceAll("&","&amp;");
		}
		
		
		Tallyrequest_Openingbalanceofmaster opening =new Tallyrequest_Openingbalanceofmaster();
		try  
		{
		   String openingbal=opening.SendToTally(customer_name);
		   System.out.println(openingbal);
		   
		   output=tally.SendToTally(customer_name, altname,address,statename,pinno,panno,registerstatus,type,
				   String.valueOf(contactdetails.get(0).getMobile()),gstno,cust_accounts.getIfsc(),
				   cust_accounts.getAcc_no(),bankname,printtoname,openingbal);
			// System.out.println("3rd :: ");
				System.out.println(output);
				String [] splitoutput = output.split("\\|\\|");
				System.out.println(splitoutput[0] +" / " + splitoutput[1]+"/"+id);
				int exportstatus=0;
				if(Integer.parseInt(splitoutput[1])==1) 
				{
					exportstatus=1;
				}
				if(Integer.parseInt(splitoutput[2])==1) 
				{
					exportstatus=1;
				}
			
				cust_bussiness_partnerRepository.exportuomtally(id,splitoutput[0],exportstatus);
		   
		   
		}
		catch(Exception a)
		{
		
			System.out.println(a);
		}
		
		}
		catch(Exception e)
		{
		
			System.out.println(e);
		}
		
	//	Custom_uom_master custMaster=op.get();
	
		Optional<Cust_bussiness_partner> op1=this.cust_bussiness_partnerRepository.findById(id);
		System.out.println(op.get());
		
		return op1.get();
	}
 	
 	public static String toTitleCase(String input) {

        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c : input.toLowerCase().toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }
            titleCase.append(c);
        }

        return titleCase.toString();
    }
 	
 	public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
           fis = new FileInputStream(fileName);
           prop = new Properties();
           prop.load(fis);
        } catch(FileNotFoundException fnfe) {
           fnfe.printStackTrace();
        } catch(IOException ioe) {
           ioe.printStackTrace();
        } finally {
           fis.close();
        }
        return prop;
     }
	 
 	@Transactional
	public Cust_bussiness_partner accountpostingUndoCustomerMaster(long id) 
	{
		try  
		{
			Optional<Cust_bussiness_partner> op=this.cust_bussiness_partnerRepository.findById(id);
			LocalDateTime ldt = LocalDateTime.now();
			cust_bussiness_partnerRepository.exportuomtallyReverse(id,op.get().getInserted_by(),ldt);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		Optional<Cust_bussiness_partner> op1=this.cust_bussiness_partnerRepository.findById(id);
		System.out.println(op1.get());
		
		return op1.get();
	}
 	
 	public Cust_bussiness_partner_address getCustomerDetails(String custid){

 		Cust_bussiness_partner_address modelList=cust_bussiness_partner_addressRepository.custAddRetriveList(custid);

		return modelList;
	}
 	
 	public List<Cust_bussiness_partner> searchCustomerMasterData(String cust_name,String cust_group,String cust_type,String finyear,String company_name)
	{
		List<Cust_bussiness_partner> searchCustomer =new ArrayList<Cust_bussiness_partner>();
		
		String tablename="cust_bussiness_partner",item_id="cp_id",item_group="group_type",item_category="cp_type",item_type="alt_name",itemtype1="";
		searchCustomer.addAll(cust_bussiness_partnerRepository.getsearchItemdata(tablename,item_id,item_group,item_category,item_type,cust_name,cust_group,cust_type,itemtype1,finyear));
		
		List<Cust_bussiness_partner> allItem = searchCustomer
				  .parallelStream()
				  .filter(c -> c.getCompany_id().equals(company_name) && c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Cust_bussiness_partner::getCp_Id).reversed())
				  .collect(Collectors.toList());
		
		return allItem;
		
	}
 	
 	public List<Map<String,Object>> customerNameCodeListnew(String company)
 	{
 		List<Map<String,Object>> customerList=new ArrayList <>();
 		customerList.addAll(cust_bussiness_partnerRepository.getCustList(company));
 		
 		return customerList;
 	}
 	
 	public List<Map<String,Object>> customerNameActiveBlockAllList(String company)
 	{
 		List<Map<String,Object>> customerList=new ArrayList <>();
 		customerList.addAll(cust_bussiness_partnerRepository.customerNameActiveBlockAllList(company));
 		
 		return customerList;
 	}
 	
 	public List<Map<String,Object>> getCustomerThruBUnewlist(String bunit)
 	{
 		List<Map<String,Object>> cList=new ArrayList <>();
 		cList.addAll(cust_bussiness_partnerRepository.getCustBList(bunit));
 		
 		return cList;
 	}
 	

 	public List<Map<String,Object>> getChannelPartyList(String group_type,String channeltype)
 	{

 		List<Map<String,Object>> pList=new ArrayList <>();
 		
 		if(channeltype.compareToIgnoreCase("Sale")==0)
 		{
 			pList.addAll(cust_bussiness_partnerRepository.getCustChannelList(group_type));
 		}
 		else
 		{
 			pList.addAll(supp_bussiness_partnerRepository.SupplierMasterChannelList(true,group_type));
 		}
 		
 		return pList;
 	
 	}

 	public Map<String, Object> getCustBPStat(String code){
 		System.out.println("bpid::"+code);
 		Map<String, Object> val=cust_bussiness_partnerRepository.getCustBPStat(code);
		return val;
	}

 	public Map<String,Object> getCustomerAddressDetails(String company)
	{
		return cust_bussiness_partner_addressRepository.getCustomerAddressDetails(company);
	}
 	
 	public List<Map<String, Object>> getCustomerMasterReport()
 	{
 		return cust_bussiness_partnerRepository.getCustomerMasterReport();
 	}
 	
 	public List<Map<String, Object>> custShipAddDtlsRetriveList(String cp_id)
 	{
 		return cust_bussiness_partner_shipping_addr_dtlsRepository.custShipAddDtlsRetriveList(cp_id);
 	}
 	
 	public Map<String, Object> getCustomershipdtls(String mainid,String custid)
 	{
 		System.out.println("check cust id:: "+mainid+" /ship id/ "+custid);
 		return cust_bussiness_partner_shipping_addr_dtlsRepository.getCustomershipdtls(mainid,custid);
 	}
 	
}
