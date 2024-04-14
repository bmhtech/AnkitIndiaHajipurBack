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
@Table(name="Bingroup")
public class Bingroup extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String bingroupid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bingroupname;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String description;
	
}
