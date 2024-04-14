package com.AnkitIndia.jwtauthentication.responseDTO;

public class ChallanpertransportreportDTO {

	private String challan_date;
	
	private String challan_no;
	
	private String partyname;
	
	private String squantity;
	
	private String quantity;
	
	private String own_gross;
	
	private String own_tare;
	
	private String own_net;
	
	private String  trans_borne_by;
	
	private String  mode_of_trans;
	
	private String  transname;
	
	private String vehicle_no;
	
	private String freight_amt;
	
	private String adv_paid;
	
	private String  kg;

	public String getChallan_date() {
		return challan_date;
	}

	public void setChallan_date(String challan_date) {
		this.challan_date = challan_date;
	}

	public String getChallan_no() {
		return challan_no;
	}

	public void setChallan_no(String challan_no) {
		this.challan_no = challan_no;
	}

	public String getPartyname() {
		return partyname;
	}

	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}

	public String getSquantity() {
		return squantity;
	}

	public void setSquantity(String squantity) {
		this.squantity = squantity;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getOwn_gross() {
		return own_gross;
	}

	public void setOwn_gross(String own_gross) {
		this.own_gross = own_gross;
	}

	public String getOwn_tare() {
		return own_tare;
	}

	public void setOwn_tare(String own_tare) {
		this.own_tare = own_tare;
	}

	public String getOwn_net() {
		return own_net;
	}

	public void setOwn_net(String own_net) {
		this.own_net = own_net;
	}

	public String getTrans_borne_by() {
		return trans_borne_by;
	}

	public void setTrans_borne_by(String trans_borne_by) {
		this.trans_borne_by = trans_borne_by;
	}

	public String getMode_of_trans() {
		return mode_of_trans;
	}

	public void setMode_of_trans(String mode_of_trans) {
		this.mode_of_trans = mode_of_trans;
	}

	public String getTransname() {
		return transname;
	}

	public void setTransname(String transname) {
		this.transname = transname;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public String getFreight_amt() {
		return freight_amt;
	}

	public void setFreight_amt(String freight_amt) {
		this.freight_amt = freight_amt;
	}

	public String getAdv_paid() {
		return adv_paid;
	}

	public void setAdv_paid(String adv_paid) {
		this.adv_paid = adv_paid;
	}

	public String getKg() {
		return kg;
	}

	public void setKg(String kg) {
		this.kg = kg;
	}

	public ChallanpertransportreportDTO(String challan_date, String challan_no, String partyname, String squantity,
			String quantity, String own_gross, String own_tare, String own_net, String trans_borne_by,
			String mode_of_trans, String transname, String vehicle_no, String freight_amt, String adv_paid, String kg) {
		super();
		this.challan_date = challan_date;
		this.challan_no = challan_no;
		this.partyname = partyname;
		this.squantity = squantity;
		this.quantity = quantity;
		this.own_gross = own_gross;
		this.own_tare = own_tare;
		this.own_net = own_net;
		this.trans_borne_by = trans_borne_by;
		this.mode_of_trans = mode_of_trans;
		this.transname = transname;
		this.vehicle_no = vehicle_no;
		this.freight_amt = freight_amt;
		this.adv_paid = adv_paid;
		this.kg = kg;
	}

	
	
}
