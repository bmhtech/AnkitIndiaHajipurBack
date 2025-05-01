package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Exporttotally.Tallyrequest_PurchaseBill;
import com.AnkitIndia.Exporttotally.Tallyrequest_PurchaseBill_Store;
import com.AnkitIndia.Exporttotally.Tallyrequest_Suppliermaster;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Charge_Master;
import com.AnkitIndia.jwtauthentication.model.Charge_Masterdtls;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_pur_acc;
import com.AnkitIndia.jwtauthentication.model.Item_group_master_sales_acc;
import com.AnkitIndia.jwtauthentication.model.Item_groupwise_hsnsumm;
import com.AnkitIndia.jwtauthentication.model.Item_groupwise_summ;
import com.AnkitIndia.jwtauthentication.model.Item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Ledgermaster;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill_Account_Info;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill_Bp_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill_Broker_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill_Docs;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill_Item_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill_Musterroll_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill_Tax_Info;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Doc;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Trans_Info;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_app_chgs;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_broker;
import com.AnkitIndia.jwtauthentication.model.Pur_Quotation;
import com.AnkitIndia.jwtauthentication.model.Pur_bill_store_chgs;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Purchase_item_groupwise_hsnsumm;
import com.AnkitIndia.jwtauthentication.model.Purchase_item_groupwise_summ;
import com.AnkitIndia.jwtauthentication.model.Purchase_item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Sales_transport;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_accont;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_addr_dtls;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_statutory;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.repository.Charge_MasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_group_master_pur_accRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_BillRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Bill_Account_InfoRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Bill_Bp_DetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Bill_Broker_DetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Bill_DocsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Bill_Item_DetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Bill_Musterroll_DetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Bill_Tax_InfoRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Bill_app_chgsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_bill_store_chgsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receiptRepository;
import com.AnkitIndia.jwtauthentication.repository.Purchase_item_groupwise_hsnsummRepository;
import com.AnkitIndia.jwtauthentication.repository.Purchase_item_groupwise_summRepository;
import com.AnkitIndia.jwtauthentication.repository.Purchase_item_groupwise_taxsummRepository;
import com.AnkitIndia.jwtauthentication.repository.Store_inv_charge_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_addressRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_bill_addrRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partner_statutoryRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_BillDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Account_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Bp_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Broker_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Item_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Musterroll_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Tax_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receiptDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.PurchasebillviewchargeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Repository
public class Pur_BillService_Imp implements Pur_BillService{
	
	@Autowired
	Pur_BillRepository pur_BillRepository;

	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Item_type_masterRepository item_type_masterRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Pur_good_receiptRepository pur_good_receiptRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	@Autowired
	Pur_OrderRepository pur_OrderRepository;
	
	@Autowired
	Charge_MasterRepository charge_MasterRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;

	@Autowired
	Pur_Bill_Item_DetailsRepository pur_Bill_Item_DetailsRepository;
	
	@Autowired
	Pur_Bill_Broker_DetailsRepository pur_Bill_Broker_DetailsRepository;
	
	@Autowired
	Pur_Bill_DocsRepository pur_Bill_DocsRepository;
	
	@Autowired
	Pur_Bill_app_chgsRepository pur_Bill_app_chgsRepository;
	
	@Autowired
	Pur_Bill_Musterroll_DetailsRepository pur_Bill_Musterroll_DetailsRepository;
	
	
	@Autowired
	Item_group_master_pur_accRepository item_group_master_pur_accRepository;
	
	@Autowired
	Supp_bussiness_partner_bill_addrRepository supp_bussiness_partner_bill_addrRepository;
	
	@Autowired
	Purchase_item_groupwise_summRepository purchase_item_groupwise_summRepository;
	
	@Autowired
	Purchase_item_groupwise_taxsummRepository purchase_item_groupwise_taxsummRepository;
	
	@Autowired
	Purchase_item_groupwise_hsnsummRepository purchase_item_groupwise_hsnsummRepository;
	
	@Autowired
	Supp_bussiness_partner_statutoryRepository supp_bussiness_partner_statutoryRepository;
	
	@Autowired
	Supp_bussiness_partner_addressRepository supp_bussiness_partner_addressRepository;
	
	@Autowired
	Charge_MasterRepository charge_masterRepository;
	
	@Autowired
	Store_inv_charge_masterRepository store_inv_charge_masterRepository;
	
	@Autowired
	Pur_bill_store_chgsRepository pur_bill_store_chgsRepository;
	
	public SequenceIdDTO getPBSequenceId(String orderdate,boolean itype,String ptype,String psubtype)
	{

	
	//new	
		long slno=0;
		if(pur_BillRepository.countId() != null ) {
			slno=Long.parseLong(pur_BillRepository.countId());
		}
		String prefix="PB";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		;
		long nslno=0;String itypep="",tprefix="PB";
		String tsno=pur_BillRepository.countPBOrder(orderdate,itype,ptype,psubtype);
		System.out.println(orderdate +" // " + itype + " // " + ptype + " // " + psubtype);
		//pur_BillRepository.countPBOrder(orderdate,itype,ptype,psubtype);
		
		if(itype == true)
		{itypep="MAT";}
		else 
		{itypep="SER";}
		System.out.println("tsno "+ tsno);
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		
		String newprefix=null;
		if(ptype.compareToIgnoreCase("ITMT00001")==0) 
		{
			newprefix="RAW";
		}
		else if(ptype.compareToIgnoreCase("ITMT00002")==0) 
		{
			newprefix="PAC";
		}
		else if(ptype.compareToIgnoreCase("ITMT00003")==0) 
		{
			newprefix="FIN";
		}
		else if(ptype.compareToIgnoreCase("ITMFIXED02")==0) 
		{
			newprefix="SCR";
		}
		else if(ptype.compareToIgnoreCase("ITMT00004")==0) 
		{
			newprefix="STR";
		}
		else if(ptype.compareToIgnoreCase("ITMT00005")==0) 
		{
			newprefix="AST";
		}
		else if(ptype.compareToIgnoreCase("ITMT00007")==0) 
		{
			newprefix="CWI";
		}
		else if(ptype.compareToIgnoreCase("ITMT00008")==0) 
		{
			newprefix="PRW";
		}
		else if(ptype.compareToIgnoreCase("ITMT00009")==0) 
		{
			newprefix="JOB";
		}
		else if(ptype.compareToIgnoreCase("ITMT00010")==0) 
		{
			newprefix="TRS";
		}
		
		String date[] = orderdate.split("-");
		
		tprefix=tprefix+"-"+newprefix+"-"+psubtype.substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.out.println( gen_sno+" // "+tprefix+ " / / "+nslno);
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		
		//ends
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_tslno,listType);
		
		genCode.setSequenceid(gen_tslno);
		
