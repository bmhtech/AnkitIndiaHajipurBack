package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.Exporttotally.Tallyrequest_Openingbalanceofmaster;
import com.AnkitIndia.Exporttotally.Tallyrequest_Suppliermaster;
import com.AnkitIndia.FileUpload.FileUploadUtil;
import com.AnkitIndia.Utility.FileSupplierMasterUtil;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Broker_master_vdr;
import com.AnkitIndia.jwtauthentication.model.Channel_customer_master;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_accont;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_addr_dtls;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_bill_addr;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_bill_addr_dtls;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_broker;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_delv_from;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_doc;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_statutory;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_master_vdrRepository;
import com.AnkitIndia.jwtauthentication.repository.Channel_customer_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.City_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.District_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Post_office_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_accontRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_addr_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_addressRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_bill_addrRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_bill_addr_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_brokerRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_delv_fromRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_docRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_statutoryRepository;
import com.AnkitIndia.jwtauthentication.repository.Supplier_groupRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_brokerDTO;
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

@Service
public class Supp_bussiness_partnerService_Imp implements Supp_bussiness_partnerService{
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	Supp_bussiness_partner_delv_fromRepository supp_bussiness_partner_delv_fromRepository;
	
	@Autowired
	Supp_bussiness_partner_addr_dtlsRepository supp_bussiness_partner_addr_dtlsRepository;
	
	@Autowired
	Supp_bussiness_partner_addressRepository supp_bussiness_partner_addressRepository;
	
	@Autowired
	Supp_bussiness_partner_accontRepository supp_bussiness_partner_accontRepository;
	
	@Autowired
	Supp_bussiness_partner_statutoryRepository supp_bussiness_partner_statutoryRepository;
	
	@Autowired
	Supp_bussiness_partner_bill_addrRepository supp_bussiness_partner_bill_addrRepository;
	
	@Autowired
	Supplier_groupRepository supplier_groupRepository;
	
	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	Supp_bussiness_partner_brokerRepository supp_bussiness_partner_brokerRepository;
	
	@Autowired
	Supp_bussiness_partner_bill_addr_dtlsRepository supp_bussiness_partner_bill_addr_dtlsRepository; 
	
	@Autowired
	Supp_bussiness_partner_docRepository supp_bussiness_partner_docRepository;
	
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
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	
	@Autowired
	Channel_customer_masterRepository channel_customer_masterRepository;
	
	
	@Autowired 
	Broker_master_vdrRepository broker_master_vdrRepository;
	
