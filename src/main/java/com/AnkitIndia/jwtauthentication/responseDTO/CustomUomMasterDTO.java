package com.AnkitIndia.jwtauthentication.responseDTO;

public class CustomUomMasterDTO {
	
	private String customuom_id;
	
	private String customuom_code;
	
	private String description;
	
	private String customuom_catg;
	
	private String uom_conv_fac;
	
	private String cal_method;
	
	private String type_term;
	
	private boolean uom_active; 
	
	private String remarks;
	
	private String fin_year;
	
	private int export;
	
	private int decimal;
	
	private String response;
	
	public String getCustomuom_id() {
		return customuom_id;
	}

	public void setCustomuom_id(String customuom_id) {
		this.customuom_id = customuom_id;
	}

	public String getCustomuom_code() {
		return customuom_code;
	}

	public void setCustomuom_code(String customuom_code) {
		this.customuom_code = customuom_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomuom_catg() {
		return customuom_catg;
	}

	public void setCustomuom_catg(String customuom_catg) {
		this.customuom_catg = customuom_catg;
	}

	public String getUom_conv_fac() {
		return uom_conv_fac;
	}

	public void setUom_conv_fac(String uom_conv_fac) {
		this.uom_conv_fac = uom_conv_fac;
	}

	public String getCal_method() {
		return cal_method;
	}

	public void setCal_method(String cal_method) {
		this.cal_method = cal_method;
	}

	public String getType_term() {
		return type_term;
	}

	public void setType_term(String type_term) {
		this.type_term = type_term;
	}

	public boolean isUom_active() {
		return uom_active;
	}

	public void setUom_active(boolean uom_active) {
		this.uom_active = uom_active;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	
	
	
	
	public int getExport() {
		return export;
	}

	public void setExport(int export) {
		this.export = export;
	}

	
	
	public int getDecimal() {
		return decimal;
	}

	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}

	
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public CustomUomMasterDTO() {
		super();
	}

	public CustomUomMasterDTO(String customuom_id, String customuom_code, String description, String customuom_catg,
			String uom_conv_fac, String cal_method, String type_term, boolean uom_active, String remarks,
			String fin_year,int export,int decimal,String response) {
		super();
		this.customuom_id = customuom_id;
		this.customuom_code = customuom_code;
		this.description = description;
		this.customuom_catg = customuom_catg;
		this.uom_conv_fac = uom_conv_fac;
		this.cal_method = cal_method;
		this.type_term = type_term;
		this.uom_active = uom_active;
		this.remarks = remarks;
		this.fin_year = fin_year;
		this.export=export;
		this.decimal = decimal;
		this.response = response;
	}
	
}
