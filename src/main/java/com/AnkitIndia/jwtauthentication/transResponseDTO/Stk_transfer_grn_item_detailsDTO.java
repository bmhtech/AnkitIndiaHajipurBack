package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

public class Stk_transfer_grn_item_detailsDTO {

	private Long id;
	
	private String stk_grn_id;
	
	private String stk_grn_no;
	
	private int slno;
	
	private String adv_item_code;
	
	private String adv_item_name; 
	
	private String adv_packing_name;
	
	private String adv_packing; 
	
	private double adv_pack_qty; 
	
	private String adv_pack_uom; 
	
	private double adv_item_qty;
	
	private double adv_mat_wt; 
	
	private String adv_item_uom; 
	
	private double rcv_pack_qty; 
	
	private String rcv_pack_uom; 
	
	private double rcv_item_qty; 
	
	private double rcv_mat_wt; 
	
	private String rcv_item_uom; 
	
	private double pssd_pack_qty; 
	
	private String pssd_pack_uom; 
	
	private double pssd_item_qty; 
	
	private double pssd_mat_wt;
	
	private String pssd_item_uom; 
	
	private double unit_rate;
	
	private String price_based_on;	
	
	private double amount; 	
	
	private double discount;
	
	private String discount_based_on;
	
	private double discount_amt;
	
	private double net_amt;
	
	private double qc_deduction;
	
	private double net_amt_after_qc;
	
	private String tax_code;
	
	private double tax_rate;	
	
	private double tax_amt;
	
	private double gross_amt;
	
	private String qc_norms;
	
	private String warehouse;
	
	private String warehouse_name;	
	
	private String rack;
	
	private String rack_name;
	
	private String stack_uom;
	
	private double stack_qty;
	
	private boolean checkbox;										
	
	private String company_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;

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

	public String getStk_grn_id() {
		return stk_grn_id;
	}

	public void setStk_grn_id(String stk_grn_id) {
		this.stk_grn_id = stk_grn_id;
	}

	public String getStk_grn_no() {
		return stk_grn_no;
	}

	public void setStk_grn_no(String stk_grn_no) {
		this.stk_grn_no = stk_grn_no;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public String getAdv_item_code() {
		return adv_item_code;
	}

	public void setAdv_item_code(String adv_item_code) {
		this.adv_item_code = adv_item_code;
	}

	public String getAdv_item_name() {
		return adv_item_name;
	}

	public void setAdv_item_name(String adv_item_name) {
		this.adv_item_name = adv_item_name;
	}

	public String getAdv_packing_name() {
		return adv_packing_name;
	}

	public void setAdv_packing_name(String adv_packing_name) {
		this.adv_packing_name = adv_packing_name;
	}

	public String getAdv_packing() {
		return adv_packing;
	}

	public void setAdv_packing(String adv_packing) {
		this.adv_packing = adv_packing;
	}

	public double getAdv_pack_qty() {
		return adv_pack_qty;
	}

	public void setAdv_pack_qty(double adv_pack_qty) {
		this.adv_pack_qty = adv_pack_qty;
	}

	public String getAdv_pack_uom() {
		return adv_pack_uom;
	}

	public void setAdv_pack_uom(String adv_pack_uom) {
		this.adv_pack_uom = adv_pack_uom;
	}

	public double getAdv_item_qty() {
		return adv_item_qty;
	}

	public void setAdv_item_qty(double adv_item_qty) {
		this.adv_item_qty = adv_item_qty;
	}

	public double getAdv_mat_wt() {
		return adv_mat_wt;
	}

	public void setAdv_mat_wt(double adv_mat_wt) {
		this.adv_mat_wt = adv_mat_wt;
	}

	public String getAdv_item_uom() {
		return adv_item_uom;
	}

	public void setAdv_item_uom(String adv_item_uom) {
		this.adv_item_uom = adv_item_uom;
	}

	public double getRcv_pack_qty() {
		return rcv_pack_qty;
	}

	public void setRcv_pack_qty(double rcv_pack_qty) {
		this.rcv_pack_qty = rcv_pack_qty;
	}

	public String getRcv_pack_uom() {
		return rcv_pack_uom;
	}

	public void setRcv_pack_uom(String rcv_pack_uom) {
		this.rcv_pack_uom = rcv_pack_uom;
	}

	public double getRcv_item_qty() {
		return rcv_item_qty;
	}

	public void setRcv_item_qty(double rcv_item_qty) {
		this.rcv_item_qty = rcv_item_qty;
	}

	public double getRcv_mat_wt() {
		return rcv_mat_wt;
	}

	public void setRcv_mat_wt(double rcv_mat_wt) {
		this.rcv_mat_wt = rcv_mat_wt;
	}

	public String getRcv_item_uom() {
		return rcv_item_uom;
	}

	public void setRcv_item_uom(String rcv_item_uom) {
		this.rcv_item_uom = rcv_item_uom;
	}

	public double getPssd_pack_qty() {
		return pssd_pack_qty;
	}

	public void setPssd_pack_qty(double pssd_pack_qty) {
		this.pssd_pack_qty = pssd_pack_qty;
	}

	public String getPssd_pack_uom() {
		return pssd_pack_uom;
	}

	public void setPssd_pack_uom(String pssd_pack_uom) {
		this.pssd_pack_uom = pssd_pack_uom;
	}

	public double getPssd_item_qty() {
		return pssd_item_qty;
	}

	public void setPssd_item_qty(double pssd_item_qty) {
		this.pssd_item_qty = pssd_item_qty;
	}

	public double getPssd_mat_wt() {
		return pssd_mat_wt;
	}

	public void setPssd_mat_wt(double pssd_mat_wt) {
		this.pssd_mat_wt = pssd_mat_wt;
	}

	public String getPssd_item_uom() {
		return pssd_item_uom;
	}

	public void setPssd_item_uom(String pssd_item_uom) {
		this.pssd_item_uom = pssd_item_uom;
	}

	public double getUnit_rate() {
		return unit_rate;
	}

	public void setUnit_rate(double unit_rate) {
		this.unit_rate = unit_rate;
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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDiscount_based_on() {
		return discount_based_on;
	}

	public void setDiscount_based_on(String discount_based_on) {
		this.discount_based_on = discount_based_on;
	}

	public double getDiscount_amt() {
		return discount_amt;
	}

	public void setDiscount_amt(double discount_amt) {
		this.discount_amt = discount_amt;
	}

	public double getNet_amt() {
		return net_amt;
	}

	public void setNet_amt(double net_amt) {
		this.net_amt = net_amt;
	}

	public double getQc_deduction() {
		return qc_deduction;
	}

	public void setQc_deduction(double qc_deduction) {
		this.qc_deduction = qc_deduction;
	}

	public double getNet_amt_after_qc() {
		return net_amt_after_qc;
	}

	public void setNet_amt_after_qc(double net_amt_after_qc) {
		this.net_amt_after_qc = net_amt_after_qc;
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

	public double getGross_amt() {
		return gross_amt;
	}

	public void setGross_amt(double gross_amt) {
		this.gross_amt = gross_amt;
	}

	public String getQc_norms() {
		return qc_norms;
	}

	public void setQc_norms(String qc_norms) {
		this.qc_norms = qc_norms;
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

	public String getStack_uom() {
		return stack_uom;
	}

	public void setStack_uom(String stack_uom) {
		this.stack_uom = stack_uom;
	}

	public double getStack_qty() {
		return stack_qty;
	}

	public void setStack_qty(double stack_qty) {
		this.stack_qty = stack_qty;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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
