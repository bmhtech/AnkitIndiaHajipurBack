package com.AnkitIndia.jwtauthentication.responseDTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User_Role_AccessDTO {

	private Long id;
	private String role_access;
	private String item_master;
	private String supplier_master;
	private String cusromer_master;
	private String broker_master;
	private String other_partner_master;
	private String other_master;
	private String mislenious_master;
	private String purchase_inventory;
	private String weighment;
	private String sales_transaction;
	private String production_module;
	private String stock_transfer;
	private String sales_pur_report;
}
