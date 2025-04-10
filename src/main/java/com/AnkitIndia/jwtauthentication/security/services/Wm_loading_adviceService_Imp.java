package com.AnkitIndia.jwtauthentication.security.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.AnkitIndia.Utility.FileUtil;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_transaction;
import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_Party_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_Shipment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_bp_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_broker_dtls;
//import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_doc_attch;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_driver_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_itm_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_trans_info;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.repository.BinMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challanRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challan_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Loading_pointRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_transactionRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_TransferRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.WarehouseMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_adviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_advice_Party_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_advice_Shipment_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_advice_bp_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_advice_broker_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_advice_doc_attchRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_advice_driver_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_advice_itm_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_advice_trans_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_OrderDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Item_Dtls_for_jobworkDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_bp_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_broker_dtlsDTO;
//import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_doc_attchDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_driver_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_itm_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Item_DtlsDTO;

@Service
public class Wm_loading_adviceService_Imp implements Wm_loading_adviceService{
	
	@Autowired
	Wm_loading_adviceRepository wm_loading_adviceRepository;
	
	@Autowired
	Wm_loading_advice_Shipment_DtlsRepository wm_loading_advice_Shipment_DtlsRepository;
	
	@Autowired
	Wm_loading_advice_trans_infoRepository wm_loading_advice_trans_infoRepository;
	
	@Autowired
	Wm_loading_advice_driver_dtlsRepository wm_loading_advice_driver_dtlsRepository;
	
	@Autowired
	Wm_loading_advice_bp_dtlsRepository wm_loading_advice_bp_dtlsRepository;
	
	@Autowired
	Wm_loading_advice_itm_dtlsRepository wm_loading_advice_itm_dtlsRepository;
	
	@Autowired
	Wm_loading_advice_doc_attchRepository wm_loading_advice_doc_attchRepository;
	
	@Autowired
	Wm_loading_advice_broker_dtlsRepository wm_loading_advice_broker_dtlsRepository;
	
	@Autowired
	Wm_loading_advice_Party_DtlsRepository wm_loading_advice_Party_DtlsRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	WarehouseMasterRepository warehouseMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Loading_pointRepository loading_pointRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	Sales_OrderRepository sales_OrderRepository;
	
	@Autowired
	Stock_TransferRepository stock_TransferRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Pur_return_approval_noteRepository pur_return_approval_noteRepository;
	
	@Autowired
	Sales_transactionRepository sales_transactionRepository;
	
	@Autowired
	BinMasterRepository binMasterRepository;
	
	@Autowired
	Delivery_challanRepository delivery_challanRepository;
	
	@Autowired
	Delivery_challan_Item_DtlsRepository delivery_challan_Item_DtlsRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	@Autowired
	Trans_bussiness_partnerRepository trans_bussiness_partnerRepository;
	
