package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Sales_Order_Party_DtlsDTO {

	private String order_id;
	
	private String order_no;
	
	private String company_id;
	
	private int sl_no;
	
	private String p_code;
	
	private String p_name;
	
	private String cp_name;
	
	private Long cp_contact;
	
	private String cp_city;

	private boolean city_approved;
	
	private String send_via;
	
	private String tcs_applicable;
	
	private double tcs_rate;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getP_code() {
		return p_code;
	}

	public void setP_code(String p_code) {
		this.p_code = p_code;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getCp_name() {
		return cp_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public Long getCp_contact() {
		return cp_contact;
	}

	public void setCp_contact(Long cp_contact) {
		this.cp_contact = cp_contact;
	}

	public String getCp_city() {
		return cp_city;
	}

	public void setCp_city(String cp_city) {
		this.cp_city = cp_city;
	}

	public boolean isCity_approved() {
		return city_approved;
	}

	public void setCity_approved(boolean city_approved) {
		this.city_approved = city_approved;
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

	public String getSend_via() {
		return send_via;
	}

	public void setSend_via(String send_via) {
		this.send_via = send_via;
	}

	public String getTcs_applicable() {
		return tcs_applicable;
	}

	public void setTcs_applicable(String tcs_applicable) {
		this.tcs_applicable = tcs_applicable;
	}

	public double getTcs_rate() {
		return tcs_rate;
	}

	public void setTcs_rate(double tcs_rate) {
		this.tcs_rate = tcs_rate;
	}
	
	
}
