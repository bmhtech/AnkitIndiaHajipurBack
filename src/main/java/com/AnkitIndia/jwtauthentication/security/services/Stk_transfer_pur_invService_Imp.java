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
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_item_details;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_bu_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_docs;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_musterroll_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_tax_info;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_item_dtls;
import com.AnkitIndia.jwtauthentication.repository.BinMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_grnRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_pur_invRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.WarehouseMasterRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_pur_invDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_item_dtlsDTO;

@Service
public class Stk_transfer_pur_invService_Imp implements Stk_transfer_pur_invService {
	
	@Autowired
	Stk_transfer_pur_invRepository stk_transfer_pur_invRepository;
	
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
	Stk_transfer_grnRepository stk_transfer_grnRepository;
	
	public SequenceIdDTO getSTPISequenceId(String tdate,String bunit){
		int slno=0;String prefix="STPI";
		String sno=stk_transfer_pur_invRepository.getSTPISequenceId(tdate,bunit);
		
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
	public Stk_transfer_pur_inv save(Stk_transfer_pur_inv sPur_inv)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(stk_transfer_pur_invRepository.countId() != null ) {
			slno=Long.parseLong(stk_transfer_pur_invRepository.countId());
		}
		String prefix="STPI";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		sPur_inv.setStk_trans_pur_inv_id(gen_sno);
		
		if(sPur_inv.getBusiness_unit().compareTo("0") !=0 && sPur_inv.getBusiness_unit().compareTo("") !=0 && sPur_inv.getBusiness_unit() !=null) {
			sPur_inv.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(sPur_inv.getBusiness_unit()).getBusinessunit_name());
		}else {sPur_inv.setBusiness_unitname("None");}
		
		if(Utility.isNullOrEmpty(sPur_inv.getVehicle_id())) {
		
			if(sPur_inv.getVehicle_id().compareTo("0") !=0 && sPur_inv.getVehicle_id().compareTo("") !=0 && sPur_inv.getVehicle_id() !=null) {
				sPur_inv.setVehicle_no(vehicleMasterRepository.getVehicleDetails(sPur_inv.getVehicle_id()).getVehicle_no());
			}else {sPur_inv.setVehicle_no("None");}
		}
		else
		{
			sPur_inv.setVehicle_no("None");
		}
		
		System.out.println("ref"+sPur_inv.getReference_id());
		stk_transfer_grnRepository.updatePurGrnStatus(sPur_inv.getReference_id());
		
		sPur_inv.setModified_type("INSERTED");
		sPur_inv.setInserted_by(userRepository.getUserDetails(sPur_inv.getUsername()).getName());
		sPur_inv.setInserted_on(ldt);
		sPur_inv.setUpdated_by("NA");
		sPur_inv.setUpdated_on(ldt);
		sPur_inv.setDeleted_by("NA");
		sPur_inv.setDeleted_on(ldt);
			
		//Dynamic
		Set<Stk_transfer_pur_inv_item_dtls> itemSet = new HashSet<Stk_transfer_pur_inv_item_dtls>();
		itemSet.addAll(sPur_inv.getStk_transfer_pur_inv_item_dtls());
		for(Stk_transfer_pur_inv_item_dtls sItem_dtls : itemSet)
		{
			sItem_dtls.setStk_trans_pur_inv_id(gen_sno);
			sItem_dtls.setStk_trans_pur_inv_no(sPur_inv.getStk_trans_pur_inv_no());
			if(sItem_dtls.getAdv_item_code().compareTo("")!=0 && sItem_dtls.getAdv_item_code().compareTo("0")!=0) {
				sItem_dtls.setAdv_item_name(item_masterRepository.itemNameById(sItem_dtls.getAdv_item_code()).getItem_name());
			}
			if(sItem_dtls.getAdv_packing_item().compareTo("")!=0 && sItem_dtls.getAdv_packing_item().compareTo("0")!=0) {
				sItem_dtls.setAdv_packing_item_name(item_masterRepository.itemNameById(sItem_dtls.getAdv_packing_item()).getItem_name());
			}
			
			sItem_dtls.setCompany_id(sPur_inv.getCompany_id());
			sItem_dtls.setFin_year(sPur_inv.getFin_year());
			sItem_dtls.setModified_type("INSERTED");
			sItem_dtls.setInserted_by(sPur_inv.getInserted_by());
			sItem_dtls.setInserted_on(sPur_inv.getInserted_on());
			sItem_dtls.setUpdated_by("NA");
			sItem_dtls.setUpdated_on(ldt);
			sItem_dtls.setDeleted_by("NA");
			sItem_dtls.setDeleted_on(ldt);
		}
		sPur_inv.setStk_transfer_pur_inv_item_dtls(itemSet);
		
