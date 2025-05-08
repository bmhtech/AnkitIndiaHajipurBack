package com.AnkitIndia.jwtauthentication.security.services;

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
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_bu_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_docs;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_item_details;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_other_info;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_resource_cost;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_trans_info;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv;
import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.repository.BinMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_ChallanRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_grnRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_grn_bu_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_grn_docsRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_grn_item_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_grn_other_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_grn_resource_costRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_grn_trans_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_pur_invRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.WarehouseMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.VehicleMasterDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_ChallanDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grnDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_bu_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_docsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_other_infoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_resource_costDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_trans_infoDTO;

@Service
public class Stk_transfer_grnService_Imp implements Stk_transfer_grnService {
	
	@Autowired
	Stk_transfer_grnRepository stk_transfer_grnRepository;

	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	WarehouseMasterRepository warehouseMasterRepository;
	
	@Autowired
	BinMasterRepository binMasterRepository;
	
	@Autowired
	Stk_transfer_grn_item_detailsRepository stk_transfer_grn_item_detailsRepository;
	
	@Autowired
	Stk_transfer_grn_trans_infoRepository stk_transfer_grn_trans_infoRepository;
	
	@Autowired
	Stk_transfer_grn_bu_dtlsRepository stk_transfer_grn_bu_dtlsRepository;
	
	@Autowired
	Stk_transfer_grn_other_infoRepository stk_transfer_grn_other_infoRepository;
	
	@Autowired
	Stk_transfer_grn_resource_costRepository stk_transfer_grn_resource_costRepository;
	
	@Autowired
	Stk_transfer_grn_docsRepository stk_transfer_grn_docsRepository;
	
	@Autowired
	Stk_Transfer_ChallanRepository stockTransferChallanRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	@Autowired
	Stk_transfer_pur_invRepository stk_transfer_pur_invRepository;
	
	public SequenceIdDTO getSTGRNSequenceId(String tdate,String bunit){
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
	public Stk_transfer_grn save(Stk_transfer_grn sGrn)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(stk_transfer_grnRepository.countId() != null ) {
			slno=Long.parseLong(stk_transfer_grnRepository.countId());
		}
		String prefix="STG";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		sGrn.setStk_grn_id(gen_sno);
		
		if(sGrn.getB_unit().compareTo("0") !=0 && sGrn.getB_unit().compareTo("") !=0 && sGrn.getB_unit() !=null) {
			sGrn.setB_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(sGrn.getB_unit()).getBusinessunit_name());
		}else {sGrn.setB_unit("None");}
		
		if(sGrn.getVehicle_id().compareToIgnoreCase("No Vehicle") == 0) {
			sGrn.setVehicle_no("No Vehicle");
		}
		else {
			if(sGrn.getVehicle_id().compareTo("0") !=0 && sGrn.getVehicle_id().compareTo("") !=0 && sGrn.getVehicle_id() !=null) {
			sGrn.setVehicle_no(vehicleMasterRepository.getVehicleDetails(sGrn.getVehicle_id()).getVehicle_no());
			}else {sGrn.setVehicle_no("None");}
		}
		
		
		if(sGrn.getReference_status().compareToIgnoreCase("Stock Transfer")==0)
		{
			stk_transfer_grnRepository.updateStockGRNStatus(sGrn.getReference_id(),sGrn.getVehicle_id());
		}
		if(sGrn.getReference_status().compareToIgnoreCase("Goods Stock Transfer")==0)
		{
			stk_transfer_grnRepository.updateStockGRNGoodsStatus(sGrn.getReference_id(),sGrn.getVehicle_id());
		}
		
		
		sGrn.setModified_type("INSERTED");
		sGrn.setInserted_by(userRepository.getUserDetails(sGrn.getUsername()).getName());
		sGrn.setInserted_on(ldt);
		sGrn.setUpdated_by("NA");
		sGrn.setUpdated_on(ldt);
		sGrn.setDeleted_by("NA");
		sGrn.setDeleted_on(ldt);
			
