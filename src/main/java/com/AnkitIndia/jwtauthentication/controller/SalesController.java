package com.AnkitIndia.jwtauthentication.controller;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Sales_return_note_item_dtls;
import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.jwtauthentication.model.Credit_item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Chgs_dyn;
import com.AnkitIndia.jwtauthentication.model.Gate_Pass;
import com.AnkitIndia.jwtauthentication.model.Item_groupwise_hsnsumm;
import com.AnkitIndia.jwtauthentication.model.Item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Item_rate_dtls;
import com.AnkitIndia.jwtauthentication.model.Party_bill_payment;
import com.AnkitIndia.jwtauthentication.model.Partyoutstanding_invoice;
import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Ratechart;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Sale_credit_note_einvoice_gen;
import com.AnkitIndia.jwtauthentication.model.Sale_invoice_einvoice_gen;
import com.AnkitIndia.jwtauthentication.model.Sales_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Sales_Order;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Summary_dyn;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Trans_Chgs_dyn;
import com.AnkitIndia.jwtauthentication.model.Sales_Quotation;
import com.AnkitIndia.jwtauthentication.model.Sales_credit_note;
import com.AnkitIndia.jwtauthentication.model.Sales_return_note;
import com.AnkitIndia.jwtauthentication.model.Sales_transaction;
import com.AnkitIndia.jwtauthentication.model.Servicemaster;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.Invoice_typeRepository;
import com.AnkitIndia.jwtauthentication.repository.Sale_credit_note_einvoice_genRepository;
import com.AnkitIndia.jwtauthentication.repository.Sale_invoice_einvoice_genRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_InvoiceRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_OrderRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_Order_SummaryRepository;
import com.AnkitIndia.jwtauthentication.repository.Sales_credit_noteRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Control_account_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Delivery_challan_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.ResponseDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_EnquiryDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Enquiry_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Enquiry_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Enquiry_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_InvoiceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_BP_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Broker_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Payment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Tax_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Invoice_Trans_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_OrderDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_QuotationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_Quotation_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_bp_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_payment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_tax_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_credit_note_trans_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_party_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_return_note_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.security.services.Delivery_challanService;
import com.AnkitIndia.jwtauthentication.security.services.Gate_PassService;
import com.AnkitIndia.jwtauthentication.security.services.Party_bill_paymentService;
import com.AnkitIndia.jwtauthentication.security.services.RatechartService;
import com.AnkitIndia.jwtauthentication.security.services.Return_approval_noteService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_EnquiryService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_InvoiceService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_OrderService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_QuotationService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_credit_noteService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_return_noteService;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challanDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Broker_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_DocsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Delivery_challan_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Delivery_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Sales_return_note_trans_infoDTO;
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
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Broker_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Party_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_SummaryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Summary_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Terms_ConDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_Quotation_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_payment_detailsDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.time.LocalDateTime;  

import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;  
import java.io.File;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import  com.AnkitIndia.qrcode.createQRImage;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
//@RequestMapping("/MeghnaBidi")
//@RequestMapping("/MeghanaPanMasala")
//@RequestMapping("/IndianSpices")
public class SalesController {
	
	/************** Sales Enquiry **************/
	
	@Autowired
	Sales_EnquiryService sales_EnquiryService;
	
	@GetMapping("/getSequenceId/{prefix}")
	public SequenceIdDTO getSequenceId(@PathVariable(value = "prefix") String prefix)
	{
		return sales_EnquiryService.getSequenceId(prefix);
	}
	
	@PostMapping("/createSalesEnquiry")
	public Sales_Enquiry save(@Valid @RequestBody Sales_Enquiry sales_Enquiry) 
	{
		return sales_EnquiryService.save(sales_Enquiry);
	}
	
	@GetMapping("/getSalesEnquiries")
	public List<Sales_Enquiry> findAll()
	{
		return sales_EnquiryService.findAll();
	}
	
	@GetMapping(value = "/salesEnquiryList")
	public List<Sales_EnquiryDTO> salesEnquiryList() {
		return sales_EnquiryService.salesEnquiryList();
	}
	
	@GetMapping(value = "/getSalesEnquiriesOpen/{qdate}")
	public List<Sales_EnquiryDTO> getSalesEnquiriesOpen(@PathVariable(value = "qdate") String qdate) {
		return sales_EnquiryService.getSalesEnquiriesOpen(qdate);
	}
	
