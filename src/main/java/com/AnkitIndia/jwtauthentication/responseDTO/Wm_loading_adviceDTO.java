package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;

public class Wm_loading_adviceDTO {
	
	private Long id;
	
	private String advice_id;
	
	private String company_id;
	
	private String advice_type;
	
	private String advice_no;
	
	private Date advice_date;
	
	private String bus_partner;
	
	private boolean wereq_active;
	
	private String b_unit;
	
	private String load_point;
	
	private String vehicle_id;
	
	private String vehicle_no;
	
	private String load_by;
	
	private String erp_usr_name;
	
	private String ref_doc_type;
	
	private String doc_no;
	
	private Date doc_date;
	
	private boolean wchrgapp_active;
	
	private String remarks;
	
	private String confirmed_by;
    
	private String approval;
    
	private String reason;
	
	private String price_term;
	
	private String cust_refdocno;
	
	private String delivery_business_unit;
	
	private String unloading_point;
	
	private int loading_status;
	
	private int weighment_status;
	
	private String weighment_id;
	
	private String referance_id;
	
	private String customer;
	
	private String customer_name;
	
	private String supplier;
	
	private String supplier_name;
	
	private String billing_req;
	
	private String stk_trans_chln_status;
	
	private String stk_trans_inv_status;
	
	//For Sales Order Date
	private String order_no;
	
	private String order_date;
	
	private String staticuom;
	
	private boolean delvchallan_status;
	
	private boolean multipleloading;
	
	private boolean jobwork;
	
	private String payment_mode;
	
	private boolean terminate;
	
	private String terminated_by;
	
	private boolean refraction;
	
	private String pur_cust_refdocno;
	
	private String pur_cust_refdocnoqty;
	
	
	
	public String getPur_cust_refdocno() {
		return pur_cust_refdocno;
	}

	public void setPur_cust_refdocno(String pur_cust_refdocno) {
		this.pur_cust_refdocno = pur_cust_refdocno;
	}

	public boolean isRefraction() {
		return refraction;
	}

	public void setRefraction(boolean refraction) {
		this.refraction = refraction;
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

	public String getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}

	public boolean isDelvchallan_status() {
		return delvchallan_status;
	}

	public void setDelvchallan_status(boolean delvchallan_status) {
		this.delvchallan_status = delvchallan_status;
	}

	public boolean isMultipleloading() {
		return multipleloading;
	}

	public void setMultipleloading(boolean multipleloading) {
		this.multipleloading = multipleloading;
	}

	public String getStaticuom() {
		return staticuom;
	}

	public void setStaticuom(String staticuom) {
		this.staticuom = staticuom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLoading_status() {
		return loading_status;
	}

	public void setLoading_status(int loading_status) {
		this.loading_status = loading_status;
	}

	public int getWeighment_status() {
		return weighment_status;
	}
	
	public String getWeighment_id() {
		return weighment_id;
	}

	public void setWeighment_id(String weighment_id) {
		this.weighment_id = weighment_id;
	}

	public String getReferance_id() {
		return referance_id;
	}

	public void setReferance_id(String referance_id) {
		this.referance_id = referance_id;
	}
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setWeighment_status(int weighment_status) {
		this.weighment_status = weighment_status;
	}

	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getDelivery_business_unit() {
		return delivery_business_unit;
	}

	public void setDelivery_business_unit(String delivery_business_unit) {
		this.delivery_business_unit = delivery_business_unit;
	}

	public String getUnloading_point() {
		return unloading_point;
	}

	public void setUnloading_point(String unloading_point) {
		this.unloading_point = unloading_point;
	}

	public String getAdvice_id() {
		return advice_id;
	}

	public void setAdvice_id(String advice_id) {
		this.advice_id = advice_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getAdvice_type() {
		return advice_type;
	}

	public void setAdvice_type(String advice_type) {
		this.advice_type = advice_type;
	}

	public String getAdvice_no() {
		return advice_no;
	}

	public void setAdvice_no(String advice_no) {
		this.advice_no = advice_no;
	}

	public Date getAdvice_date() {
		return advice_date;
	}

	public void setAdvice_date(Date advice_date) {
		this.advice_date = advice_date;
	}

	public String getBus_partner() {
		return bus_partner;
	}

	public void setBus_partner(String bus_partner) {
		this.bus_partner = bus_partner;
	}

	public boolean isWereq_active() {
		return wereq_active;
	}

	public void setWereq_active(boolean wereq_active) {
		this.wereq_active = wereq_active;
	}

	public String getB_unit() {
		return b_unit;
	}

	public void setB_unit(String b_unit) {
		this.b_unit = b_unit;
	}

	public String getLoad_point() {
		return load_point;
	}

	public void setLoad_point(String load_point) {
		this.load_point = load_point;
	}
	
	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public String getLoad_by() {
		return load_by;
	}

	public void setLoad_by(String load_by) {
		this.load_by = load_by;
	}

	public String getErp_usr_name() {
		return erp_usr_name;
	}

	public void setErp_usr_name(String erp_usr_name) {
		this.erp_usr_name = erp_usr_name;
	}

	public String getRef_doc_type() {
		return ref_doc_type;
	}

	public void setRef_doc_type(String ref_doc_type) {
		this.ref_doc_type = ref_doc_type;
	}

	public String getDoc_no() {
		return doc_no;
	}

	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}

	public Date getDoc_date() {
		return doc_date;
	}

	public void setDoc_date(Date doc_date) {
		this.doc_date = doc_date;
	}

	public boolean isWchrgapp_active() {
		return wchrgapp_active;
	}

	public void setWchrgapp_active(boolean wchrgapp_active) {
		this.wchrgapp_active = wchrgapp_active;
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

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPrice_term() {
		return price_term;
	}

	public void setPrice_term(String price_term) {
		this.price_term = price_term;
	}

	public String getCust_refdocno() {
		return cust_refdocno;
	}

	public void setCust_refdocno(String cust_refdocno) {
		this.cust_refdocno = cust_refdocno;
	}
	
	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getBilling_req() {
		return billing_req;
	}

	public void setBilling_req(String billing_req) {
		this.billing_req = billing_req;
	}

	public String getStk_trans_chln_status() {
		return stk_trans_chln_status;
	}

	public void setStk_trans_chln_status(String stk_trans_chln_status) {
		this.stk_trans_chln_status = stk_trans_chln_status;
	}

	public String getStk_trans_inv_status() {
		return stk_trans_inv_status;
	}

	public void setStk_trans_inv_status(String stk_trans_inv_status) {
		this.stk_trans_inv_status = stk_trans_inv_status;
	}

	public boolean isJobwork() {
		return jobwork;
	}

	public void setJobwork(boolean jobwork) {
		this.jobwork = jobwork;
	}
	
	
}
