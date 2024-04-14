package com.AnkitIndia.jwtauthentication.transResponseDTO;

import javax.validation.constraints.Size;

public class Invoice_Stk_Transfer_Challan_Trans_InfoDTO {
	
	private String transname;
	
	private String vehicletype; 
	
	private String vehicleno;
	
	private String ewaybillno;
	
	private String vehicle_id;

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

	public Invoice_Stk_Transfer_Challan_Trans_InfoDTO() {
		super();
	}
	
	

	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public Invoice_Stk_Transfer_Challan_Trans_InfoDTO(String transname, String vehicletype, String vehicleno,
			String ewaybillno) {
		super();
		this.transname = transname;
		this.vehicletype = vehicletype;
		this.vehicleno = vehicleno;
		this.ewaybillno = ewaybillno;
	}
	
	
}
