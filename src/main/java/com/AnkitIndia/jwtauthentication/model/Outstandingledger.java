package com.AnkitIndia.jwtauthentication.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Outstandingledger")
public class Outstandingledger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(columnDefinition="varchar(50) default '0'")
	private String branchcode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ledgerid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String voucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String referenceno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String referencedate;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String billamount;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double adjustmentamount;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String duesamount;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String preadjustedamount;
	
	@Column(columnDefinition="char(2) default '0'")
	private char vouchertype;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String entrybranchcode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String uniquevoucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String out_uniquevoucherno;
	
	@Column(columnDefinition="varchar(50) default 'INSERTED'")
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inserted_by;        
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inserted_location;
	
	private LocalDateTime modified_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String modified_by;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String modified_location; 
	
	private LocalDateTime deleted_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String deleted_by;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String deleted_location;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String fin_year;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String username;
	
	public Outstandingledger() {
		// TODO Auto-generated constructor stub
	}

	public Outstandingledger(long id, String branchcode, String ledgerid, String voucherno, String referenceno,
			String referencedate, String billamount, double adjustmentamount, String duesamount,
			String preadjustedamount, char vouchertype, String entrybranchcode, String uniquevoucherno,
			String out_uniquevoucherno, String modified_type, LocalDateTime inserted_on, String inserted_by,
			String inserted_location, LocalDateTime modified_on, String modified_by, String modified_location,
			LocalDateTime deleted_on, String deleted_by, String deleted_location, String fin_year, String username) {
		super();
		this.id = id;
		this.branchcode = branchcode;
		this.ledgerid = ledgerid;
		this.voucherno = voucherno;
		this.referenceno = referenceno;
		this.referencedate = referencedate;
		this.billamount = billamount;
		this.adjustmentamount = adjustmentamount;
		this.duesamount = duesamount;
		this.preadjustedamount = preadjustedamount;
		this.vouchertype = vouchertype;
		this.entrybranchcode = entrybranchcode;
		this.uniquevoucherno = uniquevoucherno;
		this.out_uniquevoucherno = out_uniquevoucherno;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.inserted_location = inserted_location;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.modified_location = modified_location;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.deleted_location = deleted_location;
		this.fin_year = fin_year;
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	public String getLedgerid() {
		return ledgerid;
	}

	public void setLedgerid(String ledgerid) {
		this.ledgerid = ledgerid;
	}

	public String getVoucherno() {
		return voucherno;
	}

	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
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

	public String getBillamount() {
		return billamount;
	}

	public void setBillamount(String billamount) {
		this.billamount = billamount;
	}

	public double getAdjustmentamount() {
		return adjustmentamount;
	}

	public void setAdjustmentamount(double adjustmentamount) {
		this.adjustmentamount = adjustmentamount;
	}

	public String getDuesamount() {
		return duesamount;
	}

	public void setDuesamount(String duesamount) {
		this.duesamount = duesamount;
	}

	public String getPreadjustedamount() {
		return preadjustedamount;
	}

	public void setPreadjustedamount(String preadjustedamount) {
		this.preadjustedamount = preadjustedamount;
	}

	public char getVouchertype() {
		return vouchertype;
	}

	public void setVouchertype(char vouchertype) {
		this.vouchertype = vouchertype;
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

	public String getOut_uniquevoucherno() {
		return out_uniquevoucherno;
	}

	public void setOut_uniquevoucherno(String out_uniquevoucherno) {
		this.out_uniquevoucherno = out_uniquevoucherno;
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

	public String getInserted_location() {
		return inserted_location;
	}

	public void setInserted_location(String inserted_location) {
		this.inserted_location = inserted_location;
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

	public String getModified_location() {
		return modified_location;
	}

	public void setModified_location(String modified_location) {
		this.modified_location = modified_location;
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

	public String getDeleted_location() {
		return deleted_location;
	}

	public void setDeleted_location(String deleted_location) {
		this.deleted_location = deleted_location;
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
	
}