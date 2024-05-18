package com.AnkitIndia.jwtauthentication.security.services;


import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.NumToWord;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Item_master_pack_mat_tag;
import com.AnkitIndia.jwtauthentication.model.Item_rate_dtls;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Broker_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Docs;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Item_Dtls_for_jobwork;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Item_Dtls_for_jobwork_price;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Party_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Shipment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Summary;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Summary_dyn;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Terms_Con;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Trans_Chgs_dyn;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Trans_Info;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation;
import com.AnkitIndia.jwtauthentication.model.Sales_transaction;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_itm_dtls;
import com.AnkitIndia.jwtauthentication.repository.Broker_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partner_brokerRepository;
import com.AnkitIndia.jwtauthentication.repository.Delivery_challanRepository;
import com.AnkitIndia.jwtauthentication.repository.Invoice_typeRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_Service_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_master_pack_mat_tagRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_rate_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.JobWorkItemAllocationRepository;
import com.AnkitIndia.jwtauthentication.repository.Return_approval_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_Broker_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_DocsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_Party_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_Shipment_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_SummaryRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_Summary_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_Terms_ConRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_Trans_Chgs_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_Trans_InfoRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_QuotationRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_transactionRepository;
import com.AnkitIndia.jwtauthentication.repository.Tax_code_detailsRepository;
import com.AnkitIndia.jwtauthentication.repository.Trans_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_adviceRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_loading_advice_itm_dtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Wm_unload_adviceRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_OrderDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.ResponsemsgDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Broker_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_DocsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_SummaryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Summary_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Terms_ConDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Order_Trans_InfoDTO;


@Service
@Repository
public class Sales_OrderService_Imp implements Sales_OrderService {
	
	@Autowired
	Sales_OrderRepository sales_OrderRepository;
	
	@Autowired
	Sales_Order_Party_DtlsRepository sales_Order_Party_DtlsRepository;
	
	@Autowired
	Sales_Order_Shipment_DtlsRepository sales_Order_Shipment_DtlsRepository;
	
	@Autowired
	Sales_Order_Trans_InfoRepository sales_Order_Trans_InfoRepository;
	
	@Autowired
	Sales_Order_Terms_ConRepository sales_Order_Terms_ConRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	Sales_Order_Item_DtlsRepository sales_Order_Item_DtlsRepository;
	
	@Autowired
	Sales_Order_Broker_DtlsRepository sales_Order_Broker_DtlsRepository;
	
	@Autowired
	Sales_Order_Summary_dynRepository sales_Order_Summary_dynRepository;
	
	@Autowired
	Sales_Order_DocsRepository sales_Order_DocsRepository;
	
	@Autowired
	Sales_Order_SummaryRepository sales_Order_SummaryRepository;
	
	@Autowired
	Wm_loading_adviceRepository wm_loading_adviceRepository;
	
	@Autowired
	Wm_loading_advice_itm_dtlsRepository wm_loading_advice_itm_dtlsRepository;
	
	@Autowired
	Invoice_typeRepository invoice_typeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Sales_transactionRepository sales_transactionRepository;
	
	@Autowired
	Cust_bussiness_partner_brokerRepository cust_bussiness_partner_brokerRepository;
	
	@Autowired
	Return_approval_noteRepository return_approval_noteRepository;
	
	@Autowired
	Delivery_challanRepository delivery_challanRepository;
	
	@Autowired
	Item_master_pack_mat_tagRepository item_master_pack_mat_tagRepository;
	
	@Autowired
	Trans_bussiness_partnerRepository trans_bussiness_partnerRepository;
	
	@Autowired
	Sales_Order_Trans_Chgs_dynRepository sales_Order_Trans_Chgs_dynRepository;
	
	@Autowired
	Broker_masterRepository broker_masterRepository;
	
	@Autowired
	Sales_QuotationRepository sales_QuotationRepository;
	
	@Autowired
	Item_Service_masterRepository item_Service_masterRepository;
	
	@Autowired
	Tax_code_detailsRepository tax_code_detailsRepository;
	
	@Autowired
	JobWorkItemAllocationRepository jobWorkItemAllocationRepository;
	
