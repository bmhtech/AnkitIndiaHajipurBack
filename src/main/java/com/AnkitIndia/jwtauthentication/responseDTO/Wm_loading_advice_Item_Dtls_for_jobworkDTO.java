package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Wm_loading_advice_Item_Dtls_for_jobworkDTO {
	
private String advice_id;
	
	private String company_id;
	
	private String advice_no;
	
	private int sl_no;
	
	private String job_item;
	
	private String job_item_name;
	
	private String job_packing;
	
	private String job_packing_name;
	
	private String job_hsn;
	
	private double pack_qty;
	
	private String pack_uom;
	
	private String price_based_on;
	
	private double item_qty;
	
	private String item_uom;
	
	private String tolerance;
	
	private double job_tolerance_qty;
	
  	private String order_id;

	public String getAdvice_id() {
		return advice_id;
	}

	public void setAdvice_id(String advice_id) {
		this.advice_id = advice_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getAdvice_no() {
		return advice_no;
	}

	public void setAdvice_no(String advice_no) {
		this.advice_no = advice_no;
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getJob_item() {
		return job_item;
	}

	public void setJob_item(String job_item) {
		this.job_item = job_item;
	}

	public String getJob_item_name() {
		return job_item_name;
	}

	public void setJob_item_name(String job_item_name) {
		this.job_item_name = job_item_name;
	}

	public String getJob_packing() {
		return job_packing;
	}

	public void setJob_packing(String job_packing) {
		this.job_packing = job_packing;
	}

	public String getJob_packing_name() {
		return job_packing_name;
	}

	public void setJob_packing_name(String job_packing_name) {
		this.job_packing_name = job_packing_name;
	}

	public String getJob_hsn() {
		return job_hsn;
	}

	public void setJob_hsn(String job_hsn) {
		this.job_hsn = job_hsn;
	}

	public double getPack_qty() {
		return pack_qty;
	}

	public void setPack_qty(double pack_qty) {
		this.pack_qty = pack_qty;
	}

	public String getPack_uom() {
		return pack_uom;
	}

	public void setPack_uom(String pack_uom) {
		this.pack_uom = pack_uom;
	}

	public String getPrice_based_on() {
		return price_based_on;
	}

	public void setPrice_based_on(String price_based_on) {
		this.price_based_on = price_based_on;
	}

	public double getItem_qty() {
		return item_qty;
	}

	public void setItem_qty(double item_qty) {
		this.item_qty = item_qty;
	}

	public String getItem_uom() {
		return item_uom;
	}

	public void setItem_uom(String item_uom) {
		this.item_uom = item_uom;
	}

	public String getTolerance() {
		return tolerance;
	}

	public void setTolerance(String tolerance) {
		this.tolerance = tolerance;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public double getJob_tolerance_qty() {
		return job_tolerance_qty;
	}

	public void setJob_tolerance_qty(double job_tolerance_qty) {
		this.job_tolerance_qty = job_tolerance_qty;
	}
  	
}
