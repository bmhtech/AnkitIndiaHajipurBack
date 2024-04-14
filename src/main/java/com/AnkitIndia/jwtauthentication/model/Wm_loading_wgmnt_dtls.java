package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Wm_loading_wgmnt_dtls")
public class Wm_loading_wgmnt_dtls {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String wgment_id;
	
	@Size(max = 50)
	private String company_id;
	
	private int sl_no;
	
	@Size(max = 100)
	private String customer;
	
	@Size(max = 100)
	private String advice;
	
	private Date wgment_date;
	
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
    @JoinColumn(name = "ww_id")
    private Wm_loading_wgmnt wm_loading_wgmnt;

	public Wm_loading_wgmnt_dtls() {
		super();
	}

	public Wm_loading_wgmnt_dtls(Long id, @Size(max = 50) String wgment_id, @Size(max = 50) String company_id,
			int sl_no, @Size(max = 100) String customer, @Size(max = 100) String advice, Date wgment_date,
			@Size(max = 20) String fin_year, @Size(max = 50) String modified_type, LocalDateTime inserted_on,
			@Size(max = 50) String inserted_by, LocalDateTime updated_on, @Size(max = 50) String updated_by,
			LocalDateTime deleted_on, @Size(max = 50) String deleted_by, Wm_loading_wgmnt wm_loading_wgmnt) {
		super();
		this.id = id;
		this.wgment_id = wgment_id;
		this.company_id = company_id;
		this.sl_no = sl_no;
		this.customer = customer;
		this.advice = advice;
		this.wgment_date = wgment_date;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.wm_loading_wgmnt = wm_loading_wgmnt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWgment_id() {
		return wgment_id;
	}

	public void setWgment_id(String wgment_id) {
		this.wgment_id = wgment_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Date getWgment_date() {
		return wgment_date;
	}

	public void setWgment_date(Date wgment_date) {
		this.wgment_date = wgment_date;
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

	public Wm_loading_wgmnt getWm_loading_wgmnt() {
		return wm_loading_wgmnt;
	}

	public void setWm_loading_wgmnt(Wm_loading_wgmnt wm_loading_wgmnt) {
		this.wm_loading_wgmnt = wm_loading_wgmnt;
	}

	@Override
	public String toString() {
		return "Wm_loading_wgmnt_dtls [id=" + id + ", wgment_id=" + wgment_id + ", company_id=" + company_id
				+ ", sl_no=" + sl_no + ", customer=" + customer + ", advice=" + advice + ", wgment_date=" + wgment_date
				+ ", fin_year=" + fin_year + ", modified_type=" + modified_type + ", inserted_on=" + inserted_on
				+ ", inserted_by=" + inserted_by + ", updated_on=" + updated_on + ", updated_by=" + updated_by
				+ ", deleted_on=" + deleted_on + ", deleted_by=" + deleted_by + ", wm_loading_wgmnt=" + wm_loading_wgmnt
				+ "]";
	}


	
}
