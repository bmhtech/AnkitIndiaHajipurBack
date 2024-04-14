/*package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Stk_Transfer_Invoice_Tax_Info")
public class Stk_Transfer_Invoice_Tax_Info {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String stk_invoice_id;
	
	@Size(max = 50)
	private String stk_invoice_no;
	
	@Size(max = 50)
	private String pan_no;
	
	@Size(max = 50)
	private String gst_no;
	
	@Size(max = 50)
	private String cst_no;
	
	@Size(max = 50)
	private String servicetax_no;
	
	@Size(max = 50)
	private String tin_no;
	
	@Size(max = 50)
	private String company_id;
	
	@Size(max = 20)
	private String fin_year;
	
	@Size(max = 50)
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Size(max = 50)
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	@Size(max = 50)
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	@Size(max = 50)
	private String deleted_by;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "sti_id")
	private Stk_Transfer_Invoice stk_Transfer_Invoice;

	public Stk_Transfer_Invoice_Tax_Info() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stk_Transfer_Invoice_Tax_Info(Long id, @Size(max = 50) String stk_invoice_id,
			@Size(max = 50) String stk_invoice_no, @Size(max = 50) String pan_no, @Size(max = 50) String gst_no,
			@Size(max = 50) String cst_no, @Size(max = 50) String servicetax_no, @Size(max = 50) String tin_no,
			@Size(max = 50) String company_id, @Size(max = 20) String fin_year, @Size(max = 50) String modified_type,
			LocalDateTime inserted_on, @Size(max = 50) String inserted_by, LocalDateTime updated_on,
			@Size(max = 50) String updated_by, LocalDateTime deleted_on, @Size(max = 50) String deleted_by,
			Stk_Transfer_Invoice stk_Transfer_Invoice) {
		super();
		this.id = id;
		this.stk_invoice_id = stk_invoice_id;
		this.stk_invoice_no = stk_invoice_no;
		this.pan_no = pan_no;
		this.gst_no = gst_no;
		this.cst_no = cst_no;
		this.servicetax_no = servicetax_no;
		this.tin_no = tin_no;
		this.company_id = company_id;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.stk_Transfer_Invoice = stk_Transfer_Invoice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStk_invoice_id() {
		return stk_invoice_id;
	}

	public void setStk_invoice_id(String stk_invoice_id) {
		this.stk_invoice_id = stk_invoice_id;
	}

	public String getStk_invoice_no() {
		return stk_invoice_no;
	}

	public void setStk_invoice_no(String stk_invoice_no) {
		this.stk_invoice_no = stk_invoice_no;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getGst_no() {
		return gst_no;
	}

	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}

	public String getCst_no() {
		return cst_no;
	}

	public void setCst_no(String cst_no) {
		this.cst_no = cst_no;
	}

	public String getServicetax_no() {
		return servicetax_no;
	}

	public void setServicetax_no(String servicetax_no) {
		this.servicetax_no = servicetax_no;
	}

	public String getTin_no() {
		return tin_no;
	}

	public void setTin_no(String tin_no) {
		this.tin_no = tin_no;
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

	public Stk_Transfer_Invoice getStk_Transfer_Invoice() {
		return stk_Transfer_Invoice;
	}

	public void setStk_Transfer_Invoice(Stk_Transfer_Invoice stk_Transfer_Invoice) {
		this.stk_Transfer_Invoice = stk_Transfer_Invoice;
	}

	@Override
	public String toString() {
		return "Stk_Transfer_Invoice_Tax_Info [id=" + id + ", stk_invoice_id=" + stk_invoice_id + ", stk_invoice_no="
				+ stk_invoice_no + ", pan_no=" + pan_no + ", gst_no=" + gst_no + ", cst_no=" + cst_no
				+ ", servicetax_no=" + servicetax_no + ", tin_no=" + tin_no + ", company_id=" + company_id
				+ ", fin_year=" + fin_year + ", modified_type=" + modified_type + ", inserted_on=" + inserted_on
				+ ", inserted_by=" + inserted_by + ", updated_on=" + updated_on + ", updated_by=" + updated_by
				+ ", deleted_on=" + deleted_on + ", deleted_by=" + deleted_by + ", stk_Transfer_Invoice="
				+ stk_Transfer_Invoice + "]";
	}

	

}
*/