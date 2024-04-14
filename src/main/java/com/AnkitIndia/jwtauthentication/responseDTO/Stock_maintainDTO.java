package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Stock_maintainDTO {

	private Long id;
	
	private String business_unit;
	
	private String warehouse;
	
	private String stack_rack_no;
	
	private String item_id;
		
	private String packing_id;
	
	private double standard_rate;
	
	private double mrp;
	
	private String item_type;
	
	private String item_group;
	
	private String item_category;
	
	private double adv_packing_qty;
	
	private double adv_item_qty;
	
	private double rcv_packing_qty;
	
	private double rcv_item_qty;

	private double pssd_packing_qty;
	
	private double pssd_item_qty;
	
	private double item_opening_stk;
	
	private double item_purchase_stk;
	
	private double item_pur_return;
	
	private double item_sales_stk;
	
	private double item_sales_return;
	
	private double item_stk_trans;
	
	private double item_trans_received;
	
	private double item_curr_stk;
	
	private double item_close_stk;
	
	private double pack_opening_stk;
	
	private double pack_purchase_stk;
	
	private double pack_pur_return;
	
	private double pack_sales_stk;
	
	private double pack_sales_return;
	
	private double pack_stk_trans;
	
	private double pack_trans_received;
	
	private double pack_curr_stk;
	
	private double pack_close_stk;
	
	private String fin_year;
	
	private String company_id;
	
	private String username;
	
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

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getStack_rack_no() {
		return stack_rack_no;
	}

	public void setStack_rack_no(String stack_rack_no) {
		this.stack_rack_no = stack_rack_no;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getPacking_id() {
		return packing_id;
	}

	public void setPacking_id(String packing_id) {
		this.packing_id = packing_id;
	}

	public double getStandard_rate() {
		return standard_rate;
	}

	public void setStandard_rate(double standard_rate) {
		this.standard_rate = standard_rate;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getItem_group() {
		return item_group;
	}

	public void setItem_group(String item_group) {
		this.item_group = item_group;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}

	public double getAdv_packing_qty() {
		return adv_packing_qty;
	}

	public void setAdv_packing_qty(double adv_packing_qty) {
		this.adv_packing_qty = adv_packing_qty;
	}

	public double getAdv_item_qty() {
		return adv_item_qty;
	}

	public void setAdv_item_qty(double adv_item_qty) {
		this.adv_item_qty = adv_item_qty;
	}

	public double getRcv_packing_qty() {
		return rcv_packing_qty;
	}

	public void setRcv_packing_qty(double rcv_packing_qty) {
		this.rcv_packing_qty = rcv_packing_qty;
	}

	public double getRcv_item_qty() {
		return rcv_item_qty;
	}

	public void setRcv_item_qty(double rcv_item_qty) {
		this.rcv_item_qty = rcv_item_qty;
	}

	public double getPssd_packing_qty() {
		return pssd_packing_qty;
	}

	public void setPssd_packing_qty(double pssd_packing_qty) {
		this.pssd_packing_qty = pssd_packing_qty;
	}

	public double getPssd_item_qty() {
		return pssd_item_qty;
	}

	public void setPssd_item_qty(double pssd_item_qty) {
		this.pssd_item_qty = pssd_item_qty;
	}

	public double getItem_opening_stk() {
		return item_opening_stk;
	}

	public void setItem_opening_stk(double item_opening_stk) {
		this.item_opening_stk = item_opening_stk;
	}

	public double getItem_purchase_stk() {
		return item_purchase_stk;
	}

	public void setItem_purchase_stk(double item_purchase_stk) {
		this.item_purchase_stk = item_purchase_stk;
	}

	public double getItem_pur_return() {
		return item_pur_return;
	}

	public void setItem_pur_return(double item_pur_return) {
		this.item_pur_return = item_pur_return;
	}

	public double getItem_sales_stk() {
		return item_sales_stk;
	}

	public void setItem_sales_stk(double item_sales_stk) {
		this.item_sales_stk = item_sales_stk;
	}

	public double getItem_sales_return() {
		return item_sales_return;
	}

	public void setItem_sales_return(double item_sales_return) {
		this.item_sales_return = item_sales_return;
	}

	public double getItem_stk_trans() {
		return item_stk_trans;
	}

	public void setItem_stk_trans(double item_stk_trans) {
		this.item_stk_trans = item_stk_trans;
	}

	public double getItem_trans_received() {
		return item_trans_received;
	}

	public void setItem_trans_received(double item_trans_received) {
		this.item_trans_received = item_trans_received;
	}

	public double getItem_curr_stk() {
		return item_curr_stk;
	}

	public void setItem_curr_stk(double item_curr_stk) {
		this.item_curr_stk = item_curr_stk;
	}

	public double getItem_close_stk() {
		return item_close_stk;
	}

	public void setItem_close_stk(double item_close_stk) {
		this.item_close_stk = item_close_stk;
	}

	public double getPack_opening_stk() {
		return pack_opening_stk;
	}

	public void setPack_opening_stk(double pack_opening_stk) {
		this.pack_opening_stk = pack_opening_stk;
	}

	public double getPack_purchase_stk() {
		return pack_purchase_stk;
	}

	public void setPack_purchase_stk(double pack_purchase_stk) {
		this.pack_purchase_stk = pack_purchase_stk;
	}

	public double getPack_pur_return() {
		return pack_pur_return;
	}

	public void setPack_pur_return(double pack_pur_return) {
		this.pack_pur_return = pack_pur_return;
	}

	public double getPack_sales_stk() {
		return pack_sales_stk;
	}

	public void setPack_sales_stk(double pack_sales_stk) {
		this.pack_sales_stk = pack_sales_stk;
	}

	public double getPack_sales_return() {
		return pack_sales_return;
	}

	public void setPack_sales_return(double pack_sales_return) {
		this.pack_sales_return = pack_sales_return;
	}

	public double getPack_stk_trans() {
		return pack_stk_trans;
	}

	public void setPack_stk_trans(double pack_stk_trans) {
		this.pack_stk_trans = pack_stk_trans;
	}

	public double getPack_trans_received() {
		return pack_trans_received;
	}

	public void setPack_trans_received(double pack_trans_received) {
		this.pack_trans_received = pack_trans_received;
	}

	public double getPack_curr_stk() {
		return pack_curr_stk;
	}

	public void setPack_curr_stk(double pack_curr_stk) {
		this.pack_curr_stk = pack_curr_stk;
	}

	public double getPack_close_stk() {
		return pack_close_stk;
	}

	public void setPack_close_stk(double pack_close_stk) {
		this.pack_close_stk = pack_close_stk;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
