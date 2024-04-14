package com.AnkitIndia.jwtauthentication.responseDTO;


public class BinreportdetailspopupDTO {

	
	private String bingroupid;
	
	
	private String bingroupname;

	private String dip;
	
	private String mt;

	public String getBingroupid() {
		return bingroupid;
	}

	public void setBingroupid(String bingroupid) {
		this.bingroupid = bingroupid;
	}

	public String getBingroupname() {
		return bingroupname;
	}

	public void setBingroupname(String bingroupname) {
		this.bingroupname = bingroupname;
	}

	public String getDip() {
		return dip;
	}

	public void setDip(String dip) {
		this.dip = dip;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

	public BinreportdetailspopupDTO(String bingroupid, String bingroupname, String dip, String mt) {
		super();
		this.bingroupid = bingroupid;
		this.bingroupname = bingroupname;
		this.dip = dip;
		this.mt = mt;
	}

	
	
	
}
