package com.AnkitIndia.jwtauthentication.responseDTO;

public class Control_account_detailsDTO {

	private int slno;
	
	private String entrydate;
	
	private String ledgerid;
	
	private String ledgername;
	
	private double amount;
	
	private double credit;
	
	private double debit;
	
	private String voucherno;
	
	public Control_account_detailsDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Control_account_detailsDTO(int slno, String entrydate, String ledgerid, String ledgername, double amount,
			double credit, double debit, String voucherno) {
		super();
		this.slno = slno;
		this.entrydate = entrydate;
		this.ledgerid = ledgerid;
		this.ledgername = ledgername;
		this.amount = amount;
		this.credit = credit;
		this.debit = debit;
		this.voucherno = voucherno;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
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

	public String getLedgername() {
		return ledgername;
	}

	public void setLedgername(String ledgername) {
		this.ledgername = ledgername;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public String getVoucherno() {
		return voucherno;
	}

	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}
	
}
