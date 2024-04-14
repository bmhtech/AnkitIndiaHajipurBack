package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

public class Stock_Indent_OrderDTO {
	
	private Long id;
	
	private String indent_id;
	
	private String indent_no;
	
	private String indent_date;
	
	private String referance_type;
	
	private String business_unit;
	
	private String business_unitname;
	
	private String refer_by;
	
	private String service_item;
	
	private String  department;
	
	private String indent_status;
	
	private String valid_until;
	
	private String remarks;
	
	private String  confirmed_by;
	
	private String  approved;
	
	private String  reason;
	
	private String approved_remarks;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;

	public Stock_Indent_OrderDTO() {
		super();
	}

	public Stock_Indent_OrderDTO(Long id, String indent_id, String indent_no, String indent_date, String referance_type,
			String business_unit, String refer_by, String service_item, String department, String indent_status,
			String valid_until, String remarks, String confirmed_by, String approved, String reason,
			String approved_remarks, String fin_year, String modified_type, LocalDateTime inserted_on,
			String inserted_by, LocalDateTime updated_on, String updated_by) {
		super();
		this.id = id;
		this.indent_id = indent_id;
		this.indent_no = indent_no;
		this.indent_date = indent_date;
		this.referance_type = referance_type;
		this.business_unit = business_unit;
		this.refer_by = refer_by;
		this.service_item = service_item;
		this.department = department;
		this.indent_status = indent_status;
		this.valid_until = valid_until;
		this.remarks = remarks;
		this.confirmed_by = confirmed_by;
		this.approved = approved;
		this.reason = reason;
		this.approved_remarks = approved_remarks;
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

	public String getIndent_id() {
		return indent_id;
	}

	public void setIndent_id(String indent_id) {
		this.indent_id = indent_id;
	}

	public String getIndent_no() {
		return indent_no;
	}

	public void setIndent_no(String indent_no) {
		this.indent_no = indent_no;
	}

	public String getIndent_date() {
		return indent_date;
	}

	public void setIndent_date(String indent_date) {
		this.indent_date = indent_date;
	}

	public String getReferance_type() {
		return referance_type;
	}

	public void setReferance_type(String referance_type) {
		this.referance_type = referance_type;
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

	public String getRefer_by() {
		return refer_by;
	}

	public void setRefer_by(String refer_by) {
		this.refer_by = refer_by;
	}

	public String getService_item() {
		return service_item;
	}

	public void setService_item(String service_item) {
		this.service_item = service_item;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getIndent_status() {
		return indent_status;
	}

	public void setIndent_status(String indent_status) {
		this.indent_status = indent_status;
	}

	public String getValid_until() {
		return valid_until;
	}

	public void setValid_until(String valid_until) {
		this.valid_until = valid_until;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getConfirmed_by() {
		return confirmed_by;
	}

	public void setConfirmed_by(String confirmed_by) {
		this.confirmed_by = confirmed_by;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApproved_remarks() {
		return approved_remarks;
	}

	public void setApproved_remarks(String approved_remarks) {
		this.approved_remarks = approved_remarks;
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
		return "Stock_Indent_OrderDTO [id=" + id + ", indent_id=" + indent_id + ", indent_no=" + indent_no
				+ ", indent_date=" + indent_date + ", referance_type=" + referance_type + ", business_unit="
				+ business_unit + ", refer_by=" + refer_by + ", service_item=" + service_item + ", department="
				+ department + ", indent_status=" + indent_status + ", valid_until=" + valid_until + ", remarks="
				+ remarks + ", confirmed_by=" + confirmed_by + ", approved=" + approved + ", reason=" + reason
				+ ", approved_remarks=" + approved_remarks + ", fin_year=" + fin_year + ", modified_type="
				+ modified_type + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by + ", updated_on="
				+ updated_on + ", updated_by=" + updated_by + "]";
	}
	
	
}
