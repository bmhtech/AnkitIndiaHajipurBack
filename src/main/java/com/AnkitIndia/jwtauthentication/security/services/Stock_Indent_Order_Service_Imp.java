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
import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_BP_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Broker_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stock_Indent_Item_Details;
import com.AnkitIndia.jwtauthentication.model.Stock_Indent_Order;
//import com.AnkitIndia.jwtauthentication.model.Stock_Indent_Item_Details;
import com.AnkitIndia.jwtauthentication.model.Stock_Indent_Terminations;
import com.AnkitIndia.jwtauthentication.model.Stock_Indent_Terminations_dyn;
import com.AnkitIndia.jwtauthentication.model.Stock_Indent_docs;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_InvoiceRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_IndentOrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Stock_Indent_TerminationsRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_Item_DetailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_OrderDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_TerminationsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_Terminations_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_docsDTO;

@Service
public class Stock_Indent_Order_Service_Imp implements Stock_Indent_Order_Service {
	
	@Autowired
	Stock_IndentOrderRepository stock_IndentOrderRepository;
	
	@Autowired
	Stock_Indent_TerminationsRepository stock_Indent_TerminationsRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public SequenceIdDTO getSTISequenceId(String idate)
	{
		int slno=0;String prefix="STI";
		String sno=stock_IndentOrderRepository.getSTISequenceId(idate);
		
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
	public Stock_Indent_Order save(Stock_Indent_Order sIndentOrder) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(stock_IndentOrderRepository.countId() != null ) {
			slno=Long.parseLong(stock_IndentOrderRepository.countId());
		}
		String prefix="STI";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		sIndentOrder.setIndent_id(gen_sno);	
		
		/*Start checking transaction no for unique */
		System.err.println("First:>>"+sIndentOrder.getIndent_no());
		long nslno=0;String tprefix="STI";
		String tsno=stock_IndentOrderRepository.getSTISequenceId(sIndentOrder.getIndent_date());
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = sIndentOrder.getIndent_date().split("-");
		tprefix=tprefix+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		sIndentOrder.setIndent_no(gen_tslno);
		System.err.println("Last:>>>"+sIndentOrder.getIndent_no());
		/*End checking transaction no for unique */
		
		sIndentOrder.setModified_type("INSERTED");
		sIndentOrder.setInserted_by(userRepository.getUserDetails(sIndentOrder.getUsername()).getName());
		sIndentOrder.setInserted_on(ldt);
		sIndentOrder.setUpdated_by("NA");
		sIndentOrder.setUpdated_on(ldt);
		sIndentOrder.setDeleted_by("NA");
		sIndentOrder.setDeleted_on(ldt);
		
		if(sIndentOrder.getBusiness_unit().compareTo("0") !=0 && sIndentOrder.getBusiness_unit().compareTo("") !=0 && sIndentOrder.getBusiness_unit() !=null) {
			sIndentOrder.setBusiness_unitname(companyBusinessUnitMasterRepository.CompanyBUAddress(sIndentOrder.getBusiness_unit()).getBusinessunit_name());
		}else {sIndentOrder.setBusiness_unitname("None");}
		
		Set<Stock_Indent_Item_Details> stkIndItmDtls=new HashSet<Stock_Indent_Item_Details>();
		stkIndItmDtls.addAll(sIndentOrder.getStock_Indent_Item_Details());
		for(Stock_Indent_Item_Details sIndItmDtls:stkIndItmDtls) 
		{
			sIndItmDtls.setIndent_id(gen_sno);	
			sIndItmDtls.setIndent_no(sIndentOrder.getIndent_no());
			
			sIndItmDtls.setItem_name(item_masterRepository.itemNameById(sIndItmDtls.getItem_code()).getItem_name());
			if(sIndItmDtls.getPacking_item().compareTo("")!=0 && sIndItmDtls.getPacking_item().compareTo("0")!=0) {
				sIndItmDtls.setPacking_item_name(item_masterRepository.itemNameById(sIndItmDtls.getPacking_item()).getItem_name());
			}
			sIndItmDtls.setFin_year(sIndentOrder.getInserted_by());
			sIndItmDtls.setModified_type("INSERTED");
			sIndItmDtls.setInserted_by(sIndentOrder.getInserted_by());
			sIndItmDtls.setInserted_on(sIndentOrder.getInserted_on());
			sIndItmDtls.setUpdated_by("NA");
			sIndItmDtls.setUpdated_on(ldt);
			sIndItmDtls.setDeleted_by("NA");
			sIndItmDtls.setDeleted_on(ldt);
		}
		sIndentOrder.setStock_Indent_Item_Details(stkIndItmDtls);
		
		
		Set<Stock_Indent_docs> stkIndDocs=new HashSet<Stock_Indent_docs>();
		stkIndDocs.addAll(sIndentOrder.getStock_Indent_docs());
		for(Stock_Indent_docs stkItmDcs:stkIndDocs) 
		{
			stkItmDcs.setIndent_id(gen_sno);	
			stkItmDcs.setIndent_no(sIndentOrder.getIndent_no());
			stkItmDcs.setFin_year(sIndentOrder.getInserted_by());
			stkItmDcs.setModified_type("INSERTED");
			stkItmDcs.setInserted_by(sIndentOrder.getInserted_by());
			stkItmDcs.setInserted_on(sIndentOrder.getInserted_on());
			stkItmDcs.setUpdated_by("NA");
			stkItmDcs.setUpdated_on(ldt);
			stkItmDcs.setDeleted_by("NA");
			stkItmDcs.setDeleted_on(ldt);
		}
		sIndentOrder.setStock_Indent_docs(stkIndDocs);
		
		
		Set<Stock_Indent_Terminations_dyn> stkIndTerDyn=new HashSet<Stock_Indent_Terminations_dyn>();
		stkIndTerDyn.addAll(sIndentOrder.getStock_Indent_Terminations_dyn());
		for(Stock_Indent_Terminations_dyn stkInTerDy:stkIndTerDyn) 
		{
			stkInTerDy.setIndent_id(gen_sno);	
			stkInTerDy.setIndent_no(sIndentOrder.getIndent_no());
			stkInTerDy.setFin_year(sIndentOrder.getInserted_by());
			stkInTerDy.setModified_type("INSERTED");
			stkInTerDy.setInserted_by(sIndentOrder.getInserted_by());
			stkInTerDy.setInserted_on(sIndentOrder.getInserted_on());
			stkInTerDy.setUpdated_by("NA");
			stkInTerDy.setUpdated_on(ldt);
			stkInTerDy.setDeleted_by("NA");
			stkInTerDy.setDeleted_on(ldt);
		}
		sIndentOrder.setStock_Indent_Terminations_dyn(stkIndTerDyn);
		
		Set<Stock_Indent_Terminations> stkIndTer=new HashSet<Stock_Indent_Terminations>();
		stkIndTer.add(sIndentOrder.getStock_Indent_Terminations());
		for(Stock_Indent_Terminations stIndTer:stkIndTer) 
		{
			stIndTer.setIndent_id(gen_sno);	
			stIndTer.setIndent_no(sIndentOrder.getIndent_no());
			stIndTer.setFin_year(sIndentOrder.getInserted_by());
			stIndTer.setModified_type("INSERTED");
			stIndTer.setInserted_by(sIndentOrder.getInserted_by());
			stIndTer.setInserted_on(sIndentOrder.getInserted_on());
			stIndTer.setUpdated_by("NA");
			stIndTer.setUpdated_on(ldt);
			stIndTer.setDeleted_by("NA");
			stIndTer.setDeleted_on(ldt);
		}
		if(!stkIndTer.isEmpty())
		{
			sIndentOrder.setStock_Indent_Terminations(stkIndTer.iterator().next());
		}
		
		return stock_IndentOrderRepository.save(sIndentOrder);
		
	}
	
