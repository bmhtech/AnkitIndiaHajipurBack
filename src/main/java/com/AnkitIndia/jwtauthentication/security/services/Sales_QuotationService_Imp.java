package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDGenerator;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Broker_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Docs;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Party_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Shipment_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Summary;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Summary_dyn;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Termination;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Terms_Con;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation_Trans_Info;
import com.AnkitIndia.jwtauthentication.model.Uniqueid;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_QuotationRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Quotation_Broker_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Quotation_DocsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Quotation_Item_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Quotation_Party_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Quotation_Shipment_DtlsRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Quotation_SummaryRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Quotation_Summary_dynRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Quotation_Terms_ConRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Quotation_Trans_InfoRepository;
import com.AnkitIndia.jwtauthentication.repository.UniqueidRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_QuotationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Quotation_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Broker_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_SummaryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Summary_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Terms_ConDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Trans_InfoDTO;

@Service
@Repository
public class Sales_QuotationService_Imp implements Sales_QuotationService {
	
	@Autowired
	Sales_QuotationRepository sales_QuotationRepository;
	
	@Autowired
	UniqueidRepository uniqueidRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Sales_OrderRepository sales_OrderRepository;
	
	public SalesSequenceIdDTO getSalesQuotSequenceId(String prefix,String orderdate,String type)
	{
		int slno=0;
		String sno=sales_QuotationRepository.countQuotationOrder(orderdate,type);
		if(type.compareTo("Formal")==0) {type="FOR";}
		if(type.compareTo("Informal")==0) {type="INFOR";}
		
		if(sno != null ) {
			slno=Integer.parseInt(sno);
		}
		String date[] = orderdate.split("-");
		prefix=prefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		System.err.println(prefix);
		
		String gen_sno=UniqueIDTransaction.uniqueId4(prefix,slno);
		
		Type listType = new TypeToken<SalesSequenceIdDTO>() {}.getType();
		
		SalesSequenceIdDTO genCode = new ModelMapper().map(gen_sno,listType);
		
		genCode.setSequenceid(gen_sno);
		
		return genCode;
	}
	
	@Transactional
	public Sales_Quotation save(Sales_Quotation quotation)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(sales_QuotationRepository.countId() != null ) {
			slno=Long.parseLong(sales_QuotationRepository.countId());
		}
		String prefix="SQ";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		quotation.setQuotation_id(gen_sno);
		
		/*Start checking transaction no for unique */
		System.err.println("First:>>"+quotation.getQuotation_no());
		long nslno=0;String type="",tprefix="QUOT";
		String tsno=sales_QuotationRepository.countQuotationOrder(quotation.getQuotation_date(),quotation.getQuo_type());
		if(quotation.getQuo_type().compareTo("Formal")==0) {type="FOR";}
		if(quotation.getQuo_type().compareTo("Informal")==0) {type="INFOR";}
		
		if(tsno != null ) 
		{
			nslno=Integer.parseInt(tsno);
		}
		String date[] = quotation.getQuotation_date().split("-");
		tprefix=tprefix+"-"+type+"-"+date[2]+date[1]+date[0].substring(2,4)+"-";
		String gen_tslno=UniqueIDTransaction.uniqueId4(tprefix,nslno);
		quotation.setQuotation_no(gen_tslno);
		System.err.println("Last:>>>"+quotation.getQuotation_no());
		/*End checking transaction no for unique */
		
		if(Utility.isNullOrEmpty(quotation.getCustomer())) {
			quotation.setCustomername(cust_bussiness_partnerRepository.getCustomer(quotation.getCustomer()).getCp_name());
		}else {quotation.setCustomername("None");}
		
		quotation.setModified_type("INSERTED");
		quotation.setInserted_by(userRepository.getUserDetails(quotation.getUsername()).getName());
		quotation.setInserted_on(ldt);
		quotation.setUpdated_by("NA");
		quotation.setUpdated_on(ldt);
		quotation.setDeleted_by("NA");
		quotation.setDeleted_on(ldt);
		quotation.setSale_orderused(false);
		quotation.setTerminate(false);	//Dynamic
		
		
		Set<Sales_Quotation_Item_Dtls> itemSet = new HashSet<Sales_Quotation_Item_Dtls>();
		itemSet.addAll(quotation.getSales_Quotation_Item_Dtls());
		for(Sales_Quotation_Item_Dtls itemDts : itemSet)
		{
			itemDts.setQuotation_id(gen_sno);
			itemDts.setQuotation_no(quotation.getQuotation_no());
			itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
			if(itemDts.getPacking().compareTo("")!=0) {
				itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
			}
			itemDts.setCompany_id(quotation.getCompany_id());
			itemDts.setFin_year(quotation.getFin_year());
			itemDts.setModified_type("INSERTED");
			itemDts.setInserted_by(quotation.getInserted_by());
			itemDts.setInserted_on(quotation.getInserted_on());
			itemDts.setUpdated_by("NA");
			itemDts.setUpdated_on(ldt);
			itemDts.setDeleted_by("NA");
			itemDts.setDeleted_on(ldt);
		
		}
		quotation.setSales_Quotation_Item_Dtls(itemSet);
		
