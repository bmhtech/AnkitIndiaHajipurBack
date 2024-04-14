package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

public class Receipt_accounttransactionDTO {

	private long id;
	
	private String receipt_id;
	
	private String branchcode;
	
	private String voucherno;
	
	private String entrydate;
	
	private int serial_no;
	
	private String ledgerid;
	
	private String ledgername;
	
	private String referenceno;
	
	private String referencedate;
	
	private double totalamount;
	
	private String entrybranchcode; 
	
	private String uniquevoucherno;
	
	private String drawnon;
	
	private String clearedon;
	
	private String drafton;
	
	private String narration;
	
	private String narration_dtls;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	public String getLedgername() {
		return ledgername;
	}

	public void setLedgername(String ledgername) {
		this.ledgername = ledgername;
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

	public void setDrafton(String drafton) {
		this.drafton = drafton;
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

	
}
