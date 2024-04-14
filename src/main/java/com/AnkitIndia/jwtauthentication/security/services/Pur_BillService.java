package com.AnkitIndia.jwtauthentication.security.services;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.data.domain.Page;

import com.AnkitIndia.jwtauthentication.model.Charge_Master;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_app_chgs;
import com.AnkitIndia.jwtauthentication.model.Pur_Quotation;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Purchase_item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_BillDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Account_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Bp_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Broker_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Item_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Musterroll_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Tax_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receiptDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

 public interface Pur_BillService {

	public SequenceIdDTO getPBSequenceId(String orderdate,boolean itype,String ptype,String psubtype);
	
	public Pur_Bill save(Pur_Bill pur_Bill);
	
	public Pur_Bill update(Pur_Bill pur_Bill,long id);
	
	public List<Pur_BillDTO> findAllBill();
	
	public Page<Pur_Bill_Pagination_DTO> getPurBillPagination(int page,int size);
	
	public List<Pur_Bill_Pagination_DTO> searchPurBill(String orderno,String fromdate, String todate,String supplier_name1,String pur_type1,String finyear);
	
	public List<Map<String,Object>> searchPurBillFast(String orderno,String fromdate, String todate,String supplier_name1,String pur_type1,String finyear);
	
	//public List<Pur_BillDTO> searchPurBill(String orderno,String fromdate, String todate,String supplier_name1,String finyear);
	
	public Pur_BillDTO getPurBillDetails(String pbillid,String company,String finyear);
	
	public List<Pur_BillDTO> getPaymentStatus(String fromdate,String todate);
	
	public List<Pur_BillDTO> getPurBillRtnApp(String supplier,String tdate,String company,String finyear);
	
	public Pur_Bill findOne(long id);
	 
	public List<Pur_Bill_Item_DetailsDTO> purBillItemRetriveList(String pbid);
	
	public List<Pur_Bill_Item_DetailsDTO> purBillItemRetriveListPrint(String pbid);
	
	public List<Map<String,Object>> getpurbillprintupperdata(String pbid);
	 
	public List<Pur_Bill_Musterroll_DetailsDTO> purBillMusterRetriveList(String pbid);
	 
	public Pur_Bill_Tax_InfoDTO gePurBillTaxInfo(String pbid);
	 
	public List<Pur_Bill_Broker_DetailsDTO> purBillBrokerRetriveList(String pbid);
	 
	public Pur_Bill_Bp_DetailsDTO gePurBillBPRetrive(String pbid);
	 
	public List<Pur_Bill_DocsDTO> purBillDocRetriveList(String pbid);
	 
	public Pur_Bill_Account_InfoDTO gePurBillAccRetrive(String pbid);
	
	public List<Pur_Order_app_chgs> getChargesapplication(String grnid);
	
	public List<Pur_Order_app_chgs> getChargesMatrixdetails(String unloadid);
	
	public List<Pur_Bill_app_chgs> purBillAppChargesRetriveList(String pbid);
	
	public List<Map<String,Object>> purBillStoreChargesRetriveList(String pbid);
	
	public List<Pur_Bill_app_chgs> purBillCharMatrixposting(String pbid);
	
	public Pur_Bill accountpostingPurchaseBill(long id);
	
	public Pur_Bill accountpostingPurchaseBillundo(long id);
	
	public Pur_Bill deletePurchaseBill(Pur_Bill purbillid,Long id);
	
	public List<Pur_BillDTO> getpurBillDataList(String currDate,String finyear);
	
	public List<Purchase_item_groupwise_taxsumm> getpurBillInvTaxSum(String purbillid);
	
	public List<Map<String, Object>> getPurBillNewReport(String fromdate,String todate,String finyear,String po_type,String supplier_name);
	
	public List<Map<String, Object>> getPurBillbalanceNewReport(String fromdate,String todate,String supplier_name,String finyear,String po_type);
	
	public List<Map<String, Object>> getPurchaseBillmiscreport(String business_unit,String catagory,String fromdate,String todate,String supplier_name,String ven_code_name);
	
	public List<Map<String, Object>> purchaseBillSupplierNamesList(String company,String fromdate,String todate,String business_unit);
	
	public List<Map<String, Object>> purchaseBillBrokerNamesList(String company,String fromdate,String todate,String business_unit);

	public List<Map<String, Object>> getPurBillReport(String fromdate,String todate);
	
	public List<LinkedHashMap<String, Object>> gettransactionalReport(String fromdate,String todate,String catagory);
	
	public List<LinkedHashMap<String, Object>> getstocktrackingReport(String fromdate,String todate,String catagory,String startdate);
	
	public List<Map<String, Object>> getstocktrackingReport2(String fromdate,String todate,String catagory,String startdate);
	
	
	public List<Map<String, Object>> getalltransactionfromitem(String itemcode,String packingcode,String fromdate,String todate,String catagory);
	//public List<LinkedHashMap<String, Object>> gettransactionalReport(String fromdate,String todate,String catagory);
	
	public List<Map<String, Object>> getBillThroughGRNId(String grnid);
	
	public Map<String, Object> getSuppliertdsStatDtls(String suppid,String financial_year);
	
}
