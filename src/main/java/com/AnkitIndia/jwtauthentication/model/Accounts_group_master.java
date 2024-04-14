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
@Table(name="Accounts_group_master")

public class Accounts_group_master extends CommonProperties 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String accts_group_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String accts_group_code;

	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String accts_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String acc_type_name;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String accts_grp_name;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String parent_group;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String parent_group_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean accts_grp_active;
	 
	@Column(columnDefinition="int(1) default '0'")
	private int export;	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
}
