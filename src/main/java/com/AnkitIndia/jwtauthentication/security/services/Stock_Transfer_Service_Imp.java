package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_transaction;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Docs;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer_Summary;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer_Summary_dyn;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer_Trans_Info;
import com.AnkitIndia.jwtauthentication.model.Stock_transfer_doc;
import com.AnkitIndia.jwtauthentication.model.Stock_transfer_resource_cost;
import com.AnkitIndia.jwtauthentication.model.Stock_transfer_terminations;
import com.AnkitIndia.jwtauthentication.model.Stock_transfer_terminations_dyn;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.repository.BinMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_ChallanRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_TransferRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_Transfer_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_Transfer_SummaryRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_Transfer_Summary_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_Transfer_Trans_InfoRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_transfer_docRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_transfer_resource_costRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_transfer_terminationsRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_transfer_terminations_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.WarehouseMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_adviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_TransferDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_SummaryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_Summary_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_transfer_resource_costDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_transfer_terminationsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_transfer_terminations_dynDTO;

@Service
public class Stock_Transfer_Service_Imp implements Stock_Transfer_Service{
	
	@Autowired
	Stock_TransferRepository stock_TransferRepository;
	
	@Autowired
	Stock_Transfer_SummaryRepository stock_Transfer_SummaryRepository;
	
	@Autowired
	Stock_Transfer_Trans_InfoRepository stock_Transfer_Trans_InfoRepository;
	
	@Autowired
	Stock_transfer_resource_costRepository stock_transfer_resource_costRepository;
	
	@Autowired
	Stock_Transfer_Summary_dynRepository stock_Transfer_Summary_dynRepository;
	
	@Autowired
	Stock_Transfer_Item_DtlsRepository stock_Transfer_Item_DtlsRepository;
	
	@Autowired
	Stock_transfer_docRepository stock_transfer_docRepository;
	
	@Autowired
	Stock_transfer_terminations_dynRepository stock_transfer_terminations_dynRepository;
	
	@Autowired
	Stock_transfer_terminationsRepository stock_transfer_terminationsRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	WarehouseMasterRepository warehouseMasterRepository;
	
	@Autowired
	BinMasterRepository binMasterRepository;
	
	@Autowired
	Wm_loading_adviceRepository wm_loading_adviceRepository;
	
	@Autowired
	Stk_Transfer_ChallanRepository stockTransferChallanRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
		public SequenceIdDTO getSTOSequenceId(String sdate,String ordpoint)
		{
			int slno=0;String prefix="STO",orderpoint="";
			String sno=stock_TransferRepository.getSTOSequenceId(sdate,ordpoint);
			if(ordpoint.compareTo("Inter")==0) {orderpoint="ITR";}
			if(ordpoint.compareTo("Intra")==0) {orderpoint="ITA";}
			if(sno != null ) {
				slno=Integer.parseInt(sno);
			}
			String date[] = sdate.split("-");
			prefix=prefix+"-"+orderpoint+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
			System.err.println(prefix);
			
			String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
			
			Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
			
			SequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
			
			genCode.setSequenceid(gen_sno);
			
			return genCode;
		}
		
