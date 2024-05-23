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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Item_maintain;
import com.AnkitIndia.jwtauthentication.model.Item_master_pack_mat_tag;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_Business_Partner_details;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_broker;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_doc;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_driver_dtls;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_item_details;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_resource_cost;
import com.AnkitIndia.jwtauthentication.model.Pur_good_reciept_trans_info;
import com.AnkitIndia.jwtauthentication.model.Pur_goods_receipt_other_information;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan;
import com.AnkitIndia.jwtauthentication.model.Stock_maintain;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_type_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receiptRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receipt_Business_Partner_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receipt_brokerRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receipt_docRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receipt_driver_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receipt_item_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_receipt_resource_costRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_good_reciept_trans_infoRepository;
import com.AnkitIndia.jwtauthentication.repository.Pur_goods_receipt_other_informationRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_maintainRepository;
import com.AnkitIndia.jwtauthentication.repository.Supp_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Tax_code_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receiptDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_Business_Partner_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_brokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_resource_costDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_reciept_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_goods_receipt_other_informationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_OrderDTO;

@Service
public class Pur_good_receiptService_Imp implements Pur_good_receiptService{

	@Autowired
	Pur_good_receiptRepository pur_good_receiptRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Supp_bussiness_partnerRepository suppBussiness_partnerRepository;
	
	@Autowired
	Item_type_masterRepository item_type_masterRepository;
	
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Stock_maintainRepository stock_maintainRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	@Autowired
	Pur_OrderRepository pur_OrderRepository;
	
	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	Pur_good_receipt_driver_dtlsRepository pur_good_receipt_driver_dtlsRepository;
	
	@Autowired
	Tax_code_detailsRepository tax_code_detailsRepository;
	
	public SequenceIdDTO getGRNSequenceId(String bunit,boolean itype,String ptype,String psubtype,String orderdate)
	{
		int slno=0;String prefix="GR",itypep="",buprefix="";
		//String dt=Utility.convertDate(orderdate);
		String sno=pur_good_receiptRepository.countGRNOrder(bunit,itype,ptype,psubtype,orderdate);
		//RAW ITMT00001
		//PAC ITMT00002
		//FIN ITMT00003
		//SCR ITMFIXED02
		//STR ITMT00004
		//AST ITMT00005
		//JOB ITMT00009
		String newprefix="";
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
		else if(ptype.compareToIgnoreCase("ITMT00007") == 0)
		{
			newprefix="CWI";
		}
		else if(ptype.compareToIgnoreCase("ITMT00008") == 0)
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
		
		if(itype == true)
		{
			itypep="MAT";
			}
		else {
			itypep="SER";}
		
		
		buprefix=companyBusinessUnitMasterRepository.CompanyBUAddress(bunit).getBusinessunit_name();
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		prefix=prefix+"-"+buprefix.substring(0,3)+"-"+newprefix+"-"+psubtype.substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		//psubtype
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	
	

	
	@Transactional
	public Pur_good_receipt save(Pur_good_receipt pur_good_receipt)
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;String prefix="GRN";
		if(pur_good_receiptRepository.countId() !=null)
		{
			slno=Long.parseLong(pur_good_receiptRepository.countId());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		/*Start checking transaction no for unique */
		System.err.println("First:>>"+pur_good_receipt.getGrn_no());
		long nslno=0;String tprefix="GR",itypep="",buprefix="";
		String tsno=pur_good_receiptRepository.countGRNOrder(pur_good_receipt.getB_unit(),pur_good_receipt.isItem_type(),pur_good_receipt.getPurchase_type(),pur_good_receipt.getPurchase_subtype(),pur_good_receipt.getGrn_date());
		
	
				String newprefix="";
				wm_unload_adviceRepository.getrevert(pur_good_receipt.getReferance_id());
				if(pur_good_receipt.getPurchase_type().compareTo("ITMT00001")==0) 
				{
					newprefix="RAW";
				}
				else if(pur_good_receipt.getPurchase_type().compareTo("ITMT00010")==0) 
				{
					newprefix="TRS";
				}
				else if(pur_good_receipt.getPurchase_type().compareTo("ITMT00002")==0) 
				{
					newprefix="PAC";
					
				}
				else if(pur_good_receipt.getPurchase_type().compareTo("ITMT00003")==0) 
				{
					newprefix="FIN";
				}
				else if(pur_good_receipt.getPurchase_type().compareTo("ITMFIXED02")==0) 
				{
					newprefix="SCR";
				}
				else if(pur_good_receipt.getPurchase_type().compareTo("ITMT00004")==0) 
				{
					newprefix="STR";
					/*int restqty=0;
					restqty=Integer.parseInt(pur_good_receiptRepository.getrestqty(pur_good_receipt.getReferance_id()));
					if(restqty==0)
					{*/
						pur_good_receiptRepository.getrevert(pur_good_receipt.getReferance_id());
					//}
					
				}
				else if(pur_good_receipt.getPurchase_type().compareTo("ITMT00005")==0) 
				{
					newprefix="AST";
					pur_good_receiptRepository.getrevert(pur_good_receipt.getReferance_id());
				}
				else if(pur_good_receipt.getPurchase_type().compareTo("ITMT00007")==0)
				{
					newprefix="CWI";
					pur_good_receiptRepository.getrevert(pur_good_receipt.getReferance_id());
					
				}
				else if(pur_good_receipt.getPurchase_type().compareTo("ITMT00008")==0)
				{
					newprefix="PRW";
				}
				else if(pur_good_receipt.getPurchase_type().compareToIgnoreCase("ITMT00009")==0) 
				{
					newprefix="JOB";
				}
				
				
		
		
		if(pur_good_receipt.isItem_type() == true) {itypep="MAT";}else {itypep="SER";}
		buprefix=companyBusinessUnitMasterRepository.CompanyBUAddress(pur_good_receipt.getB_unit()).getBusinessunit_name();
		
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = pur_good_receipt.getGrn_date().split("-");
		tprefix=tprefix+"-"+buprefix.substring(0,3)+"-"+newprefix+"-"+pur_good_receipt.getPurchase_subtype().substring(0, 1)+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		pur_good_receipt.setGrn_no(gen_tslno);
		System.err.println("Last:>>>"+pur_good_receipt.getGrn_no());
		/*End checking transaction no for unique */
		
		pur_good_receipt.setGrn_status("Open");
		pur_good_receipt.setGrn_id(gen_sno);
		pur_good_receipt.setPur_return_status("No");
		
		pur_good_receipt.setPurreturnid("NA");	
		pur_good_receipt.setModified_type("INSERTED");
		pur_good_receipt.setInserted_by(userRepository.getUserDetails(pur_good_receipt.getUsername()).getName());
		pur_good_receipt.setInserted_on(ldt);
		pur_good_receipt.setUpdated_by("NA");
		pur_good_receipt.setUpdated_on(ldt);
		pur_good_receipt.setDeleted_by("NA");
		pur_good_receipt.setDeleted_on(ldt);
		pur_good_receipt.setGrndate(pur_good_receipt.getGrn_date());
		pur_good_receipt.setGrnno(pur_good_receipt.getGrn_no());
		
		
		//bill_status
		pur_good_receipt.setBill_status("0");
		if(Utility.isNullOrEmpty(pur_good_receipt.getSupplier_name())) {
			pur_good_receipt.setSupplier(suppBussiness_partnerRepository.getSupplierName(pur_good_receipt.getSupplier_name()).getBp_name());
		}else {pur_good_receipt.setSupplier("None");}
		
		if(Utility.isNullOrEmpty(pur_good_receipt.getPurchase_type())) {
			pur_good_receipt.setPurchase_typename(item_type_masterRepository.getItemType(pur_good_receipt.getPurchase_type()).getItem_name());
		}else {pur_good_receipt.setPurchase_typename("None");}
		
		if(Utility.isNullOrEmpty(pur_good_receipt.getB_unit())) {
			pur_good_receipt.setB_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pur_good_receipt.getB_unit()).getBusinessunit_name());
		}else {pur_good_receipt.setB_unitname("None");}
		
