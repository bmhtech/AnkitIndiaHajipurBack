package com.AnkitIndia.jwtauthentication.transResponseDTO;

public class Sales_Order_Trans_InfoDTO {
	
	private String order_id;
	
	private String order_no;
	
	private String company_id;
	
	private String trans_borne_by;
	
	private String mode_of_trans;
	
	private String trans_code;
	
	private String transporter_name;
	
	private String charge_code;

	private String transport_from;
	
	private String transport_to;
	
	public String getTransport_from() {
		return transport_from;
	}

	public void setTransport_from(String transport_from) {
		this.transport_from = transport_from;
	}

	public String getTransport_to() {
		return transport_to;
	}

	public void setTransport_to(String transport_to) {
		this.transport_to = transport_to;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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

	public String getTrans_code() {
		return trans_code;
	}

	public void setTrans_code(String trans_code) {
		this.trans_code = trans_code;
	}

	public String getTransporter_name() {
		return transporter_name;
	}

	public void setTransporter_name(String transporter_name) {
		this.transporter_name = transporter_name;
	}

	public String getCharge_code() {
		return charge_code;
	}

	public void setCharge_code(String charge_code) {
		this.charge_code = charge_code;
	}

	
}
