package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Sales_Order_Termination")
public class Sales_Order_Termination {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String order_id;
	
	@Size(max = 50)
	private String order_no;
	
	@Size(max = 50)
	private String company_id;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean tsales_order;
	
	@Size(max = 50)
	private String order_by;
	
	@Size(max = 50)
	private String reason;
	
	@Size(max = 50)
	private String remarks;
	
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
    @JoinColumn(name = "so_id")
    private Sales_Order sales_order;

	public Sales_Order_Termination() {
		super();
	}

	public Sales_Order_Termination(Long id, @Size(max = 50) String order_id, @Size(max = 50) String order_no,
			@Size(max = 50) String company_id, boolean tsales_order, @Size(max = 50) String order_by,
			@Size(max = 50) String reason, @Size(max = 50) String remarks, @Size(max = 20) String fin_year,
			@Size(max = 50) String modified_type, LocalDateTime inserted_on, @Size(max = 50) String inserted_by,
			LocalDateTime updated_on, @Size(max = 50) String updated_by, LocalDateTime deleted_on,
			@Size(max = 50) String deleted_by, Sales_Order sales_order) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.order_no = order_no;
		this.company_id = company_id;
		this.tsales_order = tsales_order;
		this.order_by = order_by;
		this.reason = reason;
		this.remarks = remarks;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.sales_order = sales_order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public boolean isTsales_order() {
		return tsales_order;
	}

	public void setTsales_order(boolean tsales_order) {
		this.tsales_order = tsales_order;
	}

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Sales_Order getSales_order() {
		return sales_order;
	}

	public void setSales_order(Sales_Order sales_order) {
		this.sales_order = sales_order;
	}

	@Override
	public String toString() {
		return "Sales_Order_Termination [id=" + id + ", order_id=" + order_id + ", order_no=" + order_no
				+ ", company_id=" + company_id + ", tsales_order=" + tsales_order + ", order_by=" + order_by
				+ ", reason=" + reason + ", remarks=" + remarks + ", fin_year=" + fin_year + ", modified_type="
				+ modified_type + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by + ", updated_on="
				+ updated_on + ", updated_by=" + updated_by + ", deleted_on=" + deleted_on + ", deleted_by="
				+ deleted_by + ", sales_order=" + sales_order + "]";
	}
	
	

}
