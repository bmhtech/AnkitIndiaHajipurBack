package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;


public class Delivery_challan_Trans_InfoDTO {
	
	private Long id;
	
	private String delivery_cid;
	
	private String company_id;
	
	private String trans_borne_by;
	
	private String mode_of_trans;
	
	private String trans_code;
	
	private String transporter_name;
	
	private String charge_code;
	
	private String vehle_no;
	
	private double freight_amt;
    
	private String transport_rate;
	
	private double adv_paid;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

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

	public String getTransport_rate() {
		return transport_rate;
	}

	public void setTransport_rate(String transport_rate) {
		this.transport_rate = transport_rate;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getTrans_borne_by() {
		return trans_borne_by;
	}

	public void setTrans_borne_by(String trans_borne_by) {
		this.trans_borne_by = trans_borne_by;
	}

	public String getMode_of_trans() {
		return mode_of_trans;
	}

	public void setMode_of_trans(String mode_of_trans) {
		this.mode_of_trans = mode_of_trans;
	}

	public String getTrans_code() {
		return trans_code;
	}

	public void setTrans_code(String trans_code) {
		this.trans_code = trans_code;
	}

	public String getTransporter_name() {
		return transporter_name;
	}

	public void setTransporter_name(String transporter_name) {
		this.transporter_name = transporter_name;
	}

	public String getCharge_code() {
		return charge_code;
	}

	public void setCharge_code(String charge_code) {
		this.charge_code = charge_code;
	}

	public String getVehle_no() {
		return vehle_no;
	}

	public void setVehle_no(String vehle_no) {
		this.vehle_no = vehle_no;
	}

	public double getFreight_amt() {
		return freight_amt;
	}

	public void setFreight_amt(double freight_amt) {
		this.freight_amt = freight_amt;
	}

	public double getAdv_paid() {
		return adv_paid;
	}

	public void setAdv_paid(double adv_paid) {
		this.adv_paid = adv_paid;
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
