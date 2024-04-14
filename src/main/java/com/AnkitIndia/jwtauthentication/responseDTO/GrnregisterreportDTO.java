package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;

public class GrnregisterreportDTO {
	
	private String grnregisterid;
	
	private String grndate;

	private String grnno;

	private String billno;

	private String adviceno;

	private String suppliername;

	private String vehicleno;
	
	private String storeserialno;
	
	private String slno;
	
	private String itemdesc;

	private String quantity;

	private String unit;

	private String rate;

	public String getGrnregisterid() {
		return grnregisterid;
	}

	public void setGrnregisterid(String grnregisterid) {
		this.grnregisterid = grnregisterid;
	}

	public String getGrndate() {
		return grndate;
	}

	public void setGrndate(String grndate) {
		this.grndate = grndate;
	}

	public String getGrnno() {
		return grnno;
	}

	public void setGrnno(String grnno) {
		this.grnno = grnno;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getAdviceno() {
		return adviceno;
	}

	public void setAdviceno(String adviceno) {
		this.adviceno = adviceno;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getStoreserialno() {
		return storeserialno;
	}

	public void setStoreserialno(String storeserialno) {
		this.storeserialno = storeserialno;
	}

	public String getSlno() {
		return slno;
	}

	public void setSlno(String slno) {
		this.slno = slno;
	}

	public String getItemdesc() {
		return itemdesc;
	}

	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public GrnregisterreportDTO(String grnregisterid, String grndate, String grnno, String billno, String adviceno,
			String suppliername, String vehicleno, String storeserialno, String slno, String itemdesc, String quantity,
			String unit, String rate) {
		super();
		this.grnregisterid = grnregisterid;
		this.grndate = grndate;
		this.grnno = grnno;
		this.billno = billno;
		this.adviceno = adviceno;
		this.suppliername = suppliername;
		this.vehicleno = vehicleno;
		this.storeserialno = storeserialno;
		this.slno = slno;
		this.itemdesc = itemdesc;
		this.quantity = quantity;
		this.unit = unit;
		this.rate = rate;
	}
	

}
