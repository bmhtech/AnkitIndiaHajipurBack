package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

public class Sales_Order_Pagination_DTO {
	
	private Long id;
	
	private String order_id;
	
	private String order_date;
	
	private String order_no;
	
	private String order_type;
	
	private String customer;
	
	private String customer_name;
	
	private String delivery_date;

	private String q_status;
	
	private String ref_type;
	
	private String inv_type;
	
	private double net_amount;

	private boolean loading_status;

	private String modified_type;
	
	private String updated_by;
	
	private String deleted_by;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
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

	public String getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}

	public String getQ_status() {
		return q_status;
	}

	public void setQ_status(String q_status) {
		this.q_status = q_status;
	}

	public String getRef_type() {
		return ref_type;
	}

	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}

	public String getInv_type() {
		return inv_type;
	}

	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
	}

	public double getNet_amount() {
		return net_amount;
	}

	public void setNet_amount(double net_amount) {
		this.net_amount = net_amount;
	}

	public boolean isLoading_status() {
		return loading_status;
	}

	public void setLoading_status(boolean loading_status) {
		this.loading_status = loading_status;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Sales_Order_Pagination_DTO(Long id, String order_id, String order_date, String order_no, String order_type,
			String customer, String customer_name, String delivery_date, String q_status, String ref_type,
			String inv_type, double net_amount, boolean loading_status, String modified_type, String updated_by,
			String deleted_by) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.order_date = order_date;
		this.order_no = order_no;
		this.order_type = order_type;
		this.customer = customer;
		this.customer_name = customer_name;
		this.delivery_date = delivery_date;
		this.q_status = q_status;
		this.ref_type = ref_type;
		this.inv_type = inv_type;
		this.net_amount = net_amount;
		this.loading_status = loading_status;
		this.modified_type = modified_type;
		this.updated_by = updated_by;
		this.deleted_by = deleted_by;
	}

	
	
	

	
}
