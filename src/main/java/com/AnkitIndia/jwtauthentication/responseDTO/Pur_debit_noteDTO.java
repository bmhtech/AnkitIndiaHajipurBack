package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Pur_debit_noteDTO {
	
	private Long id;
	
	private String debitnoteid;
	
	private String debitnoteno;
	
	private String debitnotetype;
	
	private String debitnotedate;
	
	private String businessunit;
	 
	private String businessunitname;
	
	private String supplier_id;
	
	private String supplier_name;
	
	private String ser_item_type;
	
	private String ser_item_subtype;
	
	private String ser_item_subtypename;
	
	private String created_by;
	
	private String truck_no;
	
	private String vehicleno;

	private double item_total;
	
	private String item_total_gl_ac;
	
	private double discount;
	
	private String discount_gl_ac;
	
	private String qc_deduction;
	
	private double net_amt;
	
	private String net_amt_gl_ac ;
    
	private String qc_deduction_gl_ac;
	
	private double amt_after_deduction;
	
	private String amt_after_deduction_gl_ac;
	
	private double add_tax;
    
	private String add_tax_gl_ac;
	
	private double post_tax_amt;
	
	private String post_tax_amt_gl_ac;
	
	private double other_charges;
	
	private String other_charges_gl_ac;
    
	private double payable_amt;
	
	private String payable_amt_gl_ac;
    
	private double add1;
	
	private String add1_gl_ac;
	
	private double add2;
    
	private String add2_gl_ac;
	
	private double roundoff_amt;

	private String roundoff_gl_ac;
    
	private double payable_party;
    
	private String payable_party_gl_ac;
	
	private double already_paid;
	
	private String already_paid_gl_ac;
    
	private double net_payable_party;
    
	private String net_payable_party_gl_ac;
	
	private String referance_id;
	
	private String remarks;
	
	private String company_id;
    
	private String fin_year;
    
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

	public String getDebitnoteid() {
		return debitnoteid;
	}

	public void setDebitnoteid(String debitnoteid) {
		this.debitnoteid = debitnoteid;
	}

	public String getDebitnoteno() {
		return debitnoteno;
	}

	public void setDebitnoteno(String debitnoteno) {
		this.debitnoteno = debitnoteno;
	}

	public String getDebitnotetype() {
		return debitnotetype;
	}

	public void setDebitnotetype(String debitnotetype) {
		this.debitnotetype = debitnotetype;
	}

	public String getDebitnotedate() {
		return debitnotedate;
	}

	public void setDebitnotedate(String debitnotedate) {
		this.debitnotedate = debitnotedate;
	}

	public String getBusinessunit() {
		return businessunit;
	}

	public void setBusinessunit(String businessunit) {
		this.businessunit = businessunit;
	}

	public String getBusinessunitname() {
		return businessunitname;
	}

	public void setBusinessunitname(String businessunitname) {
		this.businessunitname = businessunitname;
	}

	public String getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSer_item_type() {
		return ser_item_type;
	}

	public void setSer_item_type(String ser_item_type) {
		this.ser_item_type = ser_item_type;
	}

	public String getSer_item_subtype() {
		return ser_item_subtype;
	}

	public void setSer_item_subtype(String ser_item_subtype) {
		this.ser_item_subtype = ser_item_subtype;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getTruck_no() {
		return truck_no;
	}

	public void setTruck_no(String truck_no) {
		this.truck_no = truck_no;
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

	public String getQc_deduction() {
		return qc_deduction;
	}

	public void setQc_deduction(String qc_deduction) {
		this.qc_deduction = qc_deduction;
	}

	public double getNet_amt() {
		return net_amt;
	}

	public void setNet_amt(double net_amt) {
		this.net_amt = net_amt;
	}

	public String getNet_amt_gl_ac() {
		return net_amt_gl_ac;
	}

	public void setNet_amt_gl_ac(String net_amt_gl_ac) {
		this.net_amt_gl_ac = net_amt_gl_ac;
	}

	public String getQc_deduction_gl_ac() {
		return qc_deduction_gl_ac;
	}

	public void setQc_deduction_gl_ac(String qc_deduction_gl_ac) {
		this.qc_deduction_gl_ac = qc_deduction_gl_ac;
	}

	public double getAmt_after_deduction() {
		return amt_after_deduction;
	}

	public void setAmt_after_deduction(double amt_after_deduction) {
		this.amt_after_deduction = amt_after_deduction;
	}

	public String getAmt_after_deduction_gl_ac() {
		return amt_after_deduction_gl_ac;
	}

	public void setAmt_after_deduction_gl_ac(String amt_after_deduction_gl_ac) {
		this.amt_after_deduction_gl_ac = amt_after_deduction_gl_ac;
	}

	public double getAdd_tax() {
		return add_tax;
	}

	public void setAdd_tax(double add_tax) {
		this.add_tax = add_tax;
	}

	public String getAdd_tax_gl_ac() {
		return add_tax_gl_ac;
	}

	public void setAdd_tax_gl_ac(String add_tax_gl_ac) {
		this.add_tax_gl_ac = add_tax_gl_ac;
	}

	public double getPost_tax_amt() {
		return post_tax_amt;
	}

	public void setPost_tax_amt(double post_tax_amt) {
		this.post_tax_amt = post_tax_amt;
	}

	public String getPost_tax_amt_gl_ac() {
		return post_tax_amt_gl_ac;
	}

	public void setPost_tax_amt_gl_ac(String post_tax_amt_gl_ac) {
		this.post_tax_amt_gl_ac = post_tax_amt_gl_ac;
	}

	public double getOther_charges() {
		return other_charges;
	}

	public void setOther_charges(double other_charges) {
		this.other_charges = other_charges;
	}

	public String getOther_charges_gl_ac() {
		return other_charges_gl_ac;
	}

	public void setOther_charges_gl_ac(String other_charges_gl_ac) {
		this.other_charges_gl_ac = other_charges_gl_ac;
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

	public double getAdd1() {
		return add1;
	}

	public void setAdd1(double add1) {
		this.add1 = add1;
	}

	public String getAdd1_gl_ac() {
		return add1_gl_ac;
	}

	public void setAdd1_gl_ac(String add1_gl_ac) {
		this.add1_gl_ac = add1_gl_ac;
	}

	public double getAdd2() {
		return add2;
	}

	public void setAdd2(double add2) {
		this.add2 = add2;
	}

	public String getAdd2_gl_ac() {
		return add2_gl_ac;
	}

	public void setAdd2_gl_ac(String add2_gl_ac) {
		this.add2_gl_ac = add2_gl_ac;
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

	public double getPayable_party() {
		return payable_party;
	}

	public void setPayable_party(double payable_party) {
		this.payable_party = payable_party;
	}

	public String getPayable_party_gl_ac() {
		return payable_party_gl_ac;
	}

	public void setPayable_party_gl_ac(String payable_party_gl_ac) {
		this.payable_party_gl_ac = payable_party_gl_ac;
	}

	public double getAlready_paid() {
		return already_paid;
	}

	public void setAlready_paid(double already_paid) {
		this.already_paid = already_paid;
	}

	public String getAlready_paid_gl_ac() {
		return already_paid_gl_ac;
	}

	public void setAlready_paid_gl_ac(String already_paid_gl_ac) {
		this.already_paid_gl_ac = already_paid_gl_ac;
	}

	public double getNet_payable_party() {
		return net_payable_party;
	}

	public void setNet_payable_party(double net_payable_party) {
		this.net_payable_party = net_payable_party;
	}

	public String getNet_payable_party_gl_ac() {
		return net_payable_party_gl_ac;
	}

	public void setNet_payable_party_gl_ac(String net_payable_party_gl_ac) {
		this.net_payable_party_gl_ac = net_payable_party_gl_ac;
	}

	public String getReferance_id() {
		return referance_id;
	}

	public void setReferance_id(String referance_id) {
		this.referance_id = referance_id;
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

	public String getSer_item_subtypename() {
		return ser_item_subtypename;
	}

	public void setSer_item_subtypename(String ser_item_subtypename) {
		this.ser_item_subtypename = ser_item_subtypename;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
