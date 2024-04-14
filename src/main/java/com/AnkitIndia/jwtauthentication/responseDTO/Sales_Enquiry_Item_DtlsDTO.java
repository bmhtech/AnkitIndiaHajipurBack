package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Sales_Enquiry_Item_DtlsDTO {

	private String enquiry_id;
	
	private String enquiry_no;
	
	private String company_id;
	
	private int slno;
	
	private String item_code;
	
	private String item_name;
	
	private double quantity;
	
	private String uom;
	
	private String packing_item;
	
	private String packing_item_name;
	
	private double packing_quantity;
	
	private String packing_uom;
	
	private String qc_norms;
	
	private double mat_wt;
	
	private double price;
	
	private String tax_code;
	
	private double tax_rate;

	private String remarks;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

	public String getEnquiry_id() {
		return enquiry_id;
	}

	public void setEnquiry_id(String enquiry_id) {
		this.enquiry_id = enquiry_id;
	}

	public String getEnquiry_no() {
		return enquiry_no;
	}

	public void setEnquiry_no(String enquiry_no) {
		this.enquiry_no = enquiry_no;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getPacking_item() {
		return packing_item;
	}

	public void setPacking_item(String packing_item) {
		this.packing_item = packing_item;
	}

	public String getPacking_item_name() {
		return packing_item_name;
	}

	public void setPacking_item_name(String packing_item_name) {
		this.packing_item_name = packing_item_name;
	}

	public double getPacking_quantity() {
		return packing_quantity;
	}

	public void setPacking_quantity(double packing_quantity) {
		this.packing_quantity = packing_quantity;
	}

	public String getPacking_uom() {
		return packing_uom;
	}

	public void setPacking_uom(String packing_uom) {
		this.packing_uom = packing_uom;
	}

	public String getQc_norms() {
		return qc_norms;
	}

	public void setQc_norms(String qc_norms) {
		this.qc_norms = qc_norms;
	}

	public double getMat_wt() {
		return mat_wt;
	}

	public void setMat_wt(double mat_wt) {
		this.mat_wt = mat_wt;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
