package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

public class Tag_advice_with_poDTO {
	
	private Long id;
	
	private String adv_po_tag_id;
	
	private String adv_po_tag_no;
	
	private String po_number;
	
    private String pur_orderno;
	
	private String advice_no;
	
	private String item_type;
	
	private String veh_no; 
	
	private String item_subtype;
	
    private String item_subtype_name;
	
	private String busi_partner;
	
    private String busi_partnername;
	
    private boolean we_req;
    
    private boolean we_chg_app;
    
	private String supp_ref_doc;
    
	private String supp_ref_docno;
    
    private String ula_date;  
	
	private boolean grn_req;  
	
	private String business_unit; 
	
    private String business_unitname;
	
	private String vehicle_id;  
	
	private int total_qty;
	
	private String uom;
	
	private boolean return_type;  
	
	private String return_remarks;  
	
	private String remarks; 
	
	private String transporter_name;
	
	private String transporter_code; 
	
	private String advice_id;
	
	private String weighment_status;
	
	private String company_id;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdv_po_tag_id() {
		return adv_po_tag_id;
	}

	public void setAdv_po_tag_id(String adv_po_tag_id) {
		this.adv_po_tag_id = adv_po_tag_id;
	}

	public String getAdv_po_tag_no() {
		return adv_po_tag_no;
	}

	public void setAdv_po_tag_no(String adv_po_tag_no) {
		this.adv_po_tag_no = adv_po_tag_no;
	}

	public String getPo_number() {
		return po_number;
	}

	public void setPo_number(String po_number) {
		this.po_number = po_number;
	}

	public String getPur_orderno() {
		return pur_orderno;
	}

	public void setPur_orderno(String pur_orderno) {
		this.pur_orderno = pur_orderno;
	}

	public String getAdvice_no() {
		return advice_no;
	}

	public void setAdvice_no(String advice_no) {
		this.advice_no = advice_no;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getVeh_no() {
		return veh_no;
	}

	public void setVeh_no(String veh_no) {
		this.veh_no = veh_no;
	}

	public String getItem_subtype() {
		return item_subtype;
	}

	public void setItem_subtype(String item_subtype) {
		this.item_subtype = item_subtype;
	}

	public String getItem_subtype_name() {
		return item_subtype_name;
	}

	public void setItem_subtype_name(String item_subtype_name) {
		this.item_subtype_name = item_subtype_name;
	}

	public String getBusi_partner() {
		return busi_partner;
	}

	public void setBusi_partner(String busi_partner) {
		this.busi_partner = busi_partner;
	}

	public String getBusi_partnername() {
		return busi_partnername;
	}

	public void setBusi_partnername(String busi_partnername) {
		this.busi_partnername = busi_partnername;
	}

	public boolean isWe_req() {
		return we_req;
	}

	public void setWe_req(boolean we_req) {
		this.we_req = we_req;
	}

	public boolean isWe_chg_app() {
		return we_chg_app;
	}

	public void setWe_chg_app(boolean we_chg_app) {
		this.we_chg_app = we_chg_app;
	}

	public String getSupp_ref_doc() {
		return supp_ref_doc;
	}

	public void setSupp_ref_doc(String supp_ref_doc) {
		this.supp_ref_doc = supp_ref_doc;
	}

	public String getSupp_ref_docno() {
		return supp_ref_docno;
	}

	public void setSupp_ref_docno(String supp_ref_docno) {
		this.supp_ref_docno = supp_ref_docno;
	}

	public String getUla_date() {
		return ula_date;
	}

	public void setUla_date(String ula_date) {
		this.ula_date = ula_date;
	}

	public boolean isGrn_req() {
		return grn_req;
	}

	public void setGrn_req(boolean grn_req) {
		this.grn_req = grn_req;
	}

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getBusiness_unitname() {
		return business_unitname;
	}

	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}

	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public int getTotal_qty() {
		return total_qty;
	}

	public void setTotal_qty(int total_qty) {
		this.total_qty = total_qty;
	}
	
	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public boolean isReturn_type() {
		return return_type;
	}

	public void setReturn_type(boolean return_type) {
		this.return_type = return_type;
	}

	public String getReturn_remarks() {
		return return_remarks;
	}

	public void setReturn_remarks(String return_remarks) {
		this.return_remarks = return_remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTransporter_name() {
		return transporter_name;
	}

	public void setTransporter_name(String transporter_name) {
		this.transporter_name = transporter_name;
	}

	public String getTransporter_code() {
		return transporter_code;
	}

	public void setTransporter_code(String transporter_code) {
		this.transporter_code = transporter_code;
	}

	public String getAdvice_id() {
		return advice_id;
	}

	public void setAdvice_id(String advice_id) {
		this.advice_id = advice_id;
	}

	public String getWeighment_status() {
		return weighment_status;
	}

	public void setWeighment_status(String weighment_status) {
		this.weighment_status = weighment_status;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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

	
}
