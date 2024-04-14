package com.AnkitIndia.jwtauthentication.responseDTO;

public class Wm_unload_advice_Pagination_DTO {
	private Long id;
	private String unadviceid;
	private String unadviceno;
	private String ula_date;
	private String customer;
	private String business_unitname;
	private String item_type;
	private String item_subtypename;
	private String vehicle_no;
	private boolean we_req;
	private String ref_type;
	private String modified_type;
	private String advice_type;
	private String busi_partner;
	private int weighment_status;
	
	public int getWeighment_status() {
		return weighment_status;
	}
	public void setWeighment_status(int weighment_status) {
		this.weighment_status = weighment_status;
	}
	public String getAdvice_type() {
		return advice_type;
	}
	public void setAdvice_type(String advice_type) {
		this.advice_type = advice_type;
	}
	public String getBusi_partner() {
		return busi_partner;
	}
	public void setBusi_partner(String busi_partner) {
		this.busi_partner = busi_partner;
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
	public String getUla_date() {
		return ula_date;
	}
	public void setUla_date(String ula_date) {
		this.ula_date = ula_date;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getBusiness_unitname() {
		return business_unitname;
	}
	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getItem_subtypename() {
		return item_subtypename;
	}
	public void setItem_subtypename(String item_subtypename) {
		this.item_subtypename = item_subtypename;
	}
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public boolean isWe_req() {
		return we_req;
	}
	public void setWe_req(boolean we_req) {
		this.we_req = we_req;
	}
	public String getRef_type() {
		return ref_type;
	}
	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}
	public String getModified_type() {
		return modified_type;
	}
	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}
	public Wm_unload_advice_Pagination_DTO(Long id, String unadviceid, String unadviceno, String ula_date,
			String customer, String business_unitname, String item_type, String item_subtypename, String vehicle_no,
			boolean we_req, String ref_type, String modified_type, String advice_type, String busi_partner,
			int weighment_status) {
		super();
		this.id = id;
		this.unadviceid = unadviceid;
		this.unadviceno = unadviceno;
		this.ula_date = ula_date;
		this.customer = customer;
		this.business_unitname = business_unitname;
		this.item_type = item_type;
		this.item_subtypename = item_subtypename;
		this.vehicle_no = vehicle_no;
		this.we_req = we_req;
		this.ref_type = ref_type;
		this.modified_type = modified_type;
		this.advice_type = advice_type;
		this.busi_partner = busi_partner;
		this.weighment_status = weighment_status;
	}
	
	

}
