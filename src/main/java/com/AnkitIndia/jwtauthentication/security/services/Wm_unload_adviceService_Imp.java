package com.AnkitIndia.jwtauthentication.security.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.Utility.FileUtil;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Charge_Master;
import com.AnkitIndia.jwtauthentication.model.Company_business_unit_master;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Driver_master;
import com.AnkitIndia.jwtauthentication.model.GatepassGateout;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Item_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_item_details;
import com.AnkitIndia.jwtauthentication.model.Pur_goods_receipt_other_information;
import com.AnkitIndia.jwtauthentication.model.Pur_ord_item_dtls_track;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_dynamic;
import com.AnkitIndia.jwtauthentication.model.Sales_transaction;
import com.AnkitIndia.jwtauthentication.model.Status_table;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Weighment_doc;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_Party_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_Shipment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_bp_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_doc_attch;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_driver_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_itm_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_trans_info;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_app_chgs;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_bp_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_doc;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_driver_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_item_dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_party_wm;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_terms_con;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_trans_info;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt_dtls;
import com.AnkitIndia.jwtauthentication.repository.BinMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Driver_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.GatepassGateoutRepository;
import com.AnkitIndia.jwtauthentication.repository.GatepassGetinRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_Order_Item_DetailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receiptRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receipt_item_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_goods_receipt_other_informationRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_ChallanRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.WarehouseMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_adviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_app_chgsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_bp_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_broker_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_docRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_driver_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_item_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_item_dtls_for_jobworkRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_party_wmRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_terms_conRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_advice_trans_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_wgmntRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Charge_MasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Charge_MasterdtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.DailygatewheatinwardreportDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Driver_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_brokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_goods_receipt_other_informationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Status_tableDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.VehicleMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_adviceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_app_chgsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_bp_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_driver_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_party_wmDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_terms_conDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmntDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Unload_Adv_Supp_DateDTO;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
@Repository
public class Wm_unload_adviceService_Imp implements Wm_unload_adviceService {
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	@Autowired
	LedgermasterRepository 	ledgermasterRepository;
	
	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	Wm_unload_wgmntRepository wm_unload_wgmntRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Trans_bussiness_partnerRepository trans_bussiness_partnerRepository;
	
	@Autowired
	Pur_Order_Item_DetailsRepository pur_Order_Item_DetailsRepository;
	
	@Autowired
	Wm_unload_advice_item_dtlsRepository wm_unload_advice_item_dtlsRepository;
	
	@Autowired
	Wm_unload_advice_app_chgsRepository wm_unload_advice_app_chgsRepository;
	
	@Autowired
	Wm_unload_advice_docRepository wm_unload_advice_docRepository;
	
	@Autowired
	Wm_unload_advice_bp_dtlsRepository wm_unload_advice_bp_dtlsRepository;
	
	@Autowired
	Wm_unload_advice_trans_infoRepository wm_unload_advice_trans_infoRepository;
	
	@Autowired
	Wm_unload_advice_terms_conRepository wm_unload_advice_terms_conRepository;
	
	@Autowired
	Wm_unload_advice_broker_dtlsRepository wm_unload_advice_broker_dtlsRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	WarehouseMasterRepository warehouseMasterRepository;
	
	@Autowired
	BinMasterRepository binMasterRepository;
	
	@Autowired
	Item_type_masterRepository item_type_masterRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository supp_bussiness_partnerRepository;
	
	@Autowired
	Return_approval_noteRepository return_approval_noteRepository;
	
	@Autowired
	Wm_loading_adviceRepository wm_loading_adviceRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Pur_OrderRepository pur_OrderRepository;
	
	@Autowired
	Stk_Transfer_ChallanRepository stk_Transfer_ChallanRepository;
	
	@Autowired
	Pur_good_receiptRepository pur_good_receiptRepository;
	
	@Autowired
	Pur_good_receipt_item_detailsRepository pur_good_receipt_item_detailsRepository;
	
	@Autowired
	GatepassGetinRepository gatepassGetinRepository;
	
	@Autowired
	GatepassGateoutRepository gatepassGateoutRepository;
	
	@Autowired
	 Driver_masterRepository driver_masterRepository;
	
	@Autowired
	Wm_unload_advice_item_dtls_for_jobworkRepository wm_unload_advice_item_dtls_for_jobworkRepository;
	
	public SequenceIdDTO getUASequenceId(String bunit,String orderdate)
	{
		int slno=0;String prefix="UA";
		String buprefix="";
		buprefix=companyBusinessUnitMasterRepository.CompanyBUAddress(bunit).getBusinessunit_name();
		
		//String dt=Utility.convertDate(orderdate);
		String sno=wm_unload_adviceRepository.countUAOrder(orderdate,bunit);
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		prefix=prefix+"-"+buprefix.substring(0,3)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno.toUpperCase());
		
