package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Size;


public class Stock_Indent_Item_DetailsDTO {
	
	private Long id;
	
	private String indent_id;
	
	private String indent_no;
	
	private String srl_no;
	
	private String  item_code;
	
	private String item_name;
	
	private String req_date;
	
	private String packing_item;
	
	private String packing_item_name;
	
	private String stock_item;
	
	private String stock_item_uom;
	
	private String stock_pack_uom;
	
	private double indent_pack_qty;
	
	private double indent_item_qty;
	
	private String stock_pack;
	
	private String packing_uom;
	
	private String item_uom;
	
	private double indicative_price;
	
	private String price_based_on;
	
	private double amount;
	
	private double net_amount;	
	
	private String tax_code;	
	
	private double tax_rate;
	
	private double tax_amount;	
	
	private double total_amount;
	
	private String qc_norms;
	
	private String priority;
	
	private String delivery_date;
	
	private String purpose;
	
	private String  to_be_used;
	
	private String  remarks;
	
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

	public String getIndent_id() {
		return indent_id;
	}

	public void setIndent_id(String indent_id) {
		this.indent_id = indent_id;
	}

	public String getIndent_no() {
		return indent_no;
	}

	public void setIndent_no(String indent_no) {
		this.indent_no = indent_no;
	}

	public String getSrl_no() {
		return srl_no;
	}

	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
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

	public String getReq_date() {
		return req_date;
	}

	public void setReq_date(String req_date) {
		this.req_date = req_date;
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

	public String getStock_item() {
		return stock_item;
	}

	public void setStock_item(String stock_item) {
		this.stock_item = stock_item;
	}

	public String getStock_item_uom() {
		return stock_item_uom;
	}

	public void setStock_item_uom(String stock_item_uom) {
		this.stock_item_uom = stock_item_uom;
	}

	public String getStock_pack_uom() {
		return stock_pack_uom;
	}

	public void setStock_pack_uom(String stock_pack_uom) {
		this.stock_pack_uom = stock_pack_uom;
	}

	public double getIndent_pack_qty() {
		return indent_pack_qty;
	}

	public void setIndent_pack_qty(double indent_pack_qty) {
		this.indent_pack_qty = indent_pack_qty;
	}

	public double getIndent_item_qty() {
		return indent_item_qty;
	}

	public void setIndent_item_qty(double indent_item_qty) {
		this.indent_item_qty = indent_item_qty;
	}

	public String getStock_pack() {
		return stock_pack;
	}

	public void setStock_pack(String stock_pack) {
		this.stock_pack = stock_pack;
	}

	public String getPacking_uom() {
		return packing_uom;
	}

	public void setPacking_uom(String packing_uom) {
		this.packing_uom = packing_uom;
	}

	public String getItem_uom() {
		return item_uom;
	}

	public void setItem_uom(String item_uom) {
		this.item_uom = item_uom;
	}

	public double getIndicative_price() {
		return indicative_price;
	}

	public void setIndicative_price(double indicative_price) {
		this.indicative_price = indicative_price;
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

	public double getNet_amount() {
		return net_amount;
	}

	public void setNet_amount(double net_amount) {
		this.net_amount = net_amount;
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

	public double getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(double tax_amount) {
		this.tax_amount = tax_amount;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public String getQc_norms() {
		return qc_norms;
	}

	public void setQc_norms(String qc_norms) {
		this.qc_norms = qc_norms;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTo_be_used() {
		return to_be_used;
	}

	public void setTo_be_used(String to_be_used) {
		this.to_be_used = to_be_used;
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

	
	

}