	public SequenceIdDTO getSuppSequenceId(String prefix,String company,String wtype) {
		Long slno=0L;String fprefix="",type="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		//if(wtype.compareTo("Supplier For Material")==0) {type="SM";}
		if(wtype.compareTo("SUPPLIER FOR MATERIAL")==0 || wtype.compareTo("Supplier For Material")==0) {type="SM";}
		
		//else if(wtype.compareTo("Supplier For Services")==0) {type="SS";}
		else if(wtype.compareTo("Supplier For Services")==0 || wtype.compareTo("SUPPLIER FOR SERVICES")==0) {type="SS";}
		else if(wtype.compareTo("Supplier For Both")==0) {type="SB";}
		else {type="";}
		fprefix=prefix+"/"+code+"/"+type+"/";
		String gen_sno=supp_bussiness_partnerRepository.getSuppSequenceId(fprefix,company,wtype);
		
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
	//public Supp_bussiness_partner save(Supp_bussiness_partner supp_bussiness_partner) {
	public Supp_bussiness_partner save(Supp_bussiness_partner supp_bussiness_partner,MultipartFile files[]) throws IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="SBP";
		if(supp_bussiness_partnerRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(supp_bussiness_partnerRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		supp_bussiness_partner.setBp_Id(gen_sno);
		if(Utility.isNullOrEmpty(supp_bussiness_partner.getGroup_type())) {
			supp_bussiness_partner.setGroup_type_name(supplier_groupRepository.getSuppParentGroup(supp_bussiness_partner.getGroup_type()).getBp_grp_name());
		}else {supp_bussiness_partner.setGroup_type_name("None");}
		
		/*Start checking transaction no for unique */
		long nslno=0;String tprefix=supp_bussiness_partner.getBp_code(),type="";
		if(supp_bussiness_partner.getBp_type().compareTo("Supplier For Material")==0) {type="SM";}
		else if(supp_bussiness_partner.getBp_type().compareTo("Supplier For Services")==0) {type="SS";}
		else if(supp_bussiness_partner.getBp_type().compareTo("Supplier For Both")==0) {type="SB";}
		else {type="";}
		String gen_snonew=supp_bussiness_partnerRepository.getSuppSequenceId(tprefix.substring(0,10),supp_bussiness_partner.getCompany_id(),supp_bussiness_partner.getBp_type());
		if(gen_snonew != null ) {
			nslno=Long.parseLong(gen_snonew);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId5(tprefix.substring(0,10),nslno);
		supp_bussiness_partner.setBp_code(gen_tslno);
		/*End checking transaction no for unique */
		
		supp_bussiness_partner.setModified_type("INSERTED");
		supp_bussiness_partner.setInserted_by(userRepository.getUserDetails(supp_bussiness_partner.getUsername()).getName());
		supp_bussiness_partner.setInserted_on(ldt);
		supp_bussiness_partner.setUpdated_by("NA");
		supp_bussiness_partner.setUpdated_on(ldt);
		supp_bussiness_partner.setDeleted_by("NA");
		supp_bussiness_partner.setDeleted_on(ldt);
		
	
		Set<Supp_bussiness_partner_address> bpAddressSet=new HashSet<Supp_bussiness_partner_address>();
		bpAddressSet.add(supp_bussiness_partner.getSupp_bussiness_partner_address());
		for(Supp_bussiness_partner_address bpadd:bpAddressSet) 
		{
			if(Utility.isNullOrEmpty(bpadd.getState_code())) {
				bpadd.setState(state_masterRepository.getState(bpadd.getState_code()).getState_name());
			}else {bpadd.setState("None");}
			
			if(Utility.isNullOrEmpty(bpadd.getDist_code())) {
				bpadd.setDistrict(district_masterRepository.getDistrictDtls(bpadd.getDist_code()).getDist_name());
			}else {bpadd.setDistrict("None");}
			
			/*if(Utility.isNullOrEmpty(bpadd.getCity_code())) {
				bpadd.setCity(city_masterRepository.getCityDtls(bpadd.getCity_code()).getCity_name());
			}else {bpadd.setCity("None");}
			*/
			if(Utility.isNullOrEmpty(bpadd.getPostid())) {
				//bpadd.setPost_office(post_office_masterRepository.getPincodeThruPO(bpadd.getPostid()).get().getPost_office());
			}else {bpadd.setPost_office("None");}
			
			bpadd.setCity(bpadd.getCity_code());
			bpadd.setBp_Id(gen_sno);
			bpadd.setCompany_id(supp_bussiness_partner.getCompany_id());
			bpadd.setFin_year(supp_bussiness_partner.getFin_year());
			bpadd.setModified_type("INSERTED");
			bpadd.setInserted_by(supp_bussiness_partner.getInserted_by());
			bpadd.setInserted_on(supp_bussiness_partner.getInserted_on());
			bpadd.setUpdated_by("NA");
			bpadd.setUpdated_on(ldt);
			bpadd.setDeleted_by("NA");
			bpadd.setDeleted_on(ldt);
		}
		if(!bpAddressSet.isEmpty())
		{
			supp_bussiness_partner.setSupp_bussiness_partner_address(bpAddressSet.iterator().next());
		}
		
		
		Set<Supp_bussiness_partner_bill_addr> bpBillAddressSet=new HashSet<Supp_bussiness_partner_bill_addr>();
		bpBillAddressSet.add(supp_bussiness_partner.getSupp_bussiness_partner_bill_addr());
		for(Supp_bussiness_partner_bill_addr bpbilladd:bpBillAddressSet) 
		{
			if(Utility.isNullOrEmpty(bpbilladd.getState_code())) {
				bpbilladd.setState(state_masterRepository.getState(bpbilladd.getState_code()).getState_name());
			}else {bpbilladd.setState("None");}
			
			if(Utility.isNullOrEmpty(bpbilladd.getDist_code())) {
				bpbilladd.setDistrict(district_masterRepository.getDistrictDtls(bpbilladd.getDist_code()).getDist_name());
			}else {bpbilladd.setDistrict("None");}
			
			/*if(Utility.isNullOrEmpty(bpbilladd.getCity_code())) {
				bpbilladd.setCity(city_masterRepository.getCityDtls(bpbilladd.getCity_code()).getCity_name());
			}else {bpbilladd.setCity("None");}*/
			
			if(Utility.isNullOrEmpty(bpbilladd.getPostid())) {
				//bpbilladd.setPost_office(post_office_masterRepository.getPincodeThruPO(bpbilladd.getPostid()).get().getPost_office());
			}else {bpbilladd.setPost_office("None");}
			
			bpbilladd.setCity(bpbilladd.getCity_code());
			bpbilladd.setBp_Id(gen_sno);
			bpbilladd.setCompany_id(supp_bussiness_partner.getCompany_id());
			bpbilladd.setFin_year(supp_bussiness_partner.getFin_year());
			bpbilladd.setModified_type("INSERTED");
			bpbilladd.setInserted_by(supp_bussiness_partner.getInserted_by());
			bpbilladd.setInserted_on(supp_bussiness_partner.getInserted_on());
			bpbilladd.setUpdated_by("NA");
			bpbilladd.setUpdated_on(ldt);
			bpbilladd.setDeleted_by("NA");
			bpbilladd.setDeleted_on(ldt);
		}
		if(!bpBillAddressSet.isEmpty())
		{
			supp_bussiness_partner.setSupp_bussiness_partner_bill_addr(bpBillAddressSet.iterator().next());
		}
		
		Set<Supp_bussiness_partner_delv_from> bpDelFromSet=new HashSet<Supp_bussiness_partner_delv_from>();
		bpDelFromSet.addAll(supp_bussiness_partner.getSupp_bussiness_partner_delv_from());
		for(Supp_bussiness_partner_delv_from bpdelvfrom:bpDelFromSet) 
		{
			//System.out.println("transporters: "+bpdelvfrom.getTransporters().length());
			bpdelvfrom.setBp_Id(gen_sno);
			bpdelvfrom.setCompany_id(supp_bussiness_partner.getCompany_id());
			bpdelvfrom.setFin_year(supp_bussiness_partner.getFin_year());
			bpdelvfrom.setModified_type("INSERTED");
			bpdelvfrom.setInserted_by(supp_bussiness_partner.getInserted_by());
			bpdelvfrom.setInserted_on(supp_bussiness_partner.getInserted_on());
			bpdelvfrom.setUpdated_by("NA");
			bpdelvfrom.setUpdated_on(ldt);
			bpdelvfrom.setDeleted_by("NA");
			bpdelvfrom.setDeleted_on(ldt);
		}
		supp_bussiness_partner.setSupp_bussiness_partner_delv_from(bpDelFromSet);

		Set<Supp_bussiness_partner_accont> bpAccountSet=new HashSet<Supp_bussiness_partner_accont>();
		bpAccountSet.add(supp_bussiness_partner.getSupp_bussiness_partner_accont());
		for(Supp_bussiness_partner_accont bpAccount:bpAccountSet) 
		{
			bpAccount.setBp_Id(gen_sno);
			bpAccount.setCompany_id(supp_bussiness_partner.getCompany_id());
			bpAccount.setFin_year(supp_bussiness_partner.getFin_year());
			bpAccount.setModified_type("INSERTED");
			bpAccount.setInserted_by(supp_bussiness_partner.getInserted_by());
			bpAccount.setInserted_on(supp_bussiness_partner.getInserted_on());
			bpAccount.setUpdated_by("NA");
			bpAccount.setUpdated_on(ldt);
			bpAccount.setDeleted_by("NA");
			bpAccount.setDeleted_on(ldt);
		}
		if(!bpAccountSet.isEmpty())
		{
			supp_bussiness_partner.setSupp_bussiness_partner_accont(bpAccountSet.iterator().next());
		}
		
		Set<Supp_bussiness_partner_statutory> bpStatSet=new HashSet<Supp_bussiness_partner_statutory>();
		bpStatSet.add(supp_bussiness_partner.getSupp_bussiness_partner_statutory());
		for(Supp_bussiness_partner_statutory bpStat:bpStatSet) 
		{
			bpStat.setBp_Id(gen_sno);
			bpStat.setCompany_id(supp_bussiness_partner.getCompany_id());
			bpStat.setFin_year(supp_bussiness_partner.getFin_year());
			bpStat.setModified_type("INSERTED");
			bpStat.setInserted_by(supp_bussiness_partner.getInserted_by());
			bpStat.setInserted_on(supp_bussiness_partner.getInserted_on());
			bpStat.setUpdated_by("NA");
			bpStat.setUpdated_on(ldt);
			bpStat.setDeleted_by("NA");
			bpStat.setDeleted_on(ldt);
		}
		if(!bpStatSet.isEmpty())
		{
			supp_bussiness_partner.setSupp_bussiness_partner_statutory(bpStatSet.iterator().next());
		}
		
		Set<Supp_bussiness_partner_broker> bpBrokerSet=new HashSet<Supp_bussiness_partner_broker>();
		bpBrokerSet.addAll(supp_bussiness_partner.getSupp_bussiness_partner_broker());
		for(Supp_bussiness_partner_broker bpBroker:bpBrokerSet) 
		{
			bpBroker.setBp_Id(gen_sno);
			if(Utility.isNullOrEmpty(bpBroker.getVen_code_name())) {
				bpBroker.setVen_name(broker_masterRepository.brokerNameByCode(bpBroker.getVen_code_name()).getName());
			}
			bpBroker.setCompany_id(supp_bussiness_partner.getCompany_id());
			bpBroker.setFin_year(supp_bussiness_partner.getFin_year());
			bpBroker.setModified_type("INSERTED");
			bpBroker.setInserted_by(supp_bussiness_partner.getInserted_by());
			bpBroker.setInserted_on(supp_bussiness_partner.getInserted_on());
			bpBroker.setUpdated_by("NA");
			bpBroker.setUpdated_on(ldt);
			bpBroker.setDeleted_by("NA");
			bpBroker.setDeleted_on(ldt);
			
			
			Broker_master_vdr brokersupp=new Broker_master_vdr();
			
			brokersupp.setVdr_code_name(bpBroker.getBp_Id());
			brokersupp.setBased_on(bpBroker.getBased_on());
			brokersupp.setBasis(bpBroker.getBasis());
			brokersupp.setBrokerage_acc(bpBroker.getBrokerage_acc());
			brokersupp.setCompany_id(bpBroker.getCompany_id());
			brokersupp.setDeleted_by("NA");
			brokersupp.setDeleted_on(ldt);
			//brokersupp.setEff_date(bpBroker.getEff_date());
			brokersupp.setFin_year(supp_bussiness_partner.getFin_year());
			brokersupp.setInserted_by(supp_bussiness_partner.getInserted_by());
			brokersupp.setInserted_on(supp_bussiness_partner.getInserted_on());
			brokersupp.setModified_type("INSERTED");
			brokersupp.setRate(bpBroker.getRate());
			brokersupp.setRemarks(bpBroker.getRemarks());
			//brokersupp.setSl_no(bpBroker.getSrl_no());
			brokersupp.setTds_acc(bpBroker.getTds_acc());
			brokersupp.setTds_rate(bpBroker.getTds_rate());
			brokersupp.setUpdated_by(supp_bussiness_partner.getUpdated_by());
			brokersupp.setUpdated_on(supp_bussiness_partner.getUpdated_on());
			
			brokersupp.setBroker_Id(bpBroker.getVen_code_name());
			brokersupp.setVdr_name(supp_bussiness_partner.getBp_name());
			
		
			bpBroker.setBroker_master_vdr(brokersupp);
			
		}
		supp_bussiness_partner.setSupp_bussiness_partner_broker(bpBrokerSet);
		
		Set<Supp_bussiness_partner_addr_dtls> addrSet=new HashSet<Supp_bussiness_partner_addr_dtls>();
		addrSet.addAll(supp_bussiness_partner.getSupp_bussiness_partner_addr_dtls());
		for(Supp_bussiness_partner_addr_dtls bpAddr:addrSet) 
		{
			bpAddr.setBp_Id(gen_sno);
			bpAddr.setCompany_id(supp_bussiness_partner.getCompany_id());
			bpAddr.setFin_year(supp_bussiness_partner.getFin_year());
			bpAddr.setModified_type("INSERTED");
			bpAddr.setInserted_by(supp_bussiness_partner.getInserted_by());
			bpAddr.setInserted_on(supp_bussiness_partner.getInserted_on());
			bpAddr.setUpdated_by("NA");
			bpAddr.setUpdated_on(ldt);
			bpAddr.setDeleted_by("NA");
			bpAddr.setDeleted_on(ldt);
		}
		supp_bussiness_partner.setSupp_bussiness_partner_addr_dtls(addrSet);
		
		Set<Supp_bussiness_partner_bill_addr_dtls> billAddrSet=new HashSet<Supp_bussiness_partner_bill_addr_dtls>();
		billAddrSet.addAll(supp_bussiness_partner.getSupp_bussiness_partner_bill_addr_dtls());
		for(Supp_bussiness_partner_bill_addr_dtls billAddr:billAddrSet) 
		{
			billAddr.setBp_Id(gen_sno);
			billAddr.setCompany_id(supp_bussiness_partner.getCompany_id());
			billAddr.setFin_year(supp_bussiness_partner.getFin_year());
			billAddr.setModified_type("INSERTED");
			billAddr.setInserted_by(supp_bussiness_partner.getInserted_by());
			billAddr.setInserted_on(supp_bussiness_partner.getInserted_on());
			billAddr.setUpdated_by("NA");
			billAddr.setUpdated_on(ldt);
			billAddr.setDeleted_by("NA");
			billAddr.setDeleted_on(ldt);
		}
		supp_bussiness_partner.setSupp_bussiness_partner_bill_addr_dtls(billAddrSet);
		
		Set<Supp_bussiness_partner_doc> docSet=new HashSet<Supp_bussiness_partner_doc>();
		docSet.addAll(supp_bussiness_partner.getSupp_bussiness_partner_docs());
		int i=0;
		for(Supp_bussiness_partner_doc bpDoc:docSet) 
		{
			bpDoc.setBp_Id(gen_sno);
			
			
			
			System.out.println(" hello files : "+files.length);
			//here start
			
			if(files.length > 0) {
				try {
					System.out.println("files[i] :: "+i+" / "+files[i]);
						//fileUpload(files[i],gen_sno+"_");
					
					bpDoc.setDoc_pdf(FileUploadUtil.fileUpload(files[i],gen_sno+"_",FileSupplierMasterUtil.folderPath));
						
					
				i++;
				}
				catch (IOException e)
				{
					System.out.println(e);
					}
				
			}
			System.out.println("3 :: ");
			
			
			
			
			bpDoc.setCompany_id(supp_bussiness_partner.getCompany_id());
			bpDoc.setFin_year(supp_bussiness_partner.getFin_year());
			bpDoc.setModified_type("INSERTED");
			bpDoc.setInserted_by(supp_bussiness_partner.getInserted_by());
			bpDoc.setInserted_on(supp_bussiness_partner.getInserted_on());
			bpDoc.setUpdated_by("NA");
			bpDoc.setUpdated_on(ldt);
			bpDoc.setDeleted_by("NA");
			bpDoc.setDeleted_on(ldt);
		}
		supp_bussiness_partner.setSupp_bussiness_partner_docs(docSet);
		
		return supp_bussiness_partnerRepository.save(supp_bussiness_partner);
	}
	
	public List<Supp_bussiness_partner> findAll(){
		
		List<Supp_bussiness_partner> suppList=new ArrayList<Supp_bussiness_partner>();
		suppList.addAll(supp_bussiness_partnerRepository.findAll());
		
		suppList.forEach((s)->{
			if(Utility.isNullOrEmpty(s.getGroup_type())) {
				s.setGroup_type(supplier_groupRepository.getSuppParentGroup(s.getGroup_type()).getBp_grp_name());
			}else {s.setGroup_type("None");}
		});
		
		List<Supp_bussiness_partner> allData = suppList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Supp_bussiness_partner::getBp_Id).reversed())
				  .collect(Collectors.toList());
			
		return allData;
	}
	
	public List<Map<String,Object>> getSupplierBPartnersFastApi(String company)
	{
		List<Map<String,Object>> supplierlist=new ArrayList<Map<String,Object>>();
		
		supplierlist.addAll(supp_bussiness_partnerRepository.getSupplierBPartnersFastApi(company));
		
		return supplierlist; 
	}
	
	public List<Supp_bussiness_partner> findSuppliers(String searchtext){
		
		List<Supp_bussiness_partner> suppList=new ArrayList<Supp_bussiness_partner>();
		suppList.addAll(supp_bussiness_partnerRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Supp_bussiness_partner> allData = suppList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Supp_bussiness_partner::getBp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Supp_bussiness_partner> allData = suppList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") &&
							  (c.getBp_name()+c.getBp_code()+c.getBp_type()+c.getTrans_currency()+c.getConstitution()+c.getSsi_app()+c.getGroup_type_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Supp_bussiness_partner::getBp_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
	@Transactional
	public Supp_bussiness_partner update(Supp_bussiness_partner iMaster,long id)
	{
		Optional<Supp_bussiness_partner> op = supp_bussiness_partnerRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(iMaster.getGroup_type())) {
			iMaster.setGroup_type_name(supplier_groupRepository.getSuppParentGroup(iMaster.getGroup_type()).getBp_grp_name());
		}else {iMaster.setGroup_type_name("None");}
		
		iMaster.setBp_Id(op.get().getBp_Id());
		iMaster.setModified_type("INSERTED");
		iMaster.setInserted_by(op.get().getInserted_by());
		iMaster.setInserted_on(op.get().getInserted_on());
		iMaster.setUpdated_by(userRepository.getUserDetails(iMaster.getUsername()).getName());
		iMaster.setUpdated_on(ldt);
		iMaster.setDeleted_by("NA");
		iMaster.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			iMaster.setId(id);
		}
		
		int astatus=supp_bussiness_partner_addressRepository.supp_bussiness_partner_addressUpdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Supp_bussiness_partner_address> bpAddressSet=new HashSet<Supp_bussiness_partner_address>();
		bpAddressSet.add(iMaster.getSupp_bussiness_partner_address());
		for(Supp_bussiness_partner_address bpadd:bpAddressSet) 
		{
			if(Utility.isNullOrEmpty(bpadd.getState_code())) {
				bpadd.setState(state_masterRepository.getState(bpadd.getState_code()).getState_name());
			}else {bpadd.setState("None");}
			
			if(Utility.isNullOrEmpty(bpadd.getDist_code())) {
				bpadd.setDistrict(district_masterRepository.getDistrictDtls(bpadd.getDist_code()).getDist_name());
			}else {bpadd.setDistrict("None");}
			
			/*if(Utility.isNullOrEmpty(bpadd.getCity_code())) {
				bpadd.setCity(city_masterRepository.getCityDtls(bpadd.getCity_code()).getCity_name());
			}else {bpadd.setCity("None");}*/
			
			//if(Utility.isNullOrEmpty(bpadd.getPostid())) {
			//	bpadd.setPost_office(post_office_masterRepository.getPincodeThruPO(bpadd.getPostid()).get().getPost_office());
			//}else {bpadd.setPost_office("None");}
			
			bpadd.setCity(bpadd.getCity_code());
			bpadd.setBp_Id(iMaster.getBp_Id());
			bpadd.setCompany_id(iMaster.getCompany_id());
			bpadd.setFin_year(iMaster.getFin_year());
			bpadd.setModified_type("INSERTED");
			bpadd.setInserted_by(iMaster.getInserted_by());
			bpadd.setInserted_on(iMaster.getInserted_on());
			bpadd.setUpdated_by(iMaster.getUpdated_by());
			bpadd.setUpdated_on(iMaster.getUpdated_on());
			bpadd.setDeleted_by("NA");
			bpadd.setDeleted_on(ldt);
		}
		if(!bpAddressSet.isEmpty())
		{
			iMaster.setSupp_bussiness_partner_address(bpAddressSet.iterator().next());
		}
		
		supp_bussiness_partner_bill_addrRepository.supp_bussiness_partner_bill_addrUpdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Supp_bussiness_partner_bill_addr> bpBillAddressSet=new HashSet<Supp_bussiness_partner_bill_addr>();
		bpBillAddressSet.add(iMaster.getSupp_bussiness_partner_bill_addr());
		for(Supp_bussiness_partner_bill_addr bpbilladd:bpBillAddressSet) 
		{
			if(Utility.isNullOrEmpty(bpbilladd.getState_code())) {
				bpbilladd.setState(state_masterRepository.getState(bpbilladd.getState_code()).getState_name());
			}else {bpbilladd.setState("None");}
			
			if(Utility.isNullOrEmpty(bpbilladd.getDist_code())) {
				bpbilladd.setDistrict(district_masterRepository.getDistrictDtls(bpbilladd.getDist_code()).getDist_name());
			}else {bpbilladd.setDistrict("None");}
			
			/*if(Utility.isNullOrEmpty(bpbilladd.getCity_code())) {
				bpbilladd.setCity(city_masterRepository.getCityDtls(bpbilladd.getCity_code()).getCity_name());
			}else {bpbilladd.setCity("None");}*/
			
		//	if(Utility.isNullOrEmpty(bpbilladd.getPostid())) {
			//	bpbilladd.setPost_office(post_office_masterRepository.getPincodeThruPO(bpbilladd.getPostid()).get().getPost_office());
			//}else {bpbilladd.setPost_office("None");}
			
			bpbilladd.setCity(bpbilladd.getCity_code());
			bpbilladd.setBp_Id(iMaster.getBp_Id());
			bpbilladd.setCompany_id(iMaster.getCompany_id());
			bpbilladd.setFin_year(iMaster.getFin_year());
			bpbilladd.setModified_type("INSERTED");
			bpbilladd.setInserted_by(iMaster.getInserted_by());
			bpbilladd.setInserted_on(iMaster.getInserted_on());
			bpbilladd.setUpdated_by(iMaster.getUpdated_by());
			bpbilladd.setUpdated_on(iMaster.getUpdated_on());
			bpbilladd.setDeleted_by("NA");
			bpbilladd.setDeleted_on(ldt);
		}
		if(!bpBillAddressSet.isEmpty())
		{
			iMaster.setSupp_bussiness_partner_bill_addr(bpBillAddressSet.iterator().next());
		}
		
		supp_bussiness_partner_delv_fromRepository.supp_bussiness_partner_delv_fromUpdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Supp_bussiness_partner_delv_from> bpDelFromSet=new HashSet<Supp_bussiness_partner_delv_from>();
		bpDelFromSet.addAll(iMaster.getSupp_bussiness_partner_delv_from());
		for(Supp_bussiness_partner_delv_from bpdelvfrom:bpDelFromSet) 
		{
			bpdelvfrom.setBp_Id(iMaster.getBp_Id());
			bpdelvfrom.setCompany_id(iMaster.getCompany_id());
			bpdelvfrom.setFin_year(iMaster.getFin_year());
			bpdelvfrom.setModified_type("INSERTED");
			bpdelvfrom.setInserted_by(iMaster.getInserted_by());
			bpdelvfrom.setInserted_on(iMaster.getInserted_on());
			bpdelvfrom.setUpdated_by(iMaster.getUpdated_by());
			bpdelvfrom.setUpdated_on(iMaster.getUpdated_on());
			bpdelvfrom.setDeleted_by("NA");
			bpdelvfrom.setDeleted_on(ldt);
		}
		iMaster.setSupp_bussiness_partner_delv_from(bpDelFromSet);
		
		supp_bussiness_partner_accontRepository.supp_bussiness_partner_accontUpdate(iMaster.getBp_Id(),"UPDATED");
		
		
		Set<Supp_bussiness_partner_accont> bpAccountSet=new HashSet<Supp_bussiness_partner_accont>();
		bpAccountSet.add(iMaster.getSupp_bussiness_partner_accont());
		for(Supp_bussiness_partner_accont bpAccount:bpAccountSet) 
		{
			bpAccount.setBp_Id(iMaster.getBp_Id());
			bpAccount.setCompany_id(iMaster.getCompany_id());
			bpAccount.setFin_year(iMaster.getFin_year());
			bpAccount.setModified_type("INSERTED");
			bpAccount.setInserted_by(iMaster.getInserted_by());
			bpAccount.setInserted_on(iMaster.getInserted_on());
			bpAccount.setUpdated_by(iMaster.getUpdated_by());
			bpAccount.setUpdated_on(iMaster.getUpdated_on());
			bpAccount.setDeleted_by("NA");
			bpAccount.setDeleted_on(ldt);
		}
		if(!bpAccountSet.isEmpty())
		{
			iMaster.setSupp_bussiness_partner_accont(bpAccountSet.iterator().next());
		}
		
		supp_bussiness_partner_statutoryRepository.supp_bussiness_partner_statutoryUpdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Supp_bussiness_partner_statutory> bpStatSet=new HashSet<Supp_bussiness_partner_statutory>();
		bpStatSet.add(iMaster.getSupp_bussiness_partner_statutory());
		for(Supp_bussiness_partner_statutory bpStat:bpStatSet) 
		{
			bpStat.setBp_Id(iMaster.getBp_Id());
			bpStat.setCompany_id(iMaster.getCompany_id());
			bpStat.setFin_year(iMaster.getFin_year());
			bpStat.setModified_type("INSERTED");
			bpStat.setInserted_by(iMaster.getInserted_by());
			bpStat.setInserted_on(iMaster.getInserted_on());
			bpStat.setUpdated_by(iMaster.getUpdated_by());
			bpStat.setUpdated_on(iMaster.getUpdated_on());
			bpStat.setDeleted_by("NA");
			bpStat.setDeleted_on(ldt);
		}
		if(!bpStatSet.isEmpty())
		{
			iMaster.setSupp_bussiness_partner_statutory(bpStatSet.iterator().next());
		}
		
		supp_bussiness_partner_brokerRepository.supp_bussiness_partner_brokerUpdate(iMaster.getBp_Id(),"UPDATED");
		broker_masterRepository.suppliertobrokerupdate(op.get().getBp_Id(),"UPDATED");
		
		Set<Supp_bussiness_partner_broker> bpBrokerSet=new HashSet<Supp_bussiness_partner_broker>();
		bpBrokerSet.addAll(iMaster.getSupp_bussiness_partner_broker());
		for(Supp_bussiness_partner_broker bpBroker:bpBrokerSet) 
		{
			bpBroker.setBp_Id(iMaster.getBp_Id());
			if(Utility.isNullOrEmpty(bpBroker.getVen_code_name())) {
				bpBroker.setVen_name(broker_masterRepository.brokerNameByCode(bpBroker.getVen_code_name()).getName());
			}
			
			bpBroker.setCompany_id(iMaster.getCompany_id());
			bpBroker.setFin_year(iMaster.getFin_year());
			bpBroker.setModified_type("INSERTED");
			bpBroker.setInserted_by(iMaster.getInserted_by());
			bpBroker.setInserted_on(iMaster.getInserted_on());
			bpBroker.setUpdated_by(iMaster.getUpdated_by());
			bpBroker.setUpdated_on(iMaster.getUpdated_on());
			bpBroker.setDeleted_by("NA");
			bpBroker.setDeleted_on(ldt);
			
			
           Broker_master_vdr brokersupp=new Broker_master_vdr();
			
			brokersupp.setVdr_code_name(bpBroker.getBp_Id());
			brokersupp.setBased_on(bpBroker.getBased_on());
			brokersupp.setBasis(bpBroker.getBasis());
			brokersupp.setBrokerage_acc(bpBroker.getBrokerage_acc());
			brokersupp.setCompany_id(bpBroker.getCompany_id());
			brokersupp.setDeleted_by("NA");
			brokersupp.setDeleted_on(ldt);
			//brokersupp.setEff_date(bpBroker.getEff_date());
			brokersupp.setFin_year(iMaster.getFin_year());
			brokersupp.setInserted_by(iMaster.getInserted_by());
			brokersupp.setInserted_on(iMaster.getInserted_on());
			brokersupp.setModified_type("INSERTED");
			brokersupp.setRate(bpBroker.getRate());
			brokersupp.setRemarks(bpBroker.getRemarks());
			//brokersupp.setSl_no(bpBroker.getSrl_no());
			brokersupp.setTds_acc(bpBroker.getTds_acc());
			brokersupp.setTds_rate(bpBroker.getTds_rate());
			brokersupp.setUpdated_by(iMaster.getUpdated_by());
			brokersupp.setUpdated_on(iMaster.getUpdated_on());
			
			brokersupp.setBroker_Id(bpBroker.getVen_code_name());
			brokersupp.setVdr_name(iMaster.getBp_name());
			
		
			bpBroker.setBroker_master_vdr(brokersupp);
			
		}
		
		iMaster.setSupp_bussiness_partner_broker(bpBrokerSet);
		
		supp_bussiness_partner_addr_dtlsRepository.supp_bussiness_partner_addr_dtlsUpdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Supp_bussiness_partner_addr_dtls> addrSet=new HashSet<Supp_bussiness_partner_addr_dtls>();
		addrSet.addAll(iMaster.getSupp_bussiness_partner_addr_dtls());
		for(Supp_bussiness_partner_addr_dtls bpAddr:addrSet) 
		{
			bpAddr.setBp_Id(iMaster.getBp_Id());
			bpAddr.setCompany_id(iMaster.getCompany_id());
			bpAddr.setFin_year(iMaster.getFin_year());
			bpAddr.setModified_type("INSERTED");
			bpAddr.setInserted_by(iMaster.getInserted_by());
			bpAddr.setInserted_on(iMaster.getInserted_on());
			bpAddr.setUpdated_by(iMaster.getUpdated_by());
			bpAddr.setUpdated_on(iMaster.getUpdated_on());
			bpAddr.setDeleted_by("NA");
			bpAddr.setDeleted_on(ldt);
		}
		iMaster.setSupp_bussiness_partner_addr_dtls(addrSet);
		
		supp_bussiness_partner_bill_addr_dtlsRepository.supp_bussiness_partner_bill_addr_dtlsUpdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Supp_bussiness_partner_bill_addr_dtls> billAddrSet=new HashSet<Supp_bussiness_partner_bill_addr_dtls>();
		billAddrSet.addAll(iMaster.getSupp_bussiness_partner_bill_addr_dtls());
		for(Supp_bussiness_partner_bill_addr_dtls billAddr:billAddrSet) 
		{
			billAddr.setBp_Id(iMaster.getBp_Id());
			billAddr.setCompany_id(iMaster.getCompany_id());
			billAddr.setFin_year(iMaster.getFin_year());
			billAddr.setModified_type("INSERTED");
			billAddr.setInserted_by(iMaster.getInserted_by());
			billAddr.setInserted_on(iMaster.getInserted_on());
			billAddr.setUpdated_by(iMaster.getUpdated_by());
			billAddr.setUpdated_on(iMaster.getUpdated_on());
			billAddr.setDeleted_by("NA");
			billAddr.setDeleted_on(ldt);
		}
		iMaster.setSupp_bussiness_partner_bill_addr_dtls(billAddrSet);
		
		supp_bussiness_partner_docRepository.supp_bussiness_partner_docUpdate(iMaster.getBp_Id(),"UPDATED");
		
		Set<Supp_bussiness_partner_doc> docSet=new HashSet<Supp_bussiness_partner_doc>();
		docSet.addAll(iMaster.getSupp_bussiness_partner_docs());
		for(Supp_bussiness_partner_doc bpDoc:docSet) 
		{
			bpDoc.setBp_Id(iMaster.getBp_Id());
			bpDoc.setCompany_id(iMaster.getCompany_id());
			bpDoc.setFin_year(iMaster.getFin_year());
			bpDoc.setModified_type("INSERTED");
			bpDoc.setInserted_by(iMaster.getInserted_by());
			bpDoc.setInserted_on(iMaster.getInserted_on());
			bpDoc.setUpdated_by(iMaster.getUpdated_by());
			bpDoc.setUpdated_on(iMaster.getUpdated_on());
			bpDoc.setDeleted_by("NA");
			bpDoc.setDeleted_on(ldt);
		}
		iMaster.setSupp_bussiness_partner_docs(docSet);
		
		return supp_bussiness_partnerRepository.save(iMaster);
		
	}
	
	@Transactional
	public Supp_bussiness_partner deleteSupp_bp(Supp_bussiness_partner supp_bussiness_partner,long id)
	{
		Optional<Supp_bussiness_partner> op = supp_bussiness_partnerRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Supp_bussiness_partner sbpMaster=op.get();
		
		sbpMaster.setBp_code(op.get().getBp_code());
		sbpMaster.setInserted_by(op.get().getInserted_by());
		sbpMaster.setInserted_on(op.get().getInserted_on());
		sbpMaster.setUpdated_by(op.get().getUpdated_by());
		sbpMaster.setUpdated_on(op.get().getUpdated_on());
		sbpMaster.setDeleted_by(userRepository.getUserDetails(supp_bussiness_partner.getUsername()).getName());
		sbpMaster.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			sbpMaster.setId(id);
		}
		
		sbpMaster.setModified_type("DELETED");
		
		supp_bussiness_partner_docRepository.supp_bussiness_partner_docUpdate(op.get().getBp_Id(),"DELETED");
		supp_bussiness_partner_bill_addr_dtlsRepository.supp_bussiness_partner_bill_addr_dtlsUpdate(op.get().getBp_Id(),"DELETED");
		supp_bussiness_partner_addr_dtlsRepository.supp_bussiness_partner_addr_dtlsUpdate(op.get().getBp_Id(),"DELETED");
		supp_bussiness_partner_brokerRepository.supp_bussiness_partner_brokerUpdate(op.get().getBp_Id(),"DELETED");
		supp_bussiness_partner_statutoryRepository.supp_bussiness_partner_statutoryUpdate(op.get().getBp_Id(),"DELETED");
		supp_bussiness_partner_accontRepository.supp_bussiness_partner_accontUpdate(op.get().getBp_Id(),"DELETED");
		supp_bussiness_partner_delv_fromRepository.supp_bussiness_partner_delv_fromUpdate(op.get().getBp_Id(),"DELETED");
		supp_bussiness_partner_bill_addrRepository.supp_bussiness_partner_bill_addrUpdate(op.get().getBp_Id(),"DELETED");
		supp_bussiness_partner_addressRepository.supp_bussiness_partner_addressUpdate(op.get().getBp_Id(),"DELETED");
		
		broker_master_vdrRepository.broker_master_vdrupdateSUPNAME(op.get().getBp_Id());
		
		return supp_bussiness_partnerRepository.save(sbpMaster);
	}
	
	public Supp_bussiness_partner findOne(long id)
	{
		Optional<Supp_bussiness_partner> sbp=this.supp_bussiness_partnerRepository.findById(id);
		return sbp.get();
	}
	
	public List<Supp_bussiness_partner_brokerDTO> getSupplierBrokerList(Long id){
		
		String code=supp_bussiness_partnerRepository.SupplierNameById(id).getBp_Id();
		
		List<Supp_bussiness_partner_broker> modelList=new ArrayList<Supp_bussiness_partner_broker>();
		
		modelList.addAll(supp_bussiness_partner_brokerRepository.getSupplierBrokerList(code));
			
		Type listType=new TypeToken<List<Supp_bussiness_partner_brokerDTO>>() {}.getType();
		
		List<Supp_bussiness_partner_brokerDTO> brokerList=new ModelMapper().map(modelList,listType);
		
		return brokerList;
	}
	
	public List<Supp_bussiness_partner_brokerDTO> getSuppBPBroker(String bp_id){
		
		List<Supp_bussiness_partner_broker> modelList=new ArrayList<Supp_bussiness_partner_broker>();
		
		modelList.addAll(supp_bussiness_partner_brokerRepository.getSupplierBrokerList(bp_id));
			
		Type listType=new TypeToken<List<Supp_bussiness_partner_brokerDTO>>() {}.getType();
		
		List<Supp_bussiness_partner_brokerDTO> brokerList=new ModelMapper().map(modelList,listType);
		
		return brokerList;
	}
	
	public List<Supp_bussiness_partner_brokerDTO> getSupplierBrokersByCode(String code){
		
		List<Supp_bussiness_partner_broker> modelList=supp_bussiness_partner_brokerRepository.getSupplierBrokerList(code);
		if(modelList.get(0).getVen_code_name().compareToIgnoreCase("0")==0)
		{
			
		}
		else {
			modelList.forEach((e)->{
				
					e.setVen_name(e.getVen_name().toUpperCase());
				
				
			});
		}
		
 		
		List<Supp_bussiness_partner_broker> addAll = modelList
				  .parallelStream()
				  .filter(c ->c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Supp_bussiness_partner_broker::getVen_name))
				  .collect(Collectors.toList());
			
		Type listType=new TypeToken<List<Supp_bussiness_partner_brokerDTO>>() {}.getType();
		
		List<Supp_bussiness_partner_brokerDTO> brokerList=new ModelMapper().map(addAll,listType);
		
		return brokerList;
	}
	
