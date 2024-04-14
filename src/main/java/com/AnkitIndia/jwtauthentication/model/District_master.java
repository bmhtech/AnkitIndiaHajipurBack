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

@Entity
@Table(name="District_master")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class District_master extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String dist_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String dist_name;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String state_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String state_name;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String country;

}
