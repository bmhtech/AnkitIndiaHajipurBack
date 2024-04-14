package com.AnkitIndia.jwtauthentication.responseDTO;

public class ReceiptAccDetails {

	private String dlegderid;
	
	private double damount;
	
	public ReceiptAccDetails() {
		super();
	}

	public ReceiptAccDetails(String dlegderid, double damount) {
		super();
		this.dlegderid = dlegderid;
		this.damount = damount;
	}

	public String getDlegderid() {
		return dlegderid;
	}

	public void setDlegderid(String dlegderid) {
		this.dlegderid = dlegderid;
	}

	public double getDamount() {
		return damount;
	}

	public void setDamount(double damount) {
		this.damount = damount;
	}
	
}
