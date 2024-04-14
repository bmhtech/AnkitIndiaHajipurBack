package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_bank_dtls;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_docs;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_exit_clause;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_exit_clause_dyn;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_item_details;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_party_dtls;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_summary;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_summary_dyn;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_terms_con;
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_time_service;
import com.AnkitIndia.jwtauthentication.model.Nonservice_desc_details;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.NongoodsserviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Nongoodsservice_item_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.NongoodstypemasterRepository;
import com.AnkitIndia.jwtauthentication.repository.ServicemasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
public class NongoodsserviceService_Imp implements NongoodsserviceService{

	@Autowired
	NongoodsserviceRepository nongoodsserviceRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	ServicemasterRepository servicemasterRepository;
	
	@Autowired
	NongoodstypemasterRepository nongoodstypemasterRepository;
	
	@Autowired
	Nongoodsservice_item_detailsRepository nongoodsservice_item_detailsRepository;
	
	public List<Map<String,Object>> getNonGoodsServicelist(String finyear)
	{
		List<Map<String,Object>> serviceList = new ArrayList<Map<String,Object>>();
		
		serviceList.addAll(nongoodsserviceRepository.getNonGoodsServicelist(finyear));
		
		return serviceList;
	}
	
	public SequenceIdDTO getServiceNo(boolean check,String fyear)
	{
		long slno=0;
		String prefix="";
		if(check==true)
		{
			prefix="POS";
		}
		else {
			prefix="SOS";
		}
		//System.out.println("fyear:"+fyear);
		String year[] = fyear.split("-");
		
		prefix=prefix+"-"+year[0].substring(2,4)+year[1].substring(2,4)+"-";
		
		slno=Long.parseLong(nongoodsserviceRepository.countId(fyear,check));
	
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public List<Map<String,Object>> getCustomerSupplierList(String bunit,boolean check)
	{
		List<Map<String,Object>> partyList = new ArrayList<Map<String,Object>>();
		
		if(check==true)//supp
		{
			partyList.addAll(supp_bussiness_partnerRepository.getPartyList(bunit));
		}
		else {//cust
			partyList.addAll(cust_bussiness_partnerRepository.getPartyList(bunit));
		}
		
		return partyList;
	}
	
	public List<Map<String,Object>> getServiceList(String servicetype)
	{
		List<Map<String,Object>> serviceList = new ArrayList<Map<String,Object>>();
		
		serviceList.addAll(servicemasterRepository.getServiceList(servicetype)); 
		
		return serviceList;
	}
	
	
	public List<Map<String,Object>> getServiceMasterDetails(String service)
	{
		List<Map<String,Object>> dtlsList = new ArrayList<Map<String,Object>>();
		
		dtlsList.addAll(servicemasterRepository.getServiceDtlsList(service)); 
		
		return dtlsList;
	}
	
	public Map<String,Object> getDescCode(String service)
	{
		Map<String,Object> code = servicemasterRepository.getDescCode(service);

		return code;
	}
	
	@Transactional
	public Nongoodsservice save(Nongoodsservice ngs)
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;
		if(nongoodsserviceRepository.genId() != null ) {
			slno=Long.parseLong(nongoodsserviceRepository.genId());
		}
		String prefix="NGS";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(Utility.isNullOrEmpty(ngs.getB_unit())) {
			ngs.setB_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(ngs.getB_unit()).getBusinessunit_name());
		}else {ngs.setB_unitname("None");}
		
		if(ngs.isService_type() == true)
		{
			if(Utility.isNullOrEmpty(ngs.getParty())) {
				ngs.setParty_name(supp_bussiness_partnerRepository.getSupplierName(ngs.getParty()).getBp_name());
			}else {ngs.setParty_name("None");}
		}
		else
		{
			if(Utility.isNullOrEmpty(ngs.getParty())) {
				ngs.setParty_name(cust_bussiness_partnerRepository.getCustomer(ngs.getParty()).getCp_name());
			}else {ngs.setParty_name("None");}
		}
		
		
		if(Utility.isNullOrEmpty(ngs.getConfirmed_by())) {
			ngs.setConfirmed_by(ngs.getConfirmed_by());
		}else {ngs.setConfirmed_by("NA");}
		
		if(Utility.isNullOrEmpty(ngs.getApproved())) {
			ngs.setApproved(ngs.getApproved());
		}else {ngs.setApproved("NA");}
		
		if(Utility.isNullOrEmpty(ngs.getReason())) {
			ngs.setReason(ngs.getReason());
		}else {ngs.setReason("NA");}
		
