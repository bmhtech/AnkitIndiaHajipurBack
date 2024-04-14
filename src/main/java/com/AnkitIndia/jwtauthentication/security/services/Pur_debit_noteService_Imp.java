package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Pur_debit_note;
import com.AnkitIndia.jwtauthentication.model.Pur_debit_note_account_info;
import com.AnkitIndia.jwtauthentication.model.Pur_debit_note_bp_details;
import com.AnkitIndia.jwtauthentication.model.Pur_debit_note_broker_details;
import com.AnkitIndia.jwtauthentication.model.Pur_debit_note_docs;
import com.AnkitIndia.jwtauthentication.model.Pur_debit_note_item_details;
import com.AnkitIndia.jwtauthentication.model.Pur_debit_note_musterroll_details;
import com.AnkitIndia.jwtauthentication.model.Pur_debit_note_tax_info;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_debit_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_debit_note_account_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_debit_note_bp_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_debit_note_broker_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_debit_note_docsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_debit_note_item_detailsReposiitory;
import com.AnkitIndia.jwtauthentication.repository.Pur_debit_note_musterroll_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_debit_note_tax_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_account_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_bp_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_broker_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_musterroll_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_tax_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
@Repository
public class Pur_debit_noteService_Imp implements Pur_debit_noteService{
	
	@Autowired
	Pur_debit_noteRepository pur_debit_noteRepository;
	
	@Autowired
	Pur_debit_note_item_detailsReposiitory pur_debit_note_item_detailsReposiitory;
	
	@Autowired
	Pur_debit_note_musterroll_detailsRepository pur_debit_note_musterroll_detailsRepository;
	
	@Autowired
	Pur_debit_note_broker_detailsRepository pur_debit_note_broker_detailsRepository;
	
	@Autowired
	Pur_debit_note_docsRepository  pur_debit_note_docsRepository;
	
	@Autowired
	Pur_debit_note_tax_infoRepository pur_debit_note_tax_infoRepository;
	
	@Autowired
	Pur_debit_note_bp_detailsRepository pur_debit_note_bp_detailsRepository;
	
