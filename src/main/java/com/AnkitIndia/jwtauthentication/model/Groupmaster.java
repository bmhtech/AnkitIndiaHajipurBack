package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.AnkitIndia.jwtauthentication.model.Parent_control;
import com.AnkitIndia.jwtauthentication.model.Subgroupmaster;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Groupmaster", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"groupname","branchcode"})
})
public class Groupmaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;  
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String uniqucode;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String branchcode;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String groupcode;          
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String groupname;            
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double openingbalance;  
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double closingbalance;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String subgroupserialno;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String incomeexpencetype;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String groupcategory;  
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double sumtrailamount;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sequenceno;  
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String detailinbs;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String printingtext;                        
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double previousyrsbalance;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String standardhead;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String reservedgrouphead;                   
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double debitamount;  
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double creditamount;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String schname;
	
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
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String fin_year;      
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String username;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	//@JsonIgnore
	private String classy;   
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="groupmaster",cascade = CascadeType.ALL)
	@JsonIgnore
	private Parent_control parent_control;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="groupmaster",cascade = CascadeType.ALL)
	@JsonIgnore
	private Subgroupmaster subgroupmaster;

	public Groupmaster() {
		super();
	}

	public Groupmaster(long id, String uniqucode, String branchcode, String groupcode, String groupname,
			double openingbalance, double closingbalance, String subgroupserialno, String incomeexpencetype,
			String groupcategory, double sumtrailamount, String sequenceno, String detailinbs, String printingtext,
			double previousyrsbalance, String standardhead, String reservedgrouphead, double debitamount,
			double creditamount, String schname,LocalDateTime inserted_on, String inserted_by,
			String inserted_location, LocalDateTime modified_on, String modified_by, String modified_location,
			String fin_year, String status, String classy, Parent_control parent_control,
			Subgroupmaster subgroupmaster) {
		super();
		this.id = id;
		this.uniqucode = uniqucode;
		this.branchcode = branchcode;
		this.groupcode = groupcode;
		this.groupname = groupname;
		this.openingbalance = openingbalance;
		this.closingbalance = closingbalance;
		this.subgroupserialno = subgroupserialno;
		this.incomeexpencetype = incomeexpencetype;
		this.groupcategory = groupcategory;
		this.sumtrailamount = sumtrailamount;
		this.sequenceno = sequenceno;
		this.detailinbs = detailinbs;
		this.printingtext = printingtext;
		this.previousyrsbalance = previousyrsbalance;
		this.standardhead = standardhead;
		this.reservedgrouphead = reservedgrouphead;
		this.debitamount = debitamount;
		this.creditamount = creditamount;
		this.schname = schname;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.inserted_location = inserted_location;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.modified_location = modified_location;
		this.fin_year = fin_year;
		this.status = status;
		this.classy = classy;
		this.parent_control = parent_control;
		this.subgroupmaster = subgroupmaster;
	}
	
	public Groupmaster(String branchcode, String groupcode, String groupname, String inserted_by,
			String subgroupserialno, String incomeexpencetype, String groupcategory, String sequenceno,
			String detailinbs, String printingtext, String standardhead, String reservedgrouphead, String schname,
			String inserted_location, String fin_year, String status) {
		super();
		this.branchcode = branchcode;
		this.groupcode = groupcode;
		this.groupname = groupname;
		this.inserted_by = inserted_by;
		this.subgroupserialno = subgroupserialno;
		this.incomeexpencetype = incomeexpencetype;
		this.groupcategory = groupcategory;
		this.sequenceno = sequenceno;
		this.detailinbs = detailinbs;
		this.printingtext = printingtext;
		this.standardhead = standardhead;
		this.reservedgrouphead = reservedgrouphead;
		this.schname = schname;
		this.inserted_location = inserted_location;
		this.fin_year = fin_year;
		this.status = status;
	}
	
	public Groupmaster(String branchcode, String groupcode, String groupname, String inserted_by,
			String subgroupserialno, String incomeexpencetype, String groupcategory, String sequenceno,
			String detailinbs, String printingtext, String standardhead, String reservedgrouphead, String schname,
			String inserted_location, String fin_year, String status,String uniqucode) {
		super();
		this.branchcode = branchcode;
		this.groupcode = groupcode;
		this.groupname = groupname;
		this.inserted_by = inserted_by;
		this.subgroupserialno = subgroupserialno;
		this.incomeexpencetype = incomeexpencetype;
		this.groupcategory = groupcategory;
		this.sequenceno = sequenceno;
		this.detailinbs = detailinbs;
		this.printingtext = printingtext;
		this.standardhead = standardhead;
		this.reservedgrouphead = reservedgrouphead;
		this.schname = schname;
		this.inserted_location = inserted_location;
		this.fin_year = fin_year;
		this.status = status;
		this.uniqucode=uniqucode;
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

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public double getOpeningbalance() {
		return openingbalance;
	}

	public void setOpeningbalance(double openingbalance) {
		this.openingbalance = openingbalance;
	}

	public double getClosingbalance() {
		return closingbalance;
	}

	public void setClosingbalance(double closingbalance) {
		this.closingbalance = closingbalance;
	}

	public String getSubgroupserialno() {
		return subgroupserialno;
	}

	public void setSubgroupserialno(String subgroupserialno) {
		this.subgroupserialno = subgroupserialno;
	}

	public String getIncomeexpencetype() {
		return incomeexpencetype;
	}

	public void setIncomeexpencetype(String incomeexpencetype) {
		this.incomeexpencetype = incomeexpencetype;
	}

	public String getGroupcategory() {
		return groupcategory;
	}

	public void setGroupcategory(String groupcategory) {
		this.groupcategory = groupcategory;
	}

	public double getSumtrailamount() {
		return sumtrailamount;
	}

	public void setSumtrailamount(double sumtrailamount) {
		this.sumtrailamount = sumtrailamount;
	}

	public String getSequenceno() {
		return sequenceno;
	}

	public void setSequenceno(String sequenceno) {
		this.sequenceno = sequenceno;
	}

	public String getDetailinbs() {
		return detailinbs;
	}

	public void setDetailinbs(String detailinbs) {
		this.detailinbs = detailinbs;
	}

	public String getPrintingtext() {
		return printingtext;
	}

	public void setPrintingtext(String printingtext) {
		this.printingtext = printingtext;
	}

	public double getPreviousyrsbalance() {
		return previousyrsbalance;
	}

	public void setPreviousyrsbalance(double previousyrsbalance) {
		this.previousyrsbalance = previousyrsbalance;
	}

	public String getStandardhead() {
		return standardhead;
	}

	public void setStandardhead(String standardhead) {
		this.standardhead = standardhead;
	}

	public String getReservedgrouphead() {
		return reservedgrouphead;
	}

	public void setReservedgrouphead(String reservedgrouphead) {
		this.reservedgrouphead = reservedgrouphead;
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

	public String getSchname() {
		return schname;
	}

	public void setSchname(String schname) {
		this.schname = schname;
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

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClassy() {
		return classy;
	}

	public void setClassy(String classy) {
		this.classy = classy;
	}

	public Parent_control getParent_control() {
		return parent_control;
	}

	public void setParent_control(Parent_control parent_control) {
		this.parent_control = parent_control;
	}

	public Subgroupmaster getSubgroupmaster() {
		return subgroupmaster;
	}

	public void setSubgroupmaster(Subgroupmaster subgroupmaster) {
		this.subgroupmaster = subgroupmaster;
	}

	@Override
	public String toString() {
		return "Groupmaster [id=" + id + ", uniqucode=" + uniqucode + ", branchcode=" + branchcode + ", groupcode="
				+ groupcode + ", groupname=" + groupname + ", openingbalance=" + openingbalance + ", closingbalance="
				+ closingbalance + ", subgroupserialno=" + subgroupserialno + ", incomeexpencetype=" + incomeexpencetype
				+ ", groupcategory=" + groupcategory + ", sumtrailamount=" + sumtrailamount + ", sequenceno="
				+ sequenceno + ", detailinbs=" + detailinbs + ", printingtext=" + printingtext + ", previousyrsbalance="
				+ previousyrsbalance + ", standardhead=" + standardhead + ", reservedgrouphead=" + reservedgrouphead
				+ ", debitamount=" + debitamount + ", creditamount=" + creditamount + ", schname=" + schname
				+ ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by
				+ ", inserted_location=" + inserted_location + ", modified_on=" + modified_on + ", modified_by="
				+ modified_by + ", modified_location=" + modified_location + ", fin_year=" + fin_year + ", status="
				+ status + ", classy=" + classy + ", parent_control=" + parent_control + ", subgroupmaster="
				+ subgroupmaster + "]";
	}

}

/*@Entity
@Table(name="Groupmaster")
@EntityListeners(AuditingEntityListener.class)
public class Groupmaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 2)
	private String groupcode;
	
	@Size(max = 45)
	private String groupname;
	
	private double openingbalance;
	
	private double closingbalance;
	
	private int subgroupserialno;
	
	@Size(max = 1)
	private String incomeexpencetype;
	
	@Size(max = 1)
	private String groupcategory;
	
	private double sumtrailamount;
	
	private int sequenceno;
	
	@Size(max = 10)
	private String detailinbs;
	
	@Size(max = 45)
	private String printingtext;
	
	private double previousyrsbalance;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean standardhead;
	
	@Size(max = 45)
	private String reservedgrouphead;
	
	private double debitamount;
	
	private double creditamount;
	
	@Size(max = 100)
	private String schname;
	
	@Size(max = 10)
	private String fyear;
	
	
	private LocalDateTime created_on;
	
	@Size(max = 50)
	private String inserted_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public double getOpeningbalance() {
		return openingbalance;
	}

	public void setOpeningbalance(double openingbalance) {
		this.openingbalance = openingbalance;
	}

	public double getClosingbalance() {
		return closingbalance;
	}

	public void setClosingbalance(double closingbalance) {
		this.closingbalance = closingbalance;
	}

	public int getSubgroupserialno() {
		return subgroupserialno;
	}

	public void setSubgroupserialno(int subgroupserialno) {
		this.subgroupserialno = subgroupserialno;
	}

	public String getIncomeexpencetype() {
		return incomeexpencetype;
	}

	public void setIncomeexpencetype(String incomeexpencetype) {
		this.incomeexpencetype = incomeexpencetype;
	}

	public String getGroupcategory() {
		return groupcategory;
	}

	public void setGroupcategory(String groupcategory) {
		this.groupcategory = groupcategory;
	}

	public double getSumtrailamount() {
		return sumtrailamount;
	}

	public void setSumtrailamount(double sumtrailamount) {
		this.sumtrailamount = sumtrailamount;
	}

	public int getSequenceno() {
		return sequenceno;
	}

	public void setSequenceno(int sequenceno) {
		this.sequenceno = sequenceno;
	}

	public String getDetailinbs() {
		return detailinbs;
	}

	public void setDetailinbs(String detailinbs) {
		this.detailinbs = detailinbs;
	}

	public String getPrintingtext() {
		return printingtext;
	}

	public void setPrintingtext(String printingtext) {
		this.printingtext = printingtext;
	}

	public double getPreviousyrsbalance() {
		return previousyrsbalance;
	}

	public void setPreviousyrsbalance(double previousyrsbalance) {
		this.previousyrsbalance = previousyrsbalance;
	}

	public boolean isStandardhead() {
		return standardhead;
	}

	public void setStandardhead(boolean standardhead) {
		this.standardhead = standardhead;
	}

	public String getReservedgrouphead() {
		return reservedgrouphead;
	}

	public void setReservedgrouphead(String reservedgrouphead) {
		this.reservedgrouphead = reservedgrouphead;
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

	public String getSchname() {
		return schname;
	}

	public void setSchname(String schname) {
		this.schname = schname;
	}

	public String getFyear() {
		return fyear;
	}

	public void setFyear(String fyear) {
		this.fyear = fyear;
	}

	public LocalDateTime getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDateTime created_on) {
		this.created_on = created_on;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}
	
	
	

}*/
