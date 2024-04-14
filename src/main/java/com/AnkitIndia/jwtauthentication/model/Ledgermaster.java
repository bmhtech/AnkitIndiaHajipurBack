package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Ledgermaster", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uniqucode"}),
        @UniqueConstraint(columnNames = {"ledgername"})
})
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "Ledgermaster.saveLedgerDrCrDetails", 
			procedureName = "saveLedgerDrCrDetails", parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniqucode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "branchcode", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "voucher_type", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "particulars", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "voucher_date", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "gross_amt", type = Double.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "pre_adjust", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "due_amt", type = Double.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	
	@NamedStoredProcedureQuery(name = "Ledgermaster.saveLedgerMaster", 
	procedureName = "saveLedgerMaster", parameters = {
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrps", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrplength", type = Integer.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "balance", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "defsubgrp", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "uniqueledcode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgername", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "uniqucode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "finyear", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "aliasname", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "billbybill", type = char.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inventoryval", type = char.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "costcenter", type = char.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "gstval", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "gstrate", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "mailingname", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "defaultcredit", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "address", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "placecode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "panno", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "contactperson", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "mobileno", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "emailid", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "drcr", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "altermobileno", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "alteremailid", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "dlno", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "gstn", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "cst", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "partytype", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "gross", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "caldatefor", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "tinno", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "statename", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "statecode", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "ldt", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "acnumber", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "bankbranchname", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "ifsccode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "gsttype", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "gstpaymenttype", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "mainsubgroupcode", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "maincontrol_acc", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	
	
	@NamedStoredProcedureQuery(name = "Ledgermaster.saveLedgerDrCr",
	procedureName = "saveLedgerDrCr", parameters = {
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "branchcode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceno", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "referencedate", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "due", type = Double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "drawnon", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_location", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "narration_dtls", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucherno", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "drcr", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "trnsamt", type = Double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "spMinus", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid2", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "dtldledgerid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "spMinusD", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "dtlsamt", type = Double.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "dtldledgerid2", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),


@NamedStoredProcedureQuery(name = "Ledgermaster.saveLedgerPcPb",
procedureName = "saveLedgerPcPb", parameters = {
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "branchcode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceno", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "referencedate", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "due", type = Double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "drawnon", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_location", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "narration_dtls", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucherno", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "drcr", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "trnsamt", type = Double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "spMinus", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid2", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "dtldledgerid", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "spMinusD", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "dtlsamt", type = Double.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "dtldledgerid2", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),

@NamedStoredProcedureQuery(name = "Ledgermaster.saveLedgerJr",
procedureName = "saveLedgerJr", parameters = {
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "branchcode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceno", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "referencedate", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "due", type = Double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "drawnon", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_location", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "narration_dtls", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucherno", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "drcr", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "trnsamt", type = Double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "spMinus", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid2", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "dtldledgerid", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "spMinusD", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "dtlsamt", type = Double.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "dtldledgerid2", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),

@NamedStoredProcedureQuery(name = "Ledgermaster.saveLedgerBillByBill",
procedureName = "saveLedgerBillByBill", parameters = {
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "branchcode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceno", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "referencedate", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "billamount", type = Double.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "adjustmentamount", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "duesamount", type = Double.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "vouchertype", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrybranchcode", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "out_uniquevoucherno", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),
	  @StoredProcedureParameter(mode = ParameterMode.IN, name = "fyear", type = String.class),@StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)})
	})
public class Ledgermaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String uniqucode;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String ledgerid;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String maincontrol_acc;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String mailing_dtls;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String bank_dtls;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tds_dtls;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String ledgername;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String aliasname;
	
	@Column(columnDefinition="char(1) default '0'")
	private char billbybill;
	
	@Column(columnDefinition="char(1) default '0'")
	private char inventoryvalue;
	
	@Column(columnDefinition="char(1) default '0'")
	private char costcenter;
	
	@Column(columnDefinition="char(1) default '0'")
	private char activeledger;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String defaultcredit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String mailingname;
	
	@Column(columnDefinition="varchar(150) default '0'")
	private String address;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String placecode;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String panno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String contactperson;
	
	@Column(columnDefinition="varchar(13) default '0'")
	private String mobileno;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String emailid;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double openingbalance;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double currentbalance;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String drcr;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String debitortype;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double principalamount;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double depreciationamount;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double depreciationrate;
	
	@Column(columnDefinition="varchar(13) default '0'")
	private String altermobileno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String alteremailid;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean standardhead;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double debitamount;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double creditamount;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double opbal1;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double opbal2;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String statename;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String statecode;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String dlno;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String gstn;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String cst;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String partytype;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String gross;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String caldatefor;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String tinno;
	
	@Column(columnDefinition="char(1) default '0'")
	private char gstcalculate;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String parsentage;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String allitemname;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String itemlist;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String acnumber;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String bankbranchname;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String ifsccode;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String gst;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String gsttype;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String gstpaymenttype;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String mainsubgroupcode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String subgroupcode;
	
	@Column(columnDefinition="varchar(50) default 'INSERTED'")
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
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sub_group_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String group_name;
	
	////////////////////////////
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="ledgermaster",cascade = CascadeType.ALL)
	private Set<Voucher_debit_credit_details> voucher_debit_credit_details;

	public Ledgermaster() {
		super();
	}

	public Ledgermaster(long id, String uniqucode, String ledgerid, String maincontrol_acc,
			String mailing_dtls, String bank_dtls, String tds_dtls, String ledgername, String aliasname,
			char billbybill, char inventoryvalue, char costcenter, char activeledger, String defaultcredit,
			String mailingname, String address, String placecode, String panno, String contactperson, String mobileno,
			String emailid, double openingbalance, double currentbalance, String drcr, String debitortype,
			double principalamount, double depreciationamount, double depreciationrate, String altermobileno,
			String alteremailid, boolean standardhead, double debitamount, double creditamount, double opbal1,
			double opbal2, String statename, String statecode, String dlno, String gstn, String cst, String partytype,
			String gross, String caldatefor, String tinno, char gstcalculate, String parsentage, String allitemname,
			String itemlist, String acnumber, String bankbranchname, String ifsccode, String gst, String gsttype,
			String gstpaymenttype, String mainsubgroupcode, String subgroupcode, String modified_type,
			LocalDateTime inserted_on, String inserted_by, LocalDateTime modified_on,
			String modified_by, String fin_year, String username,String sub_group_name,String group_name,
			Set<Voucher_debit_credit_details> voucher_debit_credit_details) {
		super();
		this.id = id;
		this.uniqucode = uniqucode;
		this.ledgerid = ledgerid;
		this.maincontrol_acc = maincontrol_acc;
		this.mailing_dtls = mailing_dtls;
		this.bank_dtls = bank_dtls;
		this.tds_dtls = tds_dtls;
		this.ledgername = ledgername;
		this.aliasname = aliasname;
		this.billbybill = billbybill;
		this.inventoryvalue = inventoryvalue;
		this.costcenter = costcenter;
		this.activeledger = activeledger;
		this.defaultcredit = defaultcredit;
		this.mailingname = mailingname;
		this.address = address;
		this.placecode = placecode;
		this.panno = panno;
		this.contactperson = contactperson;
		this.mobileno = mobileno;
		this.emailid = emailid;
		this.openingbalance = openingbalance;
		this.currentbalance = currentbalance;
		this.drcr = drcr;
		this.debitortype = debitortype;
		this.principalamount = principalamount;
		this.depreciationamount = depreciationamount;
		this.depreciationrate = depreciationrate;
		this.altermobileno = altermobileno;
		this.alteremailid = alteremailid;
		this.standardhead = standardhead;
		this.debitamount = debitamount;
		this.creditamount = creditamount;
		this.opbal1 = opbal1;
		this.opbal2 = opbal2;
		this.statename = statename;
		this.statecode = statecode;
		this.dlno = dlno;
		this.gstn = gstn;
		this.cst = cst;
		this.partytype = partytype;
		this.gross = gross;
		this.caldatefor = caldatefor;
		this.tinno = tinno;
		this.gstcalculate = gstcalculate;
		this.parsentage = parsentage;
		this.allitemname = allitemname;
		this.itemlist = itemlist;
		this.acnumber = acnumber;
		this.bankbranchname = bankbranchname;
		this.ifsccode = ifsccode;
		this.gst = gst;
		this.gsttype = gsttype;
		this.gstpaymenttype = gstpaymenttype;
		this.mainsubgroupcode = mainsubgroupcode;
		this.subgroupcode = subgroupcode;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.fin_year = fin_year;
		this.username = username;
		this.username = sub_group_name;
		this.username = group_name;
		this.voucher_debit_credit_details = voucher_debit_credit_details;
	}

	public String getMaincontrol_acc() {
		return maincontrol_acc;
	}

	public void setMaincontrol_acc(String maincontrol_acc) {
		this.maincontrol_acc = maincontrol_acc;
	}

	public String getMailing_dtls() {
		return mailing_dtls;
	}

	public void setMailing_dtls(String mailing_dtls) {
		this.mailing_dtls = mailing_dtls;
	}

	public String getBank_dtls() {
		return bank_dtls;
	}

	public void setBank_dtls(String bank_dtls) {
		this.bank_dtls = bank_dtls;
	}

	public String getTds_dtls() {
		return tds_dtls;
	}

	public void setTds_dtls(String tds_dtls) {
		this.tds_dtls = tds_dtls;
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


	public String getLedgerid() {
		return ledgerid;
	}

	public void setLedgerid(String ledgerid) {
		this.ledgerid = ledgerid;
	}

	public String getLedgername() {
		return ledgername;
	}

	public void setLedgername(String ledgername) {
		this.ledgername = ledgername;
	}

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public char getBillbybill() {
		return billbybill;
	}

	public void setBillbybill(char billbybill) {
		this.billbybill = billbybill;
	}

	public char getInventoryvalue() {
		return inventoryvalue;
	}

	public void setInventoryvalue(char inventoryvalue) {
		this.inventoryvalue = inventoryvalue;
	}

	public char getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(char costcenter) {
		this.costcenter = costcenter;
	}

	public char getActiveledger() {
		return activeledger;
	}

	public void setActiveledger(char activeledger) {
		this.activeledger = activeledger;
	}

	public String getDefaultcredit() {
		return defaultcredit;
	}

	public void setDefaultcredit(String defaultcredit) {
		this.defaultcredit = defaultcredit;
	}

	public String getMailingname() {
		return mailingname;
	}

	public void setMailingname(String mailingname) {
		this.mailingname = mailingname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPlacecode() {
		return placecode;
	}

	public void setPlacecode(String placecode) {
		this.placecode = placecode;
	}

	public String getPanno() {
		return panno;
	}

	public void setPanno(String panno) {
		this.panno = panno;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public double getOpeningbalance() {
		return openingbalance;
	}

	public void setOpeningbalance(double openingbalance) {
		this.openingbalance = openingbalance;
	}

	public double getCurrentbalance() {
		return currentbalance;
	}

	public void setCurrentbalance(double currentbalance) {
		this.currentbalance = currentbalance;
	}

	public String getDrcr() {
		return drcr;
	}

	public void setDrcr(String drcr) {
		this.drcr = drcr;
	}

	public String getDebitortype() {
		return debitortype;
	}

	public void setDebitortype(String debitortype) {
		this.debitortype = debitortype;
	}

	public double getPrincipalamount() {
		return principalamount;
	}

	public void setPrincipalamount(double principalamount) {
		this.principalamount = principalamount;
	}

	public double getDepreciationamount() {
		return depreciationamount;
	}

	public void setDepreciationamount(double depreciationamount) {
		this.depreciationamount = depreciationamount;
	}

	public double getDepreciationrate() {
		return depreciationrate;
	}

	public void setDepreciationrate(double depreciationrate) {
		this.depreciationrate = depreciationrate;
	}

	public String getAltermobileno() {
		return altermobileno;
	}

	public void setAltermobileno(String altermobileno) {
		this.altermobileno = altermobileno;
	}

	public String getAlteremailid() {
		return alteremailid;
	}

	public void setAlteremailid(String alteremailid) {
		this.alteremailid = alteremailid;
	}

	public boolean isStandardhead() {
		return standardhead;
	}

	public void setStandardhead(boolean standardhead) {
		this.standardhead = standardhead;
	}

	public double getDebitamount() {
		return debitamount;
	}

	public void setDebitamount(double debitamount) {
		this.debitamount = debitamount;
	}

	public double getCreditamount() {
		return creditamount;
	}

	public void setCreditamount(double creditamount) {
		this.creditamount = creditamount;
	}

	public double getOpbal1() {
		return opbal1;
	}

	public void setOpbal1(double opbal1) {
		this.opbal1 = opbal1;
	}

	public double getOpbal2() {
		return opbal2;
	}

	public void setOpbal2(double opbal2) {
		this.opbal2 = opbal2;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getDlno() {
		return dlno;
	}

	public void setDlno(String dlno) {
		this.dlno = dlno;
	}

	public String getGstn() {
		return gstn;
	}

	public void setGstn(String gstn) {
		this.gstn = gstn;
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public String getPartytype() {
		return partytype;
	}

	public void setPartytype(String partytype) {
		this.partytype = partytype;
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getCaldatefor() {
		return caldatefor;
	}

	public void setCaldatefor(String caldatefor) {
		this.caldatefor = caldatefor;
	}

	public String getTinno() {
		return tinno;
	}

	public void setTinno(String tinno) {
		this.tinno = tinno;
	}
	
	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public char getGstcalculate() {
		return gstcalculate;
	}

	public void setGstcalculate(char gstcalculate) {
		this.gstcalculate = gstcalculate;
	}

	public String getParsentage() {
		return parsentage;
	}

	public void setParsentage(String parsentage) {
		this.parsentage = parsentage;
	}

	public String getAllitemname() {
		return allitemname;
	}

	public void setAllitemname(String allitemname) {
		this.allitemname = allitemname;
	}

	public String getItemlist() {
		return itemlist;
	}

	public void setItemlist(String itemlist) {
		this.itemlist = itemlist;
	}

	public String getAcnumber() {
		return acnumber;
	}

	public void setAcnumber(String acnumber) {
		this.acnumber = acnumber;
	}

	public String getBankbranchname() {
		return bankbranchname;
	}

	public void setBankbranchname(String bankbranchname) {
		this.bankbranchname = bankbranchname;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public String getGsttype() {
		return gsttype;
	}

	public void setGsttype(String gsttype) {
		this.gsttype = gsttype;
	}

	public String getGstpaymenttype() {
		return gstpaymenttype;
	}

	public void setGstpaymenttype(String gstpaymenttype) {
		this.gstpaymenttype = gstpaymenttype;
	}

	public String getMainsubgroupcode() {
		return mainsubgroupcode;
	}

	public void setMainsubgroupcode(String mainsubgroupcode) {
		this.mainsubgroupcode = mainsubgroupcode;
	}
	
	public String getSubgroupcode() {
		return subgroupcode;
	}

	public void setSubgroupcode(String subgroupcode) {
		this.subgroupcode = subgroupcode;
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

	public String getSub_group_name() {
		return sub_group_name;
	}

	public void setSub_group_name(String sub_group_name) {
		this.sub_group_name = sub_group_name;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public Set<Voucher_debit_credit_details> getVoucher_debit_credit_details() {
		return voucher_debit_credit_details;
	}

	public void setVoucher_debit_credit_details(Set<Voucher_debit_credit_details> voucher_debit_credit_details) {
		this.voucher_debit_credit_details = voucher_debit_credit_details;
	}

	@Override
	public String toString() {
		return "Ledgermaster [id=" + id + ", uniqucode=" + uniqucode + ", ledgerid=" + ledgerid + ", maincontrol_acc="
				+ maincontrol_acc + ", mailing_dtls=" + mailing_dtls + ", bank_dtls=" + bank_dtls + ", tds_dtls="
				+ tds_dtls + ", ledgername=" + ledgername + ", aliasname=" + aliasname + ", billbybill=" + billbybill
				+ ", inventoryvalue=" + inventoryvalue + ", costcenter=" + costcenter + ", activeledger=" + activeledger
				+ ", defaultcredit=" + defaultcredit + ", mailingname=" + mailingname + ", address=" + address
				+ ", placecode=" + placecode + ", panno=" + panno + ", contactperson=" + contactperson + ", mobileno="
				+ mobileno + ", emailid=" + emailid + ", openingbalance=" + openingbalance + ", currentbalance="
				+ currentbalance + ", drcr=" + drcr + ", debitortype=" + debitortype + ", principalamount="
				+ principalamount + ", depreciationamount=" + depreciationamount + ", depreciationrate="
				+ depreciationrate + ", altermobileno=" + altermobileno + ", alteremailid=" + alteremailid
				+ ", standardhead=" + standardhead + ", debitamount=" + debitamount + ", creditamount=" + creditamount
				+ ", opbal1=" + opbal1 + ", opbal2=" + opbal2 + ", statename=" + statename + ", statecode=" + statecode
				+ ", dlno=" + dlno + ", gstn=" + gstn + ", cst=" + cst + ", partytype=" + partytype + ", gross=" + gross
				+ ", caldatefor=" + caldatefor + ", tinno=" + tinno + ", gstcalculate=" + gstcalculate + ", parsentage="
				+ parsentage + ", allitemname=" + allitemname + ", itemlist=" + itemlist + ", acnumber=" + acnumber
				+ ", bankbranchname=" + bankbranchname + ", ifsccode=" + ifsccode + ", gst=" + gst + ", gsttype="
				+ gsttype + ", gstpaymenttype=" + gstpaymenttype + ", mainsubgroupcode=" + mainsubgroupcode
				+ ", subgroupcode=" + subgroupcode + ", modified_type=" + modified_type + ", inserted_on=" + inserted_on
				+ ", inserted_by=" + inserted_by + ", modified_on=" + modified_on + ", modified_by=" + modified_by
				+ ", fin_year=" + fin_year + ", username=" + username + ", sub_group_name=" + sub_group_name + ", group_name=" + group_name + ", voucher_debit_credit_details="
				+ voucher_debit_credit_details + ", getMaincontrol_acc()=" + getMaincontrol_acc()
				+ ", getMailing_dtls()=" + getMailing_dtls() + ", getBank_dtls()=" + getBank_dtls() + ", getTds_dtls()="
				+ getTds_dtls() + ", getId()=" + getId() + ", getUniqucode()=" + getUniqucode() + ", getLedgerid()="
				+ getLedgerid() + ", getLedgername()=" + getLedgername() + ", getAliasname()=" + getAliasname()
				+ ", getBillbybill()=" + getBillbybill() + ", getInventoryvalue()=" + getInventoryvalue()
				+ ", getCostcenter()=" + getCostcenter() + ", getActiveledger()=" + getActiveledger()
				+ ", getDefaultcredit()=" + getDefaultcredit() + ", getMailingname()=" + getMailingname()
				+ ", getAddress()=" + getAddress() + ", getPlacecode()=" + getPlacecode() + ", getPanno()=" + getPanno()
				+ ", getContactperson()=" + getContactperson() + ", getMobileno()=" + getMobileno() + ", getEmailid()="
				+ getEmailid() + ", getOpeningbalance()=" + getOpeningbalance() + ", getCurrentbalance()="
				+ getCurrentbalance() + ", getDrcr()=" + getDrcr() + ", getDebitortype()=" + getDebitortype()
				+ ", getPrincipalamount()=" + getPrincipalamount() + ", getDepreciationamount()="
				+ getDepreciationamount() + ", getDepreciationrate()=" + getDepreciationrate() + ", getAltermobileno()="
				+ getAltermobileno() + ", getAlteremailid()=" + getAlteremailid() + ", isStandardhead()="
				+ isStandardhead() + ", getDebitamount()=" + getDebitamount() + ", getCreditamount()="
				+ getCreditamount() + ", getOpbal1()=" + getOpbal1() + ", getOpbal2()=" + getOpbal2()
				+ ", getStatename()=" + getStatename() + ", getStatecode()=" + getStatecode() + ", getDlno()="
				+ getDlno() + ", getGstn()=" + getGstn() + ", getCst()=" + getCst() + ", getPartytype()="
				+ getPartytype() + ", getGross()=" + getGross() + ", getCaldatefor()=" + getCaldatefor()
				+ ", getTinno()=" + getTinno() + ", getGst()=" + getGst() + ", getGstcalculate()=" + getGstcalculate()
				+ ", getParsentage()=" + getParsentage() + ", getAllitemname()=" + getAllitemname() + ", getItemlist()="
				+ getItemlist() + ", getAcnumber()=" + getAcnumber() + ", getBankbranchname()=" + getBankbranchname()
				+ ", getIfsccode()=" + getIfsccode() + ", getGsttype()=" + getGsttype() + ", getGstpaymenttype()="
				+ getGstpaymenttype() + ", getMainsubgroupcode()=" + getMainsubgroupcode() + ", getSubgroupcode()="
				+ getSubgroupcode() + ", getModified_type()=" + getModified_type() + ", getInserted_on()="
				+ getInserted_on() + ", getInserted_by()=" + getInserted_by() + ", getModified_on()=" + getModified_on()
				+ ", getModified_by()=" + getModified_by() + ", getFin_year()=" + getFin_year() + ", getUsername()="
				+ getUsername() + " getSub_group_name()=" + getSub_group_name() + " getGroup_name()=" + getGroup_name() + ", getVoucher_debit_credit_details()=" + getVoucher_debit_credit_details()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
	
}