	@PutMapping("/updateSalesEnquiries/{id}")
	public ResponseEntity<Sales_Enquiry> updateSalesEnquiries(@PathVariable(value="id") long id,@Valid @RequestBody Sales_Enquiry iMaster)
	{
		Sales_Enquiry op=sales_EnquiryService.update(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/SalesEnquiriesRetrive/{id}")
	public ResponseEntity<Sales_Enquiry> SalesEnquiriesRetrive(@PathVariable(value="id") Long id)
	{
		Sales_Enquiry tgs=sales_EnquiryService.findOne(id);
		if(tgs==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(tgs);
		}
	}
	
	@GetMapping(value = "/salesEnquiryByEnqId/{enquiry_id}")
	public Sales_EnquiryDTO salesEnquiryByEnqId(@PathVariable(value = "enquiry_id") String enquiry_id) {
		return sales_EnquiryService.salesEnquiryByEnqId(enquiry_id);
	}
	
	@GetMapping("/getSalesEnqItemDtls/{enquiry_id}")
	public List<Sales_Enquiry_Item_DtlsDTO> getSalesEnqItemDtls(@PathVariable(value = "enquiry_id") String enquiry_id)
	{
		return sales_EnquiryService.getSalesEnqItemDtls(enquiry_id);
	}
	
	@GetMapping("/salesEnqPersonList/{enquiry_id}")
	public Sales_EnquiryDTO salesEnqPersonList(@PathVariable(value = "enquiry_id") String enquiry_id)
	{
		return sales_EnquiryService.salesEnqPersonList(enquiry_id);
	}
	
	@GetMapping("/getSalesEnqPartyDtls/{enquiry_id}")
	public List<Sales_Enquiry_Party_DtlsDTO> getSalesEnqPartyDtls(@PathVariable(value = "enquiry_id") String enquiry_id)
	{
		return sales_EnquiryService.getSalesEnqPartyDtls(enquiry_id);
	}

	@GetMapping("/getSalesEnqDoc/{enquiry_id}")
	public List<Sales_Enquiry_DocsDTO> getSalesEnqDoc(@PathVariable(value = "enquiry_id") String enquiry_id)
	{
		return sales_EnquiryService.getSalesEnqDoc(enquiry_id);
	}
	
	@GetMapping("/getSalesEnqSequenceId/{prefix}/{orderdate}/{type}")
	public SalesSequenceIdDTO getSalesEnqSequenceId(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate,@PathVariable(value = "type") String type)
	{
		return sales_EnquiryService.getSalesEnqSequenceId(prefix,orderdate,type);
	}
	
	/************** End Sales Enquiry **************/
	
	/************** Sales Order **************/
	
	@Autowired
	Sales_OrderService sales_OrderService;
	
	@GetMapping("/getSalesOrdSequenceId/{prefix}/{orderdate}/{type}")
	public SalesSequenceIdDTO getSalesOrdSequenceId(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate,@PathVariable(value = "type") String type)
	{
		return sales_OrderService.getSalesOrdSequenceId(prefix,orderdate,type);
	}
	
	@GetMapping("/getSalesOrdSequenceIdWarehouse/{prefix}/{orderdate}")
	public SalesSequenceIdDTO getSalesOrdSequenceIdWarehouse(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate)
	{
		return sales_OrderService.getSalesOrdSequenceIdWarehouse(prefix,orderdate);
	}
	
	@PostMapping("/createEffectiveSalesOrder")
	public Sales_Order createEffectiveSalesOrder(@Valid @RequestBody Sales_Order sales_Order) 
	{
		return sales_OrderService.createEffectiveSalesOrder(sales_Order);
	}
	
	@PostMapping("/createSalesOrder")
	public Sales_Order save(@Valid @RequestBody Sales_Order sales_Order) 
	{
		return sales_OrderService.save(sales_Order);
	}
	
	@GetMapping("/getSalesOrdTransChgsDynList/{sales_order_no}")
	public List<Map<String,Object>> getSalesOrdTransChgsDynList(@PathVariable(value = "sales_order_no") String sales_order_no)
	{
		return sales_OrderService.getSalesOrdTransChgsDynList(sales_order_no);
	}
	
	@GetMapping("/getSalesOrders")
	public List<Sales_OrderDTO> findAllSalesOrders()
	{
		return sales_OrderService.findAll();
	}
	
	
	@GetMapping("/getliewterminationsalelist")
	public List<Sales_OrderDTO> getliewterminationsalelist()
	{
		return sales_OrderService.getliewterminationsalelist();
	}
	
	
	
	@GetMapping("/getDeliverySalesOrderUpdate")
	public List<Sales_OrderDTO> getDeliverySalesOrderUpdate(@RequestParam Long id)
	{
		return sales_OrderService.getDeliverySalesOrderUpdate(id);
	}
	
	
	@GetMapping("/getsaleordercharges/{transporter_Id}/{referance_id}")
	public Sales_Order_Trans_Chgs_dyn  getsaleordercharges(@PathVariable(value="transporter_Id") String transporter_Id,@PathVariable(value="referance_id") String referance_id)
	{
		return sales_OrderService.getsaleordercharges(transporter_Id,referance_id);
	}
	
	@GetMapping("/getSalesOrder")
	public List<Sales_OrderDTO> getSalesOrders(@RequestParam String party,@RequestParam String invdate,@RequestParam String comp) {
		return sales_OrderService.getSalesOrders(party,invdate,comp);
	}
	
	@GetMapping("/getSalesOrderReturn")
	public List<Map<String,Object>> getSalesOrderReturn(@RequestParam String party,@RequestParam String invdate,@RequestParam String comp) {
		return sales_OrderService.getSalesOrderReturn(party,invdate,comp);
	}
	
	@GetMapping("/findSalesOrders")
	public List<Sales_OrderDTO> findSalesOrders(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String party,@RequestParam(defaultValue = "") String invdate) {
		return sales_OrderService.findSalesOrders(bunit,party,invdate);
	}
	
	@GetMapping("/findSalesOrdersModified")
	public List<Map<String,Object>> findSalesOrdersModified(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String party,@RequestParam(defaultValue = "") String invdate) {
		return sales_OrderService.findSalesOrdersModified(bunit,party,invdate);
	}
	@GetMapping("/findSalesOrdersRefraction")
	public List<Map<String,Object>> findSalesOrdersRefraction(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String party,@RequestParam(defaultValue = "") String invdate) {
		return sales_OrderService.findSalesOrdersRefraction(bunit,party,invdate);
	}
	
	@GetMapping(value = "/salesOrderList")
	public List<Sales_OrderDTO> salesOrderList() {
		return sales_OrderService.salesOrderList();
	}
	
	@GetMapping("/salesOrderRetrive/{id}")
	public ResponseEntity<Sales_Order> salesOrderRetrive(@PathVariable(value="id") Long id)
	{
		Sales_Order tgs=sales_OrderService.findOne(id);
		if(tgs==null){
			return ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(tgs);
		}
	}
	
	@GetMapping("/getSalesOrdItemDtls/{order_id}")
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtls(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdItemDtls(order_id);
	}
	

	@GetMapping("/updatesalesorder/{id}")
	public Sales_Order updatesalesorder(@PathVariable(value = "id") Long id)
	{
		return sales_OrderService.updatesalesorder(id);
	}
	
	
	@GetMapping("/getSalesOrdItemDtlsNew/{order_id}")
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtlsNew(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdItemDtlsNew(order_id);
	}
	
	@GetMapping("/getSalesOrdItemDtlsRefraction/{order_id}")
	public List<Sales_Order_Item_Dtls> getSalesOrdItemDtlsRefraction(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdItemDtlsRefraction(order_id);
	}
	
	@GetMapping("/getSalesOrdItemDtlsJobwork/{order_id}")
	public  List<Map<String,Object>> getSalesOrdItemDtlsJobwork(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdItemDtlsJobwork(order_id);
	}
	
	@GetMapping("/getSOjwRestQty/{order_id}/{item_id}/{item_code}")
	public  List<Map<String,Object>> getSOjwRestQty(@PathVariable(value = "order_id") String order_id,
			@PathVariable(value = "item_id") String item_id, @PathVariable(value = "item_code") String item_code)
	{
		return sales_OrderService.getSOjwRestQty(order_id,item_id,item_code);
	}
	
	@GetMapping("/getSalesOrdItemDtlsUpdate/{order_id}")
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtlsUpdate(@RequestParam Long order_id)
	{
		return sales_OrderService.getSalesOrdItemDtlsUpdate(order_id);
	}
	
	@GetMapping("/getSalesOrdItemDtlsThruLoad/{order_id}")
	public List<Sales_Order_Item_DtlsDTO> getSalesOrdItemDtlsThruLoad(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdItemDtlsThruLoad(order_id);
	}
	
	@GetMapping("/getSalesOrderItemDtls/{order_id}")
	public List<Sales_Order_Item_DtlsDTO> getSalesOrderItemDtls(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrderItemDtls(order_id);
	}
	
	@GetMapping("/getSalesOrdBrokerDtls/{order_id}")
	public List<Sales_Order_Broker_DtlsDTO> getSalesOrdBrokerDtls(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdBrokerDtls(order_id);
	}	
	
	@GetMapping("/getSalesOrdSumm/{order_id}")
	public Sales_Order_SummaryDTO getSalesOrdSumm(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdSumm(order_id);
	}
	
	@GetMapping("/getSalesOrdSummDyna/{order_id}")
	public List<Sales_Order_Summary_dynDTO> getSalesOrdSummDyna(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdSummDyna(order_id);
	}
	
	
	@GetMapping("/getSalesOrdJobItemDtls/{order_id}")
	public List<Map<String,Object>> getSalesOrdJobItemDtls(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdJobItemDtls(order_id);
	}
	
	@GetMapping("/getSalesOrdTServiceItem/{order_id}")
	public List<Map<String,Object>> getSalesOrdTServiceItem(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdTServiceItem(order_id);
	}
	
	@GetMapping("/getSalesOrdTransInfo/{order_id}")
	public Sales_Order_Trans_InfoDTO getSalesOrdTransInfo(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdTransInfo(order_id);
	}

	@GetMapping(value = "/getSalesOrderDetails/{orderid}")
	public Sales_OrderDTO getSalesOrderDetails(@PathVariable(value = "orderid") String orderid) {
		return sales_OrderService.getSalesOrderDetails(orderid);
	}
	
	@GetMapping(value = "/getSalesOrderDetailsthdeliverchallan/{delveryid}")
	public Sales_Order getSalesOrderDetailsthdeliverchallan(@PathVariable(value = "delveryid") String delveryid) {
		return sales_OrderService.getSalesOrderDetailsthdeliverchallan(delveryid);
	}
	
	@GetMapping(value = "/getsaleorderjobworkprice/{delveryid}")
	public List<Map<String, Object>> getsaleorderjobworkprice(@PathVariable(value = "delveryid") String delveryid) {
		return sales_OrderService.getsaleorderjobworkprice(delveryid);
	}
	
	@GetMapping("/getSalesOrdPartyDtls/{order_id}")
	public List<Sales_Order_Party_DtlsDTO> getSalesOrdPartyDtls(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdPartyDtls(order_id);
	}
	
	@GetMapping("/getSalesOrdTermsCon/{order_id}")
	public Sales_Order_Terms_ConDTO getSalesOrdTermsCon(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdTermsCon(order_id);
	}
	
	@GetMapping("/getSalesOrdShipDtls/{order_id}")
	public Sales_Order_Shipment_DtlsDTO getSalesOrdShipDtls(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdShipDtls(order_id);
	}

	@GetMapping("/getSalesOrdDocs/{order_id}")
	public List<Sales_Order_DocsDTO> getSalesOrdDocs(@PathVariable(value = "order_id") String order_id)
	{
		return sales_OrderService.getSalesOrdDocs(order_id);
	}
	
	
	@PutMapping("/updateEffectiveSalesOrder/{id}")
	public ResponseEntity<Sales_Order> updateEffectiveSalesOrder(@PathVariable(value="id") long id,@Valid @RequestBody Sales_Order iMaster)
	{
		Sales_Order op=sales_OrderService.updateEffectiveSalesOrder(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/updateSalesOrder/{id}")
	public ResponseEntity<Sales_Order> updateSalesOrder(@PathVariable(value="id") long id,@Valid @RequestBody Sales_Order iMaster)
	{
		Sales_Order op=sales_OrderService.update(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	
	@PutMapping("/updateEffectiveSalesOrderWithLoading/{id}")
	public ResponseEntity<Sales_Order> updateEffectiveSalesOrderWithLoading(@PathVariable(value="id") long id,@Valid @RequestBody Sales_Order iMaster)
	{
		Sales_Order op=sales_OrderService.updateEffectiveSalesOrderWithLoading(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/updateSalesOrderWithLoading/{id}")
	public ResponseEntity<Sales_Order> updateSalesOrderWithLoading(@PathVariable(value="id") long id,@Valid @RequestBody Sales_Order iMaster)
	{
		Sales_Order op=sales_OrderService.updateSalesOrderWithLoading(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/updateSalesOrderWithLoadingItemDetails/{orderid}")
	public StatusDTO updateSalesOrderWithLoadingItemDetails(@PathVariable(value = "orderid") String orderid)
	{
		return sales_OrderService.updateSalesOrderWithLoadingItemDetails(orderid);
	}
	
	@PutMapping("/deleteEffectiveSalesOrder/{id}")
	public ResponseEntity<Sales_Order> deleteEffectiveSalesOrder(@PathVariable(value="id") long id,@Valid @RequestBody Sales_Order sOrder)
	{
		Sales_Order op=sales_OrderService.deleteEffectiveSalesOrder(sOrder,id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteSalesOrder/{id}")
	public ResponseEntity<Sales_Order> deleteSalesOrder(@PathVariable(value="id") long id,@Valid @RequestBody Sales_Order sOrder)
	{
		Sales_Order op=sales_OrderService.deleteSalesOrder(sOrder,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/SalesOrderTerminate/{id}/{username}/{quotationid}")
	public StatusDTO SalesOrderTerminate(@PathVariable(value= "id") long id,
											 @PathVariable(value= "username") String username,@PathVariable(value= "quotationid") String quotationid)
	{
		return sales_OrderService.SalesOrderTerminate(id,username,quotationid);
	}
	
	@GetMapping(value = "/salesOrderByParty/{custid}")
	public List<Sales_OrderDTO> salesOrderByParty(@PathVariable(value = "custid") String custid) {
		return sales_OrderService.salesOrderByParty(custid);
	}
	
	@GetMapping("/getSalesStockDetails")
	public ResponseEntity<Sales_transaction> getSalesStockDetails(@RequestParam(defaultValue = "") String orderid,@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String itemid,@RequestParam(defaultValue = "") String packingid)
	{
		Sales_transaction stocks = sales_OrderService.getSalesStockDetails(orderid,bunit,itemid,packingid);
		
		return new ResponseEntity<Sales_transaction>(stocks, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getSalesStockDetailsThruLoad")
	public ResponseEntity<Sales_transaction> getSalesStockDetailsThruLoad(@RequestParam(defaultValue = "") String orderid,@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String itemid,
			@RequestParam(defaultValue = "") String packingid,@RequestParam(defaultValue = "") String loadingid)
	{
		Sales_transaction stocks = sales_OrderService.getSalesStockDetailsThruLoad(orderid,bunit,itemid,packingid,loadingid);
		
		return new ResponseEntity<Sales_transaction>(stocks, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@GetMapping("/getLoadingRestWeight/{orderid}/{itemid}/{packing_qty}")
	public StatusDTO getLoadingRestWeight(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "itemid") String itemid,@PathVariable(value = "packing_qty") Double packing_qty)
	{
		return sales_OrderService.getLoadingRestWeight(orderid,itemid,packing_qty);
	}
	
	
	@GetMapping("/getLoadingRestWeightJobwork/{orderid}/{itemid}/{packing_qty}/{packing}/{party}")
	public StatusDTO getLoadingRestWeightJobwork(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "itemid") String itemid,
			@PathVariable(value = "packing_qty") Double packing_qty,@PathVariable(value = "packing") String packing,@PathVariable(value = "party") String party )
	{
		return sales_OrderService.getLoadingRestWeightJobwork(orderid,itemid,packing_qty,packing,party);
	}
	
	@GetMapping("/getLoadingRestWeightJobworkrectify/{pur_cust_refdocno}/{item_qty}")
	public StatusDTO getLoadingRestWeightJobwork(@PathVariable(value = "pur_cust_refdocno") String pur_cust_refdocno,
			@PathVariable(value = "item_qty") Double item_qty )
	{
		return sales_OrderService.getLoadingRestWeightJobworkrectify(pur_cust_refdocno,item_qty);
	}
	
	@GetMapping("/getLoadingRestWeightJobWorkAllocation/{itemid}/{party}/{item_qty}")
	public StatusDTO getLoadingRestWeightJobWorkAllocation(@PathVariable(value = "itemid") String itemid,
			@PathVariable(value = "party") String party,@PathVariable(value = "item_qty") Double item_qty )
	{
		return sales_OrderService.getLoadingRestWeightJobWorkAllocation(itemid,party,item_qty);
	}
	
	@GetMapping("/checkjobworkrestwt/{advice_id}")
	public StatusDTO checkjobworkrestwt(@PathVariable(value = "advice_id") String advice_id )
	{
		return sales_OrderService.checkjobworkrestwt(advice_id);
	}
	
	
	
	@GetMapping("/getLoadingRestWeightupdate/{orderid}/{itemid}/{packing_qty}/{advice_id}/{packing}/{alter_item_code}/{alter_packing}")
	public StatusDTO getLoadingRestWeightupdate(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "itemid") String itemid,
			@PathVariable(value = "packing_qty") Double packing_qty,@PathVariable(value = "advice_id") String advice_id,
			@PathVariable(value = "packing") String packing,@PathVariable(value = "alter_item_code") String alter_item_code,@PathVariable(value = "alter_packing") String alter_packing )
	{
		return sales_OrderService.getLoadingRestWeightupdate(orderid,itemid,packing_qty,advice_id,packing,alter_item_code,alter_packing);
	}
	
	@GetMapping("/getLoadingRestWeightJobworkupdate/{orderid}/{itemid}/{packing_qty}/{advice_id}/{packing}/{party}")
	public StatusDTO getLoadingRestWeightJobworkupdate(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "itemid") String itemid,
													@PathVariable(value = "packing_qty") Double packing_qty,@PathVariable(value = "advice_id") String advice_id,
													@PathVariable(value = "packing") String packing,@PathVariable(value = "party") String party )
	{
		return sales_OrderService.getLoadingRestWeightJobworkupdate(orderid,itemid,packing_qty,advice_id,packing,party);
	}
	
	
	@GetMapping("/getLoadingRestWeightupdatelooseitem/{orderid}/{itemid}/{packing_qty}/{advice_id}/{packing}")
	public StatusDTO getLoadingRestWeightupdatelooseitem(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "itemid") String itemid,@PathVariable(value = "packing_qty") Double packing_qty,@PathVariable(value = "advice_id") String advice_id,@PathVariable(value = "packing") String packing )
	{
		return sales_OrderService.getLoadingRestWeightupdatelooseitem(orderid,itemid,packing_qty,advice_id,packing);
	}
	
	@GetMapping("/getRateChartItemSO/{itemid}/{packingcode}/{company}/{business_unit}/{order_date}/{pricebasedon}")
	public Item_rate_dtls getRateChartItemSO(@PathVariable(value = "itemid") String itemid,
			@PathVariable(value = "packingcode") String packingcode,
			@PathVariable(value = "company") String company,
			@PathVariable(value = "business_unit") String business_unit,
			@PathVariable(value = "order_date") String order_date,
			@PathVariable(value = "pricebasedon") String pricebasedon)
	{
		return sales_OrderService.getRateChartItemSO(itemid,packingcode,company,business_unit,order_date,pricebasedon);
	}
	
	@GetMapping("/getRateChartItemSOforItem/{itemid}/{packingcode}/{company}/{business_unit}/{order_date}")
	public List<Item_rate_dtls> getRateChartItemSOforItem(@PathVariable(value = "itemid") String itemid,
			@PathVariable(value = "packingcode") String packingcode,
			@PathVariable(value = "company") String company,
			@PathVariable(value = "business_unit") String business_unit,
			@PathVariable(value = "order_date") String order_date)
	{
		return sales_OrderService.getRateChartItemSOforItem(itemid,packingcode,company,business_unit,order_date);
	}
	
	
	@Autowired
	Sales_OrderRepository sales_OrderRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	Invoice_typeRepository invoice_typeRepository;
	
	@Autowired
	Sales_Order_SummaryRepository sales_Order_SummaryRepository;
	
      @GetMapping("/getAll/{page}/{size}")
	  public Page<Sales_Order_Pagination_DTO> list(@PathVariable(value = "page") int page,@PathVariable(value = "size") int size) {
		  
		  
		  
		PageRequest pageRequest = PageRequest.of(page, size,Sort.by("orderdate").descending().and(Sort.by("orderno")).descending());
		//(Sort.by("published").descending().and(Sort.by("title"))
		//PageRequest pageRequest = PageRequest.of(page, size,Sort.by(Direction.DESC, "orderdate"));
	    Page<Sales_Order> pageResult = sales_OrderRepository.findAll(pageRequest);
	
	    
	    List<Sales_Order_Pagination_DTO> todos = pageResult
	    	      .stream()
	    	      .map((Sales_Order sale) -> new Sales_Order_Pagination_DTO(sale.getId(),sale.getOrder_id(),sale.getOrder_date(),
	    	    		  sale.getOrder_no(),sale.getOrder_type(),sale.getCustomer(),sale.getCustomer(),
	    	    		  sale.getDelivery_date(),sale.getQ_status(),sale.getRef_type(),
	    	    		  sale.getInv_type(),0,sale.isLoading_status(),sale.getModified_type(),sale.getDeleted_by(),sale.getUpdated_by()))
	    	      .filter(c -> c.getModified_type().equals("INSERTED"))
	    	      .collect(Collectors.toList());
	    
	   
	    
	    todos.forEach((sOrder) -> {
			if(Utility.isNullOrEmpty(sOrder.getCustomer())) {
				sOrder.setCustomer_name(cust_bussiness_partnerRepository.getCustomer(sOrder.getCustomer()).getCp_name());
			}else {sOrder.setCustomer_name("None");}
			
			if(Utility.isNullOrEmpty(sOrder.getInv_type())) {
				sOrder.setInv_type(invoice_typeRepository.getSalesInvTypesDtls(sOrder.getInv_type()).getInvtype_name());
			}else {sOrder.setInv_type("None");}
			
			//System.err.println("Order No: "+sOrder.getOrder_id());
			sOrder.setNet_amount(sales_Order_SummaryRepository.getSalesOrdSummary(sOrder.getOrder_id()).getNet_amount());
			
			sOrder.setOrder_date(Utility.convertDateDDMMYYYY(sOrder.getOrder_date()));
		});
	   
	    return new PageImpl<>(todos, pageRequest, pageResult.getTotalElements());
	 
	  }
	  
	  @GetMapping(value = "/findsaleorder/{orderid}")
		public List<Sales_Order_Pagination_DTO> findsaleorder(@PathVariable(value = "orderid") String orderid) {
			return sales_OrderService.findsaleorder(orderid);
		}
		//public List<Sales_OrderDTO> findSalesOrders(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String party,@RequestParam(defaultValue = "") String invdate) {
		
		@GetMapping(value = "/searchsaleorder")
			
		/*public List<Sales_Order_Pagination_DTO> searchsaleorder(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String customername,@RequestParam(defaultValue = "") String finyear)
		 {
			return sales_OrderService.searchsaleorder(orderno,fromdate,todate,customername,finyear);
		 }*/
		
		public  List<Sales_OrderDTO> searchsaleorder(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String customername,@RequestParam(defaultValue = "") String finyear)
			 {
				return sales_OrderService.searchsaleorder(orderno,fromdate,todate,customername,finyear);
			 }
		
		/*public  List<Map<String,Object>> searchsaleorder(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String customername,@RequestParam(defaultValue = "") String finyear)
			 {
				return sales_OrderService.searchsaleorder(orderno,fromdate,todate,customername,finyear);
			 }*/
		
		@GetMapping("/getSaleOrderList/{currDate}/{finyear}")
		public List<Sales_OrderDTO> getSaleOrderList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
		{
			return sales_OrderService.getSaleOrderList(currDate,finyear);
		}
		
		@GetMapping("/getSaleOrderFastList/{currDate}/{finyear}")
		public List<Map<String,Object>> getSaleOrderFastList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
		{
			return sales_OrderService.getSaleOrderFastList(currDate,finyear);
		}
		
		@GetMapping("/getnumtowordsaleorder/{orderid}")
		public StatusDTO getnumtowordsaleorder(@PathVariable(value = "orderid") String orderid)
		{
			return sales_OrderService.getnumtowordsaleorder(orderid);
		}
		
		@GetMapping("/checkcustomeramount/{orderid}/{finyear}")
		public ResponsemsgDTO checkcustomeramount(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "finyear") String finyear)
		{
			return sales_OrderService.checkcustomeramount(orderid,finyear);
		}
		
		@GetMapping("/transporterNameChgsList/{orderid}")
		public List<Map<String, Object>> transporterNameChgsList(@PathVariable(value = "orderid") String orderid)
		{
			return sales_OrderService.transporterNameChgsList(orderid);
		}
		
		@GetMapping("/findJobSalesOrders/{bunit}/{party}/{advdate}")
		public List<Map<String, Object>> findJobSalesOrders(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "party") String party,@PathVariable(value = "advdate") String advdate)
		{
			return sales_OrderService.findJobSalesOrders(bunit,party,advdate);
		}
		
		@GetMapping("/getDelvChallanByOrder/{salesid}/{fyear}")
		public List<Map<String, Object>> getDelvChallanByOrder(@PathVariable(value = "salesid") String salesid,@PathVariable(value = "fyear") String fyear)
		{
			return sales_OrderService.getDelvChallanByOrder(salesid,fyear);
		}
		
		@GetMapping("/getSaleOrderItemThroughGrn/{salesid}/{grnid}")
		public List<Map<String, Object>> getSaleOrderItemThroughGrn(@PathVariable(value = "salesid") String salesid,@PathVariable(value = "grnid") String grnid)
		{
			return sales_OrderService.getSaleOrderItemThroughGrn(salesid,grnid);
		}
		
		@GetMapping("/getGrnWeighment/{grnid}")
		public Map<String, Object> getGrnWeighment(@PathVariable(value = "grnid") String grnid)
		{
			return sales_OrderService.getGrnWeighment(grnid);
		}
		
	/************** End Sales Order **************/
	
	/************** Sales Quotation **************/
	
	@Autowired
	Sales_QuotationService sales_QuotationService;
	
	@GetMapping("/getSalesQuotSequenceId/{prefix}/{orderdate}/{type}")
	public SalesSequenceIdDTO getSalesQuotSequenceId(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate,@PathVariable(value = "type") String type)
	{
		return sales_QuotationService.getSalesQuotSequenceId(prefix,orderdate,type);
	}
	
	@PostMapping("/createSalesQuotation")
	public Sales_Quotation save(@Valid @RequestBody Sales_Quotation sales_Quotation) 
	{
		return sales_QuotationService.save(sales_Quotation);
	}
	
	@GetMapping("/getSalesQuotations")
	public List<Sales_Quotation> findAllQuotationsOrders()
	{
		return sales_QuotationService.findAll();
	}
	
	@GetMapping("/getSalesQuotationsList/{cur_date}")
	public List<Map<String,Object>> getSalesQuotationsList(@PathVariable(value = "cur_date") String cur_date)
	{
		return sales_QuotationService.getSalesQuotationsList(cur_date);
	}
	
	@GetMapping("/searchSaleQuotation/{fromdate}/{todate}")
	public List<Map<String,Object>> searchSaleQuotation(@PathVariable(value = "fromdate") String fromdate,
														@PathVariable(value = "todate") String todate)
	{
		return sales_QuotationService.searchSaleQuotation(fromdate,todate);
	}
	
	@GetMapping(value = "/salesQuotationList")
	public List<Sales_QuotationDTO> salesQuotationList() {
		return sales_QuotationService.salesQuotationList();
	}
	
	@GetMapping(value = "/salesQuotationFinalise")
	public List<Sales_QuotationDTO> salesQuotationFinalise() {
		return sales_QuotationService.salesQuotationFinalise();
	}
	
	@GetMapping("/salesQuotationPrevList")
	public List<Sales_QuotationDTO> salesQuotationPrevList() {
		return sales_QuotationService.salesQuotationPrevList();
	}
	
	@GetMapping("/salesQuotationRetrive/{id}")
	public ResponseEntity<Sales_Quotation> salesQuotationRetrive(@PathVariable(value="id") Long id)
	{
		Sales_Quotation tgs=sales_QuotationService.findOne(id);
		if(tgs==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(tgs);
		}
	}
	
	@GetMapping("/getSalesQuotItemDtls/{quot_id}")
	public List<Sales_Quotation_Item_DtlsDTO> getSalesQuotItemDtls(@PathVariable(value = "quot_id") String quot_id)
	{
		return sales_QuotationService.getSalesQuotItemDtls(quot_id);
	}
	
	@GetMapping("/getSalesQuotBrokerDtls/{quot_id}")
	public List<Sales_Quotation_Broker_DtlsDTO> getSalesQuotBrokerDtls(@PathVariable(value = "quot_id") String quot_id)
	{
		return sales_QuotationService.getSalesQuotBrokerDtls(quot_id);
	}
	
	@GetMapping("/getSalesQuotSummary/{quot_id}")
	public Sales_Quotation_SummaryDTO getSalesQuotSummary(@PathVariable(value = "quot_id") String quot_id)
	{
		return sales_QuotationService.getSalesQuotSummary(quot_id);
	}
	
	@GetMapping("/getSalesQuotSummDtls/{quot_id}")
	public List<Sales_Quotation_Summary_dynDTO> getSalesQuotSummaryDtls(@PathVariable(value = "quot_id") String quot_id)
	{
		return sales_QuotationService.getSalesQuotSummaryDtls(quot_id);
	}
	
	@GetMapping("/getSalesQuotTransInfo/{quot_id}")
	public Sales_Quotation_Trans_InfoDTO getSalesQuotTransInfo(@PathVariable(value = "quot_id") String quot_id)
	{
		return sales_QuotationService.getSalesQuotTransInfo(quot_id);
	}
	
	@GetMapping("/getSalesQuotPartyDtls/{quot_id}")
	public List<Sales_Quotation_Party_DtlsDTO> getSalesQuotPartyDtls(@PathVariable(value = "quot_id") String quot_id)
	{
		return sales_QuotationService.getSalesQuotPartyDtls(quot_id);
	}
	
	
	@GetMapping("/getSalesQuotTermsCon/{quot_id}")
	public Sales_Quotation_Terms_ConDTO getSalesQuotTermsCon(@PathVariable(value = "quot_id") String quot_id)
	{
		return sales_QuotationService.getSalesQuotTermsCon(quot_id);
	}
	
	@GetMapping("/getSalesQuotShipDtls/{quot_id}")
	public Sales_Quotation_Shipment_DtlsDTO getSalesQuotShipDtls(@PathVariable(value = "quot_id") String quot_id)
	{
		return sales_QuotationService.getSalesQuotShipDtls(quot_id);
	}
	
	@GetMapping("/getSalesQuotDoc/{quot_id}")
	public List<Sales_Quotation_DocsDTO> getSalesQuotDoc(@PathVariable(value = "quot_id") String quot_id)
	{
		return sales_QuotationService.getSalesQuotDoc(quot_id);
	}
	
	@GetMapping("/getSalesQuotDetails/{quot_id}")
	public Sales_QuotationDTO getSalesQuotDetails(@PathVariable(value = "quot_id") String quot_id)
	{
		return sales_QuotationService.getSalesQuotDetails(quot_id);
	}
	
	@PutMapping("/updateSalesQuotation/{id}")
	public ResponseEntity<Sales_Quotation> updateSalesQuotation(@PathVariable(value="id") long id,@Valid @RequestBody Sales_Quotation iMaster)
	{
		Sales_Quotation op=sales_QuotationService.update(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/checkSalesQuotationUsage/{quot_id}")
	public ResponseEntity<StatusDTO> checkSalesQuotationUsage(@PathVariable(value= "quot_id") String quot_id)
	{
		StatusDTO checksalesquotation = sales_QuotationService.checkSalesQuotationUsage(quot_id);
		return ResponseEntity.ok().body(checksalesquotation);
	}
	
	@PutMapping("/deleteSalesQuotation/{id}")
	public ResponseEntity<Sales_Quotation> deleteSalesQuotation(@PathVariable(value="id") long id,@Valid @RequestBody Sales_Quotation salesquot)
	{
		Sales_Quotation op=sales_QuotationService.deleteSalesQuotation(salesquot,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/SalesQuotationTerminate/{id}/{username}")
	public StatusDTO SalesQuotationTerminate(@PathVariable(value= "id") long id,
											 @PathVariable(value= "username") String username)
	{
		return sales_QuotationService.SalesQuotationTerminate(id,username);
	}
	
	
	/************** End Quotation Order **************/
	
	
	/************** Start Delivery Challan **************/
	
	@Autowired
	Delivery_challanService delivery_challanService;
	
	@GetMapping("/getDeliveryChallanDataList/{currDate}/{finyear}")
	public List<Delivery_challanDTO> getDeliveryChallanDataList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return delivery_challanService.getDeliveryChallanDataList(currDate,finyear);
	}
	
	@GetMapping("/getDeliveryChallanFastList/{currDate}/{finyear}")
	public List<Map<String,Object>> getDeliveryChallanFastList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return delivery_challanService.getDeliveryChallanFastList(currDate,finyear);
	}
	
	@GetMapping("/getDCSequenceId/{fin_year}/{inv_type}")
	public SalesSequenceIdDTO getDCSequenceId(@PathVariable(value = "fin_year") String fin_year,@PathVariable(value = "inv_type") String inv_type)
	{
		return delivery_challanService.getDCSequenceId(fin_year,inv_type);
	}
	
	@GetMapping("/getDCSequenceIdforDefence/{fin_year}/{inv_type}/{cust_id}")
	public SalesSequenceIdDTO getDCSequenceIdforDefence(@PathVariable(value = "fin_year") String fin_year,@PathVariable(value = "inv_type") String inv_type,@PathVariable(value = "cust_id") String cust_id)
	{
		return delivery_challanService.getDCSequenceIdforDefence(fin_year,inv_type,cust_id);
	}
	
  @GetMapping(value = "/searchDeliveryChallan")
		public List<Delivery_challan_Pagination_DTO> searchDeliveryChallan(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String party1,@RequestParam(defaultValue = "") String finyear)
		 {
			System.out.println(orderno+"//"+fromdate+"//"+todate+"//"+party1+"//"+finyear);
			return delivery_challanService.searchDeliveryChallan(orderno,fromdate,todate,party1,finyear);
		 }
		
  @GetMapping(value = "/searchDeliveryChallanFast")
	public List<Map<String,Object>> searchDeliveryChallanFast(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String party1,@RequestParam(defaultValue = "") String finyear)
	 {
		return delivery_challanService.searchDeliveryChallanFast(orderno,fromdate,todate,party1,finyear);
	 }
			
	@PostMapping("/createDeliveryChallan")
	public Delivery_challan save(@Valid @RequestBody Delivery_challan dChallan) 
	{
		return delivery_challanService.save(dChallan);
	}
	
	@GetMapping("/getDeliveryChallans")
	public List<Delivery_challan> findAllDeliveryChallans()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		  // System.out.println("1:: "+dtf.format(now)); 
		   
		   List<Delivery_challan> alldata=delivery_challanService.findAll();
		   LocalDateTime now1 = LocalDateTime.now();  
		   //System.out.println("2:: "+dtf.format(now1)); 
		return alldata;
				
	}
	
	 @GetMapping("/getDeliveryChallans_pagination/{page}/{size}")
	  public Page<Delivery_challan_Pagination_DTO> getDeliveryChallans_pagination(@PathVariable(value = "page") int page,@PathVariable(value = "size") int size) 
	{
		  return delivery_challanService.getDeliveryChallans_pagination(page,size);
	}
	
	@GetMapping("/getDeliveryChallanDtls")
	public Delivery_challanDTO getDeliveryChallanDtls(@RequestParam String delivery_cid)
	{
		return delivery_challanService.getDeliveryChallanDtls(delivery_cid);
	}
	
	@GetMapping("/getDeliveryChallanDtlsFast/{delivery_cid}")
	public Map<String,Object> getDeliveryChallanDtlsFast(@PathVariable(value= "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDeliveryChallanDtlsFast(delivery_cid);
	}
	
	@GetMapping("/deliverychallanjobworkRetriveList/{delivery_cid}")
	public List<Map<String,Object>> deliverychallanjobworkRetriveList(@PathVariable(value= "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.deliverychallanjobworkRetriveList(delivery_cid);
	}
	
	
	@GetMapping(value = "/getDelvChallans")
	public List<Delivery_challanDTO> getDelvChallans(@RequestParam String invtype,
			@RequestParam String party,
			@RequestParam String invdate,
			@RequestParam String comp,
			@RequestParam  String parentmodel) {
		return delivery_challanService.deliveryChallanList(invtype,party,invdate,comp,parentmodel);
	}
	
	
	@GetMapping(value = "/getDelvChallansnew")
	public List<Map<String, Object>> getDelvChallansnew(@RequestParam String invtype,
			@RequestParam String party,
			@RequestParam String invdate,
			@RequestParam String comp,
			@RequestParam  String parentmodel) {
		return delivery_challanService.getDelvChallansnew(invtype,party,invdate,comp,parentmodel);
	}
	
	
	@GetMapping(value = "/getDelvChallansnewjobwork")
	public List<Map<String, Object>> getDelvChallansnewjobwork(@RequestParam String invtype,
			@RequestParam String party,
			@RequestParam String invdate,
			@RequestParam String comp,
			@RequestParam  String parentmodel) {
		return delivery_challanService.getDelvChallansnewjobwork(invtype,party,invdate,comp,parentmodel);
	}
	
	@GetMapping(value = "/getMultipleDelvChallans")
	public List<Delivery_challanDTO> getMultipleDelvChallans(@RequestParam String invtype,
			@RequestParam String party,
			@RequestParam String invdate,
			@RequestParam String comp,
			@RequestParam  String parentmodel) {
		return delivery_challanService.getMultipleDelvChallans(invtype,party,invdate,comp,parentmodel);
	}
	
	@GetMapping(value = "/getMultipleDelvChallansUpdate/{id}")
	public List<Delivery_challanDTO> getMultipleDelvChallansUpdate(@PathVariable(value= "id") Long id) {
		return delivery_challanService.getMultipleDelvChallansUpdate(id);
	}
	
	
	@GetMapping(value = "/getDelvChallansApp")
	public List<Delivery_challanDTO> getDelvChallansApp(
			@RequestParam String party,
			@RequestParam String invdate,
			@RequestParam String comp,
			@RequestParam  String parentmodel) {
		return delivery_challanService.getDelvChallansApp(party,invdate,comp,parentmodel);
	}
	
	@GetMapping(value = "/getDelvChallansReturnApp")
	
	public List<Map<String,Object>> getDelvChallansReturnApp(
			@RequestParam String party,
			@RequestParam String invdate,
			@RequestParam String comp,
			@RequestParam  String parentmodel) {
		return delivery_challanService.getDelvChallansReturnApp(party,invdate,comp,parentmodel);
	}
	
	@GetMapping(value = "/getMultipleDelvChallansApp")
	public List<Delivery_challanDTO> getMultipleDelvChallansApp(
			@RequestParam String party,
			@RequestParam String invdate,
			@RequestParam String comp,
			@RequestParam  String parentmodel) {
		return delivery_challanService.getMultipleDelvChallansApp(party,invdate,comp,parentmodel);
	}
	
	@GetMapping("/retriveDeliveryChallan/{id}")
	public ResponseEntity<Delivery_challan> retriveDeliveryChallan(@PathVariable(value="id") Long id)
	{
		Delivery_challan wla=delivery_challanService.findOne(id);
		if(wla==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(wla);
		}
	}
	@GetMapping("/retriveDeliveryChallanOrderNo/{delivery_cid}")
	public Sales_Order retriveDeliveryChallanOrderNo(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		//System.out.println("delivery_cid::"+delivery_cid);
		return delivery_challanService.retriveDeliveryChallanOrderNo(delivery_cid);
	}
	
	@GetMapping("/getDlvChallanItemDtls/{delivery_cid}")
	public List<Delivery_challan_Item_DtlsDTO> getDlvCItmDtls(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDlvCItmDtls(delivery_cid);
	}
	
	@GetMapping("/getDlvChallanItemjobworkRetriveList/{delivery_cid}")
	public List<Map<String,Object>> getDlvChallanItemjobworkRetriveList(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDlvChallanItemjobworkRetriveList(delivery_cid);
	}
	
	@GetMapping("/getRestDlvChallanItemDtls/{delivery_cid}")
	public List<Map<String,Object>> getRestDlvChallanItemDtls(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getRestDlvChallanItemDtls(delivery_cid);
	}
	
/*	@GetMapping("/getDlvChallanItemDtlsHsn/{delivery_cid}")
	public List<Delivery_challan_Item_DtlsDTO> getDlvCItmDtlshsn(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDlvCItmDtlshsn(delivery_cid);
	}*/
	
	@GetMapping("/getDlvChallanPartyDtls/{delivery_cid}")
	public List<Delivery_challan_Party_DtlsDTO> getDlvCPtyDtls(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDlvCPtyDtls(delivery_cid);
	}
	
	@GetMapping("/getDlvChallanBrokerDtls/{delivery_cid}")
	public List<Delivery_challan_Broker_DtlsDTO> getDlvChallanBrokerDtls(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDlvChallanBrokerDtls(delivery_cid);
	}
	
	@GetMapping("/getDelivery_challan_Chgs_dyn/{delivery_cid}")
	public List<Map<String,Object>> getDelivery_challan_Chgs_dyn(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDelivery_challan_Chgs_dyn(delivery_cid);
	}
	
	@GetMapping("/getDelivery_challan_Chgs_dynDtls/{delivery_cid}")
	public List<Map<String,Object>> getDelivery_challan_Chgs_dynDtls(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDelivery_challan_Chgs_dynDtls(delivery_cid);
	}
	
	@GetMapping("/getDelivery_challan_Chgs_dynpopup/{loadingid}")
	public List<Map<String,Object>> getDelivery_challan_Chgs_dynpopup(@PathVariable(value = "loadingid") String loadingid)
	{
		return delivery_challanService.getDelivery_challan_Chgs_dynpopup(loadingid);
	}
	
	@GetMapping("/getDlvChallanShipmentDtls/{delivery_cid}")
	public Delivery_challan_Shipment_DtlsDTO getDlvChallanShipmentDtls(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDlvChallanShipmentDtls(delivery_cid);
	}
	
	@GetMapping("/getDlvChallanShipmentDtlsFast/{delivery_cid}")
	public Map<String,Object> getDlvChallanShipmentDtlsFast(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDlvChallanShipmentDtlsFast(delivery_cid);
	}
	
	@GetMapping("/getdeliverychallernpartyterm/{salesreturnnoteid}")
	public Delivery_challan getdeliverychallernpartyterm(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return delivery_challanService.getdeliverychallernpartyterm(salesreturnnoteid);
	}
	
	@GetMapping("/getDlvChallanWeightDtls/{delivery_cid}")
	public Delivery_challan_weight_dtlDTO getDlvChallanWeightDtls(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDlvChallanWeightDtls(delivery_cid);
	}
	
	@GetMapping("/getDlvChallanTransInfo/{delivery_cid}")
	public Delivery_challan_Trans_InfoDTO getDlvChallanTransInfo(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDlvChallanTransInfo(delivery_cid);
	}
	
	@GetMapping("/getDlvChlnTransInfo/{delivery_cid}")
	public List<Invoice_Delivery_Trans_InfoDTO> getDlvChlnTransInfo(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDlvChlnTransInfo(delivery_cid);
	}
	
	@GetMapping("/getMultipleDlvChlnTransInfo/{delivery_cid}")
	public List<Invoice_Delivery_Trans_InfoDTO> getMultipleDlvChlnTransInfo(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getMultipleDlvChlnTransInfo(delivery_cid);
	}
	
	@GetMapping("/getDlvChallanDoc/{delivery_cid}")
	public List<Delivery_challan_DocsDTO> getDlvCDoc(@PathVariable(value = "delivery_cid") String delivery_cid)
	{
		return delivery_challanService.getDlvCDoc(delivery_cid);
	}
	
	@PutMapping("/updateDlvChallan/{id}")
	public ResponseEntity<Delivery_challan> updateDlvChallan(@PathVariable(value="id") long id,@Valid @RequestBody Delivery_challan iMaster)
	{
		Delivery_challan op=delivery_challanService.update(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/updateDlvChallantransport/{id}")
	public ResponseEntity<Delivery_challan> updateDlvChallantransport(@PathVariable(value="id") long id,@Valid @RequestBody Delivery_challan iMaster)
	{
		Delivery_challan op=delivery_challanService.updateDlvChallantransport(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/updateTransporterDetailsthruPopup/{delv_id}/{distance}")
	public StatusDTO updateTransporterDetailsthruPopup(@PathVariable(value="delv_id") String delv_id,@PathVariable(value="distance") double distance)
	{
		return delivery_challanService.updateTransporterDetailsthruPopup(delv_id,distance);
	}
	
	@PutMapping("/deleteDeliveryChallan/{id}")
	public ResponseEntity<Delivery_challan> deleteDeliveryChallan(@PathVariable(value="id") long id,@Valid @RequestBody Delivery_challan delvid)
	{
		Delivery_challan op=delivery_challanService.deleteDeliveryChallan(delvid,id);
		return ResponseEntity.ok().body(op);
	}
	
	
	@GetMapping("/checkcashchallan/{orderdate}/{totalamount}/{id}/{party}/{referance_id}")
	public StatusDTO checkcashchallan(@PathVariable(value = "orderdate") String orderdate,@PathVariable(value = "totalamount") double totalamount,@PathVariable(value = "id") long id,@PathVariable(value = "party") String party,@PathVariable(value = "referance_id") String referance_id)
	{
		return delivery_challanService.checkcashchallan(orderdate,totalamount,id,party,referance_id);
	}
	
	
	@GetMapping("/getchallanReport/{fromdate}/{todate}")
	public List<Map<String, Object>> getchallanReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
	{
		return delivery_challanService.getchallanReport(fromdate,todate);
	}
	
	@GetMapping(value = "/getSalesOrdIfMultiTransInfo/{delveryid}")
	public Map<String, Object> getSalesOrdIfMultiTransInfo(@PathVariable(value = "delveryid") String delveryid) {
		return delivery_challanService.getSalesOrdIfMultiTransInfo(delveryid);
	}
	
	@GetMapping(value = "/getDlvChallanWeightDtlsMulti/{delveryid}")
	public Map<String, Object> getDlvChallanWeightDtlsMulti(@PathVariable(value = "delveryid") String delveryid) {
		return delivery_challanService.getDlvChallanWeightDtlsMulti(delveryid);
	}
	
	@GetMapping(value = "/getLoadingAdviceTransDtls/{delveryid}")
	public Map<String, Object> getLoadingAdviceTransDtls(@PathVariable(value = "delveryid") String delveryid) {
		return delivery_challanService.getLoadingAdviceTransDtls(delveryid);
	}
	
	@GetMapping(value = "/getGrnDetails/{grnid}")
	public Map<String, Object> getGrnDetails(@PathVariable(value = "grnid") String grnid) {
		return delivery_challanService.getGrnDetails(grnid);
	}
	
	@GetMapping(value = "/getGrndetailsforWeighment/{grnid}/{company}")
	public Map<String, Object> getGrndetailsforWeighment(@PathVariable(value = "grnid") String grnid,@PathVariable(value = "company") String company) {
		return delivery_challanService.getGrndetailsforWeighment(grnid,company);
	}
	
	
	/************** End Delivery Challan **************/
	
	/***************** Gate Pass ****************/
	
	@Autowired
	Gate_PassService gate_PassService;
	
	@PostMapping("/createGatePass")
	public Gate_Pass save(@Valid @RequestBody Gate_Pass gPass) 
	{
		return gate_PassService.saveGatePass(gPass);
	}
	
	@GetMapping("/getGatePasses")
	public List<Gate_Pass> getGatePasses()
	{
		return gate_PassService.findAllGPass();
	}
	
	/***************** End Gate Pass ****************/
	
	/************ Sales Invoice *****************/
	
	@Autowired
	Sales_InvoiceService sales_InvoiceService;
	 
	@GetMapping("/getSalesInvoiceDataList/{currDate}/{finyear}")
	public List<Sales_InvoiceDTO> getSalesInvoiceDataList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return sales_InvoiceService.getSalesInvoiceDataList(currDate,finyear);
	}
	
	@GetMapping("/getSalesInvoiceDataListFast/{currDate}/{finyear}")
	public List<Map<String,Object>> getSalesInvoiceDataListFast(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return sales_InvoiceService.getSalesInvoiceDataListFast(currDate,finyear);
	}
	
	@GetMapping("/getSISequenceId/{fin_year}/{inv_type}")
	public SalesSequenceIdDTO getSISequenceId(@PathVariable(value = "fin_year") String fin_year,@PathVariable(value = "inv_type") String inv_type)
	{
		return sales_InvoiceService.getSISequenceId(fin_year,inv_type);
	}
	
	@GetMapping("/getSISequenceIdforDefence/{fin_year}/{inv_type}/{custid}")
	public SalesSequenceIdDTO getSISequenceIdforDefence(@PathVariable(value = "fin_year") String fin_year,@PathVariable(value = "inv_type") String inv_type,@PathVariable(value = "custid") String custid)
	{
		return sales_InvoiceService.getSISequenceIdforDefence(fin_year,inv_type,custid);
	}
	
/*	@PostMapping("/createSalesInvoice")
	public Sales_Invoice save(@Valid @RequestBody Sales_Invoice sales_Invoice) 
	{
		return sales_InvoiceService.save(sales_Invoice);
	}*/
	
	@PostMapping("/createSalesInvoice")
	public Sales_Invoice createSalesInvoice(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		//System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Sales_Invoice sales_Invoice=objectMapper.readValue(input, Sales_Invoice.class);
		//	public Wm_unload_wgmnt save(Wm_unload_wgmnt wgmnt,MultipartFile files[]) throws IOException;
		return sales_InvoiceService.save(sales_Invoice,files);
	}
	
	@PostMapping("/updateSalesInv")
	public Sales_Invoice updateSalesInv(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		System.out.println(" input check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Sales_Invoice sales_Invoice=objectMapper.readValue(input, Sales_Invoice.class);
		
		return sales_InvoiceService.update(sales_Invoice,files);
	}
	
	/*@PutMapping("/updateSalesInv/{id}")
	public ResponseEntity<Sales_Invoice> updateSalesInv(@PathVariable(value="id") long id,@Valid @RequestBody Sales_Invoice iMaster)
	{
		Sales_Invoice op=sales_InvoiceService.update(iMaster,id);
		return ResponseEntity.ok().body(op);
	}*/
	
	@GetMapping("/getSalesInvoice")
	public List<Sales_InvoiceDTO> getSalesInvoice(@RequestParam String company)
	{
		return sales_InvoiceService.getSalesInvoice(company);
	}
	
	@GetMapping("/getSalesInvoices")
	public List<Sales_InvoiceDTO> getSalesInvoices(
			@RequestParam String party,
			@RequestParam String invdate,
			@RequestParam String comp) {
		return sales_InvoiceService.getSalesInvoices(party,invdate,comp);
	}
	
	@GetMapping("/getSalesInvoiceReturn")
	public List<Map<String,Object>> getSalesInvoiceReturn(
			@RequestParam String party,
			@RequestParam String invdate,
			@RequestParam String comp) {
		return sales_InvoiceService.getSalesInvoiceReturn(party,invdate,comp);
	}
	
	@GetMapping("/getSalesInvDetails/{invoice_id}")
	public Sales_InvoiceDTO getSalesInvDetails(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getSalesInvDetails(invoice_id);
	}
	
	@GetMapping("/retriveSalesInv/{id}")
	public ResponseEntity<Sales_InvoiceDTO> retriveSalesInv(@PathVariable(value="id") Long id)
	{
		Sales_InvoiceDTO si=sales_InvoiceService.findOne(id);
		if(si==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().body(si);
		}
	}
	
	@GetMapping("/getSalesInvItmDtls/{invoice_id}")
	public List<Sales_Invoice_Item_DtlsDTO> getSalesInvItmDtls(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getSalesInvItmDtls(invoice_id);
	}
	
	@GetMapping("/getSalesInvItmDtlswtAltName/{invoice_id}")
	public List<Map<String,Object>> getSalesInvItmDtlswtAltName(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getSalesInvItmDtlswtAltName(invoice_id);
	}
	
	
	@GetMapping("/retriveinvoicejobworkprice/{invoice_id}")
	public List<Map<String,Object>> retriveinvoicejobworkprice(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.retriveinvoicejobworkprice(invoice_id);
	}
	
	
	
	@GetMapping("/getSalesInvItmDtls1/{invoice_id}")
	public List<Sales_Invoice_Item_DtlsDTO> getSalesInvItmDtls1(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getSalesInvItmDtls1(invoice_id);
	}
	
	@GetMapping("/getSalesInvTaxInfo/{invoice_id}")
	public Sales_Invoice_Tax_InfoDTO getSalesInvTaxInfo(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getSalesInvTaxInfo(invoice_id);
	}
	
	@GetMapping("/getSalesInvBrkDtls/{invoice_id}")
	public List<Sales_Invoice_Broker_DtlsDTO> getSalesInvBrkDtls(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getSalesInvBrkDtls(invoice_id);
	}
	
	@GetMapping("/getSalesInvBpDtls/{invoice_id}")
	public Sales_Invoice_BP_DtlsDTO getSalesInvBpDtls(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getSalesInvBpDtls(invoice_id);
	}
	
	@GetMapping("/getSalesInvDocs/{invoice_id}")
	public List<Sales_Invoice_DocsDTO> getSalesInvDocs(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getSalesInvDocs(invoice_id);
	}
	
	@GetMapping("/getSalesTransDtls/{invoice_id}")
	public List<Sales_Invoice_Trans_DtlsDTO> getSalesTransDtls(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getSalesTransDtls(invoice_id);
	}
	
	@GetMapping("/getSalesShipDtls/{invoice_id}")
	public Sales_Invoice_Shipment_DtlsDTO getSalesShipDtls(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getSalesShipDtls(invoice_id);
	}
	
	@GetMapping("/getSalesPayDtls/{invoice_id}")
	public Sales_Invoice_Payment_DtlsDTO getSalesPayDtls(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getSalesPayDtls(invoice_id);
	}
	
	@GetMapping("/getInvoiceJobItemDtls/{invoice_id}")
	public List<Map<String,Object>> getInvoiceJobItemDtls(@PathVariable(value= "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getInvoiceJobItemDtls(invoice_id);
	}
	
	@GetMapping("/getInvoiceTServiceItem/{invoice_id}")
	public List<Map<String,Object>> getInvoiceTServiceItem(@PathVariable(value= "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getInvoiceTServiceItem(invoice_id);
	}
	@GetMapping("/geteinvoicedetails/{invoice_id}")
	public Map<String,Object> geteinvoicedetails(@PathVariable(value= "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.geteinvoicedetails(invoice_id);
	}

	@GetMapping("/getnumtoword/{taxamt}")
	public StatusDTO getnumtoword(@PathVariable(value = "taxamt") String taxamt)
	{
		return sales_InvoiceService.getnumtoword(taxamt);
	}
	@GetMapping("/getnumtowordsaleinvoice/{invoice_id}")
	public StatusDTO getnumtowordsaleinvoice(@PathVariable(value = "invoice_id") String invoice_id)
	{
		return sales_InvoiceService.getnumtowordsaleinvoice(invoice_id);
	}
	
	@PutMapping("/deleteSalesInv/{id}")
	public ResponseEntity<Sales_Invoice> deleteSalesInv(@PathVariable(value="id") long id,@Valid @RequestBody Sales_Invoice sInvoice)
	{
		Sales_Invoice op=sales_InvoiceService.deleteSalesInv(sInvoice,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getSalesPaymentDetails")
    public ResponseEntity<List<Sales_payment_detailsDTO>> getSalesPaymentDetails(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String party,@RequestParam(defaultValue = "") String fdate,@RequestParam(defaultValue = "") String tdate)
    {
    	List<Sales_payment_detailsDTO> payDetails = sales_InvoiceService.getSalesPaymentDetails(bunit,party,fdate,tdate);
    	
	    return new ResponseEntity<List<Sales_payment_detailsDTO>>(payDetails, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/getControlAccPayDetails")
    public ResponseEntity<List<Control_account_detailsDTO>> getControlAccPayDetails(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String ledger,@RequestParam(defaultValue = "") String fdate,@RequestParam(defaultValue = "") String tdate)
    {
    	List<Control_account_detailsDTO> payDetails = sales_InvoiceService.getControlAccPayDetails(bunit,ledger,fdate,tdate);
    	
	    return new ResponseEntity<List<Control_account_detailsDTO>>(payDetails, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/accountpostingSalesInvoice/{id}")
	public Sales_Invoice accountpostingSalesInvoice(@PathVariable(value = "id") Long id) {
		
			return sales_InvoiceService.accountpostingSalesInvoice(id);
		
	}
	
	 @GetMapping("/getSales_Invoice_pagination/{page}/{size}")
	  public Page<Sales_Invoice_Pagination_DTO> getSales_Invoice_pagination(@PathVariable(value = "page") int page,@PathVariable(value = "size") int size) 
	{
		  return sales_InvoiceService.getSales_Invoice_pagination(page,size);
	}
	  
  @GetMapping(value = "/searchSalesInvoice")
	public List<Sales_Invoice_Pagination_DTO> searchSalesInvoice(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String party1,@RequestParam(defaultValue = "") String finyear)
	 {
		return sales_InvoiceService.searchSalesInvoice(orderno,fromdate,todate,party1,finyear);
	 }
	 
  @GetMapping(value = "/searchSalesInvoiceFast")
	public List<Map<String,Object>> searchSalesInvoiceFast(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String party1,@RequestParam(defaultValue = "") String finyear)
	 {
		return sales_InvoiceService.searchSalesInvoiceFast(orderno,fromdate,todate,party1,finyear);
	 }
	 
	@GetMapping(value = "/geteinvoicestatus_saleinv/{id}/{invoiceno}")
	 public List<Map<String, Object>> geteinvoicestatus_saleinv(@PathVariable(value = "id") long id,@PathVariable(value = "invoiceno") String invoiceno) 
		{
			return sales_InvoiceService.geteinvoicestatus_saleinv(id,invoiceno);
		}
	
	@GetMapping(value = "/getJobWorkInvPrint/{mainid}")
	 public Map<String, Object> getJobWorkInvPrint(@PathVariable(value = "mainid") long mainid) 
		{
			  return sales_InvoiceService.getJobWorkInvPrint(mainid);
		}
	
	@GetMapping(value = "/getDelvChallanJobworkPrice/{delveryid}")
	public List<Map<String, Object>> getDelvChallanJobworkPrice(@PathVariable(value = "delveryid") String delveryid) {
		return sales_InvoiceService.getDelvChallanJobworkPrice(delveryid);
	}
	
	@GetMapping(value = "/getDelvChallanMultiJobworkPrice/{delveryid}")
	public List<Map<String, Object>> getDelvChallanMultiJobworkPrice(@PathVariable(value = "delveryid") String delveryid) {
		return sales_InvoiceService.getDelvChallanMultiJobworkPrice(delveryid);
	}
	
	@GetMapping(value = "/getSalesInvPayDtls/{invid}")
	public Map<String,Object> getSalesInvPayDtls(@PathVariable(value = "invid") String invid)
	{
		return sales_InvoiceService.getSalesInvPayDtls(invid);
	}

	/*@GetMapping(value = "/createEinvoiceGeneration/{id}/{einvjson}")
	public StatusDTO createEinvoiceGeneration(@PathVariable(value = "id") Long id,@PathVariable(value = "einvjson") Object  einvjson) throws JsonProcessingException

	{
		
		/*String USER_AGENT = "Mozilla/5.0";
		ObjectMapper mapper = new ObjectMapper();
		String oldSerial = mapper.writeValueAsString(einvjson);
		
		
		try
		 {
			//1ST API CALLING START
			
			String urlString =  "https://gstsandbox.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&user_name=TaxProEnvPON&eInvPwd=abc34*";
			
			 URL url = new URL(urlString);
			 HttpURLConnection con = (HttpURLConnection) url.openConnection();
			 con.setRequestMethod("GET");
			 con.setRequestProperty("User-Agent", USER_AGENT);
			 
			  int responseCode = con.getResponseCode();
			 // System.out.println("Sending get request : "+ url);
			//  System.out.println("Response code : "+ responseCode);
			  BufferedReader in = new BufferedReader(
			          new InputStreamReader(con.getInputStream()));
			  String output;
			  StringBuffer response = new StringBuffer();
			 
			  while ((output = in.readLine()) != null) {

			   response.append(output);
			  }
			 
			  in.close();
			  String s=response.toString();
			  JSONObject obj = new JSONObject(s);
					 
			  System.out.println("Return json: "+s+"\n\n");
			  System.out.println("Status: "+obj.get("Status"));
			  
			  obj = new JSONObject(obj.get("Data").toString());
			  System.out.println("Auth Token: "+obj.get("AuthToken"));
			  
			  String authtoken=""+obj.get("AuthToken");
			 
			  
			  // 1st Api Calling End....
			  
			  
			  String url1 = "https://gstsandbox.charteredinfo.com/eicore/dec/v1.03/Invoice?aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&AuthToken="+authtoken+"&user_name=TaxProEnvPON&QRCodeSize=200"; 
			  
			  URL objnew = new URL(url1);
			  HttpURLConnection con1 = (HttpURLConnection) objnew.openConnection();
			  con1.setRequestMethod("POST");
			  con1.setRequestProperty("User-Agent", USER_AGENT);
			  con1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			  con1.setRequestProperty("Content-Type","application/json");
			  
			  String postJsonData =  "{\"Version\":\"1.1\",\"TranDtls\":{\"TaxSch\":\"GST\",\"SupTyp\":\"B2B\",\"IgstOnIntra\":\"N\",\"RegRev\":null,\"EcmGstin\":null},\"DocDtls\":{\"Typ\":\"INV\",\"No\":\"JWI-2324-0000014\",\"Dt\":\"20-11-2023\"},\"SellerDtls\":{\"Gstin\":\"34AACCC1596Q002\",\"LglNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"TrdNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"Addr1\":\"802 MAHUA ROAD BELKUNDA\",\"Addr2\":\"BHOJPATTI VAISHALI\",\"Loc\":\"HAJIPUR\",\"Pin\":\"844125\",\"Stcd\":\"10\",\"Ph\":\"null\",\"Em\":null},\"BuyerDtls\":{\"Gstin\":\"\",\"LglNm\":\"BRITANNIA INDUSTRIES LIMITED-BIHTA\",\"TrdNm\":\"BRITANNIA INDUSTRIES LIMITED-BIHTA\",\"Pos\":\"10\",\"Addr1\":\"EPIP HAJIPUR INDUSTRIAL AREA, PLOT NUMBER-C3,C4 TO C11\",\"Addr2\":\"BIHAR\",\"Loc\":\"PATNA\",\"Pin\":801103,\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"ShipDtls\":null,\"ValDtls\":{\"AssVal\":50483.35,\"CgstVal\":1262.08,\"SgstVal\":1262.08,\"Discount\":\"0\",\"OthChrg\":\"0\",\"RndOffAmt\":0,\"TotInvVal\":53007.51},\"ItemList\":[{\"SlNo\":\"1\",\"PrdDesc\":\"CUSTOM MILLING CHARGE WHEAT FLOUR\",\"IsServc\":\"Y\",\"HsnCd\":\"998816\",\"Qty\":280.9,\"Unit\":\"QTL\",\"UnitPrice\":179.72,\"TotAmt\":50483.35,\"Discount\":0,\"PreTaxVal\":\"0\",\"AssAmt\":50483.35,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":1262.08,\"SgstAmt\":1262.08,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":53007.51}]}";
			  con1.setDoOutput(true);
			  DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
			  wr.writeBytes(postJsonData);
			  wr.flush();
			  wr.close();
			  
			  int responseCode1 = con1.getResponseCode();
			  System.out.println("nSending 'POST' request to URL : " + url1);
			  System.out.println("Post Data : " + postJsonData);
			  System.out.println("Response Code : " + responseCode1);
			 
			  BufferedReader in1 = new BufferedReader(
			  new InputStreamReader(con1.getInputStream()));
			  String output1;
			  StringBuffer response1 = new StringBuffer();
			 
			  while ((output1 = in1.readLine()) != null) {
			   response1.append(output1);
			  }
			  in1.close();
			  
			  System.out.println("Response JSON: "+response1.toString());
			 			  
		 }
		 catch(Exception e)
		 {
			 

		 }
		System.out.println("controler einvoicejson::"+fullName);
		return fullName;
	}*/

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Sales_InvoiceRepository sales_InvoiceRepository;
	
	@Autowired
	Sale_invoice_einvoice_genRepository sale_invoice_einvoice_genRepository;
		@Transactional
	 	@PutMapping("/createEinvoiceGeneration/{id}/{username}") 
	    public StatusDTO createEinvoiceGeneration2(@PathVariable(value="id") long id,@PathVariable(value="username") String username,@Valid @RequestBody StatusDTO statusDTO)
	    {
	    	System.out.println("message"+statusDTO.getStatus());
	    	//System.out.println("username"+username);
	    	String USER_AGENT = "Mozilla/5.0";
			
			String oldSerial = statusDTO.getStatus();
			
			
			try
			 {
				//1ST API CALLING START
				
				// String urlString =  "https://gstsandbox.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&user_name=TaxProEnvPON&eInvPwd=abc34*";
				 String urlString =  "https://einvapi.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&user_name=API_aayog&eInvPwd=Aayog@2022";
				
				 URL url = new URL(urlString);
				 HttpURLConnection con = (HttpURLConnection) url.openConnection();
				 con.setRequestMethod("GET");
				 con.setRequestProperty("User-Agent", USER_AGENT);
				 
				  int responseCode = con.getResponseCode();
				 // System.out.println("Sending get request : "+ url);
				//  System.out.println("Response code : "+ responseCode);
				  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				  String output;
				  StringBuffer response = new StringBuffer();
				 
				  while ((output = in.readLine()) != null) {

				   response.append(output);
				  }
				 
				  in.close();
				  String s=response.toString();
				  JSONObject obj = new JSONObject(s);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("Status"));
				  String f_api_status=""+obj.get("Status");
				  
				  System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
				  obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("Auth Token: "+obj.get("AuthToken"));
				  
				  String authtoken=""+obj.get("AuthToken");
				 
				  System.out.println("f_api_status status: "+f_api_status);
				  
				  // 1st Api Calling End....
				  
				  
				  //String url1 = "https://gstsandbox.charteredinfo.com/eicore/dec/v1.03/Invoice?aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&AuthToken="+authtoken+"&user_name=TaxProEnvPON&QRCodeSize=200";
				  String url1 = "https://einvapi.charteredinfo.com/eicore/dec/v1.03/Invoice?aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&AuthToken="+authtoken+"&user_name=API_aayog&QRCodeSize=200";
				  
				  URL objnew = new URL(url1);
				  HttpURLConnection con1 = (HttpURLConnection) objnew.openConnection();
				  con1.setRequestMethod("POST");
				  con1.setRequestProperty("User-Agent", USER_AGENT);
				  con1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				  con1.setRequestProperty("Content-Type","application/json");
				  
				  //String postJsonData =  "{\"Version\":\"1.1\",\"ShipDtls\":null,\"TranDtls\":{\"TaxSch\":\"GST\",\"SupTyp\":\"B2B\",\"IgstOnIntra\":\"N\",\"RegRev\":null,\"EcmGstin\":null},\"DocDtls\":{\"Typ\":\"INV\",\"No\":\"GSI-2324-0001049\",\"Dt\":\"23/11/2023\"},\"SellerDtls\":{\"Gstin\":\"34AACCC1596Q002\",\"LglNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"TrdNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"Addr1\":\"802 MAHUA ROAD BELKUNDA\",\"Addr2\":\"BHOJPATTI VAISHALI\",\"Loc\":\"HAJIPUR\",\"Pin\":\"844125\",\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"BuyerDtls\":{\"Gstin\":\"10BOOPK2572L1ZS\",\"LglNm\":\"SUBH KIRANA STORES\",\"TrdNm\":\"SUBH KIRANA STORES\",\"Pos\":\"10\",\"Addr1\":\"REPURA BAZAR, VIA SARAIYA, Muzaffarpur, Bihar, 843107\",\"Addr2\":\"\",\"Loc\":\"MUZAFFARPUR\",\"Pin\":843107,\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"ValDtls\":{\"AssVal\":9850,\"IgstVal\":0,\"CgstVal\":246.26,\"SgstVal\":246.26,\"StCesVal\":0,\"Discount\":0,\"OthChrg\":0,\"RndOffAmt\":0},\"ItemList\":[{\"SlNo\":\"1\",\"PrdDesc\":\"ATTA 30/10KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26},{\"SlNo\":\"2\",\"PrdDesc\":\"ATTA 30/5KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26}]}";
				  String postJsonData = oldSerial;
				  con1.setDoOutput(true);
				  DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
				  wr.writeBytes(postJsonData);
				  wr.flush();
				  wr.close();
				  
				  int responseCode1 = con1.getResponseCode();
				  System.out.println("nSending 'POST' request to URL : " + url1);
				  System.out.println("Post Data : " + postJsonData);
				  System.out.println("Response Code : " + responseCode1);
				 
				  BufferedReader in1 = new BufferedReader(
				  new InputStreamReader(con1.getInputStream()));
				  String output1;
				  StringBuffer response1 = new StringBuffer();
				 
				  while ((output1 = in1.readLine()) != null) {
				   response1.append(output1);
				  }
				  in1.close();
				  
				  System.out.println("Response JSON: "+response1.toString());
				  
				  s=response1.toString();
				  obj = new JSONObject(s);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("Status"));

				  String s_api_status=""+obj.get("Status");
				  String displaymessage="";
				  System.out.println("Data2: "+obj.get("Data").toString()+"\n\n");
				  if(obj.get("Data").toString().compareTo("null")==0)  
				  {
					 
					
					  JSONArray jsonArray = obj.getJSONArray("ErrorDetails"); 
					  for (int i = 0; i < jsonArray.length(); i++) 
					  	{  
			              
				            // store each object in JSONObject  
				            JSONObject explrObject = jsonArray.getJSONObject(i);  
				              
				            // get field value from JSONObject using get() method  
				            System.out.println("check error msg:"+explrObject.get("ErrorMessage"));  
				            displaymessage+=explrObject.get("ErrorMessage")+",";
				        }      
					  System.out.println("check error msg11:"+displaymessage);
					  
					  
					  if(displaymessage.substring(0,displaymessage.length()-1).compareTo("Duplicate IRN")==0)
					  {
						  System.out.println("Yes it is duplicated."+ obj.getJSONArray("InfoDtls"));
						  
						  JSONArray jsonArray1 = obj.getJSONArray("InfoDtls"); 
						  
						  for (int i = 0; i < jsonArray1.length(); i++) 
						  	{  
				              
					             // store each object in JSONObject  
					             JSONObject explrObject = jsonArray1.getJSONObject(i);  
					              
					             // get field value from JSONObject using get() method  
					             System.out.println("check Desc msg:"+explrObject.get("Desc"));  
					            
					             obj = new JSONObject(""+explrObject.get("Desc"));
					             System.out.println("check IRN msg:"+obj.get("Irn")); 
					             String Irn=""+obj.get("Irn");
					            
					             
					              //urlString =  "https://gstsandbox.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&user_name=TaxProEnvPON&eInvPwd=abc34*";
					              urlString =  "https://einvapi.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&user_name=API_aayog&eInvPwd=Aayog@2022";
								
								  url = new URL(urlString);
								  con = (HttpURLConnection) url.openConnection();
								  con.setRequestMethod("GET");
								  con.setRequestProperty("User-Agent", USER_AGENT);
								 
								  responseCode = con.getResponseCode();
								 // System.out.println("Sending get request : "+ url);
								//  System.out.println("Response code : "+ responseCode);
								  in = new BufferedReader(new InputStreamReader(con.getInputStream()));
								  String output0;
								  response = new StringBuffer();
								 
								  while ((output0 = in.readLine()) != null) {

								   response.append(output0);
								  }
								 
								  in.close();
								  s=response.toString();
								  obj = new JSONObject(s);
										 
								  System.out.println("Return json: "+s+"\n\n");
								  System.out.println("Status: "+obj.get("Status"));
								  f_api_status=""+obj.get("Status");
								  
								  System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
								  obj = new JSONObject(obj.get("Data").toString());
								  System.out.println("Auth Token: "+obj.get("AuthToken"));
								  
								  authtoken=""+obj.get("AuthToken");
								  
								  //url1 = "https://gstsandbox.charteredinfo.com/eicore/dec/v1.03/Invoice/irn/"+Irn+"?aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&AuthToken="+authtoken+"&user_name=TaxProEnvPON";
								  url1 = "https://einvapi.charteredinfo.com/eicore/dec/v1.03/Invoice/irn/"+Irn+"?aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&AuthToken="+authtoken+"&user_name=API_aayog";
								  
								  url = new URL(url1);
								  con = (HttpURLConnection) url.openConnection();
								  con.setRequestMethod("GET");
								  con.setRequestProperty("User-Agent", USER_AGENT);
									 
								  responseCode = con.getResponseCode();
								  // System.out.println("Sending get request : "+ url);
								  // System.out.println("Response code : "+ responseCode);
								  in = new BufferedReader(new InputStreamReader(con.getInputStream()));
								  String output01;
								  response = new StringBuffer();
									 
								  while ((output01 = in.readLine()) != null) 
								  {
									  response.append(output01);
								  }
									 
								  in.close();
								  s=response.toString();
								  obj = new JSONObject(s);
											 
								  System.out.println("Return json: "+s+"\n\n");
								  String s1=obj.get("Data").toString();
								
								  System.out.println("Data2: "+obj.get("Data").toString()+"\n\n");
								  obj = new JSONObject(s1);
								  System.out.println("AckNo: "+obj.get("AckNo")+"Irn: "+"Irn: "+obj.get("Irn")+"SignedInvoice: "+obj.get("SignedInvoice")+"AckDt: "+obj.get("AckDt"));
								
					        }  
						  
						  String irn=""+obj.get("Irn");
						  String SignedQRCode=""+obj.get("SignedQRCode");
						  String inv_id=sales_InvoiceRepository.findSalesInvoiceDtls(id).getInvoice_id();
						  String ackno=""+obj.get("AckNo"); //einvoice no
						  String ackdate=""+obj.get("AckDt");
						  
						  int checkeing_inv_id=sale_invoice_einvoice_genRepository.checkIrn(irn);
						  System.out.println("checkeing_inv_id"+checkeing_inv_id);
						  if(checkeing_inv_id==1 )
						  {
							  Sale_invoice_einvoice_gen einv =new Sale_invoice_einvoice_gen();
							  LocalDateTime ldt = LocalDateTime.now();
							  einv.setModified_type("INSERTED");
							  einv.setInserted_by(username);
							  einv.setInserted_on(ldt);
							  einv.setUpdated_by("NA");
							  einv.setUpdated_on(ldt);
							  einv.setDeleted_by("NA");
							  einv.setDeleted_on(ldt);
							  einv.setUsername(username);
							  einv.setCancel_on(ldt);
							  einv.setCancel_by("NA");
							  einv.setInvoice_id(inv_id);
							  einv.setF_api_status(Boolean.parseBoolean(f_api_status));
							  einv.setAuth_token(authtoken);
							  einv.setS_api_status(Boolean.parseBoolean(s_api_status));
							  einv.setAck_no(ackno);
							  einv.setAck_date(ackdate);
							  einv.setIrn_no(irn);
							  einv.setCancel_status(false);
							  einv.setSighned_invoice(SignedQRCode);
							  einv.setCancel_message("Not Cancel");
							  
							  sales_InvoiceRepository.updateEinvoice(ackno,id);
							  
							  sale_invoice_einvoice_genRepository.save(einv); 
							  
							  statusDTO.setStatus(displaymessage.substring(0,displaymessage.length()-1));
						  }
						  else
						  {
							  statusDTO.setStatus("Duplicate IRN No And Already in Database.");
						  }
					  }
				  }
				  else
				  {
				  obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("AckNo: "+obj.get("AckNo"));
				  System.out.println("AckDt: "+obj.get("AckDt"));
				  System.out.println("Irn: "+obj.get("Irn"));
				  System.out.println("SignedInvoice: "+obj.get("SignedInvoice"));
				  System.out.println("s_api_status status: "+s_api_status);
				  
				  String ackno=""+obj.get("AckNo"); //einvoice no
				  String ackdate=""+obj.get("AckDt");
				 
				  String irn=""+obj.get("Irn");
				  String SignedQRCode=""+obj.get("SignedQRCode");
				  
				  Sale_invoice_einvoice_gen einv =new Sale_invoice_einvoice_gen();
				  LocalDateTime ldt = LocalDateTime.now();
				  einv.setModified_type("INSERTED");
				  einv.setInserted_by(username);
				  einv.setInserted_on(ldt);
				  einv.setUpdated_by("NA");
				  einv.setUpdated_on(ldt);
				  einv.setDeleted_by("NA");
				  einv.setDeleted_on(ldt);
				  einv.setUsername(username);
				  einv.setCancel_on(ldt);
				  einv.setCancel_by("NA");
				  String inv_id=sales_InvoiceRepository.findSalesInvoiceDtls(id).getInvoice_id();
				  einv.setInvoice_id(inv_id);
				  einv.setF_api_status(Boolean.parseBoolean(f_api_status));
				  einv.setAuth_token(authtoken);
				  einv.setS_api_status(Boolean.parseBoolean(s_api_status));
				  einv.setAck_no(ackno);
				  einv.setAck_date(ackdate);
				  einv.setIrn_no(irn);
				  einv.setSighned_invoice(SignedQRCode);
				  einv.setCancel_status(false);
				  einv.setCancel_message("Not Cancel");
				  
				  sales_InvoiceRepository.updateEinvoice(ackno,id);
				  
				  sale_invoice_einvoice_genRepository.save(einv);

				  /*MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
				  
				  //String qrname=invoiceid.replaceAll("/","-");
			  	  //File qrFile = new File("F:\\Avijit\\"+invoiceno+".png");
				  //File qrFile = new File("/usr/Aayog_Einvoice_Testing/"+invoiceno+".png");
			  
				  //File qrFile = new File("/usr/Aayog_Einvoice/"+inv_id+".png");
				  File qrFile = new File("G:\\Aayog_Einvoice\\"+inv_id+".png");
				  System.out.println(qrFile + " / " + inv_id);
				  
				  Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
				  hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
				  
				  BitMatrix bitMatrix = multiFormatWriter.encode(signedInvoice, BarcodeFormat.QR_CODE,200,200);
				  
				  System.out.println("Hi Avijit");
				  //int size = 1000;
				  
				  new createQRImage(qrFile, bitMatrix, "png");*/
				  
				//path where we want to get QR Code  
				 // String path = "G:\\Aayog_Einvoice\\"+inv_id+".png"; //Local 
				  //String path =  "/usr/documents/aayogeinvoice/"+inv_id+".png";	//online 
				  String path =  "/usr/ankitindiahajipur/documents/ankithajipureinvoice/"+inv_id+".png";	//online 
				  //Encoding charset to be used  
				  String charset = "UTF-8";  
				  Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
				  //generates QR code with Low level(L) error correction capability  
				  hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
				  //invoking the user-defined method that creates the QR code  
				  new createQRImage(SignedQRCode, path, charset, hashMap, 500, 500);//increase or decrease height and width accodingly   
				  //prints if the QR code is generated   
				  System.out.println("QR Code created successfully.");
				  
				  statusDTO.setStatus("Yes_E-Invoice is created Successfully. E-Invoice is: "+ackno);
				  
				  }
				 			  
			 }
			 catch(Exception e)
			 {
				System.out.println("Exception: "+e) ;
				statusDTO.setStatus(""+e);

			 }
			 
		    return sales_InvoiceService.createEinvoiceGeneration2(id,statusDTO);
	    }
	 
	@Transactional
	@PutMapping("/createEinvoicecancel/{id}/{username}") 
    public ResponseDTO createEinvoicecancel(@PathVariable(value="id") long id,@PathVariable(value="username") String username,@Valid @RequestBody ResponseDTO responseDTO)
    {
		System.out.println("Enter Method : ");
		ResponseDTO res=new ResponseDTO();
		 
		 String oldSerial=responseDTO.getStatus();
		
		 System.out.println(responseDTO.getStatus());
		 
		 String USER_AGENT = "Mozilla/5.0";
		 
		 try
		 {
			//1ST API CALLING START
			
			 //String urlString =  "https://gstsandbox.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&user_name=TaxProEnvPON&eInvPwd=abc34*";
			 String urlString =  "https://einvapi.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&user_name=API_aayog&eInvPwd=Aayog@2022";
			
			 URL url = new URL(urlString);
			 HttpURLConnection con = (HttpURLConnection) url.openConnection();
			 con.setRequestMethod("GET");
			 con.setRequestProperty("User-Agent", USER_AGENT);
			 
			  int responseCode = con.getResponseCode();
			 // System.out.println("Sending get request : "+ url);
			//  System.out.println("Response code : "+ responseCode);
			  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			  String output;
			  StringBuffer response = new StringBuffer();
			 
			  while ((output = in.readLine()) != null) {

			   response.append(output);
			  }
			 
			  in.close();
			  String s=response.toString();
			  JSONObject obj = new JSONObject(s);
					 
			  System.out.println("Return json: "+s+"\n\n");
			  System.out.println("Status: "+obj.get("Status"));
			  String f_api_status=""+obj.get("Status");
			  
			  System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
			  obj = new JSONObject(obj.get("Data").toString());
			  System.out.println("Auth Token: "+obj.get("AuthToken"));
			  
			  String authtoken=""+obj.get("AuthToken");
			 
			  System.out.println("f_api_status status: "+f_api_status);
			  
			  // 1st API calling end
			  
			  //2nd API calling Start
			  
			  //String url1 = "https://gstsandbox.charteredinfo.com/eicore/dec/v1.03/Invoice/Cancel?aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=TaxProEnvPON";
			  String url1 = "https://einvapi.charteredinfo.com/eicore/dec/v1.03/Invoice/Cancel?aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=API_aayog";
			  
			  URL objnew = new URL(url1);
			  HttpURLConnection con1 = (HttpURLConnection) objnew.openConnection();
			  con1.setRequestMethod("POST");
			  con1.setRequestProperty("User-Agent", USER_AGENT);
			  con1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			  con1.setRequestProperty("Content-Type","application/json");
			  
			  //String postJsonData =  "{\"Version\":\"1.1\",\"ShipDtls\":null,\"TranDtls\":{\"TaxSch\":\"GST\",\"SupTyp\":\"B2B\",\"IgstOnIntra\":\"N\",\"RegRev\":null,\"EcmGstin\":null},\"DocDtls\":{\"Typ\":\"INV\",\"No\":\"GSI-2324-0001049\",\"Dt\":\"23/11/2023\"},\"SellerDtls\":{\"Gstin\":\"34AACCC1596Q002\",\"LglNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"TrdNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"Addr1\":\"802 MAHUA ROAD BELKUNDA\",\"Addr2\":\"BHOJPATTI VAISHALI\",\"Loc\":\"HAJIPUR\",\"Pin\":\"844125\",\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"BuyerDtls\":{\"Gstin\":\"10BOOPK2572L1ZS\",\"LglNm\":\"SUBH KIRANA STORES\",\"TrdNm\":\"SUBH KIRANA STORES\",\"Pos\":\"10\",\"Addr1\":\"REPURA BAZAR, VIA SARAIYA, Muzaffarpur, Bihar, 843107\",\"Addr2\":\"\",\"Loc\":\"MUZAFFARPUR\",\"Pin\":843107,\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"ValDtls\":{\"AssVal\":9850,\"IgstVal\":0,\"CgstVal\":246.26,\"SgstVal\":246.26,\"StCesVal\":0,\"Discount\":0,\"OthChrg\":0,\"RndOffAmt\":0},\"ItemList\":[{\"SlNo\":\"1\",\"PrdDesc\":\"ATTA 30/10KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26},{\"SlNo\":\"2\",\"PrdDesc\":\"ATTA 30/5KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26}]}";
			  String postJsonData = oldSerial;
			  con1.setDoOutput(true);
			  DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
			  wr.writeBytes(postJsonData);
			  wr.flush();
			  wr.close();
			  
			  int responseCode1 = con1.getResponseCode();
			  System.out.println("nSending 'POST' request to URL : " + url1);
			  System.out.println("Post Data : " + postJsonData);
			  System.out.println("Response Code : " + responseCode1);
			 
			  BufferedReader in1 = new BufferedReader(
			  new InputStreamReader(con1.getInputStream()));
			  String output1;
			  StringBuffer response1 = new StringBuffer();
			 
			  while ((output1 = in1.readLine()) != null) {
			   response1.append(output1);
			  }
			  in1.close();
			  
			  System.out.println("Response JSON: "+response1.toString());
			  
			  s=response1.toString();
			  obj = new JSONObject(s);
					 
			  System.out.println("Return json: "+s+"\n\n");
			  System.out.println("Status: "+obj.get("Status"));

			  String s_api_status=""+obj.get("Status");
			  String displaymessage="";
			  System.out.println("Data2: "+obj.get("Data").toString()+"\n\n");
			  if(obj.get("Data").toString().compareTo("null")==0)  
			  {
				 
				
				  JSONArray jsonArray = obj.getJSONArray("ErrorDetails"); 
				  for (int i = 0; i < jsonArray.length(); i++) 
				  	{  
		              
			            // store each object in JSONObject  
			            JSONObject explrObject = jsonArray.getJSONObject(i);  
			              
			            // get field value from JSONObject using get() method  
			            System.out.println("check error msg:"+explrObject.get("ErrorMessage"));  
			            displaymessage+=explrObject.get("ErrorMessage")+",";
			        }
				  
				  //System.out.println("check error msg11:"+displaymessage);
				  res.setStatus(displaymessage.substring(0,displaymessage.length()-1));
				  
			  }
			  else
			  {
				  LocalDateTime ldt = LocalDateTime.now();
				  String invid=sales_InvoiceRepository.findSalesInvoiceDtls(id).getInvoice_id();
				  String message=responseDTO.getCancel_message();
				  
				 sales_InvoiceRepository.updateEinvoiceCancel(id);
				 sale_invoice_einvoice_genRepository.updateEinvoiceGenCancel(ldt,username,invid,message);
				 res.setStatus("Done");
				// System.out.println("Response JSON2: "+response1.toString());  
			  }
			  
			  
		 }
		 catch(Exception e)
		 {
			 
		 }
		 return res;
    }
	 
	@Transactional
	 @PutMapping("/ewaybillcreate/{id}/{username}") 
	    public StatusDTO ewaybillcreate(@PathVariable(value="id") long id,@PathVariable(value="username") String username,@Valid @RequestBody StatusDTO statusDTO)
	    {
		   StatusDTO res = new StatusDTO();
		   String oldSerial=statusDTO.getStatus();
			
		   System.out.println(statusDTO.getStatus());
		   
		   String USER_AGENT = "Mozilla/5.0";
			 
			 try
			 {
				//1ST API CALLING START
				
				 //String urlString =  "https://gstsandbox.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&user_name=TaxProEnvPON&eInvPwd=abc34*";
				 String urlString =  "https://einvapi.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&user_name=API_aayog&eInvPwd=Aayog@2022";
				
				 URL url = new URL(urlString);
				 HttpURLConnection con = (HttpURLConnection) url.openConnection();
				 con.setRequestMethod("GET");
				 con.setRequestProperty("User-Agent", USER_AGENT);
				 
				  int responseCode = con.getResponseCode();
				 // System.out.println("Sending get request : "+ url);
				//  System.out.println("Response code : "+ responseCode);
				  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				  String output;
				  StringBuffer response = new StringBuffer();
				 
				  while ((output = in.readLine()) != null) {

				   response.append(output);
				  }
				 
				  in.close();
				  String s=response.toString();
				  JSONObject obj = new JSONObject(s);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("Status"));
				  String f_api_status=""+obj.get("Status");
				  
				  System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
				  obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("Auth Token: "+obj.get("AuthToken"));
				  
				  String authtoken=""+obj.get("AuthToken");
				 
				  System.out.println("f_api_status status: "+f_api_status);
				  
				  // 1st API calling end
				  
				  //2nd API calling Start
				  
				  //String url1 = "https://gstsandbox.charteredinfo.com/eiewb/dec/v1.03/ewaybill?aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=TaxProEnvPON";
				  String url1 = "https://einvapi.charteredinfo.com/eiewb/dec/v1.03/ewaybill?aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=API_aayog";
				  
				  URL objnew = new URL(url1);
				  HttpURLConnection con1 = (HttpURLConnection) objnew.openConnection();
				  con1.setRequestMethod("POST");
				  con1.setRequestProperty("User-Agent", USER_AGENT);
				  con1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				  con1.setRequestProperty("Content-Type","application/json");
				  
				  //String postJsonData =  "{\"Version\":\"1.1\",\"ShipDtls\":null,\"TranDtls\":{\"TaxSch\":\"GST\",\"SupTyp\":\"B2B\",\"IgstOnIntra\":\"N\",\"RegRev\":null,\"EcmGstin\":null},\"DocDtls\":{\"Typ\":\"INV\",\"No\":\"GSI-2324-0001049\",\"Dt\":\"23/11/2023\"},\"SellerDtls\":{\"Gstin\":\"34AACCC1596Q002\",\"LglNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"TrdNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"Addr1\":\"802 MAHUA ROAD BELKUNDA\",\"Addr2\":\"BHOJPATTI VAISHALI\",\"Loc\":\"HAJIPUR\",\"Pin\":\"844125\",\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"BuyerDtls\":{\"Gstin\":\"10BOOPK2572L1ZS\",\"LglNm\":\"SUBH KIRANA STORES\",\"TrdNm\":\"SUBH KIRANA STORES\",\"Pos\":\"10\",\"Addr1\":\"REPURA BAZAR, VIA SARAIYA, Muzaffarpur, Bihar, 843107\",\"Addr2\":\"\",\"Loc\":\"MUZAFFARPUR\",\"Pin\":843107,\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"ValDtls\":{\"AssVal\":9850,\"IgstVal\":0,\"CgstVal\":246.26,\"SgstVal\":246.26,\"StCesVal\":0,\"Discount\":0,\"OthChrg\":0,\"RndOffAmt\":0},\"ItemList\":[{\"SlNo\":\"1\",\"PrdDesc\":\"ATTA 30/10KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26},{\"SlNo\":\"2\",\"PrdDesc\":\"ATTA 30/5KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26}]}";
				  String postJsonData = oldSerial;
				  con1.setDoOutput(true);
				  DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
				  wr.writeBytes(postJsonData);
				  wr.flush();
				  wr.close();
				  
				  int responseCode1 = con1.getResponseCode();
				  System.out.println("nSending 'POST' request to URL : " + url1);
				  System.out.println("Post Data : " + postJsonData);
				  System.out.println("Response Code : " + responseCode1);
				 
				  BufferedReader in1 = new BufferedReader(
				  new InputStreamReader(con1.getInputStream()));
				  String output1;
				  StringBuffer response1 = new StringBuffer();
				 
				  while ((output1 = in1.readLine()) != null) {
				   response1.append(output1);
				  }
				  in1.close();
				  
				  System.out.println("Response JSON: "+response1.toString());
				  
				  s=response1.toString();
				  obj = new JSONObject(s);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("Status"));

				  String s_api_status=""+obj.get("Status");
				  String displaymessage="";
				  System.out.println("Data2: "+obj.get("Data").toString()+"\n\n");
				  if(obj.get("Data").toString().compareTo("null")==0)  
				  {
					 
					
					  JSONArray jsonArray = obj.getJSONArray("ErrorDetails"); 
					  for (int i = 0; i < jsonArray.length(); i++) 
					  	{  
			              
				            // store each object in JSONObject  
				            JSONObject explrObject = jsonArray.getJSONObject(i);  
				              
				            // get field value from JSONObject using get() method  
				            System.out.println("check error msg:"+explrObject.get("ErrorMessage"));  
				            displaymessage+=explrObject.get("ErrorMessage")+",";
				        }
					  
					  //System.out.println("check error msg11:"+displaymessage);
					  res.setStatus(displaymessage.substring(0,displaymessage.length()-1));
					  
				  }
				  else
				  {
				  obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("EwbNo: "+obj.get("EwbNo"));
				  System.out.println("EwbDt: "+obj.get("EwbDt"));
				  LocalDateTime ldt = LocalDateTime.now();
				  String invid=sales_InvoiceRepository.findSalesInvoiceDtls(id).getInvoice_id();
				  
				  String ewaybill=""+obj.get("EwbNo");
				  String ewaybilldate=""+obj.get("EwbDt");
				  String ewaybillvaliddate=""+obj.get("EwbValidTill");
				  String distance="NA";
				 
				  sales_InvoiceRepository.updateEwaybill(id,ewaybill);
				  sale_invoice_einvoice_genRepository.updateEwaybillGen(ldt,username,invid,ewaybill,ewaybilldate,ewaybillvaliddate,distance);
				  
				  res.setStatus("Done_EWaybill is created successfully,EWaybillNo is "+ewaybill);
				  System.out.println("Response JSON Way bill: "+response1.toString());  
				  }
				  
				  
			 }
			 catch(Exception e)
			 {
				System.out.println("Exception: "+e) ;
				res.setStatus("None");
			 }
		   
		   return res;
	    }
	 
	 @Transactional
	 @PutMapping("/ewaybillcancel/{id}/{username}") 
	    public ResponseDTO ewaybillcancel(@PathVariable(value="id") long id,@PathVariable(value="username") String username,@Valid @RequestBody ResponseDTO responseDTO)
	    {
			 //StatusDTO res = new StatusDTO();
			 System.out.println(responseDTO.getStatus());
			 
			 //System.out.println("Enter Method : ");
			 
			 ResponseDTO res=new ResponseDTO();
			 
			 String oldSerial=responseDTO.getStatus();
			
			 System.out.println("oldSerial: "+oldSerial);
			 
			 String USER_AGENT = "Mozilla/5.0";
			 
			 try
			 {
				//1ST API CALLING START
				
				 //String urlString =  "https://gstsandbox.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&user_name=TaxProEnvPON&eInvPwd=abc34*";
				 String urlString =  "https://einvapi.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&user_name=API_aayog&eInvPwd=Aayog@2022";
				
				 URL url = new URL(urlString);
				 HttpURLConnection con = (HttpURLConnection) url.openConnection();
				 con.setRequestMethod("GET");
				 con.setRequestProperty("User-Agent", USER_AGENT);
				 
				  int responseCode = con.getResponseCode();
				 // System.out.println("Sending get request : "+ url);
				//  System.out.println("Response code : "+ responseCode);
				  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				  String output;
				  StringBuffer response = new StringBuffer();
				 
				  while ((output = in.readLine()) != null) {

				   response.append(output);
				  }
				 
				  in.close();
				  String s=response.toString();
				  JSONObject obj = new JSONObject(s);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("Status"));
				  String f_api_status=""+obj.get("Status");
				  
				  System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
				  obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("Auth Token: "+obj.get("AuthToken"));
				  
				  String authtoken=""+obj.get("AuthToken");
				 
				  System.out.println("f_api_status status: "+f_api_status);
				  
				  // 1st API calling end
				  
				  //2nd API calling Start
				  
				  //String url1 = "http://gstsandbox.charteredinfo.com/ewaybillapi/dec/v1.03/ewayapi?action=CANEWB&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=TaxProEnvPON";
				  String url1 = "https://einvapi.charteredinfo.com/ewaybillapi/dec/v1.03/ewayapi?action=CANEWB&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=API_aayog";
				  
				  URL objnew = new URL(url1);
				  HttpURLConnection con1 = (HttpURLConnection) objnew.openConnection();
				  con1.setRequestMethod("POST");
				  con1.setRequestProperty("User-Agent", USER_AGENT);
				  con1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				  con1.setRequestProperty("Content-Type","application/json");
				  
				  //String postJsonData =  "{\"Version\":\"1.1\",\"ShipDtls\":null,\"TranDtls\":{\"TaxSch\":\"GST\",\"SupTyp\":\"B2B\",\"IgstOnIntra\":\"N\",\"RegRev\":null,\"EcmGstin\":null},\"DocDtls\":{\"Typ\":\"INV\",\"No\":\"GSI-2324-0001049\",\"Dt\":\"23/11/2023\"},\"SellerDtls\":{\"Gstin\":\"34AACCC1596Q002\",\"LglNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"TrdNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"Addr1\":\"802 MAHUA ROAD BELKUNDA\",\"Addr2\":\"BHOJPATTI VAISHALI\",\"Loc\":\"HAJIPUR\",\"Pin\":\"844125\",\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"BuyerDtls\":{\"Gstin\":\"10BOOPK2572L1ZS\",\"LglNm\":\"SUBH KIRANA STORES\",\"TrdNm\":\"SUBH KIRANA STORES\",\"Pos\":\"10\",\"Addr1\":\"REPURA BAZAR, VIA SARAIYA, Muzaffarpur, Bihar, 843107\",\"Addr2\":\"\",\"Loc\":\"MUZAFFARPUR\",\"Pin\":843107,\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"ValDtls\":{\"AssVal\":9850,\"IgstVal\":0,\"CgstVal\":246.26,\"SgstVal\":246.26,\"StCesVal\":0,\"Discount\":0,\"OthChrg\":0,\"RndOffAmt\":0},\"ItemList\":[{\"SlNo\":\"1\",\"PrdDesc\":\"ATTA 30/10KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26},{\"SlNo\":\"2\",\"PrdDesc\":\"ATTA 30/5KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26}]}";
				  String postJsonData = oldSerial;
				  con1.setDoOutput(true);
				  DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
				  wr.writeBytes(postJsonData);
				  wr.flush();
				  wr.close();
				  
				  int responseCode1 = con1.getResponseCode();
				  System.out.println("nSending 'POST' request to URL : " + url1);
				  System.out.println("Post Data : " + postJsonData);
				  System.out.println("Response Code : " + responseCode1);
				 
				  BufferedReader in1 = new BufferedReader(
				  new InputStreamReader(con1.getInputStream()));
				  String output1;
				  StringBuffer response1 = new StringBuffer();
				 
				  while ((output1 = in1.readLine()) != null) {
				   response1.append(output1);
				  }
				  in1.close();
				  
				  System.out.println("Response JSON: "+response1.toString());
				  
				  s=response1.toString();
				  obj = new JSONObject(s);
						 
				  //System.out.println("Return json: "+s+"\n\n");
				  //System.out.println("Status: "+obj.get("Status"));

				  //String s_api_status=""+obj.get("Status");
				  //String displaymessage="";
				  //System.out.println("Data2: "+obj.get("Data").toString()+"\n\n");
				  
				  /*if(obj.get("Data").toString().compareTo("null")==0)  
				  {
					 
					
					  JSONArray jsonArray = obj.getJSONArray("ErrorDetails"); 
					  for (int i = 0; i < jsonArray.length(); i++) 
					  	{  
			              
				            // store each object in JSONObject  
				            JSONObject explrObject = jsonArray.getJSONObject(i);  
				              
				            // get field value from JSONObject using get() method  
				            System.out.println("check error msg:"+explrObject.get("ErrorMessage"));  
				            displaymessage+=explrObject.get("ErrorMessage")+",";
				        }
					  
					  //System.out.println("check error msg11:"+displaymessage);
					  responseDTO.setStatus(displaymessage.substring(0,displaymessage.length()-1));
					  
				  }
				  else
				  {*/
					  LocalDateTime ldt = LocalDateTime.now();
					  String invid=sales_InvoiceRepository.findSalesInvoiceDtls(id).getInvoice_id();
					  String message=responseDTO.getCancel_message();
					  
					 sales_InvoiceRepository.updateWaybillCancel(id);
					 sale_invoice_einvoice_genRepository.updateEwaybillGenCancel(ldt,username,invid,message);
					 responseDTO.setStatus("Done");
					// System.out.println("Response JSON2: "+response1.toString());  
				 // }
				  
				  
			 }
			 catch(Exception e)
			 {
				System.out.println("Ereror:"+e);
			 }
			 return responseDTO;
	    }
	
	 @Transactional
	 	@PutMapping("/createEwaybillWOInvoiceCreate/{id}/{username}") 
	    public StatusDTO createEwaybillWOInvoiceCreate(@PathVariable(value="id") long id,@PathVariable(value="username") String username,@Valid @RequestBody StatusDTO statusDTO)
	    {
	    	System.out.println("message"+statusDTO.getStatus());
	    	//System.out.println("username"+username);
	    	String USER_AGENT = "Mozilla/5.0";
			
			String oldSerial = statusDTO.getStatus();
			
			try
			 {
				//1ST API CALLING START
				
				// String urlString =  "https://gstsandbox.charteredinfo.com/ewaybillapi/dec/v1.03/auth?action=ACCESSTOKEN&aspid=1659183221&password=Avijit!12ch&gstin=34AACCC1596Q002&username=TaxProEnvPON&ewbpwd=abc34*";
				String urlString =  "https://einvapi.charteredinfo.com/v1.03/dec/auth?action=ACCESSTOKEN&aspid=1751181923&password=Aayog@123&gstin=10AATCA7447B1ZV&username=API_aayog&ewbpwd=Aayog@2022";
				 URL url = new URL(urlString);
				 HttpURLConnection con = (HttpURLConnection) url.openConnection();
				 con.setRequestMethod("GET");
				 con.setRequestProperty("User-Agent", USER_AGENT);
				 
				  int responseCode = con.getResponseCode();
				  //System.out.println("Sending get request : "+ url);
				  //System.out.println("Response code : "+ responseCode);
				  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				  String output;
				  StringBuffer response = new StringBuffer();
				 
				  while ((output = in.readLine()) != null) {

				   response.append(output);
				  }
				 
				  in.close();
				  String s=response.toString();
				  JSONObject obj = new JSONObject(s);
				  
				  //System.out.println("Input : "+in);
				  //System.out.println("Output: "+output);
				  //System.out.println("Response : "+response);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("status"));
				  String f_api_status=""+obj.get("status");
				  
				  //System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
				  //obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("Auth Token: "+obj.get("authtoken"));
				  
				  String authtoken=""+obj.get("authtoken");
				 
				  System.out.println("f_api_status status: "+f_api_status);
				  
				  // 1st Api Calling End....
				  
				  
				  //String url1 = "https://gstsandbox.charteredinfo.com/ewaybillapi/dec/v1.03/ewayapi?action=GENEWAYBILL&aspid=1659183221&password=Avijit!12ch&gstin=34AACCC1596Q002&username=TaxProEnvPON&authtoken="+authtoken;
				  String url1 = "https://einvapi.charteredinfo.com/v1.03/dec/ewayapi?action=GENEWAYBILL&aspid=1751181923&password=Aayog@123&gstin=10AATCA7447B1ZV&username=API_aayog&authtoken="+authtoken;
				  
				  URL objnew = new URL(url1);
				  HttpURLConnection con1 = (HttpURLConnection) objnew.openConnection();
				  con1.setRequestMethod("POST");
				  con1.setRequestProperty("User-Agent", USER_AGENT);
				  con1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				  con1.setRequestProperty("Content-Type","application/json");
				  
				  //String postJsonData =  "{\"Version\":\"1.1\",\"ShipDtls\":null,\"TranDtls\":{\"TaxSch\":\"GST\",\"SupTyp\":\"B2B\",\"IgstOnIntra\":\"N\",\"RegRev\":null,\"EcmGstin\":null},\"DocDtls\":{\"Typ\":\"INV\",\"No\":\"GSI-2324-0001049\",\"Dt\":\"23/11/2023\"},\"SellerDtls\":{\"Gstin\":\"34AACCC1596Q002\",\"LglNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"TrdNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"Addr1\":\"802 MAHUA ROAD BELKUNDA\",\"Addr2\":\"BHOJPATTI VAISHALI\",\"Loc\":\"HAJIPUR\",\"Pin\":\"844125\",\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"BuyerDtls\":{\"Gstin\":\"10BOOPK2572L1ZS\",\"LglNm\":\"SUBH KIRANA STORES\",\"TrdNm\":\"SUBH KIRANA STORES\",\"Pos\":\"10\",\"Addr1\":\"REPURA BAZAR, VIA SARAIYA, Muzaffarpur, Bihar, 843107\",\"Addr2\":\"\",\"Loc\":\"MUZAFFARPUR\",\"Pin\":843107,\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"ValDtls\":{\"AssVal\":9850,\"IgstVal\":0,\"CgstVal\":246.26,\"SgstVal\":246.26,\"StCesVal\":0,\"Discount\":0,\"OthChrg\":0,\"RndOffAmt\":0},\"ItemList\":[{\"SlNo\":\"1\",\"PrdDesc\":\"ATTA 30/10KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26},{\"SlNo\":\"2\",\"PrdDesc\":\"ATTA 30/5KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26}]}";
				  
				  //oldSerial="{\"supplyType\":\"O\",\"subSupplyType\":\"1\",\"subSupplyDesc\":null,\"docType\":\"INV\",\"docNo\":\"GSI-2324-0004425\",\"docDate\":\"09/12/2023\",\"fromGstin\":\"34AACCC1596Q002\",\"fromTrdName\":\"AAYOG AGRO PRIVATE LIMITED\",\"fromAddr1\":\"802 MAHUA ROAD BELKUNDA\",\"fromAddr2\":\"BHOJPATTI VAISHALI\",\"fromPlace\":\"HAJIPUR\",\"fromPincode\":\"605001\",\"actFromStateCode\":34,\"fromStateCode\":34,\"toGstin\":\"10AABCB2066P2ZT\",\"toTrdName\":\"BRITANNIA INDUSTRIES LTD. - HAJIPUR\",\"toAddr1\":\"EPIP HAJIPUR INDUSTRIAL AREA,\\nPLOT NUMBER-C3,C4 TO C11\\n844102\",\"toAddr2\":\"Not Required\",\"toPlace\":\"VAISHALI\",\"toPincode\":844102,\"actToStateCode\":\"10\",\"toStateCode\":\"10\",\"transactionType\":\"4\",\"dispatchFromGSTIN\":null,\"dispatchFromTradeName\":null,\"shipToGSTIN\":null,\"shipToTradeName\":null,\"otherValue\":0,\"totalValue\":42000,\"cgstValue\":0,\"sgstValue\":0,\"igstValue\":2100,\"cessValue\":0,\"cessNonAdvolValue\":0,\"totInvValue\":44100,\"transporterId\":null,\"transporterName\":\"SHIVAM ROAD LINES - TRANS FINI PRD\",\"transDocNo\":\"GSI-2324-0004425\",\"transMode\":\"1\",\"transDistance\":\"0\",\"transDocDate\":\"09/12/2023\",\"vehicleNo\":\"HR55N2344\",\"vehicleType\":\"R\",\"ItemList\":[{\"productName\":\"BESAN 30/10KG TAX\",\"productDesc\":\"BESAN 30/10KG TAX\",\"hsnCode\":\"11061000\",\"quantity\":3,\"qtyUnit\":\"QTL\",\"cgstRate\":0,\"sgstRate\":0,\"igstRate\":5,\"cessRate\":0,\"cessNonAdvol\":0,\"taxableAmount\":8700},{\"productName\":\"DALIA 30/200G TAX\",\"productDesc\":\"DALIA 30/200G TAX\",\"hsnCode\":\"11031110\",\"quantity\":3,\"qtyUnit\":\"QTL\",\"cgstRate\":0,\"sgstRate\":0,\"igstRate\":5,\"cessRate\":0,\"cessNonAdvol\":0,\"taxableAmount\":8400},{\"productName\":\"MAIDA 30/1KG TAX\",\"productDesc\":\"MAIDA 30/1KG TAX\",\"hsnCode\":\"11010000\",\"quantity\":3,\"qtyUnit\":\"QTL\",\"cgstRate\":0,\"sgstRate\":0,\"igstRate\":5,\"cessRate\":0,\"cessNonAdvol\":0,\"taxableAmount\":8100},{\"productName\":\"SUJI 30/500G TAX\",\"productDesc\":\"SUJI 30/500G TAX\",\"hsnCode\":\"11031110\",\"quantity\":3,\"qtyUnit\":\"QTL\",\"cgstRate\":0,\"sgstRate\":0,\"igstRate\":5,\"cessRate\":0,\"cessNonAdvol\":0,\"taxableAmount\":7800},{\"productName\":\"ATTA 30/10KG TAX\",\"productDesc\":\"ATTA 30/10KG TAX\",\"hsnCode\":\"11010000\",\"quantity\":3,\"qtyUnit\":\"QTL\",\"cgstRate\":0,\"sgstRate\":0,\"igstRate\":5,\"cessRate\":0,\"cessNonAdvol\":0,\"taxableAmount\":9000}]}"; 
				  String postJsonData = oldSerial;
				  con1.setDoOutput(true);
				  DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
				  wr.writeBytes(postJsonData);
				  wr.flush();
				  wr.close();
				  
				  int responseCode1 = con1.getResponseCode();
				  //System.out.println("nSending 'POST' request to URL : " + url1);
				  System.out.println("Post Data : " + postJsonData);
				  System.out.println("Response Code : " + responseCode1);
				  if(responseCode1==200)
				  {
					  BufferedReader in1 = new BufferedReader(
							  new InputStreamReader(con1.getInputStream()));
							  String output1="";
							  StringBuffer response1 = new StringBuffer();
							 
							  while ((output1 = in1.readLine()) != null) {
							   response1.append(output1);
							  }
							  in1.close();
							  
							  System.out.println("Response JSON: "+response1.toString());
							  
							  s=response1.toString();
							  obj = new JSONObject(s);
									 
							  System.out.println("Return json: "+s+"\n\n");
							  System.out.println("ewayBillNo: "+obj.get("ewayBillNo"));
							  System.out.println("ewayBillDate: "+obj.get("ewayBillDate"));
							  System.out.println("ewayValidDate: "+obj.get("validUpto"));
							  System.out.println("ewayAlert: "+obj.get("alert"));

							  String s_api_status="1";
							  //String displaymessage="";
							  //System.out.println("Data2: "+obj.get("Data").toString()+"\n\n");
							  /*if(obj.get("Data").toString().compareTo("null")==0)  
							  {
								
								  JSONArray jsonArray = obj.getJSONArray("ErrorDetails"); 
								  for (int i = 0; i < jsonArray.length(); i++) 
								  	{  
							            // store each object in JSONObject  
							            JSONObject explrObject = jsonArray.getJSONObject(i);  
							            // get field value from JSONObject using get() method  
							            System.out.println("check error msg:"+explrObject.get("ErrorMessage"));  
							            displaymessage+=explrObject.get("ErrorMessage")+",";
							        }      
								  System.out.println("check error msg11:"+displaymessage);
								  
								  if(displaymessage.substring(0,displaymessage.length()-1).compareTo("Duplicate IRN")==0)
								  {
									  System.out.println("Yes it is duplicated.");
								  }
								  
								  statusDTO.setStatus();
								  
								 
								  
							  }
							  else
							  {*/
								  //obj = new JSONObject(obj.get("Data").toString());
								  
								  //System.out.println("EwbNo: "+obj.get("EwbNo"));
								  //System.out.println("EwbDt: "+obj.get("EwbDt"));
								  
								  
								  String ewaybill=""+obj.get("ewayBillNo");
								  String ewaybilldate=""+obj.get("ewayBillDate");
								  String ewaybillvalidupto=""+obj.get("validUpto");
								  
								  String alert=""+obj.get("alert");
								  String[] splitText = alert.split("is ");
								  String distance=splitText[1].replaceAll("[, ]","");
								 
							 
								  //System.out.println("AckNo: "+obj.get("AckNo"));
								  //System.out.println("AckDt: "+obj.get("AckDt"));
								  //System.out.println("Irn: "+obj.get("Irn"));
								  //System.out.println("SignedInvoice: "+obj.get("SignedInvoice"));
								  //System.out.println("s_api_status status: "+s_api_status);
								  
								  String ackno="NA"; //einvoice no
								  String ackdate=""+ewaybilldate;
								 
								  String irn="NA";
								  String signedInvoice="NA";
								  
								  Sale_invoice_einvoice_gen einv =new Sale_invoice_einvoice_gen();
								  
								  LocalDateTime ldt = LocalDateTime.now();
								  String invid=sales_InvoiceRepository.findSalesInvoiceDtls(id).getInvoice_id();
								  
								  einv.setModified_type("INSERTED");
								  einv.setInserted_by(username);
								  einv.setInserted_on(ldt);
								  einv.setUpdated_by("NA");
								  einv.setUpdated_on(ldt);
								  einv.setDeleted_by("NA");
								  einv.setDeleted_on(ldt);
								  einv.setUsername(username);
								  einv.setCancel_on(ldt);
								  einv.setCancel_by("NA");
								  String inv_id=sales_InvoiceRepository.findSalesInvoiceDtls(id).getInvoice_id();
								  einv.setInvoice_id(inv_id);
								  einv.setF_api_status(Boolean.parseBoolean(f_api_status));
								  einv.setAuth_token(authtoken);
								  einv.setS_api_status(Boolean.parseBoolean(s_api_status));
								  einv.setAck_no(ackno);
								  einv.setAck_date(ackdate);
								  einv.setIrn_no(irn);
								  einv.setSighned_invoice(signedInvoice);
								  einv.setCancel_status(false);
								  einv.setCancel_message("Not Cancel");
								  
								  einv.setEway_bill_no(ewaybill);
								  einv.setEway_bill_date(ewaybilldate);
								  einv.setEway_valid_upto(ewaybillvalidupto);
								  einv.setDistance(distance);
								  einv.setWaybill_create_on(ldt);
								  einv.setWaybill_create_by(username);
								  
								  sales_InvoiceRepository.updateEwaybillWOInv(id,ewaybill);
								  sale_invoice_einvoice_genRepository.save(einv);
								  
								//path where we want to get QR Code  
								  /*String path = "G:\\Aayog_Einvoice\\"+inv_id+".png";  
								  //Encoding charset to be used  
								  String charset = "UTF-8";  
								  Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
								  //generates QR code with Low level(L) error correction capability  
								  hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
								  //invoking the user-defined method that creates the QR code  
								  new createQRImage(signedInvoice, path, charset, hashMap, 2000, 2000);//increase or decrease height and width accodingly   
								  //prints if the QR code is generated   
								  System.out.println("QR Code created successfully.");*/
								  
								  statusDTO.setStatus("Yes_E-Waybill Without Invoice is Created Successfully. E-Waybill is: "+ewaybill);
								  
								 // } 
				  }
				  else {
					  statusDTO.setStatus("E-way Bill Already Created...");  
				  }
				  
				 			  
			 }
			 catch(Exception e)
			 {
				System.out.println("Exception: "+e) ;
				statusDTO.setStatus(""+e);
			 }
			 
		    return statusDTO;
	    }
	 
	
	 
	/************ End of Sales Invoice *****************/
	
	@Autowired
	Party_bill_paymentService party_bill_paymentService;
	
	@PostMapping("/createPartyBillPayment")
	public Party_bill_payment createPartyBillPayment(@Valid @RequestBody Party_bill_payment pBill_payment) 
	{
		return party_bill_paymentService.save(pBill_payment);
	}
	
	@GetMapping("/getPartyBillPayment")
    public ResponseEntity<List<Party_bill_payment>> getPartyBillPayment()
    {
    	List<Party_bill_payment> list = party_bill_paymentService.getPartyBillPayment();
	    return new ResponseEntity<List<Party_bill_payment>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/getPartyOutstanding")
    public ResponseEntity<List<Partyoutstanding_invoice>> getPartyOutstanding(@RequestParam(defaultValue = "") String party,@RequestParam(defaultValue = "") String bunit)
    {
    	List<Partyoutstanding_invoice> list = party_bill_paymentService.getPartyOutstanding(party,bunit);
    	
	    return new ResponseEntity<List<Partyoutstanding_invoice>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/getPartyBusinessUnit")
    public ResponseEntity<List<Partyoutstanding_invoice>> getPartyBusinessUnit(@RequestParam(defaultValue = "") String party)
    {
    	List<Partyoutstanding_invoice> list = party_bill_paymentService.getPartyBusinessUnit(party);
    	
	    return new ResponseEntity<List<Partyoutstanding_invoice>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/getInvTaxSum")
    public ResponseEntity<List<Map<String,Object>>> getInvTaxSum(@RequestParam(defaultValue = "") String invid)
    {
		List<Map<String,Object>> list = sales_InvoiceService.getInvTaxSum(invid);
    	
	    return new ResponseEntity<List<Map<String,Object>>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/getInvHsnSum")
    public ResponseEntity<List<Item_groupwise_hsnsumm>> getInvHsnSum(@RequestParam(defaultValue = "") String invid)
    {
    	List<Item_groupwise_hsnsumm> list = sales_InvoiceService.getInvHsnSum(invid);
    	
	    return new ResponseEntity<List<Item_groupwise_hsnsumm>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	/******************* Sales Return Start **********************/
	@Autowired
	Sales_return_noteService sales_return_noteService;
	
	@GetMapping("/getSRSequenceId/{fin_year}/{inv_type}")
	public SalesSequenceIdDTO getSRSequenceId(@PathVariable(value = "fin_year") String fin_year,@PathVariable(value = "inv_type") String inv_type)
	{
		return sales_return_noteService.getSRSequenceId(fin_year,inv_type);
	}
	
	@GetMapping("/getSalesRtnNote")
    public ResponseEntity<List<Sales_return_noteDTO>> getSalesRtnNote(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String party,@RequestParam(defaultValue = "") String invdate,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear,@RequestParam(defaultValue = "") String invoicetype)
    {
		//SELECT * FROM wm_loading_advice WHERE modified_type = 'INSERTED' AND advice_date='2022-11-15'SELECT * FROM wm_loading_advice WHERE modified_type = 'INSERTED' AND advice_date='2022-11-15'("invoicetype:"+invoicetype);
    	List<Sales_return_noteDTO> list = sales_return_noteService.getSalesRtnNote(bunit,party,invdate,company,finyear,invoicetype);
    	
	    return new ResponseEntity<List<Sales_return_noteDTO>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	
	
	@GetMapping(value = "/getSalesRtnNoteJw/{date}/{bunit}/{party_id}")
    public List<Map<String,Object>> getSalesRtnNoteJw(@PathVariable(value = "date") String date,@PathVariable(value = "bunit") String bunit,@PathVariable(value = "party_id") String party_id)
    {
	    return sales_return_noteService.getSalesRtnNoteJw(date,bunit,party_id);
    }
	
	@GetMapping(value = "/getsalereturnjobworkprice/{salereturn}")
	public List<Map<String,Object>> getsalereturnjobworkprice(@PathVariable(value = "salereturn") String salereturn)
    {
	    return sales_return_noteService.getsalereturnjobworkprice(salereturn);
    }
	
	
	
	@GetMapping(value = "/getSalesRtnNoteJwdetails/{salereturn}")
	public List<Map<String,Object>> getSalesRtnNoteJwdetails(@PathVariable(value = "salereturn") String salereturn)
    {
	    return sales_return_noteService.getSalesRtnNoteJwdetails(salereturn);
    }
	
	@GetMapping(value = "/getSalesRtnNoteUpdate/{id}")
    public List<Sales_return_noteDTO> getSalesRtnNoteUpdate(@PathVariable(value = "id") Long id)
    {
	    return sales_return_noteService.getSalesRtnNoteUpdate(id);
    }
	
	
	@GetMapping("/getSalesReturnNoteDtls/{salesreturnnoteid}")
	public Sales_return_noteDTO getSalesReturnNoteDtls(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getSalesReturnNoteDtls(salesreturnnoteid);
	}
	
	@PostMapping("/createSalesReturnNote")
	public Sales_return_note createSalesReturnNote(@Valid @RequestBody Sales_return_note sales_return_note) {
		return sales_return_noteService.save(sales_return_note);
	}
  
    @GetMapping("/getSalesReturnNote/{company}/{currentdate}")
    public List<Map<String, Object>> getSalesReturnNote(@PathVariable(value = "company") String company,
    													@PathVariable(value = "currentdate") String currentdate)
    {
	    return sales_return_noteService.getSalesReturnNote(company,currentdate); 
    }
  
    @GetMapping("/retriveSalesReturnNote/{id}")
	public ResponseEntity<Sales_return_note> retriveSalesReturnNote(@PathVariable(value = "id") Long id) {
		Sales_return_note op = sales_return_noteService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	

	
	@GetMapping("/getSalesReturnNoteBD/{salesreturnnoteid}")
	public List<Sales_return_note_broker_dtlsDTO> getSalesReturnNoteBD(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getSalesReturnNoteBD(salesreturnnoteid);
	}
	
	@GetMapping("/getSalesReturnNoteD/{salesreturnnoteid}")
	public List<Sales_return_note_docsDTO> getSalesReturnNoteD(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getSalesReturnNoteD(salesreturnnoteid);
	}
	
	@GetMapping("/getSalesReturnNoteID/{salesreturnnoteid}")
	public List<Sales_return_note_item_dtlsDTO> getSalesReturnNoteID(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getSalesReturnNoteID(salesreturnnoteid);
	}
	
	@GetMapping("/getSalesReturnNoteIDjobwork/{salesreturnnoteid}")
	public List<Map<String,Object>> getSalesReturnNoteIDjobwork(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getSalesReturnNoteIDjobwork(salesreturnnoteid);
	}
	
	
	@GetMapping("/getMultipleSalesReturnNoteitemlist/{salesreturnnoteid}")
	public List<Sales_return_note_item_dtlsDTO> getMultipleSalesReturnNoteitemlist(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getMultipleSalesReturnNoteitemlist(salesreturnnoteid);
	}
	
	@GetMapping("/getMultipleSalesReturnNoteitemlistUpdate/{id}")
	public List<Sales_return_note_item_dtlsDTO> getMultipleSalesReturnNoteitemlistUpdate(@PathVariable(value = "id") Long id)
	{
		return sales_return_noteService.getMultipleSalesReturnNoteitemlistUpdate(id);
	}
	
	@GetMapping("/getMultipleReturnApprovalitemlist/{returnapprovalid}")
	public List<Return_approval_item_dtlsDTO> getMultipleReturnApprovalitemlist(@PathVariable(value = "returnapprovalid") String returnapprovalid)
	{
		return return_approval_noteService.getMultipleReturnApprovalitemlist(returnapprovalid);
	}
	
	@GetMapping("/retriveReturnAppJobwork/{salesreturnid}")
	public List<Map<String,Object>> retriveReturnAppJobwork(@PathVariable(value = "salesreturnid") String salesreturnid)
	{
		return return_approval_noteService.retriveReturnAppJobwork(salesreturnid);
	}
	
	@GetMapping("/retriveReturnAppJobworkPrice/{salesreturnid}")
	public List<Map<String,Object>> retriveReturnAppJobworkPrice(@PathVariable(value = "salesreturnid") String salesreturnid)
	{
		return return_approval_noteService.retriveReturnAppJobworkPrice(salesreturnid);
	}
	@GetMapping("/getSalesAllTransactionData/{returnbasedon}/{customer}/{returndate}/{bunit}")
	public List<Map<String,Object>> getSalesAllTransactionData(@PathVariable(value = "returnbasedon") String returnbasedon,
			@PathVariable(value = "customer") String customer,@PathVariable(value = "returndate") String returndate,
			@PathVariable(value = "bunit") String bunit)
	{
		return return_approval_noteService.getSalesAllTransactionData(returnbasedon,customer,returndate,bunit);
	}
	
	@GetMapping("/getMultipleReturnApprovalitemlistUpdate/{id}")
	public List<Return_approval_item_dtlsDTO> getMultipleReturnApprovalitemlistUpdate(@PathVariable(value = "id") Long id)
	{
		return return_approval_noteService.getMultipleReturnApprovalitemlistUpdate(id);
	}
	
	@GetMapping("/getSalesReturnNotePD/{salesreturnnoteid}")
	public List<Sales_return_note_party_dtlsDTO> getSalesReturnNotePD(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getSalesReturnNotePD(salesreturnnoteid);
	}
	
	@GetMapping("/getSalesReturnNoteSD/{salesreturnnoteid}")
	public Sales_return_note_shipment_dtlsDTO getSalesReturnNoteSD(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getSalesReturnNoteSD(salesreturnnoteid);
	}
	
	@GetMapping("/getSalesReturnNoteTI/{salesreturnnoteid}")
	public Sales_return_note_trans_infoDTO getSalesReturnNoteTI(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getSalesReturnNoteTI(salesreturnnoteid);
	}
	
	@GetMapping("/getSalesRtnNoteTransInfo/{salesreturnnoteid}")
	public List<Invoice_Sales_return_note_trans_infoDTO> getSalesRtnNoteTransInfo(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getSalesRtnNoteTransInfo(salesreturnnoteid);
	}
	
	@GetMapping("/getMultipleSalesRtnNoteTransInfo/{salesreturnnoteid}")
	public List<Invoice_Sales_return_note_trans_infoDTO> getMultipleSalesRtnNoteTransInfo(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getMultipleSalesRtnNoteTransInfo(salesreturnnoteid);
	}
	
	@GetMapping("/getSalesReturnNoteWD/{salesreturnnoteid}")
	public Sales_return_note_weight_dtlDTO getSalesReturnNoteWD(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid)
	{
		return sales_return_noteService.getSalesReturnNoteWD(salesreturnnoteid);
	}
	
    @PutMapping("/updateSalesReturnNote/{id}") 
    public ResponseEntity<Sales_return_note> updateSalesReturnNote(@PathVariable(value="id") long id,@Valid @RequestBody Sales_return_note sales_return_note)
    {
    	Sales_return_note op=sales_return_noteService.update(sales_return_note,id);
	    return ResponseEntity.ok().body(op); 
    }
    
    @GetMapping("/getcreditnotepopupsalesreturn/{id}")
  	public List<Sales_return_note> getcreditnotepopupsalesreturn(@PathVariable(value = "id") Long id)
  	{
  		return sales_return_noteService.getcreditnotepopupsalesreturn(id);
  	}
  	
  	@GetMapping("/getsales_creditnote/{salesreturnid}/{id}")
   	public List<Sales_return_note_item_dtls> getsales_creditnote(@PathVariable(value = "salesreturnid") String salesreturnid,@PathVariable(value = "id") Long id)
   	{
   		return sales_return_noteService.getsales_creditnote(salesreturnid,id);
   	}
   	
   	@GetMapping(value = "/retriveSalesReturnNoteJobwork/{salesreturnnoteid}")
	 public List<Map<String, Object>> retriveSalesReturnNoteJobwork(@PathVariable(value = "salesreturnnoteid") String salesreturnnoteid) 
		{
			  return sales_return_noteService.retriveSalesReturnNoteJobwork(salesreturnnoteid);
		}
    /******************* Sales Return End **********************/
	
	
	/******************* Sales credit note Start **********************/
	@Autowired
	Sales_credit_noteService sales_credit_noteService;
	
	@GetMapping("/getCNSequenceId/{fin_year}/{cntype}/{cnotetype}")
	public SalesSequenceIdDTO getCNSequenceId(@PathVariable(value = "fin_year") String fin_year,@PathVariable(value = "cntype") String cntype,@PathVariable(value = "cnotetype") String cnotetype)
	{
		return sales_credit_noteService.getCNSequenceId(fin_year,cntype,cnotetype);
	}
	
	@PostMapping("/createSalesCreditNote")
	public Sales_credit_note createSalesCreditNote(@Valid @RequestBody Sales_credit_note sales_credit_note) {
		return sales_credit_noteService.save(sales_credit_note);
	}
  
    @GetMapping("/getSalesCreditNote")
    public List<Sales_credit_note> getSalesCreditNote()
    {
	    return sales_credit_noteService.findAll(); 
    }
    
    @GetMapping(value = "/getSalesCreditNoteFast/{compid}")
	 public List<Map<String, Object>> getSalesCreditNoteFast(@PathVariable(value = "compid") String compid) 
	{
		  return sales_credit_noteService.getSalesCreditNoteFast(compid);
	}
  
    @GetMapping("/retriveSalesCreditNote/{id}")
	public ResponseEntity<Sales_credit_note> retriveSalesCreditNote(@PathVariable(value = "id") Long id) {
		Sales_credit_note op = sales_credit_noteService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	 @GetMapping("/retriveSalesCreditNoteposting/{id}")
		public Sales_credit_note retriveSalesCreditNoteposting(@PathVariable(value = "id") Long id) {
			
		 return sales_credit_noteService.retriveSalesCreditNoteposting(id);
		}
	
	@GetMapping("/getSalesCreditNoteBPD/{creditnoteid}")
	public Sales_credit_note_bp_dtlsDTO getSalesCreditNoteBPD(@PathVariable(value = "creditnoteid") String creditnoteid)
	{
		return sales_credit_noteService.getSalesCreditNoteBPD(creditnoteid);
	}
	
	@GetMapping("/getSalesCreditNoteBD/{creditnoteid}")
	public List<Sales_credit_note_broker_dtlsDTO> getSalesCreditNoteBD(@PathVariable(value = "creditnoteid") String creditnoteid)
	{
		return sales_credit_noteService.getSalesCreditNoteBD(creditnoteid);
	}
	
	@GetMapping("/getSalesCreditNoteD/{creditnoteid}")
	public List<Sales_credit_note_docsDTO> getSalesCreditNoteD(@PathVariable(value = "creditnoteid") String creditnoteid)
	{
		return sales_credit_noteService.getSalesCreditNoteD(creditnoteid);
	}
	
	@GetMapping("/getSalesCreditNoteID/{creditnoteid}")
	public List<Sales_credit_note_item_dtlsDTO> getSalesCreditNoteID(@PathVariable(value = "creditnoteid") String creditnoteid)
	{
		return sales_credit_noteService.getSalesCreditNoteID(creditnoteid);
	}
	
	@GetMapping("/getSalesCreditNoteIDposting/{creditnoteid}")
	public List<Sales_credit_note_item_dtlsDTO> getSalesCreditNoteIDposting(@PathVariable(value = "creditnoteid") String creditnoteid)
	{
		return sales_credit_noteService.getSalesCreditNoteIDposting(creditnoteid);
	}
	
	@GetMapping("/getSalesCreditNotePD/{creditnoteid}")
	public Sales_credit_note_payment_dtlsDTO getSalesCreditNotePD(@PathVariable(value = "creditnoteid") String creditnoteid)
	{
		return sales_credit_noteService.getSalesCreditNotePD(creditnoteid);
	}
	
	@GetMapping(value = "/getSalesCreditNoteJobwork/{creditnoteid}")
	 public List<Map<String, Object>> getSalesCreditNoteJobwork(@PathVariable(value = "creditnoteid") String creditnoteid) 
	{
		  return sales_credit_noteService.getSalesCreditNoteJobwork(creditnoteid);
	}
	
	@GetMapping(value = "/getSalesCreditNoteJobworkPrice/{creditnoteid}")
	 public List<Map<String, Object>> getSalesCreditNoteJobworkPrice(@PathVariable(value = "creditnoteid") String creditnoteid) 
	{
		  return sales_credit_noteService.getSalesCreditNoteJobworkPrice(creditnoteid);
	}
	
	@GetMapping("/getcreditnotetaxcodes/{creditnoteid}")
	public List<Credit_item_groupwise_taxsumm> getcreditnotetaxcodes(@PathVariable(value = "creditnoteid") String creditnoteid)
	{
		return sales_credit_noteService.getcreditnotetaxcodes(creditnoteid);
	}
	
	
	@GetMapping("/getSalesCreditNoteSD/{creditnoteid}")
	public Sales_credit_note_shipment_dtlsDTO getSalesCreditNoteSD(@PathVariable(value = "creditnoteid") String creditnoteid)
	{
		return sales_credit_noteService.getSalesCreditNoteSD(creditnoteid);
	}
	
	@GetMapping("/getSalesCreditNoteTI/{creditnoteid}")
	public Sales_credit_note_tax_infoDTO getSalesCreditNoteTI(@PathVariable(value = "creditnoteid") String creditnoteid)
	{
		return sales_credit_noteService.getSalesCreditNoteTI(creditnoteid);
	}
	
	@GetMapping("/getSalesCreditNoteTD/{creditnoteid}")
	public List<Sales_credit_note_trans_dtlsDTO> getSalesCreditNoteTD(@PathVariable(value = "creditnoteid") String creditnoteid)
	{
		return sales_credit_noteService.getSalesCreditNoteTD(creditnoteid);
	}
	
    @PutMapping("/updateSalesCreditNote/{id}") 
    public ResponseEntity<Sales_credit_note> updateSalesCreditNote(@PathVariable(value="id") long id,@Valid @RequestBody Sales_credit_note Sales_credit_note)
    {
    	Sales_credit_note op=sales_credit_noteService.update(Sales_credit_note,id);
	    return ResponseEntity.ok().body(op); 
    }
 
    @GetMapping(value = "/geteinvoicestatus_creditnote/{id}/{invoiceno}")
	 public List<Map<String, Object>> geteinvoicestatus_creditnote(@PathVariable(value = "id") long id,@PathVariable(value = "invoiceno") String invoiceno) 
	{
		  return sales_credit_noteService.geteinvoicestatus_creditnote(id,invoiceno);
	}
    
    @GetMapping("/creditnoteeinvoicedetails/{creditnoteid}")
	public Map<String,Object> creditnoteeinvoicedetails(@PathVariable(value= "creditnoteid") String creditnoteid)
	{
		return sales_credit_noteService.creditnoteeinvoicedetails(creditnoteid);
	}
    
    @GetMapping(value = "/searchSalesCreditNote")
	public List<Map<String,Object>> searchSalesCreditNote(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String party1,@RequestParam(defaultValue = "") String finyear)
	 {
		return sales_credit_noteService.searchSalesCreditNote(orderno,fromdate,todate,party1,finyear);
	 }
    
    @Autowired
    Sales_credit_noteRepository sales_credit_noteRepository;
    
	@Autowired
	Sale_credit_note_einvoice_genRepository sale_credit_note_einvoice_genRepository;
	
		@Transactional
	 	@PutMapping("/creditNoteEinvoiceGeneration/{id}/{username}") 
	    public StatusDTO creditNoteEinvoiceGeneration(@PathVariable(value="id") long id,@PathVariable(value="username") String username,@Valid @RequestBody StatusDTO statusDTO)
	    {
	    	System.out.println("message"+statusDTO.getStatus());
	    	//System.out.println("username"+username);
	    	String USER_AGENT = "Mozilla/5.0";
			
			String oldSerial = statusDTO.getStatus();
			
			
			try
			 {
				//1ST API CALLING START
				
				// String urlString =  "https://gstsandbox.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&user_name=TaxProEnvPON&eInvPwd=abc34*";
				 String urlString =  "https://einvapi.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&user_name=API_aayog&eInvPwd=Aayog@2022";
				
				 URL url = new URL(urlString);
				 HttpURLConnection con = (HttpURLConnection) url.openConnection();
				 con.setRequestMethod("GET");
				 con.setRequestProperty("User-Agent", USER_AGENT);
				 
				  int responseCode = con.getResponseCode();
				 // System.out.println("Sending get request : "+ url);
				//  System.out.println("Response code : "+ responseCode);
				  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				  String output;
				  StringBuffer response = new StringBuffer();
				 
				  while ((output = in.readLine()) != null) {

				   response.append(output);
				  }
				 
				  in.close();
				  String s=response.toString();
				  JSONObject obj = new JSONObject(s);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("Status"));
				  String f_api_status=""+obj.get("Status");
				  
				  System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
				  obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("Auth Token: "+obj.get("AuthToken"));
				  
				  String authtoken=""+obj.get("AuthToken");
				 
				  System.out.println("f_api_status status: "+f_api_status);
				  
				  // 1st Api Calling End....
				  
				  
				 // String url1 = "https://gstsandbox.charteredinfo.com/eicore/dec/v1.03/Invoice?aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&AuthToken="+authtoken+"&user_name=TaxProEnvPON&QRCodeSize=200";
				  String url1 = "https://einvapi.charteredinfo.com/eicore/dec/v1.03/Invoice?aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&AuthToken="+authtoken+"&user_name=API_aayog&QRCodeSize=200";
				  
				  URL objnew = new URL(url1);
				  HttpURLConnection con1 = (HttpURLConnection) objnew.openConnection();
				  con1.setRequestMethod("POST");
				  con1.setRequestProperty("User-Agent", USER_AGENT);
				  con1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				  con1.setRequestProperty("Content-Type","application/json");
				  
				  //local JSON:{"Version":"1.1","ShipDtls":null,"TranDtls":{"TaxSch":"GST","SupTyp":"B2B","IgstOnIntra":"N","RegRev":null,"EcmGstin":null},"DocDtls":{"Typ":"CRN","No":"CNG-2324-0000122","Dt":"11/01/2024"},"SellerDtls":{"Gstin":"34AACCC1596Q002","LglNm":"NIC company pvt ltd","TrdNm":"NIC Industries","Addr1":"5th block, kuvempu layout","Addr2":"kuvempu layout","Loc":"GANDHINAGAR","Pin":"605001","Stcd":"34","Ph":"9000000000","Em":"abc@gmail.com"},"BuyerDtls":{"Gstin":"10DWTPK3976F1Z8","LglNm":"M/S NAKSH ENTERPRISES","TrdNm":"M/S NAKSH ENTERPRISES","Pos":"10","Addr1":"WARD 16, KOAT BAZAR, EAST OF AASHIRVAD HOTEL, KOAT BAZAR, SITAMARHI, Sitamarhi, Bihar, 843302","Addr2":"Not Required","Loc":"SITAMARHI","Pin":843302,"Stcd":"10","Ph":null,"Em":null},"ValDtls":{"AssVal":36394.37,"IgstVal":1819.72,"CgstVal":0,"SgstVal":0,"StCesVal":0,"Discount":0,"OthChrg":0,"RndOffAmt":-0.09,"TotInvVal":38214},"ItemList":[{"SlNo":"1","PrdDesc":"ATTA 30/1KG TAX","IsServc":"N","HsnCd":"11010000","Qty":2,"Unit":"PCS","UnitPrice":971.43,"TotAmt":1942.86,"Discount":0,"PreTaxVal":0,"AssAmt":1942.86,"GstRt":5,"IgstAmt":97.14,"CgstAmt":0,"SgstAmt":0,"CesRt":0,"CesAmt":0,"CesNonAdvlAmt":0,"StateCesRt":0,"StateCesAmt":0,"StateCesNonAdvlAmt":0,"OthChrg":0,"TotItemVal":2040},{"SlNo":"2","PrdDesc":"ATTA 30/5KG TAX","IsServc":"N","HsnCd":"11010000","Qty":9,"Unit":"PCS","UnitPrice":954.29,"TotAmt":8588.61,"Discount":0,"PreTaxVal":0,"AssAmt":8588.61,"GstRt":5,"IgstAmt":429.44,"CgstAmt":0,"SgstAmt":0,"CesRt":0,"CesAmt":0,"CesNonAdvlAmt":0,"StateCesRt":0,"StateCesAmt":0,"StateCesNonAdvlAmt":0,"OthChrg":0,"TotItemVal":9018.05},{"SlNo":"3","PrdDesc":"SUJI 30/500G TAX","IsServc":"N","HsnCd":"11031110","Qty":8,"Unit":"PCS","UnitPrice":1085.71,"TotAmt":8685.68,"Discount":0,"PreTaxVal":0,"AssAmt":8685.68,"GstRt":5,"IgstAmt":434.28,"CgstAmt":0,"SgstAmt":0,"CesRt":0,"CesAmt":0,"CesNonAdvlAmt":0,"StateCesRt":0,"StateCesAmt":0,"StateCesNonAdvlAmt":0,"OthChrg":0,"TotItemVal":9119.96},{"SlNo":"4","PrdDesc":"ATTA 30/10KG TAX","IsServc":"N","HsnCd":"11010000","Qty":18,"Unit":"PCS","UnitPrice":954.29,"TotAmt":17177.22,"Discount":0,"PreTaxVal":0,"AssAmt":17177.22,"GstRt":5,"IgstAmt":858.86,"CgstAmt":0,"SgstAmt":0,"CesRt":0,"CesAmt":0,"CesNonAdvlAmt":0,"StateCesRt":0,"StateCesAmt":0,"StateCesNonAdvlAmt":0,"OthChrg":0,"TotItemVal":18036.08}]}
				  String postJsonData = oldSerial;
				  con1.setDoOutput(true);
				  DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
				  wr.writeBytes(postJsonData);
				  wr.flush();
				  wr.close();
				  
				  int responseCode1 = con1.getResponseCode();
				  System.out.println("nSending 'POST' request to URL : " + url1);
				  System.out.println("Post Data : " + postJsonData);
				  System.out.println("Response Code : " + responseCode1);
				 
				  BufferedReader in1 = new BufferedReader(
				  new InputStreamReader(con1.getInputStream()));
				  String output1;
				  StringBuffer response1 = new StringBuffer();
				 
				  while ((output1 = in1.readLine()) != null) {
				   response1.append(output1);
				  }
				  in1.close();
				  
				  System.out.println("Response JSON: "+response1.toString());
				  
				  s=response1.toString();
				  obj = new JSONObject(s);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("Status"));

				  String s_api_status=""+obj.get("Status");
				  String displaymessage="";
				  System.out.println("Data2: "+obj.get("Data").toString()+"\n\n");
				  if(obj.get("Data").toString().compareTo("null")==0)  
				  {
					 
					
					  JSONArray jsonArray = obj.getJSONArray("ErrorDetails"); 
					  for (int i = 0; i < jsonArray.length(); i++) 
					  	{  
			              
				            // store each object in JSONObject  
				            JSONObject explrObject = jsonArray.getJSONObject(i);  
				              
				            // get field value from JSONObject using get() method  
				            System.out.println("check error msg:"+explrObject.get("ErrorMessage"));  
				            displaymessage+=explrObject.get("ErrorMessage")+",";
				        }      
					  System.out.println("check error msg11:"+displaymessage);
					  
					  
					  if(displaymessage.substring(0,displaymessage.length()-1).compareTo("Duplicate IRN")==0)
					  {
						  System.out.println("Yes it is duplicated."+ obj.getJSONArray("InfoDtls"));
						  
						  JSONArray jsonArray1 = obj.getJSONArray("InfoDtls"); 
						  
						  for (int i = 0; i < jsonArray1.length(); i++) 
						  	{  
				              
					             // store each object in JSONObject  
					             JSONObject explrObject = jsonArray1.getJSONObject(i);  
					              
					             // get field value from JSONObject using get() method  
					             System.out.println("check Desc msg:"+explrObject.get("Desc"));  
					            
					             obj = new JSONObject(""+explrObject.get("Desc"));
					             System.out.println("check IRN msg:"+obj.get("Irn")); 
					             String Irn=""+obj.get("Irn");
					            
					             
					             // urlString =  "https://gstsandbox.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&user_name=TaxProEnvPON&eInvPwd=abc34*";
					              urlString =  "https://einvapi.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&user_name=API_aayog&eInvPwd=Aayog@2022";
								
								  url = new URL(urlString);
								  con = (HttpURLConnection) url.openConnection();
								  con.setRequestMethod("GET");
								  con.setRequestProperty("User-Agent", USER_AGENT);
								 
								  responseCode = con.getResponseCode();
								 // System.out.println("Sending get request : "+ url);
								//  System.out.println("Response code : "+ responseCode);
								  in = new BufferedReader(new InputStreamReader(con.getInputStream()));
								  String output0;
								  response = new StringBuffer();
								 
								  while ((output0 = in.readLine()) != null) {

								   response.append(output0);
								  }
								 
								  in.close();
								  s=response.toString();
								  obj = new JSONObject(s);
										 
								  System.out.println("Return json: "+s+"\n\n");
								  System.out.println("Status: "+obj.get("Status"));
								  f_api_status=""+obj.get("Status");
								  
								  System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
								  obj = new JSONObject(obj.get("Data").toString());
								  System.out.println("Auth Token: "+obj.get("AuthToken"));
								  
								  authtoken=""+obj.get("AuthToken");
								  
								  //url1 = "https://gstsandbox.charteredinfo.com/eicore/dec/v1.03/Invoice/irn/"+Irn+"?aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&AuthToken="+authtoken+"&user_name=TaxProEnvPON";
								  url1 = "https://einvapi.charteredinfo.com/eicore/dec/v1.03/Invoice/irn/"+Irn+"?aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&AuthToken="+authtoken+"&user_name=API_aayog";
								  
								  url = new URL(url1);
								  con = (HttpURLConnection) url.openConnection();
								  con.setRequestMethod("GET");
								  con.setRequestProperty("User-Agent", USER_AGENT);
									 
								  responseCode = con.getResponseCode();
								  // System.out.println("Sending get request : "+ url);
								  // System.out.println("Response code : "+ responseCode);
								  in = new BufferedReader(new InputStreamReader(con.getInputStream()));
								  String output01;
								  response = new StringBuffer();
									 
								  while ((output01 = in.readLine()) != null) 
								  {
									  response.append(output01);
								  }
									 
								  in.close();
								  s=response.toString();
								  obj = new JSONObject(s);
											 
								  System.out.println("Return json: "+s+"\n\n");
								  String s1=obj.get("Data").toString();
								
								  System.out.println("Data2: "+obj.get("Data").toString()+"\n\n");
								  obj = new JSONObject(s1);
								  System.out.println("AckNo: "+obj.get("AckNo")+"Irn: "+"Irn: "+obj.get("Irn")+"SignedInvoice: "+obj.get("SignedInvoice")+"AckDt: "+obj.get("AckDt"));
								
					        }  
						  
						  String irn=""+obj.get("Irn");
						  String SignedQRCode=""+obj.get("SignedQRCode");
						  String inv_id=sales_credit_noteRepository.getCreditNoteDetails(id).getCreditnoteid();
						  String ackno=""+obj.get("AckNo"); //einvoice no
						  String ackdate=""+obj.get("AckDt");
						  
						  int checkeing_inv_id=sale_credit_note_einvoice_genRepository.checkCreditNoteIrn(irn);
						  System.out.println("checkeing_inv_id"+checkeing_inv_id);
						  if(checkeing_inv_id==1 )
						  {
							  Sale_credit_note_einvoice_gen einv =new Sale_credit_note_einvoice_gen();
							  LocalDateTime ldt = LocalDateTime.now();
							  einv.setModified_type("INSERTED");
							  einv.setInserted_by(username);
							  einv.setInserted_on(ldt);
							  einv.setUpdated_by("NA");
							  einv.setUpdated_on(ldt);
							  einv.setDeleted_by("NA");
							  einv.setDeleted_on(ldt);
							  einv.setUsername(username);
							  einv.setCancel_on(ldt);
							  einv.setCancel_by("NA");
							  einv.setCreditnoteid(inv_id);
							  einv.setF_api_status(Boolean.parseBoolean(f_api_status));
							  einv.setAuth_token(authtoken);
							  einv.setS_api_status(Boolean.parseBoolean(s_api_status));
							  einv.setAck_no(ackno);
							  einv.setAck_date(ackdate);
							  einv.setIrn_no(irn);
							  einv.setCancel_status(false);
							  einv.setSighned_invoice(SignedQRCode);
							  einv.setCancel_message("Not Cancel");
							  
							  sales_credit_noteRepository.updateEinvoice(ackno,id);
							  
							  sale_credit_note_einvoice_genRepository.save(einv); 
							  
							  statusDTO.setStatus(displaymessage.substring(0,displaymessage.length()-1));
						  }
						  else
						  {
							  statusDTO.setStatus("Duplicate IRN No And Already in Database.");
						  }
					  }
				  }
				  else
				  {
				  obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("AckNo: "+obj.get("AckNo"));
				  System.out.println("AckDt: "+obj.get("AckDt"));
				  System.out.println("Irn: "+obj.get("Irn"));
				  System.out.println("SignedInvoice: "+obj.get("SignedInvoice"));
				  System.out.println("s_api_status status: "+s_api_status);
				  
				  String ackno=""+obj.get("AckNo"); //einvoice no
				  String ackdate=""+obj.get("AckDt");
				 
				  String irn=""+obj.get("Irn");
				  String SignedQRCode=""+obj.get("SignedQRCode");
				  
				  Sale_credit_note_einvoice_gen einv =new Sale_credit_note_einvoice_gen();
				  LocalDateTime ldt = LocalDateTime.now();
				  einv.setModified_type("INSERTED");
				  einv.setInserted_by(username);
				  einv.setInserted_on(ldt);
				  einv.setUpdated_by("NA");
				  einv.setUpdated_on(ldt);
				  einv.setDeleted_by("NA");
				  einv.setDeleted_on(ldt);
				  einv.setUsername(username);
				  einv.setCancel_on(ldt);
				  einv.setCancel_by("NA");
				  String inv_id=sales_credit_noteRepository.getCreditNoteDetails(id).getCreditnoteid();
				  einv.setCreditnoteid(inv_id);
				  einv.setF_api_status(Boolean.parseBoolean(f_api_status));
				  einv.setAuth_token(authtoken);
				  einv.setS_api_status(Boolean.parseBoolean(s_api_status));
				  einv.setAck_no(ackno);
				  einv.setAck_date(ackdate);
				  einv.setIrn_no(irn);
				  einv.setSighned_invoice(SignedQRCode);
				  einv.setCancel_status(false);
				  einv.setCancel_message("Not Cancel");
				  
				  sales_credit_noteRepository.updateEinvoice(ackno,id);
				  
				  sale_credit_note_einvoice_genRepository.save(einv); 

				//path where we want to get QR Code  
				 // String path = "D:\\AayogAgroDocuments\\Aayog_Creditnote_Einvoice\\"+inv_id+".png"; //Local
				  String path =  "/usr/documents/aayogcreditnoteeinvoice/"+inv_id+".png";	//online 
				  //Encoding charset to be used  
				  String charset = "UTF-8";  
				  Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
				  //generates QR code with Low level(L) error correction capability  
				  hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
				  //invoking the user-defined method that creates the QR code  
				  new createQRImage(SignedQRCode, path, charset, hashMap, 500, 500);//increase or decrease height and width accodingly   
				  //prints if the QR code is generated   
				  System.out.println("QR Code created successfully.");
				  
				  statusDTO.setStatus("Yes_E-Invoice is created Successfully. E-Invoice is: "+ackno);
				  
				  }
				 			  
			 }
			 catch(Exception e)
			 {
				System.out.println("Exception: "+e) ;
				statusDTO.setStatus(""+e);

			 }
			 
		    return statusDTO;
	    }
	 
	@Transactional
	@PutMapping("/creditNoteEinvoicecancel/{id}/{username}") 
    public ResponseDTO creditNoteEinvoicecancel(@PathVariable(value="id") long id,@PathVariable(value="username") String username,@Valid @RequestBody ResponseDTO responseDTO)
    {
		System.out.println("Enter Method : ");
		ResponseDTO res=new ResponseDTO();
		 
		 String oldSerial=responseDTO.getStatus();
		
		 System.out.println(responseDTO.getStatus());
		 
		 String USER_AGENT = "Mozilla/5.0";
		 
		 try
		 {
			//1ST API CALLING START
			
			 //String urlString =  "https://gstsandbox.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&user_name=TaxProEnvPON&eInvPwd=abc34*";
			 String urlString =  "https://einvapi.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&user_name=API_aayog&eInvPwd=Aayog@2022";
			
			 URL url = new URL(urlString);
			 HttpURLConnection con = (HttpURLConnection) url.openConnection();
			 con.setRequestMethod("GET");
			 con.setRequestProperty("User-Agent", USER_AGENT);
			 
			  int responseCode = con.getResponseCode();
			 // System.out.println("Sending get request : "+ url);
			//  System.out.println("Response code : "+ responseCode);
			  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			  String output;
			  StringBuffer response = new StringBuffer();
			 
			  while ((output = in.readLine()) != null) {

			   response.append(output);
			  }
			 
			  in.close();
			  String s=response.toString();
			  JSONObject obj = new JSONObject(s);
					 
			  System.out.println("Return json: "+s+"\n\n");
			  System.out.println("Status: "+obj.get("Status"));
			  String f_api_status=""+obj.get("Status");
			  
			  System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
			  obj = new JSONObject(obj.get("Data").toString());
			  System.out.println("Auth Token: "+obj.get("AuthToken"));
			  
			  String authtoken=""+obj.get("AuthToken");
			 
			  System.out.println("f_api_status status: "+f_api_status);
			  
			  // 1st API calling end
			  
			  //2nd API calling Start
			  
			  //String url1 = "https://gstsandbox.charteredinfo.com/eicore/dec/v1.03/Invoice/Cancel?aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=TaxProEnvPON";
			  String url1 = "https://einvapi.charteredinfo.com/eicore/dec/v1.03/Invoice/Cancel?aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=API_aayog";
			  
			  URL objnew = new URL(url1);
			  HttpURLConnection con1 = (HttpURLConnection) objnew.openConnection();
			  con1.setRequestMethod("POST");
			  con1.setRequestProperty("User-Agent", USER_AGENT);
			  con1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			  con1.setRequestProperty("Content-Type","application/json");
			  
			  //String postJsonData =  "{\"Version\":\"1.1\",\"ShipDtls\":null,\"TranDtls\":{\"TaxSch\":\"GST\",\"SupTyp\":\"B2B\",\"IgstOnIntra\":\"N\",\"RegRev\":null,\"EcmGstin\":null},\"DocDtls\":{\"Typ\":\"INV\",\"No\":\"GSI-2324-0001049\",\"Dt\":\"23/11/2023\"},\"SellerDtls\":{\"Gstin\":\"34AACCC1596Q002\",\"LglNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"TrdNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"Addr1\":\"802 MAHUA ROAD BELKUNDA\",\"Addr2\":\"BHOJPATTI VAISHALI\",\"Loc\":\"HAJIPUR\",\"Pin\":\"844125\",\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"BuyerDtls\":{\"Gstin\":\"10BOOPK2572L1ZS\",\"LglNm\":\"SUBH KIRANA STORES\",\"TrdNm\":\"SUBH KIRANA STORES\",\"Pos\":\"10\",\"Addr1\":\"REPURA BAZAR, VIA SARAIYA, Muzaffarpur, Bihar, 843107\",\"Addr2\":\"\",\"Loc\":\"MUZAFFARPUR\",\"Pin\":843107,\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"ValDtls\":{\"AssVal\":9850,\"IgstVal\":0,\"CgstVal\":246.26,\"SgstVal\":246.26,\"StCesVal\":0,\"Discount\":0,\"OthChrg\":0,\"RndOffAmt\":0},\"ItemList\":[{\"SlNo\":\"1\",\"PrdDesc\":\"ATTA 30/10KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26},{\"SlNo\":\"2\",\"PrdDesc\":\"ATTA 30/5KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26}]}";
			  String postJsonData = oldSerial;
			  con1.setDoOutput(true);
			  DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
			  wr.writeBytes(postJsonData);
			  wr.flush();
			  wr.close();
			  
			  int responseCode1 = con1.getResponseCode();
			  System.out.println("nSending 'POST' request to URL : " + url1);
			  System.out.println("Post Data : " + postJsonData);
			  System.out.println("Response Code : " + responseCode1);
			 
			  BufferedReader in1 = new BufferedReader(
			  new InputStreamReader(con1.getInputStream()));
			  String output1;
			  StringBuffer response1 = new StringBuffer();
			 
			  while ((output1 = in1.readLine()) != null) {
			   response1.append(output1);
			  }
			  in1.close();
			  
			  System.out.println("Response JSON: "+response1.toString());
			  
			  s=response1.toString();
			  obj = new JSONObject(s);
					 
			  System.out.println("Return json: "+s+"\n\n");
			  System.out.println("Status: "+obj.get("Status"));

			  String s_api_status=""+obj.get("Status");
			  String displaymessage="";
			  System.out.println("Data2: "+obj.get("Data").toString()+"\n\n");
			  if(obj.get("Data").toString().compareTo("null")==0)  
			  {
				 
				
				  JSONArray jsonArray = obj.getJSONArray("ErrorDetails"); 
				  for (int i = 0; i < jsonArray.length(); i++) 
				  	{  
		              
			            // store each object in JSONObject  
			            JSONObject explrObject = jsonArray.getJSONObject(i);  
			              
			            // get field value from JSONObject using get() method  
			            System.out.println("check error msg:"+explrObject.get("ErrorMessage"));  
			            displaymessage+=explrObject.get("ErrorMessage")+",";
			        }
				  
				  //System.out.println("check error msg11:"+displaymessage);
				  res.setStatus(displaymessage.substring(0,displaymessage.length()-1));
				  
			  }
			  else
			  {
				  LocalDateTime ldt = LocalDateTime.now();
				  String invid=sales_credit_noteRepository.getCreditNoteDetails(id).getCreditnoteid();
				  String message=responseDTO.getCancel_message();
				  
				  sales_credit_noteRepository.updateEinvoiceCancel(id);
				  sale_credit_note_einvoice_genRepository.updateEinvoiceGenCancel(ldt,username,invid,message);
				 res.setStatus("Done");
				// System.out.println("Response JSON2: "+response1.toString());  
			  }
		  }
		 catch(Exception e)
		 {
			 
		 }
		 return res;
    }
	 
	@Transactional
	 @PutMapping("/creditNoteEwaybillcreate/{id}/{username}") 
	    public StatusDTO creditNoteEwaybillcreate(@PathVariable(value="id") long id,@PathVariable(value="username") String username,@Valid @RequestBody StatusDTO statusDTO)
	    {
		   StatusDTO res = new StatusDTO();
		   String oldSerial=statusDTO.getStatus();
			
		   System.out.println(statusDTO.getStatus());
		   
		   String USER_AGENT = "Mozilla/5.0";
			 
			 try
			 {
				//1ST API CALLING START
				
				// String urlString =  "https://gstsandbox.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&user_name=TaxProEnvPON&eInvPwd=abc34*";
				 String urlString =  "https://einvapi.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&user_name=API_aayog&eInvPwd=Aayog@2022";
				
				 URL url = new URL(urlString);
				 HttpURLConnection con = (HttpURLConnection) url.openConnection();
				 con.setRequestMethod("GET");
				 con.setRequestProperty("User-Agent", USER_AGENT);
				 
				  int responseCode = con.getResponseCode();
				 // System.out.println("Sending get request : "+ url);
				//  System.out.println("Response code : "+ responseCode);
				  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				  String output;
				  StringBuffer response = new StringBuffer();
				 
				  while ((output = in.readLine()) != null) {

				   response.append(output);
				  }
				 
				  in.close();
				  String s=response.toString();
				  JSONObject obj = new JSONObject(s);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("Status"));
				  String f_api_status=""+obj.get("Status");
				  
				  System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
				  obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("Auth Token: "+obj.get("AuthToken"));
				  
				  String authtoken=""+obj.get("AuthToken");
				 
				  System.out.println("f_api_status status: "+f_api_status);
				  
				  // 1st API calling end
				  
				  //2nd API calling Start
				  
				 // String url1 = "https://gstsandbox.charteredinfo.com/eiewb/dec/v1.03/ewaybill?aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=TaxProEnvPON";
				  String url1 = "https://einvapi.charteredinfo.com/eiewb/dec/v1.03/ewaybill?aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=API_aayog";
				  
				  URL objnew = new URL(url1);
				  HttpURLConnection con1 = (HttpURLConnection) objnew.openConnection();
				  con1.setRequestMethod("POST");
				  con1.setRequestProperty("User-Agent", USER_AGENT);
				  con1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				  con1.setRequestProperty("Content-Type","application/json");
				  
				  //String postJsonData =  "{\"Version\":\"1.1\",\"ShipDtls\":null,\"TranDtls\":{\"TaxSch\":\"GST\",\"SupTyp\":\"B2B\",\"IgstOnIntra\":\"N\",\"RegRev\":null,\"EcmGstin\":null},\"DocDtls\":{\"Typ\":\"INV\",\"No\":\"GSI-2324-0001049\",\"Dt\":\"23/11/2023\"},\"SellerDtls\":{\"Gstin\":\"34AACCC1596Q002\",\"LglNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"TrdNm\":\"AAYOG AGRO PRIVATE LIMITED\",\"Addr1\":\"802 MAHUA ROAD BELKUNDA\",\"Addr2\":\"BHOJPATTI VAISHALI\",\"Loc\":\"HAJIPUR\",\"Pin\":\"844125\",\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"BuyerDtls\":{\"Gstin\":\"10BOOPK2572L1ZS\",\"LglNm\":\"SUBH KIRANA STORES\",\"TrdNm\":\"SUBH KIRANA STORES\",\"Pos\":\"10\",\"Addr1\":\"REPURA BAZAR, VIA SARAIYA, Muzaffarpur, Bihar, 843107\",\"Addr2\":\"\",\"Loc\":\"MUZAFFARPUR\",\"Pin\":843107,\"Stcd\":\"10\",\"Ph\":null,\"Em\":null},\"ValDtls\":{\"AssVal\":9850,\"IgstVal\":0,\"CgstVal\":246.26,\"SgstVal\":246.26,\"StCesVal\":0,\"Discount\":0,\"OthChrg\":0,\"RndOffAmt\":0},\"ItemList\":[{\"SlNo\":\"1\",\"PrdDesc\":\"ATTA 30/10KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26},{\"SlNo\":\"2\",\"PrdDesc\":\"ATTA 30/5KG TAX\",\"IsServc\":\"N\",\"HsnCd\":\"11010000\",\"Qty\":1.5,\"Unit\":\"QTL\",\"UnitPrice\":985,\"TotAmt\":4925,\"Discount\":0,\"PreTaxVal\":0,\"AssAmt\":4925,\"GstRt\":5,\"IgstAmt\":0,\"CgstAmt\":123.13,\"SgstAmt\":123.13,\"CesRt\":0,\"CesAmt\":0,\"CesNonAdvlAmt\":0,\"StateCesRt\":0,\"StateCesAmt\":0,\"StateCesNonAdvlAmt\":0,\"OthChrg\":0,\"TotItemVal\":5171.26}]}";
				  String postJsonData = oldSerial;
				  con1.setDoOutput(true);
				  DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
				  wr.writeBytes(postJsonData);
				  wr.flush();
				  wr.close();
				  
				  int responseCode1 = con1.getResponseCode();
				  System.out.println("nSending 'POST' request to URL : " + url1);
				  System.out.println("Post Data : " + postJsonData);
				  System.out.println("Response Code : " + responseCode1);
				 
				  BufferedReader in1 = new BufferedReader(
				  new InputStreamReader(con1.getInputStream()));
				  String output1;
				  StringBuffer response1 = new StringBuffer();
				 
				  while ((output1 = in1.readLine()) != null) {
				   response1.append(output1);
				  }
				  in1.close();
				  
				  System.out.println("Response JSON: "+response1.toString());
				  
				  s=response1.toString();
				  obj = new JSONObject(s);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("Status"));

				  String s_api_status=""+obj.get("Status");
				  String displaymessage="";
				  System.out.println("Data2: "+obj.get("Data").toString()+"\n\n");
				  if(obj.get("Data").toString().compareTo("null")==0)  
				  {
					 
					
					  JSONArray jsonArray = obj.getJSONArray("ErrorDetails"); 
					  for (int i = 0; i < jsonArray.length(); i++) 
					  	{  
			              
				            // store each object in JSONObject  
				            JSONObject explrObject = jsonArray.getJSONObject(i);  
				              
				            // get field value from JSONObject using get() method  
				            System.out.println("check error msg:"+explrObject.get("ErrorMessage"));  
				            displaymessage+=explrObject.get("ErrorMessage")+",";
				        }
					  
					  //System.out.println("check error msg11:"+displaymessage);
					  res.setStatus(displaymessage.substring(0,displaymessage.length()-1));
					  
				  }
				  else
				  {
				  obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("EwbNo: "+obj.get("EwbNo"));
				  System.out.println("EwbDt: "+obj.get("EwbDt"));
				  LocalDateTime ldt = LocalDateTime.now();
				  String invid=sales_credit_noteRepository.getCreditNoteDetails(id).getCreditnoteid();
				  
				  String ewaybill=""+obj.get("EwbNo");
				  String ewaybilldate=""+obj.get("EwbDt");
				 
				  sales_credit_noteRepository.updateEwaybill(id,ewaybill);
				  sale_credit_note_einvoice_genRepository.updateEwaybillGen(ldt,username,invid,ewaybill,ewaybilldate);
				  
				  res.setStatus("Done_EWaybill is created successfully,EWaybillNo is "+ewaybill);
				  System.out.println("Response JSON Way bill: "+response1.toString());  
				  }
				  
				  
			 }
			 catch(Exception e)
			 {
				System.out.println("Exception: "+e) ;
				res.setStatus("None");
			 }
		   
		   return res;
	    }
	 
	
	 @Transactional
	 @PutMapping("/creditNoteEwaybillcancel/{id}/{username}") 
	    public ResponseDTO creditNoteEwaybillcancel(@PathVariable(value="id") long id,@PathVariable(value="username") String username,@Valid @RequestBody ResponseDTO responseDTO)
	    {
			 //StatusDTO res = new StatusDTO();
			 System.out.println(responseDTO.getStatus());
			 //System.out.println("Enter Method : ");
			 
			 ResponseDTO res=new ResponseDTO();
			 
			 String oldSerial=responseDTO.getStatus();
			
			 System.out.println("oldSerial: "+oldSerial);
			 
			 String USER_AGENT = "Mozilla/5.0";
			 
			 try
			 {
				//1ST API CALLING START
				 //String urlString =  "https://gstsandbox.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&user_name=TaxProEnvPON&eInvPwd=abc34*";
				 String urlString =  "https://einvapi.charteredinfo.com/eivital/dec/v1.04/auth?&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&user_name=API_aayog&eInvPwd=Aayog@2022";
				
				 URL url = new URL(urlString);
				 HttpURLConnection con = (HttpURLConnection) url.openConnection();
				 con.setRequestMethod("GET");
				 con.setRequestProperty("User-Agent", USER_AGENT);
				 
				  int responseCode = con.getResponseCode();
				 // System.out.println("Sending get request : "+ url);
				//  System.out.println("Response code : "+ responseCode);
				  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				  String output;
				  StringBuffer response = new StringBuffer();
				 
				  while ((output = in.readLine()) != null) {

				   response.append(output);
				  }
				  in.close();
				  String s=response.toString();
				  JSONObject obj = new JSONObject(s);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("Status"));
				  String f_api_status=""+obj.get("Status");
				  
				  System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
				  obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("Auth Token: "+obj.get("AuthToken"));
				  
				  String authtoken=""+obj.get("AuthToken");
				 
				  System.out.println("f_api_status status: "+f_api_status);
				  
				  // 1st API calling end
				  //2nd API calling Start
				  
				  //String url1 = "http://gstsandbox.charteredinfo.com/ewaybillapi/dec/v1.03/ewayapi?action=CANEWB&aspid=1659183221&password=Avijit!12ch&Gstin=34AACCC1596Q002&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=TaxProEnvPON";
				  String url1 = "https://einvapi.charteredinfo.com/ewaybillapi/dec/v1.03/ewayapi?action=CANEWB&aspid=1751181923&password=Aayog@123&Gstin=10AATCA7447B1ZV&eInvPwd=abc34*&AuthToken="+authtoken+"&user_name=API_aayog";
				  
				  URL objnew = new URL(url1);
				  HttpURLConnection con1 = (HttpURLConnection) objnew.openConnection();
				  con1.setRequestMethod("POST");
				  con1.setRequestProperty("User-Agent", USER_AGENT);
				  con1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				  con1.setRequestProperty("Content-Type","application/json");
				  
				  String postJsonData = oldSerial;
				  con1.setDoOutput(true);
				  DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
				  wr.writeBytes(postJsonData);
				  wr.flush();
				  wr.close();
				  
				  int responseCode1 = con1.getResponseCode();
				  System.out.println("nSending 'POST' request to URL : " + url1);
				  System.out.println("Post Data : " + postJsonData);
				  System.out.println("Response Code : " + responseCode1);
				 
				  BufferedReader in1 = new BufferedReader(
				  new InputStreamReader(con1.getInputStream()));
				  String output1;
				  StringBuffer response1 = new StringBuffer();
				 
				  while ((output1 = in1.readLine()) != null) {
				   response1.append(output1);
				  }
				  in1.close();
				  
				  System.out.println("Response JSON: "+response1.toString());
				  
				  s=response1.toString();
				  obj = new JSONObject(s);
						 
				  //System.out.println("Return json: "+s+"\n\n");
				  //System.out.println("Status: "+obj.get("Status"));

					  LocalDateTime ldt = LocalDateTime.now();
					  String invid=sales_credit_noteRepository.getCreditNoteDetails(id).getCreditnoteid();
					  String message=responseDTO.getCancel_message();
					  
					  sales_credit_noteRepository.updateWaybillCancel(id);
					  sale_credit_note_einvoice_genRepository.updateEwaybillGenCancel(ldt,username,invid,message);
					 responseDTO.setStatus("Done");

			 }
			 catch(Exception e)
			 {
				System.out.println("Ereror:"+e);
			 }
			 return responseDTO;
	    }
	
	 @Transactional
	 	@PutMapping("/creditNoteEwaybillWOInvoiceCreate/{id}/{username}") 
	    public StatusDTO creditNoteEwaybillWOInvoiceCreate(@PathVariable(value="id") long id,@PathVariable(value="username") String username,@Valid @RequestBody StatusDTO statusDTO)
	    {
	    	System.out.println("message"+statusDTO.getStatus());
	    	//System.out.println("username"+username);
	    	String USER_AGENT = "Mozilla/5.0";
			
			String oldSerial = statusDTO.getStatus();
			
			try
			 {
				//1ST API CALLING START
				
				//String urlString =  "https://gstsandbox.charteredinfo.com/ewaybillapi/dec/v1.03/auth?action=ACCESSTOKEN&aspid=1659183221&password=Avijit!12ch&gstin=34AACCC1596Q002&username=TaxProEnvPON&ewbpwd=abc34*";
				String urlString =  "https://einvapi.charteredinfo.com/v1.03/dec/auth?action=ACCESSTOKEN&aspid=1751181923&password=Aayog@123&gstin=10AATCA7447B1ZV&username=API_aayog&ewbpwd=Aayog@2022";
				 URL url = new URL(urlString);
				 HttpURLConnection con = (HttpURLConnection) url.openConnection();
				 con.setRequestMethod("GET");
				 con.setRequestProperty("User-Agent", USER_AGENT);
				 
				  int responseCode = con.getResponseCode();
				 // System.out.println("Sending get request : "+ url);
				//  System.out.println("Response code : "+ responseCode);
				  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				  String output;
				  StringBuffer response = new StringBuffer();
				 
				  while ((output = in.readLine()) != null) {

				   response.append(output);
				  }
				 
				  in.close();
				  String s=response.toString();
				  JSONObject obj = new JSONObject(s);
						 
				  System.out.println("Return json: "+s+"\n\n");
				  System.out.println("Status: "+obj.get("status"));
				  String f_api_status=""+obj.get("status");
				  
				  //System.out.println("Data1: "+obj.get("Data").toString()+"\n\n");
				  //obj = new JSONObject(obj.get("Data").toString());
				  System.out.println("Auth Token: "+obj.get("authtoken"));
				  
				  String authtoken=""+obj.get("authtoken");
				 
				  System.out.println("f_api_status status: "+f_api_status);
				  
				  // 1st Api Calling End....
				  
				  
				 // String url1 = "https://gstsandbox.charteredinfo.com/ewaybillapi/dec/v1.03/ewayapi?action=GENEWAYBILL&aspid=1659183221&password=Avijit!12ch&gstin=34AACCC1596Q002&username=TaxProEnvPON&authtoken="+authtoken;
				  String url1 = "https://einvapi.charteredinfo.com/v1.03/dec/ewayapi?action=GENEWAYBILL&aspid=1751181923&password=Aayog@123&gstin=10AATCA7447B1ZV&username=API_aayog&authtoken="+authtoken;
				  
				  URL objnew = new URL(url1);
				  HttpURLConnection con1 = (HttpURLConnection) objnew.openConnection();
				  con1.setRequestMethod("POST");
				  con1.setRequestProperty("User-Agent", USER_AGENT);
				  con1.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				  con1.setRequestProperty("Content-Type","application/json");
				  
				  //local JSON="{\"supplyType\":\"O\",\"subSupplyType\":\"1\",\"subSupplyDesc\":null,\"docType\":\"INV\",\"docNo\":\"GSI-2324-0004425\",\"docDate\":\"09/12/2023\",\"fromGstin\":\"34AACCC1596Q002\",\"fromTrdName\":\"AAYOG AGRO PRIVATE LIMITED\",\"fromAddr1\":\"802 MAHUA ROAD BELKUNDA\",\"fromAddr2\":\"BHOJPATTI VAISHALI\",\"fromPlace\":\"HAJIPUR\",\"fromPincode\":\"605001\",\"actFromStateCode\":34,\"fromStateCode\":34,\"toGstin\":\"10AABCB2066P2ZT\",\"toTrdName\":\"BRITANNIA INDUSTRIES LTD. - HAJIPUR\",\"toAddr1\":\"EPIP HAJIPUR INDUSTRIAL AREA,\\nPLOT NUMBER-C3,C4 TO C11\\n844102\",\"toAddr2\":\"Not Required\",\"toPlace\":\"VAISHALI\",\"toPincode\":844102,\"actToStateCode\":\"10\",\"toStateCode\":\"10\",\"transactionType\":\"4\",\"dispatchFromGSTIN\":null,\"dispatchFromTradeName\":null,\"shipToGSTIN\":null,\"shipToTradeName\":null,\"otherValue\":0,\"totalValue\":42000,\"cgstValue\":0,\"sgstValue\":0,\"igstValue\":2100,\"cessValue\":0,\"cessNonAdvolValue\":0,\"totInvValue\":44100,\"transporterId\":null,\"transporterName\":\"SHIVAM ROAD LINES - TRANS FINI PRD\",\"transDocNo\":\"GSI-2324-0004425\",\"transMode\":\"1\",\"transDistance\":\"0\",\"transDocDate\":\"09/12/2023\",\"vehicleNo\":\"HR55N2344\",\"vehicleType\":\"R\",\"ItemList\":[{\"productName\":\"BESAN 30/10KG TAX\",\"productDesc\":\"BESAN 30/10KG TAX\",\"hsnCode\":\"11061000\",\"quantity\":3,\"qtyUnit\":\"QTL\",\"cgstRate\":0,\"sgstRate\":0,\"igstRate\":5,\"cessRate\":0,\"cessNonAdvol\":0,\"taxableAmount\":8700},{\"productName\":\"DALIA 30/200G TAX\",\"productDesc\":\"DALIA 30/200G TAX\",\"hsnCode\":\"11031110\",\"quantity\":3,\"qtyUnit\":\"QTL\",\"cgstRate\":0,\"sgstRate\":0,\"igstRate\":5,\"cessRate\":0,\"cessNonAdvol\":0,\"taxableAmount\":8400},{\"productName\":\"MAIDA 30/1KG TAX\",\"productDesc\":\"MAIDA 30/1KG TAX\",\"hsnCode\":\"11010000\",\"quantity\":3,\"qtyUnit\":\"QTL\",\"cgstRate\":0,\"sgstRate\":0,\"igstRate\":5,\"cessRate\":0,\"cessNonAdvol\":0,\"taxableAmount\":8100},{\"productName\":\"SUJI 30/500G TAX\",\"productDesc\":\"SUJI 30/500G TAX\",\"hsnCode\":\"11031110\",\"quantity\":3,\"qtyUnit\":\"QTL\",\"cgstRate\":0,\"sgstRate\":0,\"igstRate\":5,\"cessRate\":0,\"cessNonAdvol\":0,\"taxableAmount\":7800},{\"productName\":\"ATTA 30/10KG TAX\",\"productDesc\":\"ATTA 30/10KG TAX\",\"hsnCode\":\"11010000\",\"quantity\":3,\"qtyUnit\":\"QTL\",\"cgstRate\":0,\"sgstRate\":0,\"igstRate\":5,\"cessRate\":0,\"cessNonAdvol\":0,\"taxableAmount\":9000}]}";
				  
				  String postJsonData = oldSerial;
				  con1.setDoOutput(true);
				  DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
				  wr.writeBytes(postJsonData);
				  wr.flush();
				  wr.close();
				  
				  int responseCode1 = con1.getResponseCode();
				  System.out.println("nSending 'POST' request to URL : " + url1);
				  System.out.println("Post Data : " + postJsonData);
				  System.out.println("Response Code : " + responseCode1);
				  if(responseCode1==200)
				  {
					  BufferedReader in1 = new BufferedReader(
							  new InputStreamReader(con1.getInputStream()));
							  String output1="";
							  StringBuffer response1 = new StringBuffer();
							 
							  while ((output1 = in1.readLine()) != null) {
							   response1.append(output1);
							  }
							  in1.close();
							  
							  System.out.println("Response JSON: "+response1.toString());
							  
							  s=response1.toString();
							  obj = new JSONObject(s);
									 
							  System.out.println("Return json: "+s+"\n\n");
							  System.out.println("ewayBillNo: "+obj.get("ewayBillNo"));
							  System.out.println("ewayBillDate: "+obj.get("ewayBillDate"));

							  String s_api_status="1";
								  
								  String ewaybill=""+obj.get("ewayBillNo");
								  String ewaybilldate=""+obj.get("ewayBillDate");
								 
							 
								  //System.out.println("AckNo: "+obj.get("AckNo"));
								  //System.out.println("AckDt: "+obj.get("AckDt"));
								  //System.out.println("Irn: "+obj.get("Irn"));
								  //System.out.println("SignedInvoice: "+obj.get("SignedInvoice"));
								  //System.out.println("s_api_status status: "+s_api_status);
								  
								  String ackno="NA"; //einvoice no
								  String ackdate=""+ewaybilldate;
								 
								  String irn="NA";
								  String signedInvoice="NA";
								  
								  Sale_credit_note_einvoice_gen einv =new Sale_credit_note_einvoice_gen();
								  
								  LocalDateTime ldt = LocalDateTime.now();
								  String invid=sales_credit_noteRepository.getCreditNoteDetails(id).getCreditnoteid();
								  
								  einv.setModified_type("INSERTED");
								  einv.setInserted_by(username);
								  einv.setInserted_on(ldt);
								  einv.setUpdated_by("NA");
								  einv.setUpdated_on(ldt);
								  einv.setDeleted_by("NA");
								  einv.setDeleted_on(ldt);
								  einv.setUsername(username);
								  einv.setCancel_on(ldt);
								  einv.setCancel_by("NA");
								  einv.setCreditnoteid(invid);
								  einv.setF_api_status(Boolean.parseBoolean(f_api_status));
								  einv.setAuth_token(authtoken);
								  einv.setS_api_status(Boolean.parseBoolean(s_api_status));
								  einv.setAck_no(ackno);
								  einv.setAck_date(ackdate);
								  einv.setIrn_no(irn);
								  einv.setSighned_invoice(signedInvoice);
								  einv.setCancel_status(false);
								  einv.setCancel_message("Not Cancel");
								  
								  einv.setEway_bill_no(ewaybill);
								  einv.setEway_bill_date(ewaybilldate);
								  einv.setWaybill_create_on(ldt);
								  einv.setWaybill_create_by(username);
								  
								  sales_credit_noteRepository.updateEwaybillWOInv(id,ewaybill);
								  sale_credit_note_einvoice_genRepository.save(einv);
								  
								  statusDTO.setStatus("Yes_E-Waybill Without Credit Note is Created Successfully. E-Waybill is: "+ewaybill);
								  
								 // } 
				  }
				  else {
					  statusDTO.setStatus("E-way Bill Already Created...");  
				  }
				  
				 			  
			 }
			 catch(Exception e)
			 {
				System.out.println("Exception: "+e) ;
				statusDTO.setStatus(""+e);
			 }
			 
		    return statusDTO;
	    }
	 
/******************* Sales credit note End **********************/
	
    
    @GetMapping("/getChargesMatrixSalesdetails/{delivery_cid}")
	public List<Sales_Order_Summary_dyn> getChargesMatrixSalesdetails(@PathVariable(value="delivery_cid") String delivery_cid) {
		
		return sales_InvoiceService.getChargesMatrixSalesdetails(delivery_cid);
	}
	
	
	@GetMapping("/getAppChargesSalesdetails/{delivery_cid}")
	public Sales_Order getAppChargesSalesdetails(@PathVariable(value="delivery_cid") String delivery_cid) {
		
		return sales_InvoiceService.getAppChargesSalesdetails(delivery_cid);
	}
	
	@PutMapping("/deleteCreditNotes/{id}")
	public ResponseEntity<Sales_credit_note> deleteCreditNotes(@PathVariable(value="id") long id,@Valid @RequestBody Sales_credit_note creditNote)
	{
		Sales_credit_note op=sales_credit_noteService.deleteCreditNotes(creditNote,id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteSalesReturnNotes/{id}")
	public ResponseEntity<Sales_return_note> deleteSalesReturnNotes(@PathVariable(value="id") long id,@Valid @RequestBody Sales_return_note salesReturnNote)
	{
		Sales_return_note op=sales_return_noteService.deleteSalesReturnNotes(salesReturnNote,id);
		return ResponseEntity.ok().body(op);
	}
	
	@Autowired
	Return_approval_noteService return_approval_noteService;
	
	@PutMapping("/deleteSalesReturnApprovalNotes/{id}")
	public ResponseEntity<Return_approval_note> deleteSalesReturnApprovalNotes(@PathVariable(value="id") long id,@Valid @RequestBody Return_approval_note returnApprovalNote)
	{
		Return_approval_note op=return_approval_noteService.deleteSalesReturnApprovalNotes(returnApprovalNote,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getdiliverychallanreturnapprovepopup/{id}")
	public List<Delivery_challan> getdiliverychallanreturnapprovepopup(@PathVariable(value="id") Long id) {
		
		return delivery_challanService.getdiliverychallanreturnapprovepopup(id);
	}
	
	@GetMapping("/getreturnapprovalsalesorder/{id}")
	public List<Sales_Order> getreturnapprovalsalesorder(@PathVariable(value="id") Long id) {
		
		return sales_OrderService.getreturnapprovalsalesorder(id);
	}
	
	@GetMapping("/getreturnapprovalsalesInvoice/{id}")
	public List<Sales_Invoice> getreturnapprovalsalesInvoice(@PathVariable(value="id") Long id) {
		
		return sales_InvoiceService.getreturnapprovalsalesInvoice(id);
	}
	
	@GetMapping("/accountpostingCreditNote/{id}")
	public Sales_credit_note accountpostingCreditNote(@PathVariable(value = "id") Long id) {
		
		return sales_credit_noteService.accountpostingCreditNote(id);
		
	}
	
	@GetMapping("/accountpostingundo/{id}")
	public Sales_credit_note accountpostingundo(@PathVariable(value = "id") Long id) {
		
		return sales_credit_noteService.accountpostingundo(id);
		
	}

	
	/************** Rate Chart Starts **************/
	
	@Autowired
	RatechartService ratechartService;
	
	@GetMapping("/getRSequenceId/{fin_year}")
	
	public SequenceIdDTO getRSequenceId(@PathVariable(value = "fin_year") String fin_year)
	{
		 return ratechartService.getRSequenceId(fin_year);
	}
	
	@PostMapping("/createRateChart")
	public Ratechart createRateChart(@Valid @RequestBody Ratechart ratechart)
	{
		 return ratechartService.save(ratechart);				
	}
	
	@GetMapping("/getRateChartList/{fin_year}")
	public List<Map<String, Object>> getRateChartList(@PathVariable(value = "fin_year") String fin_year)
	{
		return ratechartService.getRateChartList(fin_year);				
	}
	
	@GetMapping("/RateChartList")
	public List<Map<String, Object>> RateChartList()
	{
		return ratechartService.RateChartList();				
	}
	
	@GetMapping("/retriveRateChart/{id}")
	public Ratechart retriveRateChart(@PathVariable(value = "id") Long id) {
		 return ratechartService.findOne(id);
	}

	@GetMapping("/rateRetriveList/{r_code}")
	public List<Map<String, Object>> rateRetriveList(@PathVariable(value = "r_code") String r_code) {
		return ratechartService.rateRetriveList(r_code);
	}
	
	@PutMapping("/updateRateChart/{id}")
	public ResponseEntity<Ratechart> updateRateChart(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Ratechart ratechart) {
		Ratechart op = ratechartService.update(ratechart, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/DeleteRateChart/{id}")
	public ResponseEntity<Ratechart> DeleteRateChart(@PathVariable(value="id") long id,
													@Valid @RequestBody Ratechart ratechart)
	{
		Ratechart deleteRC=ratechartService.delete(ratechart,id);
		return ResponseEntity.ok().body(deleteRC);
	}
	
	@GetMapping("/getRateChartDateVerify/{date}")
	
	public StatusDTO getRateChartDateVerify(@PathVariable(value = "date") String date)
	{
		 return ratechartService.getRateChartDateVerify(date);
	}
	
	@GetMapping("/getRateChartItemthdt/{date}")
	
	public List<Item_rate_dtls> getRateChartItemthdt(@PathVariable(value = "date") String date)
	{
		 return ratechartService.getRateChartItemthdt(date);
	}
	
	/************** Rate Chart Ends **************/
	

    @GetMapping("/getSalesOrderList/{salesprocess}/{fin_year}")
	public List<Map<String,Object>> getSalesOrderList(@PathVariable(value = "salesprocess") String salesprocess,@PathVariable(value = "fin_year") String fin_year)
	{
		return sales_OrderService.getSalesOrderList(salesprocess, fin_year);
	}
	
	
}