		//Dynamic
		Set<Stk_transfer_pur_inv_musterroll_dtls> sMusterroll_dtls = new HashSet<Stk_transfer_pur_inv_musterroll_dtls>();
		sMusterroll_dtls.addAll(sPur_inv.getStk_transfer_pur_inv_musterroll_dtls());
		for(Stk_transfer_pur_inv_musterroll_dtls sInv_mdtls : sMusterroll_dtls)
		{
			sInv_mdtls.setStk_trans_pur_inv_id(gen_sno);
			sInv_mdtls.setStk_trans_pur_inv_no(sPur_inv.getStk_trans_pur_inv_no());
			sInv_mdtls.setCompany_id(sPur_inv.getCompany_id());
			sInv_mdtls.setFin_year(sPur_inv.getFin_year());
			sInv_mdtls.setModified_type("INSERTED");
			sInv_mdtls.setInserted_by(sPur_inv.getInserted_by());
			sInv_mdtls.setInserted_on(sPur_inv.getInserted_on());
			sInv_mdtls.setUpdated_by("NA");
			sInv_mdtls.setUpdated_on(ldt);
			sInv_mdtls.setDeleted_by("NA");
			sInv_mdtls.setDeleted_on(ldt);
		}
		sPur_inv.setStk_transfer_pur_inv_musterroll_dtls(sMusterroll_dtls);
		
		//Static
		Set<Stk_transfer_pur_inv_tax_info> sTax_infos=new HashSet<Stk_transfer_pur_inv_tax_info>();
		sTax_infos.add(sPur_inv.getStk_transfer_pur_inv_tax_info());
		for(Stk_transfer_pur_inv_tax_info sInfo:sTax_infos) 
		{
			sInfo.setStk_trans_pur_inv_id(gen_sno);	
			sInfo.setStk_trans_pur_inv_no(sPur_inv.getStk_trans_pur_inv_no());
			sInfo.setCompany_id(sPur_inv.getCompany_id());
			sInfo.setFin_year(sPur_inv.getFin_year());
			sInfo.setModified_type("INSERTED");
			sInfo.setInserted_by(sPur_inv.getInserted_by());
			sInfo.setInserted_on(sPur_inv.getInserted_on());
			sInfo.setUpdated_by("NA");
			sInfo.setUpdated_on(ldt);
			sInfo.setDeleted_by("NA");
			sInfo.setDeleted_on(ldt);
		}
		if(!sTax_infos.isEmpty())
		{
			sPur_inv.setStk_transfer_pur_inv_tax_info(sTax_infos.iterator().next());
		}
		
		//Static
		Set<Stk_transfer_pur_inv_bu_dtls> sBu_dtls=new HashSet<Stk_transfer_pur_inv_bu_dtls>();
		sBu_dtls.add(sPur_inv.getStk_transfer_pur_inv_bu_dtls());
		for(Stk_transfer_pur_inv_bu_dtls sBu_dtl:sBu_dtls) 
		{
			sBu_dtl.setStk_trans_pur_inv_id(gen_sno);	
			sBu_dtl.setStk_trans_pur_inv_no(sPur_inv.getStk_trans_pur_inv_no());
			sBu_dtl.setCompany_id(sPur_inv.getCompany_id());
			sBu_dtl.setFin_year(sPur_inv.getFin_year());
			sBu_dtl.setModified_type("INSERTED");
			sBu_dtl.setInserted_by(sPur_inv.getInserted_by());
			sBu_dtl.setInserted_on(sPur_inv.getInserted_on());
			sBu_dtl.setUpdated_by("NA");
			sBu_dtl.setUpdated_on(ldt);
			sBu_dtl.setDeleted_by("NA");
			sBu_dtl.setDeleted_on(ldt);
		}
		if(!sTax_infos.isEmpty())
		{
			sPur_inv.setStk_transfer_pur_inv_bu_dtls(sBu_dtls.iterator().next());
		}
		
		//Dynamic
		Set<Stk_transfer_pur_inv_docs> sInv_docs = new HashSet<Stk_transfer_pur_inv_docs>();
		sInv_docs.addAll(sPur_inv.getStk_transfer_pur_inv_docs());
		for(Stk_transfer_pur_inv_docs sDocs : sInv_docs)
		{
			sDocs.setStk_trans_pur_inv_id(gen_sno);
			sDocs.setStk_trans_pur_inv_no(sPur_inv.getStk_trans_pur_inv_no());
			sDocs.setCompany_id(sPur_inv.getCompany_id());
			sDocs.setFin_year(sPur_inv.getFin_year());
			sDocs.setModified_type("INSERTED");
			sDocs.setInserted_by(sPur_inv.getInserted_by());
			sDocs.setInserted_on(sPur_inv.getInserted_on());
			sDocs.setUpdated_by("NA");
			sDocs.setUpdated_on(ldt);
			sDocs.setDeleted_by("NA");
			sDocs.setDeleted_on(ldt);
		}
		sPur_inv.setStk_transfer_pur_inv_docs(sInv_docs);
		
