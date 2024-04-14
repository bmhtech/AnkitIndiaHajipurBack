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
import com.AnkitIndia.jwtauthentication.model.Nongoodsservice_item_details;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_docs;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_item_details;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_time_service;
import com.AnkitIndia.jwtauthentication.model.Nonservice_desc_details;
import com.AnkitIndia.jwtauthentication.model.Nonservicesrn_desc_details;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.NongoodsserviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Nongoodsservice_item_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.NongoodssrnRepository;
import com.AnkitIndia.jwtauthentication.repository.Nongoodssrn_item_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.NongoodstypemasterRepository;
import com.AnkitIndia.jwtauthentication.repository.ServicemasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.TermasserviceRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;


@Service
public class NongoodssrnService_Imp implements NongoodssrnService{

	@Autowired
	NongoodssrnRepository nongoodssrnRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	NongoodsserviceRepository nongoodsserviceRepository;
	
	@Autowired
	Nongoodsservice_item_detailsRepository nongoodsservice_item_detailsRepository;
	
	@Autowired
	NongoodstypemasterRepository nongoodstypemasterRepository;
	
	@Autowired
	ServicemasterRepository servicemasterRepository;
	
	@Autowired
	TermasserviceRepository termasserviceRepository;
	
	@Autowired
	Nongoodssrn_item_detailsRepository nongoodssrn_item_detailsRepository;
	
	
	
	public SequenceIdDTO getSRNNo(String fyear)
	{
		long slno=0;
		String prefix="";
		prefix="SRN";
		//System.out.println("fyear:"+fyear);
		String year[] = fyear.split("-");
		
		prefix=prefix+"-"+year[0].substring(2,4)+year[1].substring(2,4)+"-";
		
		slno=Long.parseLong(nongoodssrnRepository.countId(fyear));
	
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public List<Map<String,Object>> getSRNlist(String finyear)
	{
		List<Map<String,Object>> srnlist = new ArrayList<Map<String,Object>>();
		
		srnlist.addAll(nongoodssrnRepository.getSRNlist(finyear));
		
		return srnlist;
	}
	
	public List<Map<String,Object>> getNonServiceOrderAllList(String ordertype,String bunit,String party,String srndate)
	{
		//System.out.println("currdate::"+ordertype+"//"+bunit+"//"+party+"//"+srndate);
		List<Map<String,Object>> ordlist = new ArrayList<Map<String,Object>>();
		
		ordlist.addAll(nongoodsserviceRepository.getNonServiceOrderAllList(ordertype,bunit,party,srndate));
		
		return ordlist;
	}
	
	public List<Nongoodsservice_item_details> getServiceItemList(String serviceid)
	{
		List<Nongoodsservice_item_details> itemlist = new ArrayList<Nongoodsservice_item_details>();
		
		itemlist.addAll(nongoodsservice_item_detailsRepository.getItemDetails(serviceid));
		
		return itemlist;
	}
	
	public List<Map<String,Object>> getDescAccordingBillPeriodList(String nongoodsserviceid,String serviceno)
	{
		List<Map<String,Object>> desclist = new ArrayList<Map<String,Object>>();
		
		desclist.addAll(nongoodsservice_item_detailsRepository.getDescAccordingBillPeriodList(nongoodsserviceid,serviceno));
		
		return desclist;
	}
	
	@Transactional
	public Nongoodssrn save(Nongoodssrn srn)
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;
		if(nongoodssrnRepository.genId() != null ) {
			slno=Long.parseLong(nongoodssrnRepository.genId());
		}
		String prefix="SRN";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(Utility.isNullOrEmpty(srn.getB_unit())) {
			srn.setB_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(srn.getB_unit()).getBusinessunit_name());
		}else {srn.setB_unitname("None");}
		
		
		if(Utility.isNullOrEmpty(srn.getParty())) {
			srn.setParty_name(supp_bussiness_partnerRepository.getSupplierName(srn.getParty()).getBp_name());
		}else {srn.setParty_name("None");}
		
		if(Utility.isNullOrEmpty(srn.getRemarks())) {
			srn.setRemarks(srn.getRemarks());
		}else {srn.setRemarks("NA");}
		