		if(Utility.isNullOrEmpty(ngs.getRemarks())) {
			ngs.setRemarks(ngs.getRemarks());
		}else {ngs.setRemarks("NA");}
		
		
		
		
		ngs.setNongoodsserviceid(gen_sno);
		ngs.setModified_type("INSERTED");
		ngs.setInserted_by(userRepository.getUserDetails(ngs.getUsername()).getName());
		ngs.setInserted_on(ldt);
		ngs.setUpdated_by("NA");
		ngs.setUpdated_on(ldt);
		ngs.setDeleted_by("NA");
		ngs.setDeleted_on(ldt);
		
		Set<Nongoodsservice_item_details> itemDetails=new HashSet<Nongoodsservice_item_details>();
		itemDetails.addAll(ngs.getNongoodsservice_item_details());
		for(Nongoodsservice_item_details item:itemDetails) 
		{
			
			item.setNongoodsserviceid(gen_sno);
			
			if(item.getService_types().compareTo("")!=0 && item.getService_types().compareTo("0")!=0) {
				item.setService_types_name(nongoodstypemasterRepository.getNonGoodsServiceTypeName(item.getService_types()).getTypename());
			}else {item.setService_types_name("None");}

			if(item.getServices().compareTo("")!=0 && item.getServices().compareTo("0")!=0) {
				item.setServices_name(servicemasterRepository.getDescNameByCode(item.getServices()).getDescription());
			}else {item.setServices_name("None");}
			
			item.setCompany_id(ngs.getCompany_id());
			item.setFin_year(ngs.getFin_year());
			item.setModified_type("INSERTED");
			item.setInserted_by(ngs.getInserted_by());
			item.setInserted_on(ngs.getInserted_on());
			item.setUpdated_by(ngs.getUpdated_by());
			item.setUpdated_on(ngs.getUpdated_on());
			item.setDeleted_by("NA");
			item.setDeleted_on(ldt);
			
			Set<Nonservice_desc_details> itemDescDetails=new HashSet<Nonservice_desc_details>();
			itemDescDetails.addAll(item.getNonservice_desc_details());
			for(Nonservice_desc_details descDetails:itemDescDetails) 
			{
				descDetails.setNongoodsserviceid(gen_sno);
				descDetails.setCompany_id(ngs.getCompany_id());
				descDetails.setFin_year(ngs.getFin_year());
				descDetails.setModified_type("INSERTED");
				descDetails.setInserted_by(ngs.getInserted_by());
				descDetails.setInserted_on(ngs.getInserted_on());
				descDetails.setUpdated_by(ngs.getUpdated_by());
				descDetails.setUpdated_on(ngs.getUpdated_on());
				descDetails.setDeleted_by("NA");
				descDetails.setDeleted_on(ldt);
			}
			item.setNonservice_desc_details(itemDescDetails);
		}
		ngs.setNongoodsservice_item_details(itemDetails);
		
		Set<Nongoodsservice_terms_con> termsCon = new HashSet<Nongoodsservice_terms_con>();
		termsCon.add(ngs.getNongoodsservice_terms_con());
		for(Nongoodsservice_terms_con terms : termsCon)
		{
			terms.setNongoodsserviceid(gen_sno);
			terms.setCompany_id(ngs.getCompany_id());
			terms.setFin_year(ngs.getFin_year());
			terms.setModified_type("INSERTED");
			terms.setInserted_by(ngs.getInserted_by());
			terms.setInserted_on(ngs.getInserted_on());
			terms.setUpdated_by("NA");
			terms.setUpdated_on(ldt);
			terms.setDeleted_by("NA");
			terms.setDeleted_on(ldt);
		}
		if(!termsCon.isEmpty())
		{
			ngs.setNongoodsservice_terms_con(termsCon.iterator().next());
		}
		
		Set<Nongoodsservice_party_dtls> partyDetails=new HashSet<Nongoodsservice_party_dtls>();
		partyDetails.addAll(ngs.getNongoodsservice_party_dtls());
		for(Nongoodsservice_party_dtls party:partyDetails) 
		{
			party.setNongoodsserviceid(gen_sno);
			party.setCompany_id(ngs.getCompany_id());
			party.setFin_year(ngs.getFin_year());
			party.setModified_type("INSERTED");
			party.setInserted_by(ngs.getInserted_by());
			party.setInserted_on(ngs.getInserted_on());
			party.setUpdated_by(ngs.getUpdated_by());
			party.setUpdated_on(ngs.getUpdated_on());
			party.setDeleted_by("NA");
			party.setDeleted_on(ldt);
		}
		ngs.setNongoodsservice_party_dtls(partyDetails);
		
