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

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Exporttotally.Tallyrequest_Creditnote;
import com.AnkitIndia.Exporttotally.Tallyrequest_CreditnoteJobwork;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Credit_item_groupwise_hsnsumm;
import com.AnkitIndia.jwtauthentication.model.Credit_item_groupwise_summ;
import com.AnkitIndia.jwtauthentication.model.Credit_item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Item_Service_master;
import com.AnkitIndia.jwtauthentication.model.Item_group_jobwork_sales_acc;
import com.AnkitIndia.jwtauthentication.model.Item_group_jobwork_sales_return_acc;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_sales_retacc;
import com.AnkitIndia.jwtauthentication.model.Item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Ledgermaster;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls_for_jobwork_price;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_bp_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_docs;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_item_dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_item_dtls_for_jobwork_price;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_payment_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_shipment_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_tax_info;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note_trans_dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note;
import com.AnkitIndia.jwtauthentication.repository.Account_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Creditnote_accounttransactionRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_addressRepository;
import com.AnkitIndia.jwtauthentication.repository.Invoice_accountdetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Invoice_typeRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_Service_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_jobwork_sales_return_accRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_master_sales_retaccRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_groupwise_taxsummRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.OutstandingledgerRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Sale_credit_note_einvoice_genRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_InvoiceRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Invoice_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_note_bp_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_note_broker_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_note_docsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_note_item_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_note_payment_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_note_shipment_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_note_tax_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_note_trans_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_return_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.SubgroupmasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Tax_code_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_bp_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_payment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_tax_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_trans_dtlsDTO;

@Service
public class Sales_credit_noteService_Imp implements Sales_credit_noteService{
	
	@Autowired
	Sales_credit_noteRepository sales_credit_noteRepository;
	
	@Autowired
	Sales_credit_note_bp_dtlsRepository sales_credit_note_bp_dtlsRepository;
	
	@Autowired
	Sales_credit_note_broker_dtlsRepository sales_credit_note_broker_dtlsRepository;
	
	
	@Autowired
	Sales_credit_note_docsRepository sales_credit_note_docsRepository;
	
	@Autowired
	Sales_credit_note_item_dtlsRepository  sales_credit_note_item_dtlsRepository;
	
	@Autowired
	Sales_credit_note_payment_dtlsRepository sales_credit_note_payment_dtlsRepository;
	
	@Autowired
	Sales_credit_note_shipment_dtlsRepository sales_credit_note_shipment_dtlsRepository;
	
	@Autowired
	Sales_credit_note_tax_infoRepository sales_credit_note_tax_infoRepository;
	
	@Autowired
	Sales_credit_note_trans_dtlsRepository sales_credit_note_trans_dtlsRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Invoice_typeRepository invoice_typeRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;
	
	@Autowired
	SubgroupmasterRepository subgroupmasterRepository;
	
	@Autowired
	Invoice_accountdetailsRepository invoice_accountdetailsRepository;
	
	@Autowired
	OutstandingledgerRepository outstandingledgerRepository;
	
	@Autowired
	Account_detailsRepository account_detailsRepository;
	
	@Autowired
	Creditnote_accounttransactionRepository creditnote_accounttransactionRepository;
	
	@Autowired
	Sales_return_noteRepository sales_return_noteRepository;
	
	@Autowired
	Return_approval_noteRepository return_approval_noteRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	@Autowired
	Item_group_master_sales_retaccRepository item_group_master_sales_retaccRepository;

	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	Cust_bussiness_partner_addressRepository cust_bussiness_partner_addressRepository;
	
	@Autowired
	Sales_InvoiceRepository sales_InvoiceRepository;
	
	@Autowired
	Sales_Invoice_Item_DtlsRepository sales_Invoice_Item_DtlsRepository;
	
	@Autowired
	Item_groupwise_taxsummRepository item_groupwise_taxsummRepository;
	
	@Autowired
	Item_group_masterRepository item_group_masterRepository;
	
	@Autowired
	Item_Service_masterRepository item_Service_masterRepository;
	
	@Autowired
	Tax_code_detailsRepository tax_code_detailsRepository;
	
	@Autowired
	Item_group_jobwork_sales_return_accRepository item_group_jobwork_sales_return_accRepository;
	
	@Autowired
	Sale_credit_note_einvoice_genRepository sale_credit_note_einvoice_genRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	Trans_bussiness_partnerRepository trans_bussiness_partnerRepository;
	
	static String transtype="Credit Note",updated="UPDATED",deleted="DELETED";
	
	public SalesSequenceIdDTO getCNSequenceId(String fin_year,String cntype,String cnotetype)
	{
		long slno=0;//System.err.println("Got Data: "+fin_year+" "+cntype);
	
		String fin_yearspit[]=fin_year.split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String invoice_prefix="";
		String prefix="";
		if(cntype.compareToIgnoreCase("INV00001")==0) 
		{
			invoice_prefix="R";
		}
		if(cntype.compareToIgnoreCase("INV00002")==0) 
		{
			invoice_prefix="G";
		}
		if(cntype.compareToIgnoreCase("INV00003")==0) 
		{
			invoice_prefix="J";
		}
		if(cntype.compareToIgnoreCase("INV00004")==0) 
		{
			invoice_prefix="P";
		}
		
		//System.out.println("cnotetype"+cnotetype);
		if(cnotetype.compareToIgnoreCase("Acceptance of Lower Rate")==0)
		{
			prefix="C";
			String px="D";
			prefix=prefix+invoice_prefix+px+"-"+final_fyear+"-";
			String sno=sales_credit_noteRepository.countSalesCreditacceptance(fin_year,cntype,cnotetype);
			if(sno != null) {
				//System.err.println("Sl No: "+sno);
				slno=Long.parseLong(sno);
			}
		}
		else
		{
			prefix="CN";
			prefix=prefix+invoice_prefix+"-"+final_fyear+"-";
			String sno=sales_credit_noteRepository.countSalesCredit(fin_year,cntype,cnotetype);
			if(sno != null) {
				//System.err.println("Sl No: "+sno);
				slno=Long.parseLong(sno);
			}
		}
		//System.out.println("prefix::::"+prefix+"//"+slno);
		//String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		 String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		
		
		Type listType = new TypeToken<SalesSequenceIdDTO>() {}.getType();
		SalesSequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Sales_credit_note save(Sales_credit_note scn)
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;String prefix="SCN";
		if(sales_credit_noteRepository.countId(prefix).isPresent())
		{
			slno=Long.parseLong(sales_credit_noteRepository.countId(prefix).get());
		}
 		String gen_sno=UniqueID.uniqueid(prefix, slno);
 		
 		/*Start checking transaction no for unique */
 		
 		
 		long nslno=0;
		
		
		String fin_yearspit[]=scn.getFin_year().split("-");
		String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
		
		String invoice_prefix="";
		String tprefix="";
		if(scn.getInvoice_type().compareToIgnoreCase("INV00001")==0) 
		{
			invoice_prefix="R";
		}
		if(scn.getInvoice_type().compareToIgnoreCase("INV00002")==0) 
		{
			invoice_prefix="G";
		}
		if(scn.getInvoice_type().compareToIgnoreCase("INV00003")==0) 
		{
			invoice_prefix="J";
		}
		if(scn.getInvoice_type().compareToIgnoreCase("INV00004")==0) 
		{
			invoice_prefix="P";
		}
		
		if(scn.getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0)
		{
			tprefix="C";
			String px="D";
			tprefix=tprefix+invoice_prefix+px+"-"+final_fyear+"-";
			
			String tsno=sales_credit_noteRepository.countSalesCreditacceptance(scn.getFin_year(),scn.getInvoice_type(),scn.getCreditnotetype());
			if(tsno != null) {
				nslno=Long.parseLong(tsno);
			}
		}
		else
		{
			tprefix="CN";
			tprefix=tprefix+invoice_prefix+"-"+final_fyear+"-";
			
			String tsno=sales_credit_noteRepository.countSalesCredit(scn.getFin_year(),scn.getInvoice_type(),scn.getCreditnotetype());
			if(tsno != null) {
				nslno=Long.parseLong(tsno);
			}
		}
 		
		
	//	String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
//		/UniqueIDTransaction.uniqueId6(
		String gen_tslno=UniqueIDTransaction.uniqueId6(tprefix,nslno);
		scn.setCreditnoteno(gen_tslno);
		System.err.println("Last:>>>"+scn.getCreditnoteno());
		/*End checking transaction no for unique */
		
 		scn.setCreditnoteid(gen_sno);
 		scn.setModified_type("INSERTED");
 		scn.setInserted_by(userRepository.getUserDetails(scn.getUsername()).getName());
 		scn.setInserted_on(ldt);
 		scn.setUpdated_by("NA");
 		scn.setUpdated_on(ldt);
 		scn.setDeleted_by("NA");
 		scn.setDeleted_on(ldt);
 		
 		if(scn.getChallan().compareToIgnoreCase("Multiple")==0)
 		{
 			scn.setAllsalesorderdate(scn.getAllsalesorderdate());
 			
 			if(scn.getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection") == 0)
 	 		{
 				String [] multipleSalesReturnNote = scn.getReferance_id().split(",");
 				for(int i=0;i<multipleSalesReturnNote.length;i++)
 				{
 					sales_return_noteRepository.updatedUniqueSalesNoteToCreditNote(multipleSalesReturnNote[i],scn.getCreditnoteid());
 				}
 	 			
 	 		}
 	 		
 	 		if(scn.getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate") == 0)
 	 		{
 	 			String [] multipleSalesReturnApproval = scn.getReferance_id().split(",");
 	 			for(int j=0;j<multipleSalesReturnApproval.length;j++)
 	 			{
 	 				System.out.println(multipleSalesReturnApproval[j] + " / " +  scn.getCreditnoteid());
 	 				return_approval_noteRepository.updatedUniqueApproveNoteToCreditNote(multipleSalesReturnApproval[j],scn.getCreditnoteid());	
 	 			
 	 			}
 	 			
 	 		}
 		}
 		else
 		{
 			scn.setAllsalesorderdate("NA");
 			if(scn.getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection") == 0)
 	 		{
 	 			sales_return_noteRepository.updatedUniqueSalesNoteToCreditNote(scn.getReferance_id(),scn.getCreditnoteid());
 	 		}
 	 		
 	 		if(scn.getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate") == 0)
 	 		{
 	 			return_approval_noteRepository.updatedUniqueApproveNoteToCreditNote(scn.getReferance_id(),scn.getCreditnoteid());
 	 		}
 		}
 		
 		
 		
 		
 		if(Utility.isNullOrEmpty(scn.getBusiness_unit())) {
 			scn.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(scn.getBusiness_unit()).getBusinessunit_name());
		}else {scn.setBusiness_unitname("None");}
 		
 		if(Utility.isNullOrEmpty(scn.getParty())) {
 			scn.setPartyname(cust_bussiness_partnerRepository.getCustomer(scn.getParty()).getCp_name());
 		}else {scn.setPartyname("None");}
 		
 		if(Utility.isNullOrEmpty(scn.getInvoice_type())) {
 			scn.setInvoice_typename(invoice_typeRepository.getSalesInvTypesDtls(scn.getInvoice_type()).getInvtype_name());
		}else {scn.setInvoice_typename("None");}
 		
 		/********************************* Filtering Credit Account **********************************/
	
			/*********** Credit Account Creation End**********************************************/
			
 		
 		if(scn.getInvoice_type().compareToIgnoreCase("INV00003")==0) 
		{
 			scn.getCredit_item_groupwise_hsnsumm().clear();
 			scn.getSales_credit_note_item_dtls().clear();
 			scn.getCredit_item_groupwise_summ().clear();
 			
 			
 			Set<Sales_credit_note_item_dtls_for_jobwork> jobSet = new HashSet<Sales_credit_note_item_dtls_for_jobwork>();
			jobSet.addAll(scn.getSales_credit_note_item_dtls_for_jobwork());
			for(Sales_credit_note_item_dtls_for_jobwork jobDts : jobSet)
			{
				jobDts.setCreditnoteid(gen_sno);
				jobDts.setCreditnoteno(scn.getCreditnoteno());
				
				jobDts.setJob_item_name(item_masterRepository.itemNameById(jobDts.getJob_item()).getItem_name());
				if(Utility.isNullOrEmpty(jobDts.getJob_packing())) 
				{
					jobDts.setJob_packing_name(item_masterRepository.itemNameById(jobDts.getJob_packing()).getItem_name());
				}
				else
				{
					jobDts.setJob_packing_name("0");
				}
				jobDts.setCompany_id(scn.getCompany_id());
				jobDts.setFin_year(scn.getFin_year());
				jobDts.setModified_type("INSERTED");
				jobDts.setInserted_by(scn.getInserted_by());
				jobDts.setInserted_on(scn.getInserted_on());
				jobDts.setUpdated_by("NA");
				jobDts.setUpdated_on(ldt);
				jobDts.setDeleted_by("NA");
				jobDts.setDeleted_on(ldt);
				
			}
			scn.setSales_credit_note_item_dtls_for_jobwork(jobSet);
			
			Set<Sales_credit_note_item_dtls_for_jobwork_price> serviceItem = new HashSet<Sales_credit_note_item_dtls_for_jobwork_price>();
			serviceItem.addAll(scn.getSales_credit_note_item_dtls_for_jobwork_price());
			for(Sales_credit_note_item_dtls_for_jobwork_price service : serviceItem)
			{
				service.setCreditnoteid(gen_sno);
				service.setCreditnoteno(scn.getCreditnoteno());
				
				if(Utility.isNullOrEmpty(service.getItem_service())) {
					service.setItem_service_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getService_name());
				}else{service.setItem_service_name("0");};
				
				if(Utility.isNullOrEmpty(service.getTaxcode())) {
					service.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(service.getTaxcode()).getTax_name());
				}else{service.setTaxcode_name("0");};
				
				if(Utility.isNullOrEmpty(service.getItem_service())) {
					service.setItem_service_acc_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getAc_ledger_name());
				}else{service.setItem_service_acc_name("0");};
				
				service.setCompany_id(scn.getCompany_id());
				service.setFin_year(scn.getFin_year());
				service.setModified_type("INSERTED");
				service.setInserted_by(userRepository.getUserDetails(scn.getUsername()).getName());
				service.setInserted_on(scn.getInserted_on());
				service.setUpdated_by("NA");
				service.setUpdated_on(ldt);
				service.setDeleted_by("NA");
				service.setDeleted_on(ldt);
			}
			scn.setSales_credit_note_item_dtls_for_jobwork_price(serviceItem);
 			
 			
		}
 		else 
 		{
 			scn.getSales_credit_note_item_dtls_for_jobwork().clear();
 			scn.getSales_credit_note_item_dtls_for_jobwork_price().clear();
 			
 			 Set<Sales_credit_note_item_dtls> sItem_dtls = new HashSet<Sales_credit_note_item_dtls>();
 	        sItem_dtls.addAll(scn.getSales_credit_note_item_dtls());
 			for(Sales_credit_note_item_dtls sItemDtls : sItem_dtls) 				{
 				sItemDtls.setCreditnoteid(gen_sno);
 				sItemDtls.setCreditnoteno(scn.getCreditnoteno());
 				
 				if(Utility.isNullOrEmpty(sItemDtls.getTax_code())) {
 					sItemDtls.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(sItemDtls.getTax_code()).getTax_name());
 				}else {sItemDtls.setTaxcode_name("None");}
 				
 				if(sItemDtls.getItem_code().compareTo("0") !=0 && sItemDtls.getItem_code().compareTo("") !=0 && sItemDtls.getItem_code() !=null) {
 					sItemDtls.setItem_name(item_masterRepository.itemNameById(sItemDtls.getItem_code()).getItem_name());
 				}else {sItemDtls.setItem_name("None");}
 				
 				//sItemDtls.setItem_name(item_masterRepository.itemNameById(sItemDtls.getItem_code()).getItem_name());
 				//if(Utility.isNullOrEmpty(sItemDtls.getPacking())) {
 				if(sItemDtls.getPacking().compareTo("0") !=0 && sItemDtls.getPacking().compareTo("") !=0 && sItemDtls.getPacking() !=null) {
 					sItemDtls.setPacking_name(item_masterRepository.itemNameById(sItemDtls.getPacking()).getItem_name());
 				}else {sItemDtls.setPacking_name("None");}
 				sItemDtls.setUsername(scn.getUsername());
 				
 				sItemDtls.setItem_groupname(item_group_masterRepository.getGroupDtls_converter(sItemDtls.getItem_group()).getGroup_name());
 				
 				
 				sItemDtls.setCompany_id(scn.getCompany_id());
 				sItemDtls.setFin_year(scn.getFin_year());
 				sItemDtls.setModified_type(scn.getModified_type());
 				sItemDtls.setInserted_by(scn.getInserted_by());
 				sItemDtls.setInserted_on(scn.getInserted_on());
 				sItemDtls.setUpdated_by(scn.getUpdated_by());
 				sItemDtls.setUpdated_on(scn.getUpdated_on());
 				sItemDtls.setDeleted_by(scn.getDeleted_by());
 				sItemDtls.setDeleted_on(scn.getDeleted_on());
 			}
 			scn.setSales_credit_note_item_dtls(sItem_dtls);
 			
 			Set<Credit_item_groupwise_summ> iSummSet=new HashSet<Credit_item_groupwise_summ>();
			iSummSet.addAll(scn.getCredit_item_groupwise_summ());
			for(Credit_item_groupwise_summ iSumm:iSummSet) 
			{
				iSumm.setCreditnoteid(gen_sno);
				iSumm.setCompany_id(scn.getCompany_id());
				iSumm.setFin_year(scn.getFin_year());
				iSumm.setUsername(scn.getUsername());
				iSumm.setModified_type("INSERTED");
				iSumm.setInserted_by(userRepository.getUserDetails(scn.getUsername()).getName());
				iSumm.setInserted_on(ldt);
				iSumm.setUpdated_by(scn.getUpdated_by());
				iSumm.setUpdated_on(scn.getUpdated_on());
				iSumm.setDeleted_by(scn.getDeleted_by());
				iSumm.setDeleted_on(scn.getDeleted_on());
			}
			scn.setCredit_item_groupwise_summ(iSummSet);
			
			Set<Credit_item_groupwise_hsnsumm> iHsnSummSet=new HashSet<Credit_item_groupwise_hsnsumm>();
			iHsnSummSet.addAll(scn.getCredit_item_groupwise_hsnsumm());
			for(Credit_item_groupwise_hsnsumm iHsnSumm:iHsnSummSet) 
			{
				iHsnSumm.setCreditnoteid(gen_sno);
				iHsnSumm.setCompany_id(scn.getCompany_id());
				iHsnSumm.setFin_year(scn.getFin_year());
				iHsnSumm.setUsername(scn.getUsername());
				iHsnSumm.setModified_type("INSERTED");
				iHsnSumm.setInserted_by(userRepository.getUserDetails(scn.getUsername()).getName());
				iHsnSumm.setInserted_on(ldt);
				iHsnSumm.setUpdated_by(scn.getUpdated_by());
				iHsnSumm.setUpdated_on(scn.getUpdated_on());
				iHsnSumm.setDeleted_by(scn.getDeleted_by());
				iHsnSumm.setDeleted_on(scn.getDeleted_on());
			}
			scn.setCredit_item_groupwise_hsnsumm(iHsnSummSet);
			
			
			
 		}
 		
