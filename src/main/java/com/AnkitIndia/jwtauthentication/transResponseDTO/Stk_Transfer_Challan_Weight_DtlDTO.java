package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

public class Stk_Transfer_Challan_Weight_DtlDTO {
	
	private Long id;
	
	private String stk_challan_id;
	
	private String stk_challan_no;
	
	private String own_uom;
	
	private double own_gross;
	
	private String own_tare;
	
	private double own_net;
	
	private String eway_bill_no;
	
	private Date  own_date;
	
	private String own_slip_no;
	
	private String company_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStk_challan_id() {
		return stk_challan_id;
	}

	public void setStk_challan_id(String stk_challan_id) {
		this.stk_challan_id = stk_challan_id;
	}

	public String getStk_challan_no() {
		return stk_challan_no;
	}

	public void setStk_challan_no(String stk_challan_no) {
		this.stk_challan_no = stk_challan_no;
	}

	public String getOwn_uom() {
		return own_uom;
	}

	public void setOwn_uom(String own_uom) {
		this.own_uom = own_uom;
	}

	public double getOwn_gross() {
		return own_gross;
	}

	public void setOwn_gross(double own_gross) {
		this.own_gross = own_gross;
	}

	public String getOwn_tare() {
		return own_tare;
	}

	public void setOwn_tare(String own_tare) {
		this.own_tare = own_tare;
	}

	public double getOwn_net() {
		return own_net;
	}

	public void setOwn_net(double own_net) {
		this.own_net = own_net;
	}

	public String getEway_bill_no() {
		return eway_bill_no;
	}

	public void setEway_bill_no(String eway_bill_no) {
		this.eway_bill_no = eway_bill_no;
	}

	public Date getOwn_date() {
		return own_date;
	}

	public void setOwn_date(Date own_date) {
		this.own_date = own_date;
	}

	public String getOwn_slip_no() {
		return own_slip_no;
	}

	public void setOwn_slip_no(String own_slip_no) {
		this.own_slip_no = own_slip_no;
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
	
	
}
