package com.AnkitIndia.jwtauthentication.controller;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.AnkitIndia.jwtauthentication.model.User_Role_Access;

import com.AnkitIndia.jwtauthentication.model.Acc_group_master;
import com.AnkitIndia.jwtauthentication.model.Acc_pay_term_master;
import com.AnkitIndia.jwtauthentication.model.Bin_master;
import com.AnkitIndia.jwtauthentication.model.Bingroup;

import com.AnkitIndia.jwtauthentication.model.Business_unit;

import com.AnkitIndia.jwtauthentication.model.Company_master;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_address;
import com.AnkitIndia.jwtauthentication.model.Cust_group;
import com.AnkitIndia.jwtauthentication.model.Custom_uom_master;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Docs;
import com.AnkitIndia.jwtauthentication.model.Delivery_challan_Item_Dtls;
import com.AnkitIndia.jwtauthentication.model.Financialyear;
import com.AnkitIndia.jwtauthentication.model.Gst_input_output_ledger_dtls;
import com.AnkitIndia.jwtauthentication.model.Ledgermaster;
import com.AnkitIndia.jwtauthentication.model.Misc_master;
import com.AnkitIndia.jwtauthentication.model.Process_master_doc;
import com.AnkitIndia.jwtauthentication.model.Pur_Enquiry;
import com.AnkitIndia.jwtauthentication.model.Pur_Indent;

import com.AnkitIndia.jwtauthentication.model.Purchase_register;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice_Docs;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_dynamic;

import com.AnkitIndia.jwtauthentication.model.Sales_register;

import com.AnkitIndia.jwtauthentication.model.State_master;
import com.AnkitIndia.jwtauthentication.model.Subgroupmaster;
import com.AnkitIndia.jwtauthentication.model.Tax_code_details;
import com.AnkitIndia.jwtauthentication.model.Tax_code_master;
import com.AnkitIndia.jwtauthentication.model.Tax_type_master;
import com.AnkitIndia.jwtauthentication.model.TransportationChgsMatrixMaster;
import com.AnkitIndia.jwtauthentication.model.User;
import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.model.VehicleTypeMaster;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Warehouse_master;
import com.AnkitIndia.jwtauthentication.model.Weighment_doc;
import com.AnkitIndia.jwtauthentication.repository.FinancialyearRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_acceptance_norms_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_acceptance_norms_master_dtsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_cost_category_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_cost_centre_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_gen_ledger_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_pay_mode_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_pay_term_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_taxcode_details_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Area_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.BinMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Broker_groupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Broker_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Channel_customer_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Channel_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Charge_MasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Charge_MasterdtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.City_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitMasterDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Country_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerGroupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_address_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_delv_toDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_groupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.CustomUomMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.DateDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.DepartmentMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.DesignationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.District_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Driver_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.EmployeeMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.EmployeeMasterDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.GroupmasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_catagory_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_type_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.LedgermasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MiscMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Otherpartner_groupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Process_master_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_EnquiryDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Enquiry_Service_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_IndentDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Indent_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Item_DetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Trans_InfoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_QuotationDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quotation_BrokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Quotation_ServiceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Purchase_register_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Purpose_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Qc_rules_setupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Reason_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_dynamic_sortDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_inv_dynamic_sortingDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_register_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Screen_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.State_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Status_tableDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SubgroupmasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partnerDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_or_Customer_bussinessDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supplier_groupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.TaxTypeMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tax_code_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tds_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Trans_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.TransportationChgsMatrixMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Transporter_groupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.UserDTO;

import com.AnkitIndia.jwtauthentication.responseDTO.VehicleMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.VehicleTypeMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.WarehouseMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_loading_adviceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_adviceDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_party_wmDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_advice_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_wgmntDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.sales_reg_dynamicDTO;
import com.AnkitIndia.jwtauthentication.security.services.Acc_acceptance_norms_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_cost_category_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_cost_centre_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_gen_ledger_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_group_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_pay_mode_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_pay_term_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Acc_taxcode_details_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Area_masterService;
import com.AnkitIndia.jwtauthentication.security.services.BinMasterService;
import com.AnkitIndia.jwtauthentication.security.services.BingroupService;
import com.AnkitIndia.jwtauthentication.security.services.Broker_groupService;
import com.AnkitIndia.jwtauthentication.security.services.Broker_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Broker_templtService;
import com.AnkitIndia.jwtauthentication.security.services.Channel_customer_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Channel_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.Charge_MasterService;
import com.AnkitIndia.jwtauthentication.security.services.City_masterService;
import com.AnkitIndia.jwtauthentication.security.services.CompanyBusinessUnitMasterService;
import com.AnkitIndia.jwtauthentication.security.services.CompanyMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Country_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Cust_bussiness_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.Cust_groupService;
import com.AnkitIndia.jwtauthentication.security.services.CustomUomMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Delivery_challanService;
import com.AnkitIndia.jwtauthentication.security.services.DepartmentMasterService;
import com.AnkitIndia.jwtauthentication.security.services.DesignationService;
import com.AnkitIndia.jwtauthentication.security.services.District_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Driver_masterService;
import com.AnkitIndia.jwtauthentication.security.services.EmployeeMasterService;
import com.AnkitIndia.jwtauthentication.security.services.GroupmasterService;
import com.AnkitIndia.jwtauthentication.security.services.Item_catagory_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Item_group_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Item_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Item_type_masterService;
import com.AnkitIndia.jwtauthentication.security.services.LedgermasterService;
import com.AnkitIndia.jwtauthentication.security.services.Loading_pointService;
import com.AnkitIndia.jwtauthentication.security.services.MiscMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Op_bussiness_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.OtherItemMasterService;
import com.AnkitIndia.jwtauthentication.security.services.OtherPartyMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Otherpartner_groupService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_EnquiryService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_IndentService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_OrderService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_QuotationService;
import com.AnkitIndia.jwtauthentication.security.services.Pur_good_receiptService;
import com.AnkitIndia.jwtauthentication.security.services.Purchase_reg_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Purchase_registerService_Imp;
import com.AnkitIndia.jwtauthentication.security.services.Purchase_register_detailsService;
import com.AnkitIndia.jwtauthentication.security.services.Purpose_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Qc_rules_setupService;
import com.AnkitIndia.jwtauthentication.security.services.RatechartService;
import com.AnkitIndia.jwtauthentication.security.services.Reason_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Return_approval_noteService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_InvoiceService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_OrderService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_inv_dynamic_sortingService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_reg_dynamicService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_reg_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_registerService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_registerService_Imp;
import com.AnkitIndia.jwtauthentication.security.services.Sales_register_detailsService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_return_noteService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_transportService;
import com.AnkitIndia.jwtauthentication.security.services.Screen_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Shop_floor_masterService;
import com.AnkitIndia.jwtauthentication.security.services.State_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Store_inv_charge_masterService;
import com.AnkitIndia.jwtauthentication.security.services.SubgroupmasterService;
import com.AnkitIndia.jwtauthentication.security.services.Supp_bussiness_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.Supplier_groupService;
import com.AnkitIndia.jwtauthentication.security.services.Tag_advice_with_poService;
import com.AnkitIndia.jwtauthentication.security.services.TaxTypeMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Tax_code_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Tds_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Trans_bussiness_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.TransportationChgsMatrixMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Transporter_groupService;
import com.AnkitIndia.jwtauthentication.security.services.UserService;
import com.AnkitIndia.jwtauthentication.security.services.User_Role_AccessService;
import com.AnkitIndia.jwtauthentication.security.services.VehicleMasterService;
import com.AnkitIndia.jwtauthentication.security.services.VehicleTypeMasterService;
import com.AnkitIndia.jwtauthentication.security.services.WarehouseMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_charges_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_loading_adviceService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_unload_adviceService;
import com.AnkitIndia.jwtauthentication.security.services.Wm_unload_wgmntService;
import com.AnkitIndia.jwtauthentication.security.services.Zone_masterService;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_accountDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_addDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_add_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_docDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_othDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_ptyDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_statutoryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_transporterDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Broker_master_vdrDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_master_pack_mat_tagDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_OrderDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Pur_Order_brokerDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_addr_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_brokerDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_delv_fromDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_statutoryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Tax_code_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Trans_bussiness_partner_accontDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Trans_bussiness_partner_vehicle_dtlsDTO;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
//@RequestMapping("/MeghnaBidi")
//@RequestMapping("/MeghanaPanMasala")
//@RequestMapping("/IndianSpices")
public class ListController { 
	
	@Autowired
	Purpose_masterService purpose_masterService;
	
	@Autowired
	Item_type_masterService item_type_masterService;
	
	@Autowired
	Item_group_masterService item_group_masterService;
	
	@Autowired
	Item_catagory_masterService iCatagory_masterService;
	
	@Autowired
	SubgroupmasterService subgroupmasterService;

	@Autowired
	Tax_code_masterService tax_code_masterService;
	
	@Autowired
	Item_masterService item_masterService;
	
	@Autowired
	CustomUomMasterService customUomMasterService;
	
	@Autowired
	WarehouseMasterService warehouseMasterService;
	
	@Autowired
	Acc_acceptance_norms_masterService acc_acceptance_norms_masterService;
	
	@Autowired
	Acc_taxcode_details_masterService acc_taxcode_details_masterService;
	
	@Autowired
	Acc_pay_mode_masterService acc_pay_mode_masterService;
	
	@Autowired
	Qc_rules_setupService qc_rules_setupService;
	
	@Autowired
	Acc_gen_ledger_masterService acc_gen_ledger_masterService;
	
	@Autowired
	DesignationService designationService;
	
	@Autowired
	Reason_masterService reason_masterService;
	
	@Autowired
	Screen_masterService screen_masterService;
	
	@Autowired
	Tds_masterService tds_masterService;
	
	@Autowired
	EmployeeMasterService employeeMasterService;
	
	@Autowired
	Driver_masterService driver_masterService;
	
	@Autowired
	Channel_customer_masterService channel_customer_masterService;
	
	@Autowired
	Wm_unload_adviceService wm_unload_adviceService;
	
	@Autowired
	Wm_loading_adviceService wm_loading_adviceService;
	
	@Autowired
	Sales_registerService sales_registerService;
	
	@Autowired
	Tag_advice_with_poService tag_advice_with_poService;
	
	@Autowired
	Pur_good_receiptService pur_good_receiptService;
	
	@Autowired
	Delivery_challanService delivery_challanService;
	
	@Autowired
	Sales_return_noteService sales_return_noteService;
	
	@Autowired
	Return_approval_noteService return_approval_noteService;
	
	@Autowired
	Shop_floor_masterService shop_floor_masterService;
	
	@Autowired
	Zone_masterService zone_masterService;
	
	@Autowired
	Wm_charges_masterService wm_charges_masterService;
	
	@Autowired
	Loading_pointService loading_pointService;
	
	@Autowired
	BingroupService bingroupService;
	
	@Autowired
	Sales_OrderService sales_orderService;
	
	@Autowired
	RatechartService ratechartService;
	
	@Autowired
	Sales_InvoiceService sales_InvoiceService;
	
	@Autowired
	Sales_transportService sales_transportService;
	
	@Autowired
	OtherItemMasterService otherItemMasterService;
	
	@Autowired
	OtherPartyMasterService otherPartyMasterService;
	
	@Autowired
	Store_inv_charge_masterService store_inv_charge_masterService;
	
