package com.AnkitIndia.jwtauthentication.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="Item_master_other_info")
public class Item_master_other_info extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String gen_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String self_life;
	
	private Date exp_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String specific_desc;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String ser_item;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String non_store_item;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String stock_item;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean insert_status;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "Im_Id")
    private Item_master item_master;

}
