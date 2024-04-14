package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

public class Transportation_chgs_matrix_detailsDTO {
	
private Long id;
	
	private String tcm_id;
	private String tcm_code;
	private String trans_charge_code;
	private String transportation_from;
	private String transportation_to;
	private Long distance_in_km;
	private String vehicles_type;
	private String uom;
	
	private String full_truck_load_rate;
	
	private String rate_uom;
	
	private String tax_rate;
	
	
//	private String tds_rate;
	
	private String [] transporter;
	
	private String transporter_array;
	
	private String allowed_shortage;
	
	private String deduction_basedon;
	
	private String tax_code;
	
	private String transportation_acc;
	
	//private String tds_code;
	//private String tds_acc;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTcm_id() {
		return tcm_id;
	}
	public void setTcm_id(String tcm_id) {
		this.tcm_id = tcm_id;
	}
	public String getTcm_code() {
		return tcm_code;
	}
	public void setTcm_code(String tcm_code) {
		this.tcm_code = tcm_code;
	}
	public String getTrans_charge_code() {
		return trans_charge_code;
	}
	public void setTrans_charge_code(String trans_charge_code) {
		this.trans_charge_code = trans_charge_code;
	}
	public String getTransportation_from() {
		return transportation_from;
	}
	public void setTransportation_from(String transportation_from) {
		this.transportation_from = transportation_from;
	}
	public String getTransportation_to() {
		return transportation_to;
	}
	public void setTransportation_to(String transportation_to) {
		this.transportation_to = transportation_to;
	}
	public Long getDistance_in_km() {
		return distance_in_km;
	}
	public void setDistance_in_km(Long distance_in_km) {
		this.distance_in_km = distance_in_km;
	}
	public String getVehicles_type() {
		return vehicles_type;
	}
	public void setVehicles_type(String vehicles_type) {
		this.vehicles_type = vehicles_type;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getFull_truck_load_rate() {
		return full_truck_load_rate;
	}
	public void setFull_truck_load_rate(String full_truck_load_rate) {
		this.full_truck_load_rate = full_truck_load_rate;
	}
	public String getRate_uom() {
		return rate_uom;
	}
	public void setRate_uom(String rate_uom) {
		this.rate_uom = rate_uom;
	}
	public String getTax_rate() {
		return tax_rate;
	}
	public void setTax_rate(String tax_rate) {
		this.tax_rate = tax_rate;
	}
	public String[] getTransporter() {
		return transporter;
	}
	public void setTransporter(String[] transporter) {
		this.transporter = transporter;
	}
	public String getTransporter_array() {
		return transporter_array;
	}
	public void setTransporter_array(String transporter_array) {
		this.transporter_array = transporter_array;
	}
	public String getAllowed_shortage() {
		return allowed_shortage;
	}
	public void setAllowed_shortage(String allowed_shortage) {
		this.allowed_shortage = allowed_shortage;
	}
	public String getDeduction_basedon() {
		return deduction_basedon;
	}
	public void setDeduction_basedon(String deduction_basedon) {
		this.deduction_basedon = deduction_basedon;
	}
	public String getTax_code() {
		return tax_code;
	}
	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}
	public String getTransportation_acc() {
		return transportation_acc;
	}
	public void setTransportation_acc(String transportation_acc) {
		this.transportation_acc = transportation_acc;
	}

}