		Set<Nongoodsservice_bank_dtls> bankDetalis = new HashSet<Nongoodsservice_bank_dtls>();
		bankDetalis.add(ngs.getNongoodsservice_bank_dtls());
		for(Nongoodsservice_bank_dtls bank : bankDetalis)
		{
			bank.setNongoodsserviceid(gen_sno);
			bank.setCompany_id(ngs.getCompany_id());
			bank.setFin_year(ngs.getFin_year());
			bank.setModified_type("INSERTED");
			bank.setInserted_by(ngs.getInserted_by());
			bank.setInserted_on(ngs.getInserted_on());
			bank.setUpdated_by("NA");
			bank.setUpdated_on(ldt);
			bank.setDeleted_by("NA");
			bank.setDeleted_on(ldt);
		}
		if(!bankDetalis.isEmpty())
		{
			ngs.setNongoodsservice_bank_dtls(bankDetalis.iterator().next());
		}
		
		Set<Nongoodsservice_summary> summary = new HashSet<Nongoodsservice_summary>();
		summary.add(ngs.getNongoodsservice_summary());
		for(Nongoodsservice_summary summ : summary)
		{
			summ.setNongoodsserviceid(gen_sno);
			summ.setCompany_id(ngs.getCompany_id());
			summ.setFin_year(ngs.getFin_year());
			summ.setModified_type("INSERTED");
			summ.setInserted_by(ngs.getInserted_by());
			summ.setInserted_on(ngs.getInserted_on());
			summ.setUpdated_by("NA");
			summ.setUpdated_on(ldt);
			summ.setDeleted_by("NA");
			summ.setDeleted_on(ldt);
		}
		if(!summary.isEmpty())
		{
			ngs.setNongoodsservice_summary(summary.iterator().next());
		}
		
		Set<Nongoodsservice_summary_dyn> sumDetails=new HashSet<Nongoodsservice_summary_dyn>();
		sumDetails.addAll(ngs.getNongoodsservice_summary_dyn());
		for(Nongoodsservice_summary_dyn sum:sumDetails) 
		{
			sum.setNongoodsserviceid(gen_sno);
			sum.setCompany_id(ngs.getCompany_id());
			sum.setFin_year(ngs.getFin_year());
			sum.setModified_type("INSERTED");
			sum.setInserted_by(ngs.getInserted_by());
			sum.setInserted_on(ngs.getInserted_on());
			sum.setUpdated_by(ngs.getUpdated_by());
			sum.setUpdated_on(ngs.getUpdated_on());
			sum.setDeleted_by("NA");
			sum.setDeleted_on(ldt);
		}
		ngs.setNongoodsservice_summary_dyn(sumDetails);
		
		Set<Nongoodsservice_time_service> timeService=new HashSet<Nongoodsservice_time_service>();
		timeService.addAll(ngs.getNongoodsservice_time_service());
		for(Nongoodsservice_time_service time:timeService) 
		{
			time.setNongoodsserviceid(gen_sno);
			time.setCompany_id(ngs.getCompany_id());
			time.setFin_year(ngs.getFin_year());
			time.setModified_type("INSERTED");
			time.setInserted_by(ngs.getInserted_by());
			time.setInserted_on(ngs.getInserted_on());
			time.setUpdated_by(ngs.getUpdated_by());
			time.setUpdated_on(ngs.getUpdated_on());
			time.setDeleted_by("NA");
			time.setDeleted_on(ldt);
		}
		ngs.setNongoodsservice_time_service(timeService);
		
		Set<Nongoodsservice_docs> documents=new HashSet<Nongoodsservice_docs>();
		documents.addAll(ngs.getNongoodsservice_docs());
		for(Nongoodsservice_docs doc:documents) 
		{
			doc.setNongoodsserviceid(gen_sno);
			doc.setCompany_id(ngs.getCompany_id());
			doc.setFin_year(ngs.getFin_year());
			doc.setModified_type("INSERTED");
			doc.setInserted_by(ngs.getInserted_by());
			doc.setInserted_on(ngs.getInserted_on());
			doc.setUpdated_by(ngs.getUpdated_by());
			doc.setUpdated_on(ngs.getUpdated_on());
			doc.setDeleted_by("NA");
			doc.setDeleted_on(ldt);
		}
		ngs.setNongoodsservice_docs(documents);
		
