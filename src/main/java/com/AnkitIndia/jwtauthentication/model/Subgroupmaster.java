package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.AnkitIndia.jwtauthentication.model.Groupmaster;
import com.AnkitIndia.jwtauthentication.model.Subgroupmaster;

@Entity
@Table(name="Subgroupmaster", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"subgroupname","branchcode"})
})
public class Subgroupmaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uniqucode;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String branchcode;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String subgroupcode;  
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String subgroupname;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double openingbalance;  
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double currentbalance;  
	
	private int ledgerslno;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double sumtrailbalance;  
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double previousyrbalance;  
	
	@Column(columnDefinition="tinyint(1) default 0 ")
	private boolean standardhead;  
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double debitamount;  
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double creditamount;          
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String parent_subgroup; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String parent_subgroupcode;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String parent_srno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String incomeexpencetype;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String groupcategory;  
	
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
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="gr_id")
	private Groupmaster groupmaster;

	public Subgroupmaster() {
		super();
	}

	public Subgroupmaster(long id, String uniqucode, String branchcode, String subgroupcode, String subgroupname,
			double openingbalance, double currentbalance, int ledgerslno, double sumtrailbalance,
			double previousyrbalance, boolean standardhead, double debitamount, double creditamount,
			String parent_subgroup, String parent_subgroupcode, String parent_srno, String incomeexpencetype,
			String groupcategory, LocalDateTime inserted_on, String inserted_by, String inserted_location,
			LocalDateTime modified_on, String modified_by, String modified_location, String fin_year, String status,
			String username, Groupmaster groupmaster) {
		super();
		this.id = id;
		this.uniqucode = uniqucode;
		this.branchcode = branchcode;
		this.subgroupcode = subgroupcode;
		this.subgroupname = subgroupname;
		this.openingbalance = openingbalance;
		this.currentbalance = currentbalance;
		this.ledgerslno = ledgerslno;
		this.sumtrailbalance = sumtrailbalance;
		this.previousyrbalance = previousyrbalance;
		this.standardhead = standardhead;
		this.debitamount = debitamount;
		this.creditamount = creditamount;
		this.parent_subgroup = parent_subgroup;
		this.parent_subgroupcode = parent_subgroupcode;
		this.parent_srno = parent_srno;
		this.incomeexpencetype = incomeexpencetype;
		this.groupcategory = groupcategory;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.inserted_location = inserted_location;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.modified_location = modified_location;
		this.fin_year = fin_year;
		this.status = status;
		this.username = username;
		this.groupmaster = groupmaster;
	}

	public Subgroupmaster(String uniqucode, String branchcode, String subgroupcode, String subgroupname,
			double openingbalance, double currentbalance, int ledgerslno, double sumtrailbalance,
			double previousyrbalance, boolean standardhead, double debitamount, double creditamount,
			String parent_subgroup, String parent_subgroupcode, String parent_srno, String incomeexpencetype,
			String groupcategory, LocalDateTime inserted_on, String inserted_by, String inserted_location,
			LocalDateTime modified_on, String modified_by, String modified_location, String fin_year, String status,
			String username) {
		super();
		this.uniqucode = uniqucode;
		this.branchcode = branchcode;
		this.subgroupcode = subgroupcode;
		this.subgroupname = subgroupname;
		this.openingbalance = openingbalance;
		this.currentbalance = currentbalance;
		this.ledgerslno = ledgerslno;
		this.sumtrailbalance = sumtrailbalance;
		this.previousyrbalance = previousyrbalance;
		this.standardhead = standardhead;
		this.debitamount = debitamount;
		this.creditamount = creditamount;
		this.parent_subgroup = parent_subgroup;
		this.parent_subgroupcode = parent_subgroupcode;
		this.parent_srno = parent_srno;
		this.incomeexpencetype = incomeexpencetype;
		this.groupcategory = groupcategory;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.inserted_location = inserted_location;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.modified_location = modified_location;
		this.fin_year = fin_year;
		this.status = status;
		this.username = username;
	}

	public Subgroupmaster(String branchcode, String subgroupcode, String subgroupname, int ledgerslno,
			boolean standardhead, String inserted_by, String inserted_location, String fin_year,String incomeexpencetype,String groupcategory, String parent_subgroupcode,String parent_subgroup) {
		super();
		this.branchcode = branchcode;
		this.subgroupcode = subgroupcode;
		this.subgroupname = subgroupname;
		this.ledgerslno = ledgerslno;
		this.standardhead = standardhead;
		this.inserted_by = inserted_by;
		this.inserted_location = inserted_location;
		this.fin_year = fin_year;
		this.incomeexpencetype=incomeexpencetype;
		this.groupcategory=groupcategory;
		this.parent_subgroupcode=parent_subgroupcode;
		this.parent_subgroup=parent_subgroup;
	}
	
	public Subgroupmaster(String branchcode, String subgroupcode, String subgroupname, int ledgerslno,
			boolean standardhead, String inserted_by, String inserted_location, String fin_year, 
			 String parent_subgroup, String parent_subgroupcode,LocalDateTime inserted_on) {
		super();
		this.branchcode = branchcode;
		this.subgroupcode = subgroupcode;
		this.subgroupname = subgroupname;
		this.ledgerslno = ledgerslno;
		this.standardhead = standardhead;
		this.inserted_by = inserted_by;
		this.inserted_location = inserted_location;
		this.fin_year = fin_year;
		this.parent_subgroup = parent_subgroup;
		this.parent_subgroupcode = parent_subgroupcode;
		this.inserted_on = inserted_on;
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

	public String getSubgroupcode() {
		return subgroupcode;
	}

	public void setSubgroupcode(String subgroupcode) {
		this.subgroupcode = subgroupcode;
	}

	public String getSubgroupname() {
		return subgroupname;
	}

	public void setSubgroupname(String subgroupname) {
		this.subgroupname = subgroupname;
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

	public int getLedgerslno() {
		return ledgerslno;
	}

	public void setLedgerslno(int ledgerslno) {
		this.ledgerslno = ledgerslno;
	}

	public double getSumtrailbalance() {
		return sumtrailbalance;
	}

	public void setSumtrailbalance(double sumtrailbalance) {
		this.sumtrailbalance = sumtrailbalance;
	}

	public double getPreviousyrbalance() {
		return previousyrbalance;
	}

	public void setPreviousyrbalance(double previousyrbalance) {
		this.previousyrbalance = previousyrbalance;
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

	public String getParent_subgroup() {
		return parent_subgroup;
	}

	public void setParent_subgroup(String parent_subgroup) {
		this.parent_subgroup = parent_subgroup;
	}

	public String getParent_subgroupcode() {
		return parent_subgroupcode;
	}
	public Subgroupmaster getParent_subgroupcode1() {
		return getParent_subgroupcode1();
	}

	public void setParent_subgroupcode(String parent_subgroupcode) {
		this.parent_subgroupcode = parent_subgroupcode;
	}

	public String getParent_srno() {
		return parent_srno;
	}

	public void setParent_srno(String parent_srno) {
		this.parent_srno = parent_srno;
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

	public Groupmaster getGroupmaster() {
		return groupmaster;
	}

	public void setGroupmaster(Groupmaster groupmaster) {
		this.groupmaster = groupmaster;
	}

	@Override
	public String toString() {
		return "Subgroupmaster [id=" + id + ", uniqucode=" + uniqucode + ", branchcode=" + branchcode
				+ ", subgroupcode=" + subgroupcode + ", subgroupname=" + subgroupname + ", openingbalance="
				+ openingbalance + ", currentbalance=" + currentbalance + ", ledgerslno=" + ledgerslno
				+ ", sumtrailbalance=" + sumtrailbalance + ", previousyrbalance=" + previousyrbalance
				+ ", standardhead=" + standardhead + ", debitamount=" + debitamount + ", creditamount=" + creditamount
				+ ", parent_subgroup=" + parent_subgroup + ", parent_subgroupcode=" + parent_subgroupcode
				+ ", parent_srno=" + parent_srno + ", incomeexpencetype=" + incomeexpencetype + ", groupcategory="
				+ groupcategory + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by
				+ ", inserted_location=" + inserted_location + ", modified_on=" + modified_on + ", modified_by="
				+ modified_by + ", modified_location=" + modified_location + ", fin_year=" + fin_year + ", status="
				+ status + ", username=" + username + ", groupmaster=" + groupmaster + "]";
	}
	
}

/*@Entity
@Table(name="Subgroupmaster")
public class Subgroupmaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 20)
	private String subgroupcode;  
	
	@Size(max = 50)
	private String subgroupname;
	
	@Column(columnDefinition = "Decimal(15,2)")
	private double openingbalance;
	
	@Column(columnDefinition = "Decimal(15,2)")
	private double currentbalance;
	
	private int ledgerslno;
	
	@Column(columnDefinition = "Decimal(15,2)")
	private double sumtrailbalance;
	
	@Column(columnDefinition = "Decimal(15,2)")
	private double previousyrbalance;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean standardhead;
	
	@Column(columnDefinition = "Decimal(15,2)")
	private double debitamount;
	
	@Column(columnDefinition = "Decimal(15,2)")
	private double creditamount;
	
	@Size(max = 10)
	private String fin_year;
	
	@Size(max = 100)
	private String inserted_by;
	
	private  LocalDateTime inserted_on;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubgroupcode() {
		return subgroupcode;
	}

	public void setSubgroupcode(String subgroupcode) {
		this.subgroupcode = subgroupcode;
	}

	public String getSubgroupname() {
		return subgroupname;
	}

	public void setSubgroupname(String subgroupname) {
		this.subgroupname = subgroupname;
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

	public int getLedgerslno() {
		return ledgerslno;
	}

	public void setLedgerslno(int ledgerslno) {
		this.ledgerslno = ledgerslno;
	}

	public double getSumtrailbalance() {
		return sumtrailbalance;
	}

	public void setSumtrailbalance(double sumtrailbalance) {
		this.sumtrailbalance = sumtrailbalance;
	}

	public double getPreviousyrbalance() {
		return previousyrbalance;
	}

	public void setPreviousyrbalance(double previousyrbalance) {
		this.previousyrbalance = previousyrbalance;
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

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
	}
	
	

}*/
