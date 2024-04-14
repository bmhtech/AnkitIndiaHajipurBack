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

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Company_business_unit_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_transaction;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Weight_Dtl;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn_item_details;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_bu_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_docs;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_shipment_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_tax_info;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_trans_dtls;
import com.AnkitIndia.jwtauthentication.model.Stocksaleitem_groupwise_hsnsumm;
import com.AnkitIndia.jwtauthentication.model.Stocksaleitem_groupwise_summ;
import com.AnkitIndia.jwtauthentication.model.Stocksaleitem_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_ChallanRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_transfer_sales_invRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_ChallanDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Weight_DtlDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_bu_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_tax_infoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_trans_dtlsDTO;

@Service
public class Stk_transfer_sales_invService_Imp implements Stk_transfer_sales_invService {
	
	@Autowired
	Stk_transfer_sales_invRepository stk_transfer_sales_invRepository;
	
	@Autowired
	Stk_Transfer_ChallanRepository stk_Transfer_ChallanRepository;
	
	@Autowired
	Wm_loading_adviceRepository wm_loading_adviceRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Stk_Transfer_ChallanRepository stockTransferChallanRepository;

	public SequenceIdDTO getSTSISequenceId(String idate,String bunit){
		int slno=0;String prefix="STSI";
		String sno=stk_transfer_sales_invRepository.getSTSISequenceId(idate,bunit);
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = idate.split("-");
		prefix=prefix+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Stk_transfer_sales_inv save(Stk_transfer_sales_inv sInv)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(stk_transfer_sales_invRepository.countId() != null ) {
			slno=Long.parseLong(stk_transfer_sales_invRepository.countId());
		}
		String prefix="STSI";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		sInv.setStk_sales_inv_id(gen_sno);
		
		if(sInv.getBusiness_unit().compareTo("0") !=0 && sInv.getBusiness_unit().compareTo("") !=0 && sInv.getBusiness_unit() !=null) {
			sInv.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(sInv.getBusiness_unit()).getBusinessunit_name());
		}else {sInv.setBusiness_unitname("None");}
		
		//loading Adv Inv Status update
		/*System.out.println("Challan No: "+sInv.getReference_id());
		if(sInv.getReference_id().compareTo("0")!=0) {
			String laid=stk_Transfer_ChallanRepository.getStkTransChallanDtls(sInv.getReference_id()).getReference_id();
			wm_loading_adviceRepository.updateStockInvStatus(laid);
		}*/
		
		sInv.setModified_type("INSERTED");
		sInv.setInserted_by(userRepository.getUserDetails(sInv.getUsername()).getName());
		sInv.setInserted_on(ldt);
		sInv.setUpdated_by("NA");
		sInv.setUpdated_on(ldt);
		sInv.setDeleted_by("NA");
		sInv.setDeleted_on(ldt);
		//stk_Transfer_ChallanRepository.updateStkTransferChallan(sInv.getReference_id(),sInv.getStk_sales_inv_id());
		
		if(sInv.getChallan().compareToIgnoreCase("Multiple")==0) 
		{
			
			
			//loading Adv Inv Status update
			System.out.println("Challan No: "+sInv.getReference_id());
			
			String splitchallan[]=sInv.getReference_id().split(",");
			
			for(int i=0;i<splitchallan.length;i++) 
			{
				if(sInv.getReference_id().compareTo("0")!=0) {
					String laid=stk_Transfer_ChallanRepository.getStkTransChallanDtls(splitchallan[i]).getReference_id();
					wm_loading_adviceRepository.updateStockInvStatus(laid);
				}
				
				stk_Transfer_ChallanRepository.updateStkTransferChallanmultiple(splitchallan[i],sInv.getStk_sales_inv_id());
			}
			
		}
		else 
		{
			//loading Adv Inv Status update
			System.out.println("Challan No: "+sInv.getReference_id());
			if(sInv.getReference_id().compareTo("0")!=0) {
				String laid=stk_Transfer_ChallanRepository.getStkTransChallanDtls(sInv.getReference_id()).getReference_id();
				wm_loading_adviceRepository.updateStockInvStatus(laid);
			}
			stk_Transfer_ChallanRepository.updateStkTransferChallan(sInv.getReference_id(),sInv.getStk_sales_inv_id());
			
		}
		