			/************* Tax Entry Start *********************************/	
			
				
			
			Set<Credit_item_groupwise_taxsumm> iTaxSummSet=new HashSet<Credit_item_groupwise_taxsumm>();
			iTaxSummSet.addAll(scn.getCredit_item_groupwise_taxsumm());
			for(Credit_item_groupwise_taxsumm itaxSumm:iTaxSummSet) 
			{
				itaxSumm.setCreditnoteid(gen_sno);
				itaxSumm.setCompany_id(scn.getCompany_id());
				itaxSumm.setFin_year(scn.getFin_year());
				itaxSumm.setUsername(scn.getUsername());
				itaxSumm.setModified_type("INSERTED");
				itaxSumm.setInserted_by(userRepository.getUserDetails(scn.getUsername()).getName());
				itaxSumm.setInserted_on(ldt);
				itaxSumm.setUpdated_by(scn.getUpdated_by());
				itaxSumm.setUpdated_on(scn.getUpdated_on());
				itaxSumm.setDeleted_by(scn.getDeleted_by());
				itaxSumm.setDeleted_on(scn.getDeleted_on());
			}
			scn.setCredit_item_groupwise_taxsumm(iTaxSummSet);
			
		
			/************* Tax Entry End *********************************/
 		
	       
        
			Set<Sales_credit_note_bp_dtls> scn1 = new HashSet<Sales_credit_note_bp_dtls>();
			scn1.add(scn.getSales_credit_note_bp_dtls());
			for(Sales_credit_note_bp_dtls bpd : scn1)
			{
				bpd.setCreditnoteid(gen_sno);
				bpd.setCreditnoteno(scn.getCreditnoteno());
				bpd.setUsername(scn.getUsername());
				bpd.setCompany_id(scn.getCompany_id());
				bpd.setFin_year(scn.getFin_year());
				bpd.setModified_type(scn.getModified_type());
				bpd.setInserted_by(scn.getInserted_by());
				bpd.setInserted_on(scn.getInserted_on());
				bpd.setUpdated_by(scn.getUpdated_by());
				bpd.setUpdated_on(scn.getUpdated_on());
				bpd.setDeleted_by(scn.getDeleted_by());
				bpd.setDeleted_on(scn.getDeleted_on());
			}
			if(!scn1.isEmpty()) {
				scn.setSales_credit_note_bp_dtls(scn1.iterator().next());
			}
				
			Set<Sales_credit_note_broker_dtls> scn2 = new HashSet<Sales_credit_note_broker_dtls>();
			scn2.addAll(scn.getSales_credit_note_broker_dtls());
			for(Sales_credit_note_broker_dtls bd : scn2) 				{
				bd.setCreditnoteid(gen_sno);
				bd.setCreditnoteno(scn.getCreditnoteno());
				bd.setUsername(scn.getUsername());
				bd.setCompany_id(scn.getCompany_id());
				bd.setFin_year(scn.getFin_year());
				bd.setModified_type(scn.getModified_type());
				bd.setInserted_by(scn.getInserted_by());
				bd.setInserted_on(scn.getInserted_on());
				bd.setUpdated_by(scn.getUpdated_by());
				bd.setUpdated_on(scn.getUpdated_on());
				bd.setDeleted_by(scn.getDeleted_by());
				bd.setDeleted_on(scn.getDeleted_on());
			}
			scn.setSales_credit_note_broker_dtls(scn2);
					
			Set<Sales_credit_note_docs> scn3 = new HashSet<Sales_credit_note_docs>();
			scn3.addAll(scn.getSales_credit_note_docs());
			for(Sales_credit_note_docs d : scn3) 				{
				d.setCreditnoteid(gen_sno);
				d.setCreditnoteno(scn.getCreditnoteno());
				d.setUsername(scn.getUsername());
				d.setCompany_id(scn.getCompany_id());
				d.setFin_year(scn.getFin_year());
				d.setModified_type(scn.getModified_type());
				d.setInserted_by(scn.getInserted_by());
				d.setInserted_on(scn.getInserted_on());
				d.setUpdated_by(scn.getUpdated_by());
				d.setUpdated_on(scn.getUpdated_on());
				d.setDeleted_by(scn.getDeleted_by());
				d.setDeleted_on(scn.getDeleted_on());
			}
			scn.setSales_credit_note_docs(scn3);
					
			Set<Sales_credit_note_payment_dtls> scn5 = new HashSet<Sales_credit_note_payment_dtls>();
			scn5.add(scn.getSales_credit_note_payment_dtls());
			for(Sales_credit_note_payment_dtls pd : scn5)
			{
				pd.setCreditnoteid(gen_sno);
				pd.setCreditnoteno(scn.getCreditnoteno());
				pd.setUsername(scn.getUsername());
				pd.setCompany_id(scn.getCompany_id());
				pd.setFin_year(scn.getFin_year());
				pd.setModified_type(scn.getModified_type());
				pd.setInserted_by(scn.getInserted_by());
				pd.setInserted_on(scn.getInserted_on());
				pd.setUpdated_by(scn.getUpdated_by());
				pd.setUpdated_on(scn.getUpdated_on());
				pd.setDeleted_by(scn.getDeleted_by());
				pd.setDeleted_on(scn.getDeleted_on());
			}
			if(!scn5.isEmpty())	{
				scn.setSales_credit_note_payment_dtls(scn5.iterator().next());
			}
					
			Set<Sales_credit_note_shipment_dtls> scn6 = new HashSet<Sales_credit_note_shipment_dtls>();
			scn6.add(scn.getSales_credit_note_shipment_dtls());
			for(Sales_credit_note_shipment_dtls sd : scn6)
			{
				sd.setCreditnoteid(gen_sno);
				sd.setCreditnoteno(scn.getCreditnoteno());
				sd.setUsername(scn.getUsername());
				sd.setCompany_id(scn.getCompany_id());
				sd.setFin_year(scn.getFin_year());
				sd.setModified_type(scn.getModified_type());
				sd.setInserted_by(scn.getInserted_by());
				sd.setInserted_on(scn.getInserted_on());
				sd.setUpdated_by(scn.getUpdated_by());
				sd.setUpdated_on(scn.getUpdated_on());
				sd.setDeleted_by(scn.getDeleted_by());
				sd.setDeleted_on(scn.getDeleted_on());
			}
			if(!scn6.isEmpty())	{
				scn.setSales_credit_note_shipment_dtls(scn6.iterator().next());
			}
					
