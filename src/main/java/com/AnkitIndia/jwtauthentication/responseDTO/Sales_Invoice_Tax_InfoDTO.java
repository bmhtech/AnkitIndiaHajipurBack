package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

public class Sales_Invoice_Tax_InfoDTO {
	
private String invoice_id;
	
	private String invoice_no;
	
	private String pan_no;
	
	private String lst_vat_no;
	
	private String cst_no;
	
	private String tan_no;
	
	private String service_no;
	
	private String tin_no;
	
	private String company_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getLst_vat_no() {
		return lst_vat_no;
	}

	public void setLst_vat_no(String lst_vat_no) {
		this.lst_vat_no = lst_vat_no;
	}

	public String getCst_no() {
		return cst_no;
	}

	public void setCst_no(String cst_no) {
		this.cst_no = cst_no;
	}

	public String getTan_no() {
		return tan_no;
	}

	public void setTan_no(String tan_no) {
		this.tan_no = tan_no;
	}

	public String getService_no() {
		return service_no;
	}

	public void setService_no(String service_no) {
		this.service_no = service_no;
	}

	public String getTin_no() {
		return tin_no;
	}

	public void setTin_no(String tin_no) {
		this.tin_no = tin_no;
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