		if(Utility.isNullOrEmpty(pur_good_receipt.getVehicle_id())) {
			pur_good_receipt.setVehicle_no(vehicleMasterRepository.getVehicleDetails(pur_good_receipt.getVehicle_id()).getVehicle_no());
		}else {pur_good_receipt.setVehicle_no("None");}
		
		
		Set<Pur_good_receipt_Business_Partner_details> BGRBDetailsSet = new HashSet<Pur_good_receipt_Business_Partner_details>();
		BGRBDetailsSet.add(pur_good_receipt.getPur_good_receipt_Business_Partner_details());
		for(Pur_good_receipt_Business_Partner_details Pur_good_receiptBPD : BGRBDetailsSet)
		{
			Pur_good_receiptBPD.setGrn_id(gen_sno);
			Pur_good_receiptBPD.setGrn_no(pur_good_receipt.getGrn_no());
			Pur_good_receiptBPD.setFin_year(pur_good_receipt.getFin_year());
			Pur_good_receiptBPD.setCompany_id(pur_good_receipt.getCompany_id());
			Pur_good_receiptBPD.setModified_type("INSERTED");
			Pur_good_receiptBPD.setInserted_by(pur_good_receipt.getInserted_by());
			Pur_good_receiptBPD.setInserted_on(pur_good_receipt.getInserted_on());
			Pur_good_receiptBPD.setUpdated_by("NA");
			Pur_good_receiptBPD.setUpdated_on(ldt);
			Pur_good_receiptBPD.setDeleted_by("NA");
			Pur_good_receiptBPD.setDeleted_on(ldt);
		}
		if(!BGRBDetailsSet.isEmpty()) {
			pur_good_receipt.setPur_good_receipt_Business_Partner_details(BGRBDetailsSet.iterator().next());
		}
		
		Set<Pur_good_receipt_item_details> pgrItemSet=new HashSet<Pur_good_receipt_item_details>();
		pgrItemSet.addAll(pur_good_receipt.getPur_good_receipt_item_details());
		for(Pur_good_receipt_item_details pgrItemDtls:pgrItemSet)
		{
			pgrItemDtls.setGrn_id(gen_sno);
			pgrItemDtls.setGrn_no(pur_good_receipt.getGrn_no());
			pgrItemDtls.setAdv_item_name(item_masterRepository.itemNameById(pgrItemDtls.getAdv_item_code()).getItem_name());
			if(Utility.isNullOrEmpty(pgrItemDtls.getAdv_packing())) {
				pgrItemDtls.setAdv_packing_name(item_masterRepository.itemNameById(pgrItemDtls.getAdv_packing()).getItem_name());
			}
			pgrItemDtls.setFin_year(pur_good_receipt.getFin_year());
			pgrItemDtls.setCompany_id(pur_good_receipt.getCompany_id());
			pgrItemDtls.setModified_type("INSERTED");
			pgrItemDtls.setInserted_by(pur_good_receipt.getInserted_by());
			pgrItemDtls.setInserted_on(pur_good_receipt.getInserted_on());
			pgrItemDtls.setUpdated_by("NA");
			pgrItemDtls.setUpdated_on(ldt);
			pgrItemDtls.setDeleted_by("NA");
			pgrItemDtls.setDeleted_on(ldt);
		}
		pur_good_receipt.setPur_good_receipt_item_details(pgrItemSet);
		
		/*//For Stock_maintain
		String advitemcode="",advitemqty="",advpackcode="",advpackqty="";
		
		Set<Stock_maintain> smSet = new HashSet<Stock_maintain>();
		if(Utility.isNullOrEmpty(pur_good_receipt.getB_unit()) && pur_good_receipt.getReferance_type().compareToIgnoreCase("Open GRN")==0) {
			for(Pur_good_receipt_item_details grnDtls:pgrItemSet)
			{
				advitemcode +=grnDtls.getAdv_item_code()+",";
				advitemqty +=grnDtls.getPssd_item_qty()+",";
				advpackcode +=grnDtls.getAdv_packing()+",";
				advpackqty +=grnDtls.getPssd_pack_qty()+",";
				
				if(grnDtls.getAdv_item_code().compareTo("")!=0 && grnDtls.getAdv_item_code().compareTo("0")!=0) {
					Stock_maintain iMaintain=new Stock_maintain(pur_good_receipt.getB_unit(),grnDtls.getWarehouse_name(),grnDtls.getRack(),grnDtls.getAdv_item_code(),
							grnDtls.getAdv_packing(),Double.parseDouble(item_masterRepository.itemNameById(grnDtls.getAdv_item_code()).getStandard_rate()),
							item_masterRepository.itemNameById(grnDtls.getAdv_item_code()).getMrp(), pur_good_receipt.getPurchase_type(),
							item_masterRepository.itemNameById(grnDtls.getAdv_item_code()).getItem_group(),item_masterRepository.itemNameById(grnDtls.getAdv_item_code()).getItem_category(),
							pur_good_receipt.getFin_year(),pur_good_receipt.getCompany_id(),pur_good_receipt.getUsername(),"INSERTED",ldt,pur_good_receipt.getInserted_by(),ldt,pur_good_receipt.getUpdated_by(),
							grnDtls.getAdv_pack_qty(),grnDtls.getAdv_item_qty(),grnDtls.getRcv_pack_qty(),grnDtls.getRcv_item_qty(),
							grnDtls.getPssd_pack_qty(),grnDtls.getPssd_item_qty());
					
					smSet.add(iMaintain);
				}
			}
			advitemcode=advitemcode.substring(0,(advitemcode.length()-1));
			advitemqty=advitemqty.substring(0,(advitemqty.length()-1));
			advpackcode=advpackcode.substring(0,(advpackcode.length()-1));
			advpackqty=advpackqty.substring(0,(advpackqty.length()-1));
			
			System.err.println("Got Final Val: "+advitemqty+" , "+advpackqty+" , "+advitemcode+" , "+advpackcode+" , "+pur_good_receipt.getB_unit()+" , "+pur_good_receipt.getFin_year());
			int s=pur_good_receiptRepository.callItemStockMaintain(advitemqty,advpackqty,advitemcode,advpackcode,pur_good_receipt.getB_unit(),pur_good_receipt.getFin_year());
		}
		stock_maintainRepository.saveAll(smSet);*/
	
		Set<Pur_good_receipt_driver_dtls> PGRDetailsSet = new HashSet<Pur_good_receipt_driver_dtls>();
		PGRDetailsSet.add(pur_good_receipt.getPur_good_receipt_driver_dtls());
		for(Pur_good_receipt_driver_dtls Pur_good_recieptDI : PGRDetailsSet)
		{
			Pur_good_recieptDI.setGrn_id(gen_sno);
			Pur_good_recieptDI.setGrn_no(pur_good_receipt.getGrn_no());
			Pur_good_recieptDI.setFin_year(pur_good_receipt.getFin_year());
						
			Pur_good_recieptDI.setCompany_id(pur_good_receipt.getCompany_id());
			Pur_good_recieptDI.setModified_type("INSERTED");
			Pur_good_recieptDI.setInserted_by(pur_good_receipt.getInserted_by());
			Pur_good_recieptDI.setInserted_on(pur_good_receipt.getInserted_on());
			Pur_good_recieptDI.setUpdated_by("NA");
			Pur_good_recieptDI.setUpdated_on(ldt);
			Pur_good_recieptDI.setDeleted_by("NA");
			Pur_good_recieptDI.setDeleted_on(ldt);
		}
		if(!PGRDetailsSet.isEmpty())
		{
			pur_good_receipt.setPur_good_receipt_driver_dtls(PGRDetailsSet.iterator().next());
		}
	
		
		Set<Pur_good_reciept_trans_info> PGRTetailsSet = new HashSet<Pur_good_reciept_trans_info>();
		PGRTetailsSet.add(pur_good_receipt.getPur_good_reciept_trans_info());
		for(Pur_good_reciept_trans_info Pur_good_recieptTI : PGRTetailsSet)
		{
			Pur_good_recieptTI.setGrn_id(gen_sno);
			Pur_good_recieptTI.setGrn_no(pur_good_receipt.getGrn_no());
			Pur_good_recieptTI.setFin_year(pur_good_receipt.getFin_year());
			Pur_good_recieptTI.setCompany_id(pur_good_receipt.getCompany_id());
			Pur_good_recieptTI.setModified_type("INSERTED");
			Pur_good_recieptTI.setInserted_by(pur_good_receipt.getInserted_by());
			Pur_good_recieptTI.setInserted_on(pur_good_receipt.getInserted_on());
			Pur_good_recieptTI.setUpdated_by("NA");
			Pur_good_recieptTI.setUpdated_on(ldt);
			Pur_good_recieptTI.setDeleted_by("NA");
			Pur_good_recieptTI.setDeleted_on(ldt);
		}
		if(!PGRTetailsSet.isEmpty())
		{
			pur_good_receipt.setPur_good_reciept_trans_info(PGRTetailsSet.iterator().next());
		}
		
