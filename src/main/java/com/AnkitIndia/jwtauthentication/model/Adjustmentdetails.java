package com.AnkitIndia.jwtauthentication.model;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Adjustmentdetails")
public class Adjustmentdetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String voucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ledgerid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String entrydate;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String adjustedvoucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String referenceno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String referencedate;
	
	@Column(columnDefinition="Double(10,2) default 0.00")
	private double adjustedamount;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String entrybranchcode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String uniquevoucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String adjid_uniquevoucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inserted_by;
	
	private LocalDateTime modified_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String modified_by;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String fin_year; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String username;

	public Adjustmentdetails() {
		super();
	}

	public Adjustmentdetails(long id, String voucherno, String business_unit, String ledgerid, String entrydate,
			String adjustedvoucherno, String referenceno, String referencedate, double adjustedamount,
			String entrybranchcode, String uniquevoucherno, String adjid_uniquevoucherno, String modified_type,
			LocalDateTime inserted_on, String inserted_by, LocalDateTime modified_on, String modified_by,
			String fin_year, String username) {
		super();
		this.id = id;
		this.voucherno = voucherno;
		this.business_unit = business_unit;
		this.ledgerid = ledgerid;
		this.entrydate = entrydate;
		this.adjustedvoucherno = adjustedvoucherno;
		this.referenceno = referenceno;
		this.referencedate = referencedate;
		this.adjustedamount = adjustedamount;
		this.entrybranchcode = entrybranchcode;
		this.uniquevoucherno = uniquevoucherno;
		this.adjid_uniquevoucherno = adjid_uniquevoucherno;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
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

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getLedgerid() {
		return ledgerid;
	}

	public void setLedgerid(String ledgerid) {
		this.ledgerid = ledgerid;
	}

	public String getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	public String getAdjustedvoucherno() {
		return adjustedvoucherno;
	}

	public void setAdjustedvoucherno(String adjustedvoucherno) {
		this.adjustedvoucherno = adjustedvoucherno;
	}

	public String getReferenceno() {
		return referenceno;
	}

	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}

	public String getReferencedate() {
		return referencedate;
	}

	public void setReferencedate(String referencedate) {
		this.referencedate = referencedate;
	}

	public double getAdjustedamount() {
		return adjustedamount;
	}

	public void setAdjustedamount(double adjustedamount) {
		this.adjustedamount = adjustedamount;
	}

	public String getEntrybranchcode() {
		return entrybranchcode;
	}

	public void setEntrybranchcode(String entrybranchcode) {
		this.entrybranchcode = entrybranchcode;
	}

	public String getUniquevoucherno() {
		return uniquevoucherno;
	}

	public void setUniquevoucherno(String uniquevoucherno) {
		this.uniquevoucherno = uniquevoucherno;
	}

	public String getAdjid_uniquevoucherno() {
		return adjid_uniquevoucherno;
	}

	public void setAdjid_uniquevoucherno(String adjid_uniquevoucherno) {
		this.adjid_uniquevoucherno = adjid_uniquevoucherno;
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

	public LocalDateTime getModified_on() {
		return modified_on;
	}

	public void setModified_on(LocalDateTime modified_on) {
		this.modified_on = modified_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
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
		return "Adjustmentdetails [id=" + id + ", voucherno=" + voucherno + ", business_unit=" + business_unit
				+ ", ledgerid=" + ledgerid + ", entrydate=" + entrydate + ", adjustedvoucherno=" + adjustedvoucherno
				+ ", referenceno=" + referenceno + ", referencedate=" + referencedate + ", adjustedamount="
				+ adjustedamount + ", entrybranchcode=" + entrybranchcode + ", uniquevoucherno=" + uniquevoucherno
				+ ", adjid_uniquevoucherno=" + adjid_uniquevoucherno + ", modified_type=" + modified_type
				+ ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by + ", modified_on=" + modified_on
				+ ", modified_by=" + modified_by + ", fin_year=" + fin_year + ", username=" + username + "]";
	}
	
}
