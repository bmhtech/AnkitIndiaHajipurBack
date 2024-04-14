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
@Table(name="Pur_Quality_Check")
public class Pur_Quality_Check extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quality_check_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qcno;
	
	private Date qc_date;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String businessunit_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String supplier_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String supplier;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_sub_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ref_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String approved_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qc_by;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String vehicle_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String referenceid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String per_obs_status;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="pur_Quality_Check",cascade=CascadeType.ALL)
	private Set<Pur_Quality_Check_Details> pur_Quality_Check_Details;

}
