package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

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
@Table(name="Wheatacceptance")
public class Wheatacceptance extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String acceptanceid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grade;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String hlw;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String moisture_dm;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String moisture_am;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wet_gluten;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sv;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String stone;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String infestation;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String odour;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String stack_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String master_hlw;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String oven_moisture;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sixmm;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String twomm;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ofg;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String idk;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String master_stone;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String total_impurities;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broken_shirvilled;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grand_total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String potia;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String kb;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String master_infestation;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String master_odour;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String lab_chemist;
}
