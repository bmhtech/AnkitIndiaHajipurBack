package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Pur_Bill_Item_DetailsDTO {
	
	private Long slno;
	
	private String pur_bill_id;
	
	private String pur_bill_no;
	     
	private String adv_item_code;
	
	private String adv_item_name;
	
	private String item_group;
    
	private String adv_packing_item;
	
	private String adv_packing_item_name;
	
	private String hsn_code;
	
   // private long passed_packing_qty;
    
    private double passed_packing_qty;
    
	private String passed_packing_uom;
    
    private double passed_item_qty;
    
	private double passed_mat_weight;
    
	private String passed_item_uom;	
    
	private double  unit_rate;
    
	private String price_based_on;
    
	private double amount;
    
	private double discount;
    
	private String discount_basedon;	
    
	private double discount_amount;
    
	private double net_amount;
    
	private double qc_deduction;
    
	private double net_amt_after_qc;
    
	private String tax_code;
	
	private double tax_rate;
 
	private double cgstamt;
    
	private double sgstamt;
    
	private double igstamt;
	
	private double tax_amt;
    
	private double gross_amt;
    
	private String gl_acc;
	
	private String warehouse;
    
	private String warehouse_name;
    
	private String stack;
    
	private String stack_name;
   
	private boolean checkbox;
	
	private String fin_year;

	private String  modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;
	
	private String company_id;

	private String  tax_name;
	
	private String classified_item_name;
	
	
	public String getClassified_item_name() {
		return classified_item_name;
	}

	public void setClassified_item_name(String classified_item_name) {
		this.classified_item_name = classified_item_name;
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

	public String getTax_name() {
		return tax_name;
	}

	public void setTax_name(String tax_name) {
		this.tax_name = tax_name;
	}

	public String getItem_group() {
		return item_group;
	}

	public void setItem_group(String item_group) {
		this.item_group = item_group;
	}

	public String getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}

	public Long getSlno() {
		return slno;
	}

	public void setSlno(Long slno) {
		this.slno = slno;
	}

	public String getPur_bill_id() {
		return pur_bill_id;
	}

	public void setPur_bill_id(String pur_bill_id) {
		this.pur_bill_id = pur_bill_id;
	}

	public String getPur_bill_no() {
		return pur_bill_no;
	}

	public void setPur_bill_no(String pur_bill_no) {
		this.pur_bill_no = pur_bill_no;
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

	public String getAdv_packing_item() {
		return adv_packing_item;
	}

	public void setAdv_packing_item(String adv_packing_item) {
		this.adv_packing_item = adv_packing_item;
	}

	public String getAdv_packing_item_name() {
		return adv_packing_item_name;
	}

	public void setAdv_packing_item_name(String adv_packing_item_name) {
		this.adv_packing_item_name = adv_packing_item_name;
	}

	

	public double getPassed_packing_qty() {
		return passed_packing_qty;
	}

	public void setPassed_packing_qty(double passed_packing_qty) {
		this.passed_packing_qty = passed_packing_qty;
	}

	public String getPassed_packing_uom() {
		return passed_packing_uom;
	}

	public void setPassed_packing_uom(String passed_packing_uom) {
		this.passed_packing_uom = passed_packing_uom;
	}

	public double getPassed_item_qty() {
		return passed_item_qty;
	}

	public void setPassed_item_qty(double passed_item_qty) {
		this.passed_item_qty = passed_item_qty;
	}

	public double getPassed_mat_weight() {
		return passed_mat_weight;
	}

	public void setPassed_mat_weight(double passed_mat_weight) {
		this.passed_mat_weight = passed_mat_weight;
	}

	public String getPassed_item_uom() {
		return passed_item_uom;
	}

	public void setPassed_item_uom(String passed_item_uom) {
		this.passed_item_uom = passed_item_uom;
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

	public String getDiscount_basedon() {
		return discount_basedon;
	}

	public void setDiscount_basedon(String discount_basedon) {
		this.discount_basedon = discount_basedon;
	}

	public double getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}

	public double getNet_amount() {
		return net_amount;
	}

	public void setNet_amount(double net_amount) {
		this.net_amount = net_amount;
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

	public String getGl_acc() {
		return gl_acc;
	}

	public void setGl_acc(String gl_acc) {
		this.gl_acc = gl_acc;
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

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public String getStack_name() {
		return stack_name;
	}

	public void setStack_name(String stack_name) {
		this.stack_name = stack_name;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
	

}
