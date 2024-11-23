package com.AnkitIndia.jwtauthentication.security.services;

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
import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Broker_Dtls;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Chgs_dyn;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Docs;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Party_Dtls;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Shipment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Trans_Info;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_weight_dtl;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Item_opening_stk_dtls;
import com.AnkitIndia.jwtauthentication.model.Item_opening_stk_pack_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note;
import com.AnkitIndia.jwtauthentication.model.Sales_transport;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.CustomUomMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challanRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_Broker_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_Chgs_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_DocsRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_Item_Dtls_for_jobworkRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_Party_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_Shipment_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_Trans_InfoRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_weight_dtlRepository;
import com.AnkitIndia.jwtauthentication.repository.Invoice_typeRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_opening_stk_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_opening_stk_pack_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_InvoiceRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_Trans_Chgs_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.Tax_code_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_adviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_advice_trans_infoRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.ChallanpertransportreportDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Delivery_challan_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Invoice_typeDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.WheatstackcardreportDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challanDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Broker_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_DocsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Delivery_Trans_InfoDTO;

@Service
@Repository
public class Delivery_challanService_Imp implements Delivery_challanService {

	@Autowired
	Delivery_challanRepository dChallanRepository;
	
	@Autowired
	Delivery_challan_Item_DtlsRepository delivery_challan_Item_DtlsRepository;
	
	@Autowired
	Delivery_challan_Party_DtlsRepository delivery_challan_Party_DtlsRepository;
	
	@Autowired
	Delivery_challan_Broker_DtlsRepository delivery_challan_Broker_DtlsRepository;
	
	@Autowired
	Delivery_challan_DocsRepository delivery_challan_DocsRepository;
	
	@Autowired
	Delivery_challan_Shipment_DtlsRepository delivery_challan_Shipment_DtlsRepository;
	
	@Autowired
	Delivery_challan_weight_dtlRepository delivery_challan_weight_dtlRepository;
	  
	@Autowired
	Delivery_challan_Trans_InfoRepository delivery_challan_Trans_InfoRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Invoice_typeRepository invoice_typeRepository;
	
	@Autowired
	Sales_OrderRepository sales_OrderRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	Wm_loading_adviceRepository wm_loading_adviceRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Item_opening_stk_dtlsRepository item_opening_stk_dtlsRepository;
	
	@Autowired
	Item_opening_stk_pack_dtlsRepository item_opening_stk_pack_dtlsRepository;

	@Autowired
	 Return_approval_noteRepository return_approval_noteRepository;
	
	@Autowired
	Sales_InvoiceRepository sales_InvoiceRepository;
	
	@Autowired
	Tax_code_detailsRepository tax_code_detailsRepository;
	
	@Autowired
	CustomUomMasterRepository customUomMasterRepository;
	
	@Autowired
	Trans_bussiness_partnerRepository trans_bussiness_partnerRepository;
	
	@Autowired
	Delivery_challan_Chgs_dynRepository delivery_challan_Chgs_dynRepository;
	
	@Autowired
	Sales_Order_Trans_Chgs_dynRepository sales_Order_Trans_Chgs_dynRepository;
	
	@Autowired
	Wm_loading_advice_trans_infoRepository wm_loading_advice_trans_infoRepository;
	
	@Autowired
	Delivery_challan_Item_Dtls_for_jobworkRepository delivery_challan_Item_Dtls_for_jobworkRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	
	public SalesSequenceIdDTO getDCSequenceId(String fin_year,String inv_type)
	{
		// auto gen code starts
		String prefix="";
		String prefix1="";
		int slno=0;
		String gen_sno="";
		if(companyMasterRepository.getCompanyName().compareToIgnoreCase("ANKIT INDIA LIMITED")==0)
		{
			String sno=dChallanRepository.countDlvChln(fin_year,inv_type);
			
			if(sno != null ) {
				slno=Integer.parseInt(sno);
			}
			
			String fin_yearspit[]=fin_year.split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
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
			gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		}
		else
		{
			prefix=invoice_typeRepository.getSalesInvTypesDtls(inv_type).getInvtype_prefix();
		
			String sno=dChallanRepository.countDlvChln(fin_year,inv_type);
			
			if(sno != null ) {
				slno=Integer.parseInt(sno);
			}
			
			String fin_yearspit[]=fin_year.split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			
			//prefix=prefix+"-"+fin_year+"-";
			prefix=prefix+"-"+final_fyear+"-";
			//System.err.println(prefix);
			gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		}
		// auto gen code ends
		
		
		
		//String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		//String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
		
		Type listType = new TypeToken<SalesSequenceIdDTO>() {}.getType();
		
		SalesSequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Delivery_challan save(Delivery_challan dChallan)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		
		
		long slno=0;
		if(dChallanRepository.countId() != null ) {
			slno=Long.parseLong(dChallanRepository.countId());
		}
		String prefix="DC";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		dChallan.setDelivery_cid(gen_sno);
		
		/*Start checking transaction no for unique */
		//System.err.println("First:>>"+dChallan.getChallan_no());
		
		String gen_tslno="";
		if(companyMasterRepository.getCompanyName().compareToIgnoreCase("ANKIT INDIA LIMITED")==0)
		{
			long nslno=0;
			//String tprefix=invoice_typeRepository.getSalesInvTypesDtls(dChallan.getInv_type()).getInvtype_prefix();
			String tsno=dChallanRepository.countDlvChln(dChallan.getFin_year(),dChallan.getInv_type());
					
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			

			String fin_yearspit[]=dChallan.getFin_year().split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			
			//tprefix=tprefix+"-"+dChallan.getFin_year()+"-";
			//String tprefix="AILP"+"/"+final_fyear+"/TW";
			String tprefix="";
			String prefix1=invoice_typeRepository.getSalesInvTypesDtls(dChallan.getInv_type()).getInvtype_prefix();
			//prefix=prefix+"-"+fin_year+"-";
			if(dChallan.getInv_type().compareToIgnoreCase("INV00001") == 0)
			{
				tprefix="AILP"+"/"+final_fyear+"/TW";
			}
			else
			{
				tprefix="AILP"+"/"+final_fyear+"/"+prefix1;
			}
			 gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		}
		else 
		{
			long nslno=0;
			String tprefix=invoice_typeRepository.getSalesInvTypesDtls(dChallan.getInv_type()).getInvtype_prefix();
			String tsno=dChallanRepository.countDlvChln(dChallan.getFin_year(),dChallan.getInv_type());
					
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			

			String fin_yearspit[]=dChallan.getFin_year().split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			
			//tprefix=tprefix+"-"+dChallan.getFin_year()+"-";
			tprefix=tprefix+"-"+final_fyear+"-";
			
			//String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
			
			 gen_tslno=UniqueIDTransaction.uniqueId6(tprefix,nslno);
		}
		
		
		
		
		dChallan.setChallan_no(gen_tslno);
		//System.err.println("Last:>>>"+dChallan.getChallan_no());
		/*End checking transaction no for unique */
		
		dChallan.setModified_type("INSERTED");
		dChallan.setInserted_by(userRepository.getUserDetails(dChallan.getUsername()).getName());
		dChallan.setInserted_on(ldt);
		dChallan.setUpdated_by("NA");
		dChallan.setUpdated_on(ldt);
		dChallan.setDeleted_by("NA");
		dChallan.setDeleted_on(ldt);
		dChallan.setInvoicestatus("No");
		dChallan.setSales_returnstatus("NO");
		dChallan.setSalereturn_id("NA");
		
		dChallan.setSales_returnstatus("NO");
		dChallan.setSalereturn_id("NA");
		dChallan.setSales_invoice_id("NA");
		dChallan.setChallandate(dChallan.getChallan_date());
		dChallan.setChallanno(dChallan.getChallan_no());
		
		dChallan.setInv_type_name(invoice_typeRepository.getSalesInvTypesDtls(dChallan.getInv_type()).getInvtype_name());
		dChallan.setAdviceno(wm_loading_adviceRepository.getLoadingDetails(dChallan.getReferance_id()).getAdvice_no());
		
		
		
		if(Utility.isNullOrEmpty(dChallan.getParty())) {
			dChallan.setPartyname(cust_bussiness_partnerRepository.getCustomer(dChallan.getParty()).getCp_name());
		}else {dChallan.setPartyname("None");}
		
		if(dChallan.getRef_type().compareTo("Open Delivery Challan")!=0) {
			if(dChallan.getReferance_id().substring(0,3).compareTo("WLA")==0) {
				wm_loading_adviceRepository.updateDelvStatus(dChallan.getReferance_id(),true);
			}	
		}else {dChallan.setReferance_id(dChallan.getDelivery_cid());}
		
		if(dChallan.isJobwork()) 
		{
			
			dChallan.getDelivery_challan_Item_Dtls().clear();
			
			Set<Delivery_challan_Item_Dtls_for_jobwork> jobSet = new HashSet<Delivery_challan_Item_Dtls_for_jobwork>();
			jobSet.addAll(dChallan.getDelivery_challan_Item_Dtls_for_jobwork());
			for(Delivery_challan_Item_Dtls_for_jobwork jobDts : jobSet)
			{
				jobDts.setDelivery_cid(gen_sno);
				jobDts.setJob_item_name(item_masterRepository.itemNameById(jobDts.getJob_item()).getItem_name());
				if(Utility.isNullOrEmpty(jobDts.getJob_packing())) 
				{
					jobDts.setJob_packing_name(item_masterRepository.itemNameById(jobDts.getJob_packing()).getItem_name());
				}
				else
				{
					jobDts.setJob_packing_name("0");
				}
				jobDts.setCompany_id(dChallan.getCompany_id());
				jobDts.setFin_year(dChallan.getFin_year());
				jobDts.setModified_type("INSERTED");
				jobDts.setInserted_by(dChallan.getInserted_by());
				jobDts.setInserted_on(dChallan.getInserted_on());
				jobDts.setUpdated_by("NA");
				jobDts.setUpdated_on(ldt);
				jobDts.setDeleted_by("NA");
				jobDts.setDeleted_on(ldt);
				
			}
			dChallan.setDelivery_challan_Item_Dtls_for_jobwork(jobSet);
			
			
			
		}
		else 
		{
			dChallan.getDelivery_challan_Item_Dtls_for_jobwork().clear();
			
			
			//Dynamic
			Set<Delivery_challan_Item_Dtls> itemSet = new HashSet<Delivery_challan_Item_Dtls>();
			itemSet.addAll(dChallan.getDelivery_challan_Item_Dtls());
			for(Delivery_challan_Item_Dtls itemDts : itemSet)
			{
				itemDts.setDelivery_cid(gen_sno);
				itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
				if(Utility.isNullOrEmpty(itemDts.getPacking())) {
					itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
				}else {itemDts.setPacking_name("0");}
				itemDts.setCompany_id(dChallan.getCompany_id());
				itemDts.setFin_year(dChallan.getFin_year());
				itemDts.setModified_type("INSERTED");
				itemDts.setInserted_by(dChallan.getInserted_by());
				itemDts.setInserted_on(dChallan.getInserted_on());
				itemDts.setUpdated_by("NA");
				itemDts.setUpdated_on(ldt);
				itemDts.setDeleted_by("NA");
				itemDts.setDeleted_on(ldt);
				
				itemDts.setTax_name(tax_code_detailsRepository.getTaxCodeDetails(itemDts.getTax_code()).getTax_name());
			}
			dChallan.setDelivery_challan_Item_Dtls(itemSet);
			
			//For Item Stock Update
			String advitemcode="",advitemqty="",advpackcode="",advpackqty="";
			
			if(Utility.isNullOrEmpty(dChallan.getBusiness_unit())) {
				for(Delivery_challan_Item_Dtls dcDtls:itemSet)
				{
					advitemcode +=dcDtls.getItem_code()+",";
					advitemqty +=dcDtls.getQuantity()+",";
					advpackcode +=dcDtls.getPacking()+",";
					advpackqty +=dcDtls.getSquantity()+",";
				}
				advitemcode=advitemcode.substring(0,(advitemcode.length()-1));
				advitemqty=advitemqty.substring(0,(advitemqty.length()-1));
				advpackcode=advpackcode.substring(0,(advpackcode.length()-1));
				advpackqty=advpackqty.substring(0,(advpackqty.length()-1));
				//System.err.println("SP: >> "+advitemqty+" , "+advpackqty+" , "+advitemcode+" , "+advpackcode+" , "+dChallan.getBusiness_unit()+" , "+dChallan.getFin_year());
				//int s=dChallanRepository.callSoldItemStockDelivery(advitemqty,advpackqty,advitemcode,advpackcode,dChallan.getBusiness_unit(),dChallan.getFin_year());
			}
			
		}
		
		
	
		
		//Dynamic
		Set<Delivery_challan_Broker_Dtls> BrokerSet = new HashSet<Delivery_challan_Broker_Dtls>();
		BrokerSet.addAll(dChallan.getDelivery_challan_Broker_Dtls());
		for(Delivery_challan_Broker_Dtls brokerDts : BrokerSet)
		{
			brokerDts.setDelivery_cid(gen_sno);
			brokerDts.setCompany_id(dChallan.getCompany_id());
			brokerDts.setFin_year(dChallan.getFin_year());
			brokerDts.setModified_type("INSERTED");
			brokerDts.setInserted_by(dChallan.getInserted_by());
			brokerDts.setInserted_on(dChallan.getInserted_on());
			brokerDts.setUpdated_by("NA");
			brokerDts.setUpdated_on(ldt);
			brokerDts.setDeleted_by("NA");
			brokerDts.setDeleted_on(ldt);
		}
		dChallan.setDelivery_challan_Broker_Dtls(BrokerSet);
		
		//Dynamic
		Set<Delivery_challan_Party_Dtls> partySet = new HashSet<Delivery_challan_Party_Dtls>();
		partySet.addAll(dChallan.getDelivery_challan_Party_Dtls());
		for(Delivery_challan_Party_Dtls partyDts : partySet)
		{
			partyDts.setDelivery_cid(gen_sno);
			if(Utility.isNullOrEmpty(partyDts.getP_code())) {
				partyDts.setP_name(cust_bussiness_partnerRepository.getCustomer(partyDts.getP_code()).getCp_name());
			}else {partyDts.setP_name("0");}
			partyDts.setCompany_id(dChallan.getCompany_id());
			partyDts.setFin_year(dChallan.getFin_year());
			partyDts.setModified_type("INSERTED");
			partyDts.setInserted_by(dChallan.getInserted_by());
			partyDts.setInserted_on(dChallan.getInserted_on());
			partyDts.setUpdated_by("NA");
			partyDts.setUpdated_on(ldt);
			partyDts.setDeleted_by("NA");
			partyDts.setDeleted_on(ldt);
		}
		dChallan.setDelivery_challan_Party_Dtls(partySet);
		
		//Static
		Set<Delivery_challan_Shipment_Dtls> deliverySet = new HashSet<Delivery_challan_Shipment_Dtls>();
		deliverySet.add(dChallan.getDelivery_challan_Shipment_Dtls());
		for(Delivery_challan_Shipment_Dtls dlvDtls : deliverySet)
		{
			dlvDtls.setDelivery_cid(gen_sno);
			if(Utility.isNullOrEmpty(dlvDtls.getShip_addr()))
			{
				
			}
			else 
			{
				dlvDtls.setShip_addr("0");
			}
			dlvDtls.setCompany_id(dChallan.getCompany_id());
			dlvDtls.setFin_year(dChallan.getFin_year());
			dlvDtls.setModified_type("INSERTED");
			dlvDtls.setInserted_by(dChallan.getInserted_by());
			dlvDtls.setInserted_on(dChallan.getInserted_on());
			dlvDtls.setUpdated_by("NA");
			dlvDtls.setUpdated_on(ldt);
			dlvDtls.setDeleted_by("NA");
			dlvDtls.setDeleted_on(ldt);
		}
		if(!deliverySet.isEmpty())
		{
			dChallan.setDelivery_challan_Shipment_Dtls(deliverySet.iterator().next());
		}
		
		//Static
		Set<Delivery_challan_weight_dtl> weSet = new HashSet<Delivery_challan_weight_dtl>();
		weSet.add(dChallan.getDelivery_challan_weight_dtl());
		for(Delivery_challan_weight_dtl weDtls : weSet)
		{
			weDtls.setDelivery_cid(gen_sno);
			weDtls.setCompany_id(dChallan.getCompany_id());
			weDtls.setFin_year(dChallan.getFin_year());
			weDtls.setModified_type("INSERTED");
			weDtls.setInserted_by(dChallan.getInserted_by());
			weDtls.setInserted_on(dChallan.getInserted_on());
			weDtls.setUpdated_by("NA");
			weDtls.setUpdated_on(ldt);
			weDtls.setDeleted_by("NA");
			weDtls.setDeleted_on(ldt);
			
			weDtls.setOwn_uom_name(customUomMasterRepository.getCustomUomById(weDtls.getOwn_uom()).getDescription());
		}
		if(!weSet.isEmpty())
		{
			dChallan.setDelivery_challan_weight_dtl(weSet.iterator().next());
		}
		
		//Static
		Set<Delivery_challan_Trans_Info> trnsSet = new HashSet<Delivery_challan_Trans_Info>();
		trnsSet.add(dChallan.getDelivery_challan_Trans_Info());
		for(Delivery_challan_Trans_Info trnsDtls : trnsSet)
		{
			trnsDtls.setDelivery_cid(gen_sno);
			System.out.println("Transport : : "+trnsDtls.getTrans_code());
			if(Utility.isNullOrEmpty(trnsDtls.getTrans_code())) 
			{
				if(trnsDtls.getTrans_code().compareToIgnoreCase("0")==0)
				{
					trnsDtls.setTransporter_name("0");
				}
				else
				{
					trnsDtls.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(trnsDtls.getTrans_code()).getBp_name());
				}
			}
			else{trnsDtls.setTransporter_name("0");};
			
			trnsDtls.setCompany_id(dChallan.getCompany_id());
			trnsDtls.setFin_year(dChallan.getFin_year());
			trnsDtls.setModified_type("INSERTED");
			trnsDtls.setInserted_by(dChallan.getInserted_by());
			trnsDtls.setInserted_on(dChallan.getInserted_on());
			trnsDtls.setUpdated_by("NA");
			trnsDtls.setUpdated_on(ldt);
			trnsDtls.setDeleted_by("NA");
			trnsDtls.setDeleted_on(ldt);
			
			
			trnsDtls.setVehicle_no(vehicleMasterRepository.getVehicleDetails(trnsDtls.getVehle_no()).getVehicle_no());
			
		}
		if(!trnsSet.isEmpty())
		{
			dChallan.setDelivery_challan_Trans_Info(trnsSet.iterator().next());
		}
		//dynamic
		Set<Delivery_challan_Chgs_dyn> trnasportcharges = new HashSet<Delivery_challan_Chgs_dyn>();
		trnasportcharges.addAll(dChallan.getDelivery_challan_Chgs_dyn());
		for(Delivery_challan_Chgs_dyn charges : trnasportcharges)
		{
			charges.setDelivery_cid(gen_sno);
			charges.setChallan_no(gen_tslno);
			charges.setCompany_id(dChallan.getCompany_id());
			charges.setFin_year(dChallan.getFin_year());
			charges.setModified_type("INSERTED");
			charges.setInserted_by(dChallan.getInserted_by());
			charges.setInserted_on(dChallan.getInserted_on());
			charges.setUpdated_by(dChallan.getUpdated_by());
			charges.setUpdated_on(dChallan.getUpdated_on());
			charges.setDeleted_by("NA");
			charges.setDeleted_on(ldt);
		}
		dChallan.setDelivery_challan_Chgs_dyn(trnasportcharges);
		
