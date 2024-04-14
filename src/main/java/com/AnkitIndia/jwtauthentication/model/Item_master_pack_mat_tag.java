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
@Table(name="Item_master_pack_mat_tag")
public class Item_master_pack_mat_tag extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_name;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String capacity;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String uom1;
	
	@Column(columnDefinition="varchar(20) default 0")
	private String empbagwt_based_on;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String empty_big_wt;
	
	@Column(columnDefinition = "Decimal(12,2)")
	private double tolerance;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_uom;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean insert_status;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "Im_Id")
    private Item_master item_master;

}
