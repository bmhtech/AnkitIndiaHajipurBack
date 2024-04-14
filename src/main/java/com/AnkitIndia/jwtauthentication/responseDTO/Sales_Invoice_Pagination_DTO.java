package com.AnkitIndia.jwtauthentication.responseDTO;

public class Sales_Invoice_Pagination_DTO {

	private Long id;
	
	private String invoice_id;
	
	private String invoice_no;
	
	private String invoice_date;
	
	private String invoice_type;
	
	private String party;
	
	private String partyname;
	
	private String return_approval_status;
	
	private String salesorderno;
	
	private String salesorderdate;
	
	private String refchallanno;
	
	private String refchallandate;
	
	private double payable_amt;
	
	private String modified_type;
	
	private int export;

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

	public String getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}

	public String getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
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

	public String getReturn_approval_status() {
		return return_approval_status;
	}

	public void setReturn_approval_status(String return_approval_status) {
		this.return_approval_status = return_approval_status;
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

	public double getPayable_amt() {
		return payable_amt;
	}

	public void setPayable_amt(double payable_amt) {
		this.payable_amt = payable_amt;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public int getExport() {
		return export;
	}

	public void setExport(int export) {
		this.export = export;
	}

	public Sales_Invoice_Pagination_DTO(Long id, String invoice_id, String invoice_no, String invoice_date,
			String invoice_type, String party, String partyname, String return_approval_status, String salesorderno,
			String salesorderdate, String refchallanno, String refchallandate, double payable_amt, String modified_type,
			int export) {
		super();
		this.id = id;
		this.invoice_id = invoice_id;
		this.invoice_no = invoice_no;
		this.invoice_date = invoice_date;
		this.invoice_type = invoice_type;
		this.party = party;
		this.partyname = partyname;
		this.return_approval_status = return_approval_status;
		this.salesorderno = salesorderno;
		this.salesorderdate = salesorderdate;
		this.refchallanno = refchallanno;
		this.refchallandate = refchallandate;
		this.payable_amt = payable_amt;
		this.modified_type = modified_type;
		this.export = export;
	}	
	

}