		Set<Pur_goods_receipt_other_information> PGROetailsSet = new HashSet<Pur_goods_receipt_other_information>();
		PGROetailsSet.add(pur_good_receipt.getPur_goods_receipt_other_information());
		for(Pur_goods_receipt_other_information Pur_goods_receiptOI : PGROetailsSet)
		{
			Pur_goods_receiptOI.setGrn_id(gen_sno);
			Pur_goods_receiptOI.setGrn_no(pur_good_receipt.getGrn_no());
			Pur_goods_receiptOI.setFin_year(pur_good_receipt.getFin_year());
			Pur_goods_receiptOI.setCompany_id(pur_good_receipt.getCompany_id());
			Pur_goods_receiptOI.setModified_type("INSERTED");
			Pur_goods_receiptOI.setInserted_by(pur_good_receipt.getInserted_by());
			Pur_goods_receiptOI.setInserted_on(pur_good_receipt.getInserted_on());
			Pur_goods_receiptOI.setUpdated_by("NA");
			Pur_goods_receiptOI.setUpdated_on(ldt);
			Pur_goods_receiptOI.setDeleted_by("NA");
			Pur_goods_receiptOI.setDeleted_on(ldt);
		}
		if(!PGROetailsSet.isEmpty())
		{
			pur_good_receipt.setPur_goods_receipt_other_information(PGROetailsSet.iterator().next());
		}
		
		Set<Pur_good_receipt_resource_cost> PGRRC=new HashSet<Pur_good_receipt_resource_cost>();
		PGRRC.addAll(pur_good_receipt.getPur_good_receipt_resource_cost());
		for(Pur_good_receipt_resource_cost aby:PGRRC)
		{
			aby.setGrn_id(gen_sno);
			aby.setGrn_no(pur_good_receipt.getGrn_no());
			aby.setFin_year(pur_good_receipt.getFin_year());
			aby.setCompany_id(pur_good_receipt.getCompany_id());
			aby.setModified_type("INSERTED");
			aby.setInserted_by(pur_good_receipt.getInserted_by());
			aby.setInserted_on(pur_good_receipt.getInserted_on());
			aby.setUpdated_by("NA");
			aby.setUpdated_on(ldt);
			aby.setDeleted_by("NA");
			aby.setDeleted_on(ldt);
		}
		pur_good_receipt.setPur_good_receipt_resource_cost(PGRRC);
		
		Set<Pur_good_receipt_doc> docSet=new HashSet<Pur_good_receipt_doc>();
		docSet.addAll(pur_good_receipt.getPur_good_receipt_docs());
		for(Pur_good_receipt_doc pgrd:docSet)
		{
			pgrd.setGrn_id(gen_sno);
			pgrd.setGrn_no(pur_good_receipt.getGrn_no());
			pgrd.setFin_year(pur_good_receipt.getFin_year());
			pgrd.setCompany_id(pur_good_receipt.getCompany_id());
			pgrd.setModified_type("INSERTED");
			pgrd.setInserted_by(pur_good_receipt.getInserted_by());
			pgrd.setInserted_on(pur_good_receipt.getInserted_on());
			pgrd.setUpdated_by("NA");
			pgrd.setUpdated_on(ldt);
			pgrd.setDeleted_by("NA");
			pgrd.setDeleted_on(ldt);
		}
		pur_good_receipt.setPur_good_receipt_docs(docSet);
	
		Set<Pur_good_receipt_broker> brokerSet=new HashSet<Pur_good_receipt_broker>();
		brokerSet.addAll(pur_good_receipt.getPur_good_receipt_broker());
		for(Pur_good_receipt_broker pgrd:brokerSet)
		{
			pgrd.setGrn_id(gen_sno);
			pgrd.setGrn_no(pur_good_receipt.getGrn_no());
			if(Utility.isNullOrEmpty(pgrd.getVen_code_name())) {
			pgrd.setVen_name(broker_masterRepository.brokerNameByCode(pgrd.getVen_code_name()).getName());
			}
			else 
			{
				pgrd.setVen_name("");
			}
			pgrd.setFin_year(pur_good_receipt.getFin_year());
			pgrd.setCompany_id(pur_good_receipt.getCompany_id());
			pgrd.setModified_type("INSERTED");
			pgrd.setInserted_by(pur_good_receipt.getInserted_by());
			pgrd.setInserted_on(pur_good_receipt.getInserted_on());
			pgrd.setUpdated_by("NA");
			pgrd.setUpdated_on(ldt);
			pgrd.setDeleted_by("NA");
			pgrd.setDeleted_on(ldt);
		}
		pur_good_receipt.setPur_good_receipt_broker(brokerSet);
		
