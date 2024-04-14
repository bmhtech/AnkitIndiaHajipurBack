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
@Table(name="Item_master_alternative_dtls")
public class Item_master_alternative_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_id_old;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_group;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_category;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String mstock_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String hsn_code;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean insert_status;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String addless;
	
	@Column(columnDefinition = "Double(12,0)")
	private double packing_cost;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "Im_Id")
    private Item_master item_master;

}