	public List<Stock_Indent_Order> findAll()
	{
		List<Stock_Indent_Order> indOrdList=new ArrayList<Stock_Indent_Order>();
		indOrdList.addAll(stock_IndentOrderRepository.findStkIndOrds());
		for (int x=0;x<indOrdList.size();x++) {
			indOrdList.get(x).setIndent_date(Utility.convertDateDDMMYYYY(indOrdList.get(x).getIndent_date()));
			indOrdList.get(x).setValid_until(Utility.convertDateDDMMYYYY(indOrdList.get(x).getValid_until()));
		}
		return indOrdList;
	}
	
	public Stock_Indent_Order findOne(long id){
		Optional<Stock_Indent_Order> op=this.stock_IndentOrderRepository.findById(id);
		return op.get();
	}
	
	public List<Stock_Indent_Item_DetailsDTO> getStkIndentOrderDetailsList(String indent_id)
	{
		List<Stock_Indent_Order> modelList=new ArrayList<Stock_Indent_Order>();
		
		modelList.addAll(stock_IndentOrderRepository.getStkIndentOrderDetailsList(indent_id));
			
		Type listType=new TypeToken<List<Stock_Indent_Item_DetailsDTO>>() {}.getType();
		
		List<Stock_Indent_Item_DetailsDTO> stkIndItmOrdList=new ModelMapper().map(modelList,listType);
		
		return stkIndItmOrdList;
	}
	
