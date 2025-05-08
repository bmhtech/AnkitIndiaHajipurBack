package com.AnkitIndia.jwtauthentication.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_BusinessUnit_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Docs;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_Transfer_Challan_Trans_Info;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_grn;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_bu_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_docs;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_musterroll_dtls;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_pur_inv_tax_info;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv;
import com.AnkitIndia.jwtauthentication.model.Stk_transfer_sales_inv_docs;
import com.AnkitIndia.jwtauthentication.model.Stock_Indent_Order;
import com.AnkitIndia.jwtauthentication.model.Stock_Transfer;
import com.AnkitIndia.jwtauthentication.model.Stock_reports;
import com.AnkitIndia.jwtauthentication.model.Stock_transfer_doc;
import com.AnkitIndia.jwtauthentication.model.Stocksaleitem_groupwise_hsnsumm;
import com.AnkitIndia.jwtauthentication.model.Stocksaleitem_groupwise_taxsumm;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_driver_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;
import com.AnkitIndia.jwtauthentication.security.services.Stk_Transfer_ChallanService;
import com.AnkitIndia.jwtauthentication.security.services.Stk_transfer_grnService;
import com.AnkitIndia.jwtauthentication.security.services.Stk_transfer_pur_invService;
import com.AnkitIndia.jwtauthentication.security.services.Stk_transfer_sales_invService;
import com.AnkitIndia.jwtauthentication.security.services.Stock_Indent_Order_Service;
import com.AnkitIndia.jwtauthentication.security.services.Stock_Transfer_Service;
import com.AnkitIndia.jwtauthentication.security.services.Stock_reportsService;
import com.AnkitIndia.jwtauthentication.security.services.UniqueidService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_unload_wgmntService;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Stk_Transfer_Challan_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_ChallanDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Shipment_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_Transfer_Challan_Weight_DtlDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grnDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_bu_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_docsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_other_infoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_resource_costDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_grn_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_pur_invDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_bu_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_tax_infoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stk_transfer_sales_inv_trans_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_Item_DetailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_OrderDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_TerminationsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_Terminations_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Indent_docsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_TransferDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_Item_DtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_SummaryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_Summary_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_Transfer_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_transfer_resource_costDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_transfer_terminationsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Stock_transfer_terminations_dynDTO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
//@RequestMapping("/MeghnaBidi")
//@RequestMapping("/MeghanaPanMasala")
//@RequestMapping("/IndianSpices")
public class StockController {
	
	@Autowired
	UniqueidService uniqueidService;
	
	/********************* Stock Indent Order  ************/
	
	@Autowired
	Stock_Indent_Order_Service stock_Indent_Order_Service;
	
	@GetMapping("/getSTISequenceId/{idate}")
	public SequenceIdDTO getSTISequenceId(@PathVariable(value = "idate") String idate)
	{
		return stock_Indent_Order_Service.getSTISequenceId(idate);
	}
	
	@PostMapping("/createStockIndentOrder")
	public Stock_Indent_Order createStockIndentOrder(@Valid @RequestBody Stock_Indent_Order stock_Indent_Order) {
		return stock_Indent_Order_Service.save(stock_Indent_Order);
	}

	@GetMapping("/getStockIndentOrder")
	public List<Stock_Indent_Order> findAllIndentOrders() {
		
		return stock_Indent_Order_Service.findAll();
	}
	
	@GetMapping("/retriveStkIndentOrd/{id}")
	public ResponseEntity<Stock_Indent_Order> retriveStkIndentOrd(@PathVariable(value="id") Long id)
	{
		Stock_Indent_Order sto=stock_Indent_Order_Service.findOne(id);
		if(sto==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(sto);
		}
	}
	
	@GetMapping("/getStkIndentOrdItemDtlsList/{indent_id}")
	public List<Stock_Indent_Item_DetailsDTO> getStkIndentOrderDetailsList(@PathVariable(value = "indent_id") String indent_id)
	{
		return stock_Indent_Order_Service.getStkIndentOrderDetailsList(indent_id);
	}
	
	@GetMapping("/getStkIndentDocsList/{indent_id}")
	public List<Stock_Indent_docsDTO> getStkIndentDocsList(@PathVariable(value = "indent_id") String indent_id)
	{
		return stock_Indent_Order_Service.getStkIndentDocsList(indent_id);
	}
	
