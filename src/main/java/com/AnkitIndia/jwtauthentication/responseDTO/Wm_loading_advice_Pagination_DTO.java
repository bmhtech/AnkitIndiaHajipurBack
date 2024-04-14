package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;

public class Wm_loading_advice_Pagination_DTO {
	
	
    private Long id;
	
	private String advice_id;
	
	private String advice_no;
	
	private String advice_date;
	
	private String advice_type;
	
	private String vehicle_no;
	
	private int weighment_status;
	
	private String load_point;
	
	private String customer;
	
	private String customer_name;
	
    private String order_no;
	
	private String order_date;

	private String referance_id;
	
	private String modified_type;
	

	private boolean delvchallan_status;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAdvice_id() {
		return advice_id;
	}


	public void setAdvice_id(String advice_id) {
		this.advice_id = advice_id;
	}


	public String getAdvice_no() {
		return advice_no;
	}


	public void setAdvice_no(String advice_no) {
		this.advice_no = advice_no;
	}


	public String getAdvice_date() {
		return advice_date;
	}


	public void setAdvice_date(String advice_date) {
		this.advice_date = advice_date;
	}


	public String getAdvice_type() {
		return advice_type;
	}


	public void setAdvice_type(String advice_type) {
		this.advice_type = advice_type;
	}


	public String getVehicle_no() {
		return vehicle_no;
	}


	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}


	public int getWeighment_status() {
		return weighment_status;
	}


	public void setWeighment_status(int weighment_status) {
		this.weighment_status = weighment_status;
	}


	public String getLoad_point() {
		return load_point;
	}


	public void setLoad_point(String load_point) {
		this.load_point = load_point;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getCustomer_name() {
		return customer_name;
	}


	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
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


	public String getReferance_id() {
		return referance_id;
	}


	public void setReferance_id(String referance_id) {
		this.referance_id = referance_id;
	}


	public String getModified_type() {
		return modified_type;
	}


	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}


	public boolean isDelvchallan_status() {
		return delvchallan_status;
	}


	public void setDelvchallan_status(boolean delvchallan_status) {
		this.delvchallan_status = delvchallan_status;
	}


	public Wm_loading_advice_Pagination_DTO(Long id, String advice_id, String advice_no, String advice_date,
			String advice_type, String vehicle_no, int weighment_status, String load_point, String customer,
			String customer_name, String order_no, String order_date, String referance_id, String modified_type,
			boolean delvchallan_status) {
		super();
		this.id = id;
		this.advice_id = advice_id;
		this.advice_no = advice_no;
		this.advice_date = advice_date;
		this.advice_type = advice_type;
		this.vehicle_no = vehicle_no;
		this.weighment_status = weighment_status;
		this.load_point = load_point;
		this.customer = customer;
		this.customer_name = customer_name;
		this.order_no = order_no;
		this.order_date = order_date;
		this.referance_id = referance_id;
		this.modified_type = modified_type;
		this.delvchallan_status = delvchallan_status;
	}
	

	
	
}