		return genCode;
	
	}
	
	@Transactional
	public Pur_Bill save(Pur_Bill pur_Bill)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(pur_BillRepository.countId() != null ) {
			slno=Long.parseLong(pur_BillRepository.countId());
		}
		String prefix="PB";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		System.err.println("First:>>"+pur_Bill.getPur_bill_no());
		long nslno=0;String itypep="",tprefix="PB";
		String tsno=pur_BillRepository.countPBOrder(pur_Bill.getBill_date(),pur_Bill.isItem_type(),pur_Bill.getPurchase_type(),pur_Bill.getPurchase_subtype());
		if(pur_Bill.isItem_type() == true) {itypep="MAT";}else {itypep="SER";}
		
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String newprefix=null;
		if(pur_Bill.getPurchase_type().compareTo("ITMT00001")==0) 
		{
			newprefix="RAW";
		}
		else if(pur_Bill.getPurchase_type().compareTo("ITMT00002")==0) 
		{
			newprefix="PAC";
		}
		else if(pur_Bill.getPurchase_type().compareTo("ITMT00003")==0) 
		{
			newprefix="FIN";
		}
		else if(pur_Bill.getPurchase_type().compareTo("ITMFIXED02")==0) 
		{
			newprefix="SCR";
		}
		else if(pur_Bill.getPurchase_type().compareTo("ITMT00004")==0) 
		{
			newprefix="STR";
		}
		else if(pur_Bill.getPurchase_type().compareTo("ITMT00005")==0) 
		{
			newprefix="AST";
		}
		
		else if(pur_Bill.getPurchase_type().compareTo("ITMT00007")==0) 
		{
			newprefix="CWI";
		}
		else if(pur_Bill.getPurchase_type().compareTo("ITMT00008")==0) 
		{
			newprefix="PRW";
		}
		else if(pur_Bill.getPurchase_type().compareToIgnoreCase("ITMT00009")==0) 
		{
			newprefix="JOB";
		}
		else if(pur_Bill.getPurchase_type().compareTo("ITMT00010")==0) 
		{
			newprefix="TRS";
		}
		String date[] = pur_Bill.getBill_date().split("-");
		tprefix=tprefix+"-"+newprefix+"-"+pur_Bill.getPurchase_subtype().substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		pur_Bill.setPur_bill_no(gen_tslno);
		System.err.println("Last:>>>"+pur_Bill.getPur_bill_no());
		/*End checking transaction no for unique */
		
		pur_Bill.setPur_bill_id(gen_sno);
		pur_Bill.setModified_type("INSERTED");
		pur_Bill.setBillstatus("No");
		pur_Bill.setReturn_apv_status("No");
		pur_Bill.setPurreturnid("NA");
		pur_Bill.setInserted_by(userRepository.getUserDetails(pur_Bill.getUsername()).getName());
		pur_Bill.setInserted_on(ldt);
		pur_Bill.setUpdated_by("NA");
		pur_Bill.setUpdated_on(ldt);
		pur_Bill.setDeleted_by("NA");
		pur_Bill.setDeleted_on(ldt);
		pur_Bill.setBilldate(pur_Bill.getBill_date());
		pur_Bill.setPurbillno(pur_Bill.getPur_bill_no());
		pur_Bill.setState(supp_bussiness_partner_bill_addrRepository.getSuppBPBillAddr(pur_Bill.getSupplier_name()).getState());
		
	if(Utility.isNullOrEmpty(pur_Bill.getApp_chgs_id())) {
			
		}else {pur_Bill.setApp_chgs_id("0");}
	
		//pur_Bill.setAdviceno(adviceno);
		//here advice no added in PUR BILL through GRN
		
		//checkopengrn

	
	    String grnstatus=pur_good_receiptRepository.getPurGoodRcptDtls(pur_Bill.getReferance_id()).getReferance_type();
		
		if(grnstatus.compareToIgnoreCase("OPEN GRN")==0) 
		{
			pur_Bill.setAdviceno("OPEN GRN");
		}
		else if(grnstatus.compareToIgnoreCase("PURCHASE ORDER")==0) 
		{
			pur_Bill.setAdviceno("PURCHASE ORDER");
		}
		else 
		{
			

			String adviceid=pur_good_receiptRepository.getPurGoodRcptDtls(pur_Bill.getReferance_id()).getReferance_id();
			
			String adviceno=wm_unload_adviceRepository.getUnloadAdviceData(adviceid).getUnadviceno();
					
			pur_Bill.setAdviceno(adviceno);
		}
		
		//here ends advice no added in PUR BILL through GRN
		
		pur_good_receiptRepository.getrevertbill(pur_Bill.getReferance_id());
		
		if(Utility.isNullOrEmpty(pur_Bill.getItem_total_gl_ac())) {
			
		}else {pur_Bill.setItem_total_gl_ac("0");}
	
		
		if(pur_Bill.getPurchase_type().compareTo("0") != 0 && pur_Bill.getPurchase_type().compareTo("") != 0 && pur_Bill.getPurchase_type() != null) {
			pur_Bill.setPurchase_typename(item_type_masterRepository.getItemType(pur_Bill.getPurchase_type()).getItem_name());
		}else {pur_Bill.setPurchase_typename("None");}
		
		if(pur_Bill.getSupplier_name().compareTo("0") !=0 && pur_Bill.getSupplier_name().compareTo("") !=0 && pur_Bill.getSupplier_name() !=null) {
			pur_Bill.setSupplier(supp_bussiness_partnerRepository.getSupplierName(pur_Bill.getSupplier_name()).getBp_name());
		}else {pur_Bill.setSupplier("None");}
		
	/*	if(pur_Bill.getReferance_type().compareToIgnoreCase("OPEN GRN")==0) 
		{
			pur_Bill.setVehicleno("None");
		}
		else 
		{
			if(pur_Bill.getTruck_no().compareTo("0") !=0 && pur_Bill.getTruck_no().compareTo("") !=0 && pur_Bill.getTruck_no() !=null) {
				pur_Bill.setVehicleno(vehicleMasterRepository.getVehicleDetails(pur_Bill.getTruck_no()).getVehicle_no());
			}else {pur_Bill.setVehicleno("None");}
		}*/
		//if vehicle present then vehicle no comes otherwise not in all cases of reference type
		
		if(pur_Bill.getTruck_no().compareTo("0") !=0 && pur_Bill.getTruck_no().compareTo("") !=0 && pur_Bill.getTruck_no() !=null) {
			pur_Bill.setVehicleno(vehicleMasterRepository.getVehicleDetails(pur_Bill.getTruck_no()).getVehicle_no());
		}else {pur_Bill.setVehicleno("None");}
		
		
		Set<Pur_Bill_Item_Details> purbillitem = new HashSet<Pur_Bill_Item_Details>();
		purbillitem.addAll(pur_Bill.getPur_Bill_Item_Details());
		for(Pur_Bill_Item_Details PI : purbillitem)
		{
			PI.setPur_bill_id(gen_sno);
			PI.setPur_bill_no(pur_Bill.getPur_bill_no());
			PI.setAdv_item_name(item_masterRepository.itemNameById(PI.getAdv_item_code()).getItem_name());
			if(PI.getAdv_packing_item().compareTo("")!=0 && PI.getAdv_packing_item().compareTo("0")!=0) {
				PI.setAdv_packing_item_name(item_masterRepository.itemNameById(PI.getAdv_packing_item()).getItem_name());
			}
			PI.setCompany_id(pur_Bill.getCompany_id());
			PI.setFin_year(pur_Bill.getFin_year());
			PI.setModified_type("INSERTED");
			PI.setInserted_by(pur_Bill.getInserted_by());
			PI.setInserted_on(pur_Bill.getInserted_on());
			PI.setUpdated_by("NA");
			PI.setUpdated_on(ldt);
			PI.setDeleted_by("NA");
			PI.setDeleted_on(ldt);
		
		}
		pur_Bill.setPur_Bill_Item_Details(purbillitem);
		
		
		if(Utility.isNullOrEmpty(pur_Bill.getStore_charges())) 
		{
			pur_Bill.setStore_charges_name(store_inv_charge_masterRepository.getStoreChargeMaster(pur_Bill.getStore_charges()).getStore_charge_desc());
		}
		else 
		{
			pur_Bill.setStore_charges_name("NA");
		}
        if(Utility.isNullOrEmpty(pur_Bill.getStore_frieghtcharges_gl_ac())) {
			
		}else {pur_Bill.setStore_frieghtcharges_gl_ac("0");}
	
		
		Set<Pur_bill_store_chgs> pur_bill_store_chgs = new HashSet<Pur_bill_store_chgs>();
		pur_bill_store_chgs.addAll(pur_Bill.getPur_bill_store_chgs());
		for(Pur_bill_store_chgs purstorechgs : pur_bill_store_chgs)
		{
			purstorechgs.setPur_bill_id(gen_sno);
			purstorechgs.setPur_bill_no(pur_Bill.getPur_bill_no());
			if(pur_Bill.getPurchase_type().compareToIgnoreCase("ITMT00004")==0){
				
				if(Utility.isNullOrEmpty(pur_Bill.getStore_charges())) {
					if(pur_Bill.getStore_charges().compareToIgnoreCase("NA")!=0) 
					{
						purstorechgs.setCharges_acc_code(ledgermasterRepository.getLedgerDetailsThrName(purstorechgs.getCharges_acc()).getLedgerid());
						
					}
				}
			}
			purstorechgs.setCompany_id(pur_Bill.getCompany_id());
			purstorechgs.setFin_year(pur_Bill.getFin_year());
			purstorechgs.setModified_type("INSERTED");
			purstorechgs.setInserted_by(pur_Bill.getInserted_by());
			purstorechgs.setInserted_on(pur_Bill.getInserted_on());
			purstorechgs.setUpdated_by("NA");
			purstorechgs.setUpdated_on(ldt);
			purstorechgs.setDeleted_by("NA");
			purstorechgs.setDeleted_on(ldt);
		
		}
		pur_Bill.setPur_bill_store_chgs(pur_bill_store_chgs);
		
		Set<Pur_Bill_Musterroll_Details> purbillmuster = new HashSet<Pur_Bill_Musterroll_Details>();
		purbillmuster.addAll(pur_Bill.getPur_Bill_Musterroll_Details());
		for(Pur_Bill_Musterroll_Details PM : purbillmuster)
		{
			PM.setPur_bill_id(gen_sno);
			PM.setPur_bill_no(pur_Bill.getPur_bill_no());
			PM.setCompany_id(pur_Bill.getCompany_id());
			PM.setFin_year(pur_Bill.getFin_year());
			PM.setModified_type("INSERTED");
			PM.setInserted_by(pur_Bill.getInserted_by());
			PM.setInserted_on(pur_Bill.getInserted_on());
			PM.setUpdated_by("NA");
			PM.setUpdated_on(ldt);
			PM.setDeleted_by("NA");
			PM.setDeleted_on(ldt);
		
		}
		
		/************* Tax Entry Start *********************************/	
		
		Set<Purchase_item_groupwise_summ> iSummSet=new HashSet<Purchase_item_groupwise_summ>();
		iSummSet.addAll(pur_Bill.getPurchase_item_groupwise_summ());
		for(Purchase_item_groupwise_summ iSumm:iSummSet) 
		{
			iSumm.setPur_bill_id(gen_sno);
			iSumm.setFin_year(pur_Bill.getFin_year());
			iSumm.setCompany_id(pur_Bill.getCompany_id());
			iSumm.setUsername(pur_Bill.getUsername());
			iSumm.setModified_type("INSERTED");
			iSumm.setInserted_by(pur_Bill.getInserted_by());
			iSumm.setInserted_on(ldt);
			iSumm.setUpdated_by(pur_Bill.getUpdated_by());
			iSumm.setUpdated_on(ldt);
			iSumm.setDeleted_by(pur_Bill.getDeleted_by());
			iSumm.setDeleted_on(ldt);
		}
		pur_Bill.setPurchase_item_groupwise_summ(iSummSet);	
		
		Set<Purchase_item_groupwise_taxsumm> iTaxSummSet=new HashSet<Purchase_item_groupwise_taxsumm>();
		iTaxSummSet.addAll(pur_Bill.getPurchase_item_groupwise_taxsumm());
		for(Purchase_item_groupwise_taxsumm itaxSumm:iTaxSummSet) 
		{
			itaxSumm.setPur_bill_id(gen_sno);
			itaxSumm.setFin_year(pur_Bill.getFin_year());
			itaxSumm.setCompany_id(pur_Bill.getCompany_id());
			itaxSumm.setUsername(pur_Bill.getUsername());
			itaxSumm.setModified_type("INSERTED");
			itaxSumm.setInserted_by(pur_Bill.getInserted_by());
			itaxSumm.setInserted_on(ldt);
			itaxSumm.setUpdated_by(pur_Bill.getUpdated_by());
			itaxSumm.setUpdated_on(ldt);
			itaxSumm.setDeleted_by(pur_Bill.getDeleted_by());
			itaxSumm.setDeleted_on(ldt);
		}
		pur_Bill.setPurchase_item_groupwise_taxsumm(iTaxSummSet);
		
		Set<Purchase_item_groupwise_hsnsumm> iHsnSummSet=new HashSet<Purchase_item_groupwise_hsnsumm>();
		iHsnSummSet.addAll(pur_Bill.getPurchase_item_groupwise_hsnsumm());
		for(Purchase_item_groupwise_hsnsumm iHsnSumm:iHsnSummSet) 
		{
			iHsnSumm.setPur_bill_id(gen_sno);
			iHsnSumm.setFin_year(pur_Bill.getFin_year());
			iHsnSumm.setCompany_id(pur_Bill.getCompany_id());
			iHsnSumm.setUsername(pur_Bill.getUsername());
			iHsnSumm.setModified_type("INSERTED");
			iHsnSumm.setInserted_by(pur_Bill.getInserted_by());
			iHsnSumm.setInserted_on(ldt);
			iHsnSumm.setUpdated_by(pur_Bill.getUpdated_by());
			iHsnSumm.setUpdated_on(ldt);
			iHsnSumm.setDeleted_by(pur_Bill.getDeleted_by());
			iHsnSumm.setDeleted_on(ldt);
		}
		pur_Bill.setPurchase_item_groupwise_hsnsumm(iHsnSummSet);
		
		
		/************* Tax Entry Ends *********************************/	
		
		
		
		pur_Bill.setPur_Bill_Musterroll_Details(purbillmuster);
		
		Set<Pur_Bill_Tax_Info> purbilltax=new HashSet<Pur_Bill_Tax_Info>();
		purbilltax.add(pur_Bill.getPur_Bill_Tax_Info());
		for(Pur_Bill_Tax_Info PT : purbilltax)
		{
			PT.setPur_bill_id(gen_sno);
			PT.setPur_bill_no(pur_Bill.getPur_bill_no());
			PT.setCompany_id(pur_Bill.getCompany_id());
			PT.setFin_year(pur_Bill.getFin_year());
			PT.setModified_type("INSERTED");
			PT.setInserted_by(pur_Bill.getInserted_by());
			PT.setInserted_on(pur_Bill.getInserted_on());
			PT.setUpdated_by("NA");
			PT.setUpdated_on(ldt);
			PT.setDeleted_by("NA");
			PT.setDeleted_on(ldt);
		}
		
		if(!purbilltax.isEmpty()) {
			pur_Bill.setPur_Bill_Tax_Info(purbilltax.iterator().next());
		}

		Set<Pur_Bill_Broker_Details> purbillbroker = new HashSet<Pur_Bill_Broker_Details>();
		purbillbroker.addAll(pur_Bill.getPur_Bill_Broker_Details());
		for(Pur_Bill_Broker_Details PB : purbillbroker)
		{
			PB.setPur_bill_id(gen_sno);
			PB.setPur_bill_no(pur_Bill.getPur_bill_no());
			PB.setCompany_id(pur_Bill.getCompany_id());
			PB.setFin_year(pur_Bill.getFin_year());
			PB.setModified_type("INSERTED");
			PB.setInserted_by(pur_Bill.getInserted_by());
			PB.setInserted_on(pur_Bill.getInserted_on());
			PB.setUpdated_by("NA");
			PB.setUpdated_on(ldt);
			PB.setDeleted_by("NA");
			PB.setDeleted_on(ldt);
		
		}
		pur_Bill.setPur_Bill_Broker_Details(purbillbroker);
		
		
		Set<Pur_Bill_Bp_Details> purbillbp=new HashSet<Pur_Bill_Bp_Details>();
		purbillbp.add(pur_Bill.getPur_Bill_Bp_Details());
		for(Pur_Bill_Bp_Details PBP : purbillbp)
		{
			PBP.setPur_bill_id(gen_sno);
			PBP.setPur_bill_no(pur_Bill.getPur_bill_no());
			PBP.setCompany_id(pur_Bill.getCompany_id());
			PBP.setFin_year(pur_Bill.getFin_year());
			PBP.setModified_type("INSERTED");
			PBP.setInserted_by(pur_Bill.getInserted_by());
			PBP.setInserted_on(pur_Bill.getInserted_on());
			PBP.setUpdated_by("NA");
			PBP.setUpdated_on(ldt);
			PBP.setDeleted_by("NA");
			PBP.setDeleted_on(ldt);
		}
		
		if(!purbillbp.isEmpty()) {
			pur_Bill.setPur_Bill_Bp_Details(purbillbp.iterator().next());
		}
		
		
		Set<Pur_Bill_Docs> purbilldoc = new HashSet<Pur_Bill_Docs>();
		purbilldoc.addAll(pur_Bill.getPur_Bill_Docs());
		for(Pur_Bill_Docs PD : purbilldoc)
		{
			PD.setPur_bill_id(gen_sno);
			PD.setPur_bill_no(pur_Bill.getPur_bill_no());
			PD.setCompany_id(pur_Bill.getCompany_id());
			PD.setFin_year(pur_Bill.getFin_year());
			PD.setModified_type("INSERTED");
			PD.setInserted_by(pur_Bill.getInserted_by());
			PD.setInserted_on(pur_Bill.getInserted_on());
			PD.setUpdated_by("NA");
			PD.setUpdated_on(ldt);
			PD.setDeleted_by("NA");
			PD.setDeleted_on(ldt);
		
		}
		pur_Bill.setPur_Bill_Docs(purbilldoc);
		
		Set<Pur_Bill_app_chgs> purbillappcharges = new HashSet<Pur_Bill_app_chgs>();
		purbillappcharges.addAll(pur_Bill.getPur_Bill_app_chgs());
		for(Pur_Bill_app_chgs PBC : purbillappcharges)
		{
			PBC.setPur_bill_id(gen_sno);
			PBC.setPur_bill_no(pur_Bill.getPur_bill_no());
			PBC.setCompany_id(pur_Bill.getCompany_id());
			PBC.setFin_year(pur_Bill.getFin_year());
			PBC.setModified_type("INSERTED");
			PBC.setInserted_by(pur_Bill.getInserted_by());
			PBC.setInserted_on(pur_Bill.getInserted_on());
			PBC.setUpdated_by("NA");
			PBC.setUpdated_on(ldt);
			PBC.setDeleted_by("NA");
			PBC.setDeleted_on(ldt);
		
		}
		pur_Bill.setPur_Bill_app_chgs(purbillappcharges);
		
		Set<Pur_Bill_Account_Info> purbillacc=new HashSet<Pur_Bill_Account_Info>();
		purbillacc.add(pur_Bill.getPur_Bill_Account_Info());
		for(Pur_Bill_Account_Info PBACC : purbillacc)
		{
			PBACC.setPur_bill_id(gen_sno);
			PBACC.setPur_bill_no(pur_Bill.getPur_bill_no());
			PBACC.setCompany_id(pur_Bill.getCompany_id());
			PBACC.setFin_year(pur_Bill.getFin_year());
			PBACC.setModified_type("INSERTED");
			PBACC.setInserted_by(pur_Bill.getInserted_by());
			PBACC.setInserted_on(pur_Bill.getInserted_on());
			PBACC.setUpdated_by("NA");
			PBACC.setUpdated_on(ldt);
			PBACC.setDeleted_by("NA");
			PBACC.setDeleted_on(ldt);
		}
		
		if(!purbillacc.isEmpty()) {
			pur_Bill.setPur_Bill_Account_Info(purbillacc.iterator().next());
		}
		System.out.println("get id/"+pur_Bill.getReferance_id());
		pur_BillRepository.grn_status_update(pur_Bill.getReferance_id());
		return pur_BillRepository.save(pur_Bill);
	}
	
	
	
	@Transactional
	public Pur_Bill update(Pur_Bill pur_Bill,long id)
	{
		Optional<Pur_Bill> op=this.pur_BillRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		pur_Bill.setPur_bill_id(op.get().getPur_bill_id());
		pur_Bill.setModified_type("INSERTED");
		pur_Bill.setBillstatus("No");
		pur_Bill.setReturn_apv_status("No");
		pur_Bill.setInserted_by(op.get().getInserted_by());
		pur_Bill.setInserted_on(op.get().getInserted_on());
		pur_Bill.setUpdated_by(userRepository.getUserDetails(pur_Bill.getUsername()).getName());
		pur_Bill.setUpdated_on(ldt);
		pur_Bill.setDeleted_by("NA");
		pur_Bill.setDeleted_on(ldt);
		
		pur_Bill.setReturn_apv_status(op.get().getReturn_apv_status());
		pur_Bill.setPurreturnid(op.get().getPurreturnid());
		
		pur_Bill.setBilldate(pur_Bill.getBill_date());
		pur_Bill.setPurbillno(pur_Bill.getPur_bill_no());
		
		if(pur_Bill.getPurchase_type().compareTo("0") != 0 && pur_Bill.getPurchase_type().compareTo("") != 0 && pur_Bill.getPurchase_type() != null) {
			pur_Bill.setPurchase_typename(item_type_masterRepository.getItemType(pur_Bill.getPurchase_type()).getItem_name());
		}else {pur_Bill.setPurchase_typename("None");}
		
		if(pur_Bill.getSupplier_name().compareTo("0") !=0 && pur_Bill.getSupplier_name().compareTo("") !=0 && pur_Bill.getSupplier_name() !=null) {
			pur_Bill.setSupplier(supp_bussiness_partnerRepository.getSupplierName(pur_Bill.getSupplier_name()).getBp_name());
		}else {pur_Bill.setSupplier("None");}
		
		if(pur_Bill.getTruck_no().compareTo("0") !=0 && pur_Bill.getTruck_no().compareTo("") !=0 && pur_Bill.getTruck_no() !=null) {
			pur_Bill.setVehicleno(vehicleMasterRepository.getVehicleDetails(pur_Bill.getTruck_no()).getVehicle_no());
		}else {pur_Bill.setVehicleno("None");}
		
		if(op.isPresent())
		{
			pur_Bill.setId(id);
		}
		pur_Bill.setState(supp_bussiness_partner_bill_addrRepository.getSuppBPBillAddr(pur_Bill.getSupplier_name()).getState());
		//here advice no added in PUR BILL through GRN
		
		 String grnstatus=pur_good_receiptRepository.getPurGoodRcptDtls(pur_Bill.getReferance_id()).getReferance_type();
			
			if(grnstatus.compareToIgnoreCase("OPEN GRN")==0) 
			{
				pur_Bill.setAdviceno("OPEN GRN");
			}
			else if(grnstatus.compareToIgnoreCase("PURCHASE ORDER")==0) 
			{
				pur_Bill.setAdviceno("PURCHASE ORDER");
			}
			else 
			{
				String adviceid=pur_good_receiptRepository.getPurGoodRcptDtls(pur_Bill.getReferance_id()).getReferance_id();
				
				String adviceno=wm_unload_adviceRepository.getUnloadAdviceData(adviceid).getUnadviceno();
						
				pur_Bill.setAdviceno(adviceno);
			}
		
		
			
		//here ends advice no added in PUR BILL through GRN
			
			if(Utility.isNullOrEmpty(pur_Bill.getApp_chgs_id())) {
				
			}else {pur_Bill.setApp_chgs_id("0");}
		
			pur_Bill.setExport(op.get().getExport());
			pur_Bill.setResponse(op.get().getResponse());
			
		pur_Bill_Item_DetailsRepository.updatePur_Bill_Item_Details(op.get().getPur_bill_id());
		Set<Pur_Bill_Item_Details> purbillitem = new HashSet<Pur_Bill_Item_Details>();
		purbillitem.addAll(pur_Bill.getPur_Bill_Item_Details());
		for(Pur_Bill_Item_Details PI : purbillitem)
		{
			PI.setPur_bill_id(op.get().getPur_bill_id());
			PI.setPur_bill_no(pur_Bill.getPur_bill_no());
			PI.setAdv_item_name(item_masterRepository.itemNameById(PI.getAdv_item_code()).getItem_name());
			if(PI.getAdv_packing_item().compareTo("")!=0 && PI.getAdv_packing_item().compareTo("0")!=0) {
				PI.setAdv_packing_item_name(item_masterRepository.itemNameById(PI.getAdv_packing_item()).getItem_name());
			}
			PI.setCompany_id(pur_Bill.getCompany_id());
			PI.setFin_year(pur_Bill.getFin_year());
			PI.setModified_type("INSERTED");
			PI.setInserted_by(pur_Bill.getInserted_by());
			PI.setInserted_on(pur_Bill.getInserted_on());
			PI.setUpdated_by(pur_Bill.getUpdated_by());
			PI.setUpdated_on(pur_Bill.getUpdated_on());
			PI.setDeleted_by("NA");
			PI.setDeleted_on(ldt);
		
		}
		pur_Bill.setPur_Bill_Item_Details(purbillitem);
		
		
		pur_bill_store_chgsRepository.updatePur_Bill_store(op.get().getPur_bill_id());
		
		Set<Pur_bill_store_chgs> pur_bill_store_chgs = new HashSet<Pur_bill_store_chgs>();
		pur_bill_store_chgs.addAll(pur_Bill.getPur_bill_store_chgs());
		for(Pur_bill_store_chgs purstorechgs : pur_bill_store_chgs)
		{
			purstorechgs.setPur_bill_id(op.get().getPur_bill_id());
			purstorechgs.setPur_bill_no(pur_Bill.getPur_bill_no());
			purstorechgs.setCompany_id(pur_Bill.getCompany_id());
			purstorechgs.setFin_year(pur_Bill.getFin_year());
			purstorechgs.setModified_type("INSERTED");
			purstorechgs.setInserted_by(pur_Bill.getInserted_by());
			purstorechgs.setInserted_on(pur_Bill.getInserted_on());
			purstorechgs.setUpdated_by("NA");
			purstorechgs.setUpdated_on(ldt);
			purstorechgs.setDeleted_by("NA");
			purstorechgs.setDeleted_on(ldt);
		
		}
		pur_Bill.setPur_bill_store_chgs(pur_bill_store_chgs);
		
		
		
		/************* Tax Entry Start *********************************/
		
		purchase_item_groupwise_summRepository.updateItem_groupwise_summ(op.get().getPur_bill_id());
		
		Set<Purchase_item_groupwise_summ> iSummSet=new HashSet<Purchase_item_groupwise_summ>();
		iSummSet.addAll(pur_Bill.getPurchase_item_groupwise_summ());
		for(Purchase_item_groupwise_summ iSumm:iSummSet) 
		{
			iSumm.setPur_bill_id(op.get().getPur_bill_id());
			iSumm.setFin_year(pur_Bill.getFin_year());
			iSumm.setCompany_id(pur_Bill.getCompany_id());
			iSumm.setUsername(pur_Bill.getUsername());
			iSumm.setModified_type("INSERTED");
			iSumm.setInserted_by(pur_Bill.getInserted_by());
			iSumm.setInserted_on(pur_Bill.getInserted_on());
			iSumm.setUpdated_by(pur_Bill.getUpdated_by());
			iSumm.setUpdated_on(pur_Bill.getUpdated_on());
			iSumm.setDeleted_by(pur_Bill.getDeleted_by());
			iSumm.setDeleted_on(pur_Bill.getDeleted_on());
		}
		pur_Bill.setPurchase_item_groupwise_summ(iSummSet);	

		purchase_item_groupwise_taxsummRepository.updateItem_groupwise_taxsumm(op.get().getPur_bill_id());
		
		Set<Purchase_item_groupwise_taxsumm> iTaxSummSet=new HashSet<Purchase_item_groupwise_taxsumm>();
		iTaxSummSet.addAll(pur_Bill.getPurchase_item_groupwise_taxsumm());
		for(Purchase_item_groupwise_taxsumm itaxSumm:iTaxSummSet) 
		{
			itaxSumm.setPur_bill_id(op.get().getPur_bill_id());
			itaxSumm.setFin_year(pur_Bill.getFin_year());
			itaxSumm.setCompany_id(pur_Bill.getCompany_id());
			itaxSumm.setUsername(pur_Bill.getUsername());
			itaxSumm.setModified_type("INSERTED");
			itaxSumm.setInserted_by(pur_Bill.getInserted_by());
			itaxSumm.setInserted_on(ldt);
			itaxSumm.setUpdated_by(pur_Bill.getUpdated_by());
			itaxSumm.setUpdated_on(ldt);
			itaxSumm.setDeleted_by(pur_Bill.getDeleted_by());
			itaxSumm.setDeleted_on(ldt);
		}
		pur_Bill.setPurchase_item_groupwise_taxsumm(iTaxSummSet);
		
		purchase_item_groupwise_hsnsummRepository.updateItem_groupwise_hsnsumm(op.get().getPur_bill_id());
		
		Set<Purchase_item_groupwise_hsnsumm> iHsnSummSet=new HashSet<Purchase_item_groupwise_hsnsumm>();
		iHsnSummSet.addAll(pur_Bill.getPurchase_item_groupwise_hsnsumm());
		for(Purchase_item_groupwise_hsnsumm iHsnSumm:iHsnSummSet) 
		{
			iHsnSumm.setPur_bill_id(op.get().getPur_bill_id());
			iHsnSumm.setFin_year(pur_Bill.getFin_year());
			iHsnSumm.setCompany_id(pur_Bill.getCompany_id());
			iHsnSumm.setUsername(pur_Bill.getUsername());
			iHsnSumm.setModified_type("INSERTED");
			iHsnSumm.setInserted_by(pur_Bill.getInserted_by());
			iHsnSumm.setInserted_on(pur_Bill.getInserted_on());
			iHsnSumm.setUpdated_by(pur_Bill.getUpdated_by());
			iHsnSumm.setUpdated_on(pur_Bill.getUpdated_on());
			iHsnSumm.setDeleted_by(pur_Bill.getDeleted_by());
			iHsnSumm.setDeleted_on(pur_Bill.getDeleted_on());
		}
		pur_Bill.setPurchase_item_groupwise_hsnsumm(iHsnSummSet);
		
		
		
		
		/************* Tax Entry ends *********************************/
		
		
		
		pur_Bill_Musterroll_DetailsRepository.updatePur_Bill_Musterroll_Details(op.get().getPur_bill_id());
		
		Set<Pur_Bill_Musterroll_Details> purbillmuster = new HashSet<Pur_Bill_Musterroll_Details>();
		purbillmuster.addAll(pur_Bill.getPur_Bill_Musterroll_Details());
		for(Pur_Bill_Musterroll_Details PM : purbillmuster)
		{
			PM.setPur_bill_id(op.get().getPur_bill_id());
			PM.setPur_bill_no(pur_Bill.getPur_bill_no());
			PM.setCompany_id(pur_Bill.getCompany_id());
			PM.setFin_year(pur_Bill.getFin_year());
			PM.setModified_type("INSERTED");
			PM.setInserted_by(pur_Bill.getInserted_by());
			PM.setInserted_on(pur_Bill.getInserted_on());
			PM.setUpdated_by(pur_Bill.getUpdated_by());
			PM.setUpdated_on(pur_Bill.getUpdated_on());
			PM.setDeleted_by("NA");
			PM.setDeleted_on(ldt);
		
		}
		pur_Bill.setPur_Bill_Musterroll_Details(purbillmuster);
		
		
		pur_Bill_Tax_InfoRepository.updatePur_Bill_Tax_Info(op.get().getPur_bill_id());
		Set<Pur_Bill_Tax_Info> purbilltax=new HashSet<Pur_Bill_Tax_Info>();
		purbilltax.add(pur_Bill.getPur_Bill_Tax_Info());
		for(Pur_Bill_Tax_Info PT : purbilltax)
		{
			PT.setPur_bill_id(op.get().getPur_bill_id());
			PT.setPur_bill_no(pur_Bill.getPur_bill_no());
			PT.setCompany_id(pur_Bill.getCompany_id());
			PT.setFin_year(pur_Bill.getFin_year());
			PT.setModified_type("INSERTED");
			PT.setInserted_by(pur_Bill.getInserted_by());
			PT.setInserted_on(pur_Bill.getInserted_on());
			PT.setUpdated_by(pur_Bill.getUpdated_by());
			PT.setUpdated_on(pur_Bill.getUpdated_on());
			PT.setDeleted_by("NA");
			PT.setDeleted_on(ldt);
		}
		
		if(!purbilltax.isEmpty()) {
			pur_Bill.setPur_Bill_Tax_Info(purbilltax.iterator().next());
		}

		pur_Bill_Broker_DetailsRepository.updatePur_Bill_Broker_Details(op.get().getPur_bill_id());
		Set<Pur_Bill_Broker_Details> purbillbroker = new HashSet<Pur_Bill_Broker_Details>();
		purbillbroker.addAll(pur_Bill.getPur_Bill_Broker_Details());
		for(Pur_Bill_Broker_Details PB : purbillbroker)
		{
			PB.setPur_bill_id(op.get().getPur_bill_id());
			PB.setPur_bill_no(pur_Bill.getPur_bill_no());
			PB.setCompany_id(pur_Bill.getCompany_id());
			PB.setFin_year(pur_Bill.getFin_year());
			PB.setModified_type("INSERTED");
			PB.setInserted_by(pur_Bill.getInserted_by());
			PB.setInserted_on(pur_Bill.getInserted_on());
			PB.setUpdated_by(pur_Bill.getUpdated_by());
			PB.setUpdated_on(pur_Bill.getUpdated_on());
			PB.setDeleted_by("NA");
			PB.setDeleted_on(ldt);
		
		}
		pur_Bill.setPur_Bill_Broker_Details(purbillbroker);
		
		pur_Bill_Bp_DetailsRepository.updatePur_Bill_Bp_Details(op.get().getPur_bill_id());
		Set<Pur_Bill_Bp_Details> purbillbp=new HashSet<Pur_Bill_Bp_Details>();
		purbillbp.add(pur_Bill.getPur_Bill_Bp_Details());
		for(Pur_Bill_Bp_Details PBP : purbillbp)
		{
			PBP.setPur_bill_id(op.get().getPur_bill_id());
			PBP.setPur_bill_no(pur_Bill.getPur_bill_no());
			PBP.setCompany_id(pur_Bill.getCompany_id());
			PBP.setFin_year(pur_Bill.getFin_year());
			PBP.setModified_type("INSERTED");
			PBP.setInserted_by(pur_Bill.getInserted_by());
			PBP.setInserted_on(pur_Bill.getInserted_on());
			PBP.setUpdated_by(pur_Bill.getUpdated_by());
			PBP.setUpdated_on(pur_Bill.getUpdated_on());
			PBP.setDeleted_by("NA");
			PBP.setDeleted_on(ldt);
		}
		
		if(!purbillbp.isEmpty()) {
			pur_Bill.setPur_Bill_Bp_Details(purbillbp.iterator().next());
		}
		
		pur_Bill_DocsRepository.updatePur_Bill_Docs(op.get().getPur_bill_id());
		Set<Pur_Bill_Docs> purbilldoc = new HashSet<Pur_Bill_Docs>();
		purbilldoc.addAll(pur_Bill.getPur_Bill_Docs());
		for(Pur_Bill_Docs PD : purbilldoc)
		{
			PD.setPur_bill_id(op.get().getPur_bill_id());
			PD.setPur_bill_no(pur_Bill.getPur_bill_no());
			PD.setCompany_id(pur_Bill.getCompany_id());
			PD.setFin_year(pur_Bill.getFin_year());
			PD.setModified_type("INSERTED");
			PD.setInserted_by(pur_Bill.getInserted_by());
			PD.setInserted_on(pur_Bill.getInserted_on());
			PD.setUpdated_by(pur_Bill.getUpdated_by());
			PD.setUpdated_on(pur_Bill.getUpdated_on());
			PD.setDeleted_by("NA");
			PD.setDeleted_on(ldt);
		
		}
		pur_Bill.setPur_Bill_Docs(purbilldoc);
		
		pur_Bill_app_chgsRepository.updatePur_Bill_app_chgsls(op.get().getPur_bill_id());
		Set<Pur_Bill_app_chgs> purbillappcharges = new HashSet<Pur_Bill_app_chgs>();
		purbillappcharges.addAll(pur_Bill.getPur_Bill_app_chgs());
		for(Pur_Bill_app_chgs PBC : purbillappcharges)
		{
			PBC.setPur_bill_id(op.get().getPur_bill_id());
			PBC.setPur_bill_no(pur_Bill.getPur_bill_no());
			PBC.setCompany_id(pur_Bill.getCompany_id());
			PBC.setFin_year(pur_Bill.getFin_year());
			PBC.setModified_type("INSERTED");
			PBC.setInserted_by(pur_Bill.getInserted_by());
			PBC.setInserted_on(pur_Bill.getInserted_on());
			PBC.setUpdated_by(pur_Bill.getUpdated_by());
			PBC.setUpdated_on(pur_Bill.getUpdated_on());
			PBC.setDeleted_by("NA");
			PBC.setDeleted_on(ldt);
		
		}
		pur_Bill.setPur_Bill_app_chgs(purbillappcharges);
		
		pur_Bill_Account_InfoRepository.updatePur_Bill_Account_Info(op.get().getPur_bill_id());
		Set<Pur_Bill_Account_Info> purbillacc=new HashSet<Pur_Bill_Account_Info>();
		purbillacc.add(pur_Bill.getPur_Bill_Account_Info());
		for(Pur_Bill_Account_Info PBACC : purbillacc)
		{
			PBACC.setPur_bill_id(op.get().getPur_bill_id());
			PBACC.setPur_bill_no(pur_Bill.getPur_bill_no());
			PBACC.setCompany_id(pur_Bill.getCompany_id());
			PBACC.setFin_year(pur_Bill.getFin_year());
			PBACC.setModified_type("INSERTED");
			PBACC.setInserted_by(pur_Bill.getInserted_by());
			PBACC.setInserted_on(pur_Bill.getInserted_on());
			PBACC.setUpdated_by(pur_Bill.getUpdated_by());
			PBACC.setUpdated_on(pur_Bill.getUpdated_on());
			PBACC.setDeleted_by("NA");
			PBACC.setDeleted_on(ldt);
		}
		
		if(!purbillacc.isEmpty()) {
			pur_Bill.setPur_Bill_Account_Info(purbillacc.iterator().next());
		}
		return pur_BillRepository.save(pur_Bill);
	}
	
	public List<Pur_BillDTO> findAllBill()
	{
		List<Pur_Bill> pbList=new ArrayList<Pur_Bill>();
		pbList.addAll(pur_BillRepository.findAllBills());
		
		Type listType=new TypeToken<List<Pur_BillDTO>>() {}.getType();
		List<Pur_BillDTO> pBillList=new ModelMapper().map(pbList,listType);

		for(int i=0;i<pBillList.size();i++) {
			boolean b1=true;
			int x = Boolean.compare(pBillList.get(i).isItem_type(),b1);
			//System.out.println("get pur bill data:"+pBillList.get(i).isItem_type() +"/"+x);
			if(x ==-1) {
				pBillList.get(i).setGrn_for("Material");
			}else {pBillList.get(i).setGrn_for("Services");}
		}
		return pBillList;
	}
	
	public Page<Pur_Bill_Pagination_DTO> getPurBillPagination(int page,int size)
	  {
			//PageRequest pageRequest = PageRequest.of(page, size,Sort.by("id").descending());
			PageRequest pageRequest = PageRequest.of(page, size,Sort.by("billdate").descending().and(Sort.by("purbillno")).descending());
		    Page<Pur_Bill> pageResult = pur_BillRepository.findAll(pageRequest);
		   
		    List<Pur_Bill_Pagination_DTO> advList = pageResult
		    	      .stream()
		    	      .map((Pur_Bill pur_Bill) -> new Pur_Bill_Pagination_DTO(pur_Bill.getId(),
		    	    		  pur_Bill.getPur_bill_id(),
		    	    		  pur_Bill.getPur_bill_no(),
		    	    		  pur_Bill.getBill_date(),
		    	    		  "0",
		    	    		  pur_Bill.getPurchase_typename(),
		    	    		  pur_Bill.getSupplier(),
		    	    		  pur_Bill.getVehicleno(),
		    	    		  pur_Bill.getNet_payable_party(),
		    	    		  pur_Bill.getModified_type(),
		    	    		  pur_Bill.isItem_type(),
		    	    		  pur_Bill.getExport()
		    	    		)).filter(c -> c.getModified_type().equals("INSERTED")).collect(Collectors.toList());
		 
		    for(int i=0;i<advList.size();i++) {
				/*boolean b1=true;
				int x = Boolean.compare(advList.get(i).isItem_type(),b1);
				//System.out.println("get pur bill data:"+pBillList.get(i).isItem_type() +"/"+x);
				if(x ==-1) {
					advList.get(i).setGrn_for("Material");
				}else {advList.get(i).setGrn_for("Services");}
				
				*/
		    	if(advList.get(i).isItem_type() == true) 
		    	{
		    		advList.get(i).setGrn_for("Material");
		    	}
		    	else 
		    	{
		    		advList.get(i).setGrn_for("Services");
		    	}
				
			}
		    
		    
		    return new PageImpl<>(advList, pageRequest, pageResult.getTotalElements());
	  }
	
	public List<Map<String,Object>> searchPurBillFast(String orderno,String fromdate, String todate,String supplier_name1,String pur_type1,String finyear)
	{
		List<Map<String,Object>> searchpurbill =new ArrayList<Map<String,Object>>();
		
		String tablename="pur_bill",party_name="supplier_name",order_no="pur_bill_no",order_date="bill_date",purchase_type="purchase_type";
		//System.out.println("tablename: "+tablename+" party_name: "+party_name+" order_no "+order_no+" order_date "+order_date+" orderno "+orderno+" fromdate "+fromdate+" todate "+todate+" supplier_name1 "+supplier_name1+" finyear "+finyear);
		searchpurbill.addAll(pur_BillRepository.getsearchdataFast(tablename,party_name,order_no,order_date,purchase_type,orderno,fromdate,todate,supplier_name1,pur_type1,finyear));
		
		return searchpurbill;
	}
	
  public List<Pur_Bill_Pagination_DTO> searchPurBill(String orderno,String fromdate, String todate,String supplier_name1,String pur_type1,String finyear)
	{
		List<Pur_Bill> searchpurbill =new ArrayList<Pur_Bill>();
		
		String tablename="pur_bill",party_name="supplier_name",order_no="pur_bill_no",order_date="bill_date",purchase_type="purchase_type";
		//System.out.println("tablename: "+tablename+" party_name: "+party_name+" order_no "+order_no+" order_date "+order_date+" orderno "+orderno+" fromdate "+fromdate+" todate "+todate+" supplier_name1 "+supplier_name1+" finyear "+finyear);
		searchpurbill.addAll(pur_BillRepository.getsearchdata(tablename,party_name,order_no,order_date,purchase_type,orderno,fromdate,todate,supplier_name1,pur_type1,finyear));
		
		List<Pur_Bill_Pagination_DTO> advList = new ArrayList<Pur_Bill_Pagination_DTO>();
		for(int i=0;i<searchpurbill.size();i++) 
		{
			
			
			//System.out.println("serchsaleorder.get(i).getId() "+searchsalesinvoice.get(i).getId());
			Pur_Bill_Pagination_DTO sale= new Pur_Bill_Pagination_DTO(searchpurbill.get(i).getId(),
					searchpurbill.get(i).getPur_bill_id(),
					searchpurbill.get(i).getPur_bill_no(),
					searchpurbill.get(i).getBill_date(),
	  	    		  "0",
	  	    		searchpurbill.get(i).getPurchase_typename(),
	  	    		searchpurbill.get(i).getSupplier(),
	  	    		searchpurbill.get(i).getVehicleno(),
	  	    		searchpurbill.get(i).getNet_payable_party(),
	  	    		searchpurbill.get(i).getModified_type(),
	  	    		searchpurbill.get(i).isItem_type(),
	  	    		searchpurbill.get(i).getExport());
			
			advList.add(sale);
		}
		 for(int i=0;i<advList.size();i++) {
				boolean b1=true;
			//	int x = Boolean.compare(advList.get(i).isItem_type(),b1);
				//System.out.println("get pur bill data:"+pBillList.get(i).isItem_type() +"/"+x);
				
				if(advList.get(i).isItem_type() == true) 
		    	{
		    		advList.get(i).setGrn_for("Material");
		    	}
		    	else 
		    	{
		    		advList.get(i).setGrn_for("Services");
		    	}
				
				
			}
		 
		 List<Pur_Bill_Pagination_DTO> allData = advList
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Pur_Bill_Pagination_DTO::getBill_date).
						  thenComparingInt(
                         d -> d.getBill_date().length() + d.getPur_bill_no().length())
                 .thenComparing(Pur_Bill_Pagination_DTO::getPur_bill_no).reversed())
				  .collect(Collectors.toList());
		 
		return allData;
	}
  
 /* public List<Pur_BillDTO> searchPurBill(String orderno,String fromdate, String todate,String supplier_name1,String finyear)
	{
	  List<Pur_Bill> modelList =new ArrayList<Pur_Bill>();
	 	String tablename="pur_bill",party_name="supplier_name",order_no="pur_bill_no",order_date="bill_date";
		
		modelList.addAll(pur_BillRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,supplier_name1,finyear));
		
		
		
		Type listType = new TypeToken<List<Pur_BillDTO>>() {}.getType();
		List<Pur_BillDTO> advList = new ModelMapper().map(modelList,listType);
		
		 for(int i=0;i<advList.size();i++) {
		    	if(advList.get(i).isItem_type() == true) 
		    	{
		    		advList.get(i).setGrn_for("Material");
		    	}
		    	else 
		    	{
		    		advList.get(i).setGrn_for("Services");
		    	}
				
			}
		
		return advList;
	}*/
	
	public Pur_BillDTO getPurBillDetails(String pbillid,String company,String finyear)
	{
		Pur_Bill billDtls =pur_BillRepository.getPurBillDetails(pbillid,company,finyear);
		
		Type listType=new TypeToken<Pur_BillDTO>() {}.getType();
		
		Pur_BillDTO billDetls=new ModelMapper().map(billDtls,listType);
		
		return billDetls;
	}
	
	public List<Pur_BillDTO> getPaymentStatus(String fromdate,String todate)
	{
		List<Pur_Bill> pbDtls=new ArrayList<Pur_Bill>();
		
		System.err.println(fromdate+" , "+todate);
		if(fromdate.compareTo("undefined")!=0 && todate.compareTo("undefined")!=0 && fromdate.compareTo("null") != 0 && todate.compareTo("null") != 0) {
			pbDtls.addAll(pur_BillRepository.getPaymentStatus(Utility.convertDateDDMMYYYY(fromdate),Utility.convertDateDDMMYYYY(todate)));
		}
		
		Type listType=new TypeToken<List<Pur_BillDTO>>() {}.getType();
		List<Pur_BillDTO> purBillList=new ModelMapper().map(pbDtls,listType);
		
		return purBillList;
	}
	
	public List<Pur_BillDTO> getPurBillRtnApp(String supplier,String tdate,String company,String finyear)
	{
		List<Pur_Bill> modelList =pur_BillRepository.getPurBillRtnApp(supplier,tdate,company,finyear);
		
		Type listType=new TypeToken<List<Pur_BillDTO>>() {}.getType();
		
		List<Pur_BillDTO> ordList=new ModelMapper().map(modelList,listType);
		
		return ordList;
	}

	public Pur_Bill findOne(long id)
	{
		Optional<Pur_Bill> op=this.pur_BillRepository.findById(id);
		return op.get();
	}
	
	
	public List<Pur_Bill_Item_DetailsDTO> purBillItemRetriveList(String pbid)
	{
		List<Pur_Bill_Item_Details> modelList=pur_Bill_Item_DetailsRepository.purBillItemRetriveList(pbid);
		
		Type listType=new TypeToken<List<Pur_Bill_Item_DetailsDTO>>() {}.getType();
		
		List<Pur_Bill_Item_DetailsDTO> purbSrv=new ModelMapper().map(modelList,listType);
		
		return purbSrv;
	}
	

	public List<Pur_Bill_Item_DetailsDTO> purBillItemRetriveListPrint(String pbid)
	{
		List<Pur_Bill_Item_Details> modelList=pur_Bill_Item_DetailsRepository.purBillItemRetriveList(pbid);
		
		modelList.forEach(element->
		{
			String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(element.getAdv_item_code()).getItem_group();
			System.out.println("subgroup_items_code :: "+ subgroup_items_code);
			Item_group_master_pur_acc itemgroup= item_group_master_pur_accRepository.getItem_group_master_pur_acc(subgroup_items_code);
			System.out.println(" glac " + itemgroup.getItem_total_gl_ac());
			element.setAdv_item_name(ledgermasterRepository.getLedgers(itemgroup.getItem_total_gl_ac()).getLedgername());
			
			if(element.getDiscount_amount()==0.00) 
			{
				
			}else 
			{
				element.setDiscount_basedon(ledgermasterRepository.getLedgers(itemgroup.getDiscount_gl_ac()).getLedgername());	
			}
			
		});
		
		
		
		Type listType=new TypeToken<List<Pur_Bill_Item_DetailsDTO>>() {}.getType();
		
		List<Pur_Bill_Item_DetailsDTO> purbSrv=new ModelMapper().map(modelList,listType);
		
		return purbSrv;
	}
	
	public List<Map<String,Object>> getpurbillprintupperdata(String pbid)
	{
		return pur_BillRepository.getpurbillprintupperdata(pbid);
	}
	
	public List<Pur_Bill_Musterroll_DetailsDTO> purBillMusterRetriveList(String pbid)
	{
		List<Pur_Bill_Musterroll_Details> modelList=pur_Bill_Musterroll_DetailsRepository.purBillMusterRetriveList(pbid);
		
		Type listType=new TypeToken<List<Pur_Bill_Musterroll_DetailsDTO>>() {}.getType();
		
		List<Pur_Bill_Musterroll_DetailsDTO> purbSrv=new ModelMapper().map(modelList,listType);
		
		return purbSrv;
	}
	
	
	@Autowired
	Pur_Bill_Tax_InfoRepository pur_Bill_Tax_InfoRepository;
	public Pur_Bill_Tax_InfoDTO gePurBillTaxInfo(String pbid)
	{
		Pur_Bill_Tax_Info modelList=pur_Bill_Tax_InfoRepository.gePurBillTaxInfo(pbid);
		
		Type listType=new TypeToken<Pur_Bill_Tax_InfoDTO>() {}.getType();
		
		Pur_Bill_Tax_InfoDTO purDtls=new ModelMapper().map(modelList,listType);
		
		return purDtls;
	}
	
	
	public List<Pur_Bill_Broker_DetailsDTO> purBillBrokerRetriveList(String pbid)
	{
		List<Pur_Bill_Broker_Details> modelList=pur_Bill_Broker_DetailsRepository.purBillBrokerRetriveList(pbid);
		
		Type listType=new TypeToken<List<Pur_Bill_Broker_DetailsDTO>>() {}.getType();
		
		List<Pur_Bill_Broker_DetailsDTO> purbSrv=new ModelMapper().map(modelList,listType);
		
		return purbSrv;
	}
	
	
	@Autowired
	Pur_Bill_Bp_DetailsRepository pur_Bill_Bp_DetailsRepository;
	
	public Pur_Bill_Bp_DetailsDTO gePurBillBPRetrive(String pbid)
	{
		Pur_Bill_Bp_Details modelList=pur_Bill_Bp_DetailsRepository.gePurBillBPRetrive(pbid);
		
		Type listType=new TypeToken<Pur_Bill_Bp_DetailsDTO>() {}.getType();
		
		Pur_Bill_Bp_DetailsDTO purDtls=new ModelMapper().map(modelList,listType);
		
		return purDtls;
		
	}
	
	 public List<Pur_Bill_DocsDTO> purBillDocRetriveList(String pbid)
	 {

			List<Pur_Bill_Docs> modelList=pur_Bill_DocsRepository.purBillDocRetriveList(pbid);
			
			Type listType=new TypeToken<List<Pur_Bill_DocsDTO>>() {}.getType();
			
			List<Pur_Bill_DocsDTO> purbSrv=new ModelMapper().map(modelList,listType);
			
			return purbSrv; 
		 
	 }
	 
	 @Autowired
	 Pur_Bill_Account_InfoRepository pur_Bill_Account_InfoRepository;
	 public Pur_Bill_Account_InfoDTO gePurBillAccRetrive(String pbid)
	 {
		 	Pur_Bill_Account_Info modelList=pur_Bill_Account_InfoRepository.gePurBillAccRetrive(pbid);
			
			Type listType=new TypeToken<Pur_Bill_Account_InfoDTO>() {}.getType();
			
			Pur_Bill_Account_InfoDTO purDtls=new ModelMapper().map(modelList,listType);
			
			return purDtls; 
		 
	 }
	 
 
	 
	 public List<Pur_Order_app_chgs> getChargesapplication(String grnid)
	 {
		 Pur_good_receipt grndata=pur_good_receiptRepository.getPurGoodRcptDtls(grnid);
		
		 System.out.println(grndata.getReferance_id());
		 Wm_unload_advice unloaddata=wm_unload_adviceRepository.getUnloadId(grndata.getReferance_id());
		 
		 
		 
		 List<Pur_Order_app_chgs> appcharges=pur_BillRepository.getappcharges(unloaddata.getPur_orderid());
		 
		 
		 return appcharges;
	 }
	 
	 public List<Pur_Order_app_chgs> getChargesMatrixdetails(String unloadid)
	 {
		
		 Pur_good_receipt grndata=pur_good_receiptRepository.getPurGoodRcptDtls(unloadid);
		 
		 Wm_unload_advice unloaddata=wm_unload_adviceRepository.getUnloadId(grndata.getReferance_id());
		 
		// Pur_Order chargeid=pur_OrderRepository.getPurOrdDetails(unloaddata.getPur_orderid());
		 
		 List<Pur_Order_app_chgs> appcharges=pur_BillRepository.getappcharges(unloaddata.getPur_orderid());
		
		 
		// appcharges.forEach(element->
		// {
		//	 element.setPur_orderid(chargeid.getApp_chgs_id());
			 
		// });
		 
		 return appcharges;
		 
	 }
	 
	 public List<Pur_Bill_app_chgs> purBillAppChargesRetriveList(String pbid)
		{
			List<Pur_Bill_app_chgs> modelList=pur_Bill_app_chgsRepository.purBillAppChargesRetriveList(pbid);
			
			
			return modelList;
		}
	 
	 public List<Map<String,Object>> purBillStoreChargesRetriveList(String pbid)
	 {
		 return  pur_bill_store_chgsRepository.purBillStoreChargesRetriveList(pbid);
	 }
	 
	 
	 public List<Pur_Bill_app_chgs> purBillCharMatrixposting(String pbid)
		{
			List<Pur_Bill_app_chgs> modelList=pur_Bill_app_chgsRepository.purBillAppChargesRetriveList(pbid);
			
			Pur_Bill retriveAppId =pur_BillRepository.getPurBillNo(pbid);
			
			List<Charge_Masterdtls> chargeMatrixList=charge_MasterRepository.getChargeMasterdtlsListnew(retriveAppId.getApp_chgs_id());
			//modelList.retainAll(chargeMatrixList);
			
			for(int a=0;a<chargeMatrixList.size();a++)
			{
				String name = chargeMatrixList.get(a).getCharge_name();
				Ledgermaster accounts=ledgermasterRepository.getLedgers(chargeMatrixList.get(a).getCharge_ac());
				
				modelList.forEach((e->{
					
					
					
					if(e.getCharges_name().compareToIgnoreCase(name) == 0)
					{
						e.setCharges_name(accounts.getLedgername());
					}
					
				}));
			}
			
			
			return modelList;
			
		}
	 
	 
	 @Transactional
		public Pur_Bill accountpostingPurchaseBill(long id) 
		{
			Optional<Pur_Bill> op=this.pur_BillRepository.findById(id);
			
			List<Pur_Bill_Item_Details> itemDetails=pur_Bill_Item_DetailsRepository.purBillItemRetriveList(op.get().getPur_bill_id());
			String classfied_name="";
			
			String date=op.get().getBill_date();
			int expocheck=pur_BillRepository.getdoublelength(op.get().getPur_bill_id());
		//	String[] dest = new dest ['abcd','123'];
	      //  System.arraycopy(itemDetails, 0, dest, 0, itemDetails.size());
			String itemSubGroupName="";
			String item_name_ledger[]=new String[itemDetails.size()];
	        String item_name[]=new String[itemDetails.size()];
	        String item_amount[]=new String[itemDetails.size()];
	        String item_rate[]=new String[itemDetails.size()];
	        String item_passeditemqty[]=new String[itemDetails.size()];
	        String item_qty[]=new String[itemDetails.size()];
	        
	        String packing_qty[]=new String[itemDetails.size()];
	        String packing_uom[]=new String[itemDetails.size()];
	        String itempricebaseon[]=new String[itemDetails.size()];
	        
	        
	        String discountamount[]=new String[itemDetails.size()];
	        String discountledger[]=new String[itemDetails.size()];
	        boolean discountstat=false;
	        double item_total=0.00;
	        for(int i=0;i<itemDetails.size();i++) 
	        {
	        	//itemSubGroupName=item_masterRepository.getItemDetailsThruItemId(itemDetails.get(i).getAdv_item_code()).getSub_group_name();
	        	String subgroup_items_code=item_masterRepository.getItemDetailsThruItemId(itemDetails.get(i).getAdv_item_code()).getItem_group();
				System.out.println("subgroup_items_code :: "+ subgroup_items_code);
				Item_group_master_pur_acc itemgroup= item_group_master_pur_accRepository.getItem_group_master_pur_acc(subgroup_items_code);
				
	        	item_name_ledger[i]=ledgermasterRepository.getLedgers(itemgroup.getItem_total_gl_ac()).getLedgername();
	        	classfied_name+=itemDetails.get(i).getClassified_item_name()+",";
	        	item_name[i]=itemDetails.get(i).getAdv_item_name();
	        	System.out.println("item_name " +item_name[i]+ " / " + item_name_ledger[i]);
	        	
	        	if(expocheck>10) 
				{
	        		BigDecimal b = new BigDecimal(itemDetails.get(i).getNet_amount());
					b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	        		item_amount[i]=String.valueOf(b);
	        	
	        	//BigDecimal b = new BigDecimal(itemDetails.get(i).getAmount());
				 //b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				}
	        	else 
	        	{
	        		item_amount[i]=String.valueOf(itemDetails.get(i).getNet_amount());
	        		
	        		
	        	}
	        	
	        	packing_qty[i]=String.valueOf(itemDetails.get(i).getPassed_packing_qty());    
	        	packing_uom[i]=itemDetails.get(i).getPassed_packing_uom();
	        	itempricebaseon[i]=itemDetails.get(i).getPrice_based_on();
	        	
	        	item_rate[i]=String.valueOf(itemDetails.get(i).getUnit_rate());
	        	item_passeditemqty[i]=String.valueOf(itemDetails.get(i).getPassed_item_qty());
	        	item_qty[i]=itemDetails.get(i).getPassed_item_uom();
	        	item_total+=itemDetails.get(i).getAmount();
	        	
	        	/*if(itemDetails.get(i).getDiscount_amount()>0) 
	        	{
	        		discountstat=true;
	        		discountamount[i]=String.valueOf(itemDetails.get(i).getDiscount_amount());
	        		discountledger[i]=ledgermasterRepository.getLedgers(itemgroup.getDiscount_gl_ac()).getLedgername();
	        	}*/
	        	
	        }
	        boolean includecharge_matrix=true;
	        
	        if(op.get().getApp_chgs_id().compareToIgnoreCase("0")==0) 
	        {
	        	includecharge_matrix=false;
	        }
	        
	        Ledgermaster ledgerid=ledgermasterRepository.getLedgerDetails(op.get().getRoundoff_gl_ac());
	        int rounfoffdrcr=0;
	        //minus=cr,plus=dr
	        boolean roundoffstatus=true;
	        
	        if(op.get().getRoundoff_amt() ==0) 
	        {
	        	roundoffstatus=false;
	        }
	        //1 means dr and 0 means cr
	        if(op.get().getRoundoff_amt()>0)
	        {
	        	rounfoffdrcr=1;
	        }
	        if(op.get().getRoundoff_amt()<0)
	        {
	        	rounfoffdrcr=0;
	        	
	        }
			String roundoffglaccount=ledgerid.getLedgername();
					
			
			//Add(+)  dr.   Add(-) cr.
			//add1 dr.    add2 cr.
			
			boolean addplus=false,addminus=false;
			String add_plus_ledgername="",add_minus_ledgername="";
			double add_plus_amount=0.00,add_minus_amount=0.00;
			
			if(op.get().getAdd1()>0) 
			{
				addplus=true;
				Ledgermaster ledgeridadd=ledgermasterRepository.getLedgerDetails(op.get().getAdd1_gl_ac());
				add_plus_ledgername=ledgeridadd.getLedgername();
				add_plus_amount=op.get().getAdd1();
			}
			if(op.get().getAdd2()>0) 
			{
				addminus=true;
				Ledgermaster ledgeridminus=ledgermasterRepository.getLedgerDetails(op.get().getAdd2_gl_ac());
				add_minus_ledgername=ledgeridminus.getLedgername();
				add_minus_amount=op.get().getAdd2();
			}
			
			
			   //List<Pur_Bill_app_chgs> chargeMatrixDetails=pur_Bill_app_chgsRepository.purBillAppChargesRetriveList(op.get().getPur_bill_id());
		      
			//List<PurchasebillviewchargeDTO>chargeMatrixDetails=pur_Bill_app_chgsRepository.purBillAppChargesview(op.get().getPur_bill_id());
		       //TUHIN CHANGES 28-03-2024
			   /* List<Object[]> unloaddeatils=new ArrayList<Object[]>();
		        unloaddeatils.addAll(pur_Bill_app_chgsRepository.purBillAppChargesview(op.get().getPur_bill_id()));	        
		        List<PurchasebillviewchargeDTO>chargeMatrixDetails = new ArrayList<PurchasebillviewchargeDTO>();
		        for(final Object o : unloaddeatils)
			    {
			        Object[] obj = (Object[]) o;
			        
			        PurchasebillviewchargeDTO newpo = new PurchasebillviewchargeDTO();
			        newpo.setCharges_name(obj[0].toString());
			        newpo.setAdd_less(obj[2].toString());
			        newpo.setAmount(Double.parseDouble(obj[1].toString()));	
			        chargeMatrixDetails.add(newpo);			    }
		        
		        
		        //SELECT ledgername AS charges_name,SUM(amount) AS amount,add_less FROM  purchasebillcharges WHERE pur_bill_id='PB01338' GROUP BY ledgername,add_less
			    String charge_name[]=new String[chargeMatrixDetails.size()];
		        String charge_amount[]=new String[chargeMatrixDetails.size()];
		        String charge_add_less[]=new String[chargeMatrixDetails.size()];
		        for(int i=0;i<chargeMatrixDetails.size();i++) 
		        {
		        	if(includecharge_matrix ==true) 
		        	{
		        		//charge_name[i]=chargeMatrixDetails.get(i).getCharges_name();
		        	//	System.out.println("over here charge ");
		        	//	charge_name[i]=ledgermasterRepository.getLedgerDetails(charge_masterRepository.getchargeaccount(op.get().getApp_chgs_id(),chargeMatrixDetails.get(i).getCharges_name()).getCharge_ac()).getLedgername();	
		        		charge_name[i]=chargeMatrixDetails.get(i).getCharges_name();
		        	}
		        	else 
		        	{
		        	//	System.out.println("over here charge else ");
		        		charge_name[i]=chargeMatrixDetails.get(i).getCharges_name();
		        	}
		        	//charge_name[i]=chargeMatrixDetails.get(i).getCharges_name();
		        	
		        	charge_amount[i]=String.valueOf(chargeMatrixDetails.get(i).getAmount());
		        	charge_add_less[i]=chargeMatrixDetails.get(i).getAdd_less();
		        }
		        */
		        Pur_Bill_Bp_Details purdetails=pur_Bill_Bp_DetailsRepository.gePurBillBPRetrive(op.get().getPur_bill_id());
		       String supplieraddress=purdetails.getSupp_address();
		      
		       String supplierstate=supp_bussiness_partner_bill_addrRepository.getSuppBPBillAddr(op.get().getSupplier_name()).getState();
			//System.out.println("over here ");
			
			String supp_ref_doc_date=op.get().getSupp_ref_doc_date();
			String supp_ref_docno=op.get().getSupp_ref_docno();
			String purchaseinvoiceno=op.get().getPur_bill_no();
			
			Purchase_item_groupwise_taxsumm gst_details=new Purchase_item_groupwise_taxsumm();
			List<Purchase_item_groupwise_taxsumm>gst_details_dyna =new ArrayList<Purchase_item_groupwise_taxsumm>();
			gst_details_dyna.addAll(purchase_item_groupwise_taxsummRepository.getdetails(op.get().getPur_bill_id()));
			
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
			//System.out.println(gst_details.getCgst() + " / " + gst_details.getSgst() );
				
				cgstamount=gst_details.getCgst();
				sgstamount= gst_details.getSgst();
			}
			
			String output="";
			Tallyrequest_PurchaseBill tally=new Tallyrequest_PurchaseBill();
			try  
			{
				
				double amt=op.get().getNet_payable_party();
				//System.out.println("amt "+ amt);
				
				
				String  partyname=op.get().getSupplier();
				if(partyname.contains("&"))
				{
					partyname=partyname.replaceAll("&","&amp;");
					
				}
				
				
				String pincode=String.valueOf(supp_bussiness_partner_addressRepository.getSupplierAddr(op.get().getSupplier_name()).getPincode());
				String gstinno=supp_bussiness_partner_statutoryRepository.getSupplierStatDtls(op.get().getSupplier_name()).getGst_no();

				if(Utility.isNullOrEmpty(pincode)) 
				{
				}
				else 
				{
					pincode="";
				}
				if(Utility.isNullOrEmpty(gstinno)) 
				{
				}
				else 
				{
					gstinno="";
				}
			//BigInteger bd=new BigDecimal(op.get().getNet_payable_party()).toBigInteger();
			
			
			
			 date=date.replace("-", "");
			 supp_ref_doc_date=supp_ref_doc_date.replace("-", "");
			//SELECT LENGTH(net_payable_party) FROM `pur_bill` WHERE pur_bill_no='PB-RAW-E-250722-0004'
				//System.out.println("  : "+ pur_BillRepository.getdoublelength(op.get().getPur_bill_id()));
			 
			 
			 if(op.get().getPurchase_type().compareToIgnoreCase("ITMT00004")==0)	
			 {
				 Tallyrequest_PurchaseBill_Store tallystore=new Tallyrequest_PurchaseBill_Store();
				 boolean storechargesstatus=false;
				 
				 if(Utility.isNullOrEmpty(op.get().getStore_charges())) {
					 
						List<Pur_bill_store_chgs> pur_bill_store_chgs =new ArrayList<Pur_bill_store_chgs>();
						
						pur_bill_store_chgs.addAll(pur_bill_store_chgsRepository.purBillstorechargesList(op.get().getPur_bill_id()));
						String charge_name_ac[]=new String[pur_bill_store_chgs.size()];
					    String store_amount[]=new String[pur_bill_store_chgs.size()];
					    String purodrid="";
					    
					    
					    if(op.get().getReferance_type().compareToIgnoreCase("PURCHASE ORDER")==0) {
					    	purodrid=pur_OrderRepository.getStoreChargesdirectPoid(op.get().getReferance_id());				 		 
					    	}
					    else  if(op.get().getReferance_type().compareToIgnoreCase("OPEN GRN")==0) 
					    {
					    	purodrid="NA";
					    }
				 		 else 
				 		 {
				 			purodrid=pur_OrderRepository.getStoreChargesPoid(op.get().getReferance_id());
				 		 }
					 
						if(op.get().getStore_charges().compareToIgnoreCase("NA")!=0) 
						{
							
						double storecgstamt=0.00,storesgstamt=0.00,storeigstamt=0.00;
							 for(int i=0;i<pur_bill_store_chgs.size();i++) 
						        {
								 charge_name_ac[i]=pur_bill_store_chgs.get(i).getCharges_acc();
								 store_amount[i]=""+pur_bill_store_chgs.get(i).getStore_amount();
								 storecgstamt+=pur_bill_store_chgs.get(i).getStore_cgst();
								 storesgstamt+=pur_bill_store_chgs.get(i).getStore_sgst();
								 storeigstamt+=pur_bill_store_chgs.get(i).getStore_igst();
						        }
							 
							 storechargesstatus=true;
							 output=tallystore.SendToTally(op.get().getPur_bill_no(),op.get().getBill_date(),
										partyname,String.valueOf(op.get().getNet_payable_party()),
										String.valueOf(op.get().getRoundoff_amt()),roundoffglaccount,  
										item_name,item_amount,
										item_rate,item_passeditemqty,item_qty, 
										
										item_total,op.get().getVehicleno(),
										itemSubGroupName,rounfoffdrcr,roundoffstatus,includecharge_matrix,item_name_ledger,date
										,addplus,addminus,add_plus_ledgername,add_minus_ledgername,add_plus_amount,add_minus_amount,supplieraddress
										,supp_ref_doc_date,supp_ref_docno,purchaseinvoiceno,supplierstate
										,discountamount,discountledger,discountstat,gst_valid,gststatus,cgstledger,sgstledger,igstledger,
										 cgstamount + storecgstamt,sgstamount+storesgstamt,igstamount+storeigstamt,pincode,gstinno,charge_name_ac,store_amount,storechargesstatus,
										 packing_qty,packing_uom,itempricebaseon,classfied_name.substring(0, classfied_name.length()-1),purodrid);
							
						}
						else 
						{
							
							 output=tallystore.SendToTally(op.get().getPur_bill_no(),op.get().getBill_date(),
										partyname,String.valueOf(op.get().getNet_payable_party()),
										String.valueOf(op.get().getRoundoff_amt()),roundoffglaccount,  
										item_name,item_amount,
										item_rate,item_passeditemqty,item_qty, 
										
										item_total,op.get().getVehicleno(),
										itemSubGroupName,rounfoffdrcr,roundoffstatus,includecharge_matrix,item_name_ledger,date
										,addplus,addminus,add_plus_ledgername,add_minus_ledgername,add_plus_amount,add_minus_amount,supplieraddress
										,supp_ref_doc_date,supp_ref_docno,purchaseinvoiceno,supplierstate
										,discountamount,discountledger,discountstat,gst_valid,gststatus,cgstledger,sgstledger,igstledger,
										 cgstamount,sgstamount,igstamount,pincode,gstinno,charge_name_ac,store_amount,storechargesstatus,
										 packing_qty,packing_uom,itempricebaseon,classfied_name.substring(0, classfied_name.length()-1),purodrid);
							
						}
					}
				 
			 }
			 else 
			 {
				 List<Object[]> unloaddeatils=new ArrayList<Object[]>();
			        unloaddeatils.addAll(pur_Bill_app_chgsRepository.purBillAppChargesview(op.get().getPur_bill_id()));	        
			        List<PurchasebillviewchargeDTO>chargeMatrixDetails = new ArrayList<PurchasebillviewchargeDTO>();
			        for(final Object o : unloaddeatils)
				    {
				        Object[] obj = (Object[]) o;
				        
				        PurchasebillviewchargeDTO newpo = new PurchasebillviewchargeDTO();
				        newpo.setCharges_name(obj[0].toString());
				        newpo.setAdd_less(obj[2].toString());
				        newpo.setAmount(Double.parseDouble(obj[1].toString()));	
				        chargeMatrixDetails.add(newpo);			    }
			        
			        
			        //SELECT ledgername AS charges_name,SUM(amount) AS amount,add_less FROM  purchasebillcharges WHERE pur_bill_id='PB01338' GROUP BY ledgername,add_less
				    String charge_name[]=new String[chargeMatrixDetails.size()];
			        String charge_amount[]=new String[chargeMatrixDetails.size()];
			        String charge_add_less[]=new String[chargeMatrixDetails.size()];
			        for(int i=0;i<chargeMatrixDetails.size();i++) 
			        {
			        	if(includecharge_matrix ==true) 
			        	{
			        		//charge_name[i]=chargeMatrixDetails.get(i).getCharges_name();
			        	//	System.out.println("over here charge ");
			        	//	charge_name[i]=ledgermasterRepository.getLedgerDetails(charge_masterRepository.getchargeaccount(op.get().getApp_chgs_id(),chargeMatrixDetails.get(i).getCharges_name()).getCharge_ac()).getLedgername();	
			        		charge_name[i]=chargeMatrixDetails.get(i).getCharges_name();
			        	}
			        	else 
			        	{
			        	//	System.out.println("over here charge else ");
			        		charge_name[i]=chargeMatrixDetails.get(i).getCharges_name();
			        	}
			        	//charge_name[i]=chargeMatrixDetails.get(i).getCharges_name();
			        	
			        	charge_amount[i]=String.valueOf(chargeMatrixDetails.get(i).getAmount());
			        	charge_add_less[i]=chargeMatrixDetails.get(i).getAdd_less();
			        }
			
			 
				if(pur_BillRepository.getdoublelength(op.get().getPur_bill_id())>10) 
				{
					
					
					
					BigDecimal a = new BigDecimal(op.get().getNet_payable_party());
					a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);

				    System.out.println("over here 12 " + a);
				    String netpayableamt=a.toString();
					output=tally.SendToTally(op.get().getPur_bill_no(),op.get().getBill_date(),
							partyname,netpayableamt,
							String.valueOf(op.get().getRoundoff_amt()),roundoffglaccount,  
							item_name,item_amount,
							item_rate,item_passeditemqty,item_qty, 
							charge_name,charge_amount,charge_add_less,
							item_total,op.get().getVehicleno(),
							itemSubGroupName,rounfoffdrcr,roundoffstatus,includecharge_matrix,item_name_ledger,date
							,addplus,addminus,add_plus_ledgername,add_minus_ledgername,add_plus_amount,add_minus_amount,supplieraddress
							,supp_ref_doc_date,supp_ref_docno,purchaseinvoiceno,supplierstate
							,discountamount,discountledger,discountstat,gst_valid,gststatus,cgstledger,sgstledger,igstledger,
							 cgstamount,sgstamount,igstamount,pincode,gstinno);
			        
					
				
				}
				else 
				{
					output=tally.SendToTally(op.get().getPur_bill_no(),op.get().getBill_date(),
							partyname,String.valueOf(op.get().getNet_payable_party()),
							String.valueOf(op.get().getRoundoff_amt()),roundoffglaccount,  
							item_name,item_amount,
							item_rate,item_passeditemqty,item_qty, 
							charge_name,charge_amount,charge_add_less,
							item_total,op.get().getVehicleno(),
							itemSubGroupName,rounfoffdrcr,roundoffstatus,includecharge_matrix,item_name_ledger,date
							,addplus,addminus,add_plus_ledgername,add_minus_ledgername,add_plus_amount,add_minus_amount,supplieraddress
							,supp_ref_doc_date,supp_ref_docno,purchaseinvoiceno,supplierstate
							,discountamount,discountledger,discountstat,gst_valid,gststatus,cgstledger,sgstledger,igstledger,
							 cgstamount,sgstamount,igstamount,pincode,gstinno
							);
			        
					
				
				}
			 
			 
			 }	
				
				//System.out.println(" output :: "+output);
				String [] splitoutput = output.split("\\|\\|");
				//System.out.println(splitoutput[0] +" / " + splitoutput[1]+"/"+id);
				
				pur_BillRepository.exportuomtally(id,splitoutput[0],Integer.parseInt(splitoutput[1]));
			 	//pur_BillRepository.exportuomtally(id,"OK",1);
			}
			catch(Exception e)
			{
			
				System.out.println(e);
			}
			
		//	Custom_uom_master custMaster=op.get();
		
			Optional<Pur_Bill> op1=this.pur_BillRepository.findById(id);
			System.out.println(op.get());
			
			return op1.get();
		}
	 
	 @Transactional
	 public Pur_Bill accountpostingPurchaseBillundo(long id) 
		{
		 	pur_BillRepository.exportuomtallyundo(id);
			
			Optional<Pur_Bill> op1=pur_BillRepository.findById(id);
			//System.out.println("CHECK :: "+op1.get());
			
			return op1.get();
		}
	 
	 	@Transactional
		public Supp_bussiness_partner accountpostingUndoPurchaseBill(long id) 
		{
		
			try  
			{
				Optional<Supp_bussiness_partner> op=this.supp_bussiness_partnerRepository.findById(id);
				LocalDateTime ldt = LocalDateTime.now();
				supp_bussiness_partnerRepository.exportuomtallyReverse(id,op.get().getInserted_by(),ldt);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			Optional<Supp_bussiness_partner> op1=this.supp_bussiness_partnerRepository.findById(id);
			System.out.println(op1.get());
			
			return op1.get();
		}
	 	
	 	@Transactional
		public Pur_Bill deletePurchaseBill(Pur_Bill pur_Bill,Long id) 
	 	{
			Optional<Pur_Bill> op = pur_BillRepository.findById(id);
			
			LocalDateTime ldt = LocalDateTime.now();
			Pur_Bill purbill=op.get();
			
			purbill.setModified_type("DELETED");
			purbill.setInserted_by(op.get().getInserted_by());
			purbill.setInserted_on(op.get().getInserted_on());
			purbill.setUpdated_by(op.get().getUpdated_by());
			purbill.setUpdated_on(op.get().getUpdated_on());
			purbill.setDeleted_by(userRepository.getUserDetails(purbill.getUsername()).getName());
			purbill.setDeleted_on(ldt);
			
			pur_Bill_Item_DetailsRepository.pur_Bill_Item_DetailsUpdate(op.get().getPur_bill_id());
			
			pur_Bill_Musterroll_DetailsRepository.pur_Bill_Musterroll_DetailsUpdate(op.get().getPur_bill_id());
			
			pur_Bill_Tax_InfoRepository.pur_Bill_Tax_InfoUpdate(op.get().getPur_bill_id());
			
			pur_Bill_Broker_DetailsRepository.pur_Bill_Broker_DetailsUpdate(op.get().getPur_bill_id());
			
			pur_Bill_Bp_DetailsRepository.pur_Bill_Bp_DetailsUpdate(op.get().getPur_bill_id());
			
			pur_Bill_DocsRepository.pur_Bill_DocsUpdate(op.get().getPur_bill_id());
			
			pur_Bill_Account_InfoRepository.pur_Bill_Account_InfoUpdate(op.get().getPur_bill_id());
			
			pur_Bill_app_chgsRepository.pur_Bill_app_chgsUpdate(op.get().getPur_bill_id());
			
			pur_bill_store_chgsRepository.pur_Bill_store_chgsUpdate(op.get().getPur_bill_id());
			
			
			pur_good_receiptRepository.getrevertbilldelete(op.get().getReferance_id());
			
			purchase_item_groupwise_summRepository.deleteItem_groupwise_summ(op.get().getPur_bill_id());
			
		purchase_item_groupwise_taxsummRepository.deleteItem_groupwise_taxsumm(op.get().getPur_bill_id());
			
		purchase_item_groupwise_hsnsummRepository.deleteItem_groupwise_hsnsumm(op.get().getPur_bill_id());
			
			
			
			
			return pur_BillRepository.save(purbill);	
	 	}
	 	
	 	public List<Pur_BillDTO> getpurBillDataList1(String currDate,String finyear)
		{
		 	List<Pur_Bill> modelList =new ArrayList<Pur_Bill>();
		 	String tablename="pur_bill",party_name="supplier_name",order_no="pur_bill_no",order_date="bill_date",purchase_type="purchase_type";
			String orderno="",supplier_name1="",pur_type1="";
			
			modelList.addAll(pur_BillRepository.getsearchdata(tablename,party_name,order_no,order_date,purchase_type,orderno,currDate,currDate,supplier_name1,pur_type1,finyear));
			
			
			
			Type listType = new TypeToken<List<Pur_BillDTO>>() {}.getType();
			List<Pur_BillDTO> advList = new ModelMapper().map(modelList,listType);
			
			 for(int i=0;i<advList.size();i++) {
					
			    	if(advList.get(i).isItem_type() == true) 
			    	{
			    		advList.get(i).setGrn_for("Material");
			    	}
			    	else 
			    	{
			    		advList.get(i).setGrn_for("Services");
			    	}
					
				}
			
			return advList;
		}
		public List<Pur_BillDTO> getpurBillDataList(String currDate,String finyear)
		{
		 	List<Pur_Bill> modelList =new ArrayList<Pur_Bill>();
		 	String tablename="pur_bill",party_name="supplier_name",order_no="pur_bill_no",order_date="bill_date";
			String orderno="",supplier_name1="";
			
			//modelList.addAll(pur_BillRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,currDate,currDate,supplier_name1,finyear));
			
			modelList.addAll(pur_BillRepository.getPurBillNocurrentdate(currDate,finyear));
			
			Type listType = new TypeToken<List<Pur_BillDTO>>() {}.getType();
			List<Pur_BillDTO> advList = new ModelMapper().map(modelList,listType);
			
			 for(int i=0;i<advList.size();i++) {
					
			    	if(advList.get(i).isItem_type() == true) 
			    	{
			    		advList.get(i).setGrn_for("Material");
			    	}
			    	else 
			    	{
			    		advList.get(i).setGrn_for("Services");
			    	}
					
				}
			
			return advList;
		}
	 	
	 	public List<Purchase_item_groupwise_taxsumm> getpurBillInvTaxSum(String purbillid) 
	 	{
	 		List<Purchase_item_groupwise_taxsumm> taxdata=new ArrayList<Purchase_item_groupwise_taxsumm>();
	 				
	 		taxdata.addAll(purchase_item_groupwise_taxsummRepository.getdetails(purbillid));
	 		
	 		return taxdata;
	 		
	 	}
	 	
	 	public List<Map<String, Object>> getPurBillNewReport(String fromdate,String todate,String finyear,String po_type,String supplier_name)
	 	{
	 		List<Tuple> purbilllist=new ArrayList<Tuple>();
	 		if(po_type.compareToIgnoreCase("All") == 0 && supplier_name.compareToIgnoreCase("NONAME") == 0)
	 		{
	 			purbilllist.addAll(pur_BillRepository.getPurBillNewReport(fromdate,todate));
	 		}
	 		else if(po_type.compareToIgnoreCase("All") == 0)
	 		{
	 			purbilllist.addAll(pur_BillRepository.getPurBillNewReportwithSupplier(fromdate,todate,supplier_name));
	 		}
	 		else if(supplier_name.compareToIgnoreCase("NONAME") == 0)
	 		{
	 			purbilllist.addAll(pur_BillRepository.getPurBillNewReportwithPOtype(fromdate,todate,po_type));
	 		}
	 		else
	 		{
	 			purbilllist.addAll(pur_BillRepository.getPurBillNewReportwithPOtypeSupplier(fromdate,todate,po_type,supplier_name));
	 		}
	     	
	     	List<Map<String, Object>> finallist=new ArrayList<Map<String, Object>>();
	     	purbilllist.forEach(tuple -> {
	     		Map<String, Object> values = new LinkedHashMap<>();
	             tuple.getElements().forEach(
	                 element -> {
	                 	
	                     values.put(element.getAlias(), tuple.get(element));
	                 }
	             );
	             finallist.add(values);
	         });
	     
	     	return  finallist;
	 		
	 	}
	 	
	 	
	 	public List<Map<String, Object>> getPurBillbalanceNewReport(String fromdate,String todate,String supplier_name,String finyear,String po_type)
	 	{
	 		List<Map<String, Object>> finallist=new ArrayList<Map<String, Object>>();
	 		
	 		if(supplier_name.compareToIgnoreCase("NONAME") == 0 && po_type.compareToIgnoreCase("All") == 0) 
	 		{
	 			finallist.addAll(pur_BillRepository.getPurBillbalanceNewReportwithoutsupplierPOtype(fromdate,todate));	// ,finyear
			}
	 		else if(po_type.compareToIgnoreCase("All") == 0)
	 		{
	 			finallist.addAll(pur_BillRepository.getPurBillbalanceNewReportwithsupplier(fromdate,todate,supplier_name));		// ,finyear
	 		}
	 		else if(supplier_name.compareToIgnoreCase("NONAME") == 0)
	 		{
	 			finallist.addAll(pur_BillRepository.getPurBillbalanceNewReportwithPOtype(fromdate,todate,po_type));
	 		}
	 		else
	 		{
	 			finallist.addAll(pur_BillRepository.getPurBillbalanceNewReportwithsupplierPOtype(fromdate,todate,supplier_name,po_type));
	 		}
			
	 		return finallist;
	 		
	 	}
	 	
	 	 public List<Map<String, Object>> getPurchaseBillmiscreport(String business_unit,String catagory,String fromdate,String todate,String supplier_name,String ven_code_name)
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
				 modelList.addAll(pur_BillRepository.getPurchaseBillmiscreportbroker(business_unit,fromdate,todate,broker));
			 }
			 else if(catagory.compareToIgnoreCase("Partywise") == 0) 
			 {
				 String multiSupplier[]=supplier_name.split(",");
		 			ArrayList<String> supplier=new ArrayList<String>();
		 			for(int i=0;i<multiSupplier.length;i++)
		 			{
		 				supplier.add(multiSupplier[i]);
		 			}
		 		//System.out.println("supplier::"+supplier);
				 modelList.addAll(pur_BillRepository.getPurchaseBillmiscreportparty(business_unit,fromdate,todate,supplier));
			 }
			 else 
			 {
				 modelList.addAll(pur_BillRepository.getPurchaseBillmiscreportall(business_unit,fromdate,todate));
			 }
			 
			 return modelList;
		 }
	 	
	 	public List<Map<String, Object>> purchaseBillSupplierNamesList(String company,String fromdate,String todate,String business_unit)
		 {
			 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
			 
			 modelList.addAll(pur_BillRepository.purchaseBillSupplierNamesList(company,fromdate,todate,business_unit));
			
			 return modelList;
		 }
	 	
	 	public List<Map<String, Object>> purchaseBillBrokerNamesList(String company,String fromdate,String todate,String business_unit)
		 {
			 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
			 
			 modelList.addAll(pur_BillRepository.purchaseBillBrokerNamesList(company,fromdate,todate,business_unit));
			
			 return modelList;
		 }
	 	
	 	public List<Map<String, Object>> getPurBillReport(String fromdate,String todate)
	 	{
	 		return pur_BillRepository.PurBillReport(fromdate,todate);
	 	}
	 	
	 	public List<LinkedHashMap<String, Object>> gettransactionalReport(String fromdate,String todate,String catagory)
	 	//public List<LinkedHashMap<String, Object>> gettransactionalReport(String fromdate,String todate,String catagory)
	 	{
	 		
	 		//List<LinkedHashMap<String, Object>> reports = new ArrayList<>();
	 		List<Tuple> reports = new ArrayList<>();

	 		if(catagory.compareToIgnoreCase("Saleinvoice")==0) 
	 		{
	 			reports.addAll(pur_BillRepository.getsaleinvoicereports(fromdate,todate)) ;
	 		}
	 		if(catagory.compareToIgnoreCase("Purchasebill")==0) 
	 		{
	 			//reports.addAll(pur_BillRepository.PurBillReport(fromdate,todate)) ;
	 		}
	 		if(catagory.compareToIgnoreCase("CreditNote")==0) 
	 		{
	 			//reports.addAll(pur_BillRepository.creditnoteReport(fromdate,todate)) ;
	 		}
	 		
	 		List<LinkedHashMap<String, Object>> result = convertTuplesToMap(reports);
	 		
	 		return result;
	 	}
	 	
	 	public List<LinkedHashMap<String, Object>> getstocktrackingReport(String fromdate,String todate,String catagory,String startdate)
	 	{
	 		
	 		List<Tuple> reports = new ArrayList<>();
	 		if(catagory.compareToIgnoreCase("rawmaterial")==0) 
	 		{
	 			reports.addAll(pur_BillRepository.getstocktrackingReport(fromdate,todate)) ;
	 		}
	 		else if(catagory.compareToIgnoreCase("finishedgoods")==0) 
	 		{
	 			reports.addAll(pur_BillRepository.getstocktrackingReportfinshed(fromdate,todate)) ;
	 		}
	 		else if(catagory.compareToIgnoreCase("packingitems")==0) 
	 		{
	 			reports.addAll(pur_BillRepository.getstocktrackingReportpackingitem(fromdate,todate)) ;
	 		}
	 		
	 		
	 		List<LinkedHashMap<String, Object>> result = convertTuplesToMap(reports);
	 		return result;
	 	}
	 	
	 	
	 	public static List<LinkedHashMap<String, Object>> convertTuplesToMap(List<Tuple> tuples) {
	 	    List<LinkedHashMap<String, Object>> result = new ArrayList<>();
	 	    for (Tuple single : tuples) {
	 	    	LinkedHashMap<String, Object> tempMap = new LinkedHashMap<>();
	 	        for (TupleElement<?> key : single.getElements()) {
	 	            tempMap.put(key.getAlias(), single.get(key));
	 	            //System.out.println(key.getAlias()+" // "+single.get(key));
	 	        }
	 	        result.add(tempMap);
	 	    }
	 	    return result;
	 	}
	 	
	 	
	 	
		public List<Map<String, Object>> getstocktrackingReport2(String fromdate,String todate,String catagory,String startdate)
	 	{
	 		return pur_BillRepository.getstocktrackingReport2(fromdate,todate,startdate,catagory);
	 	}
	 	
	 	public List<Map<String, Object>> getalltransactionfromitem(String itemcode,String packingcode,String fromdate,String todate,String catagory)
	 	{
	 		
	 		List<Map<String, Object>> reports= new ArrayList<>();
	 		if(catagory.compareToIgnoreCase("rawmaterial")==0) 
	 		{
	 			reports.addAll(pur_BillRepository.allgrnreports(itemcode,packingcode,fromdate,todate));
		 		reports.addAll(pur_BillRepository.allprodreports(itemcode,packingcode,fromdate,todate));
	 		}
	 		else 
	 		{
	 			reports.addAll(pur_BillRepository.allchallanreports(itemcode,packingcode,fromdate,todate));
		 		reports.addAll(pur_BillRepository.allprodoutreports(itemcode,packingcode,fromdate,todate));
		 		reports.addAll(pur_BillRepository.allSALESRreports(itemcode,packingcode,fromdate,todate));
		 		reports.addAll(pur_BillRepository.allproductionspecialoutputRreports(itemcode,packingcode,fromdate,todate));
	 		}
	 	
	 		reports.sort(Comparator.comparing(m->(String) m.get("d_ate"),Comparator.nullsLast(Comparator.reverseOrder())));
	 		//reports.stream().sorted(Comparator.comparing(map -> (String)map.get("d_ate"))).collect(Collectors.toList());
	 		
	 		return reports;
	 	}
	 	
	 public List<Map<String, Object>> getBillThroughGRNId(String grnid)
	 	{ 
		  return pur_BillRepository.getBillThroughGRNId(grnid);
	 	}
	 
	 public Map<String, Object> getSuppliertdsStatDtls(String suppid,String financial_year)
	 {
		 
		 return pur_BillRepository.getSuppliertdsStatDtls(suppid,financial_year);
	 }
		
		public List<Map<String, Object>> getWhPeriQCReport(String fromdate, String todate) {
				return pur_BillRepository.getWhPeriQCReport(fromdate, todate);//based on unloadadvice
		}
		
		public List<Map<String, Object>> getWhQCReport(String fromdate, String todate, String process) {
			if(process.compareToIgnoreCase("All")==0)
			{
				return pur_good_receiptRepository.getAllWhQCReport(fromdate, todate);
			}
			else
			{
				return pur_good_receiptRepository.getWhQCReport(fromdate, todate, process);//based on process
			}
		}
	 	
}