		@Transactional
		public Stock_Transfer save(Stock_Transfer stock_Transfer) 
		{
			LocalDateTime ldt = LocalDateTime.now();
			
			long slno=0;
			if(stock_TransferRepository.countId() != null ) {
				slno=Long.parseLong(stock_TransferRepository.countId());
			}
			String prefix="ST";
			String gen_sno=UniqueID.uniqueid(prefix,slno);
			stock_Transfer.setOrder_id(gen_sno);	
			
			/*Start checking transaction no for unique */
			System.err.println("First:>>"+stock_Transfer.getOrder_no());
			long nslno=0;String tprefix="STO",orderpoint="";
			String tsno=stock_TransferRepository.getSTOSequenceId(stock_Transfer.getOrder_date(),stock_Transfer.getOrder_point());
			if(stock_Transfer.getOrder_point().compareTo("Inter")==0) {orderpoint="ITR";}
			if(stock_Transfer.getOrder_point().compareTo("Intra")==0) {orderpoint="ITA";}
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			String date[] = stock_Transfer.getOrder_date().split("-");
			tprefix=tprefix+"-"+orderpoint+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
			String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
			stock_Transfer.setOrder_no(gen_tslno);
			System.err.println("Last:>>>"+stock_Transfer.getOrder_no());
			/*End checking transaction no for unique */
			
			stock_Transfer.setModified_type("INSERTED");
			stock_Transfer.setInserted_by(userRepository.getUserDetails(stock_Transfer.getUsername()).getName());
			stock_Transfer.setInserted_on(ldt);
			stock_Transfer.setUpdated_by("NA");
			stock_Transfer.setUpdated_on(ldt);
			stock_Transfer.setDeleted_by("NA");
			stock_Transfer.setDeleted_on(ldt);
			
			Set<Stock_Transfer_Trans_Info> stkTrfrTrnsInf=new HashSet<Stock_Transfer_Trans_Info>();
			stkTrfrTrnsInf.add(stock_Transfer.getStock_Transfer_Trans_Info());
			for(Stock_Transfer_Trans_Info stTrTraIn:stkTrfrTrnsInf) 
			{
				stTrTraIn.setOrder_id(gen_sno);	
				stTrTraIn.setOrder_no(stock_Transfer.getOrder_no());
				stTrTraIn.setCompany_id(stock_Transfer.getCompany_id());
				stTrTraIn.setFin_year(stock_Transfer.getFin_year());
				stTrTraIn.setModified_type("INSERTED");
				stTrTraIn.setInserted_by(stock_Transfer.getInserted_by());
				stTrTraIn.setInserted_on(stock_Transfer.getInserted_on());
				stTrTraIn.setUpdated_by("NA");
				stTrTraIn.setUpdated_on(ldt);
				stTrTraIn.setDeleted_by("NA");
				stTrTraIn.setDeleted_on(ldt);
			}
			if(!stkTrfrTrnsInf.isEmpty())
			{
				stock_Transfer.setStock_Transfer_Trans_Info(stkTrfrTrnsInf.iterator().next());
			}
			
			Set<Stock_Transfer_Summary> stkTrnsSum=new HashSet<Stock_Transfer_Summary>();
			stkTrnsSum.add(stock_Transfer.getStock_Transfer_Summary());
			for(Stock_Transfer_Summary stTrSm:stkTrnsSum) 
			{
				stTrSm.setOrder_id(gen_sno);	
				stTrSm.setOrder_no(stock_Transfer.getOrder_no());
				stTrSm.setCompany_id(stock_Transfer.getCompany_id());
				stTrSm.setFin_year(stock_Transfer.getFin_year());
				stTrSm.setModified_type("INSERTED");
				stTrSm.setInserted_by(stock_Transfer.getInserted_by());
				stTrSm.setInserted_on(stock_Transfer.getInserted_on());
				stTrSm.setUpdated_by("NA");
				stTrSm.setUpdated_on(ldt);
				stTrSm.setDeleted_by("NA");
				stTrSm.setDeleted_on(ldt);
			}
			if(!stkTrnsSum.isEmpty())
			{
				stock_Transfer.setStock_Transfer_Summary(stkTrnsSum.iterator().next());
			}
			
			Set<Stock_Transfer_Summary_dyn> stkTrnSumDyn=new HashSet<Stock_Transfer_Summary_dyn>();
			stkTrnSumDyn.addAll(stock_Transfer.getStock_Transfer_Summary_dyn());
			for(Stock_Transfer_Summary_dyn stkTrSDyn:stkTrnSumDyn) 
			{
				stkTrSDyn.setOrder_id(gen_sno);
				stkTrSDyn.setOrder_no(stock_Transfer.getOrder_no());
				stkTrSDyn.setCompany_id(stock_Transfer.getCompany_id());
				stkTrSDyn.setFin_year(stock_Transfer.getFin_year());
				stkTrSDyn.setModified_type("INSERTED");
				stkTrSDyn.setInserted_by(stock_Transfer.getInserted_by());
				stkTrSDyn.setInserted_on(stock_Transfer.getInserted_on());
				stkTrSDyn.setUpdated_by("NA");
				stkTrSDyn.setUpdated_on(ldt);
				stkTrSDyn.setDeleted_by("NA");
				stkTrSDyn.setDeleted_on(ldt);
			}
			stock_Transfer.setStock_Transfer_Summary_dyn(stkTrnSumDyn);
			
			Set<Stock_Transfer_Item_Dtls> stkTrnItmDtls=new HashSet<Stock_Transfer_Item_Dtls>();
			stkTrnItmDtls.addAll(stock_Transfer.getStock_Transfer_Item_Dtls());
			for(Stock_Transfer_Item_Dtls stTrnItmDtl:stkTrnItmDtls) 
			{
				stTrnItmDtl.setOrder_id(gen_sno);
				stTrnItmDtl.setOrder_no(stock_Transfer.getOrder_no());
				stTrnItmDtl.setItem_name(item_masterRepository.itemNameById(stTrnItmDtl.getItem_code()).getItem_name());
				if(stTrnItmDtl.getPacking().compareTo("")!=0 && stTrnItmDtl.getPacking().compareTo("0")!=0) {
					stTrnItmDtl.setPacking_name(item_masterRepository.itemNameById(stTrnItmDtl.getPacking()).getItem_name());
				}
				if(Utility.isNullOrEmpty(stTrnItmDtl.getRack())) 
				{
					if(stTrnItmDtl.getRack().compareTo("")!=0 && stTrnItmDtl.getRack().compareTo("0")!=0) {
						stTrnItmDtl.setRack_name(binMasterRepository.getBinDesc(stTrnItmDtl.getRack()).getBin_description());
					}
				}
				if(Utility.isNullOrEmpty(stTrnItmDtl.getWarehouse())) 
				{
					if(stTrnItmDtl.getWarehouse().compareTo("")!=0 && stTrnItmDtl.getWarehouse().compareTo("0")!=0) {
						stTrnItmDtl.setWarehouse_name(warehouseMasterRepository.getWarehouseDetails(stTrnItmDtl.getWarehouse()).getWarehouse_name());
					}
				}
				/*if(stTrnItmDtl.getRack().compareTo("")!=0 && stTrnItmDtl.getRack().compareTo("0")!=0) {
					stTrnItmDtl.setRack_name(binMasterRepository.getBinDesc(stTrnItmDtl.getRack()).getBin_description());
				}
				if(stTrnItmDtl.getWarehouse().compareTo("")!=0 && stTrnItmDtl.getWarehouse().compareTo("0")!=0) {
					stTrnItmDtl.setWarehouse_name(warehouseMasterRepository.getWarehouseDetails(stTrnItmDtl.getWarehouse()).getWarehouse_name());
				}*/
				stTrnItmDtl.setCompany_id(stock_Transfer.getCompany_id());
				stTrnItmDtl.setFin_year(stock_Transfer.getFin_year());
				stTrnItmDtl.setModified_type("INSERTED");
				stTrnItmDtl.setInserted_by(stock_Transfer.getInserted_by());
				stTrnItmDtl.setInserted_on(stock_Transfer.getInserted_on());
				stTrnItmDtl.setUpdated_by("NA");
				stTrnItmDtl.setUpdated_on(ldt);
				stTrnItmDtl.setDeleted_by("NA");
				stTrnItmDtl.setDeleted_on(ldt);
			}
			stock_Transfer.setStock_Transfer_Item_Dtls(stkTrnItmDtls);
			
			Set<Stock_transfer_resource_cost> stkTrnReCo=new HashSet<Stock_transfer_resource_cost>();
			stkTrnReCo.addAll(stock_Transfer.getStock_transfer_resource_cost());
			for(Stock_transfer_resource_cost stkTrnReCos:stkTrnReCo) 
			{
				stkTrnReCos.setOrder_id(gen_sno);
				stkTrnReCos.setOrder_no(stock_Transfer.getOrder_no());
				stkTrnReCos.setCompany_id(stock_Transfer.getCompany_id());
				stkTrnReCos.setFin_year(stock_Transfer.getFin_year());
				stkTrnReCos.setModified_type("INSERTED");
				stkTrnReCos.setInserted_by(stock_Transfer.getInserted_by());
				stkTrnReCos.setInserted_on(stock_Transfer.getInserted_on());
				stkTrnReCos.setUpdated_by("NA");
				stkTrnReCos.setUpdated_on(ldt);
				stkTrnReCos.setDeleted_by("NA");
				stkTrnReCos.setDeleted_on(ldt);
			}
			stock_Transfer.setStock_transfer_resource_cost(stkTrnReCo);
			
			Set<Stock_transfer_doc> stkTrnDoc=new HashSet<Stock_transfer_doc>();
			stkTrnDoc.addAll(stock_Transfer.getStock_transfer_doc());
			for(Stock_transfer_doc stkTrnsDoc:stkTrnDoc) 
			{
				stkTrnsDoc.setOrder_id(gen_sno);
				stkTrnsDoc.setOrder_no(stock_Transfer.getOrder_no());
				stkTrnsDoc.setCompany_id(stock_Transfer.getCompany_id());
				stkTrnsDoc.setFin_year(stock_Transfer.getFin_year());
				stkTrnsDoc.setModified_type("INSERTED");
				stkTrnsDoc.setInserted_by(stock_Transfer.getInserted_by());
				stkTrnsDoc.setInserted_on(stock_Transfer.getInserted_on());
				stkTrnsDoc.setUpdated_by("NA");
				stkTrnsDoc.setUpdated_on(ldt);
				stkTrnsDoc.setDeleted_by("NA");
				stkTrnsDoc.setDeleted_on(ldt);
			}
			stock_Transfer.setStock_transfer_doc(stkTrnDoc);
			
			Set<Stock_transfer_terminations_dyn> stkTrnDyns=new HashSet<Stock_transfer_terminations_dyn>();
			stkTrnDyns.addAll(stock_Transfer.getStock_transfer_terminations_dyn());
			for(Stock_transfer_terminations_dyn stkTrnsDyn:stkTrnDyns) 
			{
				stkTrnsDyn.setOrder_id(gen_sno);
				stkTrnsDyn.setOrder_no(stock_Transfer.getOrder_no());
				stkTrnsDyn.setCompany_id(stock_Transfer.getCompany_id());
				stkTrnsDyn.setFin_year(stock_Transfer.getFin_year());
				stkTrnsDyn.setModified_type("INSERTED");
				stkTrnsDyn.setInserted_by(stock_Transfer.getInserted_by());
				stkTrnsDyn.setInserted_on(stock_Transfer.getInserted_on());
				stkTrnsDyn.setUpdated_by("NA");
				stkTrnsDyn.setUpdated_on(ldt);
				stkTrnsDyn.setDeleted_by("NA");
				stkTrnsDyn.setDeleted_on(ldt);
			}
			stock_Transfer.setStock_transfer_terminations_dyn(stkTrnDyns);
			
			Set<Stock_transfer_terminations> stkTrTerminations=new HashSet<Stock_transfer_terminations>();
			stkTrTerminations.add(stock_Transfer.getStock_transfer_terminations());
			for(Stock_transfer_terminations stkTerminations:stkTrTerminations) 
			{
				stkTerminations.setOrder_id(gen_sno);	
				stkTerminations.setOrder_no(stock_Transfer.getOrder_no());
				stkTerminations.setCompany_id(stock_Transfer.getCompany_id());
				stkTerminations.setFin_year(stock_Transfer.getFin_year());
				stkTerminations.setModified_type("INSERTED");
				stkTerminations.setInserted_by(stock_Transfer.getInserted_by());
				stkTerminations.setInserted_on(stock_Transfer.getInserted_on());
				stkTerminations.setUpdated_by("NA");
				stkTerminations.setUpdated_on(ldt);
				stkTerminations.setDeleted_by("NA");
				stkTerminations.setDeleted_on(ldt);
			}
			if(!stkTrfrTrnsInf.isEmpty())
			{
				stock_Transfer.setStock_transfer_terminations(stkTrTerminations.iterator().next());
			}
			
			return stock_TransferRepository.save(stock_Transfer);
			
		}	
		
