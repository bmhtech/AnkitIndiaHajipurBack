package com.AnkitIndia.jwtauthentication.responseDTO;

public class RequisitionListDTO {


	private Long id;
	
	private String requisitionno;
	
	private String business_unitname;
	
	private String requestedbyname;
    
	private String shop_floorname;
	
	private String requesteddate;
   
	private String approvedbyname;

	private boolean approvestatus;
	
	private String reject;
	
	private String response;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequisitionno() {
		return requisitionno;
	}

	public void setRequisitionno(String requisitionno) {
		this.requisitionno = requisitionno;
	}

	public String getBusiness_unitname() {
		return business_unitname;
	}

	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}

	public String getRequestedbyname() {
		return requestedbyname;
	}

	public void setRequestedbyname(String requestedbyname) {
		this.requestedbyname = requestedbyname;
	}

	public String getShop_floorname() {
		return shop_floorname;
	}

	public void setShop_floorname(String shop_floorname) {
		this.shop_floorname = shop_floorname;
	}

	public String getRequesteddate() {
		return requesteddate;
	}

	public void setRequesteddate(String requesteddate) {
		this.requesteddate = requesteddate;
	}

	public String getApprovedbyname() {
		return approvedbyname;
	}

	public void setApprovedbyname(String approvedbyname) {
		this.approvedbyname = approvedbyname;
	}

	public boolean isApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(boolean approvestatus) {
		this.approvestatus = approvestatus;
	}

	public String getReject() {
		return reject;
	}

	public void setReject(String reject) {
		this.reject = reject;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public RequisitionListDTO(Long id, String requisitionno, String business_unitname, String requestedbyname,
			String shop_floorname, String requesteddate, String approvedbyname, boolean approvestatus, String reject,
			String response) {
		super();
		this.id = id;
		this.requisitionno = requisitionno;
		this.business_unitname = business_unitname;
		this.requestedbyname = requestedbyname;
		this.shop_floorname = shop_floorname;
		this.requesteddate = requesteddate;
		this.approvedbyname = approvedbyname;
		this.approvestatus = approvestatus;
		this.reject = reject;
		this.response = response;
	}

	
	
	
}
