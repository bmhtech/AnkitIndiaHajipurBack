package com.AnkitIndia.jwtauthentication.responseDTO;

public class Pur_good_receipt_Pagination_DTO {
	private Long id;
	private String grn_id;
	private String grn_no;
	private String grn_date;
	private String b_unitname;
	private String supplier_name;
	private String purchase_typename;
	private String referance_type;
	private String vehicle_no;
	private String modified_type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGrn_id() {
		return grn_id;
	}
	public void setGrn_id(String grn_id) {
		this.grn_id = grn_id;
	}
	public String getGrn_no() {
		return grn_no;
	}
	public void setGrn_no(String grn_no) {
		this.grn_no = grn_no;
	}
	public String getGrn_date() {
		return grn_date;
	}
	public void setGrn_date(String grn_date) {
		this.grn_date = grn_date;
	}
	public String getB_unitname() {
		return b_unitname;
	}
	public void setB_unitname(String b_unitname) {
		this.b_unitname = b_unitname;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	
	
	public String getPurchase_typename() {
		return purchase_typename;
	}
	public void setPurchase_typename(String purchase_typename) {
		this.purchase_typename = purchase_typename;
	}
	public String getReferance_type() {
		return referance_type;
	}
	public void setReferance_type(String referance_type) {
		this.referance_type = referance_type;
	}
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public String getModified_type() {
		return modified_type;
	}
	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}
	public Pur_good_receipt_Pagination_DTO(Long id, String grn_id, String grn_no, String grn_date, String b_unitname,
			String supplier_name, String purchase_typename, String referance_type, String vehicle_no,
			String modified_type) {
		super();
		this.id = id;
		this.grn_id = grn_id;
		this.grn_no = grn_no;
		this.grn_date = grn_date;
		this.b_unitname = b_unitname;
		this.supplier_name = supplier_name;
		this.purchase_typename = purchase_typename;
		this.referance_type = referance_type;
		this.vehicle_no = vehicle_no;
		this.modified_type = modified_type;
	}
	
	
	

}