	public SequenceIdDTO getLASequenceId(String prefix,String orderdate,String type)
	{
		int slno=0;
		String sno=wm_loading_adviceRepository.countLoadingAdv(orderdate,type);
		if(type.compareTo("Sale")==0) {type="SO";}
		if(type.compareTo("Purchase Return")==0) {type="PR";}
		if(type.compareTo("Stock Transfer")==0) {type="ST";}
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		prefix=prefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		//System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public SequenceIdDTO getLASequenceIdWarehouse(String prefix,String orderdate)
	{
		int slno=0;
		String sno=wm_loading_adviceRepository.countLoadingAdvwarehouse(orderdate,"SW");
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		prefix=prefix+"-SW-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		//System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	//public Wm_loading_advice save(Wm_loading_advice wm_loading_advice) 
	//{
	public Wm_loading_advice save(Wm_loading_advice wm_loading_advice,MultipartFile files[]) throws IOException
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(wm_loading_adviceRepository.countId() != null ) {
			slno=Long.parseLong(wm_loading_adviceRepository.countId());
		}
		String prefix="WLA";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		
		wm_loading_advice.setAdvice_id(gen_sno);
		wm_loading_advice.setWeighment_id("0");
		wm_loading_advice.setLoading_status(0);
		wm_loading_advice.setWeighment_status(0);
		wm_loading_advice.setStk_trans_chln_status("No");
		wm_loading_advice.setStk_trans_inv_status("No");
		wm_loading_advice.setUnload_status("Not Done");
		
		/*Start checking transaction no for unique */
		//System.err.println("First:>>"+wm_loading_advice.getAdvice_no());
		long nslno=0;String tprefix="LA",type="";
		if(wm_loading_advice.getAdvice_type().compareTo("Sale")==0)
		{
			if(sales_OrderRepository.getSalesOrderDetails(wm_loading_advice.getReferance_id()).getInv_type().compareTo("INV00005")==0) 
			{
				
                String tsno=wm_loading_adviceRepository.countLoadingAdvwarehouse(wm_loading_advice.getAdvice_date(),"SW");
			
						
				if(tsno != null ) {
					nslno=Integer.parseInt(tsno);
				}
				String date[] = wm_loading_advice.getAdvice_date().split("-");
				tprefix=tprefix+"-SW-"+date[2]+date[1]+date[0].substring(2,4)+"-";
				String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
				wm_loading_advice.setAdvice_no(gen_tslno);
				
			}
			else 
			{
				String tsno=wm_loading_adviceRepository.countLoadingAdv(wm_loading_advice.getAdvice_date(),wm_loading_advice.getAdvice_type());
				
				
				if(wm_loading_advice.getAdvice_type().compareTo("Sale")==0) {type="SO";}
				if(wm_loading_advice.getAdvice_type().compareTo("Purchase Return")==0) {type="PR";}
				if(wm_loading_advice.getAdvice_type().compareTo("Stock Transfer")==0) {type="ST";}
						
				if(tsno != null ) {
					nslno=Integer.parseInt(tsno);
				}
				String date[] = wm_loading_advice.getAdvice_date().split("-");
				tprefix=tprefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
				String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
				wm_loading_advice.setAdvice_no(gen_tslno);
				
			}
		}
		else 
		{
			String tsno=wm_loading_adviceRepository.countLoadingAdv(wm_loading_advice.getAdvice_date(),wm_loading_advice.getAdvice_type());
			if(wm_loading_advice.getAdvice_type().compareTo("Sale")==0) {type="SO";}
			if(wm_loading_advice.getAdvice_type().compareTo("Purchase Return")==0) {type="PR";}
			if(wm_loading_advice.getAdvice_type().compareTo("Stock Transfer")==0) {type="ST";}
					
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			String date[] = wm_loading_advice.getAdvice_date().split("-");
			tprefix=tprefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
			String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
			wm_loading_advice.setAdvice_no(gen_tslno);
		}
		
		/*String tsno=wm_loading_adviceRepository.countLoadingAdv(wm_loading_advice.getAdvice_date(),wm_loading_advice.getAdvice_type());
		
		
		if(wm_loading_advice.getAdvice_type().compareTo("Sale")==0) {type="SO";}
		if(wm_loading_advice.getAdvice_type().compareTo("Purchase Return")==0) {type="PR";}
		if(wm_loading_advice.getAdvice_type().compareTo("Stock Transfer")==0) {type="ST";}
				
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = wm_loading_advice.getAdvice_date().split("-");
		tprefix=tprefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		wm_loading_advice.setAdvice_no(gen_tslno);
		*/
		//System.err.println("Last:>>>"+wm_loading_advice.getAdvice_no());
		/*End checking transaction no for unique */
		
		if(Utility.isNullOrEmpty(wm_loading_advice.getVehicle_id())) {
			wm_loading_advice.setVehicle_no(vehicleMasterRepository.getVehicleDetails(wm_loading_advice.getVehicle_id()).getVehicle_no());
		}else {wm_loading_advice.setVehicle_no("None");}
		
		
		if(wm_loading_advice.getAdvice_type().compareTo("Purchase Return")==0) {
			//Update loading Adv Status
			pur_return_approval_noteRepository.updateLoadingStatus(wm_loading_advice.getReferance_id(),"Done");
			
			if(Utility.isNullOrEmpty(wm_loading_advice.getSupplier())) {
				wm_loading_advice.setSupplier_name(supp_bussiness_partnerRepository.getSupplierName(wm_loading_advice.getSupplier()).getBp_name());
			}else {wm_loading_advice.setSupplier_name("None");}
			
		}else {
			if(Utility.isNullOrEmpty(wm_loading_advice.getBus_partner())) {
				wm_loading_advice.setCustomer_name(cust_bussiness_partnerRepository.getCustomer(wm_loading_advice.getBus_partner()).getCp_name());
			}else {wm_loading_advice.setCustomer_name("None");}
			
			//Stk Trns
			if(Utility.isNullOrEmpty(wm_loading_advice.getSupplier())) {
				wm_loading_advice.setSupplier_name(supp_bussiness_partnerRepository.getSupplierName(wm_loading_advice.getSupplier()).getBp_name());
			}else {wm_loading_advice.setSupplier_name("None");}
		}
		
		if(wm_loading_advice.isJobwork()==false)
		{
			wm_loading_advice.setPur_cust_refdocno("0");
			wm_loading_advice.setPur_cust_refdocnoqty("0");
		}
		
		wm_loading_advice.setModified_type("INSERTED");
		wm_loading_advice.setInserted_by(userRepository.getUserDetails(wm_loading_advice.getUsername()).getName());
		wm_loading_advice.setInserted_on(ldt);
		wm_loading_advice.setUpdated_by("NA");
		wm_loading_advice.setUpdated_on(ldt);
		wm_loading_advice.setDeleted_by("NA");
		wm_loading_advice.setDeleted_on(ldt);
		wm_loading_advice.setAdvicedate(wm_loading_advice.getAdvice_date());
		wm_loading_advice.setAdviceno(wm_loading_advice.getAdvice_no());
		//new vehicle records table starts
		if(wm_loading_advice.isMultipleloading())
		{
			vehicleMasterRepository.updateStatus(wm_loading_advice.getVehicle_id(),false);
		}else {
			vehicleMasterRepository.updateStatus(wm_loading_advice.getVehicle_id(),true);//AFTER LOAD ADVICE THE VEHICLE IS NOT SHOWN IN LODING ADVICE VEHICLE LIST UNTILL 2ND WEIGHMENT COMPLETE
			vehicleMasterRepository.updateVehicleWeighmentLocation(wm_loading_advice.getVehicle_id(),wm_loading_advice.getWeight_bridge_location());
		}
		Vehicle_weighment_load_unload  vehicle_weighment_load_unload = new Vehicle_weighment_load_unload();
		
		vehicle_weighment_load_unload.setVehicle_no(wm_loading_advice.getVehicle_no());
		vehicle_weighment_load_unload.setVehicle_id(wm_loading_advice.getVehicle_id());
		vehicle_weighment_load_unload.setReference_id(wm_loading_advice.getAdvice_id());
		vehicle_weighment_load_unload.setRef_name("Load Advice");
		vehicle_weighment_load_unload.setCompany_id(wm_loading_advice.getCompany_id());
		vehicle_weighment_load_unload.setFin_year(wm_loading_advice.getFin_year());
		vehicle_weighment_load_unload.setUsername(wm_loading_advice.getUsername());
		vehicle_weighment_load_unload.setModified_type("INSERTED");
		vehicle_weighment_load_unload.setInserted_by(userRepository.getUserDetails(wm_loading_advice.getUsername()).getName());
		vehicle_weighment_load_unload.setInserted_on(ldt);
		vehicle_weighment_load_unload.setUpdated_by("NA");
		vehicle_weighment_load_unload.setUpdated_on(ldt);
		vehicle_weighment_load_unload.setDeleted_by("NA");
		vehicle_weighment_load_unload.setDeleted_on(ldt);
		vehicle_weighment_load_unload.setRef_name_type(wm_loading_advice.getRef_doc_type());
		vehicle_weighment_load_unload.setGatepass_status("NA");
		vehicle_weighment_load_unload.setWeight_bridge_location(wm_loading_advice.getWeight_bridge_location());
		/*if(wm_loading_advice.getWeighment_status() == 0) 
		{
			vehicle_weighment_load_unload.setWe_req(false);
		}
		else 
		{
			vehicle_weighment_load_unload.setWe_req(true);
		}*/
		vehicle_weighment_load_unload.setWe_req(true);
		wm_loading_advice.setVehicle_weighment_load_unload(vehicle_weighment_load_unload);
//ends
		if(wm_loading_advice.isJobwork()==true)
		{
			wm_loading_advice.getWm_loading_advice_itm_dtls().clear();
			
			Set<Wm_loading_advice_Item_Dtls_for_jobwork> jobSet=new HashSet<Wm_loading_advice_Item_Dtls_for_jobwork>();
			jobSet.addAll(wm_loading_advice.getWm_loading_advice_Item_Dtls_for_jobwork());
			for(Wm_loading_advice_Item_Dtls_for_jobwork jobwork:jobSet) 
			{
				jobwork.setAdvice_id(gen_sno);
				jobwork.setAdvice_no(wm_loading_advice.getAdvice_no());
				
				if(Utility.isNullOrEmpty(jobwork.getJob_item())) {
					jobwork.setJob_item_name(item_masterRepository.itemNameById(jobwork.getJob_item()).getItem_name());
				}else{jobwork.setJob_item_name("0");};
				
				if(Utility.isNullOrEmpty(jobwork.getJob_packing())) {
					jobwork.setJob_packing_name(item_masterRepository.itemNameById(jobwork.getJob_packing()).getItem_name());
				}else{jobwork.setJob_packing_name("0");};
				
				jobwork.setOrder_id(wm_loading_advice.getReferance_id());
				
				jobwork.setCompany_id(wm_loading_advice.getCompany_id());
				jobwork.setFin_year(wm_loading_advice.getFin_year());
				jobwork.setModified_type("INSERTED");
				jobwork.setInserted_by(wm_loading_advice.getInserted_by());
				jobwork.setInserted_on(wm_loading_advice.getInserted_on());
				jobwork.setUpdated_by("NA");
				jobwork.setUpdated_on(ldt);
				jobwork.setDeleted_by("NA");
				jobwork.setDeleted_on(ldt);
			}
			wm_loading_advice.setWm_loading_advice_Item_Dtls_for_jobwork(jobSet);
		}
		else
		{
			//System.out.println(wm_loading_advice.getWm_loading_advice_Item_Dtls_for_jobwork().size());
			if(wm_loading_advice.getWm_loading_advice_Item_Dtls_for_jobwork().size()>0) 
			{
				wm_loading_advice.getWm_loading_advice_Item_Dtls_for_jobwork().clear();
			}
			//wm_loading_advice.getWm_loading_advice_Item_Dtls_for_jobwork().clear();
			
			Set<Wm_loading_advice_itm_dtls> itemDtlsSet=new HashSet<Wm_loading_advice_itm_dtls>();
			itemDtlsSet.addAll(wm_loading_advice.getWm_loading_advice_itm_dtls());
			for(Wm_loading_advice_itm_dtls itemDtls:itemDtlsSet) 
			{
				itemDtls.setAdvice_id(gen_sno);
				itemDtls.setAdvice_no(wm_loading_advice.getAdvice_no());
				
				if(Utility.isNullOrEmpty(itemDtls.getItem_code())) {
				itemDtls.setItem_name(item_masterRepository.itemNameById(itemDtls.getItem_code()).getItem_name());
				}else {itemDtls.setItem_name("NA");}
				
				if(Utility.isNullOrEmpty(itemDtls.getPacking())) {
					itemDtls.setPacking_name(item_masterRepository.itemNameById(itemDtls.getPacking()).getItem_name());
				}else {itemDtls.setPacking_name("NA");}
				
				if(Utility.isNullOrEmpty(itemDtls.getAlter_item_code())) {
					itemDtls.setAlter_item_name(item_masterRepository.itemNameById(itemDtls.getAlter_item_code()).getItem_name());
					}else {itemDtls.setAlter_item_name("NA");}
					
				if(Utility.isNullOrEmpty(itemDtls.getAlter_packing())) {
					itemDtls.setAlter_packing_name(item_masterRepository.itemNameById(itemDtls.getAlter_packing()).getItem_name());
				}else {itemDtls.setAlter_packing_name("NA");}
				
				if(Utility.isNullOrEmpty(itemDtls.getWarehouse())) {
					itemDtls.setWarehouse_name(warehouseMasterRepository.getWHNListbyCode(itemDtls.getWarehouse()).getWarehouse_name());
				}else {itemDtls.setWarehouse("0");itemDtls.setWarehouse_name("NA");}
				
				if(Utility.isNullOrEmpty(itemDtls.getStack_rack())) {
					itemDtls.setStack_rack_name(binMasterRepository.getBinDesc(itemDtls.getStack_rack()).getBin_description());
				}else {itemDtls.setStack_rack("0");itemDtls.setStack_rack_name("NA");}
				
				itemDtls.setOrder_id(wm_loading_advice.getReferance_id());
				
				
				
				itemDtls.setAlt_s_quantity(wm_loading_advice_itm_dtlsRepository.bagsforaltconvert(itemDtls.getItem_code(),itemDtls.getPacking(),itemDtls.getAlter_item_code(),itemDtls.getAlter_packing(),itemDtls.getS_quantity()));
				
				
				
				
				itemDtls.setCompany_id(wm_loading_advice.getCompany_id());
				itemDtls.setFin_year(wm_loading_advice.getFin_year());
				itemDtls.setModified_type("INSERTED");
				itemDtls.setInserted_by(wm_loading_advice.getInserted_by());
				itemDtls.setInserted_on(wm_loading_advice.getInserted_on());
				itemDtls.setUpdated_by("NA");
				itemDtls.setUpdated_on(ldt);
				itemDtls.setDeleted_by("NA");
				itemDtls.setDeleted_on(ldt);
			}
			wm_loading_advice.setWm_loading_advice_itm_dtls(itemDtlsSet);
			
			List<Sales_transaction> stkList=new ArrayList<Sales_transaction>();
			stkList.addAll(sales_transactionRepository.getSalesStocks(wm_loading_advice.getReferance_id()));
			
			stkList.forEach((sOrders) -> {
				itemDtlsSet.forEach((lOrders) -> {
					if(sOrders.getItem_id().compareTo(lOrders.getItem_code())==0 && sOrders.getPacking_id().compareTo(lOrders.getPacking())==0 && sOrders.getBusiness_unit().compareTo(wm_loading_advice.getB_unit())==0) {
						//Update Sales Transaction *********
						sales_transactionRepository.updateSales_transactions(wm_loading_advice.getReferance_id(), sOrders.getBusiness_unit(), sOrders.getItem_id(), sOrders.getPacking_id(),
								sOrders.getSales_item_qty()-lOrders.getQuantity(), sOrders.getSales_pack_qty()-Double.valueOf(lOrders.getS_quantity()),sOrders.getLoad_item_qty()+lOrders.getQuantity(),sOrders.getLoad_pack_qty()+Double.valueOf(lOrders.getS_quantity()),"INSERTED");
					}
				});
			});
		}
		
		
		//*****************Track Orders *****************************************************************
		
		//Update Sales Order Status
		if(wm_loading_advice.getRef_doc_type().compareToIgnoreCase("Sales Order")==0) 
		{
			if(wm_loading_advice.getReferance_id().substring(0,2).compareTo("SO")==0) 
			{
				sales_OrderRepository.updateSalesOrderDetails(wm_loading_advice.getReferance_id(),true);
			}
		}
		
		
		
		//update stock transfer order
		if(wm_loading_advice.getRef_doc_type().compareToIgnoreCase("Stock Transfer")==0)
		{
			stock_TransferRepository.updateLoadingAdvice(wm_loading_advice.getReferance_id());
		}
		
		//*****************Track Orders ******************************************************************
		
		Set<Wm_loading_advice_broker_dtls> brokerSet=new HashSet<Wm_loading_advice_broker_dtls>();
		brokerSet.addAll(wm_loading_advice.getWm_loading_advice_broker_dtls());
		for(Wm_loading_advice_broker_dtls brokerDtls:brokerSet) 
		{
			brokerDtls.setAdvice_id(gen_sno);
			brokerDtls.setAdvice_no(wm_loading_advice.getAdvice_no());
			brokerDtls.setCompany_id(wm_loading_advice.getCompany_id());
			brokerDtls.setFin_year(wm_loading_advice.getFin_year());
			brokerDtls.setModified_type("INSERTED");
			brokerDtls.setInserted_by(wm_loading_advice.getInserted_by());
			brokerDtls.setInserted_on(wm_loading_advice.getInserted_on());
			brokerDtls.setUpdated_by("NA");
			brokerDtls.setUpdated_on(ldt);
			brokerDtls.setDeleted_by("NA");
			brokerDtls.setDeleted_on(ldt);
		}
		wm_loading_advice.setWm_loading_advice_broker_dtls(brokerSet);

		Set<Wm_loading_advice_driver_dtls> driverDtlsSet=new HashSet<Wm_loading_advice_driver_dtls>();
		driverDtlsSet.add(wm_loading_advice.getWm_loading_advice_driver_dtls());
		for(Wm_loading_advice_driver_dtls driverDtls:driverDtlsSet) 
		{
			driverDtls.setAdvice_id(gen_sno);
			driverDtls.setAdvice_no(wm_loading_advice.getAdvice_no());
			driverDtls.setCompany_id(wm_loading_advice.getCompany_id());
			driverDtls.setFin_year(wm_loading_advice.getFin_year());
			driverDtls.setModified_type("INSERTED");
			driverDtls.setInserted_by(wm_loading_advice.getInserted_by());
			driverDtls.setInserted_on(wm_loading_advice.getInserted_on());
			driverDtls.setUpdated_by("NA");
			driverDtls.setUpdated_on(ldt);
			driverDtls.setDeleted_by("NA");
			driverDtls.setDeleted_on(ldt);
		}
		if(!driverDtlsSet.isEmpty())
		{
			wm_loading_advice.setWm_loading_advice_driver_dtls(driverDtlsSet.iterator().next());
		}

		Set<Wm_loading_advice_trans_info> transInfoSet=new HashSet<Wm_loading_advice_trans_info>();
		transInfoSet.add(wm_loading_advice.getWm_loading_advice_trans_info());
		for(Wm_loading_advice_trans_info transInfo:transInfoSet) 
		{
			transInfo.setAdvice_id(gen_sno);
			transInfo.setAdvice_no(wm_loading_advice.getAdvice_no());
			
			if(Utility.isNullOrEmpty(transInfo.getTransporter_name())) 
			{
				if(transInfo.getTransporter_name().compareToIgnoreCase("0")==0)
				{
					transInfo.setTrans_name("0");
				}
				else
				{
					transInfo.setTrans_name(trans_bussiness_partnerRepository.bpNameById(transInfo.getTransporter_name()).getBp_name());
				}
			}
			else{transInfo.setTrans_name("0");};
			
			transInfo.setCompany_id(wm_loading_advice.getCompany_id());
			transInfo.setFin_year(wm_loading_advice.getFin_year());
			transInfo.setModified_type("INSERTED");
			transInfo.setInserted_by(wm_loading_advice.getInserted_by());
			transInfo.setInserted_on(wm_loading_advice.getInserted_on());
			transInfo.setUpdated_by("NA");
			transInfo.setUpdated_on(ldt);
			transInfo.setDeleted_by("NA");
			transInfo.setDeleted_on(ldt);
		}
		if(!transInfoSet.isEmpty())
		{
			wm_loading_advice.setWm_loading_advice_trans_info(transInfoSet.iterator().next());
		}
		
		Set<Wm_loading_advice_bp_dtls> bpDtlsSet=new HashSet<Wm_loading_advice_bp_dtls>();
		bpDtlsSet.add(wm_loading_advice.getWm_loading_advice_bp_dtls());
		for(Wm_loading_advice_bp_dtls bpDtls:bpDtlsSet) 
		{
			bpDtls.setAdvice_id(gen_sno);
			bpDtls.setAdvice_no(wm_loading_advice.getAdvice_no());
			bpDtls.setCompany_id(wm_loading_advice.getCompany_id());
			bpDtls.setFin_year(wm_loading_advice.getFin_year());
			bpDtls.setModified_type("INSERTED");
			bpDtls.setInserted_by(wm_loading_advice.getInserted_by());
			bpDtls.setInserted_on(wm_loading_advice.getInserted_on());
			bpDtls.setUpdated_by("NA");
			bpDtls.setUpdated_on(ldt);
			bpDtls.setDeleted_by("NA");
			bpDtls.setDeleted_on(ldt);
		}
		if(!bpDtlsSet.isEmpty())
		{
			wm_loading_advice.setWm_loading_advice_bp_dtls(bpDtlsSet.iterator().next());
		}
		
		Set<Wm_loading_advice_doc_attch> docSet=new HashSet<Wm_loading_advice_doc_attch>();
		docSet.addAll(wm_loading_advice.getWm_loading_advice_doc_attch());
		int i=0;
		for(Wm_loading_advice_doc_attch doc:docSet) 
		{
			if(files.length > 0) 
			{
				try 
				{
					doc.setDoc_pdf(fileUpload(files[i],gen_sno+"_"));		
				i++;
				}
				catch (IOException e)
				{
					//System.out.println(e);
				}
				
			}
			//System.out.println("3 :: ");
			//here ends
			
			doc.setAdvice_id(gen_sno);
			
			doc.setAdvice_no(wm_loading_advice.getAdvice_no());
			doc.setCompany_id(wm_loading_advice.getCompany_id());
			doc.setFin_year(wm_loading_advice.getFin_year());
			doc.setModified_type("INSERTED");
			doc.setInserted_by(wm_loading_advice.getInserted_by());
			doc.setInserted_on(wm_loading_advice.getInserted_on());
			doc.setUpdated_by("NA");
			doc.setUpdated_on(ldt);
			doc.setDeleted_by("NA");
			doc.setDeleted_on(ldt);
		}
		wm_loading_advice.setWm_loading_advice_doc_attch(docSet);
		
		Set<Wm_loading_advice_Party_Dtls> partySet=new HashSet<Wm_loading_advice_Party_Dtls>();
		partySet.addAll(wm_loading_advice.getWm_loading_advice_Party_Dtls());
		for(Wm_loading_advice_Party_Dtls party:partySet) 
		{
			party.setAdvice_id(gen_sno);
			party.setAdvice_no(wm_loading_advice.getAdvice_no());
			party.setCompany_id(wm_loading_advice.getCompany_id());
			party.setFin_year(wm_loading_advice.getFin_year());
			party.setModified_type("INSERTED");
			party.setInserted_by(wm_loading_advice.getInserted_by());
			party.setInserted_on(wm_loading_advice.getInserted_on());
			party.setUpdated_by("NA");
			party.setUpdated_on(ldt);
			party.setDeleted_by("NA");
			party.setDeleted_on(ldt);
		}
		wm_loading_advice.setWm_loading_advice_Party_Dtls(partySet);
		
		Set<Wm_loading_advice_Shipment_Dtls> shipmentDtlsSet=new HashSet<Wm_loading_advice_Shipment_Dtls>();
		shipmentDtlsSet.add(wm_loading_advice.getWm_loading_advice_Shipment_Dtls());
		for(Wm_loading_advice_Shipment_Dtls shipmentDtls:shipmentDtlsSet) 
		{
			shipmentDtls.setAdvice_id(gen_sno);
			shipmentDtls.setAdvice_no(wm_loading_advice.getAdvice_no());
			if(Utility.isNullOrEmpty(shipmentDtls.getShip_addr()))
			{
				
			}
			else 
			{
				shipmentDtls.setShip_addr("0");
			}
			shipmentDtls.setCompany_id(wm_loading_advice.getCompany_id());
			shipmentDtls.setFin_year(wm_loading_advice.getFin_year());
			shipmentDtls.setModified_type("INSERTED");
			shipmentDtls.setInserted_by(wm_loading_advice.getInserted_by());
			shipmentDtls.setInserted_on(wm_loading_advice.getInserted_on());
			shipmentDtls.setUpdated_by(wm_loading_advice.getUpdated_by());
			shipmentDtls.setUpdated_on(wm_loading_advice.getUpdated_on());
			shipmentDtls.setDeleted_by("NA");
			shipmentDtls.setDeleted_on(ldt);
		}
		if(!shipmentDtlsSet.isEmpty())
		{
			wm_loading_advice.setWm_loading_advice_Shipment_Dtls(shipmentDtlsSet.iterator().next());
		}
		
		return wm_loading_adviceRepository.save(wm_loading_advice);
	}
		
	private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(FileUtil.folderPathload);
        if (! directory.exists()){
            directory.mkdir();
        }
    }
	
