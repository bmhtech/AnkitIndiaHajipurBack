package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;

public class Wm_unload_wgmntDTO {
	
	private Long id;
	
	private String wgment_id;
	
	private String wgment_no;
	
	private String company_id;
	
	private String weight1;
	
	private String weight2;
	
	private boolean we_status;
	
	private String wgment_date;
	
	private String wgment_for;
	
	private String ref_doc_no;
	
	private Date ref_doc_date;
	
	private String vehicle_id;
	
	private String vehicle_no;
	
	private String veh_type;
	
	private double gross_weight;
	
	private String gw_unit;
	
	private Date gw_date;
	
	private String gw_time;
	
	private String gw_remarks;
	
	private double tare_weight;  
	
	private String tw_unit;

	private Date tw_date;
	
	private String tw_time;
	
	private String tw_remarks;
	
	private double net_weight;  
	
	private String nw_unit;
	
	private double digital_weight;  
	
	private double digital_weight1; 
	
	private String remarks;
	
	private double wgment_charge;  
	
	private double wgment_rs;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private String vehicle_ref_name;

	private String weighment_type;
	
	private double tare_weight_bulker;
	
	private double net_weight_bulker;
	
	private boolean terminate;
 
	private String terminated_by;
	 
	private String ter_oth_wgmnt_no;
	
	private String terminate_remarks;
	
	private double shifting_price;
	
	private String weight_bridge_location;
	
	private String outside_weighment;
	
	private String outside_weighmentno;
	
	private double outside_netwt;
	
	private String outer_date;
	
	public String getOuter_date() {
		return outer_date;
	}

	public void setOuter_date(String outer_date) {
		this.outer_date = outer_date;
	}

	public String getOutside_weighment() {
		return outside_weighment;
	}

	public void setOutside_weighment(String outside_weighment) {
		this.outside_weighment = outside_weighment;
	}

	public String getOutside_weighmentno() {
		return outside_weighmentno;
	}

	public void setOutside_weighmentno(String outside_weighmentno) {
		this.outside_weighmentno = outside_weighmentno;
	}

	public double getOutside_netwt() {
		return outside_netwt;
	}

	public void setOutside_netwt(double outside_netwt) {
		this.outside_netwt = outside_netwt;
	}

	public String getWeight_bridge_location() {
		return weight_bridge_location;
	}

	public void setWeight_bridge_location(String weight_bridge_location) {
		this.weight_bridge_location = weight_bridge_location;
	}

	public double getShifting_price() {
		return shifting_price;
	}

	public void setShifting_price(double shifting_price) {
		this.shifting_price = shifting_price;
	}

	public String getTer_oth_wgmnt_no() {
		return ter_oth_wgmnt_no;
	}

	public void setTer_oth_wgmnt_no(String ter_oth_wgmnt_no) {
		this.ter_oth_wgmnt_no = ter_oth_wgmnt_no;
	}

	public String getTerminate_remarks() {
		return terminate_remarks;
	}

	public void setTerminate_remarks(String terminate_remarks) {
		this.terminate_remarks = terminate_remarks;
	}

	public String getTerminated_by() {
		return terminated_by;
	}

	public void setTerminated_by(String terminated_by) {
		this.terminated_by = terminated_by;
	}

	public boolean isTerminate() {
		return terminate;
	}

	public void setTerminate(boolean terminate) {
		this.terminate = terminate;
	}

	public double getTare_weight_bulker() {
		return tare_weight_bulker;
	}

	public void setTare_weight_bulker(double tare_weight_bulker) {
		this.tare_weight_bulker = tare_weight_bulker;
	}

	public double getNet_weight_bulker() {
		return net_weight_bulker;
	}

	public void setNet_weight_bulker(double net_weight_bulker) {
		this.net_weight_bulker = net_weight_bulker;
	}

	public String getWeighment_type() {
		return weighment_type;
	}

