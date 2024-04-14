package com.AnkitIndia.jwtauthentication.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Acc_sub_group_master;
import com.AnkitIndia.jwtauthentication.model.Accounts_category_master;
import com.AnkitIndia.jwtauthentication.model.Accounts_group_master;
import com.AnkitIndia.jwtauthentication.model.Accounts_ledger_master;
import com.AnkitIndia.jwtauthentication.model.Accounts_type_master;

import com.AnkitIndia.jwtauthentication.model.Area_master;
import com.AnkitIndia.jwtauthentication.model.Bin_master;
import com.AnkitIndia.jwtauthentication.model.Bingroup;
import com.AnkitIndia.jwtauthentication.model.Broker_group;
import com.AnkitIndia.jwtauthentication.model.Broker_master;
import com.AnkitIndia.jwtauthentication.model.Channel_customer_master;
import com.AnkitIndia.jwtauthentication.model.Channel_partner;
import com.AnkitIndia.jwtauthentication.model.Charge_Master;
import com.AnkitIndia.jwtauthentication.model.City_master;
import com.AnkitIndia.jwtauthentication.model.Company_business_unit_master;
import com.AnkitIndia.jwtauthentication.model.Company_master;
import com.AnkitIndia.jwtauthentication.model.Country_master;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Cust_bussiness_partner_delv_to;
import com.AnkitIndia.jwtauthentication.model.Cust_group;
import com.AnkitIndia.jwtauthentication.model.Custom_uom_master;
import com.AnkitIndia.jwtauthentication.model.Department_master;
import com.AnkitIndia.jwtauthentication.model.Designation;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Employee_master;
import com.AnkitIndia.jwtauthentication.model.GatepassChecklist;
import com.AnkitIndia.jwtauthentication.model.GatepassGateout;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization;
import com.AnkitIndia.jwtauthentication.model.GatepassGateoutAuthorization_details;
import com.AnkitIndia.jwtauthentication.model.GatepassGetin;
import com.AnkitIndia.jwtauthentication.model.GodownMaster;
import com.AnkitIndia.jwtauthentication.model.Granulation;
import com.AnkitIndia.jwtauthentication.model.Granulation_Dtls;
import com.AnkitIndia.jwtauthentication.model.Gst_input_output_ledger_dtls;
import com.AnkitIndia.jwtauthentication.model.HubMaster;
import com.AnkitIndia.jwtauthentication.model.Invoice_type;
import com.AnkitIndia.jwtauthentication.model.Item_Service_master;
import com.AnkitIndia.jwtauthentication.model.Item_catagory_master;
import com.AnkitIndia.jwtauthentication.model.Item_group_master;
import com.AnkitIndia.jwtauthentication.model.Item_maintain;
import com.AnkitIndia.jwtauthentication.model.Item_master;
import com.AnkitIndia.jwtauthentication.model.Item_opening_stk;
import com.AnkitIndia.jwtauthentication.model.Item_rate_dtls;
import com.AnkitIndia.jwtauthentication.model.Item_stock;
import com.AnkitIndia.jwtauthentication.model.Item_type_master;
import com.AnkitIndia.jwtauthentication.model.JobOrder;
import com.AnkitIndia.jwtauthentication.model.JobWorkItemAllocation;
import com.AnkitIndia.jwtauthentication.model.Jw_grn_itemtag;
import com.AnkitIndia.jwtauthentication.model.Jw_grn_partywitem_details;
import com.AnkitIndia.jwtauthentication.model.Loading_point;
import com.AnkitIndia.jwtauthentication.model.Misc_master;
import com.AnkitIndia.jwtauthentication.model.Op_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.OtherItemMaster;
import com.AnkitIndia.jwtauthentication.model.OtherPartyMaster;
import com.AnkitIndia.jwtauthentication.model.Otherpartner_group;
import com.AnkitIndia.jwtauthentication.model.Post_office_master;
import com.AnkitIndia.jwtauthentication.model.Process_master;
import com.AnkitIndia.jwtauthentication.model.Pur_Bill;
import com.AnkitIndia.jwtauthentication.model.Purchase_reg_master;
import com.AnkitIndia.jwtauthentication.model.Purpose_master;
import com.AnkitIndia.jwtauthentication.model.Qc_rules_setup;
import com.AnkitIndia.jwtauthentication.model.Reason_master;
import com.AnkitIndia.jwtauthentication.model.Return_approval_note;
import com.AnkitIndia.jwtauthentication.model.Return_approval_item_dtls;
import com.AnkitIndia.jwtauthentication.model.Role;
import com.AnkitIndia.jwtauthentication.model.Sales_Invoice;
import com.AnkitIndia.jwtauthentication.model.Sales_inv_dynamic_sorting;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_dynamic;
import com.AnkitIndia.jwtauthentication.model.Screen_master;
import com.AnkitIndia.jwtauthentication.model.Seives_Dtls;
import com.AnkitIndia.jwtauthentication.model.Seives_master;
import com.AnkitIndia.jwtauthentication.model.Shop_floor_master;
import com.AnkitIndia.jwtauthentication.model.State_master;
import com.AnkitIndia.jwtauthentication.model.Store_inv_charge_master;
import com.AnkitIndia.jwtauthentication.model.Supp_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.Supplier_group;
import com.AnkitIndia.jwtauthentication.model.System_settings;
import com.AnkitIndia.jwtauthentication.model.Tax_type_master;
import com.AnkitIndia.jwtauthentication.model.Tax_code_master;
import com.AnkitIndia.jwtauthentication.model.Tds_master;
//import com.AnkitIndia.jwtauthentication.model.Testusers;
import com.AnkitIndia.jwtauthentication.model.Trans_bussiness_partner;
import com.AnkitIndia.jwtauthentication.model.TransportationChgsMatrixMaster;
import com.AnkitIndia.jwtauthentication.model.Transporter_group;
import com.AnkitIndia.jwtauthentication.model.User;
import com.AnkitIndia.jwtauthentication.model.User_Role_Access;
import com.AnkitIndia.jwtauthentication.model.User_roles;
import com.AnkitIndia.jwtauthentication.model.Userroles;
import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.model.VehicleTypeMaster;
import com.AnkitIndia.jwtauthentication.model.Vehicle_master_doc_details;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;
import com.AnkitIndia.jwtauthentication.model.Visitor_master;
import com.AnkitIndia.jwtauthentication.model.Warehouse_master;
import com.AnkitIndia.jwtauthentication.model.Wm_charges_master;
import com.AnkitIndia.jwtauthentication.model.Wm_loading_advice;
import com.AnkitIndia.jwtauthentication.model.Zone_master;
import com.AnkitIndia.jwtauthentication.repository.Accounts_group_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.VehicleMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Vehicle_master_doc_detailsRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Channel_partner_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Charge_MasterdtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitDetailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Company_licence_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partnerDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_accontDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_address_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_bill_addrDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_bill_addr_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_brokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_docDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_bussiness_partner_statutoryDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.EmployeeMasterDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.Employee_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Invoice_typeDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_jobwork_sales_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_jobwork_sales_return_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_pur_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_pur_retaccDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_sales_accDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_sales_retaccDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_stk_trans_purDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_group_master_stk_trans_saleDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_type_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Loading_pointDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_accontDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_addressDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_bill_addrDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_brokerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_delv_fromDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Op_bussiness_partner_statutoryDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Paging_SortingDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Post_office_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Pur_Order_Pagination_DTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Qc_rules_setupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Qc_rules_setup_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.ResponseDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_broker_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_docsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_item_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_noteDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_party_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_shipment_dtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Return_approval_weight_dtlDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SalesSequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Shop_floor_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Supp_bussiness_partnerDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Transportation_chgs_matrix_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_charges_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_unload_adviceDTO;
import com.AnkitIndia.jwtauthentication.security.services.Wm_charges_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Accounts_category_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Accounts_group_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Accounts_ledger_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Accounts_type_masterService;

import com.AnkitIndia.jwtauthentication.security.services.Area_masterService;
import com.AnkitIndia.jwtauthentication.security.services.BinMasterTest;
import com.AnkitIndia.jwtauthentication.security.services.BingroupService;
import com.AnkitIndia.jwtauthentication.security.services.Broker_groupService;
import com.AnkitIndia.jwtauthentication.security.services.Broker_masterService;
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
import com.AnkitIndia.jwtauthentication.security.services.DepartmentMasterService;
import com.AnkitIndia.jwtauthentication.security.services.DesignationService;
import com.AnkitIndia.jwtauthentication.security.services.District_masterService;
import com.AnkitIndia.jwtauthentication.security.services.EmployeeMasterService;
import com.AnkitIndia.jwtauthentication.security.services.GatepassChecklistService;
import com.AnkitIndia.jwtauthentication.security.services.GatepassGateoutAuthorizationService;
import com.AnkitIndia.jwtauthentication.security.services.GatepassGateoutService;
import com.AnkitIndia.jwtauthentication.security.services.GatepassGetinService;
import com.AnkitIndia.jwtauthentication.security.services.GodownMasterService;
import com.AnkitIndia.jwtauthentication.security.services.HubMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Invoice_typeService;
import com.AnkitIndia.jwtauthentication.security.services.Item_Service_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Item_catagory_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Item_group_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Item_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Item_opening_stkService;
import com.AnkitIndia.jwtauthentication.security.services.Item_stockService;
import com.AnkitIndia.jwtauthentication.security.services.Item_type_masterService;
import com.AnkitIndia.jwtauthentication.security.services.JobWorkItemAllocationService;
import com.AnkitIndia.jwtauthentication.security.services.Jw_grn_itemtagService;
import com.AnkitIndia.jwtauthentication.security.services.Loading_pointService;
import com.AnkitIndia.jwtauthentication.security.services.MiscMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Op_bussiness_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.OtherItemMasterService;
import com.AnkitIndia.jwtauthentication.security.services.OtherPartyMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Otherpartner_groupService;
import com.AnkitIndia.jwtauthentication.security.services.Post_office_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Purchase_reg_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Purpose_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Qc_rules_setupService;
import com.AnkitIndia.jwtauthentication.security.services.Reason_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Return_approval_noteService;
import com.AnkitIndia.jwtauthentication.security.services.RoleService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_inv_dynamic_sortingService;
import com.AnkitIndia.jwtauthentication.security.services.Sales_reg_dynamicService;
import com.AnkitIndia.jwtauthentication.security.services.Screen_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Seives_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Shop_floor_masterService;
import com.AnkitIndia.jwtauthentication.security.services.State_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Store_inv_charge_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Supp_bussiness_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.Supplier_groupService;
import com.AnkitIndia.jwtauthentication.security.services.System_settingsService;
import com.AnkitIndia.jwtauthentication.security.services.TaxTypeMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Tax_code_masterService;
import com.AnkitIndia.jwtauthentication.security.services.Tds_masterService;
//import com.AnkitIndia.jwtauthentication.security.services.TestusersService;
import com.AnkitIndia.jwtauthentication.security.services.Trans_bussiness_partnerService;
import com.AnkitIndia.jwtauthentication.security.services.TransportationChgsMatrixMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Transporter_groupService;
import com.AnkitIndia.jwtauthentication.security.services.UserService;
import com.AnkitIndia.jwtauthentication.security.services.User_Role_AccessService;
import com.AnkitIndia.jwtauthentication.security.services.VehicleMasterService;
import com.AnkitIndia.jwtauthentication.security.services.VehicleTypeMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Visitor_masterService;
import com.AnkitIndia.jwtauthentication.security.services.WarehouseMasterService;
import com.AnkitIndia.jwtauthentication.security.services.Zone_masterService;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Invoice_Return_approval_trans_infoDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_masterstatusDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Item_opening_stk_dtlsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Sales_InvoiceDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_brokerDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Supp_bussiness_partner_statutoryDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Tax_code_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Trans_bussiness_partner_brokerDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.AnkitIndia.jwtauthentication.security.services.Sales_reg_masterService;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_master;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
//@RequestMapping("/MeghnaBidi")
//@RequestMapping("/MeghanaPanMasala")
//@RequestMapping("/IndianSpices")
public class MasterController {
	
	
	@InitBinder
	public void customizeBinding(WebDataBinder binder) 
	{
		binder.setFieldMarkerPrefix(null);
	}
	
	@Value("${docPdf.upload-dir}")
	private String docPdf;
	
	/************* System_settings Start **********************/
	@Autowired
	System_settingsService system_settingsService;
	
	@PostMapping("/createSystemSettings")
	public System_settings createSystemSettings(@Valid @RequestBody System_settings settings) {
		return system_settingsService.save(settings);
	}
	
