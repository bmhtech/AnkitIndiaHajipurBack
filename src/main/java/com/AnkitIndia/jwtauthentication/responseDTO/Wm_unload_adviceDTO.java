
package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;

public class Wm_unload_adviceDTO {

	private Long id;
	
	private String unadviceid;
	
	private String unadviceno;
	
	private String company_id;
	
	private String item_type;
	
	private String item_subtype;
	
	private String item_subtypename;
	
	private String busi_partner;
	
	private String customer;
	
	private String advice_type;
	
	private String ref_type;
	
	private boolean we_req;
	
	private boolean we_chg_app;
	
	private String supp_ref_doc;
	
	private String supp_ref_docno;
	
	private Date supp_ref_doc_date;
	
	private String ula_date;
	
	private boolean grn_req;
	
	private String business_unit;
	
	private String business_unitname;
	
	private String transporter_code;
	
	private String transporter_name;
	
	private String vehicle_id;
	
	private String vehicle_no;
	
	private double total_qty;
	
	private String uom;
	
	private boolean return_type;
	
	private String return_remarks;
	
	private String remarks;
	
	private boolean brokerage_active;

	private String app_chgs_id;
	
	private boolean qc_required;
	
	private String referance_id;
	
	private String weighment_id;
	
	private String adv_po_tag_no;
	
	private String adv_po_tag_date;
	
	private String fin_year;
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	private String deleted_by;
	
	private String supp_name;
	
	private int unload_status;
	
	private int weighment_status;
	
	private String pur_orderid;
	
	private String passing_wt;
	
	private boolean itemtype;
	
	private String purchase_subtype;
	
	private String uadvice_status; /* New 14-04-2022*/
	
	private String close_date; /* New 14-04-2022*/

	private int grn_status;
	
	private String pur_return_status;
	
	private String purreturnid;
	
	private String poitemnumber;
	
	private String qc_status;
	
	private double loading_weight;
	
	private boolean jobwork;
	 
	private boolean looseitem;
	
	
	public boolean isJobwork() {
		return jobwork;
	}

	public void setJobwork(boolean jobwork) {
		this.jobwork = jobwork;
	}

	public boolean isLooseitem() {
		return looseitem;
	}

	public void setLooseitem(boolean looseitem) {
		this.looseitem = looseitem;
	}

	public double getLoading_weight() {
		return loading_weight;
	}

	public void setLoading_weight(double loading_weight) {
		this.loading_weight = loading_weight;
	}

	public String getQc_status() {
		return qc_status;
	}

	public void setQc_status(String qc_status) {
		this.qc_status = qc_status;
	}

	public String getPoitemnumber() {
		return poitemnumber;
	}

	public void setPoitemnumber(String poitemnumber) {
		this.poitemnumber = poitemnumber;
	}

	public String getPur_return_status() {
		return pur_return_status;
	}

	public void setPur_return_status(String pur_return_status) {
		this.pur_return_status = pur_return_status;
	}

	public String getPurreturnid() {
		return purreturnid;
	}

	public void setPurreturnid(String purreturnid) {
		this.purreturnid = purreturnid;
	}

	public int getGrn_status() {
		return grn_status;
	}

	public void setGrn_status(int grn_status) {
		this.grn_status = grn_status;
	}

	public Date getSupp_ref_doc_date() {
		return supp_ref_doc_date;
	}