		@Transactional
		public Stock_Transfer update(Stock_Transfer stock_Transfer,Long id)
		{
			Optional<Stock_Transfer> op = stock_TransferRepository.findById(id);
			LocalDateTime ldt = LocalDateTime.now();
			
			stock_Transfer.setModified_type("INSERTED");
			stock_Transfer.setInserted_by(op.get().getInserted_by());
			stock_Transfer.setInserted_on(op.get().getInserted_on());
			stock_Transfer.setUpdated_by(userRepository.getUserDetails(stock_Transfer.getUsername()).getName());
			stock_Transfer.setUpdated_on(ldt);
			stock_Transfer.setDeleted_by("NA");
			stock_Transfer.setDeleted_on(ldt);
			
			if(op.isPresent())
			{
				stock_Transfer.setId(id);
			}
			
			stock_Transfer_Trans_InfoRepository.updateStock_Transfer_Trans_Info(op.get().getOrder_id());
			
			Set<Stock_Transfer_Trans_Info> stkTrfrTrnsInf=new HashSet<Stock_Transfer_Trans_Info>();
			stkTrfrTrnsInf.add(stock_Transfer.getStock_Transfer_Trans_Info());
			for(Stock_Transfer_Trans_Info stTrTraIn:stkTrfrTrnsInf) 
			{
				stTrTraIn.setOrder_id(op.get().getOrder_id());	
				stTrTraIn.setOrder_no(stock_Transfer.getOrder_no());
				stTrTraIn.setCompany_id(stock_Transfer.getCompany_id());
				stTrTraIn.setFin_year(stock_Transfer.getFin_year());
				stTrTraIn.setModified_type("INSERTED");
				stTrTraIn.setInserted_by(stock_Transfer.getInserted_by());
				stTrTraIn.setInserted_on(stock_Transfer.getInserted_on());
				stTrTraIn.setUpdated_by(stock_Transfer.getUpdated_by());
				stTrTraIn.setUpdated_on(stock_Transfer.getUpdated_on());
				stTrTraIn.setDeleted_by("NA");
				stTrTraIn.setDeleted_on(ldt);
			}
			if(!stkTrfrTrnsInf.isEmpty())
			{
				stock_Transfer.setStock_Transfer_Trans_Info(stkTrfrTrnsInf.iterator().next());
			}
			
			stock_Transfer_SummaryRepository.updateStock_Transfer_Summary(op.get().getOrder_id());
			
			Set<Stock_Transfer_Summary> stkTrnsSum=new HashSet<Stock_Transfer_Summary>();
			stkTrnsSum.add(stock_Transfer.getStock_Transfer_Summary());
			for(Stock_Transfer_Summary stTrSm:stkTrnsSum) 
			{
				stTrSm.setOrder_id(op.get().getOrder_id());	
				stTrSm.setOrder_no(stock_Transfer.getOrder_no());
				stTrSm.setCompany_id(stock_Transfer.getCompany_id());
				stTrSm.setFin_year(stock_Transfer.getFin_year());
				stTrSm.setModified_type("INSERTED");
				stTrSm.setInserted_by(stock_Transfer.getInserted_by());
				stTrSm.setInserted_on(stock_Transfer.getInserted_on());
				stTrSm.setUpdated_by(stock_Transfer.getUpdated_by());
				stTrSm.setUpdated_on(stock_Transfer.getUpdated_on());
				stTrSm.setDeleted_by("NA");
				stTrSm.setDeleted_on(ldt);
			}
			if(!stkTrnsSum.isEmpty())
			{
				stock_Transfer.setStock_Transfer_Summary(stkTrnsSum.iterator().next());
			}
			
			stock_Transfer_Summary_dynRepository.updateStock_Transfer_Summary_dyn(op.get().getOrder_id());
			
			Set<Stock_Transfer_Summary_dyn> stkTrnSumDyn=new HashSet<Stock_Transfer_Summary_dyn>();
			stkTrnSumDyn.addAll(stock_Transfer.getStock_Transfer_Summary_dyn());
			for(Stock_Transfer_Summary_dyn stkTrSDyn:stkTrnSumDyn) 
			{
				stkTrSDyn.setOrder_id(op.get().getOrder_id());
				stkTrSDyn.setOrder_no(stock_Transfer.getOrder_no());
				stkTrSDyn.setCompany_id(stock_Transfer.getCompany_id());
				stkTrSDyn.setFin_year(stock_Transfer.getFin_year());
				stkTrSDyn.setModified_type("INSERTED");
				stkTrSDyn.setInserted_by(stock_Transfer.getInserted_by());
				stkTrSDyn.setInserted_on(stock_Transfer.getInserted_on());
				stkTrSDyn.setUpdated_by(stock_Transfer.getUpdated_by());
				stkTrSDyn.setUpdated_on(stock_Transfer.getUpdated_on());
				stkTrSDyn.setDeleted_by("NA");
				stkTrSDyn.setDeleted_on(ldt);
			}
			stock_Transfer.setStock_Transfer_Summary_dyn(stkTrnSumDyn);
			
			stock_Transfer_Item_DtlsRepository.updateStock_Transfer_Item_Dtls(op.get().getOrder_id());
			
			Set<Stock_Transfer_Item_Dtls> stkTrnItmDtls=new HashSet<Stock_Transfer_Item_Dtls>();
			stkTrnItmDtls.addAll(stock_Transfer.getStock_Transfer_Item_Dtls());
			for(Stock_Transfer_Item_Dtls stTrnItmDtl:stkTrnItmDtls) 
			{
				stTrnItmDtl.setOrder_id(op.get().getOrder_id());
				stTrnItmDtl.setOrder_no(stock_Transfer.getOrder_no());
				stTrnItmDtl.setItem_name(item_masterRepository.itemNameById(stTrnItmDtl.getItem_code()).getItem_name());
				if(stTrnItmDtl.getPacking().compareTo("")!=0 && stTrnItmDtl.getPacking().compareTo("0")!=0) {
					stTrnItmDtl.setPacking_name(item_masterRepository.itemNameById(stTrnItmDtl.getPacking()).getItem_name());
				}
				if(Utility.isNullOrEmpty(stTrnItmDtl.getRack())) 
				{
					if(stTrnItmDtl.getRack().compareTo("")!=0 && stTrnItmDtl.getRack().compareTo("0")!=0) {
						stTrnItmDtl.setRack_name(binMasterRepository.getBinDesc(stTrnItmDtl.getRack()).getBin_description());
					}
				}
				if(Utility.isNullOrEmpty(stTrnItmDtl.getWarehouse())) 
				{
					if(stTrnItmDtl.getWarehouse().compareTo("")!=0 && stTrnItmDtl.getWarehouse().compareTo("0")!=0) {
						stTrnItmDtl.setWarehouse_name(warehouseMasterRepository.getWarehouseDetails(stTrnItmDtl.getWarehouse()).getWarehouse_name());
					}
				}
				/*if(stTrnItmDtl.getRack().compareTo("")!=0 && stTrnItmDtl.getRack().compareTo("0")!=0) {
					stTrnItmDtl.setRack_name(binMasterRepository.getBinDesc(stTrnItmDtl.getRack()).getBin_description());
				}
				if(stTrnItmDtl.getWarehouse().compareTo("")!=0 && stTrnItmDtl.getWarehouse().compareTo("0")!=0) {
					stTrnItmDtl.setWarehouse_name(warehouseMasterRepository.getWarehouseDetails(stTrnItmDtl.getWarehouse()).getWarehouse_name());
				}*/
				stTrnItmDtl.setCompany_id(stock_Transfer.getCompany_id());
				stTrnItmDtl.setFin_year(stock_Transfer.getFin_year());
				stTrnItmDtl.setModified_type("INSERTED");
				stTrnItmDtl.setInserted_by(stock_Transfer.getInserted_by());
				stTrnItmDtl.setInserted_on(stock_Transfer.getInserted_on());
				stTrnItmDtl.setUpdated_by(stock_Transfer.getUpdated_by());
				stTrnItmDtl.setUpdated_on(stock_Transfer.getUpdated_on());
				stTrnItmDtl.setDeleted_by("NA");
				stTrnItmDtl.setDeleted_on(ldt);
			}
			stock_Transfer.setStock_Transfer_Item_Dtls(stkTrnItmDtls);
			
			stock_transfer_resource_costRepository.updateStock_transfer_resource_cost(op.get().getOrder_id());
			
			Set<Stock_transfer_resource_cost> stkTrnReCo=new HashSet<Stock_transfer_resource_cost>();
			stkTrnReCo.addAll(stock_Transfer.getStock_transfer_resource_cost());
			for(Stock_transfer_resource_cost stkTrnReCos:stkTrnReCo) 
			{
				stkTrnReCos.setOrder_id(op.get().getOrder_id());
				stkTrnReCos.setOrder_no(stock_Transfer.getOrder_no());
				stkTrnReCos.setCompany_id(stock_Transfer.getCompany_id());
				stkTrnReCos.setFin_year(stock_Transfer.getFin_year());
				stkTrnReCos.setModified_type("INSERTED");
				stkTrnReCos.setInserted_by(stock_Transfer.getInserted_by());
				stkTrnReCos.setInserted_on(stock_Transfer.getInserted_on());
				stkTrnReCos.setUpdated_by(stock_Transfer.getUpdated_by());
				stkTrnReCos.setUpdated_on(stock_Transfer.getUpdated_on());
				stkTrnReCos.setDeleted_by("NA");
				stkTrnReCos.setDeleted_on(ldt);
			}
			stock_Transfer.setStock_transfer_resource_cost(stkTrnReCo);
			
			stock_transfer_docRepository.updateStock_transfer_doc(op.get().getOrder_id());
			
			Set<Stock_transfer_doc> stkTrnDoc=new HashSet<Stock_transfer_doc>();
			stkTrnDoc.addAll(stock_Transfer.getStock_transfer_doc());
			for(Stock_transfer_doc stkTrnsDoc:stkTrnDoc) 
			{
				stkTrnsDoc.setOrder_id(op.get().getOrder_id());
				stkTrnsDoc.setOrder_no(stock_Transfer.getOrder_no());
				stkTrnsDoc.setCompany_id(stock_Transfer.getCompany_id());
				stkTrnsDoc.setFin_year(stock_Transfer.getFin_year());
				stkTrnsDoc.setModified_type("INSERTED");
				stkTrnsDoc.setInserted_by(stock_Transfer.getInserted_by());
				stkTrnsDoc.setInserted_on(stock_Transfer.getInserted_on());
				stkTrnsDoc.setUpdated_by(stock_Transfer.getUpdated_by());
				stkTrnsDoc.setUpdated_on(stock_Transfer.getUpdated_on());
				stkTrnsDoc.setDeleted_by("NA");
				stkTrnsDoc.setDeleted_on(ldt);
			}
			stock_Transfer.setStock_transfer_doc(stkTrnDoc);
			
			//Update
			stock_transfer_terminations_dynRepository.updateStock_transfer_terminations_dyn(op.get().getOrder_id());
			
			Set<Stock_transfer_terminations_dyn> stkTrnDyns=new HashSet<Stock_transfer_terminations_dyn>();
			stkTrnDyns.addAll(stock_Transfer.getStock_transfer_terminations_dyn());
			for(Stock_transfer_terminations_dyn stkTrnsDyn:stkTrnDyns) 
			{
				stkTrnsDyn.setOrder_id(op.get().getOrder_id());
				stkTrnsDyn.setOrder_no(stock_Transfer.getOrder_no());
				stkTrnsDyn.setCompany_id(stock_Transfer.getCompany_id());
				stkTrnsDyn.setFin_year(stock_Transfer.getFin_year());
				stkTrnsDyn.setModified_type("INSERTED");
				stkTrnsDyn.setInserted_by(stock_Transfer.getInserted_by());
				stkTrnsDyn.setInserted_on(stock_Transfer.getInserted_on());
				stkTrnsDyn.setUpdated_by("NA");
				stkTrnsDyn.setUpdated_on(ldt);
				stkTrnsDyn.setDeleted_by("NA");
				stkTrnsDyn.setDeleted_on(ldt);
			}
			stock_Transfer.setStock_transfer_terminations_dyn(stkTrnDyns);
			
			//Update
			stock_transfer_terminationsRepository.updateStock_transfer_terminations(op.get().getOrder_id());
			
			Set<Stock_transfer_terminations> stkTrTerminations=new HashSet<Stock_transfer_terminations>();
			stkTrTerminations.add(stock_Transfer.getStock_transfer_terminations());
			for(Stock_transfer_terminations stkTerminations:stkTrTerminations) 
			{
				stkTerminations.setOrder_id(op.get().getOrder_id());	
				stkTerminations.setOrder_no(stock_Transfer.getOrder_no());
				stkTerminations.setCompany_id(stock_Transfer.getCompany_id());
				stkTerminations.setFin_year(stock_Transfer.getFin_year());
				stkTerminations.setModified_type("INSERTED");
				stkTerminations.setInserted_by(stock_Transfer.getInserted_by());
				stkTerminations.setInserted_on(stock_Transfer.getInserted_on());
				stkTerminations.setUpdated_by("NA");
				stkTerminations.setUpdated_on(ldt);
				stkTerminations.setDeleted_by("NA");
				stkTerminations.setDeleted_on(ldt);
			}
			if(!stkTrfrTrnsInf.isEmpty())
			{
				stock_Transfer.setStock_transfer_terminations(stkTrTerminations.iterator().next());
			}
			
			return stock_TransferRepository.save(stock_Transfer);
		}	
		
