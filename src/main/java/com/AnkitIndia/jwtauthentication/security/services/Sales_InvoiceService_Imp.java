package com.AnkitIndia.jwtauthentication.security.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.Exporttotally.Tallyrequest_PurchaseBill;
import com.AnkitIndia.Exporttotally.Tallyrequest_SalesInvoice;
import com.AnkitIndia.Exporttotally.Tallyrequest_SalesInvoiceJobwork;
import com.AnkitIndia.Utility.FileUtil;
import com.AnkitIndia.Utility.NumToWord;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Account_details;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_statutory;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Invoice_accountdetails;
import com.AnkitIndia.jwtauthentication.model.Item_Service_master;
import com.AnkitIndia.jwtauthentication.model.Item_group_jobwork_sales_acc;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_sales_acc;
import com.AnkitIndia.jwtauthentication.model.Item_groupwise_hsnsumm;
import com.AnkitIndia.jwtauthentication.model.Item_groupwise_summ;
import com.AnkitIndia.jwtauthentication.model.Item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Ledgermaster;
import com.AnkitIndia.jwtauthentication.model.Process_master_doc;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill_Item_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Sale_invoice_einvoice_gen;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_BP_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Broker_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Docs;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls_for_jobwork_price;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Payment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Shipment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Tax_Info;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Trans_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Item_Dtls_for_jobwork_price;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Summary_dyn;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_doc;
import com.AnkitIndia.jwtauthentication.repository.Account_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_addressRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_brokerRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_statutoryRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challanRepository;
import com.AnkitIndia.jwtauthentication.repository.Invoice_accountdetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Invoice_typeRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_Service_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_jobwork_sales_accRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_master_sales_accRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_master_stk_trans_saleRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_groupwise_hsnsummRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_groupwise_summRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_groupwise_taxsummRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Journal_accounttransactionRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.OutstandingledgerRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receiptRepository;
import com.AnkitIndia.jwtauthentication.repository.Receipt_accounttransactionRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Sale_invoice_einvoice_genRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_InvoiceRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Invoice_BP_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Invoice_Broker_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Invoice_DocsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Invoice_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Invoice_Item_Dtls_for_jobwork_priceRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Invoice_Payment_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Invoice_Shipment_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Invoice_Tax_InfoRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Invoice_Trans_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.SubgroupmasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Tax_code_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Control_account_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Delivery_challan_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.ReceiptAccDetails;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challanDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_InvoiceDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_payment_detailsDTO;

