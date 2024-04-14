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
@Table(name="OtherPartyMaster")
@Data
@EqualsAndHashCode(callSuper=false)
public class OtherPartyMaster extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String nopartyid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String nopartyname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;
	
}
