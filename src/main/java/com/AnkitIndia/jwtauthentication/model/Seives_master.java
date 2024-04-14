package com.AnkitIndia.jwtauthentication.model;

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
@Table(name="Seives_master")
public class Seives_master extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String seivesid;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String itemid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_name;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="seivesmaster",cascade = CascadeType.ALL)
	private Set<Seives_Dtls> seives_Dtls;
}
