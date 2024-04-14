package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Broker_master;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry_BPartner_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry_Doc;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry_Service_Details;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner;
import com.AnkitIndia.jwtauthentication.repository.DepartmentMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_EnquiryRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Enquiry_BPartner_DetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Enquiry_DocRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Enquiry_Service_DetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_EnquiryDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Enquiry_BPartner_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Enquiry_DocDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Enquiry_Service_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_docDTO;

@Service
public class Pur_EnquiryService_Imp implements Pur_EnquiryService{

	@Autowired
	Pur_EnquiryRepository pur_EnquiryRepository;
	
	@Autowired
	Pur_Enquiry_BPartner_DetailsRepository pur_Enquiry_BPartner_DetailsRepository;
	
	@Autowired
	Pur_Enquiry_Service_DetailsRepository pur_Enquiry_Service_DetailsRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	DepartmentMasterRepository departmentMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Pur_Enquiry_DocRepository pur_Enquiry_DocRepository;
	
	@Autowired
	Item_type_masterRepository item_type_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public SequenceIdDTO getEnqSequenceId(String prefix,String orderdate,String type)
	{
		int slno=0;
		//String dt=Utility.convertDate(orderdate);
		String sno=pur_EnquiryRepository.countEnquiryOrder(orderdate,type);
		if(type.compareTo("Formal")==0) {type="FOR";}
		if(type.compareTo("Informal")==0) {type="INFOR";}
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		prefix=prefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Pur_Enquiry save(Pur_Enquiry penq)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(pur_EnquiryRepository.countId() != null ) {
			slno=Long.parseLong(pur_EnquiryRepository.countId());
		}
		String prefix="PEQ";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		penq.setEnquiry_id(gen_sno);
		penq.setQuotation_status(false); 
		penq.setOrder_status(false);
		
		/*Start checking transaction no for unique */
		System.err.println("First:>>"+penq.getEnquiry_no());
		long nslno=0;String type="",tprefix="ENQ";
		String tsno=pur_EnquiryRepository.countEnquiryOrder(penq.getEnquiry_date(),penq.getEnquiry_type());
		if(penq.getEnquiry_type().compareTo("Formal")==0) {type="FOR";}
		if(penq.getEnquiry_type().compareTo("Informal")==0) {type="INFOR";}
		
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = penq.getEnquiry_date().split("-");
		tprefix=tprefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		penq.setEnquiry_no(gen_tslno);
		System.err.println("Last:>>>"+penq.getEnquiry_no());
		/*End checking transaction no for unique */
		
		penq.setModified_type("INSERTED");
		penq.setInserted_by(userRepository.getUserDetails(penq.getUsername()).getName());
		penq.setInserted_on(ldt);
		penq.setUpdated_by("NA");
		penq.setUpdated_on(ldt);
		penq.setDeleted_by("NA");
		penq.setDeleted_on(ldt);
		
		if(penq.getService_type().compareTo("0") != 0 && penq.getService_type().compareTo("") != 0 && penq.getService_type() != null) {
			penq.setService_type_name(item_type_masterRepository.getItemType(penq.getService_type()).getItem_name());
		}
		
		Set<Pur_Enquiry_Service_Details> peqserSet = new HashSet<Pur_Enquiry_Service_Details>();
		peqserSet.addAll(penq.getPur_Enquiry_Service_Details());
		for(Pur_Enquiry_Service_Details penqdtls : peqserSet)
		{
			//penqdtls.setCompany_id(pur_EnquiryRepository.getCompNameToCompCode(penqdtls.getCompany_id()));
			
			penqdtls.setQuotation_status(false); 
			penqdtls.setOrder_status(false);
			penqdtls.setEnquiry_id(gen_sno);
			penqdtls.setEnquiry_no(penq.getEnquiry_no());
			penqdtls.setItem_name(item_masterRepository.itemNameById(penqdtls.getItem_code()).getItem_name());
			if(penqdtls.getPacking_item().compareTo("")!=0 && penqdtls.getPacking_item().compareTo("0")!=0) {
				penqdtls.setPacking_item_name(item_masterRepository.itemNameById(penqdtls.getPacking_item()).getItem_name());
			}
			penqdtls.setCompany_id(penq.getCompany_id());
			penqdtls.setFin_year(penq.getFin_year());
			penqdtls.setModified_type("INSERTED");
			penqdtls.setInserted_by(penq.getInserted_by());
			penqdtls.setInserted_on(penq.getInserted_on());
			penqdtls.setUpdated_by("NA");
			penqdtls.setUpdated_on(ldt);
			penqdtls.setDeleted_by("NA");
			penqdtls.setDeleted_on(ldt);
		
		}
		penq.setPur_Enquiry_Service_Details(peqserSet);
		
		Set<Pur_Enquiry_BPartner_Details> peqbpSet = new HashSet<Pur_Enquiry_BPartner_Details>();
		peqbpSet.addAll(penq.getPur_Enquiry_BPartner_Details());
		for(Pur_Enquiry_BPartner_Details penqbpdtls : peqbpSet)
		{
			penqbpdtls.setEnquiry_id(gen_sno);
			penqbpdtls.setEnquiry_no(penq.getEnquiry_no());
			penqbpdtls.setBp_name(supp_bussiness_partnerRepository.getSupplierName(penqbpdtls.getBp_code()).getBp_name());
			penqbpdtls.setCompany_id(penq.getCompany_id());
			penqbpdtls.setFin_year(penq.getFin_year());
			penqbpdtls.setModified_type("INSERTED");
			penqbpdtls.setInserted_by(penq.getInserted_by());
			penqbpdtls.setInserted_on(penq.getInserted_on());
			penqbpdtls.setUpdated_by("NA");
			penqbpdtls.setUpdated_on(ldt);
			penqbpdtls.setDeleted_by("NA");
			penqbpdtls.setDeleted_on(ldt);
		
		}
		penq.setPur_Enquiry_BPartner_Details(peqbpSet);
		
		Set<Pur_Enquiry_Doc> docSet = new HashSet<Pur_Enquiry_Doc>();
		docSet.addAll(penq.getPur_Enquiry_docs());
		for(Pur_Enquiry_Doc docDtls : docSet)
		{
			docDtls.setEnquiry_id(gen_sno);
			docDtls.setEnquiry_no(penq.getEnquiry_no());
			docDtls.setCompany_id(penq.getCompany_id());
			docDtls.setFin_year(penq.getFin_year());
			docDtls.setModified_type("INSERTED");
			docDtls.setInserted_by(penq.getInserted_by());
			docDtls.setInserted_on(penq.getInserted_on());
			docDtls.setUpdated_by("NA");
			docDtls.setUpdated_on(ldt);
			docDtls.setDeleted_by("NA");
			docDtls.setDeleted_on(ldt);
		
		}
		penq.setPur_Enquiry_docs(docSet);
		
		return pur_EnquiryRepository.save(penq);
	}
	