		public Stock_Transfer findOne(long id){
			Optional<Stock_Transfer> op=this.stock_TransferRepository.findById(id);
			return op.get();
		}
		
		public List<Stock_Transfer> findAll()
		{
			List<Stock_Transfer> stlist=new ArrayList<Stock_Transfer>();
			stlist.addAll(stock_TransferRepository.findAllStkTransOrds());
			for(int i=0;i<stlist.size();i++) {
				stlist.get(i).setBusiness_unit(companyBusinessUnitMasterRepository.CompanyBUAddress(stlist.get(i).getBusiness_unit()).getBusinessunit_name());
				stlist.get(i).setDelivery_business_unit(companyBusinessUnitMasterRepository.CompanyBUAddress(stlist.get(i).getDelivery_business_unit()).getBusinessunit_name());
			}
			return stlist;
		}
		
		public List<Stock_TransferDTO> getStkTrans(){
			List<Stock_Transfer> modelList=stock_TransferRepository.getStkTransNew();
			
			Type listType=new TypeToken<List<Stock_TransferDTO>>() {}.getType();
			
			List<Stock_TransferDTO> desc=new ModelMapper().map(modelList, listType);
			
			return desc;
		}
		
		public Stock_TransferDTO getStockTransDtls(String order_id){
			Stock_Transfer modelList=stock_TransferRepository.getStockTransDtls(order_id);
			
			Type listType=new TypeToken<Stock_TransferDTO>() {}.getType();
			
			Stock_TransferDTO stockDetails=new ModelMapper().map(modelList, listType);
			
			return stockDetails;
		}
		
