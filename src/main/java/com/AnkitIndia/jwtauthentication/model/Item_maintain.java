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
@Table(name = "Item_maintain", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
        	"business_unit","item_id","packing_id"
        })
    })
public class Item_maintain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_id;
	
	@Column(columnDefinition="Double(10,2) default 0.00")
	private double standard_rate;
	
	@Column(columnDefinition="Double(10,2) default 0.00")
	private double mrp;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_group;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_category;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double item_opening_stk;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double item_purchase_stk;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double item_pur_return;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double item_sales_stk;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double item_sales_return;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double item_stk_trans;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double item_trans_received;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double item_curr_stk;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double item_close_stk;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double pack_opening_stk;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double pack_purchase_stk;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double pack_pur_return;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double pack_sales_stk;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double pack_sales_return;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double pack_stk_trans;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double pack_trans_received;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double pack_curr_stk;
	
	@Column(columnDefinition="Double(12,2) default 0.00")
	private double pack_close_stk;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean item_active;
	
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
	
	public Item_maintain() {
		// TODO Auto-generated constructor stub
	}

	public Item_maintain(String business_unit, String item_id, String item_code, String packing_id,
			double standard_rate, double mrp, String item_type, String item_group, String item_category,boolean item_active,
			String fin_year, String company_id, String username, String modified_type, LocalDateTime inserted_on,
			String inserted_by,LocalDateTime updated_on,String updated_by) {
		super();
		this.business_unit = business_unit;
		this.item_id = item_id;
		this.item_code = item_code;
		this.packing_id = packing_id;
		this.standard_rate = standard_rate;
		this.mrp = mrp;
		this.item_type = item_type;
		this.item_group = item_group;
		this.item_category = item_category;
		this.item_active = item_active;
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

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getPacking_id() {
		return packing_id;
	}

	public void setPacking_id(String packing_id) {
		this.packing_id = packing_id;
	}

	public double getStandard_rate() {
		return standard_rate;
	}

	public void setStandard_rate(double standard_rate) {
		this.standard_rate = standard_rate;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getItem_group() {
		return item_group;
	}

	public void setItem_group(String item_group) {
		this.item_group = item_group;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	
	public double getItem_opening_stk() {
		return item_opening_stk;
	}

	public void setItem_opening_stk(double item_opening_stk) {
		this.item_opening_stk = item_opening_stk;
	}

	public double getItem_purchase_stk() {
		return item_purchase_stk;
	}

	public void setItem_purchase_stk(double item_purchase_stk) {
		this.item_purchase_stk = item_purchase_stk;
	}

	public double getItem_pur_return() {
		return item_pur_return;
	}

	public void setItem_pur_return(double item_pur_return) {
		this.item_pur_return = item_pur_return;
	}

	public double getItem_sales_stk() {
		return item_sales_stk;
	}

	public void setItem_sales_stk(double item_sales_stk) {
		this.item_sales_stk = item_sales_stk;
	}

	public double getItem_sales_return() {
		return item_sales_return;
	}

	public void setItem_sales_return(double item_sales_return) {
		this.item_sales_return = item_sales_return;
	}

	public double getItem_stk_trans() {
		return item_stk_trans;
	}

	public void setItem_stk_trans(double item_stk_trans) {
		this.item_stk_trans = item_stk_trans;
	}

	public double getItem_trans_received() {
		return item_trans_received;
	}

	public void setItem_trans_received(double item_trans_received) {
		this.item_trans_received = item_trans_received;
	}

	public double getItem_curr_stk() {
		return item_curr_stk;
	}

	public void setItem_curr_stk(double item_curr_stk) {
		this.item_curr_stk = item_curr_stk;
	}

	public double getItem_close_stk() {
		return item_close_stk;
	}

	public void setItem_close_stk(double item_close_stk) {
		this.item_close_stk = item_close_stk;
	}

	public double getPack_opening_stk() {
		return pack_opening_stk;
	}

	public void setPack_opening_stk(double pack_opening_stk) {
		this.pack_opening_stk = pack_opening_stk;
	}

	public double getPack_purchase_stk() {
		return pack_purchase_stk;
	}

	public void setPack_purchase_stk(double pack_purchase_stk) {
		this.pack_purchase_stk = pack_purchase_stk;
	}

	public double getPack_pur_return() {
		return pack_pur_return;
	}

	public void setPack_pur_return(double pack_pur_return) {
		this.pack_pur_return = pack_pur_return;
	}

	public double getPack_sales_stk() {
		return pack_sales_stk;
	}

	public void setPack_sales_stk(double pack_sales_stk) {
		this.pack_sales_stk = pack_sales_stk;
	}

	public double getPack_sales_return() {
		return pack_sales_return;
	}

	public void setPack_sales_return(double pack_sales_return) {
		this.pack_sales_return = pack_sales_return;
	}

	public double getPack_stk_trans() {
		return pack_stk_trans;
	}

	public void setPack_stk_trans(double pack_stk_trans) {
		this.pack_stk_trans = pack_stk_trans;
	}

	public double getPack_trans_received() {
		return pack_trans_received;
	}

	public void setPack_trans_received(double pack_trans_received) {
		this.pack_trans_received = pack_trans_received;
	}

	public double getPack_curr_stk() {
		return pack_curr_stk;
	}

	public void setPack_curr_stk(double pack_curr_stk) {
		this.pack_curr_stk = pack_curr_stk;
	}

	public double getPack_close_stk() {
		return pack_close_stk;
	}

	public void setPack_close_stk(double pack_close_stk) {
		this.pack_close_stk = pack_close_stk;
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
		return "Item_maintain [id=" + id + ", business_unit=" + business_unit + ", item_id=" + item_id + ", item_code="
				+ item_code + ", packing_id=" + packing_id + ", standard_rate=" + standard_rate + ", mrp=" + mrp
				+ ", item_type=" + item_type + ", item_group=" + item_group + ", item_category=" + item_category
				+ ", fin_year=" + fin_year + ", company_id=" + company_id + ", username=" + username
				+ ", modified_type=" + modified_type + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by
				+ "]";
	}
	
	

}