	public void setWeighment_type(String weighment_type) {
		this.weighment_type = weighment_type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicle_ref_name() {
		return vehicle_ref_name;
	}

	public void setVehicle_ref_name(String vehicle_ref_name) {
		this.vehicle_ref_name = vehicle_ref_name;
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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getWeight1() {
		return weight1;
	}

	public void setWeight1(String weight1) {
		this.weight1 = weight1;
	}

	public String getWeight2() {
		return weight2;
	}

	public void setWeight2(String weight2) {
		this.weight2 = weight2;
	}

	public boolean isWe_status() {
		return we_status;
	}

	public void setWe_status(boolean we_status) {
		this.we_status = we_status;
	}
	
	public String getWgment_date() {
		return wgment_date;
	}

	public void setWgment_date(String wgment_date) {
		this.wgment_date = wgment_date;
	}

	public String getWgment_for() {
		return wgment_for;
	}

	public void setWgment_for(String wgment_for) {
		this.wgment_for = wgment_for;
	}

	public String getRef_doc_no() {
		return ref_doc_no;
	}

	public void setRef_doc_no(String ref_doc_no) {
		this.ref_doc_no = ref_doc_no;
	}

	public Date getRef_doc_date() {
		return ref_doc_date;
	}

	public void setRef_doc_date(Date ref_doc_date) {
		this.ref_doc_date = ref_doc_date;
	}
	
	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public String getVeh_type() {
		return veh_type;
	}

	public void setVeh_type(String veh_type) {
		this.veh_type = veh_type;
	}

	public double getGross_weight() {
		return gross_weight;
	}

	public void setGross_weight(double gross_weight) {
		this.gross_weight = gross_weight;
	}

	public String getGw_unit() {
		return gw_unit;
	}

	public void setGw_unit(String gw_unit) {
		this.gw_unit = gw_unit;
	}

	public Date getGw_date() {
		return gw_date;
	}

	public void setGw_date(Date gw_date) {
		this.gw_date = gw_date;
	}

	public String getGw_time() {
		return gw_time;
	}

	public void setGw_time(String gw_time) {
		this.gw_time = gw_time;
	}

	public String getGw_remarks() {
		return gw_remarks;
	}

	public void setGw_remarks(String gw_remarks) {
		this.gw_remarks = gw_remarks;
	}

	public double getTare_weight() {
		return tare_weight;
	}

	public void setTare_weight(double tare_weight) {
		this.tare_weight = tare_weight;
	}

	public String getTw_unit() {
		return tw_unit;
	}

	public void setTw_unit(String tw_unit) {
		this.tw_unit = tw_unit;
	}

	public Date getTw_date() {
		return tw_date;
	}

	public void setTw_date(Date tw_date) {
		this.tw_date = tw_date;
	}

	public String getTw_time() {
		return tw_time;
	}

	public void setTw_time(String tw_time) {
		this.tw_time = tw_time;
	}

	public String getTw_remarks() {
		return tw_remarks;
	}

	public void setTw_remarks(String tw_remarks) {
		this.tw_remarks = tw_remarks;
	}

	public double getNet_weight() {
		return net_weight;
	}

	public void setNet_weight(double net_weight) {
		this.net_weight = net_weight;
	}

	public String getNw_unit() {
		return nw_unit;
	}

	public void setNw_unit(String nw_unit) {
		this.nw_unit = nw_unit;
	}

	public double getDigital_weight() {
		return digital_weight;
	}

	public void setDigital_weight(double digital_weight) {
		this.digital_weight = digital_weight;
	}

	public double getWgment_charge() {
		return wgment_charge;
	}

	public void setWgment_charge(double wgment_charge) {
		this.wgment_charge = wgment_charge;
	}

	public double getWgment_rs() {
		return wgment_rs;
	}

	public void setWgment_rs(double wgment_rs) {
		this.wgment_rs = wgment_rs;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}

	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public double getDigital_weight1() {
		return digital_weight1;
	}

	public void setDigital_weight1(double digital_weight1) {
		this.digital_weight1 = digital_weight1;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
