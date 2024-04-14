package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;

public class Sales_return_noteDTO {
	
	private Long id;
	
	private String salesreturnnoteid;
	
	private String salesreturnnoteno;
	
	private String inv_type;
	
	private String inv_typename;
	
	private String party;
	
	private String partyname; 
	
	private String salesreturnnotedate;
	
	private String businessunit;
	
	private String businessunitname;
	
	private String salesreturnnoterefno;
	
	private String price_term;
	
	private String cust_ref_doc_no;
	
    private Date date2;
	
	private String remark; 
	
	private String confirmedby;
	
	private String approval;
	
	private String reason; 
	
	private double  grandtotal;
	
	private String referance_id;
	
	 private String company_id;
	    
	 private String fin_year;
	    
	 private String username;
		
	 private String modified_type;
		
	 private LocalDateTime inserted_on;
		
	 private String inserted_by;
		
	 private LocalDateTime updated_on;
		
	 private String updated_by;
	 
	 private String credit_note_id;
	 
	 private String credit_note_status;

	public String getCredit_note_id() {
		return credit_note_id;
	}

	public void setCredit_note_id(String credit_note_id) {
		this.credit_note_id = credit_note_id;
	}

	public String getCredit_note_status() {
		return credit_note_status;
	}

	public void setCredit_note_status(String credit_note_status) {
		this.credit_note_status = credit_note_status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalesreturnnoteid() {
		return salesreturnnoteid;
	}

	public void setSalesreturnnoteid(String salesreturnnoteid) {
		this.salesreturnnoteid = salesreturnnoteid;
	}

	public String getSalesreturnnoteno() {
		return salesreturnnoteno;
	}

	public void setSalesreturnnoteno(String salesreturnnoteno) {
		this.salesreturnnoteno = salesreturnnoteno;
	}

	public String getInv_type() {
		return inv_type;
	}

	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
	}
	
	public String getInv_typename() {
		return inv_typename;
	}

	public void setInv_typename(String inv_typename) {
		this.inv_typename = inv_typename;
	}

	public String getPartyname() {
		return partyname;
	}

	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}

	public String getSalesreturnnotedate() {
		return salesreturnnotedate;
	}

	public void setSalesreturnnotedate(String salesreturnnotedate) {
		this.salesreturnnotedate = salesreturnnotedate;
	}

	public String getBusinessunit() {
		return businessunit;
	}

	public void setBusinessunit(String businessunit) {
		this.businessunit = businessunit;
	}

	public String getSalesreturnnoterefno() {
		return salesreturnnoterefno;
	}

	public void setSalesreturnnoterefno(String salesreturnnoterefno) {
		this.salesreturnnoterefno = salesreturnnoterefno;
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

	public String getConfirmedby() {
		return confirmedby;
	}

	public void setConfirmedby(String confirmedby) {
		this.confirmedby = confirmedby;
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

	public double getGrandtotal() {
		return grandtotal;
	}

	public void setGrandtotal(double grandtotal) {
		this.grandtotal = grandtotal;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getBusinessunitname() {
		return businessunitname;
	}

	public void setBusinessunitname(String businessunitname) {
		this.businessunitname = businessunitname;
	}

	@Override
	public String toString() {
		return "Sales_return_noteDTO [id=" + id + ", salesreturnnoteid=" + salesreturnnoteid + ", salesreturnnoteno="
				+ salesreturnnoteno + ", inv_type=" + inv_type + ", party=" + party + ", partyname=" + partyname
				+ ", salesreturnnotedate=" + salesreturnnotedate + ", businessunit=" + businessunit
				+ ", businessunitname=" + businessunitname + ", salesreturnnoterefno=" + salesreturnnoterefno
				+ ", price_term=" + price_term + ", cust_ref_doc_no=" + cust_ref_doc_no + ", date2=" + date2
				+ ", remark=" + remark + ", confirmedby=" + confirmedby + ", approval=" + approval + ", reason="
				+ reason + ", grandtotal=" + grandtotal + ", referance_id=" + referance_id + ", company_id="
				+ company_id + ", fin_year=" + fin_year + ", username=" + username + ", modified_type=" + modified_type
				+ ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by + ", updated_on=" + updated_on
				+ ", updated_by=" + updated_by + "]";
	}
	 
	 
}
