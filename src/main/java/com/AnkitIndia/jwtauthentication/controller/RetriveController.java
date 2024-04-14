package com.AnkitIndia.jwtauthentication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AnkitIndia.jwtauthentication.model.Charge_Master;
import com.AnkitIndia.jwtauthentication.model.GatepassChecklist;
import com.AnkitIndia.jwtauthentication.model.GatepassGateout;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization_details;
import com.AnkitIndia.jwtauthentication.model.GatepassGateout_details;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin_details;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Item_master_stock_details;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Pur_Indent;
import com.AnkitIndia.jwtauthentication.model.Pur_L1_Selection;
import com.AnkitIndia.jwtauthentication.model.Pur_Order;
import com.AnkitIndia.jwtauthentication.model.Pur_Quotation;
import com.AnkitIndia.jwtauthentication.model.Pur_good_receipt_driver_dtls;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Tag_advice_with_po;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner_tds;
import com.AnkitIndia.jwtauthentication.model.Transporter_group;
//import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice_broker_dtls;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_advice;
import com.AnkitIndia.jwtauthentication.model.Wm_unload_wgmnt;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_alternative_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_inv_data1DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_inv_data2DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_other_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_master_stat_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Account_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Bp_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Broker_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_DocsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Item_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Musterroll_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Bill_Tax_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Enquiry_BPartner_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Enquiry_DocDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Enquiry_Service_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_IndentDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_L1_Selection_DtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_BPDetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_DocDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Item_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_TerminationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Terms_ConDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_app_chgsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quality_Check_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_QuotationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quotation_DocDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quotation_ServiceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receiptDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_Business_Partner_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_brokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_item_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_receipt_resource_costDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_good_reciept_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_goods_receipt_other_informationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_quotation_Business_Partner_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partner_accontDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partner_bill_addrDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partner_bill_addr_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_address_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partner_statutoryDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_bp_dtlsDTO;
//import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_doc_attchDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_driver_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_itm_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_advice_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_wgmnt_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_app_chgsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_bp_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_driver_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_party_wmDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_terms_conDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmnt_dtlsDTO;
import com.AnkitIndia.jwtauthentication.security.services.GatepassChecklistService;
import com.AnkitIndia.jwtauthentication.security.services.GatepassGateoutAuthorizationService;
import com.AnkitIndia.jwtauthentication.security.services.GatepassGateoutService;
import com.AnkitIndia.jwtauthentication.security.services.GatepassGetinService;
import com.AnkitIndia.jwtauthentication.security.services.Item_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_BillService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_Bill_app_chgs;
import com.AnkitIndia.jwtauthentication.security.services.Pur_EnquiryService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_IndentService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_L1_SelectionService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_OrderService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_Quality_CheckService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_QuotationService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_good_receiptService;
import com.AnkitIndia.jwtauthentication.security.services.Supp_bussiness_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.Supplier_groupService;
import com.AnkitIndia.jwtauthentication.security.services.Tag_advice_with_poService;
import com.AnkitIndia.jwtauthentication.security.services.Trans_bussiness_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.Transporter_groupService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_loading_adviceService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_loading_wgmntService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_unload_adviceService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_unload_wgmntService;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_master_pack_mat_tagDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_master_qc_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_Indent_DocDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_Order_Termination_dynDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_brokerDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_docDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_statutoryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Trans_bussiness_partner_vehicle_dtlsDTO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
//@RequestMapping("/MeghnaBidi")
//@RequestMapping("/MeghanaPanMasala")
//@RequestMapping("/IndianSpices")
public class RetriveController {

	@Autowired
	Supplier_groupService supplier_groupService;
	
	@Autowired
	Supp_bussiness_partnerService supp_bussiness_partnerService;
	
	@Autowired
	Transporter_groupService transporter_groupService;
	
	@Autowired
	Trans_bussiness_partnerService trans_bussiness_partnerService;
	
	@Autowired
	Item_masterService item_masterService;
	
	@Autowired
	Pur_IndentService pur_IndentService;
	
	@Autowired
	Pur_EnquiryService pur_EnquiryService;
	
	@Autowired
	Pur_QuotationService pur_QuotationService;
	
	@Autowired
	Pur_OrderService pur_OrderService;
	
	@Autowired
	Pur_Quality_CheckService pur_Quality_CheckService;
	
	@Autowired
	Pur_good_receiptService pur_good_receiptService;
	
	@Autowired
	Pur_L1_SelectionService pur_L1_SelectionService;
	
	@Autowired
	Wm_unload_adviceService wm_unload_adviceService;
	
	@Autowired
	Wm_unload_wgmntService wm_unload_wgmntService;
	
	@Autowired
	Wm_loading_adviceService wm_loading_adviceService;
	
	@Autowired
	Wm_loading_wgmntService wm_loading_wgmntService;
	
	@Autowired
	Tag_advice_with_poService tag_advice_with_poService;
	
	//....................Item Master Start.............................
	
