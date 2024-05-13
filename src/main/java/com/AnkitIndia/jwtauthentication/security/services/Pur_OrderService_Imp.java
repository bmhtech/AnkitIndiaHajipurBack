package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.FileUpload.FileUploadUtil;
import com.AnkitIndia.Utility.FilePurchaseOrderUtil;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_BPDetails;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Doc;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Item_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Termination;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Termination_dyn;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Terms_Con;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Trans_Chgs_dyn;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Trans_Info;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_app_chgs;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_broker;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Pur_order_store_chgs;
import com.AnkitIndia.jwtauthentication.model.Pur_order_terms_conditions;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_BPDetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_DocRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_Item_DetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_TerminationRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_Termination_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_Terms_ConRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_Trans_Chgs_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_Trans_InfoRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_app_chgsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_brokerRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receiptRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_order_store_chgsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_order_terms_conditionsRepository;
import com.AnkitIndia.jwtauthentication.repository.Store_inv_charge_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_item_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_wgmntRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_BPDetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_DocDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Item_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_TerminationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Terms_ConDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_app_chgsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_OrderDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_Order_Termination_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_Order_brokerDTO;

@Service
@Repository
public class Pur_OrderService_Imp implements Pur_OrderService {
	
	@Autowired
	Pur_OrderRepository pur_OrderRepository;
	
	@Autowired
	Item_type_masterRepository iTypeRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	Pur_Order_Item_DetailsRepository pur_Order_Item_DetailsRepository;
	
	@Autowired
	Item_type_masterRepository item_type_masterRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Wm_unload_advice_item_dtlsRepository wm_unload_advice_item_dtlsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	@Autowired
	Wm_unload_wgmntRepository wm_unload_wgmntRepository;
	
	@Autowired
	Pur_good_receiptRepository pur_good_receiptRepository;
	
	@Autowired
	Pur_Order_Trans_Chgs_dynRepository pur_Order_Trans_Chgs_dynRepository;
	
	@Autowired
	Pur_order_terms_conditionsRepository pur_order_terms_conditionsRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;
	
	@Autowired
	Pur_order_store_chgsRepository pur_order_store_chgsRepository;
	
	@Autowired
	Store_inv_charge_masterRepository store_inv_charge_masterRepository;
	
