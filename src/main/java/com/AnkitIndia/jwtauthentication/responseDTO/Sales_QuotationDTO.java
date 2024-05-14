package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;

public class Sales_QuotationDTO {

	private Long id;
	
	private String quotation_id;
	
	private String quotation_no;
	
	private String quo_type;
	
	private String company_id;
	
	private String quotation_date;
	
	private String valid_till;
	
	private String price_term;
	
	private String pro_order;
	
	private String cust_channel;
	
	private String cust_ref;
	
	private String receipt_ct;
	
	private String we_uom;
	
	private String business_unit;
	
	private String delivery;
	
	private String q_status;
	
	private String shipment_mode;
	
	private String ref_type;
	
	private String sales_person;
	
	private String delivery_term;
	
	private boolean brokerage_app;
	
	private String remarks;
	
	private String confirmed_by;
	
	private String approved;
	
	private String reason;
	
	private String app_chgs_id;
	
	private String inv_type;
	
	private String referance_id;
	
	private String customer;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;
	
	private boolean sale_orderused;
	
	//For Customer Name Convert
	
	private String customer_name;
	
	private String quotationcheckpoint;
	
	private boolean terminate;

	private boolean so_terminate;

	public boolean isSo_terminate() {
		return so_terminate;
	}

	public void setSo_terminate(boolean so_terminate) {
		this.so_terminate = so_terminate;
	}

	public boolean isTerminate() {
		return terminate;
	}

	public void setTerminate(boolean terminate) {
		this.terminate = terminate;
	}

	public String getQuotationcheckpoint() {
		return quotationcheckpoint;
	}

	public void setQuotationcheckpoint(String quotationcheckpoint) {
		this.quotationcheckpoint = quotationcheckpoint;
	}

	public boolean isSale_orderused() {
		return sale_orderused;
	}

	public void setSale_orderused(boolean sale_orderused) {
		this.sale_orderused = sale_orderused;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuotation_id() {
		return quotation_id;
	}

	public void setQuotation_id(String quotation_id) {
		this.quotation_id = quotation_id;
	}

	public String getQuotation_no() {
		return quotation_no;
	}

	public void setQuotation_no(String quotation_no) {
		this.quotation_no = quotation_no;
	}

	public String getQuo_type() {
		return quo_type;
	}

	public void setQuo_type(String quo_type) {
		this.quo_type = quo_type;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
	public String getQuotation_date() {
		return quotation_date;
	}

	public void setQuotation_date(String quotation_date) {
		this.quotation_date = quotation_date;
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

	public String getPro_order() {
		return pro_order;
	}

	public void setPro_order(String pro_order) {
		this.pro_order = pro_order;
	}

	public String getCust_channel() {
		return cust_channel;
	}

	public void setCust_channel(String cust_channel) {
		this.cust_channel = cust_channel;
	}

	public String getCust_ref() {
		return cust_ref;
	}

	public void setCust_ref(String cust_ref) {
		this.cust_ref = cust_ref;
	}

	public String getReceipt_ct() {
		return receipt_ct;
	}

	public void setReceipt_ct(String receipt_ct) {
		this.receipt_ct = receipt_ct;
	}

	public String getWe_uom() {
		return we_uom;
	}

	public void setWe_uom(String we_uom) {
		this.we_uom = we_uom;
	}

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getQ_status() {
		return q_status;
	}

	public void setQ_status(String q_status) {
		this.q_status = q_status;
	}

	public String getShipment_mode() {
		return shipment_mode;
	}

	public void setShipment_mode(String shipment_mode) {
		this.shipment_mode = shipment_mode;
	}

	public String getRef_type() {
		return ref_type;
	}

	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
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
	
	public boolean isBrokerage_app() {
		return brokerage_app;
	}

	public void setBrokerage_app(boolean brokerage_app) {
		this.brokerage_app = brokerage_app;
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

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApp_chgs_id() {
		return app_chgs_id;
	}

	public void setApp_chgs_id(String app_chgs_id) {
		this.app_chgs_id = app_chgs_id;
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
	
	
}
