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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Pur_debit_note;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_weight_dtl;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note_docs;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note_shipment_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note_supplier_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note_trans_dyn;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note_trans_info;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note_weight_dtl;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_note_broker_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_note_docsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_note_item_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_note_shipment_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_note_supplier_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_note_trans_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_note_trans_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_note_weight_dtlRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_trans_dynDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_supplier_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_trans_dynDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service
@Repository
public class Pur_return_noteService_Imp implements Pur_return_noteService{
	
	@Autowired
	Pur_return_noteRepository pur_return_noteRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;

	@Autowired
	Pur_return_note_trans_infoRepository pur_return_note_trans_infoRepository;
	
	@Autowired
	Pur_return_note_weight_dtlRepository pur_return_note_weight_dtlRepository;
	
	@Autowired
	Pur_return_note_shipment_dtlsRepository pur_return_note_shipment_dtlsRepository;
	
	@Autowired
	Pur_return_note_supplier_dtlsRepository pur_return_note_supplier_dtlsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Pur_return_approval_noteRepository pur_return_approval_noteRepository; 
	
	public SequenceIdDTO getPRNSequenceId(String prdate)
	{
		int slno=0;String prefix="PRN";
		String sno=pur_return_noteRepository.countPRNOrder(prdate);
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		
		String date[] = prdate.split("-");
		prefix=prefix+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Pur_return_note save(Pur_return_note pran)
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;
		
		if(pur_return_noteRepository.countId() != null)
		{
			slno=Long.parseLong(pur_return_noteRepository.countId());
		}
		String prefix="PRN";
 		String gen_sno=UniqueID.uniqueid(prefix, slno);
 		pran.setPurreturnnoteid(gen_sno);
 		
 		/*Start checking transaction no for unique */
		System.err.println("First:>>"+pran.getPurreturnnoteno());
		long nslno=0;String tprefix="PRN";
		String tsno=pur_return_noteRepository.countPRNOrder(pran.getPurreturnnotedate());
		
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = pran.getPurreturnnotedate().split("-");
		tprefix=tprefix+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		pran.setPurreturnnoteno(gen_tslno);
		System.err.println("Last:>>>"+pran.getPurreturnnoteno());
		/*End checking transaction no for unique */
		
		if(pran.getSupplierid().compareTo("0") !=0 && pran.getSupplierid().compareTo("") !=0 && pran.getSupplierid() !=null) {
			pran.setSuppliername(supp_bussiness_partnerRepository.getSupplierName(pran.getSupplierid()).getBp_name());
		}else {pran.setSuppliername("None");}
		
		if(pran.getBusinessunit().compareTo("0") !=0 && pran.getBusinessunit().compareTo("") !=0 && pran.getBusinessunit() !=null) {
			pran.setBusinessunitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pran.getBusinessunit()).getBusinessunit_name());
		}else {pran.setBusinessunitname("None");}
 		
 		pran.setModified_type("INSERTED");
 		pran.setInserted_by(userRepository.getUserDetails(pran.getUsername()).getName());
 		pran.setInserted_on(ldt);
 		pran.setUpdated_by("NA");
 		pran.setUpdated_on(ldt);
 		pran.setDeleted_by("NA");
 		pran.setDeleted_on(ldt);
 		
 		//Dynamic
		Set<Pur_return_note_item_dtls> prnItems = new HashSet<Pur_return_note_item_dtls>();
		prnItems.addAll(pran.getPur_return_note_item_dtls());
		for(Pur_return_note_item_dtls praid : prnItems)
		{
			praid.setPurreturnnoteid(pran.getPurreturnnoteid());
			praid.setPurreturnnoteno(pran.getPurreturnnoteno());
			praid.setItemname(item_masterRepository.itemNameById(praid.getItemcode()).getItem_name());
			if(praid.getPacking().compareTo("")!=0 && praid.getPacking().compareTo("0")!=0) {
				praid.setPackingname(item_masterRepository.itemNameById(praid.getPacking()).getItem_name());
			}
			praid.setCompany_id(pran.getCompany_id());
			praid.setFin_year(pran.getFin_year());
			praid.setModified_type(pran.getModified_type());
			praid.setInserted_by(pran.getInserted_by());
			praid.setInserted_on(pran.getInserted_on());
			praid.setUpdated_by(pran.getUpdated_by());
			praid.setUpdated_on(pran.getUpdated_on());
			praid.setDeleted_by(pran.getDeleted_by());
			praid.setDeleted_on(pran.getDeleted_on());
		}
		pran.setPur_return_note_item_dtls(prnItems);
		
		//Static
		Set<Pur_return_note_trans_info> prnTrans = new HashSet<Pur_return_note_trans_info>();
		prnTrans.add(pran.getPur_return_note_trans_info());
		for(Pur_return_note_trans_info prntinfo : prnTrans)
		{
			prntinfo.setPurreturnnoteid(pran.getPurreturnnoteid());
			prntinfo.setPurreturnnoteno(pran.getPurreturnnoteno());
			prntinfo.setCompany_id(pran.getCompany_id());
			prntinfo.setFin_year(pran.getFin_year());
			prntinfo.setModified_type(pran.getModified_type());
			prntinfo.setInserted_by(pran.getInserted_by());
			prntinfo.setInserted_on(pran.getInserted_on());
			prntinfo.setUpdated_by(pran.getUpdated_by());
			prntinfo.setUpdated_on(pran.getUpdated_on());
			prntinfo.setDeleted_by(pran.getDeleted_by());
			prntinfo.setDeleted_on(pran.getDeleted_on());
		}
		
		if(!prnTrans.isEmpty())
		{
			pran.setPur_return_note_trans_info(prnTrans.iterator().next());
		}
 		
		//Dynamic
		Set<Pur_return_note_broker_dtls> prnBroker = new HashSet<Pur_return_note_broker_dtls>();
		prnBroker.addAll(pran.getPur_return_note_broker_dtls());
		for(Pur_return_note_broker_dtls prabd : prnBroker)
		{
			prabd.setPurreturnnoteid(pran.getPurreturnnoteid());
			prabd.setPurreturnnoteno(pran.getPurreturnnoteno());
			prabd.setCompany_id(pran.getCompany_id());
			prabd.setFin_year(pran.getFin_year());
			prabd.setModified_type(pran.getModified_type());
			prabd.setInserted_by(pran.getInserted_by());
			prabd.setInserted_on(pran.getInserted_on());
			prabd.setUpdated_by(pran.getUpdated_by());
			prabd.setUpdated_on(pran.getUpdated_on());
			prabd.setDeleted_by(pran.getDeleted_by());
			prabd.setDeleted_on(pran.getDeleted_on());
		}
		pran.setPur_return_note_broker_dtls(prnBroker);
		
		//Static
		Set<Pur_return_note_weight_dtl> prnWt = new HashSet<Pur_return_note_weight_dtl>();
		prnWt.add(pran.getPur_return_note_weight_dtl());
		for(Pur_return_note_weight_dtl prnwd : prnWt)
		{
			prnwd.setPurreturnnoteid(pran.getPurreturnnoteid());
			prnwd.setPurreturnnoteno(pran.getPurreturnnoteno());
			prnwd.setCompany_id(pran.getCompany_id());
			prnwd.setFin_year(pran.getFin_year());
			prnwd.setModified_type(pran.getModified_type());
			prnwd.setInserted_by(pran.getInserted_by());
			prnwd.setInserted_on(pran.getInserted_on());
			prnwd.setUpdated_by(pran.getUpdated_by());
			prnwd.setUpdated_on(pran.getUpdated_on());
			prnwd.setDeleted_by(pran.getDeleted_by());
			prnwd.setDeleted_on(pran.getDeleted_on());
		}
		
		if(!prnWt.isEmpty())
		{
			pran.setPur_return_note_weight_dtl(prnWt.iterator().next());
		}
		
		//Static
		Set<Pur_return_note_shipment_dtls> prnSd = new HashSet<Pur_return_note_shipment_dtls>();
		prnSd.add(pran.getPur_return_note_shipment_dtls());
		for(Pur_return_note_shipment_dtls prnSdt : prnSd)
		{
			prnSdt.setPurreturnnoteid(pran.getPurreturnnoteid());
			prnSdt.setPurreturnnoteno(pran.getPurreturnnoteno());
			prnSdt.setCompany_id(pran.getCompany_id());
			prnSdt.setFin_year(pran.getFin_year());
			prnSdt.setModified_type(pran.getModified_type());
			prnSdt.setInserted_by(pran.getInserted_by());
			prnSdt.setInserted_on(pran.getInserted_on());
			prnSdt.setUpdated_by(pran.getUpdated_by());
			prnSdt.setUpdated_on(pran.getUpdated_on());
			prnSdt.setDeleted_by(pran.getDeleted_by());
			prnSdt.setDeleted_on(pran.getDeleted_on());
		}
		
		if(!prnSd.isEmpty())
		{
			pran.setPur_return_note_shipment_dtls(prnSd.iterator().next());
		}
		
		//Static
		Set<Pur_return_note_supplier_dtls> prnSupp = new HashSet<Pur_return_note_supplier_dtls>();
		prnSupp.addAll(pran.getPur_return_note_supplier_dtls());
		for(Pur_return_note_supplier_dtls prnSdtls : prnSupp)
		{
			prnSdtls.setPurreturnnoteid(pran.getPurreturnnoteid());
			prnSdtls.setPurreturnnoteno(pran.getPurreturnnoteno());
			prnSdtls.setCompany_id(pran.getCompany_id());
			prnSdtls.setFin_year(pran.getFin_year());
			prnSdtls.setModified_type(pran.getModified_type());
			prnSdtls.setInserted_by(pran.getInserted_by());
			prnSdtls.setInserted_on(pran.getInserted_on());
			prnSdtls.setUpdated_by(pran.getUpdated_by());
			prnSdtls.setUpdated_on(pran.getUpdated_on());
			prnSdtls.setDeleted_by(pran.getDeleted_by());
			prnSdtls.setDeleted_on(pran.getDeleted_on());
		}
		
		pran.setPur_return_note_supplier_dtls(prnSupp);
		
		//Dynamic
		Set<Pur_return_note_docs> prnDocs = new HashSet<Pur_return_note_docs>();
		prnDocs.addAll(pran.getPur_return_note_docs());
		for(Pur_return_note_docs praDoc : prnDocs)
		{
			praDoc.setPurreturnnoteid(pran.getPurreturnnoteid());
			praDoc.setPurreturnnoteno(pran.getPurreturnnoteno());
			praDoc.setCompany_id(pran.getCompany_id());
			praDoc.setFin_year(pran.getFin_year());
			praDoc.setModified_type(pran.getModified_type());
			praDoc.setInserted_by(pran.getInserted_by());
			praDoc.setInserted_on(pran.getInserted_on());
			praDoc.setUpdated_by(pran.getUpdated_by());
			praDoc.setUpdated_on(pran.getUpdated_on());
			praDoc.setDeleted_by(pran.getDeleted_by());
			praDoc.setDeleted_on(pran.getDeleted_on());
		}
		pran.setPur_return_note_docs(prnDocs);
				
		//Update Rtn App Note Status
		pur_return_approval_noteRepository.updatePurRtnAppStatus(pran.getReferance_id());
		
 		return pur_return_noteRepository.save(pran);
		
	}
	
	public Pur_return_note findOne(Long id) {
		Optional<Pur_return_note> op=this.pur_return_noteRepository.findById(id);
		return op.get();
	}
	
	public List<Pur_return_note> findAllRtnNotes(String company,String finyear)
	{
		List<Pur_return_note> rtnNotesList=new ArrayList<Pur_return_note>();
		
		rtnNotesList.addAll(pur_return_noteRepository.findAllRtnNotes(company,finyear));
		
		return rtnNotesList;
	}
	
	public Pur_return_noteDTO getPurRtnNoteDtls(String purreturnnoteid)
	{
		Pur_return_note prnDetails=pur_return_noteRepository.getPurRtnNoteDtls(purreturnnoteid);
		Type listType=new TypeToken<Pur_return_noteDTO>() {}.getType();
		Pur_return_noteDTO prnDtls=new ModelMapper().map(prnDetails, listType);
		return prnDtls;
	}
	
	
	public List<Pur_return_noteDTO> getPurReturnNotes(String bunit,String invdate,String company,String finyear)
	{
		List<Pur_return_note> appNote=new ArrayList<Pur_return_note>();
		System.err.println("Got Input:"+bunit+","+invdate+","+company+","+finyear);
		appNote.addAll(pur_return_noteRepository.getPurReturnNotes(bunit,invdate,company,finyear));
			
		Type listType=new TypeToken<List<Pur_return_noteDTO>>() {}.getType();
		
		List<Pur_return_noteDTO> appNoteList=new ModelMapper().map(appNote,listType);
		
		return appNoteList;
	}
	
	public List<Pur_return_note_item_dtlsDTO> getPurRtnNoteItemDtls(String purreturnnoteid)
	{
		List<Pur_return_note_item_dtls> modelList=pur_return_note_item_dtlsRepository.getPur_return_note_item_dtls(purreturnnoteid);
		
		Type listType=new TypeToken<List<Pur_return_note_item_dtlsDTO>>() {}.getType();
		
		List<Pur_return_note_item_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Pur_return_note_broker_dtlsDTO> getPurRtnNoteBrokerDtls(String purreturnnoteid)
	{
		List<Pur_return_note_broker_dtls> modelList=pur_return_note_broker_dtlsRepository.getPur_return_note_broker_dtls(purreturnnoteid);
		
		Type listType=new TypeToken<List<Pur_return_note_broker_dtlsDTO>>() {}.getType();
		
		List<Pur_return_note_broker_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Pur_return_note_docsDTO> getPurRtnNoteDocs(String purreturnnoteid)
	{
		List<Pur_return_note_docs> modelList=pur_return_note_docsRepository.getPur_return_note_docs(purreturnnoteid);
		
		Type listType=new TypeToken<List<Pur_return_note_docsDTO>>() {}.getType();
		
		List<Pur_return_note_docsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Pur_return_note_trans_dynDTO> getPurRtnNoteTransDyn(String purreturnnoteid)
	{
		List<Pur_return_note_trans_dyn> modelList=new ArrayList<Pur_return_note_trans_dyn>();
		modelList.addAll(pur_return_note_trans_dynRepository.getPur_return_note_trans_dyn(purreturnnoteid));
			
		Type listType=new TypeToken<List<Pur_return_note_trans_dynDTO>>() {}.getType();
		
		List<Pur_return_note_trans_dynDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public Pur_return_note_trans_infoDTO getPurRtnNoteTransInfo(String purreturnnoteid)
	{
		Pur_return_note_trans_info modelList=pur_return_note_trans_infoRepository.getPur_return_note_trans_info(purreturnnoteid);
		
		Type listType=new TypeToken<Pur_return_note_trans_infoDTO>() {}.getType();
		
		Pur_return_note_trans_infoDTO itemNameList=new ModelMapper().map(modelList, listType);
		 
		return itemNameList;
	}
	
	public Pur_return_note_weight_dtlDTO getPurRtnNoteWeDtls(String purreturnnoteid)
	{
		Pur_return_note_weight_dtl modelList=pur_return_note_weight_dtlRepository.getPur_return_note_weight_dtl(purreturnnoteid);
		
		Type listType=new TypeToken<Pur_return_note_weight_dtlDTO>() {}.getType();
		
		Pur_return_note_weight_dtlDTO itemNameList=new ModelMapper().map(modelList, listType);
		 
		return itemNameList;
		
	}
	
	public Pur_return_note_shipment_dtlsDTO getPurRtnNoteShipDtls(String purreturnnoteid)
	{
		Pur_return_note_shipment_dtls modelList=pur_return_note_shipment_dtlsRepository.getPur_return_note_shipment_dtls(purreturnnoteid);
		
		Type listType=new TypeToken<Pur_return_note_shipment_dtlsDTO>() {}.getType();
		
		Pur_return_note_shipment_dtlsDTO itemNameList=new ModelMapper().map(modelList, listType);
		 
		return itemNameList;
		
	}
	
	public Pur_return_note_supplier_dtlsDTO getPurRtnNoteSuppDtls(String purreturnnoteid)
	{
		Pur_return_note_supplier_dtls modelList=pur_return_note_supplier_dtlsRepository.getPur_return_note_supplier_dtls(purreturnnoteid);
		
		Type listType=new TypeToken<Pur_return_note_supplier_dtlsDTO>() {}.getType();
		
		Pur_return_note_supplier_dtlsDTO itemNameList=new ModelMapper().map(modelList, listType);
		 
		return itemNameList;
		
	}
	
	@Autowired
	Pur_return_note_item_dtlsRepository pur_return_note_item_dtlsRepository;
	
	@Autowired
	Pur_return_note_broker_dtlsRepository pur_return_note_broker_dtlsRepository;
	
	@Autowired
	Pur_return_note_docsRepository pur_return_note_docsRepository;
	
	@Autowired
	Pur_return_note_trans_dynRepository pur_return_note_trans_dynRepository;
	
	
	@Transactional
	public Pur_return_note update(Pur_return_note pran,long id)
	{
		Optional<Pur_return_note> op=pur_return_noteRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(pran.getSupplierid().compareTo("0") !=0 && pran.getSupplierid().compareTo("") !=0 && pran.getSupplierid() !=null) {
			pran.setSuppliername(supp_bussiness_partnerRepository.getSupplierName(pran.getSupplierid()).getBp_name());
		}else {pran.setSuppliername("None");}
		
		if(pran.getBusinessunit().compareTo("0") !=0 && pran.getBusinessunit().compareTo("") !=0 && pran.getBusinessunit() !=null) {
			pran.setBusinessunitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pran.getBusinessunit()).getBusinessunit_name());
		}else {pran.setBusinessunitname("None");}
		
 		pran.setPurreturnnoteid(op.get().getPurreturnnoteid());
		pran.setPurreturnnoteno(pran.getPurreturnnoteno());
 		pran.setModified_type("INSERTED");
 		pran.setInserted_by(userRepository.getUserDetails(pran.getUsername()).getName());
 		pran.setInserted_on(ldt);
 		pran.setUpdated_by("NA");
 		pran.setUpdated_on(ldt);
 		pran.setDeleted_by("NA");
 		pran.setDeleted_on(ldt);
 		
 		if(op.isPresent())
		{
			pran.setId(id);
		}
 		
 		//Dynamic
 		pur_return_note_item_dtlsRepository.updatePur_return_note_item_dtls(pran.getPurreturnnoteid());
		Set<Pur_return_note_item_dtls> prnItems = new HashSet<Pur_return_note_item_dtls>();
		prnItems.addAll(pran.getPur_return_note_item_dtls());
		for(Pur_return_note_item_dtls praid : prnItems)
		{
			praid.setPurreturnnoteid(pran.getPurreturnnoteid());
			praid.setPurreturnnoteno(pran.getPurreturnnoteno());
			praid.setItemname(item_masterRepository.itemNameById(praid.getItemcode()).getItem_name());
			if(praid.getPacking().compareTo("")!=0 && praid.getPacking().compareTo("0")!=0) {
				praid.setPackingname(item_masterRepository.itemNameById(praid.getPacking()).getItem_name());
			}
			praid.setCompany_id(pran.getCompany_id());
			praid.setFin_year(pran.getFin_year());
			praid.setModified_type(pran.getModified_type());
			praid.setInserted_by(pran.getInserted_by());
			praid.setInserted_on(pran.getInserted_on());
			praid.setUpdated_by(pran.getUpdated_by());
			praid.setUpdated_on(pran.getUpdated_on());
			praid.setDeleted_by(pran.getDeleted_by());
			praid.setDeleted_on(pran.getDeleted_on());
		}
		pran.setPur_return_note_item_dtls(prnItems);
		
		//Static
		pur_return_note_trans_infoRepository.updatePur_return_note_trans_info(pran.getPurreturnnoteid());
		Set<Pur_return_note_trans_info> prnTrans = new HashSet<Pur_return_note_trans_info>();
		prnTrans.add(pran.getPur_return_note_trans_info());
		for(Pur_return_note_trans_info prntinfo : prnTrans)
		{
			prntinfo.setPurreturnnoteid(pran.getPurreturnnoteid());
			prntinfo.setPurreturnnoteno(pran.getPurreturnnoteno());
			prntinfo.setCompany_id(pran.getCompany_id());
			prntinfo.setFin_year(pran.getFin_year());
			prntinfo.setModified_type(pran.getModified_type());
			prntinfo.setInserted_by(pran.getInserted_by());
			prntinfo.setInserted_on(pran.getInserted_on());
			prntinfo.setUpdated_by(pran.getUpdated_by());
			prntinfo.setUpdated_on(pran.getUpdated_on());
			prntinfo.setDeleted_by(pran.getDeleted_by());
			prntinfo.setDeleted_on(pran.getDeleted_on());
		}
		
		if(!prnTrans.isEmpty())
		{
			pran.setPur_return_note_trans_info(prnTrans.iterator().next());
		}
 		
		//Dynamic
		pur_return_note_broker_dtlsRepository.updatePur_return_note_broker_dtls(pran.getPurreturnnoteid());
		Set<Pur_return_note_broker_dtls> prnBroker = new HashSet<Pur_return_note_broker_dtls>();
		prnBroker.addAll(pran.getPur_return_note_broker_dtls());
		for(Pur_return_note_broker_dtls prabd : prnBroker)
		{
			prabd.setPurreturnnoteid(pran.getPurreturnnoteid());
			prabd.setPurreturnnoteno(pran.getPurreturnnoteno());
			prabd.setCompany_id(pran.getCompany_id());
			prabd.setFin_year(pran.getFin_year());
			prabd.setModified_type(pran.getModified_type());
			prabd.setInserted_by(pran.getInserted_by());
			prabd.setInserted_on(pran.getInserted_on());
			prabd.setUpdated_by(pran.getUpdated_by());
			prabd.setUpdated_on(pran.getUpdated_on());
			prabd.setDeleted_by(pran.getDeleted_by());
			prabd.setDeleted_on(pran.getDeleted_on());
		}
		pran.setPur_return_note_broker_dtls(prnBroker);
		
		//Static
		pur_return_note_weight_dtlRepository.updatePur_return_note_weight_dtl(pran.getPurreturnnoteid());
		Set<Pur_return_note_weight_dtl> prnWt = new HashSet<Pur_return_note_weight_dtl>();
		prnWt.add(pran.getPur_return_note_weight_dtl());
		for(Pur_return_note_weight_dtl prnwd : prnWt)
		{
			prnwd.setPurreturnnoteid(pran.getPurreturnnoteid());
			prnwd.setPurreturnnoteno(pran.getPurreturnnoteno());
			prnwd.setCompany_id(pran.getCompany_id());
			prnwd.setFin_year(pran.getFin_year());
			prnwd.setModified_type(pran.getModified_type());
			prnwd.setInserted_by(pran.getInserted_by());
			prnwd.setInserted_on(pran.getInserted_on());
			prnwd.setUpdated_by(pran.getUpdated_by());
			prnwd.setUpdated_on(pran.getUpdated_on());
			prnwd.setDeleted_by(pran.getDeleted_by());
			prnwd.setDeleted_on(pran.getDeleted_on());
		}
		
		if(!prnWt.isEmpty())
		{
			pran.setPur_return_note_weight_dtl(prnWt.iterator().next());
		}
		
		//Static
		pur_return_note_shipment_dtlsRepository.updatePur_return_note_shipment_dtls(pran.getPurreturnnoteid());
		Set<Pur_return_note_shipment_dtls> prnSd = new HashSet<Pur_return_note_shipment_dtls>();
		prnSd.add(pran.getPur_return_note_shipment_dtls());
		for(Pur_return_note_shipment_dtls prnSdt : prnSd)
		{
			prnSdt.setPurreturnnoteid(pran.getPurreturnnoteid());
			prnSdt.setPurreturnnoteno(pran.getPurreturnnoteno());
			prnSdt.setCompany_id(pran.getCompany_id());
			prnSdt.setFin_year(pran.getFin_year());
			prnSdt.setModified_type(pran.getModified_type());
			prnSdt.setInserted_by(pran.getInserted_by());
			prnSdt.setInserted_on(pran.getInserted_on());
			prnSdt.setUpdated_by(pran.getUpdated_by());
			prnSdt.setUpdated_on(pran.getUpdated_on());
			prnSdt.setDeleted_by(pran.getDeleted_by());
			prnSdt.setDeleted_on(pran.getDeleted_on());
		}
		
		if(!prnSd.isEmpty())
		{
			pran.setPur_return_note_shipment_dtls(prnSd.iterator().next());
		}
		
		//Static
		pur_return_note_supplier_dtlsRepository.updatePur_return_note_supplier_dtls(pran.getPurreturnnoteid());
		Set<Pur_return_note_supplier_dtls> prnSupp = new HashSet<Pur_return_note_supplier_dtls>();
		prnSupp.addAll(pran.getPur_return_note_supplier_dtls());
		for(Pur_return_note_supplier_dtls prnSdtls : prnSupp)
		{
			prnSdtls.setPurreturnnoteid(pran.getPurreturnnoteid());
			prnSdtls.setPurreturnnoteno(pran.getPurreturnnoteno());
			prnSdtls.setCompany_id(pran.getCompany_id());
			prnSdtls.setFin_year(pran.getFin_year());
			prnSdtls.setModified_type(pran.getModified_type());
			prnSdtls.setInserted_by(pran.getInserted_by());
			prnSdtls.setInserted_on(pran.getInserted_on());
			prnSdtls.setUpdated_by(pran.getUpdated_by());
			prnSdtls.setUpdated_on(pran.getUpdated_on());
			prnSdtls.setDeleted_by(pran.getDeleted_by());
			prnSdtls.setDeleted_on(pran.getDeleted_on());
		}
		pran.setPur_return_note_supplier_dtls(prnSupp);
		
		//Dynamic
		pur_return_note_docsRepository.updatePur_return_note_docs(pran.getPurreturnnoteid());
		Set<Pur_return_note_docs> prnDocs = new HashSet<Pur_return_note_docs>();
		prnDocs.addAll(pran.getPur_return_note_docs());
		for(Pur_return_note_docs praDoc : prnDocs)
		{
			praDoc.setPurreturnnoteid(pran.getPurreturnnoteid());
			praDoc.setPurreturnnoteno(pran.getPurreturnnoteno());
			praDoc.setCompany_id(pran.getCompany_id());
			praDoc.setFin_year(pran.getFin_year());
			praDoc.setModified_type(pran.getModified_type());
			praDoc.setInserted_by(pran.getInserted_by());
			praDoc.setInserted_on(pran.getInserted_on());
			praDoc.setUpdated_by(pran.getUpdated_by());
			praDoc.setUpdated_on(pran.getUpdated_on());
			praDoc.setDeleted_by(pran.getDeleted_by());
			praDoc.setDeleted_on(pran.getDeleted_on());
		}
		pran.setPur_return_note_docs(prnDocs);
		
				
 		return pur_return_noteRepository.save(pran);
		
	}
	

}