		Set<Nongoodsservice_exit_clause> exitClause = new HashSet<Nongoodsservice_exit_clause>();
		exitClause.add(ngs.getNongoodsservice_exit_clause());
		for(Nongoodsservice_exit_clause clause : exitClause)
		{
			clause.setNongoodsserviceid(gen_sno);
			clause.setCompany_id(ngs.getCompany_id());
			clause.setFin_year(ngs.getFin_year());
			clause.setModified_type("INSERTED");
			clause.setInserted_by(ngs.getInserted_by());
			clause.setInserted_on(ngs.getInserted_on());
			clause.setUpdated_by("NA");
			clause.setUpdated_on(ldt);
			clause.setDeleted_by("NA");
			clause.setDeleted_on(ldt);
		}
		if(!exitClause.isEmpty())
		{
			ngs.setNongoodsservice_exit_clause(exitClause.iterator().next());
		}
		
		Set<Nongoodsservice_exit_clause_dyn> exitClauseDetails=new HashSet<Nongoodsservice_exit_clause_dyn>();
		exitClauseDetails.addAll(ngs.getNongoodsservice_exit_clause_dyn());
		for(Nongoodsservice_exit_clause_dyn exitDetails:exitClauseDetails) 
		{
			System.out.println("tot amt:"+exitDetails.getTotal_amount());
			exitDetails.setNongoodsserviceid(gen_sno);
			exitDetails.setTotal_amount(exitDetails.getTotal_amount());
			exitDetails.setCompany_id(ngs.getCompany_id());
			exitDetails.setFin_year(ngs.getFin_year());
			exitDetails.setModified_type("INSERTED");
			exitDetails.setInserted_by(ngs.getInserted_by());
			exitDetails.setInserted_on(ngs.getInserted_on());
			exitDetails.setUpdated_by(ngs.getUpdated_by());
			exitDetails.setUpdated_on(ngs.getUpdated_on());
			exitDetails.setDeleted_by("NA");
			exitDetails.setDeleted_on(ldt);
		}
		ngs.setNongoodsservice_exit_clause_dyn(exitClauseDetails);
		
