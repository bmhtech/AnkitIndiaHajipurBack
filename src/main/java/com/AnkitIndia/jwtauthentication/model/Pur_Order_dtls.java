package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Pur_Order_dtls")
public class Pur_Order_dtls {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String company_id;
	
	@Size(max = 50)
	private String pur_order_no;
	
	@Size(max = 100)
	private String cp_name;

	private Long cp_phone;

	private Long cp_mobile;
	
	@Size(max = 30)
	private String cp_fax;
	
	@Size(max = 50)
	private String cp_email;
	
	@Size(max = 100)
	private String cp_designation;
	
	
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
           
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
    private Pur_Order pur_Order;

	public Pur_Order_dtls() {
		super();
	}

	public Pur_Order_dtls(Long id, @Size(max = 50) String company_id, @Size(max = 50) String pur_order_no,
			@Size(max = 100) String cp_name, Long cp_phone, Long cp_mobile, @Size(max = 30) String cp_fax,
			@Size(max = 50) String cp_email, @Size(max = 100) String cp_designation, @Size(max = 20) String fin_year,
			@Size(max = 50) String modified_type, LocalDateTime inserted_on, @Size(max = 50) String inserted_by,
			LocalDateTime updated_on, @Size(max = 50) String updated_by, LocalDateTime deleted_on,
			@Size(max = 50) String deleted_by, Pur_Order pur_Order) {
		super();
		this.id = id;
		this.company_id = company_id;
		this.pur_order_no = pur_order_no;
		this.cp_name = cp_name;
		this.cp_phone = cp_phone;
		this.cp_mobile = cp_mobile;
		this.cp_fax = cp_fax;
		this.cp_email = cp_email;
		this.cp_designation = cp_designation;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.pur_Order = pur_Order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getPur_order_no() {
		return pur_order_no;
	}

	public void setPur_order_no(String pur_order_no) {
		this.pur_order_no = pur_order_no;
	}

	public String getCp_name() {
		return cp_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public Long getCp_phone() {
		return cp_phone;
	}

	public void setCp_phone(Long cp_phone) {
		this.cp_phone = cp_phone;
	}

	public Long getCp_mobile() {
		return cp_mobile;
	}

	public void setCp_mobile(Long cp_mobile) {
		this.cp_mobile = cp_mobile;
	}

	public String getCp_fax() {
		return cp_fax;
	}

	public void setCp_fax(String cp_fax) {
		this.cp_fax = cp_fax;
	}

	public String getCp_email() {
		return cp_email;
	}

	public void setCp_email(String cp_email) {
		this.cp_email = cp_email;
	}

	public String getCp_designation() {
		return cp_designation;
	}

	public void setCp_designation(String cp_designation) {
		this.cp_designation = cp_designation;
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

	public Pur_Order getPur_Order() {
		return pur_Order;
	}

	public void setPur_Order(Pur_Order pur_Order) {
		this.pur_Order = pur_Order;
	}

	@Override
	public String toString() {
		return "Pur_Order_dtls [id=" + id + ", company_id=" + company_id + ", pur_order_no=" + pur_order_no
				+ ", cp_name=" + cp_name + ", cp_phone=" + cp_phone + ", cp_mobile=" + cp_mobile + ", cp_fax=" + cp_fax
				+ ", cp_email=" + cp_email + ", cp_designation=" + cp_designation + ", fin_year=" + fin_year
				+ ", modified_type=" + modified_type + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by
				+ ", updated_on=" + updated_on + ", updated_by=" + updated_by + ", deleted_on=" + deleted_on
				+ ", deleted_by=" + deleted_by + ", pur_Order=" + pur_Order + "]";
	}



}
