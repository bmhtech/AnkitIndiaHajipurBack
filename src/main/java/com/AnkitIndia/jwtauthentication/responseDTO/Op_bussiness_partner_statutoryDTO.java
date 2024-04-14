package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.validation.constraints.Size;

public class Op_bussiness_partner_statutoryDTO {

	private String bp_Id;
	
	private String company_id;
	
	private boolean registered;
	
	private String pan_no;
	
	private String vat_no;
	
	private String tin_no;
	
	private String cst_no;
	
	private String tan_no;
	
	private String service_tax;
	
	private boolean excise_app;
	
	private String ecc_no;
	
	private String  ce_reg_no;
	
	private String ce_range;
	
	private String ce_dev;
	
	private String ce_comm;

	public String getBp_Id() {
		return bp_Id;
	}

	public void setBp_Id(String bp_Id) {
		this.bp_Id = bp_Id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getVat_no() {
		return vat_no;
	}

	public void setVat_no(String vat_no) {
		this.vat_no = vat_no;
	}

	public String getTin_no() {
		return tin_no;
	}

	public void setTin_no(String tin_no) {
		this.tin_no = tin_no;
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

	public String getService_tax() {
		return service_tax;
	}

	public void setService_tax(String service_tax) {
		this.service_tax = service_tax;
	}

	public boolean isExcise_app() {
		return excise_app;
	}

	public void setExcise_app(boolean excise_app) {
		this.excise_app = excise_app;
	}

	public String getEcc_no() {
		return ecc_no;
	}

	public void setEcc_no(String ecc_no) {
		this.ecc_no = ecc_no;
	}

	public String getCe_reg_no() {
		return ce_reg_no;
	}

	public void setCe_reg_no(String ce_reg_no) {
		this.ce_reg_no = ce_reg_no;
	}

	public String getCe_range() {
		return ce_range;
	}

	public void setCe_range(String ce_range) {
		this.ce_range = ce_range;
	}

	public String getCe_dev() {
		return ce_dev;
	}

	public void setCe_dev(String ce_dev) {
		this.ce_dev = ce_dev;
	}

	public String getCe_comm() {
		return ce_comm;
	}

	public void setCe_comm(String ce_comm) {
		this.ce_comm = ce_comm;
	}
	
}