	@Transactional
	public Pur_Enquiry update(Pur_Enquiry iMaster,Long id)
	{
		Optional<Pur_Enquiry> op = pur_EnquiryRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		iMaster.setModified_type("INSERTED");
		iMaster.setInserted_by(op.get().getInserted_by());
		iMaster.setInserted_on(op.get().getInserted_on());
		iMaster.setUpdated_by(userRepository.getUserDetails(iMaster.getUsername()).getName());
		iMaster.setUpdated_on(ldt);
		iMaster.setDeleted_by("NA");
		iMaster.setDeleted_on(ldt);
		
		if(iMaster.getService_type().compareTo("0") != 0 && iMaster.getService_type().compareTo("") != 0 && iMaster.getService_type() != null) {
			iMaster.setService_type_name(item_type_masterRepository.getItemType(iMaster.getService_type()).getItem_name());
		}
		
		if(op.isPresent())
		{
			iMaster.setId(id);
		}
		
		pur_Enquiry_Service_DetailsRepository.pur_Enquiry_Service_Detailsupdate(iMaster.getEnquiry_id());
		Set<Pur_Enquiry_Service_Details> peqserSet = new HashSet<Pur_Enquiry_Service_Details>();
		peqserSet.addAll(iMaster.getPur_Enquiry_Service_Details());
		for(Pur_Enquiry_Service_Details penqdtls : peqserSet)
		{
			//penqdtls.setCompany_id(pur_EnquiryRepository.getCompNameToCompCode(penqdtls.getCompany_id()));
			
			penqdtls.setQuotation_status(false); 
			penqdtls.setOrder_status(false);
			penqdtls.setEnquiry_id(iMaster.getEnquiry_id());
			penqdtls.setEnquiry_no(iMaster.getEnquiry_no());
			penqdtls.setItem_name(item_masterRepository.itemNameById(penqdtls.getItem_code()).getItem_name());
			if(penqdtls.getPacking_item().compareTo("")!=0 && penqdtls.getPacking_item().compareTo("0")!=0) {
				penqdtls.setPacking_item_name(item_masterRepository.itemNameById(penqdtls.getPacking_item()).getItem_name());
			}
			penqdtls.setCompany_id(iMaster.getCompany_id());
			penqdtls.setFin_year(iMaster.getFin_year());
			penqdtls.setModified_type("INSERTED");
			penqdtls.setInserted_by(iMaster.getInserted_by());
			penqdtls.setInserted_on(iMaster.getInserted_on());
			penqdtls.setUpdated_by(iMaster.getUpdated_by());
			penqdtls.setUpdated_on(iMaster.getUpdated_on());
			penqdtls.setDeleted_by("NA");
			penqdtls.setDeleted_on(ldt);
		
		}
		iMaster.setPur_Enquiry_Service_Details(peqserSet);
		
		pur_Enquiry_BPartner_DetailsRepository.pur_Enquiry_BPartner_Detailsupdate(iMaster.getEnquiry_id());
		Set<Pur_Enquiry_BPartner_Details> peqbpSet = new HashSet<Pur_Enquiry_BPartner_Details>();
		peqbpSet.addAll(iMaster.getPur_Enquiry_BPartner_Details());
		for(Pur_Enquiry_BPartner_Details penqbpdtls : peqbpSet)
		{
			penqbpdtls.setEnquiry_id(iMaster.getEnquiry_id());
			penqbpdtls.setEnquiry_no(iMaster.getEnquiry_no());
			penqbpdtls.setBp_name(supp_bussiness_partnerRepository.getSupplierName(penqbpdtls.getBp_code()).getBp_name());
			penqbpdtls.setCompany_id(iMaster.getCompany_id());
			penqbpdtls.setFin_year(iMaster.getFin_year());
			penqbpdtls.setModified_type("INSERTED");
			penqbpdtls.setInserted_by(iMaster.getInserted_by());
			penqbpdtls.setInserted_on(iMaster.getInserted_on());
			penqbpdtls.setUpdated_by(iMaster.getUpdated_by());
			penqbpdtls.setUpdated_on(iMaster.getUpdated_on());
			penqbpdtls.setDeleted_by("NA");
			penqbpdtls.setDeleted_on(ldt);
		
		}
		iMaster.setPur_Enquiry_BPartner_Details(peqbpSet);
		
		
		pur_Enquiry_DocRepository.pur_Enquiry_Docupdate(iMaster.getEnquiry_id());
		Set<Pur_Enquiry_Doc> docSet = new HashSet<Pur_Enquiry_Doc>();
		docSet.addAll(iMaster.getPur_Enquiry_docs());
		for(Pur_Enquiry_Doc docDtls : docSet)
		{
			docDtls.setEnquiry_id(iMaster.getEnquiry_id());
			docDtls.setEnquiry_no(iMaster.getEnquiry_no());
			docDtls.setCompany_id(iMaster.getCompany_id());
			docDtls.setFin_year(iMaster.getFin_year());
			docDtls.setModified_type("INSERTED");
			docDtls.setInserted_by(iMaster.getInserted_by());
			docDtls.setInserted_on(iMaster.getInserted_on());
			docDtls.setUpdated_by(iMaster.getUpdated_by());
			docDtls.setUpdated_on(iMaster.getUpdated_on());
			docDtls.setDeleted_by("NA");
			docDtls.setDeleted_on(ldt);
		
		}
		iMaster.setPur_Enquiry_docs(docSet);
		return pur_EnquiryRepository.save(iMaster);
	}