		public List<Stock_TransferDTO> getStockTransThruInter(){
			List<Stock_Transfer> modelList=stock_TransferRepository.getStockTransThruInter();
			
			Type listType=new TypeToken<List<Stock_TransferDTO>>() {}.getType();
			
			List<Stock_TransferDTO> desc=new ModelMapper().map(modelList, listType);
			
			return desc;
		}
		public List<Stock_TransferDTO> getStockTransOrds(String tdate,String bunit){
			List<Stock_Transfer> finalList=new ArrayList<Stock_Transfer>();
			
			//finalList.addAll(stock_TransferRepository.getStockTransOrdsInter(tdate,bunit));
			finalList.addAll(stock_TransferRepository.getStockTransOrdsInterNew(tdate,bunit));
			System.err.println("Got List Data 1: "+finalList.size());
			//finalList.addAll(stock_TransferRepository.getStockTransOrdsIntra(tdate,bunit));
			finalList.addAll(stock_TransferRepository.getStockTransOrdsIntraNew(tdate,bunit));
			System.err.println("Got List Data 2: "+finalList.size());
			Type listType=new TypeToken<List<Stock_TransferDTO>>() {}.getType();
			
			List<Stock_TransferDTO> desc=new ModelMapper().map(finalList, listType);
			
			return desc;
		}
		
