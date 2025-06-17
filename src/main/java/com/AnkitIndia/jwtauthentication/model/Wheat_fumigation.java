package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="Wheat_fumigation")
public class Wheat_fumigation extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String fumigation_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String fumigation_no;
	
	private Date fumigation_date;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String businessunit_name;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="wheat_fumigation",cascade=CascadeType.ALL)
	private Set<Wheat_fumigation_details> wheat_fumigation_details;
}