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
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_BusinessUnit_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Docs;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Shipment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Trans_Info;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Weight_Dtl;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_bu_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_docs;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_item_details;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_other_info;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_resource_cost;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_trans_info;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_driver_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_ChallanRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_Challan_BusinessUnit_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_Challan_DocsRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_Challan_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_Challan_Shipment_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_Challan_Trans_InfoRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_Challan_Weight_DtlRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_grnRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_sales_invRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_TransferRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_adviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Stk_Transfer_Challan_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_ChallanDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Weight_DtlDTO;

@Service
public class Stk_Transfer_ChallanService_Imp implements Stk_Transfer_ChallanService{
	
	@Autowired
	Stk_Transfer_ChallanRepository stockTransferChallanRepository;
	
	@Autowired
	Stk_Transfer_Challan_Trans_InfoRepository stk_Transfer_Challan_Trans_InfoRepository;
	
	@Autowired
	Stk_Transfer_Challan_Shipment_DtlsRepository stk_Transfer_Challan_Shipment_DtlsRepository;
	
	@Autowired
	Stk_Transfer_Challan_BusinessUnit_DtlsRepository stk_Transfer_Challan_BusinessUnit_DtlsRepository;
	
	@Autowired
	Stk_Transfer_Challan_Weight_DtlRepository stk_Transfer_Challan_Weight_DtlRepository;
	
	@Autowired
	Stk_Transfer_Challan_Item_DtlsRepository stk_Transfer_Challan_Item_DtlsRepository;
	
	@Autowired
	Stk_Transfer_Challan_Weight_DtlRepository stk_Transfer_Challan_Weight_DtlDTORepository;
	
	@Autowired
	Stk_Transfer_Challan_DocsRepository stk_Transfer_Challan_DocsRepository;
	
	@Autowired
	Wm_loading_adviceRepository wm_loading_adviceRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Stock_TransferRepository stock_TransferRepository;
	
	@Autowired
	Stk_transfer_sales_invRepository stk_transfer_sales_invRepository;
	
