package com.AnkitIndia.jwtauthentication.transResponseDTO;

public class Item_master_pack_mat_tagDTO {
	
	private int sl_no;
	
	private String item_id;
	
	private String item_code;
	
	private String item_name;
	
	private String capacity;
	
	private String item_uom;
	
	private String empbagwt_based_on;
	
	private String empty_big_wt;
	
	private String uom1;
	
	private double tolerance;
	
	private String company_id;
	
	private String fin_year;

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getItem_uom() {
		return item_uom;
	}

	public void setItem_uom(String item_uom) {
		this.item_uom = item_uom;
	}
	
	public String getEmpbagwt_based_on() {
		return empbagwt_based_on;
	}

	public void setEmpbagwt_based_on(String empbagwt_based_on) {
		this.empbagwt_based_on = empbagwt_based_on;
	}

	public String getEmpty_big_wt() {
		return empty_big_wt;
	}

	public void setEmpty_big_wt(String empty_big_wt) {
		this.empty_big_wt = empty_big_wt;
	}

	public String getUom1() {
		return uom1;
	}

	public void setUom1(String uom1) {
		this.uom1 = uom1;
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public Item_master_pack_mat_tagDTO() {
		super();
	}

	public Item_master_pack_mat_tagDTO(int sl_no, String item_id, String item_code, String item_name, String capacity,
			String item_uom, String empbagwt_based_on, String empty_big_wt, String uom1, double tolerance,
			String company_id, String fin_year) {
		super();
		this.sl_no = sl_no;
		this.item_id = item_id;
		this.item_code = item_code;
		this.item_name = item_name;
		this.capacity = capacity;
		this.item_uom = item_uom;
		this.empbagwt_based_on = empbagwt_based_on;
		this.empty_big_wt = empty_big_wt;
		this.uom1 = uom1;
		this.tolerance = tolerance;
		this.company_id = company_id;
		this.fin_year = fin_year;
	}
	
	
}
