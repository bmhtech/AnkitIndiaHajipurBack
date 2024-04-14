package com.AnkitIndia.jwtauthentication.responseDTO;

public class Tag_advice_with_po_Pagination_DTO {
	
	
	
	  private Long id;
	  
	  private String adv_po_tag_no;
	  
	  private String advice_no;
	  
	  private String ula_date; 
	  
	  private String item_subtype_name;
	  
	  private String busi_partnername;
	  
	  private String business_unitname;
	  
	  private String pur_orderno;
	  
	  private String veh_no; 
	  
	  private String modified_type;

	public String getBusiness_unitname() {
		return business_unitname;
	}

	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdv_po_tag_no() {
		return adv_po_tag_no;
	}

	public void setAdv_po_tag_no(String adv_po_tag_no) {
		this.adv_po_tag_no = adv_po_tag_no;
	}

	public String getAdvice_no() {
		return advice_no;
	}

	public void setAdvice_no(String advice_no) {
		this.advice_no = advice_no;
	}

	public String getUla_date() {
		return ula_date;
	}

	public void setUla_date(String ula_date) {
		this.ula_date = ula_date;
	}

	public String getItem_subtype_name() {
		return item_subtype_name;
	}

	public void setItem_subtype_name(String item_subtype_name) {
		this.item_subtype_name = item_subtype_name;
	}

	public String getBusi_partnername() {
		return busi_partnername;
	}

	public void setBusi_partnername(String busi_partnername) {
		this.busi_partnername = busi_partnername;
	}


	public String getPur_orderno() {
		return pur_orderno;
	}

	public void setPur_orderno(String pur_orderno) {
		this.pur_orderno = pur_orderno;
	}

	public String getVeh_no() {
		return veh_no;
	}

	public void setVeh_no(String veh_no) {
		this.veh_no = veh_no;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public Tag_advice_with_po_Pagination_DTO(Long id, String adv_po_tag_no, String advice_no, String ula_date,
			String item_subtype_name, String busi_partnername, String business_unitname, String pur_orderno,
			String veh_no, String modified_type) {
		super();
		this.id = id;
		this.adv_po_tag_no = adv_po_tag_no;
		this.advice_no = advice_no;
		this.ula_date = ula_date;
		this.item_subtype_name = item_subtype_name;
		this.busi_partnername = busi_partnername;
		this.business_unitname = business_unitname;
		this.pur_orderno = pur_orderno;
		this.veh_no = veh_no;
		this.modified_type = modified_type;
	}

	
	
	
	  
	 

}