		public Stock_Transfer_Trans_InfoDTO getStkTransTranInfo(String order_id){

			Stock_Transfer_Trans_Info modelList=stock_Transfer_Trans_InfoRepository.getStkTransTranInfo(order_id);

			Type listType = new TypeToken<Stock_Transfer_Trans_InfoDTO>() {}.getType();

			Stock_Transfer_Trans_InfoDTO stkTrTraInfo= new ModelMapper().map(modelList,listType);
			return stkTrTraInfo;
		}
		
		public Stock_Transfer_SummaryDTO getStkTransSumm(String order_id){

			Stock_Transfer_Summary modelList=stock_Transfer_SummaryRepository.getStkTransSumm(order_id);

			Type listType = new TypeToken<Stock_Transfer_SummaryDTO>() {}.getType();

			Stock_Transfer_SummaryDTO stkTraSum= new ModelMapper().map(modelList,listType);
			
			return stkTraSum;
		}
		
		public List<Stock_Transfer_Summary_dynDTO> getStkTraSumDyn(String order_id)
		{
			List<Stock_Transfer_Summary_dyn> modelList=stock_Transfer_Summary_dynRepository.getStkTraSumDyn(order_id);
			
			Type listType=new TypeToken<List<Stock_Transfer_Summary_dynDTO>>() {}.getType();
			
			List<Stock_Transfer_Summary_dynDTO> stkTrSumDyn=new ModelMapper().map(modelList,listType);
			
			return stkTrSumDyn;
		}
		
