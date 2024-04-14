package com.AnkitIndia.jwtauthentication.responseDTO;

public class Pur_Bill_Pagination_DTO {
	private Long id;
	private String pur_bill_id;
	private String pur_bill_no;
	private String bill_date;
	private String grn_for;
	private String purchase_typename;
	private String supplier;
	private String vehicleno;
	private double net_payable_party;
	private String modified_type;
	private boolean item_type;
	private int export;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPur_bill_id() {
		return pur_bill_id;
	}
	public void setPur_bill_id(String pur_bill_id) {
		this.pur_bill_id = pur_bill_id;
	}
	public String getPur_bill_no() {
		return pur_bill_no;
	}
	public void setPur_bill_no(String pur_bill_no) {
		this.pur_bill_no = pur_bill_no;
	}
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	public String getGrn_for() {
		return grn_for;
	}
	public void setGrn_for(String grn_for) {
		this.grn_for = grn_for;
	}
	public String getPurchase_typename() {
		return purchase_typename;
	}
	public void setPurchase_typename(String purchase_typename) {
		this.purchase_typename = purchase_typename;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public double getNet_payable_party() {
		return net_payable_party;
	}
	public void setNet_payable_party(double net_payable_party) {
		this.net_payable_party = net_payable_party;
	}
	public String getModified_type() {
		return modified_type;
	}
	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}
	public boolean isItem_type() {
		return item_type;
	}
	public void setItem_type(boolean item_type) {
		this.item_type = item_type;
	}
	public int getExport() {
		return export;
	}
	public void setExport(int export) {
		this.export = export;
	}
	public Pur_Bill_Pagination_DTO(Long id, String pur_bill_id, String pur_bill_no, String bill_date, String grn_for,
			String purchase_typename, String supplier, String vehicleno, double net_payable_party, String modified_type,
			boolean item_type, int export) {
		super();
		this.id = id;
		this.pur_bill_id = pur_bill_id;
		this.pur_bill_no = pur_bill_no;
		this.bill_date = bill_date;
		this.grn_for = grn_for;
		this.purchase_typename = purchase_typename;
		this.supplier = supplier;
		this.vehicleno = vehicleno;
		this.net_payable_party = net_payable_party;
		this.modified_type = modified_type;
		this.item_type = item_type;
		this.export = export;
	}
	
	
	
}