		//Dynamic
		Set<Stk_transfer_sales_inv_item_dtls> itemSet = new HashSet<Stk_transfer_sales_inv_item_dtls>();
		itemSet.addAll(sInv.getStk_transfer_sales_inv_item_dtls());
		for(Stk_transfer_sales_inv_item_dtls sItem_dtls : itemSet)
		{
			sItem_dtls.setStk_sales_inv_id(gen_sno);
			sItem_dtls.setStk_sales_inv_no(sInv.getStk_sales_inv_no());
			
			if(sItem_dtls.getItem_code().compareTo("0") !=0 && sItem_dtls.getItem_code().compareTo("") !=0 && sItem_dtls.getItem_code() !=null) {
				sItem_dtls.setItem_name(item_masterRepository.itemNameById(sItem_dtls.getItem_code()).getItem_name());
			}else {sItem_dtls.setItem_name("None");}
			
			//sItem_dtls.setItem_name(item_masterRepository.itemNameById(sItem_dtls.getItem_code()).getItem_name());
			if(sItem_dtls.getPacking().compareTo("")!=0 && sItem_dtls.getPacking().compareTo("0")!=0) {
				sItem_dtls.setPacking_name(item_masterRepository.itemNameById(sItem_dtls.getPacking()).getItem_name());
			}else {sItem_dtls.setPacking_name("None");}
			
			sItem_dtls.setCompany_id(sInv.getCompany_id());
			sItem_dtls.setFin_year(sInv.getFin_year());
			sItem_dtls.setModified_type("INSERTED");
			sItem_dtls.setInserted_by(sInv.getInserted_by());
			sItem_dtls.setInserted_on(sInv.getInserted_on());
			sItem_dtls.setUpdated_by("NA");
			sItem_dtls.setUpdated_on(ldt);
			sItem_dtls.setDeleted_by("NA");
			sItem_dtls.setDeleted_on(ldt);
		
		}
		sInv.setStk_transfer_sales_inv_item_dtls(itemSet);
		
		//Dynamic
		Set<Stk_transfer_sales_inv_docs> sInv_docs = new HashSet<Stk_transfer_sales_inv_docs>();
		sInv_docs.addAll(sInv.getStk_transfer_sales_inv_docs());
		for(Stk_transfer_sales_inv_docs sDocs : sInv_docs)
		{
			sDocs.setStk_sales_inv_id(gen_sno);
			sDocs.setStk_sales_inv_no(sInv.getStk_sales_inv_no());
			sDocs.setCompany_id(sInv.getCompany_id());
			sDocs.setFin_year(sInv.getFin_year());
			sDocs.setModified_type("INSERTED");
			sDocs.setInserted_by(sInv.getInserted_by());
			sDocs.setInserted_on(sInv.getInserted_on());
			sDocs.setUpdated_by("NA");
			sDocs.setUpdated_on(ldt);
			sDocs.setDeleted_by("NA");
			sDocs.setDeleted_on(ldt);
		
		}
		sInv.setStk_transfer_sales_inv_docs(sInv_docs);
		
		//Static
		Set<Stk_transfer_sales_inv_tax_info> sTax_infos=new HashSet<Stk_transfer_sales_inv_tax_info>();
		sTax_infos.add(sInv.getStk_transfer_sales_inv_tax_info());
		for(Stk_transfer_sales_inv_tax_info sInfo:sTax_infos) 
		{
			sInfo.setStk_sales_inv_id(gen_sno);	
			sInfo.setStk_sales_inv_no(sInv.getStk_sales_inv_no());
			sInfo.setCompany_id(sInv.getCompany_id());
			sInfo.setFin_year(sInv.getFin_year());
			sInfo.setModified_type("INSERTED");
			sInfo.setInserted_by(sInv.getInserted_by());
			sInfo.setInserted_on(sInv.getInserted_on());
			sInfo.setUpdated_by("NA");
			sInfo.setUpdated_on(ldt);
			sInfo.setDeleted_by("NA");
			sInfo.setDeleted_on(ldt);
		}
		if(!sTax_infos.isEmpty())
		{
			sInv.setStk_transfer_sales_inv_tax_info(sTax_infos.iterator().next());
		}
		