	public SequenceIdDTO getPOSequenceId(String prefix,String orderdate,boolean orderfor,String potype,String posubtype)
	{
		int slno=0;String type="";
		if(orderfor == true) 
		{
			type="MAT";
		}
		else 
		{
			type="SER";
		}
		String sno=pur_OrderRepository.countPOrder(orderdate,orderfor,potype,posubtype);
		
		if(sno != null )
		{
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		String po_type_new="";
		if(potype.compareToIgnoreCase("ITMT00001") == 0)
		{
			po_type_new="RAW";
		}
		if(potype.compareToIgnoreCase("ITMT00002") == 0)
		{
			po_type_new="PAC";
		}
		if(potype.compareToIgnoreCase("ITMT00004") == 0)
		{
			po_type_new="STP";
		}
		if(potype.compareToIgnoreCase("ITMT00005") == 0)
		{
			po_type_new="ASP";
		}
		if(potype.compareToIgnoreCase("ITMT00007") == 0)
		{
			po_type_new="CWI";
		}
		if(potype.compareToIgnoreCase("ITMT00008") == 0)
		{
			po_type_new="RWE";
		}
		if(potype.compareToIgnoreCase("ITMT00009") == 0)
		{
			po_type_new="JOB";
		}
		if(potype.compareToIgnoreCase("ITMT00010") == 0)
		{
			po_type_new="TRS";
		}
	
		//prefix=prefix+"-"+type+"-"+po_type_new.substring(0, 3)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		prefix=prefix+"-"+type+"-"+po_type_new+"-"+posubtype.substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	
	
	@Transactional
	//public Pur_Order save(Pur_Order pOrder)
	public Pur_Order save(Pur_Order pOrder,MultipartFile files[]) throws IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		
		if(pur_OrderRepository.countId() != null ) {
			slno=Long.parseLong(pur_OrderRepository.countId());
		}
		String prefix="PO";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		pOrder.setPur_orderid(gen_sno);
		//pOrder.setTagadvice_status("No");
		
		
		
		/*Start checking transaction no for unique */
		System.err.println("First:>>"+pOrder.getPur_order_no());
		long nslno=0;String type="",tprefix="PO";
		String tsno=pur_OrderRepository.countPOrder(pOrder.getOrd_date(),pOrder.isSer_item_type(),pOrder.getSer_item_subtype(),pOrder.getPur_ord_type());
		if(pOrder.isSer_item_type() == true) {type="MAT";}else {type="SER";}
		
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = pOrder.getOrd_date().split("-");
		if(pOrder.getSer_item_subtype().compareToIgnoreCase("ITMT00001") == 0) 
		{
			tprefix=tprefix+"-"+type+"-"+"RAW-"+pOrder.getPur_ord_type().substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		}
		else if(pOrder.getSer_item_subtype().compareToIgnoreCase("ITMT00010") == 0) 
		{
			tprefix=tprefix+"-"+type+"-"+"TRS-"+pOrder.getPur_ord_type().substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		}
		else if(pOrder.getSer_item_subtype().compareToIgnoreCase("ITMT00002") == 0) 
		{
			tprefix=tprefix+"-"+type+"-"+"PAC-"+pOrder.getPur_ord_type().substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		}
		
		if(pOrder.getSer_item_subtype().compareToIgnoreCase("ITMT00004") == 0) 
		{
			tprefix=tprefix+"-"+type+"-"+"STO-"+pOrder.getPur_ord_type().substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		}
		
		if(pOrder.getSer_item_subtype().compareToIgnoreCase("ITMT00005") == 0) 
		{
			tprefix=tprefix+"-"+type+"-"+"ASP-"+pOrder.getPur_ord_type().substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		}
		if(pOrder.getSer_item_subtype().compareToIgnoreCase("ITMT00007") == 0)
		{
			tprefix=tprefix+"-"+type+"-"+"CWI-"+pOrder.getPur_ord_type().substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		}
		
		if(pOrder.getSer_item_subtype().compareToIgnoreCase("ITMT00008") == 0)
		{
			tprefix=tprefix+"-"+type+"-"+"RWE-"+pOrder.getPur_ord_type().substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		}
		if(pOrder.getSer_item_subtype().compareToIgnoreCase("ITMT00009") == 0)
		{
			tprefix=tprefix+"-"+type+"-"+"JOB-"+pOrder.getPur_ord_type().substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		}
		
		//tprefix=tprefix+"-"+type+"-"+"PAC-"+date[2]+date[1]+date[0].substring(2,4)+"-";//changes 25-04-2022
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		pOrder.setPur_order_no(gen_tslno);
		pOrder.setPo_status("Open");  /* new 14-04-2022 */
		pOrder.setTotal_qty_copy(pOrder.getTotal_qty());
		System.err.println("Last:>>>"+pOrder.getPur_order_no());
		/*End checking transaction no for unique */
		
		pOrder.setModified_type("INSERTED");
		pOrder.setInserted_by(userRepository.getUserDetails(pOrder.getUsername()).getName());
		pOrder.setInserted_on(ldt);
		pOrder.setUpdated_by("NA");
		pOrder.setUpdated_on(ldt);
		pOrder.setDeleted_by("NA");
		pOrder.setDeleted_on(ldt);
		pOrder.setOrddate(pOrder.getOrd_date());
		pOrder.setPurorderno(pOrder.getPur_order_no());
		pOrder.setPur_return_status("No");
		pOrder.setPurreturnid("NA");
		
		
		if(Utility.isNullOrEmpty(pOrder.getStore_charges())) 
		{
			if(pOrder.getStore_charges().compareTo("") != 0 && pOrder.getStore_charges() != null) {
				pOrder.setStore_charges_name(store_inv_charge_masterRepository.getStoreChargeMaster(pOrder.getStore_charges()).getStore_charge_desc());
			}else {pOrder.setStore_charges_name("None");}
		}
		else 
		{
			pOrder.setStore_charges_name("None");
		}
		
		if(pOrder.getSer_item_subtype().compareTo("0") != 0 && pOrder.getSer_item_subtype().compareTo("") != 0 && pOrder.getSer_item_subtype() != null) {
			pOrder.setSer_item_subtype_name(item_type_masterRepository.getItemType(pOrder.getSer_item_subtype()).getItem_name());
		}else {pOrder.setSer_item_subtype_name("None");}
		
		if(pOrder.getBusinessunit().compareTo("0") !=0 && pOrder.getBusinessunit().compareTo("") !=0 && pOrder.getBusinessunit() !=null) {
			pOrder.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(pOrder.getBusinessunit()).getBusinessunit_name());
		}else {pOrder.setBusinessunit_name("None");}
		
		if(pOrder.getSupplier_name().compareTo("0") !=0 && pOrder.getSupplier_name().compareTo("") !=0 && pOrder.getSupplier_name() !=null) {
			pOrder.setSupplier(supp_bussiness_partnerRepository.getSupplierName(pOrder.getSupplier_name()).getBp_name());
		}else {pOrder.setSupplier("None");}
	
		if(pOrder.getChannel_req().compareToIgnoreCase("Yes") == 0 ) 
		{
			pOrder.setSup_channel_list(pOrder.getSup_channel_list());
		}
		if(pOrder.getChannel_req().compareToIgnoreCase("No") == 0 ) 
		{
			
			if(Utility.isNullOrEmpty(pOrder.getSup_channel())) 
			{
				
			}
			else 
			{
				pOrder.setSup_channel("");
			}
			
			pOrder.setSup_channel_list(pOrder.getSupplier_name());
		}
		
		if(Utility.isNullOrEmpty(pOrder.getPoitemnumber()))
		{
			
		}
		else 
		{
			pOrder.setPoitemnumber("No");
		}
		
		Set<Pur_Order_Item_Details> itemSet = new HashSet<Pur_Order_Item_Details>();
		itemSet.addAll(pOrder.getPur_Order_Item_Details());
		for(Pur_Order_Item_Details itemDts : itemSet)
		{
			System.out.println(itemDts.getTax_amount()+","+itemDts.getTotal_amount());
			if(Double.toString(itemDts.getTax_amount()).compareTo("NaN")==0)
			{
				itemDts.setTax_amount(0);	
			}
			else
			{
				itemDts.setTax_amount(itemDts.getTax_amount());	
			}
			
			if(Double.toString(itemDts.getTotal_amount()).compareTo("NaN")==0)
			{
				itemDts.setTotal_amount(0);	
			}
			else
			{
				itemDts.setTotal_amount(itemDts.getTotal_amount());	
			}
			
			itemDts.setPur_orderid(gen_sno);
			itemDts.setPur_order_no(pOrder.getPur_order_no());
			itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
			if(itemDts.getPacking_item().compareTo("")!=0 && itemDts.getPacking_item().compareTo("0")!=0) {
				itemDts.setPacking_item_name(item_masterRepository.itemNameById(itemDts.getPacking_item()).getItem_name());
			}
			
			itemDts.setFinal_mat_wt(itemDts.getMat_weight());	/* new 14-04-2022*/
			itemDts.setNo_advice_cal(""+(pOrder.getNo_of_advice()));	/* new 14-04-2022*/
			itemDts.setAdjusted_qty(0.000);
			itemDts.setAdjusted_remarks("NA");
			itemDts.setCompany_id(pOrder.getCompany_id());
			itemDts.setFin_year(pOrder.getFin_year());
			itemDts.setModified_type("INSERTED");
			itemDts.setInserted_by(pOrder.getInserted_by());
			itemDts.setInserted_on(pOrder.getInserted_on());
			itemDts.setUpdated_by("NA");
			itemDts.setUpdated_on(ldt);
			itemDts.setDeleted_by("NA");
			itemDts.setDeleted_on(ldt);
		
			
		}
		pOrder.setPur_Order_Item_Details(itemSet);
		
		Set<Pur_Order_Terms_Con> termSet=new HashSet<Pur_Order_Terms_Con>();
		termSet.add(pOrder.getPur_Order_Terms_Con());
		for(Pur_Order_Terms_Con pTerms_Con : termSet)
		{
			pTerms_Con.setPur_orderid(gen_sno);
			pTerms_Con.setPur_order_no(pOrder.getPur_order_no());
			pTerms_Con.setCompany_id(pOrder.getCompany_id());
			pTerms_Con.setFin_year(pOrder.getFin_year());
			pTerms_Con.setModified_type("INSERTED");
			pTerms_Con.setInserted_by(pOrder.getInserted_by());
			pTerms_Con.setInserted_on(pOrder.getInserted_on());
			pTerms_Con.setUpdated_by("NA");
			pTerms_Con.setUpdated_on(ldt);
			pTerms_Con.setDeleted_by("NA");
			pTerms_Con.setDeleted_on(ldt);
		}
		
		if(!termSet.isEmpty()) {
			pOrder.setPur_Order_Terms_Con(termSet.iterator().next());
		}
		
		Set<Pur_Order_app_chgs> chgsSet = new HashSet<Pur_Order_app_chgs>();
		chgsSet.addAll(pOrder.getPur_Order_app_chgs());
		for(Pur_Order_app_chgs pChgs : chgsSet)
		{
			pChgs.setPur_orderid(gen_sno);
			pChgs.setPur_order_no(pOrder.getPur_order_no());
			pChgs.setCompany_id(pOrder.getCompany_id());
			pChgs.setFin_year(pOrder.getFin_year());
			pChgs.setModified_type("INSERTED");
			pChgs.setInserted_by(pOrder.getInserted_by());
			pChgs.setInserted_on(pOrder.getInserted_on());
			pChgs.setUpdated_by("NA");
			pChgs.setUpdated_on(ldt);
			pChgs.setDeleted_by("NA");
			pChgs.setDeleted_on(ldt);
		
		}
		pOrder.setPur_Order_app_chgs(chgsSet);
		
		Set<Pur_Order_Trans_Chgs_dyn> chgdynSet = new HashSet<Pur_Order_Trans_Chgs_dyn>();
		chgdynSet.addAll(pOrder.getPur_Order_Trans_Chgs_dyn());
		for(Pur_Order_Trans_Chgs_dyn pChgdyn : chgdynSet)
		{
			pChgdyn.setPur_orderid(gen_sno);
			pChgdyn.setPur_order_no(pOrder.getPur_order_no());
			pChgdyn.setCompany_id(pOrder.getCompany_id());
			pChgdyn.setFin_year(pOrder.getFin_year());
			pChgdyn.setModified_type("INSERTED");
			pChgdyn.setInserted_by(pOrder.getInserted_by());
			pChgdyn.setInserted_on(pOrder.getInserted_on());
			pChgdyn.setUpdated_by("NA");
			pChgdyn.setUpdated_on(ldt);
			pChgdyn.setDeleted_by("NA");
			pChgdyn.setDeleted_on(ldt);
		
		}
		pOrder.setPur_Order_Trans_Chgs_dyn(chgdynSet);
		
		Set<Pur_order_terms_conditions> termsConditionSet = new HashSet<Pur_order_terms_conditions>();
		termsConditionSet.addAll(pOrder.getPur_order_terms_conditions());
		for(Pur_order_terms_conditions tCondDyn : termsConditionSet)
		{
			tCondDyn.setPur_orderid(gen_sno);
			tCondDyn.setPur_order_no(pOrder.getPur_order_no());
			tCondDyn.setCompany_id(pOrder.getCompany_id());
			tCondDyn.setFin_year(pOrder.getFin_year());
			tCondDyn.setModified_type("INSERTED");
			tCondDyn.setInserted_by(pOrder.getInserted_by());
			tCondDyn.setInserted_on(pOrder.getInserted_on());
			tCondDyn.setUpdated_by("NA");
			tCondDyn.setUpdated_on(ldt);
			tCondDyn.setDeleted_by("NA");
			tCondDyn.setDeleted_on(ldt);
		
		}
		pOrder.setPur_order_terms_conditions(termsConditionSet);
		
		Set<Pur_Order_BPDetails> BPDetailsSet = new HashSet<Pur_Order_BPDetails>();
		BPDetailsSet.add(pOrder.getPur_Order_BPDetails());
		for(Pur_Order_BPDetails pBpDetails : BPDetailsSet)
		{
			pBpDetails.setPur_orderid(gen_sno);
			pBpDetails.setPur_order_no(pOrder.getPur_order_no());
			pBpDetails.setCompany_id(pOrder.getCompany_id());
			pBpDetails.setFin_year(pOrder.getFin_year());
			pBpDetails.setModified_type("INSERTED");
			pBpDetails.setInserted_by(pOrder.getInserted_by());
			pBpDetails.setInserted_on(pOrder.getInserted_on());
			pBpDetails.setUpdated_by("NA");
			pBpDetails.setUpdated_on(ldt);
			pBpDetails.setDeleted_by("NA");
			pBpDetails.setDeleted_on(ldt);
			
			
		}
		
		if(!BPDetailsSet.isEmpty())
		{
			pOrder.setPur_Order_BPDetails(BPDetailsSet.iterator().next());
		}
		
		Set<Pur_Order_Termination> pTerminationSet = new HashSet<Pur_Order_Termination>();
		pTerminationSet.add(pOrder.getPur_Order_Terminations());
		for(Pur_Order_Termination pTermination : pTerminationSet)
		{
			pTermination.setPur_orderid(gen_sno);
			pTermination.setPur_order_no(pOrder.getPur_order_no());
			pTermination.setCompany_id(pOrder.getCompany_id());
			pTermination.setFin_year(pOrder.getFin_year());
			pTermination.setModified_type("INSERTED");
			pTermination.setInserted_by(pOrder.getInserted_by());
			pTermination.setInserted_on(pOrder.getInserted_on());
			pTermination.setUpdated_by("NA");
			pTermination.setUpdated_on(ldt);
			pTermination.setDeleted_by("NA");
			pTermination.setDeleted_on(ldt);
			
			if(Utility.isNullOrEmpty(pTermination.getReason()))
			{
				if(pTermination.getReason().compareToIgnoreCase("RSM00004")==0)
				{
					pOrder.setPo_status("Cancel");
				}
				else if(pTermination.getReason().compareToIgnoreCase("RSM00005")==0)
				{
					pOrder.setPo_status("Closed");
				}
				else
				{
					
				}
			}
			else 
			{
				
			}
		}
		
		if(!pTerminationSet.isEmpty())
		{
			pOrder.setPur_Order_Terminations(pTerminationSet.iterator().next());
		}
		
		Set<Pur_Order_Termination_dyn> termdSet = new HashSet<Pur_Order_Termination_dyn>();
		termdSet.addAll(pOrder.getPur_Order_Terminations_dyn());
		for(Pur_Order_Termination_dyn pDyn : termdSet)
		{
			pDyn.setPur_orderid(gen_sno);
			pDyn.setPur_order_no(pOrder.getPur_order_no());
			pDyn.setCompany_id(pOrder.getCompany_id());
			pDyn.setFin_year(pOrder.getFin_year());
			pDyn.setModified_type("INSERTED");
			pDyn.setInserted_by(pOrder.getInserted_by());
			pDyn.setInserted_on(pOrder.getInserted_on());
			pDyn.setUpdated_by("NA");
			pDyn.setUpdated_on(ldt);
			pDyn.setDeleted_by("NA");
			pDyn.setDeleted_on(ldt);
		
		}
		pOrder.setPur_Order_Terminations_dyn(termdSet);
		
		Set<Pur_Order_Trans_Info> pInfoSet=new HashSet<Pur_Order_Trans_Info>();
		pInfoSet.add(pOrder.getPur_Order_Trans_Infos());
		for(Pur_Order_Trans_Info pInfo : pInfoSet)
		{
			pInfo.setPur_orderid(gen_sno);
			pInfo.setPur_order_no(pOrder.getPur_order_no());
			pInfo.setCompany_id(pOrder.getCompany_id());
			pInfo.setFin_year(pOrder.getFin_year());
			pInfo.setModified_type("INSERTED");
			pInfo.setInserted_by(pOrder.getInserted_by());
			pInfo.setInserted_on(pOrder.getInserted_on());
			pInfo.setUpdated_by("NA");
			pInfo.setUpdated_on(ldt);
			pInfo.setDeleted_by("NA");
			pInfo.setDeleted_on(ldt);
			
			
		}
		
		if(!pInfoSet.isEmpty()) {
			pOrder.setPur_Order_Trans_Infos(pInfoSet.iterator().next());
		}

		
		Set<Pur_Order_broker> brokerSet = new HashSet<Pur_Order_broker>();
		brokerSet.addAll(pOrder.getPur_Order_broker());
		for(Pur_Order_broker pBroker : brokerSet)
		{
			pBroker.setPur_orderid(gen_sno);
			pBroker.setPur_order_no(pOrder.getPur_order_no());
			if(pBroker.getVen_code_name().compareTo("")!=0 && pBroker.getVen_code_name().compareTo("0")!=0) {
				pBroker.setVen_name(broker_masterRepository.brokerNameByCode(pBroker.getVen_code_name()).getName());
			}
			pBroker.setCompany_id(pOrder.getCompany_id());
			pBroker.setFin_year(pOrder.getFin_year());
			pBroker.setModified_type("INSERTED");
			pBroker.setInserted_by(pOrder.getInserted_by());
			pBroker.setInserted_on(pOrder.getInserted_on());
			pBroker.setUpdated_by("NA");
			pBroker.setUpdated_on(ldt);
			pBroker.setDeleted_by("NA");
			pBroker.setDeleted_on(ldt);
		
		}
		pOrder.setPur_Order_broker(brokerSet);
		
		Set<Pur_Order_Doc> docSet = new HashSet<Pur_Order_Doc>();
		docSet.addAll(pOrder.getPur_Order_docs());
		int i=0;
		for(Pur_Order_Doc docDts : docSet)
		{
			docDts.setPur_orderid(gen_sno);
			
			

			System.out.println(" hello files : "+files.length);
			//here start
			
			if(files.length > 0) {
				try {
					System.out.println("files[i] :: "+i+" / "+files[i]);
						//fileUpload(files[i],gen_sno+"_");
					
					docDts.setDoc_pdf(FileUploadUtil.fileUpload(files[i],gen_sno+"_",FilePurchaseOrderUtil.folderPath));
						
					
				i++;
				}
				catch (IOException e)
				{
					System.out.println(e);
					}
				
			}
			System.out.println("3 :: ");
			
			docDts.setPur_order_no(pOrder.getPur_order_no());
			docDts.setCompany_id(pOrder.getCompany_id());
			docDts.setFin_year(pOrder.getFin_year());
			docDts.setModified_type("INSERTED");
			docDts.setInserted_by(pOrder.getInserted_by());
			docDts.setInserted_on(pOrder.getInserted_on());
			docDts.setUpdated_by("NA");
			docDts.setUpdated_on(ldt);
			docDts.setDeleted_by("NA");
			docDts.setDeleted_on(ldt);
		}
		pOrder.setPur_Order_docs(docSet);
		
		Set<Pur_order_store_chgs> storeSet = new HashSet<Pur_order_store_chgs>();
		storeSet.addAll(pOrder.getPur_order_store_chgs());
		for(Pur_order_store_chgs pStore : storeSet)
		{
			pStore.setPur_orderid(gen_sno);
			pStore.setPur_order_no(pOrder.getPur_order_no());
			
			if(Utility.isNullOrEmpty(pStore.getCharges_acc()))
			{
				System.out.println("acc:"+pStore.getCharges_acc());
				pStore.setCharges_acc_code(ledgermasterRepository.getLedgerDetailsThrName(pStore.getCharges_acc()).getLedgerid());
			}
			else 
			{
				pStore.setCharges_acc_code("");
			}
			pStore.setCompany_id(pOrder.getCompany_id());
			pStore.setFin_year(pOrder.getFin_year());
			pStore.setModified_type("INSERTED");
			pStore.setInserted_by(pOrder.getInserted_by());
			pStore.setInserted_on(pOrder.getInserted_on());
			pStore.setUpdated_by("NA");
			pStore.setUpdated_on(ldt);
			pStore.setDeleted_by("NA");
			pStore.setDeleted_on(ldt);
			pStore.setUsername(pOrder.getUsername());
		
		}
		pOrder.setPur_Order_broker(brokerSet);
		
		
		return pur_OrderRepository.save(pOrder);
	}
	