		//Dynamic
		Set<Delivery_challan_Docs> docSet = new HashSet<Delivery_challan_Docs>();
		docSet.addAll(dChallan.getDelivery_challan_Docs());
		for(Delivery_challan_Docs docDts : docSet)
		{
			docDts.setDelivery_cid(gen_sno);
			docDts.setCompany_id(dChallan.getCompany_id());
			docDts.setFin_year(dChallan.getFin_year());
			docDts.setModified_type("INSERTED");
			docDts.setInserted_by(dChallan.getInserted_by());
			docDts.setInserted_on(dChallan.getInserted_on());
			docDts.setUpdated_by("NA");
			docDts.setUpdated_on(ldt);
			docDts.setDeleted_by("NA");
			docDts.setDeleted_on(ldt);
		}
		dChallan.setDelivery_challan_Docs(docSet);
		
		return dChallanRepository.save(dChallan);
	}
	
	@Transactional
	public Delivery_challan update(Delivery_challan dChallan,Long id)
	{
		Optional<Delivery_challan> op = dChallanRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		dChallan.setModified_type("INSERTED");
		dChallan.setInserted_by(op.get().getInserted_by());
		dChallan.setInserted_on(op.get().getInserted_on());
		dChallan.setUpdated_by(userRepository.getUserDetails(dChallan.getUsername()).getName());
		dChallan.setUpdated_on(ldt);
		dChallan.setDeleted_by("NA");
		dChallan.setDeleted_on(ldt);
		dChallan.setInvoicestatus("No");
		dChallan.setSales_returnstatus(op.get().getSales_returnstatus());
		dChallan.setSalereturn_id(op.get().getSalereturn_id());
		dChallan.setSales_invoice_id(op.get().getSales_invoice_id());
		
		dChallan.setChallandate(dChallan.getChallan_date());
		dChallan.setChallanno(dChallan.getChallan_no());
		
		dChallan.setInv_type_name(invoice_typeRepository.getSalesInvTypesDtls(dChallan.getInv_type()).getInvtype_name());
		
		System.out.println(" ref :: "+dChallan.getReferance_id());
		dChallan.setAdviceno(wm_loading_adviceRepository.getLoadingDetails(dChallan.getReferance_id()).getAdvice_no());
		
		
		if(dChallan.getRef_type().compareToIgnoreCase("Loading Advice")==0 && dChallan.getReferance_id().compareToIgnoreCase("0")==0)
		{
			dChallan.setReferance_id(op.get().getReferance_id());
		}
		else
		{
			dChallan.setReferance_id(dChallan.getReferance_id());
		}
		
		if(Utility.isNullOrEmpty(dChallan.getParty())) {
			dChallan.setPartyname(cust_bussiness_partnerRepository.getCustomer(dChallan.getParty()).getCp_name());
		}else {dChallan.setPartyname("None");}
		//System.err.println("ref:>>>> "+dChallan.getReferance_id());
		
		if(dChallan.getRef_type().compareTo("Open Delivery Challan")!=0) 
		{
			if(op.get().getReferance_id().substring(0,3).compareTo("WLA")==0)
			{
				wm_loading_adviceRepository.updateDelvStatus(dChallan.getReferance_id(),true);
			}
		}else {dChallan.setReferance_id(op.get().getDelivery_cid());}
		
		if(op.isPresent()){
			dChallan.setId(id);
		}
		
		
		
	//	System.err.println("SP: >> "+radvitemqty+" , "+radvpackqty+" , "+radvitemcode+" , "+radvpackcode+" , "+op.get().getBusiness_unit()+" , "+op.get().getFin_year());
		// stops on 2-6-2022 when checking update delv challan
		//int s=dChallanRepository.callSoldItemStockDeliveryReverse(radvitemqty,radvpackqty,radvitemcode,radvpackcode,op.get().getBusiness_unit(),op.get().getFin_year());
		
		Set<Item_opening_stk_dtls> iStk_dtls=new HashSet<Item_opening_stk_dtls>();
		
		Set<Item_opening_stk_pack_dtls> iPack_dtls=new HashSet<Item_opening_stk_pack_dtls>();
		
		if(dChallan.isJobwork()==true)
		{
			dChallan.getDelivery_challan_Item_Dtls().clear();
			
			dChallanRepository.delvJobItemupdate(op.get().getDelivery_cid(),"UPDATED");
			
			Set<Delivery_challan_Item_Dtls_for_jobwork> jobSet=new HashSet<Delivery_challan_Item_Dtls_for_jobwork>();
			jobSet.addAll(dChallan.getDelivery_challan_Item_Dtls_for_jobwork());
			for(Delivery_challan_Item_Dtls_for_jobwork jobwork:jobSet) 
			{
				jobwork.setDelivery_cid(dChallan.getDelivery_cid());
				
				if(Utility.isNullOrEmpty(jobwork.getJob_item())) {
					jobwork.setJob_item_name(item_masterRepository.itemNameById(jobwork.getJob_item()).getItem_name());
				}else{jobwork.setJob_item_name("0");};
				
				if(Utility.isNullOrEmpty(jobwork.getJob_packing())) {
					jobwork.setJob_packing_name(item_masterRepository.itemNameById(jobwork.getJob_packing()).getItem_name());
				}else{jobwork.setJob_packing_name("0");};
				
				
				jobwork.setCompany_id(dChallan.getCompany_id());
				jobwork.setFin_year(dChallan.getFin_year());
				jobwork.setModified_type("INSERTED");
				jobwork.setInserted_by(dChallan.getInserted_by());
				jobwork.setInserted_on(dChallan.getInserted_on());
				jobwork.setUpdated_by(userRepository.getUserDetails(dChallan.getUsername()).getName());
				jobwork.setUpdated_on(ldt);
				jobwork.setDeleted_by("NA");
				jobwork.setDeleted_on(ldt);
			}
			dChallan.setDelivery_challan_Item_Dtls_for_jobwork(jobSet);
		}
		else
		{
			List<Delivery_challan_Item_Dtls> delvItem_Dtls=new ArrayList<Delivery_challan_Item_Dtls>();
			delvItem_Dtls.addAll(delivery_challan_Item_DtlsRepository.findAll());
			
			List<Delivery_challan_Item_Dtls> dChallanItemDtls = delvItem_Dtls
					  .stream()
					  .filter(c -> c.getDelivery_cid().equals(op.get().getDelivery_cid()))
					  .collect(Collectors.toList());
			
			String radvitemcode="",radvitemqty="",radvpackcode="",radvpackqty="";
			for(Delivery_challan_Item_Dtls dCitems : dChallanItemDtls) {	
				//System.err.println("ItemId: "+dCitems.getItem_code()+"PackId: "+dCitems.getPacking()+"S Qty: "+dCitems.getSquantity()+"Qty: "+dCitems.getQuantity());
				radvitemcode +=dCitems.getItem_code()+",";
				radvitemqty +=dCitems.getQuantity()+",";
				radvpackcode +=dCitems.getPacking()+",";
				radvpackqty +=dCitems.getSquantity()+",";
			}
			radvitemcode=radvitemcode.substring(0,(radvitemcode.length()-1));
			radvitemqty=radvitemqty.substring(0,(radvitemqty.length()-1));
			radvpackcode=radvpackcode.substring(0,(radvpackcode.length()-1));
			radvpackqty=radvpackqty.substring(0,(radvpackqty.length()-1));
			
			//Dynamic
			dChallan.getDelivery_challan_Item_Dtls_for_jobwork().clear();
			
			delivery_challan_Item_DtlsRepository.deliveryChallanItemDtlsupdate(dChallan.getDelivery_cid(),"UPDATED");
			
			Set<Delivery_challan_Item_Dtls> itemSet = new HashSet<Delivery_challan_Item_Dtls>();
			itemSet.addAll(dChallan.getDelivery_challan_Item_Dtls());
			for(Delivery_challan_Item_Dtls itemDts : itemSet)
			{
				itemDts.setDelivery_cid(dChallan.getDelivery_cid());
				itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
				if(Utility.isNullOrEmpty(itemDts.getPacking())) {
					itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
				}else {itemDts.setPacking_name("0");}
				itemDts.setCompany_id(dChallan.getCompany_id());
				itemDts.setFin_year(dChallan.getFin_year());
				itemDts.setModified_type("INSERTED");
				itemDts.setInserted_by(dChallan.getInserted_by());
				itemDts.setInserted_on(dChallan.getInserted_on());
				itemDts.setUpdated_by(dChallan.getUpdated_by());
				itemDts.setUpdated_on(dChallan.getUpdated_on());
				itemDts.setDeleted_by("NA");
				itemDts.setDeleted_on(ldt);
				
				itemDts.setTax_name(tax_code_detailsRepository.getTaxCodeDetails(itemDts.getTax_code()).getTax_name());
			}
			dChallan.setDelivery_challan_Item_Dtls(itemSet);
			
			//For Item Stock Update
			String advitemcode="",advitemqty="",advpackcode="",advpackqty="";
			if(Utility.isNullOrEmpty(dChallan.getBusiness_unit())) {
				for(Delivery_challan_Item_Dtls dcDtls:itemSet)
				{
					advitemcode +=dcDtls.getItem_code()+",";
					advitemqty +=dcDtls.getQuantity()+",";
					advpackcode +=dcDtls.getPacking()+",";
					advpackqty +=dcDtls.getSquantity()+",";
				}
				advitemcode=advitemcode.substring(0,(advitemcode.length()-1));
				advitemqty=advitemqty.substring(0,(advitemqty.length()-1));
				advpackcode=advpackcode.substring(0,(advpackcode.length()-1));
				advpackqty=advpackqty.substring(0,(advpackqty.length()-1));
				
				//System.err.println("SP: >> "+advitemqty+" , "+advpackqty+" , "+advitemcode+" , "+advpackcode+" , "+dChallan.getBusiness_unit()+" , "+dChallan.getFin_year());
				// stops on 2-6-2022 when checking update delv challan
				//int t=dChallanRepository.callSoldItemStockDelivery(advitemqty,advpackqty,advitemcode,advpackcode,dChallan.getBusiness_unit(),dChallan.getFin_year());
			}
		}
		
		
		//Dynamic
		delivery_challan_Party_DtlsRepository.deliveryChallanPartyDtlsupdate(dChallan.getDelivery_cid(),"UPDATED");
		
		Set<Delivery_challan_Party_Dtls> partySet = new HashSet<Delivery_challan_Party_Dtls>();
		partySet.addAll(dChallan.getDelivery_challan_Party_Dtls());
		for(Delivery_challan_Party_Dtls partyDts : partySet)
		{
			partyDts.setDelivery_cid(dChallan.getDelivery_cid());
			if(Utility.isNullOrEmpty(partyDts.getP_code())) {
				partyDts.setP_name(cust_bussiness_partnerRepository.getCustomer(partyDts.getP_code()).getCp_name());
			}else {partyDts.setP_name("0");}
			partyDts.setCompany_id(dChallan.getCompany_id());
			partyDts.setFin_year(dChallan.getFin_year());
			partyDts.setModified_type("INSERTED");
			partyDts.setInserted_by(dChallan.getInserted_by());
			partyDts.setInserted_on(dChallan.getInserted_on());
			partyDts.setUpdated_by(dChallan.getUpdated_by());
			partyDts.setUpdated_on(dChallan.getUpdated_on());
			partyDts.setDeleted_by("NA");
			partyDts.setDeleted_on(ldt);
		}
		dChallan.setDelivery_challan_Party_Dtls(partySet);
		
		//Static
		delivery_challan_Shipment_DtlsRepository.deliveryChallanShipDtlsupdate(dChallan.getDelivery_cid(),"UPDATED");
		
		Set<Delivery_challan_Shipment_Dtls> deliverySet = new HashSet<Delivery_challan_Shipment_Dtls>();
		deliverySet.add(dChallan.getDelivery_challan_Shipment_Dtls());
		for(Delivery_challan_Shipment_Dtls dlvDtls : deliverySet)
		{
			dlvDtls.setDelivery_cid(dChallan.getDelivery_cid());
			dlvDtls.setCompany_id(dChallan.getCompany_id());
			dlvDtls.setFin_year(dChallan.getFin_year());
			dlvDtls.setModified_type("INSERTED");
			dlvDtls.setInserted_by(dChallan.getInserted_by());
			dlvDtls.setInserted_on(dChallan.getInserted_on());
			dlvDtls.setUpdated_by(dChallan.getUpdated_by());
			dlvDtls.setUpdated_on(dChallan.getUpdated_on());
			dlvDtls.setDeleted_by("NA");
			dlvDtls.setDeleted_on(ldt);
		}
		if(!deliverySet.isEmpty()) {
			dChallan.setDelivery_challan_Shipment_Dtls(deliverySet.iterator().next());
		}
		
		//Static
		delivery_challan_weight_dtlRepository.deliveryChallanWeightDtlsupdate(dChallan.getDelivery_cid(),"UPDATED");
		
		Set<Delivery_challan_weight_dtl> weSet = new HashSet<Delivery_challan_weight_dtl>();
		weSet.add(dChallan.getDelivery_challan_weight_dtl());
		for(Delivery_challan_weight_dtl weDtls : weSet)
		{
			weDtls.setDelivery_cid(dChallan.getDelivery_cid());
			weDtls.setCompany_id(dChallan.getCompany_id());
			weDtls.setFin_year(dChallan.getFin_year());
			weDtls.setModified_type("INSERTED");
			weDtls.setInserted_by(dChallan.getInserted_by());
			weDtls.setInserted_on(dChallan.getInserted_on());
			weDtls.setUpdated_by(dChallan.getUpdated_by());
			weDtls.setUpdated_on(dChallan.getUpdated_on());
			weDtls.setDeleted_by("NA");
			weDtls.setDeleted_on(ldt);
			
			weDtls.setOwn_uom_name(customUomMasterRepository.getCustomUomById(weDtls.getOwn_uom()).getDescription());
		}
		if(!weSet.isEmpty()) {
			dChallan.setDelivery_challan_weight_dtl(weSet.iterator().next());
		}
		
		//Static
		delivery_challan_Trans_InfoRepository.deliveryChallanTrInfoupdate(dChallan.getDelivery_cid(),"UPDATED");
		
		Set<Delivery_challan_Trans_Info> trnsSet = new HashSet<Delivery_challan_Trans_Info>();
		trnsSet.add(dChallan.getDelivery_challan_Trans_Info());
		for(Delivery_challan_Trans_Info trnsDtls : trnsSet)
		{
			trnsDtls.setDelivery_cid(dChallan.getDelivery_cid());
			System.out.println("Transport Update : : "+trnsDtls.getTrans_code());
			if(Utility.isNullOrEmpty(trnsDtls.getTrans_code())) 
			{
				if(trnsDtls.getTrans_code().compareToIgnoreCase("0")==0)
				{
					trnsDtls.setTransporter_name("0");
				}
				else
				{
					trnsDtls.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(trnsDtls.getTrans_code()).getBp_name());
				}
			}
			else{trnsDtls.setTransporter_name("0");};
			
			trnsDtls.setCompany_id(dChallan.getCompany_id());
			trnsDtls.setFin_year(dChallan.getFin_year());
			trnsDtls.setModified_type("INSERTED");
			trnsDtls.setInserted_by(dChallan.getInserted_by());
			trnsDtls.setInserted_on(dChallan.getInserted_on());
			trnsDtls.setUpdated_by(dChallan.getUpdated_by());
			trnsDtls.setUpdated_on(dChallan.getUpdated_on());
			trnsDtls.setDeleted_by("NA");
			trnsDtls.setDeleted_on(ldt);
			
			trnsDtls.setVehicle_no(vehicleMasterRepository.getVehicleDetails(trnsDtls.getVehle_no()).getVehicle_no());
		}
		if(!trnsSet.isEmpty()){
			dChallan.setDelivery_challan_Trans_Info(trnsSet.iterator().next());
		}
		
		//Dynamic transportaion charge
		
			delivery_challan_Chgs_dynRepository.delivery_challan_Chgs_dynupdate(dChallan.getDelivery_cid(),"UPDATED");
					
				Set<Delivery_challan_Chgs_dyn> trnasportcharges = new HashSet<Delivery_challan_Chgs_dyn>();
				trnasportcharges.addAll(dChallan.getDelivery_challan_Chgs_dyn());
				for(Delivery_challan_Chgs_dyn charges : trnasportcharges)
				{
					charges.setDelivery_cid(dChallan.getDelivery_cid());
					charges.setChallan_no(dChallan.getChallan_no());
					charges.setCompany_id(dChallan.getCompany_id());
					charges.setFin_year(dChallan.getFin_year());
					charges.setModified_type("INSERTED");
					charges.setInserted_by(dChallan.getInserted_by());
					charges.setInserted_on(dChallan.getInserted_on());
					charges.setUpdated_by(dChallan.getUpdated_by());
					charges.setUpdated_on(dChallan.getUpdated_on());
					charges.setDeleted_by("NA");
					charges.setDeleted_on(ldt);
				}
				dChallan.setDelivery_challan_Chgs_dyn(trnasportcharges);
				
		
		//Dynamic
		delivery_challan_DocsRepository.deliveryChallanDocupdate(dChallan.getDelivery_cid(),"UPDATED");
		
		Set<Delivery_challan_Docs> docSet = new HashSet<Delivery_challan_Docs>();
		docSet.addAll(dChallan.getDelivery_challan_Docs());
		for(Delivery_challan_Docs docDts : docSet)
		{
			docDts.setDelivery_cid(dChallan.getDelivery_cid());
			docDts.setCompany_id(dChallan.getCompany_id());
			docDts.setFin_year(dChallan.getFin_year());
			docDts.setModified_type("INSERTED");
			docDts.setInserted_by(dChallan.getInserted_by());
			docDts.setInserted_on(dChallan.getInserted_on());
			docDts.setUpdated_by(dChallan.getUpdated_by());
			docDts.setUpdated_on(dChallan.getUpdated_on());
			docDts.setDeleted_by("NA");
			docDts.setDeleted_on(ldt);
		}
		dChallan.setDelivery_challan_Docs(docSet);
				
		//Dynamic
		delivery_challan_Broker_DtlsRepository.deliveryChallanBrokerDtlsupdate(dChallan.getDelivery_cid(),"UPDATED");
		Set<Delivery_challan_Broker_Dtls> BrokerSet = new HashSet<Delivery_challan_Broker_Dtls>();
		BrokerSet.addAll(dChallan.getDelivery_challan_Broker_Dtls());
		for(Delivery_challan_Broker_Dtls brokerDts : BrokerSet)
		{
			brokerDts.setDelivery_cid(dChallan.getDelivery_cid());
			brokerDts.setCompany_id(dChallan.getCompany_id());
			brokerDts.setFin_year(dChallan.getFin_year());
			brokerDts.setModified_type("INSERTED");
			brokerDts.setInserted_by(dChallan.getInserted_by());
			brokerDts.setInserted_on(dChallan.getInserted_on());
			brokerDts.setUpdated_by(dChallan.getUpdated_by());
			brokerDts.setUpdated_on(dChallan.getUpdated_on());
			brokerDts.setDeleted_by("NA");
			brokerDts.setDeleted_on(ldt);
		}
		dChallan.setDelivery_challan_Broker_Dtls(BrokerSet);

		return dChallanRepository.save(dChallan);
		//return dChallan;
	}
	
	
	@Transactional
	public Delivery_challan updateDlvChallantransport(Delivery_challan dChallan,Long id)
	{

		Optional<Delivery_challan> op = dChallanRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		dChallan.setModified_type("INSERTED");
		dChallan.setInserted_by(op.get().getInserted_by());
		dChallan.setInserted_on(op.get().getInserted_on());
		dChallan.setUpdated_by(userRepository.getUserDetails(dChallan.getUsername()).getName());
		dChallan.setUpdated_on(ldt);
		dChallan.setDeleted_by("NA");
		dChallan.setDeleted_on(ldt);
		dChallan.setInvoicestatus("No");
		dChallan.setSales_returnstatus(op.get().getSales_returnstatus());
		dChallan.setSalereturn_id(op.get().getSalereturn_id());
		dChallan.setSales_invoice_id(op.get().getSales_invoice_id());
		
		dChallan.setChallandate(dChallan.getChallan_date());
		dChallan.setChallanno(dChallan.getChallan_no());
		
		dChallan.setInv_type_name(invoice_typeRepository.getSalesInvTypesDtls(dChallan.getInv_type()).getInvtype_name());
		
		System.out.println(" ref :: "+dChallan.getReferance_id());
		dChallan.setAdviceno(wm_loading_adviceRepository.getLoadingDetails(dChallan.getReferance_id()).getAdvice_no());
		
		
		if(dChallan.getRef_type().compareToIgnoreCase("Loading Advice")==0 && dChallan.getReferance_id().compareToIgnoreCase("0")==0)
		{
			dChallan.setReferance_id(op.get().getReferance_id());
		}
		else
		{
			dChallan.setReferance_id(dChallan.getReferance_id());
		}
		
		if(Utility.isNullOrEmpty(dChallan.getParty())) {
			dChallan.setPartyname(cust_bussiness_partnerRepository.getCustomer(dChallan.getParty()).getCp_name());
		}else {dChallan.setPartyname("None");}
		//System.err.println("ref:>>>> "+dChallan.getReferance_id());
		
		if(dChallan.getRef_type().compareTo("Open Delivery Challan")!=0) 
		{
			if(op.get().getReferance_id().substring(0,3).compareTo("WLA")==0)
			{
				wm_loading_adviceRepository.updateDelvStatus(dChallan.getReferance_id(),true);
			}
		}else {dChallan.setReferance_id(op.get().getDelivery_cid());}
		
		if(op.isPresent()){
			dChallan.setId(id);
		}
		
		List<Delivery_challan_Item_Dtls> delvItem_Dtls=new ArrayList<Delivery_challan_Item_Dtls>();
		delvItem_Dtls.addAll(delivery_challan_Item_DtlsRepository.findAll());
		
		List<Delivery_challan_Item_Dtls> dChallanItemDtls = delvItem_Dtls
				  .stream()
				  .filter(c -> c.getDelivery_cid().equals(op.get().getDelivery_cid()))
				  .collect(Collectors.toList());
		
		String radvitemcode="",radvitemqty="",radvpackcode="",radvpackqty="";
		for(Delivery_challan_Item_Dtls dCitems : dChallanItemDtls) {	
			
			radvitemcode +=dCitems.getItem_code()+",";
			radvitemqty +=dCitems.getQuantity()+",";
			radvpackcode +=dCitems.getPacking()+",";
			radvpackqty +=dCitems.getSquantity()+",";
		}
		radvitemcode=radvitemcode.substring(0,(radvitemcode.length()-1));
		radvitemqty=radvitemqty.substring(0,(radvitemqty.length()-1));
		radvpackcode=radvpackcode.substring(0,(radvpackcode.length()-1));
		radvpackqty=radvpackqty.substring(0,(radvpackqty.length()-1));
		
	
		
		Set<Item_opening_stk_dtls> iStk_dtls=new HashSet<Item_opening_stk_dtls>();
		
		Set<Item_opening_stk_pack_dtls> iPack_dtls=new HashSet<Item_opening_stk_pack_dtls>();
		
		//Dynamic
		delivery_challan_Item_DtlsRepository.deliveryChallanItemDtlsupdate(dChallan.getDelivery_cid(),"UPDATED");
		
		Set<Delivery_challan_Item_Dtls> itemSet = new HashSet<Delivery_challan_Item_Dtls>();
		itemSet.addAll(dChallan.getDelivery_challan_Item_Dtls());
		for(Delivery_challan_Item_Dtls itemDts : itemSet)
		{
			itemDts.setDelivery_cid(dChallan.getDelivery_cid());
			itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
			if(Utility.isNullOrEmpty(itemDts.getPacking())) {
				itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
			}else {itemDts.setPacking_name("0");}
			itemDts.setCompany_id(dChallan.getCompany_id());
			itemDts.setFin_year(dChallan.getFin_year());
			itemDts.setModified_type("INSERTED");
			itemDts.setInserted_by(dChallan.getInserted_by());
			itemDts.setInserted_on(dChallan.getInserted_on());
			itemDts.setUpdated_by(dChallan.getUpdated_by());
			itemDts.setUpdated_on(dChallan.getUpdated_on());
			itemDts.setDeleted_by("NA");
			itemDts.setDeleted_on(ldt);
			
			itemDts.setTax_name(tax_code_detailsRepository.getTaxCodeDetails(itemDts.getTax_code()).getTax_name());
		}
		dChallan.setDelivery_challan_Item_Dtls(itemSet);
		
		//For Item Stock Update
		String advitemcode="",advitemqty="",advpackcode="",advpackqty="";
		if(Utility.isNullOrEmpty(dChallan.getBusiness_unit())) {
			for(Delivery_challan_Item_Dtls dcDtls:itemSet)
			{
				advitemcode +=dcDtls.getItem_code()+",";
				advitemqty +=dcDtls.getQuantity()+",";
				advpackcode +=dcDtls.getPacking()+",";
				advpackqty +=dcDtls.getSquantity()+",";
			}
			advitemcode=advitemcode.substring(0,(advitemcode.length()-1));
			advitemqty=advitemqty.substring(0,(advitemqty.length()-1));
			advpackcode=advpackcode.substring(0,(advpackcode.length()-1));
			advpackqty=advpackqty.substring(0,(advpackqty.length()-1));
			
			//System.err.println("SP: >> "+advitemqty+" , "+advpackqty+" , "+advitemcode+" , "+advpackcode+" , "+dChallan.getBusiness_unit()+" , "+dChallan.getFin_year());
			// stops on 2-6-2022 when checking update delv challan
			//int t=dChallanRepository.callSoldItemStockDelivery(advitemqty,advpackqty,advitemcode,advpackcode,dChallan.getBusiness_unit(),dChallan.getFin_year());
		}
		
		//Dynamic
		delivery_challan_Party_DtlsRepository.deliveryChallanPartyDtlsupdate(dChallan.getDelivery_cid(),"UPDATED");
		
		Set<Delivery_challan_Party_Dtls> partySet = new HashSet<Delivery_challan_Party_Dtls>();
		partySet.addAll(dChallan.getDelivery_challan_Party_Dtls());
		for(Delivery_challan_Party_Dtls partyDts : partySet)
		{
			partyDts.setDelivery_cid(dChallan.getDelivery_cid());
			if(Utility.isNullOrEmpty(partyDts.getP_code())) {
				partyDts.setP_name(cust_bussiness_partnerRepository.getCustomer(partyDts.getP_code()).getCp_name());
			}else {partyDts.setP_name("0");}
			partyDts.setCompany_id(dChallan.getCompany_id());
			partyDts.setFin_year(dChallan.getFin_year());
			partyDts.setModified_type("INSERTED");
			partyDts.setInserted_by(dChallan.getInserted_by());
			partyDts.setInserted_on(dChallan.getInserted_on());
			partyDts.setUpdated_by(dChallan.getUpdated_by());
			partyDts.setUpdated_on(dChallan.getUpdated_on());
			partyDts.setDeleted_by("NA");
			partyDts.setDeleted_on(ldt);
		}
		dChallan.setDelivery_challan_Party_Dtls(partySet);
		
		//Static
		delivery_challan_Shipment_DtlsRepository.deliveryChallanShipDtlsupdate(dChallan.getDelivery_cid(),"UPDATED");
		
		Set<Delivery_challan_Shipment_Dtls> deliverySet = new HashSet<Delivery_challan_Shipment_Dtls>();
		deliverySet.add(dChallan.getDelivery_challan_Shipment_Dtls());
		for(Delivery_challan_Shipment_Dtls dlvDtls : deliverySet)
		{
			dlvDtls.setDelivery_cid(dChallan.getDelivery_cid());
			dlvDtls.setCompany_id(dChallan.getCompany_id());
			dlvDtls.setFin_year(dChallan.getFin_year());
			dlvDtls.setModified_type("INSERTED");
			dlvDtls.setInserted_by(dChallan.getInserted_by());
			dlvDtls.setInserted_on(dChallan.getInserted_on());
			dlvDtls.setUpdated_by(dChallan.getUpdated_by());
			dlvDtls.setUpdated_on(dChallan.getUpdated_on());
			dlvDtls.setDeleted_by("NA");
			dlvDtls.setDeleted_on(ldt);
		}
		if(!deliverySet.isEmpty()) {
			dChallan.setDelivery_challan_Shipment_Dtls(deliverySet.iterator().next());
		}
		
		
		//Static
		delivery_challan_weight_dtlRepository.deliveryChallanWeightDtlsupdate(dChallan.getDelivery_cid(),"UPDATED");
		
		Set<Delivery_challan_weight_dtl> weSet = new HashSet<Delivery_challan_weight_dtl>();
		weSet.add(dChallan.getDelivery_challan_weight_dtl());
		for(Delivery_challan_weight_dtl weDtls : weSet)
		{
			weDtls.setDelivery_cid(dChallan.getDelivery_cid());
			weDtls.setCompany_id(dChallan.getCompany_id());
			weDtls.setFin_year(dChallan.getFin_year());
			weDtls.setModified_type("INSERTED");
			weDtls.setInserted_by(dChallan.getInserted_by());
			weDtls.setInserted_on(dChallan.getInserted_on());
			weDtls.setUpdated_by(dChallan.getUpdated_by());
			weDtls.setUpdated_on(dChallan.getUpdated_on());
			weDtls.setDeleted_by("NA");
			weDtls.setDeleted_on(ldt);
			
			weDtls.setOwn_uom_name(customUomMasterRepository.getCustomUomById(weDtls.getOwn_uom()).getDescription());
		}
		if(!weSet.isEmpty()) {
			dChallan.setDelivery_challan_weight_dtl(weSet.iterator().next());
		}
		
		//Static
		delivery_challan_Trans_InfoRepository.deliveryChallanTrInfoupdate(dChallan.getDelivery_cid(),"UPDATED");
		
		Set<Delivery_challan_Trans_Info> trnsSet = new HashSet<Delivery_challan_Trans_Info>();
		trnsSet.add(dChallan.getDelivery_challan_Trans_Info());
		for(Delivery_challan_Trans_Info trnsDtls : trnsSet)
		{
			trnsDtls.setDelivery_cid(dChallan.getDelivery_cid());
			System.out.println("Transport Update : : "+trnsDtls.getTrans_code());
			if(Utility.isNullOrEmpty(trnsDtls.getTrans_code())) 
			{
				if(trnsDtls.getTrans_code().compareToIgnoreCase("0")==0)
				{
					trnsDtls.setTransporter_name("0");
				}
				else
				{
					trnsDtls.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(trnsDtls.getTrans_code()).getBp_name());
				}
			}
			else{trnsDtls.setTransporter_name("0");};
			
			trnsDtls.setCompany_id(dChallan.getCompany_id());
			trnsDtls.setFin_year(dChallan.getFin_year());
			trnsDtls.setModified_type("INSERTED");
			trnsDtls.setInserted_by(dChallan.getInserted_by());
			trnsDtls.setInserted_on(dChallan.getInserted_on());
			trnsDtls.setUpdated_by(dChallan.getUpdated_by());
			trnsDtls.setUpdated_on(dChallan.getUpdated_on());
			trnsDtls.setDeleted_by("NA");
			trnsDtls.setDeleted_on(ldt);
			
			trnsDtls.setVehicle_no(vehicleMasterRepository.getVehicleDetails(trnsDtls.getVehle_no()).getVehicle_no());
		}
		if(!trnsSet.isEmpty()){
			dChallan.setDelivery_challan_Trans_Info(trnsSet.iterator().next());
		}
		
		
		//Dynamic transportaion charge
	
		delivery_challan_Chgs_dynRepository.delivery_challan_Chgs_dynupdate(dChallan.getDelivery_cid(),"UPDATED");
				
				Set<Delivery_challan_Chgs_dyn> trnasportcharges = new HashSet<Delivery_challan_Chgs_dyn>();
				trnasportcharges.addAll(dChallan.getDelivery_challan_Chgs_dyn());
				for(Delivery_challan_Chgs_dyn charges : trnasportcharges)
				{
					charges.setDelivery_cid(dChallan.getDelivery_cid());
					charges.setCompany_id(dChallan.getCompany_id());
					charges.setFin_year(dChallan.getFin_year());
					charges.setModified_type("INSERTED");
					charges.setInserted_by(dChallan.getInserted_by());
					charges.setInserted_on(dChallan.getInserted_on());
					charges.setUpdated_by(dChallan.getUpdated_by());
					charges.setUpdated_on(dChallan.getUpdated_on());
					charges.setDeleted_by("NA");
					charges.setDeleted_on(ldt);
				}
				dChallan.setDelivery_challan_Chgs_dyn(trnasportcharges);
		
		
		
		//Dynamic
		delivery_challan_DocsRepository.deliveryChallanDocupdate(dChallan.getDelivery_cid(),"UPDATED");
		
		Set<Delivery_challan_Docs> docSet = new HashSet<Delivery_challan_Docs>();
		docSet.addAll(dChallan.getDelivery_challan_Docs());
		for(Delivery_challan_Docs docDts : docSet)
		{
			docDts.setDelivery_cid(dChallan.getDelivery_cid());
			docDts.setCompany_id(dChallan.getCompany_id());
			docDts.setFin_year(dChallan.getFin_year());
			docDts.setModified_type("INSERTED");
			docDts.setInserted_by(dChallan.getInserted_by());
			docDts.setInserted_on(dChallan.getInserted_on());
			docDts.setUpdated_by(dChallan.getUpdated_by());
			docDts.setUpdated_on(dChallan.getUpdated_on());
			docDts.setDeleted_by("NA");
			docDts.setDeleted_on(ldt);
		}
		dChallan.setDelivery_challan_Docs(docSet);
				
		//Dynamic
		delivery_challan_Broker_DtlsRepository.deliveryChallanBrokerDtlsupdate(dChallan.getDelivery_cid(),"UPDATED");
		Set<Delivery_challan_Broker_Dtls> BrokerSet = new HashSet<Delivery_challan_Broker_Dtls>();
		BrokerSet.addAll(dChallan.getDelivery_challan_Broker_Dtls());
		for(Delivery_challan_Broker_Dtls brokerDts : BrokerSet)
		{
			brokerDts.setDelivery_cid(dChallan.getDelivery_cid());
			brokerDts.setCompany_id(dChallan.getCompany_id());
			brokerDts.setFin_year(dChallan.getFin_year());
			brokerDts.setModified_type("INSERTED");
			brokerDts.setInserted_by(dChallan.getInserted_by());
			brokerDts.setInserted_on(dChallan.getInserted_on());
			brokerDts.setUpdated_by(dChallan.getUpdated_by());
			brokerDts.setUpdated_on(dChallan.getUpdated_on());
			brokerDts.setDeleted_by("NA");
			brokerDts.setDeleted_on(ldt);
		}
		dChallan.setDelivery_challan_Broker_Dtls(BrokerSet);

		return dChallanRepository.save(dChallan);
		//return dChallan;
	
	}
	
	public List<Delivery_challan> findAll()
	{
		List<Delivery_challan> delvList=new ArrayList<Delivery_challan>();
		delvList.addAll(dChallanRepository.findDeliveryChallans());
		
		delvList.forEach((delv) ->{
			if(Utility.isNullOrEmpty(delv.getParty())) {
				delv.setParty(cust_bussiness_partnerRepository.getCustomer(delv.getParty()).getCp_name());
			}else {delv.setParty("None");}
			
			if(Utility.isNullOrEmpty(delv.getInv_type())) {
				delv.setInv_type(invoice_typeRepository.getSalesInvTypesDtls(delv.getInv_type()).getInvtype_name());
			}else {delv.setInv_type("None");}
		});
		return delvList;
	}
	
	
	
	 public Page<Delivery_challan_Pagination_DTO> getDeliveryChallans_pagination(int page,int size)
	  {
		  
			//PageRequest pageRequest = PageRequest.of(page, size,Sort.by("id").descending());
			PageRequest pageRequest = PageRequest.of(page, size,Sort.by("challandate").descending().and(Sort.by("challanno")).descending());
		    Page<Delivery_challan> pageResult = dChallanRepository.findAll(pageRequest);
		   
		    List<Delivery_challan_Pagination_DTO> delvList = pageResult
		    	      .stream()
		    	      .map((Delivery_challan delivery_challan) -> new Delivery_challan_Pagination_DTO(
		    	    		  delivery_challan.getId(),
		    	    		  delivery_challan.getDelivery_cid(),
		    	    		  delivery_challan.getChallan_date(),
		    	    		  delivery_challan.getChallan_no(),
		    	    		  delivery_challan.getParty(),
		    	    		  delivery_challan.getInv_type(),
		    	    		  delivery_challan.getInvoicestatus(),
		    	    		  delivery_challan.getRef_type(),
		    	    		  delivery_challan.getPrice_term(),delivery_challan.getModified_type())).filter(c -> c.getModified_type().equals("INSERTED")).collect(Collectors.toList());
		    	    	
		    delvList.forEach((delv) ->{
				if(Utility.isNullOrEmpty(delv.getPartyname())) {
					delv.setPartyname(cust_bussiness_partnerRepository.getCustomer(delv.getPartyname()).getCp_name());
				}else {delv.setPartyname("None");}
				
				if(Utility.isNullOrEmpty(delv.getInv_type_name())) {
					delv.setInv_type_name(invoice_typeRepository.getSalesInvTypesDtls(delv.getInv_type_name()).getInvtype_name());
				}else {delv.setInv_type_name("None");}
			});
		    	
		    return new PageImpl<>(delvList, pageRequest, pageResult.getTotalElements());
	  }
	 
	 public List<Map<String,Object>> searchDeliveryChallanFast(String orderno,String fromdate, String todate,String party1,String finyear)
		{
			List<Map<String,Object>> serchdeliverychallan =new ArrayList<Map<String,Object>>();
			String tablename="delivery_challan",party_name="party",order_no="challan_no",order_date="challan_date";
			serchdeliverychallan.addAll(dChallanRepository.getsearchdataFast(tablename,party_name,order_no,order_date,orderno,fromdate,todate,party1,finyear));
			return serchdeliverychallan;
		}
	 
	 public List<Delivery_challan_Pagination_DTO> searchDeliveryChallan(String orderno,String fromdate, String todate,String party1,String finyear)
		{
			List<Delivery_challan> serchdeliverychallan =new ArrayList<Delivery_challan>();
			//System.out.println("finyear :: "+finyear);
			//System.out.println(orderno+"///"+fromdate+"///"+todate+"///"+party1+"///"+finyear);
			String tablename="delivery_challan",party_name="party",order_no="challan_no",order_date="challan_date";
			serchdeliverychallan.addAll(dChallanRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,party1,finyear));
			//System.out.println("size:"+serchdeliverychallan.size());
			List<Delivery_challan_Pagination_DTO> delvList = new ArrayList<Delivery_challan_Pagination_DTO>();
			for(int i=0;i<serchdeliverychallan.size();i++) 
			{
			
				Delivery_challan_Pagination_DTO sale= new Delivery_challan_Pagination_DTO(
						serchdeliverychallan.get(i).getId(),
						serchdeliverychallan.get(i).getDelivery_cid(),
						serchdeliverychallan.get(i).getChallan_date(),
						serchdeliverychallan.get(i).getChallan_no(),
						serchdeliverychallan.get(i).getParty(),
						serchdeliverychallan.get(i).getInv_type(),
						serchdeliverychallan.get(i).getInvoicestatus(),
						serchdeliverychallan.get(i).getRef_type(),
						serchdeliverychallan.get(i).getPrice_term(),serchdeliverychallan.get(i).getModified_type());
				delvList.add(sale);
			}
			
		    delvList.forEach((delv) ->{
				if(Utility.isNullOrEmpty(delv.getPartyname())) {
					delv.setPartyname(cust_bussiness_partnerRepository.getCustomer(delv.getPartyname()).getCp_name());
				}else {delv.setPartyname("None");}
				
				if(Utility.isNullOrEmpty(delv.getInv_type_name())) {
					delv.setInv_type_name(invoice_typeRepository.getSalesInvTypesDtls(delv.getInv_type_name()).getInvtype_name());
				}else {delv.setInv_type_name("None");}
			});
			
		    
		    List<Delivery_challan_Pagination_DTO> finalData = delvList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Delivery_challan_Pagination_DTO::getChallan_date).
							  thenComparingInt(
	                          d -> d.getChallan_date().length() + d.getChallanno().length())
	                  .thenComparing(Delivery_challan_Pagination_DTO::getChallanno).reversed())
					  .collect(Collectors.toList());
		    
			//System.out.println(delvList.get(0));
			return finalData;
			
		}
	 
	public Delivery_challanDTO getDeliveryChallanDtls(String delivery_cid) {
		
		Delivery_challan modelList=dChallanRepository.getDeliveryChallanDtls(delivery_cid);
		Type listType = new TypeToken<Delivery_challanDTO>() {}.getType();
		
		Delivery_challanDTO dcDtls = new ModelMapper().map(modelList,listType);
		
			if(dcDtls.getReferance_id()!=null && dcDtls.getReferance_id().compareTo("0")!=0 && dcDtls.getReferance_id().substring(0, 2).compareTo("SO")==0) {
				dcDtls.setSalesorderno(sales_OrderRepository.getSalesOrderDetails(dcDtls.getReferance_id()).getOrder_no());
				dcDtls.setSalesorderdate(sales_OrderRepository.getSalesOrderDetails(dcDtls.getReferance_id()).getOrder_date());
			}
			else if(dcDtls.getReferance_id()!=null && dcDtls.getReferance_id().compareTo("0")!=0 && dcDtls.getReferance_id().substring(0, 3).compareTo("WLA")==0) {
				String ordId=wm_loading_adviceRepository.getLoadingDetails(dcDtls.getReferance_id()).getReferance_id();
				
				if(ordId.substring(0, 2).compareTo("SO")==0) {
					dcDtls.setSalesorderno(sales_OrderRepository.getSalesOrderDetails(ordId).getOrder_no());
					dcDtls.setSalesorderdate(sales_OrderRepository.getSalesOrderDetails(ordId).getOrder_date());
				}
			}else {dcDtls.setSalesorderno("");dcDtls.setSalesorderdate("");}
		return dcDtls;
	}
	
	public Map<String,Object> getDeliveryChallanDtlsFast(String delivery_cid) {
		Map<String,Object> modelList=dChallanRepository.getDeliveryChallanDtlsFast(delivery_cid);
			
		return modelList;
	}
	
	public Delivery_challan findOne(long id)
	{
		Optional<Delivery_challan> wla=this.dChallanRepository.findById(id);
		return wla.get();
	}
	
	public Sales_Order retriveDeliveryChallanOrderNo(String delvid)
	{
		Delivery_challan wla=dChallanRepository.getDeliveryChallanDtls(delvid); //reference_id //loading advice id
		//System.out.println("delivery_cid11::"+dChallanRepository.getDeliveryChallanDtls(delvid).getReferance_id());
			Wm_loading_advice ordid =new Wm_loading_advice();
			
			ordid =wm_loading_adviceRepository.getLoadingDetails(wla.getReferance_id());
			//System.out.println("delivery_cid2121::"+ordid.getReferance_id());
			Sales_Order ord_id = sales_OrderRepository.getSalesOrderDetails(ordid.getReferance_id());
			//System.out.println("delivery_cid4344::"+ord_id.getOrder_no());
		return ord_id;
	}
	public List<Delivery_challanDTO> deliveryChallanList(String invtype,String party,String invdate,String comp,String parentmodel) {
		
		
		
		
		List<Delivery_challan> modelList =new ArrayList<Delivery_challan>();
		if(parentmodel.compareToIgnoreCase("Sales Invoice")==0)
		{
			 modelList=dChallanRepository.deliveryChallanList(invtype,party,invdate,comp);
		}
		if(parentmodel.compareToIgnoreCase("Return Aproval")==0)
		{
			 modelList=dChallanRepository.deliveryChallanListreturn(invtype,party,invdate,comp);
		}
		
		Type listType = new TypeToken<List<Delivery_challanDTO>>() {}.getType();
		
		List<Delivery_challanDTO> chllnList = new ModelMapper().map(modelList,listType);
		
		return chllnList;
	}
	
	
    public List<Map<String, Object>> getDelvChallansnew(String invtype,String party,String invdate,String comp,String parentmodel) 
    {
    	List<Map<String, Object>> modelList =new ArrayList<>();
		//System.out.println("parentmodel::"+parentmodel+"//"+invtype+"//"+party+""+invdate);
    	if(parentmodel.compareToIgnoreCase("Sales Invoice")==0)
		{
			 modelList.addAll(dChallanRepository.deliveryChallanListnew(invtype,party,invdate,comp));
		}
		
    	if(parentmodel.compareToIgnoreCase("Return Aproval")==0)
		{
    		
    		List<String> deliverylist = new ArrayList<>();
    		deliverylist.addAll(dChallanRepository.getdeliverchallanreturnapproval(invtype,party,invdate));
			
    		for(int i=0;i<deliverylist.size();i++) 
    		{
    			modelList.add(dChallanRepository.getDeliveryChallanDtlsnew(deliverylist.get(i)));
    		}
    		
    		
		}
		
		return modelList;
	
    }
    
    public List<Map<String, Object>> getDelvChallansnewjobwork(String invtype,String party,String invdate,String comp,String parentmodel) 
    {
    	List<Map<String, Object>> modelList =new ArrayList<>();
		
    	if(parentmodel.compareToIgnoreCase("Sales Invoice")==0)
		{
			 modelList.addAll(dChallanRepository.deliveryChallanListnewjobwork(invtype,party,invdate,comp));
		}
		
    	if(parentmodel.compareToIgnoreCase("Return Aproval")==0)
		{
    		
    		List<String> deliverylist = new ArrayList<>();
    		deliverylist.addAll(dChallanRepository.getdeliverchallanreturnapproval(invtype,party,invdate));
			
    		for(int i=0;i<deliverylist.size();i++) 
    		{
    			modelList.add(dChallanRepository.getDeliveryChallanDtlsnew(deliverylist.get(i)));
    		}
    		
    		
		}
		
		return modelList;
	
    }
    
	
