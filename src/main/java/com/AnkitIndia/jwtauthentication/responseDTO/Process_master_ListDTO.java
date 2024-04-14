package com.AnkitIndia.jwtauthentication.responseDTO;

public class Process_master_ListDTO {
	
     private Long id;
     private String process_id;
     private String process_no;
     private String business_unitname;
     private String shop_floorname;
     private String process_type;
   
     private String   process_desc;
     private boolean status;
     
     
     
     
	public String getProcess_desc() {
		return process_desc;
	}
	public void setProcess_desc(String process_desc) {
		this.process_desc = process_desc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProcess_id() {
		return process_id;
	}
	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}
	public String getProcess_no() {
		return process_no;
	}
	public void setProcess_no(String process_no) {
		this.process_no = process_no;
	}
	public String getBusiness_unitname() {
		return business_unitname;
	}
	public void setBusiness_unitname(String business_unitname) {
		this.business_unitname = business_unitname;
	}
	public String getShop_floorname() {
		return shop_floorname;
	}
	public void setShop_floorname(String shop_floorname) {
		this.shop_floorname = shop_floorname;
	}
	public String getProcess_type() {
		return process_type;
	}
	public void setProcess_type(String process_type) {
		this.process_type = process_type;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
     
     
     
     

}
