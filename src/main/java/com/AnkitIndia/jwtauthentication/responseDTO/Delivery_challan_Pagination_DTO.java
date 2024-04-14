package com.AnkitIndia.jwtauthentication.responseDTO;



public class Delivery_challan_Pagination_DTO {

	private Long id;
	
	private String delivery_cid;
	
	private String challan_date;
	
	private String challanno;
	
    private String partyname;
     
 	private String inv_type_name;
 	
 	private String invoicestatus;
 	
 	private String ref_type;
 	
 	private String price_term;
     
	private String modified_type;

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

	public String getChallan_date() {
		return challan_date;
	}

	public void setChallan_date(String challan_date) {
		this.challan_date = challan_date;
	}


	public String getInvoicestatus() {
		return invoicestatus;
	}

	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}

	public String getRef_type() {
		return ref_type;
	}

	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}

	public String getPrice_term() {
		return price_term;
	}

	public void setPrice_term(String price_term) {
		this.price_term = price_term;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public String getChallanno() {
		return challanno;
	}

	public void setChallanno(String challanno) {
		this.challanno = challanno;
	}

	public String getPartyname() {
		return partyname;
	}

	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}

	public String getInv_type_name() {
		return inv_type_name;
	}

	public void setInv_type_name(String inv_type_name) {
		this.inv_type_name = inv_type_name;
	}

	public Delivery_challan_Pagination_DTO(Long id, String delivery_cid, String challan_date, String challanno,
			String partyname, String inv_type_name, String invoicestatus, String ref_type, String price_term,
			String modified_type) {
		super();
		this.id = id;
		this.delivery_cid = delivery_cid;
		this.challan_date = challan_date;
		this.challanno = challanno;
		this.partyname = partyname;
		this.inv_type_name = inv_type_name;
		this.invoicestatus = invoicestatus;
		this.ref_type = ref_type;
		this.price_term = price_term;
		this.modified_type = modified_type;
	}

	
	
}
