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
@Table(name="Accounts_type_master")

public class Accounts_type_master extends CommonProperties 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String accts_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String acc_type_code;

	@Column(columnDefinition="varchar(100) default 'NA'")
	private String acc_type_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String acc_type_classification;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String acc_type_sub_classification;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String acc_type_catagory;

	
}
