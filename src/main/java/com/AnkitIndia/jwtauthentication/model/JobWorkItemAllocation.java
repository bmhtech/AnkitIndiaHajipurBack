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
@Table(name="JobWorkItemAllocation")

public class JobWorkItemAllocation extends CommonProperties
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String job_item_alloc_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String job_item;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String job_item_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String party;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String party_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qty_uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qty_uom_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qty;
}
