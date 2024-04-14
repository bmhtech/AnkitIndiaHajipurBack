package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Sales_transaction", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "business_unit","item_id","packing_id","reference_id"
            })
    })
public class Sales_transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_id;
		
	@Column(columnDefinition="varchar(50) default '0'")
	private String packing_id;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double sord_item_qty;
	
	@Column(columnDefinition = "Double(10,0)")
	private double sord_pack_qty;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double sales_item_qty;
	
	@Column(columnDefinition = "Double(10,0)")
	private double sales_pack_qty;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double load_item_qty;
	
	@Column(columnDefinition = "Double(10,0)")
	private double load_pack_qty;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double delv_item_qty;
	
	@Column(columnDefinition = "Double(10,0)")
	private double delv_pack_qty;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String packing_item;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String reference_id;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String fin_year;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String company_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String username;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String updated_by;
	
	public Sales_transaction() {
		// TODO Auto-generated constructor stub
	}
	
	public Sales_transaction(String business_unit, String item_id, String packing_id,double sord_item_qty,
			double sord_pack_qty, double sales_item_qty,
			double sales_pack_qty, double load_item_qty, double load_pack_qty, double delv_item_qty,
			double delv_pack_qty, String packing_item , String reference_id, String fin_year, String company_id, String username,
			String modified_type, LocalDateTime inserted_on, String inserted_by, LocalDateTime updated_on,
			String updated_by) {
		super();
		this.business_unit = business_unit;
		this.item_id = item_id;
		this.packing_id = packing_id;
		this.sord_item_qty = sord_item_qty;
		this.sord_pack_qty = sord_pack_qty;
		this.sales_item_qty = sales_item_qty;
		this.sales_pack_qty = sales_pack_qty;
		this.load_item_qty = load_item_qty;
		this.load_pack_qty = load_pack_qty;
		this.delv_item_qty = delv_item_qty;
		this.delv_pack_qty = delv_pack_qty;
		this.packing_item = packing_item;
		this.reference_id = reference_id;
		this.fin_year = fin_year;
		this.company_id = company_id;
		this.username = username;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
	}
	
	public Sales_transaction(String business_unit, String item_id, String packing_id, double sales_item_qty,
			double sales_pack_qty, double load_item_qty, double load_pack_qty, double delv_item_qty,
			double delv_pack_qty, String packing_item , String reference_id, String fin_year, String company_id, String username,
			String modified_type, LocalDateTime inserted_on, String inserted_by, LocalDateTime updated_on,
			String updated_by) {
		super();
		this.business_unit = business_unit;
		this.item_id = item_id;
		this.packing_id = packing_id;
		this.sales_item_qty = sales_item_qty;
		this.sales_pack_qty = sales_pack_qty;
		this.load_item_qty = load_item_qty;
		this.load_pack_qty = load_pack_qty;
		this.delv_item_qty = delv_item_qty;
		this.delv_pack_qty = delv_pack_qty;
		this.packing_item = packing_item;
		this.reference_id = reference_id;
		this.fin_year = fin_year;
		this.company_id = company_id;
		this.username = username;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getPacking_id() {
		return packing_id;
	}

	public void setPacking_id(String packing_id) {
		this.packing_id = packing_id;
	}
	
	public double getSord_item_qty() {
		return sord_item_qty;
	}

	public void setSord_item_qty(double sord_item_qty) {
		this.sord_item_qty = sord_item_qty;
	}

	public double getSord_pack_qty() {
		return sord_pack_qty;
	}

	public void setSord_pack_qty(double sord_pack_qty) {
		this.sord_pack_qty = sord_pack_qty;
	}

	public double getSales_item_qty() {
		return sales_item_qty;
	}

	public void setSales_item_qty(double sales_item_qty) {
		this.sales_item_qty = sales_item_qty;
	}

	public double getSales_pack_qty() {
		return sales_pack_qty;
	}

	public void setSales_pack_qty(double sales_pack_qty) {
		this.sales_pack_qty = sales_pack_qty;
	}

	public double getLoad_item_qty() {
		return load_item_qty;
	}

	public void setLoad_item_qty(double load_item_qty) {
		this.load_item_qty = load_item_qty;
	}

	public double getLoad_pack_qty() {
		return load_pack_qty;
	}

	public void setLoad_pack_qty(double load_pack_qty) {
		this.load_pack_qty = load_pack_qty;
	}

	public double getDelv_item_qty() {
		return delv_item_qty;
	}

	public void setDelv_item_qty(double delv_item_qty) {
		this.delv_item_qty = delv_item_qty;
	}

	public double getDelv_pack_qty() {
		return delv_pack_qty;
	}

	public void setDelv_pack_qty(double delv_pack_qty) {
		this.delv_pack_qty = delv_pack_qty;
	}
	
	public String getPacking_item() {
		return packing_item;
	}

	public void setPacking_item(String packing_item) {
		this.packing_item = packing_item;
	}

	public String getReference_id() {
		return reference_id;
	}

	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}

	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	@Override
	public String toString() {
		return "Sales_transaction [id=" + id + ", business_unit=" + business_unit + ", item_id=" + item_id
				+ ", packing_id=" + packing_id + ", sord_item_qty=" + sord_item_qty + ", sord_pack_qty=" + sord_pack_qty
				+ ", sales_item_qty=" + sales_item_qty + ", sales_pack_qty=" + sales_pack_qty + ", load_item_qty="
				+ load_item_qty + ", load_pack_qty=" + load_pack_qty + ", delv_item_qty=" + delv_item_qty
				+ ", delv_pack_qty=" + delv_pack_qty + ", packing_item=" + packing_item + ", reference_id="
				+ reference_id + ", fin_year=" + fin_year + ", company_id=" + company_id + ", username=" + username
				+ ", modified_type=" + modified_type + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by
				+ ", updated_on=" + updated_on + ", updated_by=" + updated_by + "]";
	}
	
	
}