		return genCode;
	}
	
	public SequenceIdDTO getUASequenceIdnew(String bunit,String orderdate)
	{
		int slno=0;String prefix="UA";
		String buprefix="";
		
		
		
		String sno=wm_unload_adviceRepository.countUAOrderNEW(orderdate,bunit);
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		prefix=prefix+"-PRW-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno.toUpperCase());
		
		return genCode;
	}
	
	@Transactional
	//public Wm_unload_advice save(Wm_unload_advice wAdvice)
	//{
	public Wm_unload_advice save(Wm_unload_advice wAdvice,MultipartFile files[]) throws IOException
	{
	
		LocalDateTime ldt = LocalDateTime.now();
		
		double tot_mat_wt=0.000;
		
		long slno=0;String prefix="WUL";
		
		if(wm_unload_adviceRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(wm_unload_adviceRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		wAdvice.setUnadviceid(gen_sno);
		wAdvice.setQc_status("No");
		wAdvice.setUnload_status(0);
		wAdvice.setWeighment_status(0);
		wAdvice.setAdv_po_tag_no("No");
		wAdvice.setAdv_po_tag_date("No");
		wAdvice.setPur_return_status("No");
		wAdvice.setPurreturnid("NA");
		
		/*Start checking transaction no for unique */
		System.err.println("First:>>"+wAdvice.getUnadviceno());
		long nslno=0;String tprefix="UA";
		String buprefix=companyBusinessUnitMasterRepository.CompanyBUAddress(wAdvice.getBusiness_unit()).getBusinessunit_name();
	
		if(wAdvice.getItem_subtype().compareToIgnoreCase("ITMT00008")==0) 
		{
			
			String tsno=wm_unload_adviceRepository.countUAOrderNEW(wAdvice.getUla_date(),wAdvice.getBusiness_unit());
			
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			
			String date[] = wAdvice.getUla_date().split("-");
			tprefix=tprefix+"-PRW-"+date[2]+date[1]+date[0].substring(2,4)+"-";
			String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
			wAdvice.setUnadviceno(gen_tslno);
		}
		else 
		{
			String tsno=wm_unload_adviceRepository.countUAOrder(wAdvice.getUla_date(),wAdvice.getBusiness_unit());
			
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			String date[] = wAdvice.getUla_date().split("-");
			tprefix=tprefix+"-"+buprefix.substring(0,3)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
			String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
			wAdvice.setUnadviceno(gen_tslno);
		}
		
	/*	String tsno=wm_unload_adviceRepository.countUAOrder(wAdvice.getUla_date(),wAdvice.getBusiness_unit());
				
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = wAdvice.getUla_date().split("-");
		tprefix=tprefix+"-"+buprefix.substring(0,3)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		wAdvice.setUnadviceno(gen_tslno);
		System.err.println("Last:>>>"+wAdvice.getUnadviceno());
		*/
		
		
		
		if(Utility.isNullOrEmpty(wAdvice.getItem_subtype())) {
			wAdvice.setItem_subtypename(item_type_masterRepository.getItemType(wAdvice.getItem_subtype()).getItem_name());
		}else {wAdvice.setItem_type("None");wAdvice.setItem_subtypename("None");}
		
		if(Utility.isNullOrEmpty(wAdvice.getBusiness_unit())) {
			wAdvice.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(wAdvice.getBusiness_unit()).getBusinessunit_name());
		}else {wAdvice.setBusiness_unitname("None");}
		
		if(Utility.isNullOrEmpty(wAdvice.getVehicle_id())) {
			wAdvice.setVehicle_no(vehicleMasterRepository.getVehicleDetails(wAdvice.getVehicle_id()).getVehicle_no());
		}else {wAdvice.setVehicle_no("None");}
		
		if(Utility.isNullOrEmpty(wAdvice.getTransporter_code())) {
			wAdvice.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(wAdvice.getTransporter_code()).getBp_name());
		}else {wAdvice.setTransporter_name("None");}
		
		if(wAdvice.getAdvice_type().compareTo("Sales Return")==0) {
			//Update Unload Adv Status
			return_approval_noteRepository.updateUnloadStatus(wAdvice.getReferance_id());
			
			//By default
			wAdvice.setItemtype(false);
			wAdvice.setPurchase_subtype("0");
		}
		else if(wAdvice.getAdvice_type().compareTo("Stock Transfer")==0) {
			//Update Loading Adv Status
			wm_loading_adviceRepository.updateLoadingStatus(wAdvice.getVehicle_id());
			
			//By default
			wAdvice.setItemtype(false);
			wAdvice.setPurchase_subtype("0");
		}else {
			System.out.println("Pur Ord No: "+wAdvice.getReferance_id());
			if(Utility.isNullOrEmpty(wAdvice.getReferance_id())) {
				
				if(wAdvice.getReferance_id().substring(0,2).compareTo("PO")==0) {
					wAdvice.setPurchase_subtype(pur_OrderRepository.getPurOrdDetails(wAdvice.getReferance_id()).getPur_ord_type());
					wAdvice.setItemtype(pur_OrderRepository.getPurOrdDetails(wAdvice.getReferance_id()).isSer_item_type());
				}else {
					wAdvice.setItemtype(false);
					wAdvice.setPurchase_subtype("0");
				}
			}else {
				System.out.println("Open Advice:");
				wAdvice.setItemtype(false);
				wAdvice.setPurchase_subtype("0");
			}
			
			if(wAdvice.getBusi_partner().compareTo("0") !=0 && wAdvice.getBusi_partner().compareTo("") !=0 && wAdvice.getBusi_partner() !=null) {
				wAdvice.setSupp_name(supp_bussiness_partnerRepository.getSupplierName(wAdvice.getBusi_partner()).getBp_name());
			}else {wAdvice.setSupp_name("None");}
		}
		/* new 14-04-2022 Start */
		if(wAdvice.getRef_type().compareToIgnoreCase("Purchase Order")==0)
		{
			
			double update_total_qty=wAdvice.getTotal_qty();
			wAdvice.setTotal_qty(wAdvice.getTotal_qty_copy());  
			wAdvice.setTotal_qty_copy(update_total_qty);
			
			
			//pur_order_view
			System.out.println("reference id : "+wAdvice.getReferance_id());
			double rest_weight =wm_unload_adviceRepository.getpurchaserestwt(wAdvice.getReferance_id());
			System.out.println("rest_weight: "+rest_weight);
			if(rest_weight == 0.000) 
			{
				wm_unload_adviceRepository.getpurOrderUnloadUpdate(wAdvice.getReferance_id());
			}
			if(wAdvice.getItem_subtypename().compareToIgnoreCase("STORE PURCHASE") ==0) 
			{
				wm_unload_adviceRepository.getpurOrderUnloadUpdate(wAdvice.getReferance_id());
			}
			
		}
		else
		{
			if(wAdvice.getAdvice_type().compareTo("Sales Return")==0) 
			{
				if(wAdvice.isJobwork()) 
				{
					wAdvice.setItem_subtype("ITMT00009");
					wAdvice.setItem_subtypename("JOB WORK");
				}
				else
				{
					wAdvice.setItem_subtype("ITMT00003");
					wAdvice.setItem_subtypename("FINISHED PRODUCTS");
				}
				
			}
			else 
			{
				//double update_total_qty=wAdvice.getTotal_qty();
				wAdvice.setTotal_qty(wAdvice.getTotal_qty_copy());  
				wAdvice.setTotal_qty_copy(wAdvice.getTotal_qty_copy());
			}
			
		}
		if(wAdvice.getRef_type().compareToIgnoreCase("Stock Transfer")==0)
		{
			wAdvice.setBusi_partner("None");
			stk_Transfer_ChallanRepository.updateUnloadChallanStatus(wAdvice.getReferance_id());
		}
			
		
	
		/* new 14-04-2022 end */
		wAdvice.setUadvice_status("Open");  /* new 14-04-2022 */
		wAdvice.setModified_type("INSERTED");
		wAdvice.setInserted_by(userRepository.getUserDetails(wAdvice.getUsername()).getName());
		wAdvice.setInserted_on(ldt);
		wAdvice.setUpdated_by("NA");
		wAdvice.setUpdated_on(ldt);
		wAdvice.setDeleted_by("NA");
		wAdvice.setDeleted_on(ldt);
		wAdvice.setUladate(wAdvice.getUla_date());
		
		//new vehicle records table starts
		//System.out.println("vehicle:"+wAdvice.getVehicle_id());
		vehicleMasterRepository.updateStatus(wAdvice.getVehicle_id(),true);//AFTER UNLOAD ADVICE THE VEHICLE IS NOT SHOWN IN UNLODING ADVICE VEHICLE LIST UNTILL 2ND WEIGHMENT COMPLETE
		vehicleMasterRepository.updateVehicleWeighmentLocation(wAdvice.getVehicle_id(),wAdvice.getWeight_bridge_location());
		
		Vehicle_weighment_load_unload  vehicle_weighment_load_unload = new Vehicle_weighment_load_unload();
		
		vehicle_weighment_load_unload.setVehicle_no(wAdvice.getVehicle_no());
		vehicle_weighment_load_unload.setVehicle_id(wAdvice.getVehicle_id());
		vehicle_weighment_load_unload.setReference_id(wAdvice.getUnadviceid());
		vehicle_weighment_load_unload.setRef_name("Unload Advice");
		vehicle_weighment_load_unload.setCompany_id(wAdvice.getCompany_id());
		vehicle_weighment_load_unload.setFin_year(wAdvice.getFin_year());
		vehicle_weighment_load_unload.setUsername(wAdvice.getUsername());
		vehicle_weighment_load_unload.setModified_type("INSERTED");
		vehicle_weighment_load_unload.setInserted_by(userRepository.getUserDetails(wAdvice.getUsername()).getName());
		vehicle_weighment_load_unload.setInserted_on(ldt);
		vehicle_weighment_load_unload.setUpdated_by("NA");
		vehicle_weighment_load_unload.setUpdated_on(ldt);
		vehicle_weighment_load_unload.setDeleted_by("NA");
		vehicle_weighment_load_unload.setDeleted_on(ldt);
		vehicle_weighment_load_unload.setRef_name_type(wAdvice.getRef_type());
		vehicle_weighment_load_unload.setGatepass_status("NA");
		vehicle_weighment_load_unload.setWe_req(wAdvice.isWe_req());
		vehicle_weighment_load_unload.setWeight_bridge_location(wAdvice.getWeight_bridge_location());	
		
		wAdvice.setVehicle_weighment_load_unload(vehicle_weighment_load_unload);
		//ends
		
		if(wAdvice.isJobwork()) 
		{
			wAdvice.getWm_unload_advice_item_dtls().clear();
			
			Set<Wm_unload_advice_item_dtls_for_jobwork> jobSet = new HashSet<Wm_unload_advice_item_dtls_for_jobwork>();
			jobSet.addAll(wAdvice.getWm_unload_advice_item_dtls_for_jobwork());
			for(Wm_unload_advice_item_dtls_for_jobwork jobDts : jobSet)
			{
				jobDts.setUnadviceid(gen_sno);
				jobDts.setUnadviceno(wAdvice.getUnadviceno());
				
				jobDts.setJob_item_name(item_masterRepository.itemNameById(jobDts.getJob_item()).getItem_name());
				if(Utility.isNullOrEmpty(jobDts.getJob_packing())) 
				{
					jobDts.setJob_packing_name(item_masterRepository.itemNameById(jobDts.getJob_packing()).getItem_name());
				}
				else
				{
					jobDts.setJob_packing_name("0");
				}
				jobDts.setCompany_id(wAdvice.getCompany_id());
				jobDts.setFin_year(wAdvice.getFin_year());
				jobDts.setModified_type("INSERTED");
				jobDts.setInserted_by(wAdvice.getInserted_by());
				jobDts.setInserted_on(wAdvice.getInserted_on());
				jobDts.setUpdated_by("NA");
				jobDts.setUpdated_on(ldt);
				jobDts.setDeleted_by("NA");
				jobDts.setDeleted_on(ldt);
				
			}
			wAdvice.setWm_unload_advice_item_dtls_for_jobwork(jobSet);
		}
		else
		{
			wAdvice.getWm_unload_advice_item_dtls_for_jobwork().clear();
			
			//Dynamic
			Set<Wm_unload_advice_item_dtls> itemSet = new HashSet<Wm_unload_advice_item_dtls>();
			itemSet.addAll(wAdvice.getWm_unload_advice_item_dtls());
			for(Wm_unload_advice_item_dtls itemDts : itemSet)
			{
				//14-04-2022 update quantity check in pur ord itm dtls
				 if(wAdvice.getRef_type().compareToIgnoreCase("Purchase Order")==0)
				   {
					// pur_Order_Item_DetailsRepository.updateTotalItemQuantity(itemDts.getMat_wt(),itemDts.getPur_dyn_id());//itemDts.getMat_wt()=final_mat_wt calculated backup & itemDts.getPur_dyn_id() = dynamic table id =12859
				   }
				 
				itemDts.setUnadviceid(gen_sno);
				itemDts.setUnadviceno(wAdvice.getUnadviceno());
				itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
				if(itemDts.getPacking().compareTo("")!=0 && itemDts.getPacking().compareTo("0")!=0) {
					itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
				}
				itemDts.setCompany_id(wAdvice.getCompany_id());
				itemDts.setFin_year(wAdvice.getFin_year());
				itemDts.setModified_type("INSERTED");
				itemDts.setInserted_by(wAdvice.getInserted_by());
				itemDts.setInserted_on(wAdvice.getInserted_on());
				itemDts.setUpdated_by("NA");
				itemDts.setUpdated_on(ldt);
				itemDts.setDeleted_by("NA");
				itemDts.setDeleted_on(ldt);
				
				//14-04-2022  quantity check  pur ord track
				
				itemDts.setPur_orderid(wAdvice.getReferance_id());
				
				tot_mat_wt+=itemDts.getMat_wt();
				
				/*Pur_ord_item_dtls_track pur=new Pur_ord_item_dtls_track();
				pur.setPur_orderid(wAdvice.getReferance_id());//purchaseorderid
				pur.setPur_dyn_id(itemDts.getPur_dyn_id());
				pur.setUadvice_id(gen_sno);//dynamic unload id
				pur.setMat_wt(itemDts.getMat_wt());//dynamic weight
				itemDts.setPur_ord_item_dtls_track(pur);
				*/
				
			}
			wAdvice.setWm_unload_advice_item_dtls(itemSet);
		}
		
		
		//14-04-2022 update quantity check in pur ord 
		
		if(wAdvice.getRef_type().compareToIgnoreCase("Purchase Order")==0)
		   {
			/*List <Pur_Order> purord= new ArrayList<Pur_Order>();
			purord.addAll(pur_OrderRepository.getsubtype(wAdvice.getReferance_id()));
			String no_of_adv=purord.get(0).getCal_no_of_advice();
			if(no_of_adv.compareToIgnoreCase("0")==0)
			{	
			}
			else
			{}*/
			
			
			//pur_OrderRepository.updateTotalQuantity(tot_mat_wt,wAdvice.getReferance_id());//tot_mat_wt = total_qty_copy & getReferance_id=pur ord_id(po0001)
			
			  
		   }
		
		//14-04-2022 end
		//Static
		Set<Wm_unload_advice_party_wm> ptySet = new HashSet<Wm_unload_advice_party_wm>();
		ptySet.add(wAdvice.getWm_unload_advice_party_wm());
		for(Wm_unload_advice_party_wm wParty_wm : ptySet)
		{
			wParty_wm.setUnadviceid(gen_sno);
			wParty_wm.setUnadviceno(wAdvice.getUnadviceno());
			wParty_wm.setCompany_id(wAdvice.getCompany_id());
			wParty_wm.setFin_year(wAdvice.getFin_year());
			wParty_wm.setModified_type("INSERTED");
			wParty_wm.setInserted_by(wAdvice.getInserted_by());
			wParty_wm.setInserted_on(wAdvice.getInserted_on());
			wParty_wm.setUpdated_by("NA");
			wParty_wm.setUpdated_on(ldt);
			wParty_wm.setDeleted_by("NA");
			wParty_wm.setDeleted_on(ldt);
		}
		
		if(!ptySet.isEmpty())
		{
			wAdvice.setWm_unload_advice_party_wm(ptySet.iterator().next());
		}
		
		//Static
		Set<Wm_unload_advice_driver_dtls> driverSet = new HashSet<Wm_unload_advice_driver_dtls>();
		driverSet.add(wAdvice.getWm_unload_advice_driver_dtls());
		for(Wm_unload_advice_driver_dtls driver_dtls : driverSet)
		{
			driver_dtls.setUnadviceid(gen_sno);
			driver_dtls.setUnadviceno(wAdvice.getUnadviceno());
			driver_dtls.setCompany_id(wAdvice.getCompany_id());
			driver_dtls.setFin_year(wAdvice.getFin_year());
			driver_dtls.setModified_type("INSERTED");
			driver_dtls.setInserted_by(wAdvice.getInserted_by());
			driver_dtls.setInserted_on(wAdvice.getInserted_on());
			driver_dtls.setUpdated_by("NA");
			driver_dtls.setUpdated_on(ldt);
			driver_dtls.setDeleted_by("NA");
			driver_dtls.setDeleted_on(ldt);
		}
		
		if(!driverSet.isEmpty())
		{
			wAdvice.setWm_unload_advice_driver_dtls(driverSet.iterator().next());
		}
		
		//Dynamic
		Set<Wm_unload_advice_broker_dtls> brokerSet = new HashSet<Wm_unload_advice_broker_dtls>();
		brokerSet.addAll(wAdvice.getWm_unload_advice_broker_dtls());
		for(Wm_unload_advice_broker_dtls broker_dtls : brokerSet)
		{
			broker_dtls.setUnadviceid(gen_sno);
			broker_dtls.setUnadviceno(wAdvice.getUnadviceno());
		//here changes start	brokerNameByCode
		if(wAdvice.getRef_type().compareToIgnoreCase("Open Advice") !=0 && wAdvice.getRef_type().compareToIgnoreCase("Stock Transfer") !=0) 
		{
			if(Utility.isNullOrEmpty(broker_dtls.getVen_name())) 
			{
				broker_dtls.setVen_name(broker_masterRepository.brokerNameByCode(broker_dtls.getVen_code_name()).getName());
			}
			
		}

			
		//here changes ends	
		System.out.println("0");
			broker_dtls.setCompany_id(wAdvice.getCompany_id());
			System.out.println("1");
			broker_dtls.setFin_year(wAdvice.getFin_year());
			System.out.println("2");
			broker_dtls.setModified_type("INSERTED");
			System.out.println("3");
			broker_dtls.setInserted_by(wAdvice.getInserted_by());
			broker_dtls.setInserted_on(wAdvice.getInserted_on());
			broker_dtls.setUpdated_by("NA");
			broker_dtls.setUpdated_on(ldt);
			broker_dtls.setDeleted_by("NA");
			broker_dtls.setDeleted_on(ldt);
		}
		wAdvice.setWm_unload_advice_broker_dtls(brokerSet);
		
		//Static
		Set<Wm_unload_advice_terms_con> termSet = new HashSet<Wm_unload_advice_terms_con>();
		termSet.add(wAdvice.getWm_unload_advice_terms_con());
		for(Wm_unload_advice_terms_con terms_con : termSet)
		{
			terms_con.setUnadviceid(gen_sno);
			terms_con.setUnadviceno(wAdvice.getUnadviceno());
			terms_con.setCompany_id(wAdvice.getCompany_id());
			terms_con.setFin_year(wAdvice.getFin_year());
			terms_con.setModified_type("INSERTED");
			terms_con.setInserted_by(wAdvice.getInserted_by());
			terms_con.setInserted_on(wAdvice.getInserted_on());
			terms_con.setUpdated_by("NA");
			terms_con.setUpdated_on(ldt);
			terms_con.setDeleted_by("NA");
			terms_con.setDeleted_on(ldt);
		}
		
		if(!termSet.isEmpty())
		{
			wAdvice.setWm_unload_advice_terms_con(termSet.iterator().next());
		}
		
		//Static
		Set<Wm_unload_advice_trans_info> infoSet = new HashSet<Wm_unload_advice_trans_info>();
		infoSet.add(wAdvice.getWm_unload_advice_trans_info());
		for(Wm_unload_advice_trans_info info : infoSet)
		{
			info.setUnadviceid(gen_sno);
			info.setUnadviceno(wAdvice.getUnadviceno());
			info.setCompany_id(wAdvice.getCompany_id());
			info.setFin_year(wAdvice.getFin_year());
			info.setModified_type("INSERTED");
			info.setInserted_by(wAdvice.getInserted_by());
			info.setInserted_on(wAdvice.getInserted_on());
			info.setUpdated_by("NA");
			info.setUpdated_on(ldt);
			info.setDeleted_by("NA");
			info.setDeleted_on(ldt);
		}
		
		if(!infoSet.isEmpty())
		{
			wAdvice.setWm_unload_advice_trans_info(infoSet.iterator().next());
		}
		
		//Static
		Set<Wm_unload_advice_bp_dtls> bpSet = new HashSet<Wm_unload_advice_bp_dtls>();
		bpSet.add(wAdvice.getWm_unload_advice_bp_dtls());
		for(Wm_unload_advice_bp_dtls bp_dtls : bpSet)
		{
			bp_dtls.setUnadviceid(gen_sno);
			bp_dtls.setUnadviceno(wAdvice.getUnadviceno());
			bp_dtls.setCompany_id(wAdvice.getCompany_id());
			bp_dtls.setFin_year(wAdvice.getFin_year());
			bp_dtls.setModified_type("INSERTED");
			bp_dtls.setInserted_by(wAdvice.getInserted_by());
			bp_dtls.setInserted_on(wAdvice.getInserted_on());
			bp_dtls.setUpdated_by("NA");
			bp_dtls.setUpdated_on(ldt);
			bp_dtls.setDeleted_by("NA");
			bp_dtls.setDeleted_on(ldt);
		}
		
		if(!bpSet.isEmpty())
		{
			wAdvice.setWm_unload_advice_bp_dtls(bpSet.iterator().next());
		}
		
		//Dynamic
		/*Set<Wm_unload_advice_doc> docSet = new HashSet<Wm_unload_advice_doc>();
		docSet.addAll(wAdvice.getWm_unload_advice_docs());
		for(Wm_unload_advice_doc advice_doc : docSet)
		{
			advice_doc.setUnadviceid(gen_sno);
			advice_doc.setUnadviceno(wAdvice.getUnadviceno());
			advice_doc.setCompany_id(wAdvice.getCompany_id());
			advice_doc.setFin_year(wAdvice.getFin_year());
			advice_doc.setModified_type("INSERTED");
			advice_doc.setInserted_by(wAdvice.getInserted_by());
			advice_doc.setInserted_on(wAdvice.getInserted_on());
			advice_doc.setUpdated_by("NA");
			advice_doc.setUpdated_on(ldt);
			advice_doc.setDeleted_by("NA");
			advice_doc.setDeleted_on(ldt);
		
		}
		wAdvice.setWm_unload_advice_docs(docSet);
		*/
		//Dynamic
		
		
		//For Document Details start ........................................
		Set<Wm_unload_advice_doc> docSet = new HashSet<Wm_unload_advice_doc>();
		docSet.addAll(wAdvice.getWm_unload_advice_docs());
		int i=0;
		for(Wm_unload_advice_doc advice_doc : docSet)
		{
		
			if(files.length > 0) 
			{
				try 
				{
					advice_doc.setDoc_pdf(fileUpload(files[i],gen_sno+"_"));		
				i++;
				}
				catch (IOException e)
				{
					System.out.println(e);
				}
				
			}
			System.out.println("3 :: ");
			//here ends
			
			advice_doc.setUnadviceid(gen_sno);
			advice_doc.setUnadviceno(wAdvice.getUnadviceno());
			advice_doc.setCompany_id(wAdvice.getCompany_id());
			advice_doc.setFin_year(wAdvice.getFin_year());
			advice_doc.setModified_type("INSERTED");
			advice_doc.setInserted_by(wAdvice.getInserted_by());
			advice_doc.setInserted_on(wAdvice.getInserted_on());
			advice_doc.setUpdated_by("NA");
			advice_doc.setUpdated_on(ldt);
			advice_doc.setDeleted_by("NA");
			advice_doc.setDeleted_on(ldt);
		}
		wAdvice.setWm_unload_advice_docs(docSet);
		
		
		//For Document Details end ........................................
		
		
		Set<Wm_unload_advice_app_chgs> chgsSet = new HashSet<Wm_unload_advice_app_chgs>();
		chgsSet.addAll(wAdvice.getWm_unload_advice_app_chgs());
		for(Wm_unload_advice_app_chgs wApp_chgs : chgsSet)
		{
			wApp_chgs.setUnadviceid(gen_sno);
			wApp_chgs.setUnadviceno(wAdvice.getUnadviceno());
			wApp_chgs.setCompany_id(wAdvice.getCompany_id());
			wApp_chgs.setFin_year(wAdvice.getFin_year());
			wApp_chgs.setModified_type("INSERTED");
			wApp_chgs.setInserted_by(wAdvice.getInserted_by());
			wApp_chgs.setInserted_on(wAdvice.getInserted_on());
			wApp_chgs.setUpdated_by("NA");
			wApp_chgs.setUpdated_on(ldt);
			wApp_chgs.setDeleted_by("NA");
			wApp_chgs.setDeleted_on(ldt);
		
		}
		wAdvice.setWm_unload_advice_app_chgs(chgsSet);
		System.out.println("get pur id/"+wAdvice.getPur_orderid());
		//wm_unload_adviceRepository.unload_advice_updation(wAdvice.getPur_orderid());
		return wm_unload_adviceRepository.save(wAdvice);
	}
	
	
	private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File(FileUtil.folderPathunload);
        if (! directory.exists()){
            directory.mkdir();
        }
    }
	
	@Transactional
	public  String fileUpload(@RequestParam("files") MultipartFile files,String fileName) throws IOException
	{
		 
				createDirIfNotExist();
			
				
		
		    String  files_name = FileUtil.folderPathunload+fileName+files.getOriginalFilename();
		            	 
		            	 File convertFile = new File(FileUtil.folderPathunload+fileName+files.getOriginalFilename());
		                 convertFile.createNewFile();
		                 FileOutputStream fout = new FileOutputStream(convertFile);
		                 fout.write(files.getBytes());
		                 fout.close();
		 
		            return files_name;
	}
	
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	public String getBusinesspartnername (String addtype,String bussunit,String customer) 
	{
		String name="";
		if(addtype.compareToIgnoreCase("Purchase Order") == 0) 
		{
			 name = supp_bussiness_partnerRepository.getSupplierThruBUstring(bussunit);	
		}
		if(addtype.compareToIgnoreCase("Sales Return") == 0) 
		{
			 name = cust_bussiness_partnerRepository.getCustomerThruBUstring(customer);	
		}
		
		
		return name;
	}
	
	public List<Wm_unload_advice> findAll()
	{
		List<Wm_unload_advice> unAdvList=new ArrayList<Wm_unload_advice>();
		unAdvList.addAll(wm_unload_adviceRepository.findAllUnloadAdvices());
		
		unAdvList.forEach((e)->{
			if(e.getRef_type().compareToIgnoreCase("Stock Transfer")==0)
			{
				e.setCustomer("None");
			}
			else
			{
				e.setCustomer(getBusinesspartnername(e.getAdvice_type(),e.getBusi_partner(),e.getCustomer()));
			}
			
		});
		
		return unAdvList;
	}
	
	public Page<Wm_unload_advice_Pagination_DTO> getUnloadAdvicePagination(int page,int size)
	  {
			//PageRequest pageRequest = PageRequest.of(page, size,Sort.by("id").descending());
			PageRequest pageRequest = PageRequest.of(page, size,Sort.by("uladate").descending().and(Sort.by("unadviceno")).descending());
		    Page<Wm_unload_advice> pageResult = wm_unload_adviceRepository.findAll(pageRequest);
		   
		    List<Wm_unload_advice_Pagination_DTO> unAdvList = pageResult
		    	      .stream()
		    	      .map((Wm_unload_advice wm_unload_advice) -> new Wm_unload_advice_Pagination_DTO(wm_unload_advice.getId(),
		    	    		  wm_unload_advice.getUnadviceid(),
		    	    		  wm_unload_advice.getUnadviceno(),
		    	    		  wm_unload_advice.getUla_date(),
		    	    		  wm_unload_advice.getCustomer(),
		    	    		  wm_unload_advice.getBusiness_unitname(),
		    	    		  wm_unload_advice.getItem_type(),
		    	    		  wm_unload_advice.getItem_subtypename(),
		    	    		  wm_unload_advice.getVehicle_no(),
		    	    		  wm_unload_advice.isWe_req(),
		    	    		  wm_unload_advice.getRef_type(),
		    	    		  wm_unload_advice.getModified_type(),
		    	    		  wm_unload_advice.getAdvice_type(),
		    	    		  wm_unload_advice.getBusi_partner(),
		    	    		  wm_unload_advice.getWeighment_status()
		    	    		  
		    	    		  
		    	    		)).filter(c -> c.getModified_type().equals("INSERTED")).collect(Collectors.toList());
		    	      
		    unAdvList.forEach((e)->{
				if(e.getRef_type().compareToIgnoreCase("Stock Transfer")==0)
				{
					e.setCustomer("None");
				}
				else
				{
					e.setCustomer(getBusinesspartnername(e.getAdvice_type(),e.getBusi_partner(),e.getCustomer()));
				}
				
			});
		    
		    return new PageImpl<>(unAdvList, pageRequest, pageResult.getTotalElements());
	  }
	
	public List<Wm_unload_advice_Pagination_DTO> searchUnloadAdvice(String orderno,String fromdate, String todate,String bus_partner1,String finyear)
	{
		List<Wm_unload_advice> serchunloadadvice =new ArrayList<Wm_unload_advice>();
		System.out.println("finyear :: "+finyear);
		String tablename="wm_unload_advice",party_name="busi_partner",order_no="unadviceno",order_date="ula_date";
		System.out.println("tablename: "+tablename+" party_name: "+party_name+" order_no "+order_no+" order_date "+order_date+" orderno "+orderno+" fromdate "+fromdate+" todate "+todate+" bus_partner1 "+bus_partner1+" finyear "+finyear);
		serchunloadadvice.addAll(wm_unload_adviceRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,bus_partner1,finyear));
		
		System.out.println(serchunloadadvice.size());
		List<Wm_unload_advice_Pagination_DTO> unAdvList = new ArrayList<Wm_unload_advice_Pagination_DTO>();
		for(int i=0;i<serchunloadadvice.size();i++) 
		{
		
			Wm_unload_advice_Pagination_DTO sale= new Wm_unload_advice_Pagination_DTO(serchunloadadvice.get(i).getId(),
					serchunloadadvice.get(i).getUnadviceid(),
					serchunloadadvice.get(i).getUnadviceno(),
					serchunloadadvice.get(i).getUla_date(),
					serchunloadadvice.get(i).getCustomer(),
					serchunloadadvice.get(i).getBusiness_unitname(),
					serchunloadadvice.get(i).getItem_type(),
					serchunloadadvice.get(i).getItem_subtypename(),
					serchunloadadvice.get(i).getVehicle_no(),
					serchunloadadvice.get(i).isWe_req(),
					serchunloadadvice.get(i).getRef_type(),
					serchunloadadvice.get(i).getModified_type(),
					serchunloadadvice.get(i).getAdvice_type(),
					serchunloadadvice.get(i).getBusi_partner(),
					serchunloadadvice.get(i).getWeighment_status());
			
			unAdvList.add(sale);
		}
		
		   unAdvList.forEach((e)->{
				if(e.getRef_type().compareToIgnoreCase("Stock Transfer")==0)
				{
					e.setCustomer("None");
				}
				else
				{
					e.setCustomer(getBusinesspartnername(e.getAdvice_type(),e.getBusi_partner(),e.getCustomer()));
				}
				
			});
		
		   List<Wm_unload_advice_Pagination_DTO> finalData = unAdvList
					  .stream()
					  .filter(c -> c.getModified_type().equals("INSERTED"))
					  .sorted(Comparator.comparing(Wm_unload_advice_Pagination_DTO::getUla_date).
							  thenComparingInt(
	                          d -> d.getUla_date().length() + d.getUnadviceno().length())
	                  .thenComparing(Wm_unload_advice_Pagination_DTO::getUnadviceno).reversed())
					  .collect(Collectors.toList());
			
		//System.out.println(paginationlist.get(0));
		return finalData;
		
		
	}
	
	@Transactional
	public Wm_unload_advice update(Wm_unload_advice wAdvice,Long id)
	{
		Optional<Wm_unload_advice> op = wm_unload_adviceRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		//wAdvice.setPur_orderid(op.get().getPur_orderid());
		//wAdvice.setUnload_status(0);
		//wAdvice.setWeighment_status(0);
		System.out.println("Update Main Fields:: "+" /Po Id/ "+op.get().getPur_orderid()+" /Wgt Id/ "+op.get().getWeighment_id()+
				" /Wgt Sts/ "+op.get().getWeighment_status()+" /UN Sts/ "+op.get().getUnload_status());
		wAdvice.setAdv_po_tag_no("No");
		wAdvice.setQc_status(op.get().getQc_status());		
		wAdvice.setWeighment_id(op.get().getWeighment_id());
		wAdvice.setWeighment_status(op.get().getWeighment_status());
		wAdvice.setUnload_status(op.get().getUnload_status());
		
		if(Utility.isNullOrEmpty(wAdvice.getItem_subtype())) {
			wAdvice.setItem_subtypename(item_type_masterRepository.getItemType(wAdvice.getItem_subtype()).getItem_name());
		}else {wAdvice.setItem_type("None");wAdvice.setItem_subtypename("None");}
		
		if(Utility.isNullOrEmpty(wAdvice.getBusiness_unit())) {
			wAdvice.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(wAdvice.getBusiness_unit()).getBusinessunit_name());
		}else {wAdvice.setBusiness_unitname("None");}
		
		if(Utility.isNullOrEmpty(wAdvice.getVehicle_id())) {
			wAdvice.setVehicle_no(vehicleMasterRepository.getVehicleDetails(wAdvice.getVehicle_id()).getVehicle_no());
		}else {wAdvice.setVehicle_no("None");}
		
		if(Utility.isNullOrEmpty(wAdvice.getTransporter_code())) {
			wAdvice.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(wAdvice.getTransporter_code()).getBp_name());
		}else {wAdvice.setTransporter_name("None");}
				
		if(wAdvice.getAdvice_type().compareTo("Sales Return")==0) {
			//Update Unload Adv Status
			return_approval_noteRepository.updateUnloadStatus(wAdvice.getReferance_id());
		}
		else if(wAdvice.getAdvice_type().compareTo("Stock Transfer")==0) {
			//Update Loading Adv Status
			wm_loading_adviceRepository.updateLoadingStatus(wAdvice.getVehicle_id());
		}
		else
		{
			
			/*System.out.println("Pur Ord No: "+wAdvice.getReferance_id());
			if(wAdvice.getRef_type().compareToIgnoreCase("Purchase Order")==0)
			{
				if(wAdvice.getReferance_id().substring(0,2).compareTo("PO")==0) 
				{
					wAdvice.setPurchase_subtype(pur_OrderRepository.getPurOrdDetails(wAdvice.getReferance_id()).getPur_ord_type());
					wAdvice.setItemtype(pur_OrderRepository.getPurOrdDetails(wAdvice.getReferance_id()).isSer_item_type());
				}
			}
			if(wAdvice.getRef_type().compareToIgnoreCase("Open Advice")==0)
			{
				wAdvice.setItemtype(false);
				wAdvice.setPurchase_subtype("0");
			}
			
			/*if(wAdvice.getReferance_id().substring(0,2).compareTo("PO")==0) 
				{
					wAdvice.setPurchase_subtype(pur_OrderRepository.getPurOrdDetails(wAdvice.getReferance_id()).getPur_ord_type());
					wAdvice.setItemtype(pur_OrderRepository.getPurOrdDetails(wAdvice.getReferance_id()).isSer_item_type());
				}
			 else
				{
					wAdvice.setItemtype(false);
					wAdvice.setPurchase_subtype("0");
				}*/
			
		/*	if(wAdvice.getBusi_partner().compareTo("0") !=0 && wAdvice.getBusi_partner().compareTo("") !=0 && wAdvice.getBusi_partner() !=null) {
				wAdvice.setSupp_name(supp_bussiness_partnerRepository.getSupplierName(wAdvice.getBusi_partner()).getBp_name());
			}else {wAdvice.setSupp_name("None");} */
			
			
			
			
			if(Utility.isNullOrEmpty(wAdvice.getReferance_id())) {
				
				if(wAdvice.getReferance_id().substring(0,2).compareTo("PO")==0) {
					wAdvice.setPurchase_subtype(pur_OrderRepository.getPurOrdDetails(wAdvice.getReferance_id()).getPur_ord_type());
					wAdvice.setItemtype(pur_OrderRepository.getPurOrdDetails(wAdvice.getReferance_id()).isSer_item_type());
				}else {
					wAdvice.setItemtype(false);
					wAdvice.setPurchase_subtype("0");
				}
			}else {
				System.out.println("Open Advice:");
				wAdvice.setItemtype(false);
				wAdvice.setPurchase_subtype("0");
			}
			
			if(wAdvice.getBusi_partner().compareTo("0") !=0 && wAdvice.getBusi_partner().compareTo("") !=0 && wAdvice.getBusi_partner() !=null) {
				wAdvice.setSupp_name(supp_bussiness_partnerRepository.getSupplierName(wAdvice.getBusi_partner()).getBp_name());
			}else {wAdvice.setSupp_name("None");}
			
			
		}
		/* new 14-04-2022 Start */
		/*if(wAdvice.getRef_type().compareToIgnoreCase("Purchase Order")==0)
		{*/
			
		//	double update_total_qty=wAdvice.getTotal_qty();
			//wAdvice.setTotal_qty(wAdvice.getTotal_qty_copy());  
		//	wAdvice.setTotal_qty_copy(update_total_qty);
			
			
		/*}
		else
		{
			//wAdvice.setTotal_qty(wAdvice.getTotal_qty());  //same as purchase order
			double update_total_qty2=wAdvice.getTotal_qty();
			wAdvice.setTotal_qty(wAdvice.getTotal_qty_copy());  
			wAdvice.setTotal_qty_copy(update_total_qty2);
		}*/
		/* new 14-04-2022 end */
			if(wAdvice.getRef_type().compareToIgnoreCase("Purchase Order")==0)
			{
				
				double update_total_qty=wAdvice.getTotal_qty();
				wAdvice.setTotal_qty(wAdvice.getTotal_qty_copy());  
				wAdvice.setTotal_qty_copy(update_total_qty);
				
				wm_unload_adviceRepository.getpurOrderUnloadRevert(op.get().getReferance_id());
				
				
				double rest_weight =wm_unload_adviceRepository.getpurchaserestwt(op.get().getReferance_id());
				System.out.println("rest_weight: "+rest_weight);
				if(rest_weight == 0.000) 
				{
					wm_unload_adviceRepository.getpurOrderUnloadUpdate(op.get().getReferance_id());
				}
				
				
				
				
			}
			else
			{
				if(wAdvice.getAdvice_type().compareTo("Sales Return")==0) 
				{
					if(wAdvice.isJobwork()) 
					{
						wAdvice.setItem_subtype("ITMT00009");
						wAdvice.setItem_subtypename("JOB WORK");
					}
					else
					{
						wAdvice.setItem_subtype("ITMT00003");
						wAdvice.setItem_subtypename("FINISHED PRODUCTS");
					}
				}
				else 
				{
				
					//double update_total_qty=wAdvice.getTotal_qty();
					wAdvice.setTotal_qty(wAdvice.getTotal_qty_copy());  
					wAdvice.setTotal_qty_copy(wAdvice.getTotal_qty_copy());
				}
			}
			
		if(wAdvice.getRef_type().compareToIgnoreCase("Stock Transfer")==0)
		{
			wAdvice.setBusi_partner("None");
			stk_Transfer_ChallanRepository.updateUnloadChallanRevertStatus(op.get().getReferance_id());
			stk_Transfer_ChallanRepository.updateUnloadChallanStatus(wAdvice.getReferance_id());
		}
		
		wAdvice.setUadvice_status("Open");  /* new 14-04-2022 */
		wAdvice.setModified_type("INSERTED");
		wAdvice.setInserted_by(op.get().getInserted_by());
		wAdvice.setInserted_on(op.get().getInserted_on());
		wAdvice.setUpdated_by(userRepository.getUserDetails(wAdvice.getUsername()).getName());
		wAdvice.setUpdated_on(ldt);
		wAdvice.setDeleted_by("NA");
		wAdvice.setDeleted_on(ldt);
		wAdvice.setUladate(wAdvice.getUla_date());
		wAdvice.setPur_return_status(op.get().getPur_return_status());
		wAdvice.setPurreturnid(op.get().getPurreturnid());
		if(op.isPresent())
		{
			wAdvice.setId(id);
		}
		vehicleMasterRepository.updateStatus(op.get().getVehicle_id(),false);//revert vehicle
		vehicleMasterRepository.updateVehicleWeighmentLocation(op.get().getVehicle_id(),"NA");
		vehicleMasterRepository.updateStatus(wAdvice.getVehicle_id(),true);//AFTER UNLOAD ADVICE THE VEHICLE IS NOT SHOWN IN UNLODING ADVICE VEHICLE LIST UNTILL 2ND WEIGHMENT COMPLETE
		vehicleMasterRepository.updateVehicleWeighmentLocation(wAdvice.getVehicle_id(),wAdvice.getWeight_bridge_location());
		//new vehicle records table starts
		Vehicle_weighment_load_unload vehicle=  wm_unload_advice_item_dtlsRepository.vehicle_weighment_load_unloadupdateFETCH(wAdvice.getUnadviceid());
		    
				wm_unload_advice_item_dtlsRepository.vehicle_weighment_load_unloadupdate(wAdvice.getUnadviceid());
				
				
				
				Vehicle_weighment_load_unload  vehicle_weighment_load_unload = new Vehicle_weighment_load_unload();
				
				vehicle_weighment_load_unload.setVehicle_no(wAdvice.getVehicle_no());
				vehicle_weighment_load_unload.setVehicle_id(wAdvice.getVehicle_id());
				vehicle_weighment_load_unload.setReference_id(wAdvice.getUnadviceid());
				vehicle_weighment_load_unload.setRef_name("Unload Advice");
				vehicle_weighment_load_unload.setCompany_id(wAdvice.getCompany_id());
				vehicle_weighment_load_unload.setFin_year(wAdvice.getFin_year());
				vehicle_weighment_load_unload.setUsername(wAdvice.getUsername());
				vehicle_weighment_load_unload.setModified_type("INSERTED");
				vehicle_weighment_load_unload.setInserted_by(userRepository.getUserDetails(wAdvice.getUsername()).getName());
				vehicle_weighment_load_unload.setInserted_on(ldt);
				vehicle_weighment_load_unload.setUpdated_by("NA");
				vehicle_weighment_load_unload.setUpdated_on(ldt);
				vehicle_weighment_load_unload.setDeleted_by("NA");
				vehicle_weighment_load_unload.setDeleted_on(ldt);
				vehicle_weighment_load_unload.setRef_name_type(wAdvice.getRef_type());
				vehicle_weighment_load_unload.setWe_req(wAdvice.isWe_req());
				
				vehicle_weighment_load_unload.setGatepass_status(vehicle.getGatepass_status());
				vehicle_weighment_load_unload.setGp_gi_id(vehicle.getGp_gi_id());
				vehicle_weighment_load_unload.setGp_go_auth_id(vehicle.getGp_go_auth_id());
				vehicle_weighment_load_unload.setGp_go_id(vehicle.getGp_go_id());
				vehicle_weighment_load_unload.setLoad_unload_status(vehicle.getLoad_unload_status());
				vehicle_weighment_load_unload.setWeighment_status(vehicle.getWeighment_status());
				vehicle_weighment_load_unload.setStock_grn_status(vehicle.getStock_grn_status());
				
				vehicle_weighment_load_unload.setWeighment_id(vehicle.getWeighment_id());
				vehicle_weighment_load_unload.setWeight_bridge_location(wAdvice.getWeight_bridge_location());
				
				wAdvice.setVehicle_weighment_load_unload(vehicle_weighment_load_unload);
	//ends
			
		if(wAdvice.isJobwork()) 
		{
			wAdvice.getWm_unload_advice_item_dtls().clear();
			
			wm_unload_advice_item_dtls_for_jobworkRepository.updateWm_unload_advice_item_dtls_for_jobwork(op.get().getUnadviceid(),"UPDATED");
			
			Set<Wm_unload_advice_item_dtls_for_jobwork> jobSet = new HashSet<Wm_unload_advice_item_dtls_for_jobwork>();
			jobSet.addAll(wAdvice.getWm_unload_advice_item_dtls_for_jobwork());
			for(Wm_unload_advice_item_dtls_for_jobwork jobDts : jobSet)
			{
				jobDts.setUnadviceid(wAdvice.getUnadviceid());	
				jobDts.setUnadviceno(wAdvice.getUnadviceno());
				
				if(Utility.isNullOrEmpty(jobDts.getJob_item())) {
					jobDts.setJob_item_name(item_masterRepository.itemNameById(jobDts.getJob_item()).getItem_name());
				}else{jobDts.setJob_item_name("0");};
				
				if(Utility.isNullOrEmpty(jobDts.getJob_packing())){
					jobDts.setJob_packing_name(item_masterRepository.itemNameById(jobDts.getJob_packing()).getItem_name());
				}else{jobDts.setJob_packing_name("0");}
				
				jobDts.setCompany_id(wAdvice.getCompany_id());
				jobDts.setFin_year(wAdvice.getFin_year());
				jobDts.setModified_type("INSERTED");
				jobDts.setInserted_by(wAdvice.getInserted_by());
				jobDts.setInserted_on(wAdvice.getInserted_on());
				jobDts.setUpdated_by(userRepository.getUserDetails(wAdvice.getUsername()).getName());
				jobDts.setUpdated_on(ldt);
				jobDts.setDeleted_by("NA");
				jobDts.setDeleted_on(ldt);
			}
			wAdvice.setWm_unload_advice_item_dtls_for_jobwork(jobSet);
		}
		else
		{
			wAdvice.getWm_unload_advice_item_dtls_for_jobwork().clear();
			
			//Dynamic
			wm_unload_advice_item_dtlsRepository.wm_unload_advice_item_dtlsUpdate(wAdvice.getUnadviceid());
			
			Set<Wm_unload_advice_item_dtls> itemSet = new HashSet<Wm_unload_advice_item_dtls>();
			itemSet.addAll(wAdvice.getWm_unload_advice_item_dtls());
			for(Wm_unload_advice_item_dtls itemDts : itemSet)
			{
				itemDts.setUnadviceid(wAdvice.getUnadviceid());
				itemDts.setUnadviceno(wAdvice.getUnadviceno());
				itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
				if(itemDts.getPacking().compareTo("")!=0 && itemDts.getPacking().compareTo("0")!=0) {
					itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
				}
				itemDts.setCompany_id(wAdvice.getCompany_id());
				itemDts.setFin_year(wAdvice.getFin_year());
				itemDts.setModified_type("INSERTED");
				itemDts.setInserted_by(wAdvice.getInserted_by());
				itemDts.setInserted_on(wAdvice.getInserted_on());
				itemDts.setUpdated_by(wAdvice.getUpdated_by());
				itemDts.setUpdated_on(wAdvice.getUpdated_on());
				itemDts.setDeleted_by("NA");
				itemDts.setDeleted_on(ldt);
				
				itemDts.setPur_orderid(wAdvice.getReferance_id());
			
			}
			wAdvice.setWm_unload_advice_item_dtls(itemSet);
		}
		
		
		//Static
		wm_unload_advice_party_wmRepository.wm_unload_advice_party_wmUpdate(wAdvice.getUnadviceid());
		
		Set<Wm_unload_advice_party_wm> ptySet = new HashSet<Wm_unload_advice_party_wm>();
		ptySet.add(wAdvice.getWm_unload_advice_party_wm());
		for(Wm_unload_advice_party_wm wParty_wm : ptySet)
		{
			wParty_wm.setUnadviceid(wAdvice.getUnadviceid());
			wParty_wm.setUnadviceno(wAdvice.getUnadviceno());
			wParty_wm.setCompany_id(wAdvice.getCompany_id());
			wParty_wm.setFin_year(wAdvice.getFin_year());
			wParty_wm.setModified_type("INSERTED");
			wParty_wm.setInserted_by(wAdvice.getInserted_by());
			wParty_wm.setInserted_on(wAdvice.getInserted_on());
			wParty_wm.setUpdated_by(wAdvice.getUpdated_by());
			wParty_wm.setUpdated_on(wAdvice.getUpdated_on());
			wParty_wm.setDeleted_by("NA");
			wParty_wm.setDeleted_on(ldt);
		}
		
		if(!ptySet.isEmpty())
		{
			wAdvice.setWm_unload_advice_party_wm(ptySet.iterator().next());
		}
		
		//Static
		wm_unload_advice_driver_dtlsRepository.wm_unload_advice_driver_dtlsUpdate(wAdvice.getUnadviceid());
		
		Set<Wm_unload_advice_driver_dtls> driverSet = new HashSet<Wm_unload_advice_driver_dtls>();
		driverSet.add(wAdvice.getWm_unload_advice_driver_dtls());
		for(Wm_unload_advice_driver_dtls driver_dtls : driverSet)
		{
			driver_dtls.setUnadviceid(wAdvice.getUnadviceid());
			driver_dtls.setUnadviceno(wAdvice.getUnadviceno());
			driver_dtls.setCompany_id(wAdvice.getCompany_id());
			driver_dtls.setFin_year(wAdvice.getFin_year());
			driver_dtls.setModified_type("INSERTED");
			driver_dtls.setInserted_by(wAdvice.getInserted_by());
			driver_dtls.setInserted_on(wAdvice.getInserted_on());
			driver_dtls.setUpdated_by(wAdvice.getUpdated_by());
			driver_dtls.setUpdated_on(wAdvice.getUpdated_on());
			driver_dtls.setDeleted_by("NA");
			driver_dtls.setDeleted_on(ldt);
		}
		
		if(!driverSet.isEmpty())
		{
			wAdvice.setWm_unload_advice_driver_dtls(driverSet.iterator().next());
		}
		
		//Dynamic
		wm_unload_advice_broker_dtlsRepository.wm_unload_advice_broker_dtlsUpdate(wAdvice.getUnadviceid());
		
		Set<Wm_unload_advice_broker_dtls> brokerSet = new HashSet<Wm_unload_advice_broker_dtls>();
		brokerSet.addAll(wAdvice.getWm_unload_advice_broker_dtls());
		for(Wm_unload_advice_broker_dtls broker_dtls : brokerSet)
		{
			broker_dtls.setUnadviceid(wAdvice.getUnadviceid());
			broker_dtls.setUnadviceno(wAdvice.getUnadviceno());
			broker_dtls.setCompany_id(wAdvice.getCompany_id());
			broker_dtls.setFin_year(wAdvice.getFin_year());
			broker_dtls.setModified_type("INSERTED");
			broker_dtls.setInserted_by(wAdvice.getInserted_by());
			broker_dtls.setInserted_on(wAdvice.getInserted_on());
			broker_dtls.setUpdated_by(wAdvice.getUpdated_by());
			broker_dtls.setUpdated_on(wAdvice.getUpdated_on());
			broker_dtls.setDeleted_by("NA");
			broker_dtls.setDeleted_on(ldt);
		}
		wAdvice.setWm_unload_advice_broker_dtls(brokerSet);
		
		//Static
		wm_unload_advice_terms_conRepository.wm_unload_advice_terms_conUpdate(wAdvice.getUnadviceid());
		
		Set<Wm_unload_advice_terms_con> termSet = new HashSet<Wm_unload_advice_terms_con>();
		termSet.add(wAdvice.getWm_unload_advice_terms_con());
		for(Wm_unload_advice_terms_con terms_con : termSet)
		{
			terms_con.setUnadviceid(wAdvice.getUnadviceid());
			terms_con.setUnadviceno(wAdvice.getUnadviceno());
			terms_con.setCompany_id(wAdvice.getCompany_id());
			terms_con.setFin_year(wAdvice.getFin_year());
			terms_con.setModified_type("INSERTED");
			terms_con.setInserted_by(wAdvice.getInserted_by());
			terms_con.setInserted_on(wAdvice.getInserted_on());
			terms_con.setUpdated_by(wAdvice.getUpdated_by());
			terms_con.setUpdated_on(wAdvice.getUpdated_on());
			terms_con.setDeleted_by("NA");
			terms_con.setDeleted_on(ldt);
		}
		
		if(!termSet.isEmpty())
		{
			wAdvice.setWm_unload_advice_terms_con(termSet.iterator().next());
		}
		
		//Static
		wm_unload_advice_trans_infoRepository.wm_unload_advice_trans_infoUpdate(wAdvice.getUnadviceid());
		
		Set<Wm_unload_advice_trans_info> infoSet = new HashSet<Wm_unload_advice_trans_info>();
		infoSet.add(wAdvice.getWm_unload_advice_trans_info());
		for(Wm_unload_advice_trans_info info : infoSet)
		{
			info.setUnadviceid(wAdvice.getUnadviceid());
			info.setUnadviceno(wAdvice.getUnadviceno());
			info.setCompany_id(wAdvice.getCompany_id());
			info.setFin_year(wAdvice.getFin_year());
			info.setModified_type("INSERTED");
			info.setInserted_by(wAdvice.getInserted_by());
			info.setInserted_on(wAdvice.getInserted_on());
			info.setUpdated_by(wAdvice.getUpdated_by());
			info.setUpdated_on(wAdvice.getUpdated_on());
			info.setDeleted_by("NA");
			info.setDeleted_on(ldt);
		}
		
		if(!infoSet.isEmpty())
		{
			wAdvice.setWm_unload_advice_trans_info(infoSet.iterator().next());
		}
		
		//Static
		wm_unload_advice_bp_dtlsRepository.wm_unload_advice_bp_dtlsUpdate(wAdvice.getUnadviceid());
		
		Set<Wm_unload_advice_bp_dtls> bpSet = new HashSet<Wm_unload_advice_bp_dtls>();
		bpSet.add(wAdvice.getWm_unload_advice_bp_dtls());
		for(Wm_unload_advice_bp_dtls bp_dtls : bpSet)
		{
			bp_dtls.setUnadviceid(wAdvice.getUnadviceid());
			bp_dtls.setUnadviceno(wAdvice.getUnadviceno());
			bp_dtls.setCompany_id(wAdvice.getCompany_id());
			bp_dtls.setFin_year(wAdvice.getFin_year());
			bp_dtls.setModified_type("INSERTED");
			bp_dtls.setInserted_by(wAdvice.getInserted_by());
			bp_dtls.setInserted_on(wAdvice.getInserted_on());
			bp_dtls.setUpdated_by(wAdvice.getUpdated_by());
			bp_dtls.setUpdated_on(wAdvice.getUpdated_on());
			bp_dtls.setDeleted_by("NA");
			bp_dtls.setDeleted_on(ldt);
		}
		
		if(!bpSet.isEmpty())
		{
			wAdvice.setWm_unload_advice_bp_dtls(bpSet.iterator().next());
		}
		
		//Dynamic
		wm_unload_advice_docRepository.wm_unload_advice_docUpdate(wAdvice.getUnadviceid());
		
		Set<Wm_unload_advice_doc> docSet = new HashSet<Wm_unload_advice_doc>();
		docSet.addAll(wAdvice.getWm_unload_advice_docs());
		for(Wm_unload_advice_doc advice_doc : docSet)
		{
			advice_doc.setUnadviceid(wAdvice.getUnadviceid());
			advice_doc.setUnadviceno(wAdvice.getUnadviceno());
			advice_doc.setCompany_id(wAdvice.getCompany_id());
			advice_doc.setFin_year(wAdvice.getFin_year());
			advice_doc.setModified_type("INSERTED");
			advice_doc.setInserted_by(wAdvice.getInserted_by());
			advice_doc.setInserted_on(wAdvice.getInserted_on());
			advice_doc.setUpdated_by(wAdvice.getUpdated_by());
			advice_doc.setUpdated_on(wAdvice.getUpdated_on());
			advice_doc.setDeleted_by("NA");
			advice_doc.setDeleted_on(ldt);
		
		}
		wAdvice.setWm_unload_advice_docs(docSet);
		
		//Dynamic
		wm_unload_advice_app_chgsRepository.wm_unload_advice_app_chgsUpdate(wAdvice.getUnadviceid());
		
		Set<Wm_unload_advice_app_chgs> chgsSet = new HashSet<Wm_unload_advice_app_chgs>();
		chgsSet.addAll(wAdvice.getWm_unload_advice_app_chgs());
		for(Wm_unload_advice_app_chgs wApp_chgs : chgsSet)
		{
			wApp_chgs.setUnadviceid(wAdvice.getUnadviceid());
			wApp_chgs.setUnadviceno(wAdvice.getUnadviceno());
			wApp_chgs.setCompany_id(wAdvice.getCompany_id());
			wApp_chgs.setFin_year(wAdvice.getFin_year());
			wApp_chgs.setModified_type("INSERTED");
			wApp_chgs.setInserted_by(wAdvice.getInserted_by());
			wApp_chgs.setInserted_on(wAdvice.getInserted_on());
			wApp_chgs.setUpdated_by(wAdvice.getUpdated_by());
			wApp_chgs.setUpdated_on(wAdvice.getUpdated_on());
			wApp_chgs.setDeleted_by("NA");
			wApp_chgs.setDeleted_on(ldt);
		
		}
		wAdvice.setWm_unload_advice_app_chgs(chgsSet);
		
		return wm_unload_adviceRepository.save(wAdvice);
	}
	
	public Wm_unload_advice findOne(long id)
	{
		Optional<Wm_unload_advice> op=this.wm_unload_adviceRepository.findById(id);
		return op.get();
	}
	
	public Wm_unload_adviceDTO getUnloadAdvice(String vcode){

		Wm_unload_advice modelList=wm_unload_adviceRepository.getUnloadAdvice(vcode);
		Type listType = new TypeToken<Wm_unload_adviceDTO>() {}.getType();
		Wm_unload_adviceDTO uAdvice= new ModelMapper().map(modelList,listType);
		
		return uAdvice;
	}
	
	public Wm_unload_adviceDTO getUnloadDetails(String unadviceid){

		Wm_unload_advice modelList=wm_unload_adviceRepository.getUnloadDetails(unadviceid);
		
		Type listType = new TypeToken<Wm_unload_adviceDTO>() {}.getType();
		Wm_unload_adviceDTO uAdvice= new ModelMapper().map(modelList,listType);
		
		
		return uAdvice;
	}
	
	public Map<String,Object> getUnloadDetailsFastApi(String unadviceid)
	{
		Map<String,Object> qclist = wm_unload_adviceRepository.getUnloadDetailsFastApi(unadviceid);
		
		return qclist;
	}
	
	
	public Wm_unload_adviceDTO getUnloadDetailsNew(String unadviceid){

		Wm_unload_advice modelList=wm_unload_adviceRepository.getUnloadDetails(unadviceid);
		
		Company_business_unit_master stkGrn = companyBusinessUnitMasterRepository.businessunitbyid(modelList.getBusiness_unit());
		
		modelList.setSupp_ref_doc(stkGrn.getWork_address());
		modelList.setAdvice_type(Integer.toString(stkGrn.getPin_code()));
		modelList.setSupp_ref_docno(stkGrn.getState_name());
		modelList.setUla_date(stkGrn.getCity_name());
		
		
		
		Type listType = new TypeToken<Wm_unload_adviceDTO>() {}.getType();
		Wm_unload_adviceDTO uAdvice= new ModelMapper().map(modelList,listType);
		
		
		
		
		return uAdvice;
	}
	
	public Wm_unload_adviceDTO getUnloadadvanceList(String unadviceid){

		Wm_unload_advice modelList=wm_unload_adviceRepository.getUnloadadvanceList(unadviceid);
		Type listType = new TypeToken<Wm_unload_adviceDTO>() {}.getType();
		Wm_unload_adviceDTO uAdvice= new ModelMapper().map(modelList,listType);
		
		return uAdvice;
	}

	public Wm_unload_adviceDTO getUnload_multi_popup(String unadviceid){

		Wm_unload_advice modelList=wm_unload_adviceRepository.getUnloadDetails(unadviceid);
		Type listType = new TypeToken<Wm_unload_adviceDTO>() {}.getType();
		Wm_unload_adviceDTO uAdvice= new ModelMapper().map(modelList,listType);
		
		return uAdvice;
	}
	
	public Wm_unload_adviceDTO getUnloadAdvThruVehi(String vehicleid){

		Wm_unload_advice unloadDtls=wm_unload_adviceRepository.getUnloadAdvIdThruVehi(vehicleid);
		Type listType = new TypeToken<Wm_unload_adviceDTO>() {}.getType();
		Wm_unload_adviceDTO uAdviceDlts= new ModelMapper().map(unloadDtls,listType);
		
		return uAdviceDlts;
	}
	
	public List<Wm_unload_adviceDTO> getUnloadAdviceThruVehicle(String vcode,String weighment)
	{
		System.out.println(weighment+" :: "+vcode);
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadAdviceThruVehicle(vcode,weighment);

		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();

		List<Wm_unload_adviceDTO> dyna= new ModelMapper().map(modelList,listType);
		
		return dyna;
	}
	
	public List<Map<String, Object>> getUnloadAdviceThruVehiclefast(String vcode,String weighment)
	{
		return wm_unload_adviceRepository.getUnloadAdviceThruVehiclefast(vcode,weighment);
	}
	
	
    public List<Wm_unload_adviceDTO> getUnloadCodeList(String bpartner) {
		
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadCodeList(bpartner);
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadAdviceList = new ModelMapper().map(modelList,listType);
		
		return unloadAdviceList;
	}
    
    public List<Wm_unload_adviceDTO> getUnloadCodeList() {
		
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadCodeList();
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadAdvice = new ModelMapper().map(modelList,listType);
		
		return unloadAdvice;
	}
    
    public List<Wm_unload_adviceDTO> getUnloadVehiThruPurchase() {
		
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadVehiThruPurchase();
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadAdvice = new ModelMapper().map(modelList,listType);
		
		return unloadAdvice;
	}
    
    public List<Wm_unload_adviceDTO> getUnloadVehiThruSR() {
		
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadVehiThruSR();
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadAdvice = new ModelMapper().map(modelList,listType);
		
		return unloadAdvice;
	}
    
    public List<Wm_unload_adviceDTO> getUnloadVehiThruStkTransfer() {
		
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadVehiThruStkTransfer();
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadAdvice = new ModelMapper().map(modelList,listType);
		
		return unloadAdvice;
	}
    
    public List<Wm_unload_adviceDTO> getUnloadAdvRefOpenAdv() {
		
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadAdvRefOpenAdv();
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadAdviceOpen = new ModelMapper().map(modelList,listType);
		
		return unloadAdviceOpen;
	}
    
    public List<Wm_unload_adviceDTO> getUnloadAdvRefOpenAdvWt2() {
		
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadAdvRefOpenAdvWt2();
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadAdviceOpen = new ModelMapper().map(modelList,listType);
		
		return unloadAdviceOpen;
	}
    
    public List<Map<String, Object>> getUnloadAdvRefOpenAdvWt2New()
	{
		List<Map<String, Object>> unloadOpenNewList = new ArrayList<Map<String, Object>>();
		 
		unloadOpenNewList.addAll(wm_unload_adviceRepository.getUnloadAdvRefOpenAdvWt2New());
		return unloadOpenNewList;
	}
    
    public List<Wm_unload_adviceDTO> getUnloadAdvRefOpenAdvQc() {
		
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadAdvRefOpenAdvQc(true);
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadAdviceOpen = new ModelMapper().map(modelList,listType);
		
		return unloadAdviceOpen;
	}
    
    public List<Wm_unload_adviceDTO> getUnloadAdvRefPO() {
		
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadAdvRefPO();
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadPO = new ModelMapper().map(modelList,listType);
		
		return unloadPO;
	}
    
    public List<Wm_unload_adviceDTO> getUnloadAdvRefPOwt2(String bunit,String supplier,String itype,String ptype,String psubtype,String orderdate) {
		
    	List<Wm_unload_advice> modelList =new ArrayList<Wm_unload_advice>();
		modelList.addAll(wm_unload_adviceRepository.getUnloadAdvRefPOwt2(bunit,supplier,itype,ptype,psubtype,orderdate));
		
		
		modelList.forEach((e->{
			//System.out.println(e.getWeighment_id() + " / " +wm_unload_wgmntRepository.getUnloadWeightmentWt(e.getWeighment_id()).getWgment_no());
			e.setWeighment_id(wm_unload_wgmntRepository.getUnloadWeightmentWt(e.getWeighment_id()).getWgment_no());
		
		
		}));
		
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadPO = new ModelMapper().map(modelList,listType);
		
		return unloadPO;
	}
    
    public List<Map<String, Object>> getUnloadAdvRefPOwt2FastAPI(String bunit,String supplier,String itype,String ptype,String psubtype,String orderdate){
		
		List<Map<String, Object>> grnlist = new ArrayList<Map<String, Object>>();
		if(ptype.compareToIgnoreCase("ITMT00001")==0)		
		{
			grnlist.addAll(wm_unload_adviceRepository.getUnloadAdvRefPOwt2FastRawQC(bunit,supplier,itype,ptype,psubtype,orderdate));
		}
		else
		{
			grnlist.addAll(wm_unload_adviceRepository.getUnloadAdvRefPOwt2Fast(bunit,supplier,itype,ptype,psubtype,orderdate));
		}
		return grnlist;
		}
    
    public List<Wm_unload_adviceDTO> getunloadstore(String bunit,String supplier,String itype,String ptype,String psubtype,String orderdate) {
		
    	System.out.println(bunit+" / " + supplier +" / " + itype + " / "+ ptype +" / " + psubtype + " / " + orderdate );
    	List<Wm_unload_advice> modelList =new ArrayList<Wm_unload_advice>();
 		modelList.addAll(wm_unload_adviceRepository.getunloadstore(bunit,supplier,itype,ptype,psubtype,orderdate));
 		
 		
 		modelList.forEach((e->{
 			
 			e.setWeighment_id(e.getUnadviceno());
 		
 		
 		}));
 		
 		
 		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
 		
 		List<Wm_unload_adviceDTO> unloadPO = new ModelMapper().map(modelList,listType);
 		
 		return unloadPO;
 	}
    
 public List<Wm_unload_adviceDTO> getUnloadAdvRefPOwt2Argnew(String bunit,String supplier,String itype,String ptype,String psubtype,String orderdate) {
		
    	System.out.println(bunit + " / " + supplier + " / " + itype + " / " + ptype + " / " + psubtype +" / " + orderdate);
 		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadAdvRefPOwt2Argnew(bunit,supplier,itype,ptype,psubtype,orderdate);
 		
 		modelList.forEach((e->{
			
			e.setWeighment_id(wm_unload_wgmntRepository.getUnloadWeightmentWt(e.getWeighment_id()).getWgment_no());
		
		
		}));
		
 	
 		
 		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
 		
 		List<Wm_unload_adviceDTO> unloadPO = new ModelMapper().map(modelList,listType);
 		
 		return unloadPO;
 	}
    
    public List<Wm_unload_adviceDTO> getUnloadAdvRefPOQc() {
		
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadAdvRefPOQc(true);
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadPO = new ModelMapper().map(modelList,listType);
		
		return unloadPO;
	}
    
    public List<Map<String,Object>> getUnloadAdvRefPOQcNew(){
    	
		List<Map<String,Object>> qclist = new ArrayList<Map<String,Object>>();
		
		qclist.addAll(wm_unload_adviceRepository.getUnloadAdvRefPOQcNew(true));
		
		return qclist;
    }
    
    public List<Wm_unload_adviceDTO> getUnloadAdvVehiThruBU(String bunit,String orderdate) {
		
		List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadAdvVehiThruBU(bunit,orderdate);
		
		Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
		
		List<Wm_unload_adviceDTO> unloadVehi = new ModelMapper().map(modelList,listType);
		
		return unloadVehi;
	}
    
    
	public List<Wm_unload_advice_item_dtlsDTO> getUnloadItemList(String code)
	{
		List<Wm_unload_advice_item_dtls> modelList=wm_unload_advice_item_dtlsRepository.getUnloadItemList(code);
		Type listType=new TypeToken<List<Wm_unload_advice_item_dtlsDTO>>() {}.getType();
		List<Wm_unload_advice_item_dtlsDTO> uAdviceItemList=new ModelMapper().map(modelList,listType);
		for(int i=0;i<uAdviceItemList.size();i++) {
			if(uAdviceItemList.get(i).getWearhouse().compareTo("0") != 0 && uAdviceItemList.get(i).getWearhouse().compareTo("") != 0 && uAdviceItemList.get(i).getWearhouse() != null) {
				uAdviceItemList.get(i).setWearhouse_name(warehouseMasterRepository.getWHNListbyCode(uAdviceItemList.get(i).getWearhouse()).getWarehouse_name());
			}
			if(uAdviceItemList.get(i).getRack().compareTo("0") != 0 && uAdviceItemList.get(i).getRack().compareTo("") != 0 && uAdviceItemList.get(i).getRack() != null) {
				uAdviceItemList.get(i).setRack_name(binMasterRepository.getBinDesc(uAdviceItemList.get(i).getRack()).getBin_description());
			}
		}	
		return uAdviceItemList;
	}
	
	
	public List<Wm_unload_advice_item_dtlsDTO> getUnloadItemListrevise(String unadvice_id)
	{
		System.out.println("unadviceid"+unadvice_id);
		List<Wm_unload_advice_item_dtls> newitemdetailsrestructure= new ArrayList<Wm_unload_advice_item_dtls>();
		//newitemdetailsrestructure.addAll(wm_unload_advice_item_dtlsRepository.getmultiplegrnunloading(unadvice_id));
		newitemdetailsrestructure.addAll(wm_unload_advice_item_dtlsRepository.getmultiplegrnunloadingmultipleitem(unadvice_id));
		Type listType=new TypeToken<List<Wm_unload_advice_item_dtlsDTO>>() {}.getType();
		List<Wm_unload_advice_item_dtlsDTO> uAdviceItemList=new ModelMapper().map(newitemdetailsrestructure,listType);
		
		
		
		
		
		for(int i=0;i<uAdviceItemList.size();i++) {
			if(uAdviceItemList.get(i).getWearhouse().compareTo("0") != 0 && uAdviceItemList.get(i).getWearhouse().compareTo("") != 0 && uAdviceItemList.get(i).getWearhouse() != null) {
				uAdviceItemList.get(i).setWearhouse_name(warehouseMasterRepository.getWHNListbyCode(uAdviceItemList.get(i).getWearhouse()).getWarehouse_name());
			}
			if(uAdviceItemList.get(i).getRack().compareTo("0") != 0 && uAdviceItemList.get(i).getRack().compareTo("") != 0 && uAdviceItemList.get(i).getRack() != null) {
				uAdviceItemList.get(i).setRack_name(binMasterRepository.getBinDesc(uAdviceItemList.get(i).getRack()).getBin_description());
			}
		}	
		return uAdviceItemList;
	}
	
	public List<Wm_unload_advice_item_dtlsDTO> getUnloadItemDtlsThruVehi(String vehicleid)
	{
		Wm_unload_advice unloadId=wm_unload_adviceRepository.getUnloadAdvIdThruVehi(vehicleid);
		List<Wm_unload_advice_item_dtls> modelList=wm_unload_advice_item_dtlsRepository.getUnloadAdvItemList(unloadId.getUnadviceid());
		
		Type listType=new TypeToken<List<Wm_unload_advice_item_dtlsDTO>>() {}.getType();
		List<Wm_unload_advice_item_dtlsDTO> uAdviceItemList=new ModelMapper().map(modelList,listType);
			
		return uAdviceItemList;
	}
	
	public List<Wm_unload_advice_item_dtlsDTO> getUnloadAdvPOItemDtls(String adviceid,String porderid)
	{
		List<Wm_unload_advice_item_dtls> modelList=new ArrayList<Wm_unload_advice_item_dtls>();
		
		List<Wm_unload_advice_item_dtls> unadv=wm_unload_advice_item_dtlsRepository.getUnloadAdvItemList(adviceid);
		List<Pur_Order_Item_Details> ord=pur_Order_Item_DetailsRepository.getPurOrdItemList(porderid);
		
		ArrayList<String> unadvitem = new ArrayList<String>();
		ArrayList<String> orditem = new ArrayList<String>();
		
		for(int i=0;i<unadv.size();i++)
		{
			unadvitem.add(unadv.get(i).getItem_code());
		}
		
		for(int j=0;j<ord.size();j++)
		{
			orditem.add(ord.get(j).getItem_code());
		}
		
		 // Find the common elements 
		unadvitem.retainAll(orditem);
		
		System.err.println("Common elements: "+ unadvitem +" Size: "+unadvitem.size());
		
		for(int k=0;k<unadvitem.size();k++)
		{
			System.err.println(k+"th item "+unadvitem.get(k));
			modelList.add(wm_unload_advice_item_dtlsRepository.getUnloadAdvPOItemDtls(adviceid,unadvitem.get(k).trim()));
		}
		
		Type listType=new TypeToken<List<Wm_unload_advice_item_dtlsDTO>>() {}.getType();
		List<Wm_unload_advice_item_dtlsDTO> uAdviceItemList=new ModelMapper().map(modelList,listType);
		for(int i=0;i<uAdviceItemList.size();i++) {
			System.err.println("Got val >>> "+uAdviceItemList.get(i).getUnadviceid());
			if(uAdviceItemList.get(i).getWearhouse().compareTo("0") != 0 && uAdviceItemList.get(i).getWearhouse().compareTo("") != 0 && uAdviceItemList.get(i).getWearhouse() != null) {
				uAdviceItemList.get(i).setWearhouse_name(warehouseMasterRepository.getWHNListbyCode(uAdviceItemList.get(i).getWearhouse()).getWarehouse_name());
			}
			if(uAdviceItemList.get(i).getRack().compareTo("0") != 0 && uAdviceItemList.get(i).getRack().compareTo("") != 0 && uAdviceItemList.get(i).getRack() != null) {
				uAdviceItemList.get(i).setRack_name(binMasterRepository.getBinDesc(uAdviceItemList.get(i).getRack()).getBin_description());
			}
		}	
		return uAdviceItemList;
	}
	
	
	/*public List<Unload_Adv_Supp_DateDTO> getUnloadAdvSuppDate(String vno)
	{
		List<Wm_unload_advice> modelList=new ArrayList<Wm_unload_advice>();
		modelList.addAll(wm_unload_adviceRepository.getUnloadAdvSuppDate(vno));
		
		List<Wm_unload_advice> modelList1=new ArrayList<Wm_unload_advice    >();
		
		for(int jj=0;jj<modelList.size();jj++)
		{
			modelList1.add(wm_unload_adviceRepository.getUnloadAdvSuppDate1(modelList.get(jj)));
		}

	}*/
	
	 public List<Wm_unload_advice_item_dtlsDTO> wmUnAdviceItemRetriveList(String code)
		{
			List<Wm_unload_advice_item_dtls> modelList=wm_unload_advice_item_dtlsRepository.wmUnAdviceItemRetriveList(code);
			
			Type listType=new TypeToken<List<Wm_unload_advice_item_dtlsDTO>>() {}.getType();
			
			List<Wm_unload_advice_item_dtlsDTO> unlaodAdv=new ModelMapper().map(modelList,listType);
			
			return unlaodAdv;
		}
	 
	 @Autowired
	 Wm_unload_advice_party_wmRepository wm_unload_advice_party_wmRepository;
	 
	 public Wm_unload_advice_party_wmDTO wmUnAdvicePartyWmRetriveList(String code)
	 {
		 Wm_unload_advice_party_wm modelList=wm_unload_advice_party_wmRepository.wmUnAdvicePartyWmRetriveList(code);
		 Type listType = new TypeToken<Wm_unload_advice_party_wmDTO>() {}.getType();

		 Wm_unload_advice_party_wmDTO wmPartyWm= new ModelMapper().map(modelList,listType);
			
		return wmPartyWm;
	 }
	 
	 public Wm_unload_advice_party_wmDTO wmUnAdvicePartyWmRetriveListmultipopup(String code)
	 {
		 Wm_unload_advice_party_wm modelList=wm_unload_advice_party_wmRepository.wmUnAdvicePartyWmRetriveListmultipopup(code);
		 Type listType = new TypeToken<Wm_unload_advice_party_wmDTO>() {}.getType();

		 Wm_unload_advice_party_wmDTO wmPartyWm= new ModelMapper().map(modelList,listType);
			
		return wmPartyWm;
	 }
	 
	 public Wm_unload_advice_party_wmDTO getUnloadAdvPartyWmThruVehi(String vehicleid)
	 {
		 Wm_unload_advice unloadId=wm_unload_adviceRepository.getUnloadAdvIdThruVehi(vehicleid);
		 Wm_unload_advice_party_wm modelList=wm_unload_advice_party_wmRepository.wmUnAdvicePartyWmRetriveList(unloadId.getUnadviceid());
		 Type listType = new TypeToken<Wm_unload_advice_party_wmDTO>() {}.getType();

		 Wm_unload_advice_party_wmDTO wmPartyWmDtls= new ModelMapper().map(modelList,listType);
			
		 return wmPartyWmDtls;
	 }
	 
	 @Autowired
	 Wm_unload_advice_driver_dtlsRepository wm_unload_advice_driver_dtlsRepository;
	 
	 public Wm_unload_advice_driver_dtlsDTO wmUnAdviceDriverDtlsRetriveList(String code)
	 {
		 Wm_unload_advice_driver_dtls modelList=wm_unload_advice_driver_dtlsRepository.wmUnAdviceDriverDtlsRetriveList(code);
		 Type listType = new TypeToken<Wm_unload_advice_driver_dtlsDTO>() {}.getType();

		 Wm_unload_advice_driver_dtlsDTO wmDriver= new ModelMapper().map(modelList,listType);
			
		return wmDriver;
	 }
	 
	 public List<Wm_unload_advice_broker_dtlsDTO> wmUnAdviceBrokerRetriveList(String code)
		{
		
			List<Wm_unload_advice_broker_dtls> modelList=wm_unload_advice_broker_dtlsRepository.wmUnAdviceBrokerRetriveList(code);
			
			modelList.forEach((e)->{
				//ledgermasterRepository
				//e.setVen_name();
				//e.setBrokerage_acc(ledgermasterRepository.getLedgerDetails(e.getBrokerage_acc()).getLedgername());
				//e.setTds_acc(ledgermasterRepository.getLedgerDetails(e.getTds_acc()).getLedgername());
			});
			
			
			Type listType=new TypeToken<List<Wm_unload_advice_broker_dtlsDTO>>() {}.getType();
			
			List<Wm_unload_advice_broker_dtlsDTO> unlaodAdvBroker=new ModelMapper().map(modelList,listType);
			
			return unlaodAdvBroker;
		}
	 
	 public Wm_unload_advice_terms_conDTO wmUnAdviceTransConRetriveList(String code)
	 {
		 Wm_unload_advice_terms_con modelList=wm_unload_advice_terms_conRepository.wmUnAdviceTransConRetriveList(code);
		 Type listType = new TypeToken<Wm_unload_advice_terms_conDTO>() {}.getType();

		 Wm_unload_advice_terms_conDTO wmTransCon= new ModelMapper().map(modelList,listType);
			
		return wmTransCon;
	 }
	 
	 public Wm_unload_advice_trans_infoDTO wmUnAdviceTransInfoRetriveList(String code)
	 {
		 Wm_unload_advice_trans_info modelList=wm_unload_advice_trans_infoRepository.wmUnAdviceTransInfoRetriveList(code);
		 Type listType = new TypeToken<Wm_unload_advice_trans_infoDTO>() {}.getType();

		 Wm_unload_advice_trans_infoDTO wmTransInfo= new ModelMapper().map(modelList,listType);
			
		 return wmTransInfo;
	 }
	 
	 public Wm_unload_advice_trans_infoDTO getUnloadAdvTransInfoThruVehi(String vehicleid)
	 {
		 Wm_unload_advice unloadId=wm_unload_adviceRepository.getUnloadAdvIdThruVehi(vehicleid);
		 Wm_unload_advice_trans_info modelList=wm_unload_advice_trans_infoRepository.wmUnAdviceTransInfoRetriveList(unloadId.getUnadviceid());
		 Type listType = new TypeToken<Wm_unload_advice_trans_infoDTO>() {}.getType();

		 Wm_unload_advice_trans_infoDTO wmTransInfoDtls= new ModelMapper().map(modelList,listType);
			
		 return wmTransInfoDtls;
	 }
	 
	 public Wm_unload_advice_bp_dtlsDTO wmUnAdviceBpDtlsRetriveList(String code)
	 {
		 Wm_unload_advice_bp_dtls modelList=wm_unload_advice_bp_dtlsRepository.wmUnAdviceBpDtlsRetriveList(code);
		 Type listType = new TypeToken<Wm_unload_advice_bp_dtlsDTO>() {}.getType();

		 Wm_unload_advice_bp_dtlsDTO unlaodAdvBpDtls= new ModelMapper().map(modelList,listType);
			
		return unlaodAdvBpDtls;
	 }
	 
	 public List<Wm_unload_advice_docDTO> wmUnAdviceDocRetriveList(String code)
		{
			List<Wm_unload_advice_doc> modelList=wm_unload_advice_docRepository.wmUnAdviceDocRetriveList(code);
			
			Type listType=new TypeToken<List<Wm_unload_advice_docDTO>>() {}.getType();
			
			List<Wm_unload_advice_docDTO> unlaodAdvDoc=new ModelMapper().map(modelList,listType);
			
			return unlaodAdvDoc;
		}
	 
	 public List<Wm_unload_advice_app_chgsDTO> wmUnAdviceAppChgsRetriveList(String code)
		{
			List<Wm_unload_advice_app_chgs> modelList=wm_unload_advice_app_chgsRepository.wmUnAdviceAppChgsRetriveList(code);
			
			Type listType=new TypeToken<List<Wm_unload_advice_app_chgsDTO>>() {}.getType();
			
			List<Wm_unload_advice_app_chgsDTO> unlaodAdvApp=new ModelMapper().map(modelList,listType);
			
			return unlaodAdvApp;
		}
	 
	 
	 
	 
	 public List<VehicleMasterDTO> getVehiclenoall()
		{
			List<VehicleMaster> modelList=vehicleMasterRepository.findAll();
			
			List<VehicleMaster> allData = modelList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					 
					  .collect(Collectors.toList());
			
			Type listType=new TypeToken<List<VehicleMasterDTO>>() {}.getType();
			
			List<VehicleMasterDTO> vehicle=new ModelMapper().map(modelList,listType);
			return vehicle;
		}
	 
	 public List<Map<String, Object>> getVehiclenoallNew(){
			
			List<Map<String, Object>> vehicleAllNewList = new ArrayList<Map<String, Object>>();
			 
			vehicleAllNewList.addAll(vehicleMasterRepository.VehicleNoNew());
			return vehicleAllNewList;
			}
	 
	 
	 public Status_tableDTO checkweightunloadadvice(String pur_orderid,String item_code,Double mat_wt)
	 {
		 Status_table check=wm_unload_adviceRepository.checkweightunloadadvice(pur_orderid,item_code,mat_wt);
		 Type listType=new TypeToken<List<Status_tableDTO>>() {}.getType();
		 Status_tableDTO unlaodAdvBpDtls= new ModelMapper().map(check,listType);
			
			return unlaodAdvBpDtls;
	 }
	 
