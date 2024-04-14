package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

public class Stock_TransferDTO {
	
	private Long id;
	
	private String order_id;
	
	private String order_no;
	
	private String ref_type;
	
	private Date order_date;
	
	private String order_point;
	
	private String business_unit;
	
	private String delivery_business_unit;
	
	private String loading_point;
	
	private String unloading_point;
	
	private String weightment_req;
	
	private String tax_info;
	
	private String enway_bill;
	
	private String service_item;
	
	private String shipment_mode;
	
	private String reference;
	
	private String remarks;
	
	private String confirmed_by;
	
	private String approval;
	
	private String reason;
	
	private String approved_remarks;
	
	private String app_chgs_id;
	
	private String order_status;
	
	private String billing_req;
	
	private String applicable_charges_id;
	
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
	
	private boolean loadunload_status;
	
	private String stk_grn_status;
	
	
	
	public String getStk_grn_status() {
		return stk_grn_status;
	}

	public void setStk_grn_status(String stk_grn_status) {
		this.stk_grn_status = stk_grn_status;
	}

	public boolean isLoadunload_status() {
		return loadunload_status;
	}

	public void setLoadunload_status(boolean loadunload_status) {
		this.loadunload_status = loadunload_status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getRef_type() {
		return ref_type;
	}

	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public String getOrder_point() {
		return order_point;
	}

	public void setOrder_point(String order_point) {
		this.order_point = order_point;
	}

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getDelivery_business_unit() {
		return delivery_business_unit;
	}

	public void setDelivery_business_unit(String delivery_business_unit) {
		this.delivery_business_unit = delivery_business_unit;
	}

	public String getLoading_point() {
		return loading_point;
	}

	public void setLoading_point(String loading_point) {
		this.loading_point = loading_point;
	}

	public String getUnloading_point() {
		return unloading_point;
	}

	public void setUnloading_point(String unloading_point) {
		this.unloading_point = unloading_point;
	}

	public String getWeightment_req() {
		return weightment_req;
	}

	public void setWeightment_req(String weightment_req) {
		this.weightment_req = weightment_req;
	}

	public String getTax_info() {
		return tax_info;
	}

	public void setTax_info(String tax_info) {
		this.tax_info = tax_info;
	}

	public String getEnway_bill() {
		return enway_bill;
	}

	public void setEnway_bill(String enway_bill) {
		this.enway_bill = enway_bill;
	}

	public String getService_item() {
		return service_item;
	}

	public void setService_item(String service_item) {
		this.service_item = service_item;
	}

	public String getShipment_mode() {
		return shipment_mode;
	}

	public void setShipment_mode(String shipment_mode) {
		this.shipment_mode = shipment_mode;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getApproved_remarks() {
		return approved_remarks;
	}

	public void setApproved_remarks(String approved_remarks) {
		this.approved_remarks = approved_remarks;
	}

	public String getApp_chgs_id() {
		return app_chgs_id;
	}

	public void setApp_chgs_id(String app_chgs_id) {
		this.app_chgs_id = app_chgs_id;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
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

	public String getBilling_req() {
		return billing_req;
	}

	public void setBilling_req(String billing_req) {
		this.billing_req = billing_req;
	}

	public String getApplicable_charges_id() {
		return applicable_charges_id;
	}

	public void setApplicable_charges_id(String applicable_charges_id) {
		this.applicable_charges_id = applicable_charges_id;
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
	

}