	@Autowired
	Stk_transfer_grnRepository stk_transfer_grnRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	
	public SequenceIdDTO getSTCSequenceId(String cdate,String bunit)
	{
		int slno=0;String prefix="STC";
		String sno=stockTransferChallanRepository.getSTCSequenceId(cdate,bunit);
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = cdate.split("-");
		prefix=prefix+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public SequenceIdDTO getSTGRNSequenceId12(String tdate,String bunit){
		int slno=0;String prefix="STG";
		String sno=stk_transfer_grnRepository.getSTGRNSequenceId(tdate,bunit);
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = tdate.split("-");
		prefix=prefix+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Stk_Transfer_Challan save(Stk_Transfer_Challan stockTransferChallan) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		boolean grnentry=false;
		
		long slno=0;
		if(stockTransferChallanRepository.countId() != null ) {
			slno=Long.parseLong(stockTransferChallanRepository.countId());
		}
		String prefix="STC";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		stockTransferChallan.setStk_challan_id(gen_sno);
		
		/*Start checking transaction no for unique */
		System.err.println("First:>>"+stockTransferChallan.getStk_challan_no());
		long nslno=0;String tprefix="STC";
		String tsno=stockTransferChallanRepository.getSTCSequenceId(stockTransferChallan.getStk_challan_date(),stockTransferChallan.getBusiness_unit());
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = stockTransferChallan.getStk_challan_date().split("-");
		tprefix=tprefix+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		stockTransferChallan.setStk_challan_no(gen_tslno);
		System.err.println("Last:>>>"+stockTransferChallan.getStk_challan_no());
		/*End checking transaction no for unique */
		
		stockTransferChallan.setModified_type("INSERTED");
		stockTransferChallan.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
		stockTransferChallan.setInserted_on(ldt);
		stockTransferChallan.setUpdated_by("NA");
		stockTransferChallan.setUpdated_on(ldt);
		stockTransferChallan.setDeleted_by("NA");
		stockTransferChallan.setDeleted_on(ldt);
		
		stockTransferChallan.setSaleinvoice_status("NO");
		stockTransferChallan.setChallan_status("NA");
		stockTransferChallan.setSales_inv_id("0");
		//if(stockTransferChallan.getOrder_point().compareToIgnoreCase("Intra") == 0)
		if(stockTransferChallan.getWeighment_required().compareToIgnoreCase("No") == 0 || stockTransferChallan.getRef_type().compareToIgnoreCase("Open Stock Transfer Challan") == 0)
		{
			grnentry=true;
		}
		
		long slno1=0;
		if(stk_transfer_grnRepository.countId() != null ) {
			slno1=Long.parseLong(stk_transfer_grnRepository.countId());
		}
		String prefix1="STG";
		String gen_sno1=UniqueID.uniqueid(prefix1,slno1);
		String strockgrnNumber=getSTGRNSequenceId12(stockTransferChallan.getStk_challan_date(),stockTransferChallan.getBusiness_unit()).getSequenceid();
		String vehichlenumber="";
		
		
		if(stockTransferChallan.getRef_type().compareToIgnoreCase("Open Stock Transfer Challan") == 0) 
		{
			if(Utility.isNullOrEmpty(stockTransferChallan.getVehicle_type())) 
			{
				
			}
			else 
			{
				stockTransferChallan.setVehicle_type("No Vehicle");
			}
		}
		
		if(stockTransferChallan.getRef_type().compareToIgnoreCase("Goods Stock Transfer") == 0) 
		{
			stock_TransferRepository.updateStockChallanStatus(stockTransferChallan.getReference_id());
		}
		
		if(stockTransferChallan.getRef_type().compareToIgnoreCase("Loading Advice") == 0) 
		{
			wm_loading_adviceRepository.updateStockChlnStatus(stockTransferChallan.getReference_id());
		}
		
		//Loading Advice Stock Status update
		
		
		Set<Stk_Transfer_Challan_Item_Dtls> stkTrnChDtl=new HashSet<Stk_Transfer_Challan_Item_Dtls>();
		stkTrnChDtl.addAll(stockTransferChallan.getStk_Transfer_Challan_Item_Dtls());
		for(Stk_Transfer_Challan_Item_Dtls stTrChDt:stkTrnChDtl) 
		{
			stTrChDt.setStk_challan_id(gen_sno);
			stTrChDt.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChDt.setItem_name(item_masterRepository.itemNameById(stTrChDt.getItem_code()).getItem_name());
			if(stTrChDt.getPacking().compareTo("")!=0 && stTrChDt.getPacking().compareTo("0")!=0) {
				stTrChDt.setPacking_name(item_masterRepository.itemNameById(stTrChDt.getPacking()).getItem_name());
			}
			stTrChDt.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChDt.setFin_year(stockTransferChallan.getFin_year());
			stTrChDt.setModified_type("INSERTED");
			stTrChDt.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChDt.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChDt.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChDt.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChDt.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChDt.setDeleted_on(stockTransferChallan.getDeleted_on());
			
			if(grnentry == true) 
			{
				Stk_transfer_grn_item_details grnitem = new Stk_transfer_grn_item_details();
				grnitem.setStk_grn_id(gen_sno1);
				grnitem.setStk_grn_no(strockgrnNumber);
				
				
				grnitem.setSlno(Integer.parseInt(stTrChDt.getSlno()));
				grnitem.setAdv_item_code(stTrChDt.getItem_code());
				grnitem.setAdv_item_name(item_masterRepository.itemNameById(stTrChDt.getItem_code()).getItem_name());
				grnitem.setAdv_item_qty(stTrChDt.getQuantity());
				grnitem.setAdv_item_uom(stTrChDt.getUom());
				grnitem.setAdv_mat_wt(stTrChDt.getMat_wt());
				grnitem.setAdv_packing(stTrChDt.getPacking());
				grnitem.setAdv_packing_name(item_masterRepository.itemNameById(stTrChDt.getPacking()).getItem_name());
				grnitem.setAdv_pack_qty(stTrChDt.getSquantity());
				grnitem.setAdv_pack_uom(stTrChDt.getSuom());
				grnitem.setRcv_item_uom(stTrChDt.getUom());
				grnitem.setRcv_item_qty(stTrChDt.getQuantity());
				grnitem.setRcv_mat_wt(stTrChDt.getMat_wt());
				grnitem.setRcv_pack_qty(stTrChDt.getSquantity());
				grnitem.setRcv_pack_uom(stTrChDt.getSuom());
				grnitem.setPssd_item_qty(stTrChDt.getQuantity());
				grnitem.setPssd_item_uom(stTrChDt.getUom());
				grnitem.setPssd_mat_wt(stTrChDt.getMat_wt());
				grnitem.setPssd_pack_qty(stTrChDt.getSquantity());
				grnitem.setPssd_pack_uom(stTrChDt.getSuom());
				
				grnitem.setPrice_based_on(stTrChDt.getPrice_based_on());
				grnitem.setUnit_rate(stTrChDt.getPrice());
				grnitem.setAmount(stTrChDt.getAmount());
				grnitem.setNet_amt(stTrChDt.getAmount());
				grnitem.setGross_amt(stTrChDt.getTotal_amt());
				grnitem.setNet_amt_after_qc(stTrChDt.getAmount());
				grnitem.setTax_code(stTrChDt.getTax_code());
				grnitem.setTax_rate(stTrChDt.getTax_rate());
				grnitem.setTax_amt(stTrChDt.getTax_amt());
				grnitem.setWarehouse("0");
				grnitem.setWarehouse_name("0");
				grnitem.setQc_deduction(0);
				grnitem.setQc_norms("0");
				grnitem.setStack_qty(0);
				grnitem.setRack("0");
				grnitem.setStack_uom("0");
				
				grnitem.setDiscount_based_on("");
				grnitem.setDiscount(0);
				grnitem.setDiscount_amt(0);
				
				
				grnitem.setCompany_id(stockTransferChallan.getCompany_id());
				grnitem.setFin_year(stockTransferChallan.getFin_year());
				grnitem.setModified_type("INSERTED");
				grnitem.setInserted_by(stockTransferChallan.getInserted_by());
				grnitem.setInserted_on(stockTransferChallan.getInserted_on());
				grnitem.setUpdated_by(stockTransferChallan.getUpdated_by());
				grnitem.setUpdated_on(stockTransferChallan.getUpdated_on());
				grnitem.setDeleted_by(stockTransferChallan.getDeleted_by());
				grnitem.setDeleted_on(stockTransferChallan.getDeleted_on());
				
				stTrChDt.setStk_transfer_grn_item_details(grnitem);
			}
			
			
			
		}
		
		
		
		stockTransferChallan.setStk_Transfer_Challan_Item_Dtls(stkTrnChDtl);
		
		Set<Stk_Transfer_Challan_BusinessUnit_Dtls> stTrChBsUDt=new HashSet<Stk_Transfer_Challan_BusinessUnit_Dtls>();
		stTrChBsUDt.addAll(stockTransferChallan.getStk_Transfer_Challan_BusinessUnit_Dtls());
		for(Stk_Transfer_Challan_BusinessUnit_Dtls stTrChBUDt:stTrChBsUDt) 
		{
			stTrChBUDt.setStk_challan_id(gen_sno);
			stTrChBUDt.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChBUDt.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChBUDt.setFin_year(stockTransferChallan.getFin_year());
			stTrChBUDt.setModified_type("INSERTED");
			stTrChBUDt.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChBUDt.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChBUDt.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChBUDt.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChBUDt.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChBUDt.setDeleted_on(stockTransferChallan.getDeleted_on());
			
			//stk_transfer_grn_bu_dtls starts here
			if(grnentry == true) 
			{
				Stk_transfer_grn_bu_dtls stk_transfer_grn_bu_dtls = new Stk_transfer_grn_bu_dtls();
				
				stk_transfer_grn_bu_dtls.setStk_grn_id(gen_sno1);
				stk_transfer_grn_bu_dtls.setStk_grn_no(strockgrnNumber);
				stk_transfer_grn_bu_dtls.setModified_type("INSERTED");
				stk_transfer_grn_bu_dtls.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				stk_transfer_grn_bu_dtls.setInserted_on(ldt);
				stk_transfer_grn_bu_dtls.setUpdated_by("NA");
				stk_transfer_grn_bu_dtls.setUpdated_on(ldt);
				stk_transfer_grn_bu_dtls.setDeleted_by("NA");
				stk_transfer_grn_bu_dtls.setDeleted_on(ldt);
				stk_transfer_grn_bu_dtls.setCompany_id(stockTransferChallan.getCompany_id());
				stk_transfer_grn_bu_dtls.setFin_year(stockTransferChallan.getFin_year());
				stk_transfer_grn_bu_dtls.setBusinessunit_name(stTrChBUDt.getBusiness_unitname());
				stk_transfer_grn_bu_dtls.setEmail_id("");
				stk_transfer_grn_bu_dtls.setWork_address("");
				
				stTrChBUDt.setStk_transfer_grn_bu_dtls(stk_transfer_grn_bu_dtls);
			}
			//grn bu ends here
		}
		stockTransferChallan.setStk_Transfer_Challan_BusinessUnit_Dtls(stTrChBsUDt);
		
		Set<Stk_Transfer_Challan_Shipment_Dtls> stkTrfrChShDtls=new HashSet<Stk_Transfer_Challan_Shipment_Dtls>();
		stkTrfrChShDtls.add(stockTransferChallan.getStk_Transfer_Challan_Shipment_Dtls());
		for(Stk_Transfer_Challan_Shipment_Dtls stTrChShDt:stkTrfrChShDtls) 
		{
			stTrChShDt.setStk_challan_id(gen_sno);
			stTrChShDt.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChShDt.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChShDt.setFin_year(stockTransferChallan.getFin_year());
			stTrChShDt.setModified_type("INSERTED");
			stTrChShDt.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChShDt.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChShDt.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChShDt.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChShDt.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChShDt.setDeleted_on(stockTransferChallan.getDeleted_on());
		}
		if(!stkTrfrChShDtls.isEmpty())
		{
			stockTransferChallan.setStk_Transfer_Challan_Shipment_Dtls(stkTrfrChShDtls.iterator().next());
		}
		
		Set<Stk_Transfer_Challan_Trans_Info> stkTrfrChTrInf=new HashSet<Stk_Transfer_Challan_Trans_Info>();
		stkTrfrChTrInf.add(stockTransferChallan.getStk_Transfer_Challan_Trans_Info());
		for(Stk_Transfer_Challan_Trans_Info stTrChtrIn:stkTrfrChTrInf) 
		{
			stTrChtrIn.setStk_challan_id(gen_sno);
			stTrChtrIn.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChtrIn.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChtrIn.setFin_year(stockTransferChallan.getFin_year());
			stTrChtrIn.setModified_type("INSERTED");
			stTrChtrIn.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChtrIn.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChtrIn.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChtrIn.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChtrIn.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChtrIn.setDeleted_on(stockTransferChallan.getDeleted_on());
			
			vehichlenumber=stTrChtrIn.getVehicle_no();
			
			if(grnentry == true) 
			{
				Stk_transfer_grn_trans_info grntrans = new Stk_transfer_grn_trans_info();
				grntrans.setTrans_borne_by(stTrChtrIn.getTrans_borne_by());
				grntrans.setTransporter_code(stTrChtrIn.getTrans_code());
				grntrans.setMode_of_trans(stTrChtrIn.getMode_of_trans());
				grntrans.setStk_grn_id(gen_sno1);
				grntrans.setStk_grn_no(strockgrnNumber);
				
				grntrans.setModified_type("INSERTED");
				grntrans.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				grntrans.setInserted_on(ldt);
				grntrans.setUpdated_by("NA");
				grntrans.setUpdated_on(ldt);
				grntrans.setDeleted_by("NA");
				grntrans.setDeleted_on(ldt);
				grntrans.setCompany_id(stockTransferChallan.getCompany_id());
				grntrans.setFin_year(stockTransferChallan.getFin_year());
				grntrans.setAcc_name("");
				grntrans.setAcc_no("0");
				grntrans.setBank_name("");
				grntrans.setBic_swift_code("");
				grntrans.setPayment_mode("");
				grntrans.setPayment_term("");
				grntrans.setBranch("");
				grntrans.setCash_limit(0.00);
				grntrans.setTransportation_rate("");
				
				stTrChtrIn.setStk_transfer_grn_trans_info(grntrans);
			}
			
			
		}
		if(!stkTrfrChTrInf.isEmpty())
		{
			stockTransferChallan.setStk_Transfer_Challan_Trans_Info(stkTrfrChTrInf.iterator().next());
		}
		
		Set<Stk_Transfer_Challan_Weight_Dtl> stkTrfrChWeDt=new HashSet<Stk_Transfer_Challan_Weight_Dtl>();
		stkTrfrChWeDt.add(stockTransferChallan.getStk_Transfer_Challan_Weight_Dtl());
		for(Stk_Transfer_Challan_Weight_Dtl stTrChWeDt:stkTrfrChWeDt) 
		{
			stTrChWeDt.setStk_challan_id(gen_sno);
			stTrChWeDt.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChWeDt.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChWeDt.setFin_year(stockTransferChallan.getFin_year());
			stTrChWeDt.setModified_type("INSERTED");
			stTrChWeDt.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChWeDt.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChWeDt.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChWeDt.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChWeDt.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChWeDt.setDeleted_on(stockTransferChallan.getDeleted_on());
		}
		if(!stkTrfrChWeDt.isEmpty())
		{
			stockTransferChallan.setStk_Transfer_Challan_Weight_Dtl(stkTrfrChWeDt.iterator().next());
		}
		
		Set<Stk_Transfer_Challan_Docs> stTrChDocs=new HashSet<Stk_Transfer_Challan_Docs>();
		stTrChDocs.addAll(stockTransferChallan.getStk_Transfer_Challan_Docs());
		for(Stk_Transfer_Challan_Docs stTrChDcs:stTrChDocs) 
		{
			stTrChDcs.setStk_challan_id(gen_sno);
			stTrChDcs.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChDcs.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChDcs.setFin_year(stockTransferChallan.getFin_year());
			stTrChDcs.setModified_type("INSERTED");
			stTrChDcs.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChDcs.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChDcs.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChDcs.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChDcs.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChDcs.setDeleted_on(stockTransferChallan.getDeleted_on());
			
			//Stk_transfer_grn_docs starts here
			if(grnentry == true) 
			{
				Stk_transfer_grn_docs stk_transfer_grn_docs = new Stk_transfer_grn_docs();
				
				stk_transfer_grn_docs.setStk_grn_id(gen_sno1);
				stk_transfer_grn_docs.setStk_grn_no(strockgrnNumber);
				stk_transfer_grn_docs.setModified_type("INSERTED");
				stk_transfer_grn_docs.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				stk_transfer_grn_docs.setInserted_on(ldt);
				stk_transfer_grn_docs.setUpdated_by("NA");
				stk_transfer_grn_docs.setUpdated_on(ldt);
				stk_transfer_grn_docs.setDeleted_by("NA");
				stk_transfer_grn_docs.setDeleted_on(ldt);
				stk_transfer_grn_docs.setCompany_id(stockTransferChallan.getCompany_id());
				stk_transfer_grn_docs.setFin_year(stockTransferChallan.getFin_year());
				stk_transfer_grn_docs.setDoc_name("");
				
				stTrChDcs.setStk_transfer_grn_docs(stk_transfer_grn_docs);
				
			}
			//grn docs ends here
		}
		stockTransferChallan.setStk_Transfer_Challan_Docs(stTrChDocs);
		if(grnentry == true) 
		{
				Stk_transfer_grn grn =new Stk_transfer_grn();
				grn.setStk_grn_id(gen_sno1);
				grn.setStk_grn_no(strockgrnNumber);//stk_grn_date
				grn.setB_unit(stockTransferChallan.getBusiness_unit());
				grn.setB_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(stockTransferChallan.getBusiness_unit()).getBusinessunit_name());
				grn.setApplicable_charges_id("0");
				grn.setModified_type("INSERTED");
				grn.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				grn.setInserted_on(ldt);
				grn.setUpdated_by("NA");
				grn.setUpdated_on(ldt);
				grn.setDeleted_by("NA");
				grn.setDeleted_on(ldt);
				grn.setCompany_id(stockTransferChallan.getCompany_id());
				grn.setFin_year(stockTransferChallan.getFin_year());
				grn.setItem_sub_type("0");
				grn.setItem_type("0");
				grn.setReference_id(gen_sno);
				grn.setReferance_type("0");
				grn.setStk_grn_date(stockTransferChallan.getStk_challan_date());
				//vehichlenumber
				grn.setVehicle_id(vehichlenumber);
				
				if(Utility.isNullOrEmpty(vehichlenumber)) 
				{
					if(vehichlenumber.compareToIgnoreCase("")==0 || vehichlenumber.compareToIgnoreCase("0")==0)
					{
						
					}
					else
					{
						grn.setVehicle_no(vehicleMasterRepository.getVehicleDetails(vehichlenumber).getVehicle_no());
					}
				}
				grn.setReceipt_criteria(stockTransferChallan.getPassing_wt());
				grn.setReference_status("Direct");
				grn.setRec_b_unit(stockTransferChallan.getDelivery_business_unit());
				stockTransferChallan.setStk_transfer_grn(grn);
				
				//Stk_transfer_grn_other_info starts here
				Stk_transfer_grn_other_info stk_transfer_grn_other_info=new Stk_transfer_grn_other_info();
				
				stk_transfer_grn_other_info.setStk_grn_id(gen_sno1);
				stk_transfer_grn_other_info.setStk_grn_no(strockgrnNumber);
				stk_transfer_grn_other_info.setModified_type("INSERTED");
				stk_transfer_grn_other_info.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				stk_transfer_grn_other_info.setInserted_on(ldt);
				stk_transfer_grn_other_info.setUpdated_by("NA");
				stk_transfer_grn_other_info.setUpdated_on(ldt);
				stk_transfer_grn_other_info.setDeleted_by("NA");
				stk_transfer_grn_other_info.setDeleted_on(ldt);
				stk_transfer_grn_other_info.setCompany_id(stockTransferChallan.getCompany_id());
				stk_transfer_grn_other_info.setFin_year(stockTransferChallan.getFin_year());
				
				stockTransferChallan.setStk_transfer_grn_other_info(stk_transfer_grn_other_info);
				
				//Stk_transfer_grn_other_info ends here
				
				//Stk_transfer_grn_resource_cost starts here
				Stk_transfer_grn_resource_cost stk_transfer_grn_resource_cost = new Stk_transfer_grn_resource_cost();
				stk_transfer_grn_resource_cost.setStk_grn_id(gen_sno1);
				stk_transfer_grn_resource_cost.setStk_grn_no(strockgrnNumber);
				stk_transfer_grn_resource_cost.setModified_type("INSERTED");
				stk_transfer_grn_resource_cost.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				stk_transfer_grn_resource_cost.setInserted_on(ldt);
				stk_transfer_grn_resource_cost.setUpdated_by("NA");
				stk_transfer_grn_resource_cost.setUpdated_on(ldt);
				stk_transfer_grn_resource_cost.setDeleted_by("NA");
				stk_transfer_grn_resource_cost.setDeleted_on(ldt);
				stk_transfer_grn_resource_cost.setCompany_id(stockTransferChallan.getCompany_id());
				stk_transfer_grn_resource_cost.setFin_year(stockTransferChallan.getFin_year());
				stk_transfer_grn_resource_cost.setCharge_name("");
				stk_transfer_grn_resource_cost.setGross_amt(0.000);
				stk_transfer_grn_resource_cost.setRate_cal_method("");
				stk_transfer_grn_resource_cost.setTax_amt(0.000);
				stk_transfer_grn_resource_cost.setTax_rate(0.000);
				
				stockTransferChallan.setStk_transfer_grn_resource_cost(stk_transfer_grn_resource_cost);
				//Stk_transfer_grn_resource_cost ends here
				
				
				//Vehicle Entry Starts
				
				if(stockTransferChallan.getRef_type().compareToIgnoreCase("Goods Stock Transfer")==0)
				{
					String weimentstatus=stock_TransferRepository.getStkOrder(stockTransferChallan.getReference_id()).getWeightment_req();
					if((weimentstatus.compareToIgnoreCase("No")==0 || weimentstatus.compareToIgnoreCase("0")==0) && stockTransferChallan.getOrder_point().compareToIgnoreCase("Inter")==0)
					{
						//new vehicle records table starts
						Vehicle_weighment_load_unload  vehicle_weighment_load_unload = new Vehicle_weighment_load_unload();
						
						vehicle_weighment_load_unload.setVehicle_no(vehicleMasterRepository.getVehicleDetails(vehichlenumber).getVehicle_no());
						vehicle_weighment_load_unload.setVehicle_id(vehichlenumber);
						vehicle_weighment_load_unload.setReference_id(gen_sno);
						vehicle_weighment_load_unload.setRef_name("Goods Stock Transfer");
						vehicle_weighment_load_unload.setModified_type("INSERTED");
						vehicle_weighment_load_unload.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
						vehicle_weighment_load_unload.setInserted_on(ldt);
						vehicle_weighment_load_unload.setUpdated_by("NA");
						vehicle_weighment_load_unload.setUpdated_on(ldt);
						vehicle_weighment_load_unload.setDeleted_by("NA");
						vehicle_weighment_load_unload.setDeleted_on(ldt);
						vehicle_weighment_load_unload.setCompany_id(stockTransferChallan.getCompany_id());
						vehicle_weighment_load_unload.setFin_year(stockTransferChallan.getFin_year());
						vehicle_weighment_load_unload.setRef_name_type("Goods Stock Transfer");
							
						stockTransferChallan.setVehicle_weighment_load_unload(vehicle_weighment_load_unload);
						
						//ends
					}
				}
				//Vehicle Entry Ends
		}
		
		return stockTransferChallanRepository.save(stockTransferChallan);
		
		}
	
