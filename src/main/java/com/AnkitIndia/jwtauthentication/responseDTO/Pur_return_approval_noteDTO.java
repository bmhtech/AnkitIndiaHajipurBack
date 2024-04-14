package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Pur_return_approval_noteDTO {

	
	private Long id;
	
	private String purreturnid;
	
	private String purreturnno;
	
	private String purreturntype;
	
	private String ser_item_type;
	
	private String ser_item_subtype;
	
	private String purchase_subtype;
	
	private String supplier; 
	
	private String suppliername;
	
	private String purreturndate;
	
	private String businessunit;
	
	private String businessunit_name;
   
	private String returncriteria;
	
	private String returnbasedon;
	
	private String purreturnrefno;
	
	private String remark; 
	
	private String confirmedby;
	
	private String approval;
	
	private String reason; 
	
	private double  grandtotal;
	
	private String referance_id;
	
	private String weighment_id;
	
	private int reapp_note_status;
	
	private int weighment_status;
	
	private String prn_status;
	
	private String loading_status;
	
	private String company_id;
	    
	private String fin_year;
	    
	private String username;
		
	private String modified_type;
		
	private LocalDateTime inserted_on;
		
	private String inserted_by;
		
	private LocalDateTime updated_on;
		
	private String updated_by;
		
	private LocalDateTime deleted_on;
		
	private String deleted_by;
	 
	private String grnlist;
	
	
	public String getGrnlist() {
		return grnlist;
	}

	public void setGrnlist(String grnlist) {
		this.grnlist = grnlist;
	}

	public int getReapp_note_status() {
		return reapp_note_status;
	}

	public void setReapp_note_status(int reapp_note_status) {
		this.reapp_note_status = reapp_note_status;
	}

	public int getWeighment_status() {
		return weighment_status;
	}

	public void setWeighment_status(int weighment_status) {
		this.weighment_status = weighment_status;
	}

	public String getPrn_status() {
		return prn_status;
	}

	public void setPrn_status(String prn_status) {
		this.prn_status = prn_status;
	}

	public String getLoading_status() {
		return loading_status;
	}

	public void setLoading_status(String loading_status) {
		this.loading_status = loading_status;
	}

	public String getBusinessunit_name() {
		return businessunit_name;
	}

	public void setBusinessunit_name(String businessunit_name) {
		this.businessunit_name = businessunit_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPurreturnid() {
		return purreturnid;
	}

	public void setPurreturnid(String purreturnid) {
		this.purreturnid = purreturnid;
	}

	public String getPurreturnno() {
		return purreturnno;
	}

	public void setPurreturnno(String purreturnno) {
		this.purreturnno = purreturnno;
	}

	public String getPurreturntype() {
		return purreturntype;
	}

	public void setPurreturntype(String purreturntype) {
		this.purreturntype = purreturntype;
	}

	public String getSer_item_type() {
		return ser_item_type;
	}

	public void setSer_item_type(String ser_item_type) {
		this.ser_item_type = ser_item_type;
	}

	public String getSer_item_subtype() {
		return ser_item_subtype;
	}

	public void setSer_item_subtype(String ser_item_subtype) {
		this.ser_item_subtype = ser_item_subtype;
	}
	
	public String getPurchase_subtype() {
		return purchase_subtype;
	}

	public void setPurchase_subtype(String purchase_subtype) {
		this.purchase_subtype = purchase_subtype;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getPurreturndate() {
		return purreturndate;
	}

	public void setPurreturndate(String purreturndate) {
		this.purreturndate = purreturndate;
	}

	public String getBusinessunit() {
		return businessunit;
	}

	public void setBusinessunit(String businessunit) {
		this.businessunit = businessunit;
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

	public String getPurreturnrefno() {
		return purreturnrefno;
	}

	public void setPurreturnrefno(String purreturnrefno) {
		this.purreturnrefno = purreturnrefno;
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
	 
	 
}