import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_BP_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Broker_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Payment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Tax_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Trans_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class Sales_InvoiceService_Imp implements Sales_InvoiceService{
	
	@Autowired
	Sales_InvoiceRepository sales_InvoiceRepository;
	
	@Autowired
	Sales_Invoice_Trans_DtlsRepository sales_Invoice_Trans_DtlsRepository;
	
	@Autowired
	Sales_Invoice_Shipment_DtlsRepository sales_Invoice_Shipment_DtlsRepository;
	
	@Autowired
	Sales_Invoice_Payment_DtlsRepository sales_Invoice_Payment_DtlsRepository;
	
	@Autowired
	Invoice_typeRepository invoice_typeRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	Sales_Invoice_Item_DtlsRepository sales_Invoice_Item_DtlsRepository;
	
	@Autowired
	Sales_Invoice_Broker_DtlsRepository sales_Invoice_Broker_DtlsRepository;
	
	@Autowired
	Sales_Invoice_DocsRepository sales_Invoice_DocsRepository;
	
	@Autowired
	Sales_Invoice_Tax_InfoRepository sales_Invoice_Tax_InfoRepository;
	 
	@Autowired
	Sales_Invoice_BP_DtlsRepository sales_Invoice_BP_DtlsRepository;
	
	@Autowired
	Delivery_challanRepository delivery_challanRepository;
	
	@Autowired
	Receipt_accounttransactionRepository receipt_accounttransactionRepository;
	
	@Autowired
	Journal_accounttransactionRepository journal_accounttransactionRepository;
	
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
	Item_groupwise_taxsummRepository item_groupwise_taxsummRepository;
	
	@Autowired
	Item_groupwise_hsnsummRepository item_groupwise_hsnsummRepository;
	
	@Autowired
	Item_groupwise_summRepository item_groupwise_summRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Wm_loading_adviceRepository wm_loading_adviceRepository;
	
	@Autowired
	Sales_OrderRepository sales_OrderRepository;
	
	@Autowired
	Return_approval_noteRepository return_approval_noteRepository;
	
	@Autowired
	Item_group_master_sales_accRepository item_group_master_sales_accRepository;
	
	
	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	Item_group_masterRepository item_group_masterRepository;
	
	@Autowired
	Tax_code_detailsRepository tax_code_detailsRepository;
	
	@Autowired
	Cust_bussiness_partner_addressRepository cust_bussiness_partner_addressRepository;	
	
	@Autowired
	Cust_bussiness_partner_brokerRepository cust_bussiness_partner_brokerRepository;
	
	@Autowired
	Trans_bussiness_partnerRepository trans_bussiness_partnerRepository;
	
	@Autowired
	Cust_bussiness_partner_statutoryRepository cust_bussiness_partner_statutoryRepository;
	
	@Autowired
	Item_Service_masterRepository item_Service_masterRepository;
	
	@Autowired
	Sales_Invoice_Item_Dtls_for_jobwork_priceRepository sales_Invoice_Item_Dtls_for_jobwork_priceRepository;
	
	@Autowired
	Item_group_jobwork_sales_accRepository item_group_jobwork_sales_accRepository;
	
	@Autowired
	Sale_invoice_einvoice_genRepository sale_invoice_einvoice_genRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	Pur_good_receiptRepository pur_good_receiptRepository;
	
	static String transtype="Sales Invoice";
	
	public SalesSequenceIdDTO getSISequenceId(String fin_year,String inv_type)
	{
		String prefix="";
		String prefix1="";
		//String prefix=invoice_typeRepository.getSalesInvTypesDtls(inv_type).getInvtype_prefix();
		int slno=0;
		String gen_sno="";
		if(companyMasterRepository.getCompanyName().compareToIgnoreCase("ANKIT INDIA LIMITED")==0)
		{
		
			gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
            String sno=sales_InvoiceRepository.countSalesInvoice(fin_year,inv_type);
			
			if(sno != null ) {
				slno=Integer.parseInt(sno);
			}
			
			
			String fin_yearspit[]=fin_year.split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			//prefix=prefix+"-"+fin_year+"-";
			//prefix=prefix+"-"+final_fyear+"-";
			prefix1=invoice_typeRepository.getSalesInvTypesDtls(inv_type).getInvtype_prefix();
			//prefix=prefix+"-"+fin_year+"-";
			if(inv_type.compareToIgnoreCase("INV00001") == 0)
			{
				sno=sales_InvoiceRepository.countSalesInvoiceNotDefenceReg(fin_year,inv_type);
				
				if(sno != null ) {
					slno=Integer.parseInt(sno);
				}
				
				prefix="AILP"+"/"+final_fyear+"/TW";
			}
			else
			{
				prefix="AILP"+"/"+final_fyear+"/"+prefix1;
			}
			//System.err.println(prefix);
			
			gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		}
		else 
		{
			prefix=invoice_typeRepository.getSalesInvTypesDtls(inv_type).getInvtype_prefix();
			
			String sno=sales_InvoiceRepository.countSalesInvoice(fin_year,inv_type);
			
			if(sno != null ) {
				slno=Integer.parseInt(sno);
			}
			
			
			String fin_yearspit[]=fin_year.split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			//prefix=prefix+"-"+fin_year+"-";
			prefix=prefix+"-"+final_fyear+"-";
			//System.err.println(prefix);
			
			//String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
			 //String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
			gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		}
		
		Type listType = new TypeToken<SalesSequenceIdDTO>() {}.getType();
		SalesSequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public SalesSequenceIdDTO getSISequenceIdforDefence(String fin_year,String inv_type,String custid)
	{
		String prefix="";
		String prefix1="";
		//String prefix=invoice_typeRepository.getSalesInvTypesDtls(inv_type).getInvtype_prefix();
		int slno=0;
		String gen_sno="";
		if(companyMasterRepository.getCompanyName().compareToIgnoreCase("ANKIT INDIA LIMITED")==0)
		{
			String sno=sales_InvoiceRepository.countSalesInvoice(fin_year,inv_type);
			System.out.println("SI normal Serial:: "+sno);
			if(sno != null ) {
				slno=Integer.parseInt(sno);
			}
			
			String fin_yearspit[]=fin_year.split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			prefix1=invoice_typeRepository.getSalesInvTypesDtls(inv_type).getInvtype_prefix();
			String cust_group = cust_bussiness_partnerRepository.getCustomer(custid).getGroup_type();
			System.out.println("DC Defence Group:: "+cust_group);
			//prefix=prefix+"-"+fin_year+"-";
			if(inv_type.compareToIgnoreCase("INV00001") == 0 && cust_group.compareToIgnoreCase("CG00019") == 0) {
				sno=sales_InvoiceRepository.countSalesInvoice4Defence(fin_year);
				System.out.println("DC Defence sno:: "+sno);
				if(sno != null ) {
					slno=Integer.parseInt(sno);
				}
				prefix="AILP"+"/"+final_fyear+"/DF";
			}
			else if(inv_type.compareToIgnoreCase("INV00001") == 0)
			{
				sno=sales_InvoiceRepository.countSalesInvoiceNotDefenceReg(fin_year,inv_type);
				
				if(sno != null ) {
					slno=Integer.parseInt(sno);
				}
				
				prefix="AILP"+"/"+final_fyear+"/TW";
			}
			else
			{
				prefix="AILP"+"/"+final_fyear+"/"+prefix1;
			}
			
			//System.err.println(prefix);
			gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
			
			// Commented on 1104 for army
			/*gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
            String sno=sales_InvoiceRepository.countSalesInvoice(fin_year,inv_type);
			
			if(sno != null ) {
				slno=Integer.parseInt(sno);
			}
			
			
			String fin_yearspit[]=fin_year.split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			//prefix=prefix+"-"+fin_year+"-";
			//prefix=prefix+"-"+final_fyear+"-";
			prefix1=invoice_typeRepository.getSalesInvTypesDtls(inv_type).getInvtype_prefix();
			//prefix=prefix+"-"+fin_year+"-";
			if(inv_type.compareToIgnoreCase("INV00001") == 0)
			{
				prefix="AILP"+"/"+final_fyear+"/TW";
			}
			else
			{
				prefix="AILP"+"/"+final_fyear+"/"+prefix1;
			}
			//System.err.println(prefix);
			
			gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);*/
		}
		else 
		{
			prefix=invoice_typeRepository.getSalesInvTypesDtls(inv_type).getInvtype_prefix();
			
			String sno=sales_InvoiceRepository.countSalesInvoice(fin_year,inv_type);
			
			if(sno != null ) {
				slno=Integer.parseInt(sno);
			}
			
			
			String fin_yearspit[]=fin_year.split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			//prefix=prefix+"-"+fin_year+"-";
			prefix=prefix+"-"+final_fyear+"-";
			//System.err.println(prefix);
			
			//String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
			 //String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
			gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		}
		
		Type listType = new TypeToken<SalesSequenceIdDTO>() {}.getType();
		SalesSequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public SalesSequenceIdDTO getReceiptSequenceId(String tdate)
	{
		int slno=0;
		String sno=journal_accounttransactionRepository.countJournalAcc(tdate);
		if(sno != null ) {
			slno=Integer.parseInt(sno);
			slno++;
		}else {slno=1;}
		String gen_sno=String.valueOf(slno);
		
		Type listType = new TypeToken<SalesSequenceIdDTO>() {}.getType();
		SalesSequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Sales_Invoice save(Sales_Invoice sinvoice,MultipartFile files[]) throws IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		String dynsubgrp="",ledid="",unitname="";
		long slno=0;String prefix="SI";
		
		if(sales_InvoiceRepository.countId(prefix) != null ) {
			slno=Long.parseLong(sales_InvoiceRepository.countId(prefix));
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		// String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		//HAJIPUR

		sinvoice.setInvoice_id(gen_sno);	
		
		/*Start checking transaction no for unique */
		//System.err.println("First:>>"+sinvoice.getInvoice_no());
		long nslno=0;
		
		String gen_tslno="";
		
		if(companyMasterRepository.getCompanyName().compareToIgnoreCase("ANKIT INDIA LIMITED")==0)
		{
			if(cust_bussiness_partnerRepository.getCustomer(sinvoice.getParty()).getGroup_type().compareToIgnoreCase("CG00019")==0) {
				String tsno=sales_InvoiceRepository.countSalesInvoice4Defence(sinvoice.getFin_year());
				System.out.println("SI Defence Serial Save:: "+tsno);
				if(tsno != null ) {
					nslno=Integer.parseInt(tsno);
				}
				
				String fin_yearspit[]=sinvoice.getFin_year().split("-");
				String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
				String tprefix="";
				String prefix1=invoice_typeRepository.getSalesInvTypesDtls(sinvoice.getInvoice_type()).getInvtype_prefix();
				String cust_group = cust_bussiness_partnerRepository.getCustomer(sinvoice.getParty()).getGroup_type();
				System.out.println("SI Defence Group:: "+cust_group);
				//prefix=prefix+"-"+fin_year+"-";
				if(sinvoice.getInvoice_type().compareToIgnoreCase("INV00001") == 0 && cust_group.compareToIgnoreCase("CG00019") == 0) {
					tprefix="AILP"+"/"+final_fyear+"/DF";
				}
				else if(sinvoice.getInvoice_type().compareToIgnoreCase("INV00001") == 0)
				{
					tprefix="AILP"+"/"+final_fyear+"/TW";
				}
				else
				{
					tprefix="AILP"+"/"+final_fyear+"/"+prefix1;
				}
				
				//System.err.println(prefix);
				gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
			}
			else {
				String tsno=sales_InvoiceRepository.countSalesInvoice(sinvoice.getFin_year(),sinvoice.getInvoice_type());
				
				if(tsno != null ) {
					nslno=Integer.parseInt(tsno);
				}
				
				String fin_yearspit[]=sinvoice.getFin_year().split("-");
				String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
				
				//tprefix=tprefix+"-"+sinvoice.getFin_year()+"-";
				//String tprefix="AILP"+"/"+final_fyear+"/TW";
				String tprefix="";
				String prefix1=invoice_typeRepository.getSalesInvTypesDtls(sinvoice.getInvoice_type()).getInvtype_prefix();
				//prefix=prefix+"-"+fin_year+"-";
				if(sinvoice.getInvoice_type().compareToIgnoreCase("INV00001") == 0)
				{
					tsno=sales_InvoiceRepository.countSalesInvoiceNotDefenceReg(sinvoice.getFin_year(),sinvoice.getInvoice_type());
					
					if(tsno != null ) {
						nslno=Integer.parseInt(tsno);
					}
					
					tprefix="AILP"+"/"+final_fyear+"/TW";
				}
				else
				{
					tprefix="AILP"+"/"+final_fyear+"/"+prefix1;
				}
			
				gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
			}
			// Commented on 1104 for army
			/*//String tprefix=invoice_typeRepository.getSalesInvTypesDtls(sinvoice.getInvoice_type()).getInvtype_prefix();
			String tsno=sales_InvoiceRepository.countSalesInvoice(sinvoice.getFin_year(),sinvoice.getInvoice_type());
					
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			
			String fin_yearspit[]=sinvoice.getFin_year().split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			//tprefix=tprefix+"-"+sinvoice.getFin_year()+"-";
			//String tprefix="AILP"+"/"+final_fyear+"/TW";
			String tprefix="";
			String prefix1=invoice_typeRepository.getSalesInvTypesDtls(sinvoice.getInvoice_type()).getInvtype_prefix();
			//prefix=prefix+"-"+fin_year+"-";
			if(sinvoice.getInvoice_type().compareToIgnoreCase("INV00001") == 0)
			{
				tprefix="AILP"+"/"+final_fyear+"/TW";
			}
			else
			{
				tprefix="AILP"+"/"+final_fyear+"/"+prefix1;
			}
		
			gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);*/
		}
		else
		{
			String tprefix=invoice_typeRepository.getSalesInvTypesDtls(sinvoice.getInvoice_type()).getInvtype_prefix();
			String tsno=sales_InvoiceRepository.countSalesInvoice(sinvoice.getFin_year(),sinvoice.getInvoice_type());
					
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			
			String fin_yearspit[]=sinvoice.getFin_year().split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			tprefix=tprefix+"-"+final_fyear+"-";
			gen_tslno=UniqueIDTransaction.uniqueId6(tprefix,nslno);
		}
		
		sinvoice.setInvoice_no(gen_tslno);
		
		if(Utility.isNullOrEmpty(sinvoice.getItem_total_gl_ac())) {
			
		}else {sinvoice.setItem_total_gl_ac("0");}
		if(Utility.isNullOrEmpty(sinvoice.getNet_total_gl_ac())) {
			
		}else {sinvoice.setNet_total_gl_ac("0");}
		if(Utility.isNullOrEmpty(sinvoice.getApplicable_gl_ac())) {
			
		}else {sinvoice.setApplicable_gl_ac("0");}
		if(Utility.isNullOrEmpty(sinvoice.getDiscount_gl_ac())) {
			
		}else {sinvoice.setDiscount_gl_ac("0");}
		if(Utility.isNullOrEmpty(sinvoice.getFinal_bill_amt_gl_ac())) {
			
		}else {sinvoice.setFinal_bill_amt_gl_ac("0");}
		
		//item_total_gl_ac='0',net_total_gl_ac='0',applicable_gl_ac='0',discount_gl_ac='0',
				//final_bill_amt_gl_ac='0',payable_amt_gl_ac='0',roundoff_gl_ac='IB00001',
				//tax_total_gl_ac='0',total_bill_amt_gl_ac='0'
		
		
		if(Utility.isNullOrEmpty(sinvoice.getPayable_amt_gl_ac())) {
			
		}else {sinvoice.setPayable_amt_gl_ac("0");}
		if(Utility.isNullOrEmpty(sinvoice.getTax_total_gl_ac())) {
			
		}else {sinvoice.setTax_total_gl_ac("0");}
		if(Utility.isNullOrEmpty(sinvoice.getTotal_bill_amt_gl_ac())) {
			
		}else {sinvoice.setTotal_bill_amt_gl_ac("0");}
		
		
		
		sinvoice.setModified_type("INSERTED");
		sinvoice.setInserted_by(userRepository.getUserDetails(sinvoice.getUsername()).getName());
		sinvoice.setInserted_on(ldt);
		sinvoice.setUpdated_by("NA");
		sinvoice.setUpdated_on(ldt);
		sinvoice.setDeleted_by("NA");
		sinvoice.setDeleted_on(ldt);
		sinvoice.setPayment_status(false);
		sinvoice.setSales_returnstatus("NO");
		sinvoice.setSalereturn_id("NA");
		sinvoice.setInvoicedate(sinvoice.getInvoice_date());
		sinvoice.setInvoiceno(sinvoice.getInvoice_no());
		sinvoice.setState(cust_bussiness_partnerRepository.gettallycreditnotestate(sinvoice.getParty()).getState());
		sinvoice.setReturn_approval_status("NO");
		
		// GST STATUS Wheather PARTY Resgistered or Not
		if(Utility.isNullOrEmpty(cust_bussiness_partnerRepository.getInvCustBPGstStatus(sinvoice.getParty())))		
		{
			sinvoice.setGststatus("YES"); 
		}		
		else
		{
			sinvoice.setGststatus("NO");
		}
		
	//here advice no added in sales invoice through delivery challan 
		if(sinvoice.getChallan().compareToIgnoreCase("Single")==0) 
		{
			String reftype=delivery_challanRepository.getDeliveryChallanDtls(sinvoice.getReference_id()).getRef_type(); //Bypass for GRN to direct Delivery Challan
			if(reftype.compareTo("Loading Advice")==0) {
				String advice_id=delivery_challanRepository.getDeliveryChallanDtls(sinvoice.getReference_id()).getReferance_id();
				sinvoice.setAdviceno(wm_loading_adviceRepository.getLoadingStk(advice_id).getAdvice_no());
			}else {sinvoice.setAdviceno("NA");}
			
		}
		else 
		{
			
			String alldeliverychallan[]=sinvoice.getReference_id().split(",");
			List<String> deliverychallan = new ArrayList<String>();
			for(int i=0;i<alldeliverychallan.length;i++) 
			{
				deliverychallan.add( delivery_challanRepository.getDeliveryChallanDtls(alldeliverychallan[i]).getReferance_id());
			}
			
			
			String advicenumbers="";
			for(int v=0;v<deliverychallan.size();v++) 
			{
				advicenumbers=advicenumbers+wm_loading_adviceRepository.getLoadingStk(deliverychallan.get(v)).getAdvice_no()+",";
			}
			
			advicenumbers=advicenumbers.substring(0,advicenumbers.length()-1);
			
			sinvoice.setAdviceno(advicenumbers);
		}
	
		
	//here ends 	
		
		//convert code to name
		sinvoice.setInvoice_type_name(invoice_typeRepository.getSalesInvTypesDtls(sinvoice.getInvoice_type()).getInvtype_name());
		sinvoice.setBusinessunitname(companyBusinessUnitMasterRepository.businessunitbyid(sinvoice.getBusiness_unit()).getBusinessunit_name());
		
		//ends here 
		if(Utility.isNullOrEmpty(sinvoice.getParty())) {
			sinvoice.setPartyname(cust_bussiness_partnerRepository.getCustomer(sinvoice.getParty()).getCp_name());
		}else {sinvoice.setPartyname("None");}
		
		if(Utility.isNullOrEmpty(sinvoice.getBusiness_unit())) {
			unitname=companyBusinessUnitMasterRepository.CompanyBUAddress(sinvoice.getBusiness_unit()).getBusinessunit_name();
		}else {unitname="None";}
		
		if(Utility.isNullOrEmpty(sinvoice.getRoundoff_gl_ac())) {
			
		}
		else 
		{
			sinvoice.setRoundoff_gl_ac("0");
		}
		/********************************* Filtering Journal Account **********************************/
		// stopping for ledger not entered on 02062022 by Bidhan
		/*Set<Item_groupwise_summ> iGrpSumm=new HashSet<Item_groupwise_summ>();
		iGrpSumm.addAll(sinvoice.getItem_groupwise_summ());
		Set<ReceiptAccDetails> receiptAccs=new HashSet<ReceiptAccDetails>();
		
		//For Item Ledger
		iGrpSumm.forEach((igSumm) -> {
			if(igSumm.getItem_total() > 0) {
				ReceiptAccDetails rAccDetails=new ReceiptAccDetails(igSumm.getItem_ledger(),(igSumm.getItem_total()*-1));
				receiptAccs.add(rAccDetails);
			}
		});
		//For Dicount Ledger
		iGrpSumm.forEach((igSumm) -> {
			if(igSumm.getDiscount_amt() > 0) {
				ReceiptAccDetails rAccDetails=new ReceiptAccDetails(igSumm.getDiscount_ledger(),igSumm.getDiscount_amt());
				receiptAccs.add(rAccDetails);
			}
		});
		
		Set<Item_groupwise_taxsumm> iGrpTaxSumm=new HashSet<Item_groupwise_taxsumm>();
		iGrpTaxSumm.addAll(sinvoice.getItem_groupwise_taxsumm());
		Set<ReceiptAccDetails> rcptTaxAcc=new HashSet<ReceiptAccDetails>();
		
		//For CGST Ledger
		iGrpTaxSumm.forEach((igtSumm) -> {
			if(igtSumm.getCgst() > 0) {
				ReceiptAccDetails rAccDetails=new ReceiptAccDetails(igtSumm.getCgstledgerid(),(igtSumm.getCgst()*-1));
				rcptTaxAcc.add(rAccDetails);
			}
		});
		//For SGST Ledger
		iGrpTaxSumm.forEach((igtSumm) -> {
			if(igtSumm.getSgst() > 0) {
				ReceiptAccDetails rAccDetails=new ReceiptAccDetails(igtSumm.getSgstledgerid(),(igtSumm.getSgst()*-1));
				rcptTaxAcc.add(rAccDetails);
			}
		});
		//For IGST Ledger
		iGrpTaxSumm.forEach((igtSumm) -> {
			if(igtSumm.getIgst() > 0) {
				ReceiptAccDetails rAccDetails=new ReceiptAccDetails(igtSumm.getIgstledgerid(),(igtSumm.getIgst()*-1));
				rcptTaxAcc.add(rAccDetails);
			}
		});
		//Round Off Ledger
		System.err.println("Round Off Value "+sinvoice.getRoundoff_amt());
		if(sinvoice.getRoundoff_amt() != 0) {
			ReceiptAccDetails rAccDetails=new ReceiptAccDetails(sinvoice.getRoundoff_gl_ac(),(sinvoice.getRoundoff_amt()*-1));
			rcptTaxAcc.add(rAccDetails);
		}
		//TCS Ledger
		System.err.println("TCS Value "+sinvoice.getTcsamt());
		if(sinvoice.getTcsamt() > 0) {
			ReceiptAccDetails rAccDetails=new ReceiptAccDetails(sinvoice.getTcsglac(),(sinvoice.getTcsamt()*-1));
			rcptTaxAcc.add(rAccDetails);
		}
		receiptAccs.addAll(rcptTaxAcc);
		
		List<ReceiptAccDetails> rcptAccs = new ArrayList<ReceiptAccDetails>();
		
        Set<String> ReceiptLists =receiptAccs.stream().map(l->l.getDlegderid()).collect(Collectors.toSet());        
        ReceiptLists.forEach((s)-> {
        	double totalPrice = receiptAccs.stream() 
            		.filter(i -> i.getDlegderid().equals(s))
                    .map(i -> i.getDamount())  
                    .reduce(0.00,(sum, price)->sum+price); 
             
        	rcptAccs.add(new ReceiptAccDetails(s,totalPrice));
        });*/
        /********************************* Filtering Journal Account **********************************/
        
       // System.err.println("Payable Amount: "+sinvoice.getPayable_amt()+" Ledger: "+sinvoice.getPayable_amt_gl_ac());
		/*********** Journal Account Creation Start**********************************************/ 
		/*String result="",vcode="JV",finalcode="",parent ="",ledgerid="",uniquevoucher="",uniquevoucherno="";
		ledgerid=ledgermasterRepository.getLedgerDetails(sinvoice.getPayable_amt_gl_ac()).getLedgerid();
		
		int jslno=0;
		if(journal_accounttransactionRepository.getMaxVoucherId(vcode,sinvoice.getBusiness_unit(),sinvoice.getFin_year()) != null ) {
			jslno=Integer.parseInt(journal_accounttransactionRepository.getMaxVoucherId(vcode,sinvoice.getBusiness_unit(),sinvoice.getFin_year()));
		}
		System.err.println("Journal Serial Number: "+jslno);
		uniquevoucherno = Utility.acVoucherGen(jslno,vcode,sinvoice.getInvoice_date(),sinvoice.getFin_year());
		
		String[] sp=findParentSubGroupCode(sinvoice.getPayable_amt_gl_ac(),sinvoice.getBusiness_unit()).split("x@nk!tltdx");
		
			String subgrps="";	
			for (int i = 0; i < sp.length; i++) 
			{
				if(sp[i].length()==5 && sp[i].trim() !="")
				{
					subgrps +=sp[i]+",";
				}
				else if(sp[i].length()==2 && sp[i].trim() !="")
				{
					subgrps +=sp[i]+",";
				}else {}
				
			}
			subgrps=subgrps.substring(0,(subgrps.length()-1));
			System.err.println("Subgrps:>> "+subgrps);
		
			//journal_accounttransactionRepository
			
			int xy=journal_accounttransactionRepository.saveJournalAccount(sinvoice.getBusiness_unit(), sinvoice.getInvoice_date(),
					finalcode, "rAccTran.getReferenceno", "0000-00-00", sinvoice.getPayable_amt_gl_ac(),
					sinvoice.getPayable_amt(), "0000-00-00","INSERTED",ldt,userRepository.getUserDetails(sinvoice.getUsername()).getName(),
					sinvoice.getFin_year(), sinvoice.getUsername(),"rAccTran.getNarration","rAccTran.getNarration_dtls",uniquevoucherno,
					subgrps,sinvoice.getPayable_amt_gl_ac().substring(0, 2),sinvoice.getInvoice_id(),sinvoice.getInvoice_no());
			
			int s=journal_accounttransactionRepository.saveOutstandingledger(sinvoice.getBusiness_unit(),sinvoice.getPayable_amt_gl_ac(),uniquevoucherno,
					sinvoice.getInvoice_id(),sinvoice.getInvoice_no(),sinvoice.getInvoice_date(),sinvoice.getPayable_amt(),sinvoice.getPayable_amt(),"dr",sinvoice.getFin_year(),
					"INSERTED",ldt,sinvoice.getUsername(),sinvoice.getParty(),sinvoice.getPartyname(),unitname);*/
			
			/************************************************************************************************************/
			/*for(ReceiptAccDetails rcptAcc : rcptAccs) {
				System.err.println("Got Final Filter Ledger: "+rcptAcc.getDlegderid()+" Amount: "+rcptAcc.getDamount());
				String dledgerid="";
				if(Utility.isNullOrEmpty(rcptAcc.getDlegderid())) {
					dledgerid=ledgermasterRepository.getLedgerDetails(rcptAcc.getDlegderid()).getLedgerid();
					ledid+=dledgerid.substring(0, 2)+",";
				}
				
				//accountsInsertionPlus
				String[] sps=findParentSubGroupCode(dledgerid,sinvoice.getBusiness_unit()).split("x@nk!tltdx");
				
					String subgrps1="";	
					for (int i = 0; i < sps.length; i++) 
					{
						if(sps[i].length()==5 && sps[i].trim() !="")
						{
							subgrps1 +=sps[i]+",";
						}
						else if(sps[i].length()==2 && sps[i].trim() !="")
						{
							subgrps1 +=sps[i]+",";
						}else {}
						
					}
					subgrps1=subgrps1.substring(0,(subgrps1.length()-1));
					//System.err.println("Subgrps1:>> "+subgrps1);
					dynsubgrp+=subgrps1+",";
					*/
					//journal_accounttransactionRepository
					//////////////////////////////////////////////////////////////////
					/*int xz=journal_accounttransactionRepository.callJournalAccInsertPlus(sinvoice.getBusiness_unit(),sinvoice.getInvoice_date(),finalcode,uniquevoucherno,
							dledgerid,rcptAcc.getDamount(),"accDtls.getNarration",ldt,sinvoice.getUsername(),sinvoice.getFin_year(),subgrps1,dledgerid.substring(0, 2),"0",0.00,sinvoice.getInvoice_id(),sinvoice.getInvoice_no());	
					*/	
					//System.err.println("Update Status: "+xz);
					
					//////////////////////////////////////////////////////////////////
			/*//}  */
		//02062022 ends here
		/*********** Receipt Account Creation End**********************************************/
		
		
		
		if(sinvoice.isJobwork()) 
		{
			
			sinvoice.getItem_groupwise_hsnsumm().clear();
			sinvoice.getSales_Invoice_Item_Dtls().clear();
			
			sinvoice.getItem_groupwise_summ().clear();
			
			Set<Sales_Invoice_Item_Dtls_for_jobwork> jobSet = new HashSet<Sales_Invoice_Item_Dtls_for_jobwork>();
			jobSet.addAll(sinvoice.getSales_Invoice_Item_Dtls_for_jobwork());
			for(Sales_Invoice_Item_Dtls_for_jobwork jobDts : jobSet)
			{
				jobDts.setInvoice_id(gen_sno);	
				jobDts.setInvoice_no(sinvoice.getInvoice_no());
				
				jobDts.setJob_item_name(item_masterRepository.itemNameById(jobDts.getJob_item()).getItem_name());
				if(Utility.isNullOrEmpty(jobDts.getJob_packing())) 
				{
					jobDts.setJob_packing_name(item_masterRepository.itemNameById(jobDts.getJob_packing()).getItem_name());
				}
				else
				{
					jobDts.setJob_packing_name("0");
				}
				jobDts.setCompany_id(sinvoice.getCompany_id());
				jobDts.setFin_year(sinvoice.getFin_year());
				jobDts.setModified_type("INSERTED");
				jobDts.setInserted_by(sinvoice.getInserted_by());
				jobDts.setInserted_on(sinvoice.getInserted_on());
				jobDts.setUpdated_by("NA");
				jobDts.setUpdated_on(ldt);
				jobDts.setDeleted_by("NA");
				jobDts.setDeleted_on(ldt);
				
			}
			sinvoice.setSales_Invoice_Item_Dtls_for_jobwork(jobSet);
			
			Set<Sales_Invoice_Item_Dtls_for_jobwork_price> serviceItem = new HashSet<Sales_Invoice_Item_Dtls_for_jobwork_price>();
			serviceItem.addAll(sinvoice.getSales_Invoice_Item_Dtls_for_jobwork_price());
			for(Sales_Invoice_Item_Dtls_for_jobwork_price service : serviceItem)
			{
				service.setInvoice_id(gen_sno);	
				service.setInvoice_no(sinvoice.getInvoice_no());
				
				if(Utility.isNullOrEmpty(service.getItem_service())) {
					service.setItem_service_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getService_name());
				}else{service.setItem_service_name("0");};
				
				if(Utility.isNullOrEmpty(service.getTaxcode())) {
					service.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(service.getTaxcode()).getTax_name());
				}else{service.setTaxcode_name("0");};
				
				if(Utility.isNullOrEmpty(service.getItem_service())) {
					service.setItem_service_acc_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getAc_ledger_name());
				}else{service.setItem_service_acc_name("0");};
				
				service.setCompany_id(sinvoice.getCompany_id());
				service.setFin_year(sinvoice.getFin_year());
				service.setModified_type("INSERTED");
				service.setInserted_by(userRepository.getUserDetails(sinvoice.getUsername()).getName());
				service.setInserted_on(sinvoice.getInserted_on());
				service.setUpdated_by("NA");
				service.setUpdated_on(ldt);
				service.setDeleted_by("NA");
				service.setDeleted_on(ldt);
			}
			sinvoice.setSales_Invoice_Item_Dtls_for_jobwork_price(serviceItem);
			
			
		}
		else 
		{
			sinvoice.getSales_Invoice_Item_Dtls_for_jobwork().clear();
			sinvoice.getSales_Invoice_Item_Dtls_for_jobwork_price().clear();
			
			/************* Tax Entry Start *********************************/	
			
			Set<Item_groupwise_summ> iSummSet=new HashSet<Item_groupwise_summ>();
			iSummSet.addAll(sinvoice.getItem_groupwise_summ());
			for(Item_groupwise_summ iSumm:iSummSet) 
			{
				iSumm.setInvoice_id(gen_sno);	
				iSumm.setFin_year(sinvoice.getFin_year());
				iSumm.setCompany_id(sinvoice.getCompany_id());
				iSumm.setUsername(sinvoice.getUsername());
				iSumm.setModified_type("INSERTED");
				iSumm.setInserted_by(sinvoice.getInserted_by());
				iSumm.setInserted_on(sinvoice.getInserted_on());
				iSumm.setUpdated_by(sinvoice.getUpdated_by());
				iSumm.setUpdated_on(sinvoice.getUpdated_on());
				iSumm.setDeleted_by(sinvoice.getDeleted_by());
				iSumm.setDeleted_on(sinvoice.getDeleted_on());
			}
			sinvoice.setItem_groupwise_summ(iSummSet);	
			
			
			
			Set<Item_groupwise_hsnsumm> iHsnSummSet=new HashSet<Item_groupwise_hsnsumm>();
			iHsnSummSet.addAll(sinvoice.getItem_groupwise_hsnsumm());
			for(Item_groupwise_hsnsumm iHsnSumm:iHsnSummSet) 
			{
				iHsnSumm.setInvoice_id(gen_sno);
				iHsnSumm.setFin_year(sinvoice.getFin_year());
				iHsnSumm.setCompany_id(sinvoice.getCompany_id());
				iHsnSumm.setUsername(sinvoice.getUsername());
				iHsnSumm.setModified_type("INSERTED");
				iHsnSumm.setInserted_by(sinvoice.getInserted_by());
				iHsnSumm.setInserted_on(sinvoice.getInserted_on());
				iHsnSumm.setUpdated_by(sinvoice.getUpdated_by());
				iHsnSumm.setUpdated_on(sinvoice.getUpdated_on());
				iHsnSumm.setDeleted_by(sinvoice.getDeleted_by());
				iHsnSumm.setDeleted_on(sinvoice.getDeleted_on());
			}
			sinvoice.setItem_groupwise_hsnsumm(iHsnSummSet);
			
			
			/************* Tax Entry End *********************************/
			
			Set<Sales_Invoice_Item_Dtls> salesInvItmDtlSet=new HashSet<Sales_Invoice_Item_Dtls>();
			salesInvItmDtlSet.addAll(sinvoice.getSales_Invoice_Item_Dtls());
			for(Sales_Invoice_Item_Dtls itmDtls:salesInvItmDtlSet) 
			{
				itmDtls.setInvoice_id(gen_sno);	
				itmDtls.setInvoice_no(sinvoice.getInvoice_no());
				itmDtls.setItem_name(item_masterRepository.itemNameById(itmDtls.getItem_code()).getItem_name());
				if(Utility.isNullOrEmpty(itmDtls.getPacking())) {
					itmDtls.setPacking_name(item_masterRepository.itemNameById(itmDtls.getPacking()).getItem_name());
				}
				itmDtls.setTax_codename(tax_code_detailsRepository.getTaxCodeDetails(itmDtls.getTax_code()).getTax_name());
				//System.out.println("1 "+itmDtls.getItem_group());
				
				//System.out.println("2 " + item_group_masterRepository.getGroupDtls_converter(itmDtls.getItem_group()).getGroup_name());
				itmDtls.setItem_groupname(item_group_masterRepository.getGroupDtls_converter(itmDtls.getItem_group()).getGroup_name());
				
				
				
				itmDtls.setUsername(sinvoice.getUsername());
				itmDtls.setFin_year(sinvoice.getFin_year());
				itmDtls.setCompany_id(sinvoice.getCompany_id());
				itmDtls.setModified_type("INSERTED");
				itmDtls.setInserted_by(sinvoice.getInserted_by());
				itmDtls.setInserted_on(sinvoice.getInserted_on());
				itmDtls.setUpdated_by("NA");
				itmDtls.setUpdated_on(ldt);
				itmDtls.setDeleted_by("NA");
				itmDtls.setDeleted_on(ldt);
			}
			sinvoice.setSales_Invoice_Item_Dtls(salesInvItmDtlSet);
		}
		
		
		Set<Item_groupwise_taxsumm> iTaxSummSet=new HashSet<Item_groupwise_taxsumm>();
		iTaxSummSet.addAll(sinvoice.getItem_groupwise_taxsumm());
		for(Item_groupwise_taxsumm itaxSumm:iTaxSummSet) 
		{
			itaxSumm.setInvoice_id(gen_sno);	
			itaxSumm.setFin_year(sinvoice.getFin_year());
			itaxSumm.setCompany_id(sinvoice.getCompany_id());
			itaxSumm.setUsername(sinvoice.getUsername());
			itaxSumm.setModified_type("INSERTED");
			itaxSumm.setInserted_by(sinvoice.getInserted_by());
			itaxSumm.setInserted_on(sinvoice.getInserted_on());
			itaxSumm.setUpdated_by(sinvoice.getUpdated_by());
			itaxSumm.setUpdated_on(sinvoice.getUpdated_on());
			itaxSumm.setDeleted_by(sinvoice.getDeleted_by());
			itaxSumm.setDeleted_on(sinvoice.getDeleted_on());
		}
		sinvoice.setItem_groupwise_taxsumm(iTaxSummSet);
		
		
		
		Set<Sales_Invoice_Tax_Info> taxInfoSet=new HashSet<Sales_Invoice_Tax_Info>();
		taxInfoSet.add(sinvoice.getSales_Invoice_Tax_Info());
		for(Sales_Invoice_Tax_Info taxInfo:taxInfoSet) 
		{
			taxInfo.setInvoice_id(gen_sno);	
			taxInfo.setInvoice_no(sinvoice.getInvoice_no());
			taxInfo.setUsername(sinvoice.getUsername());
			taxInfo.setFin_year(sinvoice.getFin_year());
			taxInfo.setCompany_id(sinvoice.getCompany_id());
			taxInfo.setModified_type("INSERTED");
			taxInfo.setInserted_by(sinvoice.getInserted_by());
			taxInfo.setInserted_on(sinvoice.getInserted_on());
			taxInfo.setUpdated_by("NA");
			taxInfo.setUpdated_on(ldt);
			taxInfo.setDeleted_by("NA");
			taxInfo.setDeleted_on(ldt);
		}
		if(!taxInfoSet.isEmpty()) {
			sinvoice.setSales_Invoice_Tax_Info(taxInfoSet.iterator().next());
		}
		
		Set<Sales_Invoice_Broker_Dtls> salesInvBrkDtlSet=new HashSet<Sales_Invoice_Broker_Dtls>();
		salesInvBrkDtlSet.addAll(sinvoice.getSales_Invoice_Broker_Dtls());
		for(Sales_Invoice_Broker_Dtls invBrkDtls:salesInvBrkDtlSet) 
		{
			invBrkDtls.setInvoice_id(gen_sno);	
			invBrkDtls.setInvoice_no(sinvoice.getInvoice_no());
			
		//	invBrkDtls.setBroker_name(cust_bussiness_partner_brokerRepository.custBrokerRetriveListNew(invBrkDtls.getBrokercode()).getVen_name());
			invBrkDtls.setBroker_name(broker_masterRepository.brokerNameByCode(invBrkDtls.getBrokercode()).getName());
			
			invBrkDtls.setUsername(sinvoice.getUsername());
			invBrkDtls.setFin_year(sinvoice.getFin_year());
			invBrkDtls.setCompany_id(sinvoice.getCompany_id());
			invBrkDtls.setModified_type("INSERTED");
			invBrkDtls.setInserted_by(sinvoice.getInserted_by());
			invBrkDtls.setInserted_on(sinvoice.getInserted_on());
			invBrkDtls.setUpdated_by("NA");
			invBrkDtls.setUpdated_on(ldt);
			invBrkDtls.setDeleted_by("NA");
			invBrkDtls.setDeleted_on(ldt);
		}
		sinvoice.setSales_Invoice_Broker_Dtls(salesInvBrkDtlSet);
		
		//Transporter
		Set<Sales_Invoice_Trans_Dtls> salesTransSet=new HashSet<Sales_Invoice_Trans_Dtls>();
		salesTransSet.addAll(sinvoice.getSales_Invoice_Trans_Dtls());
		for(Sales_Invoice_Trans_Dtls invTransDtls:salesTransSet) 
		{
			if(Utility.isNullOrEmpty(invTransDtls.getVehicleid())) {
				invTransDtls.setVehicleno(vehicleMasterRepository.getVehicleDetails(invTransDtls.getVehicleid()).getVehicle_no());
			}else {invTransDtls.setVehicleno("0");}
			
			if(Utility.isNullOrEmpty(invTransDtls.getTransname())) 
			{
				if(invTransDtls.getTransname().compareToIgnoreCase("0")==0)
				{
					invTransDtls.setTransportername("0");
				}
				else
				{
					invTransDtls.setTransportername(trans_bussiness_partnerRepository.bpNameById(invTransDtls.getTransname()).getBp_name());
				}
			}
			else{invTransDtls.setTransportername("0");};
			
			invTransDtls.setInvoice_id(gen_sno);	
			invTransDtls.setInvoice_no(sinvoice.getInvoice_no());
			invTransDtls.setUsername(sinvoice.getUsername());
			invTransDtls.setFin_year(sinvoice.getFin_year());
			invTransDtls.setCompany_id(sinvoice.getCompany_id());
			invTransDtls.setModified_type("INSERTED");
			invTransDtls.setInserted_by(sinvoice.getInserted_by());
			invTransDtls.setInserted_on(sinvoice.getInserted_on());
			invTransDtls.setUpdated_by("NA");
			invTransDtls.setUpdated_on(ldt);
			invTransDtls.setDeleted_by("NA");
			invTransDtls.setDeleted_on(ldt);
		}
		sinvoice.setSales_Invoice_Trans_Dtls(salesTransSet);
		
		//Delivery Information
		Set<Sales_Invoice_Shipment_Dtls> invShipSet=new HashSet<Sales_Invoice_Shipment_Dtls>();
		invShipSet.add(sinvoice.getSales_Invoice_Shipment_Dtls());
		for(Sales_Invoice_Shipment_Dtls invShipDtls:invShipSet) 
		{
			invShipDtls.setInvoice_id(gen_sno);	
			invShipDtls.setInvoice_no(sinvoice.getInvoice_no());
			if(Utility.isNullOrEmpty(invShipDtls.getShipaddr()))
			{
				
			}
			else 
			{
				invShipDtls.setShipaddr("0");
			}
			invShipDtls.setUsername(sinvoice.getUsername());
			invShipDtls.setFin_year(sinvoice.getFin_year());
			invShipDtls.setCompany_id(sinvoice.getCompany_id());
			invShipDtls.setModified_type("INSERTED");
			invShipDtls.setInserted_by(sinvoice.getInserted_by());
			invShipDtls.setInserted_on(sinvoice.getInserted_on());
			invShipDtls.setUpdated_by("NA");
			invShipDtls.setUpdated_on(ldt);
			invShipDtls.setDeleted_by("NA");
			invShipDtls.setDeleted_on(ldt);
		}
		if(!invShipSet.isEmpty()) {
			sinvoice.setSales_Invoice_Shipment_Dtls(invShipSet.iterator().next());
		}
		
		//Payment
		Set<Sales_Invoice_Payment_Dtls> invPaySet=new HashSet<Sales_Invoice_Payment_Dtls>();
		invPaySet.add(sinvoice.getSales_Invoice_Payment_Dtls());
		for(Sales_Invoice_Payment_Dtls invPayDtls:invPaySet) 
		{
			invPayDtls.setInvoice_id(gen_sno);	
			invPayDtls.setInvoice_no(sinvoice.getInvoice_no());
			invPayDtls.setUsername(sinvoice.getUsername());
			invPayDtls.setFin_year(sinvoice.getFin_year());
			invPayDtls.setCompany_id(sinvoice.getCompany_id());
			invPayDtls.setModified_type("INSERTED");
			invPayDtls.setInserted_by(sinvoice.getInserted_by());
			invPayDtls.setInserted_on(sinvoice.getInserted_on());
			invPayDtls.setUpdated_by("NA");
			invPayDtls.setUpdated_on(ldt);
			invPayDtls.setDeleted_by("NA");
			invPayDtls.setDeleted_on(ldt);
		}
		if(!invPaySet.isEmpty()) {
			sinvoice.setSales_Invoice_Payment_Dtls(invPaySet.iterator().next());
		}
		
		Set<Sales_Invoice_BP_Dtls> invBpDtlsSet=new HashSet<Sales_Invoice_BP_Dtls>();
		invBpDtlsSet.add(sinvoice.getSales_Invoice_BP_Dtls());
		for(Sales_Invoice_BP_Dtls invBpDtls:invBpDtlsSet) 
		{
			invBpDtls.setInvoice_id(gen_sno);	
			invBpDtls.setInvoice_no(sinvoice.getInvoice_no());
			invBpDtls.setUsername(sinvoice.getUsername());
			invBpDtls.setFin_year(sinvoice.getFin_year());
			invBpDtls.setCompany_id(sinvoice.getCompany_id());
			invBpDtls.setModified_type("INSERTED");
			invBpDtls.setInserted_by(sinvoice.getInserted_by());
			invBpDtls.setInserted_on(sinvoice.getInserted_on());
			invBpDtls.setUpdated_by("NA");
			invBpDtls.setUpdated_on(ldt);
			invBpDtls.setDeleted_by("NA");
			invBpDtls.setDeleted_on(ldt);
		}
		if(!invBpDtlsSet.isEmpty())	{
			sinvoice.setSales_Invoice_BP_Dtls(invBpDtlsSet.iterator().next());
		}
		
		Set<Sales_Invoice_Docs> salesInvDocSet=new HashSet<Sales_Invoice_Docs>();
		salesInvDocSet.addAll(sinvoice.getSales_Invoice_Docs());
		int g=0;
		for(Sales_Invoice_Docs invDocs:salesInvDocSet) 
		{
	
			if(files.length > 0) 
			{
				try 
				{
					invDocs.setDoc_pdf(fileUpload(files[g],gen_sno+"_"));	
					invDocs.setDoc_file_name(sinvoice.getInvoice_id()+"_"+files[g].getOriginalFilename());
				g++;
				}
				catch (IOException e)
				{
					System.out.println(e);
				}
			}
			
			invDocs.setInvoice_id(gen_sno);	
			invDocs.setInvoice_no(sinvoice.getInvoice_no());
			invDocs.setUsername(sinvoice.getUsername());
			invDocs.setFin_year(sinvoice.getFin_year());
			invDocs.setCompany_id(sinvoice.getCompany_id());
			invDocs.setModified_type("INSERTED");
			invDocs.setInserted_by(sinvoice.getInserted_by());
			invDocs.setInserted_on(sinvoice.getInserted_on());
			invDocs.setUpdated_by("NA");
			invDocs.setUpdated_on(ldt);
			invDocs.setDeleted_by("NA");
			invDocs.setDeleted_on(ldt);
		}
		sinvoice.setSales_Invoice_Docs(salesInvDocSet);
		
		//Update Delivery Challan *****
		if(sinvoice.getChallan().compareToIgnoreCase("Multiple")==0) 
		{
			String splitdeliveryid[]=sinvoice.getReference_id().split(",");
			
			
			for(int i=0;i<splitdeliveryid.length;i++) 
			{
				delivery_challanRepository.updateDelvChallanStatus(splitdeliveryid[i],"Yes","Multiple",gen_sno);	
			}
			
		}
		else 
		{	
			delivery_challanRepository.updateDelvChallanStatus(sinvoice.getReference_id(),"Yes","Single",gen_sno);
			
		}

		//Invoice_accountdetails
		//02062022 starts here
		/*Invoice_accountdetails partyAccDtls=new Invoice_accountdetails(sinvoice.getInvoice_id(),sinvoice.getInvoice_no(),sinvoice.getBusiness_unit(),unitname,sinvoice.getParty(),sinvoice.getPartyname(),sinvoice.getInvoice_date(),(sinvoice.getPayable_amt()*-1),"I",transtype,uniquevoucherno,sinvoice.getModified_type(),sinvoice.getInserted_on(),sinvoice.getInserted_by(),sinvoice.getFin_year(),sinvoice.getUsername());
		Invoice_accountdetails accDtls=new Invoice_accountdetails(sinvoice.getInvoice_id(),sinvoice.getInvoice_no(),sinvoice.getBusiness_unit(),unitname,sinvoice.getPayable_amt_gl_ac(),ledgermasterRepository.getLedgerDetails(sinvoice.getPayable_amt_gl_ac()).getLedgername(),sinvoice.getInvoice_date(),sinvoice.getPayable_amt(),"A",transtype,uniquevoucherno,sinvoice.getModified_type(),sinvoice.getInserted_on(),sinvoice.getInserted_by(),sinvoice.getFin_year(),sinvoice.getUsername());
		invoice_accountdetailsRepository.save(partyAccDtls);
		invoice_accountdetailsRepository.save(accDtls);*/
		//02062022 ends here
		return sales_InvoiceRepository.save(sinvoice);
	}
	
	private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(FileUtil.folderPathSalesInvoice);
        if (! directory.exists()){
            directory.mkdir();
        }
    }
	
	@Transactional
	public  String fileUpload(@RequestParam("files") MultipartFile files,String fileName) throws IOException
	{
		 
				createDirIfNotExist();
			
				//System.out.println("fileName::"+fileName+" files::"+files.getOriginalFilename());
		
		    String  files_name = FileUtil.folderPathSalesInvoice+fileName+files.getOriginalFilename();
		            	 
		            	 File convertFile = new File(FileUtil.folderPathSalesInvoice+fileName+files.getOriginalFilename());
		                 convertFile.createNewFile();
		                 FileOutputStream fout = new FileOutputStream(convertFile);
		                 fout.write(files.getBytes());
		                 fout.close();
		 
		            return files_name;
	}
	
	@Transactional
	public Sales_Invoice update(Sales_Invoice sinvoice,MultipartFile files[])
	{
		Optional<Sales_Invoice> op = sales_InvoiceRepository.findById(sinvoice.getId());
		Sales_Invoice sales_Invoice=sales_InvoiceRepository.findSalesInvoiceDtls(sinvoice.getId());
		LocalDateTime ldt = LocalDateTime.now();
		Sales_Invoice Sales_InvoiceStatus=new Sales_Invoice();
		String unitname="";
		
		if(op.get().isPayment_status() == true) {
			System.err.println("Update is not Possible !!!");
			return Sales_InvoiceStatus;
		}else {
			System.err.println("Update is Continue !!!");
		
			if(op.isPresent()) {
				sinvoice.setId(sinvoice.getId());
			}
			
			//if(Integer.parseInt(sinvoice.getReference_id()) == 0)
			if(sinvoice.getReference_id().compareToIgnoreCase("0") == 0)
			{
				sinvoice.setReference_id(op.get().getReference_id());
			}
			
			
			//item_total_gl_ac='0',net_total_gl_ac='0',applicable_gl_ac='0',discount_gl_ac='0',
					//final_bill_amt_gl_ac='0',payable_amt_gl_ac='0',roundoff_gl_ac='IB00001',
					//tax_total_gl_ac='0',total_bill_amt_gl_ac='0'
			
           if(Utility.isNullOrEmpty(sinvoice.getItem_total_gl_ac())) 
           {
			}else 
			{
				sinvoice.setItem_total_gl_ac(op.get().getItem_total_gl_ac());
			}
           if(Utility.isNullOrEmpty(sinvoice.getNet_total_gl_ac())) 
           {
			}else 
			{
				sinvoice.setNet_total_gl_ac(op.get().getNet_total_gl_ac());
			}
           if(Utility.isNullOrEmpty(sinvoice.getApplicable_gl_ac())) 
           {
			}else 
			{
				sinvoice.setApplicable_gl_ac(op.get().getApplicable_gl_ac());
			}
           if(Utility.isNullOrEmpty(sinvoice.getDiscount_gl_ac())) 
           {
			}else 
			{
				sinvoice.setDiscount_gl_ac(op.get().getDiscount_gl_ac());
			}
			
           if(Utility.isNullOrEmpty(sinvoice.getFinal_bill_amt_gl_ac())) 
           {
			}else 
			{
				sinvoice.setFinal_bill_amt_gl_ac(op.get().getFinal_bill_amt_gl_ac());
			}
           if(Utility.isNullOrEmpty(sinvoice.getPayable_amt_gl_ac())) 
           {
			}else 
			{
				sinvoice.setPayable_amt_gl_ac(op.get().getPayable_amt_gl_ac());
			}
           if(Utility.isNullOrEmpty(sinvoice.getTax_total_gl_ac())) 
           {
			}else 
			{
				sinvoice.setTax_total_gl_ac(op.get().getTax_total_gl_ac());
			}
           if(Utility.isNullOrEmpty(sinvoice.getTotal_bill_amt_gl_ac())) 
           {
			}else 
			{
				sinvoice.setTotal_bill_amt_gl_ac(op.get().getTotal_bill_amt_gl_ac());
			}
			
			
			
			
			
			
			if(Utility.isNullOrEmpty(sinvoice.getRoundoff_gl_ac())) {
				
			}else {sinvoice.setRoundoff_gl_ac(op.get().getRoundoff_gl_ac());}
			
			sinvoice.setSales_returnstatus(op.get().getSales_returnstatus());
			sinvoice.setInvoice_no(op.get().getInvoice_no());
			sinvoice.setModified_type("INSERTED");
			sinvoice.setInserted_by(sales_Invoice.getInserted_by());
			sinvoice.setInserted_on(sales_Invoice.getInserted_on());
			sinvoice.setUpdated_by(userRepository.getUserDetails(sinvoice.getUsername()).getName());
			sinvoice.setUpdated_on(ldt);
			sinvoice.setDeleted_by("NA");
			sinvoice.setDeleted_on(ldt);
			sinvoice.setPayment_status(false);
			sinvoice.setReturn_approval_status(op.get().getReturn_approval_status());
			sinvoice.setInvoicedate(sinvoice.getInvoice_date());
			sinvoice.setInvoiceno(sinvoice.getInvoice_no());
			sinvoice.setState(cust_bussiness_partnerRepository.gettallycreditnotestate(sinvoice.getParty()).getState());
			
			if(Utility.isNullOrEmpty(sinvoice.getParty())) {
				sinvoice.setPartyname(cust_bussiness_partnerRepository.getCustomerName(sinvoice.getParty()).getCp_name());
			}else {sinvoice.setPartyname("None");}
			
			if(Utility.isNullOrEmpty(sinvoice.getBusiness_unit())) {
				unitname=companyBusinessUnitMasterRepository.CompanyBUAddress(sinvoice.getBusiness_unit()).getBusinessunit_name();
			}else {unitname="None";}
			
			// GST STATUS Wheather PARTY Resgistered or Not
			if(Utility.isNullOrEmpty(cust_bussiness_partnerRepository.getInvCustBPGstStatus(sinvoice.getParty())))		
			{
				sinvoice.setGststatus("YES"); 
			}		
			else
			{
				sinvoice.setGststatus("NO"); 
			}

			//here advice no added in sales invoice through delivery challan 
			if(sinvoice.getChallan().compareToIgnoreCase("Single")==0) 
			{
				//String advice_id=delivery_challanRepository.getDeliveryChallanDtls(sinvoice.getReference_id()).getReferance_id();
				//sinvoice.setAdviceno(wm_loading_adviceRepository.getLoadingStk(advice_id).getAdvice_no());
				
				String reftype=delivery_challanRepository.getDeliveryChallanDtls(sinvoice.getReference_id()).getRef_type(); //Bypass for GRN to direct Delivery Challan
				if(reftype.compareTo("Loading Advice")==0) {
					String advice_id=delivery_challanRepository.getDeliveryChallanDtls(sinvoice.getReference_id()).getReferance_id();
					sinvoice.setAdviceno(wm_loading_adviceRepository.getLoadingStk(advice_id).getAdvice_no());
				}else {sinvoice.setAdviceno("NA");}
				
			}
			else 
			{
				
				String alldeliverychallan[]=sinvoice.getReference_id().split(",");
				List<String> deliverychallan = new ArrayList<String>();
				for(int i=0;i<alldeliverychallan.length;i++) 
				{
					deliverychallan.add( delivery_challanRepository.getDeliveryChallanDtls(alldeliverychallan[i]).getReferance_id());
				}
				
				
				String advicenumbers="";
				for(int v=0;v<deliverychallan.size();v++) 
				{
					advicenumbers=advicenumbers+wm_loading_adviceRepository.getLoadingStk(deliverychallan.get(v)).getAdvice_no()+",";
				}
				
				advicenumbers=advicenumbers.substring(0,advicenumbers.length()-1);
				
				sinvoice.setAdviceno(advicenumbers);
			}
		
			
		//here ends 	
			
			sinvoice.setInvoice_type_name(invoice_typeRepository.getSalesInvTypesDtls(sinvoice.getInvoice_type()).getInvtype_name());
			sinvoice.setBusinessunitname(companyBusinessUnitMasterRepository.businessunitbyid(sinvoice.getBusiness_unit()).getBusinessunit_name());
			
			/********************************* Filtering Receipt Account **********************************/
			//02062022 Starts here
			/*Set<Item_groupwise_summ> iGrpSumm=new HashSet<Item_groupwise_summ>();
			iGrpSumm.addAll(sinvoice.getItem_groupwise_summ());
			Set<ReceiptAccDetails> receiptAccs=new HashSet<ReceiptAccDetails>();
			
			//For Item Ledger
			iGrpSumm.forEach((igSumm) -> {
				if(igSumm.getItem_total() > 0) {
					ReceiptAccDetails rAccDetails=new ReceiptAccDetails(igSumm.getItem_ledger(),(igSumm.getItem_total()*-1));
					receiptAccs.add(rAccDetails);
				}
			});
			//For Dicount Ledger
			iGrpSumm.forEach((igSumm) -> {
				if(igSumm.getDiscount_amt() > 0) {
					ReceiptAccDetails rAccDetails=new ReceiptAccDetails(igSumm.getDiscount_ledger(),igSumm.getDiscount_amt());
					receiptAccs.add(rAccDetails);
				}
			});
			
			Set<Item_groupwise_taxsumm> iGrpTaxSumm=new HashSet<Item_groupwise_taxsumm>();
			iGrpTaxSumm.addAll(sinvoice.getItem_groupwise_taxsumm());
			Set<ReceiptAccDetails> rcptTaxAcc=new HashSet<ReceiptAccDetails>();
			
			//For CGST Ledger
			iGrpTaxSumm.forEach((igtSumm) -> {
				if(igtSumm.getCgst() > 0) {
					ReceiptAccDetails rAccDetails=new ReceiptAccDetails(igtSumm.getCgstledgerid(),(igtSumm.getCgst()*-1));
					rcptTaxAcc.add(rAccDetails);
				}
			});
			//For SGST Ledger
			iGrpTaxSumm.forEach((igtSumm) -> {
				if(igtSumm.getSgst() > 0) {
					ReceiptAccDetails rAccDetails=new ReceiptAccDetails(igtSumm.getSgstledgerid(),(igtSumm.getSgst()*-1));
					rcptTaxAcc.add(rAccDetails);
				}
			});
			//For IGST Ledger
			iGrpTaxSumm.forEach((igtSumm) -> {
				if(igtSumm.getIgst() > 0) {
					ReceiptAccDetails rAccDetails=new ReceiptAccDetails(igtSumm.getIgstledgerid(),(igtSumm.getIgst()*-1));
					rcptTaxAcc.add(rAccDetails);
				}
			});
			//Round Off Ledger
			System.err.println("Round Off Value "+sinvoice.getRoundoff_amt());
			if(sinvoice.getRoundoff_amt() != 0) {
				ReceiptAccDetails rAccDetails=new ReceiptAccDetails(sinvoice.getRoundoff_gl_ac(),(sinvoice.getRoundoff_amt()*-1));
				rcptTaxAcc.add(rAccDetails);
			}
			//TCS Ledger
			System.err.println("TCS Value "+sinvoice.getTcsamt());
			if(sinvoice.getTcsamt() > 0) {
				ReceiptAccDetails rAccDetails=new ReceiptAccDetails(sinvoice.getTcsglac(),(sinvoice.getTcsamt()*-1));
				rcptTaxAcc.add(rAccDetails);
			}
			receiptAccs.addAll(rcptTaxAcc);
			
			List<ReceiptAccDetails> rcptAccs = new ArrayList<ReceiptAccDetails>();
			
	        Set<String> ReceiptLists =receiptAccs.stream().map(l->l.getDlegderid()).collect(Collectors.toSet());        
	        ReceiptLists.forEach((s)-> {
	        	double totalPrice = receiptAccs.stream() 
	            		.filter(i -> i.getDlegderid().equals(s))
	                    .map(i -> i.getDamount())  
	                    .reduce(0.00,(sum, price)->sum+price); 
	             
	        	rcptAccs.add(new ReceiptAccDetails(s,totalPrice));
	        });*/
	        /********************************* Filtering Receipt Account **********************************/
	        
	       // System.err.println("Payable Amount: "+sinvoice.getPayable_amt()+" Ledger: "+sinvoice.getPayable_amt_gl_ac());
			/*********** Journal Account Creation Start**********************************************/ 
			/*String result="",vcode="JV",finalcode="",parent ="",ledgerid="",uniquevoucher="",uniquevoucherno="";
			ledgerid=ledgermasterRepository.getLedgerDetails(sinvoice.getPayable_amt_gl_ac()).getLedgerid();
			
			String voucherno=outstandingledgerRepository.getOutstandingledgerDtls(sales_Invoice.getInvoice_id()).getVoucherno();
			*/
			/*int jslno=0;
			if(journal_accounttransactionRepository.getMaxVoucherId(vcode,sinvoice.getBusiness_unit(),sinvoice.getFin_year()) != null ) {
				jslno=Integer.parseInt(journal_accounttransactionRepository.getMaxVoucherId(vcode,sinvoice.getBusiness_unit(),sinvoice.getFin_year()));
			}
			System.err.println("Journal Serial Number: "+jslno);
			uniquevoucherno = Utility.acVoucherGen(jslno,vcode,sinvoice.getInvoice_date(),sinvoice.getFin_year());*/
			/*
				String[] sp=findParentSubGroupCode(sinvoice.getPayable_amt_gl_ac(),sinvoice.getBusiness_unit()).split("x@nk!tltdx");
				String subgrps="";	
				for (int i = 0; i < sp.length; i++) 
				{
					if(sp[i].length()==5 && sp[i].trim() !="") {
						subgrps +=sp[i]+",";
					}else if(sp[i].length()==2 && sp[i].trim() !="") {
						subgrps +=sp[i]+",";
					}else {}
				}
				subgrps=subgrps.substring(0,(subgrps.length()-1));
				System.err.println("Subgrps:>> "+subgrps);
				
				String[] usp=findParentSubGroupCode(sales_Invoice.getPayable_amt_gl_ac(),sales_Invoice.getBusiness_unit()).split("x@nk!tltdx");
				String usubgrps="";	
				for (int i = 0; i < usp.length; i++) 
				{
					if(usp[i].length()==5 && usp[i].trim() !="") {
						usubgrps +=usp[i]+",";
					}else if(usp[i].length()==2 && usp[i].trim() !="") {
						usubgrps +=usp[i]+",";
					}else {}
				}
				usubgrps=usubgrps.substring(0,(usubgrps.length()-1));
				
				//Update Journal_accounttransaction
				//Calling updateJournalAccount with adding voucherno
				System.err.println("Got Voucher "+voucherno+" Unit" +sales_Invoice.getBusiness_unit()+" Ledger: "+sales_Invoice.getPayable_amt_gl_ac()+" Amt: "+sales_Invoice.getPayable_amt()+" Grp: "+sales_Invoice.getPayable_amt_gl_ac().substring(0, 2)+" Subgrps: "+usubgrps);
				
				//Delete Journal
				journal_accounttransactionRepository.deleteJournal_account(voucherno,sales_Invoice.getBusiness_unit().trim());
				
				int uxy=journal_accounttransactionRepository.updateJournalAccount(sales_Invoice.getBusiness_unit().trim(), sales_Invoice.getInvoice_date(),
						voucherno, "rAccTran.getReferenceno", "0000-00-00", sales_Invoice.getPayable_amt_gl_ac(),
						sales_Invoice.getPayable_amt(), "0000-00-00","UPDATED",ldt,userRepository.getUserDetails(sales_Invoice.getUsername()).getName(),
						sales_Invoice.getFin_year(), sales_Invoice.getUsername(),"rAccTran.getNarration","rAccTran.getNarration_dtls",voucherno,
						usubgrps,sales_Invoice.getPayable_amt_gl_ac().substring(0, 2));
				
				//Save Journal_accounttransaction
				int xy=journal_accounttransactionRepository.saveJournalAccount(sinvoice.getBusiness_unit(), sinvoice.getInvoice_date(),
						finalcode, "rAccTran.getReferenceno", "0000-00-00", sinvoice.getPayable_amt_gl_ac(),
						sinvoice.getPayable_amt(), "0000-00-00","INSERTED",ldt,userRepository.getUserDetails(sinvoice.getUsername()).getName(),
						sinvoice.getFin_year(), sinvoice.getUsername(),"rAccTran.getNarration","rAccTran.getNarration_dtls",voucherno,
						subgrps,sinvoice.getPayable_amt_gl_ac().substring(0, 2),sales_Invoice.getInvoice_id(),sales_Invoice.getInvoice_no());
				
				//Calling updateOutstandingledger with adding voucherno,invoice_id
				int us=journal_accounttransactionRepository.updateOutstandingledger(sales_Invoice.getBusiness_unit(),sales_Invoice.getPayable_amt_gl_ac(),voucherno,
						sales_Invoice.getInvoice_id(),sales_Invoice.getInvoice_date(),sales_Invoice.getPayable_amt(),sales_Invoice.getPayable_amt(),"dr",sales_Invoice.getFin_year(),
						"UPDATED",ldt,sales_Invoice.getUsername(),sales_Invoice.getParty(),sales_Invoice.getPartyname(),unitname);
			
				int s=journal_accounttransactionRepository.saveOutstandingledger(sinvoice.getBusiness_unit(),sinvoice.getPayable_amt_gl_ac(),voucherno,
						sales_Invoice.getInvoice_id(),sales_Invoice.getInvoice_no(),sinvoice.getInvoice_date(),sinvoice.getPayable_amt(),sinvoice.getPayable_amt(),"dr",sinvoice.getFin_year(),
						"INSERTED",ldt,sinvoice.getUsername(),sinvoice.getParty(),sinvoice.getPartyname(),unitname);
				*/
				/***********Update Receipt Accountdetails Start**********************************************/
				/*List<Account_details> accDetails = new ArrayList<Account_details>();
				accDetails.addAll(account_detailsRepository.getAccount_details(voucherno,sales_Invoice.getPayable_amt_gl_ac()));
				
				for(Account_details urcptAcc : accDetails) {
					System.err.println("Got Final Filter Ledger: "+urcptAcc.getLedgerid()+" Amount: "+urcptAcc.getAmount());
					String udledgerid="";
					if(Utility.isNullOrEmpty(urcptAcc.getLedgerid())) {
						udledgerid=ledgermasterRepository.getLedgerDetails(urcptAcc.getLedgerid()).getLedgerid();
					}
						//accountsInsertionPlus
						String[] usps=findParentSubGroupCode(udledgerid,sales_Invoice.getBusiness_unit()).split("x@nk!tltdx");
						String usubgrps1="";	
						for (int i = 0; i < usps.length; i++) {
							if(usps[i].length()==5 && usps[i].trim() !="") {
								usubgrps1 +=usps[i]+",";
							}else if(usps[i].length()==2 && usps[i].trim() !="") {
								usubgrps1 +=usps[i]+",";
							}else {}
						}
						usubgrps1=usubgrps1.substring(0,(usubgrps1.length()-1));
						System.err.println("Subgrps1:>> "+usubgrps1);
						*/
						//////////////////////////////////////////////////////////////////
						//journal accountdetails
						//Calling updateJournalAccInsertPlus 
					/*	int uxz=journal_accounttransactionRepository.updateJournalAccInsertPlus(sales_Invoice.getBusiness_unit(),sales_Invoice.getInvoice_date(),voucherno,voucherno,
								udledgerid,urcptAcc.getAmount(),"accDtls.getNarration",ldt,sales_Invoice.getUsername(),"UPDATED",sales_Invoice.getFin_year(),usubgrps1,udledgerid.substring(0, 2),"0",0.00);	
							
						System.err.println("Update Status: "+uxz);
					*/	//////////////////////////////////////////////////////////////////
				//}
				/***********Update Receipt Accountdetails End**********************************************/
				
				/*********** Receipt Accountdetails Creation Start**********************************************/
				/*for(ReceiptAccDetails rcptAcc : rcptAccs) {
					System.err.println("Got Final Filter Ledger: "+rcptAcc.getDlegderid()+" Amount: "+rcptAcc.getDamount());
					String dledgerid="";
					if(Utility.isNullOrEmpty(rcptAcc.getDlegderid())) {
						dledgerid=ledgermasterRepository.getLedgerDetails(rcptAcc.getDlegderid()).getLedgerid();
					}
						//accountsInsertionPlus
						String[] sps=findParentSubGroupCode(dledgerid,sinvoice.getBusiness_unit()).split("x@nk!tltdx");
						String subgrps1="";	
						for (int i = 0; i < sps.length; i++) {
							if(sps[i].length()==5 && sps[i].trim() !="") {
								subgrps1 +=sps[i]+",";
							}else if(sps[i].length()==2 && sps[i].trim() !="") {
								subgrps1 +=sps[i]+",";
							}else {}
						}
						subgrps1=subgrps1.substring(0,(subgrps1.length()-1));
						System.err.println("Subgrps1:>> "+subgrps1);
					*/	
						//////////////////////////////////////////////////////////////////
						//journal accountdetails
						
					/*	int xz=journal_accounttransactionRepository.callJournalAccInsertPlus(sinvoice.getBusiness_unit(),sinvoice.getInvoice_date(),finalcode,voucherno,
								dledgerid,rcptAcc.getDamount(),"accDtls.getNarration",ldt,sinvoice.getUsername(),sinvoice.getFin_year(),subgrps1,dledgerid.substring(0, 2),"0",0.00,sales_Invoice.getInvoice_id(),sales_Invoice.getInvoice_no());	
							
						System.err.println("Update Status: "+xz);
					*/	//////////////////////////////////////////////////////////////////
				//}
			//02062022 ends here
				/*********** Receipt Accountdetails Creation End**********************************************/
				/************* Tax Entry Start *********************************/
			if(sinvoice.isJobwork()) 
			{
				sinvoice.getItem_groupwise_hsnsumm().clear();
				sinvoice.getSales_Invoice_Item_Dtls().clear();
				sinvoice.getItem_groupwise_summ().clear();
				
				sales_InvoiceRepository.invoiceJobItemupdate(op.get().getInvoice_id(),"UPDATED");
				
				Set<Sales_Invoice_Item_Dtls_for_jobwork> jobSet = new HashSet<Sales_Invoice_Item_Dtls_for_jobwork>();
				jobSet.addAll(sinvoice.getSales_Invoice_Item_Dtls_for_jobwork());
				for(Sales_Invoice_Item_Dtls_for_jobwork jobDts : jobSet)
				{
					jobDts.setInvoice_id(sinvoice.getInvoice_id());	
					jobDts.setInvoice_no(sinvoice.getInvoice_no());
					
					if(Utility.isNullOrEmpty(jobDts.getJob_item())) {
						jobDts.setJob_item_name(item_masterRepository.itemNameById(jobDts.getJob_item()).getItem_name());
					}else{jobDts.setJob_item_name("0");};
					
					if(Utility.isNullOrEmpty(jobDts.getJob_packing())){
						jobDts.setJob_packing_name(item_masterRepository.itemNameById(jobDts.getJob_packing()).getItem_name());
					}else{jobDts.setJob_packing_name("0");}
					
					jobDts.setCompany_id(sinvoice.getCompany_id());
					jobDts.setFin_year(sinvoice.getFin_year());
					jobDts.setModified_type("INSERTED");
					jobDts.setInserted_by(sinvoice.getInserted_by());
					jobDts.setInserted_on(sinvoice.getInserted_on());
					jobDts.setUpdated_by(userRepository.getUserDetails(sinvoice.getUsername()).getName());
					jobDts.setUpdated_on(ldt);
					jobDts.setDeleted_by("NA");
					jobDts.setDeleted_on(ldt);
					
					
				}
				sinvoice.setSales_Invoice_Item_Dtls_for_jobwork(jobSet);
				
				sales_InvoiceRepository.invoiceServiceItemupdate(op.get().getInvoice_id(),"UPDATED");
				
				Set<Sales_Invoice_Item_Dtls_for_jobwork_price> serviceItem = new HashSet<Sales_Invoice_Item_Dtls_for_jobwork_price>();
				serviceItem.addAll(sinvoice.getSales_Invoice_Item_Dtls_for_jobwork_price());
				for(Sales_Invoice_Item_Dtls_for_jobwork_price service : serviceItem)
				{
					service.setInvoice_id(sinvoice.getInvoice_id());	
					service.setInvoice_no(sinvoice.getInvoice_no());
					
					if(Utility.isNullOrEmpty(service.getItem_service())) {
						service.setItem_service_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getService_name());
					}else{service.setItem_service_name("0");};
					
					if(Utility.isNullOrEmpty(service.getTaxcode())) {
						service.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(service.getTaxcode()).getTax_name());
					}else{service.setTaxcode_name("0");};
					
					if(Utility.isNullOrEmpty(service.getItem_service())) {
						service.setItem_service_acc_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getAc_ledger_name());
					}else{service.setItem_service_acc_name("0");};
					
					service.setCompany_id(sinvoice.getCompany_id());
					service.setFin_year(sinvoice.getFin_year());
					service.setModified_type("INSERTED");
					service.setInserted_by(sinvoice.getInserted_by());
					service.setInserted_on(sinvoice.getInserted_on());
					service.setUpdated_by(userRepository.getUserDetails(sinvoice.getUsername()).getName());
					service.setUpdated_on(ldt);
					service.setDeleted_by("NA");
					service.setDeleted_on(ldt);
				}
				sinvoice.setSales_Invoice_Item_Dtls_for_jobwork_price(serviceItem);
				
			}
			else
			{
				
				System.out.println("sinvoice.isJobwork(): "+sinvoice.isJobwork());
				sinvoice.getSales_Invoice_Item_Dtls_for_jobwork().clear();
				sinvoice.getSales_Invoice_Item_Dtls_for_jobwork_price().clear();
				
				item_groupwise_summRepository.updateItem_groupwise_summ(sales_Invoice.getInvoice_id(),"UPDATED");
				System.out.println("Enter Item groupwise Sum Table");
				Set<Item_groupwise_summ> iSummSet=new HashSet<Item_groupwise_summ>();
				iSummSet.addAll(sinvoice.getItem_groupwise_summ());
				for(Item_groupwise_summ iSumm:iSummSet) 
				{
					System.out.println("Enter Item groupwise 1 Sum Table");
					iSumm.setInvoice_id(sales_Invoice.getInvoice_id());	
					iSumm.setFin_year(sinvoice.getFin_year());
					iSumm.setCompany_id(sinvoice.getCompany_id());
					iSumm.setUsername(sinvoice.getUsername());
					iSumm.setModified_type("INSERTED");
					iSumm.setInserted_by(sinvoice.getInserted_by());
					iSumm.setInserted_on(sinvoice.getInserted_on());
					iSumm.setUpdated_by(sinvoice.getUpdated_by());
					iSumm.setUpdated_on(sinvoice.getUpdated_on());
					iSumm.setDeleted_by(sinvoice.getDeleted_by());
					iSumm.setDeleted_on(sinvoice.getDeleted_on());
				}
				sinvoice.setItem_groupwise_summ(iSummSet);
				
				item_groupwise_hsnsummRepository.updateItem_groupwise_hsnsumm(sales_Invoice.getInvoice_id(),"UPDATED");
				System.out.println("Enter Item hsn Sum Table");
				Set<Item_groupwise_hsnsumm> iHsnSummSet=new HashSet<Item_groupwise_hsnsumm>();
				iHsnSummSet.addAll(sinvoice.getItem_groupwise_hsnsumm());
				for(Item_groupwise_hsnsumm iHsnSumm:iHsnSummSet) 
				{
					System.out.println("Enter Item hsn 1 Sum Table");
					iHsnSumm.setInvoice_id(sales_Invoice.getInvoice_id());
					iHsnSumm.setFin_year(sinvoice.getFin_year());
					iHsnSumm.setCompany_id(sinvoice.getCompany_id());
					iHsnSumm.setUsername(sinvoice.getUsername());
					iHsnSumm.setModified_type("INSERTED");
					iHsnSumm.setInserted_by(sinvoice.getInserted_by());
					iHsnSumm.setInserted_on(sinvoice.getInserted_on());
					iHsnSumm.setUpdated_by(sinvoice.getUpdated_by());
					iHsnSumm.setUpdated_on(sinvoice.getUpdated_on());
					iHsnSumm.setDeleted_by(sinvoice.getDeleted_by());
					iHsnSumm.setDeleted_on(sinvoice.getDeleted_on());
				}
				sinvoice.setItem_groupwise_hsnsumm(iHsnSummSet);
				
				sales_Invoice_Item_DtlsRepository.sales_Invoice_Item_Dtlsupdate(sales_Invoice.getInvoice_id(),"UPDATED");
				
				Set<Sales_Invoice_Item_Dtls> salesInvItmDtlSet=new HashSet<Sales_Invoice_Item_Dtls>();
				salesInvItmDtlSet.addAll(sinvoice.getSales_Invoice_Item_Dtls());
				for(Sales_Invoice_Item_Dtls itmDtls:salesInvItmDtlSet) 
				{
					itmDtls.setInvoice_id(sales_Invoice.getInvoice_id());	
					itmDtls.setInvoice_no(sales_Invoice.getInvoice_no());
					itmDtls.setItem_name(item_masterRepository.itemNameById(itmDtls.getItem_code()).getItem_name());
					if(Utility.isNullOrEmpty(itmDtls.getPacking())) {
						itmDtls.setPacking_name(item_masterRepository.itemNameById(itmDtls.getPacking()).getItem_name());
					}else {itmDtls.setPacking_name("None");}
					
					itmDtls.setTax_codename(tax_code_detailsRepository.getTaxCodeDetails(itmDtls.getTax_code()).getTax_name());
					itmDtls.setItem_groupname(item_group_masterRepository.getGroupDtls_converter(itmDtls.getItem_group()).getGroup_name());
					
					
					
					itmDtls.setUsername(sinvoice.getUsername());
					itmDtls.setFin_year(sinvoice.getFin_year());
					itmDtls.setCompany_id(sinvoice.getCompany_id());
					itmDtls.setModified_type("INSERTED");
					itmDtls.setInserted_by(sinvoice.getInserted_by());
					itmDtls.setInserted_on(sinvoice.getInserted_on());
					itmDtls.setUpdated_by(sinvoice.getUpdated_by());
					itmDtls.setUpdated_on(sinvoice.getUpdated_on());
					itmDtls.setDeleted_by("NA");
					itmDtls.setDeleted_on(ldt);
				}
				sinvoice.setSales_Invoice_Item_Dtls(salesInvItmDtlSet);
			}
					

				item_groupwise_taxsummRepository.updateItem_groupwise_taxsumm(sales_Invoice.getInvoice_id(),"UPDATED");
				
				Set<Item_groupwise_taxsumm> iTaxSummSet=new HashSet<Item_groupwise_taxsumm>();
				iTaxSummSet.addAll(sinvoice.getItem_groupwise_taxsumm());
				System.out.println("Enter Item Tax Sum Table");
				for(Item_groupwise_taxsumm itaxSumm:iTaxSummSet) 
				{
					System.out.println("Enter Item Tax 1 Sum Table");
					itaxSumm.setInvoice_id(sales_Invoice.getInvoice_id());
					itaxSumm.setFin_year(sinvoice.getFin_year());
					itaxSumm.setCompany_id(sinvoice.getCompany_id());
					itaxSumm.setUsername(sinvoice.getUsername());
					itaxSumm.setModified_type("INSERTED");
					itaxSumm.setInserted_by(sinvoice.getInserted_by());
					itaxSumm.setInserted_on(sinvoice.getInserted_on());
					itaxSumm.setUpdated_by(sinvoice.getUpdated_by());
					itaxSumm.setUpdated_on(sinvoice.getUpdated_on());
					itaxSumm.setDeleted_by(sinvoice.getDeleted_by());
					itaxSumm.setDeleted_on(sinvoice.getDeleted_on());
				}
				sinvoice.setItem_groupwise_taxsumm(iTaxSummSet);
				
				
			
			
			/************ Tax Entry End **************************************/
				
			
			
			sales_Invoice_Tax_InfoRepository.sales_Invoice_Tax_Infoupdate(sales_Invoice.getInvoice_id(),"UPDATED");
			
			Set<Sales_Invoice_Tax_Info> taxInfoSet=new HashSet<Sales_Invoice_Tax_Info>();
			taxInfoSet.add(sinvoice.getSales_Invoice_Tax_Info());
			for(Sales_Invoice_Tax_Info taxInfo:taxInfoSet) 
			{
				taxInfo.setInvoice_id(sales_Invoice.getInvoice_id());	
				taxInfo.setInvoice_no(sales_Invoice.getInvoice_no());
				taxInfo.setUsername(sinvoice.getUsername());
				taxInfo.setFin_year(sinvoice.getFin_year());
				taxInfo.setCompany_id(sinvoice.getCompany_id());
				taxInfo.setModified_type("INSERTED");
				taxInfo.setInserted_by(sinvoice.getInserted_by());
				taxInfo.setInserted_on(sinvoice.getInserted_on());
				taxInfo.setUpdated_by(sinvoice.getUpdated_by());
				taxInfo.setUpdated_on(sinvoice.getUpdated_on());
				taxInfo.setDeleted_by("NA");
				taxInfo.setDeleted_on(ldt);
			}
			if(!taxInfoSet.isEmpty()) {
				sinvoice.setSales_Invoice_Tax_Info(taxInfoSet.iterator().next());
			}
			
			sales_Invoice_Broker_DtlsRepository.sales_Invoice_Broker_Dtlsupdate(sales_Invoice.getInvoice_id(),"UPDATED");
			
			Set<Sales_Invoice_Broker_Dtls> salesInvBrkDtlSet=new HashSet<Sales_Invoice_Broker_Dtls>();
			salesInvBrkDtlSet.addAll(sinvoice.getSales_Invoice_Broker_Dtls());
			for(Sales_Invoice_Broker_Dtls invBrkDtls:salesInvBrkDtlSet) 
			{
				invBrkDtls.setInvoice_id(sales_Invoice.getInvoice_id());	
				invBrkDtls.setInvoice_no(sales_Invoice.getInvoice_no());
				
				//invBrkDtls.setBroker_name(cust_bussiness_partner_brokerRepository.custBrokerRetriveListNew(invBrkDtls.getBrokercode()).getVen_name());
				invBrkDtls.setBroker_name(broker_masterRepository.brokerNameByCode(invBrkDtls.getBrokercode()).getName());
				
				invBrkDtls.setUsername(sinvoice.getUsername());
				invBrkDtls.setFin_year(sinvoice.getFin_year());
				invBrkDtls.setCompany_id(sinvoice.getCompany_id());
				invBrkDtls.setModified_type("INSERTED");
				invBrkDtls.setInserted_by(sinvoice.getInserted_by());
				invBrkDtls.setInserted_on(sinvoice.getInserted_on());
				invBrkDtls.setUpdated_by(sinvoice.getUpdated_by());
				invBrkDtls.setUpdated_on(sinvoice.getUpdated_on());
				invBrkDtls.setDeleted_by("NA");
				invBrkDtls.setDeleted_on(ldt);
			}
			sinvoice.setSales_Invoice_Broker_Dtls(salesInvBrkDtlSet);
			
			sales_Invoice_Trans_DtlsRepository.sales_Invoice_Trans_DtlsUpdate(sales_Invoice.getInvoice_id(),"UPDATED");
			
			Set<Sales_Invoice_Trans_Dtls> salesTransSet=new HashSet<Sales_Invoice_Trans_Dtls>();
			salesTransSet.addAll(sinvoice.getSales_Invoice_Trans_Dtls());
			for(Sales_Invoice_Trans_Dtls invTransDtls:salesTransSet) 
			{
				if(Utility.isNullOrEmpty(invTransDtls.getVehicleid())) {
					invTransDtls.setVehicleno(vehicleMasterRepository.getVehicleDetails(invTransDtls.getVehicleid()).getVehicle_no());
				}else {invTransDtls.setVehicleno("0");}
				
				if(Utility.isNullOrEmpty(invTransDtls.getTransname())) 
				{
					if(invTransDtls.getTransname().compareToIgnoreCase("0")==0)
					{
						invTransDtls.setTransportername("0");
					}
					else
					{
						invTransDtls.setTransportername(trans_bussiness_partnerRepository.bpNameById(invTransDtls.getTransname()).getBp_name());
					}
				}
				else{invTransDtls.setTransportername("0");};
				
				invTransDtls.setInvoice_id(sales_Invoice.getInvoice_id());	
				invTransDtls.setInvoice_no(sales_Invoice.getInvoice_no());
				invTransDtls.setUsername(sinvoice.getUsername());
				invTransDtls.setFin_year(sinvoice.getFin_year());
				invTransDtls.setCompany_id(sinvoice.getCompany_id());
				invTransDtls.setModified_type("INSERTED");
				invTransDtls.setInserted_by(sinvoice.getInserted_by());
				invTransDtls.setInserted_on(sinvoice.getInserted_on());
				invTransDtls.setUpdated_by(sinvoice.getUpdated_by());
				invTransDtls.setUpdated_on(sinvoice.getUpdated_on());
				invTransDtls.setDeleted_by("NA");
				invTransDtls.setDeleted_on(ldt);
			}
			sinvoice.setSales_Invoice_Trans_Dtls(salesTransSet);
			
			sales_Invoice_Shipment_DtlsRepository.sales_Invoice_Shipment_DtlsUpdate(sales_Invoice.getInvoice_id(),"UPDATED");
			
			Set<Sales_Invoice_Shipment_Dtls> invShipSet=new HashSet<Sales_Invoice_Shipment_Dtls>();
			invShipSet.add(sinvoice.getSales_Invoice_Shipment_Dtls());
			for(Sales_Invoice_Shipment_Dtls invShipDtls:invShipSet) 
			{
				invShipDtls.setInvoice_id(sales_Invoice.getInvoice_id());	
				invShipDtls.setInvoice_no(sales_Invoice.getInvoice_no());
				invShipDtls.setUsername(sinvoice.getUsername());
				invShipDtls.setFin_year(sinvoice.getFin_year());
				invShipDtls.setCompany_id(sinvoice.getCompany_id());
				invShipDtls.setModified_type("INSERTED");
				invShipDtls.setInserted_by(sinvoice.getInserted_by());
				invShipDtls.setInserted_on(sinvoice.getInserted_on());
				invShipDtls.setUpdated_by(sinvoice.getUpdated_by());
				invShipDtls.setUpdated_on(sinvoice.getUpdated_on());
				invShipDtls.setDeleted_by("NA");
				invShipDtls.setDeleted_on(ldt);
			}
			if(!invShipSet.isEmpty()) {
				sinvoice.setSales_Invoice_Shipment_Dtls(invShipSet.iterator().next());
			}
			
			sales_Invoice_Payment_DtlsRepository.sales_Invoice_Payment_DtlsUpdate(sales_Invoice.getInvoice_id(),"UPDATED");
			
			Set<Sales_Invoice_Payment_Dtls> invPaySet=new HashSet<Sales_Invoice_Payment_Dtls>();
			invPaySet.add(sinvoice.getSales_Invoice_Payment_Dtls());
			for(Sales_Invoice_Payment_Dtls invPayDtls:invPaySet) 
			{
				invPayDtls.setInvoice_id(sales_Invoice.getInvoice_id());	
				invPayDtls.setInvoice_no(sales_Invoice.getInvoice_no());
				invPayDtls.setUsername(sinvoice.getUsername());
				invPayDtls.setFin_year(sinvoice.getFin_year());
				invPayDtls.setCompany_id(sinvoice.getCompany_id());
				invPayDtls.setModified_type("INSERTED");
				invPayDtls.setInserted_by(sinvoice.getInserted_by());
				invPayDtls.setInserted_on(sinvoice.getInserted_on());
				invPayDtls.setUpdated_by(sinvoice.getUpdated_by());
				invPayDtls.setUpdated_on(sinvoice.getUpdated_on());
				invPayDtls.setDeleted_by("NA");
				invPayDtls.setDeleted_on(ldt);
			}
			if(!invPaySet.isEmpty()) {
				sinvoice.setSales_Invoice_Payment_Dtls(invPaySet.iterator().next());
			}  
			
			sales_Invoice_BP_DtlsRepository.sales_Invoice_BP_Dtlsupdate(sales_Invoice.getInvoice_id(),"UPDATED");
			
			Set<Sales_Invoice_BP_Dtls> invBpDtlsSet=new HashSet<Sales_Invoice_BP_Dtls>();
			invBpDtlsSet.add(sinvoice.getSales_Invoice_BP_Dtls());
			for(Sales_Invoice_BP_Dtls invBpDtls:invBpDtlsSet) 
			{
				invBpDtls.setInvoice_id(sales_Invoice.getInvoice_id());	
				invBpDtls.setInvoice_no(sales_Invoice.getInvoice_no());
				invBpDtls.setUsername(sinvoice.getUsername());
				invBpDtls.setFin_year(sinvoice.getFin_year());
				invBpDtls.setCompany_id(sinvoice.getCompany_id());
				invBpDtls.setModified_type("INSERTED");
				invBpDtls.setInserted_by(sinvoice.getInserted_by());
				invBpDtls.setInserted_on(sinvoice.getInserted_on());
				invBpDtls.setUpdated_by(sinvoice.getUpdated_by());
				invBpDtls.setUpdated_on(sinvoice.getUpdated_on());
				invBpDtls.setDeleted_by("NA");
				invBpDtls.setDeleted_on(ldt);
			}
			if(!invBpDtlsSet.isEmpty())	{
				sinvoice.setSales_Invoice_BP_Dtls(invBpDtlsSet.iterator().next());
			}
			
			//sales_Invoice_DocsRepository.sales_Invoice_Docsupdate(sales_Invoice.getInvoice_id(),"UPDATED");
			
			Set<Sales_Invoice_Docs> salesInvDocSet=new HashSet<Sales_Invoice_Docs>();
			salesInvDocSet.addAll(sinvoice.getSales_Invoice_Docs());
			int g=0;
			for(Sales_Invoice_Docs invDocs:salesInvDocSet) 
			{
				System.out.println(" hello files : "+files.length);
				//here start
				
				if(files.length > 0) {
					try {
						System.out.println("files[g] :: "+g+" / "+files[g]);
							//fileUpload(files[i],gen_sno+"_");
						
						invDocs.setDoc_pdf(fileUpload(files[g],sales_Invoice.getInvoice_id()+"_"));
						invDocs.setDoc_file_name(sales_Invoice.getInvoice_id()+"_"+files[g].getOriginalFilename());
						
					g++;
					}
					catch (IOException e)
					{
						System.out.println(e);
						}
					
				}
				//System.out.println("3 :: ");
				invDocs.setInvoice_id(sales_Invoice.getInvoice_id());	
				invDocs.setInvoice_no(sales_Invoice.getInvoice_no());
				invDocs.setUsername(sinvoice.getUsername());
				invDocs.setFin_year(sinvoice.getFin_year());
				invDocs.setCompany_id(sinvoice.getCompany_id());
				invDocs.setModified_type("INSERTED");
				invDocs.setInserted_by(sinvoice.getInserted_by());
				invDocs.setInserted_on(sinvoice.getInserted_on());
				invDocs.setUpdated_by(sinvoice.getUpdated_by());
				invDocs.setUpdated_on(sinvoice.getUpdated_on());
				invDocs.setDeleted_by("NA");
				invDocs.setDeleted_on(ldt);
			}
			sinvoice.setSales_Invoice_Docs(salesInvDocSet);
			
			//Update Delivery Challan *****
			
			
	
			//Invoice_accountdetails
			//02062022 starts here
			/*int p=invoice_accountdetailsRepository.updateInvoice_accountdetails(sinvoice.getInvoice_id(),"UPDATED");
			
			Invoice_accountdetails partyAccDtls=new Invoice_accountdetails(sales_Invoice.getInvoice_id(),sales_Invoice.getInvoice_no(),sinvoice.getBusiness_unit(),unitname,sinvoice.getParty(),sinvoice.getPartyname(),sinvoice.getInvoice_date(),(sinvoice.getPayable_amt()*-1),"I",transtype,voucherno,sinvoice.getModified_type(),sinvoice.getInserted_on(),sinvoice.getInserted_by(),sinvoice.getFin_year(),sinvoice.getUsername());
			Invoice_accountdetails accDtls=new Invoice_accountdetails(sales_Invoice.getInvoice_id(),sales_Invoice.getInvoice_no(),sinvoice.getBusiness_unit(),unitname,sinvoice.getPayable_amt_gl_ac(),ledgermasterRepository.getLedgerDetails(sinvoice.getPayable_amt_gl_ac()).getLedgername(),sinvoice.getInvoice_date(),sinvoice.getPayable_amt(),"A",transtype,voucherno,sinvoice.getModified_type(),sinvoice.getInserted_on(),sinvoice.getInserted_by(),sinvoice.getFin_year(),sinvoice.getUsername());
			invoice_accountdetailsRepository.save(partyAccDtls);
			invoice_accountdetailsRepository.save(accDtls);
			*/
			//02062022 ends here
			return sales_InvoiceRepository.save(sinvoice);
		}	
	}
	
	@Transactional
	public Sales_Invoice deleteSalesInv(Sales_Invoice sInvoice,Long id) {
		Optional<Sales_Invoice> op = sales_InvoiceRepository.findById(id);
		Sales_Invoice sinvoice=sales_InvoiceRepository.findSalesInvoiceDtls(id);
		LocalDateTime ldt = LocalDateTime.now();
		Sales_Invoice Sales_InvoiceStatus=new Sales_Invoice();
		
		if(op.get().isPayment_status() == true) {
			System.err.println("Delete is not Possible !!!");
			return Sales_InvoiceStatus;
		}else {
			System.err.println("Delete is Continue !!!");
			if(op.isPresent()) {
				sinvoice.setId(id);
			}
			
			sinvoice.setModified_type("DELETED");
			sinvoice.setInserted_by(sinvoice.getInserted_by());
			sinvoice.setInserted_on(sinvoice.getInserted_on());
			sinvoice.setUpdated_by(sinvoice.getUpdated_by());
			sinvoice.setUpdated_on(sinvoice.getUpdated_on());
			sinvoice.setDeleted_by(userRepository.getUserDetails(sinvoice.getUsername()).getName());
			sinvoice.setDeleted_on(ldt);
			sinvoice.setPayment_status(false);
			
			//account posting off on 21/06/2022
			/*String voucherno=outstandingledgerRepository.getOutstandingledgerDtls(sinvoice.getInvoice_id()).getVoucherno();
			
			String[] usp=findParentSubGroupCode(sinvoice.getPayable_amt_gl_ac(),sinvoice.getBusiness_unit()).split("x@nk!tltdx");
			String usubgrps="";	
			for (int i = 0; i < usp.length; i++) 
			{
				if(usp[i].length()==5 && usp[i].trim() !="") {
					usubgrps +=usp[i]+",";
				}else if(usp[i].length()==2 && usp[i].trim() !="") {
					usubgrps +=usp[i]+",";
				}else {}
			}
			usubgrps=usubgrps.substring(0,(usubgrps.length()-1));
			
			//Update Journal_accounttransaction
			//Calling updateJournalAccount with adding voucherno
			//System.out.println("Got Ledger: "+sinvoice.getPayable_amt_gl_ac()+" Amt: "+sinvoice.getPayable_amt()+" Grp: "+sinvoice.getPayable_amt_gl_ac().substring(0, 2)+" Subgrps: "+usubgrps);
			int uxy=journal_accounttransactionRepository.updateJournalAccount(sinvoice.getBusiness_unit(), sinvoice.getInvoice_date(),
					voucherno, "rAccTran.getReferenceno", "0000-00-00", sinvoice.getPayable_amt_gl_ac(),
					sinvoice.getPayable_amt(), "0000-00-00","DELETED",ldt,userRepository.getUserDetails(sinvoice.getUsername()).getName(),
					sinvoice.getFin_year(), sinvoice.getUsername(),"rAccTran.getNarration","rAccTran.getNarration_dtls",voucherno,
					usubgrps,sinvoice.getPayable_amt_gl_ac().substring(0, 2));
			
			//Calling updateOutstandingledger with adding voucherno,invoice_id
			int us=journal_accounttransactionRepository.updateOutstandingledger(sinvoice.getBusiness_unit(),sinvoice.getPayable_amt_gl_ac(),voucherno,
					sinvoice.getInvoice_id(),sinvoice.getInvoice_date(),sinvoice.getPayable_amt(),sinvoice.getPayable_amt(),"dr",sinvoice.getFin_year(),
					"DELETED",ldt,sinvoice.getUsername(),sinvoice.getParty(),sinvoice.getPartyname(),"unitname");
			*/
			/***********Update Receipt Accountdetails Start**********************************************/
			/*List<Account_details> accDetails = new ArrayList<Account_details>();
			accDetails.addAll(account_detailsRepository.getAccount_details(voucherno,sinvoice.getPayable_amt_gl_ac()));
			
			for(Account_details urcptAcc : accDetails) {
				System.err.println("Got Final Filter Ledger: "+urcptAcc.getLedgerid()+" Amount: "+urcptAcc.getAmount());
				String udledgerid="";
				if(Utility.isNullOrEmpty(urcptAcc.getLedgerid())) {
					udledgerid=ledgermasterRepository.getLedgerDetails(urcptAcc.getLedgerid()).getLedgerid();
				}
					//accountsInsertionPlus
					String[] usps=findParentSubGroupCode(udledgerid,sinvoice.getBusiness_unit()).split("x@nk!tltdx");
					String usubgrps1="";	
					for (int i = 0; i < usps.length; i++) {
						if(usps[i].length()==5 && usps[i].trim() !="") {
							usubgrps1 +=usps[i]+",";
						}else if(usps[i].length()==2 && usps[i].trim() !="") {
							usubgrps1 +=usps[i]+",";
						}else {}
					}
					usubgrps1=usubgrps1.substring(0,(usubgrps1.length()-1));
					System.err.println("Subgrps1:>> "+usubgrps1);
					
					//////////////////////////////////////////////////////////////////
					//journal accountdetails
					//Calling updateJournalAccInsertPlus 
					int uxz=journal_accounttransactionRepository.updateJournalAccInsertPlus(sinvoice.getBusiness_unit(),sinvoice.getInvoice_date(),voucherno,voucherno,
							udledgerid,urcptAcc.getAmount(),"accDtls.getNarration",ldt,sinvoice.getUsername(),sinvoice.getModified_type(),sinvoice.getFin_year(),usubgrps1,udledgerid.substring(0, 2),"0",0.00);	
						
					System.err.println("Update Status: "+uxz);
					//////////////////////////////////////////////////////////////////
			}*/
			/***********Update Receipt Accountdetails End**********************************************/
			if(op.get().isJobwork()==true)
			{
				sales_InvoiceRepository.invoiceJobItemupdate(op.get().getInvoice_id(),"DELETED");
				sales_InvoiceRepository.invoiceServiceItemupdate(op.get().getInvoice_id(),"DELETED");
			}
			else
			{
				sales_Invoice_Item_DtlsRepository.sales_Invoice_Item_Dtlsupdate(sinvoice.getInvoice_id(),sinvoice.getModified_type());
				item_groupwise_summRepository.updateItem_groupwise_summ(sinvoice.getInvoice_id(),sinvoice.getModified_type());
				item_groupwise_hsnsummRepository.updateItem_groupwise_hsnsumm(sinvoice.getInvoice_id(),sinvoice.getModified_type());
			}
			
			
			item_groupwise_taxsummRepository.updateItem_groupwise_taxsumm(sinvoice.getInvoice_id(),sinvoice.getModified_type());
			
			sales_Invoice_Tax_InfoRepository.sales_Invoice_Tax_Infoupdate(sinvoice.getInvoice_id(),sinvoice.getModified_type());
			sales_Invoice_Broker_DtlsRepository.sales_Invoice_Broker_Dtlsupdate(sinvoice.getInvoice_id(),sinvoice.getModified_type());
			sales_Invoice_Trans_DtlsRepository.sales_Invoice_Trans_DtlsUpdate(sinvoice.getInvoice_id(),sinvoice.getModified_type());
			sales_Invoice_Shipment_DtlsRepository.sales_Invoice_Shipment_DtlsUpdate(sinvoice.getInvoice_id(),sinvoice.getModified_type());
			sales_Invoice_Payment_DtlsRepository.sales_Invoice_Payment_DtlsUpdate(sinvoice.getInvoice_id(),sinvoice.getModified_type());
			sales_Invoice_BP_DtlsRepository.sales_Invoice_BP_Dtlsupdate(sinvoice.getInvoice_id(),sinvoice.getModified_type());
			sales_Invoice_DocsRepository.sales_Invoice_Docsupdate(sinvoice.getInvoice_id(),sinvoice.getModified_type());
			
			//Update Delivery Challan *****
			//delivery_challanRepository.updateDelvChallanStatus(sinvoice.getReference_id(),"No");
			if(sinvoice.getChallan().compareToIgnoreCase("Multiple")==0) 
			{
				String splitdeliveryid[]=sinvoice.getReference_id().split(",");
				
				
				for(int i=0;i<splitdeliveryid.length;i++) 
				{
					delivery_challanRepository.updateDelvChallanStatus(splitdeliveryid[i],"No","NA","NA");	
				}
				
			}
			else 
			{	
				delivery_challanRepository.updateDelvChallanStatus(sinvoice.getReference_id(),"No","NA","NA");
				
			}
			
			//account posting off on 21/06/2022
			//Invoice_accountdetails
			//invoice_accountdetailsRepository.updateInvoice_accountdetails(sinvoice.getInvoice_id(),sinvoice.getModified_type());
			
			return sales_InvoiceRepository.save(sinvoice);
		}
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
		//System.err.println("Parent: "+parent);
		if(parent.compareTo("") !=0)
		{
			//System.err.println("Get values:>>>>>>>> Parent: "+parent+" ,Branch: "+bunit);
			if(subgroupmasterRepository.getParentSubGroup(parent).isPresent()) {
				x1 = subgroupmasterRepository.getParentSubGroup(parent).get().getParent_subgroupcode();
			}
			//System.err.println("Parent x1: "+x1);
			subcode.add(x1);
		}	
		if(x1.compareTo("") !=0)
		{
			if(subgroupmasterRepository.getParentSubGroup(x1).isPresent()) {
				x2 = subgroupmasterRepository.getParentSubGroup(x1).get().getParent_subgroupcode();
			}
			//System.err.println("Parent x2: "+x2);
			subcode.add(x2);
		}
		if(x2.compareTo("") !=0)
		{
			if(subgroupmasterRepository.getParentSubGroup(x2).isPresent()) {
				x3 = subgroupmasterRepository.getParentSubGroup(x2).get().getParent_subgroupcode();
			}
			//System.err.println("Parent x3: "+x3);
			subcode.add(x3);
		}
		if(x3.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x3).isPresent()) {
				x4 = subgroupmasterRepository.getParentSubGroup(x3).get().getParent_subgroupcode();
			}
			//System.err.println("Parent x4: "+x4);
			subcode.add(x4);
		}
		if(x4.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x4).isPresent()) {
				x5 = subgroupmasterRepository.getParentSubGroup(x4).get().getParent_subgroupcode();
			}
			//System.err.println("Parent x5: "+x5);
			subcode.add(x5);
		}
		if(x5.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x5).isPresent()) {
				x6 = subgroupmasterRepository.getParentSubGroup(x5).get().getParent_subgroupcode();
			}
			//System.err.println("Parent x6: "+x6);
			subcode.add(x6);
		}
		if(x6.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x6).isPresent()) {
				x7 = subgroupmasterRepository.getParentSubGroup(x6).get().getParent_subgroupcode();
			}
			//System.err.println("Parent x7: "+x7);
			subcode.add(x7);
		}	
		if(x7.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x7).isPresent()) {
				x8 = subgroupmasterRepository.getParentSubGroup(x7).get().getParent_subgroupcode();
			}
			//System.err.println("Parent x8: "+x8);
			subcode.add(x8);
		}
		if(x8.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x8).isPresent()) {
				x9 = subgroupmasterRepository.getParentSubGroup(x8).get().getParent_subgroupcode();
			}
			//System.err.println("Parent x9: "+x9);
			subcode.add(x9);
		}
		if(x9.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x9).isPresent()) {
				x10 = subgroupmasterRepository.getParentSubGroup(x9).get().getParent_subgroupcode();
			}
		//	System.err.println("Parent x10: "+x10);
			subcode.add(x10);
		}
		if(x10.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(x10).isPresent()) {
				x11 = subgroupmasterRepository.getParentSubGroup(x10).get().getParent_subgroupcode();
			}
		//	System.err.println("Parent x11: "+x11);
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
	
	public List<Sales_InvoiceDTO> getSalesInvoice(String company)
	{
		List<Sales_Invoice> sInvList=new ArrayList<Sales_Invoice>();
		sInvList.addAll(sales_InvoiceRepository.findSalesInvoices(company));
		
		Type listType=new TypeToken<List<Sales_InvoiceDTO>>() {}.getType();
		List<Sales_InvoiceDTO> salesInvList=new ModelMapper().map(sInvList,listType);
		
		salesInvList.forEach((sInv) -> {
			if(Utility.isNullOrEmpty(sInv.getInvoice_type())) {
				sInv.setInvoice_type(invoice_typeRepository.getSalesInvTypesDtls(sInv.getInvoice_type()).getInvtype_name());
			}
			else {sInv.setInvoice_type("None");}
		});
		return salesInvList;
	}
	
	
	public Page<Sales_Invoice_Pagination_DTO> getSales_Invoice_pagination(int page,int size)
	{
		//PageRequest pageRequest = PageRequest.of(page, size,Sort.by("id").descending());
		PageRequest pageRequest = PageRequest.of(page, size,Sort.by("invoicedate").descending().and(Sort.by("invoiceno")).descending());
	    Page<Sales_Invoice> pageResult = sales_InvoiceRepository.findAll(pageRequest);
	    
	    
	    List<Sales_Invoice_Pagination_DTO> sInvList = pageResult
	    	      .stream()
	    	      .map((Sales_Invoice sales_Invoice) -> new Sales_Invoice_Pagination_DTO(
	    	    		  sales_Invoice.getId(),
	    	    		  sales_Invoice.getInvoice_id(),
	    	    		  sales_Invoice.getInvoice_no(),
	    	    		  sales_Invoice.getInvoice_date(),
	    	    		  sales_Invoice.getInvoice_type(),
	    	    		  sales_Invoice.getParty(),
	    	    		  sales_Invoice.getPartyname(),
	    	    		  sales_Invoice.getReturn_approval_status(),
	    	    		  sales_Invoice.getSalesorderno(),
	    	    		  sales_Invoice.getSalesorderdate(),
	    	    		  sales_Invoice.getRefchallanno(),
	    	    		  sales_Invoice.getRefchallandate(),
	    	    		  sales_Invoice.getPayable_amt(),
	    	    		  sales_Invoice.getModified_type(),
	    	    		  sales_Invoice.getExport()
	    	    		  )).filter(c -> c.getModified_type().equals("INSERTED")).collect(Collectors.toList());
	    
	    
	    sInvList.forEach((sInv) -> {
			if(Utility.isNullOrEmpty(sInv.getInvoice_type())) {
				sInv.setInvoice_type(invoice_typeRepository.getSalesInvTypesDtls(sInv.getInvoice_type()).getInvtype_name());
			}
			else {sInv.setInvoice_type("None");}
		});
	    
	    return new PageImpl<>(sInvList, pageRequest, pageResult.getTotalElements());
	    
	    		
	}
	
	public List<Map<String,Object>> searchSalesInvoiceFast(String orderno,String fromdate, String todate,String party1,String finyear)
	{
		List<Map<String,Object>> searchsalesinvoice =new ArrayList<Map<String,Object>>();
		String tablename="sales_invoice",party_name="party",order_no="invoice_no",order_date="invoice_date";
		searchsalesinvoice.addAll(sales_InvoiceRepository.getsearchdataFast(tablename,party_name,order_no,order_date,orderno,fromdate,todate,party1,finyear));
		return searchsalesinvoice;
	}
	
	public List<Sales_Invoice_Pagination_DTO> searchSalesInvoice(String orderno,String fromdate, String todate,String party1,String finyear)
	{
		List<Sales_Invoice> searchsalesinvoice =new ArrayList<Sales_Invoice>();
		//System.out.println("finyear :: "+finyear);
		String tablename="sales_invoice",party_name="party",order_no="invoice_no",order_date="invoice_date";
		searchsalesinvoice.addAll(sales_InvoiceRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,party1,finyear));
		
		//System.out.println(searchsalesinvoice.get(0));
		List<Sales_Invoice_Pagination_DTO> paginationlist = new ArrayList<Sales_Invoice_Pagination_DTO>();
		for(int i=0;i<searchsalesinvoice.size();i++) 
		{
			
			//System.out.println("serchsaleorder.get(i).getId() "+searchsalesinvoice.get(i).getId());
			Sales_Invoice_Pagination_DTO sale= new Sales_Invoice_Pagination_DTO(searchsalesinvoice.get(i).getId(),
					searchsalesinvoice.get(i).getInvoice_id(),
					searchsalesinvoice.get(i).getInvoice_no(),
					searchsalesinvoice.get(i).getInvoice_date(),
					searchsalesinvoice.get(i).getInvoice_type(),
		    		searchsalesinvoice.get(i).getParty(),
		    		searchsalesinvoice.get(i).getPartyname(),
		    		searchsalesinvoice.get(i).getReturn_approval_status(),
		    		searchsalesinvoice.get(i).getSalesorderno(),
		    		searchsalesinvoice.get(i).getSalesorderdate(),
		    		searchsalesinvoice.get(i).getRefchallanno(),
		    		searchsalesinvoice.get(i).getRefchallandate(),
		    		searchsalesinvoice.get(i).getPayable_amt(),
		    		searchsalesinvoice.get(i).getModified_type(),
		    		searchsalesinvoice.get(i).getExport());
			
			paginationlist.add(sale);
		}
		paginationlist.forEach((sInv) -> {
				if(Utility.isNullOrEmpty(sInv.getInvoice_type())) {
					sInv.setInvoice_type(invoice_typeRepository.getSalesInvTypesDtls(sInv.getInvoice_type()).getInvtype_name());
				}
				else {sInv.setInvoice_type("None");}
			});
		  
		List<Sales_Invoice_Pagination_DTO> finalData = paginationlist
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Sales_Invoice_Pagination_DTO::getInvoice_date).
						  thenComparingInt(
                        d -> d.getInvoice_date().length() + d.getInvoice_no().length())
                .thenComparing(Sales_Invoice_Pagination_DTO::getInvoice_no).reversed())
				  .collect(Collectors.toList());
		
		//System.out.println(paginationlist.get(0));
		return finalData;
		
		
	}
	
	public List<Sales_InvoiceDTO> getSalesInvoices(String party,String invdate,String comp){
		List<Sales_Invoice> sInvList=new ArrayList<Sales_Invoice>();
		//sInvList.addAll(sales_InvoiceRepository.getSalesInvoices(party,invdate,comp));
		sInvList.addAll(sales_InvoiceRepository.getSalesInvoicesnew(party,invdate,comp));
		
		Type listType=new TypeToken<List<Sales_InvoiceDTO>>() {}.getType();
		List<Sales_InvoiceDTO> salesInvList=new ModelMapper().map(sInvList,listType);
		
		return salesInvList;
	}
	
	public List<Map<String,Object>> getSalesInvoiceReturn(String party,String invdate,String comp)
	{
		List<Map<String,Object>> sInvList=new ArrayList<>();
		
		List<String> invoiceid=new ArrayList<>();
		
		invoiceid.addAll(sales_InvoiceRepository.getSalesInvoiceReturn(party,invdate));
		for(int p=0;p<invoiceid.size();p++)
		{
			sInvList.add(sales_InvoiceRepository.getSalesInvoiceReturnList(invoiceid.get(p)));
		}
		
		return sInvList;
	}
	
	public Sales_InvoiceDTO getSalesInvDetails(String invoice_id)
	{
		Sales_Invoice sales_Invoice=sales_InvoiceRepository.getSalesInvDetails(invoice_id);
		
		Type listType=new TypeToken<Sales_InvoiceDTO>() {}.getType();
		Sales_InvoiceDTO salesInvDtls=new ModelMapper().map(sales_Invoice,listType);
		return salesInvDtls;
	}
	
	public Sales_InvoiceDTO findOne(long id)
	{
		Optional<Sales_Invoice> siv=this.sales_InvoiceRepository.findById(id);
		Sales_Invoice sInvDtls=siv.get();
		
		Type listType=new TypeToken<Sales_InvoiceDTO>() {}.getType();
		Sales_InvoiceDTO sInvoiceDtls=new ModelMapper().map(sInvDtls,listType);
		
		sInvoiceDtls.setPayable_amt_inword(NumToWord.numberConvert((int)sInvoiceDtls.getPayable_amt()));
		return sInvoiceDtls;
	}
	
	public List<Sales_Invoice_Item_DtlsDTO> getSalesInvItmDtls(String invoice_id)
	{
		List<Sales_Invoice_Item_Dtls> modelList=sales_Invoice_Item_DtlsRepository.getSalesInvItmDtls(invoice_id);
		
		Type listType=new TypeToken<List<Sales_Invoice_Item_DtlsDTO>>() {}.getType();
		
		List<Sales_Invoice_Item_DtlsDTO> salesInvDoc=new ModelMapper().map(modelList,listType);
		
		return salesInvDoc;
	}
	
	public List<Map<String,Object>> getSalesInvItmDtlswtAltName(String invoice_id)
	{
		return sales_Invoice_Item_DtlsRepository.getSalesInvItmDtlswtAltName(invoice_id);
	}
	
	public List<Map<String,Object>> retriveinvoicejobworkprice(String invoice_id)
	{
		return sales_Invoice_Item_Dtls_for_jobwork_priceRepository.retriveinvoicejobworkprice(invoice_id);
	}
	
	public List<Sales_Invoice_Item_DtlsDTO> getSalesInvItmDtls1(String invoice_id)
	{
		List<Sales_Invoice_Item_Dtls> modelList=sales_Invoice_Item_DtlsRepository.getSalesInvItmDtls(invoice_id);
		modelList.forEach(element->
		{
		    //	element.setItem_name(item_masterRepository.getItemDetailsThruItemId(element.getItem_code()).getSub_group_name());
		    //	if(element.getDiscount_amt() >0) 
			//{
			//	element.setAmount(element.getAmount() - element.getDiscount_amt());
			//}
			System.out.println("item code:::: "+ element.getItem_code());
			String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(element.getItem_code()).getItem_group();
			System.out.println("subgroup_items_code :: "+ subgroup_items_code);
			Item_group_master_sales_acc itemgroup= item_group_master_sales_accRepository.getItem_group_master_sales_acc(subgroup_items_code);
			element.setItem_name(ledgermasterRepository.getLedgers(itemgroup.getItem_total()).getLedgername());
			if(element.getDiscount_amt()==0.00) 
			{
				
			}else 
			{
				element.setDiscount_type(ledgermasterRepository.getLedgers(itemgroup.getDiscount()).getLedgername());	
			}
			
		});
		
		Type listType=new TypeToken<List<Sales_Invoice_Item_DtlsDTO>>() {}.getType();
		
		List<Sales_Invoice_Item_DtlsDTO> salesInvDoc=new ModelMapper().map(modelList,listType);
		
		return salesInvDoc;
	}
	
	public Sales_Invoice_Tax_InfoDTO getSalesInvTaxInfo(String invoice_id){

		 Sales_Invoice_Tax_Info modelList=sales_Invoice_Tax_InfoRepository.getSales_Invoice_Tax_Info(invoice_id);

		Type listType = new TypeToken<Sales_Invoice_Tax_InfoDTO>() {}.getType();

		Sales_Invoice_Tax_InfoDTO Delivary= new ModelMapper().map(modelList,listType);
		
		return Delivary;
	  }
	 
	public List<Sales_Invoice_Broker_DtlsDTO> getSalesInvBrkDtls(String invoice_id)
		{
			List<Sales_Invoice_Broker_Dtls> modelList=sales_Invoice_Broker_DtlsRepository.getSalesInvBrkDtls(invoice_id);
			
			Type listType=new TypeToken<List<Sales_Invoice_Broker_DtlsDTO>>() {}.getType();
			
			List<Sales_Invoice_Broker_DtlsDTO> salesInvBrkDtls=new ModelMapper().map(modelList,listType);
			
			return salesInvBrkDtls;
		}
	 
	public Sales_Invoice_BP_DtlsDTO getSalesInvBpDtls(String invoice_id)
		{
		 	Sales_Invoice_BP_Dtls modelList=sales_Invoice_BP_DtlsRepository.getSales_Invoice_BP_Dtls(invoice_id);
		 
		 	Type listType = new TypeToken<Sales_Invoice_BP_DtlsDTO>() {}.getType();

		 	Sales_Invoice_BP_DtlsDTO salesInvBpDtls= new ModelMapper().map(modelList,listType);
			
			return salesInvBpDtls;
		}

 	public List<Sales_Invoice_DocsDTO> getSalesInvDocs(String invoice_id)
	{
		List<Sales_Invoice_Docs> modelList=sales_Invoice_DocsRepository.getSalesInvDocs(invoice_id);
		
		Type listType=new TypeToken<List<Sales_Invoice_DocsDTO>>() {}.getType();
		
		List<Sales_Invoice_DocsDTO> salesInvDoc=new ModelMapper().map(modelList,listType);
		
		return salesInvDoc;
	}
 	
 	public List<Sales_Invoice_Trans_DtlsDTO> getSalesTransDtls(String invoice_id)
 	{
		List<Sales_Invoice_Trans_Dtls> modelList=new ArrayList<Sales_Invoice_Trans_Dtls>();
		
		modelList.addAll(sales_Invoice_Trans_DtlsRepository.getSalesTransDtls(invoice_id));
			
		Type listType=new TypeToken<List<Sales_Invoice_Trans_DtlsDTO>>() {}.getType();
		
		List<Sales_Invoice_Trans_DtlsDTO> salesInvTrans=new ModelMapper().map(modelList,listType);
		
		return salesInvTrans;
	}
 	
 	public Sales_Invoice_Shipment_DtlsDTO getSalesShipDtls(String invoice_id)
 	{
 		 Sales_Invoice_Shipment_Dtls modelList=sales_Invoice_Shipment_DtlsRepository.getSalesShipDtls(invoice_id);
		 
		 Type listType = new TypeToken<Sales_Invoice_Shipment_DtlsDTO>() {}.getType();

		 Sales_Invoice_Shipment_DtlsDTO salesInvBpDtls= new ModelMapper().map(modelList,listType);
			
		 return salesInvBpDtls;
	}
	
	public Sales_Invoice_Payment_DtlsDTO getSalesPayDtls(String invoice_id)
		{
			 Sales_Invoice_Payment_Dtls modelList=sales_Invoice_Payment_DtlsRepository.getSalesPayDtls(invoice_id);
			 
			 Type listType = new TypeToken<Sales_Invoice_Payment_DtlsDTO>() {}.getType();

			 Sales_Invoice_Payment_DtlsDTO salesInvBpDtls= new ModelMapper().map(modelList,listType);
				
			 return salesInvBpDtls;
		}

	/*public List<Sales_Invoice_Tax_InfoDTO> getSalesInvTaxInfo(String invoice_id)
	{
		List<Sales_Invoice> modelList=new ArrayList<Sales_Invoice>();
		
		modelList.addAll(sales_Invoice_Tax_InfoRepository.getSalesInvTaxInfo(invoice_id));
			
		Type listType=new TypeToken<List<Sales_Invoice_Tax_InfoDTO>>() {}.getType();
		
		List<Sales_Invoice_Tax_InfoDTO> salesInvTaxInf=new ModelMapper().map(modelList,listType);
		
		return salesInvTaxInf;
	}*/
	
	public List<Map<String,Object>> getInvTaxSum(String invid)
	{
		//List<Item_groupwise_taxsumm> iTaxsumms=new ArrayList<Item_groupwise_taxsumm>();
		/*iTaxsumms.addAll(item_groupwise_taxsummRepository.findAll());
		
		List<Item_groupwise_taxsumm> iTaxSums = iTaxsumms
			  .stream()
			  .filter(c -> c.getInvoice_id().equals(invid) && c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Item_groupwise_taxsumm::getInvoice_id))
			  .collect(Collectors.toList());*/
		//iTaxsumms.addAll(item_groupwise_taxsummRepository.getupdateItem_groupwise_taxsumm(invid));
		//return iTaxsumms;
		return item_groupwise_taxsummRepository.getupdateItem_groupwise_taxsumm(invid);
	}
	
	public List<Item_groupwise_hsnsumm> getInvHsnSum(String invid)
	{
		DecimalFormat numberFormat = new DecimalFormat("0.00");//numberFormat.format(
		DecimalFormat df = new DecimalFormat("#.###");//
		
		List<Item_groupwise_hsnsumm> iHsnSumms=new ArrayList<Item_groupwise_hsnsumm>();
		/*iHsnSumms.addAll(item_groupwise_hsnsummRepository.findAll());
		
		List<Item_groupwise_hsnsumm> iHsnSums = iHsnSumms
			  .stream()
			  .filter(c -> c.getInvoice_id().equals(invid) && c.getModified_type().equals("INSERTED"))
			  .sorted(Comparator.comparing(Item_groupwise_hsnsumm::getInvoice_id))
			  .collect(Collectors.toList());
		
		iHsnSums.forEach((hsn) -> {
			//hsn.setAmount(numberFormat.format(hsn.getAmount()));
		});*/
		iHsnSumms.addAll(item_groupwise_hsnsummRepository.getItem_groupwise_hsnsumm(invid));
		
		return iHsnSumms;
	}
	
	public List<Sales_payment_detailsDTO> getSalesPaymentDetails(String bunit,String party,String fdate,String tdate){
		
		List<Invoice_accountdetails> sPayDtls=new ArrayList<Invoice_accountdetails>();
		if(fdate.compareTo("undefined")!=0 && tdate.compareTo("undefined")!=0 && fdate.compareTo("null") != 0 && tdate.compareTo("null") != 0) {
			sPayDtls.addAll(invoice_accountdetailsRepository.getSalesPaymentDetails(bunit, party,Utility.convertDateDDMMYYYY(fdate),Utility.convertDateDDMMYYYY(tdate)));
		}
		
		Type listType=new TypeToken<List<Sales_payment_detailsDTO>>() {}.getType();
		List<Sales_payment_detailsDTO> sPayDTOs=new ModelMapper().map(sPayDtls,listType);
		
		int slno=1;
		double dueamt=0.00,totaldebit=0.00,totalcredit=0.00;
		for(Sales_payment_detailsDTO iAccDtlsDTO:sPayDTOs) 
		{
			iAccDtlsDTO.setVouchernumber(iAccDtlsDTO.getTransaction_type()+" - "+iAccDtlsDTO.getVouchernumber());
			dueamt +=iAccDtlsDTO.getAmount();
		//System.err.println("Due Amount: "+dueamt);
			iAccDtlsDTO.setSlno(slno);
			if(iAccDtlsDTO.getAmount() < 0) {
				totalcredit +=iAccDtlsDTO.getAmount();
				iAccDtlsDTO.setCredit(iAccDtlsDTO.getAmount()*-1);
				iAccDtlsDTO.setDebit(0);
			}else {
				totaldebit +=iAccDtlsDTO.getAmount();
				iAccDtlsDTO.setCredit(0);
				iAccDtlsDTO.setDebit(iAccDtlsDTO.getAmount());
			}
			slno++;
		}
		Sales_payment_detailsDTO sDto =new Sales_payment_detailsDTO(0,"","","","",0,0,0); 
		sPayDTOs.add(sDto);
		if(dueamt < 0) {
			dueamt=dueamt*-1;
		}
		Sales_payment_detailsDTO sDueDto =new Sales_payment_detailsDTO(0,"","TOTAL DUE AMOUNT as on "+Utility.convertDateDDMMYYYY(tdate),"","",0,0,dueamt);
		sPayDTOs.add(sDueDto);
		
		Sales_payment_detailsDTO sTotalDto =new Sales_payment_detailsDTO(0,"","TOTAL","","",0,totalcredit*-1,totaldebit+dueamt);
		sPayDTOs.add(sTotalDto);
		
		return sPayDTOs;
	}
	
	public List<Control_account_detailsDTO> getControlAccPayDetails(String bunit,String ledger,String fdate,String tdate){
		List<Account_details> sPayDtls=new ArrayList<Account_details>();
		if(fdate.compareTo("undefined")!=0 && tdate.compareTo("undefined")!=0 && fdate.compareTo("null") != 0 && tdate.compareTo("null") != 0) {
			sPayDtls.addAll(account_detailsRepository.getControlAccPayDetails(bunit,ledger,Utility.convertDateDDMMYYYY(fdate),Utility.convertDateDDMMYYYY(tdate)));
		}
		
		Type listType=new TypeToken<List<Control_account_detailsDTO>>() {}.getType();
		List<Control_account_detailsDTO> sPayDTOs=new ModelMapper().map(sPayDtls,listType);
		
		int slno=1;
		double dueamt=0.00,totaldebit=0.00,totalcredit=0.00;
		String prefacctype="",acctype="";
		for(Control_account_detailsDTO iAccDtlsDTO:sPayDTOs) 
		{
			prefacctype = iAccDtlsDTO.getVoucherno().substring(0,2);
			switch(prefacctype)
			{
				case "RC":  acctype="Cash Receipt"; break;
				case "RB":  acctype="Bank Receipt"; break;
				case "PB":  acctype="Bank Payment"; break;
				case "PC":  acctype="Cash Payment"; break;
				case "CO":  acctype="Contra"; break;
				case "JV":  acctype="Journal"; break;
				case "CN":  acctype="Credit Note"; break;
				case "DN":  acctype="Debit Note"; break;
				case "CP":  acctype="Credit Purchase"; break;
				case "CS":  acctype="Credit Sales"; break;
			}
			
			if(Utility.isNullOrEmpty(iAccDtlsDTO.getLedgerid())) {
				iAccDtlsDTO.setLedgername(ledgermasterRepository.getLedgerDetails(iAccDtlsDTO.getLedgerid()).getLedgername());
			}else {iAccDtlsDTO.setLedgername("None");}
			
			dueamt +=iAccDtlsDTO.getAmount();
			iAccDtlsDTO.setSlno(slno);
			if(iAccDtlsDTO.getAmount() < 0) {
				totalcredit +=iAccDtlsDTO.getAmount();
				iAccDtlsDTO.setCredit(iAccDtlsDTO.getAmount()*-1);
				iAccDtlsDTO.setDebit(0);
			}else {
				totaldebit +=iAccDtlsDTO.getAmount();
				iAccDtlsDTO.setCredit(0);
				iAccDtlsDTO.setDebit(iAccDtlsDTO.getAmount());
			}
			slno++;
		}
		Control_account_detailsDTO sDto =new Control_account_detailsDTO(0,"","","",0,0,0,""); 
		sPayDTOs.add(sDto);
		if(dueamt < 0) {
			dueamt=dueamt*-1;
		}
		Control_account_detailsDTO sDueDto =new Control_account_detailsDTO(0,"","","TOTAL DUE AMOUNT as on "+Utility.convertDateDDMMYYYY(tdate),0,0,dueamt,"");
		sPayDTOs.add(sDueDto);
		Control_account_detailsDTO sTotalDto =new Control_account_detailsDTO(0,"","","TOTAL",0,totalcredit*-1,(totalcredit*-1)+dueamt,"");
		sPayDTOs.add(sTotalDto);
		
		return sPayDTOs;
	}

	public String findParentSubGroupCodeCopy(String dledgerid, String bunit) {
	
		String parent1="",str1="",xx1="",xx2="",xx3="",xx4="",xx5="",xx6="",xx7="",xx8="",xx9="",xx10="",xx11="";
		List<String> subcode1 = new ArrayList<String>();
		if(dledgerid.length()==10)
		{
			subcode1.add(dledgerid.substring(0,5));
			
			if(subgroupmasterRepository.getParentSubGroup(dledgerid.substring(0, 5)).isPresent()) {
				parent1=subgroupmasterRepository.getParentSubGroup(dledgerid.substring(0, 5)).get().getParent_subgroupcode();
			}
			else
			{
				subcode1.add(dledgerid.substring(0,2));
				if(subgroupmasterRepository.getParentSubGroup(dledgerid.substring(0, 2)).isPresent()) {
					parent1=subgroupmasterRepository.getParentSubGroup(dledgerid.substring(0, 2)).get().getParent_subgroupcode();	
				}
			}
		}
		else
		{
			subcode1.add(dledgerid.substring(0,2));
			//System.err.println("Got Else Value: "+dledgerid.substring(0, 2));
			if(subgroupmasterRepository.getParentSubGroup(dledgerid.substring(0, 2)).isPresent()) {
				parent1=subgroupmasterRepository.getParentSubGroup(dledgerid.substring(0, 2)).get().getParent_subgroupcode();
			}
		}
		
		subcode1.add(parent1);
		//System.err.println("Parent1: "+parent1);
		if(parent1.compareTo("") !=0)
		{
			if(subgroupmasterRepository.getParentSubGroup(parent1).isPresent()) {
				xx1 = subgroupmasterRepository.getParentSubGroup(parent1).get().getParent_subgroupcode();
			}
			//System.err.println("Parent xx1: "+xx1);
			subcode1.add(xx1);
		}	
		if(xx1.compareTo("") !=0)
		{
			if(subgroupmasterRepository.getParentSubGroup(xx1).isPresent()) {
				xx2 = subgroupmasterRepository.getParentSubGroup(xx1).get().getParent_subgroupcode();
			}
			//System.err.println("Parent xx2: "+xx2);
			subcode1.add(xx2);
		}
		if(xx2.compareTo("") !=0)
		{
			if(subgroupmasterRepository.getParentSubGroup(xx2).isPresent()) {
				xx3 = subgroupmasterRepository.getParentSubGroup(xx2).get().getParent_subgroupcode();
			}
			//System.err.println("Parent xx3: "+xx3);
			subcode1.add(xx3);
		}
		if(xx3.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(xx3).isPresent()) {
				xx4 = subgroupmasterRepository.getParentSubGroup(xx3).get().getParent_subgroupcode();
			}
			//System.err.println("Parent xx4: "+xx4);
			subcode1.add(xx4);
		}
		if(xx4.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(xx4).isPresent()) {
				xx5 = subgroupmasterRepository.getParentSubGroup(xx4).get().getParent_subgroupcode();
			}
			//System.err.println("Parent xx5: "+xx5);
			subcode1.add(xx5);
		}
		if(xx5.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(xx5).isPresent()) {
				xx6 = subgroupmasterRepository.getParentSubGroup(xx5).get().getParent_subgroupcode();
			}
			//System.err.println("Parent xx6: "+xx6);
			subcode1.add(xx6);
		}
		if(xx6.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(xx6).isPresent()) {
				xx7 = subgroupmasterRepository.getParentSubGroup(xx6).get().getParent_subgroupcode();
			}
			//System.err.println("Parent xx7: "+xx7);
			subcode1.add(xx7);
		}	
		if(xx7.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(xx7).isPresent()) {
				xx8 = subgroupmasterRepository.getParentSubGroup(xx7).get().getParent_subgroupcode();
			}
			//System.err.println("Parent xx8: "+xx8);
			subcode1.add(xx8);
		}
		if(xx8.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(xx8).isPresent()) {
				xx9 = subgroupmasterRepository.getParentSubGroup(xx8).get().getParent_subgroupcode();
			}
			//System.err.println("Parent xx9: "+xx9);
			subcode1.add(xx9);
		}
		if(xx9.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(xx9).isPresent()) {
				xx10 = subgroupmasterRepository.getParentSubGroup(xx9).get().getParent_subgroupcode();
			}
			//System.err.println("Parent xx10: "+xx10);
			subcode1.add(xx10);
		}
		if(xx10.compareTo("") !=0)
		{	
			if(subgroupmasterRepository.getParentSubGroup(xx10).isPresent()) {
				xx11 = subgroupmasterRepository.getParentSubGroup(xx10).get().getParent_subgroupcode();
			}
			//System.err.println("Parent xx11: "+xx11);
			subcode1.add(xx11);
		}
		
		for (int i = 0; i < subcode1.size(); i++) {
			if (i == 0) {
				str1 += subcode1.get(i);
			} else {
				str1 += "x@nk!tltdx" + subcode1.get(i);
			}
		}
		return str1;
	}
	
	
	public List<Sales_Order_Summary_dyn> getChargesMatrixSalesdetails(String delivery_cid)
	 {
		
		 Delivery_challan delivarydata=delivery_challanRepository.getDeliveryChallanDtls(delivery_cid);
		 String refid="";
		//Wm_loading_advice loadingData=wm_loading_adviceRepository.getLoadingDetails(delivarydata.getReferance_id());
		 if(delivarydata.getRef_type().compareToIgnoreCase("GRN")==0)		 {
			 Pur_good_receipt grnData=pur_good_receiptRepository.getPurGoodRcptDtls(delivarydata.getReferance_id());
			 refid=grnData.getSales_order();
		 }
		 else
		 {
			 Wm_loading_advice loadingData=wm_loading_adviceRepository.getLoadingDetails(delivarydata.getReferance_id());
			 refid=loadingData.getReferance_id();
		 }
		
		 List<Sales_Order_Summary_dyn> appcharges=sales_InvoiceRepository.getappcharges(refid);
		
		 
		 return appcharges;
		 
	 }
	
	public Sales_Order getAppChargesSalesdetails(String delivery_cid)
	 {
		
		 Delivery_challan delivarydata=delivery_challanRepository.getDeliveryChallanDtls(delivery_cid);
		 String refid="";
		 //Wm_loading_advice loadingData=wm_loading_adviceRepository.getLoadingDetails(delivarydata.getReferance_id());
		 if(delivarydata.getRef_type().compareToIgnoreCase("GRN")==0)		 {
			 Pur_good_receipt grnData=pur_good_receiptRepository.getPurGoodRcptDtls(delivarydata.getReferance_id());
			 refid=grnData.getSales_order();
		 }
		 else
		 {
			 Wm_loading_advice loadingData=wm_loading_adviceRepository.getLoadingDetails(delivarydata.getReferance_id());
			 refid=loadingData.getReferance_id();
		 }
		 Sales_Order appcharges=sales_OrderRepository.getSalesOrderDetails(refid);
		
		 
		 return appcharges;
		 
	 }
	
	public List<Sales_Invoice> getreturnapprovalsalesInvoice(Long id)
	 {
		 Optional<Return_approval_note> op=return_approval_noteRepository.findById(id);
		 List<Sales_Invoice> salesInvoiceSingle = new ArrayList<Sales_Invoice>();
		 salesInvoiceSingle.add(sales_InvoiceRepository.getSingleSalesInvoice(op.get().getReferance_id()));
		 return salesInvoiceSingle; 
	 }
	
	@Transactional
	public Sales_Invoice accountpostingSalesInvoice(long id) 
	{

		    Optional<Sales_Invoice> op=this.sales_InvoiceRepository.findById(id);//static details 
			String output;
			//System.out.println();
			if(op.get().isJobwork()) 
			{
				String partyname=op.get().getPartyname();
				String print_to_name=cust_bussiness_partnerRepository.getCustomerThruBUstringnew(op.get().getParty()).getPrint_to_name();
				String creditnotedate=op.get().getSalesorderdate();
				double partyamount=op.get().getPayable_amt();
				
				String trucknumber="";
				if(op.get().getChallan().compareToIgnoreCase("Single")==0) 
				{
					String loadingid=delivery_challanRepository.deliveryChallanListUpdate(op.get().getReference_id()).getReferance_id();
					
					trucknumber=wm_loading_adviceRepository.getvehicleno(loadingid).getVehicle_no();
				}
				if(op.get().getChallan().compareToIgnoreCase("Multiple")==0)
				{
					
					String variousrefid[]=op.get().getReference_id().split(",");
					
	                String loadingid=delivery_challanRepository.deliveryChallanListUpdate(variousrefid[0]).getReferance_id();
					
					trucknumber=wm_loading_adviceRepository.getvehicleno(loadingid).getVehicle_no();
				
			
				}
				String statename=cust_bussiness_partnerRepository.gettallycreditnotestate(op.get().getParty()).getState();//party
				
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
		        
		        Item_groupwise_taxsumm gst_details=new Item_groupwise_taxsumm();
				//getgstdetails_dynamic
				List<Item_groupwise_taxsumm>gst_details_dyna =new ArrayList<Item_groupwise_taxsumm>();
				gst_details_dyna.addAll(sales_InvoiceRepository.getgstdetails_dynamic(op.get().getInvoice_id()));
				
				if(gst_details_dyna.size()==1) 
				{
					gst_details=gst_details_dyna.get(0);
				}
				else 
				{
					for(int i=0;i<gst_details_dyna.size();i++) 
					{
						if(gst_details_dyna.get(i).getTaxable_amt()>0) 
						{
							gst_details=gst_details_dyna.get(i);
						}
					}
				}
					
				//jobworkitemstarts here 
				Sales_Invoice_Item_Dtls_for_jobwork  jobwork=sales_InvoiceRepository.getInvoiceJobItemDtlstally(op.get().getInvoice_id());
				
				Sales_Invoice_Item_Dtls_for_jobwork_price jobworkprice=sales_Invoice_Item_Dtls_for_jobwork_priceRepository.getSalesInvItmjobpriceDtlstally(op.get().getInvoice_id());
				
				String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(jobwork.getJob_item()).getItem_group();
				
				//Item_group_master_sales_acc itemgroup= item_group_master_sales_accRepository.getItem_group_master_sales_acc(subgroup_items_code);
				//Item_group_master_sales_acc itemgroup= item_group_master_sales_accRepository.getItem_group_master_sales_acc(subgroup_items_code);
				
				Item_group_jobwork_sales_acc itemgroup = item_group_jobwork_sales_accRepository.getItemGroupJobworkSales(subgroup_items_code);
				
				Item_Service_master sericemaster=item_Service_masterRepository.serviceNameById(itemgroup.getJw_item_array());
				
				
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
				String brokercode=sales_Invoice_Broker_DtlsRepository.getSalesInvBrkDtlsposting(op.get().getInvoice_id()).getBrokercode();
				
				String broker="";
				if(Utility.isNullOrEmpty(brokercode)) 
				{
					broker=broker_masterRepository.brokerNameByCode(brokercode).getName();
				}
				else 
				{
					broker="XXXXX";
				}
				//String address_full=sales_Invoice_Shipment_DtlsRepository.getSalesShipDtls(op.get().getInvoice_id()).getPaytodtls();
			    String address=sales_Invoice_Shipment_DtlsRepository.getSalesShipDtls(op.get().getInvoice_id()).getPaytodtls();
			    String supplier_address=cust_bussiness_partner_addressRepository.custAddRetriveList(op.get().getParty()).getDistrict();
			    String customerpincode=Long.toString(cust_bussiness_partner_addressRepository.custAddRetriveList(op.get().getParty()).getPincode());
			    
			    Cust_bussiness_partner_statutory stat=cust_bussiness_partner_statutoryRepository.custStatutoryRetriveList(op.get().getParty());
			    String gstinno="";
			    String panno= "";
			    if(Utility.isNullOrEmpty(stat.getGst_no()))
				{
			    	gstinno=stat.getGst_no();
				}
			    if(Utility.isNullOrEmpty(stat.getPan_no()))
				{
			    	panno=stat.getPan_no();
				}
				    
			    if(Utility.isNullOrEmpty(customerpincode))
				{}
			    else 
			    {
			    	customerpincode="";
			    }
			    //String address_split[]=address_full.split(",");
				
			    //String address=address_split[0];
				Delivery_challan delivery_challan=delivery_challanRepository.getDeliveryChallanDtls(op.get().getReference_id());
				String deliverynotedate=delivery_challan.getChallan_date().replaceAll("-", "");
				String deliverynoteno=delivery_challan.getChallan_no();
			
				
				String invoicenumber=op.get().getInvoice_no();
				//gst ends here 
				String date=op.get().getInvoice_date().replace("-", ""); 

				boolean addplus=false,addminus=false;
				String add_plus_ledgername="",add_minus_ledgername="";
				double add_plus_amount=0.00,add_minus_amount=0.00;

				if(op.get().getAdj1_amt()>0) 
				{
					addplus=true;
					Ledgermaster ledgeridadd=ledgermasterRepository.getLedgerDetails(op.get().getAdj1_gl_ac());
					add_plus_ledgername=ledgeridadd.getLedgername();
					add_plus_amount=op.get().getAdj1_amt();
				}
				if(op.get().getAdj2_amt()>0) 
				{
					addminus=true;
					Ledgermaster ledgeridminus=ledgermasterRepository.getLedgerDetails(op.get().getAdj2_gl_ac());
					add_minus_ledgername=ledgeridminus.getLedgername();
					add_minus_amount=op.get().getAdj2_amt();
				}
							
				boolean einvoice=true;
					
					
				if(op.get().getInvoice_type().compareToIgnoreCase("INV00001")==0) 
				{
					einvoice=false;
				}
				else if(op.get().getInvoice_type().compareToIgnoreCase("INV00002")==0)//
				{
					if(cust_bussiness_partner_statutoryRepository.custStatutoryRetriveList(op.get().getParty()).isRegistered()==false) 
					{
					  einvoice=false;
					}
					else 
					{
						einvoice=true;
					}
				}
				else 
				{
					einvoice=false;
				}
				
				boolean remarkstat=false;
				String remarks="";
				if(Utility.isNullOrEmpty(op.get().getRemarks()))
				{
					remarks=op.get().getRemarks();
					remarkstat=true;
				}

				Tallyrequest_SalesInvoiceJobwork tally=new Tallyrequest_SalesInvoiceJobwork();
				try  
				{
					
					if(partyname.contains("&"))
					{
						partyname=partyname.replaceAll("&","&amp;");
						
					}
					
					if(print_to_name.contains("&"))
					{
						print_to_name=print_to_name.replaceAll("&","&amp;");
						
					}
					if(broker.contains("&"))
					{
						broker=broker.replaceAll("&","&amp;");
						
					}
					if(address.contains("&"))
					{
						address=address.replaceAll("&","&amp;");
						
					}
					/*String item_name_ledger=sericemaster.getAc_ledger_name();//done on basis of single data
			        
					
					String item_amount=String.valueOf(jobworkprice.getTax_value());
					
					String item_name=jobwork.getJob_item_name();
					
					String  item_rate=String.valueOf(jobworkprice.getJob_price());
					
					String packing_uom=jobwork.getPack_uom();
					
					String packing_qty=String.valueOf(jobwork.getPack_qty());
					
					String item_qty=jobwork.getItem_uom();
					
					String price_based_on=jobwork.getPrice_based_on();
					
					String item_passeditemqty=String.valueOf(jobwork.getItem_qty());
					
					*/
					//einvoicestarts here
					String ackno="",ackfulldate="",ackdate="",signedQRCode="",irnno="",waybill="",waybillfulldate="",waybilldate="";
					boolean waybillwoincoice=false;
					boolean waybillcheck=false;
					Sale_invoice_einvoice_gen einv=sale_invoice_einvoice_genRepository.gettallyEinvoice(op.get().getInvoice_id());
					//for e-invoice
					if(op.get().isCreate_einvoice() == true )
					{
						ackno=einv.getAck_no();
						ackfulldate=einv.getAck_date().substring(0, 10);
						ackdate=ackfulldate.replace("-", ""); 
						signedQRCode=einv.getSighned_invoice();
						irnno=einv.getIrn_no();
						
						if(op.get().isCreate_ewaybill() == true)
						{
							waybillcheck=true;
							waybill=einv.getEway_bill_no();
							waybillfulldate=einv.getEway_bill_date().substring(0, 10);
							waybilldate=waybillfulldate.replace("-", "");
						}
						else {
								waybill="";
								waybilldate="";
							}
					}
					else if((op.get().isCreate_einvoice() == false) && (op.get().isCreate_ewaybill_wo_invoice() == true))
					{
						waybillwoincoice=true;
						ackno="";
						//ackfulldate=einv.getEway_bill_date().substring(0, 10);
						ackdate=""; 
						signedQRCode="";
						irnno="";
						waybill=einv.getEway_bill_no();
						waybillfulldate=einv.getEway_bill_date().substring(0, 10);
						String waybillfulldatespit[]=waybillfulldate.split("/");
						waybilldate=waybillfulldatespit[2]+waybillfulldatespit[1]+waybillfulldatespit[0];
						//waybilldate=waybillfulldate.replace("/", "");
					}
					else
					{
						ackno="";
						ackdate="";
						signedQRCode="";
						irnno="";
						waybill="";
						waybilldate="";
					}
					//einvoicestarts ends here 
					
					String discountamount="",discountledger="";
							boolean discountstat=false;
					output = tally.SendToTally(partyname,trucknumber,creditnotedate,statename,partyamount,item_name_ledger,
							 item_name,item_amount,item_rate,item_passeditemqty,item_qty,Double.parseDouble(item_amount),roundoff_amt,
							 roundoffglaccount,rounfoffdrcr,gst_valid,gststatus,cgstledger,sgstledger,igstledger,
							 cgstamount,sgstamount,igstamount,date,invoicenumber,discountamount,discountledger,
							 discountstat,broker,address,deliverynotedate,deliverynoteno,packing_qty,price_based_on,
							 packing_uom,supplier_address,print_to_name,addplus,addminus,add_plus_ledgername,add_minus_ledgername,
							 add_plus_amount,add_minus_amount,einvoice,remarkstat,remarks,customerpincode,gstinno,
							 panno,ackno,ackdate,signedQRCode,irnno,waybill,waybillwoincoice,waybillcheck,waybilldate);
			        
					
					
					String [] splitoutput = output.split("\\|\\|");
					sales_InvoiceRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
					
				}
				catch(Exception e)
				{
				
					//System.out.println(e);
				}
			}
			else 
			{
		        // Create a DecimalFormat instance to format the number with 2 decimal places
		        DecimalFormat df = new DecimalFormat("0.00");
		        
				String partyname=op.get().getPartyname();
				String print_to_name=cust_bussiness_partnerRepository.getCustomerThruBUstringnew(op.get().getParty()).getPrint_to_name();
				String creditnotedate=op.get().getSalesorderdate();
				
				//double partyamount=op.get().getPayable_amt();
				String partyamount=df.format(op.get().getPayable_amt());
				// Output the formatted Payableamount
		        System.out.println("Payableamount:: "+partyamount);
		        
				String trucknumber="";
				
				// e-invoice starts
				String ackno="",ackfulldate="",ackdate="",signedQRCode="",irnno="",waybill="",waybillfulldate="",waybilldate="";
				boolean waybillwoincoice=false;
				boolean waybillcheck=false;
				Sale_invoice_einvoice_gen einv=sale_invoice_einvoice_genRepository.gettallyEinvoice(op.get().getInvoice_id());
				//for e-invoice
				if(op.get().isCreate_einvoice() == true )
				{
					ackno=einv.getAck_no();
					ackfulldate=einv.getAck_date().substring(0, 10);
					ackdate=ackfulldate.replace("-", ""); 
					signedQRCode=einv.getSighned_invoice();
					irnno=einv.getIrn_no();
					
					if(op.get().isCreate_ewaybill() == true)
					{
						waybillcheck=true;
						waybill=einv.getEway_bill_no();
						waybillfulldate=einv.getEway_bill_date().substring(0, 10);
						waybilldate=waybillfulldate.replace("-", "");
					}
					else {
							waybill="";
							waybilldate="";
						}
				}
				else if((op.get().isCreate_einvoice() == false) && (op.get().isCreate_ewaybill_wo_invoice() == true))
				{
					waybillwoincoice=true;
					ackno="";
					//ackfulldate=einv.getEway_bill_date().substring(0, 10);
					ackdate=""; 
					signedQRCode="";
					irnno="";
					waybill=einv.getEway_bill_no();
					waybillfulldate=einv.getEway_bill_date().substring(0, 10);
					String waybillfulldatespit[]=waybillfulldate.split("/");
					//System.out.println("Way: "+waybillfulldatespit[0]+" // "+waybillfulldatespit[1]+" // "+waybillfulldatespit[2]);
					waybilldate=waybillfulldatespit[2]+waybillfulldatespit[1]+waybillfulldatespit[0];
					//System.out.println("WayBill: "+waybilldate);
					//waybilldate=waybillfulldate.replace("/", "");
				}
				else
				{
					ackno="";
					ackdate="";
					signedQRCode="";
					irnno="";
					waybill="";
					waybilldate="";
				}
				
				//System.out.println("ackno:"+ackno+"//"+ackdate+"///"+signedQRCode+"///"+irnno+"///"+waybill+"////"+waybilldate+"////"+waybillcheck+"////"+waybillwoincoice);
				//e-invoice ends
				
					if(op.get().getChallan().compareToIgnoreCase("Single")==0) 
					{
						String loadingid=delivery_challanRepository.deliveryChallanListUpdate(op.get().getReference_id()).getReferance_id();
						String reftype=delivery_challanRepository.deliveryChallanListUpdate(op.get().getReference_id()).getRef_type();
		                if(reftype.compareToIgnoreCase("GRN")==0)
		                {
		                	trucknumber=sales_Invoice_Trans_DtlsRepository.getSalesInvTransDtls(op.get().getInvoice_id()).getVehicleno();
		                }
		                else{
		                	trucknumber=wm_loading_adviceRepository.getvehicleno(loadingid).getVehicle_no();
		                }
						
					}
					if(op.get().getChallan().compareToIgnoreCase("Multiple")==0)
					{
						
						String variousrefid[]=op.get().getReference_id().split(",");
						
		                String loadingid=delivery_challanRepository.deliveryChallanListUpdate(variousrefid[0]).getReferance_id();
		                String reftype=delivery_challanRepository.deliveryChallanListUpdate(op.get().getReference_id()).getRef_type();
		                if(reftype.compareToIgnoreCase("GRN")==0)
		                {
		                	trucknumber=sales_Invoice_Trans_DtlsRepository.getSalesInvTransDtls(op.get().getInvoice_id()).getVehicleno();
		                }
		                else{
		                	trucknumber=wm_loading_adviceRepository.getvehicleno(loadingid).getVehicle_no();
		                }
					}
				
				
				String statename=cust_bussiness_partnerRepository.gettallycreditnotestate(op.get().getParty()).getState();//party
				
					 //item details starts here	
					List<Sales_Invoice_Item_Dtls> itemDetails= sales_Invoice_Item_DtlsRepository.getSalesInvItmDtls(op.get().getInvoice_id());//item details
					
					String itemSubGroupName="";boolean discountstat=false;
					String item_name_ledger[]=new String[itemDetails.size()];
			        String item_name[]=new String[itemDetails.size()];
			        String item_amount[]=new String[itemDetails.size()];
			        String item_rate[]=new String[itemDetails.size()];
			        String item_passeditemqty[]=new String[itemDetails.size()];
			        String item_qty[]=new String[itemDetails.size()];
			        
			        String packing_uom[]=new String[itemDetails.size()];
			        
			        String packing_qty[]=new String[itemDetails.size()];
			        
			        String discountamount[]=new String[itemDetails.size()];
			        String discountledger[]=new String[itemDetails.size()];
			        String price_based_on[]=new String[itemDetails.size()];
			        double item_total=0.00;
			        
			        for(int i=0;i<itemDetails.size();i++) 
			        {
			        	
			        	String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(itemDetails.get(i).getItem_code()).getItem_group();
						Item_group_master_sales_acc itemgroup= item_group_master_sales_accRepository.getItem_group_master_sales_acc(subgroup_items_code);
			        	item_name_ledger[i]=ledgermasterRepository.getLedgers(itemgroup.getItem_total()).getLedgername();
			        	item_name[i]=itemDetails.get(i).getItem_name();
			        	
			        	// Format the amount
			        	item_amount[i]=String.valueOf(df.format(itemDetails.get(i).getAmount()));
			        	
			        	item_rate[i]=String.valueOf(itemDetails.get(i).getPrice());
			        	price_based_on[i]=itemDetails.get(i).getPrice_based_on();
			        	item_passeditemqty[i]=String.valueOf(itemDetails.get(i).getQuantity());
			        	//item_passeditemqty[i]=String.valueOf(itemDetails.get(i).getSquantity());
			        	packing_qty[i]=String.valueOf((int)(itemDetails.get(i).getSquantity()));

				        // Output the formatted amount
				        System.out.println("ItemAmt:: "+item_amount[i]);
			        	
			        	packing_uom[i]=item_masterRepository.getItemDetailsbyname(itemDetails.get(i).getPacking_name()).getMstock_unit_name(); 
			        	item_qty[i]=itemDetails.get(i).getUom();
			        	item_total+=itemDetails.get(i).getAmount();
			        	if(itemDetails.get(i).getDiscount_amt()>0) 
			        	{
			        		discountstat=true;
			        		discountamount[i]=String.valueOf(itemDetails.get(i).getDiscount_amt());
			        		discountledger[i]=ledgermasterRepository.getLedgers(itemgroup.getDiscount()).getLedgername();
			        	}
			        	
			        	System.out.println("item Rate: "+itemDetails.get(i).getPrice()+" /Amt/ "+itemDetails.get(i).getAmount()+" /NumAmt/ "+df.format(itemDetails.get(i).getAmount()));
			        	
			        }
			        
			        System.out.println("item Amt: "+item_amount+" /String Amt/ "+String.valueOf(item_amount));
			      //item details ends here  
			        
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
			        	//roundoff_amt=op.get().getRoundoff_amt();
			        }
			        if(op.get().getRoundoff_amt()<0)//minus meand debit 
			        {
			        	rounfoffdrcr=-1;//dr
			            roundoffglaccount=ledgerid.getLedgername();
			           // String amount=""+op.get().getRoundoff_amt();
			         //   amount=amount.substring(1, amount.length());
			            
			          //  roundoff_amt=Double.parseDouble(amount); ;
			        }
			       
				
					//round off ends here 
					
					//gst starts here 
					//Item_groupwise_taxsumm gst_details=sales_InvoiceRepository.getgstdetails(op.get().getInvoice_id());
					//Item_groupwise_taxsumm gst_details=sales_InvoiceRepository.getgstdetailsnew(op.get().getInvoice_id());
					
			        Item_groupwise_taxsumm gst_details=new Item_groupwise_taxsumm();
					//getgstdetails_dynamic
					List<Item_groupwise_taxsumm>gst_details_dyna =new ArrayList<Item_groupwise_taxsumm>();
					gst_details_dyna.addAll(sales_InvoiceRepository.getgstdetails_dynamic(op.get().getInvoice_id()));
					
					if(gst_details_dyna.size()==1) 
					{
						gst_details=gst_details_dyna.get(0);
					}
					else 
					{
						for(int i=0;i<gst_details_dyna.size();i++) 
						{
							if(gst_details_dyna.get(i).getTaxable_amt()>0) 
							{
								gst_details=gst_details_dyna.get(i);
							}
						}
					}
					
					
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
					String brokercode=sales_Invoice_Broker_DtlsRepository.getSalesInvBrkDtlsposting(op.get().getInvoice_id()).getBrokercode();
					
					String broker="";
							if(Utility.isNullOrEmpty(brokercode)) 
							{
								broker=broker_masterRepository.brokerNameByCode(brokercode).getName();
							}
							else 
							{
								broker="XXXXX";
							}
				//	String address_full=sales_Invoice_Shipment_DtlsRepository.getSalesShipDtls(op.get().getInvoice_id()).getPaytodtls();
				    String address=sales_Invoice_Shipment_DtlsRepository.getSalesShipDtls(op.get().getInvoice_id()).getPaytodtls();
				    String supplier_address=cust_bussiness_partner_addressRepository.custAddRetriveList(op.get().getParty()).getDistrict();
				    String customerpincode=Long.toString(cust_bussiness_partner_addressRepository.custAddRetriveList(op.get().getParty()).getPincode());
				    
				    Cust_bussiness_partner_statutory stat=cust_bussiness_partner_statutoryRepository.custStatutoryRetriveList(op.get().getParty());
				    String gstinno="";
				    String panno= "";
				    if(Utility.isNullOrEmpty(stat.getGst_no()))
					{
				    	gstinno=stat.getGst_no();
					}
				    if(Utility.isNullOrEmpty(stat.getPan_no()))
					{
				    	panno=stat.getPan_no();
					}
				    
				    if(Utility.isNullOrEmpty(customerpincode))
					{}
				    else 
				    {
				    	customerpincode="";
				    }
				    //String address_split[]=address_full.split(",");
					
				    //String address=address_split[0];
					Delivery_challan delivery_challan=delivery_challanRepository.getDeliveryChallanDtls(op.get().getReference_id());
					String deliverynotedate=delivery_challan.getChallan_date().replaceAll("-", "");
					String deliverynoteno=delivery_challan.getChallan_no();
				
					
					String invoicenumber=op.get().getInvoice_no();
					//gst ends here 
					String date=op.get().getInvoice_date().replace("-", ""); 

					boolean addplus=false,addminus=false;
					String add_plus_ledgername="",add_minus_ledgername="";
					double add_plus_amount=0.00,add_minus_amount=0.00;

					if(op.get().getAdj1_amt()>0) 
					{
						addplus=true;
						Ledgermaster ledgeridadd=ledgermasterRepository.getLedgerDetails(op.get().getAdj1_gl_ac());
						add_plus_ledgername=ledgeridadd.getLedgername();
						add_plus_amount=op.get().getAdj1_amt();
					}
					if(op.get().getAdj2_amt()>0) 
					{
						addminus=true;
						Ledgermaster ledgeridminus=ledgermasterRepository.getLedgerDetails(op.get().getAdj2_gl_ac());
						add_minus_ledgername=ledgeridminus.getLedgername();
						add_minus_amount=op.get().getAdj2_amt();
					}
								
					boolean einvoice=true;
					
					/*if(Utility.isNullOrEmpty(op.get().getE_invoice_no()))
					{
					}
					else
					{*/
						//Cust_bussiness_partner_statutory
						
							if(op.get().getInvoice_type().compareToIgnoreCase("INV00001")==0) 
							{
								einvoice=false;
							}
							else if(op.get().getInvoice_type().compareToIgnoreCase("INV00002")==0)//
							{
								if(cust_bussiness_partner_statutoryRepository.custStatutoryRetriveList(op.get().getParty()).isRegistered()==false) 
								{
								  einvoice=false;
								}
								else 
								{
									einvoice=true;
								}
							}
							else 
							{
								einvoice=false;
							}
							
								
							
						
						
					//}
					boolean remarkstat=false;
					String remarks="";
					if(Utility.isNullOrEmpty(op.get().getRemarks()))
					{
						remarks=op.get().getRemarks();
						remarkstat=true;
					}

				
				Tallyrequest_SalesInvoice tally=new Tallyrequest_SalesInvoice();
				try  
				{
					
					if(partyname.contains("&"))
					{
						partyname=partyname.replaceAll("&","&amp;");
					}
					
					if(print_to_name.contains("&"))
					{
						print_to_name=print_to_name.replaceAll("&","&amp;");
						
					}
					if(broker.contains("&"))
					{
						broker=broker.replaceAll("&","&amp;");
						
					}
					if(address.contains("&"))
					{
						address=address.replaceAll("&","&amp;");
						
					}
				
					output = tally.SendToTally(partyname,trucknumber,creditnotedate,statename,partyamount,item_name_ledger,
							 item_name,item_amount,item_rate,item_passeditemqty,item_qty,item_total,roundoff_amt,
							 roundoffglaccount,rounfoffdrcr,gst_valid,gststatus,cgstledger,sgstledger,igstledger,
							 cgstamount,sgstamount,igstamount,date,invoicenumber,discountamount,discountledger,
							 discountstat,broker,address,deliverynotedate,deliverynoteno,packing_qty,price_based_on,
							 packing_uom,supplier_address,print_to_name,addplus,addminus,add_plus_ledgername,add_minus_ledgername,
							 add_plus_amount,add_minus_amount,einvoice,remarkstat,remarks,customerpincode,gstinno,
							 panno,ackno,ackdate,signedQRCode,irnno,waybill,waybillwoincoice,waybillcheck,waybilldate);
					
					
					//System.out.println(" output :: "+output);
					String [] splitoutput = output.split("\\|\\|");
					//System.out.println(splitoutput[0] +" / " + splitoutput[1]+"/"+id);
					
					sales_InvoiceRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
					//sales_InvoiceRepository.exportuomtally(id,"OK",1);
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			
			
		Optional<Sales_Invoice> op1=this.sales_InvoiceRepository.findById(id);//static details 
		//System.out.println(op.get());
		
		return op1.get();
	}
	
	 public List<Sales_InvoiceDTO> getSalesInvoiceDataList(String currDate,String finyear)
		{
		 
			//List<Sales_Invoice> modelList=sales_InvoiceRepository.getSalesInvoiceDataList(currDate);
		 	List<Sales_Invoice> modelList =new ArrayList<Sales_Invoice>();
			String tablename="sales_invoice",party_name="party",order_no="invoice_no",order_date="invoice_date";
			String orderno="",party1="";
			modelList.addAll(sales_InvoiceRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,currDate,currDate,party1,finyear));
			
			Type listType = new TypeToken<List<Sales_InvoiceDTO>>() {}.getType();
			List<Sales_InvoiceDTO> advList = new ModelMapper().map(modelList,listType);
			
			/*advList.forEach((sInv) -> {
				if(Utility.isNullOrEmpty(sInv.getInvoice_type())) {
					sInv.setInvoice_type(invoice_typeRepository.getSalesInvTypesDtls(sInv.getInvoice_type()).getInvtype_name());
				}
				else {sInv.setInvoice_type("None");}
			});*/
			
			return advList;
		}
	 
	 public List<Map<String,Object>> getSalesInvoiceDataListFast(String currDate,String finyear)
	 {
		 return sales_InvoiceRepository.getSalesInvoiceDataListFast(currDate,finyear);
	 }
	 
	 public List<Map<String, Object>> getSalesInvoiceSummaryCatagorywiseList(String catagory,String catagoryname,String fromdate,String todate,String bunit)
	 	{
	 		List<Map<String,Object>> salesinvoiceList=new ArrayList <>();
	 		String multicatagory[]=catagoryname.split(",");
 			ArrayList<String> catagorynamemulti=new ArrayList<String>();
 			for(int i=0;i<multicatagory.length;i++)
 			{
 				catagorynamemulti.add(multicatagory[i]);
 			}
	 		if(catagory.compareToIgnoreCase("Partywise")==0)
	 		{
	 			//System.out.println("fdate:"+fromdate+"//"+todate+"//"+bunit+"//"+catagoryname);
	 			salesinvoiceList.addAll(sales_InvoiceRepository.getSalesInvoiceSummaryPartywise(catagorynamemulti,fromdate,todate,bunit));
	 		}
	 		else if(catagory.compareToIgnoreCase("Brokerwise")==0)
	 		{
	 			//System.out.println("fdate1:"+fromdate+"//"+todate+"//"+bunit+"//"+catagoryname);
	 			salesinvoiceList.addAll(sales_InvoiceRepository.getSalesInvoiceSummaryBrokerwise(catagorynamemulti,fromdate,todate,bunit));
	 		}
	 		else
	 		{
	 			//System.out.println("fdate2:"+fromdate+"//"+todate+"//"+bunit+"//"+catagoryname);
	 			salesinvoiceList.addAll(sales_InvoiceRepository.getSalesInvoiceSummaryItemwise(catagorynamemulti,fromdate,todate,bunit));
	 		}
	 		return salesinvoiceList;
	 	}
		
		public List<Map<String, Object>> getSalesInvoiceMiscList(String catagory,String fromdate,String todate,String bunit,String broker,String customer)
	 	{
	 		List<Map<String,Object>> salesinvoiceList=new ArrayList <>();
	 		if(catagory.compareToIgnoreCase("Partywise")==0)
	 		{
	 			//System.out.println("fdate:"+fromdate+"//"+todate+"//"+bunit);
	 			String multicustomer[]=customer.split(",");
	 			ArrayList<String> customermulti=new ArrayList<String>();
	 			for(int i=0;i<multicustomer.length;i++)
	 			{
	 				customermulti.add(multicustomer[i]);
	 			}
	 			salesinvoiceList.addAll(sales_InvoiceRepository.getSalesInvoiceMiscPartyList(fromdate,todate,bunit,customermulti));
	 		}
	 		else if(catagory.compareToIgnoreCase("Brokerwise")==0)
	 		{
	 			//System.out.println("fdate1:"+fromdate+"//"+todate+"//"+bunit);
	 			String multibroker[]=broker.split(",");
	 			ArrayList<String> brokermulti=new ArrayList<String>();
	 			for(int i=0;i<multibroker.length;i++)
	 			{
	 				brokermulti.add(multibroker[i]);
	 			}
	 			salesinvoiceList.addAll(sales_InvoiceRepository.getSalesInvoiceMiscBrokerList(fromdate,todate,bunit,brokermulti));
	 		}
	 		else
	 		{
	 			//System.out.println("fdate2:"+fromdate+"//"+todate+"//"+bunit);
	 			salesinvoiceList.addAll(sales_InvoiceRepository.getSalesInvoiceMiscAllList(fromdate,todate,bunit));
	 		}
	 		
	 		return salesinvoiceList;
	 	}
		
		public List<Map<String, Object>> salesInvoiceFinishedItemlist(String company,String fromdate,String todate,String business_unit)
		 {
			 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
			 
			 modelList.addAll(sales_InvoiceRepository.salesInvoiceFinishedItemlist(company,fromdate,todate,business_unit));
			
			 return modelList;
		 }
		
		public List<Map<String, Object>> salesInvoiceBrokerlist(String company,String fromdate,String todate,String business_unit)
		 {
			 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
			 
			 modelList.addAll(sales_InvoiceRepository.salesInvoiceBrokerlist(company,fromdate,todate,business_unit));
			
			 return modelList;
		 }
		
		public List<Map<String, Object>> salesInvoicePartylist(String company,String fromdate,String todate,String business_unit)
		 {
			 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
			 
			 modelList.addAll(sales_InvoiceRepository.salesInvoicePartylist(company,fromdate,todate,business_unit));
			
			 return modelList;
		 }
		
		
		public List<Map<String, Object>> geteinvoicestatus_saleinv(long id,String invoiceno)
		{
			if(id>0)
			{
				return sales_InvoiceRepository.geteinvoicestatus_saleinvupdate(invoiceno);
			}
			else
			{
				return sales_InvoiceRepository.geteinvoicestatus_saleinv();
			}
			
		}
		
		public List<Map<String,Object>> getInvoiceReportThroughChallan(String challanid)
		{
			return sales_InvoiceRepository.getInvoiceReportThroughChallan(challanid);
		}
		
		
		public List<Map<String,Object>> getdocumentListwithfileSalesInvoice(String doc_pdf)
		{
			//String filename="D:/AayogAgroDocuments/SalesInvoice/"+doc_pdf;
			String filename="/usr/documents/salesinvoice/"+doc_pdf;	//Online  Aayog
			System.out.println("filename " +filename);
			return sales_InvoiceRepository.getdocumentListwithfileSalesInvoice(filename);
		}
		
		public Sales_Invoice_Docs findOneInvDoc(long id) 
		{
			Optional<Sales_Invoice_Docs> op=this.sales_Invoice_DocsRepository.findById(id);
			return op.get();
		}
		
		@Transactional
		public void deleteSIDocument(Sales_Invoice_Docs sales_Invoice_Docs) 
		{
			System.out.println("process_master_doc.getId()"+sales_Invoice_Docs.getId());
			sales_Invoice_DocsRepository.updatepdfdelete(sales_Invoice_Docs.getId());
		}
		
		public Map<String,Object> getTransportimage1(String refno)
		{
			return sales_Invoice_DocsRepository.getTransportimage1(refno);
		}
		

		public Map<String,Object> getJobWorkInvPrint(long mainid)
		{
			return sales_InvoiceRepository.getJobWorkInvPrint(mainid);
		}
		

		public List<Map<String,Object>> getInvoiceJobItemDtls(String invoice_id)
		{
			return sales_InvoiceRepository.getInvoiceJobItemDtls(invoice_id);
		}
		
		public List<Map<String,Object>> getInvoiceTServiceItem(String invoice_id)
		{
			return sales_InvoiceRepository.getInvoiceTServiceItem(invoice_id);
		}
		
		 public Map<String,Object> geteinvoicedetails(String invoice_id)
		 {
			return sales_InvoiceRepository.geteinvoicedetails(invoice_id);
         }
		
		public StatusDTO getnumtowordsaleinvoice(String invoice_id) 
		{
			//String totalamount="0.00";
			// totalamount=""+sales_InvoiceRepository.getSalesInvDetails(invoice_id).getPayable_amt();
			BigDecimal totalamount=new BigDecimal(sales_InvoiceRepository.getSalesInvDetailsNew(invoice_id));
			System.out.println("totalamount::"+totalamount.longValue());
			String amountsplit[]=String.valueOf(totalamount.longValue()+".00").split("[.]");
			
			//System.out.println(String.valueOf(totalamount) +" / " + amountsplit[0] + " // " + amountsplit[1]);
			String ruppes=NumToWord.numberConvert(Integer.parseInt(amountsplit[0]));//wordConvert
			String amountword="Rupees ";
			/*if(amountsplit[1].compareToIgnoreCase("0") == 0) 
			{
				 amountword= ruppes +" Rupees Only" ;
			}
			else 
			{
				if(amountsplit[1].length()==1) 
				{
					String paisa=NumToWord.numberConvert(Integer.parseInt(amountsplit[1])*10);	//wordConvert
					amountword= ruppes +" Rupees and " + paisa + " Paisa Only";
				}
				else 
				{
					String paisa=NumToWord.numberConvert(Integer.parseInt(amountsplit[1]));	//wordConvert
					amountword= ruppes +" Rupees and " + paisa + " Paisa Only";
				}
			}
			*/
			
			if(amountsplit[1].compareToIgnoreCase("0") == 0) 
			{
				 amountword+= ruppes +" Only" ;
			}
			else 
			{
				if(amountsplit[1].length()==1) 
				{
					String paisa=NumToWord.numberConvert(Integer.parseInt(amountsplit[1])*10);	//wordConvert
					amountword+= ruppes +" and " + paisa + " Paisa Only";
				}
				else 
				{
					System.out.println("amt::"+amountsplit[1]);
					String paisa=NumToWord.numberConvert(Integer.parseInt(amountsplit[1]));	//wordConvert
					amountword+= ruppes +" and " + paisa + " Paisa Only";
				}
			} 
		   // System.out.println("amountword :: "+amountword);
		    StatusDTO res =new StatusDTO();
		    res.setStatus(amountword);
			return res;
		}
		
		public StatusDTO  getnumtoword(String taxamt) 
		{
			String amountword="Rupees ";
			if(taxamt.contains("."))
			{
				String amountsplit[]=String.valueOf(taxamt).split("[.]");
				String ruppes=NumToWord.numberConvert(Integer.parseInt(amountsplit[0]));//wordConvert
				if(amountsplit[1].compareToIgnoreCase("0") == 0) 
				{
					 amountword+= ruppes +" Only" ;
				}
				else 
				{
					if(amountsplit[1].length()==1) 
					{
						String paisa=NumToWord.numberConvert(Integer.parseInt(amountsplit[1])*10);	//wordConvert
						amountword+= ruppes +" and " + paisa + " Paisa Only";
					}
					else 
					{
						String paisa=NumToWord.numberConvert(Integer.parseInt(amountsplit[1]));	//wordConvert
						amountword+= ruppes +" and " + paisa + " Paisa Only";
					}
				}
			}
			else
			{
				String ruppes=NumToWord.numberConvert(Integer.parseInt(taxamt));//wordConvert
				 amountword+= ruppes +" Only" ;
			}
			
			
		   // System.out.println("amountword :: "+amountword);
		    StatusDTO res =new StatusDTO();
		    res.setStatus(amountword);
			return res;
		}
		
		public List<Map<String, Object>> getDelvChallanJobworkPrice(String deliveryid)
		{
			return sales_InvoiceRepository.getDelvChallanJobworkPrice(deliveryid);
		}
		
		public List<Map<String, Object>> getDelvChallanMultiJobworkPrice(String deliveryid)
		{
			List<Map<String,Object>> salesinvoiceList=new ArrayList <>();
			String multiid[]=deliveryid.split(",");
 			ArrayList<String> idmulti=new ArrayList<String>();
 			for(int i=0;i<multiid.length;i++)
 			{
 				idmulti.add(multiid[i]);
 			}
 			salesinvoiceList.addAll(sales_InvoiceRepository.getDelvChallanMultiJobworkPrice(idmulti));
 			
			return salesinvoiceList;
		}
		
		public List<Map<String, Object>> getJobWorkSalesReport(String fromdate,String todate)
	 	{
	 		
	 		return sales_InvoiceRepository.getJobWorkSalesReport(fromdate,todate);
	 	}
		
		 public StatusDTO createEinvoiceGeneration(long id,Object  einvjson)
		{ 
			StatusDTO op=new StatusDTO();

			return op;
		}
		 
		 
		 public StatusDTO createEinvoiceGeneration2(Long id,StatusDTO statusDTO) 
		 {
			 return statusDTO;
		 }
		 
		 public Map<String,Object> getSalesInvPayDtls(String invoice_id)
		 {
			return sales_Invoice_Payment_DtlsRepository.getSalesInvPayDtls(invoice_id);
         }
		 
		 public List<Map<String, Object>> getSalesInvoicetransitReport(String fromdate,String todate)
		 {
			 return sales_InvoiceRepository.getSalesInvoicetransitReport(fromdate,todate);
		 }
		 
		 @Transactional
		 public StatusDTO updateArnNo(Long id,String invoiceno,String asnno)
		 {
			StatusDTO result = new StatusDTO();
			sales_InvoiceRepository.updateAsnNo(id,invoiceno,asnno);
			result.setStatus("Yes");
			return result;
		 }
		 
		 public Map<String,Object> getGatepassByChallan(String challan)
		 {
			return delivery_challanRepository.getGatepassByChallan(challan);
         }
}
