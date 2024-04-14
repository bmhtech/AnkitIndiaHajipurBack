package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_docs;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_item_dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_party_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_shipment_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_trans_info;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note_weight_dtl;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_item_dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Invoice_typeRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_return_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_return_note_broker_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_return_note_docsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_return_note_item_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_return_note_item_dtls_for_jobworkRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_return_note_party_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_return_note_shipment_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_return_note_trans_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_return_note_weight_dtlRepository;
import com.AnkitIndia.jwtauthentication.repository.Tax_code_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_party_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challanDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Sales_return_note_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_item_dtls;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_noteRepository;

@Service
public class Sales_return_noteService_Imp implements Sales_return_noteService{
	
	@Autowired
	Sales_return_noteRepository sales_return_noteRepository;
	
	@Autowired
	Sales_return_note_broker_dtlsRepository sales_return_note_broker_dtlsRepository;
	
	@Autowired
	Sales_return_note_docsRepository sales_return_note_docsRepository;
	
	@Autowired
	Sales_return_note_item_dtlsRepository sales_return_note_item_dtlsRepository;
	
	@Autowired
	Sales_return_note_party_dtlsRepository sales_return_note_party_dtlsRepository;
	
	@Autowired
	Sales_return_note_shipment_dtlsRepository sales_return_note_shipment_dtlsRepository;
	
	@Autowired
	Sales_return_note_trans_infoRepository sales_return_note_trans_infoRepository;
	
	@Autowired
	Sales_return_note_weight_dtlRepository sales_return_note_weight_dtlRepository;
	
	@Autowired
	Invoice_typeRepository invoice_typeRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Return_approval_noteRepository return_approval_noteRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Sales_credit_noteRepository sales_credit_noteRepository;
	
	@Autowired
	Sales_return_note_item_dtls_for_jobworkRepository sales_return_note_item_dtls_for_jobworkRepository;
	
	@Autowired
	Tax_code_detailsRepository tax_code_detailsRepository;
	
