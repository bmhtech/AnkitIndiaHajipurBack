package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Stk_transfer_grnDTO {
	
	private Long id;
	
	private String stk_grn_id;
	
	private String stk_grn_no;
	
	private String stk_grn_date;	
	
	private String b_unit;
	
	private String b_unitname;
	
	private String item_sub_type;
	
	private String item_type;
	
	private String referance_type;
	
	private String vehicle_id;
	
	private String vehicle_no;
	
	private String applicable_charges_id;
	
	private String remarks;
	
	private boolean brokerage_active;
	
	private String reference_id;
	
	private String username;
	
	private String company_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private String reference_status;
	
	private String receipt_criteria;
	
	private int stk_pur_inv_status;
	
	private String  rec_b_unit;
	
	private String ref_type;
	
	private String sale_inv_status;
	
	public String getSale_inv_status() {
		return sale_inv_status;
	}

	public void setSale_inv_status(String sale_inv_status) {
		this.sale_inv_status = sale_inv_status;
	}

	public String getRef_type() {
		return ref_type;
	}

	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}

	public String getRec_b_unit() {
		return rec_b_unit;
	}

	public void setRec_b_unit(String rec_b_unit) {
		this.rec_b_unit = rec_b_unit;
	}

	
	public int getStk_pur_inv_status() {
		return stk_pur_inv_status;
	}

	public void setStk_pur_inv_status(int stk_pur_inv_status) {
		this.stk_pur_inv_status = stk_pur_inv_status;
	}

	public String getReceipt_criteria() {
		return receipt_criteria;
	}

	public void setReceipt_criteria(String receipt_criteria) {
		this.receipt_criteria = receipt_criteria;
	}

	public String getReference_status() {
		return reference_status;
	}

	public void setReference_status(String reference_status) {
		this.reference_status = reference_status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStk_grn_id() {
		return stk_grn_id;
	}

	public void setStk_grn_id(String stk_grn_id) {
		this.stk_grn_id = stk_grn_id;
	}

	public String getStk_grn_no() {
		return stk_grn_no;
	}

	public void setStk_grn_no(String stk_grn_no) {
		this.stk_grn_no = stk_grn_no;
	}

	public String getStk_grn_date() {
		return stk_grn_date;
	}

	public void setStk_grn_date(String stk_grn_date) {
		this.stk_grn_date = stk_grn_date;
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

	public String getItem_sub_type() {
		return item_sub_type;
	}

	public void setItem_sub_type(String item_sub_type) {
		this.item_sub_type = item_sub_type;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
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

	public String getReference_id() {
		return reference_id;
	}

	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}

	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	
	
}
