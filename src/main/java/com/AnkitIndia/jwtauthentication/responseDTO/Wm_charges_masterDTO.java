package com.AnkitIndia.jwtauthentication.responseDTO;

public class Wm_charges_masterDTO {
	
	private Long id;
	
	private String wm_charge_id;
	
	private String wm_charge_code;
	
	private String wm_charge_desc;
	
	private String vehicle_type;
	
	private String bu_unit;
	
	private double amount;
	
	private String tax_code;
	
	private double tax_rate;
	
	private String wm_charge_acc;
	
	private String fin_year;
	
	private String modified_type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWm_charge_id() {
		return wm_charge_id;
	}

	public void setWm_charge_id(String wm_charge_id) {
		this.wm_charge_id = wm_charge_id;
	}

	public String getWm_charge_code() {
		return wm_charge_code;
	}

	public void setWm_charge_code(String wm_charge_code) {
		this.wm_charge_code = wm_charge_code;
	}

	public String getWm_charge_desc() {
		return wm_charge_desc;
	}

	public void setWm_charge_desc(String wm_charge_desc) {
		this.wm_charge_desc = wm_charge_desc;
	}

	public String getVehicle_type() {
		return vehicle_type;
	}

	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}

	public String getBu_unit() {
		return bu_unit;
	}

	public void setBu_unit(String bu_unit) {
		this.bu_unit = bu_unit;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTax_code() {
		return tax_code;
	}

	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}

	public double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(double tax_rate) {
		this.tax_rate = tax_rate;
	}

	public String getWm_charge_acc() {
		return wm_charge_acc;
	}

	public void setWm_charge_acc(String wm_charge_acc) {
		this.wm_charge_acc = wm_charge_acc;
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

	public Wm_charges_masterDTO() {
		super();
	}

	public Wm_charges_masterDTO(Long id, String wm_charge_id, String wm_charge_code, String wm_charge_desc,
			String vehicle_type, String bu_unit, double amount, String tax_code, double tax_rate, String wm_charge_acc,
			String fin_year, String modified_type) {
		super();
		this.id = id;
		this.wm_charge_id = wm_charge_id;
		this.wm_charge_code = wm_charge_code;
		this.wm_charge_desc = wm_charge_desc;
		this.vehicle_type = vehicle_type;
		this.bu_unit = bu_unit;
		this.amount = amount;
		this.tax_code = tax_code;
		this.tax_rate = tax_rate;
		this.wm_charge_acc = wm_charge_acc;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
	}

}
