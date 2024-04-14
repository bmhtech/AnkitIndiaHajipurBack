package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Return_approval_noteDTO {

	private Long id;
	
	private String salesreturnid;
	
	private String salesreturnno;
	
	private String salesreturntype;
	
	private String inv_type;
	
	private String party;
	
	private String partyname; 
	
	private String salesreturndate;
	
	private String businessunit;
	
	private String businessunitname;
	
	private String returncriteria;
	
	private String returnbasedon;
	
	private String salesreturnrefno;
	
	private String remark; 
	
	private String confirmedby;
	
	private String approval;
	
	private String reason; 
	
	private double  grandtotal;
	
	private String referance_id;
	
	private String weighment_id;
	
	private int reapp_note_status;
	
	private int weighment_status;
	
	private String srn_status;
	
	private String unload_status;
	
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
	
	private String diliverylist;
	
	private boolean jobwork;
	

	public boolean isJobwork() {
		return jobwork;
	}

	public void setJobwork(boolean jobwork) {
		this.jobwork = jobwork;
	}

	public String getDiliverylist() {
		return diliverylist;
	}

	public void setDiliverylist(String diliverylist) {
		this.diliverylist = diliverylist;
	}

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

	public String getSalesreturnid() {
		return salesreturnid;
	}

	public void setSalesreturnid(String salesreturnid) {
		this.salesreturnid = salesreturnid;
	}

	public String getSalesreturnno() {
		return salesreturnno;
	}

	public void setSalesreturnno(String salesreturnno) {
		this.salesreturnno = salesreturnno;
	}

	public String getSalesreturntype() {
		return salesreturntype;
	}

	public void setSalesreturntype(String salesreturntype) {
		this.salesreturntype = salesreturntype;
	}
	
	public String getInv_type() {
		return inv_type;
	}

	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
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

	public String getSalesreturndate() {
		return salesreturndate;
	}

	public void setSalesreturndate(String salesreturndate) {
		this.salesreturndate = salesreturndate;
	}

	public String getBusinessunit() {
		return businessunit;
	}

	public void setBusinessunit(String businessunit) {
		this.businessunit = businessunit;
	}

	public String getBusinessunitname() {
		return businessunitname;
	}

	public void setBusinessunitname(String businessunitname) {
		this.businessunitname = businessunitname;
	}

	public String getReturncriteria() {
		return returncriteria;
	}

	public void setReturncriteria(String returncriteria) {
		this.returncriteria = returncriteria;
	}

	public String getReturnbasedon() {
		return returnbasedon;
	}

	public void setReturnbasedon(String returnbasedon) {
		this.returnbasedon = returnbasedon;
	}

	public String getSalesreturnrefno() {
		return salesreturnrefno;
	}

	public void setSalesreturnrefno(String salesreturnrefno) {
		this.salesreturnrefno = salesreturnrefno;
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
	
	public String getWeighment_id() {
		return weighment_id;
	}

	public void setWeighment_id(String weighment_id) {
		this.weighment_id = weighment_id;
	}

	public int getReapp_note_status() {
		return reapp_note_status;
	}

	public void setReapp_note_status(int reapp_note_status) {
		this.reapp_note_status = reapp_note_status;
	}
	
	public String getSrn_status() {
		return srn_status;
	}

	public void setSrn_status(String srn_status) {
		this.srn_status = srn_status;
	}
	
	public String getUnload_status() {
		return unload_status;
	}

	public void setUnload_status(String unload_status) {
		this.unload_status = unload_status;
	}

	public int getWeighment_status() {
		return weighment_status;
	}

	public void setWeighment_status(int weighment_status) {
		this.weighment_status = weighment_status;
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

	@Override
	public String toString() {
		return "Return_approval_noteDTO [id=" + id + ", salesreturnid=" + salesreturnid + ", salesreturnno="
				+ salesreturnno + ", salesreturntype=" + salesreturntype + ", party=" + party + ", partyname="
				+ partyname + ", salesreturndate=" + salesreturndate + ", businessunit=" + businessunit
				+ ", businessunitname=" + businessunitname + ", returncriteria=" + returncriteria + ", returnbasedon="
				+ returnbasedon + ", salesreturnrefno=" + salesreturnrefno + ", remark=" + remark + ", confirmedby="
				+ confirmedby + ", approval=" + approval + ", reason=" + reason + ", grandtotal=" + grandtotal
				+ ", referance_id=" + referance_id + ", reapp_note_status=" + reapp_note_status + ", weighment_status="
				+ weighment_status + ", company_id=" + company_id + ", fin_year=" + fin_year + ", username=" + username
				+ ", modified_type=" + modified_type + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by
				+ ", updated_on=" + updated_on + ", updated_by=" + updated_by + "]";
	}
	 
	 
}
