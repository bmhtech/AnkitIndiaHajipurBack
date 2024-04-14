package com.AnkitIndia.jwtauthentication.security.services;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Country_master;
import com.AnkitIndia.jwtauthentication.model.Role;
import com.AnkitIndia.jwtauthentication.model.User_Role_Access;
import com.AnkitIndia.jwtauthentication.repository.RoleRepository;

import com.AnkitIndia.jwtauthentication.repository.User_Role_AccessRepository;



@Service
public class User_Role_AccessService_Imp implements User_Role_AccessService{

	@Autowired
	User_Role_AccessRepository user_Role_AccessRepository;
	
	
	
	@Autowired
	RoleRepository roleRepository;
	
	@Transactional
	public User_Role_Access save(User_Role_Access user_Role_Access) {
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(roleRepository.countId() != null ) {
			slno=Long.parseLong(roleRepository.countId());
		}
		String prefix="RL";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		String parent_role_id=user_Role_Access.getParent_role_id(),name=user_Role_Access.getUser_role(),parent_role=roleRepository.getParentName(user_Role_Access.getParent_role_id());
		
		
		user_Role_Access.setUser_role(user_Role_Access.getUser_role());
		user_Role_Access.setRole_id(gen_sno);
		user_Role_Access.setParent_role_id(user_Role_Access.getParent_role_id());
		
		user_Role_Access.setParent_role_name(roleRepository.getParentName(user_Role_Access.getParent_role_id()));
		user_Role_Access.setItem_master(user_Role_Access.getItem_master());
		user_Role_Access.setSupplier_master(user_Role_Access.getSupplier_master());
		user_Role_Access.setCusromer_master(user_Role_Access.getCusromer_master());
		user_Role_Access.setBroker_master(user_Role_Access.getBroker_master());
		user_Role_Access.setOther_partner_master(user_Role_Access.getOther_partner_master());
		user_Role_Access.setOther_master(user_Role_Access.getOther_master());
		user_Role_Access.setMislenious_master(user_Role_Access.getMislenious_master());
		user_Role_Access.setPurchase_inventory(user_Role_Access.getPurchase_inventory());
		user_Role_Access.setWeighment(user_Role_Access.getWeighment());
		user_Role_Access.setSales_transaction(user_Role_Access.getSales_transaction());
		user_Role_Access.setProduction_module(user_Role_Access.getProduction_module());
		user_Role_Access.setStock_transfer(user_Role_Access.getStock_transfer());
		user_Role_Access.setGate_pass(user_Role_Access.getGate_pass());
		user_Role_Access.setSales_pur_report(user_Role_Access.getSales_pur_report());
		user_Role_Access.setStock_transaction(user_Role_Access.getStock_transaction());
		
		
		
		String jsontype="{\"MENU_ITEM\":[{\"path\":\"index\",\"title\":\"Dashboard\",\"icon\":\"dashboard\",\"children\":[{\"path\":\"\",\"title\":\"\",\"children\":[{\"path\":\"\",\"title\":\"\",\"icon\":\"\"}]}]},";
		//master starts here 
		if(user_Role_Access.getItem_master().compareToIgnoreCase("")!=0 || user_Role_Access.getSupplier_master().compareToIgnoreCase("")!=0 || user_Role_Access.getCusromer_master().compareToIgnoreCase("")!=0 || user_Role_Access.getBroker_master().compareToIgnoreCase("")!=0 || user_Role_Access.getOther_partner_master().compareToIgnoreCase("")!=0  || user_Role_Access.getOther_master().compareToIgnoreCase("")!=0 || user_Role_Access.getMislenious_master().compareToIgnoreCase("")!=0) 
		{
			jsontype+="{\"path\":\"Masters\",\"title\":\"Masters\",\"icon\":\"sitemap\",\"children\":[";
			if(user_Role_Access.getItem_master().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"ItemMaster\",\"title\":\"Item Master\",\"children\":[";
				
				
				if(user_Role_Access.getItem_master().contains("item_type")) 
				{
					jsontype+="{\"path\":\"ItemType\",\"title\":\"Item Type\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getItem_master().contains("item_catagory")) 
				{
					jsontype+="{\"path\":\"ItemCategory\",\"title\":\"Item Category\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getItem_master().contains("item_group")) 
				{
					jsontype+="{\"path\":\"ItemGroup\",\"title\":\"Item Group\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getItem_master().contains("item_master")) 
				{
					jsontype+="{\"path\":\"itemsmaster\",\"title\":\"Items Master\",\"icon\":\"angle-right\"},";
				}
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				
				jsontype+="]},";
				
			}
			if(user_Role_Access.getSupplier_master().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"Masters/SupplierMaster\",\"title\":\"Supplier Master\",\"children\":[";
				
				
				if(user_Role_Access.getSupplier_master().contains("supplier_group")) 
				{
					jsontype+="{\"path\":\"SupplierGroup\",\"title\":\"Supplier Group\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getSupplier_master().contains("supplier_master")) 
				{
					jsontype+="{\"path\":\"SuppliersMaster\",\"title\":\"Suppliers Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getSupplier_master().contains("transporter_group")) 
				{
					jsontype+="{\"path\":\"Transportergroup\",\"title\":\"Transporter group\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getSupplier_master().contains("transporter_master")) 
				{
					jsontype+="{\"path\":\"TransporterMaster\",\"title\":\"Transporter master\",\"icon\":\"angle-right\"},";
				}
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			if(user_Role_Access.getCusromer_master().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"Masters/CustomerMaster\",\"title\":\"Customer Master\",\"children\":[";
				
				
				if(user_Role_Access.getCusromer_master().contains("customer_group")) 
				{
					jsontype+="{\"path\":\"CustomerGroup\",\"title\":\"Customer Group\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getCusromer_master().contains("customer_master")) 
				{
					jsontype+="{\"path\":\"CustomersMaster\",\"title\":\"Customer Master\",\"icon\":\"angle-right\"},";
				}
				
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			if(user_Role_Access.getBroker_master().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"Masters/BrokerMaster\",\"title\":\"Broker Master\",\"children\":[";
				
				
				if(user_Role_Access.getBroker_master().contains("broker_group")) 
				{
					jsontype+="{\"path\":\"BrokerGroup\",\"title\":\"Broker Group\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getBroker_master().contains("broker_master")) 
				{
					jsontype+="{\"path\":\"BrokersMaster\",\"title\":\"Broker Master\",\"icon\":\"angle-right\"},";
				}
			
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			if(user_Role_Access.getOther_partner_master().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"Masters/OtherPartnerMaster\",\"title\":\"Other Partner Master\",\"children\":[";
				
				
				if(user_Role_Access.getOther_partner_master().contains("other_partner_group")) 
				{
					jsontype+="{\"path\":\"OtherPartnerGroup\",\"title\":\"Other Partner Group\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getOther_partner_master().contains("other_partner_master")) 
				{
					jsontype+="{\"path\":\"OtherPartners\",\"title\":\"Other Partner Master\",\"icon\":\"angle-right\"},";
				}
			
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			if(user_Role_Access.getOther_master().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"Masters/OtherMasters\",\"title\":\"Other Masters\",\"children\":[";
				
				
				if(user_Role_Access.getOther_master().contains("shop_floor_master")) 
				{
					jsontype+="{\"path\":\"shop-floor-master\",\"title\":\"Shop Floor Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getOther_master().contains("payment_term_master")) 
				{
					jsontype+="{\"path\":\"PaymentTerm\",\"title\":\"Payment Term Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getOther_master().contains("qcrules_setup")) 
				{
					jsontype+="{\"path\":\"qcrulessetup\",\"title\":\"QC Rules Setup\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getOther_master().contains("qtds_master")) 
				{
					jsontype+="{\"path\":\"tds-master\",\"title\":\"QTDS Master\",\"icon\":\"angle-right\"},";
				}
			
				if(user_Role_Access.getOther_master().contains("charges_master")) 
				{
					jsontype+="{\"path\":\"charges-master\",\"title\":\"Charges Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getOther_master().contains("zone_master")) 
				{
					jsontype+="{\"path\":\"zone-master\",\"title\":\"Zone Master\",\"icon\":\"angle-right\"},";
				}
			
				if(user_Role_Access.getOther_master().contains("channel_customer_master")) 
				{
					jsontype+="{\"path\":\"ChannelCustomerMasterComponent\",\"title\":\"Channel Customer Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getOther_master().contains("weightment_charges_master.save") || user_Role_Access.getOther_master().contains("weightment_charges_master.update") || user_Role_Access.getOther_master().contains("weightment_charges_master.delete")) 
				{
					jsontype+="{\"path\":\"WeighmentChargesMaster\",\"title\":\"Weighment Charges Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getOther_master().contains("loading_point")) 
				{
					jsontype+="{\"path\":\"loadingPoint\",\"title\":\"Loading Point\",\"icon\":\"angle-right\"}]},";
				}
				
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			if(user_Role_Access.getMislenious_master().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"Masters/MisMaster\",\"title\":\"Mislenious Master\",\"children\":[";
				
				
				if(user_Role_Access.getMislenious_master().contains("company_master")) 
				{
					jsontype+="{\"path\":\"company\",\"title\":\"Company Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("company_business_unit")) 
				{
					jsontype+="{\"path\":\"company-business-unit-master\",\"title\":\"Company Business Unit  Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("warehouse_master")) 
				{
					jsontype+="{\"path\":\"warehouse-master\",\"title\":\"WareHouse Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("bin_master")) 
				{
					jsontype+="{\"path\":\"bin-master\",\"title\":\"Bin Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("department_master")) 
				{
					jsontype+="{\"path\":\"department-master\",\"title\":\"Department Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("custom_uom_master")) 
				{
					jsontype+="{\"path\":\"custom-uom-master\",\"title\":\"Custom UOM Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("misc_master")) 
				{
					jsontype+="{\"path\":\"misc-master\",\"title\":\"Misc Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("tax_type_master")) 
				{
					jsontype+="{\"path\":\"tax-type-master\",\"title\":\"Tax Type Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("vehicle_type_master")) 
				{
					jsontype+="{\"path\":\"vehicle-type-master\",\"title\":\"Vehicle Type Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("vehicle_master")) 
				{
					jsontype+="{\"path\":\"vehicle-master\",\"title\":\"Vehicle Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("transportation_charges")) 
				{
					jsontype+="{\"path\":\"transportation-charges-matrix-master\",\"title\":\"Transportation Charges Matrix Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("tax_code_master")) 
				{
					jsontype+="{\"path\":\"tax-code-master\",\"title\":\"Tax Code Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("designation_master")) 
				{
					jsontype+="{\"path\":\"designation-master\",\"title\":\"Designation Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("district_master")) 
				{
					jsontype+="{\"path\":\"district-master\",\"title\":\"District Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("city_master")) 
				{
					jsontype+="{\"path\":\"city-master\",\"title\":\"City Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("area_master")) 
				{
					jsontype+="{\"path\":\"area-master\",\"title\":\"Area Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("reason_master")) 
				{
					jsontype+="{\"path\":\"reason-master\",\"title\":\"Reason Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("screen_master")) 
				{
					jsontype+="{\"path\":\"screen-master\",\"title\":\"Screen Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("purpose_master")) 
				{
					jsontype+="{\"path\":\"purpose-master\",\"title\":\"Purpose Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getMislenious_master().contains("invoice_type")) 
				{
					jsontype+="{\"path\":\"invoice-type-master\",\"title\":\"Invoice Type\",\"icon\":\"angle-right\"},";
				}
			
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			jsontype = jsontype.substring(0, jsontype.length()-1);
			jsontype+="]},";
		}
		//inventory transaction
		if(user_Role_Access.getPurchase_inventory().compareToIgnoreCase("")!=0 || user_Role_Access.getWeighment().compareToIgnoreCase("")!=0 || user_Role_Access.getSales_transaction().compareToIgnoreCase("")!=0 ||user_Role_Access.getProduction_module().compareToIgnoreCase("")!=0 || user_Role_Access.getStock_transfer().compareToIgnoreCase("")!=0 || user_Role_Access.getGate_pass().compareToIgnoreCase("")!=0) 
		{
			
			jsontype+="{\"path\":\"invTrans\",\"title\":\"Inventory Transaction\",\"icon\":\"sitemap\",\"children\":[";
			if(user_Role_Access.getPurchase_inventory().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"purchase\",\"title\":\"Purchase Inventory\",\"children\":[";
				
				
				if(user_Role_Access.getPurchase_inventory().contains("indent_order")) 
				{
					jsontype+="{\"path\":\"IndentOrder\",\"title\":\"Indent Order\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getPurchase_inventory().contains("purchase_enquiry")) 
				{
					jsontype+="{\"path\":\"purchase-enquiry\",\"title\":\"Purchase Enquiry\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getPurchase_inventory().contains("purchase_quotation")) 
				{
					jsontype+="{\"path\":\"purchase-quotation\",\"title\":\"Purchase Quotation\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getPurchase_inventory().contains("purchase_order")) 
				{
					jsontype+="{\"path\":\"purchase-order\",\"title\":\"Purchase Order\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getPurchase_inventory().contains("quality_check")) 
				{
					jsontype+="{\"path\":\"quality-check\",\"title\":\"Quality Check\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getPurchase_inventory().contains("peripheral_quality_check")) 
				{
					jsontype+="{\"path\":\"PeripheralQualityCheck\",\"title\":\"Peripheral Quality Check\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getPurchase_inventory().contains("grn")) 
				{
					jsontype+="{\"path\":\"grn\",\"title\":\"GRN\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getPurchase_inventory().contains("l1_selection")) 
				{
					jsontype+="{\"path\":\"l1-selection\",\"title\":\"l1-selection\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getPurchase_inventory().contains("purchase_bill")) 
				{
					jsontype+="{\"path\":\"purchase-bill\",\"title\":\"Purchase Bill\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getPurchase_inventory().contains("purchase_return_approval")) 
				{
					jsontype+="{\"path\":\"pur-return-approval-note\",\"title\":\"Purchase Return Approval Note\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getPurchase_inventory().contains("purchase_return_note")) 
				{
					jsontype+="{\"path\":\"pur-return-note\",\"title\":\"pur-return-note\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getPurchase_inventory().contains("debit_note")) 
				{
					jsontype+="{\"path\":\"debit-note\",\"title\":\"Debit Note\",\"icon\":\"angle-right\"},";
				}
				
			
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			if(user_Role_Access.getWeighment().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"invTrans/weighment\",\"title\":\"Weighment\",\"children\":[";
				
				
				if(user_Role_Access.getWeighment().contains("tag_advice_with_po")) 
				{
					jsontype+="{\"path\":\"TagAdviceWithPo\",\"title\":\"Tag Advice With PO\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getWeighment().contains("unload_advice")) 
				{
					jsontype+="{\"path\":\"unloadAdvice\",\"title\":\"Unload Advice\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getWeighment().contains("weightment.save") || user_Role_Access.getWeighment().contains("weightment.update") || user_Role_Access.getWeighment().contains("weightment.delete")) 
				{
					jsontype+="{\"path\":\"unloadWeightment\",\"title\":\"Weightment\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getWeighment().contains("loading_advice")) 
				{
					jsontype+="{\"path\":\"loadingAdvice\",\"title\":\"Loading Advice\",\"icon\":\"angle-right\"},";
				}
				
			
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			if(user_Role_Access.getSales_transaction().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"invTrans/salestransaction\",\"title\":\"Sales Transaction\",\"children\":[";
				
				
				if(user_Role_Access.getSales_transaction().contains("sales_enquiry")) 
				{
					jsontype+="{\"path\":\"salesenquiry\",\"title\":\"Sales Enquiry\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getSales_transaction().contains("sales_quotation")) 
				{
					jsontype+="{\"path\":\"SalesQuotation\",\"title\":\"Sales Quotation\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getSales_transaction().contains("sales_order")) 
				{
					jsontype+="{\"path\":\"SalesOrder\",\"title\":\"Sales Order\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getSales_transaction().contains("delivery_challan")) 
				{
					jsontype+="{\"path\":\"DeliveryChallan\",\"title\":\"Delivery Challan\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getSales_transaction().contains("sales_invoice")) 
				{
					jsontype+="{\"path\":\"SalesInvoice\",\"title\":\"Sales Invoice\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getSales_transaction().contains("return_approval_note")) 
				{
					jsontype+="{\"path\":\"ReturnApprovalNote\",\"title\":\"Return Approval Note\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getSales_transaction().contains("sales_return_note")) 
				{
					jsontype+="{\"path\":\"SalesReturnNote\",\"title\":\"Sales Return Note\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getSales_transaction().contains("credit_note")) 
				{
					jsontype+="{\"path\":\"CreditNote\",\"title\":\"Credit Note\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getSales_transaction().contains("gate_pass")) 
				{
					jsontype+="{\"path\":\"GatePass\",\"title\":\"Gate Pass\",\"icon\":\"angle-right\"},";
				}
				
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			if(user_Role_Access.getProduction_module().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"production\",\"title\":\"Production Module\",\"children\":[";
				
				
				if(user_Role_Access.getProduction_module().contains("process_master")) 
				{
					jsontype+="{\"path\":\"process-master\",\"title\":\"ProcessMaster\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getProduction_module().contains("bom_master")) 
				{
					jsontype+="{\"path\":\"bom-master\",\"title\":\"Bom Master\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getProduction_module().contains("production_planning")) 
				{
					jsontype+="{\"path\":\"production-planning\",\"title\":\"Production Planning\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getProduction_module().contains("production_transaction_reg")) 
				{
					jsontype+="{\"path\":\"production-transaction\",\"title\":\"Production Transaction(Reg)\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getProduction_module().contains("production_transaction_spc")) 
				{
					jsontype+="{\"path\":\"production-transaction-special\",\"title\":\"Production Transaction(Spc)\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getProduction_module().contains("stock_transfer_indent")) 
				{
					jsontype+="{\"path\":\"indentorder\",\"title\":\"StockTransfer Indent\",\"icon\":\"angle-right\"},";
				}
			
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			if(user_Role_Access.getStock_transfer().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"stocktransfer\",\"title\":\"Stock Transfer\",\"children\":[";
				
				
				if(user_Role_Access.getStock_transfer().contains("stock_transfer_order")) 
				{
					jsontype+="{\"path\":\"StockTransfers\",\"title\":\"Stock Transfer Order\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getStock_transfer().contains("stock_transfer_challan")) 
				{
					jsontype+="{\"path\":\"StockTransferChallan\",\"title\":\"Stock Transfer Challan\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getStock_transfer().contains("stock_transfer_pur_inv")) 
				{
					jsontype+="{\"path\":\"StockTransferPurchaseInvoiceComponent\",\"title\":\"Stock Transfer Pur Inv\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getStock_transfer().contains("stock_transfer_sales_inv")) 
				{
					jsontype+="{\"path\":\"StockTransferSalesInvoiceComponent\",\"title\":\"Stock Transfer Sales Inv\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getStock_transfer().contains("stock_transfer_grn")) 
				{
					jsontype+="{\"path\":\"StockTransferGrnComponent\",\"title\":\"Stock Transfer GRN\",\"icon\":\"angle-right\"},";
				}
				
				
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			if(user_Role_Access.getGate_pass().compareToIgnoreCase("")!=0) 
			{
				jsontype+="{\"path\":\"invTrans/gatepass\",\"title\":\"Gatepass\",\"children\":[";
				
				
				if(user_Role_Access.getGate_pass().contains("gate_pass_check_list")) 
				{
					jsontype+="{\"path\":\"gatepass_checklist\",\"title\":\"Gate Pass Check list\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getGate_pass().contains("gate_in")) 
				{
					jsontype+="{\"path\":\"gatepass-gatin\",\"title\":\"Gate In\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getGate_pass().contains("gate_out_authorization")) 
				{
					jsontype+="{\"path\":\"gatepass-gateout-a\",\"title\":\"Gate Out Authorization\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getGate_pass().contains("gate_pass_gate_out")) 
				{
					jsontype+="{\"path\":\"gatepass-geteout\",\"title\":\"Gate Pass Gate Out\",\"icon\":\"angle-right\"},";
				}
				if(user_Role_Access.getGate_pass().contains("visitor_master")) 
				{
					jsontype+="{\"path\":\"visitor-master\",\"title\":\"Visitor Master\",\"icon\":\"angle-right\"},";
				}
			
				
				jsontype = jsontype.substring(0, jsontype.length()-1);
				jsontype+="]},";
			}
			
			jsontype = jsontype.substring(0, jsontype.length()-1);
			jsontype+="]},";
		}
		//report 
		if(user_Role_Access.getSales_pur_report().compareToIgnoreCase("")!=0 ) 
		{
			jsontype+="{\"path\":\"report\",\"title\":\"Report\",\"icon\": \"angle-right\",\"children\":[{\"path\":\"salesreport\",\"title\":\"Sales Report\",\"children\":[{\"path\":\"partyledger\",\"title\":\"Party Ledger\",\"icon\": \"angle-right\"},{\"path\":\"controlaccount\",\"title\":\"Control Account\",\"icon\":\"angle-right\"},{\"path\":\"SalesReportDynamic\",\"title\":\"Sales/Purchase-Report\",\"icon\":\"angle-right\"},{\"path\":\"Salesreportsorting\",\"title\":\"Report-Sorting\",\"icon\":\"angle-right\"},{\"path\":\"SalesReportDynamicView\",\"title\":\"View-Reports\",\"icon\":\"angle-right\"}]}]},";
		}
		jsontype = jsontype.substring(0, jsontype.length()-1);
		jsontype +="]}";
	
		//Stock_transaction 
			if(user_Role_Access.getStock_transaction().compareToIgnoreCase("")!=0 ) 
			{
			jsontype+="{\"path\":\"stock-transaction\",\"title\":\"Store Transaction\",\"children\":[";
			
			if(user_Role_Access.getStock_transaction().contains("shop_floor_access")) 
			{
				jsontype+="{\"path\":\"storeflooraccess\",\"title\":\"Store Floor Access\",\"icon\":\"angle-right\"},";
			}
			if(user_Role_Access.getStock_transaction().contains("store_dashboard")) 
			{
				jsontype+="{\"path\":\"storedashboard\",\"title\":\"Store Dashboard\",\"icon\":\"angle-right\"},";
			}
			if(user_Role_Access.getStock_transaction().contains("requisition")) 
			{
				jsontype+="{\"path\":\"requisition\",\"title\":\"Requisition\",\"icon\":\"angle-right\"},";
			}
			if(user_Role_Access.getStock_transaction().contains("view_store")) 
			{
				jsontype+="{\"path\":\"viewstore\",\"title\":\"View Store\",\"icon\":\"angle-right\"},";
			}
			
			jsontype = jsontype.substring(0, jsontype.length()-1);
			jsontype +="]}";
		}
		user_Role_Access.setRoleaccessjson(jsontype);
		
	 
		Role role=new Role();
		role.setRole_id(gen_sno);
		role.setParent_role_id(parent_role_id);
		role.setName(name); 
		role.setParent_role(parent_role); 
		role.setDescription(gen_sno);
		role.setRoleaccessjson(jsontype);
		
		user_Role_Access.setRole(role);
		
		
		return user_Role_AccessRepository.save(user_Role_Access);
	}
	
	
	public List<User_Role_Access> getRoleItemMaster(String role_access)
	{
		String[] roleSplit=role_access.split("tuhinabcd");
		
		List<User_Role_Access> roleAccess=new ArrayList<User_Role_Access>();
		
		if(roleSplit[1].compareToIgnoreCase("item_master")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleItemMaster(roleSplit[0]));// get code roleSplit[0] from front end
		}
		if(roleSplit[1].compareToIgnoreCase("supplier_master")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleSupplierMaster(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("cusromer_master")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleCustomerMaster(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("broker_master")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleBrokerMaster(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("other_partner_master")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleOtherPartnerMaster(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("other_master")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleOtherMaster(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("mislenious_master")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleMisleniousMaster(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("purchase_inventory")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRolePurchaseInventory(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("weighment")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleWeighment(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("sales_transaction")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleSalesTransaction(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("production_module")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleProductionModule(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("stock_transfer")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleStockTransfer(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("sales_pur_report")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getRoleSalesPurReport(roleSplit[0]));
		}
		if(roleSplit[1].compareToIgnoreCase("stock_transaction")==0)
		{
			roleAccess.addAll(user_Role_AccessRepository.getStockTransaction(roleSplit[0]));
		}
		
		
		return roleAccess;
	}
	
	public List<User_Role_Access> findAll()
	{
		List<User_Role_Access> userroleList=new ArrayList<User_Role_Access>();
		userroleList.addAll(user_Role_AccessRepository.findAll());
				
		
		
		return userroleList;
	}
	
	public User_Role_Access getUserroleAccessperrole(String role) 
	{
		User_Role_Access selectedroles=user_Role_AccessRepository.searchrole(role);
		return selectedroles;
	}
	
}
