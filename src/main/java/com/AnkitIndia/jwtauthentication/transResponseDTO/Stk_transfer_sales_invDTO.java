package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

public class Stk_transfer_sales_invDTO {
	private Long id;
	
	private String stk_sales_inv_id;
	
	private String stk_sales_inv_no;
	
	private String business_unit;
	
	private String business_unitname;
	
	private String stk_sales_inv_date;
	
	private String stk_sales_inv_order_no;
	
	private String stk_sales_inv_order_date;
	
	private String challan;
	
	private String e_invoice_no;
	
	private String refchallanno;
	
	private String refchallandate;
	
	private double item_total;
    
	private String item_total_gl_ac;
    
	private double discount;
    
	private String discount_gl_ac;
    
	private double net_total;
    
	private String net_total_gl_ac;
    
	private double tax_total;
    
	private String tax_total_gl_ac;
    
	private double total_bill_amt;
    
	private String total_bill_amt_gl_ac;
    
	private double applicable_amt;
    
	private String applicable_gl_ac;
    
	private double adj1_amt;
    
	private String adj1_gl_ac;
    
	private double adj2_amt;
    
	private String adj2_gl_ac;
    
	private double roundoff_amt;
    
	private String roundoff_gl_ac;
    
	private double final_bill_amt;
    
	private String final_bill_amt_gl_ac;
    
	private double payable_amt;
    
	private String payable_amt_gl_ac;
    
	private double tcsamt;
    
	private String tcsglac;
    
	private double grand_total;
    
	private String remarks;
	
	private String reference_id;
	
	private String username;
	
	private String company_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;

	private String stk_sales_mutlipledates;
	
	private String multiplechallandate;
	
	
	public String getMultiplechallandate() {
		return multiplechallandate;
	}

	public void setMultiplechallandate(String multiplechallandate) {
		this.multiplechallandate = multiplechallandate;
	}

	public String getStk_sales_mutlipledates() {
		return stk_sales_mutlipledates;
	}

	public void setStk_sales_mutlipledates(String stk_sales_mutlipledates) {
		this.stk_sales_mutlipledates = stk_sales_mutlipledates;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStk_sales_inv_id() {
		return stk_sales_inv_id;
	}

	public void setStk_sales_inv_id(String stk_sales_inv_id) {
		this.stk_sales_inv_id = stk_sales_inv_id;
	}

	public String getStk_sales_inv_no() {
		return stk_sales_inv_no;
	}

	public void setStk_sales_inv_no(String stk_sales_inv_no) {
		this.stk_sales_inv_no = stk_sales_inv_no;
	}

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getBusiness_unitname() {
		return business_unitname;
	}

	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}

	public String getStk_sales_inv_date() {
		return stk_sales_inv_date;
	}

	public void setStk_sales_inv_date(String stk_sales_inv_date) {
		this.stk_sales_inv_date = stk_sales_inv_date;
	}

	public String getStk_sales_inv_order_no() {
		return stk_sales_inv_order_no;
	}

	public void setStk_sales_inv_order_no(String stk_sales_inv_order_no) {
		this.stk_sales_inv_order_no = stk_sales_inv_order_no;
	}

	public String getStk_sales_inv_order_date() {
		return stk_sales_inv_order_date;
	}

	public void setStk_sales_inv_order_date(String stk_sales_inv_order_date) {
		this.stk_sales_inv_order_date = stk_sales_inv_order_date;
	}

	public String getChallan() {
		return challan;
	}

	public void setChallan(String challan) {
		this.challan = challan;
	}

	public String getE_invoice_no() {
		return e_invoice_no;
	}

	public void setE_invoice_no(String e_invoice_no) {
		this.e_invoice_no = e_invoice_no;
	}

	public String getRefchallanno() {
		return refchallanno;
	}

	public void setRefchallanno(String refchallanno) {
		this.refchallanno = refchallanno;
	}

	public String getRefchallandate() {
		return refchallandate;
	}

	public void setRefchallandate(String refchallandate) {
		this.refchallandate = refchallandate;
	}

	public double getItem_total() {
		return item_total;
	}

	public void setItem_total(double item_total) {
		this.item_total = item_total;
	}

	public String getItem_total_gl_ac() {
		return item_total_gl_ac;
	}

