package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Item_group_master_pur_retaccDTO {

	private Long id;
	
	private String item_group_code;
	
	private String item_group_id;
	
	private String company_id;
	
	private String item_total_gl_ac;
	
	private String discount_gl_ac;
	
	//private String net_amt_gl_ac;
	
	private String amt_after_deduction_gl_ac;
	
	private String post_tax_amt_gl_ac;
	
	private String payable_amt_gl_ac;
	
	private String already_paid_gl_ac;
	
	private String adjplus;
	
	private String adjminus;
	
	private String payable_party_gl_ac;
	
	private String net_payable_party_gl_ac;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;

	public Item_group_master_pur_retaccDTO() {
		super();
	}

	public Item_group_master_pur_retaccDTO(Long id, String item_group_code, String item_group_id, String company_id,
			String item_total_gl_ac, String discount_gl_ac, String amt_after_deduction_gl_ac,
			String post_tax_amt_gl_ac, String payable_amt_gl_ac, String already_paid_gl_ac, String adjplus,
			String adjminus, String payable_party_gl_ac, String net_payable_party_gl_ac, String fin_year,
			String modified_type, LocalDateTime inserted_on, String inserted_by, LocalDateTime updated_on,
			String updated_by) {
		super();
		this.id = id;
		this.item_group_code = item_group_code;
		this.item_group_id = item_group_id;
		this.company_id = company_id;
		this.item_total_gl_ac = item_total_gl_ac;
		this.discount_gl_ac = discount_gl_ac;
		this.amt_after_deduction_gl_ac = amt_after_deduction_gl_ac;
		this.post_tax_amt_gl_ac = post_tax_amt_gl_ac;
		this.payable_amt_gl_ac = payable_amt_gl_ac;
		this.already_paid_gl_ac = already_paid_gl_ac;
		this.adjplus = adjplus;
		this.adjminus = adjminus;
		this.payable_party_gl_ac = payable_party_gl_ac;
		this.net_payable_party_gl_ac = net_payable_party_gl_ac;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem_group_code() {
		return item_group_code;
	}

	public void setItem_group_code(String item_group_code) {
		this.item_group_code = item_group_code;
	}

	public String getItem_group_id() {
		return item_group_id;
	}

	public void setItem_group_id(String item_group_id) {
		this.item_group_id = item_group_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getItem_total_gl_ac() {
		return item_total_gl_ac;
	}

	public void setItem_total_gl_ac(String item_total_gl_ac) {
		this.item_total_gl_ac = item_total_gl_ac;
	}

	public String getDiscount_gl_ac() {
		return discount_gl_ac;
	}

	public void setDiscount_gl_ac(String discount_gl_ac) {
		this.discount_gl_ac = discount_gl_ac;
	}

	public String getAmt_after_deduction_gl_ac() {
		return amt_after_deduction_gl_ac;
	}

	public void setAmt_after_deduction_gl_ac(String amt_after_deduction_gl_ac) {
		this.amt_after_deduction_gl_ac = amt_after_deduction_gl_ac;
	}

	public String getPost_tax_amt_gl_ac() {
		return post_tax_amt_gl_ac;
	}

	public void setPost_tax_amt_gl_ac(String post_tax_amt_gl_ac) {
		this.post_tax_amt_gl_ac = post_tax_amt_gl_ac;
	}

	public String getPayable_amt_gl_ac() {
		return payable_amt_gl_ac;
	}

	public void setPayable_amt_gl_ac(String payable_amt_gl_ac) {
		this.payable_amt_gl_ac = payable_amt_gl_ac;
	}

	public String getAlready_paid_gl_ac() {
		return already_paid_gl_ac;
	}

	public void setAlready_paid_gl_ac(String already_paid_gl_ac) {
		this.already_paid_gl_ac = already_paid_gl_ac;
	}

	public String getAdjplus() {
		return adjplus;
	}

	public void setAdjplus(String adjplus) {
		this.adjplus = adjplus;
	}

	public String getAdjminus() {
		return adjminus;
	}

	public void setAdjminus(String adjminus) {
		this.adjminus = adjminus;
	}

	public String getPayable_party_gl_ac() {
		return payable_party_gl_ac;
	}

	public void setPayable_party_gl_ac(String payable_party_gl_ac) {
		this.payable_party_gl_ac = payable_party_gl_ac;
	}

	public String getNet_payable_party_gl_ac() {
		return net_payable_party_gl_ac;
	}

	public void setNet_payable_party_gl_ac(String net_payable_party_gl_ac) {
		this.net_payable_party_gl_ac = net_payable_party_gl_ac;
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

	@Override
	public String toString() {
		return "Item_group_master_pur_retaccDTO [id=" + id + ", item_group_code=" + item_group_code + ", item_group_id="
				+ item_group_id + ", company_id=" + company_id + ", item_total_gl_ac=" + item_total_gl_ac
				+ ", discount_gl_ac=" + discount_gl_ac + ", amt_after_deduction_gl_ac=" + amt_after_deduction_gl_ac + ", post_tax_amt_gl_ac="
				+ post_tax_amt_gl_ac + ", payable_amt_gl_ac=" + payable_amt_gl_ac + ", already_paid_gl_ac="
				+ already_paid_gl_ac + ", adjplus=" + adjplus + ", adjminus=" + adjminus + ", payable_party_gl_ac="
				+ payable_party_gl_ac + ", net_payable_party_gl_ac=" + net_payable_party_gl_ac + ", fin_year="
				+ fin_year + ", modified_type=" + modified_type + ", inserted_on=" + inserted_on + ", inserted_by="
				+ inserted_by + ", updated_on=" + updated_on + ", updated_by=" + updated_by + "]";
	}
	
	
}
