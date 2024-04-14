package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Item_groupwise_hsnsumm;
import com.AnkitIndia.jwtauthentication.model.Item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Process_master_doc;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Docs;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Summary_dyn;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.responseDTO.Control_account_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;

import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_InvoiceDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_payment_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_BP_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Broker_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Payment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Tax_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Trans_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_Pagination_DTO;

public interface Sales_InvoiceService {
	
	public SalesSequenceIdDTO getSISequenceId(String fin_year,String inv_type);
	
	//public Sales_Invoice save(Sales_Invoice sales_Invoice);
	public Sales_Invoice save(Sales_Invoice sales_Invoice,MultipartFile files[]) throws IOException;
	
	public List<Sales_InvoiceDTO> getSalesInvoice(String company);
	
	public List<Sales_InvoiceDTO> getSalesInvoices(String party,String invdate,String comp);
	
	public List<Map<String,Object>> getSalesInvoiceReturn(String party,String invdate,String comp);
	
	public Sales_InvoiceDTO getSalesInvDetails(String invoice_id);
	
	public Sales_InvoiceDTO findOne(long id);
	
	public List<Sales_Invoice_Item_DtlsDTO> getSalesInvItmDtls(String invoice_id);
	
	public List<Map<String,Object>> getSalesInvItmDtlswtAltName(String invoice_id);
	
	public List<Map<String,Object>> retriveinvoicejobworkprice(String invoice_id);
	
	public Sales_Invoice_Tax_InfoDTO getSalesInvTaxInfo(String invoice_id);
	
	public List<Sales_Invoice_Broker_DtlsDTO> getSalesInvBrkDtls(String invoice_id);
	
	public Sales_Invoice_BP_DtlsDTO getSalesInvBpDtls(String invoice_id);
	
	public List<Sales_Invoice_DocsDTO> getSalesInvDocs(String invoice_id);
	
	//public Sales_Invoice update(Sales_Invoice iMaster,Long id);
	public Sales_Invoice update(Sales_Invoice salesinvoice,MultipartFile files[]);
	
	public Sales_Invoice deleteSalesInv(Sales_Invoice sInvoice,Long id);
	
	public List<Sales_Invoice_Trans_DtlsDTO> getSalesTransDtls(String invoice_id);
	
	public Sales_Invoice_Shipment_DtlsDTO getSalesShipDtls(String invoice_id);
	
	public Sales_Invoice_Payment_DtlsDTO getSalesPayDtls(String invoice_id);
	
	public List<Map<String,Object>> getInvTaxSum(String invid);
	
	public List<Item_groupwise_hsnsumm> getInvHsnSum(String invid);
	
	public List<Sales_payment_detailsDTO> getSalesPaymentDetails(String bunit,String party,String fdate,String tdate);
	
	public List<Control_account_detailsDTO> getControlAccPayDetails(String bunit,String ledger,String fdate,String tdate);
	
	public List<Sales_Order_Summary_dyn> getChargesMatrixSalesdetails(String delivery_cid);
	
	public Sales_Order getAppChargesSalesdetails(String delivery_cid);
	
	public List<Sales_Invoice_Item_DtlsDTO> getSalesInvItmDtls1(String invoice_id);
	
	//public Sales_Invoice accountpostingSalesInvoice(long id);
	
	public List<Sales_Invoice> getreturnapprovalsalesInvoice(Long id);
	
	public Sales_Invoice accountpostingSalesInvoice(long id);
	
	public Page<Sales_Invoice_Pagination_DTO> getSales_Invoice_pagination(int page,int size);
	
	public List<Sales_Invoice_Pagination_DTO> searchSalesInvoice(String orderno,String fromdate, String todate,String party1,String finyear);
	
	public List<Map<String,Object>> searchSalesInvoiceFast(String orderno,String fromdate, String todate,String party1,String finyear);
	
	public List<Sales_InvoiceDTO> getSalesInvoiceDataList(String currDate,String finyear);

	public List<Map<String, Object>> getSalesInvoiceSummaryCatagorywiseList(String catagory,String catagoryname,String fromdate,String todate,String bunit);

	public List<Map<String, Object>> getSalesInvoiceMiscList(String catagory,String fromdate,String todate,String bunit,String broker,String customer);
	
	public List<Map<String, Object>> salesInvoiceFinishedItemlist(String company,String fromdate,String todate,String business_unit);
	
	public List<Map<String, Object>> salesInvoiceBrokerlist(String company,String fromdate,String todate,String business_unit);
		
	public List<Map<String, Object>> salesInvoicePartylist(String company,String fromdate,String todate,String business_unit);
	
	 public List<Map<String, Object>> geteinvoicestatus_saleinv(long id,String invoiceno);
	 
	 public List<Map<String,Object>> getInvoiceReportThroughChallan(String challanid);
	 
	 public List<Map<String,Object>> getdocumentListwithfileSalesInvoice(String d);
	 
	 public Sales_Invoice_Docs findOneInvDoc(long id);
		
	public void deleteSIDocument(Sales_Invoice_Docs sales_Invoice_Docs);
	
	 public Map<String,Object> getTransportimage1(String refno);
	 

	 public Map<String,Object> getJobWorkInvPrint(long mainid);
	 
	 public List<Map<String,Object>> getInvoiceJobItemDtls(String invoice_id);
	 
	 public List<Map<String,Object>> getInvoiceTServiceItem(String invoice_id);
	 
	 public Map<String,Object> geteinvoicedetails(String invoice_id);
	 
	 
	 
	 public StatusDTO getnumtowordsaleinvoice(String invoice_id);
	 
	 public StatusDTO  getnumtoword(String taxamt);
	

	 public List<Map<String,Object>> getDelvChallanJobworkPrice(String delv_id);
	 
	 public List<Map<String,Object>> getDelvChallanMultiJobworkPrice(String delv_id);
	 
	 public List<Map<String, Object>> getJobWorkSalesReport(String fromdate,String todate);
	 
	// public StatusDTO createEinvoiceGeneration(long id,Object  einvjson);
	 
	 public StatusDTO createEinvoiceGeneration2(Long id,StatusDTO statusDTO);
	 
	 public Map<String,Object> getSalesInvPayDtls(String invoice_id);
	 
	 public List<Map<String, Object>> getSalesInvoicetransitReport(String fromdate,String todate);
	 
	 public StatusDTO updateArnNo(Long id,String invoiceno,String asnno);
	 
	}
