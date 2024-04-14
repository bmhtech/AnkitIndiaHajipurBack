package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Stock_Transfer_Item_DtlsDTO {
	
	private Long id;
	
	private String order_id;
	
	private String order_no;
	
	private String slno;
	
	private String  item_code;
	
	private String item_name;
	
	private String packing;
	
	private String packing_name;
	
	private String quantity;
	
	private String uom;
	
	private String squantity;
	
	private String suom;
	
	private double mat_wt;
	
	private double price;
	
	private String price_based_on;
	
	private String amount;
	
	private double gross_amt;
	
	private String tax_id;
	
	private String tax_rate;
	
	private double tax_amt;
	
	private double net_amt;
	
	private String acc_norms;
	
	private String warehouse;
	
	private String warehouse_name;
	
	private String rack;
	
	private String rack_name;
	
	private String remarks;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

	private double cgst_amt;
	
	private double sgst_amt;
	
	private double igst_amt;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getSlno() {
		return slno;
	}

	public void setSlno(String slno) {
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getSquantity() {
		return squantity;
	}

	public void setSquantity(String squantity) {
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public double getGross_amt() {
		return gross_amt;
	}

	public void setGross_amt(double gross_amt) {
		this.gross_amt = gross_amt;
	}

	public String getTax_id() {
		return tax_id;
	}

	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}

	public String getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(String tax_rate) {
		this.tax_rate = tax_rate;
	}

	public double getTax_amt() {
		return tax_amt;
	}

	public void setTax_amt(double tax_amt) {
		this.tax_amt = tax_amt;
	}

	public double getNet_amt() {
		return net_amt;
	}

	public void setNet_amt(double net_amt) {
		this.net_amt = net_amt;
	}

	public String getAcc_norms() {
		return acc_norms;
	}

	public void setAcc_norms(String acc_norms) {
		this.acc_norms = acc_norms;
	}
	
	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getWarehouse_name() {
		return warehouse_name;
	}

	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}

	public String getRack() {
		return rack;
	}

	public void setRack(String rack) {
		this.rack = rack;
	}

	public String getRack_name() {
		return rack_name;
	}

	public void setRack_name(String rack_name) {
		this.rack_name = rack_name;
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

	public double getCgst_amt() {
		return cgst_amt;
	}

	public void setCgst_amt(double cgst_amt) {
		this.cgst_amt = cgst_amt;
	}

	public double getSgst_amt() {
		return sgst_amt;
	}

	public void setSgst_amt(double sgst_amt) {
		this.sgst_amt = sgst_amt;
	}

	public double getIgst_amt() {
		return igst_amt;
	}

	public void setIgst_amt(double igst_amt) {
		this.igst_amt = igst_amt;
	}

	
	

}
