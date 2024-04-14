package com.AnkitIndia.jwtauthentication.responseDTO;
public class WheatstackcardreportDTO {

    private long id;
	private String wheatstackid;
	private String business_unitname;
	private String godownname;
	private String stackno;
	private String closed;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getWheatstackid() {
		return wheatstackid;
	}
	public void setWheatstackid(String wheatstackid) {
		this.wheatstackid = wheatstackid;
	}
	public String getBusiness_unitname() {
		return business_unitname;
	}
	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}
	public String getGodownname() {
		return godownname;
	}
	public void setGodownname(String godownname) {
		this.godownname = godownname;
	}
	public String getStackno() {
		return stackno;
	}
	public void setStackno(String stackno) {
		this.stackno = stackno;
	}
	public String getClosed() {
		return closed;
	}
	public void setClosed(String closed) {
		this.closed = closed;
	}
	public WheatstackcardreportDTO(long id, String wheatstackid, String business_unitname, String godownname,
			String stackno, String closed) {
		super();
		this.id = id;
		this.wheatstackid = wheatstackid;
		this.business_unitname = business_unitname;
		this.godownname = godownname;
		this.stackno = stackno;
		this.closed = closed;
	}
	
	
	
	
	
}