		//Dynamic
		Set<Stk_transfer_grn_item_details> itemSet = new HashSet<Stk_transfer_grn_item_details>();
		itemSet.addAll(sGrn.getStk_transfer_grn_item_details());
		for(Stk_transfer_grn_item_details sItem_dtls : itemSet)
		{
			sItem_dtls.setStk_grn_id(gen_sno);
			sItem_dtls.setStk_grn_no(sGrn.getStk_grn_no());
			if(sItem_dtls.getAdv_item_code().compareTo("")!=0 && sItem_dtls.getAdv_item_code().compareTo("0")!=0) {
				sItem_dtls.setAdv_item_name(item_masterRepository.itemNameById(sItem_dtls.getAdv_item_code()).getItem_name());
			}
			if(sItem_dtls.getAdv_packing().compareTo("")!=0 && sItem_dtls.getAdv_packing().compareTo("0")!=0) {
				sItem_dtls.setAdv_packing_name(item_masterRepository.itemNameById(sItem_dtls.getAdv_packing()).getItem_name());
			}
			if(Utility.isNullOrEmpty(sItem_dtls.getWarehouse())) {
				if(sItem_dtls.getWarehouse().compareTo("")!=0 && sItem_dtls.getWarehouse().compareTo("0")!=0) {
				sItem_dtls.setWarehouse_name(warehouseMasterRepository.getWarehouseDetails(sItem_dtls.getWarehouse()).getWarehouse_name());
				}
			}
			else {
				sItem_dtls.setWarehouse("0");
				sItem_dtls.setWarehouse_name("NA");
			}
			
			if(Utility.isNullOrEmpty(sItem_dtls.getRack())) {
				if(sItem_dtls.getRack().compareTo("")!=0 && sItem_dtls.getRack().compareTo("0")!=0) {
				sItem_dtls.setRack_name(binMasterRepository.getBinDesc(sItem_dtls.getRack()).getBin_description());
				}
			}
			else {
				sItem_dtls.setRack("0");
				sItem_dtls.setRack_name("NA");
			}
			
			sItem_dtls.setCompany_id(sGrn.getCompany_id());
			sItem_dtls.setFin_year(sGrn.getFin_year());
			sItem_dtls.setModified_type("INSERTED");
			sItem_dtls.setInserted_by(sGrn.getInserted_by());
			sItem_dtls.setInserted_on(sGrn.getInserted_on());
			sItem_dtls.setUpdated_by("NA");
			sItem_dtls.setUpdated_on(ldt);
			sItem_dtls.setDeleted_by("NA");
			sItem_dtls.setDeleted_on(ldt);
		}
		sGrn.setStk_transfer_grn_item_details(itemSet);
		
		
		//Static
		Set<Stk_transfer_grn_trans_info> sTrns_infos=new HashSet<Stk_transfer_grn_trans_info>();
		sTrns_infos.add(sGrn.getStk_transfer_grn_trans_info());
		for(Stk_transfer_grn_trans_info sInfo:sTrns_infos) 
		{
			sInfo.setStk_grn_id(gen_sno);	
			sInfo.setStk_grn_no(sGrn.getStk_grn_no());
			sInfo.setCompany_id(sGrn.getCompany_id());
			sInfo.setFin_year(sGrn.getFin_year());
			sInfo.setModified_type("INSERTED");
			sInfo.setInserted_by(sGrn.getInserted_by());
			sInfo.setInserted_on(sGrn.getInserted_on());
			sInfo.setUpdated_by("NA");
			sInfo.setUpdated_on(ldt);
			sInfo.setDeleted_by("NA");
			sInfo.setDeleted_on(ldt);
		}
		if(!sTrns_infos.isEmpty())
		{
			sGrn.setStk_transfer_grn_trans_info(sTrns_infos.iterator().next());
		}

