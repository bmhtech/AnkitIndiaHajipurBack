package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.AnkitIndia.Exporttotally.Tallyrequest_BrokerMaster;
import com.AnkitIndia.Exporttotally.Tallyrequest_CustomerMaster;
import com.AnkitIndia.FileUpload.FileUploadUtil;
import com.AnkitIndia.Utility.FileBrockerMasterUtil;
import com.AnkitIndia.Utility.FileSupplierMasterUtil;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Broker_master;
import com.AnkitIndia.jwtauthentication.model.Broker_master_account;
import com.AnkitIndia.jwtauthentication.model.Broker_master_add;
import com.AnkitIndia.jwtauthentication.model.Broker_master_add_dtls;
import com.AnkitIndia.jwtauthentication.model.Broker_master_doc;
import com.AnkitIndia.jwtauthentication.model.Broker_master_oth;
import com.AnkitIndia.jwtauthentication.model.Broker_master_pty;
import com.AnkitIndia.jwtauthentication.model.Broker_master_statutory;
import com.AnkitIndia.jwtauthentication.model.Broker_master_transporter;
import com.AnkitIndia.jwtauthentication.model.Broker_master_vdr;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_accont;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address_dtls;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_statutory;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Status_table;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_broker;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.repository.Broker_groupRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_master_accountRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_master_addRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_master_add_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_master_docRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_master_othRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_master_ptyRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_master_statutoryRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_master_transporterRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_master_vdrRepository;
import com.AnkitIndia.jwtauthentication.repository.City_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.District_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Post_office_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_brokerRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
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

@Service
public class Broker_masterService_Imp implements Broker_masterService{

	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	Broker_master_addRepository broker_master_addRepository;
	
	@Autowired
	Broker_groupRepository broker_groupRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	Trans_bussiness_partnerRepository trans_bussiness_partnerRepository;
	
	@Autowired
	Broker_master_add_dtlsRepository broker_master_add_dtlsRepository;
	
	@Autowired 
	Broker_master_ptyRepository broker_master_ptyRepository;
	
	@Autowired 
	Broker_master_vdrRepository broker_master_vdrRepository;
	
	@Autowired
	Broker_master_transporterRepository broker_master_transporterRepository;
	
	@Autowired
	Broker_master_othRepository broker_master_othRepository;
	
	@Autowired
	Broker_master_docRepository broker_master_docRepository;
	
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
	Supp_bussiness_partner_brokerRepository supp_bussiness_partner_brokerRepository;
	