	@Autowired
	Pur_debit_note_account_infoRepository pur_debit_note_account_infoRepository;  
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	Item_type_masterRepository item_type_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public SequenceIdDTO getDNSequenceId(String dndate,String dntype)
	{
		String prefix="DN",rtype="";
		int slno=0;
		String sno=pur_debit_noteRepository.countDebitNote(dndate,dntype);
		if(dntype.compareTo("Acceptance of Lower Rate")==0) {rtype="ALR";}
		if(dntype.compareTo("Goods Return Due To Rejection")==0) {rtype="GDR";}
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = dndate.split("-");
		prefix=prefix+"-"+rtype+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Pur_debit_note save(Pur_debit_note pdn)
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;
		
		if(pur_debit_noteRepository.countId() != null)
		{
			slno=Long.parseLong(pur_debit_noteRepository.countId());
		}
		String prefix="DN";
 		String gen_sno=UniqueID.uniqueid(prefix, slno);
 		pdn.setDebitnoteid(gen_sno);
 		
 		/*Start checking transaction no for unique */
		System.err.println("First:>>"+pdn.getDebitnoteno());
		long nslno=0;String tprefix="DN",rtype="";
		String tsno=pur_debit_noteRepository.countDebitNote(pdn.getDebitnotedate(),pdn.getDebitnotetype());
		if(pdn.getDebitnotetype().compareTo("Acceptance of Lower Rate")==0) {rtype="ALR";}
		if(pdn.getDebitnotetype().compareTo("Goods Return Due To Rejection")==0) {rtype="GDR";}		
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		
		String date[] = pdn.getDebitnotedate().split("-");
		tprefix=tprefix+"-"+rtype+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		pdn.setDebitnoteno(gen_tslno);
		System.err.println("Last:>>>"+pdn.getDebitnoteno());
		/*End checking transaction no for unique */
		System.err.println(pdn.getSer_item_subtype()+" : _ : ");
		if(pdn.getSer_item_subtype() != null)
		{
			System.err.println("kkk if");
		}else
		{
			System.err.println("kkk else");
		}
		
		if(pdn.getSupplier_id().compareTo("0") !=0 && pdn.getSupplier_id().compareTo("") !=0 && pdn.getSupplier_id() !=null) {
			pdn.setSupplier_name(supp_bussiness_partnerRepository.getSupplierName(pdn.getSupplier_id()).getBp_name());
		}else {pdn.setSupplier_name("None");}
		
		if(pdn.getBusinessunit().compareTo("0") !=0 && pdn.getBusinessunit().compareTo("") !=0 && pdn.getBusinessunit() !=null) {
			pdn.setBusinessunitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pdn.getBusinessunit()).getBusinessunit_name());
		}else {pdn.setBusinessunitname("None");}
		
		//modifi 09-12-2020 by bisu 
		
		/*
		 * if(pdn.getSer_item_subtype().compareTo("0") != 0 &&
		 * pdn.getSer_item_subtype().compareTo("") != 0 && pdn.getSer_item_subtype() !=
		 * null) { System.out.println("OKKk");
		 * pdn.setSer_item_subtypename(item_type_masterRepository.getItemType(pdn.
		 * getSer_item_subtype()).getItem_name()); }else { System.out.println("OKKk11");
		 * pdn.setSer_item_subtypename("None");}
		 */
		
		if(pdn.getTruck_no().compareTo("0") !=0 && pdn.getTruck_no().compareTo("") !=0 && pdn.getTruck_no() !=null) {
			pdn.setVehicleno(vehicleMasterRepository.getVehicleDetails(pdn.getTruck_no()).getVehicle_no());
		}else {pdn.setVehicleno("None");}
 		
 		pdn.setModified_type("INSERTED");
 		pdn.setInserted_by(userRepository.getUserDetails(pdn.getUsername()).getName());
 		pdn.setInserted_on(ldt);
 		pdn.setUpdated_by("NA");
 		pdn.setUpdated_on(ldt);
 		pdn.setDeleted_by("NA");
 		pdn.setDeleted_on(ldt);
 		
 		
		//Dynamic
		Set<Pur_debit_note_item_details> pdnItem = new HashSet<Pur_debit_note_item_details>();
		pdnItem.addAll(pdn.getPur_debit_note_item_details());
		for(Pur_debit_note_item_details pdni : pdnItem)
		{
			pdni.setDebitnoteid(pdn.getDebitnoteid());
			pdni.setDebitnoteno(pdn.getDebitnoteno());
			
			if(pdni.getAdv_item_code().compareTo("0") !=0 && pdni.getAdv_item_code().compareTo("") !=0 && pdni.getAdv_item_code() !=null) {
				pdni.setAdv_item_name(item_masterRepository.itemNameById(pdni.getAdv_item_code()).getItem_name());
			}else {pdni.setAdv_item_name("None");}
			
			//pdni.setAdv_item_name(item_masterRepository.itemNameById(pdni.getAdv_item_code()).getItem_name());
			if(pdni.getAdv_packing_item().compareTo("")!=0 && pdni.getAdv_packing_item().compareTo("0")!=0) {
				pdni.setAdv_packing_item_name(item_masterRepository.itemNameById(pdni.getAdv_packing_item()).getItem_name());
			}else {pdni.setAdv_packing_item_name("None");}
			
			pdni.setCompany_id(pdn.getCompany_id());
			pdni.setFin_year(pdn.getFin_year());
			pdni.setModified_type(pdn.getModified_type());
			pdni.setInserted_by(pdn.getInserted_by());
			pdni.setInserted_on(pdn.getInserted_on());
			pdni.setUpdated_by(pdn.getUpdated_by());
			pdni.setUpdated_on(pdn.getUpdated_on());
			pdni.setDeleted_by(pdn.getDeleted_by());
			pdni.setDeleted_on(pdn.getDeleted_on());
		}
		pdn.setPur_debit_note_item_details(pdnItem);
		
		//Dynamic
				Set<Pur_debit_note_musterroll_details> pdnMuster = new HashSet<Pur_debit_note_musterroll_details>();
				pdnMuster.addAll(pdn.getPur_debit_note_musterroll_details());
				for(Pur_debit_note_musterroll_details pdnm : pdnMuster)
				{
					pdnm.setDebitnoteid(pdn.getDebitnoteid());
					pdnm.setDebitnoteno(pdn.getDebitnoteno());
					pdnm.setCompany_id(pdn.getCompany_id());
					pdnm.setFin_year(pdn.getFin_year());
					pdnm.setModified_type(pdn.getModified_type());
					pdnm.setInserted_by(pdn.getInserted_by());
					pdnm.setInserted_on(pdn.getInserted_on());
					pdnm.setUpdated_by(pdn.getUpdated_by());
					pdnm.setUpdated_on(pdn.getUpdated_on());
					pdnm.setDeleted_by(pdn.getDeleted_by());
					pdnm.setDeleted_on(pdn.getDeleted_on());
				}
				pdn.setPur_debit_note_musterroll_details(pdnMuster);
				
				
				
				//Dynamic
				Set<Pur_debit_note_broker_details> pdnBroker = new HashSet<Pur_debit_note_broker_details>();
				pdnBroker.addAll(pdn.getPur_debit_note_broker_details());
				for(Pur_debit_note_broker_details pdnb : pdnBroker)
				{
					pdnb.setDebitnoteid(pdn.getDebitnoteid());
					pdnb.setDebitnoteno(pdn.getDebitnoteno());
					pdnb.setCompany_id(pdn.getCompany_id());
					pdnb.setFin_year(pdn.getFin_year());
					pdnb.setModified_type(pdn.getModified_type());
					pdnb.setInserted_by(pdn.getInserted_by());
					pdnb.setInserted_on(pdn.getInserted_on());
					pdnb.setUpdated_by(pdn.getUpdated_by());
					pdnb.setUpdated_on(pdn.getUpdated_on());
					pdnb.setDeleted_by(pdn.getDeleted_by());
					pdnb.setDeleted_on(pdn.getDeleted_on());
				}
				pdn.setPur_debit_note_broker_details(pdnBroker);
				
				
				
				//Dynamic
				Set<Pur_debit_note_docs> pdnDoc = new HashSet<Pur_debit_note_docs>();
				pdnDoc.addAll(pdn.getPur_debit_note_docs());
				for(Pur_debit_note_docs pdnd : pdnDoc)
				{
					pdnd.setDebitnoteid(pdn.getDebitnoteid());
					pdnd.setDebitnoteno(pdn.getDebitnoteno());
					pdnd.setCompany_id(pdn.getCompany_id());
					pdnd.setFin_year(pdn.getFin_year());
					pdnd.setModified_type(pdn.getModified_type());
					pdnd.setInserted_by(pdn.getInserted_by());
					pdnd.setInserted_on(pdn.getInserted_on());
					pdnd.setUpdated_by(pdn.getUpdated_by());
					pdnd.setUpdated_on(pdn.getUpdated_on());
					pdnd.setDeleted_by(pdn.getDeleted_by());
					pdnd.setDeleted_on(pdn.getDeleted_on());
				}
				pdn.setPur_debit_note_docs(pdnDoc);
		
		//Static
		Set<Pur_debit_note_tax_info> prnTax = new HashSet<Pur_debit_note_tax_info>();
		prnTax.add(pdn.getPur_debit_note_tax_info());
		for(Pur_debit_note_tax_info pdnt : prnTax)
		{
			pdnt.setDebitnoteid(pdn.getDebitnoteid());
			pdnt.setDebitnoteno(pdn.getDebitnoteno());
			pdnt.setCompany_id(pdn.getCompany_id());
			pdnt.setFin_year(pdn.getFin_year());
			pdnt.setModified_type(pdn.getModified_type());
			pdnt.setInserted_by(pdn.getInserted_by());
			pdnt.setInserted_on(pdn.getInserted_on());
			pdnt.setUpdated_by(pdn.getUpdated_by());
			pdnt.setUpdated_on(pdn.getUpdated_on());
			pdnt.setDeleted_by(pdn.getDeleted_by());
			pdnt.setDeleted_on(pdn.getDeleted_on());
		}
		
		if(!prnTax.isEmpty())
		{
			pdn.setPur_debit_note_tax_info(prnTax.iterator().next());
		}
		
		//Static
				Set<Pur_debit_note_bp_details> prnBuss = new HashSet<Pur_debit_note_bp_details>();
				prnBuss.add(pdn.getPur_debit_note_bp_details());
				for(Pur_debit_note_bp_details pdnbp : prnBuss)
				{
					pdnbp.setDebitnoteid(pdn.getDebitnoteid());
					pdnbp.setDebitnoteno(pdn.getDebitnoteno());
					pdnbp.setCompany_id(pdn.getCompany_id());
					pdnbp.setFin_year(pdn.getFin_year());
					pdnbp.setModified_type(pdn.getModified_type());
					pdnbp.setInserted_by(pdn.getInserted_by());
					pdnbp.setInserted_on(pdn.getInserted_on());
					pdnbp.setUpdated_by(pdn.getUpdated_by());
					pdnbp.setUpdated_on(pdn.getUpdated_on());
					pdnbp.setDeleted_by(pdn.getDeleted_by());
					pdnbp.setDeleted_on(pdn.getDeleted_on());
				}
				
				if(!prnBuss.isEmpty())
				{
					pdn.setPur_debit_note_bp_details(prnBuss.iterator().next());
				}
				
				
				//Static
				Set<Pur_debit_note_account_info> prnAcc = new HashSet<Pur_debit_note_account_info>();
				prnAcc.add(pdn.getPur_debit_note_account_info());
				for(Pur_debit_note_account_info pdna : prnAcc)
				{
					pdna.setDebitnoteid(pdn.getDebitnoteid());
					pdna.setDebitnoteno(pdn.getDebitnoteno());
					pdna.setCompany_id(pdn.getCompany_id());
					pdna.setFin_year(pdn.getFin_year());
					pdna.setModified_type(pdn.getModified_type());
					pdna.setInserted_by(pdn.getInserted_by());
					pdna.setInserted_on(pdn.getInserted_on());
					pdna.setUpdated_by(pdn.getUpdated_by());
					pdna.setUpdated_on(pdn.getUpdated_on());
					pdna.setDeleted_by(pdn.getDeleted_by());
					pdna.setDeleted_on(pdn.getDeleted_on());
				}
				
				if(!prnAcc.isEmpty())
				{
					pdn.setPur_debit_note_account_info(prnAcc.iterator().next());
				}
		
 		return pur_debit_noteRepository.save(pdn);
	}
	
	public List<Pur_debit_note> findAllDNotes(String company,String finyear)
	{
		List<Pur_debit_note> rtnNotesList=new ArrayList<Pur_debit_note>();
		
		rtnNotesList.addAll(pur_debit_noteRepository.findAllDNotes(company,finyear));
		
		return rtnNotesList;
	}
	
	public Pur_debit_note findOne(Long id) {
		Optional<Pur_debit_note> op=this.pur_debit_noteRepository.findById(id);
		return op.get();
	}
	
	public List<Pur_debit_note_item_detailsDTO> getPurDebitID(String debitnoteid)
	{
		List<Pur_debit_note_item_details> modelList=pur_debit_note_item_detailsReposiitory.getPur_debit_note_item_details(debitnoteid);
		
		Type listType=new TypeToken<List<Pur_debit_note_item_detailsDTO>>() {}.getType();
		
		List<Pur_debit_note_item_detailsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Pur_debit_note_musterroll_detailsDTO> getPurDebitMD(String debitnoteid)
	{
		List<Pur_debit_note_musterroll_details> modelList=pur_debit_note_musterroll_detailsRepository.getPur_debit_note_musterroll_details(debitnoteid);
		
		Type listType=new TypeToken<List<Pur_debit_note_musterroll_detailsDTO>>() {}.getType();
		
		List<Pur_debit_note_musterroll_detailsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Pur_debit_note_broker_detailsDTO> getPurDebitBD(String debitnoteid)
	{
		List<Pur_debit_note_broker_details> modelList=pur_debit_note_broker_detailsRepository.getPur_debit_note_broker_details(debitnoteid);
		
		Type listType=new TypeToken<List<Pur_debit_note_broker_detailsDTO>>() {}.getType();
		
		List<Pur_debit_note_broker_detailsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Pur_debit_note_docsDTO> getPurDebitD(String debitnoteid)
	{
		List<Pur_debit_note_docs> modelList=pur_debit_note_docsRepository.getPur_debit_note_docs(debitnoteid);
		
		Type listType=new TypeToken<List<Pur_debit_note_docsDTO>>() {}.getType();
		
		List<Pur_debit_note_docsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public Pur_debit_note_tax_infoDTO getPurDebitTI(String debitnoteid)
	{
		Pur_debit_note_tax_info modelList=pur_debit_note_tax_infoRepository.getPur_debit_note_tax_info(debitnoteid);
		
		Type listType=new TypeToken<Pur_debit_note_tax_infoDTO>() {}.getType();
		
		Pur_debit_note_tax_infoDTO itemNameList=new ModelMapper().map(modelList, listType);
		 return itemNameList;
	}
	
	public Pur_debit_note_bp_detailsDTO getPurDebitBPD(String debitnoteid)
	{
		Pur_debit_note_bp_details modelList=pur_debit_note_bp_detailsRepository.getPur_debit_note_bp_details(debitnoteid);
		
		Type listType=new TypeToken<Pur_debit_note_bp_detailsDTO>() {}.getType();
		
		Pur_debit_note_bp_detailsDTO itemNameList=new ModelMapper().map(modelList, listType);
		 return itemNameList;
	}
	
	public Pur_debit_note_account_infoDTO getPurDebitAI(String debitnoteid)
	{
		Pur_debit_note_account_info modelList=pur_debit_note_account_infoRepository.getPur_debit_note_account_info(debitnoteid);
		
		Type listType=new TypeToken<Pur_debit_note_account_infoDTO>() {}.getType();
		
		Pur_debit_note_account_infoDTO itemNameList=new ModelMapper().map(modelList, listType);
		 return itemNameList;
	}
	
	@Transactional
	public Pur_debit_note update(Pur_debit_note pdn,long id)
	{
		Optional<Pur_debit_note> op=pur_debit_noteRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
 		
 		pdn.setDebitnoteid(op.get().getDebitnoteid());
 		
 		if(pdn.getSupplier_id().compareTo("0") !=0 && pdn.getSupplier_id().compareTo("") !=0 && pdn.getSupplier_id() !=null) {
			pdn.setSupplier_name(supp_bussiness_partnerRepository.getSupplierName(pdn.getSupplier_id()).getBp_name());
		}else {pdn.setSupplier_name("None");}
		
		if(pdn.getBusinessunit().compareTo("0") !=0 && pdn.getBusinessunit().compareTo("") !=0 && pdn.getBusinessunit() !=null) {
			pdn.setBusinessunitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pdn.getBusinessunit()).getBusinessunit_name());
		}else {pdn.setBusinessunitname("None");}
		
		if(pdn.getSer_item_subtype().compareTo("0") != 0 && pdn.getSer_item_subtype().compareTo("") != 0 && pdn.getSer_item_subtype() != null) {
			pdn.setSer_item_subtypename(item_type_masterRepository.getItemType(pdn.getSer_item_subtype()).getItem_name());
		}else {pdn.setSer_item_subtypename("None");}
		
		if(pdn.getTruck_no().compareTo("0") !=0 && pdn.getTruck_no().compareTo("") !=0 && pdn.getTruck_no() !=null) {
			pdn.setVehicleno(vehicleMasterRepository.getVehicleDetails(pdn.getTruck_no()).getVehicle_no());
		}else {pdn.setVehicleno("None");}
 		
 		pdn.setModified_type("INSERTED");
 		pdn.setInserted_by(op.get().getInserted_by());
 		pdn.setInserted_on(op.get().getInserted_on());
 		pdn.setUpdated_by(userRepository.getUserDetails(pdn.getUsername()).getName());
 		pdn.setUpdated_on(ldt);
 		pdn.setDeleted_by("NA");
 		pdn.setDeleted_on(ldt);
 		
 		if(op.isPresent())
		{
			pdn.setId(id);
		}
 		
		//Dynamic
 		pur_debit_note_item_detailsReposiitory.updatePur_debit_note_item_details(op.get().getDebitnoteid());
		Set<Pur_debit_note_item_details> pdnItem = new HashSet<Pur_debit_note_item_details>();
		pdnItem.addAll(pdn.getPur_debit_note_item_details());
		for(Pur_debit_note_item_details pdni : pdnItem)
		{
			pdni.setDebitnoteid(op.get().getDebitnoteid());
			pdni.setDebitnoteno(pdn.getDebitnoteno());
			
			if(pdni.getAdv_item_code().compareTo("0") !=0 && pdni.getAdv_item_code().compareTo("") !=0 && pdni.getAdv_item_code() !=null) {
				pdni.setAdv_item_name(item_masterRepository.itemNameById(pdni.getAdv_item_code()).getItem_name());
			}else {pdni.setAdv_item_name("None");}
			
			//pdni.setAdv_item_name(item_masterRepository.itemNameById(pdni.getAdv_item_code()).getItem_name());
			if(pdni.getAdv_packing_item().compareTo("")!=0 && pdni.getAdv_packing_item().compareTo("0")!=0) {
				pdni.setAdv_packing_item_name(item_masterRepository.itemNameById(pdni.getAdv_packing_item()).getItem_name());
			}else {pdni.setAdv_packing_item_name("None");}
			
			pdni.setCompany_id(pdn.getCompany_id());
			pdni.setFin_year(pdn.getFin_year());
			pdni.setModified_type(pdn.getModified_type());
			pdni.setInserted_by(pdn.getInserted_by());
			pdni.setInserted_on(pdn.getInserted_on());
			pdni.setUpdated_by(pdn.getUpdated_by());
			pdni.setUpdated_on(pdn.getUpdated_on());
			pdni.setDeleted_by(pdn.getDeleted_by());
			pdni.setDeleted_on(pdn.getDeleted_on());
		}
		pdn.setPur_debit_note_item_details(pdnItem);
		
		//Dynamic
		pur_debit_note_musterroll_detailsRepository.updatePur_debit_note_musterroll_details(op.get().getDebitnoteid());
		Set<Pur_debit_note_musterroll_details> pdnMuster = new HashSet<Pur_debit_note_musterroll_details>();
		pdnMuster.addAll(pdn.getPur_debit_note_musterroll_details());
		for(Pur_debit_note_musterroll_details pdnm : pdnMuster)
		{
			pdnm.setDebitnoteid(op.get().getDebitnoteid());
			pdnm.setDebitnoteno(pdn.getDebitnoteno());
			pdnm.setCompany_id(pdn.getCompany_id());
			pdnm.setFin_year(pdn.getFin_year());
			pdnm.setModified_type(pdn.getModified_type());
			pdnm.setInserted_by(pdn.getInserted_by());
			pdnm.setInserted_on(pdn.getInserted_on());
			pdnm.setUpdated_by(pdn.getUpdated_by());
			pdnm.setUpdated_on(pdn.getUpdated_on());
			pdnm.setDeleted_by(pdn.getDeleted_by());
			pdnm.setDeleted_on(pdn.getDeleted_on());
		}
		pdn.setPur_debit_note_musterroll_details(pdnMuster);
		
		//Dynamic
		pur_debit_note_broker_detailsRepository.updatePur_debit_note_broker_details(op.get().getDebitnoteid());
		Set<Pur_debit_note_broker_details> pdnBroker = new HashSet<Pur_debit_note_broker_details>();
		pdnBroker.addAll(pdn.getPur_debit_note_broker_details());
		for(Pur_debit_note_broker_details pdnb : pdnBroker)
		{
			pdnb.setDebitnoteid(op.get().getDebitnoteid());
			pdnb.setDebitnoteno(pdn.getDebitnoteno());
			pdnb.setCompany_id(pdn.getCompany_id());
			pdnb.setFin_year(pdn.getFin_year());
			pdnb.setModified_type(pdn.getModified_type());
			pdnb.setInserted_by(pdn.getInserted_by());
			pdnb.setInserted_on(pdn.getInserted_on());
			pdnb.setUpdated_by(pdn.getUpdated_by());
			pdnb.setUpdated_on(pdn.getUpdated_on());
			pdnb.setDeleted_by(pdn.getDeleted_by());
			pdnb.setDeleted_on(pdn.getDeleted_on());
		}
		pdn.setPur_debit_note_broker_details(pdnBroker);
		
		//Dynamic
		pur_debit_note_docsRepository.updatePur_debit_note_docs(op.get().getDebitnoteid());
		Set<Pur_debit_note_docs> pdnDoc = new HashSet<Pur_debit_note_docs>();
		pdnDoc.addAll(pdn.getPur_debit_note_docs());
		for(Pur_debit_note_docs pdnd : pdnDoc)
		{
			pdnd.setDebitnoteid(op.get().getDebitnoteid());
			pdnd.setDebitnoteno(pdn.getDebitnoteno());
			pdnd.setCompany_id(pdn.getCompany_id());
			pdnd.setFin_year(pdn.getFin_year());
			pdnd.setModified_type(pdn.getModified_type());
			pdnd.setInserted_by(pdn.getInserted_by());
			pdnd.setInserted_on(pdn.getInserted_on());
			pdnd.setUpdated_by(pdn.getUpdated_by());
			pdnd.setUpdated_on(pdn.getUpdated_on());
			pdnd.setDeleted_by(pdn.getDeleted_by());
			pdnd.setDeleted_on(pdn.getDeleted_on());
		}
		pdn.setPur_debit_note_docs(pdnDoc);
		
		//Static
		pur_debit_note_tax_infoRepository.updatePur_debit_note_tax_info(op.get().getDebitnoteid());
		Set<Pur_debit_note_tax_info> prnTax = new HashSet<Pur_debit_note_tax_info>();
		prnTax.add(pdn.getPur_debit_note_tax_info());
		for(Pur_debit_note_tax_info pdnt : prnTax)
		{
			pdnt.setDebitnoteid(op.get().getDebitnoteid());
			pdnt.setDebitnoteno(pdn.getDebitnoteno());
			pdnt.setCompany_id(pdn.getCompany_id());
			pdnt.setFin_year(pdn.getFin_year());
			pdnt.setModified_type(pdn.getModified_type());
			pdnt.setInserted_by(pdn.getInserted_by());
			pdnt.setInserted_on(pdn.getInserted_on());
			pdnt.setUpdated_by(pdn.getUpdated_by());
			pdnt.setUpdated_on(pdn.getUpdated_on());
			pdnt.setDeleted_by(pdn.getDeleted_by());
			pdnt.setDeleted_on(pdn.getDeleted_on());
		}
		
		if(!prnTax.isEmpty())
		{
			pdn.setPur_debit_note_tax_info(prnTax.iterator().next());
		}
		
		//Static
		pur_debit_note_bp_detailsRepository.updatePur_debit_note_bp_details(op.get().getDebitnoteid());
		Set<Pur_debit_note_bp_details> prnBuss = new HashSet<Pur_debit_note_bp_details>();
		prnBuss.add(pdn.getPur_debit_note_bp_details());
		for(Pur_debit_note_bp_details pdnbp : prnBuss)
		{
			pdnbp.setDebitnoteid(op.get().getDebitnoteid());
			pdnbp.setDebitnoteno(pdn.getDebitnoteno());
			pdnbp.setCompany_id(pdn.getCompany_id());
			pdnbp.setFin_year(pdn.getFin_year());
			pdnbp.setModified_type(pdn.getModified_type());
			pdnbp.setInserted_by(pdn.getInserted_by());
			pdnbp.setInserted_on(pdn.getInserted_on());
			pdnbp.setUpdated_by(pdn.getUpdated_by());
			pdnbp.setUpdated_on(pdn.getUpdated_on());
			pdnbp.setDeleted_by(pdn.getDeleted_by());
			pdnbp.setDeleted_on(pdn.getDeleted_on());
		}
		
		if(!prnBuss.isEmpty())
		{
			pdn.setPur_debit_note_bp_details(prnBuss.iterator().next());
		}
		
		//Static
		pur_debit_note_account_infoRepository.updatePur_debit_note_account_info(op.get().getDebitnoteid());
		Set<Pur_debit_note_account_info> prnAcc = new HashSet<Pur_debit_note_account_info>();
		prnAcc.add(pdn.getPur_debit_note_account_info());
		for(Pur_debit_note_account_info pdna : prnAcc)
		{
			pdna.setDebitnoteid(op.get().getDebitnoteid());
			pdna.setDebitnoteno(pdn.getDebitnoteno());
			pdna.setCompany_id(pdn.getCompany_id());
			pdna.setFin_year(pdn.getFin_year());
			pdna.setModified_type(pdn.getModified_type());
			pdna.setInserted_by(pdn.getInserted_by());
			pdna.setInserted_on(pdn.getInserted_on());
			pdna.setUpdated_by(pdn.getUpdated_by());
			pdna.setUpdated_on(pdn.getUpdated_on());
			pdna.setDeleted_by(pdn.getDeleted_by());
			pdna.setDeleted_on(pdn.getDeleted_on());
		}
		
		if(!prnAcc.isEmpty())
		{
			pdn.setPur_debit_note_account_info(prnAcc.iterator().next());
		}
		
 		return pur_debit_noteRepository.save(pdn);
	}
	
	
}
