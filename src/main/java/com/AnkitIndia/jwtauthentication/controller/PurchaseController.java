package com.AnkitIndia.jwtauthentication.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.AnkitIndia.jwtauthentication.model.Nongoodssrn;
import com.AnkitIndia.jwtauthentication.model.Nongoodssrn_item_details;
import com.AnkitIndia.jwtauthentication.model.Nonservicesrn_desc_details;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Pur_Indent;
import com.AnkitIndia.jwtauthentication.model.Pur_L1_Selection;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_Trans_Chgs_dyn;
import com.AnkitIndia.jwtauthentication.model.Pur_Order_app_chgs;
import com.AnkitIndia.jwtauthentication.model.Pur_Peri_Quality_Check;
import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check;
import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check_Details;
import com.AnkitIndia.jwtauthentication.model.Pur_Quality_Check_Details_QcDetails;
import com.AnkitIndia.jwtauthentication.model.Pur_Quotation;
import com.AnkitIndia.jwtauthentication.model.Pur_debit_note;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_item_details;
import com.AnkitIndia.jwtauthentication.model.Pur_return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Pur_return_note;
import com.AnkitIndia.jwtauthentication.model.Purchase_item_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Sales_Order_Trans_Chgs_dyn;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice_item_dtls;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_BillDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_account_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_bp_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_broker_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_musterroll_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_debit_note_tax_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receiptDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_supplier_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_trans_dynDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_approval_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_supplier_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_trans_dynDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_return_note_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.security.services.Pur_BillService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_EnquiryService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_IndentService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_L1_SelectionService_Imp;
import com.AnkitIndia.jwtauthentication.security.services.Pur_OrderService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_Peri_Quality_CheckService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_Quality_CheckService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_QuotationService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_debit_noteService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_good_receiptService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_return_approval_noteService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_return_noteService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_unload_adviceService;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_OrderDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
//@RequestMapping("/MeghnaBidi")
//@RequestMapping("/MeghanaPanMasala")
//@RequestMapping("/IndianSpices")
public class PurchaseController {
	
	@Autowired
	Wm_unload_adviceService wm_unload_adviceService;
	
/******************* Purchase Indent  **********************/
	
	@Autowired
	Pur_IndentService pur_IndentService;
	
	@GetMapping("/getIndSequenceId/{prefix}/{orderdate}/{type}")
	public SequenceIdDTO getIndSequenceId(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate,@PathVariable(value = "type") String type)
	{
		return pur_IndentService.getIndSequenceId(prefix,orderdate,type);
	}
	
	/* to save a Purchase Indent */
	@PostMapping("/createPurchaseIndent")
	public Pur_Indent createPurchaseIndent(@Valid @RequestBody Pur_Indent pur_Indent) {
		return pur_IndentService.saveIndent(pur_Indent);
	}
	
	/* get all Indents */
	@GetMapping("/getPurchaseIndents")
	public List<Pur_Indent> getAllIndents() {
		return pur_IndentService.findAll();
	}
	