			Set<Sales_credit_note_tax_info> scn7 = new HashSet<Sales_credit_note_tax_info>();
			scn7.add(scn.getSales_credit_note_tax_info());
			for(Sales_credit_note_tax_info ti : scn7)
			{
				ti.setCreditnoteid(gen_sno);
				ti.setCreditnoteno(scn.getCreditnoteno());
				ti.setUsername(scn.getUsername());
				ti.setCompany_id(scn.getCompany_id());
				ti.setFin_year(scn.getFin_year());
				ti.setModified_type(scn.getModified_type());
				ti.setInserted_by(scn.getInserted_by());
				ti.setInserted_on(scn.getInserted_on());
				ti.setUpdated_by(scn.getUpdated_by());
				ti.setUpdated_on(scn.getUpdated_on());
				ti.setDeleted_by(scn.getDeleted_by());
				ti.setDeleted_on(scn.getDeleted_on());
			}
			if(!scn7.isEmpty()) {
				scn.setSales_credit_note_tax_info(scn7.iterator().next());
			}
	 		
			Set<Sales_credit_note_trans_dtls> scn8 = new HashSet<Sales_credit_note_trans_dtls>();
			scn8.addAll(scn.getSales_credit_note_trans_dtls());
			for(Sales_credit_note_trans_dtls td : scn8) 				{
				td.setCreditnoteid(gen_sno);
				td.setCreditnoteno(scn.getCreditnoteno());
				
				System.out.println("Transporter:"+td.getTransname());
				if(Utility.isNullOrEmpty(td.getVehicleno())) {
 					td.setVehicle(vehicleMasterRepository.getVehicleDetails(td.getVehicleno()).getVehicle_no());
 				}else {td.setVehicle("None");}
				
				if(Utility.isNullOrEmpty(td.getTransname())) 
				{td.setTransporter(trans_bussiness_partnerRepository.bpNameById(td.getTransname()).getBp_name());}
				else{td.setTransporter("None");};
				
				td.setUsername(scn.getUsername());
				td.setCompany_id(scn.getCompany_id());
				td.setFin_year(scn.getFin_year());
				td.setModified_type(scn.getModified_type());
				td.setInserted_by(scn.getInserted_by());
				td.setInserted_on(scn.getInserted_on());
				td.setUpdated_by(scn.getUpdated_by());
				td.setUpdated_on(scn.getUpdated_on());
				td.setDeleted_by(scn.getDeleted_by());
				td.setDeleted_on(scn.getDeleted_on());
			}
			scn.setSales_credit_note_trans_dtls(scn8);
			
			//Invoice_accountdetails
		//	Invoice_accountdetails partyAccDtls=new Invoice_accountdetails(scn.getCreditnoteid(),scn.getCreditnoteno(),scn.getBusiness_unit(),scn.getBusiness_unitname(),scn.getParty(),scn.getPartyname(),scn.getCreditnotedate(),(scn.getPayable_amt()*-1),"I",transtype,uniquevoucherno,scn.getModified_type(),scn.getInserted_on(),scn.getInserted_by(),scn.getFin_year(),scn.getUsername());
			//Invoice_accountdetails accDtls=new Invoice_accountdetails(scn.getCreditnoteid(),scn.getCreditnoteno(),scn.getBusiness_unit(),scn.getBusiness_unitname(),scn.getPayable_amt_gl_ac(),ledgermasterRepository.getLedgerDetails(scn.getPayable_amt_gl_ac()).getLedgername(),scn.getCreditnotedate(),scn.getPayable_amt(),"A",transtype,uniquevoucherno,scn.getModified_type(),scn.getInserted_on(),scn.getInserted_by(),scn.getFin_year(),scn.getUsername());
			//invoice_accountdetailsRepository.save(partyAccDtls);
		//	invoice_accountdetailsRepository.save(accDtls);
 		
