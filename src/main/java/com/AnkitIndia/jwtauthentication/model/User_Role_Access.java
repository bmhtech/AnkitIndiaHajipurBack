package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="user_role_access")
public class User_Role_Access{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String role_id;
	
	@Column(columnDefinition="varchar(100)")
	private String user_role;
	
	@Column(columnDefinition="varchar(100)")
	private String parent_role_id;
	
	@Column(columnDefinition="varchar(100)")
	private String Parent_role_name;
	
	@Column(columnDefinition="varchar(1000)")
	private String item_master;
	
	@Column(columnDefinition="varchar(1000)")
	private String supplier_master;
	
	@Column(columnDefinition="varchar(1000)")
	private String cusromer_master;
	
	@Column(columnDefinition="varchar(1000)")
	private String broker_master;
	
	@Column(columnDefinition="varchar(1000)")
	private String other_partner_master;
	
	@Column(columnDefinition="varchar(1000)")
	private String other_master;
	
	@Column(columnDefinition="varchar(2000)")
	private String mislenious_master;
	
	@Column(columnDefinition="varchar(2000)")
	private String purchase_inventory;
	
	@Column(columnDefinition="varchar(1000)")
	private String weighment;
	
	@Column(columnDefinition="varchar(1000)")
	private String sales_transaction;
	
	@Column(columnDefinition="varchar(1000)")
	private String production_module;
	
	@Column(columnDefinition="varchar(1000)")
	private String stock_transfer;
	
	@Column(columnDefinition="varchar(1000)")
	private String gate_pass;
	
	@Column(columnDefinition="varchar(1000)")
	private String sales_pur_report;
	
	@Column(columnDefinition="varchar(1000)")
	private String stock_transaction;
	
	@Lob
	 String roleaccessjson;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="user_Role_Access",cascade =CascadeType.ALL)
	private Role Role;
	

	public Role getRole() {
		return Role;
	}

	public void setRole(Role role) {
		Role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public String getParent_role_id() {
		return parent_role_id;
	}

	public void setParent_role_id(String parent_role_id) {
		this.parent_role_id = parent_role_id;
	}

	public String getParent_role_name() {
		return Parent_role_name;
	}

	public void setParent_role_name(String parent_role_name) {
		Parent_role_name = parent_role_name;
	}

	public String getItem_master() {
		return item_master;
	}

	public void setItem_master(String item_master) {
		this.item_master = item_master;
	}

	public String getSupplier_master() {
		return supplier_master;
	}

	public void setSupplier_master(String supplier_master) {
		this.supplier_master = supplier_master;
	}

	public String getCusromer_master() {
		return cusromer_master;
	}

	public void setCusromer_master(String cusromer_master) {
		this.cusromer_master = cusromer_master;
	}

	public String getBroker_master() {
		return broker_master;
	}

	public void setBroker_master(String broker_master) {
		this.broker_master = broker_master;
	}

	public String getOther_partner_master() {
		return other_partner_master;
	}

	public void setOther_partner_master(String other_partner_master) {
		this.other_partner_master = other_partner_master;
	}

	public String getOther_master() {
		return other_master;
	}

	public void setOther_master(String other_master) {
		this.other_master = other_master;
	}

	public String getMislenious_master() {
		return mislenious_master;
	}

	public void setMislenious_master(String mislenious_master) {
		this.mislenious_master = mislenious_master;
	}

	public String getPurchase_inventory() {
		return purchase_inventory;
	}

	public void setPurchase_inventory(String purchase_inventory) {
		this.purchase_inventory = purchase_inventory;
	}

	public String getWeighment() {
		return weighment;
	}

	public void setWeighment(String weighment) {
		this.weighment = weighment;
	}

	public String getSales_transaction() {
		return sales_transaction;
	}

	public void setSales_transaction(String sales_transaction) {
		this.sales_transaction = sales_transaction;
	}

	public String getProduction_module() {
		return production_module;
	}

	public void setProduction_module(String production_module) {
		this.production_module = production_module;
	}

	public String getStock_transfer() {
		return stock_transfer;
	}

	public void setStock_transfer(String stock_transfer) {
		this.stock_transfer = stock_transfer;
	}

	public String getSales_pur_report() {
		return sales_pur_report;
	}

	public void setSales_pur_report(String sales_pur_report) {
		this.sales_pur_report = sales_pur_report;
	}

	public String getStock_transaction() {
		return stock_transaction;
	}

	public void setStock_transaction(String stock_transaction) {
		this.stock_transaction = stock_transaction;
	}
	
	public String getGate_pass() {
		return gate_pass;
	}

	public void setGate_pass(String gate_pass) {
		this.gate_pass = gate_pass;
	}
	
	public String getRoleaccessjson() {
		return roleaccessjson;
	}

	public void setRoleaccessjson(String roleaccessjson) {
		this.roleaccessjson = roleaccessjson;
	}

	
	
}