		public List<Stock_Transfer_Item_DtlsDTO> getStockTransItemDlts(String order_id)
		{
			List<Stock_Transfer_Item_Dtls> modelList=stock_Transfer_Item_DtlsRepository.getStockTransItemDlts(order_id);
			
				
			Type listType=new TypeToken<List<Stock_Transfer_Item_DtlsDTO>>() {}.getType();
			
			List<Stock_Transfer_Item_DtlsDTO> stkTrItmDtls=new ModelMapper().map(modelList,listType);
			
			return stkTrItmDtls;
		}	
		
		public List<Stock_transfer_resource_costDTO> getStockTransReCost(String order_id)
		{
			List<Stock_transfer_resource_cost> modelList=stock_transfer_resource_costRepository.getStockTransReCost(order_id);
			
			Type listType=new TypeToken<List<Stock_transfer_resource_costDTO>>() {}.getType();
			
			List<Stock_transfer_resource_costDTO> stkTrcost=new ModelMapper().map(modelList,listType);
			
			return stkTrcost;
		}
		
		public List<Stock_transfer_doc> getStockTransDoc(String order_id)
		{
			List<Stock_transfer_doc> modelList=stock_transfer_docRepository.getStockTransDoc(order_id);
			
				
			Type listType=new TypeToken<List<Stock_transfer_doc>>() {}.getType();
			
			List<Stock_transfer_doc> stkTrnsDoc=new ModelMapper().map(modelList,listType);
			
			return stkTrnsDoc;
		}
		
		public Stock_transfer_terminationsDTO getStkTransTerms(String order_id){

			Stock_transfer_terminations modelList=stock_transfer_terminationsRepository.getStkTransTerms(order_id);

			Type listType = new TypeToken<Stock_transfer_terminationsDTO>() {}.getType();

			Stock_transfer_terminationsDTO stkTraTerms= new ModelMapper().map(modelList,listType);
			
			return stkTraTerms;
		}
		
		public List<Stock_transfer_terminations_dynDTO> getStockTransTermDtls(String order_id){
			List<Stock_transfer_terminations_dyn> modelList=new ArrayList<Stock_transfer_terminations_dyn>();
			
			modelList.addAll(stock_transfer_terminations_dynRepository.getStockTransTermDtls(order_id));
				
			Type listType=new TypeToken<List<Stock_transfer_terminations_dynDTO>>() {}.getType();
			
			List<Stock_transfer_terminations_dynDTO> stkTrnsTermDtls=new ModelMapper().map(modelList,listType);
			
			return stkTrnsTermDtls;
		}
		
