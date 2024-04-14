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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="Dailyproduction")
@Data
@EqualsAndHashCode(callSuper=false)
public class Dailyproduction extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String dailyproductionid;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String shopfloor;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String shopfloor_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date;
	
	@Column(columnDefinition="TEXT")
	private String remarks;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="dailyproduction",cascade = CascadeType.ALL)
	private Set<Dailyproduction_Dtls> dailyproduction_Dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="dailyproduction",cascade = CascadeType.ALL)
	private Set<Dailyproduction_Dtls_One> dailyproduction_Dtls_One;

}
