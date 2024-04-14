package com.AnkitIndia.jwtauthentication.responseDTO;

public class Wm_unload_wgmnt_Pagination_DTO {

	
	private Long id;
	
	private String wgment_id;
	
	private String wgment_no;
	
	
	private String wgment_date;
	
	private String tw_remarks;
	
	private String wgment_for;
	
	private String vehicle_no;
	
	private double gross_weight;

	private double tare_weight;
	
	private double net_weight;
	
	private double wgment_charge; 
	
	private boolean we_status;
	
	private String modified_type;
	
	private String vehicle_ref_name;

	private String vehicle_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWgment_id() {
		return wgment_id;
	}

	public void setWgment_id(String wgment_id) {
		this.wgment_id = wgment_id;
	}

	public String getWgment_no() {
		return wgment_no;
	}

	public void setWgment_no(String wgment_no) {
		this.wgment_no = wgment_no;
	}

	public String getWgment_date() {
		return wgment_date;
	}

	public void setWgment_date(String wgment_date) {
		this.wgment_date = wgment_date;
	}

	public String getTw_remarks() {
		return tw_remarks;
	}

	public void setTw_remarks(String tw_remarks) {
		this.tw_remarks = tw_remarks;
	}

	public String getWgment_for() {
		return wgment_for;
	}

	public void setWgment_for(String wgment_for) {
		this.wgment_for = wgment_for;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public double getGross_weight() {
		return gross_weight;
	}

	public void setGross_weight(double gross_weight) {
		this.gross_weight = gross_weight;
	}

	public double getTare_weight() {
		return tare_weight;
	}

	public void setTare_weight(double tare_weight) {
		this.tare_weight = tare_weight;
	}

	public double getNet_weight() {
		return net_weight;
	}

	public void setNet_weight(double net_weight) {
		this.net_weight = net_weight;
	}

	public double getWgment_charge() {
		return wgment_charge;
	}

	public void setWgment_charge(double wgment_charge) {
		this.wgment_charge = wgment_charge;
	}

	public boolean isWe_status() {
		return we_status;
	}

	public void setWe_status(boolean we_status) {
		this.we_status = we_status;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public String getVehicle_ref_name() {
		return vehicle_ref_name;
	}

	public void setVehicle_ref_name(String vehicle_ref_name) {
		this.vehicle_ref_name = vehicle_ref_name;
	}

	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public Wm_unload_wgmnt_Pagination_DTO(Long id, String wgment_id, String wgment_no, String wgment_date,
			String tw_remarks, String wgment_for, String vehicle_no, double gross_weight, double tare_weight,
			double net_weight, double wgment_charge, boolean we_status, String modified_type, String vehicle_ref_name,
			String vehicle_id) {
		super();
		this.id = id;
		this.wgment_id = wgment_id;
		this.wgment_no = wgment_no;
		this.wgment_date = wgment_date;
		this.tw_remarks = tw_remarks;
		this.wgment_for = wgment_for;
		this.vehicle_no = vehicle_no;
		this.gross_weight = gross_weight;
		this.tare_weight = tare_weight;
		this.net_weight = net_weight;
		this.wgment_charge = wgment_charge;
		this.we_status = we_status;
		this.modified_type = modified_type;
		this.vehicle_ref_name = vehicle_ref_name;
		this.vehicle_id = vehicle_id;
	}
	
	
	
	
	
}
