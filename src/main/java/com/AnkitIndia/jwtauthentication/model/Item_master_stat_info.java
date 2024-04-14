package com.AnkitIndia.jwtauthentication.model;

import java.util.Date;

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
@Table(name="Item_master_stat_info")
public class Item_master_stat_info extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tax_code;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tax_rate;
	
	
	private Date eff_date;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean applicable;
	
	private boolean insert_status;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "Im_Id")
    private Item_master item_master;

}