		return pur_good_receiptRepository.save(pur_good_receipt);
	}
	
	public List<Pur_good_receipt> findAll()
	{
		List<Pur_good_receipt> grnList=new ArrayList<Pur_good_receipt>();
		grnList.addAll(pur_good_receiptRepository.findGoodReceipts());
		for(int x=0;x<grnList.size();x++) {
			if(grnList.get(x).getSupplier_name()!=null && grnList.get(x).getSupplier_name().compareTo("")!=0 && grnList.get(x).getSupplier_name().compareTo("0")!=0) {
				grnList.get(x).setSupplier_name(suppBussiness_partnerRepository.getSupplierName(grnList.get(x).getSupplier_name()).getBp_name());
			}
			else {
				grnList.get(x).setSupplier_name("None");
			}
		}
		return grnList;
	}
	
	public Page<Pur_good_receipt_Pagination_DTO> getPurGRNPagination(int page,int size)
	  {
		  
			//PageRequest pageRequest = PageRequest.of(page, size,Sort.by("id").descending());
			PageRequest pageRequest = PageRequest.of(page, size,Sort.by("grndate").descending().and(Sort.by("grnno")).descending());
		    Page<Pur_good_receipt> pageResult = pur_good_receiptRepository.findAll(pageRequest);
		   
		    List<Pur_good_receipt_Pagination_DTO> advList = pageResult
		    	      .stream()
		    	      .map((Pur_good_receipt pur_good_receipt) -> new Pur_good_receipt_Pagination_DTO(pur_good_receipt.getId(),
		    	    		  pur_good_receipt.getGrn_id(),
		    	    		  pur_good_receipt.getGrn_no(),
		    	    		  pur_good_receipt.getGrn_date(),
		    	    		  pur_good_receipt.getB_unitname(),
		    	    		  pur_good_receipt.getSupplier_name(),
		    	    		  pur_good_receipt.getPurchase_typename(),
		    	    		  pur_good_receipt.getReferance_type(),
		    	    		  pur_good_receipt.getVehicle_no(),
		    	    		  pur_good_receipt.getModified_type()
		    	    		)).filter(c -> c.getModified_type().equals("INSERTED")).collect(Collectors.toList());
		    	      
				    for(int x=0;x<advList.size();x++) {
						if(advList.get(x).getSupplier_name()!=null && advList.get(x).getSupplier_name().compareTo("")!=0 && advList.get(x).getSupplier_name().compareTo("0")!=0) {
							advList.get(x).setSupplier_name(suppBussiness_partnerRepository.getSupplierName(advList.get(x).getSupplier_name()).getBp_name());
						}
						else {
							advList.get(x).setSupplier_name("None");
						}
			}
		    
		    return new PageImpl<>(advList, pageRequest, pageResult.getTotalElements());
	  }
	
	public Pur_good_receipt_item_details gettaxcodefromgrn(String itemcode,String grnid) 
	{
		Pur_good_receipt_item_details itemdetails=pur_good_receipt_item_detailsRepository.getPurGoodRcptItemDtlsListTAX(grnid,itemcode);
		System.out.println("tax code "+itemdetails.getTax_code());
		
		itemdetails.setTax_code(tax_code_detailsRepository.getTaxCodeDetailstax_name(itemdetails.getTax_code()).getTax_id());
		
		return itemdetails;
	}
	
	public Pur_good_receipt_item_details gettaxcodefromgrnnew(String itemcode,String grnid,String packingcode) 
	{
		Pur_good_receipt_item_details itemdetails=pur_good_receipt_item_detailsRepository.getPurGoodRcptItemDtlsListTAXnew(grnid,itemcode,packingcode);
		System.out.println("tax code "+itemdetails.getTax_code());
		
		itemdetails.setTax_code(tax_code_detailsRepository.getTaxCodeDetailstax_name(itemdetails.getTax_code()).getTax_id());
		
		return itemdetails;
	}
	
	public Pur_good_receipt_item_details gettaxcodefromgrnnewForStore(String itemcode,String grnid,String packingcode,String classifird) 
	{
		Pur_good_receipt_item_details itemdetails=pur_good_receipt_item_detailsRepository.getPurGoodRcptItemDtlsListTAXnewForStore(grnid,itemcode,packingcode,classifird);
		System.out.println("tax code "+itemdetails.getTax_code());
		
		itemdetails.setTax_code(tax_code_detailsRepository.getTaxCodeDetailstax_name(itemdetails.getTax_code()).getTax_id());
		
		return itemdetails;
	}
	
	
	public List<Map<String,Object>> searchGRNFast(String orderno,String fromdate, String todate,String supplier_name1,String pur_type1,String finyear)
	{
		List<Map<String,Object>> searchgrn =new ArrayList<Map<String,Object>>();
		
		String tablename="pur_good_receipt",party_name="supplier_name",order_no="grn_no",order_date="grn_date",purchase_type="purchase_type";
		//System.out.println("tablename: "+tablename+" party_name: "+party_name+" order_no "+order_no+" order_date "+order_date+" orderno "+orderno+" fromdate "+fromdate+" todate "+todate+" supplier_name1 "+supplier_name1+" finyear "+finyear);
		searchgrn.addAll(pur_good_receiptRepository.getsearchdataFast(tablename,party_name,order_no,order_date,purchase_type,orderno,fromdate,todate,supplier_name1,pur_type1,finyear));
		
		return searchgrn;
	}
	
	public List<Pur_good_receipt_Pagination_DTO> searchGRN(String orderno,String fromdate, String todate,String supplier_name1,String pur_type1,String finyear)
	{
		List<Pur_good_receipt> searchgrn =new ArrayList<Pur_good_receipt>();
		
		String tablename="pur_good_receipt",party_name="supplier_name",order_no="grn_no",order_date="grn_date",purchase_type="purchase_type";
		//System.out.println("tablename: "+tablename+" party_name: "+party_name+" order_no "+order_no+" order_date "+order_date+" orderno "+orderno+" fromdate "+fromdate+" todate "+todate+" supplier_name1 "+supplier_name1+" finyear "+finyear);
		searchgrn.addAll(pur_good_receiptRepository.getsearchdata(tablename,party_name,order_no,order_date,purchase_type,orderno,fromdate,todate,supplier_name1,pur_type1,finyear));
		
		List<Pur_good_receipt_Pagination_DTO> advList = new ArrayList<Pur_good_receipt_Pagination_DTO>();
		for(int i=0;i<searchgrn.size();i++) 
		{
			
			//System.out.println("serchsaleorder.get(i).getId() "+searchsalesinvoice.get(i).getId());
			Pur_good_receipt_Pagination_DTO sale= new Pur_good_receipt_Pagination_DTO(searchgrn.get(i).getId(),
					searchgrn.get(i).getGrn_id(),
					searchgrn.get(i).getGrn_no(),
					searchgrn.get(i).getGrn_date(),
					searchgrn.get(i).getB_unitname(),
					searchgrn.get(i).getSupplier_name(),
					//searchgrn.get(i).isItem_type(),
					searchgrn.get(i).getPurchase_typename(),
					searchgrn.get(i).getReferance_type(),
					searchgrn.get(i).getVehicle_no(),
					searchgrn.get(i).getModified_type());
			
			advList.add(sale);
		}
		for(int x=0;x<advList.size();x++) {
			if(advList.get(x).getSupplier_name()!=null && advList.get(x).getSupplier_name().compareTo("")!=0 && advList.get(x).getSupplier_name().compareTo("0")!=0) {
				advList.get(x).setSupplier_name(suppBussiness_partnerRepository.getSupplierName(advList.get(x).getSupplier_name()).getBp_name());
			}
			else {
				advList.get(x).setSupplier_name("None");
			}
		}
		
		List<Pur_good_receipt_Pagination_DTO> allData = advList
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				  .sorted(Comparator.comparing(Pur_good_receipt_Pagination_DTO::getGrn_date).
						  thenComparingInt(
                        d -> d.getGrn_date().length() + d.getGrn_no().length())
                .thenComparing(Pur_good_receipt_Pagination_DTO::getGrn_no).reversed())
				  .collect(Collectors.toList());
		
		return allData;
	}
	 
	@Autowired
	Pur_good_receipt_brokerRepository pur_good_receipt_brokerRepository;
	
	public Pur_good_receipt findOne(Long id)
	{
		Optional<Pur_good_receipt> PGR=pur_good_receiptRepository.findById(id);
		return PGR.get();
	}
	
	
	
	@Autowired
	Pur_good_receipt_item_detailsRepository pur_good_receipt_item_detailsRepository;
	
	@Autowired
	Pur_good_receipt_resource_costRepository pur_good_receipt_resource_costRepository;
	
	@Autowired
	Pur_good_receipt_docRepository pur_good_receipt_docRepository;
	
	@Transactional
	public Pur_good_receipt update(Pur_good_receipt pur_good_receipt,Long id)
	{
		Optional<Pur_good_receipt> PGR=pur_good_receiptRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		pur_good_receipt.setReferance_id(PGR.get().getReferance_id());
		pur_good_receipt.setGrn_status("Open");
		pur_good_receipt.setPur_return_status("No");
		pur_good_receipt.setGrn_id(PGR.get().getGrn_id());
		pur_good_receipt.setModified_type("INSERTED");
		pur_good_receipt.setInserted_by(PGR.get().getInserted_by());
		pur_good_receipt.setInserted_on(PGR.get().getInserted_on());
		pur_good_receipt.setUpdated_by(userRepository.getUserDetails(pur_good_receipt.getUsername()).getName());
		pur_good_receipt.setUpdated_on(ldt);
		pur_good_receipt.setDeleted_by("NA");
		pur_good_receipt.setDeleted_on(ldt);
		pur_good_receipt.setBill_status(PGR.get().getBill_status());
		
		pur_good_receipt.setGrndate(pur_good_receipt.getGrn_date());
		pur_good_receipt.setGrnno(pur_good_receipt.getGrn_no());
		
	    pur_good_receipt.setPur_return_status(PGR.get().getPur_return_status());
		
		pur_good_receipt.setPurreturnid(PGR.get().getPurreturnid());
		
		
		if(pur_good_receipt.getReferance_type().compareToIgnoreCase("UNLOAD ADVICE")==0 && pur_good_receipt.getReferance_id().compareToIgnoreCase("0")==0)
		{
			pur_good_receipt.setReferance_id(PGR.get().getReferance_id());
		}
		else
		{
			pur_good_receipt.setReferance_id(pur_good_receipt.getReferance_id());
		}
		
		if(pur_good_receipt.getSupplier_name().compareTo("0") !=0 && pur_good_receipt.getSupplier_name().compareTo("") !=0 && pur_good_receipt.getSupplier_name() !=null) {
			pur_good_receipt.setSupplier(suppBussiness_partnerRepository.getSupplierName(pur_good_receipt.getSupplier_name()).getBp_name());
		}else {pur_good_receipt.setSupplier("None");}
		
		if(pur_good_receipt.getPurchase_type().compareTo("0") !=0 && pur_good_receipt.getPurchase_type().compareTo("") !=0 && pur_good_receipt.getPurchase_type() !=null) {
			pur_good_receipt.setPurchase_typename(item_type_masterRepository.getItemType(pur_good_receipt.getPurchase_type()).getItem_name());
		}else {pur_good_receipt.setPurchase_typename("None");}
		
		if(pur_good_receipt.getB_unit().compareTo("0") !=0 && pur_good_receipt.getB_unit().compareTo("") !=0 && pur_good_receipt.getB_unit() !=null) {
			pur_good_receipt.setB_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(pur_good_receipt.getB_unit()).getBusinessunit_name());
		}else {pur_good_receipt.setB_unitname("None");}
		
		if(pur_good_receipt.getVehicle_id().compareTo("0") !=0 && pur_good_receipt.getVehicle_id().compareTo("") !=0 && pur_good_receipt.getVehicle_id() !=null) {
			pur_good_receipt.setVehicle_no(vehicleMasterRepository.getVehicleDetails(pur_good_receipt.getVehicle_id()).getVehicle_no());
		}else {pur_good_receipt.setVehicle_no("None");}
		
		if(PGR.isPresent())
		{
			pur_good_receipt.setId(id);
		}
		
		
		if(pur_good_receipt.getPurchase_type().compareTo("ITMT00001")==0 || pur_good_receipt.getPurchase_type().compareTo("ITMT00010")==0) 
		{
			 wm_unload_adviceRepository.getundorevert(PGR.get().getReferance_id());
			 wm_unload_adviceRepository.getrevert(pur_good_receipt.getReferance_id());
			 
		}
		else if(pur_good_receipt.getPurchase_type().compareTo("ITMT00004")==0) 
		{
			pur_good_receiptRepository.getundorevert(PGR.get().getReferance_id());
			 pur_good_receiptRepository.getrevert(pur_good_receipt.getReferance_id());
		}
		else if(pur_good_receipt.getPurchase_type().compareTo("ITMT00005")==0) 
		{
			pur_good_receiptRepository.getundorevert(PGR.get().getReferance_id());
			 pur_good_receiptRepository.getrevert(pur_good_receipt.getReferance_id());
		}
		else if(pur_good_receipt.getPurchase_type().compareTo("ITMT00007")==0) 
		{
			pur_good_receiptRepository.getundorevert(PGR.get().getReferance_id());
			 pur_good_receiptRepository.getrevert(pur_good_receipt.getReferance_id());
		}
		
		
		pur_good_receipt_item_detailsRepository.updatePur_good_receipt_item_details(PGR.get().getGrn_id());
		Set<Pur_good_receipt_item_details> Pur_good_receiptID=new HashSet<Pur_good_receipt_item_details>();
		Pur_good_receiptID.addAll(pur_good_receipt.getPur_good_receipt_item_details());
		for(Pur_good_receipt_item_details abx:Pur_good_receiptID)
		{
			abx.setGrn_id(PGR.get().getGrn_id());
			abx.setGrn_no(pur_good_receipt.getGrn_no());
			abx.setAdv_item_name(item_masterRepository.itemNameById(abx.getAdv_item_code()).getItem_name());
			if(abx.getAdv_packing().compareTo("")!=0 && abx.getAdv_packing().compareTo("0")!=0) {
				abx.setAdv_packing_name(item_masterRepository.itemNameById(abx.getAdv_packing()).getItem_name());
			}
			abx.setFin_year(pur_good_receipt.getFin_year());
			abx.setCompany_id(pur_good_receipt.getCompany_id());
			abx.setModified_type("INSERTED");
			abx.setInserted_by(pur_good_receipt.getInserted_by());
			abx.setInserted_on(pur_good_receipt.getInserted_on());
			abx.setUpdated_by(pur_good_receipt.getUpdated_by());
			abx.setUpdated_on(pur_good_receipt.getUpdated_on());
			abx.setDeleted_by("NA");
			abx.setDeleted_on(ldt);
		}
		
		pur_good_receipt.setPur_good_receipt_item_details(Pur_good_receiptID);
		
		pur_goods_receipt_other_informationRepository.updatePur_goods_receipt_other_information(PGR.get().getGrn_id());
		Set<Pur_goods_receipt_other_information> PGROetailsSet = new HashSet<Pur_goods_receipt_other_information>();
		PGROetailsSet.add(pur_good_receipt.getPur_goods_receipt_other_information());
		for(Pur_goods_receipt_other_information Pur_goods_receiptOI : PGROetailsSet)
		{
			Pur_goods_receiptOI.setGrn_id(PGR.get().getGrn_id());
			Pur_goods_receiptOI.setGrn_no(pur_good_receipt.getGrn_no());
			Pur_goods_receiptOI.setFin_year(pur_good_receipt.getFin_year());
			Pur_goods_receiptOI.setCompany_id(pur_good_receipt.getCompany_id());
			Pur_goods_receiptOI.setModified_type("INSERTED");
			Pur_goods_receiptOI.setInserted_by(pur_good_receipt.getInserted_by());
			Pur_goods_receiptOI.setInserted_on(pur_good_receipt.getInserted_on());
			Pur_goods_receiptOI.setUpdated_by(pur_good_receipt.getUpdated_by());
			Pur_goods_receiptOI.setUpdated_on(pur_good_receipt.getUpdated_on());
			Pur_goods_receiptOI.setDeleted_by("NA");
			Pur_goods_receiptOI.setDeleted_on(ldt);
		}
		if(!PGROetailsSet.isEmpty())
		{
			pur_good_receipt.setPur_goods_receipt_other_information(PGROetailsSet.iterator().next());
		}
		
		
		pur_good_receipt_brokerRepository.updatePur_good_receipt_broker(PGR.get().getGrn_id());
		Set<Pur_good_receipt_broker> brokerSet=new HashSet<Pur_good_receipt_broker>();
		brokerSet.addAll(pur_good_receipt.getPur_good_receipt_broker());
		
		for(Pur_good_receipt_broker pgrd:brokerSet)
		{
			pgrd.setGrn_id(PGR.get().getGrn_id());
			pgrd.setGrn_no(pur_good_receipt.getGrn_no());
			
			if(Utility.isNullOrEmpty(pgrd.getVen_code_name())) {
				pgrd.setVen_name(broker_masterRepository.brokerNameByCode(pgrd.getVen_code_name()).getName());
				}
				else 
				{
					pgrd.setVen_name("");
				}
			
			pgrd.setFin_year(pur_good_receipt.getFin_year());
			pgrd.setCompany_id(pur_good_receipt.getCompany_id());
			pgrd.setModified_type("INSERTED");
			pgrd.setInserted_by(pur_good_receipt.getInserted_by());
			pgrd.setInserted_on(pur_good_receipt.getInserted_on());
			pgrd.setUpdated_by(pur_good_receipt.getUpdated_by());
			pgrd.setUpdated_on(pur_good_receipt.getUpdated_on());
			pgrd.setDeleted_by("NA");
			pgrd.setDeleted_on(ldt);
		}
		pur_good_receipt.setPur_good_receipt_broker(brokerSet);
		
		pur_good_receipt_driver_dtlsRepository.updatedriverdetails(PGR.get().getGrn_id());
		Set<Pur_good_receipt_driver_dtls> PGRDetailsSet = new HashSet<Pur_good_receipt_driver_dtls>();
		PGRDetailsSet.add(pur_good_receipt.getPur_good_receipt_driver_dtls());
		for(Pur_good_receipt_driver_dtls Pur_good_recieptDI : PGRDetailsSet)
		{
			Pur_good_recieptDI.setGrn_id(PGR.get().getGrn_id());
			Pur_good_recieptDI.setGrn_no(pur_good_receipt.getGrn_no());
			Pur_good_recieptDI.setFin_year(pur_good_receipt.getFin_year());
						
			Pur_good_recieptDI.setCompany_id(pur_good_receipt.getCompany_id());
			Pur_good_recieptDI.setModified_type("INSERTED");
			Pur_good_recieptDI.setInserted_by(pur_good_receipt.getInserted_by());
			Pur_good_recieptDI.setInserted_on(pur_good_receipt.getInserted_on());
			Pur_good_recieptDI.setUpdated_by(pur_good_receipt.getInserted_by());
			Pur_good_recieptDI.setUpdated_on(pur_good_receipt.getInserted_on());
			Pur_good_recieptDI.setDeleted_by("NA");
			Pur_good_recieptDI.setDeleted_on(ldt);
		}
		if(!PGRDetailsSet.isEmpty())
		{
			pur_good_receipt.setPur_good_receipt_driver_dtls(PGRDetailsSet.iterator().next());
		}
	
		
		pur_good_reciept_trans_infoRepository.updatePur_good_reciept_trans_info(PGR.get().getGrn_id());
		Set<Pur_good_reciept_trans_info> PGRTetailsSet = new HashSet<Pur_good_reciept_trans_info>();
		PGRTetailsSet.add(pur_good_receipt.getPur_good_reciept_trans_info());
		for(Pur_good_reciept_trans_info Pur_good_recieptTI : PGRTetailsSet)
		{
			Pur_good_recieptTI.setGrn_id(PGR.get().getGrn_id());
			Pur_good_recieptTI.setGrn_no(pur_good_receipt.getGrn_no());
			Pur_good_recieptTI.setFin_year(pur_good_receipt.getFin_year());
			Pur_good_recieptTI.setCompany_id(pur_good_receipt.getCompany_id());
			Pur_good_recieptTI.setModified_type("INSERTED");
			Pur_good_recieptTI.setInserted_by(pur_good_receipt.getInserted_by());
			Pur_good_recieptTI.setInserted_on(pur_good_receipt.getInserted_on());
			Pur_good_recieptTI.setUpdated_by(pur_good_receipt.getUpdated_by());
			Pur_good_recieptTI.setUpdated_on(pur_good_receipt.getUpdated_on());
			Pur_good_recieptTI.setDeleted_by("NA");
			Pur_good_recieptTI.setDeleted_on(ldt);
		}
		if(!PGRTetailsSet.isEmpty())
		{
			pur_good_receipt.setPur_good_reciept_trans_info(PGRTetailsSet.iterator().next());
		}
		
		
		pur_good_receipt_Business_Partner_detailsRepository.updatePur_good_receipt_Business_Partner_details(PGR.get().getGrn_id());
		
		Set<Pur_good_receipt_Business_Partner_details> BGRBDetailsSet = new HashSet<Pur_good_receipt_Business_Partner_details>();
		BGRBDetailsSet.add(pur_good_receipt.getPur_good_receipt_Business_Partner_details());
		for(Pur_good_receipt_Business_Partner_details Pur_good_receiptBPD : BGRBDetailsSet)
		{
			Pur_good_receiptBPD.setGrn_id(PGR.get().getGrn_id());
			Pur_good_receiptBPD.setGrn_no(pur_good_receipt.getGrn_no());
			Pur_good_receiptBPD.setFin_year(pur_good_receipt.getFin_year());
			Pur_good_receiptBPD.setCompany_id(pur_good_receipt.getCompany_id());
			Pur_good_receiptBPD.setModified_type("INSERTED");
			Pur_good_receiptBPD.setInserted_by(pur_good_receipt.getInserted_by());
			Pur_good_receiptBPD.setInserted_on(pur_good_receipt.getInserted_on());
			Pur_good_receiptBPD.setUpdated_by(pur_good_receipt.getUpdated_by());
			Pur_good_receiptBPD.setUpdated_on(pur_good_receipt.getUpdated_on());
			Pur_good_receiptBPD.setDeleted_by("NA");
			Pur_good_receiptBPD.setDeleted_on(ldt);
		}
		if(!BGRBDetailsSet.isEmpty())
		{
			pur_good_receipt.setPur_good_receipt_Business_Partner_details(BGRBDetailsSet.iterator().next());
		}
		
		pur_good_receipt_resource_costRepository.updatePur_good_receipt_resource_cost(PGR.get().getGrn_id());
		Set<Pur_good_receipt_resource_cost> PGRRC=new HashSet<Pur_good_receipt_resource_cost>();
		PGRRC.addAll(pur_good_receipt.getPur_good_receipt_resource_cost());
		
		for(Pur_good_receipt_resource_cost aby:PGRRC)
		{
			aby.setGrn_id(PGR.get().getGrn_id());
			aby.setGrn_no(pur_good_receipt.getGrn_no());
			aby.setFin_year(pur_good_receipt.getFin_year());
			aby.setCompany_id(pur_good_receipt.getCompany_id());
			aby.setModified_type("INSERTED");
			aby.setInserted_by(pur_good_receipt.getInserted_by());
			aby.setInserted_on(pur_good_receipt.getInserted_on());
			aby.setUpdated_by(pur_good_receipt.getUpdated_by());
			aby.setUpdated_on(pur_good_receipt.getUpdated_on());
			aby.setDeleted_by("NA");
			aby.setDeleted_on(ldt);
		}
		pur_good_receipt.setPur_good_receipt_resource_cost(PGRRC);
		
		pur_good_receipt_docRepository.updatePur_good_receipt_doc(PGR.get().getGrn_id());
		Set<Pur_good_receipt_doc> docSet=new HashSet<Pur_good_receipt_doc>();
		docSet.addAll(pur_good_receipt.getPur_good_receipt_docs());
		for(Pur_good_receipt_doc pgrd:docSet)
		{
			pgrd.setGrn_id(PGR.get().getGrn_id());
			pgrd.setGrn_no(pur_good_receipt.getGrn_no());
			pgrd.setFin_year(pur_good_receipt.getFin_year());
			pgrd.setCompany_id(pur_good_receipt.getCompany_id());
			pgrd.setModified_type("INSERTED");
			pgrd.setInserted_by(pur_good_receipt.getInserted_by());
			pgrd.setInserted_on(pur_good_receipt.getInserted_on());
			pgrd.setUpdated_by(pur_good_receipt.getUpdated_by());
			pgrd.setUpdated_on(pur_good_receipt.getUpdated_on());
			pgrd.setDeleted_by("NA");
			pgrd.setDeleted_on(ldt);
		}
		pur_good_receipt.setPur_good_receipt_docs(docSet);
	
	
		return pur_good_receiptRepository.save(pur_good_receipt);
	}
	
	
	 @Autowired
	 Pur_good_receipt_Business_Partner_detailsRepository pur_good_receipt_Business_Partner_detailsRepository;
	 
	 public Pur_good_receiptDTO getPurGoodRcptDtls(String grnid)
	 {
		 Pur_Order chargeid=new Pur_Order();
		 
		 Pur_good_receipt grnDtls=pur_good_receiptRepository.getPurGoodRcptDtls(grnid);
		 
		 Wm_unload_advice unloaddata=wm_unload_adviceRepository.getUnloadId(grnDtls.getReferance_id());
		//System.out.println("pur id:"+unloaddata.getPur_orderid());
		if(grnDtls.getReferance_type().compareToIgnoreCase("PURCHASE ORDER")==0)
		{
			chargeid=pur_OrderRepository.getPurOrdDetails(grnDtls.getReferance_id());
		}
		else {
			chargeid=pur_OrderRepository.getPurOrdDetails(unloaddata.getPur_orderid());
		}
		
		 grnDtls.setApplicable_charges_id(chargeid.getApp_chgs_id());
		 
		 Type listType = new TypeToken<Pur_good_receiptDTO>() {}.getType();
		 Pur_good_receiptDTO grnDetails= new ModelMapper().map(grnDtls,listType);
		 return grnDetails;
	 }
	 
	 
	 public Pur_good_receiptDTO getPurGoodRcptDtlsopengrn(String grnid)
	 {
		 Pur_good_receipt grnDtls=pur_good_receiptRepository.getPurGoodRcptDtls(grnid);
		 
		 
		 
		// Wm_unload_advice unloaddata=wm_unload_adviceRepository.getUnloadId(grnDtls.getReferance_id());
			
		// Pur_Order chargeid=pur_OrderRepository.getPurOrdDetails(unloaddata.getPur_orderid());
		
		// grnDtls.setApplicable_charges_id(chargeid.getApp_chgs_id());
		 
		 Type listType = new TypeToken<Pur_good_receiptDTO>() {}.getType();
		 Pur_good_receiptDTO grnDetails= new ModelMapper().map(grnDtls,listType);
		 return grnDetails;
	 }
	 
	 public List<Pur_good_receiptDTO> getPurGoodRcptList()
	 {
		 List<Pur_good_receipt> grnDtls=pur_good_receiptRepository.getPurGoodRcptList();
		 Type listType = new TypeToken<List<Pur_good_receiptDTO>>() {}.getType();
		 List<Pur_good_receiptDTO> grnDetails= new ModelMapper().map(grnDtls,listType);
		 return grnDetails;
	 }
	 
	 public List<Pur_good_receiptDTO> getPurGoodRcptThruSupp(String suppid,boolean itype,String ptype,String psubtype)
	 {
		 List<Pur_good_receipt> grnDtls=new ArrayList<Pur_good_receipt>();
		if(ptype.compareToIgnoreCase("ITMT00001")==0 || ptype.compareToIgnoreCase("ITMT00010")==0) 
		{
			grnDtls=pur_good_receiptRepository.getPurGoodRcptRawMaterial(suppid,itype,ptype,psubtype);
		}
		else {
			grnDtls=pur_good_receiptRepository.getPurGoodRcptOtherPurchase(suppid,itype,ptype,psubtype);
		}
		 
		 Type listType = new TypeToken<List<Pur_good_receiptDTO>>() {}.getType();
		 List<Pur_good_receiptDTO> grnDetailsSupp= new ModelMapper().map(grnDtls,listType);
		 for(int x=0;x<grnDetailsSupp.size();x++) {
				if(grnDetailsSupp.get(x).getB_unit()!=null && grnDetailsSupp.get(x).getB_unit().compareTo("")!=0 && grnDetailsSupp.get(x).getB_unit().compareTo("0")!=0) {
					grnDetailsSupp.get(x).setB_unit_name(companyBusinessUnitMasterRepository.CompanyBUAddress(grnDetailsSupp.get(x).getB_unit()).getBusinessunit_name());
				}
				else {
					grnDetailsSupp.get(x).setB_unit_name("No");
				}
				
				if(grnDetailsSupp.get(x).getSupplier_name().compareTo("0") !=0 && grnDetailsSupp.get(x).getSupplier_name().compareTo("") !=0 && grnDetailsSupp.get(x).getSupplier_name() !=null) {
					grnDetailsSupp.get(x).setParty_name(suppBussiness_partnerRepository.getSupplierName(grnDetailsSupp.get(x).getSupplier_name()).getBp_name());
				}else {grnDetailsSupp.get(x).setParty_name("None");}
			}
		 return grnDetailsSupp;
	 }
	 
	public List<Pur_good_receiptDTO> getPurGRptRtnApp(String bunit,String supplier,String tdate,String company,String finyear)
	{
		List<Pur_good_receipt> modelList =pur_good_receiptRepository.getPurGRptRtnApp(bunit,supplier,tdate,company,finyear);
		
		Type listType=new TypeToken<List<Pur_good_receiptDTO>>() {}.getType();
		
		List<Pur_good_receiptDTO> ordList=new ModelMapper().map(modelList,listType);
		
		return ordList;
	}
	 
	 public Pur_good_receipt_Business_Partner_detailsDTO getPurGoodRcptBPDtls(String grnid)
	 {
		 Pur_good_receipt_Business_Partner_details modelList=pur_good_receipt_Business_Partner_detailsRepository.getPurGoodRcptBPDtls(grnid);
		 Type listType = new TypeToken<Pur_good_receipt_Business_Partner_detailsDTO>() {}.getType();

		 Pur_good_receipt_Business_Partner_detailsDTO grnBPD= new ModelMapper().map(modelList,listType);
			
		return grnBPD;
	 }
	 
	 public List<Pur_good_receipt_item_detailsDTO> getPurGoodRcptItemDtlsList(String grnid)
		{
			List<Pur_good_receipt_item_details> modelList=pur_good_receipt_item_detailsRepository.getPurGoodRcptItemDtlsList(grnid);
			
			Type listType=new TypeToken<List<Pur_good_receipt_item_detailsDTO>>() {}.getType();
			
			List<Pur_good_receipt_item_detailsDTO> grnItem=new ModelMapper().map(modelList,listType);
			
			return grnItem;
		}
	 
	 public List<Map<String, Object>> grnItemDtlsRetriveListFast(String grnid){
		
		return pur_good_receipt_item_detailsRepository.grnItemDtlsRetriveListFast(grnid);
	}
	 
	 public List<Map<String, Object>> getPurGoodRcptItemDtlsListfastapi(String grnid)
	 {
			return pur_good_receipt_item_detailsRepository.getPurGoodRcptItemDtlsListfastapi(grnid);
		}
	 
	 public Map<String, Object> grnOtherInfoRetriveListFast(String grnid)
	 {
			return pur_goods_receipt_other_informationRepository.grnOtherInfoRetriveListFast(grnid);
		}
	  
	 public List<Map<String, Object>> grnBrokerRetriveListFast(String grnid){
			
			return pur_good_receipt_brokerRepository.grnBrokerRetriveListFast(grnid);
		}
	 
	 public Map<String, Object> grnTransInfoRetriveListFast(String grnid)
	 {
			return pur_good_reciept_trans_infoRepository.grnTransInfoRetriveListFast(grnid);
		}
	 
	 public Map<String, Object> grnBPDtlsRetriveListFast(String grnid)
	 {
			return pur_good_receipt_Business_Partner_detailsRepository.grnBPDtlsRetriveListFast(grnid);
		}
	 
	 public List<Map<String, Object>> grnResourceCostRetriveListFast(String grnid){
			
			return pur_good_receipt_resource_costRepository.grnResourceCostRetriveListFast(grnid);
		}
	 
	 public List<Map<String, Object>> grnDocRetriveListFast(String grnid){
			
			return pur_good_receipt_docRepository.grnDocRetriveListFast(grnid);
		}
	 
	 public Map<String, Object> grndriverdetailsFast(String grnid)
	 {
			return pur_good_receipt_driver_dtlsRepository.grndriverdetailsFast(grnid);
		}
	 
	 public List<Pur_good_receipt_resource_costDTO> grnResourceCostRetriveList(String code)
		{
			List<Pur_good_receipt_resource_cost> modelList=pur_good_receipt_resource_costRepository.grnResourceCostRetriveList(code);
			
			Type listType=new TypeToken<List<Pur_good_receipt_resource_costDTO>>() {}.getType();
			
			List<Pur_good_receipt_resource_costDTO> grnResource=new ModelMapper().map(modelList,listType);
			
			return grnResource;
		}
	 
	 @Autowired
	 Pur_good_reciept_trans_infoRepository pur_good_reciept_trans_infoRepository;
	 
	 public Pur_good_reciept_trans_infoDTO grnTransInfoRetriveList(String code)
	 {
		 Pur_good_reciept_trans_info modelList=pur_good_reciept_trans_infoRepository.grnTransInfoRetriveList(code);
		 Type listType = new TypeToken<Pur_good_reciept_trans_infoDTO>() {}.getType();

		 Pur_good_reciept_trans_infoDTO grnTransInfo= new ModelMapper().map(modelList,listType);
			
		return grnTransInfo;
	 }
	 
	 public Pur_good_receipt_driver_dtls grndriverdetails(String code)
	 {
		 Pur_good_receipt_driver_dtls modelList=pur_good_receipt_driver_dtlsRepository.grndriverdetails(code);
		
			
		return modelList;
	 }

	 @Autowired
	 Pur_goods_receipt_other_informationRepository pur_goods_receipt_other_informationRepository;
	 
	 public Pur_goods_receipt_other_informationDTO grnOtherInfoRetriveList(String code)
	 {
		 Pur_goods_receipt_other_information modelList=pur_goods_receipt_other_informationRepository.grnOtherInfoRetriveList(code);
		 Type listType = new TypeToken<Pur_goods_receipt_other_informationDTO>() {}.getType();

		 Pur_goods_receipt_other_informationDTO grnOtherInfo= new ModelMapper().map(modelList,listType);
			
		return grnOtherInfo;
	 }
	 
	 public List<Pur_good_receipt_docDTO> getPurGoodRcptDocList(String grnid)
		{
			List<Pur_good_receipt_doc> modelList=pur_good_receipt_docRepository.getPurGoodRcptDocList(grnid);
			
			Type listType=new TypeToken<List<Pur_good_receipt_docDTO>>() {}.getType();
			
			List<Pur_good_receipt_docDTO> grnDoc=new ModelMapper().map(modelList,listType);
			
			return grnDoc;
		}
	 
	 public List<Pur_good_receipt_brokerDTO> getPurGoodRcptBrokerList(String grnid)
		{
			List<Pur_good_receipt_broker> modelList=pur_good_receipt_brokerRepository.getPurGoodRcptBrokerList(grnid);
			
			Type listType=new TypeToken<List<Pur_good_receipt_brokerDTO>>() {}.getType();
			
			List<Pur_good_receipt_brokerDTO> grnBroker=new ModelMapper().map(modelList,listType);
			
			return grnBroker;
		}
	 
	 public StatusDTO checkGRNUsage(String grnid)
	 {
			String result = pur_good_receiptRepository.checkGRNUsage(grnid);
			Type listType = new TypeToken<StatusDTO>() {}.getType();
			StatusDTO statusDTO= new ModelMapper().map(result,listType);
			statusDTO.setStatus(result);
			return statusDTO;
		}
	 
	 @Transactional
	public Pur_good_receipt deleteGRN(Pur_good_receipt pur_good_receipt,Long id) 
 	{
		Optional<Pur_good_receipt> op = pur_good_receiptRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		Pur_good_receipt grn=op.get();
		
		grn.setModified_type("DELETED");
		grn.setInserted_by(op.get().getInserted_by());
		grn.setInserted_on(op.get().getInserted_on());
		grn.setUpdated_by(op.get().getUpdated_by());
		grn.setUpdated_on(op.get().getUpdated_on());
		grn.setDeleted_by(userRepository.getUserDetails(grn.getUsername()).getName());
		grn.setDeleted_on(ldt);
			
		pur_good_receipt_Business_Partner_detailsRepository.pur_good_receipt_Business_Partner_detailsUpdate(op.get().getGrn_id());
		
		pur_good_receipt_item_detailsRepository.pur_good_receipt_item_detailsUpdate(op.get().getGrn_id());
		
		pur_good_receipt_resource_costRepository.pur_good_receipt_resource_costUpdate(op.get().getGrn_id());
		
		pur_good_reciept_trans_infoRepository.pur_good_reciept_trans_infoUpdate(op.get().getGrn_id());
		
		pur_goods_receipt_other_informationRepository.pur_goods_receipt_other_informationUpdate(op.get().getGrn_id());
		
		pur_good_receiptRepository.pur_good_receipt_docsUpdate(op.get().getGrn_id());
		
		pur_good_receipt_brokerRepository.pur_good_receipt_brokerUpdate(op.get().getGrn_id());
		
		pur_good_receipt_driver_dtlsRepository.deletedriverdetails(op.get().getGrn_id());
		
		//unlaod atus to 1 to 0 make wuery
		//pur_good_receiptRepository.unload_adviceUpdate(op.get().getReferance_id());//change by tuhin on 1503 for changeing grn status 0 
		pur_good_receiptRepository.unload_adviceUpdategrn(op.get().getReferance_id());
		
		return pur_good_receiptRepository.save(grn);	
 	}
	 
	 public List<Pur_good_receiptDTO> getGRNList(String currDate,String finyear)
		{
		 	List<Pur_good_receipt> modelList =new ArrayList<Pur_good_receipt>();
		 	String tablename="pur_good_receipt",party_name="supplier_name",order_no="grn_no",order_date="grn_date",purchase_type="purchase_type";
			String orderno="",supplier_name1="",pur_type1="";
			
			modelList.addAll(pur_good_receiptRepository.getsearchdata(tablename,party_name,order_no,order_date,purchase_type,orderno,currDate,currDate,supplier_name1,pur_type1,finyear));
			
			
			Type listType = new TypeToken<List<Pur_good_receiptDTO>>() {}.getType();
			List<Pur_good_receiptDTO> advList = new ModelMapper().map(modelList,listType);
			
			for(int x=0;x<advList.size();x++) {
				if(advList.get(x).getSupplier_name()!=null && advList.get(x).getSupplier_name().compareTo("")!=0 && advList.get(x).getSupplier_name().compareTo("0")!=0) {
					advList.get(x).setSupplier_name(suppBussiness_partnerRepository.getSupplierName(advList.get(x).getSupplier_name()).getBp_name());
				}
				else {
					advList.get(x).setSupplier_name("None");
				}
			}
			
			return advList;
		}	 
	 
	 
	 public List<Map<String, Object>> getGRNListData(String currDate,String finyear){
			
			List<Map<String, Object>> grnlist = new ArrayList<Map<String, Object>>();
			 
			grnlist.addAll(pur_good_receiptRepository.grnListData(currDate,finyear));
			
			return grnlist;
			}
	 
	 public List<Map<String, Object>> getgrnpurbillReport(String fromdate,String todate)
	 {
		 List<Map<String, Object>> grnlist =pur_good_receiptRepository.getrgnpurbillreport(fromdate,todate);
		 return grnlist;
	 }
	 
	 public List<Map<String, Object>> getPurchaseTransportReport(String business_unit,String fromdate,String todate,String pur_inv_type,String trans_type)
	 {
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		
		 modelList.addAll(pur_good_receiptRepository.getPurchaseTransportReport(business_unit,fromdate,todate,pur_inv_type,trans_type));
			
		 return modelList;
	 }
	
	 public List<Map<String, Object>> getGRNThroughUnloadId(String unloadid)
	 	{ 
		  return pur_good_receiptRepository.getGRNThroughUnloadId(unloadid);
	 	}
	 
	 public List<Map<String, Object>> getGrnDetailsThroughGrnId(String grnid)
	 	{ 
		  return pur_good_receiptRepository.getGrnDetailsThroughGrnId(grnid);
	 	}
	 
	 public StatusDTO purchasechecktotaltranslimit(double totalamount,String supplier_name,String finyear) 
	 {
		 StatusDTO res = new StatusDTO();
		 double amount= pur_good_receiptRepository.getpurchasechecktotaltranslimit(supplier_name,finyear);
	
		 if((amount+totalamount)<=4500000) 
		 {
			 res.setStatus("No");
		 }
		 else 
		 {
			 res.setStatus("Yes");
		 }
		// res.setStatus(pur_good_receiptRepository.getpurchasechecktotaltranslimit(totalamount,supplier_name,finyear));
		 
		 return res;
	 }
	 
	 public StatusDTO purchasechecktotaltranslimitupdate(double totalamount,String supplier_name,String finyear,long id) 
	 {
		 StatusDTO res = new StatusDTO();
		 
		 Optional<Pur_good_receipt> op = pur_good_receiptRepository.findById(id);
		 
		 double previousamount= pur_good_receiptRepository.grndetailsamount(op.get().getGrn_id());
		 
		 double amount= pur_good_receiptRepository.getpurchasechecktotaltranslimit(supplier_name,finyear);
		 
		 double finalamount =amount + totalamount- previousamount;
	
		 if((finalamount)<=4500000) 
		 {
			 res.setStatus("No");
		 }
		 else 
		 {
			 res.setStatus("Yes");
		 }
		// res.setStatus(pur_good_receiptRepository.getpurchasechecktotaltranslimit(totalamount,supplier_name,finyear));
		 
		 return res;
	 }
	 
	 public List<Map<String, Object>> getJobWorkAllocationReport(String fromdate,String todate)
	 {
		 return pur_good_receiptRepository.getJobWorkAllocationReport(fromdate,todate);
	 }
	 
	 public List<Map<String, Object>> searchpendingGRNReport(String fromdate,String todate)
	 {
		 return pur_good_receiptRepository.searchpendingGRNReport(fromdate,todate);
	 }
	 
}