	@PutMapping("/updatePurIndent/{id}")
	public ResponseEntity<Pur_Indent> updateItemMaster(@PathVariable(value="id") long id,@Valid @RequestBody Pur_Indent iMaster)
	{
		Pur_Indent op=pur_IndentService.update(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	
	/******************* Purchase Indent End **********************/
	
	/******************* Purchase Order **********************/
	
	@Autowired
	Pur_OrderService pur_OrderService;
	
	@GetMapping("/getPurchaseOrderList/{currDate}/{finyear}")
	public List<Pur_OrderDTO> getPurchaseOrderList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return pur_OrderService.getPurchaseOrderList(currDate,finyear);
	}
	
	@GetMapping("/getPurchaseOrderListFastApi/{currDate}/{finyear}")
	public List<Map<String, Object>> getPurchaseOrderListFastApi(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return pur_OrderService.getPurchaseOrderListFastApi(currDate,finyear);
	}
	
	
	@GetMapping("/getPOSequenceId/{prefix}/{orderdate}/{orderfor}/{potype}/{posubtype}")
	public SequenceIdDTO getPOSequenceId(@PathVariable(value = "prefix") String prefix,
			@PathVariable(value = "orderdate") String orderdate,
			@PathVariable(value = "orderfor") boolean orderfor,
			@PathVariable(value = "potype") String potype,
			@PathVariable(value = "posubtype") String posubtype)
	{
		return pur_OrderService.getPOSequenceId(prefix,orderdate,orderfor,potype,posubtype);
	}
	
	@GetMapping("/getJwPo")
	public List<Map<String,Object>> getJwPo()
	{
		  return pur_OrderService.getJwPo();
	}
	
	/* to save a Purchase Order */
	/*@PostMapping("/createPurchaseOrder")
	public Pur_Order createPurchaseOrder(@Valid @RequestBody Pur_Order pur_Order) {
		//System.out.println("createPurchaseOrder");
		return pur_OrderService.save(pur_Order);
	}*/
	
	@GetMapping("/transporterNameChgsPurList/{orderid}")
	public List<Map<String, Object>> transporterNameChgsPurList(@PathVariable(value = "orderid") String orderid)
	{
		return pur_OrderService.transporterNameChgsPurList(orderid);
	}
	
	@PostMapping("/createPurchaseOrder")
	public Pur_Order purordersave(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		//System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Pur_Order pur_Order=objectMapper.readValue(input, Pur_Order.class);
		
		return pur_OrderService.save(pur_Order,files);
	}
	
	
	
	/* get all Indents */
	@GetMapping("/getPurchaseOrders")
	public List<Pur_Order> getAllOrders() {
		
		return pur_OrderService.findAllOrder();
	}
	
	//getPurOrderPagination
	
	
	@GetMapping("/getpurchaseordercharges/{transporter_Id}/{referance_id}")
	public Pur_Order_Trans_Chgs_dyn  getpurchaseordercharges(@PathVariable(value="transporter_Id") String transporter_Id,@PathVariable(value="referance_id") String referance_id)
	{
		return pur_OrderService.getpurchaseordercharges(transporter_Id,referance_id);
	}
	
	 @GetMapping("/getPurOrderPagination/{page}/{size}")
	  public Page<Pur_Order_Pagination_DTO> getPurOrderPagination(@PathVariable(value = "page") int page,@PathVariable(value = "size") int size) 
	{
		  return pur_OrderService.getPurOrderPagination(page,size);
	}
	  
  @GetMapping(value = "/searchPurchaseOrder")
	public List<Pur_Order_Pagination_DTO> searchPurchaseOrder(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String supplier_name1,@RequestParam(defaultValue = "") String finyear)
	 {
		return pur_OrderService.searchPurchaseOrder(orderno,fromdate,todate,supplier_name1,finyear);
	 }
	
	 @GetMapping(value = "/searchPurchaseOrderFast")
		public List<Map<String,Object>> searchPurchaseOrderFast(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String supplier_name1,@RequestParam(defaultValue = "") String finyear)
		 {
			return pur_OrderService.searchPurchaseOrderFast(orderno,fromdate,todate,supplier_name1,finyear);
		 }
	 
	 @GetMapping(value = "/getdocumentno")
	public List<Map<String,Object>> getdocumentno()
	{
			return pur_OrderService.getdocumentno();
	}
		
	@GetMapping(value = "/purchaseorderlist/{curdate}")
	public List<Map<String, Object>> purchaseorderlist(@PathVariable(value = "curdate")String curdate)
	 {
		return pur_OrderService.purchaseorderlist(curdate);
	 }
	
	@GetMapping("/getLastPOThruSupItemDtls/{supplier_name}")
	public List<Map<String, Object>> getLastPOThruSupItemDtls(@PathVariable(value = "supplier_name") String supplier_name)
	{
		return pur_OrderService.getLastPOThruSupItemDtls(supplier_name);
					
	}
	
	@GetMapping("/getLastPOThruSupPurDtls/{supplier_name}")
	public List<Map<String, Object>> getLastPOThruSupPurDtls(@PathVariable(value = "supplier_name") String supplier_name)
	{
		return pur_OrderService.getLastPOThruSupPurDtls(supplier_name);
					
	}
	
	@PutMapping("/deletePurchaseOrder/{id}")
	public ResponseEntity<Pur_Order> deletePurchaseOrder(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Pur_Order pur_Order) {
		Pur_Order op = pur_OrderService.deletePurchaseOrder(pur_Order, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getPurOrd/{pur_order_id}")
    public List<Pur_Order> getPurOrd(@PathVariable(value = "pur_order_id") String pur_order_id) {
		
		return pur_OrderService.getPurOrd(pur_order_id);
	}
    
   /* @GetMapping("/getPurOrdListOnlyStorePurchase/{supplier}/{businessunit}")
    public List<Map<String, Object>> getPurOrdListOnlyStorePurchase(@PathVariable(value = "supplier") String supplier,@PathVariable(value = "businessunit") String businessunit)
    {
		return pur_OrderService.getPurOrdListOnlyStorePurchase(supplier,businessunit);
	}*/
    
    @GetMapping("/getPurOrdListOnlyStorePurchase/{supplier}/{businessunit}")
    public List<Pur_OrderDTO> getPurOrdListOnlyStorePurchase(@PathVariable(value = "supplier") String supplier,@PathVariable(value = "businessunit") String businessunit)
    {
		return pur_OrderService.getPurOrdListOnlyStorePurchase(supplier,businessunit);
	}
    
    @GetMapping("/getPurOrdListOnlyPacking/{supplier}/{businessunit}")
    public List<Pur_OrderDTO> getPurOrdListOnlyPacking(@PathVariable(value = "supplier") String supplier,@PathVariable(value = "businessunit") String businessunit)
    {
		return pur_OrderService.getPurOrdListOnlyPacking(supplier,businessunit);
	}
    
    
    @GetMapping("/getpssd_item_qty/{unloadadvice}/{weighmentdata}/{purchaseid}")
	public String getpssd_item_qty(@PathVariable(value = "unloadadvice") String unloadadvice,@PathVariable(value = "weighmentdata") String weighmentdata,@PathVariable(value = "purchaseid") String purchaseid)
	{
		return pur_OrderService.getpssd_item_qty(unloadadvice,weighmentdata,purchaseid);
	}
    
    @GetMapping("/getpssd_item_qtyrectify/{unadviceid}/{pricebasedon}/{subtypestatus}")
	public double getpssd_item_qtyrectify(@PathVariable(value = "unadviceid") String unadviceid,@PathVariable(value = "pricebasedon") String pricebasedon,@PathVariable(value = "subtypestatus") String subtypestatus)
	{
		
		return pur_OrderService.getpssd_item_qtyrectify(unadviceid,pricebasedon,subtypestatus);
		
	}
    
	@GetMapping("/getpssd_packing_qty/{unloadadvice}/{weighmentdata}/{purchaseid}")
	public String getpssd_packing_qty(@PathVariable(value = "unloadadvice") String unloadadvice,@PathVariable(value = "weighmentdata") String weighmentdata,@PathVariable(value = "purchaseid") String purchaseid)
	{
		
		return pur_OrderService.getpssd_packing_qty(unloadadvice,weighmentdata,purchaseid);
		
	}
    
	
	@GetMapping("/getpssd_packing_qtyrectify/{unadviceid}/{pricebasedon}/{subtypestatus}")
	public double getpssd_packing_qtyrectify(@PathVariable(value = "unadviceid") String unadviceid,@PathVariable(value = "pricebasedon") String pricebasedon,@PathVariable(value = "subtypestatus") String subtypestatus)
	{
		
		return pur_OrderService.getpssd_packing_qtyrectify(unadviceid,pricebasedon,subtypestatus);
		
	}
	
	
    @GetMapping("/getpssd_item_qtymultiplepopup/{unloadadvice}")
	public String getpssd_item_qty_multi_popup(@PathVariable(value = "unloadadvice") String unloadadvice)
	{
		return pur_OrderService.getpssd_item_qty_multi_popup(unloadadvice);	
	}
    
    
    
    
    @GetMapping("/getpssd_packing_qtymultiplepopup/{unloadadvice}")
   	public String getpssd_packing_qtymultiplepopup(@PathVariable(value = "unloadadvice") String unloadadvice)
   	{
   		return pur_OrderService.getpssd_packing_qtymultiplepopup(unloadadvice);	
   	}
    
	@PutMapping("/updatePurOrder/{id}")
	public ResponseEntity<Pur_Order> updateItemMaster(@PathVariable(value="id") long id,@Valid @RequestBody Pur_Order iMaster)
	{
		Pur_Order op=pur_OrderService.update(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/purOrdTramsCondition/{purid}")
	public List<Map<String, Object>> purOrdTramsCondition(@PathVariable(value = "purid") String purid)
	{
		return pur_OrderService.purOrdTramsCondition(purid);
					
	}
	
	/******************* Purchase Order End **********************/
	
	/******************* Purchase Enquiry Start  **********************/

	@Autowired
	Pur_EnquiryService pur_EnquiryService;
	
	/* Create Purchase Enquiry */ 
	@PostMapping("/createPurchaseEnquiry")
	public Pur_Enquiry createPurchaseEnquiry(@Valid @RequestBody Pur_Enquiry pur_Enquiry)
	{
		return pur_EnquiryService.save(pur_Enquiry);
	}
	
	/* Get All Enquiries */
	@GetMapping("/getPurchaseEnquiries")
	public List<Pur_Enquiry> getAllEnquiries()
	{
		return pur_EnquiryService.findAll();
	}
	
	@PutMapping("/updatePurEnquiry/{id}")
	public ResponseEntity<Pur_Enquiry> updatePurEnquiry(@PathVariable(value="id") long id,@Valid @RequestBody Pur_Enquiry iMaster)
	{
		Pur_Enquiry op=pur_EnquiryService.update(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	

	@GetMapping("/getEnqSequenceId/{prefix}/{orderdate}/{type}")
	public SequenceIdDTO getEnqSequenceId(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate,@PathVariable(value = "type") String type)
	{
		return pur_EnquiryService.getEnqSequenceId(prefix,orderdate,type);
	}
	
	
	/******************* Purchase Enquiry End **********************/
	
	/******************* Purchase L1 Selection Start  **********************/
	
	@Autowired
	Pur_L1_SelectionService_Imp pur_L1_SelectionService_Imp;
	
	/* Create Purchase Selection */ 
	@PostMapping("/createPurchaseL1Selection")
	public Pur_L1_Selection createPurchaseL1Selection(@Valid @RequestBody Pur_L1_Selection pur_L1_Selection)
	{
		return pur_L1_SelectionService_Imp.save(pur_L1_Selection);
	}
	
	/* Get All Selections */
	@GetMapping("/getPurchaseL1Selection")
	public List<Pur_L1_Selection> getAllL1Selection()
	{
		return pur_L1_SelectionService_Imp.findAll();
	}
	
	/******************* Purchase L1 Selection End  **********************/
	
	/******************* Purchase Quotation Start  **********************/
	
	@Autowired
	Pur_QuotationService pur_QuotationService;
	
	@GetMapping("/getQuotSequenceId/{prefix}/{orderdate}/{type}")
	public SequenceIdDTO getQuotSequenceId(@PathVariable(value = "prefix") String prefix,@PathVariable(value = "orderdate") String orderdate,@PathVariable(value = "type") String type)
	{
		return pur_QuotationService.getQuotSequenceId(prefix,orderdate,type);
	}
	
	/* Create For Purchase Quotation */
	@PostMapping("/createPurchaseQuotation")
	public Pur_Quotation createPurchaseQuotation(@Valid @RequestBody Pur_Quotation pur_Quotation)
	{
		return pur_QuotationService.save(pur_Quotation);
	}
	
	/* Get All Quotations */
	@GetMapping("/getPurchaseQuotations")
	public List<Pur_Quotation> getAllQuotations()
	{
		return pur_QuotationService.findAll();
	}
	
	@PutMapping("/updatePurQuotation/{id}")
	public ResponseEntity<Pur_Quotation> updateItemMaster(@PathVariable(value="id") long id,@Valid @RequestBody Pur_Quotation iMaster)
	{
		Pur_Quotation op=pur_QuotationService.update(iMaster,id);
		return ResponseEntity.ok().body(op);
	}
	
	
	/******************* Purchase Quotation End  **********************/
	
	
/******************* Purchase  Quality Check  Start  **********************/
	
	@Autowired
	Pur_Quality_CheckService pur_Quality_CheckService;
	
	@GetMapping("/getQClist/{finyear}")
	public List<Map<String, Object>> getQClist(@PathVariable(value = "finyear") String finyear)
	{
		return pur_Quality_CheckService.getQClist(finyear);
	}
	
	@PostMapping("/createPurchaseQualityCheck")
	public Pur_Quality_Check createPurchaseQualityCheck(@Valid @RequestBody Pur_Quality_Check pqc)
	{
		return 	pur_Quality_CheckService.save(pqc);
	}
	
	@GetMapping("/getPurchaseQualityCheck")
	public List<Pur_Quality_Check> getAllPurchaseQuality() {
		
		return pur_Quality_CheckService.findAll();
	}
	
	@GetMapping("/retriveQualityCheck/{id}")
	public ResponseEntity<Pur_Quality_Check> retriveQualityCheck(@PathVariable(value="id") Long id )
	{
		Pur_Quality_Check pqc=pur_Quality_CheckService.findOne(id);
		if(pqc == null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(pqc);
		}
	}
	
	@GetMapping("/retriveQualityCheckDetails/{quality_check_id}")
	public List<Pur_Quality_Check_Details> retriveQualityCheckDetails(@PathVariable(value="quality_check_id") String quality_check_id)
	{
		return pur_Quality_CheckService.retriveQualityCheckDetails(quality_check_id);
	}
	
	@GetMapping("/retriveQualityCheckDetailsQcDetails/{qcno}/{qcid}")
	public List<Pur_Quality_Check_Details_QcDetails> retriveQualityCheckDetailsQcDetails(@PathVariable(value="qcno") String qcno,@PathVariable(value="qcid") String qcid)
	{
		return pur_Quality_CheckService.retriveQualityCheckDetailsQcDetails(qcno,qcid);
	}
	
	@PutMapping("/updatePurchaseQualityCheck/{id}")
	public ResponseEntity<Pur_Quality_Check> updatePurchaseQualityCheck(@Valid @RequestBody Pur_Quality_Check pqc,@PathVariable(value="id") Long id)
	{
		Pur_Quality_Check updatePQC=pur_Quality_CheckService.update(pqc, id);
		return ResponseEntity.ok().body(updatePQC);
	}
	
	@PutMapping("/deletePurchaseQualityCheck/{id}")
	public ResponseEntity<Pur_Quality_Check> deletePurchaseQualityCheck(@Valid @RequestBody Pur_Quality_Check pqc,
																		@PathVariable(value="id") Long id)
	{
		Pur_Quality_Check deletePQC=pur_Quality_CheckService.delete(pqc, id);
		return ResponseEntity.ok().body(deletePQC);
	}
	
	@GetMapping("/searchQC/{fromdate}/{todate}/{vehicle}/{finyear}")
	public List<Map<String, Object>> searchQC(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,
			@PathVariable(value = "vehicle") String vehicle,@PathVariable(value = "finyear") String finyear)
	{
		return pur_Quality_CheckService.searchQC(fromdate,todate,vehicle,finyear);
	}
	
	@GetMapping("/checkQC/{advice_id}")
	public StatusDTO checkQC(@PathVariable(value= "advice_id") String advice_id)
	{
		StatusDTO checkQC = pur_Quality_CheckService.checkQC(advice_id);
		return checkQC;
	}

	
	/******************* Purchase Quality Check Start  **********************/
	
	/******************* Purchase Good Receipt Start  **********************/
	
	@Autowired
	Pur_good_receiptService pur_good_receiptService;

	@GetMapping("/getGRNList/{currDate}/{finyear}")
	public List<Pur_good_receiptDTO> getGRNList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return pur_good_receiptService.getGRNList(currDate,finyear);
	}
	
	@GetMapping("/getGRNListData/{currDate}/{finyear}")
	public List<Map<String, Object>> getGRNListData(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return pur_good_receiptService.getGRNListData(currDate,finyear);
					
	}
	
	@GetMapping("/getGRNSequenceId/{bunit}/{itype}/{ptype}/{psubtype}/{orderdate}")
	public SequenceIdDTO getGRNSequenceId(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "itype") boolean itype,@PathVariable(value = "ptype") String ptype,@PathVariable(value = "psubtype") String psubtype,@PathVariable(value = "orderdate") String orderdate)
	{
		return pur_good_receiptService.getGRNSequenceId(bunit,itype,ptype,psubtype,orderdate);
	}
	
	@PostMapping("/createPurchaseGoodReceipt")
	public Pur_good_receipt createPurchaseGoodReceipt(@Valid @RequestBody Pur_good_receipt pgc)
	{
		return pur_good_receiptService.save(pgc);
	}
	
	@GetMapping("/getPurchaseGoodReceipt")
	public List<Pur_good_receipt> getAllPurchaseGoodReceipt() {
		
		return pur_good_receiptService.findAll();
	}
	
	@GetMapping("/getPurGRNPagination/{page}/{size}")
	public Page<Pur_good_receipt_Pagination_DTO> getPurGRNPagination(@PathVariable(value = "page") int page,@PathVariable(value = "size") int size) 
	{
		  return pur_good_receiptService.getPurGRNPagination(page,size);
	}
	
	
	@GetMapping("/purchasechecktotaltranslimit/{totalamount}/{supplier_name}/{finyear}")
	public StatusDTO purchasechecktotaltranslimit(@PathVariable(value = "totalamount") double totalamount,@PathVariable(value = "supplier_name") String supplier_name,@PathVariable(value = "finyear") String finyear) 
	{
		  return pur_good_receiptService.purchasechecktotaltranslimit(totalamount,supplier_name,finyear);
	}
	
	
	@GetMapping("/purchasechecktotaltranslimitupdate/{totalamount}/{supplier_name}/{finyear}/{id}")
	public StatusDTO purchasechecktotaltranslimitupdate(@PathVariable(value = "totalamount") double totalamount,@PathVariable(value = "supplier_name") String supplier_name,@PathVariable(value = "finyear") String finyear,@PathVariable(value="id") Long id) 
	{
		  return pur_good_receiptService.purchasechecktotaltranslimitupdate(totalamount,supplier_name,finyear,id);
	}
	
	 /*********************************************************************************grn purbill report***********************************/
	 
	 
	@GetMapping("/getgrnpurbillReport/{fromdate}/{todate}")
	public List<Map<String, Object>> getgrnpurbillReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate)
	{
		return pur_good_receiptService.getgrnpurbillReport(fromdate,todate);
	}
 
	 
	 
	 /*********************************************************************************grn purbill report end***********************************/
	
	@GetMapping("/gettaxcodefromgrn/{itemcode}/{grnid}")
	public Pur_good_receipt_item_details gettaxcodefromgrn(@PathVariable(value = "itemcode") String itemcode,@PathVariable(value = "grnid") String grnid) 
	{
		  return pur_good_receiptService.gettaxcodefromgrn(itemcode,grnid);
	}
	
	@GetMapping("/gettaxcodefromgrnnew/{itemcode}/{grnid}/{packingcode}")
	public Pur_good_receipt_item_details gettaxcodefromgrnnew(@PathVariable(value = "itemcode") String itemcode,@PathVariable(value = "grnid") String grnid,@PathVariable(value = "packingcode") String packingcode) 
	{
		  return pur_good_receiptService.gettaxcodefromgrnnew(itemcode,grnid,packingcode);
	}
	
	@GetMapping("/gettaxcodefromgrnnewMulti/{itemcode}/{grnid}/{packingcode}")
	public List<Pur_good_receipt_item_details> gettaxcodefromgrnnewMulti(@PathVariable(value = "itemcode") String itemcode,@PathVariable(value = "grnid") String grnid,@PathVariable(value = "packingcode") String packingcode) 
	{
		  return pur_good_receiptService.gettaxcodefromgrnnewMulti(itemcode,grnid,packingcode);
	}
	
	@GetMapping("/gettaxcodefromgrnnewForStore/{itemcode}/{grnid}/{packingcode}/{classified}")
	public Pur_good_receipt_item_details gettaxcodefromgrnnewForStore(@PathVariable(value = "itemcode") String itemcode,@PathVariable(value = "grnid") String grnid,@PathVariable(value = "packingcode") String packingcode,@PathVariable(value = "classified") String classified) 
	{
		  return pur_good_receiptService.gettaxcodefromgrnnewForStore(itemcode,grnid,packingcode,classified);
	}
	
 @GetMapping(value = "/searchGRN")
	public List<Pur_good_receipt_Pagination_DTO> searchGRN(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String supplier_name1,@RequestParam(defaultValue = "") String pur_type1,@RequestParam(defaultValue = "") String finyear)
	 {
		return pur_good_receiptService.searchGRN(orderno,fromdate,todate,supplier_name1,pur_type1,finyear);
	 }
	
	@GetMapping(value = "/searchGRNFast")
	public List<Map<String,Object>> searchGRNFast(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String supplier_name1,@RequestParam(defaultValue = "") String pur_type1,@RequestParam(defaultValue = "") String finyear)
	 {
		return pur_good_receiptService.searchGRNFast(orderno,fromdate,todate,supplier_name1,pur_type1,finyear);
	 }
	
	@GetMapping("/retrivePurchaseGoodReceipt/{id}")
	public ResponseEntity<Pur_good_receipt> getPGRById(@PathVariable(value="id") Long id )
	{
		Pur_good_receipt pqc=pur_good_receiptService.findOne(id);
		if(pqc == null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(pqc);
		}
	}
	
	@GetMapping("/retrivePurchaseGoodReceiptPopup/{id}")
	public ResponseEntity<Pur_Order> retrivePurchaseGoodReceiptPopup(@PathVariable(value="id") Long id )
	{
		Pur_Order pqc=pur_OrderService.retrivePurchaseGoodReceiptPopup(id);
		if(pqc == null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(pqc);
		}
	}
	
	@GetMapping("/retrivePurchaseGRNUnloadAdvicePopup/{id}")
	public ResponseEntity<Wm_unload_advice> retrivePurchaseGRNUnloadAdvicePopup(@PathVariable(value="id") Long id )
	{
		Wm_unload_advice pqc=wm_unload_adviceService.retrivePurchaseGRNUnloadAdvicePopup(id);
		if(pqc == null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(pqc);
		}
	}
	
	
	@GetMapping("/retrivePurchaseGRNMultipleUnloadAdvicePopup/{id}")
	public List<Wm_unload_advice> retrivePurchaseGRNMultipleUnloadAdvicePopup(@PathVariable(value="id") Long id )
	{
		return wm_unload_adviceService.retrivePurchaseGRNMultipleUnloadAdvicePopup(id);
		
	}
	
	@GetMapping("/retriveGRNItemFormultiple/{id}")
	public List<Wm_unload_advice_item_dtls> retriveGRNItemFormultiple(@PathVariable(value="id") Long id )
	{
		return wm_unload_adviceService.retriveGRNItemFormultiple(id);
		
	}
	
	@PutMapping("/updatePurchaseGoodReceipt/{id}")
	public ResponseEntity<Pur_good_receipt> updatepurGRS(@Valid @RequestBody Pur_good_receipt pqc,@PathVariable(value="id") Long id)
	{
		Pur_good_receipt updatePQC=pur_good_receiptService.update(pqc, id);
		return ResponseEntity.ok().body(updatePQC);
	}
	
	@PutMapping("/deleteGRN/{id}")
	public ResponseEntity<Pur_good_receipt> deleteGRN(@PathVariable(value="id") long id,@Valid @RequestBody Pur_good_receipt grnid)
	{
		Pur_good_receipt op=pur_good_receiptService.deleteGRN(grnid,id);
		return ResponseEntity.ok().body(op);
	}
	
	//It will be change leter *********************************
	
	@GetMapping("/getPurGRptRtnApp")
	public ResponseEntity<List<Pur_good_receiptDTO>> getPurGRptRtnApp(@RequestParam(defaultValue = "") String bunit,
			@RequestParam(defaultValue = "") String supplier,@RequestParam(defaultValue = "") String tdate,
			@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear)
	{
		List<Pur_good_receiptDTO> list = pur_good_receiptService.getPurGRptRtnApp(bunit,supplier,tdate,company,finyear);
		return new ResponseEntity<List<Pur_good_receiptDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	
/******************* Purchase Good Receipt  End  **********************/
	
/******************* Purchase Peripheral Quality Check Start  **********************/
	
	@Autowired
	Pur_Peri_Quality_CheckService pur_Peri_Quality_CheckService;
	
	@PostMapping("/createPurPeriQualityCheck")
	public Pur_Peri_Quality_Check createPurPeriQualityCheck(@Valid @RequestBody Pur_Peri_Quality_Check pur_Peri_Quality_Check)
	{
		return pur_Peri_Quality_CheckService.save(pur_Peri_Quality_Check);
	}
	
	@GetMapping("/getPurPeriQualityCheck")
	public List<Pur_Peri_Quality_Check> findAll()
	{
		return pur_Peri_Quality_CheckService.findAllPeripheral();
	}
	
	@GetMapping("/retrivePeripheralQ/{id}")
	public ResponseEntity<Pur_Peri_Quality_Check> getPQById(@PathVariable(value="id") Long id )
	{
		Pur_Peri_Quality_Check pqc=pur_Peri_Quality_CheckService.findOne(id);
		if(pqc == null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(pqc);
		}
	}
	
	/******************* Purchase Peripheral Quality Check End  **********************/
	
	/******************* Purchase Bill Start  **********************/
	@Autowired
	Pur_BillService pur_BillService;
	
	@GetMapping("/getpurBillDataList/{currDate}/{finyear}")
	public List<Pur_BillDTO> getpurBillDataList(@PathVariable(value = "currDate") String currDate,@PathVariable(value = "finyear") String finyear)
	{
		return pur_BillService.getpurBillDataList(currDate,finyear);
	}
	@GetMapping("/getpurBillInvTaxSum/{purbillid}")
	public List<Purchase_item_groupwise_taxsumm> getpurBillInvTaxSum(@PathVariable(value = "purbillid") String purbillid)
	{
		return pur_BillService.getpurBillInvTaxSum(purbillid);
	}
	
	@GetMapping("/getPBSequenceId/{orderdate}/{itype}/{ptype}/{psubtype}")
	public SequenceIdDTO getPBSequenceId(@PathVariable(value = "orderdate") String orderdate,@PathVariable(value = "itype") boolean itype,@PathVariable(value = "ptype") String ptype,@PathVariable(value = "psubtype") String psubtype)
	{
		return pur_BillService.getPBSequenceId(orderdate,itype,ptype,psubtype);
	}
	
	@PostMapping("/createPurchaseBill")
	public Pur_Bill createPurchaseBill(@Valid @RequestBody Pur_Bill pur_Bill) {
		return pur_BillService.save(pur_Bill);
	}
	
	/* get all Bill */
	@GetMapping("/getPurchaseBill")
	public List<Pur_BillDTO> getAllBill() {
		
		return pur_BillService.findAllBill();
	}
	
	 @GetMapping("/getPurBillPagination/{page}/{size}")
	  public Page<Pur_Bill_Pagination_DTO> getPurBillPagination(@PathVariable(value = "page") int page,@PathVariable(value = "size") int size) 
	{
		  return pur_BillService.getPurBillPagination(page,size);
	}
	
  @GetMapping(value = "/searchPurBillFast")
	public List<Map<String,Object>> searchPurBillFast(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String supplier_name1,@RequestParam(defaultValue = "") String pur_type1,@RequestParam(defaultValue = "") String finyear)
	 {
		return pur_BillService.searchPurBillFast(orderno,fromdate,todate,supplier_name1,pur_type1,finyear);
	 }
	
  @GetMapping(value = "/searchPurBill")
	public List<Pur_Bill_Pagination_DTO> searchPurBill(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String supplier_name1,@RequestParam(defaultValue = "") String pur_type1,@RequestParam(defaultValue = "") String finyear)
	 {
		return pur_BillService.searchPurBill(orderno,fromdate,todate,supplier_name1,pur_type1,finyear);
	 }
	 /*@GetMapping(value = "/searchPurBill")
		public List<Pur_BillDTO> searchPurBill(@RequestParam(defaultValue = "") String orderno,@RequestParam(defaultValue = "") String fromdate,
				@RequestParam(defaultValue = "") String todate,@RequestParam(defaultValue = "") String supplier_name1,@RequestParam(defaultValue = "") String finyear)
		 {
			return pur_BillService.searchPurBill(orderno,fromdate,todate,supplier_name1,finyear);
		 }*/
		  
	@PutMapping("/deletePurchaseBill/{id}")
	public ResponseEntity<Pur_Bill> deletePurchaseBill(@PathVariable(value="id") long id,@Valid @RequestBody Pur_Bill purbillid)
	{
		Pur_Bill op=pur_BillService.deletePurchaseBill(purbillid,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getChargesMatrixdetails/{unloadid}")
	public List<Pur_Order_app_chgs> getChargesMatrixdetails(@PathVariable(value="unloadid") String unloadid) {
		
		return pur_BillService.getChargesMatrixdetails(unloadid);
	}
	
	@GetMapping("/getChargesapplication/{grnid}")
	public List<Pur_Order_app_chgs> getChargesapplication(@PathVariable(value="grnid") String grnid) {
		
		return pur_BillService.getChargesapplication(grnid);
	}
	
	@PutMapping("/updatePurBill/{id}")
	public ResponseEntity<Pur_Bill> updatePurBill(@PathVariable(value="id") long id,@Valid @RequestBody Pur_Bill pur_Bill)
	{
		Pur_Bill op=pur_BillService.update(pur_Bill,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getPurBillDetails")
	public ResponseEntity<Pur_BillDTO> getPurBillDetails(@RequestParam(defaultValue = "") String pbillid,
			@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear)
	{
		Pur_BillDTO list = pur_BillService.getPurBillDetails(pbillid,company,finyear);
		return new ResponseEntity<Pur_BillDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getPaymentStatus")
	public ResponseEntity<List<Pur_BillDTO>> getPaymentStatus(@RequestParam(defaultValue = "") String fromdate,
			@RequestParam(defaultValue = "") String todate)
	{
		List<Pur_BillDTO> list = pur_BillService.getPaymentStatus(fromdate,todate);
		return new ResponseEntity<List<Pur_BillDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	//It will be change with type & subtype ********************************
	
	@GetMapping("/getPurBillRtnApp")
	public ResponseEntity<List<Pur_BillDTO>> getPurBillRtnApp(
			@RequestParam(defaultValue = "") String supplier,@RequestParam(defaultValue = "") String tdate,
			@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear)
	{
		List<Pur_BillDTO> list = pur_BillService.getPurBillRtnApp(supplier,tdate,company,finyear);
		return new ResponseEntity<List<Pur_BillDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/accountpostingPurchaseBill/{id}")
	public ResponseEntity<Pur_Bill> accountpostingPurchaseBill(@PathVariable(value = "id") Long id) {
		Pur_Bill op = pur_BillService.accountpostingPurchaseBill(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/accountpostingPurchaseBillundo/{id}")
	public Pur_Bill accountpostingPurchaseBillundo(@PathVariable(value = "id") Long id) {
		
			return pur_BillService.accountpostingPurchaseBillundo(id);
		
	}
	
	@GetMapping("/getPurBillNewReport/{fromdate}/{todate}/{finyear}/{po_type}/{supplier_name}")
    public List<Map<String, Object>> getPurBillNewReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,
    		@PathVariable(value = "finyear") String finyear,@PathVariable(value = "po_type") String po_type,@PathVariable(value = "supplier_name") String supplier_name) 
	{
		return pur_BillService.getPurBillNewReport(fromdate,todate,finyear,po_type,supplier_name);
	}
	
	@GetMapping("/getPurBillbalanceNewReport/{fromdate}/{todate}/{supplier_name}/{finyear}/{po_type}")
    public List<Map<String, Object>> getPurBillbalanceNewReport(@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate,@PathVariable(value = "supplier_name") String supplier_name,@PathVariable(value = "finyear") String finyear,@PathVariable(value = "po_type") String po_type) 
	{
		return pur_BillService.getPurBillbalanceNewReport(fromdate,todate,supplier_name,finyear,po_type);
	}
	
	
	@GetMapping("/getSuppliertdsStatDtls/{suppid}/{financial_year}")
	public Map<String, Object> getSuppliertdsStatDtls(@PathVariable(value = "suppid") String suppid,@PathVariable(value = "financial_year") String financial_year)
	{
		return 	pur_BillService.getSuppliertdsStatDtls(suppid,financial_year);
	}
	
	/******************* Purchase Bill End  **********************/
	
	
	/******************* Purchase Return App Start **********************/
	@Autowired
	Pur_return_approval_noteService pur_return_approval_noteService;

	@GetMapping("/getPRANSequenceId/{rtntype}/{rtndate}")
	public SequenceIdDTO getPRANSequenceId(@PathVariable(value = "rtntype") String rtntype,
			@PathVariable(value = "rtndate") String rtndate)
	{
		return pur_return_approval_noteService.getPRANSequenceId(rtntype,rtndate);
	}
	
	@PostMapping("/createPurReturnAppNote")
	public Pur_return_approval_note createPurReturnApprovalNote(@Valid @RequestBody Pur_return_approval_note pur_return_approval_note) {
		return pur_return_approval_noteService.save(pur_return_approval_note);
	}
  
    @GetMapping("/getPurReturnAppNote")
    public ResponseEntity<List<Pur_return_approval_note>> getPurReturnApprovalNote(@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear)
    {
    	List<Pur_return_approval_note> list = pur_return_approval_noteService.getPurReturnApprovalNote(company,finyear);
    	
	    return new ResponseEntity<List<Pur_return_approval_note>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/getPurRtnAppNoteForLA")
    public ResponseEntity<List<Pur_return_approval_noteDTO>> getPurRtnAppNoteForLA(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String supplier,@RequestParam(defaultValue = "") String tdate,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear)
    {
    	List<Pur_return_approval_noteDTO> list = pur_return_approval_noteService.getPurRtnAppNoteForLA(bunit,supplier,tdate,company,finyear);
    	
	    return new ResponseEntity<List<Pur_return_approval_noteDTO>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/getPurRtnAppNoteThruWe")
    public ResponseEntity<List<Pur_return_approval_noteDTO>> getPurRtnAppNoteThruWe(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String supplier,@RequestParam(defaultValue = "") String tdate,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear)
    {
    	List<Pur_return_approval_noteDTO> list = pur_return_approval_noteService.getPurRtnAppNoteThruWe(bunit,supplier,tdate,company,finyear);
    	
	    return new ResponseEntity<List<Pur_return_approval_noteDTO>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/getPurRtnAppNoteLowRate")
    public ResponseEntity<List<Pur_return_approval_noteDTO>> getPurRtnAppNoteLowRate(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String supplier,@RequestParam(defaultValue = "") String tdate,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear)
    {
    	List<Pur_return_approval_noteDTO> list = pur_return_approval_noteService.getPurRtnAppNoteLowRate(bunit,supplier,tdate,company,finyear);
    	
	    return new ResponseEntity<List<Pur_return_approval_noteDTO>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    	  
    @GetMapping("/retrivePurReturnAppNote/{id}")
	public ResponseEntity<Pur_return_approval_note> retrivePurReturnApprovalNote(@PathVariable(value = "id") Long id) {
		Pur_return_approval_note op = pur_return_approval_noteService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/getPurRtnAppNoteDtls")
    public ResponseEntity<Pur_return_approval_noteDTO> getPurRtnAppNoteDtls(@RequestParam(defaultValue = "0") String purreturnid)
    {
    	Pur_return_approval_noteDTO list = pur_return_approval_noteService.getPurRtnAppNoteDtls(purreturnid);
    	
	    return new ResponseEntity<Pur_return_approval_noteDTO>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping("/getPurReturnAppDoc/{purreturnid}")
	public List<Pur_return_approval_docsDTO> getPurReturnApprovalDoc(@PathVariable(value = "purreturnid") String purreturnid)
	{
		return pur_return_approval_noteService.getPurReturnApprovalDoc(purreturnid);
	}
	
	@GetMapping("/getPurReturnAppID/{purreturnid}")
	public List<Pur_return_approval_item_dtlsDTO> getPurReturnAppID(@PathVariable(value = "purreturnid") String purreturnid)
	{
		return pur_return_approval_noteService.getPurReturnApprovalID(purreturnid);
	}
	
	@GetMapping("/getPurReturnAppBD/{purreturnid}")
	public List<Pur_return_approval_broker_dtlsDTO> getPurReturnApprovalBD(@PathVariable(value = "purreturnid") String purreturnid)
	{
		return pur_return_approval_noteService.getPurReturnApprovalBD(purreturnid);
	}
	
	@GetMapping("/getPurReturnAppTD/{purreturnid}")
	public List<Pur_return_approval_trans_dynDTO> getPurReturnApprovalTD(@PathVariable(value = "purreturnid") String purreturnid)
	{
		return pur_return_approval_noteService.getPurReturnApprovalTD(purreturnid);
	}
	
	@GetMapping("/getPurReturnAppWD/{purreturnid}")
	public Pur_return_approval_weight_dtlDTO getPurReturnApprovalWD(@PathVariable(value = "purreturnid") String purreturnid)
	
	{
		return pur_return_approval_noteService.getPurReturnApprovalWD(purreturnid);
	}
	
	@GetMapping("/getPurReturnAppTI/{purreturnid}")
	public Pur_return_approval_trans_infoDTO getPurReturnApprovalTI(@PathVariable(value = "purreturnid") String purreturnid)
	{
		return pur_return_approval_noteService.getPurReturnApprovalTI(purreturnid);
	}
	
	@GetMapping("/getPurReturnAppSD/{purreturnid}")
	public Pur_return_approval_shipment_dtlsDTO getPurReturnApprovalSD(@PathVariable(value = "purreturnid") String purreturnid)
	{
		return pur_return_approval_noteService.getPurReturnApprovalSD(purreturnid);
	}
	
	
	@GetMapping("/getPurReturnAppSuppDtls/{purreturnid}")
	public Pur_return_approval_supplier_dtlsDTO getPurReturnApprovalSuD(@PathVariable(value = "purreturnid") String purreturnid)
	{
		return pur_return_approval_noteService.getPurReturnApprovalSuD(purreturnid);
	}
	
	@PutMapping("/updatePurReturnAppNote/{id}") 
    public ResponseEntity<Pur_return_approval_note> updatePurReturnApprovalNote(@PathVariable(value="id") long id,@Valid @RequestBody Pur_return_approval_note pur_return_approval_note)
    {
    	Pur_return_approval_note op=pur_return_approval_noteService.update(pur_return_approval_note,id);
	    return ResponseEntity.ok().body(op); 
    }
 
    @GetMapping("/getReturnApprovalPopupData/{date}/{bunit}/{supplier}/{returnbasedon}/{finyear}/{compid}")
    public List<Map<String,Object>> getReturnApprovalPopupData(@PathVariable(value = "date") String date,
    		@PathVariable(value = "bunit") String bunit,@PathVariable(value = "supplier") String supplier,
    		@PathVariable(value = "returnbasedon") String returnbasedon,@PathVariable(value = "finyear") String finyear,
    		@PathVariable(value = "compid") String compid)
	{
		return pur_return_approval_noteService.getReturnApprovalPopupData(date,bunit,supplier,returnbasedon,finyear,compid);
	}
    
    
    @GetMapping("/getgrnlistbypurorder/{pur_orderid}")
    public List<Map<String,Object>> getgrnlistbypurorder(@PathVariable(value = "pur_orderid") String pur_orderid)
	{
		return pur_return_approval_noteService.getgrnlistbypurorder(pur_orderid);
	}
    
    @GetMapping("/getgrnitemlist/{grnlist}")
    public List<Map<String,Object>> getgrnitemlist(@PathVariable(value = "grnlist") String grnlist)
	{
		return pur_return_approval_noteService.getgrnitemlist(grnlist);
	}
    
    /******************* PURCHES Return End **********************/
	
    /**************************Start Purchase Return Note***************************/
    
    @Autowired
    Pur_return_noteService pur_return_noteService;
    
    @GetMapping("/getPRNSequenceId/{prdate}")
	public SequenceIdDTO getPRNSequenceId(@PathVariable(value = "prdate") String prdate)
	{
		return pur_return_noteService.getPRNSequenceId(prdate);
	}
    
    @PostMapping("/createPurReturnNote")
	public Pur_return_note createPurReturnNote(@Valid @RequestBody Pur_return_note pur_return_note) {
		return pur_return_noteService.save(pur_return_note);
	}
    
    @GetMapping("/getPurReturnNote/{company}/{finyear}")
	public List<Pur_return_note> getPurReturnNote(@PathVariable(value = "company") String company,@PathVariable(value = "finyear") String finyear) {
		return pur_return_noteService.findAllRtnNotes(company,finyear);
	}
	
	@GetMapping("/retrivePurReturnNote/{id}")
	public ResponseEntity<Pur_return_note> retrivePurReturnNote(@PathVariable(value = "id") Long id) {
		Pur_return_note op = pur_return_noteService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/getPurRtnNoteDtls/{purreturnnoteid}")
	public Pur_return_noteDTO getPurRtnNoteDtls(@PathVariable(value = "purreturnnoteid") String purreturnnoteid)
	{
		return pur_return_noteService.getPurRtnNoteDtls(purreturnnoteid);
	}
	
	@GetMapping("/getPurReturnNotes")
    public ResponseEntity<List<Pur_return_noteDTO>> getPurReturnNotes(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String invdate,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear)
    {
    	List<Pur_return_noteDTO> list = pur_return_noteService.getPurReturnNotes(bunit,invdate,company,finyear);
    	
	    return new ResponseEntity<List<Pur_return_noteDTO>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
	@GetMapping("/getPurRtnNoteItemDtls/{purreturnnoteid}")
	public List<Pur_return_note_item_dtlsDTO> getPurRtnNoteItemDtls(@PathVariable(value = "purreturnnoteid") String purreturnnoteid)
	{
		return pur_return_noteService.getPurRtnNoteItemDtls(purreturnnoteid);
	}
	
	@GetMapping("/getPurRtnNoteBrokerDtls/{purreturnnoteid}")
	public List<Pur_return_note_broker_dtlsDTO> getPurRtnNoteBrokerDtls(@PathVariable(value = "purreturnnoteid") String purreturnnoteid)
	{
		return pur_return_noteService.getPurRtnNoteBrokerDtls(purreturnnoteid);
	}
	
	@GetMapping("/getPurRtnNoteDocs/{purreturnnoteid}")
	public List<Pur_return_note_docsDTO> getPurRtnNoteDocs(@PathVariable(value = "purreturnnoteid") String purreturnnoteid)
	{
		return pur_return_noteService.getPurRtnNoteDocs(purreturnnoteid);
	}
	
	@GetMapping("/getPurRtnNoteTransDyn/{purreturnnoteid}")
	public List<Pur_return_note_trans_dynDTO> getPurRtnNoteTransDyn(@PathVariable(value = "purreturnnoteid") String purreturnnoteid)
	{
		return pur_return_noteService.getPurRtnNoteTransDyn(purreturnnoteid);
	}
	
	@GetMapping("/getPurRtnNoteTransInfo/{purreturnnoteid}")
	public Pur_return_note_trans_infoDTO getPurRtnNoteTransInfo(@PathVariable(value = "purreturnnoteid") String purreturnnoteid)
	{
		return pur_return_noteService.getPurRtnNoteTransInfo(purreturnnoteid);
	}
	
	@GetMapping("/getPurRtnNoteWeDtls/{purreturnnoteid}")
	public Pur_return_note_weight_dtlDTO getPurRtnNoteWeDtls(@PathVariable(value = "purreturnnoteid") String purreturnnoteid)
	{
		return pur_return_noteService.getPurRtnNoteWeDtls(purreturnnoteid);
	}
	
	@GetMapping("/getPurRtnNoteShipDtls/{purreturnnoteid}")
	public Pur_return_note_shipment_dtlsDTO getPurRtnNoteShipDtls(@PathVariable(value = "purreturnnoteid") String purreturnnoteid)
	{
		return pur_return_noteService.getPurRtnNoteShipDtls(purreturnnoteid);
	}
	
	@GetMapping("/getPurRtnNoteSuppDtls/{purreturnnoteid}")
	public Pur_return_note_supplier_dtlsDTO getPurRtnNoteSuppDtls(@PathVariable(value = "purreturnnoteid") String purreturnnoteid)
	{
		return pur_return_noteService.getPurRtnNoteSuppDtls(purreturnnoteid);
	}
	
	@PutMapping("/updatePurReturn/{id}") 
    public ResponseEntity<Pur_return_note> updatePurReturn(@PathVariable(value="id") long id,@Valid @RequestBody Pur_return_note pur_return_note)
    {
    	Pur_return_note op=pur_return_noteService.update(pur_return_note,id);
	    return ResponseEntity.ok().body(op); 
    }
    
    /**************************End Purchase Return Note***************************/
	
	
    /**************************Start Purchase Debit Note***************************/
	@Autowired
	Pur_debit_noteService pur_debit_noteService;
	
	@GetMapping("/getDNSequenceId/{dndate}/{dntype}")
	public SequenceIdDTO getDNSequenceId(@PathVariable(value = "dndate") String dndate,@PathVariable(value = "dntype") String dntype)
	{
		return pur_debit_noteService.getDNSequenceId(dndate,dntype);
	}
	    
	@PostMapping("/createPurDebitNote")
	public Pur_debit_note createPurDebitNote(@Valid @RequestBody Pur_debit_note pur_debit_note) {
		return pur_debit_noteService.save(pur_debit_note);
	}
	
	@GetMapping("/retrivePurDebitNote/{id}")
	public ResponseEntity<Pur_debit_note> retrivePurDebitNote(@PathVariable(value = "id") Long id) {
		Pur_debit_note op = pur_debit_noteService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	    
	@GetMapping("/getPurDebitNote/{company}/{finyear}")
	public List<Pur_debit_note> getPurDebitNote(@PathVariable(value = "company") String company,@PathVariable(value = "finyear") String finyear) {
		return pur_debit_noteService.findAllDNotes(company,finyear);
	}
		
	@GetMapping("/getPurDebitNoteItemDtls/{debitnoteid}")
	public List<Pur_debit_note_item_detailsDTO> getPurDebitNoteItemDtls(@PathVariable(value = "debitnoteid") String debitnoteid)
	{
		return pur_debit_noteService.getPurDebitID(debitnoteid);
	}
	
	@GetMapping("/getPurDebitNoteMstDtls/{debitnoteid}")
	public List<Pur_debit_note_musterroll_detailsDTO> getPurDebitNoteMstDtls(@PathVariable(value = "debitnoteid") String debitnoteid)
	{
		return pur_debit_noteService.getPurDebitMD(debitnoteid);
	}
	
	@GetMapping("/getPurDebitNoteBrokerDtls/{debitnoteid}")
	public List<Pur_debit_note_broker_detailsDTO> getPurDebitNoteBrokerDtls(@PathVariable(value = "debitnoteid") String debitnoteid)
	{
		return pur_debit_noteService.getPurDebitBD(debitnoteid);
	}
	
	@GetMapping("/getPurDebitNoteDoc/{debitnoteid}")
	public List<Pur_debit_note_docsDTO> getPurDebitNoteDoc(@PathVariable(value = "debitnoteid") String debitnoteid)
	{
		return pur_debit_noteService.getPurDebitD(debitnoteid);
	}
	
	@GetMapping("/getPurDebitNoteTaxInfo/{debitnoteid}")
	public Pur_debit_note_tax_infoDTO getPurDebitNoteTaxInfo(@PathVariable(value = "debitnoteid") String debitnoteid)
	{
		return pur_debit_noteService.getPurDebitTI(debitnoteid);
	}
	
	@GetMapping("/getPurDebitNoteBPDtls/{debitnoteid}")
	public Pur_debit_note_bp_detailsDTO getPurDebitNoteBPDtls(@PathVariable(value = "debitnoteid") String debitnoteid)
	{
		return pur_debit_noteService.getPurDebitBPD(debitnoteid);
	}
	
	@GetMapping("/getPurDebitNoteAccInfo/{debitnoteid}")
	public Pur_debit_note_account_infoDTO getPurDebitNoteAccInfo(@PathVariable(value = "debitnoteid") String debitnoteid)
	{
		return pur_debit_noteService.getPurDebitAI(debitnoteid);
	}
	
	@PutMapping("/updatePurDebitNote/{id}") 
    public ResponseEntity<Pur_debit_note> updatePurDebitNote(@PathVariable(value="id") long id,@Valid @RequestBody Pur_debit_note pur_debit_note)
    {
    	Pur_debit_note op=pur_debit_noteService.update(pur_debit_note,id);
	    return ResponseEntity.ok().body(op); 
    }
    
    /**************************End Purchase Debit Note***************************/
    
    @GetMapping("/getpssd_packing_item_qtymultiplepopup/{unloadadvice}")
	public List<Map<String,Object>> getpssd_packing_item_qtymultiplepopup(@PathVariable(value = "unloadadvice") String unloadadvice)
	{
		return pur_OrderService.getpssd_packing_item_qtymultiplepopup(unloadadvice);	
	}
    
    @GetMapping("/getPurOrdreceipt_criteriaNew/{orderid}")
	public List<Map<String,Object>> getPurOrdreceipt_criteriaNew(@PathVariable(value = "orderid") String orderid)
	{
		return pur_OrderService.getPurOrdreceipt_criteriaNew(orderid);
	}
    
}