	public List<Stock_Indent_docsDTO> getStkIndentDocsList(String indent_id)
	{
		List<Stock_Indent_Order> modelList=new ArrayList<Stock_Indent_Order>();
		
		modelList.addAll(stock_IndentOrderRepository.getStkIndentDocsList(indent_id));
			
		Type listType=new TypeToken<List<Stock_Indent_docsDTO>>() {}.getType();
		
		List<Stock_Indent_docsDTO> stkIndDocList=new ModelMapper().map(modelList,listType);
		
		return stkIndDocList;
	}
	
	public List<Stock_Indent_OrderDTO> getStkIndOrder(){
		List<Stock_Indent_Order> modelList=stock_IndentOrderRepository.getStkIndOrder();
		
		Type listType=new TypeToken<List<Stock_Indent_OrderDTO>>() {}.getType();
		
		List<Stock_Indent_OrderDTO> desc=new ModelMapper().map(modelList, listType);
		
		return desc;
	}
	
	public List<Stock_Indent_Terminations_dynDTO> getStkIndentTermDyn(String indent_id)
	{
		List<Stock_Indent_Order> modelList=new ArrayList<Stock_Indent_Order>();
		
		modelList.addAll(stock_IndentOrderRepository.getStkIndentTermDyn(indent_id));
			
		Type listType=new TypeToken<List<Stock_Indent_Terminations_dynDTO>>() {}.getType();
		
		List<Stock_Indent_Terminations_dynDTO> stkIndTerdyn=new ModelMapper().map(modelList,listType);
		
		return stkIndTerdyn;
	}
	
	public Stock_Indent_TerminationsDTO getStkIndTer(String indent_id){

		Stock_Indent_Terminations modelList=stock_Indent_TerminationsRepository.getStkIndTer(indent_id);

		Type listType = new TypeToken<Stock_Indent_TerminationsDTO>() {}.getType();

		Stock_Indent_TerminationsDTO stkIndTer= new ModelMapper().map(modelList,listType);
		return stkIndTer;
	}
	
	public Stock_Indent_OrderDTO getStkIndODR(String indent_id)
	{
		Stock_Indent_Order modelList=stock_IndentOrderRepository.getStkIndODR(indent_id);
		
		Type listType=new TypeToken<Stock_Indent_OrderDTO>() {}.getType();
		
		Stock_Indent_OrderDTO stkIndOrd=new ModelMapper().map(modelList,listType);
		
		return stkIndOrd;
	}

}