		//Dynamic
		Set<Sales_Quotation_Broker_Dtls> brokerSet = new HashSet<Sales_Quotation_Broker_Dtls>();
		brokerSet.addAll(quotation.getSales_Quotation_Broker_Dtls());
		for(Sales_Quotation_Broker_Dtls brokerDts : brokerSet)
		{
			brokerDts.setQuotation_id(gen_sno);
			brokerDts.setQuotation_no(quotation.getQuotation_no());
			brokerDts.setCompany_id(quotation.getCompany_id());
			brokerDts.setFin_year(quotation.getFin_year());
			brokerDts.setModified_type("INSERTED");
			brokerDts.setInserted_by(quotation.getInserted_by());
			brokerDts.setInserted_on(quotation.getInserted_on());
			brokerDts.setUpdated_by("NA");
			brokerDts.setUpdated_on(ldt);
			brokerDts.setDeleted_by("NA");
			brokerDts.setDeleted_on(ldt);
		
		}
		quotation.setSales_Quotation_Broker_Dtls(brokerSet);
		
		//Static
		Set<Sales_Quotation_Summary> summSet = new HashSet<Sales_Quotation_Summary>();
		summSet.add(quotation.getSales_Quotation_Summary());
		for(Sales_Quotation_Summary summ : summSet)
		{
			summ.setQuotation_id(gen_sno);
			summ.setQuotation_no(quotation.getQuotation_no());
			summ.setCompany_id(quotation.getCompany_id());
			summ.setFin_year(quotation.getFin_year());
			summ.setModified_type("INSERTED");
			summ.setInserted_by(quotation.getInserted_by());
			summ.setInserted_on(quotation.getInserted_on());
			summ.setUpdated_by("NA");
			summ.setUpdated_on(ldt);
			summ.setDeleted_by("NA");
			summ.setDeleted_on(ldt);
		}
		
		if(!summSet.isEmpty())
		{
			quotation.setSales_Quotation_Summary(summSet.iterator().next());
		}
		
		//Dynamic
		Set<Sales_Quotation_Summary_dyn> sumDtlsSet = new HashSet<Sales_Quotation_Summary_dyn>();
		sumDtlsSet.addAll(quotation.getSales_Quotation_Summary_dyn());
		for(Sales_Quotation_Summary_dyn summDts : sumDtlsSet)
		{
			summDts.setQuotation_id(gen_sno);
			summDts.setQuotation_no(quotation.getQuotation_no());
			summDts.setCompany_id(quotation.getCompany_id());
			summDts.setFin_year(quotation.getFin_year());
			summDts.setModified_type("INSERTED");
			summDts.setInserted_by(quotation.getInserted_by());
			summDts.setInserted_on(quotation.getInserted_on());
			summDts.setUpdated_by("NA");
			summDts.setUpdated_on(ldt);
			summDts.setDeleted_by("NA");
			summDts.setDeleted_on(ldt);
		
		}
		quotation.setSales_Quotation_Summary_dyn(sumDtlsSet);
		
		//Static
		Set<Sales_Quotation_Trans_Info> transSet = new HashSet<Sales_Quotation_Trans_Info>();
		transSet.add(quotation.getSales_Quotation_Trans_Info());
		for(Sales_Quotation_Trans_Info trans : transSet)
		{
			trans.setQuotation_id(gen_sno);
			trans.setQuotation_no(quotation.getQuotation_no());
			trans.setTransporter_name(trans.getTrans_code());
			trans.setCompany_id(quotation.getCompany_id());
			trans.setFin_year(quotation.getFin_year());
			trans.setModified_type("INSERTED");
			trans.setInserted_by(quotation.getInserted_by());
			trans.setInserted_on(quotation.getInserted_on());
			trans.setUpdated_by("NA");
			trans.setUpdated_on(ldt);
			trans.setDeleted_by("NA");
			trans.setDeleted_on(ldt);
		}
		
		if(!transSet.isEmpty())
		{
			quotation.setSales_Quotation_Trans_Info(transSet.iterator().next());
		}
		
		//Static
		Set<Sales_Quotation_Terms_Con> termSet = new HashSet<Sales_Quotation_Terms_Con>();
		termSet.add(quotation.getSales_Quotation_Terms_Con());
		for(Sales_Quotation_Terms_Con termdtls : termSet)
		{
			termdtls.setQuotation_id(gen_sno);
			termdtls.setQuotation_no(quotation.getQuotation_no());
			termdtls.setCompany_id(quotation.getCompany_id());
			termdtls.setFin_year(quotation.getFin_year());
			termdtls.setModified_type("INSERTED");
			termdtls.setInserted_by(quotation.getInserted_by());
			termdtls.setInserted_on(quotation.getInserted_on());
			termdtls.setUpdated_by("NA");
			termdtls.setUpdated_on(ldt);
			termdtls.setDeleted_by("NA");
			termdtls.setDeleted_on(ldt);
		}
		