	public List<Pur_Order> findAllOrder()
	{
		List<Pur_Order> ordList=new ArrayList<Pur_Order>();
		ordList.addAll(pur_OrderRepository.findAllPurOrders());
		
		for(int i=0;i<ordList.size();i++) {
			ordList.get(i).setSer_item_subtype(iTypeRepository.itemTypeNameByCode(ordList.get(i).getSer_item_subtype()).getItem_name());
		}
		return ordList;
	}
	
	
	 public Page<Pur_Order_Pagination_DTO> getPurOrderPagination(int page,int size)
	  {
		  
			//PageRequest pageRequest = PageRequest.of(page, size,Sort.by("id").descending());
		 	PageRequest pageRequest = PageRequest.of(page, size,Sort.by("orddate").descending().and(Sort.by("purorderno")).descending());
		    Page<Pur_Order> pageResult = pur_OrderRepository.findAll(pageRequest);
		   
		    List<Pur_Order_Pagination_DTO> advList = pageResult
		    	      .stream()
		    	      .map((Pur_Order pur_Order) -> new Pur_Order_Pagination_DTO(pur_Order.getId(),
		    	    		  pur_Order.getPur_orderid(),
		    	    		  pur_Order.getPur_order_no(),
		    	    		  pur_Order.getOrd_date(),
		    	    		  pur_Order.getSupplier(),
		    	      		  pur_Order.getBusinessunit_name(),
		    	      		  pur_Order.getSer_item_subtype(),
		    	      		  pur_Order.getPur_ord_type(),
		    	      		  pur_Order.getAdvice_req(),
		    	      		  pur_Order.getPo_status(),
		    	    		  pur_Order.getModified_type()
		    	    		)).filter(c -> c.getModified_type().equals("INSERTED")).collect(Collectors.toList());
		    	      
		   
		    return new PageImpl<>(advList, pageRequest, pageResult.getTotalElements());
	  }
	 
	 public List<Map<String,Object>> searchPurchaseOrderFast(String orderno,String fromdate, String todate,String supplier_name1,String finyear)
		{
		 	List<Map<String,Object>> searchpurorder =new ArrayList<Map<String,Object>>();
			
			String tablename="pur_order",party_name="supplier_name",order_no="pur_order_no",order_date="ord_date";
			//System.out.println("tablename: "+tablename+" party_name: "+party_name+" order_no "+order_no+" order_date "+order_date+" orderno "+orderno+" fromdate "+fromdate+" todate "+todate+" supplier_name1 "+supplier_name1+" finyear "+finyear);
			searchpurorder.addAll(pur_OrderRepository.getsearchdataFast(tablename,party_name,order_no,order_date,orderno,fromdate,todate,supplier_name1,finyear));
			
			return searchpurorder;
		}
	 
	 public List<Pur_Order_Pagination_DTO> searchPurchaseOrder(String orderno,String fromdate, String todate,String supplier_name1,String finyear)
		{
			List<Pur_Order> searchpurorder =new ArrayList<Pur_Order>();
			
			String tablename="pur_order",party_name="supplier_name",order_no="pur_order_no",order_date="ord_date";
			//System.out.println("tablename: "+tablename+" party_name: "+party_name+" order_no "+order_no+" order_date "+order_date+" orderno "+orderno+" fromdate "+fromdate+" todate "+todate+" supplier_name1 "+supplier_name1+" finyear "+finyear);
			searchpurorder.addAll(pur_OrderRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,supplier_name1,finyear));
			
		//	System.out.println("chk size:"+pur_OrderRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,supplier_name1,finyear));
			List<Pur_Order_Pagination_DTO> paginationlist = new ArrayList<Pur_Order_Pagination_DTO>();
			for(int i=0;i<searchpurorder.size();i++) 
			{
				
				//System.out.println("serchsaleorder.get(i).getId() "+searchsalesinvoice.get(i).getId());
				Pur_Order_Pagination_DTO sale= new Pur_Order_Pagination_DTO(searchpurorder.get(i).getId(),
						searchpurorder.get(i).getPur_orderid(),
						searchpurorder.get(i).getPur_order_no(),
						searchpurorder.get(i).getOrd_date(),
						searchpurorder.get(i).getSupplier(),
						searchpurorder.get(i).getBusinessunit_name(),
						searchpurorder.get(i).getSer_item_subtype(),
						searchpurorder.get(i).getPur_ord_type(),
						searchpurorder.get(i).getAdvice_req(),
						searchpurorder.get(i).getPo_status(),
						searchpurorder.get(i).getModified_type());
				
				paginationlist.add(sale);
			}
			