		return nongoodsserviceRepository.save(ngs);
	}
	

	@Transactional
	public Nongoodsservice update(Nongoodsservice ngs,long id)
	{
		Optional<Nongoodsservice> op = nongoodsserviceRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(ngs.getB_unit())) {
			ngs.setB_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(ngs.getB_unit()).getBusinessunit_name());
		}else {ngs.setB_unitname("None");}
		
		if(ngs.isService_type() == true)
		{
			if(Utility.isNullOrEmpty(ngs.getParty())) {
				ngs.setParty_name(supp_bussiness_partnerRepository.getSupplierName(ngs.getParty()).getBp_name());
			}else {ngs.setParty_name("None");}
		}
		else
		{
			if(Utility.isNullOrEmpty(ngs.getParty())) {
				ngs.setParty_name(cust_bussiness_partnerRepository.getCustomer(ngs.getParty()).getCp_name());
			}else {ngs.setParty_name("None");}
		}
		
		
		if(Utility.isNullOrEmpty(ngs.getConfirmed_by())) {
			ngs.setConfirmed_by(ngs.getConfirmed_by());
		}else {ngs.setConfirmed_by("NA");}
		
		if(Utility.isNullOrEmpty(ngs.getApproved())) {
			ngs.setApproved(ngs.getApproved());
		}else {ngs.setApproved("NA");}
		
		if(Utility.isNullOrEmpty(ngs.getReason())) {
			ngs.setReason(ngs.getReason());
		}else {ngs.setReason("NA");}
		
		if(Utility.isNullOrEmpty(ngs.getRemarks())) {
			ngs.setRemarks(ngs.getRemarks());
		}else {ngs.setRemarks("NA");}
		
		ngs.setNongoodsserviceid(op.get().getNongoodsserviceid());
		ngs.setModified_type("INSERTED");
		ngs.setInserted_by(op.get().getInserted_by());
		ngs.setInserted_on(op.get().getInserted_on());
		ngs.setUpdated_by(userRepository.getUserDetails(ngs.getUsername()).getName());
		ngs.setUpdated_on(ldt);
		ngs.setDeleted_by("NA");
		ngs.setDeleted_on(ldt);
		
		//Update item
		nongoodsservice_item_detailsRepository.updateItemDetails(op.get().getNongoodsserviceid());
		
		Set<Nongoodsservice_item_details> itemdata=new HashSet<Nongoodsservice_item_details>();
		itemdata.addAll(ngs.getNongoodsservice_item_details());
		for(Nongoodsservice_item_details itemdetails:itemdata) 
		{
			itemdetails.setNongoodsserviceid(op.get().getNongoodsserviceid());
			
			if(itemdetails.getService_types().compareTo("")!=0 && itemdetails.getService_types().compareTo("0")!=0) {
				itemdetails.setService_types_name(nongoodstypemasterRepository.getNonGoodsServiceTypeName(itemdetails.getService_types()).getTypename());
			}else {itemdetails.setService_types_name("None");}

			if(itemdetails.getServices().compareTo("")!=0 && itemdetails.getServices().compareTo("0")!=0) {
				itemdetails.setServices_name(servicemasterRepository.getDescNameByCode(itemdetails.getServices()).getDescription());
			}else {itemdetails.setServices_name("None");}
			
			itemdetails.setCompany_id(ngs.getCompany_id());
			itemdetails.setFin_year(ngs.getFin_year());
			itemdetails.setModified_type("INSERTED");
			itemdetails.setInserted_by(ngs.getInserted_by());
			itemdetails.setInserted_on(ngs.getInserted_on());
			itemdetails.setUpdated_by(ngs.getUpdated_by());
			itemdetails.setUpdated_on(ngs.getUpdated_on());
			itemdetails.setDeleted_by("NA");
			itemdetails.setDeleted_on(ldt);
			
			//Update service
			nongoodsservice_item_detailsRepository.updateItemServiceDetails(op.get().getNongoodsserviceid(),itemdetails.getServices());
			
			Set<Nonservice_desc_details> servicedata=new HashSet<Nonservice_desc_details>();
			servicedata.addAll(itemdetails.getNonservice_desc_details());
			for(Nonservice_desc_details service:servicedata) 
			{
				service.setNongoodsserviceid(op.get().getNongoodsserviceid());
				
				service.setCompany_id(ngs.getCompany_id());
				service.setFin_year(ngs.getFin_year());
				service.setModified_type("INSERTED");
				service.setInserted_by(ngs.getInserted_by());
				service.setInserted_on(ngs.getInserted_on());
				service.setUpdated_by(ngs.getUpdated_by());
				service.setUpdated_on(ngs.getUpdated_on());
				service.setDeleted_by("NA");
				service.setDeleted_on(ldt);
			}
			itemdetails.setNonservice_desc_details(servicedata);
		}
		ngs.setNongoodsservice_item_details(itemdata);
		
		//Update terms condition
		nongoodsserviceRepository.updateTermsConditiont(op.get().getNongoodsserviceid());
		
		Set<Nongoodsservice_terms_con> termscondata=new HashSet<Nongoodsservice_terms_con>();
		termscondata.add(ngs.getNongoodsservice_terms_con());
		for(Nongoodsservice_terms_con termscon:termscondata) 
		{
			termscon.setNongoodsserviceid(op.get().getNongoodsserviceid());
			termscon.setCompany_id(ngs.getCompany_id());
			termscon.setFin_year(ngs.getFin_year());
			termscon.setModified_type("INSERTED");
			termscon.setInserted_by(ngs.getInserted_by());
			termscon.setInserted_on(ngs.getInserted_on());
			termscon.setUpdated_by(ngs.getUpdated_by());
			termscon.setUpdated_on(ngs.getUpdated_on());
			termscon.setDeleted_by("NA");
			termscon.setDeleted_on(ldt);
		}
		if(!termscondata.isEmpty())
		{
			ngs.setNongoodsservice_terms_con(termscondata.iterator().next());
		}
		
		//Update party datails
		nongoodsserviceRepository.updatePartyDetails(op.get().getNongoodsserviceid());
		
		Set<Nongoodsservice_party_dtls> partydata=new HashSet<Nongoodsservice_party_dtls>();
		partydata.addAll(ngs.getNongoodsservice_party_dtls());
		for(Nongoodsservice_party_dtls party:partydata) 
		{
			party.setNongoodsserviceid(op.get().getNongoodsserviceid());
			party.setCompany_id(ngs.getCompany_id());
			party.setFin_year(ngs.getFin_year());
			party.setModified_type("INSERTED");
			party.setInserted_by(ngs.getInserted_by());
			party.setInserted_on(ngs.getInserted_on());
			party.setUpdated_by(ngs.getUpdated_by());
			party.setUpdated_on(ngs.getUpdated_on());
			party.setDeleted_by("NA");
			party.setDeleted_on(ldt);
		}
		ngs.setNongoodsservice_party_dtls(partydata);
		
		
		//Update Bank Details
		nongoodsserviceRepository.updateBankDetails(op.get().getNongoodsserviceid());
		
		Set<Nongoodsservice_bank_dtls> bankdata=new HashSet<Nongoodsservice_bank_dtls>();
		bankdata.add(ngs.getNongoodsservice_bank_dtls());
		for(Nongoodsservice_bank_dtls bank:bankdata) 
		{
			bank.setNongoodsserviceid(op.get().getNongoodsserviceid());
			bank.setCompany_id(ngs.getCompany_id());
			bank.setFin_year(ngs.getFin_year());
			bank.setModified_type("INSERTED");
			bank.setInserted_by(ngs.getInserted_by());
			bank.setInserted_on(ngs.getInserted_on());
			bank.setUpdated_by(ngs.getUpdated_by());
			bank.setUpdated_on(ngs.getUpdated_on());
			bank.setDeleted_by("NA");
			bank.setDeleted_on(ldt);
		}
		if(!bankdata.isEmpty())
		{
			ngs.setNongoodsservice_bank_dtls(bankdata.iterator().next());
		}
				
		//Update Summary
		nongoodsserviceRepository.updateSummary(op.get().getNongoodsserviceid());
		
		Set<Nongoodsservice_summary> summarydata=new HashSet<Nongoodsservice_summary>();
		summarydata.add(ngs.getNongoodsservice_summary());
		for(Nongoodsservice_summary summary:summarydata) 
		{
			summary.setNongoodsserviceid(op.get().getNongoodsserviceid());
			summary.setCompany_id(ngs.getCompany_id());
			summary.setFin_year(ngs.getFin_year());
			summary.setModified_type("INSERTED");
			summary.setInserted_by(ngs.getInserted_by());
			summary.setInserted_on(ngs.getInserted_on());
			summary.setUpdated_by(ngs.getUpdated_by());
			summary.setUpdated_on(ngs.getUpdated_on());
			summary.setDeleted_by("NA");
			summary.setDeleted_on(ldt);
		}
		if(!summarydata.isEmpty())
		{
			ngs.setNongoodsservice_summary(summarydata.iterator().next());
		}
	
		//Update Summary datails
		nongoodsserviceRepository.updateSummaryDyn(op.get().getNongoodsserviceid());
		
		Set<Nongoodsservice_summary_dyn> summarydyndata=new HashSet<Nongoodsservice_summary_dyn>();
		summarydyndata.addAll(ngs.getNongoodsservice_summary_dyn());
		for(Nongoodsservice_summary_dyn summarydyn:summarydyndata) 
		{
			summarydyn.setNongoodsserviceid(op.get().getNongoodsserviceid());
			summarydyn.setCompany_id(ngs.getCompany_id());
			summarydyn.setFin_year(ngs.getFin_year());
			summarydyn.setModified_type("INSERTED");
			summarydyn.setInserted_by(ngs.getInserted_by());
			summarydyn.setInserted_on(ngs.getInserted_on());
			summarydyn.setUpdated_by(ngs.getUpdated_by());
			summarydyn.setUpdated_on(ngs.getUpdated_on());
			summarydyn.setDeleted_by("NA");
			summarydyn.setDeleted_on(ldt);
		}
		ngs.setNongoodsservice_summary_dyn(summarydyndata);
		
		//Update Time Service
		nongoodsserviceRepository.updateTimeService(op.get().getNongoodsserviceid());
		
		Set<Nongoodsservice_time_service> timeservicedata=new HashSet<Nongoodsservice_time_service>();
		timeservicedata.addAll(ngs.getNongoodsservice_time_service());
		for(Nongoodsservice_time_service timeservice:timeservicedata) 
		{
			timeservice.setNongoodsserviceid(op.get().getNongoodsserviceid());
			timeservice.setCompany_id(ngs.getCompany_id());
			timeservice.setFin_year(ngs.getFin_year());
			timeservice.setModified_type("INSERTED");
			timeservice.setInserted_by(ngs.getInserted_by());
			timeservice.setInserted_on(ngs.getInserted_on());
			timeservice.setUpdated_by(ngs.getUpdated_by());
			timeservice.setUpdated_on(ngs.getUpdated_on());
			timeservice.setDeleted_by("NA");
			timeservice.setDeleted_on(ldt);
		}
		ngs.setNongoodsservice_time_service(timeservicedata);
		
		//Update Documents
		nongoodsserviceRepository.updateDocuments(op.get().getNongoodsserviceid());
		
		Set<Nongoodsservice_docs> documentsdata=new HashSet<Nongoodsservice_docs>();
		documentsdata.addAll(ngs.getNongoodsservice_docs());
		for(Nongoodsservice_docs documents:documentsdata) 
		{
			documents.setNongoodsserviceid(op.get().getNongoodsserviceid());
			documents.setCompany_id(ngs.getCompany_id());
			documents.setFin_year(ngs.getFin_year());
			documents.setModified_type("INSERTED");
			documents.setInserted_by(ngs.getInserted_by());
			documents.setInserted_on(ngs.getInserted_on());
			documents.setUpdated_by(ngs.getUpdated_by());
			documents.setUpdated_on(ngs.getUpdated_on());
			documents.setDeleted_by("NA");
			documents.setDeleted_on(ldt);
		}
		ngs.setNongoodsservice_docs(documentsdata);
		
		//Update Exit Clause
		nongoodsserviceRepository.updateExitclause(op.get().getNongoodsserviceid());
		
		Set<Nongoodsservice_exit_clause> exitclausedata=new HashSet<Nongoodsservice_exit_clause>();
		exitclausedata.add(ngs.getNongoodsservice_exit_clause());
		for(Nongoodsservice_exit_clause exitclause:exitclausedata) 
		{
			exitclause.setNongoodsserviceid(op.get().getNongoodsserviceid());
			exitclause.setCompany_id(ngs.getCompany_id());
			exitclause.setFin_year(ngs.getFin_year());
			exitclause.setModified_type("INSERTED");
			exitclause.setInserted_by(ngs.getInserted_by());
			exitclause.setInserted_on(ngs.getInserted_on());
			exitclause.setUpdated_by(ngs.getUpdated_by());
			exitclause.setUpdated_on(ngs.getUpdated_on());
			exitclause.setDeleted_by("NA");
			exitclause.setDeleted_on(ldt);
		}
		if(!exitclausedata.isEmpty())
		{
			ngs.setNongoodsservice_exit_clause(exitclausedata.iterator().next());
		}
		
		//Update Exit Clause Details
		nongoodsserviceRepository.updateExitclauseDyn(op.get().getNongoodsserviceid());
		
		Set<Nongoodsservice_exit_clause_dyn> exitclausedyndata=new HashSet<Nongoodsservice_exit_clause_dyn>();
		exitclausedyndata.addAll(ngs.getNongoodsservice_exit_clause_dyn());
		for(Nongoodsservice_exit_clause_dyn exitclausedyn:exitclausedyndata) 
		{
			exitclausedyn.setNongoodsserviceid(op.get().getNongoodsserviceid());
			exitclausedyn.setCompany_id(ngs.getCompany_id());
			exitclausedyn.setFin_year(ngs.getFin_year());
			exitclausedyn.setModified_type("INSERTED");
			exitclausedyn.setInserted_by(ngs.getInserted_by());
			exitclausedyn.setInserted_on(ngs.getInserted_on());
			exitclausedyn.setUpdated_by(ngs.getUpdated_by());
			exitclausedyn.setUpdated_on(ngs.getUpdated_on());
			exitclausedyn.setDeleted_by("NA");
			exitclausedyn.setDeleted_on(ldt);
		}
		ngs.setNongoodsservice_exit_clause_dyn(exitclausedyndata);
		
		return nongoodsserviceRepository.save(ngs);
	}
	
	@Transactional
	public Nongoodsservice delete(Nongoodsservice nongoodsservice,long id) 
	{
		Optional<Nongoodsservice> op = nongoodsserviceRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Nongoodsservice ngs=op.get();
		
		ngs.setModified_type("DELETED");
		ngs.setInserted_by(op.get().getInserted_by());
		ngs.setInserted_on(op.get().getInserted_on());
		ngs.setUpdated_by(op.get().getUpdated_by());
		ngs.setUpdated_on(op.get().getUpdated_on());
		ngs.setDeleted_by(userRepository.getUserDetails(ngs.getUsername()).getName());
		ngs.setDeleted_on(ldt);
		
		nongoodsservice_item_detailsRepository.revertItem(op.get().getNongoodsserviceid());
		nongoodsservice_item_detailsRepository.revertService(op.get().getNongoodsserviceid());
		nongoodsserviceRepository.revertTermsCon(op.get().getNongoodsserviceid());
		nongoodsserviceRepository.revertParty(op.get().getNongoodsserviceid());
		nongoodsserviceRepository.revertBankDetails(op.get().getNongoodsserviceid());
		nongoodsserviceRepository.revertSummary(op.get().getNongoodsserviceid());
		nongoodsserviceRepository.revertSummaryDetails(op.get().getNongoodsserviceid());
		nongoodsserviceRepository.revertTimeService(op.get().getNongoodsserviceid());
		nongoodsserviceRepository.revertDocuments(op.get().getNongoodsserviceid());
		nongoodsserviceRepository.revertExitClause(op.get().getNongoodsserviceid());
		nongoodsserviceRepository.revertExitClauseDetails(op.get().getNongoodsserviceid());
		
		if(op.isPresent())
		{
			ngs.setId(id);
		}
		
		return nongoodsserviceRepository.save(ngs);
	}
	
	public Nongoodsservice findOne(long id)
	{
		Optional<Nongoodsservice> op=nongoodsserviceRepository.findById(id);
		return op.get();
	}
	
	public List<Nongoodsservice_item_details> retriveNongoodsServiceItem(String nongoodsid)
	{
		List<Nongoodsservice_item_details> itemdetails= new ArrayList<Nongoodsservice_item_details>();
		itemdetails.addAll(nongoodsservice_item_detailsRepository.getItemDetails(nongoodsid));
		return itemdetails;
	}
	
	public List<Nonservice_desc_details> getItemDetailsSerList(String nongoodsid,String serviceno)
	{
		List<Nonservice_desc_details> serdetails= new ArrayList<Nonservice_desc_details>();
		serdetails.addAll(nongoodsservice_item_detailsRepository.getItemServiceDetails(nongoodsid,serviceno));
		return serdetails;
	}
	
	public Nongoodsservice_terms_con retriveNongoodsServiceTermsCon(String nongoodsid)
	{
		Nongoodsservice_terms_con termscon=nongoodsserviceRepository.getTermsCon(nongoodsid);
		return termscon;
	}
	
	public List<Nongoodsservice_party_dtls> retriveNongoodsServiceParty(String nongoodsid)
	{
		List<Nongoodsservice_party_dtls> partydetails= new ArrayList<Nongoodsservice_party_dtls>();
		partydetails.addAll(nongoodsserviceRepository.getPartyDetails(nongoodsid));
		return partydetails;
	}
	
	public Nongoodsservice_bank_dtls retriveNongoodsServiceBankDtls(String nongoodsid)
	{
		Nongoodsservice_bank_dtls bankdetails=nongoodsserviceRepository.getBankDetails(nongoodsid);
		return bankdetails;
	}
	
	public Nongoodsservice_summary retriveNongoodsServiceSummary(String nongoodsid)
	{
		Nongoodsservice_summary summary=nongoodsserviceRepository.getSummary(nongoodsid);
		return summary;
	}
	
	public List<Nongoodsservice_summary_dyn> retriveNongoodsServiceSummaryDyn(String nongoodsid)
	{
		List<Nongoodsservice_summary_dyn> summarydetails= new ArrayList<Nongoodsservice_summary_dyn>();
		summarydetails.addAll(nongoodsserviceRepository.getSummaryDetails(nongoodsid));
		return summarydetails;
	}
	
	public List<Nongoodsservice_time_service> retriveNongoodsServiceTimeService(String nongoodsid)
	{
		List<Nongoodsservice_time_service> timedetails= new ArrayList<Nongoodsservice_time_service>();
		timedetails.addAll(nongoodsserviceRepository.getTimeDetails(nongoodsid));
		return timedetails;
	}
	
	public List<Nongoodsservice_docs> retriveNongoodsServiceDocs(String nongoodsid)
	{
		List<Nongoodsservice_docs> docs= new ArrayList<Nongoodsservice_docs>();
		docs.addAll(nongoodsserviceRepository.getDocuments(nongoodsid));
		return docs;
	}
	
	public Nongoodsservice_exit_clause retriveNongoodsServiceExitClause(String nongoodsid)
	{
		Nongoodsservice_exit_clause exitclause=nongoodsserviceRepository.getExitclause(nongoodsid);
		return exitclause;
	}
	
	public List<Nongoodsservice_exit_clause_dyn> retriveNongoodsServiceExitClauseDyn(String nongoodsid)
	{
		List<Nongoodsservice_exit_clause_dyn> exitclauseDetails= new ArrayList<Nongoodsservice_exit_clause_dyn>();
		exitclauseDetails.addAll(nongoodsserviceRepository.getExitclauseDetails(nongoodsid));
		return exitclauseDetails;
	}
	
}
