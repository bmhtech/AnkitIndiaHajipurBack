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
@Table(name="Production_transaction_spl_input_details")
public class Production_transaction_spl_input_details extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_trans_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_trans_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_popupid;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String packing;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double packing_qty;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String packing_uom;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double item_qty;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_uom;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double production_qty;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String production_uom;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double con_factor;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String uom_basedon;
    
    @Column(columnDefinition = "Double(10,2) default 0.00" )
	private double ratio;
    
    @Column(columnDefinition = "Double(10,2) default 0.00" )
	private double deviation;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ptid")
    private Production_transaction_spl production_transaction_spl;
}
