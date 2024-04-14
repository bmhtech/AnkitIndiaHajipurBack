package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name="Creditnote_accounttransaction")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "Creditnote_accounttransaction.saveCreditnoteAccount", 
	  procedureName = "saveCreditnoteAccount", parameters = {
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceno", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "referencedate", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "drawnon", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "modified_type", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "narration_dtls", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucher", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrps", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "groupcode", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "creditnoteid", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "creditnoteno", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	@NamedStoredProcedureQuery(name = "Creditnote_accounttransaction.callCreditnoteAccInsertPlus", 
	  procedureName = "callCreditnoteAccInsertPlus", parameters = {
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucher", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrps", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "groupcode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "gstcal", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "percentage", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "creditnoteid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "creditnoteno", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	@NamedStoredProcedureQuery(name = "Creditnote_accounttransaction.saveOutstandingledger",
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
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)})
})
public class Creditnote_accounttransaction {

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
	
	@Column(columnDefinition="Double(12,2) default 0.00")
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
	
	public Creditnote_accounttransaction() {
		// TODO Auto-generated constructor stub
	}

	public Creditnote_accounttransaction(long id, String receipt_id, String branchcode, String voucherno,
			String entrydate, int serial_no, String ledgerid, String referenceno, String referencedate,
			double totalamount, String entrybranchcode, String uniquevoucherno, String drawnon, String clearedon,
			String drafton, String narration, String narration_dtls, String modified_type, LocalDateTime inserted_on,
			String inserted_by, LocalDateTime modified_on, String modified_by, String fin_year, String username) {
		super();
		this.id = id;
		this.receipt_id = receipt_id;
		this.branchcode = branchcode;
		this.voucherno = voucherno;
		this.entrydate = entrydate;
		this.serial_no = serial_no;
		this.ledgerid = ledgerid;
		this.referenceno = referenceno;
		this.referencedate = referencedate;
		this.totalamount = totalamount;
		this.entrybranchcode = entrybranchcode;
		this.uniquevoucherno = uniquevoucherno;
		this.drawnon = drawnon;
		this.clearedon = clearedon;
		this.drafton = drafton;
		this.narration = narration;
		this.narration_dtls = narration_dtls;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.fin_year = fin_year;
		this.username = username;
	}

	@Override
	public String toString() {
		return "Creditnote_accounttransaction [id=" + id + ", receipt_id=" + receipt_id + ", branchcode=" + branchcode
				+ ", voucherno=" + voucherno + ", entrydate=" + entrydate + ", serial_no=" + serial_no + ", ledgerid="
				+ ledgerid + ", referenceno=" + referenceno + ", referencedate=" + referencedate + ", totalamount="
				+ totalamount + ", entrybranchcode=" + entrybranchcode + ", uniquevoucherno=" + uniquevoucherno
				+ ", drawnon=" + drawnon + ", clearedon=" + clearedon + ", drafton=" + drafton + ", narration="
				+ narration + ", narration_dtls=" + narration_dtls + ", modified_type=" + modified_type
				+ ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by + ", modified_on=" + modified_on
				+ ", modified_by=" + modified_by + ", fin_year=" + fin_year + ", username=" + username + "]";
	}
	
}
