package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Invoice_accountdetails")
public class Invoice_accountdetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String voucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vouchernumber;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String particulars;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String particularsname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String entrydate;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double amount;
	
	@Column(columnDefinition="varchar(10) default 'No'")
	private String source_status;
	
	@Column(columnDefinition="varchar(50) default 'No'")
	private String transaction_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String acc_voucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inserted_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String fin_year; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String username;
	
	public Invoice_accountdetails() {
		// TODO Auto-generated constructor stub
	}
	
	public Invoice_accountdetails(long id, String voucherno, String vouchernumber, String business_unit,
			String business_unitname, String particulars, String particularsname, String entrydate, double amount,
			String source_status, String transaction_type, String acc_voucherno, String modified_type,
			LocalDateTime inserted_on, String inserted_by, String fin_year, String username) {
		super();
		this.id = id;
		this.voucherno = voucherno;
		this.vouchernumber = vouchernumber;
		this.business_unit = business_unit;
		this.business_unitname = business_unitname;
		this.particulars = particulars;
		this.particularsname = particularsname;
		this.entrydate = entrydate;
		this.amount = amount;
		this.source_status = source_status;
		this.transaction_type = transaction_type;
		this.acc_voucherno = acc_voucherno;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.fin_year = fin_year;
		this.username = username;
	}

	public Invoice_accountdetails(String voucherno, String vouchernumber, String business_unit, String business_unitname, String particulars,
			String particularsname, String entrydate, double amount, String source_status, String transaction_type, String acc_voucherno, String modified_type,
			LocalDateTime inserted_on, String inserted_by, String fin_year, String username) {
		super();
		this.voucherno = voucherno;
		this.vouchernumber = vouchernumber;
		this.business_unit = business_unit;
		this.business_unitname = business_unitname;
		this.particulars = particulars;
		this.particularsname = particularsname;
		this.entrydate = entrydate;
		this.amount = amount;
		this.source_status = source_status;
		this.transaction_type = transaction_type;
		this.acc_voucherno = acc_voucherno;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.fin_year = fin_year;
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVoucherno() {
		return voucherno;
	}

	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}
	
	public String getVouchernumber() {
		return vouchernumber;
	}

	public void setVouchernumber(String vouchernumber) {
		this.vouchernumber = vouchernumber;
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

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getParticularsname() {
		return particularsname;
	}

	public void setParticularsname(String particularsname) {
		this.particularsname = particularsname;
	}

	public String getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSource_status() {
		return source_status;
	}

	public void setSource_status(String source_status) {
		this.source_status = source_status;
	}
	
	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	
	public String getAcc_voucherno() {
		return acc_voucherno;
	}

	public void setAcc_voucherno(String acc_voucherno) {
		this.acc_voucherno = acc_voucherno;
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

	@Override
	public String toString() {
		return "Invoice_accountdetails [id=" + id + ", voucherno=" + voucherno + ", business_unit=" + business_unit
				+ ", business_unitname=" + business_unitname + ", particulars=" + particulars + ", particularsname="
				+ particularsname + ", entrydate=" + entrydate + ", amount=" + amount + ", source_status="
				+ source_status + ", modified_type=" + modified_type + ", inserted_on=" + inserted_on + ", inserted_by="
				+ inserted_by + ", fin_year=" + fin_year + ", username=" + username + "]";
	}
	
}