	@GetMapping(value = "/getStkIndOrder")
	public ResponseEntity<List<Stock_Indent_OrderDTO>> getStkIndOrder() 
	{
		List<Stock_Indent_OrderDTO> val=stock_Indent_Order_Service.getStkIndOrder();
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getStkIndentTermDtlsList/{indent_id}")
	public List<Stock_Indent_Terminations_dynDTO> getStkIndentTermDyn(@PathVariable(value = "indent_id") String indent_id)
	{
		return stock_Indent_Order_Service.getStkIndentTermDyn(indent_id);
	}
	
	@GetMapping("/getStkIndTermDtls/{indent_id}")
	public Stock_Indent_TerminationsDTO getStkIndTer(@PathVariable(value = "indent_id") String indent_id)
	{
		return stock_Indent_Order_Service.getStkIndTer(indent_id);
	}
	
	@GetMapping("/getStkIndOrdDtls/{indent_id}")
	public Stock_Indent_OrderDTO getStkIndODR(@PathVariable(value = "indent_id") String indent_id)
	{
		return stock_Indent_Order_Service.getStkIndODR(indent_id);
	}
	
	/********************* Stock Transfer Indent Order End ********************/
	
	
	/********************* Stock Transfer Order Start *************************/
	
	@Autowired
	Stock_Transfer_Service stock_Transfer_Service;
	
	@GetMapping("/getSTOSequenceId/{sdate}/{ordpoint}")
	public SequenceIdDTO getSTOSequenceId(@PathVariable(value = "sdate") String sdate,@PathVariable(value = "ordpoint") String ordpoint)
	{
		return stock_Transfer_Service.getSTOSequenceId(sdate,ordpoint);
	}

	@PostMapping("/createStockTransfer")
	public Stock_Transfer createStockTransfer(@Valid @RequestBody Stock_Transfer stock_Transfer) {
		return stock_Transfer_Service.save(stock_Transfer);
	}
	
	@GetMapping("/stock_TransferRetrive/{id}")
	public ResponseEntity<Stock_Transfer> stock_TransferRetrive(@PathVariable(value="id") Long id)
	{
		Stock_Transfer sto=stock_Transfer_Service.findOne(id);
		if(sto==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(sto);
		}
	}

	@GetMapping("/getStockTransfer")
	public List<Stock_Transfer> findAllStockTransfers() {
		
		return stock_Transfer_Service.findAll();
	}

	@GetMapping(value = "/getStkTrans")
	public ResponseEntity<List<Stock_TransferDTO>> getStkTrans() 
	{
		List<Stock_TransferDTO> val=stock_Transfer_Service.getStkTrans();
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping(value = "/getStockTransDtls/{order_id}")
	public ResponseEntity<Stock_TransferDTO> getStockTransDtls(@PathVariable(value = "order_id") String order_id) 
	{
		Stock_TransferDTO val=stock_Transfer_Service.getStockTransDtls(order_id);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping(value = "/getStockTransThruInter")
	public ResponseEntity<List<Stock_TransferDTO>> getStockTransThruInter() 
	{
		List<Stock_TransferDTO> val=stock_Transfer_Service.getStockTransThruInter();
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping(value = "/getStockTransOrds/{tdate}/{bunit}")
	public ResponseEntity<List<Stock_TransferDTO>> getStockTransOrds(@PathVariable(value = "tdate") String tdate,@PathVariable(value = "bunit") String bunit) 
	{
		List<Stock_TransferDTO> val=stock_Transfer_Service.getStockTransOrds(tdate,bunit);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getStkTransTranInfo/{order_id}")
	public Stock_Transfer_Trans_InfoDTO getStkTransTranInfo(@PathVariable(value = "order_id") String order_id)
	{
		return stock_Transfer_Service.getStkTransTranInfo(order_id);
	}
	
	@GetMapping("/getStkTransSumm/{order_id}")
	public Stock_Transfer_SummaryDTO getStkTransSumm(@PathVariable(value = "order_id") String order_id)
	{
		return stock_Transfer_Service.getStkTransSumm(order_id);
	}
	
	@GetMapping("/getStkTraSumDyn/{order_id}")
	public List<Stock_Transfer_Summary_dynDTO> getStkTraSumDyn(@PathVariable(value = "order_id") String order_id)
	{
		return stock_Transfer_Service.getStkTraSumDyn(order_id);
	}
	
	@GetMapping("/getStockTransItemDlts/{order_id}")
	public List<Stock_Transfer_Item_DtlsDTO> getStkTraItmDlts(@PathVariable(value = "order_id") String order_id)
	{
		return stock_Transfer_Service.getStockTransItemDlts(order_id);
	}
	
	@GetMapping("/getStockTransItemDltsArmy/{order_id}")
	public List<Map<String, Object>> getStockTransItemDltsArmy(@PathVariable(value = "order_id") String order_id)
	{
		return stock_Transfer_Service.getStockTransItemDltsArmy(order_id);
	}
	
	@GetMapping("/getStockTransReCost/{order_id}")
	public List<Stock_transfer_resource_costDTO> getStockTransReCost(@PathVariable(value = "order_id") String order_id)
	{
		return stock_Transfer_Service.getStockTransReCost(order_id);
	}
	
	@GetMapping("/getStkTransTerms/{order_id}")
	public Stock_transfer_terminationsDTO getStkTransTerms(@PathVariable(value = "order_id") String order_id)
	{
		return stock_Transfer_Service.getStkTransTerms(order_id);
	}
	
	@GetMapping("/getStockTransTermDtls/{order_id}")
	public List<Stock_transfer_terminations_dynDTO> getStockTransTermDtls(@PathVariable(value = "order_id") String order_id)
	{
		return stock_Transfer_Service.getStockTransTermDtls(order_id);
	}
	
	@GetMapping("/getStockTransDoc/{order_id}")
	public List<Stock_transfer_doc> getStockTransDoc(@PathVariable(value = "order_id") String order_id)
	{
		return stock_Transfer_Service.getStockTransDoc(order_id);
	}
	
	@PutMapping("/updateStockTransOrders/{id}")
	public ResponseEntity<Stock_Transfer> updateStockTransOrders(@PathVariable(value="id") long id,@Valid @RequestBody Stock_Transfer sTransfer)
	{
		Stock_Transfer op=stock_Transfer_Service.update(sTransfer,id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteStocktransferOrder/{id}")
	public ResponseEntity<Stock_Transfer> deleteStocktransferOrder(@PathVariable(value="id") long id,@Valid @RequestBody Stock_Transfer stkOrder)
	{
		Stock_Transfer op=stock_Transfer_Service.deleteStocktransferOrder(stkOrder,id);
		return ResponseEntity.ok().body(op);
	}
	
	/********************* Stock Transfer Order End ************/
	
	/********************* Stock Transfer Challan Start ***********************/
	
	@Autowired
	Stk_Transfer_ChallanService stockTransferChallan_Service;
	
	@GetMapping("/getSTCSequenceId/{cdate}/{bunit}")
	public SequenceIdDTO getSTCSequenceId(@PathVariable(value = "cdate") String cdate,@PathVariable(value = "bunit") String bunit)
	{
		return stockTransferChallan_Service.getSTCSequenceId(cdate,bunit);
	}
	
	@PostMapping("/createStockTransferChallan")
	public Stk_Transfer_Challan createStockTransferChallan(@Valid @RequestBody Stk_Transfer_Challan stockTransferChallan) {
		return stockTransferChallan_Service.save(stockTransferChallan);
	}
	
	@GetMapping("/getStockTransferChallans")
	public List<Stk_Transfer_Challan> findAllStockTransferChallans() {
		
		return stockTransferChallan_Service.findAll();
	}
	
	@GetMapping("/getStockTransChlnById/{id}")
	public Stk_Transfer_Challan getStockTransChlnById(@PathVariable(value="id") Long id)
	{
		 return stockTransferChallan_Service.findOne(id);
		
	}
	
	@GetMapping("/getStockTransLoadingWeighmentId/{id}")
	public Wm_loading_adviceDTO getStockTransLoadingWeighmentId(@PathVariable(value="id") Long id)
	{
		return stockTransferChallan_Service.getStockTransLoadingWeighmentId(id);
	}
	
	@GetMapping(value = "/getStkTransChallans")
	public ResponseEntity<List<Stk_Transfer_ChallanDTO>> getStkTransChallans(@RequestParam(defaultValue = "") String bunit,
			@RequestParam(defaultValue = "") String invdate,@RequestParam(defaultValue = "") String comp,@RequestParam(defaultValue = "") String finyear) 
	{
		List<Stk_Transfer_ChallanDTO> val=stockTransferChallan_Service.getStkTransChallans(bunit,invdate,comp,finyear);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping(value = "/getstocktransferchallaninunloading/{bunit}/{invdate}/{comp}/{finyear}")
	
	public List<Stk_Transfer_ChallanDTO> getstocktransferchallaninunloading (@PathVariable(value = "bunit") String bunit,@PathVariable(value = "invdate") String invdate,@PathVariable(value = "comp") String comp,@PathVariable(value = "finyear") String finyear)
	{
		return stockTransferChallan_Service.getstocktransferchallaninunloading(bunit,invdate,comp,finyear);
	}
	//getmutiplegetStkTransChallans
		@GetMapping(value = "/getmutiplegetStkTransChallans/{id}")
		public List<Stk_Transfer_ChallanDTO> getmutiplegetStkTransChallans (@PathVariable(value = "id") Long id)
		{
			return stockTransferChallan_Service.getmutiplegetStkTransChallans(id);
		}
	
	@GetMapping("/getMultipleStkTransChallanDtls/{stkchlnid}")
	public List<Stk_Transfer_Challan_Item_Dtls> getMultipleStkTransChallanDtls(@PathVariable(value = "stkchlnid") String stkchlnid)
	{
		return stockTransferChallan_Service.getMultipleStkTransChallanDtls(stkchlnid);
	}
	
	@GetMapping("/getMultipleStkOrderDetails/{stk_challan_id}")
	public Stk_Transfer_Challan getMultipleStkOrderDetails(@PathVariable(value = "stk_challan_id") String stk_challan_id)
	{
		return stockTransferChallan_Service.getMultipleStkOrderDetails(stk_challan_id);
	}
	
	@GetMapping("/getMultipleStkTransChallanDocs/{stk_challan_id}")
	public List<Stk_Transfer_Challan_Docs> getMultipleStkTransChallanDocs(@PathVariable(value = "stk_challan_id") String stk_challan_id)
	{
		return stockTransferChallan_Service.getMultipleStkTransChallanDocs(stk_challan_id);
	}
	
	@GetMapping("/getMultipleStkTransSalesInvItemDtlsupdate/{id}")
	public List<Stk_transfer_sales_inv_item_dtlsDTO> getMultipleStkTransSalesInvItemDtlsupdate(@PathVariable(value = "id")  Long id)
	{
		return stk_transfer_sales_invService.getMultipleStkTransSalesInvItemDtlsupdate(id);
	}
	
	
	@GetMapping(value = "/getStkTransChallanThruBUnit/{bunit}")
	public ResponseEntity<List<Stk_Transfer_ChallanDTO>> getStkTransChallanThruBUnit(@PathVariable(value = "bunit") String bunit) 
	{
		List<Stk_Transfer_ChallanDTO> val=stockTransferChallan_Service.getStkTransChallanThruBUnit(bunit);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping(value = "/getStkTransChallanDtls")
	public ResponseEntity<Stk_Transfer_ChallanDTO> getStkTransChallanDtls(@RequestParam(defaultValue = "") String stkchlnid) 
	{
		Stk_Transfer_ChallanDTO val=stockTransferChallan_Service.getStkTransChallanDtls(stkchlnid);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getStkTransChallanItemDlts/{stk_challan_id}")
	public List<Stk_Transfer_Challan_Item_DtlsDTO> getStkTransChallanItemDlts(@PathVariable(value = "stk_challan_id") String stk_challan_id)
	{
		return stockTransferChallan_Service.getStkTransChallanItemDlts(stk_challan_id);
	}
	
	@GetMapping("/getStkTransChallanShipDtls/{stk_challan_id}")
	public Stk_Transfer_Challan_Shipment_DtlsDTO getStkTransChallanShipDtls(@PathVariable(value = "stk_challan_id") String stk_challan_id)
	{
		return stockTransferChallan_Service.getStkTransChallanShipDtls(stk_challan_id);
	}
	
	@GetMapping("/getStkTransChallanTranInfo/{stk_challan_id}")
	public Stk_Transfer_Challan_Trans_InfoDTO getStkTransChallanTranInfo(@PathVariable(value = "stk_challan_id") String stk_challan_id)
	{
		return stockTransferChallan_Service.getStkTransChallanTranInfo(stk_challan_id);
	}
	
	// Dynamic Transport Details //
	@GetMapping("/getStkTransChallanTranInfos/{stk_challan_id}")
	public List<Invoice_Stk_Transfer_Challan_Trans_InfoDTO> getStkTransChallanTranInfos(@PathVariable(value = "stk_challan_id") String stk_challan_id)
	{
		return stockTransferChallan_Service.getStkTransChallanTranInfos(stk_challan_id);
	}
	
	@GetMapping("/getStkOrderDetails/{stk_challan_id}")
	public Stk_Transfer_Challan getStkOrderDetails(@PathVariable(value = "stk_challan_id") String stk_challan_id)
	{
		return stockTransferChallan_Service.getStkOrderDetails(stk_challan_id);
	}
	
	@GetMapping("/getStkTransChallanDocs/{stk_challan_id}")
	public List<Stk_Transfer_Challan_Docs> getStkTransChallanDocs(@PathVariable(value = "stk_challan_id") String stk_challan_id)
	{
		return stockTransferChallan_Service.getStkTransChallanDocs(stk_challan_id);
	}
	
	@GetMapping("/getStkTransBUDtls/{stk_challan_id}")
	public List<Stk_Transfer_Challan_BusinessUnit_Dtls> getStkTransBUDtls(@PathVariable(value = "stk_challan_id") String stk_challan_id)
	{
		return stockTransferChallan_Service.getStkTransBUDtls(stk_challan_id);
	}
	
	@GetMapping("/getStkTransChallanWtDtls/{stk_challan_id}")
	public Stk_Transfer_Challan_Weight_DtlDTO getStkTransChallanWtDtls(@PathVariable(value = "stk_challan_id") String stk_challan_id)
	{
		return stockTransferChallan_Service.getStkTransChallanWtDtls(stk_challan_id);
	}
	
	@PutMapping("/updateStkTransChallan/{id}")
	public ResponseEntity<Stk_Transfer_Challan> updateStkTransChallan(@PathVariable(value="id") long id,@Valid @RequestBody Stk_Transfer_Challan stk_Transfer_Challan)
	{
		Stk_Transfer_Challan op=stockTransferChallan_Service.update(stk_Transfer_Challan,id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteStocktransferChallan/{id}")
	public ResponseEntity<Stk_Transfer_Challan> deleteStocktransferChallan(@PathVariable(value="id") long id,@Valid @RequestBody Stk_Transfer_Challan challan)
	{
		Stk_Transfer_Challan op=stockTransferChallan_Service.deleteStocktransferChallan(challan,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getReceivingBuLoadingAdvice/{advice_id}")
	public Stock_Transfer getReceivingBuLoadingAdvice(@PathVariable(value="advice_id") String advice_id) 
	{
		return stock_Transfer_Service.getReceivingBuLoadingAdvice(advice_id);
		
	}
	
	@GetMapping("/getStkChallanVehicleNo/{advice_id}")
	public Stk_Transfer_Challan_Trans_Info getStkChallanVehicleNo(@PathVariable(value="advice_id") String advice_id) 
	{
		return stockTransferChallan_Service.getStkChallanVehicleNo(advice_id);
		
	}
	
	/********************* Stock Transfer Challan End ********************/
	
	/******************* Stk Transfer Sales Inv Start********************/
	
	@Autowired
	Stk_transfer_sales_invService stk_transfer_sales_invService;
	
	@GetMapping("/getSTSISequenceId/{idate}/{bunit}")
	public SequenceIdDTO getSTSISequenceId(@PathVariable(value = "idate") String idate,@PathVariable(value = "bunit") String bunit)
	{
		return stk_transfer_sales_invService.getSTSISequenceId(idate,bunit);
	}
	
	@PostMapping("/createStkTranSaleInv")
	public Stk_transfer_sales_inv createStkTranSaleInv(@Valid @RequestBody Stk_transfer_sales_inv sTransfer_sales_inv) {
		return stk_transfer_sales_invService.save(sTransfer_sales_inv);
	}
	
	@GetMapping("/getStkTranSaleInvs")
	public ResponseEntity<List<Stk_transfer_sales_inv>> getStkTranSaleInvs(@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear) {
		List<Stk_transfer_sales_inv> sinvList = stk_transfer_sales_invService.getStkTranSaleInvs(company, finyear);
		return new ResponseEntity<List<Stk_transfer_sales_inv>>(sinvList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getStkTransSalesInvById/{id}")
	public ResponseEntity<Stk_transfer_sales_inv> getStkTransSalesInvById(@PathVariable(value="id") Long id)
	{
		Stk_transfer_sales_inv stinvoice=stk_transfer_sales_invService.findOne(id);
		if(stinvoice==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(stinvoice);
		}
	}
	
	@GetMapping("/getStkTransSalesInvItemDtls/{stk_sales_inv_id}")
	public List<Stk_transfer_sales_inv_item_dtlsDTO> getStkTransSalesInvItemDtls(@PathVariable(value = "stk_sales_inv_id") String stk_sales_inv_id)
	{
		return stk_transfer_sales_invService.getStkTransSalesInvItemDtls(stk_sales_inv_id);
	}
	
	@GetMapping("/getStkTransSalesInvTransDtls/{stk_sales_inv_id}")
	public List<Stk_transfer_sales_inv_trans_dtlsDTO> getStkTransSalesInvTransDtls(@PathVariable(value = "stk_sales_inv_id") String stk_sales_inv_id)
	{
		return stk_transfer_sales_invService.getStkTransSalesInvTransDtls(stk_sales_inv_id);
	}
	
	@GetMapping("/getStkTransSalesInvBUDtls/{stk_sales_inv_id}")
	public Stk_transfer_sales_inv_bu_dtlsDTO getStkTransSalesInvBUDtls(@PathVariable(value = "stk_sales_inv_id") String stk_sales_inv_id)
	{
		return stk_transfer_sales_invService.getStkTransSalesInvBUDtls(stk_sales_inv_id);
	}
	
	@GetMapping("/getStkTransSalesInvDocs/{stk_sales_inv_id}")
	public List<Stk_transfer_sales_inv_docs> getStkTransSalesInvDocs(@PathVariable(value = "stk_sales_inv_id") String stk_sales_inv_id)
	{
		return stk_transfer_sales_invService.getStkTransSalesInvDocs(stk_sales_inv_id);
	}
	
	@GetMapping("/getStkTransSalesInvShipDtls/{stk_sales_inv_id}")
	public Stk_transfer_sales_inv_shipment_dtlsDTO getStkTransSalesInvShipDtls(@PathVariable(value = "stk_sales_inv_id") String stk_sales_inv_id)
	{
		return stk_transfer_sales_invService.getStkTransSalesInvShipDtls(stk_sales_inv_id);
	}
	
	@GetMapping("/getStkTransSalesInvTaxInfo/{stk_sales_inv_id}")
	public Stk_transfer_sales_inv_tax_infoDTO getStkTransSalesInvTaxInfo(@PathVariable(value = "stk_sales_inv_id") String stk_sales_inv_id)
	{
		return stk_transfer_sales_invService.getStkTransSalesInvTaxInfo(stk_sales_inv_id);
	}
	
	@GetMapping("/stkSalesInv/{id}")
	public Stk_Transfer_ChallanDTO stkSalesInv(@PathVariable(value="id") Long id)
	{
		return stk_transfer_sales_invService.stkSalesInv(id);
	}
	
	@PutMapping("/deleteStockTransferSalesInvoice/{id}")
	public ResponseEntity<Stk_transfer_sales_inv> deleteStockTransferSalesInvoice(@PathVariable(value="id") long id,@Valid @RequestBody Stk_transfer_sales_inv sInv)
	{
		Stk_transfer_sales_inv op=stk_transfer_sales_invService.deleteStockTransferSalesInvoice(sInv,id);
		return ResponseEntity.ok().body(op);
	}
	
	/******************* Stk Transfer Sales Inv End********************/
	
	
	/******************* Stk Transfer GRN Start********************/
	
	@Autowired
	Stk_transfer_grnService stk_transfer_grnService; 
	
	@GetMapping("/getSTGRNSequenceId/{tdate}/{bunit}")
	public SequenceIdDTO getSTGRNSequenceId(@PathVariable(value = "tdate") String tdate,@PathVariable(value = "bunit") String bunit)
	{
		return stk_transfer_grnService.getSTGRNSequenceId(tdate,bunit);
	}
	
	@PostMapping("/createStkTranGrn")
	public Stk_transfer_grn createStkTranGrn(@Valid @RequestBody Stk_transfer_grn sGrn) {
		return stk_transfer_grnService.save(sGrn);
	}
	
	@GetMapping("/getStkTranGrns")
	public ResponseEntity<List<Stk_transfer_grn>> getStkTranGrns(@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear) {
		List<Stk_transfer_grn> sinvList = stk_transfer_grnService.getStkTranGRN(company, finyear);
		return new ResponseEntity<List<Stk_transfer_grn>>(sinvList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getStkTranGrnsFast/{comp}/{fyear}")
	public  List<Map<String, Object>> getStkTranGrnsFast(@PathVariable(value = "comp") String comp,@PathVariable(value="fyear") String fyear)
	{
		return stk_transfer_grnService.getStkTranGrnsFast(comp,fyear);
	}
	
	@GetMapping("/getSalesInvFromStkTransGrn/{stk_grn_id}")
	public  Map<String, Object> getSalesInvFromStkTransGrn(@PathVariable(value = "stk_grn_id") String stk_grn_id)
	{
		return stk_transfer_grnService.getSalesInvFromStkTransGrn(stk_grn_id);
	}
	
	@GetMapping("/getStkTranGrn")
	public ResponseEntity<List<Stk_transfer_grnDTO>> getStkTranGrn(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String tdate,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear) {
		List<Stk_transfer_grnDTO> grnList = stk_transfer_grnService.getStkTranGrns(bunit,tdate,company,finyear);
		return new ResponseEntity<List<Stk_transfer_grnDTO>>(grnList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getStkTranGrnDtls")
	public ResponseEntity<Stk_transfer_grnDTO> getStkTranGrnDtls(@RequestParam(defaultValue = "") String stkgrnid) {
		Stk_transfer_grnDTO grnList = stk_transfer_grnService.getStkTranGrnDtls(stkgrnid);
		return new ResponseEntity<Stk_transfer_grnDTO>(grnList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getStkTranGrnById/{id}")
	public ResponseEntity<Stk_transfer_grn> getStkTranGrnById(@PathVariable(value="id") Long id)
	{
		Stk_transfer_grn stgrn=stk_transfer_grnService.findOne(id);
		if(stgrn==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(stgrn);
		}
	}
	
	@GetMapping("/getStkTransferGrnRestQty/{orderid}/{item}/{packing}")
	public Map<String,Object> getStkTransferGrnRestQty(@PathVariable(value="orderid") String orderid,
			@PathVariable(value="item") String item,@PathVariable(value="packing") String packing)
	{
		 return stk_transfer_grnService.getStkTransferGrnRestQty(orderid,item,packing);
		
	}
	
	@GetMapping("/getStkTranGrnItemDlts/{stk_grn_id}")
	public List<Stk_transfer_grn_item_detailsDTO> getStkTranGrnItemDlts(@PathVariable(value = "stk_grn_id") String stk_grn_id)
	{
		return stk_transfer_grnService.getStkTranGrnItemDlts(stk_grn_id);
	}
	
	@GetMapping("/getStkTranGrnTranInfo/{stk_grn_id}")
	public Stk_transfer_grn_trans_infoDTO getStkTranGrnTranInfo(@PathVariable(value = "stk_grn_id") String stk_grn_id)
	{
		return stk_transfer_grnService.getStkTranGrnTranInfo(stk_grn_id);
	}
	
	@GetMapping("/getStkTranGrnBUDtls/{stk_grn_id}")
	public Stk_transfer_grn_bu_dtlsDTO getStkTranGrnBUDtls(@PathVariable(value = "stk_grn_id") String stk_grn_id)
	{
		return stk_transfer_grnService.getStkTranGrnBUDtls(stk_grn_id);
	}
	
	@GetMapping("/getStkTranGrnOthDtls/{stk_grn_id}")
	public Stk_transfer_grn_other_infoDTO getStkTranGrnOthDtls(@PathVariable(value = "stk_grn_id") String stk_grn_id)
	{
		return stk_transfer_grnService.getStkTranGrnOthDtls(stk_grn_id);
	}
	
	@GetMapping("/getStkTranGrnReCostDlts/{stk_grn_id}")
	public List<Stk_transfer_grn_resource_costDTO> getStkTranGrnReCostDlts(@PathVariable(value = "stk_grn_id") String stk_grn_id)
	{
		return stk_transfer_grnService.getStkTranGrnReCostDlts(stk_grn_id);
	}
	
	@GetMapping("/getStkTranGrnDocs/{stk_grn_id}")
	public List<Stk_transfer_grn_docsDTO> getStkTranGrnDocs(@PathVariable(value = "stk_grn_id") String stk_grn_id)
	{
		return stk_transfer_grnService.getStkTranGrnDocs(stk_grn_id);
	}
	
	/******************* Stk Transfer GRN END********************/
	
	/******************* Stk Transfer Pur Inv Start********************/
	
	@Autowired
	Stk_transfer_pur_invService stk_transfer_pur_invService;
	
	@GetMapping("/getSTPISequenceId/{tdate}/{bunit}")
	public SequenceIdDTO getSTPISequenceId(@PathVariable(value = "tdate") String tdate,@PathVariable(value = "bunit") String bunit)
	{
		return stk_transfer_pur_invService.getSTPISequenceId(tdate,bunit);
	}
	
	@PostMapping("/createStkTranPurInv")
	public Stk_transfer_pur_inv createStkTranPurInv(@Valid @RequestBody Stk_transfer_pur_inv sPur_inv) {
		return stk_transfer_pur_invService.save(sPur_inv);
	}
	
	@GetMapping("/getStkTranPurInvs")
	public ResponseEntity<List<Stk_transfer_pur_invDTO>> getStkTranPurInvs(@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear) {
		List<Stk_transfer_pur_invDTO> sinvList = stk_transfer_pur_invService.getStkTranPurInvs(company, finyear);
		return new ResponseEntity<List<Stk_transfer_pur_invDTO>>(sinvList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/stkPurInvRetriveList/{id}")
	public ResponseEntity<Stk_transfer_pur_inv> stkPurInvRetriveList(@PathVariable(value="id") Long id)
	{
		Stk_transfer_pur_inv stgrn=stk_transfer_pur_invService.findOne(id);
		if(stgrn==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(stgrn);
		}
	}
	
	@GetMapping("/stkPurInvItemRetriveList/{stk_trans_pur_inv_id}")
	public List<Stk_transfer_pur_inv_item_dtls> stkPurInvItemRetriveList(@PathVariable(value = "stk_trans_pur_inv_id") String stk_trans_pur_inv_id)
	{
		return stk_transfer_pur_invService.stkPurInvItemRetriveList(stk_trans_pur_inv_id);
	}
	
	@GetMapping("/stkPurInvMusterRollRetriveList/{stk_trans_pur_inv_id}")
	public List<Stk_transfer_pur_inv_musterroll_dtls> stkPurInvMusterRollRetriveList(@PathVariable(value = "stk_trans_pur_inv_id") String stk_trans_pur_inv_id)
	{
		return stk_transfer_pur_invService.stkPurInvMusterRollRetriveList(stk_trans_pur_inv_id);
	}
	
	@GetMapping("/stkPurInvDocsRetriveList/{stk_trans_pur_inv_id}")
	public List<Stk_transfer_pur_inv_docs> stkPurInvDocsRetriveList(@PathVariable(value = "stk_trans_pur_inv_id") String stk_trans_pur_inv_id)
	{
		return stk_transfer_pur_invService.stkPurInvDocsRetriveList(stk_trans_pur_inv_id);
	}
	
	@GetMapping("/stkPurInvTaxInfoRetriveList/{stk_trans_pur_inv_id}")
	public Stk_transfer_pur_inv_tax_info stkPurInvTaxInfoRetriveList(@PathVariable(value = "stk_trans_pur_inv_id") String stk_trans_pur_inv_id)
	{
		return stk_transfer_pur_invService.stkPurInvTaxInfoRetriveList(stk_trans_pur_inv_id);
	}
	
	@GetMapping("/stkPurInvBuRetriveList/{stk_trans_pur_inv_id}")
	public Stk_transfer_pur_inv_bu_dtls stkPurInvBuRetriveList(@PathVariable(value = "stk_trans_pur_inv_id") String stk_trans_pur_inv_id)
	{
		return stk_transfer_pur_invService.stkPurInvBuRetriveList(stk_trans_pur_inv_id);
	}
	
	@PutMapping("/deleteStocktransferPurInv/{id}")
	public ResponseEntity<Stk_transfer_pur_inv> deleteStocktransferPurInv(@PathVariable(value="id") long id,@Valid @RequestBody Stk_transfer_pur_inv purinv)
	{
		Stk_transfer_pur_inv op=stk_transfer_pur_invService.deleteStocktransferPurInv(purinv,id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/stkPurInv/{id}")
	public Stk_transfer_grnDTO stkPurInv(@PathVariable(value="id") long id)
	{
		return stk_transfer_grnService.stkPurInv(id);
	}
	
	@GetMapping("/getStkTransPurInvItemDtls/{stk_pur_inv_id}")
	public List<Stk_transfer_pur_inv_item_dtls> getStkTransPurInvItemDtls(@PathVariable(value = "stk_pur_inv_id") String stk_pur_inv_id)
	{
		return stk_transfer_pur_invService.getStkTransPurInvItemDtls(stk_pur_inv_id);
	}
	
	/******************* STOCK REPORTS  STARTS********************/
	@Autowired
	Stock_reportsService stock_reportsService;
	
	@GetMapping("/getStockReports/{reportname}")
	public List<Stock_reports> getStockReports(@PathVariable(value = "reportname") String reportname)
	{
		return stock_reportsService.stocksReport(reportname);
	}
	
	
	/******************* STOCK REPORTS ENSDS ********************/
	@Autowired
	Wm_unload_wgmntService wm_unload_wgmntService;
	
	@GetMapping(value = "/getVehiclesFromVehicleLoadUnload")
	public List<Vehicle_weighment_load_unload> getVehiclesFromVehicleLoadUnload() {
		return stk_transfer_grnService.getVehiclesFromVehicleLoadUnload();
	}
	
	@GetMapping(value = "/getVehicleRefName/{vehicleid}")
	public Vehicle_weighment_load_unload getVehicleRefName(@PathVariable(value = "vehicleid") String vehicleid) {
		return stk_transfer_grnService.getVehicleRefName(vehicleid);
	}
	
	@GetMapping(value = "/getWeighmentId/{weighmentid}")
	public Wm_unload_wgmnt getWeighmentId(@PathVariable(value = "weighmentid") String weighmentid) {
		return wm_unload_wgmntService.getWeighmentId(weighmentid);
	}
	
	@GetMapping(value = "/getReceipt_criteria/{weighmentid}/{unload}")
	public Stk_Transfer_Challan getReceipt_criteria(@PathVariable(value = "weighmentid") String weighmentid,@PathVariable(value = "unload") String unload) {
		return stockTransferChallan_Service.getReceipt_criteria(weighmentid,unload);
	}
	
	@GetMapping(value = "/getChallanItemDlts/{challanid}/{itemid}")
	public Stk_Transfer_Challan_Item_Dtls getChallanItemDlts(@PathVariable(value = "challanid") String challanid,@PathVariable(value = "itemid") String itemid) {
		return stockTransferChallan_Service.getChallanItemDlts(challanid,itemid);
	}
	
	
	@PutMapping("/deleteStocktransferGRN/{id}")
	public ResponseEntity<Stk_transfer_grn> deleteStocktransferGRN(@PathVariable(value="id") long id,@Valid @RequestBody Stk_transfer_grn grn)
	{
		Stk_transfer_grn op=stk_transfer_grnService.deleteStocktransferGRN(grn,id);
		return ResponseEntity.ok().body(op);
	}
	
	
	@GetMapping("/checkStockGRNUsage/{id}")
	public StatusDTO checkStockGRNUsage(@PathVariable(value= "id") Long id)
	{
		return stk_transfer_grnService.checkStockGRNUsage(id);
	}
	
	@GetMapping("/getStockVehicleAndDriver/{challan_id}")
	public Wm_loading_advice_driver_dtls getStockVehicleAndDriver(@PathVariable(value= "challan_id") String challan_id)
	{
		return stockTransferChallan_Service.getStockVehicleAndDriver(challan_id);
	}
	
	@GetMapping("/findOneChallan/{id}")
	public Stock_Transfer findOneChallan(@PathVariable(value="id") Long id)
	{
		 return stockTransferChallan_Service.findOneChallan(id);
		
	}
	
	@GetMapping("/getStkTransSalesInvByIdprint/{id}")
	public Stk_transfer_sales_inv getStkTransSalesInvByIdprint(@PathVariable(value = "id") Long id)
	{
		return stk_transfer_sales_invService.getStkTransSalesInvByIdprint(id);
	}
	
	@GetMapping("/getstockchallandetails/{stk_challan_id}")
	public Stk_Transfer_Challan getstockchallandetails(@PathVariable(value = "stk_challan_id") String stk_challan_id)
	{
		return stockTransferChallan_Service.getstockchallandetails(stk_challan_id);
	}
	
	@GetMapping("/getOrderNumberForChallan/{ref_id}/{ref_type}")
	public Stock_Transfer getOrderNumberForChallan(@PathVariable(value = "ref_id") String ref_id,@PathVariable(value = "ref_type") String ref_type)
	{
		return stock_Transfer_Service.getOrderNumberForChallan(ref_id,ref_type);
	}
	
	@GetMapping("/getStockOrdByUnloadCode/{reference_id}/{reference_status}")
	public Stock_Transfer getStockOrdByUnloadCode(@PathVariable(value = "reference_id") String reference_id,@PathVariable(value = "reference_status") String reference_status)
	{
		return stock_Transfer_Service.getStockOrdByUnloadCode(reference_id,reference_status);
	}
	
	@GetMapping("/getstockrecivingbuunit/{id}")
	public Stk_transfer_sales_inv getstockrecivingbuunit(@PathVariable(value = "id") Long id)
	{
		return stk_transfer_sales_invService.getstockrecivingbuunit(id);
	}
	
	
	@GetMapping("/getStockSaleInvHsnSum/{stk_sales_inv_id}")
	public  List<Stocksaleitem_groupwise_hsnsumm> getStockSaleInvHsnSum(@PathVariable(value = "stk_sales_inv_id") String stk_sales_inv_id)
	{
		return stk_transfer_sales_invService.getStockSaleInvHsnSum(stk_sales_inv_id);
	}
	
	@GetMapping("/getStockSaleInvTaxSum/{stk_sales_inv_id}")
	public List<Stocksaleitem_groupwise_taxsumm> getStockSaleInvTaxSum(@PathVariable(value = "stk_sales_inv_id") String stk_sales_inv_id)
	{
		return stk_transfer_sales_invService.getStockSaleInvTaxSum(stk_sales_inv_id);
	}
	
	/* STOCK TRANSFER ORDER TO GRN STARTS */

	@GetMapping(value = "/getStkTranswtoutVch")
	public List<Map<String, Object>> getStkTranswtoutVch()
	{
		List<Map<String, Object>> val=stock_Transfer_Service.getStkTranswtoutVch();
		return val;
	}
	
	@GetMapping("/getstockOrderdetails/{stockTransOrder_id}")
	public Map<String, Object> getstockOrderdetails(@PathVariable(value = "stockTransOrder_id") String stockTransOrder_id)
	{
		return stock_Transfer_Service.getstockOrderdetails(stockTransOrder_id);
	}
	
	@GetMapping("/getStkOrderVehicleNo/{stockTransOrder_id}")
	public Map<String, Object> getStkOrderVehicleNo(@PathVariable(value = "stockTransOrder_id") String stockTransOrder_id)
	{
		return stock_Transfer_Service.getStkOrderVehicleNo(stockTransOrder_id);
	}
	
	@GetMapping("/getStkTransOrderItemDlts/{stockTransOrder_id}")
	public  List<Map<String, Object>> getStkTransOrderItemDlts(@PathVariable(value = "stockTransOrder_id") String stockTransOrder_id)
	{
		return stock_Transfer_Service.getStkTransOrderItemDlts(stockTransOrder_id);
	}
	
	/* STOCK TRANSFER ORDER TO GRN ENDS */
}

