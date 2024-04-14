package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;


public class Pur_BillDTO {
	
	private Long id;
	
	private String company_id;
	
	private String pur_bill_id;
	
	private String pur_bill_no;
	
	private String bill_date;
	
	private String business_unit;
	
	private String supplier_name;
	
	private String supplier;
	
	private boolean item_type;
	
	private String purchase_type;
	
	private String purchase_typename;
	
	private String purchase_subtype;
	
	private String created_by;
	
	private String truck_no;
	
	private String vehicleno;
	
	private String remarks;

	private double item_total;
	
	private String item_total_gl_ac;
	
	private double discount;
    
	private String discount_gl_ac;
	
	private double qc_deduction;
	
	private String qc_deduction_gl_ac;
	
	private double net_amt;
	
	private String net_amt_gl_ac;
	
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
	
	private String fin_year;

	private String  modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;
	
	private double upfrontbrokerage;
	
	private String upfrontbrokerage_gl_ac;
	
	private double claim1;
	
	private String claim1_gl_ac;
	
	private double claim2;
	
	private String claim2_gl_ac;
	
	private String add1_remarks;
	
	private int export;	
	
	private String referance_type;
	
	private String response;
	
	private String state;
	
	private String supp_ref_doc;

	
	private String supp_ref_docno;
	
	
	private String supp_ref_doc_date;
	
	private String billstatus;
	
	private String return_apv_status;
	
	private String purreturnid;
	
	private double store_taxamt;
	
	private double store_frieghtcharges;
	
	private double allstorecharges;
	
	private String store_charges;
	
	private String store_charges_name;
	
	private String store_frieghtcharges_gl_ac;
	
	
	
	
	
	public double getStore_taxamt() {
		return store_taxamt;
	}

	public void setStore_taxamt(double store_taxamt) {
		this.store_taxamt = store_taxamt;
	}

	public double getStore_frieghtcharges() {
		return store_frieghtcharges;
	}

	public void setStore_frieghtcharges(double store_frieghtcharges) {
		this.store_frieghtcharges = store_frieghtcharges;
	}

	public double getAllstorecharges() {
		return allstorecharges;
	}

	public void setAllstorecharges(double allstorecharges) {
		this.allstorecharges = allstorecharges;
	}

	public String getStore_charges() {
		return store_charges;
	}

	public void setStore_charges(String store_charges) {
		this.store_charges = store_charges;
	}

	public String getStore_charges_name() {
		return store_charges_name;
	}

	public void setStore_charges_name(String store_charges_name) {
		this.store_charges_name = store_charges_name;
	}

	public String getStore_frieghtcharges_gl_ac() {
		return store_frieghtcharges_gl_ac;
	}

	public void setStore_frieghtcharges_gl_ac(String store_frieghtcharges_gl_ac) {
		this.store_frieghtcharges_gl_ac = store_frieghtcharges_gl_ac;
	}

	public String getPurreturnid() {
		return purreturnid;
	}

	public void setPurreturnid(String purreturnid) {
		this.purreturnid = purreturnid;
	}

	public String getBillstatus() {
		return billstatus;
	}

	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}

	public String getReturn_apv_status() {
		return return_apv_status;
	}

	public void setReturn_apv_status(String return_apv_status) {
		this.return_apv_status = return_apv_status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSupp_ref_doc() {
		return supp_ref_doc;
	}

	public void setSupp_ref_doc(String supp_ref_doc) {
		this.supp_ref_doc = supp_ref_doc;
	}

	public String getSupp_ref_docno() {
		return supp_ref_docno;
	}

	public void setSupp_ref_docno(String supp_ref_docno) {
		this.supp_ref_docno = supp_ref_docno;
	}

	public String getSupp_ref_doc_date() {
		return supp_ref_doc_date;
	}

	public void setSupp_ref_doc_date(String supp_ref_doc_date) {
		this.supp_ref_doc_date = supp_ref_doc_date;
	}

	public String getReferance_type() {
		return referance_type;
	}

	public void setReferance_type(String referance_type) {
		this.referance_type = referance_type;
	}

	public int getExport() {
		return export;
	}

	public void setExport(int export) {
		this.export = export;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getAdd1_remarks() {
		return add1_remarks;
	}

	public void setAdd1_remarks(String add1_remarks) {
		this.add1_remarks = add1_remarks;
	}

	public String getAdd2_remarks() {
		return add2_remarks;
	}

	public void setAdd2_remarks(String add2_remarks) {
		this.add2_remarks = add2_remarks;
	}

	private String add2_remarks;
	

	//Pur Bill Item Type Status
	private String grn_for;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getUpfrontbrokerage() {
		return upfrontbrokerage;
	}

	public void setUpfrontbrokerage(double upfrontbrokerage) {
		this.upfrontbrokerage = upfrontbrokerage;
	}

	public String getUpfrontbrokerage_gl_ac() {
		return upfrontbrokerage_gl_ac;
	}

	public void setUpfrontbrokerage_gl_ac(String upfrontbrokerage_gl_ac) {
		this.upfrontbrokerage_gl_ac = upfrontbrokerage_gl_ac;
	}

	public double getClaim1() {
		return claim1;
	}

	public void setClaim1(double claim1) {
		this.claim1 = claim1;
	}

	public String getClaim1_gl_ac() {
		return claim1_gl_ac;
	}

	public void setClaim1_gl_ac(String claim1_gl_ac) {
		this.claim1_gl_ac = claim1_gl_ac;
	}

	public double getClaim2() {
		return claim2;
	}

	public void setClaim2(double claim2) {
		this.claim2 = claim2;
	}

	public String getClaim2_gl_ac() {
		return claim2_gl_ac;
	}

	public void setClaim2_gl_ac(String claim2_gl_ac) {
		this.claim2_gl_ac = claim2_gl_ac;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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
	
	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public boolean isItem_type() {
		return item_type;
	}

	public void setItem_type(boolean item_type) {
		this.item_type = item_type;
	}

	public String getPurchase_type() {
		return purchase_type;
	}

	public void setPurchase_type(String purchase_type) {
		this.purchase_type = purchase_type;
	}

	public String getPurchase_typename() {
		return purchase_typename;
	}

	public void setPurchase_typename(String purchase_typename) {
		this.purchase_typename = purchase_typename;
	}

	public String getPurchase_subtype() {
		return purchase_subtype;
	}

	public void setPurchase_subtype(String purchase_subtype) {
		this.purchase_subtype = purchase_subtype;
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

	public double getQc_deduction() {
		return qc_deduction;
	}

	public void setQc_deduction(double qc_deduction) {
		this.qc_deduction = qc_deduction;
	}

	public String getQc_deduction_gl_ac() {
		return qc_deduction_gl_ac;
	}

	public void setQc_deduction_gl_ac(String qc_deduction_gl_ac) {
		this.qc_deduction_gl_ac = qc_deduction_gl_ac;
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

	public String getGrn_for() {
		return grn_for;
	}

	public void setGrn_for(String grn_for) {
		this.grn_for = grn_for;
	}
	

}