	public SalesSequenceIdDTO getSRSequenceId(String fin_year,String inv_type)
	{
		String prefix=invoice_typeRepository.getSalesInvTypesDtls(inv_type).getInvtype_prefix();
		int slno=0;
		String sno=sales_return_noteRepository.countSalesReturn(fin_year,inv_type);
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String fin_yearspit[]=fin_year.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		
		//prefix="SR-"+prefix+"-"+fin_year+"-";
		prefix="SR-"+prefix+"-"+final_fyear+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		
		
		
		//String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		
		
		Type listType = new TypeToken<SalesSequenceIdDTO>() {}.getType();
		SalesSequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Sales_return_note save(Sales_return_note ran)
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;String prefix="SRN";
		if(sales_return_noteRepository.countId(prefix).isPresent())
		{
			slno=Long.parseLong(sales_return_noteRepository.countId(prefix).get());
		}
 		String gen_sno=UniqueID.uniqueid(prefix, slno);
 		ran.setSalesreturnnoteid(gen_sno);
 		
 		/*Start checking transaction no for unique */
		System.err.println("First:>>"+ran.getSalesreturnnoteno());
		long nslno=0;
		String tprefix=invoice_typeRepository.getSalesInvTypesDtls(ran.getInv_type()).getInvtype_prefix();
		String tsno=sales_return_noteRepository.countSalesReturn(ran.getFin_year(),ran.getInv_type());
				
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String fin_yearspit[]=ran.getFin_year().split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		
		//tprefix="SR-"+tprefix+"-"+ran.getFin_year()+"-";
		
		tprefix="SR-"+tprefix+"-"+final_fyear+"-";
		
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		//String gen_tslno=UniqueIDTransaction.uniqueId6(tprefix,nslno);
		//UniqueIDTransaction.uniqueId6(
		
		ran.setSalesreturnnoteno(gen_tslno);
		System.err.println("Last:>>>"+ran.getSalesreturnnoteno());
		/*End checking transaction no for unique */
 		
 		if(Utility.isNullOrEmpty(ran.getBusinessunit())) {
			ran.setBusinessunitname(companyBusinessUnitMasterRepository.CompanyBUAddress(ran.getBusinessunit()).getBusinessunit_name());
		}else {ran.setBusinessunitname("None");}
 		
 		if(Utility.isNullOrEmpty(ran.getParty())) {
 			ran.setPartyname(cust_bussiness_partnerRepository.getCustomer(ran.getParty()).getCp_name());
 		}else {ran.setPartyname("None");}
 		
 		if(Utility.isNullOrEmpty(ran.getInv_type())) {
 			ran.setInv_typename(invoice_typeRepository.getSalesInvTypesDtls(ran.getInv_type()).getInvtype_name());
		}else {ran.setInv_typename("None");}
 		
 		ran.setModified_type("INSERTED");
 		ran.setInserted_by(userRepository.getUserDetails(ran.getUsername()).getName());
 		ran.setInserted_on(ldt);
 		ran.setUpdated_by("NA");
 		ran.setUpdated_on(ldt);
 		ran.setDeleted_by("NA");
 		ran.setDeleted_on(ldt);
 		ran.setCredit_note_id("0");
 		ran.setCredit_note_status("NO");
 		
		Set<Sales_return_note_broker_dtls> ran1 = new HashSet<Sales_return_note_broker_dtls>();
		ran1.addAll(ran.getSales_return_note_broker_dtls());
		for(Sales_return_note_broker_dtls rabd : ran1)
		{
			rabd.setSalesreturnnoteid(ran.getSalesreturnnoteid());
			rabd.setSalesreturnnoteno(ran.getSalesreturnnoteno());
			rabd.setCompany_id(ran.getCompany_id());
			rabd.setFin_year(ran.getFin_year());
			rabd.setModified_type(ran.getModified_type());
			rabd.setInserted_by(ran.getInserted_by());
			rabd.setInserted_on(ran.getInserted_on());
			rabd.setUpdated_by(ran.getUpdated_by());
			rabd.setUpdated_on(ran.getUpdated_on());
			rabd.setDeleted_by(ran.getDeleted_by());
			rabd.setDeleted_on(ran.getDeleted_on());
		}
		ran.setSales_return_note_broker_dtls(ran1);
		
		Set<Sales_return_note_docs> ran2 = new HashSet<Sales_return_note_docs>();
		ran2.addAll(ran.getSales_return_note_docs());
		for(Sales_return_note_docs rad : ran2)
		{
			rad.setSalesreturnnoteid(ran.getSalesreturnnoteid());
			rad.setSalesreturnnoteno(ran.getSalesreturnnoteno());
			rad.setCompany_id(ran.getCompany_id());
			rad.setFin_year(ran.getFin_year());
			rad.setModified_type(ran.getModified_type());
			rad.setInserted_by(ran.getInserted_by());
			rad.setInserted_on(ran.getInserted_on());
			rad.setUpdated_by(ran.getUpdated_by());
			rad.setUpdated_on(ran.getUpdated_on());
			rad.setDeleted_by(ran.getDeleted_by());
			rad.setDeleted_on(ran.getDeleted_on());
		}
		ran.setSales_return_note_docs(ran2);
		
		if(ran.getInv_type().compareToIgnoreCase("INV00003")==0) 
		{
			ran.getSales_return_note_item_dtls().clear();
			
			Set<Sales_return_note_item_dtls_for_jobwork> jobSet = new HashSet<Sales_return_note_item_dtls_for_jobwork>();
			jobSet.addAll(ran.getSales_return_note_item_dtls_for_jobwork());
			for(Sales_return_note_item_dtls_for_jobwork jobDts : jobSet)
			{
				jobDts.setSalesreturnnoteid(gen_sno);
				jobDts.setSalesreturnnoteno(ran.getSalesreturnnoteno());
				
				jobDts.setJob_item_name(item_masterRepository.itemNameById(jobDts.getJob_item()).getItem_name());
				if(Utility.isNullOrEmpty(jobDts.getJob_packing())) 
				{
					jobDts.setJob_packing_name(item_masterRepository.itemNameById(jobDts.getJob_packing()).getItem_name());
				}
				else
				{
					jobDts.setJob_packing_name("0");
				}
				jobDts.setCompany_id(ran.getCompany_id());
				jobDts.setFin_year(ran.getFin_year());
				jobDts.setModified_type("INSERTED");
				jobDts.setInserted_by(ran.getInserted_by());
				jobDts.setInserted_on(ran.getInserted_on());
				jobDts.setUpdated_by("NA");
				jobDts.setUpdated_on(ldt);
				jobDts.setDeleted_by("NA");
				jobDts.setDeleted_on(ldt);
				
			}
			ran.setSales_return_note_item_dtls_for_jobwork(jobSet);
		}
		else
		{
			ran.getSales_return_note_item_dtls_for_jobwork().clear();
			
			Set<Sales_return_note_item_dtls> ran3 = new HashSet<Sales_return_note_item_dtls>();
			ran3.addAll(ran.getSales_return_note_item_dtls());
			for(Sales_return_note_item_dtls raid : ran3)
			{
				raid.setSalesreturnnoteid(ran.getSalesreturnnoteid());
				raid.setSalesreturnnoteno(ran.getSalesreturnnoteno());
				raid.setItemname(item_masterRepository.itemNameById(raid.getItemcode()).getItem_name());
				if(Utility.isNullOrEmpty(raid.getPacking())) {
					raid.setPackingname(item_masterRepository.itemNameById(raid.getPacking()).getItem_name());
				}else {raid.setPackingname("None");}
				
				if(Utility.isNullOrEmpty(raid.getTaxcode())) {
					raid.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(raid.getTaxcode()).getTax_name());
 				}else {raid.setTaxcode_name("None");}
				
				raid.setCompany_id(ran.getCompany_id());
				raid.setFin_year(ran.getFin_year());
				raid.setModified_type(ran.getModified_type());
				raid.setInserted_by(ran.getInserted_by());
				raid.setInserted_on(ran.getInserted_on());
				raid.setUpdated_by(ran.getUpdated_by());
				raid.setUpdated_on(ran.getUpdated_on());
				raid.setDeleted_by(ran.getDeleted_by());
				raid.setDeleted_on(ran.getDeleted_on());
			}
			ran.setSales_return_note_item_dtls(ran3);
		}
		
		Set<Sales_return_note_party_dtls> ran4 = new HashSet<Sales_return_note_party_dtls>();
		ran4.addAll(ran.getSales_return_note_party_dtls());
		for(Sales_return_note_party_dtls rapd : ran4) 				{
			rapd.setSalesreturnnoteid(ran.getSalesreturnnoteid());
			rapd.setSalesreturnnoteno(ran.getSalesreturnnoteno());
			rapd.setCompany_id(ran.getCompany_id());
			rapd.setFin_year(ran.getFin_year());
			rapd.setModified_type(ran.getModified_type());
			rapd.setInserted_by(ran.getInserted_by());
			rapd.setInserted_on(ran.getInserted_on());
			rapd.setUpdated_by(ran.getUpdated_by());
			rapd.setUpdated_on(ran.getUpdated_on());
			rapd.setDeleted_by(ran.getDeleted_by());
			rapd.setDeleted_on(ran.getDeleted_on());
		}
		ran.setSales_return_note_party_dtls(ran4);
		
