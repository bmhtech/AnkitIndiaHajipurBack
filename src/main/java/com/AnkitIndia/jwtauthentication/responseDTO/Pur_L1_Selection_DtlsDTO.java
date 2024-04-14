package com.AnkitIndia.jwtauthentication.responseDTO;

import java.util.Date;


public class Pur_L1_Selection_DtlsDTO {

	private String lsel_id;
	
	private int sl_no;
	
	private String pq_doc_no;
	
	private String item_code;
	
	private String item_name;
	
	private String vendor_code;
	
	private String vendor_name;
	
	private double price;
	
	private Date req_date;
	
	private Date qout_date;
	
	private String req_qty;
	
	private String qout_qty;
	
	private double amount;
	
	private String status;
	
	private String reason;
	
	private String remarks;

	public String getLsel_id() {
		return lsel_id;
	}

	public void setLsel_id(String lsel_id) {
		this.lsel_id = lsel_id;
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getPq_doc_no() {
		return pq_doc_no;
	}

	public void setPq_doc_no(String pq_doc_no) {
		this.pq_doc_no = pq_doc_no;
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

	public String getVendor_code() {
		return vendor_code;
	}

	public void setVendor_code(String vendor_code) {
		this.vendor_code = vendor_code;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getReq_date() {
		return req_date;
	}

	public void setReq_date(Date req_date) {
		this.req_date = req_date;
	}

	public Date getQout_date() {
		return qout_date;
	}

	public void setQout_date(Date qout_date) {
		this.qout_date = qout_date;
	}

	public String getReq_qty() {
		return req_qty;
	}

	public void setReq_qty(String req_qty) {
		this.req_qty = req_qty;
	}

	public String getQout_qty() {
		return qout_qty;
	}

	public void setQout_qty(String qout_qty) {
		this.qout_qty = qout_qty;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
