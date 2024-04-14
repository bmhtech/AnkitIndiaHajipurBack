package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Stk_transfer_sales_inv_item_dtls")
public class Stk_transfer_sales_inv_item_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default '0'")
	private String stk_sales_inv_id;
	
	@Column(columnDefinition="varchar(30) default '0'")
	private String stk_sales_inv_no;
	
	private int slno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String packing;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String packing_name;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double quantity;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String uom;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double squantity;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String suom;
    
    @Column(columnDefinition = "Double(10,3) default 0.000")
	private double mat_wt;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double price;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String price_based_on;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double amount;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String discount_type;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double discount_rate;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double discount_amt;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String tax_code;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double tax_rate;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double tax_amt;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double total_amt;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String acc_norms;
    
    @Column(columnDefinition="tinyint(1) default 0")
    private boolean checkbox;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stsi_id")
	private Stk_transfer_sales_inv sTransfer_sales_inv;
}
