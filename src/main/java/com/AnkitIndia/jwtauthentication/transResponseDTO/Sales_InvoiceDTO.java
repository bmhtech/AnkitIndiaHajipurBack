package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;

public class Sales_InvoiceDTO {
	private Long id;
	
	private String invoice_id;
	
	private String invoice_no;
	
	private String invoice_type;
	
	private String business_unit;
	
	private Date invoice_date;
	
	private String party;
	
	private String partyname;
	
	private String challan;
	
	private String e_invoice_no;
	
	private double grand_total;

	private boolean brokage_app;

	private String remarks;
	
	private String salesorderno;
	
	private String salesorderdate;
	
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
    
	private double tcsamt;
    
	private String tcsglac;
    
	private double payable_amt;
    
	private String payable_amt_gl_ac;
    
	private String reference_id;
	
	private String company_id;
    
	private String fin_year;
    
	private String username;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private String payable_amt_inword;
  
    private String salereturn_id;

	private String sales_returnstatus;
	
	private String return_approval_status;
	
	private String app_chgs_id;
	
	private String  cust_refdocno;
	
	private String cust_ref_doc_date;
	
	private String waybill;
	
	private String invoice_type_name;
	
	private boolean jobwork;
	
	private boolean cencel_einvoice;
	
	private boolean create_einvoice;
	
	private boolean create_ewaybill;
	
	private boolean cencel_ewaybill;

	private boolean create_ewaybill_wo_invoice;
	
	private String policyno;
	
	private String gststatus;
	
	private String asn_no;
	
	private String group_type;
	
	public String getGroup_type() {
		return group_type;
	}

	public void setGroup_type(String group_type) {
		this.group_type = group_type;
	}

	public String getAsn_no() {
		return asn_no;
	}

	public void setAsn_no(String asn_no) {
		this.asn_no = asn_no;
	}

	public String getGststatus() {
		return gststatus;
	}

	public void setGststatus(String gststatus) {
		this.gststatus = gststatus;
	}

	public String getPolicyno() {
		return policyno;
	}

	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}

	public boolean isCreate_ewaybill_wo_invoice() {
		return create_ewaybill_wo_invoice;
	}

	public void setCreate_ewaybill_wo_invoice(boolean create_ewaybill_wo_invoice) {
		this.create_ewaybill_wo_invoice = create_ewaybill_wo_invoice;
	}

	public boolean isCreate_ewaybill() {
		return create_ewaybill;
	}

	public void setCreate_ewaybill(boolean create_ewaybill) {
		this.create_ewaybill = create_ewaybill;
	}

	public boolean isCencel_ewaybill() {
		return cencel_ewaybill;
	}

	public void setCencel_ewaybill(boolean cencel_ewaybill) {
		this.cencel_ewaybill = cencel_ewaybill;
	}

	public boolean isCreate_einvoice() {
		return create_einvoice;
	}

	public void setCreate_einvoice(boolean create_einvoice) {
		this.create_einvoice = create_einvoice;
	}

	public boolean isCencel_einvoice() {
		return cencel_einvoice;
	}

	public void setCencel_einvoice(boolean cencel_einvoice) {
		this.cencel_einvoice = cencel_einvoice;
	}

	public boolean isJobwork() {
		return jobwork;
	}

	public void setJobwork(boolean jobwork) {
		this.jobwork = jobwork;
	}

	public String getInvoice_type_name() {
		return invoice_type_name;
	}

	public void setInvoice_type_name(String invoice_type_name) {
		this.invoice_type_name = invoice_type_name;
	}

	public String getWaybill() {
		return waybill;
	}

	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}

	public String getCust_refdocno() {
		return cust_refdocno;
	}

	public void setCust_refdocno(String cust_refdocno) {
		this.cust_refdocno = cust_refdocno;
	}

	public String getCust_ref_doc_date() {
		return cust_ref_doc_date;
	}

	public void setCust_ref_doc_date(String cust_ref_doc_date) {
		this.cust_ref_doc_date = cust_ref_doc_date;
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

	public String getUndo_by() {
		return undo_by;
	}

	public void setUndo_by(String undo_by) {
		this.undo_by = undo_by;
	}

	public LocalDateTime getUndo_on() {
		return undo_on;
	}

	public void setUndo_on(LocalDateTime undo_on) {
		this.undo_on = undo_on;
	}

	private int export;	
	
	
	private String response;
	
	
	private String undo_by;
	
	private LocalDateTime undo_on;

	public String getApp_chgs_id() {
		return app_chgs_id;
	}

	public void setApp_chgs_id(String app_chgs_id) {
		this.app_chgs_id = app_chgs_id;
	}

	public String getReturn_approval_status() {
		return return_approval_status;
	}

	public void setReturn_approval_status(String return_approval_status) {
		this.return_approval_status = return_approval_status;
	}

	public String getSalereturn_id() {
		return salereturn_id;
	}

	public void setSalereturn_id(String salereturn_id) {
		this.salereturn_id = salereturn_id;
	}

	public String getSales_returnstatus() {
		return sales_returnstatus;
	}

	public void setSales_returnstatus(String sales_returnstatus) {
		this.sales_returnstatus = sales_returnstatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public Date getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getPartyname() {
		return partyname;
	}

	public void setPartyname(String partyname) {
		this.partyname = partyname;
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

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}

	public boolean isBrokage_app() {
		return brokage_app;
	}

	public void setBrokage_app(boolean brokage_app) {
		this.brokage_app = brokage_app;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSalesorderno() {
		return salesorderno;
	}

	public void setSalesorderno(String salesorderno) {
		this.salesorderno = salesorderno;
	}

	public String getSalesorderdate() {
		return salesorderdate;
	}

	public void setSalesorderdate(String salesorderdate) {
		this.salesorderdate = salesorderdate;
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

	public String getReference_id() {
		return reference_id;
	}

	public void setReference_id(String referance_id) {
		this.reference_id = referance_id;
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

	public String getPayable_amt_inword() {
		return payable_amt_inword;
	}

	public void setPayable_amt_inword(String payable_amt_inword) {
		this.payable_amt_inword = payable_amt_inword;
	}
	
	
}