			List<Pur_Order_Pagination_DTO> allData = paginationlist
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Pur_Order_Pagination_DTO::getOrd_date).
							  thenComparingInt(
	                          d -> d.getOrd_date().length() + d.getPur_order_no().length())
	                  .thenComparing(Pur_Order_Pagination_DTO::getPur_order_no).reversed())
					  .collect(Collectors.toList());
			
			return allData;
			
			
		}
	 
	 public List<Map<String,Object>> purchaseorderlist (String curdate)
	 {
		 return pur_OrderRepository.purchaseorderlist(curdate);
	 }
	
	 public List<Map<String,Object>> getdocumentno()
	 {
		 return pur_OrderRepository.getdocumentno();
	 }
	 
	public List<Pur_Order> getPurOrd(String pur_order_id)
	{
		List<Pur_Order> ordList=new ArrayList<Pur_Order>();
		ordList.addAll(pur_OrderRepository.findPurOrders(pur_order_id));
		return ordList;
	}
	
	
	public String getpssd_item_qtyold(String unloadadvice,String weighmentdata,String purchaseid) 
	{
		String output="";
		System.out.println(" check here  :: " + unloadadvice +" / " + weighmentdata +" /" + purchaseid);
	
		List<Pur_Order> ordList=new ArrayList<Pur_Order>();
		ordList.addAll(pur_OrderRepository.findPurOrders(purchaseid));
		
		
		String receipt_criteria = ordList.get(0).getReceipt_criteria();
		
		System.out.println("receipt_criteria "+ receipt_criteria);
		
		
		List<Wm_unload_advice> modelList1=new ArrayList<Wm_unload_advice>();
		modelList1.add(pur_OrderRepository.getUnloadDetailsnew(weighmentdata));
		//Wm_unload_advice modelList1 = wm_unload_adviceRepository.getUnloadDetailsnew(weighmentdata);
		
		String weighment_id= modelList1.get(0).getWeighment_id();
		
		
		List<Wm_unload_wgmnt> modelList2=new ArrayList<Wm_unload_wgmnt>();
		modelList2.add(pur_OrderRepository.getUnloadWeightmentWtnew(weighment_id));
		
		
		
		double net_weight= modelList2.get(0).getNet_weight();
		
		
		System.out.println(" check here :"+ receipt_criteria + " / " + weighment_id + " / " + net_weight);
		double unloadnet =Double.parseDouble(unloadadvice);
		double weigmentnet =net_weight;
		
		//chanegs 02-05-2022
		//output = ""+weigmentnet;
		
		output = ""+weigmentnet;
		//output=output+"tuhintaresplit"+modelList2.get(0).getTarebags();
		
		
		System.out.println(" output :: " + output);
		return output;
	}
	
	
	public String getpssd_item_qty(String unloadadvice,String weighmentdata,String purchaseid) 
	{
		String output="";
		//SELECT  (SELECT net_weight FROM wm_unload_wgmnt WHERE wgment_id = w.weighment_id AND modified_type = 'INSERTED') AS net_weight FROM wm_unload_advice w WHERE w.unadviceid ='WUL02410' AND w.modified_type = 'INSERTED'
		double net_weight=pur_OrderRepository.getnetweight(weighmentdata);
		output = ""+net_weight;
		return output;
	}
	
	
	public double getpssd_item_qtyrectify(String unadviceid,String pricebasedon,String subtypestatus)
	{
		double output=0.00;
		
		if(subtypestatus.compareToIgnoreCase("Yes") == 0) 
		{
			if(pricebasedon.compareToIgnoreCase("Packing") == 0) 
			{
				//output=pur_OrderRepository.getUnloadWeightmentWtnewrectifyraw(unadviceid);
				output=Double.parseDouble(pur_OrderRepository.getUnloadWeightmentWtnewrectifyraw(unadviceid));
			}
			else 
			{
				output=pur_OrderRepository.getpssd_item_qtyrectifyraw(unadviceid);
			}
		}
		else 
		{
			output=pur_OrderRepository.getpssd_item_qtyrectifyraw(unadviceid);
		}
		return output;
	}
	
	public String getpssd_packing_qty(String unloadadvice,String weighmentdata,String purchaseid) 
	{
		String output="";
		//System.out.println(" check here  :: " + unloadadvice +" / " + weighmentdata +" /" + purchaseid);
	
		List<Pur_Order> ordList=new ArrayList<Pur_Order>();
		ordList.addAll(pur_OrderRepository.findPurOrders(purchaseid));
		
		
		String receipt_criteria = ordList.get(0).getReceipt_criteria();
		
		//System.out.println("receipt_criteria "+ receipt_criteria);
		
		
		List<Wm_unload_advice> modelList1=new ArrayList<Wm_unload_advice>();
		modelList1.add(pur_OrderRepository.getUnloadDetailsnew(weighmentdata));
		//Wm_unload_advice modelList1 = wm_unload_adviceRepository.getUnloadDetailsnew(weighmentdata);
		
		String weighment_id= modelList1.get(0).getWeighment_id();
		
		//System.out.println(weighment_id);
		List<Wm_unload_wgmnt> modelList2=new ArrayList<Wm_unload_wgmnt>();
		modelList2.add(pur_OrderRepository.getUnloadWeightmentWtnew(weighment_id));
		
		
		
	//	double net_weight= modelList2.get(0).getTarebags();
		
		
		//System.out.println(" check here :"+ receipt_criteria + " / " + weighment_id + " / " + net_weight);
		double unloadnet =Double.parseDouble(unloadadvice);
		//double weigmentnet =net_weight;
		
		//chanegs 02-05-2022
		//output = ""+weigmentnet;
		
		output = modelList2.get(0).getTarebags();
		//output=output+"tuhintaresplit"+modelList2.get(0).getTarebags();
		
		
		System.out.println(" output :: " + output);
		return output;
	}
	
	
	public double getpssd_packing_qtyrectify(String unadviceid,String pricebasedon,String subtypestatus)
	{
		double output=0.00;
		
		if(subtypestatus.compareToIgnoreCase("Yes") == 0) 
		{
			//output=pur_OrderRepository.getUnloadWeightmentWtnewrectify(unadviceid,pricebasedon);
			//Packing
			if(pricebasedon.compareToIgnoreCase("Packing") == 0) 
			{
				output=pur_OrderRepository.getpssd_item_qtyrectifyraw(unadviceid);
			}
			else 
			{
				
				output=Double.parseDouble(pur_OrderRepository.getUnloadWeightmentWtnewrectifyraw(unadviceid));
			}
			
		}
		else 
		{
			output=Double.parseDouble(pur_OrderRepository.getUnloadWeightmentWtnewrectifyraw(unadviceid));
		}
		return output;
	}
	
	public String getpssd_item_qty_multi_popup(String unloadadvice) 
	{
		String output="";
		//System.out.println("unloadadvice/"+unloadadvice);
		if(unloadadvice.contains(","))
		{
			System.out.println("check");
			String[] unload = unloadadvice.split(",");
			
			double tot_wm_weight=0.000;
			for(int p=0;p<unload.length;p++)
			{
				List<Wm_unload_advice> modelList1=new ArrayList<Wm_unload_advice>();
				modelList1.add(pur_OrderRepository.getUnloadDetailsnew(unload[p]));
				
				String weighment_id= modelList1.get(0).getWeighment_id();
				
				List<Wm_unload_wgmnt> modelList2=new ArrayList<Wm_unload_wgmnt>();
				modelList2.add(pur_OrderRepository.getUnloadWeightmentWtnew(weighment_id));
				
				double net_weight= modelList2.get(0).getNet_weight();
				tot_wm_weight=tot_wm_weight+net_weight;
				
			}
			
			output = ""+tot_wm_weight;
			
		}
		else
		{
			
				List<Wm_unload_advice> modelList1=new ArrayList<Wm_unload_advice>();
				modelList1.add(pur_OrderRepository.getUnloadDetailsnew(unloadadvice));
				
				String weighment_id= modelList1.get(0).getWeighment_id();
				
				List<Wm_unload_wgmnt> modelList2=new ArrayList<Wm_unload_wgmnt>();
				modelList2.add(pur_OrderRepository.getUnloadWeightmentWtnew(weighment_id));
				
				double net_weight= modelList2.get(0).getNet_weight();
				
			output = ""+net_weight;
		}
		
		System.out.println(" output :: " + output);
		return output;
	}
	
	
	
	public String getpssd_packing_qtymultiplepopup(String unloadadvice) 
	{
		String output="";
		//System.out.println("unloadadvice/"+unloadadvice);
		if(unloadadvice.contains(","))
		{
			System.out.println("check");
			String[] unload = unloadadvice.split(",");
			
			double tot_wm_weight=0.000;
			for(int p=0;p<unload.length;p++)
			{
				List<Wm_unload_advice> modelList1=new ArrayList<Wm_unload_advice>();
				modelList1.add(pur_OrderRepository.getUnloadDetailsnew(unload[p]));
				
				String weighment_id= modelList1.get(0).getWeighment_id();
				
				List<Wm_unload_wgmnt> modelList2=new ArrayList<Wm_unload_wgmnt>();
				modelList2.add(pur_OrderRepository.getUnloadWeightmentWtnew(weighment_id));
				
				double net_weight= Double.parseDouble(modelList2.get(0).getTarebags());
				tot_wm_weight=tot_wm_weight+net_weight;
				
			}
			
			output = ""+tot_wm_weight;
			
		}
		else
		{
			
				List<Wm_unload_advice> modelList1=new ArrayList<Wm_unload_advice>();
				modelList1.add(pur_OrderRepository.getUnloadDetailsnew(unloadadvice));
				
				String weighment_id= modelList1.get(0).getWeighment_id();
				
				List<Wm_unload_wgmnt> modelList2=new ArrayList<Wm_unload_wgmnt>();
				modelList2.add(pur_OrderRepository.getUnloadWeightmentWtnew(weighment_id));
				
				double net_weight= Double.parseDouble(modelList2.get(0).getTarebags());
				
			output = ""+net_weight;
		}
		
		System.out.println(" output :: " + output);
		return output;
	}
	
	@Autowired
	Pur_Order_app_chgsRepository pur_Order_app_chgsRepository;
	
	@Autowired
	Pur_Order_Termination_dynRepository pur_Order_Termination_dynRepository;
	
	@Autowired
	Pur_Order_brokerRepository pur_Order_brokerRepository;
	
	@Autowired
	Pur_Order_DocRepository pur_Order_DocRepository;
	
	@Transactional
	public Pur_Order update(Pur_Order iMaster,Long id)
	{
		Optional<Pur_Order> op = pur_OrderRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		iMaster.setPo_status("Open");  /* new 14-04-2022 */
	//	iMaster.setTagadvice_status("No");
		iMaster.setPur_orderid(op.get().getPur_orderid());
		iMaster.setPur_order_no(op.get().getPur_order_no());
		iMaster.setPurorderno(op.get().getPurorderno());
		iMaster.setModified_type("INSERTED");
		iMaster.setInserted_by(op.get().getInserted_by());
		iMaster.setInserted_on(op.get().getInserted_on());
		iMaster.setUpdated_by(userRepository.getUserDetails(iMaster.getUsername()).getName());
		iMaster.setUpdated_on(ldt);
		iMaster.setDeleted_by("NA");
		iMaster.setDeleted_on(ldt);
		iMaster.setPur_return_status(op.get().getPur_return_status());
		iMaster.setPurreturnid(op.get().getPurreturnid());
		
		
		iMaster.setOrddate(iMaster.getOrd_date());
		iMaster.setPurorderno(iMaster.getPur_order_no());
		
		if(Utility.isNullOrEmpty(iMaster.getStore_charges())) 
		{
		if(iMaster.getStore_charges().compareTo("") != 0 && iMaster.getStore_charges() != null) {
			iMaster.setStore_charges_name(store_inv_charge_masterRepository.getStoreChargeMaster(iMaster.getStore_charges()).getStore_charge_desc());
		}else {iMaster.setStore_charges_name("None");}
		}
		else 
		{
			iMaster.setStore_charges_name("None");
		}
		
		if(iMaster.getSer_item_subtype().compareTo("0") != 0 && iMaster.getSer_item_subtype().compareTo("") != 0 && iMaster.getSer_item_subtype() != null) {
			iMaster.setSer_item_subtype_name(item_type_masterRepository.getItemType(iMaster.getSer_item_subtype()).getItem_name());
		}else {iMaster.setSer_item_subtype_name("None");}
		
		if(iMaster.getBusinessunit().compareTo("0") !=0 && iMaster.getBusinessunit().compareTo("") !=0 && iMaster.getBusinessunit() !=null) {
			iMaster.setBusinessunit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(iMaster.getBusinessunit()).getBusinessunit_name());
		}else {iMaster.setBusinessunit_name("None");}
		
		if(iMaster.getSupplier_name().compareTo("0") !=0 && iMaster.getSupplier_name().compareTo("") !=0 && iMaster.getSupplier_name() !=null) {
			iMaster.setSupplier(supp_bussiness_partnerRepository.getSupplierName(iMaster.getSupplier_name()).getBp_name());
		}else {iMaster.setSupplier("None");}
		
		if(op.isPresent())
		{
			iMaster.setId(id);
		}
		
		if(iMaster.getChannel_req().compareToIgnoreCase("Yes") == 0 ) 
		{
			iMaster.setSup_channel_list(iMaster.getSup_channel_list());
		}
		if(iMaster.getChannel_req().compareToIgnoreCase("No") == 0 ) 
		{
			
			if(Utility.isNullOrEmpty(iMaster.getSup_channel())) 
			{
				
			}
			else 
			{
				iMaster.setSup_channel("");
			}
			
			iMaster.setSup_channel_list(iMaster.getSupplier_name());
		}
		
		
		pur_Order_Item_DetailsRepository.pur_Order_Item_Detailsupdate(op.get().getPur_orderid());
		Set<Pur_Order_Item_Details> itemSet = new HashSet<Pur_Order_Item_Details>();
		itemSet.addAll(iMaster.getPur_Order_Item_Details());
		for(Pur_Order_Item_Details itemDts : itemSet)
		{
			itemDts.setPur_orderid(op.get().getPur_orderid());
			itemDts.setPur_order_no(iMaster.getPur_order_no());
			itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
			if(itemDts.getPacking_item().compareTo("")!=0  && itemDts.getPacking_item().compareTo("0")!=0) {
				itemDts.setPacking_item_name(item_masterRepository.itemNameById(itemDts.getPacking_item()).getItem_name());
			}
			// Added for Adjust Qty
			itemDts.setAdjusted_qty(0.000);
			itemDts.setAdjusted_remarks("NA");
			
			itemDts.setCompany_id(iMaster.getCompany_id());
			itemDts.setFin_year(iMaster.getFin_year());
			itemDts.setModified_type("INSERTED");
			itemDts.setInserted_by(iMaster.getInserted_by());
			itemDts.setInserted_on(iMaster.getInserted_on());
			itemDts.setUpdated_by(iMaster.getUpdated_by());
			itemDts.setUpdated_on(iMaster.getUpdated_on());
			itemDts.setDeleted_by("NA");
			itemDts.setDeleted_on(ldt);
		
		}
		iMaster.setPur_Order_Item_Details(itemSet);
		
		pur_Order_Terms_ConRepository.pur_Order_Terms_Conupdate(op.get().getPur_orderid());
		Set<Pur_Order_Terms_Con> termSet=new HashSet<Pur_Order_Terms_Con>();
		termSet.add(iMaster.getPur_Order_Terms_Con());
		for(Pur_Order_Terms_Con pTerms_Con : termSet)
		{
			pTerms_Con.setPur_orderid(op.get().getPur_orderid());
			pTerms_Con.setPur_order_no(iMaster.getPur_order_no());
			pTerms_Con.setCompany_id(iMaster.getCompany_id());
			pTerms_Con.setFin_year(iMaster.getFin_year());
			pTerms_Con.setModified_type("INSERTED");
			pTerms_Con.setInserted_by(iMaster.getInserted_by());
			pTerms_Con.setInserted_on(iMaster.getInserted_on());
			pTerms_Con.setUpdated_by(iMaster.getUpdated_by());
			pTerms_Con.setUpdated_on(iMaster.getUpdated_on());
			pTerms_Con.setDeleted_by("NA");
			pTerms_Con.setDeleted_on(ldt);
		}
		
		if(!termSet.isEmpty()) {
			iMaster.setPur_Order_Terms_Con(termSet.iterator().next());
		}
		
		
		pur_Order_app_chgsRepository.pur_Order_app_chgsupdate(op.get().getPur_orderid());
		Set<Pur_Order_app_chgs> chgsSet = new HashSet<Pur_Order_app_chgs>();
		chgsSet.addAll(iMaster.getPur_Order_app_chgs());
		for(Pur_Order_app_chgs pChgs : chgsSet)
		{
			pChgs.setPur_orderid(op.get().getPur_orderid());
			pChgs.setPur_order_no(iMaster.getPur_order_no());
			pChgs.setCompany_id(iMaster.getCompany_id());
			pChgs.setFin_year(iMaster.getFin_year());
			pChgs.setModified_type("INSERTED");
			pChgs.setInserted_by(iMaster.getInserted_by());
			pChgs.setInserted_on(iMaster.getInserted_on());
			pChgs.setUpdated_by(iMaster.getUpdated_by());
			pChgs.setUpdated_on(iMaster.getUpdated_on());
			pChgs.setDeleted_by("NA");
			pChgs.setDeleted_on(ldt);
		
		}
		iMaster.setPur_Order_app_chgs(chgsSet);
		
		pur_order_terms_conditionsRepository.pur_order_terms_conditionsupdate(op.get().getPur_orderid());
		Set<Pur_order_terms_conditions> tCondSet = new HashSet<Pur_order_terms_conditions>();
		tCondSet.addAll(iMaster.getPur_order_terms_conditions());
		for(Pur_order_terms_conditions pTermCond : tCondSet)
		{
			pTermCond.setPur_orderid(op.get().getPur_orderid());
			pTermCond.setPur_order_no(iMaster.getPur_order_no());
			pTermCond.setCompany_id(iMaster.getCompany_id());
			pTermCond.setFin_year(iMaster.getFin_year());
			pTermCond.setModified_type("INSERTED");
			pTermCond.setInserted_by(iMaster.getInserted_by());
			pTermCond.setInserted_on(iMaster.getInserted_on());
			pTermCond.setUpdated_by(iMaster.getUpdated_by());
			pTermCond.setUpdated_on(iMaster.getUpdated_on());
			pTermCond.setDeleted_by("NA");
			pTermCond.setDeleted_on(ldt);
		
		}
		iMaster.setPur_order_terms_conditions(tCondSet);
		
		pur_Order_BPDetailsRepository.pur_Order_BPDetailsupdate(op.get().getPur_orderid());
		Set<Pur_Order_BPDetails> BPDetailsSet = new HashSet<Pur_Order_BPDetails>();
		BPDetailsSet.add(iMaster.getPur_Order_BPDetails());
		for(Pur_Order_BPDetails pBpDetails : BPDetailsSet)
		{
			pBpDetails.setPur_orderid(op.get().getPur_orderid());
			pBpDetails.setPur_order_no(iMaster.getPur_order_no());
			pBpDetails.setCompany_id(iMaster.getCompany_id());
			pBpDetails.setFin_year(iMaster.getFin_year());
			pBpDetails.setModified_type("INSERTED");
			pBpDetails.setInserted_by(iMaster.getInserted_by());
			pBpDetails.setInserted_on(iMaster.getInserted_on());
			pBpDetails.setUpdated_by(iMaster.getUpdated_by());
			pBpDetails.setUpdated_on(iMaster.getUpdated_on());
			pBpDetails.setDeleted_by("NA");
			pBpDetails.setDeleted_on(ldt);
		}
		
		if(!BPDetailsSet.isEmpty())
		{
			iMaster.setPur_Order_BPDetails(BPDetailsSet.iterator().next());
		}
		
		
		pur_Order_TerminationRepository.pur_Order_Terminationupdate(op.get().getPur_orderid());
		Set<Pur_Order_Termination> pTerminationSet = new HashSet<Pur_Order_Termination>();
		pTerminationSet.add(iMaster.getPur_Order_Terminations());
		for(Pur_Order_Termination pTermination : pTerminationSet)
		{
			pTermination.setPur_orderid(op.get().getPur_orderid());
			pTermination.setPur_order_no(iMaster.getPur_order_no());
			pTermination.setCompany_id(iMaster.getCompany_id());
			pTermination.setFin_year(iMaster.getFin_year());
			pTermination.setModified_type("INSERTED");
			pTermination.setInserted_by(iMaster.getInserted_by());
			pTermination.setInserted_on(iMaster.getInserted_on());
			pTermination.setUpdated_by(iMaster.getUpdated_by());
			pTermination.setUpdated_on(iMaster.getUpdated_on());
			pTermination.setDeleted_by("NA");
			pTermination.setDeleted_on(ldt);
			
			
			
			if(Utility.isNullOrEmpty(pTermination.getReason()))
			{
				if(pTermination.getReason().compareToIgnoreCase("RSM00004")==0)
				{
					iMaster.setPo_status("Cancel");
				}
				else if(pTermination.getReason().compareToIgnoreCase("RSM00005")==0)
				{
					iMaster.setPo_status("Closed");
				}
				else
				{
					
				}
			}
			else 
			{
				
			}
			
			
			
		}
		
		if(!pTerminationSet.isEmpty())
		{
			iMaster.setPur_Order_Terminations(pTerminationSet.iterator().next());
		}
		
		pur_Order_Termination_dynRepository.pur_Order_Termination_dynupdate(op.get().getPur_orderid());
		Set<Pur_Order_Termination_dyn> termdSet = new HashSet<Pur_Order_Termination_dyn>();
		termdSet.addAll(iMaster.getPur_Order_Terminations_dyn());
		for(Pur_Order_Termination_dyn pDyn : termdSet)
		{
			pDyn.setPur_orderid(op.get().getPur_orderid());
			pDyn.setPur_order_no(iMaster.getPur_order_no());
			pDyn.setCompany_id(iMaster.getCompany_id());
			pDyn.setFin_year(iMaster.getFin_year());
			pDyn.setModified_type("INSERTED");
			pDyn.setInserted_by(iMaster.getInserted_by());
			pDyn.setInserted_on(iMaster.getInserted_on());
			pDyn.setUpdated_by(iMaster.getUpdated_by());
			pDyn.setUpdated_on(iMaster.getUpdated_on());
			pDyn.setDeleted_by("NA");
			pDyn.setDeleted_on(ldt);
		
		}
		iMaster.setPur_Order_Terminations_dyn(termdSet);
		
		pur_Order_Trans_Chgs_dynRepository.pur_Order_Trans_Chgs_dynupdate(op.get().getPur_orderid());
		Set<Pur_Order_Trans_Chgs_dyn> chgdynSet = new HashSet<Pur_Order_Trans_Chgs_dyn>();
		chgdynSet.addAll(iMaster.getPur_Order_Trans_Chgs_dyn());
		for(Pur_Order_Trans_Chgs_dyn pChgdyn : chgdynSet)
		{
			pChgdyn.setPur_orderid(op.get().getPur_orderid());
			pChgdyn.setPur_order_no(iMaster.getPur_order_no());
			pChgdyn.setCompany_id(iMaster.getCompany_id());
			pChgdyn.setFin_year(iMaster.getFin_year());
			pChgdyn.setModified_type("INSERTED");
			pChgdyn.setInserted_by(iMaster.getInserted_by());
			pChgdyn.setInserted_on(iMaster.getInserted_on());
			pChgdyn.setUpdated_by(iMaster.getUpdated_by());
			pChgdyn.setUpdated_on(iMaster.getUpdated_on());
			pChgdyn.setDeleted_by("NA");
			pChgdyn.setDeleted_on(ldt);
		}
		iMaster.setPur_Order_Trans_Chgs_dyn(chgdynSet);
		
		
		pur_Order_Trans_InfoRepository.pur_Order_Trans_Infoupdate(op.get().getPur_orderid());
		Set<Pur_Order_Trans_Info> pInfoSet=new HashSet<Pur_Order_Trans_Info>();
		pInfoSet.add(iMaster.getPur_Order_Trans_Infos());
		for(Pur_Order_Trans_Info pInfo : pInfoSet)
		{
			pInfo.setPur_orderid(op.get().getPur_orderid());
			pInfo.setPur_order_no(iMaster.getPur_order_no());
			pInfo.setCompany_id(iMaster.getCompany_id());
			pInfo.setFin_year(iMaster.getFin_year());
			pInfo.setModified_type("INSERTED");
			pInfo.setInserted_by(iMaster.getInserted_by());
			pInfo.setInserted_on(iMaster.getInserted_on());
			pInfo.setUpdated_by(iMaster.getUpdated_by());
			pInfo.setUpdated_on(iMaster.getUpdated_on());
			pInfo.setDeleted_by("NA");
			pInfo.setDeleted_on(ldt);
		}
		
		if(!pInfoSet.isEmpty()) {
			iMaster.setPur_Order_Trans_Infos(pInfoSet.iterator().next());
		}

		
		pur_Order_brokerRepository.pur_Order_brokerupdate(op.get().getPur_orderid());
		Set<Pur_Order_broker> brokerSet = new HashSet<Pur_Order_broker>();
		brokerSet.addAll(iMaster.getPur_Order_broker());
		for(Pur_Order_broker pBroker : brokerSet)
		{
			pBroker.setPur_orderid(op.get().getPur_orderid());
			pBroker.setPur_order_no(iMaster.getPur_order_no());
			if(pBroker.getVen_code_name().compareTo("")!=0 && pBroker.getVen_code_name().compareTo("0")!=0) {
				pBroker.setVen_name(broker_masterRepository.brokerNameByCode(pBroker.getVen_code_name()).getName());
			}
			pBroker.setCompany_id(iMaster.getCompany_id());
			pBroker.setFin_year(iMaster.getFin_year());
			pBroker.setModified_type("INSERTED");
			pBroker.setInserted_by(iMaster.getInserted_by());
			pBroker.setInserted_on(iMaster.getInserted_on());
			pBroker.setUpdated_by(iMaster.getUpdated_by());
			pBroker.setUpdated_on(iMaster.getUpdated_on());
			pBroker.setDeleted_by("NA");
			pBroker.setDeleted_on(ldt);
		
		}
		iMaster.setPur_Order_broker(brokerSet);
		
		pur_Order_DocRepository.pur_Order_Docupdate(op.get().getPur_orderid());
		Set<Pur_Order_Doc> docSet = new HashSet<Pur_Order_Doc>();
		docSet.addAll(iMaster.getPur_Order_docs());
		for(Pur_Order_Doc docDts : docSet)
		{
			docDts.setPur_orderid(op.get().getPur_orderid());
			docDts.setPur_order_no(iMaster.getPur_order_no());
			docDts.setCompany_id(iMaster.getCompany_id());
			docDts.setFin_year(iMaster.getFin_year());
			docDts.setModified_type("INSERTED");
			docDts.setInserted_by(iMaster.getInserted_by());
			docDts.setInserted_on(iMaster.getInserted_on());
			docDts.setUpdated_by(iMaster.getUpdated_by());
			docDts.setUpdated_on(iMaster.getUpdated_on());
			docDts.setDeleted_by("NA");
			docDts.setDeleted_on(ldt);
		
		}
		iMaster.setPur_Order_docs(docSet);
		
		pur_order_store_chgsRepository.pur_Order_storeUpdate(op.get().getPur_orderid());
		Set<Pur_order_store_chgs> storeSet = new HashSet<Pur_order_store_chgs>();
		storeSet.addAll(iMaster.getPur_order_store_chgs());
		for(Pur_order_store_chgs pStore : storeSet)
		{
			pStore.setPur_orderid(op.get().getPur_orderid());
			pStore.setPur_order_no(iMaster.getPur_order_no());
			if(Utility.isNullOrEmpty(pStore.getCharges_acc()))
			{
				pStore.setCharges_acc_code(ledgermasterRepository.getLedgerDetailsThrName(pStore.getCharges_acc()).getLedgerid());
			}
			else 
			{
				pStore.setCharges_acc_code("");
			}
			pStore.setCompany_id(iMaster.getCompany_id());
			pStore.setFin_year(iMaster.getFin_year());
			pStore.setModified_type("INSERTED");
			pStore.setInserted_by(iMaster.getInserted_by());
			pStore.setInserted_on(iMaster.getInserted_on());
			pStore.setUpdated_by(iMaster.getUpdated_by());
			pStore.setUpdated_on(iMaster.getUpdated_on());
			pStore.setDeleted_by("NA");
			pStore.setDeleted_on(ldt);
			pStore.setUsername(iMaster.getUsername());
		
		}
		iMaster.setPur_Order_broker(brokerSet);
		
		
		return pur_OrderRepository.save(iMaster);
	}
	
	public Pur_Order findOne(long id)
	{
		Optional<Pur_Order> op=this.pur_OrderRepository.findById(id);
		return op.get();
	}
	
	public List<Pur_Order_Item_DetailsDTO> getPurOrdItemDtlsList()
	{
		List<Pur_Order_Item_Details> modelList=pur_Order_Item_DetailsRepository.getPurOrdItemDtlsList();
		Type listType=new TypeToken<List<Pur_Order_Item_DetailsDTO>>() {}.getType();
		List<Pur_Order_Item_DetailsDTO> enqList=new ModelMapper().map(modelList,listType);
		return enqList;
	}
	
	
	 public List<Pur_Order_brokerDTO> getPurOrdBrokerList(String pur_order_no)
	 {
		 	System.out.println("pur_order_no: "+pur_order_no);
			List<Pur_Order_broker> modelList=pur_Order_brokerRepository.getPurOrdBrokerList(pur_order_no);
			
			Type listType=new TypeToken<List<Pur_Order_brokerDTO>>() {}.getType();
			
			List<Pur_Order_brokerDTO> pOrdList=new ModelMapper().map(modelList,listType);
			return pOrdList;
	 }
	 
	 @Autowired
	 Pur_Order_Trans_InfoRepository pur_Order_Trans_InfoRepository;
	 
	 public Pur_Order_Trans_InfoDTO getPurOrdTrans(String pur_order_no)
	 {
		 Pur_Order_Trans_Info modelList=pur_Order_Trans_InfoRepository.getPurOrdTrans(pur_order_no);
		 Type listType = new TypeToken<Pur_Order_Trans_InfoDTO>() {}.getType();

		 Pur_Order_Trans_InfoDTO itemNameList= new ModelMapper().map(modelList,listType);
			
		return itemNameList;
	 }
	 
	 
	 
	 public List<Pur_OrderDTO> getPurOrdDDCList(String pur_order_no)
		{
			List<Pur_Order> modelList =pur_OrderRepository.getPurOrdDDCList(pur_order_no);
			
			Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
			List<Pur_OrderDTO> itemNameList=new ModelMapper().map(modelList,listType);
			
			return itemNameList;
		}
	 
	 
	 
	 	public List<Pur_OrderDTO> getPurOrdAllList()
		{
			List<Pur_Order> modelList =pur_OrderRepository.getPurOrdAllList();
			
			Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
			List<Pur_OrderDTO> itemNameList=new ModelMapper().map(modelList,listType);
			
			return itemNameList;
		}
	 	
	 	public List<Map<String, Object>> getPurOrdTransChgsDynList(String orderid)
		{
	 		 List<Map<String, Object>> transchgdynlist =new ArrayList<Map<String,Object>>();
			 
	 		transchgdynlist.addAll(pur_Order_Trans_Chgs_dynRepository.getPurOrderTransChgsdynList(orderid));
			
			return transchgdynlist;
		}
	 	
	 	public List<Map<String, Object>> getPurOrdStoreDynList(String orderid)
		{
			return pur_order_store_chgsRepository.getPurOrdStoreDynList(orderid);
		}
	 	
	 	public Pur_OrderDTO getPurOrdDetails(String orderid)
	 	{
			Pur_Order modelList =pur_OrderRepository.getPurOrdDetails(orderid);
			Type listType=new TypeToken<Pur_OrderDTO>() {}.getType();
			Pur_OrderDTO ordDtls=new ModelMapper().map(modelList,listType);
			return ordDtls;
		}
	 	
	 	/*public List<Pur_Order_Item_DetailsDTO> getpurorderstorepurchase(String orderid)
	 	 {
	 		 
	 		List<Pur_Order_Item_Details> modelList=pur_Order_Item_DetailsRepository.getPurOrdCNQUPList(orderid);
	 		
	 		
	 		modelList.forEach((e->{
				e.setFinal_mat_wt(Double.parseDouble(pur_OrderRepository.pur_order_store_item_view(e.getPur_orderid(),e.getItem_code(),e.getPacking_item(),e.getClassified_item_name())));
			}));
	 		
	 		
	 		Type listType=new TypeToken<List<Pur_Order_Item_DetailsDTO>>() {}.getType();
			List<Pur_Order_Item_DetailsDTO> purIndList=new ModelMapper().map(modelList,listType);
			return purIndList;
	 	 }
	 	 */
	 	 public List<Map<String, Object>> getpurorderstorepurchase(String orderid)
	 	 {
	 		 return pur_Order_Item_DetailsRepository.getpurorderstorepurchase(orderid);
	 	 }
	 	
	 	 /*public List<Pur_Order_Item_DetailsDTO> getpurorderpacking(String orderid)
	 	 {
            List<Pur_Order_Item_Details> modelList=pur_Order_Item_DetailsRepository.getPurOrdCNQUPList(orderid);
            
	 		modelList.forEach((e->{
				
				e.setFinal_mat_wt(Double.parseDouble(pur_OrderRepository.pur_order_packing_item_view(e.getPur_orderid(),e.getItem_code(),e.getPacking_item(),e.getPacking_item_code(),e.getPacking_size(),e.getPacking_type(),e.getPacking_weight())));
			}));
	 		Type listType=new TypeToken<List<Pur_Order_Item_DetailsDTO>>() {}.getType();
			List<Pur_Order_Item_DetailsDTO> purIndList=new ModelMapper().map(modelList,listType);
			return purIndList;
	 		 
	 	 }*/
	 	 
	 	 public List<Map<String, Object>> getpurorderpacking(String orderid)
	 	 {
	 		 return pur_Order_Item_DetailsRepository.getpurorderpacking(orderid);
	 	 }
	 	 
		public List<Pur_Order_Item_DetailsDTO> getPurOrdCNQUPList(String ccc)
		{
			System.out.println("ccc"+ccc);
			Pur_Order purorder=pur_OrderRepository.getPurOrdDetails(ccc);
					
			String[] arrOfStr=ccc.split(",");
			
			List<Pur_Order_Item_Details> modelList=new ArrayList<Pur_Order_Item_Details>();
			
			for(int i=0;i<arrOfStr.length;i++)
			{
				modelList.addAll(pur_Order_Item_DetailsRepository.getPurOrdCNQUPList(arrOfStr[i]));
			}
				
			
			if(purorder.getPoitemnumber().compareToIgnoreCase("Yes")==0)
			{
				modelList.forEach((e->{
					//e.setFinal_mat_wt(Double.parseDouble(pur_OrderRepository.pur_order_item_view(e.getPur_orderid(),e.getItem_code())));
					e.setFinal_mat_wt(Double.parseDouble(pur_OrderRepository.pur_order_item_view(e.getPur_orderid(),e.getItem_code())));
				}));
			}
			else
			{
				modelList.forEach((e->{
					//e.setFinal_mat_wt(Double.parseDouble(pur_OrderRepository.pur_order_item_view(e.getPur_orderid(),e.getItem_code())));
					e.setFinal_mat_wt(Double.parseDouble(pur_OrderRepository.pur_order_item_view_update_new(e.getPur_orderid(),e.getItem_code(),e.getPacking_item())));
				}));
			}
			
			
			
			Type listType=new TypeToken<List<Pur_Order_Item_DetailsDTO>>() {}.getType();
			List<Pur_Order_Item_DetailsDTO> purIndList=new ModelMapper().map(modelList,listType);
			return purIndList;
		}
		
		
		
		public List<Wm_unload_advice_item_dtlsDTO> getPurOrdcheckingwAdvice(String pur_no,String unadvice_id)
		{
			List<Pur_Order> pur_list=pur_OrderRepository.getsubtype(pur_no);
			String pur_id=pur_list.get(0).getPur_orderid();
			
			Pur_Order purorder=pur_OrderRepository.getPurOrdDetails(pur_id);
			
			/*List<Pur_Order_Item_Details> modelList=pur_Order_Item_DetailsRepository.getPurOrdcheckingwAdvice(pur_id);
			modelList.forEach((e->{
				e.setMat_weight(e.getPur_orderid(),e.getItem_code());
			}));*/
			List<Wm_unload_advice_item_dtls> modelList=wm_unload_advice_item_dtlsRepository.getUnloadItemList(unadvice_id);
			
			if(purorder.getPoitemnumber().compareToIgnoreCase("Yes")==0)
			{
				modelList.forEach((e->{
						if(pur_OrderRepository.pur_order_per_item_view(pur_id,e.getItem_code()) == null)
						{
							e.setMat_wt(0.00);
						}
						else 
						{
							
							//e.setMat_wt(Double.parseDouble(pur_OrderRepository.pur_order_item_view(pur_id,e.getItem_code())));
							e.setMat_wt(Double.parseDouble(pur_OrderRepository.pur_order_per_item_view(pur_id,e.getItem_code())));
						}
						
						
						if(pur_OrderRepository.pur_order_item_view_itemcodesinglecehck(pur_id,e.getItem_code())>0)
						{
							
						}
						else 
						{
							e.setItem_code("ItemMisMatch");
						}
						
					/*if(pur_OrderRepository.pur_order_item_view_itemcode(pur_id).compareToIgnoreCase("")==0)
					{
						e.setItem_code("ItemMisMatch");
					}
					else
					{
						e.setItem_code(pur_OrderRepository.pur_order_item_view_itemcode(pur_id));
					}
					*/
					
				}));
			}
			else
			{
				modelList.forEach((e->{
					//e.setMat_wt(Double.parseDouble(pur_OrderRepository.pur_order_item_view_update(pur_id)));
					//System.out.println("chech weight::"+pur_OrderRepository.pur_order_item_view_update_new(pur_id,e.getItem_code(),e.getPacking()));
						if(pur_OrderRepository.pur_order_item_view_update_new(pur_id,e.getItem_code(),e.getPacking()) == null)
						{
							e.setMat_wt(0.00);
						}
						else 
						{
							e.setMat_wt(Double.parseDouble(pur_OrderRepository.pur_order_item_view_update_new(pur_id,e.getItem_code(),e.getPacking())));
						}
					//System.out.println("chech item::"+pur_OrderRepository.pur_order_item_view_itemcode(pur_id));
				
						if(pur_OrderRepository.pur_order_item_view_itemcodecehck(pur_id,e.getItem_code(),e.getPacking())>0)
						{
							
						}
						else 
						{
							e.setItem_code("ItemMisMatch");
						}
						
						
						
				/*	if(pur_OrderRepository.pur_order_item_view_itemcode(pur_id).compareToIgnoreCase("")==0)
					{
						e.setItem_code("ItemMisMatch");
					}
					else
					{
						e.setItem_code(pur_OrderRepository.pur_order_item_view_itemcode(pur_id));
					}
					*/
				}));
			}
			
			
			Type listType=new TypeToken<List<Wm_unload_advice_item_dtlsDTO>>() {}.getType();
			
			List<Wm_unload_advice_item_dtlsDTO> unlaodAdv=new ModelMapper().map(modelList,listType);
			
			return unlaodAdv;
			
		}
		
		public List<Pur_Order_Item_DetailsDTO> purOrdItemRetriveList(String code)
		{
			List<Pur_Order_Item_Details> modelList=pur_Order_Item_DetailsRepository.purOrdItemRetriveList(code);
			
			Type listType=new TypeToken<List<Pur_Order_Item_DetailsDTO>>() {}.getType();
			
			List<Pur_Order_Item_DetailsDTO> purOrdI=new ModelMapper().map(modelList,listType);
			
			return purOrdI;
		}
		
		
		public List<Pur_Order_app_chgsDTO> getPurOrdAppChgs(String orderid)
		{
			System.out.println("orderid: "+orderid);
			List<Pur_Order_app_chgs> modelList=pur_Order_app_chgsRepository.getPurOrdAppChgs(orderid);
			
			List<Pur_Order_app_chgs> allData = modelList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Pur_Order_app_chgs::getId))
					  .collect(Collectors.toList());
			
			Type listType=new TypeToken<List<Pur_Order_app_chgsDTO>>() {}.getType();
			
			List<Pur_Order_app_chgsDTO> purAppChgs=new ModelMapper().map(allData,listType);
			
			return purAppChgs;
		}
		
		public List<Pur_Order_Termination_dynDTO> getPurOrdTermList(String orderid)
		{
			List<Pur_Order_Termination_dyn> modelList=new ArrayList<Pur_Order_Termination_dyn>();
			
			modelList.addAll(pur_Order_Termination_dynRepository.getPurOrdTermList(orderid));
				
			Type listType=new TypeToken<List<Pur_Order_Termination_dynDTO>>() {}.getType();
			
			List<Pur_Order_Termination_dynDTO> purAppChgs=new ModelMapper().map(modelList,listType);
			
			return purAppChgs;
		}
		
		 @Autowired
		 Pur_Order_BPDetailsRepository pur_Order_BPDetailsRepository;
		 
		 public Pur_Order_BPDetailsDTO purOrdBPDRetriveList(String code)
		 {
			 Pur_Order_BPDetails modelList=pur_Order_BPDetailsRepository.purOrdBPDRetriveList(code);
			 Type listType = new TypeToken<Pur_Order_BPDetailsDTO>() {}.getType();

			 Pur_Order_BPDetailsDTO purOrdBP= new ModelMapper().map(modelList,listType);
				
			return purOrdBP;
		 }
		 
		 
		 @Autowired
		 Pur_Order_TerminationRepository pur_Order_TerminationRepository;
		 
		 public Pur_Order_TerminationDTO purOrdTerminateRetriveList(String code)
		 {
			 Pur_Order_Termination modelList=pur_Order_TerminationRepository.purOrdTerminateRetriveList(code);
			 Type listType = new TypeToken<Pur_Order_TerminationDTO>() {}.getType();

			 Pur_Order_TerminationDTO purOrdTerminate= new ModelMapper().map(modelList,listType);
				
			return purOrdTerminate;
		 }
		 
		 public List<Pur_Order_DocDTO> purOrdDocRetriveList(String code)
			{
				List<Pur_Order_Doc> modelList=pur_Order_DocRepository.purOrdDocRetriveList(code);
				
				Type listType=new TypeToken<List<Pur_Order_DocDTO>>() {}.getType();
				
				List<Pur_Order_DocDTO> purOrdDoc=new ModelMapper().map(modelList,listType);
				
				return purOrdDoc;
			}
		 
		 @Autowired
		 Pur_Order_Terms_ConRepository pur_Order_Terms_ConRepository;
		 
		 public Pur_Order_Terms_ConDTO purOrdTransConRetriveList(String code)
		 {
			 Pur_Order_Terms_Con modelList=pur_Order_Terms_ConRepository.purOrdTransConRetriveList(code);
			 Type listType = new TypeToken<Pur_Order_Terms_ConDTO>() {}.getType();

			 Pur_Order_Terms_ConDTO purOrdTransCon= new ModelMapper().map(modelList,listType);
				
			return purOrdTransCon;
		 }
		 
		public List<Pur_OrderDTO> getPurOrdThruSuppList(String suppid)
		{
			List<Pur_Order> modelList =pur_OrderRepository.getPurOrdThruSuppList(suppid);
			
			Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
			
			List<Pur_OrderDTO> ordList=new ModelMapper().map(modelList,listType);
			
			return ordList;
		}
		
		public List<Pur_OrderDTO> getPurOrdAdvThruSupp(String suppid,String businessunit)
		{
			//System.out.println("getPurOrdAdvThruSupp..............."+suppid+"businessunit...."+businessunit);
			List<Pur_Order> modelList =pur_OrderRepository.getPurOrdAdvThruSupp(suppid,businessunit);
			
			//changes on 17-04-2022
			
			modelList.forEach((e->{
				
				e.setTotal_qty(Double.parseDouble(pur_OrderRepository.pur_ord_view(e.getPur_orderid())));
				//System.out.println("e.getPur_orderid..............."+e.getTotal_qty() +" / " +Double.parseDouble(pur_OrderRepository.pur_ord_view(e.getPur_orderid())));
			}));
			
			
			Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
			
			List<Pur_OrderDTO> ordList=new ModelMapper().map(modelList,listType);
			
			return ordList;
		}
		
		public List<Pur_OrderDTO> getGrnThroughPurOrd(String businessunit,String pur_type)
		{
			//System.out.println("businessunit...."+businessunit+"pur_type:"+pur_type);
			List<Pur_Order> modelList =pur_OrderRepository.getGrnThroughPurOrd(businessunit,pur_type);
			
			Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
			
			List<Pur_OrderDTO> ordList=new ModelMapper().map(modelList,listType);
			
			return ordList;
		}
		
		 public List<Pur_OrderDTO> getGrnThroughPurOrdstore(String businessunit,String pur_type)
		 {
			// System.out.println("businessunit...."+businessunit+"pur_type:"+pur_type);
				List<Pur_Order> modelList =pur_OrderRepository.getGrnThroughPurOrdstore(businessunit,pur_type);
				
				Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
				
				List<Pur_OrderDTO> ordList=new ModelMapper().map(modelList,listType);
				
				return ordList;
		 }
		 
		 public List<Pur_OrderDTO> getGrnThroughPurOrdpacking(String businessunit,String pur_type)
		 {
	List<Pur_Order> modelList =pur_OrderRepository.getGrnThroughPurOrdpacking(businessunit,pur_type);
				
				Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
				
				List<Pur_OrderDTO> ordList=new ModelMapper().map(modelList,listType);
				
				return ordList;
		 }
	
		public List<Pur_OrderDTO> getPurOrdRtnApp(String bunit,String supplier,String tdate,String company,String finyear)
		{
			List<Pur_Order> modelList =pur_OrderRepository.getPurOrdRtnApp(bunit,supplier,tdate,company,finyear);
			
			Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
			
			List<Pur_OrderDTO> ordList=new ModelMapper().map(modelList,listType);
			
			return ordList;
		}
		
		public List<Pur_OrderDTO> getPurOrdThruSuppAdvReq(String suppid)
		{
			//System.out.println("hallu "+suppid);
			List<Pur_Order> modelList =pur_OrderRepository.getPurOrdThruSuppAdvReq(suppid);
			
			Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
			
			List<Pur_OrderDTO> ordAdvList=new ModelMapper().map(modelList,listType);
			
			return ordAdvList;
		}
		
		public Pur_Order_Item_DetailsDTO getPurOrdItemDtls(String orderid,String itemcode)
		{
			Pur_Order_Item_Details modelList=pur_Order_Item_DetailsRepository.getPurOrdItemDtls(orderid,itemcode);
			Type listType=new TypeToken<Pur_Order_Item_DetailsDTO>() {}.getType();
			
			Pur_Order_Item_DetailsDTO ordItemDetails=new ModelMapper().map(modelList,listType);
			return ordItemDetails;
		}
		
		
		public Pur_Order_Item_DetailsDTO getPurOrdItemDtlsnew(String orderid,String itemcode,String packing)
		{
			Pur_Order_Item_Details modelList=pur_Order_Item_DetailsRepository.getPurOrdItemDtlsnew(orderid,itemcode,packing);
			Type listType=new TypeToken<Pur_Order_Item_DetailsDTO>() {}.getType();
			
			Pur_Order_Item_DetailsDTO ordItemDetails=new ModelMapper().map(modelList,listType);
			return ordItemDetails;
		}
		
		public Pur_Order_Item_DetailsDTO getPurOrdItemDtlsnewpoitemnumber(String orderid)
		{
			Pur_Order_Item_Details modelList=pur_Order_Item_DetailsRepository.getPurOrdItemDtlsnewpoitemnumber(orderid);
			Type listType=new TypeToken<Pur_Order_Item_DetailsDTO>() {}.getType();
			
			Pur_Order_Item_DetailsDTO ordItemDetails=new ModelMapper().map(modelList,listType);
			return ordItemDetails;
		}
		
		public List<Pur_OrderDTO> getPurOrdreceipt_criteria(String orderid)
		{
			System.out.println("orderid :: "+ orderid);
			//List<Pur_Order> modelList =pur_OrderRepository.getPurOrdreceipt_criteria(orderid);
			
			List<Pur_Order> modelList=new ArrayList<Pur_Order>();
			modelList.addAll(pur_OrderRepository.findPurOrders(orderid));
			
			
			System.out.println("modelList 123"+ modelList.get(0).getReceipt_criteria());
			Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
			
			List<Pur_OrderDTO> ordAdvList=new ModelMapper().map(modelList,listType);
			
			return ordAdvList;
		}
		
		public Pur_Order_Item_DetailsDTO getPurOrderItemWeightViewList(String orderid,String itemcode)
		{
			
			Pur_Order_Item_Details modelList=pur_OrderRepository.getPurOrderItemWeightViewList(orderid,itemcode);
			System.out.println(orderid);
			modelList.setMat_weight(pur_OrderRepository.getPurOrderItemWeightView(orderid,itemcode));
			modelList.setPurpose(String.valueOf(pur_OrderRepository.checkifexistunloadadvice(orderid)));
			
			
			Type listType=new TypeToken<Pur_Order_Item_DetailsDTO>() {}.getType();
			Pur_Order_Item_DetailsDTO ordItemDetails=new ModelMapper().map(modelList,listType);
			return ordItemDetails;
		}
		
		 public Pur_Order_Item_DetailsDTO getPurchaseOrdernetWeightnew(String orderid,String itemcode,String packing_item) 
		 {
				Pur_Order_Item_Details modelList=pur_OrderRepository.getPurOrderItemWeightViewListnew(orderid,itemcode,packing_item);
				System.out.println(orderid);
				modelList.setMat_weight(pur_OrderRepository.getPurOrderItemWeightView(orderid,itemcode));
				modelList.setPurpose(String.valueOf(pur_OrderRepository.checkifexistunloadadvice(orderid)));
				
				
				Type listType=new TypeToken<Pur_Order_Item_DetailsDTO>() {}.getType();
				Pur_Order_Item_DetailsDTO ordItemDetails=new ModelMapper().map(modelList,listType);
				return ordItemDetails;
		 }
		
		 public Pur_OrderDTO wnUnAdvicebalancedtotalquantity(String orderid)
	 	 {
				Pur_Order modelList =pur_OrderRepository.getPurOrdDetails(orderid);
				
				modelList.setTotal_qty(Double.parseDouble(pur_OrderRepository.pur_ord_view(modelList.getPur_orderid())));
				
				Type listType=new TypeToken<Pur_OrderDTO>() {}.getType();
				Pur_OrderDTO ordDtls=new ModelMapper().map(modelList,listType);
				return ordDtls;
		 }
		 
	 public StatusDTO checkPurchaseOrderUsage(String code)
	 	{
	 		String result=pur_OrderRepository.checkPurchaseOrderUsage(code);
			
			Type listType = new TypeToken<StatusDTO>() {}.getType();
			StatusDTO statusDTO = new ModelMapper().map(result,listType);
			statusDTO.setStatus(result);
			
			return statusDTO;
	 	}
		 
	@Transactional
	public Pur_Order deletePurchaseOrder(Pur_Order pur_Order,long id)
	{
		Optional<Pur_Order> op = pur_OrderRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Pur_Order pur_ord=op.get();
		boolean abc=true;
		pur_ord.setPur_orderid(op.get().getPur_orderid());
		pur_ord.setInserted_by(op.get().getInserted_by());
		pur_ord.setInserted_on(op.get().getInserted_on());
		pur_ord.setUpdated_by(op.get().getUpdated_by());
		pur_ord.setUpdated_on(op.get().getUpdated_on());
		pur_ord.setDeleted_by(userRepository.getUserDetails(pur_Order.getUsername()).getName());
		pur_ord.setDeleted_on(ldt);
		pur_ord.setModified_type("DELETED");
		
		//System.out.println("chk1");
		if(op.isPresent()) {
			pur_ord.setId(id);
		}
		
		//System.out.println("chk2");
		pur_Order_Item_DetailsRepository.pur_Order_Item_DetailsUpdate(op.get().getPur_orderid());
		pur_Order_Terms_ConRepository.pur_Order_Terms_ConUpdate(op.get().getPur_orderid());
		pur_Order_app_chgsRepository.pur_Order_app_chgsUpdate(op.get().getPur_orderid());
		pur_Order_BPDetailsRepository.pur_Order_BPDetailsUpdate(op.get().getPur_orderid());
		pur_Order_TerminationRepository.pur_Order_TerminationUpdate(op.get().getPur_orderid());
		pur_Order_Termination_dynRepository.pur_Order_Termination_dynUpdate(op.get().getPur_orderid());
		pur_Order_Trans_InfoRepository.pur_Order_Trans_InfoUpdate(op.get().getPur_orderid());
		pur_Order_brokerRepository.pur_Order_brokerUpdate(op.get().getPur_orderid());
		pur_Order_DocRepository.pur_Order_DocUpdate(op.get().getPur_orderid());
		pur_Order_Trans_Chgs_dynRepository.purOrderTransChgsdynUpdate(op.get().getPur_orderid());
		pur_order_terms_conditionsRepository.purOrderTermsCondDynUpdate(op.get().getPur_orderid());
		pur_order_store_chgsRepository.pur_Order_storeDelete(op.get().getPur_orderid());
		
		
		//System.out.println("chk3");
		return pur_OrderRepository.save(pur_ord);
	}
	
	
	public Boolean checkpurchasebuunit(String parentid) 
	{
		boolean result=false;
		result=companyBusinessUnitMasterRepository.checkBussinessUnitPurchaseOrderUsage(parentid);
		return result;
	} 
	
	public Pur_Order retrivePurchaseGoodReceiptPopup(Long id)
	{
		Optional<Pur_good_receipt> PGR=pur_good_receiptRepository.findById(id);
		
		 Pur_Order pono = pur_OrderRepository.getPurOrdDetails(PGR.get().getReferance_id());
			
		 pono.setApproved(PGR.get().getGrn_id());
			
		return pono;
	}
	
	 public List<Pur_OrderDTO> getPurchaseOrderList(String currDate,String finyear)
		{
		 	List<Pur_Order> modelList =new ArrayList<Pur_Order>();
		 	String tablename="pur_order",party_name="supplier_name",order_no="pur_order_no",order_date="ord_date";
			String orderno="",supplier_name1="";
			modelList.addAll(pur_OrderRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,currDate,currDate,supplier_name1,finyear));
			Type listType = new TypeToken<List<Pur_OrderDTO>>() {}.getType();
			List<Pur_OrderDTO> advList = new ModelMapper().map(modelList,listType);
			
			return advList;
		}
	 
	
	 public List<Map<String,Object>> getPurchaseOrderListFastApi(String currDate,String finyear)
		{
		 	List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 	
			//modelList.addAll(pur_OrderRepository.getPurchaseOrderListFastApi(currDate,finyear));
		 	
		 	modelList.addAll(pur_OrderRepository.getPurchaseOrderListFastApiNew(currDate));
			return modelList;
		}
	 
	 public List<Map<String, Object>> getPurchaseordermiscreport(String business_unit,String catagory,String fromdate,String todate,String supplier_name,String ven_code_name,String statustype)
	 {
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 
		 if(catagory.compareToIgnoreCase("Brokerwise") == 0) 
		 {
			
			 String multibroker[]=ven_code_name.split(",");
	 			ArrayList<String> broker=new ArrayList<String>();
	 			for(int i=0;i<multibroker.length;i++)
	 			{
	 				broker.add(multibroker[i]);
	 			}
	 		// System.out.println("broker::"+broker);
			 if(statustype.compareTo("Open")==0)
			 {
				 //System.out.println("Open::");
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportbrokerOpen(business_unit,fromdate,todate,broker));
			 }
			 else if(statustype.compareTo("Closed")==0)
			 {
				 //System.out.println("Closed::");
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportbrokerClose(business_unit,fromdate,todate,broker));
			 }
			 else if(statustype.compareTo("Terminated")==0)
			 {
				 //System.out.println("Terminated::");
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportbrokerTerminate(business_unit,fromdate,todate,broker));
			 }
			 else
			 {
				 //System.out.println("All::");
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportbroker(business_unit,fromdate,todate,broker));
			 }
		 }
		 else if(catagory.compareToIgnoreCase("Partywise") == 0) 
		 {
			 String multiSupplier[]=supplier_name.split(",");
	 			ArrayList<String> supplier=new ArrayList<String>();
	 			for(int i=0;i<multiSupplier.length;i++)
	 			{
	 				supplier.add(multiSupplier[i]);
	 			}
			 //System.out.println("statustype::"+statustype);
			 if(statustype.compareTo("Open")==0)
			 {
				 ///System.out.println("Open::");
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportpartyOpen(business_unit,fromdate,todate,supplier));
			 }
			 else if(statustype.compareTo("Closed")==0)
			 {
				 //System.out.println("Closed::");
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportpartyClose(business_unit,fromdate,todate,supplier));
			 }
			 else if(statustype.compareTo("Terminated")==0)
			 {
				 //System.out.println("Terminated::");
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportpartyTerminate(business_unit,fromdate,todate,supplier));
			 }
			 else
			 {
				 System.out.println("All::");
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportparty(business_unit,fromdate,todate,supplier));
			 }
		 }
		 else 
		 {
			 if(statustype.compareTo("Open")==0)
			 {
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportallOpen(business_unit,fromdate,todate));
			 }
			 else if(statustype.compareTo("Closed")==0)
			 {
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportallClose(business_unit,fromdate,todate));
			 }
			 else if(statustype.compareTo("Terminated")==0)
			 {
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportallTerminate(business_unit,fromdate,todate));
			 }
			 else
			 {
				 modelList.addAll(pur_OrderRepository.getPurchaseordermiscreportall(business_unit,fromdate,todate));
			 }
		 }
		 
		 
		 return modelList;
	 }
	 public List<Map<String, Object>> getLastPOThruSupItemDtls(String supplier_name)
	 {
		 return pur_OrderRepository.getLastPOThruSupItemDtls(supplier_name);
	 }
	 
	 public List<Map<String, Object>> getLastPOThruSupPurDtls(String supplier_name)
	 {
		 return pur_OrderRepository.getLastPOThruSupPurDtls(supplier_name);
	 }
	 
	 public List<Map<String, Object>> getPendingSoudaReport(String fromdate,String todate,String catagory)
	 {
		 List<Map<String, Object>> pendingSouda =new ArrayList<Map<String,Object>>();
		 
		 if(catagory.compareToIgnoreCase("truck")==0)
		 {
			 pendingSouda.addAll(pur_OrderRepository.getPendingSoudaReport(fromdate,todate));
		 }
		 else
		 {
			 pendingSouda.addAll(pur_OrderRepository.getPendingSoudaReportPerQty(fromdate,todate));
		 }
				 
	     return pendingSouda;
	 }
	 
	 public List<Map<String, Object>> purchaseOrderSupplierNamesList(String company,String fromdate,String todate,String business_unit)
	 {
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 
		 modelList.addAll(pur_OrderRepository.purchaseOrderSupplierNamesList(company,fromdate,todate,business_unit));
		
		 return modelList;
	 }
 	
 	public List<Map<String, Object>> purchaseOrderBrokerNamesList(String company,String fromdate,String todate,String business_unit)
	 {
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 
		 modelList.addAll(pur_OrderRepository.purchaseOrderBrokerNamesList(company,fromdate,todate,business_unit));
		
		 return modelList;
	 }
 	
 	public List<Map<String, Object>> getpoitemdetailsreportold(String poid)
 	{
 		
 		System.out.println(" poid :: " + poid);
 		
 		String po_id [] = poid.split(",");
 		
 		List<Map<String, Object>> list = new ArrayList<>();
 		for(int i=0;i<po_id.length;i++ ) 
 		{
 			System.out.println(""+ po_id[i]);
 			list.addAll(pur_Order_Item_DetailsRepository.getpoitemdetailsreport(po_id[i]));
 		}
 		System.out.println(" size "+ list.size());
 		return  list;
 	}
 	
 	public List<Map<String, Object>> getpoitemdetailsreport(String poid)
 	{
 		
 		ArrayList<String> list = new ArrayList<>(Arrays.asList(poid.split(",")));
		//list.forEach(System.out::println);
 		
 		return pur_Order_Item_DetailsRepository.getpoitemdetailsreportnew(list);
 	}
 	
 	public List<Map<String, Object>> getPurOrderReport(String fromdate,String todate)
 	{
 		
 		return pur_OrderRepository.PurOrderReport(fromdate,todate);
 	}
 	
 	
 	
 	public List<Map<String, Object>> getExecutionpo(String poid)
 	{
        System.out.println(" poid :: " + poid);
 		
 		String po_id [] = poid.split(",");
 		
 		List<Map<String, Object>> list = new ArrayList<>();
 		
 		for(int i=0;i<po_id.length;i++ ) 
 		{
 			System.out.println(""+ po_id[i]);
 			list.addAll(pur_Order_Item_DetailsRepository.getExecutionpo(po_id[i]));
 		}
 		System.out.println(" size "+ list.size());
 		return  list;
 	}
 	
 	public List<Map<String, Object>> transporterNameChgsPurList(String ordid)
	 {
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 
		 modelList.addAll(pur_Order_Trans_Chgs_dynRepository.transporterNameChgsPurList(ordid));
		
		 return modelList;
	 }
 	
 	public Map<String,Object> getPurOrderTransChgsData(String referance_id, String grn_id)
	{
		String transCode=pur_good_receiptRepository.getTransCode(grn_id);
		return pur_OrderRepository.getPurOrderTransChgsData(referance_id,transCode);
	}
	 
 	
 	public Pur_Order_Trans_Chgs_dyn  getpurchaseordercharges(String transporter_Id,String referance_id) 
 	{
 		return pur_Order_Trans_Chgs_dynRepository.getpurchaseordercharges(transporter_Id,referance_id);
 	}
 	
 	public List<Map<String,Object>> getPurOrderDetailsThroughOrderId(String orderid) 
 	{
 		return pur_OrderRepository.getPurOrderDetailsThroughOrderId(orderid);
 	}
 	
 	public List<Map<String,Object>> getTermsConditionsDtlsList() 
 	{
 		return pur_order_terms_conditionsRepository.getTermsConditionsDtlsList();
 	}

 	public List<Map<String,Object>> getPurOrdTermsCondDynList(String code)
 	{
 		return pur_order_terms_conditionsRepository.getPurOrdTermsCondDynList(code);
 	}
  
 	public List<Map<String,Object>> purOrdTramsCondition(String orderid) 
 	{
 		return pur_order_terms_conditionsRepository.purOrdTramsCondition(orderid);
 	}
 	
 	public List<Map<String,Object>> purOrdItemwtHSNRetriveList(String code)
 	{
 		return pur_Order_Item_DetailsRepository.purOrdItemwtHSNRetriveList(code);
 	}
 	
 	/*public List<Map<String,Object>> getPurOrdListOnlyStorePurchase(String supplier,String businessunit)
	{
		return pur_OrderRepository.getPurOrdListOnlyStorePurchase(supplier,businessunit);
	}*/
	
 	public  List<Pur_OrderDTO> getPurOrdListOnlyStorePurchase(String supplier,String businessunit)
	{
		List<Pur_Order> modelList =pur_OrderRepository.getPurOrdListOnlyStorePurchase(supplier,businessunit);
		modelList.forEach((e->{
			
			e.setTotal_qty(Double.parseDouble(pur_OrderRepository.pur_ord_view(e.getPur_orderid())));
		}));
		Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
		List<Pur_OrderDTO> ordList=new ModelMapper().map(modelList,listType);
		return ordList;
		
	}
 	
 	public  List<Pur_OrderDTO> getPurOrdListOnlyPacking(String supplier,String businessunit)
	{
 		System.out.println(supplier+" tuh " + businessunit);
		List<Pur_Order> modelList =pur_OrderRepository.getPurOrdListOnlyPacking(supplier,businessunit);
		modelList.forEach((e->{
			
			e.setTotal_qty(Double.parseDouble(pur_OrderRepository.pur_ord_view(e.getPur_orderid())));
		}));
		Type listType=new TypeToken<List<Pur_OrderDTO>>() {}.getType();
		List<Pur_OrderDTO> ordList=new ModelMapper().map(modelList,listType);
		return ordList;
		
	}
 	
 	public List<Map<String,Object>> getJwPo()
 	{
 		return pur_OrderRepository.getJwPo();
 	}
 	
 	 public Map<String,Object> getStoreChargesPo(String grnid,String referance_type)
 	 {
 		 Map<String,Object> storedata;
 		 
 		 if(referance_type.compareToIgnoreCase("PURCHASE ORDER")==0) {
 			storedata=pur_OrderRepository.getStoreChargesdirectPo(grnid);
 		 }
 		 else 
 		 {
 			storedata=pur_OrderRepository.getStoreChargesPo(grnid);
 		 }
 		 
 		 return storedata; 
 	 }
 	public List<Map<String,Object>> getPurOrdItemDtlsMultipleItemGRN(String orderid,String itemcode)
	{
		return pur_Order_Item_DetailsRepository.getPurOrdItemDtlsMultipleItemGRN(orderid,itemcode);
	}
 	
 	public List<Map<String,Object>> getpssd_packing_item_qtymultiplepopup(String unloadadvice)
	{
		System.out.println("Check unloadid: "+unloadadvice);
		return pur_OrderRepository.getpssd_packing_item_qtymultiplepopup(unloadadvice);
	}
 	
 	public List<Map<String,Object>> getPurOrdreceipt_criteriaNew(String orderid)
	{
		System.out.println("orderid :: "+ orderid);
		List<Map<String,Object>> modelList=new ArrayList<Map<String,Object>>();
		modelList.addAll(pur_OrderRepository.findPurOrdersFast(orderid));
		return modelList;
	}
}
