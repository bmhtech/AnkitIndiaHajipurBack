package com.AnkitIndia.jwtauthentication.responseDTO;

public class SalesOrderDeliveryChallanCheckDTO {
	
	
	private String order_id;
	
	private double salesqty;
	
	private double challanqty;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public double getSalesqty() {
		return salesqty;
	}

	public void setSalesqty(double salesqty) {
		this.salesqty = salesqty;
	}

	public double getChallanqty() {
		return challanqty;
	}

	public void setChallanqty(double challanqty) {
		this.challanqty = challanqty;
	}

}
