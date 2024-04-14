package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;

public class Stk_Transfer_ChallanDTO {
	
	private Long id;
	
	private String stk_challan_id;
	
	private String stk_challan_no;
	
	private String stk_challan_date;
	
	private String cust_ref_doc_no;
	
	private String stk_challan_date2;
	
	private String remark;
	
	private String confirmed_by;
	
	private String approval;
	
	private String reason;
	
	private String ref_type;
	
	private String business_unit;
	
	private double grand_total;
	
	private String order_point;
	
	private String billing_req;
	
	private String passing_wt;
	
	private String reference_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;
	
	private String challan_status;
	
	private String saleinvoice_status;
	
	private String sales_inv_id;
	
	private String delivery_business_unit;
	
	private String weighment_required;
	
	private String vehicle_type;
	
	public String getWeighment_required() {
		return weighment_required;
	}

	public void setWeighment_required(String weighment_required) {
		this.weighment_required = weighment_required;
	}

	public String getVehicle_type() {
		return vehicle_type;
	}

	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}

	public int getUnload_status() {
		return unload_status;
	}

	public void setUnload_status(int unload_status) {
		this.unload_status = unload_status;
	}

	private int unload_status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStk_challan_id() {
		return stk_challan_id;
	}

	public void setStk_challan_id(String stk_challan_id) {
		this.stk_challan_id = stk_challan_id;
	}

	public String getStk_challan_no() {
		return stk_challan_no;
	}

	public void setStk_challan_no(String stk_challan_no) {
		this.stk_challan_no = stk_challan_no;
	}

	public String getStk_challan_date() {
		return stk_challan_date;
	}

	public void setStk_challan_date(String stk_challan_date) {
		this.stk_challan_date = stk_challan_date;
	}

	public String getCust_ref_doc_no() {
		return cust_ref_doc_no;
	}

	public void setCust_ref_doc_no(String cust_ref_doc_no) {
		this.cust_ref_doc_no = cust_ref_doc_no;
	}

	public String getStk_challan_date2() {
		return stk_challan_date2;
	}

	public void setStk_challan_date2(String stk_challan_date2) {
		this.stk_challan_date2 = stk_challan_date2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getConfirmed_by() {
		return confirmed_by;
	}

	public void setConfirmed_by(String confirmed_by) {
		this.confirmed_by = confirmed_by;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRef_type() {
		return ref_type;
	}

	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
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

	public LocalDateTime getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(LocalDateTime deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public String getOrder_point() {
		return order_point;
	}

	public void setOrder_point(String order_point) {
		this.order_point = order_point;
	}

	public String getBilling_req() {
		return billing_req;
	}

	public void setBilling_req(String billing_req) {
		this.billing_req = billing_req;
	}

	public String getPassing_wt() {
		return passing_wt;
	}

	public void setPassing_wt(String passing_wt) {
		this.passing_wt = passing_wt;
	}

	public String getReference_id() {
		return reference_id;
	}

	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}

	public String getChallan_status() {
		return challan_status;
	}

	public void setChallan_status(String challan_status) {
		this.challan_status = challan_status;
	}

	

	public String getSaleinvoice_status() {
		return saleinvoice_status;
	}

	public void setSaleinvoice_status(String saleinvoice_status) {
		this.saleinvoice_status = saleinvoice_status;
	}

	public String getSales_inv_id() {
		return sales_inv_id;
	}

	public void setSales_inv_id(String sales_inv_id) {
		this.sales_inv_id = sales_inv_id;
	}

	public String getDelivery_business_unit() {
		return delivery_business_unit;
	}

	public void setDelivery_business_unit(String delivery_business_unit) {
		this.delivery_business_unit = delivery_business_unit;
	}
	
	
}
