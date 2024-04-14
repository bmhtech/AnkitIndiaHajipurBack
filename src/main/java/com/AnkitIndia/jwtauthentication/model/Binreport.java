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
@Table(name="Binreport")
public class Binreport extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String binreportid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String approvedby;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String approvedbyname;
	
	

	@OneToMany(fetch = FetchType.EAGER,mappedBy="binreport",cascade = CascadeType.ALL)
	private Set<Binreportdetails> binreportdetails;

}
