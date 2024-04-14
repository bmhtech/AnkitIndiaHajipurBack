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
@Table(name="State_master")
@Data
@EqualsAndHashCode(callSuper=false)
public class State_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String state_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String state_name;
	
	@Column(columnDefinition="varchar(10) default '0'")
	private String country_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String country_name;
	
}
