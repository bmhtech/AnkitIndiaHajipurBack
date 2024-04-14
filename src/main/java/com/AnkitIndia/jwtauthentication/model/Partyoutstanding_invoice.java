package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Partyoutstanding_invoice")
public class Partyoutstanding_invoice{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String party;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String partyname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String business_unitname;
	
	@Column(columnDefinition = "Double(20,2) default 0.0")
	private double billamount;
	
	@Column(columnDefinition = "Double(20,2) default 0.0")
	private double duesamount;
	
	@Column(columnDefinition = "Double(20,2) default 0.0")
	private double adjustmentamount;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String control_account;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String bill_date;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String acc_voucherno;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String company_id;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String fin_year;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String username;
	
    @Column(columnDefinition="varchar(50) default 0")
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String inserted_by;

	public Partyoutstanding_invoice() {
		super();
	}
	
	public Partyoutstanding_invoice(Long id, String invoice_id, String invoice_no, String party, String partyname,
			String business_unit, String business_unitname, double billamount, double duesamount,
			double adjustmentamount, String control_account, String bill_date, String acc_voucherno, String company_id,
			String fin_year, String username, String modified_type, LocalDateTime inserted_on, String inserted_by) {
		super();
		this.id = id;
		this.invoice_id = invoice_id;
		this.invoice_no = invoice_no;
		this.party = party;
		this.partyname = partyname;
		this.business_unit = business_unit;
		this.business_unitname = business_unitname;
		this.billamount = billamount;
		this.duesamount = duesamount;
		this.adjustmentamount = adjustmentamount;
		this.control_account = control_account;
		this.bill_date = bill_date;
		this.acc_voucherno = acc_voucherno;
		this.company_id = company_id;
		this.fin_year = fin_year;
		this.username = username;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
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

	public double getBillamount() {
		return billamount;
	}

	public void setBillamount(double billamount) {
		this.billamount = billamount;
	}

	public double getDuesamount() {
		return duesamount;
	}

	public void setDuesamount(double duesamount) {
		this.duesamount = duesamount;
	}

	public double getAdjustmentamount() {
		return adjustmentamount;
	}

	public void setAdjustmentamount(double adjustmentamount) {
		this.adjustmentamount = adjustmentamount;
	}

	public String getControl_account() {
		return control_account;
	}

	public void setControl_account(String control_account) {
		this.control_account = control_account;
	}

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	
	public String getAcc_voucherno() {
		return acc_voucherno;
	}

	public void setAcc_voucherno(String acc_voucherno) {
		this.acc_voucherno = acc_voucherno;
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

	@Override
	public String toString() {
		return "Partyoutstanding_invoice [id=" + id + ", invoice_id=" + invoice_id + ", invoice_no=" + invoice_no
				+ ", party=" + party + ", partyname=" + partyname + ", business_unit=" + business_unit
				+ ", business_unitname=" + business_unitname + ", billamount=" + billamount + ", duesamount="
				+ duesamount + ", adjustmentamount=" + adjustmentamount + ", control_account=" + control_account
				+ ", bill_date=" + bill_date + ", company_id=" + company_id + ", fin_year=" + fin_year + ", username="
				+ username + ", modified_type=" + modified_type + ", inserted_on=" + inserted_on + ", inserted_by="
				+ inserted_by + "]";
	}
	

}