public List<Delivery_challanDTO> getMultipleDelvChallans(String invtype,String party,String invdate,String comp,String parentmodel) {
		
		//System.out.println( " tuhin here "+party + " / " + invdate + " / " + comp + " / " + invtype + " / " +parentmodel );
		List<Delivery_challan> modelList =new ArrayList<Delivery_challan>();
		if(parentmodel.compareToIgnoreCase("Sales Invoice")==0)
		{
			 modelList=dChallanRepository.deliveryChallanList(invtype,party,invdate,comp);
		}
		
		
		Type listType = new TypeToken<List<Delivery_challanDTO>>() {}.getType();
		
		List<Delivery_challanDTO> chllnList = new ModelMapper().map(modelList,listType);
		
		return chllnList;
	}
	
	public List<Delivery_challanDTO> getMultipleDelvChallansUpdate(Long id) 
	{
		Optional<Sales_Invoice> op = sales_InvoiceRepository.findById(id);

		 String []splitdeliverychallan=op.get().getReference_id().split(",");
		 
		 List<Delivery_challan> modelList =new ArrayList<Delivery_challan>();
		 for(int i=0;i<splitdeliverychallan.length;i++) 
		 {
			 modelList.add(dChallanRepository.deliveryChallanListUpdate(splitdeliverychallan[i]));
		 }
		Type listType = new TypeToken<List<Delivery_challanDTO>>() {}.getType();
		List<Delivery_challanDTO> chllnList = new ModelMapper().map(modelList,listType);
		return chllnList;
	}

	public List<Delivery_challanDTO> getDelvChallansApp(String party,String invdate,String comp,String parentmodel){
		
		//System.out.println( " tuhin here "+party + " / " + invdate + " / " + comp + " / " +parentmodel);
		List<Delivery_challan> modelList =new ArrayList<Delivery_challan>();
		if(parentmodel.compareToIgnoreCase("Sales Invoice")==0)
		{
			 modelList=dChallanRepository.getDelvChallansApp(party,invdate,comp);
		}
		if(parentmodel.compareToIgnoreCase("Return Aproval")==0)
		{
			 modelList=dChallanRepository.getDelvChallansAppreturn(party,invdate,comp);
		}
		
		Type listType = new TypeToken<List<Delivery_challanDTO>>() {}.getType();
		
		List<Delivery_challanDTO> chllnList = new ModelMapper().map(modelList,listType);
		
		return chllnList;
	}
	
	public List<Map<String,Object>> getDelvChallansReturnApp(String party,String invdate,String comp,String parentmodel){
		//System.out.println("delv chln:::"+parentmodel+"//"+comp+"//"+party+""+invdate);
		List<Map<String,Object>> modelList=new ArrayList<>();
		if(parentmodel.compareToIgnoreCase("Sales Invoice")==0)
		{
			List<String> delvID=new ArrayList<>();
			delvID.addAll(dChallanRepository.getDelvChallansReturnApp(party,invdate));
			for(int p=0;p<delvID.size();p++)
			{
				modelList.add(dChallanRepository.getDelvChallanReturnList(delvID.get(p)));
			}
		}
		
		if(parentmodel.compareToIgnoreCase("Return Aproval")==0)
		{
			 modelList=dChallanRepository.getDelvChallansAppreturnnew(party,invdate,comp);
		}
		
		return modelList;
	}

	