	@Transactional
	public  String fileUpload(@RequestParam("files") MultipartFile files,String fileName) throws IOException
	{
		 
				createDirIfNotExist();
			
				
		
		    String  files_name = FileUtil.folderPathload+fileName+files.getOriginalFilename();
		            	 
		            	 File convertFile = new File(FileUtil.folderPathload+fileName+files.getOriginalFilename());
		                 convertFile.createNewFile();
		                 FileOutputStream fout = new FileOutputStream(convertFile);
		                 fout.write(files.getBytes());
		                 fout.close();
		 
		            return files_name;
	}
	
	@Transactional
	public Wm_loading_advice update(Wm_loading_advice wm_loading_advice,Long id)
	{
		Optional<Wm_loading_advice> op = wm_loading_adviceRepository.findById(id);
		
		Wm_loading_advice lWm_loading_advice=new Wm_loading_advice();
		LocalDateTime ldt = LocalDateTime.now();
	//closed by tuhin 	on 21-11-2022	
	/*	if(op.get().getWeighment_status() != 0) 
		{
			System.err.println("Update is not Possible !!!");
			return lWm_loading_advice;
		}
		else 
		{
			
		*/
//closed by tuhin on 21-11-2022			
			System.err.println("Update is Continue !!!");
			wm_loading_advice.setAdvice_id(op.get().getAdvice_id());
			wm_loading_advice.setAdvice_no(op.get().getAdvice_no());
			wm_loading_advice.setWeighment_id(op.get().getWeighment_id());
			wm_loading_advice.setLoading_status(op.get().getLoading_status());
			wm_loading_advice.setWeighment_status(op.get().getWeighment_status());
			wm_loading_advice.setStk_trans_chln_status(op.get().getStk_trans_chln_status());
			wm_loading_advice.setStk_trans_inv_status(op.get().getStk_trans_inv_status());
			wm_loading_advice.setUnload_status(op.get().getUnload_status());
			wm_loading_advice.setPrice_term(op.get().getPrice_term());
			wm_loading_advice.setWeighment_status(op.get().getWeighment_status());
			wm_loading_advice.setWeighment_id(op.get().getWeighment_id());
			
			
			
			
			if(Utility.isNullOrEmpty(wm_loading_advice.getVehicle_id())) {
				wm_loading_advice.setVehicle_no(vehicleMasterRepository.getVehicleDetails(wm_loading_advice.getVehicle_id()).getVehicle_no());
			}else {wm_loading_advice.setVehicle_no("None");}
			
			if(wm_loading_advice.getAdvice_type().compareTo("Purchase Return")==0) {
				//Update loading Adv Status
				pur_return_approval_noteRepository.updateLoadingStatus(wm_loading_advice.getReferance_id(),"Done");
				
				if(Utility.isNullOrEmpty(wm_loading_advice.getSupplier())) {
					wm_loading_advice.setSupplier_name(supp_bussiness_partnerRepository.getSupplierName(wm_loading_advice.getSupplier()).getBp_name());
				}else {wm_loading_advice.setSupplier_name("None");}
				
			}else {
				if(Utility.isNullOrEmpty(wm_loading_advice.getBus_partner())) {
					wm_loading_advice.setCustomer_name(cust_bussiness_partnerRepository.getCustomer(wm_loading_advice.getBus_partner()).getCp_name());
				}else {wm_loading_advice.setCustomer_name("None");}
				
				//Stk Trns
				if(Utility.isNullOrEmpty(wm_loading_advice.getSupplier())) {
					wm_loading_advice.setSupplier_name(supp_bussiness_partnerRepository.getSupplierName(wm_loading_advice.getSupplier()).getBp_name());
				}else {wm_loading_advice.setSupplier_name("None");}
			}
			
			if(wm_loading_advice.isJobwork()==false)
			{
				wm_loading_advice.setPur_cust_refdocno("0");
				wm_loading_advice.setPur_cust_refdocnoqty("0");
			}
			
			wm_loading_advice.setModified_type("INSERTED");
			wm_loading_advice.setInserted_by(op.get().getInserted_by());
			wm_loading_advice.setInserted_on(op.get().getInserted_on());
			wm_loading_advice.setUpdated_by(userRepository.getUserDetails(wm_loading_advice.getUsername()).getName());
			wm_loading_advice.setUpdated_on(ldt);
			wm_loading_advice.setDeleted_by("NA");
			wm_loading_advice.setDeleted_on(ldt);
			wm_loading_advice.setAdvicedate(wm_loading_advice.getAdvice_date());
			wm_loading_advice.setAdviceno(wm_loading_advice.getAdvice_no());
			
			if(op.isPresent()) {
				wm_loading_advice.setId(id);
			}
			
			//new vehicle records table starts
			
			if(wm_loading_advice.isMultipleloading())
			{
				vehicleMasterRepository.updateStatus(wm_loading_advice.getVehicle_id(),false);
			}
			else 
			{
				vehicleMasterRepository.updateStatus(op.get().getVehicle_id(),false);//revert vehicle
				vehicleMasterRepository.updateVehicleWeighmentLocation(op.get().getVehicle_id(),"NA"); //Revert Kata location
				vehicleMasterRepository.updateStatus(wm_loading_advice.getVehicle_id(),true);//AFTER LOAD ADVICE THE VEHICLE IS NOT SHOWN IN LODING ADVICE VEHICLE LIST UNTILL 2ND WEIGHMENT COMPLETE
				vehicleMasterRepository.updateVehicleWeighmentLocation(wm_loading_advice.getVehicle_id(),wm_loading_advice.getWeight_bridge_location());//Set Weight bridge Location
			}
			
			Vehicle_weighment_load_unload  vehicle_weighment_load_unload_get =wm_loading_adviceRepository.getvehiclegstat(wm_loading_advice.getAdvice_id());
			
			
			wm_loading_adviceRepository.loadingVehicleUpdate(wm_loading_advice.getAdvice_id());
			Vehicle_weighment_load_unload  vehicle_weighment_load_unload = new Vehicle_weighment_load_unload();
			
			vehicle_weighment_load_unload.setVehicle_no(wm_loading_advice.getVehicle_no());
			vehicle_weighment_load_unload.setVehicle_id(wm_loading_advice.getVehicle_id());
			vehicle_weighment_load_unload.setReference_id(wm_loading_advice.getAdvice_id());
			vehicle_weighment_load_unload.setRef_name("Load Advice");
			vehicle_weighment_load_unload.setCompany_id(wm_loading_advice.getCompany_id());
			vehicle_weighment_load_unload.setFin_year(wm_loading_advice.getFin_year());
			vehicle_weighment_load_unload.setUsername(wm_loading_advice.getUsername());
			vehicle_weighment_load_unload.setModified_type("INSERTED");
			vehicle_weighment_load_unload.setInserted_by(userRepository.getUserDetails(wm_loading_advice.getUsername()).getName());
			vehicle_weighment_load_unload.setInserted_on(ldt);
			vehicle_weighment_load_unload.setUpdated_by("NA");
			vehicle_weighment_load_unload.setUpdated_on(ldt);
			vehicle_weighment_load_unload.setDeleted_by("NA");
			vehicle_weighment_load_unload.setDeleted_on(ldt);
			vehicle_weighment_load_unload.setRef_name_type(wm_loading_advice.getRef_doc_type());
			vehicle_weighment_load_unload.setGatepass_status(vehicle_weighment_load_unload_get.getGatepass_status());
			vehicle_weighment_load_unload.setGp_gi_id(vehicle_weighment_load_unload_get.getGp_gi_id());
			vehicle_weighment_load_unload.setGp_go_auth_id(vehicle_weighment_load_unload_get.getGp_go_auth_id());
			vehicle_weighment_load_unload.setGp_go_id(vehicle_weighment_load_unload_get.getGp_go_id());
			vehicle_weighment_load_unload.setLoad_unload_status(vehicle_weighment_load_unload_get.getLoad_unload_status());
			vehicle_weighment_load_unload.setWeighment_status(vehicle_weighment_load_unload_get.getWeighment_status());
			vehicle_weighment_load_unload.setWeighment_id(vehicle_weighment_load_unload_get.getWeighment_id());
			vehicle_weighment_load_unload.setStock_grn_status(vehicle_weighment_load_unload_get.getStock_grn_status());
			vehicle_weighment_load_unload.setWeight_bridge_location(wm_loading_advice.getWeight_bridge_location());
		/*	if(wm_loading_advice.getWeighment_status() == 0) 
			{
				vehicle_weighment_load_unload.setWe_req(false);
			}
			else 
			{
				vehicle_weighment_load_unload.setWe_req(true);
			}*/
			vehicle_weighment_load_unload.setWe_req(true);
			
			wm_loading_advice.setVehicle_weighment_load_unload(vehicle_weighment_load_unload);
	//ends
			boolean itemexchange=false;
			System.out.println("loading::"+wm_loading_advice.getReferance_id()+"///"+op.get().getReferance_id());
			if(wm_loading_advice.getReferance_id().compareToIgnoreCase(op.get().getReferance_id())==0)
			{
				System.out.println("If::");
			}
			else
			{
				System.out.println("else::");
				sales_OrderRepository.reverseSalesOrderExchange(op.get().getReferance_id());
				if(wm_loading_advice.getRef_doc_type().compareToIgnoreCase("Sales Order")==0) 
				{
					if(wm_loading_advice.getReferance_id().substring(0,2).compareTo("SO")==0) 
					{
						sales_OrderRepository.updateSalesOrderDetails(wm_loading_advice.getReferance_id(),true);
					}
				}
				
				if(wm_loading_advice.getRef_doc_type().compareToIgnoreCase("Stock Transfer")==0)
				{
					stock_TransferRepository.updateLoadingAdvice(wm_loading_advice.getReferance_id());
				}
			}
			
			if(wm_loading_advice.isJobwork()==true)
			{
				wm_loading_advice.getWm_loading_advice_itm_dtls().clear();
				
				wm_loading_adviceRepository.wm_advJobItemupdate(op.get().getAdvice_id(),"UPDATED");
				
				Set<Wm_loading_advice_Item_Dtls_for_jobwork> jobSet=new HashSet<Wm_loading_advice_Item_Dtls_for_jobwork>();
				jobSet.addAll(wm_loading_advice.getWm_loading_advice_Item_Dtls_for_jobwork());
				for(Wm_loading_advice_Item_Dtls_for_jobwork jobwork:jobSet) 
				{
					jobwork.setAdvice_id(op.get().getAdvice_id());
					jobwork.setAdvice_no(op.get().getAdvice_no());
					
					if(Utility.isNullOrEmpty(jobwork.getJob_item())) {
						jobwork.setJob_item_name(item_masterRepository.itemNameById(jobwork.getJob_item()).getItem_name());
					}else{jobwork.setJob_item_name("0");};
					
					if(Utility.isNullOrEmpty(jobwork.getJob_packing())) {
						jobwork.setJob_packing_name(item_masterRepository.itemNameById(jobwork.getJob_packing()).getItem_name());
					}else{jobwork.setJob_packing_name("0");};
					
					jobwork.setOrder_id(wm_loading_advice.getReferance_id());
					
					jobwork.setCompany_id(wm_loading_advice.getCompany_id());
					jobwork.setFin_year(wm_loading_advice.getFin_year());
					jobwork.setModified_type("INSERTED");
					jobwork.setInserted_by(wm_loading_advice.getInserted_by());
					jobwork.setInserted_on(wm_loading_advice.getInserted_on());
					jobwork.setUpdated_by("NA");
					jobwork.setUpdated_on(ldt);
					jobwork.setDeleted_by("NA");
					jobwork.setDeleted_on(ldt);
				}
				wm_loading_advice.setWm_loading_advice_Item_Dtls_for_jobwork(jobSet);
			}
			else
			{
				//System.out.println(wm_loading_advice.getWm_loading_advice_Item_Dtls_for_jobwork().size());
				if(wm_loading_advice.getWm_loading_advice_Item_Dtls_for_jobwork().size()>0) 
				{
					wm_loading_advice.getWm_loading_advice_Item_Dtls_for_jobwork().clear();
				}
				//wm_loading_advice.getWm_loading_advice_Item_Dtls_for_jobwork().clear();
				
				Set<Wm_loading_advice_itm_dtls> prevLoadOrds=new HashSet<Wm_loading_advice_itm_dtls>();
				prevLoadOrds.addAll(wm_loading_advice_itm_dtlsRepository.getLoadingItemDetails(wm_loading_advice.getAdvice_id()));
				
				wm_loading_advice_itm_dtlsRepository.wm_loading_advice_itm_dtlsupdate(wm_loading_advice.getAdvice_id(),"UPDATED");
				
				Set<Wm_loading_advice_itm_dtls> itemDtlsSet=new HashSet<Wm_loading_advice_itm_dtls>();
				itemDtlsSet.addAll(wm_loading_advice.getWm_loading_advice_itm_dtls());
				for(Wm_loading_advice_itm_dtls itemDtls:itemDtlsSet) 
				{
					itemDtls.setAdvice_id(wm_loading_advice.getAdvice_id());
					itemDtls.setAdvice_no(wm_loading_advice.getAdvice_no());
					if(Utility.isNullOrEmpty(itemDtls.getItem_code())) {
						itemDtls.setItem_name(item_masterRepository.itemNameById(itemDtls.getItem_code()).getItem_name());
					}else {itemDtls.setItem_name("NA");}
						
					if(Utility.isNullOrEmpty(itemDtls.getPacking())) {
						itemDtls.setPacking_name(item_masterRepository.itemNameById(itemDtls.getPacking()).getItem_name());
					}else {itemDtls.setPacking_name("NA");}
					
					if(Utility.isNullOrEmpty(itemDtls.getAlter_item_code())) {
						itemDtls.setAlter_item_name(item_masterRepository.itemNameById(itemDtls.getAlter_item_code()).getItem_name());
						}else {itemDtls.setAlter_item_name("NA");}
						
					if(Utility.isNullOrEmpty(itemDtls.getAlter_packing())) {
						itemDtls.setAlter_packing_name(item_masterRepository.itemNameById(itemDtls.getAlter_packing()).getItem_name());
					}else {itemDtls.setAlter_packing_name("NA");}
					
					if(Utility.isNullOrEmpty(itemDtls.getWarehouse())) {
						itemDtls.setWarehouse_name(warehouseMasterRepository.getWHNListbyCode(itemDtls.getWarehouse()).getWarehouse_name());
					}else {itemDtls.setWarehouse("0");itemDtls.setWarehouse_name("NA");}
					
					if(Utility.isNullOrEmpty(itemDtls.getStack_rack())) {
						itemDtls.setStack_rack_name(binMasterRepository.getBinDesc(itemDtls.getStack_rack()).getBin_description());
					}else {itemDtls.setStack_rack("0");itemDtls.setStack_rack_name("NA");}
					
					if(wm_loading_advice.getRef_doc_type().compareToIgnoreCase("Sales Order") == 0) 
					{
						if(Utility.isNullOrEmpty(itemDtls.getOrder_id()))
				 		{
							
				 		}
						else
						{
							itemDtls.setOrder_id(wm_loading_advice.getReferance_id());
						}
						
					}
					
					//SELECT capacity ,(SELECT capacity  FROM item_master_pack_mat_tag WHERE modified_type='INSERTED' AND item_id=:alter_item_code  AND  item_code=:alter_packing) AS packing_capacity,ROUND(:s_quantity*((SELECT capacity  FROM item_master_pack_mat_tag WHERE modified_type='INSERTED' AND item_id=:alter_item_code  AND  item_code=:alter_packing)/(capacity))) AS altsqty FROM item_master_pack_mat_tag WHERE modified_type='INSERTED' AND  item_code=:packing AND item_id=:item_code
					 
					// itemDtls.setAlt_s_quantity(wm_loading_advice_itm_dtlsRepository.bagsforaltconvert(item_code,packing,alter_item_code,alter_packing,s_quantity));
					 itemDtls.setAlt_s_quantity(wm_loading_advice_itm_dtlsRepository.bagsforaltconvert(itemDtls.getItem_code(),itemDtls.getPacking(),itemDtls.getAlter_item_code(),itemDtls.getAlter_packing(),itemDtls.getS_quantity()));
					//new cal ends here 
					
					itemDtls.setOrder_id(wm_loading_advice.getReferance_id());
					itemDtls.setCompany_id(wm_loading_advice.getCompany_id());
					itemDtls.setFin_year(wm_loading_advice.getFin_year());
					itemDtls.setModified_type("INSERTED");
					itemDtls.setInserted_by(wm_loading_advice.getInserted_by());
					itemDtls.setInserted_on(wm_loading_advice.getInserted_on());
					itemDtls.setUpdated_by(wm_loading_advice.getUpdated_by());
					itemDtls.setUpdated_on(wm_loading_advice.getUpdated_on());
					itemDtls.setDeleted_by("NA");
					itemDtls.setDeleted_on(ldt);
				}
				wm_loading_advice.setWm_loading_advice_itm_dtls(itemDtlsSet);
				

				//*****************Track Orders *****************************************************************
				
				List<Sales_transaction> stkList=new ArrayList<Sales_transaction>();
				stkList.addAll(sales_transactionRepository.getSalesStocks(wm_loading_advice.getReferance_id()));
				
				stkList.forEach((stkOrders) -> {
					itemDtlsSet.forEach((lOrders) -> {
						prevLoadOrds.forEach((ploadOrds) ->{
							if(stkOrders.getItem_id().compareTo(lOrders.getItem_code())==0 && stkOrders.getPacking_id().compareTo(lOrders.getPacking())==0 && stkOrders.getBusiness_unit().compareTo(wm_loading_advice.getB_unit())==0 
									&& stkOrders.getItem_id().compareTo(ploadOrds.getItem_code())==0 && stkOrders.getPacking_id().compareTo(ploadOrds.getPacking())==0) {
								
								//System.err.println("Final Stk: Curr Pack: "+(Double.valueOf(ploadOrds.getS_quantity())+stkOrders.getSales_pack_qty()-Double.valueOf(lOrders.getS_quantity())));
								//System.err.println("Final Stk: Curr Item: "+(ploadOrds.getQuantity()+stkOrders.getSales_item_qty()-lOrders.getQuantity()));
								//System.err.println("Final Stk: Curr Load: "+(stkOrders.getLoad_pack_qty()-ploadOrds.getS_quantity()+Double.valueOf(lOrders.getS_quantity())));
								
								//Update Sales Transaction *********
							//	System.err.println("Final: "+wm_loading_advice.getReferance_id()+" , "+stkOrders.getBusiness_unit()+" , "+stkOrders.getItem_id()+" , "+stkOrders.getPacking_id());
								
								sales_transactionRepository.updateSales_transactions(wm_loading_advice.getReferance_id(), stkOrders.getBusiness_unit(), stkOrders.getItem_id(), stkOrders.getPacking_id(),
										(ploadOrds.getQuantity()+stkOrders.getSales_item_qty()-lOrders.getQuantity()),(Double.valueOf(ploadOrds.getS_quantity())+stkOrders.getSales_pack_qty()-Double.valueOf(lOrders.getS_quantity())),
										(stkOrders.getLoad_item_qty()-ploadOrds.getQuantity()+Double.valueOf(lOrders.getQuantity())),(stkOrders.getLoad_pack_qty()-ploadOrds.getS_quantity()+Double.valueOf(lOrders.getS_quantity())),"INSERTED");
								
							}
						});
					});
				});
				
				//*****************Track Orders ******************************************************************
			}
			
			
			
			wm_loading_advice_driver_dtlsRepository.wm_loading_advice_driver_dtlsupdate(wm_loading_advice.getAdvice_id(),"UPDATED");
			
			Set<Wm_loading_advice_driver_dtls> driverDtlsSet=new HashSet<Wm_loading_advice_driver_dtls>();
			driverDtlsSet.add(wm_loading_advice.getWm_loading_advice_driver_dtls());
			for(Wm_loading_advice_driver_dtls driverDtls:driverDtlsSet) 
			{
				driverDtls.setAdvice_id(wm_loading_advice.getAdvice_id());
				driverDtls.setAdvice_no(wm_loading_advice.getAdvice_no());
				driverDtls.setCompany_id(wm_loading_advice.getCompany_id());
				driverDtls.setFin_year(wm_loading_advice.getFin_year());
				driverDtls.setModified_type("INSERTED");
				driverDtls.setInserted_by(wm_loading_advice.getInserted_by());
				driverDtls.setInserted_on(wm_loading_advice.getInserted_on());
				driverDtls.setUpdated_by(wm_loading_advice.getUpdated_by());
				driverDtls.setUpdated_on(wm_loading_advice.getUpdated_on());
				driverDtls.setDeleted_by("NA");
				driverDtls.setDeleted_on(ldt);
			}
			if(!driverDtlsSet.isEmpty())
			{
				wm_loading_advice.setWm_loading_advice_driver_dtls(driverDtlsSet.iterator().next());
			}
			
			wm_loading_advice_trans_infoRepository.wm_loading_advice_trans_infoupdate(wm_loading_advice.getAdvice_id(),"UPDATED");
			
			Set<Wm_loading_advice_trans_info> transInfoSet=new HashSet<Wm_loading_advice_trans_info>();
			transInfoSet.add(wm_loading_advice.getWm_loading_advice_trans_info());
			for(Wm_loading_advice_trans_info transInfo:transInfoSet) 
			{
				transInfo.setAdvice_id(wm_loading_advice.getAdvice_id());
				transInfo.setAdvice_no(wm_loading_advice.getAdvice_no());

				if(Utility.isNullOrEmpty(transInfo.getTransporter_name())) 
				{
					if(transInfo.getTransporter_name().compareToIgnoreCase("0")==0)
					{
						transInfo.setTrans_name("0");
					}
					else
					{
						transInfo.setTrans_name(trans_bussiness_partnerRepository.bpNameById(transInfo.getTransporter_name()).getBp_name());
					}
				}
				else{transInfo.setTrans_name("0");};
				
				transInfo.setCompany_id(wm_loading_advice.getCompany_id());
				transInfo.setFin_year(wm_loading_advice.getFin_year());
				transInfo.setModified_type("INSERTED");
				transInfo.setInserted_by(wm_loading_advice.getInserted_by());
				transInfo.setInserted_on(wm_loading_advice.getInserted_on());
				transInfo.setUpdated_by(wm_loading_advice.getUpdated_by());
				transInfo.setUpdated_on(wm_loading_advice.getUpdated_on());
				transInfo.setDeleted_by("NA");
				transInfo.setDeleted_on(ldt);
			}
			if(!transInfoSet.isEmpty())
			{
				wm_loading_advice.setWm_loading_advice_trans_info(transInfoSet.iterator().next());
			}
			
			wm_loading_advice_bp_dtlsRepository.wm_loading_advice_bp_dtlsupdate(wm_loading_advice.getAdvice_id(),"UPDATED");
			
			Set<Wm_loading_advice_bp_dtls> bpDtlsSet=new HashSet<Wm_loading_advice_bp_dtls>();
			bpDtlsSet.add(wm_loading_advice.getWm_loading_advice_bp_dtls());
			for(Wm_loading_advice_bp_dtls bpDtls:bpDtlsSet) 
			{
				bpDtls.setAdvice_id(wm_loading_advice.getAdvice_id());
				bpDtls.setAdvice_no(wm_loading_advice.getAdvice_no());
				bpDtls.setCompany_id(wm_loading_advice.getCompany_id());
				bpDtls.setFin_year(wm_loading_advice.getFin_year());
				bpDtls.setModified_type("INSERTED");
				bpDtls.setInserted_by(wm_loading_advice.getInserted_by());
				bpDtls.setInserted_on(wm_loading_advice.getInserted_on());
				bpDtls.setUpdated_by(wm_loading_advice.getUpdated_by());
				bpDtls.setUpdated_on(wm_loading_advice.getUpdated_on());
				bpDtls.setDeleted_by("NA");
				bpDtls.setDeleted_on(ldt);
			}
			if(!bpDtlsSet.isEmpty())
			{
				wm_loading_advice.setWm_loading_advice_bp_dtls(bpDtlsSet.iterator().next());
			}
			
			wm_loading_advice_doc_attchRepository.wm_loading_advice_doc_attchupdate(wm_loading_advice.getAdvice_id(),"UPDATED");
			
			Set<Wm_loading_advice_doc_attch> docSet=new HashSet<Wm_loading_advice_doc_attch>();
			docSet.addAll(wm_loading_advice.getWm_loading_advice_doc_attch());
			for(Wm_loading_advice_doc_attch doc:docSet) 
			{
				doc.setAdvice_id(wm_loading_advice.getAdvice_id());
				doc.setAdvice_no(wm_loading_advice.getAdvice_no());
				doc.setCompany_id(wm_loading_advice.getCompany_id());
				doc.setFin_year(wm_loading_advice.getFin_year());
				doc.setModified_type("INSERTED");
				doc.setInserted_by(wm_loading_advice.getInserted_by());
				doc.setInserted_on(wm_loading_advice.getInserted_on());
				doc.setUpdated_by(wm_loading_advice.getUpdated_by());
				doc.setUpdated_on(wm_loading_advice.getUpdated_on());
				doc.setDeleted_by("NA");
				doc.setDeleted_on(ldt);
			}
			wm_loading_advice.setWm_loading_advice_doc_attch(docSet);
			
			wm_loading_advice_broker_dtlsRepository.wm_loading_advice_broker_dtlsUpdate(wm_loading_advice.getAdvice_id(),"UPDATED");
			
			Set<Wm_loading_advice_broker_dtls> brokerSet=new HashSet<Wm_loading_advice_broker_dtls>();
			brokerSet.addAll(wm_loading_advice.getWm_loading_advice_broker_dtls());
			for(Wm_loading_advice_broker_dtls brokerDtls:brokerSet) 
			{
				brokerDtls.setAdvice_id(wm_loading_advice.getAdvice_id());
				brokerDtls.setAdvice_no(wm_loading_advice.getAdvice_no());
				brokerDtls.setCompany_id(wm_loading_advice.getCompany_id());
				brokerDtls.setFin_year(wm_loading_advice.getFin_year());
				brokerDtls.setModified_type("INSERTED");
				brokerDtls.setInserted_by(wm_loading_advice.getInserted_by());
				brokerDtls.setInserted_on(wm_loading_advice.getInserted_on());
				brokerDtls.setUpdated_by(wm_loading_advice.getUpdated_by());
				brokerDtls.setUpdated_on(wm_loading_advice.getUpdated_on());
				brokerDtls.setDeleted_by("NA");
				brokerDtls.setDeleted_on(ldt);
			}
			wm_loading_advice.setWm_loading_advice_broker_dtls(brokerSet);
			
			wm_loading_advice_Party_DtlsRepository.wm_loading_advice_Party_DtlsUpdate(wm_loading_advice.getAdvice_id(),"UPDATED");
			
			Set<Wm_loading_advice_Party_Dtls> partySet=new HashSet<Wm_loading_advice_Party_Dtls>();
			partySet.addAll(wm_loading_advice.getWm_loading_advice_Party_Dtls());
			for(Wm_loading_advice_Party_Dtls party:partySet) 
			{
				party.setAdvice_id(wm_loading_advice.getAdvice_id());
				party.setAdvice_no(wm_loading_advice.getAdvice_no());
				party.setCompany_id(wm_loading_advice.getCompany_id());
				party.setFin_year(wm_loading_advice.getFin_year());
				party.setModified_type("INSERTED");
				party.setInserted_by(wm_loading_advice.getInserted_by());
				party.setInserted_on(wm_loading_advice.getInserted_on());
				party.setUpdated_by(wm_loading_advice.getUpdated_by());
				party.setUpdated_on(wm_loading_advice.getUpdated_on());
				party.setDeleted_by("NA");
				party.setDeleted_on(ldt);
			}
			wm_loading_advice.setWm_loading_advice_Party_Dtls(partySet);
			
			wm_loading_advice_Shipment_DtlsRepository.wm_loading_advice_Shipment_DtlsUpdate(wm_loading_advice.getAdvice_id(),"UPDATED");
			
			Set<Wm_loading_advice_Shipment_Dtls> shipmentDtlsSet=new HashSet<Wm_loading_advice_Shipment_Dtls>();
			shipmentDtlsSet.add(wm_loading_advice.getWm_loading_advice_Shipment_Dtls());
			for(Wm_loading_advice_Shipment_Dtls shipmentDtls:shipmentDtlsSet) 
			{
				shipmentDtls.setAdvice_id(wm_loading_advice.getAdvice_id());
				shipmentDtls.setAdvice_no(wm_loading_advice.getAdvice_no());
				shipmentDtls.setCompany_id(wm_loading_advice.getCompany_id());
				shipmentDtls.setFin_year(wm_loading_advice.getFin_year());
				shipmentDtls.setModified_type("INSERTED");
				shipmentDtls.setInserted_by(wm_loading_advice.getInserted_by());
				shipmentDtls.setInserted_on(wm_loading_advice.getInserted_on());
				shipmentDtls.setUpdated_by(wm_loading_advice.getUpdated_by());
				shipmentDtls.setUpdated_on(wm_loading_advice.getUpdated_on());
				shipmentDtls.setDeleted_by("NA");
				shipmentDtls.setDeleted_on(ldt);
			}
			if(!shipmentDtlsSet.isEmpty())
			{
				wm_loading_advice.setWm_loading_advice_Shipment_Dtls(shipmentDtlsSet.iterator().next());
			}
			return wm_loading_adviceRepository.save(wm_loading_advice);
		//}	
	}
	