	@Autowired
	FinancialyearRepository financialyearRepository;
	
	
	@GetMapping("/getfinyearlist")
	public List<Financialyear> getfinyearlist()
	{
		return financialyearRepository.getfinyearlist();
	}
	
	
	@GetMapping("/getBusiUnitStateStatus/{bunit}/{dbunit}")
	public ResponseEntity<StatusDTO> getBusiUnitStateStatus(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "dbunit") String dbunit) 
	{
		StatusDTO val=companyBusinessUnitMasterService.getBusiUnitStateStatus(bunit,dbunit);
		System.err.println("GOT VAL--------->"+val);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/chkDriverStatus/{doc}")
	public ResponseEntity<List<Driver_masterDTO>> chkDriverStatus(@PathVariable(value = "doc") String doc) 
	{
		List<Driver_masterDTO> val=driver_masterService.chkDriverStatus(doc);
		System.err.println("GOT VAL--------->"+val);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping(value = "/getChannelCustDesc")
	public ResponseEntity<List<Channel_customer_masterDTO>> getChannelCustDesc() 
	{
		List<Channel_customer_masterDTO> val=channel_customer_masterService.getChannelCustDesc();
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@RequestMapping(value = "/getPurTermReasons", method = RequestMethod.GET)
	public ResponseEntity<List<Reason_masterDTO>> getPurTermReasons() 
	{
		List<Reason_masterDTO> val=reason_masterService.getPurTermReasons();
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/wnUnAdvicebalancedtotalquantity/{orderid}")
	public Pur_OrderDTO wnUnAdvicebalancedtotalquantity(@PathVariable(value = "orderid") String orderid)
	{
		return pur_OrderService.wnUnAdvicebalancedtotalquantity(orderid);
	}
	
	@GetMapping("/getDriverByVehicle/{vid}")
	public ResponseEntity<List<Driver_masterDTO>> getDriverByVehicle(@PathVariable(value = "vid") String vid) 
	{
		List<Driver_masterDTO> val=driver_masterService.getDriverByVehicle(vid);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getDriverList")
	public ResponseEntity<List<Driver_masterDTO>> getDriverList() 
	{
		List<Driver_masterDTO> val=driver_masterService.getDriverList();
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getDriverListnew")
	public List<Map<String,Object>> getDriverListnew() 
	{
		return driver_masterService.getDriverListnew();
	}
	
	@GetMapping("/getDriverDetails/{did}")
	public ResponseEntity<Driver_masterDTO> getDriverDetails(@PathVariable(value = "did") String did) 
	{
		Driver_masterDTO val=driver_masterService.getDriverDetails(did);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	
	@GetMapping("/getTransListByVlno/{vno}")
	public List<Trans_bussiness_partner_vehicle_dtlsDTO> getTransListByVlno(@PathVariable(value = "vno") String vno)
	{
		return trans_bussiness_partner.getTransListByVlno(vno);
	}
	
	
	
	@GetMapping("/getSuppContactNameList/{bp_id}")
	public List<Supp_bussiness_partner_delv_fromDTO> getSuppContactNameList(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSuppContactNameList(bp_id);
	}
	
	@GetMapping("/getSuppContById/{bp_id}")
	public List<Supp_bussiness_partner_addr_dtlsDTO> getSuppContById(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSuppContById(bp_id);
	}
	
	@GetMapping("/getSupplierContact")
	public List<Supp_bussiness_partner_addr_dtlsDTO> getSupplierContact()
	{
		return supp_bussiness_partnerService.getSupplierContact();
	}
	
	@GetMapping("/getSuppphoneByIdName/{bp_id},{CP}")
	public Supp_bussiness_partner_addr_dtlsDTO getSuppphoneByIdName(@PathVariable(value = "bp_id") String bp_id,@PathVariable(value = "CP") String CP)
	{
		return supp_bussiness_partnerService.getSuppphoneByIdName(bp_id,CP);
	}
	
	@GetMapping("/getSupplierAddrFast/{bp_id}")
	public Map<String, Object> getSupplierAddrFast(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSupplierAddrFast(bp_id);
	}
	
	@GetMapping("/getSupplierAddr/{bp_id}")
	public Supp_bussiness_partner_addressDTO getSupplierAddr(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSupplierAddr(bp_id);
	}
	
	@GetMapping("/getSupplierContDetails/{sbp_id}/{cp}")
	public Supp_bussiness_partner_addr_dtlsDTO getSupplierContDetails(@PathVariable(value = "sbp_id") String sbp_id,@PathVariable(value = "cp") String cp)
	{
		return supp_bussiness_partnerService.getSupplierContDetails(sbp_id,cp);
	}
	
	@GetMapping("/getSupplierAddress/{code}")
	public String getSupplierAddress(@PathVariable(value = "code") String code)
	{
		return supp_bussiness_partnerService.getSupplierAddress(code);
	}
	
	@GetMapping("/getSupplierStatDtls/{bp_id}")
	public ResponseEntity<Supp_bussiness_partner_statutoryDTO> getSupplierStatDtls(@PathVariable(value = "bp_id") String bp_id) 
	{
		Supp_bussiness_partner_statutoryDTO val=supp_bussiness_partnerService.getSupplierStatDtls(bp_id);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("getSuppDelvAddress/{bp_id}/{bu_name}")
	public Supp_bussiness_partner_delv_fromDTO getSuppDelvAddress(@PathVariable(value = "bp_id") String bp_id,@PathVariable(value = "bu_name") String bu_name)
	{
		return supp_bussiness_partnerService.getSuppDelvAddress(bp_id,bu_name);
	}
	
	@GetMapping("/getCustDelvFromList/{code}")
	public List<Cust_bussiness_partner_delv_toDTO> getCustDelvFromList(@PathVariable(value = "code") String code)
	{
		List<Cust_bussiness_partner_delv_toDTO> unitList= new ArrayList<Cust_bussiness_partner_delv_toDTO>();
		
		//Cust_bussiness_partner_delv_toDTO def=new Cust_bussiness_partner_delv_toDTO("0","0",0L,"Choose an Option","0","0","0","0",0L,0L,0L,"0","0","0","0","0","0","0");
		Cust_bussiness_partner_delv_toDTO def=new Cust_bussiness_partner_delv_toDTO();
		def.setCp_Id("0");
		def.setB_unit_name("Choose an Option");
		unitList.add(def);
		unitList.addAll(cust_bussiness_partnerService.getCustDelvFromList(code));
		
		return unitList;
	}
	
	@GetMapping("/findCustDelvFromList/{code}")
	public List<Cust_bussiness_partner_delv_toDTO> findCustDelvFromList(@PathVariable(value = "code") String code)
	{
		List<Cust_bussiness_partner_delv_toDTO> unitList= new ArrayList<Cust_bussiness_partner_delv_toDTO>();
		unitList.addAll(cust_bussiness_partnerService.getCustDelvFromList(code));
		return unitList;
	}
	
	@GetMapping("/getCustomerAddress/{custid}")
	public Cust_bussiness_partner_addressDTO getCustomerAddress(@PathVariable(value = "custid") String custid)
	{
		return cust_bussiness_partnerService.getCustomerAddress(custid);
	}
	
	@GetMapping("/getCustContDetails/{cbp_id}/{cp}")
	public ResponseEntity<Cust_bussiness_partner_address_dtlsDTO> getCustContDetails(@PathVariable(value = "cbp_id") String cbp_id,@PathVariable(value = "cp") String cp)
	{
		Cust_bussiness_partner_address_dtlsDTO val=cust_bussiness_partnerService.getCustContDetails(cbp_id,cp);
		System.err.println("Got val:"+val);
		if(val==null)
		{
			System.err.println("False");
			return ResponseEntity.notFound().build();
		}
		else
		{
			System.err.println("True");
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getCustDelvFromAdd")
	public ResponseEntity<Cust_bussiness_partner_delv_toDTO> getCustDelvFromAdd(@RequestParam(defaultValue = "") String cbpid,@RequestParam(defaultValue = "") String bunit)
	{
		System.err.println("First Id: "+cbpid+" unit: "+bunit);
		Cust_bussiness_partner_delv_toDTO cToDTO = cust_bussiness_partnerService.getCustDelvFromAdd(cbpid,bunit);
		return new ResponseEntity<Cust_bussiness_partner_delv_toDTO>(cToDTO, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getSuppBUnitName")
	public List<Supp_bussiness_partner_delv_fromDTO> getSuppBUnitName()
	{
		return supp_bussiness_partnerService.getSuppBUnitName();
	}
	
	@GetMapping("/getSuppBUnitAddr/{bp_id}")
	public Supp_bussiness_partner_delv_fromDTO getSuppBUnitAddr(@PathVariable(value = "bp_id") String bp_id)
	{
		return supp_bussiness_partnerService.getSuppBUnitAddr(bp_id);
	}
	
	@GetMapping(value = "/getEmployeeName")
	public List<EmployeeMasterDTO> getEmployeeName(@RequestParam("company") String company) {
		
		List<EmployeeMasterDTO> empList= new ArrayList<EmployeeMasterDTO>();
		
		EmployeeMasterDTO def=new EmployeeMasterDTO("0","Choose an Option","0","0","0","0","0","0","0","0","0",	0L,	0L);
		empList.add(def);
		empList.addAll(employeeMasterService.getEmployeeName(company));
		
		return empList;
	}
	
	@GetMapping(value = "/getEmployeeNamenew")
    public List<EmployeeMasterDTOC> getEmployeeNamenew(@RequestParam("company") String company) {
	
		return employeeMasterService.getEmployeeNamenew(company);
	}
	
	@GetMapping("/employeeAdminNamesList/{company}")
	public List<Map<String, Object>> employeeAdminNamesList(@PathVariable(value = "company") String company)
	{
		return employeeMasterService.employeeAdminNamesList(company);
	}
	
	@RequestMapping(value = "/getReasonList", method = RequestMethod.GET)
	public List<Reason_masterDTO> getReasonMNCList() {
		
		List<Reason_masterDTO> reasonList= new ArrayList<Reason_masterDTO>();
		
		Reason_masterDTO def=new Reason_masterDTO("0","0","Choose an Option");
		reasonList.add(def);
		reasonList.addAll(reason_masterService.getReasonMNCList());
		
		return reasonList;
	}
	
	@RequestMapping(value = "/getReasonIndent", method = RequestMethod.GET)
	public List<Reason_masterDTO> getReasonIndent() {
		return reason_masterService.getReasonIndent();
	}
	
	@RequestMapping(value = "/getScreenList", method = RequestMethod.GET)
	public List<Screen_masterDTO> getScreenMNCList() {
		return screen_masterService.getScreenMNCList();
	}
	
	@RequestMapping(value = "/getTDSList", method = RequestMethod.GET)
	public List<Tds_masterDTO> getTdsMNCList() {
		return tds_masterService.getTdsMNCList();
	}
	
	@RequestMapping(value = "/getTDSRate/{tdsid}", method = RequestMethod.GET)
	public Tds_masterDTO getTDSRate(@PathVariable(value = "tdsid") String tdsid) {
		return tds_masterService.getTDSRate(tdsid);
	}
	
	@RequestMapping(value = "/getPurposeList", method = RequestMethod.GET)
	public List<Purpose_masterDTO> getPurposeList() {
		return purpose_masterService.getPurposeList();
	}
	
	
	@GetMapping("/getDesigNameByCode/{code}")
	public DesignationDTO getDesigNameByCode(@PathVariable(value = "code") String code)
	{
		return designationService.getDesigNameToCode(code);
	}
	
	@RequestMapping(value = "/getDesignation", method = RequestMethod.GET)
	public List<DesignationDTO> getDesignation() {
		return designationService.getDesignation();
	}
	
	@GetMapping(value = "/designationListNew")
	public List<Map<String,Object>> designationListNew(){
		return designationService.designationListNew();
	}
	
	@GetMapping("/getUomByIGroup/{code}")
	public CustomUomMasterDTO getUomByIGroup(@PathVariable(value = "code") String code)
	{
		return customUomMasterService.getUomByIGroup(code);
	}
	
	@GetMapping("/getUomList")
	public List<Map<String,Object>> getUomList()
	{
		return customUomMasterService.getUomList();
	}
	
	@GetMapping("/getCustomUOMs/{uomtype}")
	public List<CustomUomMasterDTO> getCustomUOMs(@PathVariable(value = "uomtype") String uomtype)
	{
		List<CustomUomMasterDTO> uomList= new ArrayList<CustomUomMasterDTO>();
		CustomUomMasterDTO def=new CustomUomMasterDTO("0","0","Choose an Option","0","0","0","0",false,"0","0",0,0,"0");
		uomList.add(def);
		uomList.addAll(customUomMasterService.getCustomUOMs(uomtype));
		
		return uomList;
	}
	
	@GetMapping("/getCustomUomsbyname/{uomname}")
	public Custom_uom_master getCustomUomsbyname(@PathVariable(value = "uomname") String uomname)
	{
		return customUomMasterService.getCustomUomsbyname(uomname);
	}
		
	
	
	
	@GetMapping("/getCustomUomById/{cuomid}")
	public CustomUomMasterDTO getCustomUomById(@PathVariable(value = "cuomid") String cuomid)
	{
		return customUomMasterService.getCustomUomById(cuomid);
	}
	
	@RequestMapping(value = "/getAccLedgerList", method = RequestMethod.GET)
	public List<Acc_gen_ledger_masterDTO> getAccLedgerList() {
		return acc_gen_ledger_masterService.getAccLedgerList();
	}
	
	@RequestMapping(value = "/brokerMsNameList", method = RequestMethod.GET)
	public List<Broker_masterDTO> getbrokerMsNameList() {
		return broker_masterService.getbrokerMsNameList();
	}
	
	
	@GetMapping("/getVehicleTypeNByCode/{code}")
	public VehicleTypeMasterDTO getVehicleTypeNByCode(@PathVariable(value = "code") String code)
	{
		return vehicleTypeMasterService.getVehicleTypeNByCode(code);
	}
	
	@RequestMapping(value = "/getCompanyNameCode", method = RequestMethod.GET)
	public List<CompanyMasterDTO> getCompanyNameCode() {
		return companyMasterService.getCompanyNameCode();
	}
	
	@RequestMapping(value = "/getWarehouseNameCode", method = RequestMethod.GET)
	public List<WarehouseMasterDTO> getWarehouseNameCode() {
		return warehouseMasterService.getWarehouseNameCode();
	}
	
	@RequestMapping(value = "/getBinNameCode", method = RequestMethod.GET)
	public List<BinMasterDTO> getBinNameCode() {
		return binMasterService.getBinNameCode();
	}
	
	
	@GetMapping(value = "/getbinlist")
	public List<BinMasterDTO> getbinlist() {
		return binMasterService.getbinlist();
	}
    
	@GetMapping("/getbinlistbygroup/{bingroupid}/{finyear}")
    public List<BinMasterDTO> getbinlistbygroup(@PathVariable(value = "bingroupid") String bingroupid,@PathVariable(value = "finyear") String finyear)
	{
		return binMasterService.getbinlistbygroup(bingroupid,finyear);
	}
	
	
	@GetMapping("/checkbingroup/{bingroupid}/{binid}/{finyear}")
    public StatusDTO checkbingroup(@PathVariable(value = "bingroupid") String bingroupid,@PathVariable(value = "binid") String binid,@PathVariable(value = "finyear") String finyear)
	{
		return binMasterService.checkbingroup(bingroupid,binid,finyear);
	}
	
	@GetMapping("/bincalculation/{binid}/{dip}")
    public StatusDTO bincalculation(@PathVariable(value = "binid") String binid,@PathVariable(value = "dip") Double dip)
	{
		return binMasterService.bincalculation(binid,dip);
	}
	
	@GetMapping(value = "/getBinDescByWarehouse/{wcode}")
	public List<BinMasterDTO> getBinDescByWarehouse(@PathVariable(value = "wcode") String wcode) {
		return binMasterService.getBinDescByWarehouse(wcode);
	}
	
	@RequestMapping(value = "/getDeptNameCode", method = RequestMethod.GET)
	public List<DepartmentMasterDTO> getDeptNameCode() {
		return departmentMasterService.getDeptNameCode();
	}
	
	@RequestMapping(value = "/getMiscNameCode", method = RequestMethod.GET)
	public List<MiscMasterDTO> getMiscNameCode() {
		return miscMasterService.getMiscNameCode();
	}
	
	@GetMapping(value = "/getMiscList")
	public List<MiscMasterDTO> getMiscList() {
		List<MiscMasterDTO> miseList= new ArrayList<MiscMasterDTO>();
		
		MiscMasterDTO def=new MiscMasterDTO("0","Choose an Option","Choose an Option",false,"0","0","0");
		miseList.add(def);
		miseList.addAll(miscMasterService.getMiscList());
		
		return miseList;
	}
	
	@RequestMapping(value = "/getTaxTypeNameCode", method = RequestMethod.GET)
	public List<TaxTypeMasterDTO> getTaxTypeNameCode() {
		return taxTypeMasterService.getTaxTypeNameCode();
	}
	
	@RequestMapping(value = "/getVehicleTNameCode", method = RequestMethod.GET)
	public List<VehicleTypeMasterDTO> getVehicleTNameCode() {
		return vehicleTypeMasterService.getVehicleTNameCode();
	}
	
	@RequestMapping(value = "/getVehicleNameCode", method = RequestMethod.GET)
	public List<VehicleMasterDTO> getVehicleNameCode() {
		return vehicleMasterService.getVehicleNameCode();
	}
	
	@GetMapping(value = "/getVehicleThruWeighment")
	public List<VehicleMasterDTO> getVehicleThruWeighment() {
		return vehicleMasterService.getVehicleThruWeighment();
	}
	
	@GetMapping(value = "/getVehicleThruWeighmentfast")
	public List<Map<String,Object>> getVehicleThruWeighmentfast() {
		return vehicleMasterService.getVehicleThruWeighmentfast();
	}
	
	@GetMapping(value = "/allVechileList")
	public List<Map<String,Object>> allVechileList() {
		return vehicleMasterService.allVechileList();
	}
	
	@GetMapping(value = "/getVehicleDetails/{vid}")
	public VehicleMasterDTO getVehicleDetails(@PathVariable(value = "vid") String vid) {
		return vehicleMasterService.getVehicleDetails(vid);
	}
	
	@GetMapping(value = "/getVehicleThruTransporter/{tid}")
	public List<VehicleMasterDTO> getVehicleThruTransporter(@PathVariable(value = "tid") String tid) {
		return vehicleMasterService.getVehicleThruTransporter(tid);
	}
	
	@GetMapping(value = "/getVehicleThruTransWOWt1/{tid}")
	public List<VehicleMasterDTO> getVehicleThruTransWOWt1(@PathVariable(value = "tid") String tid) {
		return vehicleMasterService.getVehicleThruTransWOWt1(tid);
	}
	
	@GetMapping(value = "/getVehicleThruTransWOWt2/{tid}")
	public List<VehicleMasterDTO> getVehicleThruTransWOWt2(@PathVariable(value = "tid") String tid) {
		return vehicleMasterService.getVehicleThruTransWOWt2(tid);
	}
	
	@RequestMapping(value = "/getVehicleNumberByVtype/{vtype}", method = RequestMethod.GET)
	public List<VehicleMasterDTO> getVehicleNumberByVtype(@PathVariable(value = "vtype") String vtype) {
		return vehicleMasterService.getVehicleNumberByVtype(vtype);
	}
	
	@GetMapping("/getVehicleTypeName/{vcode}")
	public List<VehicleMasterDTO> getVehicleTypeName(@PathVariable(value = "vcode") String vcode)
	{
		return vehicleMasterService.getVehicleTypeName(vcode);
	}
	
	@RequestMapping(value = "/getTransChgNameCode", method = RequestMethod.GET)
	public List<TransportationChgsMatrixMasterDTO> getTransChgNameCode() {
		return transportationChgsMatrixMasterService.getTransChgNameCode();
	}
	
	@GetMapping(value = "/getTaxCNameCode/{company}")
	public List<Tax_code_masterDTO> getTaxNameCode(@PathVariable(value = "company") String company) {
		return tax_code_masterService.getTaxNameCode(company);
	}
	//Delete
	@GetMapping(value = "/getTaxCNameCode")
	public List<Tax_code_masterDTO> getTaxNameCode() {
		return tax_code_masterService.getTaxNameCode();
	}
	
	
	@GetMapping("/getTaxNameByTaxCode/{code}")
	public Tax_code_masterDTO getTaxNameByTaxCode(@PathVariable(value = "code") String code)
	{
		System.err.println("Got Tax Code --------->"+code);
		return tax_code_masterService.getTaxNameByTaxCode(code);
	}
	
	
	@GetMapping("/chkItemNameStatus")
	public ResponseEntity<MessageStatusDTO> chkItemNameStatus(@RequestParam(defaultValue = "") String iname) 
	{
		MessageStatusDTO val=item_masterService.chkItemNameStatus(iname);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/chkItemCodeStatus")
	public ResponseEntity<StatusDTO> chkItemCodeStatus(@RequestParam(defaultValue = "") String icode) 
	{
		StatusDTO val=item_masterService.chkItemCodeStatus(icode);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping(value = "/getAllItems")
	public List<Item_masterDTO> getAllItems() {
		return item_masterService.getAllItems();
	}
	
	@GetMapping(value = "/getItemCodeName")
	public List<Item_masterDTO> getItemCodeName(){
		return item_masterService.getItemNameList();
	}
	
	@GetMapping(value = "/getItemCodeNewName")
	public List<Map<String,Object>> getItemCodeNewName(){
		return item_masterService.getItemNameNewList();
	}
	
	@GetMapping(value = "/getPackingUom/{item_id}")
	public List<Map<String,Object>> getPackingUom(@PathVariable(value = "item_id") String item_id){
		return item_masterService.getPackingUom(item_id);
	}
	
	@GetMapping(value = "/getItemCodeNamecom")
	public List<Item_masterDTO> getItemCodeNamecom(@RequestParam("company") String company) {
		return item_masterService.getItemCodeNamecom(company);
	}
	
	@GetMapping(value = "/getItemRawMaterials")
	public List<Item_masterDTO> getItemRawMaterials() {
		return item_masterService.getItemRawMaterials();
	}
	
	@GetMapping(value = "/getItemThruType/{itype}")
	public List<Item_masterDTO> getItemThruType(@PathVariable(value = "itype") String itype) {
		List<Item_masterDTO> typelist = new ArrayList<Item_masterDTO>();
		Item_masterDTO asd = new Item_masterDTO();
		typelist.add(asd);
		typelist.forEach((element->{
			element.setItem_id("0");
			element.setItem_name("Choose an Option");
		}));
		
		typelist.addAll(item_masterService.getItemThruType(itype));
		return typelist;
	}
	
	@GetMapping(value = "/getItemThruType3/{itype}")
	public List<Item_masterDTO> getItemThruType3(@PathVariable(value = "itype") String itype) {
		List<Item_masterDTO> typelist = new ArrayList<Item_masterDTO>();
	
		typelist.addAll(item_masterService.getItemThruType3(itype));
		return typelist;
	}
	
	@GetMapping(value = "/getItemThruTypeForUsedItem/{bunit},{itype}")
	public List<Item_masterDTO> getItemThruTypeForUsedItem(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "itype") String itype) {
		
		return item_masterService.getItemThruTypeForUsedItem(bunit,itype);
	}
	
	@GetMapping(value = "/getweatreceivingitemlist/{businessunit_id}")
	public List<Item_masterDTO> getweatreceivingitemlist(@PathVariable(value = "businessunit_id") String businessunit_id) {
		List<Item_masterDTO> typelist = new ArrayList<Item_masterDTO>();
		Item_masterDTO asd = new Item_masterDTO();
		typelist.add(asd);
		typelist.forEach((element->{
			element.setItem_id("0");
			element.setItem_name("Choose an Option");
		}));
		
		typelist.addAll(item_masterService.getweatreceivingitemlist(businessunit_id));
		return typelist;
	}
	
	@GetMapping(value = "/getLabItemlist/{businessunit_id}")
	public List<Item_masterDTO> getLabItemlist(@PathVariable(value = "businessunit_id") String businessunit_id) {
		List<Item_masterDTO> typelist = new ArrayList<Item_masterDTO>();
		Item_masterDTO asd = new Item_masterDTO();
		typelist.add(asd);
		typelist.forEach((element->{
			element.setItem_id("0");
			element.setItem_name("Choose an Option");
		}));
		
		typelist.addAll(item_masterService.getLabItemlist(businessunit_id));
		return typelist;
	}
	
	@GetMapping(value = "/getItemThruSales")
	public List<Item_masterDTO> getItemThruSales() {
		List<Item_masterDTO> itemList= new ArrayList<Item_masterDTO>();
		
		//Item_masterDTO def=new Item_masterDTO("0","0","0","Choose an Option",false,"0","0","0","0","0","0","0",false,false,false,false,false,false,false,"0","0","0","0","0","0");
		Item_masterDTO def=new Item_masterDTO();
		def.setItem_id("0");
		def.setItem_name("Choose an Option");
		itemList.add(def);
		itemList.addAll(item_masterService.getItemThruSales());
		
		return itemList;
	}

	@GetMapping(value = "/getItemThruSalesNew")
	public List<Map<String, Object>> getItemThruSalesNew() {
		
		return item_masterService.getItemThruSalesNew();
	}
	
	@GetMapping(value = "/getFinishedItemlist/{businessunit_id}")
	public List<Map<String, Object>>getFinishedItemlist(@PathVariable(value = "businessunit_id") String businessunit_id) 
	{
	
		return item_masterService.getFinishedItemlist(businessunit_id);
		
	}
	
	
	@GetMapping(value = "/getItemThruSalesThruBU")
	public List<Item_masterDTO> getItemThruSalesThruBU(@RequestParam(defaultValue = "") String bunit,@RequestParam("company") String company){
		List<Item_masterDTO> itemList= new ArrayList<Item_masterDTO>();
		
		//Item_masterDTO def=new Item_masterDTO("0","0","0","Choose an Option",false,"0","0","0","0","0","0","0",false,false,false,false,false,false,false,"0","0","0","0","0","0");
		Item_masterDTO def=new Item_masterDTO();
		def.setItem_id("0");
		def.setItem_name("Choose an Option");
		itemList.add(def);
		itemList.addAll(item_masterService.getItemThruSalesThruBU(bunit,company));
		
		return itemList;
	}
	
	@GetMapping("/getItemThruSalesThruBUFastApi/{bunit}/{company}")
	public List<Map<String,Object>> getItemThruSalesThruBUFastApi(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "company") String company)
	{
		return item_masterService.getItemThruSalesThruBUFastApi(bunit,company);
	}
	
	@GetMapping(value = "/getItemThruSalesThruBU_inv_type")
	public List<Item_masterDTO> getItemThruSalesThruBU_inv_type(@RequestParam(defaultValue = "") String bunit,@RequestParam("company") String company,@RequestParam("inv_type") String inv_type){
		List<Item_masterDTO> itemList= new ArrayList<Item_masterDTO>();
		
		
		Item_masterDTO def=new Item_masterDTO();
		def.setItem_id("0");
		def.setItem_name("Choose an Option");
		itemList.add(def);
		itemList.addAll(item_masterService.getItemThruSalesThruBU_inv_type(bunit,company,inv_type));
		
		return itemList;
	}
	
	@GetMapping("/getJobworklist/{item_id}")
	public  List<Map<String,Object>> getJobworklist(@PathVariable(value = "item_id") String item_id)
	{
		return item_masterService.getJobworklist(item_id);
	}
	
	@GetMapping("/getItemThruSalesThruBU_inv_typeReg/{bunit}/{company}")
	public List<Map<String,Object>> getItemThruSalesThruBU_inv_typeReg(@PathVariable(value = "bunit") String bunit,
																	   @PathVariable(value = "company") String company)
	{
		return item_masterService.getItemThruSalesThruBU_inv_typeReg(bunit,company);
	}
	
	
	
	@GetMapping("/getRateChartItemList/{curr_date}/{b_unit}/{inv_type}")
	public List<Map<String,Object>> getRateChartItemList(@PathVariable(value = "curr_date") String curr_date,
														 @PathVariable(value = "b_unit") String b_unit,
														 @PathVariable(value = "inv_type") String inv_type)
	{
		return ratechartService.getRateChartItemList(curr_date,b_unit,inv_type);
	}
	
	@GetMapping("/getRateItemQty/{order_date}/{itemid}/{packingid}/{id}/{pricebasedon}")
	public Map<String,Object> getRateItemQty(@PathVariable(value = "order_date") String order_date,
												   @PathVariable(value = "itemid") String itemid,
												   @PathVariable(value = "packingid") String packingid,
												   @PathVariable(value = "id") long id,
												   @PathVariable(value = "pricebasedon") String pricebasedon)
	{
		return ratechartService.getRateItemQty(order_date,itemid,packingid,id,pricebasedon);
	}
	
	@GetMapping("/getItemThruSalesThruBU_inv_typeGST/{bunit}/{company}")
	public List<Map<String,Object>> getItemThruSalesThruBU_inv_typeGST(@PathVariable(value = "bunit") String bunit,
																	   @PathVariable(value = "company") String company)
	{
		return item_masterService.getItemThruSalesThruBU_inv_typeGST(bunit,company);
	}
	
	@GetMapping(value = "/getItemThruProcessed")
	public List<Item_masterDTO> getItemThruProcessed() {
		List<Item_masterDTO> itemList= new ArrayList<Item_masterDTO>();
		
		//Item_masterDTO def=new Item_masterDTO("0","0","0","Choose an Option",false,"0","0","0","0","0","0","0",false,false,false,false,false,false,false,"0","0","0","0","0","0");
		Item_masterDTO def=new Item_masterDTO();
		def.setItem_id("0");
		def.setItem_name("Choose an Option");
		itemList.add(def);
		itemList.addAll(item_masterService.getItemThruProcessed());
		
		return itemList;
	}
	
	@GetMapping(value = "/getItemThruProcessed/{bunit}")
	public List<Item_masterDTO> getItemThruProcessed(@PathVariable(value = "bunit") String bunit) {
		List<Item_masterDTO> itemList= new ArrayList<Item_masterDTO>();
		
		//Item_masterDTO def=new Item_masterDTO("0","0","0","Choose an Option",false,"0","0","0","0","0","0","0",false,false,false,false,false,false,false,"0","0","0","0","0","0");
		Item_masterDTO def=new Item_masterDTO();
		def.setItem_id("0");
		def.setItem_name("Choose an Option");
		itemList.add(def);
		itemList.addAll(item_masterService.getItemThruProcessed(bunit));
		
		return itemList;
	}
	
	@GetMapping(value = "/itemnameproduction/{entrymode}/{businessunit}/{shopfloor}")
	public List<Item_masterDTO> itemnameproduction(@PathVariable(value = "entrymode") String entrymode,@PathVariable(value = "businessunit") String businessunit,@PathVariable(value = "shopfloor") String shopfloor) {
		
		
		return item_masterService.itemnameproduction(entrymode,businessunit,shopfloor);
	}
	
	
	@GetMapping(value = "/getItemNameByGroup/{businessunit}/{shop_floor}/{process}")
	public List<Item_masterDTO> itemNameByGroupProduction(@PathVariable(value = "businessunit") String businessunit,@PathVariable(value = "shop_floor") String shop_floor,@PathVariable(value = "process") String process) {
		
		
		return item_masterService.itemNameByGroupProduction(businessunit,shop_floor,process);
	}
	
	
	@GetMapping(value = "/getItemThruPurchase")
	public List<Item_masterDTO> getItemThruPurchase() {
		List<Item_masterDTO> typeList=new ArrayList<Item_masterDTO>(); 
		Item_masterDTO def= new Item_masterDTO();
		
		typeList.add(def);
		typeList.forEach((e->{
			e.setItem_id("0");
			e.setItem_name("Choose an option");	
		}));
		typeList.addAll(item_masterService.getItemThruPurchase());
		
		return typeList;
	}
	
	@GetMapping(value = "/getItemThruPurchasenew")
	//List<HashMap<String, Object[]>>
	public List<Map<String, Object>> getItemThruPurchasenew() {
		
		return item_masterService.getItemThruPurchasenew();
	}
	
	@GetMapping("/getItemCodeByPacking/{company}")
	public List<Item_masterDTO> getItemCodeByPacking(@PathVariable(value = "company") String company)
	{
		return item_masterService.getItemCodeByPacking(company);
	}
	//Delete
	@GetMapping("/getItemCodeByPacking")
	public List<Item_masterDTO> getItemCodeByPacking()
	{
		return item_masterService.getItemCodeByPacking();
	}
	
	@GetMapping("/getItemCodeWithoutPacking")
	public List<Item_masterDTO> getItemCodeWithoutPacking()
	{
		return item_masterService.getItemCodeWithoutPacking();
	}
	 
	@GetMapping(value = "/getItemListByGroup/{group}/{item_id}")
	public List<Item_masterDTO> getItemListByGroup(@PathVariable(value = "group") String group,@PathVariable(value = "item_id") String item_id) {
		return item_masterService.getItemListByGroup(group,item_id);
	}
	
	@GetMapping(value = "/getItemByItemGroup/{itemid}")
	public List<Item_masterDTO> getItemByItemGroup(@PathVariable(value = "itemid") String itemid) {
		return item_masterService.getItemByItemGroup(itemid);
	}
	
	@GetMapping("/getItemNameByCode/{code}")
	public Item_masterDTO itemNameByCode(@PathVariable(value = "code") String code)
	{
		return item_masterService.getItemNameByCode(code);
	}
	
	@GetMapping("/getItemNameById/{code}/{company}")
	public Item_masterDTO itemNameById(@PathVariable(value = "code") String code,@PathVariable(value = "company") String company)
	{
		return item_masterService.getItemNameById(code,company);
	}
	
	@GetMapping("/getPackingMasterCode/{itemid}/{packingid}")
	public List<Map<String, Object>> getPackingMasterCode(@PathVariable(value = "itemid") String itemid,
			@PathVariable(value = "packingid") String packingid)
	{
		return item_masterService.getPackingMasterCode(itemid,packingid);
	}
	
	@GetMapping("/getItemNameCodeStock/{itemID}")
	public Item_masterDTO getItemNameCodeStock(@PathVariable(value = "itemID") String itemID)
	{
		return item_masterService.getItemNameCodeStock(itemID);
	}
	
	@GetMapping("/getItemCodeByType/{type}")
	public List<Item_masterDTO> getItemCodeByType(@PathVariable(value = "type") String type)
	{
		return item_masterService.getItemCodeByType(type);
	}
	
	
	@GetMapping(value = "/getItemTypes/{company}")
	public List<Item_type_masterDTO> getItemTypes(@PathVariable(value = "company") String company) {
		//System.out.println("getItemTypes");
		List<Item_type_masterDTO> typeList= new ArrayList<Item_type_masterDTO>();
		
		Item_type_masterDTO def=new Item_type_masterDTO("0","0","Choose an option","0");
		typeList.add(def);
		typeList.addAll(item_type_masterService.getItemType(company));
		
		return typeList;
	}
	
	@GetMapping(value = "/getItemTypesNew/{company}")
	public List<Item_type_masterDTO> getItemTypesNew(@PathVariable(value = "company") String company) {
		//System.out.println("getItemTypes");
		List<Item_type_masterDTO> typeList= new ArrayList<Item_type_masterDTO>();
		
		Item_type_masterDTO def=new Item_type_masterDTO("0","0","Choose an option","0");
		typeList.add(def);
		typeList.addAll(item_type_masterService.getItemTypesNew(company));
		
		return typeList;
	}
	
	@GetMapping("/itemTypeListFastAPI/{company}")
	public List<Map<String,Object>> itemTypeListFastAPI(@PathVariable(value = "company") String company)
	{
		return item_type_masterService.itemTypeListFastAPI(company);
	}
	
	
	@GetMapping(value = "/getItemTypesMaster/{company}")
	public List<Item_type_masterDTO> getItemTypesMaster(@PathVariable(value = "company") String company) {
		
		List<Item_type_masterDTO> typeList= new ArrayList<Item_type_masterDTO>();
		//System.out.println("getItemTypesMaster");
		Item_type_masterDTO def=new Item_type_masterDTO("0","0","Choose an option","0");
		typeList.add(def);
		typeList.addAll(item_type_masterService.getItemTypeMaster(company));
		
		return typeList;
	}
	
	@GetMapping("/getItemTypeNameByCode/{code}")
	public Item_type_masterDTO itemTypeNameByCode(@PathVariable(value = "code") String code)
	{
		return item_type_masterService.getItemTypeNameByCode(code);
	}
	
	@GetMapping("/itempackUom/{code}&{code1}&{company}")
	public Item_master_pack_mat_tagDTO itempackUom(@PathVariable(value = "code") String code,@PathVariable(value = "code1") String code1,@PathVariable(value = "company") String company)
	{
		return item_masterService.itempackUom(code,code1,company);
	}
	

	@GetMapping(value = "/getIGroupNames/{company}")
	public List<Item_group_masterDTO> getItemGroupName(@PathVariable(value = "company") String company) {
		List<Item_group_masterDTO> groupList= new ArrayList<Item_group_masterDTO>();
		Item_group_masterDTO def=new Item_group_masterDTO("0","0","Choose an option","0","0","0");
		groupList.add(def);
		groupList.addAll(item_group_masterService.getItemGroup(company));
		
		return groupList;
	}
	
	@GetMapping("/itemGroupFastList/{company}")
	public List<Map<String, Object>> itemGroupFastList(@PathVariable(value = "company") String company)
	{
		return item_group_masterService.itemGroupFastList(company);
	}
	
	@GetMapping("/getItemGroupUom/{code}")
	public Item_group_masterDTO getItemGroupUom(@PathVariable(value = "code") String code)
	{
		return item_group_masterService.getItemGroupUom(code);
	}
	
	@GetMapping("/chkItemGroupName/{code}")
	public String chkItemGroupName(@PathVariable(value = "code") String code)
	{
		return item_group_masterService.chkItemGroupName(code);
	}
	
	@GetMapping("/chkItemGroupStatus/{code}")
	public ResponseEntity<MessageStatusDTO> chkItemGroupStatus(@PathVariable(value = "code") String code) 
	{
		MessageStatusDTO val=item_group_masterService.chkItemGroupStatus(code);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping(value = "/getICategoriesName/{company}")
	public List<Item_catagory_masterDTO> getItemCategoriesName(@PathVariable(value = "company") String company) {
		return iCatagory_masterService.getIcategory(company);
	}
	
	@GetMapping("/getItemTypeByCode/{code}")
	public Item_catagory_masterDTO catagoryNameByCode(@PathVariable(value = "code") String code)
	{
		//System.err.println("xyz: "+buCode);
		return iCatagory_masterService.getItemTypeByName(code);
	}
	
	@GetMapping("/chkCatNameStatus/{cname}")
	public ResponseEntity<MessageStatusDTO> chkCatNameStatus(@PathVariable(value = "cname") String cname) 
	{
		MessageStatusDTO val=iCatagory_masterService.chkCatNameStatus(cname);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@RequestMapping(value = "/getTaxCodeName", method = RequestMethod.GET)
	public List<Tax_code_master> getTaxCodeNames() {
		return tax_code_masterService.getTaxCode();
	}
	
	@RequestMapping(value = "/getAccNormsNc", method = RequestMethod.GET)
	public List<Acc_acceptance_norms_masterDTO> getAccNormsNameCode() {
		return acc_acceptance_norms_masterService.getAccNormsNameCode();
	}
	
	@GetMapping("/getAccNormsDetails/{code}")
	public List<Acc_acceptance_norms_master_dtsDTO> getAccNormsDetails(@PathVariable(value = "code") String code)
	{
		return acc_acceptance_norms_masterService.getAccNormsDetails(code);
	}
	
	@RequestMapping(value = "/getAccTaxcodeNc", method = RequestMethod.GET)
	public List<Acc_taxcode_details_masterDTO> getAccTaxcodeNc() {
		return acc_taxcode_details_masterService.getAccTaxcodeNc();
	}
	
	@RequestMapping(value = "/getAccPaymodeNc", method = RequestMethod.GET)
	public List<Acc_pay_mode_masterDTO> getAccPaymodeNc() {
		return acc_pay_mode_masterService.getAccPaymodeNc();
	}
	
	@RequestMapping(value = "/getAccPaytermNc", method = RequestMethod.GET)
	public List<Acc_pay_term_masterDTO> getAccPaytermNc() {
		return acc_pay_term_masterService.getAccPaytermNc();
	}
	
	@GetMapping(value = "/getQcrulesNc/{company}")
	public List<Qc_rules_setupDTO> getQcrulesNc(@PathVariable(value = "company") String company) {
		return qc_rules_setupService.getQcrulesNc(company);
	}
	
	@Autowired
	Pur_EnquiryService pur_EnquiryService;
	
	@GetMapping("/enqNo")
	public List<Pur_Enquiry> getEnqList()
	{
		return pur_EnquiryService.enqNo();
	}
	
	@GetMapping("/getPurEnquiryDetails/{enqid}")
	public Pur_EnquiryDTO getPurEnquiryDetails(@PathVariable(value = "enqid") String enqid)
	{
		return pur_EnquiryService.getPurEnquiryDetails(enqid);
	}
	
	@GetMapping("/getPurEnquiryDDCList/{ccc}")
	public List<Pur_EnquiryDTO> getPurEnquiryDDCList(@PathVariable(value = "ccc") String ccc)
	{
		return pur_EnquiryService.getPurEnquiryDDCList(ccc);
	}
	
	@GetMapping("/getPurEnquiryDDCSuppList/{reftype}/{sid}/{itemtype}")
	public List<Pur_EnquiryDTO> getPurEnquiryDDCSuppList(@PathVariable(value = "reftype") String reftype,@PathVariable(value = "sid") String sid,@PathVariable(value = "itemtype") String itemtype)
	{
		return pur_EnquiryService.getPurEnquiryDDCSuppList(reftype,sid,itemtype);
	}
	
	@GetMapping("/getPurEnquiryInformal/{reftype}")
	public List<Pur_EnquiryDTO> getPurEnquiryInformal(@PathVariable(value = "reftype") String reftype)
	{
		return pur_EnquiryService.getPurEnquiryInformal(reftype);
	}
	
	@GetMapping("/getPurEnquiryCNQUPList/{enq_id}")
	public List<Pur_Enquiry_Service_DetailsDTO> getPurEnquiryCNQUPList(@PathVariable(value = "enq_id") String enq_id)
	{
		return pur_EnquiryService.getPurEnquiryCNQUPList(enq_id);
	}
	
	@Autowired
	Country_masterService country_masterService;
	
	@GetMapping("/countryList")
	public List<Country_masterDTO> getCountry()
	{
		return country_masterService.countrylist();
	}
	
	@Autowired
	State_masterService state_masterService;
	
	@GetMapping("/stateList")
	public List<State_masterDTO> getState()
	{
		return state_masterService.statelist();
	}
	
	@GetMapping("/stateListByCountry/{country_name}")
	public List<State_masterDTO> stateListByCountry(@PathVariable(value = "country_name") String country_name)
	{
		
		List<State_masterDTO> typeList= new ArrayList<State_masterDTO>();
		State_masterDTO def=new State_masterDTO();
		typeList.add(def);
		typeList.forEach((e->{
			e.setState_code("0");
			e.setState_name("Choose an option");	
		}));
		typeList.addAll(state_masterService.statelistByCountry(country_name));
		return 	typeList;

		
	}
	
	@GetMapping("/statelistByCountryUserprofile")
	public List<State_master> statelistByCountryUserprofile()
	{
		
		return state_masterService.statelistByCountryUserprofile();
	}
	
	@Autowired
	District_masterService district_masterService;
	
	@GetMapping("/districtList")
	public List<District_masterDTO> getDistrict()
	{
		return district_masterService.districtlist();
	}
	
	@GetMapping("/districtListByState/{state_Name}")
	public List<District_masterDTO> districtListByState(@PathVariable(value = "state_Name") String state_Name)
	{
		return district_masterService.districtListByState(state_Name);
	}
	
	@GetMapping("/getDistrictThruState/{stateid}")
	public List<District_masterDTO> getDistrictThruState(@PathVariable(value = "stateid") String stateid)
	{
		List<District_masterDTO> distList= new ArrayList<>();
		District_masterDTO def=new District_masterDTO("0","Choose an option","0","0");
		distList.add(def);
		distList.addAll(district_masterService.getDistrictThruState(stateid));
		
		return distList;
	}
	
	@Autowired
	City_masterService city_masterService;
	
	@GetMapping("/cityList")
	public List<City_masterDTO> getCity()
	{
		return city_masterService.citylist();
	}
	
	@GetMapping("/cityListByDistrict/{dist_name}")
	public List<City_masterDTO> getCityListByDistrict(@PathVariable(value = "dist_name") String dist_name)
	{
		return city_masterService.citylistByDistrict(dist_name);
	}
	
	@GetMapping("/getCityListThruDistrict/{distid}")
	public List<City_masterDTO> getCityListThruDistrict(@PathVariable(value = "distid") String distid)
	{
		List<City_masterDTO> cityList= new ArrayList<>();
		City_masterDTO def=new City_masterDTO("0","Choose an option", "0", "0");
		cityList.add(def);
		cityList.addAll(city_masterService.getCityListThruDistrict(distid));
		
		return cityList;
	}
	
	@Autowired
	Area_masterService area_masterService;
	
	@GetMapping("/areaList")
	public List<Area_masterDTO> areaMasterList()
	{
		return area_masterService.areaMasterList();
	}
	
	@Autowired
	Supplier_groupService supplier_groupService;
	

	@GetMapping("/supplierGroupId")
	public Supplier_groupDTO supplierGroupId()
	{
		return supplier_groupService.supplierGroupId();
	}
	
	@GetMapping("/supplierGroupList")
	public List<Supplier_groupDTO> supplierGroupList()
	{
		List<Supplier_groupDTO> suppgList= new ArrayList<Supplier_groupDTO>();
		Supplier_groupDTO def=new Supplier_groupDTO("0","0","Choose an Option","0");
		suppgList.add(def);
		suppgList.addAll(supplier_groupService.getSupplierGroupNCList());
		return suppgList;
	}
	
	@GetMapping("/getSuppParentGroup/{code}")
	public Supplier_groupDTO getSuppParentGroup(@PathVariable(value = "code") String code)
	{
		return supplier_groupService.getSuppParentGroup(code);
	}
	
	
	@GetMapping("/getsupplierByGroup/{code}")
	public List<Map<String,Object>> getsupplierByGroup(@PathVariable(value = "code") String code)
	{
		return supplier_groupService.getsupplierByGroup(code);
	}
	
	
	@GetMapping("/chkSuppGroupStatus/{grpname}")
	public ResponseEntity<MessageStatusDTO> chkSuppGroupStatus(@PathVariable(value = "grpname") String grpname) 
	{
		MessageStatusDTO val=supplier_groupService.chkSuppGroupStatus(grpname);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@Autowired
	Supp_bussiness_partnerService supp_bussiness_partnerService;
	
	@GetMapping("/chkSuppNameStatus/{sname}")
	public ResponseEntity<MessageStatusDTO> chkSuppNameStatus(@PathVariable(value = "sname") String sname) 
	{
		//System.out.println("Supplier : : "+sname);
		MessageStatusDTO val=supp_bussiness_partnerService.chkSuppNameStatus(sname);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/chkVehicleNoStatus/{sno}")
	public ResponseEntity<MessageStatusDTO> chkVehicleNoStatus(@PathVariable(value = "sno") String sno) 
	{
		MessageStatusDTO val=vehicleMasterService.chkVehicleNoStatus(sno);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/supplierMasterNCList/{company}")
	public List<Supp_bussiness_partnerDTO> supplierMasterName(@PathVariable(value = "company") String company)
	{
		List<Supp_bussiness_partnerDTO> typeList= new ArrayList<Supp_bussiness_partnerDTO>();
        Supp_bussiness_partnerDTO def=new Supp_bussiness_partnerDTO();
		typeList.add(def);
		typeList.forEach((e->{
			e.setBp_Id("0");
			e.setBp_name("Choose an option");
			
		}));
		typeList.addAll(supp_bussiness_partnerService.getSupplierMasterNCList(company));
		
		return 	typeList;
	}
	
	@GetMapping("/supplierMasterNCListNew/{company}")
	public List<Map<String, Object>> supplierMasterNameNew(@PathVariable(value = "company") String company)
	{
		return (supp_bussiness_partnerService.getSupplierMasterNCListNew(company));
	}
	
	@GetMapping("/supplierMasterListWithTotalAmt/{company}/{fin_year}")
	public List<Map<String, Object>> supplierMasterListWithTotalAmt(@PathVariable(value = "company") String company,
																	@PathVariable(value = "fin_year") String fin_year)
	{
		return (supp_bussiness_partnerService.getSupplierMasterListWithTotalAmt(company,fin_year));
	}
	
	@GetMapping("/newsupplierNamesList/{company}")
	public List<Supp_bussiness_partnerDTOC> newsupplierNamesList(@PathVariable(value = "company") String company)
	{
		List<Supp_bussiness_partnerDTOC> typeList= new ArrayList<Supp_bussiness_partnerDTOC>();
		Supp_bussiness_partnerDTOC def=new Supp_bussiness_partnerDTOC();
		typeList.add(def);
		typeList.forEach((e->{
			e.setBp_Id("0");
			e.setBp_name("Choose an option");
			
		}));
		
		typeList.addAll(supp_bussiness_partnerService.newsupplierNamesList(company));
		
		return 	typeList;
	}
	
	
	
	@GetMapping("/supplierorcustomerCodeList/{company}")
	public List<Supp_or_Customer_bussinessDTO> supplierorcustomerCodeList(@PathVariable(value = "company") String company)
	{
		List<Supp_or_Customer_bussinessDTO> typeList= new ArrayList<Supp_or_Customer_bussinessDTO>();
		Supp_or_Customer_bussinessDTO def=new Supp_or_Customer_bussinessDTO();
		typeList.add(def);
		typeList.forEach((e->{
			e.setBp_Id("0");
			e.setBp_name("Choose an option");
			
		}));
		typeList.addAll(supp_bussiness_partnerService.supplierorcustomerCodeList(company));
		
		return 	typeList;
	}
	
	@GetMapping("/supplierorcustomerCodeListNew/{company}")
	public  List<Map<String, Object>> supplierorcustomerCodeListNew(@PathVariable(value = "company") String company)
	{
	
		return 	supp_bussiness_partnerService.supplierorcustomerCodeListNew(company);
	}
	
	//Delete
	@GetMapping("/supplierMasterNCList")
	public List<Supp_bussiness_partnerDTO> supplierMasterName()
	{
		return 	supp_bussiness_partnerService.getSupplierMasterNCList();
	}
	
	@GetMapping("/supplierListByGroup/{group}")
	public List<Supp_bussiness_partnerDTO> supplierListByGroup(@PathVariable(value = "group") String group)
	{
		return 	supp_bussiness_partnerService.supplierListByGroup(group);
	}
	
	@GetMapping("/getSupplierName/{code}")
	public Supp_bussiness_partnerDTO getSupplierName(@PathVariable(value = "code") String code)
	{
		return supp_bussiness_partnerService.getSupplierName(code);
	}
	
	@GetMapping("/getSupplierDelvFromAdd/{sbp_id}/{cp}")
	public Supp_bussiness_partner_delv_fromDTO getSupplierDelvFromAdd(@PathVariable(value = "sbp_id") String sbp_id,@PathVariable(value = "cp") String cp)
	{
		return supp_bussiness_partnerService.getSupplierDelvFromAdd(sbp_id,cp);
	}

	
	@Autowired
	Cust_groupService cust_groupService;
	
	@GetMapping("/customerGroupList")
	public List<Cust_group> customerGroupList()
	{
		return cust_groupService.getCustomerGroupList();
	}
	
	@Autowired
	Cust_bussiness_partnerService cust_bussiness_partnerService;
	
	@GetMapping("/chkCustNameStatus/{cname}")
	public ResponseEntity<MessageStatusDTO> chkCustNameStatus(@PathVariable(value = "cname") String cname) 
	{
		MessageStatusDTO val=cust_bussiness_partnerService.chkCustNameStatus(cname);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/customerNameCodeList")
	public List<Cust_bussiness_partnerDTOC> customerNameCodeList(@RequestParam("company") String company)
	{
		List<Cust_bussiness_partnerDTOC> custList= new ArrayList<Cust_bussiness_partnerDTOC>();
		
		//Cust_bussiness_partnerDTO def=new Cust_bussiness_partnerDTO("0","0","0","0","Choose an Option","0",false,"0","0","0",false,"0",false,"0","0");
		Cust_bussiness_partnerDTOC def=new Cust_bussiness_partnerDTOC("0","0","Choose an Option");
		custList.add(def);
		custList.addAll(cust_bussiness_partnerService.customerNameCodeList(company));
		
		return custList;
	}
	
	
	@GetMapping("/customerNameCodeListnew")
	public List<Map<String, Object>> customerNameCodeListnew(@RequestParam("company") String company)
	{
		return cust_bussiness_partnerService.customerNameCodeListnew(company);
	}
	
	@GetMapping("/customerNameActiveBlockAllList/{comp}")
	public List<Map<String, Object>> customerNameActiveBlockAllList(@PathVariable("comp") String comp)
	{
		return cust_bussiness_partnerService.customerNameActiveBlockAllList(comp);
	}
	
	@GetMapping("/customerList")
	public List<Cust_bussiness_partnerDTOC> customerList(@RequestParam("company") String company)
	{
		
	//	return cust_bussiness_partnerService.customerList(company);
		List<Cust_bussiness_partnerDTOC> custList= new ArrayList<Cust_bussiness_partnerDTOC>();
		Cust_bussiness_partnerDTOC def=new Cust_bussiness_partnerDTOC("0","0","Choose an Option");
		custList.add(def);
		custList.addAll(cust_bussiness_partnerService.customerList(company));
		
		return custList;
	}
	
	//newcustomerList
	@GetMapping("/newcustomerList")
	public List<Cust_bussiness_partnerDTOC> newcustomerList(@RequestParam("company") String company)
	{
		
	//	return cust_bussiness_partnerService.customerList(company);
		List<Cust_bussiness_partnerDTOC> custList= new ArrayList<Cust_bussiness_partnerDTOC>();
		Cust_bussiness_partnerDTOC def=new Cust_bussiness_partnerDTOC("0","0","Choose an Option");
		custList.add(def);
		custList.addAll(cust_bussiness_partnerService.newcustomerList(company));
		
		return custList;
	}
	@GetMapping("/newfastcustomerList")
	public List<Map<String,Object>> newfastcustomerList(@RequestParam("company") String company)
	{
	
		return cust_bussiness_partnerService.newfastcustomerList(company);
	}
	
	//without company
	//delete later(1242)
	@GetMapping("/customerNameCodeListNC")
	public List<Cust_bussiness_partnerDTOC> customerNameCodeList()
	{
		List<Cust_bussiness_partnerDTOC> custList= new ArrayList<Cust_bussiness_partnerDTOC>();
		
		//Cust_bussiness_partnerDTO def=new Cust_bussiness_partnerDTO("0","0","0","0","Choose an Option","0",false,"0","0","0",false,"0",false,"0","0");
		Cust_bussiness_partnerDTOC def=new Cust_bussiness_partnerDTOC("0","0","Choose an Option");
		custList.add(def);
		custList.addAll(cust_bussiness_partnerService.customerNameCodeList());
		
		return custList;
	}
	
	@GetMapping("/getCustomers")
	public List<Cust_bussiness_partnerDTOC> getCustomers()
	{
		List<Cust_bussiness_partnerDTOC> custList= new ArrayList<Cust_bussiness_partnerDTOC>();
		
		Cust_bussiness_partnerDTOC def=new Cust_bussiness_partnerDTOC("0","0","Choose an Option");
		custList.add(def);
		custList.addAll(cust_bussiness_partnerService.getCustomers());
		
		return custList;
	}
	
	@GetMapping("/getCustomerList")
	public List<Cust_bussiness_partnerDTOC> getCustomerList()
	{
		List<Cust_bussiness_partnerDTOC> custList= new ArrayList<Cust_bussiness_partnerDTOC>();
		custList.addAll(cust_bussiness_partnerService.getCustomers());
		return custList;
	}
	
	
	@GetMapping("/getCustomerName/{code}")
	public Cust_bussiness_partnerDTO getCustomerName(@PathVariable(value = "code") String code)
	{
		return cust_bussiness_partnerService.getCustomerName(code);
	}
	
	@GetMapping("/getCustomerByChannel/{channelid}")
	public List<Cust_bussiness_partnerDTO> getCustomerByChannel(@PathVariable(value = "channelid") String channelid)
	{
		return cust_bussiness_partnerService.getCustomerByChannel(channelid);
	}
	
	@GetMapping("/getCustomerByChannelFastApi/{channelid}")
	public List<Map<String,Object>> getCustomerByChannelFastApi(@PathVariable(value = "channelid") String channelid)
	{
		return cust_bussiness_partnerService.getCustomerByChannelFastApi(channelid);
	}
	
	@GetMapping("/getSupplierByChannel/{channelid}")
	public List<Supp_bussiness_partnerDTO> getSupplierByChannel(@PathVariable(value = "channelid") String channelid)
	{
		return supp_bussiness_partnerService.getSupplierByChannel(channelid);
	}
	
	@GetMapping("/getSupplierByChannelFastApi/{channelid}")
	public List<Map<String,Object>> getSupplierByChannelFastApi(@PathVariable(value = "channelid") String channelid)
	{
		return supp_bussiness_partnerService.getSupplierByChannelFastApi(channelid);
	}
	
	@GetMapping(value = "/getCustGroupByChannel/{channelid}")
	public ResponseEntity<List<Cust_bussiness_partnerGroupDTO>> getCustGroupByChannel(@PathVariable(value = "channelid") String channelid) 
	{
		List<Cust_bussiness_partnerGroupDTO> val=cust_bussiness_partnerService.getCustGroupByChannel(channelid);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getCustomerByGroup/{group}")
	public List<Cust_bussiness_partnerDTO> getCustomerByGroup(@PathVariable(value = "group") String group)
	{
		return cust_bussiness_partnerService.getCustomerByGroup(group);
	}
	
	@GetMapping("/custGroupList")
	public List<Cust_groupDTO> custGroupList()
	{
		List<Cust_groupDTO> custgList= new ArrayList<Cust_groupDTO>();
		Cust_groupDTO def=new Cust_groupDTO("0","Choose an Option","0","0","0","0",false,"0","0");
		custgList.add(def);
		custgList.addAll(cust_groupService.getCustGroupNCList());
		return custgList;
	}
	
	@GetMapping("/getCustParentGroup/{code}")
	public Cust_groupDTO getCustParentGroup(@PathVariable(value = "code") String code)
	{
		return cust_groupService.getCustParentGroup(code);
	}
	
	@GetMapping("/getCustomerControlAccounts")
	public Cust_groupDTO getCustomerControlAccounts(@RequestParam(defaultValue = "") String custid)
	{
		return cust_groupService.getCustomerControlAccounts(custid);
	}
	
	@GetMapping("/chkCustGroupStatus/{grpmane}")
	public ResponseEntity<MessageStatusDTO> chkCustGroupStatus(@PathVariable(value = "grpmane") String grpmane) 
	{
		MessageStatusDTO val=cust_groupService.chkCustGroupStatus(grpmane);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@Autowired
	Transporter_groupService transporter_groupService;
	
	@GetMapping("/transporterGroupList")
	public List<Transporter_groupDTO> transporterGroupList()
	{
		List<Transporter_groupDTO> transgList= new ArrayList<Transporter_groupDTO>();
		Transporter_groupDTO def=new Transporter_groupDTO("0","Choose an Option","0","0","0",false,"0");
		transgList.add(def);
		transgList.addAll(transporter_groupService.getTransporterGroupList());
		return transgList;
	}
	
	@GetMapping("/getTransParentGroup/{code}")
	public Transporter_groupDTO getTransParentGroup(@PathVariable(value = "code") String code)
	{
		return transporter_groupService.getTransParentGroup(code);
	}
	
	@GetMapping("/chkTransGroupStatus/{grpmane}")
	public ResponseEntity<MessageStatusDTO> chkTransGroupStatus(@PathVariable(value = "grpmane") String grpmane) 
	{
		MessageStatusDTO val=transporter_groupService.chkTransGroupStatus(grpmane);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@Autowired
	Trans_bussiness_partnerService trans_bussiness_partner;
	
	@GetMapping("/chkTransNameStatus/{tname}")
	public ResponseEntity<MessageStatusDTO> chkTransNameStatus(@PathVariable(value = "tname") String tname) 
	{
		MessageStatusDTO val=trans_bussiness_partner.chkTransNameStatus(tname);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getTransporterMNCList")
	public List<Trans_bussiness_partnerDTO> getTransporterMNCList()
	{
		return 	trans_bussiness_partner.getTransporterMNCList();
	}
	
	@GetMapping("/getTransporterMNCListFast")
	public List<Map<String, Object>> getTransporterMNCListFast()
	{
		return trans_bussiness_partner.getTransporterMNCListFast();
	}
	
	@GetMapping("/getTransporterListFastbp_Id")
	public List<Map<String, Object>> getTransporterListFastbp_Id()
	{
		return trans_bussiness_partner.getTransporterListFastbp_Id();
	}
	
	@GetMapping("/getTransporterThruCustomer/{custid}")
	public List<Trans_bussiness_partnerDTO> getTransporterThruCustomer(@PathVariable(value = "custid") String custid)
	{
		//System.out.println("custid :: "+custid);
		return 	trans_bussiness_partner.getTransporterThruCustomer(custid);
	}
	
	@GetMapping("/getTransporterThruSupplier/{suppid}")
	public List<Trans_bussiness_partnerDTO> getTransporterThruSupplier(@PathVariable(value = "suppid") String suppid)
	{
		return 	trans_bussiness_partner.getTransporterThruSupplier(suppid);
	}
	
	
	@GetMapping("/getSuppliertransport/{suppid}")
	public List<Supp_bussiness_partner_delv_fromDTO> getSuppliertransport(@PathVariable(value = "suppid") String suppid)
	{
		return 	trans_bussiness_partner.getSuppliertransport(suppid);
	}
	
	
	
	
	
	@GetMapping("/getsalevehiclelist/{transportid}")
	public List<VehicleMaster> getsalevehiclelist(@PathVariable(value = "transportid") String transportid)
	{
		return 	trans_bussiness_partner.getsalevehiclelist(transportid);
	}
	
	@GetMapping("/getTransporterThruGroup/{tgid}")
	public List<Trans_bussiness_partnerDTO> getTransporterThruGroup(@PathVariable(value = "tgid") String tgid)
	{
		return 	trans_bussiness_partner.getTransporterThruGroup(tgid);
	}
	
	@GetMapping("/bpNameByCode/{bpCode}")
	public Trans_bussiness_partnerDTO bpNameByCode(@PathVariable(value = "bpCode") String bpCode)
	{
		return trans_bussiness_partner.getBPNameByCode(bpCode);
	}
	
	
	@GetMapping("/getTransAccount/{bp_Id}")
	public Trans_bussiness_partner_accontDTO getTransAccount(@PathVariable(value = "bp_Id") String bp_Id)
	{
		return trans_bussiness_partner.getTransAccount(bp_Id);
	}
	
	@GetMapping("/getTransChargeCode/{trans_id}/{transfrom}/{transto}/{type}")
	public List<Map<String, Object>> getTransChargeCode(@PathVariable(value = "trans_id") String trans_id,
																@PathVariable(value = "transfrom") String transfrom,
																@PathVariable(value = "transto") String transto,
																@PathVariable(value = "type") String type)
	{
		return transportationChgsMatrixMasterService.getTransChargeCode(trans_id,transfrom,transto,type);
	}
	
	//.................Broker......................
	
	@Autowired
	Broker_groupService broker_groupService;
	
	@GetMapping("/brokerGroupList")
	public List<Broker_groupDTO> brokerGroupList(@RequestParam String company)
	{
		List<Broker_groupDTO> broGroupList= new ArrayList<Broker_groupDTO>();
		Broker_groupDTO def=new Broker_groupDTO("0","Choose an option","0","company_name");
		broGroupList.add(def);
		broGroupList.addAll(broker_groupService.getBrokerGroupList(company));
		
		return broGroupList;
	}
	
	@GetMapping("/brokerGroupMasterList")

	public List<Broker_groupDTO> brokerGroupList()
	{
		//System.out.println("brokerGroupList");
		List<Broker_groupDTO> broGroupList= new ArrayList<Broker_groupDTO>();
		Broker_groupDTO def=new Broker_groupDTO("0","Choose an option","0","company_name");
		broGroupList.add(def);
		broGroupList.addAll(broker_groupService.getBrokerGroupList());

		
		return broGroupList;
	}
	
	@GetMapping("/getBroParentGroup/{code}/{company}")
	public Broker_groupDTO getBroParentGroup(@PathVariable(value = "code") String code, @PathVariable(value = "company") String company )
	{
		return broker_groupService.getBroParentGroup(code,company);
	}
	
	@GetMapping("/chkBrokerGroupStatus/{grpmane}/{company}")
	public ResponseEntity<MessageStatusDTO> chkBrokerGroupStatus(@PathVariable(value = "grpmane") String grpmane, @PathVariable(value = "company") String company ) 
	{
		MessageStatusDTO val=broker_groupService.chkBrokerGroupStatus(grpmane,company);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@Autowired
	Broker_masterService broker_masterService;
	
	@GetMapping("/chkBrokerNameStatus/{bname}")
	public ResponseEntity<MessageStatusDTO> chkBrokerNameStatus(@PathVariable(value = "bname") String bname) 
	{
		MessageStatusDTO val=broker_masterService.chkBrokerNameStatus(bname);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/brokerMNCList")
	public List<Broker_masterDTO> brokerMNCList()
	{
		List<Broker_masterDTO> brokerList= new ArrayList<Broker_masterDTO>();
		//Broker_masterDTO def=new Broker_masterDTO(0L,"0","0","Choose an Option","0","0","0","0","0",false,false,"0","0");
		Broker_masterDTO def=new Broker_masterDTO();
		def.setBroker_Id("0");
		def.setName("Choose an Option");
		brokerList.add(def);
		brokerList.addAll(broker_masterService.brokerMNCList());
		return brokerList;
	}
	
	@GetMapping("/brokerNameListFast")
	public List<Map<String, Object>> brokerNameListFast()
	{
		return broker_masterService.brokerNameListFast();
	}
	
	@GetMapping("/brokerNameByCode/{code}")
	public Broker_masterDTO brokerNameByCode(@PathVariable(value = "code") String code)
	{
		return 	broker_masterService.brokerNameByCode(code);
	}
	
	
	@Autowired
	Broker_templtService broker_templtService;
	
	/*@GetMapping("/brokerTNCList")
	public List<Broker_templtDTO> brokerTNCList()
	{
		return 	broker_masterService.brokerTNCList();
	}*/
	
	
	@GetMapping("/getBrokerMaster/{id}")
	public Broker_masterDTO getBrokerMaster(@PathVariable(value = "id") Long id)
	{
		return broker_masterService.getBrokerMaster(id);
	}
	
	
	@GetMapping("/getBrokerMasterAddr/{code}")
	public List<Broker_master_addDTO> getBrokerMasterAddr(@PathVariable(value = "code") String code)
	{
		return broker_masterService.getBrokerMasterAddr(code);
	}
	
	@GetMapping("/getBrokerMasterAddrDtls/{code}")
	public List<Broker_master_add_dtlsDTO> getBrokerMasterAddrDtls(@PathVariable(value = "code") String code)
	{
		return broker_masterService.getBrokerMasterAddrDtls(code);
	}
	
	@GetMapping("/getBrokerMasterPty/{code}")
	public List<Broker_master_ptyDTO> getBrokerMasterPty(@PathVariable(value = "code") String code)
	{
		return broker_masterService.getBrokerMasterPty(code);
	}
	
	@GetMapping("/getBrokerMasterTransporter/{code}")
	public List<Broker_master_transporterDTO> getBrokerMasterTransporter(@PathVariable(value = "code") String code)
	{
		return broker_masterService.getBrokerMasterTransporter(code);
	}
	
	@GetMapping("/getBrokerMasterVdr/{code}")
	public List<Broker_master_vdrDTO> getBrokerMasterVdr(@PathVariable(value = "code") String code)
	{
		return broker_masterService.getBrokerMasterVdr(code);
	}
	
	
	@GetMapping("/getBrokerMasterVdrnew/{code}")
	public List<Map<String, Object>> getBrokerMasterVdrnew(@PathVariable(value = "code") String code)
	{
		return broker_masterService.getBrokerMasterVdrnew(code);
	}
	
	@GetMapping("/brokerStatutoryRetriveList/{code}")
	public Broker_master_statutoryDTO brokerStatutoryRetriveList(@PathVariable(value = "code") String code)
	{
		return broker_masterService.brokerStatutoryRetriveList(code);
	}
	
	@GetMapping("/brokerAccountRetriveList/{code}")
	public Broker_master_accountDTO brokerAccountRetriveList(@PathVariable(value = "code") String code)
	{
		return broker_masterService.brokerAccountRetriveList(code);
	}
	
	@GetMapping("/brokerOthPartnerRetriveList/{code}")
	public List<Broker_master_othDTO> brokerOthPartnerRetriveList(@PathVariable(value = "code") String code)
	{
		return broker_masterService.brokerOthPartnerRetriveList(code);
	}
	
	@GetMapping("/brokerDocRetriveList/{code}")
	public List<Broker_master_docDTO> brokerDocRetriveList(@PathVariable(value = "code") String code)
	{
		return broker_masterService.brokerDocRetriveList(code);
	}

	@Autowired
	Otherpartner_groupService otherpartner_groupService;
	
	@GetMapping("/otherPartnerGroupList")
	public List<Otherpartner_groupDTO> otherpartnerGroupList()
	{
		return 	otherpartner_groupService.getOtherpartnerGroupList();
	}
	
	
	@Autowired
	Op_bussiness_partnerService op_bussiness_partner;
	
	@GetMapping("/getOtherPartnerMNCList")
	public List<Op_bussiness_partnerDTO> getOtherPartnerMNCList()
	{
		return 	op_bussiness_partner.getOtherPartnerMNCList();
	}
	
	
	//.......................Channel_partner....................
	
	@Autowired
	Channel_partnerService channel_partnerService;
	
	@GetMapping("/getChannelPartnerNCList")
	public List<Channel_partnerDTO> ChannelPartnerNCList()
	{
		return 	channel_partnerService.ChannelPartnerNCList();
	}
	
	
	//.................Company Master...........................
	
	@Autowired
	CompanyMasterService companyMasterService;
	
	@GetMapping("/getCompanyNCList")
	public List<Company_master> getCompanyNCCode()
	{
		return companyMasterService.getCompanyNCCode();
	}
	
	
	//.............Company Business Unit Master.....................
	@Autowired
	CompanyBusinessUnitMasterService companyBusinessUnitMasterService;
	
	@GetMapping("/getcompanyBUMNCList/{company}")
	public List<CompanyBusinessUnitMasterDTO> getcompanyBUMNCList(@PathVariable(value = "company") String company)
	{
		List<CompanyBusinessUnitMasterDTO> businessList= new ArrayList<CompanyBusinessUnitMasterDTO>();
		CompanyBusinessUnitMasterDTO def=new CompanyBusinessUnitMasterDTO(0L,"0","0","Choose an Option");
		businessList.add(def);
		businessList.addAll(companyBusinessUnitMasterService.getcompanyBUMNCList(company));
		return businessList;
	}
	
	@GetMapping("/getcompanyBUMNCListnew/{company}")
	public List<CompanyBusinessUnitMasterDTOC> getcompanyBUMNCListnew(@PathVariable(value = "company") String company)
	{
		return companyBusinessUnitMasterService.getcompanyBUMNCListnew(company);
	}
	
	@GetMapping("/getcompanyBUMNCListFastApi/{company}")
	public List<Map<String,Object>> getcompanyBUMNCListFastApi(@PathVariable(value = "company") String company)
	{
		return companyBusinessUnitMasterService.getcompanyBUMNCListFastApi(company);
	}
	
	@GetMapping("/getcompanyBUnitList/{company}")
	public List<Business_unit> getcompanyBUnameList(@PathVariable(value = "company") String company)
	{
		List<Business_unit> businessList= new ArrayList<Business_unit>();
		
		Business_unit def=new Business_unit();
		businessList.add(def);
		businessList.forEach((e->{
			e.setBusinessunit_id("0");
			e.setBusinessunit_name("Choose an Option");
		}));
		
		businessList.add(def);
		businessList.addAll(companyBusinessUnitMasterService.getcompanyBUnameList(company));
		return businessList;
	}
	//Delete
	@GetMapping("/getcompanyBUMNCList")
	public List<CompanyBusinessUnitMasterDTO> getcompanyBUMNCList()
	{
		List<CompanyBusinessUnitMasterDTO> businessList= new ArrayList<CompanyBusinessUnitMasterDTO>();
		CompanyBusinessUnitMasterDTO def=new CompanyBusinessUnitMasterDTO(0L,"0","0","Choose an Option");
		businessList.add(def);
		businessList.addAll(companyBusinessUnitMasterService.getcompanyBUMNCList());
		return businessList;
	}
	
	@GetMapping("/getCompanyBusinessUnits/{company}")
	public List<CompanyBusinessUnitMasterDTO> getCompanyBusinessUnits(@PathVariable(value = "company") String company)
	{
		List<CompanyBusinessUnitMasterDTO> businessList= new ArrayList<CompanyBusinessUnitMasterDTO>();
		businessList.addAll(companyBusinessUnitMasterService.getcompanyBUMNCList(company));
		return businessList;
	}
	//Delete
	@GetMapping("/getCompanyBusinessUnits")
	public List<CompanyBusinessUnitMasterDTO> getCompanyBusinessUnits()
	{
		List<CompanyBusinessUnitMasterDTO> businessList= new ArrayList<CompanyBusinessUnitMasterDTO>();
		businessList.addAll(companyBusinessUnitMasterService.getcompanyBUMNCList());
		return businessList;
	}
	
	@GetMapping("/bUnitNameByCode/{buCode}")
	public CompanyBusinessUnitMasterDTO bUnitNameByCode(@PathVariable(value = "buCode") String buCode)
	{
		CompanyBusinessUnitMasterDTO def=new CompanyBusinessUnitMasterDTO(0L,"0","0","Choose an Option");
		if(buCode.compareTo("0")==0) {
			return def;
		}
		else {
			return companyBusinessUnitMasterService.getBUnitNameByCode(buCode);	
		}
	}
	
	
	@GetMapping("/getCompanyBUAddress/{Id}")
	public CompanyBusinessUnitMasterDTO CompanyBUAddress(@PathVariable(value = "Id") String Id)
	{
		return companyBusinessUnitMasterService.CompanyBUAddress(Id);
	}
	
	@GetMapping("/getCompBusinessUnitDiff/{bunitid}")
	public ResponseEntity<List<CompanyBusinessUnitMasterDTO>> getCompBusinessUnitDiff(@PathVariable(value = "bunitid") String bunitid) 
	{
		List<CompanyBusinessUnitMasterDTO> val=companyBusinessUnitMasterService.getCompBusinessUnitDiff(bunitid);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	//..................Ware House .............
	@Autowired
	WarehouseMasterService WarehouseMasterService;
	
	@GetMapping("/getWareHouseNCList")
	public List<Warehouse_master> getWareHouseNCList()
	{
		return WarehouseMasterService.getWareHouseNCList();
	}
	
	
	//...............Bin..................
	
	@Autowired
	BinMasterService binMasterService;
	
	@GetMapping("/getBinNCList")
	public List<Bin_master> getBinNCList()
	{
		return binMasterService.getBinNCList();
	}
	
	//............Custom UOM Master..............

	
	//......................Department....................................
	@Autowired
	DepartmentMasterService departmentMasterService;
	
	@GetMapping("/getDepartmentNCList")
	public List<DepartmentMasterDTO> getDepartmentNCList()
	{
		List<DepartmentMasterDTO> deptList= new ArrayList<DepartmentMasterDTO>();
		DepartmentMasterDTO def=new DepartmentMasterDTO("0","Choose an option",false);
		deptList.add(def);
		deptList.addAll(departmentMasterService.getDepartmentNCList());
		
		return deptList;
	}
	
	//.....................Misc.................................
	@Autowired
	MiscMasterService miscMasterService;
	
	@GetMapping("/getMiscNCList")
	public List<Misc_master> getMiscNCList()
	{
		return miscMasterService.getMiscNCList();
	}
	
	
	//................Tax Type Master..............
	@Autowired
	TaxTypeMasterService taxTypeMasterService;
	
	@GetMapping("/getTaxTypeNCList")
	public List<Tax_type_master> getTaxTypeNCList()
	{
		return taxTypeMasterService.getTaxTypeNCList();
	}
	
	//..............Vehicle Type ..............................
	@Autowired
	VehicleTypeMasterService vehicleTypeMasterService;
	
	@GetMapping("/getVehicleTypeNCList")
	public List<VehicleTypeMaster> getVehicleTypeNCList()
	{
		return vehicleTypeMasterService.getVehicleTypeNCList();
	}
	
	//..............Vehicle ..............................
	@Autowired
	VehicleMasterService vehicleMasterService;
	
	@GetMapping("/getVehicleNCList")
	public List<VehicleMaster> getVehicleNCList()
	{
		return vehicleMasterService.getVehicleNCList();
	}
		
	//..............Transporting Charges Matrix ..............................
		@Autowired
		TransportationChgsMatrixMasterService transportationChgsMatrixMasterService;
				
		@GetMapping("/getTransportationCMNCList")
		public List<TransportationChgsMatrixMaster> getTransportationCMNCList()
		{
			return transportationChgsMatrixMasterService.getTransportationCMNCList();
		}
		
		//..............Transporting Charges Matrix ..............................
		@Autowired
		Pur_IndentService pur_IndentService;
				
		@GetMapping("/getPurIndentCList")
		public List<Pur_Indent> getPurIndentCList()
		{
			return pur_IndentService.getPurIndentCList();
		}
		
		@GetMapping("/getPurIndentCYList")
		public List<Pur_Indent> getPurIndentCYList()
		{
			return pur_IndentService.getPurIndentCYList();
		}
		
		@GetMapping("/getPurIndentDDCList/{ccc}/{itemtype}")
		public List<Pur_IndentDTO> getPurIndentDDCList(@PathVariable(value = "ccc") String ccc,@PathVariable(value = "itemtype") String itemtype)
		{
			return pur_IndentService.getPurIndentDDCList(ccc,itemtype);
		}
		
		
		@GetMapping("/getPurIndentCNQUPList/{indent_no}")
		public List<Pur_Indent_DetailsDTO> getPurIndentCNQUPList(@PathVariable(value = "indent_no") String indent_no)
		{
			return pur_IndentService.getPurIndentCNQUPList(indent_no);
		}
		
		@GetMapping("/getPurIndentDetailsList/{indent_no}")
		public List<Pur_Indent_DetailsDTO> getPurIndentDetailsList(@PathVariable(value = "indent_no") String indent_no)
		{
			return pur_IndentService.getPurIndentDetailsList(indent_no);
		}
		
		
	//..............Payment Term ..............................
		@Autowired
		Acc_pay_term_masterService acc_pay_term_masterService;
				
		@GetMapping("/getPayTermList")
		public List<Acc_pay_term_master> getPayTermList()
		{
			return acc_pay_term_masterService.getPayTermList();
		}
		
		@GetMapping("/getPayTermNCList")
		public List<Acc_pay_term_masterDTO> getPayTermNCList()
		{
			List<Acc_pay_term_masterDTO> payList= new ArrayList<Acc_pay_term_masterDTO>();
			Acc_pay_term_masterDTO def=new Acc_pay_term_masterDTO("0","Choose an Option",false);
			payList.add(def);
			payList.addAll(acc_pay_term_masterService.getPayTermNCList());
			
			return payList;
		}
		
		@GetMapping("/getPayTermNCListFast")
		public List<Map<String,Object>> getPayTermNCListFast()
		{
			return acc_pay_term_masterService.getPayTermNCListFast();
		}
		
//..................................Group ......................................
	
	@Autowired
	GroupmasterService groupmasterService;
	
	@GetMapping("/getGroupmasterCList")
	public List<GroupmasterDTO> getGroupmasterCList()
	{
		return groupmasterService.getGroupmasterCList();
	}
	
	@GetMapping("/getGroupmasterNByCList/{code}")
	public GroupmasterDTO getGroupmasterNByCList(@PathVariable(value = "code") String code)
	{
		return groupmasterService.getGroupmasterNByCList(code);
	}
	
	//..................................Sub Group ......................................
	
	@GetMapping(value = "/getSubgroupName")
	public List<SubgroupmasterDTO> getSubgroupName() {
		
		List<SubgroupmasterDTO> subGroupList= new ArrayList<SubgroupmasterDTO>();
		SubgroupmasterDTO def=new SubgroupmasterDTO("0","Choose an option");
		subGroupList.add(def);
		subGroupList.addAll(subgroupmasterService.getSubgroupNames());
		
		return subGroupList;
	}
	
	@GetMapping("/getSubGroupmasterNByC/{code}")
	public SubgroupmasterDTO getSubGroupmasterNByC(@PathVariable(value = "code") String code)
	{
		return subgroupmasterService.getSubGroupmasterNByC(code);
	}
		
	@GetMapping("/subgroupByGr/{groupName}")
	public List<Subgroupmaster> getsubgroupByGr(@PathVariable(value = "groupName") String groupName)
	{
		return subgroupmasterService.getSubgroupByGr(groupName);
	}
	
	@Autowired
	LedgermasterService ledgermasterService;
	
	@GetMapping("/getLedgerBySGr/{subgroup}")
	public List<LedgermasterDTO> getLedgerBySGr(@PathVariable(value = "subgroup") String subgroup)
	{
		List<LedgermasterDTO> ledgerList= new ArrayList<LedgermasterDTO>();
		LedgermasterDTO def=new LedgermasterDTO("0","Choose an option");
		ledgerList.add(def);
		ledgerList.addAll(ledgermasterService.getLedgerBySGrp(subgroup));
		
		return ledgerList;
	}
	
	@GetMapping("/getLedger")
	public List<LedgermasterDTO> getLedger()
	{
		List<LedgermasterDTO> ledgerList= new ArrayList<LedgermasterDTO>();
		LedgermasterDTO def=new LedgermasterDTO("0","Choose an option");
		ledgerList.add(def);
		ledgerList.addAll(ledgermasterService.getLedger());
		
		return ledgerList;
	}
	
	@GetMapping("/getLedgerNew")
	public List<Map<String,Object>> getLedgerNew()
	{
		return ledgermasterService.getLedgerNew();
	}
	
	@GetMapping(value ="/getLedgerWithBACH")
	public ResponseEntity<List<LedgermasterDTO>> getLedgerWithBACH() {
		List<LedgermasterDTO> ledgerList= new ArrayList<LedgermasterDTO>();
		
		ledgerList.addAll(ledgermasterService.getLedgerWithBACH());
		
		return ResponseEntity.ok().body(ledgerList);
	}
	
	@GetMapping("/getDutiesTaxesLedger")
	public List<LedgermasterDTO> getDutiesTaxesLedger()
	{
		List<LedgermasterDTO> ledgerList= new ArrayList<LedgermasterDTO>();
		LedgermasterDTO def=new LedgermasterDTO("0","Choose an option");
		ledgerList.add(def);
		ledgerList.addAll(ledgermasterService.getDutiesTaxesLedger());
		
		return ledgerList;
	}
	
	@GetMapping("/getControlLedgers")
	public List<LedgermasterDTO> getControlLedgers()
	{
		List<LedgermasterDTO> ledgerList= new ArrayList<LedgermasterDTO>();
		LedgermasterDTO def=new LedgermasterDTO("0","Choose an option");
		ledgerList.add(def);
		ledgerList.addAll(ledgermasterService.getControlLedgers());
		return ledgerList;
	}
	
	@GetMapping("/getBankLedger")
	public List<LedgermasterDTO> getBankLedger()
	{
		return ledgermasterService.getBankLedger();
	}
	
	@GetMapping("/getLedgerName")
	public ResponseEntity<LedgermasterDTO> getLedgerName(@RequestParam(defaultValue = "") String ledgerid)
	{
		LedgermasterDTO ledgermasterDTO = ledgermasterService.getLedgerName(ledgerid);
		return new ResponseEntity<LedgermasterDTO>(ledgermasterDTO, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(value="/getCustomUOMNCList",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomUomMasterDTO> getCustomUOMNCList()
	{
		List<CustomUomMasterDTO> custList= new ArrayList<CustomUomMasterDTO>();
		
		CustomUomMasterDTO def=new CustomUomMasterDTO("0","0","Choose an Option","0","0","0","0",false,"0","0",0,0,"0");
		custList.add(def);
		custList.addAll(customUomMasterService.getCustomUOMNCList());
		
		return custList;
	}
	
	@GetMapping(value="/getStandardCustomUOM/{company}")
	public List<CustomUomMasterDTO> getStandardCustomUOM(@PathVariable(value = "company") String company)
	{
		List<CustomUomMasterDTO> custList= new ArrayList<CustomUomMasterDTO>();
		
		CustomUomMasterDTO def=new CustomUomMasterDTO("0","0","Choose an Option","0","0","0","0",false,"0","0",0,0,"0");
		custList.add(def);
		custList.addAll(customUomMasterService.getStandardCustomUOM());
		
		return custList;
	}
	//Delete 
	@GetMapping(value="/getStandardCustomUOM")
	public List<CustomUomMasterDTO> getStandardCustomUOM()
	{
		List<CustomUomMasterDTO> custList= new ArrayList<CustomUomMasterDTO>();
		
		CustomUomMasterDTO def=new CustomUomMasterDTO("0","0","Choose an Option","0","0","0","0",false,"0","0",0,0,"0");
		custList.add(def);
		custList.addAll(customUomMasterService.getStandardCustomUOM());
		
		return custList;
	}
	
	@GetMapping(value="/getWeighmentCustomUOM",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomUomMasterDTO> getWeighmentCustomUOM()
	{
		List<CustomUomMasterDTO> custList= new ArrayList<CustomUomMasterDTO>();
		
		CustomUomMasterDTO def=new CustomUomMasterDTO("0","0","Choose an Option","0","0","0","0",false,"0","0",0,0,"0");
		custList.add(def);
		custList.addAll(customUomMasterService.getWeighmentCustomUOM());
		
		return custList;
	}
	
	//..............Acc group ..............................
	@Autowired
	Acc_group_masterService acc_group_masterService;
	
	@GetMapping("/groupCodeList")
	public List<Acc_group_master> groupCode()
	{
		return acc_group_masterService.getGroupcode();
	}
	
	
	@GetMapping("/groupNameByCode/{grpCode}")
	public List<Acc_group_master> groupNameByCode(@PathVariable(value = "grpCode") String grpCode)
	{
		return acc_group_masterService.getGroupNameByCode(grpCode);
	}
	
	//..............Acc group End..............................
	
	//..............Acc Cost center..............................
	@Autowired
	Acc_cost_centre_masterService acc_cost_centre_masterService;
	
	@GetMapping("/getAccCostCentreNCList")
	public List<Acc_cost_centre_masterDTO> getAccCostCentreN()
	{
		return acc_cost_centre_masterService.getAccCostCentreN();
	}
	
	//..............Acc Cost category..............................
		@Autowired
		Acc_cost_category_masterService acc_cost_category_masterService;
		
		@GetMapping("/getAccCostCategoriCNList")
		public List<Acc_cost_category_masterDTO> getAccCostCategoriCNList()
		{
			return acc_cost_category_masterService.getAccCostCategoriCNList();
		}
		
		@GetMapping("/getAccCostCatNListbyC/{grpCode}")
		public Acc_cost_category_masterDTO getAccCostCatNListbyC(@PathVariable(value = "grpCode") String grpCode)
		{
			return acc_cost_category_masterService.getAccCostCatNListbyC(grpCode);
		}
		
		@GetMapping("/getWHNListbyCode/{whCode}")
		public WarehouseMasterDTO getWHNListbyCode(@PathVariable(value = "whCode") String whCode)
		{
			return warehouseMasterService.getWHNListbyCode(whCode);
		}
		
		@GetMapping("/getWHListbyBUnit/{bunit}")
		public List<WarehouseMasterDTO> getWHListbyBUnit(@PathVariable(value = "bunit") String bunit)
		{
			//System.out.println("bunit/"+bunit);
			return warehouseMasterService.getWHListbyBUnit(bunit);
		}
		
		@GetMapping("/getWHListbyBUnitFastApi/{bunit}")
		public List<Map<String, Object>> getWHListbyBUnitFastApi(@PathVariable(value = "bunit") String bunit)
		{
			return warehouseMasterService.getWHListbyBUnitFastApi(bunit);
		}
		
		
		@GetMapping("/getAllWarehouse")
		public List<WarehouseMasterDTO> getAllWarehouse()
		{
		
			return warehouseMasterService.getAllWarehouse();
		}
		
		@GetMapping(value = "/getStackNoByWarehouse/{wh_code}")
		public List<Map<String, Object>> getStackNoByWarehouse(@PathVariable(value = "wh_code") String wh_code) 
		{
			return warehouseMasterService.getStackNoByWarehouse(wh_code);
		}
		
		@GetMapping(value = "/getStackUom/{rack}")
		public Map<String, Object> getStackUom(@PathVariable(value = "rack") String rack) 
		{
			return warehouseMasterService.getStackUom(rack);
		}
		
		@GetMapping("/getTaxNameRate")
		public List<Tax_code_detailsDTO> getTaxNameRate()
		{
			return tax_code_masterService.getTaxNameRate();
		}
		
		@GetMapping("/taxlistbycode/{code}")
		public Tax_code_details taxlistbycode(@PathVariable(value = "code") String code)
		{
			return tax_code_masterService.taxlistbycode(code);
		}
		
		@GetMapping("/getPurEnqItemDtlsList")
		public List<Pur_Enquiry_Service_DetailsDTO> getPurEnqItemDtlsList()
		{
			return pur_EnquiryService.getPurEnqItemDtlsList();
		}
		
		@Autowired
		Pur_QuotationService pur_QuotationService;
		
		@GetMapping("/getPurQtyItemDtlsList")
		public List<Pur_Quotation_ServiceDTO> getPurQtyItemDtlsList()
		{
			return pur_QuotationService.getPurQtyItemDtlsList();
		}
		
		
		@GetMapping("/getPurQtyDDCList/{ccc}")
		public List<Pur_QuotationDTO> getPurQtyDDCList(@PathVariable(value = "ccc") String ccc)
		{
			return pur_QuotationService.getPurQtyDDCList(ccc);
		}
		
		@GetMapping("/getPurQuotPrevList")
		public List<Pur_QuotationDTO> getPurQuotPrevList()
		{
			return pur_QuotationService.getPurQuotPrevList();
		}
		
		@GetMapping("/getPurQuotThruSuppList/{reftype}/{suppid}/{itemtype}")
		public List<Pur_QuotationDTO> getPurQuotThruSuppList(@PathVariable(value = "reftype") String reftype,@PathVariable(value = "suppid") String suppid,@PathVariable(value = "itemtype") String itemtype)
		{
			return pur_QuotationService.getPurQuotThruSuppList(reftype,suppid,itemtype);
		}
		
		@GetMapping("/getPurQtyCNQUPList/{quotation_no}")
		public List<Pur_Quotation_ServiceDTO> getPurQtyCNQUPList(@PathVariable(value = "quotation_no") String quotation_no)
		{
			return pur_QuotationService.getPurQtyCNQUPList(quotation_no);
		}
		
		@GetMapping("/getPurQuotBrokerDtls/{quot_id}")
		public List<Pur_Quotation_BrokerDTO> getPurQuotBrokerDtls(@PathVariable(value = "quot_id") String quot_id)
		{
			return pur_QuotationService.getPurQuotBrokerDtls(quot_id);
		}
		
		@Autowired
		Pur_OrderService pur_OrderService;
		
		@GetMapping("/getPurOrdItemDtlsList")
		public List<Pur_Order_Item_DetailsDTO> getPurOrdItemDtlsList()
		{
			return pur_OrderService.getPurOrdItemDtlsList();
		}
		
		@GetMapping("/getPurOrdItemDtls/{orderid}/{itemcode}")
		public Pur_Order_Item_DetailsDTO getPurOrdItemDtls(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "itemcode") String itemcode)
		{
			return pur_OrderService.getPurOrdItemDtls(orderid,itemcode);
		}
		
		@GetMapping("/getPurOrdItemDtlsnew/{orderid}/{itemcode}/{packing}")
		public Pur_Order_Item_DetailsDTO getPurOrdItemDtlsnew(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "itemcode") String itemcode,@PathVariable(value = "packing") String packing)
		{
			return pur_OrderService.getPurOrdItemDtlsnew(orderid,itemcode,packing);
		}
		
		@GetMapping("/getPurOrdItemDtlsnewpoitemnumber/{orderid}")
		public Pur_Order_Item_DetailsDTO getPurOrdItemDtlsnewpoitemnumber(@PathVariable(value = "orderid") String orderid)
		{
			return pur_OrderService.getPurOrdItemDtlsnewpoitemnumber(orderid);
		}
		
		
		@GetMapping("/getPurOrderItemWeightViewList/{orderid}/{itemcode}")
		public Pur_Order_Item_DetailsDTO getPurOrderItemWeightViewList(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "itemcode") String itemcode)
		{
			return pur_OrderService.getPurOrderItemWeightViewList(orderid,itemcode);
		}
		
		@GetMapping("/getPurchaseOrdernetWeightnew/{orderid}/{itemcode}/{packing_item}")
		public Pur_Order_Item_DetailsDTO getPurchaseOrdernetWeightnew(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "itemcode") String itemcode,@PathVariable(value = "packing_item") String packing_item)
		{
			return pur_OrderService.getPurchaseOrdernetWeightnew(orderid,itemcode,packing_item);
		}
		
		
		@GetMapping("/getPurOrdreceipt_criteria/{orderid}")
		public List<Pur_OrderDTO> getPurOrdreceipt_criteria(@PathVariable(value = "orderid") String orderid)
		{
			return pur_OrderService.getPurOrdreceipt_criteria(orderid);
		}
		
		
		
		@GetMapping("/getPurOrdBrokerList/{pur_order_no}")
		public List<Pur_Order_brokerDTO> getPurOrdBrokerList(@PathVariable(value = "pur_order_no") String pur_order_no)
		{
			return pur_OrderService.getPurOrdBrokerList(pur_order_no);
		}
		
		@GetMapping("/getPurOrdTrans/{pur_order_no}")
		public Pur_Order_Trans_InfoDTO getPurOrdTrans(@PathVariable(value = "pur_order_no") String pur_order_no)
		{
			return pur_OrderService.getPurOrdTrans(pur_order_no);
		}
		
		
		@GetMapping("/getPurOrdDDCList/{pur_order_no}")
		public List<Pur_OrderDTO> getPurOrdDDCList(@PathVariable(value = "pur_order_no") String pur_order_no)
		{
			return pur_OrderService.getPurOrdDDCList(pur_order_no);
		}
		
		@GetMapping("/getPurOrdTransChgsDynList/{pur_order_no}")
		public List<Map<String,Object>> getPurOrdTransChgsDynList(@PathVariable(value = "pur_order_no") String pur_order_no)
		{
			return pur_OrderService.getPurOrdTransChgsDynList(pur_order_no);
		}
		
		@GetMapping("/getPurOrdStoreDynList/{pur_order_no}")
		public List<Map<String,Object>> getPurOrdStoreDynList(@PathVariable(value = "pur_order_no") String pur_order_no)
		{
			return pur_OrderService.getPurOrdStoreDynList(pur_order_no);
		}
		
		@GetMapping("/getPurOrdAllList")
		public List<Pur_OrderDTO> getPurOrdAllList()
		{
			return pur_OrderService.getPurOrdAllList();
		}
		
		@GetMapping("/getPurOrdDetails/{orderid}")
		public Pur_OrderDTO getPurOrdDetails(@PathVariable(value = "orderid") String orderid)
		{
			return pur_OrderService.getPurOrdDetails(orderid);
		}

		@GetMapping("/getPurOrdcheckingwAdvice/{pur_no}/{unadvice_id}")
		public List<Wm_unload_advice_item_dtlsDTO> getPurOrdcheckingwAdvice(@PathVariable(value = "pur_no") String pur_no,@PathVariable(value = "unadvice_id") String unadvice_id)
		{
			return pur_OrderService.getPurOrdcheckingwAdvice(pur_no,unadvice_id);
		}
		
		
		@GetMapping("/getPurOrdThruSuppList/{suppid}")
		public List<Pur_OrderDTO> getPurOrdThruSuppList(@PathVariable(value = "suppid") String suppid)
		{
			return pur_OrderService.getPurOrdThruSuppList(suppid);
		}
		
		@GetMapping("/getPurOrdThruSuppAdvReq/{suppid}")
		public List<Pur_OrderDTO> getPurOrdThruSuppAdvReq(@PathVariable(value = "suppid") String suppid)
		{

			return pur_OrderService.getPurOrdThruSuppAdvReq(suppid);
		}
		
		@GetMapping("/getPurOrdAdvThruSupp/{suppid}/{businessunit}")
		public List<Pur_OrderDTO> getPurOrdAdvThruSupp(@PathVariable(value = "suppid") String suppid,@PathVariable(value = "businessunit") String businessunit)
		{
			return pur_OrderService.getPurOrdAdvThruSupp(suppid,businessunit);
		}
		
		@GetMapping("/getPurOrdAdvThruSuppFast/{suppid}/{businessunit}")
		public List<Map<String,Object>> getPurOrdAdvThruSuppFast(@PathVariable(value = "suppid") String suppid,@PathVariable(value = "businessunit") String businessunit)
		{
			return pur_OrderService.getPurOrdAdvThruSuppFast(suppid,businessunit);
		}
		
		@GetMapping("/getGrnThroughPurOrd/{businessunit}/{pur_type}")
		public List<Pur_OrderDTO> getGrnThroughPurOrd(@PathVariable(value = "businessunit") String businessunit,@PathVariable(value = "pur_type") String pur_type)
		{
			return pur_OrderService.getGrnThroughPurOrd(businessunit,pur_type);
		}
		
		@GetMapping("/getGrnThroughPurOrdstore/{businessunit}/{pur_type}")
		public List<Pur_OrderDTO> getGrnThroughPurOrdstore(@PathVariable(value = "businessunit") String businessunit,@PathVariable(value = "pur_type") String pur_type)
		{
			return pur_OrderService.getGrnThroughPurOrdstore(businessunit,pur_type);
		}
		
		@GetMapping("/getGrnThroughPurOrdpacking/{businessunit}/{pur_type}")
		public List<Pur_OrderDTO> getGrnThroughPurOrdpacking(@PathVariable(value = "businessunit") String businessunit,@PathVariable(value = "pur_type") String pur_type)
		{
			return pur_OrderService.getGrnThroughPurOrdpacking(businessunit,pur_type);
		}
		
		@GetMapping("/getPurOrdRtnApp")
		public ResponseEntity<List<Pur_OrderDTO>> getPurOrdRtnApp(@RequestParam(defaultValue = "") String bunit,
				@RequestParam(defaultValue = "") String supplier,@RequestParam(defaultValue = "") String tdate,
				@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear)
		{
			List<Pur_OrderDTO> list = pur_OrderService.getPurOrdRtnApp(bunit,supplier,tdate,company,finyear);
			return new ResponseEntity<List<Pur_OrderDTO>>(list, new HttpHeaders(), HttpStatus.OK);
		}
		
		@GetMapping("/getPurOrdCNQUPList/{ccc}")
		public List<Pur_Order_Item_DetailsDTO> getPurOrdCNQUPList(@PathVariable(value = "ccc") String ccc)
		{
			return pur_OrderService.getPurOrdCNQUPList(ccc);
		}
		
		
	    @GetMapping("/getpurorderstorepurchase/{orderid}")
		//public List<Pur_Order_Item_DetailsDTO> getpurorderstorepurchase(@PathVariable(value = "orderid") String orderid)
		//{
		//	return pur_OrderService.getpurorderstorepurchase(orderid);
		//}
	    public List<Map<String, Object>> getpurorderstorepurchase(@PathVariable(value = "orderid") String orderid)
		{
			return pur_OrderService.getpurorderstorepurchase(orderid);
		}
		
		
	  @GetMapping("/getpurorderpacking/{orderid}")
	//  public List<Pur_Order_Item_DetailsDTO> getpurorderpacking(@PathVariable(value = "orderid") String orderid)
	//  {
	//	  return pur_OrderService.getpurorderpacking(orderid);
	 // }
	  public List<Map<String, Object>> getpurorderpacking(@PathVariable(value = "orderid") String orderid)
	  {
		 return pur_OrderService.getpurorderpacking(orderid);
	  }
	 
		
		// Purchase Order Packing Print Start 
		@GetMapping("/getTermsConditionsDtlsList")
		public List<Map<String, Object>> getTermsConditionsDtlsList()
		{
			return pur_OrderService.getTermsConditionsDtlsList();
		}

		    @Autowired
			Charge_MasterService charge_MasterService;
			
			@GetMapping("/getChargeMasterList")
			public List<Charge_MasterDTO> getChargeMasterList()
			{
				List<Charge_MasterDTO> chgList= new ArrayList<Charge_MasterDTO>();
				
				Charge_MasterDTO def=new Charge_MasterDTO("0","0","Choose an Option");
				chgList.add(def);
				chgList.addAll(charge_MasterService.getChargeMasterList());
				
				return chgList;
			}
			
			@GetMapping("/getChargeMasterdtlsList/{code}")
			public List<Charge_MasterdtlsDTO> getChargeMasterdtlsList(@PathVariable(value = "code") String code)
			{
				return charge_MasterService.getChargeMasterdtlsList(code);
			}
			
			/*********** Start Unload Advice ******************/
			
			@GetMapping("/getUnloadAdvice/{vcode}")
			public Wm_unload_adviceDTO getUnloadAdvice(@PathVariable(value = "vcode") String vcode)
			{
				return wm_unload_adviceService.getUnloadAdvice(vcode);
			}
			
			@GetMapping("/getVehiclenoall")
			public List<VehicleMasterDTO> getVehiclenoall()
			{
				List<VehicleMasterDTO>vechilelist = new ArrayList<VehicleMasterDTO>();
				 
				VehicleMasterDTO def= new VehicleMasterDTO();
				vechilelist.add(def);
				
				vechilelist.forEach((e->{
					e.setVehicle_id("0");
					e.setVehicle_no("Choose an option");
					
				}));
				
				vechilelist.addAll(wm_unload_adviceService.getVehiclenoall());
				return vechilelist;			
			}
			
			@GetMapping("/getVehiclenoallNew")
			public List<Map<String, Object>> getVehiclenoallNew()
			{
				return wm_unload_adviceService.getVehiclenoallNew();
							
			}
			
			@GetMapping("/getUnloadDetails/{unadviceid}")
			public Wm_unload_adviceDTO getUnloadDetails(@PathVariable(value = "unadviceid") String unadviceid)
			{
				return wm_unload_adviceService.getUnloadDetails(unadviceid);
			} 
			
			
			
			@GetMapping("/getUnloadDetailsFastApi/{unadviceid}")
			public Map<String, Object> getUnloadDetailsFastApi(@PathVariable(value = "unadviceid") String unadviceid)
			{
				return wm_unload_adviceService.getUnloadDetailsFastApi(unadviceid);
			}
			
			@GetMapping("/getUnload_multi_popup/{unadviceid}")
			public Wm_unload_adviceDTO getUnload_multi_popup(@PathVariable(value = "unadviceid") String unadviceid)
			{
				return wm_unload_adviceService.getUnload_multi_popup(unadviceid);
			} 
			
			@GetMapping("/getUnloadAdvThruVehi/{vehicleid}")
			public Wm_unload_adviceDTO getUnloadAdvThruVehi(@PathVariable(value = "vehicleid") String vehicleid)
			{
				return wm_unload_adviceService.getUnloadAdvThruVehi(vehicleid);
			}
			
			@GetMapping("/getUnloadItemList/{code}")
			public List<Wm_unload_advice_item_dtlsDTO> getUnloadItemList(@PathVariable(value = "code") String code)
			{
				return wm_unload_adviceService.getUnloadItemList(code);
			}
			
			@GetMapping("/getUnloadItemListrevise/{unadvice_id}")
			public List<Wm_unload_advice_item_dtlsDTO> getUnloadItemListrevise(@PathVariable(value = "unadvice_id") String unadvice_id)
			{
				return wm_unload_adviceService.getUnloadItemListrevise(unadvice_id);
			}
			
			
			@GetMapping("/getUnloadItemDtlsThruVehi/{vehicleid}")
			public List<Wm_unload_advice_item_dtlsDTO> getUnloadItemDtlsThruVehi(@PathVariable(value = "vehicleid") String vehicleid)
			{
				return wm_unload_adviceService.getUnloadItemDtlsThruVehi(vehicleid);
			}
			@GetMapping("/getUnloadadvanceList/{unadviceid}")
			public Wm_unload_adviceDTO getUnloadadvanceList(@PathVariable(value = "unadviceid") String unadviceid)
			{
				return wm_unload_adviceService.getUnloadDetails(unadviceid);
			} 
			
			@GetMapping("/getUnloadadvanceListNew/{unadviceid}")
			public Wm_unload_adviceDTO getUnloadadvanceListNew(@PathVariable(value = "unadviceid") String unadviceid)
			{
				return wm_unload_adviceService.getUnloadDetailsNew(unadviceid);
			}
			
			@GetMapping("/getCBUdetails/{bunit}")
			public CompanyBusinessUnitMasterDTO getCBUdetails(@PathVariable(value = "bunit") String bunit)
			{
				//System.out.println("bunit"+bunit);
				return companyBusinessUnitMasterService.getCBUdetails(bunit);
			}
			
			@GetMapping("/getCBUdetailsById/{bunit}")
			public CompanyBusinessUnitMasterDTO getCBUdetailsById(@PathVariable(value = "bunit") String bunit)
			{
				//System.out.println("bunit"+bunit);
				return companyBusinessUnitMasterService.getCBUdetailsById(bunit);
			}
			@GetMapping("/getUnloadAdvPOItemDtls/{adviceid}/{porderid}")
			public List<Wm_unload_advice_item_dtlsDTO> getUnloadAdvPOItemDtls(@PathVariable(value = "adviceid") String adviceid,@PathVariable(value = "porderid") String porderid)
			{
				return wm_unload_adviceService.getUnloadAdvPOItemDtls(adviceid,porderid);
			}
			
			@GetMapping("/getUnloadAdvPartyWmThruVehi/{vehicleid}")
			public Wm_unload_advice_party_wmDTO getUnloadAdvPartyWmThruVehi(@PathVariable(value = "vehicleid") String vehicleid)
			{
				return wm_unload_adviceService.getUnloadAdvPartyWmThruVehi(vehicleid);
			}
			@GetMapping("/getUnloadAdvTransInfoThruVehi/{vehicleid}")
			public Wm_unload_advice_trans_infoDTO getUnloadAdvTransInfoThruVehi(@PathVariable(value = "vehicleid") String vehicleid)
			{
				return wm_unload_adviceService.getUnloadAdvTransInfoThruVehi(vehicleid);
			}
			
			@GetMapping("/getUnloadCodeList/{bpartner}")
			public List<Wm_unload_adviceDTO> getUnloadCodeList(@PathVariable(value = "bpartner") String bpartner)
			{
				return wm_unload_adviceService.getUnloadCodeList(bpartner);
			}
			
			@GetMapping("/getUnloadCodeList")
			public List<Wm_unload_adviceDTO> getUnloadCodeList()
			{
				return wm_unload_adviceService.getUnloadCodeList();
			}
			
			@GetMapping("/getUnloadVehiThruPurchase")
			public List<Wm_unload_adviceDTO> getUnloadVehiThruPurchase()
			{
				return wm_unload_adviceService.getUnloadVehiThruPurchase();
			}
			
			@GetMapping("/getVehicleListWeighment")
			public List<Vehicle_weighment_load_unload> getVehicleListWeighment()
			{
				return wm_unload_wgmntService.getVehicleListWeighment();
			}
			
			@GetMapping("/getVehicleListWeighmentnew")
			public List<Vehicle_weighment_load_unload> getVehicleListWeighmentnew()
			{
				return wm_unload_wgmntService.getVehicleListWeighmentnew();
			}
			@GetMapping("/getVehicleListWeighmenOutAuth")
			public List<Vehicle_weighment_load_unload> getVehicleListWeighmenOutAuth()
			{
				return wm_unload_wgmntService.getVehicleListWeighmenOutAuth();
			}
			
			
			@GetMapping("/getUnloadVehiThruSR")
			public List<Wm_unload_adviceDTO> getUnloadVehiThruSR()
			{
				return wm_unload_adviceService.getUnloadVehiThruSR();
			}
			
			@GetMapping("/getUnloadVehiThruStkTransfer")
			public List<Wm_unload_adviceDTO> getUnloadVehiThruStkTransfer()
			{
				return wm_unload_adviceService.getUnloadVehiThruStkTransfer();
			}
			
			@GetMapping("/getUnloadAdvRefOpenAdv")
			public List<Wm_unload_adviceDTO> getUnloadAdvRefOpenAdv()
			{
				return wm_unload_adviceService.getUnloadAdvRefOpenAdv();
			}
			
			@GetMapping("/getUnloadAdvRefOpenAdvWt2")
			public List<Wm_unload_adviceDTO> getUnloadAdvRefOpenAdvWt2()
			{
				List<Wm_unload_adviceDTO>unadvicelist = new ArrayList<Wm_unload_adviceDTO>();
				 
				Wm_unload_adviceDTO def= new Wm_unload_adviceDTO();
				unadvicelist.add(def);
				
				unadvicelist.forEach((e->{
					e.setUnadviceid("0");
					e.setUnadviceno("Choose an option");
					
				}));
				unadvicelist.addAll(wm_unload_adviceService.getUnloadAdvRefOpenAdvWt2());
				return unadvicelist;
			}
			
			@GetMapping("/getUnloadAdvRefOpenAdvWt2New")
			public List<Map<String, Object>> getUnloadAdvRefOpenAdvWt2New()
			{
				return wm_unload_adviceService.getUnloadAdvRefOpenAdvWt2New();
			}
			
			@GetMapping("/getUnloadAdvRefOpenAdvQc")
			public List<Wm_unload_adviceDTO> getUnloadAdvRefOpenAdvQc()
			{
				return wm_unload_adviceService.getUnloadAdvRefOpenAdvQc();
			}
			
			@GetMapping("/getUnloadAdvRefPO")
			public List<Wm_unload_adviceDTO> getUnloadAdvRefPO()
			{
				return wm_unload_adviceService.getUnloadAdvRefPO();
			}
			
			@GetMapping("/getUnloadAdvRefPOwt2/{bunit}/{supplier}/{itype}/{ptype}/{psubtype}/{orderdate}")
			public List<Wm_unload_adviceDTO> getUnloadAdvRefPOwt2(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "supplier") String supplier,
					@PathVariable(value = "itype") String itype,@PathVariable(value = "ptype") String ptype,@PathVariable(value = "psubtype") String psubtype,
					@PathVariable(value = "orderdate") String orderdate)
			{
				return wm_unload_adviceService.getUnloadAdvRefPOwt2(bunit,supplier,itype,ptype,psubtype,orderdate);
			}
			
			@GetMapping("/getUnloadAdvRefPOwt2FastAPI/{bunit}/{supplier}/{itype}/{ptype}/{psubtype}/{orderdate}")
			public List<Map<String, Object>> getUnloadAdvRefPOwt2FastAPI(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "supplier") String supplier,
					@PathVariable(value = "itype") String itype,@PathVariable(value = "ptype") String ptype,@PathVariable(value = "psubtype") String psubtype,
					@PathVariable(value = "orderdate") String orderdate)
			{
				return wm_unload_adviceService.getUnloadAdvRefPOwt2FastAPI(bunit,supplier,itype,ptype,psubtype,orderdate);
							
			}
			
			//
			@GetMapping("/getunloadstore/{bunit}/{supplier}/{itype}/{ptype}/{psubtype}/{orderdate}")
			public List<Wm_unload_adviceDTO> getunloadstore(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "supplier") String supplier,
					@PathVariable(value = "itype") String itype,@PathVariable(value = "ptype") String ptype,@PathVariable(value = "psubtype") String psubtype,
					@PathVariable(value = "orderdate") String orderdate)
			{
				return wm_unload_adviceService.getunloadstore(bunit,supplier,itype,ptype,psubtype,orderdate);
			}
			
			
			
			@GetMapping("/getUnloadAdvRefPOwt2Argnew/{bunit}/{supplier}/{itype}/{ptype}/{psubtype}/{orderdate}")
			public List<Wm_unload_adviceDTO> getUnloadAdvRefPOwt2Argnew(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "supplier") String supplier,
					@PathVariable(value = "itype") String itype,@PathVariable(value = "ptype") String ptype,@PathVariable(value = "psubtype") String psubtype,
					@PathVariable(value = "orderdate") String orderdate)
			{
				return wm_unload_adviceService.getUnloadAdvRefPOwt2Argnew(bunit,supplier,itype,ptype,psubtype,orderdate);
			}
			
			@GetMapping("/getUnloadAdvRefPOQc")
			public List<Wm_unload_adviceDTO> getUnloadAdvRefPOQc()
			{
				return wm_unload_adviceService.getUnloadAdvRefPOQc();
			}
			
			@GetMapping("/getUnloadAdvRefPOQcNew")
			public List<Map<String, Object>> getUnloadAdvRefPOQcNew()
			{
				return wm_unload_adviceService.getUnloadAdvRefPOQcNew();
			}
			
			@GetMapping("/getUnloadAdvVehiThruBU")
			public ResponseEntity<List<Wm_unload_adviceDTO>> getUnloadAdvVehiThruBU(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String orderdate)
			{
				List<Wm_unload_adviceDTO> vehiList = wm_unload_adviceService.getUnloadAdvVehiThruBU(bunit,orderdate);
				
				return new ResponseEntity<List<Wm_unload_adviceDTO>>(vehiList, new HttpHeaders(), HttpStatus.OK);
			}
			
			
			@GetMapping("/getUnloadAdviceThruVehicle/{vcode}/{weighment}")
			public List<Wm_unload_adviceDTO> getUnloadAdviceThruVehicle(@PathVariable(value = "vcode") String vcode,@PathVariable(value = "weighment") String weighment)
			{
				return wm_unload_adviceService.getUnloadAdviceThruVehicle(vcode,weighment);
			}
			
			@GetMapping("/getVehicleListWeighmentLocation/{location}")
			public List<Vehicle_weighment_load_unload> getVehicleListWeighmentLocation(@PathVariable(value = "location") String location)
			{
				return wm_unload_wgmntService.getVehicleListWeighmentLocation(location);
			}
			
			@GetMapping("/getUnloadAdviceThruVehiclefast/{vcode}/{weighment}")
			public List<Map<String, Object>> getUnloadAdviceThruVehiclefast(@PathVariable(value = "vcode") String vcode,@PathVariable(value = "weighment") String weighment)
			{
				return wm_unload_adviceService.getUnloadAdviceThruVehiclefast(vcode,weighment);
			}
			
			@GetMapping("/getVehicleLocationwiseWeighmentList/{location}")
			public List<Vehicle_weighment_load_unload> getVehicleLocationwiseWeighmentList(@PathVariable(value = "location") String location)
			{
				return wm_unload_wgmntService.getVehicleLocationwiseWeighmentList(location);
			}
			
			@GetMapping("/getGetDocuments/{unadv_id}")
			public List<Weighment_doc> getGetDocuments(@PathVariable(value = "unadv_id") String unadv_id)
			{
				return wm_unload_wgmntService.getGetDocuments(unadv_id);
			}
			
			
			/*@GetMapping("/getUnloadAdvSuppDate/{Vno}")
			public List<Unload_Adv_Supp_DateDTO> getUnloadAdvSuppDate(@PathVariable(value = "Vno") String Vno)
			{
				return wm_unload_adviceService.getUnloadAdvSuppDate(Vno);
			}*/
			
			/*********** End Unload Advice ******************/
			
			/*@GetMapping("/getLoadingVehiThruSales")
			public List<Wm_loading_adviceDTO> getLoadingVehiThruSales()
			{
				return wm_loading_adviceService.getLoadingVehiThruSales();
			}*/
			
			@GetMapping("/getLoadingVehiThruSales")
			public List<VehicleMaster> getLoadingVehiThruSales()
			{
				return wm_loading_adviceService.getLoadingVehiThruSales();
			}
			
			@GetMapping("/getLoadingVehiThruStkTransfer")
			public List<Wm_loading_adviceDTO> getLoadingVehiThruStkTransfer()
			{
				return wm_loading_adviceService.getLoadingVehiThruStkTransfer();
			}
			
			@GetMapping("/getLoadingVehiThruPR")
			public List<Wm_loading_adviceDTO> getLoadingVehiThruPR()
			{
				return wm_loading_adviceService.getLoadingVehiThruPR();
			}
			
			@GetMapping("/getLoadngAdviceThruVehicle/{vcode}/{weighment}")
			public List<Wm_loading_adviceDTO> getLoadngAdviceThruVehicle(@PathVariable(value = "vcode") String vcode,@PathVariable(value = "weighment") String weighment)
			{
				return wm_loading_adviceService.getLoadngAdviceThruVehicle(vcode,weighment);
			}
			
			@GetMapping("/getLoadngAdviceThruVehiclefast/{vcode}/{weighment}")
			public List<Map<String, Object>> getLoadngAdviceThruVehiclefast(@PathVariable(value = "vcode") String vcode,@PathVariable(value = "weighment") String weighment)
			{
				return wm_loading_adviceService.getLoadngAdviceThruVehiclefast(vcode,weighment);
			}
			
			@Autowired
			Wm_unload_wgmntService wm_unload_wgmntService;
			 
			@GetMapping("/getUnloadWeightment/{vcode}")
			public Wm_unload_wgmntDTO getUnloadWeightment(@PathVariable(value = "vcode") String vcode)
			{
				return wm_unload_wgmntService.getUnloadWeightment(vcode);
			}
			
			@GetMapping("/getUnloadWeightmentWt/{wgment_id}")
			public Wm_unload_wgmntDTO getUnloadWeightmentWt(@PathVariable(value = "wgment_id") String wgment_id)
			{
				return wm_unload_wgmntService.getUnloadWeightmentWt(wgment_id);
			}
			
			@GetMapping("/getUnloadWeightmentWtmultipopup/{wgment_id}")
			public Wm_unload_wgmntDTO getUnloadWeightmentWtmultipopup(@PathVariable(value = "wgment_id") String wgment_id)
			{
				return wm_unload_wgmntService.getUnloadWeightmentWtmultipopup(wgment_id);
			}
			
			@GetMapping("/checkweightunloadadvice/{pur_orderid}/{item_code}/{mat_wt}")
			public Status_tableDTO checkweightunloadadvice(@PathVariable(value = "pur_orderid") String pur_orderid,@PathVariable(value = "item_code") String item_code,@PathVariable(value = "mat_wt") Double mat_wt)
			{
				return wm_unload_adviceService.checkweightunloadadvice(pur_orderid,item_code,mat_wt);
			}
			
			//sales report list
			
			@Autowired
			Sales_registerService_Imp sales_registerService_Imp;
			@GetMapping("/getSalesreport")
			public List<Sales_register> getSalesreport()
			{
				return sales_registerService_Imp.findAll();
			}
			@GetMapping("/getSalesreport2")
			public List<Sales_register> getSalesreport2()
			{
				return sales_registerService_Imp.findAll();
			}
				
			@Autowired 
			Sales_register_detailsService sales_register_detailsService;
			@GetMapping("/getReportField/{sales_report}")
			public List<Sales_register_detailsDTO> getSalesRegisterdto(@PathVariable(value = "sales_report") String sales_report)
			{
				List<Sales_register_detailsDTO> salereportlist= new ArrayList<Sales_register_detailsDTO>();
				
				salereportlist.addAll(sales_register_detailsService.getSalesRegisterdto(sales_report));
				return salereportlist;
			}
			@Autowired 
			Sales_reg_masterService sales_reg_masterService;
			
			//@GetMapping("/getSalesRegisterList")
			@RequestMapping(value = "/getSalesRegisterList", method = RequestMethod.GET)
			public List<Sales_register_detailsDTO> getSalesRegisterList()
			{
				
				return sales_reg_masterService.getSalesRegisterList();
				
			}
			@GetMapping("/getReportNameList/{sales_report}")
			public List<Sales_register_detailsDTO> getReportNameList(@PathVariable(value = "sales_report") String sales_report)
			{
				
                List<Sales_register_detailsDTO> salereportlist= new ArrayList<Sales_register_detailsDTO>();
				
				salereportlist.addAll(sales_reg_masterService.getReportNameList(sales_report));
				return salereportlist;
				 
				
			}

			//purchase report list
			
			@Autowired
			Purchase_registerService_Imp purchase_registerService_Imp;
			@GetMapping("/getPurchasereport")
			public List<Purchase_register> getPurchasereport()
			{
				return purchase_registerService_Imp.findAll();
			}
		
			@Autowired
			Purchase_reg_masterService purchase_reg_masterService;
			@GetMapping("/getPurchaseRegisterList")
			//@RequestMapping(value = "/getSalesRegisterList", method = RequestMethod.GET)
			public List<Purchase_register_detailsDTO> getPurchaseRegisterList()
			{
				
				return purchase_reg_masterService.getPurchaseRegisterList();
				
			}
			
			@GetMapping("/getPurReportNameList/{purchase_report}")
			public List<Purchase_register_detailsDTO> getPurReportNameList(@PathVariable(value = "purchase_report") String purchase_report)
			{
                List<Purchase_register_detailsDTO> purreportlist= new ArrayList<Purchase_register_detailsDTO>();
                purreportlist.addAll(purchase_reg_masterService.getPurReportNameList(purchase_report));
				return purreportlist;
			}
			
			@Autowired
			Purchase_register_detailsService purchase_register_detailsService;
			
			@GetMapping("/getReportFieldPurchase/{purchase_report}")
			public List<Purchase_register_detailsDTO> getPurchaseRegisterdt(@PathVariable(value = "purchase_report") String purchase_report)
			{
				List<Purchase_register_detailsDTO> purchasereportlist= new ArrayList<Purchase_register_detailsDTO>();
				purchasereportlist.addAll(purchase_register_detailsService.getPurchaseRegisterdt(purchase_report));
				
				
				return purchasereportlist;
			}
			
			//Sales Report Dynamic List
			
			@GetMapping("/getSalesInvoicesr/{sales_report}/{reportname}")
			public List getSalesInvoices(
					@PathVariable(value = "sales_report") String sales_report,
					@PathVariable(value = "reportname") String reportname) {
				//System.out.print("hi");
				return sales_reg_masterService.getSalesInvoices(sales_report,reportname);
			}
			
			@GetMapping("/getSalesCol/{sales_report}/{reportname}")
			public List getSalesCol(@PathVariable(value = "sales_report") String sales_report,@PathVariable(value = "reportname") String reportname) {
				return sales_reg_masterService.getSalesCol(sales_report,reportname);
			}
			
			//Purchase Report Dynamic List
			
			
			
			@GetMapping("/getPurReportDynamic/{purchase_report}/{reportname}")
			public List getPurReportDynamic(@PathVariable(value = "purchase_report") String purchase_report,
					@PathVariable(value = "reportname") String reportname) {
				return purchase_reg_masterService.getPurReportDynamic(purchase_report, reportname);
			}
			
			@GetMapping("/getPurReportCol/{purchase_report}/{reportname}")
			public List getPurReportCol(@PathVariable(value = "purchase_report") String purchase_report,
					@PathVariable(value = "reportname") String reportname) {
				return purchase_reg_masterService.getPurReportCol(purchase_report, reportname);
			}
			
			// new sales dynamic list
			
			@Autowired
			Sales_reg_dynamicService sales_reg_dynamicService;
			
			//@GetMapping("/getSalesRegDynamicList")
			@RequestMapping(value = "/getSalesRegDynamicList", method = RequestMethod.GET)
			public List<sales_reg_dynamicDTO> getSalesRegDynamicList()
			{
				
				return sales_reg_dynamicService.getSalesRegDynamicList();
				
			}
			
			@GetMapping("/getSalesReportNameList")
			public List<sales_reg_dynamicDTO> getSalesReportNameList()
			{
				
				return sales_reg_dynamicService.getSalesReportNameList();
				
			}
			
			@GetMapping("/getSalesDynamicReportCol/{reportname}")
			public List getColumn(@PathVariable(value = "reportname") String reportname) {
				return sales_reg_dynamicService.getColumn(reportname);
			}
			
			@GetMapping("/getSalesDynamicProcedure/{reportname}/{fromdate}/{todate}")
			public List getRows(@PathVariable(value = "reportname") String reportname,@PathVariable(value = "fromdate") String fromdate,@PathVariable(value = "todate") String todate) {
				//String fromdate,String todate
				return sales_reg_dynamicService.getRows(reportname,fromdate,todate);
			}
			
			@GetMapping("/salesDynamicSort/{reportname}")
			public List<Sales_dynamic_sortDTO> salesDynamicSort(@PathVariable(value = "reportname") Long reportname)
			{
				
				return sales_reg_dynamicService.salesDynamicSort(reportname);
				
			}
			
			/*@GetMapping("/salessortde/{backend}")
			public List<Sales_dynamic_sort> salessortde(@PathVariable(value = "backend") String backend)
			{
				
				return sales_reg_dynamicService.salessortde(backend);
				
			}*/
			
			@Autowired
			Sales_inv_dynamic_sortingService sales_inv_dynamic_sortingService;
			
			@GetMapping("/getSalesSortDynList")
			public List<Sales_inv_dynamic_sortingDTO> getSalesSortDynList()
			{
				
				return sales_inv_dynamic_sortingService.getSalesSortDynList();
				
			}
			
			@GetMapping("/getSalesRegDuplicateCheck")
			public List<Sales_reg_dynamic> getSalesRegDuplicateCheck()
			{
				return sales_reg_dynamicService.getSalesRegDuplicateCheck();				
			}
			
			
			@GetMapping("/reportTypeDropdownList/{reporttype}")
			public List<sales_reg_dynamicDTO> reportTypeDropdownList(@PathVariable(value = "reporttype") String reporttype)
			{
				//System.out.println("listcontroller " + reporttype);
				return sales_reg_dynamicService.reportTypeDropdownList(reporttype);
			}
			
			@Autowired
			User_Role_AccessService user_Role_AccessService;
			
			//@GetMapping("/getRoleItemMaster/{role_access}")
			@RequestMapping(value = "/getRoleItemMaster/{role_access}", method = RequestMethod.GET)
			public List<User_Role_Access> getRoleItemMaster(@PathVariable(value = "role_access") String role_access)
			{
				//System.out.println("see role access/"+role_access);
				return user_Role_AccessService.getRoleItemMaster(role_access);
				
			}
			
			@Autowired
			UserService userService;
			
			@GetMapping("/getRolesNameList/{role}")
			public List<UserDTO> getRolesNameList(@PathVariable(value = "role") String role)
			{
				//System.out.println("see role access/"+role_access);
				return userService.getRolesNameList(role);
				
			}
			
			@GetMapping("/getUserEmailDuplicateCheck")
			public List<User> getUserEmailDuplicateCheck()
			{
				return userService.getUserEmailDuplicateCheck();				
			}
			
			@GetMapping("/getUserEmailPswdDuplicateCheck/{username}/{password}")
			public Boolean getUserEmailPswdDuplicateCheck(@PathVariable(value = "username") String username,@PathVariable(value = "password") String password)
			{
				//System.out.println("see role access/"+role_access);
				return userService.getUserEmailPswdDuplicateCheck(username,password);
				
			}
			
			@GetMapping("file/download/{fileName:.+}")
		    @ResponseBody
		    public ResponseEntity<Resource> getFileByName(@PathVariable String fileName) {
				System.out.println("fileName :: " + fileName);
		        Resource file = loadFile(fileName);
		        return ResponseEntity.ok()
		                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
		                .body(file);
		    }
			
			public Resource loadFile(String fileName) {
		        try{
		        	//Path location = Paths.get("D:/AayogAgroDocuments/SalesInvoice/");
		        	Path location = Paths.get("/usr/documents/salesinvoice/");   //Online Aayog
		            Path file = location.resolve(fileName);
		            System.out.println("file::"+file);
		            Resource resource = new UrlResource(file.toUri());
		            if(resource.exists() || resource.isReadable()) {
		                return  resource;
		            } else {
		                throw new RuntimeException("Failed");
		            }
		        } catch (MalformedURLException e) {
		            throw new RuntimeException("Failed");
		        }
		    }
			
			@GetMapping("file/downloadDC/{fileName:.+}")
		    @ResponseBody
		    public ResponseEntity<Resource> getFileByNameDC(@PathVariable String fileName) {
				System.out.println("fileName :: " + fileName);
		        Resource file = loadFileDC(fileName);
		        return ResponseEntity.ok()
		                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
		                .body(file);
		    }
			
			public Resource loadFileDC(String fileName) {
		        try{
		        	//Path location = Paths.get("D:/AayogAgroDocuments/deliverychallan/");
		        	Path location = Paths.get("/usr/ankitindiahajipur/documents/deliverychallan/");   //Online Aayog
		            Path file = location.resolve(fileName);
		            System.out.println("file::"+file);
		            Resource resource = new UrlResource(file.toUri());
		            if(resource.exists() || resource.isReadable()) {
		                return  resource;
		            } else {
		                throw new RuntimeException("Failed");
		            }
		        } catch (MalformedURLException e) {
		            throw new RuntimeException("Failed");
		        }
		    }
			
			
			
			
			
			@GetMapping("files/download/{fileName}/{pagename}")
		    @ResponseBody
		    public ResponseEntity<Resource> getFilesByName(@PathVariable (value = "fileName") String fileName,@PathVariable (value = "pagename") String pagename) {
				//System.out.println("fileName :: " + fileName);
		        Resource file = loadFiles(fileName,pagename);
		        return ResponseEntity.ok()
		                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
		                .body(file);
		    }
			
			
			public Resource loadFiles(String fileName,String pagename) {
		        try{
		        	String Filepath=null;
		        	if(pagename.compareToIgnoreCase("weighment")==0) 
		        	{
		        		//Filepath="D:/AayogAgroDocuments/Weighment";
		        		Filepath="/usr/documents/weighment";  	//Online Aayog
		        	}
		        	Path location = Paths.get(Filepath);
		            Path file = location.resolve(fileName);
		            Resource resource = new UrlResource(file.toUri());
		            if(resource.exists() || resource.isReadable()) {
		                return  resource;
		            } else {
		                throw new RuntimeException("Failed");
		            }
		        } catch (MalformedURLException e) {
		            throw new RuntimeException("Failed");
		        }
		    }
			
			
			@GetMapping("/getUnload_advice_updation/{orderid}/{subtype}")
			public List<Wm_unload_adviceDTO> getUnload_advice_updation(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "subtype") String subtype)
			{
				//System.out.println("orderid /"+orderid+"subtype /"+subtype);
				return wm_unload_adviceService.getUnload_advice_updation(orderid,subtype);
			}
			
			
			@GetMapping("/bro_supp_updation/{brokercode}")
			public List<Broker_master_vdrDTO> bro_supp_updation(@PathVariable(value = "brokercode") String brokercode)
			{
				//System.out.println("brokercode /"+brokercode);
				return broker_masterService.bro_supp_updation(brokercode);
			}
			
			@GetMapping("/supp_bro_updation/{suppliercode}")
			public List<Supp_bussiness_partner_brokerDTO> supp_bro_updation(@PathVariable(value = "suppliercode") String suppliercode)
			{
				//System.out.println("suppliercode /"+suppliercode);
				return supp_bussiness_partnerService.supp_bro_updation(suppliercode);
			}
			
			@GetMapping("/getUserRoleList")
			public List<User_Role_Access> getUserRoleList()
			{
				return user_Role_AccessService.findAll();
			}
			@GetMapping("/getUserroleAccessperrole/{role}")
			public User_Role_Access getUserroleAccessperrole(@PathVariable(value = "role") String role)
			{
				return user_Role_AccessService.getUserroleAccessperrole(role);
			}
			@GetMapping("/checkItemMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkItemMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO val=item_masterService.checkItemMasterUsage(code);
				return ResponseEntity.ok().body(val);
			}
			
			@GetMapping("/checkBrokerMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkBrokerMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=broker_masterService.checkBrokerMasterUsage(code);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/checkSupplierMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkSupplierMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=supp_bussiness_partnerService.checkSupplierMasterUsage(code);
				return ResponseEntity.ok().body(check);
			}
			
		    @GetMapping("/checkTransporterMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkTransporterMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=trans_bussiness_partner.checkTransporterMasterUsage(code);
				return ResponseEntity.ok().body(check);
			}
			
			/*@GetMapping("/checkVehicleMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkVehicleMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=vehicleMasterService.checkVehicleMasterUsage(code);
				return ResponseEntity.ok().body(check);
			}*/
			
			@GetMapping("/checkChargeMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkChargeMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=charge_MasterService.checkChargeMasterUsage(code);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/checkTdsMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkTdsMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=tds_masterService.checkTdsMasterUsage(code);
				return ResponseEntity.ok().body(check);
			}

			@GetMapping("/checkChennelCustomerMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkChennelCustomerMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=channel_customer_masterService.checkChennelCustomerMasterUsage(code);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/checkWmChgsMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkWmChgsMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=wm_charges_masterService.checkWmChgsMasterUsage(code);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/checkLoadingPointUsage/{code}")
			public ResponseEntity<StatusDTO> checkLoadingPointUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=loading_pointService.checkLoadingPointUsage(code);
				return ResponseEntity.ok().body(check);
			}
			
		
			
			/*@GetMapping("/checkMisleniousDeletation/{parent_id}/{parentModel}")
			public StatusDTO checkMisleniousDeletation(@PathVariable(value = "parent_id") String parent_id,@PathVariable(value = "parentModel") String parentModel)
			{
				//System.out.println("parent_id::"+parent_id+"parentModel::"+parentModel);
				Miscellaneous_Master_Deletation miscellaneous_Master_Deletation =new Miscellaneous_Master_Deletation();
				
				return miscellaneous_Master_Deletation.checkingmiscelaneous(parent_id, parentModel);
			}*/
			
			@GetMapping("/checkMisleniousDeletation/{parent_id}/{parentModel}")
			public ResponseEntity<StatusDTO> checkMisleniousDeletation(@PathVariable(value = "parent_id") String parent_id,@PathVariable(value = "parentModel") String parentModel)
			{
				StatusDTO check=companyBusinessUnitMasterService.checkMisleniousDeletation(parent_id, parentModel);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/checkZoneMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkZoneMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=zone_masterService.checkZoneMasterUsage(code);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/checkCustomerMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkCustomerMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=cust_bussiness_partnerService.checkCustomerMasterUsage(code);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/checkPurchaseOrderUsage/{code}")
			public ResponseEntity<StatusDTO> checkPurchaseOrderUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=pur_OrderService.checkPurchaseOrderUsage(code);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/checkTagAdvicePoUsage/{adviceno}")
			public ResponseEntity<StatusDTO> checkTagAdvicePoUsage(@PathVariable(value= "adviceno") String adviceno)
			{
				StatusDTO ckeckdto = tag_advice_with_poService.checkTagAdvicePoUsage(adviceno);
				return ResponseEntity.ok().body(ckeckdto);
			}
			
			@GetMapping("/checkTagAdvicePoUsageingrn/{pur_orderno}")
			public ResponseEntity<StatusDTO> checkTagAdvicePoUsageingrn(@PathVariable(value= "pur_orderno") String pur_orderno)
			{
				StatusDTO ckeckdto = tag_advice_with_poService.checkTagAdvicePoUsageingrn(pur_orderno);
				return ResponseEntity.ok().body(ckeckdto);
			}
		
			
			
			@GetMapping("/checkGRNUsage/{grnid}")
			public ResponseEntity<StatusDTO> checkGRNUsage(@PathVariable(value= "grnid") String grnid)
			{
				StatusDTO checkgrn = pur_good_receiptService.checkGRNUsage(grnid);
				return ResponseEntity.ok().body(checkgrn);
			}
			
			@GetMapping("/checkDeliveryChallanUsage/{delvid}")
			public ResponseEntity<StatusDTO> checkDeliveryChallanUsage(@PathVariable(value= "delvid") String delvid)
			{
				StatusDTO checkdelvchallan = delivery_challanService.checkDeliveryChallanUsage(delvid);
				return ResponseEntity.ok().body(checkdelvchallan);
			}
			@GetMapping("/getDeliverychallanlist/{salesorderid}")
			public List<Delivery_challan> getDeliverychallanlist(@PathVariable(value= "salesorderid") String salesorderid)
			{
				return delivery_challanService.getDeliverychallanlist(salesorderid);
			}
			
			@GetMapping("/getdeliverchallanitemlist/{deliverycharges}")
			public List<Delivery_challan_Item_Dtls> getdeliverchallanitemlist(@PathVariable(value= "deliverycharges") String deliverycharges)
			{
				return delivery_challanService.getdeliverchallanitemlist(deliverycharges);
			}
			
			@GetMapping("/getdeliverchallanjobitemlist/{deliverycharges}")
			public List<Map<String,Object>> getdeliverchallanjobitemlist(@PathVariable(value= "deliverycharges") String deliverycharges)
			{
				return delivery_challanService.getdeliverchallanjobitemlist(deliverycharges);
			}
			
			@GetMapping("/getdeliverchallanitemlistUpdate/{id}")
			public List<Delivery_challan_Item_Dtls> getdeliverchallanitemlistUpdate(@PathVariable(value= "id") Long id)
			{
				return delivery_challanService.getdeliverchallanitemlistUpdate(id);
			}
			
			@GetMapping("/getAccountPostingFor")
			public List<Ledgermaster> getAccountPostingFor(){
				return ledgermasterService.getAccountPostingFor();
			}
			
			@GetMapping("/salesReturnNoteUsage/{salesreturnnoteid}")
			public ResponseEntity<StatusDTO> salesReturnNoteUsage(@PathVariable(value= "salesreturnnoteid") String salesreturnnoteid)
			{
				//System.out.println("salesreturnnoteid::"+salesreturnnoteid);
				StatusDTO checkreturnnote = sales_return_noteService.salesReturnNoteUsage(salesreturnnoteid);
				return ResponseEntity.ok().body(checkreturnnote);
			}
			
			@GetMapping("/salesReturnApprovalNoteUsage/{salesreturnid}")
			public ResponseEntity<StatusDTO> salesReturnApprovalNoteUsage(@PathVariable(value= "salesreturnid") String salesreturnid)
			{
				//System.out.println("salesreturnid::"+salesreturnid);
				StatusDTO checkreturnapproval = return_approval_noteService.salesReturnApprovalNoteUsage(salesreturnid);
				return ResponseEntity.ok().body(checkreturnapproval);
			}
			
			@GetMapping("/checkShopFloorUsage/{shopfloorid}")
			public ResponseEntity<StatusDTO> checkShopFloorUsage(@PathVariable(value= "shopfloorid") String shopfloorid)
			{
				StatusDTO checkShopfloor = shop_floor_masterService.checkShopFloorUsage(shopfloorid);
				return ResponseEntity.ok().body(checkShopfloor);
			}
			
			@GetMapping("/checkPayTermUsage/{payterm_id}")
			public ResponseEntity<StatusDTO> checkPayTermUsage(@PathVariable(value= "payterm_id") String payterm_id)
			{
				StatusDTO checkPayTerm = acc_pay_term_masterService.checkPayTermUsage(payterm_id);
				return ResponseEntity.ok().body(checkPayTerm);
			}
			
			@GetMapping("/checkQualityCheckUsage/{qc_id}")
			public ResponseEntity<StatusDTO> checkQualityCheckUsage(@PathVariable(value= "qc_id") String qc_id)
			{
				StatusDTO checkQC = qc_rules_setupService.checkQualityCheckUsage(qc_id);
				return ResponseEntity.ok().body(checkQC);
			}
			
			
			@GetMapping("/getCompanyDetails/{compid}")
			public Company_master getCompanyDetails(@PathVariable(value= "compid") String compid)
			{
				return companyMasterService.getCompanyDetails(compid);
			}
			
			@GetMapping("/getCustomerDetails/{custid}")
			public Cust_bussiness_partner_address getCustomerDetails(@PathVariable(value= "custid") String custid)
			{
				return cust_bussiness_partnerService.getCustomerDetails(custid);
			}
			
			@GetMapping("/retriveTaxTypeGst/{taxcode}")
			public List<Gst_input_output_ledger_dtls> retriveTaxTypeGst(@PathVariable(value= "taxcode") String taxcode)
			{
				return taxTypeMasterService.retriveTaxTypeGst(taxcode);
			}
			
			@GetMapping("/checkVehicleNoWeighment/{veh_id}/{action}")
			public StatusDTO checkVehicleNoWeighment(@PathVariable(value= "veh_id") String veh_id,@PathVariable(value= "action") String action)
			{
				return wm_loading_adviceService.checkVehicleNoWeighment(veh_id,action);
			}
			
			@GetMapping("/bingroupList")
			public List<Bingroup> bingroupList()
			{
				return bingroupService.getBingrouplist();
			}
			
			@GetMapping("/getSuppBPStat/{code}")
			public Map<String, Object> getSuppBPStat(@PathVariable(value = "code") String code)
			{
				//System.out.println("bpid1::"+code);
				return supp_bussiness_partnerService.getSuppBPStat(code);
			}
			
			@GetMapping("/getCustBPStat/{code}")
			public Map<String, Object> getCustBPStat(@PathVariable(value = "code") String code)
			{
				//System.out.println("bpid1::"+code);
				return cust_bussiness_partnerService.getCustBPStat(code);
			}
			
			@GetMapping("/getReturnApprovalNoteList/{company}/{currentdate}")
			public List<Map<String, Object>> getReturnApprovalNoteList(@PathVariable(value = "company") String company,@PathVariable(value = "currentdate") String currentdate)
			{
				return return_approval_noteService.getReturnApprovalNoteList(company,currentdate);
			}
			
			/*@GetMapping("/searchReturnApprovalNote/{fromdate}/{todate}/{party1}")
			public List<Map<String, Object>> searchReturnApprovalNote(@PathVariable(value = "fromdate") String fromdate,
																	  @PathVariable(value = "todate") String todate,
																	  @PathVariable(value = "party1") String party1)
			{
				return return_approval_noteService.searchReturnApprovalNote(fromdate,todate,party1);
			}*/
			
			@GetMapping("/searchReturnApprovalNote/{fromdate}/{todate}")
			public List<Map<String, Object>> searchReturnApprovalNote(@PathVariable(value = "fromdate") String fromdate,
																	  @PathVariable(value = "todate") String todate)
			{
				return return_approval_noteService.searchReturnApprovalNote(fromdate,todate);
			}
			
			@GetMapping("/searchSalesReturnNote/{fromdate}/{todate}/{party1}")
			public List<Map<String, Object>> searchSalesReturnNote(@PathVariable(value = "fromdate") String fromdate,
																   @PathVariable(value = "todate") String todate,
																   @PathVariable(value = "party1") String party1)
			{
				return sales_return_noteService.searchSalesReturnNote(fromdate,todate,party1);
			}
			
			@GetMapping("/getCurrentDate")
			public DateDTO getCurrentDate()
			{
				LocalDateTime curDate = LocalDateTime.now();
				DateDTO res=new DateDTO();
				
				res.setCur_date(curDate);
				 
				return res;
			}
			
			@GetMapping("/getAlternativeItemList/{itemcode}")
			public List<Map<String, Object>> getAlternativeItemList(@PathVariable(value = "itemcode") String itemcode)
			{
				System.out.println("itemcode::"+itemcode);
				return item_masterService.getAlternativeItemList(itemcode);
			}
			
			@GetMapping("/getSalesOrdItemDtlsnew/{salesorderid}")
			public List<Map<String, Object>> getSalesOrdItemDtlsnew(@PathVariable(value = "salesorderid") String salesorderid)
			{
				return sales_orderService.getSalesOrdItemDtlsnew(salesorderid);
			}
			
			@GetMapping("/getItemAlternateprice/{itemid}/{alternateitemid}")
			public Map<String, Object> getItemAlternateprice(@PathVariable(value = "itemid") String itemid,@PathVariable(value = "alternateitemid") String alternateitemid)
			{
				return item_masterService.getItemAlternateprice(itemid,alternateitemid);
			}
			
			@GetMapping("/getLoadItemTotalWt/{loadid}")
			public Map<String, Object> getLoadItemTotalWt(@PathVariable(value = "loadid") String loadid)
			{
				return wm_loading_adviceService.getLoadItemTotalWt(loadid);
			}
			
			@GetMapping("/getTransportImage/{doc_img}")
		    public ResponseEntity<?> getTransportImage(@PathVariable(value = "doc_img")  String doc_img) {        
		        try {
		            
		        	
		        	//Path imagePath = Paths.get("D:/AayogAgroDocuments/SalesInvoice/"+doc_img);
		        	Path imagePath = Paths.get("/usr/documents/salesinvoice/"+doc_img); //Online Aayog
		            
		            if (imagePath != null) {
		           
		                
		                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(imagePath));
		                
		                return ResponseEntity
		                        .ok()
		                        .contentLength(imagePath.toFile().length())
		                        .contentType(MediaType.IMAGE_JPEG)
		                        .body(resource);                    
		            }
		            else 
		            {
		               
		                return ResponseEntity.status(HttpStatus.OK).build();
		            }
		        } catch (Exception e) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		        }
		    }
			
			@GetMapping("/getEinvoiceImage/{doc_img}")
		    public ResponseEntity<?> getEinvoiceImage(@PathVariable(value = "doc_img")  String doc_img) {        
		        try {
		            
		        	

		        	//Path imagePath = Paths.get("G:/Aayog_Einvoice/"+doc_img);

		        	Path imagePath = Paths.get("/usr/documents/aayogeinvoice/"+doc_img); //Online Aayog
		            
		            if (imagePath != null) {
		           
		                
		                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(imagePath));
		                
		                return ResponseEntity
		                        .ok()
		                        .contentLength(imagePath.toFile().length())
		                        .contentType(MediaType.IMAGE_JPEG)
		                        .body(resource);                    
		            }
		            else 
		            {
		               
		                return ResponseEntity.status(HttpStatus.OK).build();
		            }
		        } catch (Exception e) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		        }
		    }
			
			
			@GetMapping("/getdocumentListwithfileSalesInvoice/{doc_pdf}")
			public List<Map<String,Object>> getdocumentListwithfileSalesInvoice(@PathVariable(value = "doc_pdf") String doc_pdf)
			{
				return sales_InvoiceService.getdocumentListwithfileSalesInvoice(doc_pdf);
			}
			
			@DeleteMapping("/getdeletefileSalesInvoice/{id}")
			public ResponseEntity<Sales_Invoice_Docs> getdeletefileSalesInvoice(@PathVariable(value = "id") long id) {
				Sales_Invoice_Docs docpdf = sales_InvoiceService.findOneInvDoc(id);
				if (docpdf == null) {
					return ResponseEntity.notFound().build();
				} else {
					sales_InvoiceService.deleteSIDocument(docpdf);
					return ResponseEntity.ok().build();
				}
			}
			
			@GetMapping("/getdocumentListwithfileDelvChallan/{doc_pdf}")
			public List<Map<String,Object>> getdocumentListwithfileDelvChallan(@PathVariable(value = "doc_pdf") String doc_pdf)
			{
				return delivery_challanService.getdocumentListwithfileDelvChallan(doc_pdf);
			}
			
			@DeleteMapping("/getdeletefileDelvChallan/{id}")
			public ResponseEntity<Sales_Invoice_Docs> getdeletefileDelvChallan(@PathVariable(value = "id") long id) {
				Delivery_challan_Docs docpdf = delivery_challanService.findOneInvDoc(id);
				if (docpdf == null) {
					return ResponseEntity.notFound().build();
				} else {
					delivery_challanService.deleteSIDocument(docpdf);
					return ResponseEntity.ok().build();
				}
			}
			
			@GetMapping("/getTransportimage1/{refno}")
			public Map<String,Object> getTransportimage(@PathVariable(value = "refno") String refno)
			{
				return sales_InvoiceService.getTransportimage1(refno);
			}
			
			@GetMapping("/getDuplicateRefTransport")
			public List<Map<String,Object>> getDuplicateRefTransport()
			{
				return sales_transportService.getDuplicateRefTransport();
			}
			
			@GetMapping("/getServiceItemTax/{name}")
			public Map<String,Object> getServiceItemTax(@PathVariable(value = "name") String name)
			{
				return tax_code_masterService.getServiceItemTax(name);
			}
			
			@GetMapping("/getOtherItemMasterList/{company}")
			public List<Map<String, Object>> getOtherItemMasterList(@PathVariable(value = "company") String company)
			{
				return otherItemMasterService.getOtherItemMasterList(company);
			}
			
			@GetMapping("/getOtherPartyMasterList/{company}")
			public List<Map<String, Object>> getOtherPartyMasterList(@PathVariable(value = "company") String company)
			{
				return otherPartyMasterService.getOtherPartyMasterList(company);
			}
			
			@GetMapping("/getCustomerAddressDetails/{code}")
			public Map<String, Object> getCustomerAddressDetails(@PathVariable(value = "code") String code)
			{
				return cust_bussiness_partnerService.getCustomerAddressDetails(code);
			}
			
			@GetMapping("/checkValidGstNo/{code}")
			public ResponseEntity<StatusDTO> checkValidGstNo(@PathVariable(value = "code") String code)
			{
				StatusDTO check=supp_bussiness_partnerService.checkValidGstNo(code);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/checkValidPANNo/{code}")
			public ResponseEntity<StatusDTO> checkValidPANNo(@PathVariable(value = "code") String code)
			{
				StatusDTO check=supp_bussiness_partnerService.checkValidPANNo(code);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/getStoreChargeMasterDtlsList/{store_charges_id}")
			public List<Map<String, Object>> getStoreChargeMasterDtlsList(@PathVariable(value = "store_charges_id") String store_charges_id)
			{
				return store_inv_charge_masterService.getStoreChargeMasterDtlsList(store_charges_id);
			}
			
			@GetMapping("/checkStoreChargeMasterUsage/{code}")
			public ResponseEntity<StatusDTO> checkStoreChargeMasterUsage(@PathVariable(value = "code") String code)
			{
				StatusDTO check=store_inv_charge_masterService.checkStoreChargeMasterUsage(code);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/updateArnNo/{id}/{invoiceno}/{asnno}")
			public ResponseEntity<StatusDTO> updateArnNo(@PathVariable(value="id") Long id,
					@PathVariable(value = "invoiceno") String invoiceno,@PathVariable(value = "asnno") String asnno)
			{
				StatusDTO check=sales_InvoiceService.updateArnNo(id,invoiceno,asnno);
				return ResponseEntity.ok().body(check);
			}
			
			@GetMapping("/getMiscListFast")
			public List<Map<String,Object>> getMiscListFast()
			{
				return miscMasterService.getMiscListFast();
			}
			
			@GetMapping("/getUnloadAdvRefPOwt2ArgnewMultiItemGRN/{bunit}/{supplier}/{itype}/{ptype}/{psubtype}/{orderdate}")
			public List<Map<String,Object>> getUnloadAdvRefPOwt2ArgnewMultiItemGRN(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "supplier") String supplier,
					@PathVariable(value = "itype") String itype,@PathVariable(value = "ptype") String ptype,@PathVariable(value = "psubtype") String psubtype,
					@PathVariable(value = "orderdate") String orderdate)
			{
				return wm_unload_adviceService.getUnloadAdvRefPOwt2ArgnewMultiItemGRN(bunit,supplier,itype,ptype,psubtype,orderdate);
			}
			
			@GetMapping("/getItemPackUomNew/{code}/{code1}/{company}")
			public Map<String,Object> getItemPackUomNew(@PathVariable(value = "code") String code,@PathVariable(value = "code1") String code1,@PathVariable(value = "company") String company)
			{
				return item_masterService.getItemPackUomNew(code,code1,company);
			}
			
			@GetMapping("/getPurOrdItemDtlsMultipleItemGRN/{orderid}/{itemcode}")
			public List<Map<String,Object>> getPurOrdItemDtlsMultipleItemGRN(@PathVariable(value = "orderid") String orderid,@PathVariable(value = "itemcode") String itemcode)
			{
				return pur_OrderService.getPurOrdItemDtlsMultipleItemGRN(orderid,itemcode);
			}
			
			@GetMapping("/getUnloadWeightmentWtmultipopupmultipleItem/{wgment_id}")
			public List<Map<String,Object>> getUnloadWeightmentWtmultipopupmultipleItem(@PathVariable(value = "wgment_id") String wgment_id)
			{
				return wm_unload_wgmntService.getUnloadWeightmentWtmultipopupmultipleItem(wgment_id);
			}
			
			@GetMapping("/getUnloadItemDtls/{id}/{unadviceid}")
			public List<Map<String,Object>> getUnloadItemDtls(@PathVariable(value="id") Long id,
															  @PathVariable(value = "unadviceid") String unadviceid)
			{
				return wm_unload_adviceService.getUnloadItemDtls(id,unadviceid);
			}
			
			/*@GetMapping("/getGroupItemLedgerForJob/{group}")
			public List<Map<String,Object>> getGroupItemLedgerForJob(@PathVariable(value = "group") String group)
			{
				return item_group_masterService.getGroupItemLedgerForJob(group);
			}*/
		/*	@GetMapping("/getbingrouplistbyunit/{businessunit_id}/{finyear}")
			public List<Bingroup> getbingrouplistbyunit(@PathVariable(value= "businessunit_id") String businessunit_id,@PathVariable(value= "finyear") String finyear)
			{
				return bingroupService.getbingrouplistbyunit(businessunit_id,finyear);
			}*/
			
			/*@PostMapping("/getdeleteprocessfileSystem/{filename}/{fileid}")
			public void givenUsingJDK7nio2_whenDeletingAFile_thenCorrect(@PathVariable String fileName,@PathVariable String fileid) throws IOException {
				   

			    Path fileToDeletePath = Paths.get("fileid");
			    Files.delete(fileToDeletePath);
			  //FURTUR WORK  
			    process_master_docRepository.findpdf(fileid);
			    
			}*/
			
		/*	
			@GetMapping("/getPurOrdCNQUPList/{orderid}")
			public Wm_unload_wgmntDTO getPurOrdCNQUPList(@PathVariable(value = "orderid") String orderid)
			{
				return wm_unload_wgmntService.getPurOrdCNQUPList(orderid);
			}*/
}