	public SequenceIdDTO getBrokerSequenceId(String prefix,String company,String wtype) {
		Long slno=0L;String fprefix="",type="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		if(wtype.compareTo("BROKER FOR MATERIAL")==0) {type="BM";}
		else if(wtype.compareTo("BROKER FOR SERVICES")==0) {type="BS";}
		else if(wtype.compareTo("BROKER FOR FINISH MATERIAL")==0) {type="BF";}
		else {type="";}
		fprefix=prefix+"/"+code+"/"+type+"/";
		String gen_sno=broker_masterRepository.getBrokerSequenceId(fprefix,company,wtype);
		
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
	//public Broker_master save(Broker_master broker_master)
	public Broker_master save(Broker_master broker_master,MultipartFile files[]) throws IOException
	
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="BM";
		if(broker_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(broker_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		broker_master.setBroker_Id(gen_sno);
		
		if(Utility.isNullOrEmpty(broker_master.getGroup_type())) {
			broker_master.setGroup_type_name(broker_groupRepository.getBroParentGroup(broker_master.getGroup_type(),broker_master.getCompany_id()).getGroup_name());
		}
		
		/*Start checking transaction no for unique */
		long nslno=0;String tprefix=broker_master.getBroker_code().substring(0,10);
		
		String gen_snonew=broker_masterRepository.getBrokerSequenceId(tprefix,broker_master.getCompany_id(),broker_master.getBroker_type());
		if(gen_snonew != null ) {
			nslno=Long.parseLong(gen_snonew);
		}
		String gen_tslno=UniqueIDTransaction.uniqueId5(tprefix,nslno);
		broker_master.setBroker_code(gen_tslno);
		/*End checking transaction no for unique */
		
		broker_master.setModified_type("INSERTED");
		broker_master.setInserted_by(userRepository.getUserDetails(broker_master.getUsername()).getName());
		broker_master.setInserted_on(ldt);
		broker_master.setUpdated_by("NA");
		broker_master.setUpdated_on(ldt);
		broker_master.setDeleted_by("NA");
		broker_master.setDeleted_on(ldt);
		
		Set<Broker_master_add> bMA=new HashSet<Broker_master_add>();
		bMA.add(broker_master.getBroker_master_add());
		for(Broker_master_add bb1:bMA) 
		{
			if(Utility.isNullOrEmpty(bb1.getState_code())) {
				bb1.setState(state_masterRepository.getState(bb1.getState_code()).getState_name());
			}else {bb1.setState("None");}
			
			if(Utility.isNullOrEmpty(bb1.getDist_code())) {
				bb1.setDist(district_masterRepository.getDistrictDtls(bb1.getDist_code()).getDist_name());
			}else {bb1.setDist("None");}
			
			/*if(Utility.isNullOrEmpty(bb1.getCity_code())) {
				bb1.setCity(city_masterRepository.getCityDtls(bb1.getCity_code()).getCity_name());
			}else {bb1.setCity("None");}*/
			
			if(Utility.isNullOrEmpty(bb1.getPostid())) {
				//bb1.setPost_office(post_office_masterRepository.getPincodeThruPO(bb1.getPostid()).get().getPost_office());
			}else {bb1.setPost_office("None");}
			
			bb1.setCity(bb1.getCity_code());
			bb1.setBroker_code(broker_master.getBroker_code());
			bb1.setBroker_Id(gen_sno);
			bb1.setCompany_id(broker_master.getCompany_id());
			bb1.setFin_year(broker_master.getFin_year());
			bb1.setModified_type("INSERTED");
			bb1.setInserted_by(broker_master.getInserted_by());
			bb1.setInserted_on(broker_master.getInserted_on());
			bb1.setUpdated_by(broker_master.getUpdated_by());
			bb1.setUpdated_on(broker_master.getUpdated_on());
			bb1.setDeleted_by("NA");
			bb1.setDeleted_on(ldt);
		}
		if(!bMA.isEmpty()) {
			broker_master.setBroker_master_add(bMA.iterator().next());
		}

		Set<Broker_master_add_dtls> bMAD=new HashSet<Broker_master_add_dtls>();
		bMAD.addAll(broker_master.getBroker_master_add_dtls());
		for(Broker_master_add_dtls bb2:bMAD) 
		{
			bb2.setBroker_code(broker_master.getBroker_code());
			bb2.setBroker_Id(gen_sno);
			bb2.setCompany_id(broker_master.getCompany_id());
			bb2.setFin_year(broker_master.getFin_year());
			bb2.setModified_type("INSERTED");
			bb2.setInserted_by(broker_master.getInserted_by());
			bb2.setInserted_on(broker_master.getInserted_on());
			bb2.setUpdated_by(broker_master.getUpdated_by());
			bb2.setUpdated_on(broker_master.getUpdated_on());
			bb2.setDeleted_by("NA");
			bb2.setDeleted_on(ldt);
		}
		broker_master.setBroker_master_add_dtls(bMAD);
		
		Set<Broker_master_pty> bMP=new HashSet<Broker_master_pty>();
		bMP.addAll(broker_master.getBroker_master_pty());
		for(Broker_master_pty bb3:bMP) 
		{
			bb3.setBroker_code(broker_master.getBroker_code());
			bb3.setBroker_Id(gen_sno);
			if(Utility.isNullOrEmpty(bb3.getPty_code_name())) {
				bb3.setPty_name(cust_bussiness_partnerRepository.getCustomer(bb3.getPty_code_name()).getCp_name());
			}else {bb3.setPty_name("None");}
			bb3.setCompany_id(broker_master.getCompany_id());
			bb3.setFin_year(broker_master.getFin_year());
			bb3.setModified_type("INSERTED");
			bb3.setInserted_by(broker_master.getInserted_by());
			bb3.setInserted_on(broker_master.getInserted_on());
			bb3.setUpdated_by(broker_master.getUpdated_by());
			bb3.setUpdated_on(broker_master.getUpdated_on());
			bb3.setDeleted_by("NA");
			bb3.setDeleted_on(ldt);
		}
		broker_master.setBroker_master_pty(bMP);
		
	
		
		
		
		Set<Broker_master_vdr> bMV=new HashSet<Broker_master_vdr>();
		bMV.addAll(broker_master.getBroker_master_vdr());
		System.out.println("size/"+bMV.size());
		for(Broker_master_vdr bb4:bMV) 
		{
			bb4.setBroker_code(broker_master.getBroker_code());
			bb4.setBroker_Id(gen_sno);
			System.out.println("vendors/"+bb4.getVdr_code_name());
			if(Utility.isNullOrEmpty(bb4.getVdr_code_name())) {
				bb4.setVdr_name(supp_bussiness_partnerRepository.getSupplierName(bb4.getVdr_code_name()).getBp_name());
			}else {bb4.setVdr_name("None");}
			bb4.setCompany_id(broker_master.getCompany_id());
			bb4.setFin_year(broker_master.getFin_year());
			bb4.setModified_type("INSERTED");
			bb4.setInserted_by(broker_master.getInserted_by());
			bb4.setInserted_on(broker_master.getInserted_on());
			bb4.setUpdated_by(broker_master.getUpdated_by());
			bb4.setUpdated_on(broker_master.getUpdated_on());
			bb4.setDeleted_by("NA");
			bb4.setDeleted_on(ldt);
			
			//changes here 25-04-2022	
			//Supp_bussiness_partner_broker sup_broker=new Supp_bussiness_partner_broker();
			
			System.out.println("bb4.getVdr_code_name()/"+bb4.getVdr_code_name());
			
			String x=bb4.getVdr_code_name();
			//supp_bussiness_partner_brokerRepository.sup_broker_count1(x);
			
			//supp_bussiness_partner_brokerRepository.sup_broker(x);
			Supp_bussiness_partner_broker supbroker=new Supp_bussiness_partner_broker();
		
			supbroker.setBp_Id(bb4.getVdr_code_name());
			supbroker.setBased_on(bb4.getBased_on());
			supbroker.setBasis(bb4.getBasis());
			supbroker.setBrokerage_acc(bb4.getBrokerage_acc());
			supbroker.setCompany_id(bb4.getCompany_id());
			supbroker.setDeleted_by("NA");
			supbroker.setDeleted_on(ldt);
			//supbroker.setEff_date(bb4.getEff_date());
			supbroker.setFin_year(broker_master.getFin_year());
			supbroker.setInserted_by(broker_master.getInserted_by());
			supbroker.setInserted_on(broker_master.getInserted_on());
			supbroker.setModified_type("INSERTED");
			supbroker.setRate(bb4.getRate());
			supbroker.setRemarks(bb4.getRemarks());
			//supbroker.setSl_no(bb4.getSrl_no());
			supbroker.setTds_acc(bb4.getTds_acc());
			supbroker.setTds_rate(bb4.getTds_rate());
			supbroker.setUpdated_by(broker_master.getUpdated_by());
			supbroker.setUpdated_on(broker_master.getUpdated_on());
			
			
			supbroker.setVen_code_name(bb4.getBroker_Id());
			supbroker.setVen_name(broker_master.getName());
			
		
			bb4.setSupp_bussiness_partner_broker(supbroker);
			
			//changes here ends 
		}
		broker_master.setBroker_master_vdr(bMV);
		
	
		
		
		
		Set<Broker_master_transporter> bMT=new HashSet<Broker_master_transporter>();
		bMT.addAll(broker_master.getBroker_master_transporter());
		for(Broker_master_transporter bb5:bMT) 
		{
			bb5.setBroker_code(broker_master.getBroker_code());
			bb5.setBroker_Id(gen_sno);
			if(Utility.isNullOrEmpty(bb5.getTrans_code_name())) {
				bb5.setTrans_name(trans_bussiness_partnerRepository.bpNameById(bb5.getTrans_code_name()).getBp_name());
			}else {bb5.setTrans_name("None");}
			bb5.setCompany_id(broker_master.getCompany_id());
			bb5.setFin_year(broker_master.getFin_year());
			bb5.setModified_type("INSERTED");
			bb5.setInserted_by(broker_master.getInserted_by());
			bb5.setInserted_on(broker_master.getInserted_on());
			bb5.setUpdated_by(broker_master.getUpdated_by());
			bb5.setUpdated_on(broker_master.getUpdated_on());
			bb5.setDeleted_by("NA");
			bb5.setDeleted_on(ldt);
		}
		broker_master.setBroker_master_transporter(bMT);
		
		Set<Broker_master_account> bMAcc=new HashSet<Broker_master_account>();
		bMAcc.add(broker_master.getBroker_master_account());
		for(Broker_master_account bb6:bMAcc) 
		{
			bb6.setBroker_code(broker_master.getBroker_code());
			bb6.setBroker_Id(gen_sno);
			bb6.setCompany_id(broker_master.getCompany_id());
			bb6.setFin_year(broker_master.getFin_year());
			bb6.setModified_type("INSERTED");
			bb6.setInserted_by(broker_master.getInserted_by());
			bb6.setInserted_on(broker_master.getInserted_on());
			bb6.setUpdated_by(broker_master.getUpdated_by());
			bb6.setUpdated_on(broker_master.getUpdated_on());
			bb6.setDeleted_by("NA");
			bb6.setDeleted_on(ldt);
		}
		if(!bMAcc.isEmpty()) {
			broker_master.setBroker_master_account(bMAcc.iterator().next());
		}
		
		Set<Broker_master_oth> bMO=new HashSet<Broker_master_oth>();
		bMO.addAll(broker_master.getBroker_master_oth());
		for(Broker_master_oth bb7:bMO) 
		{
			bb7.setBroker_code(broker_master.getBroker_code());
			bb7.setBroker_Id(gen_sno);
			bb7.setCompany_id(broker_master.getCompany_id());
			bb7.setFin_year(broker_master.getFin_year());
			bb7.setModified_type("INSERTED");
			bb7.setInserted_by(broker_master.getInserted_by());
			bb7.setInserted_on(broker_master.getInserted_on());
			bb7.setUpdated_by(broker_master.getUpdated_by());
			bb7.setUpdated_on(broker_master.getUpdated_on());
			bb7.setDeleted_by("NA");
			bb7.setDeleted_on(ldt);
		}
		broker_master.setBroker_master_oth(bMO);
		
		Set<Broker_master_statutory> bMS=new HashSet<Broker_master_statutory>();
		bMS.add(broker_master.getBroker_master_statutory());
		for(Broker_master_statutory bb8:bMS) 
		{
			bb8.setBroker_code(broker_master.getBroker_code());
			bb8.setBroker_Id(gen_sno);
			bb8.setCompany_id(broker_master.getCompany_id());
			bb8.setFin_year(broker_master.getFin_year());
			bb8.setModified_type("INSERTED");
			bb8.setInserted_by(broker_master.getInserted_by());
			bb8.setInserted_on(broker_master.getInserted_on());
			bb8.setUpdated_by(broker_master.getUpdated_by());
			bb8.setUpdated_on(broker_master.getUpdated_on());
			bb8.setDeleted_by("NA");
			bb8.setDeleted_on(ldt);
		}
		if(!bMS.isEmpty()) {
			broker_master.setBroker_master_statutory(bMS.iterator().next());
		}
		
		Set<Broker_master_doc> bM12=new HashSet<Broker_master_doc>();
		bM12.addAll(broker_master.getBroker_master_doc());
		int i=0;
		for(Broker_master_doc bb9:bM12) 
		{
			bb9.setBroker_code(broker_master.getBroker_code());
			bb9.setBroker_Id(gen_sno);
			
			
			System.out.println(" hello files : "+files.length);
			//here start
			
			if(files.length > 0) {
				try {
					System.out.println("files[i] :: "+i+" / "+files[i]);
						//fileUpload(files[i],gen_sno+"_");
					
					bb9.setDoc_pdf(FileUploadUtil.fileUpload(files[i],gen_sno+"_",FileBrockerMasterUtil.folderPath));
						
					
				i++;
				}
				catch (IOException e)
				{
					System.out.println(e);
					}
				
			}
			System.out.println("3 :: ");
			
			
			
			
			
			bb9.setCompany_id(broker_master.getCompany_id());
			bb9.setFin_year(broker_master.getFin_year());
			bb9.setModified_type("INSERTED");
			bb9.setInserted_by(broker_master.getInserted_by());
			bb9.setInserted_on(broker_master.getInserted_on());
			bb9.setUpdated_by(broker_master.getUpdated_by());
			bb9.setUpdated_on(broker_master.getUpdated_on());
			bb9.setDeleted_by("NA");
			bb9.setDeleted_on(ldt);
		}
		broker_master.setBroker_master_doc(bM12);
		
		
		return  broker_masterRepository.save(broker_master);
	}
	
	@Transactional
	public Broker_master update(Broker_master iMaster,Long id)
	{
		Optional<Broker_master> op = broker_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(iMaster.getGroup_type())) {
			iMaster.setGroup_type_name(broker_groupRepository.getBroParentGroup(iMaster.getGroup_type(),iMaster.getCompany_id()).getGroup_name());
		}else {iMaster.setGroup_type_name("None");}
		
		iMaster.setBroker_Id(op.get().getBroker_Id());
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
		
		broker_master_addRepository.broker_master_addupdate(iMaster.getBroker_Id(),"UPDATED");
		
		Set<Broker_master_add> bMA=new HashSet<Broker_master_add>();
		bMA.add(iMaster.getBroker_master_add());
		for(Broker_master_add bb1:bMA) 
		{
			if(Utility.isNullOrEmpty(bb1.getState_code())) {
				bb1.setState(state_masterRepository.getState(bb1.getState_code()).getState_name());
			}else {bb1.setState("None");}
			
			if(Utility.isNullOrEmpty(bb1.getDist_code())) {
				bb1.setDist(district_masterRepository.getDistrictDtls(bb1.getDist_code()).getDist_name());
			}else {bb1.setDist("None");}
			
			/*if(Utility.isNullOrEmpty(bb1.getCity_code())) {
				bb1.setCity(city_masterRepository.getCityDtls(bb1.getCity_code()).getCity_name());
			}else {bb1.setCity("None");}*/
			
			if(Utility.isNullOrEmpty(bb1.getPostid())) {
				//bb1.setPost_office(post_office_masterRepository.getPincodeThruPO(bb1.getPostid()).get().getPost_office());
			}else {bb1.setPost_office("None");}
			
			bb1.setCity(bb1.getCity_code());
			bb1.setBroker_code(iMaster.getBroker_code());
			bb1.setBroker_Id(iMaster.getBroker_Id());
			bb1.setCompany_id(iMaster.getCompany_id());
			bb1.setFin_year(iMaster.getFin_year());
			bb1.setModified_type("INSERTED");
			bb1.setInserted_by(iMaster.getInserted_by());
			bb1.setInserted_on(iMaster.getInserted_on());
			bb1.setUpdated_by(iMaster.getUpdated_by());
			bb1.setUpdated_on(iMaster.getUpdated_on());
			bb1.setDeleted_by("NA");
			bb1.setDeleted_on(ldt);
		}
		if(!bMA.isEmpty()) {
			iMaster.setBroker_master_add(bMA.iterator().next());
		}
		
		broker_master_add_dtlsRepository.broker_master_add_dtlsupdate(iMaster.getBroker_Id(),"UPDATED");
		
		Set<Broker_master_add_dtls> bMAD=new HashSet<Broker_master_add_dtls>();
		bMAD.addAll(iMaster.getBroker_master_add_dtls());
		for(Broker_master_add_dtls bb2:bMAD) 
		{
			bb2.setBroker_code(iMaster.getBroker_code());
			bb2.setBroker_Id(iMaster.getBroker_Id());
			bb2.setCompany_id(iMaster.getCompany_id());
			bb2.setFin_year(iMaster.getFin_year());
			bb2.setModified_type("INSERTED");
			bb2.setInserted_by(iMaster.getInserted_by());
			bb2.setInserted_on(iMaster.getInserted_on());
			bb2.setUpdated_by(iMaster.getUpdated_by());
			bb2.setUpdated_on(iMaster.getUpdated_on());
			bb2.setDeleted_by("NA");
			bb2.setDeleted_on(ldt);
		}
		
		iMaster.setBroker_master_add_dtls(bMAD);
		
		broker_master_ptyRepository.broker_master_ptyupdate(iMaster.getBroker_Id(),"UPDATED");
		
		Set<Broker_master_pty> bMP=new HashSet<Broker_master_pty>();
		bMP.addAll(iMaster.getBroker_master_pty());
		for(Broker_master_pty bb3:bMP) 
		{
			bb3.setBroker_code(iMaster.getBroker_code());
			bb3.setBroker_Id(iMaster.getBroker_Id());
			if(Utility.isNullOrEmpty(bb3.getPty_code_name())) {
				bb3.setPty_name(cust_bussiness_partnerRepository.getCustomer(bb3.getPty_code_name()).getCp_name());
			}else {bb3.setPty_name("None");}
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
		iMaster.setBroker_master_pty(bMP);
		
		broker_master_vdrRepository.broker_master_vdrupdate(iMaster.getBroker_Id(),"UPDATED");
		
		Set<Broker_master_vdr> bMV=new HashSet<Broker_master_vdr>();
		bMV.addAll(iMaster.getBroker_master_vdr());
		for(Broker_master_vdr bb4:bMV) 
		{
			bb4.setBroker_code(iMaster.getBroker_code());
			bb4.setBroker_Id(iMaster.getBroker_Id());
			if(Utility.isNullOrEmpty(bb4.getVdr_code_name())) {
				System.out.println("vencode :: "+bb4.getVdr_code_name());
				if(bb4.getVdr_code_name().contains("SBP")) 
				{
					bb4.setVdr_name(supp_bussiness_partnerRepository.getSupplierName(bb4.getVdr_code_name()).getBp_name());
				}
				if(bb4.getVdr_code_name().contains("CBP")) 
				{
					bb4.setVdr_name(cust_bussiness_partnerRepository.getCustomer(bb4.getVdr_code_name()).getCp_name());
				}
				
			}
			else
			{
				bb4.setVdr_name("None");
				}
			bb4.setCompany_id(iMaster.getCompany_id());
			bb4.setFin_year(iMaster.getFin_year());
			bb4.setModified_type("INSERTED");
			bb4.setInserted_by(iMaster.getInserted_by());
			bb4.setInserted_on(iMaster.getInserted_on());
			bb4.setUpdated_by(iMaster.getUpdated_by());
			bb4.setUpdated_on(iMaster.getUpdated_on());
			bb4.setDeleted_by("NA");
			bb4.setDeleted_on(ldt);
			
			
			Supp_bussiness_partner_broker supbroker=new Supp_bussiness_partner_broker();
			
			supbroker.setBp_Id(bb4.getVdr_code_name());
			supbroker.setBased_on(bb4.getBased_on());
			supbroker.setBasis(bb4.getBasis());
			supbroker.setBrokerage_acc(bb4.getBrokerage_acc());
			supbroker.setCompany_id(bb4.getCompany_id());
			supbroker.setDeleted_by("NA");
			supbroker.setDeleted_on(ldt);
			//supbroker.setEff_date(bb4.getEff_date());
			supbroker.setFin_year(iMaster.getFin_year());
			supbroker.setInserted_by(iMaster.getInserted_by());
			supbroker.setInserted_on(iMaster.getInserted_on());
			supbroker.setModified_type("INSERTED");
			supbroker.setRate(bb4.getRate());
			supbroker.setRemarks(bb4.getRemarks());
			//supbroker.setSl_no(bb4.getSrl_no());
			supbroker.setTds_acc(bb4.getTds_acc());
			supbroker.setTds_rate(bb4.getTds_rate());
			supbroker.setUpdated_by(iMaster.getUpdated_by());
			supbroker.setUpdated_on(iMaster.getUpdated_on());
			
			
			supbroker.setVen_code_name(bb4.getBroker_Id());
			supbroker.setVen_name(iMaster.getName());
			
		
			bb4.setSupp_bussiness_partner_broker(supbroker);
		}
		
		iMaster.setBroker_master_vdr(bMV);
		
		broker_master_transporterRepository.broker_master_transporterupdate(iMaster.getBroker_Id(),"UPDATED");
		
		Set<Broker_master_transporter> bMT=new HashSet<Broker_master_transporter>();
		bMT.addAll(iMaster.getBroker_master_transporter());
		for(Broker_master_transporter bb5:bMT) 
		{
			bb5.setBroker_code(iMaster.getBroker_code());
			bb5.setBroker_Id(iMaster.getBroker_Id());
			if(Utility.isNullOrEmpty(bb5.getTrans_code_name())) {
				bb5.setTrans_name(trans_bussiness_partnerRepository.bpNameById(bb5.getTrans_code_name()).getBp_name());
			}else {bb5.setTrans_name("None");}
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
		iMaster.setBroker_master_transporter(bMT);
		
		broker_master_accountRepository.broker_master_accountupdate(iMaster.getBroker_Id(),"UPDATED");
		
		Set<Broker_master_account> bMAcc=new HashSet<Broker_master_account>();
		bMAcc.add(iMaster.getBroker_master_account());
		for(Broker_master_account bb6:bMAcc) 
		{
			bb6.setBroker_code(iMaster.getBroker_code());
			bb6.setBroker_Id(iMaster.getBroker_Id());
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
		if(!bMAcc.isEmpty()) {
			iMaster.setBroker_master_account(bMAcc.iterator().next());
		}
		
		broker_master_othRepository.broker_master_othupdate(iMaster.getBroker_Id(),"UPDATED");
		
		Set<Broker_master_oth> bMO=new HashSet<Broker_master_oth>();
		bMO.addAll(iMaster.getBroker_master_oth());
		for(Broker_master_oth bb7:bMO) 
		{
			bb7.setBroker_code(iMaster.getBroker_code());
			bb7.setBroker_Id(iMaster.getBroker_Id());
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
		iMaster.setBroker_master_oth(bMO);
		
		broker_master_statutoryRepository.broker_master_statutoryupdate(iMaster.getBroker_Id(),"UPDATED");
		
		Set<Broker_master_statutory> bMS=new HashSet<Broker_master_statutory>();
		bMS.add(iMaster.getBroker_master_statutory());
		for(Broker_master_statutory bb8:bMS) 
		{
			bb8.setBroker_code(iMaster.getBroker_code());
			bb8.setBroker_Id(iMaster.getBroker_Id());
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
		if(!bMS.isEmpty()) {
			iMaster.setBroker_master_statutory(bMS.iterator().next());
		}
		
		broker_master_docRepository.broker_master_docupdate(iMaster.getBroker_Id(),"UPDATED");
		
		Set<Broker_master_doc> bM12=new HashSet<Broker_master_doc>();
		bM12.addAll(iMaster.getBroker_master_doc());
		for(Broker_master_doc bb9:bM12) 
		{
			bb9.setBroker_code(iMaster.getBroker_code());
			bb9.setBroker_Id(iMaster.getBroker_Id());
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
		iMaster.setBroker_master_doc(bM12);
		
		return  broker_masterRepository.save(iMaster);
	}
	
	@Transactional
	public Broker_master deleteBrokerMaster (Broker_master bMaster,Long id)
	{
		Optional<Broker_master> op = broker_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Broker_master broker_master=op.get();
		
		if(Utility.isNullOrEmpty(bMaster.getGroup_type())) {
			bMaster.setGroup_type_name(broker_groupRepository.getBroParentGroup(bMaster.getGroup_type(),bMaster.getCompany_id()).getGroup_name());
		}else {bMaster.setGroup_type_name("None");}
		
		broker_master.setInserted_by(op.get().getInserted_by());
		broker_master.setInserted_on(op.get().getInserted_on());
		broker_master.setUpdated_by("NA");
		broker_master.setUpdated_on(ldt);
		broker_master.setDeleted_by(userRepository.getUserDetails(bMaster.getUsername()).getName());
		broker_master.setDeleted_on(ldt);
		
		broker_master.setModified_type("DELETED");
		
		broker_master_docRepository.broker_master_docupdate(op.get().getBroker_Id(),"DELETED");
		broker_master_statutoryRepository.broker_master_statutoryupdate(op.get().getBroker_Id(),"DELETED");
		broker_master_othRepository.broker_master_othupdate(op.get().getBroker_Id(),"DELETED");
		broker_master_accountRepository.broker_master_accountupdate(op.get().getBroker_Id(),"DELETED");
		broker_master_transporterRepository.broker_master_transporterupdate(op.get().getBroker_Id(),"DELETED");
		broker_master_vdrRepository.broker_master_vdrupdate(op.get().getBroker_Id(),"DELETED");
		broker_master_ptyRepository.broker_master_ptyupdate(op.get().getBroker_Id(),"DELETED");
		broker_master_add_dtlsRepository.broker_master_add_dtlsupdate(op.get().getBroker_Id(),"DELETED");
		broker_master_addRepository.broker_master_addupdate(op.get().getBroker_Id(),"DELETED");
		
		if(op.isPresent()) {
			broker_master.setId(id);
		}
		return  broker_masterRepository.save(broker_master);
	}
	
	public List<Broker_master> findAll()
	{
		List<Broker_master> brokerList=new ArrayList<Broker_master>();
		brokerList.addAll(broker_masterRepository.findAll());
		
		List<Broker_master> allData = brokerList
				  .parallelStream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Broker_master::getBroker_Id).reversed())
				  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<Map<String,Object>> getBrokersFastApi(String company)
	{
		List<Map<String,Object>> brokerlist=new ArrayList<Map<String,Object>>();
		
		brokerlist.addAll(broker_masterRepository.getBrokersFastApi(company));
		
		return brokerlist; 
	}
	
	public List<Broker_master> findBrokers(String searchtext)
	{
		List<Broker_master> brokerList=new ArrayList<Broker_master>();
		brokerList.addAll(broker_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Broker_master> allData = brokerList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Broker_master::getName))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Broker_master> allData = brokerList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED") &&
							  (c.getBroker_code()+c.getBroker_type()+c.getName()+c.getGroup_type()+c.getTrans_curr()+c.getGroup_type_name()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Broker_master::getName))
					  .collect(Collectors.toList());
			
			return allData;
		}
		
		/*if(searchtext.compareTo("0")==0 || searchtext ==null) {
			brokerList.addAll(broker_masterRepository.findAllBrokers());
		}
		else {
			brokerList.addAll(broker_masterRepository.findBrokers(searchtext));
		}
		
		for(int i=0;i<brokerList.size();i++) {
			if(brokerList.get(i).getGroup_type().compareTo("0") != 0 && brokerList.get(i).getGroup_type().compareTo("") != 0 && brokerList.get(i).getGroup_type() != null) {
				brokerList.get(i).setGroup_type(broker_groupRepository.getBroParentGroup(brokerList.get(i).getGroup_type()).getGroup_name());
			}
		}
		return  brokerList;*/
	}
	
	public List<Broker_masterDTO> brokerMNCList(){
		
	List<Broker_master> modelList=broker_masterRepository.brokerMNCList(true);
	
	modelList.forEach((e->{
		e.setName(e.getName().toUpperCase());
	}));
	
	List<Broker_master> cPartners = modelList
			  .parallelStream()
			  .filter(c ->c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Broker_master::getName))
			  .collect(Collectors.toList());
	
	Type listType=new TypeToken<List<Broker_masterDTO>>() {}.getType();
	
	List<Broker_masterDTO> itemNameList=new ModelMapper().map(cPartners, listType);
	
	return itemNameList;
	}
	
	public List<Broker_masterDTO> getbrokerMsNameList(){
		
		List<Broker_master> modelList=broker_masterRepository.getbrokerMsNameList();
		
		Type listType=new TypeToken<List<Broker_masterDTO>>() {}.getType();
		
		List<Broker_masterDTO> brokerNameList=new ModelMapper().map(modelList, listType);
		
		return brokerNameList;
	}
	
	public Broker_masterDTO brokerNameByCode(String code){
		
		Broker_master modelList=broker_masterRepository.brokerNameByCode(code);
		
		Type listType=new TypeToken<Broker_masterDTO>() {}.getType();
		
		Broker_masterDTO brokerNameList=new ModelMapper().map(modelList, listType);
		
		return brokerNameList;
	}
	
	
	public Broker_masterDTO getBrokerMaster(Long id)
	{
		Broker_master modelList=broker_masterRepository.getBrokerMaster(id);
		
		Type listType=new TypeToken<Broker_masterDTO>() {}.getType();
		
		Broker_masterDTO BrokerMaster=new ModelMapper().map(modelList,listType);
		
		return BrokerMaster;
	}
	
	public List<Broker_master_addDTO> getBrokerMasterAddr(String code)
	{
		System.out.println("code: "+code);
		List<Broker_master_add> modelList=broker_master_addRepository.getBrokerMasterAddr(code);
		
		Type listType=new TypeToken<List<Broker_master_addDTO>>() {}.getType();
		
		List<Broker_master_addDTO> BrokerMasterAddr=new ModelMapper().map(modelList,listType);
		
		return BrokerMasterAddr;	
	}
	
	
	public List<Broker_master_add_dtlsDTO> getBrokerMasterAddrDtls(String code){
		
		List<Broker_master_add_dtls> modelList=broker_master_add_dtlsRepository.getBrokerMasterAddrDtls(code);
		
		Type listType=new TypeToken<List<Broker_master_add_dtlsDTO>>() {}.getType();
		
		List<Broker_master_add_dtlsDTO> brokerList=new ModelMapper().map(modelList,listType);
		
		return brokerList;
	}
	
	
	
	public List<Broker_master_ptyDTO> getBrokerMasterPty(String code){
		
		List<Broker_master_pty> modelList=broker_master_ptyRepository.getBrokerMasterPty(code);
		
			
		Type listType=new TypeToken<List<Broker_master_ptyDTO>>() {}.getType();
		
		List<Broker_master_ptyDTO> brokerPtyList=new ModelMapper().map(modelList,listType);
		
		return brokerPtyList;
	}
	
	
	public List<Broker_master_transporterDTO> getBrokerMasterTransporter(String code){
		
		List<Broker_master_transporter> modelList=broker_master_transporterRepository.getBrokerMasterTransporter(code);
		
			
		Type listType=new TypeToken<List<Broker_master_transporterDTO>>() {}.getType();
		
		List<Broker_master_transporterDTO> brokerTransList=new ModelMapper().map(modelList,listType);
		
		return brokerTransList;
	}
	
	
	public List<Broker_master_vdrDTO> getBrokerMasterVdr(String code){
		
		List<Broker_master_vdr> modelList=broker_master_vdrRepository.getBrokerMasterVdr(code);
		
		Type listType=new TypeToken<List<Broker_master_vdrDTO>>() {}.getType();
		
		List<Broker_master_vdrDTO> brokerVdrList=new ModelMapper().map(modelList,listType);
		
		return brokerVdrList;
	}
	
	
	public List<Map<String, Object>> getBrokerMasterVdrnew(String code)
	{
		return broker_master_vdrRepository.getBrokerMasterVdrnew(code);
	}
	
	 @Autowired
	 Broker_master_statutoryRepository broker_master_statutoryRepository;
	 
	 public Broker_master_statutoryDTO brokerStatutoryRetriveList(String code)
	 {
		 Broker_master_statutory modelList=broker_master_statutoryRepository.brokerStatutoryRetriveList(code);
		 Type listType = new TypeToken<Broker_master_statutoryDTO>() {}.getType();

		 Broker_master_statutoryDTO brokerStatutory= new ModelMapper().map(modelList,listType);
			
		return brokerStatutory;
	 }
	
	 @Autowired
	 Broker_master_accountRepository broker_master_accountRepository;
	 
	 public Broker_master_accountDTO brokerAccountRetriveList(String code)
	 {
		 Broker_master_account modelList=broker_master_accountRepository.brokerAccountRetriveList(code);
		 Type listType = new TypeToken<Broker_master_accountDTO>() {}.getType();

		 Broker_master_accountDTO brokerAccount= new ModelMapper().map(modelList,listType);
			
		return brokerAccount;
	 }
	 
	 public List<Broker_master_othDTO> brokerOthPartnerRetriveList(String code)
		{
			List<Broker_master_oth> modelList=broker_master_othRepository.brokerOthPartnerRetriveList(code);
			
			Type listType=new TypeToken<List<Broker_master_othDTO>>() {}.getType();
			
			List<Broker_master_othDTO> brokerOTP=new ModelMapper().map(modelList,listType);
			
			return brokerOTP;
		}
	 
	 public List<Broker_master_docDTO> brokerDocRetriveList(String code)
		{
			List<Broker_master> modelList=new ArrayList<Broker_master>();
			
			modelList.addAll(broker_masterRepository.brokerDocRetriveList(code));
				
			Type listType=new TypeToken<List<Broker_master_docDTO>>() {}.getType();
			
			List<Broker_master_docDTO> brokerDoc=new ModelMapper().map(modelList,listType);
			
			return brokerDoc;
		}
	 
	 public MessageStatusDTO chkBrokerNameStatus(String bname){
		String result=broker_masterRepository.chkBrokerNameStatus(bname);
		
		if(result == null) {result="NOTEXIST";}
		else {result="EXIST";}
		
		Type listType = new TypeToken<MessageStatusDTO>() {}.getType();
		MessageStatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setGroup_name(result);
		
		return grpStatus;
	}
	 
	 public StatusDTO chkBrokerMasterCodeStatus(String code){
			String result="";
			
			if(broker_masterRepository.chkBrokerMasterCodeStatus(code).isPresent()) {
				result="EXIST";
			}else {
				result="NOTEXIST";
			}
			Type listType = new TypeToken<StatusDTO>() {}.getType();
			StatusDTO statusDTO = new ModelMapper().map(result,listType);
			statusDTO.setStatus(result);
			
			return statusDTO;
		}
	 
	 
		 public List<Broker_master_vdrDTO> bro_supp_updation(String brokercode) 
		 {
			
			 supp_bussiness_partner_brokerRepository.sup_broker(brokercode); 
			 
			 List<Broker_master_vdr> modelList=broker_master_vdrRepository.getBrokerMasterVdr(brokercode);
						
			 Type listType=new TypeToken<List<Broker_master_vdrDTO>>() {}.getType();
						
			 List<Broker_master_vdrDTO> brokerVdrList=new ModelMapper().map(modelList,listType);
						
			 return brokerVdrList;
		 }
		 
		 public StatusDTO checkBrokerMasterUsage(String code){
				String result=broker_masterRepository.checkBrokerMasterUsage(code);
				
				Type listType = new TypeToken<StatusDTO>() {}.getType();
				StatusDTO statusDTO = new ModelMapper().map(result,listType);
				statusDTO.setStatus(result);
				
				return statusDTO;
			}
		 
		 @Transactional
			public Broker_master accountpostingBrokerMaster(long id) 
			{
				Optional<Broker_master> op=this.broker_masterRepository.findById(id);
				Broker_master_add bro_address=broker_master_addRepository.getBrokerMasterAddress(op.get().getBroker_Id());
				Broker_master_statutory bro_statutary=broker_master_statutoryRepository.brokerStatutoryRetriveList(op.get().getBroker_Id());
				Broker_master_account bro_accounts=broker_master_accountRepository.brokerAccountRetriveList(op.get().getBroker_Id());
				
				List<Broker_master_add_dtls> contactdetails=broker_master_add_dtlsRepository.getBrokerMasterAddrDtls(op.get().getBroker_Id());
				System.out.println("over here ");
				
				String output,finalresult,registered;
				Tallyrequest_BrokerMaster tally=new Tallyrequest_BrokerMaster();
				try  
				{
					String address="",gstno="";
					if(Utility.isNullOrEmpty(bro_address.getAddress1())) 
					{
						address = bro_address.getAddress1();
					}
					if(Utility.isNullOrEmpty(bro_address.getAddress2())) 
					{
						address = bro_address.getAddress2();
					}
					if(Utility.isNullOrEmpty(bro_address.getAddress3())) 
					{
						address = bro_address.getAddress3();
					}
					
					System.out.println("over here 12 ");
					
			//address.substring(0, address.length()-1)
					String registerstatus="";
			
				if(Utility.isNullOrEmpty(bro_statutary.getGst_no())) 
				{
					String gstnno=bro_statutary.getGst_no();
					
					if(gstnno.compareToIgnoreCase("null")==0 || gstnno.compareToIgnoreCase("")==0) 
					{
						registerstatus="UnRegistered";
					}
					else {
						gstno = bro_statutary.getGst_no();
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
				}
				else 
				{
					altname="";
				}
				if(address.compareToIgnoreCase("")!=0) 
				{
					address=address.substring(0, address.length()-1);
				}
				String pinno="000000";
				if(Utility.isNullOrEmpty(String.valueOf(bro_address.getPin()))) 
				{
					pinno=String.valueOf(bro_address.getPin());
					System.out.println("here 1 ");
					if(pinno.compareToIgnoreCase("null")==0 || pinno.compareToIgnoreCase("")==0)
					{
						pinno="000000";
					}
					System.out.println("here 1 ");
				}
				else 
				{
					pinno="000000";
				}
//				System.out.println("over here 12 ");
//				System.out.println(op.get().getName()+"  // "+ altname + " // " + address);
//				System.out.println(address+"  // "+bro_address.getState()+" /// "+gstno);
//				System.out.println(String.valueOf(bro_address.getPin()) + " /  " + pinno +"  // "+bro_statutary.getPan_no());
//				System.out.println(registerstatus+"  // "+op.get().getBroker_type()+"  // "+String.valueOf(contactdetails.get(0).getMob_no()));
//				System.out.println(bro_accounts.getIfsc_code()+"  // "+bro_accounts.getAcc_no());
//				
				//System.out.println(op.get().getBp_name()+"  // "+ altname+"  // "+address.substring(0, address.length()-1)+"  // "+sup_address.getState()+"  // "+String.valueOf(sup_address.getPincode())+"  // "+sup_statutary.getPan_no()+"  // "+registerstatus+"  // "+op.get().getBp_type()+"  // "+String.valueOf(contactdetails.get(0).getMobile())+"  // "+sup_accounts.getIfsc()+"  // "+sup_accounts.getAcc_no());
				String partyname=op.get().getName();
				if(partyname.contains("&"))
				{
					partyname=partyname.replaceAll("&","&amp;");
					
				}
				if(altname.contains("&"))
				{
					altname=altname.replaceAll("&","&amp;");
					
				}
				if(address.contains("&"))
				{
					address=address.replaceAll("&","&amp;");
					
				}
				String mobno="0000000000";
				if(Utility.isNullOrEmpty(String.valueOf(contactdetails.get(0).getMob_no()))) 
				{
					mobno=String.valueOf(contactdetails.get(0).getMob_no());
					
					if(mobno.compareToIgnoreCase("null")==0)					
					{
						mobno="0000000000";
					}
				}
				else 
				{
					mobno="0000000000";
				}
				System.out.println("MOB IF : :"+mobno);
					output=tally.SendToTally(partyname, altname,address,bro_address.getState(),pinno,bro_statutary.getPan_no(),registerstatus,op.get().getBroker_type(),mobno,gstno,bro_accounts.getIfsc_code(),bro_accounts.getAcc_no());
					
					System.out.println(output);
					String [] splitoutput = output.split("\\|\\|");
					System.out.println(splitoutput[0] +" / " + splitoutput[1]+"/"+id);
					broker_masterRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
				}
				catch(Exception e)
				{
				
					System.out.println(e);
				}
				
			//	Custom_uom_master custMaster=op.get();
			
				Optional<Broker_master> op1=this.broker_masterRepository.findById(id);
				System.out.println(op.get());
				
				return op1.get();
			}
		 
		 @Transactional
			public Broker_master accountpostingUndoBrokerMaster(long id) 
			{
				try  
				{
					Optional<Broker_master> op=this.broker_masterRepository.findById(id);
					LocalDateTime ldt = LocalDateTime.now();
					broker_masterRepository.exportuomtallyReverse(id,op.get().getInserted_by(),ldt);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				Optional<Broker_master> op1=this.broker_masterRepository.findById(id);
				System.out.println(op1.get());
				return op1.get();
			}	
		 
		 public List<Map<String, Object>> brokerNameListFast()
			{
				return broker_masterRepository.brokerNameListFast(true);
			}
}