		Set<Sales_return_note_shipment_dtls> ran5 = new HashSet<Sales_return_note_shipment_dtls>();
		ran5.add(ran.getSales_return_note_shipment_dtls());
		for(Sales_return_note_shipment_dtls rasd : ran5)
		{
			rasd.setSalesreturnnoteid(ran.getSalesreturnnoteid());
			rasd.setSalesreturnnoteno(ran.getSalesreturnnoteno());
			rasd.setCompany_id(ran.getCompany_id());
			rasd.setFin_year(ran.getFin_year());
			rasd.setModified_type(ran.getModified_type());
			rasd.setInserted_by(ran.getInserted_by());
			rasd.setInserted_on(ran.getInserted_on());
			rasd.setUpdated_by(ran.getUpdated_by());
			rasd.setUpdated_on(ran.getUpdated_on());
			rasd.setDeleted_by(ran.getDeleted_by());
			rasd.setDeleted_on(ran.getDeleted_on());
		}
		if(!ran5.isEmpty())	{
			ran.setSales_return_note_shipment_dtls(ran5.iterator().next());
		}
		
		Set<Sales_return_note_trans_info> ran6 = new HashSet<Sales_return_note_trans_info>();
		ran6.add(ran.getSales_return_note_trans_info());
		for(Sales_return_note_trans_info rati : ran6)
		{
			rati.setSalesreturnnoteid(ran.getSalesreturnnoteid());
			rati.setSalesreturnnoteno(ran.getSalesreturnnoteno());
			rati.setCompany_id(ran.getCompany_id());
			rati.setFin_year(ran.getFin_year());
			rati.setModified_type(ran.getModified_type());
			rati.setInserted_by(ran.getInserted_by());
			rati.setInserted_on(ran.getInserted_on());
			rati.setUpdated_by(ran.getUpdated_by());
			rati.setUpdated_on(ran.getUpdated_on());
			rati.setDeleted_by(ran.getDeleted_by());
			rati.setDeleted_on(ran.getDeleted_on());
		}
		if(!ran6.isEmpty())	{
			ran.setSales_return_note_trans_info(ran6.iterator().next());
		}
		
		Set<Sales_return_note_weight_dtl> ran7 = new HashSet<Sales_return_note_weight_dtl>();
		ran7.add(ran.getSales_return_note_weight_dtl());
		for(Sales_return_note_weight_dtl rawd : ran7)
		{
			rawd.setSalesreturnnoteid(ran.getSalesreturnnoteid());
			rawd.setSalesreturnnoteno(ran.getSalesreturnnoteno());
			rawd.setCompany_id(ran.getCompany_id());
			rawd.setFin_year(ran.getFin_year());
			rawd.setModified_type(ran.getModified_type());
			rawd.setInserted_by(ran.getInserted_by());
			rawd.setInserted_on(ran.getInserted_on());
			rawd.setUpdated_by(ran.getUpdated_by());
			rawd.setUpdated_on(ran.getUpdated_on());
			rawd.setDeleted_by(ran.getDeleted_by());
			rawd.setDeleted_on(ran.getDeleted_on());
		}
		if(!ran7.isEmpty())	{
			ran.setSales_return_note_weight_dtl(ran7.iterator().next());
		}
		
		//Update Rtn App Note Status
		return_approval_noteRepository.updateRtnAppStatus(ran.getReferance_id());

		return sales_return_noteRepository.save(ran);
	}
	
	public List<Sales_return_noteDTO> getSalesRtnNote(String bunit,String party,String invdate,String company,String finyear,String invoicetype)
	{
		List<Sales_return_note> appNote=new ArrayList<Sales_return_note>();
		
		appNote.addAll(sales_return_noteRepository.getSalesRtnNote(bunit,company,finyear,invdate,party,invoicetype));
		
		appNote.forEach(e->{
			//System.out.println("reff id::"+return_approval_noteRepository.getReturnApprovalDtls(e.getReferance_id()).getSalesreturnrefno());
			e.setSalesreturnnoterefno(return_approval_noteRepository.getReturnApprovalDtls(e.getReferance_id()).getSalesreturnrefno());
		});
		
		Type listType=new TypeToken<List<Sales_return_noteDTO>>() {}.getType();
		List<Sales_return_noteDTO> appNoteList=new ModelMapper().map(appNote,listType);
		
		return appNoteList;
	}
	
	public List<Map<String,Object>> getSalesRtnNoteJw(String date,String bunit,String party_id)
	{
		return sales_return_noteRepository.getSalesRtnNoteJw(date,bunit,party_id);
		
	}
	
	
	public List<Map<String,Object>> getSalesRtnNoteJwdetails(String salereturn)
	{
		return sales_return_noteRepository.getSalesRtnNoteJwdetails(salereturn);
		
	}
	
	public List<Map<String,Object>> getsalereturnjobworkprice(String salereturn)
	{
		return sales_return_noteRepository.getsalereturnjobworkprice(salereturn);
	}
	
	public List<Sales_return_noteDTO> getSalesRtnNoteUpdate(Long id)
	{
		
		Optional<Sales_credit_note> op = sales_credit_noteRepository.findById(id);
		
		String []splitsalesreturnnote=op.get().getReferance_id().split(",");
		
		List<Sales_return_note> appNote=new ArrayList<Sales_return_note>();
		 for(int i=0;i<splitsalesreturnnote.length;i++) 
		 {
			 appNote.add(sales_return_noteRepository.getSalesRtnNoteUpdate(splitsalesreturnnote[i]));
		 }
		Type listType=new TypeToken<List<Sales_return_noteDTO>>() {}.getType();
		List<Sales_return_noteDTO> appNoteList=new ModelMapper().map(appNote,listType);
		
		return appNoteList;
	}
	
	public Sales_return_noteDTO getSalesReturnNoteDtls(String salesreturnnoteid)
	{
		Sales_return_note modelList=sales_return_noteRepository.getSalesReturnNoteDtls(salesreturnnoteid);
		
		Type listType=new TypeToken<Sales_return_noteDTO>() {}.getType();
		Sales_return_noteDTO salesRNoteDtls=new ModelMapper().map(modelList, listType);
		 
		return salesRNoteDtls;
		
	}
	