	public List<Supp_bussiness_partner_brokerDTO> getSupplierBrokerDtls(String code){
		System.out.println(("code::"+code));
		List<Supp_bussiness_partner_broker> modelList=supp_bussiness_partner_brokerRepository.getSupplierBrokerDtls(code);
			
		Type listType=new TypeToken<List<Supp_bussiness_partner_brokerDTO>>() {}.getType();
		List<Supp_bussiness_partner_brokerDTO> brokerList=new ModelMapper().map(modelList,listType);
		
		return brokerList;
	}
	
	public List<Supp_bussiness_partner_brokerDTO> getnewSupplierBrokersDtls(String code,String supp){
		
		List<Supp_bussiness_partner_broker> modelList=supp_bussiness_partner_brokerRepository.getnewSupplierBrokersDtls(code,supp);
			
		Type listType=new TypeToken<List<Supp_bussiness_partner_brokerDTO>>() {}.getType();
		List<Supp_bussiness_partner_brokerDTO> brokerList=new ModelMapper().map(modelList,listType);
		
		return brokerList;
	}
	
	
	public Supp_bussiness_partner_brokerDTO getSupplierBrokerDtls(String sbp_id,String brokercode){
		
		Supp_bussiness_partner_broker modelList=supp_bussiness_partner_brokerRepository.getSupplierBrokerDtls(sbp_id, brokercode);
			
		Type listType=new TypeToken<Cust_bussiness_partner_brokerDTO>() {}.getType();
		
		if(modelList == null) {
		Supp_bussiness_partner_brokerDTO def=new Supp_bussiness_partner_brokerDTO(0L,"0","0",0,"0","0","0","0",0.0,"0",0.0,"0");
		
		Supp_bussiness_partner_brokerDTO brokerDtls = new ModelMapper().map(def,listType);
			return brokerDtls;
		}
		else {
			Supp_bussiness_partner_brokerDTO brokerDtls = new ModelMapper().map(modelList,listType);
			return brokerDtls;
		}
	}

