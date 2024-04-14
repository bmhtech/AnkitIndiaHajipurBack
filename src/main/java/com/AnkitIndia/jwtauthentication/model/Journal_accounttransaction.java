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

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="Journal_accounttransaction", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"voucherno"})
})
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "Journal_accounttransaction.saveJournalAccount", 
	  procedureName = "saveJournalAccount", parameters = {
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceno", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "referencedate", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "drawnon", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "modified_type", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "narration_dtls", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucher", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrps", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "groupcode", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "invoiceid", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "invoiceno", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	@NamedStoredProcedureQuery(name = "Journal_accounttransaction.callJournalAccInsertPlus",
	  procedureName = "callJournalAccInsertPlus", parameters = {
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucherno", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrps", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "groupcode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "gstcal", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "percentage", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "invoiceid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "invoiceno", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	@NamedStoredProcedureQuery(name = "Journal_accounttransaction.saveOutstandingledger",
	  procedureName = "saveOutstandingledger", parameters = {
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceno", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "invoice_no", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "referencedate", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "billamount", type = double.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "duesamount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "vouchertype", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "modified_type", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "party", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "partyname", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "unitname", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	@NamedStoredProcedureQuery(name = "Journal_accounttransaction.updateJournalAccount", 
	  procedureName = "updateJournalAccount", parameters = {
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceno", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "referencedate", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "drawnon", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "modified_type", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "narration_dtls", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucher", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrps", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "groupcode", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	@NamedStoredProcedureQuery(name = "Journal_accounttransaction.updateJournalAccInsertPlus", 
	  procedureName = "updateJournalAccInsertPlus", parameters = {
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucherno", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "modified_type", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrps", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "groupcode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "gstcal", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "percentage", type = double.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	@NamedStoredProcedureQuery(name = "Journal_accounttransaction.updateOutstandingledger", 
	  procedureName = "updateOutstandingledger", parameters = {
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceno", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "referencedate", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "billamount", type = double.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "duesamount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "vouchertype", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "modified_type", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "party", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "partyname", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "unitname", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)})
})
public class Journal_accounttransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String receipt_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String branchcode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String voucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String entrydate;
	
	private int serial_no;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String ledgerid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String referenceno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String referencedate;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double totalamount;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String entrybranchcode; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String uniquevoucherno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String drawnon;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String clearedon;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String drafton;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String narration;
	
	@Column(columnDefinition="varchar(500) default '0'")
	private String narration_dtls;
	
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
	
	//@OneToMany(fetch = FetchType.EAGER,mappedBy="rAccounttransaction",cascade = CascadeType.ALL)
	//private Set<Account_details> accountdetails;
	
	public Journal_accounttransaction() {
		// TODO Auto-generated constructor stub
	}

	public Journal_accounttransaction(long id, String receipt_id, String branchcode, String voucherno,
			String entrydate, String ledgerid, String referenceno, String referencedate, double totalamount,
			String entrybranchcode, String uniquevoucherno, String drawnon, String clearedon, String drafton,
			String modified_type, LocalDateTime inserted_on, String inserted_by, LocalDateTime modified_on,
			String modified_by, String fin_year, String username) {
		super();
		this.id = id;
		this.receipt_id = receipt_id;
		this.branchcode = branchcode;
		this.voucherno = voucherno;
		this.entrydate = entrydate;
		this.ledgerid = ledgerid;
		this.referenceno = referenceno;
		this.referencedate = referencedate;
		this.totalamount = totalamount;
		this.entrybranchcode = entrybranchcode;
		this.uniquevoucherno = uniquevoucherno;
		this.drawnon = drawnon;
		this.clearedon = clearedon;
		this.drafton = drafton;
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

	public String getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}
	
	public int getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(int serial_no) {
		this.serial_no = serial_no;
	}

	public String getLedgerid() {
		return ledgerid;
	}

	public void setLedgerid(String ledgerid) {
		this.ledgerid = ledgerid;
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

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
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

	public String getDrawnon() {
		return drawnon;
	}

	public void setDrawnon(String drawnon) {
		this.drawnon = drawnon;
	}

	public String getClearedon() {
		return clearedon;
	}

	public void setClearedon(String clearedon) {
		this.clearedon = clearedon;
	}

	public String getDrafton() {
		return drafton;
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

	public void setDrafton(String drafton) {
		this.drafton = drafton;
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
		return "Journal_accounttransaction [id=" + id + ", receipt_id=" + receipt_id + ", branchcode=" + branchcode
				+ ", voucherno=" + voucherno + ", entrydate=" + entrydate + ", serial_no=" + serial_no + ", ledgerid="
				+ ledgerid + ", referenceno=" + referenceno + ", referencedate=" + referencedate + ", totalamount="
				+ totalamount + ", entrybranchcode=" + entrybranchcode + ", uniquevoucherno=" + uniquevoucherno
				+ ", drawnon=" + drawnon + ", clearedon=" + clearedon + ", drafton=" + drafton + ", narration="
				+ narration + ", narration_dtls=" + narration_dtls + ", modified_type=" + modified_type
				+ ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by + ", modified_on=" + modified_on
				+ ", modified_by=" + modified_by + ", fin_year=" + fin_year + ", username=" + username + "]";
	}
	
}