	public List<Pur_Enquiry> findAll()
	{
		List<Pur_Enquiry> enqList=new ArrayList<Pur_Enquiry>();
		enqList.addAll(pur_EnquiryRepository.findAllPurEnquirys());
		return enqList;
	}
	
	public Pur_Enquiry findOne(long id)
	{
		Optional<Pur_Enquiry> op=this.pur_EnquiryRepository.findById(id);
		return op.get();
	}
	
	 public List<Pur_Enquiry> enqNo() {
	        return pur_EnquiryRepository.enquiry_no("poor1");
	    }
	 
	 
	public List<Pur_EnquiryDTO> getPurEnquiryDDCList(String ccc)
	{
		List<Pur_Enquiry> modelList =pur_EnquiryRepository.getPurEnquiryDDCList(ccc);
		
		Type listType=new TypeToken<List<Pur_EnquiryDTO>>() {}.getType();
		List<Pur_EnquiryDTO> itemNameList=new ModelMapper().map(modelList,listType);
		
		return itemNameList;
	}
	
	public List<Pur_EnquiryDTO> getPurEnquiryDDCSuppList(String reftype,String sid,String itemtype)
	{
		List<Pur_Enquiry_BPartner_Details> bPartnerList =pur_Enquiry_BPartner_DetailsRepository.getPurEnquiryBPList(sid);
		
		List<Pur_Enquiry> modelList=new ArrayList<Pur_Enquiry>();
		
		for(int i=0;i<bPartnerList.size();i++)
		{
			 modelList.addAll(pur_EnquiryRepository.getPurEnquiryDDCSuppList(bPartnerList.get(i).getEnquiry_id(),reftype,itemtype));
			 System.err.println(bPartnerList.get(i).getEnquiry_id());
		}
		
		Type listType=new TypeToken<List<Pur_EnquiryDTO>>() {}.getType();
		List<Pur_EnquiryDTO> enqList=new ModelMapper().map(modelList,listType);
		
		return enqList;
	}
	
