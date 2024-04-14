package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Delivery_challanDTO {
	
	private Long id;
	
	private String delivery_cid;
	
	private String challan_no;
	
	private String business_unit;
	
	private String company_id;
	
	private String challan_date;
	
	private boolean bro_info_req;
	
	private String price_term;
	
	private String cust_ref_doc_no;
	
    private Date date2;
    
	private String remark;
    
	private String confirmed_by;
    
	private String approval;
    
	private String reason;
    
	private String ref_type;
    
	private String party;
	
	private String partyname;
	
	private String inv_type;
    
	private String grand_total;
	
	private String referance_id;
	
	private String invoicestatus;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private String salereturn_id;
	 
	private String sales_returnstatus;
	
	private String challan_status;
	
	//For Sales Invoice
	
	private String salesorderno;
	
	private String salesorderdate;
	
	private String sales_invoice_id;
	
	private boolean jobwork;
	
	

	public boolean isJobwork() {
		return jobwork;
	}

	public void setJobwork(boolean jobwork) {
		this.jobwork = jobwork;
	}

	public String getSales_invoice_id() {
		return sales_invoice_id;
	}

	public void setSales_invoice_id(String sales_invoice_id) {
		this.sales_invoice_id = sales_invoice_id;
	}

	public String getChallan_status() {
		return challan_status;
	}

	public void setChallan_status(String challan_status) {
		this.challan_status = challan_status;
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

	public String getDelivery_cid() {
		return delivery_cid;
	}

	public void setDelivery_cid(String delivery_cid) {
		this.delivery_cid = delivery_cid;
	}

	public String getChallan_no() {
		return challan_no;
	}

	public void setChallan_no(String challan_no) {
		this.challan_no = challan_no;
	}
	
	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getChallan_date() {
		return challan_date;
	}

	public void setChallan_date(String challan_date) {
		this.challan_date = challan_date;
	}

	public boolean isBro_info_req() {
		return bro_info_req;
	}

	public void setBro_info_req(boolean bro_info_req) {
		this.bro_info_req = bro_info_req;
	}

	public String getPrice_term() {
		return price_term;
	}

	public void setPrice_term(String price_term) {
		this.price_term = price_term;
	}

	public String getCust_ref_doc_no() {
		return cust_ref_doc_no;
	}

	public void setCust_ref_doc_no(String cust_ref_doc_no) {
		this.cust_ref_doc_no = cust_ref_doc_no;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
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

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}
	
	public String getPartyname() {
		return partyname;
	}

	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}

	public String getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(String grand_total) {
		this.grand_total = grand_total;
	}

	public String getReferance_id() {
		return referance_id;
	}

	public void setReferance_id(String referance_id) {
		this.referance_id = referance_id;
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

	public String getSalesorderno() {
		return salesorderno;
	}

	public void setSalesorderno(String salesorderno) {
		this.salesorderno = salesorderno;
	}

	public String getSalesorderdate() {
		return salesorderdate;
	}

	public void setSalesorderdate(String salesorderdate) {
		this.salesorderdate = salesorderdate;
	}

	public String getInv_type() {
		return inv_type;
	}

	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
	}

	public String getInvoicestatus() {
		return invoicestatus;
	}

	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}
	

}
