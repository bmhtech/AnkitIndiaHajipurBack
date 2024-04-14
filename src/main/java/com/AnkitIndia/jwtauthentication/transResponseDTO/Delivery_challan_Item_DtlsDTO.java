package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;


public class Delivery_challan_Item_DtlsDTO {
	
	private Long id;
	
	private String delivery_cid;
	
	private String company_id;
    
    private int slno;
	
	private String item_code;
	
	private String item_name;
	
	private String packing;
	
	private String packing_name;
	
	private String hsn_code;
	
	private double quantity;
	
	private String uom;
	
	private double squantity;
	
	private String suom;
	
	private double mat_wt;
	
	private double price;

	private String price_based_on;
	
	private double amount;
	
	private String discount_type;
	
	private double discount_rate;
		
	private double discount_amt;
	
	private String tax_code;
	
	private double tax_rate;
	
	private double cgstamt;
	
	private double sgstamt;
	
	private double igstamt;
	
	private double tax_amt;
	
	private double total_amt;
	
	private String acc_norms;
	
	private double discount_amount;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

	private int bags;
	
	public int getBags() {
		return bags;
	}

	public void setBags(int bags) {
		this.bags = bags;
	}

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

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}
	
	public String getPacking_name() {
		return packing_name;
	}

	public void setPacking_name(String packing_name) {
		this.packing_name = packing_name;
	}
	
	public String getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
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

	public double getSquantity() {
		return squantity;
	}

	public void setSquantity(double squantity) {
		this.squantity = squantity;
	}

	public String getSuom() {
		return suom;
	}

	public void setSuom(String suom) {
		this.suom = suom;
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

	public String getPrice_based_on() {
		return price_based_on;
	}

	public void setPrice_based_on(String price_based_on) {
		this.price_based_on = price_based_on;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDiscount_type() {
		return discount_type;
	}

	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}

	public double getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(double discount_rate) {
		this.discount_rate = discount_rate;
	}

	public double getDiscount_amt() {
		return discount_amt;
	}

	public void setDiscount_amt(double discount_amt) {
		this.discount_amt = discount_amt;
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

	public double getTax_amt() {
		return tax_amt;
	}

	public void setTax_amt(double tax_amt) {
		this.tax_amt = tax_amt;
	}

	public double getTotal_amt() {
		return total_amt;
	}

	public void setTotal_amt(double total_amt) {
		this.total_amt = total_amt;
	}

	public String getAcc_norms() {
		return acc_norms;
	}

	public void setAcc_norms(String acc_norms) {
		this.acc_norms = acc_norms;
	}

	public double getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
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

	public double getCgstamt() {
		return cgstamt;
	}

	public void setCgstamt(double cgstamt) {
		this.cgstamt = cgstamt;
	}

	public double getSgstamt() {
		return sgstamt;
	}

	public void setSgstamt(double sgstamt) {
		this.sgstamt = sgstamt;
	}

	public double getIgstamt() {
		return igstamt;
	}

	public void setIgstamt(double igstamt) {
		this.igstamt = igstamt;
	}
	
	

}