	@Autowired
	Wm_unload_adviceRepository wm_unload_adviceRepository;
	
	
	public SalesSequenceIdDTO getSalesOrdSequenceId(String prefix,String orderdate,String type)
	{
		int slno=0;
		String sno=sales_OrderRepository.countSalesOrder(orderdate,type);
		if(type.compareTo("Formal")==0) {type="FOR";}
		if(type.compareTo("Informal")==0) {type="INFOR";}
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		prefix=prefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		//System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SalesSequenceIdDTO>() {}.getType();
		
		SalesSequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	public SalesSequenceIdDTO getSalesOrdSequenceIdWarehouse(String prefix,String orderdate)
	{
		int slno=0;
		String sno=sales_OrderRepository.countSalesOrderwarehouse(orderdate);
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		prefix=prefix+"-RFW-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		//System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SalesSequenceIdDTO>() {}.getType();
		
		SalesSequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	
	
   @Transactional
   public Sales_Order createEffectiveSalesOrder(Sales_Order sOrder)
   {
	LocalDateTime ldt = LocalDateTime.now();
	
	long slno=0;
	if(sales_OrderRepository.countId() != null ) {
		slno=Long.parseLong(sales_OrderRepository.countId());
	}
	String prefix="SO";
	String gen_sno=UniqueID.uniqueid(prefix,slno);
	sOrder.setOrder_id(gen_sno);
	
	if(sOrder.getInv_type().compareToIgnoreCase("INV00005")==0)
	{
		long nslno=0;String tprefix="SO";
		String tsno=sales_OrderRepository.countSalesOrderwarehouse(sOrder.getOrder_date());
		
		
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = sOrder.getOrder_date().split("-");
		tprefix=tprefix+"-RFW-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		sOrder.setOrder_no(gen_tslno);
	}
	else 
	{
		long nslno=0;String type="",tprefix="SO";
		String tsno=sales_OrderRepository.countSalesOrder(sOrder.getOrder_date(),sOrder.getOrder_type());
		if(sOrder.getOrder_type().compareTo("Formal")==0) {type="FOR";}
		if(sOrder.getOrder_type().compareTo("Informal")==0) {type="INFOR";}
		
		if(tsno != null ) {
			nslno=Integer.parseInt(tsno);
		}
		String date[] = sOrder.getOrder_date().split("-");
		tprefix=tprefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		sOrder.setOrder_no(gen_tslno);
	}

	
	
	sOrder.setModified_type("INSERTED");
	sOrder.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
	sOrder.setInserted_on(ldt);
	sOrder.setUpdated_by("NA");
	sOrder.setUpdated_on(ldt);
	sOrder.setDeleted_by("NA");
	sOrder.setDeleted_on(ldt);
	sOrder.setSales_returnstatus("NO");
	sOrder.setSalereturn_id("NA");
	sOrder.setOrderdate(sOrder.getOrder_date());
	sOrder.setOrderno(sOrder.getOrder_no());
	sOrder.setTolerancecheckpoint("Yes");
	sOrder.setRatedateentry("0000-00-00T00:00");
	if(sOrder.getOrder_type().compareToIgnoreCase("Formal")==0) 
	{
		sOrder.setCust_channel_list(sOrder.getCustomer());
	}
	else 
	{
		sOrder.setCust_channel_list(sOrder.getCust_channel_list());
	}
	

	
	
	
	if(sOrder.getRef_type().compareTo("Open Sales Order")!=0) {
		//System.out.println("Other Orders");	
	}else {sOrder.setReferance_id(sOrder.getOrder_id());}

	Set<Sales_Order_Item_Dtls> itemSet = new HashSet<Sales_Order_Item_Dtls>();
	itemSet.addAll(sOrder.getSales_Order_Item_Dtls());
	for(Sales_Order_Item_Dtls itemDts : itemSet)
	{
		itemDts.setOrder_id(gen_sno);
		itemDts.setOrder_no(sOrder.getOrder_no());
		itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
		if(Utility.isNullOrEmpty(itemDts.getPacking())) {
			itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
		}else{itemDts.setPacking_name("0");};
		itemDts.setCompany_id(sOrder.getCompany_id());
		itemDts.setFin_year(sOrder.getFin_year());
		itemDts.setModified_type("INSERTED");
		itemDts.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
		itemDts.setInserted_on(sOrder.getInserted_on());
		itemDts.setUpdated_by("NA");
		itemDts.setUpdated_on(ldt);
		itemDts.setDeleted_by("NA");
		itemDts.setDeleted_on(ldt);
	}
	sOrder.setSales_Order_Item_Dtls(itemSet);
	
	//For Sales_Transaction
	Set<Sales_transaction> stSet = new HashSet<Sales_transaction>();
	if(Utility.isNullOrEmpty(sOrder.getBusiness_unit())) {
		for(Sales_Order_Item_Dtls itmDtls:itemSet)
		{
			if(Utility.isNullOrEmpty(itmDtls.getItem_code())) {
				Sales_transaction sTransaction=new Sales_transaction(sOrder.getBusiness_unit(), itmDtls.getItem_code(), itmDtls.getPacking(),itmDtls.getQuantity(), itmDtls.getSquantity(),
						itmDtls.getQuantity(), itmDtls.getSquantity(),0.00,0,0.00,0,itmDtls.getPacking_list(), gen_sno, sOrder.getFin_year(), sOrder.getCompany_id(),
						sOrder.getUsername(), sOrder.getModified_type(), sOrder.getInserted_on(), sOrder.getInserted_by(), sOrder.getUpdated_on(),
						sOrder.getUpdated_by());
				
				stSet.add(sTransaction);
			}else {System.err.println("Not in Block !!!!!!!!!");}
		}
	}
	sales_transactionRepository.saveAll(stSet);
	
	Set<Sales_Order_Broker_Dtls> brokerSet = new HashSet<Sales_Order_Broker_Dtls>();
	brokerSet.addAll(sOrder.getSales_Order_Broker_Dtls());
	for(Sales_Order_Broker_Dtls brokerDts : brokerSet)
	{
		brokerDts.setOrder_id(gen_sno);
		
		//brokerDts.setBroker_name(cust_bussiness_partner_brokerRepository.custBrokerRetriveListNew(brokerDts.getP_code()).getVen_name());
		brokerDts.setBroker_name(broker_masterRepository.brokerNameByCode(brokerDts.getBroker_code()).getName());
		brokerDts.setOrder_no(sOrder.getOrder_no());
		brokerDts.setCompany_id(sOrder.getCompany_id());
		brokerDts.setFin_year(sOrder.getFin_year());
		brokerDts.setModified_type("INSERTED");
		brokerDts.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
		brokerDts.setInserted_on(sOrder.getInserted_on());
		brokerDts.setUpdated_by("NA");
		brokerDts.setUpdated_on(ldt);
		brokerDts.setDeleted_by("NA");
		brokerDts.setDeleted_on(ldt);
	
	}
	sOrder.setSales_Order_Broker_Dtls(brokerSet);
	
	Set<Sales_Order_Summary> summSet = new HashSet<Sales_Order_Summary>();
	summSet.add(sOrder.getSales_Order_Summary());
	for(Sales_Order_Summary party : summSet)
	{
		party.setOrder_id(gen_sno);
		party.setOrder_no(sOrder.getOrder_no());
		party.setCompany_id(sOrder.getCompany_id());
		party.setFin_year(sOrder.getFin_year());
		party.setModified_type("INSERTED");
		party.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
		party.setInserted_on(sOrder.getInserted_on());
		party.setUpdated_by("NA");
		party.setUpdated_on(ldt);
		party.setDeleted_by("NA");
		party.setDeleted_on(ldt);
	}
	if(!summSet.isEmpty()) {
		sOrder.setSales_Order_Summary(summSet.iterator().next());
	}
	
	Set<Sales_Order_Summary_dyn> summdSet = new HashSet<Sales_Order_Summary_dyn>();
	summdSet.addAll(sOrder.getSales_Order_Summary_dyn());
	for(Sales_Order_Summary_dyn summddtls : summdSet)
	{
		summddtls.setOrder_id(gen_sno);
		summddtls.setOrder_no(sOrder.getOrder_no());
		summddtls.setCompany_id(sOrder.getCompany_id());
		summddtls.setFin_year(sOrder.getFin_year());
		summddtls.setModified_type("INSERTED");
		summddtls.setInserted_by(sOrder.getInserted_by());
		summddtls.setInserted_on(sOrder.getInserted_on());
		summddtls.setUpdated_by("NA");
		summddtls.setUpdated_on(ldt);
		summddtls.setDeleted_by("NA");
		summddtls.setDeleted_on(ldt);
	}
	sOrder.setSales_Order_Summary_dyn(summdSet);
	
	Set<Sales_Order_Trans_Info> transSet = new HashSet<Sales_Order_Trans_Info>();
	transSet.add(sOrder.getSales_Order_Trans_Info());
	for(Sales_Order_Trans_Info trans : transSet)
	{
		trans.setOrder_id(gen_sno);
		trans.setOrder_no(sOrder.getOrder_no());
		
		System.out.println("CHECK  :: "+trans.getTrans_code());
		if(Utility.isNullOrEmpty(trans.getTrans_code())) 
		{
			if(trans.getTrans_code().compareToIgnoreCase("0")==0 || trans.getTrans_code().compareToIgnoreCase("NA")==0)
			{
				trans.setTransporter_name("0");
			}
			else
			{
				trans.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(trans.getTrans_code()).getBp_name());
			}
		}
		else{trans.setTransporter_name("0");};
		
		trans.setCompany_id(sOrder.getCompany_id());
		trans.setFin_year(sOrder.getFin_year());
		trans.setModified_type("INSERTED");
		trans.setInserted_by(sOrder.getInserted_by());
		trans.setInserted_on(sOrder.getInserted_on());
		trans.setUpdated_by("NA");
		trans.setUpdated_on(ldt);
		trans.setDeleted_by("NA");
		trans.setDeleted_on(ldt);
	}
	if(!transSet.isEmpty())	{
		sOrder.setSales_Order_Trans_Info(transSet.iterator().next());
	}
	
	Set<Sales_Order_Trans_Chgs_dyn> chargedynSet = new HashSet<Sales_Order_Trans_Chgs_dyn>();
	chargedynSet.addAll(sOrder.getSales_Order_Trans_Chgs_dyn());
	for(Sales_Order_Trans_Chgs_dyn chgdyn : chargedynSet)
	{
		chgdyn.setOrder_id(gen_sno);
		chgdyn.setOrder_no(sOrder.getOrder_no());
		chgdyn.setCompany_id(sOrder.getCompany_id());
		chgdyn.setFin_year(sOrder.getFin_year());
		chgdyn.setModified_type("INSERTED");
		chgdyn.setInserted_by(sOrder.getInserted_by());
		chgdyn.setInserted_on(sOrder.getInserted_on());
		chgdyn.setUpdated_by("NA");
		chgdyn.setUpdated_on(ldt);
		chgdyn.setDeleted_by("NA");
		chgdyn.setDeleted_on(ldt);
	}
	sOrder.setSales_Order_Trans_Chgs_dyn(chargedynSet);
	
	Set<Sales_Order_Party_Dtls> bpSet = new HashSet<Sales_Order_Party_Dtls>();
	bpSet.addAll(sOrder.getSales_Order_Party_Dtls());
	for(Sales_Order_Party_Dtls bpdtls : bpSet)
	{
		bpdtls.setOrder_id(gen_sno);
		bpdtls.setOrder_no(sOrder.getOrder_no());
		if(Utility.isNullOrEmpty(bpdtls.getP_code())){
			bpdtls.setP_name(cust_bussiness_partnerRepository.getCustomer(bpdtls.getP_code()).getCp_name());
		}else {bpdtls.setP_name("0");}
		bpdtls.setCompany_id(sOrder.getCompany_id());
		bpdtls.setFin_year(sOrder.getFin_year());
		bpdtls.setModified_type("INSERTED");
		bpdtls.setInserted_by(sOrder.getInserted_by());
		bpdtls.setInserted_on(sOrder.getInserted_on());
		bpdtls.setUpdated_by("NA");
		bpdtls.setUpdated_on(ldt);
		bpdtls.setDeleted_by("NA");
		bpdtls.setDeleted_on(ldt);
	}
	sOrder.setSales_Order_Party_Dtls(bpSet);
	
	Set<Sales_Order_Terms_Con> termSet = new HashSet<Sales_Order_Terms_Con>();
	termSet.add(sOrder.getSales_Order_Terms_Con());
	for(Sales_Order_Terms_Con termdtls : termSet)
	{
		termdtls.setOrder_id(gen_sno);
		termdtls.setOrder_no(sOrder.getOrder_no());
		termdtls.setCompany_id(sOrder.getCompany_id());
		termdtls.setFin_year(sOrder.getFin_year());
		termdtls.setModified_type("INSERTED");
		termdtls.setInserted_by(sOrder.getInserted_by());
		termdtls.setInserted_on(sOrder.getInserted_on());
		termdtls.setUpdated_by("NA");
		termdtls.setUpdated_on(ldt);
		termdtls.setDeleted_by("NA");
		termdtls.setDeleted_on(ldt);
	}
	if(!termSet.isEmpty())	{
		sOrder.setSales_Order_Terms_Con(termSet.iterator().next());
	}
	
	Set<Sales_Order_Shipment_Dtls> shipSet = new HashSet<Sales_Order_Shipment_Dtls>();
	shipSet.add(sOrder.getSales_Order_Shipment_Dtls());
	for(Sales_Order_Shipment_Dtls shipdtls : shipSet)
	{
		shipdtls.setOrder_id(gen_sno);
		shipdtls.setOrder_no(sOrder.getOrder_no());
		shipdtls.setCompany_id(sOrder.getCompany_id());
		shipdtls.setFin_year(sOrder.getFin_year());
		shipdtls.setModified_type("INSERTED");
		shipdtls.setInserted_by(sOrder.getInserted_by());
		shipdtls.setInserted_on(sOrder.getInserted_on());
		shipdtls.setUpdated_by("NA");
		shipdtls.setUpdated_on(ldt);
		shipdtls.setDeleted_by("NA");
		shipdtls.setDeleted_on(ldt);
	}
	if(!shipSet.isEmpty())	{
		sOrder.setSales_Order_Shipment_Dtls(shipSet.iterator().next());
	}
	
	Set<Sales_Order_Docs> docSet = new HashSet<Sales_Order_Docs>();
	docSet.addAll(sOrder.getSales_Order_Docs());
	for(Sales_Order_Docs docDts : docSet)
	{
		docDts.setOrder_id(gen_sno);
		docDts.setOrder_no(sOrder.getOrder_no());
		docDts.setCompany_id(sOrder.getCompany_id());
		docDts.setFin_year(sOrder.getFin_year());
		docDts.setModified_type("INSERTED");
		docDts.setInserted_by(sOrder.getInserted_by());
		docDts.setInserted_on(sOrder.getInserted_on());
		docDts.setUpdated_by("NA");
		docDts.setUpdated_on(ldt);
		docDts.setDeleted_by("NA");
		docDts.setDeleted_on(ldt);
	}
	sOrder.setSales_Order_Docs(docSet);
	
	/*Set<Sales_Order_Termination> tSet = new HashSet<Sales_Order_Termination>();
	tSet.add(sOrder.getSales_Order_Termination());
	for(Sales_Order_Termination termdtls : tSet)
	{
		termdtls.setOrder_id(gen_sno);
		termdtls.setOrder_no(sOrder.getOrder_no());
		termdtls.setCompany_id(sOrder.getCompany_id());
		termdtls.setFin_year(sOrder.getFin_year());
		termdtls.setModified_type("INSERTED");
		termdtls.setInserted_by(sOrder.getInserted_by());
		termdtls.setInserted_on(sOrder.getInserted_on());
		termdtls.setUpdated_by("NA");
		termdtls.setUpdated_on(ldt);
		termdtls.setDeleted_by("NA");
		termdtls.setDeleted_on(ldt);
	}
	if(!tSet.isEmpty())
	{
		sOrder.setSales_Order_Termination(tSet.iterator().next());
	}*/
	return sales_OrderRepository.save(sOrder);
   }
	
	
	@Transactional
	public Sales_Order save(Sales_Order sOrder)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(sales_OrderRepository.countId() != null ) {
			slno=Long.parseLong(sales_OrderRepository.countId());
		}
		String prefix="SO";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		sOrder.setOrder_id(gen_sno);
		
		if(sOrder.getInv_type().compareToIgnoreCase("INV00005")==0)
		{
			long nslno=0;String tprefix="SO";
			String tsno=sales_OrderRepository.countSalesOrderwarehouse(sOrder.getOrder_date());
			
			
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			String date[] = sOrder.getOrder_date().split("-");
			tprefix=tprefix+"-RFW-"+date[2]+date[1]+date[0].substring(2,4)+"-";
			String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
			sOrder.setOrder_no(gen_tslno);
		}
		else 
		{
			long nslno=0;String type="",tprefix="SO";
			String tsno=sales_OrderRepository.countSalesOrder(sOrder.getOrder_date(),sOrder.getOrder_type());
			if(sOrder.getOrder_type().compareTo("Formal")==0) {type="FOR";}
			if(sOrder.getOrder_type().compareTo("Informal")==0) {type="INFOR";}
			
			if(tsno != null ) {
				nslno=Integer.parseInt(tsno);
			}
			String date[] = sOrder.getOrder_date().split("-");
			tprefix=tprefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
			String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
			sOrder.setOrder_no(gen_tslno);
		}
		
		

		
		
		sOrder.setModified_type("INSERTED");
		sOrder.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
		sOrder.setInserted_on(ldt);
		sOrder.setUpdated_by("NA");
		sOrder.setUpdated_on(ldt);
		sOrder.setDeleted_by("NA");
		sOrder.setDeleted_on(ldt);
		sOrder.setSales_returnstatus("NO");
		sOrder.setSalereturn_id("NA");
		sOrder.setOrderdate(sOrder.getOrder_date());
		sOrder.setOrderno(sOrder.getOrder_no());
		 LocalDateTime date = LocalDateTime.now();
		 String currentdatesaleorder = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
		
		sOrder.setRatedateentry(currentdatesaleorder);	
		
		
		if(Utility.isNullOrEmpty(sOrder.getTolerancecheckpoint())) 
		{
			
		}
		else
		{
			sOrder.setTolerancecheckpoint("Yes");
		};
		
		if(sOrder.getOrder_type().compareToIgnoreCase("Formal")==0) 
		{
			sOrder.setCust_channel_list(sOrder.getCustomer());
		}
		else 
		{
			sOrder.setCust_channel_list(sOrder.getCust_channel_list());
		}
		
		if(Utility.isNullOrEmpty(sOrder.getCust_channel()))		
		{
			
		}
		else
		{
			sOrder.setCust_channel_list(sOrder.getCustomer());
		}
		
		if(sOrder.getRef_type().compareTo("Sales Quotation")==0) 
		{
			sales_QuotationRepository.updatequationstationorder(sOrder.getReferance_id());	
		}
		
		
		if(sOrder.getRef_type().compareTo("Open Sales Order")!=0) {
			//System.out.println("Other Orders");	
		}else {sOrder.setReferance_id(sOrder.getOrder_id());}
	
		
		if(sOrder.getInv_type().compareToIgnoreCase("INV00003")==0)
		{
			sOrder.getSales_Order_Item_Dtls().clear();
			sOrder.getSales_Order_Summary_dyn().clear();
			
			sOrder.setTotal_job_amt(sOrder.getTotal_job_amt());
			System.out.println("else::");
			Set<Sales_Order_Item_Dtls_for_jobwork> jobwork = new HashSet<Sales_Order_Item_Dtls_for_jobwork>();
			jobwork.addAll(sOrder.getSales_Order_Item_Dtls_for_jobwork());
			for(Sales_Order_Item_Dtls_for_jobwork jobdtls : jobwork)
			{
				jobdtls.setOrder_id(gen_sno);
				jobdtls.setOrder_no(sOrder.getOrder_no());
				
				if(Utility.isNullOrEmpty(jobdtls.getJob_item())) {
					jobdtls.setJob_item_name(item_masterRepository.itemNameById(jobdtls.getJob_item()).getItem_name());
				}else{jobdtls.setJob_item_name("0");};
				
				if(Utility.isNullOrEmpty(jobdtls.getJob_packing())) {
					jobdtls.setJob_packing_name(item_masterRepository.itemNameById(jobdtls.getJob_packing()).getItem_name());
				}else{jobdtls.setJob_packing_name("0");};
				
				jobdtls.setCompany_id(sOrder.getCompany_id());
				jobdtls.setFin_year(sOrder.getFin_year());
				jobdtls.setModified_type("INSERTED");
				jobdtls.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
				jobdtls.setInserted_on(sOrder.getInserted_on());
				jobdtls.setUpdated_by("NA");
				jobdtls.setUpdated_on(ldt);
				jobdtls.setDeleted_by("NA");
				jobdtls.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Item_Dtls_for_jobwork(jobwork);
			
			Set<Sales_Order_Item_Dtls_for_jobwork_price> serviceItem = new HashSet<Sales_Order_Item_Dtls_for_jobwork_price>();
			serviceItem.addAll(sOrder.getSales_Order_Item_Dtls_for_jobwork_price());
			for(Sales_Order_Item_Dtls_for_jobwork_price service : serviceItem)
			{
				service.setOrder_id(gen_sno);
				service.setOrder_no(sOrder.getOrder_no());
				
				if(Utility.isNullOrEmpty(service.getItem_service())) {
					service.setItem_service_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getService_name());
				}else{service.setItem_service_name("0");};
				
				if(Utility.isNullOrEmpty(service.getTaxcode())) {
					service.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(service.getTaxcode()).getTax_name());
				}else{service.setTaxcode_name("0");};
				
				service.setCompany_id(sOrder.getCompany_id());
				service.setFin_year(sOrder.getFin_year());
				service.setModified_type("INSERTED");
				service.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
				service.setInserted_on(sOrder.getInserted_on());
				service.setUpdated_by("NA");
				service.setUpdated_on(ldt);
				service.setDeleted_by("NA");
				service.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Item_Dtls_for_jobwork_price(serviceItem);
		}
		else
		{
			System.out.println(sOrder.getSales_Order_Item_Dtls_for_jobwork().size());
			if(sOrder.getSales_Order_Item_Dtls_for_jobwork().size()>0) 
			{
				sOrder.setTotal_job_amt("0");
				sOrder.getSales_Order_Item_Dtls_for_jobwork().clear();
				sOrder.getSales_Order_Item_Dtls_for_jobwork_price().clear();
			}
			
			System.out.println("else::");
			Set<Sales_Order_Item_Dtls> itemSet = new HashSet<Sales_Order_Item_Dtls>();
			itemSet.addAll(sOrder.getSales_Order_Item_Dtls());
			for(Sales_Order_Item_Dtls itemDts : itemSet)
			{
				itemDts.setOrder_id(gen_sno);
				itemDts.setOrder_no(sOrder.getOrder_no());
				itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
				if(Utility.isNullOrEmpty(itemDts.getPacking())) {
					itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
				}else{itemDts.setPacking_name("0");};
				itemDts.setCompany_id(sOrder.getCompany_id());
				itemDts.setFin_year(sOrder.getFin_year());
				itemDts.setModified_type("INSERTED");
				itemDts.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
				itemDts.setInserted_on(sOrder.getInserted_on());
				itemDts.setUpdated_by("NA");
				itemDts.setUpdated_on(ldt);
				itemDts.setDeleted_by("NA");
				itemDts.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Item_Dtls(itemSet);
			
			//For Sales_Transaction
			Set<Sales_transaction> stSet = new HashSet<Sales_transaction>();
			if(Utility.isNullOrEmpty(sOrder.getBusiness_unit())) {
				for(Sales_Order_Item_Dtls itmDtls:itemSet)
				{
					if(Utility.isNullOrEmpty(itmDtls.getItem_code())) {
						Sales_transaction sTransaction=new Sales_transaction(sOrder.getBusiness_unit(), itmDtls.getItem_code(), itmDtls.getPacking(),itmDtls.getQuantity(), itmDtls.getSquantity(),
								itmDtls.getQuantity(), itmDtls.getSquantity(),0.00,0,0.00,0,itmDtls.getPacking_list(), gen_sno, sOrder.getFin_year(), sOrder.getCompany_id(),
								sOrder.getUsername(), sOrder.getModified_type(), sOrder.getInserted_on(), sOrder.getInserted_by(), sOrder.getUpdated_on(),
								sOrder.getUpdated_by());
						
						stSet.add(sTransaction);
					}else {System.err.println("Not in Block !!!!!!!!!");}
				}
			}
			sales_transactionRepository.saveAll(stSet);
			
			Set<Sales_Order_Summary> summSet = new HashSet<Sales_Order_Summary>();
			summSet.add(sOrder.getSales_Order_Summary());
			for(Sales_Order_Summary party : summSet)
			{
				party.setOrder_id(gen_sno);
				party.setOrder_no(sOrder.getOrder_no());
				party.setCompany_id(sOrder.getCompany_id());
				party.setFin_year(sOrder.getFin_year());
				party.setModified_type("INSERTED");
				party.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
				party.setInserted_on(sOrder.getInserted_on());
				party.setUpdated_by("NA");
				party.setUpdated_on(ldt);
				party.setDeleted_by("NA");
				party.setDeleted_on(ldt);
			}
			if(!summSet.isEmpty()) {
				sOrder.setSales_Order_Summary(summSet.iterator().next());
			}
			
			Set<Sales_Order_Summary_dyn> summdSet = new HashSet<Sales_Order_Summary_dyn>();
			summdSet.addAll(sOrder.getSales_Order_Summary_dyn());
			for(Sales_Order_Summary_dyn summddtls : summdSet)
			{
				summddtls.setOrder_id(gen_sno);
				summddtls.setOrder_no(sOrder.getOrder_no());
				summddtls.setCompany_id(sOrder.getCompany_id());
				summddtls.setFin_year(sOrder.getFin_year());
				summddtls.setModified_type("INSERTED");
				summddtls.setInserted_by(sOrder.getInserted_by());
				summddtls.setInserted_on(sOrder.getInserted_on());
				summddtls.setUpdated_by("NA");
				summddtls.setUpdated_on(ldt);
				summddtls.setDeleted_by("NA");
				summddtls.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Summary_dyn(summdSet);
			
		}
		
		
		
		
		Set<Sales_Order_Broker_Dtls> brokerSet = new HashSet<Sales_Order_Broker_Dtls>();
		brokerSet.addAll(sOrder.getSales_Order_Broker_Dtls());
		for(Sales_Order_Broker_Dtls brokerDts : brokerSet)
		{
			brokerDts.setOrder_id(gen_sno);
			
			//brokerDts.setBroker_name(cust_bussiness_partner_brokerRepository.custBrokerRetriveListNew(brokerDts.getP_code()).getVen_name());
			brokerDts.setBroker_name(broker_masterRepository.brokerNameByCode(brokerDts.getBroker_code()).getName());
			brokerDts.setOrder_no(sOrder.getOrder_no());
			brokerDts.setCompany_id(sOrder.getCompany_id());
			brokerDts.setFin_year(sOrder.getFin_year());
			brokerDts.setModified_type("INSERTED");
			brokerDts.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
			brokerDts.setInserted_on(sOrder.getInserted_on());
			brokerDts.setUpdated_by("NA");
			brokerDts.setUpdated_on(ldt);
			brokerDts.setDeleted_by("NA");
			brokerDts.setDeleted_on(ldt);
		
		}
		sOrder.setSales_Order_Broker_Dtls(brokerSet);
		
		
		
		Set<Sales_Order_Trans_Info> transSet = new HashSet<Sales_Order_Trans_Info>();
		transSet.add(sOrder.getSales_Order_Trans_Info());
		for(Sales_Order_Trans_Info trans : transSet)
		{
			trans.setOrder_id(gen_sno);
			trans.setOrder_no(sOrder.getOrder_no());
			
			System.out.println("CHECK  :: "+trans.getTrans_code());
			if(Utility.isNullOrEmpty(trans.getTrans_code())) 
			{
				if(trans.getTrans_code().compareToIgnoreCase("0")==0 || trans.getTrans_code().compareToIgnoreCase("NA")==0)
				{
					trans.setTransporter_name("0");
				}
				else
				{
					trans.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(trans.getTrans_code()).getBp_name());
				}
			}
			else{trans.setTransporter_name("0");};
			
			trans.setCompany_id(sOrder.getCompany_id());
			trans.setFin_year(sOrder.getFin_year());
			trans.setModified_type("INSERTED");
			trans.setInserted_by(sOrder.getInserted_by());
			trans.setInserted_on(sOrder.getInserted_on());
			trans.setUpdated_by("NA");
			trans.setUpdated_on(ldt);
			trans.setDeleted_by("NA");
			trans.setDeleted_on(ldt);
		}
		if(!transSet.isEmpty())	{
			sOrder.setSales_Order_Trans_Info(transSet.iterator().next());
		}
		
		Set<Sales_Order_Trans_Chgs_dyn> chargedynSet = new HashSet<Sales_Order_Trans_Chgs_dyn>();
		chargedynSet.addAll(sOrder.getSales_Order_Trans_Chgs_dyn());
		for(Sales_Order_Trans_Chgs_dyn chgdyn : chargedynSet)
		{
			chgdyn.setOrder_id(gen_sno);
			chgdyn.setOrder_no(sOrder.getOrder_no());
			chgdyn.setCompany_id(sOrder.getCompany_id());
			chgdyn.setFin_year(sOrder.getFin_year());
			chgdyn.setModified_type("INSERTED");
			chgdyn.setInserted_by(sOrder.getInserted_by());
			chgdyn.setInserted_on(sOrder.getInserted_on());
			chgdyn.setUpdated_by("NA");
			chgdyn.setUpdated_on(ldt);
			chgdyn.setDeleted_by("NA");
			chgdyn.setDeleted_on(ldt);
		}
		sOrder.setSales_Order_Trans_Chgs_dyn(chargedynSet);
		
		Set<Sales_Order_Party_Dtls> bpSet = new HashSet<Sales_Order_Party_Dtls>();
		bpSet.addAll(sOrder.getSales_Order_Party_Dtls());
		for(Sales_Order_Party_Dtls bpdtls : bpSet)
		{
			bpdtls.setOrder_id(gen_sno);
			bpdtls.setOrder_no(sOrder.getOrder_no());
			if(Utility.isNullOrEmpty(bpdtls.getP_code())){
				bpdtls.setP_name(cust_bussiness_partnerRepository.getCustomer(bpdtls.getP_code()).getCp_name());
			}else {bpdtls.setP_name("0");}
			bpdtls.setCompany_id(sOrder.getCompany_id());
			bpdtls.setFin_year(sOrder.getFin_year());
			bpdtls.setModified_type("INSERTED");
			bpdtls.setInserted_by(sOrder.getInserted_by());
			bpdtls.setInserted_on(sOrder.getInserted_on());
			bpdtls.setUpdated_by("NA");
			bpdtls.setUpdated_on(ldt);
			bpdtls.setDeleted_by("NA");
			bpdtls.setDeleted_on(ldt);
		}
		sOrder.setSales_Order_Party_Dtls(bpSet);
		
		Set<Sales_Order_Terms_Con> termSet = new HashSet<Sales_Order_Terms_Con>();
		termSet.add(sOrder.getSales_Order_Terms_Con());
		for(Sales_Order_Terms_Con termdtls : termSet)
		{
			termdtls.setOrder_id(gen_sno);
			termdtls.setOrder_no(sOrder.getOrder_no());
			termdtls.setCompany_id(sOrder.getCompany_id());
			termdtls.setFin_year(sOrder.getFin_year());
			termdtls.setModified_type("INSERTED");
			termdtls.setInserted_by(sOrder.getInserted_by());
			termdtls.setInserted_on(sOrder.getInserted_on());
			termdtls.setUpdated_by("NA");
			termdtls.setUpdated_on(ldt);
			termdtls.setDeleted_by("NA");
			termdtls.setDeleted_on(ldt);
		}
		if(!termSet.isEmpty())	{
			sOrder.setSales_Order_Terms_Con(termSet.iterator().next());
		}
		
		Set<Sales_Order_Shipment_Dtls> shipSet = new HashSet<Sales_Order_Shipment_Dtls>();
		shipSet.add(sOrder.getSales_Order_Shipment_Dtls());
		for(Sales_Order_Shipment_Dtls shipdtls : shipSet)
		{
			shipdtls.setOrder_id(gen_sno);
			shipdtls.setOrder_no(sOrder.getOrder_no());
			if(Utility.isNullOrEmpty(shipdtls.getShip_addr())){
				
			}else {shipdtls.setShip_addr("0");}
			shipdtls.setCompany_id(sOrder.getCompany_id());
			shipdtls.setFin_year(sOrder.getFin_year());
			shipdtls.setModified_type("INSERTED");
			shipdtls.setInserted_by(sOrder.getInserted_by());
			shipdtls.setInserted_on(sOrder.getInserted_on());
			shipdtls.setUpdated_by("NA");
			shipdtls.setUpdated_on(ldt);
			shipdtls.setDeleted_by("NA");
			shipdtls.setDeleted_on(ldt);
		}
		if(!shipSet.isEmpty())	{
			sOrder.setSales_Order_Shipment_Dtls(shipSet.iterator().next());
		}
		
		Set<Sales_Order_Docs> docSet = new HashSet<Sales_Order_Docs>();
		docSet.addAll(sOrder.getSales_Order_Docs());
		for(Sales_Order_Docs docDts : docSet)
		{
			docDts.setOrder_id(gen_sno);
			docDts.setOrder_no(sOrder.getOrder_no());
			docDts.setCompany_id(sOrder.getCompany_id());
			docDts.setFin_year(sOrder.getFin_year());
			docDts.setModified_type("INSERTED");
			docDts.setInserted_by(sOrder.getInserted_by());
			docDts.setInserted_on(sOrder.getInserted_on());
			docDts.setUpdated_by("NA");
			docDts.setUpdated_on(ldt);
			docDts.setDeleted_by("NA");
			docDts.setDeleted_on(ldt);
		}
		sOrder.setSales_Order_Docs(docSet);
		
		/*Set<Sales_Order_Termination> tSet = new HashSet<Sales_Order_Termination>();
		tSet.add(sOrder.getSales_Order_Termination());
		for(Sales_Order_Termination termdtls : tSet)
		{
			termdtls.setOrder_id(gen_sno);
			termdtls.setOrder_no(sOrder.getOrder_no());
			termdtls.setCompany_id(sOrder.getCompany_id());
			termdtls.setFin_year(sOrder.getFin_year());
			termdtls.setModified_type("INSERTED");
			termdtls.setInserted_by(sOrder.getInserted_by());
			termdtls.setInserted_on(sOrder.getInserted_on());
			termdtls.setUpdated_by("NA");
			termdtls.setUpdated_on(ldt);
			termdtls.setDeleted_by("NA");
			termdtls.setDeleted_on(ldt);
		}
		if(!tSet.isEmpty())
		{
			sOrder.setSales_Order_Termination(tSet.iterator().next());
		}*/
		return sales_OrderRepository.save(sOrder);
	}
	
	@Transactional
	public Sales_Order updateEffectiveSalesOrder(Sales_Order sOrder,Long id)
	{

		Optional<Sales_Order> op = sales_OrderRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Sales_Order sOrderStatus=new Sales_Order();
		
		if(op.get().isLoading_status() == true) {
			System.err.println("Update is not Possible !!!");
			return sOrderStatus;
		}else {
			System.err.println("Update is Continue !!!");
			if(op.isPresent())	{
				sOrder.setId(id);
			}
			sOrder.setOrder_no(op.get().getOrder_no());
			sOrder.setModified_type("INSERTED");
			sOrder.setInserted_by(op.get().getInserted_by());
			sOrder.setInserted_on(op.get().getInserted_on());
			sOrder.setUpdated_by(sOrder.getUsername());
			sOrder.setUpdated_on(ldt);
			sOrder.setDeleted_by("NA");
			sOrder.setDeleted_on(ldt);
			sOrder.setOrderdate(op.get().getOrder_date());
			sOrder.setOrderno(op.get().getOrder_no());
			sOrder.setLoading_status(op.get().isLoading_status());
			sOrder.setSales_returnstatus(op.get().getSales_returnstatus());
			sOrder.setSalereturn_id(op.get().getSalereturn_id());
			sOrder.setTolerancecheckpoint("Yes");
			
			if(sOrder.getRef_type().compareTo("Open Sales Order")!=0) {
				//System.out.println("Other Orders");	
			}else {sOrder.setReferance_id(op.get().getOrder_id());}
			
			sales_Order_Item_DtlsRepository.sales_Order_Item_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			
			Set<Sales_Order_Item_Dtls> itemSet = new HashSet<Sales_Order_Item_Dtls>();
			itemSet.addAll(sOrder.getSales_Order_Item_Dtls());
			for(Sales_Order_Item_Dtls itemDts : itemSet)
			{
				itemDts.setOrder_id(op.get().getOrder_id());
				itemDts.setOrder_no(sOrder.getOrder_no());
				itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
				if(Utility.isNullOrEmpty(itemDts.getPacking())) {
					itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
				}else {itemDts.setPacking_name("0");}
				itemDts.setCompany_id(sOrder.getCompany_id());
				itemDts.setFin_year(sOrder.getFin_year());
				itemDts.setModified_type("INSERTED");
				itemDts.setInserted_by(sOrder.getInserted_by());
				itemDts.setInserted_on(sOrder.getInserted_on());
				itemDts.setUpdated_by(sOrder.getUsername());
				itemDts.setUpdated_on(sOrder.getUpdated_on());
				itemDts.setDeleted_by("NA");
				itemDts.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Item_Dtls(itemSet);
			
			//Delete Sales_Transaction
			List<Sales_transaction> sOrdTrans=new ArrayList<Sales_transaction>();
			sOrdTrans.addAll(sales_transactionRepository.getSalesStocks(op.get().getOrder_id()));
			
			sOrdTrans.forEach((sTrans) -> {
				sales_transactionRepository.deleteSales_transactions(sTrans.getReference_id(),sTrans.getBusiness_unit(),sTrans.getItem_id(),sTrans.getPacking_id());
			});
			
			//For Sales_Transaction
			Set<Sales_transaction> stSet = new HashSet<Sales_transaction>();
			if(Utility.isNullOrEmpty(sOrder.getBusiness_unit())) {
				for(Sales_Order_Item_Dtls itmDtls:itemSet)
				{
					if(Utility.isNullOrEmpty(itmDtls.getItem_code())) {
						Sales_transaction sTransaction=new Sales_transaction(sOrder.getBusiness_unit(), itmDtls.getItem_code(), itmDtls.getPacking(),itmDtls.getQuantity(), itmDtls.getSquantity(),
								itmDtls.getQuantity(), itmDtls.getSquantity(),0.00,0,0.00,0,itmDtls.getPacking_list(), op.get().getOrder_id(), sOrder.getFin_year(), sOrder.getCompany_id(),
								sOrder.getUsername(), sOrder.getModified_type(), sOrder.getInserted_on(), sOrder.getInserted_by(), sOrder.getUpdated_on(),
								sOrder.getUpdated_by());
						
						stSet.add(sTransaction);
					}else {System.err.println("Not in Block !!!!!!!!!");}
				}
			}
			sales_transactionRepository.saveAll(stSet);
					
			sales_Order_Broker_DtlsRepository.sales_Order_Broker_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Broker_Dtls> brokerSet = new HashSet<Sales_Order_Broker_Dtls>();
			brokerSet.addAll(sOrder.getSales_Order_Broker_Dtls());
			for(Sales_Order_Broker_Dtls brokerDts : brokerSet)
			{
				brokerDts.setOrder_id(op.get().getOrder_id());
				
				//brokerDts.setBroker_name(cust_bussiness_partner_brokerRepository.custBrokerRetriveListNew(brokerDts.getP_code()).getVen_name());
				brokerDts.setBroker_name(broker_masterRepository.brokerNameByCode(brokerDts.getBroker_code()).getName());
				brokerDts.setOrder_no(sOrder.getOrder_no());
				brokerDts.setCompany_id(sOrder.getCompany_id());
				brokerDts.setFin_year(sOrder.getFin_year());
				brokerDts.setModified_type("INSERTED");
				brokerDts.setInserted_by(sOrder.getInserted_by());
				brokerDts.setInserted_on(sOrder.getInserted_on());
				brokerDts.setUpdated_by(sOrder.getUpdated_by());
				brokerDts.setUpdated_on(sOrder.getUpdated_on());
				brokerDts.setDeleted_by("NA");
				brokerDts.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Broker_Dtls(brokerSet);
			
			sales_Order_SummaryRepository.sales_Order_Summaryupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Summary> summSet = new HashSet<Sales_Order_Summary>();
			summSet.add(sOrder.getSales_Order_Summary());
			for(Sales_Order_Summary party : summSet)
			{
				party.setOrder_id(op.get().getOrder_id());
				party.setOrder_no(sOrder.getOrder_no());
				party.setCompany_id(sOrder.getCompany_id());
				party.setFin_year(sOrder.getFin_year());
				party.setModified_type("INSERTED");
				party.setInserted_by(sOrder.getInserted_by());
				party.setInserted_on(sOrder.getInserted_on());
				party.setUpdated_by(sOrder.getUpdated_by());
				party.setUpdated_on(sOrder.getUpdated_on());
				party.setDeleted_by("NA");
				party.setDeleted_on(ldt);
			}
			if(!summSet.isEmpty())	{
				sOrder.setSales_Order_Summary(summSet.iterator().next());
			}
			
			sales_Order_Summary_dynRepository.sales_Order_Summary_dynupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Summary_dyn> summdSet = new HashSet<Sales_Order_Summary_dyn>();
			summdSet.addAll(sOrder.getSales_Order_Summary_dyn());
			for(Sales_Order_Summary_dyn summddtls : summdSet)
			{
				summddtls.setOrder_id(op.get().getOrder_id());
				summddtls.setOrder_no(sOrder.getOrder_no());
				summddtls.setCompany_id(sOrder.getCompany_id());
				summddtls.setFin_year(sOrder.getFin_year());
				summddtls.setModified_type("INSERTED");
				summddtls.setInserted_by(sOrder.getInserted_by());
				summddtls.setInserted_on(sOrder.getInserted_on());
				summddtls.setUpdated_by(sOrder.getUpdated_by());
				summddtls.setUpdated_on(sOrder.getUpdated_on());
				summddtls.setDeleted_by("NA");
				summddtls.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Summary_dyn(summdSet);
			
			sales_Order_Trans_InfoRepository.Sales_Order_Trans_Infoupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Trans_Info> transSet = new HashSet<Sales_Order_Trans_Info>();
			transSet.add(sOrder.getSales_Order_Trans_Info());
			for(Sales_Order_Trans_Info trans : transSet)
			{
				trans.setOrder_id(op.get().getOrder_id());
				trans.setOrder_no(sOrder.getOrder_no());
				
				if(Utility.isNullOrEmpty(trans.getTrans_code())) 
				{
					if(trans.getTrans_code().compareToIgnoreCase("0")==0)
					{
						trans.setTransporter_name("0");
					}
					else
					{
						trans.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(trans.getTrans_code()).getBp_name());
					}
				}
				else{trans.setTransporter_name("0");};
				
				trans.setCompany_id(sOrder.getCompany_id());
				trans.setFin_year(sOrder.getFin_year());
				trans.setModified_type("INSERTED");
				trans.setInserted_by(sOrder.getInserted_by());
				trans.setInserted_on(sOrder.getInserted_on());
				trans.setUpdated_by(sOrder.getUpdated_by());
				trans.setUpdated_on(sOrder.getUpdated_on());
				trans.setDeleted_by("NA");
				trans.setDeleted_on(ldt);
			}
			if(!transSet.isEmpty())	{
				sOrder.setSales_Order_Trans_Info(transSet.iterator().next());
			}
			
			sales_Order_Party_DtlsRepository.sales_Order_Party_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Party_Dtls> bpSet = new HashSet<Sales_Order_Party_Dtls>();
			bpSet.addAll(sOrder.getSales_Order_Party_Dtls());
			for(Sales_Order_Party_Dtls bpdtls : bpSet)
			{
				bpdtls.setOrder_id(op.get().getOrder_id());
				bpdtls.setOrder_no(sOrder.getOrder_no());
				if(Utility.isNullOrEmpty(bpdtls.getP_code())) {
					bpdtls.setP_name(cust_bussiness_partnerRepository.getCustomer(bpdtls.getP_code()).getCp_name());
				}else {bpdtls.setP_name("0");}
				bpdtls.setCompany_id(sOrder.getCompany_id());
				bpdtls.setFin_year(sOrder.getFin_year());
				bpdtls.setModified_type("INSERTED");
				bpdtls.setInserted_by(sOrder.getInserted_by());
				bpdtls.setInserted_on(sOrder.getInserted_on());
				bpdtls.setUpdated_by(sOrder.getUpdated_by());
				bpdtls.setUpdated_on(sOrder.getUpdated_on());
				bpdtls.setDeleted_by("NA");
				bpdtls.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Party_Dtls(bpSet);
			
			sales_Order_Trans_Chgs_dynRepository.sales_Order_Trans_Chgs_dynupdate(op.get().getOrder_id());
			Set<Sales_Order_Trans_Chgs_dyn> chargedynSet = new HashSet<Sales_Order_Trans_Chgs_dyn>();
			chargedynSet.addAll(sOrder.getSales_Order_Trans_Chgs_dyn());
			for(Sales_Order_Trans_Chgs_dyn chgdyn : chargedynSet)
			{
				chgdyn.setOrder_id(op.get().getOrder_id());
				chgdyn.setOrder_no(sOrder.getOrder_no());
				chgdyn.setCompany_id(sOrder.getCompany_id());
				chgdyn.setFin_year(sOrder.getFin_year());
				chgdyn.setModified_type("INSERTED");
				chgdyn.setInserted_by(sOrder.getInserted_by());
				chgdyn.setInserted_on(sOrder.getInserted_on());
				chgdyn.setUpdated_by(sOrder.getUpdated_by());
				chgdyn.setUpdated_on(sOrder.getUpdated_on());
				chgdyn.setDeleted_by("NA");
				chgdyn.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Trans_Chgs_dyn(chargedynSet);
			
			sales_Order_Terms_ConRepository.sales_Order_Terms_Conupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Terms_Con> termSet = new HashSet<Sales_Order_Terms_Con>();
			termSet.add(sOrder.getSales_Order_Terms_Con());
			for(Sales_Order_Terms_Con termdtls : termSet)
			{
				termdtls.setOrder_id(op.get().getOrder_id());
				termdtls.setOrder_no(sOrder.getOrder_no());
				termdtls.setCompany_id(sOrder.getCompany_id());
				termdtls.setFin_year(sOrder.getFin_year());
				termdtls.setModified_type("INSERTED");
				termdtls.setInserted_by(sOrder.getInserted_by());
				termdtls.setInserted_on(sOrder.getInserted_on());
				termdtls.setUpdated_by(sOrder.getUpdated_by());
				termdtls.setUpdated_on(sOrder.getUpdated_on());
				termdtls.setDeleted_by("NA");
				termdtls.setDeleted_on(ldt);
			}
			if(!termSet.isEmpty())	{
				sOrder.setSales_Order_Terms_Con(termSet.iterator().next());
			}
			
			sales_Order_Shipment_DtlsRepository.sales_Order_Shipment_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Shipment_Dtls> shipSet = new HashSet<Sales_Order_Shipment_Dtls>();
			shipSet.add(sOrder.getSales_Order_Shipment_Dtls());
			for(Sales_Order_Shipment_Dtls shipdtls : shipSet)
			{
				shipdtls.setOrder_id(op.get().getOrder_id());
				shipdtls.setOrder_no(sOrder.getOrder_no());
				shipdtls.setCompany_id(sOrder.getCompany_id());
				shipdtls.setFin_year(sOrder.getFin_year());
				shipdtls.setModified_type("INSERTED");
				shipdtls.setInserted_by(sOrder.getInserted_by());
				shipdtls.setInserted_on(sOrder.getInserted_on());
				shipdtls.setUpdated_by(sOrder.getUpdated_by());
				shipdtls.setUpdated_on(sOrder.getUpdated_on());
				shipdtls.setDeleted_by("NA");
				shipdtls.setDeleted_on(ldt);
			}
			if(!shipSet.isEmpty())	{
				sOrder.setSales_Order_Shipment_Dtls(shipSet.iterator().next());
			}
			
			sales_Order_DocsRepository.updateSales_Order_Docs(op.get().getOrder_id(),"UPDATED");
			
			Set<Sales_Order_Docs> docSet = new HashSet<Sales_Order_Docs>();
			docSet.addAll(sOrder.getSales_Order_Docs());
			for(Sales_Order_Docs docDts : docSet)
			{
				docDts.setOrder_id(op.get().getOrder_id());
				docDts.setOrder_no(sOrder.getOrder_no());
				docDts.setCompany_id(sOrder.getCompany_id());
				docDts.setFin_year(sOrder.getFin_year());
				docDts.setModified_type("INSERTED");
				docDts.setInserted_by(sOrder.getInserted_by());
				docDts.setInserted_on(sOrder.getInserted_on());
				docDts.setUpdated_by(sOrder.getUpdated_by());
				docDts.setUpdated_on(sOrder.getUpdated_on());
				docDts.setDeleted_by("NA");
				docDts.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Docs(docSet);
			
			return sales_OrderRepository.save(sOrder);	
		}
	}
	
	@Transactional
	public Sales_Order update(Sales_Order sOrder,Long id)
	{
		Optional<Sales_Order> op = sales_OrderRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Sales_Order sOrderStatus=new Sales_Order();
		
		if(op.get().isLoading_status() == true) {
			System.err.println("Update is not Possible !!!");
			return sOrderStatus;
		}else {
			System.err.println("Update is Continue !!!");
			if(op.isPresent())	{
				sOrder.setId(id);
			}
			sOrder.setOrder_no(op.get().getOrder_no());
			sOrder.setModified_type("INSERTED");
			sOrder.setInserted_by(op.get().getInserted_by());
			sOrder.setInserted_on(op.get().getInserted_on());
			sOrder.setUpdated_by(sOrder.getUsername());
			sOrder.setUpdated_on(ldt);
			sOrder.setDeleted_by("NA");
			sOrder.setDeleted_on(ldt);
			sOrder.setOrderdate(op.get().getOrder_date());
			sOrder.setOrderno(op.get().getOrder_no());
			sOrder.setLoading_status(op.get().isLoading_status());
			sOrder.setSales_returnstatus(op.get().getSales_returnstatus());
			sOrder.setSalereturn_id(op.get().getSalereturn_id());
			LocalDateTime date = LocalDateTime.now();
			String currentdatesaleorder = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
			
			sOrder.setRatedateentry(currentdatesaleorder);	
			
			
			if(Utility.isNullOrEmpty(sOrder.getTolerancecheckpoint())) 
			{
				
			}
			else
			{
				sOrder.setTolerancecheckpoint("Yes");
			};
			
			
			
			if(sOrder.getOrder_type().compareToIgnoreCase("Formal")==0) 
			{
				sOrder.setCust_channel_list(sOrder.getCustomer());
			}
			else 
			{
				sOrder.setCust_channel_list(sOrder.getCust_channel_list());
			}
			
			if(Utility.isNullOrEmpty(sOrder.getCust_channel()))		
			{
				
			}
			else
			{
				sOrder.setCust_channel_list(sOrder.getCustomer());
			}
			
			sales_QuotationRepository.revertupdatequationstationorder(op.get().getReferance_id());
			if(sOrder.getRef_type().compareTo("Sales Quotation")==0) 
			{
				sales_QuotationRepository.updatequationstationorder(sOrder.getReferance_id());	
			}
			
			
			if(sOrder.getRef_type().compareTo("Open Sales Order")!=0) {
				//System.out.println("Other Orders");	
			}else {sOrder.setReferance_id(sOrder.getOrder_id());}
		
			
			
			if(sOrder.getRef_type().compareTo("Open Sales Order")!=0) {
				//System.out.println("Other Orders");	
			}else {sOrder.setReferance_id(op.get().getOrder_id());}
			

			if(sOrder.getInv_type().compareToIgnoreCase("INV00003")==0)
			{
				sOrder.getSales_Order_Item_Dtls().clear();
				sOrder.getSales_Order_Summary_dyn().clear();
				
				sOrder.setTotal_job_amt(sOrder.getTotal_job_amt());
				
				sales_OrderRepository.sales_OrderJobItemupdate(op.get().getOrder_id(),"UPDATED");
				
				Set<Sales_Order_Item_Dtls_for_jobwork> jobwork = new HashSet<Sales_Order_Item_Dtls_for_jobwork>();
				jobwork.addAll(sOrder.getSales_Order_Item_Dtls_for_jobwork());
				for(Sales_Order_Item_Dtls_for_jobwork jobdtls : jobwork)
				{
					jobdtls.setOrder_id(op.get().getOrder_id());
					jobdtls.setOrder_no(op.get().getOrder_no());
					
					if(Utility.isNullOrEmpty(jobdtls.getJob_item())) {
						jobdtls.setJob_item_name(item_masterRepository.itemNameById(jobdtls.getJob_item()).getItem_name());
					}else{jobdtls.setJob_item_name("0");};
					
					if(Utility.isNullOrEmpty(jobdtls.getJob_packing())) {
						jobdtls.setJob_packing_name(item_masterRepository.itemNameById(jobdtls.getJob_packing()).getItem_name());
					}else{jobdtls.setJob_packing_name("0");};
					
					jobdtls.setCompany_id(sOrder.getCompany_id());
					jobdtls.setFin_year(sOrder.getFin_year());
					jobdtls.setModified_type("INSERTED");
					jobdtls.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
					jobdtls.setInserted_on(sOrder.getInserted_on());
					jobdtls.setUpdated_by(sOrder.getUsername());
					jobdtls.setUpdated_on(sOrder.getUpdated_on());
					jobdtls.setDeleted_by("NA");
					jobdtls.setDeleted_on(ldt);
				}
				sOrder.setSales_Order_Item_Dtls_for_jobwork(jobwork);
				
				sales_OrderRepository.sales_OrderServiceItemupdate(op.get().getOrder_id(),"UPDATED");
				
				Set<Sales_Order_Item_Dtls_for_jobwork_price> serviceItem = new HashSet<Sales_Order_Item_Dtls_for_jobwork_price>();
				serviceItem.addAll(sOrder.getSales_Order_Item_Dtls_for_jobwork_price());
				for(Sales_Order_Item_Dtls_for_jobwork_price service : serviceItem)
				{
					service.setOrder_id(op.get().getOrder_id());
					service.setOrder_no(op.get().getOrder_no());
					
					if(Utility.isNullOrEmpty(service.getItem_service())) {
						service.setItem_service_name(item_Service_masterRepository.serviceNameById(service.getItem_service()).getService_name());
					}else{service.setItem_service_name("0");};
					
					if(Utility.isNullOrEmpty(service.getTaxcode())) {
						service.setTaxcode_name(tax_code_detailsRepository.getTaxCodeDetails(service.getTaxcode()).getTax_name());
					}else{service.setTaxcode_name("0");};
					
					service.setCompany_id(sOrder.getCompany_id());
					service.setFin_year(sOrder.getFin_year());
					service.setModified_type("INSERTED");
					service.setInserted_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
					service.setInserted_on(sOrder.getInserted_on());
					service.setUpdated_by(sOrder.getUsername());
					service.setUpdated_on(sOrder.getUpdated_on());
					service.setDeleted_by("NA");
					service.setDeleted_on(ldt);
				}
				sOrder.setSales_Order_Item_Dtls_for_jobwork_price(serviceItem);
			}
			else
			{
				//sOrder.setTotal_job_amt("0");
				System.out.println(sOrder.getSales_Order_Item_Dtls_for_jobwork().size());
				if(sOrder.getSales_Order_Item_Dtls_for_jobwork().size()>0) 
				{
					sOrder.setTotal_job_amt("0");
					sOrder.getSales_Order_Item_Dtls_for_jobwork().clear();
					sOrder.getSales_Order_Item_Dtls_for_jobwork_price().clear();
				}
				
				sales_Order_Item_DtlsRepository.sales_Order_Item_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
				
				Set<Sales_Order_Item_Dtls> itemSet = new HashSet<Sales_Order_Item_Dtls>();
				itemSet.addAll(sOrder.getSales_Order_Item_Dtls());
				for(Sales_Order_Item_Dtls itemDts : itemSet)
				{
					itemDts.setOrder_id(op.get().getOrder_id());
					itemDts.setOrder_no(sOrder.getOrder_no());
					itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
					if(Utility.isNullOrEmpty(itemDts.getPacking())) {
						itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
					}else {itemDts.setPacking_name("0");}
					itemDts.setCompany_id(sOrder.getCompany_id());
					itemDts.setFin_year(sOrder.getFin_year());
					itemDts.setModified_type("INSERTED");
					itemDts.setInserted_by(sOrder.getInserted_by());
					itemDts.setInserted_on(sOrder.getInserted_on());
					itemDts.setUpdated_by(sOrder.getUsername());
					itemDts.setUpdated_on(sOrder.getUpdated_on());
					itemDts.setDeleted_by("NA");
					itemDts.setDeleted_on(ldt);
				}
				sOrder.setSales_Order_Item_Dtls(itemSet);
				
				//Delete Sales_Transaction
				List<Sales_transaction> sOrdTrans=new ArrayList<Sales_transaction>();
				sOrdTrans.addAll(sales_transactionRepository.getSalesStocks(op.get().getOrder_id()));
				
				sOrdTrans.forEach((sTrans) -> {
					sales_transactionRepository.deleteSales_transactions(sTrans.getReference_id(),sTrans.getBusiness_unit(),sTrans.getItem_id(),sTrans.getPacking_id());
				});
				
				//For Sales_Transaction
				Set<Sales_transaction> stSet = new HashSet<Sales_transaction>();
				if(Utility.isNullOrEmpty(sOrder.getBusiness_unit())) {
					for(Sales_Order_Item_Dtls itmDtls:itemSet)
					{
						if(Utility.isNullOrEmpty(itmDtls.getItem_code())) {
							Sales_transaction sTransaction=new Sales_transaction(sOrder.getBusiness_unit(), itmDtls.getItem_code(), itmDtls.getPacking(),itmDtls.getQuantity(), itmDtls.getSquantity(),
									itmDtls.getQuantity(), itmDtls.getSquantity(),0.00,0,0.00,0,itmDtls.getPacking_list(), op.get().getOrder_id(), sOrder.getFin_year(), sOrder.getCompany_id(),
									sOrder.getUsername(), sOrder.getModified_type(), sOrder.getInserted_on(), sOrder.getInserted_by(), sOrder.getUpdated_on(),
									sOrder.getUpdated_by());
							
							stSet.add(sTransaction);
						}else {System.err.println("Not in Block !!!!!!!!!");}
					}
				}
				sales_transactionRepository.saveAll(stSet);
				
				sales_Order_SummaryRepository.sales_Order_Summaryupdate(op.get().getOrder_id(),"UPDATED");
				Set<Sales_Order_Summary> summSet = new HashSet<Sales_Order_Summary>();
				summSet.add(sOrder.getSales_Order_Summary());
				for(Sales_Order_Summary party : summSet)
				{
					party.setOrder_id(op.get().getOrder_id());
					party.setOrder_no(sOrder.getOrder_no());
					party.setCompany_id(sOrder.getCompany_id());
					party.setFin_year(sOrder.getFin_year());
					party.setModified_type("INSERTED");
					party.setInserted_by(sOrder.getInserted_by());
					party.setInserted_on(sOrder.getInserted_on());
					party.setUpdated_by(sOrder.getUpdated_by());
					party.setUpdated_on(sOrder.getUpdated_on());
					party.setDeleted_by("NA");
					party.setDeleted_on(ldt);
				}
				if(!summSet.isEmpty())	{
					sOrder.setSales_Order_Summary(summSet.iterator().next());
				}
				
				sales_Order_Summary_dynRepository.sales_Order_Summary_dynupdate(op.get().getOrder_id(),"UPDATED");
				Set<Sales_Order_Summary_dyn> summdSet = new HashSet<Sales_Order_Summary_dyn>();
				summdSet.addAll(sOrder.getSales_Order_Summary_dyn());
				for(Sales_Order_Summary_dyn summddtls : summdSet)
				{
					summddtls.setOrder_id(op.get().getOrder_id());
					summddtls.setOrder_no(sOrder.getOrder_no());
					summddtls.setCompany_id(sOrder.getCompany_id());
					summddtls.setFin_year(sOrder.getFin_year());
					summddtls.setModified_type("INSERTED");
					summddtls.setInserted_by(sOrder.getInserted_by());
					summddtls.setInserted_on(sOrder.getInserted_on());
					summddtls.setUpdated_by(sOrder.getUpdated_by());
					summddtls.setUpdated_on(sOrder.getUpdated_on());
					summddtls.setDeleted_by("NA");
					summddtls.setDeleted_on(ldt);
				}
				sOrder.setSales_Order_Summary_dyn(summdSet);
			}
			
			
			
			
					
			sales_Order_Broker_DtlsRepository.sales_Order_Broker_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Broker_Dtls> brokerSet = new HashSet<Sales_Order_Broker_Dtls>();
			brokerSet.addAll(sOrder.getSales_Order_Broker_Dtls());
			for(Sales_Order_Broker_Dtls brokerDts : brokerSet)
			{
				brokerDts.setOrder_id(op.get().getOrder_id());
				
				//brokerDts.setBroker_name(cust_bussiness_partner_brokerRepository.custBrokerRetriveListNew(brokerDts.getP_code()).getVen_name());
				brokerDts.setBroker_name(broker_masterRepository.brokerNameByCode(brokerDts.getBroker_code()).getName());
				brokerDts.setOrder_no(sOrder.getOrder_no());
				brokerDts.setCompany_id(sOrder.getCompany_id());
				brokerDts.setFin_year(sOrder.getFin_year());
				brokerDts.setModified_type("INSERTED");
				brokerDts.setInserted_by(sOrder.getInserted_by());
				brokerDts.setInserted_on(sOrder.getInserted_on());
				brokerDts.setUpdated_by(sOrder.getUpdated_by());
				brokerDts.setUpdated_on(sOrder.getUpdated_on());
				brokerDts.setDeleted_by("NA");
				brokerDts.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Broker_Dtls(brokerSet);
			
			
			
			sales_Order_Trans_InfoRepository.Sales_Order_Trans_Infoupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Trans_Info> transSet = new HashSet<Sales_Order_Trans_Info>();
			transSet.add(sOrder.getSales_Order_Trans_Info());
			for(Sales_Order_Trans_Info trans : transSet)
			{
				trans.setOrder_id(op.get().getOrder_id());
				trans.setOrder_no(sOrder.getOrder_no());
				
				if(Utility.isNullOrEmpty(trans.getTrans_code())) 
				{
					if(trans.getTrans_code().compareToIgnoreCase("0")==0)
					{
						trans.setTransporter_name("0");
					}
					else
					{
						trans.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(trans.getTrans_code()).getBp_name());
					}
				}
				else{trans.setTransporter_name("0");};
				
				trans.setCompany_id(sOrder.getCompany_id());
				trans.setFin_year(sOrder.getFin_year());
				trans.setModified_type("INSERTED");
				trans.setInserted_by(sOrder.getInserted_by());
				trans.setInserted_on(sOrder.getInserted_on());
				trans.setUpdated_by(sOrder.getUpdated_by());
				trans.setUpdated_on(sOrder.getUpdated_on());
				trans.setDeleted_by("NA");
				trans.setDeleted_on(ldt);
			}
			if(!transSet.isEmpty())	{
				sOrder.setSales_Order_Trans_Info(transSet.iterator().next());
			}
			
			sales_Order_Party_DtlsRepository.sales_Order_Party_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Party_Dtls> bpSet = new HashSet<Sales_Order_Party_Dtls>();
			bpSet.addAll(sOrder.getSales_Order_Party_Dtls());
			for(Sales_Order_Party_Dtls bpdtls : bpSet)
			{
				bpdtls.setOrder_id(op.get().getOrder_id());
				bpdtls.setOrder_no(sOrder.getOrder_no());
				if(Utility.isNullOrEmpty(bpdtls.getP_code())) {
					bpdtls.setP_name(cust_bussiness_partnerRepository.getCustomer(bpdtls.getP_code()).getCp_name());
				}else {bpdtls.setP_name("0");}
				bpdtls.setCompany_id(sOrder.getCompany_id());
				bpdtls.setFin_year(sOrder.getFin_year());
				bpdtls.setModified_type("INSERTED");
				bpdtls.setInserted_by(sOrder.getInserted_by());
				bpdtls.setInserted_on(sOrder.getInserted_on());
				bpdtls.setUpdated_by(sOrder.getUpdated_by());
				bpdtls.setUpdated_on(sOrder.getUpdated_on());
				bpdtls.setDeleted_by("NA");
				bpdtls.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Party_Dtls(bpSet);
			
			sales_Order_Trans_Chgs_dynRepository.sales_Order_Trans_Chgs_dynupdate(op.get().getOrder_id());
			Set<Sales_Order_Trans_Chgs_dyn> chargedynSet = new HashSet<Sales_Order_Trans_Chgs_dyn>();
			chargedynSet.addAll(sOrder.getSales_Order_Trans_Chgs_dyn());
			for(Sales_Order_Trans_Chgs_dyn chgdyn : chargedynSet)
			{
				chgdyn.setOrder_id(op.get().getOrder_id());
				chgdyn.setOrder_no(sOrder.getOrder_no());
				chgdyn.setCompany_id(sOrder.getCompany_id());
				chgdyn.setFin_year(sOrder.getFin_year());
				chgdyn.setModified_type("INSERTED");
				chgdyn.setInserted_by(sOrder.getInserted_by());
				chgdyn.setInserted_on(sOrder.getInserted_on());
				chgdyn.setUpdated_by(sOrder.getUpdated_by());
				chgdyn.setUpdated_on(sOrder.getUpdated_on());
				chgdyn.setDeleted_by("NA");
				chgdyn.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Trans_Chgs_dyn(chargedynSet);
			
			sales_Order_Terms_ConRepository.sales_Order_Terms_Conupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Terms_Con> termSet = new HashSet<Sales_Order_Terms_Con>();
			termSet.add(sOrder.getSales_Order_Terms_Con());
			for(Sales_Order_Terms_Con termdtls : termSet)
			{
				termdtls.setOrder_id(op.get().getOrder_id());
				termdtls.setOrder_no(sOrder.getOrder_no());
				termdtls.setCompany_id(sOrder.getCompany_id());
				termdtls.setFin_year(sOrder.getFin_year());
				termdtls.setModified_type("INSERTED");
				termdtls.setInserted_by(sOrder.getInserted_by());
				termdtls.setInserted_on(sOrder.getInserted_on());
				termdtls.setUpdated_by(sOrder.getUpdated_by());
				termdtls.setUpdated_on(sOrder.getUpdated_on());
				termdtls.setDeleted_by("NA");
				termdtls.setDeleted_on(ldt);
			}
			if(!termSet.isEmpty())	{
				sOrder.setSales_Order_Terms_Con(termSet.iterator().next());
			}
			
			sales_Order_Shipment_DtlsRepository.sales_Order_Shipment_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Shipment_Dtls> shipSet = new HashSet<Sales_Order_Shipment_Dtls>();
			shipSet.add(sOrder.getSales_Order_Shipment_Dtls());
			for(Sales_Order_Shipment_Dtls shipdtls : shipSet)
			{
				shipdtls.setOrder_id(op.get().getOrder_id());
				shipdtls.setOrder_no(sOrder.getOrder_no());
				shipdtls.setCompany_id(sOrder.getCompany_id());
				shipdtls.setFin_year(sOrder.getFin_year());
				shipdtls.setModified_type("INSERTED");
				shipdtls.setInserted_by(sOrder.getInserted_by());
				shipdtls.setInserted_on(sOrder.getInserted_on());
				shipdtls.setUpdated_by(sOrder.getUpdated_by());
				shipdtls.setUpdated_on(sOrder.getUpdated_on());
				shipdtls.setDeleted_by("NA");
				shipdtls.setDeleted_on(ldt);
			}
			if(!shipSet.isEmpty())	{
				sOrder.setSales_Order_Shipment_Dtls(shipSet.iterator().next());
			}
			
			sales_Order_DocsRepository.updateSales_Order_Docs(op.get().getOrder_id(),"UPDATED");
			
			Set<Sales_Order_Docs> docSet = new HashSet<Sales_Order_Docs>();
			docSet.addAll(sOrder.getSales_Order_Docs());
			for(Sales_Order_Docs docDts : docSet)
			{
				docDts.setOrder_id(op.get().getOrder_id());
				docDts.setOrder_no(sOrder.getOrder_no());
				docDts.setCompany_id(sOrder.getCompany_id());
				docDts.setFin_year(sOrder.getFin_year());
				docDts.setModified_type("INSERTED");
				docDts.setInserted_by(sOrder.getInserted_by());
				docDts.setInserted_on(sOrder.getInserted_on());
				docDts.setUpdated_by(sOrder.getUpdated_by());
				docDts.setUpdated_on(sOrder.getUpdated_on());
				docDts.setDeleted_by("NA");
				docDts.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Docs(docSet);
			
			return sales_OrderRepository.save(sOrder);	
		}
	}
	
	@Transactional
	public Sales_Order updateEffectiveSalesOrderWithLoading(Sales_Order sOrder,Long id)
	{

		Optional<Sales_Order> op = sales_OrderRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
	
			if(op.isPresent())	{
				sOrder.setId(id);
			}
			
			sOrder.setOrder_no(op.get().getOrder_no());
			sOrder.setModified_type("INSERTED");
			sOrder.setInserted_by(op.get().getInserted_by());
			sOrder.setInserted_on(op.get().getInserted_on());
			sOrder.setUpdated_by(sOrder.getUsername());
			sOrder.setUpdated_on(ldt);
			sOrder.setDeleted_by("NA");
			sOrder.setDeleted_on(ldt);
			sOrder.setOrderdate(op.get().getOrder_date());
			sOrder.setOrderno(op.get().getOrder_no());
			sOrder.setLoading_status(op.get().isLoading_status());
			sOrder.setSales_returnstatus(op.get().getSales_returnstatus());
			sOrder.setSalereturn_id(op.get().getSalereturn_id());
			
			if(sOrder.getRef_type().compareTo("Open Sales Order")!=0) {
				//System.out.println("Other Orders");	
			}else {sOrder.setReferance_id(op.get().getOrder_id());}
			
			sales_Order_Item_DtlsRepository.sales_Order_Item_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			
			List<Wm_loading_advice_itm_dtls> loadingdetails=new ArrayList<Wm_loading_advice_itm_dtls>();
			loadingdetails.addAll(wm_loading_adviceRepository.getloadingadviceviasaleorder(op.get().getOrder_id()));
			
			wm_loading_adviceRepository.updateloadingbysaleorder(op.get().getOrder_id(),sOrder.getUsername(),ldt);
			
			Set<Sales_Order_Item_Dtls> itemSet = new HashSet<Sales_Order_Item_Dtls>();
			itemSet.addAll(sOrder.getSales_Order_Item_Dtls());
			for(Sales_Order_Item_Dtls itemDts : itemSet)
			{
								
				
				itemDts.setOrder_id(op.get().getOrder_id());
				itemDts.setOrder_no(sOrder.getOrder_no());
				itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
				if(Utility.isNullOrEmpty(itemDts.getPacking())) {
					itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
				}else {itemDts.setPacking_name("0");}
				itemDts.setCompany_id(sOrder.getCompany_id());
				itemDts.setFin_year(sOrder.getFin_year());
				itemDts.setModified_type("INSERTED");
				itemDts.setInserted_by(sOrder.getInserted_by());
				itemDts.setInserted_on(sOrder.getInserted_on());
				itemDts.setUpdated_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
				itemDts.setUpdated_on(sOrder.getUpdated_on());
				itemDts.setDeleted_by("NA");
				itemDts.setDeleted_on(ldt);
				
				if(op.get().isLoading_status() == true) 
				{
					for( int v=0;v<loadingdetails.size();v++) 
					{
						if(loadingdetails.get(v).getItem_code().compareToIgnoreCase(itemDts.getItem_code())==0 && loadingdetails.get(v).getPacking().compareToIgnoreCase(itemDts.getPacking())==0 && loadingdetails.get(v).getOrder_id().compareToIgnoreCase(itemDts.getOrder_id())==0) 
						{
							Wm_loading_advice_itm_dtls loadingitem = new Wm_loading_advice_itm_dtls();
							
							loadingitem.setAdvice_id(loadingdetails.get(v).getAdvice_id());
							loadingitem.setAdvice_no(loadingdetails.get(v).getAdvice_no());
							loadingitem.setAcc_norms(loadingdetails.get(v).getAcc_norms());
							double amount=0.00,discount_amt=0.00,tax_amt=0.00,total_amt=0.00;
							
							if(itemDts.getPrice_based_on().compareToIgnoreCase("Packing")==0) 
							{
								loadingitem.setAmount(itemDts.getPrice()*loadingdetails.get(v).getS_quantity());
								amount=itemDts.getPrice()*loadingdetails.get(v).getS_quantity();
							}
							else 
							{
								loadingitem.setAmount(itemDts.getPrice()* loadingdetails.get(v).getQuantity());
								amount=itemDts.getPrice()* loadingdetails.get(v).getQuantity();
							}
							
						
							loadingitem.setBase_qty(loadingdetails.get(v).getBase_qty());
							loadingitem.setDeleted_by(loadingdetails.get(v).getDeleted_by());
							loadingitem.setDeleted_on(loadingdetails.get(v).getDeleted_on());
							
							if(itemDts.getDiscount_type().compareToIgnoreCase("Uom")==0) 
							{
								loadingitem.setDiscount_amt(loadingdetails.get(v).getDiscount_rate() * loadingdetails.get(v).getMat_wt());
								discount_amt=loadingdetails.get(v).getDiscount_rate() * loadingdetails.get(v).getMat_wt();
							}
							else 
							{
                                loadingitem.setDiscount_amt((amount * loadingdetails.get(v).getDiscount_rate())/100);
								discount_amt=(amount * loadingdetails.get(v).getDiscount_rate())/100;
							}
							
							
							loadingitem.setDiscount_rate(loadingdetails.get(v).getDiscount_rate());
							loadingitem.setDiscount_type(loadingdetails.get(v).getDiscount_type());
							loadingitem.setFin_year(sOrder.getFin_year());
							loadingitem.setHsn_code(loadingdetails.get(v).getHsn_code());
							loadingitem.setInserted_by(loadingdetails.get(v).getInserted_by());
							loadingitem.setInserted_on(loadingdetails.get(v).getInserted_on());
							loadingitem.setItem_code(loadingdetails.get(v).getItem_code());
							loadingitem.setItem_name(loadingdetails.get(v).getItem_name());
							loadingitem.setMat_wt(loadingdetails.get(v).getMat_wt());
							loadingitem.setModified_type("INSERTED");
							loadingitem.setOrder_id(loadingdetails.get(v).getOrder_id());
							loadingitem.setPacking(loadingdetails.get(v).getPacking());
							loadingitem.setPacking_name(loadingdetails.get(v).getPacking_name());
							
							loadingitem.setPrice(itemDts.getPrice());
							
							loadingitem.setPrice_based_on(loadingdetails.get(v).getPrice_based_on());
							loadingitem.setQuantity(loadingdetails.get(v).getQuantity());
							loadingitem.setS_quantity(loadingdetails.get(v).getS_quantity());
							loadingitem.setS_uom(loadingdetails.get(v).getS_uom());
							loadingitem.setSl_no(loadingdetails.get(v).getSl_no());
							loadingitem.setStack_rack(loadingdetails.get(v).getStack_rack());
							loadingitem.setStack_rack_name(loadingdetails.get(v).getStack_rack_name());
						
							
							loadingitem.setTax_amt(((amount- discount_amt)*loadingdetails.get(v).getTax_rate())/100);
							tax_amt=((amount- discount_amt)*loadingdetails.get(v).getTax_rate())/100;
									
									
							loadingitem.setTax_code(loadingdetails.get(v).getTax_code());
							loadingitem.setTax_rate(loadingdetails.get(v).getTax_rate());
							loadingitem.setTolerance(loadingdetails.get(v).getTolerance());
							
							loadingitem.setTotal_amt((amount-discount_amt)+tax_amt);
							total_amt=((amount-discount_amt)+tax_amt);
							
							
							loadingitem.setUom(loadingdetails.get(v).getUom());
							loadingitem.setUpdated_by(sOrder.getUsername());
							loadingitem.setUpdated_on(ldt);
							loadingitem.setUsername(sOrder.getUsername());
							loadingitem.setWarehouse(loadingdetails.get(v).getWarehouse());
							loadingitem.setWarehouse_name(loadingdetails.get(v).getWarehouse_name());
							
							
							
							itemDts.setWm_loading_advice_itm_dtls(loadingitem);
						}
						
					}
					
					
					
				}

				
			}
			sOrder.setSales_Order_Item_Dtls(itemSet);
			
			//Delete Sales_Transaction
			List<Sales_transaction> sOrdTrans=new ArrayList<Sales_transaction>();
			sOrdTrans.addAll(sales_transactionRepository.getSalesStocks(op.get().getOrder_id()));
			
			sOrdTrans.forEach((sTrans) -> {
				sales_transactionRepository.deleteSales_transactions(sTrans.getReference_id(),sTrans.getBusiness_unit(),sTrans.getItem_id(),sTrans.getPacking_id());
			});
			
			//For Sales_Transaction
			Set<Sales_transaction> stSet = new HashSet<Sales_transaction>();
			if(Utility.isNullOrEmpty(sOrder.getBusiness_unit())) {
				for(Sales_Order_Item_Dtls itmDtls:itemSet)
				{
					if(Utility.isNullOrEmpty(itmDtls.getItem_code())) {
						Sales_transaction sTransaction=new Sales_transaction(sOrder.getBusiness_unit(), itmDtls.getItem_code(), itmDtls.getPacking(),itmDtls.getQuantity(), itmDtls.getSquantity(),
								itmDtls.getQuantity(), itmDtls.getSquantity(),0.00,0,0.00,0,itmDtls.getPacking_list(), op.get().getOrder_id(), sOrder.getFin_year(), sOrder.getCompany_id(),
								sOrder.getUsername(), sOrder.getModified_type(), sOrder.getInserted_on(), sOrder.getInserted_by(), sOrder.getUpdated_on(),
								sOrder.getUpdated_by());
						
						stSet.add(sTransaction);
					}else {System.err.println("Not in Block !!!!!!!!!");}
				}
			}
			sales_transactionRepository.saveAll(stSet);
					
			sales_Order_Broker_DtlsRepository.sales_Order_Broker_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Broker_Dtls> brokerSet = new HashSet<Sales_Order_Broker_Dtls>();
			brokerSet.addAll(sOrder.getSales_Order_Broker_Dtls());
			for(Sales_Order_Broker_Dtls brokerDts : brokerSet)
			{
				brokerDts.setOrder_id(op.get().getOrder_id());
				
				brokerDts.setBroker_name(cust_bussiness_partner_brokerRepository.custBrokerRetriveListNew(brokerDts.getP_code()).getVen_name());
				
				brokerDts.setOrder_no(sOrder.getOrder_no());
				brokerDts.setCompany_id(sOrder.getCompany_id());
				brokerDts.setFin_year(sOrder.getFin_year());
				brokerDts.setModified_type("INSERTED");
				brokerDts.setInserted_by(sOrder.getInserted_by());
				brokerDts.setInserted_on(sOrder.getInserted_on());
				brokerDts.setUpdated_by(sOrder.getUpdated_by());
				brokerDts.setUpdated_on(sOrder.getUpdated_on());
				brokerDts.setDeleted_by("NA");
				brokerDts.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Broker_Dtls(brokerSet);
			
			sales_Order_SummaryRepository.sales_Order_Summaryupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Summary> summSet = new HashSet<Sales_Order_Summary>();
			summSet.add(sOrder.getSales_Order_Summary());
			for(Sales_Order_Summary party : summSet)
			{
				party.setOrder_id(op.get().getOrder_id());
				party.setOrder_no(sOrder.getOrder_no());
				party.setCompany_id(sOrder.getCompany_id());
				party.setFin_year(sOrder.getFin_year());
				party.setModified_type("INSERTED");
				party.setInserted_by(sOrder.getInserted_by());
				party.setInserted_on(sOrder.getInserted_on());
				party.setUpdated_by(sOrder.getUpdated_by());
				party.setUpdated_on(sOrder.getUpdated_on());
				party.setDeleted_by("NA");
				party.setDeleted_on(ldt);
			}
			if(!summSet.isEmpty())	{
				sOrder.setSales_Order_Summary(summSet.iterator().next());
			}
			
			sales_Order_Summary_dynRepository.sales_Order_Summary_dynupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Summary_dyn> summdSet = new HashSet<Sales_Order_Summary_dyn>();
			summdSet.addAll(sOrder.getSales_Order_Summary_dyn());
			for(Sales_Order_Summary_dyn summddtls : summdSet)
			{
				summddtls.setOrder_id(op.get().getOrder_id());
				summddtls.setOrder_no(sOrder.getOrder_no());
				summddtls.setCompany_id(sOrder.getCompany_id());
				summddtls.setFin_year(sOrder.getFin_year());
				summddtls.setModified_type("INSERTED");
				summddtls.setInserted_by(sOrder.getInserted_by());
				summddtls.setInserted_on(sOrder.getInserted_on());
				summddtls.setUpdated_by(sOrder.getUpdated_by());
				summddtls.setUpdated_on(sOrder.getUpdated_on());
				summddtls.setDeleted_by("NA");
				summddtls.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Summary_dyn(summdSet);
			
			sales_Order_Trans_Chgs_dynRepository.sales_Order_Trans_Chgs_dynupdate(op.get().getOrder_id());
			Set<Sales_Order_Trans_Chgs_dyn> chargedynSet = new HashSet<Sales_Order_Trans_Chgs_dyn>();
			chargedynSet.addAll(sOrder.getSales_Order_Trans_Chgs_dyn());
			for(Sales_Order_Trans_Chgs_dyn chgdyn : chargedynSet)
			{
				chgdyn.setOrder_id(op.get().getOrder_id());
				chgdyn.setOrder_no(sOrder.getOrder_no());
				chgdyn.setCompany_id(sOrder.getCompany_id());
				chgdyn.setFin_year(sOrder.getFin_year());
				chgdyn.setModified_type("INSERTED");
				chgdyn.setInserted_by(sOrder.getInserted_by());
				chgdyn.setInserted_on(sOrder.getInserted_on());
				chgdyn.setUpdated_by(sOrder.getUpdated_by());
				chgdyn.setUpdated_on(sOrder.getUpdated_on());
				chgdyn.setDeleted_by("NA");
				chgdyn.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Trans_Chgs_dyn(chargedynSet);
			
			sales_Order_Trans_InfoRepository.Sales_Order_Trans_Infoupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Trans_Info> transSet = new HashSet<Sales_Order_Trans_Info>();
			transSet.add(sOrder.getSales_Order_Trans_Info());
			for(Sales_Order_Trans_Info trans : transSet)
			{
				trans.setOrder_id(op.get().getOrder_id());
				trans.setOrder_no(sOrder.getOrder_no());
				
				if(Utility.isNullOrEmpty(trans.getTrans_code())) 
				{
					if(trans.getTrans_code().compareToIgnoreCase("0")==0)
					{
						trans.setTransporter_name("0");
					}
					else
					{
						trans.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(trans.getTrans_code()).getBp_name());
					}
				}
				else{trans.setTransporter_name("0");};
				
				trans.setCompany_id(sOrder.getCompany_id());
				trans.setFin_year(sOrder.getFin_year());
				trans.setModified_type("INSERTED");
				trans.setInserted_by(sOrder.getInserted_by());
				trans.setInserted_on(sOrder.getInserted_on());
				trans.setUpdated_by(sOrder.getUpdated_by());
				trans.setUpdated_on(sOrder.getUpdated_on());
				trans.setDeleted_by("NA");
				trans.setDeleted_on(ldt);
			}
			if(!transSet.isEmpty())	{
				sOrder.setSales_Order_Trans_Info(transSet.iterator().next());
			}
			
			sales_Order_Party_DtlsRepository.sales_Order_Party_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Party_Dtls> bpSet = new HashSet<Sales_Order_Party_Dtls>();
			bpSet.addAll(sOrder.getSales_Order_Party_Dtls());
			for(Sales_Order_Party_Dtls bpdtls : bpSet)
			{
				bpdtls.setOrder_id(op.get().getOrder_id());
				bpdtls.setOrder_no(sOrder.getOrder_no());
				if(Utility.isNullOrEmpty(bpdtls.getP_code())) {
					bpdtls.setP_name(cust_bussiness_partnerRepository.getCustomer(bpdtls.getP_code()).getCp_name());
				}else {bpdtls.setP_name("0");}
				bpdtls.setCompany_id(sOrder.getCompany_id());
				bpdtls.setFin_year(sOrder.getFin_year());
				bpdtls.setModified_type("INSERTED");
				bpdtls.setInserted_by(sOrder.getInserted_by());
				bpdtls.setInserted_on(sOrder.getInserted_on());
				bpdtls.setUpdated_by(sOrder.getUpdated_by());
				bpdtls.setUpdated_on(sOrder.getUpdated_on());
				bpdtls.setDeleted_by("NA");
				bpdtls.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Party_Dtls(bpSet);
			
			sales_Order_Terms_ConRepository.sales_Order_Terms_Conupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Terms_Con> termSet = new HashSet<Sales_Order_Terms_Con>();
			termSet.add(sOrder.getSales_Order_Terms_Con());
			for(Sales_Order_Terms_Con termdtls : termSet)
			{
				termdtls.setOrder_id(op.get().getOrder_id());
				termdtls.setOrder_no(sOrder.getOrder_no());
				termdtls.setCompany_id(sOrder.getCompany_id());
				termdtls.setFin_year(sOrder.getFin_year());
				termdtls.setModified_type("INSERTED");
				termdtls.setInserted_by(sOrder.getInserted_by());
				termdtls.setInserted_on(sOrder.getInserted_on());
				termdtls.setUpdated_by(sOrder.getUpdated_by());
				termdtls.setUpdated_on(sOrder.getUpdated_on());
				termdtls.setDeleted_by("NA");
				termdtls.setDeleted_on(ldt);
			}
			if(!termSet.isEmpty())	{
				sOrder.setSales_Order_Terms_Con(termSet.iterator().next());
			}
			
			sales_Order_Shipment_DtlsRepository.sales_Order_Shipment_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Shipment_Dtls> shipSet = new HashSet<Sales_Order_Shipment_Dtls>();
			shipSet.add(sOrder.getSales_Order_Shipment_Dtls());
			for(Sales_Order_Shipment_Dtls shipdtls : shipSet)
			{
				shipdtls.setOrder_id(op.get().getOrder_id());
				shipdtls.setOrder_no(sOrder.getOrder_no());
				shipdtls.setCompany_id(sOrder.getCompany_id());
				shipdtls.setFin_year(sOrder.getFin_year());
				shipdtls.setModified_type("INSERTED");
				shipdtls.setInserted_by(sOrder.getInserted_by());
				shipdtls.setInserted_on(sOrder.getInserted_on());
				shipdtls.setUpdated_by(sOrder.getUpdated_by());
				shipdtls.setUpdated_on(sOrder.getUpdated_on());
				shipdtls.setDeleted_by("NA");
				shipdtls.setDeleted_on(ldt);
			}
			if(!shipSet.isEmpty())	{
				sOrder.setSales_Order_Shipment_Dtls(shipSet.iterator().next());
			}
			
			sales_Order_DocsRepository.updateSales_Order_Docs(op.get().getOrder_id(),"UPDATED");
			
			Set<Sales_Order_Docs> docSet = new HashSet<Sales_Order_Docs>();
			docSet.addAll(sOrder.getSales_Order_Docs());
			for(Sales_Order_Docs docDts : docSet)
			{
				docDts.setOrder_id(op.get().getOrder_id());
				docDts.setOrder_no(sOrder.getOrder_no());
				docDts.setCompany_id(sOrder.getCompany_id());
				docDts.setFin_year(sOrder.getFin_year());
				docDts.setModified_type("INSERTED");
				docDts.setInserted_by(sOrder.getInserted_by());
				docDts.setInserted_on(sOrder.getInserted_on());
				docDts.setUpdated_by(sOrder.getUpdated_by());
				docDts.setUpdated_on(sOrder.getUpdated_on());
				docDts.setDeleted_by("NA");
				docDts.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Docs(docSet);
			
			return sales_OrderRepository.save(sOrder);	
		
	}
	
	@Transactional
	public Sales_Order updateSalesOrderWithLoading(Sales_Order sOrder,Long id)
	{
		Optional<Sales_Order> op = sales_OrderRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
	
			if(op.isPresent())	{
				sOrder.setId(id);
			}
			
			sOrder.setOrder_no(op.get().getOrder_no());
			sOrder.setModified_type("INSERTED");
			sOrder.setInserted_by(op.get().getInserted_by());
			sOrder.setInserted_on(op.get().getInserted_on());
			sOrder.setUpdated_by(sOrder.getUsername());
			sOrder.setUpdated_on(ldt);
			sOrder.setDeleted_by("NA");
			sOrder.setDeleted_on(ldt);
			sOrder.setOrderdate(op.get().getOrder_date());
			sOrder.setOrderno(op.get().getOrder_no());
			sOrder.setLoading_status(op.get().isLoading_status());
			sOrder.setSales_returnstatus(op.get().getSales_returnstatus());
			sOrder.setSalereturn_id(op.get().getSalereturn_id());
			
			if(sOrder.getRef_type().compareTo("Open Sales Order")!=0) {
				//System.out.println("Other Orders");	
			}else {sOrder.setReferance_id(op.get().getOrder_id());}
			
			sales_Order_Item_DtlsRepository.sales_Order_Item_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			
			List<Wm_loading_advice_itm_dtls> loadingdetails=new ArrayList<Wm_loading_advice_itm_dtls>();
			loadingdetails.addAll(wm_loading_adviceRepository.getloadingadviceviasaleorder(op.get().getOrder_id()));
			
			wm_loading_adviceRepository.updateloadingbysaleorder(op.get().getOrder_id(),sOrder.getUsername(),ldt);
			
			Set<Sales_Order_Item_Dtls> itemSet = new HashSet<Sales_Order_Item_Dtls>();
			itemSet.addAll(sOrder.getSales_Order_Item_Dtls());
			for(Sales_Order_Item_Dtls itemDts : itemSet)
			{
								
				
				itemDts.setOrder_id(op.get().getOrder_id());
				itemDts.setOrder_no(sOrder.getOrder_no());
				itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
				if(Utility.isNullOrEmpty(itemDts.getPacking())) {
					itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
				}else {itemDts.setPacking_name("0");}
				itemDts.setCompany_id(sOrder.getCompany_id());
				itemDts.setFin_year(sOrder.getFin_year());
				itemDts.setModified_type("INSERTED");
				itemDts.setInserted_by(sOrder.getInserted_by());
				itemDts.setInserted_on(sOrder.getInserted_on());
				itemDts.setUpdated_by(userRepository.getUserDetails(sOrder.getUsername()).getName());
				itemDts.setUpdated_on(sOrder.getUpdated_on());
				itemDts.setDeleted_by("NA");
				itemDts.setDeleted_on(ldt);
				
				if(op.get().isLoading_status() == true) 
				{
					for( int v=0;v<loadingdetails.size();v++) 
					{
						if(loadingdetails.get(v).getItem_code().compareToIgnoreCase(itemDts.getItem_code())==0 && loadingdetails.get(v).getPacking().compareToIgnoreCase(itemDts.getPacking())==0 && loadingdetails.get(v).getOrder_id().compareToIgnoreCase(itemDts.getOrder_id())==0) 
						{
							Wm_loading_advice_itm_dtls loadingitem = new Wm_loading_advice_itm_dtls();
							
							loadingitem.setAdvice_id(loadingdetails.get(v).getAdvice_id());
							loadingitem.setAdvice_no(loadingdetails.get(v).getAdvice_no());
							loadingitem.setAcc_norms(loadingdetails.get(v).getAcc_norms());
							double amount=0.00,discount_amt=0.00,tax_amt=0.00,total_amt=0.00;
							
							if(itemDts.getPrice_based_on().compareToIgnoreCase("Packing")==0) 
							{
								loadingitem.setAmount(itemDts.getPrice()*loadingdetails.get(v).getS_quantity());
								amount=itemDts.getPrice()*loadingdetails.get(v).getS_quantity();
							}
							else 
							{
								loadingitem.setAmount(itemDts.getPrice()* loadingdetails.get(v).getQuantity());
								amount=itemDts.getPrice()* loadingdetails.get(v).getQuantity();
							}
							
						
							loadingitem.setBase_qty(loadingdetails.get(v).getBase_qty());
							loadingitem.setDeleted_by(loadingdetails.get(v).getDeleted_by());
							loadingitem.setDeleted_on(loadingdetails.get(v).getDeleted_on());
							
							if(itemDts.getDiscount_type().compareToIgnoreCase("Uom")==0) 
							{
								loadingitem.setDiscount_amt(loadingdetails.get(v).getDiscount_rate() * loadingdetails.get(v).getMat_wt());
								discount_amt=loadingdetails.get(v).getDiscount_rate() * loadingdetails.get(v).getMat_wt();
							}
							else 
							{
                                loadingitem.setDiscount_amt((amount * loadingdetails.get(v).getDiscount_rate())/100);
								discount_amt=(amount * loadingdetails.get(v).getDiscount_rate())/100;
							}
							
							
							loadingitem.setDiscount_rate(loadingdetails.get(v).getDiscount_rate());
							loadingitem.setDiscount_type(loadingdetails.get(v).getDiscount_type());
							loadingitem.setFin_year(sOrder.getFin_year());
							loadingitem.setHsn_code(loadingdetails.get(v).getHsn_code());
							loadingitem.setInserted_by(loadingdetails.get(v).getInserted_by());
							loadingitem.setInserted_on(loadingdetails.get(v).getInserted_on());
							loadingitem.setItem_code(loadingdetails.get(v).getItem_code());
							loadingitem.setItem_name(loadingdetails.get(v).getItem_name());
							loadingitem.setMat_wt(loadingdetails.get(v).getMat_wt());
							loadingitem.setModified_type("INSERTED");
							loadingitem.setOrder_id(loadingdetails.get(v).getOrder_id());
							loadingitem.setPacking(loadingdetails.get(v).getPacking());
							loadingitem.setPacking_name(loadingdetails.get(v).getPacking_name());
							
							loadingitem.setPrice(itemDts.getPrice());
							
							loadingitem.setPrice_based_on(loadingdetails.get(v).getPrice_based_on());
							loadingitem.setQuantity(loadingdetails.get(v).getQuantity());
							loadingitem.setS_quantity(loadingdetails.get(v).getS_quantity());
							loadingitem.setS_uom(loadingdetails.get(v).getS_uom());
							loadingitem.setSl_no(loadingdetails.get(v).getSl_no());
							loadingitem.setStack_rack(loadingdetails.get(v).getStack_rack());
							loadingitem.setStack_rack_name(loadingdetails.get(v).getStack_rack_name());
						
							
							loadingitem.setTax_amt(((amount- discount_amt)*loadingdetails.get(v).getTax_rate())/100);
							tax_amt=((amount- discount_amt)*loadingdetails.get(v).getTax_rate())/100;
									
									
							loadingitem.setTax_code(loadingdetails.get(v).getTax_code());
							loadingitem.setTax_rate(loadingdetails.get(v).getTax_rate());
							loadingitem.setTolerance(loadingdetails.get(v).getTolerance());
							
							loadingitem.setTotal_amt((amount-discount_amt)+tax_amt);
							total_amt=((amount-discount_amt)+tax_amt);
							
							
							loadingitem.setUom(loadingdetails.get(v).getUom());
							loadingitem.setUpdated_by(sOrder.getUsername());
							loadingitem.setUpdated_on(ldt);
							loadingitem.setUsername(sOrder.getUsername());
							loadingitem.setWarehouse(loadingdetails.get(v).getWarehouse());
							loadingitem.setWarehouse_name(loadingdetails.get(v).getWarehouse_name());
							
							
							
							itemDts.setWm_loading_advice_itm_dtls(loadingitem);
						}
						
					}
					
					
					
				}

				
			}
			sOrder.setSales_Order_Item_Dtls(itemSet);
			
			//Delete Sales_Transaction
			List<Sales_transaction> sOrdTrans=new ArrayList<Sales_transaction>();
			sOrdTrans.addAll(sales_transactionRepository.getSalesStocks(op.get().getOrder_id()));
			
			sOrdTrans.forEach((sTrans) -> {
				sales_transactionRepository.deleteSales_transactions(sTrans.getReference_id(),sTrans.getBusiness_unit(),sTrans.getItem_id(),sTrans.getPacking_id());
			});
			
			//For Sales_Transaction
			Set<Sales_transaction> stSet = new HashSet<Sales_transaction>();
			if(Utility.isNullOrEmpty(sOrder.getBusiness_unit())) {
				for(Sales_Order_Item_Dtls itmDtls:itemSet)
				{
					if(Utility.isNullOrEmpty(itmDtls.getItem_code())) {
						Sales_transaction sTransaction=new Sales_transaction(sOrder.getBusiness_unit(), itmDtls.getItem_code(), itmDtls.getPacking(),itmDtls.getQuantity(), itmDtls.getSquantity(),
								itmDtls.getQuantity(), itmDtls.getSquantity(),0.00,0,0.00,0,itmDtls.getPacking_list(), op.get().getOrder_id(), sOrder.getFin_year(), sOrder.getCompany_id(),
								sOrder.getUsername(), sOrder.getModified_type(), sOrder.getInserted_on(), sOrder.getInserted_by(), sOrder.getUpdated_on(),
								sOrder.getUpdated_by());
						
						stSet.add(sTransaction);
					}else {System.err.println("Not in Block !!!!!!!!!");}
				}
			}
			sales_transactionRepository.saveAll(stSet);
					
			sales_Order_Broker_DtlsRepository.sales_Order_Broker_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Broker_Dtls> brokerSet = new HashSet<Sales_Order_Broker_Dtls>();
			brokerSet.addAll(sOrder.getSales_Order_Broker_Dtls());
			for(Sales_Order_Broker_Dtls brokerDts : brokerSet)
			{
				brokerDts.setOrder_id(op.get().getOrder_id());
				
				brokerDts.setBroker_name(cust_bussiness_partner_brokerRepository.custBrokerRetriveListNew(brokerDts.getP_code()).getVen_name());
				
				brokerDts.setOrder_no(sOrder.getOrder_no());
				brokerDts.setCompany_id(sOrder.getCompany_id());
				brokerDts.setFin_year(sOrder.getFin_year());
				brokerDts.setModified_type("INSERTED");
				brokerDts.setInserted_by(sOrder.getInserted_by());
				brokerDts.setInserted_on(sOrder.getInserted_on());
				brokerDts.setUpdated_by(sOrder.getUpdated_by());
				brokerDts.setUpdated_on(sOrder.getUpdated_on());
				brokerDts.setDeleted_by("NA");
				brokerDts.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Broker_Dtls(brokerSet);
			
			sales_Order_SummaryRepository.sales_Order_Summaryupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Summary> summSet = new HashSet<Sales_Order_Summary>();
			summSet.add(sOrder.getSales_Order_Summary());
			for(Sales_Order_Summary party : summSet)
			{
				party.setOrder_id(op.get().getOrder_id());
				party.setOrder_no(sOrder.getOrder_no());
				party.setCompany_id(sOrder.getCompany_id());
				party.setFin_year(sOrder.getFin_year());
				party.setModified_type("INSERTED");
				party.setInserted_by(sOrder.getInserted_by());
				party.setInserted_on(sOrder.getInserted_on());
				party.setUpdated_by(sOrder.getUpdated_by());
				party.setUpdated_on(sOrder.getUpdated_on());
				party.setDeleted_by("NA");
				party.setDeleted_on(ldt);
			}
			if(!summSet.isEmpty())	{
				sOrder.setSales_Order_Summary(summSet.iterator().next());
			}
			
			sales_Order_Summary_dynRepository.sales_Order_Summary_dynupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Summary_dyn> summdSet = new HashSet<Sales_Order_Summary_dyn>();
			summdSet.addAll(sOrder.getSales_Order_Summary_dyn());
			for(Sales_Order_Summary_dyn summddtls : summdSet)
			{
				summddtls.setOrder_id(op.get().getOrder_id());
				summddtls.setOrder_no(sOrder.getOrder_no());
				summddtls.setCompany_id(sOrder.getCompany_id());
				summddtls.setFin_year(sOrder.getFin_year());
				summddtls.setModified_type("INSERTED");
				summddtls.setInserted_by(sOrder.getInserted_by());
				summddtls.setInserted_on(sOrder.getInserted_on());
				summddtls.setUpdated_by(sOrder.getUpdated_by());
				summddtls.setUpdated_on(sOrder.getUpdated_on());
				summddtls.setDeleted_by("NA");
				summddtls.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Summary_dyn(summdSet);
			
			sales_Order_Trans_Chgs_dynRepository.sales_Order_Trans_Chgs_dynupdate(op.get().getOrder_id());
			Set<Sales_Order_Trans_Chgs_dyn> chargedynSet = new HashSet<Sales_Order_Trans_Chgs_dyn>();
			chargedynSet.addAll(sOrder.getSales_Order_Trans_Chgs_dyn());
			for(Sales_Order_Trans_Chgs_dyn chgdyn : chargedynSet)
			{
				chgdyn.setOrder_id(op.get().getOrder_id());
				chgdyn.setOrder_no(sOrder.getOrder_no());
				chgdyn.setCompany_id(sOrder.getCompany_id());
				chgdyn.setFin_year(sOrder.getFin_year());
				chgdyn.setModified_type("INSERTED");
				chgdyn.setInserted_by(sOrder.getInserted_by());
				chgdyn.setInserted_on(sOrder.getInserted_on());
				chgdyn.setUpdated_by(sOrder.getUpdated_by());
				chgdyn.setUpdated_on(sOrder.getUpdated_on());
				chgdyn.setDeleted_by("NA");
				chgdyn.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Trans_Chgs_dyn(chargedynSet);
			
			sales_Order_Trans_InfoRepository.Sales_Order_Trans_Infoupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Trans_Info> transSet = new HashSet<Sales_Order_Trans_Info>();
			transSet.add(sOrder.getSales_Order_Trans_Info());
			for(Sales_Order_Trans_Info trans : transSet)
			{
				trans.setOrder_id(op.get().getOrder_id());
				trans.setOrder_no(sOrder.getOrder_no());
				
				if(Utility.isNullOrEmpty(trans.getTrans_code())) 
				{
					if(trans.getTrans_code().compareToIgnoreCase("0")==0)
					{
						trans.setTransporter_name("0");
					}
					else
					{
						trans.setTransporter_name(trans_bussiness_partnerRepository.bpNameById(trans.getTrans_code()).getBp_name());
					}
				}
				else{trans.setTransporter_name("0");};
				
				trans.setCompany_id(sOrder.getCompany_id());
				trans.setFin_year(sOrder.getFin_year());
				trans.setModified_type("INSERTED");
				trans.setInserted_by(sOrder.getInserted_by());
				trans.setInserted_on(sOrder.getInserted_on());
				trans.setUpdated_by(sOrder.getUpdated_by());
				trans.setUpdated_on(sOrder.getUpdated_on());
				trans.setDeleted_by("NA");
				trans.setDeleted_on(ldt);
			}
			if(!transSet.isEmpty())	{
				sOrder.setSales_Order_Trans_Info(transSet.iterator().next());
			}
			
			sales_Order_Party_DtlsRepository.sales_Order_Party_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Party_Dtls> bpSet = new HashSet<Sales_Order_Party_Dtls>();
			bpSet.addAll(sOrder.getSales_Order_Party_Dtls());
			for(Sales_Order_Party_Dtls bpdtls : bpSet)
			{
				bpdtls.setOrder_id(op.get().getOrder_id());
				bpdtls.setOrder_no(sOrder.getOrder_no());
				if(Utility.isNullOrEmpty(bpdtls.getP_code())) {
					bpdtls.setP_name(cust_bussiness_partnerRepository.getCustomer(bpdtls.getP_code()).getCp_name());
				}else {bpdtls.setP_name("0");}
				bpdtls.setCompany_id(sOrder.getCompany_id());
				bpdtls.setFin_year(sOrder.getFin_year());
				bpdtls.setModified_type("INSERTED");
				bpdtls.setInserted_by(sOrder.getInserted_by());
				bpdtls.setInserted_on(sOrder.getInserted_on());
				bpdtls.setUpdated_by(sOrder.getUpdated_by());
				bpdtls.setUpdated_on(sOrder.getUpdated_on());
				bpdtls.setDeleted_by("NA");
				bpdtls.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Party_Dtls(bpSet);
			
			sales_Order_Terms_ConRepository.sales_Order_Terms_Conupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Terms_Con> termSet = new HashSet<Sales_Order_Terms_Con>();
			termSet.add(sOrder.getSales_Order_Terms_Con());
			for(Sales_Order_Terms_Con termdtls : termSet)
			{
				termdtls.setOrder_id(op.get().getOrder_id());
				termdtls.setOrder_no(sOrder.getOrder_no());
				termdtls.setCompany_id(sOrder.getCompany_id());
				termdtls.setFin_year(sOrder.getFin_year());
				termdtls.setModified_type("INSERTED");
				termdtls.setInserted_by(sOrder.getInserted_by());
				termdtls.setInserted_on(sOrder.getInserted_on());
				termdtls.setUpdated_by(sOrder.getUpdated_by());
				termdtls.setUpdated_on(sOrder.getUpdated_on());
				termdtls.setDeleted_by("NA");
				termdtls.setDeleted_on(ldt);
			}
			if(!termSet.isEmpty())	{
				sOrder.setSales_Order_Terms_Con(termSet.iterator().next());
			}
			
			sales_Order_Shipment_DtlsRepository.sales_Order_Shipment_Dtlsupdate(op.get().getOrder_id(),"UPDATED");
			Set<Sales_Order_Shipment_Dtls> shipSet = new HashSet<Sales_Order_Shipment_Dtls>();
			shipSet.add(sOrder.getSales_Order_Shipment_Dtls());
			for(Sales_Order_Shipment_Dtls shipdtls : shipSet)
			{
				shipdtls.setOrder_id(op.get().getOrder_id());
				shipdtls.setOrder_no(sOrder.getOrder_no());
				shipdtls.setCompany_id(sOrder.getCompany_id());
				shipdtls.setFin_year(sOrder.getFin_year());
				shipdtls.setModified_type("INSERTED");
				shipdtls.setInserted_by(sOrder.getInserted_by());
				shipdtls.setInserted_on(sOrder.getInserted_on());
				shipdtls.setUpdated_by(sOrder.getUpdated_by());
				shipdtls.setUpdated_on(sOrder.getUpdated_on());
				shipdtls.setDeleted_by("NA");
				shipdtls.setDeleted_on(ldt);
			}
			if(!shipSet.isEmpty())	{
				sOrder.setSales_Order_Shipment_Dtls(shipSet.iterator().next());
			}
			
			sales_Order_DocsRepository.updateSales_Order_Docs(op.get().getOrder_id(),"UPDATED");
			
			Set<Sales_Order_Docs> docSet = new HashSet<Sales_Order_Docs>();
			docSet.addAll(sOrder.getSales_Order_Docs());
			for(Sales_Order_Docs docDts : docSet)
			{
				docDts.setOrder_id(op.get().getOrder_id());
				docDts.setOrder_no(sOrder.getOrder_no());
				docDts.setCompany_id(sOrder.getCompany_id());
				docDts.setFin_year(sOrder.getFin_year());
				docDts.setModified_type("INSERTED");
				docDts.setInserted_by(sOrder.getInserted_by());
				docDts.setInserted_on(sOrder.getInserted_on());
				docDts.setUpdated_by(sOrder.getUpdated_by());
				docDts.setUpdated_on(sOrder.getUpdated_on());
				docDts.setDeleted_by("NA");
				docDts.setDeleted_on(ldt);
			}
			sOrder.setSales_Order_Docs(docSet);
			
			return sales_OrderRepository.save(sOrder);	
		
	}
	
	@Transactional
	public Sales_Order deleteEffectiveSalesOrder(Sales_Order salesOrder,Long id) 
	{
		Optional<Sales_Order> op = sales_OrderRepository.findById(id);
		Sales_Order sOrder=sales_OrderRepository.getSalesOrderDetails(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		Sales_Order sOrderStatus=new Sales_Order();
		
		if(op.get().isLoading_status() == true) {
			System.err.println("Delete is not Possible !!!");
			return sOrderStatus;
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
			
			sales_Order_Item_DtlsRepository.sales_Order_Item_Dtlsupdate(op.get().getOrder_id(),"DELETED");
			
			//Delete Sales_Transaction
			List<Sales_transaction> sOrdTrans=new ArrayList<Sales_transaction>();
			sOrdTrans.addAll(sales_transactionRepository.getSalesStocks(op.get().getOrder_id()));
			
			sOrdTrans.forEach((sTrans) -> {
				sales_transactionRepository.deleteSales_transactions(sTrans.getReference_id(),sTrans.getBusiness_unit(),sTrans.getItem_id(),sTrans.getPacking_id());
			});
			
			sales_Order_Broker_DtlsRepository.sales_Order_Broker_Dtlsupdate(op.get().getOrder_id(),"DELETED");
			
			sales_Order_SummaryRepository.sales_Order_Summaryupdate(op.get().getOrder_id(),"DELETED");
			
			sales_Order_Summary_dynRepository.sales_Order_Summary_dynupdate(op.get().getOrder_id(),"DELETED");
			
			sales_Order_Trans_InfoRepository.Sales_Order_Trans_Infoupdate(op.get().getOrder_id(),"DELETED");
			
			sales_Order_Party_DtlsRepository.sales_Order_Party_Dtlsupdate(op.get().getOrder_id(),"DELETED");
			
			sales_Order_Terms_ConRepository.sales_Order_Terms_Conupdate(op.get().getOrder_id(),"DELETED");
			
			sales_Order_Shipment_DtlsRepository.sales_Order_Shipment_Dtlsupdate(op.get().getOrder_id(),"DELETED");
			
			sales_Order_DocsRepository.updateSales_Order_Docs(op.get().getOrder_id(),"DELETED");
			
			sales_Order_Trans_Chgs_dynRepository.salesOrderTransChgsdynUpdate(op.get().getOrder_id());
			
			return sales_OrderRepository.save(sOrder);	
		}
	}
	
	@Transactional
	public StatusDTO SalesOrderTerminate(long id,String username)
	{
		StatusDTO res =new StatusDTO();
		sales_OrderRepository.SalesOrderTerminate(id,username);
		
		Optional<Sales_Order> op=this.sales_OrderRepository.findById(id);
		
		if(op.get().isTerminate())
		{
			res.setStatus("Yes");	
		}
		else 
		{
			res.setStatus("No");	
		}
			
			return res;
	}
	
	@Transactional
	public Sales_Order deleteSalesOrder(Sales_Order salesOrder,Long id) {

		Optional<Sales_Order> op = sales_OrderRepository.findById(id);
		Sales_Order sOrder=sales_OrderRepository.getSalesOrderDetails(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		Sales_Order sOrderStatus=new Sales_Order();
		
		if(op.get().isLoading_status() == true) {
			System.err.println("Delete is not Possible !!!");
			return sOrderStatus;
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
			
			if(op.get().getInv_type().compareToIgnoreCase("INV00003")==0)
			{
				sales_OrderRepository.sales_OrderJobItemupdate(op.get().getOrder_id(),"DELETED");
				sales_OrderRepository.sales_OrderServiceItemupdate(op.get().getOrder_id(),"DELETED");
			}
			else
			{
				sales_Order_Item_DtlsRepository.sales_Order_Item_Dtlsupdate(op.get().getOrder_id(),"DELETED");
				
				sales_Order_SummaryRepository.sales_Order_Summaryupdate(op.get().getOrder_id(),"DELETED");
				
				sales_Order_Summary_dynRepository.sales_Order_Summary_dynupdate(op.get().getOrder_id(),"DELETED");
			}
			
			sales_QuotationRepository.revertupdatequationstationorder(op.get().getReferance_id());
			//Delete Sales_Transaction
			List<Sales_transaction> sOrdTrans=new ArrayList<Sales_transaction>();
			sOrdTrans.addAll(sales_transactionRepository.getSalesStocks(op.get().getOrder_id()));
			
			sOrdTrans.forEach((sTrans) -> {
				sales_transactionRepository.deleteSales_transactions(sTrans.getReference_id(),sTrans.getBusiness_unit(),sTrans.getItem_id(),sTrans.getPacking_id());
			});
			
			
			
			sales_Order_Broker_DtlsRepository.sales_Order_Broker_Dtlsupdate(op.get().getOrder_id(),"DELETED");
			
			
			sales_Order_Trans_InfoRepository.Sales_Order_Trans_Infoupdate(op.get().getOrder_id(),"DELETED");
			
			sales_Order_Party_DtlsRepository.sales_Order_Party_Dtlsupdate(op.get().getOrder_id(),"DELETED");
			
			sales_Order_Terms_ConRepository.sales_Order_Terms_Conupdate(op.get().getOrder_id(),"DELETED");
			
			sales_Order_Shipment_DtlsRepository.sales_Order_Shipment_Dtlsupdate(op.get().getOrder_id(),"DELETED");
			
			sales_Order_DocsRepository.updateSales_Order_Docs(op.get().getOrder_id(),"DELETED");
			
			sales_Order_Trans_Chgs_dynRepository.salesOrderTransChgsdynUpdate(op.get().getOrder_id());
			
			return sales_OrderRepository.save(sOrder);	
		}
	}
	
	public List<Sales_OrderDTO> findAll()
	{
		List<Sales_Order> sOrdList=new ArrayList<Sales_Order>();
		sOrdList.addAll(sales_OrderRepository.findSalesOrders());
		
		Type listType = new TypeToken<List<Sales_OrderDTO>>() {}.getType();
		List<Sales_OrderDTO> ordList = new ModelMapper().map(sOrdList,listType);
		
		ordList.forEach((sOrder) -> {
			if(Utility.isNullOrEmpty(sOrder.getCustomer())) {
				sOrder.setCustomer_name(cust_bussiness_partnerRepository.getCustomer(sOrder.getCustomer()).getCp_name());
			}else {sOrder.setCustomer_name("None");}
			
			if(Utility.isNullOrEmpty(sOrder.getInv_type())) {
				sOrder.setInv_type(invoice_typeRepository.getSalesInvTypesDtls(sOrder.getInv_type()).getInvtype_name());
			}else {sOrder.setInv_type("None");}
			
			System.err.println("Order No: "+sOrder.getOrder_id());
			sOrder.setNet_amount(sales_Order_SummaryRepository.getSalesOrdSummary(sOrder.getOrder_id()).getNet_amount());
			
			sOrder.setOrder_date(Utility.convertDateDDMMYYYY(sOrder.getOrder_date()));
		});
		return ordList;
	}
	
	
	public List<Sales_OrderDTO> getliewterminationsalelist()
	{
		List<Sales_Order> sOrdList=new ArrayList<Sales_Order>();
		sOrdList.addAll(sales_OrderRepository.findSalesOrdersliew());
		
		
		
		
		
		Type listType = new TypeToken<List<Sales_OrderDTO>>() {}.getType();
		List<Sales_OrderDTO> ordList = new ModelMapper().map(sOrdList,listType);
		
		
		return ordList;
	}
	
	public List<Sales_OrderDTO> getSalesOrders(String party,String invdate,String comp){
		
		List<Sales_Order> sOrdList=new ArrayList<Sales_Order>();
		sOrdList.addAll(sales_OrderRepository.getSalesOrders(party,Utility.convertDateYYYYMMDD(invdate),comp));
		
		Type listType = new TypeToken<List<Sales_OrderDTO>>() {}.getType();
		List<Sales_OrderDTO> ordList = new ModelMapper().map(sOrdList,listType);
		
		ordList.forEach((sOrder) -> {
			if(Utility.isNullOrEmpty(sOrder.getCustomer())) {
				sOrder.setCustomer_name(cust_bussiness_partnerRepository.getCustomer(sOrder.getCustomer()).getCp_name());
			}else {sOrder.setCustomer_name("None");}
			
			if(Utility.isNullOrEmpty(sOrder.getInv_type())) {
				sOrder.setInv_type(invoice_typeRepository.getSalesInvTypesDtls(sOrder.getInv_type()).getInvtype_name());
			}else {sOrder.setInv_type("None");}
			
			sOrder.setNet_amount(sales_Order_SummaryRepository.getSalesOrdSummary(sOrder.getOrder_id()).getNet_amount()); 
			
			sOrder.setOrder_date(Utility.convertDateDDMMYYYY(sOrder.getOrder_date()));
		});
		return ordList;
	}
	
public List<Map<String,Object>> getSalesOrderReturn(String party,String invdate,String comp){
		
		List<Map<String,Object>> sOrdList=new ArrayList<>();
		List<String> salesorderID=new ArrayList<>();
		salesorderID.addAll(sales_OrderRepository.getSalesOrderReturn(party,invdate));
		for(int p=0;p<salesorderID.size();p++)
		{
			sOrdList.add(sales_OrderRepository.getSalesOrderReturnList(salesorderID.get(p)));
		}
		return sOrdList;
	}

	
	public List<Sales_OrderDTO> getDeliverySalesOrderUpdate(Long id){
			
		
			Optional<Delivery_challan> op = delivery_challanRepository.findById(id);
		
		
			List<Sales_Order> sOrdList=new ArrayList<Sales_Order>();
			sOrdList.addAll(sales_OrderRepository.salesOrderPartyList(op.get().getDelivery_cid()));
			
			Type listType = new TypeToken<List<Sales_OrderDTO>>() {}.getType();
			List<Sales_OrderDTO> ordList = new ModelMapper().map(sOrdList,listType);
			
			
			return ordList;
		}

	public List<Sales_OrderDTO> findSalesOrders(String bunit,String party,String invdate)
	{
	    System.out.println(bunit +" // " + party + " // " + invdate);
		List<Sales_Order> sOrdList=new ArrayList<Sales_Order>();
		
	//here changes starts due to channel
		List<Sales_Order> finalSalesOrds=new ArrayList<Sales_Order>();
		
		
		//List<Sales_Order_Party_Dtls>  partypersales= new ArrayList<Sales_Order_Party_Dtls>();
		
		//partypersales.addAll(sales_OrderRepository.getpartysalesorder(party));//partydetails list
		
		List<Sales_Order> partylist=new ArrayList<Sales_Order>();//partylist 
		partylist.addAll(sales_OrderRepository.getsaleorderbypartychannel(party));
		
		
		//System.out.println("party size :: "+partypersales.size());
		List<Sales_Order> salesOrders=new ArrayList<Sales_Order>();//restwt 
		for(int i=0;i<partylist.size();i++) 
		{
			
			//double totalrest_wt=sales_OrderRepository.getrestwet_forloadingpopup(partylist.get(i).getOrder_id());
			System.out.println(partylist.get(i).getOrder_id());
			double totalrest_wt=sales_OrderRepository.getrestwet_forloadingpopuptolerance(partylist.get(i).getOrder_id());
			if(totalrest_wt>0) 
			{
				salesOrders.add(sales_OrderRepository.getSalesOrderDetails(partylist.get(i).getOrder_id()));
			}
		}
		
	
		sOrdList = salesOrders
				  .stream()
				  .filter(c -> c.getBusiness_unit().equals(bunit) &&
						  Utility.convertStrToDate(c.getOrder_date()).compareTo(Utility.convertStrToDate(invdate)) <= 0)
				  .collect(Collectors.toList());
		//here ends due to channel
		List<String> orderId = new ArrayList<String>();
		sOrdList.forEach((sOrder) -> {
			
			List<Sales_transaction> sOrdTrans=new ArrayList<Sales_transaction>();
			sOrdTrans.addAll(sales_transactionRepository.getSalesStocks(sOrder.getOrder_id()));
			
			double totalQty = sOrdTrans.stream()  
			        .collect(Collectors.summingDouble(ord->ord.getSales_pack_qty()));  
					System.err.println(totalQty);
					
			if(totalQty != 0) {
				orderId.add(sOrder.getOrder_id());
			}
		});
		
		for(String ordID : orderId)
		{
			finalSalesOrds.addAll(salesOrders
					  .stream()
					  .filter(c -> c.getOrder_id().equals(ordID))
					  .collect(Collectors.toList()));
		}
		
		Type listType = new TypeToken<List<Sales_OrderDTO>>() {}.getType();
		List<Sales_OrderDTO> ordList = new ModelMapper().map(finalSalesOrds,listType);
		
		ordList.forEach((sOrder) -> {
			if(Utility.isNullOrEmpty(sOrder.getCustomer())) {
				sOrder.setCustomer_name(cust_bussiness_partnerRepository.getCustomer(sOrder.getCustomer()).getCp_name());
			}else {sOrder.setCustomer_name("None");}
			// Added by Tuhin
			sOrder.setRemarks(sOrder.getInv_type());
			
			if(Utility.isNullOrEmpty(sOrder.getInv_type())) {
				sOrder.setInv_type(invoice_typeRepository.getSalesInvTypesDtls(sOrder.getInv_type()).getInvtype_name());
			}else {sOrder.setInv_type("None");}
			
			if(Utility.isNullOrEmpty(sOrder.getOrder_id())) {
				sOrder.setNet_amount(sales_Order_SummaryRepository.getSalesOrdSummary(sOrder.getOrder_id()).getNet_amount());
			}
			sOrder.setOrder_date(Utility.convertDateDDMMYYYY(sOrder.getOrder_date()));
		});
		return ordList;
	}
	
	public List<Map<String,Object>> findSalesOrdersModified(String bunit,String party,String invdate)
	{
		return sales_OrderRepository.findSalesOrdersModified(party,invdate);
	}
	public List<Map<String,Object>> findSalesOrdersRefraction(String bunit,String party,String invdate)
	{
		return sales_OrderRepository.findSalesOrdersRefraction(party,invdate);
	}
	
	public List<Sales_OrderDTO> findSalesOrders_channelbackup(String bunit,String party,String invdate){
		System.out.println(bunit +" // " + party + " // " + invdate);
			List<Sales_Order> sOrdList=new ArrayList<Sales_Order>();
			//sOrdList.addAll(sales_OrderRepository.findSalesOrders(party,Utility.convertDateYYYYMMDD(invdate),bunit));
			
			/*List<Sales_Order> salesOrders=new ArrayList<Sales_Order>();
			salesOrders.addAll(sales_OrderRepository.findAll());
			
			List<Sales_Order> finalSalesOrds=new ArrayList<Sales_Order>();
			
			sOrdList = salesOrders
					  .stream()
					  .filter(c -> c.getCustomer().equals(party) && c.getBusiness_unit().equals(bunit) &&
							  Utility.convertStrToDate(c.getOrder_date()).compareTo(Utility.convertStrToDate(invdate)) <= 0)
					  .collect(Collectors.toList());
			
			*/
		//here changes starts due to channel
			List<Sales_Order> finalSalesOrds=new ArrayList<Sales_Order>();
			List<Sales_Order_Party_Dtls>  partypersales= new ArrayList<Sales_Order_Party_Dtls>();
			partypersales.addAll(sales_OrderRepository.getpartysalesorder(party));//partydetails list
			//System.out.println("party size :: "+partypersales.size());
			List<Sales_Order> salesOrders=new ArrayList<Sales_Order>();//restwt 
			for(int i=0;i<partypersales.size();i++) 
			{
				//System.out.println("party size :: "+partypersales.get(i).getOrder_id());
				double totalrest_wt=sales_OrderRepository.getrestwet_forloadingpopup(partypersales.get(i).getOrder_id());
				if(totalrest_wt>0) 
				{
					salesOrders.add(sales_OrderRepository.getSalesOrderDetails(partypersales.get(i).getOrder_id()));
				}
			}
			
			
		/*	for(int i=0;i<partypersales.size();i++) 
			{
				salesOrders.add(sales_OrderRepository.getSalesOrderDetails(partypersales.get(i).getOrder_id()));
			}
		*/	
			sOrdList = salesOrders
					  .stream()
					  .filter(c -> c.getBusiness_unit().equals(bunit) &&
							  Utility.convertStrToDate(c.getOrder_date()).compareTo(Utility.convertStrToDate(invdate)) <= 0)
					  .collect(Collectors.toList());
			//here ends due to channel
			List<String> orderId = new ArrayList<String>();
			sOrdList.forEach((sOrder) -> {
				
				List<Sales_transaction> sOrdTrans=new ArrayList<Sales_transaction>();
				sOrdTrans.addAll(sales_transactionRepository.getSalesStocks(sOrder.getOrder_id()));
				
				double totalQty = sOrdTrans.stream()  
				        .collect(Collectors.summingDouble(ord->ord.getSales_pack_qty()));  
						System.err.println(totalQty);
						
				if(totalQty != 0) {
					orderId.add(sOrder.getOrder_id());
				}
			});
			
			for(String ordID : orderId)
			{
				finalSalesOrds.addAll(salesOrders
						  .stream()
						  .filter(c -> c.getOrder_id().equals(ordID))
						  .collect(Collectors.toList()));
			}
			
			Type listType = new TypeToken<List<Sales_OrderDTO>>() {}.getType();
			List<Sales_OrderDTO> ordList = new ModelMapper().map(finalSalesOrds,listType);
			
			ordList.forEach((sOrder) -> {
				if(Utility.isNullOrEmpty(sOrder.getCustomer())) {
					sOrder.setCustomer_name(cust_bussiness_partnerRepository.getCustomer(sOrder.getCustomer()).getCp_name());
				}else {sOrder.setCustomer_name("None");}
				// Added by Tuhin
				sOrder.setRemarks(sOrder.getInv_type());
				
				if(Utility.isNullOrEmpty(sOrder.getInv_type())) {
					sOrder.setInv_type(invoice_typeRepository.getSalesInvTypesDtls(sOrder.getInv_type()).getInvtype_name());
				}else {sOrder.setInv_type("None");}
				
				if(Utility.isNullOrEmpty(sOrder.getOrder_id())) {
					sOrder.setNet_amount(sales_Order_SummaryRepository.getSalesOrdSummary(sOrder.getOrder_id()).getNet_amount());
				}
				sOrder.setOrder_date(Utility.convertDateDDMMYYYY(sOrder.getOrder_date()));
			});
			return ordList;
		}
	
public List<Sales_OrderDTO> findSalesOrdersbackup(String bunit,String party,String invdate){
		
		List<Sales_Order> sOrdList=new ArrayList<Sales_Order>();
		//sOrdList.addAll(sales_OrderRepository.findSalesOrders(party,Utility.convertDateYYYYMMDD(invdate),bunit));
		
		/*List<Sales_Order> salesOrders=new ArrayList<Sales_Order>();
		salesOrders.addAll(sales_OrderRepository.findAll());
		
		List<Sales_Order> finalSalesOrds=new ArrayList<Sales_Order>();
		
		sOrdList = salesOrders
				  .stream()
				  .filter(c -> c.getCustomer().equals(party) && c.getBusiness_unit().equals(bunit) &&
						  Utility.convertStrToDate(c.getOrder_date()).compareTo(Utility.convertStrToDate(invdate)) <= 0)
				  .collect(Collectors.toList());
		
		*/
	//here changes starts due to channel
		List<Sales_Order> finalSalesOrds=new ArrayList<Sales_Order>();
		List<Sales_Order_Party_Dtls>  partypersales= new ArrayList<Sales_Order_Party_Dtls>();
		partypersales.addAll(sales_OrderRepository.getpartysalesorder(party));//partydetails list
		
		List<Sales_Order> salesOrders=new ArrayList<Sales_Order>();
		for(int i=0;i<partypersales.size();i++) 
		{
			salesOrders.add(sales_OrderRepository.getSalesOrderDetails(partypersales.get(i).getOrder_id()));
		}
		
		sOrdList = salesOrders
				  .stream()
				  .filter(c -> c.getBusiness_unit().equals(bunit) &&
						  Utility.convertStrToDate(c.getOrder_date()).compareTo(Utility.convertStrToDate(invdate)) <= 0)
				  .collect(Collectors.toList());
		//here ends due to channel
		List<String> orderId = new ArrayList<String>();
		sOrdList.forEach((sOrder) -> {
			
			List<Sales_transaction> sOrdTrans=new ArrayList<Sales_transaction>();
			sOrdTrans.addAll(sales_transactionRepository.getSalesStocks(sOrder.getOrder_id()));
			
			double totalQty = sOrdTrans.stream()  
			        .collect(Collectors.summingDouble(ord->ord.getSales_pack_qty()));  
					System.err.println(totalQty);
					
			if(totalQty != 0) {
				orderId.add(sOrder.getOrder_id());
			}
		});
		
		for(String ordID : orderId)
		{
			finalSalesOrds.addAll(salesOrders
					  .stream()
					  .filter(c -> c.getOrder_id().equals(ordID))
					  .collect(Collectors.toList()));
		}
		
		Type listType = new TypeToken<List<Sales_OrderDTO>>() {}.getType();
		List<Sales_OrderDTO> ordList = new ModelMapper().map(finalSalesOrds,listType);
		
		ordList.forEach((sOrder) -> {
			if(Utility.isNullOrEmpty(sOrder.getCustomer())) {
				sOrder.setCustomer_name(cust_bussiness_partnerRepository.getCustomer(sOrder.getCustomer()).getCp_name());
			}else {sOrder.setCustomer_name("None");}
			
			if(Utility.isNullOrEmpty(sOrder.getInv_type())) {
				sOrder.setInv_type(invoice_typeRepository.getSalesInvTypesDtls(sOrder.getInv_type()).getInvtype_name());
			}else {sOrder.setInv_type("None");}
			
			if(Utility.isNullOrEmpty(sOrder.getOrder_id())) {
				sOrder.setNet_amount(sales_Order_SummaryRepository.getSalesOrdSummary(sOrder.getOrder_id()).getNet_amount());
			}
			sOrder.setOrder_date(Utility.convertDateDDMMYYYY(sOrder.getOrder_date()));
		});
		return ordList;
	}
	
	
	public Sales_transaction getSalesStockDetails(String orderid,String bunit,String itemid,String packingid) {
		
		Sales_transaction stkDtls= sales_transactionRepository.getSalesStockDetails(orderid,bunit,itemid,packingid);
		
		return stkDtls;
	}
	
	public Sales_Order findOne(long id) {
		Optional<Sales_Order> op=this.sales_OrderRepository.findById(id);
		return op.get();
	}
	
	public List<Sales_OrderDTO> salesOrderList() {
		
		List<Sales_Order> modelList=sales_OrderRepository.salesOrderList();
		Type listType = new TypeToken<List<Sales_OrderDTO>>() {}.getType();
		List<Sales_OrderDTO> salesOrd = new ModelMapper().map(modelList,listType);
		
		salesOrd.forEach((ords) -> {
			if(Utility.isNullOrEmpty(ords.getCustomer())) {
				ords.setCustomer_name(cust_bussiness_partnerRepository.getCustomer(ords.getCustomer()).getCp_name());
			}else {ords.setCustomer_name("No");}
		});
		return salesOrd;
	}
	
	public Sales_OrderDTO getSalesOrderDetails(String orderid){
		
		Sales_Order modelList=sales_OrderRepository.getSalesOrderDetails(orderid);
		
		Type listType = new TypeToken<Sales_OrderDTO>() {}.getType();
		
		Sales_OrderDTO salesOrdDtls = new ModelMapper().map(modelList,listType);
		
		return salesOrdDtls;
	}
	
	public List<Sales_OrderDTO> salesOrderByParty(String custid) {                                           
		
		List<Sales_Order_Party_Dtls> partyList=sales_Order_Party_DtlsRepository.salesOrderPartyList(custid);

		List<Sales_Order> modelList=new ArrayList<Sales_Order>();
		
		for(int i=0;i<partyList.size();i++) {
			modelList.addAll(sales_OrderRepository.salesOrderPartyList(partyList.get(i).getOrder_id()));
		}
		
		Type listType = new TypeToken<List<Sales_OrderDTO>>() {}.getType();
		
		List<Sales_OrderDTO> salesOrd = new ModelMapper().map(modelList,listType);
		
		return salesOrd;
	}
	
	//Sales Order Stock Details to be show after minus ****************************************
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtls(String order_id)
	{
		
		List<Sales_Order_Item_Dtls> modelList=sales_Order_Item_DtlsRepository.getSalesOrdItemDtls(order_id);
		
		Type listType=new TypeToken<List<Sales_Order_Item_DtlsDTO>>() {}.getType();
		List<Sales_Order_Item_DtlsDTO> salesOrdItems=new ModelMapper().map(modelList,listType);
		return salesOrdItems;
	}
	public List<Sales_Order_Item_Dtls> getSalesOrdItemDtlsRefraction(String order_id) {
		return sales_Order_Item_DtlsRepository.getSalesOrdItemDtlsRefraction(order_id);
	}
	
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtlsNew(String order_id)
	{
		Sales_Order saleorder=sales_OrderRepository.getSalesOrderDetails(order_id);
		//INV00004 //PACKING MATERIAL SALE
		
		List<Sales_Order_Item_Dtls> modelList=sales_Order_Item_DtlsRepository.getSalesOrdItemDtls(order_id);
		
		modelList.forEach(e->
		{
			System.out.println("code:"+e.getItem_code()+"//"+e.getPacking());
			//Item_master_pack_mat_tag ietmcap=item_master_pack_mat_tagRepository.getItemCapacity(e.getItem_code());
			Item_master_pack_mat_tag ietmcap=item_master_pack_mat_tagRepository.getItemCapacityNew(e.getItem_code(),e.getPacking());
			
			//capacity = ietmcap.capacity
			
			//Double restwt = sales_OrderRepository.getLoadingRestWeight(order_id,e.getItem_code());
			//Double restwt = sales_OrderRepository.getLoadingRestWeightNew(order_id,e.getItem_code(),e.getPacking());
			Double restwt = sales_OrderRepository.getLoadingRestWeightNew(order_id,e.getItem_code(),e.getPacking());
			System.out.println("getCapacity :: "+ restwt +" / " +Double.parseDouble(ietmcap.getCapacity()));
            Double packingqty=restwt/Double.parseDouble(ietmcap.getCapacity());
            System.out.println("Check Price Based on : : "+e.getPrice_based_on()+" // "+packingqty+" // "+Math.round(packingqty));
            Double packingbasedqty =  Math.round(packingqty)*Double.parseDouble(ietmcap.getCapacity());
			//System.out.println("packingqty :: "+ packingqty + " / " + restwt +" / " +Double.parseDouble(ietmcap.getCapacity()));
			
			
			if(saleorder.getInv_type().compareToIgnoreCase("INV00004")==0) //it means when packing material for sale than view calcution wont be happening 
			{
				
			}
			else 
			{
				e.setSquantity(Math.round(packingqty));
				
				if(e.getPrice_based_on().compareToIgnoreCase("Packing")==0)
				{
					//System.out.println("If Packing :: "+packingqty*Double.parseDouble(ietmcap.getCapacity()));
					System.out.println("If Packing 1 :: "+packingbasedqty);
					e.setQuantity(packingbasedqty);
					e.setMat_wt(packingbasedqty);
				}
				else
				{
					//System.out.println("Else Item :: "+restwt);
					e.setQuantity(restwt);
					e.setMat_wt(restwt);
				}
				
			}
			
			
			
		});
		
		
		
		Type listType=new TypeToken<List<Sales_Order_Item_DtlsDTO>>() {}.getType();
		List<Sales_Order_Item_DtlsDTO> salesOrdItems=new ModelMapper().map(modelList,listType);
		return salesOrdItems;
	}
	
	
	
	public List<Map<String,Object>> getSalesOrdItemDtlsJobwork(String order_id)
	{
		List<Map<String,Object>> modelList=sales_Order_Item_DtlsRepository.getSalesOrdItemDtls_for_jobwork(order_id);
		return modelList;
	}
	
	public List<Map<String, Object>> getSOjwRestQty(String order_id,String item_id,String item_code)
	{
		return sales_OrderRepository.getSOjwRestQty(order_id,item_id,item_code);
	}
	
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtlsThruLoad(String order_id)
	{
		List<Sales_Order_Item_Dtls> saleOrds=new ArrayList<Sales_Order_Item_Dtls>();
		saleOrds.addAll(sales_Order_Item_DtlsRepository.getSalesOrderItemDtls(order_id));
		
		List<Sales_transaction> stkList=new ArrayList<Sales_transaction>();
		stkList.addAll(sales_transactionRepository.getSalesStocks(order_id));
		
		//Pending
			
		Type listType=new TypeToken<List<Sales_Order_Item_DtlsDTO>>() {}.getType();
		List<Sales_Order_Item_DtlsDTO> salesOrdItemList=new ModelMapper().map(saleOrds,listType);
		
		return salesOrdItemList;
	}
	
	public List<Map<String,Object>> getSalesOrdJobItemDtls(String order_id)
	{
		return sales_OrderRepository.getSalesOrdJobItemDtls(order_id);
	}
	
	public List<Map<String,Object>> getSalesOrdTServiceItem(String order_id)
	{
		return sales_OrderRepository.getSalesOrdTServiceItem(order_id);
	}
	
	public List<Sales_Order_Broker_DtlsDTO> getSalesOrdBrokerDtls(String order_id)
	{
		List<Sales_Order_Broker_Dtls> modelList=sales_Order_Broker_DtlsRepository.getSalesOrdBrokerDtls(order_id);
		
		Type listType=new TypeToken<List<Sales_Order_Broker_DtlsDTO>>() {}.getType();
		
		List<Sales_Order_Broker_DtlsDTO> salesOrdBroker=new ModelMapper().map(modelList,listType);
		
		return salesOrdBroker;
	}
	
	public List<Sales_Order_Summary_dynDTO> getSalesOrdSummDyna(String order_id)
	{
		List<Sales_Order_Summary_dyn> modelList=sales_Order_Summary_dynRepository.getSalesOrdSummDyna(order_id);
		
		Type listType=new TypeToken<List<Sales_Order_Summary_dynDTO>>() {}.getType();
		
		List<Sales_Order_Summary_dynDTO> salesOrdSummDyna=new ModelMapper().map(modelList,listType);
		
		return salesOrdSummDyna;
	}
	
	public List<Sales_Order_Party_DtlsDTO> getSalesOrdPartyDtls(String order_id)
	{
		List<Sales_Order_Party_Dtls> modelList=sales_Order_Party_DtlsRepository.getSalesOrdPartyDtls(order_id);
		
		Type listType=new TypeToken<List<Sales_Order_Party_DtlsDTO>>() {}.getType();
		
		List<Sales_Order_Party_DtlsDTO> salesOrdParty=new ModelMapper().map(modelList,listType);
		
		return salesOrdParty;
	}
	
	public List<Sales_Order_DocsDTO> getSalesOrdDocs(String order_id)
	{
		List<Sales_Order_Docs> modelList=sales_Order_DocsRepository.getSalesOrdDocs(order_id);
		
		Type listType=new TypeToken<List<Sales_Order_DocsDTO>>() {}.getType();
		
		List<Sales_Order_DocsDTO> salesOrdDoc=new ModelMapper().map(modelList,listType);
		
		return salesOrdDoc;
	}
	
	public Sales_Order_Shipment_DtlsDTO getSalesOrdShipDtls(String order_id)
	{
		Sales_Order_Shipment_Dtls modelList=sales_Order_Shipment_DtlsRepository.getSalesOrdShipDtls(order_id);
		
		Type listType=new TypeToken<Sales_Order_Shipment_DtlsDTO>() {}.getType();
		
		Sales_Order_Shipment_DtlsDTO salesOrdShip=new ModelMapper().map(modelList,listType);
		
		return salesOrdShip;
	}
	
	public Sales_Order_Trans_InfoDTO getSalesOrdTransInfo(String order_id)
	{
		Sales_Order_Trans_Info modelList=sales_Order_Trans_InfoRepository.getSalesOrdTransInfo(order_id);
		
		Type listType=new TypeToken<Sales_Order_Trans_InfoDTO>() {}.getType();
		
		Sales_Order_Trans_InfoDTO salesOrdTrans=new ModelMapper().map(modelList,listType);
		
		return salesOrdTrans;
	}
	
	public Sales_Order_SummaryDTO getSalesOrdSumm(String order_id)
	{
		Sales_Order_Summary modelList=sales_Order_SummaryRepository.getSalesOrdSummary(order_id);
		
		Type listType=new TypeToken<Sales_Order_SummaryDTO>() {}.getType();
		
		Sales_Order_SummaryDTO salesOrdSumm=new ModelMapper().map(modelList,listType);
		
		return salesOrdSumm;
	}
	
	public Sales_Order_Terms_ConDTO getSalesOrdTermsCon(String order_id)
	{
		Sales_Order_Terms_Con modelList= sales_Order_Terms_ConRepository.getSalesOrdTermsCon(order_id);
		
		Type listType=new TypeToken<Sales_Order_Terms_ConDTO>() {}.getType();
		
		Sales_Order_Terms_ConDTO salesOrdTermsCon=new ModelMapper().map(modelList,listType);
		
		return salesOrdTermsCon;
	}
	
	public Sales_transaction getSalesStockDetailsThruLoad(String orderid,String bunit,String itemid,String packingid,String loadingid) {
	//	System.err.println("Got Order: "+orderid +" ,Unit :"+bunit +" ,Item: "+itemid +" ,Pack: "+packingid +" ,Load: "+loadingid);
		Sales_transaction sTransaction=null;
		LocalDateTime ldt = LocalDateTime.now();
		//Loading Curr...
		Wm_loading_advice loadDtls=wm_loading_adviceRepository.getLoadingDtls(loadingid,orderid,bunit);
		//System.err.println("Loading val: "+loadDtls.getAdvice_id());
		Wm_loading_advice_itm_dtls loadOrds=null;
		if(Utility.isNullOrEmpty(loadDtls.getAdvice_id())){
			loadOrds=wm_loading_advice_itm_dtlsRepository.getLoadingItemDtls(loadDtls.getAdvice_id(),itemid,packingid);
		}
		//System.err.println("Got VALUE: "+loadOrds);
		//Sales Transaction Remaining 
		Sales_transaction stkTranOrds=sales_transactionRepository.getSalesStockDetails(orderid,bunit,itemid,packingid);
		
		//Sales Order Total 
		/*Optional<Sales_Order> salesDtls=sales_OrderRepository.getSalesOrderDetails(orderid,bunit);
		Sales_Order_Item_Dtls salesOrds=null;
		if(salesDtls.isPresent()){
			salesOrds=sales_Order_Item_DtlsRepository.getSalesOrderItemDtls(orderid,itemid,packingid);
			System.err.println("Sales val: "+loadDtls.get().getAdvice_id());
		}*/
		
		if(loadOrds != null) {
			if(stkTranOrds.getItem_id().compareTo(loadOrds.getItem_code())==0 && stkTranOrds.getPacking_id().compareTo(loadOrds.getPacking())==0 && stkTranOrds.getBusiness_unit().compareTo(loadDtls.getB_unit())==0) {
				sTransaction=new Sales_transaction(stkTranOrds.getBusiness_unit(), stkTranOrds.getItem_id(), stkTranOrds.getPacking_id(), stkTranOrds.getSales_item_qty()+loadOrds.getQuantity(), stkTranOrds.getSales_pack_qty()+Double.valueOf(loadOrds.getS_quantity()),
						stkTranOrds.getLoad_item_qty(), stkTranOrds.getLoad_pack_qty(), stkTranOrds.getDelv_item_qty(), stkTranOrds.getDelv_pack_qty(), stkTranOrds.getPacking_item(), stkTranOrds.getReference_id(),
						stkTranOrds.getFin_year(), stkTranOrds.getCompany_id(), stkTranOrds.getUsername(), stkTranOrds.getModified_type(), stkTranOrds.getInserted_on(), stkTranOrds.getInserted_by(), stkTranOrds.getUpdated_on(), stkTranOrds.getUpdated_by());
				
				/*sales_transactionRepository.updateSales_transactions(orderid,bunit,stkTranOrds.getItem_id(),stkTranOrds.getPacking_id(),
						stkTranOrds.getSales_item_qty()+loadOrds.getQuantity(), stkTranOrds.getSales_pack_qty()+Double.valueOf(loadOrds.getS_quantity()),"INSERTED");*/
			}
		}else {
			sTransaction=new Sales_transaction("0", "0", "0", 0.00, 0, 0.00, 0, 0.00, 0, "0", "0", "0", "0", "0", "0", ldt, "0", ldt, "0");
		}
		return sTransaction;
	}
	
	public List<Sales_Order_Item_DtlsDTO> getSalesOrderItemDtls(String order_id)
	{
		double pkcqty=0,itemqty=0,matwt=0;
		//Order Items
		List<Sales_Order_Item_Dtls> ordItems=new ArrayList<Sales_Order_Item_Dtls>();
		ordItems.addAll(sales_Order_Item_DtlsRepository.getSalesOrderItemDtls(order_id));
		
		List<Wm_loading_advice> lAdvId=wm_loading_adviceRepository.loadingAdviceDetails(order_id);
		//Used Loading Items
		List<Wm_loading_advice_itm_dtls> loadingItems=new ArrayList<Wm_loading_advice_itm_dtls>();
		for(int k=0;k<lAdvId.size();k++) {
			//System.err.println("Adv Id: "+lAdvId.get(k).getAdvice_id());
			loadingItems.addAll(wm_loading_advice_itm_dtlsRepository.getLoadingItemDetails(lAdvId.get(k).getAdvice_id()));
		}
		
		for(int i=0;i<ordItems.size();i++) {
			for(int j=0;j<loadingItems.size();j++) {
				//System.err.println("1ST Ord Items : "+ordItems.get(i).getItem_code()+",Adv Item: "+loadingItems.get(j).getItem_code()+" ,Pkc Qty: "+ordItems.get(i).getSquantity()+" ,itm Qty: "+ordItems.get(i).getQuantity());
				
				if(ordItems.get(i).getItem_code().compareTo(loadingItems.get(j).getItem_code()) ==0) {
					//System.out.println("2ND Ord Item : "+ordItems.get(i).getItem_code()+",Adv Item: "+loadingItems.get(j).getItem_code()+" ,Pkc Qty: "+ordItems.get(i).getSquantity()+" ,itm Qty: "+ordItems.get(i).getQuantity());
					
					double sumpkcqty=0,sumitemqty=0,summatwt=0;
					
					pkcqty=ordItems.get(i).getSquantity()-loadingItems.get(j).getS_quantity();
					itemqty=ordItems.get(i).getQuantity()-loadingItems.get(j).getQuantity();
					matwt=ordItems.get(i).getMat_wt()-loadingItems.get(j).getMat_wt();
					
					//System.out.println("Got result: "+pkcqty+","+itemqty+","+matwt);
				}
				else {
					System.err.println("Opps !!! Not Matched !!!");
				}
			}
			
			ordItems.get(i).setSquantity(pkcqty);
			ordItems.get(i).setQuantity(itemqty);
			ordItems.get(i).setMat_wt(matwt);
			
			//System.out.println("Final Items >> Pkc Qty: "+ordItems.get(i).getSquantity()+" ,itm Qty: "+ordItems.get(i).getQuantity());
		}
		
		Type listType=new TypeToken<List<Sales_Order_Item_DtlsDTO>>() {}.getType();
		List<Sales_Order_Item_DtlsDTO> findItems=new ModelMapper().map(ordItems,listType);
		return findItems;
	}
	
	public List<Sales_Order> getreturnapprovalsalesorder(Long id)
	 {
		 Optional<Return_approval_note> op=return_approval_noteRepository.findById(id);
		 List<Sales_Order> salesorderSingle = new ArrayList<Sales_Order>();
		 salesorderSingle.add(sales_OrderRepository.getSalesOrderDetails(op.get().getReferance_id()));
		 
			
		 salesorderSingle.forEach((sOrder) -> {
				if(Utility.isNullOrEmpty(sOrder.getCustomer())) {
					
					sOrder.setCustomer(cust_bussiness_partnerRepository.getCustomer(sOrder.getCustomer()).getCp_name());
					//System.out.println("tuhin "+sOrder.getCustomer());
				}
				else
				{
					sOrder.setCustomer("None");
				}
				
				
			});
		 
		 
		 
		 return salesorderSingle; 
		 
	 }
	
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtlsUpdate(Long order_id)
	{
		
		Optional<Delivery_challan> op = delivery_challanRepository.findById(order_id);
		
		List<Sales_Order_Item_Dtls> modelList=sales_Order_Item_DtlsRepository.getSalesOrdItemDtls(op.get().getDelivery_cid());
		
		Type listType=new TypeToken<List<Sales_Order_Item_DtlsDTO>>() {}.getType();
		List<Sales_Order_Item_DtlsDTO> salesOrdItems=new ModelMapper().map(modelList,listType);
		return salesOrdItems;
	}
	
	public StatusDTO getLoadingRestWeight(String orderid,String itemid,Double packing_qty)
	{
		
		String formaalstatus=sales_OrderRepository.getSalesOrderDetails(orderid).getRef_type();
		Double restwt =0.00;
		 
		
		if(formaalstatus.compareToIgnoreCase("Sales Quotation")==0)	
		{
			restwt = sales_OrderRepository.getLoadingRestWeight(orderid,itemid);
		}
		else 
		{
			
			restwt = sales_OrderRepository.toleranceloading(orderid,itemid);
		}
		
		StatusDTO loadingres=new StatusDTO();
		System.out.println(" / "+restwt+" / " + packing_qty);
		if(restwt>=packing_qty)
		{
			loadingres.setStatus("Yes");
		}
		else
		{
			loadingres.setStatus("No");
		}
		
		return loadingres;
	}
	
	public StatusDTO getLoadingRestWeightJobworkrectify(String pur_cust_refdocno,Double item_qty)
	{
		StatusDTO loadingres=new StatusDTO();
		loadingres.setStatus(sales_OrderRepository.allocationStatus(pur_cust_refdocno,item_qty));
		return loadingres;
	}
	
	public StatusDTO checkjobworkrestwt(String advice_id) 
	{
		StatusDTO loadingres=new StatusDTO();
		loadingres.setStatus(sales_OrderRepository.allocationStatusgrn(advice_id));
		return loadingres;
	}
	
	public StatusDTO getLoadingRestWeightJobwork(String orderid,String itemid,Double packing_qty,String packing, String party) 
	{
		Double restwt =0.00;
		
		restwt = sales_OrderRepository.toleranceloadingjobwork(orderid,itemid);
		
		StatusDTO loadingres=new StatusDTO();
		
		if(restwt>=packing_qty)
		{
			//System.out.println("Check : :" +jobWorkItemAllocationRepository.checkJobAllocationData(itemid,party));
			/*if((jobWorkItemAllocationRepository.checkJobAllocationData(itemid,party))>0)
			{
				//System.out.println("Job : : "+ jobWorkItemAllocationRepository.allocationStatus(itemid,party,packing_qty));
				loadingres.setStatus(jobWorkItemAllocationRepository.allocationStatus(itemid,party,packing_qty));
			}
			else
			{
				loadingres.setStatus("No");
			}*/
			loadingres.setStatus("Yes");
		}
		else
		{
			loadingres.setStatus("No");
		}
		
		return loadingres;
		
	}
	
	public StatusDTO getLoadingRestWeightJobworkold0503(String orderid,String itemid,Double packing_qty,String packing, String party) 
	{
		Double restwt =0.00;
		
		restwt = sales_OrderRepository.toleranceloadingjobwork(orderid,itemid);
		
		StatusDTO loadingres=new StatusDTO();
		
		if(restwt>=packing_qty)
		{
			//System.out.println("Check : :" +jobWorkItemAllocationRepository.checkJobAllocationData(itemid,party));
			if((jobWorkItemAllocationRepository.checkJobAllocationData(itemid,party))>0)
			{
				//System.out.println("Job : : "+ jobWorkItemAllocationRepository.allocationStatus(itemid,party,packing_qty));
				loadingres.setStatus(jobWorkItemAllocationRepository.allocationStatus(itemid,party,packing_qty));
			}
			else
			{
				loadingres.setStatus("No");
			}
			//loadingres.setStatus("Yes");
		}
		else
		{
			loadingres.setStatus("No");
		}
		
		return loadingres;
		
	}
	
	public StatusDTO getLoadingRestWeightJobWorkAllocation(String itemid,String party,Double item_qty) 
	{
		StatusDTO loadingres=new StatusDTO();
		
		if((jobWorkItemAllocationRepository.checkJobAllocationData(itemid,party))>0)
		{
			//System.out.println("Job : : "+ jobWorkItemAllocationRepository.allocationStatus(itemid,party,item_qty));
			loadingres.setStatus(jobWorkItemAllocationRepository.allocationStatus(itemid,party,item_qty));
		}
		else
		{
			loadingres.setStatus("No");
		}
		return loadingres;
	}
	
	public StatusDTO getLoadingRestWeightupdate(String orderid,String itemid,Double packing_qty,String advice_id,String packing,String alter_item_code,String alter_packing)
	{
		StatusDTO loadingres=new StatusDTO();
		// SELECT (CASE WHEN w.weighment_status < 2 THEN (SELECT CASE WHEN COUNT(wd.id)>0 THEN (CASE WHEN SUM((SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid)+wd.mat_wt)>=:packing_qty THEN "Yes" ELSE "No" END) ELSE (CASE WHEN (SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid)>=:packing_qty THEN "Yes" ELSE "No" END)END FROM wm_loading_advice_itm_dtls wd WHERE wd.modified_type = 'INSERTED' AND wd.advice_id =:advice_id AND wd.alter_item_code =:itemid  AND wd.alter_packing =:packingid) ELSE "No" END) AS STATUS FROM wm_loading_advice w WHERE w.advice_id =:advice_id AND w.modified_type ='INSERTED'
		loadingres.setStatus(wm_loading_advice_itm_dtlsRepository.getloadingitemdetailwtalternate(advice_id,itemid,packing_qty,orderid,alter_item_code,alter_packing));
		return loadingres;
	}
	
	
	public StatusDTO getLoadingRestWeightJobworkupdate(String orderid,String itemid,Double packing_qty,String advice_id,String packing,String party)
	{
		StatusDTO loadingres=new StatusDTO();
		
		/*String res=wm_loading_advice_itm_dtlsRepository.getLoadingRestWeightJobworkupdate(advice_id,itemid,packing,packing_qty,orderid);
		if((res.compareToIgnoreCase("Yes")==0))		
		{
			//System.out.println("Value : : "+itemid+" / "+advice_id+" / "+party+" / "+packing_qty);
			res=jobWorkItemAllocationRepository.allocationStatusUpdate(itemid,advice_id,party,packing_qty);
		}
			
		
		loadingres.setStatus(res);
		*/
		
		loadingres.setStatus(wm_loading_advice_itm_dtlsRepository.getLoadingRestWeightJobworkupdate(advice_id,itemid,packing,packing_qty,orderid));
		return loadingres;
	}
	
	public StatusDTO getLoadingRestWeightJobworkupdateold(String orderid,String itemid,Double packing_qty,String advice_id,String packing,String party)
	{
		StatusDTO loadingres=new StatusDTO();
		String res=wm_loading_advice_itm_dtlsRepository.getLoadingRestWeightJobworkupdate(advice_id,itemid,packing,packing_qty,orderid);
		if((res.compareToIgnoreCase("Yes")==0))		
		{
			System.out.println("Value : : "+itemid+" / "+advice_id+" / "+party+" / "+packing_qty);
			res=jobWorkItemAllocationRepository.allocationStatusUpdate(itemid,advice_id,party,packing_qty);
		}
			
		//loadingres.setStatus(wm_loading_advice_itm_dtlsRepository.getLoadingRestWeightJobworkupdate(advice_id,itemid,packing,packing_qty,orderid));
		loadingres.setStatus(res);
		return loadingres;
	}
	
	
	
	public StatusDTO getLoadingRestWeightupdatelooseitem(String orderid,String itemid,Double packing_qty,String advice_id,String packing)
	{
		StatusDTO loadingres=new StatusDTO();
	    //SELECT (SELECT CASE WHEN COUNT(wd.id)>0 THEN (CASE WHEN SUM((SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid)+wd.mat_wt)>=:packing_qty THEN "Yes" ELSE "No" END) ELSE (CASE WHEN (SELECT s.rest_wt FROM loading_sales_order s WHERE s.order_id = :orderid AND s.item_code =:itemid)>=:packing_qty THEN "Yes" ELSE "No" END)END FROM wm_loading_advice_itm_dtls wd WHERE wd.modified_type = 'INSERTED' AND wd.advice_id =:advice_id AND wd.alter_item_code =:itemid  AND wd.alter_packing =:packingid)  AS STATUS FROM wm_loading_advice w WHERE w.advice_id =:advice_id AND w.modified_type ='INSERTED'
		loadingres.setStatus(wm_loading_advice_itm_dtlsRepository.getLoadingRestWeightupdatelooseitem(advice_id,itemid,packing,packing_qty,orderid));
		return loadingres;
	}
	
	public StatusDTO getLoadingRestWeightupdateold(String orderid,String itemid,Double packing_qty,String advice_id,String packing)
	{
		Double restwt = sales_OrderRepository.getLoadingRestWeight(orderid,itemid);
		
		StatusDTO loadingres=new StatusDTO();
		
		Wm_loading_advice unloadetails=wm_loading_adviceRepository.getLoadingDetails(advice_id);
		
		if(unloadetails.getWeighment_status()<2) 
		{
			Wm_loading_advice_itm_dtls itemdetails= new Wm_loading_advice_itm_dtls();
			double finalqty=0;
			
			
			//if(wm_loading_advice_itm_dtlsRepository.checkloadingitems(advice_id,itemid)>0) 
			if(wm_loading_advice_itm_dtlsRepository.checkloadingitemsalternative(advice_id,itemid,packing)>0)
			{
				//itemdetails=wm_loading_advice_itm_dtlsRepository.getLoadingItemDtlsupdatedloadingadvice(advice_id,itemid);
				
				itemdetails=wm_loading_advice_itm_dtlsRepository.getLoadingItemDtlsupdatedloadingadvicealternative(advice_id,itemid,packing);
				finalqty=restwt+itemdetails.getMat_wt();
			}
			else 
			{
				finalqty=restwt;
			}
			
			
			//finalqty=restwt+itemdetails.getMat_wt();

			/*Wm_loading_advice_itm_dtls itemdetails=wm_loading_advice_itm_dtlsRepository.getLoadingItemDtlsupdatedloadingadvice(advice_id,itemid);
			System.out.println("Chk Value : "+restwt);
			System.out.println("Chk Mat Value : "+itemdetails.getMat_wt());
			Double finalqty=restwt+itemdetails.getMat_wt();
			System.out.println("Chk  Final Value : "+finalqty);*/

			if(finalqty>=packing_qty)
			{
				loadingres.setStatus("Yes");
			}
			else
			{
				loadingres.setStatus("No");
			}
		}
		else 
		{
			
			loadingres.setStatus("No");
			
		}
		
		
		
		return loadingres;
	}
	public List<Sales_Order_Pagination_DTO> findsaleorder(String order_id)
	{
		List<Sales_Order> serchsaleorder = new ArrayList<Sales_Order>();
		
		serchsaleorder.add(sales_OrderRepository.getSalesOrderDetailssearch(order_id));
		//System.out.println(serchsaleorder.get(0));
		List<Sales_Order_Pagination_DTO> paginationlist = new ArrayList<Sales_Order_Pagination_DTO>();
		for(int i=0;i<serchsaleorder.size();i++) 
		{
			if(serchsaleorder.get(i).isLoading_status())
			{
				//SalesOrderDeliveryChallanCheckDTO abc=sales_OrderRepository.deliveryChallanCheck(sOrder.getOrder_id());
				List<Object[]> data=new ArrayList<Object[]>();
				data.addAll(sales_OrderRepository.deliveryChallanCheck(serchsaleorder.get(i).getOrder_id())) ;
				 
				 for(final Object o : data)
				    {
					 Object[] obj = (Object[]) o;
					// System.out.println(obj[0].toString() +" // " + obj[1].toString() + " // " + obj[2].toString());
					
					 if(Double.valueOf(obj[1].toString()) > Double.valueOf(obj[2].toString()))
						{
							if(Double.valueOf(obj[2].toString())==0.00)
							{
								serchsaleorder.get(i).setDeleted_by("true");
								serchsaleorder.get(i).setUpdated_by("true");
							}
							else {
								serchsaleorder.get(i).setDeleted_by("true");
								serchsaleorder.get(i).setUpdated_by("false");
								}
						}
						else
						{
							serchsaleorder.get(i).setDeleted_by("false");
							serchsaleorder.get(i).setUpdated_by("false");
						}
					  }
				    }
			else
			{
				serchsaleorder.get(i).setDeleted_by("false");
				serchsaleorder.get(i).setUpdated_by("false");
			}
			//System.out.println("serchsaleorder.get(i).getId() "+serchsaleorder.get(i).getId());
			Sales_Order_Pagination_DTO sale= new Sales_Order_Pagination_DTO(serchsaleorder.get(i).getId(),
					serchsaleorder.get(i).getOrder_id(),
					serchsaleorder.get(i).getOrder_date(),
					serchsaleorder.get(i).getOrder_no(),serchsaleorder.get(i).getOrder_type(),serchsaleorder.get(i).getCustomer(),cust_bussiness_partnerRepository.getCustomer(serchsaleorder.get(i).getCustomer()).getCp_name(),
					serchsaleorder.get(i).getDelivery_date(),serchsaleorder.get(i).getQ_status(),serchsaleorder.get(i).getRef_type(),
					invoice_typeRepository.getSalesInvTypesDtls(serchsaleorder.get(i).getInv_type()).getInvtype_name(),sales_Order_SummaryRepository.getSalesOrdSummary(serchsaleorder.get(i).getOrder_id()).getNet_amount(),serchsaleorder.get(i).isLoading_status(),
					serchsaleorder.get(i).getModified_type(),
					serchsaleorder.get(i).getDeleted_by(),
					serchsaleorder.get(i).getUpdated_by());
			
			paginationlist.add(sale);
		}
		
	 
		 return paginationlist;
	}
	
	/*public List<Map<String,Object>> searchsaleorder(String orderno,String fromdate, String todate,String customername,String finyear)
	{
		System.out.println("orderno:"+orderno);
		String sql="";

		if(orderno.compareToIgnoreCase("")!=0) 
		{
			sql+=" order_no='"+orderno +"' And";
		}
		if(fromdate.compareToIgnoreCase("")!=0) 
		{
			sql+=" s.order_date>='"+fromdate+"' And";
		}
		else 
		{
			sql+=" s.order_date>='"+fromdate+"' And";
		}
		if(todate.compareToIgnoreCase("")!=0)
		{
			sql+=" s.order_date<='"+todate+"' And";
		}
		else 
		{
			sql+=" s.order_date<='"+todate+"' And";
		}
	    if(customername.compareToIgnoreCase("")!=0)
	    {
	    	sql+=" s.customer='"+customername+"' And";
	    }
		if(finyear!=null)
		{
			sql+=" s.fin_year='"+finyear+"' And";
		}
		
		sql=sql.substring(0, sql.length()-3);
		System.out.println("sql::"+sql);
		return sales_OrderRepository.getSaleOrderFastListsearch(sql);
	}*/
	
	public List<Sales_OrderDTO> searchsaleorder(String orderno,String fromdate, String todate,String customername,String finyear)
	{
		List<Sales_Order> serchsaleorder =new ArrayList<Sales_Order>();
		//System.out.println("finyear :: "+finyear);
		String tablename="sales_order",party_name="customer",order_no="order_no",order_date="order_date";
		serchsaleorder.addAll(sales_OrderRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,customername,finyear));
		
		//List<Sales_Order> modelList=sales_OrderRepository.getSaleOrderList(currDate);
		
		Type listType = new TypeToken<List<Sales_OrderDTO>>() {}.getType();
		List<Sales_OrderDTO> ordList = new ModelMapper().map(serchsaleorder,listType);
		ordList.forEach((sOrder) -> {
				if(Utility.isNullOrEmpty(sOrder.getCustomer())) {
					sOrder.setCustomer_name(cust_bussiness_partnerRepository.getCustomer(sOrder.getCustomer()).getCp_name());
				}else {sOrder.setCustomer_name("None");}
				
				if(Utility.isNullOrEmpty(sOrder.getInv_type())) {
					sOrder.setInv_type(invoice_typeRepository.getSalesInvTypesDtls(sOrder.getInv_type()).getInvtype_name());
				}else {sOrder.setInv_type("None");}
				System.out.println("order id::"+sOrder.getOrder_id());
			
		/*	if(sOrder.isLoading_status())
			{
				//SalesOrderDeliveryChallanCheckDTO abc=sales_OrderRepository.deliveryChallanCheck(sOrder.getOrder_id());
				List<Object[]> data=new ArrayList<Object[]>();
				data.addAll(sales_OrderRepository.deliveryChallanCheck(sOrder.getOrder_id())) ;
				 
				 for(final Object o : data)
				    {
					 Object[] obj = (Object[]) o;
					 System.out.println(obj[0].toString() +" // " + obj[1].toString() + " // " + obj[2].toString());
					
					 if(Double.valueOf(obj[1].toString()) > Double.valueOf(obj[2].toString()))
						{
							if(Double.valueOf(obj[2].toString())==0.00)
							{
								sOrder.setDeleted_by("true");
								sOrder.setUpdated_by("true");
							}
							else {
									sOrder.setDeleted_by("true");
									sOrder.setUpdated_by("false");
								}
						}
						else
						{
							sOrder.setDeleted_by("false");
							sOrder.setUpdated_by("false");
						}
					  }
				    }
			else
			{
				sOrder.setDeleted_by("false");
				sOrder.setUpdated_by("false");
			}*/
				
				//System.err.println("inv_type: "+sOrder.getInv_type());
				//sOrder.setNet_amount(sales_Order_SummaryRepository.getSalesOrdSummary(sOrder.getOrder_id()).getNet_amount());
				if(sOrder.getInv_type().compareToIgnoreCase("JOB WORK INVOICE")==0)
				{
					sOrder.setNet_amount(Double.valueOf(sOrder.getTotal_job_amt()));
				}
				else
				{
					sOrder.setNet_amount(sales_Order_Item_DtlsRepository.getSalesOrdItemAmt(sOrder.getOrder_id()));
				}
				
				System.err.println("Order No: "+sOrder.getNet_amount());
				//net = amt+tax-dis
				sOrder.setOrder_date(Utility.convertDateDDMMYYYY(sOrder.getOrder_date()));
			});
		
		return ordList;
	}
	
	/*public List<Sales_Order_Pagination_DTO> searchsaleorder(String orderno,String fromdate, String todate,String customername,String finyear)
	{
		List<Sales_Order> serchsaleorder =new ArrayList<Sales_Order>();
		//System.out.println("finyear :: "+finyear);
		String tablename="sales_order",party_name="customer",order_no="order_no",order_date="order_date";
		serchsaleorder.addAll(sales_OrderRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,fromdate,todate,customername,finyear));
		
		//System.out.println(serchsaleorder.get(0));
		List<Sales_Order_Pagination_DTO> paginationlist = new ArrayList<Sales_Order_Pagination_DTO>();
		for(int i=0;i<serchsaleorder.size();i++) 
		{
			if(serchsaleorder.get(i).isLoading_status())
			{
				//SalesOrderDeliveryChallanCheckDTO abc=sales_OrderRepository.deliveryChallanCheck(sOrder.getOrder_id());
				List<Object[]> data=new ArrayList<Object[]>();
				data.addAll(sales_OrderRepository.deliveryChallanCheck(serchsaleorder.get(i).getOrder_id())) ;
				 
				 for(final Object o : data)
				    {
					 Object[] obj = (Object[]) o;
					// System.out.println(obj[0].toString() +" // " + obj[1].toString() + " // " + obj[2].toString());
					
					 if(Double.valueOf(obj[1].toString()) > Double.valueOf(obj[2].toString()))
						{
							if(Double.valueOf(obj[2].toString())==0.00)
							{
								serchsaleorder.get(i).setDeleted_by("true");
								serchsaleorder.get(i).setUpdated_by("true");
							}
							else {
								serchsaleorder.get(i).setDeleted_by("true");
								serchsaleorder.get(i).setUpdated_by("false");
								}
						}
						else
						{
							serchsaleorder.get(i).setDeleted_by("false");
							serchsaleorder.get(i).setUpdated_by("false");
						}
					  }
				 
				 if(serchsaleorder.get(i).getInv_type().compareToIgnoreCase("JOB WORK INVOICE")==0)
					{
						serchsaleorder.get(i).setTotal_job_amt(Double.valueOf(serchsaleorder.get(i).getTotal_job_amt()));
					}
					else
					{
						serchsaleorder.get(i).setNet_amount(sales_Order_Item_DtlsRepository.getSalesOrdItemAmt(serchsaleorder.get(i).getOrder_id()));
					}
				    }
			else
			{
				serchsaleorder.get(i).setDeleted_by("false");
				serchsaleorder.get(i).setUpdated_by("false");
			}
			
			
			//System.out.println("serchsaleorder.get(i).getId() "+serchsaleorder.get(i).getId());
			Sales_Order_Pagination_DTO sale= new Sales_Order_Pagination_DTO(serchsaleorder.get(i).getId(),
					serchsaleorder.get(i).getOrder_id(),
					serchsaleorder.get(i).getOrder_date(),
					serchsaleorder.get(i).getOrder_no(),serchsaleorder.get(i).getOrder_type(),serchsaleorder.get(i).getCustomer(),cust_bussiness_partnerRepository.getCustomer(serchsaleorder.get(i).getCustomer()).getCp_name(),
					serchsaleorder.get(i).getDelivery_date(),serchsaleorder.get(i).getQ_status(),serchsaleorder.get(i).getRef_type(),
					invoice_typeRepository.getSalesInvTypesDtls(serchsaleorder.get(i).getInv_type()).getInvtype_name(),
					//sales_Order_SummaryRepository.getSalesOrdSummary(serchsaleorder.get(i).getOrder_id()).getNet_amount(),
					sales_Order_Item_DtlsRepository.getSalesOrdItemAmt(serchsaleorder.get(i).getOrder_id()),
					serchsaleorder.get(i).isLoading_status(),
					serchsaleorder.get(i).getModified_type(),
					serchsaleorder.get(i).getDeleted_by(),
					serchsaleorder.get(i).getUpdated_by()
					);
			
			paginationlist.add(sale);
		}
		
		List<Sales_Order_Pagination_DTO> allData = paginationlist
				  .stream()
				  .filter(c -> c.getModified_type().equals("INSERTED"))
				 // .sorted(Comparator.comparing(Sales_Order_Pagination_DTO::getOrder_date).reversed().thenComparing(Sales_Order_Pagination_DTO::getOrder_no).reversed())
				  .sorted(Comparator.comparing(Sales_Order_Pagination_DTO::getOrder_date).
						  thenComparingInt(
                          d -> d.getOrder_date().length() + d.getOrder_no().length())
                  .thenComparing(Sales_Order_Pagination_DTO::getOrder_no).reversed())
				  .collect(Collectors.toList());
		
		//System.out.println(paginationlist.get(0));
		return allData;
		
		
	}*/
	
	public List<Sales_OrderDTO> getSaleOrderList(String currDate,String finyear)
	{
		List<Sales_Order> modelList =new ArrayList<Sales_Order>();
		String tablename="sales_order",party_name="customer",order_no="order_no",order_date="order_date";
		String orderno="",customername="";
		modelList.addAll(sales_OrderRepository.getsearchdata(tablename,party_name,order_no,order_date,orderno,currDate,currDate,customername,finyear));
		
		//List<Sales_Order> modelList=sales_OrderRepository.getSaleOrderList(currDate);
		
		Type listType = new TypeToken<List<Sales_OrderDTO>>() {}.getType();
		List<Sales_OrderDTO> ordList = new ModelMapper().map(modelList,listType);
		ordList.forEach((sOrder) -> {
				if(Utility.isNullOrEmpty(sOrder.getCustomer())) {
					sOrder.setCustomer_name(cust_bussiness_partnerRepository.getCustomer(sOrder.getCustomer()).getCp_name());
				}else {sOrder.setCustomer_name("None");}
				
				if(Utility.isNullOrEmpty(sOrder.getInv_type())) {
					sOrder.setInv_type(invoice_typeRepository.getSalesInvTypesDtls(sOrder.getInv_type()).getInvtype_name());
				}else {sOrder.setInv_type("None");}
				System.out.println("order id::"+sOrder.getOrder_id());
			
			/*if(sOrder.isLoading_status())
			{
				//SalesOrderDeliveryChallanCheckDTO abc=sales_OrderRepository.deliveryChallanCheck(sOrder.getOrder_id());
				List<Object[]> data=new ArrayList<Object[]>();
				data.addAll(sales_OrderRepository.deliveryChallanCheck(sOrder.getOrder_id())) ;
				 
				 for(final Object o : data)
				    {
					 Object[] obj = (Object[]) o;
					 System.out.println(obj[0].toString() +" // " + obj[1].toString() + " // " + obj[2].toString());
					
					 if(Double.valueOf(obj[1].toString()) > Double.valueOf(obj[2].toString()))
						{
							if(Double.valueOf(obj[2].toString())==0.00)
							{
								sOrder.setDeleted_by("true");
								sOrder.setUpdated_by("true");
							}
							else {
									sOrder.setDeleted_by("true");
									sOrder.setUpdated_by("false");
								}
						}
						else
						{
							sOrder.setDeleted_by("false");
							sOrder.setUpdated_by("false");
						}
					  }
				    }
			else
			{
				sOrder.setDeleted_by("false");
				sOrder.setUpdated_by("false");
			}*/
				
				//System.err.println("inv_type: "+sOrder.getInv_type());
				//sOrder.setNet_amount(sales_Order_SummaryRepository.getSalesOrdSummary(sOrder.getOrder_id()).getNet_amount());
				if(sOrder.getInv_type().compareToIgnoreCase("JOB WORK INVOICE")==0)
				{
					sOrder.setNet_amount(Double.valueOf(sOrder.getTotal_job_amt()));
				}
				else
				{
					sOrder.setNet_amount(sales_Order_Item_DtlsRepository.getSalesOrdItemAmt(sOrder.getOrder_id()));
				}
				
				System.err.println("Order No: "+sOrder.getNet_amount());
				//net = amt+tax-dis
				sOrder.setOrder_date(Utility.convertDateDDMMYYYY(sOrder.getOrder_date()));
			});
		
		return ordList;
	}
	
	public List<Map<String,Object>> getSaleOrderFastList(String currDate,String finyear)
	{
		return sales_OrderRepository.getSaleOrderFastList(currDate,finyear);
	}
	
	public Sales_Order getSalesOrderDetailsthdeliverchallan(String deliveryid) 
	{
		Delivery_challan getchallandetails=delivery_challanRepository.getDeliveryChallanDtls(deliveryid);
		
		
		Wm_loading_advice loadingdetails=wm_loading_adviceRepository.getLoadingDetails(getchallandetails.getReferance_id());
		
		Sales_Order salesdetails= sales_OrderRepository.getSalesOrderDetails(loadingdetails.getReferance_id());
		
		
		return salesdetails;
		
		
	}
	
	public List<Map<String, Object>> getsaleorderjobworkprice(String deliveryid)
	{
		
		//Delivery_challan getchallandetails=delivery_challanRepository.getDeliveryChallanDtls(deliveryid);
		
		//Wm_loading_advice loadingdetails=wm_loading_adviceRepository.getLoadingDetails(getchallandetails.getReferance_id());
		
		//Sales_Order salesdetails= sales_OrderRepository.getSalesOrderDetails(loadingdetails.getReferance_id());
		
		
		//SELECT * FROM `sales_order_item_dtls_for_jobwork_price` WHERE order_id=(SELECT order_id FROM channeldeliverycustomerjobwork WHERE delivery_cid='DC08732')
		return sales_OrderRepository.getSalesOrdTServiceItemdc(deliveryid);
		
		
	}
	
	public Sales_Order updatesalesorder(Long id) 
	{
		Optional<Return_approval_note> op=return_approval_noteRepository.findById(id);
		
		double restwt=sales_OrderRepository.getrestwet_forloadingpopup(op.get().getOrder_id());
		if(restwt ==0) 
		{
			sales_OrderRepository.updatesalesorder_salesreturn(op.get().getOrder_id(),op.get().getSalesreturnid());
		}
			
		Sales_Order sales= new Sales_Order();
		return sales;
	}
	
	@Autowired
	Item_rate_dtlsRepository item_rate_dtlsRepository;
	
	public Item_rate_dtls getRateChartItemSO(String itemid,String packingcode,String company,String business_unit,String order_date, String pricebasedon )
	{
		Item_rate_dtls rate=new Item_rate_dtls();
		
		rate=item_rate_dtlsRepository.getRateChartItemSOList(itemid,packingcode,order_date,pricebasedon);
		
		return rate;
	}
	
	public List<Item_rate_dtls> getRateChartItemSOforItem(String itemid,String packingcode,String company,String business_unit,String order_date)
	{
		List<Item_rate_dtls> rate=new ArrayList<Item_rate_dtls>();
		
		rate.addAll(item_rate_dtlsRepository.getRateChartItemSOforItem(itemid,packingcode,order_date));
		return rate;
	}
	
	public StatusDTO getnumtowordsaleorder(String orderid) 
	{
		
		double totalamount=sales_Order_Item_DtlsRepository.getnumtowordsaleorder(orderid);
		System.out.println(totalamount);
		String amountsplit[]=String.valueOf(totalamount).split("[.]");
		
		System.out.println(String.valueOf(totalamount) +" / " + amountsplit[0] + " // " + amountsplit[1]);
		String ruppes=NumToWord.numberConvert(Integer.parseInt(amountsplit[0]));//wordConvert
		String amountword="";
		if(amountsplit[1].compareToIgnoreCase("0") == 0) 
		{
			 amountword= ruppes +" Rupees Only" ;
		}
		else 
		{
			if(amountsplit[1].length()==1) 
			{
				String paisa=NumToWord.numberConvert(Integer.parseInt(amountsplit[1])*10);	//wordConvert
				amountword= ruppes +" Rupees and " + paisa + " Paisa Only";
			}
			else 
			{
				String paisa=NumToWord.numberConvert(Integer.parseInt(amountsplit[1]));	//wordConvert
				amountword= ruppes +" Rupees and " + paisa + " Paisa Only";
			}
		}
	   // System.out.println("amountword :: "+amountword);
	    StatusDTO res =new StatusDTO();
	    res.setStatus(amountword);
		return res;
	}
	
	public ResponsemsgDTO checkcustomeramount(String orderid,String finyear)
	{
		ResponsemsgDTO res =new ResponsemsgDTO();
		
		
		String party=sales_OrderRepository.getSalesOrderDetails(orderid).getCustomer();
		double amount =sales_OrderRepository.getSalesOrderDetailsaprty(party,finyear);
		res.setMsg(sales_Order_Terms_ConRepository.getlastorder(orderid));
		
		if(amount>=4500000) 
		{
			res.setStatus("No");
		}
		else 
		{
			res.setStatus("Yes");
		}
		
		return res;
	}
	
	public StatusDTO updateSalesOrderWithLoadingItemDetails(String orderid) 
	{
		int abc=0;
		boolean challanstatus=false;
		LocalDateTime ldt = LocalDateTime.now();
		//System.out.println("orderid " + orderid+"//"+sales_OrderRepository.countlengthSalesItem(orderid)+"///"+wm_loading_adviceRepository.countlength(orderid));
		StatusDTO loadingstatus =new StatusDTO();
		challanstatus = wm_loading_adviceRepository.getchallan(orderid);
		//System.out.println("challanstatus:::"+challanstatus);
		if(sales_OrderRepository.countlengthSalesItem(orderid)>=wm_loading_adviceRepository.countlength(orderid) && challanstatus==false)
		{
			abc=sales_OrderRepository.updateSalesorderwithLoadingAdvice(orderid,ldt);
			loadingstatus.setStatus(String.valueOf(abc));
		}
		else {
			loadingstatus.setStatus("");
		}
		
		return loadingstatus;
	}
	
	public List<Map<String, Object>> getSalesOrderSummaryCatagorywiseList(String catagory,String catagoryname,String fromdate,String todate,String bunit)
 	{
 		List<Map<String,Object>> salesorderList=new ArrayList <>();
 		
 		String multicatahgory[]=catagoryname.split(",");
			ArrayList<String> catagorymultiple=new ArrayList<String>();
			for(int i=0;i<multicatahgory.length;i++)
			{
				catagorymultiple.add(multicatahgory[i]);
			}
			
 		if(catagory.compareToIgnoreCase("Partywise")==0)
 		{
 			//System.out.println("fdate:"+fromdate+"//"+todate+"//"+bunit+"//"+catagoryname);
 			salesorderList.addAll(sales_OrderRepository.getSalesOrderSummaryPartywise(catagorymultiple,fromdate,todate,bunit));
 		}
 		else if(catagory.compareToIgnoreCase("Brokerwise")==0)
 		{
 			//System.out.println("fdate1:"+fromdate+"//"+todate+"//"+bunit+"//"+catagoryname);
 			salesorderList.addAll(sales_OrderRepository.getSalesOrderSummaryBrokerwise(catagorymultiple,fromdate,todate,bunit));
 		}
 		else
 		{
 			//System.out.println("fdate2:"+fromdate+"//"+todate+"//"+bunit+"//"+catagoryname);
 			salesorderList.addAll(sales_OrderRepository.getSalesOrderSummaryItemwise(catagorymultiple,fromdate,todate,bunit));
 		}
 		return salesorderList;
 	}
	
	public List<Map<String, Object>> getSalesOrderMiscList(String catagory,String fromdate,String todate,String bunit,String broker,String customer)
 	{
 		List<Map<String,Object>> salesorderList=new ArrayList <>();
 		if(catagory.compareToIgnoreCase("Partywise")==0)
 		{
 			//System.out.println("fdate:"+fromdate+"//"+todate+"//"+bunit+"//"+customer);
 			String multiparty[]=customer.split(",");
			ArrayList<String> customermultiple=new ArrayList<String>();
			for(int i=0;i<multiparty.length;i++)
			{
				customermultiple.add(multiparty[i]);
			}
 			salesorderList.addAll(sales_OrderRepository.getSalesOrderMiscPartyList(fromdate,todate,bunit,customermultiple));
 		}
 		else if(catagory.compareToIgnoreCase("Brokerwise")==0)
 		{
 			//System.out.println("fdate1:"+fromdate+"//"+todate+"//"+bunit+"//"+broker);
 			String multibroker[]=broker.split(",");
			ArrayList<String> brokermultiple=new ArrayList<String>();
			for(int i=0;i<multibroker.length;i++)
			{
				brokermultiple.add(multibroker[i]);
			}
 			salesorderList.addAll(sales_OrderRepository.getSalesOrderMiscBrokerList(fromdate,todate,bunit,brokermultiple));
 		}
 		else
 		{
 			//System.out.println("fdate2:"+fromdate+"//"+todate+"//"+bunit);
 			salesorderList.addAll(sales_OrderRepository.getSalesOrderMiscAllList(fromdate,todate,bunit));
 		}
 		
 		return salesorderList;
 	}
	
	public List<Map<String, Object>> salesOrderFinishedItemlist(String company,String fromdate,String todate,String business_unit)
	 {
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 
		 modelList.addAll(sales_OrderRepository.salesOrderFinishedItemlist(company,fromdate,todate,business_unit));
		
		 return modelList;
	 }
	
	public List<Map<String, Object>> salesOrderBrokerlist(String company,String fromdate,String todate,String business_unit)
	 {
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 
		 modelList.addAll(sales_OrderRepository.salesOrderBrokerlist(company,fromdate,todate,business_unit));
		
		 return modelList;
	 }
	
	public List<Map<String, Object>> salesOrderPartylist(String company,String fromdate,String todate,String business_unit)
	 {
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 
		 modelList.addAll(sales_OrderRepository.salesOrderPartylist(company,fromdate,todate,business_unit));
		
		 return modelList;
	 }
	
	public List<Map<String, Object>> getSalesOrdTransChgsDynList(String ordid)
	 {
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 
		 modelList.addAll(sales_Order_Trans_Chgs_dynRepository.getSalesOrdTransChgsDynList(ordid));
		
		 return modelList;
	 }
	
	public Map<String,Object> getSalesOrderData(String referance_id, String delivery_cid)
	{
		String transCode=sales_OrderRepository.getTransCode(delivery_cid);
		
		return sales_OrderRepository.getSalesOrderData(referance_id,transCode);
	}
	
	public Map<String,Object> getdeliverychallanData(String delivery_cid)
	{
		return sales_OrderRepository.getdeliverychallanData(delivery_cid);
	}
	
	public List<Map<String, Object>> transporterNameChgsList(String ordid)
	 {
		 List<Map<String,Object>> modelList =new ArrayList<Map<String,Object>>();
		 
		 modelList.addAll(sales_Order_Trans_Chgs_dynRepository.transporterNameChgsList(ordid));
		
		 return modelList;
	 }
	
	public Sales_Order_Trans_Chgs_dyn  getsaleordercharges(String transporter_Id,String referance_id) 
	{
		return sales_Order_Trans_Chgs_dynRepository.getsaleordercharges(transporter_Id,referance_id);
	}
	
	public List<Map<String, Object>>  getSalesOrdItemDtlsnew(String orderid) 
	{
		return sales_Order_Item_DtlsRepository.getSalesOrdItemDtlsnew(orderid);
	}
	
	public List<Map<String, Object>> getSalesOrderReport(String fromdate, String todate)
	{
		return sales_OrderRepository.getSalesOrderReport(fromdate, todate);
	}
	
	public List<Map<String, Object>> getSalesOrderReportOrderWise(String orderno)
	{
		return sales_OrderRepository.getSalesOrderReportOrderWise(orderno);
	}
	
	public List<Map<String, Object>> findJobSalesOrders(String bunit,String party,String advdate)
	{
		return sales_OrderRepository.findJobSalesOrders(bunit,party,advdate);
	}
	
	public List<Map<String, Object>> getTrialdata(String fromdate,String todate)
	{
		return sales_OrderRepository.getTrialdata(fromdate,todate);
	}
}
