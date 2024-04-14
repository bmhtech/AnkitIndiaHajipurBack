package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.sql.Date;
import java.time.LocalDateTime;


public class Delivery_challan_weight_dtlDTO {
	
	private Long id;
	
	private String delivery_cid;
	
	private String company_id;
	
	private String own_uom;
    
	private double own_gross;
	
	private double own_tare;
	
	private double own_net; 
    
	private String own_permit_no;
    
    private Date own_date;
    
	private String own_slip_no;
    
	private String party_uom;
    
	private double party_gross;
    
	private double party_tare;
    
	private double party_net;
    
    private Date party_date;
    
	private String party_slip_no;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDelivery_cid() {
		return delivery_cid;
	}

	public void setDelivery_cid(String delivery_cid) {
		this.delivery_cid = delivery_cid;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getOwn_uom() {
		return own_uom;
	}

	public void setOwn_uom(String own_uom) {
		this.own_uom = own_uom;
	}

	public double getOwn_gross() {
		return own_gross;
	}

	public void setOwn_gross(double own_gross) {
		this.own_gross = own_gross;
	}

	public double getOwn_tare() {
		return own_tare;
	}

	public void setOwn_tare(double own_tare) {
		this.own_tare = own_tare;
	}

	public double getOwn_net() {
		return own_net;
	}

	public void setOwn_net(double own_net) {
		this.own_net = own_net;
	}

	public String getOwn_permit_no() {
		return own_permit_no;
	}

	public void setOwn_permit_no(String own_permit_no) {
		this.own_permit_no = own_permit_no;
	}

	public Date getOwn_date() {
		return own_date;
	}

	public void setOwn_date(Date own_date) {
		this.own_date = own_date;
	}

	public String getOwn_slip_no() {
		return own_slip_no;
	}

	public void setOwn_slip_no(String own_slip_no) {
		this.own_slip_no = own_slip_no;
	}

	public String getParty_uom() {
		return party_uom;
	}

	public void setParty_uom(String party_uom) {
		this.party_uom = party_uom;
	}

	public double getParty_gross() {
		return party_gross;
	}

	public void setParty_gross(double party_gross) {
		this.party_gross = party_gross;
	}

	public double getParty_tare() {
		return party_tare;
	}

	public void setParty_tare(double party_tare) {
		this.party_tare = party_tare;
	}

	public double getParty_net() {
		return party_net;
	}

	public void setParty_net(double party_net) {
		this.party_net = party_net;
	}

	public Date getParty_date() {
		return party_date;
	}

	public void setParty_date(Date party_date) {
		this.party_date = party_date;
	}

	public String getParty_slip_no() {
		return party_slip_no;
	}

	public void setParty_slip_no(String party_slip_no) {
		this.party_slip_no = party_slip_no;
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
	
	

}
