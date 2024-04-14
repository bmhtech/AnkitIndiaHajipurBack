package com.AnkitIndia.jwtauthentication.transResponseDTO;

public class Sales_payment_detailsDTO {

	private int slno;
	
	private String vouchernumber;
	
	private String entrydate;
	
	private String particulars;
	
	private String particularsname;
	
	private double amount;
	
	private double credit;
	
	private double debit;
	
	private String transaction_type;

	public Sales_payment_detailsDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Sales_payment_detailsDTO(int slno, String vouchernumber, String entrydate, String particulars,
			String particularsname, double amount, double credit, double debit) {
		super();
		this.slno = slno;
		this.vouchernumber = vouchernumber;
		this.entrydate = entrydate;
		this.particulars = particulars;
		this.particularsname = particularsname;
		this.amount = amount;
		this.credit = credit;
		this.debit = debit;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public String getVouchernumber() {
		return vouchernumber;
	}

	public void setVouchernumber(String vouchernumber) {
		this.vouchernumber = vouchernumber;
	}

	public String getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getParticularsname() {
		return particularsname;
	}

	public void setParticularsname(String particularsname) {
		this.particularsname = particularsname;
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

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	
}