		//Static
		Set<Stk_transfer_grn_bu_dtls> sBu_dtls=new HashSet<Stk_transfer_grn_bu_dtls>();
		sBu_dtls.add(sGrn.getStk_transfer_grn_bu_dtls());
		for(Stk_transfer_grn_bu_dtls sGrn_bu_dtls:sBu_dtls) 
		{
			sGrn_bu_dtls.setStk_grn_id(gen_sno);	
			sGrn_bu_dtls.setStk_grn_no(sGrn.getStk_grn_no());
			sGrn_bu_dtls.setCompany_id(sGrn.getCompany_id());
			sGrn_bu_dtls.setFin_year(sGrn.getFin_year());
			sGrn_bu_dtls.setModified_type("INSERTED");
			sGrn_bu_dtls.setInserted_by(sGrn.getInserted_by());
			sGrn_bu_dtls.setInserted_on(sGrn.getInserted_on());
			sGrn_bu_dtls.setUpdated_by("NA");
			sGrn_bu_dtls.setUpdated_on(ldt);
			sGrn_bu_dtls.setDeleted_by("NA");
			sGrn_bu_dtls.setDeleted_on(ldt);
		}
		if(!sBu_dtls.isEmpty())
		{
			sGrn.setStk_transfer_grn_bu_dtls(sBu_dtls.iterator().next());
		}
		
		//Static
		Set<Stk_transfer_grn_other_info> sOther_infos=new HashSet<Stk_transfer_grn_other_info>();
		sOther_infos.add(sGrn.getStk_transfer_grn_other_info());
		for(Stk_transfer_grn_other_info sOther_info:sOther_infos) 
		{
			sOther_info.setStk_grn_id(gen_sno);	
			sOther_info.setStk_grn_no(sGrn.getStk_grn_no());
			sOther_info.setCompany_id(sGrn.getCompany_id());
			sOther_info.setFin_year(sGrn.getFin_year());
			sOther_info.setModified_type("INSERTED");
			sOther_info.setInserted_by(sGrn.getInserted_by());
			sOther_info.setInserted_on(sGrn.getInserted_on());
			sOther_info.setUpdated_by("NA");
			sOther_info.setUpdated_on(ldt);
			sOther_info.setDeleted_by("NA");
			sOther_info.setDeleted_on(ldt);
		}
		if(!sOther_infos.isEmpty())
		{
			sGrn.setStk_transfer_grn_other_info(sOther_infos.iterator().next());
		}
		
		//Dynamic
		Set<Stk_transfer_grn_resource_cost> sResource_costs = new HashSet<Stk_transfer_grn_resource_cost>();
		sResource_costs.addAll(sGrn.getStk_transfer_grn_resource_cost());
		for(Stk_transfer_grn_resource_cost sCost : sResource_costs)
		{
			sCost.setStk_grn_id(gen_sno);
			sCost.setStk_grn_no(sGrn.getStk_grn_no());
			sCost.setCompany_id(sGrn.getCompany_id());
			sCost.setFin_year(sGrn.getFin_year());
			sCost.setModified_type("INSERTED");
			sCost.setInserted_by(sGrn.getInserted_by());
			sCost.setInserted_on(sGrn.getInserted_on());
			sCost.setUpdated_by("NA");
			sCost.setUpdated_on(ldt);
			sCost.setDeleted_by("NA");
			sCost.setDeleted_on(ldt);
		
		}
		sGrn.setStk_transfer_grn_resource_cost(sResource_costs);
		
		//Dynamic
		Set<Stk_transfer_grn_docs> sGrn_docs = new HashSet<Stk_transfer_grn_docs>();
		sGrn_docs.addAll(sGrn.getStk_transfer_grn_docs());
		for(Stk_transfer_grn_docs sDocs : sGrn_docs)
		{
			sDocs.setStk_grn_id(gen_sno);
			sDocs.setStk_grn_no(sGrn.getStk_grn_no());
			sDocs.setCompany_id(sGrn.getCompany_id());
			sDocs.setFin_year(sGrn.getFin_year());
			sDocs.setModified_type("INSERTED");
			sDocs.setInserted_by(sGrn.getInserted_by());
			sDocs.setInserted_on(sGrn.getInserted_on());
			sDocs.setUpdated_by("NA");
			sDocs.setUpdated_on(ldt);
			sDocs.setDeleted_by("NA");
			sDocs.setDeleted_on(ldt);
		}
		sGrn.setStk_transfer_grn_docs(sGrn_docs);
		
