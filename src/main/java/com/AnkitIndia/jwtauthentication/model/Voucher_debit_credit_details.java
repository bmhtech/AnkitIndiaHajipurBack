package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Ledgermaster_debit_credit_details")
public class Voucher_debit_credit_details {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String uniqucode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String branchcode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String voucher_type;

	@Column(columnDefinition="varchar(50) default '0'")
	private String particulars;

	@Column(columnDefinition="varchar(50) default '0'")
	private String voucher_date;

	@Column(columnDefinition="Double(10,2) default 0.00")
	private double gross_amt;

	@Column(columnDefinition="varchar(50) default '0'")
	private String pre_adjust;

	@Column(columnDefinition="Double(10,2) default 0.00")
	private double due_amt;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "l_id")
    private Ledgermaster ledgermaster;

	public Voucher_debit_credit_details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Voucher_debit_credit_details(long id, String uniqucode, String branchcode, String voucher_type,
			String particulars, String voucher_date, double gross_amt, String pre_adjust, double due_amt,
			Ledgermaster ledgermaster) {
		super();
		this.id = id;
		this.uniqucode = uniqucode;
		this.branchcode = branchcode;
		this.voucher_type = voucher_type;
		this.particulars = particulars;
		this.voucher_date = voucher_date;
		this.gross_amt = gross_amt;
		this.pre_adjust = pre_adjust;
		this.due_amt = due_amt;
		this.ledgermaster = ledgermaster;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUniqucode() {
		return uniqucode;
	}

	public void setUniqucode(String uniqucode) {
		this.uniqucode = uniqucode;
	}

	public String getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	public String getVoucher_type() {
		return voucher_type;
	}

	public void setVoucher_type(String voucher_type) {
		this.voucher_type = voucher_type;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getVoucher_date() {
		return voucher_date;
	}

	public void setVoucher_date(String voucher_date) {
		this.voucher_date = voucher_date;
	}

	public double getGross_amt() {
		return gross_amt;
	}

	public void setGross_amt(double gross_amt) {
		this.gross_amt = gross_amt;
	}

	public String getPre_adjust() {
		return pre_adjust;
	}

	public void setPre_adjust(String pre_adjust) {
		this.pre_adjust = pre_adjust;
	}

	public double getDue_amt() {
		return due_amt;
	}

	public void setDue_amt(double due_amt) {
		this.due_amt = due_amt;
	}

	public Ledgermaster getLedgermaster() {
		return ledgermaster;
	}

	public void setLedgermaster(Ledgermaster ledgermaster) {
		this.ledgermaster = ledgermaster;
	}
	
}
