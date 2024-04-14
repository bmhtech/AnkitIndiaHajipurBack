package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Wm_loading_wgmnt")
public class Wm_loading_wgmnt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 50)
	private String wgment_id;
	
	@Size(max = 50)
	private String company_id;
	
	@Size(max = 30)
	private String weight;
	
	private Date wgment_date;
	
	@Size(max = 100)
	private String wgment_for;
	
	@Size(max = 50)
	private String ref_doc_no;
	
	private Date ref_doc_date;
	
	@Size(max = 50)
	private String veh_no;
	
	@Size(max = 100)
	private String veh_type;
	
	@Size(max = 100)
	private String customer;
	
	private double gross_weight;
	
	@Size(max = 20)
	private String gw_unit;
	
	private Date gw_date;
	
	@Size(max = 50)
	private String gw_time;
	
	@Size(max = 100)
	private String gw_remarks;
	
	private double tare_weight;
	
	@Size(max = 20)
	private String tw_unit;
	
	private Date tw_date;
	
	@Size(max = 50)
	private String tw_time;
	
	@Size(max = 100)
	private String tw_remarks;
	
	private double net_weight;
	
	@Size(max = 20)
	private String nw_unit;
	
	private double digital_weight;
	
	private double  wgment_charge;
	
	private double wgment_rs;
	
	@Size(max = 20)
	private String fin_year;
	
	@Size(max = 50)
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Size(max = 50)
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	@Size(max = 50)
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	@Size(max = 50)
	private String deleted_by;
       
	@OneToMany(fetch = FetchType.EAGER,mappedBy="wm_loading_wgmnt",cascade = CascadeType.ALL)
	private Set<Wm_loading_wgmnt_dtls> wm_loading_wgmnt_dtls;
	
	public Wm_loading_wgmnt() {
		super();
	}

	public Wm_loading_wgmnt(Long id, @Size(max = 50) String wgment_id, @Size(max = 50) String company_id,
			@Size(max = 30) String weight, Date wgment_date, @Size(max = 100) String wgment_for,
			@Size(max = 50) String ref_doc_no, Date ref_doc_date, @Size(max = 50) String veh_no,
			@Size(max = 100) String veh_type, @Size(max = 100) String customer, double gross_weight,
			@Size(max = 20) String gw_unit, Date gw_date, @Size(max = 50) String gw_time,
			@Size(max = 100) String gw_remarks, double tare_weight, @Size(max = 20) String tw_unit, Date tw_date,
			@Size(max = 50) String tw_time, @Size(max = 100) String tw_remarks, double net_weight,
			@Size(max = 20) String nw_unit, double digital_weight, double wgment_charge, double wgment_rs,
			@Size(max = 20) String fin_year, @Size(max = 50) String modified_type, LocalDateTime inserted_on,
			@Size(max = 50) String inserted_by, LocalDateTime updated_on, @Size(max = 50) String updated_by,
			LocalDateTime deleted_on, @Size(max = 50) String deleted_by,
			Set<Wm_loading_wgmnt_dtls> wm_loading_wgmnt_dtls) {
		super();
		this.id = id;
		this.wgment_id = wgment_id;
		this.company_id = company_id;
		this.weight = weight;
		this.wgment_date = wgment_date;
		this.wgment_for = wgment_for;
		this.ref_doc_no = ref_doc_no;
		this.ref_doc_date = ref_doc_date;
		this.veh_no = veh_no;
		this.veh_type = veh_type;
		this.customer = customer;
		this.gross_weight = gross_weight;
		this.gw_unit = gw_unit;
		this.gw_date = gw_date;
		this.gw_time = gw_time;
		this.gw_remarks = gw_remarks;
		this.tare_weight = tare_weight;
		this.tw_unit = tw_unit;
		this.tw_date = tw_date;
		this.tw_time = tw_time;
		this.tw_remarks = tw_remarks;
		this.net_weight = net_weight;
		this.nw_unit = nw_unit;
		this.digital_weight = digital_weight;
		this.wgment_charge = wgment_charge;
		this.wgment_rs = wgment_rs;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.wm_loading_wgmnt_dtls = wm_loading_wgmnt_dtls;
	}

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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Date getWgment_date() {
		return wgment_date;
	}

	public void setWgment_date(Date wgment_date) {
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

	public String getVeh_no() {
		return veh_no;
	}

	public void setVeh_no(String veh_no) {
		this.veh_no = veh_no;
	}

	public String getVeh_type() {
		return veh_type;
	}

	public void setVeh_type(String veh_type) {
		this.veh_type = veh_type;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
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

	public LocalDateTime getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(LocalDateTime deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Set<Wm_loading_wgmnt_dtls> getWm_loading_wgmnt_dtls() {
		return wm_loading_wgmnt_dtls;
	}

	public void setWm_loading_wgmnt_dtls(Set<Wm_loading_wgmnt_dtls> wm_loading_wgmnt_dtls) {
		this.wm_loading_wgmnt_dtls = wm_loading_wgmnt_dtls;
	}

	@Override
	public String toString() {
		return "Wm_loading_wgmnt [id=" + id + ", wgment_id=" + wgment_id + ", company_id=" + company_id + ", weight="
				+ weight + ", wgment_date=" + wgment_date + ", wgment_for=" + wgment_for + ", ref_doc_no=" + ref_doc_no
				+ ", ref_doc_date=" + ref_doc_date + ", veh_no=" + veh_no + ", veh_type=" + veh_type + ", customer="
				+ customer + ", gross_weight=" + gross_weight + ", gw_unit=" + gw_unit + ", gw_date=" + gw_date
				+ ", gw_time=" + gw_time + ", gw_remarks=" + gw_remarks + ", tare_weight=" + tare_weight + ", tw_unit="
				+ tw_unit + ", tw_date=" + tw_date + ", tw_time=" + tw_time + ", tw_remarks=" + tw_remarks
				+ ", net_weight=" + net_weight + ", nw_unit=" + nw_unit + ", digital_weight=" + digital_weight
				+ ", wgment_charge=" + wgment_charge + ", wgment_rs=" + wgment_rs + ", fin_year=" + fin_year
				+ ", modified_type=" + modified_type + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by
				+ ", updated_on=" + updated_on + ", updated_by=" + updated_by + ", deleted_on=" + deleted_on
				+ ", deleted_by=" + deleted_by + ", wm_loading_wgmnt_dtls=" + wm_loading_wgmnt_dtls + "]";
	}

	
	
}