		//Static
		Set<Stk_transfer_sales_inv_bu_dtls> sBu_dtlsSet=new HashSet<Stk_transfer_sales_inv_bu_dtls>();
		sBu_dtlsSet.add(sInv.getStk_transfer_sales_inv_bu_dtls());
		for(Stk_transfer_sales_inv_bu_dtls sBu_dtls:sBu_dtlsSet) 
		{
			sBu_dtls.setStk_sales_inv_id(gen_sno);	
			sBu_dtls.setStk_sales_inv_no(sInv.getStk_sales_inv_no());
			sBu_dtls.setCompany_id(sInv.getCompany_id());
			sBu_dtls.setFin_year(sInv.getFin_year());
			sBu_dtls.setModified_type("INSERTED");
			sBu_dtls.setInserted_by(sInv.getInserted_by());
			sBu_dtls.setInserted_on(sInv.getInserted_on());
			sBu_dtls.setUpdated_by("NA");
			sBu_dtls.setUpdated_on(ldt);
			sBu_dtls.setDeleted_by("NA");
			sBu_dtls.setDeleted_on(ldt);
		}
		if(!sBu_dtlsSet.isEmpty())
		{
			sInv.setStk_transfer_sales_inv_bu_dtls(sBu_dtlsSet.iterator().next());
		}
		
		//Dynamic
		Set<Stk_transfer_sales_inv_trans_dtls> sTrans_dtlsSet = new HashSet<Stk_transfer_sales_inv_trans_dtls>();
		sTrans_dtlsSet.addAll(sInv.getStk_transfer_sales_inv_trans_dtls());
		for(Stk_transfer_sales_inv_trans_dtls sTrans_dtls : sTrans_dtlsSet)
		{
			sTrans_dtls.setStk_sales_inv_id(gen_sno);
			sTrans_dtls.setStk_sales_inv_no(sInv.getStk_sales_inv_no());
			sTrans_dtls.setCompany_id(sInv.getCompany_id());
			sTrans_dtls.setFin_year(sInv.getFin_year());
			sTrans_dtls.setModified_type("INSERTED");
			sTrans_dtls.setInserted_by(sInv.getInserted_by());
			sTrans_dtls.setInserted_on(sInv.getInserted_on());
			sTrans_dtls.setUpdated_by("NA");
			sTrans_dtls.setUpdated_on(ldt);
			sTrans_dtls.setDeleted_by("NA");
			sTrans_dtls.setDeleted_on(ldt);
		
		}
		sInv.setStk_transfer_sales_inv_trans_dtls(sTrans_dtlsSet);
		
		//dynamic Stocksaleitem_groupwise_hsnsumm
		
				Set<Stocksaleitem_groupwise_hsnsumm> stock_item_groupwisehsnsum = new HashSet<Stocksaleitem_groupwise_hsnsumm>();
				stock_item_groupwisehsnsum.addAll(sInv.getStocksaleitem_groupwise_hsnsumm());
				for(Stocksaleitem_groupwise_hsnsumm stock_item_hsn_sum : stock_item_groupwisehsnsum)
				{
					stock_item_hsn_sum.setStk_sales_inv_id(gen_sno);
					stock_item_hsn_sum.setStk_sales_inv_no(sInv.getStk_sales_inv_no());
					stock_item_hsn_sum.setCompany_id(sInv.getCompany_id());
					stock_item_hsn_sum.setFin_year(sInv.getFin_year());
					stock_item_hsn_sum.setModified_type("INSERTED");
					stock_item_hsn_sum.setInserted_by(sInv.getInserted_by());
					stock_item_hsn_sum.setInserted_on(sInv.getInserted_on());
					stock_item_hsn_sum.setUpdated_by("NA");
					stock_item_hsn_sum.setUpdated_on(ldt);
					stock_item_hsn_sum.setDeleted_by("NA");
					stock_item_hsn_sum.setDeleted_on(ldt);
				
				}
				sInv.setStocksaleitem_groupwise_hsnsumm(stock_item_groupwisehsnsum);
				
