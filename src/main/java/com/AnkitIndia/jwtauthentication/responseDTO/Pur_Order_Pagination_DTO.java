package com.AnkitIndia.jwtauthentication.responseDTO;

public class Pur_Order_Pagination_DTO {
	
	private Long id;
	private String pur_orderid;
	private String pur_order_no;
	private String ord_date;
	private String supplier;
	private String businessunit_name;
	private String ser_item_subtype;
	private String pur_ord_type;
	private String advice_req;
	private String po_status;
	private String modified_type;
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
	public String getOrd_date() {
		return ord_date;
	}
	public void setOrd_date(String ord_date) {
		this.ord_date = ord_date;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getBusinessunit_name() {
		return businessunit_name;
	}
	public void setBusinessunit_name(String businessunit_name) {
		this.businessunit_name = businessunit_name;
	}
	public String getSer_item_subtype() {
		return ser_item_subtype;
	}
	public void setSer_item_subtype(String ser_item_subtype) {
		this.ser_item_subtype = ser_item_subtype;
	}
	public String getPur_ord_type() {
		return pur_ord_type;
	}
	public void setPur_ord_type(String pur_ord_type) {
		this.pur_ord_type = pur_ord_type;
	}
	public String getAdvice_req() {
		return advice_req;
	}
	public void setAdvice_req(String advice_req) {
		this.advice_req = advice_req;
	}
	public String getPo_status() {
		return po_status;
	}
	public void setPo_status(String po_status) {
		this.po_status = po_status;
	}
	public String getModified_type() {
		return modified_type;
	}
	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}
	
	public Pur_Order_Pagination_DTO(Long id, String pur_orderid, String pur_order_no, String ord_date, String supplier,
			String businessunit_name, String ser_item_subtype, String pur_ord_type, String advice_req, String po_status,
			String modified_type) {
		super();
		this.id = id;
		this.pur_orderid = pur_orderid;
		this.pur_order_no = pur_order_no;
		this.ord_date = ord_date;
		this.supplier = supplier;
		this.businessunit_name = businessunit_name;
		this.ser_item_subtype = ser_item_subtype;
		this.pur_ord_type = pur_ord_type;
		this.advice_req = advice_req;
		this.po_status = po_status;
		this.modified_type = modified_type;
	}
	
	
	
	
	
	
}
