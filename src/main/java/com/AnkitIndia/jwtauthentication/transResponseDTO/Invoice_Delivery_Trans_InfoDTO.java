package com.AnkitIndia.jwtauthentication.transResponseDTO;

public class Invoice_Delivery_Trans_InfoDTO {
	
	private String transname;
	
	private String vehicletype; 
	
	private String vehicleno;
	
	private String ewaybillno;

	public String getTransname() {
		return transname;
	}

	public void setTransname(String transname) {
		this.transname = transname;
	}

	public String getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getEwaybillno() {
		return ewaybillno;
	}

	public void setEwaybillno(String ewaybillno) {
		this.ewaybillno = ewaybillno;
	}

	public Invoice_Delivery_Trans_InfoDTO() {
		super();
	}

	public Invoice_Delivery_Trans_InfoDTO(String transname, String vehicletype, String vehicleno, String ewaybillno) {
		super();
		this.transname = transname;
		this.vehicletype = vehicletype;
		this.vehicleno = vehicleno;
		this.ewaybillno = ewaybillno;
	}
	
	
}