	public void setSupp_ref_doc_date(Date supp_ref_doc_date) {
		this.supp_ref_doc_date = supp_ref_doc_date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnadviceid() {
		return unadviceid;
	}

	public void setUnadviceid(String unadviceid) {
		this.unadviceid = unadviceid;
	}

	public String getUnadviceno() {
		return unadviceno;
	}

	public void setUnadviceno(String unadviceno) {
		this.unadviceno = unadviceno;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getItem_subtype() {
		return item_subtype;
	}

	public void setItem_subtype(String item_subtype) {
		this.item_subtype = item_subtype;
	}

	public String getBusi_partner() {
		return busi_partner;
	}

	public void setBusi_partner(String busi_partner) {
		this.busi_partner = busi_partner;
	}
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getAdvice_type() {
		return advice_type;
	}

	public void setAdvice_type(String advice_type) {
		this.advice_type = advice_type;
	}

	public String getRef_type() {
		return ref_type;
	}

	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
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

	public String getTransporter_code() {
		return transporter_code;
	}

	public void setTransporter_code(String transporter_code) {
		this.transporter_code = transporter_code;
	}

	public String getTransporter_name() {
		return transporter_name;
	}

	public void setTransporter_name(String transporter_name) {
		this.transporter_name = transporter_name;
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

	

	public double getTotal_qty() {
		return total_qty;
	}

	public void setTotal_qty(double total_qty) {
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

	public boolean isBrokerage_active() {
		return brokerage_active;
	}

	public void setBrokerage_active(boolean brokerage_active) {
		this.brokerage_active = brokerage_active;
	}

	public String getApp_chgs_id() {
		return app_chgs_id;
	}

	public void setApp_chgs_id(String app_chgs_id) {
		this.app_chgs_id = app_chgs_id;
	}

	public boolean isQc_required() {
		return qc_required;
	}

	public void setQc_required(boolean qc_required) {
		this.qc_required = qc_required;
	}

	public String getReferance_id() {
		return referance_id;
	}

	public void setReferance_id(String referance_id) {
		this.referance_id = referance_id;
	}

	public String getWeighment_id() {
		return weighment_id;
	}

	public void setWeighment_id(String weighment_id) {
		this.weighment_id = weighment_id;
	}

	public String getAdv_po_tag_no() {
		return adv_po_tag_no;
	}

	public void setAdv_po_tag_no(String adv_po_tag_no) {
		this.adv_po_tag_no = adv_po_tag_no;
	}
	
	public String getAdv_po_tag_date() {
		return adv_po_tag_date;
	}

	public void setAdv_po_tag_date(String adv_po_tag_date) {
		this.adv_po_tag_date = adv_po_tag_date;
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

	public String getSupp_name() {
		return supp_name;
	}

	public void setSupp_name(String supp_name) {
		this.supp_name = supp_name;
	}

	public int getUnload_status() {
		return unload_status;
	}

	public void setUnload_status(int unload_status) {
		this.unload_status = unload_status;
	}

	public int getWeighment_status() {
		return weighment_status;
	}

	public void setWeighment_status(int weighment_status) {
		this.weighment_status = weighment_status;
	}

	public String getPur_orderid() {
		return pur_orderid;
	}

	public void setPur_orderid(String pur_orderid) {
		this.pur_orderid = pur_orderid;
	}

	public String getItem_subtypename() {
		return item_subtypename;
	}

	public void setItem_subtypename(String item_subtypename) {
		this.item_subtypename = item_subtypename;
	}

	public String getBusiness_unitname() {
		return business_unitname;
	}

	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}

	public String getPassing_wt() {
		return passing_wt;
	}

	public void setPassing_wt(String passing_wt) {
		this.passing_wt = passing_wt;
	}

	public boolean isItemtype() {
		return itemtype;
	}

	public void setItemtype(boolean itemtype) {
		this.itemtype = itemtype;
	}

	public String getPurchase_subtype() {
		return purchase_subtype;
	}

	public void setPurchase_subtype(String purchase_subtype) {
		this.purchase_subtype = purchase_subtype;
	}

	public String getUadvice_status() {
		return uadvice_status;
	}

	public void setUadvice_status(String uadvice_status) {
		this.uadvice_status = uadvice_status;
	}

	public String getClose_date() {
		return close_date;
	}

	public void setClose_date(String close_date) {
		this.close_date = close_date;
	}
	
	
}