	public void setItem_total_gl_ac(String item_total_gl_ac) {
		this.item_total_gl_ac = item_total_gl_ac;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDiscount_gl_ac() {
		return discount_gl_ac;
	}

	public void setDiscount_gl_ac(String discount_gl_ac) {
		this.discount_gl_ac = discount_gl_ac;
	}

	public double getNet_total() {
		return net_total;
	}

	public void setNet_total(double net_total) {
		this.net_total = net_total;
	}

	public String getNet_total_gl_ac() {
		return net_total_gl_ac;
	}

	public void setNet_total_gl_ac(String net_total_gl_ac) {
		this.net_total_gl_ac = net_total_gl_ac;
	}

	public double getTax_total() {
		return tax_total;
	}

	public void setTax_total(double tax_total) {
		this.tax_total = tax_total;
	}

	public String getTax_total_gl_ac() {
		return tax_total_gl_ac;
	}

	public void setTax_total_gl_ac(String tax_total_gl_ac) {
		this.tax_total_gl_ac = tax_total_gl_ac;
	}

	public double getTotal_bill_amt() {
		return total_bill_amt;
	}

	public void setTotal_bill_amt(double total_bill_amt) {
		this.total_bill_amt = total_bill_amt;
	}

	public String getTotal_bill_amt_gl_ac() {
		return total_bill_amt_gl_ac;
	}

	public void setTotal_bill_amt_gl_ac(String total_bill_amt_gl_ac) {
		this.total_bill_amt_gl_ac = total_bill_amt_gl_ac;
	}

	public double getApplicable_amt() {
		return applicable_amt;
	}

	public void setApplicable_amt(double applicable_amt) {
		this.applicable_amt = applicable_amt;
	}

	public String getApplicable_gl_ac() {
		return applicable_gl_ac;
	}

	public void setApplicable_gl_ac(String applicable_gl_ac) {
		this.applicable_gl_ac = applicable_gl_ac;
	}

	public double getAdj1_amt() {
		return adj1_amt;
	}

	public void setAdj1_amt(double adj1_amt) {
		this.adj1_amt = adj1_amt;
	}

	public String getAdj1_gl_ac() {
		return adj1_gl_ac;
	}

	public void setAdj1_gl_ac(String adj1_gl_ac) {
		this.adj1_gl_ac = adj1_gl_ac;
	}

	public double getAdj2_amt() {
		return adj2_amt;
	}

	public void setAdj2_amt(double adj2_amt) {
		this.adj2_amt = adj2_amt;
	}

	public String getAdj2_gl_ac() {
		return adj2_gl_ac;
	}

	public void setAdj2_gl_ac(String adj2_gl_ac) {
		this.adj2_gl_ac = adj2_gl_ac;
	}

	public double getRoundoff_amt() {
		return roundoff_amt;
	}

	public void setRoundoff_amt(double roundoff_amt) {
		this.roundoff_amt = roundoff_amt;
	}

	public String getRoundoff_gl_ac() {
		return roundoff_gl_ac;
	}

	public void setRoundoff_gl_ac(String roundoff_gl_ac) {
		this.roundoff_gl_ac = roundoff_gl_ac;
	}

	public double getFinal_bill_amt() {
		return final_bill_amt;
	}

	public void setFinal_bill_amt(double final_bill_amt) {
		this.final_bill_amt = final_bill_amt;
	}

	public String getFinal_bill_amt_gl_ac() {
		return final_bill_amt_gl_ac;
	}

	public void setFinal_bill_amt_gl_ac(String final_bill_amt_gl_ac) {
		this.final_bill_amt_gl_ac = final_bill_amt_gl_ac;
	}

	public double getPayable_amt() {
		return payable_amt;
	}

	public void setPayable_amt(double payable_amt) {
		this.payable_amt = payable_amt;
	}

	public String getPayable_amt_gl_ac() {
		return payable_amt_gl_ac;
	}

	public void setPayable_amt_gl_ac(String payable_amt_gl_ac) {
		this.payable_amt_gl_ac = payable_amt_gl_ac;
	}

	public double getTcsamt() {
		return tcsamt;
	}

	public void setTcsamt(double tcsamt) {
		this.tcsamt = tcsamt;
	}

	public String getTcsglac() {
		return tcsglac;
	}

	public void setTcsglac(String tcsglac) {
		this.tcsglac = tcsglac;
	}

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReference_id() {
		return reference_id;
	}

	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