	@Transactional
	public Stk_Transfer_Challan update(Stk_Transfer_Challan stockTransferChallan,Long id)
	{
		
		boolean grnentry=false;
		Optional<Stk_Transfer_Challan> op = stockTransferChallanRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		stockTransferChallan.setModified_type("INSERTED");
		stockTransferChallan.setInserted_by(op.get().getInserted_by());
		stockTransferChallan.setInserted_on(op.get().getInserted_on());
		stockTransferChallan.setUpdated_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
		stockTransferChallan.setUpdated_on(ldt);
		stockTransferChallan.setDeleted_by("NA");
		stockTransferChallan.setDeleted_on(ldt);
		
		//if(stockTransferChallan.getOrder_point().compareToIgnoreCase("Intra") == 0)
		if(stockTransferChallan.getWeighment_required().compareToIgnoreCase("No") == 0 || stockTransferChallan.getRef_type().compareToIgnoreCase("Open Stock Transfer Challan") == 0) 
		{
			grnentry=true;
		}
		
		long slno1=0;
		if(stk_transfer_grnRepository.countId() != null ) {
			slno1=Long.parseLong(stk_transfer_grnRepository.countId());
		}
		String prefix1="STG";
		String gen_sno1=UniqueID.uniqueid(prefix1,slno1);
		String strockgrnNumber=getSTGRNSequenceId12(stockTransferChallan.getStk_challan_date(),stockTransferChallan.getBusiness_unit()).getSequenceid();
		String vehichlenumber="";
		
		if(stockTransferChallan.getRef_type().compareToIgnoreCase("Open Stock Transfer Challan") == 0) 
		{
			if(Utility.isNullOrEmpty(stockTransferChallan.getVehicle_type())) 
			{
				
			}
			else 
			{
				stockTransferChallan.setVehicle_type("No Vehicle");
			}
		}
		
		if(stockTransferChallan.getRef_type().compareToIgnoreCase("Goods Stock Transfer") == 0) 
		{
			stock_TransferRepository.revertStockChallanStatus(op.get().getReference_id());
			stock_TransferRepository.updateStockChallanStatus(stockTransferChallan.getReference_id());
		}
		
		if(stockTransferChallan.getRef_type().compareToIgnoreCase("Loading Advice") == 0) 
		{
			wm_loading_adviceRepository.revertStockChlnStatus(op.get().getReference_id());
			wm_loading_adviceRepository.updateStockChlnStatus(stockTransferChallan.getReference_id());
		}
		
		if(op.isPresent())
		{
			stockTransferChallan.setId(id);
		}
		//String grnid=stk_transfer_grnRepository.getGrn(op.get().getReference_id()).getStk_grn_id();
		stk_Transfer_Challan_Item_DtlsRepository.updateStk_Transfer_Challan_Item_Dtls(op.get().getStk_challan_id());
		//stk_transfer_grnRepository.updateStkTransferGrnItemDetails(grnid);
		if(grnentry == true) 
		{
			System.out.println("check1:"+op.get().getStk_challan_id());
			System.out.println("check:"+stk_transfer_grnRepository.getGrn(op.get().getStk_challan_id()).getStk_grn_id());
			//String grnid=stk_transfer_grnRepository.getGrn(op.get().getReference_id()).getStk_grn_id();
			String grnid=stk_transfer_grnRepository.getGrn(op.get().getStk_challan_id()).getStk_grn_id();
			stk_transfer_grnRepository.updateStkTransferGrnItemDetails(grnid);
			stk_transfer_grnRepository.updateStkTransferGrnTransportDetails(grnid);
			stk_transfer_grnRepository.updateStkTransferGrn(grnid);
			
			stk_transfer_grnRepository.stkTransferGrnBuDtlsUpdate(grnid);
			stk_transfer_grnRepository.stkTransferGrnDocsUpdate(grnid);
			stk_transfer_grnRepository.stkTransferGrnOtherInfoUpdate(grnid);
			stk_transfer_grnRepository.stkTransferGrnResourceCostUpdate(grnid);
			
		}
		
		Set<Stk_Transfer_Challan_Item_Dtls> stkTrnChDtl=new HashSet<Stk_Transfer_Challan_Item_Dtls>();
		stkTrnChDtl.addAll(stockTransferChallan.getStk_Transfer_Challan_Item_Dtls());
		for(Stk_Transfer_Challan_Item_Dtls stTrChDt:stkTrnChDtl) 
		{
			stTrChDt.setStk_challan_id(op.get().getStk_challan_id());
			stTrChDt.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChDt.setItem_name(item_masterRepository.itemNameById(stTrChDt.getItem_code()).getItem_name());
			if(stTrChDt.getPacking().compareTo("")!=0 && stTrChDt.getPacking().compareTo("0")!=0) {
				stTrChDt.setPacking_name(item_masterRepository.itemNameById(stTrChDt.getPacking()).getItem_name());
			}
			stTrChDt.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChDt.setFin_year(stockTransferChallan.getFin_year());
			stTrChDt.setModified_type("INSERTED");
			stTrChDt.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChDt.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChDt.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChDt.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChDt.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChDt.setDeleted_on(stockTransferChallan.getDeleted_on());
			
			
			if(grnentry == true) 
			{
				Stk_transfer_grn_item_details grnitem = new Stk_transfer_grn_item_details();
				grnitem.setStk_grn_id(gen_sno1);
				grnitem.setStk_grn_no(strockgrnNumber);
				
				
				grnitem.setSlno(Integer.parseInt(stTrChDt.getSlno()));
				grnitem.setAdv_item_code(stTrChDt.getItem_code());
				grnitem.setAdv_item_name(item_masterRepository.itemNameById(stTrChDt.getItem_code()).getItem_name());
				grnitem.setAdv_item_qty(stTrChDt.getQuantity());
				grnitem.setAdv_item_uom(stTrChDt.getUom());
				grnitem.setAdv_mat_wt(stTrChDt.getMat_wt());
				grnitem.setAdv_packing(stTrChDt.getPacking());
				grnitem.setAdv_packing_name(item_masterRepository.itemNameById(stTrChDt.getPacking()).getItem_name());
				grnitem.setAdv_pack_qty(stTrChDt.getSquantity());
				grnitem.setAdv_pack_uom(stTrChDt.getSuom());
				grnitem.setRcv_item_uom(stTrChDt.getUom());
				grnitem.setRcv_item_qty(stTrChDt.getQuantity());
				grnitem.setRcv_mat_wt(stTrChDt.getMat_wt());
				grnitem.setRcv_pack_qty(stTrChDt.getSquantity());
				grnitem.setRcv_pack_uom(stTrChDt.getSuom());
				grnitem.setPssd_item_qty(stTrChDt.getQuantity());
				grnitem.setPssd_item_uom(stTrChDt.getUom());
				grnitem.setPssd_mat_wt(stTrChDt.getMat_wt());
				grnitem.setPssd_pack_qty(stTrChDt.getSquantity());
				grnitem.setPssd_pack_uom(stTrChDt.getSuom());
				
				grnitem.setPrice_based_on(stTrChDt.getPrice_based_on());
				grnitem.setUnit_rate(stTrChDt.getPrice());
				grnitem.setAmount(stTrChDt.getAmount());
				grnitem.setNet_amt(stTrChDt.getAmount());
				grnitem.setGross_amt(stTrChDt.getTotal_amt());
				grnitem.setNet_amt_after_qc(stTrChDt.getAmount());
				grnitem.setTax_code(stTrChDt.getTax_code());
				grnitem.setTax_rate(stTrChDt.getTax_rate());
				grnitem.setTax_amt(stTrChDt.getTax_amt());
				grnitem.setWarehouse("0");
				grnitem.setWarehouse_name("0");
				grnitem.setQc_deduction(0);
				grnitem.setQc_norms("0");
				grnitem.setStack_qty(0);
				grnitem.setRack("0");
				grnitem.setStack_uom("0");
				
				grnitem.setDiscount_based_on("");
				grnitem.setDiscount(0);
				grnitem.setDiscount_amt(0);
				
				
				grnitem.setCompany_id(stockTransferChallan.getCompany_id());
				grnitem.setFin_year(stockTransferChallan.getFin_year());
				grnitem.setModified_type("INSERTED");
				grnitem.setInserted_by(stockTransferChallan.getInserted_by());
				grnitem.setInserted_on(stockTransferChallan.getInserted_on());
				grnitem.setUpdated_by(stockTransferChallan.getUpdated_by());
				grnitem.setUpdated_on(stockTransferChallan.getUpdated_on());
				grnitem.setDeleted_by(stockTransferChallan.getDeleted_by());
				grnitem.setDeleted_on(stockTransferChallan.getDeleted_on());
				
				stTrChDt.setStk_transfer_grn_item_details(grnitem);
			}
			
			
		}
		stockTransferChallan.setStk_Transfer_Challan_Item_Dtls(stkTrnChDtl);
		
		stk_Transfer_Challan_BusinessUnit_DtlsRepository.updateStk_Transfer_Challan_BusinessUnit_Dtls(op.get().getStk_challan_id());
		
		Set<Stk_Transfer_Challan_BusinessUnit_Dtls> stTrChBsUDt=new HashSet<Stk_Transfer_Challan_BusinessUnit_Dtls>();
		stTrChBsUDt.addAll(stockTransferChallan.getStk_Transfer_Challan_BusinessUnit_Dtls());
		for(Stk_Transfer_Challan_BusinessUnit_Dtls stTrChBUDt:stTrChBsUDt) 
		{
			stTrChBUDt.setStk_challan_id(op.get().getStk_challan_id());
			stTrChBUDt.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChBUDt.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChBUDt.setFin_year(stockTransferChallan.getFin_year());
			stTrChBUDt.setModified_type("INSERTED");
			stTrChBUDt.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChBUDt.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChBUDt.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChBUDt.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChBUDt.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChBUDt.setDeleted_on(stockTransferChallan.getDeleted_on());
			
			//stk_transfer_grn_bu_dtls starts here
			if(grnentry == true) 
			{
				Stk_transfer_grn_bu_dtls stk_transfer_grn_bu_dtls = new Stk_transfer_grn_bu_dtls();
				
				stk_transfer_grn_bu_dtls.setStk_grn_id(gen_sno1);
				stk_transfer_grn_bu_dtls.setStk_grn_no(strockgrnNumber);
				stk_transfer_grn_bu_dtls.setModified_type("INSERTED");
				stk_transfer_grn_bu_dtls.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				stk_transfer_grn_bu_dtls.setInserted_on(ldt);
				stk_transfer_grn_bu_dtls.setUpdated_by("NA");
				stk_transfer_grn_bu_dtls.setUpdated_on(ldt);
				stk_transfer_grn_bu_dtls.setDeleted_by("NA");
				stk_transfer_grn_bu_dtls.setDeleted_on(ldt);
				stk_transfer_grn_bu_dtls.setCompany_id(stockTransferChallan.getCompany_id());
				stk_transfer_grn_bu_dtls.setFin_year(stockTransferChallan.getFin_year());
				stk_transfer_grn_bu_dtls.setBusinessunit_name(stTrChBUDt.getBusiness_unitname());
				stk_transfer_grn_bu_dtls.setEmail_id("");
				stk_transfer_grn_bu_dtls.setWork_address("");
				
				stTrChBUDt.setStk_transfer_grn_bu_dtls(stk_transfer_grn_bu_dtls);
			}
			//grn bu ends here
		}
		stockTransferChallan.setStk_Transfer_Challan_BusinessUnit_Dtls(stTrChBsUDt);
		
		stk_Transfer_Challan_Shipment_DtlsRepository.updateStk_Transfer_Challan_Shipment_Dtls(op.get().getStk_challan_id());
		
		Set<Stk_Transfer_Challan_Shipment_Dtls> stkTrfrChShDtls=new HashSet<Stk_Transfer_Challan_Shipment_Dtls>();
		stkTrfrChShDtls.add(stockTransferChallan.getStk_Transfer_Challan_Shipment_Dtls());
		for(Stk_Transfer_Challan_Shipment_Dtls stTrChShDt:stkTrfrChShDtls) 
		{
			stTrChShDt.setStk_challan_id(op.get().getStk_challan_id());
			stTrChShDt.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChShDt.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChShDt.setFin_year(stockTransferChallan.getFin_year());
			stTrChShDt.setModified_type("INSERTED");
			stTrChShDt.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChShDt.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChShDt.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChShDt.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChShDt.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChShDt.setDeleted_on(stockTransferChallan.getDeleted_on());
		}
		if(!stkTrfrChShDtls.isEmpty())
		{
			stockTransferChallan.setStk_Transfer_Challan_Shipment_Dtls(stkTrfrChShDtls.iterator().next());
		}
		
		stk_Transfer_Challan_Trans_InfoRepository.updateStk_Transfer_Challan_Trans_Info(op.get().getStk_challan_id());
		
		Set<Stk_Transfer_Challan_Trans_Info> stkTrfrChTrInf=new HashSet<Stk_Transfer_Challan_Trans_Info>();
		stkTrfrChTrInf.add(stockTransferChallan.getStk_Transfer_Challan_Trans_Info());
		for(Stk_Transfer_Challan_Trans_Info stTrChtrIn:stkTrfrChTrInf) 
		{
			stTrChtrIn.setStk_challan_id(op.get().getStk_challan_id());
			stTrChtrIn.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChtrIn.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChtrIn.setFin_year(stockTransferChallan.getFin_year());
			stTrChtrIn.setModified_type("INSERTED");
			stTrChtrIn.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChtrIn.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChtrIn.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChtrIn.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChtrIn.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChtrIn.setDeleted_on(stockTransferChallan.getDeleted_on());
			
	       vehichlenumber=stTrChtrIn.getVehicle_no();
			
			if(grnentry == true) 
			{
				Stk_transfer_grn_trans_info grntrans = new Stk_transfer_grn_trans_info();
				grntrans.setTrans_borne_by(stTrChtrIn.getTrans_borne_by());
				grntrans.setTransporter_code(stTrChtrIn.getTrans_code());
				grntrans.setMode_of_trans(stTrChtrIn.getMode_of_trans());
				grntrans.setStk_grn_id(gen_sno1);
				grntrans.setStk_grn_no(strockgrnNumber);
				
				grntrans.setModified_type("INSERTED");
				grntrans.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				grntrans.setInserted_on(ldt);
				grntrans.setUpdated_by("NA");
				grntrans.setUpdated_on(ldt);
				grntrans.setDeleted_by("NA");
				grntrans.setDeleted_on(ldt);
				grntrans.setCompany_id(stockTransferChallan.getCompany_id());
				grntrans.setFin_year(stockTransferChallan.getFin_year());
				grntrans.setAcc_name("");
				grntrans.setAcc_no("0");
				grntrans.setBank_name("");
				grntrans.setBic_swift_code("");
				grntrans.setPayment_mode("");
				grntrans.setPayment_term("");
				grntrans.setBranch("");
				grntrans.setCash_limit(0.00);
				grntrans.setTransportation_rate("");
				
				stTrChtrIn.setStk_transfer_grn_trans_info(grntrans);
			}
		}
		if(!stkTrfrChTrInf.isEmpty())
		{
			stockTransferChallan.setStk_Transfer_Challan_Trans_Info(stkTrfrChTrInf.iterator().next());
		}
		
		stk_Transfer_Challan_Weight_DtlRepository.updateStk_Transfer_Challan_Weight_Dtl(op.get().getStk_challan_id());
		
		Set<Stk_Transfer_Challan_Weight_Dtl> stkTrfrChWeDt=new HashSet<Stk_Transfer_Challan_Weight_Dtl>();
		stkTrfrChWeDt.add(stockTransferChallan.getStk_Transfer_Challan_Weight_Dtl());
		for(Stk_Transfer_Challan_Weight_Dtl stTrChWeDt:stkTrfrChWeDt) 
		{
			stTrChWeDt.setStk_challan_id(op.get().getStk_challan_id());
			stTrChWeDt.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChWeDt.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChWeDt.setFin_year(stockTransferChallan.getFin_year());
			stTrChWeDt.setModified_type("INSERTED");
			stTrChWeDt.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChWeDt.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChWeDt.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChWeDt.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChWeDt.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChWeDt.setDeleted_on(stockTransferChallan.getDeleted_on());
		}
		if(!stkTrfrChWeDt.isEmpty())
		{
			stockTransferChallan.setStk_Transfer_Challan_Weight_Dtl(stkTrfrChWeDt.iterator().next());
		}
		
		stk_Transfer_Challan_DocsRepository.updateStk_Transfer_Challan_Docs(op.get().getStk_challan_id());
		
		Set<Stk_Transfer_Challan_Docs> stTrChDocs=new HashSet<Stk_Transfer_Challan_Docs>();
		stTrChDocs.addAll(stockTransferChallan.getStk_Transfer_Challan_Docs());
		for(Stk_Transfer_Challan_Docs stTrChDcs:stTrChDocs) 
		{
			stTrChDcs.setStk_challan_id(op.get().getStk_challan_id());
			stTrChDcs.setStk_challan_no(stockTransferChallan.getStk_challan_no());
			stTrChDcs.setCompany_id(stockTransferChallan.getCompany_id());
			stTrChDcs.setFin_year(stockTransferChallan.getFin_year());
			stTrChDcs.setModified_type("INSERTED");
			stTrChDcs.setInserted_by(stockTransferChallan.getInserted_by());
			stTrChDcs.setInserted_on(stockTransferChallan.getInserted_on());
			stTrChDcs.setUpdated_by(stockTransferChallan.getUpdated_by());
			stTrChDcs.setUpdated_on(stockTransferChallan.getUpdated_on());
			stTrChDcs.setDeleted_by(stockTransferChallan.getDeleted_by());
			stTrChDcs.setDeleted_on(stockTransferChallan.getDeleted_on());
			
		//Stk_transfer_grn_docs starts here
			if(grnentry == true) 
			{
				Stk_transfer_grn_docs stk_transfer_grn_docs = new Stk_transfer_grn_docs();
				
				stk_transfer_grn_docs.setStk_grn_id(gen_sno1);
				stk_transfer_grn_docs.setStk_grn_no(strockgrnNumber);
				stk_transfer_grn_docs.setModified_type("INSERTED");
				stk_transfer_grn_docs.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				stk_transfer_grn_docs.setInserted_on(ldt);
				stk_transfer_grn_docs.setUpdated_by("NA");
				stk_transfer_grn_docs.setUpdated_on(ldt);
				stk_transfer_grn_docs.setDeleted_by("NA");
				stk_transfer_grn_docs.setDeleted_on(ldt);
				stk_transfer_grn_docs.setCompany_id(stockTransferChallan.getCompany_id());
				stk_transfer_grn_docs.setFin_year(stockTransferChallan.getFin_year());
				stk_transfer_grn_docs.setDoc_name("");
				
				stTrChDcs.setStk_transfer_grn_docs(stk_transfer_grn_docs);
				
			}
			//grn docs ends here
			
		}
		stockTransferChallan.setStk_Transfer_Challan_Docs(stTrChDocs);
		
		
		
		if(grnentry == true) 
		{
				Stk_transfer_grn grn =new Stk_transfer_grn();
				grn.setStk_grn_id(gen_sno1);
				grn.setStk_grn_no(strockgrnNumber);//stk_grn_date
				grn.setB_unit(stockTransferChallan.getBusiness_unit());
				grn.setB_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(stockTransferChallan.getBusiness_unit()).getBusinessunit_name());
				grn.setApplicable_charges_id("0");
				grn.setModified_type("INSERTED");
				grn.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				grn.setInserted_on(ldt);
				grn.setUpdated_by("NA");
				grn.setUpdated_on(ldt);
				grn.setDeleted_by("NA");
				grn.setDeleted_on(ldt);
				grn.setCompany_id(stockTransferChallan.getCompany_id());
				grn.setFin_year(stockTransferChallan.getFin_year());
				grn.setItem_sub_type("0");
				grn.setItem_type("0");
				grn.setReference_id(op.get().getStk_challan_id());
				grn.setReferance_type("0");
				grn.setStk_grn_date(stockTransferChallan.getStk_challan_date());
				//vehichlenumber
				grn.setVehicle_id(vehichlenumber);
				grn.setReference_status("Direct");
				if(Utility.isNullOrEmpty(vehichlenumber)) 
				{
					if(vehichlenumber.compareToIgnoreCase("")==0 || vehichlenumber.compareToIgnoreCase("0")==0)
					{
						
					}
					else
					{
						grn.setVehicle_no(vehicleMasterRepository.getVehicleDetails(vehichlenumber).getVehicle_no());
					}
				}
				grn.setReceipt_criteria(stockTransferChallan.getPassing_wt());
				grn.setRec_b_unit(stockTransferChallan.getDelivery_business_unit());
				stockTransferChallan.setStk_transfer_grn(grn);
				
				//Stk_transfer_grn_other_info starts here
				Stk_transfer_grn_other_info stk_transfer_grn_other_info=new Stk_transfer_grn_other_info();
				
				stk_transfer_grn_other_info.setStk_grn_id(gen_sno1);
				stk_transfer_grn_other_info.setStk_grn_no(strockgrnNumber);
				stk_transfer_grn_other_info.setModified_type("INSERTED");
				stk_transfer_grn_other_info.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				stk_transfer_grn_other_info.setInserted_on(ldt);
				stk_transfer_grn_other_info.setUpdated_by("NA");
				stk_transfer_grn_other_info.setUpdated_on(ldt);
				stk_transfer_grn_other_info.setDeleted_by("NA");
				stk_transfer_grn_other_info.setDeleted_on(ldt);
				stk_transfer_grn_other_info.setCompany_id(stockTransferChallan.getCompany_id());
				stk_transfer_grn_other_info.setFin_year(stockTransferChallan.getFin_year());
				
				stockTransferChallan.setStk_transfer_grn_other_info(stk_transfer_grn_other_info);
				
				//Stk_transfer_grn_other_info ends here
				
				//Stk_transfer_grn_resource_cost starts here
				Stk_transfer_grn_resource_cost stk_transfer_grn_resource_cost = new Stk_transfer_grn_resource_cost();
				stk_transfer_grn_resource_cost.setStk_grn_id(gen_sno1);
				stk_transfer_grn_resource_cost.setStk_grn_no(strockgrnNumber);
				stk_transfer_grn_resource_cost.setModified_type("INSERTED");
				stk_transfer_grn_resource_cost.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				stk_transfer_grn_resource_cost.setInserted_on(ldt);
				stk_transfer_grn_resource_cost.setUpdated_by("NA");
				stk_transfer_grn_resource_cost.setUpdated_on(ldt);
				stk_transfer_grn_resource_cost.setDeleted_by("NA");
				stk_transfer_grn_resource_cost.setDeleted_on(ldt);
				stk_transfer_grn_resource_cost.setCompany_id(stockTransferChallan.getCompany_id());
				stk_transfer_grn_resource_cost.setFin_year(stockTransferChallan.getFin_year());
				stk_transfer_grn_resource_cost.setCharge_name("");
				stk_transfer_grn_resource_cost.setGross_amt(0.000);
				stk_transfer_grn_resource_cost.setRate_cal_method("");
				stk_transfer_grn_resource_cost.setTax_amt(0.000);
				stk_transfer_grn_resource_cost.setTax_rate(0.000);
				
				stockTransferChallan.setStk_transfer_grn_resource_cost(stk_transfer_grn_resource_cost);
				//Stk_transfer_grn_resource_cost ends here
				
		}
		//Vehicle Entry Starts
		
