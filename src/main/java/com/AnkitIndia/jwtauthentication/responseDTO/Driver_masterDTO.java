package com.AnkitIndia.jwtauthentication.responseDTO;

public class Driver_masterDTO {

	private Long id;
	
	private String driver_id;
	
	private String driver_name;
	
	private Long phone_no;
	
	private String address;
	
	private String identity;
	
	private String doc_type;
	
	private String doc_no;
	
	private String veh_no;
	
	private String doc_img;
	
	private String catagory;

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public Long getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(Long phone_no) {
		this.phone_no = phone_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String getDoc_no() {
		return doc_no;
	}

	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}

	public String getVeh_no() {
		return veh_no;
	}

	public void setVeh_no(String veh_no) {
		this.veh_no = veh_no;
	}
	
	

	
	public String getDoc_img() {
		return doc_img;
	}

	public void setDoc_img(String doc_img) {
		this.doc_img = doc_img;
	}

	public Driver_masterDTO() {
		super();
	}
	
	public Driver_masterDTO(Long id,String driver_id, String driver_name, Long phone_no, String address, String identity,
			String doc_type, String doc_no, String veh_no,String doc_img) {
		super();
		this.id=id;
		this.driver_id = driver_id;
		this.driver_name = driver_name;
		this.phone_no = phone_no;
		this.address = address;
		this.identity = identity;
		this.doc_type = doc_type;
		this.doc_no = doc_no;
		this.veh_no = veh_no;
		this.doc_img = doc_img;
	}

	@Override
	public String toString() {
		return "Driver_masterDTO [id="+id+",driver_id=" + driver_id + ", driver_name=" + driver_name + ", phone_no=" + phone_no
				+ ", address=" + address + ", identity=" + identity + ", doc_type=" + doc_type + ", doc_no=" + doc_no
				+ ", veh_no=" + veh_no + ",doc_img="+doc_img+"]";
	}
	
	
}