		/*//Dynamic
		Set<Stk_transfer_grn_broker_details> sBroker_details = new HashSet<Stk_transfer_grn_broker_details>();
		sBroker_details.addAll(sGrn.getStk_transfer_grn_broker_details());
		for(Stk_transfer_grn_broker_details sBroker : sBroker_details)
		{
			sBroker.setStk_grn_id(gen_sno);
			sBroker.setStk_grn_no(sGrn.getStk_grn_no());
			sBroker.setCompany_id(sGrn.getCompany_id());
			sBroker.setFin_year(sGrn.getFin_year());
			sBroker.setModified_type("INSERTED");
			sBroker.setInserted_by(sGrn.getInserted_by());
			sBroker.setInserted_on(sGrn.getInserted_on());
			sBroker.setUpdated_by("NA");
			sBroker.setUpdated_on(ldt);
			sBroker.setDeleted_by("NA");
			sBroker.setDeleted_on(ldt);
		}
		sGrn.setStk_transfer_grn_broker_details(sBroker_details);*/
		
		return stk_transfer_grnRepository.save(sGrn);
	}
	
	public List<Stk_transfer_grn> getStkTranGRN(String company,String finyear){
		return stk_transfer_grnRepository.findAllStkTransGRN(company,finyear);
	}
	
	public List<Stk_transfer_grnDTO> getStkTranGrns(String bunit,String tdate,String company,String finyear)
	{
		List<Stk_transfer_grn> modifiedgrnDtls=new ArrayList<Stk_transfer_grn>();
		List<Stk_transfer_grn> grnDtls=new ArrayList<Stk_transfer_grn>();
		//grnDtls.addAll(stk_transfer_grnRepository.getStkTranGrns(bunit,tdate,company,finyear));
		grnDtls.addAll(stk_transfer_grnRepository.getStkTranGrnsNew(bunit,tdate,company,finyear));	
		
		for(int i=0;i<grnDtls.size();i++) 
		{
			
			if(grnDtls.get(i).getReference_status().compareToIgnoreCase("Stock Transfer") == 0 ) 
			{
				Wm_unload_advice challanid=wm_unload_adviceRepository.getUnloadAdviceData(grnDtls.get(i).getReference_id());
				
				//unload
				Stk_Transfer_Challan billreq=stockTransferChallanRepository.getStkOrderDetails(challanid.getReferance_id());
				
				
				if(billreq.getBilling_req().compareToIgnoreCase("Yes")==0) 
				{
					modifiedgrnDtls.add(grnDtls.get(i));
				}
			}
			else 
			{
				//direct challan
				Stk_Transfer_Challan billreq=stockTransferChallanRepository.getStkOrderDetails(grnDtls.get(i).getReference_id());
				
				if(billreq.getBilling_req().compareToIgnoreCase("Yes")==0) 
				{
					modifiedgrnDtls.add(grnDtls.get(i));
				}
			}
			
			
		}
		
		
		Type listType=new TypeToken<List<Stk_transfer_grnDTO>>() {}.getType();
		
		List<Stk_transfer_grnDTO> stkTrnsGrnDtls=new ModelMapper().map(modifiedgrnDtls,listType);
		
		return stkTrnsGrnDtls;
	}
	
	public Stk_transfer_grnDTO getStkTranGrnDtls(String stkgrnid)
	{
		Stk_transfer_grn grnDtls=stk_transfer_grnRepository.getStkTranGrnDtls(stkgrnid);
		Type listType=new TypeToken<Stk_transfer_grnDTO>() {}.getType();
		
		Stk_transfer_grnDTO stkTrnsGrnDtls=new ModelMapper().map(grnDtls,listType);
		
		return stkTrnsGrnDtls;
	}
	
	public Stk_transfer_grn findOne(long id){
		Optional<Stk_transfer_grn> op=this.stk_transfer_grnRepository.findById(id);
		return op.get();
	}
	
	public List<Stk_transfer_grn_item_detailsDTO> getStkTranGrnItemDlts(String stk_grn_id)
	{
		List<Stk_transfer_grn_item_details> itemDtls=new ArrayList<Stk_transfer_grn_item_details>();
		
		itemDtls.addAll(stk_transfer_grn_item_detailsRepository.getStk_transfer_grn_item_details(stk_grn_id));
			
		Type listType=new TypeToken<List<Stk_transfer_grn_item_detailsDTO>>() {}.getType();
		
		List<Stk_transfer_grn_item_detailsDTO> stkTrnsItmDtls=new ModelMapper().map(itemDtls,listType);
		
		return stkTrnsItmDtls;
	}
	
	public Stk_transfer_grn_trans_infoDTO getStkTranGrnTranInfo(String stk_grn_id){

		Stk_transfer_grn_trans_info modelList=stk_transfer_grn_trans_infoRepository.getStk_transfer_grn_trans_info(stk_grn_id);

		Type listType = new TypeToken<Stk_transfer_grn_trans_infoDTO>() {}.getType();

		Stk_transfer_grn_trans_infoDTO stkTrTrnsInfo= new ModelMapper().map(modelList,listType);
		return stkTrTrnsInfo;
	}
	
	public Stk_transfer_grn_bu_dtlsDTO getStkTranGrnBUDtls(String stk_grn_id){

		Stk_transfer_grn_bu_dtls modelList=stk_transfer_grn_bu_dtlsRepository.getStk_transfer_grn_bu_dtls(stk_grn_id);

		Type listType = new TypeToken<Stk_transfer_grn_bu_dtlsDTO>() {}.getType();

		Stk_transfer_grn_bu_dtlsDTO stkTrBU= new ModelMapper().map(modelList,listType);
		
		return stkTrBU;
	}
	
	public Stk_transfer_grn_other_infoDTO getStkTranGrnOthDtls(String stk_grn_id){

		Stk_transfer_grn_other_info modelList=stk_transfer_grn_other_infoRepository.getStk_transfer_grn_other_info(stk_grn_id);

		Type listType = new TypeToken<Stk_transfer_grn_other_infoDTO>() {}.getType();

		Stk_transfer_grn_other_infoDTO stkTrOthInfo= new ModelMapper().map(modelList,listType);
		
		return stkTrOthInfo;
	}
	
	public List<Stk_transfer_grn_resource_costDTO> getStkTranGrnReCostDlts(String stk_grn_id)
	{
		List<Stk_transfer_grn_resource_cost> costDtls=new ArrayList<Stk_transfer_grn_resource_cost>();
		
		costDtls.addAll(stk_transfer_grn_resource_costRepository.getStk_transfer_grn_resource_cost(stk_grn_id));
			
		Type listType=new TypeToken<List<Stk_transfer_grn_resource_costDTO>>() {}.getType();
		
		List<Stk_transfer_grn_resource_costDTO> stkTrnsCostDtls=new ModelMapper().map(costDtls,listType);
		
		return stkTrnsCostDtls;
	}
	
	public List<Stk_transfer_grn_docsDTO> getStkTranGrnDocs(String stk_grn_id)
	{
		List<Stk_transfer_grn_docs> docDtls=new ArrayList<Stk_transfer_grn_docs>();
		
		docDtls.addAll(stk_transfer_grn_docsRepository.getStk_transfer_grn_docs(stk_grn_id));
			
		Type listType=new TypeToken<List<Stk_transfer_grn_docsDTO>>() {}.getType();
		
		List<Stk_transfer_grn_docsDTO> stkTrnsDocDtls=new ModelMapper().map(docDtls,listType);
		
		return stkTrnsDocDtls;
	}
	
	public List<Vehicle_weighment_load_unload> getVehiclesFromVehicleLoadUnload() {
		
		List<Vehicle_weighment_load_unload> vehiList=new ArrayList<Vehicle_weighment_load_unload>();
		vehiList.addAll(vehicleMasterRepository.getVehiclesFromVehicleLoadUnload());
		
		return vehiList;
	}
	public Vehicle_weighment_load_unload getVehicleRefName(String vehicleid)
	{
		Vehicle_weighment_load_unload retypename=vehicleMasterRepository.getRefNameType(vehicleid);
		return retypename;
	}
	
	@Transactional
	public Stk_transfer_grn deleteStocktransferGRN(Stk_transfer_grn grn,long id) {

			Optional<Stk_transfer_grn> op = stk_transfer_grnRepository.findById(id);
			
			LocalDateTime ldt = LocalDateTime.now();
			Stk_transfer_grn sGrn=op.get();
			
			sGrn.setModified_type("DELETED");
			sGrn.setInserted_by(op.get().getInserted_by());
			sGrn.setInserted_on(op.get().getInserted_on());
			sGrn.setUpdated_by(op.get().getUpdated_by());
			sGrn.setUpdated_on(op.get().getUpdated_on());
			sGrn.setDeleted_by(userRepository.getUserDetails(grn.getUsername()).getName());
			sGrn.setDeleted_on(ldt);
			
			stk_transfer_grnRepository.updateStkTransferGrnItemDetails(op.get().getStk_grn_id());
			
			stk_transfer_grnRepository.updateStkTransferGrnTransportDetails(op.get().getStk_grn_id());
			
			stk_transfer_grnRepository.stkTransferGrnBuDtlsUpdate(op.get().getStk_grn_id());
			
			stk_transfer_grnRepository.stkTransferGrnOtherInfoUpdate(op.get().getStk_grn_id());
			
			stk_transfer_grnRepository.stkTransferGrnResourceCostUpdate(op.get().getStk_grn_id());
			
			stk_transfer_grnRepository.stkTransferGrnDocsUpdate(op.get().getStk_grn_id());
			
			if(op.get().getReference_status().compareToIgnoreCase("Stock Transfer")==0)
			{
				stk_transfer_grnRepository.vehicleLoadUnloadUpdate(op.get().getStk_grn_id(),op.get().getVehicle_id());
			}
			if(op.get().getReference_status().compareToIgnoreCase("Goods Stock Transfer")==0)
			{
				stk_transfer_grnRepository.stockGRNGoodsStatusUpdate(op.get().getStk_grn_id(),op.get().getVehicle_id());
			}
			
			return stk_transfer_grnRepository.save(sGrn);	
	}
	
	
	public StatusDTO checkStockGRNUsage(long id)
	 {
		StatusDTO result = new StatusDTO();
		
		Optional<Stk_transfer_grn> op = stk_transfer_grnRepository.findById(id);
		if(op.get().getReference_status().compareToIgnoreCase("Direct")==0)
		{
			result.setStatus("No");
		}
		else
		{
			if(stk_transfer_grnRepository.checkStockGRNUsage(op.get().getVehicle_id()))
			{
				result.setStatus("Yes");
			}
			else
			{
				result.setStatus("No");
			}
		}
		
		return result;
	 }
	
	public Stk_transfer_grnDTO stkPurInv(long id){
		
		Optional<Stk_transfer_pur_inv> op = stk_transfer_pur_invRepository.findById(id);
		
		Stk_transfer_grn stkGrn = stk_transfer_grnRepository.getStkTranGrnDtls(op.get().getReference_id());
		
		stkGrn.setReference_status(op.get().getStk_trans_pur_inv_id());
		
		Type listType=new TypeToken<Stk_transfer_grnDTO>() {}.getType();
		Stk_transfer_grnDTO purstk=new ModelMapper().map(stkGrn,listType);
		
		return purstk;
		
	}
	
	public Map<String,Object> getStkTransferGrnRestQty(String orderid,String item,String packing)
	{
		return stk_transfer_grnRepository.getStkTransferGrnRestQty(orderid,item,packing);
	}
	
	public List<Map<String,Object>> getStkTranGrnsFast(String comp,String fyear)
	{
		return stk_transfer_grnRepository.getStkTranGrnsFast(comp,fyear);
	}
	
	public Map<String,Object> getSalesInvFromStkTransGrn(String stk_grn_id)
	{
		return stk_transfer_grn_item_detailsRepository.getSalesInvFromStkTransGrn(stk_grn_id);
	}
	
}