/*	 public Wm_unload_adviceDTO getUnload_advice_updation(String orderid,String unloadid){
			
		 System.out.println("orderid/"+orderid+"unloadid/"+unloadid);
			wm_unload_adviceRepository.getUnload_advice_updation(orderid);
			
			Wm_unload_advice modelList=wm_unload_adviceRepository.getUnloadId(unloadid);
				
			Type listType = new TypeToken<Wm_unload_adviceDTO>() {}.getType();
			Wm_unload_adviceDTO uAdvice= new ModelMapper().map(modelList,listType);
			
			return uAdvice;
		}*/
	 
	 
	 public List<Wm_unload_adviceDTO> getUnload_advice_updation(String orderid,String subtype) {
		 if(subtype.compareToIgnoreCase("Purchase Order")==0)
		 {
			 System.out.println("Purchase enter: "+orderid);
			 wm_unload_adviceRepository.getUnload_advice_updation(orderid); 
		 }
		
			List<Wm_unload_advice> modelList=wm_unload_adviceRepository.getUnloadAdvRefPOQc(true);
			
			Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
			
			List<Wm_unload_adviceDTO> unloadPO = new ModelMapper().map(modelList,listType);
			
			return unloadPO;
		}
	 
	 @Transactional
		public Wm_unload_advice deleteUnloadAdvice(Wm_unload_advice wm_unload_advice,Long id) 
	 	{
			Optional<Wm_unload_advice> op = wm_unload_adviceRepository.findById(id);
			
			LocalDateTime ldt = LocalDateTime.now();
			Wm_unload_advice unload=op.get();
			
			unload.setModified_type("DELETED");
			unload.setInserted_by(op.get().getInserted_by());
			unload.setInserted_on(op.get().getInserted_on());
			unload.setUpdated_by(op.get().getUpdated_by());
			unload.setUpdated_on(op.get().getUpdated_on());
			unload.setDeleted_by(userRepository.getUserDetails(unload.getUsername()).getName());
			unload.setDeleted_on(ldt);
			
			if(op.get().isJobwork())
			{
				wm_unload_advice_item_dtls_for_jobworkRepository.updateWm_unload_advice_item_dtls_for_jobwork(op.get().getUnadviceid(),"DELETED");
			}
			else
			{
				wm_unload_advice_item_dtlsRepository.wm_unload_advice_item_dtlsupdate(op.get().getUnadviceid());
			}
			
			wm_unload_advice_party_wmRepository.wm_unload_advice_party_wmupdate(op.get().getUnadviceid());
			
			wm_unload_advice_driver_dtlsRepository.wm_unload_advice_driver_dtlsupdate(op.get().getUnadviceid());
			
			wm_unload_advice_broker_dtlsRepository.wm_unload_advice_broker_dtlsupdate(op.get().getUnadviceid());
			
			wm_unload_advice_terms_conRepository.wm_unload_advice_terms_conupdate(op.get().getUnadviceid());
			
			wm_unload_advice_trans_infoRepository.wm_unload_advice_trans_infoupdate(op.get().getUnadviceid());
			
			wm_unload_advice_bp_dtlsRepository.wm_unload_advice_bp_dtlsupdate(op.get().getUnadviceid());
			
			wm_unload_advice_docRepository.wm_unload_advice_docupdate(op.get().getUnadviceid());
			
			wm_unload_advice_app_chgsRepository.wm_unload_advice_app_chgsupdate(op.get().getUnadviceid());
			
			wm_unload_advice_item_dtlsRepository.vehicle_weighment_load_unloadupdate(op.get().getUnadviceid());
			
			
			
			if(op.get().getAdvice_type().compareTo("Sales Return")==0) {
				//Update Unload Adv Status
				return_approval_noteRepository.updateUnloadStatusrevert(op.get().getReferance_id());
				
				
			}
			else if(op.get().getAdvice_type().compareTo("Stock Transfer")==0) {
				//Update Loading Adv Status
				
				stk_Transfer_ChallanRepository.updateUnloadChallanRevertStatus(op.get().getReferance_id());
			}
			else 
			{
				wm_unload_adviceRepository.getpurOrderUnloadRevert(op.get().getReferance_id());//po revert
			}
			//vehicle reveRt 
			wm_unload_advice_item_dtlsRepository.vehicle_weighment_load_unloadupdate(op.get().getUnadviceid());
			
			return wm_unload_adviceRepository.save(unload);	
	 	}
	 
	 public Wm_unload_advice getUnloadAdviceData(String unadviceid){

		Wm_unload_advice modelList=wm_unload_adviceRepository.getUnloadAdviceData(unadviceid);
		modelList.setBusi_partner(stk_Transfer_ChallanRepository.getStkOrderDetails(modelList.getReferance_id()).getBusiness_unit());
		
		return modelList;
	}
	 
	 public Wm_unload_advice retrivePurchaseGRNUnloadAdvicePopup(Long id)
		{
			Optional<Pur_good_receipt> PGR=pur_good_receiptRepository.findById(id);
			
			Wm_unload_advice pono = wm_unload_adviceRepository.getUnloadAdviceData(PGR.get().getReferance_id());
				
			 pono.setAdv_po_tag_no(PGR.get().getGrn_id());
				
			return pono;
		}
	 
	 
	 public List<Wm_unload_advice> retrivePurchaseGRNMultipleUnloadAdvicePopup(Long id)
		{
			Optional<Pur_good_receipt> PGR=pur_good_receiptRepository.findById(id);
			
			String multi[]=PGR.get().getReferance_id().split(",");
			
			List<Wm_unload_advice> unAdvList=new ArrayList<Wm_unload_advice>();
			
			for(int p=0;p<multi.length;p++)
			{
				unAdvList.add(wm_unload_adviceRepository.getUnloadAdviceData(multi[p]));
				
				
			}
			
			unAdvList.forEach((e->{
				e.setPur_orderid(pur_OrderRepository.getPurOrdDetails(e.getReferance_id()).getPur_order_no());
				e.setWeighment_id(wm_unload_wgmntRepository.getUnloadWeightmentWt(e.getWeighment_id()).getWgment_no());
			
			
			}));
			
			return unAdvList;
		}
	 
	 
	 public List<Wm_unload_advice_item_dtls> retriveGRNItemFormultiple(Long id)
		{
			Optional<Pur_good_receipt> PGR=pur_good_receiptRepository.findById(id);
			
			List<Pur_good_receipt_item_details> grnitemdtls=new ArrayList<Pur_good_receipt_item_details>();
			
			List<Wm_unload_advice_item_dtls> itemdtls=new ArrayList<Wm_unload_advice_item_dtls>();
			
			grnitemdtls.addAll(pur_good_receipt_item_detailsRepository.getPurGoodRcptItemDtlsList(PGR.get().getGrn_id()));
			
			for(int p=0;p<grnitemdtls.size();p++)
			{
				Wm_unload_advice_item_dtls unloadItem=new Wm_unload_advice_item_dtls();
				unloadItem.setItem_name(grnitemdtls.get(p).getAdv_item_name());
				unloadItem.setPacking_name(grnitemdtls.get(p).getAdv_packing_name());
				unloadItem.setQuantity(grnitemdtls.get(p).getAdv_item_qty());
				unloadItem.setUom(grnitemdtls.get(p).getAdv_item_uom());
				unloadItem.setS_qty((int)grnitemdtls.get(p).getAdv_pack_qty());
				unloadItem.setS_uom(grnitemdtls.get(p).getAdv_pack_uom());
				unloadItem.setMat_wt(grnitemdtls.get(p).getAdv_mat_wt());
				unloadItem.setWearhouse(grnitemdtls.get(p).getWarehouse_name());
				unloadItem.setRack(grnitemdtls.get(p).getRack());
				
				itemdtls.add(unloadItem);
			}
			
			return itemdtls;
		}
	 
	 
	 public List<Wm_unload_adviceDTO> getUnloaDataList(String currDate,String finyear)
		{
			List<Wm_unload_advice> modelList =new ArrayList<Wm_unload_advice>();
			String tablename="wm_unload_advice",party_name="busi_partner",order_no="unadviceno",order_date="ula_date";
			String orderno="",bus_partner1="";
			modelList.addAll(wm_unload_adviceRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,currDate,currDate,bus_partner1,finyear));
			
			
			Type listType = new TypeToken<List<Wm_unload_adviceDTO>>() {}.getType();
			List<Wm_unload_adviceDTO> advList = new ModelMapper().map(modelList,listType);
			
			advList.forEach((e)->{
					if(e.getRef_type().compareToIgnoreCase("Stock Transfer")==0)
					{
						e.setCustomer("None");
					}
					else
					{
						e.setCustomer(getBusinesspartnername(e.getAdvice_type(),e.getBusi_partner(),e.getCustomer()));
					}
					
				});
			 
			return advList;
		}
	 
	 public List<Map<String,Object>> getUnloaDataListfastapi(String currDate,String finyear)
	 {
			
		//SELECT w.unadviceno AS unadviceno,w.ula_date AS ula_date,CASE WHEN w.ref_type = 'Stock Transfer' THEN 'None' WHEN w.advice_type ='Purchase Order' THEN (SELECT b.bp_name FROM supp_bussiness_partner b WHERE b.bp_Id =w.busi_partner AND b.modified_type = 'INSERTED') WHEN w.advice_type ='Sales Return' THEN (SELECT s.cp_name FROM cust_bussiness_partner s WHERE s.modified_type = 'INSERTED' AND s.cp_Id =w.customer) ELSE '' END AS customer,w.business_unitname AS business_unitname,w.item_subtypename AS item_subtypename,w.vehicle_no AS vehicle_no,w.ref_type AS ref_type,w.grn_status AS grn_status,w.unadviceid AS unadviceid,w.id AS id,w.weighment_status AS weighment_status FROM wm_unload_advice  w WHERE w.ula_date ="2023-05-04" AND w.modified_type = "INSERTED" 
			return wm_unload_adviceRepository.getunloadlistfast(currDate);
	}
	 
	 public List<Map<String,Object>> getSearchUnloadAdvice(String orderno,String fromdate,String todate,String bus_partner1,String finyear)
	 {
			
		    String tablename="wm_unload_advice",party_name="busi_partner",order_no="unadviceno",order_date="ula_date";
			System.out.println("tablename: "+tablename+" party_name: "+party_name+" order_no "+order_no+" order_date "+order_date+" orderno "+orderno+" fromdate "+fromdate+" todate "+todate+" bus_partner1 "+bus_partner1+" finyear "+finyear);
			//serchunloadadvice.addAll(wm_unload_adviceRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,bus_partner1,finyear));
			
		 //SELECT w.unadviceno AS unadviceno,w.ula_date AS ula_date,CASE WHEN w.ref_type = 'Stock Transfer' THEN 'None' WHEN w.advice_type ='Purchase Order' THEN (SELECT b.bp_name FROM supp_bussiness_partner b WHERE b.bp_Id =w.busi_partner AND b.modified_type = 'INSERTED') WHEN w.advice_type ='Sales Return' THEN (SELECT s.cp_name FROM cust_bussiness_partner s WHERE s.modified_type = 'INSERTED' AND s.cp_Id =w.customer) ELSE '' END AS customer,w.business_unitname AS business_unitname,w.item_subtypename AS item_subtypename,w.vehicle_no AS vehicle_no,w.ref_type AS ref_type,w.grn_status AS grn_status,w.unadviceid AS unadviceid,w.id AS id,w.weighment_status AS weighment_status FROM wm_unload_advice  w WHERE w.ula_date ="2023-05-04" AND w.modified_type = "INSERTED" 
			return wm_unload_adviceRepository.getSearchUnloadAdvice(tablename,party_name,order_no,order_date,orderno,fromdate,todate,bus_partner1,finyear);
	}
	 
	 
	/* public List<Map<String,Object>> getUnloaDataListNew(String currDate,String finyear)
		{
			List<Map<String,Object>> unloadlist =new ArrayList<Map<String,Object>>();
			
			unloadlist.addAll(wm_unload_adviceRepository.getUnloadlist(currDate,finyear));
			 
			return unloadlist;
		}*/
	 
	 public List<DailygatewheatinwardreportDTO> getdailygatewheatinwardreportold(String date)
	 {
		 List<Object[]> unloaddeatils=new ArrayList<Object[]>();
		 unloaddeatils.addAll(wm_unload_adviceRepository.getdailygatewheatinwardreport(date));
		 
		 
		 List<DailygatewheatinwardreportDTO> list = new ArrayList<DailygatewheatinwardreportDTO>();        
		    for(final Object o : unloaddeatils)
		    {
		        Object[] obj = (Object[]) o;
		        if(obj[1].toString().compareToIgnoreCase("") == 0) 
		        {
		        
		        	
		        	list.add(new DailygatewheatinwardreportDTO(
		        			obj[0].toString(),
			        		obj[2].toString(),
			        		obj[3].toString(),
			        		obj[4].toString(),
			        		"WHEAT", 
			        		obj[7].toString(),
			        		obj[5].toString(),
			        		obj[6].toString(),
			        		obj[9].toString(),
			        		obj[8].toString(),
			        		obj[11].toString(),
			        		obj[10].toString(),
			        		obj[12].toString(),
			        		obj[13].toString(),
			        		obj[12].toString()
			        		
			        		));
		        }
		        else 
		        {
		        	list.add(new DailygatewheatinwardreportDTO(
		        			obj[0].toString(),
			        		obj[2].toString(),
			        		obj[3].toString(),
			        		obj[4].toString(),
			        		"WHEAT", 
			        		obj[7].toString(),
			        		obj[5].toString(),
			        		obj[6].toString(),
			        		obj[9].toString(),
			        		obj[8].toString(),
			        		obj[11].toString(),
			        		obj[10].toString(),
			        		obj[12].toString(),
			        		obj[13].toString(),
			        		obj[12].toString()
			        		
			        		));
		        }
		       
		    }
		    return list;
		
	 }
	 
	 /*public List<DailygatewheatinwardreportDTO> getdailygatewheatinwardreport(String date,String todate)
	 {
		 List<Wm_unload_advice> unloadlist = new ArrayList<Wm_unload_advice>();
		 unloadlist.addAll(wm_unload_adviceRepository.getdetailswithdate(date,todate));
		 System.out.println();
		 List<DailygatewheatinwardreportDTO> list = new ArrayList<DailygatewheatinwardreportDTO>();
		 for(int i=0;i<unloadlist.size();i++) 
		 {
			// Wm_unload_advice_party_wm partydetails=wm_unload_advice_party_wmRepository.wmUnAdvicePartyWmRetriveList(unloadlist.get(i).getUnadviceid());
			 
			 Wm_unload_wgmnt weigmentdetails=wm_unload_wgmntRepository.getreportwgnmet(unloadlist.get(i).getUnadviceid());
			 
			 List<Wm_unload_advice_item_dtls> unloaddetails=wm_unload_advice_item_dtlsRepository.getUnloadAdvItemListreport(unloadlist.get(i).getUnadviceid());
					 
			 
			 Wm_unload_advice_driver_dtls driver=wm_unload_advice_driver_dtlsRepository.wmUnAdviceDriverDtlsRetriveList(unloadlist.get(i).getUnadviceid());
			 Vehicle_weighment_load_unload vechile=wm_unload_adviceRepository.getvechiledetails(unloadlist.get(i).getUnadviceid());
			 
			 List<GatepassGetin> gatein=gatepassGetinRepository.getGatepassdetails(vechile.getGp_gi_id());
			 List<GatepassGateout> gateout= gatepassGateoutRepository.getgateoutdetails(vechile.getGp_go_id());
			// double bags= wm_unload_advice_item_dtlsRepository.gettotalpackingqty(unloadlist.get(i).getUnadviceid());
			 
			if(unloadlist.get(i).getItem_subtypename().compareToIgnoreCase("RAW MATERIALS") == 0) 
		    {
				 list.add(new DailygatewheatinwardreportDTO(
						 unloadlist.get(i).getUnadviceno(),
						 unloadlist.get(i).getSupp_name(),
						 unloadlist.get(i).getVehicle_no(),
						 unloadlist.get(i).getSupp_ref_docno(),
						 unloaddetails.get(0).getItem_name(), 
			        	 weigmentdetails.getTarebags(),
			        	 weigmentdetails.getWgmentno(),
						 gatein.get(0).getInserted_on().toLocalTime().toString(),
						 gateout.get(0).getInserted_on().toLocalTime().toString(),
						 String.valueOf(weigmentdetails.getGross_weight()),
						 String.valueOf(weigmentdetails.getTare_weight()),
						 String.valueOf(weigmentdetails.getNet_weight()),
						 driver_masterRepository.getDriverDetails(driver.getDriver_name()).getDriver_name(),
						 "0"
			        		));
			}
		 }
		 return list;
	 }*/
	 
	 public List<Map<String, Object>> getdailygatewheatinwardreportnew(String date,String todate)
	 {
		 return wm_unload_adviceRepository.wheat_inward_view_new(date,todate);
	 }
	 
	 public List<Map<String, Object>> getdailygatewheatinwardreportnew2(String date,String todate,String order)
	 {
		 List<Map<String, Object>> report=new ArrayList<Map<String, Object>>();
		 
		 if(order.compareToIgnoreCase("Purchase") == 0) 
         {
			 report = wm_unload_adviceRepository.wheat_inward_view_new(date,todate);
         }
		 else
		 {
			 report = wm_unload_adviceRepository.wheat_inward_view_new_sale(date,todate);
		 }
		 return report;
	 }
	 
	 public List<Map<String, Object>> getdailygatewheatinwardreportnew2WithParty(String date,String todate,String order,String party)
	 {
		 List<Map<String, Object>> report=new ArrayList<Map<String, Object>>();
		 
		 if(order.compareToIgnoreCase("Purchase") == 0) 
         {
			 report = wm_unload_adviceRepository.wheat_inward_view_new_withparty(date,todate,party);
         }
		 else
		 {
			 report = wm_unload_adviceRepository.wheat_inward_view_new_sale_withparty(date,todate,party);
		 }
		 return report;
	 }
	 
	 public List<DailygatewheatinwardreportDTO> getdailygatewheatinwardreportold2(String date,String todate)
	 {
		 
		 List<Object[]> unloaddeatils=new ArrayList<Object[]>();
		 unloaddeatils.addAll(wm_unload_adviceRepository.wheat_inward_view(date,todate));
		 
		 
		 List<DailygatewheatinwardreportDTO> list = new ArrayList<DailygatewheatinwardreportDTO>();        
		    for(final Object o : unloaddeatils)
		    {
		        Object[] obj = (Object[]) o;
		      
		      //  List<Wm_unload_advice_item_dtls> unloaddetails=wm_unload_advice_item_dtlsRepository.getUnloadAdvItemListreport(obj[0].toString());
		        	
		        
		        	list.add(new DailygatewheatinwardreportDTO(
			        		obj[0].toString(),
			        		obj[1].toString(),
			        		obj[2].toString(),
			        		obj[3].toString(),
			        		//"WHEAT", 
			        	//	unloaddetails.get(0).getItem_name(),//itemname
			        		obj[15].toString(),
			        		obj[7].toString(),
			        		obj[5].toString(),
			        		obj[6].toString(),
			        		obj[9].toString(),
			        		obj[8].toString(),
			        		obj[11].toString(),
			        		obj[10].toString(),
			        		obj[12].toString(),
			        		obj[13].toString(),
			        		obj[12].toString()
			        		
			        		
			        		));
		        
		       }
		
			return list;
	 }
	 
	public Wm_unload_advice getunloadfromreturnid(String salereturnid) 
	{
		
	 Wm_unload_advice unlaoddeatils=wm_unload_adviceRepository.getunloadfromreturnid(salereturnid);
				
				return unlaoddeatils;
	}
	 
	public List<Map<String, Object>> getUnloadAdviceReport(String fromdate,String todate,String suppliername,String finyear)
 	{
 		List<Map<String, Object>> finallist=new ArrayList<Map<String, Object>>();
 		
 		if(suppliername.compareToIgnoreCase("NONAME") == 0) 
 		{
 			finallist.addAll(wm_unload_adviceRepository.getUnloadAdvReportwithoutSupplier(fromdate,todate));
		}
 		else 
 		{
 			finallist.addAll(wm_unload_adviceRepository.getUnloadAdvReportwithSupplier(fromdate,todate,suppliername));
 		}
		
 		return finallist;
 		
 	}
	 
	public List<Map<String, Object>> getUnloadListThroughPurOrderId(String purorder)
 	{ 
	 	List<Map<String,Object>> unloadList=new ArrayList <>();
		String multiPurorderid[]=purorder.split(",");
		ArrayList<String> purorderidmultiple=new ArrayList<String>();
		for(int i=0;i<multiPurorderid.length;i++)
		{
			purorderidmultiple.add(multiPurorderid[i]);
		}
		unloadList.addAll(wm_unload_adviceRepository.getUnloadListThroughPurOrderId(purorderidmultiple));
		return unloadList;
 	}
	 
	public List<Map<String, Object>> getUnloadDetailsThroughUnloadId(String unloadid)
 	{ 
	  return wm_unload_adviceRepository.getUnloadDetailsThroughUnloadId(unloadid);
 	}
	 
	public List<Map<String, Object>> retriveUnloadAdviceJobwork(String unloadid)
 	{ 
	  return wm_unload_advice_item_dtls_for_jobworkRepository.retriveUnloadAdviceJobwork(unloadid);
 	}
 
	public List<Map<String, Object>> getSalesReturnNoteJobwork(String advdate,String bunit,String party)
 	{ 
	  return wm_unload_adviceRepository.getSalesReturnNoteJobwork(advdate,bunit,party);
 	}
	
	public List<Map<String, Object>> unloadadvicejobworkRetriveList(String unadviceid)
	{
		return wm_unload_adviceRepository.unloadadvicejobworkRetriveList(unadviceid);
	}
	
	public List<Map<String, Object>> wmUnAdviceBrokerRetriveFastList(String unadviceid)
	{
		return wm_unload_advice_broker_dtlsRepository.wmUnAdviceBrokerRetriveFastList(unadviceid);
	}
	
	public Map<String, Object> wmUnAdviceBpDtlsRetriveFastList(String unadviceid)
	{
		return wm_unload_advice_bp_dtlsRepository.wmUnAdviceBpDtlsRetriveFastList(unadviceid);
	}
	
	public Map<String, Object> wmUnAdviceDriverDtlsRetriveFastList(String unadviceid)
	{
		return wm_unload_advice_driver_dtlsRepository.wmUnAdviceDriverDtlsRetriveFastList(unadviceid);
	}
	
	public Map<String, Object> wmUnAdvicePartyWmRetriveFastList(String unadviceid)
	{
		return wm_unload_advice_party_wmRepository.wmUnAdvicePartyWmRetriveFastList(unadviceid);
	}
	
	
	  public List<Map<String, Object>> getUnloadItemFastList(String unadviceid) {
	  return wm_unload_advice_item_dtlsRepository.getUnloadItemFastList(unadviceid); 
	  }
	 
	
	public Map<String, Object> wmUnAdviceTransConRetriveFastList(String unadviceid)
	{
		return wm_unload_advice_terms_conRepository.wmUnAdviceTransConRetriveFastList(unadviceid);
	}
	
	public Map<String, Object> wmUnAdviceTransInfoRetriveFastList(String unadviceid)
	{
		return wm_unload_advice_trans_infoRepository.wmUnAdviceTransInfoRetriveFastList(unadviceid);
	}
	
	 public List<Map<String, Object>> wmUnAdviceAppChgsRetriveListFast(String unadviceid) {
		  return wm_unload_advice_app_chgsRepository.wmUnAdviceAppChgsRetriveListFast(unadviceid); 
		  }
	 
	 public List<Map<String, Object>> wmUnAdviceDocRetriveListFast(String unadviceid) {
		  return wm_unload_advice_docRepository.wmUnAdviceDocRetriveListFast(unadviceid); 
		  }
	 
	
	public List<Map<String,Object>> getUnloadAdvRefPOwt2ArgnewMultiItemGRN(String bunit,String supplier,String itype,String ptype,String psubtype,String orderdate)
	 {
		 List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		 
		 list.addAll(wm_unload_adviceRepository.getUnloadAdvRefPOwt2ArgnewMultiItemGRN(bunit,supplier,itype,ptype,psubtype,orderdate));
		 
		 return list;
	 }
	
	public List<Map<String,Object>> searchpendingUnAdviceReport(String fromdate,String todate)
	{
		return wm_unload_adviceRepository.searchpendingUnAdviceReport(fromdate,todate);
	}
}


