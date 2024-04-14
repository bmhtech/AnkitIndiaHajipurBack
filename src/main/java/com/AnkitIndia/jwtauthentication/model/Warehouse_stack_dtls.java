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
@Table(name="Warehouse_stack_dtls")

public class Warehouse_stack_dtls extends CommonProperties
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String warehouse_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String warehouse_code;
	
	private Long sl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String stack_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_uom;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_qty; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_uom; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String opening_packing_qty; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String opening_item_qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String stack_date;
	
	
	//Static
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "w_id")
    private Warehouse_master warehouse_master;
	
}