	@GetMapping("/getSystemSettings")
	public ResponseEntity<List<System_settings>> getSystemSettings(@RequestParam String company)
	{
		List<System_settings> sList= new ArrayList<System_settings>();
		sList.addAll(system_settingsService.getSystemSettings(company));
		
		return new ResponseEntity<List<System_settings>>(sList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/retriveSystemSettings/{id}")
	public ResponseEntity<System_settings> retriveSystemSettings(@PathVariable(value = "id") Long id) {
		System_settings op = system_settingsService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/getSystemSettingsByComp")
	public ResponseEntity<System_settings> getSystemSettingsByComp(@RequestParam(defaultValue = "") String comp) {
		System_settings op = system_settingsService.getSystemSettingsByComp(comp);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/updateSystemSettings/{id}")
	public ResponseEntity<System_settings> updateSystemSettings(@PathVariable(value = "id") Long id,
			@Valid @RequestBody System_settings settings) {
		System_settings op = system_settingsService.update(settings, id);
		return ResponseEntity.ok().body(op);
	}
	
	/************* System_settings End **********************/
	
	/************* Roles **********************/
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/createRole")
	public Role createRole(@Valid @RequestBody Role role) {
		return roleService.save(role);
	}
	
	@GetMapping("/getRoles")
	public ResponseEntity<List<Role>> getRoles()
	{
		List<Role> roles= new ArrayList<Role>();
		
		Role def=new Role(0L,"Choose an Option","0","0","0","0");
		roles.add(def);
		roles.addAll(roleService.getRoles());
		
		return new ResponseEntity<List<Role>>(roles, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getRoleList")
	public ResponseEntity<List<Role>> getRoleList()
	{
		List<Role> roles= new ArrayList<Role>();
		roles.addAll(roleService.getRoles());
		return new ResponseEntity<List<Role>>(roles, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getRolesThruUserId")
	public ResponseEntity<List<Role>> getRolesThruUserId(@RequestParam(defaultValue = "") String user)
	{
		List<Role> list = roleService.getRolesThruUserId(user);
		return new ResponseEntity<List<Role>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/updateUserRoles")
	public StatusDTO updateUserRoles(@Valid @RequestBody Userroles uRoles) {
		return roleService.updateUserRoles(uRoles);
	}
	
	@GetMapping("/getUserRoles")
	public ResponseEntity<User_roles> getUserRoles(@RequestParam(defaultValue = "") String user,@RequestParam(defaultValue = "") String role) 
	{
		User_roles val=roleService.getUserRoles(user,role);
		if(val==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getUsers()
	{
		List<User> list = userService.getUsers();
		return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	/***************** Channel Customer Master ********************/

	@Autowired
	Channel_customer_masterService channel_customer_masterService;

	@PostMapping("/createChannelCust")
	public Channel_customer_master save(@Valid @RequestBody Channel_customer_master cMaster) {
		return channel_customer_masterService.save(cMaster);
	}

	@GetMapping("/getChannelCust")
	public List<Channel_customer_master> findChannelAll() {
		return channel_customer_masterService.findAll();
	}
	
	@GetMapping("/getChannelCustForSales")
	public List<Map<String, Object>> getChannelCustForSales() {
		return channel_customer_masterService.getChannelCustForSales();
	}
	
	@GetMapping("/getChannelCustFastApi")
	public List<Map<String, Object>> getChannelCustFastApi() {
		return channel_customer_masterService.getChannelCustFastApi();
	}
	
	@GetMapping("/terminatechannel/{id}")
	public StatusDTO terminatechannel(@PathVariable(value = "id") long id) {
		return channel_customer_masterService.terminatechannel(id);
	}
	@GetMapping("/retriveChannelCust/{id}")
	public ResponseEntity<Channel_customer_master> retriveChannelCust(@PathVariable(value = "id") Long id) {
		Channel_customer_master op = channel_customer_masterService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateChannelCust/{id}")
	public ResponseEntity<Channel_customer_master> updateChannelCust(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Channel_customer_master channel_customer_master) {
		Channel_customer_master op = channel_customer_masterService.update(channel_customer_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteChannelCustomer/{id}")
	public ResponseEntity<Channel_customer_master> deleteChannel_customer(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Channel_customer_master channel_customer_master) {
		Channel_customer_master op = channel_customer_masterService.deleteChannel_customer(channel_customer_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findChannelCustomer")
	public ResponseEntity<List<Channel_customer_master>> findChannel_customer(@RequestParam(defaultValue = "") String searchtext) {
		List<Channel_customer_master> ccList= channel_customer_masterService.findChannel_customer(searchtext);
		if (ccList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(ccList);
		}
	}
	
	@GetMapping("/getChannelPartyList/{group_type}/{channeltype}")
	public List<Map<String, Object>> getChannelPartyList(@PathVariable("group_type") String group_type,
														 @PathVariable("channeltype") String channeltype)
	{
		return cust_bussiness_partnerService.getChannelPartyList(group_type,channeltype );
	}

	/*
	 * @GetMapping("/retriveItemMaster/{id}") public ResponseEntity<Item_master>
	 * retriveItemMaster(@PathVariable(value="id") Long id) { Item_master
	 * op=item_masterService.findOne(id); if(id==null) { return
	 * ResponseEntity.notFound().build(); } else { return
	 * ResponseEntity.ok().body(op); } }
	 */

	/***************** End Channel Customer Master ********************/

	/***************** Area Master Start ********************/
	@Autowired
	Area_masterService area_masterService;

	@PostMapping("/createAreaMaster")
	public Area_master createAreaMaster(@Valid @RequestBody Area_master area_master) {
		return area_masterService.save(area_master);
	}

	@GetMapping("/getAreaMaster")
	public List<Area_master> getAreaMaster() {
		return area_masterService.findAll();
	}
	
	@GetMapping("/getAreaList")
	public List<Area_master> getAreaList() {
		return area_masterService.findAll();
	}

	@GetMapping("/retriveAreaMaster/{id}")
	public ResponseEntity<Area_master> retriveAreaMaster(@PathVariable(value = "id") Long id) {
		Area_master op = area_masterService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateAreaMaster/{id}")
	public ResponseEntity<Area_master> updateAreaMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Area_master area_master) {
		Area_master op = area_masterService.update(area_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteAreaMaster/{id}")
	public ResponseEntity<Area_master> deleteAreaMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Area_master area_master) {
		Area_master op = area_masterService.deleteAreaMaster(area_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findAreas")
	public ResponseEntity<List<Area_master>> findAreas(@RequestParam(defaultValue = "") String searchtext) {
		List<Area_master> aList= area_masterService.findAreas(searchtext);
		if (aList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(aList);
		}
	}

	/***************** Area Master End ********************/
	
	/************************** Start Shop_floor_master **************************/
	@Autowired
	Shop_floor_masterService shop_floor_masterService;
	
	@GetMapping("/getSFSequenceId")
	public ResponseEntity<SequenceIdDTO> getSFSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = shop_floor_masterService.getSFSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/createShopFloor")
	public Shop_floor_master save(@Valid @RequestBody Shop_floor_master sFloor_master) {
		return shop_floor_masterService.save(sFloor_master);
	}
	
	@GetMapping("/findAllShopFloor")
	public List<Shop_floor_master> findAllShopFloor() {
		return shop_floor_masterService.findAllShopFloor();
	}
	
	@GetMapping("/getShopFloors")
	public List<Shop_floor_masterDTO> getShopFloors() {
		return shop_floor_masterService.getShopFloors();
	}
	
	@GetMapping("/getShopFloorThruBU/{bunit}")
	public List<Shop_floor_masterDTO> getShopFloorThruBU(@PathVariable(value = "bunit") String bunit) {
		return shop_floor_masterService.getShopFloorThruBU(bunit);
	}
	
	
	@GetMapping("/getShopFloorThruBUregular/{bunit}")
	public  List<Map<String,Object>> getShopFloorThruBUregular(@PathVariable(value = "bunit") String bunit) {
		return shop_floor_masterService.getShopFloorThruBUregular(bunit);
	}
	
	@GetMapping("/getShopFloorspecialThruBU/{bunit}")
	public  List<Map<String,Object>> getShopFloorspecialThruBU(@PathVariable(value = "bunit") String bunit) {
		return shop_floor_masterService.getShopFloorspecialThruBU(bunit);
	}
	
	@GetMapping("/retriveShopFloor/{id}")
	public ResponseEntity<Shop_floor_master> retriveShopFloor(@PathVariable(value = "id") Long id) {
		Shop_floor_master op = shop_floor_masterService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/updateShopFloor/{id}")
	public ResponseEntity<Shop_floor_master> updateShopFloor(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Shop_floor_master sFloor_master) {
		Shop_floor_master op = shop_floor_masterService.update(sFloor_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	
	@PutMapping("/deleteShopFloor/{id}")
	public ResponseEntity<Shop_floor_master> deleteShop_floor(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Shop_floor_master sFloor_master) {
		Shop_floor_master op = shop_floor_masterService.deleteShop_floor(sFloor_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	
	@GetMapping("/findShopFloor")
	public ResponseEntity<List<Shop_floor_master>> findShop_floor(@RequestParam(defaultValue = "") String searchtext) {
		List<Shop_floor_master> shopList= shop_floor_masterService.findShop_floor(searchtext);
		if (shopList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(shopList);
		}
	}
	
	
	/************************** End Shop_floor_master **************************/

	/***************** Loading point Master ********************/

	@Autowired
	Loading_pointService loading_pointService;
	
	@GetMapping("/getLoadingPointSequenceId")
	public ResponseEntity<SequenceIdDTO> getLoadingPointSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = loading_pointService.getLoadingPointSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createLoadingPoint")
	public Loading_point save(@Valid @RequestBody Loading_point loading_point) {
		return loading_pointService.save(loading_point);
	}

	@GetMapping("/getLoadingPoints")
	public List<Loading_point> findLoadingPointAll() {
		return loading_pointService.findAll();
	}

	@GetMapping("/getLoadingPointThruBU/{bunit}")
	public List<Loading_pointDTO> getLoadingPointThruBU(@PathVariable(value = "bunit") String bunit) {
		return loading_pointService.getLoadingPointThruBU(bunit);
	}
	
	@GetMapping("/getLoadingPointThruBUDiff/{bunit}/{lpoint}")
	public List<Loading_pointDTO> getLoadingPointThruBUDiff(@PathVariable(value = "bunit") String bunit,@PathVariable(value = "lpoint") String lpoint) {
		return loading_pointService.getLoadingPointThruBUDiff(bunit,lpoint);
	}

	@GetMapping("/retriveLoadingPoints/{id}")
	public ResponseEntity<Loading_point> retriveLoadingPoints(@PathVariable(value = "id") Long id) {
		Loading_point op = loading_pointService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateLoadingPoint/{id}")
	public ResponseEntity<Loading_point> updateLoadingPoint(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Loading_point loading_point) {
		Loading_point op = loading_pointService.update(loading_point, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteLoadingPoint/{id}")
	public ResponseEntity<Loading_point> deleteLoading_point(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Loading_point loading_point) {
		Loading_point op = loading_pointService.deleteLoading_point(loading_point, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findLoadingPoint")
	public ResponseEntity<List<Loading_point>> findLoading_point(@RequestParam(defaultValue = "") String searchtext) {
		List<Loading_point> loadList= loading_pointService.findLoading_point(searchtext);
		if (loadList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(loadList);
		}
	}
	/***************** End Loading Point Master ********************/

	/***************** ZONE MASTER ********************/

	@Autowired
	Zone_masterService zone_masterService;

	@PostMapping("/createZoneMaster")
	public Zone_master save(@Valid @RequestBody Zone_master zone_master) {
		return zone_masterService.save(zone_master);
	}

	@GetMapping("/getZoneMaster")
	public List<Zone_master> findAll() {
		return zone_masterService.findAll();
	}

	@GetMapping("/retriveZoneMaster/{id}")
	public ResponseEntity<Zone_master> getZoneMaster(@PathVariable(value = "id") Long id) {
		Zone_master op = zone_masterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/deleteZoneMaster/{id}")
	public ResponseEntity<Zone_master> deleteZone(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Zone_master zone_master) {
		Zone_master op = zone_masterService.deleteZone(zone_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/updateZoneMaster/{id}")
	public ResponseEntity<Zone_master> updateZone(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Zone_master zone_master) {
		Zone_master op = zone_masterService.updateZone(zone_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findZoneMaster")
	public ResponseEntity<List<Zone_master>> findZone(@RequestParam(defaultValue = "") String searchtext) {
		List<Zone_master> zoneList= zone_masterService.findZone(searchtext);
		if (zoneList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(zoneList);
		}
	}
	
	/***************** Purpose Master *******************/

	@Autowired
	Purpose_masterService purpose_masterService;

	@PostMapping("/createPurpose")
	public Purpose_master save(@Valid @RequestBody Purpose_master purpose_master) {
		return purpose_masterService.save(purpose_master);
	}

	@GetMapping("/getPurpose")
	public List<Purpose_master> findZoneAll() {
		return purpose_masterService.findAll();
	}

	@GetMapping("/retrivePurpose/{id}")
	public ResponseEntity<Purpose_master> getPurposeMaster(@PathVariable(value = "id") Long id) {
		Purpose_master op = purpose_masterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updatePurpose/{id}")
	public ResponseEntity<Purpose_master> updatePurpose(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Purpose_master purpose_master) {
		Purpose_master op = purpose_masterService.update(purpose_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deletePurpose/{id}")
	public ResponseEntity<Purpose_master> deletePurpose(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Purpose_master purpose_master) {
		Purpose_master op = purpose_masterService.deletePurpose(purpose_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findPurpose")
	public ResponseEntity<List<Purpose_master>> findPurpose(@RequestParam(defaultValue = "") String searchtext) {
		List<Purpose_master> purList= purpose_masterService.findPurpose(searchtext);
		if (purList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(purList);
		}
	}

	/******************* Designation Master ******************/

	@Autowired
	DesignationService designationService;

	@PostMapping("/createDesignation")
	public ResponseEntity<Designation> save(@Valid @RequestBody Designation designation) {
		designationService.save(designation);

		return new ResponseEntity<Designation>(designation, HttpStatus.OK);
	}

	@GetMapping("/getDesignations")
	public List<Designation> getDesignationAll() {
		return designationService.findAll();
	}

	@GetMapping("/retriveDesignation/{id}")
	public ResponseEntity<Designation> getDesignationMaster(@PathVariable(value = "id") Long id) {
		Designation op = designationService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateDesignatione/{id}")
	public ResponseEntity<Designation> updateDesignatione(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Designation designation) {
		Designation op = designationService.update(designation, id);
		return ResponseEntity.ok().body(op);
	}
 
	@PutMapping("/deleteDesignation/{id}")
	public ResponseEntity<Designation> deleteDesignation(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Designation designation) {
		Designation op = designationService.deleteDesignation(designation, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findDesignation")
	public ResponseEntity<List<Designation>> findDesignation(@RequestParam(defaultValue = "") String searchtext) {
		List<Designation> designList= designationService.findDesignation(searchtext);
		if (designList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(designList);
		}
	}
	
	/******************* transportationChgsMatrix Master Start *******************/
	@Autowired
	TransportationChgsMatrixMasterService transportationChgsMatrixMasterService;
	
	@GetMapping("/getTCMId")
	public ResponseEntity<SequenceIdDTO> getTCMId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = transportationChgsMatrixMasterService.getTCMId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/createTransChgsMatrix")
	public ResponseEntity<TransportationChgsMatrixMaster> save(
			@Valid @RequestBody TransportationChgsMatrixMaster transportationChgsMatrixMaster) {
		transportationChgsMatrixMasterService.save(transportationChgsMatrixMaster);

		return new ResponseEntity<TransportationChgsMatrixMaster>(transportationChgsMatrixMaster, HttpStatus.OK);
	}

	@GetMapping("/getTransChgsMatrixs")
	public List<TransportationChgsMatrixMaster> findTransportationChgsMatrixMasterAll() {

		return transportationChgsMatrixMasterService.findAll();
	}

	@GetMapping("/retriveTransChgsMatrix/{id}")
	public ResponseEntity<TransportationChgsMatrixMaster> getTransportationChgsMatrixMaster(
			@PathVariable(value = "id") Long id) {
		TransportationChgsMatrixMaster op = transportationChgsMatrixMasterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@GetMapping("/transChrgsMatRetriveList/{t_id}")
	public List<Transportation_chgs_matrix_detailsDTO> transChrgsMatRetriveList(
			@PathVariable(value = "t_id") String t_id) {
		return transportationChgsMatrixMasterService.transChrgsMatRetriveList(t_id);
	}

	@PutMapping("/updateTransChgsMatrix/{id}")
	public ResponseEntity<TransportationChgsMatrixMaster> updateTransportationChgsMatrixMaster(
			@PathVariable(value = "id") Long id,
			@Valid @RequestBody TransportationChgsMatrixMaster transportationChgsMatrixMaster) {
		TransportationChgsMatrixMaster op = transportationChgsMatrixMasterService.update(transportationChgsMatrixMaster,
				id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteTransChgsMatrix/{id}")
	public ResponseEntity<TransportationChgsMatrixMaster> deleteTransChgsMatrix(@PathVariable(value = "id") Long id,
			@Valid @RequestBody TransportationChgsMatrixMaster transportationChgsMatrixMaster) {
		TransportationChgsMatrixMaster op = transportationChgsMatrixMasterService.deleteTCMM(transportationChgsMatrixMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findTransChgsMatrix")
	public ResponseEntity<List<TransportationChgsMatrixMaster>> findTransChgsMatrix(@RequestParam(defaultValue = "") String searchtext) {
		List<TransportationChgsMatrixMaster> taxList= transportationChgsMatrixMasterService.findTransportationChgsMatrixMaster(searchtext);
		if (taxList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(taxList);
		}
	}

	/******************* transportationChgsMatrix Master End *******************/

	/******************* Tax Type Master Start *******************/
	@Autowired
	TaxTypeMasterService taxTypeMasterService;

	@PostMapping("/createTaxType")
	public Tax_type_master save(@Valid @RequestBody Tax_type_master taxTypeMaster) {
		return taxTypeMasterService.save(taxTypeMaster);
	}

	@GetMapping("/getgstdetailsoftaxtype/{code}")
	public Gst_input_output_ledger_dtls getgstdetailsoftaxtype(@PathVariable(value = "code") String code) {
		return taxTypeMasterService.getgstdetailsoftaxtype(code);
	}
	
	@GetMapping("/getTaxTypeSequenceId")
	public SequenceIdDTO getTaxTypeSequenceId() {
		return taxTypeMasterService.getTaxTypeSequenceId(); 
	}
	
	@GetMapping("/getTaxTypes")
	public List<Tax_type_master> findTaxTypeMasterAll() {
		return taxTypeMasterService.findAll();
	}

	@GetMapping("/retriveTaxType/{id}")
	public ResponseEntity<Tax_type_master> getTaxTypeMaster(@PathVariable(value = "id") Long id) {
		Tax_type_master op = taxTypeMasterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateTaxType/{id}")
	public ResponseEntity<Tax_type_master> updateTaxTypeMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Tax_type_master taxTypeMaster) {
		Tax_type_master op = taxTypeMasterService.update(taxTypeMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteTaxType/{id}")
	public ResponseEntity<Tax_type_master> deleteTaxType(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Tax_type_master taxTypeMaster) {
		Tax_type_master op = taxTypeMasterService.deleteTax_type(taxTypeMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findTaxType")
	public ResponseEntity<List<Tax_type_master>> findTaxType(@RequestParam(defaultValue = "") String searchtext) {
		List<Tax_type_master> taxList= taxTypeMasterService.findTax_type(searchtext);
		if (taxList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(taxList);
		}
	}
	
	/******************* Tax Type Master End *******************/

	/******************* Misc Master Start *******************/
	@Autowired
	MiscMasterService miscMasterService;

	@PostMapping("/createMisc")
	public Misc_master save(@Valid @RequestBody Misc_master miscMaster) {
		return miscMasterService.save(miscMaster);
	}

	@GetMapping("/getMiscs")
	public List<Misc_master> findMiscMasterAll() {
		return miscMasterService.findAll();
	}

	@GetMapping("/retriveMisc/{id}")
	public ResponseEntity<Misc_master> getMiscMaster(@PathVariable(value = "id") Long id) {
		Misc_master op = miscMasterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateMisc/{id}")
	public ResponseEntity<Misc_master> updateMaterialTypeMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Misc_master miscMaster) {
		Misc_master op = miscMasterService.update(miscMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteMisc/{id}")
	public ResponseEntity<Misc_master> deleteMisc_master(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Misc_master miscMaster) {
		Misc_master op = miscMasterService.deleteMisc_master(miscMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findMisc")
	public ResponseEntity<List<Misc_master>> findMisc_master(@RequestParam(defaultValue = "") String searchtext) {
		List<Misc_master> miscList= miscMasterService.findMisc_master(searchtext);
		if (miscList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(miscList);
		}
	}
	
	/******************* Misc Master End *******************/

	/******************* Tax code *************************/
	@Autowired
	Tax_code_masterService tax_code_masterService;
	
	@GetMapping("/getTaxCodeSequenceId")
	public ResponseEntity<SequenceIdDTO> getTaxCodeSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = tax_code_masterService.getTaxCodeSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createTaxCode")
	public Tax_code_master save(@Valid @RequestBody Tax_code_master tax_code_master) {
		return tax_code_masterService.save(tax_code_master);
	}

	
	
	
	@GetMapping("/getTaxCode")
	public List<Tax_code_master> getTaxCode() {
		return tax_code_masterService.findAll();
	}

	@GetMapping("/retriveTaxCode/{id}")
	public ResponseEntity<Tax_code_master> getTaxCodeMaster(@PathVariable(value = "id") Long id) {
		Tax_code_master op = tax_code_masterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@GetMapping("/taxCodeDtlsRetriveList/{t_id}")
	public List<Tax_code_detailsDTO> taxCodeDtlsRetriveList(@PathVariable(value = "t_id") String t_id) {
		return tax_code_masterService.taxCodeDtlsRetriveList(t_id);
	}
	
	@GetMapping("/getTaxCodeDetails/{t_id}")
	public Tax_code_detailsDTO getTaxCodeDetails(@PathVariable(value = "t_id") String t_id) {
		return tax_code_masterService.getTaxCodeDetails(t_id);
	}
	
	
	@GetMapping("/getTaxCodeDetailsname")
	//@GetMapping("/getTaxCodeDetailsname/{tax_name}")
	public Tax_code_detailsDTO getTaxCodeDetailsname(@RequestParam(defaultValue = "") String tax_name) {
		//public Tax_code_detailsDTO getTaxCodeDetailsname(@PathVariable(value = "tax_name") String tax_name) {
		return tax_code_masterService.getTaxCodeDetailsname(tax_name);
	}
	

	@PutMapping("/updateTaxCode/{id}")
	public ResponseEntity<Tax_code_master> updateTaxCode(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Tax_code_master tax_code_master) {
		Tax_code_master op = tax_code_masterService.update(tax_code_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findTaxCode")
	public ResponseEntity<List<Tax_code_master>> findTaxCode(@RequestParam(defaultValue = "") String searchtext) {
		List<Tax_code_master> tList= tax_code_masterService.findTaxCode(searchtext);
		if (tList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(tList);
		}
	}
	
	@PutMapping("/deleteTaxCode/{id}")
	public ResponseEntity<Tax_code_master> deleteTaxCode(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Tax_code_master tax_code_master) {
		Tax_code_master op = tax_code_masterService.deleteTaxCode(tax_code_master, id);
		return ResponseEntity.ok().body(op);
	}

	/******************* Custom Uom Master Start *******************/
	@Autowired
	CustomUomMasterService customUomMasterService;
	
	@GetMapping("/getCustomUOMSequenceId")
	public ResponseEntity<SequenceIdDTO> getCustomUOMSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = customUomMasterService.getCustomUOMSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createCustomUom")
	public Custom_uom_master save(@Valid @RequestBody Custom_uom_master customUomMaster) {
		return customUomMasterService.save(customUomMaster);
	}

	@GetMapping("/getCustomUoms")
	public List<Custom_uom_master> getCustommUnoAll() {
		return customUomMasterService.findAll();
	}

	@GetMapping("/retriveCustomerUom/{id}")
	public ResponseEntity<Custom_uom_master> getCoustometUnoById(@PathVariable(value = "id") Long id) {
		Custom_uom_master op = customUomMasterService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateCustomUom/{id}")
	public ResponseEntity<Custom_uom_master> updateCustomUom(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Custom_uom_master customUomMaster) {
		Custom_uom_master op = customUomMasterService.update(customUomMaster, id);
		return ResponseEntity.ok().body(op);

	}
	
	@PutMapping("/deleteCustomUom/{id}")
	public ResponseEntity<Custom_uom_master> deleteCustom_uom(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Custom_uom_master customUomMaster) {
		Custom_uom_master op = customUomMasterService.deleteCustom_uom(customUomMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findCustomUom")
	public ResponseEntity<List<Custom_uom_master>> findCustom_uom(@RequestParam(defaultValue = "") String searchtext) {
		List<Custom_uom_master> custList= customUomMasterService.findCustom_uom(searchtext);
		if (custList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(custList);
		}
	}
	
	@GetMapping("/exportuom/{id}")
	public ResponseEntity<Custom_uom_master> exportuom(@PathVariable(value = "id") Long id) {
		Custom_uom_master op = customUomMasterService.exportuom(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	/******************* Custom Uom Master End *******************/

	/******************* Bin Master Start *******************/
	@Autowired
	// BinMasterService binMasterService;
	BinMasterTest binMasterTest;
	
	@GetMapping("/getBinSequenceId")
	public ResponseEntity<SequenceIdDTO> getBinSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = binMasterTest.getBinSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	/* to save a Bin */
	@PostMapping("/createBin")
	public Bin_master createBin(@Valid @RequestBody Bin_master bin) {
		return binMasterTest.save(bin);
	}

	/* get all Bin */
	@GetMapping("/getBin")
	public List<Bin_master> getBinAll() {
		return binMasterTest.findAll();
	}

	@GetMapping("/retriveBin/{id}")
	public ResponseEntity<Bin_master> getBinById(@PathVariable(value = "id") Long id) {
		Bin_master bin = binMasterTest.findOne(id);

		if (bin == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(bin);
		}

	}

	/* update */

	@PutMapping("/updateBin/{id}")
	public ResponseEntity<Bin_master> updateBin(@PathVariable(value = "id") long id,
			@Valid @RequestBody Bin_master bin) {
		Bin_master updateBin = binMasterTest.update(bin, id);
		return ResponseEntity.ok().body(updateBin);
	}

	/*
	 * @DeleteMapping("/deleteBin/{id}") public ResponseEntity<Bin_master>
	 * deleteBin(@PathVariable(value="id") long id) { Bin_master
	 * bin=binMasterTest.findOne(id); if(bin == null) { return
	 * ResponseEntity.notFound().build(); } else { binMasterTest.delete(bin); return
	 * ResponseEntity.ok().build(); } }
	 */
	
	@PutMapping("/deleteBin/{id}")
	public ResponseEntity<Bin_master> deleteBin(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Bin_master bin) {
		Bin_master op = binMasterTest.deleteBin(bin, id);
		return ResponseEntity.ok().body(op);
	}

	@GetMapping("/findBin")
	public ResponseEntity<List<Bin_master>> findBin(@RequestParam(defaultValue = "") String searchtext) {
		List<Bin_master> binList= binMasterTest.findBin_master(searchtext);
		if (binList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(binList);
		}
	}
	/******************* Bin Master End *******************/

	/******************* Company Master Start *******************/

	@Autowired
	CompanyMasterService companyMasterService;
	
	@GetMapping("/getCompanySequenceId")
	public ResponseEntity<SequenceIdDTO> getCompanySequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = companyMasterService.getCompanySequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createCompany")
	public Company_master save(@Valid @RequestBody Company_master company_master) {
		return companyMasterService.save(company_master);
	}

	@GetMapping("/getCompanies")
	public List<Company_master> findCompanyAll() {
		return companyMasterService.findAll();
	}

	@GetMapping("/retriveCompany/{id}")
	public ResponseEntity<Company_master> getCompanybyId(@PathVariable(value = "id") Long id) {
		Company_master op = companyMasterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@GetMapping("/compLiceRetriveList/{cp_id}")
	public List<Company_licence_detailsDTO> compLiceRetriveList(@PathVariable(value = "cp_id") String cp_id) {
		return companyMasterService.compLiceRetriveList(cp_id);
	}

	@PutMapping("/updateCompany/{id}")
	public ResponseEntity<Company_master> updateCompany(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Company_master companyMaster) {
		Company_master op = companyMasterService.update(companyMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteCompany/{id}")
	public ResponseEntity<Company_master> deleteCompany(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Company_master company_master) {
		Company_master op = companyMasterService.deleteCompany(company_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findCompanys")
	public ResponseEntity<List<Company_master>> findCompanys(@RequestParam(defaultValue = "") String searchtext) {
		List<Company_master> cList= companyMasterService.findCompanys(searchtext);
		if (cList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(cList);
		}
	}
	/******************* Company Master End *******************/

	/******************* Company Business Unit Master Start *******************/
	@Autowired
	CompanyBusinessUnitMasterService companyBusinessUnitMasterService;
	
	@GetMapping("/getCompanyBusiSequenceId")
	public ResponseEntity<SequenceIdDTO> getCompanyBusiSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = companyBusinessUnitMasterService.getCompanyBusiSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createCompanyBusiness")
	public Company_business_unit_master save(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException{
		Company_business_unit_master company_business_unit_master=new ObjectMapper().readValue(input, Company_business_unit_master.class);
		return companyBusinessUnitMasterService.save(company_business_unit_master,files);
	}

	@GetMapping("/getCompanyBusiness")
	public List<Company_business_unit_master> findCompanyBusinessAll() {
		return companyBusinessUnitMasterService.findAll();
	}

	@GetMapping("/retriveCompanyBusiness/{id}")

	public ResponseEntity<Company_business_unit_master> getCompanyBusinessUnitMasterById(
			@PathVariable(value = "id") Long id) {
		Company_business_unit_master op = companyBusinessUnitMasterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@GetMapping("/compBURetriveList/{b_id}")
	public List<CompanyBusinessUnitDetailsDTO> compBURetriveList(@PathVariable(value = "b_id") String b_id) {
		return companyBusinessUnitMasterService.compBURetriveList(b_id);
	}

	@PutMapping("/updateCompanyBusiness")
	public ResponseEntity<Company_business_unit_master> updateCompanyBusiness(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException{
		Company_business_unit_master cUnit_master=new ObjectMapper().readValue(input, Company_business_unit_master.class);
		Company_business_unit_master ob = companyBusinessUnitMasterService.update(cUnit_master,files);
		return ResponseEntity.ok().body(ob);
	}
	
	@PutMapping("/deleteCompanyBUMaster/{id}")
	public ResponseEntity<Company_business_unit_master> deleteCompanyBUMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Company_business_unit_master cbum) {
		Company_business_unit_master op = companyBusinessUnitMasterService.deleteCompanyBUMaster(cbum, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findCompanyBUMaster")
	public ResponseEntity<List<Company_business_unit_master>> findCompanyBUMaster(@RequestParam(defaultValue = "") String searchtext) {
		List<Company_business_unit_master> cBUMList= companyBusinessUnitMasterService.findCompanyBUMaster(searchtext);
		if (cBUMList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(cBUMList);
		}
	}
	
	@GetMapping("/getCompanyBussinessUnitDetails/{company}/{bunit}")
	public Map<String, Object> getCompanyBussinessUnitDetails(@PathVariable(value = "company") String company,@PathVariable(value = "bunit") String bunit)
	{
		return companyBusinessUnitMasterService.getCompanyBussinessUnitDetails(company,bunit);
	}

	/******************* Company Business Unit Master End *******************/

	/********************** Item Type Master *********************/

	@Autowired
	Item_type_masterService item_type_masterService;
	
	@GetMapping("/getItypeSequenceId")
	public ResponseEntity<SequenceIdDTO> getItypeSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = item_type_masterService.getItypeSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createItemtype")
	public Item_type_master save(@Valid @RequestBody Item_type_master item_type_master) {
		return item_type_masterService.saveItemType(item_type_master);
	}

	/*@GetMapping("/getItemtypes/{company}")
	public List<Item_type_master> getItemtypes(@PathVariable(value = "company") String company) {
		return item_type_masterService.getItemtypes(company);
	}*/

	@GetMapping("/getItemTypeList/{psize}/{pageNo}")
	public List<Item_type_masterDTO> getItemTypeList(@PathVariable(value = "psize") int psize,
			@PathVariable(value = "pageNo") int pageNo) {
		return item_type_masterService.getItemtypes(psize, pageNo);
	}

	@GetMapping("/findItemTypes/{company}/{searchtext}")
	public List<Item_type_master> findItemTypes(@PathVariable(value = "company") String company,
			@PathVariable(value = "searchtext") String searchtext) {
		return item_type_masterService.findItemTypes(searchtext, company);
	}

	@GetMapping("/retriveItemType/{id}")
	public ResponseEntity<Item_type_master> getItemTypebyId(@PathVariable(value = "id") Long id) {
		Item_type_master op = item_type_masterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateItemType/{id}")
	public ResponseEntity<Item_type_master> updateItemType(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Item_type_master iType_master) {
		Item_type_master op = item_type_masterService.update(iType_master, id);
		return ResponseEntity.ok().body(op);
	}

	@PutMapping("/deleteItemTypes/{id}")
	public ResponseEntity<Item_type_master> deleteItemTypes(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Item_type_master iType_master) {
		Item_type_master op = item_type_masterService.deleteItemTypes(iType_master, id);
		return ResponseEntity.ok().body(op);
	}

	@DeleteMapping("/deleteItemType/{id}")
	public ResponseEntity<Item_type_master> deleteItemType(@PathVariable(value = "id") long id) {
		Item_type_master itype = item_type_masterService.findOne(id);
		if (itype == null) {
			return ResponseEntity.notFound().build();
		} else {
			item_type_masterService.deleteItemType(itype);
			return ResponseEntity.ok().build();
		}
	}
	
	@GetMapping("/chkItemTypeCodeStatus")
	public ResponseEntity<StatusDTO> chkItemTypeCodeStatus(@RequestParam(defaultValue = "") String code) 
	{
		StatusDTO val=item_type_masterService.chkItemTypeCodeStatus(code);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}

	/********************** Item Type Master End *********************/

	/********************** Item Catagory Master *********************/

	@Autowired
	Item_catagory_masterService item_catagoryService;
	
	@GetMapping("/getIcatSequenceId")
	public ResponseEntity<SequenceIdDTO> getIcatSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = item_catagoryService.getIcatSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createItemCategory")
	public Item_catagory_master save(@Valid @RequestBody Item_catagory_master item_catagory_master) {
		return item_catagoryService.saveItemCategory(item_catagory_master);
	}

	@GetMapping("/getItemCategories/{company}")
	public List<Item_catagory_master> findCategoryAll(@PathVariable(value = "company") String company) {
		return item_catagoryService.findAll(company);
	}

	@GetMapping("/getItemCategories/{company}/{fin_year}")
	public List<Item_catagory_master> findCategoryAll(@PathVariable(value = "company") String company,
			@PathVariable(value = "fin_year") String fin_year) {
		return item_catagoryService.findICategories(company, fin_year);
	}

	@GetMapping("/getItemCategories/{psize}&{pageNo}")
	public List<Item_catagory_master> getItemCategories(@PathVariable(value = "psize") int psize,
			@PathVariable(value = "pageNo") int pageNo) {
		return item_catagoryService.findAll(psize, pageNo);
	}

	@GetMapping("/findItemCategories/{company}/{searchtext}")
	public List<Item_catagory_master> findItemCategories(@PathVariable(value = "company") String company,
			@PathVariable(value = "searchtext") String searchtext) {
		return item_catagoryService.findItemCategories(searchtext, company);
	}

	@GetMapping("/retriveItemCategory/{id}")
	public ResponseEntity<Item_catagory_master> retriveItemCategory(@PathVariable(value = "id") Long id) {
		Item_catagory_master itmcat = item_catagoryService.findOne(id);
		if (itmcat == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(itmcat);
		}
	}

	@PutMapping("/updateItemCategory/{id}")
	public ResponseEntity<Item_catagory_master> updateItemCategory(@PathVariable(value = "id") long id,
			@Valid @RequestBody Item_catagory_master iCatagory_master) {
		Item_catagory_master op = item_catagoryService.update(iCatagory_master, id);
		return ResponseEntity.ok().body(op);
	}

	@PutMapping("/deleteItemCategory/{id}")
	public ResponseEntity<Item_catagory_master> deleteItemCategory(@PathVariable(value = "id") long id,
			@Valid @RequestBody Item_catagory_master iCatagory_master) {
		Item_catagory_master op = item_catagoryService.deleteItemCategory(iCatagory_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/chkItemCatagoryCodeStatus")
	public ResponseEntity<StatusDTO> chkItemCatagoryCodeStatus(@RequestParam(defaultValue = "") String code) 
	{
		StatusDTO val=item_catagoryService.chkItemCatagoryCodeStatus(code);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}

	/********************** Item Catagory Master End *********************/

	/******************* Item Group ******************/

	@Autowired
	Item_group_masterService item_group_masterService;
	
	@GetMapping("/getIgrpSequenceId")
	public ResponseEntity<SequenceIdDTO> getIgrpSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = item_group_masterService.getIgrpSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createItemGroup")
	public Item_group_master save(@Valid @RequestBody Item_group_master group_master) {
		return item_group_masterService.saveItemGroup(group_master);
	}

	@GetMapping("/getItemGroups/{company}")
	public List<Item_group_master> findGroupAll(@PathVariable(value = "company") String company) {
		return item_group_masterService.findAll(company);
	}

	@GetMapping("/retriveItemGroup/{id}")
	public ResponseEntity<Item_group_master> retriveItemGroup(@PathVariable(value = "id") Long id) {
		Item_group_master itmgrp = item_group_masterService.findOne(id);
		if (itmgrp == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(itmgrp);
		}
	}
	
	@GetMapping("/getItemGroupSalesAcc/{igroupid}")
	public Item_group_master_sales_accDTO getItemGroupSalesAcc(@PathVariable(value = "igroupid") String igroupid)
	{
		return item_group_masterService.getItemGroupSalesAcc(igroupid);
	}
	
	@GetMapping("/getItemGroupPurAcc/{igroupid}")
	public Item_group_master_pur_accDTO getItemGroupPurAcc(@PathVariable(value = "igroupid") String igroupid)
	{
		return item_group_masterService.getItemGroupPurAcc(igroupid);
	}
	
	@GetMapping("/getItemGroupSalesRtnAcc/{igroupid}")
	public Item_group_master_sales_retaccDTO getItemGroupSalesRtnAcc(@PathVariable(value = "igroupid") String igroupid)
	{
		return item_group_masterService.getItemGroupSalesRtnAcc(igroupid);
	}
	
	@GetMapping("/getItemGroupPurRtnAcc/{igroupid}")
	public Item_group_master_pur_retaccDTO getItemGroupPurRtnAcc(@PathVariable(value = "igroupid") String igroupid)
	{
		return item_group_masterService.getItemGroupPurRtnAcc(igroupid);
	}
	
	@GetMapping("/getItemGroupStkTrnsPur/{igroupid}")
	public Item_group_master_stk_trans_purDTO getItemGroupStkTrnsPur(@PathVariable(value = "igroupid") String igroupid)
	{
		return item_group_masterService.getItemGroupStkTrnsPur(igroupid);
	}
	
	@GetMapping("/getItemGroupStkTrnsSale/{igroupid}")
	public Item_group_master_stk_trans_saleDTO getItemGroupStkTrnsSale(@PathVariable(value = "igroupid") String igroupid)
	{
		return item_group_masterService.getItemGroupStkTrnsSale(igroupid);
	}
	
	@GetMapping("/getItemGroupJobworkSales/{igroupid}")
	public Item_group_jobwork_sales_accDTO getItemGroupJobworkSales(@PathVariable(value = "igroupid") String igroupid)
	{
		return item_group_masterService.getItemGroupJobworkSales(igroupid);
	}
	
	@GetMapping("/getItemGroupJobworkSalesReturn/{igroupid}")
	public Item_group_jobwork_sales_return_accDTO getItemGroupJobworkSalesReturn(@PathVariable(value = "igroupid") String igroupid)
	{
		return item_group_masterService.getItemGroupJobworkSalesReturn(igroupid);
	}

	@PutMapping("/updateItemGroup/{id}")
	public ResponseEntity<Item_group_master> updateItemGroup(@PathVariable(value = "id") long id,
			@Valid @RequestBody Item_group_master item_group_master) {
		Item_group_master op = item_group_masterService.update(item_group_master, id);
		return ResponseEntity.ok().body(op);
	}

	@PutMapping("/deleteItemGroup/{id}")
	public ResponseEntity<Item_group_master> deleteItemGroup(@PathVariable(value = "id") long id,
			@Valid @RequestBody Item_group_master item_group_master) {
		Item_group_master op = item_group_masterService.deleteItemGroup(item_group_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getGroupSalesAccThruItems")
	public ResponseEntity<Item_group_master_sales_accDTO> getGroupSalesAccThruItems(@RequestParam(defaultValue = "") String items)
	{
		Item_group_master_sales_accDTO grpAccounts = item_group_masterService.getGroupSalesAccThruItems(items);
		return new ResponseEntity<Item_group_master_sales_accDTO>(grpAccounts, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/findItemGroups")
	public ResponseEntity<List<Item_group_master>> findItemGroups(@RequestParam(defaultValue = "") String searchtext,@RequestParam(defaultValue = "") String company) {
		List<Item_group_master> iList= item_group_masterService.findItemGroups(searchtext, company);
		if (iList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(iList);
		}
	}
	
	@GetMapping("/chkItemGroupCodeStatus")
	public ResponseEntity<StatusDTO> chkItemGroupCodeStatus(@RequestParam(defaultValue = "") String code) 
	{
		StatusDTO val=item_group_masterService.chkItemGroupCodeStatus(code);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}
	

	/******************* Item Group End ******************/

	/******************** Item Master *******************/

	@Autowired
	Item_masterService item_masterService;
	
	@Autowired
	Item_opening_stkService item_opening_stkService;
	
	@GetMapping("/getItemSequenceId")
	public ResponseEntity<SequenceIdDTO> getItemSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = item_masterService.getItemSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getItemOpeningStock")
	public ResponseEntity<List<Item_maintain>> getItemOpeningStock(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String itype,@RequestParam(defaultValue="") String tdate)
	{
		List<Item_maintain> itemStockList = item_masterService.getItemOpeningStock(bunit,itype,tdate);
		
		return new ResponseEntity<List<Item_maintain>>(itemStockList, new HttpHeaders(), HttpStatus.OK);
	}

	/* to save a Item Master */
	@PostMapping("/createItem")
	public Item_master createItem(@Valid @RequestBody Item_master iMaster) {
		return item_masterService.saveItem(iMaster);
	}

	/* get all Items */
	@GetMapping("/getItems/{company}")
	public List<Item_master> getItems(@PathVariable(value = "company") String company) {

		return item_masterService.getItems(company);
	}
	
	@GetMapping("/findAllItems")
	public List<Item_master> findAllItems() {
		return item_masterService.findAllItems();
	}

	@GetMapping("/findItems/{company}/{searchtext}")
	public List<Item_master> findItems(@PathVariable(value = "company") String company,
			@PathVariable(value = "searchtext") String searchtext) {

		return item_masterService.findItems(company, searchtext);
	}
	
	@GetMapping("/getItemOpening")
	public ResponseEntity<List<Item_masterDTO>> getItemOpening(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String itype,@RequestParam(defaultValue="") String tdate)
	{
		List<Item_masterDTO> itemList = item_masterService.getItemOpening(bunit,itype,tdate);
		
		return new ResponseEntity<List<Item_masterDTO>>(itemList, new HttpHeaders(), HttpStatus.OK);
	}

	@Autowired
	Item_masterRepository item_masterRepository;

	@GetMapping("/getItemPaging")
	public Page<Item_master> getItemPaging(@RequestParam(defaultValue = "0") int pageno, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "id") String sortBy, @RequestParam String comp) {
		return item_masterService.getItemPaging(pageno, pagesize, sortBy, comp);
	}

	@GetMapping("/getItemPagingSorting")
	public ResponseEntity<List<Item_master>> getItemPagingSorting(@RequestParam(defaultValue = "0") Integer pageno,
			@RequestParam(defaultValue = "10") Integer pagesize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "") String comp) {
		List<Item_master> list = item_masterService.getItemPagingSorting(pageno, pagesize, sortBy, comp);

		return new ResponseEntity<List<Item_master>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getItemPagingTotal")
	public ResponseEntity<Paging_SortingDTO> getItemPagingTotal(@RequestParam(defaultValue = "0") Integer pageno,
			@RequestParam(defaultValue = "10") Integer pagesize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "") String comp) {
		Paging_SortingDTO list = item_masterService.getItemPagingTotal(pageno, pagesize, sortBy, comp);

		return new ResponseEntity<Paging_SortingDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/updateItemMaster/{id}")
	public ResponseEntity<Item_master> updateItemMaster(@PathVariable(value = "id") long id,
			@Valid @RequestBody Item_master iMaster) {
		Item_master op = item_masterService.update(iMaster, id);
		return ResponseEntity.ok().body(op);
	}

	@PutMapping("/deleteItemMaster/{id}")
	public ResponseEntity<Item_masterDTO> deleteItemMaster(@PathVariable(value = "id") long id,@Valid @RequestBody Item_master iMaster) {
		Item_masterDTO op = item_masterService.deleteItemMaster(id,iMaster);
		return ResponseEntity.ok().body(op);
	}
	
	
	@PostMapping("/createItemOpeningStk")
	public Item_opening_stk createItemOpeningStk(@Valid @RequestBody Item_opening_stk iOpening_stk) throws JsonParseException, JsonMappingException, IOException {
		return item_opening_stkService.save(iOpening_stk);
	}
	
	@GetMapping("/getItemOpeningStk")
	public ResponseEntity<List<Item_opening_stk_dtlsDTO>> getItemOpeningStk()
	{
		List<Item_opening_stk_dtlsDTO> itemList = item_opening_stkService.getItemOpeningStk();
		
		return new ResponseEntity<List<Item_opening_stk_dtlsDTO>>(itemList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/accountitemposting/{id}")
	public Item_master accountitemposting(@PathVariable(value = "id") long id) {
		return item_masterService.accountitemposting(id);
	}
	
	@GetMapping("/accountpostingUndoItemMaster/{id}")
	public Item_master accountpostingUndoItemMaster(@PathVariable(value = "id") long id) {
		return item_masterService.accountpostingUndoItemMaster(id);
	}
	
  @GetMapping(value = "/searchItemData")
	public List<Map<String,Object>> searchItemData(@RequestParam(defaultValue = "") String itemtype1,@RequestParam(defaultValue = "") String company_name)
	 {
		System.out.println("itemtype1:"+itemtype1);
		return item_masterService.searchItemData(itemtype1,company_name);
	 }
	
  @GetMapping("/retriveClassifiedItem/{itemid}/{company}")
	public List<Map<String, Object>> retriveClassifiedItem(@PathVariable(value = "itemid") String itemid,@PathVariable(value = "company") String company)
	{
		return item_masterService.retriveClassifiedItem(itemid,company);
	}
  
  @GetMapping("/retriveItemSizeAndWeight/{itemid}/{company}")
	public List<Map<String, Object>> retriveItemSizeAndWeight(@PathVariable(value = "itemid") String itemid,@PathVariable(value = "company") String company)
	{
		return item_masterService.retriveItemSizeAndWeight(itemid,company);
	}
	/**************** Item Master End ***************/

	/******************* Department Master Start *******************/
	@Autowired
	DepartmentMasterService departmentMasterService;

	@PostMapping("/createDepartment")
	public Department_master save(@Valid @RequestBody Department_master departmentMaster) {
		return departmentMasterService.save(departmentMaster);
	}

	@GetMapping("/getDepartments")
	public List<Department_master> findDepartmentAll() {
		return departmentMasterService.findAll();
	}

	@GetMapping("/retriveDepartment/{id}")

	public ResponseEntity<Department_master> gettDepartment(@PathVariable(value = "id") Long id) {
		Department_master op = departmentMasterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateDepartment/{id}")
	public ResponseEntity<Department_master> updateDepartment(@PathVariable(value = "id") long id,
			@Valid @RequestBody Department_master departmentMaster) {
		Department_master op = departmentMasterService.update(departmentMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteDepartment/{id}")
	public ResponseEntity<Department_master> deleteDepartment(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Department_master departmentMaster) {
		Department_master op = departmentMasterService.deleteDepartment(departmentMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findDepartment")
	public ResponseEntity<List<Department_master>> findDepartment(@RequestParam(defaultValue = "") String searchtext) {
		List<Department_master> deptList= departmentMasterService.findDepartment(searchtext);
		if (deptList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(deptList);
		}
	}
	
	/******************* Department Master End *******************/

	/******************* Employees Master Start *******************/
	@Autowired
	EmployeeMasterService employeeMasterService;
	
	
	@GetMapping("/getEmployeeSequenceId")
	public ResponseEntity<SequenceIdDTO> getEmployeeSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = employeeMasterService.getEmployeeSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createEmployee")
	//@RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	public Employee_master save(@Valid @RequestBody Employee_master employeeMaster) {
		
		//System.out.println(" hi ");
		return employeeMasterService.save(employeeMaster);
	}

	@GetMapping("/getEmployees")

	public List<Employee_masterDTO> findEmployeeAll() {
		return employeeMasterService.findAll();
	}

	@GetMapping("/retriveEmployee/{id}")

	public ResponseEntity<Employee_master> getEmployee(@PathVariable(value = "id") Long id) {
		//System.out.println("controler: " + id);
		Employee_master op = employeeMasterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee_master> updateEmployee(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Employee_master employeeMaster) {
		Employee_master op = employeeMasterService.update(employeeMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteEmployee/{id}")
	public ResponseEntity<Employee_master> deleteEmployee(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Employee_master employeeMaster) {
		Employee_master op = employeeMasterService.deleteEmployee(employeeMaster, id);
		return ResponseEntity.ok().body(op);
	}

	/******************* Employees Master End *******************/

	/******************* Vehicle Master Start *******************/
	@Autowired
	VehicleMasterService vehicleMasterService;
	
	@GetMapping("/getVehicleSequenceId")
	public ResponseEntity<SequenceIdDTO> getVehicleSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = vehicleMasterService.getVehicleSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/createVehicle")
	public VehicleMaster createVehicle(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String Input) throws JsonParseException,JsonMappingException,IOException{
		VehicleMaster vehicleMaster=new ObjectMapper().readValue(Input, VehicleMaster.class);
		return vehicleMasterService.save(vehicleMaster,files);
	}

	@PostMapping("/createVehiclepopup")
	public VehicleMaster createVehiclepopup(@Valid @RequestBody VehicleMaster vehicleMaster) {
		return vehicleMasterService.popupsave(vehicleMaster);
	}
	
	@GetMapping("/getVehicles")
	public List<VehicleMaster> findVehicleMasterAll() {
		return vehicleMasterService.findAll();
	}
	
	@GetMapping("/getVehicleDocDtls")
	public ResponseEntity<List<Vehicle_master_doc_details>> getVehicleDocDtls(@RequestParam(defaultValue = "") String vid) {
		List<Vehicle_master_doc_details> list = vehicleMasterService.getVehicleDocDtls(vid);
		return new ResponseEntity<List<Vehicle_master_doc_details>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/retriveVehicle/{id}")
	public ResponseEntity<VehicleMaster> getVehicleMaster(@PathVariable(value = "id") Long id) {
		VehicleMaster op = vehicleMasterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateVehicle")
	public ResponseEntity<VehicleMaster> updateVehicleMaster(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String Input) throws JsonParseException,JsonMappingException,IOException{
		VehicleMaster vehicleMaster=new ObjectMapper().readValue(Input, VehicleMaster.class);
		VehicleMaster op = vehicleMasterService.update(vehicleMaster, files);
		return ResponseEntity.ok().body(op);
	}

	@GetMapping("/getVehiSequenceId/{prefix}")
	public SequenceIdDTO getSequenceId(@PathVariable(value = "prefix") String prefix) {
		return vehicleMasterService.getSequenceId(prefix);
	}
	
	@PutMapping("/deleteVehicleMaster/{id}")
	public ResponseEntity<VehicleMaster> deleteVehicleMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody VehicleMaster vehicleMaster) {
		VehicleMaster op = vehicleMasterService.deleteVehicleMaster(vehicleMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findVehicles")
	public ResponseEntity<List<VehicleMaster>> findVehicles(@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String searchtext) {
		List<VehicleMaster> vehicleList= vehicleMasterService.findVehicle(company,searchtext);
		if (vehicleList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(vehicleList);
		}
	}
	
	/********************************** GET DIRECTORY **********************************/
	@Autowired
	VehicleMasterRepository vehicleMasterRepository;
	
	@Autowired
	Vehicle_master_doc_detailsRepository vehicle_master_doc_detailsRepository;
	
	@GetMapping( value = "/getDocFile", produces = MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody byte[] getDocFileWithMediaType(@RequestParam("directory") String directory,@RequestParam("file_name") String file_name) throws IOException 
	{
		if(directory.contains("colon") || directory.contains("backslash"))
		{
			directory=directory.replace("colon", ":").replace("backslash", "/");
		}
		//System.out.println("Directory: "+directory+" File: "+file_name);
		
		String filePath=docPdf+"/VehicleDocuments";
		Optional<VehicleMaster> vOptional=vehicleMasterRepository.getVehicleDtls(directory);
		if(vOptional.isPresent()) {
			filePath=filePath+"/"+vOptional.get().getVehicle_no().trim()+"/"+vOptional.get().getVehicle_id().trim()+"_"+file_name+".pdf";
		}
		
		System.err.println(directory+","+file_name+","+filePath);
	    FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        try {
            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        finally { 
        	if (fileInputStream != null) {try {fileInputStream.close();} catch (IOException e) {e.printStackTrace();}}
        }
        return bytesArray;
	}
	/********************************** GET DIRECTORY **********************************/

	/******************* Vehicle Master End *******************/

	/******************* VehicleType Master Start *******************/
	@Autowired
	VehicleTypeMasterService vehicleTypeMasterService;

	@GetMapping("/getVtypeSequenceId")
	public ResponseEntity<SequenceIdDTO> getVtypeSequenceId(@RequestParam(defaultValue = "") String prefix)
	{
		SequenceIdDTO list = vehicleTypeMasterService.getVtypeSequenceId(prefix);
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/createVehicleType")
	public VehicleTypeMaster save(@Valid @RequestBody VehicleTypeMaster vehicleTypeMaster) {
		return vehicleTypeMasterService.save(vehicleTypeMaster);
	}

	@GetMapping("/getVehicleTypes")
	public List<VehicleTypeMaster> findVehicleTypeMasterAll() {
		return vehicleTypeMasterService.findAll();
	}

	@GetMapping("/retriveVehicleType/{id}")
	public ResponseEntity<VehicleTypeMaster> getVehicleTypeMaster(@PathVariable(value = "id") Long id) {
		VehicleTypeMaster op = vehicleTypeMasterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateVehicleType/{id}")
	public ResponseEntity<VehicleTypeMaster> updateVehicleTypeMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody VehicleTypeMaster vehicleTypeMaster) {
		VehicleTypeMaster op = vehicleTypeMasterService.update(vehicleTypeMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteVehicleType/{id}")
	public ResponseEntity<VehicleTypeMaster> deleteVehicleType(@PathVariable(value = "id") Long id,
			@Valid @RequestBody VehicleTypeMaster vehicleTypeMaster) {
		VehicleTypeMaster op = vehicleTypeMasterService.deleteVehicleType(vehicleTypeMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findVehicleType")
	public ResponseEntity<List<VehicleTypeMaster>> findVehicleType(@RequestParam(defaultValue = "") String searchtext) {
		List<VehicleTypeMaster> vehicleList= vehicleTypeMasterService.findVehicleType(searchtext);
		if (vehicleList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(vehicleList);
		}
	}
	/******************* VehicleType Master End *******************/

	/******************* Warehouse Master Start *******************/
	@Autowired
	WarehouseMasterService warehouseMasterService;
	
	@GetMapping("/getWareHouseSequenceId")
	public ResponseEntity<SequenceIdDTO> getWareHouseSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = warehouseMasterService.getWareHouseSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createWarehouse")
	public Warehouse_master save(@Valid @RequestBody Warehouse_master warehouseMaster) {
		return warehouseMasterService.save(warehouseMaster);
	}

	@GetMapping("/getWarehouses")
	public List<Warehouse_master> findWarehouseMasterAll() {
		return warehouseMasterService.findAll();
	}

	@GetMapping("/retriveWarehouse/{id}")
	public ResponseEntity<Warehouse_master> getWarehouseMaster(@PathVariable(value = "id") Long id) {
		Warehouse_master op = warehouseMasterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/retriveWarehouseStackDtls/{warehouse_id}")
	public List<Map<String, Object>> retriveWarehouseStackDtls(@PathVariable(value = "warehouse_id") String warehouse_id) 
	{
		return warehouseMasterService.warehouseStackRetriveList(warehouse_id);
	}

	@PutMapping("/updateWarehouse/{id}")
	public ResponseEntity<Warehouse_master> updateWarehouseMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Warehouse_master warehouseMaster) {
		Warehouse_master op = warehouseMasterService.update(warehouseMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteWarehouse/{id}")
	public ResponseEntity<Warehouse_master> deleteWarehouse(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Warehouse_master warehouse_master) {
		Warehouse_master op = warehouseMasterService.deleteWarehouse(warehouse_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findWarehouse")
	public ResponseEntity<List<Warehouse_master>> findWarehouse(@RequestParam(defaultValue = "") String searchtext) {
		List<Warehouse_master> wList= warehouseMasterService.findAllWarehouse(searchtext);
		if (wList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(wList);
		}
	}
	
	/******************* Warehouse Master End *******************/

	/*******************
	 * Customer Bussiness Partner Master Start
	 *******************/
	@Autowired
	Cust_bussiness_partnerService cust_bussiness_partnerService;
	
	@GetMapping("/getCustSequenceId")
	public ResponseEntity<SequenceIdDTO> getCustSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String wtype)
	{
		SequenceIdDTO list = cust_bussiness_partnerService.getCustSequenceId(prefix,company,wtype);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createCustomerBussinessPartner")
	public Cust_bussiness_partner save(@Valid @RequestBody Cust_bussiness_partner cust_bussiness_partner) {
		return cust_bussiness_partnerService.save(cust_bussiness_partner);
	}

	@PutMapping("/updateCustTransporters/{custid}/{transid}")
	public ResponseEntity<String> updateCustTransporters(@PathVariable(value = "custid") String custid,
			@PathVariable(value = "transid") String transid) {
		String op = cust_bussiness_partnerService.updateCustTransporters(custid, transid);
		return ResponseEntity.ok().body(op);
	}

	@PutMapping("/updateCustomerTransporters/{custid}/{transid}")
	public ResponseEntity<Cust_bussiness_partner_delv_to> updateCustomerTransporters(
			@PathVariable(value = "custid") String custid, @PathVariable(value = "transid") String transid) {
		Cust_bussiness_partner_delv_to op = cust_bussiness_partnerService.updateCustomerTransporters(custid, transid);
		return ResponseEntity.ok().body(op);
	}

	@PutMapping("/updateCustomerMaster/{id}")
	public ResponseEntity<Cust_bussiness_partner> updateCustomerMaster(@PathVariable(value = "id") long id,
			@Valid @RequestBody Cust_bussiness_partner iMaster) {
		Cust_bussiness_partner op = cust_bussiness_partnerService.update(iMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteCustomerMaster/{id}")
	public ResponseEntity<Cust_bussiness_partner> deleteCustomerMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Cust_bussiness_partner cust_bussiness_partner) {
		Cust_bussiness_partner op = cust_bussiness_partnerService.deleteCustomerMaster(cust_bussiness_partner, id);
		return ResponseEntity.ok().body(op);
	}

	@GetMapping("/getCustomerBussinessPartner")
	public List<Cust_bussiness_partner> findCustomerBussinessPartnerAll() {
		return cust_bussiness_partnerService.findAll();
	}
	
	@GetMapping("/getCustomerBussinessPartnerFastApi/{company}")
	public List<Map<String,Object>> getCustomerBussinessPartnerFastApi(@PathVariable(value = "company") String company) {
		return cust_bussiness_partnerService.getCustomerBussinessPartnerFastApi(company);
	}
	
	/*
	@GetMapping("/getCustomerBussinessPartner")
	public List<Cust_bussiness_partner> findCustomerBussinessPartnerAll() {
		return cust_bussiness_partnerService.findAll();
	}
*/
	@GetMapping("/findCustomers/{searchtext}")
	public List<Cust_bussiness_partner> findCustomers(@PathVariable(value = "searchtext") String searchtext) {

		return cust_bussiness_partnerService.findCustomers(searchtext);
	}

	@GetMapping("/retriveCustomer/{id}")
	public ResponseEntity<Cust_bussiness_partner> getCustomerr(@PathVariable(value = "id") Long id) {
		Cust_bussiness_partner op = cust_bussiness_partnerService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@GetMapping("/custAddRetriveList/{code}")
	public Cust_bussiness_partner_addressDTO custAddRetriveList(@PathVariable(value = "code") String code) {
		return cust_bussiness_partnerService.custAddRetriveList(code);
	}

	@GetMapping("/getCustomerThruBU/{bunit}")
	public List<Cust_bussiness_partnerDTOC> getCustomerThruBU(@PathVariable(value = "bunit") String bunit) {
	 List<Cust_bussiness_partnerDTOC> customerlist = new ArrayList<Cust_bussiness_partnerDTOC>();
		 
		 Cust_bussiness_partnerDTOC def= new Cust_bussiness_partnerDTOC("0","0","Choose an Option");
		 customerlist.add(def);
		 customerlist.addAll(cust_bussiness_partnerService.getCustomerThruBU(bunit));
		return customerlist;
	}
	
	@GetMapping("/checkcustomersaleclosed/{customer_id}")
	public StatusDTO checkcustomersaleclosed (@PathVariable(value = "customer_id") String customer_id)
	{
	
		return cust_bussiness_partnerService.checkcustomersaleclosed(customer_id);
	}
	
	@GetMapping("/getCustomerThruBUnewlist/{bunit}")
	public List<Map<String,Object>> getCustomerThruBUnewlist(@PathVariable(value = "bunit") String bunit) {
	
		return cust_bussiness_partnerService.getCustomerThruBUnewlist(bunit);
	
	}
	
	
	@GetMapping("/getCustomerThruBUGrp")
	public ResponseEntity<List<Cust_bussiness_partnerDTO>> getCustomerThruBUGrp(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String custgrp) {
		List<Cust_bussiness_partnerDTO> list = cust_bussiness_partnerService.getCustomerThruBUGrp(bunit,custgrp);
		return new ResponseEntity<List<Cust_bussiness_partnerDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/custAddDtlsRetriveList/{code}/{company}")
	public List<Cust_bussiness_partner_address_dtlsDTO> custAddDtlsRetriveList(
			@PathVariable(value = "code") String code,@PathVariable(value = "company") String company) {
		//System.out.println(code+" : custAddDtlsRetriveList	: "+company);
		return cust_bussiness_partnerService.custAddDtlsRetriveList(code,company);
	}
	
	@GetMapping("/custAddDtlsRetriveListnew/{code}")
	public List<Cust_bussiness_partner_address_dtlsDTO> custAddDtlsRetriveListnew(
			@PathVariable(value = "code") String code) {
		//System.out.println(code+" : custAddDtlsRetriveList	: "+company);
		return cust_bussiness_partnerService.custAddDtlsRetriveListnew(code);
	}

	@GetMapping("/custContactByName/{code},{name}/{company}")
	public Cust_bussiness_partner_address_dtlsDTO custContactByName(@PathVariable(value = "code") String code,
			@PathVariable(value = "name") String name,@PathVariable(value = "company") String company) {
		return cust_bussiness_partnerService.custContactByName(code, name,company);
	}

	@GetMapping("/custBillAddRetriveList/{code}")
	public Cust_bussiness_partner_bill_addrDTO custBillAddRetriveList(@PathVariable(value = "code") String code) {
		return cust_bussiness_partnerService.custBillAddRetriveList(code);
	}

	@GetMapping("/custBillAddDtlsRetriveList/{code}")
	public List<Cust_bussiness_partner_bill_addr_dtlsDTO> custBillAddDtlsRetriveList(
			@PathVariable(value = "code") String code) {
		return cust_bussiness_partnerService.custBillAddDtlsRetriveList(code);
	}
	
	@GetMapping("/custShipAddDtlsRetriveList/{cp_id}")
	public List<Map<String, Object>> custShipAddDtlsRetriveList(
			@PathVariable(value = "cp_id") String cp_id) {
		return cust_bussiness_partnerService.custShipAddDtlsRetriveList(cp_id);
	}

	@GetMapping("/custAccountRetriveList/{code}/{company}")
	public Cust_bussiness_partner_accontDTO custAccountRetriveList(@PathVariable(value = "code") String code,@PathVariable(value = "company") String company) {
		return cust_bussiness_partnerService.custAccountRetriveList(code,company);
	}
	
	@GetMapping("/custAccountRetriveList/{code}")
	public Cust_bussiness_partner_accontDTO custAccountRetriveList(@PathVariable(value = "code") String code) {
		return cust_bussiness_partnerService.custAccountRetriveList(code);
	}

	@GetMapping("/custStatutoryRetriveList/{code}")
	public Cust_bussiness_partner_statutoryDTO custStatutoryRetriveList(@PathVariable(value = "code") String code) {
		return cust_bussiness_partnerService.custStatutoryRetriveList(code);
	}

	@GetMapping("/custBrokerRetriveList/{code}")
	public List<Cust_bussiness_partner_brokerDTO> custBrokerRetriveList(@PathVariable(value = "code") String code) {
		return cust_bussiness_partnerService.custBrokerRetriveList(code);
	}

	@GetMapping("/custBrokerByCode/{bcode}")
	public List<Cust_bussiness_partner_brokerDTO> custBrokerByCode(@PathVariable(value = "bcode") String bcode) {
		return cust_bussiness_partnerService.custBrokerByCode(bcode);
	}

	@GetMapping("/getCustomerBrokerDtls/{cp_id}/{bcode}")
	public Cust_bussiness_partner_brokerDTO getCustomerBrokerDtls(@PathVariable(value = "cp_id") String cp_id,
			@PathVariable(value = "bcode") String bcode) {
		return cust_bussiness_partnerService.getCustomerBrokerDtls(cp_id, bcode);
	}

	@GetMapping("/custDocRetriveList/{code}")
	public List<Cust_bussiness_partner_docDTO> custDocRetriveList(@PathVariable(value = "code") String code) {
		return cust_bussiness_partnerService.custDocRetriveList(code);
	}
	
	@GetMapping("/chkCustCodeStatus")
	public ResponseEntity<StatusDTO> chkCustCodeStatus(@RequestParam(defaultValue = "") String code) 
	{
		StatusDTO val=cust_bussiness_partnerService.chkCustCodeStatus(code);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}

	@GetMapping("/accountpostingCustomerMaster/{id}")
	public Cust_bussiness_partner accountpostingCustomerMaster(@PathVariable(value = "id") Long id) {
		return cust_bussiness_partnerService.accountpostingCustomerMaster(id);
		
	}
	
	@GetMapping("/accountpostingUndoCustomerMaster/{id}")
	public Cust_bussiness_partner accountpostingUndoCustomerMaster(@PathVariable(value = "id") Long id) {
		return cust_bussiness_partnerService.accountpostingUndoCustomerMaster(id);
		
	}
	
	@GetMapping(value = "/searchCustomerMasterData")
	public List<Cust_bussiness_partner> searchCustomerMasterData(@RequestParam(defaultValue = "") String customer_name1,@RequestParam(defaultValue = "") String cust_group,
			@RequestParam(defaultValue = "") String cust_type,@RequestParam(defaultValue = "") String finyear,@RequestParam(defaultValue = "") String company_name)
	 {
		//System.out.println("cust_name "+customer_name1);
		return cust_bussiness_partnerService.searchCustomerMasterData(customer_name1,cust_group,cust_type,finyear,company_name);
	 }
	
	/******************* Customer Bussiness Partner Master End *******************/

	/******************* Customer Group Master Start *******************/
	@Autowired
	Cust_groupService cust_groupService;
	
	@GetMapping("/getCgrpSequenceId")
	public ResponseEntity<SequenceIdDTO> getCgrpSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = cust_groupService.getCgrpSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createCustomerGroup")
	public Cust_group save(@Valid @RequestBody Cust_group cust_group) {
		return cust_groupService.save(cust_group);
	}

	@GetMapping("/getCustomerGroup")
	public List<Cust_group> findCustomerGroupAll() {
		return cust_groupService.findAll();
	}

	@GetMapping("/retriveCustomerGrp/{id}")
	public ResponseEntity<Cust_group> getCustomerGrp(@PathVariable(value = "id") Long id) {
		Cust_group op = cust_groupService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateCustomerGrp/{id}")
	public ResponseEntity<Cust_group> updateCustomerGrp(@PathVariable(value = "id") long id,
			@Valid @RequestBody Cust_group cust_group) {
		Cust_group op = cust_groupService.update(cust_group, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteCustGrp/{id}")
	public ResponseEntity<Cust_group> deleteCustGrp(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Cust_group cust_group) {
		Cust_group op = cust_groupService.deleteCustGrp(cust_group, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findCustomerGrps")
	public ResponseEntity<List<Cust_group>> findCustomerGrps(@RequestParam(defaultValue = "") String searchtext,@RequestParam(defaultValue = "") String company) {
		List<Cust_group> cgList= cust_groupService.findCustomerGrp(searchtext,company);
		if (cgList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(cgList);
		}
	}
	
	@GetMapping("/chkCustGrpCodeStatus")
	public ResponseEntity<StatusDTO> chkCustGrpCodeStatus(@RequestParam(defaultValue = "") String code) 
	{
		StatusDTO val=cust_groupService.chkCustGrpCodeStatus(code);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}
	

	/******************* Customer Group Master End *******************/

	/******************* Supplier Group Master Start *******************/
	@Autowired
	Supplier_groupService supplier_groupService;
	
	@GetMapping("/getSgrpSequenceId")
	public ResponseEntity<SequenceIdDTO> getSgrpSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = supplier_groupService.getSgrpSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createSupplierGroup")
	public ResponseEntity<Supplier_group> save(@Valid @RequestBody Supplier_group supplier_group) {
		supplier_groupService.save(supplier_group);
		return new ResponseEntity<Supplier_group>(supplier_group, HttpStatus.OK);
	}

	@GetMapping("/getSupplierGroup")
	public List<Supplier_group> findSupplierGroupAll() {
		return supplier_groupService.findAll();
	}

	@GetMapping("/retriveSupplierGroup/{id}")
	public ResponseEntity<Supplier_group> retriveSupplierGroup(@PathVariable(value = "id") Long id) {
		Supplier_group op = supplier_groupService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateSupplierGroup/{id}")
	public ResponseEntity<Supplier_group> updateSupplierGroup(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Supplier_group supplier_group) {
		Supplier_group op = supplier_groupService.update(supplier_group, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteSupplierGroup/{id}")
	public ResponseEntity<Supplier_group> deleteSupplierGroup(@PathVariable(value = "id") long id,
			@Valid @RequestBody Supplier_group supplier_group) {
		Supplier_group op = supplier_groupService.deleteSupplierGrp(supplier_group, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findSupplierGrps")
	public ResponseEntity<List<Supplier_group>> findSupplierGrps(@RequestParam(defaultValue = "") String searchtext,@RequestParam(defaultValue = "") String company) {
		List<Supplier_group> sgList= supplier_groupService.findSupplierGrps(searchtext,company);
		if (sgList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(sgList);
		}
	}
	
	@GetMapping("/chkSupplierGrpCodeStatus")
	public ResponseEntity<StatusDTO> chkSupplierGrpCodeStatus(@RequestParam(defaultValue = "") String code) 
	{
		StatusDTO val=supplier_groupService.chkSupplierGrpCodeStatus(code);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}

	/******************* Supplier Group Master End *******************/

	/******************* Transporter Group Master Start *******************/
	@Autowired
	Transporter_groupService transporter_groupService;
	
	@GetMapping("/getTgrpSequenceId")
	public ResponseEntity<SequenceIdDTO> getTgrpSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = transporter_groupService.getTgrpSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createTransporterGroup")
	public Transporter_group save(@Valid @RequestBody Transporter_group transporter_group) {
		return transporter_groupService.save(transporter_group);
	}

	@GetMapping("/getTransporterGroup")
		public List<Transporter_group> findTransporterGroupAll() {
			return transporter_groupService.findAll();
	}
		
	@PutMapping("/updateTransGrp/{id}")
	public ResponseEntity<Transporter_group> updateTransGrp(@PathVariable(value="id") long id,@Valid @RequestBody Transporter_group transporter_group)
	{
		Transporter_group op=transporter_groupService.update(transporter_group,id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteTransporterGrp/{id}")
	public ResponseEntity<Transporter_group> deleteTransporterGrps(@PathVariable(value = "id") long id,
		@Valid @RequestBody Transporter_group transporter_group) {
		Transporter_group op = transporter_groupService.deleteTransporterGrps(transporter_group, id);
		return ResponseEntity.ok().body(op);
	}
		
	@GetMapping("/findTransporterGrps")
	public ResponseEntity<List<Transporter_group>> findTransporterGrps(@RequestParam(defaultValue = "") String searchtext,@RequestParam(defaultValue = "") String company) {
		List<Transporter_group> tgList= transporter_groupService.findTransporterGrps(searchtext,company);
		if (tgList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(tgList);
		}
	}	
	
	@GetMapping("/chkTransporterGrpCodeStatus")
	public ResponseEntity<StatusDTO> chkTransporterGrpCodeStatus(@RequestParam(defaultValue = "") String code) 
	{
		StatusDTO val=transporter_groupService.chkTransporterGrpCodeStatus(code);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}
	
	/******************* Transporter Group Master End *******************/

	/******************* Broker Group Master Start *******************/
	@Autowired
	Broker_groupService broker_groupService;
	
	@GetMapping("/getBgrpSequenceId")
	public ResponseEntity<SequenceIdDTO> getBgrpSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = broker_groupService.getBgrpSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createBrokerGroup")
	public Broker_group save(@Valid @RequestBody Broker_group broker_group) {
		return broker_groupService.save(broker_group);
	}

	@GetMapping("/getBrokerGroup")
	public List<Broker_group> findBrokerGroupAll(@RequestParam String company) {
		return broker_groupService.findAll(company);
	}

	@GetMapping("/retriveBrokerGrp/{id}")
	public ResponseEntity<Broker_group> getBroker(@PathVariable(value = "id") Long id) {
		Broker_group op = broker_groupService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateBrokerGrp/{id}")
	public ResponseEntity<Broker_group> updateBrokerGrp(@PathVariable(value = "id") long id,
			@Valid @RequestBody Broker_group broker_group) {
		Broker_group op = broker_groupService.update(broker_group, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteBrokerGrp/{id}")
	public ResponseEntity<Broker_group> deleteBrokerGrp(@PathVariable(value = "id") long id,
			@Valid @RequestBody Broker_group broker_group) {
		Broker_group op = broker_groupService.deleteBrokerGrp(broker_group, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findBrokerGrps")
	public ResponseEntity<List<Broker_group>> findBrokerGrps(@RequestParam(defaultValue = "") String searchtext,@RequestParam(defaultValue = "") String company) {
		List<Broker_group> bgList=broker_groupService.findBrokerGrp(searchtext,company);
		if(bgList==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(bgList);
		}
	}
	
	@GetMapping("/chkBrokerGrpCodeStatus")
	public ResponseEntity<StatusDTO> chkBrokerGrpCodeStatus(@RequestParam(defaultValue = "") String code, @RequestParam(defaultValue = "") String company) 
	{
		StatusDTO val=broker_groupService.chkBrokerGrpCodeStatus(code,company);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}

	/******************* Broker Group Master End *******************/

	/*******************
	 * Supplier Bussiness Partner Master Start
	 *******************/
	@Autowired
	Supp_bussiness_partnerService supp_bussiness_partnerService;

	@GetMapping("/getSuppSequenceId")
	public ResponseEntity<SequenceIdDTO> getSuppSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String wtype)
	{
		SequenceIdDTO list = supp_bussiness_partnerService.getSuppSequenceId(prefix,company,wtype);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
//10-05-2022	
	/*@PostMapping("/createSupplierBPartner")
	public Supp_bussiness_partner save(@Valid @RequestBody Supp_bussiness_partner supp_bussiness_partner) {
		return supp_bussiness_partnerService.save(supp_bussiness_partner);
	}

	*/
	//
	
	@PostMapping("/createSupplierBPartner")
	public Supp_bussiness_partner suppsave(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		////System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Supp_bussiness_partner supp_bussiness_partner=objectMapper.readValue(input, Supp_bussiness_partner.class);
		
		return supp_bussiness_partnerService.save(supp_bussiness_partner,files);
	}
	
	
	
	
	@GetMapping("/getSupplierBPartners")
	public List<Supp_bussiness_partner> findSupplierBussinessPartnerAll() {
		return supp_bussiness_partnerService.findAll();
	}
	
	@GetMapping("/getSupplierBPartnersFastApi/{company}")
	public List<Map<String,Object>> getSupplierBPartnersFastApi(@PathVariable(value = "company") String company) {
		return supp_bussiness_partnerService.getSupplierBPartnersFastApi(company);
	}

	@GetMapping("/findSuppliers/{searchtext}")
	public List<Supp_bussiness_partner> findSuppliers(@PathVariable(value = "searchtext") String searchtext) {

		return supp_bussiness_partnerService.findSuppliers(searchtext);
	}

	@PutMapping("/updateSuppBussinessPartner/{id}")
	public ResponseEntity<Supp_bussiness_partner> updateSuppBussinessPartner(@PathVariable(value = "id") long id,
			@Valid @RequestBody Supp_bussiness_partner iMaster) {
		Supp_bussiness_partner op = supp_bussiness_partnerService.update(iMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteSuppBussinessPartner/{id}")
	public ResponseEntity<Supp_bussiness_partner> deleteSuppBussinessPartner(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Supp_bussiness_partner supp_bussiness_partner) {
		Supp_bussiness_partner op = supp_bussiness_partnerService.deleteSupp_bp(supp_bussiness_partner, id);
		return ResponseEntity.ok().body(op);
	}

	@GetMapping("/getSupplierBrokerList/{id}")
	public List<Supp_bussiness_partner_brokerDTO> getSupplierBrokerList(@PathVariable(value = "id") Long id) {
		return supp_bussiness_partnerService.getSupplierBrokerList(id);
	}
	
	@GetMapping("/getSupplierBrokerDtls/{sbp_id}/{brokercode}")
	public Supp_bussiness_partner_brokerDTO getSupplierBrokerDtls(@PathVariable(value = "sbp_id") String sbp_id,
			@PathVariable(value = "brokercode") String brokercode) {
		return supp_bussiness_partnerService.getSupplierBrokerDtls(sbp_id, brokercode);
	}

	@GetMapping("/getSupplierBrokersByCode/{code}")
	public List<Supp_bussiness_partner_brokerDTO> getSupplierBrokersByCode(@PathVariable(value = "code") String code) {
		return supp_bussiness_partnerService.getSupplierBrokersByCode(code);
	}

	@GetMapping("/getSupplierBrokersDtls/{code}")
	public List<Supp_bussiness_partner_brokerDTO> getSupplierBrokerDtls(@PathVariable(value = "code") String code) {
		return supp_bussiness_partnerService.getSupplierBrokerDtls(code);
	}
	
	@GetMapping("/getnewSupplierBrokersDtls/{code}/{sup}")
	public List<Supp_bussiness_partner_brokerDTO> getnewSupplierBrokersDtls(@PathVariable(value = "code") String code,@PathVariable(value = "sup") String sup) {
		return supp_bussiness_partnerService.getnewSupplierBrokersDtls(code,sup);
	}
	
	@GetMapping("/getSupplierStatutaries/{code}")
	public List<Supp_bussiness_partner_statutoryDTO> getSupplierStatutaries(@PathVariable(value = "code") String code) {
		return supp_bussiness_partnerService.getSupplierStatutaries(code);
	}

	@GetMapping("/getSupplierThruBU/{bunit}")
	public List<Supp_bussiness_partnerDTO> getSupplierThruBU(@PathVariable(value = "bunit") String bunit) {
		List<Supp_bussiness_partnerDTO>supplierlist = new ArrayList<Supp_bussiness_partnerDTO>();
		 
		Supp_bussiness_partnerDTO def= new Supp_bussiness_partnerDTO();
		supplierlist.add(def);
		
		supplierlist.forEach((e->{
			e.setBp_Id("0");
			e.setBp_name("Choose an option");
			
		}));
		
		supplierlist.addAll(supp_bussiness_partnerService.getSupplierThruBU(bunit));
		return supplierlist;
	}
	
	@GetMapping("/getSupplierThruBUNew/{bunit}")
	public List<Map<String,Object>> getSupplierThruBUNew(@PathVariable(value = "bunit") String bunit) {
		
		return supp_bussiness_partnerService.getSupplierThruBUNew(bunit);
	}
	
	@GetMapping("/chkSuppBpCodeStatus")
	public ResponseEntity<StatusDTO> chkSuppBpCodeStatus(@RequestParam(defaultValue = "") String code) 
	{
		StatusDTO val=supp_bussiness_partnerService.chkSuppBpCodeStatus(code);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/accountpostingSupplierBPartner/{id}")
	public Supp_bussiness_partner accountpostingSupplierBPartner(@PathVariable(value = "id") Long id) {
		return supp_bussiness_partnerService.accountpostingSupplierBPartner(id);
		
	}
	
	@GetMapping("/accountpostingUndoSupplierBPartner/{id}")
	public Supp_bussiness_partner accountpostingUndoSupplierBPartner(@PathVariable(value = "id") Long id) {
		return supp_bussiness_partnerService.accountpostingUndoSupplierBPartner(id);
		
	}
	
	@GetMapping(value = "/searchSupplierMasterData")
	public List<Supp_bussiness_partner> searchSupplierMasterData(@RequestParam(defaultValue = "") String supp_name,@RequestParam(defaultValue = "") String supp_group,
			@RequestParam(defaultValue = "") String supp_type,@RequestParam(defaultValue = "") String finyear,@RequestParam(defaultValue = "") String company_name)
	 {
		System.out.println("supp_name:"+supp_name);
		return supp_bussiness_partnerService.searchSupplierMasterData(supp_name,supp_group,supp_type,finyear,company_name);
	 }

	/******************* Supplier Bussiness Partner Master End *******************/

	/******************** Transporter Bussiness Partner Master Start *******************/
	 
	 
	@Autowired
	Trans_bussiness_partnerService trans_bussiness_partnerService;
	
	@GetMapping("/getTransSequenceId")
	public ResponseEntity<SequenceIdDTO> getTransSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = trans_bussiness_partnerService.getTransSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	/*@PostMapping("/createTransporterBussinessPartner")
	public Trans_bussiness_partner save(@Valid @RequestBody Trans_bussiness_partner trans_bussiness_partner) {
		return trans_bussiness_partnerService.save(trans_bussiness_partner);
	}*/
	@PostMapping("/createTransporterBussinessPartner")
	public Trans_bussiness_partner savetransporter(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		//System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Trans_bussiness_partner trans_bussiness_partner=objectMapper.readValue(input, Trans_bussiness_partner.class);
		
		return trans_bussiness_partnerService.save(trans_bussiness_partner,files);
	}

	/*@PutMapping("/updateTransBussinessPartner/{id}")
	public ResponseEntity<Trans_bussiness_partner> updateTransBussinessPartner(@PathVariable(value = "id") long id,
			@Valid @RequestBody Trans_bussiness_partner iMaster) {
		Trans_bussiness_partner op = trans_bussiness_partnerService.update(iMaster, id);
		return ResponseEntity.ok().body(op);
	}*/
	
	@PostMapping("/updateTransBussinessPartner")
	public Trans_bussiness_partner updateTransporter(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
		System.out.println(" in put check  ");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Trans_bussiness_partner iMaster=objectMapper.readValue(input, Trans_bussiness_partner.class);
		
		return trans_bussiness_partnerService.update(iMaster,files);
	}
	@PutMapping("/deleteTransBussinessPartner/{id}")
	public ResponseEntity<Trans_bussiness_partner> deleteTransBussinessPartner(@PathVariable(value = "id") long id,
			@Valid @RequestBody Trans_bussiness_partner trans_bussiness_partner) {
		Trans_bussiness_partner op = trans_bussiness_partnerService.deleteTransBussinessPartner(trans_bussiness_partner, id);
		return ResponseEntity.ok().body(op);
	}

	@GetMapping("/getTransporterBussinessPartner")
	public List<Trans_bussiness_partner> findTransBussinessPartnerAll() {
		return trans_bussiness_partnerService.findAll();
	}
	
	@GetMapping("/getTransporterBussinessPartnerFast")
	public List<Map<String,Object>> getTransporterBussinessPartnerFast() {
		return trans_bussiness_partnerService.getTransporterBussinessPartnerFast();
	}
	
	@GetMapping("/transporterownlist/{translist}")
	public List<Trans_bussiness_partner> transporterownlist(@PathVariable(value = "translist") String translist) {
		return trans_bussiness_partnerService.transporterownlist(translist);
	}

	@GetMapping("/findTransporters/{searchtext}")
	public List<Trans_bussiness_partner> findTransporters(@PathVariable(value = "searchtext") String searchtext) {

		return trans_bussiness_partnerService.findTransporters(searchtext);
	}

	@GetMapping("/getTransporterBrokerList/{code}")
	public List<Trans_bussiness_partner_brokerDTO> getTransporterBrokerList(@PathVariable(value = "code") String code) {
		return trans_bussiness_partnerService.getTransporterBrokerList(code);
	}

	@GetMapping("/getTransporterBrokers/{vcode}")
	public List<Trans_bussiness_partner_brokerDTO> getTransporterBrokers(@PathVariable(value = "vcode") String vcode) {
		return trans_bussiness_partnerService.getTransporterBrokers(vcode);
	}
	
	@GetMapping("/chkTransBpCodeStatus")
	public ResponseEntity<StatusDTO> chkTransBpCodeStatus(@RequestParam(defaultValue = "") String code) 
	{
		StatusDTO val=trans_bussiness_partnerService.chkTransBpCodeStatus(code);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}
	
	@GetMapping("/accountPostingTransporter/{id}")
	public Trans_bussiness_partner accountPostingTransporter(@PathVariable(value = "id") Long id) {
		
			return trans_bussiness_partnerService.accountPostingTransporter(id);
		
	}
	

	/*******************
	 * Transporter Bussiness Partner Master End
	 *******************/

	/*******************
	 * Other Partner Bussiness Partner Master Start
	 *******************/
	@Autowired
	Op_bussiness_partnerService op_bussiness_partnerService;

	@PostMapping("/createOtherPartnerBussinessPartner")
	public Op_bussiness_partner save(@Valid @RequestBody Op_bussiness_partner op_bussiness_partner) {
		return op_bussiness_partnerService.save(op_bussiness_partner);
	}

	@GetMapping("/getOtherPartnerBussinessPartner")
	public List<Op_bussiness_partner> findOPBussinessPartnerAll() {
		return op_bussiness_partnerService.findAll();
	}

	@GetMapping("/retriveOtherPartner/{id}")
	public ResponseEntity<Op_bussiness_partner> getPartner(@PathVariable(value = "id") Long id) {
		Op_bussiness_partner op = op_bussiness_partnerService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@GetMapping("/oBPAddressRetriveList/{code}")
	public Op_bussiness_partner_addressDTO oBPAddressRetriveList(@PathVariable(value = "code") String code) {
		return op_bussiness_partnerService.oBPAddressRetriveList(code);
	}

	@GetMapping("/oBPBillAddressRetriveList/{code}")
	public Op_bussiness_partner_bill_addrDTO oBPBillAddressRetriveList(@PathVariable(value = "code") String code) {
		return op_bussiness_partnerService.oBPBillAddressRetriveList(code);
	}

	@GetMapping("/oBPDelvFromRetriveList/{code}")
	public List<Op_bussiness_partner_delv_fromDTO> oBPDelvFromRetriveList(@PathVariable(value = "code") String code) {
		return op_bussiness_partnerService.oBPDelvFromRetriveList(code);
	}

	@GetMapping("/oBPAccountRetriveList/{code}")
	public Op_bussiness_partner_accontDTO oBPAccountRetriveList(@PathVariable(value = "code") String code) {
		return op_bussiness_partnerService.oBPAccountRetriveList(code);
	}

	@GetMapping("/oBPStatutoryRetriveList/{code}")
	public Op_bussiness_partner_statutoryDTO oBPStatutoryRetriveList(@PathVariable(value = "code") String code) {
		return op_bussiness_partnerService.oBPStatutoryRetriveList(code);
	}

	@GetMapping("/oBPBrokerRetriveList/{code}")
	public List<Op_bussiness_partner_brokerDTO> oBPBrokerRetriveList(@PathVariable(value = "code") String code) {
		return op_bussiness_partnerService.oBPBrokerRetriveList(code);
	}

	/*******************
	 * Other Partner Bussiness Partner Master End
	 *******************/

	/******************* Other Partner Group Master Start *******************/
	@Autowired
	Otherpartner_groupService otherpartner_groupService;

	@PostMapping("/createOtherPartnerGroup")
	public Otherpartner_group save(@Valid @RequestBody Otherpartner_group otherpartner_group) {
		return otherpartner_groupService.save(otherpartner_group);
	}

	@GetMapping("/getOtherPartnerGroup")
	public List<Otherpartner_group> findOPGroupAll() {
		return otherpartner_groupService.findAll();
	}

	@GetMapping("/retriveOtherPartGroup/{id}")
	public ResponseEntity<Otherpartner_group> getOtherPGrp(@PathVariable(value = "id") Long id) {
		Otherpartner_group op = otherpartner_groupService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	/******************* Other Partner Group Master End *******************/

	/***************** Channel Partner ********************/

	@Autowired
	Channel_partnerService channel_partnerService;

	@PostMapping("/createChannel")
	public Channel_partner save(@Valid @RequestBody Channel_partner cPartner) {
		return channel_partnerService.saveChannelPartner(cPartner);
	}

	@GetMapping("/getChannel")
	public List<Channel_partner> findAllcPartners() {
		return channel_partnerService.findAllcPartners();
	}

	@GetMapping("/retriveChannelPartner/{id}")
	public ResponseEntity<Channel_partner> getChnlP(@PathVariable(value = "id") Long id) {
		Channel_partner op = channel_partnerService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@GetMapping("/channelPRetriveList/{cp_id}")
	public List<Channel_partner_detailsDTO> channelPRetriveList(@PathVariable(value = "cp_id") String cp_id) {
		return channel_partnerService.channelPRetriveList(cp_id);
	}

	@PutMapping("/updateChannelP/{id}")
	public ResponseEntity<Channel_partner> updateChannelP(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Channel_partner channel_partner) {
		Channel_partner op = channel_partnerService.update(channel_partner, id);
		return ResponseEntity.ok().body(op);
	}

	/****************** Channel Partner End *****************/

	/***************** Broker Master Partner ********************/

	@Autowired
	Broker_masterService broker_masterService;
	  
	@GetMapping("/getBrokerSequenceId")
	public ResponseEntity<SequenceIdDTO> getBrokerSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String wtype)
	{
		SequenceIdDTO list = broker_masterService.getBrokerSequenceId(prefix,company,wtype);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

//	@PostMapping("/createBroker")
//	public Broker_master save(@Valid @RequestBody Broker_master broker_master) {
	//	return broker_masterService.save(broker_master);
	//}

	
	
	@PostMapping("/createBroker")
	public Broker_master brokersave(@RequestParam(required=false,value="files") MultipartFile files[],@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
	//	//System.out.println(" in put check  "+ input);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		
		Broker_master broker_master=objectMapper.readValue(input, Broker_master.class);
		
		return broker_masterService.save(broker_master,files);
	}
	
	
	
	
	
	@GetMapping("/getBrokers")
	public List<Broker_master> findBrokerAll() {
		return broker_masterService.findAll();
	}
	
	@GetMapping("/getBrokersFastApi/{company}")
	public List<Map<String,Object>> getBrokersFastApi(@PathVariable(value = "company") String company) {
		return broker_masterService.getBrokersFastApi(company);
	}
	
	@GetMapping("/findBrokers/{searchtext}")
	public List<Broker_master> findBrokers(@PathVariable(value = "searchtext") String searchtext) {

		return broker_masterService.findBrokers(searchtext);
	}

	@PutMapping("/updateBrokerMaster/{id}")
	public ResponseEntity<Broker_master> updateBrokerMaster(@PathVariable(value = "id") long id,
			@Valid @RequestBody Broker_master iMaster) {
		Broker_master op = broker_masterService.update(iMaster, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteBrokerMaster/{id}")
	public ResponseEntity<Broker_master> deleteBrokerMaster(@PathVariable(value = "id") long id,
			@Valid @RequestBody Broker_master broker_master) {
		Broker_master op = broker_masterService.deleteBrokerMaster(broker_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/chkBrokerMasterCodeStatus")
	public ResponseEntity<StatusDTO> chkBrokerMasterCodeStatus(@RequestParam(defaultValue = "") String code) 
	{
		StatusDTO val=broker_masterService.chkBrokerMasterCodeStatus(code);
		if(val==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(val);
		}
	}

	@GetMapping("/accountpostingBrokerMaster/{id}")
	public Broker_master accountpostingBrokerMaster(@PathVariable(value = "id") Long id) {
		return broker_masterService.accountpostingBrokerMaster(id);
	}
	
	@GetMapping("/accountpostingUndoBrokerMaster/{id}")
	public Broker_master accountpostingUndoBrokerMaster(@PathVariable(value = "id") Long id) {
		return broker_masterService.accountpostingUndoBrokerMaster(id);
	}
	
	/****************** Broker Master End *****************/

	/***************** Country Master ********************/

	@Autowired
	Country_masterService country_masterService;

	@PostMapping("/createCountry")
	public Country_master save(@Valid @RequestBody Country_master country_master) {
		return country_masterService.save(country_master);
	}

	@GetMapping("/getCountry")
	public List<Country_master> findCountryAll() {
		return country_masterService.findAll();
	}

	/****************** Country Master End *****************/

	/***************** State Master ********************/

	@Autowired
	State_masterService state_masterService;

	@PostMapping("/createState")
	public State_master save(@Valid @RequestBody State_master state_master) {
		return state_masterService.save(state_master);
	}

	@GetMapping("/getState")
	public List<State_master> findStateAll() {
		return state_masterService.findAll();
	}

	@GetMapping("/stateRetrive/{id}")
	public ResponseEntity<State_master> stateRetrive(@PathVariable(value = "id") Long id) {
		State_master tgs = state_masterService.findOne(id);
		if (tgs == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(tgs);
		}
	}

	@PutMapping("/updateState/{id}")
	public ResponseEntity<State_master> updateState(@PathVariable(value = "id") long id,
			@Valid @RequestBody State_master state_master) {
		State_master op = state_masterService.update(state_master, id);
		return ResponseEntity.ok().body(op);
	}

	/****************** State Master End *****************/

	/***************** District Master ********************/

	@Autowired

	District_masterService district_masterService;

	@PostMapping("/createDistrict")
	public District_master save(@Valid @RequestBody District_master district_master) {
		return district_masterService.save(district_master);
	}

	@GetMapping("/getDistrict")
	public List<District_master> findDistrictAll() {
		return district_masterService.findAll();
	}

	@GetMapping("/retriveDistrict/{id}")
	public ResponseEntity<District_master> getDistrictMaster(@PathVariable(value = "id") Long id) {
		District_master op = district_masterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateDistrict/{id}")
	public ResponseEntity<District_master> updateDistrict(@PathVariable(value = "id") Long id,
			@Valid @RequestBody District_master district_master) {
		District_master op = district_masterService.update(district_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteDistrict/{id}")
	public ResponseEntity<District_master> deleteDistrict(@PathVariable(value = "id") Long id,
			@Valid @RequestBody District_master district_master) {
		District_master op = district_masterService.deleteDistrict(district_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findDistricts")
	public ResponseEntity<List<District_master>> findDistricts(@RequestParam(defaultValue = "") String searchtext) {
		List<District_master> dList= district_masterService.findDistricts(searchtext);
		if (dList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(dList);
		}
	}
	
	/****************** District Master End *****************/

	/***************** City Master ********************/
	
	
	@Autowired

	City_masterService city_masterService;

	@PostMapping("/createCity")
	public City_master save(@Valid @RequestBody City_master city_master) {
		return city_masterService.save(city_master);
	}

	@GetMapping("/getCity")
	public List<City_master> findCityAll() {
		return city_masterService.findAll();
	}

	@GetMapping("/retriveCity/{id}")
	public ResponseEntity<City_master> getCityMaster(@PathVariable(value = "id") Long id) {
		City_master op = city_masterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateCity/{id}")
	public ResponseEntity<City_master> updateCity(@PathVariable(value = "id") long id,
			@Valid @RequestBody City_master city_master) {
		City_master op = city_masterService.update(city_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteCity/{id}")
	public ResponseEntity<City_master> deleteCity(@PathVariable(value = "id") long id,
			@Valid @RequestBody City_master city_master) {
		City_master op = city_masterService.deleteCity(city_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findCities")
	public ResponseEntity<List<City_master>> findCities(@RequestParam(defaultValue = "") String searchtext) {
		List<City_master> cList= city_masterService.findCities(searchtext);
		if (cList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(cList);
		}
	}

	/****************** City Master End *****************/
	/****************** Post Master ********************/
	@Autowired
	Post_office_masterService post_office_masterService;
	
	@PostMapping("/createPostOffice")
	public Post_office_master save(@Valid @RequestBody Post_office_master post_office) {
		return post_office_masterService.save(post_office);
	}
	
	@GetMapping("/getPostOffice")
	public List<Post_office_master> findAllPostOffice() {
		return post_office_masterService.findAll();
	}
	
	@GetMapping("/retrivePostOffice/{id}")
	public ResponseEntity<Post_office_master> getPostMaster(@PathVariable(value = "id") Long id) {
		Post_office_master op = post_office_masterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}
	
	@PutMapping("/updatePostOffice/{id}")
	public ResponseEntity<Post_office_master> updatePostOffice(@PathVariable(value = "id") long id,
			@Valid @RequestBody Post_office_master post_master) {
		Post_office_master op = post_office_masterService.update(post_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deletePostOffice/{id}")
	public ResponseEntity<Post_office_master> deletePostOffice(@PathVariable(value = "id") long id,
			@Valid @RequestBody Post_office_master post_master) {
		Post_office_master op = post_office_masterService.deletePostOffice(post_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/getPostOfficeThruDist")
	public ResponseEntity<List<Post_office_masterDTO>> getPostOfficeThruDist(@RequestParam(defaultValue = "") String distid) {
		List<Post_office_masterDTO> poList= new ArrayList<>();
		Post_office_masterDTO def=new Post_office_masterDTO("0","Choose an option", 0, "0", "0", "0");
		poList.add(def);
		poList.addAll(post_office_masterService.getPostOfficeThruDist(distid));
		
		return ResponseEntity.ok().body(poList);
	}
	
	@GetMapping("/getPincodeThruPO")
	public ResponseEntity<Post_office_masterDTO> getPincodeThruPO(@RequestParam(defaultValue = "") String po) {
		Post_office_masterDTO postOff= post_office_masterService.getPincodeThruPO(po);
		if (postOff == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(postOff);
		}
	}
	
	@GetMapping("/findPostOffice")
	public ResponseEntity<List<Post_office_masterDTO>> findPostOffice(@RequestParam(defaultValue = "") String pincode,@RequestParam(defaultValue = "") String dist) {
		List<Post_office_masterDTO> poList= new ArrayList<>();
		Post_office_masterDTO def=new Post_office_masterDTO("0","Choose an option", 0, "0", "0", "0");
		poList.add(def);
		poList.addAll(post_office_masterService.findPOThruPincode(pincode,dist));
		
		return ResponseEntity.ok().body(poList);
	}
	
	@GetMapping("/findAllPostOffice")
	public ResponseEntity<List<Post_office_master>> findAllPostOffice(@RequestParam(defaultValue = "") String searchtext) {
		List<Post_office_master> cList= post_office_masterService.findPostOffice(searchtext);
		if (cList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(cList);
		}
	}
	
	/*************************** End Post Master ****************************/
	
	@Autowired
	Qc_rules_setupService qc_rules_setupService;
	
	@GetMapping("/getQcRulesSequenceId")
	public ResponseEntity<SequenceIdDTO> getQcRulesSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = qc_rules_setupService.getQcRulesSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createQc_rules_setup")
	public Qc_rules_setup save(@Valid @RequestBody Qc_rules_setup qc_rules_setup) {
		return qc_rules_setupService.save(qc_rules_setup);
	}

	@GetMapping("/getQc_rules_setup")
	public List<Qc_rules_setup> findQcRulesSetupAll() {
		return qc_rules_setupService.findAll();
	}

	@GetMapping("/retriveQcRuleSetup/{id}")
	public ResponseEntity<Qc_rules_setup> retriveQcRuleSetup(@PathVariable(value = "id") Long id) {
		Qc_rules_setup op = qc_rules_setupService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@GetMapping("/qcRuleSetupRetriveList/{q_id}")
	public List<Qc_rules_setup_dtlsDTO> qcRuleSetupRetriveList(@PathVariable(value = "q_id") String q_id) {
		return qc_rules_setupService.qcRulesRetriveList(q_id);
	}
	
	@GetMapping("/getQCRuleSetupDtls/{q_id}")
	public Qc_rules_setupDTO getQCRuleSetupDtls(@PathVariable(value = "q_id") String q_id) {
		return qc_rules_setupService.getQCRuleSetupDtls(q_id);
	}

	@PutMapping("/updateQcRuleSetup/{id}")
	public ResponseEntity<Qc_rules_setup> updateQcRuleSetup(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Qc_rules_setup qc_rules_setup) {
		Qc_rules_setup op = qc_rules_setupService.update(qc_rules_setup, id);
		return ResponseEntity.ok().body(op);
	}
	
	
	@PutMapping("/deleteQcRulesSetup/{id}")
	public ResponseEntity<Qc_rules_setup> deleteQcRulesSetup(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Qc_rules_setup QCRS) {
		Qc_rules_setup op = qc_rules_setupService.deleteQcRulesSetup(QCRS, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findQcRulesSetup")
	public ResponseEntity<List<Qc_rules_setup>> findQcRulesSetup(@RequestParam(defaultValue = "") String searchtext) {
		List<Qc_rules_setup> QCRList= qc_rules_setupService.findQcRulesSetup(searchtext);
		if (QCRList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(QCRList);
		}
	}

	/****************** InvType Master START *****************/
	@Autowired
	Invoice_typeService invoice_typeService;
	
	@GetMapping("/getInvtypeSequenceId")
	public ResponseEntity<SequenceIdDTO> getInvtypeSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = invoice_typeService.getInvtypeSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createInvType")
	public Invoice_type save(@Valid @RequestBody Invoice_type invoice_type) {
		return invoice_typeService.save(invoice_type);
	}

	@GetMapping("/getInvTypes")
	public List<Invoice_type> findInvTypesAll() {
		return invoice_typeService.findAll();
	}

	@GetMapping("/getSalesInvTypes")
	public List<Invoice_typeDTO> supplierGroupList() {
		List<Invoice_typeDTO> typeList = new ArrayList<Invoice_typeDTO>();
		Invoice_typeDTO def = new Invoice_typeDTO(0L, "0", "0", "0", "Choose an Option");
		typeList.add(def);
		typeList.addAll(invoice_typeService.getSalesInvTypes());
		return typeList;
	}
	
	@GetMapping("/getSalesInvTypesFast")
	public List<Map<String,Object>> getSalesInvTypesFast() {
		return invoice_typeService.getSalesInvTypesFast();
	}
	
	@GetMapping("/retriveInvType/{id}")
	public ResponseEntity<Invoice_type> retriveInvType(@PathVariable(value = "id") Long id) {
		Invoice_type op = invoice_typeService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateInvType/{id}")
	public ResponseEntity<Invoice_type> updateInvType(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Invoice_type invoice_type) {
		Invoice_type op = invoice_typeService.update(invoice_type, id);
		return ResponseEntity.ok().body(op);
	}

	@PutMapping("/deleteInvoiceType/{id}")
	public ResponseEntity<Invoice_type> deleteInvoiceType(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Invoice_type invoice_type) {
		Invoice_type op = invoice_typeService.deleteInvoiceType(invoice_type, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findInvoiceType")
	public ResponseEntity<List<Invoice_type>> findInvoice_type(@RequestParam(defaultValue = "") String searchtext) {
		List<Invoice_type> invList= invoice_typeService.findInvoice_type(searchtext);
		if (invList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(invList);
		}
	}
	/******************* Screen Master Start *************************/

	@Autowired
	Screen_masterService screen_masterService;

	@PostMapping("/createScreenMaster")
	public Screen_master save(@Valid @RequestBody Screen_master screen_master) {
		return screen_masterService.save(screen_master);
	}

	@GetMapping("/getScreenMaster")
	public List<Screen_master> findScreen_masterAll() {
		return screen_masterService.findAll();
	}

	@GetMapping("/retriveScreen/{id}")
	public ResponseEntity<Screen_master> getScreenMaster(@PathVariable(value = "id") Long id) {
		Screen_master op = screen_masterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateScreen/{id}")
	public ResponseEntity<Screen_master> updateScreen(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Screen_master screen_master) {
		Screen_master op = screen_masterService.update(screen_master, id);
		return ResponseEntity.ok().body(op);
	}
    
	@PutMapping("/deleteScreen/{id}")
	public ResponseEntity<Screen_master> deleteScreen(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Screen_master screen_master) {
		Screen_master op = screen_masterService.deleteScreen(screen_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findScreen")
	public ResponseEntity<List<Screen_master>> findScreen(@RequestParam(defaultValue = "") String searchtext) {
		List<Screen_master> screenList= screen_masterService.findScreen(searchtext);
		if (screenList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(screenList);
		}
	}
	/******************* Screen Master End *************************/

	/******************* Reason Master Start *************************/

	@Autowired
	Reason_masterService reason_masterService;

	@PostMapping("/createReasonMaster")
	public Reason_master save(@Valid @RequestBody Reason_master reason_master) {
		return reason_masterService.save(reason_master);
	}

	@GetMapping("/getReasonMaster")
	public List<Reason_master> findReason_masterAll() {
		return reason_masterService.findAll();
	}

	@GetMapping("/retriveReason/{id}")
	public ResponseEntity<Reason_master> getReasonMaster(@PathVariable(value = "id") Long id) {
		Reason_master op = reason_masterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateReason/{id}")
	public ResponseEntity<Reason_master> updateReason(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Reason_master reason_master) {
		Reason_master op = reason_masterService.update(reason_master, id);
		return ResponseEntity.ok().body(op);
	}
   
	@PutMapping("/deleteReason/{id}")
	public ResponseEntity<Reason_master> deleteReason(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Reason_master reason_master) {
		Reason_master op = reason_masterService.deleteReason(reason_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findReason")
	public ResponseEntity<List<Reason_master>> findReason(@RequestParam(defaultValue = "") String searchtext) {
		List<Reason_master> reasonList= reason_masterService.findReason(searchtext);
		if (reasonList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(reasonList);
		}
	}
	
	/******************* Reason Master End *************************/

	/******************* TDS Master Start *************************/

	@Autowired
	Tds_masterService tds_masterService;
	
	/*
	 * @GetMapping("/getItemSequenceId") public ResponseEntity<SequenceIdDTO>
	 * getTdsSequenceId(@RequestParam(defaultValue = "") String
	 * prefix,@RequestParam(defaultValue = "") String company) { SequenceIdDTO list
	 * = tds_masterService.getTdsSequenceId(prefix,company);
	 * 
	 * return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(),
	 * HttpStatus.OK); }
	 */

	@PostMapping("/createTdsMaster")
	public Tds_master save(@Valid @RequestBody Tds_master tds_master) {
		return tds_masterService.save(tds_master);
	}

	@GetMapping("/getTdsMaster")
	public List<Tds_master> findTdsMasterAll() {
		return tds_masterService.findAll();
	}
	
	
	@GetMapping("/gettdsdetails/{taxcode}")
	Tds_master gettdsdetails(@PathVariable(value = "taxcode") String taxcode)
	{
		return tds_masterService.gettdsdetails(taxcode);
	}
	
	
	
	@GetMapping("/retriveTdsMaster/{id}")
	public ResponseEntity<Tds_master> retriveTdsMaster(@PathVariable(value = "id") Long id) {
		Tds_master op = tds_masterService.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateTdsMaster/{id}")
	public ResponseEntity<Tds_master> updateTdsMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Tds_master tds_master) {
		Tds_master op = tds_masterService.update(tds_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteTds/{id}")
	public ResponseEntity<Tds_master> deleteTds(@PathVariable(value = "id") long id,
			@Valid @RequestBody Tds_master tds_master) {
		Tds_master op = tds_masterService.deleteTds(tds_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findTds")
	public List<Tds_master> findTds(@RequestParam(defaultValue = "") String searchtext,@RequestParam(defaultValue = "") String company) {
		return tds_masterService.findTds(searchtext, company);
	}


	/******************* TDS Master End *************************/
	/*
	 * @Autowired UserService userService;
	 * 
	 * @RequestMapping(value = "/download", method = RequestMethod.GET) public
	 * String download(Model model) { model.addAttribute("users",
	 * userService.findAllUsers()); return ""; }
	 */

//*************************** charges master starts*****************************

	@Autowired
	Charge_MasterService charge_masterservice;

	@PostMapping("/createChargemaster")
	public ResponseEntity<Charge_Master> save(@Valid @RequestBody Charge_Master charge_master) {
		charge_masterservice.save(charge_master);

		return new ResponseEntity<Charge_Master>(charge_master, HttpStatus.OK);
	}

	@GetMapping("/getCharges")
	public List<Charge_Master> getCharges() {
		return charge_masterservice.findAll();
	}

	@GetMapping("/retriveChargesMaster/{id}")
	public ResponseEntity<Charge_Master> getChMaster(@PathVariable(value = "id") Long id) {
		Charge_Master op = charge_masterservice.findOne(id);
		if (op == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@GetMapping("/chargeMasterRetriveList/{c_id}")
	public List<Charge_MasterdtlsDTO> chargeMasterRetriveList(@PathVariable(value = "c_id") String c_id) {
		return charge_masterservice.chargeRetriveList(c_id);
	}

	@PutMapping("/updateChargemaster/{id}")
	public ResponseEntity<Charge_Master> updateChargemaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Charge_Master charge_Master) {
		Charge_Master op = charge_masterservice.update(charge_Master, id);
		return ResponseEntity.ok().body(op);
	}
	
	
	@PutMapping("/deleteChargeMaster/{id}")
	public ResponseEntity<Charge_Master> deleteCharge(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Charge_Master charge_Master) {
		Charge_Master op = charge_masterservice.deleteCharge(charge_Master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findChargeMaster")
	public ResponseEntity<List<Charge_Master>> findChargeMaster(@RequestParam(defaultValue = "") String searchtext) {
		List<Charge_Master> chargeList= charge_masterservice.findChargeMaster(searchtext);
		if (chargeList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(chargeList);
		}
	}

	/*************** Test **************/
	/*
	 * @Autowired TestusersService testusersService;
	 * 
	 * @PostMapping("/createTestUser") public ResponseEntity<Testusers>
	 * save(@Valid @RequestBody Testusers testusers) {
	 * testusersService.Usersave(testusers);
	 * 
	 * return new ResponseEntity<Testusers>(testusers, HttpStatus.OK); }
	 */

	/*************** Test end **************/

	/*************** Wm_charges_master start **************/

	@Autowired
	Wm_charges_masterService wm_charges_masterService;
	
	@GetMapping("/getWnChargesSequenceId")
	public ResponseEntity<SequenceIdDTO> getWnChargesSequenceId(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = wm_charges_masterService.getWnChargesSequenceId(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createWeighmentChargeMaster")
	public Wm_charges_master save(@Valid @RequestBody Wm_charges_master wm_charges_master) {
		return wm_charges_masterService.save(wm_charges_master);
	}

	@GetMapping("/getWeighmentChargeMasters")
	public List<Wm_charges_master> findWeighmentChargeMasters() {
		return wm_charges_masterService.findAll();
	}

	@GetMapping("/getWeighmentCharges")
	public List<Wm_charges_masterDTO> getWeighmentCharges() {
		return wm_charges_masterService.getWeighmentCharges();
	}

	@GetMapping("/getWeighmentChargeMasters/{wm_charge_id}")
	public Wm_charges_masterDTO getWeighmentChargeMasters(@PathVariable(value = "wm_charge_id") String wm_charge_id) {
		return wm_charges_masterService.getWeighmentChargeMasters(wm_charge_id);
	}

	@GetMapping("/getWeighmentChargeThruVtype/{vehicletype}")
	public Wm_charges_masterDTO getWeighmentChargeThruVtype(@PathVariable(value = "vehicletype") String vehicletype) {
		return wm_charges_masterService.getWeighmentChargeThruVtype(vehicletype);
	}

	@GetMapping("/retriveWeighmentChargeMasters/{id}")
	public ResponseEntity<Wm_charges_master> retriveWeighmentChargeMasters(@PathVariable(value = "id") Long id) {
		Wm_charges_master op = wm_charges_masterService.findOne(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(op);
		}
	}

	@PutMapping("/updateWeighmentChargeMaster/{id}")
	public ResponseEntity<Wm_charges_master> updateWeighmentChargeMaster(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Wm_charges_master wm_charges_master) {
		Wm_charges_master op = wm_charges_masterService.update(wm_charges_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteWeighmentCharges/{id}")
	public ResponseEntity<Wm_charges_master> deleteWmCharges(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Wm_charges_master wm_charges_master) {
		Wm_charges_master op = wm_charges_masterService.deleteWmCharges(wm_charges_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/findWeighmentCharges")
	public ResponseEntity<List<Wm_charges_master>> findWmCharges(@RequestParam(defaultValue = "") String searchtext) {
		List<Wm_charges_master> chargeList= wm_charges_masterService.findWmCharges(searchtext);
		if (chargeList == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(chargeList);
		}
	}

	/*************** Wm_charges_master end **************/

	/******************* Sales Return Start **********************/
		@Autowired
		Return_approval_noteService return_approval_noteService;

		/* ReturnApprovalNote */
		
		@GetMapping("/getRANSequenceId/{fin_year}/{return_type}")
		public SalesSequenceIdDTO getRANSequenceId(@PathVariable(value = "fin_year") String fin_year,@PathVariable(value = "return_type") String return_type)
		{
			return return_approval_noteService.getRANSequenceId(fin_year,return_type);
		}
		
		@PostMapping("/createReturnApprovalNote")
		public Return_approval_note createReturnApprovalNote(@Valid @RequestBody Return_approval_note return_approval_note) {
			return return_approval_noteService.save(return_approval_note);
		}
	  
	    @GetMapping("/getReturnApprovalNote")
	    public ResponseEntity<List<Return_approval_note>> getReturnApprovalNote(@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear)
	    {
	    	List<Return_approval_note> list = return_approval_noteService.getReturnApprovalNote(company,finyear);
	    	
		    return new ResponseEntity<List<Return_approval_note>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @GetMapping("/getReturnAppNoteThruUnAdv")
	    public ResponseEntity<List<Return_approval_noteDTO>> getReturnAppNoteThruUnAdv(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String uldate,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear,@RequestParam(defaultValue = "") String party)
	    {
	    	List<Return_approval_noteDTO> list = return_approval_noteService.getReturnAppNoteThruUnAdv(bunit,uldate,company,finyear,party);
	    	
		    return new ResponseEntity<List<Return_approval_noteDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    
	    @GetMapping("/getReturnAppNoteThruUnAdvjobwork/{advice_date}/{bunit}/{party}")
	    public List<Map<String,Object>> getReturnAppNoteThruUnAdvjobwork(@PathVariable(value = "advice_date") String advice_date,@PathVariable(value = "bunit") String bunit,@PathVariable(value = "party") String party)
		{
			return return_approval_noteService.getReturnAppNoteThruUnAdvjobwork(advice_date,bunit,party);
		}
	    
	    @GetMapping("/getReturnAppNoteThruWe")
	    public ResponseEntity<List<Return_approval_noteDTO>> getReturnAppNoteThruWe(@RequestParam(defaultValue = "") String invtype,@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String party,@RequestParam(defaultValue = "") String srdate,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear)
	    {
	    	List<Return_approval_noteDTO> list = return_approval_noteService.getReturnAppNoteThruWe(invtype,bunit,party,srdate,company,finyear);
	    	
		    return new ResponseEntity<List<Return_approval_noteDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @GetMapping("/getReturnAppNoteThruWejobwork/{date}/{bunit}/{party}")
	    public List<Map<String,Object>> getReturnAppNoteThruWejobwork(@PathVariable(value = "date") String date,@PathVariable(value = "bunit") String bunit,@PathVariable(value = "party") String party)
		{
			return return_approval_noteService.getReturnAppNoteThruWejobwork(date,bunit,party);
		}
	  
	    
	    @GetMapping("/getRtnAppNoteLowRate")
	    public ResponseEntity<List<Return_approval_noteDTO>> getRtnAppNoteLowRate(@RequestParam(defaultValue = "") String bunit,@RequestParam(defaultValue = "") String party,@RequestParam(defaultValue = "") String invdate,@RequestParam(defaultValue = "") String company,@RequestParam(defaultValue = "") String finyear,@RequestParam(defaultValue = "") String invoicetype)
	    {
	    	////System.out.println("invoicetype1::"+invoicetype);
	    	List<Return_approval_noteDTO> list = return_approval_noteService.getRtnAppNoteLowRate(bunit,party,invdate,company,finyear,invoicetype);
	    	
		    return new ResponseEntity<List<Return_approval_noteDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @GetMapping("/getreturnapprovalnote_salesreturnupdate/{id}")
		public List<Return_approval_note> getreturnapprovalnote_salesreturnupdate(@PathVariable(value = "id") Long id)
		{
			return return_approval_noteService.getreturnapprovalnote_salesreturnupdate(id);
		}
	     
	    @GetMapping("/getRtnAppNoteLowRateUpdate/{id}")
	    public List<Return_approval_noteDTO> getRtnAppNoteLowRateUpdate(@PathVariable(value = "id") Long id)
	    {
		    return return_approval_noteService.getRtnAppNoteLowRateUpdate(id);
	    }
	    
	    @GetMapping("/retriveReturnApprovalNote/{id}")
		public ResponseEntity<Return_approval_note> retriveReturnApprovalNote(@PathVariable(value = "id") Long id) {
			Return_approval_note op = return_approval_noteService.findOne(id);
			if (id == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(op);
			}
		}
		
		@GetMapping("/getcreditnoteapproval/{id}")
		public List<Return_approval_note> getcreditnoteapproval(@PathVariable(value = "id") Long id)
		{
			return return_approval_noteService.getcreditnoteapproval(id);
		}
		//getRtnAppNoteLowRateitemdetals
		@GetMapping("/getRtnAppNoteLowRateitemdetals/{id}")
		public List<Return_approval_item_dtls> getRtnAppNoteLowRateitemdetals(@PathVariable(value = "id") Long id)
		{
			//System.out.println("/"+id);
			return return_approval_noteService.getRtnAppNoteLowRateitemdetals(id);
		}
		
		@GetMapping("/getRtnAppNoteLowRateitemdetals_returnapp/{salesreturnid}/{id}")
		public List<Return_approval_item_dtls> getRtnAppNoteLowRateitemdetals_returnapp(@PathVariable(value = "salesreturnid") String salesreturnid,@PathVariable(value = "id") Long id)
		{
			//System.out.println(salesreturnid + " here  " + id);
			return return_approval_noteService.getRtnAppNoteLowRateitemdetals_returnapp(salesreturnid,id);
		}
		
		@GetMapping("/getReturnApprovalDtls/{salesreturnid}")
		public Return_approval_noteDTO getReturnApprovalDtls(@PathVariable(value = "salesreturnid") String salesreturnid)
		{
			return return_approval_noteService.getReturnApprovalDtls(salesreturnid);
		}
		
		@GetMapping("/getReturnApprovalBD/{salesreturnid}")
		public List<Return_approval_broker_dtlsDTO> getReturnApprovalBD(@PathVariable(value = "salesreturnid") String salesreturnid)
		{
			return return_approval_noteService.getReturnApprovalBD(salesreturnid);
		}
		
		@GetMapping("/getReturnApprovalD/{salesreturnid}")
		public List<Return_approval_docsDTO> getReturnApprovalD(@PathVariable(value = "salesreturnid") String salesreturnid)
		{
			return return_approval_noteService.getReturnApprovalD(salesreturnid);
		}
		
		@GetMapping("/getReturnApprovalID/{salesreturnid}")
		public List<Return_approval_item_dtlsDTO> getReturnApprovalID(@PathVariable(value = "salesreturnid") String salesreturnid)
		{
			return return_approval_noteService.getReturnApprovalID(salesreturnid);
		}
		
		@GetMapping("/getReturnApprovalPD/{salesreturnid}")
		public List<Return_approval_party_dtlsDTO> getReturnApprovalPD(@PathVariable(value = "salesreturnid") String salesreturnid)
		{
			return return_approval_noteService.getReturnApprovalPD(salesreturnid);
		}
		
		@GetMapping("/getReturnApprovalSD/{salesreturnid}")
		public Return_approval_shipment_dtlsDTO getReturnApprovalSD(@PathVariable(value = "salesreturnid") String salesreturnid)
		{
			return return_approval_noteService.getReturnApprovalSD(salesreturnid);
		}
		
		@GetMapping("/getReturnApprovalTI/{salesreturnid}")
		public Return_approval_trans_infoDTO getReturnApprovalTI(@PathVariable(value = "salesreturnid") String salesreturnid)
		{
			return return_approval_noteService.getReturnApprovalTI(salesreturnid);
		}
		
		@GetMapping("/getReturnAppTransInfo/{salesreturnid}")
		public List<Invoice_Return_approval_trans_infoDTO> getReturnAppTransInfo(@PathVariable(value = "salesreturnid") String salesreturnid)
		{
			return return_approval_noteService.getReturnAppTransInfo(salesreturnid);
		}
		
		@GetMapping("/getReturnApprovalWD/{salesreturnid}")
		public Return_approval_weight_dtlDTO getReturnApprovalWD(@PathVariable(value = "salesreturnid") String salesreturnid)
		{
			return return_approval_noteService.getReturnApprovalWD(salesreturnid);
		}
		
	    @PutMapping("/updateReturnApprovalNote/{id}") 
	    public ResponseEntity<Return_approval_note> updateReturnApprovalNote(@PathVariable(value="id") long id,@Valid @RequestBody Return_approval_note return_approval_note)
	    {
		    Return_approval_note op=return_approval_noteService.update(return_approval_note,id);
		    return ResponseEntity.ok().body(op); 
	    }
	 

	/******************* Sales Return End **********************/
	    
	/******************* Sales Report master **********************/ 
	    @Autowired
	    Sales_reg_masterService sales_reg_masterService;

		@PostMapping("/createSalesRegMaster")
		public ResponseEntity<Sales_reg_master> save(@Valid @RequestBody Sales_reg_master sales_reg_master) {
			sales_reg_masterService.save(sales_reg_master);

			return new ResponseEntity<Sales_reg_master>(sales_reg_master, HttpStatus.OK);
		}
		
		@GetMapping("/retriveSalesRegister/{id}")
		public ResponseEntity<Sales_reg_master> retriveSalesRegister(@PathVariable(value = "id") Long id) {
			Sales_reg_master sales = sales_reg_masterService.findOne(id);
			if (id == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(sales);
			}
		}

		@PutMapping("/updateSalesRegMaster/{id}")
		public ResponseEntity<Sales_reg_master> SalesRegisterUpdate(@PathVariable(value = "id") Long id,@Valid @RequestBody Sales_reg_master sales_reg_master)
		{
			Sales_reg_master sales = sales_reg_masterService.update(sales_reg_master, id);
			return ResponseEntity.ok().body(sales);
		}
		
	
		@Autowired
		Purchase_reg_masterService purchase_reg_masterService;
		
		@PostMapping("/createPurchaseRegMaster")
		public ResponseEntity<Purchase_reg_master> save(@Valid @RequestBody Purchase_reg_master purchase_reg_master)
		{
			purchase_reg_masterService.save(purchase_reg_master);
			return new ResponseEntity<Purchase_reg_master>(purchase_reg_master, HttpStatus.OK);
		}
		
		@Autowired
		Sales_reg_dynamicService sales_reg_dynamicService;
		
		@PostMapping("/createSalesRegDynamic")
		public ResponseEntity<Sales_reg_dynamic> save(@Valid @RequestBody Sales_reg_dynamic sales_reg_dynamic) {
			sales_reg_dynamicService.save(sales_reg_dynamic);

			return new ResponseEntity<Sales_reg_dynamic>(sales_reg_dynamic, HttpStatus.OK);
		}
		
		@GetMapping("/retriveSalesRegDynamic/{id}")
		public ResponseEntity<Sales_reg_dynamic> retriveSalesRegDynamic(@PathVariable(value = "id") Long id) {
			Sales_reg_dynamic sales = sales_reg_dynamicService.findOne(id);
			if (id == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(sales);
			}
		}
		
		/*@Autowired
		Sales_inv_dynamic_sortingService sales_inv_dynamic_sortingService;
		
		@PostMapping("/createSalesInvDynamicSorting")
		public ResponseEntity<Sales_inv_dynamic_sorting> save(@Valid @RequestBody Sales_inv_dynamic_sorting sales_inv_dynamic_sorting) {
			sales_inv_dynamic_sortingService.save(sales_inv_dynamic_sorting);

			return new ResponseEntity<Sales_inv_dynamic_sorting>(sales_inv_dynamic_sorting, HttpStatus.OK);
		}*/
		
		@PutMapping("/updateSalesRegSort/{id}")
		public ResponseEntity<Sales_reg_dynamic> updateSalesRegSort(@PathVariable(value = "id") Long id,@Valid @RequestBody Sales_reg_dynamic sales_reg_dynamic)
		{
			Sales_reg_dynamic sales = sales_reg_dynamicService.update(sales_reg_dynamic, id);
			return ResponseEntity.ok().body(sales);
		}
		
		@PutMapping("/updateSalesRegSorting/{id}")
		public ResponseEntity<Sales_reg_dynamic> updateSalesRegSorting(@PathVariable(value = "id") Long id,@Valid @RequestBody Sales_reg_dynamic sales_reg_dynamic)
		{
			//System.out.println("check id::"+id);
			Sales_reg_dynamic sales = sales_reg_dynamicService.update2(sales_reg_dynamic, id);
			return ResponseEntity.ok().body(sales);
		}
		
		/*@PutMapping("/deleteSalesRegDynamic/{id}")
		public ResponseEntity<Sales_reg_dynamic> deleteSalesRegDynamic(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Sales_reg_dynamic sales_reg_dynamic) {
			//System.out.println("master id"+id);
			Sales_reg_dynamic op = sales_reg_dynamicService.deleteSalesRegDynamic(sales_reg_dynamic, id);
			return ResponseEntity.ok().body(op);
		}*/
		
		@GetMapping("/deleteSalesRegDynamic/{id}")
		public ResponseEntity<StatusDTO> deleteSalesRegDynamic(@PathVariable(value= "id") Long id)
		{
			StatusDTO op = sales_reg_dynamicService.deleteSalesRegDynamic(id);
			return ResponseEntity.ok().body(op);
		}
		@Autowired
		User_Role_AccessService user_Role_AccessService;
		
		@PostMapping("/createUserRoleAccess")
		public ResponseEntity<User_Role_Access> save(@Valid @RequestBody User_Role_Access user_Role_Access) {
			user_Role_AccessService.save(user_Role_Access);

			return new ResponseEntity<User_Role_Access>(user_Role_Access, HttpStatus.OK);
		}
		
		//gate pass check list  start 
		
		@Autowired
		GatepassChecklistService gatepassChecklistService;
		
		@Autowired
		GatepassGetinService gatepassGetinService;
		
		@Autowired
		GatepassGateoutAuthorizationService gatepassGateoutAuthorizationService;
		
		@Autowired
		GatepassGateoutService gatepassGateoutService;
		
		@PostMapping("/creategatepasschecklist")
		public GatepassChecklist creategatepasschecklist(@Valid @RequestBody GatepassChecklist gatepassChecklist) {
			return gatepassChecklistService.save(gatepassChecklist);
		}
		
		@GetMapping("/getGatepasschecklistin")
		public List<GatepassChecklist> getGatepasschecklistin()
		{
			return gatepassChecklistService.getGatepasschecklistin();
		}
		
		
		@GetMapping("/getGatepassgetin_List")
		public List<GatepassGetin> getGatepassgetin_List()
		{
			return gatepassGetinService.getGatepassgetin_List();
		}
		
		@GetMapping("/getGatepassgetouta_List")
		public List<GatepassGateoutAuthorization> getGatepassgetouta_List()
		{
			return gatepassGateoutAuthorizationService.getGatepassgetouta_List();
		}
		
		
		
		
		@GetMapping("/getgatepassimageimage/{doc_img}")
	    public ResponseEntity<?> getProfileImage(@PathVariable(value = "doc_img")  String doc_img) {        
	        try {
	            
	        	
	        	Path imagePath = Paths.get("D:/AayogAgroDocuments/getpassgetin/"+doc_img);
	        	//Path imagePath = Paths.get("C:/AayogAgroDocuments/getpassgetin/"+doc_img);
	            
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
		
		
		
		@GetMapping("/getGatepasschecklistout")
		public List<GatepassChecklist> getGatepasschecklistout()
		{
			return gatepassChecklistService.getGatepasschecklistout();
		}
		
		@GetMapping("/getVehicleListgatepassout")
		public List<GatepassGateoutAuthorization> getVehicleListgatepassout()
		{
			return gatepassGateoutService.getVehicleListgatepassout();
		}
		
		
		
		@GetMapping("/getVehicleListgatepassauth/{vehicle_id}")
		public GatepassGateoutAuthorization getVehicleListgatepassauth(@PathVariable(value = "vehicle_id") String vehicle_id)
		{
			return gatepassGateoutService.getVehicleListgatepassauth(vehicle_id);
		}
		
		
		@GetMapping("/getgatepassauthdetails/{authid}")
		public List<GatepassGateoutAuthorization_details> getgatepassauthdetails(@PathVariable(value = "authid") String authid)
		{
			return gatepassGateoutService.getgatepassauthdetails(authid);
		}
		
		
		@PostMapping("/creategatepassGetin")
		public GatepassGetin creategatepassGetin(@RequestParam(required=false,value="files") MultipartFile files,@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
			//System.out.println(" in put check  "+ input);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			
			GatepassGetin gatepassGetin=objectMapper.readValue(input, GatepassGetin.class);
			
			return gatepassGetinService.save(gatepassGetin,files);
		}
		
		
		@PostMapping("/updategatepassGetin")
		public GatepassGetin updategatepassGetin(@RequestParam(required=false,value="files") MultipartFile files,@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
			//System.out.println(" in put check  "+ input);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			
			GatepassGetin gatepassGetin=objectMapper.readValue(input, GatepassGetin.class);
			
			
			return gatepassGetinService.update(gatepassGetin,files);
		}
		
		@PostMapping("/creategatepassGetouta")
		public GatepassGateoutAuthorization creategatepassGetouta(@Valid @RequestBody GatepassGateoutAuthorization gatepassGateoutAuthorization) {
			return gatepassGateoutAuthorizationService.save(gatepassGateoutAuthorization);
		}
		
		
		@PutMapping("/updategatepassGetouta/{id}")
		public ResponseEntity<GatepassGateoutAuthorization> updategatepassGetouta(@PathVariable(value = "id") Long id,
				@Valid @RequestBody GatepassGateoutAuthorization gatepassGateoutAuthorization) {
			GatepassGateoutAuthorization op = gatepassGateoutAuthorizationService.update(gatepassGateoutAuthorization, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		
		@PostMapping("/creategatepassGetout")
		public GatepassGateout creategatepassGetout(@Valid @RequestBody GatepassGateout gatepassGateout) {
			return gatepassGateoutService.save(gatepassGateout);
		}

		@GetMapping("/gatoutList")
		public List<GatepassGateout> gatoutList() {
			
			return gatepassGateoutService.findAll();
		}
		
		/*@PutMapping("/updategatepassGetout/{id}")
		public ResponseEntity<GatepassGateout> updategatepassGetout(@PathVariable(value = "id") Long id,@Valid @RequestBody GatepassGateout gatepassGateout) {
			GatepassGateout op = gatepassGateoutService.update(gatepassGateout, id);
			return ResponseEntity.ok().body(op);

		}*/
		
		@GetMapping("/gatepassCheckList")
		public List<GatepassChecklist> gatepassCheckList() {
			
			return gatepassChecklistService.findAll();
		}
		
		@PutMapping("/updategatepassCheckList/{id}")
		public ResponseEntity<GatepassChecklist> updategatepassCheckList(@PathVariable(value = "id") Long id,@Valid @RequestBody GatepassChecklist gatepassChecklist) {
			GatepassChecklist op = gatepassChecklistService.update(gatepassChecklist, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		@GetMapping("/checkGatePassCheckListUsage/{code}")
		public ResponseEntity<StatusDTO> checkGatePassCheckListUsage(@PathVariable(value = "code") String code)
		{
			StatusDTO check=gatepassChecklistService.checkGatePassCheckListUsage(code);
			return ResponseEntity.ok().body(check);
		}
		
		@PutMapping("/deleteGatePassCheckList/{id}")
		public ResponseEntity<GatepassChecklist> deleteGatePassCheckList(@PathVariable(value = "id") Long id,
				@Valid @RequestBody GatepassChecklist gatepassChecklist) {
			GatepassChecklist op = gatepassChecklistService.deleteGatePassCheckList(gatepassChecklist, id);
			return ResponseEntity.ok().body(op);
		}
		
		
		//gate pass check list  Ends
		
		//Visitor master  starts
		
		@Autowired
		Visitor_masterService visitor_masterService;
		
		@PostMapping("/createVisitorMaster")
		public Visitor_master createVisitorMaster(@RequestParam(required=false,value="files") MultipartFile files,@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
			//System.out.println(" in put check  "+ input);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			
			Visitor_master visitor_master=objectMapper.readValue(input, Visitor_master.class);
			
			return visitor_masterService.save(visitor_master,files);
		}
		
		@GetMapping("/visitorsList")
		public List<Visitor_master> visitorsList() {
			
			return visitor_masterService.findAll();
		}

		@GetMapping("/retrieveVisitorById/{id}")
		public ResponseEntity<Visitor_master> retrieveVisitorById(@PathVariable(value="id") Long id)
		{
			Visitor_master op=visitor_masterService.findOne(id);
			if(id==null)
			{
				return ResponseEntity.notFound().build();
			}
			else
			{
				return ResponseEntity.ok().body(op);
			}
		}
		
		@GetMapping("/getVisitorsimageimage/{doc_img}")
	    public ResponseEntity<?> getProfileImg(@PathVariable(value = "doc_img")  String doc_img) {        
	        try {
	            
	        	
	        	Path imagePath = Paths.get("D:/AayogAgroDocuments/visitorimage/"+doc_img);
	        	//Path imagePath = Paths.get("C:/AayogAgroDocuments/getpassgetin/"+doc_img);
	            
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
		
		
		@PostMapping("/updateVisitorMaster")
		public Visitor_master updateVisitorMaster(@RequestParam(required=false,value="files") MultipartFile files,@Valid @RequestParam("Input") String input) throws JsonParseException,JsonMappingException,IOException {
			//System.out.println(" in put check  "+ input);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			
			Visitor_master visitor_master=objectMapper.readValue(input, Visitor_master.class);
			
			
			return visitor_masterService.update(visitor_master,files);
		}
		
		@GetMapping("/checkVisitorMasterDeletion/{visitor_id}")
		public StatusDTO checkVisitorMasterDeletion(@PathVariable(value= "visitor_id") String visitor_id)
		{
			return visitor_masterService.checkVisitorMasterDeletion(visitor_id);
		}
		
		@PutMapping("/deleteVisitorMaster/{id}")
		public ResponseEntity<Visitor_master> deleteVisitorMaster(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Visitor_master visitor_master) {
			Visitor_master op = visitor_masterService.deleteVisitorMaster(visitor_master, id);
			return ResponseEntity.ok().body(op);
		}
		
		//Visitor master  ends
		
		
		@GetMapping("/getTransporterimageimage/{doc_img}")
	    public ResponseEntity<?> getTransImg(@PathVariable(value = "doc_img")  String doc_img) {        
	        try {
	            
	        	
	        	Path imagePath = Paths.get("C:/AayogAgroDocuments/Transporter/"+doc_img);
	        	//Path imagePath = Paths.get("C:/AayogAgroDocuments/getpassgetin/"+doc_img);
	            
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
		
		
		/****************** Godown Master START *****************/
		@Autowired
		GodownMasterService godownMasterService;
		
		@GetMapping("/getGodownList")
		public List<GodownMaster> getGodownList() {
			return godownMasterService.findAll();
		}

		@PostMapping("/createGodownMaster")
		public GodownMaster save(@Valid @RequestBody GodownMaster godownMaster) {
			return godownMasterService.save(godownMaster);
		}


		@GetMapping("/retriveGodownMaster/{id}")
		public ResponseEntity<GodownMaster> retriveGodownMaster(@PathVariable(value = "id") Long id) {
			GodownMaster op = godownMasterService.findOne(id);
			if (op == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(op);
			}
		}

		@PutMapping("/updateGodownMaster/{id}")
		public ResponseEntity<GodownMaster> updateGodownMaster(@PathVariable(value = "id") Long id,
				@Valid @RequestBody GodownMaster godownMaster) {
			GodownMaster op = godownMasterService.update(godownMaster, id);
			return ResponseEntity.ok().body(op);
		}

		@PutMapping("/deleteGodownMaster/{id}")
		public ResponseEntity<GodownMaster> deleteGodownMaster(@PathVariable(value = "id") Long id,
				@Valid @RequestBody GodownMaster godownMaster) {
			GodownMaster op = godownMasterService.deleteGodownMaster(godownMaster, id);
			return ResponseEntity.ok().body(op);
		}
		
		@GetMapping("/findGodownMaster")
		public ResponseEntity<List<GodownMaster>> findGodownMaster(@RequestParam(defaultValue = "") String searchtext) {
			List<GodownMaster> godownList= godownMasterService.findGodownMaster(searchtext);
			if (godownList == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(godownList);
			}
		}
		
		@GetMapping("/getGodownMasterList/{company}")
	    public List<GodownMaster> getGodownMasterList(@PathVariable(value = "company") String company) 
		{
			System.out.println("company:"+company);
			return godownMasterService.getGodownMasterList(company);
		}
		
/******************* Godown Master Ends *************************/
/****************** HUB Master START *****************/
		@Autowired
		HubMasterService hubMasterService;
		
		@GetMapping("/getHubList")
		public List<HubMaster> getHubList() {
			return hubMasterService.findAll();
		}

		@PostMapping("/createHubMaster")
		public HubMaster save(@Valid @RequestBody HubMaster hubMaster) {
			return hubMasterService.save(hubMaster);
		}


		@GetMapping("/retriveHubMaster/{id}")
		public ResponseEntity<HubMaster> retriveHubMaster(@PathVariable(value = "id") Long id) {
			HubMaster op = hubMasterService.findOne(id);
			if (op == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(op);
			}
		}

		@PutMapping("/updateHubMaster/{id}")
		public ResponseEntity<HubMaster> updateHubMaster(@PathVariable(value = "id") Long id,
				@Valid @RequestBody HubMaster hubMaster) {
			HubMaster op = hubMasterService.update(hubMaster, id);
			return ResponseEntity.ok().body(op);
		}

		@PutMapping("/deleteHubMaster/{id}")
		public ResponseEntity<HubMaster> deleteHubMaster(@PathVariable(value = "id") Long id,
				@Valid @RequestBody HubMaster hubMaster) {
			HubMaster op = hubMasterService.deleteHubMaster(hubMaster, id);
			return ResponseEntity.ok().body(op);
		}
		
		@GetMapping("/findHubMaster")
		public ResponseEntity<List<HubMaster>> findHubMaster(@RequestParam(defaultValue = "") String searchtext) {
			List<HubMaster> hubList= hubMasterService.findHubMaster(searchtext);
			if (hubList == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(hubList);
			}
		}
		
		@GetMapping("/getHubMasterList/{company}")
	    public List<HubMaster> getHubMasterList(@PathVariable(value = "company") String company) 
		{
			System.out.println("company:"+company);
			return hubMasterService.getHubMasterList(company);
		}
/******************* HUB Master Ends *************************/
		
/*************************************************************Seives Master Starts here***************************************/
		@Autowired
		Seives_masterService seives_masterService;
		
		@GetMapping("/getSeiveslist")
		public List<Seives_master> getSeiveslist() 
		{
			  return seives_masterService.getSeiveslist();
		}
	 
		@PostMapping("/createSeives")
		public Seives_master createSeives(@Valid @RequestBody Seives_master seives_master) {
			return seives_masterService.save(seives_master);
		}
		
		@PutMapping("/updateSeives/{id}")
		public ResponseEntity<Seives_master> updateSeives(@PathVariable(value = "id") Long id,@Valid @RequestBody Seives_master seives_master) {
			Seives_master op = seives_masterService.update(seives_master, id);
			return ResponseEntity.ok().body(op);
		}
		
		@PutMapping("/deleteSeives/{id}")
		public ResponseEntity<Seives_master> deleteSeives(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Seives_master seives_master) {
			Seives_master op = seives_masterService.delete(seives_master, id);
			return ResponseEntity.ok().body(op);
		}
		
		 @GetMapping("/retriveSeives/{id}")
			public ResponseEntity<Seives_master> retriveSeives(@PathVariable(value="id") Long id)
			{
				Seives_master op=seives_masterService.findOne(id);
				if(id==null)
				{
					return ResponseEntity.notFound().build();
				}
				else
				{
					return ResponseEntity.ok().body(op);
				}
			}
		 
		 
		 @GetMapping("/retriveSeivesDetails/{seivesid}")
			public List<Seives_Dtls> retriveSeivesDetails(@PathVariable(value = "seivesid") String seivesid)
			{
				return seives_masterService.retriveSeivesDetails(seivesid);
			}
			
		
/*************************************************************Seives Master Ends here***************************************/		
/*************************************************************Bin Group Starts here***************************************/
			@Autowired
			BingroupService bingroupService;
			
			@GetMapping("/getBingrouplist")
			public List<Bingroup> getBingrouplist() 
			{
				  return bingroupService.getBingrouplist();
			}
		 
			@PostMapping("/createBinGroup")
			public Bingroup createBinGroup(@Valid @RequestBody Bingroup bingroup) {
				return bingroupService.save(bingroup);
			}
			
			@PutMapping("/updateBinGroup/{id}")
			public ResponseEntity<Bingroup> updateBinGroup(@PathVariable(value = "id") Long id,@Valid @RequestBody Bingroup bingroup) {
				Bingroup op = bingroupService.update(bingroup, id);
				return ResponseEntity.ok().body(op);
			}
			
			@PutMapping("/deleteBinGroup/{id}")
			public ResponseEntity<Bingroup> deleteBinGroup(@PathVariable(value = "id") Long id,
					@Valid @RequestBody Bingroup bingroup) {
				Bingroup op = bingroupService.delete(bingroup, id);
				return ResponseEntity.ok().body(op);
			}
			
			 @GetMapping("/retriveBinGroup/{id}")
				public ResponseEntity<Bingroup> retriveBinGroup(@PathVariable(value="id") Long id)
				{
				 Bingroup op=bingroupService.findOne(id);
					if(id==null)
					{
						return ResponseEntity.notFound().build();
					}
					else
					{
						return ResponseEntity.ok().body(op);
					}
				}
		
/*************************************************************Bin Group Ends here***************************************/
 
 /*************************************************************Acc Group Starts here***************************************/
			 
	@Autowired
	Accounts_group_masterService accounts_group_masterService;
	
	
	@GetMapping("/getSeqNoForAccGrp")
	public ResponseEntity<SequenceIdDTO> getSeqNoForAccGrp(@RequestParam(defaultValue = "") String prefix,@RequestParam(defaultValue = "") String company)
	{
		SequenceIdDTO list = accounts_group_masterService.getSeqNoForAccGrp(prefix,company);
		
		return new ResponseEntity<SequenceIdDTO>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/accountTypeList")
	public List<Map<String,Object>> accountTypeList() {
		return accounts_group_masterService.accountTypeList();
	}
	
	@GetMapping(value = "/accountCatagoryList")
	public List<Map<String,Object>> accountCatagoryList() {
		return accounts_group_masterService.accountCatagoryList();
	}
	
	@GetMapping(value = "/accGroupList/{company}/{catagory}")
	public List<Map<String,Object>> accGroupList(@PathVariable(value = "company") String company,@PathVariable(value = "catagory") String catagory) {
		return accounts_group_masterService.accGroupList(company,catagory);
	}
	
	@GetMapping(value = "/accountGroupList")
	public List<Map<String,Object>> accountGroupList() {
		return accounts_group_masterService.accountGroupList();
	}
	
	@GetMapping(value = "/accountParentGroupList")
	public List<Map<String,Object>> accountParentGroupList() {
		return accounts_group_masterService.accountParentGroupList();
	}
	
	@PostMapping("/createAccGroups")
	public Accounts_group_master createAccGroups(@Valid @RequestBody Accounts_group_master accounts_group_master) {
		return accounts_group_masterService.save(accounts_group_master);
	}


	@GetMapping("/retriveAccGroup/{id}")
	public ResponseEntity<Accounts_group_master> retriveAccGroup(@PathVariable(value = "id") Long id) {
		Accounts_group_master itmgrp = accounts_group_masterService.findOne(id);
		if (itmgrp == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(itmgrp);
		}
	}
	
	

	@PutMapping("/updateAccGroups/{id}")
	public ResponseEntity<Accounts_group_master> updateAccGroups(@PathVariable(value = "id") long id,
			@Valid @RequestBody Accounts_group_master accounts_group_master) {
		//System.out.println("in master");
		Accounts_group_master op = accounts_group_masterService.update(accounts_group_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/checkAccGroupUsage/{code}")
	public ResponseEntity<StatusDTO> checkAccGroupUsage(@PathVariable(value = "code") String code)
	{
		StatusDTO val=accounts_group_masterService.checkAccGroupUsage(code);
		return ResponseEntity.ok().body(val);
	}
	
	
	@PutMapping("/deleteAccGrpMaster/{id}")
	public ResponseEntity<Accounts_group_master> deleteAccGrpMaster(@PathVariable(value = "id") long id,
			@Valid @RequestBody Accounts_group_master accounts_group_master) {
		Accounts_group_master op = accounts_group_masterService.deleteAccGrpMaster(accounts_group_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/postingaccountsgroup/{id}")
	public Accounts_group_master postingaccountsgroup(@PathVariable(value = "id") Long id) {
		
			return accounts_group_masterService.postingaccountsgroup(id);
		
	}
 /*************************************************************Acc Group Ends here***************************************/

/*************************************************************Accounts Type Master Starts here***************************************/
	@Autowired
	Accounts_type_masterService accounts_type_masterService;

	@GetMapping("/getATSequenceId/{fin_year}")
	public SequenceIdDTO getATSequenceId(@PathVariable(value = "fin_year") String fin_year)
	{
		 return accounts_type_masterService.getATSequenceId(fin_year);
	}
	
	@GetMapping("/getAccountsTypeList")
	public List<Map<String, Object>> getAccountsTypeList()
	{
		return accounts_type_masterService.getAccountsTypeList();				
	}
	
 	@PostMapping("/createAccountstype")
	public Accounts_type_master save(@Valid @RequestBody Accounts_type_master accounts_type_master) 
 	{
		return accounts_type_masterService.saveAccountsType(accounts_type_master);
	}
			 
 	
 	
 	@GetMapping("/retriveaccountstype/{id}")
	public ResponseEntity<Accounts_type_master> retriveaccountstype(@PathVariable(value="id") Long id)
	{
		Accounts_type_master op = accounts_type_masterService.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	@GetMapping("/checkAccTypeUsage/{code}")
	public ResponseEntity<StatusDTO> checkAccTypeUsage(@PathVariable(value = "code") String code)
	{
		StatusDTO val=accounts_type_masterService.checkAccTypeUsage(code);
		return ResponseEntity.ok().body(val);
	}
	
	@PutMapping("/deleteaccountstype/{id}")
	public ResponseEntity<Accounts_type_master> deleteaccountstype(@PathVariable(value = "id") long id,
			@Valid @RequestBody Accounts_type_master accounts_type_master) {
		Accounts_type_master op = accounts_type_masterService.deleteaccountstype(accounts_type_master, id);
		return ResponseEntity.ok().body(op);
	}
	
/*************************************************************Accounts Type Master Ends here***************************************/
/*************************************************************Accounts Master Catagory Starts here***************************************/		 
	
	@Autowired	
	Accounts_category_masterService accounts_category_masterService;
	
	@GetMapping("/getAccountsCategoryList")
	public List<Map<String, Object>> getAccountsCategoryList()
	{
		return accounts_category_masterService.getAccountsCategoryList();				
	}
	
	
	@GetMapping("/getAccountCatagorySequenceId/{fin_year}")
	public SequenceIdDTO getAccountCatagorySequenceId(@PathVariable(value = "fin_year") String fin_year)
	{
		 return accounts_category_masterService.getAccountCatagorySequenceId(fin_year);
	}
	
	@PostMapping("/createAccCatagory")
	public Accounts_category_master createAccCatagory(@Valid @RequestBody Accounts_category_master accounts_category_master) {
		return accounts_category_masterService.save(accounts_category_master);
	}
	
	@GetMapping("/retriveaccountscatagory/{id}")
	public ResponseEntity<Accounts_category_master> retriveaccountscatagory(@PathVariable(value="id") Long id)
	{
		Accounts_category_master op = accounts_category_masterService.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	
	@PutMapping("/updateAccCatagory/{id}")
	public ResponseEntity<Accounts_category_master> updateAccCatagory(@PathVariable(value = "id") long id,
			@Valid @RequestBody Accounts_category_master accounts_category_master) {
		
		Accounts_category_master op = accounts_category_masterService.update(accounts_category_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@GetMapping("/checkAccCatagoryUsage/{code}")
	public ResponseEntity<StatusDTO> checkAccCatagoryUsage(@PathVariable(value = "code") String code)
	{
		StatusDTO val=accounts_category_masterService.checkAccCatagoryUsage(code);
		return ResponseEntity.ok().body(val);
	}
	
	@PutMapping("/deleteAccCatagoryMaster/{id}")
	public ResponseEntity<Accounts_category_master> deleteAccCatagoryMaster(@PathVariable(value = "id") long id,
			@Valid @RequestBody Accounts_category_master accounts_category_master) {
		Accounts_category_master op = accounts_category_masterService.deleteAccCatagoryMaster(accounts_category_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	/*************************************************************Accounts Master Catagory Ends here***************************************/		
	/*************************************************************Accounts Master Catagory Starts here***************************************/		
	
	@Autowired
	Accounts_ledger_masterService accounts_ledger_masterService;
	
	@GetMapping("/getAccountLedgerSequenceId/{fin_year}")
	public SequenceIdDTO getAccountLedgerSequenceId(@PathVariable(value = "fin_year") String fin_year)
	{
		 return accounts_ledger_masterService.getAccountLedgerSequenceId(fin_year);
	}
	
	@GetMapping("/accountledgerlist")
	public List<Map<String, Object>> accountledgerlist()
	{
		return accounts_ledger_masterService.accountledgerlist();				
	}
	
	
	
	
	
	@PostMapping("/createAccLedger")
	public Accounts_ledger_master createAccLedger(@Valid @RequestBody Accounts_ledger_master accounts_ledger_master) {
		return accounts_ledger_masterService.save(accounts_ledger_master);
	}
	
	@GetMapping("/retriveaccountsledger/{id}")
	public ResponseEntity<Accounts_ledger_master> retriveaccountsledger(@PathVariable(value="id") Long id)
	{
		Accounts_ledger_master op = accounts_ledger_masterService.findOne(id);
		if(op==null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok().body(op);
		}
	}
	
	
	@PutMapping("/updateAccLedger/{id}")
	public ResponseEntity<Accounts_ledger_master> updateAccLedger(@PathVariable(value = "id") long id,
			@Valid @RequestBody Accounts_ledger_master accounts_ledger_master) {
		
		Accounts_ledger_master op = accounts_ledger_masterService.update(accounts_ledger_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	@PutMapping("/deleteAccLedger/{id}")
	public ResponseEntity<Accounts_ledger_master> deleteAccLedger(@PathVariable(value = "id") long id,
			@Valid @RequestBody Accounts_ledger_master accounts_ledger_master) {
		Accounts_ledger_master op = accounts_ledger_masterService.deleteAccLedger(accounts_ledger_master, id);
		return ResponseEntity.ok().body(op);
	}
	
	/*************************************************************Accounts Master Catagory ends here***************************************/
	
	/*************************************************************Item Stock Starts here***************************************/
	@Autowired
	Item_stockService item_stockService;
	
	@GetMapping("/getStocklist")
	public List<Map<String,Object>> getStocklist() 
	{
		  return item_stockService.getStocklist();
	}
	
	@GetMapping("/getAllItemFromStockView")
	public List<Map<String,Object>> getAllItemFromStockView() 
	{
		  return item_stockService.getAllItemFromStockView();
	}
 
	@PostMapping("/createItemStock")
	public Item_stock createItemStock(@Valid @RequestBody Item_stock item_stock) {
		return item_stockService.save(item_stock);
	}
	
	@PutMapping("/updateItemStock/{id}")
	public ResponseEntity<Item_stock> updateItemStock(@PathVariable(value = "id") Long id,@Valid @RequestBody Item_stock item_stock) {
		Item_stock op = item_stockService.update(item_stock, id);
		return ResponseEntity.ok().body(op);
	}
	
	 @GetMapping("/retriveItemStock/{id}")
		public ResponseEntity<Item_stock> retriveItemStock(@PathVariable(value="id") Long id)
		{
			Item_stock op=item_stockService.findOne(id);
			if(id==null)
			{
				return ResponseEntity.notFound().build();
			}
			else
			{
				return ResponseEntity.ok().body(op);
			}
		}
	 
	 
	 @GetMapping("/retriveStockDetails/{stockid}")
		public List<Map<String,Object>> retriveStockDetails(@PathVariable(value = "stockid") String stockid)
		{
			return item_stockService.retriveStockDetails(stockid);
		}
		
	
/*************************************************************Item Stock Ends here***************************************/
	 
 /*************************************************************Item Service Master Starts here***************************************/
	
	 @Autowired
	 Item_Service_masterService item_Service_masterService;
		
	 	@GetMapping("/getItemServiceSequenceId/{company}/{fin_year}")
		public SequenceIdDTO getItemServiceSequenceId(@PathVariable(value = "company") String company,@PathVariable(value = "fin_year") String fin_year)
		{
			 return item_Service_masterService.getItemServiceSequenceId(company,fin_year);
		}
		
	 	@GetMapping("/getItemServiceList/{company}")
		public List<Map<String, Object>> getItemServiceList(@PathVariable(value = "company") String company)
		{
			return item_Service_masterService.getItemServiceList(company);				
		}
	 
	 	@GetMapping("/findItemServiceMaster")
		public ResponseEntity<List<Item_Service_master>> findItemServiceMaster(@RequestParam(defaultValue = "") String searchtext,@RequestParam(defaultValue = "") String company) {
			List<Item_Service_master> iList= item_Service_masterService.findItemServiceMaster(searchtext, company);
			if (iList == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(iList);
			}
		}
	 	
	 	@GetMapping("/chkItemServiceNameStatic/{cname}")
		public ResponseEntity<MessageStatusDTO> chkItemServiceNameStatic(@PathVariable(value = "cname") String cname) 
		{
			MessageStatusDTO val=item_Service_masterService.chkItemServiceNameStatic(cname);
			if(val==null)
			{
				return ResponseEntity.notFound().build();
			}
			else
			{
				return ResponseEntity.ok().body(val);
			}
		}
	 	
		@PostMapping("/createItemServiceMaster")
		public Item_Service_master createItemServiceMaster(@Valid @RequestBody Item_Service_master item_Service_master)
		{
			 return item_Service_masterService.save(item_Service_master);				
		}
		
		
		@GetMapping("/retriveItemServiceMaster/{id}")
		public Item_Service_master retriveItemServiceMaster(@PathVariable(value = "id") Long id) {
			 return item_Service_masterService.findOne(id);
		}

		
		@PutMapping("/updateItemServiceMaster/{id}")
		public ResponseEntity<Item_Service_master> updateItemServiceMaster(@PathVariable(value = "id") Long id,
				@Valid @RequestBody Item_Service_master item_Service_master) {
			Item_Service_master op = item_Service_masterService.update(item_Service_master, id);
			return ResponseEntity.ok().body(op);
		}
		
		@PutMapping("/deleteItemService/{id}")
		public ResponseEntity<Item_Service_master> deleteItemService(@PathVariable(value="id") long id,
														@Valid @RequestBody Item_Service_master item_Service_master)
		{
			Item_Service_master deleteRC=item_Service_masterService.delete(item_Service_master,id);
			return ResponseEntity.ok().body(deleteRC);
		}
		
 /*************************************************************Item Service Master Ends here***************************************/
	
/****************** Other Party Master START *****************/
@Autowired
OtherPartyMasterService otherPartyMasterService;


@PostMapping("/createOtherPartyMaster")
public OtherPartyMaster save(@Valid @RequestBody OtherPartyMaster partyMaster) {
	return otherPartyMasterService.save(partyMaster);
}


@GetMapping("/retriveOtherPartyMaster/{id}")
public ResponseEntity<OtherPartyMaster> retriveOtherPartyMaster(@PathVariable(value = "id") Long id) {
	OtherPartyMaster op = otherPartyMasterService.findOne(id);
	if (op == null) {
		return ResponseEntity.notFound().build();
	} else {
		return ResponseEntity.ok().body(op);
	}
}

@PutMapping("/updateOtherPartyMaster/{id}")
public ResponseEntity<OtherPartyMaster> updateOtherPartyMaster(@PathVariable(value = "id") Long id,
		@Valid @RequestBody OtherPartyMaster partyMaster) {
	OtherPartyMaster op = otherPartyMasterService.update(partyMaster, id);
	return ResponseEntity.ok().body(op);
}

@PutMapping("/deleteOtherPartyMaster/{id}")
public ResponseEntity<OtherPartyMaster> deleteOtherPartyMaster(@PathVariable(value = "id") Long id,
		@Valid @RequestBody OtherPartyMaster party) {
	OtherPartyMaster op = otherPartyMasterService.delete(party, id);
	return ResponseEntity.ok().body(op);
}

@GetMapping("/findOtherPartyMaster/{searchtext}")
public ResponseEntity<List<OtherPartyMaster>> findOtherPartyMaster(@PathVariable(value = "searchtext") String searchtext) {
	List<OtherPartyMaster> partylist= otherPartyMasterService.findOtherPartyMaster(searchtext);
	if (partylist == null) {
		return ResponseEntity.notFound().build();
	} else {
		return ResponseEntity.ok().body(partylist);
	}
}

@GetMapping("/getOtherPartyList/{company}")
public List<Map<String,Object>> getOtherPartyList(@PathVariable(value = "company") String company) 
{
	System.out.println("company:"+company);
	return otherPartyMasterService.getOtherPartyList(company);
}

/******************* Other Party Master Ends *************************/
/****************** Other Item Master START *****************/
@Autowired
OtherItemMasterService otheItemMasterService;


@PostMapping("/createOtherItemMaster")
public OtherItemMaster save(@Valid @RequestBody OtherItemMaster itemMaster) {
	return otheItemMasterService.save(itemMaster);
}


@GetMapping("/retriveOtheritemMaster/{id}")
public ResponseEntity<OtherItemMaster> retriveOtheritemMaster(@PathVariable(value = "id") Long id) {
	OtherItemMaster op = otheItemMasterService.findOne(id);
	if (op == null) {
		return ResponseEntity.notFound().build();
	} else {
		return ResponseEntity.ok().body(op);
	}
}

@PutMapping("/updateOtherItemMaster/{id}")
public ResponseEntity<OtherItemMaster> updateOtherItemMaster(@PathVariable(value = "id") Long id,
		@Valid @RequestBody OtherItemMaster itemMaster) {
	OtherItemMaster op = otheItemMasterService.update(itemMaster, id);
	return ResponseEntity.ok().body(op);
}

@PutMapping("/deleteOtherItemMaster/{id}")
public ResponseEntity<OtherItemMaster> deleteOtherItemMaster(@PathVariable(value = "id") Long id,
		@Valid @RequestBody OtherItemMaster item) {
	OtherItemMaster op = otheItemMasterService.delete(item, id);
	return ResponseEntity.ok().body(op);
}

@GetMapping("/findOtherItemMaster/{searchtext}")
public ResponseEntity<List<OtherItemMaster>> findOtherItemMaster(@PathVariable(value = "searchtext") String searchtext) {
	List<OtherItemMaster> itemlist= otheItemMasterService.findOtherItemMaster(searchtext);
	if (itemlist == null) {
		return ResponseEntity.notFound().build();
	} else {
		return ResponseEntity.ok().body(itemlist);
	}
}

@GetMapping("/getOtherItemList/{company}")
public List<Map<String,Object>> getOtherItemList(@PathVariable(value = "company") String company) 
{
	System.out.println("company:"+company);
	return otheItemMasterService.getOtherItemList(company);
}

/******************* Other Item Master Ends *************************/	

/************** JobWork Item Allocation Starts **************/

@Autowired
JobWorkItemAllocationService jobWorkItemAllocationService;

@PostMapping("/createJobItemAllocation")
public JobWorkItemAllocation createJobItemAllocation(@Valid @RequestBody JobWorkItemAllocation jobWorkItemAllocation)
{
	 return jobWorkItemAllocationService.save(jobWorkItemAllocation);				
}

@GetMapping("/JobWorkItemAllocationList")
public List<Map<String, Object>> JobWorkItemAllocationList()
{
	return jobWorkItemAllocationService.JobWorkItemAllocationList();				
}

@GetMapping("/retriveJobItemAllocation/{id}")
public JobWorkItemAllocation retriveJobItemAllocation(@PathVariable(value = "id") Long id) {
	 return jobWorkItemAllocationService.findOne(id);
}

@PutMapping("/updateJobItemAllocation/{id}")
public ResponseEntity<JobWorkItemAllocation> updateJobItemAllocation(@PathVariable(value = "id") Long id,
		@Valid @RequestBody JobWorkItemAllocation jobWorkItemAllocation) {
	JobWorkItemAllocation op = jobWorkItemAllocationService.update(jobWorkItemAllocation, id);
	return ResponseEntity.ok().body(op);
}

@PutMapping("/DeleteJobItemAllocation/{id}")
public ResponseEntity<JobWorkItemAllocation> DeleteJobItemAllocation(@PathVariable(value="id") long id,
												@Valid @RequestBody JobWorkItemAllocation jobWorkItemAllocation)
{
	JobWorkItemAllocation deleteRC=jobWorkItemAllocationService.delete(jobWorkItemAllocation,id);
	return ResponseEntity.ok().body(deleteRC);
}

@GetMapping("/getItemQtythruLoading/{item}/{party}")
public Map<String, Object> getItemQtythruLoading(@PathVariable(value = "item") String item,
													   @PathVariable(value = "party") String party)
{
	return jobWorkItemAllocationService.getItemQtythruLoading(item,party);				
}

@GetMapping("/getJwAllocationRestWt/{party}")
public Map<String,Object> getJwAllocationRestWt(@PathVariable(value = "party") String party)
{
	  return jobWorkItemAllocationService.getJwAllocationRestWt(party);
}

/************** JobWork Item Allocation Ends **************/

/************** JobWork GRN Item Tagging Starts **************/
@Autowired
Jw_grn_itemtagService jw_grn_itemtagService;

@GetMapping("/getJwGRN")
public List<Map<String,Object>> getJwGRN()
{
	  return jw_grn_itemtagService.getJwGRN();
}

@GetMapping("/getJwGRNunique")
public List<Map<String,Object>> getJwGRNunique()
{
	  return jw_grn_itemtagService.getJwGRNunique();
}



@GetMapping("/getJobItemList")
public List<Map<String,Object>> getJobItemList()
{
	  return jw_grn_itemtagService.getJobItemList();
}

@GetMapping("/getJobItemTagMaster")
public List<Map<String,Object>> getJobItemTagMaster() 
{
	  return jw_grn_itemtagService.getJobItemTagMaster();
}

@PostMapping("/createJobItemTagMaster")
public Jw_grn_itemtag createJobItemTagMaster(@Valid @RequestBody Jw_grn_itemtag jw_grn_itemtag) {
	return jw_grn_itemtagService.save(jw_grn_itemtag);
}

@GetMapping("/retriveJobItemTagMaster/{id}")
public Jw_grn_itemtag retriveJobItemTagMaster(@PathVariable(value = "id") Long id) {
	 return jw_grn_itemtagService.findOne(id);
}
 
 
@GetMapping("/getJwGrnPartytagDetails/{jw_grn_tag}")
public List<Map<String,Object>> getJwGrnPartytagDetails(@PathVariable(value = "jw_grn_tag") String jw_grn_tag)
{
	return jw_grn_itemtagService.getJwGrnPartytagDetails(jw_grn_tag);
}
 
@GetMapping("/getJwGrnPartywitemDetails/{jw_grn_tag}/{party}")
public List<Map<String,Object>> getJwGrnPartywitemDetails(@PathVariable(value = "jw_grn_tag") String jw_grn_tag,@PathVariable(value = "party") String party)
{
	return jw_grn_itemtagService.getJwGrnPartywitemDetails(jw_grn_tag,party);
}

@PutMapping("/updateJobItemTagMaster/{id}")
 public ResponseEntity<Jw_grn_itemtag> updateJobItemTagMaster(@PathVariable(value = "id") Long id,@Valid @RequestBody Jw_grn_itemtag jw_grn_itemtag) {
	 Jw_grn_itemtag op = jw_grn_itemtagService.update(jw_grn_itemtag, id);
 	return ResponseEntity.ok().body(op);
 }

@PutMapping("/DeleteJobItemTagMaster/{id}")
public ResponseEntity<Jw_grn_itemtag> DeleteJobItemTagMaster(@PathVariable(value="id") long id,@Valid @RequestBody Jw_grn_itemtag jw_grn_itemtag)
{
	 Jw_grn_itemtag deleteRC=jw_grn_itemtagService.delete(jw_grn_itemtag,id);
	return ResponseEntity.ok().body(deleteRC);
}

@GetMapping("/checkdeleteJobItemTagMaster/{jw_grn_tag}")
public StatusDTO checkdeleteJobItemTagMaster(@PathVariable(value = "jw_grn_tag") String jw_grn_tag) {
	 return jw_grn_itemtagService.checkdeleteJobItemTagMaster(jw_grn_tag);
}
 
	
	@GetMapping("/checkjw_itemallocation/{party}/{job_item}/{qty}/{jw_grn_tag}")
	public ResponseDTO checkjw_itemallocation(@PathVariable(value = "party") String party,
			@PathVariable(value = "job_item") String job_item,
			@PathVariable(value = "qty") double qty,@PathVariable(value = "jw_grn_tag") String jw_grn_tag) {
		 return jw_grn_itemtagService.checkjw_itemallocation(party,job_item,qty,jw_grn_tag);
	}
	
	
	

/************** JobWork GRN Item Tagging Ends **************/
	
/**************  Store Charges Master Starts **************/
	@Autowired
	Store_inv_charge_masterService store_inv_charge_masterService;

	@GetMapping("/getStoreChargesList")
	public List<Map<String,Object>> getStoreChargesList()
	{
		  return store_inv_charge_masterService.getStoreChargesList();
	}

	@PostMapping("/createStoreChargeMaster")
	public Store_inv_charge_master createStoreChargeMaster(@Valid @RequestBody Store_inv_charge_master store_inv_charge_master) {
		return store_inv_charge_masterService.save(store_inv_charge_master);
	}
	
	@GetMapping("/retriveStoreInventoryChgs/{id}")
	public Store_inv_charge_master retriveStoreInventoryChgs(@PathVariable(value = "id") Long id) {
		 return store_inv_charge_masterService.findOne(id);
	}
	
	@PutMapping("/updateStoreChargeMaster/{id}")
	 public ResponseEntity<Store_inv_charge_master> updateStoreChargeMaster(@PathVariable(value = "id") Long id,@Valid @RequestBody Store_inv_charge_master store_inv_charge_master) {
		 Store_inv_charge_master op = store_inv_charge_masterService.update(store_inv_charge_master, id);
	 	return ResponseEntity.ok().body(op);
	 }
	 
	 @PutMapping("/deleteStoreChargeMaster/{id}")
	 public ResponseEntity<Store_inv_charge_master> deleteStoreChargeMaster(@PathVariable(value="id") long id,@Valid @RequestBody Store_inv_charge_master store_inv_charge_master)
	 {
		 Store_inv_charge_master deleteRC=store_inv_charge_masterService.delete(store_inv_charge_master,id);
	 	return ResponseEntity.ok().body(deleteRC);
	 }
	 
	 
/************** Store Charges Master ends **************/
	
}			 

