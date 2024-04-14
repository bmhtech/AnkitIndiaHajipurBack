package com.AnkitIndia.jwtauthentication.transResponseDTO;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Pur_OrderDTO {

	private Long id;

	private String pur_orderid;
	
	private String pur_order_no;

	private String company_id;

	private boolean ser_item_type;
	
	private String ord_date;

	private String ser_item_subtype;
	
	private String ser_item_subtype_name;
	
	private String pur_ord_type;
	
	private String supplier_name;
	
	private String supplier;

	private String businessunit;
	
	private String businessunit_name;
	
	private String advice_req;
	
	private String po_fullfillment;

	private int no_of_advice;
	
	private String referance_type;
	
	private String consignment_type;

	private String pref_doc_no;

	private boolean master_roll_required;
	
	private boolean broker_info;
	
	private boolean madvice_sin_grn;
	
	private boolean weightment_req;
	
	private String pan_no;

	private String gst_no;

	private String cin_no;

	private String tan_no;

	private String ship_to_addr_id;

	private String ship_to_addr;

	private String pay_to_addr_id;

	private String pay_to_addr;

	private String remarks;

	private String confirmed_by;

	private String approved;

	private String reason;

	private String app_remarks;
	
	private boolean brokerage_active;
	
	private String app_chgs_id;
	
	private String fin_year;

	private String  modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private String referance_id;
	
	private String tagadvice_status;
	
	private String passing_wt;
	
	private String receipt_criteria;

	private String trans_borne_by_chgs;
	
	private String cal_no_of_advice;

	
	private double total_qty; /* New 14-04-2022*/
	
	
	private String staticuom; /* New 14-04-2022*/
	
	private String close_date;
	
	private String po_status;
	
	
	private String total_qty_copy;
	
	private int grn_status;
	
	private int unload_status;
	
	private String pur_return_status;
	
	private String purreturnid;
	
	private String poitemnumber;
	
	private String document_no;
	
	
	public String getDocument_no() {
		return document_no;
	}

	public void setDocument_no(String document_no) {
		this.document_no = document_no;
	}

	public String getTrans_borne_by_chgs() {
		return trans_borne_by_chgs;
	}

	public void setTrans_borne_by_chgs(String trans_borne_by_chgs) {
		this.trans_borne_by_chgs = trans_borne_by_chgs;
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

	public int getUnload_status() {
		return unload_status;
	}

	public void setUnload_status(int unload_status) {
		this.unload_status = unload_status;
	}

	public int getGrn_status() {
		return grn_status;
	}

	public void setGrn_status(int grn_status) {
		this.grn_status = grn_status;
	}

	public String getTotal_qty_copy() {
		return total_qty_copy;
	}

	public void setTotal_qty_copy(String total_qty_copy) {
		this.total_qty_copy = total_qty_copy;
	}

	public double getTotal_qty() {
		return total_qty;
	}

	public void setTotal_qty(double total_qty) {
		this.total_qty = total_qty;
	}

	public String getStaticuom() {
		return staticuom;
	}

	public void setStaticuom(String staticuom) {
		this.staticuom = staticuom;
	}

	public String getClose_date() {
		return close_date;
	}

	public void setClose_date(String close_date) {
		this.close_date = close_date;
	}

	public String getPo_status() {
		return po_status;
	}

	public void setPo_status(String po_status) {
		this.po_status = po_status;
	}

	public String getReceipt_criteria() {
		return receipt_criteria;
	}

	public void setReceipt_criteria(String receipt_criteria) {
		this.receipt_criteria = receipt_criteria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPur_orderid() {
		return pur_orderid;
	}

	public void setPur_orderid(String pur_orderid) {
		this.pur_orderid = pur_orderid;
	}

	public String getPur_order_no() {
		return pur_order_no;
	}

	public void setPur_order_no(String pur_order_no) {
		this.pur_order_no = pur_order_no;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public boolean isSer_item_type() {
		return ser_item_type;
	}

	public void setSer_item_type(boolean ser_item_type) {
		this.ser_item_type = ser_item_type;
	}

	public String getOrd_date() {
		return ord_date;
	}

	public void setOrd_date(String ord_date) {
		this.ord_date = ord_date;
	}

	public String getSer_item_subtype() {
		return ser_item_subtype;
	}

	public void setSer_item_subtype(String ser_item_subtype) {
		this.ser_item_subtype = ser_item_subtype;
	}

	public String getSer_item_subtype_name() {
		return ser_item_subtype_name;
	}

	public void setSer_item_subtype_name(String ser_item_subtype_name) {
		this.ser_item_subtype_name = ser_item_subtype_name;
	}

	public String getPur_ord_type() {
		return pur_ord_type;
	}

	public void setPur_ord_type(String pur_ord_type) {
		this.pur_ord_type = pur_ord_type;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getBusinessunit() {
		return businessunit;
	}

	public void setBusinessunit(String businessunit) {
		this.businessunit = businessunit;
	}

	public String getBusinessunit_name() {
		return businessunit_name;
	}

	public void setBusinessunit_name(String businessunit_name) {
		this.businessunit_name = businessunit_name;
	}

	public String getAdvice_req() {
		return advice_req;
	}

	public void setAdvice_req(String advice_req) {
		this.advice_req = advice_req;
	}

	public String getPo_fullfillment() {
		return po_fullfillment;
	}

	public void setPo_fullfillment(String po_fullfillment) {
		this.po_fullfillment = po_fullfillment;
	}

	public int getNo_of_advice() {
		return no_of_advice;
	}

	public void setNo_of_advice(int no_of_advice) {
		this.no_of_advice = no_of_advice;
	}

	public String getReferance_type() {
		return referance_type;
	}

	public void setReferance_type(String referance_type) {
		this.referance_type = referance_type;
	}

	public String getConsignment_type() {
		return consignment_type;
	}

	public void setConsignment_type(String consignment_type) {
		this.consignment_type = consignment_type;
	}

	public String getPref_doc_no() {
		return pref_doc_no;
	}

	public void setPref_doc_no(String pref_doc_no) {
		this.pref_doc_no = pref_doc_no;
	}

	public boolean isMaster_roll_required() {
		return master_roll_required;
	}

	public void setMaster_roll_required(boolean master_roll_required) {
		this.master_roll_required = master_roll_required;
	}

	public boolean isBroker_info() {
		return broker_info;
	}

	public void setBroker_info(boolean broker_info) {
		this.broker_info = broker_info;
	}

	public boolean isMadvice_sin_grn() {
		return madvice_sin_grn;
	}

	public void setMadvice_sin_grn(boolean madvice_sin_grn) {
		this.madvice_sin_grn = madvice_sin_grn;
	}

	public boolean isWeightment_req() {
		return weightment_req;
	}

	public void setWeightment_req(boolean weightment_req) {
		this.weightment_req = weightment_req;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getGst_no() {
		return gst_no;
	}

	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}

	public String getCin_no() {
		return cin_no;
	}

	public void setCin_no(String cin_no) {
		this.cin_no = cin_no;
	}

	public String getTan_no() {
		return tan_no;
	}

	public void setTan_no(String tan_no) {
		this.tan_no = tan_no;
	}

	public String getShip_to_addr_id() {
		return ship_to_addr_id;
	}

	public void setShip_to_addr_id(String ship_to_addr_id) {
		this.ship_to_addr_id = ship_to_addr_id;
	}

	public String getShip_to_addr() {
		return ship_to_addr;
	}

	public void setShip_to_addr(String ship_to_addr) {
		this.ship_to_addr = ship_to_addr;
	}

	public String getPay_to_addr_id() {
		return pay_to_addr_id;
	}

	public void setPay_to_addr_id(String pay_to_addr_id) {
		this.pay_to_addr_id = pay_to_addr_id;
	}

	public String getPay_to_addr() {
		return pay_to_addr;
	}

	public void setPay_to_addr(String pay_to_addr) {
		this.pay_to_addr = pay_to_addr;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getConfirmed_by() {
		return confirmed_by;
	}

	public void setConfirmed_by(String confirmed_by) {
		this.confirmed_by = confirmed_by;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApp_remarks() {
		return app_remarks;
	}

	public void setApp_remarks(String app_remarks) {
		this.app_remarks = app_remarks;
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

	public String getReferance_id() {
		return referance_id;
	}

	public void setReferance_id(String referance_id) {
		this.referance_id = referance_id;
	}

	public String getTagadvice_status() {
		return tagadvice_status;
	}

	public void setTagadvice_status(String tagadvice_status) {
		this.tagadvice_status = tagadvice_status;
	}

	public String getPassing_wt() {
		return passing_wt;
	}

	public void setPassing_wt(String passing_wt) {
		this.passing_wt = passing_wt;
	}

	public String getCal_no_of_advice() {
		return cal_no_of_advice;
	}

	public void setCal_no_of_advice(String cal_no_of_advice) {
		this.cal_no_of_advice = cal_no_of_advice;
	}
	
	
}
