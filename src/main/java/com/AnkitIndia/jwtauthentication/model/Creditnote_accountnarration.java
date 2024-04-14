package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Creditnote_accountnarration")
public class Creditnote_accountnarration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String receipt_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String branchcode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String voucherno;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String narration;
	
	@Column(columnDefinition="varchar(500) default '0'")
	private String narration_dtls;
	
	@Column(columnDefinition="varchar(500) default '0'")
	private String entrybranchcode;
	
	@Column(columnDefinition="varchar(500) default '0'")
	private String uniquevoucherno;
	
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
	
	public Creditnote_accountnarration() {
		// TODO Auto-generated constructor stub
	}

	public Creditnote_accountnarration(long id, String receipt_id, String branchcode, String voucherno,
			String narration, String narration_dtls, String entrybranchcode, String uniquevoucherno,
			String modified_type, LocalDateTime inserted_on, String inserted_by, LocalDateTime modified_on,
			String modified_by, String fin_year, String username) {
		super();
		this.id = id;
		this.receipt_id = receipt_id;
		this.branchcode = branchcode;
		this.voucherno = voucherno;
		this.narration = narration;
		this.narration_dtls = narration_dtls;
		this.entrybranchcode = entrybranchcode;
		this.uniquevoucherno = uniquevoucherno;
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

	public String getReceipt_id() {
		return receipt_id;
	}

	public void setReceipt_id(String receipt_id) {
		this.receipt_id = receipt_id;
	}

	public String getBranchcode() {
		return branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	public String getVoucherno() {
		return voucherno;
	}

	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getNarration_dtls() {
		return narration_dtls;
	}

	public void setNarration_dtls(String narration_dtls) {
		this.narration_dtls = narration_dtls;
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
		return "Creditnote_accountnarration [id=" + id + ", receipt_id=" + receipt_id + ", branchcode=" + branchcode
				+ ", voucherno=" + voucherno + ", narration=" + narration + ", narration_dtls=" + narration_dtls
				+ ", entrybranchcode=" + entrybranchcode + ", uniquevoucherno=" + uniquevoucherno + ", modified_type="
				+ modified_type + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by + ", modified_on="
				+ modified_on + ", modified_by=" + modified_by + ", fin_year=" + fin_year + ", username=" + username
				+ "]";
	}
	
}