	@GetMapping("/retriveItemMaster/{id}")
	public ResponseEntity<Item_master> retriveItemMaster(@PathVariable(value="id") Long id)
	{
		Item_master op=item_masterService.findOne(id);
		if(id==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/retriveItemMasterAltDtls/{item_id}/{company}")
	public List<Item_master_alternative_dtlsDTO> retriveItemMasterAltDtls(@PathVariable(value = "item_id") String item_id,@PathVariable(value = "company") String company)
	{
		return item_masterService.retriveItemMasterAltDtls(item_id,company);
	}
	
	@GetMapping("/getItemMasterInvData1/{item_id}/{company}")
	public Item_master_inv_data1DTO retriveItemMasterInvData1(@PathVariable(value = "item_id") String item_id,@PathVariable(value = "company") String company)
	{
		return item_masterService.retriveItemMasterInvData1(item_id,company);
	}
	
	@GetMapping("/retriveItemMasterInvData2/{item_id}/{company}")
	public Item_master_inv_data2DTO retriveItemMasterInvData2(@PathVariable(value = "item_id") String item_id,@PathVariable(value = "company") String company)
	{
		return item_masterService.retriveItemMasterInvData2(item_id,company);
	}
	
	@GetMapping("/retriveItemMasterOtherInfo/{item_id}/{company}")
	public Item_master_other_infoDTO retriveItemMasterOtherInfo(@PathVariable(value = "item_id") String item_id,@PathVariable(value = "company") String company)
	{
		return item_masterService.retriveItemMasterOtherInfo(item_id,company);
	}
	
	@GetMapping("/retriveItemMasterStatInfo/{item_id}/{company}")
	public List<Item_master_stat_infoDTO> retriveItemMasterStatInfo(@PathVariable(value = "item_id") String item_id,@PathVariable(value = "company") String company)
	{
		return item_masterService.retriveItemMasterStatInfo(item_id,company);
	}
	
	@GetMapping("/getItemMasterPackMat/{code}/{company}")
	public List<Item_master_pack_mat_tagDTO> getItemMasterPackMat(@PathVariable(value = "code") String code,@PathVariable(value = "company") String company)
	{
		List<Item_master_pack_mat_tagDTO> itemList= new ArrayList<Item_master_pack_mat_tagDTO>();
		
		Item_master_pack_mat_tagDTO def=new Item_master_pack_mat_tagDTO(0,"0","0","Choose an Option","0","0","0","0","0",0.0,"0","0");
		itemList.add(def);
		itemList.addAll(item_masterService.getItemMasterPackMat(code,company));
		
		return itemList;
	}
	
	@GetMapping("/getItemMasterPackMat/{code}")
	public List<Item_master_pack_mat_tagDTO> getItemMasterPackMat(@PathVariable(value = "code") String code)
	{
		List<Item_master_pack_mat_tagDTO> itemList= new ArrayList<Item_master_pack_mat_tagDTO>();
		
		Item_master_pack_mat_tagDTO def=new Item_master_pack_mat_tagDTO(0,"0","0","Choose an Option","0","0","0","0","0",0.0,"0","0");
		itemList.add(def);
		itemList.addAll(item_masterService.getItemMasterPackMat(code));
		
		return itemList;
	}
	
	@GetMapping("/getItemMasterPackMatMultipopup/{code}")
	public List<Item_master_pack_mat_tagDTO> getItemMasterPackMatMultipopup(@PathVariable(value = "code") String code)
	{
		List<Item_master_pack_mat_tagDTO> itemList= new ArrayList<Item_master_pack_mat_tagDTO>();
		
		itemList.addAll(item_masterService.getItemMasterPackMat(code));
		
		return itemList;
	}
	
	@GetMapping("/getItemMasterPackMaterials/{code}/{company}")
	public List<Item_master_pack_mat_tagDTO> getItemMasterPackMaterials(@PathVariable(value = "code") String code,@PathVariable(value = "company") String company)
	{
		List<Item_master_pack_mat_tagDTO> itemList= new ArrayList<Item_master_pack_mat_tagDTO>();
		itemList.addAll(item_masterService.getItemMasterPackMat(code,company));
		return itemList;
	}

	@GetMapping("/getItemMasterPackMatList/{code}/{company}")
	public List<Item_master_pack_mat_tagDTO> getItemMasterPackMatList(@PathVariable(value = "code") String code,@PathVariable(value = "company") String company)
	{
		List<Item_master_pack_mat_tagDTO> itemList= new ArrayList<Item_master_pack_mat_tagDTO>();
		itemList.addAll(item_masterService.getItemMasterPackMat(code,company));
		return itemList;
	}
	
	@GetMapping("/getItemQCDetails/{code}/{company}")
	public List<Item_master_qc_detailsDTO> getItemQCDetails(@PathVariable(value = "code") String code,@PathVariable(value = "company") String company)
	{
		return item_masterService.getItemQCDetails(code,company);
	}
	
	@GetMapping("/getItemCapacity/{item_id}")
	public Item_master_pack_mat_tagDTO getItemCapacity(@PathVariable(value = "item_id") String item_id)
	{
		return item_masterService.getItemCapacity(item_id);
	}
	
	@GetMapping("/getItemstockDetails/{code}/{company}")
	public List<Item_master_stock_details> getItemstockDetails(@PathVariable(value = "code") String code,@PathVariable(value = "company") String company)
	{
		return item_masterService.getItemstockDetails(code,company);
	}
	
	@GetMapping("/retrivePackingDtls/{packingMasterCode}/{packingid}")
	public Map<String, Object> retrivePackingDtls(@PathVariable(value = "packingMasterCode") String packingMasterCode,
												  @PathVariable(value = "packingid") String packingid)
	{
		return item_masterService.retrivePackingDtls(packingMasterCode,packingid);
	}
	
	//....................Item Master End.............................
	
	//....................Supplier Master Start.............................
	
	@GetMapping("/retriveSupplierBPartner/{id}")
	public ResponseEntity<Supp_bussiness_partner> retriveSupplierBPartner(@PathVariable(value="id") Long id)
	{
		Supp_bussiness_partner sbp=supp_bussiness_partnerService.findOne(id);
		if(sbp==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(sbp);
		}
	}
	
	@GetMapping("/getSuppBPAddr/{bp_id}")
	public Supp_bussiness_partner_addressDTO getSuppBPAddr(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSuppBPAddr(bp_id);
	}
	
	@GetMapping("/getSuppBPBillAddr/{bp_id}")
	public Supp_bussiness_partner_bill_addrDTO getSuppBPBillAddr(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSuppBPBillAddr(bp_id);
	}
	
	@GetMapping("/getSuppBPBillAddrDtls/{bp_id}")
	public List<Supp_bussiness_partner_bill_addr_dtlsDTO> getSuppBPBillAddrDtls(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSuppBPBillAddrDtls(bp_id);
	}
	
	@GetMapping("/getSuppBPAcc/{bp_id}")
	public Supp_bussiness_partner_accontDTO getSuppBPAcc(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSuppBPAcc(bp_id);
	}
	
	@GetMapping("/getSuppBPStatuDtls/{bp_id}")
	public Supp_bussiness_partner_statutoryDTO getSuppBPStatuDtls(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSuppBPStatuDtls(bp_id);
	}
	
	@GetMapping("/getSuppBPBroker/{bp_id}")
	public List<Supp_bussiness_partner_brokerDTO> getSuppBPBroker(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSuppBPBroker(bp_id);
	}
	
	@GetMapping("/getSuppBPDoc/{bp_id}")
	public List<Supp_bussiness_partner_docDTO> getSuppBPDoc(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSuppBPDoc(bp_id);
	}
	
	@GetMapping("/TransGrpRetrive/{id}")
	public ResponseEntity<Transporter_group> TransGrpRetrive(@PathVariable(value="id") Long id)
	{
		Transporter_group tgs=transporter_groupService.findOne(id);
		if(tgs==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(tgs);
		}
	}
	
	@GetMapping("/TransBussPtRetrive/{id}")
	public ResponseEntity<Trans_bussiness_partner> TransBussPtRetrive(@PathVariable(value="id") Long id)
	{
		Trans_bussiness_partner tgs=trans_bussiness_partnerService.findOne(id);
		if(tgs==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(tgs);
		}
	}
	
	
	@GetMapping("/getTransBPAddr/{bp_id}")
	public Trans_bussiness_partner_addressDTO getTransBPAddr(@PathVariable(value = "bp_id") String bp_id)
	{
		return trans_bussiness_partnerService.getTransBPAddr(bp_id);
	}
	
	@GetMapping("/getTransBPAddrDtls/{bp_id}")
	public List<Trans_bussiness_partner_address_dtlsDTO> getTransBPAddrDtls(@PathVariable(value = "bp_id") String bp_id)
	{
		return trans_bussiness_partnerService.getTransBPAddrDtls(bp_id);
	}
	
	@GetMapping("/getTranstds/{bp_id}")
	public Trans_bussiness_partner_tds getTranstds(@PathVariable(value = "bp_id") String bp_id)
	{
		return trans_bussiness_partnerService.getTranstds(bp_id);
	}
	
	@GetMapping("/getTransBPStatu/{bp_id}")
	public Trans_bussiness_partner_statutoryDTO getTransBPStatu(@PathVariable(value = "bp_id") String bp_id)
	{
		return trans_bussiness_partnerService.getTransBPStatu(bp_id);
	}
	
	
	@GetMapping("/getTransBPDocs/{bp_id}")
	public List<Trans_bussiness_partner_docDTO> getTransBPDocs(@PathVariable(value = "bp_id") String bp_id)
	{
		return trans_bussiness_partnerService.getTransBPDocs(bp_id);
	}
	
	@GetMapping("/getTransBPVD/{bp_id}")
	public List<Trans_bussiness_partner_vehicle_dtlsDTO> getTransBPVD(@PathVariable(value = "bp_id") String bp_id)
	{
		return trans_bussiness_partnerService.getTransBPVD(bp_id);
	}
	
	
	//....................Supplier Master End.............................
	
	//....................INDENT ORDER START.............................
	
	@GetMapping("/getPurIndentDtls/{indentno}")
	public Pur_IndentDTO getPurIndentDtls(@PathVariable(value = "indentno") String indentno) {
		return pur_IndentService.getPurIndentDtls(indentno);
	}
	
	
	@GetMapping("/indentOrderRetrive/{id}")
	public ResponseEntity<Pur_Indent> indentRetrive(@PathVariable(value="id") Long id)
	{
		Pur_Indent tgs=pur_IndentService.findOne(id);
		if(tgs==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(tgs);
		}
	}

	@GetMapping("/indentOrderDocRetriveList/{code}")
	public List<Pur_Indent_DocDTO> indentOrderDocRetriveList(@PathVariable(value = "code") String code)
	{
		return pur_IndentService.indentOrderDocRetriveList(code);
	}
	//....................INDENT ORDER END.............................
	
	//....................PURCHASE ENQUIRY START.............................
	
		@GetMapping("/purEnquiryRetrive/{id}")
		public ResponseEntity<Pur_Enquiry> purEnqRetrive(@PathVariable(value="id") Long id)
		{
			Pur_Enquiry tgs=pur_EnquiryService.findOne(id);
			if(tgs==null)
			{
				return ResponseEntity.notFound().build();
			}
			else
			{
				return ResponseEntity.ok().body(tgs);
			}
		}
		
		@GetMapping("/getPurEnquiryItemDtlsList/{enq_id}")
		public List<Pur_Enquiry_Service_DetailsDTO> getPurEnquiryItemDtlsList(@PathVariable(value = "enq_id") String enq_id)
		{
			return pur_EnquiryService.getPurEnquiryItemDtlsList(enq_id);
		}
		
		
		@GetMapping("/getPurEnquiryBPDetails/{enq_id}")
		public List<Pur_Enquiry_BPartner_DetailsDTO> getPurEnquiryBPDetails(@PathVariable(value = "enq_id") String enq_id)
		{
			return pur_EnquiryService.getPurEnquiryBPDetails(enq_id);
		}
		
		@GetMapping("/getPurEnquiryDocList/{enq_id}")
		public List<Pur_Enquiry_DocDTO> getPurEnquiryDocList(@PathVariable(value = "enq_id") String enq_id)
		{
			return pur_EnquiryService.getPurEnquiryDocList(enq_id);
		}
		
	//....................PURCHASE ENQUIRY END.............................
		
	//....................PURCHASE QUOTATION START.............................
		
			@GetMapping("/purQuotationRetrive/{id}")
			public ResponseEntity<Pur_Quotation> purQtnRetrive(@PathVariable(value="id") Long id)
			{
				Pur_Quotation tgs=pur_QuotationService.findOne(id);
				if(tgs==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(tgs);
				}
			}
			
			@GetMapping("/getPurQuotDetails/{quotationno}")
			public Pur_QuotationDTO purQuotDetails(@PathVariable(value="quotationno") String quotationno)
			{
				return pur_QuotationService.purQuotDetails(quotationno);
			}
			
			
			@GetMapping("/purQuotServRetriveList/{code}")
			public List<Pur_Quotation_ServiceDTO> purQServRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_QuotationService.purQServRetriveList(code);
			}
			
			@GetMapping("/gePurQuotBPDetails/{code}")
			public Pur_quotation_Business_Partner_detailsDTO purQBPRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_QuotationService.purQBPRetriveList(code);
			}
			
