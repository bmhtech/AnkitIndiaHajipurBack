package com.AnkitIndia.jwtauthentication.responseDTO;


import java.sql.Date;

import javax.validation.constraints.Size;

public class Wm_unload_advice_party_wmDTO {

	private String unadviceid;
	
	private String unadviceno;
	
	private String company_id;
	
	private String gross_wt;
	
	private String uom1;
	
	private String tare_wt;
	
	private String uom2;
	
	private String net_wt;
	
	private String uom3;
	
	private String slip_no;
	
	private Date pw_date;
	
	private String wb_name;

	public String getUnadviceid() {
		return unadviceid;
	}

	public void setUnadviceid(String unadviceid) {
		this.unadviceid = unadviceid;
	}

	public String getUnadviceno() {
		return unadviceno;
	}

	public void setUnadviceno(String unadviceno) {
		this.unadviceno = unadviceno;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getGross_wt() {
		return gross_wt;
	}

	public void setGross_wt(String gross_wt) {
		this.gross_wt = gross_wt;
	}

	public String getUom1() {
		return uom1;
	}

	public void setUom1(String uom1) {
		this.uom1 = uom1;
	}

	public String getTare_wt() {
		return tare_wt;
	}

	public void setTare_wt(String tare_wt) {
		this.tare_wt = tare_wt;
	}

	public String getUom2() {
		return uom2;
	}

	public void setUom2(String uom2) {
		this.uom2 = uom2;
	}

	public String getNet_wt() {
		return net_wt;
	}

	public void setNet_wt(String net_wt) {
		this.net_wt = net_wt;
	}

	public String getUom3() {
		return uom3;
	}

	public void setUom3(String uom3) {
		this.uom3 = uom3;
	}

	public String getSlip_no() {
		return slip_no;
	}

	public void setSlip_no(String slip_no) {
		this.slip_no = slip_no;
	}

	public Date getPw_date() {
		return pw_date;
	}

	public void setPw_date(Date pw_date) {
		this.pw_date = pw_date;
	}

	public String getWb_name() {
		return wb_name;
	}

	public void setWb_name(String wb_name) {
		this.wb_name = wb_name;
	}
	
}
