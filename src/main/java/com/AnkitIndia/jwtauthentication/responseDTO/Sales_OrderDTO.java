package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Sales_OrderDTO {

	private Long id;
	
	private String order_id;
	
	private String order_no;
	
	private String company_id;
	
	private String pro_order;
	
	private String order_date;
	
	private String ref_type;
	
	private String business_unit;
	
	private String order_type;
	
	private String valid_till;
	
	private String price_term;
	
	private String cust_channel;
	
	private String cust_refdocno;
	
	private String receipt_criteria;
	
	private String q_status;
	
	private String we_uom;
	
	private String delivery_date;
	
	private String shipment_mode;
	
	private String sales_person;
	
	private String delivery_term;
	
	private String app_chgs_id;
	
	private String remarks;
	
	private String confirmed_by;
	
	private String approval;
	
	private String reason;
	
	private String inv_type;
	
	private String referance_id;
	
	private String customer;
	
	private boolean brokerage_app;
	
	private boolean loading_status;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;
	
	
	 private String salereturn_id;
	 
	
	private String tolerancecheckpoint;

		
	private String tolerancecheckpointremarks;
	 
	
	 private String sales_returnstatus;
	
	 
	 
	 
	 
	 
	//For Customer Name Convert
	private String customer_name;
	
	private double net_amount;
	
	private String cust_ref_doc_date;
	
	private String trans_borne_by_chgs;
	
	 private String total_job_amt;
	 
	 private boolean terminate;
	
	
	
	
	
	
	
	

	public boolean isTerminate() {
		return terminate;
	}

	public void setTerminate(boolean terminate) {
		this.terminate = terminate;
	}

	public String getTotal_job_amt() {
		return total_job_amt;
	}

	public void setTotal_job_amt(String total_job_amt) {
		this.total_job_amt = total_job_amt;
	}

	public String getTolerancecheckpoint() {
		return tolerancecheckpoint;
	}

	public void setTolerancecheckpoint(String tolerancecheckpoint) {
		this.tolerancecheckpoint = tolerancecheckpoint;
	}

	public String getTolerancecheckpointremarks() {
		return tolerancecheckpointremarks;
	}

	public void setTolerancecheckpointremarks(String tolerancecheckpointremarks) {
		this.tolerancecheckpointremarks = tolerancecheckpointremarks;
	}

	public String getTrans_borne_by_chgs() {
		return trans_borne_by_chgs;
	}

	public void setTrans_borne_by_chgs(String trans_borne_by_chgs) {
		this.trans_borne_by_chgs = trans_borne_by_chgs;
	}

	public String getCust_ref_doc_date() {
		return cust_ref_doc_date;
	}

	public void setCust_ref_doc_date(String cust_ref_doc_date) {
		this.cust_ref_doc_date = cust_ref_doc_date;
	}

	public String getSalereturn_id() {
		return salereturn_id;
	}

	public void setSalereturn_id(String salereturn_id) {
		this.salereturn_id = salereturn_id;
	}

	public String getSales_returnstatus() {
		return sales_returnstatus;
	}

	public void setSales_returnstatus(String sales_returnstatus) {
		this.sales_returnstatus = sales_returnstatus;
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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getPro_order() {
		return pro_order;
	}

	public void setPro_order(String pro_order) {
		this.pro_order = pro_order;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
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

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getValid_till() {
		return valid_till;
	}

	public void setValid_till(String valid_till) {
		this.valid_till = valid_till;
	}

	public String getPrice_term() {
		return price_term;
	}

	public void setPrice_term(String price_term) {
		this.price_term = price_term;
	}

	public String getCust_channel() {
		return cust_channel;
	}

	public void setCust_channel(String cust_channel) {
		this.cust_channel = cust_channel;
	}

	public String getCust_refdocno() {
		return cust_refdocno;
	}

	public void setCust_refdocno(String cust_refdocno) {
		this.cust_refdocno = cust_refdocno;
	}

	public String getReceipt_criteria() {
		return receipt_criteria;
	}

	public void setReceipt_criteria(String receipt_criteria) {
		this.receipt_criteria = receipt_criteria;
	}

	public String getQ_status() {
		return q_status;
	}

	public void setQ_status(String q_status) {
		this.q_status = q_status;
	}

	public String getWe_uom() {
		return we_uom;
	}

	public void setWe_uom(String we_uom) {
		this.we_uom = we_uom;
	}

	public String getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}

	public String getShipment_mode() {
		return shipment_mode;
	}

	public void setShipment_mode(String shipment_mode) {
		this.shipment_mode = shipment_mode;
	}

	public String getSales_person() {
		return sales_person;
	}

	public void setSales_person(String sales_person) {
		this.sales_person = sales_person;
	}

	public String getDelivery_term() {
		return delivery_term;
	}

	public void setDelivery_term(String delivery_term) {
		this.delivery_term = delivery_term;
	}

	public String getApp_chgs_id() {
		return app_chgs_id;
	}

	public void setApp_chgs_id(String app_chgs_id) {
		this.app_chgs_id = app_chgs_id;
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
	
	public String getInv_type() {
		return inv_type;
	}

	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
	}

	public String getReferance_id() {
		return referance_id;
	}

	public void setReferance_id(String referance_id) {
		this.referance_id = referance_id;
	}
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public boolean isBrokerage_app() {
		return brokerage_app;
	}

	public void setBrokerage_app(boolean brokerage_app) {
		this.brokerage_app = brokerage_app;
	}
	
	public boolean isLoading_status() {
		return loading_status;
	}

	public void setLoading_status(boolean loading_status) {
		this.loading_status = loading_status;
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

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public double getNet_amount() {
		return net_amount;
	}

	public void setNet_amount(double net_amount) {
		this.net_amount = net_amount;
	}
	
	
}