public List<Delivery_challanDTO> getMultipleDelvChallansApp(String party,String invdate,String comp,String parentmodel){
		
		//System.out.println( " tuhin here "+party + " / " + invdate + " / " + comp + " / " +parentmodel);
		List<Delivery_challan> modelList =new ArrayList<Delivery_challan>();
		if(parentmodel.compareToIgnoreCase("Sales Invoice")==0)
		{
			 modelList=dChallanRepository.getDelvChallansApp(party,invdate,comp);
		}
		
		Type listType = new TypeToken<List<Delivery_challanDTO>>() {}.getType();
		
		List<Delivery_challanDTO> chllnList = new ModelMapper().map(modelList,listType);
		
		return chllnList;
	}

	public List<Delivery_challan_Item_DtlsDTO> getDlvCItmDtls(String delivery_cid)
	{
		List<Delivery_challan_Item_Dtls> modelList=delivery_challan_Item_DtlsRepository.getDlvCItmDtls(delivery_cid);
		
		Type listType=new TypeToken<List<Delivery_challan_Item_DtlsDTO>>() {}.getType();
		
		List<Delivery_challan_Item_DtlsDTO> dlvCItmDtls=new ModelMapper().map(modelList,listType);
		
		return dlvCItmDtls;
	}
	
	public List<Map<String,Object>>getDlvChallanItemjobworkRetriveList(String delivery_cid)
	{
		return delivery_challan_Item_Dtls_for_jobworkRepository.getDlvChallanItemjobworkRetriveList(delivery_cid);
	}
	
	/*public List<Delivery_challan_Item_DtlsDTO> getDlvCItmDtlshsn(String delivery_cid)
	{
		List<Delivery_challan_Item_Dtls> modelList=delivery_challan_Item_DtlsRepository.getDlvCItmDtls(delivery_cid);
		
		modelList.forEach(element->{
			Item_master hsn_code = new Item_master();
			hsn_code =item_masterRepository.getItemNameCodeStock(element.getItem_code());
			element.setUpdated_by(hsn_code.getHsn_code());
		});
		
		Type listType=new TypeToken<List<Delivery_challan_Item_DtlsDTO>>() {}.getType();
		
		List<Delivery_challan_Item_DtlsDTO> dlvCItmDtls=new ModelMapper().map(modelList,listType);
		
		return dlvCItmDtls;
	}*/
	
	public List<Delivery_challan_Party_DtlsDTO> getDlvCPtyDtls(String delivery_cid)
	{
		List<Delivery_challan_Party_Dtls> modelList=delivery_challan_Party_DtlsRepository.getDlvCPtyDtls(delivery_cid);
		
		Type listType=new TypeToken<List<Delivery_challan_Party_DtlsDTO>>() {}.getType();
		
		List<Delivery_challan_Party_DtlsDTO> dlvCPtyDtls=new ModelMapper().map(modelList,listType);
		
		return dlvCPtyDtls;
	}
	
	
	public List<Delivery_challan_Broker_DtlsDTO> getDlvChallanBrokerDtls(String delivery_cid)
	{
		List<Delivery_challan_Broker_Dtls> modelList=delivery_challan_Broker_DtlsRepository.getDlvChallanBrokerDtls(delivery_cid);
		
		Type listType=new TypeToken<List<Delivery_challan_Broker_DtlsDTO>>() {}.getType();
		
		List<Delivery_challan_Broker_DtlsDTO> dlvBrokerDtls=new ModelMapper().map(modelList,listType);
		
		return dlvBrokerDtls;
	}
	
	public List<Map<String,Object>> getDelivery_challan_Chgs_dyn(String delivery_cid)
	{
		
		 String loadingid=dChallanRepository.getDeliveryChallanDtls(delivery_cid).getReferance_id();
		 
		 Wm_loading_advice load =wm_loading_adviceRepository.loadingAdviceVehicleList(loadingid);	 
		 
		 String chargecode=delivery_challan_Trans_InfoRepository.getDlvChallanTransInfo(delivery_cid).getCharge_code();
		 
		 
		 return sales_Order_Trans_Chgs_dynRepository.gettransdetails(load.getReferance_id(),chargecode);
	}
	
	public List<Map<String,Object>> getDelivery_challan_Chgs_dynDtls(String delivery_cid)
	{
		 return delivery_challan_Chgs_dynRepository.getDelivery_challan_Chgs_dyn(delivery_cid);
	}
	
	public List<Map<String,Object>> getDelivery_challan_Chgs_dynpopup(String loadingid)
	{
		
		// String loadingid=dChallanRepository.getDeliveryChallanDtls(delivery_cid).getReferance_id();
		 
		 Wm_loading_advice load =wm_loading_adviceRepository.loadingAdviceVehicleList(loadingid);	 
		 
		 //String chargecode=delivery_challan_Trans_InfoRepository.getDlvChallanTransInfo(delivery_cid).getCharge_code();
		 
		 String chargecode=wm_loading_advice_trans_infoRepository.getLoadingAdvTransinfo(loadingid).getCharge_code();
		 
		 return sales_Order_Trans_Chgs_dynRepository.gettransdetails(load.getReferance_id(),chargecode);
	}
	
	  public Delivery_challan_Shipment_DtlsDTO getDlvChallanShipmentDtls(String delivery_cid){

		  Delivery_challan_Shipment_Dtls modelList=delivery_challan_Shipment_DtlsRepository.rdlvChallanShipmentDtls(delivery_cid);

		  Type listType = new TypeToken<Delivery_challan_Shipment_DtlsDTO>() {}.getType();

		  Delivery_challan_Shipment_DtlsDTO Delivary= new ModelMapper().map(modelList,listType);
		
		  return Delivary;
	  }
	  
	  public Map<String,Object> getDlvChallanShipmentDtlsFast(String delivery_cid)
		{
			 return delivery_challan_Shipment_DtlsRepository.getDlvChallanShipmentDtlsFast(delivery_cid);
		}
	  
	  public Delivery_challan getdeliverychallernpartyterm(String salesretunnote_id) 
	  {
		  
		  Return_approval_note return_approval=return_approval_noteRepository.getRtnAppNoteLowRateUpdate(salesretunnote_id);
		  Delivery_challan partydetails= new Delivery_challan();
		 
		  if(return_approval.getReturnbasedon().compareToIgnoreCase("Delivery Challan")==0) 
		  {
			  partydetails.setPrice_term(dChallanRepository.getDeliveryChallanDtls(return_approval.getReferance_id()).getPrice_term());
		  }
		  else 
		  {
			  partydetails.setPrice_term(sales_OrderRepository.getSalesOrderDetails(return_approval.getReferance_id()).getPrice_term());
		  }
		  
		  return partydetails;
		  //returnbasedon
	  }

	  public Delivery_challan_weight_dtlDTO getDlvChallanWeightDtls(String delivery_cid){

		  Delivery_challan_weight_dtl modelList=delivery_challan_weight_dtlRepository.getDlvChallanWeightDtls(delivery_cid);

		  Type listType = new TypeToken<Delivery_challan_weight_dtlDTO>() {}.getType();

		  Delivery_challan_weight_dtlDTO Delivary= new ModelMapper().map(modelList,listType);
		
		  return Delivary;
	  }
	  
	  public Delivery_challan_Trans_InfoDTO getDlvChallanTransInfo(String delivery_cid){

		Delivery_challan_Trans_Info modelList=delivery_challan_Trans_InfoRepository.getDlvChallanTransInfo(delivery_cid);

		Type listType = new TypeToken<Delivery_challan_Trans_InfoDTO>() {}.getType();

		Delivery_challan_Trans_InfoDTO Delivary= new ModelMapper().map(modelList,listType);
		
		return Delivary;
	  }
	  
	  public List<Invoice_Delivery_Trans_InfoDTO> getDlvChlnTransInfo(String delivery_cid){

		  String[] arrOfStr=delivery_cid.split(",");
		  List<Invoice_Delivery_Trans_InfoDTO> delvTransList=new ArrayList<Invoice_Delivery_Trans_InfoDTO>();
		  
		  for(int i=0;i<arrOfStr.length;i++)
		  {
			  Delivery_challan_Trans_Info transInfo=delivery_challan_Trans_InfoRepository.getDlvChallanTransInfo(arrOfStr[i]);
			  Delivery_challan_weight_dtl wtInfo=delivery_challan_weight_dtlRepository.getDlvChallanWeightDtls(arrOfStr[i]);
			  Invoice_Delivery_Trans_InfoDTO def=new Invoice_Delivery_Trans_InfoDTO(transInfo.getTrans_code(),vehicleMasterRepository.getVehicleDetails(transInfo.getVehle_no()).getVehtype_code(),transInfo.getVehle_no(),wtInfo.getOwn_permit_no());
			  delvTransList.add(def);
		  }
		  
		  Type listType = new TypeToken<List<Invoice_Delivery_Trans_InfoDTO>>() {}.getType();

		  List<Invoice_Delivery_Trans_InfoDTO> invTrans= new ModelMapper().map(delvTransList,listType);
		
		  return invTrans;
	  }
	  
	  public List<Invoice_Delivery_Trans_InfoDTO> getMultipleDlvChlnTransInfo(String delivery_cid){

		  String[] arrOfStr=delivery_cid.split(",");
		  List<Invoice_Delivery_Trans_InfoDTO> delvTransportList=new ArrayList<Invoice_Delivery_Trans_InfoDTO>();
		  
		  for(int i=0;i<arrOfStr.length;i++)
		  {
			  Delivery_challan_Trans_Info transInfo=delivery_challan_Trans_InfoRepository.getDlvChallanTransInfo(arrOfStr[i]);
			  Delivery_challan_weight_dtl wtInfo=delivery_challan_weight_dtlRepository.getDlvChallanWeightDtls(arrOfStr[i]);
			  Invoice_Delivery_Trans_InfoDTO def=new Invoice_Delivery_Trans_InfoDTO(transInfo.getTrans_code(),vehicleMasterRepository.getVehicleDetails(transInfo.getVehle_no()).getVehtype_code(),transInfo.getVehle_no(),wtInfo.getOwn_permit_no());
			  delvTransportList.add(def);
		  }
		  
		  Type listType = new TypeToken<List<Invoice_Delivery_Trans_InfoDTO>>() {}.getType();

		  List<Invoice_Delivery_Trans_InfoDTO> invTrans= new ModelMapper().map(delvTransportList,listType);
		
		  return invTrans;
	  }
	  
	  public List<Delivery_challan_DocsDTO> getDlvCDoc(String delivery_cid)
		{
			List<Delivery_challan_Docs> modelList=delivery_challan_DocsRepository.getDlvCDoc(delivery_cid);
			
			Type listType=new TypeToken<List<Delivery_challan_DocsDTO>>() {}.getType();
			
			List<Delivery_challan_DocsDTO> dlvCItmDtls=new ModelMapper().map(modelList,listType);
			
			return dlvCItmDtls;
		}
	  
	 public StatusDTO checkDeliveryChallanUsage(String delvid)
	 {
		String result = dChallanRepository.checkDeliveryChallanUsage(delvid);
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO= new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		return statusDTO;
	 }
	 
	 @Transactional
	 public Delivery_challan deleteDeliveryChallan(Delivery_challan delivery_challan,Long id) 
	 	{
			Optional<Delivery_challan> op = dChallanRepository.findById(id);
			
			LocalDateTime ldt = LocalDateTime.now();
			Delivery_challan delvchallan=op.get();
			
			delvchallan.setModified_type("DELETED");
			delvchallan.setInserted_by(op.get().getInserted_by());
			delvchallan.setInserted_on(op.get().getInserted_on());
			delvchallan.setUpdated_by(op.get().getUpdated_by());
			delvchallan.setUpdated_on(op.get().getUpdated_on());
			delvchallan.setDeleted_by(userRepository.getUserDetails(delvchallan.getUsername()).getName());
			delvchallan.setDeleted_on(ldt);
			
			if(op.get().isJobwork()==true)
			{
				dChallanRepository.delvJobItemupdate(op.get().getDelivery_cid(),"DELETED");
			}
			else
			{
				delivery_challan_Item_DtlsRepository.delivery_challan_Item_DtlsUpdate(op.get().getDelivery_cid());
			}
				
			
			delivery_challan_Broker_DtlsRepository.delivery_challan_Broker_DtlsUpdate(op.get().getDelivery_cid());
			
			delivery_challan_Party_DtlsRepository.delivery_challan_Party_DtlsUpdate(op.get().getDelivery_cid());
			
			delivery_challan_Shipment_DtlsRepository.delivery_challan_Shipment_DtlsUpdate(op.get().getDelivery_cid());
			
			delivery_challan_weight_dtlRepository.delivery_challan_weight_dtlUpdate(op.get().getDelivery_cid());
			
			delivery_challan_Trans_InfoRepository.delivery_challan_Trans_InfoUpdate(op.get().getDelivery_cid());
			
			delivery_challan_DocsRepository.delivery_challan_DocsUpdate(op.get().getDelivery_cid());
			
			delivery_challan_Chgs_dynRepository.delivery_challan_Chgs_dynupdate(op.get().getDelivery_cid(),"DELETED");
			
			wm_loading_adviceRepository.revertgrnstatus(op.get().getReferance_id());
			
			
			
			return dChallanRepository.save(delvchallan);	
	 	}
	 
	 public List<Delivery_challan>getDeliverychallanlist (String salesorderid)
	 {
		 //System.out.println(" salesorderid :: " + salesorderid);
		 List<Wm_loading_advice> loadinglist= wm_loading_adviceRepository.loadingAdviceDetails(salesorderid);
		 
		 
		 
		 List<Delivery_challan> challanlist=new ArrayList<Delivery_challan>();
		 
		 for(int i=0;i<loadinglist.size();i++) 
		 {
			 //System.out.println("tuhin "+loadinglist.get(i).getReferance_id());
			 challanlist.add(dChallanRepository.getloadingdeliverychallan(loadinglist.get(i).getAdvice_id()));
		 }
		
		 
		 return challanlist;
	 }
	 
	 public List<Map<String,Object>>getDeliverychallanReturnList(String salesorderid)
	 {
		 List<String> loadinglist=new ArrayList<>();
		 loadinglist.addAll(wm_loading_adviceRepository.loadingAdviceReturnDetails(salesorderid));
		 
		List<Map<String,Object>> challanlist=new ArrayList<>();
		 
		 for(int p=0;p<loadinglist.size();p++)
			{
			 challanlist.add(dChallanRepository.getloadingdeliverychallanReturn(loadinglist.get(p)));
			}
		 
		 return challanlist;
	 }
	 
	 public List<Delivery_challan_Item_Dtls>getdeliverchallanitemlist (String deliverycharges)
	 {
		 //System.out.println("deliverycharges :: "+deliverycharges);
		 String []splitdeliverychallan=deliverycharges.split(",");
		 
		 List<Delivery_challan_Item_Dtls> challanlist=new ArrayList<Delivery_challan_Item_Dtls>();
		 for(int i=0;i<splitdeliverychallan.length;i++) 
		 {
			 challanlist.addAll(dChallanRepository.getdeliverychallanitemlist(splitdeliverychallan[i]));
		 }
		 
		 
		 return challanlist;
	 }
	 
	public List<Map<String,Object>> getdeliverchallanjobitemlist (String deliverycharges)
	 {
		 String []splitdeliverychallan=deliverycharges.split(",");
		 System.out.println(deliverycharges);
		 ArrayList<String> challanmultiple=new ArrayList<String>();
		 for(int i=0;i<splitdeliverychallan.length;i++)
		 {
			 System.out.println(splitdeliverychallan[i]);
			challanmultiple.add(splitdeliverychallan[i]);
		 }
		 List<Map<String,Object>> challanlist=new ArrayList <>();
		 challanlist.addAll(dChallanRepository.getdeliverchallanjobitemlist(challanmultiple));
		 return challanlist;
		
	 }
	 
	 public List<Delivery_challan_Item_Dtls>getdeliverchallanitemlistUpdate (Long id)
	 {
		 Optional<Sales_Invoice> op = sales_InvoiceRepository.findById(id);
		 
		 String []splitdeliverychallan=op.get().getReference_id().split(",");
		 
		 List<Delivery_challan_Item_Dtls> challanlist=new ArrayList<Delivery_challan_Item_Dtls>();
		 for(int i=0;i<splitdeliverychallan.length;i++) 
		 {
			 challanlist.addAll(dChallanRepository.getdeliverychallanitemlist(splitdeliverychallan[i]));
		 }
		 return challanlist;
	 }
	 
	 public List<Delivery_challan> getdiliverychallanreturnapprovepopup(Long id)
	 {
		 Optional<Return_approval_note> op=return_approval_noteRepository.findById(id);
		 
		 List<Delivery_challan> dchallanSingle = new ArrayList<Delivery_challan>();
		 dchallanSingle.add(dChallanRepository.getDeliveryChallanDtls(op.get().getReferance_id()));
		
		 
		 return dchallanSingle; 
		 
	 }
	 
	 public List<Delivery_challanDTO> getDeliveryChallanDataList(String currDate,String finyear)
		{
			//List<Delivery_challan> modelList=dChallanRepository.getDeliveryChallanDataList(currDate);
		 	List<Delivery_challan> modelList =new ArrayList<Delivery_challan>();
			String tablename="delivery_challan",party_name="party",order_no="challan_no",order_date="challan_date";
			String orderno="",party1="";
			modelList.addAll(dChallanRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,currDate,currDate,party1,finyear));
			
			
			Type listType = new TypeToken<List<Delivery_challanDTO>>() {}.getType();
			List<Delivery_challanDTO> advList = new ModelMapper().map(modelList,listType);
			
			advList.forEach((delv) ->{
					if(Utility.isNullOrEmpty(delv.getParty())) {
						delv.setParty(cust_bussiness_partnerRepository.getCustomer(delv.getParty()).getCp_name());
					}else {delv.setParty("None");}
					
					if(Utility.isNullOrEmpty(delv.getInv_type())) {
						delv.setInv_type(invoice_typeRepository.getSalesInvTypesDtls(delv.getInv_type()).getInvtype_name());
					}else {delv.setInv_type("None");}
				});
			
			return advList;
		}
	 
	 public List<Map<String,Object>> getDeliveryChallanFastList(String currDate,String finyear)
		{
		 List<Map<String,Object>> deliverychallan=new ArrayList<Map<String,Object>>();
		 
		 deliverychallan.addAll(dChallanRepository.getDeliveryChallanFastList(currDate,finyear));
		 
		 return deliverychallan;
		}
	 
	 public List<ChallanpertransportreportDTO> getChallanPerTransportReport(String fromdate,String todate,String transborneby)
		{
		 if(Utility.isNullOrEmpty(todate))
		 {
			 
		 }
		 else
		 {
			 todate=fromdate;
		 }
		 
			List<Object[]> challandata=new ArrayList<Object[]>();
		
			if(Utility.isNullOrEmpty(transborneby))
			{
				if(transborneby.compareToIgnoreCase("1")==0)
				{
					transborneby="FOB";
				}
				else
				{
					transborneby="FOR";
				}
				
				challandata.addAll(dChallanRepository.getChallanPerTransportModeReport(fromdate,todate,transborneby));
			}
			else
			{
				challandata.addAll(dChallanRepository.getChallanPerTransportReport(fromdate,todate));
			}
			
			List<ChallanpertransportreportDTO> list = new ArrayList<ChallanpertransportreportDTO>();     
			
		    for(final Object o : challandata)
		    {
		        Object[] obj = (Object[]) o;
		        list.add(new ChallanpertransportreportDTO(obj[0].toString(),
		        		obj[1].toString(), 
		        		obj[2].toString(), 
		        		obj[3].toString(),
		        		obj[4].toString(),
		        		obj[5].toString(),
		        		obj[6].toString(),
		        		obj[7].toString(), 
		        		obj[8].toString(), 
		        		obj[9].toString(),
		        		obj[10].toString(),
		        		obj[11].toString(), 
		        		obj[12].toString(),
		        		obj[13].toString(), 
		        		obj[14].toString()));
		    }
		    
		    return list;
		}
	 public List<Map<String, Object>> getSalesTransportReport(String business_unit,String fromdate,String todate,String inv_type,String trans_type,String transporter_code)
	 {
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 
		
			if(inv_type.compareToIgnoreCase("INV00003")==0)			
			{
				if(transporter_code.compareToIgnoreCase("No")==0)				
				{
					modelList.addAll(dChallanRepository.getChallanTransportModeReportJW(business_unit,fromdate,todate,inv_type,trans_type));
				}
				else 
				{
					modelList.addAll(dChallanRepository.getChallanTransportModeReportJWwithTrans(business_unit,fromdate,todate,inv_type,trans_type,transporter_code));
				}
			}
			else
			{
				if(transporter_code.compareToIgnoreCase("No")==0)
				{
					modelList.addAll(dChallanRepository.getChallanTransportModeReport(business_unit,fromdate,todate,inv_type,trans_type));
				}
				else 
				{
					modelList.addAll(dChallanRepository.getChallanTransportModeReportwithTrans(business_unit,fromdate,todate,inv_type,trans_type,transporter_code));
				}
			}
		 
			
		 return modelList;
	 }
	 
	 public StatusDTO checkcashchallan(String orderdate,double totalamount,long id,String party,String referance_id)
	 {
		 StatusDTO resp = new StatusDTO();
		 
		 if(id>0) 
		 {
			 Optional<Delivery_challan> op = dChallanRepository.findById(id);
			 double totalamountincash=0.00;
			 if(dChallanRepository.countIdbydateUPDATE(orderdate,id,party)>0) 
			 {
                  List<Delivery_challan>challan = dChallanRepository.getDeliveryChallanDataListupdate(orderdate,id,party);
				 
				 
				 for(int i=0;i<challan.size();i++) 
				 {
					 System.out.println("ref id  :: "+challan.get(i).getReferance_id());
					 totalamountincash += dChallanRepository.gettotalamount(challan.get(i).getReferance_id());
				 }
			 }
			 System.out.println("totalamountincash before 1 :: " + totalamountincash + " / " +  totalamount + " / " + op.get().getGrand_total());
			 
			 if(dChallanRepository.countIdbycheckpaymentmode(referance_id)>0) //countIdbycheckpaymentmode
			 {
				 totalamountincash=totalamountincash+totalamount;
			 }
			 
			// totalamountincash=totalamountincash+ totalamount;
			 System.out.println("totalamountincash after 1 :: " + totalamountincash);
			 
			 if(totalamountincash>=200000) 
			 {
				 resp.setStatus("No");
			 }
			 else  
			 {
				 resp.setStatus("Yes");
			 }
			 
			 
		 }
		 else 
		 {
			 double totalamountincash=0.00;
			 if(dChallanRepository.countIdbydate(orderdate,party)>0) 
			 {
				 
				 List<Delivery_challan>challan = dChallanRepository.getDeliveryChallanDataListnew(orderdate,party);
				 
				 
				 for(int i=0;i<challan.size();i++) 
				 {
					 
					 totalamountincash += dChallanRepository.gettotalamount(challan.get(i).getReferance_id());
				 }
				
				
			 }
			
			 System.out.println("totalamountincash after 2 :: " + totalamountincash);
			
			 //referance_id
			 if(dChallanRepository.countIdbycheckpaymentmode(referance_id)>0) //countIdbycheckpaymentmode
			 {
				 totalamountincash=totalamountincash+totalamount;
			 }
			 
			 System.out.println("totalamountincash before 2 :: " + totalamountincash);
			 
			 
			 if(totalamountincash>=200000) 
			 {
				 resp.setStatus("No");
			 }
			 else 
			 {
				 resp.setStatus("Yes");
			 }
			
		 }
		
		 
		 return resp;
	 }
	 
	 public List<Map<String, Object>> getchallanReport(String fromdate,String todate)
	 {
		 List<Map<String, Object>> challan=dChallanRepository.getchallanReport(fromdate,todate);
				 
	     return challan;
	 }
	 
	 public List<Map<String,Object>> getDeliveryChallanReportThrouhgLA(String loadingid)
	 {
		 return dChallanRepository.getDeliveryChallanReportThrouhgLA(loadingid);
	 }
	 
	 public List<Map<String,Object>> deliverychallanjobworkRetriveList(String delv_id)
	 {
		 return dChallanRepository.deliverychallanjobworkRetriveList(delv_id);
	 }
	 
	 public Map<String, Object> getSalesOrdIfMultiTransInfo(String deliveryid)   
		{
			String multiid[]=deliveryid.split(",");
			if(deliveryid.contains(","))
			{
				Map<String,Object> modelList=delivery_challan_Trans_InfoRepository.getSalesOrdIfMultiTransInfo(multiid[0]);
				return modelList;
			}
			else 
			{
				Map<String,Object> modelList1=delivery_challan_Trans_InfoRepository.getSalesOrdIfMultiTransInfo(deliveryid);
				return modelList1;
			}
			
			
		}
	 
	 public Map<String, Object> getDlvChallanWeightDtlsMulti(String deliveryid)   
		{
			String multiid[]=deliveryid.split(",");
			if(deliveryid.contains(","))
			{
				Map<String,Object> modelList=delivery_challan_weight_dtlRepository.getDlvChallanWeightDtlsMulti(multiid[0]);
				return modelList;
			}
			else 
			{
				Map<String,Object> modelList1=delivery_challan_weight_dtlRepository.getDlvChallanWeightDtlsMulti(deliveryid);
				return modelList1;
			}
			
			
		}
	 
	 @Transactional
	 public StatusDTO updateTransporterDetailsthruPopup(String delv_id,double distance)
	 {
		 StatusDTO status=new StatusDTO();
		 //System.out.println("Check : : "+delivery_challan_Chgs_dynRepository.updateTransporterDetailsthruPopup(delv_id,distance));
		 
		 status.setStatus(Integer.toString(delivery_challan_Chgs_dynRepository.updateTransporterDetailsthruPopup(delv_id,distance)));
		
		 return status;
	 }
	 
	 public Map<String, Object> getLoadingAdviceTransDtls(String deliveryid)   
		{
			String refid=dChallanRepository.getDeliveryChallanDtls(deliveryid).getReferance_id();
			return wm_loading_advice_trans_infoRepository.getloadingTransDetails(refid);
		}
	 
	 public List<Map<String, Object>> getSalesFreightReport(String fromdate,String todate,String invoicetype)
		{
			return dChallanRepository.getSalesFreightReport(fromdate,todate,invoicetype);
		}
	 
	 public List<Map<String, Object>> getRestDlvChallanItemDtls(String delvid)
		{
			return dChallanRepository.getRestwt(delvid);
		}
	 
	 public List<Map<String, Object>> searchpendingDelvChallan(String fromdate,String todate)
		{
			return dChallanRepository.searchpendingDelvChallan(fromdate,todate);
		}
	 
}