		if(!termSet.isEmpty())
		{
			quotation.setSales_Quotation_Terms_Con(termSet.iterator().next());
		}
		
		//Dynamic
		Set<Sales_Quotation_Party_Dtls> parSet = new HashSet<Sales_Quotation_Party_Dtls>();
		parSet.addAll(quotation.getSales_Quotation_Party_Dtls());
		for(Sales_Quotation_Party_Dtls parDts : parSet)
		{
			parDts.setQuotation_id(gen_sno);
			parDts.setQuotation_no(quotation.getQuotation_no());
			parDts.setP_name(cust_bussiness_partnerRepository.getCustomer(quotation.getCustomer()).getCp_name());
			parDts.setCompany_id(quotation.getCompany_id());
			parDts.setFin_year(quotation.getFin_year());
			parDts.setModified_type("INSERTED");
			parDts.setInserted_by(quotation.getInserted_by());
			parDts.setInserted_on(quotation.getInserted_on());
			parDts.setUpdated_by("NA");
			parDts.setUpdated_on(ldt);
			parDts.setDeleted_by("NA");
			parDts.setDeleted_on(ldt);
		
		}
		quotation.setSales_Quotation_Party_Dtls(parSet);
		
		
		//Static
		Set<Sales_Quotation_Shipment_Dtls> shipSet = new HashSet<Sales_Quotation_Shipment_Dtls>();
		shipSet.add(quotation.getSales_Quotation_Shipment_Dtls());
		for(Sales_Quotation_Shipment_Dtls shipdtls : shipSet)
		{
			shipdtls.setQuotation_id(gen_sno);
			shipdtls.setQuotation_no(quotation.getQuotation_no());
			shipdtls.setCompany_id(quotation.getCompany_id());
			shipdtls.setFin_year(quotation.getFin_year());
			shipdtls.setModified_type("INSERTED");
			shipdtls.setInserted_by(quotation.getInserted_by());
			shipdtls.setInserted_on(quotation.getInserted_on());
			shipdtls.setUpdated_by("NA");
			shipdtls.setUpdated_on(ldt);
			shipdtls.setDeleted_by("NA");
			shipdtls.setDeleted_on(ldt);
		}
		
		if(!shipSet.isEmpty())
		{
			quotation.setSales_Quotation_Shipment_Dtls(shipSet.iterator().next());
		}
		
		
		//Dynamic
		Set<Sales_Quotation_Docs> docSet = new HashSet<Sales_Quotation_Docs>();
		docSet.addAll(quotation.getSales_Quotation_Docs());
		for(Sales_Quotation_Docs docDts : docSet)
		{
			docDts.setQuotation_id(gen_sno);
			docDts.setQuotation_no(quotation.getQuotation_no());
			docDts.setCompany_id(quotation.getCompany_id());
			docDts.setFin_year(quotation.getFin_year());
			docDts.setModified_type("INSERTED");
			docDts.setInserted_by(quotation.getInserted_by());
			docDts.setInserted_on(quotation.getInserted_on());
			docDts.setUpdated_by("NA");
			docDts.setUpdated_on(ldt);
			docDts.setDeleted_by("NA");
			docDts.setDeleted_on(ldt);
		
		}
		quotation.setSales_Quotation_Docs(docSet);
		
		//Static
		/*Set<Sales_Quotation_Termination> tSet = new HashSet<Sales_Quotation_Termination>();
		tSet.add(quotation.getSales_Quotation_Termination());
		for(Sales_Quotation_Termination termdtls : tSet)
		{
			termdtls.setQuotation_id(gen_sno);
			termdtls.setQuotation_no(quotation.getQuotation_no());
			termdtls.setCompany_id(quotation.getCompany_id());
			termdtls.setFin_year(quotation.getFin_year());
			termdtls.setModified_type("INSERTED");
			termdtls.setInserted_by(quotation.getInserted_by());
			termdtls.setInserted_on(quotation.getInserted_on());
			termdtls.setUpdated_by("NA");
			termdtls.setUpdated_on(ldt);
			termdtls.setDeleted_by("NA");
			termdtls.setDeleted_on(ldt);
		}
		
		if(!tSet.isEmpty())
		{
			quotation.setSales_Quotation_Termination(tSet.iterator().next());
		}*/
		