//	public List<Sales_return_note> findAll()
//	{
//		List<Sales_return_note> inv1List=new ArrayList<Sales_return_note>();
//		inv1List.addAll(sales_return_noteRepository.findAll());
//		List<Sales_return_note> allData = inv1List
//				  .stream()
//				  .filter(c -> !c.getModified_type().equals("DELETED"))
//				  .collect(Collectors.toList());
//		
//		return allData;
//	}
	
	public List<Map<String, Object>> getSalesReturnNote(String company,String currentdate)
	{
 		List<Map<String, Object>> val=sales_return_noteRepository.getSalesReturnNote(company,currentdate);
		return val;
	}
	
	public Sales_return_note findOne(Long id) {
		 Optional<Sales_return_note> op=this.sales_return_noteRepository.findById(id);
		 return op.get();
	}
	
	public List<Sales_return_note_broker_dtlsDTO> getSalesReturnNoteBD(String salesreturnnoteid)
	{
		List<Sales_return_note_broker_dtls> modelList=sales_return_note_broker_dtlsRepository.getSalesReturnNoteBD(salesreturnnoteid);
		
		Type listType=new TypeToken<List<Sales_return_note_broker_dtlsDTO>>() {}.getType();
		List<Sales_return_note_broker_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Sales_return_note_docsDTO> getSalesReturnNoteD(String salesreturnnoteid)
	{
		List<Sales_return_note_docs> modelList=sales_return_note_docsRepository.getSalesReturnNoteD(salesreturnnoteid);
		
		Type listType=new TypeToken<List<Sales_return_note_docsDTO>>() {}.getType();
		List<Sales_return_note_docsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	
	public List<Sales_return_note_item_dtlsDTO> getSalesReturnNoteID(String salesreturnnoteid)
	{
		List<Sales_return_note_item_dtls> modelList=sales_return_note_item_dtlsRepository.getSalesReturnNoteID(salesreturnnoteid);
		
		Type listType=new TypeToken<List<Sales_return_note_item_dtlsDTO>>() {}.getType();
		List<Sales_return_note_item_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	
	public List<Map<String,Object>> getSalesReturnNoteIDjobwork(String salesreturnnoteid)
	{
		return sales_return_note_item_dtlsRepository.getSalesReturnNoteIDjobwork(salesreturnnoteid);
	}
	
	 public List<Sales_return_note_item_dtlsDTO>getMultipleSalesReturnNoteitemlist(String salesreturnnoteid)
	 {
		 System.out.println("sales return  :: "+salesreturnnoteid);
		 String []splitsalesreturnnote=salesreturnnoteid.split(",");
		 
		 List<Sales_return_note_item_dtls> challanlist=new ArrayList<Sales_return_note_item_dtls>();
		 for(int i=0;i<splitsalesreturnnote.length;i++) 
		 {
			 challanlist.addAll(sales_return_note_item_dtlsRepository.getSalesReturnNoteID(splitsalesreturnnote[i]));
		 }
		 
		 Type listType=new TypeToken<List<Sales_return_note_item_dtlsDTO>>() {}.getType();
		 List<Sales_return_note_item_dtlsDTO> salesItemList=new ModelMapper().map(challanlist,listType);
		 
		 return salesItemList;
	 }
	 
	 
	 public List<Sales_return_note_item_dtlsDTO>getMultipleSalesReturnNoteitemlistUpdate(Long id)
	 {
		 Optional<Sales_credit_note> op=sales_credit_noteRepository.findById(id);
		 
		 String []splitsalesreturnnoteITEMDTLS=op.get().getReferance_id().split(",");
		 
		  List<Sales_return_note_item_dtls> challanlist=new ArrayList<Sales_return_note_item_dtls>();
		  for(int i=0;i<splitsalesreturnnoteITEMDTLS.length;i++) 
			 {
			  challanlist.addAll(sales_return_note_item_dtlsRepository.getSalesReturnNoteID(splitsalesreturnnoteITEMDTLS[i]));
			 }
		 Type listType=new TypeToken<List<Sales_return_note_item_dtlsDTO>>() {}.getType();
		 List<Sales_return_note_item_dtlsDTO> salesItemList=new ModelMapper().map(challanlist,listType);
		 
		 return salesItemList;
	 }
	 
	 
	public List<Sales_return_note_party_dtlsDTO> getSalesReturnNotePD(String salesreturnnoteid)
	{

		List<Sales_return_note_party_dtls> modelList=sales_return_note_party_dtlsRepository.getSalesReturnNotePD(salesreturnnoteid);
		
		Type listType=new TypeToken<List<Sales_return_note_party_dtlsDTO>>() {}.getType();
		List<Sales_return_note_party_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public Sales_return_note_shipment_dtlsDTO getSalesReturnNoteSD(String salesreturnnoteid)
	{
		Sales_return_note_shipment_dtls modelList=sales_return_note_shipment_dtlsRepository.getSalesReturnNoteSD(salesreturnnoteid);
		
		Type listType=new TypeToken<Sales_return_note_shipment_dtlsDTO>() {}.getType();
		Sales_return_note_shipment_dtlsDTO itemNameList=new ModelMapper().map(modelList, listType);
		 
		return itemNameList;
	}
	
	
	public Sales_return_note_trans_infoDTO getSalesReturnNoteTI(String salesreturnnoteid)
	{
		Sales_return_note_trans_info modelList=sales_return_note_trans_infoRepository.getSalesReturnNoteTI(salesreturnnoteid);
		
		Type listType=new TypeToken<Sales_return_note_trans_infoDTO>() {}.getType();
		Sales_return_note_trans_infoDTO itemNameList=new ModelMapper().map(modelList, listType);
		 
		return itemNameList;
	}
	
	public List<Invoice_Sales_return_note_trans_infoDTO> getSalesRtnNoteTransInfo(String salesreturnnoteid){
		  String[] arrOfStr=salesreturnnoteid.split(",");
		  List<Invoice_Sales_return_note_trans_infoDTO> iTransList=new ArrayList<Invoice_Sales_return_note_trans_infoDTO>();
		  
		  for(int i=0;i<arrOfStr.length;i++)
		  {
			  Sales_return_note_trans_info transInfo=sales_return_note_trans_infoRepository.getSalesReturnNoteTI(arrOfStr[i]);
			  Sales_return_note_weight_dtl wtInfo=sales_return_note_weight_dtlRepository.getSalesReturnNoteWD(arrOfStr[i]);
			  
			 // Invoice_Sales_return_note_trans_infoDTO def=new Invoice_Sales_return_note_trans_infoDTO(transInfo.getTranscode(),vehicleMasterRepository.getVehicleDetails(transInfo.getVehicleno()).getVehtype_code(),transInfo.getVehicleno(),wtInfo.getOwnpermitno());
			  Invoice_Sales_return_note_trans_infoDTO def =new Invoice_Sales_return_note_trans_infoDTO();
				def.setTransname(transInfo.getTranscode());
				//def.setVehicleno(vehicleMasterRepository.getVehicleDetails(transInfo.getVehicleno()).getVehtype_code());
			//	def.setVehicletype(transInfo.getVehicleno());
				
				def.setVehicletype(vehicleMasterRepository.getVehicleDetails(transInfo.getVehicleno()).getVehtype_code());
				def.setVehicleno(transInfo.getVehicleno());
				def.setEwaybillno(wtInfo.getOwnpermitno());
				iTransList.add(def);
		  }
		  
		  Type listType = new TypeToken<List<Invoice_Sales_return_note_trans_infoDTO>>() {}.getType();

		  List<Invoice_Sales_return_note_trans_infoDTO> invTrans= new ModelMapper().map(iTransList,listType);
		
		  return invTrans;
	}
	
	public List<Invoice_Sales_return_note_trans_infoDTO> getMultipleSalesRtnNoteTransInfo(String salesreturnnoteid){
		  String[] arrOfStr=salesreturnnoteid.split(",");
		  List<Invoice_Sales_return_note_trans_infoDTO> iTransList=new ArrayList<Invoice_Sales_return_note_trans_infoDTO>();
		  
		  for(int i=0;i<arrOfStr.length;i++)
		  {
			  Sales_return_note_trans_info transInfo=sales_return_note_trans_infoRepository.getSalesReturnNoteTI(arrOfStr[i]);
			  Sales_return_note_weight_dtl wtInfo=sales_return_note_weight_dtlRepository.getSalesReturnNoteWD(arrOfStr[i]);
			  //Invoice_Sales_return_note_trans_infoDTO def=new Invoice_Sales_return_note_trans_infoDTO(transInfo.getTranscode(),vehicleMasterRepository.getVehicleDetails(transInfo.getVehicleno()).getVehtype_code(),transInfo.getVehicleno(),wtInfo.getOwnpermitno());
			  Invoice_Sales_return_note_trans_infoDTO def =new Invoice_Sales_return_note_trans_infoDTO();
				def.setTransname(transInfo.getTranscode());
				def.setVehicleno(vehicleMasterRepository.getVehicleDetailsVno(transInfo.getVehicleno()).getVehtype_code());
				def.setVehicletype(transInfo.getVehicleno());
				def.setEwaybillno(wtInfo.getOwnpermitno());
			  iTransList.add(def);
		  }
		  
		  Type listType = new TypeToken<List<Invoice_Sales_return_note_trans_infoDTO>>() {}.getType();

		  List<Invoice_Sales_return_note_trans_infoDTO> invTrans= new ModelMapper().map(iTransList,listType);
		
		  return invTrans;
	}
	
	public Sales_return_note_weight_dtlDTO getSalesReturnNoteWD(String salesreturnnoteid)
	{
		Sales_return_note_weight_dtl modelList=sales_return_note_weight_dtlRepository.getSalesReturnNoteWD(salesreturnnoteid);
		
		Type listType=new TypeToken<Sales_return_note_weight_dtlDTO>() {}.getType();
		
		Sales_return_note_weight_dtlDTO itemNameList=new ModelMapper().map(modelList, listType);
		 return itemNameList;
		
	}
	
	@Transactional
	public Sales_return_note update(Sales_return_note ran,long id)
	{
		Optional<Sales_return_note> op=sales_return_noteRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(ran.getBusinessunit())) {
			ran.setBusinessunitname(companyBusinessUnitMasterRepository.CompanyBUAddress(ran.getBusinessunit()).getBusinessunit_name());
		}else {ran.setBusinessunitname("None");}
 		
 		if(Utility.isNullOrEmpty(ran.getParty())) {
 			ran.setPartyname(cust_bussiness_partnerRepository.getCustomer(ran.getParty()).getCp_name());
 		}else {ran.setPartyname("None");}
 		
 		if(Utility.isNullOrEmpty(ran.getInv_type())) {
 			ran.setInv_typename(invoice_typeRepository.getSalesInvTypesDtls(ran.getInv_type()).getInvtype_name());
		}else {ran.setInv_typename("None");}
 		
		ran.setModified_type("INSERTED");
 		ran.setInserted_by(op.get().getInserted_by());
 		ran.setInserted_on(op.get().getInserted_on());
 		ran.setUpdated_by(userRepository.getUserDetails(ran.getUsername()).getName());
 		ran.setUpdated_on(ldt);
 		ran.setDeleted_by("NA");
 		ran.setDeleted_on(ldt);
 		ran.setCredit_note_status("NO");
		
 		if(op.isPresent()) {
			ran.setId(id);
		}
 		
 			sales_return_note_broker_dtlsRepository.updateSales_return_note_broker_dtls(ran.getSalesreturnnoteid(),"UPDATED");
			
 			Set<Sales_return_note_broker_dtls> ran1 = new HashSet<Sales_return_note_broker_dtls>();
			ran1.addAll(ran.getSales_return_note_broker_dtls());
			for(Sales_return_note_broker_dtls rabd : ran1)
			{
				rabd.setSalesreturnnoteid(ran.getSalesreturnnoteid());
				rabd.setSalesreturnnoteno(ran.getSalesreturnnoteno());
				rabd.setCompany_id(ran.getCompany_id());
				rabd.setFin_year(ran.getFin_year());
				rabd.setModified_type(ran.getModified_type());
				rabd.setInserted_by(ran.getInserted_by());
				rabd.setInserted_on(ran.getInserted_on());
				rabd.setUpdated_by(ran.getUpdated_by());
				rabd.setUpdated_on(ran.getUpdated_on());
				rabd.setDeleted_by(ran.getDeleted_by());
				rabd.setDeleted_on(ran.getDeleted_on());
			}
			ran.setSales_return_note_broker_dtls(ran1);
			
			sales_return_note_docsRepository.updateSales_return_note_docs(ran.getSalesreturnnoteid(),"UPDATED");
			
			Set<Sales_return_note_docs> ran2 = new HashSet<Sales_return_note_docs>();
			ran2.addAll(ran.getSales_return_note_docs());
			for(Sales_return_note_docs rad : ran2)
			{
				rad.setSalesreturnnoteid(ran.getSalesreturnnoteid());
				rad.setSalesreturnnoteno(ran.getSalesreturnnoteno());
				rad.setCompany_id(ran.getCompany_id());
				rad.setFin_year(ran.getFin_year());
				rad.setModified_type(ran.getModified_type());
				rad.setInserted_by(ran.getInserted_by());
				rad.setInserted_on(ran.getInserted_on());
				rad.setUpdated_by(ran.getUpdated_by());
				rad.setUpdated_on(ran.getUpdated_on());
				rad.setDeleted_by(ran.getDeleted_by());
				rad.setDeleted_on(ran.getDeleted_on());
			}
			ran.setSales_return_note_docs(ran2);
			
			if(ran.getInv_type().compareToIgnoreCase("INV00003")==0) 
			{
				ran.getSales_return_note_item_dtls().clear();
				
				sales_return_note_item_dtls_for_jobworkRepository.updateSales_return_note_item_dtls_for_jobwork(op.get().getSalesreturnnoteid(),"UPDATED");
				
				Set<Sales_return_note_item_dtls_for_jobwork> jobSet = new HashSet<Sales_return_note_item_dtls_for_jobwork>();
				jobSet.addAll(ran.getSales_return_note_item_dtls_for_jobwork());
				for(Sales_return_note_item_dtls_for_jobwork jobDts : jobSet)
				{
					jobDts.setSalesreturnnoteid(ran.getSalesreturnnoteid());
					jobDts.setSalesreturnnoteno(ran.getSalesreturnnoteno());
					
					if(Utility.isNullOrEmpty(jobDts.getJob_item())) {
						jobDts.setJob_item_name(item_masterRepository.itemNameById(jobDts.getJob_item()).getItem_name());
					}else{jobDts.setJob_item_name("0");};
					
					if(Utility.isNullOrEmpty(jobDts.getJob_packing())){
						jobDts.setJob_packing_name(item_masterRepository.itemNameById(jobDts.getJob_packing()).getItem_name());
					}else{jobDts.setJob_packing_name("0");}
					
					jobDts.setCompany_id(ran.getCompany_id());
					jobDts.setFin_year(ran.getFin_year());
					jobDts.setModified_type("INSERTED");
					jobDts.setInserted_by(ran.getInserted_by());
					jobDts.setInserted_on(ran.getInserted_on());
					jobDts.setUpdated_by(userRepository.getUserDetails(ran.getUsername()).getName());
					jobDts.setUpdated_on(ldt);
					jobDts.setDeleted_by("NA");
					jobDts.setDeleted_on(ldt);
				}
				ran.setSales_return_note_item_dtls_for_jobwork(jobSet);
			}
			else
			{
				ran.getSales_return_note_item_dtls_for_jobwork().clear();
				sales_return_note_item_dtlsRepository.updateSales_return_note_item_dtls(ran.getSalesreturnnoteid(),"UPDATED");
				
				Set<Sales_return_note_item_dtls> ran3 = new HashSet<Sales_return_note_item_dtls>();
				ran3.addAll(ran.getSales_return_note_item_dtls());
				for(Sales_return_note_item_dtls raid : ran3)
				{
					raid.setSalesreturnnoteid(ran.getSalesreturnnoteid());
					raid.setSalesreturnnoteno(ran.getSalesreturnnoteno());
					raid.setItemname(item_masterRepository.itemNameById(raid.getItemcode()).getItem_name());
					if(Utility.isNullOrEmpty(raid.getPacking())) {
						raid.setPackingname(item_masterRepository.itemNameById(raid.getPacking()).getItem_name());
					}else {raid.setPackingname("None");}
					
					if(Utility.isNullOrEmpty(raid.getTaxcode())) {
						raid.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(raid.getTaxcode()).getTax_name());
	 				}else {raid.setTaxcode_name("None");}
					
					raid.setCompany_id(ran.getCompany_id());
					raid.setFin_year(ran.getFin_year());
					raid.setModified_type(ran.getModified_type());
					raid.setInserted_by(ran.getInserted_by());
					raid.setInserted_on(ran.getInserted_on());
					raid.setUpdated_by(ran.getUpdated_by());
					raid.setUpdated_on(ran.getUpdated_on());
					raid.setDeleted_by(ran.getDeleted_by());
					raid.setDeleted_on(ran.getDeleted_on());
				}
				ran.setSales_return_note_item_dtls(ran3);
			}
			
			sales_return_note_party_dtlsRepository.updateSales_return_note_party_dtls(ran.getSalesreturnnoteid(),"UPDATED");
			
			Set<Sales_return_note_party_dtls> ran4 = new HashSet<Sales_return_note_party_dtls>();
			ran4.addAll(ran.getSales_return_note_party_dtls());
			for(Sales_return_note_party_dtls rapd : ran4) 				{
				
				rapd.setSalesreturnnoteid(ran.getSalesreturnnoteid());
				rapd.setSalesreturnnoteno(ran.getSalesreturnnoteno());
				rapd.setCompany_id(ran.getCompany_id());
				rapd.setFin_year(ran.getFin_year());
				rapd.setModified_type(ran.getModified_type());
				rapd.setInserted_by(ran.getInserted_by());
				rapd.setInserted_on(ran.getInserted_on());
				rapd.setUpdated_by(ran.getUpdated_by());
				rapd.setUpdated_on(ran.getUpdated_on());
				rapd.setDeleted_by(ran.getDeleted_by());
				rapd.setDeleted_on(ran.getDeleted_on());
			}
			ran.setSales_return_note_party_dtls(ran4);
			
			sales_return_note_shipment_dtlsRepository.updateSales_return_note_shipment_dtls(ran.getSalesreturnnoteid(),"UPDATED");
			
			Set<Sales_return_note_shipment_dtls> ran5 = new HashSet<Sales_return_note_shipment_dtls>();
			ran5.add(ran.getSales_return_note_shipment_dtls());
			for(Sales_return_note_shipment_dtls rasd : ran5)
			{
				rasd.setSalesreturnnoteid(ran.getSalesreturnnoteid());
				rasd.setSalesreturnnoteno(ran.getSalesreturnnoteno());
				rasd.setCompany_id(ran.getCompany_id());
				rasd.setFin_year(ran.getFin_year());
				rasd.setModified_type(ran.getModified_type());
				rasd.setInserted_by(ran.getInserted_by());
				rasd.setInserted_on(ran.getInserted_on());
				rasd.setUpdated_by(ran.getUpdated_by());
				rasd.setUpdated_on(ran.getUpdated_on());
				rasd.setDeleted_by(ran.getDeleted_by());
				rasd.setDeleted_on(ran.getDeleted_on());
			}
			if(!ran5.isEmpty()) {
				ran.setSales_return_note_shipment_dtls(ran5.iterator().next());
			}
			
			sales_return_note_trans_infoRepository.updateSales_return_note_trans_info(ran.getSalesreturnnoteid(),"UPDATED");
			
			Set<Sales_return_note_trans_info> ran6 = new HashSet<Sales_return_note_trans_info>();
			ran6.add(ran.getSales_return_note_trans_info());
			for(Sales_return_note_trans_info rati : ran6)
			{
				rati.setSalesreturnnoteid(ran.getSalesreturnnoteid());
				rati.setSalesreturnnoteno(ran.getSalesreturnnoteno());
				rati.setCompany_id(ran.getCompany_id());
				rati.setFin_year(ran.getFin_year());
				rati.setModified_type(ran.getModified_type());
				rati.setInserted_by(ran.getInserted_by());
				rati.setInserted_on(ran.getInserted_on());
				rati.setUpdated_by(ran.getUpdated_by());
				rati.setUpdated_on(ran.getUpdated_on());
				rati.setDeleted_by(ran.getDeleted_by());
				rati.setDeleted_on(ran.getDeleted_on());
			}
			if(!ran6.isEmpty())	{
				ran.setSales_return_note_trans_info(ran6.iterator().next());
			}
			
			sales_return_note_weight_dtlRepository.updateSales_return_note_weight_dtl(ran.getSalesreturnnoteid(),"UPDATED");
			
			Set<Sales_return_note_weight_dtl> ran7 = new HashSet<Sales_return_note_weight_dtl>();
			ran7.add(ran.getSales_return_note_weight_dtl());
			for(Sales_return_note_weight_dtl rawd : ran7)
			{
				rawd.setSalesreturnnoteid(ran.getSalesreturnnoteid());
				rawd.setSalesreturnnoteno(ran.getSalesreturnnoteno());
				rawd.setCompany_id(ran.getCompany_id());
				rawd.setFin_year(ran.getFin_year());
				rawd.setModified_type(ran.getModified_type());
				rawd.setInserted_by(ran.getInserted_by());
				rawd.setInserted_on(ran.getInserted_on());
				rawd.setUpdated_by(ran.getUpdated_by());
				rawd.setUpdated_on(ran.getUpdated_on());
				rawd.setDeleted_by(ran.getDeleted_by());
				rawd.setDeleted_on(ran.getDeleted_on());
			}
			if(!ran7.isEmpty())	{
				ran.setSales_return_note_weight_dtl(ran7.iterator().next());
			}
 		
		return sales_return_noteRepository.save(ran);
	}
	
	 public StatusDTO salesReturnNoteUsage(String salesreturnnoteid)
	 {
		String result = sales_return_noteRepository.salesReturnNoteUsage(salesreturnnoteid);
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO= new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		return statusDTO;
	 }
	 
	@Transactional
	public Sales_return_note deleteSalesReturnNotes(Sales_return_note salesReturnNote,Long id) {

		Optional<Sales_return_note> op = sales_return_noteRepository.findById(id);
		Sales_return_note sales_return_Note=sales_return_noteRepository.getSalesReturnNoteDetails(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
			if(op.isPresent())	{
				sales_return_Note.setId(id);
			}
			
			sales_return_Note.setModified_type("DELETED");
			sales_return_Note.setInserted_by(op.get().getInserted_by());
			sales_return_Note.setInserted_on(op.get().getInserted_on());
			sales_return_Note.setUpdated_by(op.get().getUpdated_by());
			sales_return_Note.setUpdated_on(op.get().getUpdated_on());
			sales_return_Note.setDeleted_by(userRepository.getUserDetails(sales_return_Note.getUsername()).getName());
			sales_return_Note.setDeleted_on(ldt);
			
			sales_return_noteRepository.sales_return_note_broker_dtlsupdate(op.get().getSalesreturnnoteid());
			
			sales_return_noteRepository.sales_return_note_docsupdate(op.get().getSalesreturnnoteid());
			
			if(op.get().getInv_type().compareToIgnoreCase("INV00003")==0) 
			{
				sales_return_note_item_dtls_for_jobworkRepository.updateSales_return_note_item_dtls_for_jobwork(op.get().getSalesreturnnoteid(),"DELETED");
			}
			else
			{
				sales_return_noteRepository.sales_return_note_item_dtlsupdate(op.get().getSalesreturnnoteid());
			}
			
			sales_return_noteRepository.sales_return_note_party_dtlsupdate(op.get().getSalesreturnnoteid());
			
			sales_return_noteRepository.sales_return_note_shipment_dtlsupdate(op.get().getSalesreturnnoteid());
			
			sales_return_noteRepository.sales_return_note_trans_infoupdate(op.get().getSalesreturnnoteid());
			
			sales_return_noteRepository.sales_return_note_weight_dtlupdate(op.get().getSalesreturnnoteid());
			
			
			return_approval_noteRepository.updateRtnAppStatusReverse(op.get().getReferance_id());
			
			return sales_return_noteRepository.save(sales_return_Note);	
		}
	
	public  List<Sales_return_note>getcreditnotepopupsalesreturn(long id) 
	{
		
		Optional<Sales_credit_note> op=sales_credit_noteRepository.findById(id);

		 List<Sales_return_note> salesreturn =new ArrayList<Sales_return_note>();
				 
		 salesreturn.add(sales_return_noteRepository.getSalesReturnNoteDtls(op.get().getReferance_id()));
		return salesreturn;
	}
	
	public  List<Sales_return_note_item_dtls> getsales_creditnote(String salesreturnid,long id)
	{
		
		
		List<Sales_return_note_item_dtls> salesreturn =new ArrayList<Sales_return_note_item_dtls>();
		salesreturn.addAll(sales_return_noteRepository.getSalesItemDetails(salesreturnid));
		
		Optional<Sales_credit_note> op=sales_credit_noteRepository.findById(id);
		
		
		List<Sales_credit_note_item_dtls> creditnote_itemdetails =new ArrayList<Sales_credit_note_item_dtls>();
		creditnote_itemdetails.addAll(sales_credit_noteRepository.getcreditnote_itemdetails(op.get().getCreditnoteid()));
		
		
		List<Sales_return_note_item_dtls> salesreturnfinal =new ArrayList<Sales_return_note_item_dtls>();
		for(int i=0;i<salesreturn.size();i++) 
		{
			String Itemcode = salesreturn.get(i).getItemcode();
			creditnote_itemdetails.forEach((e)->
			{
			
				if(e.getItem_code().compareToIgnoreCase(Itemcode)==0) 
				{
					salesreturnfinal.addAll(salesreturn);
				}
			});

		}
		
		return salesreturnfinal;
		
	}
	
	public List<Map<String, Object>> searchSalesReturnNote(String fromdate,String todate,String party1)
	{
 		return sales_return_noteRepository.searchSalesReturnNote(fromdate,todate,party1);
	}
	
	public List<Map<String, Object>> retriveSalesReturnNoteJobwork(String salesreturnnoteid)
	{
 		return sales_return_note_item_dtls_for_jobworkRepository.retriveSalesReturnNoteJobwork(salesreturnnoteid);
	}
	
}
