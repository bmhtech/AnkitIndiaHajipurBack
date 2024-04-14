package com.AnkitIndia.jwtauthentication.responseDTO;

public class LedgermasterDTO {
	
	private String ledgerid;
	
	private String ledgername;
	
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

	public LedgermasterDTO() {
		super();
	}

	public LedgermasterDTO(String ledgerid, String ledgername) {
		super();
		this.ledgerid = ledgerid;
		this.ledgername = ledgername;
	}
	
	
}