 		return sales_credit_noteRepository.save(scn);
	}
	
	public List<Sales_credit_note> findAll()
	{
		List<Sales_credit_note> inv1List=new ArrayList<Sales_credit_note>();
		inv1List.addAll(sales_credit_noteRepository.findAll());
		List<Sales_credit_note> allData = inv1List
				  .stream()
				  .filter(c -> !c.getModified_type().equals("DELETED"))
				  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Sales_credit_note findOne(Long id)
	{
		Optional<Sales_credit_note> op=this.sales_credit_noteRepository.findById(id);
		return op.get();
	}
	
	public Sales_credit_note retriveSalesCreditNoteposting(long id)
	{
		Optional<Sales_credit_note> op=this.sales_credit_noteRepository.findById(id);
		
		Sales_credit_note cr = new Sales_credit_note();
		cr=op.get();
		
		if(cr.getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0)
		{
			Sales_Invoice inv = sales_InvoiceRepository.getSalesInvDetails(return_approval_noteRepository.getReturnApprovalDtls(cr.getReferance_id()).getReferance_id());
			System.out.println(inv.getPayable_amt() +" // " + cr.getPayable_amt());
			cr.setPayable_amt(inv.getPayable_amt() - cr.getPayable_amt());
			cr.setAdj1_amt(inv.getAdj1_amt() - cr.getAdj1_amt());
			cr.setAdj2_amt(inv.getAdj2_amt() - cr.getAdj2_amt());
		}
		return cr;
	}
	
	public Sales_credit_note_bp_dtlsDTO getSalesCreditNoteBPD(String creditnoteid)
	{
		Sales_credit_note_bp_dtls modelList=sales_credit_note_bp_dtlsRepository.getSales_credit_note_bp_dtls(creditnoteid);
		
		Type listType=new TypeToken<Sales_credit_note_bp_dtlsDTO>() {}.getType();
		Sales_credit_note_bp_dtlsDTO itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}
	
	public List<Sales_credit_note_broker_dtlsDTO> getSalesCreditNoteBD(String creditnoteid)
	{
		List<Sales_credit_note_broker_dtls> modelList=sales_credit_note_broker_dtlsRepository.getSales_credit_note_broker_dtls(creditnoteid);
		
		Type listType=new TypeToken<List<Sales_credit_note_broker_dtlsDTO>>() {}.getType();
		List<Sales_credit_note_broker_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Sales_credit_note_docsDTO> getSalesCreditNoteD(String creditnoteid)
	{
		List<Sales_credit_note_docs> modelList=sales_credit_note_docsRepository.getSales_credit_note_docs(creditnoteid);
		
			
		Type listType=new TypeToken<List<Sales_credit_note_docsDTO>>() {}.getType();
		List<Sales_credit_note_docsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Sales_credit_note_item_dtlsDTO> getSalesCreditNoteID(String creditnoteid)
	{
		List<Sales_credit_note_item_dtls> modelList=sales_credit_note_item_dtlsRepository.getSales_credit_note_item_dtls(creditnoteid);
		
		Type listType=new TypeToken<List<Sales_credit_note_item_dtlsDTO>>() {}.getType();
		List<Sales_credit_note_item_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	
	public List<Sales_credit_note_item_dtlsDTO> getSalesCreditNoteIDposting(String creditnoteid)
	{
		List<Sales_credit_note_item_dtls> modelList=sales_credit_note_item_dtlsRepository.getSales_credit_note_item_dtls(creditnoteid);
		
		Sales_credit_note cr = sales_credit_noteRepository.getCreditNoteDetailsthcerdit(creditnoteid);
		
		if(cr.getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0)
		{
			cr.setPayable_amt((sales_InvoiceRepository.getSalesInvDetails((return_approval_noteRepository.getReturnApprovalDtls(cr.getReferance_id()).getReferance_id())).getPayable_amt())- cr.getPayable_amt());
			
			modelList.forEach(element->
			{
	        	String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(element.getItem_code()).getItem_group();
	        	Item_group_master_sales_retacc itemgroup= item_group_master_sales_retaccRepository.getItem_group_master_sales_retacc(subgroup_items_code);
	        	element.setItem_name(ledgermasterRepository.getLedgers(itemgroup.getItem_total()).getLedgername());		
	        	
	        	
	        	element.setAmount((sales_Invoice_Item_DtlsRepository.getSalesInvItmDtlsitem(return_approval_noteRepository.getReturnApprovalDtls(cr.getReferance_id()).getReferance_id(),element.getItem_code(),element.getPacking()).getAmount()) - element.getAmount());
			});
		}
		else 
		{
			modelList.forEach(element->
			{
	        	String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(element.getItem_code()).getItem_group();
	        	Item_group_master_sales_retacc itemgroup= item_group_master_sales_retaccRepository.getItem_group_master_sales_retacc(subgroup_items_code);
	        	element.setItem_name(ledgermasterRepository.getLedgers(itemgroup.getItem_total()).getLedgername());			
			});
		}
		
	
		
		Type listType=new TypeToken<List<Sales_credit_note_item_dtlsDTO>>() {}.getType();
		List<Sales_credit_note_item_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	public Sales_credit_note_payment_dtlsDTO getSalesCreditNotePD(String creditnoteid)
	{
		Sales_credit_note_payment_dtls modelList=sales_credit_note_payment_dtlsRepository.getSales_credit_note_payment_dtls(creditnoteid);
		
		Type listType=new TypeToken<Sales_credit_note_payment_dtlsDTO>() {}.getType();
		Sales_credit_note_payment_dtlsDTO itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}
	
	public Sales_credit_note_shipment_dtlsDTO getSalesCreditNoteSD(String creditnoteid)
	{
		Sales_credit_note_shipment_dtls modelList=sales_credit_note_shipment_dtlsRepository.getSales_credit_note_shipment_dtls(creditnoteid);
		
		Type listType=new TypeToken<Sales_credit_note_shipment_dtlsDTO>() {}.getType();
		Sales_credit_note_shipment_dtlsDTO itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}
	
	public Sales_credit_note_tax_infoDTO getSalesCreditNoteTI(String creditnoteid)
	{
		Sales_credit_note_tax_info modelList=sales_credit_note_tax_infoRepository.getSales_credit_note_tax_info(creditnoteid);
		
		Type listType=new TypeToken<Sales_credit_note_tax_infoDTO>() {}.getType();
		Sales_credit_note_tax_infoDTO itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
	}
	
	public List<Sales_credit_note_trans_dtlsDTO> getSalesCreditNoteTD(String creditnoteid)
	{
		List<Sales_credit_note_trans_dtls> modelList=sales_credit_note_trans_dtlsRepository.getSales_credit_note_trans_dtls(creditnoteid);
			
		Type listType=new TypeToken<List<Sales_credit_note_trans_dtlsDTO>>() {}.getType();
		List<Sales_credit_note_trans_dtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Map<String, Object>> getSalesCreditNoteJobwork(String creditnoteid)
	{
		return sales_credit_noteRepository.getSalesCreditNoteJobwork(creditnoteid);
	}
	
	public List<Map<String, Object>> getSalesCreditNoteJobworkPrice(String creditnoteid)
	{
		return sales_credit_noteRepository.getSalesCreditNoteJobworkPrice(creditnoteid);
	}
	
	@Transactional
	public  Sales_credit_note update(Sales_credit_note scn,long id)
	{
		Optional<Sales_credit_note> op=sales_credit_noteRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(scn.getBusiness_unit())) {
 			scn.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(scn.getBusiness_unit()).getBusinessunit_name());
		}else {scn.setBusiness_unitname("None");}
 		
 		if(Utility.isNullOrEmpty(scn.getParty())) {
 			scn.setPartyname(cust_bussiness_partnerRepository.getCustomer(scn.getParty()).getCp_name());
 		}else {scn.setPartyname("None");}
 		
 		if(Utility.isNullOrEmpty(scn.getInvoice_type())) {
 			scn.setInvoice_typename(invoice_typeRepository.getSalesInvTypesDtls(scn.getInvoice_type()).getInvtype_name());
		}else {scn.setInvoice_typename("None");}
		
 		scn.setModified_type("INSERTED");
 		scn.setInserted_by(op.get().getInserted_by());
 		scn.setInserted_on(op.get().getInserted_on());
 		scn.setUpdated_by(userRepository.getUserDetails(scn.getUsername()).getName());
 		scn.setUpdated_on(ldt);
 		scn.setDeleted_by("NA");
 		scn.setDeleted_on(ldt);
 		if(scn.getReferance_id().compareToIgnoreCase("0") == 0)
 		{
 			scn.setReferance_id(op.get().getReferance_id());
 		}
 		else 
 		{
 			if(op.get().getChallan().compareToIgnoreCase("Multiple")==0)
 	 		{
 				if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection") == 0)
	 	 		{
	 				String [] multipleSalesReturnNote = op.get().getReferance_id().split(",");
	 				for(int i=0;i<multipleSalesReturnNote.length;i++)
	 				{
	 					sales_return_noteRepository.updatedUniqueSalesNoteToCreditNote(multipleSalesReturnNote[i],scn.getCreditnoteid());
	 				}
	 	 			
	 	 		}
	 	 		
	 	 		if(op.get().getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate") == 0)
	 	 		{
	 	 			String [] multipleSalesReturnApproval = op.get().getReferance_id().split(",");
	 	 			for(int j=0;j<multipleSalesReturnApproval.length;j++)
	 	 			{
	 	 				return_approval_noteRepository.updatedUniqueApproveNoteToCreditNote(multipleSalesReturnApproval[j],scn.getCreditnoteid());	
	 	 			}
	 	 		}
 	 		}
 			else
 			{
 				if(scn.getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection") == 0)
 		 		{
 		 			sales_return_noteRepository.updatedUniqueSalesNoteToCreditNote(scn.getReferance_id(),scn.getCreditnoteid());
 		 		}
 		 		
 		 		if(scn.getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate") == 0)
 		 		{
 		 			return_approval_noteRepository.updatedUniqueApproveNoteToCreditNote(scn.getReferance_id(),scn.getCreditnoteid());
 		 		}
 			}
	 		
 		}
 		
 		if(scn.getInvoice_type().compareToIgnoreCase("INV00003")==0) 
		{
 			scn.getCredit_item_groupwise_hsnsumm().clear();
 			scn.getSales_credit_note_item_dtls().clear();
 			scn.getCredit_item_groupwise_summ().clear();
 			
 			sales_credit_noteRepository.creditNoteJobItemupdate(op.get().getCreditnoteid(),"UPDATED");
 			
 			Set<Sales_credit_note_item_dtls_for_jobwork> jobSet = new HashSet<Sales_credit_note_item_dtls_for_jobwork>();
			jobSet.addAll(scn.getSales_credit_note_item_dtls_for_jobwork());
			for(Sales_credit_note_item_dtls_for_jobwork jobDts : jobSet)
			{
				jobDts.setCreditnoteid(op.get().getCreditnoteid());
				jobDts.setCreditnoteno(scn.getCreditnoteno());
				
				jobDts.setJob_item_name(item_masterRepository.itemNameById(jobDts.getJob_item()).getItem_name());
				if(Utility.isNullOrEmpty(jobDts.getJob_packing())) 
				{
					jobDts.setJob_packing_name(item_masterRepository.itemNameById(jobDts.getJob_packing()).getItem_name());
				}
				else
				{
					jobDts.setJob_packing_name("0");
				}
				jobDts.setCompany_id(scn.getCompany_id());
				jobDts.setFin_year(scn.getFin_year());
				jobDts.setModified_type("INSERTED");
				jobDts.setInserted_by(scn.getInserted_by());
				jobDts.setInserted_on(scn.getInserted_on());
				jobDts.setUpdated_by(userRepository.getUserDetails(scn.getUsername()).getName());
				jobDts.setUpdated_on(ldt);
				jobDts.setDeleted_by("NA");
				jobDts.setDeleted_on(ldt);
				
			}
			scn.setSales_credit_note_item_dtls_for_jobwork(jobSet);
			
			sales_credit_noteRepository.creditNoteServiceItemupdate(op.get().getCreditnoteid(),"UPDATED");
			
			Set<Sales_credit_note_item_dtls_for_jobwork_price> serviceItem = new HashSet<Sales_credit_note_item_dtls_for_jobwork_price>();
			serviceItem.addAll(scn.getSales_credit_note_item_dtls_for_jobwork_price());
			for(Sales_credit_note_item_dtls_for_jobwork_price service : serviceItem)
			{
				service.setCreditnoteid(op.get().getCreditnoteid());
				service.setCreditnoteno(scn.getCreditnoteno());
				
				if(Utility.isNullOrEmpty(service.getItem_service())) {
					service.setItem_service_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getService_name());
				}else{service.setItem_service_name("0");};
				
				if(Utility.isNullOrEmpty(service.getTaxcode())) {
					service.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(service.getTaxcode()).getTax_name());
				}else{service.setTaxcode_name("0");};
				
				if(Utility.isNullOrEmpty(service.getItem_service())) {
					service.setItem_service_acc_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getAc_ledger_name());
				}else{service.setItem_service_acc_name("0");};
				
				service.setCompany_id(scn.getCompany_id());
				service.setFin_year(scn.getFin_year());
				service.setModified_type("INSERTED");
				service.setInserted_by(scn.getInserted_by());
				service.setInserted_on(scn.getInserted_on());
				service.setUpdated_by(userRepository.getUserDetails(scn.getUsername()).getName());
				service.setUpdated_on(ldt);
				service.setDeleted_by("NA");
				service.setDeleted_on(ldt);
			}
			scn.setSales_credit_note_item_dtls_for_jobwork_price(serviceItem);
 			
 			
			}
	 		else 
	 		{
	 			scn.getSales_credit_note_item_dtls_for_jobwork().clear();
	 			scn.getSales_credit_note_item_dtls_for_jobwork_price().clear();
	 			
	 			sales_credit_note_item_dtlsRepository.updateSales_credit_note_item_dtls(op.get().getCreditnoteid(),updated);
				
				Set<Sales_credit_note_item_dtls> sItem_dtls = new HashSet<Sales_credit_note_item_dtls>();
		        sItem_dtls.addAll(scn.getSales_credit_note_item_dtls());
				for(Sales_credit_note_item_dtls sItemDtls : sItem_dtls) 
				{
					sItemDtls.setCreditnoteid(op.get().getCreditnoteid());
					sItemDtls.setCreditnoteno(scn.getCreditnoteno());
					if(sItemDtls.getItem_code().compareTo("0") !=0 && sItemDtls.getItem_code().compareTo("") !=0 && sItemDtls.getItem_code() !=null) {
						sItemDtls.setItem_name(item_masterRepository.itemNameById(sItemDtls.getItem_code()).getItem_name());
					}else {sItemDtls.setItem_name("None");}
					
					if(Utility.isNullOrEmpty(sItemDtls.getTax_code())) {
	 					sItemDtls.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(sItemDtls.getTax_code()).getTax_name());
	 				}else {sItemDtls.setTaxcode_name("None");}
					
					//sItemDtls.setItem_name(item_masterRepository.itemNameById(sItemDtls.getItem_code()).getItem_name());
					//if(Utility.isNullOrEmpty(sItemDtls.getPacking())) {
					if(sItemDtls.getPacking().compareTo("0") !=0 && sItemDtls.getPacking().compareTo("") !=0 && sItemDtls.getPacking() !=null) {
						sItemDtls.setPacking_name(item_masterRepository.itemNameById(sItemDtls.getPacking()).getItem_name());
					}else {sItemDtls.setPacking_name("None");}
					
					sItemDtls.setItem_groupname(item_group_masterRepository.getGroupDtls_converter(sItemDtls.getItem_group()).getGroup_name());
					
					sItemDtls.setCompany_id(scn.getCompany_id());
					sItemDtls.setFin_year(scn.getFin_year());
					sItemDtls.setModified_type(scn.getModified_type());
					sItemDtls.setInserted_by(scn.getInserted_by());
					sItemDtls.setInserted_on(scn.getInserted_on());
					sItemDtls.setUpdated_by(scn.getUpdated_by());
					sItemDtls.setUpdated_on(scn.getUpdated_on());
					sItemDtls.setDeleted_by(scn.getDeleted_by());
					sItemDtls.setDeleted_on(scn.getDeleted_on());
				}
				scn.setSales_credit_note_item_dtls(sItem_dtls);
				
	 		}
	 		
 		
 			sales_credit_note_bp_dtlsRepository.updateSales_credit_note_bp_dtls(op.get().getCreditnoteid(),updated);
			
 			Set<Sales_credit_note_bp_dtls> scn1 = new HashSet<Sales_credit_note_bp_dtls>();
			scn1.add(scn.getSales_credit_note_bp_dtls());
			for(Sales_credit_note_bp_dtls bpd : scn1)
			{
				bpd.setCreditnoteid(op.get().getCreditnoteid());
				bpd.setCreditnoteno(scn.getCreditnoteno());
				bpd.setCompany_id(scn.getCompany_id());
				bpd.setFin_year(scn.getFin_year());
				bpd.setModified_type(scn.getModified_type());
				bpd.setInserted_by(scn.getInserted_by());
				bpd.setInserted_on(scn.getInserted_on());
				bpd.setUpdated_by(scn.getUpdated_by());
				bpd.setUpdated_on(scn.getUpdated_on());
				bpd.setDeleted_by(scn.getDeleted_by());
				bpd.setDeleted_on(scn.getDeleted_on());
			}
			if(!scn1.isEmpty())	{
				scn.setSales_credit_note_bp_dtls(scn1.iterator().next());
			}
			
			sales_credit_note_broker_dtlsRepository.updateSales_credit_note_broker_dtls(op.get().getCreditnoteid(),updated);
				
			Set<Sales_credit_note_broker_dtls> scn2 = new HashSet<Sales_credit_note_broker_dtls>();
			scn2.addAll(scn.getSales_credit_note_broker_dtls());
			for(Sales_credit_note_broker_dtls bd : scn2) 				{
				bd.setCreditnoteid(op.get().getCreditnoteid());
				bd.setCreditnoteno(scn.getCreditnoteno());
				bd.setCompany_id(scn.getCompany_id());
				bd.setFin_year(scn.getFin_year());
				bd.setModified_type(scn.getModified_type());
				bd.setInserted_by(scn.getInserted_by());
				bd.setInserted_on(scn.getInserted_on());
				bd.setUpdated_by(scn.getUpdated_by());
				bd.setUpdated_on(scn.getUpdated_on());
				bd.setDeleted_by(scn.getDeleted_by());
				bd.setDeleted_on(scn.getDeleted_on());
			}
			scn.setSales_credit_note_broker_dtls(scn2);
				
			sales_credit_note_docsRepository.updateSales_credit_note_docs(op.get().getCreditnoteid(),updated);
			
			Set<Sales_credit_note_docs> scn3 = new HashSet<Sales_credit_note_docs>();
			scn3.addAll(scn.getSales_credit_note_docs());
			for(Sales_credit_note_docs d : scn3) 				{
				d.setCreditnoteid(op.get().getCreditnoteid());
				d.setCreditnoteno(scn.getCreditnoteno());
				d.setCompany_id(scn.getCompany_id());
				d.setFin_year(scn.getFin_year());
				d.setModified_type(scn.getModified_type());
				d.setInserted_by(scn.getInserted_by());
				d.setInserted_on(scn.getInserted_on());
				d.setUpdated_by(scn.getUpdated_by());
				d.setUpdated_on(scn.getUpdated_on());
				d.setDeleted_by(scn.getDeleted_by());
				d.setDeleted_on(scn.getDeleted_on());
			}
			scn.setSales_credit_note_docs(scn3);
				
			sales_credit_note_payment_dtlsRepository.updateSales_credit_note_payment_dtls(op.get().getCreditnoteid(),updated);
			
			Set<Sales_credit_note_payment_dtls> scn5 = new HashSet<Sales_credit_note_payment_dtls>();
			scn5.add(scn.getSales_credit_note_payment_dtls());
			for(Sales_credit_note_payment_dtls pd : scn5)
			{
				pd.setCreditnoteid(op.get().getCreditnoteid());
				pd.setCreditnoteno(scn.getCreditnoteno());
				pd.setCompany_id(scn.getCompany_id());
				pd.setFin_year(scn.getFin_year());
				pd.setModified_type(scn.getModified_type());
				pd.setInserted_by(scn.getInserted_by());
				pd.setInserted_on(scn.getInserted_on());
				pd.setUpdated_by(scn.getUpdated_by());
				pd.setUpdated_on(scn.getUpdated_on());
				pd.setDeleted_by(scn.getDeleted_by());
				pd.setDeleted_on(scn.getDeleted_on());
			}
			if(!scn5.isEmpty())	{
				scn.setSales_credit_note_payment_dtls(scn5.iterator().next());
			}
				
			sales_credit_note_shipment_dtlsRepository.updateSales_credit_note_shipment_dtls(op.get().getCreditnoteid(),updated);
			
			Set<Sales_credit_note_shipment_dtls> scn6 = new HashSet<Sales_credit_note_shipment_dtls>();
			scn6.add(scn.getSales_credit_note_shipment_dtls());
			for(Sales_credit_note_shipment_dtls sd : scn6)
			{ 
				sd.setCreditnoteid(op.get().getCreditnoteid());
				sd.setCreditnoteno(scn.getCreditnoteno());
				sd.setCompany_id(scn.getCompany_id());
				sd.setFin_year(scn.getFin_year());
				sd.setModified_type(scn.getModified_type());
				sd.setInserted_by(scn.getInserted_by());
				sd.setInserted_on(scn.getInserted_on());
				sd.setUpdated_by(scn.getUpdated_by());
				sd.setUpdated_on(scn.getUpdated_on());
				sd.setDeleted_by(scn.getDeleted_by());
				sd.setDeleted_on(scn.getDeleted_on());
			}
			if(!scn6.isEmpty())	{
				scn.setSales_credit_note_shipment_dtls(scn6.iterator().next());
			}
				
			sales_credit_note_tax_infoRepository.updateSales_credit_note_tax_info(op.get().getCreditnoteid(),updated);
			
			Set<Sales_credit_note_tax_info> scn7 = new HashSet<Sales_credit_note_tax_info>();
			scn7.add(scn.getSales_credit_note_tax_info());
			for(Sales_credit_note_tax_info ti : scn7)
			{
				ti.setCreditnoteid(op.get().getCreditnoteid());
				ti.setCreditnoteno(scn.getCreditnoteno());
				ti.setCompany_id(scn.getCompany_id());
				ti.setFin_year(scn.getFin_year());
				ti.setModified_type(scn.getModified_type());
				ti.setInserted_by(scn.getInserted_by());
				ti.setInserted_on(scn.getInserted_on());
				ti.setUpdated_by(scn.getUpdated_by());
				ti.setUpdated_on(scn.getUpdated_on());
				ti.setDeleted_by(scn.getDeleted_by());
				ti.setDeleted_on(scn.getDeleted_on());
			}
			if(!scn7.isEmpty())	{
				scn.setSales_credit_note_tax_info(scn7.iterator().next());
			}
				
			sales_credit_note_trans_dtlsRepository.updateSales_credit_note_trans_dtls(op.get().getCreditnoteid(),updated);
			
			Set<Sales_credit_note_trans_dtls> scn8 = new HashSet<Sales_credit_note_trans_dtls>();
			scn8.addAll(scn.getSales_credit_note_trans_dtls());
			for(Sales_credit_note_trans_dtls td : scn8) 				{
				
				td.setCreditnoteid(op.get().getCreditnoteid());
				td.setCreditnoteno(scn.getCreditnoteno());
				
				//System.out.println("Transporter:"+td.getTransname());
				if(Utility.isNullOrEmpty(td.getVehicleno())) {
 					td.setVehicle(vehicleMasterRepository.getVehicleDetails(td.getVehicleno()).getVehicle_no());
 				}else {td.setVehicle("None");}
				
				if(Utility.isNullOrEmpty(td.getTransname())) 
				{td.setTransporter(trans_bussiness_partnerRepository.bpNameById(td.getTransname()).getBp_name());}
				else{td.setTransporter("None");};
				
				td.setCompany_id(scn.getCompany_id());
				td.setFin_year(scn.getFin_year());
				td.setModified_type(scn.getModified_type());
				td.setInserted_by(scn.getInserted_by());
				td.setInserted_on(scn.getInserted_on());
				td.setUpdated_by(scn.getUpdated_by());
				td.setUpdated_on(scn.getUpdated_on());
				td.setDeleted_by(scn.getDeleted_by());
				td.setDeleted_on(scn.getDeleted_on());
			}
			scn.setSales_credit_note_trans_dtls(scn8);
 		
 		return sales_credit_noteRepository.save(scn);
	}

	public String findParentSubGroupCode(String ledgerid, String bunit) {
		String parent="",str="",x1="",x2="",x3="",x4="",x5="",x6="",x7="",x8="",x9="",x10="",x11="";
		List<String> subcode = new ArrayList<String>();
		if(ledgerid.length()==10)
		{
			subcode.add(ledgerid.substring(0,5));
			if(subgroupmasterRepository.getParentSubGroup(ledgerid.substring(0, 5)).isPresent()) {
				parent=subgroupmasterRepository.getParentSubGroup(ledgerid.substring(0, 5)).get().getParent_subgroupcode();
			}
			else
			{
				subcode.add(ledgerid.substring(0,2));
				if(subgroupmasterRepository.getParentSubGroup(ledgerid.substring(0, 2)).isPresent()) {
					parent=subgroupmasterRepository.getParentSubGroup(ledgerid.substring(0, 2)).get().getParent_subgroupcode();	
				}
			}
		}
		else
		{
			subcode.add(ledgerid.substring(0,2));
			if(subgroupmasterRepository.getParentSubGroup(ledgerid.substring(0, 2)).isPresent()) {
				parent=subgroupmasterRepository.getParentSubGroup(ledgerid.substring(0, 2)).get().getParent_subgroupcode();
			}
		}
		
		subcode.add(parent);
		System.err.println("Parent: "+parent);
		if(parent.compareTo("") !=0)
		{
			System.err.println("Get values:>>>>>>>> Parent: "+parent+" ,Branch: "+bunit);
			if(subgroupmasterRepository.getParentSubGroup(parent).isPresent()) {
				x1 = subgroupmasterRepository.getParentSubGroup(parent).get().getParent_subgroupcode();
			}
			System.err.println("Parent x1: "+x1);
			subcode.add(x1);
		}	
		if(x1.compareTo("") !=0)
		{
			if(subgroupmasterRepository.getParentSubGroup(x1).isPresent()) {
				x2 = subgroupmasterRepository.getParentSubGroup(x1).get().getParent_subgroupcode();
			}
			System.err.println("Parent x2: "+x2);
			subcode.add(x2);
		}
		if(x2.compareTo("") !=0)
		{
			if(subgroupmasterRepository.getParentSubGroup(x2).isPresent()) {
				x3 = subgroupmasterRepository.getParentSubGroup(x2).get().getParent_subgroupcode();
			}
			System.err.println("Parent x3: "+x3);
			subcode.add(x3);
		}
		if(x3.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x3).isPresent()) {
				x4 = subgroupmasterRepository.getParentSubGroup(x3).get().getParent_subgroupcode();
			}
			System.err.println("Parent x4: "+x4);
			subcode.add(x4);
		}
		if(x4.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x4).isPresent()) {
				x5 = subgroupmasterRepository.getParentSubGroup(x4).get().getParent_subgroupcode();
			}
			System.err.println("Parent x5: "+x5);
			subcode.add(x5);
		}
		if(x5.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x5).isPresent()) {
				x6 = subgroupmasterRepository.getParentSubGroup(x5).get().getParent_subgroupcode();
			}
			System.err.println("Parent x6: "+x6);
			subcode.add(x6);
		}
		if(x6.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x6).isPresent()) {
				x7 = subgroupmasterRepository.getParentSubGroup(x6).get().getParent_subgroupcode();
			}
			System.err.println("Parent x7: "+x7);
			subcode.add(x7);
		}	
		if(x7.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x7).isPresent()) {
				x8 = subgroupmasterRepository.getParentSubGroup(x7).get().getParent_subgroupcode();
			}
			System.err.println("Parent x8: "+x8);
			subcode.add(x8);
		}
		if(x8.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x8).isPresent()) {
				x9 = subgroupmasterRepository.getParentSubGroup(x8).get().getParent_subgroupcode();
			}
			System.err.println("Parent x9: "+x9);
			subcode.add(x9);
		}
		if(x9.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x9).isPresent()) {
				x10 = subgroupmasterRepository.getParentSubGroup(x9).get().getParent_subgroupcode();
			}
			System.err.println("Parent x10: "+x10);
			subcode.add(x10);
		}
		if(x10.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x10).isPresent()) {
				x11 = subgroupmasterRepository.getParentSubGroup(x10).get().getParent_subgroupcode();
			}
			System.err.println("Parent x11: "+x11);
			subcode.add(x11);
		}
		
		for (int i = 0; i < subcode.size(); i++) {
			if (i == 0) {
				str += subcode.get(i);
			} else {
				str += "x@nk!tltdx" + subcode.get(i);
			}
		}
		return str;
	}
	
	
	@Transactional
	public Sales_credit_note deleteCreditNotes(Sales_credit_note creditNote,Long id) {

		Optional<Sales_credit_note> op = sales_credit_noteRepository.findById(id);
		Sales_credit_note credit_Note=sales_credit_noteRepository.getCreditNoteDetails(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		//Sales_credit_note creditNoteStatus=new Sales_credit_note();
		
			if(op.isPresent())	{
				credit_Note.setId(id);
			}
			
			credit_Note.setModified_type("DELETED");
			credit_Note.setInserted_by(op.get().getInserted_by());
			credit_Note.setInserted_on(op.get().getInserted_on());
			credit_Note.setUpdated_by(op.get().getUpdated_by());
			credit_Note.setUpdated_on(op.get().getUpdated_on());
			credit_Note.setDeleted_by(userRepository.getUserDetails(credit_Note.getUsername()).getName());
			credit_Note.setDeleted_on(ldt);
			
			if(credit_Note.getInvoice_type().compareToIgnoreCase("INV00003")==0) 
			{
				sales_credit_noteRepository.creditNoteJobItemupdate(op.get().getCreditnoteid(),"DELETED");
				sales_credit_noteRepository.creditNoteServiceItemupdate(op.get().getCreditnoteid(),"DELETED");
			}
			else
			{
				sales_credit_noteRepository.sales_credit_note_item_dtlsupdate(op.get().getCreditnoteid());
				sales_credit_noteRepository.credit_item_groupwise_hsnsummupdate(op.get().getCreditnoteid());
				sales_credit_noteRepository.credit_item_groupwise_summupdate(op.get().getCreditnoteid());
			}
			
			sales_credit_noteRepository.sales_credit_note_bp_dtlsupdate(op.get().getCreditnoteid());
			
			sales_credit_noteRepository.sales_credit_note_broker_dtlsupdate(op.get().getCreditnoteid());
			
			sales_credit_noteRepository.sales_credit_note_docsupdate(op.get().getCreditnoteid());
			
			sales_credit_noteRepository.sales_credit_note_payment_dtlsupdate(op.get().getCreditnoteid());
			
			sales_credit_noteRepository.sales_credit_note_shipment_dtlsupdate(op.get().getCreditnoteid());
			
			sales_credit_noteRepository.sales_credit_note_tax_infoupdate(op.get().getCreditnoteid());
			
			sales_credit_noteRepository.sales_credit_note_trans_dtlsupdate(op.get().getCreditnoteid());
			
			sales_credit_noteRepository.credit_item_groupwise_taxsummupdate(op.get().getCreditnoteid());
			
			
			
			/*if(op.get().getChallan().compareToIgnoreCase("Multiple")==0)
	 		{
				if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection") == 0)
	 	 		{
	 				String [] multipleSalesReturnNote = op.get().getReferance_id().split(",");
	 				for(int i=0;i<multipleSalesReturnNote.length;i++)
	 				{
	 					sales_return_noteRepository.updatedUniqueSalesNoteToCreditNoteDeleted(multipleSalesReturnNote[i]);
	 				}
	 	 			
	 	 		}
	 	 		
	 	 		if(op.get().getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate") == 0)
	 	 		{
	 	 			System.out.println("Return Approval multiple"+op.get().getReferance_id());
	 	 			String [] multipleSalesReturnApproval = op.get().getReferance_id().split(",");
	 	 			for(int j=0;j<multipleSalesReturnApproval.length;j++)
	 	 			{
	 	 				return_approval_noteRepository.updatedUniqueApproveNoteToCreditNoteDelete(multipleSalesReturnApproval[j]);	
	 	 			}
	 	 		}
	 		}
 	 		else
 	 		{*/
 	 			if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection") == 0)
 		 		{
 		 			sales_return_noteRepository.updatedUniqueSalesNoteToCreditNoteDeleted(op.get().getCreditnoteid());
 		 		}
 		 		
 		 		if(op.get().getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate") == 0)
 		 		{
 		 			return_approval_noteRepository.updatedUniqueApproveNoteToCreditNoteDelete(op.get().getCreditnoteid());
 		 		}
 	 		/*}*/
			
 	 		
			
			return sales_credit_noteRepository.save(credit_Note);	
		}
	
		public List<Credit_item_groupwise_taxsumm> getcreditnotetaxcodes(String creditnoteid)
		{
			List<Credit_item_groupwise_taxsumm> modelList=sales_credit_note_item_dtlsRepository.getcreditnotetaxcodes(creditnoteid);
			
			Sales_credit_note cr = sales_credit_noteRepository.getCreditNoteDetailsthcerdit(creditnoteid);
			
			if(cr.getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0)
			{
				modelList.forEach(element->
				{
					System.out.println("here watch "+return_approval_noteRepository.getReturnApprovalDtls(cr.getReferance_id()).getReferance_id());
					Item_groupwise_taxsumm tax= item_groupwise_taxsummRepository.getItem_groupwise_taxsummbyinvoiceid(return_approval_noteRepository.getReturnApprovalDtls(cr.getReferance_id()).getReferance_id());
					
					element.setCgst(tax.getCgst() - element.getCgst());
					element.setSgst(tax.getSgst() - element.getSgst());
					element.setIgst(tax.getIgst() - element.getIgst());
				});
			}
			
			
			
			return modelList;
		}
		
		
		@Transactional
		public Sales_credit_note accountpostingCreditNote(long id) 
		{
			Optional<Sales_credit_note> op=this.sales_credit_noteRepository.findById(id);//static details 
			
			if(op.get().getInvoice_type().compareToIgnoreCase("INV00003")==0) 
			{
				String partyname=op.get().getPartyname();
				String creditnotedate=op.get().getCreditnotedate();
				
				double partyamount=0;
				if(op.get().getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0) 
				{
					partyamount=(sales_InvoiceRepository.getSalesInvDetails
							((return_approval_noteRepository.getReturnApprovalDtls
									(op.get().getReferance_id()).getReferance_id())).getPayable_amt())- op.get().getPayable_amt();
				}
				else 
				{
					 partyamount=op.get().getPayable_amt();
				}
				
				
				String trucknumber="";
				if(op.get().getChallan().compareToIgnoreCase("Single")==0)
				{
					if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection")==0) 
					{
						String returnnoteid=sales_return_noteRepository.getSalesReturnNoteDtls(op.get().getReferance_id()).getReferance_id();
						
						trucknumber=wm_unload_adviceRepository.getTallyvehicleno(returnnoteid).getVehicle_no();
					}
					else 
					{
						trucknumber=return_approval_noteRepository.getTallyvehicleno(op.get().getReferance_id()).getVehicleno();
					}
					
				}
				if(op.get().getChallan().compareToIgnoreCase("Multiple")==0)
				{
					String variousrefid[]=op.get().getReferance_id().split(",");
					
					if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection")==0) 
					{
						String returnnoteid=sales_return_noteRepository.getSalesReturnNoteDtls(variousrefid[0]).getReferance_id();
						
						trucknumber=wm_unload_adviceRepository.getTallyvehicleno(returnnoteid).getVehicle_no();
					}
					else 
					{
						trucknumber=return_approval_noteRepository.getTallyvehicleno(variousrefid[0]).getVehicleno();
					}
				}
				String statename=cust_bussiness_partnerRepository.gettallycreditnotestate(op.get().getParty()).getState();//party
			
				
				//jobworkitemstarts here 
				Sales_credit_note_item_dtls_for_jobwork  jobwork=sales_credit_noteRepository.getInvoiceJobItemDtlstally(op.get().getCreditnoteid());
				
				Sales_credit_note_item_dtls_for_jobwork_price jobworkprice=sales_credit_noteRepository.getSalesInvItmjobpriceDtlstally(op.get().getCreditnoteid());
				
				String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(jobwork.getJob_item()).getItem_group();
				
				Item_group_jobwork_sales_return_acc itemgroup = item_group_jobwork_sales_return_accRepository.getItemGroupJobworkSalesReturn(subgroup_items_code);
				
				Item_Service_master sericemaster=item_Service_masterRepository.serviceNameById(itemgroup.getJw_sr_item_array());
				
				
				String item_name_ledger=sericemaster.getAc_ledger_name();//done on basis of single data
	        
				
				String item_amount=String.valueOf(jobworkprice.getTax_value());
				
				String item_name=jobwork.getJob_item_name();
				
				String  item_rate=String.valueOf(jobworkprice.getJob_price());
				
				String packing_uom=jobwork.getPack_uom();
				
				String packing_qty=String.valueOf(jobwork.getPack_qty());
				
				String item_qty=jobwork.getItem_uom();
				
				String price_based_on=jobwork.getPrice_based_on();
				
				String item_passeditemqty=String.valueOf(jobwork.getItem_qty());
				
				//job work item ends here  
		        
		      
		        
		      
		  		
		  		
				//gst starts here 
				Credit_item_groupwise_taxsumm gst_details=sales_credit_noteRepository.getgstdetails(op.get().getCreditnoteid());
				boolean gst_valid=true,gststatus=true;
				
				String cgstledger="",sgstledger="",igstledger="";
				double cgstamount=0.00,sgstamount=0.00,igstamount=0.00;
				
				if(gst_details.getCgst() == 0.00 && gst_details.getSgst() == 0.00 && gst_details.getIgst() ==0.00) 
				{
					gst_valid=false;
				}
				else if( gst_details.getCgst() == 0.00 && gst_details.getSgst() == 0.00 && gst_details.getIgst() !=0.00 ) 
				{
					gststatus=false;
					igstledger=ledgermasterRepository.getLedgerDetails(gst_details.getIgstledgerid()).getLedgername();
					
					
					if(op.get().getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0)
					{
						Item_groupwise_taxsumm tax= item_groupwise_taxsummRepository.getItem_groupwise_taxsummbyinvoiceid(return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id()).getReferance_id());
						igstamount=tax.getIgst()-gst_details.getIgst();
					}
					else 
					{
						igstamount= gst_details.getIgst();
					}
					
					
				}
				else 
				{
					gststatus=true;
					cgstledger=ledgermasterRepository.getLedgerDetails(gst_details.getCgstledgerid()).getLedgername();
					sgstledger=ledgermasterRepository.getLedgerDetails(gst_details.getSgstledgerid()).getLedgername();
					
				
					if(op.get().getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0)
					{
						Item_groupwise_taxsumm tax= item_groupwise_taxsummRepository.getItem_groupwise_taxsummbyinvoiceid(return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id()).getReferance_id());
						cgstamount=tax.getCgst() - gst_details.getCgst();
						sgstamount=tax.getSgst() - gst_details.getSgst();
					}
					else 
					{
						cgstamount=gst_details.getCgst();
						sgstamount= gst_details.getSgst();
					}
					
				}
				
				//gst ends here 
				
				String brokercode=sales_credit_note_broker_dtlsRepository.getSales_credit_note_broker_dtlsposting(op.get().getCreditnoteid()).getBrokercode();
				String broker="";
				if(Utility.isNullOrEmpty(brokercode)) 
				{
					broker=broker_masterRepository.brokerNameByCode(brokercode).getName();
				}
				else 
				{
					broker="XXXXX";
				}

				String address_full=sales_credit_note_shipment_dtlsRepository.getSales_credit_note_shipment_dtls(op.get().getCreditnoteid()).getPaytodtls();
				
				String address_split[]=address_full.split(",");
				String address=address_split[0];
				
				Sales_return_note sale_return=sales_return_noteRepository.getSalesReturnNoteDtls(op.get().getReferance_id());
				
				
				String invoicenumber=op.get().getCreditnoteno();
				//gst ends here 
				String date=op.get().getCreditnotedate().replace("-", ""); 
				Sales_Invoice  salesincoice = new Sales_Invoice();
				String deliverynotedate="";
				String deliverynoteno="";
				
				if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection")==0)
				{
					Sales_return_note sale_return1=sales_return_noteRepository.getSalesReturnNoteDtls(op.get().getReferance_id());
					
					deliverynotedate=sale_return1.getSalesreturnnotedate().replaceAll("-", "");
					deliverynoteno=sale_return1.getSalesreturnnoteno();
					
					String salereturnid=op.get().getReferance_id();
					System.out.println("1 " + salereturnid);

					String returnappid=sales_return_noteRepository.getSalesRtnNoteUpdate(salereturnid).getReferance_id();
					System.out.println("2 " + returnappid);
					String saleinvoiceid=return_approval_noteRepository.getReturnApprovalDtls(returnappid).getReferance_id();
					System.out.println(" 3 " + saleinvoiceid);


					salesincoice= sales_InvoiceRepository.getSalesInvDetailsthroughdelivery(saleinvoiceid);  
					System.out.println();
				}
				else 
				{
					Return_approval_note returnap =return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id());
					
					deliverynotedate=returnap.getSalesreturndate().replaceAll("-", "");
					deliverynoteno=returnap.getSalesreturnno();
					
					
					String saleinvoiceid=return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id()).getReferance_id();
					System.out.println(" 3 " + saleinvoiceid);


					salesincoice= sales_InvoiceRepository.getSalesInvDetails(saleinvoiceid);  
					
				}

				String saleinvoiceno=salesincoice.getInvoice_no();
				String saleinvoicedate=salesincoice.getInvoice_date().replace("-", "");
				String output;
				Tallyrequest_CreditnoteJobwork tally=new Tallyrequest_CreditnoteJobwork();
				
				 String supplier_address=cust_bussiness_partner_addressRepository.custAddRetriveList(op.get().getParty()).getDistrict();
				 
				  //round off starts here  
		          Ledgermaster ledgerid=ledgermasterRepository.getLedgerDetails(op.get().getRoundoff_gl_ac());
		          int rounfoffdrcr=0;
		          //minus=cr,plus=dr
		          String roundoffglaccount =null;
		          double roundoff_amt=0;
		          if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection")==0)
				  {
		        	  roundoff_amt=op.get().getRoundoff_amt();
				  }
		          else 
		          {
		        	  
		        	  roundoff_amt=partyamount-Double.parseDouble(item_amount)-cgstamount-sgstamount-igstamount;
		        	  roundoff_amt=Double.parseDouble(String.format("%.2f", roundoff_amt));
		          }
		      	  
		          if(roundoff_amt>0)//plus means cr
		          {
		          	rounfoffdrcr=1;//cr
		          	roundoffglaccount=ledgerid.getLedgername();
		          	
		          }
		          if(roundoff_amt<0)//minus meand debit 
		          {
		          	  rounfoffdrcr=-1;//dr
		              roundoffglaccount=ledgerid.getLedgername();
		            
		          }
		         
		  	
		  		//round off ends here 
				try  
				{
					
					if(partyname.contains("&"))
					{
						partyname=partyname.replaceAll("&","&amp;");
						
					}
					if(broker.contains("&"))
					{
						broker=broker.replaceAll("&","&amp;");
						
					}
					String discountamount="",discountledger="";
					boolean discountstat=false;
				
					output = tally.SendToTally(partyname,trucknumber,creditnotedate,statename,partyamount,item_name_ledger,
							 item_name,item_amount,item_rate,item_passeditemqty,item_qty,Double.parseDouble(item_amount),String.valueOf(roundoff_amt),
							 roundoffglaccount,rounfoffdrcr,gst_valid,gststatus,cgstledger,sgstledger,igstledger,
							 cgstamount,sgstamount,igstamount,   date,invoicenumber,discountamount,discountledger,
							 discountstat,broker,address,deliverynotedate,deliverynoteno,packing_qty,price_based_on
							 ,packing_uom,supplier_address,saleinvoiceno,saleinvoicedate);
			    
					
					System.out.println(" output :: "+output);
					String [] splitoutput = output.split("\\|\\|");
					System.out.println(splitoutput[0] +" / " + splitoutput[1]+"/"+id);
					
					sales_credit_noteRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
					
				}
				catch(Exception e)
				{
				
					System.out.println(e);
				}
			}
			else //without jobwork
			{
				String partyname=op.get().getPartyname();
				String creditnotedate=op.get().getCreditnotedate();
				
				double partyamount=0;
				if(op.get().getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0) 
				{
					partyamount=(sales_InvoiceRepository.getSalesInvDetails
							((return_approval_noteRepository.getReturnApprovalDtls
									(op.get().getReferance_id()).getReferance_id())).getPayable_amt())- op.get().getPayable_amt();
				}
				else 
				{
					 partyamount=op.get().getPayable_amt();
				}
				
				System.out.println(partyamount + " // " +  op.get().getCreditnotetype());
				
				
				
				
				String trucknumber="";
				if(op.get().getChallan().compareToIgnoreCase("Single")==0)
				{
					if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection")==0) 
					{
						String returnnoteid=sales_return_noteRepository.getSalesReturnNoteDtls(op.get().getReferance_id()).getReferance_id();
						
						trucknumber=wm_unload_adviceRepository.getTallyvehicleno(returnnoteid).getVehicle_no();
					}
					else 
					{
						trucknumber=return_approval_noteRepository.getTallyvehicleno(op.get().getReferance_id()).getVehicleno();
					}
					
				}
				if(op.get().getChallan().compareToIgnoreCase("Multiple")==0)
				{
					String variousrefid[]=op.get().getReferance_id().split(",");
					
					if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection")==0) 
					{
						String returnnoteid=sales_return_noteRepository.getSalesReturnNoteDtls(variousrefid[0]).getReferance_id();
						
						trucknumber=wm_unload_adviceRepository.getTallyvehicleno(returnnoteid).getVehicle_no();
					}
					else 
					{
						trucknumber=return_approval_noteRepository.getTallyvehicleno(variousrefid[0]).getVehicleno();
					}
				}
				String statename=cust_bussiness_partnerRepository.gettallycreditnotestate(op.get().getParty()).getState();//party
			
			//item details starts here	
				List<Sales_credit_note_item_dtls> itemDetails=sales_credit_note_item_dtlsRepository.getSales_credit_note_item_dtls(op.get().getCreditnoteid());//item details
				
				String itemSubGroupName="";boolean discountstat=false;
				 String item_name_ledger[]=new String[itemDetails.size()];
		        String item_name[]=new String[itemDetails.size()];
		        String item_amount[]=new String[itemDetails.size()];
		        String item_rate[]=new String[itemDetails.size()];
		        String item_passeditemqty[]=new String[itemDetails.size()];
		        String packing_qty[]=new String[itemDetails.size()];
		        String item_qty[]=new String[itemDetails.size()];
		        String packing_uom[]=new String[itemDetails.size()];
		        
		        String discountamount[]=new String[itemDetails.size()];
		        String discountledger[]=new String[itemDetails.size()];
		        String price_based_on[]=new String[itemDetails.size()];
		        
		        double item_total=0.00;
		        for(int i=0;i<itemDetails.size();i++) 
		        {
		        	String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(itemDetails.get(i).getItem_code()).getItem_group();
					
		        	Item_group_master_sales_retacc itemgroup= item_group_master_sales_retaccRepository.getItem_group_master_sales_retacc(subgroup_items_code);

		        	item_name_ledger[i]=ledgermasterRepository.getLedgers(itemgroup.getItem_total()).getLedgername();
		        	item_name[i]=itemDetails.get(i).getItem_name();
		        	if(op.get().getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0)
					{
		        		double saleinvamount=sales_Invoice_Item_DtlsRepository.getSalesInvItmDtlsitem
		        				(return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id()).getReferance_id(),itemDetails.get(i).getItem_code(),itemDetails.get(i).getPacking())
		        				.getAmount();
		        		
		        		item_amount[i]= String.valueOf(String.format("%.2f",(saleinvamount- itemDetails.get(i).getAmount())));
		        		item_total+=Double.parseDouble(item_amount[i]);
		        		//String.format("%.2f", String.valueOf((saleinvamount- itemDetails.get(i).getAmount())))
		        	}
		        	else 
		        	{
		        		item_amount[i]=String.valueOf(itemDetails.get(i).getAmount());
		        		item_total+=itemDetails.get(i).getAmount();
		        	}
		        
		        	
		        	//element.setAmount((sales_Invoice_Item_DtlsRepository.getSalesInvItmDtlsitem(return_approval_noteRepository.getReturnApprovalDtls(cr.getReferance_id()).getReferance_id(),element.getItem_code(),element.getPacking()).getAmount()) - element.getAmount());
		        	
		        	
		        	
		        	item_rate[i]=String.valueOf(itemDetails.get(i).getPrice());
		        	item_passeditemqty[i]=String.valueOf(itemDetails.get(i).getQuantity());
		        	packing_qty[i]=String.valueOf((int)(itemDetails.get(i).getSquantity()));
		        	item_qty[i]=itemDetails.get(i).getUom();
		        	
		        	packing_uom[i]=item_masterRepository.getItemDetailsbyname(itemDetails.get(i).getPacking_name()).getMstock_unit_name();
		        	
		        	
		        	price_based_on[i]=itemDetails.get(i).getPrice_based_on();
		        	if(itemDetails.get(i).getDiscount_amt()>0) 
		        	{
		        		discountstat=true;
		        		//in future if bug comes 
		        		/*if(op.get().getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0)
						{
			        		double saleinvdiscamount=sales_Invoice_Item_DtlsRepository.getSalesInvItmDtlsitem
			        				(return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id()).getReferance_id(),itemDetails.get(i).getItem_code(),itemDetails.get(i).getPacking())
			        				.getDiscount_amt();
			        		
			        		discountamount[i]= String.valueOf(String.format("%.2f",(saleinvdiscamount- itemDetails.get(i).getDiscount_amt()));
			        		//String.format("%.2f", String.valueOf(saleinvdiscamount- itemDetails.get(i).getDiscount_amt()))
			        	}
			        	else 
			        	{
			        		discountamount[i]=String.valueOf(itemDetails.get(i).getDiscount_amt());
			        		
			        	}
		        		*/
		        		discountamount[i]=String.valueOf(itemDetails.get(i).getDiscount_amt());
		        		discountledger[i]=ledgermasterRepository.getLedgers(itemgroup.getDiscount()).getLedgername();
		        	}
		        
		        }
		      //item details ends here  
		        
		      
		        
		      
		  		
		  		
				//gst starts here 
				Credit_item_groupwise_taxsumm gst_details=sales_credit_noteRepository.getgstdetails(op.get().getCreditnoteid());
				boolean gst_valid=true,gststatus=true;
				
				String cgstledger="",sgstledger="",igstledger="";
				double cgstamount=0.00,sgstamount=0.00,igstamount=0.00;
				
				
	            /*Item_groupwise_taxsumm tax= item_groupwise_taxsummRepository.getItem_groupwise_taxsummbyinvoiceid(return_approval_noteRepository.getReturnApprovalDtls(cr.getReferance_id()).getReferance_id());
				
				element.setCgst(tax.getCgst() - element.getCgst());
				element.setSgst(tax.getSgst() - element.getSgst());
				element.setIgst(tax.getIgst() - element.getIgst());
				*/
				
				
				if(gst_details.getCgst() == 0.00 && gst_details.getSgst() == 0.00 && gst_details.getIgst() ==0.00) 
				{
					gst_valid=false;
				}
				else if( gst_details.getCgst() == 0.00 && gst_details.getSgst() == 0.00 && gst_details.getIgst() !=0.00 ) 
				{
					gststatus=false;
					igstledger=ledgermasterRepository.getLedgerDetails(gst_details.getIgstledgerid()).getLedgername();
					
					
					if(op.get().getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0)
					{
						Item_groupwise_taxsumm tax= item_groupwise_taxsummRepository.getItem_groupwise_taxsummbyinvoiceid(return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id()).getReferance_id());
						igstamount=tax.getIgst()-gst_details.getIgst();
					}
					else 
					{
						igstamount= gst_details.getIgst();
					}
					
					
				}
				else 
				{
					gststatus=true;
					cgstledger=ledgermasterRepository.getLedgerDetails(gst_details.getCgstledgerid()).getLedgername();
					sgstledger=ledgermasterRepository.getLedgerDetails(gst_details.getSgstledgerid()).getLedgername();
					
				
					if(op.get().getCreditnotetype().compareToIgnoreCase("Acceptance of Lower Rate")==0)
					{
						Item_groupwise_taxsumm tax= item_groupwise_taxsummRepository.getItem_groupwise_taxsummbyinvoiceid(return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id()).getReferance_id());
						cgstamount=tax.getCgst() - gst_details.getCgst();
						sgstamount=tax.getSgst() - gst_details.getSgst();
					}
					else 
					{
						cgstamount=gst_details.getCgst();
						sgstamount= gst_details.getSgst();
					}
					
				}
				
				//gst ends here 
				
				String brokercode=sales_credit_note_broker_dtlsRepository.getSales_credit_note_broker_dtlsposting(op.get().getCreditnoteid()).getBrokercode();
				String broker="";
				if(Utility.isNullOrEmpty(brokercode)) 
				{
					broker=broker_masterRepository.brokerNameByCode(brokercode).getName();
				}
				else 
				{
					broker="XXXXX";
				}

				String address_full=sales_credit_note_shipment_dtlsRepository.getSales_credit_note_shipment_dtls(op.get().getCreditnoteid()).getPaytodtls();
				
				String address_split[]=address_full.split(",");
				String address=address_split[0];
				//Delivery_challan delivery_challan=delivery_challanRepository.getDeliveryChallanDtls(op.get().getReference_id());
				Sales_return_note sale_return=sales_return_noteRepository.getSalesReturnNoteDtls(op.get().getReferance_id());
				
				//String deliverynotedate=sale_return.getSalesreturnnotedate().replaceAll("-", "");
				//String deliverynoteno=sale_return.getSalesreturnnoteno();
			
				
				String invoicenumber=op.get().getCreditnoteno();
				//gst ends here 
				String date=op.get().getCreditnotedate().replace("-", ""); 
				Sales_Invoice  salesincoice = new Sales_Invoice();
				String deliverynotedate="";
				String deliverynoteno="";
				
				if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection")==0)
				{
					Sales_return_note sale_return1=sales_return_noteRepository.getSalesReturnNoteDtls(op.get().getReferance_id());
					
					deliverynotedate=sale_return1.getSalesreturnnotedate().replaceAll("-", "");
					deliverynoteno=sale_return1.getSalesreturnnoteno();
					
					String salereturnid=op.get().getReferance_id();
					System.out.println("1 " + salereturnid);

					String returnappid=sales_return_noteRepository.getSalesRtnNoteUpdate(salereturnid).getReferance_id();
					System.out.println("2 " + returnappid);
					String saleinvoiceid=return_approval_noteRepository.getReturnApprovalDtls(returnappid).getReferance_id();
					System.out.println(" 3 " + saleinvoiceid);


					salesincoice= sales_InvoiceRepository.getSalesInvDetailsthroughdelivery(saleinvoiceid);  
					System.out.println();
				}
				else 
				{
					Return_approval_note returnap =return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id());
					
					deliverynotedate=returnap.getSalesreturndate().replaceAll("-", "");
					deliverynoteno=returnap.getSalesreturnno();
					
					
					String saleinvoiceid=return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id()).getReferance_id();
					System.out.println(" 3 " + saleinvoiceid);


					salesincoice= sales_InvoiceRepository.getSalesInvDetails(saleinvoiceid);  
					
				}




				String saleinvoiceno=salesincoice.getInvoice_no();
				String saleinvoicedate=salesincoice.getInvoice_date().replace("-", "");
				String output;
				Tallyrequest_Creditnote tally=new Tallyrequest_Creditnote();
				
				 String supplier_address=cust_bussiness_partner_addressRepository.custAddRetriveList(op.get().getParty()).getDistrict();
				 
				  //round off starts here  
		          Ledgermaster ledgerid=ledgermasterRepository.getLedgerDetails(op.get().getRoundoff_gl_ac());
		          int rounfoffdrcr=0;
		          //minus=cr,plus=dr
		          String roundoffglaccount =null;
		          double roundoff_amt=0;
		          if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection")==0)
				  {
		        	  roundoff_amt=op.get().getRoundoff_amt();
				  }
		          else 
		          {
		        	  
		        	  roundoff_amt=partyamount-item_total-cgstamount-sgstamount-igstamount;
		        	  roundoff_amt=Double.parseDouble(String.format("%.2f", roundoff_amt));
		          }
		      	  
		          if(roundoff_amt>0)//plus means cr
		          {
		          	rounfoffdrcr=1;//cr
		          	roundoffglaccount=ledgerid.getLedgername();
		          	
		          }
		          if(roundoff_amt<0)//minus meand debit 
		          {
		          	  rounfoffdrcr=-1;//dr
		              roundoffglaccount=ledgerid.getLedgername();
		            
		          }
		         
		  	
		  		//round off ends here 
				try  
				{
					
					if(partyname.contains("&"))
					{
						partyname=partyname.replaceAll("&","&amp;");
						
					}
					if(broker.contains("&"))
					{
						broker=broker.replaceAll("&","&amp;");
						
					}
					
					/*output = tally.SendToTally(partyname,trucknumber,creditnotedate,statename,partyamount,item_name_ledger,
							 item_name,item_amount,item_rate,item_passeditemqty,item_qty,item_total,roundoff_amt,
							 roundoffglaccount,rounfoffdrcr,gst_valid,gststatus,cgstledger,sgstledger,igstledger,
							 cgstamount,sgstamount,igstamount);
			        */
					output = tally.SendToTally(partyname,trucknumber,creditnotedate,statename,partyamount,item_name_ledger,
							 item_name,item_amount,item_rate,item_passeditemqty,item_qty,item_total,String.valueOf(roundoff_amt),
							 roundoffglaccount,rounfoffdrcr,gst_valid,gststatus,cgstledger,sgstledger,igstledger,
							 cgstamount,sgstamount,igstamount,   date,invoicenumber,discountamount,discountledger,
							 discountstat,broker,address,deliverynotedate,deliverynoteno,packing_qty,price_based_on
							 ,packing_uom,supplier_address,saleinvoiceno,saleinvoicedate);
			    
					
					System.out.println(" output :: "+output);
					String [] splitoutput = output.split("\\|\\|");
					System.out.println(splitoutput[0] +" / " + splitoutput[1]+"/"+id);
					
					sales_credit_noteRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
					
				}
				catch(Exception e)
				{
				
					System.out.println(e);
				}
				
			}
			
			
		
		
			Optional<Sales_credit_note> op1=this.sales_credit_noteRepository.findById(id);//static details 
			//System.out.println(op.get());
			
			return op1.get();
		}
		
		@Transactional
		public Sales_credit_note accountpostingCreditNoteold(long id) 
		{
			Optional<Sales_credit_note> op=this.sales_credit_noteRepository.findById(id);//static details 
			
			String partyname=op.get().getPartyname();
			String creditnotedate=op.get().getCreditnotedate();
			double partyamount=op.get().getPayable_amt();
			
			String trucknumber="";
			if(op.get().getChallan().compareToIgnoreCase("Single")==0)
			{
				if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection")==0) 
				{
					String returnnoteid=sales_return_noteRepository.getSalesReturnNoteDtls(op.get().getReferance_id()).getReferance_id();
					
					trucknumber=wm_unload_adviceRepository.getTallyvehicleno(returnnoteid).getVehicle_no();
				}
				else 
				{
					trucknumber=return_approval_noteRepository.getTallyvehicleno(op.get().getReferance_id()).getVehicleno();
				}
				
			}
			if(op.get().getChallan().compareToIgnoreCase("Multiple")==0)
			{
				String variousrefid[]=op.get().getReferance_id().split(",");
				
				if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection")==0) 
				{
					String returnnoteid=sales_return_noteRepository.getSalesReturnNoteDtls(variousrefid[0]).getReferance_id();
					
					trucknumber=wm_unload_adviceRepository.getTallyvehicleno(returnnoteid).getVehicle_no();
				}
				else 
				{
					trucknumber=return_approval_noteRepository.getTallyvehicleno(variousrefid[0]).getVehicleno();
				}
			}
			String statename=cust_bussiness_partnerRepository.gettallycreditnotestate(op.get().getParty()).getState();//party
		
		//item details starts here	
			List<Sales_credit_note_item_dtls> itemDetails=sales_credit_note_item_dtlsRepository.getSales_credit_note_item_dtls(op.get().getCreditnoteid());//item details
			
			String itemSubGroupName="";boolean discountstat=false;
			 String item_name_ledger[]=new String[itemDetails.size()];
	        String item_name[]=new String[itemDetails.size()];
	        String item_amount[]=new String[itemDetails.size()];
	        String item_rate[]=new String[itemDetails.size()];
	        String item_passeditemqty[]=new String[itemDetails.size()];
	        String packing_qty[]=new String[itemDetails.size()];
	        String item_qty[]=new String[itemDetails.size()];
	        String packing_uom[]=new String[itemDetails.size()];
	        
	        String discountamount[]=new String[itemDetails.size()];
	        String discountledger[]=new String[itemDetails.size()];
	        String price_based_on[]=new String[itemDetails.size()];
	        
	        double item_total=0.00;
	        for(int i=0;i<itemDetails.size();i++) 
	        {
	        	String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(itemDetails.get(i).getItem_code()).getItem_group();
				
	        	Item_group_master_sales_retacc itemgroup= item_group_master_sales_retaccRepository.getItem_group_master_sales_retacc(subgroup_items_code);

	        	item_name_ledger[i]=ledgermasterRepository.getLedgers(itemgroup.getItem_total()).getLedgername();
	        	item_name[i]=itemDetails.get(i).getItem_name();
	        	item_amount[i]=String.valueOf(itemDetails.get(i).getAmount());
	        	item_rate[i]=String.valueOf(itemDetails.get(i).getPrice());
	        	item_passeditemqty[i]=String.valueOf(itemDetails.get(i).getQuantity());
	        	packing_qty[i]=String.valueOf((int)(itemDetails.get(i).getSquantity()));
	        	item_qty[i]=itemDetails.get(i).getUom();
	        	
	        	packing_uom[i]=item_masterRepository.getItemDetailsbyname(itemDetails.get(i).getPacking_name()).getMstock_unit_name();
	        	
	        	item_total+=itemDetails.get(i).getAmount();
	        	price_based_on[i]=itemDetails.get(i).getPrice_based_on();
	        	if(itemDetails.get(i).getDiscount_amt()>0) 
	        	{
	        		discountstat=true;
	        		discountamount[i]=String.valueOf(itemDetails.get(i).getDiscount_amt());
	        		discountledger[i]=ledgermasterRepository.getLedgers(itemgroup.getDiscount()).getLedgername();
	        	}
	        
	        }
	      //item details ends here  
	        
	      //round off starts here  
	        
	        //round off starts here  
	          Ledgermaster ledgerid=ledgermasterRepository.getLedgerDetails(op.get().getRoundoff_gl_ac());
	          int rounfoffdrcr=0;
	          //minus=cr,plus=dr
	          String roundoffglaccount =null;
	      	double roundoff_amt=op.get().getRoundoff_amt();
	         // double roundoff_amt=0.00;
	          if(op.get().getRoundoff_amt()>0)//plus means cr
	          {
	          	rounfoffdrcr=1;//cr
	          	roundoffglaccount=ledgerid.getLedgername();
	          	
	          }
	          if(op.get().getRoundoff_amt()<0)//minus meand debit 
	          {
	          	  rounfoffdrcr=-1;//dr
	              roundoffglaccount=ledgerid.getLedgername();
	            
	          }
	         
	  	
	  		//round off ends here 
	  		
	  		
			//gst starts here 
			Credit_item_groupwise_taxsumm gst_details=sales_credit_noteRepository.getgstdetails(op.get().getCreditnoteid());
			boolean gst_valid=true,gststatus=true;
			
			String cgstledger="",sgstledger="",igstledger="";
			double cgstamount=0.00,sgstamount=0.00,igstamount=0.00;
			
			if(gst_details.getCgst() == 0.00 && gst_details.getSgst() == 0.00 && gst_details.getIgst() ==0.00) 
			{
				gst_valid=false;
			}
			else if( gst_details.getCgst() == 0.00 && gst_details.getSgst() == 0.00 && gst_details.getIgst() !=0.00 ) 
			{
				gststatus=false;
				igstledger=ledgermasterRepository.getLedgerDetails(gst_details.getIgstledgerid()).getLedgername();
				
				
				igstamount= gst_details.getIgst();
			}
			else 
			{
				gststatus=true;
				
				
				cgstledger=ledgermasterRepository.getLedgerDetails(gst_details.getCgstledgerid()).getLedgername();
				sgstledger=ledgermasterRepository.getLedgerDetails(gst_details.getSgstledgerid()).getLedgername();
				
				cgstamount=gst_details.getCgst();
				sgstamount= gst_details.getSgst();
			}
			
			//gst ends here 
			
			String brokercode=sales_credit_note_broker_dtlsRepository.getSales_credit_note_broker_dtlsposting(op.get().getCreditnoteid()).getBrokercode();
			String broker="";
			if(Utility.isNullOrEmpty(brokercode)) 
			{
				broker=broker_masterRepository.brokerNameByCode(brokercode).getName();
			}
			else 
			{
				broker="XXXXX";
			}

			String address_full=sales_credit_note_shipment_dtlsRepository.getSales_credit_note_shipment_dtls(op.get().getCreditnoteid()).getPaytodtls();
			
			String address_split[]=address_full.split(",");
			String address=address_split[0];
			//Delivery_challan delivery_challan=delivery_challanRepository.getDeliveryChallanDtls(op.get().getReference_id());
			Sales_return_note sale_return=sales_return_noteRepository.getSalesReturnNoteDtls(op.get().getReferance_id());
			
			//String deliverynotedate=sale_return.getSalesreturnnotedate().replaceAll("-", "");
			//String deliverynoteno=sale_return.getSalesreturnnoteno();
		
			
			String invoicenumber=op.get().getCreditnoteno();
			//gst ends here 
			String date=op.get().getCreditnotedate().replace("-", ""); 
			Sales_Invoice  salesincoice = new Sales_Invoice();
			String deliverynotedate="";
			String deliverynoteno="";
			
			if(op.get().getCreditnotetype().compareToIgnoreCase("Goods Return Due To Rejection")==0)
			{
				Sales_return_note sale_return1=sales_return_noteRepository.getSalesReturnNoteDtls(op.get().getReferance_id());
				
				deliverynotedate=sale_return1.getSalesreturnnotedate().replaceAll("-", "");
				deliverynoteno=sale_return1.getSalesreturnnoteno();
				
				String salereturnid=op.get().getReferance_id();
				System.out.println("1 " + salereturnid);

				String returnappid=sales_return_noteRepository.getSalesRtnNoteUpdate(salereturnid).getReferance_id();
				System.out.println("2 " + returnappid);
				String saleinvoiceid=return_approval_noteRepository.getReturnApprovalDtls(returnappid).getReferance_id();
				System.out.println(" 3 " + saleinvoiceid);


				salesincoice= sales_InvoiceRepository.getSalesInvDetailsthroughdelivery(saleinvoiceid);  
				System.out.println();
			}
			else 
			{
				Return_approval_note returnap =return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id());
				
				deliverynotedate=returnap.getSalesreturndate().replaceAll("-", "");
				deliverynoteno=returnap.getSalesreturnno();
				
				
				String saleinvoiceid=return_approval_noteRepository.getReturnApprovalDtls(op.get().getReferance_id()).getReferance_id();
				System.out.println(" 3 " + saleinvoiceid);


				salesincoice= sales_InvoiceRepository.getSalesInvDetails(saleinvoiceid);  
				
			}




			String saleinvoiceno=salesincoice.getInvoice_no();
			String saleinvoicedate=salesincoice.getInvoice_date().replace("-", "");
			String output;
			Tallyrequest_Creditnote tally=new Tallyrequest_Creditnote();
			
			 String supplier_address=cust_bussiness_partner_addressRepository.custAddRetriveList(op.get().getParty()).getDistrict();
			try  
			{
				
				if(partyname.contains("&"))
				{
					partyname=partyname.replaceAll("&","&amp;");
					
				}
				
				/*output = tally.SendToTally(partyname,trucknumber,creditnotedate,statename,partyamount,item_name_ledger,
						 item_name,item_amount,item_rate,item_passeditemqty,item_qty,item_total,roundoff_amt,
						 roundoffglaccount,rounfoffdrcr,gst_valid,gststatus,cgstledger,sgstledger,igstledger,
						 cgstamount,sgstamount,igstamount);
		        */
				output = tally.SendToTally(partyname,trucknumber,creditnotedate,statename,partyamount,item_name_ledger,
						 item_name,item_amount,item_rate,item_passeditemqty,item_qty,item_total,String.valueOf(roundoff_amt),
						 roundoffglaccount,rounfoffdrcr,gst_valid,gststatus,cgstledger,sgstledger,igstledger,
						 cgstamount,sgstamount,igstamount,   date,invoicenumber,discountamount,discountledger,
						 discountstat,broker,address,deliverynotedate,deliverynoteno,packing_qty,price_based_on
						 ,packing_uom,supplier_address,saleinvoiceno,saleinvoicedate);
		    
				
				System.out.println(" output :: "+output);
				String [] splitoutput = output.split("\\|\\|");
				System.out.println(splitoutput[0] +" / " + splitoutput[1]+"/"+id);
				
				sales_credit_noteRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
				
			}
			catch(Exception e)
			{
			
				System.out.println(e);
			}
			
		
		
			Optional<Sales_credit_note> op1=this.sales_credit_noteRepository.findById(id);//static details 
			//System.out.println(op.get());
			
			return op1.get();
		}
		@Transactional
		public Sales_credit_note accountpostingundo(long id)
		{
			
			Optional<Sales_credit_note> op=this.sales_credit_noteRepository.findById(id);
			LocalDateTime ldt = LocalDateTime.now();
			sales_credit_noteRepository.exportuomtallyReverse(id,op.get().getInserted_by(),ldt);
			
			
			Optional<Sales_credit_note> op1=sales_credit_noteRepository.findById(id);
			return op1.get();
		}
		
		
		public List<Map<String, Object>> geteinvoicestatus_creditnote(long id,String invoiceno)
		{
			if(id>0)
			{
				return sales_credit_noteRepository.geteinvoicestatus_saleinvupdate(invoiceno);
			}
			else
			{
				return sales_credit_noteRepository.geteinvoicestatus_saleinv();
			}
			
		}
		
		public List<Map<String, Object>> getSalesCreditNoteFast(String compid)
		{
			return sales_credit_noteRepository.getSalesCreditNoteFast(compid);
		}
		
		public List<Map<String, Object>> getSalesCreditNoteReport(String fromdate,String todate,String invoicetype)
		{
			List<Map<String,Object>> salesCreditnoteList=new ArrayList <>();
			
			if(invoicetype.compareToIgnoreCase("INV00003")==0) 
			{
				//System.out.println("if part");
				salesCreditnoteList.addAll(sales_credit_noteRepository.getSalesCreditNoteJobWorkReport(fromdate,todate,invoicetype));
			}
			else
			{
				//System.out.println("else part");
				salesCreditnoteList.addAll(sales_credit_noteRepository.getSalesCreditNoteReport(fromdate,todate,invoicetype));
			}
			
			return salesCreditnoteList;
		}
		
		
		public Map<String,Object> creditnoteeinvoicedetails(String creditnoteid)
		 {
			return sale_credit_note_einvoice_genRepository.creditnoteeinvoicedetails(creditnoteid);
        }
		
		public List<Map<String,Object>> searchSalesCreditNote(String orderno,String fromdate, String todate,String party1,String finyear)
		{
			List<Map<String,Object>> searchSalesCreditNote =new ArrayList<Map<String,Object>>();
			String tablename="sales_credit_note",party_name="party",order_no="creditnoteno",order_date="creditnotedate";
			searchSalesCreditNote.addAll(sales_InvoiceRepository.getsearchdataFast(tablename,party_name,order_no,order_date,orderno,fromdate,todate,party1,finyear));
			return searchSalesCreditNote;
		}
		
}