		@Transactional
		public Stock_Transfer deleteStocktransferOrder(Stock_Transfer stkOrder,Long id) {

			Optional<Stock_Transfer> op = stock_TransferRepository.findById(id);
			Stock_Transfer sOrder=stock_TransferRepository.getStockTransferDetails(id);
			
			LocalDateTime ldt = LocalDateTime.now();
			Stock_Transfer stkOrderStatus=new Stock_Transfer();
			
			if(op.get().isLoadunload_status() == true) {
				System.err.println("Delete is not Possible !!!");
				return stkOrderStatus;
			}else {
				System.err.println("Delete Successfully !!!");
				
				if(op.isPresent())	{
					sOrder.setId(id);
				}
				
				sOrder.setModified_type("DELETED");
				sOrder.setInserted_by(op.get().getInserted_by());
				sOrder.setInserted_on(op.get().getInserted_on());
				sOrder.setUpdated_by(op.get().getUpdated_by());
				sOrder.setUpdated_on(op.get().getUpdated_on());
				sOrder.setDeleted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
				sOrder.setDeleted_on(ldt);
				
				stock_TransferRepository.stockTransferItemDetailsUpdate(op.get().getOrder_id());
				
				stock_TransferRepository.stockTransferTransInfoUpdate(op.get().getOrder_id());
				
				stock_TransferRepository.stockTransferSummaryUpdate(op.get().getOrder_id());
				
				stock_TransferRepository.stockTransferSummaryDynUpdate(op.get().getOrder_id());
				
				stock_TransferRepository.stockTransferResourceCostUpdate(op.get().getOrder_id());
				
				stock_TransferRepository.stockTransferDocUpdate(op.get().getOrder_id());
				
				stock_TransferRepository.stockTransferTerminationsUpdate(op.get().getOrder_id());
				
				stock_TransferRepository.StockTransferTerminationsDynUpdate(op.get().getOrder_id());
				
				return stock_TransferRepository.save(sOrder);	
			}
		}
		
		public Stock_Transfer getReceivingBuLoadingAdvice(String order_id){

			Wm_loading_advice refid =wm_loading_adviceRepository.getLoadingDetails(order_id);
			
			Stock_Transfer modelList=stock_TransferRepository.getReceivingBuLoadingAdvice(refid.getReferance_id());
			
			return modelList;
		}
		
	public Stock_Transfer getOrderNumberForChallan(String ref_id,String ref_type){

		Stock_Transfer modelList = new Stock_Transfer();
		if(ref_type.compareToIgnoreCase("Goods Stock Transfer")==0)
		{
			 modelList=stock_TransferRepository.getStkOrder(ref_id);
		}
		if(ref_type.compareToIgnoreCase("Loading Advice")==0)
		{
			Wm_loading_advice load_Order =wm_loading_adviceRepository.getLoadingDetails(ref_id);
			modelList.setOrder_no(load_Order.getAdvice_no());
			modelList.setOrder_date(load_Order.getAdvice_date());
		}
		
		return modelList;
	}
	
	public Stock_Transfer getStockOrdByUnloadCode(String reference_id,String reference_status) 
	{
		Stock_Transfer orderdetails=new Stock_Transfer();
		
		if(reference_status.compareToIgnoreCase("Direct")==0)
		{
			Stk_Transfer_Challan challan = stockTransferChallanRepository.getStkOrderDetails(reference_id);
			
			if(challan.getRef_type().compareToIgnoreCase("Open Stock Transfer Challan")==0)
			{
				orderdetails.setOrder_no("Open Stock Transfer Challan");
				orderdetails.setOrder_date("");
			}
			else
			{
				Stock_Transfer challanOrder = stock_TransferRepository.getReceivingBuLoadingAdvice(challan.getReference_id());
				
				orderdetails.setOrder_no(challanOrder.getOrder_no());
				orderdetails.setOrder_date(challanOrder.getOrder_date());
			}
		}
		
		if(reference_status.compareToIgnoreCase("Goods Stock Transfer")==0)
		{
			Stk_Transfer_Challan challan = stockTransferChallanRepository.getStkOrderDetails(reference_id);
			
			Stock_Transfer challanOrder = stock_TransferRepository.getReceivingBuLoadingAdvice(challan.getReference_id());
			
			orderdetails.setOrder_no(challanOrder.getOrder_no());
			orderdetails.setOrder_date(challanOrder.getOrder_date());
		}
		
		if(reference_status.compareToIgnoreCase("Stock Transfer")==0)
		{
			Wm_unload_advice unload=wm_unload_adviceRepository.getUnloadId(reference_id);
			
			Stk_Transfer_Challan challan = stockTransferChallanRepository.getStkOrderDetails(unload.getReferance_id());
			
			Stock_Transfer challanOrder = stock_TransferRepository.getReceivingBuLoadingAdvice(challan.getReference_id());
			
			orderdetails.setOrder_no(challanOrder.getOrder_no());
			orderdetails.setOrder_date(challanOrder.getOrder_date());
		}
		
		return orderdetails;
	}
}