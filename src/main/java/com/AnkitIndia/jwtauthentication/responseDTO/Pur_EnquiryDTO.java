package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

public class Pur_EnquiryDTO {

	private Long id;
	
	private String company_id;
	
	private String enquiry_id;
	
	private String enquiry_no;
	
	private String enquiry_date;
	
	private String valid_until;
	
	private String enquiry_type;
	
	private String mode_of_enq;
	
	private String enquiry_status;
	
	private String service_type;
	
	private String service_type_name;
	
	private String referance_type;
	
	private String dept;
	
	private String remarks;
	
	private String document;
	
	private String packing_req;
	
	private String fullfillment_by;
	
	private String fullfillment_type;
	
	private String payment_term;
	
	private String trans_born_by;
	
	private String loc_of_delivery;
	
	private String security_doc;
	
	private String confirmed_by;
	
	private String approved;
	
	private String reason;
	
	private String fin_year;
	
	private String  modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private boolean quotation_status;
	
	private boolean order_status;
	
	private String department_name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getEnquiry_id() {
		return enquiry_id;
	}

	public void setEnquiry_id(String enquiry_id) {
		this.enquiry_id = enquiry_id;
	}

	public String getEnquiry_no() {
		return enquiry_no;
	}

	public void setEnquiry_no(String enquiry_no) {
		this.enquiry_no = enquiry_no;
	}

	public String getEnquiry_date() {
		return enquiry_date;
	}

	public void setEnquiry_date(String enquiry_date) {
		this.enquiry_date = enquiry_date;
	}

	public String getValid_until() {
		return valid_until;
	}

	public void setValid_until(String valid_until) {
		this.valid_until = valid_until;
	}

	public String getEnquiry_type() {
		return enquiry_type;
	}

	public void setEnquiry_type(String enquiry_type) {
		this.enquiry_type = enquiry_type;
	}

	public String getMode_of_enq() {
		return mode_of_enq;
	}

	public void setMode_of_enq(String mode_of_enq) {
		this.mode_of_enq = mode_of_enq;
	}

	public String getEnquiry_status() {
		return enquiry_status;
	}

	public void setEnquiry_status(String enquiry_status) {
		this.enquiry_status = enquiry_status;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	
	public String getService_type_name() {
		return service_type_name;
	}

	public void setService_type_name(String service_type_name) {
		this.service_type_name = service_type_name;
	}

	public String getReferance_type() {
		return referance_type;
	}

	public void setReferance_type(String referance_type) {
		this.referance_type = referance_type;
	}
	
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
	
	public String getPacking_req() {
		return packing_req;
	}

	public void setPacking_req(String packing_req) {
		this.packing_req = packing_req;
	}

	public String getFullfillment_by() {
		return fullfillment_by;
	}

	public void setFullfillment_by(String fullfillment_by) {
		this.fullfillment_by = fullfillment_by;
	}

	public String getFullfillment_type() {
		return fullfillment_type;
	}

	public void setFullfillment_type(String fullfillment_type) {
		this.fullfillment_type = fullfillment_type;
	}

	public String getPayment_term() {
		return payment_term;
	}

	public void setPayment_term(String payment_term) {
		this.payment_term = payment_term;
	}

	public String getTrans_born_by() {
		return trans_born_by;
	}

	public void setTrans_born_by(String trans_born_by) {
		this.trans_born_by = trans_born_by;
	}

	public String getLoc_of_delivery() {
		return loc_of_delivery;
	}

	public void setLoc_of_delivery(String loc_of_delivery) {
		this.loc_of_delivery = loc_of_delivery;
	}

	public String getSecurity_doc() {
		return security_doc;
	}

	public void setSecurity_doc(String security_doc) {
		this.security_doc = security_doc;
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

	public boolean isQuotation_status() {
		return quotation_status;
	}

	public void setQuotation_status(boolean quotation_status) {
		this.quotation_status = quotation_status;
	}

	public boolean isOrder_status() {
		return order_status;
	}

	public void setOrder_status(boolean order_status) {
		this.order_status = order_status;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	
	
}