	public List<Supp_bussiness_partner_statutoryDTO> getSupplierStatutaries(String code){
		
		List<Supp_bussiness_partner_statutory> modelList=supp_bussiness_partner_statutoryRepository.getSupplierStatutaries(code);
			
		Type listType=new TypeToken<List<Supp_bussiness_partner_statutoryDTO>>() {}.getType();
		
		List<Supp_bussiness_partner_statutoryDTO> brokerList=new ModelMapper().map(modelList,listType);
		
		return brokerList;
	}
	
	public List<Supp_bussiness_partnerDTO> getSupplierMasterNCList(String company){
		
		List<Supp_bussiness_partner> modelList=supp_bussiness_partnerRepository.SupplierMasterName(true,company);
		modelList.forEach((e)->{
			e.setBp_name(e.getBp_name().toUpperCase());
		});
 		
		List<Supp_bussiness_partner> cPartners = modelList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Supp_bussiness_partner::getBp_name))
				  .collect(Collectors.toList());
		
		Type listType=new TypeToken<List<Supp_bussiness_partnerDTO>>() {}.getType();
		List<Supp_bussiness_partnerDTO> itemNameList=new ModelMapper().map(cPartners, listType);
		
		return itemNameList;
	}
	
public List<Map<String, Object>> getSupplierMasterNCListNew(String company){
		
	List<Map<String, Object>> supplierMasterNewList = new ArrayList<Map<String, Object>>();
	 
	supplierMasterNewList.addAll(supp_bussiness_partnerRepository.SupplierMasterNameNew(true,company));
	return supplierMasterNewList;
	}
	