				//dynamic Stocksaleitem_groupwise_summ
				
				Set<Stocksaleitem_groupwise_summ> stock_item_groupwisesum = new HashSet<Stocksaleitem_groupwise_summ>();
				stock_item_groupwisesum.addAll(sInv.getStocksaleitem_groupwise_summ());
				for(Stocksaleitem_groupwise_summ stock_item_group_sum : stock_item_groupwisesum)
				{
					stock_item_group_sum.setStk_sales_inv_id(gen_sno);
					stock_item_group_sum.setStk_sales_inv_no(sInv.getStk_sales_inv_no());
					stock_item_group_sum.setCompany_id(sInv.getCompany_id());
					stock_item_group_sum.setFin_year(sInv.getFin_year());
					stock_item_group_sum.setModified_type("INSERTED");
					stock_item_group_sum.setInserted_by(sInv.getInserted_by());
					stock_item_group_sum.setInserted_on(sInv.getInserted_on());
					stock_item_group_sum.setUpdated_by("NA");
					stock_item_group_sum.setUpdated_on(ldt);
					stock_item_group_sum.setDeleted_by("NA");
					stock_item_group_sum.setDeleted_on(ldt);
				
				}
				sInv.setStocksaleitem_groupwise_summ(stock_item_groupwisesum);
				
				
				
				//dynamic Stocksaleitem_groupwise_taxsumm
				
				Set<Stocksaleitem_groupwise_taxsumm> stocksaleitem_groupwise_taxsumm = new HashSet<Stocksaleitem_groupwise_taxsumm>();
				stocksaleitem_groupwise_taxsumm.addAll(sInv.getStocksaleitem_groupwise_taxsumm());
				for(Stocksaleitem_groupwise_taxsumm stock_item_tax_sum : stocksaleitem_groupwise_taxsumm)
				{
					stock_item_tax_sum.setStk_sales_inv_id(gen_sno);
					stock_item_tax_sum.setStk_sales_inv_no(sInv.getStk_sales_inv_no());
					stock_item_tax_sum.setCompany_id(sInv.getCompany_id());
					stock_item_tax_sum.setFin_year(sInv.getFin_year());
					stock_item_tax_sum.setModified_type("INSERTED");
					stock_item_tax_sum.setInserted_by(sInv.getInserted_by());
					stock_item_tax_sum.setInserted_on(sInv.getInserted_on());
					stock_item_tax_sum.setUpdated_by("NA");
					stock_item_tax_sum.setUpdated_on(ldt);
					stock_item_tax_sum.setDeleted_by("NA");
					stock_item_tax_sum.setDeleted_on(ldt);
				
				}
				sInv.setStocksaleitem_groupwise_taxsumm(stocksaleitem_groupwise_taxsumm);
				
		//Static
		Set<Stk_transfer_sales_inv_shipment_dtls> shipment_dtlsSet=new HashSet<Stk_transfer_sales_inv_shipment_dtls>();
		shipment_dtlsSet.add(sInv.getStk_transfer_sales_inv_shipment_dtls());
		for(Stk_transfer_sales_inv_shipment_dtls shipment_dtls:shipment_dtlsSet) 
		{
			shipment_dtls.setStk_sales_inv_id(gen_sno);	
			shipment_dtls.setStk_sales_inv_no(sInv.getStk_sales_inv_no());
			shipment_dtls.setCompany_id(sInv.getCompany_id());
			shipment_dtls.setFin_year(sInv.getFin_year());
			shipment_dtls.setModified_type("INSERTED");
			shipment_dtls.setInserted_by(sInv.getInserted_by());
			shipment_dtls.setInserted_on(sInv.getInserted_on());
			shipment_dtls.setUpdated_by("NA");
			shipment_dtls.setUpdated_on(ldt);
			shipment_dtls.setDeleted_by("NA");
			shipment_dtls.setDeleted_on(ldt);
		}
		if(!shipment_dtlsSet.isEmpty())
		{
			sInv.setStk_transfer_sales_inv_shipment_dtls(shipment_dtlsSet.iterator().next());
		}
		