	public List<Pur_EnquiryDTO> getPurEnquiryInformal(String reftype)
	{
		List<Pur_Enquiry> modelList=new ArrayList<Pur_Enquiry>();
		
		modelList.addAll(pur_EnquiryRepository.getPurEnquiryInformal(reftype));
		
		Type listType=new TypeToken<List<Pur_EnquiryDTO>>() {}.getType();
		List<Pur_EnquiryDTO> enqList=new ModelMapper().map(modelList,listType);
		
		return enqList;
	}

	public Pur_EnquiryDTO getPurEnquiryDetails(String enqid)
	{
		Pur_Enquiry modelList =pur_EnquiryRepository.getPurEnquiryDetails(enqid);
		
		Type listType=new TypeToken<Pur_EnquiryDTO>() {}.getType();
		Pur_EnquiryDTO enqDtls=new ModelMapper().map(modelList,listType);
		
		return enqDtls;
	}
	
	 public List<Pur_Enquiry_Service_DetailsDTO> getPurEnquiryCNQUPList(String enq_id)
		{
			String[] arrOfStr=enq_id.split(",");
			
			List<Pur_Enquiry_Service_Details> modelList=new ArrayList<Pur_Enquiry_Service_Details>();
			
			for(int i=0;i<arrOfStr.length;i++)
			{
				modelList.addAll(pur_Enquiry_Service_DetailsRepository.getPurEnquiryCNQUPList(arrOfStr[i]));
			}
			Type listType=new TypeToken<List<Pur_Enquiry_Service_DetailsDTO>>() {}.getType();
			
			List<Pur_Enquiry_Service_DetailsDTO> enqList=new ModelMapper().map(modelList,listType);
			
			return enqList;
		}
	 
	 public List<Pur_Enquiry_Service_DetailsDTO> getPurEnqItemDtlsList()
		{
			List<Pur_Enquiry_Service_Details> modelList=new ArrayList<Pur_Enquiry_Service_Details>();
			modelList.addAll(pur_Enquiry_Service_DetailsRepository.getPurEnqItemDtlsList());
			Type listType=new TypeToken<List<Pur_Enquiry_Service_DetailsDTO>>() {}.getType();
			List<Pur_Enquiry_Service_DetailsDTO> enqList=new ModelMapper().map(modelList,listType);
			return enqList;
		}
	 
	 
	 public List<Pur_Enquiry_Service_DetailsDTO> getPurEnquiryItemDtlsList(String enq_id)
		{
			List<Pur_Enquiry_Service_Details> modelList=new ArrayList<Pur_Enquiry_Service_Details>();
			modelList.addAll(pur_Enquiry_Service_DetailsRepository.getPurEnquiryItemDtlsList(enq_id));
			Type listType=new TypeToken<List<Pur_Enquiry_Service_DetailsDTO>>() {}.getType();
			List<Pur_Enquiry_Service_DetailsDTO> enqList=new ModelMapper().map(modelList,listType);
			return enqList;
		}
	 
	 public List<Pur_Enquiry_BPartner_DetailsDTO> getPurEnquiryBPDetails(String enq_id)
	 {
		List<Pur_Enquiry_BPartner_Details> modelList=new ArrayList<Pur_Enquiry_BPartner_Details>();
		
		modelList.addAll(pur_Enquiry_BPartner_DetailsRepository.getPurEnquiryBPDetails(enq_id));
		Type listType=new TypeToken<List<Pur_Enquiry_BPartner_DetailsDTO>>() {}.getType();
		List<Pur_Enquiry_BPartner_DetailsDTO> enqBPList=new ModelMapper().map(modelList,listType);
		
		return enqBPList;
	}
	 
	 public List<Pur_Enquiry_DocDTO> getPurEnquiryDocList(String enq_id)
	 {
		List<Pur_Enquiry_Doc> modelList=new ArrayList<Pur_Enquiry_Doc>();
		
		modelList.addAll(pur_Enquiry_DocRepository.getPurEnquiryDocList(enq_id));
		Type listType=new TypeToken<List<Pur_Enquiry_DocDTO>>() {}.getType();
		List<Pur_Enquiry_DocDTO> docList=new ModelMapper().map(modelList,listType);
		return docList;
	 }
}