public List<Map<String, Object>> getSupplierMasterListWithTotalAmt(String company,  String fin_year){
	
	List<Map<String, Object>> supplierMasterListWithTotalAmt = new ArrayList<Map<String, Object>>();
	 
	supplierMasterListWithTotalAmt.addAll(supp_bussiness_partnerRepository.SupplierMasterNameWithTotalAmt(company,fin_year));
	return supplierMasterListWithTotalAmt;
	}	

	public List<Supp_bussiness_partnerDTOC> newsupplierNamesList(String company){
		List<Object[]> cust_list=new ArrayList<Object[]>();
	
		cust_list.addAll(supp_bussiness_partnerRepository.SupplierMasterName_new(true,company));
	
		
		 List<Supp_bussiness_partnerDTOC> list = new ArrayList<Supp_bussiness_partnerDTOC>();        
	    for(final Object o : cust_list)
	    {
	        Object[] obj = (Object[]) o;
	        list.add(new Supp_bussiness_partnerDTOC(obj[0].toString(), obj[1].toString()));
	    }
		  
		return list;
	}
	
	
	public List<Supp_or_Customer_bussinessDTO> supplierorcustomerCodeList(String company){
		
		List<Supp_bussiness_partner> modelList=supp_bussiness_partnerRepository.SupplierMasterName(true,company);
		
		
		List<Supp_or_Customer_bussinessDTO> newList= new ArrayList<Supp_or_Customer_bussinessDTO>();
		modelList.forEach((e)->{
			Supp_or_Customer_bussinessDTO newsup= new Supp_or_Customer_bussinessDTO();
			
			newsup.setBp_Id(e.getBp_Id());
			newsup.setBp_name(e.getBp_name().toUpperCase());
			
			newList.add(newsup);
		});
	
		List<Cust_bussiness_partner> custList=new ArrayList<Cust_bussiness_partner>();
		custList.addAll(cust_bussiness_partnerRepository.findallcustomer(company));
		
		custList.forEach((e)->{
			Supp_or_Customer_bussinessDTO newsup= new Supp_or_Customer_bussinessDTO();
			newsup.setBp_Id(e.getCp_Id());
			newsup.setBp_name(e.getCp_name());
			
			newList.add(newsup);
		});
 		
		
		return newList;
	}
	
	public  List<Map<String, Object>> supplierorcustomerCodeListNew(String company)
	{
		 List<Map<String, Object>> suplist=new ArrayList<>();
		 suplist.addAll(supp_bussiness_partnerRepository.allsupplier(company));
		 suplist.addAll(cust_bussiness_partnerRepository.allcustomer(company));
		 
		// SELECT  s.bp_id,s.bp_name FROM supp_bussiness_partner s WHERE s.modified_type = 'INSERTED' AND s.bp_active =1 
		// SELECT cp_Id,cp_name FROM cust_bussiness_partner c WHERE c.modified_type = 'INSERTED' AND c.cp_active =1
		 
		 return suplist;
	}
	
	//Delete
	public List<Supp_bussiness_partnerDTO> getSupplierMasterNCList(){
		
		List<Supp_bussiness_partner> modelList=supp_bussiness_partnerRepository.SupplierMasterName(true);
		
		modelList.forEach((e)->{
			e.setBp_name(e.getBp_name().toUpperCase());
		});
 		
		List<Supp_bussiness_partner> cPartners = modelList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Supp_bussiness_partner::getBp_name))
				  .collect(Collectors.toList());
		
		Type listType=new TypeToken<List<Supp_bussiness_partnerDTO>>() {}.getType();
		List<Supp_bussiness_partnerDTO> itemNameList=new ModelMapper().map(cPartners, listType);
		
		return itemNameList;
	}
	
	public List<Supp_bussiness_partnerDTO> supplierListByGroup(String group){
		
		List<Supp_bussiness_partner> modelList=supp_bussiness_partnerRepository.supplierListByGroup(true,group);
		Type listType=new TypeToken<List<Supp_bussiness_partnerDTO>>() {}.getType();
		List<Supp_bussiness_partnerDTO> suppGroup=new ModelMapper().map(modelList, listType);
		
		return suppGroup;
	}
	
	public Supp_bussiness_partnerDTO getSupplierName(String Code){
		
		Supp_bussiness_partner modelList=supp_bussiness_partnerRepository.getSupplierName(Code);
		
		Type listType=new TypeToken<Supp_bussiness_partnerDTO>() {}.getType();
		Supp_bussiness_partnerDTO supp=new ModelMapper().map(modelList, listType);
		
		return supp;
	}

	public String getSupplierAddress(String code){
		String val=supp_bussiness_partnerRepository.getSupplierAddress(code);
		
		/*Supp_bussiness_partner modelList=supp_bussiness_partnerRepository.getSupplierAddress(code);
		Type listType=new TypeToken<Supp_bussiness_partnerDTO>() {}.getType();
		Supp_bussiness_partnerDTO suppAddr=new ModelMapper().map(modelList, listType);*/
		
		return val;
	}
	
	public List<Supp_bussiness_partner_addr_dtlsDTO> getSupplierContact()
	{
		List<Supp_bussiness_partner_addr_dtls> modelList=supp_bussiness_partner_addr_dtlsRepository.getSupplierContact();
		
		Type listType=new TypeToken<List<Supp_bussiness_partner_addr_dtlsDTO>>() {}.getType();
		
		List<Supp_bussiness_partner_addr_dtlsDTO> suppAddr=new ModelMapper().map(modelList,listType);
		
		return suppAddr;
	}
	
	public List<Supp_bussiness_partner_addr_dtlsDTO> getSuppContById(String bp_id)
	{
		List<Supp_bussiness_partner_addr_dtls> modelList=supp_bussiness_partner_addr_dtlsRepository.getSuppContById(bp_id);
		System.out.println("getSuppContById");
		modelList.forEach((e)->{
			e.setContact_person(e.getContact_person().toUpperCase());
		});
 		
		List<Supp_bussiness_partner_addr_dtls> allData = modelList
				  .parallelStream()
				  .filter(c ->c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Supp_bussiness_partner_addr_dtls::getContact_person))
				  .collect(Collectors.toList());
		
		Type listType=new TypeToken<List<Supp_bussiness_partner_addr_dtlsDTO>>() {}.getType();
		
		List<Supp_bussiness_partner_addr_dtlsDTO> sAddr=new ModelMapper().map(allData,listType);
		
		return sAddr;
	}
	
	public Supp_bussiness_partner_addr_dtlsDTO getSuppphoneByIdName(String bp_id,String CP)
	{
		Supp_bussiness_partner_addr_dtls modelList=supp_bussiness_partner_addr_dtlsRepository.getSuppphoneByIdName(bp_id,CP);
			
		Type listType=new TypeToken<Supp_bussiness_partner_addr_dtlsDTO>() {}.getType();
		
		Supp_bussiness_partner_addr_dtlsDTO sAddr=new ModelMapper().map(modelList,listType);
		
		return sAddr;
	}
	
	public Supp_bussiness_partner_delv_fromDTO getSupplierDelvFromAdd(String sbp_id,String CN){

		Supp_bussiness_partner_delv_from modelList=supp_bussiness_partner_delv_fromRepository.getSupplierDelvFromAdd(sbp_id,CN);

		Type listType = new TypeToken<Supp_bussiness_partner_delv_fromDTO>() {}.getType();

		Supp_bussiness_partner_delv_fromDTO itemNameList= new ModelMapper().map(modelList,listType);
		
		return itemNameList;
	}
	
	public List<Supp_bussiness_partner_delv_fromDTO> getSuppBUnitName()
	{
		List<Supp_bussiness_partner_delv_from> modelList=supp_bussiness_partner_delv_fromRepository.getSuppBUnitName();
		
		Type listType=new TypeToken<List<Supp_bussiness_partner_delv_fromDTO>>() {}.getType();
		
		List<Supp_bussiness_partner_delv_fromDTO> suppCon=new ModelMapper().map(modelList,listType);
		
		return suppCon;
	}
	
	public List<Supp_bussiness_partner_delv_fromDTO> getSuppContactNameList(String bp_id)
	{
		List<Supp_bussiness_partner_delv_from> modelList=supp_bussiness_partner_delv_fromRepository.getSuppContactNameList(bp_id);
		
		modelList.forEach((e)->{
		//	e.setContact_person(e.getContact_person().toUpperCase());
			e.setContact_person(e.getContact_person());
		});
 		
		List<Supp_bussiness_partner_delv_from> allData = modelList
				  .parallelStream()
				  .filter(c ->c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Supp_bussiness_partner_delv_from::getContact_person))
				  .collect(Collectors.toList());
		
		Type listType=new TypeToken<List<Supp_bussiness_partner_delv_fromDTO>>() {}.getType();
		
		List<Supp_bussiness_partner_delv_fromDTO> suppCon=new ModelMapper().map(allData,listType);
		
		return suppCon;
	}
	
	public Supp_bussiness_partner_delv_fromDTO getSuppBUnitAddr(String bp_id)
	{
		Supp_bussiness_partner_delv_from modelList=supp_bussiness_partner_delv_fromRepository.getSuppBUnitAddr(bp_id);
			
		Type listType=new TypeToken<Supp_bussiness_partner_delv_fromDTO>() {}.getType();
		
		Supp_bussiness_partner_delv_fromDTO suppAddr=new ModelMapper().map(modelList,listType);
		
		return suppAddr;
	}
	
	public Supp_bussiness_partner_addr_dtlsDTO getSupplierContDetails(String sbp_id,String cp){

		Supp_bussiness_partner_addr_dtls modelList=supp_bussiness_partner_addr_dtlsRepository.getSupplierContDetails(sbp_id,cp);

		Type listType = new TypeToken<Supp_bussiness_partner_addr_dtlsDTO>() {}.getType();

		Supp_bussiness_partner_addr_dtlsDTO itemNameList= new ModelMapper().map(modelList,listType);
		
		return itemNameList;
	}
	
	public Supp_bussiness_partner_addressDTO getSupplierAddr(String bp_id){
		System.out.println("bp_id/"+bp_id);
		
	
		
		Supp_bussiness_partner_address modelList=supp_bussiness_partner_addressRepository.getSupplierAddr(bp_id);

		Type listType = new TypeToken<Supp_bussiness_partner_addressDTO>() {}.getType();

		Supp_bussiness_partner_addressDTO suppAddress= new ModelMapper().map(modelList,listType);
		
		suppAddress.setAddress(modelList.getAdd1()+","+modelList.getAdd2()+","+modelList.getAdd3());
		
		return suppAddress;
	}
	
	public Supp_bussiness_partner_addressDTO getSuppBPAddr(String bp_id){

		Supp_bussiness_partner_address modelList=supp_bussiness_partner_addressRepository.getSupplierAddr(bp_id);

		Type listType = new TypeToken<Supp_bussiness_partner_addressDTO>() {}.getType();

		Supp_bussiness_partner_addressDTO suppAddress= new ModelMapper().map(modelList,listType);
		return suppAddress;
	}
	
	public Supp_bussiness_partner_bill_addrDTO getSuppBPBillAddr(String bp_id){

		Supp_bussiness_partner_bill_addr modelList=supp_bussiness_partner_bill_addrRepository.getSuppBPBillAddr(bp_id);

		Type listType = new TypeToken<Supp_bussiness_partner_bill_addrDTO>() {}.getType();

		Supp_bussiness_partner_bill_addrDTO suppAddress= new ModelMapper().map(modelList,listType);
		
		return suppAddress;
	}
	
	public List<Supp_bussiness_partner_bill_addr_dtlsDTO> getSuppBPBillAddrDtls(String bp_id)
	{
		List<Supp_bussiness_partner_bill_addr_dtls> modelList=supp_bussiness_partner_bill_addr_dtlsRepository.getSuppBPBillAddrDtls(bp_id);
		
		Type listType=new TypeToken<List<Supp_bussiness_partner_bill_addr_dtlsDTO>>() {}.getType();
		
		List<Supp_bussiness_partner_bill_addr_dtlsDTO> sAddr=new ModelMapper().map(modelList,listType);
		
		return sAddr;
	}
	
	public List<Supp_bussiness_partner_docDTO> getSuppBPDoc(String bp_id)
	{
		List<Supp_bussiness_partner_doc> modelList=supp_bussiness_partner_docRepository.getSuppBPDoc(bp_id);
		
			
		Type listType=new TypeToken<List<Supp_bussiness_partner_docDTO>>() {}.getType();
		
		List<Supp_bussiness_partner_docDTO> sAddr=new ModelMapper().map(modelList,listType);
		
		return sAddr;
	}
	
	public Supp_bussiness_partner_accontDTO getSuppBPAcc(String bp_id)
	{
		Supp_bussiness_partner_accont modelList=supp_bussiness_partner_accontRepository.getSuppBPAcc(bp_id);
		Type listType=new TypeToken<Supp_bussiness_partner_accontDTO>() {}.getType();
		
		Supp_bussiness_partner_accontDTO sAddr=new ModelMapper().map(modelList,listType);
		
		return sAddr;
	}
	
	public Supp_bussiness_partner_statutoryDTO getSuppBPStatuDtls(String bp_id)
	{
		Supp_bussiness_partner_statutory modelList=supp_bussiness_partner_statutoryRepository.getSuppBPStatuDtls(bp_id);
		Type listType=new TypeToken<Supp_bussiness_partner_statutoryDTO>() {}.getType();
		
		Supp_bussiness_partner_statutoryDTO sAddr=new ModelMapper().map(modelList,listType);
		
		return sAddr;
	}
	
	public Supp_bussiness_partner_statutoryDTO getSupplierStatDtls(String bp_id)
	{
		System.out.println("bp_id: "+bp_id);
		Supp_bussiness_partner_statutory modelList=supp_bussiness_partner_statutoryRepository.getSupplierStatDtls(bp_id);
		Type listType=new TypeToken<Supp_bussiness_partner_statutoryDTO>() {}.getType();
		
		Supp_bussiness_partner_statutoryDTO supStatDtls=new ModelMapper().map(modelList,listType);
		
		return supStatDtls;
	}
	
	public Supp_bussiness_partner_delv_fromDTO getSuppDelvAddress(String bp_id,String bu_name)
	{
		Supp_bussiness_partner_delv_from modelList=supp_bussiness_partner_delv_fromRepository.getSuppDelvAddress(bp_id,bu_name);
		Type listType=new TypeToken<Supp_bussiness_partner_delv_fromDTO>() {}.getType();
		
		Supp_bussiness_partner_delv_fromDTO supDelAddr=new ModelMapper().map(modelList,listType);
		
		String FullAdd="";
		
		if(Utility.isNullOrEmpty(supDelAddr.getAddress()))
		{
			FullAdd+=supDelAddr.getAddress()+",";
		}
		if(Utility.isNullOrEmpty(supDelAddr.getCity()))
		{
			FullAdd+=supDelAddr.getCity()+",";
		}
		if(Utility.isNullOrEmpty(String.valueOf(supDelAddr.getPincode())))
		{
			FullAdd+=supDelAddr.getPincode()+",";
		}
		if(Utility.isNullOrEmpty(String.valueOf(supDelAddr.getMobile())))
		{
			FullAdd+=supDelAddr.getMobile()+",";
		}
		if(Utility.isNullOrEmpty(supDelAddr.getFax()))
		{
			FullAdd+=supDelAddr.getFax()+",";
		}
		if(Utility.isNullOrEmpty(supDelAddr.getEmail()))
		{
			FullAdd+=supDelAddr.getEmail()+",";
		}
		
		FullAdd=FullAdd.substring(0,FullAdd.length()-1);
		supDelAddr.setFulladdress(FullAdd);
		
		//supDelAddr.setFulladdress(supDelAddr.getAddress()+" , "+supDelAddr.getCity()+" , "+supDelAddr.getPincode()+" , "+supDelAddr.getMobile()+" , "+supDelAddr.getFax()+" , "+supDelAddr.getEmail());
		
		return supDelAddr;
	} 
	
 	public List<Supp_bussiness_partnerDTO> getSupplierThruBU(String bunit){
		
		List<Supp_bussiness_partner> modelList=new ArrayList<Supp_bussiness_partner>();
		
		modelList.addAll(supp_bussiness_partnerRepository.getSupplierThruBU(bunit));
			
		Type listType=new TypeToken<List<Supp_bussiness_partnerDTO>>() {}.getType();
		
		List<Supp_bussiness_partnerDTO> supplierList=new ModelMapper().map(modelList,listType);
		
		return supplierList;
	}
 	
 	public List<Map<String,Object>> getSupplierThruBUNew(String bunit)
 	{
 		List<Map<String,Object>> supplierList=new ArrayList<Map<String, Object>>();
 		
 		supplierList.addAll(supp_bussiness_partnerRepository.getSupplierThruBUNew(bunit));
 		
 		return supplierList;
 	}

 	public MessageStatusDTO chkSuppNameStatus(String sname)
 	{
 		String supplier=sname.replace("MS", "M/S");
 		
 		/*String name=sname.substring(3);
 		
 		System.out.println("ENTER : : "+supplier+" Check : "+name);*/
 		
 		String result=supp_bussiness_partnerRepository.chkSuppNameStatus(supplier);
		
		if(result == null) 
		{
		 result="NOTEXIST";
		}
		else 
		{
		 result="EXIST";
		}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}

 	public StatusDTO chkSuppBpCodeStatus(String code){
		String result="";
		
		if(supp_bussiness_partnerRepository.chkSuppBpCodeStatus(code).isPresent()) {
			result="EXIST";
		}else {
			result="NOTEXIST";
		}
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
	}
 	
 	public List<Supp_bussiness_partner_brokerDTO> supp_bro_updation(String suppliercode) 
	 {
		
		 supp_bussiness_partner_brokerRepository.broker_supp(suppliercode); 
		 
		 List<Supp_bussiness_partner_broker> modelList=supp_bussiness_partner_brokerRepository.getSupplierBrokerDtlscode(suppliercode);
			
		 Type listType=new TypeToken<List<Supp_bussiness_partner_brokerDTO>>() {}.getType();
		 List<Supp_bussiness_partner_brokerDTO> brokerList=new ModelMapper().map(modelList,listType);
			
		 return brokerList;
		 
	 }
 	
 	public StatusDTO checkSupplierMasterUsage(String code)
 	{
 		String result=supp_bussiness_partnerRepository.checkSupplierMasterUsage(code);
		
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
 	}
 	
 	@Transactional
	public Supp_bussiness_partner accountpostingSupplierBPartner(long id) 
	{
		Optional<Supp_bussiness_partner> op=this.supp_bussiness_partnerRepository.findById(id);
		Supp_bussiness_partner_address sup_address=supp_bussiness_partnerRepository.getaddress(op.get().getBp_Id());
		Supp_bussiness_partner_statutory sup_statutary=supp_bussiness_partnerRepository.getstatutary(op.get().getBp_Id());
		Supp_bussiness_partner_accont sup_accounts=supp_bussiness_partnerRepository.getaccounts(op.get().getBp_Id());
		
		List<Supp_bussiness_partner_addr_dtls> contactdetails=supp_bussiness_partnerRepository.getcontact(op.get().getBp_Id());
		System.out.println("over here ");
		
		String output,finalresult,registered;
		Tallyrequest_Suppliermaster tally=new Tallyrequest_Suppliermaster();
		try  
		{
			String address="",gstno="";
			if(Utility.isNullOrEmpty(sup_address.getAdd1())) 
			{
				address = sup_address.getAdd1();
			}
			if(Utility.isNullOrEmpty(sup_address.getAdd2())) 
			{
				address = sup_address.getAdd2();
			}
			if(Utility.isNullOrEmpty(sup_address.getAdd3())) 
			{
				address = sup_address.getAdd3();
			}
			
			System.out.println("over here 12 ");
			
	//address.substring(0, address.length()-1)
			String registerstatus="";
	
		if(Utility.isNullOrEmpty(sup_statutary.getGst_no())) 
		{
			
			String gstnno=sup_statutary.getGst_no();
			
			if(gstnno.compareToIgnoreCase("null")==0 || gstnno.compareToIgnoreCase("")==0) 
			{
				registerstatus="UnRegistered";
			}
			else {
				gstno = sup_statutary.getGst_no();
				registerstatus="Registered";
			}
		}
		else 
		{
			registerstatus="UnRegistered";
		}
		String altname;
		if(Utility.isNullOrEmpty(op.get().getAlt_name())) 
		{
			altname=op.get().getAlt_name();
			if(op.get().getAlt_name().contains("&"))
			{
				altname=op.get().getAlt_name().replaceAll("&","&amp;");
			}
			
		}
		else 
		{
			altname="";
		}
		if(address.compareToIgnoreCase("")!=0) 
		{
			//address=address.substring(0, address.length()-1);
			address=address+" DIST: "+sup_address.getDistrict();
		}
		else 
		{
			address=" DIST: "+sup_address.getDistrict();
		}
		
		
		String pinno;
		if(Utility.isNullOrEmpty(String.valueOf(sup_address.getPincode()))) 
		{
			System.out.println("pincode "+sup_address.getPincode());
			pinno=String.valueOf(sup_address.getPincode());
			if(pinno.compareToIgnoreCase("null")==0 || pinno.compareToIgnoreCase("")==0)
			{
				pinno="";
			}
			
			
		}
		else 
		{
			pinno="";
		}
		
		String bankname=sup_accounts.getBankname();
		System.out.println("state name "+sup_address.getState());
		
		
		String websitecheck=supp_bussiness_partner_addressRepository.getSupplierAddr(op.get().getBp_Id()).getWebsite();
		String website="";
		if(Utility.isNullOrEmpty(String.valueOf(websitecheck))) 
		{
			website=String.valueOf(websitecheck);
		}
		else 
		{
			website="";
		}
		//System.out.println("1");
		String email="",phone="",fax="",contactperson="";
		
		if(Utility.isNullOrEmpty(String.valueOf(contactdetails.get(0).getEmail()))) 
		{
			email=String.valueOf(contactdetails.get(0).getEmail());
		}
	//	System.out.println("2");
		//System.out.println("tuhin phone " + contactdetails.get(0).getPhone());
		if(Utility.isNullOrEmpty(String.valueOf(contactdetails.get(0).getPhone()))) 
		{
			//System.out.println("tuhin");
			//phone=Long.toString(contactdetails.get(0).getPhone());
			phone=String.valueOf(contactdetails.get(0).getPhone());
		}
		//System.out.println("3");
		if(Utility.isNullOrEmpty(String.valueOf(contactdetails.get(0).getFax()))) 
		{
			fax=String.valueOf(contactdetails.get(0).getFax());
		}
		//System.out.println("4");
		if(Utility.isNullOrEmpty(String.valueOf(contactdetails.get(0).getContact_person()))) 
		{
			contactperson=String.valueOf(contactdetails.get(0).getContact_person());
		}
		//System.out.println("5");
		String supplier_name=op.get().getBp_name();
		if(supplier_name.contains("&"))
		{
			supplier_name=supplier_name.replaceAll("&","&amp;");
		}
		if(address.contains("&"))
		{
			address=address.replaceAll("&","&amp;");
		}
		
		Tallyrequest_Openingbalanceofmaster opening =new Tallyrequest_Openingbalanceofmaster();
		try  
		{
			String openingbal=opening.SendToTally(supplier_name);
			output=tally.SendToTally(supplier_name, altname,address,sup_address.getState(),pinno,sup_statutary.getPan_no(),registerstatus,
					op.get().getGroup_type_name(),String.valueOf(contactdetails.get(0).getMobile()),gstno,
					sup_accounts.getIfsc(),sup_accounts.getAcc_no(),bankname,website,email,phone,fax,contactperson,openingbal);
			
			System.out.println(output);
			String [] splitoutput = output.split("\\|\\|");
			System.out.println(splitoutput[0] +" / " + splitoutput[1]+"/"+id);
			int export=0;
			if(Integer.parseInt(splitoutput[1])==1) 
			{
				export=1;
			}
			if(Integer.parseInt(splitoutput[2])==1) 
			{
				export=1;
			}
			
			//supp_bussiness_partnerRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
			supp_bussiness_partnerRepository.exportuomtally(id,splitoutput[0],export);
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
	
		Optional<Supp_bussiness_partner> op1=this.supp_bussiness_partnerRepository.findById(id);
		System.out.println(op.get());
		
		return op1.get();
	}
 	
 	@Transactional
	public Supp_bussiness_partner accountpostingUndoSupplierBPartner(long id) 
	{
	
		try  
		{
			Optional<Supp_bussiness_partner> op=this.supp_bussiness_partnerRepository.findById(id);
			LocalDateTime ldt = LocalDateTime.now();
			supp_bussiness_partnerRepository.exportuomtallyReverse(id,op.get().getInserted_by(),ldt);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		Optional<Supp_bussiness_partner> op1=this.supp_bussiness_partnerRepository.findById(id);
		System.out.println(op1.get());
		
		return op1.get();
	}
 	
 	public List<Supp_bussiness_partner> searchSupplierMasterData(String supp_name,String supp_group,String supp_type,String finyear,String company_name)
	{
		List<Supp_bussiness_partner> searchSupplier =new ArrayList<Supp_bussiness_partner>();
		
		String tablename="supp_bussiness_partner",item_id="bp_id",item_group="group_type",item_category="bp_type",item_type="alt_name",itemtype1="";
		searchSupplier.addAll(supp_bussiness_partnerRepository.getsearchItemdata(tablename,item_id,item_group,item_category,item_type,supp_name,supp_group,supp_type,itemtype1,finyear));
		
		List<Supp_bussiness_partner> allItem = searchSupplier
				  .parallelStream()
				  .filter(c -> c.getCompany_id().equals(company_name) && c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Supp_bussiness_partner::getBp_Id).reversed())
				  .collect(Collectors.toList());
		
		
		return allItem;
		
		
	}
 	
 	public List<Supp_bussiness_partnerDTO> getSupplierByChannel(String channelid)
 	{
 		Channel_customer_master custChannelId=channel_customer_masterRepository.getChannelCustId(channelid);
		String[] arrOfStr=custChannelId.getCustid().split(",");
		List<Supp_bussiness_partner> modelList=new ArrayList<Supp_bussiness_partner>();
		
		for(int i=0;i<arrOfStr.length;i++)
		{
			modelList.add(supp_bussiness_partnerRepository.getSupplierName(arrOfStr[i]));
		}
		
		Type listType = new TypeToken<List<Supp_bussiness_partnerDTO>>() {}.getType();
		List<Supp_bussiness_partnerDTO> custchannel = new ModelMapper().map(modelList,listType);
		
		return custchannel;
 	}
 	
 	public Map<String, Object> getSuppBPStat(String code){
 		System.out.println("bpid::"+code);
 		Map<String, Object> val=supp_bussiness_partnerRepository.getSuppBPStat(code);
		return val;
	}
 	
 	public Map<String, Object> getSupplierAddrFast(String bp_id){
 	Map<String, Object> val=supp_bussiness_partner_addressRepository.getSupplierAddrFast(bp_id);
	return val;
 	}

 	public StatusDTO checkValidGstNo(String gst)
 	{
 		String result="";
 		if(supp_bussiness_partner_statutoryRepository.checkValidGstNo(gst).isPresent()) {
			result="EXIST";
		}else {
			result="NOTEXIST";
		}
		
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
 	}
 	
 	public StatusDTO checkValidPANNo(String pan)
 	{
 		String result="";
 		if(supp_bussiness_partner_statutoryRepository.checkValidPANNo(pan).isPresent()) {
			result="EXIST";
		}else {
			result="NOTEXIST";
		}
		
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO = new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		
		return statusDTO;
 	}
 	
 	public List<Map<String, Object>> getSupplierMasterReport()
 	{
 		return supp_bussiness_partnerRepository.getSupplierMasterReport();
 	}
 	
}
