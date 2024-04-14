package com.AnkitIndia.jwtauthentication.security.services;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Broker_master;
import com.AnkitIndia.jwtauthentication.model.Broker_master_account;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Pur_Indent;
import com.AnkitIndia.jwtauthentication.model.Pur_Quotation;
import com.AnkitIndia.jwtauthentication.model.Pur_Quotation_Broker;
import com.AnkitIndia.jwtauthentication.model.Pur_Quotation_Doc;
import com.AnkitIndia.jwtauthentication.model.Pur_Quotation_Service;
import com.AnkitIndia.jwtauthentication.model.Pur_quotation_Business_Partner_details;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_master_accountRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_QuotationRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Quotation_BrokerRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Quotation_DocRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Quotation_ServiceRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_quotation_Business_Partner_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_IndentDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Indent_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_QuotationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quotation_BrokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quotation_DocDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quotation_ServiceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_quotation_Business_Partner_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_accountDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_othDTO;

@Service
public class Pur_QuotationService_Imp implements Pur_QuotationService{

	@Autowired
	Pur_QuotationRepository pur_QuotationRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	Pur_Quotation_ServiceRepository pur_Quotation_ServiceRepository;
	
	@Autowired
	Item_type_masterRepository item_type_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public SequenceIdDTO getQuotSequenceId(String prefix,String orderdate,String type)
	{
		int slno=0;
		//String dt=Utility.convertDate(orderdate);
		String sno=pur_QuotationRepository.countQuotOrder(orderdate,type);
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
	public Pur_Quotation save(Pur_Quotation purquote)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(pur_QuotationRepository.countId() != null ) {
			slno=Long.parseLong(pur_QuotationRepository.countId());
		}
		String prefix="PQ";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		purquote.setQuotation_id(gen_sno);
		
		/*Start checking transaction no for unique */
		System.err.println("First:>>"+purquote.getQuotation_no());
		long nslno=0;String type="",tprefix="QU";
		String tsno=pur_QuotationRepository.countQuotOrder(purquote.getQuotation_date(),purquote.getQuotation_type());
		if(purquote.getQuotation_type().compareTo("Formal")==0) {type="FOR";}
		if(purquote.getQuotation_type().compareTo("Informal")==0) {type="INFOR";}
		
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = purquote.getQuotation_date().split("-");
		tprefix=tprefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		purquote.setQuotation_no(gen_tslno);
		System.err.println("Last:>>>"+purquote.getQuotation_no());
		/*End checking transaction no for unique */
		
		purquote.setModified_type("INSERTED");
		purquote.setInserted_by(userRepository.getUserDetails(purquote.getUsername()).getName());
		purquote.setInserted_on(ldt);
		purquote.setUpdated_by("NA");
		purquote.setUpdated_on(ldt);
		purquote.setDeleted_by("NA");
		purquote.setDeleted_on(ldt);
		
		if(purquote.getQuotation_service().compareTo("0") != 0 && purquote.getQuotation_service().compareTo("") != 0 && purquote.getQuotation_service() != null) {
			purquote.setItem_type(item_type_masterRepository.getItemType(purquote.getQuotation_service()).getItem_name());
		}
		
		Set<Pur_Quotation_Service> perQSerSet = new HashSet<Pur_Quotation_Service>();
		perQSerSet.addAll(purquote.getPur_Quotation_Service());
		for(Pur_Quotation_Service perqserdtls : perQSerSet)
		{
			perqserdtls.setQuotation_id(gen_sno);
			perqserdtls.setQuotation_no(purquote.getQuotation_no());
			perqserdtls.setItem_name(item_masterRepository.itemNameById(perqserdtls.getItem_code()).getItem_name());
			if(perqserdtls.getPacking_item().compareTo("")!=0 && perqserdtls.getPacking_item().compareTo("0")!=0) {
				perqserdtls.setPacking_item_name(item_masterRepository.itemNameById(perqserdtls.getPacking_item()).getItem_name());
			}
			perqserdtls.setCompany_id(purquote.getCompany_id());
			perqserdtls.setFin_year(purquote.getFin_year());
			perqserdtls.setModified_type("INSERTED");
			perqserdtls.setInserted_by(purquote.getInserted_by());
			perqserdtls.setInserted_on(purquote.getInserted_on());
			perqserdtls.setUpdated_by("NA");
			perqserdtls.setUpdated_on(ldt);
			perqserdtls.setDeleted_by("NA");
			perqserdtls.setDeleted_on(ldt);
		
		}
		purquote.setPur_Quotation_Service(perQSerSet);
		
		Set<Pur_quotation_Business_Partner_details> Partner_detailsSet = new HashSet<Pur_quotation_Business_Partner_details>();
		Partner_detailsSet.add(purquote.getPur_quotation_Business_Partner_details());
		for(Pur_quotation_Business_Partner_details purQuotBPdtls : Partner_detailsSet)
		{
			purQuotBPdtls.setQuotation_id(gen_sno);
			purQuotBPdtls.setQuotation_no(purquote.getQuotation_no());
			purQuotBPdtls.setCompany_id(purquote.getCompany_id());
			purQuotBPdtls.setFin_year(purquote.getFin_year());
			purQuotBPdtls.setModified_type("INSERTED");
			purQuotBPdtls.setInserted_by(purquote.getInserted_by());
			purQuotBPdtls.setInserted_on(purquote.getInserted_on());
			purQuotBPdtls.setUpdated_by("NA");
			purQuotBPdtls.setUpdated_on(ldt);
			purQuotBPdtls.setDeleted_by("NA");
			purQuotBPdtls.setDeleted_on(ldt);
			
		}
		if(!Partner_detailsSet.isEmpty())
		{
			purquote.setPur_quotation_Business_Partner_details(Partner_detailsSet.iterator().next());
		}
		
		
		Set<Pur_Quotation_Doc> docSet = new HashSet<Pur_Quotation_Doc>();
		docSet.addAll(purquote.getPur_Quotation_docs());
		for(Pur_Quotation_Doc docDtls : docSet)
		{
			docDtls.setQuotation_id(gen_sno);
			docDtls.setQuotation_no(purquote.getQuotation_no());
			docDtls.setCompany_id(purquote.getCompany_id());
			docDtls.setFin_year(purquote.getFin_year());
			docDtls.setModified_type("INSERTED");
			docDtls.setInserted_by(purquote.getInserted_by());
			docDtls.setInserted_on(purquote.getInserted_on());
			docDtls.setUpdated_by("NA");
			docDtls.setUpdated_on(ldt);
			docDtls.setDeleted_by("NA");
			docDtls.setDeleted_on(ldt);
		
		}
		purquote.setPur_Quotation_docs(docSet);
		
		Set<Pur_Quotation_Broker> brokerSet = new HashSet<Pur_Quotation_Broker>();
		brokerSet.addAll(purquote.getPur_Quotation_Broker());
		for(Pur_Quotation_Broker brokerDtls : brokerSet)
		{
			brokerDtls.setQuotation_id(gen_sno);
			brokerDtls.setQuotation_no(purquote.getQuotation_no());
			if(brokerDtls.getVen_code_name().compareTo("")!=0 && brokerDtls.getVen_code_name().compareTo("0")!=0) {
				brokerDtls.setVen_name(broker_masterRepository.brokerNameByCode(brokerDtls.getVen_code_name()).getName());
			}
			brokerDtls.setCompany_id(purquote.getCompany_id());
			brokerDtls.setFin_year(purquote.getFin_year());
			brokerDtls.setModified_type("INSERTED");
			brokerDtls.setInserted_by(purquote.getInserted_by());
			brokerDtls.setInserted_on(purquote.getInserted_on());
			brokerDtls.setUpdated_by("NA");
			brokerDtls.setUpdated_on(ldt);
			brokerDtls.setDeleted_by("NA");
			brokerDtls.setDeleted_on(ldt);
		
		}
		purquote.setPur_Quotation_Broker(brokerSet);
		
		
		return pur_QuotationRepository.save(purquote);
	}
	