		srn.setSrnid(gen_sno);
		srn.setModified_type("INSERTED");
		srn.setInserted_by(userRepository.getUserDetails(srn.getUsername()).getName());
		srn.setInserted_on(ldt);
		srn.setUpdated_by("NA");
		srn.setUpdated_on(ldt);
		srn.setDeleted_by("NA");
		srn.setDeleted_on(ldt);
		
		Set<Nongoodssrn_item_details> itemDetails=new HashSet<Nongoodssrn_item_details>();
		itemDetails.addAll(srn.getNongoodssrn_item_details());
		for(Nongoodssrn_item_details item:itemDetails) 
		{
			
			item.setSrnid(gen_sno);
			item.setNongoodsserviceid(srn.getOrderid());
			if(Utility.isNullOrEmpty(item.getService_types())) {
				item.setService_types_name(nongoodstypemasterRepository.getNonGoodsServiceTypeName(item.getService_types()).getTypename());
			}else {item.setService_types_name("None");}

			if(Utility.isNullOrEmpty(item.getServices())) {
				item.setServices_name(servicemasterRepository.getDescNameByCode(item.getServices()).getDescription());
			}else {item.setServices_name("None");}
			
			item.setCompany_id(srn.getCompany_id());
			item.setFin_year(srn.getFin_year());
			item.setModified_type("INSERTED");
			item.setInserted_by(srn.getInserted_by());
			item.setInserted_on(srn.getInserted_on());
			item.setUpdated_by(srn.getUpdated_by());
			item.setUpdated_on(srn.getUpdated_on());
			item.setDeleted_by("NA");
			item.setDeleted_on(ldt);
			
			Set<Nonservicesrn_desc_details> itemDescDetails=new HashSet<Nonservicesrn_desc_details>();
			itemDescDetails.addAll(item.getNonservicesrn_desc_details());
			for(Nonservicesrn_desc_details descDetails:itemDescDetails) 
			{
				descDetails.setSrnid(gen_sno);
				descDetails.setNongoodsserviceid(srn.getOrderid());
				descDetails.setSrn_date(srn.getSrndate());
				descDetails.setCompany_id(srn.getCompany_id());
				descDetails.setFin_year(srn.getFin_year());
				descDetails.setModified_type("INSERTED");
				descDetails.setInserted_by(srn.getInserted_by());
				descDetails.setInserted_on(srn.getInserted_on());
				descDetails.setUpdated_by(srn.getUpdated_by());
				descDetails.setUpdated_on(srn.getUpdated_on());
				descDetails.setDeleted_by("NA");
				descDetails.setDeleted_on(ldt);
			}
			item.setNonservicesrn_desc_details(itemDescDetails);
		}
		srn.setNongoodssrn_item_details(itemDetails);
		
		Set<Nongoodssrn_time_service> timeservice=new HashSet<Nongoodssrn_time_service>();
		timeservice.addAll(srn.getNongoodssrn_time_service());
		for(Nongoodssrn_time_service time:timeservice) 
		{
			time.setSrnid(gen_sno);
			time.setCompany_id(srn.getCompany_id());
			time.setFin_year(srn.getFin_year());
			time.setModified_type("INSERTED");
			time.setInserted_by(srn.getInserted_by());
			time.setInserted_on(srn.getInserted_on());
			time.setUpdated_by(srn.getUpdated_by());
			time.setUpdated_on(srn.getUpdated_on());
			time.setDeleted_by("NA");
			time.setDeleted_on(ldt);
		}
		srn.setNongoodssrn_time_service(timeservice);
		
		Set<Nongoodssrn_docs> documents=new HashSet<Nongoodssrn_docs>();
		documents.addAll(srn.getNongoodssrn_docs());
		for(Nongoodssrn_docs doc:documents) 
		{
			doc.setSrnid(gen_sno);
			doc.setCompany_id(srn.getCompany_id());
			doc.setFin_year(srn.getFin_year());
			doc.setModified_type("INSERTED");
			doc.setInserted_by(srn.getInserted_by());
			doc.setInserted_on(srn.getInserted_on());
			doc.setUpdated_by(srn.getUpdated_by());
			doc.setUpdated_on(srn.getUpdated_on());
			doc.setDeleted_by("NA");
			doc.setDeleted_on(ldt);
		}
		srn.setNongoodssrn_docs(documents);
		
