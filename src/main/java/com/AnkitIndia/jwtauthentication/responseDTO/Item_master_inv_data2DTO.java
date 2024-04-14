package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Item_master_inv_data2DTO {

	private Long id;
	
	private String item_id;
	
	private String item_code;
	
	private String company_id;
	
	private boolean neg_inv_allow;
	
	private boolean manage_inv_wh;
	
	private int sales_qty;
	
	private String sales_uom;
	
	private int pur_qty;
	
	private String pur_uom;
	
	private int eoq_qty;
	
	private String eoq_uom;
	
	private int reorder_qty;
	
	private String reorder_uom;
	
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

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public boolean isNeg_inv_allow() {
		return neg_inv_allow;
	}

	public void setNeg_inv_allow(boolean neg_inv_allow) {
		this.neg_inv_allow = neg_inv_allow;
	}

	public boolean isManage_inv_wh() {
		return manage_inv_wh;
	}

	public void setManage_inv_wh(boolean manage_inv_wh) {
		this.manage_inv_wh = manage_inv_wh;
	}

	public int getSales_qty() {
		return sales_qty;
	}

	public void setSales_qty(int sales_qty) {
		this.sales_qty = sales_qty;
	}

	public String getSales_uom() {
		return sales_uom;
	}

	public void setSales_uom(String sales_uom) {
		this.sales_uom = sales_uom;
	}

	public int getPur_qty() {
		return pur_qty;
	}

	public void setPur_qty(int pur_qty) {
		this.pur_qty = pur_qty;
	}

	public String getPur_uom() {
		return pur_uom;
	}

	public void setPur_uom(String pur_uom) {
		this.pur_uom = pur_uom;
	}

	public int getEoq_qty() {
		return eoq_qty;
	}

	public void setEoq_qty(int eoq_qty) {
		this.eoq_qty = eoq_qty;
	}

	public String getEoq_uom() {
		return eoq_uom;
	}

	public void setEoq_uom(String eoq_uom) {
		this.eoq_uom = eoq_uom;
	}

	public int getReorder_qty() {
		return reorder_qty;
	}

	public void setReorder_qty(int reorder_qty) {
		this.reorder_qty = reorder_qty;
	}

	public String getReorder_uom() {
		return reorder_uom;
	}

	public void setReorder_uom(String reorder_uom) {
		this.reorder_uom = reorder_uom;
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