	@Autowired
	Pur_Quotation_DocRepository pur_Quotation_DocRepository;
	
	@Autowired
	Pur_Quotation_BrokerRepository pur_Quotation_BrokerRepository;
	
	@Transactional
	public Pur_Quotation update(Pur_Quotation iMaster,Long id) 
	{
		Optional<Pur_Quotation> op = pur_QuotationRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		iMaster.setModified_type("INSERTED");
		iMaster.setInserted_by(op.get().getInserted_by());
		iMaster.setInserted_on(op.get().getInserted_on());
		iMaster.setUpdated_by(userRepository.getUserDetails(iMaster.getUsername()).getName());
		iMaster.setUpdated_on(ldt);
		iMaster.setDeleted_by("NA");
		iMaster.setDeleted_on(ldt);
		
		if(iMaster.getQuotation_service().compareTo("0") != 0 && iMaster.getQuotation_service().compareTo("") != 0 && iMaster.getQuotation_service() != null) {
			iMaster.setItem_type(item_type_masterRepository.getItemType(iMaster.getQuotation_service()).getItem_name());
		}
		
		if(op.isPresent())
		{
			iMaster.setId(id);
		}
		
		pur_Quotation_ServiceRepository.pur_Quotation_Serviceupdate(op.get().getQuotation_id());
		Set<Pur_Quotation_Service> perQSerSet = new HashSet<Pur_Quotation_Service>();
		perQSerSet.addAll(iMaster.getPur_Quotation_Service());
		for(Pur_Quotation_Service perqserdtls : perQSerSet)
		{
			perqserdtls.setQuotation_id(op.get().getQuotation_id());
			perqserdtls.setQuotation_no(iMaster.getQuotation_no());
			perqserdtls.setItem_name(item_masterRepository.itemNameById(perqserdtls.getItem_code()).getItem_name());
			if(perqserdtls.getPacking_item().compareTo("")!=0 && perqserdtls.getPacking_item().compareTo("0")!=0) {
				perqserdtls.setPacking_item_name(item_masterRepository.itemNameById(perqserdtls.getPacking_item()).getItem_name());
			}
			perqserdtls.setCompany_id(iMaster.getCompany_id());
			perqserdtls.setFin_year(iMaster.getFin_year());
			perqserdtls.setModified_type("INSERTED");
			perqserdtls.setInserted_by(iMaster.getInserted_by());
			perqserdtls.setInserted_on(iMaster.getInserted_on());
			perqserdtls.setUpdated_by(iMaster.getUpdated_by());
			perqserdtls.setUpdated_on(iMaster.getUpdated_on());
			perqserdtls.setDeleted_by("NA");
			perqserdtls.setDeleted_on(ldt);
			
		}
		iMaster.setPur_Quotation_Service(perQSerSet);
		
		pur_quotation_Business_Partner_detailsRepository.pur_quotation_Business_Partner_detailsupdate(op.get().getQuotation_id());
		Set<Pur_quotation_Business_Partner_details> Partner_detailsSet = new HashSet<Pur_quotation_Business_Partner_details>();
		Partner_detailsSet.add(iMaster.getPur_quotation_Business_Partner_details());
		for(Pur_quotation_Business_Partner_details purQuotBPdtls : Partner_detailsSet)
		{
			purQuotBPdtls.setQuotation_id(op.get().getQuotation_id());
			purQuotBPdtls.setQuotation_no(iMaster.getQuotation_no());
			purQuotBPdtls.setCompany_id(iMaster.getCompany_id());
			purQuotBPdtls.setFin_year(iMaster.getFin_year());
			purQuotBPdtls.setModified_type("INSERTED");
			purQuotBPdtls.setInserted_by(iMaster.getInserted_by());
			purQuotBPdtls.setInserted_on(iMaster.getInserted_on());
			purQuotBPdtls.setUpdated_by(iMaster.getUpdated_by());
			purQuotBPdtls.setUpdated_on(iMaster.getUpdated_on());
			purQuotBPdtls.setDeleted_by("NA");
			purQuotBPdtls.setDeleted_on(ldt);
			
		}
		if(!Partner_detailsSet.isEmpty())
		{
			iMaster.setPur_quotation_Business_Partner_details(Partner_detailsSet.iterator().next());
		}
		
		pur_Quotation_DocRepository.pur_Quotation_Docupdate(op.get().getQuotation_id());
		Set<Pur_Quotation_Doc> docSet = new HashSet<Pur_Quotation_Doc>();
		docSet.addAll(iMaster.getPur_Quotation_docs());
		for(Pur_Quotation_Doc docDtls : docSet)
		{
			docDtls.setQuotation_id(op.get().getQuotation_id());
			docDtls.setQuotation_no(iMaster.getQuotation_no());
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
		iMaster.setPur_Quotation_docs(docSet);
		
		pur_Quotation_BrokerRepository.pur_Quotation_Brokerupdate(op.get().getQuotation_id());
		Set<Pur_Quotation_Broker> brokerSet = new HashSet<Pur_Quotation_Broker>();
		brokerSet.addAll(iMaster.getPur_Quotation_Broker());
		for(Pur_Quotation_Broker brokerDtls : brokerSet)
		{
			brokerDtls.setQuotation_id(op.get().getQuotation_id());
			brokerDtls.setQuotation_no(iMaster.getQuotation_no());
			if(brokerDtls.getVen_code_name().compareTo("")!=0 && brokerDtls.getVen_code_name().compareTo("0")!=0) {
				brokerDtls.setVen_name(broker_masterRepository.brokerNameByCode(brokerDtls.getVen_code_name()).getName());
			}
			brokerDtls.setCompany_id(iMaster.getCompany_id());
			brokerDtls.setFin_year(iMaster.getFin_year());
			brokerDtls.setModified_type("INSERTED");
			brokerDtls.setInserted_by(iMaster.getInserted_by());
			brokerDtls.setInserted_on(iMaster.getInserted_on());
			brokerDtls.setUpdated_by(iMaster.getUpdated_by());
			brokerDtls.setUpdated_on(iMaster.getUpdated_on());
			brokerDtls.setDeleted_by("NA");
			brokerDtls.setDeleted_on(ldt);
		
		}
		iMaster.setPur_Quotation_Broker(brokerSet);
		
		
		return pur_QuotationRepository.save(iMaster);
	}
	
	public List<Pur_Quotation> findAll()
	{
		List<Pur_Quotation> quotList=new ArrayList<Pur_Quotation>();
		quotList.addAll(pur_QuotationRepository.findAllPurQuotations());
		return quotList;
	}
	
	public Pur_Quotation findOne(long id)
	{
		Optional<Pur_Quotation> op=this.pur_QuotationRepository.findById(id);
		return op.get();
	}
	
	public Pur_QuotationDTO purQuotDetails(String quotationno)
	{
		Pur_Quotation modelList=pur_QuotationRepository.purQuotDetails(quotationno);
		
		Type listType=new TypeToken<Pur_QuotationDTO>() {}.getType();
		
		Pur_QuotationDTO purDtls=new ModelMapper().map(modelList,listType);
		
		return purDtls;
	}
	
	 public List<Pur_Quotation_ServiceDTO> getPurQtyItemDtlsList()
		{
			List<Pur_Quotation> modelList=new ArrayList<Pur_Quotation>();
			modelList.addAll(pur_QuotationRepository.getPurQtyItemDtlsList());
			Type listType=new TypeToken<List<Pur_Quotation_ServiceDTO>>() {}.getType();
			List<Pur_Quotation_ServiceDTO> enqList=new ModelMapper().map(modelList,listType);
			return enqList;
		}
	 
	 public List<Pur_QuotationDTO> getPurQtyDDCList(String ccc)
		{
			List<Pur_Quotation> modelList =pur_QuotationRepository.getPurQtyDDCList(ccc);
			Type listType=new TypeToken<List<Pur_QuotationDTO>>() {}.getType();
			List<Pur_QuotationDTO> itemNameList=new ModelMapper().map(modelList,listType);
			
			return itemNameList;
		}
	 
	 public List<Pur_QuotationDTO> getPurQuotPrevList()
		{
			List<Pur_Quotation> modelList =pur_QuotationRepository.getPurQuotPrevList();
			Type listType=new TypeToken<List<Pur_QuotationDTO>>() {}.getType();
			List<Pur_QuotationDTO> itemNameList=new ModelMapper().map(modelList,listType);
			
			return itemNameList;
		}
	 
	 public List<Pur_QuotationDTO> getPurQuotThruSuppList(String reftype,String suppid,String itemtype)
		{
			List<Pur_Quotation> modelList =pur_QuotationRepository.getPurQuotThruSuppList(reftype,suppid,itemtype);
			Type listType=new TypeToken<List<Pur_QuotationDTO>>() {}.getType();
			List<Pur_QuotationDTO> itemNameList=new ModelMapper().map(modelList,listType);
			
			return itemNameList;
		}
	 
	 public List<Pur_Quotation_ServiceDTO> getPurQtyCNQUPList(String quotation_no)
		{
			String[] arrOfStr=quotation_no.split(",");
			
			List<Pur_Quotation> modelList=new ArrayList<Pur_Quotation>();
			
			for(int i=0;i<arrOfStr.length;i++)
			{
				modelList.addAll(pur_QuotationRepository.getPurQtyCNQUPList(arrOfStr[i]));
			}
				
			Type listType=new TypeToken<List<Pur_Quotation_ServiceDTO>>() {}.getType();
			List<Pur_Quotation_ServiceDTO> purIndList=new ModelMapper().map(modelList,listType);
			return purIndList;
		}
	 
	public List<Pur_Quotation_ServiceDTO> purQServRetriveList(String code)
	{
		List<Pur_Quotation> modelList=new ArrayList<Pur_Quotation>();
		
		modelList.addAll(pur_QuotationRepository.purQServRetriveList(code));
			
		Type listType=new TypeToken<List<Pur_Quotation_ServiceDTO>>() {}.getType();
		
		List<Pur_Quotation_ServiceDTO> purQSrv=new ModelMapper().map(modelList,listType);
		
		return purQSrv;
	}
	
	public List<Pur_Quotation_BrokerDTO> getPurQuotBrokerDtls(String quot_id)
	{
		List<Pur_Quotation> modelList=new ArrayList<Pur_Quotation>();
		
		modelList.addAll(pur_QuotationRepository.getPurQuotBrokerDtls(quot_id));
			
		Type listType=new TypeToken<List<Pur_Quotation_BrokerDTO>>() {}.getType();
		
		List<Pur_Quotation_BrokerDTO> purBroker=new ModelMapper().map(modelList,listType);
		
		return purBroker;
	}
	 
	 
	 @Autowired
	 Pur_quotation_Business_Partner_detailsRepository pur_quotation_Business_Partner_detailsRepository;
	 
	 public Pur_quotation_Business_Partner_detailsDTO purQBPRetriveList(String code)
	 {
		 Pur_quotation_Business_Partner_details modelList=pur_quotation_Business_Partner_detailsRepository.purQBPRetriveList(code);
		 Type listType = new TypeToken<Pur_quotation_Business_Partner_detailsDTO>() {}.getType();

		 Pur_quotation_Business_Partner_detailsDTO purQBP= new ModelMapper().map(modelList,listType);
			
		return purQBP;
	 }
	 
	 public List<Pur_Quotation_DocDTO> purQDocRetriveList(String code)
		{
			List<Pur_Quotation> modelList=new ArrayList<Pur_Quotation>();
			
			modelList.addAll(pur_QuotationRepository.purQDocRetriveList(code));
				
			Type listType=new TypeToken<List<Pur_Quotation_DocDTO>>() {}.getType();
			
			List<Pur_Quotation_DocDTO> purQDoc=new ModelMapper().map(modelList,listType);
			
			return purQDoc;
		}
}
