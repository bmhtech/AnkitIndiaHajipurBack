package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="Item_Service_master")
public class Item_Service_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String service_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String service_code;

	@Column(columnDefinition="varchar(80) default 'NA'")
	private String service_name;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String gst_code;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String gst_name;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String description;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String sac_code;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String ac_ledger;
	
	@Column(columnDefinition="varchar(80) default 'NA'")
	private String ac_ledger_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean service_active;
	
	

}
