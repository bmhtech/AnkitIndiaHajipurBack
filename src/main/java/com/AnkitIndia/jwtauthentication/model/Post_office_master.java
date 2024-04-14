package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name="Post_office_master")
@Data
@EqualsAndHashCode(callSuper=false)
public class Post_office_master extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String postid;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String post_office;
	
	private int pincode;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String dist_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String dist_name;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String state_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String state_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String country_name;
}