		return stk_transfer_sales_invRepository.save(sInv);
	}
	
	public List<Stk_transfer_sales_inv> getStkTranSaleInvs(String company,String finyear){
		
		return stk_transfer_sales_invRepository.findAllStkTransSInv(company,finyear);
	}
	
	public Stk_transfer_sales_inv findOne(long id){
		Optional<Stk_transfer_sales_inv> op=this.stk_transfer_sales_invRepository.findById(id);
		return op.get();
	}
	
	public List<Stk_transfer_sales_inv_item_dtlsDTO> getStkTransSalesInvItemDtls(String stk_sales_inv_id)
	{
		List<Stk_transfer_sales_inv_item_dtls> itemDtls=new ArrayList<Stk_transfer_sales_inv_item_dtls>();
		
		itemDtls.addAll(stk_transfer_sales_invRepository.getStk_transfer_invoice_item_details(stk_sales_inv_id));
			
		Type listType=new TypeToken<List<Stk_transfer_sales_inv_item_dtlsDTO>>() {}.getType();
		
		List<Stk_transfer_sales_inv_item_dtlsDTO> stkTrnsItmDtls=new ModelMapper().map(itemDtls,listType);
		
		return stkTrnsItmDtls;
	}
	
	public List<Stk_transfer_sales_inv_trans_dtlsDTO> getStkTransSalesInvTransDtls(String stk_sales_inv_id)
	{
		List<Stk_transfer_sales_inv_trans_dtls> transDtls=new ArrayList<Stk_transfer_sales_inv_trans_dtls>();
		
		transDtls.addAll(stk_transfer_sales_invRepository.getStk_transfer_invoice_trans_details(stk_sales_inv_id));
			
		Type listType=new TypeToken<List<Stk_transfer_sales_inv_trans_dtlsDTO>>() {}.getType();
		
		List<Stk_transfer_sales_inv_trans_dtlsDTO> stkTrnsDtls=new ModelMapper().map(transDtls,listType);
		
		return stkTrnsDtls;
	}
	
	public Stk_transfer_sales_inv_bu_dtlsDTO getStkTransSalesInvBUDtls(String stk_sales_inv_id)
	{
		Stk_transfer_sales_inv_bu_dtls modelList=stk_transfer_sales_invRepository.getStkTransSalesInvBUDtls(stk_sales_inv_id);
		Type listType = new TypeToken<Stk_transfer_sales_inv_bu_dtlsDTO>() {}.getType();
		Stk_transfer_sales_inv_bu_dtlsDTO stkTrBUdetails= new ModelMapper().map(modelList,listType);
		return stkTrBUdetails;
	}
	
	public List<Stk_transfer_sales_inv_docs> getStkTransSalesInvDocs(String stk_sales_inv_id)
	{
		List<Stk_transfer_sales_inv_docs> transDocs=new ArrayList<Stk_transfer_sales_inv_docs>();
		
		transDocs.addAll(stk_transfer_sales_invRepository.getStkTransSalesInvDocs(stk_sales_inv_id));
			
		return transDocs;
	}
	
	public Stk_transfer_sales_inv_shipment_dtlsDTO getStkTransSalesInvShipDtls(String stk_sales_inv_id)
	{
		Stk_transfer_sales_inv_shipment_dtls modelList=stk_transfer_sales_invRepository.getStkTransShipDtls(stk_sales_inv_id);
		Type listType = new TypeToken<Stk_transfer_sales_inv_shipment_dtlsDTO>() {}.getType();
		Stk_transfer_sales_inv_shipment_dtlsDTO stkshipdetails= new ModelMapper().map(modelList,listType);
		return stkshipdetails;
	}
	
	public Stk_transfer_sales_inv_tax_infoDTO getStkTransSalesInvTaxInfo(String stk_sales_inv_id)
	{
		Stk_transfer_sales_inv_tax_info modelList=stk_transfer_sales_invRepository.getStkTransSalesInvTaxInfo(stk_sales_inv_id);
		Type listType = new TypeToken<Stk_transfer_sales_inv_tax_infoDTO>() {}.getType();
		Stk_transfer_sales_inv_tax_infoDTO taxinfo= new ModelMapper().map(modelList,listType);
		return taxinfo;
	}
	
	public Stk_Transfer_ChallanDTO stkSalesInv(long id){
		
		Optional<Stk_transfer_sales_inv> op = stk_transfer_sales_invRepository.findById(id);
		
		Stk_Transfer_Challan stkChallan = stockTransferChallanRepository.getStkTransChallanDtls(op.get().getReference_id());
		
		stkChallan.setSaleinvoice_status(op.get().getStk_sales_inv_id());
		
		Type listType=new TypeToken<Stk_Transfer_ChallanDTO>() {}.getType();
		Stk_Transfer_ChallanDTO salesstk=new ModelMapper().map(stkChallan,listType);
		
		return salesstk;
		
	}
	
	@Transactional
	public Stk_transfer_sales_inv deleteStockTransferSalesInvoice(Stk_transfer_sales_inv sInv,Long id) {

			Optional<Stk_transfer_sales_inv> op = stk_transfer_sales_invRepository.findById(id);
			LocalDateTime ldt = LocalDateTime.now();
			Stk_transfer_sales_inv salesInv=op.get();
			
			salesInv.setStk_sales_inv_id(op.get().getStk_sales_inv_id());
			salesInv.setInserted_by(op.get().getInserted_by());
			salesInv.setInserted_on(op.get().getInserted_on());
			salesInv.setUpdated_by(op.get().getUpdated_by());
			salesInv.setUpdated_on(op.get().getUpdated_on());
			salesInv.setDeleted_by(userRepository.getUserDetails(salesInv.getUsername()).getName());
			salesInv.setDeleted_on(ldt);
			salesInv.setModified_type("DELETED");
			
			//System.out.println("chk1");
			if(op.isPresent()) {
				salesInv.setId(id);
			}
			String laid=stk_Transfer_ChallanRepository.getStkTransChallanDtls(salesInv.getReference_id()).getReference_id();
			stk_transfer_sales_invRepository.updateStockInvLoadingStatus(laid);
			
			stk_transfer_sales_invRepository.stk_Transfer_ChallanUpdate(op.get().getStk_sales_inv_id());
			
			stk_transfer_sales_invRepository.stk_transfer_sales_inv_item_dtlsUpdate(op.get().getStk_sales_inv_id());
			
			stk_transfer_sales_invRepository.stk_transfer_sales_inv_docsUpdate(op.get().getStk_sales_inv_id());
			
			stk_transfer_sales_invRepository.stk_transfer_sales_inv_tax_infoUpdate(op.get().getStk_sales_inv_id());
			
			stk_transfer_sales_invRepository.stk_transfer_sales_inv_bu_dtlsUpdate(op.get().getStk_sales_inv_id());
			
			stk_transfer_sales_invRepository.stk_transfer_sales_inv_trans_dtlsUpdate(op.get().getStk_sales_inv_id());
			
			stk_transfer_sales_invRepository.stk_transfer_sales_inv_shipment_dtlsUpdate(op.get().getStk_sales_inv_id());
			
			stk_transfer_sales_invRepository.stocksaleitem_groupwise_hsnsummUpdate(op.get().getStk_sales_inv_id());
			
			stk_transfer_sales_invRepository.stocksaleitem_groupwise_summUpdate(op.get().getStk_sales_inv_id());
			
			stk_transfer_sales_invRepository.stocksaleitem_groupwise_taxsummUpdate(op.get().getStk_sales_inv_id());
			
			
			return stk_transfer_sales_invRepository.save(salesInv);	
		}
	
	public List<Stk_transfer_sales_inv_item_dtlsDTO> getMultipleStkTransSalesInvItemDtlsupdate(long id)
	{
		
		
		Optional<Stk_transfer_sales_inv> op = stk_transfer_sales_invRepository.findById(id);
		
		List<Stk_transfer_sales_inv_item_dtls> itemDtls=new ArrayList<Stk_transfer_sales_inv_item_dtls>();
		
		itemDtls.addAll(stk_transfer_sales_invRepository.getStk_transfer_invoice_item_details(op.get().getStk_sales_inv_id()));
		
	
		Type listType=new TypeToken<List<Stk_transfer_sales_inv_item_dtlsDTO>>() {}.getType();
		
		List<Stk_transfer_sales_inv_item_dtlsDTO> stkTrnsItmDtls=new ModelMapper().map(itemDtls,listType);
		
		return stkTrnsItmDtls;
		
		
		
		
	}
	
	public Stk_transfer_sales_inv getStkTransSalesInvByIdprint(long id) 
	{
		Stk_transfer_sales_inv sales_inv=new Stk_transfer_sales_inv();
		
		Optional<Stk_transfer_sales_inv> op=this.stk_transfer_sales_invRepository.findById(id);
		
		Stk_Transfer_Challan recievvingbu=stockTransferChallanRepository.getStkTransChallanDtls(op.get().getReference_id());
		
		sales_inv=op.get();
		
		sales_inv.setBusiness_unitname(recievvingbu.getDelivery_business_unit());
		return sales_inv;
	}
	
	public Stk_transfer_sales_inv getstockrecivingbuunit(long id) 
	{
		Stk_transfer_sales_inv sales_inv=new Stk_transfer_sales_inv();
		Optional<Stk_transfer_sales_inv> op=this.stk_transfer_sales_invRepository.findById(id);
		
		Company_business_unit_master companydetails_stocksalesinvoicesending =companyBusinessUnitMasterRepository.businessunitbyid(op.get().getBusiness_unit());
		Company_business_unit_master companydetails_stocksalesinvoicerecieving = new Company_business_unit_master();
		if(op.get().getChallan().compareToIgnoreCase("Single")==0) 
		{
			
			Stk_Transfer_Challan recievvingbu=stockTransferChallanRepository.getStkTransChallanDtls(op.get().getReference_id());
			
			
			companydetails_stocksalesinvoicerecieving=companyBusinessUnitMasterRepository.businessunitbyid(recievvingbu.getDelivery_business_unit());
			
		}
		if(op.get().getChallan().compareToIgnoreCase("Multiple")==0) 
		{
		    String splitchallan[]=op.get().getReference_id().split(",");
		    
		    Stk_Transfer_Challan recievvingbu=stockTransferChallanRepository.getStkTransChallanDtls(splitchallan[0]);
		    
		    companydetails_stocksalesinvoicerecieving=companyBusinessUnitMasterRepository.businessunitbyid(recievvingbu.getDelivery_business_unit());
		}
		
		sales_inv.setBusiness_unit(companydetails_stocksalesinvoicesending.getState_name());//sending business unit
		sales_inv.setBusiness_unitname(companydetails_stocksalesinvoicerecieving.getState_name());//recieving business Unt
		
		return sales_inv;
	}
	
	
	public  List<Stocksaleitem_groupwise_hsnsumm> getStockSaleInvHsnSum(String stk_sales_inv_id) 
	{
		List<Stocksaleitem_groupwise_hsnsumm> hsn=new ArrayList<Stocksaleitem_groupwise_hsnsumm>(); 
		  hsn.addAll(stk_transfer_sales_invRepository.gethsn(stk_sales_inv_id));
		return hsn;
	}
	public  List<Stocksaleitem_groupwise_taxsumm> getStockSaleInvTaxSum(String stk_sales_inv_id) 
	{
		List<Stocksaleitem_groupwise_taxsumm> tax =new ArrayList<Stocksaleitem_groupwise_taxsumm>(); 
		tax.addAll(stk_transfer_sales_invRepository.gettax(stk_sales_inv_id));
		return tax;
	}
	
}