	@Transactional
	public Wm_loading_advice deleteLoadingAdvice(Wm_loading_advice loading_advice,Long id)
	{
		Optional<Wm_loading_advice> op = wm_loading_adviceRepository.findById(id);
		Wm_loading_advice wm_loading_advice=wm_loading_adviceRepository.getLoadingDetails(id);
		Wm_loading_advice loading_adviceStatus=new Wm_loading_advice(); 
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(op.get().getWeighment_status() != 0) {
			//System.err.println("Delete is not Possible !!!");
			return loading_adviceStatus;
		}else {
			//System.err.println("Delete Successfully !!!");
		
			wm_loading_advice.setWeighment_id("0");
			wm_loading_advice.setLoading_status(0);
			wm_loading_advice.setWeighment_status(0);
			wm_loading_advice.setStk_trans_chln_status("No");
			wm_loading_advice.setStk_trans_inv_status("No");
			wm_loading_advice.setUnload_status("Not Done");
			wm_loading_advice.setModified_type("DELETED");
			wm_loading_advice.setInserted_by(op.get().getInserted_by());
			wm_loading_advice.setInserted_on(op.get().getInserted_on());
			wm_loading_advice.setUpdated_by(op.get().getUpdated_by());
			wm_loading_advice.setUpdated_on(op.get().getUpdated_on());
			wm_loading_advice.setDeleted_by(userRepository.getUserDetails(wm_loading_advice.getUsername()).getName());
			wm_loading_advice.setDeleted_on(ldt);
			
			if(op.isPresent())	{
				wm_loading_advice.setId(id);
			}
			
			if(wm_loading_advice.getAdvice_type().compareTo("Purchase Return")==0) {
				//Update loading Adv Status
				pur_return_approval_noteRepository.updateLoadingStatus(wm_loading_advice.getReferance_id(),"Not Done");
			}else {}
			
			List<Wm_loading_advice> lAdvices=new ArrayList<Wm_loading_advice>();
			lAdvices.addAll(wm_loading_adviceRepository.loadingAdviceDetails(wm_loading_advice.getAdvice_id(),wm_loading_advice.getReferance_id()));
			if(lAdvices.size() == 0 && wm_loading_advice.getReferance_id().substring(0,2).compareTo("SO")==0) {
				sales_OrderRepository.updateSalesOrderDetails(wm_loading_advice.getReferance_id(),false);
			}
			
			//*****************Track Orders *****************************************************************
			//System.err.println("Advice: >>>>"+wm_loading_advice.getAdvice_id());  //Get Order Details
			Set<Wm_loading_advice_itm_dtls> itemDtlsSet=new HashSet<Wm_loading_advice_itm_dtls>();
			itemDtlsSet.addAll(wm_loading_advice_itm_dtlsRepository.getLoadingItemDetails(wm_loading_advice.getAdvice_id()));
			
			List<Sales_transaction> stkList=new ArrayList<Sales_transaction>();
			stkList.addAll(sales_transactionRepository.getSalesStocks(wm_loading_advice.getReferance_id()));
			
			stkList.forEach((stkOrders) -> {
				itemDtlsSet.forEach((lOrders) -> {
					if(stkOrders.getItem_id().compareTo(lOrders.getItem_code())==0 && stkOrders.getPacking_id().compareTo(lOrders.getPacking())==0 && stkOrders.getBusiness_unit().compareTo(wm_loading_advice.getB_unit())==0) {
						
						//Adjust Sales Transaction Order *********
						sales_transactionRepository.updateSales_transactions(wm_loading_advice.getReferance_id(), stkOrders.getBusiness_unit(), stkOrders.getItem_id(), stkOrders.getPacking_id(),
								(stkOrders.getSales_item_qty()+lOrders.getQuantity()), (stkOrders.getSales_pack_qty()+Double.valueOf(lOrders.getS_quantity())),(stkOrders.getLoad_item_qty()-lOrders.getQuantity()),(stkOrders.getLoad_pack_qty()-Double.valueOf(lOrders.getS_quantity())),"INSERTED");
					}
				});
			});
			//*****************Track Orders ******************************************************************
			
			if(op.get().isJobwork()==true)
			{
				wm_loading_adviceRepository.wm_advJobItemupdate(op.get().getAdvice_id(),"DELETED");
			}
			else
			{
				wm_loading_advice_itm_dtlsRepository.wm_loading_advice_itm_dtlsupdate(wm_loading_advice.getAdvice_id(),"DELETED");
			}
			
			wm_loading_advice_driver_dtlsRepository.wm_loading_advice_driver_dtlsupdate(wm_loading_advice.getAdvice_id(),"DELETED");
			
			wm_loading_advice_trans_infoRepository.wm_loading_advice_trans_infoupdate(wm_loading_advice.getAdvice_id(),"DELETED");
			
			wm_loading_advice_bp_dtlsRepository.wm_loading_advice_bp_dtlsupdate(wm_loading_advice.getAdvice_id(),"DELETED");
			
			wm_loading_advice_doc_attchRepository.wm_loading_advice_doc_attchupdate(wm_loading_advice.getAdvice_id(),"DELETED");
			
			wm_loading_advice_broker_dtlsRepository.wm_loading_advice_broker_dtlsUpdate(wm_loading_advice.getAdvice_id(),"DELETED");
			
			wm_loading_advice_Party_DtlsRepository.wm_loading_advice_Party_DtlsUpdate(wm_loading_advice.getAdvice_id(),"DELETED");
			
			wm_loading_advice_Shipment_DtlsRepository.wm_loading_advice_Shipment_DtlsUpdate(wm_loading_advice.getAdvice_id(),"DELETED");
			
			wm_loading_adviceRepository.loadingVehicleUpdate(wm_loading_advice.getAdvice_id());
			
			return wm_loading_adviceRepository.save(wm_loading_advice);
		}
	  }
	
