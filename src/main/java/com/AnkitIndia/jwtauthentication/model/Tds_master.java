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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Tds_master")
public class Tds_master extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tds_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tds_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tds_section;
	
	@Column(columnDefinition = "Decimal(12,2) default 0.00")
	private double tds_rate;
	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tds_acc;
	
	@Column(columnDefinition="varchar(150) default '0'")
	private String tds_accname;
}
