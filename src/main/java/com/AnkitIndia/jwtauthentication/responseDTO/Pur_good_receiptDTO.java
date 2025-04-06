package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Pur_good_receiptDTO {
	
	private Long id;
	
	private String grn_id;
	
	private String grn_no;
	
	private String b_unit;
	
	private String b_unitname;
	
	private String grn_date;
	
	private String supplier_name;
	
	private String supplier;
	
	private boolean item_type;
	
	private String purchase_type;
	
	private String purchase_typename;
	
	private String purchase_subtype;
	
	private String referance_type;
	
	private String sales_process;
	
	private String sales_order;
	
	private String vehicle_id;
	
	private String vehicle_no;
	
	private String applicable_charges_id;
	
	private String remarks;
	
	private boolean brokerage_active;
	
	private String referance_id;
	
	private String company_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private String inserted_by;
	
	private LocalDateTime inserted_on;
	
	private String updated_by;
	
	private LocalDateTime updated_on;

	private String bill_status;
	
	private boolean broker_info; /* new 16-04-2022 */
	
	//For Business Unit Name Show
	private String b_unit_name;               //Right

	private String pur_return_status;
	
	private String purreturnid;
	
	private String party_name;
	
	private String stack_maintain;
	
	public String getStack_maintain() {
		return stack_maintain;
	}

	public void setStack_maintain(String stack_maintain) {
		this.stack_maintain = stack_maintain;
	}

	private String challan_status;
	
	

	public String getChallan_status() {
		return challan_status;
	}

	public void setChallan_status(String challan_status) {
		this.challan_status = challan_status;
	}

	public String getSales_process() {
		return sales_process;
	}

	public void setSales_process(String sales_process) {
		this.sales_process = sales_process;
	}

	public String getSales_order() {
		return sales_order;
	}

	public void setSales_order(String sales_order) {
		this.sales_order = sales_order;
	}

	public String getParty_name() {
		return party_name;
	}

	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}

	public String getPurreturnid() {
		return purreturnid;
	}

	public void setPurreturnid(String purreturnid) {
		this.purreturnid = purreturnid;
	}

	public String getPur_return_status() {
		return pur_return_status;
	}

	public void setPur_return_status(String pur_return_status) {
		this.pur_return_status = pur_return_status;
	}

	public String getBill_status() {
		return bill_status;
	}

	public void setBill_status(String bill_status) {
		this.bill_status = bill_status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrn_id() {
		return grn_id;
	}

	public void setGrn_id(String grn_id) {
		this.grn_id = grn_id;
	}

	public String getGrn_no() {
		return grn_no;
	}

	public void setGrn_no(String grn_no) {
		this.grn_no = grn_no;
	}

	public String getB_unit() {
		return b_unit;
	}

	public void setB_unit(String b_unit) {
		this.b_unit = b_unit;
	}

	public String getB_unitname() {
		return b_unitname;
	}

	public void setB_unitname(String b_unitname) {
		this.b_unitname = b_unitname;
	}

	public String getGrn_date() {
		return grn_date;
	}

	public void setGrn_date(String grn_date) {
		this.grn_date = grn_date;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public boolean isItem_type() {
		return item_type;
	}

	public void setItem_type(boolean item_type) {
		this.item_type = item_type;
	}

	public String getPurchase_type() {
		return purchase_type;
	}

	public void setPurchase_type(String purchase_type) {
		this.purchase_type = purchase_type;
	}

	public String getPurchase_typename() {
		return purchase_typename;
	}

	public void setPurchase_typename(String purchase_typename) {
		this.purchase_typename = purchase_typename;
	}

	public String getPurchase_subtype() {
		return purchase_subtype;
	}

	public void setPurchase_subtype(String purchase_subtype) {
		this.purchase_subtype = purchase_subtype;
	}

	public String getReferance_type() {
		return referance_type;
	}

	public void setReferance_type(String referance_type) {
		this.referance_type = referance_type;
	}

	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public String getApplicable_charges_id() {
		return applicable_charges_id;
	}

	public void setApplicable_charges_id(String applicable_charges_id) {
		this.applicable_charges_id = applicable_charges_id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isBrokerage_active() {
		return brokerage_active;
	}

	public void setBrokerage_active(boolean brokerage_active) {
		this.brokerage_active = brokerage_active;
	}

	public String getReferance_id() {
		return referance_id;
	}

	public void setReferance_id(String referance_id) {
		this.referance_id = referance_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

	public String getB_unit_name() {
		return b_unit_name;
	}

	public void setB_unit_name(String b_unit_name) {
		this.b_unit_name = b_unit_name;
	}

	public boolean isBroker_info() {
		return broker_info;
	}

	public void setBroker_info(boolean broker_info) {
		this.broker_info = broker_info;
	}

	
}
