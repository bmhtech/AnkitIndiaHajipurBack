/*package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Invoice;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Invoice_Bu_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Invoice_Docs;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Invoice_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Invoice_Tax_Info;
import com.AnkitIndia.jwtauthentication.repository.CompanyBusinessUnitMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Stk_Transfer_InvoiceRepository;

@Service
public class Stk_Transfer_InvoiceService_Imp implements Stk_Transfer_InvoiceService {
	
	@Autowired
	Stk_Transfer_InvoiceRepository stk_Transfer_InvoiceRepository;
	
	@Autowired
	CompanyBusinessUnitMasterRepository companyBusinessUnitMasterRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Transactional
	public Stk_Transfer_Invoice save(Stk_Transfer_Invoice stockTransferInvoice) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(stk_Transfer_InvoiceRepository.countId() != null ) {
			slno=Long.parseLong(stk_Transfer_InvoiceRepository.countId());
		}
		String prefix="STI";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		stockTransferInvoice.setStk_invoice_id(gen_sno);	
		stockTransferInvoice.setModified_type("INSERTED");
		stockTransferInvoice.setInserted_by("xxxx");
		stockTransferInvoice.setInserted_on(ldt);
		stockTransferInvoice.setUpdated_by("NA");
		stockTransferInvoice.setUpdated_on(ldt);
		stockTransferInvoice.setDeleted_by("NA");
		stockTransferInvoice.setDeleted_on(ldt);
		
		Set<Stk_Transfer_Invoice_Item_Dtls> stkTraInItDtls=new HashSet<Stk_Transfer_Invoice_Item_Dtls>();
		stkTraInItDtls.addAll(stockTransferInvoice.getStk_Transfer_Invoice_Item_Dtls());
		for(Stk_Transfer_Invoice_Item_Dtls stTrInItDt:stkTraInItDtls) 
		{
			stTrInItDt.setStk_invoice_id(gen_sno);
			stTrInItDt.setStk_invoice_no(stockTransferInvoice.getStk_invoice_no());
			stTrInItDt.setItem_name(item_masterRepository.itemNameById(stTrInItDt.getItem_code()).getItem_name());
			if(stTrInItDt.getPacking().compareTo("")!=0) {
				stTrInItDt.setPacking_name(item_masterRepository.itemNameById(stTrInItDt.getPacking()).getItem_name());
			}
			stTrInItDt.setCompany_id(stockTransferInvoice.getCompany_id());
			stTrInItDt.setFin_year(stockTransferInvoice.getFin_year());
			stTrInItDt.setModified_type("INSERTED");
			stTrInItDt.setInserted_by("xxxx");
			stTrInItDt.setInserted_on(ldt);
			stTrInItDt.setUpdated_by("NA");
			stTrInItDt.setUpdated_on(ldt);
			stTrInItDt.setDeleted_by("NA");
			stTrInItDt.setDeleted_on(ldt);
		}
		stockTransferInvoice.setStk_Transfer_Invoice_Item_Dtls(stkTraInItDtls);
		
		Set<Stk_Transfer_Invoice_Docs> stkTraInDcs=new HashSet<Stk_Transfer_Invoice_Docs>();
		stkTraInDcs.addAll(stockTransferInvoice.getStk_Transfer_Invoice_Docs());
		for(Stk_Transfer_Invoice_Docs stTrInDc:stkTraInDcs) 
		{
			stTrInDc.setStk_invoice_id(gen_sno);
			stTrInDc.setStk_invoice_no(stockTransferInvoice.getStk_invoice_no());
			stTrInDc.setCompany_id(stockTransferInvoice.getCompany_id());
			stTrInDc.setFin_year(stockTransferInvoice.getFin_year());
			stTrInDc.setModified_type("INSERTED");
			stTrInDc.setInserted_by("xxxx");
			stTrInDc.setInserted_on(ldt);
			stTrInDc.setUpdated_by("NA");
			stTrInDc.setUpdated_on(ldt);
			stTrInDc.setDeleted_by("NA");
			stTrInDc.setDeleted_on(ldt);
		}
		stockTransferInvoice.setStk_Transfer_Invoice_Docs(stkTraInDcs);
		
		Set<Stk_Transfer_Invoice_Bu_Dtls> stkTrInBuDtls=new HashSet<Stk_Transfer_Invoice_Bu_Dtls>();
		stkTrInBuDtls.add(stockTransferInvoice.getStk_Transfer_Invoice_Bu_Dtls());
		for(Stk_Transfer_Invoice_Bu_Dtls stTrInBDt:stkTrInBuDtls) 
		{
			stTrInBDt.setStk_invoice_id(gen_sno);
			stTrInBDt.setStk_invoice_no(stockTransferInvoice.getStk_invoice_no());
			stTrInBDt.setCompany_id(stockTransferInvoice.getCompany_id());
			stTrInBDt.setFin_year(stockTransferInvoice.getFin_year());
			stTrInBDt.setModified_type("INSERTED");
			stTrInBDt.setInserted_by("xxxx");
			stTrInBDt.setInserted_on(ldt);
			stTrInBDt.setUpdated_by("NA");
			stTrInBDt.setUpdated_on(ldt);
			stTrInBDt.setDeleted_by("NA");
			stTrInBDt.setDeleted_on(ldt);
		}
		if(!stkTrInBuDtls.isEmpty())
		{
			stockTransferInvoice.setStk_Transfer_Invoice_Bu_Dtls(stkTrInBuDtls.iterator().next());
		}
		
		Set<Stk_Transfer_Invoice_Tax_Info> stkTrInTxInf=new HashSet<Stk_Transfer_Invoice_Tax_Info>();
		stkTrInTxInf.add(stockTransferInvoice.getStk_Transfer_Invoice_Tax_Info());
		for(Stk_Transfer_Invoice_Tax_Info stTrInTI:stkTrInTxInf) 
		{
			stTrInTI.setStk_invoice_id(gen_sno);
			stTrInTI.setStk_invoice_no(stockTransferInvoice.getStk_invoice_no());
			stTrInTI.setCompany_id(stockTransferInvoice.getCompany_id());
			stTrInTI.setFin_year(stockTransferInvoice.getFin_year());
			stTrInTI.setModified_type("INSERTED");
			stTrInTI.setInserted_by("xxxx");
			stTrInTI.setInserted_on(ldt);
			stTrInTI.setUpdated_by("NA");
			stTrInTI.setUpdated_on(ldt);
			stTrInTI.setDeleted_by("NA");
			stTrInTI.setDeleted_on(ldt);
		}
		if(!stkTrInTxInf.isEmpty())
		{
			stockTransferInvoice.setStk_Transfer_Invoice_Tax_Info(stkTrInTxInf.iterator().next());
		}
		
		return stk_Transfer_InvoiceRepository.save(stockTransferInvoice);
		
	}
	
	public List<Stk_Transfer_Invoice> findAll()
	{
		for(int i=0;i<stk_Transfer_InvoiceRepository.findAll().size();i++) {
			stk_Transfer_InvoiceRepository.findAll().get(i).setBusiness_unit(companyBusinessUnitMasterRepository.CompanyBUAddress(stk_Transfer_InvoiceRepository.findAll().get(i).getBusiness_unit()).getBusinessunit_name());
		}
		return stk_Transfer_InvoiceRepository.findAll();
	}

}
*/