		return stk_transfer_pur_invRepository.save(sPur_inv);
	}
	
	public List<Stk_transfer_pur_invDTO> getStkTranPurInvs(String company,String finyear)
	{
		List<Stk_transfer_pur_inv> grnDtls=new ArrayList<Stk_transfer_pur_inv>();
		grnDtls.addAll(stk_transfer_pur_invRepository.getStkTranPurInvs(company,finyear));
			
		Type listType=new TypeToken<List<Stk_transfer_pur_invDTO>>() {}.getType();
		
		List<Stk_transfer_pur_invDTO> stkTrnsGrnDtls=new ModelMapper().map(grnDtls,listType);
		
		return stkTrnsGrnDtls;
	}

	public Stk_transfer_pur_inv findOne(long id){
		Optional<Stk_transfer_pur_inv> op=this.stk_transfer_pur_invRepository.findById(id);
		return op.get();
	}
	
	public List<Stk_transfer_pur_inv_item_dtls> stkPurInvItemRetriveList(String stk_trans_pur_inv_id)
	{
		List<Stk_transfer_pur_inv_item_dtls> itemDtls=new ArrayList<Stk_transfer_pur_inv_item_dtls>();
		itemDtls.addAll(stk_transfer_pur_invRepository.stkPurInvItemRetriveList(stk_trans_pur_inv_id));
		
		return itemDtls;
	}
	
	public List<Stk_transfer_pur_inv_musterroll_dtls> stkPurInvMusterRollRetriveList(String stk_trans_pur_inv_id)
	{
		List<Stk_transfer_pur_inv_musterroll_dtls> itemDtls=new ArrayList<Stk_transfer_pur_inv_musterroll_dtls>();
		itemDtls.addAll(stk_transfer_pur_invRepository.stkPurInvMusterRollRetriveList(stk_trans_pur_inv_id));
		
		return itemDtls;
	}
	
	public List<Stk_transfer_pur_inv_docs> stkPurInvDocsRetriveList(String stk_trans_pur_inv_id)
	{
		List<Stk_transfer_pur_inv_docs> itemDtls=new ArrayList<Stk_transfer_pur_inv_docs>();
		itemDtls.addAll(stk_transfer_pur_invRepository.stkPurInvDocsRetriveList(stk_trans_pur_inv_id));
		
		return itemDtls;
	}
	
	public Stk_transfer_pur_inv_tax_info stkPurInvTaxInfoRetriveList(String stk_trans_pur_inv_id)
	{
		Stk_transfer_pur_inv_tax_info itemDtls=stk_transfer_pur_invRepository.stkPurInvTaxInfoRetriveList(stk_trans_pur_inv_id);
		
		return itemDtls;
	}
	
	public Stk_transfer_pur_inv_bu_dtls stkPurInvBuRetriveList(String stk_trans_pur_inv_id)
	{
		Stk_transfer_pur_inv_bu_dtls itemDtls=stk_transfer_pur_invRepository.stkPurInvBuRetriveList(stk_trans_pur_inv_id);
		
		return itemDtls;
	}
	
	@Transactional
	public Stk_transfer_pur_inv deleteStocktransferPurInv(Stk_transfer_pur_inv purinv,long id) {

			Optional<Stk_transfer_pur_inv> op = stk_transfer_pur_invRepository.findById(id);
			
			LocalDateTime ldt = LocalDateTime.now();
			Stk_transfer_pur_inv sPurInv=op.get();
			
			sPurInv.setModified_type("DELETED");
			sPurInv.setInserted_by(op.get().getInserted_by());
			sPurInv.setInserted_on(op.get().getInserted_on());
			sPurInv.setUpdated_by(op.get().getUpdated_by());
			sPurInv.setUpdated_on(op.get().getUpdated_on());
			sPurInv.setDeleted_by(userRepository.getUserDetails(sPurInv.getUsername()).getName());
			sPurInv.setDeleted_on(ldt);
			
			stk_transfer_pur_invRepository.updateStkTransferPurInvItemDetails(op.get().getStk_trans_pur_inv_id());
			
			stk_transfer_pur_invRepository.updateStkTransferPurInvMusterrollDetails(op.get().getStk_trans_pur_inv_id());
			
			stk_transfer_pur_invRepository.stkTransferPurInvTaxUpdate(op.get().getStk_trans_pur_inv_id());
			
			stk_transfer_pur_invRepository.stkTransferPurInvBuUpdate(op.get().getStk_trans_pur_inv_id());
			
			stk_transfer_pur_invRepository.stkTransferPurInvDocsUpdate(op.get().getStk_trans_pur_inv_id());
			
			stk_transfer_pur_invRepository.grnPurchaseStatusUpdate(op.get().getReference_id());
			
		  return stk_transfer_pur_invRepository.save(sPurInv);	
	}
	
	public List<Stk_transfer_pur_inv_item_dtls> getStkTransPurInvItemDtls(String stk_pur_inv_id)
	{
		List<Stk_transfer_pur_inv_item_dtls> itemDtls=new ArrayList<Stk_transfer_pur_inv_item_dtls>();
		
		itemDtls.addAll(stk_transfer_pur_invRepository.stkPurInvItemRetriveList(stk_pur_inv_id));
			
		
		return itemDtls;
	}
	
}
