package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Stk_Transfer_Challan_Item_Dtls")
public class Stk_Transfer_Challan_Item_Dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_challan_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_challan_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String slno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_code;
	
	@Column(columnDefinition="varchar(200) default '0'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String packing;
	
	@Column(columnDefinition="varchar(200) default '0'")
	private String packing_name;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double quantity;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String uom;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double squantity;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String suom;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double mat_wt;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double price;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String price_based_on;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double amount;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tax_code;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tax_rate;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double tax_amt;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double total_amt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String acc_norms;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String checkbox;
	
	@Column(columnDefinition = "Double(12,2)")
	private double cgst_amt;
	
	@Column(columnDefinition = "Double(12,2)")
	private double sgst_amt;
	
	@Column(columnDefinition = "Double(12,2)")
	private double igst_amt;
	
	private int bags;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stc_id")
	private Stk_Transfer_Challan stk_Transfer_Challan;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan_Item_Dtls",cascade = CascadeType.ALL)
	private Stk_transfer_grn_item_details stk_transfer_grn_item_details;
}