			@GetMapping("/getPurQuotDocs/{code}")
			public List<Pur_Quotation_DocDTO> purQDocRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_QuotationService.purQDocRetriveList(code);
			}
			
	//....................PURCHASE QUOTATION END.............................
			
	//....................PURCHASE ORDER START.............................
			
			@GetMapping("/purOrderRetrive/{id}")
			public ResponseEntity<Pur_Order> purOrderRetrive(@PathVariable(value="id") Long id)
			{
				Pur_Order tgs=pur_OrderService.findOne(id);
				if(tgs==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(tgs);
				}
			}
			
			
			@GetMapping("/purOrdItemRetriveList/{code}")
			public List<Pur_Order_Item_DetailsDTO> purOrdItemRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_OrderService.purOrdItemRetriveList(code);
			}
			
			@GetMapping("/getPurOrdAppChgs/{orderid}")
			public List<Pur_Order_app_chgsDTO> getPurOrdAppChgs(@PathVariable(value = "orderid") String orderid)
			{
				return pur_OrderService.getPurOrdAppChgs(orderid);
			}
			
			@GetMapping("/getPurOrdTermList/{orderid}")
			public List<Pur_Order_Termination_dynDTO> getPurOrdTermList(@PathVariable(value = "orderid") String orderid)
			{
				return pur_OrderService.getPurOrdTermList(orderid);
			}
			
			@GetMapping("/purOrdBPDRetriveList/{code}")
			public Pur_Order_BPDetailsDTO purOrdBPDRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_OrderService.purOrdBPDRetriveList(code);
			}
			
			@GetMapping("/purOrdTerminateRetriveList/{code}")
			public Pur_Order_TerminationDTO purOrdTerminateRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_OrderService.purOrdTerminateRetriveList(code);
			}
			
			@GetMapping("/purOrdDocRetriveList/{code}")
			public List<Pur_Order_DocDTO> purOrdDocRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_OrderService.purOrdDocRetriveList(code);
			}
			
			@GetMapping("/purOrdTransConRetriveList/{code}")
			public Pur_Order_Terms_ConDTO purOrdTransConRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_OrderService.purOrdTransConRetriveList(code);
			}
			
			@GetMapping("/getPurOrdTermsCondDynList/{code}")
			public List<Map<String,Object>> getPurOrdTermsCondDynList(@PathVariable(value = "code") String code)
			{
				return pur_OrderService.getPurOrdTermsCondDynList(code);
			}
			
			@GetMapping("/purOrdItemwtHSNRetriveList/{code}")
			public List<Map<String,Object>> purOrdItemwtHSNRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_OrderService.purOrdItemwtHSNRetriveList(code);
			}
			
			@GetMapping("/getStoreChargesPo/{grnid}/{referance_type}")
			public Map<String,Object> getStoreChargesPo(@PathVariable(value = "grnid") String grnid,@PathVariable(value = "referance_type") String referance_type)
			{
				return pur_OrderService.getStoreChargesPo(grnid,referance_type);
			}
			
	//....................PURCHASE ORDER END.............................
			
	//....................PURCHASE ORDER START.............................
			
			
			@GetMapping("/purQChkRetriveList/{code}")
			public List<Pur_Quality_Check_DetailsDTO> purQChkRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_Quality_CheckService.purQChkRetriveList(code);
			}
			
	//....................PURCHASE ORDER END.............................
			
	//....................PURCHASE GOOD RECEIPT START.............................
			
			@GetMapping("/getPurGoodRcptList")
			public List<Pur_good_receiptDTO> getPurGoodRcptList() {
				return pur_good_receiptService.getPurGoodRcptList();
			}
			
			@GetMapping("/getPurGoodRcptThruSupp/{suppid}/{itype}/{ptype}/{psubtype}")
			public List<Pur_good_receiptDTO> getPurGoodRcptThruSupp(@PathVariable(value = "suppid") String suppid,
					@PathVariable(value = "itype") boolean itype,
					@PathVariable(value = "ptype") String ptype,
					@PathVariable(value = "psubtype") String psubtype) {
				return pur_good_receiptService.getPurGoodRcptThruSupp(suppid,itype,ptype,psubtype);
			}
			
			@GetMapping("/getPurGoodRcptDtls/{grnid}")
			public Pur_good_receiptDTO getPurGoodRcptDtls(@PathVariable(value = "grnid") String grnid) {
				return pur_good_receiptService.getPurGoodRcptDtls(grnid);
			}
			
			@GetMapping("/getPurGoodRcptDtlsopengrn/{grnid}")
			public Pur_good_receiptDTO getPurGoodRcptDtlsopengrn(@PathVariable(value = "grnid") String grnid) {
				return pur_good_receiptService.getPurGoodRcptDtlsopengrn(grnid);
			}
			@GetMapping("/getPurGoodRcptBPDtls/{grnid}")
			public Pur_good_receipt_Business_Partner_detailsDTO getPurGoodRcptBPDtls(@PathVariable(value = "grnid") String grnid)
			{
				return pur_good_receiptService.getPurGoodRcptBPDtls(grnid);
			}
			
			@GetMapping("/getPurGoodRcptItemDtlsList/{grnid}")
			public List<Pur_good_receipt_item_detailsDTO> getPurGoodRcptItemDtlsList(@PathVariable(value = "grnid") String grnid)
			{
				return pur_good_receiptService.getPurGoodRcptItemDtlsList(grnid);
			}
			
			
			@GetMapping("/getPurGoodRcptItemDtlsListfastapi/{grnid}")
			public List<Map<String, Object>> getPurGoodRcptItemDtlsListfastapi(@PathVariable(value = "grnid") String grnid)
			{
				return pur_good_receiptService.getPurGoodRcptItemDtlsListfastapi(grnid);
			}
			
			
			
			@GetMapping("/grnResourceCostRetriveList/{code}")
			public List<Pur_good_receipt_resource_costDTO> grnResourceCostRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_good_receiptService.grnResourceCostRetriveList(code);
			}
			
			@GetMapping("/grnTransInfoRetriveList/{code}")
			public Pur_good_reciept_trans_infoDTO grnTransInfoRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_good_receiptService.grnTransInfoRetriveList(code);
			}
			
			@GetMapping("/grndriverdetails/{code}")
			public Pur_good_receipt_driver_dtls grndriverdetails(@PathVariable(value = "code") String code)
			{
				return pur_good_receiptService.grndriverdetails(code);
			}
			
			
			@GetMapping("/grnOtherInfoRetriveList/{code}")
			public Pur_goods_receipt_other_informationDTO grnOtherInfoRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_good_receiptService.grnOtherInfoRetriveList(code);
			}
			
			@GetMapping("/getPurGoodRcptDocList/{grnid}")
			public List<Pur_good_receipt_docDTO> getPurGoodRcptDocList(@PathVariable(value = "grnid") String grnid)
			{
				return pur_good_receiptService.getPurGoodRcptDocList(grnid);
			}
			
			@GetMapping("/getPurGoodRcptBrokerList/{grnid}")
			public List<Pur_good_receipt_brokerDTO> getPurGoodRcptBrokerList(@PathVariable(value = "grnid") String grnid)
			{
				return pur_good_receiptService.getPurGoodRcptBrokerList(grnid);
			}
			
	//....................PURCHASE GOOD RECEIPT END.............................
			
	//....................L1 Selection START.............................
			
			@GetMapping("/purL1SelectionRetrive/{id}")
			public ResponseEntity<Pur_L1_Selection> purL1Selection(@PathVariable(value="id") Long id)
			{
				Pur_L1_Selection tgs=pur_L1_SelectionService.findOne(id);
				if(tgs==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(tgs);
				}
			}
			
			@GetMapping("/l1DtlsRetriveList/{code}")
			public List<Pur_L1_Selection_DtlsDTO> l1DtlsRetriveList(@PathVariable(value = "code") String code)
			{
				return pur_L1_SelectionService.l1DtlsRetriveList(code);
			}
			
	//....................L1 Selection END.............................
		//tag advice po retrive list start	
			
			@GetMapping("/tagadviceRetrive/{id}")
			public ResponseEntity<Tag_advice_with_po> tagadviceRetrive(@PathVariable(value="id") Long id)
			{
				Tag_advice_with_po tgs=tag_advice_with_poService.findOne(id);
				if(tgs==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(tgs);
				}
			}
			
	//end
			
   //....................PURCHASE WEIGHTMENT UNLOAD ADVICE START.............................
			
			@GetMapping("/unloadAdviceRetrive/{id}")
			public ResponseEntity<Wm_unload_advice> unloadAdvice(@PathVariable(value="id") Long id)
			{
				Wm_unload_advice tgs=wm_unload_adviceService.findOne(id);
				if(tgs==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(tgs);
				}
			}
			
			@GetMapping("/wmUnAdviceItemRetriveList/{code}")
			public List<Wm_unload_advice_item_dtlsDTO> wmUnAdviceItemRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_unload_adviceService.wmUnAdviceItemRetriveList(code);
			}
			
			@GetMapping("/wmUnAdvicePartyWmRetriveList/{code}")
			public Wm_unload_advice_party_wmDTO wmUnAdvicePartyWmRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_unload_adviceService.wmUnAdvicePartyWmRetriveList(code);
			}

			@GetMapping("/wmUnAdvicePartyWmRetriveListmultipopup/{code}")
			public Wm_unload_advice_party_wmDTO wmUnAdvicePartyWmRetriveListmultipopup(@PathVariable(value = "code") String code)
			{
				return wm_unload_adviceService.wmUnAdvicePartyWmRetriveListmultipopup(code);
			}
			
			@GetMapping("/wmUnAdviceDriverDtlsRetriveList/{code}")
			public Wm_unload_advice_driver_dtlsDTO wmUnAdviceDriverDtlsRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_unload_adviceService.wmUnAdviceDriverDtlsRetriveList(code);
			}
			
			@GetMapping("/wmUnAdviceBrokerRetriveList/{code}")
			public List<Wm_unload_advice_broker_dtlsDTO> wmUnAdviceBrokerRetriveList(@PathVariable(value = "code") String code)
			{
			
				return wm_unload_adviceService.wmUnAdviceBrokerRetriveList(code);
			}
			
			@GetMapping("/wmUnAdviceTransConRetriveList/{code}")
			public Wm_unload_advice_terms_conDTO wmUnAdviceTransConRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_unload_adviceService.wmUnAdviceTransConRetriveList(code);
			}
			
			@GetMapping("/wmUnAdviceTransInfoRetriveList/{code}")
			public Wm_unload_advice_trans_infoDTO wmUnAdviceTransInfoRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_unload_adviceService.wmUnAdviceTransInfoRetriveList(code);
			}
			
			@GetMapping("/wmUnAdviceBpDtlsRetriveList/{code}")
			public Wm_unload_advice_bp_dtlsDTO wmUnAdviceBpDtlsRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_unload_adviceService.wmUnAdviceBpDtlsRetriveList(code);
			}
			
			@GetMapping("/wmUnAdviceDocRetriveList/{code}")
			public List<Wm_unload_advice_docDTO> wmUnAdviceDocRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_unload_adviceService.wmUnAdviceDocRetriveList(code);
			}
			
			@GetMapping("/wmUnAdviceAppChgsRetriveList/{code}")
			public List<Wm_unload_advice_app_chgsDTO> wmUnAdviceAppChgsRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_unload_adviceService.wmUnAdviceAppChgsRetriveList(code);
			}
			
	//....................PURCHASE WEIGHTMENT UNLOAD ADVICE END.............................
	
	//....................PURCHASE UNLOAD WEIGHTMENT START.............................
			
			@GetMapping("/unloadWeightmentRetrive/{id}")
			public ResponseEntity<Wm_unload_wgmnt> unloadW(@PathVariable(value="id") Long id)
			{
				Wm_unload_wgmnt tgs=wm_unload_wgmntService.findOne(id);
				if(tgs==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(tgs);
				}
			}
			
			@GetMapping("/unloadWMDtlsRetriveList/{code}")
			public List<Wm_unload_wgmnt_dtlsDTO> unloadWMDtlsRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_unload_wgmntService.unloadWMDtlsRetriveList(code);
			}
			
	//....................PURCHASE UNLOAD WEIGHTMENT END.............................

   //....................PURCHASE UNLOAD WEIGHTMENT START.............................
			
			@GetMapping("/loadingItemRetriveList/{code}")
			public List<Wm_loading_advice_itm_dtlsDTO> loadingItemRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_loading_adviceService.loadingItemRetriveList(code);
			}
			
			
			@GetMapping("/loadingItemRetriveListprint/{code}")
			public List<Wm_loading_advice_itm_dtlsDTO> loadingItemRetriveListprint(@PathVariable(value = "code") String code)
			{
				return wm_loading_adviceService.loadingItemRetriveListprint(code);
			}
			
			@GetMapping("/loadingDriverRetriveList/{code}")
			public Wm_loading_advice_driver_dtlsDTO loadingDriverRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_loading_adviceService.loadingDriverRetriveList(code);
			}
			
			@GetMapping("/loadingTransInfoRetriveList/{code}/{company}")
			public Wm_loading_advice_trans_infoDTO loadingTransInfoRetriveList(@PathVariable(value = "code") String code,@PathVariable(value = "company") String company)
			{
				return wm_loading_adviceService.loadingTransInfoRetriveList(code,company);
			}
			
			@GetMapping("/loadingBPDtlsRetriveList/{code}")
			public Wm_loading_advice_bp_dtlsDTO loadingBPDtlsRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_loading_adviceService.loadingBPDtlsRetriveList(code);
			}
			
			@GetMapping("/loadingDocRetriveList/{code}")
			public List<Wm_loading_advice_doc_attchDTO> loadingDocRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_loading_adviceService.loadingDocRetriveList(code);
			}
			
			/*@GetMapping("/loadingAdviceBrokerDtls/{code}")
			public List<Wm_loading_advice_broker_dtlsDTO> loadingAdviceBrokerDtls(@PathVariable(value = "code") String code)
			{
				System.out.println("loadingAdviceBrokerDtls: "+code);
				return wm_loading_adviceService.loadingAdviceBrokerDtls(code);
			}*/
			
			
			
	//....................PURCHASE UNLOAD WEIGHTMENT END.............................
			
    //....................PURCHASE UNLOAD WEIGHTMENT START.............................
			
			@GetMapping("/wmLoadingDtlsRetriveList/{code}")
			public List<Wm_loading_wgmnt_dtlsDTO> wmLoadingDtlsRetriveList(@PathVariable(value = "code") String code)
			{
				return wm_loading_wgmntService.wmLoadingDtlsRetriveList(code);
			}
			
	//....................PURCHASE UNLOAD WEIGHTMENT END.............................
			
			//....................PURCHASE BILL START.............................		
			@Autowired
			Pur_BillService pur_BillService;
			
			
			@GetMapping("/purBillRetrive/{id}")
			public ResponseEntity<Pur_Bill> purBillRetrive(@PathVariable(value="id") Long id)
			{
				Pur_Bill tgs=pur_BillService.findOne(id);
				if(tgs==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(tgs);
				}
			}
			
			
			@GetMapping("/purBillItemRetriveList/{pbid}")
			public List<Pur_Bill_Item_DetailsDTO> purBillItemRetriveList(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.purBillItemRetriveList(pbid);
			}
			
			@GetMapping("/purBillItemRetriveListPrint/{pbid}")
			public List<Pur_Bill_Item_DetailsDTO> purBillItemRetriveListPrint(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.purBillItemRetriveListPrint(pbid);
			}
			
			@GetMapping("/getpurbillprintupperdata/{pbid}")
			public List<Map<String,Object>> getpurbillprintupperdata(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.getpurbillprintupperdata(pbid);
			}
			
			@GetMapping("/purBillAppChargesRetriveList/{pbid}")
			public List<Pur_Bill_app_chgs> purBillAppChargesRetriveList(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.purBillAppChargesRetriveList(pbid);
			}
			
			@GetMapping("/purBillStoreChargesRetriveList/{pbid}")
			public List<Map<String,Object>> purBillStoreChargesRetriveList(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.purBillStoreChargesRetriveList(pbid);
			}
			
			
			@GetMapping("/purBillCharMatrixposting/{pbid}")
			public List<Pur_Bill_app_chgs> purBillCharMatrixposting(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.purBillCharMatrixposting(pbid);
			}
			
			@GetMapping("/purBillMusterRetriveList/{pbid}")
			public List<Pur_Bill_Musterroll_DetailsDTO> purBillMusterRetriveList(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.purBillMusterRetriveList(pbid);
			}
			
			@GetMapping("/gePurBillTaxInfo/{pbid}")
			public Pur_Bill_Tax_InfoDTO gePurBillTaxInfo(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.gePurBillTaxInfo(pbid);
			}
			
			@GetMapping("/purBillBrokerRetriveList/{pbid}")
			public List<Pur_Bill_Broker_DetailsDTO> purBillBrokerRetriveList(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.purBillBrokerRetriveList(pbid);
			}
			
			@GetMapping("/gePurBillBPRetrive/{pbid}")
			public Pur_Bill_Bp_DetailsDTO gePurBillBPRetrive(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.gePurBillBPRetrive(pbid);
			}
			
			@GetMapping("/purBillDocRetriveList/{pbid}")
			public List<Pur_Bill_DocsDTO> purBillDocRetriveList(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.purBillDocRetriveList(pbid);
			}
			
			@GetMapping("/gePurBillAccRetrive/{pbid}")
			public Pur_Bill_Account_InfoDTO gePurBillAccRetrive(@PathVariable(value = "pbid") String pbid)
			{
				return pur_BillService.gePurBillAccRetrive(pbid);
			}
			
			
			//gatepass retrive starts 
			@Autowired
			GatepassGetinService gatepassGetinService;
			
			@Autowired
			GatepassGateoutAuthorizationService gatepassGateoutAuthorizationService;
			
			@Autowired
			GatepassChecklistService gatepassChecklistService;
			
			@Autowired
			GatepassGateoutService gatepassGateoutService;
			
			@GetMapping("/getGatepassgetinretrivebyid/{id}")
			public ResponseEntity<GatepassGetin> getGatepassgetinretrivebyid(@PathVariable(value="id") Long id)
			{
				GatepassGetin op=gatepassGetinService.findOne(id);
				if(id==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(op);
				}
			}
			
			
			
			@GetMapping("/getGatepassgetinretrivedetails/{gp_gi_id}")
			public List<GatepassGetin_details> getGatepassgetinretrivedetails(@PathVariable(value="gp_gi_id") String gp_gi_id)
			{
				return gatepassGetinService.getGatepassgetinretrivedetails(gp_gi_id);
				
			}
			
			
			//auth
			
			
			@GetMapping("/getGatepassgetoutaretrivebyid/{id}")
			public ResponseEntity<GatepassGateoutAuthorization> getGatepassgetoutaretrivebyid(@PathVariable(value="id") Long id)
			{
				GatepassGateoutAuthorization op=gatepassGateoutAuthorizationService.findOne(id);
				if(id==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(op);
				}
			}
			
			
			
			@GetMapping("/getGatepassgetoutaretrivedetails/{gp_go_auth_id}")
			public List<GatepassGateoutAuthorization_details> getGatepassgetoutaretrivedetails(@PathVariable(value="gp_go_auth_id") String gp_go_auth_id)
			{
				return gatepassGateoutAuthorizationService.getGatepassgetoutaretrivedetails(gp_go_auth_id);
				
			}
			
//Gate pass retrive start
			
			@GetMapping("/retriveGatepassGateOut/{id}")
			public ResponseEntity<GatepassGateout> retriveGatepassGateOut(@PathVariable(value="id") Long id)
			{
				GatepassGateout tgs=gatepassGateoutService.findOne(id);
				if(tgs==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(tgs);
				}
			}
			
			
			@GetMapping("/retriveGatepassGateOutDetails/{gp_go_id}")
			public List<GatepassGateout_details> retriveGatepassGateOutDetails(@PathVariable(value = "gp_go_id") String gp_go_id)
			{
				return gatepassGateoutService.retriveGatepassGateOutDetails(gp_go_id);
			}
			
			@GetMapping("/retriveGatepassChkList/{id}")
			public ResponseEntity<GatepassChecklist> retriveGatepassChkList(@PathVariable(value="id") Long id)
			{
				GatepassChecklist tgs=gatepassChecklistService.findOne(id);
				if(tgs==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(tgs);
				}
			}
			
			//Gate pass retrive end
			
			//auth ends
			//gatepass ends 
}