		return nongoodssrnRepository.save(srn);
	}
	
	public Nongoodssrn findOne(long id)
	{
		Optional<Nongoodssrn> op=nongoodssrnRepository.findById(id);
		return op.get();
	}
	
	public List<Nongoodssrn_item_details> retriveNongoodsSrnItem(String srnid)
	{
		List<Nongoodssrn_item_details> itemdetails= new ArrayList<Nongoodssrn_item_details>();
		itemdetails.addAll(nongoodssrn_item_detailsRepository.getItemDetails(srnid));
		return itemdetails;
	}
	
	public List<Nonservicesrn_desc_details> getSrnItemDetailsSerList(String srnid,String serviceno)
	{
		List<Nonservicesrn_desc_details> serdetails= new ArrayList<Nonservicesrn_desc_details>();
		serdetails.addAll(nongoodssrn_item_detailsRepository.getSrnItemDetailsSerList(srnid,serviceno));
		return serdetails;
	}
	
	public List<Nongoodssrn_time_service> retriveNongoodsSrnTime(String srnid)
	{
		List<Nongoodssrn_time_service> timedetails= new ArrayList<Nongoodssrn_time_service>();
		timedetails.addAll(nongoodssrnRepository.retriveNongoodsSrnTime(srnid));
		return timedetails;
	}
	
	public List<Nongoodssrn_docs> retriveNongoodsSrnDocs(String srnid)
	{
		List<Nongoodssrn_docs> docdetails= new ArrayList<Nongoodssrn_docs>();
		docdetails.addAll(nongoodssrnRepository.getDocDetails(srnid));
		return docdetails;
	}
	@Transactional
	public Nongoodssrn update(Nongoodssrn srn,long id)
	{
		Optional<Nongoodssrn> op = nongoodssrnRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(srn.getB_unit())) {
			srn.setB_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(srn.getB_unit()).getBusinessunit_name());
		}else {srn.setB_unitname("None");}
		
		
		if(Utility.isNullOrEmpty(srn.getParty())) {
			srn.setParty_name(supp_bussiness_partnerRepository.getSupplierName(srn.getParty()).getBp_name());
		}else {srn.setParty_name("None");}
		
		if(Utility.isNullOrEmpty(srn.getRemarks())) {
			srn.setRemarks(srn.getRemarks());
		}else {srn.setRemarks("NA");}
		
		
		
		srn.setSrnid(op.get().getSrnid());
		srn.setModified_type("INSERTED");
		srn.setInserted_by(op.get().getInserted_by());
		srn.setInserted_on(op.get().getInserted_on());
		srn.setUpdated_by(userRepository.getUserDetails(srn.getUsername()).getName());
		srn.setUpdated_on(ldt);
		srn.setDeleted_by("NA");
		srn.setDeleted_on(ldt);
		
		//Update item
		nongoodssrn_item_detailsRepository.updateItemDetails(op.get().getSrnid());
		
		Set<Nongoodssrn_item_details> itemDetails=new HashSet<Nongoodssrn_item_details>();
		itemDetails.addAll(srn.getNongoodssrn_item_details());
		for(Nongoodssrn_item_details item:itemDetails) 
		{
			
			item.setSrnid(op.get().getSrnid());
			item.setNongoodsserviceid(srn.getOrderid());
			if(Utility.isNullOrEmpty(item.getService_types())) {
				item.setService_types_name(nongoodstypemasterRepository.getNonGoodsServiceTypeName(item.getService_types()).getTypename());
			}else {item.setService_types_name("None");}

			if(Utility.isNullOrEmpty(item.getServices())) {
				item.setServices_name(servicemasterRepository.getDescNameByCode(item.getServices()).getDescription());
			}else {item.setServices_name("None");}
			
			item.setCompany_id(srn.getCompany_id());
			item.setFin_year(srn.getFin_year());
			item.setModified_type("INSERTED");
			item.setInserted_by(srn.getInserted_by());
			item.setInserted_on(srn.getInserted_on());
			item.setUpdated_by(srn.getUpdated_by());
			item.setUpdated_on(srn.getUpdated_on());
			item.setDeleted_by("NA");
			item.setDeleted_on(ldt);
			
			//Update service
			nongoodssrn_item_detailsRepository.updateItemServiceDetails(op.get().getSrnid(),item.getServices());
			
			Set<Nonservicesrn_desc_details> itemDescDetails=new HashSet<Nonservicesrn_desc_details>();
			itemDescDetails.addAll(item.getNonservicesrn_desc_details());
			for(Nonservicesrn_desc_details descDetails:itemDescDetails) 
			{
				descDetails.setSrnid(op.get().getSrnid());
				descDetails.setNongoodsserviceid(srn.getOrderid());
				descDetails.setSrn_date(srn.getSrndate());
				descDetails.setCompany_id(srn.getCompany_id());
				descDetails.setFin_year(srn.getFin_year());
				descDetails.setModified_type("INSERTED");
				descDetails.setInserted_by(srn.getInserted_by());
				descDetails.setInserted_on(srn.getInserted_on());
				descDetails.setUpdated_by(srn.getUpdated_by());
				descDetails.setUpdated_on(srn.getUpdated_on());
				descDetails.setDeleted_by("NA");
				descDetails.setDeleted_on(ldt);
			}
			item.setNonservicesrn_desc_details(itemDescDetails);
		}
		srn.setNongoodssrn_item_details(itemDetails);
		
		nongoodssrnRepository.updateTimeDetails(op.get().getSrnid());
		
		Set<Nongoodssrn_time_service> timeservice=new HashSet<Nongoodssrn_time_service>();
		timeservice.addAll(srn.getNongoodssrn_time_service());
		for(Nongoodssrn_time_service time:timeservice) 
		{
			time.setSrnid(op.get().getSrnid());
			time.setCompany_id(srn.getCompany_id());
			time.setFin_year(srn.getFin_year());
			time.setModified_type("INSERTED");
			time.setInserted_by(srn.getInserted_by());
			time.setInserted_on(srn.getInserted_on());
			time.setUpdated_by(srn.getUpdated_by());
			time.setUpdated_on(srn.getUpdated_on());
			time.setDeleted_by("NA");
			time.setDeleted_on(ldt);
		}
		srn.setNongoodssrn_time_service(timeservice);
		
		nongoodssrnRepository.updateDoc(op.get().getSrnid());
		
		Set<Nongoodssrn_docs> documents=new HashSet<Nongoodssrn_docs>();
		documents.addAll(srn.getNongoodssrn_docs());
		for(Nongoodssrn_docs doc:documents) 
		{
			doc.setSrnid(op.get().getSrnid());
			doc.setCompany_id(srn.getCompany_id());
			doc.setFin_year(srn.getFin_year());
			doc.setModified_type("INSERTED");
			doc.setInserted_by(srn.getInserted_by());
			doc.setInserted_on(srn.getInserted_on());
			doc.setUpdated_by(srn.getUpdated_by());
			doc.setUpdated_on(srn.getUpdated_on());
			doc.setDeleted_by("NA");
			doc.setDeleted_on(ldt);
		}
		srn.setNongoodssrn_docs(documents);
		
	return nongoodssrnRepository.save(srn);
	}

	@Transactional
	public Nongoodssrn delete(Nongoodssrn nongoodssrn,long id) 
	{
		Optional<Nongoodssrn> op = nongoodssrnRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Nongoodssrn srn=op.get();
		
		srn.setModified_type("DELETED");
		srn.setInserted_by(op.get().getInserted_by());
		srn.setInserted_on(op.get().getInserted_on());
		srn.setUpdated_by(op.get().getUpdated_by());
		srn.setUpdated_on(op.get().getUpdated_on());
		srn.setDeleted_by(userRepository.getUserDetails(srn.getUsername()).getName());
		srn.setDeleted_on(ldt);
		
		nongoodssrn_item_detailsRepository.revertItem(op.get().getSrnid());
		nongoodssrn_item_detailsRepository.revertService(op.get().getSrnid());
		nongoodssrnRepository.revertTimeDetails(op.get().getSrnid());
		nongoodssrnRepository.revertDocuments(op.get().getSrnid());
		
		if(op.isPresent())
		{
			srn.setId(id);
		}
		
		return nongoodssrnRepository.save(srn);
	}
	
}
