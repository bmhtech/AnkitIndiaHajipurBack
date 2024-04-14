package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

public class Invoice_accountdetailsDTO {

	private long slno;
	
	private String voucherno;
	
	private String vouchernumber;
	
	private String business_unit;
	
	private String business_unitname;
	
	private String particulars;
	
	private String particularsname;
	
	private String entrydate;
	
	private double amount;
	
	private String source_status;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private String fin_year; 
	
	private String username;

	public long getSlno() {
		return slno;
	}

	public void setSlno(long slno) {
		this.slno = slno;
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
		return "Invoice_accountdetailsDTO [slno=" + slno + ", voucherno=" + voucherno + ", vouchernumber="
				+ vouchernumber + ", business_unit=" + business_unit + ", business_unitname=" + business_unitname
				+ ", particulars=" + particulars + ", particularsname=" + particularsname + ", entrydate=" + entrydate
				+ ", amount=" + amount + ", source_status=" + source_status + ", modified_type=" + modified_type
				+ ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by + ", fin_year=" + fin_year
				+ ", username=" + username + "]";
	}

	
}