	  public List<Wm_loading_adviceDTO> findAll(){
			
			List<Wm_loading_advice> lAdvList=new ArrayList<Wm_loading_advice>();
			lAdvList.addAll(wm_loading_adviceRepository.findLoadingAdvices());
			
			Type listType=new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
			List<Wm_loading_adviceDTO> advList=new ModelMapper().map(lAdvList,listType);
			
			for(int i=0;i<advList.size();i++) {
				if(Utility.isNullOrEmpty(advList.get(i).getLoad_point())) {
					advList.get(i).setLoad_point(loading_pointRepository.getLoadingPointDetails(advList.get(i).getLoad_point()).getLoading_name());
				}else {advList.get(i).setLoad_point("None");}
				
				if(Utility.isNullOrEmpty(advList.get(i).getCustomer())) {
					advList.get(i).setCustomer(cust_bussiness_partnerRepository.getCustomer(advList.get(i).getCustomer()).getCp_name());
				}else {advList.get(i).setCustomer("None");}
				
				if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,2).compareTo("SO")==0) {
					advList.get(i).setOrder_no(sales_OrderRepository.getSalesOrderDetails(advList.get(i).getReferance_id()).getOrder_no());
					advList.get(i).setOrder_date(sales_OrderRepository.getSalesOrderDetails(advList.get(i).getReferance_id()).getOrder_date());
				}
				else if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,2).compareTo("ST")==0) {
					advList.get(i).setOrder_no(stock_TransferRepository.getStockTransDtls(advList.get(i).getReferance_id()).getOrder_no());
					advList.get(i).setOrder_date(stock_TransferRepository.getStockTransDtls(advList.get(i).getReferance_id()).getOrder_date());
				}
				else if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,3).compareTo("PRA")==0) {
					advList.get(i).setOrder_no(pur_return_approval_noteRepository.getPurRtnAppNoteDtls(advList.get(i).getReferance_id()).getPurreturnno());
					advList.get(i).setOrder_date(pur_return_approval_noteRepository.getPurRtnAppNoteDtls(advList.get(i).getReferance_id()).getPurreturndate());
				}
				else {
					advList.get(i).setOrder_date("None");
					advList.get(i).setOrder_no("None");
				}
			}
			return advList;
		}
		
	  
	  public Page<Wm_loading_advice_Pagination_DTO> getLoadingAdvices_pagination(int page,int size)
	  {
		  
			//PageRequest pageRequest = PageRequest.of(page, size,Sort.by("id").descending());
		  	PageRequest pageRequest = PageRequest.of(page, size,Sort.by("advicedate").descending().and(Sort.by("adviceno")).descending());
		    Page<Wm_loading_advice> pageResult = wm_loading_adviceRepository.findAll(pageRequest);
		   
		    List<Wm_loading_advice_Pagination_DTO> advList = pageResult
		    	      .stream()
		    	      .map((Wm_loading_advice wm_loading_advice) -> new Wm_loading_advice_Pagination_DTO(wm_loading_advice.getId(),
		    	    		   wm_loading_advice.getAdvice_id(),
		    	    		   wm_loading_advice.getAdvice_no(),
		    	    		   wm_loading_advice.getAdvice_date(),
		    	    		   wm_loading_advice.getAdvice_type(),
		    	    		   wm_loading_advice.getVehicle_no(),
		    	    		   wm_loading_advice.getWeighment_status(),
		    	    		   wm_loading_advice.getLoad_point(),
		    	    		   wm_loading_advice.getCustomer(),
		    	    		   wm_loading_advice.getCustomer_name(),
		    	    		   "na",
		    	    		   wm_loading_advice.getAdvice_date(),
		    	    		   wm_loading_advice.getReferance_id(),wm_loading_advice.getModified_type(),wm_loading_advice.isDelvchallan_status())).filter(c -> c.getModified_type().equals("INSERTED")).collect(Collectors.toList());
		    	      
		    for(int i=0;i<advList.size();i++) {
				if(Utility.isNullOrEmpty(advList.get(i).getLoad_point())) {
					advList.get(i).setLoad_point(loading_pointRepository.getLoadingPointDetails(advList.get(i).getLoad_point()).getLoading_name());
				}else {advList.get(i).setLoad_point("None");}
				
				if(Utility.isNullOrEmpty(advList.get(i).getCustomer())) {
					advList.get(i).setCustomer(cust_bussiness_partnerRepository.getCustomer(advList.get(i).getCustomer()).getCp_name());
				}else {advList.get(i).setCustomer("None");}
				
				if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,2).compareTo("SO")==0) {
					advList.get(i).setOrder_no(sales_OrderRepository.getSalesOrderDetails(advList.get(i).getReferance_id()).getOrder_no());
					advList.get(i).setOrder_date(sales_OrderRepository.getSalesOrderDetails(advList.get(i).getReferance_id()).getOrder_date());
				}
				else if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,2).compareTo("ST")==0) {
					advList.get(i).setOrder_no(stock_TransferRepository.getStockTransDtls(advList.get(i).getReferance_id()).getOrder_no());
					advList.get(i).setOrder_date(stock_TransferRepository.getStockTransDtls(advList.get(i).getReferance_id()).getOrder_date());
				}
				else if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,3).compareTo("PRA")==0) {
					advList.get(i).setOrder_no(pur_return_approval_noteRepository.getPurRtnAppNoteDtls(advList.get(i).getReferance_id()).getPurreturnno());
					advList.get(i).setOrder_date(pur_return_approval_noteRepository.getPurRtnAppNoteDtls(advList.get(i).getReferance_id()).getPurreturndate());
				}
				else {
					advList.get(i).setOrder_date("None");
					advList.get(i).setOrder_no("None");
				}
			}
		   
		    return new PageImpl<>(advList, pageRequest, pageResult.getTotalElements());
	  }
	  
	  public List<Map<String,Object>> searchLoadingAdviceFast(String orderno,String fromdate, String todate,String bus_partner1,String finyear)
		{
			List<Map<String,Object>> serchloadinadvice =new ArrayList<Map<String,Object>>();
			String tablename="wm_loading_advice",party_name="bus_partner",order_no="advice_no",order_date="advice_date";
			serchloadinadvice.addAll(wm_loading_adviceRepository.getsearchdataFast(tablename,party_name,order_no,order_date,orderno,fromdate,todate,bus_partner1,finyear));
			return serchloadinadvice;
		}
	  
	  public List<Wm_loading_advice_Pagination_DTO> searchLoadingAdvice(String orderno,String fromdate, String todate,String bus_partner1,String finyear)
		{
			List<Wm_loading_advice> serchloadinadvice =new ArrayList<Wm_loading_advice>();
			//System.out.println("finyear :: "+finyear);
			String tablename="wm_loading_advice",party_name="bus_partner",order_no="advice_no",order_date="advice_date";
			serchloadinadvice.addAll(wm_loading_adviceRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,bus_partner1,finyear));
			
			//System.out.println(serchloadinadvice.get(0));
			List<Wm_loading_advice_Pagination_DTO> advList = new ArrayList<Wm_loading_advice_Pagination_DTO>();
			for(int i=0;i<serchloadinadvice.size();i++) 
			{
			
				Wm_loading_advice_Pagination_DTO sale= new Wm_loading_advice_Pagination_DTO(serchloadinadvice.get(i).getId(),
							serchloadinadvice.get(i).getAdvice_id(),
							serchloadinadvice.get(i).getAdvice_no(),
							serchloadinadvice.get(i).getAdvice_date(),
							serchloadinadvice.get(i).getAdvice_type(),
							serchloadinadvice.get(i).getVehicle_no(),
							serchloadinadvice.get(i).getWeighment_status(),
							serchloadinadvice.get(i).getLoad_point(),
							serchloadinadvice.get(i).getCustomer(),
							serchloadinadvice.get(i).getCustomer_name(),
	    	    		    "na",
	    	    		    serchloadinadvice.get(i).getAdvice_date(),
	    	    		    serchloadinadvice.get(i).getReferance_id(),serchloadinadvice.get(i).getModified_type(),serchloadinadvice.get(i).isDelvchallan_status());
				
				advList.add(sale);
			}
			
			  for(int i=0;i<advList.size();i++) {
					if(Utility.isNullOrEmpty(advList.get(i).getLoad_point())) {
						advList.get(i).setLoad_point(loading_pointRepository.getLoadingPointDetails(advList.get(i).getLoad_point()).getLoading_name());
					}else {advList.get(i).setLoad_point("None");}
					
					if(Utility.isNullOrEmpty(advList.get(i).getCustomer())) {
						advList.get(i).setCustomer(cust_bussiness_partnerRepository.getCustomer(advList.get(i).getCustomer()).getCp_name());
					}else {advList.get(i).setCustomer("None");}
					
					if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,2).compareTo("SO")==0) {
						advList.get(i).setOrder_no(sales_OrderRepository.getSalesOrderDetails(advList.get(i).getReferance_id()).getOrder_no());
						advList.get(i).setOrder_date(sales_OrderRepository.getSalesOrderDetails(advList.get(i).getReferance_id()).getOrder_date());
					}
					else if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,2).compareTo("ST")==0) {
						advList.get(i).setOrder_no(stock_TransferRepository.getStockTransDtls(advList.get(i).getReferance_id()).getOrder_no());
						advList.get(i).setOrder_date(stock_TransferRepository.getStockTransDtls(advList.get(i).getReferance_id()).getOrder_date());
					}
					else if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,3).compareTo("PRA")==0) {
						advList.get(i).setOrder_no(pur_return_approval_noteRepository.getPurRtnAppNoteDtls(advList.get(i).getReferance_id()).getPurreturnno());
						advList.get(i).setOrder_date(pur_return_approval_noteRepository.getPurRtnAppNoteDtls(advList.get(i).getReferance_id()).getPurreturndate());
					}
					else {
						advList.get(i).setOrder_date("None");
						advList.get(i).setOrder_no("None");
					}
				}
			   
			  List<Wm_loading_advice_Pagination_DTO> finalData = advList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Wm_loading_advice_Pagination_DTO::getAdvice_date).
							  thenComparingInt(
	                          d -> d.getAdvice_date().length() + d.getAdvice_no().length())
	                  .thenComparing(Wm_loading_advice_Pagination_DTO::getAdvice_no).reversed())
					  .collect(Collectors.toList());
			  
			//System.out.println(paginationlist.get(0));
			return finalData;
			
			
		}
	  
	  public Wm_loading_advice findOne(long id)
		{
			Optional<Wm_loading_advice> wla=this.wm_loading_adviceRepository.findById(id);
			return wla.get();
		}
			
	  public List<Wm_loading_advice_itm_dtlsDTO> loadingItemRetriveList(String code)
		{
			List<Wm_loading_advice_itm_dtls> modelList=wm_loading_advice_itm_dtlsRepository.loadingItemRetriveList(code);
			
			Type listType=new TypeToken<List<Wm_loading_advice_itm_dtlsDTO>>() {}.getType();
			
			List<Wm_loading_advice_itm_dtlsDTO> loadingItem=new ModelMapper().map(modelList,listType);
			
			return loadingItem;
		}
	  
	  public List<Wm_loading_advice_itm_dtlsDTO> loadingItemRetriveListprint(String code)
		{
		       Wm_loading_advice refernce=wm_loading_adviceRepository.getvehicleno(code);//loading details static
		       
		       
		       Sales_Order saleorder=sales_OrderRepository.getSalesOrderDetails(refernce.getReferance_id());
		       
		  
			List<Wm_loading_advice_itm_dtls> modelList=wm_loading_advice_itm_dtlsRepository.loadingItemRetriveList(code);
			
			
			modelList.forEach(e->
			{
					if(saleorder.getInv_type().compareToIgnoreCase("INV00004")==0) //it means when packing material for sale than view calcution wont be happening 
					{
						e.setQuantity(e.getMat_wt());
					}
					
			});
			Type listType=new TypeToken<List<Wm_loading_advice_itm_dtlsDTO>>() {}.getType();
			
			List<Wm_loading_advice_itm_dtlsDTO> loadingItem=new ModelMapper().map(modelList,listType);
			
			return loadingItem;
		}
	  public Wm_loading_advice_driver_dtlsDTO loadingDriverRetriveList(String code)
		 {
			 Wm_loading_advice_driver_dtls modelList=wm_loading_advice_driver_dtlsRepository.loadingDriverRetriveList(code);
			 Type listType = new TypeToken<Wm_loading_advice_driver_dtlsDTO>() {}.getType();

			 Wm_loading_advice_driver_dtlsDTO loadingDriver= new ModelMapper().map(modelList,listType);
				
			 return loadingDriver;
		 }
		 
	  public Wm_loading_advice_trans_infoDTO loadingTransInfoRetriveList(String code,String company)
		 {
			 Wm_loading_advice_trans_info modelList=wm_loading_advice_trans_infoRepository.loadingTransInfoRetriveList(code,company);
			 
			 Type listType = new TypeToken<Wm_loading_advice_trans_infoDTO>() {}.getType();

			 Wm_loading_advice_trans_infoDTO loadingTransInfo= new ModelMapper().map(modelList,listType);
				
			 return loadingTransInfo;
		 }
		 
	  public  Wm_loading_advice_bp_dtlsDTO loadingBPDtlsRetriveList(String code)
		 {
			 Wm_loading_advice_bp_dtls modelList=wm_loading_advice_bp_dtlsRepository.loadingBPDtlsRetriveList(code);
			 Type listType = new TypeToken<Wm_loading_advice_bp_dtlsDTO>() {}.getType();

			 Wm_loading_advice_bp_dtlsDTO loadingBPDtls= new ModelMapper().map(modelList,listType);
				
			 return loadingBPDtls;
		 }
	 
	  public List<Wm_loading_advice_doc_attchDTO> loadingDocRetriveList(String code)
		{
			List<Wm_loading_advice_doc_attch> modelList=wm_loading_advice_doc_attchRepository.loadingDocRetriveList(code);
			
			Type listType=new TypeToken<List<Wm_loading_advice_doc_attchDTO>>() {}.getType();
			
			List<Wm_loading_advice_doc_attchDTO> loadingDoc=new ModelMapper().map(modelList,listType);
			
			return loadingDoc;
		}
	    
	  public List<Wm_loading_advice_broker_dtlsDTO> getLoadingAdvBrokerDtls(String order_id)
		{
			List<Wm_loading_advice_broker_dtls> modelList=wm_loading_advice_broker_dtlsRepository.getLoadingAdvBrokerDtls(order_id);
			
			Type listType=new TypeToken<List<Wm_loading_advice_broker_dtlsDTO>>() {}.getType();
			
			List<Wm_loading_advice_broker_dtlsDTO> loadingBroker=new ModelMapper().map(modelList,listType);
			
			return loadingBroker;
		}
	    
	  public List<Wm_loading_adviceDTO> getLoadingAdviceList(String company){
	    	
	    	List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadingAdviceList();

	    	modelList.forEach((e->{
			e.setAdvice_no(e.getAdvice_no().toUpperCase());
	    	}));
	    	
	    	List<Wm_loading_advice> allData = modelList
					  .parallelStream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Wm_loading_advice::getAdvice_no))
					  .collect(Collectors.toList());
			
			Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
			
			List<Wm_loading_adviceDTO> lAdviceList = new ModelMapper().map(allData,listType);
			
			return lAdviceList;
	    }
	    
	  public Wm_loading_adviceDTO getLoadingDetails(String advice_id){
	    	
	    	Wm_loading_advice modelList=wm_loading_adviceRepository.getLoadingDetails(advice_id);
			
			Type listType = new TypeToken<Wm_loading_adviceDTO>() {}.getType();
			
			Wm_loading_adviceDTO lAdviceList = new ModelMapper().map(modelList,listType);
			
			return lAdviceList;
	    }
	    
	  public Wm_loading_adviceDTO getLoadingAdvThruVehicle(String vehicleid,String adv_type){
	    	
	    	Wm_loading_advice modelList=wm_loading_adviceRepository.getLoadingAdvThruVehicle(vehicleid,adv_type);
			
			Type listType = new TypeToken<Wm_loading_adviceDTO>() {}.getType();
			
			Wm_loading_adviceDTO loadAdviceList = new ModelMapper().map(modelList,listType);
			
			return loadAdviceList;
	    }
	    
	  public List<Wm_loading_adviceDTO> getLoadingAdviceStkTrans(){
	    	
	    	//List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadingAdviceStkTrans();
	    	List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadingAdviceStkTransNew();
			
			Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
			
			List<Wm_loading_adviceDTO> lAdviceList = new ModelMapper().map(modelList,listType);
			
			return lAdviceList;
	    }
	    
	  public List<Wm_loading_adviceDTO> getLoadingAdvicesThruWeigh(String cdate){
	    	
	    	List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadingAdvicesThruWeigh(cdate);
			
			Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
			
			List<Wm_loading_adviceDTO> lweList = new ModelMapper().map(modelList,listType);
			
			return lweList;
	    }
	    
	  public List<Wm_loading_adviceDTO> getLoadingAdvicesThruWeigh(String cdate,String party,String inv_type){
		
	    		    	
	    	List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadingAdvicesThruWeigh(Utility.convertDateYYYYMMDD(cdate),party,false);
			//inv_type
	    	
	    	List<Wm_loading_advice> newlist =new ArrayList<Wm_loading_advice>();
	    	modelList.forEach(e->
	    	{
	    		if(sales_OrderRepository.checkgstregular(e.getReferance_id(),inv_type)) 
	    		{
	    			newlist.add(e);
	    		}
	    		
	    		
	    	});
	    	
	    	
	    	Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
			List<Wm_loading_adviceDTO> lweList = new ModelMapper().map(newlist,listType);
			
			return lweList;
	    }
	    
	  public List<Wm_loading_adviceDTO> getLoadingAdvPurRtn(String prdate,String supplier,String company,String finyear){
	    	
	    	List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadingAdvPurRtn(prdate,supplier,company,finyear);
			
			Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
			
			List<Wm_loading_adviceDTO> lweList = new ModelMapper().map(modelList,listType);
			
			return lweList;
	    }
	    
	  public List<Wm_loading_adviceDTO> getLoadingAdviceThruBUnit(String bunit){
	    	
	    	List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadingAdviceThruBUnit(bunit);
			
			Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
			
			List<Wm_loading_adviceDTO> lAdviceList = new ModelMapper().map(modelList,listType);
			
			return lAdviceList;
	    }
	
	  public Wm_loading_adviceDTO loadingAdviceVehicleList(String code){

		Wm_loading_advice modelList=wm_loading_adviceRepository.loadingAdviceVehicleList(code);

		Type listType = new TypeToken<Wm_loading_adviceDTO>() {}.getType();

		Wm_loading_adviceDTO vehicle= new ModelMapper().map(modelList,listType);
		
		return vehicle;
	  }
	  
	  public List<Wm_loading_advice_Party_DtlsDTO> getLoadingAdvPartyDtls(String advice_id)
		{
			List<Wm_loading_advice_Party_Dtls> modelList=wm_loading_advice_Party_DtlsRepository.getLoadingAdvPartyDtls(advice_id);
			
			Type listType=new TypeToken<List<Wm_loading_advice_Party_DtlsDTO>>() {}.getType();
			
			List<Wm_loading_advice_Party_DtlsDTO> loadingParty=new ModelMapper().map(modelList,listType);
			
			return loadingParty;
		} 
	  
	  public Wm_loading_advice_Shipment_DtlsDTO getLoadingAdvShipDtls(String advice_id)
		{
		  	Wm_loading_advice_Shipment_Dtls modelList=wm_loading_advice_Shipment_DtlsRepository.getLoadingAdvShipDtls(advice_id);
			
			Type listType=new TypeToken<Wm_loading_advice_Shipment_DtlsDTO>() {}.getType();
			
			Wm_loading_advice_Shipment_DtlsDTO loadingShip=new ModelMapper().map(modelList,listType);
			
			return loadingShip;
		}
	  
	  public Wm_loading_advice_trans_infoDTO getLoadingAdvTransinfo(String advice_id)
		{
		  	Wm_loading_advice_trans_info modelList=wm_loading_advice_trans_infoRepository.getLoadingAdvTransinfo(advice_id);
			
			Type listType=new TypeToken<Wm_loading_advice_trans_infoDTO>() {}.getType();
			
			Wm_loading_advice_trans_infoDTO loadingtrans=new ModelMapper().map(modelList,listType);
			
			return loadingtrans;
		}
	  
	  public List<Wm_loading_advice_itm_dtlsDTO> getLoadingAdvItemDtlsThruVehicle(String vehicle_id,String adv_type){
		  
		  	List<Wm_loading_advice> advNoList=wm_loading_adviceRepository.loadingAdviceList(vehicle_id,adv_type);
		  	
			List<Wm_loading_advice_itm_dtls> modelList=new ArrayList<Wm_loading_advice_itm_dtls>();
			
			for(int i=0;i<advNoList.size();i++)
			{
				modelList.addAll(wm_loading_advice_itm_dtlsRepository.loadingItemRetriveList(advNoList.get(i).getAdvice_id()));
			}
				
			Type listType=new TypeToken<List<Wm_loading_advice_itm_dtlsDTO>>() {}.getType();
			List<Wm_loading_advice_itm_dtlsDTO> itemList=new ModelMapper().map(modelList,listType);
			return itemList;
	  }
	  
	  public Wm_loading_advice_driver_dtlsDTO getLoadingAdvDriverDtlsThruVehicle(String vehicle_id,String adv_type) {
		  
		  List<Wm_loading_advice> advNoList=wm_loading_adviceRepository.loadingAdviceList(vehicle_id,adv_type);
		    Wm_loading_advice_driver_dtls modelList=null;
		  	for(int i=0;i<advNoList.size();i++)
			{
			  	modelList=wm_loading_advice_driver_dtlsRepository.loadingDriverRetriveList(advNoList.get(0).getAdvice_id());
			}
		  
		  	Type listType = new TypeToken<Wm_loading_advice_driver_dtlsDTO>() {}.getType();

			Wm_loading_advice_driver_dtlsDTO loadingDriverDtls= new ModelMapper().map(modelList,listType);
				
			return loadingDriverDtls;
	  }
	  
	  public Wm_loading_advice_trans_infoDTO getLoadingAdvTransInfoThruVehicle(String vehicle_id,String adv_type) {
		  List<Wm_loading_advice> advNoList=wm_loading_adviceRepository.loadingAdviceList(vehicle_id,adv_type);
		  
		  Wm_loading_advice_trans_info modelList=null;
		  
		    for(int i=0;i<advNoList.size();i++)
			{
			  	modelList=wm_loading_advice_trans_infoRepository.loadingTransInfoRetriveList(advNoList.get(0).getAdvice_id());
			}
		  
		  	Type listType = new TypeToken<Wm_loading_advice_trans_infoDTO>() {}.getType();

		  	Wm_loading_advice_trans_infoDTO loadingTransDtls= new ModelMapper().map(modelList,listType);
				
			return loadingTransDtls;
	  } 
	  
	  /*public List<Wm_loading_adviceDTO> getLoadingVehiThruSales() {
			List<Wm_loading_advice> loadingAdv=wm_loading_adviceRepository.getLoadingVehiThruSales();
			
			Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
			List<Wm_loading_adviceDTO> unloadAdvice = new ModelMapper().map(loadingAdv,listType);
			
			return unloadAdvice;
	  }*/
	  
	  public List<VehicleMaster> getLoadingVehiThruSales() {
		  
			List<Wm_loading_advice> loadingAdv=wm_loading_adviceRepository.getLoadingVehiThruSales();
			List<VehicleMaster> vehicleList=new ArrayList<VehicleMaster>();
			
			Set<String> vehiList =   
					loadingAdv.stream()  
		            .map(load->load.getVehicle_id())  
		            .collect(Collectors.toSet());
			vehiList.forEach((vehicle) -> {
				//System.err.println("Vehicle Number : "+vehicle);
				vehicleList.add(vehicleMasterRepository.getVehicleDetails(vehicle));
			});
			
			return vehicleList;
	  }
	     
	  public List<Wm_loading_adviceDTO> getLoadingVehiThruStkTransfer() {
			
			List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadingVehiThruStkTransfer();
			
			Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
			
			List<Wm_loading_adviceDTO> loading = new ModelMapper().map(modelList,listType);
			
			return loading;
		}
	    
	  public List<Wm_loading_adviceDTO> getLoadingVehiThruPR() {
			
			List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadingVehiThruPR();
			
			Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
			
			List<Wm_loading_adviceDTO> loading = new ModelMapper().map(modelList,listType);
			
			return loading;
		}
	    
	  public List<Wm_loading_adviceDTO> getLoadngAdviceThruVehicle(String vcode,String weighment)
		{
			List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadngAdviceThruVehicle(vcode,weighment);
		  //System.out.println("vcode/"+vcode+"weighment/"+weighment);
		  //List<Wm_loading_advice> modelList=wm_loading_adviceRepository.wm_loading_status_check(vcode,weighment);

			Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();

			List<Wm_loading_adviceDTO> dyna= new ModelMapper().map(modelList,listType);
			
			return dyna;
		}
	  
	  public List<Map<String, Object>> getLoadngAdviceThruVehiclefast(String vcode,String weighment)
	  {
		  
		  return wm_loading_adviceRepository.getLoadngAdviceThruVehiclefast(vcode,weighment);
	  }
	  
	  public List<Wm_loading_adviceDTO> getLoadingVehiThruBU(String dbunit,String cdate){
	    	
	    	List<Wm_loading_advice> finalAdv=new ArrayList<Wm_loading_advice>();
	    	
	    	finalAdv.addAll(wm_loading_adviceRepository.getLoadingAdvThruStkWithBill(dbunit,cdate));
	    	finalAdv.addAll(wm_loading_adviceRepository.getLoadingAdvThruStkWithoutBill(dbunit,cdate));
	    	
			Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
			
			List<Wm_loading_adviceDTO> buVehicle = new ModelMapper().map(finalAdv,listType);
			
			return buVehicle;
		}
	 
	public List<Wm_loading_adviceDTO> getLoadAdvThruWeighmentUpdate(Long id){
	
		Optional<Delivery_challan> op = delivery_challanRepository.findById(id);

		List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadAdvThruWeighmentUpdate(op.get().getReferance_id());
		
		Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
		List<Wm_loading_adviceDTO> lweList = new ModelMapper().map(modelList,listType);
		
		return lweList;
	}
	
	public List<Wm_loading_advice_itm_dtls> loadingItemRetriveListUpdate(Long id){
		
		Optional<Delivery_challan> op = delivery_challanRepository.findById(id);

		List<Delivery_challan_Item_Dtls> modelList=delivery_challan_Item_DtlsRepository.getDelivery_challan_Item_Dtls(op.get().getDelivery_cid());
		
		
		
		
		
		List<Wm_loading_advice_itm_dtls> wmloadingitemdetails =  wm_loading_adviceRepository.loadingItemRetriveListUpdate(op.get().getReferance_id());
		
		List<Wm_loading_advice_itm_dtls> newlistitemdetails =new ArrayList<Wm_loading_advice_itm_dtls>();
		
	
		for(int i=0;i<wmloadingitemdetails.size();i++) 
		{
			String Itemcode = wmloadingitemdetails.get(i).getItem_code();
		int v=i;
			modelList.forEach((e)->
			{
			
				if(e.getItem_code().compareToIgnoreCase(Itemcode)==0) 
				{
					
					newlistitemdetails.add(wmloadingitemdetails.get(v));
				}
			});
			
			

		}
		
		
		
		return newlistitemdetails;
}
	
	public StatusDTO checkVehicleNoWeighment(String veh_id,String action)
	{
		StatusDTO status=new StatusDTO();
		if(action.compareToIgnoreCase("Loading")==0)
		{
			
			//Gratter Than 0 Means Duplicate Vehicle Number
			if(wm_loading_adviceRepository.checkVehicleNo(veh_id))
			{
				status.setStatus("Yes");
			}
			else
			{
				status.setStatus("No");
			}
			
		}
		if(action.compareToIgnoreCase("Unloading")==0)
		{
			//System.out.println("Unloading"+veh_id);
			if(wm_unload_adviceRepository.checkVehicleNo(veh_id))
			{
				status.setStatus("Yes");
			}
			else
			{
				status.setStatus("No");
			}
		}
		
		return status;
	}
	
	public List<Wm_loading_adviceDTO> getLoadingAdviceDataList(String currDate,String finyear)
	{
		//System.out.println("currDate:"+currDate+"finyear:"+finyear);
		List<Wm_loading_advice> modelList=new ArrayList<Wm_loading_advice>();
		String tablename="wm_loading_advice",party_name="bus_partner",order_no="advice_no",order_date="advice_date";
		String orderno="",bus_partner1="";
		
		modelList.addAll(wm_loading_adviceRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,currDate,currDate,bus_partner1,finyear));
		
		
		//List<Wm_loading_advice> modelList=wm_loading_adviceRepository.getLoadingAdviceDataList(currDate);
		
		Type listType = new TypeToken<List<Wm_loading_adviceDTO>>() {}.getType();
		List<Wm_loading_adviceDTO> advList = new ModelMapper().map(modelList,listType);
		
		 for(int i=0;i<advList.size();i++) {
				if(Utility.isNullOrEmpty(advList.get(i).getLoad_point())) {
					advList.get(i).setLoad_point(loading_pointRepository.getLoadingPointDetails(advList.get(i).getLoad_point()).getLoading_name());
				}else {advList.get(i).setLoad_point("None");}
				
				if(Utility.isNullOrEmpty(advList.get(i).getCustomer())) {
					advList.get(i).setCustomer(cust_bussiness_partnerRepository.getCustomer(advList.get(i).getCustomer()).getCp_name());
				}else {advList.get(i).setCustomer("None");}
				
				if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,2).compareTo("SO")==0) {
					advList.get(i).setOrder_no(sales_OrderRepository.getSalesOrderDetails(advList.get(i).getReferance_id()).getOrder_no());
					advList.get(i).setOrder_date(sales_OrderRepository.getSalesOrderDetails(advList.get(i).getReferance_id()).getOrder_date());
				}
				else if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,2).compareTo("ST")==0) {
					advList.get(i).setOrder_no(stock_TransferRepository.getStockTransDtls(advList.get(i).getReferance_id()).getOrder_no());
					advList.get(i).setOrder_date(stock_TransferRepository.getStockTransDtls(advList.get(i).getReferance_id()).getOrder_date());
				}
				else if(Utility.isNullOrEmpty(advList.get(i).getReferance_id()) && advList.get(i).getReferance_id().substring(0,3).compareTo("PRA")==0) {
					advList.get(i).setOrder_no(pur_return_approval_noteRepository.getPurRtnAppNoteDtls(advList.get(i).getReferance_id()).getPurreturnno());
					advList.get(i).setOrder_date(pur_return_approval_noteRepository.getPurRtnAppNoteDtls(advList.get(i).getReferance_id()).getPurreturndate());
				}
				else {
					advList.get(i).setOrder_date("None");
					advList.get(i).setOrder_no("None");
				}
			}
		 
		return advList;
	}
	
	public List<Map<String,Object>>  getLoadingAdviceDataListfast(String currDate,String finyear)
	{
		
		List<Map<String,Object>> advList=wm_loading_adviceRepository.getsearchdatanewfast(currDate,finyear);
				
		return advList;
	}
	
	public List<Map<String, Object>> getLoadingAdviceReport(String fromdate,String todate,String finyear)
 	{
 		List<Map<String, Object>> advicelist=new ArrayList<Map<String, Object>>();
     	
 		advicelist.addAll(wm_loading_adviceRepository.getLoadingAdviceReport(fromdate,todate));
     
     	return advicelist;
 		
 	}
	
	public List<Map<String, Object>> getLoadingAdviceReportThrouhgSO(String soid)
	{
		ArrayList<String> solist = new ArrayList<>(Arrays.asList(soid.split(",")));
		
		return wm_loading_adviceRepository.getLoadingAdviceReportThrouhgSO(solist);
	}
	
	public Map<String, Object> getLoadItemTotalWt(String loadid)
	{
		System.out.println("load id string "+loadid);
		//return wm_loading_adviceRepository.getLoadItemTotalWt(loadid);
		
		String multiparty[]=loadid.split(",");
		ArrayList<String> customermultiple=new ArrayList<String>();
		for(int i=0;i<multiparty.length;i++)
		{
			customermultiple.add(multiparty[i]);
		}
			
		return wm_loading_adviceRepository.getLoadItemTotalWtjobwork(customermultiple);
	}
	
	public List<Wm_loading_advice_Item_Dtls_for_jobworkDTO> loadingItemjobworkRetriveList(String advceid)
	{
		List<Wm_loading_advice_Item_Dtls_for_jobwork> modelList=wm_loading_adviceRepository.loadingItemjobworkRetriveList(advceid);
		
		Type listType=new TypeToken<List<Wm_loading_advice_Item_Dtls_for_jobworkDTO>>() {}.getType();
		
		List<Wm_loading_advice_Item_Dtls_for_jobworkDTO> loadingJob=new ModelMapper().map(modelList,listType);
		
		return loadingJob;
	}
	
	public List<Map<String, Object>> loadadvicejobworkRetriveList(String loadid)
	{
		return wm_loading_adviceRepository.loadadvicejobworkRetriveList(loadid);
	}
	
	public StatusDTO checkAdviceinCash(String advicedate,String party,String ref_type,double total_amt)
	{
		StatusDTO res=new StatusDTO();
		System.out.println("Amt: : "+total_amt+" / "+"party : : "+party+" / "+"advice date : : "+advicedate);
		if(ref_type.compareToIgnoreCase("Sales Order")==0)
		{
			System.out.println("Yes if");
			System.out.println("if Amt "+total_amt);
			System.out.println("Check Res : : "+(wm_loading_adviceRepository.checkAdviceinCash(advicedate,party,total_amt)));
			res.setStatus(wm_loading_adviceRepository.checkAdviceinCash(advicedate,party,total_amt));
		}
		else
		{
			System.out.println("No else");
			res.setStatus("No");
		}
		System.out.println("Check : : "+(wm_loading_adviceRepository.checkAdviceinCash(advicedate,party,total_amt)));
		return res;
	}
	
	public StatusDTO checkAdviceinCashUpdate(String advicedate,String party,String ref_type,double total_amt,String advice_id)
	{
		StatusDTO res=new StatusDTO();
		double old_totalAmt=wm_loading_adviceRepository.getOldAdviceAmt(advice_id);
		System.out.println("OLD Amt : : "+old_totalAmt);
		System.out.println("New Amt : : "+(total_amt-old_totalAmt));
		System.out.println("Amt: : "+total_amt+" / "+"party : : "+party+" / "+"advice date : : "+advicedate+" / "+"advice id : : "+advice_id);
		if(ref_type.compareToIgnoreCase("Sales Order")==0)
		{
			res.setStatus(wm_loading_adviceRepository.checkAdviceinCashUpdate(advicedate,party,(total_amt-old_totalAmt)));
		}
		else
		{
			res.setStatus("No");
		}
		return res;
	}
	
	public StatusDTO custPayment(String advicedate,String party,String ref_type)
	{
		StatusDTO res=new StatusDTO();
		
		if(ref_type.compareToIgnoreCase("Sales Order")==0)	
		{
			res.setStatus(wm_loading_adviceRepository.custPayment(advicedate,party));
		}
		else
		{
			res.setStatus("No");	
		}
			
		return res;
	}
}