		return sales_QuotationRepository.save(quotation);
	}
	
	@Autowired
	Sales_Quotation_Item_DtlsRepository sales_Quotation_Item_DtlsRepository;
	
	@Autowired
	Sales_Quotation_Broker_DtlsRepository sales_Quotation_Broker_DtlsRepository;
	
	@Autowired
	Sales_Quotation_Summary_dynRepository sales_Quotation_Summary_dynRepository;

	@Autowired
	Sales_Quotation_Party_DtlsRepository sales_Quotation_Party_DtlsRepository;
	
	@Autowired
	Sales_Quotation_DocsRepository sales_Quotation_DocsRepository;
	
	@Transactional
	public Sales_Quotation update(Sales_Quotation iMaster,Long id)
	{
		Optional<Sales_Quotation> op=sales_QuotationRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(iMaster.getCustomer())) {
			iMaster.setCustomername(cust_bussiness_partnerRepository.getCustomer(iMaster.getCustomer()).getCp_name());
		}else {iMaster.setCustomername("None");}
		
		iMaster.setModified_type("INSERTED");
		iMaster.setInserted_by(op.get().getInserted_by());
		iMaster.setInserted_on(op.get().getInserted_on());
		iMaster.setUpdated_by(userRepository.getUserDetails(iMaster.getUsername()).getName());
		iMaster.setUpdated_on(ldt);
		iMaster.setDeleted_by("NA");
		iMaster.setDeleted_on(ldt);
		iMaster.setSale_orderused(op.get().isSale_orderused());
		if(op.isPresent())
		{
			iMaster.setId(id);
		}
		
		//Dynamic
		sales_Quotation_Item_DtlsRepository.sales_Quotation_Item_Dtlsupdate(iMaster.getQuotation_id());
				Set<Sales_Quotation_Item_Dtls> itemSet = new HashSet<Sales_Quotation_Item_Dtls>();
				itemSet.addAll(iMaster.getSales_Quotation_Item_Dtls());
				for(Sales_Quotation_Item_Dtls itemDts : itemSet)
				{
					itemDts.setQuotation_id(iMaster.getQuotation_id());
					itemDts.setQuotation_no(iMaster.getQuotation_no());
					itemDts.setItem_name(item_masterRepository.itemNameById(itemDts.getItem_code()).getItem_name());
					if(itemDts.getPacking().compareTo("")!=0) {
						itemDts.setPacking_name(item_masterRepository.itemNameById(itemDts.getPacking()).getItem_name());
					}
					itemDts.setCompany_id(iMaster.getCompany_id());
					itemDts.setFin_year(iMaster.getFin_year());
					itemDts.setModified_type("INSERTED");
					itemDts.setInserted_by(iMaster.getInserted_by());
					itemDts.setInserted_on(iMaster.getInserted_on());
					itemDts.setUpdated_by(iMaster.getUpdated_by());
					itemDts.setUpdated_on(iMaster.getUpdated_on());
					itemDts.setDeleted_by("NA");
					itemDts.setDeleted_on(ldt);
				
				}
				iMaster.setSales_Quotation_Item_Dtls(itemSet);
				
				//Dynamic
				sales_Quotation_Broker_DtlsRepository.sales_Quotation_Broker_Dtlsupdate(iMaster.getQuotation_id());
				Set<Sales_Quotation_Broker_Dtls> brokerSet = new HashSet<Sales_Quotation_Broker_Dtls>();
				brokerSet.addAll(iMaster.getSales_Quotation_Broker_Dtls());
				for(Sales_Quotation_Broker_Dtls brokerDts : brokerSet)
				{
					brokerDts.setQuotation_id(iMaster.getQuotation_id());
					brokerDts.setQuotation_no(iMaster.getQuotation_no());
					brokerDts.setCompany_id(iMaster.getCompany_id());
					brokerDts.setFin_year(iMaster.getFin_year());
					brokerDts.setModified_type("INSERTED");
					brokerDts.setInserted_by(iMaster.getInserted_by());
					brokerDts.setInserted_on(iMaster.getInserted_on());
					brokerDts.setUpdated_by(iMaster.getUpdated_by());
					brokerDts.setUpdated_on(iMaster.getUpdated_on());
					brokerDts.setDeleted_by("NA");
					brokerDts.setDeleted_on(ldt);
				
				}
				iMaster.setSales_Quotation_Broker_Dtls(brokerSet);
				
				//Static
				sales_Quotation_SummaryRepository.sales_Quotation_Summaryupdate(iMaster.getQuotation_id());
				Set<Sales_Quotation_Summary> summSet = new HashSet<Sales_Quotation_Summary>();
				summSet.add(iMaster.getSales_Quotation_Summary());
				for(Sales_Quotation_Summary summ : summSet)
				{
					summ.setQuotation_id(iMaster.getQuotation_id());
					summ.setQuotation_no(iMaster.getQuotation_no());
					summ.setCompany_id(iMaster.getCompany_id());
					summ.setFin_year(iMaster.getFin_year());
					summ.setModified_type("INSERTED");
					summ.setInserted_by(iMaster.getInserted_by());
					summ.setInserted_on(iMaster.getInserted_on());
					summ.setUpdated_by(iMaster.getUpdated_by());
					summ.setUpdated_on(iMaster.getUpdated_on());
					summ.setDeleted_by("NA");
					summ.setDeleted_on(ldt);
				}
				
				if(!summSet.isEmpty())
				{
					iMaster.setSales_Quotation_Summary(summSet.iterator().next());
				}
				
				//Dynamic
				sales_Quotation_Summary_dynRepository.sales_Quotation_Summary_dynupdate(iMaster.getQuotation_id());
				Set<Sales_Quotation_Summary_dyn> sumDtlsSet = new HashSet<Sales_Quotation_Summary_dyn>();
				sumDtlsSet.addAll(iMaster.getSales_Quotation_Summary_dyn());
				for(Sales_Quotation_Summary_dyn summDts : sumDtlsSet)
				{
					summDts.setQuotation_id(iMaster.getQuotation_id());
					summDts.setQuotation_no(iMaster.getQuotation_no());
					summDts.setCompany_id(iMaster.getCompany_id());
					summDts.setFin_year(iMaster.getFin_year());
					summDts.setModified_type("INSERTED");
					summDts.setInserted_by(iMaster.getInserted_by());
					summDts.setInserted_on(iMaster.getInserted_on());
					summDts.setUpdated_by(iMaster.getUpdated_by());
					summDts.setUpdated_on(iMaster.getUpdated_on());
					summDts.setDeleted_by("NA");
					summDts.setDeleted_on(ldt);
				
				}
				iMaster.setSales_Quotation_Summary_dyn(sumDtlsSet);
				
				//Static
				sales_Quotation_Trans_InfoRepository.sales_Quotation_Trans_Infoupdate(iMaster.getQuotation_id());
				Set<Sales_Quotation_Trans_Info> transSet = new HashSet<Sales_Quotation_Trans_Info>();
				transSet.add(iMaster.getSales_Quotation_Trans_Info());
				for(Sales_Quotation_Trans_Info trans : transSet)
				{
					trans.setQuotation_id(iMaster.getQuotation_id());
					trans.setQuotation_no(iMaster.getQuotation_no());
					trans.setTransporter_name(trans.getTrans_code());
					trans.setCompany_id(iMaster.getCompany_id());
					trans.setFin_year(iMaster.getFin_year());
					trans.setModified_type("INSERTED");
					trans.setInserted_by(iMaster.getInserted_by());
					trans.setInserted_on(iMaster.getInserted_on());
					trans.setUpdated_by(iMaster.getUpdated_by());
					trans.setUpdated_on(iMaster.getUpdated_on());
					trans.setDeleted_by("NA");
					trans.setDeleted_on(ldt);
				}
				
				if(!transSet.isEmpty())
				{
					iMaster.setSales_Quotation_Trans_Info(transSet.iterator().next());
				}
				
				//Static
				sales_Quotation_Terms_ConRepository.sales_Quotation_Terms_Conupdate(iMaster.getQuotation_id());
				Set<Sales_Quotation_Terms_Con> termSet = new HashSet<Sales_Quotation_Terms_Con>();
				termSet.add(iMaster.getSales_Quotation_Terms_Con());
				for(Sales_Quotation_Terms_Con termdtls : termSet)
				{
					termdtls.setQuotation_id(iMaster.getQuotation_id());
					termdtls.setQuotation_no(iMaster.getQuotation_no());
					termdtls.setCompany_id(iMaster.getCompany_id());
					termdtls.setFin_year(iMaster.getFin_year());
					termdtls.setModified_type("INSERTED");
					termdtls.setInserted_by(iMaster.getInserted_by());
					termdtls.setInserted_on(iMaster.getInserted_on());
					termdtls.setUpdated_by(iMaster.getUpdated_by());
					termdtls.setUpdated_on(iMaster.getUpdated_on());
					termdtls.setDeleted_by("NA");
					termdtls.setDeleted_on(ldt);
				}
				
				if(!termSet.isEmpty())
				{
					iMaster.setSales_Quotation_Terms_Con(termSet.iterator().next());
				}
				
				//Dynamic
				sales_Quotation_Party_DtlsRepository.sales_Quotation_Party_Dtlsupdate(iMaster.getQuotation_id());
				Set<Sales_Quotation_Party_Dtls> parSet = new HashSet<Sales_Quotation_Party_Dtls>();
				parSet.addAll(iMaster.getSales_Quotation_Party_Dtls());
				for(Sales_Quotation_Party_Dtls parDts : parSet)
				{
					parDts.setQuotation_id(iMaster.getQuotation_id());
					parDts.setQuotation_no(iMaster.getQuotation_no());
					if(Utility.isNullOrEmpty(parDts.getP_code()))
					{
						parDts.setP_name(cust_bussiness_partnerRepository.getCustomer(iMaster.getCustomer()).getCp_name());
					}
					else 
					{
						parDts.setP_name("0");
					}
					
					parDts.setCompany_id(iMaster.getCompany_id());
					parDts.setFin_year(iMaster.getFin_year());
					parDts.setModified_type("INSERTED");
					parDts.setInserted_by(iMaster.getInserted_by());
					parDts.setInserted_on(iMaster.getInserted_on());
					parDts.setUpdated_by(iMaster.getUpdated_by());
					parDts.setUpdated_on(iMaster.getUpdated_on());
					parDts.setDeleted_by("NA");
					parDts.setDeleted_on(ldt);
				
				}
				iMaster.setSales_Quotation_Party_Dtls(parSet);
				
				
				//Static
				sales_Quotation_Shipment_DtlsRepository.sales_Quotation_Shipment_Dtlsupdate(iMaster.getQuotation_id());
				Set<Sales_Quotation_Shipment_Dtls> shipSet = new HashSet<Sales_Quotation_Shipment_Dtls>();
				shipSet.add(iMaster.getSales_Quotation_Shipment_Dtls());
				for(Sales_Quotation_Shipment_Dtls shipdtls : shipSet)
				{
					shipdtls.setQuotation_id(iMaster.getQuotation_id());
					shipdtls.setQuotation_no(iMaster.getQuotation_no());
					shipdtls.setCompany_id(iMaster.getCompany_id());
					shipdtls.setFin_year(iMaster.getFin_year());
					shipdtls.setModified_type("INSERTED");
					shipdtls.setInserted_by(iMaster.getInserted_by());
					shipdtls.setInserted_on(iMaster.getInserted_on());
					shipdtls.setUpdated_by(iMaster.getUpdated_by());
					shipdtls.setUpdated_on(iMaster.getUpdated_on());
					shipdtls.setDeleted_by("NA");
					shipdtls.setDeleted_on(ldt);
				}
				
				if(!shipSet.isEmpty())
				{
					iMaster.setSales_Quotation_Shipment_Dtls(shipSet.iterator().next());
				}
				
				
				//Dynamic
				sales_Quotation_DocsRepository.sales_Quotation_Docsupdate(iMaster.getQuotation_id());
				Set<Sales_Quotation_Docs> docSet = new HashSet<Sales_Quotation_Docs>();
				docSet.addAll(iMaster.getSales_Quotation_Docs());
				for(Sales_Quotation_Docs docDts : docSet)
				{
					docDts.setQuotation_id(iMaster.getQuotation_id());
					docDts.setQuotation_no(iMaster.getQuotation_no());
					docDts.setCompany_id(iMaster.getCompany_id());
					docDts.setFin_year(iMaster.getFin_year());
					docDts.setModified_type("INSERTED");
					docDts.setInserted_by(iMaster.getInserted_by());
					docDts.setInserted_on(iMaster.getInserted_on());
					docDts.setUpdated_by(iMaster.getUpdated_by());
					docDts.setUpdated_on(iMaster.getUpdated_on());
					docDts.setDeleted_by("NA");
					docDts.setDeleted_on(ldt);
				
				}
				iMaster.setSales_Quotation_Docs(docSet);
		
		
		return sales_QuotationRepository.save(iMaster);
	}
	
	public List<Sales_Quotation> findAll()
	{
		List<Sales_Quotation> quotList=new ArrayList<Sales_Quotation>();
		quotList.addAll(sales_QuotationRepository.findSalesQuotations());
		
		return quotList;
	}
	
	public List<Map<String,Object>> getSalesQuotationsList(String cur_date)
	{
		return sales_QuotationRepository.getSalesQuotationsList(cur_date);
	}
	
	public List<Map<String,Object>> searchSaleQuotation(String fromdate,String todate)
	{
		return sales_QuotationRepository.searchSaleQuotation(fromdate,todate);
	}
	
	public List<Sales_QuotationDTO> salesQuotationList() {
		
		List<Sales_Quotation> modelList=sales_QuotationRepository.salesQuotationList();
		
		Type listType = new TypeToken<List<Sales_QuotationDTO>>() {}.getType();
		
		List<Sales_QuotationDTO> squotlist = new ModelMapper().map(modelList,listType);
		
		return squotlist;
	}
	
	public List<Sales_QuotationDTO> salesQuotationFinalise() {
		List<Sales_Quotation> modelList=sales_QuotationRepository.salesQuotationThruStatus("Finalise");
		Type listType = new TypeToken<List<Sales_QuotationDTO>>() {}.getType();
		List<Sales_QuotationDTO> squotlist = new ModelMapper().map(modelList,listType);
		for(int x=0;x<squotlist.size();x++) {
			if(squotlist.get(x).getCustomer()!=null && squotlist.get(x).getCustomer().compareTo("")!=0 && squotlist.get(x).getCustomer().compareTo("0")!=0) {
				squotlist.get(x).setCustomer_name(cust_bussiness_partnerRepository.getCustomer(squotlist.get(x).getCustomer()).getCp_name());
			}
			else {
				squotlist.get(x).setCustomer_name("No");
			}
		}
		return squotlist;
	}
	
	public List<Sales_QuotationDTO> salesQuotationPrevList() {
		
		List<Sales_Quotation> modelList=sales_QuotationRepository.salesQuotationThruStatus("Pending");
		
		Type listType = new TypeToken<List<Sales_QuotationDTO>>() {}.getType();
		
		List<Sales_QuotationDTO> squotlist = new ModelMapper().map(modelList,listType);
		
		return squotlist;
	}
	
	public Sales_QuotationDTO getSalesQuotDetails(String quot_id) {
		
		Sales_Quotation modelList=sales_QuotationRepository.getSalesQuotDetails(quot_id);
		
		Type listType = new TypeToken<Sales_QuotationDTO>() {}.getType();
		
		Sales_QuotationDTO squotdtls = new ModelMapper().map(modelList,listType);
		
		return squotdtls;
	}
	
	public List<Sales_Quotation_Item_DtlsDTO> getSalesQuotItemDtls(String quot_id)
	{
		List<Sales_Quotation_Item_Dtls> modelList=sales_Quotation_Item_DtlsRepository.getSalesQuotItemDtls(quot_id);
		
		Type listType=new TypeToken<List<Sales_Quotation_Item_DtlsDTO>>() {}.getType();
		
		List<Sales_Quotation_Item_DtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	
	public List<Sales_Quotation_Broker_DtlsDTO> getSalesQuotBrokerDtls(String quot_id)
	{
		List<Sales_Quotation_Broker_Dtls> modelList=sales_Quotation_Broker_DtlsRepository.getSalesQuotBrokerDtls(quot_id);
		
		Type listType=new TypeToken<List<Sales_Quotation_Broker_DtlsDTO>>() {}.getType();
		
		List<Sales_Quotation_Broker_DtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Sales_Quotation_Summary_dynDTO> getSalesQuotSummaryDtls(String quot_id)
	{
		List<Sales_Quotation_Summary_dyn> modelList=sales_Quotation_Summary_dynRepository.getSalesQuotSummaryDtls(quot_id);
		
		Type listType=new TypeToken<List<Sales_Quotation_Summary_dynDTO>>() {}.getType();
		
		List<Sales_Quotation_Summary_dynDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	public List<Sales_Quotation_Party_DtlsDTO> getSalesQuotPartyDtls(String quot_id)
	{
		List<Sales_Quotation_Party_Dtls> modelList=sales_Quotation_Party_DtlsRepository.getSalesQuotPartyDtls(quot_id);
		
		Type listType=new TypeToken<List<Sales_Quotation_Party_DtlsDTO>>() {}.getType();
		
		List<Sales_Quotation_Party_DtlsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}
	
	@Autowired
	Sales_Quotation_SummaryRepository sales_Quotation_SummaryRepository;
	
	public Sales_Quotation_SummaryDTO getSalesQuotSummary(String quot_id)
	{
		Sales_Quotation_Summary modelList=sales_Quotation_SummaryRepository.getSalesQuotSummary(quot_id);
		
		Type listType=new TypeToken<Sales_Quotation_SummaryDTO>() {}.getType();
		
		Sales_Quotation_SummaryDTO itemNameList=new ModelMapper().map(modelList, listType);
		 return itemNameList;
		
	}
	
	
	@Autowired
	Sales_Quotation_Terms_ConRepository sales_Quotation_Terms_ConRepository;
	
	public Sales_Quotation_Terms_ConDTO getSalesQuotTermsCon(String quot_id)
	{
		Sales_Quotation_Terms_Con modelList=sales_Quotation_Terms_ConRepository.getSalesQuotTransCon(quot_id);
		
		Type listType=new TypeToken<Sales_Quotation_Terms_ConDTO>() {}.getType();
		
		Sales_Quotation_Terms_ConDTO itemNameList=new ModelMapper().map(modelList, listType);
		
		 return itemNameList;
		
	}
	
	
	@Autowired
	Sales_Quotation_Trans_InfoRepository sales_Quotation_Trans_InfoRepository;
	
	public Sales_Quotation_Trans_InfoDTO getSalesQuotTransInfo(String quot_id)
	{
		Sales_Quotation_Trans_Info modelList=sales_Quotation_Trans_InfoRepository.getSalesQuotTransInfo(quot_id);
		
		Type listType=new TypeToken<Sales_Quotation_Trans_InfoDTO>() {}.getType();
		
		Sales_Quotation_Trans_InfoDTO itemNameList=new ModelMapper().map(modelList, listType);
		 return itemNameList;
		
	}
	
	@Autowired
	Sales_Quotation_Shipment_DtlsRepository sales_Quotation_Shipment_DtlsRepository;
	
	public Sales_Quotation_Shipment_DtlsDTO getSalesQuotShipDtls(String quot_id)
	{
		Sales_Quotation_Shipment_Dtls modelList=sales_Quotation_Shipment_DtlsRepository.getSalesQuotShipDtls(quot_id);
		
		Type listType=new TypeToken<Sales_Quotation_Shipment_DtlsDTO>() {}.getType();
		
		Sales_Quotation_Shipment_DtlsDTO itemNameList=new ModelMapper().map(modelList, listType);
		
		return itemNameList;
		
	}
	
		
	
	public List<Sales_Quotation_DocsDTO> getSalesQuotDoc(String quot_id)
	{
		List<Sales_Quotation_Docs> modelList=sales_Quotation_DocsRepository.getSales_Quotation_Docs(quot_id);
		
		Type listType=new TypeToken<List<Sales_Quotation_DocsDTO>>() {}.getType();
		
		List<Sales_Quotation_DocsDTO> salesItemList=new ModelMapper().map(modelList,listType);
		
		return salesItemList;
	}

	public Sales_Quotation findOne(long id) {
		Optional<Sales_Quotation> op=this.sales_QuotationRepository.findById(id);
		return op.get();
	}
	
	public StatusDTO checkSalesQuotationUsage(String quot_id)
	 {
		String result = sales_QuotationRepository.checkSalesQuotationUsage(quot_id);
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO statusDTO= new ModelMapper().map(result,listType);
		statusDTO.setStatus(result);
		return statusDTO;
	 }
	
	@Transactional
	public StatusDTO SalesQuotationTerminate(long id,String username)
	{
		StatusDTO res =new StatusDTO();
		sales_QuotationRepository.SalesQuotationTerminate(id,username);
		Optional<Sales_Quotation> op=this.sales_QuotationRepository.findById(id);
		sales_OrderRepository.salesOrderTerminationviaQuotation(op.get().getQuotation_id(),username);		
		
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
	 public Sales_Quotation deleteSalesQuotation(Sales_Quotation sales_quot,Long id) 
	 	{
		 
		 Optional<Sales_Quotation> op = sales_QuotationRepository.findById(id);
		 Sales_Quotation salesquot=sales_QuotationRepository.getQuotationDetails(id);
			
			LocalDateTime ldt = LocalDateTime.now();
			Sales_Quotation sQtStatus=new Sales_Quotation();
			
			if(op.get().isSale_orderused() == true) {
				System.err.println("Delete is not Possible !!!");
				return sQtStatus;
			}else {
				System.err.println("Delete Successfully !!!");
				
				if(op.isPresent())	{
					salesquot.setId(id);
				}
			System.out.println("local date :"+ldt);
			salesquot.setModified_type("DELETED");
			salesquot.setInserted_by(op.get().getInserted_by());
			salesquot.setInserted_on(op.get().getInserted_on());
			salesquot.setUpdated_by(op.get().getUpdated_by());
			salesquot.setUpdated_on(op.get().getUpdated_on());
			salesquot.setDeleted_by(userRepository.getUserDetails(sales_quot.getUsername()).getName());
			salesquot.setUsername(sales_quot.getUsername());
			salesquot.setDeleted_on(ldt);
				
			sales_Quotation_Item_DtlsRepository.sales_Quotation_Item_DtlsDelete(op.get().getQuotation_id());
			
			sales_Quotation_Broker_DtlsRepository.sales_Quotation_Broker_DtlsDelete(op.get().getQuotation_id());
			
			sales_Quotation_SummaryRepository.sales_Quotation_SummaryDelete(op.get().getQuotation_id());
			
			sales_Quotation_Summary_dynRepository.sales_Quotation_Summary_dynDelete(op.get().getQuotation_id());
			
			sales_Quotation_Trans_InfoRepository.sales_Quotation_Trans_InfoDelete(op.get().getQuotation_id());
			
			sales_Quotation_Terms_ConRepository.sales_Quotation_Terms_ConDelete(op.get().getQuotation_id());
			
			sales_Quotation_Party_DtlsRepository.sales_Quotation_Party_DtlsDelete(op.get().getQuotation_id());
			
			sales_Quotation_Shipment_DtlsRepository.sales_Quotation_Shipment_DtlsDelete(op.get().getQuotation_id());
			
			sales_Quotation_DocsRepository.sales_Quotation_DocsDelete(op.get().getQuotation_id());
			
			return sales_QuotationRepository.save(salesquot);	
			}
	 	}
	 
}
