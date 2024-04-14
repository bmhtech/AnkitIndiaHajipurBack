package com.AnkitIndia.jwtauthentication.model;


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

@Entity
@Table(name="Accountdetails")
public class Account_details {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String voucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String branchcode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String entrydate;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String ledgerid;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double amount;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean check1;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean check2;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String narration; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String gstcalculate;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double parsentage;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String entrybranchcode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String uniquevoucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String inv_voucherid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String inv_voucherno;
	
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
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="racc_id")
	private Receipt_accounttransaction rAccounttransaction;

	public Account_details() {
		super();
	}
	
	public Account_details(long id, String voucherno, String branchcode, String entrydate, String ledgerid,
			double amount, boolean check1, boolean check2, String narration, String gstcalculate, double parsentage,
			String entrybranchcode, String uniquevoucherno, String inv_voucherid, String inv_voucherno,
			String modified_type, LocalDateTime inserted_on, String inserted_by, LocalDateTime modified_on,
			String modified_by, String fin_year, String username, Receipt_accounttransaction rAccounttransaction) {
		super();
		this.id = id;
		this.voucherno = voucherno;
		this.branchcode = branchcode;
		this.entrydate = entrydate;
		this.ledgerid = ledgerid;
		this.amount = amount;
		this.check1 = check1;
		this.check2 = check2;
		this.narration = narration;
		this.gstcalculate = gstcalculate;
		this.parsentage = parsentage;
		this.entrybranchcode = entrybranchcode;
		this.uniquevoucherno = uniquevoucherno;
		this.inv_voucherid = inv_voucherid;
		this.inv_voucherno = inv_voucherno;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.fin_year = fin_year;
		this.username = username;
		this.rAccounttransaction = rAccounttransaction;
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

	public String getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	public String getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	public String getLedgerid() {
		return ledgerid;
	}

	public void setLedgerid(String ledgerid) {
		this.ledgerid = ledgerid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isCheck1() {
		return check1;
	}

	public void setCheck1(boolean check1) {
		this.check1 = check1;
	}

	public boolean isCheck2() {
		return check2;
	}

	public void setCheck2(boolean check2) {
		this.check2 = check2;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getGstcalculate() {
		return gstcalculate;
	}

	public void setGstcalculate(String gstcalculate) {
		this.gstcalculate = gstcalculate;
	}

	public double getParsentage() {
		return parsentage;
	}

	public void setParsentage(double parsentage) {
		this.parsentage = parsentage;
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
	
	public String getInv_voucherid() {
		return inv_voucherid;
	}

	public void setInv_voucherid(String inv_voucherid) {
		this.inv_voucherid = inv_voucherid;
	}

	public String getInv_voucherno() {
		return inv_voucherno;
	}

	public void setInv_voucherno(String inv_voucherno) {
		this.inv_voucherno = inv_voucherno;
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

	public Receipt_accounttransaction getrAccounttransaction() {
		return rAccounttransaction;
	}

	public void setrAccounttransaction(Receipt_accounttransaction rAccounttransaction) {
		this.rAccounttransaction = rAccounttransaction;
	}

	@Override
	public String toString() {
		return "Account_details [id=" + id + ", voucherno=" + voucherno + ", branchcode=" + branchcode
				+ ", entrydate=" + entrydate + ", ledgerid=" + ledgerid + ", amount=" + amount + ", check1=" + check1
				+ ", check2=" + check2 + ", narration=" + narration + ", gstcalculate=" + gstcalculate + ", parsentage="
				+ parsentage + ", entrybranchcode=" + entrybranchcode + ", uniquevoucherno=" + uniquevoucherno
				+ ", modified_type=" + modified_type + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by
				+ ", modified_on=" + modified_on + ", modified_by=" + modified_by + ", fin_year=" + fin_year
				+ ", username=" + username + ", rAccounttransaction=" + rAccounttransaction + "]";
	}
	
	
}