		if(stockTransferChallan.getRef_type().compareToIgnoreCase("Goods Stock Transfer")==0)
		{
			String weimentstatus=stock_TransferRepository.getStkOrder(stockTransferChallan.getReference_id()).getWeightment_req();
			if((weimentstatus.compareToIgnoreCase("No")==0 || weimentstatus.compareToIgnoreCase("0")==0) && stockTransferChallan.getOrder_point().compareToIgnoreCase("Inter")==0)
			{
				//new vehicle records table starts
				vehicleMasterRepository.resetGoodsTransferChallanVehicle(op.get().getStk_challan_id());
				Vehicle_weighment_load_unload  vehicle_weighment_load_unload = new Vehicle_weighment_load_unload();
				
				vehicle_weighment_load_unload.setVehicle_no(vehicleMasterRepository.getVehicleDetails(vehichlenumber).getVehicle_no());
				vehicle_weighment_load_unload.setVehicle_id(vehichlenumber);
				vehicle_weighment_load_unload.setReference_id(op.get().getStk_challan_id());
				vehicle_weighment_load_unload.setRef_name("Goods Stock Transfer");
				vehicle_weighment_load_unload.setModified_type("INSERTED");
				vehicle_weighment_load_unload.setInserted_by(userRepository.getUserDetails(stockTransferChallan.getUsername()).getName());
				vehicle_weighment_load_unload.setInserted_on(ldt);
				vehicle_weighment_load_unload.setUpdated_by("NA");
				vehicle_weighment_load_unload.setUpdated_on(ldt);
				vehicle_weighment_load_unload.setDeleted_by("NA");
				vehicle_weighment_load_unload.setDeleted_on(ldt);
				vehicle_weighment_load_unload.setCompany_id(stockTransferChallan.getCompany_id());
				vehicle_weighment_load_unload.setFin_year(stockTransferChallan.getFin_year());
				vehicle_weighment_load_unload.setRef_name_type("Goods Stock Transfer");
					
				stockTransferChallan.setVehicle_weighment_load_unload(vehicle_weighment_load_unload);
				
				//ends
			}
		}
		//Vehicle Entry Ends
		return stockTransferChallanRepository.save(stockTransferChallan);
	}
	
	
	public List<Stk_Transfer_Challan> findAll()
	{
		List<Stk_Transfer_Challan> sChallans=new ArrayList<Stk_Transfer_Challan>();
		sChallans.addAll(stockTransferChallanRepository.findAllStkChallans());
		for(int i=0;i<sChallans.size();i++) {
			sChallans.get(i).setBusiness_unit(companyBusinessUnitMasterRepository.CompanyBUAddress(sChallans.get(i).getBusiness_unit()).getBusinessunit_name());
		}
		return sChallans;
	}
	
	public Stk_Transfer_Challan findOne(long id){
		Optional<Stk_Transfer_Challan> op=this.stockTransferChallanRepository.findById(id);
		return op.get();
	}
	
	public List<Stk_Transfer_ChallanDTO> getStkTransChallanThruBUnit(String bunit){
		List<Stk_Transfer_Challan> modelList=stockTransferChallanRepository.getStkTransChallanThruBUnit(bunit);
		
		Type listType=new TypeToken<List<Stk_Transfer_ChallanDTO>>() {}.getType();
		
		List<Stk_Transfer_ChallanDTO> stkChallan=new ModelMapper().map(modelList, listType);
		
		return stkChallan;
	}
	
	public List<Stk_Transfer_ChallanDTO> getStkTransChallans(String bunit,String invdate,String comp,String finyear){
		List<Stk_Transfer_Challan> modelList=stockTransferChallanRepository.getStkTransChallans(bunit,invdate,comp,finyear);
		
		Type listType=new TypeToken<List<Stk_Transfer_ChallanDTO>>() {}.getType();
		
		List<Stk_Transfer_ChallanDTO> stkChallan=new ModelMapper().map(modelList, listType);
		
		return stkChallan;
	}
	
	public List<Stk_Transfer_ChallanDTO> getstocktransferchallaninunloading(String bunit,String invdate,String comp,String finyear)
	{
		List<Stk_Transfer_Challan> modelList=stockTransferChallanRepository.getstocktransferchallaninunloadingNew(bunit,invdate,comp,finyear);
		
		Type listType=new TypeToken<List<Stk_Transfer_ChallanDTO>>() {}.getType();
		
		List<Stk_Transfer_ChallanDTO> stkChallan=new ModelMapper().map(modelList, listType);
		
		return stkChallan;
	}
	
	public Stk_Transfer_ChallanDTO getStkTransChallanDtls(String stk_challan_id)
	{
		Stk_Transfer_Challan sTransfer_Challan=stockTransferChallanRepository.getStkTransChallanDtls(stk_challan_id);
		
		Type listType=new TypeToken<Stk_Transfer_ChallanDTO>() {}.getType();
		
		Stk_Transfer_ChallanDTO sChallanDTO=new ModelMapper().map(sTransfer_Challan,listType);
		
		return sChallanDTO;
	}
	
	public List<Stk_Transfer_Challan_Item_DtlsDTO> getStkTransChallanItemDlts(String stk_challan_id)
	{
		List<Stk_Transfer_Challan_Item_Dtls> modelList=stk_Transfer_Challan_Item_DtlsRepository.getStkTransChallanItemDlts(stk_challan_id);
		
			
		Type listType=new TypeToken<List<Stk_Transfer_Challan_Item_DtlsDTO>>() {}.getType();
		
		List<Stk_Transfer_Challan_Item_DtlsDTO> stkTrItmDtls=new ModelMapper().map(modelList,listType);
		
		return stkTrItmDtls;
	}
	
	public Stk_Transfer_Challan_Shipment_DtlsDTO getStkTransChallanShipDtls(String stk_challan_id){

		Stk_Transfer_Challan_Shipment_Dtls modelList=stk_Transfer_Challan_Shipment_DtlsRepository.getStkTransChallanShipDtls(stk_challan_id);

		Type listType = new TypeToken<Stk_Transfer_Challan_Shipment_DtlsDTO>() {}.getType();

		Stk_Transfer_Challan_Shipment_DtlsDTO stkTrShip= new ModelMapper().map(modelList,listType);
		return stkTrShip;
	}
	
	public Stk_Transfer_Challan_Trans_InfoDTO getStkTransChallanTranInfo(String stk_challan_id){

		Stk_Transfer_Challan_Trans_Info modelList=stk_Transfer_Challan_Trans_InfoRepository.getStkTransChallanTranInfo(stk_challan_id);

		Type listType = new TypeToken<Stk_Transfer_Challan_Trans_InfoDTO>() {}.getType();

		Stk_Transfer_Challan_Trans_InfoDTO stkTrTraInfo= new ModelMapper().map(modelList,listType);
		return stkTrTraInfo;
	}
	
	
	public List<Invoice_Stk_Transfer_Challan_Trans_InfoDTO> getStkTransChallanTranInfos(String stk_challan_id) {
		  String[] arrOfStr=stk_challan_id.split(",");
		  List<Invoice_Stk_Transfer_Challan_Trans_InfoDTO> sTrans_InfoDTOs=new ArrayList<Invoice_Stk_Transfer_Challan_Trans_InfoDTO>();
		  for(int i=0;i<arrOfStr.length;i++)
		  {
			  
			  Stk_Transfer_Challan_Trans_Info transInfo=stk_Transfer_Challan_Trans_InfoRepository.getStkTransChallanTranInfo(arrOfStr[i]);
			  Stk_Transfer_Challan_Weight_Dtl wtInfo=stk_Transfer_Challan_Weight_DtlRepository.getStkTransChallanWtDtls(arrOfStr[i]);
			  //System.out.println(transInfo.getTrans_code()+" ,, "+vehicleMasterRepository.getVehicleDetails(transInfo.getVehicle_no()).getVehtype_code()+" ,, "+transInfo.getVehicle_no()+" ,, "+wtInfo.getEway_bill_no()+" ,, ");
			  if(Utility.isNullOrEmpty(transInfo.getTrans_code()) ) 
			  {
				  System.out.println("if ");
				 // Invoice_Stk_Transfer_Challan_Trans_InfoDTO def=new Invoice_Stk_Transfer_Challan_Trans_InfoDTO(transInfo.getTrans_code(),vehicleMasterRepository.getVehicleDetails(transInfo.getVehicle_no()).getVehtype_code(),transInfo.getVehicle_no(),wtInfo.getEway_bill_no());
				  
				  if(transInfo.getTrans_code().compareToIgnoreCase("")==0)
				  {
					  Invoice_Stk_Transfer_Challan_Trans_InfoDTO def=new Invoice_Stk_Transfer_Challan_Trans_InfoDTO();
					  def.setTransname("");
					  sTrans_InfoDTOs.add(def);
				  }
				  else 
				  {
					  Invoice_Stk_Transfer_Challan_Trans_InfoDTO def=new Invoice_Stk_Transfer_Challan_Trans_InfoDTO();
					  def.setTransname(transInfo.getTrans_code());
					  def.setVehicletype(vehicleMasterRepository.getVehicleDetails(transInfo.getVehicle_no()).getVehtype_code());
					  def.setVehicleno(transInfo.getVehicle_no());
					  def.setEwaybillno(wtInfo.getEway_bill_no());
					  
					  sTrans_InfoDTOs.add(def);
				  }
				  
				  
			  }
			  else 
			  {
				  System.out.println("else ");
				  Invoice_Stk_Transfer_Challan_Trans_InfoDTO def=new Invoice_Stk_Transfer_Challan_Trans_InfoDTO();
				  def.setTransname("");
				  sTrans_InfoDTOs.add(def);
				  
			  }
			 /* if(transInfo.getTrans_code().compareToIgnoreCase("")==0)
			  {
				  Invoice_Stk_Transfer_Challan_Trans_InfoDTO def=new Invoice_Stk_Transfer_Challan_Trans_InfoDTO();
				  def.setTransname("");
				  sTrans_InfoDTOs.add(def);
			  }
			  else
			  {
				  Invoice_Stk_Transfer_Challan_Trans_InfoDTO def=new Invoice_Stk_Transfer_Challan_Trans_InfoDTO(transInfo.getTrans_code(),vehicleMasterRepository.getVehicleDetails(transInfo.getVehicle_no()).getVehtype_code(),transInfo.getVehicle_no(),wtInfo.getEway_bill_no());
				  sTrans_InfoDTOs.add(def);
			  }
			  */
			 
		  }
		  
		  Type listType = new TypeToken<List<Invoice_Stk_Transfer_Challan_Trans_InfoDTO>>() {}.getType();

		  List<Invoice_Stk_Transfer_Challan_Trans_InfoDTO> invTrans= new ModelMapper().map(sTrans_InfoDTOs,listType);
		
		  return invTrans;
	}
	
	public List<Stk_Transfer_Challan_Docs> getStkTransChallanDocs(String stk_challan_id)
	{
		List<Stk_Transfer_Challan_Docs> modelList=stk_Transfer_Challan_DocsRepository.getStkTransChallanDocs(stk_challan_id);
		
		Type listType=new TypeToken<List<Stk_Transfer_Challan_Docs>>() {}.getType();
		
		List<Stk_Transfer_Challan_Docs> stkTrItmDtls=new ModelMapper().map(modelList,listType);
		
		return stkTrItmDtls;
	}
	
	public Stk_Transfer_Challan_Weight_DtlDTO getStkTransChallanWtDtls(String stk_challan_id)
	{
		Stk_Transfer_Challan_Weight_Dtl modelList=stk_Transfer_Challan_Weight_DtlDTORepository.getStkTransChallanWtDtls(stk_challan_id);
		Type listType = new TypeToken<Stk_Transfer_Challan_Weight_DtlDTO>() {}.getType();
		Stk_Transfer_Challan_Weight_DtlDTO stkTrTraInfo= new ModelMapper().map(modelList,listType);
		return stkTrTraInfo;
	}
	
	public List<Stk_Transfer_Challan_BusinessUnit_Dtls> getStkTransBUDtls(String stk_challan_id)
	{
		List<Stk_Transfer_Challan_BusinessUnit_Dtls> modelList=stk_Transfer_Challan_BusinessUnit_DtlsRepository.getStkTransBUDtls(stk_challan_id);
		Type listType=new TypeToken<List<Stk_Transfer_Challan_BusinessUnit_Dtls>>() {}.getType();
		List<Stk_Transfer_Challan_BusinessUnit_Dtls> stkTUnit_Dtls=new ModelMapper().map(modelList,listType);
		return stkTUnit_Dtls;
	}
	 
	@Transactional
	public Stk_Transfer_Challan deleteStocktransferChallan(Stk_Transfer_Challan challan,Long id) {

			Optional<Stk_Transfer_Challan> op = stockTransferChallanRepository.findById(id);
			
			LocalDateTime ldt = LocalDateTime.now();
			Stk_Transfer_Challan sChallan=op.get();
			boolean grnentry=false;
			sChallan.setModified_type("DELETED");
			sChallan.setInserted_by(op.get().getInserted_by());
			sChallan.setInserted_on(op.get().getInserted_on());
			sChallan.setUpdated_by(op.get().getUpdated_by());
			sChallan.setUpdated_on(op.get().getUpdated_on());
			sChallan.setDeleted_by(userRepository.getUserDetails(sChallan.getUsername()).getName());
			sChallan.setDeleted_on(ldt);
			
			stockTransferChallanRepository.stkTransferChallanItemDtlsUpdate(op.get().getStk_challan_id());
			
			stockTransferChallanRepository.stkTransferChallanBusinessUnitDtlsUpdate(op.get().getStk_challan_id());
			
			stockTransferChallanRepository.stkTransferChallanShipmentDtlsUpdate(op.get().getStk_challan_id());
			
			stockTransferChallanRepository.stkTransferChallanTransInfoUpdate(op.get().getStk_challan_id());
			
			stockTransferChallanRepository.stkTransferChallanWeightDtlUpdate(op.get().getStk_challan_id());
			
			stockTransferChallanRepository.stkTransferChallanDocsUpdate(op.get().getStk_challan_id());
			if(op.get().getWeighment_required().compareToIgnoreCase("No") == 0 || op.get().getRef_type().compareToIgnoreCase("Open Stock Transfer Challan") == 0) 
			{
				grnentry=true;
			}
			
			if(grnentry == true) 
			{
				String grnid=stk_transfer_grnRepository.getGrn(op.get().getReference_id()).getStk_grn_id();
				stk_transfer_grnRepository.updateStkTransferGrnItemDetails(grnid);
				stk_transfer_grnRepository.updateStkTransferGrnTransportDetails(grnid);
				stk_transfer_grnRepository.updateStkTransferGrn(grnid);
				
				stk_transfer_grnRepository.stkTransferGrnBuDtlsUpdate(grnid);
				stk_transfer_grnRepository.stkTransferGrnDocsUpdate(grnid);
				stk_transfer_grnRepository.stkTransferGrnOtherInfoUpdate(grnid);
				stk_transfer_grnRepository.stkTransferGrnResourceCostUpdate(grnid);
				
			}
			
			return stockTransferChallanRepository.save(sChallan);	
	}

	public Stk_Transfer_Challan getStkOrderDetails(String stk_challan_id)
	{
		Stk_Transfer_Challan modelList=stockTransferChallanRepository.getStkOrderDetails(stk_challan_id);
		Stk_Transfer_Challan newList = new Stk_Transfer_Challan();
		
		if(modelList.getRef_type().compareToIgnoreCase("Open Stock Transfer Challan")==0)
		{
			newList.setRef_type(modelList.getRef_type());
			newList.setCust_ref_doc_no("Open Stock Transfer Challan");
			newList.setStk_challan_date("");
			
		}
		else
		{
			if(modelList.getRef_type().compareToIgnoreCase("Goods Stock Transfer")==0)
			{
				Stock_Transfer stkOrd = stock_TransferRepository.getStkOrder(modelList.getReference_id());
				newList.setStk_challan_date(stkOrd.getOrder_date());
				newList.setCust_ref_doc_no(stkOrd.getOrder_no());
			}
			
			if(modelList.getRef_type().compareToIgnoreCase("Loading Advice")==0)
			{
				Wm_loading_advice loadingAdv = wm_loading_adviceRepository.getLoadingStk(modelList.getReference_id());
				Stock_Transfer stkOrd1 = stock_TransferRepository.getStkOrder(loadingAdv.getReferance_id());
				newList.setStk_challan_date(stkOrd1.getOrder_date());
				newList.setCust_ref_doc_no(stkOrd1.getOrder_no());
			}
		}
		return newList;
	}
	
	public Wm_loading_adviceDTO getStockTransLoadingWeighmentId(long id){
			
		Optional<Stk_Transfer_Challan> op = stockTransferChallanRepository.findById(id);
		
		Wm_loading_advice loadingAdv = wm_loading_adviceRepository.getLoadingStkbyId(op.get().getReference_id());
		
		loadingAdv.setBus_partner(op.get().getStk_challan_id());
		
		Type listType=new TypeToken<Wm_loading_adviceDTO>() {}.getType();
		Wm_loading_adviceDTO loadwmstk=new ModelMapper().map(loadingAdv,listType);
		
		return loadwmstk;
		
	}
	
	public List<Stk_Transfer_Challan_Item_Dtls> getMultipleStkTransChallanDtls(String stk_challan_id)
	{
		
		
         String []splitstk_challan_id=stk_challan_id.split(",");
		 
		 List<Stk_Transfer_Challan_Item_Dtls> stk_challan_idlist=new ArrayList<Stk_Transfer_Challan_Item_Dtls>();
		 for(int i=0;i<splitstk_challan_id.length;i++) 
		 {
			 System.out.println("challan id  " + splitstk_challan_id[i]);
			// stk_challan_idlist.add(stockTransferChallanRepository.getStkTransChallanDtls(splitstk_challan_id[i]));
			 stk_challan_idlist.addAll(stk_Transfer_Challan_Item_DtlsRepository.getStkTransChallanItemDlts(splitstk_challan_id[i]));
			 
		 }
		
		return stk_challan_idlist;
	}
	public List<Stk_Transfer_Challan_Docs> getMultipleStkTransChallanDocs(String stk_challan_id)
	{
		
		
         String []splitstk_challan_id=stk_challan_id.split(",");
		 
		 List<Stk_Transfer_Challan_Docs> challanlist=new ArrayList<Stk_Transfer_Challan_Docs>();
		 for(int i=0;i<splitstk_challan_id.length;i++) 
		 {
			 challanlist.addAll(stk_Transfer_Challan_DocsRepository.getStkTransChallanDocs(splitstk_challan_id[i]));
		 }
		Type listType=new TypeToken<List<Stk_Transfer_Challan_Docs>>() {}.getType();
		
		List<Stk_Transfer_Challan_Docs> stkTrItmDtls=new ModelMapper().map(challanlist,listType);
		
		return stkTrItmDtls;
	}
	
	
	
	
	
	public List<Stk_Transfer_ChallanDTO> getmutiplegetStkTransChallans(long id)
	{
		
		Optional<Stk_transfer_sales_inv> op = stk_transfer_sales_invRepository.findById(id);
		
		
		String []splitstockchallan=op.get().getReference_id().split(",");
		
		List<Stk_Transfer_Challan> modelList=new ArrayList<Stk_Transfer_Challan>();
		for(int i=0;i<splitstockchallan.length;i++) 
		{
			
			
			modelList.add(stockTransferChallanRepository.getStkTransChallanDtls(splitstockchallan[i]));
			
		}
		
		
		Type listType=new TypeToken<List<Stk_Transfer_ChallanDTO>>() {}.getType();
		
		List<Stk_Transfer_ChallanDTO> stkTrItmDtls=new ModelMapper().map(modelList,listType);
		
		return stkTrItmDtls;
	}
	
	public Stk_Transfer_Challan getMultipleStkOrderDetails(String stk_challan_id)
	{
		
         String []splitstk_challan_id=stk_challan_id.split(",");
		 
		 List<Stk_Transfer_Challan> challanlist=new ArrayList<Stk_Transfer_Challan>();
		 for(int i=0;i<splitstk_challan_id.length;i++) 
		 {
			 challanlist.add(stockTransferChallanRepository.getStkOrderDetails(splitstk_challan_id[i]));
		 }
		
		
		Stk_Transfer_Challan modelList=stockTransferChallanRepository.getStkOrderDetails(stk_challan_id);
		
		
		String cust_ref_doc_no="",st_chllan_date="",challan_number="",challan_date="";
		
		Stk_Transfer_Challan newList = new Stk_Transfer_Challan();
		
		for(int i=0;i<challanlist.size();i++) 
		{
			
			if(challanlist.get(i).getRef_type().compareToIgnoreCase("Open Stock Transfer Challan")==0)
			{
				
				cust_ref_doc_no+="Open Stock Transfer Challan"+",";
				st_chllan_date+="NA"+",";
				challan_number+=challanlist.get(i).getStk_challan_no()+",";
				challan_date+=challanlist.get(i).getStk_challan_date()+",";
				newList.setRemark(challanlist.get(i).getStk_challan_date());
			}
			else
			{
				if(challanlist.get(i).getRef_type().compareToIgnoreCase("Goods Stock Transfer")==0)
				{
					Stock_Transfer stkOrd = stock_TransferRepository.getStkOrder(challanlist.get(i).getReference_id());
					cust_ref_doc_no+=stkOrd.getOrder_date()+",";
					st_chllan_date+=stkOrd.getOrder_no()+",";
					newList.setReason(stkOrd.getOrder_date());
					challan_number+=challanlist.get(i).getStk_challan_no()+",";
					challan_date+=challanlist.get(i).getStk_challan_date()+",";
					newList.setRemark(challanlist.get(i).getStk_challan_date());
				}
				
				if(challanlist.get(i).getRef_type().compareToIgnoreCase("Loading Advice")==0)
				{
					Wm_loading_advice loadingAdv = wm_loading_adviceRepository.getLoadingStk(challanlist.get(i).getReference_id());
					Stock_Transfer stkOrd1 = stock_TransferRepository.getStkOrder(loadingAdv.getReferance_id());
					//newList.setStk_challan_date(stkOrd1.getOrder_date());
					//newList.setCust_ref_doc_no(stkOrd1.getOrder_no());
					newList.setReason(stkOrd1.getOrder_date());//stockorderdate single 
					cust_ref_doc_no+=stkOrd1.getOrder_date()+",";//stockorderdate multiple
					st_chllan_date+=stkOrd1.getOrder_no()+",";//stockordernumber multiple
					challan_number+=challanlist.get(i).getStk_challan_no()+",";//stockchallan number multpile 
					challan_date+=challanlist.get(i).getStk_challan_date()+",";//stock challan date multiple
					newList.setRemark(challanlist.get(i).getStk_challan_date());//stock challan date single
					
				}
			}
		}
		
		newList.setStk_challan_date(st_chllan_date.substring(0, st_chllan_date.length()-1));
		newList.setCust_ref_doc_no(cust_ref_doc_no.substring(0, cust_ref_doc_no.length()-1));
		newList.setStk_challan_no(challan_number.substring(0, challan_number.length()-1));
		newList.setStk_challan_date2(challan_date.substring(0, challan_date.length()-1));
		return newList;
	}
	
	public Stk_Transfer_Challan_Trans_Info getStkChallanVehicleNo(String order_id){

		Stk_Transfer_Challan_Trans_Info modelList=stockTransferChallanRepository.getStkChallanVehicleNo(order_id);
		
		return modelList;
	}
	public Stk_Transfer_Challan getReceipt_criteria(String stk_challan_id,String unload){

		Stk_Transfer_Challan challan=new Stk_Transfer_Challan();
		if(unload.compareToIgnoreCase("unload")==0)
		{
			String challanid=wm_unload_adviceRepository.getUnloadId(stk_challan_id).getReferance_id();
			challan=stockTransferChallanRepository.getStkOrderDetails(challanid);
		}
		else
		{
			challan=stockTransferChallanRepository.getStkOrderDetails(stk_challan_id);
		}
		
		return challan;
	}
	
	public Stk_Transfer_Challan_Item_Dtls getChallanItemDlts(String stk_challan_id,String itemid){

		Stk_Transfer_Challan_Item_Dtls challan = stockTransferChallanRepository.getitemDetails(stk_challan_id,itemid);
		
		return challan;
	}
	
	public Wm_loading_advice_driver_dtls getStockVehicleAndDriver(String challan_id)
	{
		Stk_Transfer_Challan_Trans_Info challan = stockTransferChallanRepository.getStockVehicleAndDriver(challan_id);
		//System.out.println("vehi chk:"+challan_id+"//"+challan.getVehicle_no());
		Wm_loading_advice_driver_dtls vehicleChallan = new Wm_loading_advice_driver_dtls();
		
		Stk_Transfer_Challan challanRefference=stockTransferChallanRepository.getStkOrderDetails(challan_id);
				
		vehicleChallan=stockTransferChallanRepository.getStockDriverName(challanRefference.getReference_id());
		
		vehicleChallan.setDoc_no(challan.getVehicle_no());
		
		return vehicleChallan;
	}
	
	public Stock_Transfer findOneChallan(long id){

		Optional<Stk_Transfer_Challan> op = stockTransferChallanRepository.findById(id);
		
		//System.out.println("chk refid:"+op.get().getReference_id());
		
		Stock_Transfer challan = stock_TransferRepository.getReceivingBuLoadingAdvice(op.get().getReference_id());
		challan.setService_item(op.get().getStk_challan_id());
		return challan;
	}

	public Stk_Transfer_Challan getstockchallandetails(String stk_challan_id) 
	{
		Stk_Transfer_Challan challandetails=stockTransferChallanRepository.getStkOrderDetails(stk_challan_id);
		return challandetails;
	}
	
	
	
}
